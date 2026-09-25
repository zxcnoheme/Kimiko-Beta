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
 *  com.mojang.blaze3d.systems.RenderPass
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.systems.RenderSystem$ShapeIndexBuffer
 *  com.mojang.blaze3d.textures.FilterMode
 *  com.mojang.blaze3d.textures.GpuTexture
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.vertex.VertexFormat
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  com.mojang.blaze3d.vertex.VertexFormatElement
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.client.gl.GpuSampler
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gl.SimpleFramebuffer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.lwjgl.system.MemoryStack
 *  org.lwjgl.system.MemoryUtil
 */
package rtx.kimiko.utils.render.render3d;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.SimpleFramebuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfTextGeometry;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00b8\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b'\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0011\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\"\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003JY\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\tH\u0007b\u0002\b\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012Js\u0010 \u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\tH\u0007b\u0002\b\u0010\u00a2\u0006\u0004\b \u0010!JO\u0010+\u001a\u00020\u000f2\b\u0010#\u001a\u0004\u0018\u00010\"2\b\u0010%\u001a\u0004\u0018\u00010$2\u0006\u0010&\u001a\u00020\t2\u0006\u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020\t2\u0006\u0010)\u001a\u00020\t2\u0006\u0010*\u001a\u00020\tH\u0007b\u0002\b\u0010\u00a2\u0006\u0004\b+\u0010,J\u001b\u0010/\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020-H\u0007b\u0002\b\u0010\u00a2\u0006\u0004\b/\u00100JO\u0010>\u001a\u00020\u000f2\u0006\u00102\u001a\u0002012\u0006\u00104\u001a\u0002032\u0006\u00106\u001a\u0002052\u0006\u00108\u001a\u0002072\u0006\u0010:\u001a\u0002092\u0006\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020;2\u0006\u0010.\u001a\u00020-H\u0002\u00a2\u0006\u0004\b>\u0010?JI\u0010G\u001a\u00020\u000f2\u0006\u00102\u001a\u0002012\u0006\u0010A\u001a\u00020@2\b\u0010C\u001a\u0004\u0018\u00010B2\u0006\u0010D\u001a\u00020;2\u0006\u0010E\u001a\u00020;2\u0006\u00104\u001a\u00020F2\u0006\u00106\u001a\u000205H\u0002\u00a2\u0006\u0004\bG\u0010HJ\u001f\u0010K\u001a\u00020-2\u0006\u0010I\u001a\u00020;2\u0006\u0010J\u001a\u00020;H\u0002\u00a2\u0006\u0004\bK\u0010LJ\u000f\u0010M\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bM\u0010\u0003J?\u0010T\u001a\u00020\u000f2\u0006\u0010N\u001a\u00020;2\u0006\u0010O\u001a\u00020\t2\u0006\u0010P\u001a\u00020\t2\u0006\u0010Q\u001a\u00020\t2\u0006\u0010R\u001a\u00020\t2\u0006\u0010S\u001a\u00020;H\u0002\u00a2\u0006\u0004\bT\u0010UJ\u0017\u0010W\u001a\u0002092\u0006\u0010V\u001a\u00020;H\u0002\u00a2\u0006\u0004\bW\u0010XJ\u0017\u0010Z\u001a\u00020-2\u0006\u0010Y\u001a\u00020;H\u0002\u00a2\u0006\u0004\bZ\u0010[J\u000f\u0010\\\u001a\u00020-H\u0002\u00a2\u0006\u0004\b\\\u0010]J\u001f\u0010a\u001a\u00020@2\u0006\u0010_\u001a\u00020^2\u0006\u0010`\u001a\u00020^H\u0002\u00a2\u0006\u0004\ba\u0010bJ\u001f\u0010A\u001a\u00020@2\u0006\u0010_\u001a\u00020^2\u0006\u0010d\u001a\u00020cH\u0002\u00a2\u0006\u0004\bA\u0010eJ\u0013\u0010f\u001a\u00020\u000fH\u0007b\u0002\b\u0010\u00a2\u0006\u0004\bf\u0010\u0003J\u0017\u0010i\u001a\u00020^2\u0006\u0010h\u001a\u00020gH\u0002\u00a2\u0006\u0004\bi\u0010jJ\u000f\u0010k\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bk\u0010\u0003R\u0014\u0010l\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bl\u0010mR\u0014\u0010n\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bn\u0010mR\u0014\u0010o\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bo\u0010mR\u0014\u0010p\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bp\u0010mR\u0014\u0010q\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bq\u0010mR\u0014\u0010r\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\br\u0010mR\u0014\u0010s\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bs\u0010mR\u0014\u0010t\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bt\u0010mR\u0014\u0010u\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bu\u0010mR\u0014\u0010v\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bv\u0010mR\u0014\u0010w\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bw\u0010mR\u0014\u0010x\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bx\u0010mR\u0014\u0010y\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\by\u0010mR\u0014\u0010z\u001a\u00020g8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bz\u0010{R\u0014\u0010|\u001a\u00020g8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b|\u0010{R\u0014\u0010}\u001a\u00020g8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b}\u0010{R\u0014\u0010~\u001a\u00020;8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b~\u0010\u007fR\u0016\u0010\u0080\u0001\u001a\u00020;8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010\u007fR\u0016\u0010\u0081\u0001\u001a\u00020;8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0081\u0001\u0010\u007fR\u0016\u0010\u0082\u0001\u001a\u00020;8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0082\u0001\u0010\u007fR\u0016\u0010\u0083\u0001\u001a\u00020;8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0083\u0001\u0010\u007fR\u0016\u0010\u0084\u0001\u001a\u00020;8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0084\u0001\u0010\u007fR\u0016\u0010\u0085\u0001\u001a\u00020;8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0085\u0001\u0010\u007fR\u0017\u0010\u0086\u0001\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0017\u0010\u0088\u0001\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0088\u0001\u0010\u0087\u0001R\u0016\u0010\u0089\u0001\u001a\u00020;8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0089\u0001\u0010\u007fR\u0017\u0010\u008a\u0001\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008a\u0001\u0010\u0087\u0001R\u0017\u0010\u008b\u0001\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008b\u0001\u0010\u0087\u0001R\u0017\u0010\u008c\u0001\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008c\u0001\u0010\u0087\u0001R\u0016\u0010\u008d\u0001\u001a\u00020;8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u008d\u0001\u0010\u007fR\u0016\u0010\u008e\u0001\u001a\u00020;8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u008e\u0001\u0010\u007fR\u0018\u0010\u0090\u0001\u001a\u00030\u008f\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0090\u0001\u0010\u0091\u0001R\u0017\u0010\u0092\u0001\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0092\u0001\u0010\u0093\u0001R\u001b\u0010\u0094\u0001\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0094\u0001\u0010\u0095\u0001R\u001b\u0010\u0096\u0001\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0096\u0001\u0010\u0095\u0001R\u001b\u0010\u0097\u0001\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0097\u0001\u0010\u0095\u0001R\u001b\u0010\u0098\u0001\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0098\u0001\u0010\u0095\u0001R\u001b\u0010\u0099\u0001\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0099\u0001\u0010\u0095\u0001R\u001b\u0010\u009a\u0001\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u0095\u0001R\u001b\u0010\u009b\u0001\u001a\u0004\u0018\u0001098\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009b\u0001\u0010\u009c\u0001R\u001b\u0010\u009d\u0001\u001a\u0004\u0018\u0001098\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009d\u0001\u0010\u009c\u0001R\u001b\u0010\u009e\u0001\u001a\u0004\u0018\u00010F8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009e\u0001\u0010\u009f\u0001R \u0010\u00a1\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010F0\u00a0\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a1\u0001\u0010\u00a2\u0001R \u0010\u00a3\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010F0\u00a0\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a3\u0001\u0010\u00a2\u0001R\u0018\u0010\u00a4\u0001\u001a\u00020;8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a4\u0001\u0010\u007fR\u0018\u0010\u00a5\u0001\u001a\u00020;8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a5\u0001\u0010\u007fR\u0019\u0010\u00a6\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a6\u0001\u0010\u0087\u0001R\u001b\u0010\u00a7\u0001\u001a\u0004\u0018\u0001098\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a7\u0001\u0010\u009c\u0001R\u001b\u0010\u00a8\u0001\u001a\u0004\u0018\u0001098\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a8\u0001\u0010\u009c\u0001R \u0010\u00a9\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u0001090\u00a0\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a9\u0001\u0010\u00aa\u0001R\u001c\u0010\u00ac\u0001\u001a\u0005\u0018\u00010«\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ac\u0001\u0010\u00ad\u0001R\u001c\u0010\u00ae\u0001\u001a\u0005\u0018\u00010«\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ae\u0001\u0010\u00ad\u0001R\u001c\u0010\u00af\u0001\u001a\u0005\u0018\u00010«\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00af\u0001\u0010\u00ad\u0001R\u0018\u0010\u00b0\u0001\u001a\u00020;8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00b0\u0001\u0010\u007fR\u0018\u0010\u00b1\u0001\u001a\u00020;8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00b1\u0001\u0010\u007fR\u0019\u0010\u00b2\u0001\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b2\u0001\u0010\u00b3\u0001R\u0019\u0010\u00b4\u0001\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b4\u0001\u0010\u00b3\u0001R\u001b\u0010\u00b5\u0001\u001a\u0004\u0018\u00010B8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b5\u0001\u0010\u00b6\u0001R\u0019\u0010\u00b7\u0001\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b7\u0001\u0010\u00b8\u0001R\u0019\u0010\u00b9\u0001\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b9\u0001\u0010\u00b8\u0001R\u0019\u0010\u00ba\u0001\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ba\u0001\u0010\u00b8\u0001R\u0019\u0010»\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b»\u0001\u0010\u0087\u0001R\u0019\u0010\u00bc\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00bc\u0001\u0010\u0087\u0001R\u0019\u0010\u00bd\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00bd\u0001\u0010\u0087\u0001R\u0019\u0010\u00be\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00be\u0001\u0010\u0087\u0001R\u0019\u0010\u00bf\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00bf\u0001\u0010\u0087\u0001R\u0019\u0010\u00c0\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c0\u0001\u0010\u0087\u0001R\u0019\u0010\u00c1\u0001\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c1\u0001\u0010\u00b8\u0001R\u0019\u0010\u00c2\u0001\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c2\u0001\u0010\u00b8\u0001R\u0019\u0010\u00c3\u0001\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c3\u0001\u0010\u00b8\u0001R\u0019\u0010\u00c4\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c4\u0001\u0010\u0087\u0001R\u0019\u0010\u00c5\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c5\u0001\u0010\u0087\u0001R\u0019\u0010\u00c6\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c6\u0001\u0010\u0087\u0001R\u0019\u0010\u00c7\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c7\u0001\u0010\u0087\u0001R\u0019\u0010\u00c8\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c8\u0001\u0010\u0087\u0001R\u0019\u0010\u00c9\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c9\u0001\u0010\u0087\u0001R\u0019\u0010\u00ca\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ca\u0001\u0010\u0087\u0001R\u0019\u0010\u00cb\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00cb\u0001\u0010\u0087\u0001R\u0019\u0010\u00cc\u0001\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00cc\u0001\u0010\u0087\u0001\u00a8\u0006\u00cd\u0001"}, d2={"Lrtx/kimiko/utils/render/render3d/Text3D;", "", "<init>", "()V", "Lorg/joml/Matrix4f;", "modelView", "projection", "Lnet/minecraft/Vec3d;", "camera", "", "gray", "boost", "glow", "glowSpread", "blurScale", "", "Lkotlin/jvm/JvmStatic;", "begin", "(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/Vec3d;FFFFF)V", "", "x", "y", "z", "toRightX", "toRightY", "toRightZ", "toUpX", "toUpY", "toUpZ", "scale", "centerX", "centerY", "plane", "(DDDFFFFFFFFF)V", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextGeometry$Layout;", "layout", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextGeometry$Quad;", "quad", "shiftX", "shiftY", "blur", "heat", "alpha", "glyph", "(Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextGeometry$Layout;Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextGeometry$Quad;FFFFF)V", "", "throughWalls", "end", "(Z)V", "Lcom/mojang/blaze3d/systems/CommandEncoder;", "encoder", "Lnet/minecraft/Framebuffer;", "target", "Lnet/minecraft/GpuSampler;", "sampler", "Lcom/mojang/blaze3d/systems/RenderSystem$ShapeIndexBuffer;", "indices", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "indexBuffer", "", "total", "pages", "glowChain", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lnet/minecraft/Framebuffer;Lnet/minecraft/GpuSampler;Lcom/mojang/blaze3d/systems/RenderSystem$ShapeIndexBuffer;Lcom/mojang/blaze3d/buffers/GpuBuffer;IIZ)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "source", "sourceWidth", "sourceHeight", "Lnet/minecraft/SimpleFramebuffer;", "kawase", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lcom/mojang/blaze3d/textures/GpuTextureView;IILnet/minecraft/SimpleFramebuffer;Lnet/minecraft/GpuSampler;)V", "width", "height", "ensureGlowTargets", "(II)Z", "closeGlowTargets", "cursor", "localX", "localY", "u", "v", "slot", "emit", "(IFFFFI)V", "page", "glyphBuffer", "(I)Lcom/mojang/blaze3d/buffers/GpuBuffer;", "required", "ensureCapacity", "(I)Z", "ensureReady", "()Z", "Lnet/minecraft/Identifier;", "location", "fragment", "kawasePipeline", "(Lnet/minecraft/Identifier;Lnet/minecraft/Identifier;)Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lcom/mojang/blaze3d/platform/DepthTestFunction;", "depth", "(Lnet/minecraft/Identifier;Lcom/mojang/blaze3d/platform/DepthTestFunction;)Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "invalidate", "", "path", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "disable", "DEPTH_PIPELINE_ID", "Lnet/minecraft/Identifier;", "OVERLAY_PIPELINE_ID", "SHADER", "GLOW_SHADER", "GLOW_COMPOSITE_SHADER", "GLOW_PIPELINE_ID", "GLOW_DOWN_PIPELINE_ID", "GLOW_UP_PIPELINE_ID", "GLOW_COMPOSITE_PIPELINE_ID", "FULLSCREEN_VERTEX", "KAWASE_VERTEX", "KAWASE_DOWN_SHADER", "KAWASE_UP_SHADER", "UNIFORM_BLOCK", "Ljava/lang/String;", "GLYPH_BLOCK", "SAMPLER", "UNIFORM_BYTES", "I", "VERTEX_BYTES", "QUAD_BYTES", "PAGE_QUADS", "GLYPH_BYTES", "MAX_PAGES", "MAX_QUADS", "HALO_MARGIN", "F", "MIN_ALPHA", "GLOW_PASSES", "GLOW_RENDER_SCALE", "GLOW_OFFSET_SCALE", "GLOW_GAIN", "KAWASE_UNIFORM_BYTES", "GLOW_UNIFORM_BYTES", "Lcom/mojang/blaze3d/vertex/VertexFormat;", "FORMAT", "Lcom/mojang/blaze3d/vertex/VertexFormat;", "VIEW_PROJECTION", "Lorg/joml/Matrix4f;", "depthPipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "overlayPipeline", "glowSourcePipeline", "glowDownPipeline", "glowUpPipeline", "glowCompositePipeline", "kawaseBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "glowParamsBuffer", "glowTarget", "Lnet/minecraft/SimpleFramebuffer;", "", "glowDownTargets", "[Lnet/minecraft/SimpleFramebuffer;", "glowUpTargets", "glowWidth", "glowHeight", "maxHeat", "uniformBuffer", "vertexBuffer", "glyphBuffers", "[Lcom/mojang/blaze3d/buffers/GpuBuffer;", "Ljava/nio/ByteBuffer;", "uniformData", "Ljava/nio/ByteBuffer;", "glyphData", "vertexData", "capacity", "quads", "active", "Z", "failed", "atlasView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "cameraX", "D", "cameraY", "cameraZ", "grayLevel", "heatBoost", "glowStrength", "glowRadius", "blurReference", "uvScale", "planeX", "planeY", "planeZ", "rightX", "rightY", "rightZ", "upX", "upY", "upZ", "planeScale", "planeCenterX", "planeCenterY", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nText3D.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Text3D.kt\nrtx/kimiko/utils/render/render3d/Text3D\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,693:1\n1#2:694\n*E\n"})
public final class Text3D {
    @NotNull
    public static final Text3D INSTANCE = new Text3D();
    @NotNull
    private static final Identifier DEPTH_PIPELINE_ID = INSTANCE.id("pipeline/effects/lyrics_text");
    @NotNull
    private static final Identifier OVERLAY_PIPELINE_ID = INSTANCE.id("pipeline/effects/lyrics_text_overlay");
    @NotNull
    private static final Identifier SHADER = INSTANCE.id("effects/lyrics_text/lyrics_text");
    @NotNull
    private static final Identifier GLOW_SHADER = INSTANCE.id("effects/lyrics_text/lyrics_glow");
    @NotNull
    private static final Identifier GLOW_COMPOSITE_SHADER = INSTANCE.id("effects/lyrics_text/lyrics_glow_composite");
    @NotNull
    private static final Identifier GLOW_PIPELINE_ID = INSTANCE.id("pipeline/effects/lyrics_glow");
    @NotNull
    private static final Identifier GLOW_DOWN_PIPELINE_ID = INSTANCE.id("pipeline/effects/lyrics_glow_down");
    @NotNull
    private static final Identifier GLOW_UP_PIPELINE_ID = INSTANCE.id("pipeline/effects/lyrics_glow_up");
    @NotNull
    private static final Identifier GLOW_COMPOSITE_PIPELINE_ID = INSTANCE.id("pipeline/effects/lyrics_glow_composite");
    @NotNull
    private static final Identifier FULLSCREEN_VERTEX = INSTANCE.id("post/targetcircle/fullscreen");
    @NotNull
    private static final Identifier KAWASE_VERTEX = INSTANCE.id("post/fogblur/kawase");
    @NotNull
    private static final Identifier KAWASE_DOWN_SHADER = INSTANCE.id("ui/kawase/down");
    @NotNull
    private static final Identifier KAWASE_UP_SHADER = INSTANCE.id("ui/kawase/up");
    @NotNull
    private static final String UNIFORM_BLOCK = "LyricsTextData";
    @NotNull
    private static final String GLYPH_BLOCK = "LyricsGlyphArray";
    @NotNull
    private static final String SAMPLER = "Atlas";
    private static final int UNIFORM_BYTES = 80;
    private static final int VERTEX_BYTES = 24;
    private static final int QUAD_BYTES = 96;
    private static final int PAGE_QUADS = 256;
    private static final int GLYPH_BYTES = 8192;
    private static final int MAX_PAGES = 8;
    private static final int MAX_QUADS = 2048;
    private static final float HALO_MARGIN = 1.15f;
    private static final float MIN_ALPHA = 0.004f;
    private static final int GLOW_PASSES = 2;
    private static final float GLOW_RENDER_SCALE = 0.5f;
    private static final float GLOW_OFFSET_SCALE = 1.25f;
    private static final float GLOW_GAIN = 0.5f;
    private static final int KAWASE_UNIFORM_BYTES = 48;
    private static final int GLOW_UNIFORM_BYTES = 16;
    @NotNull
    private static final VertexFormat FORMAT;
    @NotNull
    private static final Matrix4f VIEW_PROJECTION;
    @Nullable
    private static RenderPipeline depthPipeline;
    @Nullable
    private static RenderPipeline overlayPipeline;
    @Nullable
    private static RenderPipeline glowSourcePipeline;
    @Nullable
    private static RenderPipeline glowDownPipeline;
    @Nullable
    private static RenderPipeline glowUpPipeline;
    @Nullable
    private static RenderPipeline glowCompositePipeline;
    @Nullable
    private static GpuBuffer kawaseBuffer;
    @Nullable
    private static GpuBuffer glowParamsBuffer;
    @Nullable
    private static SimpleFramebuffer glowTarget;
    @NotNull
    private static final SimpleFramebuffer[] glowDownTargets;
    @NotNull
    private static final SimpleFramebuffer[] glowUpTargets;
    private static int glowWidth;
    private static int glowHeight;
    private static float maxHeat;
    @Nullable
    private static GpuBuffer uniformBuffer;
    @Nullable
    private static GpuBuffer vertexBuffer;
    @NotNull
    private static final GpuBuffer[] glyphBuffers;
    @Nullable
    private static ByteBuffer uniformData;
    @Nullable
    private static ByteBuffer glyphData;
    @Nullable
    private static ByteBuffer vertexData;
    private static int capacity;
    private static int quads;
    private static boolean active;
    private static boolean failed;
    @Nullable
    private static GpuTextureView atlasView;
    private static double cameraX;
    private static double cameraY;
    private static double cameraZ;
    private static float grayLevel;
    private static float heatBoost;
    private static float glowStrength;
    private static float glowRadius;
    private static float blurReference;
    private static float uvScale;
    private static double planeX;
    private static double planeY;
    private static double planeZ;
    private static float rightX;
    private static float rightY;
    private static float rightZ;
    private static float upX;
    private static float upY;
    private static float upZ;
    private static float planeScale;
    private static float planeCenterX;
    private static float planeCenterY;

