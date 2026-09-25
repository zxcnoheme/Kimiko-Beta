/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.buffers.GpuBuffer$MappedView
 *  com.mojang.blaze3d.buffers.GpuBufferSlice
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Snippet
 *  com.mojang.blaze3d.platform.DepthTestFunction
 *  com.mojang.blaze3d.systems.CommandEncoder
 *  com.mojang.blaze3d.systems.RenderPass
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.GpuTexture
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.textures.TextureFormat
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.io.CloseableKt
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  com.mojang.blaze3d.systems.ProjectionType
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.client.gl.GlobalSettings
 *  net.minecraft.client.render.RawProjectionMatrix
 *  net.minecraft.client.render.state.CameraRenderState
 *  net.minecraft.client.option.TextureFilteringMode
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 *  net.minecraft.util.hit.HitResult.Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.world.RaycastContext
 *  net.minecraft.world.RaycastContext.FluidHandling
 *  net.minecraft.world.RaycastContext.ShapeType
 *  net.minecraft.util.hit.BlockHitResult
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.gl.SimpleFramebuffer
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  net.minecraft.client.render.GameRenderer
 *  net.minecraft.client.render.RenderTickCounter
 *  net.minecraft.client.util.memory.ObjectAllocator
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 *  org.joml.Vector4f
 */
