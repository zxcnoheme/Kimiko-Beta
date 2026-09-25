/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.util.Window
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.screen.slot.SlotActionType
 *  net.minecraft.screen.slot.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.util.collection.DefaultedList
 *  net.minecraft.client.option.KeyBinding
 *  net.minecraft.client.util.InputUtil.Key
 *  net.minecraft.client.util.InputUtil.Type
 *  net.minecraft.client.network.ClientPlayerInteractionManager
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.glfw.GLFW
 */
package rtx.kimiko.api.modules.impl.Utils;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.util.Window;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.item.Item;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.inventory.ClickSlotEvent;
import rtx.kimiko.api.events.impl.inventory.HandledScreenEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.time.StopWatch;

@Feature(value={"itemscroller"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001b\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001c\u00ca\u0001\u0010\b\u001d\u0012\f\b\u001e\u0012\b\b\fJ\u0004\b\b(\u001f\u00a8\u0006 "}, d2={"Lrtx/kimiko/api/modules/impl/Utils/ItemScroller;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "Lrtx/kimiko/api/events/impl/inventory/HandledScreenEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onHandledScreen", "(Lrtx/kimiko/api/events/impl/inventory/HandledScreenEvent;)V", "Lrtx/kimiko/api/events/impl/inventory/ClickSlotEvent;", "onClickSlot", "(Lrtx/kimiko/api/events/impl/inventory/ClickSlotEvent;)V", "Lnet/minecraft/KeyBinding;", "key", "", "isKeyDown", "(Lnet/minecraft/KeyBinding;)Z", "Lnet/minecraft/Slot;", "slot", "", "menuSlotId", "(Lnet/minecraft/Slot;)I", "Lrtx/kimiko/utils/time/StopWatch;", "stopWatch", "Lrtx/kimiko/utils/time/StopWatch;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "scrollDelay", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "Lrtx/kimiko/api/liteapi/Feature;", "value", "itemscroller", "rtx.kimiko:kimiko"})
public final class ItemScroller
extends Module {
    @NotNull
    private final StopWatch stopWatch = new StopWatch();
    @NotNull
    private final SliderSetting scrollDelay = (SliderSetting)this.register((Setting)new SliderSetting("Задержка прокрутки", "Задержка между кликами прокрутки предметов.").range(0, 200).increment(1).setValue(50.0f));

    public ItemScroller() {
        super("Item Scroller", "Прокручивает подходящие предметы в инвентаре с клавишами-модификаторами.", Category.UTILS);
    }

    @EventHandler
    public final void onHandledScreen(@NotNull HandledScreenEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, "event");
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        ClientPlayerInteractionManager clientPlayerInteractionManager2 = this.mc.interactionManager;
        if (clientPlayerInteractionManager2 == null) {
            return;
        }
        ClientPlayerInteractionManager gameMode = clientPlayerInteractionManager2;
        Slot hoverSlot = event.getSlotHover();
        KeyBinding keyBinding2 = this.mc.options.dropKey;
        Intrinsics.checkNotNullExpressionValue((Object)keyBinding2, "keyDrop");
        SlotActionType actionType;
        if (this.isKeyDown(keyBinding2)) {
            actionType = SlotActionType.THROW;
        } else {
            KeyBinding keyBinding3 = this.mc.options.attackKey;
            Intrinsics.checkNotNullExpressionValue((Object)keyBinding3, "keyAttack");
            actionType = this.isKeyDown(keyBinding3) ? SlotActionType.QUICK_MOVE : null;
        }
        KeyBinding keyBinding4 = this.mc.options.sneakKey;
        Intrinsics.checkNotNullExpressionValue((Object)keyBinding4, (String)"keyShift");
        if (this.isKeyDown(keyBinding4)) {
            int slotId;
            KeyBinding keyBinding5 = this.mc.options.sprintKey;
            Intrinsics.checkNotNullExpressionValue((Object)keyBinding5, (String)"keySprint");
            if (!this.isKeyDown(keyBinding5) && hoverSlot != null && hoverSlot.hasStack() && actionType != null && this.stopWatch.every(this.scrollDelay.getValue()) && (slotId = this.menuSlotId(hoverSlot)) != -1) {
                gameMode.clickSlot(player.currentScreenHandler.syncId, slotId, actionType == SlotActionType.THROW ? 1 : 0, actionType, (PlayerEntity)player);
            }
        }
    }

    @EventHandler
    public final void onClickSlot(@NotNull ClickSlotEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        ClientPlayerInteractionManager clientPlayerInteractionManager2 = this.mc.interactionManager;
        if (clientPlayerInteractionManager2 == null) {
            return;
        }
        ClientPlayerInteractionManager gameMode = clientPlayerInteractionManager2;
        int slotId = event.getSlotId();
        if (slotId < 0 || slotId >= player.currentScreenHandler.slots.size()) {
            return;
        }
        Slot slot2 = player.currentScreenHandler.getSlot(slotId);
        Intrinsics.checkNotNullExpressionValue((Object)slot2, (String)"getSlot(...)");
        Slot slot = slot2;
        if (!slot.hasStack()) {
            return;
        }
        Item item2 = slot.getStack().getItem();
        Intrinsics.checkNotNullExpressionValue((Object)item2, (String)"getItem(...)");
        Item item = item2;
        KeyBinding keyBinding2 = this.mc.options.sneakKey;
        Intrinsics.checkNotNullExpressionValue((Object)keyBinding2, (String)"keyShift");
        if (this.isKeyDown(keyBinding2)) {
            KeyBinding keyBinding3 = this.mc.options.sprintKey;
            Intrinsics.checkNotNullExpressionValue((Object)keyBinding3, "keySprint");
            if (this.isKeyDown(keyBinding3) && this.stopWatch.every(50.0)) {
                for (Slot s : player.currentScreenHandler.slots) {
                    if (s.hasStack() && s.getStack().getItem() == item && s.inventory == slot.inventory) {
                        int scrolledSlotId = this.menuSlotId(s);
                        if (scrolledSlotId != -1) {
                            gameMode.clickSlot(player.currentScreenHandler.syncId, scrolledSlotId, 1, event.getActionType(), (PlayerEntity)player);
                        }
                    }
                }
            }
        }
    }

    private final boolean isKeyDown(KeyBinding key) {
        Window window2 = this.mc.getWindow();
        if (window2 == null) {
            return false;
        }
        Window window = window2;
        InputUtil.Key key2 = key.getDefaultKey();
        Intrinsics.checkNotNullExpressionValue((Object)key2, (String)"getDefaultKey(...)");
        InputUtil.Key bound = key2;
        int code = bound.getCode();
        if (code == -1) {
            return false;
        }
        return switch (WhenMappings.$EnumSwitchMapping$0[bound.getCategory().ordinal()]) {
            case 1 -> {
                if (GLFW.glfwGetKey((long)window.getHandle(), (int)code) == 1) {
                    yield true;
                }
                yield false;
            }
            case 2 -> {
                if (GLFW.glfwGetMouseButton((long)window.getHandle(), (int)code) == 1) {
                    yield true;
                }
                yield false;
            }
            default -> false;
        };
    }

    private final int menuSlotId(Slot slot) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return -1;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        if (slot == null || player.currentScreenHandler == null) {
            return -1;
        }
        DefaultedList defaultedList2 = player.currentScreenHandler.slots;
        Intrinsics.checkNotNullExpressionValue((Object)defaultedList2, (String)"slots");
        DefaultedList slots = defaultedList2;
        int n = ((Collection)slots).size();
        for (int i = 0; i < n; ++i) {
            if (!Intrinsics.areEqual((Object)slots.get(i), (Object)slot)) continue;
            return i;
        }
        return slot.id;
    }

    private static final boolean onClickSlot$lambda$0(Item $item, Slot $slot, Slot scrolledSlot) {
        return scrolledSlot.hasStack() && Intrinsics.areEqual((Object)scrolledSlot.getStack().getItem(), (Object)$item) && Intrinsics.areEqual((Object)scrolledSlot.inventory, (Object)$slot.inventory);
    }

    private static final boolean onClickSlot$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final Unit onClickSlot$lambda$2(ItemScroller this$0, ClientPlayerInteractionManager $gameMode, ClientPlayerEntity $player, ClickSlotEvent $event, Slot scrolledSlot) {
        int scrolledSlotId = this$0.menuSlotId(scrolledSlot);
        if (scrolledSlotId != -1) {
            $gameMode.clickSlot($player.currentScreenHandler.syncId, scrolledSlotId, 1, $event.getActionType(), (PlayerEntity)$player);
        }
        return Unit.INSTANCE;
    }

    private static final void onClickSlot$lambda$3(Function1 $tmp0, Object p0) {
        $tmp0.invoke(p0);
    }

    @Metadata(mv={2, 4, 0}, k=3, xi=48)
    public static final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] nArray = new int[InputUtil.Type.values().length];
            try {
                nArray[InputUtil.Type.KEYSYM.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[InputUtil.Type.MOUSE.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
        }
    }
}

