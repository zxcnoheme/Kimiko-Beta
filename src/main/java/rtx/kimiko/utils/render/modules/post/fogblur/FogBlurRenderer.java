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
 *  net.minecraft.client.gl.GpuSampler
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.block.enums.CameraSubmersionType
 *  net.minecraft.client.gl.SimpleFramebuffer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.system.MemoryStack
 */
package rtx.kimiko.utils.render.modules.post.fogblur;

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
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.gl.SimpleFramebuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryStack;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.others.RenderSampler;
import rtx.kimiko.utils.render.render2d.ThemeWaveUniform;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u000f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J\u001d\u0010\f\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\rJ;\u0010\u0014\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0014\u0010\u0015J+\u0010\u0016\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0018\u0010\u0003J\u0013\u0010\u0019\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0019\u0010\u0003JE\u0010!\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u001fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b#\u0010\u0003J/\u0010'\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u001fH\u0002\u00a2\u0006\u0004\b'\u0010(J\u001f\u0010)\u001a\u00020\b2\u0006\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u001fH\u0002\u00a2\u0006\u0004\b)\u0010*J1\u0010/\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010+2\u0006\u0010.\u001a\u00020-2\u0006\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u001fH\u0002\u00a2\u0006\u0004\b/\u00100J-\u00106\u001a\u0004\u0018\u0001032\u0006\u00102\u001a\u0002012\b\u00104\u001a\u0004\u0018\u0001032\b\u00105\u001a\u0004\u0018\u000103H\u0002\u00a2\u0006\u0004\b6\u00107J;\u0010<\u001a\u0004\u0018\u0001032\b\u00108\u001a\u0004\u0018\u0001032\u0006\u00109\u001a\u00020\u001f2\u0006\u0010:\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020\u001f2\u0006\u0010;\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b<\u0010=Jk\u0010F\u001a\u00020\b2\u0006\u0010?\u001a\u00020>2\b\u00108\u001a\u0004\u0018\u0001032\u0006\u00109\u001a\u00020\u001f2\u0006\u0010:\u001a\u00020\u001f2\b\u0010,\u001a\u0004\u0018\u00010+2\u0006\u0010;\u001a\u00020\u000e2\u0006\u0010@\u001a\u00020\u000e2\u0006\u0010A\u001a\u00020\u000e2\u0006\u0010B\u001a\u00020\u000e2\u0006\u0010C\u001a\u00020\u000e2\u0006\u0010E\u001a\u00020DH\u0002\u00a2\u0006\u0004\bF\u0010GJW\u0010N\u001a\u00020\b2\b\u0010,\u001a\u0004\u0018\u0001032\b\u0010H\u001a\u0004\u0018\u0001032\b\u0010I\u001a\u0004\u0018\u0001032\b\u00105\u001a\u0004\u0018\u0001032\u0006\u0010J\u001a\u00020\u000e2\u0006\u0010K\u001a\u00020\u000e2\u0006\u0010L\u001a\u00020\u000e2\u0006\u0010M\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\bN\u0010OJW\u0010T\u001a\u00020\b2\u0006\u00102\u001a\u0002012\u0006\u0010P\u001a\u00020\u000e2\u0006\u0010Q\u001a\u00020\u000e2\u0006\u0010R\u001a\u00020\u000e2\u0006\u0010J\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020\u000e2\u0006\u0010K\u001a\u00020\u000e2\u0006\u0010L\u001a\u00020\u000e2\u0006\u0010M\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\bT\u0010UJ\u001f\u0010V\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u001fH\u0002\u00a2\u0006\u0004\bV\u0010WJ\u000f\u0010X\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\bX\u0010YJ\u000f\u0010Z\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bZ\u0010\u0007J\u000f\u0010[\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b[\u0010\u0003J\u000f\u0010\\\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\\\u0010\u0003J\u000f\u0010]\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b]\u0010\u0003J\u000f\u0010^\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b^\u0010\u0003J'\u0010b\u001a\u00020\u000e2\u0006\u0010_\u001a\u00020\u000e2\u0006\u0010`\u001a\u00020\u000e2\u0006\u0010a\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\bb\u0010cJ\u0017\u0010f\u001a\u00020e2\u0006\u0010d\u001a\u00020-H\u0002\u00a2\u0006\u0004\bf\u0010gR\u0014\u0010h\u001a\u00020\u001f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bh\u0010iR\u0014\u0010j\u001a\u00020\u001f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bj\u0010iR\u0014\u0010k\u001a\u00020\u001f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bk\u0010iR\u0014\u0010l\u001a\u00020e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bl\u0010mR\u0014\u0010n\u001a\u00020e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bn\u0010mR\u0014\u0010o\u001a\u00020e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bo\u0010mR\u0014\u0010p\u001a\u00020e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bp\u0010mR\u0014\u0010q\u001a\u00020e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bq\u0010mR\u0014\u0010r\u001a\u00020e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\br\u0010mR\u0014\u0010s\u001a\u00020e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bs\u0010mR\u0014\u0010t\u001a\u00020e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bt\u0010mR\u0014\u0010u\u001a\u00020e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bu\u0010mR\u0014\u0010v\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bv\u0010wR\u0016\u0010x\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bx\u0010wR\u0016\u0010y\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010wR\u0016\u0010z\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bz\u0010wR\u0016\u0010{\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010wR\u0016\u0010|\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b|\u0010wR\u0016\u0010}\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b}\u0010wR\u0016\u0010~\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b~\u0010wR\u0017\u0010\u007f\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u007f\u0010\u0080\u0001R\u001b\u0010\u0081\u0001\u001a\u0004\u0018\u00010>8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0081\u0001\u0010\u0082\u0001R\u001b\u0010\u0083\u0001\u001a\u0004\u0018\u00010>8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0083\u0001\u0010\u0082\u0001R\u001b\u0010\u0084\u0001\u001a\u0004\u0018\u00010>8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0084\u0001\u0010\u0082\u0001R\u001b\u0010\u0085\u0001\u001a\u0004\u0018\u00010>8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0085\u0001\u0010\u0082\u0001R\u001c\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0086\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0087\u0001\u0010\u0088\u0001R\u001c\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u0086\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0089\u0001\u0010\u0088\u0001R\u001c\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008a\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008b\u0001\u0010\u008c\u0001R\u001b\u0010\u008d\u0001\u001a\u0004\u0018\u0001038\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008d\u0001\u0010\u008e\u0001R\u001b\u0010\u008f\u0001\u001a\u0004\u0018\u00010+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008f\u0001\u0010\u0090\u0001R \u0010\u0092\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010+0\u0091\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0092\u0001\u0010\u0093\u0001R \u0010\u0094\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010+0\u0091\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0094\u0001\u0010\u0093\u0001R\u0018\u0010\u0095\u0001\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0095\u0001\u0010iR\u0018\u0010\u0096\u0001\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0096\u0001\u0010iR\u0018\u0010\u0097\u0001\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0097\u0001\u0010iR\u0018\u0010\u0098\u0001\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0098\u0001\u0010iR\u0018\u0010\u0099\u0001\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0099\u0001\u0010iR\u0019\u0010\u009a\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u0080\u0001R\u001c\u0010\u009b\u0001\u001a\u0005\u0018\u00010\u008a\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009b\u0001\u0010\u008c\u0001R\u001b\u0010\u009c\u0001\u001a\u0004\u0018\u0001038\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u008e\u0001R\u0018\u0010\u009d\u0001\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u009d\u0001\u0010iR\u0018\u0010\u009e\u0001\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u009e\u0001\u0010iR\u0019\u0010\u009f\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009f\u0001\u0010\u0080\u0001\u00a8\u0006\u00a0\u0001"}, d2={"Lrtx/kimiko/utils/render/modules/post/fogblur/FogBlurRenderer;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "isDisabledAfterError", "()Z", "", "beginFrame", "Lnet/minecraft/Framebuffer;", "main", "captureOpaqueDepth", "(Lnet/minecraft/Framebuffer;)V", "", "red", "green", "blue", "mix", "clientTheme", "setBlurTint", "(FFFFZ)V", "setFallbackColor", "(FFF)V", "clear", "invalidate", "renderTarget", "strength", "distanceBlocks", "opacityPercent", "renderScale", "", "maxPasses", "apply", "(Lnet/minecraft/Framebuffer;FFFFI)V", "init", "width", "height", "passes", "ensureTargets", "(IIFI)Z", "ensureDepthCopy", "(II)V", "Lnet/minecraft/SimpleFramebuffer;", "target", "", "name", "ensureTarget", "(Lnet/minecraft/SimpleFramebuffer;Ljava/lang/String;II)Lnet/minecraft/SimpleFramebuffer;", "Lcom/mojang/blaze3d/systems/CommandEncoder;", "encoder", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "scene", "depth", "renderPrepare", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;)Lcom/mojang/blaze3d/textures/GpuTextureView;", "source", "sourceWidth", "sourceHeight", "offsetScale", "renderBlurChain", "(Lcom/mojang/blaze3d/textures/GpuTextureView;IIIF)Lcom/mojang/blaze3d/textures/GpuTextureView;", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "sourceX", "sourceY", "sourceW", "sourceH", "Lnet/minecraft/GpuSampler;", "sampler", "renderKawasePass", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lcom/mojang/blaze3d/textures/GpuTextureView;IILnet/minecraft/SimpleFramebuffer;FFFFFLnet/minecraft/GpuSampler;)V", "medium", "strong", "opacity", "farPlane", "minThreshold", "maxThreshold", "composite", "(Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;FFFF)V", "texelX", "texelY", "offset", "nearPlane", "writeCompositeUniform", "(Lcom/mojang/blaze3d/systems/CommandEncoder;FFFFFFFF)V", "computePasses", "(FI)I", "viewDistance", "()F", "isUnderwaterView", "closeTargets", "closeOpaqueDepth", "closeCompositeBuffer", "closeKawaseBuffer", "value", "min", "max", "clamp", "(FFF)F", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "COMPOSITE_UNIFORM_SIZE", "I", "KAWASE_UNIFORM_SIZE", "MAX_BLUR_ITERATIONS", "KAWASE_DOWN_PIPELINE_ID", "Lnet/minecraft/Identifier;", "KAWASE_UP_PIPELINE_ID", "COMPOSITE_PIPELINE_ID", "KAWASE_VERTEX", "KAWASE_DOWN_SHADER", "KAWASE_UP_SHADER", "PREPARE_PIPELINE_ID", "PREPARE_SHADER", "COMPOSITE_SHADER", "UNDERWATER_FAR", "F", "fallbackRed", "fallbackGreen", "fallbackBlue", "tintRed", "tintGreen", "tintBlue", "tintMix", "tintFromClientTheme", "Z", "kawaseDownPipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "kawaseUpPipeline", "compositePipeline", "preparePipeline", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "compositeUniformBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "kawaseUniformBuffer", "Lcom/mojang/blaze3d/textures/GpuTexture;", "depthCopyTexture", "Lcom/mojang/blaze3d/textures/GpuTexture;", "depthCopyTextureView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "prepareTarget", "Lnet/minecraft/SimpleFramebuffer;", "", "downTargets", "[Lnet/minecraft/SimpleFramebuffer;", "upTargets", "sceneWidth", "sceneHeight", "blurWidth", "blurHeight", "allocatedPasses", "disabledAfterError", "opaqueDepthTexture", "opaqueDepthTextureView", "opaqueDepthWidth", "opaqueDepthHeight", "opaqueCaptured", "rtx.kimiko:kimiko"})
public final class FogBlurRenderer {
    @NotNull
    public static final FogBlurRenderer INSTANCE = new FogBlurRenderer();
    private static final int COMPOSITE_UNIFORM_SIZE = 64;
    private static final int KAWASE_UNIFORM_SIZE = 48;
    private static final int MAX_BLUR_ITERATIONS = 4;
    @NotNull
    private static final Identifier KAWASE_DOWN_PIPELINE_ID = INSTANCE.id("pipeline/post/fogblur/kawase_down");
    @NotNull
    private static final Identifier KAWASE_UP_PIPELINE_ID = INSTANCE.id("pipeline/post/fogblur/kawase_up");
    @NotNull
    private static final Identifier COMPOSITE_PIPELINE_ID = INSTANCE.id("pipeline/post/fogblur/composite");
    @NotNull
    private static final Identifier KAWASE_VERTEX = INSTANCE.id("post/fogblur/kawase");
    @NotNull
    private static final Identifier KAWASE_DOWN_SHADER = INSTANCE.id("post/fogblur/kawase_down");
    @NotNull
    private static final Identifier KAWASE_UP_SHADER = INSTANCE.id("post/fogblur/kawase_up");
    @NotNull
    private static final Identifier PREPARE_PIPELINE_ID = INSTANCE.id("pipeline/post/fogblur/prepare");
    @NotNull
    private static final Identifier PREPARE_SHADER = INSTANCE.id("post/fogblur/prepare");
    @NotNull
    private static final Identifier COMPOSITE_SHADER = INSTANCE.id("post/fogblur/composite");
    private static final float UNDERWATER_FAR = 32.0f;
    private static float fallbackRed = 0.55f;
    private static float fallbackGreen = 0.65f;
    private static float fallbackBlue = 0.78f;
    private static float tintRed;
    private static float tintGreen;
    private static float tintBlue;
    private static float tintMix;
    private static boolean tintFromClientTheme;
    @Nullable
    private static RenderPipeline kawaseDownPipeline;
    @Nullable
    private static RenderPipeline kawaseUpPipeline;
    @Nullable
    private static RenderPipeline compositePipeline;
    @Nullable
    private static RenderPipeline preparePipeline;
    @Nullable
    private static GpuBuffer compositeUniformBuffer;
    @Nullable
    private static GpuBuffer kawaseUniformBuffer;
    @Nullable
    private static GpuTexture depthCopyTexture;
    @Nullable
    private static GpuTextureView depthCopyTextureView;
    @Nullable
    private static SimpleFramebuffer prepareTarget;
    @NotNull
    private static final SimpleFramebuffer[] downTargets;
    @NotNull
    private static final SimpleFramebuffer[] upTargets;
    private static int sceneWidth;
    private static int sceneHeight;
    private static int blurWidth;
    private static int blurHeight;
    private static int allocatedPasses;
    private static boolean disabledAfterError;
    @Nullable
    private static GpuTexture opaqueDepthTexture;
    @Nullable
    private static GpuTextureView opaqueDepthTextureView;
    private static int opaqueDepthWidth;
    private static int opaqueDepthHeight;
    private static boolean opaqueCaptured;

