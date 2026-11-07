package rtx.kimiko.api.modules.impl.Interface;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Счётчик дней: сколько дней ты с Kimiko — маленькая плашка
 * на несколько секунд после захода в мир.
 */
public class DaysCounterModule extends Module {
    private static final String FILE_NAME = "kimiko_days.json";
    private static final long SHOW_MS = 5000L;
    private static final Gson GSON = new Gson();

    private boolean worldWasNull = true;
    private long showUntil;
    private int days;

    public DaysCounterModule() {
        super("Days Counter NEW", "Плашка «Ты с Kimiko N дн.» после захода в мир.", Category.DISPLAY);
        this.showUntil = 0L;
        this.days = 0;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (this.mc.world == null) {
            this.worldWasNull = true;
            return;
        }
        if (this.worldWasNull) {
            this.worldWasNull = false;
            this.onWorldJoined();
        }
        if (!this.isEnabled() || this.showUntil == 0L || this.days < 1) {
            return;
        }
        if (System.currentTimeMillis() > this.showUntil) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        String label = "Ты с Kimiko " + this.days + " дн.";
        float textW = Fonts.SEMIBOLD.width(label, 8.0f);
        float x = w - textW - 16.0f;
        float y = 44.0f;
        Render2D.rect(x - 7.0f, y - 3.0f, textW + 14.0f, 14.0f, 7.0f, MenuColors.rgba(9, 11, 17, 165));
        Fonts.SEMIBOLD.draw(label, x, y, 8.0f, MenuColors.rgba(235, 240, 250, 220));
        Render2D.flush();
    }

    private void onWorldJoined() {
        try {
            LocalDate first = this.loadOrCreateFirstLaunch();
            if (first != null) {
                long between = ChronoUnit.DAYS.between(first, LocalDate.now());
                if (between >= 1) {
                    this.days = (int) Math.min(between, 100000L);
                    this.showUntil = System.currentTimeMillis() + SHOW_MS;
                }
            }
        } catch (Throwable ignored) {
        }
    }

    /** Читает дату первого запуска или создаёт файл с сегодняшней. */
    private LocalDate loadOrCreateFirstLaunch() {
        Path file = ConfigManager.userConfigDirectory().resolve(FILE_NAME);
        try {
            if (Files.exists(file)) {
                String text = Files.readString(file, StandardCharsets.UTF_8);
                JsonObject root = JsonParser.parseString(text).getAsJsonObject();
                if (root.has("firstLaunch")) {
                    return LocalDate.parse(root.get("firstLaunch").getAsString());
                }
            }
            JsonObject root = new JsonObject();
            root.addProperty("firstLaunch", LocalDate.now().toString());
            try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
                writer.write(GSON.toJson(root));
            }
        } catch (Throwable ignored) {
            return null;
        }
        return LocalDate.now();
    }
}
