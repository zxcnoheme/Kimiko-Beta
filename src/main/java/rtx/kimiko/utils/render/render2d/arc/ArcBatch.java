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
package rtx.kimiko.utils.render.render2d.arc;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.core.batch.UiBatch;
import rtx.kimiko.utils.render.core.pipeline.ClientPipelines2D;
import rtx.kimiko.utils.render.core.uniform.UniformWriter;
import rtx.kimiko.utils.render.render2d.arc.ArcBatchKt;
import rtx.kimiko.utils.render.render2d.arc.BuiltArc;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002H\u0014\u00a2\u0006\u0004\b\t\u0010\nR\u0019\u0010\r\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\f\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f8\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0014"}, d2={"Lrtx/kimiko/utils/render/render2d/arc/ArcBatch;", "Lrtx/kimiko/utils/render/core/batch/UiBatch;", "Lrtx/kimiko/utils/render/render2d/arc/BuiltArc;", "<init>", "()V", "Lrtx/kimiko/utils/render/core/uniform/UniformWriter;", "writer", "item", "", "write", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Lrtx/kimiko/utils/render/render2d/arc/BuiltArc;)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "", "pipelines", "[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "getPipelines", "()[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "rtx.kimiko:kimiko"})
public final class ArcBatch
extends UiBatch<BuiltArc> {
    @NotNull
    public static final ArcBatch INSTANCE = new ArcBatch();
    @JvmField
    @NotNull
    public static final RenderPipeline PIPELINE = ClientPipelines2D.ARC_DIVIDER;
    @NotNull
    private static final RenderPipeline[] pipelines;

    private ArcBatch() {
        super("arc", ArcBatchKt.access$getARC_LAYOUT$p(), "ArcParamsArray", 256, 0, 16, null);
    }

    @Override
    @NotNull
    public RenderPipeline[] getPipelines() {
        return pipelines;
    }

    @Override
    protected void write(@NotNull UniformWriter writer, @NotNull BuiltArc item) {
        Intrinsics.checkNotNullParameter((Object)writer, (String)"writer");
        Intrinsics.checkNotNullParameter((Object)item, (String)"item");
        writer.putVec4("shape", item.halfWidth(), Math.max(item.lift(), 0.0f), Math.max(item.thickness(), 0.5f), Math.max(item.feather(), 0.5f));
        writer.putColor("tint", item.color());
    }

    static {
        RenderPipeline[] renderPipelineArray = new RenderPipeline[]{ClientPipelines2D.ARC_DIVIDER};
        pipelines = renderPipelineArray;
    }
}

