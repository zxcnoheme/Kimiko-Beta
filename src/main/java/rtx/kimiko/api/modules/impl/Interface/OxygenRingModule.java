package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Кольцо кислорода: вместо пузырьков — дуга вокруг прицела,
 * сжимается с запасом воздуха, мигает на последней трети.
 */
public class OxygenRingModule extends Module {
    public OxygenRingModule() {
        super("Oxygen Ring NEW", "Дуга кислорода вокруг прицела вместо пузырьков.", Category.DISPLAY);
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null) {
            return;
        }
        int air = this.mc.player.getAir();
        if (air >= 300) {
            return;
        }
        float frac = MathHelper(air / 300.0f);
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        float cx = w * 0.5f;
        float cy = h * 0.5f;
        int color;
        if (frac > 0.33f) {
            color = MenuColors.rgba(120, 190, 255, 230);
        } else {
            boolean blink = (System.currentTimeMillis() / 250L) % 2L == 0L;
            color = blink ? MenuColors.rgba(255, 90, 90, 240) : MenuColors.rgba(255, 90, 90, 120);
        }
        Render2D.arcRect(cx, cy, 16.0f, 2.2f, -90.0f, 360.0f * frac, color);
        Render2D.arcRect(cx, cy, 16.0f, 2.2f, -90.0f + 360.0f * frac, 360.0f * (1.0f - frac),
                MenuColors.rgba(255, 255, 255, 30));
        Render2D.flush();
    }

    private static float MathHelper(float v) {
        return Math.max(0.0f, Math.min(1.0f, v));
    }
}
