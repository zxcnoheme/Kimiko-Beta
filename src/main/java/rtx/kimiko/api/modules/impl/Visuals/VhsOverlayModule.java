package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.world.ClientWorld;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

import java.util.Random;

/**
 * VHS Overlay: стиль видеозаписи — мигающая красная точка REC,
 * таймер записи в правом верхнем углу и лёгкий аналоговый шум.
 */
public class VhsOverlayModule extends Module {
    private final BooleanSetting showRec;
    private final BooleanSetting noise;
    private long recStart;

    public VhsOverlayModule() {
        super("VHS Overlay NEW", "Стиль видеозаписи: точка REC, таймер и лёгкий шум «плёнки».", Category.VISUALS);
        this.showRec = (BooleanSetting) this.register((Setting) new BooleanSetting("Показывать REC", "Точка REC и таймер записи в углу.", true));
        this.noise = (BooleanSetting) this.register((Setting) new BooleanSetting("Шум", "Случайные помехи на кадре.", true));
        this.recStart = 0L;
    }

    @Override
    protected void onEnable() {
        this.recStart = System.currentTimeMillis();
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.recStart == 0L) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;

        if (this.noise.getValue()) {
            this.drawNoise(w, h);
        }
        if (this.showRec.getValue()) {
            this.drawRec(w);
        }
        Render2D.flush();
    }

    /** Короткие белёсые полоски-помехи: сид по тику, чтобы «дрожали» на 20 тпс. */
    private void drawNoise(float w, float h) {
        ClientWorld world = this.mc.world;
        long seed = world != null ? world.getTime() : System.currentTimeMillis() / 50L;
        Random random = new Random(seed);
        for (int i = 0; i < 30; i++) {
            float y = random.nextFloat() * h;
            float x = random.nextFloat() * w * 0.8f;
            float len = 18.0f + random.nextFloat() * 90.0f;
            int a = 8 + random.nextInt(7);
            Render2D.rect(x, y, len, 1.0f, MenuColors.rgba(255, 255, 255, a));
        }
    }

    /** Точка REC + надпись + таймер мм:сс от момента включения модуля. */
    private void drawRec(float w) {
        long now = System.currentTimeMillis();
        long elapsed = Math.max(0L, (now - this.recStart) / 1000L);
        String timer = String.format("%02d:%02d", elapsed / 60L, elapsed % 60L);
        float labelSize = 9.0f;
        float timerSize = 8.0f;
        float blockW = 9.0f + Math.max(Fonts.SEMIBOLD.width("REC", labelSize), Fonts.SEMIBOLD.width(timer, timerSize));
        float x = w - 12.0f - blockW;
        float y = 12.0f;
        boolean on = (now - this.recStart) % 1200L < 600L;
        Render2D.circle(x + 3.0f, y + 4.5f, 3.0f, MenuColors.rgba(255, 45, 45, on ? 235 : 60));
        Fonts.SEMIBOLD.draw("REC", x + 9.0f, y, labelSize, MenuColors.rgba(255, 240, 240, 230));
        Fonts.SEMIBOLD.draw(timer, x + 9.0f, y + 12.0f, timerSize, MenuColors.rgba(255, 255, 255, 185));
    }
}
