/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.FilterMode
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  kotlin.Metadata
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
package rtx.kimiko.utils.render.render2d.sectormask;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import kotlin.Metadata;
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
import rtx.kimiko.utils.render.render2d.sectormask.BuiltSectorMask;
import rtx.kimiko.utils.render.render2d.sectormask.SectorMaskBatch;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ?\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0016\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\u001eJ\u0011\u0010\u001f\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\b\u001f\u0010\u001eR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010 R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010!R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\"R\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010!R\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010#\u00a8\u0006$"}, d2={"Lrtx/kimiko/utils/render/render2d/sectormask/SectorMaskRenderState;", "Lnet/minecraft/SimpleGuiElementRenderState;", "Lorg/joml/Matrix3x2f;", "pose", "Lrtx/kimiko/utils/render/render2d/sectormask/BuiltSectorMask;", "mask", "Lnet/minecraft/ScreenRect;", "scissorArea", "<init>", "(Lorg/joml/Matrix3x2f;Lrtx/kimiko/utils/render/render2d/sectormask/BuiltSectorMask;Lnet/minecraft/ScreenRect;)V", "Lnet/minecraft/VertexConsumer;", "consumer", "", "buildVertices", "(Lnet/minecraft/VertexConsumer;)V", "", "x", "y", "", "coordX", "coordY", "batchIndex", "vertex", "(Lnet/minecraft/VertexConsumer;FFIII)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "()Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lnet/minecraft/TextureSetup;", "textureSetup", "()Lnet/minecraft/TextureSetup;", "()Lnet/minecraft/ScreenRect;", "bounds", "Lrtx/kimiko/utils/render/render2d/sectormask/BuiltSectorMask;", "Lnet/minecraft/ScreenRect;", "Lorg/joml/Matrix3x2f;", "Lnet/minecraft/TextureSetup;", "rtx.kimiko:kimiko"})
public final class SectorMaskRenderState
implements SimpleGuiElementRenderState {
    @NotNull
    private final BuiltSectorMask mask;
    @Nullable
    private final ScreenRect scissorArea;
    @NotNull
    private final Matrix3x2f pose;
    @Nullable
    private final ScreenRect bounds;
    @NotNull
    private final TextureSetup textureSetup;

    public SectorMaskRenderState(@NotNull Matrix3x2f pose, @NotNull BuiltSectorMask mask, @Nullable ScreenRect scissorArea) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)mask, (String)"mask");
        this.mask = mask;
        this.scissorArea = scissorArea;
        this.pose = PoseCache.snapshot(pose);
        GpuTextureView gpuTextureView = this.mask.texture();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        TextureSetup textureSetup2 = TextureSetup.of((GpuTextureView)gpuTextureView, (GpuSampler)RenderSystem.getSamplerCache().getRepeated(FilterMode.LINEAR));
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup2, (String)"singleTexture(...)");
        this.textureSetup = textureSetup2;
        ScreenRect screenRect2 = new ScreenRect((int)Math.floor(this.mask.x()), (int)Math.floor(this.mask.y()), (int)Math.ceil(this.mask.size()), (int)Math.ceil(this.mask.size())).transformEachVertex((Matrix3x2fc)this.pose);
        Intrinsics.checkNotNullExpressionValue((Object)screenRect2, (String)"transformMaxBounds(...)");
        ScreenRect transformedBounds = screenRect2;
        this.bounds = this.scissorArea == null ? transformedBounds : this.scissorArea.intersection(transformedBounds);
    }

    public void setupVertices(@NotNull VertexConsumer consumer) {
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        int batchIndex = SectorMaskBatch.INSTANCE.reserve(this.mask);
        if (batchIndex < 0) {
            return;
        }
        float x0 = this.mask.x();
        float y0 = this.mask.y();
        float x1 = this.mask.x() + this.mask.size();
        float y1 = this.mask.y() + this.mask.size();
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
        return SectorMaskBatch.PIPELINE;
    }

    @NotNull
    public TextureSetup textureSetup() {
        return this.textureSetup;
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

