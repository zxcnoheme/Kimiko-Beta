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
package rtx.kimiko.utils.render.render2d.glass;

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
import rtx.kimiko.utils.render.render2d.glass.BuiltGlass;
import rtx.kimiko.utils.render.render2d.glass.GlassBatchKt;
import rtx.kimiko.utils.render.render2d.glass.GlassEntry;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002H\u0014\u00a2\u0006\u0004\b\t\u0010\nJ'\u0010\r\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH\u0014\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0014\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0015\u001a\u00020\u00138\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0014\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R \u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u00178\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/render/render2d/glass/GlassBatch;", "Lrtx/kimiko/utils/render/core/batch/UiBatch;", "Lrtx/kimiko/utils/render/render2d/glass/GlassEntry;", "<init>", "()V", "Lrtx/kimiko/utils/render/core/uniform/UniformWriter;", "writer", "item", "", "write", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Lrtx/kimiko/utils/render/render2d/glass/GlassEntry;)V", "", "slot", "writeExtra", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Lrtx/kimiko/utils/render/render2d/glass/GlassEntry;I)V", "Lcom/mojang/blaze3d/systems/RenderPass;", "pass", "bindExtraUniforms", "(Lcom/mojang/blaze3d/systems/RenderPass;)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "", "pipelines", "[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "getPipelines", "()[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nGlassBatch.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GlassBatch.kt\nrtx/kimiko/utils/render/render2d/glass/GlassBatch\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,96:1\n1#2:97\n*E\n"})
public final class GlassBatch
extends UiBatch<GlassEntry> {
    @NotNull
    public static final GlassBatch INSTANCE = new GlassBatch();
    @JvmField
    @NotNull
    public static final RenderPipeline PIPELINE = ClientPipelines2D.GLASS;
    @NotNull
    private static final RenderPipeline[] pipelines;

    private GlassBatch() {
        super("glass", GlassBatchKt.access$getGLASS_LAYOUT$p(), "GlassParamsArray", 448, 0, 16, null);
    }

    @Override
    @NotNull
    public RenderPipeline[] getPipelines() {
        return pipelines;
    }

    @Override
    protected void write(@NotNull UniformWriter writer, @NotNull GlassEntry item) {
        Intrinsics.checkNotNullParameter((Object)writer, (String)"writer");
        Intrinsics.checkNotNullParameter((Object)item, (String)"item");
        BuiltGlass glass = item.getGlass();
        writer.putVec4("radii", glass.radiusTopLeft(), glass.radiusTopRight(), glass.radiusBottomRight(), glass.radiusBottomLeft());
        writer.putVec4("size", glass.width(), glass.height(), glass.splitIndex(), Math.max(glass.squirt(), 0.001f));
        writer.putVec4("fresnel", glass.globalAlpha(), glass.fresnelPower(), glass.baseAlpha(), glass.fresnelMix());
        writer.putVec4("scissorFade", glass.scissorFade(), glass.scissorTop(), 0.0f, 0.0f);
        writer.putColor("fresnelColor", glass.fresnelColor());
        writer.putVec4("material", glass.fresnelInvert() ? 1.0f : 0.0f, glass.distortStrength(), glass.paletteSlot() > 0 ? 10.0f + (float)glass.paletteSlot() : glass.z(), glass.colorOffset());
        writer.putColor("color", glass.color());
        writer.putColor("secondColor", glass.secondColor());
    }

    @Override
    protected void writeExtra(@NotNull UniformWriter writer, @NotNull GlassEntry item, int slot) {
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
        RenderPipeline[] renderPipelineArray = new RenderPipeline[]{ClientPipelines2D.GLASS};
        pipelines = renderPipelineArray;
    }
}

