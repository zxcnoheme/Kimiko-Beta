/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.outline.outline360;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 \"2\u00020\u0001:\u0001\"B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\rJ\u0010\u0010\u0013\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\rJL\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001a\u001a\u00020\u0005H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u0010J\u0011\u0010\u001c\u001a\u00020\u001bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b\u0003\u0010\rR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010 \u001a\u0004\b\u0004\u0010\rR%\u0010\u0006\u001a\u00020\u00058\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010!\u001a\u0004\b\u0006\u0010\u0010R%\u0010\u0007\u001a\u00020\u00058\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010!\u001a\u0004\b\u0007\u0010\u0010R%\u0010\b\u001a\u00020\u00028\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010 \u001a\u0004\b\b\u0010\rR%\u0010\t\u001a\u00020\u00028\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010 \u001a\u0004\b\t\u0010\r\u00a8\u0006#"}, d2={"Lrtx/kimiko/utils/render/render2d/outline/outline360/Outline360Range;", "", "", "startDegrees", "endDegrees", "", "color", "colorEnd", "blendStartDegrees", "blendEndDegrees", "<init>", "(FFIIFF)V", "component1", "()F", "component2", "component3", "()I", "component4", "component5", "component6", "copy", "(FFIIFF)Lrtx/kimiko/utils/render/render2d/outline/outline360/Outline360Range;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "Companion", "rtx.kimiko:kimiko"})
public final class Outline360Range {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final float startDegrees;
    private final float endDegrees;
    private final int color;
    private final int colorEnd;
    private final float blendStartDegrees;
    private final float blendEndDegrees;

    public Outline360Range(float startDegrees, float endDegrees, int color, int colorEnd, float blendStartDegrees, float blendEndDegrees) {
        this.startDegrees = startDegrees;
        this.endDegrees = endDegrees;
        this.color = color;
        this.colorEnd = colorEnd;
        this.blendStartDegrees = blendStartDegrees;
        this.blendEndDegrees = blendEndDegrees;
    }

    @JvmName(name="startDegrees")
    public final float startDegrees() {
        return this.startDegrees;
    }

    @JvmName(name="endDegrees")
    public final float endDegrees() {
        return this.endDegrees;
    }

    @JvmName(name="color")
    public final int color() {
        return this.color;
    }

    @JvmName(name="colorEnd")
    public final int colorEnd() {
        return this.colorEnd;
    }

    @JvmName(name="blendStartDegrees")
    public final float blendStartDegrees() {
        return this.blendStartDegrees;
    }

    @JvmName(name="blendEndDegrees")
    public final float blendEndDegrees() {
        return this.blendEndDegrees;
    }

    public final float component1() {
        return this.startDegrees;
    }

    public final float component2() {
        return this.endDegrees;
    }

    public final int component3() {
        return this.color;
    }

    public final int component4() {
        return this.colorEnd;
    }

    public final float component5() {
        return this.blendStartDegrees;
    }

    public final float component6() {
        return this.blendEndDegrees;
    }

    @NotNull
    public final Outline360Range copy(float startDegrees, float endDegrees, int color, int colorEnd, float blendStartDegrees, float blendEndDegrees) {
        return new Outline360Range(startDegrees, endDegrees, color, colorEnd, blendStartDegrees, blendEndDegrees);
    }

    public static /* synthetic */ Outline360Range copy$default(Outline360Range outline360Range, float f, float f2, int n, int n2, float f3, float f4, int n3, Object object) {
        if ((n3 & 1) != 0) {
            f = outline360Range.startDegrees;
        }
        if ((n3 & 2) != 0) {
            f2 = outline360Range.endDegrees;
        }
        if ((n3 & 4) != 0) {
            n = outline360Range.color;
        }
        if ((n3 & 8) != 0) {
            n2 = outline360Range.colorEnd;
        }
        if ((n3 & 0x10) != 0) {
            f3 = outline360Range.blendStartDegrees;
        }
        if ((n3 & 0x20) != 0) {
            f4 = outline360Range.blendEndDegrees;
        }
        return outline360Range.copy(f, f2, n, n2, f3, f4);
    }

    @NotNull
    public String toString() {
        return "Outline360Range(startDegrees=" + this.startDegrees + ", endDegrees=" + this.endDegrees + ", color=" + this.color + ", colorEnd=" + this.colorEnd + ", blendStartDegrees=" + this.blendStartDegrees + ", blendEndDegrees=" + this.blendEndDegrees + ")";
    }

    public int hashCode() {
        int result = Float.hashCode(this.startDegrees);
        result = result * 31 + Float.hashCode(this.endDegrees);
        result = result * 31 + Integer.hashCode(this.color);
        result = result * 31 + Integer.hashCode(this.colorEnd);
        result = result * 31 + Float.hashCode(this.blendStartDegrees);
        result = result * 31 + Float.hashCode(this.blendEndDegrees);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Outline360Range)) {
            return false;
        }
        Outline360Range outline360Range = (Outline360Range)other;
        if (Float.compare(this.startDegrees, outline360Range.startDegrees) != 0) {
            return false;
        }
        if (Float.compare(this.endDegrees, outline360Range.endDegrees) != 0) {
            return false;
        }
        if (this.color != outline360Range.color) {
            return false;
        }
        if (this.colorEnd != outline360Range.colorEnd) {
            return false;
        }
        if (Float.compare(this.blendStartDegrees, outline360Range.blendStartDegrees) != 0) {
            return false;
        }
        return Float.compare(this.blendEndDegrees, outline360Range.blendEndDegrees) == 0;
    }

    @JvmStatic
    @NotNull
    public static final Outline360Range of(float startDegrees, float endDegrees, int color) {
        return Companion.of(startDegrees, endDegrees, color);
    }

    @JvmStatic
    @NotNull
    public static final Outline360Range gradient(float startDegrees, float endDegrees, int colorStart, int colorEnd, float blendStartDegrees, float blendEndDegrees) {
        return Companion.gradient(startDegrees, endDegrees, colorStart, colorEnd, blendStartDegrees, blendEndDegrees);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J+\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000b\u0010\fJC\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/utils/render/render2d/outline/outline360/Outline360Range.Companion;", "", "<init>", "()V", "", "startDegrees", "endDegrees", "", "color", "Lrtx/kimiko/utils/render/render2d/outline/outline360/Outline360Range;", "Lkotlin/jvm/JvmStatic;", "of", "(FFI)Lrtx/kimiko/utils/render/render2d/outline/outline360/Outline360Range;", "colorStart", "colorEnd", "blendStartDegrees", "blendEndDegrees", "gradient", "(FFIIFF)Lrtx/kimiko/utils/render/render2d/outline/outline360/Outline360Range;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final Outline360Range of(float startDegrees, float endDegrees, int color) {
            return new Outline360Range(startDegrees, endDegrees, color, color, -1.0f, -1.0f);
        }

        @JvmStatic
        @NotNull
        public final Outline360Range gradient(float startDegrees, float endDegrees, int colorStart, int colorEnd, float blendStartDegrees, float blendEndDegrees) {
            return new Outline360Range(startDegrees, endDegrees, colorStart, colorEnd, blendStartDegrees, blendEndDegrees);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

