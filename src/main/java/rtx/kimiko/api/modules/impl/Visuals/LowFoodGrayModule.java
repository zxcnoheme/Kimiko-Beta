package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Серость голода: еды почти нет — экран слегка обесцвечивается
 * и по краям ложится лёгкая тень.
 */
public class LowFoodGrayModule extends Module {
    private final SliderSetting strength;

    public LowFoodGrayModule() {
        super("Hunger Gray NEW", "Экран слегка серееет, когда шкала голода почти пуста.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Насколько сильно гасить цвета.").setValue(0.6f).range(0.2f, 1.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null || this.mc.player.isDead()) {
            return;
        }
        int food = this.mc.player.getHungerManager().getFoodLevel();
        if (food > 6) {
            return;
        }
        float severity = (6 - food) / 6.0f;
        int alpha = (int) (severity * 35.0f * this.strength.getValue());
        if (alpha < 2) {
            return;
        }

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        // серый слой-обесцвечивание
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f, MenuColors.rgba(128, 128, 128, alpha));
        // тонкая тёмная виньетка по краям
        int edge = (int) (alpha * 0.9f);
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f,
                MenuColors.rgba(40, 40, 45, edge),
                MenuColors.rgba(40, 40, 45, edge),
                MenuColors.rgba(25, 25, 30, edge / 3),
                MenuColors.rgba(25, 25, 30, edge / 3));
        Render2D.flush();
    }
}
