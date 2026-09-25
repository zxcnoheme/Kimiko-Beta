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
package rtx.kimiko.utils.render.render2d.zippy;

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
import rtx.kimiko.utils.render.render2d.zippy.BuiltZippy;
import rtx.kimiko.utils.render.render2d.zippy.ZippyBatch;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u000b\u0010\fJ?\u0010\u0014\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0011\u0010\u001f\u001a\u0004\u0018\u00010\u001cH\u0016\u00a2\u0006\u0004\b\u001f\u0010\u001eR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010 R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010!R\u0014\u0010\u001f\u001a\u00020\u001c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\"\u00a8\u0006#"}, d2={"Lrtx/kimiko/utils/render/render2d/zippy/ZippyRenderState;", "Lnet/minecraft/SimpleGuiElementRenderState;", "Lorg/joml/Matrix3x2f;", "pose", "Lrtx/kimiko/utils/render/render2d/zippy/BuiltZippy;", "zippy", "<init>", "(Lorg/joml/Matrix3x2f;Lrtx/kimiko/utils/render/render2d/zippy/BuiltZippy;)V", "Lnet/minecraft/VertexConsumer;", "consumer", "", "buildVertices", "(Lnet/minecraft/VertexConsumer;)V", "", "x", "y", "", "coordX", "coordY", "batchIndex", "vertex", "(Lnet/minecraft/VertexConsumer;FFIII)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "()Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lnet/minecraft/TextureSetup;", "textureSetup", "()Lnet/minecraft/TextureSetup;", "Lnet/minecraft/ScreenRect;", "scissorArea", "()Lnet/minecraft/ScreenRect;", "bounds", "Lrtx/kimiko/utils/render/render2d/zippy/BuiltZippy;", "Lorg/joml/Matrix3x2f;", "Lnet/minecraft/ScreenRect;", "rtx.kimiko:kimiko"})
public final class ZippyRenderState
implements SimpleGuiElementRenderState {
    @NotNull
    private final BuiltZippy zippy;
    @NotNull
    private final Matrix3x2f pose;
    @NotNull
    private final ScreenRect bounds;

    public ZippyRenderState(@NotNull Matrix3x2f pose, @NotNull BuiltZippy zippy) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)zippy, (String)"zippy");
        this.zippy = zippy;
        this.pose = PoseCache.snapshot(pose);
        ScreenRect screenRect2 = new ScreenRect(Math.round(this.zippy.x()), Math.round(this.zippy.y()), Math.round(this.zippy.width()), Math.round(this.zippy.height())).transformEachVertex((Matrix3x2fc)this.pose);
        Intrinsics.checkNotNullExpressionValue((Object)screenRect2, (String)"transformMaxBounds(...)");
        this.bounds = screenRect2;
    }

    public void setupVertices(@NotNull VertexConsumer consumer) {
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        int batchIndex = ZippyBatch.INSTANCE.reserve(this.zippy);
        if (batchIndex < 0) {
            return;
        }
        float x0 = this.zippy.x();
        float y0 = this.zippy.y();
        float x1 = this.zippy.x() + this.zippy.width();
        float y1 = this.zippy.y() + this.zippy.height();
        this.vertex(consumer, x0, y0, 0, 0, batchIndex);
        this.vertex(consumer, x0, y1, 0, 255, batchIndex);
        this.vertex(consumer, x1, y1, 255, 255, batchIndex);
        this.vertex(consumer, x1, y0, 255, 0, batchIndex);
    }

    private final void vertex(VertexConsumer consumer, float x, float y, int coordX, int coordY, int batchIndex) {
        consumer.vertex((Matrix3x2fc)this.pose, x, y).color(coordX, coordY, 255, 255).lineWidth((float)(batchIndex + 1));
    }

    @NotNull
    public RenderPipeline pipeline() {
        return ZippyBatch.PIPELINE;
    }

    @NotNull
    public TextureSetup textureSetup() {
        TextureSetup textureSetup2 = TextureSetup.empty();
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup2, (String)"noTexture(...)");
        return textureSetup2;
    }

    @Nullable
    public ScreenRect scissorArea() {
        return null;
    }

    @Nullable
    public ScreenRect bounds() {
        return this.bounds;
    }
}

