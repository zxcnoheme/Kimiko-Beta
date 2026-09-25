package rtx.kimiko.api.modules.impl.Utils;

import net.minecraft.item.Items;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import mixin.accessor.LivingEntityAccessor;
import mixin.accessor.MinecraftAccessor;

@Feature(value={"fastexp"})
public final class FastExp
extends Module {
    public FastExp() {
        super("FastExp", "Убирает задержку при использовании бутылочек опыта.", Category.UTILS);
    }

    @EventHandler
    public void onTick(@NotNull TickEvent tickEvent) {
        boolean bl;
        if (!tickEvent.isPre() || this.mc.player == null) {
            return;
        }
        boolean bl2 = bl = this.mc.player.getMainHandStack().isOf(Items.EXPERIENCE_BOTTLE) || this.mc.player.getOffHandStack().isOf(Items.EXPERIENCE_BOTTLE);
        if (!bl) {
            return;
        }
        ((MinecraftAccessor)(Object)this.mc).kimiko$setRightClickDelay(0);
        ((LivingEntityAccessor)(Object)this.mc.player).kimiko$setAttackAnim(0.0f);
    }
}