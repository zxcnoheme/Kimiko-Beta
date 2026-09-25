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
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.system.MemoryStack
 */
package rtx.kimiko.utils.render.modules.post.themeshock;

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
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
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
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryStack;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.api.ui.theme.ThemeManager;
import rtx.kimiko.api.ui.theme.ThemeWave;
import rtx.kimiko.utils.render.others.RenderSampler;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001XB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J#\u0010\t\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000b\u001a\u00020\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u000b\u0010\u0003J\u001f\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000e\u0010\nJ\u000f\u0010\u000f\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0003J\u0013\u0010\u0010\u001a\u00020\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u000f\u0010\u0011\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0003J\u000f\u0010\u0012\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0003J\u000f\u0010\u0013\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0003J\u0011\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010 \u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b \u0010!J\u001f\u0010#\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b#\u0010$J\u0013\u0010%\u001a\u00020\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b%\u0010\u0003J\u000f\u0010&\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b&\u0010\u0003J\u000f\u0010'\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b'\u0010\u0003J\u0017\u0010(\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b(\u0010)J\u000f\u0010*\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b*\u0010\u0003J\u000f\u0010+\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b+\u0010\u0003J\u000f\u0010-\u001a\u00020,H\u0002\u00a2\u0006\u0004\b-\u0010.J\u0017\u00102\u001a\u0002012\u0006\u00100\u001a\u00020/H\u0002\u00a2\u0006\u0004\b2\u00103R\u0014\u00104\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0014\u00106\u001a\u00020\u00048\u0002X\u0082D\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0014\u00108\u001a\u00020\u00178\u0002X\u0082D\u00a2\u0006\u0006\n\u0004\b8\u00105R\u0014\u00109\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b9\u00105R\u0014\u0010:\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010<\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010;R\u0018\u0010>\u001a\u0004\u0018\u00010=8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0016\u0010@\u001a\u00020,8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u0010AR\u0016\u0010B\u001a\u00020,8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u0010AR$\u0010E\u001a\u0012\u0012\u0004\u0012\u00020\u00140Cj\b\u0012\u0004\u0012\u00020\u0014`D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010FR\u001a\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00140G8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010IR\u0018\u0010K\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010LR\u0018\u0010N\u001a\u0004\u0018\u00010M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0016\u0010P\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u00105R\u0016\u0010Q\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u00105R\u0016\u0010R\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bR\u00105R\u0016\u0010S\u001a\u00020,8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010AR\u0016\u0010T\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u00107R\u0016\u0010U\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bU\u00107R\u0016\u0010V\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010W\u00a8\u0006Y"}, d2={"Lrtx/kimiko/utils/render/modules/post/themeshock/ThemeShockwaveRenderer;", "", "<init>", "()V", "", "guiClickX", "guiClickY", "", "Lkotlin/jvm/JvmStatic;", "trigger", "(FF)V", "cancel", "normX", "normY", "arm", "dropPending", "onFrameEnd", "warmup", "startWave", "retireActive", "Lrtx/kimiko/utils/render/modules/post/themeshock/ThemeShockwaveRenderer$Wave;", "acquire", "()Lrtx/kimiko/utils/render/modules/post/themeshock/ThemeShockwaveRenderer$Wave;", "", "width", "height", "ensureFrameTexture", "(II)V", "Lnet/minecraft/Framebuffer;", "main", "", "now", "drawWaves", "(Lnet/minecraft/Framebuffer;J)V", "wave", "waveProgress", "(Lrtx/kimiko/utils/render/modules/post/themeshock/ThemeShockwaveRenderer$Wave;J)F", "invalidate", "fail", "releaseResources", "closeWave", "(Lrtx/kimiko/utils/render/modules/post/themeshock/ThemeShockwaveRenderer$Wave;)V", "releaseIdleFrameTexture", "closeFrameTexture", "", "uiOpen", "()Z", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "UNIFORM_SIZE", "I", "DURATION_SECONDS", "F", "MAX_WAVES", "IDLE_FRAMES_BEFORE_RELEASE", "SHADER", "Lnet/minecraft/Identifier;", "PIPELINE_ID", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "warmedUp", "Z", "disabledAfterError", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "active", "Ljava/util/ArrayList;", "Ljava/util/ArrayDeque;", "free", "Ljava/util/ArrayDeque;", "Lcom/mojang/blaze3d/textures/GpuTexture;", "frameTexture", "Lcom/mojang/blaze3d/textures/GpuTexture;", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "frameView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "frameWidth", "frameHeight", "idleFrames", "pendingTrigger", "pendingCenterX", "pendingCenterY", "pendingStartNanos", "J", "Wave", "rtx.kimiko:kimiko"})
public final class ThemeShockwaveRenderer {
    @NotNull
    public static final ThemeShockwaveRenderer INSTANCE = new ThemeShockwaveRenderer();
    private static final int UNIFORM_SIZE = 32;
    private static final float DURATION_SECONDS = 1.15f;
    private static final int MAX_WAVES = 6;
    private static final int IDLE_FRAMES_BEFORE_RELEASE = 120;
    @NotNull
    private static final Identifier SHADER = INSTANCE.id("post/themeshock/themeshock");
    @NotNull
    private static final Identifier PIPELINE_ID = INSTANCE.id("pipeline/post/themeshock/themeshock");
    @Nullable
    private static RenderPipeline pipeline;
    private static boolean warmedUp;
    private static boolean disabledAfterError;
    @NotNull
    private static final ArrayList<Wave> active;
    @NotNull
    private static final ArrayDeque<Wave> free;
    @Nullable
    private static GpuTexture frameTexture;
    @Nullable
    private static GpuTextureView frameView;
    private static int frameWidth;
    private static int frameHeight;
    private static int idleFrames;
    private static boolean pendingTrigger;
    private static float pendingCenterX;
    private static float pendingCenterY;
    private static long pendingStartNanos;

