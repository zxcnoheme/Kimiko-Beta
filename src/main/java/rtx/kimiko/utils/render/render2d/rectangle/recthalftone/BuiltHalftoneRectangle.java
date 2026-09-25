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
package rtx.kimiko.utils.render.render2d.rectangle.recthalftone;

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
import rtx.kimiko.utils.render.render2d.rectangle.recthalftone.HalftoneRectangleBatch;
import rtx.kimiko.utils.render.render2d.rectangle.recthalftone.HalftoneRectangleRenderState;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 G2\u00020\u0001:\u0001GB\u0087\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u000b\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015BQ\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u000b\u0012\u0006\u0010\u0011\u001a\u00020\u000b\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0018Bi\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u000b\u0012\u0006\u0010\u0011\u001a\u00020\u000b\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0019J\u0015\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u000b\u00a2\u0006\u0004\b\u001c\u0010\u001dJ%\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010#\u001a\u00020\"2\b\u0010!\u001a\u0004\u0018\u00010 \u00a2\u0006\u0004\b#\u0010$J\r\u0010&\u001a\u00020%\u00a2\u0006\u0004\b&\u0010'J\u0010\u0010(\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b(\u0010)J\u0010\u0010*\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b*\u0010)J\u0010\u0010+\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b+\u0010)J\u0010\u0010,\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b,\u0010)J\u0010\u0010-\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b-\u0010)J\u0010\u0010.\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b.\u0010)J\u0010\u0010/\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b/\u0010)J\u0010\u00100\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b0\u0010)J\u0010\u00101\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b1\u00102J\u0010\u00103\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b3\u00102J\u0010\u00104\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b4\u00102J\u0010\u00105\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b5\u00102J\u0010\u00106\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b6\u0010)J\u0010\u00107\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b7\u00102J\u0010\u00108\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b8\u0010)J\u0010\u00109\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b9\u0010)J\u00b0\u0001\u0010:\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b:\u0010;J\u001b\u0010=\u001a\u00020%2\b\u0010<\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b=\u0010>J\u0011\u0010?\u001a\u00020\u000bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b?\u00102J\u0011\u0010A\u001a\u00020@H\u00d6\u0081\u0004\u00a2\u0006\u0004\bA\u0010BR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\bC\u0012\b\bD\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010E\u001a\u0004\b\u0003\u0010)R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\bC\u0012\b\bD\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010E\u001a\u0004\b\u0004\u0010)R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\bC\u0012\b\bD\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010E\u001a\u0004\b\u0005\u0010)R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\bC\u0012\b\bD\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010E\u001a\u0004\b\u0006\u0010)R%\u0010\u0007\u001a\u00020\u00028\u0007z\f\bC\u0012\b\bD\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010E\u001a\u0004\b\u0007\u0010)R%\u0010\b\u001a\u00020\u00028\u0007z\f\bC\u0012\b\bD\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010E\u001a\u0004\b\b\u0010)R%\u0010\t\u001a\u00020\u00028\u0007z\f\bC\u0012\b\bD\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010E\u001a\u0004\b\t\u0010)R%\u0010\n\u001a\u00020\u00028\u0007z\f\bC\u0012\b\bD\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010E\u001a\u0004\b\n\u0010)R%\u0010\f\u001a\u00020\u000b8\u0007z\f\bC\u0012\b\bD\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010F\u001a\u0004\b\f\u00102R%\u0010\r\u001a\u00020\u000b8\u0007z\f\bC\u0012\b\bD\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010F\u001a\u0004\b\r\u00102R%\u0010\u000e\u001a\u00020\u000b8\u0007z\f\bC\u0012\b\bD\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010F\u001a\u0004\b\u000e\u00102R%\u0010\u000f\u001a\u00020\u000b8\u0007z\f\bC\u0012\b\bD\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010F\u001a\u0004\b\u000f\u00102R%\u0010\u0010\u001a\u00020\u00028\u0007z\f\bC\u0012\b\bD\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010E\u001a\u0004\b\u0010\u0010)R%\u0010\u0011\u001a\u00020\u000b8\u0007z\f\bC\u0012\b\bD\u0012\u0004\b\b(\u0011\u00a2\u0006\f\n\u0004\b\u0011\u0010F\u001a\u0004\b\u0011\u00102R%\u0010\u0012\u001a\u00020\u00028\u0007z\f\bC\u0012\b\bD\u0012\u0004\b\b(\u0012\u00a2\u0006\f\n\u0004\b\u0012\u0010E\u001a\u0004\b\u0012\u0010)R%\u0010\u0013\u001a\u00020\u00028\u0007z\f\bC\u0012\b\bD\u0012\u0004\b\b(\u0013\u00a2\u0006\f\n\u0004\b\u0013\u0010E\u001a\u0004\b\u0013\u0010)\u00a8\u0006H"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/recthalftone/BuiltHalftoneRectangle;", "", "", "x", "y", "width", "height", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "", "colorTopLeft", "colorTopRight", "colorBottomRight", "colorBottomLeft", "smoothness", "dotColor", "dotSize", "dotSpacing", "<init>", "(FFFFFFFFIIIIFIFF)V", "radius", "color", "(FFFFFIIFF)V", "(FFFFFFFFIIFF)V", "withSmoothness", "(F)Lrtx/kimiko/utils/render/render2d/rectangle/recthalftone/BuiltHalftoneRectangle;", "withColor", "(I)Lrtx/kimiko/utils/render/render2d/rectangle/recthalftone/BuiltHalftoneRectangle;", "withDots", "(IFF)Lrtx/kimiko/utils/render/render2d/rectangle/recthalftone/BuiltHalftoneRectangle;", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "", "visible", "()Z", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "()I", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "copy", "(FFFFFFFFIIIIFIFF)Lrtx/kimiko/utils/render/render2d/rectangle/recthalftone/BuiltHalftoneRectangle;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltHalftoneRectangle {
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
    private final int colorTopLeft;
    private final int colorTopRight;
    private final int colorBottomRight;
    private final int colorBottomLeft;
    private final float smoothness;
    private final int dotColor;
    private final float dotSize;
    private final float dotSpacing;
    public static final float DEFAULT_SMOOTHNESS = 0.0f;
    public static final int DEFAULT_COLOR = -1;
    public static final int DEFAULT_DOT_COLOR = -872415232;
    public static final float DEFAULT_DOT_SIZE = 1.25f;
    public static final float DEFAULT_DOT_SPACING = 4.0f;

    public BuiltHalftoneRectangle(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float smoothness, int dotColor, float dotSize, float dotSpacing) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radiusTopLeft = radiusTopLeft;
        this.radiusTopRight = radiusTopRight;
        this.radiusBottomRight = radiusBottomRight;
        this.radiusBottomLeft = radiusBottomLeft;
        this.colorTopLeft = colorTopLeft;
        this.colorTopRight = colorTopRight;
        this.colorBottomRight = colorBottomRight;
        this.colorBottomLeft = colorBottomLeft;
        this.smoothness = smoothness;
        this.dotColor = dotColor;
        this.dotSize = dotSize;
        this.dotSpacing = dotSpacing;
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

    @JvmName(name="smoothness")
    public final float smoothness() {
        return this.smoothness;
    }

    @JvmName(name="dotColor")
    public final int dotColor() {
        return this.dotColor;
    }

    @JvmName(name="dotSize")
    public final float dotSize() {
        return this.dotSize;
    }

    @JvmName(name="dotSpacing")
    public final float dotSpacing() {
        return this.dotSpacing;
    }

    public BuiltHalftoneRectangle(float x, float y, float width, float height, float radius, int color, int dotColor, float dotSize, float dotSpacing) {
        this(x, y, width, height, radius, radius, radius, radius, color, color, color, color, 0.0f, dotColor, dotSize, dotSpacing);
    }

    public BuiltHalftoneRectangle(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int color, int dotColor, float dotSize, float dotSpacing) {
        this(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, color, color, color, color, 0.0f, dotColor, dotSize, dotSpacing);
    }

    @NotNull
    public final BuiltHalftoneRectangle withSmoothness(float smoothness) {
        return new BuiltHalftoneRectangle(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.colorTopLeft, this.colorTopRight, this.colorBottomRight, this.colorBottomLeft, smoothness, this.dotColor, this.dotSize, this.dotSpacing);
    }

    @NotNull
    public final BuiltHalftoneRectangle withColor(int color) {
        return new BuiltHalftoneRectangle(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, color, color, color, color, this.smoothness, this.dotColor, this.dotSize, this.dotSpacing);
    }

    @NotNull
    public final BuiltHalftoneRectangle withDots(int dotColor, float dotSize, float dotSpacing) {
        return new BuiltHalftoneRectangle(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.colorTopLeft, this.colorTopRight, this.colorBottomRight, this.colorBottomLeft, this.smoothness, dotColor, dotSize, dotSpacing);
    }

    public final void render(@Nullable DrawContext graphics) {
        if (!this.visible()) {
            return;
        }
        EngineFrame.submitWith(graphics, pose -> new HalftoneRectangleRenderState(pose, HalftoneRectangleBatch.normalize(this)));
    }

    public final boolean visible() {
        int colors = this.colorTopLeft | this.colorTopRight | this.colorBottomRight | this.colorBottomLeft | this.dotColor;
        return this.width > 0.0f && this.height > 0.0f && colors >>> 24 != 0;
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
        return this.colorTopLeft;
    }

    public final int component10() {
        return this.colorTopRight;
    }

    public final int component11() {
        return this.colorBottomRight;
    }

    public final int component12() {
        return this.colorBottomLeft;
    }

    public final float component13() {
        return this.smoothness;
    }

    public final int component14() {
        return this.dotColor;
    }

    public final float component15() {
        return this.dotSize;
    }

    public final float component16() {
        return this.dotSpacing;
    }

    @NotNull
    public final BuiltHalftoneRectangle copy(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float smoothness, int dotColor, float dotSize, float dotSpacing) {
        return new BuiltHalftoneRectangle(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft, smoothness, dotColor, dotSize, dotSpacing);
    }

    public static /* synthetic */ BuiltHalftoneRectangle copy$default(BuiltHalftoneRectangle builtHalftoneRectangle, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, int n2, int n3, int n4, float f9, int n5, float f10, float f11, int n6, Object object) {
        if ((n6 & 1) != 0) {
            f = builtHalftoneRectangle.x;
        }
        if ((n6 & 2) != 0) {
            f2 = builtHalftoneRectangle.y;
        }
        if ((n6 & 4) != 0) {
            f3 = builtHalftoneRectangle.width;
        }
        if ((n6 & 8) != 0) {
            f4 = builtHalftoneRectangle.height;
        }
        if ((n6 & 0x10) != 0) {
            f5 = builtHalftoneRectangle.radiusTopLeft;
        }
        if ((n6 & 0x20) != 0) {
            f6 = builtHalftoneRectangle.radiusTopRight;
        }
        if ((n6 & 0x40) != 0) {
            f7 = builtHalftoneRectangle.radiusBottomRight;
        }
        if ((n6 & 0x80) != 0) {
            f8 = builtHalftoneRectangle.radiusBottomLeft;
        }
        if ((n6 & 0x100) != 0) {
            n = builtHalftoneRectangle.colorTopLeft;
        }
        if ((n6 & 0x200) != 0) {
            n2 = builtHalftoneRectangle.colorTopRight;
        }
        if ((n6 & 0x400) != 0) {
            n3 = builtHalftoneRectangle.colorBottomRight;
        }
        if ((n6 & 0x800) != 0) {
            n4 = builtHalftoneRectangle.colorBottomLeft;
        }
        if ((n6 & 0x1000) != 0) {
            f9 = builtHalftoneRectangle.smoothness;
        }
        if ((n6 & 0x2000) != 0) {
            n5 = builtHalftoneRectangle.dotColor;
        }
        if ((n6 & 0x4000) != 0) {
            f10 = builtHalftoneRectangle.dotSize;
        }
        if ((n6 & 0x8000) != 0) {
            f11 = builtHalftoneRectangle.dotSpacing;
        }
        return builtHalftoneRectangle.copy(f, f2, f3, f4, f5, f6, f7, f8, n, n2, n3, n4, f9, n5, f10, f11);
    }

    @NotNull
    public String toString() {
        return "BuiltHalftoneRectangle(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", radiusTopLeft=" + this.radiusTopLeft + ", radiusTopRight=" + this.radiusTopRight + ", radiusBottomRight=" + this.radiusBottomRight + ", radiusBottomLeft=" + this.radiusBottomLeft + ", colorTopLeft=" + this.colorTopLeft + ", colorTopRight=" + this.colorTopRight + ", colorBottomRight=" + this.colorBottomRight + ", colorBottomLeft=" + this.colorBottomLeft + ", smoothness=" + this.smoothness + ", dotColor=" + this.dotColor + ", dotSize=" + this.dotSize + ", dotSpacing=" + this.dotSpacing + ")";
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
        result = result * 31 + Integer.hashCode(this.colorTopLeft);
        result = result * 31 + Integer.hashCode(this.colorTopRight);
        result = result * 31 + Integer.hashCode(this.colorBottomRight);
        result = result * 31 + Integer.hashCode(this.colorBottomLeft);
        result = result * 31 + Float.hashCode(this.smoothness);
        result = result * 31 + Integer.hashCode(this.dotColor);
        result = result * 31 + Float.hashCode(this.dotSize);
        result = result * 31 + Float.hashCode(this.dotSpacing);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltHalftoneRectangle)) {
            return false;
        }
        BuiltHalftoneRectangle builtHalftoneRectangle = (BuiltHalftoneRectangle)other;
        if (Float.compare(this.x, builtHalftoneRectangle.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtHalftoneRectangle.y) != 0) {
            return false;
        }
        if (Float.compare(this.width, builtHalftoneRectangle.width) != 0) {
            return false;
        }
        if (Float.compare(this.height, builtHalftoneRectangle.height) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopLeft, builtHalftoneRectangle.radiusTopLeft) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopRight, builtHalftoneRectangle.radiusTopRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomRight, builtHalftoneRectangle.radiusBottomRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomLeft, builtHalftoneRectangle.radiusBottomLeft) != 0) {
            return false;
        }
        if (this.colorTopLeft != builtHalftoneRectangle.colorTopLeft) {
            return false;
        }
        if (this.colorTopRight != builtHalftoneRectangle.colorTopRight) {
            return false;
        }
        if (this.colorBottomRight != builtHalftoneRectangle.colorBottomRight) {
            return false;
        }
        if (this.colorBottomLeft != builtHalftoneRectangle.colorBottomLeft) {
            return false;
        }
        if (Float.compare(this.smoothness, builtHalftoneRectangle.smoothness) != 0) {
            return false;
        }
        if (this.dotColor != builtHalftoneRectangle.dotColor) {
            return false;
        }
        if (Float.compare(this.dotSize, builtHalftoneRectangle.dotSize) != 0) {
            return false;
        }
        return Float.compare(this.dotSpacing, builtHalftoneRectangle.dotSpacing) == 0;
    }


    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\n\u0010\tR\u0014\u0010\u000b\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u0006R\u0014\u0010\f\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0006\u00a8\u0006\r"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/recthalftone/BuiltHalftoneRectangle.Companion;", "", "<init>", "()V", "", "DEFAULT_SMOOTHNESS", "F", "", "DEFAULT_COLOR", "I", "DEFAULT_DOT_COLOR", "DEFAULT_DOT_SIZE", "DEFAULT_DOT_SPACING", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

