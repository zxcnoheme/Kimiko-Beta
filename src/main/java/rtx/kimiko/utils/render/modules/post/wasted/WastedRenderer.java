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
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.system.MemoryUtil
 */
package rtx.kimiko.utils.render.modules.post.wasted;

import com.mojang.blaze3d.buffers.GpuBuffer;
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
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryUtil;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.modules.post.wasted.WastedState;
import rtx.kimiko.utils.render.others.RenderSampler;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J3\u0010\f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0003J\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00198\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u00198\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u00198\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001dR\u0014\u0010 \u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0018\u0010#\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0018\u0010&\u001a\u0004\u0018\u00010%8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0018\u0010)\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0018\u0010,\u001a\u0004\u0018\u00010+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0018\u0010/\u001a\u0004\u0018\u00010.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0016\u00101\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u0010!R\u0016\u00102\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u0010!R\u0016\u00103\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u00104\u00a8\u00065"}, d2={"Lrtx/kimiko/utils/render/modules/post/wasted/WastedRenderer;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "invalidate", "", "tintR", "tintG", "tintB", "tintStrength", "apply", "(FFFF)V", "", "ensureInitialized", "()Z", "", "width", "height", "ensureTextures", "(II)V", "closeTextures", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "PIPELINE_ID", "Lnet/minecraft/Identifier;", "VERTEX_SHADER", "FRAGMENT_SHADER", "UNIFORM_SIZE", "I", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Ljava/nio/ByteBuffer;", "dataBuffer", "Ljava/nio/ByteBuffer;", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "uniformBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "Lcom/mojang/blaze3d/textures/GpuTexture;", "sceneCopy", "Lcom/mojang/blaze3d/textures/GpuTexture;", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "sceneCopyView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "lastWidth", "lastHeight", "disabledAfterError", "Z", "rtx.kimiko:kimiko"})
public final class WastedRenderer {
    @NotNull
    public static final WastedRenderer INSTANCE = new WastedRenderer();
    @NotNull
    private static final Identifier PIPELINE_ID = INSTANCE.id("pipeline/post/wasted");
    @NotNull
    private static final Identifier VERTEX_SHADER = INSTANCE.id("post/wasted/wasted");
    @NotNull
    private static final Identifier FRAGMENT_SHADER = INSTANCE.id("post/wasted/wasted");
    private static final int UNIFORM_SIZE = 48;
    @Nullable
    private static RenderPipeline pipeline;
    @Nullable
    private static ByteBuffer dataBuffer;
    @Nullable
    private static GpuBuffer uniformBuffer;
    @Nullable
    private static GpuTexture sceneCopy;
    @Nullable
    private static GpuTextureView sceneCopyView;
    private static int lastWidth;
    private static int lastHeight;
    private static boolean disabledAfterError;

    private WastedRenderer() {
    }

    @JvmStatic
    public static final void invalidate() {
        disabledAfterError = false;
        INSTANCE.closeTextures();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void apply(float tintR, float tintG, float tintB, float tintStrength) {
        if (disabledAfterError || !WastedState.isActive()) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.getFramebuffer() == null) {
            return;
        }
        int width = minecraft.getFramebuffer().textureWidth;
        int height = minecraft.getFramebuffer().textureHeight;
        if (width <= 0 || height <= 0 || !INSTANCE.ensureInitialized()) {
            return;
        }
        try {
            INSTANCE.ensureTextures(width, height);
            ByteBuffer byteBuffer = dataBuffer;
            Intrinsics.checkNotNull((Object)byteBuffer);
            ByteBuffer data = byteBuffer;
            GpuBuffer gpuBuffer = uniformBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            GpuBuffer uniform = gpuBuffer;
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            GpuTexture gpuTexture = minecraft.getFramebuffer().getColorAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = sceneCopy;
            Intrinsics.checkNotNull((Object)gpuTexture2);
            encoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, width, height);
            data.clear();
            data.putFloat(WastedState.progress());
            data.putFloat((float)(System.currentTimeMillis() % 100000L) / 1000.0f);
            data.putFloat((float)width / (float)Math.max(1, height));
            data.putFloat(WastedState.strength());
            data.putFloat(tintR);
            data.putFloat(tintG);
            data.putFloat(tintB);
            data.putFloat(tintStrength);
            data.putFloat(WastedState.flash());
            data.putFloat(WastedState.radialBlur());
            data.putFloat(0.0f);
            data.putFloat(0.0f);
            data.flip();
            encoder.writeToBuffer(uniform.slice(), data);
            Supplier<String> supplier = WastedRenderer::apply$lambda$0;
            GpuTextureView gpuTextureView = minecraft.getFramebuffer().getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
            Throwable throwable = null;
            try {
                RenderPass pass = (RenderPass)autoCloseable;
                boolean bl = false;
                RenderPipeline renderPipeline = pipeline;
                Intrinsics.checkNotNull((Object)renderPipeline);
                pass.setPipeline(renderPipeline);
                pass.bindTexture("Sampler0", sceneCopyView, RenderSampler.linear());
                pass.setUniform("WastedData", uniform.slice());
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
            disabledAfterError = true;
            INSTANCE.closeTextures();
        }
    }

    private final boolean ensureInitialized() {
        boolean bl;
        GpuBuffer currentUniform = uniformBuffer;
        if (pipeline != null && dataBuffer != null && currentUniform != null && !currentUniform.isClosed()) {
            return true;
        }
        try {
            GpuBuffer uniform;
            if (pipeline == null) {
                pipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(PIPELINE_ID).withVertexShader(VERTEX_SHADER).withFragmentShader(FRAGMENT_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("WastedData", UniformType.UNIFORM_BUFFER).withSampler("Sampler0").withoutBlend().withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (dataBuffer == null) {
                dataBuffer = MemoryUtil.memAlloc((int)48);
            }
            if ((uniform = uniformBuffer) == null || uniform.isClosed()) {
                uniformBuffer = RenderSystem.getDevice().createBuffer(WastedRenderer::ensureInitialized$lambda$0, 136, 48L);
            }
            bl = true;
        }
        catch (Throwable throwable) {
            disabledAfterError = true;
            bl = false;
        }
        return bl;
    }

    private final void ensureTextures(int width, int height) {
        GpuTexture current = sceneCopy;
        if (current != null && !current.isClosed() && width == lastWidth && height == lastHeight) {
            return;
        }
        this.closeTextures();
        sceneCopy = RenderSystem.getDevice().createTexture(WastedRenderer::ensureTextures$lambda$0, 5, TextureFormat.RGBA8, width, height, 1, 1);
        GpuDevice gpuDevice = RenderSystem.getDevice();
        GpuTexture gpuTexture = sceneCopy;
        Intrinsics.checkNotNull((Object)gpuTexture);
        sceneCopyView = gpuDevice.createTextureView(gpuTexture);
        lastWidth = width;
        lastHeight = height;
    }

    private final void closeTextures() {
        GpuTextureView gpuTextureView = sceneCopyView;
        if (gpuTextureView != null) {
            gpuTextureView.close();
        }
        sceneCopyView = null;
        GpuTexture gpuTexture = sceneCopy;
        if (gpuTexture != null) {
            gpuTexture.close();
        }
        sceneCopy = null;
        lastWidth = -1;
        lastHeight = -1;
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }

    private static final String apply$lambda$0() {
        return "kimiko:wasted_pass";
    }

    private static final String ensureInitialized$lambda$0() {
        return "kimiko:wasted_uniform";
    }

    private static final String ensureTextures$lambda$0() {
        return "kimiko:wasted_scene";
    }
}

