/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.arc;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 +2\u00020\u0001:\u0001+B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000eJ\r\u0010\u0011\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000eJ\r\u0010\u0012\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0012\u0010\u000eJ\r\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u000eJ\u0010\u0010\u0017\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u000eJ\u0010\u0010\u0018\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u000eJ\u0010\u0010\u0019\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u000eJ\u0010\u0010\u001a\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u000eJ\u0010\u0010\u001b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u000eJ\u0010\u0010\u001c\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b\u001c\u0010\u001dJV\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\tH\u00c6\u0001\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010!\u001a\u00020\u00132\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b!\u0010\"J\u0011\u0010#\u001a\u00020\tH\u00d6\u0081\u0004\u00a2\u0006\u0004\b#\u0010\u001dJ\u0011\u0010%\u001a\u00020$H\u00d6\u0081\u0004\u00a2\u0006\u0004\b%\u0010&R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b'\u0012\b\b(\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010)\u001a\u0004\b\u0003\u0010\u000eR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b'\u0012\b\b(\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010)\u001a\u0004\b\u0004\u0010\u000eR%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b'\u0012\b\b(\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010)\u001a\u0004\b\u0005\u0010\u000eR%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b'\u0012\b\b(\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010)\u001a\u0004\b\u0006\u0010\u000eR%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b'\u0012\b\b(\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010)\u001a\u0004\b\u0007\u0010\u000eR%\u0010\b\u001a\u00020\u00028\u0007z\f\b'\u0012\b\b(\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010)\u001a\u0004\b\b\u0010\u000eR%\u0010\n\u001a\u00020\t8\u0007z\f\b'\u0012\b\b(\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010*\u001a\u0004\b\n\u0010\u001d\u00a8\u0006,"}, d2={"Lrtx/kimiko/utils/render/render2d/arc/BuiltArc;", "", "", "centerX", "centerY", "width", "lift", "thickness", "feather", "", "color", "<init>", "(FFFFFFI)V", "halfWidth", "()F", "padX", "padY", "spanX", "spanY", "", "visible", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "()I", "copy", "(FFFFFFI)Lrtx/kimiko/utils/render/render2d/arc/BuiltArc;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltArc {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final float centerX;
    private final float centerY;
    private final float width;
    private final float lift;
    private final float thickness;
    private final float feather;
    private final int color;
    public static final float DEFAULT_FEATHER = 0.85f;

    public BuiltArc(float centerX, float centerY, float width, float lift, float thickness, float feather, int color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.width = width;
        this.lift = lift;
        this.thickness = thickness;
        this.feather = feather;
        this.color = color;
    }

    @JvmName(name="centerX")
    public final float centerX() {
        return this.centerX;
    }

    @JvmName(name="centerY")
    public final float centerY() {
        return this.centerY;
    }

    @JvmName(name="width")
    public final float width() {
        return this.width;
    }

    @JvmName(name="lift")
    public final float lift() {
        return this.lift;
    }

    @JvmName(name="thickness")
    public final float thickness() {
        return this.thickness;
    }

    @JvmName(name="feather")
    public final float feather() {
        return this.feather;
    }

    @JvmName(name="color")
    public final int color() {
        return this.color;
    }

    public final float halfWidth() {
        return this.width * 0.5f;
    }

    public final float padX() {
        return this.thickness + this.feather;
    }

    public final float padY() {
        return this.thickness + this.feather;
    }

    public final float spanX() {
        return this.halfWidth() + this.padX();
    }

    public final float spanY() {
        return this.lift + this.padY();
    }

    public final boolean visible() {
        return this.width > 0.5f && this.color >>> 24 != 0;
    }

    public final float component1() {
        return this.centerX;
    }

    public final float component2() {
        return this.centerY;
    }

    public final float component3() {
        return this.width;
    }

    public final float component4() {
        return this.lift;
    }

    public final float component5() {
        return this.thickness;
    }

    public final float component6() {
        return this.feather;
    }

    public final int component7() {
        return this.color;
    }

    @NotNull
    public final BuiltArc copy(float centerX, float centerY, float width, float lift, float thickness, float feather, int color) {
        return new BuiltArc(centerX, centerY, width, lift, thickness, feather, color);
    }

    public static /* synthetic */ BuiltArc copy$default(BuiltArc builtArc, float f, float f2, float f3, float f4, float f5, float f6, int n, int n2, Object object) {
        if ((n2 & 1) != 0) {
            f = builtArc.centerX;
        }
        if ((n2 & 2) != 0) {
            f2 = builtArc.centerY;
        }
        if ((n2 & 4) != 0) {
            f3 = builtArc.width;
        }
        if ((n2 & 8) != 0) {
            f4 = builtArc.lift;
        }
        if ((n2 & 0x10) != 0) {
            f5 = builtArc.thickness;
        }
        if ((n2 & 0x20) != 0) {
            f6 = builtArc.feather;
        }
        if ((n2 & 0x40) != 0) {
            n = builtArc.color;
        }
        return builtArc.copy(f, f2, f3, f4, f5, f6, n);
    }

    @NotNull
    public String toString() {
        return "BuiltArc(centerX=" + this.centerX + ", centerY=" + this.centerY + ", width=" + this.width + ", lift=" + this.lift + ", thickness=" + this.thickness + ", feather=" + this.feather + ", color=" + this.color + ")";
    }

    public int hashCode() {
        int result = Float.hashCode(this.centerX);
        result = result * 31 + Float.hashCode(this.centerY);
        result = result * 31 + Float.hashCode(this.width);
        result = result * 31 + Float.hashCode(this.lift);
        result = result * 31 + Float.hashCode(this.thickness);
        result = result * 31 + Float.hashCode(this.feather);
        result = result * 31 + Integer.hashCode(this.color);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltArc)) {
            return false;
        }
        BuiltArc builtArc = (BuiltArc)other;
        if (Float.compare(this.centerX, builtArc.centerX) != 0) {
            return false;
        }
        if (Float.compare(this.centerY, builtArc.centerY) != 0) {
            return false;
        }
        if (Float.compare(this.width, builtArc.width) != 0) {
            return false;
        }
        if (Float.compare(this.lift, builtArc.lift) != 0) {
            return false;
        }
        if (Float.compare(this.thickness, builtArc.thickness) != 0) {
            return false;
        }
        if (Float.compare(this.feather, builtArc.feather) != 0) {
            return false;
        }
        return this.color == builtArc.color;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/utils/render/render2d/arc/BuiltArc.Companion;", "", "<init>", "()V", "", "DEFAULT_FEATHER", "F", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

