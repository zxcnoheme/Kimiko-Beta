package rtx.kimiko.api.modules.impl.Utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.text.Text;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Data Export NEW: клавиша делает резервную копию waypoints.json в файл
 * kimiko-backup-<метка времени>.json в конфиг-каталоге.
 */
public class DataExportModule extends Module {
    private final BindSetting bind;
    private boolean busy;
    private boolean lastDown;

    public DataExportModule() {
        super("Data Export NEW", "Клавиша сохраняет резервную копию waypoints.json с меткой времени.", Category.UTILS);
        this.bind = (BindSetting) this.register((Setting) new BindSetting("Клавиша", "Клавиша создания резервной копии.").setKey(-1));
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
            Thread worker = new Thread(this::exportBackup, "Kimiko-DataExport");
            worker.setDaemon(true);
            worker.start();
        }
        this.lastDown = down;
    }

    private void exportBackup() {
        try {
            Path source = ConfigManager.Companion.systemConfigDirectory().resolve("waypoints.json");
            if (!Files.exists(source)) {
                this.chat("§7waypoints.json не найден — резервировать нечего.");
                return;
            }
            String content = Files.readString(source, StandardCharsets.UTF_8);
            JsonElement parsed;
            try {
                parsed = JsonParser.parseString(content);
            } catch (Throwable t) {
                this.chat("§cwaypoints.json повреждён — копия не создана.");
                return;
            }

            String stamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            String name = "kimiko-backup-" + stamp + ".json";
            Path target = ConfigManager.Companion.systemConfigDirectory().resolve(name);

            JsonObject out = new JsonObject();
            out.addProperty("created", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            out.addProperty("source", "waypoints.json");
            out.add("content", parsed);
            Files.writeString(target, out.toString(), StandardCharsets.UTF_8);

            this.chat("§aРезервная копия сохранена: §f" + name);
        } catch (Throwable t) {
            this.chat("§cНе удалось создать резервную копию: §7" + t.getMessage());
        } finally {
            this.busy = false;
        }
    }

    private void chat(String s) {
        if (this.mc.player != null) {
            this.mc.player.sendMessage(Text.literal(s), false);
        }
    }
}
