package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Взгляд эндермена: смотришь на эндермена — по краям экрана пульсирует
 * пурпурная виньетка, сильнее когда он ближе.
 */
public class EndermanStareModule extends Module {
    private static final double RANGE = 24.0;
    private static final double COS_5 = 0.9962;

    private final SliderSetting strength;
    private float proximity;

    public EndermanStareModule() {
        super("Enderman Stare NEW", "Пурпурная виньетка, когда смотришь на эндермена.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Яркость виньетки.").setValue(0.6f).range(0.2f, 1.0f));
        this.proximity = 0.0f;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled() || this.mc.player == null || this.mc.world == null) {
            this.proximity = 0.0f;
            return;
        }
        Vec3d look = this.mc.player.getRotationVec(1.0f);
        Vec3d eye = this.mc.player.getEyePos();
        float best = Float.MAX_VALUE;
        for (Entity e : this.mc.world.getEntities()) {
            if (!(e instanceof EndermanEntity enderman) || !enderman.isAlive()) {
                continue;
            }
            Vec3d to = enderman.getEyePos().subtract(eye);
            double dist = to.length();
            if (dist > RANGE || dist < 0.5) {
                continue;
            }
            if (to.normalize().dotProduct(look) < COS_5) {
                continue;
            }
            best = Math.min(best, (float) dist);
        }
        this.proximity = best == Float.MAX_VALUE ? 0.0f : 1.0f - best / (float) RANGE;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.proximity <= 0.0f) {
            return;
        }
        float pulse = 0.7f + 0.3f * (float) Math.sin(System.currentTimeMillis() * 0.004);
        float alpha = this.proximity * pulse * this.strength.getValue() * 0.20f;

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        // пурпурная виньетка: края ярко, центр прозрачно
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f,
                MenuColors.rgba(170, 70, 220, (int) (255 * alpha)),
                MenuColors.rgba(170, 70, 220, (int) (255 * alpha)),
                MenuColors.rgba(110, 30, 170, (int) (255 * alpha * 0.25f)),
                MenuColors.rgba(110, 30, 170, (int) (255 * alpha * 0.25f)));
        Render2D.flush();
    }
}