    private Text3D() {
    }

    @JvmStatic
    public static final void begin(@Nullable Matrix4f modelView, @Nullable Matrix4f projection, @Nullable Vec3d camera, float gray, float boost, float glow, float glowSpread, float blurScale) {
        active = false;
        if (failed || modelView == null || projection == null || camera == null || !INSTANCE.ensureReady()) {
            return;
        }
        VIEW_PROJECTION.set((Matrix4fc)projection).mul((Matrix4fc)modelView);
        cameraX = camera.x;
        cameraY = camera.y;
        cameraZ = camera.z;
        grayLevel = gray;
        heatBoost = boost;
        glowStrength = Math.max(0.0f, glow);
        glowRadius = glowStrength > 0.0f ? Math.max(0.0f, glowSpread) : 0.0f;
        blurReference = Math.max(0.001f, blurScale);
        uvScale = 0.0f;
        maxHeat = 0.0f;
        atlasView = null;
        quads = 0;
        active = true;
    }

    @JvmStatic
    public static final void plane(double x, double y, double z, float toRightX, float toRightY, float toRightZ, float toUpX, float toUpY, float toUpZ, float scale, float centerX, float centerY) {
        planeX = x;
        planeY = y;
        planeZ = z;
        rightX = toRightX;
        rightY = toRightY;
        rightZ = toRightZ;
        upX = toUpX;
        upY = toUpY;
        upZ = toUpZ;
        planeScale = scale;
        planeCenterX = centerX;
        planeCenterY = centerY;
    }

