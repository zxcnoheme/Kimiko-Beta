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
 *  com.mojang.blaze3d.systems.GpuDevice
 *  com.mojang.blaze3d.systems.RenderPass
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.GpuTexture
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.textures.TextureFormat
 *  com.mojang.blaze3d.vertex.VertexFormat
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  com.mojang.blaze3d.vertex.VertexFormatElement
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gui.render.state.ColoredQuadGuiElementRenderState
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gl.SimpleFramebuffer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fc
 *  org.joml.Matrix4f
 *  org.lwjgl.system.MemoryStack
 */
package rtx.kimiko.utils.render.modules.post.guilayerblur;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.platform.DestFactor;
import com.mojang.blaze3d.platform.SourceFactor;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gui.render.state.ColoredQuadGuiElementRenderState;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gl.SimpleFramebuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.modules.impl.Utils.guishare.RemoteGuiWorld;
import rtx.kimiko.api.ui.window.GuiShatterAnimation;
import rtx.kimiko.api.ui.window.WorldGuiCloseAnimation;
import rtx.kimiko.utils.render.modules.post.guilayerblur.GuiCapture;
import rtx.kimiko.utils.render.others.RenderSampler;
import rtx.kimiko.utils.render.others.profiler.RenderProfiler;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00b6\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J#\u0010\t\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u0013\u0010\u0011\u001a\u00020\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u0011\u0010\u0003J\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0015\u001a\u0004\u0018\u00010\u0012H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0014J\u0017\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0003J\u000f\u0010\u001b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u0003J\u000f\u0010\u001c\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u0003J\u0015\u0010\u001d\u001a\u0004\u0018\u00010\u0016H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u001f\u0010 J\u001b\u0010\"\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\"\u0010#J\u001d\u0010&\u001a\u00020\u00072\b\u0010%\u001a\u0004\u0018\u00010$H\u0007b\u0002\b\b\u00a2\u0006\u0004\b&\u0010'J\u001d\u0010*\u001a\u00020\u00042\b\u0010)\u001a\u0004\u0018\u00010(H\u0007b\u0002\b\b\u00a2\u0006\u0004\b*\u0010+J\u001d\u0010,\u001a\u00020\u00042\b\u0010)\u001a\u0004\u0018\u00010(H\u0007b\u0002\b\b\u00a2\u0006\u0004\b,\u0010+J\u001d\u0010-\u001a\u00020\u00072\b\u0010%\u001a\u0004\u0018\u00010$H\u0007b\u0002\b\b\u00a2\u0006\u0004\b-\u0010'J\u001d\u0010.\u001a\u00020\u00072\b\u0010%\u001a\u0004\u0018\u00010$H\u0007b\u0002\b\b\u00a2\u0006\u0004\b.\u0010'J\u001d\u0010/\u001a\u00020\u00042\b\u0010)\u001a\u0004\u0018\u00010(H\u0007b\u0002\b\b\u00a2\u0006\u0004\b/\u0010+J\u001d\u00100\u001a\u00020\u00042\b\u0010)\u001a\u0004\u0018\u00010(H\u0007b\u0002\b\b\u00a2\u0006\u0004\b0\u0010+J\u0015\u00101\u001a\u0004\u0018\u00010\u0016H\u0007b\u0002\b\b\u00a2\u0006\u0004\b1\u0010\u001eJ\u0013\u00102\u001a\u00020\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b2\u0010\u0003J\u001d\u00103\u001a\u00020\u00072\b\u0010%\u001a\u0004\u0018\u00010$H\u0007b\u0002\b\b\u00a2\u0006\u0004\b3\u0010'J\u001d\u00104\u001a\u00020\u00072\b\u0010%\u001a\u0004\u0018\u00010$H\u0007b\u0002\b\b\u00a2\u0006\u0004\b4\u0010'J\u0013\u00105\u001a\u00020\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b5\u0010\u0003J'\u00109\u001a\u00020\u00072\u0006\u00107\u001a\u0002062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u00108\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b9\u0010:J1\u0010?\u001a\u00020\u00042\u0006\u00107\u001a\u0002062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010<\u001a\u00020;2\b\u0010>\u001a\u0004\u0018\u00010=H\u0002\u00a2\u0006\u0004\b?\u0010@J\u0017\u0010C\u001a\u00020A2\u0006\u0010B\u001a\u00020AH\u0002\u00a2\u0006\u0004\bC\u0010DJ\u001d\u0010E\u001a\u00020\u00042\b\u0010)\u001a\u0004\u0018\u00010(H\u0007b\u0002\b\b\u00a2\u0006\u0004\bE\u0010+J\u001d\u0010F\u001a\u00020\u00072\b\u0010%\u001a\u0004\u0018\u00010$H\u0007b\u0002\b\b\u00a2\u0006\u0004\bF\u0010'J\u001d\u0010G\u001a\u00020\u00042\b\u0010)\u001a\u0004\u0018\u00010(H\u0007b\u0002\b\b\u00a2\u0006\u0004\bG\u0010+J\u0015\u0010H\u001a\u0004\u0018\u00010\u0016H\u0007b\u0002\b\b\u00a2\u0006\u0004\bH\u0010\u001eJ\u0017\u0010I\u001a\u00020\u00072\u0006\u00107\u001a\u000206H\u0002\u00a2\u0006\u0004\bI\u0010JJ\u0013\u0010K\u001a\u00020\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\bK\u0010 J\u0013\u0010L\u001a\u00020\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\bL\u0010 J\u0019\u0010N\u001a\u0004\u0018\u00010\u00122\u0006\u0010M\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\bN\u0010OJ\u001f\u0010P\u001a\u0004\u0018\u00010\u00162\b\u0010M\u001a\u0004\u0018\u00010\u0016H\u0007b\u0002\b\b\u00a2\u0006\u0004\bP\u0010QJ?\u0010U\u001a\u00020\u00072\u0006\u00107\u001a\u0002062\f\u0010S\u001a\b\u0012\u0004\u0012\u00020;0R2\b\u0010T\u001a\u0004\u0018\u00010=2\u0006\u0010\f\u001a\u00020A2\u0006\u0010\r\u001a\u00020AH\u0002\u00a2\u0006\u0004\bU\u0010VJ#\u0010Y\u001a\u00020\u00072\u0006\u0010W\u001a\u00020A2\u0006\u0010X\u001a\u00020AH\u0007b\u0002\b\b\u00a2\u0006\u0004\bY\u0010ZJ\u0013\u0010[\u001a\u00020\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b[\u0010\u0003J9\u0010a\u001a\u00020\u00072\u0006\u00107\u001a\u0002062\b\u0010\\\u001a\u0004\u0018\u00010\u00122\u0006\u0010^\u001a\u00020]2\u0006\u0010_\u001a\u00020A2\u0006\u0010`\u001a\u00020AH\u0002\u00a2\u0006\u0004\ba\u0010bJA\u0010f\u001a\u00020\u00042\u0006\u00107\u001a\u0002062\b\u0010\\\u001a\u0004\u0018\u00010\u00122\u0006\u0010c\u001a\u00020A2\u0006\u0010d\u001a\u00020A2\u0006\u0010W\u001a\u00020A2\u0006\u0010e\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bf\u0010gJ'\u0010j\u001a\u00020\u000b2\u0006\u0010i\u001a\u00020h2\u0006\u0010W\u001a\u00020A2\u0006\u0010d\u001a\u00020AH\u0002\u00a2\u0006\u0004\bj\u0010kJ'\u0010m\u001a\u00020\u00072\u0006\u00107\u001a\u0002062\u0006\u0010l\u001a\u00020A2\u0006\u0010d\u001a\u00020AH\u0002\u00a2\u0006\u0004\bm\u0010nJ9\u0010p\u001a\u00020\u00072\u0006\u00107\u001a\u0002062\b\u0010o\u001a\u0004\u0018\u00010=2\u0006\u0010\f\u001a\u00020A2\u0006\u0010\r\u001a\u00020A2\u0006\u0010e\u001a\u00020AH\u0002\u00a2\u0006\u0004\bp\u0010qJY\u0010p\u001a\u00020\u00072\u0006\u00107\u001a\u0002062\b\u0010o\u001a\u0004\u0018\u00010=2\u0006\u0010\f\u001a\u00020A2\u0006\u0010\r\u001a\u00020A2\u0006\u0010e\u001a\u00020A2\u0006\u0010r\u001a\u00020A2\u0006\u0010s\u001a\u00020A2\u0006\u0010t\u001a\u00020A2\u0006\u0010u\u001a\u00020AH\u0002\u00a2\u0006\u0004\bp\u0010vJ\u000f\u0010w\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bw\u0010 J\u001f\u0010x\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\bx\u0010\u000fJ\u001f\u0010y\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\by\u0010zJ\u000f\u0010{\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b{\u0010\u0003J\u001b\u0010|\u001a\u0004\u0018\u00010]2\b\u0010^\u001a\u0004\u0018\u00010]H\u0002\u00a2\u0006\u0004\b|\u0010}J\u001a\u0010\u0080\u0001\u001a\u00020\u00072\u0006\u0010\u007f\u001a\u00020~H\u0002\u00a2\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001J\u001c\u0010\u0084\u0001\u001a\u00020(2\b\u0010\u0083\u0001\u001a\u00030\u0082\u0001H\u0002\u00a2\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001J\u001b\u0010\u0087\u0001\u001a\u00030\u0086\u00012\u0006\u0010)\u001a\u00020(H\u0002\u00a2\u0006\u0006\b\u0087\u0001\u0010\u0088\u0001J&\u0010\u008a\u0001\u001a\u00020\u00072\b\u0010%\u001a\u0004\u0018\u00010$2\b\u0010\u0089\u0001\u001a\u00030\u0086\u0001H\u0002\u00a2\u0006\u0006\b\u008a\u0001\u0010\u008b\u0001J\u001d\u0010\u008e\u0001\u001a\u00030\u008d\u00012\b\u0010\u008c\u0001\u001a\u00030\u0082\u0001H\u0002\u00a2\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0017\u0010\u0090\u0001\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0090\u0001\u0010\u0091\u0001R\u0017\u0010\u0092\u0001\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0092\u0001\u0010\u0091\u0001R\u0017\u0010\u0093\u0001\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0093\u0001\u0010\u0091\u0001R\u0017\u0010\u0094\u0001\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0094\u0001\u0010\u0091\u0001R\u0017\u0010\u0095\u0001\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0095\u0001\u0010\u0091\u0001R\u0017\u0010\u0096\u0001\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0096\u0001\u0010\u0091\u0001R\u0017\u0010\u0097\u0001\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0097\u0001\u0010\u0091\u0001R\u0017\u0010\u0098\u0001\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0098\u0001\u0010\u0091\u0001R\u0017\u0010\u0099\u0001\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0099\u0001\u0010\u0091\u0001R\u0017\u0010\u009a\u0001\u001a\u00020A8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u009b\u0001R\u0018\u0010\u009c\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u009d\u0001R\u0018\u0010\u009e\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009e\u0001\u0010\u009d\u0001R\u0018\u0010\u009f\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009f\u0001\u0010\u009d\u0001R\u0018\u0010\u00a0\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a0\u0001\u0010\u009d\u0001R\u0018\u0010\u00a1\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a1\u0001\u0010\u009d\u0001R\u0018\u0010\u00a2\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a2\u0001\u0010\u009d\u0001R\u0018\u0010\u00a3\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a3\u0001\u0010\u009d\u0001R\u0018\u0010\u00a4\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a4\u0001\u0010\u009d\u0001R\u0018\u0010\u00a5\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a5\u0001\u0010\u009d\u0001R\u0018\u0010\u00a6\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a6\u0001\u0010\u009d\u0001R\u0018\u0010\u00a7\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a7\u0001\u0010\u009d\u0001R\u0018\u0010\u00a8\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a8\u0001\u0010\u009d\u0001R\u0018\u0010\u00a9\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a9\u0001\u0010\u009d\u0001R\u0018\u0010«\u0001\u001a\u00030\u00aa\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b«\u0001\u0010\u00ac\u0001R\u0018\u0010\u00ad\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00ad\u0001\u0010\u009d\u0001R\u0018\u0010\u00ae\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00ae\u0001\u0010\u009d\u0001R\u0018\u0010\u00af\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00af\u0001\u0010\u009d\u0001R\u0018\u0010\u00b0\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b0\u0001\u0010\u009d\u0001R\u0018\u0010\u00b1\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b1\u0001\u0010\u009d\u0001R\u0018\u0010\u00b2\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b2\u0001\u0010\u009d\u0001R\u0017\u0010\u00b3\u0001\u001a\u00020(8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b3\u0001\u0010\u00b4\u0001R\u0018\u0010\u00b5\u0001\u001a\u00030\u0086\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b5\u0001\u0010\u00b6\u0001R\u0017\u0010\u00b7\u0001\u001a\u00020(8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b7\u0001\u0010\u00b4\u0001R\u0018\u0010\u00b8\u0001\u001a\u00030\u0086\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b8\u0001\u0010\u00b6\u0001R\u0017\u0010\u00b9\u0001\u001a\u00020(8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b9\u0001\u0010\u00b4\u0001R\u0018\u0010\u00ba\u0001\u001a\u00030\u0086\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00ba\u0001\u0010\u00b6\u0001R\u0017\u0010»\u0001\u001a\u00020(8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b»\u0001\u0010\u00b4\u0001R\u0018\u0010\u00bc\u0001\u001a\u00030\u0086\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00bc\u0001\u0010\u00b6\u0001R\u0017\u0010\u00bd\u0001\u001a\u00020(8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00bd\u0001\u0010\u00b4\u0001R\u0018\u0010\u00be\u0001\u001a\u00030\u0086\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00be\u0001\u0010\u00b6\u0001R\u0017\u0010\u00bf\u0001\u001a\u00020(8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00bf\u0001\u0010\u00b4\u0001R\u0018\u0010\u00c0\u0001\u001a\u00030\u0086\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00c0\u0001\u0010\u00b6\u0001R\u001b\u0010\u00c1\u0001\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c1\u0001\u0010\u00b4\u0001R\u001b\u0010\u00c2\u0001\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c2\u0001\u0010\u00b4\u0001R\u001b\u0010\u00c3\u0001\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c3\u0001\u0010\u00b4\u0001R\u001b\u0010\u00c4\u0001\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c4\u0001\u0010\u00b4\u0001R\u001b\u0010\u00c5\u0001\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c5\u0001\u0010\u00b4\u0001R\u001b\u0010\u00c6\u0001\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c6\u0001\u0010\u00b4\u0001R\u001b\u0010\u00c7\u0001\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c7\u0001\u0010\u00b4\u0001R\u001b\u0010\u00c8\u0001\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c8\u0001\u0010\u00b4\u0001R\u001b\u0010\u00c9\u0001\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c9\u0001\u0010\u00b4\u0001R\u001c\u0010\u00cb\u0001\u001a\u0005\u0018\u00010\u00ca\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00cb\u0001\u0010\u00cc\u0001R\u001c\u0010\u00cd\u0001\u001a\u0005\u0018\u00010\u00ca\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00cd\u0001\u0010\u00cc\u0001R\u001c\u0010\u00ce\u0001\u001a\u0005\u0018\u00010\u00ca\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ce\u0001\u0010\u00cc\u0001R\u001c\u0010\u00cf\u0001\u001a\u0005\u0018\u00010\u00ca\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00cf\u0001\u0010\u00cc\u0001R\u001c\u0010\u00d0\u0001\u001a\u0005\u0018\u00010\u00ca\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00d0\u0001\u0010\u00cc\u0001R\u001c\u0010\u00d1\u0001\u001a\u0005\u0018\u00010\u00ca\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00d1\u0001\u0010\u00cc\u0001R\u001b\u0010\u00d2\u0001\u001a\u0004\u0018\u00010h8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00d2\u0001\u0010\u00d3\u0001R\u0017\u0010\u00d4\u0001\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00d4\u0001\u0010\u00d5\u0001R\u001b\u0010\u00d6\u0001\u001a\u0004\u0018\u00010]8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00d6\u0001\u0010\u00d7\u0001R\u001b\u0010\u00d8\u0001\u001a\u0004\u0018\u00010]8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00d8\u0001\u0010\u00d7\u0001R\u001b\u0010\u00d9\u0001\u001a\u0004\u0018\u00010]8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00d9\u0001\u0010\u00d7\u0001R\u0019\u0010\u00da\u0001\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00da\u0001\u0010\u0091\u0001R\u0019\u0010\u00db\u0001\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00db\u0001\u0010\u0091\u0001R\u001b\u0010\u00dc\u0001\u001a\u0004\u0018\u00010]8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00dc\u0001\u0010\u00d7\u0001R\u0019\u0010\u00dd\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00dd\u0001\u0010\u00de\u0001R\u0019\u0010\u00df\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00df\u0001\u0010\u00de\u0001R\u0019\u0010\u00e0\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00e0\u0001\u0010\u00de\u0001R\u001b\u0010\u00e1\u0001\u001a\u0004\u0018\u00010]8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00e1\u0001\u0010\u00d7\u0001R\u0019\u0010\u00e2\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00e2\u0001\u0010\u00de\u0001R\u0019\u0010\u00e3\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00e3\u0001\u0010\u00de\u0001R\u001c\u0010\u00e5\u0001\u001a\u0005\u0018\u00010\u00e4\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00e5\u0001\u0010\u00e6\u0001R\u001b\u0010\u00e7\u0001\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00e7\u0001\u0010\u00e8\u0001R\u0019\u0010\u00e9\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00e9\u0001\u0010\u00de\u0001R\u001c\u0010\u00ea\u0001\u001a\u0005\u0018\u00010\u00e4\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ea\u0001\u0010\u00e6\u0001R\u001b\u0010\u00eb\u0001\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00eb\u0001\u0010\u00e8\u0001R\u0019\u0010\u00ec\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ec\u0001\u0010\u00de\u0001R\u001c\u0010\u00ed\u0001\u001a\u0005\u0018\u00010\u00e4\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ed\u0001\u0010\u00e6\u0001R\u001b\u0010\u00ee\u0001\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ee\u0001\u0010\u00e8\u0001R\u0019\u0010\u00ef\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ef\u0001\u0010\u00de\u0001\u00a8\u0006\u00f0\u0001"}, d2={"Lrtx/kimiko/utils/render/modules/post/guilayerblur/GuiLayerBlurRenderer;", "", "<init>", "()V", "", "localCapture", "remoteCapture", "", "Lkotlin/jvm/JvmStatic;", "beginCapture", "(ZZ)V", "", "width", "height", "ensureRemoteFbo", "(II)Z", "snapshotWorldDepth", "snapshotHandDepth", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "occlusionDepthView", "()Lcom/mojang/blaze3d/textures/GpuTextureView;", "occlusionHandView", "Lnet/minecraft/Framebuffer;", "main", "capturePreCompositeColor", "(Lnet/minecraft/Framebuffer;)V", "closePreCompositeColor", "closeHandDepthCopy", "closeWorldDepthCopy", "remoteCaptureTarget", "()Lnet/minecraft/Framebuffer;", "isRemoteRouting", "()Z", "routing", "setRemoteRouting", "(Z)V", "Lnet/minecraft/DrawContext;", "graphics", "markRemoteBegin", "(Lnet/minecraft/DrawContext;)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "isRemoteBegin", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Z", "isRemoteEnd", "markRemoteCardBegin", "markRemoteCardEnd", "isRemoteCardBegin", "isRemoteCardEnd", "captureTarget", "markPanelRange", "markPanelEnd", "markPanelEndRemote", "compositeRemotePanels", "Lcom/mojang/blaze3d/systems/CommandEncoder;", "encoder", "occlude", "drawRemoteQuad", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lnet/minecraft/Framebuffer;Z)V", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$PanelQuad;", "quad", "Lorg/joml/Matrix4f;", "liveMatrix", "drawRemoteShards", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lnet/minecraft/Framebuffer;Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$PanelQuad;Lorg/joml/Matrix4f;)Z", "", "value", "clamp01", "(F)F", "isPanelBoundary", "markPopupBoundary", "isPopupBoundary", "worldSnapshotWithPanels", "blitPanelsIntoPopupSlots", "(Lcom/mojang/blaze3d/systems/CommandEncoder;)V", "captureActiveThisFrame", "available", "mainTarget", "worldWarpSourceView", "(Lnet/minecraft/Framebuffer;)Lcom/mojang/blaze3d/textures/GpuTextureView;", "worldBackdropSource", "(Lnet/minecraft/Framebuffer;)Lnet/minecraft/Framebuffer;", "", "remoteQuads", "localMatrix", "writeWorldWarpUniform", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Ljava/util/List;Lorg/joml/Matrix4f;FF)V", "scale", "blurRadius", "composite", "(FF)V", "shutdown", "source", "Lnet/minecraft/SimpleFramebuffer;", "target", "stepX", "stepY", "gaussianPass", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lcom/mojang/blaze3d/textures/GpuTextureView;Lnet/minecraft/SimpleFramebuffer;FF)V", "progress", "alpha", "worldMode", "drawShards", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lcom/mojang/blaze3d/textures/GpuTextureView;FFFZ)Z", "Ljava/nio/ByteBuffer;", "vertexData", "appendKeepRegion", "(Ljava/nio/ByteBuffer;FF)I", "invScale", "writeCompositeUniform", "(Lcom/mojang/blaze3d/systems/CommandEncoder;FF)V", "matrix", "writeWorldQuadUniform", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lorg/joml/Matrix4f;FFF)V", "u0", "vTop", "u1", "vBottom", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lorg/joml/Matrix4f;FFFFFFF)V", "ensurePipelines", "ensureTargets", "ensureSceneSnapshotTarget", "(II)V", "closeTargets", "destroy", "(Lnet/minecraft/SimpleFramebuffer;)Lnet/minecraft/SimpleFramebuffer;", "", "throwable", "disableAfterError", "(Ljava/lang/Throwable;)V", "", "name", "markerPipeline", "(Ljava/lang/String;)Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lnet/minecraft/ColoredQuadGuiElementRenderState;", "markerState", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Lnet/minecraft/ColoredQuadGuiElementRenderState;", "marker", "submitMarker", "(Lnet/minecraft/DrawContext;Lnet/minecraft/ColoredQuadGuiElementRenderState;)V", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "BLUR_UNIFORM_BYTES", "I", "COMPOSITE_UNIFORM_BYTES", "SLOT_BLIT_UNIFORM_BYTES", "WORLD_QUAD_UNIFORM_BYTES", "WORLD_WARP_UNIFORM_BYTES", "WORLD_WARP_MAX_SLOTS", "WORLD_WARP_MATS_OFFSET", "WORLD_WARP_RECT_OFFSET", "WORLD_WARP_LOCAL_OFFSET", "TAPS_HALF", "F", "BLUR_PIPELINE_ID", "Lnet/minecraft/Identifier;", "COMPOSITE_PIPELINE_ID", "WORLD_COMPOSITE_PIPELINE_ID", "WORLD_BACKDROP_PIPELINE_ID", "WORLD_SLOTS_PIPELINE_ID", "SLOT_BLIT_PIPELINE_ID", "SLOT_BLIT_SHADER", "FULLSCREEN", "SHARD_PIPELINE_ID", "SHARD_SHADER", "SHARD_OCCLUDED_PIPELINE_ID", "SHARD_OCCLUDED_SHADER", "WORLD_QUAD_SHADER", "Lcom/mojang/blaze3d/vertex/VertexFormat;", "SHARD_FORMAT", "Lcom/mojang/blaze3d/vertex/VertexFormat;", "WORLD_BACKDROP_SHADER", "WORLD_SLOTS_SHADER", "GAUSSIAN_SHADER", "COMPOSITE_SHADER", "WORLD_OCCLUDED_SHADER", "WORLD_OCCLUDED_PIPELINE_ID", "PANEL_BOUNDARY_PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "PANEL_BOUNDARY", "Lnet/minecraft/ColoredQuadGuiElementRenderState;", "POPUP_BOUNDARY_PIPELINE", "POPUP_BOUNDARY", "REMOTE_BEGIN_PIPELINE", "REMOTE_BEGIN", "REMOTE_END_PIPELINE", "REMOTE_END", "REMOTE_CARD_BEGIN_PIPELINE", "REMOTE_CARD_BEGIN", "REMOTE_CARD_END_PIPELINE", "REMOTE_CARD_END", "blurPipeline", "compositePipeline", "worldCompositePipeline", "worldOccludedPipeline", "worldBackdropPipeline", "shardPipeline", "shardOccludedPipeline", "worldSlotsPipeline", "slotBlitPipeline", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "blurUniform", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "compositeUniform", "worldQuadUniform", "worldSlotsUniform", "slotBlitUniform", "shardVertexBuffer", "shardVertexData", "Ljava/nio/ByteBuffer;", "IDENTITY_MATRIX", "Lorg/joml/Matrix4f;", "guiFbo", "Lnet/minecraft/SimpleFramebuffer;", "tempH", "tempV", "texWidth", "texHeight", "sceneSnapshot", "captureActive", "Z", "panelRangeActive", "disabledAfterError", "remoteFbo", "remoteCaptureActive", "remoteRouting", "Lcom/mojang/blaze3d/textures/GpuTexture;", "worldDepthCopy", "Lcom/mojang/blaze3d/textures/GpuTexture;", "worldDepthCopyView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "worldDepthCopyReady", "handDepthCopy", "handDepthCopyView", "handDepthCopyReady", "preCompositeColor", "preCompositeColorView", "preCompositeColorReady", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nGuiLayerBlurRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GuiLayerBlurRenderer.kt\nrtx/kimiko/utils/render/modules/post/guilayerblur/GuiLayerBlurRenderer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1306:1\n1#2:1307\n*E\n"})
public final class GuiLayerBlurRenderer {
    @NotNull
    public static final GuiLayerBlurRenderer INSTANCE = new GuiLayerBlurRenderer();
    private static final int BLUR_UNIFORM_BYTES = 16;
    private static final int COMPOSITE_UNIFORM_BYTES = 16;
    private static final int SLOT_BLIT_UNIFORM_BYTES = 64;
    private static final int WORLD_QUAD_UNIFORM_BYTES = 96;
    private static final int WORLD_WARP_UNIFORM_BYTES = 512;
    private static final int WORLD_WARP_MAX_SLOTS = 5;
    private static final int WORLD_WARP_MATS_OFFSET = 16;
    private static final int WORLD_WARP_RECT_OFFSET = 336;
    private static final int WORLD_WARP_LOCAL_OFFSET = 416;
    private static final float TAPS_HALF = 16.0f;
    @NotNull
    private static final Identifier BLUR_PIPELINE_ID = INSTANCE.id("pipeline/post/guilayerblur/gaussian");
    @NotNull
    private static final Identifier COMPOSITE_PIPELINE_ID = INSTANCE.id("pipeline/post/guilayerblur/composite");
    @NotNull
    private static final Identifier WORLD_COMPOSITE_PIPELINE_ID = INSTANCE.id("pipeline/post/guilayerblur/composite_world");
    @NotNull
    private static final Identifier WORLD_BACKDROP_PIPELINE_ID = INSTANCE.id("pipeline/post/guilayerblur/world_backdrop");
    @NotNull
    private static final Identifier WORLD_SLOTS_PIPELINE_ID = INSTANCE.id("pipeline/post/guilayerblur/world_backdrop_slots");
    @NotNull
    private static final Identifier SLOT_BLIT_PIPELINE_ID = INSTANCE.id("pipeline/post/guilayerblur/slot_blit");
    @NotNull
    private static final Identifier SLOT_BLIT_SHADER = INSTANCE.id("post/guilayerblur/slot_blit");
    @NotNull
    private static final Identifier FULLSCREEN = INSTANCE.id("post/guilayerblur/fullscreen");
    @NotNull
    private static final Identifier SHARD_PIPELINE_ID = INSTANCE.id("pipeline/post/guilayerblur/shard");
    @NotNull
    private static final Identifier SHARD_SHADER = INSTANCE.id("post/guilayerblur/shard");
    @NotNull
    private static final Identifier SHARD_OCCLUDED_PIPELINE_ID = INSTANCE.id("pipeline/post/guilayerblur/shard_occluded");
    @NotNull
    private static final Identifier SHARD_OCCLUDED_SHADER = INSTANCE.id("post/guilayerblur/shard_occluded");
    @NotNull
    private static final Identifier WORLD_QUAD_SHADER = INSTANCE.id("post/guilayerblur/world_quad");
    @NotNull
    private static final VertexFormat SHARD_FORMAT;
    @NotNull
    private static final Identifier WORLD_BACKDROP_SHADER;
    @NotNull
    private static final Identifier WORLD_SLOTS_SHADER;
    @NotNull
    private static final Identifier GAUSSIAN_SHADER;
    @NotNull
    private static final Identifier COMPOSITE_SHADER;
    @NotNull
    private static final Identifier WORLD_OCCLUDED_SHADER;
    @NotNull
    private static final Identifier WORLD_OCCLUDED_PIPELINE_ID;
    @NotNull
    private static final RenderPipeline PANEL_BOUNDARY_PIPELINE;
    @NotNull
    private static final ColoredQuadGuiElementRenderState PANEL_BOUNDARY;
    @NotNull
    private static final RenderPipeline POPUP_BOUNDARY_PIPELINE;
    @NotNull
    private static final ColoredQuadGuiElementRenderState POPUP_BOUNDARY;
    @NotNull
    private static final RenderPipeline REMOTE_BEGIN_PIPELINE;
    @NotNull
    private static final ColoredQuadGuiElementRenderState REMOTE_BEGIN;
    @NotNull
    private static final RenderPipeline REMOTE_END_PIPELINE;
    @NotNull
    private static final ColoredQuadGuiElementRenderState REMOTE_END;
    @NotNull
    private static final RenderPipeline REMOTE_CARD_BEGIN_PIPELINE;
    @NotNull
    private static final ColoredQuadGuiElementRenderState REMOTE_CARD_BEGIN;
    @NotNull
    private static final RenderPipeline REMOTE_CARD_END_PIPELINE;
    @NotNull
    private static final ColoredQuadGuiElementRenderState REMOTE_CARD_END;
    @Nullable
    private static RenderPipeline blurPipeline;
    @Nullable
    private static RenderPipeline compositePipeline;
    @Nullable
    private static RenderPipeline worldCompositePipeline;
    @Nullable
    private static RenderPipeline worldOccludedPipeline;
    @Nullable
    private static RenderPipeline worldBackdropPipeline;
    @Nullable
    private static RenderPipeline shardPipeline;
    @Nullable
    private static RenderPipeline shardOccludedPipeline;
    @Nullable
    private static RenderPipeline worldSlotsPipeline;
    @Nullable
    private static RenderPipeline slotBlitPipeline;
    @Nullable
    private static GpuBuffer blurUniform;
    @Nullable
    private static GpuBuffer compositeUniform;
    @Nullable
    private static GpuBuffer worldQuadUniform;
    @Nullable
    private static GpuBuffer worldSlotsUniform;
    @Nullable
    private static GpuBuffer slotBlitUniform;
    @Nullable
    private static GpuBuffer shardVertexBuffer;
    @Nullable
    private static ByteBuffer shardVertexData;
    @NotNull
    private static final Matrix4f IDENTITY_MATRIX;
    @Nullable
    private static SimpleFramebuffer guiFbo;
    @Nullable
    private static SimpleFramebuffer tempH;
    @Nullable
    private static SimpleFramebuffer tempV;
    private static int texWidth;
    private static int texHeight;
    @Nullable
    private static SimpleFramebuffer sceneSnapshot;
    private static boolean captureActive;
    private static boolean panelRangeActive;
    private static boolean disabledAfterError;
    @Nullable
    private static SimpleFramebuffer remoteFbo;
    private static boolean remoteCaptureActive;
    private static boolean remoteRouting;
    @Nullable
    private static GpuTexture worldDepthCopy;
    @Nullable
    private static GpuTextureView worldDepthCopyView;
    private static boolean worldDepthCopyReady;
    @Nullable
    private static GpuTexture handDepthCopy;
    @Nullable
    private static GpuTextureView handDepthCopyView;
    private static boolean handDepthCopyReady;
    @Nullable
    private static GpuTexture preCompositeColor;
    @Nullable
    private static GpuTextureView preCompositeColorView;
    private static boolean preCompositeColorReady;

