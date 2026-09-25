/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.pipeline.BlendFunction
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Builder
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
 *  net.minecraft.client.texture.NativeImage
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.world.ClientWorld
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.lwjgl.system.MemoryStack
 */
package rtx.kimiko.utils.render.modules.post.customsky;

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
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.lwjgl.system.MemoryStack;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.modules.impl.Visuals.Ambience;
import rtx.kimiko.utils.render.modules.post.usersky.UserSkyManager;
import rtx.kimiko.utils.render.others.RenderSampler;
import rtx.kimiko.utils.render.render2d.ThemeWaveUniform;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0015\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J\u0013\u0010\n\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u0003J\u001b\u0010\r\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0003J\u0013\u0010\u0010\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u001d\u0010\u0013\u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u007f\u0010\"\u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\"\u0010#J)\u0010)\u001a\u00020\t2\u0006\u0010%\u001a\u00020$2\b\u0010'\u001a\u0004\u0018\u00010&2\u0006\u0010(\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b)\u0010*J\u0017\u0010,\u001a\u00020\t2\u0006\u0010+\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b,\u0010-J\u000f\u0010.\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b.\u0010\u0003J\u0017\u0010/\u001a\u00020\u00172\u0006\u0010+\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b/\u00100J'\u00104\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u00172\u0006\u00102\u001a\u00020\u00172\u0006\u00103\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b4\u00105J\u001f\u00108\u001a\u00020\u00152\u0006\u00106\u001a\u00020\u00172\u0006\u00107\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b8\u00109J\u0013\u0010:\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b:\u0010\u0003J\u000f\u0010;\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b;\u0010\u0003J\u000f\u0010<\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b<\u0010\u0003J\u000f\u0010=\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b=\u0010\u0003J\u000f\u0010>\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b>\u0010\u0003J\u0017\u0010B\u001a\u00020A2\u0006\u0010@\u001a\u00020?H\u0002\u00a2\u0006\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u0014\u0010F\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bF\u0010ER\u0014\u0010G\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u0010ER\u0014\u0010H\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bH\u0010ER\u0014\u0010I\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bI\u0010ER\u0014\u0010J\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bJ\u0010ER\u0014\u0010K\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bK\u0010ER\u0014\u0010L\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bL\u0010MR\u0014\u0010N\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bN\u0010ER\u0014\u0010O\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bO\u0010ER\u0014\u0010P\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bP\u0010ER\u0014\u0010Q\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bQ\u0010RR\u0014\u0010S\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bS\u0010RR\u0014\u0010T\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u0010RR\u0014\u0010U\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bU\u0010RR\u0014\u0010V\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bV\u0010RR\u001a\u0010X\u001a\b\u0012\u0004\u0012\u00020A0W8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bX\u0010YR\u001a\u0010Z\u001a\b\u0012\u0004\u0012\u00020A0W8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bZ\u0010YR\u001a\u0010[\u001a\b\u0012\u0004\u0012\u00020?0W8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b[\u0010\\R\u0014\u0010]\u001a\u00020\u000b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u0014\u0010_\u001a\u00020\u000b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b_\u0010^R\u001c\u0010a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010`0W8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\ba\u0010bR\u0018\u0010c\u001a\u0004\u0018\u00010`8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010dR\u0018\u0010e\u001a\u0004\u0018\u00010`8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010dR\u0018\u0010g\u001a\u0004\u0018\u00010f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bg\u0010hR\u0018\u0010i\u001a\u0004\u0018\u00010f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bi\u0010hR\u001c\u0010k\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010j0W8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bk\u0010lR\u001c\u0010m\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0W8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bm\u0010nR\u0018\u0010o\u001a\u0004\u0018\u00010j8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bo\u0010pR\u0018\u0010q\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bq\u0010rR\u0018\u0010s\u001a\u0004\u0018\u00010j8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bs\u0010pR\u0018\u0010t\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010rR\u001c\u0010u\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010j0W8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bu\u0010lR\u001c\u0010v\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0W8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bv\u0010nR\u0014\u0010x\u001a\u00020w8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bx\u0010yR\u0014\u0010z\u001a\u00020w8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bz\u0010yR\u0016\u0010{\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010ER\u0016\u0010|\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b|\u0010ER\u0016\u0010}\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b}\u0010ER\u0016\u0010~\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b~\u0010ER\u0016\u0010\u007f\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u007f\u0010ER\u0018\u0010\u0080\u0001\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010ER\u0018\u0010\u0081\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0081\u0001\u0010MR\u0018\u0010\u0082\u0001\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0082\u0001\u0010ER\u0019\u0010\u0083\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0019\u0010\u0085\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0085\u0001\u0010\u0084\u0001R\u0016\u0010\u0086\u0001\u001a\u00020\u000b8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0086\u0001\u0010^R\u0019\u0010\u0087\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0087\u0001\u0010\u0084\u0001R\u0019\u0010\u0088\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0088\u0001\u0010\u0084\u0001R$\u0010\u008a\u0001\u001a\r\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0089\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008a\u0001\u0010\u008b\u0001\u00a8\u0006\u008c\u0001"}, d2={"Lrtx/kimiko/utils/render/modules/post/customsky/CustomSkyRenderer;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "isDisabledAfterError", "()Z", "isAllocated", "", "beginFrame", "Lorg/joml/Matrix4f;", "viewProj", "prepareFrame", "(Lorg/joml/Matrix4f;)V", "invalidate", "finishFrame", "Lnet/minecraft/Framebuffer;", "renderTarget", "applyPending", "(Lnet/minecraft/Framebuffer;)V", "", "time", "", "skyType", "colorR", "colorG", "colorB", "color2R", "color2G", "color2B", "gradientMode", "brightness", "useClientColor", "apply", "(Lnet/minecraft/Framebuffer;Lorg/joml/Matrix4f;FIFFFFFFFFZ)V", "Lcom/mojang/blaze3d/systems/CommandEncoder;", "encoder", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "skyView", "hdr", "buildBloom", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lcom/mojang/blaze3d/textures/GpuTextureView;Z)V", "type", "init", "(I)V", "ensureNoiseTexture", "resScale", "(I)I", "width", "height", "scale", "ensureTargets", "(III)Z", "index", "base", "halton", "(II)F", "clear", "closeTargets", "closeNoise", "closeBloomUniform", "closeUniform", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "TYPE_COUNT", "I", "TYPE_BLACKHOLE", "TYPE_STARFALL", "TYPE_USER", "UNIFORM_SIZE", "BLOOM_LEVELS", "BLOOM_UNIFORM_SIZE", "BLOOM_THRESHOLD", "F", "NOISE_SIZE", "NOISE_SHIFT_X", "NOISE_SHIFT_Y", "VERTEX_SHADER", "Lnet/minecraft/Identifier;", "COMPOSITE_SHADER", "COMPOSITE_PIPELINE_ID", "BLOOM_SHADER", "BLOOM_PIPELINE_ID", "", "MARCH_SHADERS", "[Lnet/minecraft/Identifier;", "MARCH_PIPELINE_IDS", "MARCH_PASS_NAMES", "[Ljava/lang/String;", "INV_VIEW_PROJ", "Lorg/joml/Matrix4f;", "PREV_VIEW_PROJ", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "marchPipelines", "[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "compositePipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "bloomPipeline", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "uniformBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "bloomUniformBuffer", "Lcom/mojang/blaze3d/textures/GpuTexture;", "skyTextures", "[Lcom/mojang/blaze3d/textures/GpuTexture;", "skyTextureViews", "[Lcom/mojang/blaze3d/textures/GpuTextureView;", "sceneCopyTexture", "Lcom/mojang/blaze3d/textures/GpuTexture;", "sceneCopyTextureView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "noiseTexture", "noiseTextureView", "bloomTextures", "bloomTextureViews", "", "bloomWidths", "[I", "bloomHeights", "fullWidth", "fullHeight", "marchWidth", "marchHeight", "currentScale", "frameIndex", "taaSequence", "lastType", "historyValid", "Z", "disabledAfterError", "PENDING_VIEW_PROJ", "pendingValid", "appliedThisFrame", "Ljava/lang/ref/WeakReference;", "lastLevel", "Ljava/lang/ref/WeakReference;", "rtx.kimiko:kimiko"})
public final class CustomSkyRenderer {
    @NotNull
    public static final CustomSkyRenderer INSTANCE = new CustomSkyRenderer();
    private static final int TYPE_COUNT = 3;
    private static final int TYPE_BLACKHOLE = 1;
    private static final int TYPE_STARFALL = 2;
    private static final int TYPE_USER = 3;
    private static final int UNIFORM_SIZE = 208;
    private static final int BLOOM_LEVELS = 6;
    private static final int BLOOM_UNIFORM_SIZE = 16;
    private static final float BLOOM_THRESHOLD = 0.28f;
    private static final int NOISE_SIZE = 256;
    private static final int NOISE_SHIFT_X = 37;
    private static final int NOISE_SHIFT_Y = 17;
    @NotNull
    private static final Identifier VERTEX_SHADER = INSTANCE.id("post/customsky/customsky");
    @NotNull
    private static final Identifier COMPOSITE_SHADER = INSTANCE.id("post/customsky/composite");
    @NotNull
    private static final Identifier COMPOSITE_PIPELINE_ID = INSTANCE.id("pipeline/post/customsky/composite");
    @NotNull
    private static final Identifier BLOOM_SHADER = INSTANCE.id("post/customsky/bloom_down");
    @NotNull
    private static final Identifier BLOOM_PIPELINE_ID = INSTANCE.id("pipeline/post/customsky/bloom_down");
    @NotNull
    private static final Identifier[] MARCH_SHADERS;
    @NotNull
    private static final Identifier[] MARCH_PIPELINE_IDS;
    @NotNull
    private static final String[] MARCH_PASS_NAMES;
    @NotNull
    private static final Matrix4f INV_VIEW_PROJ;
    @NotNull
    private static final Matrix4f PREV_VIEW_PROJ;
    @NotNull
    private static final RenderPipeline[] marchPipelines;
    @Nullable
    private static RenderPipeline compositePipeline;
    @Nullable
    private static RenderPipeline bloomPipeline;
    @Nullable
    private static GpuBuffer uniformBuffer;
    @Nullable
    private static GpuBuffer bloomUniformBuffer;
    @NotNull
    private static final GpuTexture[] skyTextures;
    @NotNull
    private static final GpuTextureView[] skyTextureViews;
    @Nullable
    private static GpuTexture sceneCopyTexture;
    @Nullable
    private static GpuTextureView sceneCopyTextureView;
    @Nullable
    private static GpuTexture noiseTexture;
    @Nullable
    private static GpuTextureView noiseTextureView;
    @NotNull
    private static final GpuTexture[] bloomTextures;
    @NotNull
    private static final GpuTextureView[] bloomTextureViews;
    @NotNull
    private static final int[] bloomWidths;
    @NotNull
    private static final int[] bloomHeights;
    private static int fullWidth;
    private static int fullHeight;
    private static int marchWidth;
    private static int marchHeight;
    private static int currentScale;
    private static int frameIndex;
    private static float taaSequence;
    private static int lastType;
    private static boolean historyValid;
    private static boolean disabledAfterError;
    @NotNull
    private static final Matrix4f PENDING_VIEW_PROJ;
    private static boolean pendingValid;
    private static boolean appliedThisFrame;
    @Nullable
    private static WeakReference<ClientWorld> lastLevel;

    private CustomSkyRenderer() {
    }

    @JvmStatic
    public static final boolean isDisabledAfterError() {
        return disabledAfterError;
    }

    @JvmStatic
    public static final boolean isAllocated() {
        return skyTextures[0] != null || sceneCopyTexture != null;
    }

    @JvmStatic
    public static final void beginFrame() {
    }

    @JvmStatic
    public static final void prepareFrame(@NotNull Matrix4f viewProj) {
        Intrinsics.checkNotNullParameter((Object)viewProj, (String)"viewProj");
        ClientWorld level = MinecraftClient.getInstance().world;
        WeakReference<ClientWorld> last = lastLevel;
        if (last == null || last.get() != level) {
            lastLevel = new WeakReference<ClientWorld>(level);
            CustomSkyRenderer.invalidate();
        }
        PENDING_VIEW_PROJ.set((Matrix4fc)viewProj);
        pendingValid = true;
        appliedThisFrame = false;
    }

    @JvmStatic
    public static final void invalidate() {
        pendingValid = false;
        appliedThisFrame = false;
        disabledAfterError = false;
        historyValid = false;
        lastType = -1;
        CustomSkyRenderer.clear();
    }

    @JvmStatic
    public static final void finishFrame() {
        pendingValid = false;
    }

    @JvmStatic
    public static final void applyPending(@Nullable Framebuffer renderTarget) {
        if (appliedThisFrame || !pendingValid || renderTarget == null) {
            return;
        }
        Ambience ambience = Ambience.Companion.getInstance();
        if (ambience == null || !ambience.isCustomSkyActive()) {
            if (CustomSkyRenderer.isAllocated()) {
                CustomSkyRenderer.clear();
            }
            return;
        }
        appliedThisFrame = true;
        CustomSkyRenderer.beginFrame();
        float time = (float)((double)(System.currentTimeMillis() % 20000000L) / 1000.0);
        int color = ambience.skyColorRGB();
        int color2 = ambience.skyColor2RGB();
        CustomSkyRenderer.apply(renderTarget, PENDING_VIEW_PROJ, time, ambience.skyTypeIndex(), (float)(color >> 16 & 0xFF) / 255.0f, (float)(color >> 8 & 0xFF) / 255.0f, (float)(color & 0xFF) / 255.0f, (float)(color2 >> 16 & 0xFF) / 255.0f, (float)(color2 >> 8 & 0xFF) / 255.0f, (float)(color2 & 0xFF) / 255.0f, ambience.skyGradientMode(), ambience.skyBrightness(), ambience.skyUsesClientColor());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void apply(@Nullable Framebuffer renderTarget, @Nullable Matrix4f viewProj, float time, int skyType, float colorR, float colorG, float colorB, float color2R, float color2G, float color2B, float gradientMode, float brightness, boolean useClientColor) {
        RenderPipeline marchPipeline;
        if (disabledAfterError || renderTarget == null || viewProj == null || renderTarget.getColorAttachment() == null || renderTarget.getColorAttachmentView() == null || renderTarget.getDepthAttachmentView() == null || renderTarget.textureWidth <= 0 || renderTarget.textureHeight <= 0) {
            return;
        }
        int type = Math.clamp((long)skyType, 0, 3);
        boolean hdr = type == 1;
        INSTANCE.init(type);
        RenderPipeline renderPipeline = marchPipeline = type == 3 ? UserSkyManager.activePipeline() : marchPipelines[type];
        if (marchPipeline == null || compositePipeline == null || bloomPipeline == null || uniformBuffer == null || bloomUniformBuffer == null || hdr && noiseTextureView == null || !INSTANCE.ensureTargets(renderTarget.textureWidth, renderTarget.textureHeight, INSTANCE.resScale(type))) {
            return;
        }
        if (lastType != type) {
            historyValid = false;
            lastType = type;
        }
        int write = frameIndex & 1;
        int read = 1 - write;
        boolean useHistory = hdr && historyValid;
        try {
            RenderPass pass;
            Object object;
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            INV_VIEW_PROJ.set((Matrix4fc)viewProj).invert();
            float jitterX = INSTANCE.halton(frameIndex % 8 + 1, 2) - 0.5f;
            float jitterY = INSTANCE.halton(frameIndex % 8 + 1, 3) - 0.5f;
            AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
            Object object2 = null;
            try {
                MemoryStack stack = (MemoryStack)autoCloseable;
                boolean bl = false;
                ByteBuffer data = stack.calloc(208);
                INV_VIEW_PROJ.get(0, data);
                data.putFloat(64, time);
                data.putFloat(68, renderTarget.textureWidth);
                data.putFloat(72, renderTarget.textureHeight);
                data.putFloat(76, brightness);
                data.putFloat(80, colorR);
                data.putFloat(84, colorG);
                data.putFloat(88, colorB);
                data.putFloat(92, gradientMode);
                data.putFloat(96, color2R);
                data.putFloat(100, color2G);
                data.putFloat(104, color2B);
                data.putFloat(108, type);
                data.putFloat(112, hdr ? jitterX : 0.0f);
                data.putFloat(116, hdr ? jitterY : 0.0f);
                data.putFloat(120, useHistory ? 1.0f : 0.0f);
                data.putFloat(124, taaSequence);
                PREV_VIEW_PROJ.get(128, data);
                data.putFloat(192, useClientColor ? 1.0f : 0.0f);
                data.position(0);
                GpuBuffer gpuBuffer = uniformBuffer;
                Intrinsics.checkNotNull((Object)gpuBuffer);
                encoder.writeToBuffer(gpuBuffer.slice(0L, 208L), data);
// object = Unit.INSTANCE;
            }
            catch (Throwable bl) {
                object2 = bl;
                throw bl;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)object2);
            }
            String marchName = type == 3 ? "kimiko:customsky_march_user" : MARCH_PASS_NAMES[type];
            Supplier<String> supplier = () -> CustomSkyRenderer.apply$lambda$1(marchName);
            GpuTextureView gpuTextureView = skyTextureViews[write];
            Intrinsics.checkNotNull((Object)gpuTextureView);
            object2 = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
            object = null;
            try {
                pass = (RenderPass)object2;
                boolean bl = false;
                pass.setPipeline(marchPipeline);
                GpuBuffer gpuBuffer = uniformBuffer;
                Intrinsics.checkNotNull((Object)gpuBuffer);
                pass.setUniform("SkyParams", gpuBuffer);
                if (type != 3) {
                    ThemeWaveUniform.bind(pass);
                }
                if (hdr) {
                    pass.bindTexture("NoiseTex", noiseTextureView, RenderSampler.linearRepeat());
                    pass.bindTexture("History", skyTextureViews[read], RenderSampler.linear());
                }
                pass.draw(0, 6);
// pass = Unit.INSTANCE;
            }
            catch (Throwable bl) {
                object = bl;
                throw bl;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)object2, (Throwable)object);
            }
            INSTANCE.buildBloom(encoder, skyTextureViews[write], hdr);
            GpuTexture gpuTexture = renderTarget.getColorAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = sceneCopyTexture;
            Intrinsics.checkNotNull((Object)gpuTexture2);
            encoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, renderTarget.textureWidth, renderTarget.textureHeight);
            Supplier<String> supplier2 = CustomSkyRenderer::apply$lambda$3;
            GpuTextureView gpuTextureView2 = renderTarget.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView2);
            object2 = (AutoCloseable)encoder.createRenderPass(supplier2, gpuTextureView2, OptionalInt.empty());
            object = null;
            try {
                pass = (RenderPass)object2;
                boolean bl = false;
                RenderPipeline renderPipeline2 = compositePipeline;
                Intrinsics.checkNotNull((Object)renderPipeline2);
                pass.setPipeline(renderPipeline2);
                GpuBuffer gpuBuffer = uniformBuffer;
                Intrinsics.checkNotNull((Object)gpuBuffer);
                pass.setUniform("SkyParams", gpuBuffer);
                pass.bindTexture("Scene", sceneCopyTextureView, RenderSampler.linear());
                pass.bindTexture("DepthTex", renderTarget.getDepthAttachmentView(), RenderSampler.nearest());
                pass.bindTexture("Sky", skyTextureViews[write], RenderSampler.linear());
                for (int level = 0; level < 6; ++level) {
                    pass.bindTexture("Bloom" + level, bloomTextureViews[level], RenderSampler.linear());
                }
                pass.draw(0, 6);
                Unit unit = Unit.INSTANCE;
            }
            catch (Throwable throwable) {
                object = throwable;
                throw throwable;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)object2, (Throwable)object);
            }
            PREV_VIEW_PROJ.set((Matrix4fc)viewProj);
            historyValid = true;
            frameIndex = frameIndex + 1 & 0x3FFFFFFF;
            taaSequence = (taaSequence + 0.618034f) % 1.0f;
        }
        catch (Throwable throwable) {
            disabledAfterError = true;
            INSTANCE.closeTargets();
            INSTANCE.closeUniform();
            INSTANCE.closeBloomUniform();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void buildBloom(CommandEncoder encoder, GpuTextureView skyView, boolean hdr) {
        GpuTextureView source = skyView;
        int srcWidth = 0;
        srcWidth = marchWidth;
        int srcHeight = 0;
        srcHeight = marchHeight;
        for (int level = 0; level < 6; ++level) {
            Object object;
            int current = level;
            AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
            Throwable throwable = null;
            try {
                MemoryStack stack = (MemoryStack)autoCloseable;
                boolean bl = false;
                ByteBuffer data = stack.calloc(16);
                data.putFloat(0, 1.0f / (float)srcWidth);
                data.putFloat(4, 1.0f / (float)srcHeight);
                data.putFloat(8, !hdr && level == 0 ? 0.28f : 0.0f);
                data.putFloat(12, hdr ? 1.0f : 0.0f);
                data.position(0);
                GpuBuffer gpuBuffer = bloomUniformBuffer;
                Intrinsics.checkNotNull((Object)gpuBuffer);
                encoder.writeToBuffer(gpuBuffer.slice(0L, 16L), data);
// object = Unit.INSTANCE;
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
            }
            GpuTextureView target = bloomTextureViews[level];
            Intrinsics.checkNotNull((Object)target);
            GpuTextureView input = source;
            object = (AutoCloseable)encoder.createRenderPass(() -> CustomSkyRenderer.buildBloom$lambda$1(current), target, OptionalInt.empty());
            Throwable throwable3 = null;
            try {
                RenderPass pass = (RenderPass)object;
                boolean bl = false;
                RenderPipeline renderPipeline = bloomPipeline;
                Intrinsics.checkNotNull((Object)renderPipeline);
                pass.setPipeline(renderPipeline);
                GpuBuffer gpuBuffer = bloomUniformBuffer;
                Intrinsics.checkNotNull((Object)gpuBuffer);
                pass.setUniform("BloomParams", gpuBuffer);
                pass.bindTexture("Source", input, RenderSampler.linear());
                pass.draw(0, 6);
                Unit unit = Unit.INSTANCE;
            }
            catch (Throwable throwable4) {
                throwable3 = throwable4;
                throw throwable4;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)object, (Throwable)throwable3);
            }
            source = target;
            srcWidth = bloomWidths[level];
            srcHeight = bloomHeights[level];
        }
    }

    private final void init(int type) {
        if (disabledAfterError) {
            return;
        }
        try {
            GpuBuffer uniform;
            GpuBuffer bloomUniform;
            RenderPipeline.Builder builder;
            if (type != 3 && marchPipelines[type] == null) {
                RenderPipeline.Builder builder2 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(MARCH_PIPELINE_IDS[type]).withVertexShader(VERTEX_SHADER).withFragmentShader(MARCH_SHADERS[type]).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("SkyParams", UniformType.UNIFORM_BUFFER).withUniform("ThemeWaveParams", UniformType.UNIFORM_BUFFER).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false);
                Intrinsics.checkNotNullExpressionValue((Object)builder2, (String)"withCull(...)");
                builder = builder2;
                if (type == 1) {
                    builder.withSampler("NoiseTex").withSampler("History");
                }
                CustomSkyRenderer.marchPipelines[type] = RenderPipelines.register((RenderPipeline)builder.build());
            }
            if (compositePipeline == null) {
                RenderPipeline.Builder builder3 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(COMPOSITE_PIPELINE_ID).withVertexShader(VERTEX_SHADER).withFragmentShader(COMPOSITE_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("SkyParams", UniformType.UNIFORM_BUFFER).withSampler("Scene").withSampler("DepthTex").withSampler("Sky");
                Intrinsics.checkNotNullExpressionValue((Object)builder3, (String)"withSampler(...)");
                builder = builder3;
                for (int level = 0; level < 6; ++level) {
                    builder.withSampler("Bloom" + level);
                }
                compositePipeline = RenderPipelines.register((RenderPipeline)builder.withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (bloomPipeline == null) {
                bloomPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(BLOOM_PIPELINE_ID).withVertexShader(VERTEX_SHADER).withFragmentShader(BLOOM_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("BloomParams", UniformType.UNIFORM_BUFFER).withSampler("Source").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if ((bloomUniform = bloomUniformBuffer) == null || bloomUniform.isClosed() || bloomUniform.size() < 16L) {
                this.closeBloomUniform();
                bloomUniformBuffer = RenderSystem.getDevice().createBuffer(CustomSkyRenderer::init$lambda$0, 136, 16L);
            }
            if ((uniform = uniformBuffer) == null || uniform.isClosed() || uniform.size() < 208L) {
                this.closeUniform();
                uniformBuffer = RenderSystem.getDevice().createBuffer(CustomSkyRenderer::init$lambda$1, 136, 208L);
            }
            if (type == 1) {
                this.ensureNoiseTexture();
            }
        }
        catch (Throwable throwable) {
            disabledAfterError = true;
            for (int i = 0; i < 3; ++i) {
                CustomSkyRenderer.marchPipelines[i] = null;
            }
            compositePipeline = null;
            bloomPipeline = null;
            this.closeUniform();
            this.closeBloomUniform();
            this.closeNoise();
        }
    }

    private final void ensureNoiseTexture() {
        GpuTexture current = noiseTexture;
        if (current != null && !current.isClosed()) {
            return;
        }
        this.closeNoise();
        GpuDevice gpuDevice = RenderSystem.getDevice();
        Intrinsics.checkNotNullExpressionValue((Object)gpuDevice, (String)"getDevice(...)");
        GpuDevice device = gpuDevice;
        int[] red = new int[65536];
        int[] blue = new int[65536];
        Random random = new Random(1592639215L);
        int n = red.length;
        for (int i = 0; i < n; ++i) {
            red[i] = random.nextInt(256);
            blue[i] = random.nextInt(256);
        }
        NativeImage image = new NativeImage(256, 256, false);
        for (int y = 0; y < 256; ++y) {
            for (int x = 0; x < 256; ++x) {
                int sx = Math.floorMod(x - 37, 256);
                int sy = Math.floorMod(y - 17, 256);
                int r = red[y * 256 + x];
                int g = red[sy * 256 + sx];
                int b = blue[y * 256 + x];
                image.setColor(x, y, 0xFF000000 | b << 16 | g << 8 | r);
            }
        }
        noiseTexture = device.createTexture(CustomSkyRenderer::ensureNoiseTexture$lambda$0, 5, TextureFormat.RGBA8, 256, 256, 1, 1);
        CommandEncoder commandEncoder = device.createCommandEncoder();
        GpuTexture gpuTexture = noiseTexture;
        Intrinsics.checkNotNull((Object)gpuTexture);
        commandEncoder.writeToTexture(gpuTexture, image);
        GpuTexture gpuTexture2 = noiseTexture;
        Intrinsics.checkNotNull((Object)gpuTexture2);
        noiseTextureView = device.createTextureView(gpuTexture2);
        image.close();
    }

    private final int resScale(int type) {
        return type == 1 ? 2 : 1;
    }

    private final boolean ensureTargets(int width, int height, int scale) {
        GpuDevice gpuDevice = RenderSystem.tryGetDevice();
        if (gpuDevice == null) {
            return false;
        }
        GpuDevice device = gpuDevice;
        if (skyTextures[0] != null && skyTextures[1] != null && sceneCopyTexture != null && fullWidth == width && fullHeight == height && currentScale == scale) {
            return true;
        }
        this.closeTargets();
        int hw = Math.max(1, width / scale);
        int hh = Math.max(1, height / scale);
        for (int i = 0; i < 2; ++i) {
            int index = i;
            CustomSkyRenderer.skyTextures[i] = device.createTexture(() -> CustomSkyRenderer.ensureTargets$lambda$0(index), 12, TextureFormat.RGBA8, hw, hh, 1, 1);
            GpuTexture gpuTexture = skyTextures[i];
            Intrinsics.checkNotNull((Object)gpuTexture);
            CustomSkyRenderer.skyTextureViews[i] = device.createTextureView(gpuTexture);
        }
        GpuTexture gpuTexture = sceneCopyTexture = device.createTexture(CustomSkyRenderer::ensureTargets$lambda$1, 5, TextureFormat.RGBA8, width, height, 1, 1);
        Intrinsics.checkNotNull((Object)gpuTexture);
        sceneCopyTextureView = device.createTextureView(gpuTexture);
        int bw = hw;
        int bh = hh;
        for (int level = 0; level < 6; ++level) {
            bw = Math.max(1, bw / 2);
            bh = Math.max(1, bh / 2);
            int index = level;
            int levelWidth = bw;
            int levelHeight = bh;
            CustomSkyRenderer.bloomTextures[level] = device.createTexture(() -> CustomSkyRenderer.ensureTargets$lambda$2(index), 12, TextureFormat.RGBA8, levelWidth, levelHeight, 1, 1);
            GpuTexture gpuTexture2 = bloomTextures[level];
            Intrinsics.checkNotNull((Object)gpuTexture2);
            CustomSkyRenderer.bloomTextureViews[level] = device.createTextureView(gpuTexture2);
            CustomSkyRenderer.bloomWidths[level] = levelWidth;
            CustomSkyRenderer.bloomHeights[level] = levelHeight;
        }
        fullWidth = width;
        fullHeight = height;
        marchWidth = hw;
        marchHeight = hh;
        currentScale = scale;
        historyValid = false;
        return true;
    }

    private final float halton(int index, int base) {
        float result = 0.0f;
        float f = 1.0f;
        for (int i = index; i > 0; i /= base) {
            result += (f /= (float)base) * (float)(i % base);
        }
        return result;
    }

    @JvmStatic
    public static final void clear() {
        INSTANCE.closeTargets();
    }

    private final void closeTargets() {
        for (int i = 0; i < 2; ++i) {
            GpuTextureView gpuTextureView = skyTextureViews[i];
            if (gpuTextureView != null) {
                gpuTextureView.close();
            }
            CustomSkyRenderer.skyTextureViews[i] = null;
            GpuTexture gpuTexture = skyTextures[i];
            if (gpuTexture != null) {
                gpuTexture.close();
            }
            CustomSkyRenderer.skyTextures[i] = null;
        }
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
        for (int level = 0; level < 6; ++level) {
            GpuTextureView gpuTextureView2 = bloomTextureViews[level];
            if (gpuTextureView2 != null) {
                gpuTextureView2.close();
            }
            CustomSkyRenderer.bloomTextureViews[level] = null;
            GpuTexture gpuTexture2 = bloomTextures[level];
            if (gpuTexture2 != null) {
                gpuTexture2.close();
            }
            CustomSkyRenderer.bloomTextures[level] = null;
            CustomSkyRenderer.bloomWidths[level] = 0;
            CustomSkyRenderer.bloomHeights[level] = 0;
        }
        fullWidth = -1;
        fullHeight = -1;
        historyValid = false;
    }

    private final void closeNoise() {
        GpuTextureView gpuTextureView = noiseTextureView;
        if (gpuTextureView != null) {
            gpuTextureView.close();
        }
        noiseTextureView = null;
        GpuTexture gpuTexture = noiseTexture;
        if (gpuTexture != null) {
            gpuTexture.close();
        }
        noiseTexture = null;
    }

    private final void closeBloomUniform() {
        GpuBuffer gpuBuffer = bloomUniformBuffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        bloomUniformBuffer = null;
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

    private static final String apply$lambda$1(String $marchName) {
        return $marchName;
    }

    private static final String apply$lambda$3() {
        return "kimiko:customsky_composite";
    }

    private static final String buildBloom$lambda$1(int $current) {
        return "kimiko:customsky_bloom" + $current;
    }

    private static final String init$lambda$0() {
        return "kimiko:customsky_bloom_uniforms";
    }

    private static final String init$lambda$1() {
        return "kimiko:customsky_uniforms";
    }

    private static final String ensureNoiseTexture$lambda$0() {
        return "kimiko:customsky_noise";
    }

    private static final String ensureTargets$lambda$0(int $index) {
        return "kimiko:customsky_march" + $index;
    }

    private static final String ensureTargets$lambda$1() {
        return "kimiko:customsky_scene_copy";
    }

    private static final String ensureTargets$lambda$2(int $index) {
        return "kimiko:customsky_bloom" + $index;
    }

    static {
        MARCH_SHADERS = new Identifier[]{INSTANCE.id("post/customsky/aurora"), INSTANCE.id("post/customsky/blackhole"), INSTANCE.id("post/customsky/starfall")};
        MARCH_PIPELINE_IDS = new Identifier[]{INSTANCE.id("pipeline/post/customsky/aurora"), INSTANCE.id("pipeline/post/customsky/blackhole"), INSTANCE.id("pipeline/post/customsky/starfall")};
        MARCH_PASS_NAMES = new String[]{"kimiko:customsky_march_aurora", "kimiko:customsky_march_blackhole", "kimiko:customsky_march_starfall"};
        INV_VIEW_PROJ = new Matrix4f();
        PREV_VIEW_PROJ = new Matrix4f();
        marchPipelines = new RenderPipeline[3];
        skyTextures = new GpuTexture[2];
        skyTextureViews = new GpuTextureView[2];
        bloomTextures = new GpuTexture[6];
        bloomTextureViews = new GpuTextureView[6];
        bloomWidths = new int[6];
        bloomHeights = new int[6];
        fullWidth = -1;
        fullHeight = -1;
        marchWidth = -1;
        marchHeight = -1;
        currentScale = -1;
        lastType = -1;
        PENDING_VIEW_PROJ = new Matrix4f();
    }
}