package rtx.kimiko.api.modules.impl.Visuals.portallive;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.OptionalInt;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import mixin.portallive.PortalCameraAccessor;
import com.mojang.blaze3d.systems.ProjectionType;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gl.GlobalSettings;
import net.minecraft.client.render.RawProjectionMatrix;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.option.TextureFilteringMode;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.RaycastContext;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.client.render.Camera;
import net.minecraft.client.gl.SimpleFramebuffer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.memory.ObjectAllocator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector4f;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.modules.impl.Visuals.portallive.PortalLiveView;
import rtx.kimiko.utils.render.others.RenderSampler;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00d4\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u0087\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J#\u0010\t\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJC\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u0013\u0010\u0014J+\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u0017\u0010\u0003J\u0013\u0010\u0019\u001a\u00020\u0018H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u0019\u0010\u001aJ#\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001dH\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u001f\u0010 J/\u0010%\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020#H\u0002\u00a2\u0006\u0004\b%\u0010&J\u0013\u0010'\u001a\u00020\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b'\u0010\u0003J'\u0010*\u001a\u00020\r2\u0006\u0010\"\u001a\u00020!2\u0006\u0010(\u001a\u00020\r2\u0006\u0010)\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b*\u0010+J+\u0010.\u001a\u00020-2\u0006\u0010\"\u001a\u00020!2\u0006\u0010(\u001a\u00020\r2\u0006\u0010,\u001a\u00020\rH\u0007b\u0002\b\b\u00a2\u0006\u0004\b.\u0010/J/\u00102\u001a\u00020-2\u0006\u0010\"\u001a\u00020!2\u0006\u0010(\u001a\u00020\r2\u0006\u0010,\u001a\u00020\r2\u0006\u00101\u001a\u000200H\u0002\u00a2\u0006\u0004\b2\u00103JE\u00106\u001a\u00020\u00072\u0006\u00104\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002\u00a2\u0006\u0004\b6\u00107J\u000f\u00109\u001a\u000208H\u0002\u00a2\u0006\u0004\b9\u0010:J9\u0010;\u001a\u0004\u0018\u00010\u00112\u0006\u00104\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b;\u0010<J\u000f\u0010=\u001a\u00020-H\u0002\u00a2\u0006\u0004\b=\u0010>J\u001f\u0010?\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b?\u0010\nJ\u000f\u0010@\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b@\u0010\u0003R\u0019\u0010C\u001a\u00020A8\u0006X\u0087\u0004\u0092\u0002\u0002\bB\u00a2\u0006\u0006\n\u0004\bC\u0010DR\u0016\u0010E\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bE\u0010FR\u0016\u0010G\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bG\u0010FR\u0014\u0010H\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010DR\u0014\u0010I\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010DR\u0018\u0010J\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bJ\u0010KR\u0018\u0010M\u001a\u0004\u0018\u00010L8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010NR\u0018\u0010P\u001a\u0004\u0018\u00010O8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010QR\u0018\u0010R\u001a\u0004\u0018\u00010O8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bR\u0010QR\u0018\u0010T\u001a\u0004\u0018\u00010S8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010UR\u0018\u0010W\u001a\u0004\u0018\u00010V8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bW\u0010XR\u0018\u0010Y\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010ZR\u0018\u0010\\\u001a\u0004\u0018\u00010[8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\\\u0010]R\u0016\u0010^\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010FR\u0016\u0010_\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010FR\u0018\u0010a\u001a\u0004\u0018\u00010`8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010bR\u0016\u0010c\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010dR\u0016\u0010e\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010dR\u0016\u0010f\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bf\u0010dR\u0016\u0010g\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bg\u0010FR\u0016\u0010h\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bh\u0010FR\u0016\u0010i\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bi\u0010jR\u0016\u0010k\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bk\u0010jR\u001e\u0010l\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bl\u0010mR\u0016\u0010n\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bn\u0010oR\u0014\u0010q\u001a\u00020p8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bq\u0010rR\u0014\u0010s\u001a\u00020p8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bs\u0010rR\u0018\u0010t\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010uR\u0016\u0010v\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bv\u0010oR\u0018\u0010x\u001a\u0004\u0018\u00010w8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bx\u0010yR\u0018\u0010z\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bz\u0010KR\u0018\u0010|\u001a\u0004\u0018\u00010{8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b|\u0010}R\u0016\u0010~\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b~\u0010dR\u001d\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u0002000\u007f8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001R\u0017\u0010\u0082\u0001\u001a\u0002008\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0018\u0010\u0085\u0001\u001a\u00030\u0084\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0085\u0001\u0010\u0086\u0001\u00a8\u0006\u0088\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveCapture;", "", "<init>", "()V", "", "width", "height", "", "Lkotlin/jvm/JvmStatic;", "setResolution", "(II)V", "fps", "quality", "Lnet/minecraft/Vec3d;", "cameraPos", "lookAt", "Ljava/util/function/Consumer;", "", "onEncoded", "request", "(IILnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Ljava/util/function/Consumer;)V", "requestMirror", "(ILnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;)V", "clearRequest", "", "mirrorFrameAtMs", "()J", "Lnet/minecraft/GameRenderer;", "gameRenderer", "Lnet/minecraft/RenderTickCounter;", "deltaTracker", "renderPortalPass", "(Lnet/minecraft/GameRenderer;Lnet/minecraft/RenderTickCounter;)V", "Lnet/minecraft/MinecraftClient;", "mc", "Lnet/minecraft/Camera;", "camera", "applyGlobals", "(Lnet/minecraft/MinecraftClient;Lnet/minecraft/GameRenderer;Lnet/minecraft/RenderTickCounter;Lnet/minecraft/Camera;)V", "shutdown", "focus", "lens", "clampLens", "(Lnet/minecraft/MinecraftClient;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;)Lnet/minecraft/Vec3d;", "cam", "", "fullBodyVisible", "(Lnet/minecraft/MinecraftClient;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;)Z", "", "offsets", "pointsVisible", "(Lnet/minecraft/MinecraftClient;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;[D)Z", "rgba", "px", "submitEncode", "([BIIIILjava/util/function/Consumer;)V", "Ljava/util/concurrent/ExecutorService;", "ensureEncoder", "()Ljava/util/concurrent/ExecutorService;", "encodeJpeg", "([BIIII)[B", "ensurePipeline", "()Z", "ensureResources", "closeResources", "Lnet/minecraft/Identifier;", "Lkotlin/jvm/JvmField;", "MIRROR_TEXTURE_ID", "Lnet/minecraft/Identifier;", "streamW", "I", "streamH", "DOWNSCALE_PIPELINE_ID", "COPY_SHADER", "readback", "[B", "Lnet/minecraft/SimpleFramebuffer;", "downTarget", "Lnet/minecraft/SimpleFramebuffer;", "Lcom/mojang/blaze3d/textures/GpuTexture;", "backupTexture", "Lcom/mojang/blaze3d/textures/GpuTexture;", "mirrorTexture", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "mirrorView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "downscalePipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "portalCamera", "Lnet/minecraft/Camera;", "Lnet/minecraft/RawProjectionMatrix;", "projectionBuffer", "Lnet/minecraft/RawProjectionMatrix;", "mainW", "mainH", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "readBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "disabledAfterError", "Z", "requested", "requestedMirror", "requestedFps", "requestedQuality", "requestedCamera", "Lnet/minecraft/Vec3d;", "requestedLook", "requestedConsumer", "Ljava/util/function/Consumer;", "mirrorFrameAtMsVal", "J", "Ljava/util/concurrent/atomic/AtomicBoolean;", "readInFlight", "Ljava/util/concurrent/atomic/AtomicBoolean;", "encoderBusy", "encoder", "Ljava/util/concurrent/ExecutorService;", "lastCaptureAtMs", "Ljava/awt/image/BufferedImage;", "encodeImage", "Ljava/awt/image/BufferedImage;", "encodeBgr", "Ljavax/imageio/ImageWriter;", "jpegWriter", "Ljavax/imageio/ImageWriter;", "resolutionDirty", "", "BODY_TIERS", "[[D", "TIER_MIN_FRACTION", "[D", "", "FOOT_CLEARANCE", "D", "TargetTexture", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nPortalLiveCapture.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PortalLiveCapture.kt\nrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveCapture\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,577:1\n1#2:578\n*E\n"})
public final class PortalLiveCapture {
    @NotNull
    public static final PortalLiveCapture INSTANCE = new PortalLiveCapture();
    @JvmField
    @NotNull
    public static final Identifier MIRROR_TEXTURE_ID;
    private static volatile int streamW;
    private static volatile int streamH;
    @NotNull
    private static final Identifier DOWNSCALE_PIPELINE_ID;
    @NotNull
    private static final Identifier COPY_SHADER;
    @Nullable
    private static byte[] readback;
    @Nullable
    private static SimpleFramebuffer downTarget;
    @Nullable
    private static GpuTexture backupTexture;
    @Nullable
    private static GpuTexture mirrorTexture;
    @Nullable
    private static GpuTextureView mirrorView;
    @Nullable
    private static RenderPipeline downscalePipeline;
    @Nullable
    private static Camera portalCamera;
    @Nullable
    private static RawProjectionMatrix projectionBuffer;
    private static int mainW;
    private static int mainH;
    @Nullable
    private static GpuBuffer readBuffer;
    private static boolean disabledAfterError;
    private static volatile boolean requested;
    private static volatile boolean requestedMirror;
    private static volatile int requestedFps;
    private static volatile int requestedQuality;
    @NotNull
    private static volatile Vec3d requestedCamera;
    @NotNull
    private static volatile Vec3d requestedLook;
    @Nullable
    private static volatile Consumer<byte[]> requestedConsumer;
    private static volatile long mirrorFrameAtMsVal;
    @NotNull
    private static final AtomicBoolean readInFlight;
    @NotNull
    private static final AtomicBoolean encoderBusy;
    @Nullable
    private static volatile ExecutorService encoder;
    private static long lastCaptureAtMs;
    @Nullable
    private static BufferedImage encodeImage;
    @Nullable
    private static byte[] encodeBgr;
    @Nullable
    private static ImageWriter jpegWriter;
    private static boolean resolutionDirty;
    @NotNull
    private static final double[][] BODY_TIERS;
    @NotNull
    private static final double[] TIER_MIN_FRACTION;
    private static final double FOOT_CLEARANCE = 0.15;

