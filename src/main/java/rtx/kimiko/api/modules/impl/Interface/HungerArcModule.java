package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Дуга сытости: тонкая дуга под прицелом, цвет меняется от полноты.
 */
public class HungerArcModule extends Module {
    public HungerArcModule() {
        super("Hunger Arc NEW", "Тонкая дуга сытости под прицелом: зелёная → жёлтая → красная.", Category.DISPLAY);
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null) {
            return;
        }
        int food = this.mc.player.getHungerManager().getFoodLevel();
        float frac = Math.max(0.0f, Math.min(1.0f, food / 20.0f));
        if (frac >= 0.999f) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        int color;
        if (frac > 0.6f) {
            color = MenuColors.rgba(120, 220, 110, 220);
        } else if (frac > 0.3f) {
            color = MenuColors.rgba(255, 200, 80, 230);
        } else {
            color = MenuColors.rgba(255, 80, 80, 240);
        }
        Render2D.arcRect(w * 0.5f, h * 0.5f + 20.0f, 12.0f, 1.8f, 90.0f, 180.0f * frac, color);
        Render2D.arcRect(w * 0.5f, h * 0.5f + 20.0f, 12.0f, 1.8f, 90.0f + 180.0f * frac, 180.0f * (1.0f - frac),
                MenuColors.rgba(255, 255, 255, 25));
        Render2D.flush();
    }
}
