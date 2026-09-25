/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.shimmer;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u000fJ\u0010\u0010\u0012\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u000fJ\u0010\u0010\u0013\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u000fJ\u0010\u0010\u0014\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u000fJ\u0010\u0010\u0015\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u000fJ\u0010\u0010\u0016\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0017J`\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\nH\u00c6\u0001\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0011\u0010\u001e\u001a\u00020\nH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001e\u0010\u0017J\u0011\u0010 \u001a\u00020\u001fH\u00d6\u0081\u0004\u00a2\u0006\u0004\b \u0010!R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010$\u001a\u0004\b\u0003\u0010\u000fR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010$\u001a\u0004\b\u0004\u0010\u000fR%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010$\u001a\u0004\b\u0005\u0010\u000fR%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010$\u001a\u0004\b\u0006\u0010\u000fR%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010$\u001a\u0004\b\u0007\u0010\u000fR%\u0010\b\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010$\u001a\u0004\b\b\u0010\u000fR%\u0010\t\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010$\u001a\u0004\b\t\u0010\u000fR%\u0010\u000b\u001a\u00020\n8\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010%\u001a\u0004\b\u000b\u0010\u0017\u00a8\u0006&"}, d2={"Lrtx/kimiko/utils/render/render2d/shimmer/BuiltShimmer;", "", "", "x", "y", "width", "height", "progress", "halfWidth", "intensity", "", "color", "<init>", "(FFFFFFFI)V", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "()I", "copy", "(FFFFFFFI)Lrtx/kimiko/utils/render/render2d/shimmer/BuiltShimmer;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "rtx.kimiko:kimiko"})
public final class BuiltShimmer {
    private final float x;
    private final float y;
    private final float width;
    private final float height;
    private final float progress;
    private final float halfWidth;
    private final float intensity;
    private final int color;

    public BuiltShimmer(float x, float y, float width, float height, float progress, float halfWidth, float intensity, int color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.progress = progress;
        this.halfWidth = halfWidth;
        this.intensity = intensity;
        this.color = color;
    }

    @JvmName(name="x")
    public final float x() {
        return this.x;
    }

    @JvmName(name="y")
    public final float y() {
        return this.y;
    }

    @JvmName(name="width")
    public final float width() {
        return this.width;
    }

    @JvmName(name="height")
    public final float height() {
        return this.height;
    }

    @JvmName(name="progress")
    public final float progress() {
        return this.progress;
    }

    @JvmName(name="halfWidth")
    public final float halfWidth() {
        return this.halfWidth;
    }

    @JvmName(name="intensity")
    public final float intensity() {
        return this.intensity;
    }

    @JvmName(name="color")
    public final int color() {
        return this.color;
    }

    public final float component1() {
        return this.x;
    }

    public final float component2() {
        return this.y;
    }

    public final float component3() {
        return this.width;
    }

    public final float component4() {
        return this.height;
    }

    public final float component5() {
        return this.progress;
    }

    public final float component6() {
        return this.halfWidth;
    }

    public final float component7() {
        return this.intensity;
    }

    public final int component8() {
        return this.color;
    }

    @NotNull
    public final BuiltShimmer copy(float x, float y, float width, float height, float progress, float halfWidth, float intensity, int color) {
        return new BuiltShimmer(x, y, width, height, progress, halfWidth, intensity, color);
    }

    public static /* synthetic */ BuiltShimmer copy$default(BuiltShimmer builtShimmer, float f, float f2, float f3, float f4, float f5, float f6, float f7, int n, int n2, Object object) {
        if ((n2 & 1) != 0) {
            f = builtShimmer.x;
        }
        if ((n2 & 2) != 0) {
            f2 = builtShimmer.y;
        }
        if ((n2 & 4) != 0) {
            f3 = builtShimmer.width;
        }
        if ((n2 & 8) != 0) {
            f4 = builtShimmer.height;
        }
        if ((n2 & 0x10) != 0) {
            f5 = builtShimmer.progress;
        }
        if ((n2 & 0x20) != 0) {
            f6 = builtShimmer.halfWidth;
        }
        if ((n2 & 0x40) != 0) {
            f7 = builtShimmer.intensity;
        }
        if ((n2 & 0x80) != 0) {
            n = builtShimmer.color;
        }
        return builtShimmer.copy(f, f2, f3, f4, f5, f6, f7, n);
    }

    @NotNull
    public String toString() {
        return "BuiltShimmer(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", progress=" + this.progress + ", halfWidth=" + this.halfWidth + ", intensity=" + this.intensity + ", color=" + this.color + ")";
    }

    public int hashCode() {
        int result = Float.hashCode(this.x);
        result = result * 31 + Float.hashCode(this.y);
        result = result * 31 + Float.hashCode(this.width);
        result = result * 31 + Float.hashCode(this.height);
        result = result * 31 + Float.hashCode(this.progress);
        result = result * 31 + Float.hashCode(this.halfWidth);
        result = result * 31 + Float.hashCode(this.intensity);
        result = result * 31 + Integer.hashCode(this.color);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltShimmer)) {
            return false;
        }
        BuiltShimmer builtShimmer = (BuiltShimmer)other;
        if (Float.compare(this.x, builtShimmer.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtShimmer.y) != 0) {
            return false;
        }
        if (Float.compare(this.width, builtShimmer.width) != 0) {
            return false;
        }
        if (Float.compare(this.height, builtShimmer.height) != 0) {
            return false;
        }
        if (Float.compare(this.progress, builtShimmer.progress) != 0) {
            return false;
        }
        if (Float.compare(this.halfWidth, builtShimmer.halfWidth) != 0) {
            return false;
        }
        if (Float.compare(this.intensity, builtShimmer.intensity) != 0) {
            return false;
        }
        return this.color == builtShimmer.color;
    }
}

