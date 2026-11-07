package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Учёт лута: сколько предметов добыто за сессию (по дельте инвентаря).
 */
public class LootCounterModule extends Module {
    private final SliderSetting yShift;
    private int lastTotal = -1;
    private int looted;

    public LootCounterModule() {
        super("Loot Counter NEW", "Сколько предметов ты собрал за сессию — счётчик над хотбаром.", Category.DISPLAY);
        this.yShift = (SliderSetting) this.register((Setting) new SliderSetting("Смещение", "Выше/ниже над хотбаром.").setValue(16.0f).range(0.0f, 48.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || this.mc.player == null) {
            return;
        }
        var inventory = this.mc.player.getInventory();
        int total = 0;
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                total += stack.getCount();
            }
        }
        if (this.lastTotal < 0) {
            this.lastTotal = total;
            return;
        }
        int delta = total - this.lastTotal;
        this.lastTotal = total;
        if (delta > 0) {
            this.looted += delta;
        }
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null || this.looted <= 0) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        String label = "Лут: " + this.looted;
        float textW = Fonts.SEMIBOLD.width(label, 8.0f);
        float bx = w * 0.5f + 50.0f;
        float by = h - 76.0f - this.yShift.getValue();
        Render2D.rect(bx, by, textW + 14.0f, 13.0f, 6.5f, MenuColors.rgba(9, 11, 17, 170));
        Fonts.SEMIBOLD.draw(label, bx + 7.0f, by + 3.5f, 8.0f, MenuColors.rgba(180, 235, 160, 220));
        Render2D.flush();
    }
}
