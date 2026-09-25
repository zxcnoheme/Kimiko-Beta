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
package rtx.kimiko.utils.render.render2d.rectangle.rectdefault;

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
import rtx.kimiko.utils.render.render2d.rectangle.rectdefault.BuiltRectangle;
import rtx.kimiko.utils.render.render2d.rectangle.rectdefault.RectangleBatchKt;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0014\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0014\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u001f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0015\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R \u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0017\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/RectangleBatch;", "Lrtx/kimiko/utils/render/core/batch/UiBatch;", "Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/BuiltRectangle;", "<init>", "()V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "", "pageIndexOf", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)I", "Lrtx/kimiko/utils/render/core/uniform/UniformWriter;", "writer", "item", "", "write", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/BuiltRectangle;)V", "Lcom/mojang/blaze3d/systems/RenderPass;", "pass", "bindExtraUniforms", "(Lcom/mojang/blaze3d/systems/RenderPass;)V", "", "Lkotlin/jvm/JvmField;", "PIPELINES", "[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipelines", "getPipelines", "()[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nRectangleBatch.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RectangleBatch.kt\nrtx/kimiko/utils/render/render2d/rectangle/rectdefault/RectangleBatch\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,102:1\n1#2:103\n*E\n"})
public final class RectangleBatch
extends UiBatch<BuiltRectangle> {
    @NotNull
    public static final RectangleBatch INSTANCE = new RectangleBatch();
    @JvmField
    @NotNull
    public static final RenderPipeline[] PIPELINES = ClientPipelines2D.RECT_DEFAULT_PAGES;
    @NotNull
    private static final RenderPipeline[] pipelines = PIPELINES;

    private RectangleBatch() {
        super("defaultrectangle", RectangleBatchKt.access$getRECTANGLE_LAYOUT$p(), "RectangleParamsArray", 448, 16);
    }

    @Override
    @NotNull
    public RenderPipeline[] getPipelines() {
        return pipelines;
    }

    @Override
    public int pageIndexOf(@NotNull RenderPipeline pipeline) {
        Intrinsics.checkNotNullParameter((Object)pipeline, (String)"pipeline");
        int n = PIPELINES.length;
        for (int index = 0; index < n; ++index) {
            if (PIPELINES[index] != pipeline) continue;
            return index;
        }
        return 0;
    }

    @Override
    protected void write(@NotNull UniformWriter writer, @NotNull BuiltRectangle item) {
        Intrinsics.checkNotNullParameter((Object)writer, (String)"writer");
        Intrinsics.checkNotNullParameter((Object)item, (String)"item");
        writer.putVec4("radii", item.radiusTopLeft(), item.radiusTopRight(), item.radiusBottomRight(), item.radiusBottomLeft());
        writer.putVec4("size", item.width(), item.height(), item.smoothness(), item.paletteMode());
        if (item.paletteMode() != 0) {
            writer.putVec4("colorTopLeft", item.paletteTint(), item.paletteAlpha(), 0.0f, 0.0f);
            writer.putVec4("colorTopRight", 0.0f, 0.0f, 0.0f, 0.0f);
            writer.putVec4("colorBottomRight", 0.0f, 0.0f, 0.0f, 0.0f);
            writer.putVec4("colorBottomLeft", 0.0f, 0.0f, 0.0f, 0.0f);
        } else {
            writer.putColor("colorTopLeft", item.colorTopLeft());
            writer.putColor("colorTopRight", item.colorTopRight());
            writer.putColor("colorBottomRight", item.colorBottomRight());
            writer.putColor("colorBottomLeft", item.colorBottomLeft());
        }
        writer.putVec4("scissorRect", item.scissorX(), item.scissorY(), item.scissorWidth(), item.scissorHeight());
        writer.putVec4("scissorRadii", item.scissorRadiusTopLeft(), item.scissorRadiusTopRight(), item.scissorRadiusBottomRight(), item.scissorRadiusBottomLeft());
        writer.putVec4("scissorRotation", item.scissorCos(), item.scissorSin(), item.scissorFade(), 0.0f);
    }

    @Override
    protected void bindExtraUniforms(@NotNull RenderPass pass) {
        block0: {
            Intrinsics.checkNotNullParameter((Object)pass, (String)"pass");
            GpuBuffer gpuBuffer = ClientPalette.buffer();
            if (gpuBuffer == null) break block0;
            GpuBuffer it = gpuBuffer;
            boolean bl = false;
            pass.setUniform("PaletteParams", it);
        }
    }
}

