/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.gui.ScreenRect
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fc
 */
package rtx.kimiko.utils.render.render2d.shape;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.gui.ScreenRect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;
import rtx.kimiko.utils.render.render2d.PoseCache;
import rtx.kimiko.utils.render.render2d.blur.BlurCapture;
import rtx.kimiko.utils.render.render2d.shape.BuiltShape;
import rtx.kimiko.utils.render.render2d.shape.ShapeBatch;
import rtx.kimiko.utils.render.render2d.shape.ShapeEntry;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J/\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0016\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\u001eJ\u0011\u0010\u001f\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\b\u001f\u0010\u001eR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010 R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010!R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\"R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010#R\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010!\u00a8\u0006$"}, d2={"Lrtx/kimiko/utils/render/render2d/shape/ShapeRenderState;", "Lnet/minecraft/SimpleGuiElementRenderState;", "Lorg/joml/Matrix3x2f;", "pose", "Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;", "shape", "Lnet/minecraft/ScreenRect;", "scissorArea", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "capture", "<init>", "(Lorg/joml/Matrix3x2f;Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;Lnet/minecraft/ScreenRect;Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;)V", "Lnet/minecraft/VertexConsumer;", "consumer", "", "buildVertices", "(Lnet/minecraft/VertexConsumer;)V", "", "x", "y", "", "batchIndex", "vertex", "(Lnet/minecraft/VertexConsumer;FFI)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "()Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lnet/minecraft/TextureSetup;", "textureSetup", "()Lnet/minecraft/TextureSetup;", "()Lnet/minecraft/ScreenRect;", "bounds", "Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;", "Lnet/minecraft/ScreenRect;", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "Lorg/joml/Matrix3x2f;", "rtx.kimiko:kimiko"})
public final class ShapeRenderState
implements SimpleGuiElementRenderState {
    @NotNull
    private final BuiltShape shape;
    @Nullable
    private final ScreenRect scissorArea;
    @NotNull
    private final BlurCapture capture;
    @NotNull
    private final Matrix3x2f pose;
    @Nullable
    private final ScreenRect bounds;

    public ShapeRenderState(@NotNull Matrix3x2f pose, @NotNull BuiltShape shape, @Nullable ScreenRect scissorArea, @NotNull BlurCapture capture) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)shape, (String)"shape");
        Intrinsics.checkNotNullParameter((Object)capture, (String)"capture");
        this.shape = shape;
        this.scissorArea = scissorArea;
        this.capture = capture;
        this.pose = PoseCache.snapshot(pose);
        ScreenRect screenRect2 = new ScreenRect(Math.round(this.shape.x()), Math.round(this.shape.y()), Math.round(this.shape.width()), Math.round(this.shape.height())).transformEachVertex((Matrix3x2fc)this.pose);
        Intrinsics.checkNotNullExpressionValue((Object)screenRect2, (String)"transformMaxBounds(...)");
        ScreenRect transformedBounds = screenRect2;
        this.bounds = this.scissorArea == null ? transformedBounds : this.scissorArea.intersection(transformedBounds);
    }

    public void setupVertices(@NotNull VertexConsumer consumer) {
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        int batchIndex = ShapeBatch.INSTANCE.reserve(new ShapeEntry(this.shape, this.capture));
        if (batchIndex < 0) {
            return;
        }
        float x0 = this.shape.x();
        float y0 = this.shape.y();
        float x1 = this.shape.x() + this.shape.width();
        float y1 = this.shape.y() + this.shape.height();
        this.vertex(consumer, x0, y0, batchIndex);
        this.vertex(consumer, x0, y1, batchIndex);
        this.vertex(consumer, x1, y1, batchIndex);
        this.vertex(consumer, x1, y0, batchIndex);
    }

    private final void vertex(VertexConsumer consumer, float x, float y, int batchIndex) {
        consumer.vertex((Matrix3x2fc)this.pose, x, y).color(this.shape.color()).lineWidth((float)(batchIndex + 1));
    }

    @NotNull
    public RenderPipeline pipeline() {
        return ShapeBatch.PIPELINE;
    }

    @NotNull
    public TextureSetup textureSetup() {
        return this.capture.setup;
    }

    @Nullable
    public ScreenRect scissorArea() {
        return this.scissorArea;
    }

    @Nullable
    public ScreenRect bounds() {
        return this.bounds;
    }
}

