/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.pipeline.BlendFunction
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Snippet
 *  com.mojang.blaze3d.platform.DepthTestFunction
 *  com.mojang.blaze3d.platform.DestFactor
 *  com.mojang.blaze3d.platform.SourceFactor
 *  com.mojang.blaze3d.systems.CommandEncoder
 *  com.mojang.blaze3d.systems.RenderPass
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.systems.RenderSystem$ShapeIndexBuffer
 *  com.mojang.blaze3d.textures.FilterMode
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gl.GpuSampler
 *  net.minecraft.client.render.BufferBuilder
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gl.SimpleFramebuffer
 *  net.minecraft.client.util.BufferAllocator
 *  net.minecraft.client.render.BuiltBuffer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.lwjgl.system.MemoryStack
 */
package rtx.kimiko.utils.render.render2d.glow;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.platform.DestFactor;
import com.mojang.blaze3d.platform.SourceFactor;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gl.SimpleFramebuffer;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.render.BuiltBuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.lwjgl.system.MemoryStack;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.ui.theme.ThemeWave;
import rtx.kimiko.utils.render.others.profiler.RenderProfiler;
import rtx.kimiko.utils.render.render2d.BatchOverflow;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.render.render2d.ClientSplits;
import rtx.kimiko.utils.render.render2d.GradientSweep;
import rtx.kimiko.utils.render.render2d.RefreshRateThrottle;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.render2d.ThemeWaveUniform;
import rtx.kimiko.utils.render.render2d.glow.BuiltGlow;
import rtx.kimiko.utils.render.render2d.glow.GlowCapture;
import rtx.kimiko.utils.render.render2d.glow.GlowRenderState;
import rtx.kimiko.utils.render.util.scissor.ScissorUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00bc\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0016\u0018\u0000 \u008e\u00012\u00060\u0001j\u0002`\u0002:\u0004\u008f\u0001\u008e\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\tJ!\u0010\f\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0004J\r\u0010\u0011\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0004J\u001d\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001e\u001a\u00020\u00072\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\r\u0010 \u001a\u00020\u0007\u00a2\u0006\u0004\b \u0010\u0004J\r\u0010!\u001a\u00020\u0007\u00a2\u0006\u0004\b!\u0010\u0004J\u001f\u0010%\u001a\u00020$2\u0006\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b%\u0010&J/\u0010+\u001a\u00020\u00072\u0006\u0010(\u001a\u00020'2\u0006\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u00142\u0006\u0010*\u001a\u00020)H\u0002\u00a2\u0006\u0004\b+\u0010,J\u0017\u0010-\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b-\u0010.J\u0019\u00100\u001a\u00020\u00072\b\u0010/\u001a\u0004\u0018\u00010'H\u0002\u00a2\u0006\u0004\b0\u00101Ja\u00108\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\n2\u0006\u00103\u001a\u00020\u00122\b\u0010/\u001a\u0004\u0018\u00010'2\u0006\u00104\u001a\u00020\u00142\u0006\u00105\u001a\u00020\u00142\u0006\u00106\u001a\u00020\u00142\u0006\u00107\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b8\u00109J)\u0010:\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\n2\b\u0010/\u001a\u0004\u0018\u00010'H\u0002\u00a2\u0006\u0004\b:\u0010;Jk\u0010F\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010=\u001a\u0004\u0018\u00010<2\u0006\u0010>\u001a\u00020\u00142\u0006\u0010?\u001a\u00020\u00142\b\u0010/\u001a\u0004\u0018\u00010'2\u0006\u0010A\u001a\u00020@2\u0006\u0010B\u001a\u00020@2\u0006\u0010C\u001a\u00020@2\u0006\u0010D\u001a\u00020@2\u0006\u0010E\u001a\u00020@2\u0006\u0010*\u001a\u00020)H\u0002\u00a2\u0006\u0004\bF\u0010GJ5\u0010J\u001a\u00020\u00072\b\u0010H\u001a\u0004\u0018\u00010'2\b\u0010I\u001a\u0004\u0018\u00010'2\b\u0010/\u001a\u0004\u0018\u00010'2\u0006\u0010*\u001a\u00020)H\u0002\u00a2\u0006\u0004\bJ\u0010KJ\u0017\u0010O\u001a\u00020N2\u0006\u0010M\u001a\u00020LH\u0002\u00a2\u0006\u0004\bO\u0010PJ\u0011\u0010R\u001a\u0004\u0018\u00010QH\u0002\u00a2\u0006\u0004\bR\u0010SJ\u0011\u0010T\u001a\u0004\u0018\u00010QH\u0002\u00a2\u0006\u0004\bT\u0010SJ\u0011\u0010U\u001a\u0004\u0018\u00010QH\u0002\u00a2\u0006\u0004\bU\u0010SJ\u0011\u0010V\u001a\u0004\u0018\u00010QH\u0002\u00a2\u0006\u0004\bV\u0010SJ\u0017\u0010Z\u001a\u00020Y2\u0006\u0010X\u001a\u00020WH\u0002\u00a2\u0006\u0004\bZ\u0010[J)\u0010^\u001a\u0004\u0018\u00010'2\u0006\u00102\u001a\u00020\u00142\u0006\u0010\\\u001a\u00020\u00142\u0006\u0010]\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b^\u0010_J)\u0010`\u001a\u0004\u0018\u00010'2\u0006\u00102\u001a\u00020\u00142\u0006\u0010\\\u001a\u00020\u00142\u0006\u0010]\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b`\u0010_J)\u0010a\u001a\u0004\u0018\u00010'2\u0006\u00102\u001a\u00020\u00142\u0006\u0010\\\u001a\u00020\u00142\u0006\u0010]\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\ba\u0010_J/\u0010c\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u00142\u0006\u0010\\\u001a\u00020\u00142\u0006\u0010]\u001a\u00020\u00142\u0006\u0010b\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\bc\u0010dJ1\u0010g\u001a\u00020'2\b\u0010/\u001a\u0004\u0018\u00010'2\u0006\u0010f\u001a\u00020e2\u0006\u0010\\\u001a\u00020\u00142\u0006\u0010]\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\bg\u0010hJ#\u0010i\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\bi\u0010\rJ\u000f\u0010j\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\bj\u0010\u0004J\u000f\u0010k\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\bk\u0010\u0004J\u000f\u0010l\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\bl\u0010\u0004R4\u0010p\u001a\"\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010'0n0mj\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010'0n`o8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bp\u0010qR4\u0010r\u001a\"\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010'0n0mj\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010'0n`o8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\br\u0010qR(\u0010s\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010'0mj\n\u0012\u0006\u0012\u0004\u0018\u00010'`o8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bs\u0010qR(\u0010t\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010'0mj\n\u0012\u0006\u0012\u0004\u0018\u00010'`o8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bt\u0010qR(\u0010u\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010'0mj\n\u0012\u0006\u0012\u0004\u0018\u00010'`o8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bu\u0010qR$\u0010w\u001a\u0012\u0012\u0004\u0012\u00020v0mj\b\u0012\u0004\u0012\u00020v`o8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bw\u0010qR$\u0010x\u001a\u0012\u0012\u0004\u0012\u00020\n0mj\b\u0012\u0004\u0012\u00020\n`o8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bx\u0010qR$\u0010y\u001a\u0012\u0012\u0004\u0012\u00020\u00120mj\b\u0012\u0004\u0012\u00020\u0012`o8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\by\u0010qR\u0016\u0010{\u001a\u00020z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010|R\u0016\u0010}\u001a\u00020z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b}\u0010|R\u0016\u0010~\u001a\u00020z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b~\u0010|R\u0016\u0010\u007f\u001a\u00020z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u007f\u0010|R\u001b\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001R\u001b\u0010\u0082\u0001\u001a\u0004\u0018\u00010Q8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001R\u001b\u0010\u0084\u0001\u001a\u0004\u0018\u00010Q8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0084\u0001\u0010\u0083\u0001R\u001b\u0010\u0085\u0001\u001a\u0004\u0018\u00010Q8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0085\u0001\u0010\u0083\u0001R\u0019\u0010\u0086\u0001\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0019\u0010\u0088\u0001\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0088\u0001\u0010\u0087\u0001R\u0017\u0010\u0089\u0001\u001a\u00020\u00128\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0089\u0001\u0010\u008a\u0001R\u0019\u0010\u008b\u0001\u001a\u00020$8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0019\u0010\u008d\u0001\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008d\u0001\u0010\u0087\u0001\u00a8\u0006\u0090\u0001"}, d2={"Lrtx/kimiko/utils/render/render2d/glow/GlowRenderer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lnet/minecraft/DrawContext;", "graphics", "", "beginFrame", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;", "glow", "draw", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;)V", "enqueue", "(Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;)V", "flush", "beginGuiFrame", "Lrtx/kimiko/utils/render/render2d/glow/GlowCapture;", "capture", "", "reserve", "(Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;Lrtx/kimiko/utils/render/render2d/glow/GlowCapture;)I", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "", "isGlowPipeline", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Z", "Lcom/mojang/blaze3d/systems/RenderPass;", "renderPass", "bindParams", "(Lcom/mojang/blaze3d/systems/RenderPass;)V", "prepareBuffers", "preparePending", "atlasW", "atlasH", "", "atlasSig", "(II)J", "Lnet/minecraft/SimpleFramebuffer;", "result", "Lnet/minecraft/GpuSampler;", "sampler", "assignCaptures", "(Lnet/minecraft/SimpleFramebuffer;IILnet/minecraft/GpuSampler;)V", "packTiles", "(I)I", "target", "clearTarget", "(Lnet/minecraft/SimpleFramebuffer;)V", "index", "cap", "tx", "ty", "tw", "th", "renderSourceShape", "(ILrtx/kimiko/utils/render/render2d/glow/BuiltGlow;Lrtx/kimiko/utils/render/render2d/glow/GlowCapture;Lnet/minecraft/SimpleFramebuffer;IIIIII)V", "renderShape", "(ILrtx/kimiko/utils/render/render2d/glow/BuiltGlow;Lnet/minecraft/SimpleFramebuffer;)V", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "source", "sourceWidth", "sourceHeight", "", "offsetScale", "sourceX", "sourceY", "sourceW", "sourceH", "renderKawasePass", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lcom/mojang/blaze3d/textures/GpuTextureView;IILnet/minecraft/SimpleFramebuffer;FFFFFLnet/minecraft/GpuSampler;)V", "blurred", "sharpMask", "renderCutoutPass", "(Lnet/minecraft/SimpleFramebuffer;Lnet/minecraft/SimpleFramebuffer;Lnet/minecraft/SimpleFramebuffer;Lnet/minecraft/GpuSampler;)V", "Lorg/lwjgl/system/MemoryStack;", "stack", "Ljava/nio/ByteBuffer;", "buildUniformData", "(Lorg/lwjgl/system/MemoryStack;)Ljava/nio/ByteBuffer;", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "ensureParamsBuffer", "()Lcom/mojang/blaze3d/buffers/GpuBuffer;", "ensureWritableParamsBuffer", "ensureKawaseParamsBuffer", "ensureFullscreenQuadBuffer", "Lnet/minecraft/BufferAllocator;", "alloc", "Lnet/minecraft/BuiltBuffer;", "buildFullscreenQuad", "(Lnet/minecraft/BufferAllocator;)Lnet/minecraft/BuiltBuffer;", "width", "height", "ensureShapeTarget", "(III)Lnet/minecraft/SimpleFramebuffer;", "ensureBlurTarget", "ensureResultTarget", "iterations", "ensureScratchTargets", "(IIII)V", "", "name", "ensureTarget", "(Lnet/minecraft/SimpleFramebuffer;Ljava/lang/String;II)Lnet/minecraft/SimpleFramebuffer;", "submit", "closeParamsBuffer", "closeKawaseParamsBuffer", "close", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "downPool", "Ljava/util/ArrayList;", "upPool", "shapeTargets", "blurTargets", "resultTargets", "Lrtx/kimiko/utils/render/render2d/glow/GlowRenderer$PendingGlow;", "pendingGlows", "preparedGlows", "preparedCaptures", "", "tileX", "[I", "tileY", "tileW", "tileH", "activeGraphics", "Lnet/minecraft/DrawContext;", "paramsBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "kawaseParamsBuffer", "fullscreenQuadBuffer", "paramsDirty", "Z", "globalCompositeSubmitted", "globalCapture", "Lrtx/kimiko/utils/render/render2d/glow/GlowCapture;", "lastAtlasSig", "J", "atlasValid", "Companion", "PendingGlow", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nGlowRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GlowRenderer.kt\nrtx/kimiko/utils/render/render2d/glow/GlowRenderer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1102:1\n1#2:1103\n*E\n"})
public final class GlowRenderer
implements AutoCloseable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ArrayList<SimpleFramebuffer[]> downPool = new ArrayList(224);
    @NotNull
    private final ArrayList<SimpleFramebuffer[]> upPool = new ArrayList(224);
    @NotNull
    private final ArrayList<SimpleFramebuffer> shapeTargets = new ArrayList(224);
    @NotNull
    private final ArrayList<SimpleFramebuffer> blurTargets = new ArrayList(224);
    @NotNull
    private final ArrayList<SimpleFramebuffer> resultTargets = new ArrayList(224);
    @NotNull
    private final ArrayList<PendingGlow> pendingGlows = new ArrayList(32);
    @NotNull
    private final ArrayList<BuiltGlow> preparedGlows = new ArrayList(32);
    @NotNull
    private final ArrayList<GlowCapture> preparedCaptures = new ArrayList(32);
    @NotNull
    private int[] tileX = new int[224];
    @NotNull
    private int[] tileY = new int[224];
    @NotNull
    private int[] tileW = new int[224];
    @NotNull
    private int[] tileH = new int[224];
    @Nullable
    private DrawContext activeGraphics;
    @Nullable
    private GpuBuffer paramsBuffer;
    @Nullable
    private GpuBuffer kawaseParamsBuffer;
    @Nullable
    private GpuBuffer fullscreenQuadBuffer;
    private boolean paramsDirty = true;
    private boolean globalCompositeSubmitted;
    @NotNull
    private final GlowCapture globalCapture = new GlowCapture();
    private long lastAtlasSig;
    private boolean atlasValid;
    private static final float TILE_SCALE = 1.0f;
    private static final int ATLAS_WIDTH = 2048;
    private static final int ATLAS_MAX_HEIGHT = 2048;
    private static final int ATLAS_HEIGHT_BUCKET = 256;
    private static final int TILE_GAP = 8;
    private static final int MAX_GLOWS = 224;
    private static final int PARAMS_PER_GLOW = 5;
    private static final int FLOATS_PER_PARAM = 4;
    private static final int UNIFORM_BYTES = 17920;
    private static final int KAWASE_UNIFORM_BYTES = 48;
    private static final int MAX_BLUR_ITERATIONS = 4;
    private static final int MAX_SPANS = 64;
    private static final int PALETTE_BASE_VEC4 = 70;
    private static final int PALETTE_VEC4 = 8;
    private static final int WAVE_MAP_VEC4 = 78;
    private static final int SHAPE_PARAMS_VEC4 = 79;
    private static final int SHAPE_BYTES = 1264;
    @Nullable
    private static volatile GlowRenderer instance;
    @NotNull
    private static final Map<BuiltGlow, Float> waveZoom;
    @JvmField
    @NotNull
    public static final RenderPipeline GLOW_COMPOSITE_PIPELINE;
    @NotNull
    private static final RenderPipeline GLOW_SHAPE_PIPELINE;
    @NotNull
    private static final RenderPipeline GLOW_SOURCE_PIPELINE;
    @NotNull
    private static final RenderPipeline GLOW_BLUR_DOWN_PIPELINE;
    @NotNull
    private static final RenderPipeline GLOW_BLUR_UP_PIPELINE;
    @NotNull
    private static final RenderPipeline GLOW_CUTOUT_PIPELINE;

    private GlowRenderer() {
    }

    public final void beginFrame(@Nullable DrawContext graphics) {
        this.activeGraphics = graphics;
    }

    public final void draw(@Nullable DrawContext graphics, @Nullable BuiltGlow glow) {
        this.beginFrame(graphics);
        this.enqueue(glow);
        this.flush();
    }

    public final void enqueue(@Nullable BuiltGlow glow) {
        this.submit(this.activeGraphics, glow);
    }

    public final void flush() {
        this.activeGraphics = null;
    }

    public final void beginGuiFrame() {
        this.preparedGlows.clear();
        this.preparedCaptures.clear();
        waveZoom.clear();
        this.paramsDirty = false;
        this.globalCompositeSubmitted = false;
    }

    public final int reserve(@NotNull BuiltGlow glow, @NotNull GlowCapture capture) {
        Intrinsics.checkNotNullParameter((Object)glow, (String)"glow");
        Intrinsics.checkNotNullParameter((Object)capture, (String)"capture");
        int index = this.preparedGlows.size();
        if (index >= 224) {
            return BatchOverflow.drop("glow", 224);
        }
        this.preparedGlows.add(glow);
        this.preparedCaptures.add(capture);
        this.paramsDirty = true;
        return index;
    }

    public final boolean isGlowPipeline(@Nullable RenderPipeline pipeline) {
        return pipeline == GLOW_COMPOSITE_PIPELINE;
    }

    public final void bindParams(@Nullable RenderPass renderPass) {
        if (renderPass == null || this.preparedGlows.isEmpty()) {
            return;
        }
        GpuBuffer buffer = this.ensureParamsBuffer();
        if (buffer != null) {
            renderPass.setUniform("GlowParamsArray", buffer);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void prepareBuffers() {
        if (this.preparedGlows.isEmpty() || !this.paramsDirty) {
            return;
        }
        GpuBuffer gpuBuffer = this.ensureWritableParamsBuffer();
        if (gpuBuffer == null) {
            return;
        }
        GpuBuffer buffer = gpuBuffer;
        try {
            AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
            Throwable throwable = null;
            try {
                MemoryStack stack = (MemoryStack)autoCloseable;
                boolean bl = false;
                Intrinsics.checkNotNull((Object)stack);
                ByteBuffer uniformData = this.buildUniformData(stack);
                RenderSystem.getDevice().createCommandEncoder().writeToBuffer(buffer.slice(0L, (long)uniformData.remaining()), uniformData);
                this.paramsDirty = false;
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
        catch (RuntimeException ignored) {
            this.paramsDirty = true;
        }
    }

    public final void preparePending() {
        if (this.pendingGlows.isEmpty()) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.getWindow() == null) {
            this.pendingGlows.clear();
            return;
        }
        int packedHeight = this.packTiles(2048);
        int bw = 2048;
        int bh = Math.max(256, Math.min(2048, (packedHeight + 256 - 1) / 256 * 256));
        SimpleFramebuffer source = this.ensureShapeTarget(0, bw, bh);
        SimpleFramebuffer blurTarget = this.ensureBlurTarget(0, bw, bh);
        SimpleFramebuffer result = this.ensureResultTarget(0, bw, bh);
        if (source == null || blurTarget == null || result == null) {
            this.pendingGlows.clear();
            return;
        }
        GpuSampler gpuSampler2 = RenderSystem.getSamplerCache().get(FilterMode.LINEAR);
        Intrinsics.checkNotNullExpressionValue((Object)gpuSampler2, (String)"getClampToEdge(...)");
        GpuSampler sampler = gpuSampler2;
        long sig = this.atlasSig(bw, bh);
        if (!RefreshRateThrottle.updateFrame() && this.atlasValid && sig == this.lastAtlasSig) {
            this.assignCaptures(result, bw, bh, sampler);
            this.pendingGlows.clear();
            return;
        }
        this.lastAtlasSig = sig;
        this.atlasValid = true;
        RenderProfiler.Scope tilesScope = RenderProfiler.begin("ui.glow.tiles");
        this.clearTarget(source);
        float maxBlurRadius = 0.0f;
        int n = ((Collection)this.pendingGlows).size();
        for (int i = 0; i < n; ++i) {
            PendingGlow pg = (PendingGlow) (this.pendingGlows.get(i));
            if (this.tileW[i] > 0 && this.tileH[i] > 0) {
                this.renderSourceShape(i, pg.getGlow(), pg.getCapture(), source, this.tileX[i], this.tileY[i], this.tileW[i], this.tileH[i], bw, bh);
            } else {
                pg.getCapture().regionU0 = 0.0f;
                pg.getCapture().regionV0 = 0.0f;
                pg.getCapture().regionUW = 0.0f;
                pg.getCapture().regionVH = 0.0f;
            }
            maxBlurRadius = Math.max(maxBlurRadius, pg.getGlow().blurRadius());
        }
        RenderProfiler.end(tilesScope);
        RenderProfiler.Scope blurScope = RenderProfiler.begin("ui.glow.blur");
        int iterations = 3;
        float offsetScale = GlowRenderer.Companion.clamp(maxBlurRadius / 20.0f, 0.5f, 4.0f) * 1.0f;
        this.ensureScratchTargets(0, bw, bh, iterations);
        SimpleFramebuffer[] class_6367Array = this.downPool.get(0);
        Intrinsics.checkNotNullExpressionValue((Object)class_6367Array, (String)"get(...)");
        SimpleFramebuffer[] downTargets = class_6367Array;
        SimpleFramebuffer[] class_6367Array2 = this.upPool.get(0);
        Intrinsics.checkNotNullExpressionValue((Object)class_6367Array2, (String)"get(...)");
        SimpleFramebuffer[] upTargets = class_6367Array2;
        GpuTextureView gpuTextureView = source.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        GpuTextureView src = gpuTextureView;
        int srcW = source.textureWidth;
        int srcH = source.textureHeight;
        for (int d = 0; d < iterations; ++d) {
            this.renderKawasePass(GLOW_BLUR_DOWN_PIPELINE, src, srcW, srcH, downTargets[d], offsetScale, 0.0f, 0.0f, 1.0f, 1.0f, sampler);
            SimpleFramebuffer simpleFramebuffer2 = downTargets[d];
            Intrinsics.checkNotNull((Object)simpleFramebuffer2);
            Intrinsics.checkNotNull((Object)simpleFramebuffer2.getColorAttachmentView());
            SimpleFramebuffer simpleFramebuffer3 = downTargets[d];
            Intrinsics.checkNotNull((Object)simpleFramebuffer3);
            srcW = simpleFramebuffer3.textureWidth;
            SimpleFramebuffer simpleFramebuffer4 = downTargets[d];
            Intrinsics.checkNotNull((Object)simpleFramebuffer4);
            srcH = simpleFramebuffer4.textureHeight;
        }
        for (int u = iterations - 2; -1 < u; --u) {
            this.renderKawasePass(GLOW_BLUR_UP_PIPELINE, src, srcW, srcH, upTargets[u], offsetScale, 0.0f, 0.0f, 1.0f, 1.0f, sampler);
            SimpleFramebuffer simpleFramebuffer5 = upTargets[u];
            Intrinsics.checkNotNull((Object)simpleFramebuffer5);
            Intrinsics.checkNotNull((Object)simpleFramebuffer5.getColorAttachmentView());
            SimpleFramebuffer simpleFramebuffer6 = upTargets[u];
            Intrinsics.checkNotNull((Object)simpleFramebuffer6);
            srcW = simpleFramebuffer6.textureWidth;
            SimpleFramebuffer simpleFramebuffer7 = upTargets[u];
            Intrinsics.checkNotNull((Object)simpleFramebuffer7);
            srcH = simpleFramebuffer7.textureHeight;
        }
        this.renderKawasePass(GLOW_BLUR_UP_PIPELINE, src, srcW, srcH, blurTarget, offsetScale, 0.0f, 0.0f, 1.0f, 1.0f, sampler);
        this.renderCutoutPass(blurTarget, source, result, sampler);
        RenderProfiler.end(blurScope);
        GpuTextureView gpuTextureView2 = result.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView2);
        TextureSetup textureSetup2 = TextureSetup.of((GpuTextureView)gpuTextureView2, (GpuSampler)sampler);
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup2, (String)"singleTexture(...)");
        TextureSetup setup = textureSetup2;
        int n2 = ((Collection)this.pendingGlows).size();
        for (int i = 0; i < n2; ++i) {
            this.pendingGlows.get((int)i).getCapture().setup = setup;
        }
        this.pendingGlows.clear();
    }

    private final long atlasSig(int atlasW, int atlasH) {
        long sig = 1469598103934665603L;
        sig = (sig ^ (long)atlasW) * 1099511628211L;
        sig = (sig ^ (long)atlasH) * 1099511628211L;
        sig = (sig ^ (long)this.pendingGlows.size()) * 1099511628211L;
        int n = ((Collection)this.pendingGlows).size();
        for (int i = 0; i < n; ++i) {
            BuiltGlow glow = this.pendingGlows.get(i).getGlow();
            sig = (sig ^ (long)Float.floatToIntBits(glow.width())) * 1099511628211L;
            sig = (sig ^ (long)Float.floatToIntBits(glow.height())) * 1099511628211L;
            sig = (sig ^ (long)Float.floatToIntBits(glow.radiusTopLeft())) * 1099511628211L;
            sig = (sig ^ (long)Float.floatToIntBits(glow.radiusTopRight())) * 1099511628211L;
            sig = (sig ^ (long)Float.floatToIntBits(glow.radiusBottomRight())) * 1099511628211L;
            sig = (sig ^ (long)Float.floatToIntBits(glow.radiusBottomLeft())) * 1099511628211L;
            sig = (sig ^ (long)Float.floatToIntBits(glow.blurRadius())) * 1099511628211L;
            sig = (sig ^ (long)Float.floatToIntBits(glow.intensity())) * 1099511628211L;
            sig = (sig ^ (long)Float.floatToIntBits(glow.alpha())) * 1099511628211L;
            sig = (sig ^ (long)glow.splitIndex()) * 1099511628211L;
            sig = (sig ^ (long)glow.spanCount()) * 1099511628211L;
            float[] spans = glow.spans();
            if (spans == null) continue;
            int limit = Math.min(spans.length, 128);
            for (int s = 0; s < limit; ++s) {
                sig = (sig ^ (long)Float.floatToIntBits(spans[s])) * 1099511628211L;
            }
        }
        return sig == 0L ? 1L : sig;
    }

    private final void assignCaptures(SimpleFramebuffer result, int atlasW, int atlasH, GpuSampler sampler) {
        GpuTextureView gpuTextureView = result.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        TextureSetup textureSetup2 = TextureSetup.of((GpuTextureView)gpuTextureView, (GpuSampler)sampler);
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup2, (String)"singleTexture(...)");
        TextureSetup setup = textureSetup2;
        int n = ((Collection)this.pendingGlows).size();
        for (int i = 0; i < n; ++i) {
            PendingGlow pg = (PendingGlow) (this.pendingGlows.get(i));
            GlowCapture cap = pg.getCapture();
            if (this.tileW[i] > 0 && this.tileH[i] > 0) {
                float pad = pg.getGlow().effectivePad();
                float exactW = Math.min((pg.getGlow().width() + pad * 2.0f) * 1.0f, (float)this.tileW[i]);
                float exactH = Math.min((pg.getGlow().height() + pad * 2.0f) * 1.0f, (float)this.tileH[i]);
                cap.regionU0 = (float)this.tileX[i] / (float)atlasW;
                cap.regionV0 = (float)this.tileY[i] / (float)atlasH;
                cap.regionUW = Math.max(exactW / (float)atlasW, 1.0E-6f);
                cap.regionVH = Math.max(exactH / (float)atlasH, 1.0E-6f);
            } else {
                cap.regionU0 = 0.0f;
                cap.regionV0 = 0.0f;
                cap.regionUW = 0.0f;
                cap.regionVH = 0.0f;
            }
            cap.setup = setup;
        }
    }

    private final int packTiles(int atlasW) {
        int gap;
        int n = this.pendingGlows.size();
        if (this.tileX.length < n) {
            this.tileX = new int[n];
            this.tileY = new int[n];
            this.tileW = new int[n];
            this.tileH = new int[n];
        }
        float maxPad = 8.0f;
        for (int i = 0; i < n; ++i) {
            maxPad = Math.max(maxPad, this.pendingGlows.get(i).getGlow().effectivePad());
        }
        int cursorX = gap = Math.max(8, Math.round(maxPad * 1.0f));
        int cursorY = gap;
        int rowH = 0;
        for (int i = 0; i < n; ++i) {
            BuiltGlow glow = this.pendingGlows.get(i).getGlow();
            float pad = glow.effectivePad();
            int tw = Math.max(Math.round((glow.width() + pad * 2.0f) * 1.0f), 1);
            int th = Math.max(Math.round((glow.height() + pad * 2.0f) * 1.0f), 1);
            if (cursorX + tw + gap > atlasW) {
                cursorX = gap;
                cursorY += rowH + gap;
                rowH = 0;
            }
            if (tw + gap * 2 > atlasW || cursorY + th + gap > 2048) {
                this.tileW[i] = 0;
                this.tileH[i] = 0;
                continue;
            }
            this.tileX[i] = cursorX;
            this.tileY[i] = cursorY;
            this.tileW[i] = tw;
            this.tileH[i] = th;
            cursorX += tw + gap;
            rowH = Math.max(rowH, th);
        }
        return cursorY + rowH + gap;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void clearTarget(SimpleFramebuffer target) {
        if (target == null) {
            return;
        }
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        Supplier<String> supplier = GlowRenderer::clearTarget$lambda$0;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0), null, OptionalDouble.empty());
        Throwable throwable = null;
        try {
            RenderPass it = (RenderPass)autoCloseable;
            boolean bl = false;
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
    private final void renderSourceShape(int index, BuiltGlow glow, GlowCapture cap, SimpleFramebuffer target, int tx, int ty, int tw, int th, int atlasW, int atlasH) {
        if (target == null) {
            return;
        }
        float pad = glow.effectivePad();
        float exactW = Math.min((glow.width() + pad * 2.0f) * 1.0f, (float)tw);
        float exactH = Math.min((glow.height() + pad * 2.0f) * 1.0f, (float)th);
        cap.regionU0 = (float)tx / (float)atlasW;
        cap.regionV0 = (float)ty / (float)atlasH;
        cap.regionUW = Math.max(exactW / (float)atlasW, 1.0E-6f);
        cap.regionVH = Math.max(exactH / (float)atlasH, 1.0E-6f);
        float nx0 = (float)tx / (float)atlasW * 2.0f - 1.0f;
        float ny0 = (float)ty / (float)atlasH * 2.0f - 1.0f;
        float nx1 = ((float)tx + exactW) / (float)atlasW * 2.0f - 1.0f;
        float ny1 = ((float)ty + exactH) / (float)atlasH * 2.0f - 1.0f;
        GpuBuffer quad = null;
        try (BufferAllocator alloc = new BufferAllocator(4 * VertexFormats.POSITION.getVertexSize())) {
            BufferBuilder bb = new BufferBuilder(alloc, VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
            bb.vertex(nx0, ny0, 0.0f);
            bb.vertex(nx0, ny1, 0.0f);
            bb.vertex(nx1, ny1, 0.0f);
            bb.vertex(nx1, ny0, 0.0f);
            try (BuiltBuffer mesh = bb.end()) {
                quad = RenderSystem.getDevice().createBuffer(() -> GlowRenderer.renderSourceShape$lambda$0$0$0(index), 32, mesh.getBuffer());
            }
        }
        catch (RuntimeException ignored) {
            return;
        }
        GpuBuffer gpuBuffer = quad;
        if (gpuBuffer == null) {
            return;
        }
        GpuBuffer quadBuffer = gpuBuffer;
        GpuBuffer shapeParams = null;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            ByteBuffer data = stack.calloc(SHAPE_BYTES);
            GlowRenderer.Companion.writeGlowParams(data, 0, glow, 0.0f, 0.0f, 1.0f, 1.0f);
            data.putFloat(48, glow.splitIndex());
            data.putFloat(52, glow.waveFreq());
            data.putFloat(56, glow.wavePhase());
            data.putFloat(60, glow.waveEnabled() ? 1.0f : 0.0f);
            GlowRenderer.Companion.writeShapeSpans(data, glow);
            GlowRenderer.Companion.writePalette(data, glow);
            GlowRenderer.Companion.writeWaveMap(data, glow);
            data.position(0);
            shapeParams = RenderSystem.getDevice().createBuffer(() -> GlowRenderer.renderSourceShape$lambda$1$0(index), 136, data);
        }
        catch (RuntimeException ignored) {
            quadBuffer.close();
            return;
        }
        if (shapeParams == null) {
            quadBuffer.close();
            return;
        }
        GpuBuffer params = shapeParams;
        try {
            CommandEncoder encoder = RenderSystem.getDevice().createCommandEncoder();
            RenderSystem.ShapeIndexBuffer indexBuf = RenderSystem.getSequentialBuffer(VertexFormat.DrawMode.QUADS);
            GpuBuffer indices = indexBuf.getIndexBuffer(6);
            Supplier<String> supplier = () -> GlowRenderer.renderSourceShape$lambda$3(index);
            GpuTextureView gpuTextureView = target.getColorAttachmentView();
            try (RenderPass pass = encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty(), null, OptionalDouble.empty())) {
                pass.setPipeline(GLOW_SOURCE_PIPELINE);
                pass.setUniform("GlowParamsArray", params);
                GpuBuffer splits = ClientSplits.buffer();
                if (splits != null) {
                    pass.setUniform("SplitParams", splits);
                }
                ThemeWaveUniform.bind(pass);
                pass.setVertexBuffer(0, quadBuffer);
                pass.setIndexBuffer(indices, indexBuf.getIndexType());
                pass.drawIndexed(0, 0, 6, 1);
            }
        }
        catch (RuntimeException runtimeException) {
        }
        finally {
            params.close();
            quadBuffer.close();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void renderShape(int index, BuiltGlow glow, SimpleFramebuffer target) {
        GpuBuffer quad = this.ensureFullscreenQuadBuffer();
        if (quad == null || target == null) {
            return;
        }
        GpuBuffer shapeParams = null;
        try {
            AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
            Throwable throwable = null;
            try {
                MemoryStack stack = (MemoryStack)autoCloseable;
                boolean bl = false;
                ByteBuffer data = stack.calloc(SHAPE_BYTES);
                Intrinsics.checkNotNull((Object)data);
                GlowRenderer.Companion.writeGlowParams(data, 0, glow, 0.0f, 0.0f, 1.0f, 1.0f);
                data.putFloat(48, glow.splitIndex());
                data.putFloat(52, glow.waveFreq());
                data.putFloat(56, glow.wavePhase());
                data.putFloat(60, glow.waveEnabled() ? 1.0f : 0.0f);
                GlowRenderer.Companion.writeShapeSpans(data, glow);
                GlowRenderer.Companion.writePalette(data, glow);
                GlowRenderer.Companion.writeWaveMap(data, glow);
                data.position(0);
                shapeParams = RenderSystem.getDevice().createBuffer(() -> GlowRenderer.renderShape$lambda$0$0(index), 136, data);
// stack = Unit.INSTANCE;
            }
            catch (Throwable bl) {
                throwable = bl;
                throw bl;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
            }
        }
        catch (RuntimeException ignored) {
            return;
        }
        GpuBuffer gpuBuffer = shapeParams;
        if (gpuBuffer == null) {
            return;
        }
        try (GpuBuffer params = gpuBuffer;){
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            RenderSystem.ShapeIndexBuffer shapeIndexBuffer2 = RenderSystem.getSequentialBuffer((VertexFormat.DrawMode)VertexFormat.DrawMode.QUADS);
            Intrinsics.checkNotNullExpressionValue((Object)shapeIndexBuffer2, (String)"getSequentialBuffer(...)");
            RenderSystem.ShapeIndexBuffer indexBuf = shapeIndexBuffer2;
            GpuBuffer gpuBuffer2 = indexBuf.getIndexBuffer(6);
            Intrinsics.checkNotNullExpressionValue((Object)gpuBuffer2, (String)"getBuffer(...)");
            GpuBuffer indices = gpuBuffer2;
            Supplier<String> supplier = () -> GlowRenderer.renderShape$lambda$1(index);
            GpuTextureView gpuTextureView = target.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0), null, OptionalDouble.empty());
            Throwable throwable = null;
            try {
                RenderPass pass = (RenderPass)autoCloseable;
                boolean bl = false;
                pass.setPipeline(GLOW_SHAPE_PIPELINE);
                pass.setUniform("GlowParamsArray", params);
                GpuBuffer splits = ClientSplits.buffer();
                if (splits != null) {
                    pass.setUniform("SplitParams", splits);
                }
                ThemeWaveUniform.bind(pass);
                pass.setVertexBuffer(0, quad);
                pass.setIndexBuffer(indices, indexBuf.getIndexType());
                pass.drawIndexed(0, 0, 6, 1);
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
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void renderKawasePass(RenderPipeline pipeline, GpuTextureView source, int sourceWidth, int sourceHeight, SimpleFramebuffer target, float offsetScale, float sourceX, float sourceY, float sourceW, float sourceH, GpuSampler sampler) {
        if (source == null || target == null) {
            return;
        }
        GpuBuffer gpuBuffer = this.ensureFullscreenQuadBuffer();
        if (gpuBuffer == null) {
            return;
        }
        GpuBuffer quad = gpuBuffer;
        GpuBuffer gpuBuffer2 = this.ensureKawaseParamsBuffer();
        if (gpuBuffer2 == null) {
            return;
        }
        GpuBuffer kawaseUBO = gpuBuffer2;
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        RenderSystem.ShapeIndexBuffer shapeIndexBuffer2 = RenderSystem.getSequentialBuffer((VertexFormat.DrawMode)VertexFormat.DrawMode.QUADS);
        Intrinsics.checkNotNullExpressionValue((Object)shapeIndexBuffer2, (String)"getSequentialBuffer(...)");
        RenderSystem.ShapeIndexBuffer indexBuf = shapeIndexBuffer2;
        GpuBuffer gpuBuffer3 = indexBuf.getIndexBuffer(6);
        Intrinsics.checkNotNullExpressionValue((Object)gpuBuffer3, (String)"getBuffer(...)");
        GpuBuffer indices = gpuBuffer3;
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer u = stack.calloc(KAWASE_UNIFORM_BYTES);
            u.putFloat(0, sourceX);
            u.putFloat(4, sourceY);
            u.putFloat(8, sourceW);
            u.putFloat(12, sourceH);
            u.putFloat(16, offsetScale / (float)Math.max(sourceWidth, 1));
            u.putFloat(20, offsetScale / (float)Math.max(sourceHeight, 1));
            u.putFloat(32, 0.0f);
            u.putFloat(36, 0.0f);
            u.putFloat(40, 0.0f);
            u.putFloat(44, 0.0f);
            u.position(0);
            encoder.writeToBuffer(kawaseUBO.slice(0L, (long)KAWASE_UNIFORM_BYTES), u);
// stack = Unit.INSTANCE;
        }
        catch (Throwable bl) {
            throwable = bl;
            throw bl;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        Supplier<String> supplier = GlowRenderer::renderKawasePass$lambda$1;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0), null, OptionalDouble.empty());
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            pass.setPipeline(pipeline);
            pass.bindTexture("Sampler0", source, sampler);
            pass.setUniform("KawaseParams", kawaseUBO);
            pass.setVertexBuffer(0, quad);
            pass.setIndexBuffer(indices, indexBuf.getIndexType());
            pass.drawIndexed(0, 0, 6, 1);
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
    private final void renderCutoutPass(SimpleFramebuffer blurred, SimpleFramebuffer sharpMask, SimpleFramebuffer target, GpuSampler sampler) {
        if (blurred == null || sharpMask == null || target == null) {
            return;
        }
        GpuBuffer quad = this.ensureFullscreenQuadBuffer();
        GpuBuffer kawaseUBO = this.ensureKawaseParamsBuffer();
        if (quad == null || kawaseUBO == null) {
            return;
        }
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        RenderSystem.ShapeIndexBuffer shapeIndexBuffer2 = RenderSystem.getSequentialBuffer((VertexFormat.DrawMode)VertexFormat.DrawMode.QUADS);
        Intrinsics.checkNotNullExpressionValue((Object)shapeIndexBuffer2, (String)"getSequentialBuffer(...)");
        RenderSystem.ShapeIndexBuffer indexBuf = shapeIndexBuffer2;
        GpuBuffer gpuBuffer = indexBuf.getIndexBuffer(6);
        Intrinsics.checkNotNullExpressionValue((Object)gpuBuffer, (String)"getBuffer(...)");
        GpuBuffer indices = gpuBuffer;
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer u = stack.calloc(KAWASE_UNIFORM_BYTES);
            u.putFloat(0, 0.0f);
            u.putFloat(4, 0.0f);
            u.putFloat(8, 1.0f);
            u.putFloat(12, 1.0f);
            u.position(0);
            encoder.writeToBuffer(kawaseUBO.slice(0L, (long)KAWASE_UNIFORM_BYTES), u);
// stack = Unit.INSTANCE;
        }
        catch (Throwable bl) {
            throwable = bl;
            throw bl;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        Supplier<String> supplier = GlowRenderer::renderCutoutPass$lambda$1;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0), null, OptionalDouble.empty());
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            pass.setPipeline(GLOW_CUTOUT_PIPELINE);
            GpuTextureView gpuTextureView2 = blurred.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView2);
            pass.bindTexture("Sampler0", gpuTextureView2, sampler);
            GpuTextureView gpuTextureView3 = sharpMask.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView3);
            pass.bindTexture("Sampler1", gpuTextureView3, sampler);
            pass.setUniform("KawaseParams", kawaseUBO);
            pass.setVertexBuffer(0, quad);
            pass.setIndexBuffer(indices, indexBuf.getIndexType());
            pass.drawIndexed(0, 0, 6, 1);
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

    private final ByteBuffer buildUniformData(MemoryStack stack) {
        int count = Math.max(1, this.preparedGlows.size());
        int bytes = count * 5 * 4 * 4;
        ByteBuffer data = stack.calloc(bytes);
        int n = ((Collection)this.preparedGlows).size();
        for (int i = 0; i < n; ++i) {
            BuiltGlow g = this.preparedGlows.get(i);
            GlowCapture cap = i < this.preparedCaptures.size() ? this.preparedCaptures.get(i) : null;
            float u0 = cap != null ? cap.regionU0 : 0.0f;
            float v0 = cap != null ? cap.regionV0 : 0.0f;
            float uw = cap != null ? cap.regionUW : 1.0f;
            float vh = cap != null ? cap.regionVH : 1.0f;
            GlowRenderer.Companion.writeGlowParams(data, i, g, u0, v0, uw, vh);
        }
        data.position(0);
        Intrinsics.checkNotNull((Object)data);
        return data;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final GpuBuffer ensureParamsBuffer() {
        if (!this.paramsDirty && this.paramsBuffer != null) {
            return this.paramsBuffer;
        }
        this.prepareBuffers();
        if (!this.paramsDirty && this.paramsBuffer != null) {
            return this.paramsBuffer;
        }
        this.closeParamsBuffer();
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            Intrinsics.checkNotNull((Object)stack);
            ByteBuffer data = this.buildUniformData(stack);
            this.paramsBuffer = RenderSystem.getDevice().createBuffer(GlowRenderer::ensureParamsBuffer$lambda$0$0, 128, data);
            this.paramsDirty = false;
            GpuBuffer gpuBuffer = this.paramsBuffer;
            return gpuBuffer;
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
    }

    private final GpuBuffer ensureWritableParamsBuffer() {
        GpuBuffer current = this.paramsBuffer;
        if (current != null && !current.isClosed() && current.size() >= (long)UNIFORM_BYTES) {
            return current;
        }
        this.closeParamsBuffer();
        try {
            GpuBuffer created = RenderSystem.getDevice().createBuffer(GlowRenderer::ensureWritableParamsBuffer$lambda$0, 136, (long)UNIFORM_BYTES);
            this.paramsBuffer = created;
            return created;
        }
        catch (RuntimeException ignored) {
            return null;
        }
    }

    private final GpuBuffer ensureKawaseParamsBuffer() {
        GpuBuffer current = this.kawaseParamsBuffer;
        if (current != null && !current.isClosed() && current.size() >= (long)KAWASE_UNIFORM_BYTES) {
            return current;
        }
        this.closeKawaseParamsBuffer();
        try {
            GpuBuffer created = RenderSystem.getDevice().createBuffer(GlowRenderer::ensureKawaseParamsBuffer$lambda$0, 136, (long)KAWASE_UNIFORM_BYTES);
            this.kawaseParamsBuffer = created;
            return created;
        }
        catch (RuntimeException ignored) {
            return null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final GpuBuffer ensureFullscreenQuadBuffer() {
        GpuBuffer current = this.fullscreenQuadBuffer;
        if (current != null) {
            return current;
        }
        AutoCloseable autoCloseable = (AutoCloseable)new BufferAllocator(4 * VertexFormats.POSITION.getVertexSize());
        Throwable throwable = null;
        try {
            BufferAllocator alloc = (BufferAllocator)autoCloseable;
            boolean bl = false;
            AutoCloseable autoCloseable2 = (AutoCloseable)this.buildFullscreenQuad(alloc);
            Throwable throwable2 = null;
            try {
                BuiltBuffer mesh = (BuiltBuffer)autoCloseable2;
                boolean bl2 = false;
                this.fullscreenQuadBuffer = RenderSystem.getDevice().createBuffer(GlowRenderer::ensureFullscreenQuadBuffer$lambda$0$0$0, 32, mesh.getBuffer());
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
        return this.fullscreenQuadBuffer;
    }

    private final BuiltBuffer buildFullscreenQuad(BufferAllocator alloc) {
        BufferBuilder bb = new BufferBuilder(alloc, VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
        bb.vertex(-1.0f, -1.0f, 0.0f);
        bb.vertex(-1.0f, 1.0f, 0.0f);
        bb.vertex(1.0f, 1.0f, 0.0f);
        bb.vertex(1.0f, -1.0f, 0.0f);
        BuiltBuffer builtBuffer2 = bb.end();
        Intrinsics.checkNotNullExpressionValue((Object)builtBuffer2, (String)"buildOrThrow(...)");
        return builtBuffer2;
    }

    private final SimpleFramebuffer ensureShapeTarget(int index, int width, int height) {
        while (this.shapeTargets.size() <= index) {
            this.shapeTargets.add(null);
        }
        SimpleFramebuffer t = this.ensureTarget(this.shapeTargets.get(index), "kimiko_glow_shape_" + index, width, height);
        this.shapeTargets.set(index, t);
        return t;
    }

    private final SimpleFramebuffer ensureBlurTarget(int index, int width, int height) {
        while (this.blurTargets.size() <= index) {
            this.blurTargets.add(null);
        }
        SimpleFramebuffer t = this.ensureTarget(this.blurTargets.get(index), "kimiko_glow_blur_" + index, width, height);
        this.blurTargets.set(index, t);
        return t;
    }

    private final SimpleFramebuffer ensureResultTarget(int index, int width, int height) {
        while (this.resultTargets.size() <= index) {
            this.resultTargets.add(null);
        }
        SimpleFramebuffer t = this.ensureTarget(this.resultTargets.get(index), "kimiko_glow_result_" + index, width, height);
        this.resultTargets.set(index, t);
        return t;
    }

    private final void ensureScratchTargets(int index, int width, int height, int iterations) {
        while (this.downPool.size() <= index) {
            this.downPool.add(new SimpleFramebuffer[4]);
            this.upPool.add(new SimpleFramebuffer[4]);
        }
        SimpleFramebuffer[] class_6367Array = this.downPool.get(index);
        Intrinsics.checkNotNullExpressionValue((Object)class_6367Array, (String)"get(...)");
        SimpleFramebuffer[] down = class_6367Array;
        SimpleFramebuffer[] class_6367Array2 = this.upPool.get(index);
        Intrinsics.checkNotNullExpressionValue((Object)class_6367Array2, (String)"get(...)");
        SimpleFramebuffer[] up = class_6367Array2;
        int tw = Math.max(width, 1);
        int th = Math.max(height, 1);
        for (int i = 0; i < iterations; ++i) {
            down[i] = this.ensureTarget(down[i], "kimiko_glow_down_" + index + "_" + i, tw, th);
            if (i < iterations - 1) {
                up[i] = this.ensureTarget(up[i], "kimiko_glow_up_" + index + "_" + i, tw, th);
            }
            tw = Math.max(tw / 2, 1);
            th = Math.max(th / 2, 1);
        }
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

    private final void submit(DrawContext graphics, BuiltGlow glow) {
        if (graphics == null || glow == null || !glow.visible()) {
            return;
        }
        try {
            waveZoom.put(glow, Float.valueOf(Render2DCoordinateSpace.uiZoom()));
            Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
            GlowCapture capture = new GlowCapture();
            if (this.pendingGlows.size() < 224) {
                capture.index = this.pendingGlows.size();
                this.pendingGlows.add(new PendingGlow(glow, capture, pose));
            }
            ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState().addSimpleElement((SimpleGuiElementRenderState)new GlowRenderState(pose, glow, ScissorUtil.current(), capture));
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    private final void closeParamsBuffer() {
        GpuBuffer gpuBuffer = this.paramsBuffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        this.paramsBuffer = null;
    }

    private final void closeKawaseParamsBuffer() {
        GpuBuffer gpuBuffer = this.kawaseParamsBuffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        this.kawaseParamsBuffer = null;
    }

    @Override
    public void close() {
        SimpleFramebuffer t;
        this.preparedGlows.clear();
        this.activeGraphics = null;
        this.atlasValid = false;
        this.lastAtlasSig = 0L;
        this.closeParamsBuffer();
        this.closeKawaseParamsBuffer();
        GpuBuffer gpuBuffer = this.fullscreenQuadBuffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        this.fullscreenQuadBuffer = null;
        Iterator<SimpleFramebuffer> iterator = this.shapeTargets.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<SimpleFramebuffer> iterator2 = iterator;
        while (iterator2.hasNext()) {
            SimpleFramebuffer simpleFramebuffer2 = t = iterator2.next();
            if (simpleFramebuffer2 == null) continue;
            simpleFramebuffer2.delete();
        }
        Iterator<SimpleFramebuffer> iterator3 = this.blurTargets.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator3, (String)"iterator(...)");
        iterator2 = iterator3;
        while (iterator2.hasNext()) {
            SimpleFramebuffer simpleFramebuffer3 = t = iterator2.next();
            if (simpleFramebuffer3 == null) continue;
            simpleFramebuffer3.delete();
        }
        Iterator<SimpleFramebuffer> iterator4 = this.resultTargets.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator4, (String)"iterator(...)");
        iterator2 = iterator4;
        while (iterator2.hasNext()) {
            SimpleFramebuffer simpleFramebuffer4 = t = iterator2.next();
            if (simpleFramebuffer4 == null) continue;
            simpleFramebuffer4.delete();
        }
        this.shapeTargets.clear();
        this.blurTargets.clear();
        this.resultTargets.clear();
        GlowRenderer.Companion.destroyPool(this.downPool);
        GlowRenderer.Companion.destroyPool(this.upPool);
    }

    private static final String clearTarget$lambda$0() {
        return "kimiko_glow_clear";
    }

    private static final String renderSourceShape$lambda$0$0$0(int $index) {
        return "kimiko_glow_src_quad_" + $index;
    }

    private static final String renderSourceShape$lambda$1$0(int $index) {
        return "kimiko_glow_src_params_" + $index;
    }

    private static final String renderSourceShape$lambda$3(int $index) {
        return "kimiko_glow_source_" + $index;
    }

    private static final String renderShape$lambda$0$0(int $index) {
        return "kimiko_glow_shape_params_" + $index;
    }

    private static final String renderShape$lambda$1(int $index) {
        return "kimiko_glow_shape_" + $index;
    }

    private static final String renderKawasePass$lambda$1() {
        return "kimiko_glow_kawase";
    }

    private static final String renderCutoutPass$lambda$1() {
        return "kimiko_glow_cutout";
    }

    private static final String ensureParamsBuffer$lambda$0$0() {
        return "kimiko_glow_params";
    }

    private static final String ensureWritableParamsBuffer$lambda$0() {
        return "kimiko_glow_params";
    }

    private static final String ensureKawaseParamsBuffer$lambda$0() {
        return "kimiko_glow_kawase_params";
    }

    private static final String ensureFullscreenQuadBuffer$lambda$0$0$0() {
        return "kimiko_glow_fullscreen_quad";
    }

    @JvmStatic
    @NotNull
    public static final GlowRenderer getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final void closeInstance() {
        Companion.closeInstance();
    }

    public /* synthetic */ GlowRenderer(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    static {
        waveZoom = new IdentityHashMap(64);
        RenderPipeline renderPipeline = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(GlowRenderer.Companion.id("pipeline/glow_composite")).withVertexShader(GlowRenderer.Companion.id("ui/glow/glow_composite")).withFragmentShader(GlowRenderer.Companion.id("ui/glow/glow_composite")).withVertexFormat(VertexFormats.POSITION_COLOR_LINE_WIDTH, VertexFormat.DrawMode.QUADS).withBlend(new BlendFunction(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ZERO, DestFactor.ONE)).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withSampler("Sampler0").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withUniform("GlowParamsArray", UniformType.UNIFORM_BUFFER).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"build(...)");
        GLOW_COMPOSITE_PIPELINE = renderPipeline;
        RenderPipeline renderPipeline2 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(GlowRenderer.Companion.id("pipeline/glow_shape")).withVertexShader(GlowRenderer.Companion.id("ui/glow/glow_shape")).withFragmentShader(GlowRenderer.Companion.id("ui/glow/glow_shape")).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.QUADS).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withUniform("GlowParamsArray", UniformType.UNIFORM_BUFFER).withUniform("SplitParams", UniformType.UNIFORM_BUFFER).withUniform("ThemeWaveParams", UniformType.UNIFORM_BUFFER).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline2, (String)"build(...)");
        GLOW_SHAPE_PIPELINE = renderPipeline2;
        RenderPipeline renderPipeline3 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(GlowRenderer.Companion.id("pipeline/glow_source")).withVertexShader(GlowRenderer.Companion.id("ui/glow/glow_source")).withFragmentShader(GlowRenderer.Companion.id("ui/glow/glow_shape")).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.QUADS).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withUniform("GlowParamsArray", UniformType.UNIFORM_BUFFER).withUniform("SplitParams", UniformType.UNIFORM_BUFFER).withUniform("ThemeWaveParams", UniformType.UNIFORM_BUFFER).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline3, (String)"build(...)");
        GLOW_SOURCE_PIPELINE = renderPipeline3;
        RenderPipeline renderPipeline4 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(GlowRenderer.Companion.id("pipeline/glow_blur_down")).withVertexShader(GlowRenderer.Companion.id("ui/glow/glow_blur")).withFragmentShader(GlowRenderer.Companion.id("ui/glow/glow_blur_down")).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.QUADS).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withSampler("Sampler0").withUniform("KawaseParams", UniformType.UNIFORM_BUFFER).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline4, (String)"build(...)");
        GLOW_BLUR_DOWN_PIPELINE = renderPipeline4;
        RenderPipeline renderPipeline5 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(GlowRenderer.Companion.id("pipeline/glow_blur_up")).withVertexShader(GlowRenderer.Companion.id("ui/glow/glow_blur")).withFragmentShader(GlowRenderer.Companion.id("ui/glow/glow_blur_up")).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.QUADS).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withSampler("Sampler0").withUniform("KawaseParams", UniformType.UNIFORM_BUFFER).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline5, (String)"build(...)");
        GLOW_BLUR_UP_PIPELINE = renderPipeline5;
        RenderPipeline renderPipeline6 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(GlowRenderer.Companion.id("pipeline/glow_cutout")).withVertexShader(GlowRenderer.Companion.id("ui/glow/glow_blur")).withFragmentShader(GlowRenderer.Companion.id("ui/glow/glow_cutout")).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.QUADS).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withSampler("Sampler0").withSampler("Sampler1").withUniform("KawaseParams", UniformType.UNIFORM_BUFFER).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline6, (String)"build(...)");
        GLOW_CUTOUT_PIPELINE = renderPipeline6;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003JG\u0010\u0015\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J!\u0010\u0018\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\u000eH\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J!\u0010\u001a\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\u000eH\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0019J\u001f\u0010\u001b\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u0019J%\u0010 \u001a\u00020\b2\u0014\u0010\u001f\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001d0\u001cH\u0002\u00a2\u0006\u0004\b \u0010!J'\u0010%\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b%\u0010&J\u0017\u0010*\u001a\u00020)2\u0006\u0010(\u001a\u00020'H\u0002\u00a2\u0006\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010/R\u0014\u00100\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u0010/R\u0014\u00101\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u0010/R\u0014\u00102\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u0010/R\u0014\u00103\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b3\u0010/R\u0014\u00104\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u0010/R\u0014\u00105\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u0010/R\u0014\u00106\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b6\u0010/R\u0014\u00107\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u0010/R\u0014\u00108\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u0010/R\u0014\u00109\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b9\u0010/R\u0014\u0010:\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b:\u0010/R\u0014\u0010;\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b;\u0010/R\u0014\u0010<\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b<\u0010/R\u0014\u0010=\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b=\u0010/R\u0014\u0010>\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010/R\u0018\u0010?\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b?\u0010@R \u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00100A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0019\u0010F\u001a\u00020D8\u0006X\u0087\u0004\u0092\u0002\u0002\bE\u00a2\u0006\u0006\n\u0004\bF\u0010GR\u0014\u0010H\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010GR\u0014\u0010I\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010GR\u0014\u0010J\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010GR\u0014\u0010K\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bK\u0010GR\u0014\u0010L\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010G\u00a8\u0006M"}, d2={"Lrtx/kimiko/utils/render/render2d/glow/GlowRenderer.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/render2d/glow/GlowRenderer;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/utils/render/render2d/glow/GlowRenderer;", "", "closeInstance", "Ljava/nio/ByteBuffer;", "data", "", "index", "Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;", "g", "", "u0", "v0", "uw", "vh", "writeGlowParams", "(Ljava/nio/ByteBuffer;ILrtx/kimiko/utils/render/render2d/glow/BuiltGlow;FFFF)V", "glow", "writePalette", "(Ljava/nio/ByteBuffer;Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;)V", "writeWaveMap", "writeShapeSpans", "", "", "Lnet/minecraft/SimpleFramebuffer;", "pool", "destroyPool", "(Ljava/util/List;)V", "v", "min", "max", "clamp", "(FFF)F", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "TILE_SCALE", "F", "ATLAS_WIDTH", "I", "ATLAS_MAX_HEIGHT", "ATLAS_HEIGHT_BUCKET", "TILE_GAP", "MAX_GLOWS", "PARAMS_PER_GLOW", "FLOATS_PER_PARAM", "UNIFORM_BYTES", "KAWASE_UNIFORM_BYTES", "MAX_BLUR_ITERATIONS", "MAX_SPANS", "PALETTE_BASE_VEC4", "PALETTE_VEC4", "WAVE_MAP_VEC4", "SHAPE_PARAMS_VEC4", "SHAPE_BYTES", "instance", "Lrtx/kimiko/utils/render/render2d/glow/GlowRenderer;", "", "waveZoom", "Ljava/util/Map;", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "GLOW_COMPOSITE_PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "GLOW_SHAPE_PIPELINE", "GLOW_SOURCE_PIPELINE", "GLOW_BLUR_DOWN_PIPELINE", "GLOW_BLUR_UP_PIPELINE", "GLOW_CUTOUT_PIPELINE", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        @NotNull
        public final GlowRenderer getInstance() {
            GlowRenderer local = null;
            local = instance;
            if (local == null) {
                Class<GlowRenderer> clazz = GlowRenderer.class;
                synchronized (clazz) {
                    boolean bl = false;
                    local = instance;
                    if (local == null) {
                        local = new GlowRenderer(null);
                        instance = local;
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return local;
        }

        @JvmStatic
        public final void closeInstance() {
            GlowRenderer local = instance;
            if (local != null) {
                local.close();
                instance = null;
            }
        }

        private final void writeGlowParams(ByteBuffer data, int index, BuiltGlow g, float u0, float v0, float uw, float vh) {
            int off = index * 5 * 4 * 4;
            data.putFloat(off, g.radiusTopLeft());
            data.putFloat(off + 4, g.radiusTopRight());
            data.putFloat(off + 8, g.radiusBottomRight());
            data.putFloat(off + 12, g.radiusBottomLeft());
            data.putFloat(off + 16, g.width());
            data.putFloat(off + 20, g.height());
            data.putFloat(off + 24, g.effectivePad());
            data.putFloat(off + 28, 2.0f);
            int color = g.color();
            data.putFloat(off + 32, (float)(color >>> 16 & 0xFF) / 255.0f);
            data.putFloat(off + 36, (float)(color >>> 8 & 0xFF) / 255.0f);
            data.putFloat(off + 40, (float)(color & 0xFF) / 255.0f);
            data.putFloat(off + 44, g.intensity() * g.alpha());
            data.putFloat(off + 48, u0);
            data.putFloat(off + 52, v0);
            data.putFloat(off + 56, uw);
            data.putFloat(off + 60, vh);
            int sc = g.secondColor();
            data.putFloat(off + 64, (float)(sc >>> 16 & 0xFF) / 255.0f);
            data.putFloat(off + 68, (float)(sc >>> 8 & 0xFF) / 255.0f);
            data.putFloat(off + 72, (float)(sc & 0xFF) / 255.0f);
            data.putFloat(off + 76, g.colorOffset());
        }

        private final void writePalette(ByteBuffer data, BuiltGlow glow) {
            BuiltGlow.GlowPalette override;
            int base = 1120;
            BuiltGlow builtGlow = glow;
            BuiltGlow.GlowPalette glowPalette = override = builtGlow != null ? builtGlow.palette() : null;
            if (override != null && !(override.colors().length == 0)) {
                int[] colors = override.colors();
                int count = Math.max(1, Math.min(colors.length, 6));
                data.putFloat(base, count);
                data.putFloat(base + 4, override.phase());
                data.putFloat(base + 8, override.styleId());
                data.putFloat(base + 12, -1.0f);
                int meta2 = base + 112;
                data.putFloat(meta2, override.styleId());
                data.putFloat(meta2 + 4, override.closed());
                for (int i = 0; i < count; ++i) {
                    int c = colors[i] & 0xFFFFFF;
                    int off = base + (1 + i) * 4 * 4;
                    data.putFloat(off, (float)(c >>> 16 & 0xFF) / 255.0f);
                    data.putFloat(off + 4, (float)(c >>> 8 & 0xFF) / 255.0f);
                    data.putFloat(off + 8, (float)(c & 0xFF) / 255.0f);
                    data.putFloat(off + 12, 1.0f);
                }
                return;
            }
            int count = Math.max(1, Math.min(ClientPalette.count(), 6));
            int[] colors = ClientPalette.colors();
            data.putFloat(base, count);
            data.putFloat(base + 4, ClientPalette.phase());
            data.putFloat(base + 8, ClientPalette.styleId());
            data.putFloat(base + 12, GradientSweep.progress());
            int meta2 = base + 112;
            data.putFloat(meta2, ClientPalette.prevStyle());
            data.putFloat(meta2 + 4, ClientPalette.closed());
            for (int i = 0; i < count && i < colors.length; ++i) {
                int c = colors[i] & 0xFFFFFF;
                int off = base + (1 + i) * 4 * 4;
                data.putFloat(off, (float)(c >>> 16 & 0xFF) / 255.0f);
                data.putFloat(off + 4, (float)(c >>> 8 & 0xFF) / 255.0f);
                data.putFloat(off + 8, (float)(c & 0xFF) / 255.0f);
                data.putFloat(off + 12, 1.0f);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private final void writeWaveMap(ByteBuffer data, BuiltGlow glow) {
            int off = 1248;
            if (glow == null) {
                data.putFloat(off + 12, 0.0f);
                return;
            }
            BuiltGlow.GlowPalette override = glow.palette();
            boolean useShared = override == null || override.colors().length == 0;
            float pad = glow.effectivePad();
            float zoom = ((Number)waveZoom.getOrDefault(glow, Float.valueOf(1.0f))).floatValue();
            float previousZoom = Render2DCoordinateSpace.pushUiZoom(zoom);
            try {
                data.putFloat(off, Render2DCoordinateSpace.pixelX(glow.x() - pad));
                data.putFloat(off + 4, ThemeWave.screenPixelHeight() - Render2DCoordinateSpace.pixelY(glow.y() - pad));
                data.putFloat(off + 8, Render2DCoordinateSpace.pixelScale());
            }
            finally {
                Render2DCoordinateSpace.popUiZoom(previousZoom);
            }
            data.putFloat(off + 12, useShared ? 1.0f : 0.0f);
        }

        private final void writeShapeSpans(ByteBuffer data, BuiltGlow g) {
            float[] spans = g.spans();
            int count = g.spanCount();
            if (spans == null || count <= 0) {
                return;
            }
            float pad = g.effectivePad();
            float innerRadius = Math.min(Math.min(g.radiusTopLeft(), g.radiusTopRight()), Math.min(g.radiusBottomRight(), g.radiusBottomLeft()));
            int base = 80;
            data.putFloat(base, Math.min(count, 64));
            data.putFloat(base + 4, innerRadius);
            data.putFloat(base + 8, g.leftAligned());
            data.putFloat(base + 12, g.bottomAnchored() > 0.5f ? 0.0f : 1.0f);
            int spanBase = base + 16;
            int actualCount = Math.min(count, 64);
            float targetHeight = g.height() + pad * 2.0f;
            for (int i = 0; i < actualCount; ++i) {
                int src = i * 4;
                float left = spans[src] + pad;
                float right = spans[src + 1] + pad;
                float top = spans[src + 2] + pad;
                float bottom = spans[src + 3] + pad;
                float flippedTop = targetHeight - bottom;
                float flippedBottom = targetHeight - top;
                int off = spanBase + i * 4 * 4;
                data.putFloat(off, left);
                data.putFloat(off + 4, right);
                data.putFloat(off + 8, flippedTop);
                data.putFloat(off + 12, flippedBottom);
            }
        }

        private final void destroyPool(List<SimpleFramebuffer[]> pool) {
            for (SimpleFramebuffer[] arr : pool) {
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] != null) {
                        arr[i].delete();
                        arr[i] = null;
                    }
                }
            }
            pool.clear();
        }

        private final float clamp(float v, float min, float max) {
            return Math.max(min, Math.min(max, v));
        }

        private final Identifier id(String path) {
            Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
            Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
            return identifier2;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ.\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0017\u001a\u00020\u0016H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u001a\u001a\u00020\u0019H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010\rR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010 \u001a\u0004\b!\u0010\u000f\u00a8\u0006\""}, d2={"Lrtx/kimiko/utils/render/render2d/glow/GlowRenderer$PendingGlow;", "", "Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;", "glow", "Lrtx/kimiko/utils/render/render2d/glow/GlowCapture;", "capture", "Lorg/joml/Matrix3x2f;", "pose", "<init>", "(Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;Lrtx/kimiko/utils/render/render2d/glow/GlowCapture;Lorg/joml/Matrix3x2f;)V", "component1", "()Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;", "component2", "()Lrtx/kimiko/utils/render/render2d/glow/GlowCapture;", "component3", "()Lorg/joml/Matrix3x2f;", "copy", "(Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;Lrtx/kimiko/utils/render/render2d/glow/GlowCapture;Lorg/joml/Matrix3x2f;)Lrtx/kimiko/utils/render/render2d/glow/GlowRenderer$PendingGlow;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;", "getGlow", "Lrtx/kimiko/utils/render/render2d/glow/GlowCapture;", "getCapture", "Lorg/joml/Matrix3x2f;", "getPose", "rtx.kimiko:kimiko"})
    private static final class PendingGlow {
        @NotNull
        private final BuiltGlow glow;
        @NotNull
        private final GlowCapture capture;
        @NotNull
        private final Matrix3x2f pose;

        public PendingGlow(@NotNull BuiltGlow glow, @NotNull GlowCapture capture, @NotNull Matrix3x2f pose) {
            Intrinsics.checkNotNullParameter((Object)glow, (String)"glow");
            Intrinsics.checkNotNullParameter((Object)capture, (String)"capture");
            Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
            this.glow = glow;
            this.capture = capture;
            this.pose = pose;
        }

        @NotNull
        public final BuiltGlow getGlow() {
            return this.glow;
        }

        @NotNull
        public final GlowCapture getCapture() {
            return this.capture;
        }

        @NotNull
        public final Matrix3x2f getPose() {
            return this.pose;
        }

        @NotNull
        public final BuiltGlow component1() {
            return this.glow;
        }

        @NotNull
        public final GlowCapture component2() {
            return this.capture;
        }

        @NotNull
        public final Matrix3x2f component3() {
            return this.pose;
        }

        @NotNull
        public final PendingGlow copy(@NotNull BuiltGlow glow, @NotNull GlowCapture capture, @NotNull Matrix3x2f pose) {
            Intrinsics.checkNotNullParameter((Object)glow, (String)"glow");
            Intrinsics.checkNotNullParameter((Object)capture, (String)"capture");
            Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
            return new PendingGlow(glow, capture, pose);
        }

        public static /* synthetic */ PendingGlow copy$default(PendingGlow pendingGlow, BuiltGlow builtGlow, GlowCapture glowCapture, Matrix3x2f matrix3x2f, int n, Object object) {
            if ((n & 1) != 0) {
                builtGlow = pendingGlow.glow;
            }
            if ((n & 2) != 0) {
                glowCapture = pendingGlow.capture;
            }
            if ((n & 4) != 0) {
                matrix3x2f = pendingGlow.pose;
            }
            return pendingGlow.copy(builtGlow, glowCapture, matrix3x2f);
        }

        @NotNull
        public String toString() {
            return "PendingGlow(glow=" + this.glow + ", capture=" + this.capture + ", pose=" + this.pose + ")";
        }

        public int hashCode() {
            int result = this.glow.hashCode();
            result = result * 31 + this.capture.hashCode();
            result = result * 31 + this.pose.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PendingGlow)) {
                return false;
            }
            PendingGlow pendingGlow = (PendingGlow)other;
            if (!Intrinsics.areEqual((Object)this.glow, (Object)pendingGlow.glow)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.capture, (Object)pendingGlow.capture)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.pose, (Object)pendingGlow.pose);
        }
    }
}

