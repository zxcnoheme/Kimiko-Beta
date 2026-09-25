/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.systems.RenderPass
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.render2d.shape;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderPass;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.core.batch.UiBatch;
import rtx.kimiko.utils.render.core.pipeline.ClientPipelines2D;
import rtx.kimiko.utils.render.core.uniform.UniformWriter;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.render.render2d.ClientSplits;
import rtx.kimiko.utils.render.render2d.blur.BlurCapture;
import rtx.kimiko.utils.render.render2d.shape.BuiltShape;
import rtx.kimiko.utils.render.render2d.shape.ShapeBatchKt;
import rtx.kimiko.utils.render.render2d.shape.ShapeEntry;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0014\u00a2\u0006\u0004\b\u000e\u0010\u000fJ'\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0010H\u0014\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0014H\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0019\u0010\u001a\u001a\u00020\u00188\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR \u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00180\u001c8\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \u00a8\u0006!"}, d2={"Lrtx/kimiko/utils/render/render2d/shape/ShapeBatch;", "Lrtx/kimiko/utils/render/core/batch/UiBatch;", "Lrtx/kimiko/utils/render/render2d/shape/ShapeEntry;", "<init>", "()V", "Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;", "s", "", "shapeFresnelScale", "(Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;)F", "Lrtx/kimiko/utils/render/core/uniform/UniformWriter;", "writer", "item", "", "write", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Lrtx/kimiko/utils/render/render2d/shape/ShapeEntry;)V", "", "slot", "writeExtra", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Lrtx/kimiko/utils/render/render2d/shape/ShapeEntry;I)V", "Lcom/mojang/blaze3d/systems/RenderPass;", "pass", "bindExtraUniforms", "(Lcom/mojang/blaze3d/systems/RenderPass;)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "", "pipelines", "[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "getPipelines", "()[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nShapeBatch.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ShapeBatch.kt\nrtx/kimiko/utils/render/render2d/shape/ShapeBatch\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,121:1\n1#2:122\n*E\n"})
public final class ShapeBatch
extends UiBatch<ShapeEntry> {
    @NotNull
    public static final ShapeBatch INSTANCE = new ShapeBatch();
    @JvmField
    @NotNull
    public static final RenderPipeline PIPELINE = ClientPipelines2D.SHAPE;
    @NotNull
    private static final RenderPipeline[] pipelines;

    private ShapeBatch() {
        super("shape", ShapeBatchKt.access$getSHAPE_LAYOUT$p(), "ShapeParamsArray", 16, 0, 16, null);
    }

    @Override
    @NotNull
    public RenderPipeline[] getPipelines() {
        return pipelines;
    }

    private final float shapeFresnelScale(BuiltShape s) {
        int count = Math.min(s.spanCount(), 64);
        float[] spans = s.spans();
        if (count <= 0) {
            return 0.0f;
        }
        float minThickness = Float.MAX_VALUE;
        for (int sp = 0; sp < count; ++sp) {
            float bottom = spans[sp * 4 + 3];
            float top = spans[sp * 4 + 2];
            float h = bottom - top;
            if (!(h > 0.001f)) continue;
            minThickness = Math.min(minThickness, h);
        }
        if (minThickness == Float.MAX_VALUE) {
            return 0.0f;
        }
        return Math.max(minThickness * 0.5f, 1.5f);
    }

    @Override
    protected void write(@NotNull UniformWriter writer, @NotNull ShapeEntry item) {
        Intrinsics.checkNotNullParameter((Object)writer, (String)"writer");
        Intrinsics.checkNotNullParameter((Object)item, (String)"item");
        BuiltShape s = item.getShape();
        writer.putVec4("radii", s.radiusTopLeft(), s.radiusTopRight(), s.radiusBottomRight(), s.radiusBottomLeft());
        writer.putVec4("size", s.width(), s.height(), this.shapeFresnelScale(s), Math.max(s.squirt(), 0.001f));
        writer.putVec4("fresnel", s.globalAlpha(), s.fresnelPower(), s.baseAlpha(), s.fresnelMix());
        writer.putColor("fresnelColor", s.fresnelColor());
        writer.putFloatAt("fresnelColor", 0, s.splitIndex());
        writer.putVec4("material", s.fresnelInvert() ? 1.0f : 0.0f, s.distortStrength(), s.z(), s.colorOffset());
        writer.putColor("color", s.color());
        writer.putColor("secondColor", s.secondColor());
        writer.putVec4("spanInfo", s.spanCount(), s.innerRadius(), s.leftAligned(), s.bottomAnchored());
        writer.putVec4("rowWave", s.waveFreq(), s.wavePhase(), s.waveEnabled() ? 1.0f : 0.0f, 0.0f);
        float[] spanData = s.spans();
        int count = Math.min(s.spanCount(), 64);
        for (int index = 0; index < count; ++index) {
            int src = index * 4;
            writer.putVec4At("spans", index, spanData[src], spanData[src + 1], spanData[src + 2], spanData[src + 3]);
        }
    }

    @Override
    protected void writeExtra(@NotNull UniformWriter writer, @NotNull ShapeEntry item, int slot) {
        Intrinsics.checkNotNullParameter((Object)writer, (String)"writer");
        Intrinsics.checkNotNullParameter((Object)item, (String)"item");
        BlurCapture blurCapture = item.getCapture();
        if (blurCapture == null) {
            return;
        }
        BlurCapture capture = blurCapture;
        writer.putVec4("capture", capture.regionX, capture.regionY, capture.regionW, capture.regionH);
    }

    @Override
    protected void bindExtraUniforms(@NotNull RenderPass pass) {
        block1: {
            GpuBuffer it;
            Intrinsics.checkNotNullParameter((Object)pass, (String)"pass");
            GpuBuffer gpuBuffer = ClientPalette.buffer();
            if (gpuBuffer != null) {
                it = gpuBuffer;
                boolean bl = false;
                pass.setUniform("PaletteParams", it);
            }
            GpuBuffer gpuBuffer2 = ClientSplits.buffer();
            if (gpuBuffer2 == null) break block1;
            it = gpuBuffer2;
            boolean bl = false;
            pass.setUniform("SplitParams", it);
        }
    }

    static {
        RenderPipeline[] renderPipelineArray = new RenderPipeline[]{ClientPipelines2D.SHAPE};
        pipelines = renderPipelineArray;
    }
}

