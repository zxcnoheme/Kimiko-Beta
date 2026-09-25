/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmOverloads
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.screen.ScreenHandler
 *  net.minecraft.screen.PlayerScreenHandler
 *  net.minecraft.item.ItemStack
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.inventory;

import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.inventory.InventoryClicks;
import rtx.kimiko.utils.inventory.InventoryItems;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJ!\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000b\u001a\u00020\bH\u0007b\u0002\b\u0005b\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\nJ\u001f\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J%\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u0017H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/inventory/ElytraSwapper;", "", "<init>", "()V", "Lrtx/kimiko/utils/inventory/ElytraSwapper$Result;", "Lkotlin/jvm/JvmStatic;", "swap", "()Lrtx/kimiko/utils/inventory/ElytraSwapper$Result;", "", "hasTarget", "()Z", "ignoreLock", "", "Lkotlin/jvm/JvmOverloads;", "findTargetSlot", "(Z)I", "chestLocked", "Lnet/minecraft/ScreenHandler;", "menu", "Lnet/minecraft/ItemStack;", "chest", "pickTarget", "(Lnet/minecraft/ScreenHandler;Lnet/minecraft/ItemStack;)I", "Ljava/util/function/Predicate;", "match", "findInStorage", "(Lnet/minecraft/ScreenHandler;Ljava/util/function/Predicate;)I", "Result", "rtx.kimiko:kimiko"})
public final class ElytraSwapper {
    @NotNull
    public static final ElytraSwapper INSTANCE = new ElytraSwapper();

    private ElytraSwapper() {
    }

    @JvmStatic
    @NotNull
    public static final Result swap() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        ClientPlayerEntity player = mc.player;
        if (player == null || mc.interactionManager == null) {
            return Result.UNAVAILABLE;
        }
        PlayerScreenHandler playerScreenHandler2 = player.playerScreenHandler;
        Intrinsics.checkNotNullExpressionValue((Object)playerScreenHandler2, (String)"inventoryMenu");
        PlayerScreenHandler menu = playerScreenHandler2;
        if (player.currentScreenHandler != menu) {
            return Result.UNAVAILABLE;
        }
        ItemStack itemStack2 = menu.getSlot(6).getStack();
        Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"getItem(...)");
        ItemStack chest = itemStack2;
        int target = INSTANCE.pickTarget((ScreenHandler)menu, chest);
        if (target == -1) {
            return Result.NO_TARGET;
        }
        if (!chest.isEmpty() && !menu.getSlot(6).canTakeItems((PlayerEntity)player)) {
            return Result.BLOCKED;
        }
        InventoryClicks.swapSlots(6, target);
        return Result.SWAPPED;
    }

    @JvmStatic
    public static final boolean hasTarget() {
        return ElytraSwapper.findTargetSlot$default(false, 1, null) != -1;
    }

    @JvmStatic
    @JvmOverloads
    public static final int findTargetSlot(boolean ignoreLock) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        ClientPlayerEntity clientPlayerEntity2 = mc.player;
        if (clientPlayerEntity2 == null) {
            return -1;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        PlayerScreenHandler playerScreenHandler2 = player.playerScreenHandler;
        Intrinsics.checkNotNullExpressionValue((Object)playerScreenHandler2, (String)"inventoryMenu");
        PlayerScreenHandler menu = playerScreenHandler2;
        ItemStack itemStack2 = menu.getSlot(6).getStack();
        Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"getItem(...)");
        ItemStack chest = itemStack2;
        int target = INSTANCE.pickTarget((ScreenHandler)menu, chest);
        if (target == -1) {
            return -1;
        }
        if (!(ignoreLock || chest.isEmpty() || menu.getSlot(6).canTakeItems((PlayerEntity)player))) {
            return -1;
        }
        return target;
    }

    public static /* synthetic */ int findTargetSlot$default(boolean bl, int n, Object object) {
        if ((n & 1) != 0) {
            bl = false;
        }
        return ElytraSwapper.findTargetSlot(bl);
    }

    @JvmStatic
    public static final boolean chestLocked() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        ClientPlayerEntity clientPlayerEntity2 = mc.player;
        if (clientPlayerEntity2 == null) {
            return false;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        PlayerScreenHandler playerScreenHandler2 = player.playerScreenHandler;
        Intrinsics.checkNotNullExpressionValue((Object)playerScreenHandler2, (String)"inventoryMenu");
        PlayerScreenHandler menu = playerScreenHandler2;
        ItemStack itemStack2 = menu.getSlot(6).getStack();
        Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"getItem(...)");
        ItemStack chest = itemStack2;
        return !chest.isEmpty() && !menu.getSlot(6).canTakeItems((PlayerEntity)player);
    }

    private final int pickTarget(ScreenHandler menu, ItemStack chest) {
        if (InventoryItems.isElytra(chest)) {
            return this.findInStorage(menu, ElytraSwapper::pickTarget$lambda$0);
        }
        if (InventoryItems.isChestplate(chest)) {
            return this.findInStorage(menu, ElytraSwapper::pickTarget$lambda$1);
        }
        int elytra = this.findInStorage(menu, ElytraSwapper::pickTarget$lambda$2);
        return elytra != -1 ? elytra : this.findInStorage(menu, ElytraSwapper::pickTarget$lambda$3);
    }

    private final int findInStorage(ScreenHandler menu, Predicate<ItemStack> match) {
        int i = 9;
        int max = Math.min(45, menu.slots.size() - 1);
        if (i <= max) {
            while (true) {
                ItemStack stack = (ItemStack) (menu.getSlot(i).getStack());
                if (!stack.isEmpty() && match.test(stack)) {
                    return i;
                }
                if (i == max) break;
                ++i;
            }
        }
        return -1;
    }

    @JvmStatic
    @JvmOverloads
    public static final int findTargetSlot() {
        return ElytraSwapper.findTargetSlot$default(false, 1, null);
    }

    private static final boolean pickTarget$lambda$0(ItemStack it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return InventoryItems.isChestplate(it);
    }

    private static final boolean pickTarget$lambda$1(ItemStack it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return InventoryItems.isElytra(it);
    }

    private static final boolean pickTarget$lambda$2(ItemStack it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return InventoryItems.isElytra(it);
    }

    private static final boolean pickTarget$lambda$3(ItemStack it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return InventoryItems.isChestplate(it);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/utils/inventory/ElytraSwapper$Result;", "", "<init>", "(Ljava/lang/String;I)V", "SWAPPED", "NO_TARGET", "BLOCKED", "UNAVAILABLE", "rtx.kimiko:kimiko"})
    public static enum Result {
        SWAPPED,
        NO_TARGET,
        BLOCKED,
        UNAVAILABLE;
@NotNull
        public static EnumEntries<Result> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }
}

