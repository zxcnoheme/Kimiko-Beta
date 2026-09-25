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
package rtx.kimiko.utils.render.render2d.veil;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.core.batch.UiBatch;
import rtx.kimiko.utils.render.core.pipeline.ClientPipelines2D;
import rtx.kimiko.utils.render.core.uniform.UniformWriter;
import rtx.kimiko.utils.render.render2d.veil.BuiltVeil;
import rtx.kimiko.utils.render.render2d.veil.VeilBatchKt;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002H\u0014\u00a2\u0006\u0004\b\t\u0010\nR\u0019\u0010\r\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\f\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f8\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0014"}, d2={"Lrtx/kimiko/utils/render/render2d/veil/VeilBatch;", "Lrtx/kimiko/utils/render/core/batch/UiBatch;", "Lrtx/kimiko/utils/render/render2d/veil/BuiltVeil;", "<init>", "()V", "Lrtx/kimiko/utils/render/core/uniform/UniformWriter;", "writer", "item", "", "write", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Lrtx/kimiko/utils/render/render2d/veil/BuiltVeil;)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "", "pipelines", "[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "getPipelines", "()[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "rtx.kimiko:kimiko"})
public final class VeilBatch
extends UiBatch<BuiltVeil> {
    @NotNull
    public static final VeilBatch INSTANCE = new VeilBatch();
    @JvmField
    @NotNull
    public static final RenderPipeline PIPELINE = ClientPipelines2D.VEIL_UNION;
    @NotNull
    private static final RenderPipeline[] pipelines;

    private VeilBatch() {
        super("veil", VeilBatchKt.access$getVEIL_LAYOUT$p(), "VeilParamsArray", 4, 0, 16, null);
    }

    @Override
    @NotNull
    public RenderPipeline[] getPipelines() {
        return pipelines;
    }

    @Override
    protected void write(@NotNull UniformWriter writer, @NotNull BuiltVeil item) {
        Intrinsics.checkNotNullParameter((Object)writer, (String)"writer");
        Intrinsics.checkNotNullParameter((Object)item, (String)"item");
        writer.putVec4("bounds", item.boundsX, item.boundsY, item.boundsW, item.boundsH);
        writer.putColor("tint", item.color);
        writer.putVec4("panel", item.panelX, item.panelY, item.panelW, item.panelH);
        writer.putVec4("meta", item.panelRadius, item.rectCount, 0.0f, 0.0f);
        float[] rects = item.rects;
        int n = item.rectCount;
        for (int index = 0; index < n; ++index) {
            int src = index * 6;
            double rotation = Math.toRadians(rects[src + 4]);
            writer.putVec4At("rects", index * 2, rects[src], rects[src + 1], rects[src + 2], rects[src + 3]);
            writer.putVec4At("rects", index * 2 + 1, (float)Math.cos(rotation), (float)Math.sin(rotation), rects[src + 5], 0.0f);
        }
    }

    static {
        RenderPipeline[] renderPipelineArray = new RenderPipeline[]{ClientPipelines2D.VEIL_UNION};
        pipelines = renderPipelineArray;
    }
}