    private FogBlurRenderer() {
    }

    @JvmStatic
    public static final boolean isDisabledAfterError() {
        return disabledAfterError;
    }

    @JvmStatic
    public static final void beginFrame() {
        opaqueCaptured = false;
    }

    @JvmStatic
    public static final void captureOpaqueDepth(@Nullable Framebuffer main) {
        if (disabledAfterError || main == null || main.getDepthAttachment() == null || main.textureWidth <= 0 || main.textureHeight <= 0) {
            return;
        }
        try {
            GpuTexture gpuTexture = main.getDepthAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture src = gpuTexture;
            GpuDevice gpuDevice = RenderSystem.tryGetDevice();
            if (gpuDevice == null) {
                return;
            }
            GpuDevice device = gpuDevice;
            if (opaqueDepthTexture == null || opaqueDepthWidth != main.textureWidth || opaqueDepthHeight != main.textureHeight) {
                INSTANCE.closeOpaqueDepth();
                GpuTexture gpuTexture2 = opaqueDepthTexture = device.createTexture(FogBlurRenderer::captureOpaqueDepth$lambda$0, 5, src.getFormat(), main.textureWidth, main.textureHeight, 1, 1);
                Intrinsics.checkNotNull((Object)gpuTexture2);
                opaqueDepthTextureView = device.createTextureView(gpuTexture2);
                opaqueDepthWidth = main.textureWidth;
                opaqueDepthHeight = main.textureHeight;
            }
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            GpuTexture gpuTexture3 = opaqueDepthTexture;
            Intrinsics.checkNotNull((Object)gpuTexture3);
            commandEncoder.copyTextureToTexture(src, gpuTexture3, 0, 0, 0, 0, 0, main.textureWidth, main.textureHeight);
            opaqueCaptured = true;
        }
        catch (Throwable ignored) {
            opaqueCaptured = false;
        }
    }

