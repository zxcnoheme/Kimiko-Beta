/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.pipeline.BlendFunction
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Snippet
 *  com.mojang.blaze3d.platform.DepthTestFunction
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
 *  net.minecraft.client.gl.GpuSampler
 *  net.minecraft.util.Hand
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.render.OutlineVertexConsumerProvider
 *  net.minecraft.client.gl.SimpleFramebuffer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.system.MemoryStack
 */
package rtx.kimiko.utils.render.modules.post.itemoutline;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.nio.ByteBuffer;
import java.util.OptionalInt;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.util.Hand;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OutlineVertexConsumerProvider;
import net.minecraft.client.gl.SimpleFramebuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryStack;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.modules.impl.Visuals.ViewModel;
import rtx.kimiko.utils.render.others.RenderSampler;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0014\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J\u000f\u0010\n\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\n\u0010\u0003J)\u0010\u0010\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001f\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J'\u0010%\u001a\u00020\b2\u0006\u0010\"\u001a\u00020!2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020#H\u0002\u00a2\u0006\u0004\b%\u0010&J'\u0010'\u001a\u00020\b2\u0006\u0010\"\u001a\u00020!2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020#H\u0002\u00a2\u0006\u0004\b'\u0010&J/\u0010+\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020!2\u0006\u0010(\u001a\u00020!2\u0006\u0010*\u001a\u00020)2\u0006\u0010$\u001a\u00020#H\u0002\u00a2\u0006\u0004\b+\u0010,J\u0013\u0010-\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b-\u0010\u0003J\u000f\u0010.\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b.\u0010\u0003J\u000f\u0010/\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b/\u0010\u0003J\u001b\u00100\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002\u00a2\u0006\u0004\b0\u00101J\u0017\u00104\u001a\u0002032\u0006\u00102\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b4\u00105R\u0014\u00106\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0014\u00108\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010:\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b:\u00109R\u0014\u0010;\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b;\u00109R\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010>R\u0014\u0010?\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b?\u00107R\u0014\u0010@\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u00107R\u0014\u0010A\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bA\u0010BR\u0014\u0010C\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bC\u0010BR\u0014\u0010D\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010BR\u0014\u0010E\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010BR\u0014\u0010F\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u0010BR\u0014\u0010G\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010BR\u0014\u0010H\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010BR\u0018\u0010J\u001a\u0004\u0018\u00010I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bJ\u0010KR\u0018\u0010L\u001a\u0004\u0018\u00010I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bL\u0010KR\u0018\u0010M\u001a\u0004\u0018\u00010I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010KR\u0018\u0010N\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0018\u0010P\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010OR\u0018\u0010Q\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u0010RR\u0018\u0010S\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010RR\u0016\u0010T\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u00107R\u0016\u0010U\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bU\u00107R\u0016\u0010V\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010W\u00a8\u0006X"}, d2={"Lrtx/kimiko/utils/render/modules/post/itemoutline/ItemOutlineRenderer;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "outlineColor", "()I", "", "run", "init", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "buffer", "size", "", "name", "ensureBuffer", "(Lcom/mojang/blaze3d/buffers/GpuBuffer;ILjava/lang/String;)Lcom/mojang/blaze3d/buffers/GpuBuffer;", "width", "height", "", "ensureTargets", "(II)Z", "Lnet/minecraft/OutlineVertexConsumerProvider;", "outlineSource", "drainSilhouette", "(Lnet/minecraft/OutlineVertexConsumerProvider;)V", "Lcom/mojang/blaze3d/systems/CommandEncoder;", "encoder", "Lnet/minecraft/SimpleFramebuffer;", "target", "writeDtConfig", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lnet/minecraft/SimpleFramebuffer;)V", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "source", "Lnet/minecraft/GpuSampler;", "sampler", "distanceTransformH", "(Lcom/mojang/blaze3d/textures/GpuTextureView;Lnet/minecraft/SimpleFramebuffer;Lnet/minecraft/GpuSampler;)V", "distanceTransformV", "dist", "", "alpha", "isolines", "(Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;FLnet/minecraft/GpuSampler;)V", "clear", "closeTargets", "closeBuffers", "closeBuffer", "(Lcom/mojang/blaze3d/buffers/GpuBuffer;)Lcom/mojang/blaze3d/buffers/GpuBuffer;", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "OUTLINE_COLOR", "I", "MAX_DIST", "F", "RADIUS", "THICKNESS", "", "LEVELS", "[F", "DT_CONFIG_SIZE", "ISO_CONFIG_SIZE", "VERTEX", "Lnet/minecraft/Identifier;", "DT_H_SHADER", "DT_V_SHADER", "ISO_SHADER", "DT_H_PIPELINE_ID", "DT_V_PIPELINE_ID", "ISO_PIPELINE_ID", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "dtHPipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "dtVPipeline", "isoPipeline", "dtConfigBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "isoConfigBuffer", "silhouette", "Lnet/minecraft/SimpleFramebuffer;", "swap", "targetWidth", "targetHeight", "disabledAfterError", "Z", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nItemOutlineRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ItemOutlineRenderer.kt\nrtx/kimiko/utils/render/modules/post/itemoutline/ItemOutlineRenderer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,309:1\n1#2:310\n*E\n"})
public final class ItemOutlineRenderer {
    @NotNull
    public static final ItemOutlineRenderer INSTANCE = new ItemOutlineRenderer();
    private static final int OUTLINE_COLOR = -1;
    private static final float MAX_DIST = 20.0f;
    private static final float RADIUS = 20.0f;
    private static final float THICKNESS = 2.0f;
    @NotNull
    private static final float[] LEVELS;
    private static final int DT_CONFIG_SIZE = 16;
    private static final int ISO_CONFIG_SIZE = 32;
    @NotNull
    private static final Identifier VERTEX;
    @NotNull
    private static final Identifier DT_H_SHADER;
    @NotNull
    private static final Identifier DT_V_SHADER;
    @NotNull
    private static final Identifier ISO_SHADER;
    @NotNull
    private static final Identifier DT_H_PIPELINE_ID;
    @NotNull
    private static final Identifier DT_V_PIPELINE_ID;
    @NotNull
    private static final Identifier ISO_PIPELINE_ID;
    @Nullable
    private static RenderPipeline dtHPipeline;
    @Nullable
    private static RenderPipeline dtVPipeline;
    @Nullable
    private static RenderPipeline isoPipeline;
    @Nullable
    private static GpuBuffer dtConfigBuffer;
    @Nullable
    private static GpuBuffer isoConfigBuffer;
    @Nullable
    private static SimpleFramebuffer silhouette;
    @Nullable
    private static SimpleFramebuffer swap;
    private static int targetWidth;
    private static int targetHeight;
    private static boolean disabledAfterError;

