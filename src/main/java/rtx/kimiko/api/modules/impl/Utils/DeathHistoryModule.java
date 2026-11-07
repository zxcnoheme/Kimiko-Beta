package rtx.kimiko.api.modules.impl.Utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Death History NEW: запоминает смерти (время, координаты, измерение) в памяти
 * и в deaths.json; клавиша выводит последние смерти в чат.
 */
public class DeathHistoryModule extends Module {
    private static final int MAX_ENTRIES = 50;

    private final BindSetting showBind;
    private final SliderSetting maxShow;
    private final List<Entry> deaths = new ArrayList<>();
    private boolean deathMarked;
    private boolean loaded;
    private boolean lastShowDown;

    public DeathHistoryModule() {
        super("Death History NEW", "Запоминает твои смерти и показывает последние по клавише.", Category.UTILS);
        this.showBind = (BindSetting) this.register((Setting) new BindSetting("Клавиша", "Клавиша показа последних смертей.").setKey(-1));
        this.maxShow = (SliderSetting) this.register((Setting) new SliderSetting("Сколько показывать", "Число строк в чате.").setValue(5.0f).range(1.0f, 10.0f));
        this.deathMarked = false;
        this.loaded = false;
    }

    @Override
    protected void onEnable() {
        this.load();
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled() || this.mc.player == null) {
            return;
        }

        // детект смерти
        if (this.mc.player.isDead() && !this.deathMarked) {
            this.deathMarked = true;
            this.record();
        } else if (!this.mc.player.isDead()) {
            this.deathMarked = false;
        }

        // показ по клавише
        boolean down = this.showBind.getValue().isDown(this.mc.getWindow().getHandle());
        if (down && !this.lastShowDown) {
            this.showRecent();
        }
        this.lastShowDown = down;
    }

    private void record() {
        Entry e = new Entry();
        e.time = System.currentTimeMillis();
        e.x = MathHelper.floor(this.mc.player.getX()) + 0.5;
        e.y = MathHelper.floor(this.mc.player.getY());
        e.z = MathHelper.floor(this.mc.player.getZ()) + 0.5;
        e.dimension = this.mc.world != null ? this.mc.world.getRegistryKey().getValue().toString() : "unknown";
        if (!this.deaths.isEmpty()) {
            Entry last = this.deaths.get(this.deaths.size() - 1);
            if (e.time - last.time < 8000L
                    && Math.abs(e.x - last.x) < 1.5 && Math.abs(e.z - last.z) < 1.5) {
                return; // дубликат той же смерти
            }
        }
        this.deaths.add(e);
        while (this.deaths.size() > MAX_ENTRIES) {
            this.deaths.remove(0);
        }
        this.save();
        this.chat("§7Смерть записана: §f" + (int) e.x + " " + (int) e.y + " " + (int) e.z);
    }

    private void showRecent() {
        if (this.deaths.isEmpty()) {
            this.chat("§7Смертей пока не было.");
            return;
        }
        int count = Math.min(this.deaths.size(), Math.max(1, this.maxShow.getInt()));
        this.chat("§eПоследние смерти:");
        int shown = 1;
        for (int i = this.deaths.size() - 1; i >= 0 && shown <= count; i--, shown++) {
            Entry e = this.deaths.get(i);
            this.chat(" §f" + shown + ". " + this.formatEntry(e));
        }
    }

    private String formatEntry(Entry e) {
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date(e.time));
        String dim = e.dimension.startsWith("minecraft:") ? e.dimension.substring("minecraft:".length()) : e.dimension;
        return "§7" + time + " §f" + (int) e.x + " " + (int) e.y + " " + (int) e.z + " §8" + dim;
    }

    private void chat(String s) {
        if (this.mc.player != null) {
            this.mc.player.sendMessage(Text.literal(s), false);
        }
    }

    // ——— хранение

    private static final class Entry {
        long time;
        double x;
        double y;
        double z;
        String dimension;
    }

    private Path file() {
        return ConfigManager.Companion.systemConfigDirectory().resolve("deaths.json");
    }

    private void load() {
        if (this.loaded) {
            return;
        }
        this.loaded = true;
        try {
            Path path = this.file();
            if (!Files.exists(path)) {
                return;
            }
            JsonObject root = JsonParser.parseString(Files.readString(path, StandardCharsets.UTF_8)).getAsJsonObject();
            if (!root.has("deaths")) {
                return;
            }
            this.deaths.clear();
            for (JsonElement el : root.getAsJsonArray("deaths")) {
                try {
                    JsonObject o = el.getAsJsonObject();
                    Entry e = new Entry();
                    e.time = o.get("time").getAsLong();
                    e.x = o.get("x").getAsDouble();
                    e.y = o.get("y").getAsDouble();
                    e.z = o.get("z").getAsDouble();
                    e.dimension = o.has("dimension") ? o.get("dimension").getAsString() : "unknown";
                    this.deaths.add(e);
                } catch (Throwable ignored) {
                }
            }
        } catch (Throwable t) {
            System.out.println("[KIMIKO] DeathHistory: не удалось загрузить deaths.json (" + t + ")");
        }
    }

    private void save() {
        try {
            JsonObject root = new JsonObject();
            JsonArray arr = new JsonArray();
            for (Entry e : this.deaths) {
                JsonObject o = new JsonObject();
                o.addProperty("time", e.time);
                o.addProperty("x", e.x);
                o.addProperty("y", e.y);
                o.addProperty("z", e.z);
                o.addProperty("dimension", e.dimension);
                arr.add(o);
            }
            root.add("deaths", arr);
            Path path = this.file();
            Files.createDirectories(path.getParent());
            Files.writeString(path, root.toString(), StandardCharsets.UTF_8);
        } catch (Throwable t) {
            System.out.println("[KIMIKO] DeathHistory: не удалось сохранить deaths.json (" + t + ")");
        }
    }
}
