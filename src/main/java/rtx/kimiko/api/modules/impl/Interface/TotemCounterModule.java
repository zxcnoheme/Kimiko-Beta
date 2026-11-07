package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Счётчик тотемов: сколько тотемов бессмертия лежит в инвентаре —
 * плашка над хотбаром, краснеет на нуле.
 */
public class TotemCounterModule extends Module {
    private final SliderSetting yShift;

    public TotemCounterModule() {
        super("Totem Counter NEW", "Плашка над хотбаром: сколько тотемов в инвентаре. На нуле — красная.", Category.DISPLAY);
        this.yShift = (SliderSetting) this.register((Setting) new SliderSetting("Смещение", "Выше/ниже над хотбаром.").setValue(16.0f).range(0.0f, 48.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null) {
            return;
        }
        int totems = 0;
        var inventory = this.mc.player.getInventory();
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty() && stack.isOf(Items.TOTEM_OF_UNDYING)) {
                totems += stack.getCount();
            }
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        String label = "Тотемы: " + totems;
        float textW = Fonts.SEMIBOLD.width(label, 8.0f);
        int bg = totems == 0 ? MenuColors.rgba(120, 20, 20, 190) : MenuColors.rgba(9, 11, 17, 170);
        int text = totems == 0 ? MenuColors.rgba(255, 120, 120, 240) : MenuColors.rgba(235, 240, 250, 220);
        float bx = w * 0.5f + 50.0f;
        float by = h - 62.0f - this.yShift.getValue();
        Render2D.rect(bx, by, textW + 14.0f, 13.0f, 6.5f, bg);
        Fonts.SEMIBOLD.draw(label, bx + 7.0f, by + 3.5f, 8.0f, text);
        Render2D.flush();
    }
}