    private ItemOutlineRenderer() {
    }

    @JvmStatic
    public static final int outlineColor() {
        return -1;
    }

    @JvmStatic
    public static final void run() {
        if (disabledAfterError) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        if (minecraftClient2 == null) {
            return;
        }
        MinecraftClient mc = minecraftClient2;
        OutlineVertexConsumerProvider outlineSource = mc.getBufferBuilders() != null ? mc.getBufferBuilders().getOutlineVertexConsumers() : null;
        Framebuffer framebuffer2 = mc.getFramebuffer();
        Intrinsics.checkNotNullExpressionValue((Object)framebuffer2, (String)"getMainRenderTarget(...)");
        Framebuffer main = framebuffer2;
        if (outlineSource == null || main.getColorAttachmentView() == null) {
            return;
        }
        float alpha = Math.max(ViewModel.Companion.outlineAlpha(Hand.MAIN_HAND), ViewModel.Companion.outlineAlpha(Hand.OFF_HAND));
        if (alpha <= 0.001f) {
            return;
        }
        INSTANCE.init();
        if (dtHPipeline == null || dtVPipeline == null || isoPipeline == null) {
            return;
        }
        if (!INSTANCE.ensureTargets(main.textureWidth, main.textureHeight)) {
            return;
        }
        try {
            INSTANCE.drainSilhouette(outlineSource);
            GpuSampler linear = RenderSampler.linear();
            SimpleFramebuffer simpleFramebuffer2 = silhouette;
            Intrinsics.checkNotNull((Object)simpleFramebuffer2);
            GpuTextureView gpuTextureView = simpleFramebuffer2.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            SimpleFramebuffer simpleFramebuffer3 = swap;
            Intrinsics.checkNotNull((Object)simpleFramebuffer3);
            INSTANCE.distanceTransformH(gpuTextureView, simpleFramebuffer3, linear);
            SimpleFramebuffer simpleFramebuffer4 = swap;
            Intrinsics.checkNotNull((Object)simpleFramebuffer4);
            GpuTextureView gpuTextureView2 = simpleFramebuffer4.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView2);
            SimpleFramebuffer simpleFramebuffer5 = silhouette;
            Intrinsics.checkNotNull((Object)simpleFramebuffer5);
            INSTANCE.distanceTransformV(gpuTextureView2, simpleFramebuffer5, linear);
            GpuTextureView gpuTextureView3 = main.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView3);
            SimpleFramebuffer simpleFramebuffer6 = silhouette;
            Intrinsics.checkNotNull((Object)simpleFramebuffer6);
            GpuTextureView gpuTextureView4 = simpleFramebuffer6.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView4);
            INSTANCE.isolines(gpuTextureView3, gpuTextureView4, alpha, linear);
        }
        catch (Throwable throwable) {
            disabledAfterError = true;
            INSTANCE.closeTargets();
            INSTANCE.closeBuffers();
        }
    }

    private final void init() {
        if (disabledAfterError) {
            return;
        }
        try {
            if (dtHPipeline == null) {
                dtHPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(DT_H_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(DT_H_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("DtConfig", UniformType.UNIFORM_BUFFER).withSampler("MaskTex").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (dtVPipeline == null) {
                dtVPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(DT_V_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(DT_V_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("DtConfig", UniformType.UNIFORM_BUFFER).withSampler("DistH").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (isoPipeline == null) {
                isoPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(ISO_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(ISO_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("IsoConfig", UniformType.UNIFORM_BUFFER).withSampler("DistTex").withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            dtConfigBuffer = this.ensureBuffer(dtConfigBuffer, 16, "kimiko:vm_outline_dt_config");
            isoConfigBuffer = this.ensureBuffer(isoConfigBuffer, 32, "kimiko:vm_outline_iso_config");
        }
        catch (Throwable throwable) {
            disabledAfterError = true;
            dtHPipeline = null;
            dtVPipeline = null;
            isoPipeline = null;
            this.closeBuffers();
        }
    }

    private final GpuBuffer ensureBuffer(GpuBuffer buffer, int size, String name) {
        if (buffer != null && !buffer.isClosed() && buffer.size() >= (long)size) {
            return buffer;
        }
        GpuBuffer gpuBuffer = buffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        GpuBuffer gpuBuffer2 = RenderSystem.getDevice().createBuffer(() -> ItemOutlineRenderer.ensureBuffer$lambda$0(name), 136, (long)size);
        Intrinsics.checkNotNullExpressionValue((Object)gpuBuffer2, (String)"createBuffer(...)");
        return gpuBuffer2;
    }

    private final boolean ensureTargets(int width, int height) {
        GpuDevice device = RenderSystem.tryGetDevice();
        if (device == null || width <= 0 || height <= 0) {
            return false;
        }
        if (silhouette != null && swap != null && targetWidth == width && targetHeight == height) {
            return true;
        }
        this.closeTargets();
        silhouette = new SimpleFramebuffer("kimiko_vm_outline_silhouette", width, height, true);
        swap = new SimpleFramebuffer("kimiko_vm_outline_swap", width, height, false);
        targetWidth = width;
        targetHeight = height;
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void drainSilhouette(OutlineVertexConsumerProvider outlineSource) {
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        Supplier<String> supplier = ItemOutlineRenderer::drainSilhouette$lambda$0;
        SimpleFramebuffer simpleFramebuffer2 = silhouette;
        Intrinsics.checkNotNull((Object)simpleFramebuffer2);
        GpuTextureView gpuTextureView = simpleFramebuffer2.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0));
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
        GpuTextureView prevColor = RenderSystem.outputColorTextureOverride;
        GpuTextureView prevDepth = RenderSystem.outputDepthTextureOverride;
        SimpleFramebuffer simpleFramebuffer3 = silhouette;
        Intrinsics.checkNotNull((Object)simpleFramebuffer3);
        RenderSystem.outputColorTextureOverride = simpleFramebuffer3.getColorAttachmentView();
        SimpleFramebuffer simpleFramebuffer4 = silhouette;
        Intrinsics.checkNotNull((Object)simpleFramebuffer4);
        RenderSystem.outputDepthTextureOverride = simpleFramebuffer4.getDepthAttachmentView();
        try {
            outlineSource.draw();
        }
        finally {
            RenderSystem.outputColorTextureOverride = prevColor;
            RenderSystem.outputDepthTextureOverride = prevDepth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void writeDtConfig(CommandEncoder encoder, SimpleFramebuffer target) {
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(16);
            data.putFloat(0, 1.0f / (float)target.textureWidth);
            data.putFloat(4, 1.0f / (float)target.textureHeight);
            data.putFloat(8, 20.0f);
            data.putFloat(12, 20.0f);
            data.position(0);
            GpuBuffer gpuBuffer = dtConfigBuffer;
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void distanceTransformH(GpuTextureView source, SimpleFramebuffer target, GpuSampler sampler) {
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        this.writeDtConfig(encoder, target);
        Supplier<String> supplier = ItemOutlineRenderer::distanceTransformH$lambda$0;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0));
        Throwable throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = dtHPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            pass.bindTexture("MaskTex", source, sampler);
            GpuBuffer gpuBuffer = dtConfigBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("DtConfig", gpuBuffer);
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
    private final void distanceTransformV(GpuTextureView source, SimpleFramebuffer target, GpuSampler sampler) {
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        this.writeDtConfig(encoder, target);
        Supplier<String> supplier = ItemOutlineRenderer::distanceTransformV$lambda$0;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0));
        Throwable throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = dtVPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            pass.bindTexture("DistH", source, sampler);
            GpuBuffer gpuBuffer = dtConfigBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("DtConfig", gpuBuffer);
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
    private final void isolines(GpuTextureView target, GpuTextureView dist, float alpha, GpuSampler sampler) {
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            float phase = (float)(System.currentTimeMillis() % 1000L) / 1000.0f;
            ByteBuffer data = stack.calloc(32);
            data.putFloat(0, 20.0f);
            data.putFloat(4, 2.0f);
            data.putFloat(8, alpha);
            data.putFloat(12, phase);
            data.putFloat(16, LEVELS[0]);
            data.putFloat(20, LEVELS[1]);
            data.putFloat(24, LEVELS[2]);
            data.putFloat(28, 0.0f);
            data.position(0);
            GpuBuffer gpuBuffer = isoConfigBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 32L), data);
// stack = Unit.INSTANCE;
        }
        catch (Throwable bl) {
            throwable = bl;
            throw bl;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        autoCloseable = (AutoCloseable)encoder.createRenderPass(ItemOutlineRenderer::isolines$lambda$1, target, OptionalInt.empty());
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = isoPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            pass.bindTexture("DistTex", dist, sampler);
            GpuBuffer gpuBuffer = isoConfigBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("IsoConfig", gpuBuffer);
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

    @JvmStatic
    public static final void clear() {
        INSTANCE.closeTargets();
        INSTANCE.closeBuffers();
    }

    private final void closeTargets() {
        SimpleFramebuffer simpleFramebuffer2 = silhouette;
        if (simpleFramebuffer2 != null) {
            simpleFramebuffer2.delete();
        }
        silhouette = null;
        SimpleFramebuffer simpleFramebuffer3 = swap;
        if (simpleFramebuffer3 != null) {
            simpleFramebuffer3.delete();
        }
        swap = null;
        targetWidth = -1;
        targetHeight = -1;
    }

    private final void closeBuffers() {
        dtConfigBuffer = this.closeBuffer(dtConfigBuffer);
        isoConfigBuffer = this.closeBuffer(isoConfigBuffer);
    }

    private final GpuBuffer closeBuffer(GpuBuffer buffer) {
        block0: {
            GpuBuffer gpuBuffer = buffer;
            if (gpuBuffer == null) break block0;
            gpuBuffer.close();
        }
        return null;
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }

    private static final String ensureBuffer$lambda$0(String $name) {
        return $name;
    }

    private static final String drainSilhouette$lambda$0() {
        return "kimiko:vm_outline_clear";
    }

    private static final String distanceTransformH$lambda$0() {
        return "kimiko:vm_outline_dt_h";
    }

    private static final String distanceTransformV$lambda$0() {
        return "kimiko:vm_outline_dt_v";
    }

    private static final String isolines$lambda$1() {
        return "kimiko:vm_outline_iso";
    }

    static {
        float[] fArray = new float[]{5.0f, 10.0f, 15.0f};
        LEVELS = fArray;
        VERTEX = INSTANCE.id("post/itemoutline/itemoutline");
        DT_H_SHADER = INSTANCE.id("post/itemoutline/dt_h");
        DT_V_SHADER = INSTANCE.id("post/itemoutline/dt_v");
        ISO_SHADER = INSTANCE.id("post/itemoutline/isoline");
        DT_H_PIPELINE_ID = INSTANCE.id("pipeline/post/itemoutline/dt_h");
        DT_V_PIPELINE_ID = INSTANCE.id("pipeline/post/itemoutline/dt_v");
        ISO_PIPELINE_ID = INSTANCE.id("pipeline/post/itemoutline/isoline");
        targetWidth = -1;
        targetHeight = -1;
    }
}

