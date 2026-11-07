package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.consume.UseAction;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Дуга натяжки лука: у прицела растёт дуга заряда с отметкой
 * полного натяжения.
 */
public class BowChargeArcModule extends Module {
    public BowChargeArcModule() {
        super("Bow Charge NEW", "Дуга натяжки лука у прицела с отметкой полного заряда.", Category.DISPLAY);
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null || !this.mc.player.isUsingItem()) {
            return;
        }
        if (this.mc.player.getActiveItem().getUseAction() != UseAction.BOW) {
            return;
        }
        int ticks = this.mc.player.getItemUseTime();
        float charge = Math.max(0.0f, Math.min(1.0f, ticks / 20.0f));
        if (charge <= 0.02f) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        boolean full = charge >= 1.0f;
        int color = full ? MenuColors.rgba(140, 255, 120, 250) : MenuColors.rgba(255, 210, 100, 235);
        Render2D.arcRect(w * 0.5f, h * 0.5f, 13.0f, 2.0f, -90.0f, 360.0f * charge, color);
        Render2D.arcRect(w * 0.5f, h * 0.5f, 13.0f, 2.0f, -90.0f + 360.0f * charge, 360.0f * (1.0f - charge),
                MenuColors.rgba(255, 255, 255, 25));
        if (full) {
            Render2D.arcRect(w * 0.5f, h * 0.5f, 16.0f, 1.0f, -90.0f, 360.0f,
                    MenuColors.rgba(140, 255, 120, (int) (120 * (0.5f + 0.5f * (float) Math.sin(System.currentTimeMillis() * 0.01)))));
        }
        Render2D.flush();
    }
}
