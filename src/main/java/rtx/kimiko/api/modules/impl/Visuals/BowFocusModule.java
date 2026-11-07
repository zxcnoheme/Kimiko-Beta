package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.consume.UseAction;
import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Bow Focus NEW: при натяжке лука по краям экрана нарастает виньетка —
 * 4-сторонний градиент, сила зависит от заряда (полное натяжение = максимум).
 */
public class BowFocusModule extends Module {
    private static final float MAX_ALPHA = 60.0f;

    private final SliderSetting strength;

    public BowFocusModule() {
        super("Bow Focus NEW", "Виньетка по краям экрана при натяжке лука — растёт с зарядом.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Насколько плотной становится виньетка.").setValue(1.0f).range(0.2f, 1.5f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null || !this.mc.player.isUsingItem()) {
            return;
        }
        if (this.mc.player.getActiveItem().getUseAction() != UseAction.BOW) {
            return;
        }
        // заряд: 20 тиков = полное натяжение
        float charge = MathHelper.clamp(this.mc.player.getItemUseTime() / 20.0f, 0.0f, 1.0f);
        if (charge <= 0.03f) {
            return;
        }
        float scale = this.strength.getValue() * charge;

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;

        // альфы сторон (до 60), верх и низ чуть плотнее боков
        float top = Math.min(MAX_ALPHA, MAX_ALPHA * scale);
        float bottom = Math.min(MAX_ALPHA, MAX_ALPHA * 0.85f * scale);
        float side = Math.min(MAX_ALPHA, MAX_ALPHA * 0.7f * scale);

        int cTop = MenuColors.rgba(6, 8, 14, (int) top);
        int cBottom = MenuColors.rgba(6, 8, 14, (int) bottom);
        int cSide = MenuColors.rgba(6, 8, 14, (int) side);
        int cClear = MenuColors.rgba(6, 8, 14, 0);

        float edgeV = h * 0.16f;
        float edgeH = w * 0.10f;

        // верх: цвет у края, прозрачность к центру
        Render2D.rect(0.0f, 0.0f, w, edgeV, 0.0f, cTop, cTop, cClear, cClear);
        // низ
        Render2D.rect(0.0f, h - edgeV, w, edgeV, 0.0f, cClear, cClear, cBottom, cBottom);
        // лево
        Render2D.rect(0.0f, 0.0f, edgeH, h, 0.0f, cSide, cClear, cClear, cSide);
        // право
        Render2D.rect(w - edgeH, 0.0f, edgeH, h, 0.0f, cClear, cSide, cSide, cClear);
        Render2D.flush();
    }
}
