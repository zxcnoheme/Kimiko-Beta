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
 *  net.minecraft.util.Formatting
 *  net.minecraft.item.ItemStack
 *  net.minecraft.text.Text
 *  net.minecraft.client.option.KeyBinding
 *  net.minecraft.client.util.InputUtil
 *  net.minecraft.client.util.InputUtil.Key
 *  net.minecraft.client.util.InputUtil.Type
 *  net.minecraft.text.MutableText
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.glfw.GLFW
 */
package rtx.kimiko.api.modules.impl.Utils;

import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mixin.accessor.KeyMappingAccessor;
import net.minecraft.client.util.Window;
import net.minecraft.util.Formatting;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.MutableText;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;
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
import rtx.kimiko.api.modules.settings.impl.SelectSetting;
import rtx.kimiko.utils.chat.ChatMessage;
import rtx.kimiko.utils.inventory.HotbarSwapper;
import rtx.kimiko.utils.inventory.InventoryItems;

@ServerRule(mode=ServerRule.Mode.BLOCK, servers={Server.RW, Server.ST, Server.WM})
@Feature(value={"autoswap"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 /2\u00020\u0001:\u00020/B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u001b\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0003b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000b\u0010\u0003J\u000f\u0010\f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\f\u0010\u0003J\u000f\u0010\r\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\r\u0010\u0003J\u000f\u0010\u000e\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u0003J\u0017\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0003J\u0015\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u001d\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u001c\u001a\u00020\u001bH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010!R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010&\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010)\u001a\u00020(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010,\u001a\u00020+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010.\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010'\u00ca\u0001.\b1\u0012\n\b2\u0012\u0006\b\n0384\u0012\u001e\b5\u0012\u001a\b\fJ\u0006\b\n0687J\u0006\b\n0688J\u0006\b\n0689\u00ca\u0001\u0010\b:\u0012\f\b;\u0012\b\b\fJ\u0004\b\b(<\u00a8\u0006="}, d2={"Lrtx/kimiko/api/modules/impl/Utils/AutoSwap;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onDisable", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "beginSwap", "processSwap", "maintainMovementStop", "restoreMovement", "Lnet/minecraft/KeyBinding;", "key", "resync", "(Lnet/minecraft/KeyBinding;)V", "", "isPhysicallyDown", "(Lnet/minecraft/KeyBinding;)Z", "resetState", "Ljava/util/function/Predicate;", "Lnet/minecraft/ItemStack;", "resolveTarget", "()Ljava/util/function/Predicate;", "", "selected", "predicateFor", "(Ljava/lang/String;)Ljava/util/function/Predicate;", "Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "firstItem", "Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "secondItem", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "bind", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "lastDown", "Z", "Lrtx/kimiko/api/modules/impl/Utils/AutoSwap$State;", "state", "Lrtx/kimiko/api/modules/impl/Utils/AutoSwap$State;", "", "stateTimer", "J", "keysOverridden", "Companion", "State", "Lrtx/kimiko/api/modules/restrict/ServerRule;", "mode", "Lrtx/kimiko/api/modules/restrict/ServerRule$Mode;", "BLOCK", "servers", "Lrtx/kimiko/api/modules/restrict/Server;", "RW", "ST", "WM", "Lrtx/kimiko/api/liteapi/Feature;", "value", "autoswap", "rtx.kimiko:kimiko"})
public final class AutoSwap
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SelectSetting firstItem;
    @NotNull
    private final SelectSetting secondItem;
    @NotNull
    private final BindSetting bind;
    private boolean lastDown;
    @NotNull
    private State state;
    private long stateTimer;
    private boolean keysOverridden;
    @NotNull
    private static final String TOTEM = "Тотем";
    @NotNull
    private static final String SPHERE = "Сфера";
    private static final long PRE_SWAP_STOP_MS = 50L;
    private static final long POST_SWAP_STOP_MS = 60L;

    public AutoSwap() {
        super("Auto Swap", "Свапает боевой предмет в оффхенд по бинду одним кликом, без открытия инвентаря.", Category.UTILS);
        String[] stringArray = new String[]{TOTEM, SPHERE};
        this.firstItem = (SelectSetting)this.register((Setting)new SelectSetting("Основной предмет", "Выберите первый предмет для обмена.").value(stringArray).selected(TOTEM));
        stringArray = new String[]{TOTEM, SPHERE};
        this.secondItem = (SelectSetting)this.register((Setting)new SelectSetting("Вторичный предмет", "Выберите второй предмет для обмена.").value(stringArray).selected(SPHERE));
        this.bind = (BindSetting)this.register((Setting)new BindSetting("Кнопка свапа", "Свапает предмет в оффхенд при нажатии.").setKey(-1));
        this.state = State.IDLE;
    }

    @Override
    protected void onDisable() {
        this.restoreMovement();
        this.resetState();
        this.lastDown = false;
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        boolean down;
        if (!event.isPre()) {
            return;
        }
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        if (this.mc.world == null || this.mc.getWindow() == null || this.mc.currentScreen != null) {
            this.restoreMovement();
            this.resetState();
            this.lastDown = false;
            return;
        }
        if (this.state != State.IDLE) {
            this.maintainMovementStop();
            this.processSwap();
            return;
        }
        boolean bl = down = this.bind.isBound() && this.bind.getValue().isDown(this.mc.getWindow().getHandle());
        if (down && !this.lastDown) {
            this.beginSwap();
        }
        this.lastDown = down;
    }

    private final void beginSwap() {
        if (!HotbarSwapper.has(this.predicateFor(this.firstItem.getValue())) && !HotbarSwapper.has(this.predicateFor(this.secondItem.getValue()))) {
            return;
        }
        this.keysOverridden = true;
        this.maintainMovementStop();
        this.state = State.WAIT_BEFORE_SWAP;
        this.stateTimer = System.currentTimeMillis();
    }

    private final void processSwap() {
        switch (WhenMappings.$EnumSwitchMapping$0[this.state.ordinal()]) {
            case 1: {
                if (System.currentTimeMillis() - this.stateTimer < 50L) {
                    return;
                }
                Predicate<ItemStack> target = this.resolveTarget();
                ItemStack targetStack = HotbarSwapper.find(target);
                if (HotbarSwapper.swapToOffhand(target) && !targetStack.isEmpty()) {
                    MutableText mutableText2 = Text.literal((String)I18n.tr("Auto Swap свапнул на ")).formatted(Formatting.WHITE).append((Text)targetStack.getName().copy().formatted(targetStack.getRarity().getFormatting()));
                    Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"append(...)");
                    ChatMessage.brandmessage((Text)mutableText2);
                }
                this.state = State.WAIT_AFTER_SWAP;
                this.stateTimer = System.currentTimeMillis();
                break;
            }
            case 2: {
                if (System.currentTimeMillis() - this.stateTimer < 60L) {
                    return;
                }
                this.restoreMovement();
                this.resetState();
            }
        }
    }

    private final void maintainMovementStop() {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        this.mc.options.forwardKey.setPressed(false);
        this.mc.options.backKey.setPressed(false);
        this.mc.options.leftKey.setPressed(false);
        this.mc.options.rightKey.setPressed(false);
        this.mc.options.jumpKey.setPressed(false);
        this.mc.options.sprintKey.setPressed(false);
        if (player.isSprinting()) {
            player.setSprinting(false);
        }
    }

    private final void restoreMovement() {
        if (!this.keysOverridden) {
            return;
        }
        KeyBinding keyBinding2 = this.mc.options.forwardKey;
        Intrinsics.checkNotNullExpressionValue((Object)keyBinding2, (String)"keyUp");
        this.resync(keyBinding2);
        KeyBinding keyBinding3 = this.mc.options.backKey;
        Intrinsics.checkNotNullExpressionValue((Object)keyBinding3, (String)"keyDown");
        this.resync(keyBinding3);
        KeyBinding keyBinding4 = this.mc.options.leftKey;
        Intrinsics.checkNotNullExpressionValue((Object)keyBinding4, (String)"keyLeft");
        this.resync(keyBinding4);
        KeyBinding keyBinding5 = this.mc.options.rightKey;
        Intrinsics.checkNotNullExpressionValue((Object)keyBinding5, (String)"keyRight");
        this.resync(keyBinding5);
        KeyBinding keyBinding6 = this.mc.options.jumpKey;
        Intrinsics.checkNotNullExpressionValue((Object)keyBinding6, (String)"keyJump");
        this.resync(keyBinding6);
        KeyBinding keyBinding7 = this.mc.options.sprintKey;
        Intrinsics.checkNotNullExpressionValue((Object)keyBinding7, (String)"keySprint");
        this.resync(keyBinding7);
        this.keysOverridden = false;
    }

    private final void resync(KeyBinding key) {
        key.setPressed(this.isPhysicallyDown(key));
    }

    private final boolean isPhysicallyDown(KeyBinding key) {
        boolean bl;
        Window window2 = this.mc.getWindow();
        if (window2 == null) {
            return false;
        }
        Window window = window2;
        try {
            Intrinsics.checkNotNull((Object)key, (String)"null cannot be cast to non-null type mixin.accessor.KeyMappingAccessor");
            InputUtil.Key bound = ((KeyMappingAccessor)key).kimiko$getBoundKey();
            bl = bound.getCategory() == InputUtil.Type.MOUSE ? GLFW.glfwGetMouseButton((long)window.getHandle(), (int)bound.getCode()) == 1 : InputUtil.isKeyPressed((Window)window, (int)bound.getCode());
        }
        catch (Throwable t) {
            bl = false;
        }
        return bl;
    }

    private final void resetState() {
        this.state = State.IDLE;
        this.stateTimer = 0L;
    }

    private final Predicate<ItemStack> resolveTarget() {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return this.predicateFor(this.firstItem.getValue());
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        Predicate<ItemStack> first = this.predicateFor(this.firstItem.getValue());
        Predicate<ItemStack> second = this.predicateFor(this.secondItem.getValue());
        ItemStack itemStack2 = player.getOffHandStack();
        Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"getOffhandItem(...)");
        ItemStack offhand = itemStack2;
        if (HotbarSwapper.has(first) && !first.test(offhand)) {
            return first;
        }
        return second;
    }

    private final Predicate<ItemStack> predicateFor(String selected) {
        return Intrinsics.areEqual((Object)SPHERE, (Object)selected) ? AutoSwap::predicateFor$lambda$0 : AutoSwap::predicateFor$lambda$1;
    }

    private static final boolean predicateFor$lambda$0(ItemStack it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return InventoryItems.isPlayerHead(it);
    }

    private static final boolean predicateFor$lambda$1(ItemStack it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return InventoryItems.isEnchantedTotem(it);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\n\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/AutoSwap.Companion;", "", "<init>", "()V", "", "TOTEM", "Ljava/lang/String;", "SPHERE", "", "PRE_SWAP_STOP_MS", "J", "POST_SWAP_STOP_MS", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/AutoSwap$State;", "", "<init>", "(Ljava/lang/String;I)V", "IDLE", "WAIT_BEFORE_SWAP", "WAIT_AFTER_SWAP", "rtx.kimiko:kimiko"})
    private static enum State {
        IDLE,
        WAIT_BEFORE_SWAP,
        WAIT_AFTER_SWAP;
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
                nArray[State.WAIT_AFTER_SWAP.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
        }
    }
}

