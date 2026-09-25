/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.screen.ScreenHandler
 *  net.minecraft.screen.slot.SlotActionType
 *  net.minecraft.client.network.ClientPlayerInteractionManager
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Utils;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.restrict.Server;
import rtx.kimiko.api.modules.restrict.ServerRule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.utils.time.StopWatch;
import sigil.protect.Level;
import sigil.protect.Protect;

@ServerRule(mode=ServerRule.Mode.BLOCK, servers={Server.ST})
@Feature(value={"autoresell"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 %2\u00020\u0001:\u0002&%B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003J\u001f\u0010\n\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\n\u0010\u0003J\u001b\u0010\u000e\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0003b\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u001f\u0010\u0012\u001a\u00020\u0004H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0003J\u000f\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0003R\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010 R\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010$\u00ca\u0001\u001e\b'\u0012\n\b\u001c\u0012\u0006\b\n0(8)\u0012\u000e\b*\u0012\n\b\fJ\u0006\b\n0+8,\u00ca\u0001\u0010\b-\u0012\f\b\u0006\u0012\b\b\fJ\u0004\b\b(.\u00a8\u0006/"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/AutoResell;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onEnable", "onDisable", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "tickCommand", "STD", "tickAuction", "", "isMenuOpen", "()Z", "", "slot", "clickSlot", "(I)V", "endCycle", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "mode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/utils/time/StopWatch;", "cycleTimer", "Lrtx/kimiko/utils/time/StopWatch;", "stepTimer", "Lrtx/kimiko/api/modules/impl/Utils/AutoResell$State;", "state", "Lrtx/kimiko/api/modules/impl/Utils/AutoResell$State;", "Companion", "State", "Lrtx/kimiko/api/modules/restrict/ServerRule;", "Lrtx/kimiko/api/modules/restrict/ServerRule$Mode;", "BLOCK", "servers", "Lrtx/kimiko/api/modules/restrict/Server;", "ST", "Lrtx/kimiko/api/liteapi/Feature;", "autoresell", "rtx.kimiko:kimiko"})
public final class AutoResell
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ModeSetting mode;
    @NotNull
    private final StopWatch cycleTimer;
    @NotNull
    private final StopWatch stepTimer;
    @NotNull
    private State state;
    private static final double CYCLE_MS = 65000.0;
    private static final double CLICK_MS = 500.0;
    private static final int STORAGE_SLOT = 46;
    private static final int RESELL_SLOT = 52;

    public AutoResell() {
        super("Auto Resell", "Перевыставляет предметы на аукционе.", Category.UTILS);
        String[] stringArray = new String[]{"Команда", "Аукцион"};
        this.mode = (ModeSetting)this.register((Setting)new ModeSetting("Режим", "Способ перевыставления.", "Команда", stringArray));
        this.cycleTimer = new StopWatch();
        this.stepTimer = new StopWatch();
        this.state = State.IDLE;
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onEnable() {
        this.state = State.IDLE;
        this.cycleTimer.setMs(65000L);
        this.stepTimer.reset();
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        this.state = State.IDLE;
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        if (!event.isPre()) {
            return;
        }
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        if (player.networkHandler == null || this.mc.interactionManager == null) {
            this.state = State.IDLE;
            return;
        }
        if (Intrinsics.areEqual((Object)this.mode.getValue(), (Object)"Команда")) {
            this.tickCommand();
        } else {
            this.tickAuction();
        }
    }

    private final void tickCommand() {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        if (!this.cycleTimer.finished(65000.0)) {
            return;
        }
        player.networkHandler.sendChatCommand("ah resell");
        this.cycleTimer.reset();
    }

    @Protect(value=Level.STD)
    private final void tickAuction() {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        switch (WhenMappings.$EnumSwitchMapping$0[this.state.ordinal()]) {
            case 1: {
                if (!this.cycleTimer.finished(65000.0)) {
                    return;
                }
                player.networkHandler.sendChatCommand("ah");
                this.state = State.WAIT_MENU;
                this.stepTimer.reset();
                break;
            }
            case 2: {
                if (!this.isMenuOpen()) {
                    if (this.stepTimer.finished(3000.0)) {
                        this.endCycle();
                    }
                    return;
                }
                if (!this.stepTimer.finished(500.0)) {
                    return;
                }
                this.clickSlot(46);
                this.state = State.WAIT_STORAGE;
                this.stepTimer.reset();
                break;
            }
            case 3: {
                if (!this.isMenuOpen()) {
                    this.endCycle();
                    return;
                }
                if (!this.stepTimer.finished(500.0)) {
                    return;
                }
                this.clickSlot(52);
                this.state = State.WAIT_RESELL;
                this.stepTimer.reset();
                break;
            }
            case 4: {
                if (!this.stepTimer.finished(500.0)) {
                    return;
                }
                player.closeHandledScreen();
                this.endCycle();
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    private final boolean isMenuOpen() {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return false;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        ScreenHandler screenHandler2 = player.currentScreenHandler;
        if (screenHandler2 == null) {
            return false;
        }
        ScreenHandler menu = screenHandler2;
        return menu.syncId != 0;
    }

    private final void clickSlot(int slot) {
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
        gameMode.clickSlot(player.currentScreenHandler.syncId, slot, 0, SlotActionType.PICKUP, (PlayerEntity)player);
    }

    private final void endCycle() {
        this.state = State.IDLE;
        this.cycleTimer.reset();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\n\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/AutoResell.Companion;", "", "<init>", "()V", "", "CYCLE_MS", "D", "CLICK_MS", "", "STORAGE_SLOT", "I", "RESELL_SLOT", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/AutoResell$State;", "", "<init>", "(Ljava/lang/String;I)V", "IDLE", "WAIT_MENU", "WAIT_STORAGE", "WAIT_RESELL", "rtx.kimiko:kimiko"})
    private static enum State {
        IDLE,
        WAIT_MENU,
        WAIT_STORAGE,
        WAIT_RESELL;
@NotNull
        public static EnumEntries<State> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }

    @Metadata(mv={2, 4, 0}, k=3, xi=48)
    public static final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] nArray = new int[State.values().length];
            try {
                nArray[State.IDLE.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[State.WAIT_MENU.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[State.WAIT_STORAGE.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[State.WAIT_RESELL.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
        }
    }
}