    @JvmStatic
    public static final void glyph(@Nullable MsdfTextGeometry.Layout layout, @Nullable MsdfTextGeometry.Quad quad, float shiftX, float shiftY, float blur, float heat, float alpha) {
        if (!active || layout == null || quad == null || alpha <= 0.004f) {
            return;
        }
        GpuTextureView view = layout.atlas();
        if (view == null || atlasView != null && atlasView != view || quads >= 2048) {
            return;
        }
        if (!INSTANCE.ensureCapacity(quads + 1)) {
            return;
        }
        atlasView = view;
        float localWidth = quad.x1() - quad.x0();
        float uvWidth = quad.u1() - quad.u0();
        float uvPerLocal = localWidth > 1.0E-5f ? uvWidth / localWidth : 0.0f;
        float radius = blur * uvPerLocal;
        float padLocal = blur > 0.0f ? blur * 1.15f : 0.0f;
        float padUv = padLocal * uvPerLocal;
        uvScale = uvPerLocal;
        maxHeat = Math.max(maxHeat, heat * alpha);
        float leftX = quad.x0() - padLocal - planeCenterX + shiftX;
        float rightEdge = quad.x1() + padLocal - planeCenterX + shiftX;
        float topY = planeCenterY - (quad.y0() - padLocal) + shiftY;
        float bottomY = planeCenterY - (quad.y1() + padLocal) + shiftY;
        float u0 = quad.u0() - padUv;
        float u1 = quad.u1() + padUv;
        float v0 = quad.v0() - padUv;
        float v1 = quad.v1() + padUv;
        int page = quads / 256;
        int slot = quads % 256;
        int boundsOffset = page * 8192 + slot * 16;
        int effectOffset = page * 8192 + 4096 + slot * 16;
        ByteBuffer byteBuffer = glyphData;
        Intrinsics.checkNotNull((Object)byteBuffer);
        ByteBuffer glyphs = byteBuffer;
        glyphs.putFloat(boundsOffset, quad.u0());
        glyphs.putFloat(boundsOffset + 4, quad.v0());
        glyphs.putFloat(boundsOffset + 8, quad.u1());
        glyphs.putFloat(boundsOffset + 12, quad.v1());
        glyphs.putFloat(effectOffset, radius);
        glyphs.putFloat(effectOffset + 4, heat);
        glyphs.putFloat(effectOffset + 8, alpha);
        glyphs.putFloat(effectOffset + 12, 0.0f);
        int base = quads * 96;
        INSTANCE.emit(base, leftX, topY, u0, v0, slot);
        INSTANCE.emit(base + 24, leftX, bottomY, u0, v1, slot);
        INSTANCE.emit(base + 48, rightEdge, bottomY, u1, v1, slot);
        INSTANCE.emit(base + 72, rightEdge, topY, u1, v0, slot);
        int n = quads;
        quads = n + 1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void end(boolean throughWalls) {
        if (!active) {
            return;
        }
        active = false;
        if (quads == 0 || atlasView == null) {
            return;
        }
        Framebuffer framebuffer2 = MinecraftClient.getInstance().getFramebuffer();
        Intrinsics.checkNotNullExpressionValue((Object)framebuffer2, (String)"getMainRenderTarget(...)");
        Framebuffer target = framebuffer2;
        if (target.getColorAttachmentView() == null || target.getDepthAttachmentView() == null) {
            quads = 0;
            return;
        }
        int total = quads;
        quads = 0;
        try {
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            ByteBuffer byteBuffer = uniformData;
            Intrinsics.checkNotNull((Object)byteBuffer);
            ByteBuffer uniforms = byteBuffer;
            ByteBuffer byteBuffer2 = vertexData;
            Intrinsics.checkNotNull((Object)byteBuffer2);
            ByteBuffer vertices = byteBuffer2;
            ByteBuffer byteBuffer3 = glyphData;
            Intrinsics.checkNotNull((Object)byteBuffer3);
            ByteBuffer glyphs = byteBuffer3;
            VIEW_PROJECTION.get(0, uniforms);
            uniforms.putFloat(64, grayLevel);
            uniforms.putFloat(68, heatBoost);
            uniforms.putFloat(72, glowStrength);
            uniforms.putFloat(76, Math.max(1.0E-6f, blurReference * uvScale));
            uniforms.limit(80);
            GpuBuffer gpuBuffer = uniformBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 80L), uniforms);
            uniforms.clear();
            int bytes = total * 96;
            vertices.limit(bytes);
            GpuBuffer gpuBuffer2 = vertexBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer2);
            encoder.writeToBuffer(gpuBuffer2.slice(0L, (long)bytes), vertices);
            vertices.clear();
            int pages = (total + 256 - 1) / 256;
            for (int page = 0; page < pages; ++page) {
                glyphs.limit((page + 1) * 8192);
                glyphs.position(page * 8192);
                encoder.writeToBuffer(INSTANCE.glyphBuffer(page).slice(0L, 8192L), glyphs);
                glyphs.clear();
            }
            GpuSampler gpuSampler2 = RenderSystem.getSamplerCache().get(FilterMode.LINEAR);
            Intrinsics.checkNotNullExpressionValue((Object)gpuSampler2, (String)"getClampToEdge(...)");
            GpuSampler sampler = gpuSampler2;
            RenderSystem.ShapeIndexBuffer shapeIndexBuffer2 = RenderSystem.getSequentialBuffer((VertexFormat.DrawMode)VertexFormat.DrawMode.QUADS);
            Intrinsics.checkNotNullExpressionValue((Object)shapeIndexBuffer2, (String)"getSequentialBuffer(...)");
            RenderSystem.ShapeIndexBuffer indices = shapeIndexBuffer2;
            GpuBuffer gpuBuffer3 = indices.getIndexBuffer(total * 6);
            Intrinsics.checkNotNullExpressionValue((Object)gpuBuffer3, (String)"getBuffer(...)");
            GpuBuffer indexBuffer = gpuBuffer3;
            Supplier<String> supplier = Text3D::end$lambda$0;
            GpuTextureView gpuTextureView = target.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty(), target.getDepthAttachmentView(), OptionalDouble.empty());
            Throwable throwable = null;
            try {
                RenderPipeline renderPipeline;
                RenderPass pass = (RenderPass)autoCloseable;
                boolean bl = false;
                if (throughWalls) {
                    RenderPipeline renderPipeline2 = overlayPipeline;
                    renderPipeline = renderPipeline2;
                    Intrinsics.checkNotNull((Object)renderPipeline2);
                } else {
                    RenderPipeline renderPipeline3 = depthPipeline;
                    renderPipeline = renderPipeline3;
                    Intrinsics.checkNotNull((Object)renderPipeline3);
                }
                pass.setPipeline(renderPipeline);
                GpuBuffer gpuBuffer4 = vertexBuffer;
                Intrinsics.checkNotNull((Object)gpuBuffer4);
                pass.setVertexBuffer(0, gpuBuffer4);
                pass.setIndexBuffer(indexBuffer, indices.getIndexType());
                GpuBuffer gpuBuffer5 = uniformBuffer;
                Intrinsics.checkNotNull((Object)gpuBuffer5);
                pass.setUniform(UNIFORM_BLOCK, gpuBuffer5.slice());
                pass.bindTexture(SAMPLER, atlasView, sampler);
                for (int page = 0; page < pages; ++page) {
                    int count = Math.min(256, total - page * 256);
                    GpuBuffer gpuBuffer6 = glyphBuffers[page];
                    Intrinsics.checkNotNull((Object)gpuBuffer6);
                    pass.setUniform(GLYPH_BLOCK, gpuBuffer6.slice());
                    pass.drawIndexed(0, page * 256 * 6, count * 6, 1);
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
            if (glowStrength > 0.004f && maxHeat > 0.004f && INSTANCE.ensureGlowTargets(target.textureWidth, target.textureHeight)) {
                INSTANCE.glowChain(encoder, target, sampler, indices, indexBuffer, total, pages, throughWalls);
            }
        }
        catch (Throwable ignored) {
            INSTANCE.disable();
        }
        finally {
            atlasView = null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void glowChain(CommandEncoder encoder, Framebuffer target, GpuSampler sampler, RenderSystem.ShapeIndexBuffer indices, GpuBuffer indexBuffer, int total, int pages, boolean throughWalls) {
        int index;
        SimpleFramebuffer simpleFramebuffer2 = glowTarget;
        Intrinsics.checkNotNull((Object)simpleFramebuffer2);
        SimpleFramebuffer glow = simpleFramebuffer2;
        Supplier<String> supplier = Text3D::glowChain$lambda$0;
        GpuTextureView gpuTextureView = glow.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0), glow.getDepthAttachmentView(), OptionalDouble.of(1.0));
        Throwable throwable = null;
        try {
            RenderPass it = (RenderPass)autoCloseable;
            boolean bl = false;
// it = Unit.INSTANCE;
        }
        catch (Throwable bl) {
            throwable = bl;
            throw bl;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        if (!throughWalls && target.getDepthAttachment() != null && glow.getDepthAttachment() != null) {
            GpuTexture gpuTexture = target.getDepthAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = glow.getDepthAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture2);
            encoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, glowWidth, glowHeight);
        }
        Supplier<String> supplier2 = Text3D::glowChain$lambda$2;
        GpuTextureView gpuTextureView2 = glow.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView2);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier2, gpuTextureView2, OptionalInt.empty(), glow.getDepthAttachmentView(), OptionalDouble.empty());
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = glowSourcePipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            GpuBuffer gpuBuffer = vertexBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setVertexBuffer(0, gpuBuffer);
            pass.setIndexBuffer(indexBuffer, indices.getIndexType());
            GpuBuffer gpuBuffer2 = uniformBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer2);
            pass.setUniform(UNIFORM_BLOCK, gpuBuffer2.slice());
            pass.bindTexture(SAMPLER, atlasView, sampler);
            for (int page = 0; page < pages; ++page) {
                int count = Math.min(256, total - page * 256);
                GpuBuffer gpuBuffer3 = glyphBuffers[page];
                Intrinsics.checkNotNull((Object)gpuBuffer3);
                pass.setUniform(GLYPH_BLOCK, gpuBuffer3.slice());
                pass.drawIndexed(0, page * 256 * 6, count * 6, 1);
            }
