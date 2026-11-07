package rtx.kimiko.api.modules.impl.Utils;

import net.minecraft.text.Text;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Config Backup NEW: при заходе в мир (и не чаще раза в час) складывает
 * ZIP-архив всех *.json конфиг-каталога (корень + подкаталоги на 1 уровень)
 * в backups/kimiko-config-<метка времени>.zip. Хранит до 10 архивов.
 * О первом бэкапе сообщает в чат, дальнейшие проходят тихо.
 */
public class ConfigBackupModule extends Module {
    private static final long INTERVAL_MS = 60L * 60L * 1000L;
    private static final int KEEP_BACKUPS = 10;
    private static final String BACKUP_DIR = "backups";
    private static final String BACKUP_PREFIX = "kimiko-config-";

    private final BooleanSetting enabledSetting;
    private boolean wasInWorld;
    private boolean busy;
    private boolean firstNotified;
    private long lastBackupAt;

    public ConfigBackupModule() {
        super("Config Backup NEW", "Автоматический ZIP-бэкап конфигов при заходе в мир, не чаще раза в час.", Category.UTILS);
        this.enabledSetting = (BooleanSetting) this.register((Setting) new BooleanSetting("Включён", "Делать автоматические бэкапы конфигов.", true));
        this.wasInWorld = false;
        this.busy = false;
        this.firstNotified = false;
        this.lastBackupAt = 0L;
    }

    @Override
    protected void onDisable() {
        this.wasInWorld = false;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre()) {
            return;
        }
        boolean inWorld = this.mc.world != null && this.mc.player != null;
        boolean joined = inWorld && !this.wasInWorld;
        this.wasInWorld = inWorld;
        if (!joined || !this.isEnabled() || !this.enabledSetting.getValue() || this.busy) {
            return;
        }
        long now = System.currentTimeMillis();
        if (this.lastBackupAt != 0L && now - this.lastBackupAt < INTERVAL_MS) {
            return;
        }
        this.busy = true;
        this.lastBackupAt = now;
        Thread worker = new Thread(this::runBackup, "Kimiko-ConfigBackup");
        worker.setDaemon(true);
        worker.start();
    }

    private void runBackup() {
        try {
            Path root = ConfigManager.Companion.systemConfigDirectory();
            if (root == null || !Files.isDirectory(root)) {
                return;
            }
            List<Path> jsons = collectJsons(root);
            if (jsons.isEmpty()) {
                return;
            }
            Path backupDir = root.resolve(BACKUP_DIR);
            Files.createDirectories(backupDir);
            String stamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            Path target = backupDir.resolve(BACKUP_PREFIX + stamp + ".zip");
            writeZip(root, jsons, target);
            pruneOld(backupDir);
            if (!this.firstNotified) {
                this.firstNotified = true;
                this.chat("§aПервый бэкап конфигов создан: §f" + BACKUP_DIR + "/" + target.getFileName()
                        + "§7 (дальше — раз в час, без уведомлений)");
            } else {
                System.out.println("[KIMIKO] Config Backup: " + target);
            }
        } catch (Throwable t) {
            System.out.println("[KIMIKO] Config Backup: ошибка — " + t);
        } finally {
            this.busy = false;
        }
    }

    /** Все *.json из корня конфиг-каталога и его подкаталогов на 1 уровень (без папки backups). */
    private static List<Path> collectJsons(Path root) {
        List<Path> result = new ArrayList<>();
        try (Stream<Path> rootStream = Files.list(root)) {
            rootStream.sorted().forEach(path -> {
                String name = path.getFileName().toString();
                if (Files.isDirectory(path)) {
                    if (name.equals(BACKUP_DIR)) {
                        return;
                    }
                    try (Stream<Path> sub = Files.list(path)) {
                        sub.filter(p -> !Files.isDirectory(p))
                                .filter(p -> p.getFileName().toString().toLowerCase().endsWith(".json"))
                                .sorted()
                                .forEach(result::add);
                    } catch (Throwable ignored) {
                    }
                } else if (name.toLowerCase().endsWith(".json")) {
                    result.add(path);
                }
            });
        } catch (Throwable ignored) {
        }
        return result;
    }

    private static void writeZip(Path root, List<Path> jsons, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(Files.newOutputStream(target))) {
            for (Path json : jsons) {
                String relative = root.relativize(json).toString().replace('\\', '/');
                if (relative.startsWith("/")) {
                    relative = relative.substring(1);
                }
                zip.putNextEntry(new ZipEntry(relative));
                byte[] bytes = Files.readAllBytes(json);
                zip.write(bytes, 0, bytes.length);
                zip.closeEntry();
            }
            zip.finish();
        } catch (Throwable t) {
            try {
                Files.deleteIfExists(target);
            } catch (Throwable ignored) {
            }
            throw new RuntimeException("Не удалось записать архив", t);
        }
    }

    /** Оставляет только KEEP_BACKUPS самых свежих архивов kimiko-config-*.zip. */
    private static void pruneOld(Path backupDir) {
        List<Path> archives = new ArrayList<>();
        try (Stream<Path> stream = Files.list(backupDir)) {
            stream.filter(Files::isRegularFile)
                    .filter(p -> {
                        String name = p.getFileName().toString();
                        return name.startsWith(BACKUP_PREFIX) && name.endsWith(".zip");
                    })
                    .forEach(archives::add);
        } catch (Throwable ignored) {
            return;
        }
        if (archives.size() <= KEEP_BACKUPS) {
            return;
        }
        // имя содержит метку yyyyMMdd-HHmmss — лексическая сортировка = хронологическая
        archives.sort(Comparator.comparing(p -> p.getFileName().toString()));
        int extra = archives.size() - KEEP_BACKUPS;
        for (int i = 0; i < extra; i++) {
            try {
                Files.deleteIfExists(archives.get(i));
            } catch (Throwable ignored) {
            }
        }
    }

    private void chat(String s) {
        if (this.mc.player != null) {
            this.mc.player.sendMessage(Text.literal(s), false);
        }
    }
}
