package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.player.AttackEntityEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Здоровье цели: ударил моба — над прицелом на секунду всплывает
 * его текущее здоровье с красным сердечком.
 */
public class TargetHealthPopModule extends Module {
    private static final long SHOW_MS = 1000L;
    private static final long POP_MS = 160L;

    private float targetHp;
    private long shownAt;

    public TargetHealthPopModule() {
        super("Target Health NEW", "Показывает здоровье цели у прицела после удара.", Category.DISPLAY);
        this.targetHp = 0.0f;
        this.shownAt = 0L;
    }

    @EventHandler
    private void onAttack(AttackEntityEvent event) {
        if (!this.isEnabled() || event.isSynthetic()) {
            return;
        }
        if (!(event.getTarget() instanceof LivingEntity living)) {
            return;
        }
        if (this.mc.player == null || living == this.mc.player) {
            return;
        }
        this.targetHp = living.getHealth();
        this.shownAt = System.currentTimeMillis();
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null || this.shownAt == 0L) {
            return;
        }
        long age = System.currentTimeMillis() - this.shownAt;
        if (age > SHOW_MS) {
            return;
        }
        // плавное появление: сдвиг вверх + рост непрозрачности
        float pop = Math.min(1.0f, age / (float) POP_MS);
        float ease = 1.0f - (1.0f - pop) * (1.0f - pop);
        int alpha = (int) (235 * ease);
        float lift = (1.0f - ease) * 4.0f;

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float cx = this.mc.getWindow().getScaledWidth() / sf * 0.5f;
        float cy = this.mc.getWindow().getScaledHeight() / sf * 0.5f - 26.0f - lift;

        String label = String.format("HP %.1f", this.targetHp);
        float textW = Fonts.SEMIBOLD.width(label, 8.0f);
        float hx = cx - (textW + 12.0f) * 0.5f;
        // красное сердечко: тело + два «ушка»
        int heart = MenuColors.rgba(235, 60, 80, alpha);
        Render2D.circle(hx + 5.0f, cy + 5.0f, 3.2f, 10, heart);
        Render2D.circle(hx + 3.2f, cy + 3.0f, 2.1f, 8, heart);
        Render2D.circle(hx + 6.8f, cy + 3.0f, 2.1f, 8, heart);
        Fonts.SEMIBOLD.draw(label, hx + 12.0f, cy, 8.0f, MenuColors.rgba(255, 110, 120, alpha));
        Render2D.flush();
    }
}
