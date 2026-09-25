/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 */
package rtx.kimiko.utils.render.render2d.zippy;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import rtx.kimiko.utils.render.core.frame.EngineFrame;
import rtx.kimiko.utils.render.render2d.zippy.ZippyBatch;
import rtx.kimiko.utils.render.render2d.zippy.ZippyRenderState;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 ;2\u00020\u0001:\u0001;B_\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010B9\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\u000f\u0010\u0012BQ\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\u000f\u0010\u0013J\u0015\u0010\u0014\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0016\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0017J\u0017\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001f\u001a\u00020\u001e\u00a2\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b#\u0010\"J\u0010\u0010$\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b$\u0010\"J\u0010\u0010%\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b%\u0010\"J\u0010\u0010&\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b&\u0010\"J\u0010\u0010'\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b'\u0010\"J\u0010\u0010(\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b(\u0010\"J\u0010\u0010)\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b)\u0010\"J\u0010\u0010*\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b*\u0010+J\u0010\u0010,\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b,\u0010\"J\u0010\u0010-\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b-\u0010\"J~\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b.\u0010/J\u001b\u00101\u001a\u00020\u001e2\b\u00100\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b1\u00102J\u0011\u00103\u001a\u00020\u000bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b3\u0010+J\u0011\u00105\u001a\u000204H\u00d6\u0081\u0004\u00a2\u0006\u0004\b5\u00106R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b7\u0012\b\b8\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u00109\u001a\u0004\b\u0003\u0010\"R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b7\u0012\b\b8\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u00109\u001a\u0004\b\u0004\u0010\"R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b7\u0012\b\b8\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u00109\u001a\u0004\b\u0005\u0010\"R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b7\u0012\b\b8\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u00109\u001a\u0004\b\u0006\u0010\"R%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b7\u0012\b\b8\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u00109\u001a\u0004\b\u0007\u0010\"R%\u0010\b\u001a\u00020\u00028\u0007z\f\b7\u0012\b\b8\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u00109\u001a\u0004\b\b\u0010\"R%\u0010\t\u001a\u00020\u00028\u0007z\f\b7\u0012\b\b8\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u00109\u001a\u0004\b\t\u0010\"R%\u0010\n\u001a\u00020\u00028\u0007z\f\b7\u0012\b\b8\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u00109\u001a\u0004\b\n\u0010\"R%\u0010\f\u001a\u00020\u000b8\u0007z\f\b7\u0012\b\b8\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010:\u001a\u0004\b\f\u0010+R%\u0010\r\u001a\u00020\u00028\u0007z\f\b7\u0012\b\b8\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u00109\u001a\u0004\b\r\u0010\"R%\u0010\u000e\u001a\u00020\u00028\u0007z\f\b7\u0012\b\b8\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u00109\u001a\u0004\b\u000e\u0010\"\u00a8\u0006<"}, d2={"Lrtx/kimiko/utils/render/render2d/zippy/BuiltZippy;", "", "", "x", "y", "width", "height", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "", "color", "smoothness", "timeOffset", "<init>", "(FFFFFFFFIFF)V", "radius", "(FFFFFI)V", "(FFFFFFFFI)V", "withColor", "(I)Lrtx/kimiko/utils/render/render2d/zippy/BuiltZippy;", "withSmoothness", "(F)Lrtx/kimiko/utils/render/render2d/zippy/BuiltZippy;", "withTimeOffset", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "", "visible", "()Z", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "()I", "component10", "component11", "copy", "(FFFFFFFFIFF)Lrtx/kimiko/utils/render/render2d/zippy/BuiltZippy;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltZippy {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final float x;
    private final float y;
    private final float width;
    private final float height;
    private final float radiusTopLeft;
    private final float radiusTopRight;
    private final float radiusBottomRight;
    private final float radiusBottomLeft;
    private final int color;
    private final float smoothness;
    private final float timeOffset;
    public static final float DEFAULT_SMOOTHNESS = 0.0f;
    public static final int DEFAULT_COLOR = -1;

    public BuiltZippy(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int color, float smoothness, float timeOffset) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radiusTopLeft = radiusTopLeft;
        this.radiusTopRight = radiusTopRight;
        this.radiusBottomRight = radiusBottomRight;
        this.radiusBottomLeft = radiusBottomLeft;
        this.color = color;
        this.smoothness = smoothness;
        this.timeOffset = timeOffset;
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

    @JvmName(name="radiusTopLeft")
    public final float radiusTopLeft() {
        return this.radiusTopLeft;
    }

    @JvmName(name="radiusTopRight")
    public final float radiusTopRight() {
        return this.radiusTopRight;
    }

    @JvmName(name="radiusBottomRight")
    public final float radiusBottomRight() {
        return this.radiusBottomRight;
    }

    @JvmName(name="radiusBottomLeft")
    public final float radiusBottomLeft() {
        return this.radiusBottomLeft;
    }

    @JvmName(name="color")
    public final int color() {
        return this.color;
    }

    @JvmName(name="smoothness")
    public final float smoothness() {
        return this.smoothness;
    }

    @JvmName(name="timeOffset")
    public final float timeOffset() {
        return this.timeOffset;
    }

    public BuiltZippy(float x, float y, float width, float height, float radius, int color) {
        this(x, y, width, height, radius, radius, radius, radius, color, 0.0f, 0.0f);
    }

    public BuiltZippy(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int color) {
        this(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, color, 0.0f, 0.0f);
    }

    @NotNull
    public final BuiltZippy withColor(int color) {
        return new BuiltZippy(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, color, this.smoothness, this.timeOffset);
    }

    @NotNull
    public final BuiltZippy withSmoothness(float smoothness) {
        return new BuiltZippy(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, smoothness, this.timeOffset);
    }

    @NotNull
    public final BuiltZippy withTimeOffset(float timeOffset) {
        return new BuiltZippy(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.smoothness, timeOffset);
    }

    public final void render(@Nullable DrawContext graphics) {
        if (!this.visible()) {
            return;
        }
        EngineFrame.submitWith(graphics, pose -> new ZippyRenderState(pose, ZippyBatch.normalize(this)));
    }

    public final boolean visible() {
        return this.width > 0.0f && this.height > 0.0f && this.color >>> 24 != 0;
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
        return this.radiusTopLeft;
    }

    public final float component6() {
        return this.radiusTopRight;
    }

    public final float component7() {
        return this.radiusBottomRight;
    }

    public final float component8() {
        return this.radiusBottomLeft;
    }

    public final int component9() {
        return this.color;
    }

    public final float component10() {
        return this.smoothness;
    }

    public final float component11() {
        return this.timeOffset;
    }

    @NotNull
    public final BuiltZippy copy(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int color, float smoothness, float timeOffset) {
        return new BuiltZippy(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, color, smoothness, timeOffset);
    }

    public static /* synthetic */ BuiltZippy copy$default(BuiltZippy builtZippy, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, float f9, float f10, int n2, Object object) {
        if ((n2 & 1) != 0) {
            f = builtZippy.x;
        }
        if ((n2 & 2) != 0) {
            f2 = builtZippy.y;
        }
        if ((n2 & 4) != 0) {
            f3 = builtZippy.width;
        }
        if ((n2 & 8) != 0) {
            f4 = builtZippy.height;
        }
        if ((n2 & 0x10) != 0) {
            f5 = builtZippy.radiusTopLeft;
        }
        if ((n2 & 0x20) != 0) {
            f6 = builtZippy.radiusTopRight;
        }
        if ((n2 & 0x40) != 0) {
            f7 = builtZippy.radiusBottomRight;
        }
        if ((n2 & 0x80) != 0) {
            f8 = builtZippy.radiusBottomLeft;
        }
        if ((n2 & 0x100) != 0) {
            n = builtZippy.color;
        }
        if ((n2 & 0x200) != 0) {
            f9 = builtZippy.smoothness;
        }
        if ((n2 & 0x400) != 0) {
            f10 = builtZippy.timeOffset;
        }
        return builtZippy.copy(f, f2, f3, f4, f5, f6, f7, f8, n, f9, f10);
    }

    @NotNull
    public String toString() {
        return "BuiltZippy(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", radiusTopLeft=" + this.radiusTopLeft + ", radiusTopRight=" + this.radiusTopRight + ", radiusBottomRight=" + this.radiusBottomRight + ", radiusBottomLeft=" + this.radiusBottomLeft + ", color=" + this.color + ", smoothness=" + this.smoothness + ", timeOffset=" + this.timeOffset + ")";
    }

    public int hashCode() {
        int result = Float.hashCode(this.x);
        result = result * 31 + Float.hashCode(this.y);
        result = result * 31 + Float.hashCode(this.width);
        result = result * 31 + Float.hashCode(this.height);
        result = result * 31 + Float.hashCode(this.radiusTopLeft);
        result = result * 31 + Float.hashCode(this.radiusTopRight);
        result = result * 31 + Float.hashCode(this.radiusBottomRight);
        result = result * 31 + Float.hashCode(this.radiusBottomLeft);
        result = result * 31 + Integer.hashCode(this.color);
        result = result * 31 + Float.hashCode(this.smoothness);
        result = result * 31 + Float.hashCode(this.timeOffset);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltZippy)) {
            return false;
        }
        BuiltZippy builtZippy = (BuiltZippy)other;
        if (Float.compare(this.x, builtZippy.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtZippy.y) != 0) {
            return false;
        }
        if (Float.compare(this.width, builtZippy.width) != 0) {
            return false;
        }
        if (Float.compare(this.height, builtZippy.height) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopLeft, builtZippy.radiusTopLeft) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopRight, builtZippy.radiusTopRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomRight, builtZippy.radiusBottomRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomLeft, builtZippy.radiusBottomLeft) != 0) {
            return false;
        }
        if (this.color != builtZippy.color) {
            return false;
        }
        if (Float.compare(this.smoothness, builtZippy.smoothness) != 0) {
            return false;
        }
        return Float.compare(this.timeOffset, builtZippy.timeOffset) == 0;
    }


    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/utils/render/render2d/zippy/BuiltZippy.Companion;", "", "<init>", "()V", "", "DEFAULT_SMOOTHNESS", "F", "", "DEFAULT_COLOR", "I", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

