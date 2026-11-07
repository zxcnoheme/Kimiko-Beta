package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.world.ClientWorld;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Золотой час: на закате мир на экране мягко подсвечивается тёплым
 * солнечным оттенком — время вокруг 18:00 по внутриигровым часам.
 */
public class GoldenHourModule extends Module {
    private final SliderSetting strength;

    public GoldenHourModule() {
        super("Golden Hour NEW", "Тёплый солнечный оттенок на закате — время золотого часа.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Насколько сильно подсвечивать закат.").setValue(0.6f).range(0.1f, 1.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null) {
            return;
        }
        float day = world.getTimeOfDay();
        // закатный пик ~0.5 (18:00), рассветный ~0.0 (06:00)
        float sunset = bell(day, 0.5f, 0.055f);
        float sunrise = bell(day, 0.0f, 0.055f) + bell(day, 1.0f, 0.055f);
        float glow = Math.max(sunset, sunrise * 0.8f);
        if (glow <= 0.01f) {
            return;
        }
        float alpha = glow * this.strength.getValue() * 0.085f;
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f,
                MenuColors.rgba(255, 158, 64, (int) (255 * alpha)),
                MenuColors.rgba(255, 158, 64, (int) (255 * alpha)),
                MenuColors.rgba(255, 120, 60, 0),
                MenuColors.rgba(255, 120, 60, 0));
        Render2D.flush();
    }

    /** Колокол: 1 в центре, спад к радиусу. */
    private static float bell(float value, float center, float radius) {
        float d = Math.abs(value - center);
        if (d > radius) {
            return 0.0f;
        }
        float t = 1.0f - d / radius;
        return t * t * (3.0f - 2.0f * t);
    }
}