// pass = Unit.INSTANCE;
        }
        catch (Throwable bl) {
            throwable = bl;
            throw bl;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        RenderPipeline renderPipeline = glowDownPipeline;
        Intrinsics.checkNotNull((Object)renderPipeline);
        GpuTextureView gpuTextureView3 = glow.getColorAttachmentView();
        SimpleFramebuffer simpleFramebuffer3 = glowDownTargets[0];
        Intrinsics.checkNotNull((Object)simpleFramebuffer3);
        this.kawase(encoder, renderPipeline, gpuTextureView3, glowWidth, glowHeight, simpleFramebuffer3, sampler);
        GpuTextureView current = null;
        SimpleFramebuffer simpleFramebuffer4 = glowDownTargets[0];
        Intrinsics.checkNotNull((Object)simpleFramebuffer4);
        current = simpleFramebuffer4.getColorAttachmentView();
        SimpleFramebuffer simpleFramebuffer5 = glowDownTargets[0];
        Intrinsics.checkNotNull((Object)simpleFramebuffer5);
        int width = simpleFramebuffer5.textureWidth;
        SimpleFramebuffer simpleFramebuffer6 = glowDownTargets[0];
        Intrinsics.checkNotNull((Object)simpleFramebuffer6);
        int height = simpleFramebuffer6.textureHeight;
        for (index = 1; index < 2; ++index) {
            RenderPipeline renderPipeline2 = glowDownPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline2);
            SimpleFramebuffer simpleFramebuffer7 = glowDownTargets[index];
            Intrinsics.checkNotNull((Object)simpleFramebuffer7);
            this.kawase(encoder, renderPipeline2, current, width, height, simpleFramebuffer7, sampler);
            SimpleFramebuffer simpleFramebuffer8 = glowDownTargets[index];
            Intrinsics.checkNotNull((Object)simpleFramebuffer8);
            current = simpleFramebuffer8.getColorAttachmentView();
            SimpleFramebuffer simpleFramebuffer9 = glowDownTargets[index];
            Intrinsics.checkNotNull((Object)simpleFramebuffer9);
            width = simpleFramebuffer9.textureWidth;
            SimpleFramebuffer simpleFramebuffer10 = glowDownTargets[index];
            Intrinsics.checkNotNull((Object)simpleFramebuffer10);
            height = simpleFramebuffer10.textureHeight;
        }
        for (index = 0; -1 < index; --index) {
            RenderPipeline renderPipeline3 = glowUpPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline3);
            SimpleFramebuffer simpleFramebuffer11 = glowUpTargets[index];
            Intrinsics.checkNotNull((Object)simpleFramebuffer11);
            this.kawase(encoder, renderPipeline3, current, width, height, simpleFramebuffer11, sampler);
            SimpleFramebuffer simpleFramebuffer12 = glowUpTargets[index];
            Intrinsics.checkNotNull((Object)simpleFramebuffer12);
            current = simpleFramebuffer12.getColorAttachmentView();
            SimpleFramebuffer simpleFramebuffer13 = glowUpTargets[index];
            Intrinsics.checkNotNull((Object)simpleFramebuffer13);
            width = simpleFramebuffer13.textureWidth;
            SimpleFramebuffer simpleFramebuffer14 = glowUpTargets[index];
            Intrinsics.checkNotNull((Object)simpleFramebuffer14);
            height = simpleFramebuffer14.textureHeight;
        }
        AutoCloseable autoCloseable2 = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable2 = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable2;
            boolean bl = false;
            ByteBuffer data = stack.calloc(16);
            data.putFloat(0, glowStrength * 0.5f);
            data.position(0);
            GpuBuffer gpuBuffer = glowParamsBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 16L), data);