    @JvmStatic
    public static final void setBlurTint(float red, float green, float blue, float mix, boolean clientTheme) {
        tintFromClientTheme = clientTheme;
        if (!(Math.abs(red) <= Float.MAX_VALUE && Math.abs(green) <= Float.MAX_VALUE && Math.abs(blue) <= Float.MAX_VALUE && Math.abs(mix) <= Float.MAX_VALUE)) {
            tintRed = 0.0f;
            tintGreen = 0.0f;
            tintBlue = 0.0f;
            tintMix = 0.0f;
            return;
        }
        tintRed = INSTANCE.clamp(red, 0.0f, 1.0f);
        tintGreen = INSTANCE.clamp(green, 0.0f, 1.0f);
        tintBlue = INSTANCE.clamp(blue, 0.0f, 1.0f);
        tintMix = INSTANCE.clamp(mix, 0.0f, 1.0f);
    }

    @JvmStatic
    public static final void setFallbackColor(float red, float green, float blue) {
        if (!(Math.abs(red) <= Float.MAX_VALUE && Math.abs(green) <= Float.MAX_VALUE && Math.abs(blue) <= Float.MAX_VALUE)) {
            return;
        }
        fallbackRed = INSTANCE.clamp(red, 0.0f, 1.0f);
        fallbackGreen = INSTANCE.clamp(green, 0.0f, 1.0f);
        fallbackBlue = INSTANCE.clamp(blue, 0.0f, 1.0f);
    }

