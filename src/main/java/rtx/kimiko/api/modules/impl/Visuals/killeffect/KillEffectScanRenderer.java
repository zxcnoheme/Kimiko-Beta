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
 *  kotlin.ranges.RangesKt
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.render.Camera
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.lwjgl.system.MemoryUtil
 */
package rtx.kimiko.api.modules.impl.Visuals.killeffect;

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
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.OptionalInt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.client.gl.UniformType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.lwjgl.system.MemoryUtil;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.impl.Visuals.killeffect.KillEffectEasing;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.others.FullscreenQuadBuffer;
import rtx.kimiko.utils.render.others.RenderSampler;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u0086\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\n\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\r\u001a\u00020\fH\u0007b\u0002\b\t\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\bH\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u000f\u0010\u0003Jc\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0012H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u0003J\u009f\u0001\u00105\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u00020\u00122\u0006\u00100\u001a\u00020\u00122\u0006\u00102\u001a\u0002012\u0006\u00104\u001a\u000203H\u0002\u00a2\u0006\u0004\b5\u00106J\u001f\u00108\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u00122\u0006\u00107\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b8\u00109J\u000f\u0010:\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b:\u0010\u000eJ\u000f\u0010;\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b;\u0010\u0003J\u001f\u0010?\u001a\u00020\b2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020#H\u0002\u00a2\u0006\u0004\b?\u0010@J7\u0010E\u001a\u00020\b2\u0006\u0010=\u001a\u00020<2\u0006\u0010A\u001a\u00020\u00062\u0006\u0010B\u001a\u00020\u00062\u0006\u0010C\u001a\u00020\u00062\u0006\u0010D\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bE\u0010FJ\u001f\u0010H\u001a\u00020\b2\u0006\u0010=\u001a\u00020<2\u0006\u0010G\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\bH\u0010IJ\u001f\u0010K\u001a\u00020\u00122\u0006\u0010G\u001a\u00020\u00122\u0006\u0010J\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bK\u0010LJ\u0017\u0010N\u001a\u00020\u00062\u0006\u0010M\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bN\u0010OJ\u001f\u0010Q\u001a\u00020\u00062\u0006\u0010P\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bQ\u0010RJ'\u0010V\u001a\u00020\u00062\u0006\u0010T\u001a\u00020S2\u0006\u0010P\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bV\u0010WJ\u001f\u0010X\u001a\u00020\u00062\u0006\u0010T\u001a\u00020S2\u0006\u0010P\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bX\u0010YJ\u000f\u0010Z\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bZ\u0010[J\u0017\u0010\\\u001a\u00020\u00122\u0006\u0010G\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\\\u0010]J\u0017\u0010^\u001a\u00020\u00122\u0006\u0010G\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b^\u0010]J\u0017\u0010_\u001a\u00020\u00122\u0006\u0010G\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b_\u0010]J\u0017\u0010`\u001a\u00020\u00122\u0006\u0010G\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b`\u0010]R\u0014\u0010a\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\ba\u0010bR\u0014\u0010c\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bc\u0010bR\u0014\u0010d\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bd\u0010eR\u0014\u0010f\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bf\u0010eR\u0014\u0010g\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bg\u0010eR\u0014\u0010i\u001a\u00020h8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bi\u0010jR\u0014\u0010k\u001a\u00020h8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bk\u0010jR\u0014\u0010l\u001a\u00020h8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bl\u0010jR\u0014\u0010n\u001a\u00020m8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bn\u0010oR\u001a\u0010r\u001a\b\u0012\u0004\u0012\u00020q0p8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\br\u0010sR\u0018\u0010t\u001a\u0004\u0018\u0001018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010uR\u0018\u0010v\u001a\u0004\u0018\u0001038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bv\u0010wR\u0018\u0010y\u001a\u0004\u0018\u00010x8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010zR\u0018\u0010{\u001a\u0004\u0018\u00010 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010|R\u0016\u0010}\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b}\u0010bR\u0016\u0010~\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b~\u0010bR\u0017\u0010\u007f\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u007f\u0010\u0080\u0001R\u0017\u0010\u0081\u0001\u001a\u00020#8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0081\u0001\u0010\u0082\u0001R\u0017\u0010\u0083\u0001\u001a\u00020#8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0083\u0001\u0010\u0082\u0001R\u0017\u0010\u0084\u0001\u001a\u00020#8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0084\u0001\u0010\u0082\u0001R\u0017\u0010\u0085\u0001\u001a\u00020#8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0085\u0001\u0010\u0082\u0001\u00a8\u0006\u0087\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectScanRenderer;", "", "<init>", "()V", "Lnet/minecraft/Vec3d;", "position", "", "speedMultiplier", "", "Lkotlin/jvm/JvmStatic;", "ping", "(Lnet/minecraft/Vec3d;F)V", "", "isDisabledAfterError", "()Z", "clear", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "context", "", "baseOuterColor", "baseMidColor", "baseInnerColor", "baseScanlineColor", "flickOuterColor", "flickMidColor", "flickInnerColor", "flickScanlineColor", "render", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;FIIIIIIII)V", "init", "Lcom/mojang/blaze3d/systems/CommandEncoder;", "encoder", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "colorTexture", "depthTexture", "Lorg/joml/Matrix4f;", "inverseProjection", "inverseView", "cameraPos", "center", "radius", "width", "sharpness", "progress", "flick", "outerColor", "midColor", "innerColor", "scanlineColor", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipe", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "uBuf", "renderWave", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;FFFFFIIIILcom/mojang/blaze3d/pipeline/RenderPipeline;Lcom/mojang/blaze3d/buffers/GpuBuffer;)V", "height", "ensureDepthCopyTexture", "(II)Z", "cleanupExpiredWaves", "closeDepthCopyTexture", "Ljava/nio/ByteBuffer;", "buffer", "matrix", "putMatrix", "(Ljava/nio/ByteBuffer;Lorg/joml/Matrix4f;)V", "x", "y", "z", "w", "putVec4", "(Ljava/nio/ByteBuffer;FFFF)V", "color", "putColor", "(Ljava/nio/ByteBuffer;I)V", "factor", "scaleColor", "(IF)I", "value", "wave", "(F)F", "duration", "scaleDuration", "(FF)F", "", "start", "multiplier", "computeFlickRadius", "(JFF)F", "timeProgress", "(JF)F", "viewDistance", "()F", "red", "(I)I", "green", "blue", "alpha", "MAX_ACTIVE_WAVES", "I", "UNIFORM_SIZE", "INITIAL_RADIUS", "F", "BASE_GROWTH_DURATION", "FLICK_DURATION_MULTIPLIER", "Lnet/minecraft/Identifier;", "PIPELINE_ID", "Lnet/minecraft/Identifier;", "VERTEX_SHADER", "FRAGMENT_SHADER", "Lnet/minecraft/MinecraftClient;", "MINECRAFT", "Lnet/minecraft/MinecraftClient;", "Ljava/util/ArrayDeque;", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectScanRenderer$WavePulse;", "activeWaves", "Ljava/util/ArrayDeque;", "pipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "uniformBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "Lcom/mojang/blaze3d/textures/GpuTexture;", "depthCopyTexture", "Lcom/mojang/blaze3d/textures/GpuTexture;", "depthCopyTextureView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "depthCopyWidth", "depthCopyHeight", "disabledAfterErrorVal", "Z", "projectionScratch", "Lorg/joml/Matrix4f;", "viewScratch", "inverseProjectionScratch", "inverseViewScratch", "WavePulse", "rtx.kimiko:kimiko"})
public final class KillEffectScanRenderer {
    @NotNull
    public static final KillEffectScanRenderer INSTANCE = new KillEffectScanRenderer();
    private static final int MAX_ACTIVE_WAVES = 4;
    private static final int UNIFORM_SIZE = 256;
    private static final float INITIAL_RADIUS = 1.0f;
    private static final float BASE_GROWTH_DURATION = 6400.0f;
    private static final float FLICK_DURATION_MULTIPLIER = 0.5f;
    @NotNull
    private static final Identifier PIPELINE_ID;
    @NotNull
    private static final Identifier VERTEX_SHADER;
    @NotNull
    private static final Identifier FRAGMENT_SHADER;
    @NotNull
    private static final MinecraftClient MINECRAFT;
    @NotNull
    private static final ArrayDeque<WavePulse> activeWaves;
    @Nullable
    private static RenderPipeline pipeline;
    @Nullable
    private static GpuBuffer uniformBuffer;
    @Nullable
    private static GpuTexture depthCopyTexture;
    @Nullable
    private static GpuTextureView depthCopyTextureView;
    private static int depthCopyWidth;
    private static int depthCopyHeight;
    private static boolean disabledAfterErrorVal;
    @NotNull
    private static final Matrix4f projectionScratch;
    @NotNull
    private static final Matrix4f viewScratch;
    @NotNull
    private static final Matrix4f inverseProjectionScratch;
    @NotNull
    private static final Matrix4f inverseViewScratch;

