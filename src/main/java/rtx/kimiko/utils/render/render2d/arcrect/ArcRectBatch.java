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
package rtx.kimiko.utils.render.render2d.arcrect;

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
import rtx.kimiko.utils.render.render2d.arcrect.ArcRectBatchKt;
import rtx.kimiko.utils.render.render2d.arcrect.BuiltArcRect;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001b\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0014\u00a2\u0006\u0004\b\r\u0010\u000eR\u0019\u0010\u0011\u001a\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00138\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/utils/render/render2d/arcrect/ArcRectBatch;", "Lrtx/kimiko/utils/render/core/batch/UiBatch;", "Lrtx/kimiko/utils/render/render2d/arcrect/BuiltArcRect;", "<init>", "()V", "arc", "Lkotlin/jvm/JvmStatic;", "normalize", "(Lrtx/kimiko/utils/render/render2d/arcrect/BuiltArcRect;)Lrtx/kimiko/utils/render/render2d/arcrect/BuiltArcRect;", "Lrtx/kimiko/utils/render/core/uniform/UniformWriter;", "writer", "item", "", "write", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Lrtx/kimiko/utils/render/render2d/arcrect/BuiltArcRect;)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "", "pipelines", "[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "getPipelines", "()[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "rtx.kimiko:kimiko"})
public final class ArcRectBatch
extends UiBatch<BuiltArcRect> {
    @NotNull
    public static final ArcRectBatch INSTANCE = new ArcRectBatch();
    @JvmField
    @NotNull
    public static final RenderPipeline PIPELINE = ClientPipelines2D.ARC_RECT;
    @NotNull
    private static final RenderPipeline[] pipelines;

    private ArcRectBatch() {
        super("arc_rect", ArcRectBatchKt.access$getARC_RECT_LAYOUT$p(), "ArcRectParamsArray", 256, 0, 16, null);
    }

    @Override
    @NotNull
    public RenderPipeline[] getPipelines() {
        return pipelines;
    }

    @JvmStatic
    @NotNull
    public static final BuiltArcRect normalize(@NotNull BuiltArcRect arc) {
        Intrinsics.checkNotNullParameter((Object)arc, (String)"arc");
        float radius = Math.max(arc.radius(), 0.0f);
        float thickness = RangesKt.coerceIn((float)arc.thickness(), (float)0.0f, (float)(radius * 2.0f));
        float sweep = RangesKt.coerceIn((float)arc.sweepDegrees(), (float)0.0f, (float)360.0f);
        float feather = Math.max(arc.feather(), 0.35f);
        float shift = arc.gradientShift() - (float)Math.floor(arc.gradientShift());
        return BuiltArcRect.copy$default(arc, 0.0f, 0.0f, radius, thickness, 0.0f, sweep, feather, false, shift, 0, 0, 0, 0, 7827, null);
    }

    @Override
    protected void write(@NotNull UniformWriter writer, @NotNull BuiltArcRect item) {
        Intrinsics.checkNotNullParameter((Object)writer, (String)"writer");
        Intrinsics.checkNotNullParameter((Object)item, (String)"item");
        writer.putVec4("shape", item.radius(), item.thickness(), item.feather(), item.roundCaps() ? 1.0f : 0.0f);
        writer.putVec4("angles", (float)Math.toRadians(item.startDegrees()), (float)Math.toRadians(item.sweepDegrees()), item.gradientShift(), 0.0f);
        writer.putColor("colorStart", item.colorStart());
        writer.putColor("colorSecond", item.colorSecond());
        writer.putColor("colorThird", item.colorThird());
        writer.putColor("colorEnd", item.colorEnd());
    }

    static {
        RenderPipeline[] renderPipelineArray = new RenderPipeline[]{PIPELINE};
        pipelines = renderPipelineArray;
    }
}

