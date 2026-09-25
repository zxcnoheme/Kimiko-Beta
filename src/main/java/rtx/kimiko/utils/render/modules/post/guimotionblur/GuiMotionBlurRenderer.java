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
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gl.SimpleFramebuffer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.system.MemoryStack
 */
package rtx.kimiko.utils.render.modules.post.guimotionblur;

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
import java.util.OptionalDouble;
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
import net.minecraft.client.gl.SimpleFramebuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryStack;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.others.FullscreenQuadBuffer;
import rtx.kimiko.utils.render.others.RenderSampler;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00a0\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\t\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ%\u0010\b\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\fJ\u001b\u0010\r\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\r\u0010\tJ%\u0010\r\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\r\u0010\fJ\u008d\u0001\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0095\u0001\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010!\u001a\u00020 H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001e\u0010\"J\u0097\u0001\u0010\u001e\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001e\u0010#J\u009f\u0001\u0010\u001e\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010!\u001a\u00020 H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001e\u0010$J\u008d\u0001\u0010%\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b%\u0010\u001fJ\u00a3\u0001\u0010'\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010!\u001a\u00020 2\u0006\u0010&\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b'\u0010(J\u0013\u0010)\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b)\u0010\u0003J\u000f\u0010*\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b*\u0010\u0003J'\u0010.\u001a\u00020 2\u0006\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b.\u0010/J/\u00102\u001a\u00020\u00062\u0006\u00101\u001a\u0002002\u0006\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b2\u00103Ji\u0010;\u001a\u00020\u00062\u0006\u00105\u001a\u0002042\b\u00107\u001a\u0004\u0018\u0001062\u0006\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020\u000f2\u0006\u00109\u001a\u0002082\u0006\u0010:\u001a\u0002082\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b;\u0010<J[\u0010D\u001a\u00020\u00062\u0006\u00105\u001a\u0002042\b\u0010=\u001a\u0004\u0018\u0001062\b\u0010\u000b\u001a\u0004\u0018\u0001082\u0006\u0010>\u001a\u00020\u00042\u0006\u0010?\u001a\u00020\u00042\u0006\u0010@\u001a\u00020\u000f2\u0006\u0010A\u001a\u00020\u000f2\u0006\u0010B\u001a\u00020\u000f2\u0006\u0010C\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bD\u0010EJA\u0010K\u001a\u00020\u00062\u0006\u00105\u001a\u0002042\b\u0010F\u001a\u0004\u0018\u00010\u00142\u0006\u0010G\u001a\u00020\u000f2\u0006\u0010H\u001a\u00020\u000f2\u0006\u0010I\u001a\u00020\u000f2\u0006\u0010J\u001a\u00020 H\u0002\u00a2\u0006\u0004\bK\u0010LJ7\u0010O\u001a\u00020\u000f2\u0006\u0010N\u001a\u00020M2\u0006\u0010F\u001a\u00020\u00142\u0006\u0010G\u001a\u00020\u000f2\u0006\u0010H\u001a\u00020\u000f2\u0006\u0010I\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bO\u0010PJ_\u0010Y\u001a\u00020\u00062\u0006\u0010N\u001a\u00020M2\u0006\u0010Q\u001a\u00020\u00042\u0006\u0010R\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010S\u001a\u00020\u00042\u0006\u0010T\u001a\u00020\u00042\u0006\u0010U\u001a\u00020\u000f2\u0006\u0010V\u001a\u00020\u000f2\u0006\u0010W\u001a\u00020\u000f2\u0006\u0010X\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bY\u0010ZJ\u00d5\u0001\u0010`\u001a\u00020\u00062\u0006\u00105\u001a\u0002042\b\u0010\u000b\u001a\u0004\u0018\u0001062\b\u0010[\u001a\u0004\u0018\u0001062\b\u0010\\\u001a\u0004\u0018\u0001062\b\u0010]\u001a\u0004\u0018\u0001062\b\u0010^\u001a\u0004\u0018\u0001062\b\u0010_\u001a\u0004\u0018\u0001062\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010H\u001a\u00020\u000f2\u0006\u0010I\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b`\u0010aJy\u0010b\u001a\u00020\u00062\u0006\u00105\u001a\u0002042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010H\u001a\u00020\u000f2\u0006\u0010I\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bb\u0010cJ\u0017\u0010-\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b-\u0010dJ\u000f\u0010e\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\be\u0010\u0003J\u0017\u0010g\u001a\u00020\u00062\u0006\u0010f\u001a\u00020 H\u0002\u00a2\u0006\u0004\bg\u0010hJ\u000f\u0010i\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bi\u0010\u0003J\u0019\u0010l\u001a\u00020\u00062\b\u0010k\u001a\u0004\u0018\u00010jH\u0002\u00a2\u0006\u0004\bl\u0010mJ\u0017\u0010p\u001a\u00020\u00062\u0006\u0010o\u001a\u00020nH\u0002\u00a2\u0006\u0004\bp\u0010qJ\u000f\u0010r\u001a\u00020 H\u0002\u00a2\u0006\u0004\br\u0010sJ!\u0010t\u001a\u00020\u000f2\b\u0010F\u001a\u0004\u0018\u00010\u00142\u0006\u0010G\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bt\u0010uJ'\u0010y\u001a\u00020\u00042\u0006\u0010v\u001a\u00020\u00042\u0006\u0010w\u001a\u00020\u00042\u0006\u0010x\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\by\u0010zJ'\u0010{\u001a\u00020\u000f2\u0006\u0010v\u001a\u00020\u000f2\u0006\u0010w\u001a\u00020\u000f2\u0006\u0010x\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b{\u0010|J\u001a\u0010\u0080\u0001\u001a\u00020\u007f2\u0006\u0010~\u001a\u00020}H\u0002\u00a2\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001R\u0017\u0010\u0082\u0001\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0017\u0010\u0084\u0001\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0084\u0001\u0010\u0083\u0001R\u0017\u0010\u0085\u0001\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0085\u0001\u0010\u0083\u0001R\u0017\u0010\u0086\u0001\u001a\u00020\u000f8\u0006X\u0086T\u00a2\u0006\b\n\u0006\b\u0086\u0001\u0010\u0083\u0001R\u0017\u0010\u0087\u0001\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0087\u0001\u0010\u0083\u0001R\u0018\u0010\u0089\u0001\u001a\u00030\u0088\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0089\u0001\u0010\u008a\u0001R\u0017\u0010\u008b\u0001\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008b\u0001\u0010\u0083\u0001R\u0017\u0010\u008c\u0001\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008c\u0001\u0010\u0083\u0001R\u0017\u0010\u008d\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008d\u0001\u0010\u008e\u0001R\u0017\u0010\u008f\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u008f\u0001\u0010\u008e\u0001R\u0017\u0010\u0090\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0090\u0001\u0010\u008e\u0001R\u0017\u0010\u0091\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0091\u0001\u0010\u008e\u0001R\u0017\u0010\u0092\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0092\u0001\u0010\u008e\u0001R\u0017\u0010\u0093\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0093\u0001\u0010\u008e\u0001R\u0017\u0010\u0094\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0094\u0001\u0010\u008e\u0001R\u0017\u0010\u0095\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0095\u0001\u0010\u008e\u0001R\u0017\u0010\u0096\u0001\u001a\u00020\u007f8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0096\u0001\u0010\u0097\u0001R\u0017\u0010\u0098\u0001\u001a\u00020\u007f8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0098\u0001\u0010\u0097\u0001R\u0017\u0010\u0099\u0001\u001a\u00020\u007f8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0099\u0001\u0010\u0097\u0001R\u0017\u0010\u009a\u0001\u001a\u00020\u007f8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u0097\u0001R\u0017\u0010\u009b\u0001\u001a\u00020\u007f8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009b\u0001\u0010\u0097\u0001R\u0017\u0010\u009c\u0001\u001a\u00020\u007f8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u0097\u0001R\u0017\u0010\u009d\u0001\u001a\u00020\u007f8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009d\u0001\u0010\u0097\u0001R\u0017\u0010\u009e\u0001\u001a\u00020\u007f8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009e\u0001\u0010\u0097\u0001R\u0017\u0010\u009f\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009f\u0001\u0010\u008e\u0001R\u0018\u0010\u00a1\u0001\u001a\u00030\u00a0\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a1\u0001\u0010\u00a2\u0001R\u0018\u0010\u00a3\u0001\u001a\u00030\u00a0\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a3\u0001\u0010\u00a2\u0001R\u0018\u0010\u00a4\u0001\u001a\u00030\u00a0\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a4\u0001\u0010\u00a2\u0001R\u0018\u0010\u00a5\u0001\u001a\u00030\u00a0\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a5\u0001\u0010\u00a2\u0001R\u001b\u0010\u00a6\u0001\u001a\u0004\u0018\u00010j8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a6\u0001\u0010\u00a7\u0001R\u001b\u0010\u00a8\u0001\u001a\u0004\u0018\u00010j8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a8\u0001\u0010\u00a7\u0001R\u001b\u0010\u00a9\u0001\u001a\u0004\u0018\u00010j8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a9\u0001\u0010\u00a7\u0001R\u001b\u0010\u00aa\u0001\u001a\u0004\u0018\u00010j8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00aa\u0001\u0010\u00a7\u0001R\u001c\u0010\u00ac\u0001\u001a\u0005\u0018\u00010«\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ac\u0001\u0010\u00ad\u0001R\u001b\u0010\u00ae\u0001\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ae\u0001\u0010\u00af\u0001R\u001c\u0010\u00b0\u0001\u001a\u0005\u0018\u00010«\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b0\u0001\u0010\u00ad\u0001R\u001b\u0010\u00b1\u0001\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b1\u0001\u0010\u00af\u0001R\u001c\u0010\u00b2\u0001\u001a\u0005\u0018\u00010«\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b2\u0001\u0010\u00ad\u0001R\u001b\u0010\u00b3\u0001\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b3\u0001\u0010\u00af\u0001R\u001c\u0010\u00b4\u0001\u001a\u0005\u0018\u00010«\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b4\u0001\u0010\u00ad\u0001R\u001b\u0010\u00b5\u0001\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b5\u0001\u0010\u00af\u0001R\u001b\u0010\u00b6\u0001\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b6\u0001\u0010\u00b7\u0001R\u001b\u0010\u00b8\u0001\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b8\u0001\u0010\u00b7\u0001R\u001b\u0010\u00b9\u0001\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b9\u0001\u0010\u00b7\u0001R\u001b\u0010\u00ba\u0001\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ba\u0001\u0010\u00b7\u0001R\u001b\u0010»\u0001\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b»\u0001\u0010\u00b7\u0001R\u0017\u0010\u0019\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0019\u0010\u0083\u0001R\u0017\u0010\u001a\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u001a\u0010\u0083\u0001R\u0019\u0010\u00bc\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00bc\u0001\u0010\u0083\u0001R\u0019\u0010\u00bd\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00bd\u0001\u0010\u0083\u0001R\u0019\u0010\u00be\u0001\u001a\u00020 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00be\u0001\u0010\u00bf\u0001R\u001b\u0010\u00c0\u0001\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c0\u0001\u0010\u00c1\u0001R\u0019\u0010\u00c2\u0001\u001a\u00020 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c2\u0001\u0010\u00bf\u0001R\u001b\u0010\u00c3\u0001\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c3\u0001\u0010\u00c1\u0001R\u0019\u0010\u00c4\u0001\u001a\u00020 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c4\u0001\u0010\u00bf\u0001R\u001b\u0010\u00c5\u0001\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c5\u0001\u0010\u00c1\u0001R\u0019\u0010\u00c6\u0001\u001a\u00020 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c6\u0001\u0010\u00bf\u0001R\u001a\u0010\u00c8\u0001\u001a\u00030\u00c7\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c8\u0001\u0010\u00c9\u0001R\u0019\u0010\u00ca\u0001\u001a\u00020 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ca\u0001\u0010\u00bf\u0001R\u0017\u0010\u00cb\u0001\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00cb\u0001\u0010\u0083\u0001R\u0017\u0010\u00cc\u0001\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00cc\u0001\u0010\u0083\u0001R\u0018\u0010\u00cd\u0001\u001a\u00030\u00c7\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00cd\u0001\u0010\u00c9\u0001\u00a8\u0006\u00ce\u0001"}, d2={"Lrtx/kimiko/utils/render/modules/post/guimotionblur/GuiMotionBlurRenderer;", "", "<init>", "()V", "", "radius", "", "Lkotlin/jvm/JvmStatic;", "captureBackground", "(F)V", "Lnet/minecraft/Framebuffer;", "target", "(Lnet/minecraft/Framebuffer;F)V", "captureHudBackground", "opacity", "", "boundsX", "boundsY", "boundsWidth", "boundsHeight", "", "maskRects", "maskRectCount", "sourceX", "sourceY", "sourceWidth", "sourceHeight", "scale", "originX", "originY", "applyWithCopy", "(FFIIII[FIIIIIFFF)V", "", "maskReplace", "(FFIIII[FIIIIIFFFZ)V", "(Lnet/minecraft/Framebuffer;FFIIII[FIIIIIFFF)V", "(Lnet/minecraft/Framebuffer;FFIIII[FIIIIIFFFZ)V", "applyHudWithCopy", "captureKind", "applyCaptured", "(Lnet/minecraft/Framebuffer;FFIIII[FIIIIIFFFZI)V", "shutdown", "releaseResources", "width", "height", "renderScale", "ensureReady", "(IIF)Z", "Lcom/mojang/blaze3d/systems/GpuDevice;", "device", "ensureTargets", "(Lcom/mojang/blaze3d/systems/GpuDevice;IIF)V", "Lcom/mojang/blaze3d/systems/CommandEncoder;", "encoder", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "src", "Lnet/minecraft/SimpleFramebuffer;", "horizontal", "result", "gaussianChain", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lcom/mojang/blaze3d/textures/GpuTextureView;IILnet/minecraft/SimpleFramebuffer;Lnet/minecraft/SimpleFramebuffer;FIIII)V", "source", "stepX", "stepY", "regionX", "regionY", "regionWidth", "regionHeight", "gaussianPass", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lcom/mojang/blaze3d/textures/GpuTextureView;Lnet/minecraft/SimpleFramebuffer;FFIIII)V", "rects", "rectCount", "targetWidth", "targetHeight", "replace", "renderMask", "(Lcom/mojang/blaze3d/systems/CommandEncoder;[FIIIZ)V", "Ljava/nio/ByteBuffer;", "data", "writeMaskVertices", "(Ljava/nio/ByteBuffer;[FIII)I", "x", "y", "localX", "localY", "halfW", "halfH", "presence", "blurMix", "putVertex", "(Ljava/nio/ByteBuffer;FFFFFIIII)V", "scene", "blur", "background", "backgroundBlur", "mask", "composite", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;FFIIIIII[FIIIIFFF)V", "writeCompositeUniform", "(Lcom/mojang/blaze3d/systems/CommandEncoder;FFII[FIIIIFFF)V", "(F)F", "closeTargets", "mainCapture", "clearBackgroundCapture", "(Z)V", "clearHudBackgroundCapture", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "buffer", "closeBuffer", "(Lcom/mojang/blaze3d/buffers/GpuBuffer;)V", "", "throwable", "disableAfterError", "(Ljava/lang/Throwable;)V", "isTemporarilyDisabled", "()Z", "safeMaskRectCount", "([FI)I", "value", "min", "max", "clamp", "(FFF)F", "clampInt", "(III)I", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "UNIFORM_BYTES", "I", "COMPOSITE_UNIFORM_BYTES", "MAX_MASK_RECTS", "MASK_RECT_STRIDE", "MASK_VERTICES_PER_RECT", "Lcom/mojang/blaze3d/vertex/VertexFormat;", "MASK_FORMAT", "Lcom/mojang/blaze3d/vertex/VertexFormat;", "MASK_VERTEX_STRIDE_BYTES", "MASK_VERTEX_BYTES", "MASK_FEATHER_PX", "F", "MASK_CORNER_RADIUS_PX", "GAUSSIAN_SAMPLE_RADIUS", "MOTION_LAYER_BOOST", "GUI_MASK_RADIUS", "GUI_MASK_FEATHER", "MIN_RENDER_SCALE", "MAX_RENDER_SCALE", "BLUR_PIPELINE_ID", "Lnet/minecraft/Identifier;", "MASK_PIPELINE_ID", "MASK_REPLACE_PIPELINE_ID", "COMPOSITE_PIPELINE_ID", "FULLSCREEN_SHADER", "GAUSSIAN_SHADER", "MASK_SHADER", "COMPOSITE_SHADER", "GAUSSIAN_TAPS_HALF", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "BLUR_PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "MASK_PIPELINE", "MASK_REPLACE_PIPELINE", "COMPOSITE_PIPELINE", "fullscreenVertexBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "maskVertexBuffer", "blurUniformBuffer", "compositeUniformBuffer", "Lcom/mojang/blaze3d/textures/GpuTexture;", "sceneCopyTexture", "Lcom/mojang/blaze3d/textures/GpuTexture;", "sceneCopyTextureView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "backgroundCopyTexture", "backgroundCopyTextureView", "remoteBackgroundCopyTexture", "remoteBackgroundCopyTextureView", "hudBackgroundCopyTexture", "hudBackgroundCopyTextureView", "maskTarget", "Lnet/minecraft/SimpleFramebuffer;", "horizontalTarget", "verticalTarget", "backgroundHorizontalTarget", "backgroundVerticalTarget", "blurWidth", "blurHeight", "backgroundCaptured", "Z", "backgroundCaptureTarget", "Lnet/minecraft/Framebuffer;", "remoteBackgroundCaptured", "remoteBackgroundCaptureTarget", "hudBackgroundCaptured", "hudBackgroundCaptureTarget", "disabledAfterError", "", "retryAfterErrorNanos", "J", "pipelinesRegistered", "CAPTURE_AUTO", "CAPTURE_HUD", "ERROR_RETRY_DELAY_NANOS", "rtx.kimiko:kimiko"})
public final class GuiMotionBlurRenderer {
    @NotNull
    public static final GuiMotionBlurRenderer INSTANCE = new GuiMotionBlurRenderer();
    private static final int UNIFORM_BYTES = 16;
    private static final int COMPOSITE_UNIFORM_BYTES = 80;
    private static final int MAX_MASK_RECTS = 192;
    public static final int MASK_RECT_STRIDE = 6;
    private static final int MASK_VERTICES_PER_RECT = 6;
    @NotNull
    private static final VertexFormat MASK_FORMAT;
    private static final int MASK_VERTEX_STRIDE_BYTES;
    private static final int MASK_VERTEX_BYTES;
    private static final float MASK_FEATHER_PX = 8.0f;
    private static final float MASK_CORNER_RADIUS_PX = 8.0f;
    private static final float GAUSSIAN_SAMPLE_RADIUS = 8.0f;
    private static final float MOTION_LAYER_BOOST = 1.0f;
    private static final float GUI_MASK_RADIUS = 10.0f;
    private static final float GUI_MASK_FEATHER = 18.0f;
    private static final float MIN_RENDER_SCALE = 0.42f;
    private static final float MAX_RENDER_SCALE = 0.56f;
    @NotNull
    private static final Identifier BLUR_PIPELINE_ID;
    @NotNull
    private static final Identifier MASK_PIPELINE_ID;
    @NotNull
    private static final Identifier MASK_REPLACE_PIPELINE_ID;
    @NotNull
    private static final Identifier COMPOSITE_PIPELINE_ID;
    @NotNull
    private static final Identifier FULLSCREEN_SHADER;
    @NotNull
    private static final Identifier GAUSSIAN_SHADER;
    @NotNull
    private static final Identifier MASK_SHADER;
    @NotNull
    private static final Identifier COMPOSITE_SHADER;
    private static final float GAUSSIAN_TAPS_HALF = 16.0f;
    @NotNull
    private static final RenderPipeline BLUR_PIPELINE;
    @NotNull
    private static final RenderPipeline MASK_PIPELINE;
    @NotNull
    private static final RenderPipeline MASK_REPLACE_PIPELINE;
    @NotNull
    private static final RenderPipeline COMPOSITE_PIPELINE;
    @Nullable
    private static GpuBuffer fullscreenVertexBuffer;
    @Nullable
    private static GpuBuffer maskVertexBuffer;
    @Nullable
    private static GpuBuffer blurUniformBuffer;
    @Nullable
    private static GpuBuffer compositeUniformBuffer;
    @Nullable
    private static GpuTexture sceneCopyTexture;
    @Nullable
    private static GpuTextureView sceneCopyTextureView;
    @Nullable
    private static GpuTexture backgroundCopyTexture;
    @Nullable
    private static GpuTextureView backgroundCopyTextureView;
    @Nullable
    private static GpuTexture remoteBackgroundCopyTexture;
    @Nullable
    private static GpuTextureView remoteBackgroundCopyTextureView;
    @Nullable
    private static GpuTexture hudBackgroundCopyTexture;
    @Nullable
    private static GpuTextureView hudBackgroundCopyTextureView;
    @Nullable
    private static SimpleFramebuffer maskTarget;
    @Nullable
    private static SimpleFramebuffer horizontalTarget;
    @Nullable
    private static SimpleFramebuffer verticalTarget;
    @Nullable
    private static SimpleFramebuffer backgroundHorizontalTarget;
    @Nullable
    private static SimpleFramebuffer backgroundVerticalTarget;
    private static int sourceWidth;
    private static int sourceHeight;
    private static int blurWidth;
    private static int blurHeight;
    private static boolean backgroundCaptured;
    @Nullable
    private static Framebuffer backgroundCaptureTarget;
    private static boolean remoteBackgroundCaptured;
    @Nullable
    private static Framebuffer remoteBackgroundCaptureTarget;
    private static boolean hudBackgroundCaptured;
    @Nullable
    private static Framebuffer hudBackgroundCaptureTarget;
    private static boolean disabledAfterError;
    private static long retryAfterErrorNanos;
    private static boolean pipelinesRegistered;
    private static final int CAPTURE_AUTO = 0;
    private static final int CAPTURE_HUD = 1;
    private static final long ERROR_RETRY_DELAY_NANOS = 250000000L;

