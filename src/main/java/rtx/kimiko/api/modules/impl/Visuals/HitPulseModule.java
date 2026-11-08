package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Пульс урона: при получении удара от краёв экрана расходится цветная
 * волна. Цвет, длительность и форма настраиваются.
 */
public class HitPulseModule extends Module {
    private final ColorSetting color;
    private final SliderSetting strength;
    private final SliderSetting duration;
    private final ModeSetting shape;

    private long pulseStart;
    private int lastHurtTime;

    public HitPulseModule() {
        super("Hit Pulse NEW", "Цветная волна от краёв экрана при получении удара — цвет и длительность настраиваются.", Category.VISUALS);
        this.color = (ColorSetting) this.register((Setting) new ColorSetting("Цвет", "Цвет волны.").value(0xFFFF3B3B));
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Яркость волны.").setValue(0.7f).range(0.2f, 1.0f));
        this.duration = (SliderSetting) this.register((Setting) new SliderSetting("Длительность", "Сколько миллисекунд живёт волна.").setValue(450.0f).range(200.0f, 1000.0f));
        this.shape = (ModeSetting) this.register((Setting) new ModeSetting("Форма", "Волна от краёв или сплошная вспышка.", "Волна", "Волна", "Вспышка"));
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
        long lifeMs = (long) this.duration.getValue();
        long age = System.currentTimeMillis() - this.pulseStart;
        if (age > lifeMs) {
            return;
        }
        float t = (float) age / (float) lifeMs;
        float wave = (1.0f - t) * (1.0f - t);
        float alpha = wave * this.strength.getValue() * 0.32f;
        int tint = this.color.getColor() & 0xFFFFFF;
        boolean flash = this.shape.is("Вспышка");

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;

        if (flash) {
            Render2D.rect(0.0f, 0.0f, w, h, 0.0f,
                    (((int) (Math.min(1.0f, alpha * 1.4f) * 255.0f)) << 24) | tint);
        } else {
            float grow = t * 0.10f;
            float ix = w * grow;
            float iy = h * grow;
            Render2D.rect(0.0f, 0.0f, w, h, 0.0f,
                    MenuColors.withAlpha(tint, 0),
                    MenuColors.withAlpha(tint, 0),
                    MenuColors.withAlpha(tint, (int) (Math.min(1.0f, alpha * 1.2f) * 255.0f)),
                    MenuColors.withAlpha(tint, (int) (Math.min(1.0f, alpha * 1.2f) * 255.0f)));
            Render2D.rect(ix, iy, w - ix * 2.0f, h - iy * 2.0f, 0.0f,
                    MenuColors.withAlpha(tint, 0));
        }
        Render2D.flush();
    }
}