// stack = Unit.INSTANCE;
        }
        catch (Throwable bl) {
            throwable2 = bl;
            throw bl;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable2, (Throwable)throwable2);
        }
        Supplier<String> supplier3 = Text3D::glowChain$lambda$5;
        GpuTextureView gpuTextureView4 = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView4);
        autoCloseable2 = (AutoCloseable)encoder.createRenderPass(supplier3, gpuTextureView4, OptionalInt.empty());
        throwable2 = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable2;
            boolean bl = false;
            RenderPipeline renderPipeline4 = glowCompositePipeline;
            Intrinsics.checkNotNull((Object)renderPipeline4);
            pass.setPipeline(renderPipeline4);
            pass.bindTexture("Sampler0", current, sampler);
            GpuBuffer gpuBuffer = glowParamsBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("LyricsGlowParams", gpuBuffer);
            pass.draw(0, 6);
            Unit unit = Unit.INSTANCE;
        }
        catch (Throwable throwable3) {
            throwable2 = throwable3;
            throw throwable3;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable2, (Throwable)throwable2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void kawase(CommandEncoder encoder, RenderPipeline pipeline, GpuTextureView source, int sourceWidth, int sourceHeight, SimpleFramebuffer target, GpuSampler sampler) {
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(48);
            data.putFloat(0, 0.0f);
            data.putFloat(4, 0.0f);
            data.putFloat(8, 1.0f);
            data.putFloat(12, 1.0f);
            data.putFloat(16, 1.25f / (float)Math.max(sourceWidth, 1));
            data.putFloat(20, 1.25f / (float)Math.max(sourceHeight, 1));
            data.putFloat(32, 0.0f);
            data.putFloat(36, 0.0f);
            data.putFloat(40, 0.0f);
            data.putFloat(44, 1.0f);
            data.position(0);
            GpuBuffer gpuBuffer = kawaseBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 48L), data);
