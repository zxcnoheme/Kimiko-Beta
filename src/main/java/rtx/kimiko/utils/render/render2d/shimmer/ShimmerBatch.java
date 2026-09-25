/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.ScreenRect
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 */
package rtx.kimiko.utils.render.render2d.shimmer;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.ScreenRect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import rtx.kimiko.utils.render.core.batch.UiBatch;
import rtx.kimiko.utils.render.core.pipeline.ClientPipelines2D;
import rtx.kimiko.utils.render.core.uniform.UniformWriter;
import rtx.kimiko.utils.render.modules.post.GuiRenderStateLayerAccessor;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.render2d.shimmer.BuiltShimmer;
import rtx.kimiko.utils.render.render2d.shimmer.ShimmerBatchKt;
import rtx.kimiko.utils.render.render2d.shimmer.ShimmerRenderState;
import rtx.kimiko.utils.render.util.scissor.ScissorUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001%B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002H\u0014\u00a2\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000e\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\bH\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u0010\u0010\u0004J\u001d\u0010\u0012\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u0012\u0010\u0013R0\u0010\u0018\u001a\u001e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014j\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0016`\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0019\u0010\u001e\u001a\u00020\u001c8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001d\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR \u0010!\u001a\b\u0012\u0004\u0012\u00020\u001c0 8\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\u00a8\u0006&"}, d2={"Lrtx/kimiko/utils/render/render2d/shimmer/ShimmerBatch;", "Lrtx/kimiko/utils/render/core/batch/UiBatch;", "Lrtx/kimiko/utils/render/render2d/shimmer/BuiltShimmer;", "<init>", "()V", "Lrtx/kimiko/utils/render/core/uniform/UniformWriter;", "writer", "item", "", "write", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Lrtx/kimiko/utils/render/render2d/shimmer/BuiltShimmer;)V", "Lnet/minecraft/DrawContext;", "graphics", "Lkotlin/jvm/JvmStatic;", "beginFrame", "(Lnet/minecraft/DrawContext;)V", "flush", "shimmer", "enqueue", "(Lrtx/kimiko/utils/render/render2d/shimmer/BuiltShimmer;)V", "Ljava/util/LinkedHashMap;", "Lrtx/kimiko/utils/render/render2d/shimmer/ShimmerBatch$FrameBatchKey;", "Lrtx/kimiko/utils/render/render2d/shimmer/ShimmerRenderState;", "Lkotlin/collections/LinkedHashMap;", "frameBatches", "Ljava/util/LinkedHashMap;", "activeGraphics", "Lnet/minecraft/DrawContext;", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "", "pipelines", "[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "getPipelines", "()[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "FrameBatchKey", "rtx.kimiko:kimiko"})
public final class ShimmerBatch
extends UiBatch<BuiltShimmer> {
    @NotNull
    public static final ShimmerBatch INSTANCE = new ShimmerBatch();
    @NotNull
    private static final LinkedHashMap<FrameBatchKey, ShimmerRenderState> frameBatches = new LinkedHashMap(16);
    @Nullable
    private static DrawContext activeGraphics;
    @JvmField
    @NotNull
    public static final RenderPipeline PIPELINE;
    @NotNull
    private static final RenderPipeline[] pipelines;

    private ShimmerBatch() {
        super("shimmer", ShimmerBatchKt.access$getSHIMMER_LAYOUT$p(), "ShimmerParamsArray", 256, 0, 16, null);
    }

    @Override
    @NotNull
    public RenderPipeline[] getPipelines() {
        return pipelines;
    }

    @Override
    protected void write(@NotNull UniformWriter writer, @NotNull BuiltShimmer item) {
        Intrinsics.checkNotNullParameter((Object)writer, (String)"writer");
        Intrinsics.checkNotNullParameter((Object)item, (String)"item");
        writer.putVec4("shape", item.progress(), item.halfWidth(), item.intensity(), 0.0f);
    }

    @JvmStatic
    public static final void beginFrame(@Nullable DrawContext graphics) {
        if (activeGraphics != graphics) {
            frameBatches.clear();
        }
        activeGraphics = graphics;
    }

    @JvmStatic
    public static final void flush() {
        activeGraphics = null;
        frameBatches.clear();
    }

    @JvmStatic
    public static final void enqueue(@Nullable BuiltShimmer shimmer) {
        DrawContext graphics = activeGraphics;
        if (graphics == null || shimmer == null) {
            return;
        }
        try {
            GuiRenderState guiState = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
            Intrinsics.checkNotNull((Object)guiState, (String)"null cannot be cast to non-null type rtx.kimiko.utils.render.modules.post.GuiRenderStateLayerAccessor");
            int layerSerial = ((GuiRenderStateLayerAccessor)guiState).kimiko$getLayerSerial();
            Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
            ScreenRect scissor = ScissorUtil.current();
            FrameBatchKey key = new FrameBatchKey(guiState, layerSerial, scissor);
            if (frameBatches.get(key) == null) {
                ShimmerRenderState state = new ShimmerRenderState(pose, shimmer, scissor);
                ((Map)frameBatches).put(key, state);
                guiState.addSimpleElement((SimpleGuiElementRenderState)state);
            }
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    static {
        PIPELINE = ClientPipelines2D.SHIMMER;
        RenderPipeline[] renderPipelineArray = new RenderPipeline[]{ClientPipelines2D.SHIMMER};
        pipelines = renderPipelineArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ0\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0016\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\rJ\u0011\u0010\u0018\u001a\u00020\u0017H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b\u001d\u0010\rR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b\u001f\u0010\u000f\u00a8\u0006 "}, d2={"Lrtx/kimiko/utils/render/render2d/shimmer/ShimmerBatch$FrameBatchKey;", "", "Lnet/minecraft/GuiRenderState;", "state", "", "layer", "Lnet/minecraft/ScreenRect;", "scissor", "<init>", "(Lnet/minecraft/GuiRenderState;ILnet/minecraft/ScreenRect;)V", "component1", "()Lnet/minecraft/GuiRenderState;", "component2", "()I", "component3", "()Lnet/minecraft/ScreenRect;", "copy", "(Lnet/minecraft/GuiRenderState;ILnet/minecraft/ScreenRect;)Lrtx/kimiko/utils/render/render2d/shimmer/ShimmerBatch$FrameBatchKey;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/GuiRenderState;", "getState", "I", "getLayer", "Lnet/minecraft/ScreenRect;", "getScissor", "rtx.kimiko:kimiko"})
    private static final class FrameBatchKey {
        @NotNull
        private final GuiRenderState state;
        private final int layer;
        @Nullable
        private final ScreenRect scissor;

        public FrameBatchKey(@NotNull GuiRenderState state, int layer, @Nullable ScreenRect scissor) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            this.state = state;
            this.layer = layer;
            this.scissor = scissor;
        }

        @NotNull
        public final GuiRenderState getState() {
            return this.state;
        }

        public final int getLayer() {
            return this.layer;
        }

        @Nullable
        public final ScreenRect getScissor() {
            return this.scissor;
        }

        @NotNull
        public final GuiRenderState component1() {
            return this.state;
        }

        public final int component2() {
            return this.layer;
        }

        @Nullable
        public final ScreenRect component3() {
            return this.scissor;
        }

        @NotNull
        public final FrameBatchKey copy(@NotNull GuiRenderState state, int layer, @Nullable ScreenRect scissor) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            return new FrameBatchKey(state, layer, scissor);
        }

        public static /* synthetic */ FrameBatchKey copy$default(FrameBatchKey frameBatchKey, GuiRenderState guiRenderState2, int n, ScreenRect screenRect2, int n2, Object object) {
            if ((n2 & 1) != 0) {
                guiRenderState2 = frameBatchKey.state;
            }
            if ((n2 & 2) != 0) {
                n = frameBatchKey.layer;
            }
            if ((n2 & 4) != 0) {
                screenRect2 = frameBatchKey.scissor;
            }
            return frameBatchKey.copy(guiRenderState2, n, screenRect2);
        }

        @NotNull
        public String toString() {
            return "FrameBatchKey(state=" + this.state + ", layer=" + this.layer + ", scissor=" + this.scissor + ")";
        }

        public int hashCode() {
            int result = this.state.hashCode();
            result = result * 31 + Integer.hashCode(this.layer);
            result = result * 31 + (this.scissor == null ? 0 : this.scissor.hashCode());
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FrameBatchKey)) {
                return false;
            }
            FrameBatchKey frameBatchKey = (FrameBatchKey)other;
            if (!Intrinsics.areEqual((Object)this.state, (Object)frameBatchKey.state)) {
                return false;
            }
            if (this.layer != frameBatchKey.layer) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.scissor, (Object)frameBatchKey.scissor);
        }
    }
}

