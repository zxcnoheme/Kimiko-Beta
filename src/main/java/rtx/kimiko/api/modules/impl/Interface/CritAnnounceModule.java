package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.player.AttackEntityEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.utils.animations.Easings;
import rtx.kimiko.utils.animations.SmoothAnimation;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Крит-анонс: при крит-ударе над прицелом вспыхивает «КРИТ».
 */
public class CritAnnounceModule extends Module {
    private static final long SHOW_MS = 600L;

    private final SmoothAnimation pop = new SmoothAnimation();
    private long showStart;

    public CritAnnounceModule() {
        super("Crit Announce NEW", "Вспышка «КРИТ» над прицелом при крит-ударе.", Category.DISPLAY);
        this.showStart = 0L;
        this.pop.setValue(0.0);
    }

    @EventHandler
    private void onAttack(AttackEntityEvent event) {
        if (!this.isEnabled() || event.isSynthetic() || this.mc.player == null) {
            return;
        }
        // эвристика крита: в воздухе/падаем без земли
        if (this.mc.player.fallDistance > 0.4f && !this.mc.player.isOnGround()
                && !this.mc.player.isClimbing() && !this.mc.player.isTouchingWater()) {
            this.showStart = System.currentTimeMillis();
            this.pop.setValue(0.0);
            this.pop.run(1.0, 0.18, Easings.QUAD_OUT, false);
        }
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (this.showStart == 0L || this.mc.player == null) {
            return;
        }
        long age = System.currentTimeMillis() - this.showStart;
        if (age > SHOW_MS) {
            return;
        }
        this.pop.update();
        float pop = (float) this.pop.getValue();
        float fade = 1.0f - (float) age / (float) SHOW_MS;
        float alpha = pop * fade;
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        String label = "КРИТ!";
        float size = 11.0f * (0.8f + 0.2f * pop);
        float tw = Fonts.EXTRABOLD.width(label, size);
        float y = h * 0.5f - 30.0f - (1.0f - pop) * 6.0f;
        Fonts.EXTRABOLD.draw(label, w * 0.5f - tw * 0.5f, y, size,
                MenuColors.rgba(255, 190, 80, (int) (255 * alpha)));
        Render2D.flush();
    }
}