    @JvmStatic
    public static final void clear() {
        INSTANCE.closeTargets();
    }

    @JvmStatic
    public static final void invalidate() {
        disabledAfterError = false;
        opaqueCaptured = false;
        INSTANCE.closeTargets();
    }

    @JvmStatic
    public static final void apply(@Nullable Framebuffer renderTarget, float strength, float distanceBlocks, float opacityPercent, float renderScale, int maxPasses) {
        if (disabledAfterError || renderTarget == null || renderTarget.getColorAttachment() == null || renderTarget.getColorAttachmentView() == null || renderTarget.getDepthAttachment() == null) {
            return;
        }
        if (renderTarget.textureWidth <= 0 || renderTarget.textureHeight <= 0) {
            return;
        }
        INSTANCE.init();
        if (kawaseDownPipeline == null || kawaseUpPipeline == null || compositePipeline == null || preparePipeline == null || compositeUniformBuffer == null || kawaseUniformBuffer == null) {
            return;
        }
        float opacity = INSTANCE.clamp(opacityPercent / 100.0f, 0.0f, 1.0f);
        if (opacity <= 0.0f) {
            return;
        }
        int passes = INSTANCE.computePasses(strength, maxPasses);
        if (!INSTANCE.ensureTargets(renderTarget.textureWidth, renderTarget.textureHeight, renderScale, passes)) {
            return;
        }
        try {
            boolean hasOpaqueSnapshot;
            boolean bl = hasOpaqueSnapshot = opaqueCaptured && opaqueDepthTextureView != null;
            if (!hasOpaqueSnapshot) {
                INSTANCE.ensureDepthCopy(renderTarget.textureWidth, renderTarget.textureHeight);
                CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
                GpuTexture gpuTexture = renderTarget.getDepthAttachment();
                Intrinsics.checkNotNull((Object)gpuTexture);
                GpuTexture gpuTexture2 = depthCopyTexture;
                Intrinsics.checkNotNull((Object)gpuTexture2);
                commandEncoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, renderTarget.textureWidth, renderTarget.textureHeight);
            }
            float distance = distanceBlocks;
            float farPlane = INSTANCE.viewDistance();
            if (INSTANCE.isUnderwaterView()) {
                float underwaterFar = Math.min(farPlane, 32.0f);
                distance *= underwaterFar / farPlane;
                farPlane = underwaterFar;
            }
            float maxStartDistance = Math.max(1.0f, farPlane * 0.9f);
            float startDistance = INSTANCE.clamp(distance, 1.0f, maxStartDistance);
            float rampEnd = Math.min(startDistance + Math.max(48.0f, (farPlane - startDistance) * 0.6f), farPlane * 0.99f);
            float minThreshold = INSTANCE.clamp(startDistance / farPlane, 0.0f, 0.985f);
            float maxThreshold = INSTANCE.clamp(rampEnd / farPlane, minThreshold + 0.01f, 1.0f);
            GpuTextureView fogDepthView = hasOpaqueSnapshot ? opaqueDepthTextureView : depthCopyTextureView;
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            INSTANCE.writeCompositeUniform(encoder, 0.0f, 0.0f, 0.0f, opacity, 0.05f, farPlane, minThreshold, maxThreshold);
            float offsetScale = INSTANCE.clamp(0.6f + strength * 0.0325f, 0.6f, 1.25f);
            GpuTextureView prepared = INSTANCE.renderPrepare(encoder, renderTarget.getColorAttachmentView(), fogDepthView);
            GpuTextureView blurred = INSTANCE.renderBlurChain(prepared, renderTarget.textureWidth, renderTarget.textureHeight, passes, offsetScale);
            SimpleFramebuffer simpleFramebuffer2 = downTargets[0];
            INSTANCE.composite(renderTarget.getColorAttachmentView(), (GpuTextureView)(simpleFramebuffer2 != null ? simpleFramebuffer2.getColorAttachmentView() : null), blurred, fogDepthView, opacity, farPlane, minThreshold, maxThreshold);
        }
        catch (Throwable throwable) {
            disabledAfterError = true;
            INSTANCE.closeTargets();
            INSTANCE.closeCompositeBuffer();
            INSTANCE.closeKawaseBuffer();
        }
    }

    private final void init() {
        if (disabledAfterError) {
            return;
        }
        try {
            GpuBuffer kawase;
            GpuBuffer composite;
            if (kawaseDownPipeline == null) {
                kawaseDownPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(KAWASE_DOWN_PIPELINE_ID).withVertexShader(KAWASE_VERTEX).withFragmentShader(KAWASE_DOWN_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("KawaseParams", UniformType.UNIFORM_BUFFER).withSampler("Sampler0").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (kawaseUpPipeline == null) {
                kawaseUpPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(KAWASE_UP_PIPELINE_ID).withVertexShader(KAWASE_VERTEX).withFragmentShader(KAWASE_UP_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("KawaseParams", UniformType.UNIFORM_BUFFER).withSampler("Sampler0").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (preparePipeline == null) {
                preparePipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(PREPARE_PIPELINE_ID).withVertexShader(this.id("post/fogblur/fogblur")).withFragmentShader(PREPARE_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("FogBlurData", UniformType.UNIFORM_BUFFER).withSampler("SceneSampler").withSampler("DepthSampler").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (compositePipeline == null) {
                compositePipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(COMPOSITE_PIPELINE_ID).withVertexShader(this.id("post/fogblur/fogblur")).withFragmentShader(COMPOSITE_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("FogBlurData", UniformType.UNIFORM_BUFFER).withUniform("ThemeWaveParams", UniformType.UNIFORM_BUFFER).withSampler("BlurMediumSampler").withSampler("BlurStrongSampler").withSampler("DepthSampler").withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if ((composite = compositeUniformBuffer) == null || composite.isClosed() || composite.size() < 64L) {
                this.closeCompositeBuffer();
                compositeUniformBuffer = RenderSystem.getDevice().createBuffer(FogBlurRenderer::init$lambda$0, 136, 64L);
            }
            if ((kawase = kawaseUniformBuffer) == null || kawase.isClosed() || kawase.size() < 48L) {
                this.closeKawaseBuffer();
                kawaseUniformBuffer = RenderSystem.getDevice().createBuffer(FogBlurRenderer::init$lambda$1, 136, 48L);
            }
        }
        catch (Throwable throwable) {
            disabledAfterError = true;
            kawaseDownPipeline = null;
            kawaseUpPipeline = null;
            compositePipeline = null;
            preparePipeline = null;
            this.closeCompositeBuffer();
            this.closeKawaseBuffer();
        }
    }

    private final boolean ensureTargets(int width, int height, float renderScale, int passes) {
        if (RenderSystem.tryGetDevice() == null) {
            return false;
        }
        int targetWidth = Math.max(1, Math.round((float)width * this.clamp(renderScale, 0.25f, 0.75f)));
        int targetHeight = Math.max(1, Math.round((float)height * this.clamp(renderScale, 0.25f, 0.75f)));
        if (prepareTarget != null && sceneWidth == width && sceneHeight == height && blurWidth == targetWidth && blurHeight == targetHeight && allocatedPasses >= passes) {
            return true;
        }
        this.closeTargets();
        prepareTarget = this.ensureTarget(prepareTarget, "kimiko_fog_blur_prepare", width, height);
        int currentWidth = targetWidth;
        int currentHeight = targetHeight;
        for (int i = 0; i < passes; ++i) {
            FogBlurRenderer.downTargets[i] = this.ensureTarget(downTargets[i], "kimiko_fog_blur_down_" + i, currentWidth, currentHeight);
            if (i < passes - 1) {
                FogBlurRenderer.upTargets[i] = this.ensureTarget(upTargets[i], "kimiko_fog_blur_up_" + i, currentWidth, currentHeight);
            }
            currentWidth = Math.max(currentWidth / 2, 1);
            currentHeight = Math.max(currentHeight / 2, 1);
        }
        sceneWidth = width;
        sceneHeight = height;
        blurWidth = targetWidth;
        blurHeight = targetHeight;
        allocatedPasses = passes;
        return true;
    }

    private final void ensureDepthCopy(int width, int height) {
        if (depthCopyTexture != null) {
            return;
        }
        GpuDevice gpuDevice = RenderSystem.tryGetDevice();
        if (gpuDevice == null) {
            return;
        }
        GpuDevice device = gpuDevice;
        GpuTexture gpuTexture = depthCopyTexture = device.createTexture(FogBlurRenderer::ensureDepthCopy$lambda$0, 5, TextureFormat.DEPTH32, width, height, 1, 1);
        Intrinsics.checkNotNull((Object)gpuTexture);
        depthCopyTextureView = device.createTextureView(gpuTexture);
    }

    private final SimpleFramebuffer ensureTarget(SimpleFramebuffer target, String name, int width, int height) {
        if (target == null) {
            return new SimpleFramebuffer(name, width, height, false);
        }
        if (target.textureWidth != width || target.textureHeight != height) {
            target.resize(width, height);
        }
        return target;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final GpuTextureView renderPrepare(CommandEncoder encoder, GpuTextureView scene, GpuTextureView depth) {
        SimpleFramebuffer simpleFramebuffer2 = prepareTarget;
        if (simpleFramebuffer2 == null) {
            return null;
        }
        SimpleFramebuffer target = simpleFramebuffer2;
        if (scene == null || depth == null) {
            return null;
        }
        Supplier<String> supplier = FogBlurRenderer::renderPrepare$lambda$0;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
        Throwable throwable = null;
        try {
            RenderPass renderPass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = preparePipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            renderPass.setPipeline(renderPipeline);
            GpuBuffer gpuBuffer = compositeUniformBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            renderPass.setUniform("FogBlurData", gpuBuffer);
            renderPass.bindTexture("SceneSampler", scene, RenderSampler.nearest());
            renderPass.bindTexture("DepthSampler", depth, RenderSampler.nearest());
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
        return target.getColorAttachmentView();
    }

    private final GpuTextureView renderBlurChain(GpuTextureView source, int sourceWidth, int sourceHeight, int passes, float offsetScale) {
        if (source == null) {
            return null;
        }
        GpuTextureView currentSource = source;
        int currentWidth = sourceWidth;
        int currentHeight = sourceHeight;
        GpuSampler sampler = RenderSampler.linear();
        for (int i = 0; i < passes; ++i) {
            SimpleFramebuffer target = downTargets[i];
            RenderPipeline renderPipeline = kawaseDownPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            this.renderKawasePass(renderPipeline, currentSource, currentWidth, currentHeight, target, offsetScale, 0.0f, 0.0f, 1.0f, 1.0f, sampler);
            currentSource = target.getColorAttachmentView();
            currentWidth = target.textureWidth;
            currentHeight = target.textureHeight;
        }
        for (int i = passes - 2; i >= 0; --i) {
            SimpleFramebuffer target = upTargets[i];
            RenderPipeline renderPipeline = kawaseUpPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            this.renderKawasePass(renderPipeline, currentSource, currentWidth, currentHeight, target, offsetScale, 0.0f, 0.0f, 1.0f, 1.0f, sampler);
            currentSource = target.getColorAttachmentView();
            currentWidth = target.textureWidth;
            currentHeight = target.textureHeight;
        }
        return currentSource;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void renderKawasePass(RenderPipeline pipeline, GpuTextureView source, int sourceWidth, int sourceHeight, SimpleFramebuffer target, float offsetScale, float sourceX, float sourceY, float sourceW, float sourceH, GpuSampler sampler) {
        if (source == null || target == null || target.getColorAttachmentView() == null) {
            return;
        }
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(48);
            data.putFloat(0, sourceX);
            data.putFloat(4, sourceY);
            data.putFloat(8, sourceW);
            data.putFloat(12, sourceH);
            data.putFloat(16, offsetScale / (float)Math.max(sourceWidth, 1));
            data.putFloat(20, offsetScale / (float)Math.max(sourceHeight, 1));
            data.putFloat(32, fallbackRed);
            data.putFloat(36, fallbackGreen);
            data.putFloat(40, fallbackBlue);
            data.putFloat(44, 1.0f);
            data.position(0);
            GpuBuffer gpuBuffer = kawaseUniformBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 48L), data);
            Supplier<String> supplier = FogBlurRenderer::renderKawasePass$lambda$0$0;
            GpuTextureView gpuTextureView = target.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            AutoCloseable autoCloseable2 = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0));
            Throwable throwable2 = null;
            try {
                RenderPass renderPass = (RenderPass)autoCloseable2;
                boolean bl2 = false;
                renderPass.setPipeline(pipeline);
                renderPass.bindTexture("Sampler0", source, sampler);
                GpuBuffer gpuBuffer2 = kawaseUniformBuffer;
                Intrinsics.checkNotNull((Object)gpuBuffer2);
                renderPass.setUniform("KawaseParams", gpuBuffer2);
                renderPass.draw(0, 6);
                Unit unit = Unit.INSTANCE;
            }
            catch (Throwable throwable3) {
                throwable2 = throwable3;
                throw throwable3;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable2, (Throwable)throwable2);
            }
            Unit unit = Unit.INSTANCE;
        }
        catch (Throwable throwable4) {
            throwable = throwable4;
            throw throwable4;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void composite(GpuTextureView target, GpuTextureView medium, GpuTextureView strong, GpuTextureView depth, float opacity, float farPlane, float minThreshold, float maxThreshold) {
        if (target == null || strong == null || depth == null || opacity <= 0.0f) {
            return;
        }
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(FogBlurRenderer::composite$lambda$0, target, OptionalInt.empty());
        Throwable throwable = null;
        try {
            RenderPass renderPass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = compositePipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            renderPass.setPipeline(renderPipeline);
            GpuBuffer gpuBuffer = compositeUniformBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            renderPass.setUniform("FogBlurData", gpuBuffer);
            ThemeWaveUniform.bind(renderPass);
            GpuTextureView gpuTextureView = medium;
            if (gpuTextureView == null) {
                gpuTextureView = strong;
            }
            renderPass.bindTexture("BlurMediumSampler", gpuTextureView, RenderSampler.linear());
            renderPass.bindTexture("BlurStrongSampler", strong, RenderSampler.linear());
            renderPass.bindTexture("DepthSampler", depth, RenderSampler.nearest());
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void writeCompositeUniform(CommandEncoder encoder, float texelX, float texelY, float offset, float opacity, float nearPlane, float farPlane, float minThreshold, float maxThreshold) {
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(64);
            data.putFloat(0, texelX);
            data.putFloat(4, texelY);
            data.putFloat(8, offset);
            data.putFloat(12, opacity);
            data.putFloat(16, nearPlane);
            data.putFloat(20, farPlane);
            data.putFloat(24, minThreshold);
            data.putFloat(28, maxThreshold);
            data.putFloat(32, tintRed);
            data.putFloat(36, tintGreen);
            data.putFloat(40, tintBlue);
            data.putFloat(44, tintMix);
            data.putFloat(48, tintFromClientTheme ? 1.0f : 0.0f);
            data.position(0);
            GpuBuffer gpuBuffer = compositeUniformBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 64L), data);
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

    private final int computePasses(float strength, int maxPasses) {
        int requested = strength >= 14.0f ? 4 : (strength >= 8.0f ? 3 : (strength >= 3.0f ? 2 : 1));
        return Math.clamp((long)requested, 1, Math.max(1, maxPasses));
    }

    private final float viewDistance() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.options == null) {
            return 192.0f;
        }
        return Math.max(96.0f, (float)(((Number)minecraft.options.getViewDistance().getValue()).intValue() + 1) * 16.0f);
    }

    private final boolean isUnderwaterView() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.gameRenderer == null || minecraft.gameRenderer.getCamera() == null) {
            return false;
        }
        return minecraft.gameRenderer.getCamera().getSubmersionType() == CameraSubmersionType.WATER;
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
        SimpleFramebuffer simpleFramebuffer2 = prepareTarget;
        if (simpleFramebuffer2 != null) {
            simpleFramebuffer2.delete();
        }
        prepareTarget = null;
        for (int i = 0; i < 4; ++i) {
            SimpleFramebuffer simpleFramebuffer3 = downTargets[i];
            if (simpleFramebuffer3 != null) {
                simpleFramebuffer3.delete();
            }
            FogBlurRenderer.downTargets[i] = null;
            SimpleFramebuffer simpleFramebuffer4 = upTargets[i];
            if (simpleFramebuffer4 != null) {
                simpleFramebuffer4.delete();
            }
            FogBlurRenderer.upTargets[i] = null;
        }
        this.closeOpaqueDepth();
        sceneWidth = -1;
        sceneHeight = -1;
        blurWidth = -1;
        blurHeight = -1;
        allocatedPasses = 0;
    }

    private final void closeOpaqueDepth() {
        GpuTextureView gpuTextureView = opaqueDepthTextureView;
        if (gpuTextureView != null) {
            gpuTextureView.close();
        }
        opaqueDepthTextureView = null;
        GpuTexture gpuTexture = opaqueDepthTexture;
        if (gpuTexture != null) {
            gpuTexture.close();
        }
        opaqueDepthTexture = null;
        opaqueDepthWidth = -1;
        opaqueDepthHeight = -1;
        opaqueCaptured = false;
    }

    private final void closeCompositeBuffer() {
        GpuBuffer gpuBuffer = compositeUniformBuffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        compositeUniformBuffer = null;
    }

    private final void closeKawaseBuffer() {
        GpuBuffer gpuBuffer = kawaseUniformBuffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        kawaseUniformBuffer = null;
    }

    private final float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }

    private static final String captureOpaqueDepth$lambda$0() {
        return "kimiko:fog_blur_opaque_depth";
    }

    private static final String init$lambda$0() {
        return "kimiko:fog_blur_uniforms";
    }

    private static final String init$lambda$1() {
        return "kimiko:fog_blur_kawase_uniforms";
    }

    private static final String ensureDepthCopy$lambda$0() {
        return "kimiko:fog_blur_depth_copy";
    }

    private static final String renderPrepare$lambda$0() {
        return "kimiko:fog_blur_prepare";
    }

    private static final String renderKawasePass$lambda$0$0() {
        return "kimiko:fog_blur_kawase";
    }

    private static final String composite$lambda$0() {
        return "kimiko:fog_blur_composite";
    }

    static {
        downTargets = new SimpleFramebuffer[4];
        upTargets = new SimpleFramebuffer[4];
        sceneWidth = -1;
        sceneHeight = -1;
        blurWidth = -1;
        blurHeight = -1;
        opaqueDepthWidth = -1;
        opaqueDepthHeight = -1;
    }
}

