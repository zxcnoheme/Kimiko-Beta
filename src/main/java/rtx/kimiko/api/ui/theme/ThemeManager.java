/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.math.MathKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.theme;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.ui.theme.Theme;
import rtx.kimiko.api.ui.theme.ThemeWave;
import sigil.protect.Level;
import sigil.protect.Protect;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0011\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J#\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0011\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0017\u0010\u0016J\u001b\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0018\u0010\u0016J\u001b\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0019\u0010\u0016J+\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\nH\u0007b\u0002\b\u0005b\u000e\b\u001a\u0012\n\b\u001b\u0012\u0006\b\n0\u001c8\u001d\u00a2\u0006\u0004\b\u001e\u0010\u0016J\u001b\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001f\u0010\u0016J\u001b\u0010 \u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b \u0010\u0016J\u0013\u0010!\u001a\u00020\u0014H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b!\u0010\"J#\u0010$\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b$\u0010%J+\u0010)\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u00142\u0006\u0010(\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b)\u0010*J3\u0010.\u001a\u00020\u00142\u0006\u0010+\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010,\u001a\u00020\n2\u0006\u0010-\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b.\u0010/J\u0013\u00101\u001a\u000200H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b1\u00102J\u001f\u00103\u001a\u00020\u00142\u0006\u0010+\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b3\u0010%J\u0013\u00105\u001a\u000204H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b5\u00106J#\u00107\u001a\u0002042\u0006\u0010,\u001a\u00020\n2\u0006\u0010-\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b7\u00108J\u0013\u00109\u001a\u000204H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b9\u00106R\u0014\u0010:\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0016\u0010<\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0016\u0010>\u001a\u0002008\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0016\u0010@\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u0010AR\u0016\u0010B\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u0010AR\u0016\u0010C\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bC\u0010D\u00a8\u0006E"}, d2={"Lrtx/kimiko/api/ui/theme/ThemeManager;", "", "<init>", "()V", "Lrtx/kimiko/api/ui/theme/Theme;", "Lkotlin/jvm/JvmStatic;", "current", "()Lrtx/kimiko/api/ui/theme/Theme;", "", "clearPendingWave", "", "normX", "normY", "", "armWave", "(FF)J", "theme", "set", "(Lrtx/kimiko/api/ui/theme/Theme;)V", "alpha255", "", "accent", "(F)I", "accentBright", "accentSoft", "accentFill", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "toggleOn", "gradientA", "gradientB", "accentOpaque", "()I", "rgb", "rgba", "(IF)I", "from", "to", "t", "mix", "(IIF)I", "index", "screenX", "screenY", "shadeAt", "(IFFF)I", "", "waveBound", "()Z", "shade", "", "blendedPalette", "()[I", "blendedPaletteAt", "(FF)[I", "targetPalette", "SHADE_COUNT", "I", "currentTheme", "Lrtx/kimiko/api/ui/theme/Theme;", "pendingWave", "Z", "pendingCenterX", "F", "pendingCenterY", "pendingStartNanos", "J", "rtx.kimiko:kimiko"})
public final class ThemeManager {
    @NotNull
    public static final ThemeManager INSTANCE = new ThemeManager();
    private static final int SHADE_COUNT = 7;
    @NotNull
    private static Theme currentTheme = Theme.KIMIKO;
    private static boolean pendingWave;
    private static float pendingCenterX;
    private static float pendingCenterY;
    private static long pendingStartNanos;

    private ThemeManager() {
    }

    @JvmStatic
    @NotNull
    public static final Theme current() {
        return currentTheme;
    }

    @JvmStatic
    public static final void clearPendingWave() {
        pendingWave = false;
    }

    @JvmStatic
    public static final long armWave(float normX, float normY) {
        pendingWave = true;
        pendingCenterX = normX;
        pendingCenterY = normY;
        pendingStartNanos = System.nanoTime();
        return pendingStartNanos;
    }

    @JvmStatic
    public static final void set(@Nullable Theme theme) {
        if (theme == null || theme == currentTheme) {
            pendingWave = false;
            return;
        }
        if (pendingWave) {
            pendingWave = false;
            ThemeWave.INSTANCE.push$rtx_kimiko_kimiko(theme.shades(), theme.palette(), pendingCenterX, pendingCenterY, pendingStartNanos, false);
        } else {
            ThemeWave.INSTANCE.push$rtx_kimiko_kimiko(theme.shades(), theme.palette(), 0.5f, 0.5f, System.nanoTime(), true);
        }
        currentTheme = theme;
        ConfigManager.Companion.markDirty();
    }

