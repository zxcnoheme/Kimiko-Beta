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
package rtx.kimiko.utils.render.modules.post.explosionwave;

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

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\n\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\f\u0010\u0003J\u001f\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\bH\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u0013\u0010\u0003J\u000f\u0010\u0014\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0003J\u000f\u0010\u0015\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0003J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\r8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\r8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\r8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001cR\u0014\u0010\u001f\u001a\u00020\r8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001cR\u0014\u0010 \u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u001cR\u0014\u0010!\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010\"R\u0018\u0010%\u001a\u0004\u0018\u00010$8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0018\u0010(\u001a\u0004\u0018\u00010'8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0018\u0010+\u001a\u0004\u0018\u00010*8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010,R\u0018\u0010.\u001a\u0004\u0018\u00010-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010/R\u0018\u00100\u001a\u0004\u0018\u00010*8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u0010,R\u0018\u00101\u001a\u0004\u0018\u00010-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u0010/R\u0016\u00102\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u0010\u001cR\u0016\u00103\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u0010\u001cR\u0016\u00104\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00105\u00a8\u00066"}, d2={"Lrtx/kimiko/utils/render/modules/post/explosionwave/ExplosionWaveRenderer;", "", "<init>", "()V", "Lnet/minecraft/Framebuffer;", "renderTarget", "", "uniform", "", "Lkotlin/jvm/JvmStatic;", "apply", "(Lnet/minecraft/Framebuffer;[F)V", "init", "", "width", "height", "", "ensureSceneCopy", "(II)Z", "clear", "closeTargets", "closeUniform", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "MAX_WAVES", "I", "INV_VP_OFFSET", "DATA_OFFSET", "UNIFORM_FLOATS", "UNIFORM_SIZE", "PIPELINE_ID", "Lnet/minecraft/Identifier;", "SHADER", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "uniformBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "Lcom/mojang/blaze3d/textures/GpuTexture;", "sceneCopyTexture", "Lcom/mojang/blaze3d/textures/GpuTexture;", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "sceneCopyTextureView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "depthCopyTexture", "depthCopyTextureView", "sceneWidth", "sceneHeight", "disabledAfterError", "Z", "rtx.kimiko:kimiko"})
public final class ExplosionWaveRenderer {
    @NotNull
    public static final ExplosionWaveRenderer INSTANCE = new ExplosionWaveRenderer();
    public static final int MAX_WAVES = 8;
    public static final int INV_VP_OFFSET = 8;
    public static final int DATA_OFFSET = 24;
    public static final int UNIFORM_FLOATS = 120;
    private static final int UNIFORM_SIZE = 480;
    @NotNull
    private static final Identifier PIPELINE_ID = INSTANCE.id("pipeline/post/explosionwave");
    @NotNull
    private static final Identifier SHADER = INSTANCE.id("post/explosionwave/explosionwave");
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

    private ExplosionWaveRenderer() {
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
                ByteBuffer data = stack.calloc(480);
                for (int i = 0; i < 120; ++i) {
                    data.putFloat(i * 4, i < uniform.length ? uniform[i] : 0.0f);
                }
                data.position(0);
                encoder.writeToBuffer(currentUniform.slice(0L, 480L), data);
// stack = Unit.INSTANCE;
            }
            catch (Throwable bl) {
                throwable = bl;
                throw bl;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
            }
            Supplier<String> supplier = ExplosionWaveRenderer::apply$lambda$1;
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
                renderPass.setUniform("Waves", currentUniform);
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
                pipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(PIPELINE_ID).withVertexShader(SHADER).withFragmentShader(SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("Waves", UniformType.UNIFORM_BUFFER).withSampler("Scene").withSampler("DepthSampler").withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if ((current = uniformBuffer) == null || current.isClosed() || current.size() < 480L) {
                this.closeUniform();
                uniformBuffer = RenderSystem.getDevice().createBuffer(ExplosionWaveRenderer::init$lambda$0, 136, 480L);
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
        GpuTexture gpuTexture = sceneCopyTexture = device.createTexture(ExplosionWaveRenderer::ensureSceneCopy$lambda$0, 5, TextureFormat.RGBA8, width, height, 1, 1);
        Intrinsics.checkNotNull((Object)gpuTexture);
        sceneCopyTextureView = device.createTextureView(gpuTexture);
        GpuTexture gpuTexture2 = depthCopyTexture = device.createTexture(ExplosionWaveRenderer::ensureSceneCopy$lambda$1, 5, TextureFormat.DEPTH32, width, height, 1, 1);
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
        return "kimiko:explosion_wave";
    }

    private static final String init$lambda$0() {
        return "kimiko:explosion_wave_uniforms";
    }

    private static final String ensureSceneCopy$lambda$0() {
        return "kimiko:explosion_wave_scene_copy";
    }

    private static final String ensureSceneCopy$lambda$1() {
        return "kimiko:explosion_wave_depth_copy";
    }

    static {
        sceneWidth = -1;
        sceneHeight = -1;
    }
}

