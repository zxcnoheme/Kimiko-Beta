/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.systems.RenderPass
 *  com.mojang.blaze3d.systems.RenderSystem
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.outline.outline360;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.core.batch.UiBatch;
import rtx.kimiko.utils.render.core.pipeline.ClientPipelines2D;
import rtx.kimiko.utils.render.core.uniform.UniformWriter;
import rtx.kimiko.utils.render.render2d.outline.outline360.BuiltOutline360;
import rtx.kimiko.utils.render.render2d.outline.outline360.Outline360BatchKt;
import rtx.kimiko.utils.render.render2d.outline.outline360.Outline360Range;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0014\u00a2\u0006\u0004\b\u0006\u0010\u0004J\u001f\u0010\n\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0014\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\fH\u0014\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0010H\u0014\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0004R\u0019\u0010\u001a\u001a\u00020\u00188\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR \u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00180\u001c8\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0018\u0010$\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010&\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'\u00a8\u0006("}, d2={"Lrtx/kimiko/utils/render/render2d/outline/outline360/Outline360Batch;", "Lrtx/kimiko/utils/render/core/batch/UiBatch;", "Lrtx/kimiko/utils/render/render2d/outline/outline360/BuiltOutline360;", "<init>", "()V", "", "beforeWrite", "Lrtx/kimiko/utils/render/core/uniform/UniformWriter;", "writer", "item", "write", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Lrtx/kimiko/utils/render/render2d/outline/outline360/BuiltOutline360;)V", "", "count", "afterWrite", "(I)V", "Lcom/mojang/blaze3d/systems/RenderPass;", "pass", "bindExtraUniforms", "(Lcom/mojang/blaze3d/systems/RenderPass;)V", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "ensureRangesBuffer", "()Lcom/mojang/blaze3d/buffers/GpuBuffer;", "close", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "", "pipelines", "[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "getPipelines", "()[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Ljava/nio/ByteBuffer;", "rangesHost", "Ljava/nio/ByteBuffer;", "rangesBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "rangeOffset", "I", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nOutline360Batch.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Outline360Batch.kt\nrtx/kimiko/utils/render/render2d/outline/outline360/Outline360Batch\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,156:1\n1#2:157\n*E\n"})
public final class Outline360Batch
extends UiBatch<BuiltOutline360> {
    @NotNull
    public static final Outline360Batch INSTANCE = new Outline360Batch();
    @JvmField
    @NotNull
    public static final RenderPipeline PIPELINE = ClientPipelines2D.OUTLINE_360;
    @NotNull
    private static final RenderPipeline[] pipelines;
    @NotNull
    private static final ByteBuffer rangesHost;
    @Nullable
    private static GpuBuffer rangesBuffer;
    private static int rangeOffset;

    private Outline360Batch() {
        super("outline360", Outline360BatchKt.access$getOUTLINE_360_LAYOUT$p(), "Outline360ParamsArray", 256, 0, 16, null);
    }

    @Override
    @NotNull
    public RenderPipeline[] getPipelines() {
        return pipelines;
    }

    @Override
    protected void beforeWrite() {
        rangeOffset = 0;
    }

    @Override
    protected void write(@NotNull UniformWriter writer, @NotNull BuiltOutline360 item) {
        Intrinsics.checkNotNullParameter((Object)writer, (String)"writer");
        Intrinsics.checkNotNullParameter((Object)item, (String)"item");
        writer.putVec4("radii", item.radiusTopLeft(), item.radiusTopRight(), item.radiusBottomRight(), item.radiusBottomLeft());
        writer.putVec4("size", item.width(), item.height(), item.thickness(), item.smoothness());
        writer.putColor("defaultColor", item.defaultColor());
        int rangeCount = Math.min(item.ranges().size(), 1024 - rangeOffset);
        writer.putVec4("ranges", rangeOffset, rangeCount, item.blendDegrees(), item.angleOffsetDegrees());
        for (int index = 0; index < rangeCount; ++index) {
            Outline360Range range = item.ranges().get(index);
            UniformWriter rangeWriter = Outline360BatchKt.access$getRANGE_LAYOUT$p().writer(rangesHost, rangeOffset + index);
            rangeWriter.putVec4("angles", range.startDegrees(), range.endDegrees(), range.blendStartDegrees(), range.blendEndDegrees());
            rangeWriter.putColor("color", range.color());
            rangeWriter.putColor("colorEnd", range.colorEnd());
        }
        rangeOffset += rangeCount;
    }

    @Override
    protected void afterWrite(int count) {
        if (rangeOffset <= 0) {
            return;
        }
        GpuBuffer gpuBuffer = this.ensureRangesBuffer();
        if (gpuBuffer == null) {
            return;
        }
        GpuBuffer buffer = gpuBuffer;
        int bytes = Outline360BatchKt.access$getRANGE_LAYOUT$p().byteSize(rangeOffset);
        rangesHost.position(0);
        rangesHost.limit(bytes);
        try {
            RenderSystem.getDevice().createCommandEncoder().writeToBuffer(buffer.slice(0L, (long)bytes), rangesHost);
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
        rangesHost.clear();
    }

    @Override
    protected void bindExtraUniforms(@NotNull RenderPass pass) {
        block0: {
            Intrinsics.checkNotNullParameter((Object)pass, (String)"pass");
            GpuBuffer gpuBuffer = rangesBuffer;
            if (gpuBuffer == null) break block0;
            GpuBuffer it = gpuBuffer;
            boolean bl = false;
            pass.setUniform("Outline360RangesArray", it);
        }
    }

    private final GpuBuffer ensureRangesBuffer() {
        int total = Outline360BatchKt.access$getRANGE_LAYOUT$p().byteSize(1024);
        GpuBuffer current = rangesBuffer;
        if (current != null && !current.isClosed() && current.size() >= (long)total) {
            return current;
        }
        if (rangesBuffer != null) {
            rangesBuffer.close();
        }
        rangesBuffer = null;
        try {
            GpuBuffer created = RenderSystem.getDevice().createBuffer(Outline360Batch::ensureRangesBuffer$lambda$0, 136, (long)total);
            rangesBuffer = created;
            return created;
        }
        catch (RuntimeException ignored) {
            return null;
        }
    }

    @Override
    public void close() {
        super.close();
        rangeOffset = 0;
        GpuBuffer gpuBuffer = rangesBuffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        rangesBuffer = null;
    }

    private static final String ensureRangesBuffer$lambda$0() {
        return "kimiko_outline360_ranges";
    }

    static {
        RenderPipeline[] renderPipelineArray = new RenderPipeline[]{ClientPipelines2D.OUTLINE_360};
        pipelines = renderPipelineArray;
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(Outline360BatchKt.access$getRANGE_LAYOUT$p().byteSize(1024)).order(ByteOrder.nativeOrder());
        Intrinsics.checkNotNullExpressionValue((Object)byteBuffer, (String)"order(...)");
        rangesHost = byteBuffer;
    }
}

