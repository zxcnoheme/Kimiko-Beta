package rtx.kimiko.api.modules.impl.Utils;

import net.minecraft.text.Text;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Migration Wizard NEW: при заходе в мир, если текущий конфиг-каталог ещё
 * не создан, а рядом (в родителе) лежат старые конфиги (kimiko_old /
 * kimiko-backup / kimiko-old) — предлагает перенести их по бинду
 * «Перенести»: копирует *.json в текущий каталог и отчитывается в чат.
 * Если старых конфигов нет — молчит.
 */
public class MigrationModule extends Module {
    private static final String[] OLD_DIRS = {"kimiko_old", "kimiko-backup", "kimiko-old"};

    private final BindSetting migrateBind;
    private final BooleanSetting done;
    private final List<Path> pendingFiles = new ArrayList<>();
    private boolean pending;
    private boolean wasInWorld;
    private boolean lastDown;

    public MigrationModule() {
        super("Migration Wizard NEW", "Предлагает перенести старые конфиги Kimiko в новый каталог.", Category.UTILS);
        this.migrateBind = (BindSetting) this.register((Setting) new BindSetting("Перенести", "Клавиша запуска переноса найденных старых конфигов.").setKey(-1));
        this.done = (BooleanSetting) this.register((Setting) new BooleanSetting("Выполнено", "Перенос уже выполнен — больше не предлагать.", false));
        this.pending = false;
        this.wasInWorld = false;
        this.lastDown = false;
    }

    @Override
    protected void onDisable() {
        this.pending = false;
        this.wasInWorld = false;
        this.lastDown = false;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        // ——— обнаружение старых конфигов при заходе в мир
        boolean inWorld = this.mc.world != null && this.mc.player != null;
        boolean joined = inWorld && !this.wasInWorld;
        this.wasInWorld = inWorld;
        if (joined && !this.done.getValue() && !this.pending) {
            this.scan();
        }

        // ——— запуск переноса по бинду
        boolean down = this.migrateBind.getValue().isDown(this.mc.getWindow().getHandle());
        if (down && !this.lastDown && this.pending && inWorld) {
            this.performMigration();
        }
        this.lastDown = down;
    }

    /** Ищет *.json в старых каталогах рядом с конфиг-каталогом. Текущего каталога быть не должно. */
    private void scan() {
        try {
            Path current = ConfigManager.Companion.systemConfigDirectory();
            if (current == null || Files.exists(current)) {
                return;
            }
            Path parent = current.getParent();
            if (parent == null || !Files.isDirectory(parent)) {
                return;
            }
            this.pendingFiles.clear();
            for (String name : OLD_DIRS) {
                Path oldDir = parent.resolve(name);
                if (!Files.isDirectory(oldDir)) {
                    continue;
                }
                collectJsons(oldDir, this.pendingFiles);
            }
            if (this.pendingFiles.isEmpty()) {
                return;
            }
            this.pending = true;
            this.chat("§eНайдены старые конфиги Kimiko: §f" + this.pendingFiles.size() + " §eфайл(ов) в §f"
                    + parent + "§e. Нажмите «Перенести», чтобы скопировать их в новый каталог.");
        } catch (Throwable t) {
            System.out.println("[KIMIKO] Migration Wizard: ошибка поиска — " + t);
        }
    }

    /** Корень старого каталога и его подкаталоги на 1 уровень. */
    private static void collectJsons(Path dir, List<Path> out) {
        try (Stream<Path> stream = Files.list(dir)) {
            stream.sorted().forEach(path -> {
                String name = path.getFileName().toString();
                if (Files.isDirectory(path)) {
                    try (Stream<Path> sub = Files.list(path)) {
                        sub.filter(p -> !Files.isDirectory(p))
                                .filter(p -> p.getFileName().toString().toLowerCase().endsWith(".json"))
                                .sorted()
                                .forEach(out::add);
                    } catch (Throwable ignored) {
                    }
                } else if (name.toLowerCase().endsWith(".json")) {
                    out.add(path);
                }
            });
        } catch (Throwable ignored) {
        }
    }

    private void performMigration() {
        Path current = ConfigManager.Companion.systemConfigDirectory();
        if (current == null || this.pendingFiles.isEmpty()) {
            this.pending = false;
            return;
        }
        int copied = 0;
        int failed = 0;
        try {
            Files.createDirectories(current);
        } catch (Throwable t) {
            this.chat("§cНе удалось создать каталог конфигов: §7" + t.getMessage());
            return;
        }
        for (Path src : this.pendingFiles) {
            try {
                Path target = current.resolve(src.getFileName().toString());
                Files.copy(src, target, StandardCopyOption.REPLACE_EXISTING);
                copied++;
            } catch (Throwable t) {
                failed++;
                System.out.println("[KIMIKO] Migration Wizard: не удалось перенести " + src + " — " + t);
            }
        }
        StringBuilder report = new StringBuilder("§aПеренос завершён: §f").append(copied).append(" §aфайл(ов) скопировано");
        if (failed > 0) {
            report.append("§c, ошибок: §f").append(failed);
        }
        this.chat(report.toString());
        this.done.setValue(true);
        this.pending = false;
        this.pendingFiles.clear();
    }

    private void chat(String s) {
        if (this.mc.player != null) {
            this.mc.player.sendMessage(Text.literal(s), false);
        }
    }
}
