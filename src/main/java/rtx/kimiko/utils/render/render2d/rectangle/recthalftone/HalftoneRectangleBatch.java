/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.render2d.rectangle.recthalftone;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.core.batch.UiBatch;
import rtx.kimiko.utils.render.core.pipeline.ClientPipelines2D;
import rtx.kimiko.utils.render.core.uniform.UniformWriter;
import rtx.kimiko.utils.render.render2d.rectangle.recthalftone.BuiltHalftoneRectangle;
import rtx.kimiko.utils.render.render2d.rectangle.recthalftone.HalftoneRectangleBatchKt;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001b\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0014\u00a2\u0006\u0004\b\r\u0010\u000eR\u0019\u0010\u0011\u001a\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00138\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/recthalftone/HalftoneRectangleBatch;", "Lrtx/kimiko/utils/render/core/batch/UiBatch;", "Lrtx/kimiko/utils/render/render2d/rectangle/recthalftone/BuiltHalftoneRectangle;", "<init>", "()V", "rectangle", "Lkotlin/jvm/JvmStatic;", "normalize", "(Lrtx/kimiko/utils/render/render2d/rectangle/recthalftone/BuiltHalftoneRectangle;)Lrtx/kimiko/utils/render/render2d/rectangle/recthalftone/BuiltHalftoneRectangle;", "Lrtx/kimiko/utils/render/core/uniform/UniformWriter;", "writer", "item", "", "write", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Lrtx/kimiko/utils/render/render2d/rectangle/recthalftone/BuiltHalftoneRectangle;)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "", "pipelines", "[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "getPipelines", "()[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "rtx.kimiko:kimiko"})
public final class HalftoneRectangleBatch
extends UiBatch<BuiltHalftoneRectangle> {
    @NotNull
    public static final HalftoneRectangleBatch INSTANCE = new HalftoneRectangleBatch();
    @JvmField
    @NotNull
    public static final RenderPipeline PIPELINE = ClientPipelines2D.RECT_HALFTONE;
    @NotNull
    private static final RenderPipeline[] pipelines;

    private HalftoneRectangleBatch() {
        super("halftonerect", HalftoneRectangleBatchKt.access$getHALFTONE_LAYOUT$p(), "HalftoneRectangleParamsArray", 384, 0, 16, null);
    }

    @Override
    @NotNull
    public RenderPipeline[] getPipelines() {
        return pipelines;
    }

    @JvmStatic
    @NotNull
    public static final BuiltHalftoneRectangle normalize(@NotNull BuiltHalftoneRectangle rectangle) {
        Intrinsics.checkNotNullParameter((Object)rectangle, (String)"rectangle");
        float x = Math.round(rectangle.x());
        float y = Math.round(rectangle.y());
        float w = Math.max(1.0f, (float)Math.round(rectangle.width()));
        float h = Math.max(1.0f, (float)Math.round(rectangle.height()));
        float maxRadius = Math.max(0.0f, Math.min(w, h) * 0.5f);
        float maxDotSize = Math.max(Math.min(w, h), 0.0f);
        return new BuiltHalftoneRectangle(x, y, w, h, RangesKt.coerceIn((float)rectangle.radiusTopLeft(), (float)0.0f, (float)maxRadius), RangesKt.coerceIn((float)rectangle.radiusTopRight(), (float)0.0f, (float)maxRadius), RangesKt.coerceIn((float)rectangle.radiusBottomRight(), (float)0.0f, (float)maxRadius), RangesKt.coerceIn((float)rectangle.radiusBottomLeft(), (float)0.0f, (float)maxRadius), rectangle.colorTopLeft(), rectangle.colorTopRight(), rectangle.colorBottomRight(), rectangle.colorBottomLeft(), Math.max(rectangle.smoothness(), 0.0f), rectangle.dotColor(), RangesKt.coerceIn((float)rectangle.dotSize(), (float)0.0f, (float)maxDotSize), Math.max(rectangle.dotSpacing(), 0.0f));
    }

    @Override
    protected void write(@NotNull UniformWriter writer, @NotNull BuiltHalftoneRectangle item) {
        Intrinsics.checkNotNullParameter((Object)writer, (String)"writer");
        Intrinsics.checkNotNullParameter((Object)item, (String)"item");
        writer.putVec4("radii", item.radiusTopLeft(), item.radiusTopRight(), item.radiusBottomRight(), item.radiusBottomLeft());
        writer.putVec4("size", item.width(), item.height(), item.smoothness(), item.dotSize());
        writer.putColor("colorTopLeft", item.colorTopLeft());
        writer.putColor("colorTopRight", item.colorTopRight());
        writer.putColor("colorBottomRight", item.colorBottomRight());
        writer.putColor("colorBottomLeft", item.colorBottomLeft());
        writer.putColor("dotColor", item.dotColor());
        writer.putVec4("dot", item.dotSpacing(), item.x(), item.y(), 0.0f);
    }

    static {
        RenderPipeline[] renderPipelineArray = new RenderPipeline[]{ClientPipelines2D.RECT_HALFTONE};
        pipelines = renderPipelineArray;
    }
}

