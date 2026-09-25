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
 *  com.mojang.blaze3d.textures.FilterMode
 *  com.mojang.blaze3d.textures.GpuTexture
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.textures.TextureFormat
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.system.MemoryUtil
 */
package rtx.kimiko.utils.render.modules.post.handsflame;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
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
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryUtil;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.modules.impl.Visuals.NameTags;
import rtx.kimiko.api.modules.impl.Visuals.ShaderHands;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.modules.post.shaderhands.ShaderHandsRenderer;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.render.render2d.ThemeWaveUniform;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00a6\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0011\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u00be\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u0013\u0010\u0007\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0007\u0010\u0003J\u000f\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000b\u0010\u0003J\u0013\u0010\f\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\u0003J\u0013\u0010\r\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u0003J\u0013\u0010\u000e\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\u0003J\u0013\u0010\u000f\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0003J\u0013\u0010\u0010\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\nJ\u0013\u0010\u0011\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0011\u0010\u0003J\u0013\u0010\u0012\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0003J\u001b\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0014\u0010\u0015J[\u0010!\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b!\u0010\"J\u0013\u0010#\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b#\u0010\nJ\u001f\u0010&\u001a\u00020\b2\u0006\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b(\u0010\u0003J\u001f\u0010)\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b)\u0010*J/\u0010/\u001a\u00020.2\u0006\u0010,\u001a\u00020+2\u0006\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b/\u00100J\u0017\u00103\u001a\u00020\u00042\u0006\u00102\u001a\u000201H\u0002\u00a2\u0006\u0004\b3\u00104J\u000f\u00105\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b5\u0010\nJ\u000f\u00106\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b6\u00107J/\u0010:\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\u001c2\u0006\u00108\u001a\u00020\b2\u0006\u00109\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b:\u0010;J\u0017\u0010=\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b=\u0010>JG\u0010F\u001a\u00020\u00042\u0006\u0010@\u001a\u00020?2\u0006\u00102\u001a\u0002012\b\u0010B\u001a\u0004\u0018\u00010A2\b\u0010C\u001a\u0004\u0018\u00010A2\b\u0010D\u001a\u0004\u0018\u00010A2\b\u0010E\u001a\u0004\u0018\u00010AH\u0002\u00a2\u0006\u0004\bF\u0010GJQ\u0010I\u001a\u00020\u00042\u0006\u0010@\u001a\u00020?2\u0006\u00102\u001a\u0002012\b\u0010B\u001a\u0004\u0018\u00010A2\b\u0010C\u001a\u0004\u0018\u00010A2\b\u0010D\u001a\u0004\u0018\u00010A2\b\u0010E\u001a\u0004\u0018\u00010A2\b\u0010H\u001a\u0004\u0018\u00010AH\u0002\u00a2\u0006\u0004\bI\u0010JJ)\u0010K\u001a\u00020\u00042\u0006\u0010@\u001a\u00020?2\u0006\u00102\u001a\u0002012\b\u0010H\u001a\u0004\u0018\u00010AH\u0002\u00a2\u0006\u0004\bK\u0010LJ\u0011\u0010M\u001a\u0004\u0018\u00010AH\u0002\u00a2\u0006\u0004\bM\u0010NJ\u0011\u0010O\u001a\u0004\u0018\u00010AH\u0002\u00a2\u0006\u0004\bO\u0010NJ\u000f\u0010P\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bP\u0010\u0003J\u0019\u0010Q\u001a\u00020\b2\b\u00102\u001a\u0004\u0018\u000101H\u0002\u00a2\u0006\u0004\bQ\u0010RJ\u000f\u0010S\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\bS\u00107J'\u0010W\u001a\u00020\u00162\u0006\u0010T\u001a\u00020\u00162\u0006\u0010U\u001a\u00020\u00162\u0006\u0010V\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\bW\u0010XJ\u0017\u0010[\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020YH\u0002\u00a2\u0006\u0004\b[\u0010\\J\u000f\u0010]\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b]\u0010\u0003J\u000f\u0010^\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b^\u0010\u0003J\u0017\u0010a\u001a\u00020`2\u0006\u0010_\u001a\u00020+H\u0002\u00a2\u0006\u0004\ba\u0010bJ\u001f\u0010d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001c2\u0006\u0010c\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\bd\u0010eJ\u001f\u0010g\u001a\u00020f2\u0006\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\bg\u0010hJ\u0017\u0010j\u001a\u00020\u00042\u0006\u0010i\u001a\u00020fH\u0002\u00a2\u0006\u0004\bj\u0010kR\u0014\u0010l\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bl\u0010mR\u0014\u0010n\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bn\u0010mR\u0014\u0010o\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bo\u0010mR\u0014\u0010p\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bp\u0010mR\u0014\u0010q\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bq\u0010mR\u0014\u0010r\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\br\u0010mR\u0014\u0010s\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bs\u0010mR\u0014\u0010t\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bt\u0010uR\u0014\u0010v\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bv\u0010uR\u0016\u0010w\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bw\u0010xR\u0016\u0010y\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010zR\u0016\u0010{\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010zR\u0016\u0010|\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b|\u0010zR\u0016\u0010}\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b}\u0010zR\u0016\u0010~\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b~\u0010zR\u0016\u0010\u007f\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u007f\u0010uR\u0018\u0010\u0080\u0001\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010uR\u0018\u0010\u0081\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0081\u0001\u0010xR\u0018\u0010\u0082\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0082\u0001\u0010xR\u001c\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0083\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0084\u0001\u0010\u0085\u0001R\u001c\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0083\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0086\u0001\u0010\u0085\u0001R\u001c\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0083\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0087\u0001\u0010\u0085\u0001R\u001c\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u0088\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0089\u0001\u0010\u008a\u0001R\u001c\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u0088\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008b\u0001\u0010\u008a\u0001R\u001c\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008c\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008d\u0001\u0010\u008e\u0001R\u001b\u0010\u008f\u0001\u001a\u0004\u0018\u00010.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008f\u0001\u0010\u0090\u0001R\u001b\u0010\u0091\u0001\u001a\u0004\u0018\u00010.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0091\u0001\u0010\u0090\u0001R\u001b\u0010\u0092\u0001\u001a\u0004\u0018\u00010.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0092\u0001\u0010\u0090\u0001R\u001b\u0010\u0093\u0001\u001a\u0004\u0018\u00010.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0093\u0001\u0010\u0090\u0001R\u001b\u0010\u0094\u0001\u001a\u0004\u0018\u00010.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0094\u0001\u0010\u0090\u0001R\u001b\u0010\u0095\u0001\u001a\u0004\u0018\u00010A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0095\u0001\u0010\u0096\u0001R\u001b\u0010\u0097\u0001\u001a\u0004\u0018\u00010A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0097\u0001\u0010\u0096\u0001R\u001b\u0010\u0098\u0001\u001a\u0004\u0018\u00010A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0098\u0001\u0010\u0096\u0001R\u001b\u0010\u0099\u0001\u001a\u0004\u0018\u00010A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0099\u0001\u0010\u0096\u0001R\u001b\u0010\u009a\u0001\u001a\u0004\u0018\u00010A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u0096\u0001R \u0010\u009c\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010.0\u009b\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u009d\u0001R \u0010\u009e\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010.0\u009b\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009e\u0001\u0010\u009d\u0001R \u0010\u009f\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010A0\u009b\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009f\u0001\u0010\u00a0\u0001R \u0010\u00a1\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010A0\u009b\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a1\u0001\u0010\u00a0\u0001R\u0018\u0010\u00a2\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a2\u0001\u0010xR\u0018\u0010\u00a3\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a3\u0001\u0010xR\u0018\u0010\u00a4\u0001\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a4\u0001\u0010uR\u0018\u0010\u00a5\u0001\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a5\u0001\u0010uR\u0018\u0010\u00a6\u0001\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a6\u0001\u0010uR\u0018\u0010\u00a7\u0001\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a7\u0001\u0010uR\u0018\u0010\u00a8\u0001\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a8\u0001\u0010uR\u0018\u0010\u00a9\u0001\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a9\u0001\u0010uR\u001c\u0010«\u0001\u001a\u0005\u0018\u00010\u00aa\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b«\u0001\u0010\u00ac\u0001R\u001b\u0010\u00ad\u0001\u001a\u0004\u0018\u00010f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ad\u0001\u0010\u00ae\u0001R\u0018\u0010\u00af\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00af\u0001\u0010xR\u0018\u0010\u00b1\u0001\u001a\u00030\u00b0\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b1\u0001\u0010\u00b2\u0001R\u001b\u0010\u00b3\u0001\u001a\u0004\u0018\u00010.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b3\u0001\u0010\u0090\u0001R\u001b\u0010\u00b4\u0001\u001a\u0004\u0018\u00010A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b4\u0001\u0010\u0096\u0001R\u0016\u0010\u00b5\u0001\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u00b5\u0001\u0010zR\u001a\u0010\u00b7\u0001\u001a\u00030\u00b6\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b7\u0001\u0010\u00b8\u0001R\u0018\u0010\u00b9\u0001\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00b9\u0001\u0010zR\u0018\u0010\u00ba\u0001\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00ba\u0001\u0010zR\u0018\u0010»\u0001\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b»\u0001\u0010zR\u0018\u0010\u00bc\u0001\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00bc\u0001\u0010zR\u0018\u0010\u00bd\u0001\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00bd\u0001\u0010z\u00a8\u0006\u00bf\u0001"}, d2={"Lrtx/kimiko/utils/render/modules/post/handsflame/HandsFlameRenderer;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "captureBeforeHandRender", "captureAfterHandRender", "", "ensureBlackTexture", "()Z", "closeBlackTexture", "renderCapturedHandsFlame", "beginIrisHandDepthCapture", "endIrisHandDepthCapture", "renderIrisCapturedHandsFlame", "hasCapturedHands", "resetTrail", "shutdown", "enabled", "setFlameEnabled", "(Z)V", "", "strength", "riseSpeed", "wobble", "length", "brightness", "", "colorMode", "color", "itemsOnly", "clientGradient", "configure", "(FFFFFIIZZ)V", "shouldRenderFlame", "width", "height", "ensureReady", "(II)Z", "initPipelines", "ensureTextures", "(II)V", "", "label", "usage", "Lcom/mojang/blaze3d/textures/GpuTexture;", "createTexture", "(Ljava/lang/String;III)Lcom/mojang/blaze3d/textures/GpuTexture;", "Lnet/minecraft/Framebuffer;", "target", "ensureIrisDepthTextures", "(Lnet/minecraft/Framebuffer;)V", "advanceTrailClock", "consumeTrailStep", "()F", "irisDepthMode", "dt", "writeUniforms", "(IIZF)V", "rgb", "putGradientColor", "(I)V", "Lcom/mojang/blaze3d/systems/CommandEncoder;", "encoder", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "depthView", "beforeDepthView", "beforeView", "afterView", "renderTrail", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lnet/minecraft/Framebuffer;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;)V", "sceneView", "renderComposite", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lnet/minecraft/Framebuffer;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;)V", "restoreProtectedNameTags", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lnet/minecraft/Framebuffer;Lcom/mojang/blaze3d/textures/GpuTextureView;)V", "historyTrailView", "()Lcom/mojang/blaze3d/textures/GpuTextureView;", "nextTrailView", "swapTrailHistory", "isUsable", "(Lnet/minecraft/Framebuffer;)Z", "flameTime", "value", "min", "max", "clamp", "(FFF)F", "", "throwable", "disableAfterError", "(Ljava/lang/Throwable;)V", "closeTextures", "closeIrisDepthTextures", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "shift", "channel", "(II)I", "Lrtx/kimiko/utils/render/modules/post/handsflame/HandsFlameRenderer$RenderRegion;", "flameRegion", "(II)Lrtx/kimiko/utils/render/modules/post/handsflame/HandsFlameRenderer$RenderRegion;", "region", "ensureRenderRegion", "(Lrtx/kimiko/utils/render/modules/post/handsflame/HandsFlameRenderer$RenderRegion;)V", "TRAIL_PIPELINE_ID", "Lnet/minecraft/Identifier;", "COMPOSITE_PIPELINE_ID", "RESTORE_PIPELINE_ID", "FULLSCREEN_VERTEX_SHADER", "TRAIL_FRAGMENT_SHADER", "COMPOSITE_FRAGMENT_SHADER", "RESTORE_FRAGMENT_SHADER", "UNIFORM_BYTES", "I", "IRIS_CAPTURE_SLOTS", "flameEnabled", "Z", "flameStrength", "F", "flameRiseSpeed", "flameWobble", "flameLength", "flameBrightness", "flameColorMode", "flameColor", "flameItemsOnly", "flameClientGradient", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "trailPipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "compositePipeline", "restorePipeline", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "uniformBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "dummyVertexBuffer", "Ljava/nio/ByteBuffer;", "dataBuffer", "Ljava/nio/ByteBuffer;", "beforeTexture", "Lcom/mojang/blaze3d/textures/GpuTexture;", "sceneTexture", "handTexture", "trailTextureA", "trailTextureB", "beforeTextureView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "sceneTextureView", "handTextureView", "trailTextureViewA", "trailTextureViewB", "", "irisDepthBeforeTextures", "[Lcom/mojang/blaze3d/textures/GpuTexture;", "irisDepthAfterTextures", "irisDepthBeforeTextureViews", "[Lcom/mojang/blaze3d/textures/GpuTextureView;", "irisDepthAfterTextureViews", "useTrailAAsHistory", "capturedBeforeHands", "irisCaptureCount", "irisOpenCaptureSlot", "lastWidth", "lastHeight", "lastIrisDepthWidth", "lastIrisDepthHeight", "Lcom/mojang/blaze3d/textures/TextureFormat;", "lastIrisDepthFormat", "Lcom/mojang/blaze3d/textures/TextureFormat;", "lastRenderRegion", "Lrtx/kimiko/utils/render/modules/post/handsflame/HandsFlameRenderer$RenderRegion;", "disabledAfterError", "", "protectedTagBounds", "[F", "blackTexture", "blackTextureView", "TRAIL_STEP_SECONDS", "", "trailClockNs", "J", "trailAccumulator", "pendingTrailStep", "smoothR", "smoothG", "smoothB", "RenderRegion", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nHandsFlameRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HandsFlameRenderer.kt\nrtx/kimiko/utils/render/modules/post/handsflame/HandsFlameRenderer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,895:1\n1#2:896\n*E\n"})
public final class HandsFlameRenderer {
    @NotNull
    public static final HandsFlameRenderer INSTANCE = new HandsFlameRenderer();
    @NotNull
    private static final Identifier TRAIL_PIPELINE_ID = INSTANCE.id("pipeline/effects/hands_flame_trail");
    @NotNull
    private static final Identifier COMPOSITE_PIPELINE_ID = INSTANCE.id("pipeline/effects/hands_flame_composite");
    @NotNull
    private static final Identifier RESTORE_PIPELINE_ID = INSTANCE.id("pipeline/effects/hands_flame_restore");
    @NotNull
    private static final Identifier FULLSCREEN_VERTEX_SHADER = INSTANCE.id("effects/hands_flame/fullscreen");
    @NotNull
    private static final Identifier TRAIL_FRAGMENT_SHADER = INSTANCE.id("effects/hands_flame/trail");
    @NotNull
    private static final Identifier COMPOSITE_FRAGMENT_SHADER = INSTANCE.id("effects/hands_flame/composite");
    @NotNull
    private static final Identifier RESTORE_FRAGMENT_SHADER = INSTANCE.id("effects/hands_flame/restore");
    private static final int UNIFORM_BYTES = 144;
    private static final int IRIS_CAPTURE_SLOTS = 2;
    private static boolean flameEnabled;
    private static float flameStrength;
    private static float flameRiseSpeed;
    private static float flameWobble;
    private static float flameLength;
    private static float flameBrightness;
    private static int flameColorMode;
    private static int flameColor;
    private static boolean flameItemsOnly;
    private static boolean flameClientGradient;
    @Nullable
    private static RenderPipeline trailPipeline;
    @Nullable
    private static RenderPipeline compositePipeline;
    @Nullable
    private static RenderPipeline restorePipeline;
    @Nullable
    private static GpuBuffer uniformBuffer;
    @Nullable
    private static GpuBuffer dummyVertexBuffer;
    @Nullable
    private static ByteBuffer dataBuffer;
    @Nullable
    private static GpuTexture beforeTexture;
    @Nullable
    private static GpuTexture sceneTexture;
    @Nullable
    private static GpuTexture handTexture;
    @Nullable
    private static GpuTexture trailTextureA;
    @Nullable
    private static GpuTexture trailTextureB;
    @Nullable
    private static GpuTextureView beforeTextureView;
    @Nullable
    private static GpuTextureView sceneTextureView;
    @Nullable
    private static GpuTextureView handTextureView;
    @Nullable
    private static GpuTextureView trailTextureViewA;
    @Nullable
    private static GpuTextureView trailTextureViewB;
    @NotNull
    private static final GpuTexture[] irisDepthBeforeTextures;
    @NotNull
    private static final GpuTexture[] irisDepthAfterTextures;
    @NotNull
    private static final GpuTextureView[] irisDepthBeforeTextureViews;
    @NotNull
    private static final GpuTextureView[] irisDepthAfterTextureViews;
    private static boolean useTrailAAsHistory;
    private static boolean capturedBeforeHands;
    private static int irisCaptureCount;
    private static int irisOpenCaptureSlot;
    private static int lastWidth;
    private static int lastHeight;
    private static int lastIrisDepthWidth;
    private static int lastIrisDepthHeight;
    @Nullable
    private static TextureFormat lastIrisDepthFormat;
    @Nullable
    private static RenderRegion lastRenderRegion;
    private static boolean disabledAfterError;
    @NotNull
    private static final float[] protectedTagBounds;
    @Nullable
    private static GpuTexture blackTexture;
    @Nullable
    private static GpuTextureView blackTextureView;
    private static final float TRAIL_STEP_SECONDS = 0.008333334f;
    private static long trailClockNs;
    private static float trailAccumulator;
    private static float pendingTrailStep;
    private static float smoothR;
    private static float smoothG;
    private static float smoothB;

    private HandsFlameRenderer() {
    }

    @JvmStatic
    public static final void captureBeforeHandRender() {
        capturedBeforeHands = false;
        if (!HandsFlameRenderer.shouldRenderFlame()) {
            return;
        }
        Framebuffer framebuffer2 = MinecraftClient.getInstance().getFramebuffer();
        Intrinsics.checkNotNullExpressionValue((Object)framebuffer2, (String)"getMainRenderTarget(...)");
        Framebuffer target = framebuffer2;
        if (!INSTANCE.isUsable(target)) {
            return;
        }
        if (!INSTANCE.ensureReady(target.textureWidth, target.textureHeight)) {
            return;
        }
        try {
            RenderRegion region = INSTANCE.flameRegion(target.textureWidth, target.textureHeight);
            INSTANCE.ensureRenderRegion(region);
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            GpuTexture gpuTexture = target.getColorAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = beforeTexture;
            Intrinsics.checkNotNull((Object)gpuTexture2);
            commandEncoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, region.getX(), region.getY(), region.getX(), region.getY(), region.getWidth(), region.getHeight());
        }
        catch (Throwable throwable) {
            INSTANCE.disableAfterError(throwable);
        }
    }

    @JvmStatic
    public static final void captureAfterHandRender() {
        if (!HandsFlameRenderer.shouldRenderFlame()) {
            return;
        }
        Framebuffer framebuffer2 = MinecraftClient.getInstance().getFramebuffer();
        Intrinsics.checkNotNullExpressionValue((Object)framebuffer2, (String)"getMainRenderTarget(...)");
        Framebuffer target = framebuffer2;
        if (!INSTANCE.isUsable(target) || !INSTANCE.ensureReady(target.textureWidth, target.textureHeight)) {
            return;
        }
        try {
            RenderRegion region = INSTANCE.flameRegion(target.textureWidth, target.textureHeight);
            INSTANCE.ensureRenderRegion(region);
            if (!ShaderHandsRenderer.wasHandCapturedThisFrame()) {
                CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
                GpuTexture gpuTexture = target.getColorAttachment();
                Intrinsics.checkNotNull((Object)gpuTexture);
                GpuTexture gpuTexture2 = handTexture;
                Intrinsics.checkNotNull((Object)gpuTexture2);
                commandEncoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, region.getX(), region.getY(), region.getX(), region.getY(), region.getWidth(), region.getHeight());
            }
            capturedBeforeHands = true;
        }
        catch (Throwable throwable) {
            INSTANCE.disableAfterError(throwable);
        }
    }

    private final boolean ensureBlackTexture() {
        if (blackTexture != null && blackTextureView != null) {
            return true;
        }
        blackTexture = RenderSystem.getDevice().createTexture(HandsFlameRenderer::ensureBlackTexture$lambda$0, 13, TextureFormat.RGBA8, 1, 1, 1, 1);
        GpuDevice gpuDevice = RenderSystem.getDevice();
        GpuTexture gpuTexture = blackTexture;
        Intrinsics.checkNotNull((Object)gpuTexture);
        blackTextureView = gpuDevice.createTextureView(gpuTexture);
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        GpuTexture gpuTexture2 = blackTexture;
        Intrinsics.checkNotNull((Object)gpuTexture2);
        commandEncoder.clearColorTexture(gpuTexture2, 0);
        return blackTextureView != null;
    }

    private final void closeBlackTexture() {
        GpuTextureView gpuTextureView = blackTextureView;
        if (gpuTextureView != null) {
            gpuTextureView.close();
        }
        blackTextureView = null;
        GpuTexture gpuTexture = blackTexture;
        if (gpuTexture != null) {
            gpuTexture.close();
        }
        blackTexture = null;
    }

    @JvmStatic
    public static final void renderCapturedHandsFlame() {
        if (!HandsFlameRenderer.capturedBeforeHands) {
            return;
        }
        HandsFlameRenderer.capturedBeforeHands = false;
        if (!HandsFlameRenderer.shouldRenderFlame()) {
            return;
        }
        Framebuffer framebuffer2 = MinecraftClient.getInstance().getFramebuffer();
        Intrinsics.checkNotNullExpressionValue((Object)framebuffer2, (String)"getMainRenderTarget(...)");
        Framebuffer target = framebuffer2;
        if (!INSTANCE.isUsable(target) || !INSTANCE.ensureReady(target.textureWidth, target.textureHeight)) {
            return;
        }
        try {
            RenderRegion region = INSTANCE.flameRegion(target.textureWidth, target.textureHeight);
            INSTANCE.ensureRenderRegion(region);
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            boolean hasFlame = flameEnabled && (!flameItemsOnly || (player != null && (!player.getMainHandStack().isEmpty() || !player.getOffHandStack().isEmpty())));
            CommandEncoder encoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)encoder, (String)"createCommandEncoder(...)");
            if (hasFlame) {
                GpuTextureView capturedHands = ShaderHandsRenderer.capturedHandColorView();
                GpuTextureView capturedDepth = ShaderHandsRenderer.capturedHandDepthView();
                boolean captured = capturedHands != null && capturedDepth != null && INSTANCE.ensureBlackTexture();
                GpuTextureView beforeView = captured ? blackTextureView : beforeTextureView;
                GpuTextureView afterView = captured ? capturedHands : handTextureView;
                GpuTextureView depthView = captured ? capturedDepth : target.getDepthAttachmentView();
                boolean stepTrail = INSTANCE.advanceTrailClock();
                INSTANCE.writeUniforms(target.textureWidth, target.textureHeight, false, stepTrail ? INSTANCE.consumeTrailStep() : 0.0f);
                GpuBuffer uBuf = uniformBuffer;
                Intrinsics.checkNotNull((Object)uBuf);
                ByteBuffer dBuf = dataBuffer;
                Intrinsics.checkNotNull((Object)dBuf);
                encoder.writeToBuffer(uBuf.slice(0L, (long)dBuf.remaining()), dBuf);
                if (stepTrail) {
                    INSTANCE.renderTrail(encoder, target, depthView, depthView, beforeView, afterView);
                    INSTANCE.swapTrailHistory();
                }
                GpuTexture colorAttach = target.getColorAttachment();
                Intrinsics.checkNotNull((Object)colorAttach);
                GpuTexture sceneTex = sceneTexture;
                Intrinsics.checkNotNull((Object)sceneTex);
                encoder.copyTextureToTexture(colorAttach, sceneTex, 0, region.getX(), region.getY(), region.getX(), region.getY(), region.getWidth(), region.getHeight());
                INSTANCE.renderComposite(encoder, target, depthView, depthView, beforeView, afterView, sceneTextureView);
            }
        }
        catch (Throwable throwable) {
            INSTANCE.disableAfterError(throwable);
        }
    }

    @JvmStatic
    public static final void beginIrisHandDepthCapture() {
        irisOpenCaptureSlot = -1;
        if (!HandsFlameRenderer.shouldRenderFlame()) {
            return;
        }
        Framebuffer framebuffer2 = MinecraftClient.getInstance().getFramebuffer();
        Intrinsics.checkNotNullExpressionValue((Object)framebuffer2, (String)"getMainRenderTarget(...)");
        Framebuffer target = framebuffer2;
        if (!INSTANCE.isUsable(target) || !INSTANCE.ensureReady(target.textureWidth, target.textureHeight)) {
            return;
        }
        try {
            RenderRegion region = INSTANCE.flameRegion(target.textureWidth, target.textureHeight);
            INSTANCE.ensureRenderRegion(region);
            INSTANCE.ensureIrisDepthTextures(target);
            if (irisCaptureCount >= 2) {
                return;
            }
            irisOpenCaptureSlot = irisCaptureCount;
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            GpuTexture gpuTexture = target.getDepthAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = irisDepthBeforeTextures[irisOpenCaptureSlot];
            Intrinsics.checkNotNull((Object)gpuTexture2);
            commandEncoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, region.getX(), region.getY(), region.getX(), region.getY(), region.getWidth(), region.getHeight());
        }
        catch (Throwable throwable) {
            irisOpenCaptureSlot = -1;
            INSTANCE.disableAfterError(throwable);
        }
    }

    @JvmStatic
    public static final void endIrisHandDepthCapture() {
        int slot = irisOpenCaptureSlot;
        irisOpenCaptureSlot = -1;
        if (slot < 0 || slot >= 2) {
            return;
        }
        Framebuffer framebuffer2 = MinecraftClient.getInstance().getFramebuffer();
        Intrinsics.checkNotNullExpressionValue((Object)framebuffer2, (String)"getMainRenderTarget(...)");
        Framebuffer target = framebuffer2;
        if (!INSTANCE.isUsable(target) || irisDepthAfterTextures[slot] == null) {
            return;
        }
        try {
            RenderRegion region = INSTANCE.flameRegion(target.textureWidth, target.textureHeight);
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            GpuTexture gpuTexture = target.getDepthAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = irisDepthAfterTextures[slot];
            Intrinsics.checkNotNull((Object)gpuTexture2);
            commandEncoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, region.getX(), region.getY(), region.getX(), region.getY(), region.getWidth(), region.getHeight());
            irisCaptureCount = Math.max(irisCaptureCount, slot + 1);
        }
        catch (Throwable throwable) {
            INSTANCE.disableAfterError(throwable);
        }
    }

    @JvmStatic
    public static final void renderIrisCapturedHandsFlame() {
        int captureCount = irisCaptureCount;
        irisCaptureCount = 0;
        irisOpenCaptureSlot = -1;
        if (captureCount <= 0 || !shouldRenderFlame()) {
            return;
        }
        Framebuffer framebuffer2 = MinecraftClient.getInstance().getFramebuffer();
        Intrinsics.checkNotNullExpressionValue((Object)framebuffer2, (String)"getMainRenderTarget(...)");
        Framebuffer target = framebuffer2;
        if (!INSTANCE.isUsable(target) || !INSTANCE.ensureReady(target.textureWidth, target.textureHeight)) {
            return;
        }
        try {
            RenderRegion region = INSTANCE.flameRegion(target.textureWidth, target.textureHeight);
            INSTANCE.ensureRenderRegion(region);
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            boolean hasFlame = flameEnabled && (!flameItemsOnly || (player != null && (!player.getMainHandStack().isEmpty() || !player.getOffHandStack().isEmpty())));
            CommandEncoder encoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)encoder, (String)"createCommandEncoder(...)");
            GpuTexture colorAttach = target.getColorAttachment();
            Intrinsics.checkNotNull((Object)colorAttach);
            GpuTexture handTex = handTexture;
            Intrinsics.checkNotNull((Object)handTex);
            encoder.copyTextureToTexture(colorAttach, handTex, 0, region.getX(), region.getY(), region.getX(), region.getY(), region.getWidth(), region.getHeight());
            if (hasFlame) {
                boolean stepTrail = INSTANCE.advanceTrailClock();
                INSTANCE.writeUniforms(target.textureWidth, target.textureHeight, true, stepTrail ? INSTANCE.consumeTrailStep() : 0.0f);
                GpuBuffer uBuf = uniformBuffer;
                Intrinsics.checkNotNull((Object)uBuf);
                ByteBuffer dBuf = dataBuffer;
                Intrinsics.checkNotNull((Object)dBuf);
                encoder.writeToBuffer(uBuf.slice(0L, (long)dBuf.remaining()), dBuf);
                int lastCapture = -1;
                int maxCaptures = Math.min(captureCount, 2);
                for (int i = 0; i < maxCaptures; ++i) {
                    if (irisDepthBeforeTextureViews[i] == null || irisDepthAfterTextureViews[i] == null) continue;
                    if (stepTrail) {
                        INSTANCE.renderTrail(encoder, target, irisDepthAfterTextureViews[i], irisDepthBeforeTextureViews[i], handTextureView, handTextureView);
                        INSTANCE.swapTrailHistory();
                    }
                    lastCapture = i;
                }
                if (lastCapture >= 0) {
                    INSTANCE.renderComposite(encoder, target, irisDepthAfterTextureViews[lastCapture], irisDepthBeforeTextureViews[lastCapture], handTextureView, handTextureView, handTextureView);
                    INSTANCE.restoreProtectedNameTags(encoder, target, handTextureView);
                }
            }
        }
        catch (Throwable throwable) {
            INSTANCE.disableAfterError(throwable);
        }
    }

    @JvmStatic
    public static final boolean hasCapturedHands() {
        return capturedBeforeHands;
    }

    @JvmStatic
    public static final void resetTrail() {
        capturedBeforeHands = false;
        if (trailTextureA == null || trailTextureB == null) {
            return;
        }
        try {
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            GpuTexture gpuTexture = trailTextureA;
            Intrinsics.checkNotNull((Object)gpuTexture);
            encoder.clearColorTexture(gpuTexture, 0);
            GpuTexture gpuTexture2 = trailTextureB;
            Intrinsics.checkNotNull((Object)gpuTexture2);
            encoder.clearColorTexture(gpuTexture2, 0);
        }
        catch (Throwable throwable) {
            INSTANCE.disableAfterError(throwable);
        }
    }

    @JvmStatic
    public static final void shutdown() {
        INSTANCE.closeTextures();
        INSTANCE.closeBlackTexture();
        GpuBuffer gpuBuffer = uniformBuffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        GpuBuffer gpuBuffer2 = dummyVertexBuffer;
        if (gpuBuffer2 != null) {
            gpuBuffer2.close();
        }
        ByteBuffer byteBuffer = dataBuffer;
        if (byteBuffer != null) {
            ByteBuffer it = byteBuffer;
            boolean bl = false;
            MemoryUtil.memFree((Buffer)it);
        }
        uniformBuffer = null;
        dummyVertexBuffer = null;
        dataBuffer = null;
        trailPipeline = null;
        compositePipeline = null;
        restorePipeline = null;
        capturedBeforeHands = false;
    }

    @JvmStatic
    public static final void setFlameEnabled(boolean enabled) {
        if (flameEnabled == enabled) {
            return;
        }
        flameEnabled = enabled;
        if (!enabled) {
            HandsFlameRenderer.resetTrail();
            irisCaptureCount = 0;
            irisOpenCaptureSlot = -1;
        }
    }

    @JvmStatic
    public static final void configure(float strength, float riseSpeed, float wobble, float length, float brightness, int colorMode, int color, boolean itemsOnly, boolean clientGradient) {
        flameStrength = INSTANCE.clamp(strength, 0.0f, 2.0f);
        flameRiseSpeed = INSTANCE.clamp(riseSpeed, 0.0f, 2.0f);
        flameWobble = INSTANCE.clamp(wobble, 0.0f, 2.0f);
        flameLength = INSTANCE.clamp(length, 0.1f, 2.5f);
        flameBrightness = INSTANCE.clamp(brightness, 0.0f, 2.0f);
        flameColorMode = Math.max(0, Math.min(2, colorMode));
        flameColor = color;
        flameItemsOnly = itemsOnly;
        flameClientGradient = clientGradient;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @JvmStatic
    public static final boolean shouldRenderFlame() {
        if (disabledAfterError) {
            return false;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.player == null) return false;
        if (minecraft.world == null) {
            return false;
        }
        if (!ShaderHands.Companion.isNewModeActive()) {
            return false;
        }
        if (!flameEnabled) return false;
        if (!flameItemsOnly) return true;
        ClientPlayerEntity clientPlayerEntity2 = minecraft.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity2);
        if (!clientPlayerEntity2.getMainHandStack().isEmpty()) return true;
        ClientPlayerEntity clientPlayerEntity3 = minecraft.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity3);
        if (clientPlayerEntity3.getOffHandStack().isEmpty()) return false;
        return true;
    }

    private final boolean ensureReady(int width, int height) {
        if (trailPipeline == null || compositePipeline == null || restorePipeline == null || uniformBuffer == null || dummyVertexBuffer == null || dataBuffer == null) {
            this.initPipelines();
        }
        this.ensureTextures(width, height);
        return trailPipeline != null && compositePipeline != null && restorePipeline != null && uniformBuffer != null && dummyVertexBuffer != null && dataBuffer != null && beforeTexture != null && sceneTexture != null && trailTextureA != null && trailTextureB != null && beforeTextureView != null && sceneTextureView != null && trailTextureViewA != null && trailTextureViewB != null;
    }

    private final void initPipelines() {
        try {
            trailPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(TRAIL_PIPELINE_ID).withVertexShader(FULLSCREEN_VERTEX_SHADER).withFragmentShader(TRAIL_FRAGMENT_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("HandsFlameData", UniformType.UNIFORM_BUFFER).withUniform("ThemeWaveParams", UniformType.UNIFORM_BUFFER).withSampler("BeforeSampler").withSampler("AfterSampler").withSampler("PrevTrailSampler").withSampler("DepthSampler").withSampler("NoHandDepthSampler").withSampler("BoundsTexL").withSampler("BoundsTexR").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            compositePipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(COMPOSITE_PIPELINE_ID).withVertexShader(FULLSCREEN_VERTEX_SHADER).withFragmentShader(COMPOSITE_FRAGMENT_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("HandsFlameData", UniformType.UNIFORM_BUFFER).withSampler("SceneSampler").withSampler("BeforeSampler").withSampler("AfterSampler").withSampler("TrailSampler").withSampler("DepthSampler").withSampler("NoHandDepthSampler").withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            restorePipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(RESTORE_PIPELINE_ID).withVertexShader(FULLSCREEN_VERTEX_SHADER).withFragmentShader(RESTORE_FRAGMENT_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withSampler("SceneSampler").withoutBlend().withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            dataBuffer = MemoryUtil.memAlloc((int)144);
            uniformBuffer = RenderSystem.getDevice().createBuffer(HandsFlameRenderer::initPipelines$lambda$0, 136, 144L);
            ByteBuffer dummyData = MemoryUtil.memAlloc((int)4);
            dummyData.putInt(0);
            dummyData.flip();
            dummyVertexBuffer = RenderSystem.getDevice().createBuffer(HandsFlameRenderer::initPipelines$lambda$1, 40, dummyData);
            MemoryUtil.memFree((Buffer)dummyData);
        }
        catch (Throwable throwable) {
            this.disableAfterError(throwable);
        }
    }

    private final void ensureTextures(int width, int height) {
        if (beforeTexture != null && width == lastWidth && height == lastHeight) {
            return;
        }
        this.closeTextures();
        int trailWidth = Math.max(1, width / 3);
        int trailHeight = Math.max(1, height / 3);
        beforeTexture = this.createTexture("kimiko:hands_flame_before", width, height, 7);
        sceneTexture = this.createTexture("kimiko:hands_flame_scene", width, height, 5);
        handTexture = this.createTexture("kimiko:hands_flame_hand_temp", width, height, 5);
        trailTextureA = this.createTexture("kimiko:hands_flame_trail_a", trailWidth, trailHeight, 13);
        trailTextureB = this.createTexture("kimiko:hands_flame_trail_b", trailWidth, trailHeight, 13);
        GpuDevice gpuDevice = RenderSystem.getDevice();
        GpuTexture gpuTexture = beforeTexture;
        Intrinsics.checkNotNull((Object)gpuTexture);
        beforeTextureView = gpuDevice.createTextureView(gpuTexture);
        GpuDevice gpuDevice2 = RenderSystem.getDevice();
        GpuTexture gpuTexture2 = sceneTexture;
        Intrinsics.checkNotNull((Object)gpuTexture2);
        sceneTextureView = gpuDevice2.createTextureView(gpuTexture2);
        GpuDevice gpuDevice3 = RenderSystem.getDevice();
        GpuTexture gpuTexture3 = handTexture;
        Intrinsics.checkNotNull((Object)gpuTexture3);
        handTextureView = gpuDevice3.createTextureView(gpuTexture3);
        GpuDevice gpuDevice4 = RenderSystem.getDevice();
        GpuTexture gpuTexture4 = trailTextureA;
        Intrinsics.checkNotNull((Object)gpuTexture4);
        trailTextureViewA = gpuDevice4.createTextureView(gpuTexture4);
        GpuDevice gpuDevice5 = RenderSystem.getDevice();
        GpuTexture gpuTexture5 = trailTextureB;
        Intrinsics.checkNotNull((Object)gpuTexture5);
        trailTextureViewB = gpuDevice5.createTextureView(gpuTexture5);
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        GpuTexture gpuTexture6 = trailTextureA;
        Intrinsics.checkNotNull((Object)gpuTexture6);
        encoder.clearColorTexture(gpuTexture6, 0);
        GpuTexture gpuTexture7 = trailTextureB;
        Intrinsics.checkNotNull((Object)gpuTexture7);
        encoder.clearColorTexture(gpuTexture7, 0);
        useTrailAAsHistory = true;
        lastWidth = width;
        lastHeight = height;
    }

    private final GpuTexture createTexture(String label, int width, int height, int usage) {
        GpuTexture gpuTexture = RenderSystem.getDevice().createTexture(() -> HandsFlameRenderer.createTexture$lambda$0(label), usage, TextureFormat.RGBA8, width, height, 1, 1);
        Intrinsics.checkNotNullExpressionValue((Object)gpuTexture, (String)"createTexture(...)");
        return gpuTexture;
    }

    private final void ensureIrisDepthTextures(Framebuffer target) {
        GpuTexture gpuTexture = target.getDepthAttachment();
        Intrinsics.checkNotNull((Object)gpuTexture);
        GpuTexture depthTexture = gpuTexture;
        TextureFormat textureFormat = depthTexture.getFormat();
        Intrinsics.checkNotNullExpressionValue((Object)textureFormat, (String)"getFormat(...)");
        TextureFormat depthFormat = textureFormat;
        if (irisDepthBeforeTextures[0] != null && target.textureWidth == lastIrisDepthWidth && target.textureHeight == lastIrisDepthHeight && depthFormat == lastIrisDepthFormat) {
            return;
        }
        this.closeIrisDepthTextures();
        for (int i = 0; i < 2; ++i) {
            HandsFlameRenderer.irisDepthBeforeTextures[i] = RenderSystem.getDevice().createTexture(HandsFlameRenderer::ensureIrisDepthTextures$lambda$0, 5, depthFormat, target.textureWidth, target.textureHeight, 1, 1);
            HandsFlameRenderer.irisDepthAfterTextures[i] = RenderSystem.getDevice().createTexture(HandsFlameRenderer::ensureIrisDepthTextures$lambda$1, 5, depthFormat, target.textureWidth, target.textureHeight, 1, 1);
            GpuDevice gpuDevice = RenderSystem.getDevice();
            GpuTexture gpuTexture2 = irisDepthBeforeTextures[i];
            Intrinsics.checkNotNull((Object)gpuTexture2);
            HandsFlameRenderer.irisDepthBeforeTextureViews[i] = gpuDevice.createTextureView(gpuTexture2);
            GpuDevice gpuDevice2 = RenderSystem.getDevice();
            GpuTexture gpuTexture3 = irisDepthAfterTextures[i];
            Intrinsics.checkNotNull((Object)gpuTexture3);
            HandsFlameRenderer.irisDepthAfterTextureViews[i] = gpuDevice2.createTextureView(gpuTexture3);
        }
        lastIrisDepthWidth = target.textureWidth;
        lastIrisDepthHeight = target.textureHeight;
        lastIrisDepthFormat = depthFormat;
    }

    private final boolean advanceTrailClock() {
        long now = System.nanoTime();
        if (trailClockNs != 0L) {
            trailAccumulator += this.clamp((float)(now - trailClockNs) / 1.0E9f, 0.0f, 0.1f);
        }
        trailClockNs = now;
        if (trailAccumulator >= 0.008333334f) {
            pendingTrailStep = Math.min(trailAccumulator, 0.05f);
            trailAccumulator = 0.0f;
            return true;
        }
        return false;
    }

    private final float consumeTrailStep() {
        return pendingTrailStep;
    }

    private final void writeUniforms(int width, int height, boolean irisDepthMode, float dt) {
        int color = flameColor;
        float alpha = (float)ColorEngine.alpha(color) / 255.0f;
        float targetR = (float)this.channel(color, 16) / 255.0f;
        float targetG = (float)this.channel(color, 8) / 255.0f;
        float targetB = (float)this.channel(color, 0) / 255.0f;
        if (smoothR < 0.0f) {
            smoothR = targetR;
            smoothG = targetG;
            smoothB = targetB;
        } else {
            float k = 1.0f - (float)Math.exp(-dt * 10.0f);
            smoothR += (targetR - smoothR) * k;
            smoothG += (targetG - smoothG) * k;
            smoothB += (targetB - smoothB) * k;
        }
        ByteBuffer byteBuffer = dataBuffer;
        Intrinsics.checkNotNull((Object)byteBuffer);
        ByteBuffer data = byteBuffer;
        data.clear();
        data.putFloat(smoothR);
        data.putFloat(smoothG);
        data.putFloat(smoothB);
        data.putFloat(dt);
        data.putFloat(flameStrength);
        data.putFloat(flameRiseSpeed);
        data.putFloat(flameWobble);
        data.putFloat(flameLength);
        data.putFloat(flameBrightness);
        data.putFloat(this.flameTime());
        data.putFloat((float)flameColorMode + (flameItemsOnly ? 10.0f : 0.0f) + (irisDepthMode ? 20.0f : 0.0f));
        data.putFloat(alpha);
        data.putFloat(width);
        data.putFloat(height);
        data.putFloat(1.0f / (float)Math.max(width, 1));
        data.putFloat(1.0f / (float)Math.max(height, 1));
        boolean gradient = flameClientGradient && ClientPalette.count() >= 2;
        float gradientPhase = ClientPalette.phase() * 20.0f;
        if (gradient) {
            this.putGradientColor(ClientPalette.loopColor(gradientPhase + 0.75f));
            this.putGradientColor(ClientPalette.loopColor(gradientPhase + 0.5f));
            this.putGradientColor(ClientPalette.loopColor(gradientPhase + 0.25f));
            this.putGradientColor(ClientPalette.loopColor(gradientPhase));
        } else {
            for (int i = 0; i < 16; ++i) {
                data.putFloat(0.0f);
            }
        }
        data.putFloat(gradientPhase);
        data.putFloat(gradient ? 1.0f : 0.0f);
        data.putFloat(0.0f);
        data.putFloat(0.0f);
        data.flip();
    }

    private final void putGradientColor(int rgb) {
        ByteBuffer byteBuffer = dataBuffer;
        Intrinsics.checkNotNull((Object)byteBuffer);
        ByteBuffer data = byteBuffer;
        data.putFloat((float)(rgb >> 16 & 0xFF) / 255.0f);
        data.putFloat((float)(rgb >> 8 & 0xFF) / 255.0f);
        data.putFloat((float)(rgb & 0xFF) / 255.0f);
        data.putFloat(1.0f);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void renderTrail(CommandEncoder encoder, Framebuffer target, GpuTextureView depthView, GpuTextureView beforeDepthView, GpuTextureView beforeView, GpuTextureView afterView) {
        GpuTextureView boundsL = null;
        boundsL = ShaderHandsRenderer.handBoundsLeftView();
        GpuTextureView boundsR = null;
        boundsR = ShaderHandsRenderer.handBoundsRightView();
        if (boundsL == null || boundsR == null) {
            if (!this.ensureBlackTexture()) {
                return;
            }
            boundsL = blackTextureView;
            boundsR = blackTextureView;
        }
        Supplier<String> supplier = HandsFlameRenderer::renderTrail$lambda$0;
        GpuTextureView gpuTextureView = this.nextTrailView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty(), null, OptionalDouble.empty());
        Throwable throwable = null;
        try {
            RenderPass renderPass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = trailPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            renderPass.setPipeline(renderPipeline);
            GpuBuffer gpuBuffer = dummyVertexBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            renderPass.setVertexBuffer(0, gpuBuffer);
            renderPass.bindTexture("BeforeSampler", beforeView, RenderSystem.getSamplerCache().get(FilterMode.LINEAR));
            renderPass.bindTexture("AfterSampler", afterView, RenderSystem.getSamplerCache().get(FilterMode.LINEAR));
            renderPass.bindTexture("PrevTrailSampler", INSTANCE.historyTrailView(), RenderSystem.getSamplerCache().get(FilterMode.LINEAR));
            renderPass.bindTexture("DepthSampler", depthView, RenderSystem.getSamplerCache().get(FilterMode.NEAREST));
            renderPass.bindTexture("NoHandDepthSampler", beforeDepthView, RenderSystem.getSamplerCache().get(FilterMode.NEAREST));
            renderPass.bindTexture("BoundsTexL", boundsL, RenderSystem.getSamplerCache().get(FilterMode.NEAREST));
            renderPass.bindTexture("BoundsTexR", boundsR, RenderSystem.getSamplerCache().get(FilterMode.NEAREST));
            GpuBuffer gpuBuffer2 = uniformBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer2);
            renderPass.setUniform("HandsFlameData", gpuBuffer2.slice());
            ThemeWaveUniform.bind(renderPass);
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
    private final void renderComposite(CommandEncoder encoder, Framebuffer target, GpuTextureView depthView, GpuTextureView beforeDepthView, GpuTextureView beforeView, GpuTextureView afterView, GpuTextureView sceneView) {
        Supplier<String> supplier = HandsFlameRenderer::renderComposite$lambda$0;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty(), null, OptionalDouble.empty());
        Throwable throwable = null;
        try {
            RenderPass renderPass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = compositePipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            renderPass.setPipeline(renderPipeline);
            GpuBuffer gpuBuffer = dummyVertexBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            renderPass.setVertexBuffer(0, gpuBuffer);
            renderPass.bindTexture("SceneSampler", sceneView, RenderSystem.getSamplerCache().get(FilterMode.LINEAR));
            renderPass.bindTexture("BeforeSampler", beforeView, RenderSystem.getSamplerCache().get(FilterMode.LINEAR));
            renderPass.bindTexture("AfterSampler", afterView, RenderSystem.getSamplerCache().get(FilterMode.LINEAR));
            renderPass.bindTexture("TrailSampler", INSTANCE.historyTrailView(), RenderSystem.getSamplerCache().get(FilterMode.LINEAR));
            renderPass.bindTexture("DepthSampler", depthView, RenderSystem.getSamplerCache().get(FilterMode.NEAREST));
            renderPass.bindTexture("NoHandDepthSampler", beforeDepthView, RenderSystem.getSamplerCache().get(FilterMode.NEAREST));
            GpuBuffer gpuBuffer2 = uniformBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer2);
            renderPass.setUniform("HandsFlameData", gpuBuffer2.slice());
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
    private final void restoreProtectedNameTags(CommandEncoder encoder, Framebuffer target, GpuTextureView sceneView) {
        int count = NameTags.Companion.copyUnderHandBounds(protectedTagBounds);
        float guiWidth = Position.Companion.screenWidth();
        float guiHeight = Position.Companion.screenHeight();
        if (count <= 0 || guiWidth <= 0.0f || guiHeight <= 0.0f || restorePipeline == null) {
            return;
        }
        float scaleX = (float)target.textureWidth / guiWidth;
        float scaleY = (float)target.textureHeight / guiHeight;
        Supplier<String> supplier = HandsFlameRenderer::restoreProtectedNameTags$lambda$0;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty(), null, OptionalDouble.empty());
        Throwable throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = restorePipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            GpuBuffer gpuBuffer = dummyVertexBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setVertexBuffer(0, gpuBuffer);
            pass.bindTexture("SceneSampler", sceneView, RenderSystem.getSamplerCache().get(FilterMode.NEAREST));
            for (int i = 0; i < count; ++i) {
                int offset = i * 4;
                int left = Math.max(0, (int)Math.floor(protectedTagBounds[offset] * scaleX));
                int top = Math.max(0, (int)Math.floor(protectedTagBounds[offset + 1] * scaleY));
                int right = Math.min(target.textureWidth, (int)Math.ceil((protectedTagBounds[offset] + protectedTagBounds[offset + 2]) * scaleX));
                int bottom = Math.min(target.textureHeight, (int)Math.ceil((protectedTagBounds[offset + 1] + protectedTagBounds[offset + 3]) * scaleY));
                if (right <= left || bottom <= top) continue;
                pass.enableScissor(left, target.textureHeight - bottom, right - left, bottom - top);
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

    private final GpuTextureView historyTrailView() {
        return useTrailAAsHistory ? trailTextureViewA : trailTextureViewB;
    }

    private final GpuTextureView nextTrailView() {
        return useTrailAAsHistory ? trailTextureViewB : trailTextureViewA;
    }

    private final void swapTrailHistory() {
        useTrailAAsHistory = !useTrailAAsHistory;
    }

    private final boolean isUsable(Framebuffer target) {
        return target != null && target.getColorAttachment() != null && target.getColorAttachmentView() != null && target.getDepthAttachmentView() != null && target.textureWidth > 0 && target.textureHeight > 0;
    }

    private final float flameTime() {
        return (float)(System.nanoTime() % 180000000000L) / 1.0E9f;
    }

    private final float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    private final void disableAfterError(Throwable throwable) {
        disabledAfterError = true;
        HandsFlameRenderer.shutdown();
    }

    private final void closeTextures() {
        GpuTextureView gpuTextureView = beforeTextureView;
        if (gpuTextureView != null) {
            gpuTextureView.close();
        }
        GpuTextureView gpuTextureView2 = sceneTextureView;
        if (gpuTextureView2 != null) {
            gpuTextureView2.close();
        }
        GpuTextureView gpuTextureView3 = handTextureView;
        if (gpuTextureView3 != null) {
            gpuTextureView3.close();
        }
        GpuTextureView gpuTextureView4 = trailTextureViewA;
        if (gpuTextureView4 != null) {
            gpuTextureView4.close();
        }
        GpuTextureView gpuTextureView5 = trailTextureViewB;
        if (gpuTextureView5 != null) {
            gpuTextureView5.close();
        }
        GpuTexture gpuTexture = beforeTexture;
        if (gpuTexture != null) {
            gpuTexture.close();
        }
        GpuTexture gpuTexture2 = sceneTexture;
        if (gpuTexture2 != null) {
            gpuTexture2.close();
        }
        GpuTexture gpuTexture3 = handTexture;
        if (gpuTexture3 != null) {
            gpuTexture3.close();
        }
        GpuTexture gpuTexture4 = trailTextureA;
        if (gpuTexture4 != null) {
            gpuTexture4.close();
        }
        GpuTexture gpuTexture5 = trailTextureB;
        if (gpuTexture5 != null) {
            gpuTexture5.close();
        }
        this.closeIrisDepthTextures();
        beforeTextureView = null;
        sceneTextureView = null;
        handTextureView = null;
        trailTextureViewA = null;
        trailTextureViewB = null;
        beforeTexture = null;
        sceneTexture = null;
        handTexture = null;
        trailTextureA = null;
        trailTextureB = null;
        lastWidth = -1;
        lastHeight = -1;
        lastRenderRegion = null;
    }

    private final void closeIrisDepthTextures() {
        for (int i = 0; i < 2; ++i) {
            GpuTextureView gpuTextureView = irisDepthBeforeTextureViews[i];
            if (gpuTextureView != null) {
                gpuTextureView.close();
            }
            GpuTextureView gpuTextureView2 = irisDepthAfterTextureViews[i];
            if (gpuTextureView2 != null) {
                gpuTextureView2.close();
            }
            GpuTexture gpuTexture = irisDepthBeforeTextures[i];
            if (gpuTexture != null) {
                gpuTexture.close();
            }
            GpuTexture gpuTexture2 = irisDepthAfterTextures[i];
            if (gpuTexture2 != null) {
                gpuTexture2.close();
            }
            HandsFlameRenderer.irisDepthBeforeTextureViews[i] = null;
            HandsFlameRenderer.irisDepthAfterTextureViews[i] = null;
            HandsFlameRenderer.irisDepthBeforeTextures[i] = null;
            HandsFlameRenderer.irisDepthAfterTextures[i] = null;
        }
        irisCaptureCount = 0;
        irisOpenCaptureSlot = -1;
        lastIrisDepthWidth = -1;
        lastIrisDepthHeight = -1;
        lastIrisDepthFormat = null;
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }

    private final int channel(int color, int shift) {
        return color >>> shift & 0xFF;
    }

    private final RenderRegion flameRegion(int width, int height) {
        return new RenderRegion(0, 0, width, height);
    }

    private final void ensureRenderRegion(RenderRegion region) {
        if (Intrinsics.areEqual((Object)region, (Object)lastRenderRegion)) {
            return;
        }
        lastRenderRegion = region;
        HandsFlameRenderer.resetTrail();
    }

    private static final String ensureBlackTexture$lambda$0() {
        return "kimiko:hands_flame_black";
    }

    private static final String initPipelines$lambda$0() {
        return "kimiko:hands_flame_uniform";
    }

    private static final String initPipelines$lambda$1() {
        return "kimiko:hands_flame_dummy_vertex";
    }

    private static final String createTexture$lambda$0(String $label) {
        return $label;
    }

    private static final String ensureIrisDepthTextures$lambda$0() {
        return "kimiko:hands_flame_iris_depth_before";
    }

    private static final String ensureIrisDepthTextures$lambda$1() {
        return "kimiko:hands_flame_iris_depth_after";
    }

    private static final String renderTrail$lambda$0() {
        return "kimiko:hands_flame_trail";
    }

    private static final String renderComposite$lambda$0() {
        return "kimiko:hands_flame_composite";
    }

    private static final String restoreProtectedNameTags$lambda$0() {
        return "kimiko:hands_flame_restore_nametags";
    }

    static {
        flameStrength = 0.85f;
        flameWobble = 0.65f;
        flameLength = 0.95f;
        flameBrightness = 0.9f;
        flameColor = ColorEngine.rgba(255, 255, 255, 230);
        irisDepthBeforeTextures = new GpuTexture[2];
        irisDepthAfterTextures = new GpuTexture[2];
        irisDepthBeforeTextureViews = new GpuTextureView[2];
        irisDepthAfterTextureViews = new GpuTextureView[2];
        useTrailAAsHistory = true;
        irisOpenCaptureSlot = -1;
        lastWidth = -1;
        lastHeight = -1;
        lastIrisDepthWidth = -1;
        lastIrisDepthHeight = -1;
        protectedTagBounds = new float[512];
        smoothR = -1.0f;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\nJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\nJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\nJ8\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0014\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0014\u0010\nJ\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\nR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u001a\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u001b\u0010\nR\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u001c\u0010\n\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/utils/render/modules/post/handsflame/HandsFlameRenderer$RenderRegion;", "", "", "x", "y", "width", "height", "<init>", "(IIII)V", "component1", "()I", "component2", "component3", "component4", "copy", "(IIII)Lrtx/kimiko/utils/render/modules/post/handsflame/HandsFlameRenderer$RenderRegion;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "I", "getX", "getY", "getWidth", "getHeight", "rtx.kimiko:kimiko"})
    private static final class RenderRegion {
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        public RenderRegion(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public final int getX() {
            return this.x;
        }

        public final int getY() {
            return this.y;
        }

        public final int getWidth() {
            return this.width;
        }

        public final int getHeight() {
            return this.height;
        }

        public final int component1() {
            return this.x;
        }

        public final int component2() {
            return this.y;
        }

        public final int component3() {
            return this.width;
        }

        public final int component4() {
            return this.height;
        }

        @NotNull
        public final RenderRegion copy(int x, int y, int width, int height) {
            return new RenderRegion(x, y, width, height);
        }

        public static /* synthetic */ RenderRegion copy$default(RenderRegion renderRegion, int n, int n2, int n3, int n4, int n5, Object object) {
            if ((n5 & 1) != 0) {
                n = renderRegion.x;
            }
            if ((n5 & 2) != 0) {
                n2 = renderRegion.y;
            }
            if ((n5 & 4) != 0) {
                n3 = renderRegion.width;
            }
            if ((n5 & 8) != 0) {
                n4 = renderRegion.height;
            }
            return renderRegion.copy(n, n2, n3, n4);
        }

        @NotNull
        public String toString() {
            return "RenderRegion(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ")";
        }

        public int hashCode() {
            int result = Integer.hashCode(this.x);
            result = result * 31 + Integer.hashCode(this.y);
            result = result * 31 + Integer.hashCode(this.width);
            result = result * 31 + Integer.hashCode(this.height);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RenderRegion)) {
                return false;
            }
            RenderRegion renderRegion = (RenderRegion)other;
            if (this.x != renderRegion.x) {
                return false;
            }
            if (this.y != renderRegion.y) {
                return false;
            }
            if (this.width != renderRegion.width) {
                return false;
            }
            return this.height == renderRegion.height;
        }
    }
}

