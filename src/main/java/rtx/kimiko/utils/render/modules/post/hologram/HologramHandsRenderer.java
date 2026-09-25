/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Snippet
 *  com.mojang.blaze3d.platform.DepthTestFunction
 *  com.mojang.blaze3d.systems.CommandEncoder
 *  com.mojang.blaze3d.systems.GpuDevice
 *  com.mojang.blaze3d.systems.RenderPass
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.FilterMode
 *  com.mojang.blaze3d.textures.GpuTexture
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.textures.TextureFormat
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
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.system.MemoryUtil
 */
package rtx.kimiko.utils.render.modules.post.hologram;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.nio.Buffer;
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
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryUtil;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.modules.post.hologram.HologramHandsConfig;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u0013\u0010\u0007\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0007\u0010\u0003J\u0013\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0003J\u0013\u0010\n\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\f\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\u0003J\u000f\u0010\r\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\r\u0010\u000bJ\u0017\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0003J\u0017\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J7\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010 \u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b \u0010!J\u000f\u0010#\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b%\u0010$J\u0019\u0010&\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0002\u00a2\u0006\u0004\b&\u0010\u0011J\u000f\u0010(\u001a\u00020'H\u0002\u00a2\u0006\u0004\b(\u0010)J'\u0010-\u001a\u00020'2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020'2\u0006\u0010,\u001a\u00020'H\u0002\u00a2\u0006\u0004\b-\u0010.J\u0017\u00101\u001a\u00020\u00042\u0006\u00100\u001a\u00020/H\u0002\u00a2\u0006\u0004\b1\u00102J\u000f\u00103\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b3\u0010\u0003J\u0017\u00106\u001a\u0002052\u0006\u00104\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b6\u00107R\u0014\u00108\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010:\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010<\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010;R\u0014\u0010=\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010;R\u0014\u0010>\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0018\u0010A\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u0010BR\u0018\u0010D\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u0018\u0010F\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u0010ER\u0018\u0010H\u001a\u0004\u0018\u00010G8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bH\u0010IR\u0018\u0010J\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bJ\u0010KR\u0018\u0010L\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bL\u0010KR\u0018\u0010M\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010KR\u0018\u0010O\u001a\u0004\u0018\u00010N8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bO\u0010PR\u0018\u0010Q\u001a\u0004\u0018\u00010N8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u0010PR\u0018\u0010R\u001a\u0004\u0018\u00010N8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bR\u0010PR\u0016\u0010S\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010?R\u0016\u0010T\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010?R\u0018\u0010U\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bU\u0010VR\u0016\u0010W\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bW\u0010XR\u0016\u0010Y\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010XR\u0016\u0010Z\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010X\u00a8\u0006["}, d2={"Lrtx/kimiko/utils/render/modules/post/hologram/HologramHandsRenderer;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "captureBeforeHandRender", "captureAfterHandRender", "renderCapturedHologram", "", "hasCapturedHands", "()Z", "shutdown", "shouldRender", "Lnet/minecraft/Framebuffer;", "target", "ensureReady", "(Lnet/minecraft/Framebuffer;)Z", "init", "ensureTextures", "(Lnet/minecraft/Framebuffer;)V", "", "label", "Lcom/mojang/blaze3d/textures/TextureFormat;", "format", "", "width", "height", "usage", "Lcom/mojang/blaze3d/textures/GpuTexture;", "createTexture", "(Ljava/lang/String;Lcom/mojang/blaze3d/textures/TextureFormat;III)Lcom/mojang/blaze3d/textures/GpuTexture;", "writeUniforms", "(II)V", "Lnet/minecraft/GpuSampler;", "linear", "()Lnet/minecraft/GpuSampler;", "nearest", "isUsable", "", "time", "()F", "value", "min", "max", "clamp", "(FFF)F", "", "throwable", "disableAfterError", "(Ljava/lang/Throwable;)V", "closeTextures", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "NAMESPACE", "Ljava/lang/String;", "PIPELINE_ID", "Lnet/minecraft/Identifier;", "VERTEX_SHADER", "FRAGMENT_SHADER", "UNIFORM_BYTES", "I", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "uniformBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "dummyVertexBuffer", "Ljava/nio/ByteBuffer;", "uniformData", "Ljava/nio/ByteBuffer;", "beforeColorTexture", "Lcom/mojang/blaze3d/textures/GpuTexture;", "afterColorTexture", "beforeDepthTexture", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "beforeColorView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "afterColorView", "beforeDepthView", "lastWidth", "lastHeight", "lastDepthFormat", "Lcom/mojang/blaze3d/textures/TextureFormat;", "capturedHands", "Z", "capturedDepth", "disabledAfterError", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nHologramHandsRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HologramHandsRenderer.kt\nrtx/kimiko/utils/render/modules/post/hologram/HologramHandsRenderer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,324:1\n1#2:325\n*E\n"})
public final class HologramHandsRenderer {
    @NotNull
    public static final HologramHandsRenderer INSTANCE = new HologramHandsRenderer();
    @NotNull
    private static final String NAMESPACE = Kimiko.Companion.namespace();
    @NotNull
    private static final Identifier PIPELINE_ID = INSTANCE.id("pipeline/effects/hands_hologram");
    @NotNull
    private static final Identifier VERTEX_SHADER = INSTANCE.id("effects/hands_hologram/fullscreen");
    @NotNull
    private static final Identifier FRAGMENT_SHADER = INSTANCE.id("effects/hands_hologram/hologram");
    private static final int UNIFORM_BYTES = 64;
    @Nullable
    private static RenderPipeline pipeline;
    @Nullable
    private static GpuBuffer uniformBuffer;
    @Nullable
    private static GpuBuffer dummyVertexBuffer;
    @Nullable
    private static ByteBuffer uniformData;
    @Nullable
    private static GpuTexture beforeColorTexture;
    @Nullable
    private static GpuTexture afterColorTexture;
    @Nullable
    private static GpuTexture beforeDepthTexture;
    @Nullable
    private static GpuTextureView beforeColorView;
    @Nullable
    private static GpuTextureView afterColorView;
    @Nullable
    private static GpuTextureView beforeDepthView;
    private static int lastWidth;
    private static int lastHeight;
    @Nullable
    private static TextureFormat lastDepthFormat;
    private static boolean capturedHands;
    private static boolean capturedDepth;
    private static boolean disabledAfterError;

