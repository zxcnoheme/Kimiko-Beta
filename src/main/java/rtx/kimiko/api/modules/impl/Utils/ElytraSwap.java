/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.gui.screen.ingame.InventoryScreen
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.restrict.Server;
import rtx.kimiko.api.modules.restrict.ServerRule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.utils.chat.ChatMessage;
import rtx.kimiko.utils.inventory.ElytraSwapper;
import rtx.kimiko.utils.inventory.InventoryClicks;
import rtx.kimiko.utils.inventory.InventorySequence;
import rtx.kimiko.utils.time.StopWatch;

@ServerRule(mode=ServerRule.Mode.BLOCK, servers={Server.RW, Server.ST, Server.WM})
@Feature(value={"elytraswap"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u001b\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0003b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000b\u0010\u0003J\u000f\u0010\f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\f\u0010\u0003J\u0017\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0003R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001c\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 \u00ca\u0001.\b\"\u0012\n\b#\u0012\u0006\b\n0$8%\u0012\u001e\b&\u0012\u001a\b\fJ\u0006\b\n0'8(J\u0006\b\n0'8)J\u0006\b\n0'8*\u00ca\u0001\u0010\b+\u0012\f\b,\u0012\b\b\fJ\u0004\b\b(-\u00a8\u0006."}, d2={"Lrtx/kimiko/api/modules/impl/Utils/ElytraSwap;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onDisable", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "triggerSwap", "openInventory", "", "slotId", "clickInInventory", "(I)V", "closeInventory", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "swapKey", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "Lrtx/kimiko/utils/inventory/InventorySequence;", "sequence", "Lrtx/kimiko/utils/inventory/InventorySequence;", "Lrtx/kimiko/utils/time/StopWatch;", "cooldown", "Lrtx/kimiko/utils/time/StopWatch;", "", "lastDown", "Z", "weOpened", "targetSlot", "I", "Companion", "Lrtx/kimiko/api/modules/restrict/ServerRule;", "mode", "Lrtx/kimiko/api/modules/restrict/ServerRule$Mode;", "BLOCK", "servers", "Lrtx/kimiko/api/modules/restrict/Server;", "RW", "ST", "WM", "Lrtx/kimiko/api/liteapi/Feature;", "value", "elytraswap", "rtx.kimiko:kimiko"})
public final class ElytraSwap
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final BindSetting swapKey = (BindSetting)this.register((Setting)new BindSetting("Клавиша свапа", "Открывает инвентарь, меняет элитру на нагрудник и закрывает.").setKey(-1));
    @NotNull
    private final InventorySequence sequence = new InventorySequence();
    @NotNull
    private final StopWatch cooldown = new StopWatch();
    private boolean lastDown;
    private boolean weOpened;
    private int targetSlot = -1;
    private static final double COOLDOWN_MS = 350.0;
    private static final long OPEN_DELAY_MS = 70L;
    private static final long CLICK_DELAY_MS = 70L;
    private static final long CLOSE_DELAY_MS = 70L;

    public ElytraSwap() {
        super("Elytra Swap", "Легитный свап элитры на нагрудник через открытие инвентаря.", Category.UTILS);
    }

    @Override
    protected void onDisable() {
        this.sequence.cancel();
        if (this.weOpened) {
            this.closeInventory();
        }
        this.targetSlot = -1;
        this.lastDown = false;
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        boolean canStart;
        if (!event.isPre()) {
            return;
        }
        this.sequence.tick();
        if (this.mc.player == null || this.mc.world == null || this.mc.getWindow() == null) {
            this.lastDown = false;
            return;
        }
        boolean down = this.swapKey.isBound() && this.swapKey.getValue().isDown(this.mc.getWindow().getHandle());
        boolean bl = canStart = !this.sequence.isRunning() && this.mc.currentScreen == null && this.cooldown.finished(350.0);
        if (down && !this.lastDown && canStart) {
            this.triggerSwap();
            this.cooldown.reset();
        }
        this.lastDown = down;
    }

    private final void triggerSwap() {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        if (!Intrinsics.areEqual((Object)player.currentScreenHandler, (Object)player.playerScreenHandler)) {
            player.closeHandledScreen();
        }
        this.targetSlot = ElytraSwapper.findTargetSlot$default(false, 1, null);
        if (this.targetSlot == -1) {
            if (ElytraSwapper.chestLocked()) {
                ChatMessage.brandmessage(I18n.tr("Нагрудник нельзя снять — проклятие несъёмности."));
            } else if (ElytraSwapper.findTargetSlot(true) == -1) {
                ChatMessage.brandmessage(I18n.tr("В инвентаре нет второго нагрудника или элитры для свапа."));
            } else {
                ChatMessage.brandmessage(I18n.tr("Слот брони занят — свап невозможен."));
            }
            return;
        }
        this.sequence.cancel();
        this.sequence.then(() -> ElytraSwap.triggerSwap$lambda$0(this)).thenAfter(70L, () -> ElytraSwap.triggerSwap$lambda$1(this)).thenAfter(70L, () -> ElytraSwap.triggerSwap$lambda$2(this)).thenAfter(70L, () -> ElytraSwap.triggerSwap$lambda$3(this)).thenAfter(70L, () -> ElytraSwap.triggerSwap$lambda$4(this)).start();
    }

    private final void openInventory() {
        ClientPlayerEntity player = this.mc.player;
        if (player != null && this.mc.currentScreen == null) {
            this.mc.setScreen((Screen)new InventoryScreen((PlayerEntity)player));
            this.weOpened = true;
        }
    }

    private final void clickInInventory(int slotId) {
        if (!(this.mc.currentScreen instanceof InventoryScreen)) {
            this.sequence.cancel();
            this.weOpened = false;
            this.targetSlot = -1;
            ChatMessage.brandmessage(I18n.tr("Свап прерван — инвентарь закрылся."));
            return;
        }
        InventoryClicks.pickup(slotId);
    }

    private final void closeInventory() {
        ClientPlayerEntity player = this.mc.player;
        if (player != null && this.mc.currentScreen instanceof InventoryScreen) {
            player.closeHandledScreen();
            this.mc.setScreen(null);
        }
        this.weOpened = false;
        this.targetSlot = -1;
    }

    private static final void triggerSwap$lambda$0(ElytraSwap this$0) {
        this$0.openInventory();
    }

    private static final void triggerSwap$lambda$1(ElytraSwap this$0) {
        this$0.clickInInventory(6);
    }

    private static final void triggerSwap$lambda$2(ElytraSwap this$0) {
        this$0.clickInInventory(this$0.targetSlot);
    }

    private static final void triggerSwap$lambda$3(ElytraSwap this$0) {
        this$0.clickInInventory(6);
    }

    private static final void triggerSwap$lambda$4(ElytraSwap this$0) {
        this$0.closeInventory();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\tR\u0014\u0010\u000b\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\t\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/ElytraSwap.Companion;", "", "<init>", "()V", "", "COOLDOWN_MS", "D", "", "OPEN_DELAY_MS", "J", "CLICK_DELAY_MS", "CLOSE_DELAY_MS", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

