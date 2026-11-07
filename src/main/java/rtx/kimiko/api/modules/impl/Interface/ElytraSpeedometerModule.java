package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Тахометр элитр: скорость полёта в м/с с полосой-индикатором.
 */
public class ElytraSpeedometerModule extends Module {
    public ElytraSpeedometerModule() {
        super("Elytra Speedo NEW", "Скорость полёта на элитрах в м/с с полосой.", Category.DISPLAY);
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null || !this.mc.player.isGliding()) {
            return;
        }
        double speed = this.mc.player.getVelocity().horizontalLength() * 20.0; // м/с
        float frac = (float) Math.min(1.0, speed / 33.0);
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        float barW = 120.0f;
        float x = w * 0.5f - barW * 0.5f;
        float y = h - 90.0f;
        Render2D.rect(x, y, barW, 4.0f, 2.0f, MenuColors.rgba(255, 255, 255, 25));
        int color = frac > 0.75f ? MenuColors.rgba(140, 255, 130, 235)
                : frac > 0.4f ? MenuColors.rgba(140, 210, 255, 235)
                : MenuColors.rgba(255, 255, 255, 200);
        Render2D.rect(x, y, barW * frac, 4.0f, 2.0f, color);
        String label = String.format("%.1f м/с", speed);
        float tw = Fonts.SEMIBOLD.width(label, 8.0f);
        Fonts.SEMIBOLD.draw(label, w * 0.5f - tw * 0.5f, y + 7.0f, 8.0f, MenuColors.rgba(235, 240, 250, 220));
        Render2D.flush();
    }
}