    private PortalLiveCapture() {
    }

    @JvmStatic
    public static final void setResolution(int width, int height) {
        if (width == streamW && height == streamH) {
            return;
        }
        streamW = width;
        streamH = height;
        resolutionDirty = true;
    }

    @JvmStatic
    public static final void request(int n, int n2, @NotNull Vec3d vec3d2, @NotNull Vec3d vec3d3, @Nullable Consumer<byte[]> consumer) {
    }

    @JvmStatic
    public static final void requestMirror(int n, @NotNull Vec3d vec3d2, @NotNull Vec3d vec3d3) {
    }

    @JvmStatic
    public static final void clearRequest() {
        requested = false;
        requestedConsumer = null;
        requestedMirror = false;
    }

    @JvmStatic
    public static final long mirrorFrameAtMs() {
        return mirrorFrameAtMsVal;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void renderPortalPass(@NotNull GameRenderer gameRenderer, @NotNull RenderTickCounter deltaTracker) {
        long interval;
        Intrinsics.checkNotNullParameter((Object)gameRenderer, (String)"gameRenderer");
        Intrinsics.checkNotNullParameter((Object)deltaTracker, (String)"deltaTracker");
        if (!requested || disabledAfterError || PortalLiveView.rendering()) {
            return;
        }
        boolean mirror = requestedMirror;
        Consumer<byte[]> onEncoded = requestedConsumer;
        if (!mirror && onEncoded == null) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - lastCaptureAtMs < (interval = 1000L / (long)Math.max(1, requestedFps))) {
            return;
        }
        if (!(mirror || !encoderBusy.get() && readInFlight.compareAndSet(false, true))) {
            return;
        }
        lastCaptureAtMs = now;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        ClientWorld level = mc.world;
        ClientPlayerEntity player = mc.player;
        Framebuffer main = mc.getFramebuffer();
        ObjectAllocator allocator = PortalLiveView.frameAllocator();
        GpuBufferSlice fog = PortalLiveView.frameFog();
        Vector4f fogColor = PortalLiveView.frameFogColor();
        if (level == null || player == null || main == null || main.getColorAttachment() == null || main.textureWidth <= 0 || main.textureHeight <= 0 || allocator == null || fog == null || fogColor == null || !INSTANCE.ensurePipeline()) {
            if (!mirror) {
                readInFlight.set(false);
            }
            return;
        }
        try {
            byte[] rBack;
            Vec3d vec3d2;
            INSTANCE.ensureResources(main.textureWidth, main.textureHeight);
            float partial = deltaTracker.getTickProgress(true);
            Camera pCam = portalCamera;
            if (pCam == null) {
                portalCamera = pCam = new Camera();
            }
            if ((vec3d2 = requestedCamera) == null) {
                Vec3d vec3d3 = Vec3d.ZERO;
                vec3d2 = vec3d3;
                Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"ZERO");
            }
            Vec3d reqCam = vec3d2;
            Vec3d vec3d4 = requestedLook;
            if (vec3d4 == null) {
                Vec3d vec3d5 = Vec3d.ZERO;
                vec3d4 = vec3d5;
                Intrinsics.checkNotNullExpressionValue((Object)vec3d5, (String)"ZERO");
            }
            Vec3d reqLook = vec3d4;
            Vec3d camPos = INSTANCE.clampLens(mc, reqLook, reqCam);
            Vec3d vec3d6 = reqLook.subtract(camPos);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d6, (String)"subtract(...)");
            Vec3d delta = vec3d6;
            double horizontal = Math.sqrt(delta.x * delta.x + delta.z * delta.z);
            float aimYaw = (float)Math.toDegrees(Math.atan2(-delta.x, delta.z));
            float aimPitch = (float)(-Math.toDegrees(Math.atan2(delta.y, Math.max(0.001, horizontal))));
            pCam.update((World)level, (Entity)player, true, false, partial);
            PortalCameraAccessor access = (PortalCameraAccessor)pCam;
            access.kimiko$invokeSetRotation(aimYaw, aimPitch);
            access.kimiko$invokeSetPosition(camPos);
            float fovDeg = PortalLiveView.fovOverride();
            Matrix4f projection = new Matrix4f().perspective(fovDeg * ((float)Math.PI / 180), 1.7777778f, 0.05f, gameRenderer.getFarPlaneDistance());
            Matrix4f viewMatrix = new Matrix4f().rotation((Quaternionfc)pCam.getRotation().conjugate(new Quaternionf()));
            CameraRenderState cameraRenderState2 = gameRenderer.getEntityRenderStates().cameraRenderState;
            Intrinsics.checkNotNullExpressionValue((Object)cameraRenderState2, (String)"cameraRenderState");
            CameraRenderState cameraState = cameraRenderState2;
            cameraState.initialized = true;
            cameraState.pos = pCam.getCameraPos();
            cameraState.blockPos = pCam.getBlockPos();
            cameraState.entityPos = player.getLerpedPos(partial);
            cameraState.orientation = new Quaternionf((Quaternionfc)pCam.getRotation());
            RawProjectionMatrix pBuf = projectionBuffer;
            if (pBuf == null) {
                projectionBuffer = pBuf = new RawProjectionMatrix("kimiko portal");
            }
            GpuTexture bTex = backupTexture;
            GpuTexture colorTex = main.getColorAttachment();
            if (bTex != null && colorTex != null) {
                RenderSystem.getDevice().createCommandEncoder().copyTextureToTexture(colorTex, bTex, 0, 0, 0, 0, 0, main.textureWidth, main.textureHeight);
            }
            GpuBufferSlice savedFog = RenderSystem.getShaderFog();
            GpuBufferSlice savedProjection = RenderSystem.getProjectionMatrixBuffer();
            ProjectionType projectionType2 = RenderSystem.getProjectionType();
            Intrinsics.checkNotNullExpressionValue((Object)projectionType2, (String)"getProjectionType(...)");
            ProjectionType savedProjectionType = projectionType2;
            RenderSystem.setProjectionMatrix((GpuBufferSlice)pBuf.set(projection), (ProjectionType)ProjectionType.PERSPECTIVE);
            PortalLiveView.recordActualFov(fovDeg);
            PortalLiveView.begin(camPos, aimYaw, aimPitch);
            INSTANCE.applyGlobals(mc, gameRenderer, deltaTracker, pCam);
            try {
                mc.worldRenderer.render(allocator, deltaTracker, false, pCam, viewMatrix, projection, projection, fog, fogColor, true);
            }
            finally {
                GpuBufferSlice it;
                Camera camera2 = gameRenderer.getCamera();
                Intrinsics.checkNotNullExpressionValue((Object)camera2, (String)"getMainCamera(...)");
                INSTANCE.applyGlobals(mc, gameRenderer, deltaTracker, camera2);
                GpuBufferSlice gpuBufferSlice = savedFog;
                if (gpuBufferSlice != null) {
                    it = gpuBufferSlice;
                    boolean bl = false;
                    RenderSystem.setShaderFog((GpuBufferSlice)it);
                }
                GpuBufferSlice gpuBufferSlice2 = savedProjection;
                if (gpuBufferSlice2 != null) {
                    it = gpuBufferSlice2;
                    boolean bl = false;
                    RenderSystem.setProjectionMatrix((GpuBufferSlice)it, (ProjectionType)savedProjectionType);
                }
                PortalLiveView.end();
            }
            SimpleFramebuffer targetTex = downTarget;
            RenderPipeline pipe = downscalePipeline;
            if (mirror) {
                GpuTexture mTex = mirrorTexture;
                if (mTex != null && colorTex != null) {
                    RenderSystem.getDevice().createCommandEncoder().copyTextureToTexture(colorTex, mTex, 0, 0, 0, 0, 0, main.textureWidth, main.textureHeight);
                }
            } else if (targetTex != null && pipe != null && colorTex != null) {
                CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
                Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
                CommandEncoder downEncoder = commandEncoder;
                GpuTextureView gpuTextureView = targetTex.getColorAttachmentView();
                if (gpuTextureView == null) {
                    return;
                }
                GpuTextureView targetView = gpuTextureView;
                AutoCloseable autoCloseable = (AutoCloseable)downEncoder.createRenderPass(PortalLiveCapture::renderPortalPass$lambda$2, targetView, OptionalInt.empty());
                Throwable throwable = null;
                try {
                    RenderPass pass = (RenderPass)autoCloseable;
                    boolean bl = false;
                    pass.setPipeline(pipe);
                    pass.bindTexture("Sampler0", main.getColorAttachmentView(), RenderSampler.linear());
                    pass.draw(0, 6);
// pass = Unit.INSTANCE;
                }
                catch (Throwable bl) {
                    throwable = bl;
                    throw bl;
                }
                finally {
                    AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
                }
            }
            if (bTex != null && colorTex != null) {
                RenderSystem.getDevice().createCommandEncoder().copyTextureToTexture(bTex, colorTex, 0, 0, 0, 0, 0, main.textureWidth, main.textureHeight);
            }
            if (mirror) {
                mirrorFrameAtMsVal = now;
                return;
            }
            if (targetTex == null) {
                return;
            }
            GpuTexture downTex = targetTex.getColorAttachment();
            if (downTex == null) {
                return;
            }
            int px = downTex.getFormat().pixelSize();
            int width = streamW;
            int height = streamH;
            int size = width * height * px;
            GpuBuffer rBuf = readBuffer;
            if (rBuf == null || rBuf.isClosed()) {
                readBuffer = rBuf = RenderSystem.getDevice().createBuffer(PortalLiveCapture::renderPortalPass$lambda$4, 9, (long)size);
            }
            if ((rBack = readback) == null || rBack.length != size) {
                readback = rBack = new byte[size];
            }
            byte[] capturedRBack = rBack;
            GpuBuffer gpuBuffer = rBuf;
            if (gpuBuffer == null) {
                return;
            }
            GpuBuffer safeRBuf = gpuBuffer;
            RenderSystem.getDevice().createCommandEncoder().copyTextureToBuffer(downTex, safeRBuf, 0L, () -> PortalLiveCapture.renderPortalPass$lambda$5(safeRBuf, onEncoded, capturedRBack, px, width, height, size), 0);
        }
        catch (Throwable throwable) {
            disabledAfterError = true;
            readInFlight.set(false);
            INSTANCE.closeResources();
        }
    }

    private final void applyGlobals(MinecraftClient mc, GameRenderer gameRenderer, RenderTickCounter deltaTracker, Camera camera) {
        long l;
        GlobalSettings globalSettings2 = gameRenderer.getGlobalSettings();
        int n = mc.getWindow().getFramebufferWidth();
        int n2 = mc.getWindow().getFramebufferHeight();
        Object object = mc.options.getGlintStrength().getValue();
        Intrinsics.checkNotNullExpressionValue((Object)object, (String)"get(...)");
        double d = ((Number)object).doubleValue();
        if (mc.world == null) {
            l = 0L;
        } else {
            ClientWorld clientWorld3 = mc.world;
            Intrinsics.checkNotNull((Object)clientWorld3);
            l = clientWorld3.getTime();
        }
        globalSettings2.set(n, n2, d, l, deltaTracker, mc.options.getMenuBackgroundBlurrinessValue(), camera, mc.options.getTextureFiltering().getValue() == TextureFilteringMode.RGSS);
    }

    @JvmStatic
    public static final void shutdown() {
        ExecutorService current = encoder;
        encoder = null;
        ExecutorService executorService = current;
        if (executorService != null) {
            executorService.shutdownNow();
        }
        encoderBusy.set(false);
    }

    private final Vec3d clampLens(MinecraftClient mc, Vec3d focus, Vec3d lens) {
        Vec3d vec3d2 = lens.subtract(focus);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"subtract(...)");
        Vec3d toLens = vec3d2;
        double length = toLens.length();
        if (length < 0.7) {
            return lens;
        }
        Vec3d vec3d3 = toLens.multiply(1.0 / length);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"scale(...)");
        Vec3d dir = vec3d3;
        double step = Math.max(0.25, length / 8.0);
        int n = ((Object[])BODY_TIERS).length;
        for (int tier = 0; tier < n; ++tier) {
            double minT = Math.max(0.7, length * TIER_MIN_FRACTION[tier]);
            for (double t = length; t >= minT; t -= step) {
                Vec3d cam = (Vec3d) (focus.add(dir.multiply(t)));
                if (!this.pointsVisible(mc, focus, cam, BODY_TIERS[tier])) continue;
                return cam;
            }
        }
        Vec3d vec3d4 = focus.add(dir.multiply(0.7));
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"add(...)");
        return vec3d4;
    }

    @JvmStatic
    public static final boolean fullBodyVisible(@NotNull MinecraftClient mc, @NotNull Vec3d focus, @NotNull Vec3d cam) {
        Intrinsics.checkNotNullParameter((Object)mc, (String)"mc");
        Intrinsics.checkNotNullParameter((Object)focus, (String)"focus");
        Intrinsics.checkNotNullParameter((Object)cam, (String)"cam");
        return INSTANCE.pointsVisible(mc, focus, cam, BODY_TIERS[0]);
    }

    private final boolean pointsVisible(MinecraftClient mc, Vec3d focus, Vec3d cam, double[] offsets) {
        ClientPlayerEntity clientPlayerEntity2 = mc.player;
        if (clientPlayerEntity2 == null) {
            return true;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        ClientWorld clientWorld3 = mc.world;
        if (clientWorld3 == null) {
            return true;
        }
        ClientWorld level = clientWorld3;
        double minY = player.getY() + 0.15;
        for (double dy : offsets) {
            Vec3d point = new Vec3d(focus.x, Math.max(focus.y + dy, minY), focus.z);
            BlockHitResult hit = level.raycast(new RaycastContext(point, cam, RaycastContext.ShapeType.VISUAL, RaycastContext.FluidHandling.NONE, (Entity)player));
            if (hit.getType() == HitResult.Type.MISS) continue;
            return false;
        }
        return true;
    }

    private final void submitEncode(byte[] rgba, int px, int width, int height, int quality, Consumer<byte[]> onEncoded) {
        if (!encoderBusy.compareAndSet(false, true)) {
            return;
        }
        ExecutorService service = this.ensureEncoder();
        try {
            service.execute(() -> PortalLiveCapture.submitEncode$lambda$0(rgba, px, width, height, quality, onEncoded));
        }
        catch (Throwable throwable) {
            encoderBusy.set(false);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final ExecutorService ensureEncoder() {
        ExecutorService current = null;
        current = encoder;
        if (current == null || current.isShutdown()) {
            Class<PortalLiveCapture> clazz = PortalLiveCapture.class;
            synchronized (clazz) {
                boolean bl = false;
                current = encoder;
                if (current == null || current.isShutdown()) {
                    encoder = current = Executors.newSingleThreadExecutor(PortalLiveCapture::ensureEncoder$lambda$0$0);
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        ExecutorService executorService = current;
        Intrinsics.checkNotNull((Object)executorService);
        return executorService;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final byte[] encodeJpeg(byte[] rgba, int px, int width, int height, int quality) {
        BufferedImage img = null;
        img = encodeImage;
        byte[] bgr = encodeBgr;
        if (img == null || img.getWidth() != width || img.getHeight() != height) {
            encodeImage = img = new BufferedImage(width, height, 5);
            DataBuffer dataBuffer = img.getRaster().getDataBuffer();
            Intrinsics.checkNotNull((Object)dataBuffer, (String)"null cannot be cast to non-null type java.awt.image.DataBufferByte");
            encodeBgr = bgr = ((DataBufferByte)dataBuffer).getData();
        }
        if (bgr == null) {
            return null;
        }
        byte[] target = bgr;
        for (int y = 0; y < height; ++y) {
            int src = (height - 1 - y) * width * px;
            int dst = y * width * 3;
            for (int x = 0; x < width; ++x) {
                target[dst] = rgba[src + 2];
                target[dst + 1] = rgba[src + 1];
                target[dst + 2] = rgba[src];
                src += px;
                dst += 3;
            }
        }
        if (jpegWriter == null) {
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
            if (!writers.hasNext()) {
                return null;
            }
            jpegWriter = writers.next();
        }
        ImageWriter imageWriter = jpegWriter;
        if (imageWriter == null) {
            return null;
        }
        ImageWriter writer = imageWriter;
        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(2);
        param.setCompressionQuality(Math.max(0.1f, Math.min(0.95f, (float)quality / 100.0f)));
        ByteArrayOutputStream output = new ByteArrayOutputStream(32768);
        try (MemoryCacheImageOutputStream imageOutput = new MemoryCacheImageOutputStream(output)) {
            writer.setOutput(imageOutput);
            writer.write(null, new IIOImage(img, null, null), param);
        } catch (IOException e) {
            return null;
        }
        return output.toByteArray();
    }

    private final boolean ensurePipeline() {
        boolean bl;
        if (downscalePipeline != null) {
            return true;
        }
        try {
            downscalePipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(DOWNSCALE_PIPELINE_ID).withVertexShader(COPY_SHADER).withFragmentShader(COPY_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withSampler("Sampler0").withoutBlend().withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            bl = true;
        }
        catch (Throwable throwable) {
            disabledAfterError = true;
            downscalePipeline = null;
            bl = false;
        }
        return bl;
    }

    private final void ensureResources(int width, int height) {
        GpuTextureView mView;
        GpuTexture mTex;
        GpuTexture bTex;
        if (resolutionDirty || width != mainW || height != mainH) {
            resolutionDirty = false;
            this.closeResources();
        }
        if (downTarget != null) {
            return;
        }
        downTarget = new SimpleFramebuffer("kimiko:portallive_down", streamW, streamH, false);
        int usage = 7;
        GpuTexture gpuTexture = RenderSystem.getDevice().createTexture(PortalLiveCapture::ensureResources$lambda$0, usage, TextureFormat.RGBA8, width, height, 1, 1);
        Intrinsics.checkNotNullExpressionValue((Object)gpuTexture, (String)"createTexture(...)");
        backupTexture = bTex = gpuTexture;
        GpuTexture gpuTexture2 = RenderSystem.getDevice().createTexture(PortalLiveCapture::ensureResources$lambda$1, usage, TextureFormat.RGBA8, width, height, 1, 1);
        Intrinsics.checkNotNullExpressionValue((Object)gpuTexture2, (String)"createTexture(...)");
        mirrorTexture = mTex = gpuTexture2;
        GpuTextureView gpuTextureView = RenderSystem.getDevice().createTextureView(mTex);
        Intrinsics.checkNotNullExpressionValue((Object)gpuTextureView, (String)"createTextureView(...)");
        mirrorView = mView = gpuTextureView;
        mainW = width;
        mainH = height;
        MinecraftClient.getInstance().getTextureManager().registerTexture(MIRROR_TEXTURE_ID, (AbstractTexture)new TargetTexture(mTex, mView));
    }

    private final void closeResources() {
        SimpleFramebuffer simpleFramebuffer2 = downTarget;
        if (simpleFramebuffer2 != null) {
            simpleFramebuffer2.delete();
        }
        downTarget = null;
        GpuTextureView gpuTextureView = mirrorView;
        if (gpuTextureView != null) {
            gpuTextureView.close();
        }
        mirrorView = null;
        GpuTexture gpuTexture = mirrorTexture;
        if (gpuTexture != null) {
            gpuTexture.close();
        }
        mirrorTexture = null;
        GpuTexture gpuTexture2 = backupTexture;
        if (gpuTexture2 != null) {
            gpuTexture2.close();
        }
        backupTexture = null;
        GpuBuffer gpuBuffer = readBuffer;
        if (gpuBuffer != null) {
            GpuBuffer it = gpuBuffer;
            boolean bl = false;
            if (!it.isClosed()) {
                it.close();
            }
        }
        readBuffer = null;
        mainW = -1;
        mainH = -1;
    }

    private static final String renderPortalPass$lambda$2() {
        return "kimiko:portallive_downscale";
    }

    private static final String renderPortalPass$lambda$4() {
        return "kimiko:portallive_read";
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static final void renderPortalPass$lambda$5(GpuBuffer $safeRBuf, Consumer $onEncoded, byte[] $capturedRBack, int $px, int $width, int $height, int $size) {
        boolean ok = false;
        try {
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder mapEncoder = commandEncoder;
            AutoCloseable autoCloseable = (AutoCloseable)mapEncoder.mapBuffer($safeRBuf, true, false);
            Throwable throwable = null;
            try {
                GpuBuffer.MappedView view = (GpuBuffer.MappedView)autoCloseable;
                boolean bl = false;
                ByteBuffer byteBuffer = view.data();
                Intrinsics.checkNotNullExpressionValue((Object)byteBuffer, (String)"data(...)");
                ByteBuffer data = byteBuffer;
                data.get(0, $capturedRBack, 0, $size);
                ok = true;
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
        }
        finally {
            readInFlight.set(false);
        }
        if (ok && $onEncoded != null) {
            INSTANCE.submitEncode($capturedRBack, $px, $width, $height, requestedQuality, $onEncoded);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static final void submitEncode$lambda$0(byte[] $rgba, int $px, int $width, int $height, int $quality, Consumer $onEncoded) {
        try {
            byte[] jpeg = INSTANCE.encodeJpeg($rgba, $px, $width, $height, $quality);
            if (jpeg != null) {
                $onEncoded.accept(jpeg);
            }
        }
        catch (Throwable throwable) {
        }
        finally {
            encoderBusy.set(false);
        }
    }

    private static final Thread ensureEncoder$lambda$0$0(Runnable r) {
        Thread thread = new Thread(r, "kimiko-portal-encode");
        thread.setDaemon(true);
        return thread;
    }

    private static final String ensureResources$lambda$0() {
        return "kimiko:portallive_backup";
    }

    private static final String ensureResources$lambda$1() {
        return "kimiko:portallive_mirror";
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"dynamic/portal_live_mirror");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        MIRROR_TEXTURE_ID = identifier2;
        streamW = 854;
        streamH = 480;
        Identifier identifier3 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"pipeline/post/portallive/downscale");
        Intrinsics.checkNotNullExpressionValue((Object)identifier3, (String)"fromNamespaceAndPath(...)");
        DOWNSCALE_PIPELINE_ID = identifier3;
        Identifier identifier4 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"post/hpfocus/copy");
        Intrinsics.checkNotNullExpressionValue((Object)identifier4, (String)"fromNamespaceAndPath(...)");
        COPY_SHADER = identifier4;
        mainW = -1;
        mainH = -1;
        requestedFps = 15;
        requestedQuality = 60;
        Vec3d vec3d2 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
        requestedCamera = vec3d2;
        Vec3d vec3d3 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"ZERO");
        requestedLook = vec3d3;
        readInFlight = new AtomicBoolean(false);
        encoderBusy = new AtomicBoolean(false);
        BODY_TIERS = new double[][]{
            {-1.0, -0.55, 0.0, 0.55},
            {-0.55, 0.0, 0.55},
            {0.0, 0.55}
        };
        TIER_MIN_FRACTION = new double[]{0.6, 0.45, 0.0};
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\fR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveCapture$TargetTexture;", "Lnet/minecraft/AbstractTexture;", "Lcom/mojang/blaze3d/textures/GpuTexture;", "gpuTextureVal", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "gpuTextureViewVal", "<init>", "(Lcom/mojang/blaze3d/textures/GpuTexture;Lcom/mojang/blaze3d/textures/GpuTextureView;)V", "getTexture", "()Lcom/mojang/blaze3d/textures/GpuTexture;", "getTextureView", "()Lcom/mojang/blaze3d/textures/GpuTextureView;", "Lcom/mojang/blaze3d/textures/GpuTexture;", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "rtx.kimiko:kimiko"})
    public static final class TargetTexture
    extends AbstractTexture {
        @NotNull
        private final GpuTexture gpuTextureVal;
        @NotNull
        private final GpuTextureView gpuTextureViewVal;

        public TargetTexture(@NotNull GpuTexture gpuTextureVal, @NotNull GpuTextureView gpuTextureViewVal) {
            Intrinsics.checkNotNullParameter((Object)gpuTextureVal, (String)"gpuTextureVal");
            Intrinsics.checkNotNullParameter((Object)gpuTextureViewVal, (String)"gpuTextureViewVal");
            this.gpuTextureVal = gpuTextureVal;
            this.gpuTextureViewVal = gpuTextureViewVal;
        }

        @NotNull
        public GpuTexture getGlTexture() {
            return this.gpuTextureVal;
        }

        @NotNull
        public GpuTextureView getGlTextureView() {
            return this.gpuTextureViewVal;
        }
    }
}

