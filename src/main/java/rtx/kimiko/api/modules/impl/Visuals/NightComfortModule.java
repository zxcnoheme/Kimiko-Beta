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

import java.time.LocalTime;

/**
 * Ночной фильтр комфорта: поздно вечером и ночью по реальным часам экран
 * мягко подкрашивается тёплым оттенком — глаза меньше напрягаются.
 */
public class NightComfortModule extends Module {
    private final SliderSetting strength;

    public NightComfortModule() {
        super("Night Comfort", "Тёплый фильтр экрана ночью (по реальным часам): меньше напряжения на глаза.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Насколько сильно подкрашивать экран.").setValue(0.5f).range(0.1f, 1.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled()) {
            return;
        }
        LocalTime now = LocalTime.now();
        int hour = now.getHour();
        // окно 22:00–07:00, плавный вход 20:00–22:00 и выход 07:00–09:00
        float factor;
        if (hour >= 22 || hour < 7) {
            factor = 1.0f;
        } else if (hour >= 20) {
            factor = (hour - 20) / 2.0f;
        } else if (hour < 9) {
            factor = 1.0f - (hour - 7) / 2.0f;
        } else {
            return;
        }
        float alpha = factor * this.strength.getValue() * 0.10f;
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        int w = this.mc.getWindow().getScaledWidth();
        int h = this.mc.getWindow().getScaledHeight();
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        Render2D.rect(0.0f, 0.0f, w / sf, h / sf, 0.0f,
                MenuColors.rgba(255, 166, 88, (int) (255 * alpha)));
        Render2D.flush();
    }
}
