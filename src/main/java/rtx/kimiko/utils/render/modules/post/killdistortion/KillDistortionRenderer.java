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
package rtx.kimiko.utils.render.modules.post.killdistortion;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
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

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J%\u0010\r\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0003J\u001f\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0015\u0010\u0003J\u000f\u0010\u0016\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0003J\u000f\u0010\u0017\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0003J\u0017\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u00108\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001eR\u0014\u0010 \u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010!R\u0018\u0010$\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0018\u0010'\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0018\u0010*\u001a\u0004\u0018\u00010)8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0018\u0010-\u001a\u0004\u0018\u00010,8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0018\u0010/\u001a\u0004\u0018\u00010)8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u0010+R\u0018\u00100\u001a\u0004\u0018\u00010,8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u0010.R\u0016\u00101\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u0010\u001eR\u0016\u00102\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u0010\u001eR\u0016\u00103\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u00104\u00a8\u00065"}, d2={"Lrtx/kimiko/utils/render/modules/post/killdistortion/KillDistortionRenderer;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "isDisabledAfterError", "()Z", "Lnet/minecraft/Framebuffer;", "renderTarget", "", "uniform", "", "apply", "(Lnet/minecraft/Framebuffer;[F)V", "init", "", "width", "height", "ensureSceneCopy", "(II)Z", "clear", "closeTargets", "closeUniform", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "UNIFORM_FLOATS", "I", "UNIFORM_SIZE", "PIPELINE_ID", "Lnet/minecraft/Identifier;", "SHADER", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "uniformBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "Lcom/mojang/blaze3d/textures/GpuTexture;", "sceneCopyTexture", "Lcom/mojang/blaze3d/textures/GpuTexture;", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "sceneCopyTextureView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "depthCopyTexture", "depthCopyTextureView", "sceneWidth", "sceneHeight", "disabledAfterError", "Z", "rtx.kimiko:kimiko"})
public final class KillDistortionRenderer {
    @NotNull
    public static final KillDistortionRenderer INSTANCE = new KillDistortionRenderer();
    public static final int UNIFORM_FLOATS = 8;
    private static final int UNIFORM_SIZE = 32;
    @NotNull
    private static final Identifier PIPELINE_ID = INSTANCE.id("pipeline/post/killdistortion");
    @NotNull
    private static final Identifier SHADER = INSTANCE.id("post/killdistortion/killdistortion");
    @Nullable
    private static RenderPipeline pipeline;
    @Nullable
    private static GpuBuffer uniformBuffer;
    @Nullable
    private static GpuTexture sceneCopyTexture;
    @Nullable
    private static GpuTextureView sceneCopyTextureView;
    @Nullable
    private static GpuTexture depthCopyTexture;
    @Nullable
    private static GpuTextureView depthCopyTextureView;
    private static int sceneWidth;
    private static int sceneHeight;
    private static boolean disabledAfterError;

    private KillDistortionRenderer() {
    }

