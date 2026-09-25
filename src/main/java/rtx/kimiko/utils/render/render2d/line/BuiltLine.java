/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.line;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u00a2\u0006\u0004\b\f\u0010\rB9\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\f\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0013J\u0010\u0010\u0018\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0013J\u0010\u0010\u0019\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u0013J\u0010\u0010\u001a\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u0013J\u0010\u0010\u001b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u0013J\u0010\u0010\u001c\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001e\u0010\u0013J\u0010\u0010\u001f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001f\u0010\u0013J`\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b \u0010!J\u001b\u0010#\u001a\u00020\u000f2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b#\u0010$J\u0011\u0010%\u001a\u00020\bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b%\u0010\u001dJ\u0011\u0010'\u001a\u00020&H\u00d6\u0081\u0004\u00a2\u0006\u0004\b'\u0010(R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b)\u0012\b\b*\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010+\u001a\u0004\b\u0003\u0010\u0013R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b)\u0012\b\b*\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010+\u001a\u0004\b\u0004\u0010\u0013R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b)\u0012\b\b*\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010+\u001a\u0004\b\u0005\u0010\u0013R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b)\u0012\b\b*\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010+\u001a\u0004\b\u0006\u0010\u0013R%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b)\u0012\b\b*\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010+\u001a\u0004\b\u0007\u0010\u0013R%\u0010\t\u001a\u00020\b8\u0007z\f\b)\u0012\b\b*\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010,\u001a\u0004\b\t\u0010\u001dR%\u0010\n\u001a\u00020\u00028\u0007z\f\b)\u0012\b\b*\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010+\u001a\u0004\b\n\u0010\u0013R%\u0010\u000b\u001a\u00020\u00028\u0007z\f\b)\u0012\b\b*\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010+\u001a\u0004\b\u000b\u0010\u0013\u00a8\u0006-"}, d2={"Lrtx/kimiko/utils/render/render2d/line/BuiltLine;", "", "", "x1", "y1", "x2", "y2", "thickness", "", "color", "fadeStart", "fadeEnd", "<init>", "(FFFFFIFF)V", "(FFFFFI)V", "", "visible", "()Z", "length", "()F", "", "corners", "()[F", "component1", "component2", "component3", "component4", "component5", "component6", "()I", "component7", "component8", "copy", "(FFFFFIFF)Lrtx/kimiko/utils/render/render2d/line/BuiltLine;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "rtx.kimiko:kimiko"})
public final class BuiltLine {
    private final float x1;
    private final float y1;
    private final float x2;
    private final float y2;
    private final float thickness;
    private final int color;
    private final float fadeStart;
    private final float fadeEnd;

    public BuiltLine(float x1, float y1, float x2, float y2, float thickness, int color, float fadeStart, float fadeEnd) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.thickness = thickness;
        this.color = color;
        this.fadeStart = fadeStart;
        this.fadeEnd = fadeEnd;
    }

    @JvmName(name="x1")
    public final float x1() {
        return this.x1;
    }

    @JvmName(name="y1")
    public final float y1() {
        return this.y1;
    }

    @JvmName(name="x2")
    public final float x2() {
        return this.x2;
    }

    @JvmName(name="y2")
    public final float y2() {
        return this.y2;
    }

    @JvmName(name="thickness")
    public final float thickness() {
        return this.thickness;
    }

    @JvmName(name="color")
    public final int color() {
        return this.color;
    }

    @JvmName(name="fadeStart")
    public final float fadeStart() {
        return this.fadeStart;
    }

    @JvmName(name="fadeEnd")
    public final float fadeEnd() {
        return this.fadeEnd;
    }

    public BuiltLine(float x1, float y1, float x2, float y2, float thickness, int color) {
        this(x1, y1, x2, y2, thickness, color, 1.0f, 1.0f);
    }

    public final boolean visible() {
        if (this.thickness <= 0.0f || (this.color >>> 24 & 0xFF) == 0) {
            return false;
        }
        float dx = this.x2 - this.x1;
        float dy = this.y2 - this.y1;
        return dx * dx + dy * dy > 1.0E-4f;
    }

    public final float length() {
        float dx = this.x2 - this.x1;
        float dy = this.y2 - this.y1;
        return (float)Math.sqrt(dx * dx + dy * dy);
    }

    @NotNull
    public final float[] corners() {
        float dx = this.x2 - this.x1;
        float dy = this.y2 - this.y1;
        float len = (float)Math.sqrt(dx * dx + dy * dy);
        if (len < 1.0E-4f) {
            len = 1.0E-4f;
        }
        float px = -dy / len * this.thickness * 0.5f;
        float py = dx / len * this.thickness * 0.5f;
        float[] fArray = new float[]{this.x1 + px, this.y1 + py, this.x1 - px, this.y1 - py, this.x2 - px, this.y2 - py, this.x2 + px, this.y2 + py};
        return fArray;
    }

    public final float component1() {
        return this.x1;
    }

    public final float component2() {
        return this.y1;
    }

    public final float component3() {
        return this.x2;
    }

    public final float component4() {
        return this.y2;
    }

    public final float component5() {
        return this.thickness;
    }

    public final int component6() {
        return this.color;
    }

    public final float component7() {
        return this.fadeStart;
    }

    public final float component8() {
        return this.fadeEnd;
    }

    @NotNull
    public final BuiltLine copy(float x1, float y1, float x2, float y2, float thickness, int color, float fadeStart, float fadeEnd) {
        return new BuiltLine(x1, y1, x2, y2, thickness, color, fadeStart, fadeEnd);
    }

    public static /* synthetic */ BuiltLine copy$default(BuiltLine builtLine, float f, float f2, float f3, float f4, float f5, int n, float f6, float f7, int n2, Object object) {
        if ((n2 & 1) != 0) {
            f = builtLine.x1;
        }
        if ((n2 & 2) != 0) {
            f2 = builtLine.y1;
        }
        if ((n2 & 4) != 0) {
            f3 = builtLine.x2;
        }
        if ((n2 & 8) != 0) {
            f4 = builtLine.y2;
        }
        if ((n2 & 0x10) != 0) {
            f5 = builtLine.thickness;
        }
        if ((n2 & 0x20) != 0) {
            n = builtLine.color;
        }
        if ((n2 & 0x40) != 0) {
            f6 = builtLine.fadeStart;
        }
        if ((n2 & 0x80) != 0) {
            f7 = builtLine.fadeEnd;
        }
        return builtLine.copy(f, f2, f3, f4, f5, n, f6, f7);
    }

    @NotNull
    public String toString() {
        return "BuiltLine(x1=" + this.x1 + ", y1=" + this.y1 + ", x2=" + this.x2 + ", y2=" + this.y2 + ", thickness=" + this.thickness + ", color=" + this.color + ", fadeStart=" + this.fadeStart + ", fadeEnd=" + this.fadeEnd + ")";
    }

    public int hashCode() {
        int result = Float.hashCode(this.x1);
        result = result * 31 + Float.hashCode(this.y1);
        result = result * 31 + Float.hashCode(this.x2);
        result = result * 31 + Float.hashCode(this.y2);
        result = result * 31 + Float.hashCode(this.thickness);
        result = result * 31 + Integer.hashCode(this.color);
        result = result * 31 + Float.hashCode(this.fadeStart);
        result = result * 31 + Float.hashCode(this.fadeEnd);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltLine)) {
            return false;
        }
        BuiltLine builtLine = (BuiltLine)other;
        if (Float.compare(this.x1, builtLine.x1) != 0) {
            return false;
        }
        if (Float.compare(this.y1, builtLine.y1) != 0) {
            return false;
        }
        if (Float.compare(this.x2, builtLine.x2) != 0) {
            return false;
        }
        if (Float.compare(this.y2, builtLine.y2) != 0) {
            return false;
        }
        if (Float.compare(this.thickness, builtLine.thickness) != 0) {
            return false;
        }
        if (this.color != builtLine.color) {
            return false;
        }
        if (Float.compare(this.fadeStart, builtLine.fadeStart) != 0) {
            return false;
        }
        return Float.compare(this.fadeEnd, builtLine.fadeEnd) == 0;
    }
}

