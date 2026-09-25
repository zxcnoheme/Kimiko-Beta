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
package rtx.kimiko.utils.render.render2d.circle;

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
import rtx.kimiko.utils.render.render2d.circle.BuiltCircle;
import rtx.kimiko.utils.render.render2d.circle.CircleBatchKt;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001b\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0014\u00a2\u0006\u0004\b\r\u0010\u000eR\u0019\u0010\u0011\u001a\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0013\u001a\u00020\u000f8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0012R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00148\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/utils/render/render2d/circle/CircleBatch;", "Lrtx/kimiko/utils/render/core/batch/UiBatch;", "Lrtx/kimiko/utils/render/render2d/circle/BuiltCircle;", "<init>", "()V", "circle", "Lkotlin/jvm/JvmStatic;", "normalize", "(Lrtx/kimiko/utils/render/render2d/circle/BuiltCircle;)Lrtx/kimiko/utils/render/render2d/circle/BuiltCircle;", "Lrtx/kimiko/utils/render/core/uniform/UniformWriter;", "writer", "item", "", "write", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Lrtx/kimiko/utils/render/render2d/circle/BuiltCircle;)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "ADAPTIVE_PIPELINE", "", "pipelines", "[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "getPipelines", "()[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "rtx.kimiko:kimiko"})
public final class CircleBatch
extends UiBatch<BuiltCircle> {
    @NotNull
    public static final CircleBatch INSTANCE = new CircleBatch();
    @JvmField
    @NotNull
    public static final RenderPipeline PIPELINE = ClientPipelines2D.CIRCLE;
    @JvmField
    @NotNull
    public static final RenderPipeline ADAPTIVE_PIPELINE;
    @NotNull
    private static final RenderPipeline[] pipelines;

    private CircleBatch() {
        super("circle", CircleBatchKt.access$getCIRCLE_LAYOUT$p(), "CircleParamsArray", 512, 0, 16, null);
    }

    @Override
    @NotNull
    public RenderPipeline[] getPipelines() {
        return pipelines;
    }

    @JvmStatic
    @NotNull
    public static final BuiltCircle normalize(@NotNull BuiltCircle circle) {
        Intrinsics.checkNotNullParameter((Object)circle, (String)"circle");
        return new BuiltCircle(circle.x(), circle.y(), Math.max(circle.radius(), 0.0f), RangesKt.coerceIn((float)circle.thickness(), (float)0.0f, (float)circle.radius()), Math.max(circle.smoothness(), 0.0f), circle.color());
    }

    @Override
    protected void write(@NotNull UniformWriter writer, @NotNull BuiltCircle item) {
        Intrinsics.checkNotNullParameter((Object)writer, (String)"writer");
        Intrinsics.checkNotNullParameter((Object)item, (String)"item");
        writer.putVec4("shape", item.radius(), Math.max(item.smoothness(), 0.0f), item.thickness(), 0.0f);
        writer.putColor("tint", item.color());
    }

    static {
        String[] stringArray = new String[]{"CircleParamsArray"};
        String[] stringArray2 = stringArray;
        stringArray = new String[]{"Sampler0"};
        ADAPTIVE_PIPELINE = ClientPipelines2D.standard$default(ClientPipelines2D.INSTANCE, "circle_adaptive", stringArray2, null, null, "core/circle_adaptive", null, stringArray, 44, null);
        pipelines = new RenderPipeline[]{PIPELINE, ADAPTIVE_PIPELINE};
    }
}

