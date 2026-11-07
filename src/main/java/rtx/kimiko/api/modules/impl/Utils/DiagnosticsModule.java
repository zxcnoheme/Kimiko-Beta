package rtx.kimiko.api.modules.impl.Utils;

import net.minecraft.text.Text;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Diagnostics Pack NEW: клавиша собирает ZIP с latest.log и всеми *.json
 * из конфиг-каталога Kimiko — удобно приложить к сообщению об ошибке.
 */
public class DiagnosticsModule extends Module {
    private final BindSetting bind;
    private boolean busy;
    private boolean lastDown;

    public DiagnosticsModule() {
        super("Diagnostics Pack NEW", "Клавиша собирает ZIP с latest.log и конфигами Kimiko для отчёта об ошибке.", Category.UTILS);
        this.bind = (BindSetting) this.register((Setting) new BindSetting("Клавиша", "Клавиша сборки диагностического ZIP.").setKey(-1));
        this.busy = false;
        this.lastDown = false;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        boolean down = this.bind.getValue().isDown(this.mc.getWindow().getHandle());
        if (down && !this.lastDown && !this.busy) {
            this.busy = true;
            Thread worker = new Thread(this::buildPack, "Kimiko-Diagnostics");
            worker.setDaemon(true);
            worker.start();
        }
        this.lastDown = down;
    }

    private void buildPack() {
        Path out = null;
        try {
            String stamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            out = ConfigManager.Companion.systemConfigDirectory().resolve("kimiko-diagnostics-" + stamp + ".zip");
            Files.createDirectories(out.getParent());

            try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(out.toFile()))) {
                int added = 0;

                // latest.log относительно каталога запуска игры
                try {
                    File runDir = this.mc.runDirectory;
                    File log = runDir != null ? new File(runDir, "logs/latest.log") : null;
                    if (log != null && log.isFile() && log.length() <= 32L * 1024L * 1024L) {
                        this.zipFile(zip, log, "logs/latest.log");
                        added++;
                    } else {
                        this.zipBytes(zip, "logs/latest.log.absent",
                                "latest.log недоступен".getBytes(StandardCharsets.UTF_8));
                    }
                } catch (Throwable t) {
                    this.zipBytes(zip, "logs/latest.log.error",
                            ("latest.log недоступен: " + t).getBytes(StandardCharsets.UTF_8));
                }

                // все *.json из конфиг-каталога Kimiko
                Path configDir = ConfigManager.Companion.systemConfigDirectory();
                if (Files.isDirectory(configDir)) {
                    List<Path> jsons;
                    try (Stream<Path> stream = Files.list(configDir)) {
                        jsons = stream.filter(p -> Files.isRegularFile(p)
                                        && p.getFileName().toString().toLowerCase().endsWith(".json"))
                                .collect(Collectors.toList());
                    }
                    for (Path p : jsons) {
                        if (added >= 96 || Files.size(p) > 8L * 1024L * 1024L) {
                            continue;
                        }
                        try {
                            this.zipFile(zip, p.toFile(), p.getFileName().toString());
                            added++;
                        } catch (Throwable ignored) {
                        }
                    }
                }
            }

            this.chat("§aДиагностический пакет: §f" + out.toAbsolutePath());
        } catch (Throwable t) {
            this.chat("§cНе удалось собрать пакет: §7" + t.getMessage());
            if (out != null) {
                try {
                    Files.deleteIfExists(out);
                } catch (Throwable ignored) {
                }
            }
        } finally {
            this.busy = false;
        }
    }

    private void zipFile(ZipOutputStream zip, File file, String entryName) throws Exception {
        try (FileInputStream in = new FileInputStream(file)) {
            zip.putNextEntry(new ZipEntry(entryName));
            byte[] buffer = new byte[8192];
            int read;
            while ((read = in.read(buffer)) > 0) {
                zip.write(buffer, 0, read);
            }
            zip.closeEntry();
        }
    }

    private void zipBytes(ZipOutputStream zip, String entryName, byte[] data) throws Exception {
        zip.putNextEntry(new ZipEntry(entryName));
        zip.write(data);
        zip.closeEntry();
    }

    private void chat(String s) {
        if (this.mc.player != null) {
            this.mc.player.sendMessage(Text.literal(s), false);
        }
    }
}
