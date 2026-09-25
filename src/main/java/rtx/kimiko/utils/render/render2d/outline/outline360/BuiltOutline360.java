/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.outline.outline360;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.render2d.outline.outline360.Outline360Range;
import rtx.kimiko.utils.render.render2d.outline.outline360.Outline360Renderer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 -2\u00020\u0001:\u0001-B\u007f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011\u00a2\u0006\u0004\b\u0014\u0010\u0015BQ\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011\u00a2\u0006\u0004\b\u0014\u0010\u0017Bi\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011\u00a2\u0006\u0004\b\u0014\u0010\u0018J\u0015\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001aJ\u0015\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001aJ\u0017\u0010 \u001a\u00020\u001f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u00a2\u0006\u0004\b \u0010!J\r\u0010#\u001a\u00020\"\u00a2\u0006\u0004\b#\u0010$R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010'\u001a\u0004\b\u0003\u0010(R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010'\u001a\u0004\b\u0004\u0010(R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010'\u001a\u0004\b\u0005\u0010(R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010'\u001a\u0004\b\u0006\u0010(R%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010'\u001a\u0004\b\u0007\u0010(R%\u0010\b\u001a\u00020\u00028\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010'\u001a\u0004\b\b\u0010(R%\u0010\t\u001a\u00020\u00028\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010'\u001a\u0004\b\t\u0010(R%\u0010\n\u001a\u00020\u00028\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010'\u001a\u0004\b\n\u0010(R%\u0010\u000b\u001a\u00020\u00028\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010'\u001a\u0004\b\u000b\u0010(R%\u0010\r\u001a\u00020\f8\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010)\u001a\u0004\b\r\u0010*R%\u0010\u000e\u001a\u00020\u00028\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010'\u001a\u0004\b\u000e\u0010(R%\u0010\u000f\u001a\u00020\u00028\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010'\u001a\u0004\b\u000f\u0010(R%\u0010\u0010\u001a\u00020\u00028\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010'\u001a\u0004\b\u0010\u0010(R+\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\u0013\u00a2\u0006\f\n\u0004\b\u0013\u0010+\u001a\u0004\b\u0013\u0010,\u00a8\u0006."}, d2={"Lrtx/kimiko/utils/render/render2d/outline/outline360/BuiltOutline360;", "", "", "x", "y", "width", "height", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "thickness", "", "defaultColor", "smoothness", "blendDegrees", "angleOffsetDegrees", "", "Lrtx/kimiko/utils/render/render2d/outline/outline360/Outline360Range;", "ranges", "<init>", "(FFFFFFFFFIFFFLjava/util/List;)V", "radius", "(FFFFFFILjava/util/List;)V", "(FFFFFFFFFILjava/util/List;)V", "withSmoothness", "(F)Lrtx/kimiko/utils/render/render2d/outline/outline360/BuiltOutline360;", "withBlendDegrees", "withAngleOffset", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "", "visible", "()Z", "Lkotlin/jvm/JvmName;", "name", "F", "()F", "I", "()I", "Ljava/util/List;", "()Ljava/util/List;", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltOutline360 {
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
    private final float thickness;
    private final int defaultColor;
    private final float smoothness;
    private final float blendDegrees;
    private final float angleOffsetDegrees;
    @NotNull
    private final List<Outline360Range> ranges;
    public static final float DEFAULT_SMOOTHNESS = 0.5f;
    public static final float DEFAULT_BLEND_DEGREES = 14.0f;
    public static final int DEFAULT_COLOR = -13619152;

    public BuiltOutline360(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, float thickness, int defaultColor, float smoothness, float blendDegrees, float angleOffsetDegrees, @Nullable List<Outline360Range> ranges) {
        List list;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radiusTopLeft = radiusTopLeft;
        this.radiusTopRight = radiusTopRight;
        this.radiusBottomRight = radiusBottomRight;
        this.radiusBottomLeft = radiusBottomLeft;
        this.thickness = thickness;
        this.defaultColor = defaultColor;
        this.smoothness = smoothness;
        this.blendDegrees = blendDegrees;
        this.angleOffsetDegrees = angleOffsetDegrees;
        if (ranges == null) {
            list = CollectionsKt.emptyList();
        } else {
            List list2 = List.copyOf((Collection)ranges);
            list = list2;
            Intrinsics.checkNotNullExpressionValue(list2, (String)"copyOf(...)");
        }
        this.ranges = list;
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

    @JvmName(name="thickness")
    public final float thickness() {
        return this.thickness;
    }

    @JvmName(name="defaultColor")
    public final int defaultColor() {
        return this.defaultColor;
    }

    @JvmName(name="smoothness")
    public final float smoothness() {
        return this.smoothness;
    }

    @JvmName(name="blendDegrees")
    public final float blendDegrees() {
        return this.blendDegrees;
    }

    @JvmName(name="angleOffsetDegrees")
    public final float angleOffsetDegrees() {
        return this.angleOffsetDegrees;
    }

    @JvmName(name="ranges")
    @NotNull
    public final List<Outline360Range> ranges() {
        return this.ranges;
    }

    public BuiltOutline360(float x, float y, float width, float height, float radius, float thickness, int defaultColor, @Nullable List<Outline360Range> ranges) {
        this(x, y, width, height, radius, radius, radius, radius, thickness, defaultColor, 0.5f, 14.0f, 0.0f, ranges);
    }

    public BuiltOutline360(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, float thickness, int defaultColor, @Nullable List<Outline360Range> ranges) {
        this(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, thickness, defaultColor, 0.5f, 14.0f, 0.0f, ranges);
    }

    @NotNull
    public final BuiltOutline360 withSmoothness(float smoothness) {
        return new BuiltOutline360(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.thickness, this.defaultColor, smoothness, this.blendDegrees, this.angleOffsetDegrees, this.ranges);
    }

    @NotNull
    public final BuiltOutline360 withBlendDegrees(float blendDegrees) {
        return new BuiltOutline360(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.thickness, this.defaultColor, this.smoothness, blendDegrees, this.angleOffsetDegrees, this.ranges);
    }

    @NotNull
    public final BuiltOutline360 withAngleOffset(float angleOffsetDegrees) {
        return new BuiltOutline360(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.thickness, this.defaultColor, this.smoothness, this.blendDegrees, angleOffsetDegrees, this.ranges);
    }

    public final void render(@Nullable DrawContext graphics) {
        Outline360Renderer.Companion.getInstance().draw(graphics, this);
    }

    public final boolean visible() {
        return this.width > 0.0f && this.height > 0.0f && this.thickness > 0.0f && this.defaultColor >>> 24 != 0;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\t\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/utils/render/render2d/outline/outline360/BuiltOutline360.Companion;", "", "<init>", "()V", "", "DEFAULT_SMOOTHNESS", "F", "DEFAULT_BLEND_DEGREES", "", "DEFAULT_COLOR", "I", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

