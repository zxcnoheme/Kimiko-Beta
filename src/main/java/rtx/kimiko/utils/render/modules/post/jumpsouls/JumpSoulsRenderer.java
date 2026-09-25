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
 *  com.mojang.blaze3d.textures.GpuTexture
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.textures.TextureFormat
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.system.MemoryStack
 */
package rtx.kimiko.utils.render.modules.post.jumpsouls;

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
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.nio.ByteBuffer;
import java.util.OptionalInt;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryStack;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.others.RenderSampler;
import rtx.kimiko.utils.render.others.profiler.RenderProfiler;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\n\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ/\u0010\u0012\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0003J!\u0010\u0018\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0013\u0010 \u001a\u00020\bH\u0007b\u0002\b\t\u00a2\u0006\u0004\b \u0010\u0003J\u000f\u0010!\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b!\u0010\u0003J\u000f\u0010\"\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\"\u0010\u0003J\u0017\u0010%\u001a\u00020$2\u0006\u0010#\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020\u001a8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020\u001a8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b)\u0010(R\u0014\u0010*\u001a\u00020\u001a8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b*\u0010(R\u0014\u0010+\u001a\u00020\u001a8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b+\u0010(R\u0014\u0010,\u001a\u00020\u001a8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b,\u0010(R\u0014\u0010-\u001a\u00020\u001a8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b-\u0010(R\u0014\u0010.\u001a\u00020\u001a8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b.\u0010(R\u0014\u0010/\u001a\u00020\u001a8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u0010(R\u0014\u00100\u001a\u00020\u001a8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u0010(R\u0014\u00101\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b1\u00102R\u0014\u00103\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u00102R\u0014\u00104\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00102R\u0014\u00105\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00102R\u0018\u00107\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00108R\u0018\u00109\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u00108R\u0018\u0010:\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0018\u0010<\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010;R\u0018\u0010>\u001a\u0004\u0018\u00010=8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0018\u0010A\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u0010BR\u0018\u0010C\u001a\u0004\u0018\u00010=8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bC\u0010?R\u0018\u0010D\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010BR\u0016\u0010E\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bE\u0010(R\u0016\u0010F\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u0010(R\u0016\u0010G\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bG\u0010H\u00a8\u0006I"}, d2={"Lrtx/kimiko/utils/render/modules/post/jumpsouls/JumpSoulsRenderer;", "", "<init>", "()V", "Lnet/minecraft/Framebuffer;", "renderTarget", "", "uniform", "", "Lkotlin/jvm/JvmStatic;", "apply", "(Lnet/minecraft/Framebuffer;[F)V", "Lcom/mojang/blaze3d/systems/CommandEncoder;", "encoder", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "buffer", "", "mode", "upload", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lcom/mojang/blaze3d/buffers/GpuBuffer;[FF)V", "init", "current", "", "name", "ensureUniform", "(Lcom/mojang/blaze3d/buffers/GpuBuffer;Ljava/lang/String;)Lcom/mojang/blaze3d/buffers/GpuBuffer;", "", "width", "height", "", "ensureTargets", "(II)Z", "clear", "closeTargets", "closeUniforms", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "MAX_CIRCLES", "I", "COLORS_PER_CIRCLE", "MODE_OFFSET", "INV_VP_OFFSET", "DATA_OFFSET", "COLORS_OFFSET", "UNIFORM_FLOATS", "UNIFORM_SIZE", "GLOW_DOWNSCALE", "PIPELINE_ID", "Lnet/minecraft/Identifier;", "COMPOSITE_PIPELINE_ID", "SHADER", "COMPOSITE_SHADER", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "compositePipeline", "groundUniform", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "volumeUniform", "Lcom/mojang/blaze3d/textures/GpuTexture;", "depthCopyTexture", "Lcom/mojang/blaze3d/textures/GpuTexture;", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "depthCopyTextureView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "glowTexture", "glowTextureView", "targetWidth", "targetHeight", "disabledAfterError", "Z", "rtx.kimiko:kimiko"})
public final class JumpSoulsRenderer {
    @NotNull
    public static final JumpSoulsRenderer INSTANCE = new JumpSoulsRenderer();
    public static final int MAX_CIRCLES = 16;
    public static final int COLORS_PER_CIRCLE = 4;
    public static final int MODE_OFFSET = 8;
    public static final int INV_VP_OFFSET = 12;
    public static final int DATA_OFFSET = 28;
    public static final int COLORS_OFFSET = 156;
    public static final int UNIFORM_FLOATS = 412;
    private static final int UNIFORM_SIZE = 1648;
    private static final int GLOW_DOWNSCALE = 4;
    @NotNull
    private static final Identifier PIPELINE_ID = INSTANCE.id("pipeline/post/jumpsouls");
    @NotNull
    private static final Identifier COMPOSITE_PIPELINE_ID = INSTANCE.id("pipeline/post/jumpsouls_composite");
    @NotNull
    private static final Identifier SHADER = INSTANCE.id("post/jumpsouls/jumpsouls");
    @NotNull
    private static final Identifier COMPOSITE_SHADER = INSTANCE.id("post/jumpsouls/composite");
    @Nullable
    private static RenderPipeline pipeline;
    @Nullable
    private static RenderPipeline compositePipeline;
    @Nullable
    private static GpuBuffer groundUniform;
    @Nullable
    private static GpuBuffer volumeUniform;
    @Nullable
    private static GpuTexture depthCopyTexture;
    @Nullable
    private static GpuTextureView depthCopyTextureView;
    @Nullable
    private static GpuTexture glowTexture;
    @Nullable
    private static GpuTextureView glowTextureView;
    private static int targetWidth;
    private static int targetHeight;
    private static boolean disabledAfterError;