    private KillEffectScanRenderer() {
    }

    @JvmStatic
    public static final void ping(@Nullable Vec3d position, float speedMultiplier) {
        if (position == null) {
            return;
        }
        while (activeWaves.size() >= 4) {
            activeWaves.removeFirst();
        }
        activeWaves.addLast(new WavePulse(position, System.currentTimeMillis(), RangesKt.coerceIn((float)speedMultiplier, (float)0.25f, (float)2.0f)));
    }

    @JvmStatic
    public static final boolean isDisabledAfterError() {
        return disabledAfterErrorVal;
    }

    @JvmStatic
    public static final void clear() {
        activeWaves.clear();
        INSTANCE.closeDepthCopyTexture();
    }

    @JvmStatic
    public static final void render(@NotNull WorldRenderEvent context, float speedMultiplier, int baseOuterColor, int baseMidColor, int baseInnerColor, int baseScanlineColor, int flickOuterColor, int flickMidColor, int flickInnerColor, int flickScanlineColor) {
        Intrinsics.checkNotNullParameter((Object)context, (String)"context");
        if (disabledAfterErrorVal || activeWaves.isEmpty()) {
            return;
        }
        if (!INSTANCE.cleanupExpiredWaves()) {
            return;
        }
        if (pipeline == null) {
            INSTANCE.init();
        }
        RenderPipeline pipe = pipeline;
        GpuBuffer uBuf = uniformBuffer;
        if (pipe == null || uBuf == null) {
            return;
        }
        Framebuffer framebuffer2 = MINECRAFT.getFramebuffer();
        if (framebuffer2 == null) {
            return;
        }
        Framebuffer renderTarget = framebuffer2;
        GpuTextureView gpuTextureView = renderTarget.getColorAttachmentView();
        if (gpuTextureView == null) {
            return;
        }
        GpuTextureView colorView = gpuTextureView;
        GpuTexture gpuTexture = renderTarget.getDepthAttachment();
        if (gpuTexture == null) {
            return;
        }
        GpuTexture depthTex = gpuTexture;
        if (!INSTANCE.ensureDepthCopyTexture(renderTarget.textureWidth, renderTarget.textureHeight)) {
            return;
        }
        GpuTextureView gpuTextureView2 = depthCopyTextureView;
        if (gpuTextureView2 == null) {
            return;
        }
        GpuTextureView dView = gpuTextureView2;
        GpuTexture gpuTexture2 = depthCopyTexture;
        if (gpuTexture2 == null) {
            return;
        }
        GpuTexture dTex = gpuTexture2;
        try {
            Matrix4f matrix4f = context.getProjectionMatrix();
            if (matrix4f == null) {
                return;
            }
            Matrix4f projMat = matrix4f;
            Matrix4f matrix4f2 = context.getPositionMatrix();
            if (matrix4f2 == null) {
                return;
            }
            Matrix4f posMat = matrix4f2;
            Camera camera2 = context.getCamera();
            if (camera2 == null) {
                return;
            }
            Camera camera = camera2;
            Matrix4f projection = projectionScratch.set((Matrix4fc)projMat);
            Matrix4f view = viewScratch.set((Matrix4fc)posMat);
            Intrinsics.checkNotNull((Object)projection, (String)"null cannot be cast to non-null type org.joml.Matrix4fc");
            Matrix4f inverseProjection = inverseProjectionScratch.set((Matrix4fc)projection).invert();
            Intrinsics.checkNotNull((Object)view, (String)"null cannot be cast to non-null type org.joml.Matrix4fc");
            Matrix4f inverseView = inverseViewScratch.set((Matrix4fc)view).invert();
            Vec3d vec3d2 = camera.getCameraPos();
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
            Vec3d cameraPos = vec3d2;
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            encoder.copyTextureToTexture(depthTex, dTex, 0, 0, 0, 0, 0, renderTarget.textureWidth, renderTarget.textureHeight);
            Iterator<WavePulse> iterator = activeWaves.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
            Iterator<WavePulse> iterator2 = iterator;
            while (iterator2.hasNext()) {
                WavePulse wavePulse = iterator2.next();
                float growthDuration = INSTANCE.scaleDuration(6400.0f, wavePulse.getSpeedMultiplier());
                float flickRadius = INSTANCE.computeFlickRadius(wavePulse.getStartedAt(), growthDuration, 0.5f);
                float flickProgress = INSTANCE.timeProgress(wavePulse.getStartedAt(), growthDuration * 0.5f);
                float flickAlphaProgress = 1.0f - flickProgress;
                float flickWave = Math.min(INSTANCE.wave(flickAlphaProgress) * 2.0f, 1.0f);
                Intrinsics.checkNotNull((Object)inverseProjection);
                Intrinsics.checkNotNull((Object)inverseView);
                INSTANCE.renderWave(encoder, colorView, dView, inverseProjection, inverseView, cameraPos, wavePulse.getCenter(), flickRadius, flickRadius / 1.5f, 40.0f, flickProgress, 1.0f, INSTANCE.scaleColor(flickOuterColor, flickAlphaProgress), INSTANCE.scaleColor(flickMidColor, flickWave), INSTANCE.scaleColor(flickInnerColor, flickWave), INSTANCE.scaleColor(flickScanlineColor, flickWave), pipe, uBuf);
            }
        }
        catch (Throwable ignored) {
            disabledAfterErrorVal = true;
            INSTANCE.closeDepthCopyTexture();
        }
    }

