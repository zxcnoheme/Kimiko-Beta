/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.Util
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerInventory
 *  net.minecraft.screen.ScreenHandler
 *  net.minecraft.screen.slot.SlotActionType
 *  net.minecraft.screen.slot.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.screen.ingame.HandledScreen
 *  net.minecraft.client.network.ClientPlayerInteractionManager
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.invmanager;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Util;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.invmanager.InventoryTemplates;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002OPB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\u000b\u001a\u00020\n2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u0003J\u000f\u0010\u000e\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u0003J)\u0010\u0012\u001a\u00020\n2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0013J%\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0016\u0010\u0017J+\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00182\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJA\u0010!\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b!\u0010\"J\u001f\u0010$\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b$\u0010%J'\u0010+\u001a\u00020\n2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020&2\u0006\u0010*\u001a\u00020)H\u0002\u00a2\u0006\u0004\b+\u0010,J\u001f\u0010/\u001a\u00020\n2\u0006\u0010.\u001a\u00020-2\u0006\u0010\u0015\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b/\u00100J\u001f\u00102\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u0002012\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b2\u00103J\u0017\u00105\u001a\u00020\u00042\u0006\u00104\u001a\u00020&H\u0002\u00a2\u0006\u0004\b5\u00106J'\u00108\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d072\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b8\u00109R\u0014\u0010;\u001a\u00020:8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u0014\u0010=\u001a\u00020:8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b=\u0010<R\u0014\u0010>\u001a\u00020&8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020&8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u0010?R\u0016\u0010A\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u0010BR\u0016\u0010C\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bC\u0010BR\u001c\u0010D\u001a\b\u0012\u0002\b\u0003\u0018\u00010\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u0016\u0010F\u001a\u00020:8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u0010<R\u0016\u0010G\u001a\u00020:8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bG\u0010<R\u001a\u0010J\u001a\b\u0012\u0004\u0012\u00020I0H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010KR\u0014\u0010M\u001a\u00020L8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bM\u0010N\u00a8\u0006Q"}, d2={"Lrtx/kimiko/api/invmanager/InventoryArranger;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "isActive", "()Z", "Lnet/minecraft/HandledScreen;", "target", "", "start", "(Lnet/minecraft/HandledScreen;)V", "stop", "finish", "current", "Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;", "template", "tick", "(Lnet/minecraft/HandledScreen;Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;)V", "Lnet/minecraft/ScreenHandler;", "menu", "needsArrange", "(Lnet/minecraft/ScreenHandler;Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;)Z", "Lnet/minecraft/PlayerInventory;", "inventory", "Lrtx/kimiko/api/invmanager/InventoryArranger$Move;", "findMove", "(Lnet/minecraft/ScreenHandler;Lnet/minecraft/PlayerInventory;Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;)Lrtx/kimiko/api/invmanager/InventoryArranger$Move;", "Lnet/minecraft/Slot;", "Lrtx/kimiko/api/invmanager/InventoryTemplates$Entry;", "wanted", "compatibleTarget", "findSource", "(Lnet/minecraft/ScreenHandler;Lnet/minecraft/PlayerInventory;Lrtx/kimiko/api/invmanager/InventoryTemplates$Template;Lnet/minecraft/Slot;Lrtx/kimiko/api/invmanager/InventoryTemplates$Entry;Z)Lnet/minecraft/Slot;", "move", "queueMove", "(Lnet/minecraft/ScreenHandler;Lrtx/kimiko/api/invmanager/InventoryArranger$Move;)V", "", "slotId", "button", "Lnet/minecraft/SlotActionType;", "clickType", "enqueue", "(IILnet/minecraft/SlotActionType;)V", "Lnet/minecraft/MinecraftClient;", "mc", "executePendingClick", "(Lnet/minecraft/MinecraftClient;Lnet/minecraft/ScreenHandler;)V", "Lnet/minecraft/ItemStack;", "satisfied", "(Lnet/minecraft/ItemStack;Lrtx/kimiko/api/invmanager/InventoryTemplates$Entry;)Z", "inventoryIndex", "isHotbar", "(I)Z", "", "playerSlots", "(Lnet/minecraft/ScreenHandler;Lnet/minecraft/PlayerInventory;)[Lnet/minecraft/Slot;", "", "MOVE_INTERVAL_MS", "J", "SETTLE_DELAY_MS", "OFFHAND_INDEX", "I", "BUFFER_HOTBAR_INDEX", "active", "Z", "inspectionBroken", "screen", "Lnet/minecraft/HandledScreen;", "lastMoveMs", "matchedSinceMs", "Ljava/util/Deque;", "Lrtx/kimiko/api/invmanager/InventoryArranger$PendingClick;", "pendingClicks", "Ljava/util/Deque;", "", "ORDERED_INVENTORY_INDEX", "[I", "Move", "PendingClick", "rtx.kimiko:kimiko"})
public final class InventoryArranger {
    @NotNull
    public static final InventoryArranger INSTANCE = new InventoryArranger();
    private static final long MOVE_INTERVAL_MS = 100L;
    private static final long SETTLE_DELAY_MS = 1000L;
    private static final int OFFHAND_INDEX = 40;
    private static final int BUFFER_HOTBAR_INDEX = 0;
    private static boolean active;
    private static boolean inspectionBroken;
    @Nullable
    private static HandledScreen<?> screen;
    private static long lastMoveMs;
    private static long matchedSinceMs;
    @NotNull
    private static final Deque<PendingClick> pendingClicks;
    @NotNull
    private static final int[] ORDERED_INVENTORY_INDEX;

    private InventoryArranger() {
    }

    @JvmStatic
    public static final boolean isActive() {
        return active;
    }

    @JvmStatic
    public static final void start(@NotNull HandledScreen<?> target) {
        Intrinsics.checkNotNullParameter(target, (String)"target");
        pendingClicks.clear();
        ScreenHandler screenHandler2 = target.getScreenHandler();
        Intrinsics.checkNotNullExpressionValue((Object)screenHandler2, (String)"getMenu(...)");
        ScreenHandler menu = screenHandler2;
        if (!menu.getCursorStack().isEmpty()) {
            INSTANCE.finish();
            return;
        }
        active = true;
        screen = target;
        lastMoveMs = 0L;
        matchedSinceMs = 0L;
    }

    @JvmStatic
    public static final void stop() {
        INSTANCE.finish();
    }

    private final void finish() {
        active = false;
        screen = null;
        pendingClicks.clear();
    }

    @JvmStatic
    public static final void tick(@NotNull HandledScreen<?> current, @Nullable InventoryTemplates.Template template) {
        Intrinsics.checkNotNullParameter(current, (String)"current");
        if (!active) {
            return;
        }
        if (current != screen) {
            INSTANCE.finish();
            return;
        }
        if (template == null) {
            INSTANCE.finish();
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.player == null || mc.interactionManager == null) {
            INSTANCE.finish();
            return;
        }
        ScreenHandler screenHandler2 = current.getScreenHandler();
        Intrinsics.checkNotNullExpressionValue((Object)screenHandler2, (String)"getMenu(...)");
        ScreenHandler menu = screenHandler2;
        ClientPlayerEntity clientPlayerEntity2 = mc.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity2);
        PlayerInventory playerInventory2 = clientPlayerEntity2.getInventory();
        Intrinsics.checkNotNullExpressionValue((Object)playerInventory2, (String)"getInventory(...)");
        PlayerInventory inventory = playerInventory2;
        long now = Util.getMeasuringTimeMs();
        if (now - lastMoveMs < 100L) {
            return;
        }
        if (pendingClicks.isEmpty() && !menu.getCursorStack().isEmpty()) {
            lastMoveMs = now;
            matchedSinceMs = 0L;
            return;
        }
        if (!pendingClicks.isEmpty()) {
            INSTANCE.executePendingClick(mc, menu);
            lastMoveMs = now;
            return;
        }
        Move move = INSTANCE.findMove(menu, inventory, template);
        if (move == null) {
            if (matchedSinceMs == 0L) {
                matchedSinceMs = now;
            } else if (now - matchedSinceMs >= 1000L) {
                INSTANCE.finish();
            }
            lastMoveMs = now;
            return;
        }
        matchedSinceMs = 0L;
        INSTANCE.queueMove(menu, move);
        if (!pendingClicks.isEmpty()) {
            INSTANCE.executePendingClick(mc, menu);
        }
        lastMoveMs = now;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @JvmStatic
    public static final boolean needsArrange(@NotNull ScreenHandler menu, @Nullable InventoryTemplates.Template template) {
        Intrinsics.checkNotNullParameter((Object)menu, (String)"menu");
        if (inspectionBroken) {
            return false;
        }
        try {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            if (mc.player == null) return false;
            ClientPlayerEntity clientPlayerEntity2 = mc.player;
            Intrinsics.checkNotNull((Object)clientPlayerEntity2);
            PlayerInventory playerInventory2 = clientPlayerEntity2.getInventory();
            Intrinsics.checkNotNullExpressionValue((Object)playerInventory2, (String)"getInventory(...)");
            if (INSTANCE.findMove(menu, playerInventory2, template) == null) return false;
            return true;
        }
        catch (Throwable failure) {
            inspectionBroken = true;
            return false;
        }
    }

    private final Move findMove(ScreenHandler menu, PlayerInventory inventory, InventoryTemplates.Template template) {
        if (template == null) {
            return null;
        }
        Slot[] playerSlots = this.playerSlots(menu, inventory);
        int n = playerSlots.length;
        for (int order = 0; order < n; ++order) {
            int inventoryIndex = order < ORDERED_INVENTORY_INDEX.length ? ORDERED_INVENTORY_INDEX[order] : 0;
            InventoryTemplates.Entry wanted = template.slots.get(inventoryIndex);
            Slot target = playerSlots[inventoryIndex];
            if (wanted == null || target == null) continue;
            ItemStack targetStack = target.getStack();
            if (this.satisfied(targetStack, wanted)) continue;
            String wantedKey = InventoryTemplates.entryLayoutKey(wanted);
            boolean compatibleTarget = targetStack.isEmpty() || Intrinsics.areEqual((Object)InventoryTemplates.layoutKey(targetStack), (Object)wantedKey);
            Slot source;
            if (compatibleTarget && !targetStack.isEmpty() || (source = this.findSource(menu, inventory, template, target, wanted, compatibleTarget)) == null) continue;
            return new Move(source, target, wanted, compatibleTarget);
        }
        return null;
    }

    private final Slot findSource(ScreenHandler menu, PlayerInventory inventory, InventoryTemplates.Template template, Slot target, InventoryTemplates.Entry wanted, boolean compatibleTarget) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        String wantedKey = InventoryTemplates.entryLayoutKey(wanted);
        for (Slot slot : menu.slots) {
            if (slot == target || slot.inventory != inventory) continue;
            ItemStack stack = slot.getStack();
            InventoryTemplates.Entry ownWanted;
            if (stack.isEmpty() || !Intrinsics.areEqual((Object)InventoryTemplates.layoutKey(stack), (Object)wantedKey) || !target.canInsert(stack) || player != null && !slot.canTakeItems((PlayerEntity)player) || (ownWanted = template.slots.get(slot.getIndex())) != null && this.satisfied(stack, ownWanted) || stack.getCount() != wanted.count() || !compatibleTarget && !slot.canInsert(target.getStack())) continue;
            return slot;
        }
        return null;
    }

    private final void queueMove(ScreenHandler menu, Move move) {
        int sourceId = menu.slots.indexOf((Object)move.getSource());
        int targetId = menu.slots.indexOf((Object)move.getTarget());
        if (sourceId < 0 || targetId < 0) {
            return;
        }
        int sourceIndex = move.getSource().getIndex();
        int targetIndex = move.getTarget().getIndex();
        if (this.isHotbar(sourceIndex)) {
            this.enqueue(targetId, sourceIndex, SlotActionType.SWAP);
        } else if (this.isHotbar(targetIndex)) {
            this.enqueue(sourceId, targetIndex, SlotActionType.SWAP);
        } else if (sourceIndex == 40) {
            this.enqueue(targetId, 40, SlotActionType.SWAP);
        } else if (targetIndex == 40) {
            this.enqueue(sourceId, 40, SlotActionType.SWAP);
        } else {
            this.enqueue(sourceId, 0, SlotActionType.SWAP);
            this.enqueue(targetId, 0, SlotActionType.SWAP);
            this.enqueue(sourceId, 0, SlotActionType.SWAP);
        }
    }

    private final void enqueue(int slotId, int button, SlotActionType clickType) {
        pendingClicks.addLast(new PendingClick(slotId, button, clickType));
    }

    private final void executePendingClick(MinecraftClient mc, ScreenHandler menu) {
        PendingClick pendingClick = pendingClicks.removeFirst();
        ClientPlayerInteractionManager clientPlayerInteractionManager2 = mc.interactionManager;
        Intrinsics.checkNotNull((Object)clientPlayerInteractionManager2);
        int n = menu.syncId;
        int n2 = pendingClick.getSlotId();
        int n3 = pendingClick.getButton();
        SlotActionType slotActionType2 = pendingClick.getClickType();
        ClientPlayerEntity clientPlayerEntity2 = mc.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity2);
        clientPlayerInteractionManager2.clickSlot(n, n2, n3, slotActionType2, (PlayerEntity)clientPlayerEntity2);
    }

    private final boolean satisfied(ItemStack current, InventoryTemplates.Entry wanted) {
        return !current.isEmpty() && Intrinsics.areEqual((Object)InventoryTemplates.layoutKey(current), (Object)InventoryTemplates.entryLayoutKey(wanted)) && current.getCount() == wanted.count();
    }

    private final boolean isHotbar(int inventoryIndex) {
        return 0 <= inventoryIndex ? inventoryIndex < 9 : false;
    }

    private final Slot[] playerSlots(ScreenHandler menu, PlayerInventory inventory) {
        Slot[] result = new Slot[41];
        Iterator iterator = menu.slots.iterator();
        Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (iterator2.hasNext()) {
            int index;
            Slot slot = (Slot)iterator2.next();
            if (slot.inventory != inventory || (index = slot.getIndex()) < 0 || index >= result.length) continue;
            result[index] = slot;
        }
        return result;
    }

    static {
        pendingClicks = new ArrayDeque();
        int[] nArray = new int[]{9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 1, 2, 3, 4, 5, 6, 7, 8, 36, 37, 38, 39, 40, 0};
        ORDERED_INVENTORY_INDEX = nArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\fJ\u0010\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J8\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0007H\u00c6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0015\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0011\u0010\u0018\u001a\u00020\u0017H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001b\u001a\u00020\u001aH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001d\u001a\u0004\b\u001e\u0010\fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001d\u001a\u0004\b\u001f\u0010\fR\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b!\u0010\u000fR\u0017\u0010\b\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\"\u001a\u0004\b#\u0010\u0011\u00a8\u0006$"}, d2={"Lrtx/kimiko/api/invmanager/InventoryArranger$Move;", "", "Lnet/minecraft/Slot;", "source", "target", "Lrtx/kimiko/api/invmanager/InventoryTemplates$Entry;", "wanted", "", "compatibleTarget", "<init>", "(Lnet/minecraft/Slot;Lnet/minecraft/Slot;Lrtx/kimiko/api/invmanager/InventoryTemplates$Entry;Z)V", "component1", "()Lnet/minecraft/Slot;", "component2", "component3", "()Lrtx/kimiko/api/invmanager/InventoryTemplates$Entry;", "component4", "()Z", "copy", "(Lnet/minecraft/Slot;Lnet/minecraft/Slot;Lrtx/kimiko/api/invmanager/InventoryTemplates$Entry;Z)Lrtx/kimiko/api/invmanager/InventoryArranger$Move;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/Slot;", "getSource", "getTarget", "Lrtx/kimiko/api/invmanager/InventoryTemplates$Entry;", "getWanted", "Z", "getCompatibleTarget", "rtx.kimiko:kimiko"})
    private static final class Move {
        @NotNull
        private final Slot source;
        @NotNull
        private final Slot target;
        @NotNull
        private final InventoryTemplates.Entry wanted;
        private final boolean compatibleTarget;

        public Move(@NotNull Slot source, @NotNull Slot target, @NotNull InventoryTemplates.Entry wanted, boolean compatibleTarget) {
            Intrinsics.checkNotNullParameter((Object)source, (String)"source");
            Intrinsics.checkNotNullParameter((Object)target, (String)"target");
            Intrinsics.checkNotNullParameter((Object)wanted, (String)"wanted");
            this.source = source;
            this.target = target;
            this.wanted = wanted;
            this.compatibleTarget = compatibleTarget;
        }

        @NotNull
        public final Slot getSource() {
            return this.source;
        }

        @NotNull
        public final Slot getTarget() {
            return this.target;
        }

        @NotNull
        public final InventoryTemplates.Entry getWanted() {
            return this.wanted;
        }

        public final boolean getCompatibleTarget() {
            return this.compatibleTarget;
        }

        @NotNull
        public final Slot component1() {
            return this.source;
        }

        @NotNull
        public final Slot component2() {
            return this.target;
        }

        @NotNull
        public final InventoryTemplates.Entry component3() {
            return this.wanted;
        }

        public final boolean component4() {
            return this.compatibleTarget;
        }

        @NotNull
        public final Move copy(@NotNull Slot source, @NotNull Slot target, @NotNull InventoryTemplates.Entry wanted, boolean compatibleTarget) {
            Intrinsics.checkNotNullParameter((Object)source, (String)"source");
            Intrinsics.checkNotNullParameter((Object)target, (String)"target");
            Intrinsics.checkNotNullParameter((Object)wanted, (String)"wanted");
            return new Move(source, target, wanted, compatibleTarget);
        }

        public static /* synthetic */ Move copy$default(Move move, Slot slot2, Slot slot3, InventoryTemplates.Entry entry, boolean bl, int n, Object object) {
            if ((n & 1) != 0) {
                slot2 = move.source;
            }
            if ((n & 2) != 0) {
                slot3 = move.target;
            }
            if ((n & 4) != 0) {
                entry = move.wanted;
            }
            if ((n & 8) != 0) {
                bl = move.compatibleTarget;
            }
            return move.copy(slot2, slot3, entry, bl);
        }

        @NotNull
        public String toString() {
            return "Move(source=" + this.source + ", target=" + this.target + ", wanted=" + this.wanted + ", compatibleTarget=" + this.compatibleTarget + ")";
        }

        public int hashCode() {
            int result = this.source.hashCode();
            result = result * 31 + this.target.hashCode();
            result = result * 31 + this.wanted.hashCode();
            result = result * 31 + Boolean.hashCode(this.compatibleTarget);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Move)) {
                return false;
            }
            Move move = (Move)other;
            if (!Intrinsics.areEqual((Object)this.source, (Object)move.source)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.target, (Object)move.target)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.wanted, (Object)move.wanted)) {
                return false;
            }
            return this.compatibleTarget == move.compatibleTarget;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\nJ\u0010\u0010\f\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ.\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0014\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0014\u0010\nJ\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\nR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u001a\u0010\nR\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b\u001c\u0010\r\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/api/invmanager/InventoryArranger$PendingClick;", "", "", "slotId", "button", "Lnet/minecraft/SlotActionType;", "clickType", "<init>", "(IILnet/minecraft/SlotActionType;)V", "component1", "()I", "component2", "component3", "()Lnet/minecraft/SlotActionType;", "copy", "(IILnet/minecraft/SlotActionType;)Lrtx/kimiko/api/invmanager/InventoryArranger$PendingClick;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "I", "getSlotId", "getButton", "Lnet/minecraft/SlotActionType;", "getClickType", "rtx.kimiko:kimiko"})
    private static final class PendingClick {
        private final int slotId;
        private final int button;
        @NotNull
        private final SlotActionType clickType;

        public PendingClick(int slotId, int button, @NotNull SlotActionType clickType) {
            Intrinsics.checkNotNullParameter((Object)clickType, (String)"clickType");
            this.slotId = slotId;
            this.button = button;
            this.clickType = clickType;
        }

        public final int getSlotId() {
            return this.slotId;
        }

        public final int getButton() {
            return this.button;
        }

        @NotNull
        public final SlotActionType getClickType() {
            return this.clickType;
        }

        public final int component1() {
            return this.slotId;
        }

        public final int component2() {
            return this.button;
        }

        @NotNull
        public final SlotActionType component3() {
            return this.clickType;
        }

        @NotNull
        public final PendingClick copy(int slotId, int button, @NotNull SlotActionType clickType) {
            Intrinsics.checkNotNullParameter((Object)clickType, (String)"clickType");
            return new PendingClick(slotId, button, clickType);
        }

        public static /* synthetic */ PendingClick copy$default(PendingClick pendingClick, int n, int n2, SlotActionType slotActionType2, int n3, Object object) {
            if ((n3 & 1) != 0) {
                n = pendingClick.slotId;
            }
            if ((n3 & 2) != 0) {
                n2 = pendingClick.button;
            }
            if ((n3 & 4) != 0) {
                slotActionType2 = pendingClick.clickType;
            }
            return pendingClick.copy(n, n2, slotActionType2);
        }

        @NotNull
        public String toString() {
            return "PendingClick(slotId=" + this.slotId + ", button=" + this.button + ", clickType=" + this.clickType + ")";
        }

        public int hashCode() {
            int result = Integer.hashCode(this.slotId);
            result = result * 31 + Integer.hashCode(this.button);
            result = result * 31 + this.clickType.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PendingClick)) {
                return false;
            }
            PendingClick pendingClick = (PendingClick)other;
            if (this.slotId != pendingClick.slotId) {
                return false;
            }
            if (this.button != pendingClick.button) {
                return false;
            }
            return this.clickType == pendingClick.clickType;
        }
    }
}

