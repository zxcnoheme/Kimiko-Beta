/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 */
package rtx.kimiko.utils.render.render2d.picker;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import rtx.kimiko.utils.render.core.frame.EngineFrame;
import rtx.kimiko.utils.render.render2d.picker.PickerRenderState;
import rtx.kimiko.utils.render.util.scissor.ScissorUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 12\u00020\u0001:\u00011BW\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u0019J\u0010\u0010\u001b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u0019J\u0010\u0010\u001c\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001c\u0010\u0019J\u0010\u0010\u001d\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001f\u0010\u0019J\u0010\u0010 \u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b \u0010\u0019J\u0010\u0010!\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b!\u0010\u0019J\u0010\u0010\"\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\"\u0010\u001eJ\u0010\u0010#\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b#\u0010\u0019Jt\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b$\u0010%J\u001b\u0010'\u001a\u00020\u00152\b\u0010&\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b'\u0010(J\u0011\u0010)\u001a\u00020\u0007H\u00d6\u0081\u0004\u00a2\u0006\u0004\b)\u0010\u001eJ\u0011\u0010+\u001a\u00020*H\u00d6\u0081\u0004\u00a2\u0006\u0004\b+\u0010,R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010/\u001a\u0004\b\u0003\u0010\u0019R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010/\u001a\u0004\b\u0004\u0010\u0019R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010/\u001a\u0004\b\u0005\u0010\u0019R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010/\u001a\u0004\b\u0006\u0010\u0019R%\u0010\b\u001a\u00020\u00078\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u00100\u001a\u0004\b\b\u0010\u001eR%\u0010\t\u001a\u00020\u00028\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010/\u001a\u0004\b\t\u0010\u0019R%\u0010\n\u001a\u00020\u00028\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010/\u001a\u0004\b\n\u0010\u0019R%\u0010\u000b\u001a\u00020\u00028\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010/\u001a\u0004\b\u000b\u0010\u0019R%\u0010\f\u001a\u00020\u00078\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u00100\u001a\u0004\b\f\u0010\u001eR%\u0010\r\u001a\u00020\u00028\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010/\u001a\u0004\b\r\u0010\u0019\u00a8\u00062"}, d2={"Lrtx/kimiko/utils/render/render2d/picker/BuiltPicker;", "", "", "x", "y", "w", "h", "", "mode", "radius", "smoothness", "alpha", "solidColor", "checkerPx", "<init>", "(FFFFIFFFIF)V", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "", "visible", "()Z", "component1", "()F", "component2", "component3", "component4", "component5", "()I", "component6", "component7", "component8", "component9", "component10", "copy", "(FFFFIFFFIF)Lrtx/kimiko/utils/render/render2d/picker/BuiltPicker;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltPicker {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final float x;
    private final float y;
    private final float w;
    private final float h;
    private final int mode;
    private final float radius;
    private final float smoothness;
    private final float alpha;
    private final int solidColor;
    private final float checkerPx;

    public BuiltPicker(float x, float y, float w, float h, int mode, float radius, float smoothness, float alpha, int solidColor, float checkerPx) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.mode = mode;
        this.radius = radius;
        this.smoothness = smoothness;
        this.alpha = alpha;
        this.solidColor = solidColor;
        this.checkerPx = checkerPx;
    }

    @JvmName(name="x")
    public final float x() {
        return this.x;
    }

    @JvmName(name="y")
    public final float y() {
        return this.y;
    }

    @JvmName(name="w")
    public final float w() {
        return this.w;
    }

    @JvmName(name="h")
    public final float h() {
        return this.h;
    }

    @JvmName(name="mode")
    public final int mode() {
        return this.mode;
    }

    @JvmName(name="radius")
    public final float radius() {
        return this.radius;
    }

    @JvmName(name="smoothness")
    public final float smoothness() {
        return this.smoothness;
    }

    @JvmName(name="alpha")
    public final float alpha() {
        return this.alpha;
    }

    @JvmName(name="solidColor")
    public final int solidColor() {
        return this.solidColor;
    }

    @JvmName(name="checkerPx")
    public final float checkerPx() {
        return this.checkerPx;
    }

    public final void render(@Nullable DrawContext graphics) {
        if (!this.visible()) {
            return;
        }
        EngineFrame.submitWith(graphics, pose -> new PickerRenderState(pose, this, ScissorUtil.current()));
    }

    public final boolean visible() {
        return this.w > 0.0f && this.h > 0.0f && this.alpha > 0.0f;
    }

    public final float component1() {
        return this.x;
    }

    public final float component2() {
        return this.y;
    }

    public final float component3() {
        return this.w;
    }

    public final float component4() {
        return this.h;
    }

    public final int component5() {
        return this.mode;
    }

    public final float component6() {
        return this.radius;
    }

    public final float component7() {
        return this.smoothness;
    }

    public final float component8() {
        return this.alpha;
    }

    public final int component9() {
        return this.solidColor;
    }

    public final float component10() {
        return this.checkerPx;
    }

    @NotNull
    public final BuiltPicker copy(float x, float y, float w, float h, int mode, float radius, float smoothness, float alpha, int solidColor, float checkerPx) {
        return new BuiltPicker(x, y, w, h, mode, radius, smoothness, alpha, solidColor, checkerPx);
    }

    public static /* synthetic */ BuiltPicker copy$default(BuiltPicker builtPicker, float f, float f2, float f3, float f4, int n, float f5, float f6, float f7, int n2, float f8, int n3, Object object) {
        if ((n3 & 1) != 0) {
            f = builtPicker.x;
        }
        if ((n3 & 2) != 0) {
            f2 = builtPicker.y;
        }
        if ((n3 & 4) != 0) {
            f3 = builtPicker.w;
        }
        if ((n3 & 8) != 0) {
            f4 = builtPicker.h;
        }
        if ((n3 & 0x10) != 0) {
            n = builtPicker.mode;
        }
        if ((n3 & 0x20) != 0) {
            f5 = builtPicker.radius;
        }
        if ((n3 & 0x40) != 0) {
            f6 = builtPicker.smoothness;
        }
        if ((n3 & 0x80) != 0) {
            f7 = builtPicker.alpha;
        }
        if ((n3 & 0x100) != 0) {
            n2 = builtPicker.solidColor;
        }
        if ((n3 & 0x200) != 0) {
            f8 = builtPicker.checkerPx;
        }
        return builtPicker.copy(f, f2, f3, f4, n, f5, f6, f7, n2, f8);
    }

    @NotNull
    public String toString() {
        return "BuiltPicker(x=" + this.x + ", y=" + this.y + ", w=" + this.w + ", h=" + this.h + ", mode=" + this.mode + ", radius=" + this.radius + ", smoothness=" + this.smoothness + ", alpha=" + this.alpha + ", solidColor=" + this.solidColor + ", checkerPx=" + this.checkerPx + ")";
    }

    public int hashCode() {
        int result = Float.hashCode(this.x);
        result = result * 31 + Float.hashCode(this.y);
        result = result * 31 + Float.hashCode(this.w);
        result = result * 31 + Float.hashCode(this.h);
        result = result * 31 + Integer.hashCode(this.mode);
        result = result * 31 + Float.hashCode(this.radius);
        result = result * 31 + Float.hashCode(this.smoothness);
        result = result * 31 + Float.hashCode(this.alpha);
        result = result * 31 + Integer.hashCode(this.solidColor);
        result = result * 31 + Float.hashCode(this.checkerPx);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltPicker)) {
            return false;
        }
        BuiltPicker builtPicker = (BuiltPicker)other;
        if (Float.compare(this.x, builtPicker.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtPicker.y) != 0) {
            return false;
        }
        if (Float.compare(this.w, builtPicker.w) != 0) {
            return false;
        }
        if (Float.compare(this.h, builtPicker.h) != 0) {
            return false;
        }
        if (this.mode != builtPicker.mode) {
            return false;
        }
        if (Float.compare(this.radius, builtPicker.radius) != 0) {
            return false;
        }
        if (Float.compare(this.smoothness, builtPicker.smoothness) != 0) {
            return false;
        }
        if (Float.compare(this.alpha, builtPicker.alpha) != 0) {
            return false;
        }
        if (this.solidColor != builtPicker.solidColor) {
            return false;
        }
        return Float.compare(this.checkerPx, builtPicker.checkerPx) == 0;
    }


    @JvmStatic
    @NotNull
    public static final BuiltPicker hue(float x, float y, float w, float h, float alpha) {
        return Companion.hue(x, y, w, h, alpha);
    }

    @JvmStatic
    @NotNull
    public static final BuiltPicker alpha(float x, float y, float w, float h, int solidColor, float alpha) {
        return Companion.alpha(x, y, w, h, solidColor, alpha);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J;\u0010\f\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\f\u0010\rJC\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u0004H\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\t\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/utils/render/render2d/picker/BuiltPicker.Companion;", "", "<init>", "()V", "", "x", "y", "w", "h", "alpha", "Lrtx/kimiko/utils/render/render2d/picker/BuiltPicker;", "Lkotlin/jvm/JvmStatic;", "hue", "(FFFFF)Lrtx/kimiko/utils/render/render2d/picker/BuiltPicker;", "", "solidColor", "(FFFFIF)Lrtx/kimiko/utils/render/render2d/picker/BuiltPicker;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final BuiltPicker hue(float x, float y, float w, float h, float alpha) {
            return new BuiltPicker(x, y, w, h, 0, h * 0.25f, 0.75f, alpha, 0, h);
        }

        @JvmStatic
        @NotNull
        public final BuiltPicker alpha(float x, float y, float w, float h, int solidColor, float alpha) {
            return new BuiltPicker(x, y, w, h, 1, h * 0.25f, 0.75f, alpha, solidColor, h * 0.5f);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