    private GuiLayerBlurRenderer() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void beginCapture(boolean localCapture, boolean remoteCapture) {
        block17: {
            captureActive = false;
            panelRangeActive = false;
            remoteCaptureActive = false;
            remoteRouting = false;
            RemoteGuiWorld.prepareCardBlurDraw();
            if (!localCapture && !remoteCapture || disabledAfterError) {
                return;
            }
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            Framebuffer main = mc.getFramebuffer();
            if (main == null || main.getColorAttachmentView() == null || main.textureWidth <= 0 || main.textureHeight <= 0) {
                return;
            }
            try {
                RenderPass it;
                Throwable throwable;
                AutoCloseable autoCloseable;
                if (!INSTANCE.ensurePipelines() || !INSTANCE.ensureTargets(main.textureWidth, main.textureHeight)) {
                    return;
                }
                CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
                Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
                CommandEncoder encoder = commandEncoder;
                if (localCapture) {
                    Supplier<String> supplier = GuiLayerBlurRenderer::beginCapture$lambda$0;
                    SimpleFramebuffer simpleFramebuffer2 = guiFbo;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer2);
                    GpuTextureView gpuTextureView = simpleFramebuffer2.getColorAttachmentView();
                    Intrinsics.checkNotNull((Object)gpuTextureView);
                    OptionalInt optionalInt = OptionalInt.of(0);
                    SimpleFramebuffer simpleFramebuffer3 = guiFbo;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer3);
                    autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, optionalInt, simpleFramebuffer3.getDepthAttachmentView(), OptionalDouble.of(1.0));
                    throwable = null;
                    try {
                        it = (RenderPass)autoCloseable;
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
                    captureActive = true;
                }
                if (!remoteCapture || !INSTANCE.ensureRemoteFbo(main.textureWidth, main.textureHeight)) break block17;
                Supplier<String> supplier = GuiLayerBlurRenderer::beginCapture$lambda$2;
                SimpleFramebuffer simpleFramebuffer4 = remoteFbo;
                Intrinsics.checkNotNull((Object)simpleFramebuffer4);
                GpuTextureView gpuTextureView = simpleFramebuffer4.getColorAttachmentView();
                Intrinsics.checkNotNull((Object)gpuTextureView);
                OptionalInt optionalInt = OptionalInt.of(0);
                SimpleFramebuffer simpleFramebuffer5 = remoteFbo;
                Intrinsics.checkNotNull((Object)simpleFramebuffer5);
                autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, optionalInt, simpleFramebuffer5.getDepthAttachmentView(), OptionalDouble.of(1.0));
                throwable = null;
                try {
                    it = (RenderPass)autoCloseable;
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
                remoteCaptureActive = true;
            }
            catch (Throwable throwable) {
                INSTANCE.disableAfterError(throwable);
            }
        }
    }

    private final boolean ensureRemoteFbo(int width, int height) {
        SimpleFramebuffer created;
        SimpleFramebuffer current = remoteFbo;
        if (current != null && current.textureWidth == width && current.textureHeight == height) {
            return current.getColorAttachmentView() != null;
        }
        if (current != null) {
            current.delete();
            remoteFbo = null;
        }
        remoteFbo = created = new SimpleFramebuffer("kimiko_gui_share_capture", width, height, true);
        return created.getColorAttachmentView() != null;
    }

    @JvmStatic
    public static final void snapshotWorldDepth() {
        if (disabledAfterError || !RemoteGuiWorld.depthSnapshotWanted()) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Framebuffer main = mc.getFramebuffer();
        if (main == null || main.getDepthAttachment() == null || main.textureWidth <= 0 || main.textureHeight <= 0) {
            worldDepthCopyReady = false;
            return;
        }
        try {
            GpuTexture current = worldDepthCopy;
            if (current != null && (current.getWidth(0) != main.textureWidth || current.getHeight(0) != main.textureHeight)) {
                INSTANCE.closeWorldDepthCopy();
            }
            if (worldDepthCopy == null) {
                GpuDevice gpuDevice = RenderSystem.getDevice();
                Intrinsics.checkNotNullExpressionValue((Object)gpuDevice, (String)"getDevice(...)");
                GpuDevice device = gpuDevice;
                GpuTexture gpuTexture = worldDepthCopy = device.createTexture(GuiLayerBlurRenderer::snapshotWorldDepth$lambda$0, 5, TextureFormat.DEPTH32, main.textureWidth, main.textureHeight, 1, 1);
                Intrinsics.checkNotNull((Object)gpuTexture);
                worldDepthCopyView = device.createTextureView(gpuTexture);
            }
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            GpuTexture gpuTexture = main.getDepthAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = worldDepthCopy;
            Intrinsics.checkNotNull((Object)gpuTexture2);
            commandEncoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, main.textureWidth, main.textureHeight);
            worldDepthCopyReady = true;
            handDepthCopyReady = false;
            preCompositeColorReady = false;
        }
        catch (Throwable throwable) {
            INSTANCE.closeWorldDepthCopy();
        }
    }

    @JvmStatic
    public static final void snapshotHandDepth() {
        if (disabledAfterError || !worldDepthCopyReady) {
            handDepthCopyReady = false;
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Framebuffer main = mc.getFramebuffer();
        if (main == null || main.getDepthAttachment() == null || main.textureWidth <= 0 || main.textureHeight <= 0) {
            handDepthCopyReady = false;
            return;
        }
        try {
            GpuTexture current = handDepthCopy;
            if (current != null && (current.getWidth(0) != main.textureWidth || current.getHeight(0) != main.textureHeight)) {
                INSTANCE.closeHandDepthCopy();
            }
            if (handDepthCopy == null) {
                GpuDevice gpuDevice = RenderSystem.getDevice();
                Intrinsics.checkNotNullExpressionValue((Object)gpuDevice, (String)"getDevice(...)");
                GpuDevice device = gpuDevice;
                GpuTexture gpuTexture = handDepthCopy = device.createTexture(GuiLayerBlurRenderer::snapshotHandDepth$lambda$0, 5, TextureFormat.DEPTH32, main.textureWidth, main.textureHeight, 1, 1);
                Intrinsics.checkNotNull((Object)gpuTexture);
                handDepthCopyView = device.createTextureView(gpuTexture);
            }
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            GpuTexture gpuTexture = main.getDepthAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = handDepthCopy;
            Intrinsics.checkNotNull((Object)gpuTexture2);
            commandEncoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, main.textureWidth, main.textureHeight);
            handDepthCopyReady = true;
        }
        catch (Throwable throwable) {
            INSTANCE.closeHandDepthCopy();
        }
    }

    private final GpuTextureView occlusionDepthView() {
        return worldDepthCopyReady && worldDepthCopyView != null ? worldDepthCopyView : null;
    }

    private final GpuTextureView occlusionHandView() {
        if (handDepthCopyReady && handDepthCopyView != null) {
            return handDepthCopyView;
        }
        return this.occlusionDepthView();
    }

    private final void capturePreCompositeColor(Framebuffer main) {
        if (main.getColorAttachment() == null) {
            preCompositeColorReady = false;
            return;
        }
        try {
            GpuTexture current = preCompositeColor;
            if (current != null && (current.getWidth(0) != main.textureWidth || current.getHeight(0) != main.textureHeight)) {
                this.closePreCompositeColor();
            }
            if (preCompositeColor == null) {
                GpuDevice gpuDevice = RenderSystem.getDevice();
                Intrinsics.checkNotNullExpressionValue((Object)gpuDevice, (String)"getDevice(...)");
                GpuDevice device = gpuDevice;
                GpuTexture gpuTexture = preCompositeColor = device.createTexture(GuiLayerBlurRenderer::capturePreCompositeColor$lambda$0, 5, TextureFormat.RGBA8, main.textureWidth, main.textureHeight, 1, 1);
                Intrinsics.checkNotNull((Object)gpuTexture);
                preCompositeColorView = device.createTextureView(gpuTexture);
            }
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            GpuTexture gpuTexture = main.getColorAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = preCompositeColor;
            Intrinsics.checkNotNull((Object)gpuTexture2);
            commandEncoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, main.textureWidth, main.textureHeight);
            preCompositeColorReady = true;
        }
        catch (Throwable throwable) {
            this.closePreCompositeColor();
        }
    }

    private final void closePreCompositeColor() {
        preCompositeColorReady = false;
        GpuTextureView gpuTextureView = preCompositeColorView;
        if (gpuTextureView != null) {
            gpuTextureView.close();
        }
        preCompositeColorView = null;
        GpuTexture gpuTexture = preCompositeColor;
        if (gpuTexture != null) {
            gpuTexture.close();
        }
        preCompositeColor = null;
    }

    private final void closeHandDepthCopy() {
        handDepthCopyReady = false;
        GpuTextureView gpuTextureView = handDepthCopyView;
        if (gpuTextureView != null) {
            gpuTextureView.close();
        }
        handDepthCopyView = null;
        GpuTexture gpuTexture = handDepthCopy;
        if (gpuTexture != null) {
            gpuTexture.close();
        }
        handDepthCopy = null;
    }

    private final void closeWorldDepthCopy() {
        worldDepthCopyReady = false;
        GpuTextureView gpuTextureView = worldDepthCopyView;
        if (gpuTextureView != null) {
            gpuTextureView.close();
        }
        worldDepthCopyView = null;
        GpuTexture gpuTexture = worldDepthCopy;
        if (gpuTexture != null) {
            gpuTexture.close();
        }
        worldDepthCopy = null;
    }

    @JvmStatic
    @Nullable
    public static final Framebuffer remoteCaptureTarget() {
        return remoteCaptureActive ? (Framebuffer)remoteFbo : null;
    }

    @JvmStatic
    public static final boolean isRemoteRouting() {
        return remoteRouting;
    }

    @JvmStatic
    public static final void setRemoteRouting(boolean routing) {
        remoteRouting = routing;
    }

    @JvmStatic
    public static final void markRemoteBegin(@Nullable DrawContext graphics) {
        if (graphics == null) {
            return;
        }
        GuiRenderState state = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
        state.createNewRootLayer();
        state.addSimpleElement((SimpleGuiElementRenderState)REMOTE_BEGIN);
        state.createNewRootLayer();
    }

    @JvmStatic
    public static final boolean isRemoteBegin(@Nullable RenderPipeline pipeline) {
        return pipeline == REMOTE_BEGIN_PIPELINE;
    }

    @JvmStatic
    public static final boolean isRemoteEnd(@Nullable RenderPipeline pipeline) {
        return pipeline == REMOTE_END_PIPELINE;
    }

    @JvmStatic
    public static final void markRemoteCardBegin(@Nullable DrawContext graphics) {
        INSTANCE.submitMarker(graphics, REMOTE_CARD_BEGIN);
    }

    @JvmStatic
    public static final void markRemoteCardEnd(@Nullable DrawContext graphics) {
        INSTANCE.submitMarker(graphics, REMOTE_CARD_END);
    }

    @JvmStatic
    public static final boolean isRemoteCardBegin(@Nullable RenderPipeline pipeline) {
        return pipeline == REMOTE_CARD_BEGIN_PIPELINE;
    }

    @JvmStatic
    public static final boolean isRemoteCardEnd(@Nullable RenderPipeline pipeline) {
        return pipeline == REMOTE_CARD_END_PIPELINE;
    }

    @JvmStatic
    @Nullable
    public static final Framebuffer captureTarget() {
        return captureActive && panelRangeActive ? (Framebuffer)guiFbo : null;
    }

    @JvmStatic
    public static final void markPanelRange() {
        if (captureActive) {
            panelRangeActive = true;
        }
    }

    @JvmStatic
    public static final void markPanelEnd(@Nullable DrawContext graphics) {
        if (graphics == null || !GuiCapture.active() || !GuiCapture.emitPanelBoundary()) {
            return;
        }
        GuiRenderState state = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
        state.createNewRootLayer();
        state.addSimpleElement((SimpleGuiElementRenderState)PANEL_BOUNDARY);
        state.createNewRootLayer();
    }

    @JvmStatic
    public static final void markPanelEndRemote(@Nullable DrawContext graphics) {
        if (graphics == null) {
            return;
        }
        GuiRenderState state = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
        state.createNewRootLayer();
        state.addSimpleElement((SimpleGuiElementRenderState)REMOTE_END);
        state.createNewRootLayer();
    }

    @JvmStatic
    public static final void compositeRemotePanels() {
        List<RemoteGuiWorld.PanelQuad> panelQuads = RemoteGuiWorld.consumeQuads();
        if (panelQuads.isEmpty() || remoteFbo == null || disabledAfterError) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Framebuffer main = mc.getFramebuffer();
        if (main == null || main.getColorAttachmentView() == null) {
            return;
        }
        if (main.textureWidth != texWidth || main.textureHeight != texHeight) {
            return;
        }
        try {
            if (!INSTANCE.ensurePipelines()) {
                return;
            }
            INSTANCE.capturePreCompositeColor(main);
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            boolean occlude = worldDepthCopyReady && worldDepthCopyView != null && worldOccludedPipeline != null;
            for (RemoteGuiWorld.PanelQuad quad : panelQuads) {
                Matrix4f live = RemoteGuiWorld.freshMatrix(quad);
                if (live == null) {
                    live = quad.matrix;
                }
                if (quad.shatter != null && INSTANCE.drawRemoteShards(encoder, main, quad, live)) continue;
                INSTANCE.writeCompositeUniform(encoder, 1.0f, INSTANCE.clamp01(quad.opacity));
                INSTANCE.writeWorldQuadUniform(encoder, live, quad.quadW, quad.quadH, 1.0f, quad.fbX / (float)texWidth, 1.0f - quad.fbY / (float)texHeight, (quad.fbX + quad.fbW) / (float)texWidth, 1.0f - (quad.fbY + quad.fbH) / (float)texHeight);
                INSTANCE.drawRemoteQuad(encoder, main, occlude);
            }
        }
        catch (Throwable throwable) {
            INSTANCE.disableAfterError(throwable);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void drawRemoteQuad(CommandEncoder encoder, Framebuffer main, boolean occlude) {
        if (occlude) {
            Supplier<String> supplier = GuiLayerBlurRenderer::drawRemoteQuad$lambda$0;
            GpuTextureView gpuTextureView = main.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
            Throwable throwable = null;
            try {
                RenderPass pass = (RenderPass)autoCloseable;
                boolean bl = false;
                RenderPipeline renderPipeline = worldOccludedPipeline;
                Intrinsics.checkNotNull((Object)renderPipeline);
                pass.setPipeline(renderPipeline);
                SimpleFramebuffer simpleFramebuffer2 = remoteFbo;
                Intrinsics.checkNotNull((Object)simpleFramebuffer2);
                pass.bindTexture("uGui", simpleFramebuffer2.getColorAttachmentView(), RenderSampler.linear());
                pass.bindTexture("uDepth", worldDepthCopyView, RenderSampler.nearest());
                pass.bindTexture("uHandDepth", INSTANCE.occlusionHandView(), RenderSampler.nearest());
                GpuBuffer gpuBuffer = compositeUniform;
                Intrinsics.checkNotNull((Object)gpuBuffer);
                pass.setUniform("CompositeData", gpuBuffer);
                GpuBuffer gpuBuffer2 = worldQuadUniform;
                Intrinsics.checkNotNull((Object)gpuBuffer2);
                pass.setUniform("WorldQuadData", gpuBuffer2);
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
        Supplier<String> supplier = GuiLayerBlurRenderer::drawRemoteQuad$lambda$2;
        GpuTextureView gpuTextureView = main.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty(), main.getDepthAttachmentView(), OptionalDouble.empty());
        Throwable throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = worldCompositePipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            SimpleFramebuffer simpleFramebuffer3 = remoteFbo;
            Intrinsics.checkNotNull((Object)simpleFramebuffer3);
            pass.bindTexture("uGui", simpleFramebuffer3.getColorAttachmentView(), RenderSampler.linear());
            GpuBuffer gpuBuffer = compositeUniform;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("CompositeData", gpuBuffer);
            GpuBuffer gpuBuffer3 = worldQuadUniform;
            Intrinsics.checkNotNull((Object)gpuBuffer3);
            pass.setUniform("WorldQuadData", gpuBuffer3);
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final boolean drawRemoteShards(CommandEncoder encoder, Framebuffer main, RemoteGuiWorld.PanelQuad quad, Matrix4f liveMatrix) {
        boolean occlude;
        RemoteGuiWorld.Shatter shatter = quad.shatter;
        ByteBuffer vertexData = shardVertexData;
        if (shatter == null || shardVertexBuffer == null || vertexData == null) {
            return false;
        }
        boolean bl = occlude = worldDepthCopyReady && worldDepthCopyView != null && shardOccludedPipeline != null;
        if (!occlude && shardPipeline == null) {
            return false;
        }
        float uvOffsetX = quad.fbX / (float)texWidth + quad.fbW / (float)texWidth * shatter.offsetX;
        float uvOffsetY = 1.0f - quad.fbY / (float)texHeight - quad.fbH / (float)texHeight * (shatter.offsetY + shatter.scaleY);
        float uvScaleX = quad.fbW / (float)texWidth * shatter.scaleX;
        float uvScaleY = quad.fbH / (float)texHeight * shatter.scaleY;
        GuiShatterAnimation.Remap remap = new GuiShatterAnimation.Remap(shatter.offsetX, shatter.offsetY, shatter.scaleX, shatter.scaleY, uvOffsetX, uvOffsetY, uvScaleX, uvScaleY);
        int vertices = shatter.state.buildGeometry(vertexData, shatter.progress, shatter.alpha, 1.0f, true, remap);
        if (vertices <= 0 || vertexData.remaining() <= 0) {
            return false;
        }
        Matrix4f matrix4f = liveMatrix;
        if (matrix4f == null) {
            matrix4f = quad.matrix;
        }
        this.writeWorldQuadUniform(encoder, matrix4f, quad.quadW, quad.quadH, 1.0f);
        GpuBuffer gpuBuffer = shardVertexBuffer;
        Intrinsics.checkNotNull((Object)gpuBuffer);
        encoder.writeToBuffer(gpuBuffer.slice(0L, (long)vertexData.remaining()), vertexData);
        Supplier<String> supplier = GuiLayerBlurRenderer::drawRemoteShards$lambda$0;
        GpuTextureView gpuTextureView = main.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
        Throwable throwable = null;
        try {
            RenderPipeline renderPipeline;
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl2 = false;
            if (occlude) {
                RenderPipeline renderPipeline2 = shardOccludedPipeline;
                renderPipeline = renderPipeline2;
                Intrinsics.checkNotNull((Object)renderPipeline2);
            } else {
                RenderPipeline renderPipeline3 = shardPipeline;
                renderPipeline = renderPipeline3;
                Intrinsics.checkNotNull((Object)renderPipeline3);
            }
            pass.setPipeline(renderPipeline);
            GpuBuffer gpuBuffer2 = shardVertexBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer2);
            pass.setVertexBuffer(0, gpuBuffer2);
            SimpleFramebuffer simpleFramebuffer2 = remoteFbo;
            Intrinsics.checkNotNull((Object)simpleFramebuffer2);
            pass.bindTexture("uGui", simpleFramebuffer2.getColorAttachmentView(), RenderSampler.linear());
            if (occlude) {
                pass.bindTexture("uDepth", worldDepthCopyView, RenderSampler.nearest());
                pass.bindTexture("uHandDepth", INSTANCE.occlusionHandView(), RenderSampler.nearest());
            }
            GpuBuffer gpuBuffer3 = worldQuadUniform;
            Intrinsics.checkNotNull((Object)gpuBuffer3);
            pass.setUniform("WorldQuadData", gpuBuffer3);
            pass.draw(0, vertices);
            Unit unit = Unit.INSTANCE;
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        return true;
    }

    private final float clamp01(float value) {
        return Math.max(0.0f, Math.min(1.0f, value));
    }

    @JvmStatic
    public static final boolean isPanelBoundary(@Nullable RenderPipeline pipeline) {
        return pipeline == PANEL_BOUNDARY_PIPELINE;
    }

    @JvmStatic
    public static final void markPopupBoundary(@Nullable DrawContext graphics) {
        if (graphics == null) {
            return;
        }
        GuiRenderState state = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
        state.createNewRootLayer();
        state.addSimpleElement((SimpleGuiElementRenderState)POPUP_BOUNDARY);
        state.createNewRootLayer();
    }

    @JvmStatic
    public static final boolean isPopupBoundary(@Nullable RenderPipeline pipeline) {
        return pipeline == POPUP_BOUNDARY_PIPELINE;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    @Nullable
    public static final Framebuffer worldSnapshotWithPanels() {
        Framebuffer framebuffer2;
        SimpleFramebuffer snapshot;
        block17: {
            block16: {
                if (disabledAfterError || remoteFbo == null) break block16;
                SimpleFramebuffer simpleFramebuffer2 = remoteFbo;
                Intrinsics.checkNotNull((Object)simpleFramebuffer2);
                if (simpleFramebuffer2.getColorAttachmentView() != null) break block17;
            }
            return null;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Framebuffer main = mc.getFramebuffer();
        if (main == null || main.getColorAttachmentView() == null) {
            return null;
        }
        Framebuffer base = GuiLayerBlurRenderer.worldBackdropSource(main);
        if (base != (snapshot = sceneSnapshot) || snapshot == null || snapshot.getColorAttachmentView() == null) {
            return base;
        }
        try {
            SimpleFramebuffer simpleFramebuffer3;
            if (!INSTANCE.ensurePipelines()) {
                simpleFramebuffer3 = null;
            } else {
                CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
                Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
                CommandEncoder encoder = commandEncoder;
                INSTANCE.writeCompositeUniform(encoder, 1.0f, 1.0f);
                Supplier<String> supplier = GuiLayerBlurRenderer::worldSnapshotWithPanels$lambda$0;
                GpuTextureView gpuTextureView = snapshot.getColorAttachmentView();
                Intrinsics.checkNotNull((Object)gpuTextureView);
                AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
                Throwable throwable = null;
                try {
                    RenderPass pass = (RenderPass)autoCloseable;
                    boolean bl = false;
                    RenderPipeline renderPipeline = compositePipeline;
                    Intrinsics.checkNotNull((Object)renderPipeline);
                    pass.setPipeline(renderPipeline);
                    SimpleFramebuffer simpleFramebuffer4 = remoteFbo;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer4);
                    pass.bindTexture("uGui", simpleFramebuffer4.getColorAttachmentView(), RenderSampler.linear());
                    GpuBuffer gpuBuffer = compositeUniform;
                    Intrinsics.checkNotNull((Object)gpuBuffer);
                    pass.setUniform("CompositeData", gpuBuffer);
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
                INSTANCE.blitPanelsIntoPopupSlots(encoder);
                simpleFramebuffer3 = snapshot;
            }
            framebuffer2 = (Framebuffer)simpleFramebuffer3;
        }
        catch (Throwable throwable) {
            INSTANCE.disableAfterError(throwable);
            framebuffer2 = null;
        }
        return framebuffer2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void blitPanelsIntoPopupSlots(CommandEncoder encoder) {
        SimpleFramebuffer snapshot = sceneSnapshot;
        if (slotBlitPipeline == null || slotBlitUniform == null || snapshot == null) {
            return;
        }
        List<RemoteGuiWorld.PanelQuad> quads = RemoteGuiWorld.peekQuads();
        if (quads.isEmpty()) {
            return;
        }
        float width = snapshot.textureWidth;
        float height = snapshot.textureHeight;
        for (RemoteGuiWorld.PanelQuad quad : quads) {
            RemoteGuiWorld.SlotBlit blit = quad.blit;
            if (blit == null || quad.fbW < 1.0f || quad.fbH < 1.0f || blit.srcW < 1.0f || blit.srcH < 1.0f) continue;
            AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
            Throwable throwable = null;
            try {
                MemoryStack stack = (MemoryStack)autoCloseable;
                boolean bl = false;
                ByteBuffer data = stack.calloc(64);
                data.putFloat(0, quad.fbX);
                data.putFloat(4, quad.fbY);
                data.putFloat(8, quad.fbW);
                data.putFloat(12, quad.fbH);
                data.putFloat(16, blit.srcX);
                data.putFloat(20, blit.srcY);
                data.putFloat(24, blit.srcW);
                data.putFloat(28, blit.srcH);
                data.putFloat(32, blit.clipX);
                data.putFloat(36, blit.clipY);
                data.putFloat(40, blit.clipW);
                data.putFloat(44, blit.clipH);
                data.putFloat(48, width);
                data.putFloat(52, height);
                data.position(0);
                GpuBuffer gpuBuffer = slotBlitUniform;
                Intrinsics.checkNotNull((Object)gpuBuffer);
                encoder.writeToBuffer(gpuBuffer.slice(0L, 64L), data);
// stack = Unit.INSTANCE;
            }
            catch (Throwable bl) {
                throwable = bl;
                throw bl;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
            }
            Supplier<String> supplier = GuiLayerBlurRenderer::blitPanelsIntoPopupSlots$lambda$1;
            GpuTextureView gpuTextureView = snapshot.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
            throwable = null;
            try {
                RenderPass pass = (RenderPass)autoCloseable;
                boolean bl = false;
                RenderPipeline renderPipeline = slotBlitPipeline;
                Intrinsics.checkNotNull((Object)renderPipeline);
                pass.setPipeline(renderPipeline);
                SimpleFramebuffer simpleFramebuffer2 = remoteFbo;
                Intrinsics.checkNotNull((Object)simpleFramebuffer2);
                pass.bindTexture("uGui", simpleFramebuffer2.getColorAttachmentView(), RenderSampler.linear());
                GpuBuffer gpuBuffer = slotBlitUniform;
                Intrinsics.checkNotNull((Object)gpuBuffer);
                pass.setUniform("SlotBlitData", gpuBuffer);
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
    }

    @JvmStatic
    public static final boolean captureActiveThisFrame() {
        return captureActive && guiFbo != null;
    }

    @JvmStatic
    public static final boolean available() {
        return !disabledAfterError;
    }

    private final GpuTextureView worldWarpSourceView(Framebuffer mainTarget) {
        return preCompositeColorReady && preCompositeColorView != null ? preCompositeColorView : mainTarget.getColorAttachmentView();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    @Nullable
    public static final Framebuffer worldBackdropSource(@Nullable Framebuffer mainTarget) {
        Framebuffer framebuffer2;
        if (mainTarget == null || disabledAfterError || mainTarget.getColorAttachmentView() == null) {
            return mainTarget;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        if (minecraftClient2 == null) {
            return mainTarget;
        }
        MinecraftClient mc = minecraftClient2;
        List remoteQuads = RemoteGuiWorld.captureRequested() ? RemoteGuiWorld.peekQuads() : CollectionsKt.emptyList();
        Matrix4f worldMatrix = null;
        if (WorldGuiCloseAnimation.isActive() && mc.currentScreen == null) {
            worldMatrix = WorldGuiCloseAnimation.compositeMatrix(mainTarget.textureWidth, mainTarget.textureHeight);
        }
        if (remoteQuads.isEmpty() && worldMatrix == null) {
            return mainTarget;
        }
        try {
            Framebuffer framebuffer3;
            if (!INSTANCE.ensurePipelines()) {
                framebuffer3 = mainTarget;
            } else {
                INSTANCE.ensureSceneSnapshotTarget(mainTarget.textureWidth, mainTarget.textureHeight);
                SimpleFramebuffer snapshot = sceneSnapshot;
                if (snapshot == null) {
                    framebuffer3 = mainTarget;
                } else {
                    SimpleFramebuffer simpleFramebuffer2;
                    CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
                    Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
                    CommandEncoder encoder = commandEncoder;
                    if (remoteQuads.isEmpty()) {
                        INSTANCE.writeWorldQuadUniform(encoder, worldMatrix, mainTarget.textureWidth, mainTarget.textureHeight, 1.0f);
                        Supplier<String> supplier = GuiLayerBlurRenderer::worldBackdropSource$lambda$0;
                        GpuTextureView gpuTextureView = snapshot.getColorAttachmentView();
                        Intrinsics.checkNotNull((Object)gpuTextureView);
                        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0));
                        Throwable throwable = null;
                        try {
                            RenderPass pass = (RenderPass)autoCloseable;
                            boolean bl = false;
                            RenderPipeline renderPipeline = worldBackdropPipeline;
                            Intrinsics.checkNotNull((Object)renderPipeline);
                            pass.setPipeline(renderPipeline);
                            pass.bindTexture("uGui", INSTANCE.worldWarpSourceView(mainTarget), RenderSampler.linear());
                            GpuBuffer gpuBuffer = worldQuadUniform;
                            Intrinsics.checkNotNull((Object)gpuBuffer);
                            pass.setUniform("WorldQuadData", gpuBuffer);
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
                        simpleFramebuffer2 = snapshot;
                    } else {
                        INSTANCE.writeWorldWarpUniform(encoder, remoteQuads, worldMatrix, mainTarget.textureWidth, mainTarget.textureHeight);
                        Supplier<String> supplier = GuiLayerBlurRenderer::worldBackdropSource$lambda$2;
                        GpuTextureView gpuTextureView = snapshot.getColorAttachmentView();
                        Intrinsics.checkNotNull((Object)gpuTextureView);
                        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0));
                        Throwable throwable = null;
                        try {
                            RenderPass pass = (RenderPass)autoCloseable;
                            boolean bl = false;
                            RenderPipeline renderPipeline = worldSlotsPipeline;
                            Intrinsics.checkNotNull((Object)renderPipeline);
                            pass.setPipeline(renderPipeline);
                            pass.bindTexture("uGui", INSTANCE.worldWarpSourceView(mainTarget), RenderSampler.linear());
                            GpuBuffer gpuBuffer = worldSlotsUniform;
                            Intrinsics.checkNotNull((Object)gpuBuffer);
                            pass.setUniform("WorldWarpData", gpuBuffer);
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
                        simpleFramebuffer2 = snapshot;
                    }
                    framebuffer3 = (Framebuffer)simpleFramebuffer2;
                }
            }
            framebuffer2 = framebuffer3;
        }
        catch (Throwable throwable) {
            INSTANCE.disableAfterError(throwable);
            framebuffer2 = mainTarget;
        }
        return framebuffer2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void writeWorldWarpUniform(CommandEncoder encoder, List<RemoteGuiWorld.PanelQuad> remoteQuads, Matrix4f localMatrix, float width, float height) {
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(512);
            int count = 0;
            for (RemoteGuiWorld.PanelQuad quad : remoteQuads) {
                if (count >= 4) break;
                quad.matrix.get(16 + count * 64, data);
                int rectOffset = 336 + count * 16;
                data.putFloat(rectOffset, quad.fbX);
                data.putFloat(rectOffset + 4, quad.fbY);
                data.putFloat(rectOffset + 8, quad.fbW);
                data.putFloat(rectOffset + 12, quad.fbH);
                int localOffset = 416 + count * 16;
                data.putFloat(localOffset, quad.quadW / Math.max(quad.fbW, 1.0f));
                data.putFloat(localOffset + 4, quad.quadH / Math.max(quad.fbH, 1.0f));
                ++count;
            }
            if (localMatrix != null && count < 5) {
                localMatrix.get(16 + count * 64, data);
                int rectOffset = 336 + count * 16;
                data.putFloat(rectOffset + 8, width);
                data.putFloat(rectOffset + 12, height);
                int localOffset = 416 + count * 16;
                data.putFloat(localOffset, 1.0f);
                data.putFloat(localOffset + 4, 1.0f);
                ++count;
            }
            data.putFloat(0, count);
            data.putFloat(4, width);
            data.putFloat(8, height);
            data.position(0);
            GpuBuffer gpuBuffer = worldSlotsUniform;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 512L), data);
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
    @JvmStatic
    public static final void composite(float scale, float blurRadius) {
        if (!captureActive || guiFbo == null || disabledAfterError) {
            captureActive = false;
            return;
        }
        captureActive = false;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Framebuffer main = mc.getFramebuffer();
        if (main == null || main.getColorAttachmentView() == null || compositePipeline == null) {
            return;
        }
        int width = main.textureWidth;
        int height = main.textureHeight;
        if (width != texWidth || height != texHeight) {
            return;
        }
        try {
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            float shatterProgress = GuiCapture.shatterProgress();
            boolean shatter = GuiShatterAnimation.isActive() && shatterProgress > 0.0f && tempH != null && tempV != null;
            boolean worldActive = WorldGuiCloseAnimation.isActive();
            Matrix4f shatterMatrix = IDENTITY_MATRIX;
            if (worldActive && (shatterMatrix = WorldGuiCloseAnimation.compositeMatrix(width, height)) == null) {
                return;
            }
            if (shatter) {
                float shardScale = worldActive ? WorldGuiCloseAnimation.screenScale() : scale;
                float shardAlpha = worldActive ? WorldGuiCloseAnimation.alpha() : 1.0f;
                INSTANCE.writeWorldQuadUniform(encoder, shatterMatrix, width, height, worldActive ? 1.0f : 0.0f);
                SimpleFramebuffer simpleFramebuffer2 = guiFbo;
                Intrinsics.checkNotNull((Object)simpleFramebuffer2);
                if (!INSTANCE.drawShards(encoder, simpleFramebuffer2.getColorAttachmentView(), shatterProgress, shardAlpha, shardScale, worldActive)) {
                    return;
                }
                if (blurRadius >= 0.5f) {
                    float step = blurRadius / 16.0f;
                    SimpleFramebuffer simpleFramebuffer3 = tempH;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer3);
                    GpuTextureView gpuTextureView = simpleFramebuffer3.getColorAttachmentView();
                    SimpleFramebuffer simpleFramebuffer4 = tempV;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer4);
                    INSTANCE.gaussianPass(encoder, gpuTextureView, simpleFramebuffer4, step / (float)width, 0.0f);
                    SimpleFramebuffer simpleFramebuffer5 = tempV;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer5);
                    GpuTextureView gpuTextureView2 = simpleFramebuffer5.getColorAttachmentView();
                    SimpleFramebuffer simpleFramebuffer6 = tempH;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer6);
                    INSTANCE.gaussianPass(encoder, gpuTextureView2, simpleFramebuffer6, 0.0f, step / (float)height);
                }
                INSTANCE.writeCompositeUniform(encoder, 1.0f, 1.0f);
                Supplier<String> supplier = GuiLayerBlurRenderer::composite$lambda$0;
                GpuTextureView gpuTextureView = main.getColorAttachmentView();
                Intrinsics.checkNotNull((Object)gpuTextureView);
                AutoCloseable step = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
                Throwable throwable = null;
                try {
                    RenderPass pass = (RenderPass)step;
                    boolean bl = false;
                    RenderPipeline renderPipeline = compositePipeline;
                    Intrinsics.checkNotNull((Object)renderPipeline);
                    pass.setPipeline(renderPipeline);
                    SimpleFramebuffer simpleFramebuffer7 = tempH;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer7);
                    pass.bindTexture("uGui", simpleFramebuffer7.getColorAttachmentView(), RenderSampler.linear());
                    GpuBuffer gpuBuffer = compositeUniform;
                    Intrinsics.checkNotNull((Object)gpuBuffer);
                    pass.setUniform("CompositeData", gpuBuffer);
                    pass.draw(0, 6);
// pass = Unit.INSTANCE;
                }
                catch (Throwable bl) {
                    throwable = bl;
                    throw bl;
                }
                finally {
                    AutoCloseableKt.closeFinally((AutoCloseable)step, (Throwable)throwable);
                }
                return;
            }
            GpuTextureView source = null;
            SimpleFramebuffer simpleFramebuffer8 = guiFbo;
            Intrinsics.checkNotNull((Object)simpleFramebuffer8);
            source = simpleFramebuffer8.getColorAttachmentView();
            if (blurRadius >= 0.5f && tempH != null && tempV != null) {
                float step = blurRadius / 16.0f;
                SimpleFramebuffer simpleFramebuffer9 = guiFbo;
                Intrinsics.checkNotNull((Object)simpleFramebuffer9);
                GpuTextureView gpuTextureView = simpleFramebuffer9.getColorAttachmentView();
                SimpleFramebuffer simpleFramebuffer10 = tempH;
                Intrinsics.checkNotNull((Object)simpleFramebuffer10);
                INSTANCE.gaussianPass(encoder, gpuTextureView, simpleFramebuffer10, step / (float)width, 0.0f);
                SimpleFramebuffer simpleFramebuffer11 = tempH;
                Intrinsics.checkNotNull((Object)simpleFramebuffer11);
                GpuTextureView gpuTextureView3 = simpleFramebuffer11.getColorAttachmentView();
                SimpleFramebuffer simpleFramebuffer12 = tempV;
                Intrinsics.checkNotNull((Object)simpleFramebuffer12);
                INSTANCE.gaussianPass(encoder, gpuTextureView3, simpleFramebuffer12, 0.0f, step / (float)height);
                SimpleFramebuffer simpleFramebuffer13 = tempV;
                Intrinsics.checkNotNull((Object)simpleFramebuffer13);
                source = simpleFramebuffer13.getColorAttachmentView();
            }
            if (worldActive) {
                Matrix4f worldMatrix = shatterMatrix;
                float worldScale = WorldGuiCloseAnimation.screenScale();
                float worldInvScale = worldScale > 1.0E-4f ? 1.0f / worldScale : 1.0f;
                INSTANCE.writeCompositeUniform(encoder, worldInvScale, WorldGuiCloseAnimation.alpha());
                INSTANCE.writeWorldQuadUniform(encoder, worldMatrix, width, height, 1.0f);
                Supplier<String> supplier = GuiLayerBlurRenderer::composite$lambda$2;
                GpuTextureView gpuTextureView = main.getColorAttachmentView();
                Intrinsics.checkNotNull((Object)gpuTextureView);
                AutoCloseable pass = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty(), main.getDepthAttachmentView(), OptionalDouble.empty());
                Throwable bl = null;
                try {
                    RenderPass pass2 = (RenderPass)pass;
                    boolean bl2 = false;
                    RenderPipeline renderPipeline = worldCompositePipeline;
                    Intrinsics.checkNotNull((Object)renderPipeline);
                    pass2.setPipeline(renderPipeline);
                    pass2.bindTexture("uGui", source, RenderSampler.linear());
                    GpuBuffer gpuBuffer = compositeUniform;
                    Intrinsics.checkNotNull((Object)gpuBuffer);
                    pass2.setUniform("CompositeData", gpuBuffer);
                    GpuBuffer gpuBuffer2 = worldQuadUniform;
                    Intrinsics.checkNotNull((Object)gpuBuffer2);
                    pass2.setUniform("WorldQuadData", gpuBuffer2);
                    pass2.draw(0, 6);
                    Unit unit = Unit.INSTANCE;
                }
                catch (Throwable throwable) {
                    bl = throwable;
                    throw throwable;
                }
                finally {
                    AutoCloseableKt.closeFinally((AutoCloseable)pass, (Throwable)bl);
                }
                return;
            }
            float invScale = scale > 1.0E-4f ? 1.0f / scale : 1.0f;
            INSTANCE.writeCompositeUniform(encoder, invScale, 1.0f);
            Supplier<String> supplier = GuiLayerBlurRenderer::composite$lambda$4;
            GpuTextureView gpuTextureView = main.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
            Throwable throwable = null;
            try {
                RenderPass pass = (RenderPass)autoCloseable;
                boolean bl = false;
                RenderPipeline renderPipeline = compositePipeline;
                Intrinsics.checkNotNull((Object)renderPipeline);
                pass.setPipeline(renderPipeline);
                pass.bindTexture("uGui", source, RenderSampler.linear());
                GpuBuffer gpuBuffer = compositeUniform;
                Intrinsics.checkNotNull((Object)gpuBuffer);
                pass.setUniform("CompositeData", gpuBuffer);
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
        catch (Throwable throwable) {
            INSTANCE.disableAfterError(throwable);
        }
    }

    @JvmStatic
    public static final void shutdown() {
        captureActive = false;
        INSTANCE.closeTargets();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void gaussianPass(CommandEncoder encoder, GpuTextureView source, SimpleFramebuffer target, float stepX, float stepY) {
        RenderProfiler.Scope scope = RenderProfiler.begin("ui.capture.gaussian");
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(16);
            data.putFloat(0, stepX);
            data.putFloat(4, stepY);
            data.position(0);
            GpuBuffer gpuBuffer = blurUniform;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 16L), data);
// stack = Unit.INSTANCE;
        }
        catch (Throwable bl) {
            throwable = bl;
            throw bl;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        Supplier<String> supplier = GuiLayerBlurRenderer::gaussianPass$lambda$1;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0));
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = blurPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            pass.bindTexture("uInput", source, RenderSampler.linear());
            GpuBuffer gpuBuffer = blurUniform;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("BlurData", gpuBuffer);
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
        RenderProfiler.end(scope);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final boolean drawShards(CommandEncoder encoder, GpuTextureView source, float progress, float alpha, float scale, boolean worldMode) {
        ByteBuffer vertexData = shardVertexData;
        if (shardPipeline == null || shardVertexBuffer == null || vertexData == null || tempH == null) {
            return false;
        }
        int vertices = 0;
        vertices = GuiShatterAnimation.buildGeometry(vertexData, progress, alpha, scale, worldMode);
        if ((vertices += this.appendKeepRegion(vertexData, scale, alpha)) <= 0 || vertexData.remaining() <= 0) {
            return false;
        }
        RenderProfiler.Scope scope = RenderProfiler.begin("ui.capture.shards");
        GpuBuffer gpuBuffer = shardVertexBuffer;
        Intrinsics.checkNotNull((Object)gpuBuffer);
        encoder.writeToBuffer(gpuBuffer.slice(0L, (long)vertexData.remaining()), vertexData);
        Supplier<String> supplier = GuiLayerBlurRenderer::drawShards$lambda$0;
        SimpleFramebuffer simpleFramebuffer2 = tempH;
        Intrinsics.checkNotNull((Object)simpleFramebuffer2);
        GpuTextureView gpuTextureView = simpleFramebuffer2.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0));
        Throwable throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = shardPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            GpuBuffer gpuBuffer2 = shardVertexBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer2);
            pass.setVertexBuffer(0, gpuBuffer2);
            pass.bindTexture("uGui", source, RenderSampler.linear());
            GpuBuffer gpuBuffer3 = worldQuadUniform;
            Intrinsics.checkNotNull((Object)gpuBuffer3);
            pass.setUniform("WorldQuadData", gpuBuffer3);
            pass.draw(0, vertices);
            Unit unit = Unit.INSTANCE;
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        RenderProfiler.end(scope);
        return true;
    }

    private final int appendKeepRegion(ByteBuffer vertexData, float scale, float alpha) {
        float[] fArray = GuiCapture.shatterKeepRect();
        if (fArray == null) {
            return 0;
        }
        float[] keep = fArray;
        if (keep.length < 4 || keep[2] <= 0.0f || keep[3] <= 0.0f) {
            return 0;
        }
        float[] rect = GuiShatterAnimation.beginRect();
        float screenW = rect[5];
        float screenH = rect[6];
        if (screenW <= 1.0f || screenH <= 1.0f) {
            return 0;
        }
        float panelX0 = (rect[0] - rect[4]) / screenW;
        float panelY0 = (rect[1] - rect[4]) / screenH;
        float panelX1 = (rect[0] + rect[2] + rect[4]) / screenW;
        float panelY1 = (rect[1] + rect[3] + rect[4]) / screenH;
        float x0 = this.clamp01(keep[0] / screenW);
        float y0 = this.clamp01(keep[1] / screenH);
        float x1 = this.clamp01((keep[0] + keep[2]) / screenW);
        float y1 = this.clamp01((keep[1] + keep[3]) / screenH);
        int shade = Math.round(this.clamp01(alpha) * 255.0f);
        int written = 0;
        if (y0 < panelY0) {
            written += GuiShatterAnimation.appendQuad(vertexData, x0, y0, x1, Math.min(y1, panelY0), scale, shade);
        }
        if (y1 > panelY1) {
            written += GuiShatterAnimation.appendQuad(vertexData, x0, Math.max(y0, panelY1), x1, y1, scale, shade);
        }
        float innerTop = Math.max(y0, panelY0);
        float innerBottom = Math.min(y1, panelY1);
        if (innerBottom > innerTop) {
            if (x0 < panelX0) {
                written += GuiShatterAnimation.appendQuad(vertexData, x0, innerTop, Math.min(x1, panelX0), innerBottom, scale, shade);
            }
            if (x1 > panelX1) {
                written += GuiShatterAnimation.appendQuad(vertexData, Math.max(x0, panelX1), innerTop, x1, innerBottom, scale, shade);
            }
        }
        return written;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void writeCompositeUniform(CommandEncoder encoder, float invScale, float alpha) {
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(16);
            data.putFloat(0, invScale);
            data.putFloat(4, alpha);
            data.position(0);
            GpuBuffer gpuBuffer = compositeUniform;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 16L), data);
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

    private final void writeWorldQuadUniform(CommandEncoder encoder, Matrix4f matrix, float width, float height, float worldMode) {
        this.writeWorldQuadUniform(encoder, matrix, width, height, worldMode, 0.0f, 1.0f, 1.0f, 0.0f);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void writeWorldQuadUniform(CommandEncoder encoder, Matrix4f matrix, float width, float height, float worldMode, float u0, float vTop, float u1, float vBottom) {
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(96);
            Matrix4f matrix4f = matrix;
            Intrinsics.checkNotNull((Object)matrix4f);
            matrix4f.get(0, data);
            data.putFloat(64, width);
            data.putFloat(68, height);
            data.putFloat(72, worldMode);
            data.putFloat(80, u0);
            data.putFloat(84, vTop);
            data.putFloat(88, u1);
            data.putFloat(92, vBottom);
            data.position(0);
            GpuBuffer gpuBuffer = worldQuadUniform;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 96L), data);
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

    private final boolean ensurePipelines() {
        if (blurPipeline != null && compositePipeline != null && worldCompositePipeline != null && worldBackdropPipeline != null && shardPipeline != null && worldSlotsPipeline != null && slotBlitPipeline != null && blurUniform != null && compositeUniform != null && worldQuadUniform != null && shardVertexBuffer != null && worldSlotsUniform != null && slotBlitUniform != null) {
            return true;
        }
        if (blurPipeline == null) {
            blurPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(BLUR_PIPELINE_ID).withVertexShader(FULLSCREEN).withFragmentShader(GAUSSIAN_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withSampler("uInput").withUniform("BlurData", UniformType.UNIFORM_BUFFER).withoutBlend().withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
        }
        if (compositePipeline == null) {
            compositePipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(COMPOSITE_PIPELINE_ID).withVertexShader(FULLSCREEN).withFragmentShader(COMPOSITE_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withSampler("uGui").withUniform("CompositeData", UniformType.UNIFORM_BUFFER).withBlend(new BlendFunction(SourceFactor.ONE, DestFactor.ONE_MINUS_SRC_ALPHA)).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
        }
        if (worldCompositePipeline == null) {
            worldCompositePipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(WORLD_COMPOSITE_PIPELINE_ID).withVertexShader(WORLD_QUAD_SHADER).withFragmentShader(COMPOSITE_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withSampler("uGui").withUniform("CompositeData", UniformType.UNIFORM_BUFFER).withUniform("WorldQuadData", UniformType.UNIFORM_BUFFER).withBlend(new BlendFunction(SourceFactor.ONE, DestFactor.ONE_MINUS_SRC_ALPHA)).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
        }
        if (worldOccludedPipeline == null) {
            worldOccludedPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(WORLD_OCCLUDED_PIPELINE_ID).withVertexShader(WORLD_QUAD_SHADER).withFragmentShader(WORLD_OCCLUDED_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withSampler("uGui").withSampler("uDepth").withSampler("uHandDepth").withUniform("CompositeData", UniformType.UNIFORM_BUFFER).withUniform("WorldQuadData", UniformType.UNIFORM_BUFFER).withBlend(new BlendFunction(SourceFactor.ONE, DestFactor.ONE_MINUS_SRC_ALPHA)).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
        }
        if (worldBackdropPipeline == null) {
            worldBackdropPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(WORLD_BACKDROP_PIPELINE_ID).withVertexShader(FULLSCREEN).withFragmentShader(WORLD_BACKDROP_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withSampler("uGui").withUniform("WorldQuadData", UniformType.UNIFORM_BUFFER).withoutBlend().withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
        }
        if (shardPipeline == null) {
            shardPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(SHARD_PIPELINE_ID).withVertexShader(SHARD_SHADER).withFragmentShader(SHARD_SHADER).withVertexFormat(SHARD_FORMAT, VertexFormat.DrawMode.TRIANGLES).withSampler("uGui").withUniform("WorldQuadData", UniformType.UNIFORM_BUFFER).withBlend(new BlendFunction(SourceFactor.ONE, DestFactor.ONE_MINUS_SRC_ALPHA)).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
        }
        if (shardOccludedPipeline == null) {
            shardOccludedPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(SHARD_OCCLUDED_PIPELINE_ID).withVertexShader(SHARD_SHADER).withFragmentShader(SHARD_OCCLUDED_SHADER).withVertexFormat(SHARD_FORMAT, VertexFormat.DrawMode.TRIANGLES).withSampler("uGui").withSampler("uDepth").withSampler("uHandDepth").withUniform("WorldQuadData", UniformType.UNIFORM_BUFFER).withBlend(new BlendFunction(SourceFactor.ONE, DestFactor.ONE_MINUS_SRC_ALPHA)).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
        }
        if (worldSlotsPipeline == null) {
            worldSlotsPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(WORLD_SLOTS_PIPELINE_ID).withVertexShader(FULLSCREEN).withFragmentShader(WORLD_SLOTS_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withSampler("uGui").withUniform("WorldWarpData", UniformType.UNIFORM_BUFFER).withoutBlend().withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
        }
        if (slotBlitPipeline == null) {
            slotBlitPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(SLOT_BLIT_PIPELINE_ID).withVertexShader(FULLSCREEN).withFragmentShader(SLOT_BLIT_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withSampler("uGui").withUniform("SlotBlitData", UniformType.UNIFORM_BUFFER).withBlend(new BlendFunction(SourceFactor.ONE, DestFactor.ONE_MINUS_SRC_ALPHA)).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
        }
        if (shardVertexBuffer == null) {
            shardVertexBuffer = RenderSystem.getDevice().createBuffer(GuiLayerBlurRenderer::ensurePipelines$lambda$0, 40, 44928L);
        }
        if (shardVertexData == null) {
            shardVertexData = ByteBuffer.allocateDirect(44928).order(ByteOrder.nativeOrder());
        }
        if (blurUniform == null) {
            blurUniform = RenderSystem.getDevice().createBuffer(GuiLayerBlurRenderer::ensurePipelines$lambda$1, 136, 16L);
        }
        if (compositeUniform == null) {
            compositeUniform = RenderSystem.getDevice().createBuffer(GuiLayerBlurRenderer::ensurePipelines$lambda$2, 136, 16L);
        }
        if (worldQuadUniform == null) {
            worldQuadUniform = RenderSystem.getDevice().createBuffer(GuiLayerBlurRenderer::ensurePipelines$lambda$3, 136, 96L);
        }
        if (worldSlotsUniform == null) {
            worldSlotsUniform = RenderSystem.getDevice().createBuffer(GuiLayerBlurRenderer::ensurePipelines$lambda$4, 136, 512L);
        }
        if (slotBlitUniform == null) {
            slotBlitUniform = RenderSystem.getDevice().createBuffer(GuiLayerBlurRenderer::ensurePipelines$lambda$5, 136, 64L);
        }
        return blurPipeline != null && compositePipeline != null && worldCompositePipeline != null && worldBackdropPipeline != null && shardPipeline != null && worldSlotsPipeline != null && slotBlitPipeline != null && blurUniform != null && compositeUniform != null && worldQuadUniform != null && shardVertexBuffer != null && shardVertexData != null && worldSlotsUniform != null && slotBlitUniform != null;
    }

    private final boolean ensureTargets(int width, int height) {
        GpuDevice device = RenderSystem.tryGetDevice();
        if (device == null || width <= 0 || height <= 0) {
            return false;
        }
        if (guiFbo != null && tempH != null && tempV != null && width == texWidth && height == texHeight) {
            return true;
        }
        this.closeTargets();
        guiFbo = new SimpleFramebuffer("kimiko_gui_capture", width, height, true);
        tempH = new SimpleFramebuffer("kimiko_gui_capture_h", width, height, false);
        tempV = new SimpleFramebuffer("kimiko_gui_capture_v", width, height, false);
        texWidth = width;
        texHeight = height;
        return true;
    }

    private final void ensureSceneSnapshotTarget(int width, int height) {
        SimpleFramebuffer simpleFramebuffer2;
        SimpleFramebuffer current = sceneSnapshot;
        if (current != null && current.textureWidth == width && current.textureHeight == height) {
            return;
        }
        sceneSnapshot = this.destroy(sceneSnapshot);
        try {
            simpleFramebuffer2 = new SimpleFramebuffer("kimiko_world_backdrop_snapshot", width, height, false);
        }
        catch (Throwable throwable) {
            simpleFramebuffer2 = null;
        }
        sceneSnapshot = simpleFramebuffer2;
    }

    private final void closeTargets() {
        guiFbo = this.destroy(guiFbo);
        tempH = this.destroy(tempH);
        tempV = this.destroy(tempV);
        sceneSnapshot = this.destroy(sceneSnapshot);
        remoteFbo = this.destroy(remoteFbo);
        this.closeWorldDepthCopy();
        this.closeHandDepthCopy();
        this.closePreCompositeColor();
        texWidth = -1;
        texHeight = -1;
    }

    private final SimpleFramebuffer destroy(SimpleFramebuffer target) {
        block0: {
            SimpleFramebuffer simpleFramebuffer2 = target;
            if (simpleFramebuffer2 == null) break block0;
            simpleFramebuffer2.delete();
        }
        return null;
    }

    private final void disableAfterError(Throwable throwable) {
        disabledAfterError = true;
        captureActive = false;
        this.closeTargets();
    }

    private final RenderPipeline markerPipeline(String name) {
        RenderPipeline renderPipeline = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(this.id("pipeline/post/guilayerblur/" + name)).withVertexShader("core/position_color").withFragmentShader("core/position_color").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.QUADS).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withColorWrite(false).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"build(...)");
        return renderPipeline;
    }

    private final ColoredQuadGuiElementRenderState markerState(RenderPipeline pipeline) {
        return new ColoredQuadGuiElementRenderState(pipeline, TextureSetup.empty(), (Matrix3x2fc)new Matrix3x2f(), 0, 0, 1, 1, 0, 0, null);
    }

    private final void submitMarker(DrawContext graphics, ColoredQuadGuiElementRenderState marker) {
        if (graphics == null) {
            return;
        }
        GuiRenderState state = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
        state.createNewRootLayer();
        state.addSimpleElement((SimpleGuiElementRenderState)marker);
        state.createNewRootLayer();
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }

    private static final String beginCapture$lambda$0() {
        return "kimiko:gui_capture_clear";
    }

    private static final String beginCapture$lambda$2() {
        return "kimiko:gui_share_clear";
    }

    private static final String snapshotWorldDepth$lambda$0() {
        return "kimiko:gui_share_world_depth";
    }

    private static final String snapshotHandDepth$lambda$0() {
        return "kimiko:gui_share_hand_depth";
    }

    private static final String capturePreCompositeColor$lambda$0() {
        return "kimiko:gui_share_scene_before";
    }

    private static final String drawRemoteQuad$lambda$0() {
        return "kimiko:gui_share_composite";
    }

    private static final String drawRemoteQuad$lambda$2() {
        return "kimiko:gui_share_composite";
    }

    private static final String drawRemoteShards$lambda$0() {
        return "kimiko:gui_share_shards";
    }

    private static final String worldSnapshotWithPanels$lambda$0() {
        return "kimiko:gui_share_popup_backdrop";
    }

    private static final String blitPanelsIntoPopupSlots$lambda$1() {
        return "kimiko:gui_share_popup_slot_blit";
    }

    private static final String worldBackdropSource$lambda$0() {
        return "kimiko:world_backdrop_warp";
    }

    private static final String worldBackdropSource$lambda$2() {
        return "kimiko:world_backdrop_warp_slots";
    }

    private static final String composite$lambda$0() {
        return "kimiko:gui_capture_shard_composite";
    }

    private static final String composite$lambda$2() {
        return "kimiko:gui_capture_composite_world";
    }

    private static final String composite$lambda$4() {
        return "kimiko:gui_capture_composite";
    }

    private static final String gaussianPass$lambda$1() {
        return "kimiko:gui_capture_gaussian";
    }

    private static final String drawShards$lambda$0() {
        return "kimiko:gui_capture_shards";
    }

    private static final String ensurePipelines$lambda$0() {
        return "kimiko:guilayerblur_shard_vertices";
    }

    private static final String ensurePipelines$lambda$1() {
        return "kimiko:guilayerblur_blur_uniform";
    }

    private static final String ensurePipelines$lambda$2() {
        return "kimiko:guilayerblur_composite_uniform";
    }

    private static final String ensurePipelines$lambda$3() {
        return "kimiko:guilayerblur_world_quad_uniform";
    }

    private static final String ensurePipelines$lambda$4() {
        return "kimiko:guilayerblur_world_warp_uniform";
    }

    private static final String ensurePipelines$lambda$5() {
        return "kimiko:guilayerblur_slot_blit_uniform";
    }

    static {
        VertexFormat vertexFormat = VertexFormat.builder().add("Position", VertexFormatElement.POSITION).add("UV0", VertexFormatElement.UV0).add("Color", VertexFormatElement.COLOR).build();
        Intrinsics.checkNotNullExpressionValue((Object)vertexFormat, (String)"build(...)");
        SHARD_FORMAT = vertexFormat;
        WORLD_BACKDROP_SHADER = INSTANCE.id("post/guilayerblur/world_backdrop");
        WORLD_SLOTS_SHADER = INSTANCE.id("post/guilayerblur/world_backdrop_slots");
        GAUSSIAN_SHADER = INSTANCE.id("post/guilayerblur/gaussian");
        COMPOSITE_SHADER = INSTANCE.id("post/guilayerblur/composite");
        WORLD_OCCLUDED_SHADER = INSTANCE.id("post/guilayerblur/composite_world_occluded");
        WORLD_OCCLUDED_PIPELINE_ID = INSTANCE.id("pipeline/post/guilayerblur/composite_world_occluded");
        PANEL_BOUNDARY_PIPELINE = INSTANCE.markerPipeline("panel_boundary");
        PANEL_BOUNDARY = INSTANCE.markerState(PANEL_BOUNDARY_PIPELINE);
        POPUP_BOUNDARY_PIPELINE = INSTANCE.markerPipeline("popup_boundary");
        POPUP_BOUNDARY = INSTANCE.markerState(POPUP_BOUNDARY_PIPELINE);
        REMOTE_BEGIN_PIPELINE = INSTANCE.markerPipeline("remote_begin");
        REMOTE_BEGIN = INSTANCE.markerState(REMOTE_BEGIN_PIPELINE);
        REMOTE_END_PIPELINE = INSTANCE.markerPipeline("remote_end");
        REMOTE_END = INSTANCE.markerState(REMOTE_END_PIPELINE);
        REMOTE_CARD_BEGIN_PIPELINE = INSTANCE.markerPipeline("remote_card_begin");
        REMOTE_CARD_BEGIN = INSTANCE.markerState(REMOTE_CARD_BEGIN_PIPELINE);
        REMOTE_CARD_END_PIPELINE = INSTANCE.markerPipeline("remote_card_end");
        REMOTE_CARD_END = INSTANCE.markerState(REMOTE_CARD_END_PIPELINE);
        IDENTITY_MATRIX = new Matrix4f();
        texWidth = -1;
        texHeight = -1;
    }
}

