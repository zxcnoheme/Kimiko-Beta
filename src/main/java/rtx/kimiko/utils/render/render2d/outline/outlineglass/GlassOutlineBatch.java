/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.render2d.outline.outlineglass;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.core.batch.UiBatch;
import rtx.kimiko.utils.render.core.pipeline.ClientPipelines2D;
import rtx.kimiko.utils.render.core.uniform.UniformWriter;
import rtx.kimiko.utils.render.render2d.blur.BlurCapture;
import rtx.kimiko.utils.render.render2d.outline.outlineglass.BuiltGlassOutline;
import rtx.kimiko.utils.render.render2d.outline.outlineglass.GlassOutlineBatchKt;
import rtx.kimiko.utils.render.render2d.outline.outlineglass.GlassOutlineEntry;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002H\u0014\u00a2\u0006\u0004\b\t\u0010\nJ'\u0010\r\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH\u0014\u00a2\u0006\u0004\b\r\u0010\u000eR\u0019\u0010\u0011\u001a\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00138\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/utils/render/render2d/outline/outlineglass/GlassOutlineBatch;", "Lrtx/kimiko/utils/render/core/batch/UiBatch;", "Lrtx/kimiko/utils/render/render2d/outline/outlineglass/GlassOutlineEntry;", "<init>", "()V", "Lrtx/kimiko/utils/render/core/uniform/UniformWriter;", "writer", "item", "", "write", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Lrtx/kimiko/utils/render/render2d/outline/outlineglass/GlassOutlineEntry;)V", "", "slot", "writeExtra", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Lrtx/kimiko/utils/render/render2d/outline/outlineglass/GlassOutlineEntry;I)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "", "pipelines", "[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "getPipelines", "()[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "rtx.kimiko:kimiko"})
public final class GlassOutlineBatch
extends UiBatch<GlassOutlineEntry> {
    @NotNull
    public static final GlassOutlineBatch INSTANCE = new GlassOutlineBatch();
    @JvmField
    @NotNull
    public static final RenderPipeline PIPELINE = ClientPipelines2D.OUTLINE_GLASS;
    @NotNull
    private static final RenderPipeline[] pipelines;

    private GlassOutlineBatch() {
        super("glassoutline", GlassOutlineBatchKt.access$getGLASS_OUTLINE_LAYOUT$p(), "GlassOutlineParamsArray", 438, 0, 16, null);
    }

    @Override
    @NotNull
    public RenderPipeline[] getPipelines() {
        return pipelines;
    }

    @Override
    protected void write(@NotNull UniformWriter writer, @NotNull GlassOutlineEntry item) {
        Intrinsics.checkNotNullParameter((Object)writer, (String)"writer");
        Intrinsics.checkNotNullParameter((Object)item, (String)"item");
        BuiltGlassOutline o = item.getOutline();
        writer.putVec4("radii", o.radiusTopLeft(), o.radiusTopRight(), o.radiusBottomRight(), o.radiusBottomLeft());
        writer.putVec4("size", o.width(), o.height(), o.thickness(), o.smoothness());
        writer.putVec4("fresnel", o.globalAlpha(), o.fresnelPower(), o.baseAlpha(), o.fresnelMix());
        writer.putColor("fresnelColor", o.fresnelColor());
        writer.putVec4("material", o.fresnelInvert() ? 1.0f : 0.0f, o.distortStrength(), o.z(), Math.max(o.squirt(), 0.001f));
        writer.putColor("color", o.color());
    }

    @Override
    protected void writeExtra(@NotNull UniformWriter writer, @NotNull GlassOutlineEntry item, int slot) {
        Intrinsics.checkNotNullParameter((Object)writer, (String)"writer");
        Intrinsics.checkNotNullParameter((Object)item, (String)"item");
        BlurCapture blurCapture = item.getCapture();
        if (blurCapture == null) {
            return;
        }
        BlurCapture capture = blurCapture;
        writer.putVec4("capture", capture.regionX, capture.regionY, capture.regionW, capture.regionH);
    }

    static {
        RenderPipeline[] renderPipelineArray = new RenderPipeline[]{ClientPipelines2D.OUTLINE_GLASS};
        pipelines = renderPipelineArray;
    }
}

