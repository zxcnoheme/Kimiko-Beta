/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.theme;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.api.ui.theme.ThemeManager;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.rectangle.rectdefault.BuiltRectangle;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007JC\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0011JC\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0011J[\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0017JC\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0018\u0010\u0011JC\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0019\u0010\u0011JC\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001a\u0010\u0011JO\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJC\u0010\"\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\"\u0010\u0011J/\u0010'\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b'\u0010(JM\u0010/\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020)2\b\u0010,\u001a\u0004\u0018\u00010+2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010-\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010.\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b/\u00100JE\u0010/\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020)2\b\u0010,\u001a\u0004\u0018\u00010+2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010-\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b/\u00101J\u001b\u00104\u001a\u00020\b2\u0006\u00103\u001a\u000202H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b4\u00105\u00a8\u00066"}, d2={"Lrtx/kimiko/api/ui/theme/AccentGradient;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "usesDual", "()Z", "", "x", "y", "w", "h", "radius", "alpha255", "", "fillVertical", "(FFFFFF)V", "fillHorizontal", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "(FFFFFFFFF)V", "fillHorizontalLoop", "fillKnob", "overlayTrackShade", "", "mode", "tint", "paletteFill", "(FFFFFIFF)V", "trackX", "knobX", "overlayKnobFade", "r", "g", "b", "a", "rgba", "(IIIF)I", "Lrtx/kimiko/utils/render/fonts/Fonts;", "font", "", "text", "size", "indexT", "msdfIcon", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;FFFFF)V", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;FFFF)V", "Lrtx/kimiko/api/modules/Category;", "cat", "categoryListIndexT", "(Lrtx/kimiko/api/modules/Category;)F", "rtx.kimiko:kimiko"})
public final class AccentGradient {
    @NotNull
    public static final AccentGradient INSTANCE = new AccentGradient();

    private AccentGradient() {
    }

    @JvmStatic
    public static final boolean usesDual() {
        InterfaceModule module = InterfaceModule.Companion.getInstance();
        if (module == null || module.isThemeClientColor()) {
            return ThemeManager.current().gradientA() != ThemeManager.current().gradientB();
        }
        return module.rectUseSecondColor.getValue();
    }

    @JvmStatic
    public static final void fillVertical(float x, float y, float w, float h, float radius, float alpha255) {
        INSTANCE.paletteFill(x, y, w, h, radius, 1, 1.0f, alpha255);
    }

    @JvmStatic
    public static final void fillHorizontal(float x, float y, float w, float h, float radius, float alpha255) {
        INSTANCE.paletteFill(x, y, w, h, radius, 2, 1.0f, alpha255);
    }

    @JvmStatic
    public static final void fillHorizontal(float x, float y, float w, float h, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, float alpha255) {
        if (w <= 0.0f || h <= 0.0f || alpha255 <= 0.0f) {
            return;
        }
        float a = alpha255 / 255.0f;
        if (a > 1.0f) {
            a = 1.0f;
        }
        Render2D.rect(new BuiltRectangle(x, y, w, h, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, -1).withPaletteGradient(2, 1.0f, a));
    }

    @JvmStatic
    public static final void fillHorizontalLoop(float x, float y, float w, float h, float radius, float alpha255) {
        INSTANCE.paletteFill(x, y, w, h, radius, 3, 1.0f, alpha255);
    }

    @JvmStatic
    public static final void fillKnob(float x, float y, float w, float h, float radius, float alpha255) {
        AccentGradient.fillVertical(x, y, w, h, radius, alpha255);
    }

    @JvmStatic
    public static final void overlayTrackShade(float x, float y, float w, float h, float radius, float alpha255) {
        INSTANCE.paletteFill(x, y, w, h, radius, 2, 0.6f, alpha255 * 0.3f);
    }

    private final void paletteFill(float x, float y, float w, float h, float radius, int mode, float tint, float alpha255) {
        if (w <= 0.0f || h <= 0.0f || alpha255 <= 0.0f) {
            return;
        }
        float a = alpha255 / 255.0f;
        if (a > 1.0f) {
            a = 1.0f;
        }
        Render2D.rect(new BuiltRectangle(x, y, w, h, radius, -1).withPaletteGradient(mode, tint, a));
    }

    @JvmStatic
    public static final void overlayKnobFade(float trackX, float knobX, float y, float h, float radius, float alpha255) {
        if (h <= 0.0f || alpha255 <= 0.0f) {
            return;
        }
        float fadeStart = trackX + (knobX - trackX) * 0.66666f;
        float fadeW = knobX - fadeStart;
        if (fadeW <= 0.5f) {
            return;
        }
        int black = INSTANCE.rgba(0, 0, 0, alpha255 * 0.61f);
        Render2D.rect(fadeStart, y, fadeW, h, radius, 0, black, black, 0);
    }

    private final int rgba(int r, int g, int b, float a) {
        int alpha = Math.max(0, Math.min(255, MathKt.roundToInt((float)a)));
        if (alpha <= 0) {
            return 0;
        }
        return alpha << 24 | r << 16 | g << 8 | b;
    }

    @JvmStatic
    public static final void msdfIcon(@NotNull Fonts font, @Nullable String text, float x, float y, float size, float alpha255, float indexT) {
        Intrinsics.checkNotNullParameter((Object)((Object)font), (String)"font");
        if (alpha255 <= 0.0f || text == null || ((CharSequence)text).length() == 0) {
            return;
        }
        float t = indexT < 0.0f ? 0.0f : (indexT > 1.0f ? 1.0f : indexT);
        float leftT = t * 0.42f;
        float rightT = 0.28f + t * 0.72f;
        int left = ClientAccent.gradientColorAt(leftT, alpha255, x, y + size * 0.5f);
        int right = ClientAccent.gradientColorAt(rightT, alpha255, x + size, y + size * 0.5f);
        font.msdf(text, x, y, size, left, right, right, left);
    }

    @JvmStatic
    public static final void msdfIcon(@NotNull Fonts font, @Nullable String text, float x, float y, float size, float alpha255) {
        Intrinsics.checkNotNullParameter((Object)((Object)font), (String)"font");
        AccentGradient.msdfIcon(font, text, x, y, size, alpha255, 0.5f);
    }

    @JvmStatic
    public static final float categoryListIndexT(@NotNull Category cat) {
        Intrinsics.checkNotNullParameter((Object)((Object)cat), (String)"cat");
        int idx = switch (WhenMappings.$EnumSwitchMapping$0[cat.ordinal()]) {
            case 1 -> 0;
            case 2 -> 1;
            case 3 -> 2;
            case 4 -> 4;
            case 5 -> 3;
            case 6 -> 5;
            default -> throw new NoWhenBranchMatchedException();
        };
        return (float)idx / 5.0f;
    }

    @Metadata(mv={2, 4, 0}, k=3, xi=48)
    public static final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] nArray = new int[Category.values().length];
            try {
                nArray[Category.VISUALS.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[Category.DISPLAY.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[Category.UTILS.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[Category.THEMES.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[Category.CONFIGS.ordinal()] = 5;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[Category.EVENTS.ordinal()] = 6;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
        }
    }
}

