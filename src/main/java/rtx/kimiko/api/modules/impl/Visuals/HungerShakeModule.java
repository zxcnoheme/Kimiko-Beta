package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Дрожь голода: когда сытость падает до 2 и ниже — тёмные полосы по
 * краям экрана мелко дрожат, подчёркивая голод.
 */
public class HungerShakeModule extends Module {
    private final SliderSetting strength;

    public HungerShakeModule() {
        super("Hunger Shake NEW", "Тёмные полосы по краям экрана дрожат при низком голоде.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Размах дрожи (в пикселях).").setValue(2.0f).range(1.0f, 4.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null || this.mc.world == null) {
            return;
        }
        if (this.mc.player.getHungerManager().getFoodLevel() > 2) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        float amp = this.strength.getValue();
        int color = (110 << 24) | 0x0A0A0A;
        // четыре угловые полосы со случайным смещением каждый кадр
        this.shakenBar(8.0f, 8.0f, 56.0f, 6.0f, amp, color);
        this.shakenBar(w - 64.0f, 8.0f, 56.0f, 6.0f, amp, color);
        this.shakenBar(8.0f, h - 14.0f, 56.0f, 6.0f, amp, color);
        this.shakenBar(w - 64.0f, h - 14.0f, 56.0f, 6.0f, amp, color);
        Render2D.flush();
    }

    private void shakenBar(float x, float y, float width, float height, float amp, int color) {
        float jx = (float) (Math.random() * 2.0 - 1.0) * amp;
        float jy = (float) (Math.random() * 2.0 - 1.0) * amp;
        Render2D.rect(x + jx, y + jy, width, height, 2.0f, color);
    }
}
