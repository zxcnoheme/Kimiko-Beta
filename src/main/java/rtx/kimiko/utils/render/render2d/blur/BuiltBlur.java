/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.blur;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.render2d.blur.BlurFramebuffer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 A2\u00020\u0001:\u0001ABw\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\r\u00a2\u0006\u0004\b\u0012\u0010\u0013BA\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0015Ba\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\r\u00a2\u0006\u0004\b\u0012\u0010\u0017J\u0017\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\r\u00a2\u0006\u0004\b\u001d\u0010\u001eJ-\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r\u00a2\u0006\u0004\b\u001f\u0010 J\r\u0010\u0016\u001a\u00020\r\u00a2\u0006\u0004\b\u0016\u0010!J\r\u0010#\u001a\u00020\"\u00a2\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b%\u0010&J\u0010\u0010'\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b'\u0010&J\u0010\u0010(\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b(\u0010&J\u0010\u0010)\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b)\u0010&J\u0010\u0010*\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b*\u0010&J\u0010\u0010+\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b+\u0010&J\u0010\u0010,\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b,\u0010&J\u0010\u0010-\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b-\u0010&J\u0010\u0010.\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b.\u0010&J\u0010\u0010/\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b/\u0010&J\u0010\u00100\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\b0\u0010!J\u0010\u00101\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\b1\u0010!J\u0010\u00102\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\b2\u0010!J\u0010\u00103\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\b3\u0010!J\u009c\u0001\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\rH\u00c6\u0001\u00a2\u0006\u0004\b4\u00105J\u001b\u00107\u001a\u00020\"2\b\u00106\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b7\u00108J\u0011\u00109\u001a\u00020\rH\u00d6\u0081\u0004\u00a2\u0006\u0004\b9\u0010!J\u0011\u0010;\u001a\u00020:H\u00d6\u0081\u0004\u00a2\u0006\u0004\b;\u0010<R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b=\u0012\b\b>\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010?\u001a\u0004\b\u0003\u0010&R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b=\u0012\b\b>\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010?\u001a\u0004\b\u0004\u0010&R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b=\u0012\b\b>\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010?\u001a\u0004\b\u0005\u0010&R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b=\u0012\b\b>\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010?\u001a\u0004\b\u0006\u0010&R%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b=\u0012\b\b>\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010?\u001a\u0004\b\u0007\u0010&R%\u0010\b\u001a\u00020\u00028\u0007z\f\b=\u0012\b\b>\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010?\u001a\u0004\b\b\u0010&R%\u0010\t\u001a\u00020\u00028\u0007z\f\b=\u0012\b\b>\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010?\u001a\u0004\b\t\u0010&R%\u0010\n\u001a\u00020\u00028\u0007z\f\b=\u0012\b\b>\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010?\u001a\u0004\b\n\u0010&R%\u0010\u000b\u001a\u00020\u00028\u0007z\f\b=\u0012\b\b>\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010?\u001a\u0004\b\u000b\u0010&R%\u0010\f\u001a\u00020\u00028\u0007z\f\b=\u0012\b\b>\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010?\u001a\u0004\b\f\u0010&R%\u0010\u000e\u001a\u00020\r8\u0007z\f\b=\u0012\b\b>\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010@\u001a\u0004\b\u000e\u0010!R%\u0010\u000f\u001a\u00020\r8\u0007z\f\b=\u0012\b\b>\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010@\u001a\u0004\b\u000f\u0010!R%\u0010\u0010\u001a\u00020\r8\u0007z\f\b=\u0012\b\b>\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010@\u001a\u0004\b\u0010\u0010!R%\u0010\u0011\u001a\u00020\r8\u0007z\f\b=\u0012\b\b>\u0012\u0004\b\b(\u0011\u00a2\u0006\f\n\u0004\b\u0011\u0010@\u001a\u0004\b\u0011\u0010!\u00a8\u0006B"}, d2={"Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;", "", "", "x", "y", "width", "height", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "smoothness", "blurRadius", "", "colorTopLeft", "colorTopRight", "colorBottomRight", "colorBottomLeft", "<init>", "(FFFFFFFFFFIIII)V", "radius", "(FFFFFFF)V", "color", "(FFFFFFFFFFI)V", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "withColor", "(I)Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;", "withColors", "(IIII)Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;", "()I", "", "visible", "()Z", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "(FFFFFFFFFFIIII)Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltBlur {
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
    private final float smoothness;
    private final float blurRadius;
    private final int colorTopLeft;
    private final int colorTopRight;
    private final int colorBottomRight;
    private final int colorBottomLeft;
    private static final int DEFAULT_COLOR = -1;

    public BuiltBlur(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, float smoothness, float blurRadius, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radiusTopLeft = radiusTopLeft;
        this.radiusTopRight = radiusTopRight;
        this.radiusBottomRight = radiusBottomRight;
        this.radiusBottomLeft = radiusBottomLeft;
        this.smoothness = smoothness;
        this.blurRadius = blurRadius;
        this.colorTopLeft = colorTopLeft;
        this.colorTopRight = colorTopRight;
        this.colorBottomRight = colorBottomRight;
        this.colorBottomLeft = colorBottomLeft;
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

    @JvmName(name="smoothness")
    public final float smoothness() {
        return this.smoothness;
    }

    @JvmName(name="blurRadius")
    public final float blurRadius() {
        return this.blurRadius;
    }

    @JvmName(name="colorTopLeft")
    public final int colorTopLeft() {
        return this.colorTopLeft;
    }

    @JvmName(name="colorTopRight")
    public final int colorTopRight() {
        return this.colorTopRight;
    }

    @JvmName(name="colorBottomRight")
    public final int colorBottomRight() {
        return this.colorBottomRight;
    }

    @JvmName(name="colorBottomLeft")
    public final int colorBottomLeft() {
        return this.colorBottomLeft;
    }

    public BuiltBlur(float x, float y, float width, float height, float radius, float smoothness, float blurRadius) {
        this(x, y, width, height, radius, radius, radius, radius, smoothness, blurRadius, DEFAULT_COLOR);
    }

    public BuiltBlur(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, float smoothness, float blurRadius, int color) {
        this(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, smoothness, blurRadius, color, color, color, color);
    }

    public final void render(@Nullable DrawContext graphics) {
        BlurFramebuffer.Companion.getInstance().draw(graphics, this);
    }

    @NotNull
    public final BuiltBlur withColor(int color) {
        return this.withColors(color, color, color, color);
    }

    @NotNull
    public final BuiltBlur withColors(int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft) {
        return new BuiltBlur(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.smoothness, this.blurRadius, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft);
    }

    public final int color() {
        return this.colorTopLeft;
    }

    public final boolean visible() {
        int colors = this.colorTopLeft | this.colorTopRight | this.colorBottomRight | this.colorBottomLeft;
        return this.width > 0.0f && this.height > 0.0f && colors >>> 24 != 0 && this.blurRadius > 0.0f;
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

    public final float component9() {
        return this.smoothness;
    }

    public final float component10() {
        return this.blurRadius;
    }

    public final int component11() {
        return this.colorTopLeft;
    }

    public final int component12() {
        return this.colorTopRight;
    }

    public final int component13() {
        return this.colorBottomRight;
    }

    public final int component14() {
        return this.colorBottomLeft;
    }

    @NotNull
    public final BuiltBlur copy(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, float smoothness, float blurRadius, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft) {
        return new BuiltBlur(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, smoothness, blurRadius, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft);
    }

    public static /* synthetic */ BuiltBlur copy$default(BuiltBlur builtBlur, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, int n, int n2, int n3, int n4, int n5, Object object) {
        if ((n5 & 1) != 0) {
            f = builtBlur.x;
        }
        if ((n5 & 2) != 0) {
            f2 = builtBlur.y;
        }
        if ((n5 & 4) != 0) {
            f3 = builtBlur.width;
        }
        if ((n5 & 8) != 0) {
            f4 = builtBlur.height;
        }
        if ((n5 & 0x10) != 0) {
            f5 = builtBlur.radiusTopLeft;
        }
        if ((n5 & 0x20) != 0) {
            f6 = builtBlur.radiusTopRight;
        }
        if ((n5 & 0x40) != 0) {
            f7 = builtBlur.radiusBottomRight;
        }
        if ((n5 & 0x80) != 0) {
            f8 = builtBlur.radiusBottomLeft;
        }
        if ((n5 & 0x100) != 0) {
            f9 = builtBlur.smoothness;
        }
        if ((n5 & 0x200) != 0) {
            f10 = builtBlur.blurRadius;
        }
        if ((n5 & 0x400) != 0) {
            n = builtBlur.colorTopLeft;
        }
        if ((n5 & 0x800) != 0) {
            n2 = builtBlur.colorTopRight;
        }
        if ((n5 & 0x1000) != 0) {
            n3 = builtBlur.colorBottomRight;
        }
        if ((n5 & 0x2000) != 0) {
            n4 = builtBlur.colorBottomLeft;
        }
        return builtBlur.copy(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, n, n2, n3, n4);
    }

    @NotNull
    public String toString() {
        return "BuiltBlur(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", radiusTopLeft=" + this.radiusTopLeft + ", radiusTopRight=" + this.radiusTopRight + ", radiusBottomRight=" + this.radiusBottomRight + ", radiusBottomLeft=" + this.radiusBottomLeft + ", smoothness=" + this.smoothness + ", blurRadius=" + this.blurRadius + ", colorTopLeft=" + this.colorTopLeft + ", colorTopRight=" + this.colorTopRight + ", colorBottomRight=" + this.colorBottomRight + ", colorBottomLeft=" + this.colorBottomLeft + ")";
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
        result = result * 31 + Float.hashCode(this.smoothness);
        result = result * 31 + Float.hashCode(this.blurRadius);
        result = result * 31 + Integer.hashCode(this.colorTopLeft);
        result = result * 31 + Integer.hashCode(this.colorTopRight);
        result = result * 31 + Integer.hashCode(this.colorBottomRight);
        result = result * 31 + Integer.hashCode(this.colorBottomLeft);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltBlur)) {
            return false;
        }
        BuiltBlur builtBlur = (BuiltBlur)other;
        if (Float.compare(this.x, builtBlur.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtBlur.y) != 0) {
            return false;
        }
        if (Float.compare(this.width, builtBlur.width) != 0) {
            return false;
        }
        if (Float.compare(this.height, builtBlur.height) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopLeft, builtBlur.radiusTopLeft) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopRight, builtBlur.radiusTopRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomRight, builtBlur.radiusBottomRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomLeft, builtBlur.radiusBottomLeft) != 0) {
            return false;
        }
        if (Float.compare(this.smoothness, builtBlur.smoothness) != 0) {
            return false;
        }
        if (Float.compare(this.blurRadius, builtBlur.blurRadius) != 0) {
            return false;
        }
        if (this.colorTopLeft != builtBlur.colorTopLeft) {
            return false;
        }
        if (this.colorTopRight != builtBlur.colorTopRight) {
            return false;
        }
        if (this.colorBottomRight != builtBlur.colorBottomRight) {
            return false;
        }
        return this.colorBottomLeft == builtBlur.colorBottomLeft;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082D\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur.Companion;", "", "<init>", "()V", "", "DEFAULT_COLOR", "I", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

