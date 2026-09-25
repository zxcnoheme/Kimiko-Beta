/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.SharedConstants
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.glfw.GLFWNativeWin32
 *  org.lwjgl.system.JNI
 *  org.lwjgl.system.MemoryStack
 *  org.lwjgl.system.MemoryUtil
 *  org.lwjgl.system.windows.WindowsLibrary
 */
package rtx.kimiko.api.ui.window;

import java.nio.IntBuffer;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.SharedConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFWNativeWin32;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.windows.WindowsLibrary;
import rtx.kimiko.api.ui.window.WindowTitleAnimation;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J\u0013\u0010\t\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0007J\u001b\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0017\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0016\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0015R\u0014\u0010\u0018\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0015R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001bR\u0019\u0010\u001d\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0016\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u0015R\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b!\u0010\"\u00a8\u0006#"}, d2={"Lrtx/kimiko/api/ui/window/MainWindow;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "getTitle", "()Ljava/lang/String;", "getMainMenuSubtitle", "getMinecraftVersionName", "", "windowHandle", "", "applyDarkMode", "(J)V", "", "isWindows", "()Z", "getDwmSetWindowAttribute", "()J", "CLIENT_NAME", "Ljava/lang/String;", "Lkotlin/jvm/JvmField;", "CLIENT_NAME_UPPER", "CLIENT_VERSION", "", "DWMWA_USE_IMMERSIVE_DARK_MODE", "I", "DWMWA_USE_IMMERSIVE_DARK_MODE_LEGACY", "TITLE", "Lorg/lwjgl/system/windows/WindowsLibrary;", "dwmapi", "Lorg/lwjgl/system/windows/WindowsLibrary;", "dwmSetWindowAttribute", "J", "rtx.kimiko:kimiko"})
public final class MainWindow {
    @NotNull
    public static final MainWindow INSTANCE = new MainWindow();
    @NotNull
    public static final String CLIENT_NAME = "Kimiko";
    @JvmField
    @NotNull
    public static final String CLIENT_NAME_UPPER;
    @NotNull
    public static final String CLIENT_VERSION = "v1.5";
    private static final int DWMWA_USE_IMMERSIVE_DARK_MODE = 20;
    private static final int DWMWA_USE_IMMERSIVE_DARK_MODE_LEGACY = 19;
    @JvmField
    @NotNull
    public static final String TITLE;
    @Nullable
    private static WindowsLibrary dwmapi;
    private static long dwmSetWindowAttribute;

    private MainWindow() {
    }

    @JvmStatic
    @NotNull
    public static final String getTitle() {
        return WindowTitleAnimation.Companion.get().currentTitle();
    }

    @JvmStatic
    @NotNull
    public static final String getMainMenuSubtitle() {
        return CLIENT_NAME_UPPER + " v1.5  \u00b7  " + MainWindow.getMinecraftVersionName();
    }

    @JvmStatic
    @NotNull
    public static final String getMinecraftVersionName() {
        String string = SharedConstants.getGameVersion().name();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"name(...)");
        return string;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void applyDarkMode(long windowHandle) {
        if (!INSTANCE.isWindows() || windowHandle == 0L) {
            return;
        }
        try {
            long hwnd = GLFWNativeWin32.glfwGetWin32Window((long)windowHandle);
            long function = INSTANCE.getDwmSetWindowAttribute();
            if (hwnd == 0L || function == 0L) {
                return;
            }
            AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
            Throwable throwable = null;
            try {
                MemoryStack stack = (MemoryStack)autoCloseable;
                boolean bl = false;
                IntBuffer enabled = stack.ints(1);
                long enabledAddress = MemoryUtil.memAddress((IntBuffer)enabled);
                int result = JNI.callPPI((long)hwnd, (int)20, (long)enabledAddress, (int)4, (long)function);
                if (result != 0) {
                    JNI.callPPI((long)hwnd, (int)19, (long)enabledAddress, (int)4, (long)function);
                }
                Unit unit = Unit.INSTANCE;
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private final boolean isWindows() {
        String string = System.getProperty("os.name", "");
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getProperty(...)");
        String string2 = string;
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string3 = string2.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toLowerCase(...)");
        return String.valueOf(string3).contains("win");
    }

    private final long getDwmSetWindowAttribute() {
        if (dwmSetWindowAttribute != 0L) {
            return dwmSetWindowAttribute;
        }
        if (dwmapi == null) {
            dwmapi = new WindowsLibrary("dwmapi.dll");
        }
        WindowsLibrary windowsLibrary = dwmapi;
        Intrinsics.checkNotNull((Object)windowsLibrary);
        dwmSetWindowAttribute = windowsLibrary.getFunctionAddress((CharSequence)"DwmSetWindowAttribute");
        return dwmSetWindowAttribute;
    }

    static {
        String string = CLIENT_NAME;
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string2 = string.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toUpperCase(...)");
        CLIENT_NAME_UPPER = string2;
        TITLE = MainWindow.getTitle();
    }
}

