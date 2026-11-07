package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Тон грозы: когда за окном настоящая гроза — экран слегка
 * подкрашивается серо-синим, передавая тяжёлое небо.
 */
public class StormTintModule extends Module {
    private final SliderSetting strength;

    public StormTintModule() {
        super("Storm Tint NEW", "Серо-синий тон экрана во время грозы.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Насколько сильно подкрашивать экран.").setValue(0.6f).range(0.1f, 1.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.world == null || this.mc.player == null) {
            return;
        }
        float thunder = this.mc.world.getThunderGradient(1.0f);
        if (thunder <= 0.5f) {
            return;
        }
        int alpha = Math.max(3, (int) (thunder * 22.0f * this.strength.getValue()));

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f,
                MenuColors.rgba(40, 50, 70, alpha));
        Render2D.flush();
    }
}