    private ThemeShockwaveRenderer() {
    }

    @JvmStatic
    public static final void trigger(float guiClickX, float guiClickY) {
        float normX = Render2DCoordinateSpace.normalizedDesignX(guiClickX);
        float normY = Render2DCoordinateSpace.normalizedDesignY(guiClickY);
        INSTANCE.arm(Math.clamp(normX, 0.0f, 1.0f), Math.clamp(1.0f - normY, 0.0f, 1.0f));
    }

    @JvmStatic
    public static final void cancel() {
        pendingTrigger = false;
        INSTANCE.retireActive();
    }

    private final void arm(float normX, float normY) {
        if (disabledAfterError) {
            return;
        }
        long start = ThemeManager.armWave(normX, normY);
        pendingCenterX = normX;
        pendingCenterY = normY;
        pendingStartNanos = start;
        pendingTrigger = true;
    }

    private final void dropPending() {
        pendingTrigger = false;
        ThemeManager.clearPendingWave();
        ThemeWave.demoteSpatial();
    }

    @JvmStatic
    public static final void onFrameEnd() {
        if (disabledAfterError) {
            if (pendingTrigger) {
                INSTANCE.dropPending();
            }
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Framebuffer main = mc.getFramebuffer();
        if (main == null || main.getColorAttachment() == null || main.getColorAttachmentView() == null || main.textureWidth <= 0 || main.textureHeight <= 0) {
            if (pendingTrigger) {
                INSTANCE.dropPending();
            }
            return;
        }
        if (!warmedUp && (pendingTrigger || INSTANCE.uiOpen())) {
            INSTANCE.warmup();
            if (disabledAfterError) {
                if (pendingTrigger) {
                    INSTANCE.dropPending();
                }
                return;
            }
        }
        if (pendingTrigger) {
            if (warmedUp) {
                pendingTrigger = false;
                INSTANCE.startWave();
            } else {
                INSTANCE.dropPending();
            }
        }
        if (active.isEmpty()) {
            INSTANCE.releaseIdleFrameTexture();
            return;
        }
        long now = ThemeWave.frameNanos();
        Iterator<Wave> iterator = active.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Wave> it = iterator;
        while (it.hasNext()) {
            Wave wave = (Wave) (it.next());
            GpuBuffer uniforms = wave.getUniforms();
            if (!(INSTANCE.waveProgress(wave, now) >= 1.0f) && uniforms != null && !uniforms.isClosed()) continue;
            it.remove();
            free.push(wave);
        }
        if (active.isEmpty()) {
            INSTANCE.releaseIdleFrameTexture();
            return;
        }
        idleFrames = 0;
        try {
            INSTANCE.drawWaves(main, now);
        }
        catch (Throwable throwable) {
            INSTANCE.fail();
        }
    }

    private final void warmup() {
        try {
            if (pipeline == null) {
                pipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(PIPELINE_ID).withVertexShader(SHADER).withFragmentShader(SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("ShockwaveParams", UniformType.UNIFORM_BUFFER).withSampler("Sampler0").withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            GpuDevice gpuDevice = RenderSystem.getDevice();
            RenderPipeline renderPipeline = pipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            if (!gpuDevice.precompilePipeline(renderPipeline).isValid()) {
                this.fail();
                return;
            }
            warmedUp = true;
        }
        catch (Throwable throwable) {
            this.fail();
        }
    }

    private final void startWave() {
        try {
            Wave wave = this.acquire();
            if (wave == null) {
                return;
            }
            Wave wave2 = wave;
            wave2.setStartNanos(pendingStartNanos != 0L ? pendingStartNanos : System.nanoTime());
            wave2.setCenterX(pendingCenterX);
            wave2.setCenterY(pendingCenterY);
            active.add(wave2);
        }
        catch (Throwable throwable) {
            this.fail();
        }
    }

    private final void retireActive() {
        for (Wave wave : active) {
            free.push(wave);
        }
        active.clear();
    }

    private final Wave acquire() {
        Wave wave;
        if (active.size() >= MAX_WAVES) {
            wave = active.remove(0);
        } else if (!free.isEmpty()) {
            wave = free.pop();
        } else {
            wave = new Wave();
        }
        Wave wave2 = wave;
        GpuDevice gpuDevice = RenderSystem.getDevice();
        Intrinsics.checkNotNullExpressionValue((Object)gpuDevice, (String)"getDevice(...)");
        GpuDevice device = gpuDevice;
        GpuBuffer uniforms = wave2.getUniforms();
        if (uniforms == null || uniforms.isClosed()) {
            wave2.setUniforms(device.createBuffer(ThemeShockwaveRenderer::acquire$lambda$0, 136, 32L));
        }
        return wave2;
    }

    private final void ensureFrameTexture(int width, int height) {
        GpuTexture texture = frameTexture;
        GpuTextureView view = frameView;
        if (texture != null && !texture.isClosed() && view != null && !view.isClosed() && frameWidth == width && frameHeight == height) {
            return;
        }
        this.closeFrameTexture();
        GpuDevice gpuDevice = RenderSystem.getDevice();
        Intrinsics.checkNotNullExpressionValue((Object)gpuDevice, (String)"getDevice(...)");
        GpuDevice device = gpuDevice;
        GpuTexture gpuTexture = frameTexture = device.createTexture(ThemeShockwaveRenderer::ensureFrameTexture$lambda$0, 5, TextureFormat.RGBA8, width, height, 1, 1);
        Intrinsics.checkNotNull((Object)gpuTexture);
        frameView = device.createTextureView(gpuTexture);
        frameWidth = width;
        frameHeight = height;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void drawWaves(Framebuffer main, long now) {
        this.ensureFrameTexture(main.textureWidth, main.textureHeight);
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        GpuTexture gpuTexture = main.getColorAttachment();
        Intrinsics.checkNotNull((Object)gpuTexture);
        GpuTexture gpuTexture2 = frameTexture;
        Intrinsics.checkNotNull((Object)gpuTexture2);
        encoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, main.textureWidth, main.textureHeight);
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(32);
            Iterator<Wave> iterator = active.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
            Iterator<Wave> iterator2 = iterator;
            while (iterator2.hasNext()) {
                Wave wave = (Wave) (iterator2.next());
                data.putFloat(0, wave.getCenterX());
                data.putFloat(4, wave.getCenterY());
                data.putFloat(8, INSTANCE.waveProgress(wave, now));
                data.putFloat(12, 1.0f);
                data.putFloat(16, main.textureWidth);
                data.putFloat(20, main.textureHeight);
                data.putFloat(24, 0.075f);
                data.putFloat(28, 0.1f);
                data.position(0);
                GpuBuffer gpuBuffer = wave.getUniforms();
                Intrinsics.checkNotNull((Object)gpuBuffer);
                encoder.writeToBuffer(gpuBuffer.slice(0L, 32L), data);
            }
// stack = Unit.INSTANCE;
        }
        catch (Throwable bl) {
            throwable = bl;
            throw bl;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        Supplier<String> supplier = ThemeShockwaveRenderer::drawWaves$lambda$1;
        GpuTextureView gpuTextureView = main.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = pipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            for (int i = active.size() - 1; -1 < i; --i) {
                Wave wave = (Wave) (active.get(i));
                GpuBuffer gpuBuffer = wave.getUniforms();
                Intrinsics.checkNotNull((Object)gpuBuffer);
                pass.setUniform("ShockwaveParams", gpuBuffer);
                pass.bindTexture("Sampler0", frameView, RenderSampler.linear());
                pass.draw(0, 6);
            }
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

    private final float waveProgress(Wave wave, long now) {
        return Math.max(0.0f, (float)(now - wave.getStartNanos()) / 1.0E9f / DURATION_SECONDS);
    }

    @JvmStatic
    public static final void invalidate() {
        INSTANCE.releaseResources();
        disabledAfterError = false;
        ThemeWave.cancel();
    }

    private final void fail() {
        this.releaseResources();
        disabledAfterError = true;
        ThemeWave.cancel();
    }

    private final void releaseResources() {
        for (Wave wave : active) {
            this.closeWave(wave);
        }
        active.clear();
        for (Wave wave : free) {
            this.closeWave(wave);
        }
        free.clear();
        this.closeFrameTexture();
        pendingTrigger = false;
        warmedUp = false;
    }

    private final void closeWave(Wave wave) {
        GpuBuffer gpuBuffer = wave.getUniforms();
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        wave.setUniforms(null);
    }

    private final void releaseIdleFrameTexture() {
        if (frameTexture == null && frameView == null) {
            return;
        }
        if (++idleFrames < 120) {
            return;
        }
        this.closeFrameTexture();
    }

    private final void closeFrameTexture() {
        GpuTextureView gpuTextureView = frameView;
        if (gpuTextureView != null) {
            gpuTextureView.close();
        }
        frameView = null;
        GpuTexture gpuTexture = frameTexture;
        if (gpuTexture != null) {
            gpuTexture.close();
        }
        frameTexture = null;
        frameWidth = -1;
        frameHeight = -1;
        idleFrames = 0;
    }

    private final boolean uiOpen() {
        boolean bl;
        try {
            bl = UI.Companion.isOpen();
        }
        catch (Throwable throwable) {
            bl = false;
        }
        return bl;
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }

    private static final String acquire$lambda$0() {
        return "kimiko:theme_shockwave_uniforms";
    }

    private static final String ensureFrameTexture$lambda$0() {
        return "kimiko:theme_shockwave_frame";
    }

    private static final String drawWaves$lambda$1() {
        return "kimiko:theme_shockwave";
    }

    static {
        active = new ArrayList();
        free = new ArrayDeque();
        frameWidth = -1;
        frameHeight = -1;
        pendingCenterX = 0.5f;
        pendingCenterY = 0.5f;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/render/modules/post/themeshock/ThemeShockwaveRenderer$Wave;", "", "<init>", "()V", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "uniforms", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "getUniforms", "()Lcom/mojang/blaze3d/buffers/GpuBuffer;", "setUniforms", "(Lcom/mojang/blaze3d/buffers/GpuBuffer;)V", "", "startNanos", "J", "getStartNanos", "()J", "setStartNanos", "(J)V", "", "centerX", "F", "getCenterX", "()F", "setCenterX", "(F)V", "centerY", "getCenterY", "setCenterY", "rtx.kimiko:kimiko"})
    private static final class Wave {
        @Nullable
        private GpuBuffer uniforms;
        private long startNanos;
        private float centerX;
        private float centerY;

        @Nullable
        public final GpuBuffer getUniforms() {
            return this.uniforms;
        }

        public final void setUniforms(@Nullable GpuBuffer gpuBuffer) {
            this.uniforms = gpuBuffer;
        }

        public final long getStartNanos() {
            return this.startNanos;
        }

        public final void setStartNanos(long l) {
            this.startNanos = l;
        }

        public final float getCenterX() {
            return this.centerX;
        }

        public final void setCenterX(float f) {
            this.centerX = f;
        }

        public final float getCenterY() {
            return this.centerY;
        }

        public final void setCenterY(float f) {
            this.centerY = f;
        }
    }
}

