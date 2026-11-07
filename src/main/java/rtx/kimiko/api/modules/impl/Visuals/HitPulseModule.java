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
 * Пульс урона: при каждом полученном ударе от краёв экрана расходится
 * мягкая красная волна — сразу понятно, что тебя зацепили.
 */
public class HitPulseModule extends Module {
    private static final long PULSE_MS = 450L;

    private final SliderSetting strength;
    private long pulseStart;
    private int lastHurtTime;

    public HitPulseModule() {
        super("Hit Pulse NEW", "Красная волна от краёв экрана при каждом полученном ударе.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Яркость волны.").setValue(0.7f).range(0.2f, 1.0f));
        this.pulseStart = 0L;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (this.mc.player == null) {
            return;
        }
        int hurt = this.mc.player.hurtTime;
        if (hurt > this.lastHurtTime) {
            this.pulseStart = System.currentTimeMillis();
        }
        this.lastHurtTime = hurt;
        if (!this.isEnabled() || this.pulseStart == 0L) {
            return;
        }
        long age = System.currentTimeMillis() - this.pulseStart;
        if (age > PULSE_MS) {
            return;
        }
        float t = (float) age / (float) PULSE_MS;
        float wave = (1.0f - t) * (1.0f - t);
        float alpha = wave * this.strength.getValue() * 0.32f;

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        float grow = t * 0.10f;
        float ix = w * grow;
        float iy = h * grow;
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f,
                MenuColors.rgba(255, 40, 40, 0),
                MenuColors.rgba(255, 40, 40, 0),
                MenuColors.rgba(180, 10, 10, (int) (255 * alpha)),
                MenuColors.rgba(180, 10, 10, (int) (255 * alpha)));
        // внутренняя чистая зона
        Render2D.rect(ix, iy, w - ix * 2.0f, h - iy * 2.0f, 0.0f,
                MenuColors.rgba(255, 40, 40, 0));
        Render2D.flush();
    }
}
