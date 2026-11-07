package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Сон-затемнение: когда укладываешься спать — экран плавно гаснет,
 * при пробуждении так же плавно возвращается.
 */
public class SleepFadeModule extends Module {
    private float fade;

    public SleepFadeModule() {
        super("Sleep Fade NEW", "Плавное затемнение экрана при укладывании спать и мягкое пробуждение.", Category.VISUALS);
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (this.mc.player == null) {
            this.fade = 0.0f;
            return;
        }
        float target = this.mc.player.isSleeping() ? 1.0f : 0.0f;
        this.fade += (target - this.fade) * 0.055f;
        if (this.fade <= 0.01f) {
            return;
        }
        float alpha = Math.min(0.88f, this.fade * 0.9f);
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f, MenuColors.rgba(4, 5, 10, (int) (255 * alpha)));
        Render2D.flush();
    }
}
