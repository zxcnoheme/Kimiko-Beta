package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
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
 * Виньетка крипера: крипер рядом — экран тревожно пульсирует белым,
 * чем ближе крипер, тем быстрее и ярче пульс.
 */
public class CreeperVignetteModule extends Module {
    private static final double RANGE = 4.0;

    private final SliderSetting strength;
    private float proximity;

    public CreeperVignetteModule() {
        super("Creeper Vignette NEW", "Белая пульсирующая виньетка, когда крипер совсем рядом.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Яркость виньетки.").setValue(0.6f).range(0.2f, 1.0f));
        this.proximity = 0.0f;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled() || this.mc.player == null || this.mc.world == null) {
            this.proximity = 0.0f;
            return;
        }
        float best = Float.MAX_VALUE;
        for (Entity e : this.mc.world.getEntities()) {
            if (!(e instanceof CreeperEntity creeper) || !creeper.isAlive()) {
                continue;
            }
            float dist = creeper.distanceTo(this.mc.player);
            if (dist <= RANGE) {
                best = Math.min(best, dist);
            }
        }
        this.proximity = best == Float.MAX_VALUE ? 0.0f : 1.0f - best / (float) RANGE;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.proximity <= 0.0f) {
            return;
        }
        // чем ближе крипер — тем чаще пульс (1.5–7 Гц)
        float freq = 1.5f + this.proximity * 5.5f;
        float beat = 0.5f + 0.5f * (float) Math.sin(System.currentTimeMillis() * Math.PI * 2.0 * (freq / 60.0f) / 1000.0);
        float alpha = (0.25f + 0.75f * beat) * this.proximity * this.strength.getValue() * 0.16f;

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f,
                MenuColors.rgba(255, 255, 255, (int) (255 * alpha)),
                MenuColors.rgba(255, 255, 255, (int) (255 * alpha)),
                MenuColors.rgba(255, 240, 220, (int) (255 * alpha * 0.3f)),
                MenuColors.rgba(255, 240, 220, (int) (255 * alpha * 0.3f)));
        Render2D.flush();
    }
}