    private final void init() {
        try {
            pipeline = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(PIPELINE_ID).withVertexShader(VERTEX_SHADER).withFragmentShader(FRAGMENT_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("Uniforms", UniformType.UNIFORM_BUFFER).withSampler("DepthSampler").withBlend(BlendFunction.ADDITIVE).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withDepthWrite(false).build();
            uniformBuffer = RenderSystem.getDevice().createBuffer(KillEffectScanRenderer::init$lambda$0, 136, 256L);
        }
        catch (Throwable ignored) {
            disabledAfterErrorVal = true;
            pipeline = null;
            uniformBuffer = null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void renderWave(CommandEncoder encoder, GpuTextureView colorTexture, GpuTextureView depthTexture, Matrix4f inverseProjection, Matrix4f inverseView, Vec3d cameraPos, Vec3d center, float radius, float width, float sharpness, float progress, float flick, int outerColor, int midColor, int innerColor, int scanlineColor, RenderPipeline pipe, GpuBuffer uBuf) {
        GpuBuffer gpuBuffer = FullscreenQuadBuffer.getOrCreate();
        if (gpuBuffer == null) {
            return;
        }
        GpuBuffer quadBuffer = gpuBuffer;
        ByteBuffer buffer = MemoryUtil.memAlloc((int)256);
        try {
            Intrinsics.checkNotNull((Object)buffer);
            this.putMatrix(buffer, inverseProjection);
            this.putMatrix(buffer, inverseView);
            this.putVec4(buffer, (float)cameraPos.x, (float)cameraPos.y, (float)cameraPos.z, radius);
            this.putVec4(buffer, (float)center.x, (float)center.y, (float)center.z, Math.max(width, 1.0E-4f));
            this.putColor(buffer, outerColor);
            this.putColor(buffer, midColor);
            this.putColor(buffer, innerColor);
            this.putColor(buffer, scanlineColor);
            this.putVec4(buffer, sharpness, progress, flick, 0.0f);
            while (buffer.position() < 256) {
                buffer.put((byte)0);
            }
            buffer.flip();
            encoder.writeToBuffer(uBuf.slice(), buffer);
            AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(KillEffectScanRenderer::renderWave$lambda$0, colorTexture, OptionalInt.empty());
            Throwable throwable = null;
            try {
                RenderPass pass = (RenderPass)autoCloseable;
                boolean bl = false;
                pass.setPipeline(pipe);
                pass.setVertexBuffer(0, quadBuffer);
                pass.setUniform("Uniforms", uBuf);
                pass.bindTexture("DepthSampler", depthTexture, RenderSampler.nearest());
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
        finally {
            MemoryUtil.memFree((Buffer)buffer);
        }
    }

    private final boolean ensureDepthCopyTexture(int width, int height) {
        GpuTexture dTex;
        if (width <= 0 || height <= 0) {
            return false;
        }
        if (depthCopyTexture != null && depthCopyTextureView != null && depthCopyWidth == width && depthCopyHeight == height) {
            return true;
        }
        this.closeDepthCopyTexture();
        GpuDevice gpuDevice = RenderSystem.tryGetDevice();
        if (gpuDevice == null) {
            return false;
        }
        GpuDevice device = gpuDevice;
        GpuTexture gpuTexture = device.createTexture(KillEffectScanRenderer::ensureDepthCopyTexture$lambda$0, 5, TextureFormat.DEPTH32, width, height, 1, 1);
        Intrinsics.checkNotNullExpressionValue((Object)gpuTexture, (String)"createTexture(...)");
        depthCopyTexture = dTex = gpuTexture;
        depthCopyTextureView = device.createTextureView(dTex);
        depthCopyWidth = width;
        depthCopyHeight = height;
        return true;
    }

    private final boolean cleanupExpiredWaves() {
        Iterator<WavePulse> iterator = activeWaves.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<WavePulse> iterator2 = iterator;
        long now = System.currentTimeMillis();
        while (iterator2.hasNext()) {
            WavePulse wavePulse = iterator2.next();
            float growthDuration = this.scaleDuration(6400.0f, wavePulse.getSpeedMultiplier());
            if (!((float)(now - wavePulse.getStartedAt()) >= growthDuration)) continue;
            iterator2.remove();
        }
        return !activeWaves.isEmpty();
    }

    private final void closeDepthCopyTexture() {
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
        depthCopyWidth = -1;
        depthCopyHeight = -1;
    }

    private final void putMatrix(ByteBuffer buffer, Matrix4f matrix) {
        buffer.putFloat(matrix.m00()).putFloat(matrix.m01()).putFloat(matrix.m02()).putFloat(matrix.m03());
        buffer.putFloat(matrix.m10()).putFloat(matrix.m11()).putFloat(matrix.m12()).putFloat(matrix.m13());
        buffer.putFloat(matrix.m20()).putFloat(matrix.m21()).putFloat(matrix.m22()).putFloat(matrix.m23());
        buffer.putFloat(matrix.m30()).putFloat(matrix.m31()).putFloat(matrix.m32()).putFloat(matrix.m33());
    }

    private final void putVec4(ByteBuffer buffer, float x, float y, float z, float w) {
        buffer.putFloat(x).putFloat(y).putFloat(z).putFloat(w);
    }

    private final void putColor(ByteBuffer buffer, int color) {
        buffer.putFloat((float)this.red(color) / 255.0f);
        buffer.putFloat((float)this.green(color) / 255.0f);
        buffer.putFloat((float)this.blue(color) / 255.0f);
        buffer.putFloat((float)this.alpha(color) / 255.0f);
    }

    private final int scaleColor(int color, float factor) {
        float clamped = RangesKt.coerceIn((float)factor, (float)0.0f, (float)1.0f);
        return ColorEngine.rgba(Math.round((float)this.red(color) * clamped), Math.round((float)this.green(color) * clamped), Math.round((float)this.blue(color) * clamped), Math.round(255.0f * clamped));
    }

    private final float wave(float value) {
        float clamped = RangesKt.coerceIn((float)value, (float)0.0f, (float)1.0f);
        return clamped > 0.5f ? (1.0f - clamped) * 2.0f : clamped * 2.0f;
    }

    private final float scaleDuration(float duration, float speedMultiplier) {
        float speed = RangesKt.coerceIn((float)speedMultiplier, (float)0.25f, (float)2.0f);
        return Math.max(280.0f, duration / speed);
    }

    private final float computeFlickRadius(long start, float duration, float multiplier) {
        float progress = KillEffectEasing.expoInOut(this.timeProgress(start, duration * multiplier));
        return 1.0f + (this.viewDistance() - 1.0f) * progress;
    }

    private final float timeProgress(long start, float duration) {
        return RangesKt.coerceIn((float)((float)(System.currentTimeMillis() - start) / duration), (float)0.0f, (float)1.0f);
    }

    private final float viewDistance() {
        return Math.max(96.0f, (float)(((Number)KillEffectScanRenderer.MINECRAFT.options.getViewDistance().getValue()).intValue() + 1) * 16.0f);
    }

    private final int red(int color) {
        return color >>> 16 & 0xFF;
    }

    private final int green(int color) {
        return color >>> 8 & 0xFF;
    }

    private final int blue(int color) {
        return color & 0xFF;
    }

    private final int alpha(int color) {
        return color >>> 24 & 0xFF;
    }

    private static final String init$lambda$0() {
        return "kimiko:frag_effect_scan_uniforms";
    }

    private static final String renderWave$lambda$0() {
        return "kimiko:frag_effect_scan";
    }

    private static final String ensureDepthCopyTexture$lambda$0() {
        return "kimiko:frag_effect_depth_copy";
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"pipeline/effects/frag_effect_scan");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        PIPELINE_ID = identifier2;
        Identifier identifier3 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"effects/frag_effect_scan/frag_effect_scan");
        Intrinsics.checkNotNullExpressionValue((Object)identifier3, (String)"fromNamespaceAndPath(...)");
        VERTEX_SHADER = identifier3;
        Identifier identifier4 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"effects/frag_effect_scan/frag_effect_scan");
        Intrinsics.checkNotNullExpressionValue((Object)identifier4, (String)"fromNamespaceAndPath(...)");
        FRAGMENT_SHADER = identifier4;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MINECRAFT = minecraftClient2;
        activeWaves = new ArrayDeque();
        depthCopyWidth = -1;
        depthCopyHeight = -1;
        projectionScratch = new Matrix4f();
        viewScratch = new Matrix4f();
        inverseProjectionScratch = new Matrix4f();
        inverseViewScratch = new Matrix4f();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ.\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0017\u001a\u00020\u0016H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u001a\u001a\u00020\u0019H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010\rR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010 \u001a\u0004\b!\u0010\u000f\u00a8\u0006\""}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectScanRenderer$WavePulse;", "", "Lnet/minecraft/Vec3d;", "center", "", "startedAt", "", "speedMultiplier", "<init>", "(Lnet/minecraft/Vec3d;JF)V", "component1", "()Lnet/minecraft/Vec3d;", "component2", "()J", "component3", "()F", "copy", "(Lnet/minecraft/Vec3d;JF)Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectScanRenderer$WavePulse;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/Vec3d;", "getCenter", "J", "getStartedAt", "F", "getSpeedMultiplier", "rtx.kimiko:kimiko"})
    private static final class WavePulse {
        @NotNull
        private final Vec3d center;
        private final long startedAt;
        private final float speedMultiplier;

        public WavePulse(@NotNull Vec3d center, long startedAt, float speedMultiplier) {
            Intrinsics.checkNotNullParameter((Object)center, (String)"center");
            this.center = center;
            this.startedAt = startedAt;
            this.speedMultiplier = speedMultiplier;
        }

        @NotNull
        public final Vec3d getCenter() {
            return this.center;
        }

        public final long getStartedAt() {
            return this.startedAt;
        }

        public final float getSpeedMultiplier() {
            return this.speedMultiplier;
        }

        @NotNull
        public final Vec3d component1() {
            return this.center;
        }

        public final long component2() {
            return this.startedAt;
        }

        public final float component3() {
            return this.speedMultiplier;
        }

        @NotNull
        public final WavePulse copy(@NotNull Vec3d center, long startedAt, float speedMultiplier) {
            Intrinsics.checkNotNullParameter((Object)center, (String)"center");
            return new WavePulse(center, startedAt, speedMultiplier);
        }

        public static /* synthetic */ WavePulse copy$default(WavePulse wavePulse, Vec3d vec3d2, long l, float f, int n, Object object) {
            if ((n & 1) != 0) {
                vec3d2 = wavePulse.center;
            }
            if ((n & 2) != 0) {
                l = wavePulse.startedAt;
            }
            if ((n & 4) != 0) {
                f = wavePulse.speedMultiplier;
            }
            return wavePulse.copy(vec3d2, l, f);
        }

        @NotNull
        public String toString() {
            return "WavePulse(center=" + this.center + ", startedAt=" + this.startedAt + ", speedMultiplier=" + this.speedMultiplier + ")";
        }

        public int hashCode() {
            int result = this.center.hashCode();
            result = result * 31 + Long.hashCode(this.startedAt);
            result = result * 31 + Float.hashCode(this.speedMultiplier);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WavePulse)) {
                return false;
            }
            WavePulse wavePulse = (WavePulse)other;
            if (!Intrinsics.areEqual((Object)this.center, (Object)wavePulse.center)) {
                return false;
            }
            if (this.startedAt != wavePulse.startedAt) {
                return false;
            }
            return Float.compare(this.speedMultiplier, wavePulse.speedMultiplier) == 0;
        }
    }
}

