/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.util.Window
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.glfw.GLFW
 */
package rtx.kimiko.utils.key;

import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.util.Window;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;
import rtx.kimiko.utils.key.InputType;
import rtx.kimiko.utils.key.KeyHelper;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 !2\u00020\u0001:\u0001!B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u0010\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0010\u0010\u0014J\u0015\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0018\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\r\u0010\u001d\u001a\u00020\u001c\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u001f\u0010\u0007R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010 \u00a8\u0006\""}, d2={"Lrtx/kimiko/utils/key/KeyBind;", "", "", "code", "<init>", "(I)V", "getCode", "()I", "", "isBound", "()Z", "Lrtx/kimiko/utils/key/InputType;", "getType", "()Lrtx/kimiko/utils/key/InputType;", "", "windowHandle", "isDown", "(J)Z", "Lnet/minecraft/Window;", "window", "(Lnet/minecraft/Window;)Z", "button", "matchesMouseButton", "(I)Z", "", "vertical", "matchesScroll", "(D)Z", "", "getDisplayName", "()Ljava/lang/String;", "toMouseButton", "I", "Companion", "rtx.kimiko:kimiko"})
public final class KeyBind {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final int code;
    public static final int SCROLL_UP = 1000;
    public static final int SCROLL_DOWN = 1001;
    public static final int MIDDLE_MOUSE = 1002;
    @JvmField
    @NotNull
    public static final KeyBind NONE = new KeyBind(-1);

    public KeyBind(int code) {
        this.code = code;
    }

    public final int getCode() {
        return this.code;
    }

    public final boolean isBound() {
        return this.code != -1;
    }

    @NotNull
    public final InputType getType() {
        if (!this.isBound()) {
            return InputType.NONE;
        }
        if (this.code == 1000 || this.code == 1001) {
            return InputType.SCROLL;
        }
        if (this.code == 1002 || KeyBind.Companion.isRawMouseButton(this.code)) {
            return InputType.MOUSE;
        }
        return InputType.KEYBOARD;
    }

    public final boolean isDown(long windowHandle) {
        if (!this.isBound()) {
            return false;
        }
        return switch (WhenMappings.$EnumSwitchMapping$0[this.getType().ordinal()]) {
            case 1 -> {
                if (GLFW.glfwGetMouseButton((long)windowHandle, (int)this.toMouseButton()) == 1) {
                    yield true;
                }
                yield false;
            }
            case 2 -> false;
            default -> GLFW.glfwGetKey((long)windowHandle, (int)this.code) == 1;
        };
    }

    public final boolean isDown(@NotNull Window window) {
        boolean bl;
        Intrinsics.checkNotNullParameter((Object)window, (String)"window");
        try {
            Field field = Window.class.getDeclaredField("handle");
            field.setAccessible(true);
            bl = this.isDown(field.getLong(window));
        }
        catch (Throwable e) {
            bl = false;
        }
        return bl;
    }

    public final boolean matchesMouseButton(int button) {
        return this.getType() == InputType.MOUSE && this.toMouseButton() == button;
    }

    public final boolean matchesScroll(double vertical) {
        return this.code == 1000 && vertical > 0.0 || this.code == 1001 && vertical < 0.0;
    }

    @NotNull
    public final String getDisplayName() {
        return KeyHelper.getShortName(this.code);
    }

    private final int toMouseButton() {
        return this.code == 1002 ? 2 : this.code;
    }

    @JvmStatic
    @NotNull
    public static final KeyBind keyboard(int code) {
        return Companion.keyboard(code);
    }

    @JvmStatic
    @NotNull
    public static final KeyBind mouse(int code) {
        return Companion.mouse(code);
    }

    @JvmStatic
    @NotNull
    public static final KeyBind scrollUp() {
        return Companion.scrollUp();
    }

    @JvmStatic
    @NotNull
    public static final KeyBind scrollDown() {
        return Companion.scrollDown();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001b\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\n\u0010\tJ\u0013\u0010\u000b\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\r\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0014R\u0019\u0010\u0018\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0017\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/utils/key/KeyBind.Companion;", "", "<init>", "()V", "", "code", "Lrtx/kimiko/utils/key/KeyBind;", "Lkotlin/jvm/JvmStatic;", "keyboard", "(I)Lrtx/kimiko/utils/key/KeyBind;", "mouse", "scrollUp", "()Lrtx/kimiko/utils/key/KeyBind;", "scrollDown", "", "isRawMouseButton", "(I)Z", "normalizeMouseCode", "(I)I", "SCROLL_UP", "I", "SCROLL_DOWN", "MIDDLE_MOUSE", "Lkotlin/jvm/JvmField;", "NONE", "Lrtx/kimiko/utils/key/KeyBind;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final KeyBind keyboard(int code) {
            return new KeyBind(code);
        }

        @JvmStatic
        @NotNull
        public final KeyBind mouse(int code) {
            return new KeyBind(this.normalizeMouseCode(code));
        }

        @JvmStatic
        @NotNull
        public final KeyBind scrollUp() {
            return new KeyBind(1000);
        }

        @JvmStatic
        @NotNull
        public final KeyBind scrollDown() {
            return new KeyBind(1001);
        }

        private final boolean isRawMouseButton(int code) {
            return code >= 0 && code <= 7;
        }

        private final int normalizeMouseCode(int code) {
            return code == 2 ? 1002 : code;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=3, xi=48)
    public static final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] nArray = new int[InputType.values().length];
            try {
                nArray[InputType.MOUSE.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[InputType.SCROLL.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
        }
    }
}

