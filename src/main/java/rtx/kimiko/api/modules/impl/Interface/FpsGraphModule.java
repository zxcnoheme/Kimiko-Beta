package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * График FPS: микрограф последних секунд с текущим значением.
 */
public class FpsGraphModule extends Module {
    private static final int BARS = 48;

    private final SliderSetting scale;
    private final float[] history = new float[BARS];
    private int cursor;

    public FpsGraphModule() {
        super("FPS Graph NEW", "Микрограф FPS за последние секунды.", Category.DISPLAY);
        this.scale = (SliderSetting) this.register((Setting) new SliderSetting("Масштаб", "Верхняя граница графика (FPS).").setValue(240.0f).range(60.0f, 500.0f));
        this.cursor = 0;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled()) {
            return;
        }
        float fps = this.mc.getCurrentFps();
        this.history[this.cursor % BARS] = Math.min(fps, this.scale.getValue());
        this.cursor++;

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float barW = 2.2f;
        float graphW = BARS * (barW + 0.8f);
        float graphH = 22.0f;
        float x = w - graphW - 14.0f;
        float y = 30.0f;

        for (int i = 0; i < BARS; i++) {
            float v = this.history[(this.cursor + i) % BARS];
            if (v <= 0.0f) {
                continue;
            }
            float bh = Math.max(1.0f, v / this.scale.getValue() * graphH);
            int color = v >= this.scale.getValue() * 0.5f ? MenuColors.rgba(110, 220, 130, 200)
                    : v >= this.scale.getValue() * 0.25f ? MenuColors.rgba(255, 200, 90, 210)
                    : MenuColors.rgba(255, 90, 90, 220);
            Render2D.rect(x + i * (barW + 0.8f), y + graphH - bh, barW, bh, 1.0f, color);
        }
        String label = fps + " FPS";
        Fonts.SEMIBOLD.draw(label, x, y + graphH + 3.0f, 7.5f, MenuColors.rgba(235, 240, 250, 200));
        Render2D.flush();
    }
}
