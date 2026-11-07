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
 * Дымка низкого HP: когда сердец мало — красная пульсирующая дымка
 * по краям экрана, пульс учащается с падением здоровья.
 */
public class LowHealthHazeModule extends Module {
    private final SliderSetting threshold;
    private final SliderSetting strength;

    public LowHealthHazeModule() {
        super("Low HP Haze NEW", "Красная пульсирующая дымка по краям при низком здоровье — как сердцебиение.", Category.VISUALS);
        this.threshold = (SliderSetting) this.register((Setting) new SliderSetting("Порог", "Доля здоровья, ниже которой начинается дымка.").setValue(0.35f).range(0.1f, 0.6f));
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Яркость дымки.").setValue(0.7f).range(0.2f, 1.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null || this.mc.player.isDead()) {
            return;
        }
        float max = this.mc.player.getMaxHealth();
        if (max <= 0.0f) {
            return;
        }
        float frac = this.mc.player.getHealth() / max;
        float threshold = this.threshold.getValue();
        if (frac >= threshold) {
            return;
        }
        float severity = 1.0f - frac / threshold;
        float bpm = 60.0f + severity * 70.0f;
        float beat = 0.5f + 0.5f * (float) Math.sin(System.currentTimeMillis() * Math.PI * 2.0 * (bpm / 60.0f) / 1000.0);
        float alpha = (0.35f + 0.65f * severity) * beat * this.strength.getValue() * 0.24f;

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f,
                MenuColors.rgba(200, 20, 20, (int) (220 * alpha)),
                MenuColors.rgba(200, 20, 20, (int) (220 * alpha)),
                MenuColors.rgba(140, 0, 0, (int) (255 * alpha)),
                MenuColors.rgba(140, 0, 0, (int) (255 * alpha)));
        Render2D.flush();
    }
}