    private GuiMotionBlurRenderer() {
    }

    @JvmStatic
    public static final void captureBackground(float radius) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.gameRenderer == null || minecraft.getFramebuffer() == null) {
            backgroundCaptured = false;
            backgroundCaptureTarget = null;
            return;
        }
        GuiMotionBlurRenderer.captureBackground(minecraft.getFramebuffer(), radius);
    }

    @JvmStatic
    public static final void captureBackground(@Nullable Framebuffer target, float radius) {
        boolean mainCapture;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        boolean bl = mainCapture = target == minecraft.getFramebuffer();
        if (INSTANCE.isTemporarilyDisabled() || !(Math.abs(radius) <= Float.MAX_VALUE)) {
            INSTANCE.clearBackgroundCapture(mainCapture);
            return;
        }
        if (target == null || target.textureWidth <= 0 || target.textureHeight <= 0 || target.getColorAttachment() == null) {
            INSTANCE.clearBackgroundCapture(mainCapture);
            return;
        }
        if (!INSTANCE.ensureReady(target.textureWidth, target.textureHeight, 1.0f)) {
            INSTANCE.clearBackgroundCapture(mainCapture);
            return;
        }
        try {
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            GpuTexture gpuTexture = target.getColorAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = mainCapture ? backgroundCopyTexture : remoteBackgroundCopyTexture;
            Intrinsics.checkNotNull((Object)gpuTexture2);
            commandEncoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, target.textureWidth, target.textureHeight);
            if (mainCapture) {
                backgroundCaptured = true;
                backgroundCaptureTarget = target;
            } else {
                remoteBackgroundCaptured = true;
                remoteBackgroundCaptureTarget = target;
            }
        }
        catch (Throwable throwable) {
            INSTANCE.clearBackgroundCapture(mainCapture);
            INSTANCE.disableAfterError(throwable);
        }
    }

    @JvmStatic
    public static final void captureHudBackground(float radius) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.gameRenderer == null || minecraft.getFramebuffer() == null) {
            hudBackgroundCaptured = false;
            hudBackgroundCaptureTarget = null;
            return;
        }
        GuiMotionBlurRenderer.captureHudBackground(minecraft.getFramebuffer(), radius);
    }

    @JvmStatic
    public static final void captureHudBackground(@Nullable Framebuffer target, float radius) {
        if (INSTANCE.isTemporarilyDisabled() || !(Math.abs(radius) <= Float.MAX_VALUE)) {
            INSTANCE.clearHudBackgroundCapture();
            return;
        }
        if (target == null || target.textureWidth <= 0 || target.textureHeight <= 0 || target.getColorAttachment() == null) {
            INSTANCE.clearHudBackgroundCapture();
            return;
        }
        if (!INSTANCE.ensureReady(target.textureWidth, target.textureHeight, 1.0f)) {
            INSTANCE.clearHudBackgroundCapture();
            return;
        }
        try {
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            GpuTexture gpuTexture = target.getColorAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = hudBackgroundCopyTexture;
            Intrinsics.checkNotNull((Object)gpuTexture2);
            commandEncoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, target.textureWidth, target.textureHeight);
            hudBackgroundCaptured = true;
            hudBackgroundCaptureTarget = target;
        }
        catch (Throwable throwable) {
            INSTANCE.clearHudBackgroundCapture();
            INSTANCE.disableAfterError(throwable);
        }
    }

    @JvmStatic
    public static final void applyWithCopy(float opacity, float radius, int boundsX, int boundsY, int boundsWidth, int boundsHeight, @Nullable float[] maskRects, int maskRectCount, int sourceX, int sourceY, int sourceWidth, int sourceHeight, float scale, float originX, float originY) {
        GuiMotionBlurRenderer.applyWithCopy(opacity, radius, boundsX, boundsY, boundsWidth, boundsHeight, maskRects, maskRectCount, sourceX, sourceY, sourceWidth, sourceHeight, scale, originX, originY, false);
    }

    @JvmStatic
    public static final void applyWithCopy(float opacity, float radius, int boundsX, int boundsY, int boundsWidth, int boundsHeight, @Nullable float[] maskRects, int maskRectCount, int sourceX, int sourceY, int sourceWidth, int sourceHeight, float scale, float originX, float originY, boolean maskReplace) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.gameRenderer == null || minecraft.getFramebuffer() == null) {
            return;
        }
        GuiMotionBlurRenderer.applyWithCopy(minecraft.getFramebuffer(), opacity, radius, boundsX, boundsY, boundsWidth, boundsHeight, maskRects, maskRectCount, sourceX, sourceY, sourceWidth, sourceHeight, scale, originX, originY, maskReplace);
    }

    @JvmStatic
    public static final void applyWithCopy(@Nullable Framebuffer target, float opacity, float radius, int boundsX, int boundsY, int boundsWidth, int boundsHeight, @Nullable float[] maskRects, int maskRectCount, int sourceX, int sourceY, int sourceWidth, int sourceHeight, float scale, float originX, float originY) {
        GuiMotionBlurRenderer.applyWithCopy(target, opacity, radius, boundsX, boundsY, boundsWidth, boundsHeight, maskRects, maskRectCount, sourceX, sourceY, sourceWidth, sourceHeight, scale, originX, originY, false);
    }

    @JvmStatic
    public static final void applyWithCopy(@Nullable Framebuffer target, float opacity, float radius, int boundsX, int boundsY, int boundsWidth, int boundsHeight, @Nullable float[] maskRects, int maskRectCount, int sourceX, int sourceY, int sourceWidth, int sourceHeight, float scale, float originX, float originY, boolean maskReplace) {
        INSTANCE.applyCaptured(target, opacity, radius, boundsX, boundsY, boundsWidth, boundsHeight, maskRects, maskRectCount, sourceX, sourceY, sourceWidth, sourceHeight, scale, originX, originY, maskReplace, 0);
    }

    @JvmStatic
    public static final void applyHudWithCopy(float opacity, float radius, int boundsX, int boundsY, int boundsWidth, int boundsHeight, @Nullable float[] maskRects, int maskRectCount, int sourceX, int sourceY, int sourceWidth, int sourceHeight, float scale, float originX, float originY) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.gameRenderer == null || minecraft.getFramebuffer() == null) {
            return;
        }
        INSTANCE.applyCaptured(minecraft.getFramebuffer(), opacity, radius, boundsX, boundsY, boundsWidth, boundsHeight, maskRects, maskRectCount, sourceX, sourceY, sourceWidth, sourceHeight, scale, originX, originY, false, 1);
    }

    private final void applyCaptured(Framebuffer target, float opacity, float radius, int boundsX, int boundsY, int boundsWidth, int boundsHeight, float[] maskRects, int maskRectCount, int sourceX, int sourceY, int sourceWidth, int sourceHeight, float scale, float originX, float originY, boolean maskReplace, int captureKind) {
        if (this.isTemporarilyDisabled()) {
            return;
        }
        float safeOpacity = this.clamp(opacity, 0.0f, 1.0f);
        float safeScale = Math.abs(scale) <= Float.MAX_VALUE ? this.clamp(scale, 0.25f, 4.0f) : 1.0f;
        boolean scaleActive = Math.abs(safeScale - 1.0f) > 5.0E-4f;
        int safeMaskRectCount = this.safeMaskRectCount(maskRects, maskRectCount);
        if (!scaleActive && safeOpacity <= 0.002f || !(Math.abs(radius) <= Float.MAX_VALUE) || safeMaskRectCount <= 0) {
            return;
        }
        if (target == null || target.textureWidth <= 0 || target.textureHeight <= 0 || target.getColorAttachment() == null || target.getColorAttachmentView() == null) {
            return;
        }
        int x0 = this.clampInt(boundsX, 0, target.textureWidth);
        int y0 = this.clampInt(boundsY, 0, target.textureHeight);
        int x1 = this.clampInt(boundsX + boundsWidth, x0, target.textureWidth);
        int y1 = this.clampInt(boundsY + boundsHeight, y0, target.textureHeight);
        int clippedWidth = x1 - x0;
        int clippedHeight = y1 - y0;
        if (clippedWidth <= 0 || clippedHeight <= 0) {
            return;
        }
        int sourceClippedX = scaleActive ? this.clampInt(sourceX, 0, target.textureWidth) : 0;
        int sourceClippedY = scaleActive ? this.clampInt(sourceY, 0, target.textureHeight) : 0;
        int sourceClippedRight = scaleActive ? this.clampInt(sourceX + sourceWidth, sourceClippedX, target.textureWidth) : 0;
        int sourceClippedBottom = scaleActive ? this.clampInt(sourceY + sourceHeight, sourceClippedY, target.textureHeight) : 0;
        int sourceClippedWidth = sourceClippedRight - sourceClippedX;
        int sourceClippedHeight = sourceClippedBottom - sourceClippedY;
        if (scaleActive && (sourceClippedWidth <= 0 || sourceClippedHeight <= 0)) {
            return;
        }
        float safeRadius = this.clamp(radius, 0.0f, 48.0f);
        if (!this.ensureReady(target.textureWidth, target.textureHeight, 1.0f)) {
            return;
        }
        boolean captureAvailable = captureKind == 1 ? hudBackgroundCaptured && target == hudBackgroundCaptureTarget : (backgroundCaptured && target == backgroundCaptureTarget || remoteBackgroundCaptured && target == remoteBackgroundCaptureTarget);
        if (!captureAvailable) {
            return;
        }
        boolean mainCapture = backgroundCaptured && target == backgroundCaptureTarget;
        boolean remoteCapture = remoteBackgroundCaptured && target == remoteBackgroundCaptureTarget;
        GpuTextureView capturedBackground = captureKind == 1 ? hudBackgroundCopyTextureView : (mainCapture ? backgroundCopyTextureView : remoteBackgroundCopyTextureView);
        if (captureKind == 1) {
            this.clearHudBackgroundCapture();
        } else {
            this.clearBackgroundCapture(mainCapture);
        }
        try {
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            GpuTexture gpuTexture = target.getColorAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = sceneCopyTexture;
            Intrinsics.checkNotNull((Object)gpuTexture2);
            encoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, target.textureWidth, target.textureHeight);
            int n = target.textureWidth;
            int n2 = target.textureHeight;
            SimpleFramebuffer simpleFramebuffer2 = horizontalTarget;
            Intrinsics.checkNotNull((Object)simpleFramebuffer2);
            SimpleFramebuffer simpleFramebuffer3 = verticalTarget;
            Intrinsics.checkNotNull((Object)simpleFramebuffer3);
            this.gaussianChain(encoder, sceneCopyTextureView, n, n2, simpleFramebuffer2, simpleFramebuffer3, safeRadius, x0, y0, clippedWidth, clippedHeight);
            int n3 = target.textureWidth;
            int n4 = target.textureHeight;
            SimpleFramebuffer simpleFramebuffer4 = backgroundHorizontalTarget;
            Intrinsics.checkNotNull((Object)simpleFramebuffer4);
            SimpleFramebuffer simpleFramebuffer5 = backgroundVerticalTarget;
            Intrinsics.checkNotNull((Object)simpleFramebuffer5);
            this.gaussianChain(encoder, capturedBackground, n3, n4, simpleFramebuffer4, simpleFramebuffer5, safeRadius, x0, y0, clippedWidth, clippedHeight);
            this.renderMask(encoder, maskRects, safeMaskRectCount, target.textureWidth, target.textureHeight, maskReplace);
            GpuTextureView gpuTextureView2 = target.getColorAttachmentView();
            SimpleFramebuffer simpleFramebuffer6 = verticalTarget;
            Intrinsics.checkNotNull((Object)simpleFramebuffer6);
            GpuTextureView gpuTextureView3 = simpleFramebuffer6.getColorAttachmentView();
            SimpleFramebuffer simpleFramebuffer7 = backgroundVerticalTarget;
            Intrinsics.checkNotNull((Object)simpleFramebuffer7);
            GpuTextureView gpuTextureView4 = simpleFramebuffer7.getColorAttachmentView();
            SimpleFramebuffer simpleFramebuffer8 = maskTarget;
            Intrinsics.checkNotNull((Object)simpleFramebuffer8);
            this.composite(encoder, gpuTextureView2, sceneCopyTextureView, gpuTextureView3, capturedBackground, gpuTextureView4, simpleFramebuffer8.getColorAttachmentView(), safeOpacity, safeRadius, x0, y0, clippedWidth, clippedHeight, target.textureWidth, target.textureHeight, maskRects, sourceClippedX, sourceClippedY, sourceClippedWidth, sourceClippedHeight, safeScale, originX, originY);
        }
        catch (Throwable throwable) {
            this.disableAfterError(throwable);
        }
    }

    @JvmStatic
    public static final void shutdown() {
        INSTANCE.releaseResources();
        disabledAfterError = false;
        retryAfterErrorNanos = 0L;
    }

    private final void releaseResources() {
        this.closeTargets();
        this.closeBuffer(maskVertexBuffer);
        this.closeBuffer(blurUniformBuffer);
        this.closeBuffer(compositeUniformBuffer);
        maskVertexBuffer = null;
        blurUniformBuffer = null;
        compositeUniformBuffer = null;
        fullscreenVertexBuffer = null;
    }

    private final boolean ensureReady(int width, int height, float renderScale) {
        boolean bl;
        try {
            GpuBuffer compositeUniform;
            GpuBuffer blurUniform;
            GpuBuffer maskVertices;
            GpuBuffer fullscreen;
            GpuDevice gpuDevice = RenderSystem.tryGetDevice();
            if (gpuDevice == null) {
                return false;
            }
            GpuDevice device = gpuDevice;
            if (!pipelinesRegistered) {
                RenderPipelines.register((RenderPipeline)BLUR_PIPELINE);
                RenderPipelines.register((RenderPipeline)MASK_PIPELINE);
                RenderPipelines.register((RenderPipeline)MASK_REPLACE_PIPELINE);
                RenderPipelines.register((RenderPipeline)COMPOSITE_PIPELINE);
                pipelinesRegistered = true;
            }
            if ((fullscreen = fullscreenVertexBuffer) == null || fullscreen.isClosed()) {
                fullscreenVertexBuffer = FullscreenQuadBuffer.getOrCreate();
            }
            if ((maskVertices = maskVertexBuffer) == null || maskVertices.isClosed() || maskVertices.size() < (long)MASK_VERTEX_BYTES) {
                this.closeBuffer(maskVertexBuffer);
                maskVertexBuffer = device.createBuffer(GuiMotionBlurRenderer::ensureReady$lambda$0, 40, (long)MASK_VERTEX_BYTES);
            }
            if ((blurUniform = blurUniformBuffer) == null || blurUniform.isClosed() || blurUniform.size() < 16L) {
                this.closeBuffer(blurUniformBuffer);
                blurUniformBuffer = device.createBuffer(GuiMotionBlurRenderer::ensureReady$lambda$1, 136, 16L);
            }
            if ((compositeUniform = compositeUniformBuffer) == null || compositeUniform.isClosed() || compositeUniform.size() < 80L) {
                this.closeBuffer(compositeUniformBuffer);
                compositeUniformBuffer = device.createBuffer(GuiMotionBlurRenderer::ensureReady$lambda$2, 136, 80L);
            }
            this.ensureTargets(device, width, height, renderScale);
            bl = fullscreenVertexBuffer != null && maskVertexBuffer != null && blurUniformBuffer != null && compositeUniformBuffer != null && sceneCopyTextureView != null && backgroundCopyTextureView != null && remoteBackgroundCopyTextureView != null && hudBackgroundCopyTextureView != null && maskTarget != null && horizontalTarget != null && verticalTarget != null && backgroundHorizontalTarget != null && backgroundVerticalTarget != null;
        }
        catch (Throwable throwable) {
            this.disableAfterError(throwable);
            bl = false;
        }
        return bl;
    }

    private final void ensureTargets(GpuDevice device, int width, int height, float renderScale) {
        SimpleFramebuffer mask = maskTarget;
        if (sceneCopyTextureView != null && backgroundCopyTextureView != null && remoteBackgroundCopyTextureView != null && hudBackgroundCopyTextureView != null && sourceWidth == width && sourceHeight == height && blurWidth == width && blurHeight == height && mask != null && mask.textureWidth == width && mask.textureHeight == height) {
            return;
        }
        this.closeTargets();
        GpuTexture gpuTexture = sceneCopyTexture = device.createTexture(GuiMotionBlurRenderer::ensureTargets$lambda$0, 5, TextureFormat.RGBA8, width, height, 1, 1);
        Intrinsics.checkNotNull((Object)gpuTexture);
        sceneCopyTextureView = device.createTextureView(gpuTexture);
        GpuTexture gpuTexture2 = backgroundCopyTexture = device.createTexture(GuiMotionBlurRenderer::ensureTargets$lambda$1, 5, TextureFormat.RGBA8, width, height, 1, 1);
        Intrinsics.checkNotNull((Object)gpuTexture2);
        backgroundCopyTextureView = device.createTextureView(gpuTexture2);
        GpuTexture gpuTexture3 = remoteBackgroundCopyTexture = device.createTexture(GuiMotionBlurRenderer::ensureTargets$lambda$2, 5, TextureFormat.RGBA8, width, height, 1, 1);
        Intrinsics.checkNotNull((Object)gpuTexture3);
        remoteBackgroundCopyTextureView = device.createTextureView(gpuTexture3);
        GpuTexture gpuTexture4 = hudBackgroundCopyTexture = device.createTexture(GuiMotionBlurRenderer::ensureTargets$lambda$3, 5, TextureFormat.RGBA8, width, height, 1, 1);
        Intrinsics.checkNotNull((Object)gpuTexture4);
        hudBackgroundCopyTextureView = device.createTextureView(gpuTexture4);
        maskTarget = new SimpleFramebuffer("kimiko_gui_motion_blur_mask", width, height, false);
        horizontalTarget = new SimpleFramebuffer("kimiko_gui_motion_blur_scene_h", width, height, false);
        verticalTarget = new SimpleFramebuffer("kimiko_gui_motion_blur_scene_result", width, height, false);
        backgroundHorizontalTarget = new SimpleFramebuffer("kimiko_gui_motion_blur_bg_h", width, height, false);
        backgroundVerticalTarget = new SimpleFramebuffer("kimiko_gui_motion_blur_bg_result", width, height, false);
        sourceWidth = width;
        sourceHeight = height;
        blurWidth = width;
        blurHeight = height;
    }

    private final void gaussianChain(CommandEncoder encoder, GpuTextureView src, int width, int height, SimpleFramebuffer horizontal, SimpleFramebuffer result, float radius, int boundsX, int boundsY, int boundsWidth, int boundsHeight) {
        float stepPx = radius / 16.0f;
        int pad = (int)Math.ceil(radius) + 8;
        int rx = this.clampInt(boundsX - pad, 0, width);
        int ry = this.clampInt(boundsY - pad, 0, height);
        int rr = this.clampInt(boundsX + boundsWidth + pad, rx, width);
        int rb = this.clampInt(boundsY + boundsHeight + pad, ry, height);
        if (rr - rx <= 0 || rb - ry <= 0) {
            return;
        }
        this.gaussianPass(encoder, src, horizontal, stepPx / (float)Math.max(1, width), 0.0f, rx, ry, rr - rx, rb - ry);
        this.gaussianPass(encoder, horizontal.getColorAttachmentView(), result, 0.0f, stepPx / (float)Math.max(1, height), rx, ry, rr - rx, rb - ry);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void gaussianPass(CommandEncoder encoder, GpuTextureView source, SimpleFramebuffer target, float stepX, float stepY, int regionX, int regionY, int regionWidth, int regionHeight) {
        if (source == null || target == null || target.getColorAttachmentView() == null) {
            return;
        }
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(16);
            data.putFloat(0, stepX);
            data.putFloat(4, stepY);
            data.position(0);
            GpuBuffer gpuBuffer = blurUniformBuffer;
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
        Supplier<String> supplier = GuiMotionBlurRenderer::gaussianPass$lambda$1;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty(), null, OptionalDouble.empty());
        throwable = null;
        try {
            RenderPass renderPass = (RenderPass)autoCloseable;
            boolean bl = false;
            renderPass.setPipeline(BLUR_PIPELINE);
            GpuBuffer gpuBuffer = fullscreenVertexBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            renderPass.setVertexBuffer(0, gpuBuffer);
            int scissorY = Math.max(0, target.textureHeight - (regionY + regionHeight));
            renderPass.enableScissor(regionX, scissorY, regionWidth, regionHeight);
            renderPass.bindTexture("MotionInput", source, RenderSampler.linear());
            GpuBuffer gpuBuffer2 = blurUniformBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer2);
            renderPass.setUniform("MotionBlurParams", gpuBuffer2.slice(0L, 16L));
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
    private final void renderMask(CommandEncoder encoder, float[] rects, int rectCount, int targetWidth, int targetHeight, boolean replace) {
        SimpleFramebuffer mask = maskTarget;
        if (rects == null || mask == null || mask.getColorAttachmentView() == null || maskVertexBuffer == null) {
            return;
        }
        int vertexCount = 0;
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.malloc(rectCount * 6 * MASK_VERTEX_STRIDE_BYTES);
            Intrinsics.checkNotNull((Object)data);
            vertexCount = INSTANCE.writeMaskVertices(data, rects, rectCount, targetWidth, targetHeight);
            if (vertexCount > 0) {
                data.flip();
                GpuBuffer gpuBuffer = maskVertexBuffer;
                Intrinsics.checkNotNull((Object)gpuBuffer);
                encoder.writeToBuffer(gpuBuffer.slice(0L, (long)data.remaining()), data);
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
        Supplier<String> supplier = GuiMotionBlurRenderer::renderMask$lambda$1;
        GpuTextureView gpuTextureView = mask.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0), null, OptionalDouble.empty());
        throwable = null;
        try {
            RenderPass renderPass = (RenderPass)autoCloseable;
            boolean bl = false;
            if (vertexCount > 0) {
                renderPass.setPipeline(replace ? MASK_REPLACE_PIPELINE : MASK_PIPELINE);
                GpuBuffer gpuBuffer = maskVertexBuffer;
                Intrinsics.checkNotNull((Object)gpuBuffer);
                renderPass.setVertexBuffer(0, gpuBuffer);
                renderPass.draw(0, vertexCount);
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

    private final int writeMaskVertices(ByteBuffer data, float[] rects, int rectCount, int targetWidth, int targetHeight) {
        int vertexCount = 0;
        int safeCount = this.safeMaskRectCount(rects, rectCount);
        float safeTargetWidth = Math.max(1.0f, (float)targetWidth);
        float safeTargetHeight = Math.max(1.0f, (float)targetHeight);
        for (int i = 0; i < safeCount; ++i) {
            int offset = i * 6;
            if (rects[offset + 4] < 0.0f) continue;
            float rectW = rects[offset + 2];
            float rectH = rects[offset + 3];
            float presence = this.clamp(rects[offset + 4], 0.0f, 1.0f);
            float blurMix = this.clamp(rects[offset + 5], 0.0f, 1.0f);
            if (rectW <= 0.5f || rectH <= 0.5f) continue;
            float left = rects[offset] - 8.0f;
            float top = rects[offset + 1] - 8.0f;
            float right = rects[offset] + rectW + 8.0f;
            float bottom = rects[offset + 1] + rectH + 8.0f;
            float x0 = left / safeTargetWidth * 2.0f - 1.0f;
            float x1 = right / safeTargetWidth * 2.0f - 1.0f;
            float y0 = 1.0f - top / safeTargetHeight * 2.0f;
            float y1 = 1.0f - bottom / safeTargetHeight * 2.0f;
            float radius = Math.min(8.0f, Math.min(rectW, rectH) * 0.5f);
            float lx0 = -8.0f;
            float ly0 = -8.0f;
            float lx1 = rectW + 8.0f;
            float ly1 = rectH + 8.0f;
            int halfW = Math.min(Short.MAX_VALUE, Math.round(rectW * 8.0f));
            int halfH = Math.min(Short.MAX_VALUE, Math.round(rectH * 8.0f));
            int pr = Math.round(presence * 255.0f);
            int bm = Math.round(blurMix * 255.0f);
            this.putVertex(data, x0, y1, radius, lx0, ly1, halfW, halfH, pr, bm);
            this.putVertex(data, x1, y1, radius, lx1, ly1, halfW, halfH, pr, bm);
            this.putVertex(data, x1, y0, radius, lx1, ly0, halfW, halfH, pr, bm);
            this.putVertex(data, x0, y1, radius, lx0, ly1, halfW, halfH, pr, bm);
            this.putVertex(data, x1, y0, radius, lx1, ly0, halfW, halfH, pr, bm);
            this.putVertex(data, x0, y0, radius, lx0, ly0, halfW, halfH, pr, bm);
            vertexCount += 6;
        }
        return vertexCount;
    }

    private final void putVertex(ByteBuffer data, float x, float y, float radius, float localX, float localY, int halfW, int halfH, int presence, int blurMix) {
        data.putFloat(x);
        data.putFloat(y);
        data.putFloat(radius);
        data.putFloat(localX);
        data.putFloat(localY);
        data.putShort((short)halfW);
        data.putShort((short)halfH);
        data.put((byte)presence);
        data.put((byte)blurMix);
        data.put((byte)0);
        data.put((byte)-1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void composite(CommandEncoder encoder, GpuTextureView target, GpuTextureView scene, GpuTextureView blur, GpuTextureView background, GpuTextureView backgroundBlur, GpuTextureView mask, float opacity, float radius, int boundsX, int boundsY, int boundsWidth, int boundsHeight, int targetWidth, int targetHeight, float[] maskRects, int sourceX, int sourceY, int sourceWidth, int sourceHeight, float scale, float originX, float originY) {
        if (target == null || scene == null || blur == null || background == null || backgroundBlur == null || mask == null) {
            return;
        }
        int scissorY = Math.max(0, targetHeight - (boundsY + boundsHeight));
        this.writeCompositeUniform(encoder, opacity, radius, targetWidth, targetHeight, maskRects, sourceX, sourceY, sourceWidth, sourceHeight, scale, originX, originY);
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(GuiMotionBlurRenderer::composite$lambda$0, target, OptionalInt.empty(), null, OptionalDouble.empty());
        Throwable throwable = null;
        try {
            RenderPass renderPass = (RenderPass)autoCloseable;
            boolean bl = false;
            renderPass.setPipeline(COMPOSITE_PIPELINE);
            GpuBuffer gpuBuffer = fullscreenVertexBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            renderPass.setVertexBuffer(0, gpuBuffer);
            renderPass.enableScissor(boundsX, scissorY, boundsWidth, boundsHeight);
            renderPass.bindTexture("MotionScene", scene, RenderSampler.linear());
            renderPass.bindTexture("MotionBackground", background, RenderSampler.linear());
            renderPass.bindTexture("MotionBlurred", blur, RenderSampler.linear());
            renderPass.bindTexture("MotionBackgroundBlurred", backgroundBlur, RenderSampler.linear());
            renderPass.bindTexture("MotionMask", mask, RenderSampler.linear());
            GpuBuffer gpuBuffer2 = compositeUniformBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer2);
            renderPass.setUniform("MotionCompositeParams", gpuBuffer2.slice());
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
    private final void writeCompositeUniform(CommandEncoder encoder, float opacity, float radius, int targetWidth, int targetHeight, float[] maskRects, int sourceX, int sourceY, int sourceWidth, int sourceHeight, float scale, float originX, float originY) {
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(80);
            float rectX = 0.0f;
            float rectY = 0.0f;
            float rectW = targetWidth;
            float rectH = targetHeight;
            if (maskRects != null && maskRects.length >= 4) {
                rectX = maskRects[0];
                rectY = maskRects[1];
                rectW = Math.max(0.0f, maskRects[2]);
                rectH = Math.max(0.0f, maskRects[3]);
            }
            data.putFloat(0, opacity);
            data.putFloat(4, 0.035f);
            data.putFloat(8, 1.0f);
            data.putFloat(12, 10.0f);
            data.putFloat(16, rectX);
            data.putFloat(20, rectY);
            data.putFloat(24, rectW);
            data.putFloat(28, rectH);
            data.putFloat(32, targetWidth);
            data.putFloat(36, targetHeight);
            data.putFloat(40, 18.0f);
            data.putFloat(44, scale);
            data.putFloat(48, sourceX);
            data.putFloat(52, sourceY);
            data.putFloat(56, Math.max(0, sourceWidth));
            data.putFloat(60, Math.max(0, sourceHeight));
            data.putFloat(64, originX);
            data.putFloat(68, originY);
            data.putFloat(72, Math.max(0.0f, radius));
            data.putFloat(76, 0.0f);
            data.position(0);
            GpuBuffer gpuBuffer = compositeUniformBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 80L), data);
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

    private final float renderScale(float radius) {
        return this.clamp(0.56f - radius * 0.012f, 0.42f, 0.56f);
    }

    private final void closeTargets() {
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
        GpuTextureView gpuTextureView2 = backgroundCopyTextureView;
        if (gpuTextureView2 != null) {
            gpuTextureView2.close();
        }
        backgroundCopyTextureView = null;
        GpuTexture gpuTexture2 = backgroundCopyTexture;
        if (gpuTexture2 != null) {
            gpuTexture2.close();
        }
        backgroundCopyTexture = null;
        GpuTextureView gpuTextureView3 = remoteBackgroundCopyTextureView;
        if (gpuTextureView3 != null) {
            gpuTextureView3.close();
        }
        remoteBackgroundCopyTextureView = null;
        GpuTexture gpuTexture3 = remoteBackgroundCopyTexture;
        if (gpuTexture3 != null) {
            gpuTexture3.close();
        }
        remoteBackgroundCopyTexture = null;
        GpuTextureView gpuTextureView4 = hudBackgroundCopyTextureView;
        if (gpuTextureView4 != null) {
            gpuTextureView4.close();
        }
        hudBackgroundCopyTextureView = null;
        GpuTexture gpuTexture4 = hudBackgroundCopyTexture;
        if (gpuTexture4 != null) {
            gpuTexture4.close();
        }
        hudBackgroundCopyTexture = null;
        SimpleFramebuffer simpleFramebuffer2 = maskTarget;
        if (simpleFramebuffer2 != null) {
            simpleFramebuffer2.delete();
        }
        maskTarget = null;
        SimpleFramebuffer simpleFramebuffer3 = horizontalTarget;
        if (simpleFramebuffer3 != null) {
            simpleFramebuffer3.delete();
        }
        horizontalTarget = null;
        SimpleFramebuffer simpleFramebuffer4 = backgroundHorizontalTarget;
        if (simpleFramebuffer4 != null) {
            simpleFramebuffer4.delete();
        }
        backgroundHorizontalTarget = null;
        SimpleFramebuffer simpleFramebuffer5 = verticalTarget;
        if (simpleFramebuffer5 != null) {
            simpleFramebuffer5.delete();
        }
        verticalTarget = null;
        SimpleFramebuffer simpleFramebuffer6 = backgroundVerticalTarget;
        if (simpleFramebuffer6 != null) {
            simpleFramebuffer6.delete();
        }
        backgroundVerticalTarget = null;
        sourceWidth = -1;
        sourceHeight = -1;
        blurWidth = -1;
        blurHeight = -1;
        backgroundCaptured = false;
        backgroundCaptureTarget = null;
        remoteBackgroundCaptured = false;
        remoteBackgroundCaptureTarget = null;
        hudBackgroundCaptured = false;
        hudBackgroundCaptureTarget = null;
    }

    private final void clearBackgroundCapture(boolean mainCapture) {
        if (mainCapture) {
            backgroundCaptured = false;
            backgroundCaptureTarget = null;
        } else {
            remoteBackgroundCaptured = false;
            remoteBackgroundCaptureTarget = null;
        }
    }

    private final void clearHudBackgroundCapture() {
        hudBackgroundCaptured = false;
        hudBackgroundCaptureTarget = null;
    }

    private final void closeBuffer(GpuBuffer buffer) {
        block0: {
            GpuBuffer gpuBuffer = buffer;
            if (gpuBuffer == null) break block0;
            gpuBuffer.close();
        }
    }

    private final void disableAfterError(Throwable throwable) {
        backgroundCaptured = false;
        backgroundCaptureTarget = null;
        remoteBackgroundCaptured = false;
        remoteBackgroundCaptureTarget = null;
        this.clearHudBackgroundCapture();
        this.releaseResources();
        disabledAfterError = true;
        retryAfterErrorNanos = System.nanoTime() + 250000000L;
    }

    private final boolean isTemporarilyDisabled() {
        if (!disabledAfterError) {
            return false;
        }
        if (System.nanoTime() < retryAfterErrorNanos) {
            return true;
        }
        disabledAfterError = false;
        retryAfterErrorNanos = 0L;
        return false;
    }

    private final int safeMaskRectCount(float[] rects, int rectCount) {
        if (rects == null || rectCount <= 0) {
            return 0;
        }
        return Math.min(Math.min(rectCount, 192), rects.length / 6);
    }

    private final float clamp(float value, float min, float max) {
        if (max < min) {
            return min;
        }
        return Math.max(min, Math.min(max, value));
    }

    private final int clampInt(int value, int min, int max) {
        if (max < min) {
            return min;
        }
        return Math.max(min, Math.min(max, value));
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }

    private static final String ensureReady$lambda$0() {
        return "kimiko:gui_motion_blur_mask_vertices";
    }

    private static final String ensureReady$lambda$1() {
        return "kimiko:gui_motion_blur_uniform";
    }

    private static final String ensureReady$lambda$2() {
        return "kimiko:gui_motion_blur_composite_uniform";
    }

    private static final String ensureTargets$lambda$0() {
        return "kimiko:gui_motion_blur_scene_copy";
    }

    private static final String ensureTargets$lambda$1() {
        return "kimiko:gui_motion_blur_background_copy";
    }

    private static final String ensureTargets$lambda$2() {
        return "kimiko:gui_motion_blur_remote_background_copy";
    }

    private static final String ensureTargets$lambda$3() {
        return "kimiko:gui_motion_blur_hud_background_copy";
    }

    private static final String gaussianPass$lambda$1() {
        return "kimiko:gui_motion_blur_gaussian";
    }

    private static final String renderMask$lambda$1() {
        return "kimiko:gui_motion_blur_mask";
    }

    private static final String composite$lambda$0() {
        return "kimiko:gui_motion_blur_composite";
    }

    static {
        VertexFormat vertexFormat = VertexFormat.builder().add("Position", VertexFormatElement.POSITION).add("UV0", VertexFormatElement.UV0).add("UV1", VertexFormatElement.UV1).add("Color", VertexFormatElement.COLOR).build();
        Intrinsics.checkNotNullExpressionValue((Object)vertexFormat, (String)"build(...)");
        MASK_FORMAT = vertexFormat;
        MASK_VERTEX_STRIDE_BYTES = MASK_FORMAT.getVertexSize();
        MASK_VERTEX_BYTES = 1152 * MASK_VERTEX_STRIDE_BYTES;
        BLUR_PIPELINE_ID = INSTANCE.id("pipeline/post/guimotionblur/gaussian");
        MASK_PIPELINE_ID = INSTANCE.id("pipeline/post/guimotionblur/mask");
        MASK_REPLACE_PIPELINE_ID = INSTANCE.id("pipeline/post/guimotionblur/mask_replace");
        COMPOSITE_PIPELINE_ID = INSTANCE.id("pipeline/post/guimotionblur/composite");
        FULLSCREEN_SHADER = INSTANCE.id("post/guimotionblur/fullscreen");
        GAUSSIAN_SHADER = INSTANCE.id("post/guimotionblur/gaussian");
        MASK_SHADER = INSTANCE.id("post/guimotionblur/mask");
        COMPOSITE_SHADER = INSTANCE.id("post/guimotionblur/composite");
        RenderPipeline renderPipeline = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(BLUR_PIPELINE_ID).withVertexShader(FULLSCREEN_SHADER).withFragmentShader(GAUSSIAN_SHADER).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.TRIANGLES).withSampler("MotionInput").withUniform("MotionBlurParams", UniformType.UNIFORM_BUFFER).withoutBlend().withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"build(...)");
        BLUR_PIPELINE = renderPipeline;
        RenderPipeline renderPipeline2 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(MASK_PIPELINE_ID).withVertexShader(MASK_SHADER).withFragmentShader(MASK_SHADER).withVertexFormat(MASK_FORMAT, VertexFormat.DrawMode.TRIANGLES).withBlend(new BlendFunction(SourceFactor.ONE, DestFactor.ONE)).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline2, (String)"build(...)");
        MASK_PIPELINE = renderPipeline2;
        RenderPipeline renderPipeline3 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(MASK_REPLACE_PIPELINE_ID).withVertexShader(MASK_SHADER).withFragmentShader(MASK_SHADER).withVertexFormat(MASK_FORMAT, VertexFormat.DrawMode.TRIANGLES).withoutBlend().withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline3, (String)"build(...)");
        MASK_REPLACE_PIPELINE = renderPipeline3;
        RenderPipeline renderPipeline4 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(COMPOSITE_PIPELINE_ID).withVertexShader(FULLSCREEN_SHADER).withFragmentShader(COMPOSITE_SHADER).withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.TRIANGLES).withSampler("MotionScene").withSampler("MotionBackground").withSampler("MotionBlurred").withSampler("MotionBackgroundBlurred").withSampler("MotionMask").withUniform("MotionCompositeParams", UniformType.UNIFORM_BUFFER).withoutBlend().withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline4, (String)"build(...)");
        COMPOSITE_PIPELINE = renderPipeline4;
        sourceWidth = -1;
        sourceHeight = -1;
        blurWidth = -1;
        blurHeight = -1;
    }
}