    private JumpSoulsRenderer() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void apply(@Nullable Framebuffer renderTarget, @NotNull float[] uniform) {
        RenderProfiler.Scope scope;
        block21: {
            Intrinsics.checkNotNullParameter((Object)uniform, (String)"uniform");
            if (disabledAfterError || renderTarget == null || renderTarget.getColorAttachmentView() == null || renderTarget.getDepthAttachment() == null || renderTarget.textureWidth <= 0 || renderTarget.textureHeight <= 0) {
                return;
            }
            INSTANCE.init();
            RenderPipeline currentPipeline = pipeline;
            RenderPipeline currentComposite = compositePipeline;
            GpuBuffer ground = groundUniform;
            GpuBuffer volume = volumeUniform;
            if (currentPipeline == null || currentComposite == null || ground == null || volume == null || !INSTANCE.ensureTargets(renderTarget.textureWidth, renderTarget.textureHeight)) {
                return;
            }
            boolean glowOn = uniform.length > 5 && uniform[5] > 0.001f;
            scope = RenderProfiler.begin("world.jumpsouls");
            try {
                RenderPass renderPass;
                CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
                Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
                CommandEncoder encoder = commandEncoder;
                GpuTexture gpuTexture = renderTarget.getDepthAttachment();
                Intrinsics.checkNotNull((Object)gpuTexture);
                GpuTexture gpuTexture2 = depthCopyTexture;
                Intrinsics.checkNotNull((Object)gpuTexture2);
                encoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, renderTarget.textureWidth, renderTarget.textureHeight);
                INSTANCE.upload(encoder, ground, uniform, 0.0f);
                if (glowOn) {
                    INSTANCE.upload(encoder, volume, uniform, 1.0f);
                }
                Supplier<String> supplier = JumpSoulsRenderer::apply$lambda$0;
                GpuTextureView gpuTextureView = renderTarget.getColorAttachmentView();
                Intrinsics.checkNotNull((Object)gpuTextureView);
                AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
                Throwable throwable = null;
                try {
                    renderPass = (RenderPass)autoCloseable;
                    boolean bl = false;
                    renderPass.setPipeline(currentPipeline);
                    renderPass.bindTexture("DepthSampler", depthCopyTextureView, RenderSampler.nearest());
                    renderPass.setUniform("Souls", ground);
                    renderPass.draw(0, 6);
// renderPass = Unit.INSTANCE;
                }
                catch (Throwable bl) {
                    throwable = bl;
                    throw bl;
                }
                finally {
                    AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
                }
                if (!glowOn) break block21;
                Supplier<String> supplier2 = JumpSoulsRenderer::apply$lambda$2;
                GpuTextureView gpuTextureView2 = glowTextureView;
                Intrinsics.checkNotNull((Object)gpuTextureView2);
                autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier2, gpuTextureView2, OptionalInt.of(0));
                throwable = null;
                try {
                    renderPass = (RenderPass)autoCloseable;
                    boolean bl = false;
                    renderPass.setPipeline(currentPipeline);
                    renderPass.bindTexture("DepthSampler", depthCopyTextureView, RenderSampler.nearest());
                    renderPass.setUniform("Souls", volume);
                    renderPass.draw(0, 6);
// renderPass = Unit.INSTANCE;
                }
                catch (Throwable bl) {
                    throwable = bl;
                    throw bl;
                }
                finally {
                    AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
                }
                Supplier<String> supplier3 = JumpSoulsRenderer::apply$lambda$4;
                GpuTextureView gpuTextureView3 = renderTarget.getColorAttachmentView();
                Intrinsics.checkNotNull((Object)gpuTextureView3);
                autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier3, gpuTextureView3, OptionalInt.empty());
                throwable = null;
                try {
                    renderPass = (RenderPass)autoCloseable;
                    boolean bl = false;
                    renderPass.setPipeline(currentComposite);
                    renderPass.bindTexture("GlowSampler", glowTextureView, RenderSampler.linear());
                    renderPass.draw(0, 6);
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
                disabledAfterError = true;
                INSTANCE.closeTargets();
            }
        }
        RenderProfiler.end(scope);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void upload(CommandEncoder encoder, GpuBuffer buffer, float[] uniform, float mode) {
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(1648);
            for (int i = 0; i < 412; ++i) {
                data.putFloat(i * 4, i < uniform.length ? uniform[i] : 0.0f);
            }
            data.putFloat(32, mode);
            data.position(0);
            encoder.writeToBuffer(buffer.slice(0L, 1648L), data);
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

    private final void init() {
        if (disabledAfterError) {
            return;
        }
        try {
            if (pipeline == null) {
                pipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(PIPELINE_ID).withVertexShader(SHADER).withFragmentShader(SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("Souls", UniformType.UNIFORM_BUFFER).withSampler("DepthSampler").withBlend(new BlendFunction(SourceFactor.ONE, DestFactor.ONE_MINUS_SRC_ALPHA)).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (compositePipeline == null) {
                compositePipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(COMPOSITE_PIPELINE_ID).withVertexShader(SHADER).withFragmentShader(COMPOSITE_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withSampler("GlowSampler").withBlend(new BlendFunction(SourceFactor.ONE, DestFactor.ONE_MINUS_SRC_ALPHA)).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            groundUniform = this.ensureUniform(groundUniform, "kimiko:jump_souls_uniforms");
            volumeUniform = this.ensureUniform(volumeUniform, "kimiko:jump_souls_volume_uniforms");
        }
        catch (Throwable throwable) {
            disabledAfterError = true;
            pipeline = null;
            compositePipeline = null;
            this.closeUniforms();
        }
    }

    private final GpuBuffer ensureUniform(GpuBuffer current, String name) {
        if (current != null && !current.isClosed() && current.size() >= 1648L) {
            return current;
        }
        GpuBuffer gpuBuffer = current;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        GpuBuffer gpuBuffer2 = RenderSystem.getDevice().createBuffer(() -> JumpSoulsRenderer.ensureUniform$lambda$0(name), 136, 1648L);
        Intrinsics.checkNotNullExpressionValue((Object)gpuBuffer2, (String)"createBuffer(...)");
        return gpuBuffer2;
    }

    private final boolean ensureTargets(int width, int height) {
        GpuDevice gpuDevice = RenderSystem.tryGetDevice();
        if (gpuDevice == null) {
            return false;
        }
        GpuDevice device = gpuDevice;
        if (depthCopyTexture != null && glowTexture != null && targetWidth == width && targetHeight == height) {
            return true;
        }
        this.closeTargets();
        GpuTexture gpuTexture = depthCopyTexture = device.createTexture(JumpSoulsRenderer::ensureTargets$lambda$0, 5, TextureFormat.DEPTH32, width, height, 1, 1);
        Intrinsics.checkNotNull((Object)gpuTexture);
        depthCopyTextureView = device.createTextureView(gpuTexture);
        GpuTexture gpuTexture2 = glowTexture = device.createTexture(JumpSoulsRenderer::ensureTargets$lambda$1, 12, TextureFormat.RGBA8, Math.max(1, width / 4), Math.max(1, height / 4), 1, 1);
        Intrinsics.checkNotNull((Object)gpuTexture2);
        glowTextureView = device.createTextureView(gpuTexture2);
        targetWidth = width;
        targetHeight = height;
        return true;
    }

    @JvmStatic
    public static final void clear() {
        INSTANCE.closeTargets();
    }

    private final void closeTargets() {
        GpuTextureView gpuTextureView = depthCopyTextureView;
        if (gpuTextureView != null) {
            gpuTextureView.close();
        }
        depthCopyTextureView = null;
        GpuTexture gpuTexture = depthCopyTexture;
        if (gpuTexture != null) {
            gpuTexture.close();
        }
        depthCopyTexture = null;
        GpuTextureView gpuTextureView2 = glowTextureView;
        if (gpuTextureView2 != null) {
            gpuTextureView2.close();
        }
        glowTextureView = null;
        GpuTexture gpuTexture2 = glowTexture;
        if (gpuTexture2 != null) {
            gpuTexture2.close();
        }
        glowTexture = null;
        targetWidth = -1;
        targetHeight = -1;
    }

    private final void closeUniforms() {
        GpuBuffer gpuBuffer = groundUniform;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        groundUniform = null;
        GpuBuffer gpuBuffer2 = volumeUniform;
        if (gpuBuffer2 != null) {
            gpuBuffer2.close();
        }
        volumeUniform = null;
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }

    private static final String apply$lambda$0() {
        return "kimiko:jump_souls";
    }

    private static final String apply$lambda$2() {
        return "kimiko:jump_souls_volume";
    }

    private static final String apply$lambda$4() {
        return "kimiko:jump_souls_composite";
    }

    private static final String ensureUniform$lambda$0(String $name) {
        return $name;
    }

    private static final String ensureTargets$lambda$0() {
        return "kimiko:jump_souls_depth_copy";
    }

    private static final String ensureTargets$lambda$1() {
        return "kimiko:jump_souls_glow";
    }

    static {
        targetWidth = -1;
        targetHeight = -1;
    }
}

