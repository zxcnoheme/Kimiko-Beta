/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.arcrect;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0015\n\u0002\b!\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\b\u0018\u0000 F2\u00020\u0001:\u0001FBo\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\r\u00a2\u0006\u0004\b\u0012\u0010\u0013BA\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\r\u00a2\u0006\u0004\b\u0012\u0010\u0015J-\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\r\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001c\u00a2\u0006\u0004\b\u001a\u0010\u001eJ\u0015\u0010\u001f\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u0015\u0010!\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b!\u0010\"J\u0015\u0010$\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u0002\u00a2\u0006\u0004\b$\u0010 J\r\u0010%\u001a\u00020\u0002\u00a2\u0006\u0004\b%\u0010&J\r\u0010'\u001a\u00020\n\u00a2\u0006\u0004\b'\u0010(J\u000f\u0010)\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b)\u0010(J\u0010\u0010*\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b*\u0010&J\u0010\u0010+\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b+\u0010&J\u0010\u0010,\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b,\u0010&J\u0010\u0010-\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b-\u0010&J\u0010\u0010.\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b.\u0010&J\u0010\u0010/\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b/\u0010&J\u0010\u00100\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b0\u0010&J\u0010\u00101\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b1\u0010(J\u0010\u00102\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b2\u0010&J\u0010\u00103\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\b3\u00104J\u0010\u00105\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\b5\u00104J\u0010\u00106\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\b6\u00104J\u0010\u00107\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\b7\u00104J\u0092\u0001\u00108\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\rH\u00c6\u0001\u00a2\u0006\u0004\b8\u00109J\u001b\u0010;\u001a\u00020\n2\b\u0010:\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b;\u0010<J\u0011\u0010=\u001a\u00020\rH\u00d6\u0081\u0004\u00a2\u0006\u0004\b=\u00104J\u0011\u0010?\u001a\u00020>H\u00d6\u0081\u0004\u00a2\u0006\u0004\b?\u0010@R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\bA\u0012\b\bB\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010C\u001a\u0004\b\u0003\u0010&R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\bA\u0012\b\bB\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010C\u001a\u0004\b\u0004\u0010&R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\bA\u0012\b\bB\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010C\u001a\u0004\b\u0005\u0010&R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\bA\u0012\b\bB\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010C\u001a\u0004\b\u0006\u0010&R%\u0010\u0007\u001a\u00020\u00028\u0007z\f\bA\u0012\b\bB\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010C\u001a\u0004\b\u0007\u0010&R%\u0010\b\u001a\u00020\u00028\u0007z\f\bA\u0012\b\bB\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010C\u001a\u0004\b\b\u0010&R%\u0010\t\u001a\u00020\u00028\u0007z\f\bA\u0012\b\bB\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010C\u001a\u0004\b\t\u0010&R%\u0010\u000b\u001a\u00020\n8\u0007z\f\bA\u0012\b\bB\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010D\u001a\u0004\b\u000b\u0010(R%\u0010\f\u001a\u00020\u00028\u0007z\f\bA\u0012\b\bB\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010C\u001a\u0004\b\f\u0010&R%\u0010\u000e\u001a\u00020\r8\u0007z\f\bA\u0012\b\bB\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010E\u001a\u0004\b\u000e\u00104R%\u0010\u000f\u001a\u00020\r8\u0007z\f\bA\u0012\b\bB\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010E\u001a\u0004\b\u000f\u00104R%\u0010\u0010\u001a\u00020\r8\u0007z\f\bA\u0012\b\bB\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010E\u001a\u0004\b\u0010\u00104R%\u0010\u0011\u001a\u00020\r8\u0007z\f\bA\u0012\b\bB\u0012\u0004\b\b(\u0011\u00a2\u0006\f\n\u0004\b\u0011\u0010E\u001a\u0004\b\u0011\u00104\u00a8\u0006G"}, d2={"Lrtx/kimiko/utils/render/render2d/arcrect/BuiltArcRect;", "", "", "centerX", "centerY", "radius", "thickness", "startDegrees", "sweepDegrees", "feather", "", "roundCaps", "gradientShift", "", "colorStart", "colorSecond", "colorThird", "colorEnd", "<init>", "(FFFFFFFZFIIII)V", "color", "(FFFFFFI)V", "start", "second", "third", "end", "withColors", "(IIII)Lrtx/kimiko/utils/render/render2d/arcrect/BuiltArcRect;", "", "stops", "([I)Lrtx/kimiko/utils/render/render2d/arcrect/BuiltArcRect;", "withFeather", "(F)Lrtx/kimiko/utils/render/render2d/arcrect/BuiltArcRect;", "withRoundCaps", "(Z)Lrtx/kimiko/utils/render/render2d/arcrect/BuiltArcRect;", "shift", "withGradientShift", "extent", "()F", "visible", "()Z", "anyOpaque", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "()I", "component11", "component12", "component13", "copy", "(FFFFFFFZFIIII)Lrtx/kimiko/utils/render/render2d/arcrect/BuiltArcRect;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "Z", "I", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltArcRect {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final float centerX;
    private final float centerY;
    private final float radius;
    private final float thickness;
    private final float startDegrees;
    private final float sweepDegrees;
    private final float feather;
    private final boolean roundCaps;
    private final float gradientShift;
    private final int colorStart;
    private final int colorSecond;
    private final int colorThird;
    private final int colorEnd;
    public static final float DEFAULT_FEATHER = 0.75f;
    public static final float MIN_FEATHER = 0.35f;
    public static final float FULL_SWEEP = 360.0f;

    public BuiltArcRect(float centerX, float centerY, float radius, float thickness, float startDegrees, float sweepDegrees, float feather, boolean roundCaps, float gradientShift, int colorStart, int colorSecond, int colorThird, int colorEnd) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.thickness = thickness;
        this.startDegrees = startDegrees;
        this.sweepDegrees = sweepDegrees;
        this.feather = feather;
        this.roundCaps = roundCaps;
        this.gradientShift = gradientShift;
        this.colorStart = colorStart;
        this.colorSecond = colorSecond;
        this.colorThird = colorThird;
        this.colorEnd = colorEnd;
    }

    @JvmName(name="centerX")
    public final float centerX() {
        return this.centerX;
    }

    @JvmName(name="centerY")
    public final float centerY() {
        return this.centerY;
    }

    @JvmName(name="radius")
    public final float radius() {
        return this.radius;
    }

    @JvmName(name="thickness")
    public final float thickness() {
        return this.thickness;
    }

    @JvmName(name="startDegrees")
    public final float startDegrees() {
        return this.startDegrees;
    }

    @JvmName(name="sweepDegrees")
    public final float sweepDegrees() {
        return this.sweepDegrees;
    }

    @JvmName(name="feather")
    public final float feather() {
        return this.feather;
    }

    @JvmName(name="roundCaps")
    public final boolean roundCaps() {
        return this.roundCaps;
    }

    @JvmName(name="gradientShift")
    public final float gradientShift() {
        return this.gradientShift;
    }

    @JvmName(name="colorStart")
    public final int colorStart() {
        return this.colorStart;
    }

    @JvmName(name="colorSecond")
    public final int colorSecond() {
        return this.colorSecond;
    }

    @JvmName(name="colorThird")
    public final int colorThird() {
        return this.colorThird;
    }

    @JvmName(name="colorEnd")
    public final int colorEnd() {
        return this.colorEnd;
    }

    public BuiltArcRect(float centerX, float centerY, float radius, float thickness, float startDegrees, float sweepDegrees, int color) {
        this(centerX, centerY, radius, thickness, startDegrees, sweepDegrees, 0.75f, true, 0.0f, color, color, color, color);
    }

    @NotNull
    public final BuiltArcRect withColors(int start, int second, int third, int end) {
        return BuiltArcRect.copy$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, false, 0.0f, start, second, third, end, 511, null);
    }

    @NotNull
    public final BuiltArcRect withColors(@NotNull int[] stops) {
        Intrinsics.checkNotNullParameter((Object)stops, (String)"stops");
        return stops.length < 4 ? this : this.withColors(stops[0], stops[1], stops[2], stops[3]);
    }

    @NotNull
    public final BuiltArcRect withFeather(float feather) {
        return BuiltArcRect.copy$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, feather, false, 0.0f, 0, 0, 0, 0, 8127, null);
    }

    @NotNull
    public final BuiltArcRect withRoundCaps(boolean roundCaps) {
        return BuiltArcRect.copy$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, roundCaps, 0.0f, 0, 0, 0, 0, 8063, null);
    }

    @NotNull
    public final BuiltArcRect withGradientShift(float shift) {
        return BuiltArcRect.copy$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, false, shift, 0, 0, 0, 0, 7935, null);
    }

    public final float extent() {
        return this.radius + this.thickness * 0.5f + Math.max(this.feather, 0.35f);
    }

    public final boolean visible() {
        return this.radius > 0.0f && this.thickness > 0.01f && this.sweepDegrees > 0.05f && this.anyOpaque();
    }

    private final boolean anyOpaque() {
        return this.colorStart >>> 24 != 0 || this.colorSecond >>> 24 != 0 || this.colorThird >>> 24 != 0 || this.colorEnd >>> 24 != 0;
    }

    public final float component1() {
        return this.centerX;
    }

    public final float component2() {
        return this.centerY;
    }

    public final float component3() {
        return this.radius;
    }

    public final float component4() {
        return this.thickness;
    }

    public final float component5() {
        return this.startDegrees;
    }

    public final float component6() {
        return this.sweepDegrees;
    }

    public final float component7() {
        return this.feather;
    }

    public final boolean component8() {
        return this.roundCaps;
    }

    public final float component9() {
        return this.gradientShift;
    }

    public final int component10() {
        return this.colorStart;
    }

    public final int component11() {
        return this.colorSecond;
    }

    public final int component12() {
        return this.colorThird;
    }

    public final int component13() {
        return this.colorEnd;
    }

    @NotNull
    public final BuiltArcRect copy(float centerX, float centerY, float radius, float thickness, float startDegrees, float sweepDegrees, float feather, boolean roundCaps, float gradientShift, int colorStart, int colorSecond, int colorThird, int colorEnd) {
        return new BuiltArcRect(centerX, centerY, radius, thickness, startDegrees, sweepDegrees, feather, roundCaps, gradientShift, colorStart, colorSecond, colorThird, colorEnd);
    }

    public static /* synthetic */ BuiltArcRect copy$default(BuiltArcRect builtArcRect, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean bl, float f8, int n, int n2, int n3, int n4, int n5, Object object) {
        if ((n5 & 1) != 0) {
            f = builtArcRect.centerX;
        }
        if ((n5 & 2) != 0) {
            f2 = builtArcRect.centerY;
        }
        if ((n5 & 4) != 0) {
            f3 = builtArcRect.radius;
        }
        if ((n5 & 8) != 0) {
            f4 = builtArcRect.thickness;
        }
        if ((n5 & 0x10) != 0) {
            f5 = builtArcRect.startDegrees;
        }
        if ((n5 & 0x20) != 0) {
            f6 = builtArcRect.sweepDegrees;
        }
        if ((n5 & 0x40) != 0) {
            f7 = builtArcRect.feather;
        }
        if ((n5 & 0x80) != 0) {
            bl = builtArcRect.roundCaps;
        }
        if ((n5 & 0x100) != 0) {
            f8 = builtArcRect.gradientShift;
        }
        if ((n5 & 0x200) != 0) {
            n = builtArcRect.colorStart;
        }
        if ((n5 & 0x400) != 0) {
            n2 = builtArcRect.colorSecond;
        }
        if ((n5 & 0x800) != 0) {
            n3 = builtArcRect.colorThird;
        }
        if ((n5 & 0x1000) != 0) {
            n4 = builtArcRect.colorEnd;
        }
        return builtArcRect.copy(f, f2, f3, f4, f5, f6, f7, bl, f8, n, n2, n3, n4);
    }

    @NotNull
    public String toString() {
        return "BuiltArcRect(centerX=" + this.centerX + ", centerY=" + this.centerY + ", radius=" + this.radius + ", thickness=" + this.thickness + ", startDegrees=" + this.startDegrees + ", sweepDegrees=" + this.sweepDegrees + ", feather=" + this.feather + ", roundCaps=" + this.roundCaps + ", gradientShift=" + this.gradientShift + ", colorStart=" + this.colorStart + ", colorSecond=" + this.colorSecond + ", colorThird=" + this.colorThird + ", colorEnd=" + this.colorEnd + ")";
    }

    public int hashCode() {
        int result = Float.hashCode(this.centerX);
        result = result * 31 + Float.hashCode(this.centerY);
        result = result * 31 + Float.hashCode(this.radius);
        result = result * 31 + Float.hashCode(this.thickness);
        result = result * 31 + Float.hashCode(this.startDegrees);
        result = result * 31 + Float.hashCode(this.sweepDegrees);
        result = result * 31 + Float.hashCode(this.feather);
        result = result * 31 + Boolean.hashCode(this.roundCaps);
        result = result * 31 + Float.hashCode(this.gradientShift);
        result = result * 31 + Integer.hashCode(this.colorStart);
        result = result * 31 + Integer.hashCode(this.colorSecond);
        result = result * 31 + Integer.hashCode(this.colorThird);
        result = result * 31 + Integer.hashCode(this.colorEnd);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltArcRect)) {
            return false;
        }
        BuiltArcRect builtArcRect = (BuiltArcRect)other;
        if (Float.compare(this.centerX, builtArcRect.centerX) != 0) {
            return false;
        }
        if (Float.compare(this.centerY, builtArcRect.centerY) != 0) {
            return false;
        }
        if (Float.compare(this.radius, builtArcRect.radius) != 0) {
            return false;
        }
        if (Float.compare(this.thickness, builtArcRect.thickness) != 0) {
            return false;
        }
        if (Float.compare(this.startDegrees, builtArcRect.startDegrees) != 0) {
            return false;
        }
        if (Float.compare(this.sweepDegrees, builtArcRect.sweepDegrees) != 0) {
            return false;
        }
        if (Float.compare(this.feather, builtArcRect.feather) != 0) {
            return false;
        }
        if (this.roundCaps != builtArcRect.roundCaps) {
            return false;
        }
        if (Float.compare(this.gradientShift, builtArcRect.gradientShift) != 0) {
            return false;
        }
        if (this.colorStart != builtArcRect.colorStart) {
            return false;
        }
        if (this.colorSecond != builtArcRect.colorSecond) {
            return false;
        }
        if (this.colorThird != builtArcRect.colorThird) {
            return false;
        }
        return this.colorEnd == builtArcRect.colorEnd;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006\u00a8\u0006\t"}, d2={"Lrtx/kimiko/utils/render/render2d/arcrect/BuiltArcRect.Companion;", "", "<init>", "()V", "", "DEFAULT_FEATHER", "F", "MIN_FEATHER", "FULL_SWEEP", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

