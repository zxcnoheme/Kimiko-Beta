/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.screen.PlayerScreenHandler
 *  net.minecraft.item.ItemStack
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.inventory;

import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.inventory.InventoryClicks;
import rtx.kimiko.utils.inventory.InventoryItems;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001d\u0010\n\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\n\u0010\tJ!\u0010\u000e\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fJ!\u0010\u0010\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0011J!\u0010\u0012\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0012\u0010\u000fJ!\u0010\u0013\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0013\u0010\u000fJ\u001d\u0010\u0015\u001a\u00020\u00142\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/utils/inventory/HotbarSwapper;", "", "<init>", "()V", "", "nameQuery", "", "Lkotlin/jvm/JvmStatic;", "swapNamedToHand", "(Ljava/lang/String;)Z", "hasNamed", "Ljava/util/function/Predicate;", "Lnet/minecraft/ItemStack;", "match", "has", "(Ljava/util/function/Predicate;)Z", "find", "(Ljava/util/function/Predicate;)Lnet/minecraft/ItemStack;", "swapToHand", "swapToOffhand", "", "findStorage", "(Ljava/util/function/Predicate;)I", "rtx.kimiko:kimiko"})
public final class HotbarSwapper {
    @NotNull
    public static final HotbarSwapper INSTANCE = new HotbarSwapper();

    private HotbarSwapper() {
    }

    @JvmStatic
    public static final boolean swapNamedToHand(@Nullable String nameQuery) {
        return HotbarSwapper.swapToHand(arg_0 -> HotbarSwapper.swapNamedToHand$lambda$0(nameQuery, arg_0));
    }

    @JvmStatic
    public static final boolean hasNamed(@Nullable String nameQuery) {
        return INSTANCE.findStorage(arg_0 -> HotbarSwapper.hasNamed$lambda$0(nameQuery, arg_0)) != -1;
    }

    @JvmStatic
    public static final boolean has(@NotNull Predicate<ItemStack> match) {
        Intrinsics.checkNotNullParameter(match, (String)"match");
        return INSTANCE.findStorage(match) != -1;
    }

    @JvmStatic
    @NotNull
    public static final ItemStack find(@NotNull Predicate<ItemStack> match) {
        ItemStack itemStack2;
        Intrinsics.checkNotNullParameter(match, (String)"match");
        int slot = INSTANCE.findStorage(match);
        if (slot == -1) {
            ItemStack itemStack3 = ItemStack.EMPTY;
            itemStack2 = itemStack3;
            Intrinsics.checkNotNullExpressionValue((Object)itemStack3, (String)"EMPTY");
        } else {
            itemStack2 = InventoryClicks.itemAt(slot);
        }
        return itemStack2;
    }

    @JvmStatic
    public static final boolean swapToHand(@NotNull Predicate<ItemStack> match) {
        Intrinsics.checkNotNullParameter(match, (String)"match");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.player == null || mc.interactionManager == null || mc.currentScreen != null) {
            return false;
        }
        ClientPlayerEntity clientPlayerEntity2 = mc.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity2);
        PlayerScreenHandler playerScreenHandler2 = clientPlayerEntity2.playerScreenHandler;
        Intrinsics.checkNotNullExpressionValue((Object)playerScreenHandler2, (String)"inventoryMenu");
        PlayerScreenHandler menu = playerScreenHandler2;
        ClientPlayerEntity clientPlayerEntity3 = mc.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity3);
        if (clientPlayerEntity3.currentScreenHandler != menu) {
            return false;
        }
        int slot = INSTANCE.findStorage(match);
        if (slot == -1) {
            return false;
        }
        ClientPlayerEntity clientPlayerEntity4 = mc.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity4);
        int hotbar = clientPlayerEntity4.getInventory().getSelectedSlot();
        if (hotbar < 0 || hotbar > 8) {
            return false;
        }
        InventoryClicks.hotbarSwap(slot, hotbar);
        return true;
    }

    @JvmStatic
    public static final boolean swapToOffhand(@NotNull Predicate<ItemStack> match) {
        Intrinsics.checkNotNullParameter(match, (String)"match");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.player == null || mc.interactionManager == null || mc.currentScreen != null) {
            return false;
        }
        ClientPlayerEntity clientPlayerEntity2 = mc.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity2);
        PlayerScreenHandler playerScreenHandler2 = clientPlayerEntity2.playerScreenHandler;
        Intrinsics.checkNotNullExpressionValue((Object)playerScreenHandler2, (String)"inventoryMenu");
        PlayerScreenHandler menu = playerScreenHandler2;
        ClientPlayerEntity clientPlayerEntity3 = mc.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity3);
        if (clientPlayerEntity3.currentScreenHandler != menu) {
            return false;
        }
        int slot = INSTANCE.findStorage(match);
        if (slot == -1) {
            return false;
        }
        InventoryClicks.swapOffhand(slot);
        return true;
    }

    private final int findStorage(Predicate<ItemStack> match) {
        return InventoryClicks.findSlot(match, 9, 44);
    }

    private static final boolean swapNamedToHand$lambda$0(String $nameQuery, ItemStack stack) {
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        return InventoryItems.nameContains(stack, $nameQuery);
    }

    private static final boolean hasNamed$lambda$0(String $nameQuery, ItemStack stack) {
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        return InventoryItems.nameContains(stack, $nameQuery);
    }
}

