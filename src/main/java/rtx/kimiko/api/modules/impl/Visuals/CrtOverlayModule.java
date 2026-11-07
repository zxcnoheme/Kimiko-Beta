package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * CRT Overlay: стилизация экрана под старый телевизор — горизонтальные
 * сканлайны развёртки и мягкая виньетка по краям кадра.
 */
public class CrtOverlayModule extends Module {
    private final BooleanSetting scanlines;
    private final BooleanSetting vignette;
    private final SliderSetting strength;

    public CrtOverlayModule() {
        super("CRT Overlay NEW", "Стилизация под старый телевизор: сканлайны и виньетка по краям экрана.", Category.VISUALS);
        this.scanlines = (BooleanSetting) this.register((Setting) new BooleanSetting("Сканлайны", "Горизонтальные линии развёртки.", true));
        this.vignette = (BooleanSetting) this.register((Setting) new BooleanSetting("Виньетка", "Затемнение по краям кадра.", true));
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Общая интенсивность эффекта.").setValue(1.0f).range(0.2f, 2.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled()) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;

        if (this.vignette.getValue()) {
            this.drawVignette(w, h);
        }
        if (this.scanlines.getValue()) {
            int color = MenuColors.rgba(0, 0, 0, (int) (26.0f * this.strength.getValue()));
            for (int i = 0, y = 0; i < 200 && y < (int) h; i++, y += 3) {
                Render2D.rect(0.0f, (float) y, w, 1.0f, color);
            }
        }
        Render2D.flush();
    }

    /** Четырёхсторонний градиент: тёмные края, прозрачный центр. */
    private void drawVignette(float w, float h) {
        int dark = MenuColors.rgba(0, 0, 0, (int) (70.0f * this.strength.getValue()));
        int clear = MenuColors.rgba(0, 0, 0, 0);
        float side = w * 0.22f;
        float vert = h * 0.26f;
        Render2D.rect(0.0f, 0.0f, w, vert, 0.0f, dark, dark, clear, clear);
        Render2D.rect(0.0f, h - vert, w, vert, 0.0f, clear, clear, dark, dark);
        Render2D.rect(0.0f, 0.0f, side, h, 0.0f, dark, clear, clear, dark);
        Render2D.rect(w - side, 0.0f, side, h, 0.0f, clear, dark, dark, clear);
    }
}