    private HologramHandsRenderer() {
    }

    @JvmStatic
    public static final void captureBeforeHandRender() {
        capturedHands = false;
        capturedDepth = false;
        if (!INSTANCE.shouldRender()) {
            return;
        }
        Framebuffer framebuffer2 = MinecraftClient.getInstance().getFramebuffer();
        Intrinsics.checkNotNullExpressionValue((Object)framebuffer2, (String)"getMainRenderTarget(...)");
        Framebuffer target = framebuffer2;
        if (!INSTANCE.isUsable(target) || !INSTANCE.ensureReady(target)) {
            return;
        }
        try {
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            GpuTexture gpuTexture = target.getColorAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = beforeColorTexture;
            Intrinsics.checkNotNull((Object)gpuTexture2);
            encoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, target.textureWidth, target.textureHeight);
            GpuTexture gpuTexture3 = target.getDepthAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture3);
            GpuTexture gpuTexture4 = beforeDepthTexture;
            Intrinsics.checkNotNull((Object)gpuTexture4);
            encoder.copyTextureToTexture(gpuTexture3, gpuTexture4, 0, 0, 0, 0, 0, target.textureWidth, target.textureHeight);
            capturedDepth = true;
        }
        catch (Throwable throwable) {
            INSTANCE.disableAfterError(throwable);
        }
    }

    @JvmStatic
    public static final void captureAfterHandRender() {
        if (!INSTANCE.shouldRender()) {
            return;
        }
        Framebuffer framebuffer2 = MinecraftClient.getInstance().getFramebuffer();
        Intrinsics.checkNotNullExpressionValue((Object)framebuffer2, (String)"getMainRenderTarget(...)");
        Framebuffer target = framebuffer2;
        if (!INSTANCE.isUsable(target) || !INSTANCE.ensureReady(target)) {
            return;
        }
        try {
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            GpuTexture gpuTexture = target.getColorAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = afterColorTexture;
            Intrinsics.checkNotNull((Object)gpuTexture2);
            commandEncoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, target.textureWidth, target.textureHeight);
            capturedHands = true;
        }
        catch (Throwable throwable) {
            INSTANCE.disableAfterError(throwable);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void renderCapturedHologram() {
        if (!capturedHands) {
            return;
        }
        capturedHands = false;
        if (!INSTANCE.shouldRender()) {
            return;
        }
        Framebuffer framebuffer2 = MinecraftClient.getInstance().getFramebuffer();
        Intrinsics.checkNotNullExpressionValue((Object)framebuffer2, (String)"getMainRenderTarget(...)");
        Framebuffer target = framebuffer2;
        if (!INSTANCE.isUsable(target) || !INSTANCE.ensureReady(target)) {
            return;
        }
        try {
            INSTANCE.writeUniforms(target.textureWidth, target.textureHeight);
            ByteBuffer byteBuffer = uniformData;
            Intrinsics.checkNotNull((Object)byteBuffer);
            ByteBuffer data = byteBuffer;
            GpuBuffer gpuBuffer = uniformBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            GpuBuffer uniform = gpuBuffer;
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            encoder.writeToBuffer(uniform.slice(0L, (long)data.remaining()), data);
            GpuTextureView depthView = target.getDepthAttachmentView();
            GpuTextureView noHandDepthView = capturedDepth ? beforeDepthView : depthView;
            Supplier<String> supplier = HologramHandsRenderer::renderCapturedHologram$lambda$0;
            GpuTextureView gpuTextureView = target.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty(), null, OptionalDouble.empty());
            Throwable throwable = null;
            try {
                RenderPass pass = (RenderPass)autoCloseable;
                boolean bl = false;
                RenderPipeline renderPipeline = pipeline;
                Intrinsics.checkNotNull((Object)renderPipeline);
                pass.setPipeline(renderPipeline);
                GpuBuffer gpuBuffer2 = dummyVertexBuffer;
                Intrinsics.checkNotNull((Object)gpuBuffer2);
                pass.setVertexBuffer(0, gpuBuffer2);
                pass.bindTexture("SceneSampler", afterColorView, INSTANCE.linear());
                pass.bindTexture("BackgroundSampler", afterColorView, INSTANCE.linear());
                pass.bindTexture("BeforeSampler", beforeColorView, INSTANCE.linear());
                pass.bindTexture("DepthSampler", depthView, INSTANCE.nearest());
                pass.bindTexture("NoHandDepthSampler", noHandDepthView, INSTANCE.nearest());
                pass.setUniform("HandsHologramData", uniform.slice());
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
            INSTANCE.disableAfterError(throwable);
        }
    }

    @JvmStatic
    public static final boolean hasCapturedHands() {
        return capturedHands;
    }

    @JvmStatic
    public static final void shutdown() {
        INSTANCE.closeTextures();
        GpuBuffer gpuBuffer = uniformBuffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        GpuBuffer gpuBuffer2 = dummyVertexBuffer;
        if (gpuBuffer2 != null) {
            gpuBuffer2.close();
        }
        ByteBuffer byteBuffer = uniformData;
        if (byteBuffer != null) {
            ByteBuffer it = byteBuffer;
            boolean bl = false;
            MemoryUtil.memFree((Buffer)it);
        }
        uniformBuffer = null;
        dummyVertexBuffer = null;
        uniformData = null;
        pipeline = null;
        capturedHands = false;
        capturedDepth = false;
    }

    private final boolean shouldRender() {
        if (disabledAfterError || !HologramHandsConfig.enabled) {
            return false;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        return minecraft.player != null && minecraft.world != null;
    }

    private final boolean ensureReady(Framebuffer target) {
        if (pipeline == null || uniformBuffer == null || dummyVertexBuffer == null || uniformData == null) {
            this.init();
        }
        this.ensureTextures(target);
        return pipeline != null && uniformBuffer != null && dummyVertexBuffer != null && uniformData != null && beforeColorView != null && afterColorView != null && beforeDepthView != null;
    }

    private final void init() {
        try {
            pipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(PIPELINE_ID).withVertexShader(VERTEX_SHADER).withFragmentShader(FRAGMENT_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("HandsHologramData", UniformType.UNIFORM_BUFFER).withSampler("SceneSampler").withSampler("BeforeSampler").withSampler("BackgroundSampler").withSampler("DepthSampler").withSampler("NoHandDepthSampler").withoutBlend().withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            uniformData = MemoryUtil.memAlloc((int)64);
            uniformBuffer = RenderSystem.getDevice().createBuffer(HologramHandsRenderer::init$lambda$0, 136, 64L);
            ByteBuffer dummy = MemoryUtil.memAlloc((int)4);
            dummy.putInt(0);
            dummy.flip();
            dummyVertexBuffer = RenderSystem.getDevice().createBuffer(HologramHandsRenderer::init$lambda$1, 40, dummy);
            MemoryUtil.memFree((Buffer)dummy);
        }
        catch (Throwable throwable) {
            this.disableAfterError(throwable);
        }
    }

    private final void ensureTextures(Framebuffer target) {
        GpuTexture gpuTexture = target.getDepthAttachment();
        Intrinsics.checkNotNull((Object)gpuTexture);
        TextureFormat textureFormat = gpuTexture.getFormat();
        Intrinsics.checkNotNullExpressionValue((Object)textureFormat, (String)"getFormat(...)");
        TextureFormat depthFormat = textureFormat;
        if (beforeColorTexture != null && target.textureWidth == lastWidth && target.textureHeight == lastHeight && depthFormat == lastDepthFormat) {
            return;
        }
        this.closeTextures();
        int usage = 5;
        beforeColorTexture = this.createTexture("kimiko:hologram_before_color", TextureFormat.RGBA8, target.textureWidth, target.textureHeight, usage);
        afterColorTexture = this.createTexture("kimiko:hologram_after_color", TextureFormat.RGBA8, target.textureWidth, target.textureHeight, usage);
        beforeDepthTexture = this.createTexture("kimiko:hologram_before_depth", depthFormat, target.textureWidth, target.textureHeight, usage);
        GpuDevice gpuDevice = RenderSystem.getDevice();
        GpuTexture gpuTexture2 = beforeColorTexture;
        Intrinsics.checkNotNull((Object)gpuTexture2);
        beforeColorView = gpuDevice.createTextureView(gpuTexture2);
        GpuDevice gpuDevice2 = RenderSystem.getDevice();
        GpuTexture gpuTexture3 = afterColorTexture;
        Intrinsics.checkNotNull((Object)gpuTexture3);
        afterColorView = gpuDevice2.createTextureView(gpuTexture3);
        GpuDevice gpuDevice3 = RenderSystem.getDevice();
        GpuTexture gpuTexture4 = beforeDepthTexture;
        Intrinsics.checkNotNull((Object)gpuTexture4);
        beforeDepthView = gpuDevice3.createTextureView(gpuTexture4);
        lastWidth = target.textureWidth;
        lastHeight = target.textureHeight;
        lastDepthFormat = depthFormat;
    }

    private final GpuTexture createTexture(String label, TextureFormat format, int width, int height, int usage) {
        GpuTexture gpuTexture = RenderSystem.getDevice().createTexture(() -> HologramHandsRenderer.createTexture$lambda$0(label), usage, format, width, height, 1, 1);
        Intrinsics.checkNotNullExpressionValue((Object)gpuTexture, (String)"createTexture(...)");
        return gpuTexture;
    }

    private final void writeUniforms(int width, int height) {
        int color = HologramHandsConfig.color;
        ByteBuffer byteBuffer = uniformData;
        if (byteBuffer == null) {
            return;
        }
        ByteBuffer data = byteBuffer;
        data.clear();
        data.putFloat((float)(color >> 16 & 0xFF) / 255.0f);
        data.putFloat((float)(color >> 8 & 0xFF) / 255.0f);
        data.putFloat((float)(color & 0xFF) / 255.0f);
        data.putFloat(this.clamp(HologramHandsConfig.opacity, 0.0f, 1.0f));
        data.putFloat(this.time());
        data.putFloat(HologramHandsConfig.scanlines);
        data.putFloat(HologramHandsConfig.glitch);
        data.putFloat(HologramHandsConfig.glow);
        data.putFloat(capturedDepth ? 1.0f : 0.0f);
        data.putFloat(HologramHandsConfig.scanSpeed);
        data.putFloat(HologramHandsConfig.flicker);
        data.putFloat(this.clamp(HologramHandsConfig.transparency, 0.0f, 1.0f));
        data.putFloat(width);
        data.putFloat(height);
        data.putFloat(1.0f / (float)Math.max(width, 1));
        data.putFloat(1.0f / (float)Math.max(height, 1));
        data.flip();
    }

    private final GpuSampler linear() {
        GpuSampler gpuSampler2 = RenderSystem.getSamplerCache().get(FilterMode.LINEAR);
        Intrinsics.checkNotNullExpressionValue((Object)gpuSampler2, (String)"getClampToEdge(...)");
        return gpuSampler2;
    }

    private final GpuSampler nearest() {
        GpuSampler gpuSampler2 = RenderSystem.getSamplerCache().get(FilterMode.NEAREST);
        Intrinsics.checkNotNullExpressionValue((Object)gpuSampler2, (String)"getClampToEdge(...)");
        return gpuSampler2;
    }

    private final boolean isUsable(Framebuffer target) {
        return target != null && target.getColorAttachment() != null && target.getColorAttachmentView() != null && target.getDepthAttachment() != null && target.getDepthAttachmentView() != null && target.textureWidth > 0 && target.textureHeight > 0;
    }

    private final float time() {
        return (float)(System.nanoTime() % 180000000000L) / 1.0E9f;
    }

    private final float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    private final void disableAfterError(Throwable throwable) {
        disabledAfterError = true;
        HologramHandsRenderer.shutdown();
    }

    private final void closeTextures() {
        GpuTextureView gpuTextureView = beforeColorView;
        if (gpuTextureView != null) {
            gpuTextureView.close();
        }
        GpuTextureView gpuTextureView2 = afterColorView;
        if (gpuTextureView2 != null) {
            gpuTextureView2.close();
        }
        GpuTextureView gpuTextureView3 = beforeDepthView;
        if (gpuTextureView3 != null) {
            gpuTextureView3.close();
        }
        GpuTexture gpuTexture = beforeColorTexture;
        if (gpuTexture != null) {
            gpuTexture.close();
        }
        GpuTexture gpuTexture2 = afterColorTexture;
        if (gpuTexture2 != null) {
            gpuTexture2.close();
        }
        GpuTexture gpuTexture3 = beforeDepthTexture;
        if (gpuTexture3 != null) {
            gpuTexture3.close();
        }
        beforeColorView = null;
        afterColorView = null;
        beforeDepthView = null;
        beforeColorTexture = null;
        afterColorTexture = null;
        beforeDepthTexture = null;
        lastWidth = -1;
        lastHeight = -1;
        lastDepthFormat = null;
        capturedDepth = false;
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)NAMESPACE, (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }

    private static final String renderCapturedHologram$lambda$0() {
        return "kimiko:hands_hologram";
    }

    private static final String init$lambda$0() {
        return "kimiko:hands_hologram_uniform";
    }

    private static final String init$lambda$1() {
        return "kimiko:hands_hologram_dummy_vertex";
    }

    private static final String createTexture$lambda$0(String $label) {
        return $label;
    }

    static {
        lastWidth = -1;
        lastHeight = -1;
    }
}

