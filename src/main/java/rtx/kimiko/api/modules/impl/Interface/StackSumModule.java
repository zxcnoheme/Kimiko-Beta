package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;

/**
 * Stack Sum NEW: клавиша считает суммарное количество предметов того же типа,
 * что в главной руке, по всему инвентарю.
 */
public class StackSumModule extends Module {
    private final BindSetting bind;
    private boolean lastDown;

    public StackSumModule() {
        super("Stack Sum NEW", "Клавиша считает суммарное количество предметов того же типа, что в руке.", Category.DISPLAY);
        this.bind = (BindSetting) this.register((Setting) new BindSetting("Клавиша", "Клавиша подсчёта.").setKey(-1));
        this.lastDown = false;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled() || this.mc.player == null) {
            return;
        }
        boolean down = this.bind.getValue().isDown(this.mc.getWindow().getHandle());
        if (down && !this.lastDown) {
            this.count();
        }
        this.lastDown = down;
    }

    private void count() {
        ItemStack main = this.mc.player.getMainHandStack();
        if (main == null || main.isEmpty()) {
            this.chat("§7В главной руке пусто.");
            return;
        }
        int total = 0;
        int slots = 0;
        for (int i = 0; i < this.mc.player.getInventory().size(); i++) {
            ItemStack s = this.mc.player.getInventory().getStack(i);
            if (s == null || s.isEmpty()) {
                continue;
            }
            if (s.isOf(main.getItem())) {
                total += s.getCount();
                slots++;
            }
        }
        if (total == 0) {
            this.chat("§7" + main.getName().getString() + ": §f0");
            return;
        }
        this.chat("§a" + main.getName().getString() + ": §f" + total + " §7(в " + slots + " слот.)");
    }

    private void chat(String s) {
        if (this.mc.player != null) {
            this.mc.player.sendMessage(Text.literal(s), false);
        }
    }
}
