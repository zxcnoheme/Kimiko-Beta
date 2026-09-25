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
package rtx.kimiko.utils.render.modules.post.saturation;

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
import rtx.kimiko.utils.render.others.RenderSampler;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J'\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0019R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0018\u0010 \u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0018\u0010#\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0018\u0010&\u001a\u0004\u0018\u00010%8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0018\u0010(\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010+R\u0016\u0010-\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010.\u00a8\u0006/"}, d2={"Lrtx/kimiko/utils/render/modules/post/saturation/Saturation2D;", "", "<init>", "()V", "", "saturation", "", "Lkotlin/jvm/JvmStatic;", "applyWithCopy", "(F)V", "", "ensureInitialized", "()Z", "", "width", "height", "ensureTextures", "(II)V", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "targetView", "sourceView", "apply", "(Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;F)V", "Lnet/minecraft/Identifier;", "PIPELINE_ID", "Lnet/minecraft/Identifier;", "VERTEX_SHADER", "FRAGMENT_SHADER", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Ljava/nio/ByteBuffer;", "dataBuffer", "Ljava/nio/ByteBuffer;", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "uniformBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "Lcom/mojang/blaze3d/textures/GpuTexture;", "tempTexture", "Lcom/mojang/blaze3d/textures/GpuTexture;", "tempTextureView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "lastWidth", "I", "lastHeight", "disabledAfterError", "Z", "rtx.kimiko:kimiko"})
public final class Saturation2D {
    @NotNull
    public static final Saturation2D INSTANCE = new Saturation2D();
    @NotNull
    private static final Identifier PIPELINE_ID;
    @NotNull
    private static final Identifier VERTEX_SHADER;
    @NotNull
    private static final Identifier FRAGMENT_SHADER;
    @Nullable
    private static RenderPipeline pipeline;
    @Nullable
    private static ByteBuffer dataBuffer;
    @Nullable
    private static GpuBuffer uniformBuffer;
    @Nullable
    private static GpuTexture tempTexture;
    @Nullable
    private static GpuTextureView tempTextureView;
    private static int lastWidth;
    private static int lastHeight;
    private static boolean disabledAfterError;

    private Saturation2D() {
    }

    @JvmStatic
    public static final void applyWithCopy(float saturation) {
        if (disabledAfterError) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient client = minecraftClient2;
        if (client.getFramebuffer() == null || !(Math.abs(saturation) <= Float.MAX_VALUE)) {
            return;
        }
        float clampedSaturation = Math.clamp(saturation, 0.0f, 2.0f);
        if (Math.abs(clampedSaturation - 1.0f) <= 5.0E-4f) {
            return;
        }
        int width = client.getFramebuffer().textureWidth;
        int height = client.getFramebuffer().textureHeight;
        if (width <= 0 || height <= 0 || !INSTANCE.ensureInitialized()) {
            return;
        }
        try {
            INSTANCE.ensureTextures(width, height);
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            GpuTexture gpuTexture = client.getFramebuffer().getColorAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = tempTexture;
            Intrinsics.checkNotNull((Object)gpuTexture2);
            encoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, width, height);
            GpuTextureView gpuTextureView = client.getFramebuffer().getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            GpuTextureView gpuTextureView2 = tempTextureView;
            Intrinsics.checkNotNull((Object)gpuTextureView2);
            INSTANCE.apply(gpuTextureView, gpuTextureView2, clampedSaturation);
        }
        catch (Throwable ignored) {
            disabledAfterError = true;
        }
    }

    private final boolean ensureInitialized() {
        boolean bl;
        if (pipeline != null && dataBuffer != null && uniformBuffer != null) {
            return true;
        }
        try {
            pipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(PIPELINE_ID).withVertexShader(VERTEX_SHADER).withFragmentShader(FRAGMENT_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("SaturationData", UniformType.UNIFORM_BUFFER).withSampler("Sampler0").withoutBlend().withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            dataBuffer = MemoryUtil.memAlloc((int)16);
            uniformBuffer = RenderSystem.getDevice().createBuffer(Saturation2D::ensureInitialized$lambda$0, 136, 16L);
            bl = true;
        }
        catch (Throwable ignored) {
            disabledAfterError = true;
            bl = false;
        }
        return bl;
    }

    private final void ensureTextures(int width, int height) {
        if (tempTexture != null && width == lastWidth && height == lastHeight) {
            return;
        }
        GpuTextureView gpuTextureView = tempTextureView;
        if (gpuTextureView != null) {
            gpuTextureView.close();
        }
        GpuTexture gpuTexture = tempTexture;
        if (gpuTexture != null) {
            gpuTexture.close();
        }
        tempTexture = RenderSystem.getDevice().createTexture(Saturation2D::ensureTextures$lambda$0, 13, TextureFormat.RGBA8, width, height, 1, 1);
        GpuDevice gpuDevice = RenderSystem.getDevice();
        GpuTexture gpuTexture2 = tempTexture;
        Intrinsics.checkNotNull((Object)gpuTexture2);
        tempTextureView = gpuDevice.createTextureView(gpuTexture2);
        lastWidth = width;
        lastHeight = height;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void apply(GpuTextureView targetView, GpuTextureView sourceView, float saturation) {
        ByteBuffer byteBuffer = dataBuffer;
        if (byteBuffer == null) {
            return;
        }
        ByteBuffer data = byteBuffer;
        GpuBuffer gpuBuffer = uniformBuffer;
        if (gpuBuffer == null) {
            return;
        }
        GpuBuffer uniform = gpuBuffer;
        data.clear();
        data.putFloat(saturation);
        data.flip();
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        encoder.writeToBuffer(uniform.slice(), data);
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(Saturation2D::apply$lambda$0, targetView, OptionalInt.empty());
        Throwable throwable = null;
        try {
            RenderPass renderPass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = pipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            renderPass.setPipeline(renderPipeline);
            renderPass.bindTexture("Sampler0", sourceView, RenderSampler.nearest());
            renderPass.setUniform("SaturationData", uniform.slice());
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

    private static final String ensureInitialized$lambda$0() {
        return "kimiko:saturation_uniform";
    }

    private static final String ensureTextures$lambda$0() {
        return "kimiko:saturation_temp";
    }

    private static final String apply$lambda$0() {
        return "kimiko:saturation_pass";
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"pipeline/post/saturation");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        PIPELINE_ID = identifier2;
        Identifier identifier3 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"post/saturation/saturation");
        Intrinsics.checkNotNullExpressionValue((Object)identifier3, (String)"fromNamespaceAndPath(...)");
        VERTEX_SHADER = identifier3;
        Identifier identifier4 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"post/saturation/saturation");
        Intrinsics.checkNotNullExpressionValue((Object)identifier4, (String)"fromNamespaceAndPath(...)");
        FRAGMENT_SHADER = identifier4;
    }
}