    @JvmStatic
    public static final int accent(float alpha255) {
        return INSTANCE.shade(0, alpha255);
    }

    @JvmStatic
    public static final int accentBright(float alpha255) {
        return INSTANCE.shade(1, alpha255);
    }

    @JvmStatic
    public static final int accentSoft(float alpha255) {
        return INSTANCE.shade(2, alpha255);
    }

    @JvmStatic
    public static final int accentFill(float alpha255) {
        return INSTANCE.shade(3, alpha255);
    }

    @JvmStatic
    @Protect(value=Level.CROWN)
    public static final int toggleOn(float alpha255) {
        return INSTANCE.shade(4, alpha255);
    }

    @JvmStatic
    public static final int gradientA(float alpha255) {
        return INSTANCE.shade(5, alpha255);
    }

    @JvmStatic
    public static final int gradientB(float alpha255) {
        return INSTANCE.shade(6, alpha255);
    }

    @JvmStatic
    public static final int accentOpaque() {
        return 0xFF000000 | INSTANCE.shade(0, 255.0f) & 0xFFFFFF;
    }

    @JvmStatic
    public static final int rgba(int rgb, float alpha255) {
        int a = Math.max(0, Math.min(255, MathKt.roundToInt((float)alpha255)));
        if (a <= 0) {
            return 0;
        }
        return a << 24 | rgb & 0xFFFFFF;
    }

    @JvmStatic
    public static final int mix(int from, int to, float t) {
        float tt = t < 0.0f ? 0.0f : (t > 1.0f ? 1.0f : t);
        int a = MathKt.roundToInt((float)((float)(from >>> 24 & 0xFF) + (float)((to >>> 24 & 0xFF) - (from >>> 24 & 0xFF)) * tt));
        int r = MathKt.roundToInt((float)((float)(from >>> 16 & 0xFF) + (float)((to >>> 16 & 0xFF) - (from >>> 16 & 0xFF)) * tt));
        int g = MathKt.roundToInt((float)((float)(from >>> 8 & 0xFF) + (float)((to >>> 8 & 0xFF) - (from >>> 8 & 0xFF)) * tt));
        int b = MathKt.roundToInt((float)((float)(from & 0xFF) + (float)((to & 0xFF) - (from & 0xFF)) * tt));
        if (a <= 0) {
            return 0;
        }
        return a << 24 | r << 16 | g << 8 | b;
    }

    @JvmStatic
    public static final int shadeAt(int index, float alpha255, float screenX, float screenY) {
        if (index < 0 || index >= 7) {
            return 0;
        }
        int a = Math.max(0, Math.min(255, MathKt.roundToInt((float)alpha255)));
        if (a <= 0) {
            return 0;
        }
        if (!ThemeManager.waveBound()) {
            return INSTANCE.shade(index, alpha255);
        }
        return a << 24 | ThemeWave.shadeAtPos(index, screenX, screenY) & 0xFFFFFF;
    }

    @JvmStatic
    public static final boolean waveBound() {
        return ThemeWave.hasSpatialLayers();
    }

    private final int shade(int index, float alpha255) {
        int a = Math.max(0, Math.min(255, MathKt.roundToInt((float)alpha255)));
        if (a <= 0) {
            return 0;
        }
        return a << 24 | ThemeWave.shadeGlobal(index) & 0xFFFFFF;
    }

    @JvmStatic
    @NotNull
    public static final int[] blendedPalette() {
        return ThemeWave.paletteGlobal();
    }

    @JvmStatic
    @NotNull
    public static final int[] blendedPaletteAt(float screenX, float screenY) {
        if (!ThemeManager.waveBound()) {
            return ThemeWave.paletteGlobal();
        }
        return ThemeWave.paletteAtPos(screenX, screenY);
    }

    @JvmStatic
    @NotNull
    public static final int[] targetPalette() {
        return currentTheme.palette();
    }

    static {
        pendingCenterX = 0.5f;
        pendingCenterY = 0.5f;
    }
}

