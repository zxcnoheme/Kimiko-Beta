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
package rtx.kimiko.utils.render.render2d.circle;

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
import rtx.kimiko.utils.render.render2d.circle.CircleBatch;
import rtx.kimiko.utils.render.render2d.circle.CircleRenderState;
import rtx.kimiko.utils.render.util.scissor.ScissorUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 /2\u00020\u0001:\u0001/B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bB)\b\u0016\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0010J\u0017\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0018\u001a\u00020\u0017\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001c\u0010\u001bJ\u0010\u0010\u001d\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001d\u0010\u001bJ\u0010\u0010\u001e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001e\u0010\u001bJ\u0010\u0010\u001f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001f\u0010\u001bJ\u0010\u0010 \u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b \u0010!JL\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\bH\u00c6\u0001\u00a2\u0006\u0004\b\"\u0010#J\u001b\u0010%\u001a\u00020\u00172\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b%\u0010&J\u0011\u0010'\u001a\u00020\bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b'\u0010!J\u0011\u0010)\u001a\u00020(H\u00d6\u0081\u0004\u00a2\u0006\u0004\b)\u0010*R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b+\u0012\b\b,\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010-\u001a\u0004\b\u0003\u0010\u001bR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b+\u0012\b\b,\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010-\u001a\u0004\b\u0004\u0010\u001bR%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b+\u0012\b\b,\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010-\u001a\u0004\b\u0005\u0010\u001bR%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b+\u0012\b\b,\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010-\u001a\u0004\b\u0006\u0010\u001bR%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b+\u0012\b\b,\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010-\u001a\u0004\b\u0007\u0010\u001bR%\u0010\t\u001a\u00020\b8\u0007z\f\b+\u0012\b\b,\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010.\u001a\u0004\b\t\u0010!\u00a8\u00060"}, d2={"Lrtx/kimiko/utils/render/render2d/circle/BuiltCircle;", "", "", "x", "y", "radius", "thickness", "smoothness", "", "color", "<init>", "(FFFFFI)V", "centerX", "centerY", "(FFFI)V", "withThickness", "(F)Lrtx/kimiko/utils/render/render2d/circle/BuiltCircle;", "withSmoothness", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "", "visible", "()Z", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "()I", "copy", "(FFFFFI)Lrtx/kimiko/utils/render/render2d/circle/BuiltCircle;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltCircle {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final float x;
    private final float y;
    private final float radius;
    private final float thickness;
    private final float smoothness;
    private final int color;
    public static final float DEFAULT_SMOOTHNESS = 0.5f;

    public BuiltCircle(float x, float y, float radius, float thickness, float smoothness, int color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.thickness = thickness;
        this.smoothness = smoothness;
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

    @JvmName(name="radius")
    public final float radius() {
        return this.radius;
    }

    @JvmName(name="thickness")
    public final float thickness() {
        return this.thickness;
    }

    @JvmName(name="smoothness")
    public final float smoothness() {
        return this.smoothness;
    }

    @JvmName(name="color")
    public final int color() {
        return this.color;
    }

    public BuiltCircle(float centerX, float centerY, float radius, int color) {
        this(centerX, centerY, radius, 0.0f, 0.5f, color);
    }

    @NotNull
    public final BuiltCircle withThickness(float thickness) {
        return new BuiltCircle(this.x, this.y, this.radius, thickness, this.smoothness, this.color);
    }

    @NotNull
    public final BuiltCircle withSmoothness(float smoothness) {
        return new BuiltCircle(this.x, this.y, this.radius, this.thickness, smoothness, this.color);
    }

    public final void render(@Nullable DrawContext graphics) {
        if (!this.visible()) {
            return;
        }
        EngineFrame.submitWith(graphics, pose -> new CircleRenderState(pose, CircleBatch.normalize(this), ScissorUtil.current(), null, 8, null));
    }

    public final boolean visible() {
        return this.radius > 0.0f && this.color >>> 24 != 0;
    }

    public final float component1() {
        return this.x;
    }

    public final float component2() {
        return this.y;
    }

    public final float component3() {
        return this.radius;
    }

    public final float component4() {
        return this.thickness;
    }

    public final float component5() {
        return this.smoothness;
    }

    public final int component6() {
        return this.color;
    }

    @NotNull
    public final BuiltCircle copy(float x, float y, float radius, float thickness, float smoothness, int color) {
        return new BuiltCircle(x, y, radius, thickness, smoothness, color);
    }

    public static /* synthetic */ BuiltCircle copy$default(BuiltCircle builtCircle, float f, float f2, float f3, float f4, float f5, int n, int n2, Object object) {
        if ((n2 & 1) != 0) {
            f = builtCircle.x;
        }
        if ((n2 & 2) != 0) {
            f2 = builtCircle.y;
        }
        if ((n2 & 4) != 0) {
            f3 = builtCircle.radius;
        }
        if ((n2 & 8) != 0) {
            f4 = builtCircle.thickness;
        }
        if ((n2 & 0x10) != 0) {
            f5 = builtCircle.smoothness;
        }
        if ((n2 & 0x20) != 0) {
            n = builtCircle.color;
        }
        return builtCircle.copy(f, f2, f3, f4, f5, n);
    }

    @NotNull
    public String toString() {
        return "BuiltCircle(x=" + this.x + ", y=" + this.y + ", radius=" + this.radius + ", thickness=" + this.thickness + ", smoothness=" + this.smoothness + ", color=" + this.color + ")";
    }

    public int hashCode() {
        int result = Float.hashCode(this.x);
        result = result * 31 + Float.hashCode(this.y);
        result = result * 31 + Float.hashCode(this.radius);
        result = result * 31 + Float.hashCode(this.thickness);
        result = result * 31 + Float.hashCode(this.smoothness);
        result = result * 31 + Integer.hashCode(this.color);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltCircle)) {
            return false;
        }
        BuiltCircle builtCircle = (BuiltCircle)other;
        if (Float.compare(this.x, builtCircle.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtCircle.y) != 0) {
            return false;
        }
        if (Float.compare(this.radius, builtCircle.radius) != 0) {
            return false;
        }
        if (Float.compare(this.thickness, builtCircle.thickness) != 0) {
            return false;
        }
        if (Float.compare(this.smoothness, builtCircle.smoothness) != 0) {
            return false;
        }
        return this.color == builtCircle.color;
    }


    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/utils/render/render2d/circle/BuiltCircle.Companion;", "", "<init>", "()V", "", "DEFAULT_SMOOTHNESS", "F", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