    @JvmStatic
    public static final boolean isDisabledAfterError() {
        return disabledAfterError;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void apply(@Nullable Framebuffer renderTarget, @NotNull float[] uniform) {
        Intrinsics.checkNotNullParameter((Object)uniform, (String)"uniform");
        if (disabledAfterError || renderTarget == null || renderTarget.getColorAttachment() == null || renderTarget.getColorAttachmentView() == null || renderTarget.getDepthAttachment() == null || renderTarget.textureWidth <= 0 || renderTarget.textureHeight <= 0) {
            return;
        }
        INSTANCE.init();
        RenderPipeline currentPipeline = pipeline;
        GpuBuffer currentUniform = uniformBuffer;
        if (currentPipeline == null || currentUniform == null || !INSTANCE.ensureSceneCopy(renderTarget.textureWidth, renderTarget.textureHeight)) {
            return;
        }
        try {
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            GpuTexture gpuTexture = renderTarget.getColorAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = sceneCopyTexture;
            Intrinsics.checkNotNull((Object)gpuTexture2);
            encoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, renderTarget.textureWidth, renderTarget.textureHeight);
            GpuTexture gpuTexture3 = renderTarget.getDepthAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture3);
            GpuTexture gpuTexture4 = depthCopyTexture;
            Intrinsics.checkNotNull((Object)gpuTexture4);
            encoder.copyTextureToTexture(gpuTexture3, gpuTexture4, 0, 0, 0, 0, 0, renderTarget.textureWidth, renderTarget.textureHeight);
            AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
            Throwable throwable = null;
            try {
                MemoryStack stack = (MemoryStack)autoCloseable;
                boolean bl = false;
                ByteBuffer data = stack.calloc(32);
                for (int i = 0; i < 8; ++i) {
                    data.putFloat(i * 4, i < uniform.length ? uniform[i] : 0.0f);
                }
                data.position(0);
                encoder.writeToBuffer(currentUniform.slice(0L, 32L), data);
// stack = Unit.INSTANCE;
            }
            catch (Throwable bl) {
                throwable = bl;
                throw bl;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
            }
            Supplier<String> supplier = KillDistortionRenderer::apply$lambda$1;
            GpuTextureView gpuTextureView = renderTarget.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
            throwable = null;
            try {
                RenderPass renderPass = (RenderPass)autoCloseable;
                boolean bl = false;
                renderPass.setPipeline(currentPipeline);
                renderPass.bindTexture("Scene", sceneCopyTextureView, RenderSampler.linear());
                renderPass.bindTexture("DepthSampler", depthCopyTextureView, RenderSampler.nearest());
                renderPass.setUniform("KillDistortion", currentUniform);
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

    private final void init() {
        if (disabledAfterError) {
            return;
        }
        try {
            GpuBuffer current;
            if (pipeline == null) {
                pipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(PIPELINE_ID).withVertexShader(SHADER).withFragmentShader(SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("KillDistortion", UniformType.UNIFORM_BUFFER).withSampler("Scene").withSampler("DepthSampler").withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if ((current = uniformBuffer) == null || current.isClosed() || current.size() < 32L) {
                this.closeUniform();
                uniformBuffer = RenderSystem.getDevice().createBuffer(KillDistortionRenderer::init$lambda$0, 136, 32L);
            }
        }
        catch (Throwable throwable) {
            disabledAfterError = true;
            pipeline = null;
            this.closeUniform();
        }
    }

    private final boolean ensureSceneCopy(int width, int height) {
        GpuDevice gpuDevice = RenderSystem.tryGetDevice();
        if (gpuDevice == null) {
            return false;
        }
        GpuDevice device = gpuDevice;
        if (sceneCopyTexture != null && sceneWidth == width && sceneHeight == height) {
            return true;
        }
        this.closeTargets();
        GpuTexture gpuTexture = sceneCopyTexture = device.createTexture(KillDistortionRenderer::ensureSceneCopy$lambda$0, 5, TextureFormat.RGBA8, width, height, 1, 1);
        Intrinsics.checkNotNull((Object)gpuTexture);
        sceneCopyTextureView = device.createTextureView(gpuTexture);
        GpuTexture gpuTexture2 = depthCopyTexture = device.createTexture(KillDistortionRenderer::ensureSceneCopy$lambda$1, 5, TextureFormat.DEPTH32, width, height, 1, 1);
        Intrinsics.checkNotNull((Object)gpuTexture2);
        depthCopyTextureView = device.createTextureView(gpuTexture2);
        sceneWidth = width;
        sceneHeight = height;
        return true;
    }

    @JvmStatic
    public static final void clear() {
        INSTANCE.closeTargets();
    }

    private final void closeTargets() {
        GpuTextureView gpuTextureView = sceneCopyTextureView;
        if (gpuTextureView != null) {
            gpuTextureView.close();
        }
        sceneCopyTextureView = null;
        GpuTexture gpuTexture = sceneCopyTexture;
        if (gpuTexture != null) {
            gpuTexture.close();
        }
        sceneCopyTexture = null;
        GpuTextureView gpuTextureView2 = depthCopyTextureView;
        if (gpuTextureView2 != null) {
            gpuTextureView2.close();
        }
        depthCopyTextureView = null;
        GpuTexture gpuTexture2 = depthCopyTexture;
        if (gpuTexture2 != null) {
            gpuTexture2.close();
        }
        depthCopyTexture = null;
        sceneWidth = -1;
        sceneHeight = -1;
    }

    private final void closeUniform() {
        GpuBuffer gpuBuffer = uniformBuffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        uniformBuffer = null;
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }

    private static final String apply$lambda$1() {
        return "kimiko:kill_distortion";
    }

    private static final String init$lambda$0() {
        return "kimiko:kill_distortion_uniforms";
    }

    private static final String ensureSceneCopy$lambda$0() {
        return "kimiko:kill_distortion_scene_copy";
    }

    private static final String ensureSceneCopy$lambda$1() {
        return "kimiko:kill_distortion_depth_copy";
    }

    static {
        sceneWidth = -1;
        sceneHeight = -1;
    }
}

