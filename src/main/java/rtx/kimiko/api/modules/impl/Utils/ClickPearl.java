/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.util.Window
 *  net.minecraft.util.Hand
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.item.Items
 *  net.minecraft.client.network.ClientPlayerInteractionManager
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Utils;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mixin.accessor.MultiPlayerGameModeAccessor;
import net.minecraft.client.util.Window;
import net.minecraft.util.Hand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.client.network.ClientPlayerInteractionManager;
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

@ServerRule(mode=ServerRule.Mode.ONLY, servers={Server.RW})
@Feature(value={"clickpearl"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 %2\u00020\u0001:\u0002&%B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u001b\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000b\u0010\u0003J\u000f\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0003R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001c\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b!\u0010 R\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010$\u00ca\u0001\u001e\b'\u0012\n\b(\u0012\u0006\b\n0)8*\u0012\u000e\b+\u0012\n\b\fJ\u0006\b\n0,8-\u00ca\u0001\u0010\b.\u0012\f\b/\u0012\b\b\fJ\u0004\b\b(0\u00a8\u00061"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/ClickPearl;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onDisable", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onPreTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "processThrow", "", "bindJustPressed", "()Z", "", "findPearlInHotbar", "()I", "slot", "selectHotbarSlot", "(I)V", "resetState", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "keySetting", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "lastBindDown", "Z", "", "lastUseTime", "J", "actionTimer", "previousSlot", "I", "pearlSlot", "Lrtx/kimiko/api/modules/impl/Utils/ClickPearl$State;", "state", "Lrtx/kimiko/api/modules/impl/Utils/ClickPearl$State;", "Companion", "State", "Lrtx/kimiko/api/modules/restrict/ServerRule;", "mode", "Lrtx/kimiko/api/modules/restrict/ServerRule$Mode;", "ONLY", "servers", "Lrtx/kimiko/api/modules/restrict/Server;", "RW", "Lrtx/kimiko/api/liteapi/Feature;", "value", "clickpearl", "rtx.kimiko:kimiko"})
public final class ClickPearl
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final BindSetting keySetting = (BindSetting)this.register((Setting)new BindSetting("Клавиша", "Клавиша для броска эндер-жемчуга.").setKey(-1));
    private boolean lastBindDown;
    private long lastUseTime;
    private long actionTimer;
    private int previousSlot = -1;
    private int pearlSlot = -1;
    @NotNull
    private State state = State.IDLE;
    private static final long SWAP_DELAY_MS = 50L;
    private static final long RESTORE_DELAY_MS = 50L;
    private static final long COOLDOWN_MS = 200L;

    public ClickPearl() {
        super("Click Pearl", "Бросает эндер-жемчуг из хотбара по нажатию клавиши.", Category.UTILS);
    }

    @Override
    protected void onDisable() {
        this.lastBindDown = false;
        this.resetState();
    }

    @EventHandler
    public final void onPreTick(@NotNull TickEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPre()) {
            return;
        }
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        if (this.mc.world == null) {
            this.resetState();
            this.lastBindDown = false;
            return;
        }
        if (this.state != State.IDLE) {
            this.processThrow();
            return;
        }
        if (!this.bindJustPressed() || this.mc.currentScreen != null) {
            return;
        }
        if (System.currentTimeMillis() - this.lastUseTime < 200L) {
            return;
        }
        if (player.getItemCooldownManager().isCoolingDown(Items.ENDER_PEARL.getDefaultStack())) {
            return;
        }
        int slot = this.findPearlInHotbar();
        if (slot == -1) {
            ChatMessage.brandmessage(I18n.tr("Эндер-жемчуг не найден в хотбаре."));
            return;
        }
        this.previousSlot = player.getInventory().getSelectedSlot();
        this.pearlSlot = slot;
        this.state = State.WAIT_BEFORE_SWAP;
        this.actionTimer = System.currentTimeMillis();
    }

    private final void processThrow() {
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
        switch (WhenMappings.$EnumSwitchMapping$0[this.state.ordinal()]) {
            case 1: {
                if (System.currentTimeMillis() - this.actionTimer < 50L) {
                    return;
                }
                this.selectHotbarSlot(this.pearlSlot);
                gameMode.interactItem((PlayerEntity)player, Hand.MAIN_HAND);
                player.swingHand(Hand.MAIN_HAND);
                this.lastUseTime = System.currentTimeMillis();
                this.state = State.WAIT_BEFORE_RESTORE;
                this.actionTimer = System.currentTimeMillis();
                break;
            }
            case 2: {
                if (System.currentTimeMillis() - this.actionTimer < 50L) {
                    return;
                }
                if (this.previousSlot != -1) {
                    this.selectHotbarSlot(this.previousSlot);
                }
                this.resetState();
                break;
            }
            default: {
                this.resetState();
            }
        }
    }

    private final boolean bindJustPressed() {
        Window window2 = this.mc.getWindow();
        Intrinsics.checkNotNullExpressionValue((Object)window2, (String)"getWindow(...)");
        Window window = window2;
        if (this.mc.currentScreen != null) {
            this.lastBindDown = false;
            return false;
        }
        boolean down = this.keySetting.getValue().isDown(window.getHandle());
        boolean pressed = down && !this.lastBindDown;
        this.lastBindDown = down;
        return pressed;
    }

    private final int findPearlInHotbar() {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return -1;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        for (int i = 0; i < 9; ++i) {
            if (!Intrinsics.areEqual((Object)player.getInventory().getStack(i).getItem(), (Object)Items.ENDER_PEARL)) continue;
            return i;
        }
        return -1;
    }

    private final void selectHotbarSlot(int slot) {
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
        if (slot < 0 || slot > 8) {
            return;
        }
        if (player.getInventory().getSelectedSlot() == slot) {
            return;
        }
        player.getInventory().setSelectedSlot(slot);
        ((MultiPlayerGameModeAccessor)gameMode).kimiko$ensureHasSentCarriedItem();
    }

    private final void resetState() {
        this.state = State.IDLE;
        this.actionTimer = 0L;
        this.previousSlot = -1;
        this.pearlSlot = -1;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/ClickPearl.Companion;", "", "<init>", "()V", "", "SWAP_DELAY_MS", "J", "RESTORE_DELAY_MS", "COOLDOWN_MS", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/ClickPearl$State;", "", "<init>", "(Ljava/lang/String;I)V", "IDLE", "WAIT_BEFORE_SWAP", "WAIT_BEFORE_RESTORE", "rtx.kimiko:kimiko"})
    private static enum State {
        IDLE,
        WAIT_BEFORE_SWAP,
        WAIT_BEFORE_RESTORE;
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
                nArray[State.WAIT_BEFORE_SWAP.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[State.WAIT_BEFORE_RESTORE.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
        }
    }
}