// stack = Unit.INSTANCE;
        }
        catch (Throwable bl) {
            throwable = bl;
            throw bl;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        Supplier<String> supplier = Text3D::kawase$lambda$1;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            pass.setPipeline(pipeline);
            pass.bindTexture("Sampler0", source, sampler);
            GpuBuffer gpuBuffer = kawaseBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("KawaseParams", gpuBuffer);
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

    private final boolean ensureGlowTargets(int width, int height) {
        if (width <= 0 || height <= 0) {
            return false;
        }
        if (glowTarget != null && glowWidth == width && glowHeight == height) {
            return true;
        }
        this.closeGlowTargets();
        glowTarget = new SimpleFramebuffer("kimiko_lyrics_glow", width, height, true);
        int currentWidth = Math.max(1, Math.round((float)width * 0.5f));
        int currentHeight = Math.max(1, Math.round((float)height * 0.5f));
        for (int index = 0; index < 2; ++index) {
            Text3D.glowDownTargets[index] = new SimpleFramebuffer("kimiko_lyrics_glow_down_" + index, currentWidth, currentHeight, false);
            if (index < 1) {
                Text3D.glowUpTargets[index] = new SimpleFramebuffer("kimiko_lyrics_glow_up_" + index, currentWidth, currentHeight, false);
            }
            currentWidth = Math.max(currentWidth / 2, 1);
            currentHeight = Math.max(currentHeight / 2, 1);
        }
        glowWidth = width;
        glowHeight = height;
        return true;
    }

    private final void closeGlowTargets() {
        SimpleFramebuffer simpleFramebuffer2 = glowTarget;
        if (simpleFramebuffer2 != null) {
            simpleFramebuffer2.delete();
        }
        glowTarget = null;
        for (int index = 0; index < 2; ++index) {
            SimpleFramebuffer simpleFramebuffer3 = glowDownTargets[index];
            if (simpleFramebuffer3 != null) {
                simpleFramebuffer3.delete();
            }
            Text3D.glowDownTargets[index] = null;
            SimpleFramebuffer simpleFramebuffer4 = glowUpTargets[index];
            if (simpleFramebuffer4 != null) {
                simpleFramebuffer4.delete();
            }
            Text3D.glowUpTargets[index] = null;
        }
        glowWidth = -1;
        glowHeight = -1;
    }

    private final void emit(int cursor, float localX, float localY, float u, float v, int slot) {
        float scaledX = localX * planeScale;
        float scaledY = localY * planeScale;
        float x = (float)(planeX - cameraX) + rightX * scaledX + upX * scaledY;
        float y = (float)(planeY - cameraY) + rightY * scaledX + upY * scaledY;
        float z = (float)(planeZ - cameraZ) + rightZ * scaledX + upZ * scaledY;
        ByteBuffer byteBuffer = vertexData;
        Intrinsics.checkNotNull((Object)byteBuffer);
        ByteBuffer vertices = byteBuffer;
        vertices.putFloat(cursor, x);
        vertices.putFloat(cursor + 4, y);
        vertices.putFloat(cursor + 8, z);
        vertices.putFloat(cursor + 12, u);
        vertices.putFloat(cursor + 16, v);
        vertices.putFloat(cursor + 20, slot);
    }

    private final GpuBuffer glyphBuffer(int page) {
        GpuBuffer buffer = glyphBuffers[page];
        if (buffer == null || buffer.isClosed()) {
            Text3D.glyphBuffers[page] = buffer = RenderSystem.getDevice().createBuffer(Text3D::glyphBuffer$lambda$0, 136, 8192L);
        }
        return buffer;
    }

    private final boolean ensureCapacity(int required) {
        boolean bl;
        if (required <= capacity) {
            return true;
        }
        if (required > 2048) {
            return false;
        }
        int target = Math.min(2048, Math.max(256, Integer.highestOneBit(required - 1) * 2));
        try {
            ByteBuffer resized;
            ByteBuffer byteBuffer = MemoryUtil.memRealloc((ByteBuffer)vertexData, (int)(target * 96));
            if (byteBuffer == null) {
                return false;
            }
            vertexData = resized = byteBuffer;
            GpuBuffer gpuBuffer = vertexBuffer;
            if (gpuBuffer != null) {
                gpuBuffer.close();
            }
            vertexBuffer = RenderSystem.getDevice().createBuffer(Text3D::ensureCapacity$lambda$0, 40, (long)(target * 96));
            capacity = target;
            bl = true;
        }
        catch (Throwable ignored) {
            this.disable();
            bl = false;
        }
        return bl;
    }

    private final boolean ensureReady() {
        boolean bl;
        if (depthPipeline != null && overlayPipeline != null && uniformBuffer != null && uniformData != null && glyphData != null) {
            return true;
        }
        try {
            GpuBuffer glowParams;
            GpuBuffer kawase;
            GpuBuffer uniform;
            if (depthPipeline == null) {
                depthPipeline = this.pipeline(DEPTH_PIPELINE_ID, DepthTestFunction.LEQUAL_DEPTH_TEST);
            }
            if (overlayPipeline == null) {
                overlayPipeline = this.pipeline(OVERLAY_PIPELINE_ID, DepthTestFunction.NO_DEPTH_TEST);
            }
            if (uniformData == null) {
                uniformData = MemoryUtil.memAlloc((int)80);
            }
            if (glyphData == null) {
                glyphData = MemoryUtil.memAlloc((int)65536);
            }
            if ((uniform = uniformBuffer) == null || uniform.isClosed()) {
                uniformBuffer = RenderSystem.getDevice().createBuffer(Text3D::ensureReady$lambda$0, 136, 80L);
            }
            if (glowSourcePipeline == null) {
                glowSourcePipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(GLOW_PIPELINE_ID).withVertexShader(SHADER).withFragmentShader(GLOW_SHADER).withVertexFormat(FORMAT, VertexFormat.DrawMode.QUADS).withUniform(UNIFORM_BLOCK, UniformType.UNIFORM_BUFFER).withUniform(GLYPH_BLOCK, UniformType.UNIFORM_BUFFER).withSampler(SAMPLER).withBlend(BlendFunction.ADDITIVE).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (glowDownPipeline == null) {
                glowDownPipeline = this.kawasePipeline(GLOW_DOWN_PIPELINE_ID, KAWASE_DOWN_SHADER);
            }
            if (glowUpPipeline == null) {
                glowUpPipeline = this.kawasePipeline(GLOW_UP_PIPELINE_ID, KAWASE_UP_SHADER);
            }
            if (glowCompositePipeline == null) {
                glowCompositePipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(GLOW_COMPOSITE_PIPELINE_ID).withVertexShader(FULLSCREEN_VERTEX).withFragmentShader(GLOW_COMPOSITE_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("LyricsGlowParams", UniformType.UNIFORM_BUFFER).withSampler("Sampler0").withBlend(BlendFunction.LIGHTNING).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if ((kawase = kawaseBuffer) == null || kawase.isClosed()) {
                kawaseBuffer = RenderSystem.getDevice().createBuffer(Text3D::ensureReady$lambda$1, 136, 48L);
            }
            if ((glowParams = glowParamsBuffer) == null || glowParams.isClosed()) {
                glowParamsBuffer = RenderSystem.getDevice().createBuffer(Text3D::ensureReady$lambda$2, 136, 16L);
            }
            bl = true;
        }
        catch (Throwable ignored) {
            this.disable();
            bl = false;
        }
        return bl;
    }

    private final RenderPipeline kawasePipeline(Identifier location, Identifier fragment) {
        RenderPipeline renderPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(location).withVertexShader(KAWASE_VERTEX).withFragmentShader(fragment).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("KawaseParams", UniformType.UNIFORM_BUFFER).withSampler("Sampler0").withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"register(...)");
        return renderPipeline;
    }

    private final RenderPipeline pipeline(Identifier location, DepthTestFunction depth) {
        RenderPipeline renderPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(location).withVertexShader(SHADER).withFragmentShader(SHADER).withVertexFormat(FORMAT, VertexFormat.DrawMode.QUADS).withUniform(UNIFORM_BLOCK, UniformType.UNIFORM_BUFFER).withUniform(GLYPH_BLOCK, UniformType.UNIFORM_BUFFER).withSampler(SAMPLER).withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA).withDepthTestFunction(depth).withDepthWrite(false).withCull(false).build());
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"register(...)");
        return renderPipeline;
    }

    @JvmStatic
    public static final void invalidate() {
        block6: {
            active = false;
            quads = 0;
            capacity = 0;
            atlasView = null;
            INSTANCE.closeGlowTargets();
            GpuBuffer gpuBuffer = kawaseBuffer;
            if (gpuBuffer != null) {
                gpuBuffer.close();
            }
            kawaseBuffer = null;
            GpuBuffer gpuBuffer2 = glowParamsBuffer;
            if (gpuBuffer2 != null) {
                gpuBuffer2.close();
            }
            glowParamsBuffer = null;
            GpuBuffer gpuBuffer3 = uniformBuffer;
            if (gpuBuffer3 != null) {
                gpuBuffer3.close();
            }
            uniformBuffer = null;
            GpuBuffer gpuBuffer4 = vertexBuffer;
            if (gpuBuffer4 != null) {
                gpuBuffer4.close();
            }
            vertexBuffer = null;
            for (int page = 0; page < 8; ++page) {
                GpuBuffer gpuBuffer5 = glyphBuffers[page];
                if (gpuBuffer5 != null) {
                    gpuBuffer5.close();
                }
                Text3D.glyphBuffers[page] = null;
            }
            ByteBuffer byteBuffer = vertexData;
            if (byteBuffer == null) break block6;
            ByteBuffer it = byteBuffer;
            boolean bl = false;
            MemoryUtil.memFree((Buffer)it);
            vertexData = null;
        }
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }

    private final void disable() {
        ByteBuffer it;
        failed = true;
        active = false;
        quads = 0;
        capacity = 0;
        atlasView = null;
        this.closeGlowTargets();
        GpuBuffer gpuBuffer = kawaseBuffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        kawaseBuffer = null;
        GpuBuffer gpuBuffer2 = glowParamsBuffer;
        if (gpuBuffer2 != null) {
            gpuBuffer2.close();
        }
        glowParamsBuffer = null;
        GpuBuffer gpuBuffer3 = uniformBuffer;
        if (gpuBuffer3 != null) {
            gpuBuffer3.close();
        }
        GpuBuffer gpuBuffer4 = vertexBuffer;
        if (gpuBuffer4 != null) {
            gpuBuffer4.close();
        }
        for (int page = 0; page < 8; ++page) {
            GpuBuffer gpuBuffer5 = glyphBuffers[page];
            if (gpuBuffer5 != null) {
                gpuBuffer5.close();
            }
            Text3D.glyphBuffers[page] = null;
        }
        ByteBuffer byteBuffer = uniformData;
        if (byteBuffer != null) {
            it = byteBuffer;
            boolean bl = false;
            MemoryUtil.memFree((Buffer)it);
        }
        ByteBuffer byteBuffer2 = glyphData;
        if (byteBuffer2 != null) {
            it = byteBuffer2;
            boolean bl = false;
            MemoryUtil.memFree((Buffer)it);
        }
        ByteBuffer byteBuffer3 = vertexData;
        if (byteBuffer3 != null) {
            it = byteBuffer3;
            boolean bl = false;
            MemoryUtil.memFree((Buffer)it);
        }
        uniformBuffer = null;
        vertexBuffer = null;
        uniformData = null;
        glyphData = null;
        vertexData = null;
        depthPipeline = null;
        overlayPipeline = null;
        glowSourcePipeline = null;
        glowDownPipeline = null;
        glowUpPipeline = null;
        glowCompositePipeline = null;
    }

    private static final String end$lambda$0() {
        return "kimiko:lyrics_text";
    }

    private static final String glowChain$lambda$0() {
        return "kimiko:lyrics_glow_clear";
    }

    private static final String glowChain$lambda$2() {
        return "kimiko:lyrics_glow_source";
    }

    private static final String glowChain$lambda$5() {
        return "kimiko:lyrics_glow_composite";
    }

    private static final String kawase$lambda$1() {
        return "kimiko:lyrics_glow_kawase";
    }

    private static final String glyphBuffer$lambda$0() {
        return "kimiko:lyrics_text_glyphs";
    }

    private static final String ensureCapacity$lambda$0() {
        return "kimiko:lyrics_text_vertices";
    }

    private static final String ensureReady$lambda$0() {
        return "kimiko:lyrics_text_uniform";
    }

    private static final String ensureReady$lambda$1() {
        return "kimiko:lyrics_glow_kawase";
    }

    private static final String ensureReady$lambda$2() {
        return "kimiko:lyrics_glow_params";
    }

    static {
        VertexFormat vertexFormat = VertexFormat.builder().add("Position", VertexFormatElement.POSITION).add("UV0", VertexFormatElement.UV0).add("LineWidth", VertexFormatElement.LINE_WIDTH).build();
        Intrinsics.checkNotNullExpressionValue((Object)vertexFormat, (String)"build(...)");
        FORMAT = vertexFormat;
        VIEW_PROJECTION = new Matrix4f();
        glowDownTargets = new SimpleFramebuffer[2];
        glowUpTargets = new SimpleFramebuffer[2];
        glowWidth = -1;
        glowHeight = -1;
        glyphBuffers = new GpuBuffer[8];
    }
}

