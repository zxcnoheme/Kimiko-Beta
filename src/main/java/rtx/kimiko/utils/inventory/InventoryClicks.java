/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.screen.ScreenHandler
 *  net.minecraft.screen.slot.SlotActionType
 *  net.minecraft.screen.slot.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.network.ClientPlayerInteractionManager
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.inventory;

import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\t\u001a\u0004\u0018\u00010\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0010\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u000fJ#\u0010\u0012\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0014\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0014\u0010\u000fJ+\u0010\u0018\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0016H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0018\u0010\u0019J#\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001c\u0010\u0013J1\u0010\"\u001a\u00020\u000b2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\"\u0010#J\u001b\u0010$\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020\u000b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b)\u0010*\u00a8\u0006+"}, d2={"Lrtx/kimiko/utils/inventory/InventoryClicks;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "canInteract", "()Z", "Lnet/minecraft/ScreenHandler;", "menu", "()Lnet/minecraft/ScreenHandler;", "", "slotId", "", "pickup", "(I)V", "quickMove", "hotbarIndex", "hotbarSwap", "(II)V", "swapOffhand", "button", "Lnet/minecraft/SlotActionType;", "type", "click", "(IILnet/minecraft/SlotActionType;)V", "slotA", "slotB", "swapSlots", "Ljava/util/function/Predicate;", "Lnet/minecraft/ItemStack;", "match", "from", "to", "findSlot", "(Ljava/util/function/Predicate;II)I", "itemAt", "(I)Lnet/minecraft/ItemStack;", "Lnet/minecraft/MinecraftClient;", "MC", "Lnet/minecraft/MinecraftClient;", "OFFHAND_BUTTON", "I", "rtx.kimiko:kimiko"})
public final class InventoryClicks {
    @NotNull
    public static final InventoryClicks INSTANCE = new InventoryClicks();
    @NotNull
    private static final MinecraftClient MC;
    public static final int OFFHAND_BUTTON = 40;

    private InventoryClicks() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @JvmStatic
    public static final boolean canInteract() {
        if (InventoryClicks.MC.player == null) return false;
        if (InventoryClicks.MC.interactionManager == null) return false;
        ClientPlayerEntity clientPlayerEntity2 = InventoryClicks.MC.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity2);
        if (clientPlayerEntity2.currentScreenHandler == null) return false;
        return true;
    }

    @JvmStatic
    @Nullable
    public static final ScreenHandler menu() {
        ClientPlayerEntity clientPlayerEntity2 = InventoryClicks.MC.player;
        return clientPlayerEntity2 != null ? clientPlayerEntity2.currentScreenHandler : null;
    }

    @JvmStatic
    public static final void pickup(int slotId) {
        InventoryClicks.click(slotId, 0, SlotActionType.PICKUP);
    }

    @JvmStatic
    public static final void quickMove(int slotId) {
        InventoryClicks.click(slotId, 0, SlotActionType.QUICK_MOVE);
    }

    @JvmStatic
    public static final void hotbarSwap(int slotId, int hotbarIndex) {
        InventoryClicks.click(slotId, hotbarIndex, SlotActionType.SWAP);
    }

    @JvmStatic
    public static final void swapOffhand(int slotId) {
        InventoryClicks.click(slotId, 40, SlotActionType.SWAP);
    }

    @JvmStatic
    public static final void click(int slotId, int button, @NotNull SlotActionType type) {
        Intrinsics.checkNotNullParameter((Object)type, (String)"type");
        if (!InventoryClicks.canInteract() || slotId < 0) {
            return;
        }
        ClientPlayerEntity clientPlayerEntity2 = InventoryClicks.MC.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity2);
        ScreenHandler screenHandler2 = clientPlayerEntity2.currentScreenHandler;
        Intrinsics.checkNotNullExpressionValue((Object)screenHandler2, (String)"containerMenu");
        ScreenHandler menu = screenHandler2;
        if (slotId >= menu.slots.size()) {
            return;
        }
        ClientPlayerInteractionManager clientPlayerInteractionManager2 = InventoryClicks.MC.interactionManager;
        Intrinsics.checkNotNull((Object)clientPlayerInteractionManager2);
        int n = menu.syncId;
        ClientPlayerEntity clientPlayerEntity3 = InventoryClicks.MC.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity3);
        clientPlayerInteractionManager2.clickSlot(n, slotId, button, type, (PlayerEntity)clientPlayerEntity3);
    }

    @JvmStatic
    public static final void swapSlots(int slotA, int slotB) {
        if (!InventoryClicks.canInteract() || slotA < 0 || slotB < 0 || slotA == slotB) {
            return;
        }
        InventoryClicks.pickup(slotA);
        InventoryClicks.pickup(slotB);
        InventoryClicks.pickup(slotA);
    }

    @JvmStatic
    public static final int findSlot(@NotNull Predicate<ItemStack> match, int from, int to) {
        Intrinsics.checkNotNullParameter(match, (String)"match");
        ScreenHandler screenHandler2 = InventoryClicks.menu();
        if (screenHandler2 == null) {
            return -1;
        }
        ScreenHandler menu = screenHandler2;
        int max = Math.min(to, menu.slots.size() - 1);
        int i = Math.max(0, from);
        if (i <= max) {
            while (true) {
                Slot slot = (Slot) (menu.getSlot(i));
                if (slot.hasStack() && match.test(slot.getStack())) {
                    return i;
                }
                if (i == max) break;
                ++i;
            }
        }
        return -1;
    }

    @JvmStatic
    @NotNull
    public static final ItemStack itemAt(int slotId) {
        ScreenHandler menu = InventoryClicks.menu();
        if (menu == null || slotId < 0 || slotId >= menu.slots.size()) {
            ItemStack itemStack2 = ItemStack.EMPTY;
            Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"EMPTY");
            return itemStack2;
        }
        ItemStack itemStack3 = menu.getSlot(slotId).getStack();
        Intrinsics.checkNotNullExpressionValue((Object)itemStack3, (String)"getItem(...)");
        return itemStack3;
    }

    static {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MC = minecraftClient2;
    }
}

