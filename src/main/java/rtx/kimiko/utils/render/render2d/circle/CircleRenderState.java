/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.FilterMode
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gl.GpuSampler
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.gui.ScreenRect
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fc
 */
package rtx.kimiko.utils.render.render2d.circle;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.gui.ScreenRect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;
import rtx.kimiko.utils.render.render2d.PoseCache;
import rtx.kimiko.utils.render.render2d.blur.BlurCapture;
import rtx.kimiko.utils.render.render2d.circle.BuiltCircle;
import rtx.kimiko.utils.render.render2d.circle.CircleBatch;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 &2\u00020\u0001:\u0001&B-\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J?\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u001dH\u0016\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010 J\u0011\u0010!\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\b!\u0010 R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\"R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010#R\u0016\u0010\t\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010$R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010%R\u0016\u0010!\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010#\u00a8\u0006'"}, d2={"Lrtx/kimiko/utils/render/render2d/circle/CircleRenderState;", "Lnet/minecraft/SimpleGuiElementRenderState;", "Lorg/joml/Matrix3x2f;", "pose", "Lrtx/kimiko/utils/render/render2d/circle/BuiltCircle;", "circle", "Lnet/minecraft/ScreenRect;", "scissorArea", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "backdropCapture", "<init>", "(Lorg/joml/Matrix3x2f;Lrtx/kimiko/utils/render/render2d/circle/BuiltCircle;Lnet/minecraft/ScreenRect;Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;)V", "Lnet/minecraft/VertexConsumer;", "consumer", "", "buildVertices", "(Lnet/minecraft/VertexConsumer;)V", "", "x", "y", "", "coordX", "coordY", "batchIndex", "vertex", "(Lnet/minecraft/VertexConsumer;FFIII)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "()Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lnet/minecraft/TextureSetup;", "textureSetup", "()Lnet/minecraft/TextureSetup;", "()Lnet/minecraft/ScreenRect;", "bounds", "Lrtx/kimiko/utils/render/render2d/circle/BuiltCircle;", "Lnet/minecraft/ScreenRect;", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "Lorg/joml/Matrix3x2f;", "Companion", "rtx.kimiko:kimiko"})
public final class CircleRenderState
implements SimpleGuiElementRenderState {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final BuiltCircle circle;
    @Nullable
    private final ScreenRect scissorArea;
    @Nullable
    private final BlurCapture backdropCapture;
    @NotNull
    private final Matrix3x2f pose;
    @Nullable
    private final ScreenRect bounds;

    public CircleRenderState(@NotNull Matrix3x2f pose, @NotNull BuiltCircle circle, @Nullable ScreenRect scissorArea, @Nullable BlurCapture backdropCapture) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)circle, (String)"circle");
        this.circle = circle;
        this.scissorArea = scissorArea;
        this.backdropCapture = backdropCapture;
        this.pose = PoseCache.snapshot(pose);
        float extent = CircleRenderState.Companion.extent(this.circle);
        int boundsX = (int)Math.floor(this.circle.x() - extent);
        int boundsY = (int)Math.floor(this.circle.y() - extent);
        int boundsSize = (int)Math.ceil(extent * 2.0f);
        ScreenRect screenRect2 = new ScreenRect(boundsX, boundsY, boundsSize, boundsSize).transformEachVertex((Matrix3x2fc)this.pose);
        Intrinsics.checkNotNullExpressionValue((Object)screenRect2, (String)"transformMaxBounds(...)");
        ScreenRect transformedBounds = screenRect2;
        this.bounds = this.scissorArea == null ? transformedBounds : this.scissorArea.intersection(transformedBounds);
    }

    public /* synthetic */ CircleRenderState(Matrix3x2f matrix3x2f, BuiltCircle builtCircle, ScreenRect screenRect2, BlurCapture blurCapture, int n, DefaultConstructorMarker defaultConstructorMarker) {
        this(matrix3x2f, builtCircle, screenRect2, (n & 8) != 0 ? null : blurCapture);
    }

    public void setupVertices(@NotNull VertexConsumer consumer) {
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        int batchIndex = CircleBatch.INSTANCE.reserve(this.circle);
        if (batchIndex < 0) {
            return;
        }
        float extent = CircleRenderState.Companion.extent(this.circle);
        float x0 = this.circle.x() - extent;
        float y0 = this.circle.y() - extent;
        float x1 = this.circle.x() + extent;
        float y1 = this.circle.y() + extent;
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
        BlurCapture blurCapture = this.backdropCapture;
        return (blurCapture != null ? blurCapture.backdropView : null) != null ? CircleBatch.ADAPTIVE_PIPELINE : CircleBatch.PIPELINE;
    }

    @NotNull
    public TextureSetup textureSetup() {
        BlurCapture blurCapture = this.backdropCapture;
        if (blurCapture == null) {
            TextureSetup textureSetup2 = TextureSetup.empty();
            Intrinsics.checkNotNullExpressionValue((Object)textureSetup2, (String)"noTexture(...)");
            return textureSetup2;
        }
        BlurCapture capture = blurCapture;
        GpuTextureView gpuTextureView = capture.backdropView;
        if (gpuTextureView == null) {
            TextureSetup textureSetup3 = TextureSetup.empty();
            Intrinsics.checkNotNullExpressionValue((Object)textureSetup3, (String)"noTexture(...)");
            return textureSetup3;
        }
        GpuTextureView backdrop = gpuTextureView;
        GpuSampler gpuSampler2 = capture.backdropSampler;
        if (gpuSampler2 == null) {
            GpuSampler gpuSampler3 = RenderSystem.getSamplerCache().get(FilterMode.LINEAR);
            gpuSampler2 = gpuSampler3;
            Intrinsics.checkNotNullExpressionValue((Object)gpuSampler3, (String)"getClampToEdge(...)");
        }
        GpuSampler sampler = gpuSampler2;
        TextureSetup textureSetup4 = TextureSetup.of((GpuTextureView)backdrop, (GpuSampler)sampler);
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup4, (String)"singleTexture(...)");
        return textureSetup4;
    }

    @Nullable
    public ScreenRect scissorArea() {
        return this.scissorArea;
    }

    @Nullable
    public ScreenRect bounds() {
        return this.bounds;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/utils/render/render2d/circle/CircleRenderState.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/render2d/circle/BuiltCircle;", "circle", "", "extent", "(Lrtx/kimiko/utils/render/render2d/circle/BuiltCircle;)F", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float extent(BuiltCircle circle) {
            return circle.radius() + Math.max(circle.smoothness(), 0.0f);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

