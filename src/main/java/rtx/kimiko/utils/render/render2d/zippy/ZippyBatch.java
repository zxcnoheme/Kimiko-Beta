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
package rtx.kimiko.utils.render.render2d.zippy;

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
import rtx.kimiko.utils.render.render2d.zippy.BuiltZippy;
import rtx.kimiko.utils.render.render2d.zippy.ZippyBatchKt;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001b\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0014\u00a2\u0006\u0004\b\n\u0010\u0004J\u001f\u0010\u000e\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0002H\u0014\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0012\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0011\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u00148\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001a\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/render/render2d/zippy/ZippyBatch;", "Lrtx/kimiko/utils/render/core/batch/UiBatch;", "Lrtx/kimiko/utils/render/render2d/zippy/BuiltZippy;", "<init>", "()V", "zippy", "Lkotlin/jvm/JvmStatic;", "normalize", "(Lrtx/kimiko/utils/render/render2d/zippy/BuiltZippy;)Lrtx/kimiko/utils/render/render2d/zippy/BuiltZippy;", "", "beforeWrite", "Lrtx/kimiko/utils/render/core/uniform/UniformWriter;", "writer", "item", "write", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Lrtx/kimiko/utils/render/render2d/zippy/BuiltZippy;)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "", "pipelines", "[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "getPipelines", "()[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "", "frameTime", "F", "rtx.kimiko:kimiko"})
public final class ZippyBatch
extends UiBatch<BuiltZippy> {
    @NotNull
    public static final ZippyBatch INSTANCE = new ZippyBatch();
    @JvmField
    @NotNull
    public static final RenderPipeline PIPELINE = ClientPipelines2D.ZIPPY;
    @NotNull
    private static final RenderPipeline[] pipelines;
    private static float frameTime;

    private ZippyBatch() {
        super("zippy", ZippyBatchKt.access$getZIPPY_LAYOUT$p(), "ZippyParamsArray", 256, 0, 16, null);
    }

    @Override
    @NotNull
    public RenderPipeline[] getPipelines() {
        return pipelines;
    }

    @JvmStatic
    @NotNull
    public static final BuiltZippy normalize(@NotNull BuiltZippy zippy) {
        Intrinsics.checkNotNullParameter((Object)zippy, (String)"zippy");
        float maxRadius = Math.max(0.0f, Math.min(zippy.width(), zippy.height()) * 0.5f);
        return new BuiltZippy(zippy.x(), zippy.y(), zippy.width(), zippy.height(), RangesKt.coerceIn((float)zippy.radiusTopLeft(), (float)0.0f, (float)maxRadius), RangesKt.coerceIn((float)zippy.radiusTopRight(), (float)0.0f, (float)maxRadius), RangesKt.coerceIn((float)zippy.radiusBottomRight(), (float)0.0f, (float)maxRadius), RangesKt.coerceIn((float)zippy.radiusBottomLeft(), (float)0.0f, (float)maxRadius), zippy.color(), Math.max(zippy.smoothness(), 0.0f), zippy.timeOffset());
    }

    @Override
    protected void beforeWrite() {
        frameTime = (float)(System.currentTimeMillis() % 120000L) / 1000.0f;
    }

    @Override
    protected void write(@NotNull UniformWriter writer, @NotNull BuiltZippy item) {
        Intrinsics.checkNotNullParameter((Object)writer, (String)"writer");
        Intrinsics.checkNotNullParameter((Object)item, (String)"item");
        writer.putVec4("radii", item.radiusTopLeft(), item.radiusTopRight(), item.radiusBottomRight(), item.radiusBottomLeft());
        writer.putVec4("size", item.width(), item.height(), item.smoothness(), frameTime + item.timeOffset());
        writer.putColor("tint", item.color());
    }

    static {
        RenderPipeline[] renderPipelineArray = new RenderPipeline[]{ClientPipelines2D.ZIPPY};
        pipelines = renderPipelineArray;
    }
}

