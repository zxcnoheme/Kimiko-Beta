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
 *  com.mojang.blaze3d.systems.RenderSystem$ShapeIndexBuffer
 *  com.mojang.blaze3d.textures.FilterMode
 *  com.mojang.blaze3d.textures.GpuTexture
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.textures.TextureFormat
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.util.Window
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gl.GpuSampler
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.BufferBuilder
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gl.SimpleFramebuffer
 *  net.minecraft.client.gui.ScreenRect
 *  net.minecraft.client.util.BufferAllocator
 *  net.minecraft.client.render.BuiltBuffer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fc
 *  org.lwjgl.system.MemoryStack
 */
package rtx.kimiko.utils.render.render2d.blur;

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
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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
import net.minecraft.client.util.Window;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gl.SimpleFramebuffer;
import net.minecraft.client.gui.ScreenRect;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.render.BuiltBuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;
import org.lwjgl.system.MemoryStack;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.modules.post.guilayerblur.GuiLayerBlurRenderer;
import rtx.kimiko.utils.render.others.profiler.RenderProfiler;
import rtx.kimiko.utils.render.render2d.RefreshRateThrottle;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.render2d.blur.BlurCapture;
import rtx.kimiko.utils.render.render2d.blur.BlurRenderState;
import rtx.kimiko.utils.render.render2d.blur.BuiltBlur;
import rtx.kimiko.utils.render.util.scissor.ScissorUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00e0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u000b\u0018\u0000 \u00a4\u00012\u00060\u0001j\u0002`\u0002:\b\u00a5\u0001\u00a6\u0001\u00a7\u0001\u00a4\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\tJ!\u0010\f\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0004J\r\u0010\u0011\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0004J\r\u0010\u0012\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0012\u0010\u0004J+\u0010\u0015\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016J+\u0010\u0017\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\u0004\b\u0017\u0010\u0016J5\u0010\u001a\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0019\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\r\u0010\u001c\u001a\u00020\u0007\u00a2\u0006\u0004\b\u001c\u0010\u0004J/\u0010$\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b$\u0010%J!\u0010*\u001a\u0004\u0018\u00010)2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020&H\u0002\u00a2\u0006\u0004\b*\u0010+J\u000f\u0010,\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b,\u0010\u0004J\r\u0010-\u001a\u00020\u0007\u00a2\u0006\u0004\b-\u0010\u0004J\r\u0010.\u001a\u00020\u0007\u00a2\u0006\u0004\b.\u0010\u0004J\u0017\u00101\u001a\u00020\u00182\b\u00100\u001a\u0004\u0018\u00010/\u00a2\u0006\u0004\b1\u00102J\u0017\u00105\u001a\u00020\u00072\b\u00104\u001a\u0004\u0018\u000103\u00a2\u0006\u0004\b5\u00106J\u001d\u00107\u001a\u00020&2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b7\u00108J#\u00109\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b9\u0010\rJ\u0011\u0010;\u001a\u0004\u0018\u00010:H\u0002\u00a2\u0006\u0004\b;\u0010<J\r\u0010=\u001a\u00020\u0007\u00a2\u0006\u0004\b=\u0010\u0004J\u0011\u0010>\u001a\u0004\u0018\u00010:H\u0002\u00a2\u0006\u0004\b>\u0010<J%\u0010E\u001a\u00020D2\u0006\u0010@\u001a\u00020?2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020B0AH\u0002\u00a2\u0006\u0004\bE\u0010FJ)\u0010H\u001a\u0004\u0018\u00010\u001f2\u0006\u0010G\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020&H\u0002\u00a2\u0006\u0004\bH\u0010IJ/\u0010K\u001a\u00020\u00072\u0006\u0010G\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020&2\u0006\u0010J\u001a\u00020&H\u0002\u00a2\u0006\u0004\bK\u0010LJ1\u0010P\u001a\u00020\u001f2\b\u0010M\u001a\u0004\u0018\u00010\u001f2\u0006\u0010O\u001a\u00020N2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020&H\u0002\u00a2\u0006\u0004\bP\u0010QJ)\u0010W\u001a\u0004\u0018\u00010V2\u0006\u0010S\u001a\u00020R2\u0006\u0010U\u001a\u00020T2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\bW\u0010XJ=\u0010]\u001a\u00020\u00072\u0006\u0010G\u001a\u00020&2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010Y\u001a\u0004\u0018\u00010\u001f2\b\u0010Z\u001a\u0004\u0018\u00010V2\u0006\u0010\\\u001a\u00020[H\u0002\u00a2\u0006\u0004\b]\u0010^J\u0017\u0010_\u001a\u00020&2\u0006\u0010\\\u001a\u00020[H\u0002\u00a2\u0006\u0004\b_\u0010`Jk\u0010i\u001a\u00020\u00072\u0006\u00100\u001a\u00020/2\b\u0010a\u001a\u0004\u0018\u00010)2\u0006\u0010b\u001a\u00020&2\u0006\u0010c\u001a\u00020&2\b\u0010M\u001a\u0004\u0018\u00010\u001f2\u0006\u0010d\u001a\u00020[2\u0006\u0010e\u001a\u00020[2\u0006\u0010f\u001a\u00020[2\u0006\u0010g\u001a\u00020[2\u0006\u0010h\u001a\u00020[2\u0006\u0010\"\u001a\u00020!H\u0002\u00a2\u0006\u0004\bi\u0010jJ\u0011\u0010k\u001a\u0004\u0018\u00010:H\u0002\u00a2\u0006\u0004\bk\u0010<J\u0011\u0010l\u001a\u0004\u0018\u00010:H\u0002\u00a2\u0006\u0004\bl\u0010<J\u0017\u0010p\u001a\u00020o2\u0006\u0010n\u001a\u00020mH\u0002\u00a2\u0006\u0004\bp\u0010qJ\u0017\u0010r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\br\u0010sJ\u000f\u0010t\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\bt\u0010\u0004J\u000f\u0010u\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\bu\u0010\u0004J\u000f\u0010v\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\bv\u0010\u0004R$\u0010y\u001a\u0012\u0012\u0004\u0012\u00020R0wj\b\u0012\u0004\u0012\u00020R`x8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\by\u0010zR$\u0010{\u001a\u0012\u0012\u0004\u0012\u00020B0wj\b\u0012\u0004\u0012\u00020B`x8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b{\u0010zR\u0018\u0010|\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b|\u0010}R(\u0010~\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u001f0wj\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f`x8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b~\u0010zR\u0019\u0010\u007f\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u007f\u0010\u0080\u0001R\u0019\u0010\u0081\u0001\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0081\u0001\u0010\u0082\u0001R\u0019\u0010\u0083\u0001\u001a\u00020[8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0083\u0001\u0010\u0084\u0001R\u001c\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0085\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0086\u0001\u0010\u0087\u0001R\u001b\u0010\u0088\u0001\u001a\u0004\u0018\u00010)8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0088\u0001\u0010\u0089\u0001R\u0019\u0010\u008a\u0001\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0019\u0010\u008c\u0001\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008c\u0001\u0010\u008b\u0001R\u0019\u0010\u008d\u0001\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008d\u0001\u0010\u0082\u0001R\u001b\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008e\u0001\u0010\u0080\u0001R\u0019\u0010\u008f\u0001\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008f\u0001\u0010\u0082\u0001R\u0019\u0010\u0090\u0001\u001a\u00020[8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0090\u0001\u0010\u0084\u0001R\u001b\u0010\u0091\u0001\u001a\u0004\u0018\u00010V8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0091\u0001\u0010\u0092\u0001R8\u0010\u0094\u0001\u001a$\u0012\r\u0012\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u0093\u00010wj\u0011\u0012\r\u0012\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u0093\u0001`x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0094\u0001\u0010zR8\u0010\u0095\u0001\u001a$\u0012\r\u0012\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u0093\u00010wj\u0011\u0012\r\u0012\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u0093\u0001`x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0095\u0001\u0010zR\u001b\u0010\u0096\u0001\u001a\u0004\u0018\u00010:8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0096\u0001\u0010\u0097\u0001R\u001b\u0010\u0098\u0001\u001a\u0004\u0018\u00010:8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0098\u0001\u0010\u0097\u0001R\u001b\u0010\u0099\u0001\u001a\u0004\u0018\u00010:8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0099\u0001\u0010\u0097\u0001R\u0019\u0010\u009a\u0001\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u0082\u0001R\u0019\u0010\u009b\u0001\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009b\u0001\u0010\u0082\u0001R\u0019\u0010\u009c\u0001\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u008b\u0001R\u001a\u0010\u009e\u0001\u001a\u00030\u009d\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009e\u0001\u0010\u009f\u0001R\u0019\u0010\u00a0\u0001\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a0\u0001\u0010\u0082\u0001R\u001a\u0010\u00a1\u0001\u001a\u00030\u009d\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a1\u0001\u0010\u009f\u0001R\u0019\u0010\u00a2\u0001\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a2\u0001\u0010\u0082\u0001R(\u0010\u00a3\u0001\u001a\u0014\u0012\u0005\u0012\u00030\u009d\u00010wj\t\u0012\u0005\u0012\u00030\u009d\u0001`x8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u00a3\u0001\u0010z\u00a8\u0006\u00a8\u0001"}, d2={"Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lnet/minecraft/DrawContext;", "graphics", "", "beginFrame", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;", "blur", "draw", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;)V", "enqueue", "(Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;)V", "flush", "beginGuiFrame", "preparePending", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "capture", "requestCapture", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;)V", "requestBackdropSample", "", "affectsGlobalBlur", "requestBackdropCapture", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;Z)V", "prepareGuiDraw", "Lnet/minecraft/Framebuffer;", "mainTarget", "Lnet/minecraft/SimpleFramebuffer;", "global", "Lnet/minecraft/GpuSampler;", "sampler", "refreshCopy", "freezeStableBackdrop", "(Lnet/minecraft/Framebuffer;Lnet/minecraft/SimpleFramebuffer;Lnet/minecraft/GpuSampler;Z)V", "", "width", "height", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "ensureStableBackdrop", "(II)Lcom/mojang/blaze3d/textures/GpuTextureView;", "closeStableBackdrop", "recaptureBackdrop", "recaptureWorldBackdrop", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "isBlurPipeline", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Z", "Lcom/mojang/blaze3d/systems/RenderPass;", "renderPass", "bindBlurParams", "(Lcom/mojang/blaze3d/systems/RenderPass;)V", "reserve", "(Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;)I", "submit", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "ensureParamsBuffer", "()Lcom/mojang/blaze3d/buffers/GpuBuffer;", "prepareBuffers", "ensureWritableParamsBuffer", "Lorg/lwjgl/system/MemoryStack;", "stack", "", "Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$PreparedBlur;", "batch", "Ljava/nio/ByteBuffer;", "buildUniformData", "(Lorg/lwjgl/system/MemoryStack;Ljava/util/List;)Ljava/nio/ByteBuffer;", "index", "ensureResultTarget", "(III)Lnet/minecraft/SimpleFramebuffer;", "iterations", "ensureScratchTargets", "(IIII)V", "target", "", "name", "ensureTarget", "(Lnet/minecraft/SimpleFramebuffer;Ljava/lang/String;II)Lnet/minecraft/SimpleFramebuffer;", "Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$PendingBlur;", "pendingBlur", "Lnet/minecraft/MinecraftClient;", "minecraft", "Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$Region;", "computeRegionFor", "(Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$PendingBlur;Lnet/minecraft/MinecraftClient;Lnet/minecraft/Framebuffer;)Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$Region;", "resultTarget", "region", "", "blurRadius", "renderBlurChain", "(ILnet/minecraft/Framebuffer;Lnet/minecraft/SimpleFramebuffer;Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$Region;F)V", "computeIterations", "(F)I", "source", "sourceWidth", "sourceHeight", "offsetScale", "sourceX", "sourceY", "sourceW", "sourceH", "renderKawasePass", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lcom/mojang/blaze3d/textures/GpuTextureView;IILnet/minecraft/SimpleFramebuffer;FFFFFLnet/minecraft/GpuSampler;)V", "ensureKawaseParamsBuffer", "ensureFullscreenQuadBuffer", "Lnet/minecraft/BufferAllocator;", "allocator", "Lnet/minecraft/BuiltBuffer;", "buildFullscreenQuad", "(Lnet/minecraft/BufferAllocator;)Lnet/minecraft/BuiltBuffer;", "normalize", "(Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;)Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;", "closeParamsBuffer", "closeKawaseParamsBuffer", "close", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "pendingBlurs", "Ljava/util/ArrayList;", "preparedBlurs", "activeGraphics", "Lnet/minecraft/DrawContext;", "resultTargets", "globalResult", "Lnet/minecraft/SimpleFramebuffer;", "globalBlurReady", "Z", "globalBlurRadius", "F", "Lcom/mojang/blaze3d/textures/GpuTexture;", "stableBackdropTexture", "Lcom/mojang/blaze3d/textures/GpuTexture;", "stableBackdropView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "stableBackdropWidth", "I", "stableBackdropHeight", "stableBackdropReady", "worldGlobalResult", "worldGlobalBlurReady", "worldGlobalBlurRadius", "worldGlobalRegion", "Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$Region;", "", "downPool", "upPool", "fullscreenQuadBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "paramsBuffer", "kawaseParamsBuffer", "captureRequested", "paramsDirty", "preparedIterations", "", "globalChainSig", "J", "globalRecaptured", "worldChainSig", "worldRecaptured", "resultChainSigs", "Companion", "Region", "PreparedBlur", "PendingBlur", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nBlurFramebuffer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BlurFramebuffer.kt\nrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1188:1\n1#2:1189\n*E\n"})
public final class BlurFramebuffer
implements AutoCloseable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ArrayList<PendingBlur> pendingBlurs = new ArrayList(32);
    @NotNull
    private final ArrayList<PreparedBlur> preparedBlurs = new ArrayList(32);
    @Nullable
    private DrawContext activeGraphics;
    @NotNull
    private final ArrayList<SimpleFramebuffer> resultTargets = new ArrayList(64);
    @Nullable
    private SimpleFramebuffer globalResult;
    private boolean globalBlurReady;
    private float globalBlurRadius;
    @Nullable
    private GpuTexture stableBackdropTexture;
    @Nullable
    private GpuTextureView stableBackdropView;
    private int stableBackdropWidth;
    private int stableBackdropHeight;
    private boolean stableBackdropReady;
    @Nullable
    private SimpleFramebuffer worldGlobalResult;
    private boolean worldGlobalBlurReady;
    private float worldGlobalBlurRadius;
    @Nullable
    private Region worldGlobalRegion;
    @NotNull
    private final ArrayList<SimpleFramebuffer[]> downPool = new ArrayList(64);
    @NotNull
    private final ArrayList<SimpleFramebuffer[]> upPool = new ArrayList(64);
    @Nullable
    private GpuBuffer fullscreenQuadBuffer;
    @Nullable
    private GpuBuffer paramsBuffer;
    @Nullable
    private GpuBuffer kawaseParamsBuffer;
    private boolean captureRequested;
    private boolean paramsDirty = true;
    private int preparedIterations;
    private long globalChainSig;
    private boolean globalRecaptured;
    private long worldChainSig;
    private boolean worldRecaptured;
    @NotNull
    private final ArrayList<Long> resultChainSigs = new ArrayList(64);
    private static final int MAX_BLUR_RECTS = 512;
    private static final int MAX_BLUR_ITERATIONS = 4;
    private static final int PARAMS_PER_RECT = 3;
    private static final int FLOATS_PER_PARAM = 4;
    private static final int UNIFORM_BYTES = 24576;
    private static final int KAWASE_UNIFORM_BYTES = 48;
    private static final float MAX_BLUR_RENDER_SCALE = 0.5f;
    private static final int MAX_CAPTURES = 64;
    private static final int GLOBAL_SCRATCH_INDEX = 64;
    private static final int WORLD_GLOBAL_SCRATCH_INDEX = 65;
    @Nullable
    private static volatile BlurFramebuffer instance;
    private static float skyFallbackRed;
    private static float skyFallbackGreen;
    private static float skyFallbackBlue;
    private static boolean worldScopeActive;
    @JvmField
    @NotNull
    public static final RenderPipeline BATCHED_BLUR_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderPipeline KAWASE_DOWN_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderPipeline KAWASE_UP_PIPELINE;

    private BlurFramebuffer() {
    }

    public final void beginFrame(@Nullable DrawContext graphics) {
        this.activeGraphics = graphics;
    }

    public final void draw(@Nullable DrawContext graphics, @Nullable BuiltBlur blur) {
        this.beginFrame(graphics);
        this.enqueue(blur);
        this.flush();
    }

    public final void enqueue(@Nullable BuiltBlur blur) {
        this.submit(this.activeGraphics, blur);
    }

    public final void flush() {
        this.activeGraphics = null;
    }

    public final void beginGuiFrame() {
        RefreshRateThrottle.beginFrame();
        this.preparedBlurs.clear();
        this.captureRequested = false;
        this.paramsDirty = false;
        this.preparedIterations = 0;
    }

    public final void preparePending() {
        GpuTextureView stableView;
        SimpleFramebuffer target;
        int gh;
        if (this.pendingBlurs.isEmpty()) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        Framebuffer mainTarget = minecraft.getFramebuffer();
        if (mainTarget == null || mainTarget.getColorAttachment() == null) {
            this.pendingBlurs.clear();
            return;
        }
        this.preparedIterations = this.computeIterations(0.0f);
        GpuSampler gpuSampler2 = RenderSystem.getSamplerCache().get(FilterMode.LINEAR);
        Intrinsics.checkNotNullExpressionValue((Object)gpuSampler2, (String)"getClampToEdge(...)");
        GpuSampler sampler = gpuSampler2;
        this.globalBlurReady = false;
        this.worldGlobalBlurReady = false;
        this.worldGlobalRegion = null;
        float backdropRadius = -1.0f;
        float worldBackdropRadius = -1.0f;
        Region worldBackdropRegion = null;
        float sampleOnlyGlobalRadius = -1.0f;
        BuiltBlur stableGlobalBounds = null;
        for (PendingBlur pending : this.pendingBlurs) {
            if (!pending.getBackdrop()) continue;
            if (!pending.getAffectsGlobalBlur()) {
                if (sampleOnlyGlobalRadius < 0.0f) {
                    sampleOnlyGlobalRadius = pending.getBlur().blurRadius();
                }
                if (pending.getWorldScope() || stableGlobalBounds != null) continue;
                stableGlobalBounds = pending.getBlur();
                pending.setStableBackdrop(true);
                continue;
            }
            if (pending.getWorldScope()) {
                if (worldBackdropRadius < 0.0f) {
                    worldBackdropRadius = pending.getBlur().blurRadius();
                }
                worldBackdropRegion = BlurFramebuffer.Companion.union(worldBackdropRegion, this.computeRegionFor(pending, minecraft, mainTarget));
                continue;
            }
            if (!(backdropRadius < 0.0f)) continue;
            backdropRadius = pending.getBlur().blurRadius();
        }
        BuiltBlur stableBounds = stableGlobalBounds;
        if (stableBounds != null) {
            for (PendingBlur pending2 : this.pendingBlurs) {
                if (!pending2.getBackdrop() || pending2.getWorldScope() || pending2.getStableBackdrop() || !BlurFramebuffer.Companion.sameBackdropBounds(pending2.getBlur(), stableBounds)) continue;
                pending2.setStableBackdrop(true);
            }
        }
        if (backdropRadius < 0.0f) {
            backdropRadius = sampleOnlyGlobalRadius;
        }
        if (backdropRadius >= 0.0f) {
            float scale = 0.5f;
            int gw = Math.max(Math.round((float)mainTarget.textureWidth * scale), 1);
            gh = Math.max(Math.round((float)mainTarget.textureHeight * scale), 1);
            this.globalResult = target = this.ensureTarget(this.globalResult, "kimiko_blur_global", gw, gh);
            if (target.getColorAttachmentView() != null) {
                TextureSetup textureSetup2;
                this.globalBlurReady = true;
                this.globalBlurRadius = backdropRadius;
                GpuTextureView gpuTextureView = target.getColorAttachmentView();
                Intrinsics.checkNotNull((Object)gpuTextureView);
                TextureSetup textureSetup3 = TextureSetup.of((GpuTextureView)gpuTextureView, (GpuSampler)sampler);
                Intrinsics.checkNotNullExpressionValue((Object)textureSetup3, (String)"singleTexture(...)");
                TextureSetup globalSetup = textureSetup3;
                stableView = stableBounds != null ? this.ensureStableBackdrop(gw, gh) : null;
                GpuTextureView gpuTextureView2 = stableView;
                if (gpuTextureView2 != null) {
                    GpuTextureView it = gpuTextureView2;
                    boolean bl = false;
                    textureSetup2 = TextureSetup.of((GpuTextureView)it, (GpuSampler)sampler);
                } else {
                    textureSetup2 = null;
                }
                TextureSetup stableSetup = textureSetup2;
                for (PendingBlur pending3 : this.pendingBlurs) {
                    if (!pending3.getBackdrop() || pending3.getWorldScope()) continue;
                    BlurCapture capture = pending3.getCapture();
                    GpuTextureView view;
                    if (pending3.getStableBackdrop() && stableView != null) {
                        view = stableView;
                    } else {
                        view = target.getColorAttachmentView();
                    }
                    TextureSetup setup = pending3.getStableBackdrop() && stableSetup != null ? stableSetup : globalSetup;
                    capture.regionX = 0.0f;
                    capture.regionY = 0.0f;
                    capture.regionW = mainTarget.textureWidth;
                    capture.regionH = mainTarget.textureHeight;
                    capture.setup = setup;
                    capture.bindBackdrop(view, sampler);
                }
                this.captureRequested = true;
            }
        }
        if (worldBackdropRadius >= 0.0f && worldBackdropRegion != null) {
            float scale = 0.5f;
            int gw = Math.max(Math.round((float)worldBackdropRegion.getWidth() * scale), 1);
            gh = Math.max(Math.round((float)worldBackdropRegion.getHeight() * scale), 1);
            this.worldGlobalResult = target = this.ensureTarget(this.worldGlobalResult, "kimiko_blur_global_world", gw, gh);
            if (target.getColorAttachmentView() != null) {
                this.worldGlobalBlurReady = true;
                this.worldGlobalBlurRadius = worldBackdropRadius;
                this.worldGlobalRegion = worldBackdropRegion;
                GpuTextureView gpuTextureView = target.getColorAttachmentView();
                Intrinsics.checkNotNull((Object)gpuTextureView);
                TextureSetup textureSetup4 = TextureSetup.of((GpuTextureView)gpuTextureView, (GpuSampler)sampler);
                Intrinsics.checkNotNullExpressionValue((Object)textureSetup4, (String)"singleTexture(...)");
                TextureSetup worldSetup = textureSetup4;
                for (PendingBlur pending4 : this.pendingBlurs) {
                    if (!pending4.getBackdrop() || !pending4.getWorldScope()) continue;
                    BlurCapture capture = pending4.getCapture();
                    capture.regionX = worldBackdropRegion.getX();
                    capture.regionY = worldBackdropRegion.getY();
                    capture.regionW = worldBackdropRegion.getWidth();
                    capture.regionH = worldBackdropRegion.getHeight();
                    capture.setup = worldSetup;
                    GpuTextureView gpuTextureView4 = target.getColorAttachmentView();
                    Intrinsics.checkNotNull((Object)gpuTextureView4);
                    capture.bindBackdrop(gpuTextureView4, sampler);
                }
                this.captureRequested = true;
            }
        }
        int i = 0;
        while (i < this.pendingBlurs.size() && i < 64) {
            int resultHeight;
            PendingBlur pending5 = (PendingBlur) (this.pendingBlurs.get(i));
            if (pending5.getBackdrop()) {
                ++i;
                continue;
            }
            BlurCapture capture = pending5.getCapture();
            Region region = this.computeRegionFor(pending5, minecraft, mainTarget);
            if (region == null) {
                ++i;
                continue;
            }
            float scale = 0.5f;
            int resultWidth = Math.max(Math.round((float)region.getWidth() * scale), 1);
            SimpleFramebuffer result = this.ensureResultTarget(i, resultWidth, resultHeight = Math.max(Math.round((float)region.getHeight() * scale), 1));
            if (result == null || result.getColorAttachmentView() == null) {
                ++i;
                continue;
            }
            pending5.setRegion(region);
            capture.regionX = region.getX();
            capture.regionY = region.getY();
            capture.regionW = region.getWidth();
            capture.regionH = region.getHeight();
            GpuTextureView gpuTextureView = result.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            Intrinsics.checkNotNullExpressionValue((Object)TextureSetup.of((GpuTextureView)gpuTextureView, (GpuSampler)sampler), (String)"singleTexture(...)");
            capture.setup = capture.setup;
            GpuTextureView gpuTextureView5 = result.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView5);
            capture.bindBackdrop(gpuTextureView5, sampler);
            this.captureRequested = true;
            ++i;
        }
    }

    public final void requestCapture(@Nullable DrawContext graphics, @Nullable BuiltBlur blur, @Nullable BlurCapture capture) {
        this.requestBackdropCapture(graphics, blur, capture, true);
    }

    public final void requestBackdropSample(@Nullable DrawContext graphics, @Nullable BuiltBlur blur, @Nullable BlurCapture capture) {
        this.requestBackdropCapture(graphics, blur, capture, false);
    }

    private final void requestBackdropCapture(DrawContext graphics, BuiltBlur blur, BlurCapture capture, boolean affectsGlobalBlur) {
        if (graphics == null || blur == null || capture == null || !blur.visible()) {
            return;
        }
        try {
            BuiltBlur normalized = this.normalize(blur);
            Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
            capture.reset();
            boolean sampleWorldScope = affectsGlobalBlur ? worldScopeActive : false;
            this.pendingBlurs.add(new PendingBlur(normalized, pose, ScissorUtil.current(), capture, true, sampleWorldScope, affectsGlobalBlur));
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    public final void prepareGuiDraw() {
        if (!this.captureRequested) {
            this.pendingBlurs.clear();
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        Framebuffer mainTarget = minecraft.getFramebuffer();
        if (mainTarget == null || mainTarget.getColorAttachmentView() == null) {
            this.pendingBlurs.clear();
            return;
        }
        Framebuffer worldSource = GuiLayerBlurRenderer.worldBackdropSource(mainTarget);
        GpuSampler gpuSampler2 = RenderSystem.getSamplerCache().get(FilterMode.LINEAR);
        Intrinsics.checkNotNullExpressionValue((Object)gpuSampler2, (String)"getClampToEdge(...)");
        GpuSampler sampler = gpuSampler2;
        RenderProfiler.Scope prepareScope = RenderProfiler.begin("ui.backdrop.prepare");
        boolean fresh = RefreshRateThrottle.updateFrame();
        SimpleFramebuffer global = this.globalResult;
        boolean globalChainUpdated = false;
        if (this.globalBlurReady && global != null) {
            long sig = BlurFramebuffer.Companion.chainSig(0, 0, mainTarget.textureWidth, mainTarget.textureHeight, this.globalBlurRadius, global.textureWidth, global.textureHeight, System.identityHashCode(mainTarget));
            if (fresh || sig != this.globalChainSig || this.globalRecaptured) {
                this.renderBlurChain(64, mainTarget, global, new Region(0, 0, mainTarget.textureWidth, mainTarget.textureHeight), this.globalBlurRadius);
                this.globalChainSig = sig;
                this.globalRecaptured = false;
                globalChainUpdated = true;
            }
        }
        if (this.globalBlurReady && global != null) {
            this.freezeStableBackdrop(mainTarget, global, sampler, globalChainUpdated);
        }
        SimpleFramebuffer worldGlobal = this.worldGlobalResult;
        Region worldRegion = this.worldGlobalRegion;
        if (this.worldGlobalBlurReady && worldGlobal != null && worldRegion != null) {
            long sig = BlurFramebuffer.Companion.chainSig(worldRegion.getX(), worldRegion.getY(), worldRegion.getWidth(), worldRegion.getHeight(), this.worldGlobalBlurRadius, worldGlobal.textureWidth, worldGlobal.textureHeight, System.identityHashCode(worldSource));
            if (fresh || sig != this.worldChainSig || this.worldRecaptured) {
                this.renderBlurChain(65, worldSource, worldGlobal, worldRegion, this.worldGlobalBlurRadius);
                this.worldChainSig = sig;
                this.worldRecaptured = false;
            }
        }
        int i = 0;
        while (i < this.pendingBlurs.size() && i < 64) {
            block15: {
                long sig;
                SimpleFramebuffer result;
                Region region;
                PendingBlur pending = this.pendingBlurs.get(i);
                block14: {
                    if (pending.getBackdrop()) {
                        ++i;
                        continue;
                    }
                    region = pending.getRegion();
                    SimpleFramebuffer simpleFramebuffer2 = result = i < this.resultTargets.size() ? this.resultTargets.get(i) : null;
                    if (region == null || result == null) {
                        ++i;
                        continue;
                    }
                    while (this.resultChainSigs.size() <= i) {
                        this.resultChainSigs.add(0L);
                    }
                    sig = BlurFramebuffer.Companion.chainSig(region.getX(), region.getY(), region.getWidth(), region.getHeight(), pending.getBlur().blurRadius(), result.textureWidth, result.textureHeight, System.identityHashCode(pending.getWorldScope() ? worldSource : mainTarget));
                    if (fresh) break block14;
                    Long l = this.resultChainSigs.get(i);
                    if (l != null && sig == l) break block15;
                }
                this.renderBlurChain(i, pending.getWorldScope() ? worldSource : mainTarget, result, region, pending.getBlur().blurRadius());
                this.resultChainSigs.set(i, sig);
            }
            ++i;
        }
        RenderProfiler.end(prepareScope);
        this.pendingBlurs.clear();
    }

    private final void freezeStableBackdrop(Framebuffer mainTarget, SimpleFramebuffer global, GpuSampler sampler, boolean refreshCopy) {
        boolean hasStableCapture = false;
        Iterator<PendingBlur> iterator = this.pendingBlurs.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<PendingBlur> iterator2 = iterator;
        while (iterator2.hasNext()) {
            PendingBlur pending = (PendingBlur) (iterator2.next());
            if (!pending.getBackdrop() || !pending.getStableBackdrop() || pending.getWorldScope()) continue;
            hasStableCapture = true;
            break;
        }
        if (!hasStableCapture || global.getColorAttachment() == null || global.getColorAttachmentView() == null) {
            return;
        }
        GpuTextureView gpuTextureView = this.ensureStableBackdrop(global.textureWidth, global.textureHeight);
        if (gpuTextureView == null) {
            return;
        }
        GpuTextureView stableView = gpuTextureView;
        if (refreshCopy || !this.stableBackdropReady) {
            try {
                CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
                GpuTexture gpuTexture = global.getColorAttachment();
                Intrinsics.checkNotNull((Object)gpuTexture);
                GpuTexture gpuTexture2 = this.stableBackdropTexture;
                Intrinsics.checkNotNull((Object)gpuTexture2);
                commandEncoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, global.textureWidth, global.textureHeight);
                this.stableBackdropReady = true;
            }
            catch (RuntimeException ignored) {
                this.stableBackdropReady = false;
                return;
            }
        }
        if (!this.stableBackdropReady) {
            return;
        }
        TextureSetup textureSetup2 = TextureSetup.of((GpuTextureView)stableView, (GpuSampler)sampler);
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup2, (String)"singleTexture(...)");
        TextureSetup stableSetup = textureSetup2;
        Iterator<PendingBlur> iterator3 = this.pendingBlurs.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator3, (String)"iterator(...)");
        Iterator<PendingBlur> iterator4 = iterator3;
        while (iterator4.hasNext()) {
            PendingBlur pending = (PendingBlur) (iterator4.next());
            if (!pending.getBackdrop() || !pending.getStableBackdrop() || pending.getWorldScope()) continue;
            BlurCapture capture = pending.getCapture();
            capture.regionX = 0.0f;
            capture.regionY = 0.0f;
            capture.regionW = mainTarget.textureWidth;
            capture.regionH = mainTarget.textureHeight;
            capture.setup = stableSetup;
            capture.bindBackdrop(stableView, sampler);
        }
    }

    private final GpuTextureView ensureStableBackdrop(int width, int height) {
        GpuTextureView gpuTextureView;
        if (width <= 0 || height <= 0) {
            return null;
        }
        GpuTexture texture = this.stableBackdropTexture;
        GpuTextureView view = this.stableBackdropView;
        if (texture != null && !texture.isClosed() && view != null && !view.isClosed() && this.stableBackdropWidth == width && this.stableBackdropHeight == height) {
            return view;
        }
        this.closeStableBackdrop();
        try {
            GpuDevice gpuDevice = RenderSystem.getDevice();
            Intrinsics.checkNotNullExpressionValue((Object)gpuDevice, (String)"getDevice(...)");
            GpuDevice device = gpuDevice;
            GpuTexture gpuTexture = device.createTexture(BlurFramebuffer::ensureStableBackdrop$lambda$0, 5, TextureFormat.RGBA8, width, height, 1, 1);
            Intrinsics.checkNotNullExpressionValue((Object)gpuTexture, (String)"createTexture(...)");
            GpuTexture created = gpuTexture;
            GpuTextureView gpuTextureView2 = device.createTextureView(created);
            Intrinsics.checkNotNullExpressionValue((Object)gpuTextureView2, (String)"createTextureView(...)");
            GpuTextureView createdView = gpuTextureView2;
            this.stableBackdropTexture = created;
            this.stableBackdropView = createdView;
            this.stableBackdropWidth = width;
            this.stableBackdropHeight = height;
            this.stableBackdropReady = false;
            gpuTextureView = createdView;
        }
        catch (RuntimeException ignored) {
            this.closeStableBackdrop();
            gpuTextureView = null;
        }
        return gpuTextureView;
    }

    private final void closeStableBackdrop() {
        this.stableBackdropReady = false;
        GpuTextureView gpuTextureView = this.stableBackdropView;
        if (gpuTextureView != null) {
            gpuTextureView.close();
        }
        this.stableBackdropView = null;
        GpuTexture gpuTexture = this.stableBackdropTexture;
        if (gpuTexture != null) {
            gpuTexture.close();
        }
        this.stableBackdropTexture = null;
        this.stableBackdropWidth = 0;
        this.stableBackdropHeight = 0;
    }

    public final void recaptureBackdrop() {
        SimpleFramebuffer global = this.globalResult;
        if (!this.globalBlurReady || global == null || global.getColorAttachmentView() == null) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        Framebuffer mainTarget = minecraft.getFramebuffer();
        if (mainTarget == null || mainTarget.getColorAttachmentView() == null) {
            return;
        }
        RenderProfiler.Scope scope = RenderProfiler.begin("ui.backdrop.recapture");
        this.renderBlurChain(64, mainTarget, global, new Region(0, 0, mainTarget.textureWidth, mainTarget.textureHeight), this.globalBlurRadius);
        RenderProfiler.end(scope);
        this.globalRecaptured = true;
    }

    public final void recaptureWorldBackdrop() {
        SimpleFramebuffer worldGlobal = this.worldGlobalResult;
        Region worldRegion = this.worldGlobalRegion;
        if (!this.worldGlobalBlurReady || worldGlobal == null || worldRegion == null || worldGlobal.getColorAttachmentView() == null) {
            return;
        }
        Framebuffer source = GuiLayerBlurRenderer.worldSnapshotWithPanels();
        if (source == null || source.getColorAttachmentView() == null) {
            return;
        }
        RenderProfiler.Scope scope = RenderProfiler.begin("ui.backdrop.recapture.world");
        this.renderBlurChain(65, source, worldGlobal, worldRegion, this.worldGlobalBlurRadius);
        RenderProfiler.end(scope);
        this.worldRecaptured = true;
    }

    public final boolean isBlurPipeline(@Nullable RenderPipeline pipeline) {
        return pipeline == BATCHED_BLUR_PIPELINE;
    }

    public final void bindBlurParams(@Nullable RenderPass renderPass) {
        if (renderPass == null || this.preparedBlurs.isEmpty()) {
            return;
        }
        GpuBuffer buffer = this.ensureParamsBuffer();
        if (buffer != null) {
            renderPass.setUniform("BlurParamsArray", buffer);
        }
    }

    public final int reserve(@NotNull BuiltBlur blur, @NotNull BlurCapture capture) {
        Intrinsics.checkNotNullParameter((Object)blur, (String)"blur");
        Intrinsics.checkNotNullParameter((Object)capture, (String)"capture");
        int index = this.preparedBlurs.size();
        if (index == 512) {
            return -1;
        }
        this.preparedBlurs.add(new PreparedBlur(blur, capture));
        this.paramsDirty = true;
        return index;
    }

    private final void submit(DrawContext graphics, BuiltBlur blur) {
        if (blur == null || !blur.visible()) {
            return;
        }
        if (graphics == null) {
            return;
        }
        try {
            BuiltBlur normalized = this.normalize(blur);
            Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
            ScreenRect scissorArea = ScissorUtil.current();
            BlurCapture capture = new BlurCapture();
            this.pendingBlurs.add(new PendingBlur(normalized, pose, scissorArea, capture, false, worldScopeActive, false, 64, null));
            ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState().addSimpleElement((SimpleGuiElementRenderState)new BlurRenderState(pose, normalized, scissorArea, capture));
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final GpuBuffer ensureParamsBuffer() {
        if (this.paramsDirty) {
            this.prepareBuffers();
        }
        if (!this.paramsDirty && this.paramsBuffer != null) {
            return this.paramsBuffer;
        }
        if (this.paramsDirty) {
            this.closeParamsBuffer();
            AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
            Throwable throwable = null;
            try {
                MemoryStack stack = (MemoryStack)autoCloseable;
                boolean bl = false;
                Intrinsics.checkNotNull((Object)stack);
                ByteBuffer uniformData = this.buildUniformData(stack, (List<PreparedBlur>)this.preparedBlurs);
                this.paramsBuffer = RenderSystem.getDevice().createBuffer(BlurFramebuffer::ensureParamsBuffer$lambda$0$0, 136, uniformData);
                Unit unit = Unit.INSTANCE;
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
            }
            this.paramsDirty = false;
            return this.paramsBuffer;
        }
        GpuBuffer current = this.paramsBuffer;
        if (current == null || current.isClosed() || current.size() < 24576L) {
            this.closeParamsBuffer();
            this.paramsBuffer = RenderSystem.getDevice().createBuffer(BlurFramebuffer::ensureParamsBuffer$lambda$1, 136, 24576L);
        }
        return this.paramsBuffer;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void prepareBuffers() {
        if (this.preparedBlurs.isEmpty() || !this.paramsDirty) {
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
                ByteBuffer uniformData = this.buildUniformData(stack, (List<PreparedBlur>)this.preparedBlurs);
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

    private final GpuBuffer ensureWritableParamsBuffer() {
        GpuBuffer current = this.paramsBuffer;
        if (current != null && !current.isClosed() && current.size() >= 24576L) {
            return current;
        }
        this.closeParamsBuffer();
        try {
            GpuBuffer created = RenderSystem.getDevice().createBuffer(BlurFramebuffer::ensureWritableParamsBuffer$lambda$0, 136, 24576L);
            this.paramsBuffer = created;
            return created;
        }
        catch (RuntimeException ignored) {
            return null;
        }
    }

    private final ByteBuffer buildUniformData(MemoryStack stack, List<PreparedBlur> batch) {
        int usedBytes = Math.max(1, batch.size()) * 3 * 4 * 4;
        ByteBuffer data = stack.calloc(usedBytes);
        int n = ((Collection)batch).size();
        for (int i = 0; i < n; ++i) {
            PreparedBlur prepared = batch.get(i);
            BuiltBlur blur = prepared.getBlur();
            BlurCapture capture = prepared.getCapture();
            int offset = i * 3 * 4 * 4;
            data.putFloat(offset, blur.radiusTopLeft());
            data.putFloat(offset + 4, blur.radiusTopRight());
            data.putFloat(offset + 8, blur.radiusBottomRight());
            data.putFloat(offset + 12, blur.radiusBottomLeft());
            data.putFloat(offset + 16, blur.width());
            data.putFloat(offset + 20, blur.height());
            data.putFloat(offset + 24, blur.smoothness());
            data.putFloat(offset + 28, blur.blurRadius());
            data.putFloat(offset + 32, capture.regionX);
            data.putFloat(offset + 36, capture.regionY);
            data.putFloat(offset + 40, capture.regionW);
            data.putFloat(offset + 44, capture.regionH);
        }
        data.position(0);
        Intrinsics.checkNotNull((Object)data);
        return data;
    }

    private final SimpleFramebuffer ensureResultTarget(int index, int width, int height) {
        if (width <= 0 || height <= 0) {
            return null;
        }
        while (this.resultTargets.size() <= index) {
            this.resultTargets.add(null);
        }
        SimpleFramebuffer target = this.ensureTarget(this.resultTargets.get(index), "kimiko_blur_result_" + index, width, height);
        this.resultTargets.set(index, target);
        return target;
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
        int targetWidth = Math.max(width, 1);
        int targetHeight = Math.max(height, 1);
        for (int i = 0; i < iterations; ++i) {
            down[i] = this.ensureTarget(down[i], "kimiko_blur_down_" + index + "_" + i, targetWidth, targetHeight);
            if (i < iterations - 1) {
                up[i] = this.ensureTarget(up[i], "kimiko_blur_up_" + index + "_" + i, targetWidth, targetHeight);
            }
            targetWidth = Math.max(targetWidth / 2, 1);
            targetHeight = Math.max(targetHeight / 2, 1);
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

    private final Region computeRegionFor(PendingBlur pendingBlur, MinecraftClient minecraft, Framebuffer mainTarget) {
        Window window2 = minecraft.getWindow();
        Intrinsics.checkNotNullExpressionValue((Object)window2, (String)"getWindow(...)");
        Window window = window2;
        int guiWidth = Math.max(window.getScaledWidth(), 1);
        int guiHeight = Math.max(window.getScaledHeight(), 1);
        float scaleX = (float)mainTarget.textureWidth / (float)guiWidth;
        float scaleY = (float)mainTarget.textureHeight / (float)guiHeight;
        BuiltBlur blur = pendingBlur.getBlur();
        float padding = Render2DCoordinateSpace.toGui(Math.max(16.0f, blur.blurRadius() * 2.0f));
        ScreenRect screenRect2 = new ScreenRect(Math.round(blur.x()), Math.round(blur.y()), Math.round(blur.width()), Math.round(blur.height())).transformEachVertex((Matrix3x2fc)pendingBlur.getPose());
        Intrinsics.checkNotNullExpressionValue((Object)screenRect2, (String)"transformMaxBounds(...)");
        ScreenRect bounds = screenRect2;
        ScreenRect scissorArea = pendingBlur.getScissorArea();
        if (scissorArea != null) {
            ScreenRect screenRect3 = scissorArea.intersection(bounds);
            if (screenRect3 == null) {
                return null;
            }
            bounds = screenRect3;
        }
        float minX = BlurFramebuffer.Companion.clamp((float)bounds.getLeft() - padding, 0.0f, guiWidth);
        float minY = BlurFramebuffer.Companion.clamp((float)bounds.getTop() - padding, 0.0f, guiHeight);
        float maxX = BlurFramebuffer.Companion.clamp((float)bounds.getRight() + padding, minX + 1.0f, guiWidth);
        float maxY = BlurFramebuffer.Companion.clamp((float)bounds.getBottom() + padding, minY + 1.0f, guiHeight);
        int grid = 8;
        int x0 = BlurFramebuffer.Companion.clampInt(BlurFramebuffer.Companion.snapDown((int)Math.floor(minX * scaleX), grid), 0, mainTarget.textureWidth - 1);
        int x1 = BlurFramebuffer.Companion.clampInt(BlurFramebuffer.Companion.snapUp((int)Math.ceil(maxX * scaleX), grid), x0 + 1, mainTarget.textureWidth);
        int y0 = BlurFramebuffer.Companion.clampInt(BlurFramebuffer.Companion.snapDown((int)Math.floor(((float)guiHeight - maxY) * scaleY), grid), 0, mainTarget.textureHeight - 1);
        int y1 = BlurFramebuffer.Companion.clampInt(BlurFramebuffer.Companion.snapUp((int)Math.ceil(((float)guiHeight - minY) * scaleY), grid), y0 + 1, mainTarget.textureHeight);
        int w = Math.max(x1 - x0, 1);
        int h = Math.max(y1 - y0, 1);
        return new Region(x0, y0, w, h);
    }

    private final void renderBlurChain(int index, Framebuffer mainTarget, SimpleFramebuffer resultTarget, Region region, float blurRadius) {
        if (resultTarget == null || region == null || mainTarget == null) {
            return;
        }
        int iterations = this.preparedIterations > 0 ? this.preparedIterations : this.computeIterations(blurRadius);
        this.ensureScratchTargets(index, resultTarget.textureWidth, resultTarget.textureHeight, iterations);
        SimpleFramebuffer[] class_6367Array = this.downPool.get(index);
        Intrinsics.checkNotNullExpressionValue((Object)class_6367Array, (String)"get(...)");
        SimpleFramebuffer[] downTargets = class_6367Array;
        SimpleFramebuffer[] class_6367Array2 = this.upPool.get(index);
        Intrinsics.checkNotNullExpressionValue((Object)class_6367Array2, (String)"get(...)");
        SimpleFramebuffer[] upTargets = class_6367Array2;
        float offsetScale = BlurFramebuffer.Companion.clamp(blurRadius / 18.0f, 0.15f, 3.0f);
        GpuSampler gpuSampler2 = RenderSystem.getSamplerCache().get(FilterMode.LINEAR);
        Intrinsics.checkNotNullExpressionValue((Object)gpuSampler2, (String)"getClampToEdge(...)");
        GpuSampler sampler = gpuSampler2;
        GpuTextureView source = mainTarget.getColorAttachmentView();
        int sourceWidth = mainTarget.textureWidth;
        int sourceHeight = mainTarget.textureHeight;
        float sourceX = (float)region.getX() / (float)Math.max(mainTarget.textureWidth, 1);
        float sourceY = (float)region.getY() / (float)Math.max(mainTarget.textureHeight, 1);
        float sourceW = (float)region.getWidth() / (float)Math.max(mainTarget.textureWidth, 1);
        float sourceH = (float)region.getHeight() / (float)Math.max(mainTarget.textureHeight, 1);
        for (int i = 0; i < iterations; ++i) {
            SimpleFramebuffer target = downTargets[i];
            this.renderKawasePass(KAWASE_DOWN_PIPELINE, source, sourceWidth, sourceHeight, target, offsetScale, sourceX, sourceY, sourceW, sourceH, sampler);
            source = target.getColorAttachmentView();
            sourceWidth = target.textureWidth;
            sourceHeight = target.textureHeight;
            sourceX = 0.0f;
            sourceY = 0.0f;
            sourceW = 1.0f;
            sourceH = 1.0f;
        }
        for (int i = iterations - 2; i >= 0; --i) {
            SimpleFramebuffer target = upTargets[i];
            this.renderKawasePass(KAWASE_UP_PIPELINE, source, sourceWidth, sourceHeight, target, offsetScale, 0.0f, 0.0f, 1.0f, 1.0f, sampler);
            source = target.getColorAttachmentView();
            sourceWidth = target.textureWidth;
            sourceHeight = target.textureHeight;
        }
        this.renderKawasePass(KAWASE_UP_PIPELINE, source, sourceWidth, sourceHeight, resultTarget, offsetScale, 0.0f, 0.0f, 1.0f, 1.0f, sampler);
    }

    private final int computeIterations(float blurRadius) {
        return 2;
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
        GpuBuffer fullscreenQuad = gpuBuffer;
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        RenderSystem.ShapeIndexBuffer shapeIndexBuffer2 = RenderSystem.getSequentialBuffer((VertexFormat.DrawMode)VertexFormat.DrawMode.QUADS);
        Intrinsics.checkNotNullExpressionValue((Object)shapeIndexBuffer2, (String)"getSequentialBuffer(...)");
        RenderSystem.ShapeIndexBuffer indexBuffer = shapeIndexBuffer2;
        GpuBuffer gpuBuffer2 = indexBuffer.getIndexBuffer(6);
        Intrinsics.checkNotNullExpressionValue((Object)gpuBuffer2, (String)"getBuffer(...)");
        GpuBuffer indices = gpuBuffer2;
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer uniformData = stack.calloc(48);
            uniformData.putFloat(0, sourceX);
            uniformData.putFloat(4, sourceY);
            uniformData.putFloat(8, sourceW);
            uniformData.putFloat(12, sourceH);
            uniformData.putFloat(16, offsetScale / (float)Math.max(sourceWidth, 1));
            uniformData.putFloat(20, offsetScale / (float)Math.max(sourceHeight, 1));
            uniformData.putFloat(32, skyFallbackRed);
            uniformData.putFloat(36, skyFallbackGreen);
            uniformData.putFloat(40, skyFallbackBlue);
            uniformData.putFloat(44, 1.0f);
            uniformData.position(0);
            GpuBuffer uniformBuffer = this.ensureKawaseParamsBuffer();
            if (uniformBuffer == null) {
                return;
            }
            encoder.writeToBuffer(uniformBuffer.slice(0L, 48L), uniformData);
            Supplier<String> supplier = BlurFramebuffer::renderKawasePass$lambda$0$0;
            GpuTextureView gpuTextureView = target.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            AutoCloseable autoCloseable2 = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0), null, OptionalDouble.empty());
            Throwable throwable2 = null;
            try {
                RenderPass renderPass = (RenderPass)autoCloseable2;
                boolean bl2 = false;
                renderPass.setPipeline(pipeline);
                renderPass.bindTexture("Sampler0", source, sampler);
                renderPass.setUniform("KawaseParams", uniformBuffer);
                renderPass.setVertexBuffer(0, fullscreenQuad);
                renderPass.setIndexBuffer(indices, indexBuffer.getIndexType());
                renderPass.drawIndexed(0, 0, 6, 1);
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

    private final GpuBuffer ensureKawaseParamsBuffer() {
        GpuBuffer current = this.kawaseParamsBuffer;
        if (current == null || current.isClosed() || current.size() < 48L) {
            this.closeKawaseParamsBuffer();
            this.kawaseParamsBuffer = RenderSystem.getDevice().createBuffer(BlurFramebuffer::ensureKawaseParamsBuffer$lambda$0, 136, 48L);
        }
        return this.kawaseParamsBuffer;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final GpuBuffer ensureFullscreenQuadBuffer() {
        GpuBuffer gpuBuffer;
        GpuBuffer gpuBuffer2 = this.fullscreenQuadBuffer;
        if (gpuBuffer2 != null) {
            GpuBuffer it = gpuBuffer2;
            boolean bl = false;
            return it;
        }
        AutoCloseable autoCloseable = (AutoCloseable)new BufferAllocator(4 * VertexFormats.POSITION.getVertexSize());
        Throwable throwable = null;
        try {
            GpuBuffer gpuBuffer3;
            BufferAllocator allocator = (BufferAllocator)autoCloseable;
            boolean bl = false;
            AutoCloseable autoCloseable2 = (AutoCloseable)this.buildFullscreenQuad(allocator);
            Throwable throwable2 = null;
            try {
                GpuBuffer created;
                BuiltBuffer meshData = (BuiltBuffer)autoCloseable2;
                boolean bl2 = false;
                GpuBuffer gpuBuffer4 = RenderSystem.getDevice().createBuffer(BlurFramebuffer::ensureFullscreenQuadBuffer$lambda$1$0$0, 32, meshData.getBuffer());
                Intrinsics.checkNotNullExpressionValue((Object)gpuBuffer4, (String)"createBuffer(...)");
                this.fullscreenQuadBuffer = created = gpuBuffer4;
                gpuBuffer3 = created;
            }
            catch (Throwable throwable3) {
                throwable2 = throwable3;
                throw throwable3;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable2, (Throwable)throwable2);
            }
            gpuBuffer = gpuBuffer3;
        }
        catch (Throwable throwable4) {
            throwable = throwable4;
            throw throwable4;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        return gpuBuffer;
    }

    private final BuiltBuffer buildFullscreenQuad(BufferAllocator allocator) {
        BufferBuilder builder = new BufferBuilder(allocator, VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
        builder.vertex(-1.0f, -1.0f, 0.0f);
        builder.vertex(-1.0f, 1.0f, 0.0f);
        builder.vertex(1.0f, 1.0f, 0.0f);
        builder.vertex(1.0f, -1.0f, 0.0f);
        BuiltBuffer builtBuffer2 = builder.end();
        Intrinsics.checkNotNullExpressionValue((Object)builtBuffer2, (String)"buildOrThrow(...)");
        return builtBuffer2;
    }

    private final BuiltBlur normalize(BuiltBlur blur) {
        float maxRadius = Math.max(0.0f, Math.min(blur.width(), blur.height()) * 0.5f);
        float radiusTopLeft = BlurFramebuffer.Companion.clamp(blur.radiusTopLeft(), 0.0f, maxRadius);
        float radiusTopRight = BlurFramebuffer.Companion.clamp(blur.radiusTopRight(), 0.0f, maxRadius);
        float radiusBottomRight = BlurFramebuffer.Companion.clamp(blur.radiusBottomRight(), 0.0f, maxRadius);
        float radiusBottomLeft = BlurFramebuffer.Companion.clamp(blur.radiusBottomLeft(), 0.0f, maxRadius);
        float smoothness = Math.max(0.0f, blur.smoothness());
        float blurRadius = Math.max(0.0f, blur.blurRadius());
        if (radiusTopLeft == blur.radiusTopLeft() && radiusTopRight == blur.radiusTopRight() && radiusBottomRight == blur.radiusBottomRight() && radiusBottomLeft == blur.radiusBottomLeft() && smoothness == blur.smoothness() && blurRadius == blur.blurRadius()) {
            return blur;
        }
        return new BuiltBlur(blur.x(), blur.y(), blur.width(), blur.height(), radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, smoothness, blurRadius, blur.colorTopLeft(), blur.colorTopRight(), blur.colorBottomRight(), blur.colorBottomLeft());
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
        SimpleFramebuffer it;
        this.pendingBlurs.clear();
        this.preparedBlurs.clear();
        this.activeGraphics = null;
        this.captureRequested = false;
        this.closeParamsBuffer();
        this.closeKawaseParamsBuffer();
        Iterator<SimpleFramebuffer> iterator = this.resultTargets.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<SimpleFramebuffer> iterator2 = iterator;
        while (iterator2.hasNext()) {
            SimpleFramebuffer target;
            SimpleFramebuffer simpleFramebuffer2 = target = iterator2.next();
            if (simpleFramebuffer2 == null) continue;
            simpleFramebuffer2.delete();
        }
        this.resultTargets.clear();
        SimpleFramebuffer simpleFramebuffer3 = this.globalResult;
        if (simpleFramebuffer3 != null) {
            it = simpleFramebuffer3;
            boolean bl = false;
            it.delete();
            this.globalResult = null;
        }
        this.globalBlurReady = false;
        this.closeStableBackdrop();
        SimpleFramebuffer simpleFramebuffer4 = this.worldGlobalResult;
        if (simpleFramebuffer4 != null) {
            it = simpleFramebuffer4;
            boolean bl = false;
            it.delete();
            this.worldGlobalResult = null;
        }
        this.worldGlobalBlurReady = false;
        this.worldGlobalRegion = null;
        this.globalChainSig = 0L;
        this.worldChainSig = 0L;
        this.globalRecaptured = false;
        this.worldRecaptured = false;
        this.resultChainSigs.clear();
        BlurFramebuffer.Companion.destroyScratchPool(this.downPool);
        BlurFramebuffer.Companion.destroyScratchPool(this.upPool);
        GpuBuffer gpuBuffer = this.fullscreenQuadBuffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        this.fullscreenQuadBuffer = null;
    }

    private static final String ensureStableBackdrop$lambda$0() {
        return "kimiko:watermark_backdrop";
    }

    private static final String ensureParamsBuffer$lambda$0$0() {
        return "kimiko_blur_params";
    }

    private static final String ensureParamsBuffer$lambda$1() {
        return "kimiko_blur_params";
    }

    private static final String ensureWritableParamsBuffer$lambda$0() {
        return "kimiko_blur_params";
    }

    private static final String renderKawasePass$lambda$0$0() {
        return "kimiko_kawase_blur";
    }

    private static final String ensureKawaseParamsBuffer$lambda$0() {
        return "kimiko_kawase_params";
    }

    private static final String ensureFullscreenQuadBuffer$lambda$1$0$0() {
        return "kimiko_blur_fullscreen_quad";
    }

    @JvmStatic
    @NotNull
    public static final BlurFramebuffer getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final void closeInstance() {
        Companion.closeInstance();
    }

    @JvmStatic
    public static final void setSkyFallbackColor(float red, float green, float blue) {
        Companion.setSkyFallbackColor(red, green, blue);
    }

    @JvmStatic
    public static final void beginWorldScope() {
        Companion.beginWorldScope();
    }

    @JvmStatic
    public static final void endWorldScope() {
        Companion.endWorldScope();
    }

    public /* synthetic */ BlurFramebuffer(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    static {
        skyFallbackRed = 0.55f;
        skyFallbackGreen = 0.65f;
        skyFallbackBlue = 0.78f;
        RenderPipeline renderPipeline = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(BlurFramebuffer.Companion.id("pipeline/batched_blur")).withVertexShader(BlurFramebuffer.Companion.id("ui/batched_blur/batched_blur")).withFragmentShader(BlurFramebuffer.Companion.id("ui/batched_blur/batched_blur")).withVertexFormat(VertexFormats.POSITION_COLOR_LINE_WIDTH, VertexFormat.DrawMode.QUADS).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withSampler("Sampler0").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withUniform("BlurParamsArray", UniformType.UNIFORM_BUFFER).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"build(...)");
        BATCHED_BLUR_PIPELINE = renderPipeline;
        RenderPipeline renderPipeline2 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(BlurFramebuffer.Companion.id("pipeline/kawase_down")).withVertexShader(BlurFramebuffer.Companion.id("ui/kawase/down")).withFragmentShader(BlurFramebuffer.Companion.id("ui/kawase/down")).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.QUADS).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withSampler("Sampler0").withUniform("KawaseParams", UniformType.UNIFORM_BUFFER).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline2, (String)"build(...)");
        KAWASE_DOWN_PIPELINE = renderPipeline2;
        RenderPipeline renderPipeline3 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(BlurFramebuffer.Companion.id("pipeline/kawase_up")).withVertexShader(BlurFramebuffer.Companion.id("ui/kawase/down")).withFragmentShader(BlurFramebuffer.Companion.id("ui/kawase/up")).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.QUADS).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withSampler("Sampler0").withUniform("KawaseParams", UniformType.UNIFORM_BUFFER).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline3, (String)"build(...)");
        KAWASE_UP_PIPELINE = renderPipeline3;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010!\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J+\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u0013\u0010\u0011\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0011\u0010\u0003JO\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ%\u0010!\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010 \u001a\u0004\u0018\u00010\u001eH\u0002\u00a2\u0006\u0004\b!\u0010\"J\u001f\u0010%\u001a\u00020$2\u0006\u0010\u001f\u001a\u00020#2\u0006\u0010 \u001a\u00020#H\u0002\u00a2\u0006\u0004\b%\u0010&J\u001f\u0010)\u001a\u00020\u00122\u0006\u0010'\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b)\u0010*J\u001f\u0010+\u001a\u00020\u00122\u0006\u0010'\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b+\u0010*J'\u0010.\u001a\u00020\n2\u0006\u0010'\u001a\u00020\n2\u0006\u0010,\u001a\u00020\n2\u0006\u0010-\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b.\u0010/J'\u00100\u001a\u00020\u00122\u0006\u0010'\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b0\u00101J%\u00106\u001a\u00020\b2\u0014\u00105\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001040302H\u0002\u00a2\u0006\u0004\b6\u00107J\u0017\u0010;\u001a\u00020:2\u0006\u00109\u001a\u000208H\u0002\u00a2\u0006\u0004\b;\u0010<R\u0014\u0010=\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b=\u0010>R\u0014\u0010?\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b?\u0010>R\u0014\u0010@\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u0010>R\u0014\u0010A\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bA\u0010>R\u0014\u0010B\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bB\u0010>R\u0014\u0010C\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u0010>R\u0014\u0010D\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u0014\u0010F\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bF\u0010>R\u0014\u0010G\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u0010>R\u0014\u0010H\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bH\u0010>R\u0018\u0010I\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bI\u0010JR\u0016\u0010K\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010ER\u0016\u0010L\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bL\u0010ER\u0016\u0010M\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010ER\u0016\u0010N\u001a\u00020$8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0019\u0010R\u001a\u00020P8\u0006X\u0087\u0004\u0092\u0002\u0002\bQ\u00a2\u0006\u0006\n\u0004\bR\u0010SR\u0019\u0010T\u001a\u00020P8\u0006X\u0087\u0004\u0092\u0002\u0002\bQ\u00a2\u0006\u0006\n\u0004\bT\u0010SR\u0019\u0010U\u001a\u00020P8\u0006X\u0087\u0004\u0092\u0002\u0002\bQ\u00a2\u0006\u0006\n\u0004\bU\u0010S\u00a8\u0006V"}, d2={"Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer;", "", "closeInstance", "", "red", "green", "blue", "setSkyFallbackColor", "(FFF)V", "beginWorldScope", "endWorldScope", "", "x", "y", "w", "h", "radius", "resultW", "resultH", "scope", "", "chainSig", "(IIIIFIII)J", "Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$Region;", "first", "second", "union", "(Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$Region;Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$Region;)Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$Region;", "Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;", "", "sameBackdropBounds", "(Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;)Z", "value", "grid", "snapDown", "(II)I", "snapUp", "min", "max", "clamp", "(FFF)F", "clampInt", "(III)I", "", "", "Lnet/minecraft/SimpleFramebuffer;", "pool", "destroyScratchPool", "(Ljava/util/List;)V", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "MAX_BLUR_RECTS", "I", "MAX_BLUR_ITERATIONS", "PARAMS_PER_RECT", "FLOATS_PER_PARAM", "UNIFORM_BYTES", "KAWASE_UNIFORM_BYTES", "MAX_BLUR_RENDER_SCALE", "F", "MAX_CAPTURES", "GLOBAL_SCRATCH_INDEX", "WORLD_GLOBAL_SCRATCH_INDEX", "instance", "Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer;", "skyFallbackRed", "skyFallbackGreen", "skyFallbackBlue", "worldScopeActive", "Z", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "BATCHED_BLUR_PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "KAWASE_DOWN_PIPELINE", "KAWASE_UP_PIPELINE", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        @NotNull
        public final BlurFramebuffer getInstance() {
            BlurFramebuffer local = null;
            local = instance;
            if (local == null) {
                Class<BlurFramebuffer> clazz = BlurFramebuffer.class;
                synchronized (clazz) {
                    boolean bl = false;
                    local = instance;
                    if (local == null) {
                        local = new BlurFramebuffer(null);
                        instance = local;
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return local;
        }

        @JvmStatic
        public final void closeInstance() {
            BlurFramebuffer local = instance;
            if (local != null) {
                local.close();
                instance = null;
            }
        }

        @JvmStatic
        public final void setSkyFallbackColor(float red, float green, float blue) {
            if (!(Math.abs(red) <= Float.MAX_VALUE && Math.abs(green) <= Float.MAX_VALUE && Math.abs(blue) <= Float.MAX_VALUE)) {
                return;
            }
            skyFallbackRed = this.clamp(red, 0.0f, 1.0f);
            skyFallbackGreen = this.clamp(green, 0.0f, 1.0f);
            skyFallbackBlue = this.clamp(blue, 0.0f, 1.0f);
        }

        @JvmStatic
        public final void beginWorldScope() {
            worldScopeActive = true;
        }

        @JvmStatic
        public final void endWorldScope() {
            worldScopeActive = false;
        }

        private final long chainSig(int x, int y, int w, int h, float radius, int resultW, int resultH, int scope) {
            long sig = 1469598103934665603L;
            sig = (sig ^ (long)x) * 1099511628211L;
            sig = (sig ^ (long)y) * 1099511628211L;
            sig = (sig ^ (long)w) * 1099511628211L;
            sig = (sig ^ (long)h) * 1099511628211L;
            sig = (sig ^ (long)Float.floatToIntBits(radius)) * 1099511628211L;
            sig = (sig ^ (long)resultW) * 1099511628211L;
            sig = (sig ^ (long)resultH) * 1099511628211L;
            return (sig = (sig ^ (long)scope) * 1099511628211L) == 0L ? 1L : sig;
        }

        private final Region union(Region first, Region second) {
            if (first == null) {
                return second;
            }
            if (second == null) {
                return first;
            }
            int x = Math.min(first.getX(), second.getX());
            int y = Math.min(first.getY(), second.getY());
            int right = Math.max(first.getX() + first.getWidth(), second.getX() + second.getWidth());
            int bottom = Math.max(first.getY() + first.getHeight(), second.getY() + second.getHeight());
            return new Region(x, y, right - x, bottom - y);
        }

        private final boolean sameBackdropBounds(BuiltBlur first, BuiltBlur second) {
            return Math.abs(first.x() - second.x()) <= 0.01f && Math.abs(first.y() - second.y()) <= 0.01f && Math.abs(first.width() - second.width()) <= 0.01f && Math.abs(first.height() - second.height()) <= 0.01f;
        }

        private final int snapDown(int value, int grid) {
            return Math.floorDiv(value, grid) * grid;
        }

        private final int snapUp(int value, int grid) {
            return -Math.floorDiv(-value, grid) * grid;
        }

        private final float clamp(float value, float min, float max) {
            return Math.max(min, Math.min(max, value));
        }

        private final int clampInt(int value, int min, int max) {
            return Math.max(min, Math.min(max, value));
        }

        private final void destroyScratchPool(List<SimpleFramebuffer[]> pool) {
            for (SimpleFramebuffer[] targets : pool) {
                if (targets == null) continue;
                for (int i = 0; i < targets.length; i++) {
                    if (targets[i] != null) {
                        targets[i].delete();
                        targets[i] = null;
                    }
                }
            }
            pool.clear();
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

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\n\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u000b\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\f\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\f\u0010\u001c\u001a\u0004\b\u001f\u0010\u001eR\u0017\u0010\r\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\r\u0010\u001c\u001a\u0004\b \u0010\u001eR$\u0010\"\u001a\u0004\u0018\u00010!8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\"\u0010(\u001a\u00020\n8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b(\u0010\u001c\u001a\u0004\b)\u0010\u001e\"\u0004\b*\u0010+\u00a8\u0006,"}, d2={"Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$PendingBlur;", "", "Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;", "blur", "Lorg/joml/Matrix3x2f;", "pose", "Lnet/minecraft/ScreenRect;", "scissorArea", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "capture", "", "backdrop", "worldScope", "affectsGlobalBlur", "<init>", "(Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;Lorg/joml/Matrix3x2f;Lnet/minecraft/ScreenRect;Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;ZZZ)V", "Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;", "getBlur", "()Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;", "Lorg/joml/Matrix3x2f;", "getPose", "()Lorg/joml/Matrix3x2f;", "Lnet/minecraft/ScreenRect;", "getScissorArea", "()Lnet/minecraft/ScreenRect;", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "getCapture", "()Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "Z", "getBackdrop", "()Z", "getWorldScope", "getAffectsGlobalBlur", "Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$Region;", "region", "Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$Region;", "getRegion", "()Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$Region;", "setRegion", "(Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$Region;)V", "stableBackdrop", "getStableBackdrop", "setStableBackdrop", "(Z)V", "rtx.kimiko:kimiko"})
    private static final class PendingBlur {
        @NotNull
        private final BuiltBlur blur;
        @NotNull
        private final Matrix3x2f pose;
        @Nullable
        private final ScreenRect scissorArea;
        @NotNull
        private final BlurCapture capture;
        private final boolean backdrop;
        private final boolean worldScope;
        private final boolean affectsGlobalBlur;
        @Nullable
        private Region region;
        private boolean stableBackdrop;

        public PendingBlur(@NotNull BuiltBlur blur, @NotNull Matrix3x2f pose, @Nullable ScreenRect scissorArea, @NotNull BlurCapture capture, boolean backdrop, boolean worldScope, boolean affectsGlobalBlur) {
            Intrinsics.checkNotNullParameter((Object)blur, (String)"blur");
            Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
            Intrinsics.checkNotNullParameter((Object)capture, (String)"capture");
            this.blur = blur;
            this.pose = pose;
            this.scissorArea = scissorArea;
            this.capture = capture;
            this.backdrop = backdrop;
            this.worldScope = worldScope;
            this.affectsGlobalBlur = affectsGlobalBlur;
        }

        public /* synthetic */ PendingBlur(BuiltBlur builtBlur, Matrix3x2f matrix3x2f, ScreenRect screenRect2, BlurCapture blurCapture, boolean bl, boolean bl2, boolean bl3, int n, DefaultConstructorMarker defaultConstructorMarker) {
            this(builtBlur, matrix3x2f, screenRect2, blurCapture, bl, bl2, (n & 0x40) != 0 ? true : bl3);
        }

        @NotNull
        public final BuiltBlur getBlur() {
            return this.blur;
        }

        @NotNull
        public final Matrix3x2f getPose() {
            return this.pose;
        }

        @Nullable
        public final ScreenRect getScissorArea() {
            return this.scissorArea;
        }

        @NotNull
        public final BlurCapture getCapture() {
            return this.capture;
        }

        public final boolean getBackdrop() {
            return this.backdrop;
        }

        public final boolean getWorldScope() {
            return this.worldScope;
        }

        public final boolean getAffectsGlobalBlur() {
            return this.affectsGlobalBlur;
        }

        @Nullable
        public final Region getRegion() {
            return this.region;
        }

        public final void setRegion(@Nullable Region region) {
            this.region = region;
        }

        public final boolean getStableBackdrop() {
            return this.stableBackdrop;
        }

        public final void setStableBackdrop(boolean bl) {
            this.stableBackdrop = bl;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u000b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$PreparedBlur;", "", "Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;", "blur", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "capture", "<init>", "(Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;)V", "component1", "()Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;", "component2", "()Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "copy", "(Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;)Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$PreparedBlur;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;", "getBlur", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "getCapture", "rtx.kimiko:kimiko"})
    private static final class PreparedBlur {
        @NotNull
        private final BuiltBlur blur;
        @NotNull
        private final BlurCapture capture;

        public PreparedBlur(@NotNull BuiltBlur blur, @NotNull BlurCapture capture) {
            Intrinsics.checkNotNullParameter((Object)blur, (String)"blur");
            Intrinsics.checkNotNullParameter((Object)capture, (String)"capture");
            this.blur = blur;
            this.capture = capture;
        }

        @NotNull
        public final BuiltBlur getBlur() {
            return this.blur;
        }

        @NotNull
        public final BlurCapture getCapture() {
            return this.capture;
        }

        @NotNull
        public final BuiltBlur component1() {
            return this.blur;
        }

        @NotNull
        public final BlurCapture component2() {
            return this.capture;
        }

        @NotNull
        public final PreparedBlur copy(@NotNull BuiltBlur blur, @NotNull BlurCapture capture) {
            Intrinsics.checkNotNullParameter((Object)blur, (String)"blur");
            Intrinsics.checkNotNullParameter((Object)capture, (String)"capture");
            return new PreparedBlur(blur, capture);
        }

        public static /* synthetic */ PreparedBlur copy$default(PreparedBlur preparedBlur, BuiltBlur builtBlur, BlurCapture blurCapture, int n, Object object) {
            if ((n & 1) != 0) {
                builtBlur = preparedBlur.blur;
            }
            if ((n & 2) != 0) {
                blurCapture = preparedBlur.capture;
            }
            return preparedBlur.copy(builtBlur, blurCapture);
        }

        @NotNull
        public String toString() {
            return "PreparedBlur(blur=" + this.blur + ", capture=" + this.capture + ")";
        }

        public int hashCode() {
            int result = this.blur.hashCode();
            result = result * 31 + this.capture.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PreparedBlur)) {
                return false;
            }
            PreparedBlur preparedBlur = (PreparedBlur)other;
            if (!Intrinsics.areEqual((Object)this.blur, (Object)preparedBlur.blur)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.capture, (Object)preparedBlur.capture);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\nJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\nJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\nJ8\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0014\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0014\u0010\nJ\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\nR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u001a\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u001b\u0010\nR\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u001c\u0010\n\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$Region;", "", "", "x", "y", "width", "height", "<init>", "(IIII)V", "component1", "()I", "component2", "component3", "component4", "copy", "(IIII)Lrtx/kimiko/utils/render/render2d/blur/BlurFramebuffer$Region;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "I", "getX", "getY", "getWidth", "getHeight", "rtx.kimiko:kimiko"})
    private static final class Region {
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        public Region(int x, int y, int width, int height) {
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
        public final Region copy(int x, int y, int width, int height) {
            return new Region(x, y, width, height);
        }

        public static /* synthetic */ Region copy$default(Region region, int n, int n2, int n3, int n4, int n5, Object object) {
            if ((n5 & 1) != 0) {
                n = region.x;
            }
            if ((n5 & 2) != 0) {
                n2 = region.y;
            }
            if ((n5 & 4) != 0) {
                n3 = region.width;
            }
            if ((n5 & 8) != 0) {
                n4 = region.height;
            }
            return region.copy(n, n2, n3, n4);
        }

        @NotNull
        public String toString() {
            return "Region(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ")";
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
            if (!(other instanceof Region)) {
                return false;
            }
            Region region = (Region)other;
            if (this.x != region.x) {
                return false;
            }
            if (this.y != region.y) {
                return false;
            }
            if (this.width != region.width) {
                return false;
            }
            return this.height == region.height;
        }
    }
}

