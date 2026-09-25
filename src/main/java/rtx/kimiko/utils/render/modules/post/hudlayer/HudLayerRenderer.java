/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.pipeline.BlendFunction
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Snippet
 *  com.mojang.blaze3d.platform.DepthTestFunction
 *  com.mojang.blaze3d.platform.DestFactor
 *  com.mojang.blaze3d.platform.SourceFactor
 *  com.mojang.blaze3d.systems.CommandEncoder
 *  com.mojang.blaze3d.systems.GpuDevice
 *  com.mojang.blaze3d.systems.RenderPass
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gui.render.state.ColoredQuadGuiElementRenderState
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gl.SimpleFramebuffer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fc
 *  org.lwjgl.system.MemoryStack
 */
package rtx.kimiko.utils.render.modules.post.hudlayer;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.platform.DestFactor;
import com.mojang.blaze3d.platform.SourceFactor;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.nio.ByteBuffer;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gui.render.state.ColoredQuadGuiElementRenderState;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gl.SimpleFramebuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;
import org.lwjgl.system.MemoryStack;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.others.RenderSampler;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0014\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u001d\u0010\t\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJE\u0010\u0011\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u0018\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0018\u0010\u0017J\u0013\u0010\u0019\u001a\u00020\u0015H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u001d\u0010\u001f\u001a\u00020\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u001bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001f\u0010 J\u0013\u0010!\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b!\u0010\u0003Jg\u00100\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\"2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$2\u0006\u0010'\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*2\u0006\u0010.\u001a\u00020*2\u0006\u0010/\u001a\u00020*H\u0002\u00a2\u0006\u0004\b0\u00101J\u0017\u00102\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b2\u00103J\u000f\u00104\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b4\u0010\u001aJ\u001f\u00107\u001a\u00020\u00152\u0006\u00105\u001a\u00020*2\u0006\u00106\u001a\u00020*H\u0002\u00a2\u0006\u0004\b7\u00108J\u000f\u00109\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b9\u0010\u0003J\u001b\u0010:\u001a\u0004\u0018\u00010$2\b\u0010&\u001a\u0004\u0018\u00010$H\u0002\u00a2\u0006\u0004\b:\u0010;J\u000f\u0010<\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b<\u0010\u0003J\u0017\u0010=\u001a\u00020*2\u0006\u0010+\u001a\u00020*H\u0002\u00a2\u0006\u0004\b=\u0010>J'\u0010B\u001a\u00020*2\u0006\u0010?\u001a\u00020*2\u0006\u0010@\u001a\u00020*2\u0006\u0010A\u001a\u00020*H\u0002\u00a2\u0006\u0004\bB\u0010CJ\u0017\u0010F\u001a\u00020\u00132\u0006\u0010E\u001a\u00020DH\u0002\u00a2\u0006\u0004\bF\u0010GJ\u0017\u0010I\u001a\u00020H2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\bI\u0010JJ!\u0010L\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010K\u001a\u00020HH\u0002\u00a2\u0006\u0004\bL\u0010MJ\u0017\u0010P\u001a\u00020O2\u0006\u0010N\u001a\u00020DH\u0002\u00a2\u0006\u0004\bP\u0010QR\u0014\u0010R\u001a\u00020\u000b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bR\u0010SR\u0014\u0010T\u001a\u00020\u000b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bT\u0010SR\u0014\u0010U\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bU\u0010SR\u0014\u0010V\u001a\u00020*8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bV\u0010WR\u0014\u0010X\u001a\u00020*8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bX\u0010WR\u0014\u0010Y\u001a\u00020*8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bY\u0010WR\u0014\u0010Z\u001a\u00020*8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bZ\u0010WR\u0014\u0010[\u001a\u00020O8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b[\u0010\\R\u0014\u0010]\u001a\u00020O8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b]\u0010\\R\u0014\u0010^\u001a\u00020O8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b^\u0010\\R\u0014\u0010_\u001a\u00020O8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b_\u0010\\R\u0014\u0010`\u001a\u00020O8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b`\u0010\\R\u0014\u0010a\u001a\u00020\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\ba\u0010bR\u0014\u0010c\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bc\u0010dR\u0014\u0010e\u001a\u00020\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\be\u0010bR\u0014\u0010f\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bf\u0010dR\u0014\u0010h\u001a\u00020g8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bh\u0010iR\u0014\u0010j\u001a\u00020g8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bj\u0010iR\u0014\u0010k\u001a\u00020g8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bk\u0010iR\u0014\u0010l\u001a\u00020g8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bl\u0010iR\u0014\u0010m\u001a\u00020g8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bm\u0010iR\u0016\u0010n\u001a\u00020*8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bn\u0010WR\u0016\u0010o\u001a\u00020*8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bo\u0010WR\u0016\u0010p\u001a\u00020*8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bp\u0010WR\u0018\u0010q\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bq\u0010bR\u0018\u0010r\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\br\u0010bR\u0018\u0010t\u001a\u0004\u0018\u00010s8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010uR\u0018\u0010v\u001a\u0004\u0018\u00010s8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bv\u0010uR\u0018\u0010w\u001a\u0004\u0018\u00010$8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bw\u0010xR\u0018\u0010y\u001a\u0004\u0018\u00010$8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010xR\u0018\u0010z\u001a\u0004\u0018\u00010$8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bz\u0010xR\u0016\u0010{\u001a\u00020*8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010WR\u0016\u0010|\u001a\u00020*8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b|\u0010WR\u0016\u0010}\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b}\u0010~\u00a8\u0006\u007f"}, d2={"Lrtx/kimiko/utils/render/modules/post/hudlayer/HudLayerRenderer;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "beginFrame", "Lnet/minecraft/DrawContext;", "graphics", "markBegin", "(Lnet/minecraft/DrawContext;)V", "", "designX", "designY", "designWidth", "designHeight", "alpha", "markEnd", "(Lnet/minecraft/DrawContext;FFFFF)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "", "isBegin", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Z", "isEnd", "beginDraw", "()Z", "Lnet/minecraft/Framebuffer;", "layerTarget", "()Lnet/minecraft/Framebuffer;", "destination", "endDraw", "(Lnet/minecraft/Framebuffer;)V", "shutdown", "Lcom/mojang/blaze3d/systems/CommandEncoder;", "encoder", "Lnet/minecraft/SimpleFramebuffer;", "source", "target", "stepX", "stepY", "blurSigma", "", "radius", "scissorX", "scissorTop", "scissorWidth", "scissorHeight", "gaussianPass", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lnet/minecraft/SimpleFramebuffer;Lnet/minecraft/SimpleFramebuffer;FFFIIIII)V", "writeCompositeUniform", "(Lcom/mojang/blaze3d/systems/CommandEncoder;)V", "ensurePipelines", "width", "height", "ensureTargets", "(II)Z", "closeTargets", "destroy", "(Lnet/minecraft/SimpleFramebuffer;)Lnet/minecraft/SimpleFramebuffer;", "disableAfterError", "clampRadius", "(I)I", "value", "minimum", "maximum", "clampInt", "(III)I", "", "name", "markerPipeline", "(Ljava/lang/String;)Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lnet/minecraft/ColoredQuadGuiElementRenderState;", "markerState", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Lnet/minecraft/ColoredQuadGuiElementRenderState;", "marker", "submitMarker", "(Lnet/minecraft/DrawContext;Lnet/minecraft/ColoredQuadGuiElementRenderState;)V", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "MAXIMUM_BLUR_SIGMA", "F", "LAYER_PADDING", "MINIMUM_SIGMA", "MAXIMUM_RADIUS", "I", "MAX_LAYERS", "BLUR_UNIFORM_BYTES", "COMPOSITE_UNIFORM_BYTES", "FULLSCREEN", "Lnet/minecraft/Identifier;", "GAUSSIAN_SHADER", "COMPOSITE_SHADER", "BLUR_PIPELINE_ID", "COMPOSITE_PIPELINE_ID", "BEGIN_PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "BEGIN_MARKER", "Lnet/minecraft/ColoredQuadGuiElementRenderState;", "END_PIPELINE", "END_MARKER", "", "rectX", "[F", "rectY", "rectWidth", "rectHeight", "sigma", "count", "drawIndex", "activeIndex", "blurPipeline", "compositePipeline", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "blurUniform", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "compositeUniform", "layer", "Lnet/minecraft/SimpleFramebuffer;", "tempH", "tempV", "texWidth", "texHeight", "disabledAfterError", "Z", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nHudLayerRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HudLayerRenderer.kt\nrtx/kimiko/utils/render/modules/post/hudlayer/HudLayerRenderer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,383:1\n1#2:384\n*E\n"})
public final class HudLayerRenderer {
    @NotNull
    public static final HudLayerRenderer INSTANCE = new HudLayerRenderer();
    public static final float MAXIMUM_BLUR_SIGMA = 16.0f;
    public static final float LAYER_PADDING = 32.0f;
    private static final float MINIMUM_SIGMA = 0.75f;
    private static final int MAXIMUM_RADIUS = 32;
    private static final int MAX_LAYERS = 32;
    private static final int BLUR_UNIFORM_BYTES = 16;
    private static final int COMPOSITE_UNIFORM_BYTES = 16;
    @NotNull
    private static final Identifier FULLSCREEN = INSTANCE.id("post/hudlayer/fullscreen");
    @NotNull
    private static final Identifier GAUSSIAN_SHADER = INSTANCE.id("post/hudlayer/gaussian");
    @NotNull
    private static final Identifier COMPOSITE_SHADER = INSTANCE.id("post/hudlayer/composite");
    @NotNull
    private static final Identifier BLUR_PIPELINE_ID = INSTANCE.id("pipeline/post/hudlayer/gaussian");
    @NotNull
    private static final Identifier COMPOSITE_PIPELINE_ID = INSTANCE.id("pipeline/post/hudlayer/composite");
    @NotNull
    private static final RenderPipeline BEGIN_PIPELINE = INSTANCE.markerPipeline("layer_begin");
    @NotNull
    private static final ColoredQuadGuiElementRenderState BEGIN_MARKER = INSTANCE.markerState(BEGIN_PIPELINE);
    @NotNull
    private static final RenderPipeline END_PIPELINE = INSTANCE.markerPipeline("layer_end");
    @NotNull
    private static final ColoredQuadGuiElementRenderState END_MARKER = INSTANCE.markerState(END_PIPELINE);
    @NotNull
    private static final float[] rectX = new float[32];
    @NotNull
    private static final float[] rectY = new float[32];
    @NotNull
    private static final float[] rectWidth = new float[32];
    @NotNull
    private static final float[] rectHeight = new float[32];
    @NotNull
    private static final float[] sigma = new float[32];
    private static int count;
    private static int drawIndex;
    private static int activeIndex;
    @Nullable
    private static RenderPipeline blurPipeline;
    @Nullable
    private static RenderPipeline compositePipeline;
    @Nullable
    private static GpuBuffer blurUniform;
    @Nullable
    private static GpuBuffer compositeUniform;
    @Nullable
    private static SimpleFramebuffer layer;
    @Nullable
    private static SimpleFramebuffer tempH;
    @Nullable
    private static SimpleFramebuffer tempV;
    private static int texWidth;
    private static int texHeight;
    private static boolean disabledAfterError;

    private HudLayerRenderer() {
    }

    @JvmStatic
    public static final void beginFrame() {
        count = 0;
        drawIndex = 0;
        activeIndex = -1;
    }

    @JvmStatic
    public static final void markBegin(@Nullable DrawContext graphics) {
        INSTANCE.submitMarker(graphics, BEGIN_MARKER);
    }

    @JvmStatic
    public static final void markEnd(@Nullable DrawContext graphics, float designX, float designY, float designWidth, float designHeight, float alpha) {
        INSTANCE.submitMarker(graphics, END_MARKER);
        if (count >= 32) {
            return;
        }
        float clamped = Math.max(0.0f, Math.min(1.0f, alpha));
        HudLayerRenderer.rectX[HudLayerRenderer.count] = Render2DCoordinateSpace.pixelX(designX);
        HudLayerRenderer.rectY[HudLayerRenderer.count] = Render2DCoordinateSpace.pixelY(designY);
        HudLayerRenderer.rectWidth[HudLayerRenderer.count] = Render2DCoordinateSpace.pixelSize(designWidth);
        HudLayerRenderer.rectHeight[HudLayerRenderer.count] = Render2DCoordinateSpace.pixelSize(designHeight);
        HudLayerRenderer.sigma[HudLayerRenderer.count] = 16.0f * (1.0f - clamped);
        int n = count;
        count = n + 1;
    }

    @JvmStatic
    public static final boolean isBegin(@Nullable RenderPipeline pipeline) {
        return pipeline == BEGIN_PIPELINE;
    }

    @JvmStatic
    public static final boolean isEnd(@Nullable RenderPipeline pipeline) {
        return pipeline == END_PIPELINE;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final boolean beginDraw() {
        activeIndex = -1;
        int index = drawIndex;
        if (index >= count) {
            return false;
        }
        int n = drawIndex;
        drawIndex = n + 1;
        if (disabledAfterError || sigma[index] < 0.75f) {
            return false;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        if (minecraftClient2 == null) {
            return false;
        }
        Framebuffer main = minecraftClient2.getFramebuffer();
        if (main == null || main.textureWidth <= 0 || main.textureHeight <= 0) {
            return false;
        }
        try {
            if (!INSTANCE.ensurePipelines() || !INSTANCE.ensureTargets(main.textureWidth, main.textureHeight)) {
                return false;
            }
            SimpleFramebuffer simpleFramebuffer2 = layer;
            if (simpleFramebuffer2 == null) {
                return false;
            }
            SimpleFramebuffer target = simpleFramebuffer2;
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Supplier<String> supplier = HudLayerRenderer::beginDraw$lambda$0;
            GpuTextureView gpuTextureView = target.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            AutoCloseable autoCloseable = (AutoCloseable)commandEncoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0), target.getDepthAttachmentView(), OptionalDouble.of(1.0));
            Throwable throwable = null;
            try {
                RenderPass it = (RenderPass)autoCloseable;
                boolean bl = false;
                Unit unit = Unit.INSTANCE;
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
            }
        }
        catch (Throwable throwable) {
            INSTANCE.disableAfterError();
            return false;
        }
        activeIndex = index;
        return true;
    }

    @JvmStatic
    @Nullable
    public static final Framebuffer layerTarget() {
        return activeIndex < 0 ? null : (Framebuffer)layer;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void endDraw(@Nullable Framebuffer destination) {
        int index = activeIndex;
        activeIndex = -1;
        if (index < 0 || destination == null || destination.getColorAttachmentView() == null) {
            return;
        }
        SimpleFramebuffer simpleFramebuffer2 = layer;
        if (simpleFramebuffer2 == null) {
            return;
        }
        SimpleFramebuffer source = simpleFramebuffer2;
        SimpleFramebuffer simpleFramebuffer3 = tempH;
        if (simpleFramebuffer3 == null) {
            return;
        }
        SimpleFramebuffer horizontal = simpleFramebuffer3;
        SimpleFramebuffer simpleFramebuffer4 = tempV;
        if (simpleFramebuffer4 == null) {
            return;
        }
        SimpleFramebuffer vertical = simpleFramebuffer4;
        if (destination.textureWidth != texWidth || destination.textureHeight != texHeight) {
            return;
        }
        float blurSigma = sigma[index];
        int radius = INSTANCE.clampRadius((int)Math.ceil(blurSigma * 3.0f));
        float padded = 32.0f * Render2DCoordinateSpace.pixelScale();
        float left = rectX[index] - padded;
        float top = rectY[index] - padded;
        float right = rectX[index] + rectWidth[index] + padded;
        float bottom = rectY[index] + rectHeight[index] + padded;
        int scissorX = INSTANCE.clampInt((int)left, 0, texWidth);
        int scissorRight = INSTANCE.clampInt((int)Math.ceil(right), scissorX, texWidth);
        int scissorTop = INSTANCE.clampInt((int)top, 0, texHeight);
        int scissorBottom = INSTANCE.clampInt((int)Math.ceil(bottom), scissorTop, texHeight);
        int scissorWidth = scissorRight - scissorX;
        int scissorHeight = scissorBottom - scissorTop;
        if (scissorWidth <= 0 || scissorHeight <= 0) {
            return;
        }
        int expandedTop = INSTANCE.clampInt(scissorTop - radius, 0, texHeight);
        int expandedBottom = INSTANCE.clampInt(scissorBottom + radius, expandedTop, texHeight);
        try {
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            INSTANCE.gaussianPass(encoder, source, horizontal, 1.0f / (float)texWidth, 0.0f, blurSigma, radius, scissorX, expandedTop, scissorWidth, expandedBottom - expandedTop);
            INSTANCE.gaussianPass(encoder, horizontal, vertical, 0.0f, 1.0f / (float)texHeight, blurSigma, radius, scissorX, scissorTop, scissorWidth, scissorHeight);
            INSTANCE.writeCompositeUniform(encoder);
            Supplier<String> supplier = HudLayerRenderer::endDraw$lambda$0;
            GpuTextureView gpuTextureView = destination.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
            Throwable throwable = null;
            try {
                RenderPass pass = (RenderPass)autoCloseable;
                boolean bl = false;
                pass.enableScissor(scissorX, texHeight - scissorBottom, scissorWidth, scissorHeight);
                RenderPipeline renderPipeline = compositePipeline;
                Intrinsics.checkNotNull((Object)renderPipeline);
                pass.setPipeline(renderPipeline);
                pass.bindTexture("uLayer", vertical.getColorAttachmentView(), RenderSampler.linear());
                GpuBuffer gpuBuffer = compositeUniform;
                Intrinsics.checkNotNull((Object)gpuBuffer);
                pass.setUniform("HudCompositeData", gpuBuffer);
                pass.draw(0, 6);
                Unit unit = Unit.INSTANCE;
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
            }
        }
        catch (Throwable throwable) {
            INSTANCE.disableAfterError();
        }
    }

    @JvmStatic
    public static final void shutdown() {
        INSTANCE.closeTargets();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void gaussianPass(CommandEncoder encoder, SimpleFramebuffer source, SimpleFramebuffer target, float stepX, float stepY, float blurSigma, int radius, int scissorX, int scissorTop, int scissorWidth, int scissorHeight) {
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(16);
            data.putFloat(0, stepX);
            data.putFloat(4, stepY);
            data.putFloat(8, blurSigma);
            data.putFloat(12, radius);
            data.position(0);
            GpuBuffer gpuBuffer = blurUniform;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 16L), data);
// stack = Unit.INSTANCE;
        }
        catch (Throwable bl) {
            throwable = bl;
            throw bl;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        Supplier<String> supplier = HudLayerRenderer::gaussianPass$lambda$1;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0));
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            pass.enableScissor(scissorX, texHeight - (scissorTop + scissorHeight), scissorWidth, scissorHeight);
            RenderPipeline renderPipeline = blurPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            pass.bindTexture("uInput", source.getColorAttachmentView(), RenderSampler.linear());
            GpuBuffer gpuBuffer = blurUniform;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("HudBlurData", gpuBuffer);
            pass.draw(0, 6);
            Unit unit = Unit.INSTANCE;
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void writeCompositeUniform(CommandEncoder encoder) {
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(16);
            data.putFloat(0, 1.0f);
            data.position(0);
            GpuBuffer gpuBuffer = compositeUniform;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 16L), data);
            Unit unit = Unit.INSTANCE;
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
    }

    private final boolean ensurePipelines() {
        if (blurPipeline != null && compositePipeline != null && blurUniform != null && compositeUniform != null) {
            return true;
        }
        if (blurPipeline == null) {
            blurPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(BLUR_PIPELINE_ID).withVertexShader(FULLSCREEN).withFragmentShader(GAUSSIAN_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withSampler("uInput").withUniform("HudBlurData", UniformType.UNIFORM_BUFFER).withoutBlend().withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
        }
        if (compositePipeline == null) {
            compositePipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(COMPOSITE_PIPELINE_ID).withVertexShader(FULLSCREEN).withFragmentShader(COMPOSITE_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withSampler("uLayer").withUniform("HudCompositeData", UniformType.UNIFORM_BUFFER).withBlend(new BlendFunction(SourceFactor.ONE, DestFactor.ONE_MINUS_SRC_ALPHA)).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
        }
        GpuDevice gpuDevice = RenderSystem.tryGetDevice();
        if (gpuDevice == null) {
            return false;
        }
        GpuDevice device = gpuDevice;
        if (blurUniform == null) {
            blurUniform = device.createBuffer(HudLayerRenderer::ensurePipelines$lambda$0, 136, 16L);
        }
        if (compositeUniform == null) {
            compositeUniform = device.createBuffer(HudLayerRenderer::ensurePipelines$lambda$1, 136, 16L);
        }
        return blurPipeline != null && compositePipeline != null && blurUniform != null && compositeUniform != null;
    }

    private final boolean ensureTargets(int width, int height) {
        if (RenderSystem.tryGetDevice() == null || width <= 0 || height <= 0) {
            return false;
        }
        if (layer != null && tempH != null && tempV != null && width == texWidth && height == texHeight) {
            return true;
        }
        this.closeTargets();
        layer = new SimpleFramebuffer("kimiko_hud_layer", width, height, true);
        tempH = new SimpleFramebuffer("kimiko_hud_layer_h", width, height, false);
        tempV = new SimpleFramebuffer("kimiko_hud_layer_v", width, height, false);
        texWidth = width;
        texHeight = height;
        SimpleFramebuffer simpleFramebuffer2 = layer;
        Intrinsics.checkNotNull((Object)simpleFramebuffer2);
        return simpleFramebuffer2.getColorAttachmentView() != null;
    }

    private final void closeTargets() {
        layer = this.destroy(layer);
        tempH = this.destroy(tempH);
        tempV = this.destroy(tempV);
        texWidth = -1;
        texHeight = -1;
    }

    private final SimpleFramebuffer destroy(SimpleFramebuffer target) {
        block0: {
            SimpleFramebuffer simpleFramebuffer2 = target;
            if (simpleFramebuffer2 == null) break block0;
            simpleFramebuffer2.delete();
        }
        return null;
    }

    private final void disableAfterError() {
        disabledAfterError = true;
        activeIndex = -1;
        this.closeTargets();
    }

    private final int clampRadius(int radius) {
        return Math.min(Math.max(radius, 1), 32);
    }

    private final int clampInt(int value, int minimum, int maximum) {
        return value < minimum ? minimum : (value > maximum ? maximum : value);
    }

    private final RenderPipeline markerPipeline(String name) {
        RenderPipeline renderPipeline = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(this.id("pipeline/post/hudlayer/" + name)).withVertexShader("core/position_color").withFragmentShader("core/position_color").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.QUADS).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withColorWrite(false).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"build(...)");
        return renderPipeline;
    }

    private final ColoredQuadGuiElementRenderState markerState(RenderPipeline pipeline) {
        return new ColoredQuadGuiElementRenderState(pipeline, TextureSetup.empty(), (Matrix3x2fc)new Matrix3x2f(), 0, 0, 1, 1, 0, 0, null);
    }

    private final void submitMarker(DrawContext graphics, ColoredQuadGuiElementRenderState marker) {
        if (graphics == null || disabledAfterError) {
            return;
        }
        GuiRenderState state = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
        state.createNewRootLayer();
        state.addSimpleElement((SimpleGuiElementRenderState)marker);
        state.createNewRootLayer();
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }

    private static final String beginDraw$lambda$0() {
        return "kimiko:hud_layer_clear";
    }

    private static final String endDraw$lambda$0() {
        return "kimiko:hud_layer_composite";
    }

    private static final String gaussianPass$lambda$1() {
        return "kimiko:hud_layer_gaussian";
    }

    private static final String ensurePipelines$lambda$0() {
        return "kimiko_hud_layer_blur";
    }

    private static final String ensurePipelines$lambda$1() {
        return "kimiko_hud_layer_composite";
    }

    static {
        activeIndex = -1;
        texWidth = -1;
        texHeight = -1;
    }
}

