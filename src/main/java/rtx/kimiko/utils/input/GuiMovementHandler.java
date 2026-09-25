/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.util.Window
 *  net.minecraft.client.option.KeyBinding
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.option.GameOptions
 *  net.minecraft.client.util.InputUtil
 *  net.minecraft.client.util.InputUtil.Key
 *  net.minecraft.client.util.InputUtil.Type
 *  net.minecraft.client.gui.screen.ChatScreen
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.glfw.GLFW
 */
package rtx.kimiko.utils.input;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.util.Window;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.gui.screen.ChatScreen;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0003b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\r\u0010\u0003J\u000f\u0010\u000e\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000e\u0010\fJ\u000f\u0010\u000f\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0003J\u000f\u0010\u0010\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u0019\u0010\u0013\u001a\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/utils/input/GuiMovementHandler;", "", "<init>", "()V", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "", "canHandleScreen", "()Z", "finishHandledScreen", "canSyncInput", "syncMovementKeys", "releaseMovementKeys", "Lnet/minecraft/KeyBinding;", "key", "isPressed", "(Lnet/minecraft/KeyBinding;)Z", "Lnet/minecraft/MinecraftClient;", "mc", "Lnet/minecraft/MinecraftClient;", "handledScreen", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class GuiMovementHandler {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final MinecraftClient mc;
    private boolean handledScreen;
    @NotNull
    private static final GuiMovementHandler INSTANCE = new GuiMovementHandler();

    private GuiMovementHandler() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        this.mc = minecraftClient2;
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        if (!event.isPre()) {
            return;
        }
        if (!this.canHandleScreen()) {
            this.finishHandledScreen();
            return;
        }
        this.handledScreen = true;
        this.syncMovementKeys();
    }

    private final boolean canHandleScreen() {
        return this.mc.player != null && this.mc.world != null && this.mc.options != null && this.mc.getWindow() != null && this.mc.currentScreen != null && !(this.mc.currentScreen instanceof ChatScreen);
    }

    private final void finishHandledScreen() {
        if (!this.handledScreen) {
            this.handledScreen = false;
            return;
        }
        if (this.canSyncInput()) {
            this.syncMovementKeys();
        } else {
            this.releaseMovementKeys();
        }
        this.handledScreen = false;
    }

    private final boolean canSyncInput() {
        return this.mc.player != null && this.mc.world != null && this.mc.options != null && this.mc.getWindow() != null && this.mc.currentScreen == null;
    }

    private final void syncMovementKeys() {
        GameOptions gameOptions2 = this.mc.options;
        if (gameOptions2 == null) {
            return;
        }
        GameOptions options = gameOptions2;
        GuiMovementHandler.Companion.setDown(options.forwardKey, this.isPressed(options.forwardKey));
        GuiMovementHandler.Companion.setDown(options.backKey, this.isPressed(options.backKey));
        GuiMovementHandler.Companion.setDown(options.leftKey, this.isPressed(options.leftKey));
        GuiMovementHandler.Companion.setDown(options.rightKey, this.isPressed(options.rightKey));
        GuiMovementHandler.Companion.setDown(options.jumpKey, this.isPressed(options.jumpKey));
        GuiMovementHandler.Companion.setDown(options.sprintKey, this.isPressed(options.sprintKey));
        GuiMovementHandler.Companion.setDown(options.sneakKey, this.isPressed(options.sneakKey));
    }

    private final void releaseMovementKeys() {
        GameOptions gameOptions2 = this.mc.options;
        if (gameOptions2 == null) {
            return;
        }
        GameOptions options = gameOptions2;
        GuiMovementHandler.Companion.setDown(options.forwardKey, false);
        GuiMovementHandler.Companion.setDown(options.backKey, false);
        GuiMovementHandler.Companion.setDown(options.leftKey, false);
        GuiMovementHandler.Companion.setDown(options.rightKey, false);
        GuiMovementHandler.Companion.setDown(options.jumpKey, false);
        GuiMovementHandler.Companion.setDown(options.sprintKey, false);
        GuiMovementHandler.Companion.setDown(options.sneakKey, false);
    }

    private final boolean isPressed(KeyBinding key) {
        if (key == null || key.isUnbound()) {
            return false;
        }
        InputUtil.Key key2 = InputUtil.fromTranslationKey((String)key.getBoundKeyTranslationKey());
        Intrinsics.checkNotNullExpressionValue((Object)key2, (String)"getKey(...)");
        InputUtil.Key inputKey = key2;
        long handle = this.mc.getWindow().getHandle();
        return switch (WhenMappings.$EnumSwitchMapping$0[inputKey.getCategory().ordinal()]) {
            case 1 -> InputUtil.isKeyPressed((Window)this.mc.getWindow(), (int)inputKey.getCode());
            case 2 -> {
                if (GLFW.glfwGetMouseButton((long)handle, (int)inputKey.getCode()) == 1) {
                    yield true;
                }
                yield false;
            }
            default -> false;
        };
    }

    @JvmStatic
    public static final void init() {
        Companion.init();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J!\u0010\u000b\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/utils/input/GuiMovementHandler.Companion;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "init", "Lnet/minecraft/KeyBinding;", "key", "", "down", "setDown", "(Lnet/minecraft/KeyBinding;Z)V", "Lrtx/kimiko/utils/input/GuiMovementHandler;", "INSTANCE", "Lrtx/kimiko/utils/input/GuiMovementHandler;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final void init() {
            EventBus.Companion.get().subscribe(INSTANCE);
        }

        private final void setDown(KeyBinding key, boolean down) {
            block0: {
                KeyBinding keyBinding2 = key;
                if (keyBinding2 == null) break block0;
                keyBinding2.setPressed(down);
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
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

