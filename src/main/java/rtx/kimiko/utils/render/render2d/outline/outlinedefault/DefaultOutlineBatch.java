/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.systems.RenderPass
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.render2d.outline.outlinedefault;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderPass;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.core.batch.UiBatch;
import rtx.kimiko.utils.render.core.pipeline.ClientPipelines2D;
import rtx.kimiko.utils.render.core.uniform.UniformWriter;
import rtx.kimiko.utils.render.others.RoundedScissor;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.render.render2d.outline.outlinedefault.BuiltOutline;
import rtx.kimiko.utils.render.render2d.outline.outlinedefault.DefaultOutlineBatchKt;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001b\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0014\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0014\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0015\u001a\u00020\u00138\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0014\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R \u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u00178\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/render/render2d/outline/outlinedefault/DefaultOutlineBatch;", "Lrtx/kimiko/utils/render/core/batch/UiBatch;", "Lrtx/kimiko/utils/render/render2d/outline/outlinedefault/BuiltOutline;", "<init>", "()V", "outline", "Lkotlin/jvm/JvmStatic;", "normalize", "(Lrtx/kimiko/utils/render/render2d/outline/outlinedefault/BuiltOutline;)Lrtx/kimiko/utils/render/render2d/outline/outlinedefault/BuiltOutline;", "Lrtx/kimiko/utils/render/core/uniform/UniformWriter;", "writer", "item", "", "write", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Lrtx/kimiko/utils/render/render2d/outline/outlinedefault/BuiltOutline;)V", "Lcom/mojang/blaze3d/systems/RenderPass;", "pass", "bindExtraUniforms", "(Lcom/mojang/blaze3d/systems/RenderPass;)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "", "pipelines", "[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "getPipelines", "()[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "rtx.kimiko:kimiko"})
public final class DefaultOutlineBatch
extends UiBatch<BuiltOutline> {
    @NotNull
    public static final DefaultOutlineBatch INSTANCE = new DefaultOutlineBatch();
    @JvmField
    @NotNull
    public static final RenderPipeline PIPELINE = ClientPipelines2D.OUTLINE_DEFAULT;
    @NotNull
    private static final RenderPipeline[] pipelines;

    private DefaultOutlineBatch() {
        super("outline", DefaultOutlineBatchKt.access$getOUTLINE_LAYOUT$p(), "OutlineParamsArray", 400, 0, 16, null);
    }

    @Override
    @NotNull
    public RenderPipeline[] getPipelines() {
        return pipelines;
    }

    @JvmStatic
    @NotNull
    public static final BuiltOutline normalize(@NotNull BuiltOutline outline) {
        Intrinsics.checkNotNullParameter((Object)outline, (String)"outline");
        float maxRadius = Math.max(0.0f, Math.max(outline.width(), outline.height()) * 0.5f);
        float maxThickness = Math.max(0.0f, Math.min(outline.width(), outline.height()) * 0.5f);
        float sx = outline.scissorX();
        float sy = outline.scissorY();
        float sw = outline.scissorWidth();
        float sh = outline.scissorHeight();
        float srtl = outline.scissorRadiusTopLeft();
        float srtr = outline.scissorRadiusTopRight();
        float srbr = outline.scissorRadiusBottomRight();
        float srbl = outline.scissorRadiusBottomLeft();
        float scos = outline.scissorCos();
        float ssin = outline.scissorSin();
        float sfade = outline.scissorFade();
        if (RoundedScissor.isEnabled()) {
            sx = RoundedScissor.x();
            sy = RoundedScissor.y();
            sw = RoundedScissor.width();
            sh = RoundedScissor.height();
            srtl = RoundedScissor.radiusTopLeft();
            srtr = RoundedScissor.radiusTopRight();
            srbr = RoundedScissor.radiusBottomRight();
            srbl = RoundedScissor.radiusBottomLeft();
            scos = RoundedScissor.cos();
            ssin = RoundedScissor.sin();
            sfade = RoundedScissor.fadeTop();
        }
        return new BuiltOutline(outline.x(), outline.y(), outline.width(), outline.height(), RangesKt.coerceIn((float)outline.radiusTopLeft(), (float)0.0f, (float)maxRadius), RangesKt.coerceIn((float)outline.radiusTopRight(), (float)0.0f, (float)maxRadius), RangesKt.coerceIn((float)outline.radiusBottomRight(), (float)0.0f, (float)maxRadius), RangesKt.coerceIn((float)outline.radiusBottomLeft(), (float)0.0f, (float)maxRadius), RangesKt.coerceIn((float)outline.thickness(), (float)0.0f, (float)maxThickness), outline.colorTopLeft(), outline.colorTopRight(), outline.colorBottomRight(), outline.colorBottomLeft(), Math.max(outline.smoothness(), 0.0f), sx, sy, sw, sh, srtl, srtr, srbr, srbl, scos, ssin, outline.paletteStrength(), outline.colorOffset(), sfade);
    }

    @Override
    protected void write(@NotNull UniformWriter writer, @NotNull BuiltOutline item) {
        Intrinsics.checkNotNullParameter((Object)writer, (String)"writer");
        Intrinsics.checkNotNullParameter((Object)item, (String)"item");
        writer.putVec4("radii", item.radiusTopLeft(), item.radiusTopRight(), item.radiusBottomRight(), item.radiusBottomLeft());
        writer.putVec4("size", item.width(), item.height(), item.thickness(), item.smoothness());
        writer.putColor("colorTopLeft", item.colorTopLeft());
        writer.putColor("colorTopRight", item.colorTopRight());
        writer.putColor("colorBottomRight", item.colorBottomRight());
        writer.putColor("colorBottomLeft", item.colorBottomLeft());
        writer.putVec4("scissorRect", item.scissorX(), item.scissorY(), item.scissorWidth(), item.scissorHeight());
        writer.putVec4("scissorRadii", item.scissorRadiusTopLeft(), item.scissorRadiusTopRight(), item.scissorRadiusBottomRight(), item.scissorRadiusBottomLeft());
        writer.putVec4("extra", item.scissorCos(), item.scissorSin(), item.paletteStrength(), item.colorOffset());
        writer.putVec4("scissorFade", item.scissorFade(), 0.0f, 0.0f, 0.0f);
    }

    @Override
    protected void bindExtraUniforms(@NotNull RenderPass pass) {
        Intrinsics.checkNotNullParameter((Object)pass, (String)"pass");
        GpuBuffer gpuBuffer = ClientPalette.buffer();
        if (gpuBuffer == null) {
            return;
        }
        GpuBuffer palette = gpuBuffer;
        pass.setUniform("PaletteParams", palette);
    }

    static {
        RenderPipeline[] renderPipelineArray = new RenderPipeline[]{ClientPipelines2D.OUTLINE_DEFAULT};
        pipelines = renderPipelineArray;
    }
}

