/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.buffers.GpuBuffer$MappedView
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
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.client.gl.GpuSampler
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gl.SimpleFramebuffer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.system.MemoryStack
 */
package rtx.kimiko.utils.render.modules.post.shaderhands;

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
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.SimpleFramebuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryStack;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.others.RenderSampler;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.render.render2d.ThemeWaveUniform;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\u0015\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b6\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\bm\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001f\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u001b\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0011H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u0011H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u0011H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0016\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0015J\u000f\u0010\u0018\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0015J3\u0010\u001f\u001a\u00020\u00062\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001b\u001a\u00020\f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001e\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b!\u0010\u0015J\u0013\u0010\"\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\"\u0010\u0003J3\u0010(\u001a\u00020\u00112\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020#H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b(\u0010)J3\u0010*\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020#H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b*\u0010+J5\u0010,\u001a\u0004\u0018\u00010\u00042\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020#H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b,\u0010-JE\u00100\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010/2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020#2\b\u0010.\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b0\u00101J/\u00106\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00020#2\u0006\u00104\u001a\u00020#2\u0006\u00105\u001a\u00020#H\u0002\u00a2\u0006\u0004\b6\u00107J\u001f\u00109\u001a\u00020#2\u0006\u00108\u001a\u00020#2\u0006\u00104\u001a\u00020#H\u0002\u00a2\u0006\u0004\b9\u0010:J\u001f\u0010<\u001a\u00020#2\u0006\u0010;\u001a\u00020#2\u0006\u00105\u001a\u00020#H\u0002\u00a2\u0006\u0004\b<\u0010:J'\u0010A\u001a\u00020\f2\u0006\u0010>\u001a\u00020=2\u0006\u0010?\u001a\u00020\f2\u0006\u0010@\u001a\u00020\fH\u0002\u00a2\u0006\u0004\bA\u0010BJ\u0013\u0010C\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bC\u0010\u0003J\u0013\u0010D\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bD\u0010\u0003J\u0013\u0010E\u001a\u00020\u0011H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bE\u0010\u0015J\u0015\u0010F\u001a\u0004\u0018\u00010\u0019H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bF\u0010GJ\u0015\u0010H\u001a\u0004\u0018\u00010\u0019H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bH\u0010GJ\u0015\u0010I\u001a\u0004\u0018\u00010\u0019H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bI\u0010GJ\u0015\u0010J\u001a\u0004\u0018\u00010\u0019H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bJ\u0010GJ}\u0010X\u001a\u00020\u00062\u0006\u0010K\u001a\u00020\f2\b\u0010L\u001a\u0004\u0018\u00010=2\u0006\u0010M\u001a\u00020\u00112\u0006\u0010N\u001a\u00020\u00112\u0006\u0010O\u001a\u00020\f2\u0006\u0010P\u001a\u00020#2\u0006\u0010Q\u001a\u00020#2\u0006\u0010R\u001a\u00020#2\u0006\u0010S\u001a\u00020\u00112\u0006\u0010T\u001a\u00020#2\u0006\u0010U\u001a\u00020#2\u0006\u0010V\u001a\u00020#2\u0006\u0010W\u001a\u00020#H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bX\u0010YJ-\u0010^\u001a\u00020\u00062\b\u0010[\u001a\u0004\u0018\u00010Z2\u0006\u0010\\\u001a\u00020\f2\u0006\u0010]\u001a\u00020\fH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b^\u0010_J\u000f\u0010`\u001a\u00020#H\u0002\u00a2\u0006\u0004\b`\u0010aJ\u000f\u0010b\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bb\u0010\u0003J\u001f\u0010d\u001a\u00020\u00062\u0006\u0010c\u001a\u00020=2\u0006\u0010\u001e\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bd\u0010eJ7\u0010k\u001a\u00020\u00062\u0006\u0010f\u001a\u00020\u00042\u0006\u0010g\u001a\u00020#2\u0006\u0010h\u001a\u00020#2\u0006\u0010i\u001a\u00020#2\u0006\u0010j\u001a\u00020#H\u0002\u00a2\u0006\u0004\bk\u0010lJ!\u0010n\u001a\u00020\u00062\b\u0010m\u001a\u0004\u0018\u00010\u001d2\u0006\u0010f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bn\u0010oJ\u0013\u0010p\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bp\u0010\u0003J\u0013\u0010q\u001a\u00020\u0011H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bq\u0010\u0015J'\u0010v\u001a\u00020\u00062\u0006\u0010r\u001a\u00020\f2\u0006\u0010s\u001a\u00020\f2\u0006\u0010u\u001a\u00020tH\u0002\u00a2\u0006\u0004\bv\u0010wJ'\u0010{\u001a\u00020\f2\u0006\u0010x\u001a\u00020\f2\u0006\u0010y\u001a\u00020\f2\u0006\u0010z\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b{\u0010|JV\u0010\u0082\u0001\u001a\u00020\u00062\u0006\u0010[\u001a\u00020Z2\b\u0010}\u001a\u0004\u0018\u00010\u00192\u0006\u0010m\u001a\u00020\u001d2\u0006\u0010~\u001a\u00020#2\u0006\u0010\u007f\u001a\u00020#2\u0006\u0010u\u001a\u00020t2\u0007\u0010\u0080\u0001\u001a\u00020\f2\u0007\u0010\u0081\u0001\u001a\u00020\fH\u0002\u00a2\u0006\u0006\b\u0082\u0001\u0010\u0083\u0001J}\u0010\u008d\u0001\u001a\u00020\u00062\b\u0010m\u001a\u0004\u0018\u00010\u00192\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u00192\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u00192\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u00192\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u00192\u0007\u0010\u0088\u0001\u001a\u00020\f2\u0007\u0010\u0089\u0001\u001a\u00020#2\u0007\u0010\u008a\u0001\u001a\u00020#2\u0007\u0010\u008b\u0001\u001a\u00020#2\u0007\u0010\u008c\u0001\u001a\u00020#2\u0006\u0010u\u001a\u00020tH\u0002\u00a2\u0006\u0006\b\u008d\u0001\u0010\u008e\u0001Jn\u0010\u0093\u0001\u001a\u00020\u00062\b\u0010}\u001a\u0004\u0018\u00010\u00192\u0006\u0010m\u001a\u00020\u001d2\u0007\u0010\u008f\u0001\u001a\u00020#2\u0007\u0010\u0090\u0001\u001a\u00020#2\u0006\u0010P\u001a\u00020#2\u0007\u0010\u0091\u0001\u001a\u00020=2\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u00192\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u00192\u0006\u0010u\u001a\u00020t2\u0007\u0010\u0092\u0001\u001a\u00020\u0011H\u0002\u00a2\u0006\u0006\b\u0093\u0001\u0010\u0094\u0001Jf\u0010\u0096\u0001\u001a\u00020\u00062\b\u0010}\u001a\u0004\u0018\u00010\u00192\u0006\u0010m\u001a\u00020\u001d2\u0007\u0010\u008f\u0001\u001a\u00020#2\u0007\u0010\u0090\u0001\u001a\u00020#2\u0007\u0010\u0095\u0001\u001a\u00020#2\u0007\u0010\u0091\u0001\u001a\u00020=2\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u00192\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u00192\u0006\u0010u\u001a\u00020tH\u0002\u00a2\u0006\u0006\b\u0096\u0001\u0010\u0097\u0001J|\u0010\u009b\u0001\u001a\u00020\u00062\b\u0010m\u001a\u0004\u0018\u00010\u00192\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u00192\t\u0010\u0098\u0001\u001a\u0004\u0018\u00010\u00192\u0007\u0010\u0091\u0001\u001a\u00020=2\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u00192\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u00192\u0007\u0010\u0095\u0001\u001a\u00020#2\u0007\u0010\u0099\u0001\u001a\u00020#2\u0007\u0010\u009a\u0001\u001a\u00020#2\u0006\u0010S\u001a\u00020\u00112\u0006\u0010u\u001a\u00020tH\u0002\u00a2\u0006\u0006\b\u009b\u0001\u0010\u009c\u0001J\u0011\u0010\u009d\u0001\u001a\u00020\u0006H\u0002\u00a2\u0006\u0005\b\u009d\u0001\u0010\u0003J8\u0010\u00a1\u0001\u001a\u00020\u00062\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u00192\u0007\u0010\u009e\u0001\u001a\u00020#2\u0007\u0010\u009f\u0001\u001a\u00020#2\u0007\u0010\u00a0\u0001\u001a\u00020\u001dH\u0002\u00a2\u0006\u0006\b\u00a1\u0001\u0010\u00a2\u0001JJ\u0010\u00a3\u0001\u001a\u00020\u00062\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u00192\u0007\u0010\u009e\u0001\u001a\u00020#2\u0007\u0010\u009f\u0001\u001a\u00020#2\u0006\u0010\u001b\u001a\u00020\f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00192\u0007\u0010\u00a0\u0001\u001a\u00020\u001dH\u0002\u00a2\u0006\u0006\b\u00a3\u0001\u0010\u00a4\u0001JH\u0010\u00a5\u0001\u001a\u00020\u00062\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010m\u001a\u00020\u001d2\u0007\u0010\u009e\u0001\u001a\u00020#2\u0007\u0010\u009f\u0001\u001a\u00020#2\u0006\u0010\u001b\u001a\u00020\f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0019H\u0002\u00a2\u0006\u0006\b\u00a5\u0001\u0010\u00a6\u0001J6\u0010\u00a9\u0001\u001a\u00020\u00062\b\u0010}\u001a\u0004\u0018\u00010\u00192\u0006\u0010m\u001a\u00020\u001d2\u0007\u0010\u00a7\u0001\u001a\u00020\f2\u0007\u0010\u00a8\u0001\u001a\u00020\fH\u0002\u00a2\u0006\u0006\b\u00a9\u0001\u0010\u00aa\u0001J&\u0010\u00ae\u0001\u001a\u00020Z2\b\u0010\u00ac\u0001\u001a\u00030«\u00012\b\u0010\u00ad\u0001\u001a\u00030«\u0001H\u0002\u00a2\u0006\u0006\b\u00ae\u0001\u0010\u00af\u0001J1\u0010\u00b4\u0001\u001a\u00030\u00b0\u00012\t\u0010\u000b\u001a\u0005\u0018\u00010\u00b0\u00012\u0007\u0010\u00b1\u0001\u001a\u00020\f2\b\u0010\u00b3\u0001\u001a\u00030\u00b2\u0001H\u0002\u00a2\u0006\u0006\b\u00b4\u0001\u0010\u00b5\u0001J\"\u0010\u00b6\u0001\u001a\u00020\u00112\u0006\u0010r\u001a\u00020\f2\u0006\u0010s\u001a\u00020\fH\u0002\u00a2\u0006\u0006\b\u00b6\u0001\u0010\u00b7\u0001J\u001b\u0010\u00b9\u0001\u001a\u00020=2\u0007\u0010\u00b8\u0001\u001a\u00020\fH\u0002\u00a2\u0006\u0006\b\u00b9\u0001\u0010\u00ba\u0001J+\u0010»\u0001\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0007\u0010\u0091\u0001\u001a\u00020=H\u0002\u00a2\u0006\u0006\b»\u0001\u0010\u00bc\u0001J+\u0010\u00be\u0001\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0007\u0010\u00bd\u0001\u001a\u00020\fH\u0002\u00a2\u0006\u0006\b\u00be\u0001\u0010\u00bf\u0001J\"\u0010\u00c1\u0001\u001a\u00020#2\u0006\u0010g\u001a\u00020#2\u0007\u0010\u00c0\u0001\u001a\u00020#H\u0002\u00a2\u0006\u0005\b\u00c1\u0001\u0010:J-\u0010\u00c5\u0001\u001a\u00020#2\u0007\u0010\u00c2\u0001\u001a\u00020#2\u0007\u0010\u00c3\u0001\u001a\u00020#2\u0007\u0010\u00c4\u0001\u001a\u00020#H\u0002\u00a2\u0006\u0006\b\u00c5\u0001\u0010\u00c6\u0001J\u0011\u0010\u00c7\u0001\u001a\u00020\u0006H\u0002\u00a2\u0006\u0005\b\u00c7\u0001\u0010\u0003J\u0011\u0010\u00c8\u0001\u001a\u00020\u0006H\u0002\u00a2\u0006\u0005\b\u00c8\u0001\u0010\u0003J\u0015\u0010\u0092\u0001\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0005\b\u0092\u0001\u0010\u0003J\u0011\u0010\u00c9\u0001\u001a\u00020\u0006H\u0002\u00a2\u0006\u0005\b\u00c9\u0001\u0010\u0003J\u0011\u0010\u00ca\u0001\u001a\u00020\u0006H\u0002\u00a2\u0006\u0005\b\u00ca\u0001\u0010\u0003J\u001e\u0010\u00cb\u0001\u001a\u0004\u0018\u00010\u001d2\b\u0010m\u001a\u0004\u0018\u00010\u001dH\u0002\u00a2\u0006\u0006\b\u00cb\u0001\u0010\u00cc\u0001J\u0011\u0010\u00cd\u0001\u001a\u00020\u0006H\u0002\u00a2\u0006\u0005\b\u00cd\u0001\u0010\u0003J \u0010\u00ce\u0001\u001a\u0005\u0018\u00010\u00b0\u00012\t\u0010\u000b\u001a\u0005\u0018\u00010\u00b0\u0001H\u0002\u00a2\u0006\u0006\b\u00ce\u0001\u0010\u00cf\u0001J\u001d\u0010\u00d1\u0001\u001a\u00030«\u00012\b\u0010\u00d0\u0001\u001a\u00030\u00b2\u0001H\u0002\u00a2\u0006\u0006\b\u00d1\u0001\u0010\u00d2\u0001R\u0017\u0010\u00d3\u0001\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00d3\u0001\u0010\u00d4\u0001R\u0017\u0010\u00d5\u0001\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00d5\u0001\u0010\u00d4\u0001R\u0017\u0010\u00d6\u0001\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00d6\u0001\u0010\u00d4\u0001R\u0017\u0010\u00d7\u0001\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00d7\u0001\u0010\u00d4\u0001R\u0017\u0010\u00d8\u0001\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00d8\u0001\u0010\u00d4\u0001R\u0017\u0010\u00d9\u0001\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00d9\u0001\u0010\u00d4\u0001R\u0017\u0010\u00da\u0001\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00da\u0001\u0010\u00d4\u0001R\u0017\u0010\u00db\u0001\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00db\u0001\u0010\u00d4\u0001R\u0017\u0010\u00dc\u0001\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00dc\u0001\u0010\u00d4\u0001R\u0017\u0010\u00dd\u0001\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00dd\u0001\u0010\u00d4\u0001R\u0017\u0010\u00de\u0001\u001a\u00020#8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00de\u0001\u0010\u00df\u0001R\u0017\u0010\u00e0\u0001\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00e0\u0001\u0010\u00d4\u0001R\u0018\u0010\u00e1\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00e1\u0001\u0010\u00e2\u0001R\u0018\u0010\u00e3\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00e3\u0001\u0010\u00e2\u0001R\u0018\u0010\u00e4\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00e4\u0001\u0010\u00e2\u0001R\u0018\u0010\u00e5\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00e5\u0001\u0010\u00e2\u0001R\u0018\u0010\u00e6\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00e6\u0001\u0010\u00e2\u0001R\u0018\u0010\u00e7\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00e7\u0001\u0010\u00e2\u0001R\u0018\u0010\u00e8\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00e8\u0001\u0010\u00e2\u0001R\u0018\u0010\u00e9\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00e9\u0001\u0010\u00e2\u0001R\u0018\u0010\u00ea\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00ea\u0001\u0010\u00e2\u0001R\u0018\u0010\u00eb\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00eb\u0001\u0010\u00e2\u0001R\u0018\u0010\u00ec\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00ec\u0001\u0010\u00e2\u0001R\u0018\u0010\u00ed\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00ed\u0001\u0010\u00e2\u0001R\u0018\u0010\u00ee\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00ee\u0001\u0010\u00e2\u0001R\u0018\u0010\u00ef\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00ef\u0001\u0010\u00e2\u0001R\u0018\u0010\u00f0\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00f0\u0001\u0010\u00e2\u0001R\u0018\u0010\u00f1\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00f1\u0001\u0010\u00e2\u0001R\u0018\u0010\u00f2\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00f2\u0001\u0010\u00e2\u0001R\u0018\u0010\u00f3\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00f3\u0001\u0010\u00e2\u0001R\u0018\u0010\u00f4\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00f4\u0001\u0010\u00e2\u0001R\u0018\u0010\u00f5\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00f5\u0001\u0010\u00e2\u0001R\u0018\u0010\u00f6\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00f6\u0001\u0010\u00e2\u0001R\u0018\u0010\u00f7\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00f7\u0001\u0010\u00e2\u0001R\u0018\u0010\u00f8\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00f8\u0001\u0010\u00e2\u0001R\u0018\u0010\u00f9\u0001\u001a\u00030«\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00f9\u0001\u0010\u00e2\u0001R\u001b\u0010\u00fa\u0001\u001a\u0004\u0018\u00010Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00fa\u0001\u0010\u00fb\u0001R\u001b\u0010\u00fc\u0001\u001a\u0004\u0018\u00010Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00fc\u0001\u0010\u00fb\u0001R\u001b\u0010\u00fd\u0001\u001a\u0004\u0018\u00010Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00fd\u0001\u0010\u00fb\u0001R\u001b\u0010\u00fe\u0001\u001a\u0004\u0018\u00010Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00fe\u0001\u0010\u00fb\u0001R\u001b\u0010\u00ff\u0001\u001a\u0004\u0018\u00010Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ff\u0001\u0010\u00fb\u0001R\u001b\u0010\u0080\u0002\u001a\u0004\u0018\u00010Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0080\u0002\u0010\u00fb\u0001R\u001b\u0010\u0081\u0002\u001a\u0004\u0018\u00010Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0081\u0002\u0010\u00fb\u0001R\u001b\u0010\u0082\u0002\u001a\u0004\u0018\u00010Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0082\u0002\u0010\u00fb\u0001R\u001b\u0010\u0083\u0002\u001a\u0004\u0018\u00010Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0083\u0002\u0010\u00fb\u0001R\u001b\u0010\u0084\u0002\u001a\u0004\u0018\u00010Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0084\u0002\u0010\u00fb\u0001R\u001b\u0010\u0085\u0002\u001a\u0004\u0018\u00010Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0085\u0002\u0010\u00fb\u0001R\u001b\u0010\u0086\u0002\u001a\u0004\u0018\u00010Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0086\u0002\u0010\u00fb\u0001R\u001c\u0010\u0087\u0002\u001a\u0005\u0018\u00010\u00b0\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0087\u0002\u0010\u0088\u0002R\u001c\u0010\u0089\u0002\u001a\u0005\u0018\u00010\u00b0\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0089\u0002\u0010\u0088\u0002R\u001c\u0010\u008a\u0002\u001a\u0005\u0018\u00010\u00b0\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008a\u0002\u0010\u0088\u0002R\u001c\u0010\u008b\u0002\u001a\u0005\u0018\u00010\u00b0\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008b\u0002\u0010\u0088\u0002R\u001c\u0010\u008c\u0002\u001a\u0005\u0018\u00010\u00b0\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008c\u0002\u0010\u0088\u0002R\u001c\u0010\u008d\u0002\u001a\u0005\u0018\u00010\u00b0\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008d\u0002\u0010\u0088\u0002R\u001c\u0010\u008e\u0002\u001a\u0005\u0018\u00010\u00b0\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008e\u0002\u0010\u0088\u0002R\u001c\u0010\u008f\u0002\u001a\u0005\u0018\u00010\u00b0\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008f\u0002\u0010\u0088\u0002R\u001c\u0010\u0090\u0002\u001a\u0005\u0018\u00010\u00b0\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0090\u0002\u0010\u0088\u0002R\u0017\u0010\u0091\u0002\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0091\u0002\u0010\u0092\u0002R\u0017\u0010\u0093\u0002\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0093\u0002\u0010\u0092\u0002R\u0017\u0010\u0094\u0002\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0094\u0002\u0010\u00d4\u0001R\u0017\u0010\u0095\u0002\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0095\u0002\u0010\u00d4\u0001R\u0017\u0010\u0096\u0002\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0096\u0002\u0010\u00d4\u0001R\u0017\u0010\u0097\u0002\u001a\u00020#8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0097\u0002\u0010\u00df\u0001R\u0017\u0010\u0098\u0002\u001a\u00020#8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0098\u0002\u0010\u00df\u0001R\u001b\u0010\u0099\u0002\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0099\u0002\u0010\u009a\u0002R\u001c\u0010\u009b\u0002\u001a\u0005\u0018\u00010\u00b0\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009b\u0002\u0010\u0088\u0002R\u001b\u0010\u009c\u0002\u001a\u0004\u0018\u00010=8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009c\u0002\u0010\u009d\u0002R\u0019\u0010\u009e\u0002\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009e\u0002\u0010\u009f\u0002R)\u0010\u00a2\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u001d0\u00a0\u0002j\t\u0012\u0004\u0012\u00020\u001d`\u00a1\u00028\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a2\u0002\u0010\u00a3\u0002R\u001b\u0010\u00a4\u0002\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a4\u0002\u0010\u009a\u0002R\u001b\u0010\u00a5\u0002\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a5\u0002\u0010\u009a\u0002R\u001b\u0010\u00a6\u0002\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a6\u0002\u0010\u009a\u0002R)\u0010\u00a7\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u001d0\u00a0\u0002j\t\u0012\u0004\u0012\u00020\u001d`\u00a1\u00028\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a7\u0002\u0010\u00a3\u0002R\u001b\u0010\u00a8\u0002\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a8\u0002\u0010\u009a\u0002R\u001b\u0010\u00a9\u0002\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a9\u0002\u0010\u009a\u0002R\u001b\u0010\u00aa\u0002\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00aa\u0002\u0010\u009a\u0002R\u001c\u0010\u00ac\u0002\u001a\u0005\u0018\u00010«\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ac\u0002\u0010\u00ad\u0002R\u001b\u0010\u00ae\u0002\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ae\u0002\u0010\u00af\u0002R\u001b\u0010\u00b0\u0002\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b0\u0002\u0010\u009a\u0002R\u001b\u0010\u00b1\u0002\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b1\u0002\u0010\u009a\u0002R\u001b\u0010\u00b2\u0002\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b2\u0002\u0010\u009a\u0002R\u001b\u0010\u00b3\u0002\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b3\u0002\u0010\u009a\u0002R\u001b\u0010\u00b4\u0002\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b4\u0002\u0010\u009a\u0002R\u0019\u0010\u00b5\u0002\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b5\u0002\u0010\u00d4\u0001R\u0019\u0010\u00b6\u0002\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b6\u0002\u0010\u00d4\u0001R\u001b\u0010\u00b7\u0002\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b7\u0002\u0010\u00af\u0002R\u001b\u0010\u00b8\u0002\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b8\u0002\u0010\u00af\u0002R\u0019\u0010\u00b9\u0002\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00b9\u0002\u0010\u009f\u0002R\u0019\u0010\u00ba\u0002\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00ba\u0002\u0010\u009f\u0002R\u0019\u0010»\u0002\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b»\u0002\u0010\u009f\u0002R\u0019\u0010\u00bc\u0002\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00bc\u0002\u0010\u009f\u0002R\u001b\u0010\u00bd\u0002\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00bd\u0002\u0010\u0092\u0002R\u0019\u0010\u00be\u0002\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00be\u0002\u0010\u009f\u0002R\u001b\u0010\u00bf\u0002\u001a\u0004\u0018\u00010=8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00bf\u0002\u0010\u009d\u0002R\u001b\u0010\u00c0\u0002\u001a\u0004\u0018\u00010=8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c0\u0002\u0010\u009d\u0002R\u0019\u0010\u00c1\u0002\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c1\u0002\u0010\u00d4\u0001R\u001b\u0010\u00c2\u0002\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c2\u0002\u0010\u0092\u0002R\u001b\u0010\u00c3\u0002\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c3\u0002\u0010\u0092\u0002R\u0017\u0010\u00c4\u0002\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00c4\u0002\u0010\u0092\u0002R\u001b\u0010\u00c5\u0002\u001a\u0004\u0018\u00010=8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00c5\u0002\u0010\u009d\u0002\u00a8\u0006\u00c6\u0002"}, d2={"Lrtx/kimiko/utils/render/modules/post/shaderhands/ShaderHandsRenderer;", "", "<init>", "()V", "", "params", "", "Lkotlin/jvm/JvmStatic;", "setGlowThemeParams", "([F)V", "Ljava/nio/ByteBuffer;", "buffer", "", "offset", "putGlowTheme", "(Ljava/nio/ByteBuffer;I)V", "captureScene", "", "withSceneBlur", "(Z)V", "beginHandCapture", "()Z", "isCapturing", "renderMaskGrid", "ensureGridBounds", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "mask", "mode", "splitView", "Lnet/minecraft/SimpleFramebuffer;", "out", "gridBoundsPass", "(Lcom/mojang/blaze3d/textures/GpuTextureView;ILcom/mojang/blaze3d/textures/GpuTextureView;Lnet/minecraft/SimpleFramebuffer;)V", "computeGpuHandBounds", "updateHandMask", "", "guiX", "guiY", "guiW", "guiH", "isHandCoveredAt", "(FFFF)Z", "handDepthAt", "(FFFF)F", "handBoundsAt", "(FFFF)[F", "isoPx", "", "handContours", "(FFFF[F)Ljava/util/List;", "dist", "iso", "cellW", "cellH", "marchSquares", "([FFFF)[F", "gxf", "sxCell", "(FF)F", "gyf", "syCell", "", "grid", "cx", "cy", "nearestCovered", "([III)I", "endHandCapture", "beginHandFrame", "wasHandCapturedThisFrame", "capturedHandDepthView", "()Lcom/mojang/blaze3d/textures/GpuTextureView;", "capturedHandColorView", "handBoundsLeftView", "handBoundsRightView", "baseColor", "glowGradient", "glassEnabled", "glowEnabled", "glowMode", "radius", "outlineWidthValue", "glowStrengthValue", "blending", "glassSaturation", "glassWhite", "glassDistort", "glassTint", "composite", "(I[IZZIFFFZFFFF)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "colorA", "colorB", "compositeUserShader", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;II)V", "tanHalfFov", "()F", "computeHandBounds", "gb", "toTexBounds", "([I[F)V", "b", "x", "y", "z", "w", "setBounds", "([FFFFF)V", "target", "writeBounds", "(Lnet/minecraft/SimpleFramebuffer;[F)V", "compositePlain", "isHandMaskReady", "width", "height", "Lnet/minecraft/GpuSampler;", "sampler", "kawaseBlur", "(IILnet/minecraft/GpuSampler;)V", "full", "shift", "margin", "quad", "(III)I", "source", "rx", "ry", "scissorW", "scissorH", "kawasePass", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lcom/mojang/blaze3d/textures/GpuTextureView;Lnet/minecraft/SimpleFramebuffer;FFLnet/minecraft/GpuSampler;II)V", "scene", "handMask", "boundsL", "boundsR", "color", "saturation", "whiteLift", "distortion", "tintAmount", "glass", "(Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;IFFFFLnet/minecraft/GpuSampler;)V", "dirX", "dirY", "gradient", "clear", "glowDilate", "(Lcom/mojang/blaze3d/textures/GpuTextureView;Lnet/minecraft/SimpleFramebuffer;FFF[ILcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lnet/minecraft/GpuSampler;Z)V", "glowRadius", "glowGauss", "(Lcom/mojang/blaze3d/textures/GpuTextureView;Lnet/minecraft/SimpleFramebuffer;FFF[ILcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lnet/minecraft/GpuSampler;)V", "glowTex", "glowStrength", "outlineWidth", "outline", "(Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;[ILcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;FFFZLnet/minecraft/GpuSampler;)V", "init", "minX", "maxX", "finalTarget", "computeBoundsInto", "(Lcom/mojang/blaze3d/textures/GpuTextureView;FFLnet/minecraft/SimpleFramebuffer;)V", "computeBounds", "(Lcom/mojang/blaze3d/textures/GpuTextureView;FFILcom/mojang/blaze3d/textures/GpuTextureView;Lnet/minecraft/SimpleFramebuffer;)V", "boundsInit", "(Lcom/mojang/blaze3d/textures/GpuTextureView;Lnet/minecraft/SimpleFramebuffer;FFILcom/mojang/blaze3d/textures/GpuTextureView;)V", "sourceWidth", "sourceHeight", "boundsReduce", "(Lcom/mojang/blaze3d/textures/GpuTextureView;Lnet/minecraft/SimpleFramebuffer;II)V", "Lnet/minecraft/Identifier;", "pipelineId", "fragment", "registerKawase", "(Lnet/minecraft/Identifier;Lnet/minecraft/Identifier;)Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "size", "", "name", "ensureBuffer", "(Lcom/mojang/blaze3d/buffers/GpuBuffer;ILjava/lang/String;)Lcom/mojang/blaze3d/buffers/GpuBuffer;", "ensureTargets", "(II)Z", "base", "gradientColors", "(I)[I", "putGradient", "(Ljava/nio/ByteBuffer;I[I)V", "argb", "putColor", "(Ljava/nio/ByteBuffer;II)V", "sigma", "gaussian", "value", "min", "max", "clamp", "(FFF)F", "reset", "fail", "closeTargets", "closeGridBounds", "destroy", "(Lnet/minecraft/SimpleFramebuffer;)Lnet/minecraft/SimpleFramebuffer;", "closeBuffers", "closeBuffer", "(Lcom/mojang/blaze3d/buffers/GpuBuffer;)Lcom/mojang/blaze3d/buffers/GpuBuffer;", "path", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "KAWASE_SIZE", "I", "GLASS_SIZE", "GLOW_SIZE", "GAUSS_SIZE", "OUTLINE_SIZE", "THEME_MIX_OFFSET_GAUSS", "THEME_MIX_OFFSET_OUTLINE", "REDUCE_SIZE", "INIT_SIZE", "BOUNDS_WRITE_SIZE", "KAWASE_OFFSET", "F", "KAWASE_STEPS", "VERTEX", "Lnet/minecraft/Identifier;", "KAWASE_DOWN_SHADER", "KAWASE_UP_SHADER", "GLASS_SHADER", "GLOW_DILATE_SHADER", "GLOW_GAUSS_SHADER", "OUTLINE_SHADER", "BOUNDS_INIT_SHADER", "BOUNDS_REDUCE_SHADER", "BOUNDS_WRITE_SHADER", "PASSTHROUGH_SHADER", "HANDMASK_SHADER", "KAWASE_DOWN_PIPELINE_ID", "KAWASE_UP_PIPELINE_ID", "GLASS_PIPELINE_ID", "GLOW_DILATE_PIPELINE_ID", "GLOW_GAUSS_PIPELINE_ID", "OUTLINE_PIPELINE_ID", "OUTLINE_ADD_PIPELINE_ID", "BOUNDS_INIT_PIPELINE_ID", "BOUNDS_REDUCE_PIPELINE_ID", "BOUNDS_WRITE_PIPELINE_ID", "PASSTHROUGH_PIPELINE_ID", "HANDMASK_PIPELINE_ID", "kawaseDownPipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "kawaseUpPipeline", "glassPipeline", "glowDilatePipeline", "glowGaussPipeline", "outlinePipeline", "outlineAddPipeline", "boundsInitPipeline", "boundsReducePipeline", "boundsWritePipeline", "passthroughPipeline", "handMaskPipeline", "kawaseBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "glassBuffer", "glowBuffer", "gaussBuffer", "outlineBuffer", "reduceBuffer", "initBuffer", "boundsWriteBuffer", "userBuffer", "boundsAData", "[F", "boundsBData", "MASK_W", "MASK_H", "MASK_ALPHA_THRESHOLD", "MASK_DEPTH_MIN", "MASK_DEPTH_MAX", "maskGrid", "Lnet/minecraft/SimpleFramebuffer;", "maskReadBuffer", "handMaskCpu", "[I", "handMaskValid", "Z", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "gridBoundsChain", "Ljava/util/ArrayList;", "gridUnion", "gridLeft", "gridRight", "boundsChain", "boundsUnion", "boundsLeft", "boundsRight", "Lcom/mojang/blaze3d/textures/GpuTexture;", "sceneCopyTexture", "Lcom/mojang/blaze3d/textures/GpuTexture;", "sceneCopyTextureView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "sceneA", "sceneB", "handFbo", "glow", "glowSwap", "targetWidth", "targetHeight", "prevColorOverride", "prevDepthOverride", "capturing", "sceneReady", "handReady", "disabledAfterError", "glowThemeParams", "handCapturedFrame", "floodStamp", "floodQueue", "floodGen", "floodDist", "marchBuf", "marchPts", "maskBackBuffer", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nShaderHandsRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ShaderHandsRenderer.kt\nrtx/kimiko/utils/render/modules/post/shaderhands/ShaderHandsRenderer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1640:1\n1#2:1641\n*E\n"})
public final class ShaderHandsRenderer {
    @NotNull
    public static final ShaderHandsRenderer INSTANCE = new ShaderHandsRenderer();
    private static final int KAWASE_SIZE = 16;
    private static final int GLASS_SIZE = 32;
    private static final int GLOW_SIZE = 96;
    private static final int GAUSS_SIZE = 256;
    private static final int OUTLINE_SIZE = 128;
    private static final int THEME_MIX_OFFSET_GAUSS = 112;
    private static final int THEME_MIX_OFFSET_OUTLINE = 96;
    private static final int REDUCE_SIZE = 16;
    private static final int INIT_SIZE = 16;
    private static final int BOUNDS_WRITE_SIZE = 16;
    private static final float KAWASE_OFFSET = 2.6f;
    private static final int KAWASE_STEPS = 4;
    @NotNull
    private static final Identifier VERTEX = INSTANCE.id("post/shaderhands/shaderhands");
    @NotNull
    private static final Identifier KAWASE_DOWN_SHADER = INSTANCE.id("post/shaderhands/light_kawase_down");
    @NotNull
    private static final Identifier KAWASE_UP_SHADER = INSTANCE.id("post/shaderhands/light_kawase_up");
    @NotNull
    private static final Identifier GLASS_SHADER = INSTANCE.id("post/shaderhands/glass");
    @NotNull
    private static final Identifier GLOW_DILATE_SHADER = INSTANCE.id("post/shaderhands/glow_dilate");
    @NotNull
    private static final Identifier GLOW_GAUSS_SHADER = INSTANCE.id("post/shaderhands/glow_gauss");
    @NotNull
    private static final Identifier OUTLINE_SHADER = INSTANCE.id("post/shaderhands/outline");
    @NotNull
    private static final Identifier BOUNDS_INIT_SHADER = INSTANCE.id("post/shaderhands/bounds_init");
    @NotNull
    private static final Identifier BOUNDS_REDUCE_SHADER = INSTANCE.id("post/shaderhands/bounds_reduce");
    @NotNull
    private static final Identifier BOUNDS_WRITE_SHADER = INSTANCE.id("post/shaderhands/bounds_write");
    @NotNull
    private static final Identifier PASSTHROUGH_SHADER = INSTANCE.id("post/shaderhands/passthrough");
    @NotNull
    private static final Identifier HANDMASK_SHADER = INSTANCE.id("post/shaderhands/handmask");
    @NotNull
    private static final Identifier KAWASE_DOWN_PIPELINE_ID = INSTANCE.id("pipeline/post/shaderhands/kawase_down");
    @NotNull
    private static final Identifier KAWASE_UP_PIPELINE_ID = INSTANCE.id("pipeline/post/shaderhands/kawase_up");
    @NotNull
    private static final Identifier GLASS_PIPELINE_ID = INSTANCE.id("pipeline/post/shaderhands/glass");
    @NotNull
    private static final Identifier GLOW_DILATE_PIPELINE_ID = INSTANCE.id("pipeline/post/shaderhands/glow_dilate");
    @NotNull
    private static final Identifier GLOW_GAUSS_PIPELINE_ID = INSTANCE.id("pipeline/post/shaderhands/glow_gauss");
    @NotNull
    private static final Identifier OUTLINE_PIPELINE_ID = INSTANCE.id("pipeline/post/shaderhands/outline");
    @NotNull
    private static final Identifier OUTLINE_ADD_PIPELINE_ID = INSTANCE.id("pipeline/post/shaderhands/outline_add");
    @NotNull
    private static final Identifier BOUNDS_INIT_PIPELINE_ID = INSTANCE.id("pipeline/post/shaderhands/bounds_init");
    @NotNull
    private static final Identifier BOUNDS_REDUCE_PIPELINE_ID = INSTANCE.id("pipeline/post/shaderhands/bounds_reduce");
    @NotNull
    private static final Identifier BOUNDS_WRITE_PIPELINE_ID = INSTANCE.id("pipeline/post/shaderhands/bounds_write");
    @NotNull
    private static final Identifier PASSTHROUGH_PIPELINE_ID = INSTANCE.id("pipeline/post/shaderhands/passthrough");
    @NotNull
    private static final Identifier HANDMASK_PIPELINE_ID = INSTANCE.id("pipeline/post/shaderhands/handmask");
    @Nullable
    private static RenderPipeline kawaseDownPipeline;
    @Nullable
    private static RenderPipeline kawaseUpPipeline;
    @Nullable
    private static RenderPipeline glassPipeline;
    @Nullable
    private static RenderPipeline glowDilatePipeline;
    @Nullable
    private static RenderPipeline glowGaussPipeline;
    @Nullable
    private static RenderPipeline outlinePipeline;
    @Nullable
    private static RenderPipeline outlineAddPipeline;
    @Nullable
    private static RenderPipeline boundsInitPipeline;
    @Nullable
    private static RenderPipeline boundsReducePipeline;
    @Nullable
    private static RenderPipeline boundsWritePipeline;
    @Nullable
    private static RenderPipeline passthroughPipeline;
    @Nullable
    private static RenderPipeline handMaskPipeline;
    @Nullable
    private static GpuBuffer kawaseBuffer;
    @Nullable
    private static GpuBuffer glassBuffer;
    @Nullable
    private static GpuBuffer glowBuffer;
    @Nullable
    private static GpuBuffer gaussBuffer;
    @Nullable
    private static GpuBuffer outlineBuffer;
    @Nullable
    private static GpuBuffer reduceBuffer;
    @Nullable
    private static GpuBuffer initBuffer;
    @Nullable
    private static GpuBuffer boundsWriteBuffer;
    @Nullable
    private static GpuBuffer userBuffer;
    @NotNull
    private static final float[] boundsAData;
    @NotNull
    private static final float[] boundsBData;
    private static final int MASK_W = 256;
    private static final int MASK_H = 144;
    private static final int MASK_ALPHA_THRESHOLD = 110;
    private static final float MASK_DEPTH_MIN = 0.2f;
    private static final float MASK_DEPTH_MAX = 2.0f;
    @Nullable
    private static SimpleFramebuffer maskGrid;
    @Nullable
    private static GpuBuffer maskReadBuffer;
    @Nullable
    private static volatile int[] handMaskCpu;
    private static volatile boolean handMaskValid;
    @NotNull
    private static final ArrayList<SimpleFramebuffer> gridBoundsChain;
    @Nullable
    private static SimpleFramebuffer gridUnion;
    @Nullable
    private static SimpleFramebuffer gridLeft;
    @Nullable
    private static SimpleFramebuffer gridRight;
    @NotNull
    private static final ArrayList<SimpleFramebuffer> boundsChain;
    @Nullable
    private static SimpleFramebuffer boundsUnion;
    @Nullable
    private static SimpleFramebuffer boundsLeft;
    @Nullable
    private static SimpleFramebuffer boundsRight;
    @Nullable
    private static GpuTexture sceneCopyTexture;
    @Nullable
    private static GpuTextureView sceneCopyTextureView;
    @Nullable
    private static SimpleFramebuffer sceneA;
    @Nullable
    private static SimpleFramebuffer sceneB;
    @Nullable
    private static SimpleFramebuffer handFbo;
    @Nullable
    private static SimpleFramebuffer glow;
    @Nullable
    private static SimpleFramebuffer glowSwap;
    private static int targetWidth;
    private static int targetHeight;
    @Nullable
    private static GpuTextureView prevColorOverride;
    @Nullable
    private static GpuTextureView prevDepthOverride;
    private static boolean capturing;
    private static boolean sceneReady;
    private static boolean handReady;
    private static boolean disabledAfterError;
    @Nullable
    private static float[] glowThemeParams;
    private static boolean handCapturedFrame;
    @Nullable
    private static int[] floodStamp;
    @Nullable
    private static int[] floodQueue;
    private static int floodGen;
    @Nullable
    private static float[] floodDist;
    @Nullable
    private static float[] marchBuf;
    @NotNull
    private static final float[] marchPts;
    @Nullable
    private static int[] maskBackBuffer;

    private ShaderHandsRenderer() {
    }

    @JvmStatic
    public static final void setGlowThemeParams(@Nullable float[] params) {
        glowThemeParams = params != null && params.length >= 6 ? params : null;
    }

    private final void putGlowTheme(ByteBuffer buffer, int offset) {
        float[] theme = glowThemeParams;
        if (theme == null) {
            for (int i = 0; i < 8; ++i) {
                buffer.putFloat(offset + i * 4, 0.0f);
            }
            return;
        }
        buffer.putFloat(offset, theme[0]);
        buffer.putFloat(offset + 4, theme[1]);
        buffer.putFloat(offset + 8, theme[2]);
        buffer.putFloat(offset + 12, theme[3]);
        buffer.putFloat(offset + 16, theme[4]);
        buffer.putFloat(offset + 20, theme[5]);
        buffer.putFloat(offset + 24, 0.0f);
        buffer.putFloat(offset + 28, 0.0f);
    }

    @JvmStatic
    public static final void captureScene() {
        ShaderHandsRenderer.captureScene(true);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void captureScene(boolean withSceneBlur) {
        handCapturedFrame = false;
        if (disabledAfterError) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Framebuffer main = mc.getFramebuffer();
        if (main == null || main.getColorAttachment() == null || main.getColorAttachmentView() == null) {
            return;
        }
        INSTANCE.init();
        if (kawaseDownPipeline == null || kawaseUpPipeline == null || !INSTANCE.ensureTargets(main.textureWidth, main.textureHeight)) {
            return;
        }
        try {
            GpuSampler linear = RenderSampler.linear();
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder copy = commandEncoder;
            SimpleFramebuffer hand = handFbo;
            if (hand != null) {
                Supplier<String> supplier = ShaderHandsRenderer::captureScene$lambda$0;
                GpuTextureView gpuTextureView = hand.getColorAttachmentView();
                Intrinsics.checkNotNull((Object)gpuTextureView);
                AutoCloseable autoCloseable = (AutoCloseable)copy.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0), hand.getDepthAttachmentView(), OptionalDouble.of(1.0));
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
            handReady = false;
            sceneReady = false;
            if (withSceneBlur) {
                GpuTexture gpuTexture = main.getColorAttachment();
                Intrinsics.checkNotNull((Object)gpuTexture);
                GpuTexture gpuTexture2 = sceneCopyTexture;
                Intrinsics.checkNotNull((Object)gpuTexture2);
                copy.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, main.textureWidth, main.textureHeight);
                INSTANCE.kawaseBlur(main.textureWidth, main.textureHeight, linear);
            }
            sceneReady = true;
        }
        catch (Throwable throwable) {
            INSTANCE.fail();
        }
    }

    @JvmStatic
    public static final boolean beginHandCapture() {
        boolean bl;
        if (disabledAfterError || !sceneReady) {
            return false;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Framebuffer framebuffer2 = mc.getFramebuffer();
        if (framebuffer2 == null) {
            return false;
        }
        Framebuffer main = framebuffer2;
        INSTANCE.init();
        if (!INSTANCE.ensureTargets(main.textureWidth, main.textureHeight) || handFbo == null) {
            return false;
        }
        try {
            prevColorOverride = RenderSystem.outputColorTextureOverride;
            prevDepthOverride = RenderSystem.outputDepthTextureOverride;
            SimpleFramebuffer simpleFramebuffer2 = handFbo;
            Intrinsics.checkNotNull((Object)simpleFramebuffer2);
            RenderSystem.outputColorTextureOverride = simpleFramebuffer2.getColorAttachmentView();
            SimpleFramebuffer simpleFramebuffer3 = handFbo;
            Intrinsics.checkNotNull((Object)simpleFramebuffer3);
            RenderSystem.outputDepthTextureOverride = simpleFramebuffer3.getDepthAttachmentView();
            capturing = true;
            bl = true;
        }
        catch (Throwable throwable) {
            INSTANCE.fail();
            bl = false;
        }
        return bl;
    }

    @JvmStatic
    public static final boolean isCapturing() {
        return capturing;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final boolean renderMaskGrid() {
        SimpleFramebuffer hand = handFbo;
        if (hand == null || handMaskPipeline == null || hand.getDepthAttachmentView() == null) {
            return false;
        }
        if (maskGrid == null) {
            maskGrid = new SimpleFramebuffer("kimiko_hand_mask_grid", 256, 144, false);
        }
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        Supplier<String> supplier = ShaderHandsRenderer::renderMaskGrid$lambda$0;
        SimpleFramebuffer simpleFramebuffer2 = maskGrid;
        Intrinsics.checkNotNull((Object)simpleFramebuffer2);
        GpuTextureView gpuTextureView = simpleFramebuffer2.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0));
        Throwable throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = handMaskPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            pass.bindTexture("HandTex", hand.getColorAttachmentView(), RenderSampler.linear());
            pass.bindTexture("HandDepth", hand.getDepthAttachmentView(), RenderSampler.linear());
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
        return true;
    }

    private final boolean ensureGridBounds() {
        if (!((Collection)gridBoundsChain).isEmpty() && gridUnion != null && gridLeft != null && gridRight != null) {
            return true;
        }
        this.closeGridBounds();
        int bw = 256;
        int bh = 144;
        int level = 0;
        gridBoundsChain.add(new SimpleFramebuffer("kimiko_hand_gbounds_" + level, bw, bh, false));
        while (bw > 1 || bh > 1) {
            bw = Math.max(1, bw / 2);
            bh = Math.max(1, bh / 2);
            gridBoundsChain.add(new SimpleFramebuffer("kimiko_hand_gbounds_" + ++level, bw, bh, false));
        }
        gridUnion = new SimpleFramebuffer("kimiko_hand_gbounds_union", 1, 1, false);
        gridLeft = new SimpleFramebuffer("kimiko_hand_gbounds_left", 1, 1, false);
        gridRight = new SimpleFramebuffer("kimiko_hand_gbounds_right", 1, 1, false);
        return true;
    }

    private final void gridBoundsPass(GpuTextureView mask, int mode, GpuTextureView splitView, SimpleFramebuffer out) {
        SimpleFramebuffer simpleFramebuffer2 = gridBoundsChain.get(0);
        Intrinsics.checkNotNullExpressionValue((Object)simpleFramebuffer2, (String)"get(...)");
        this.boundsInit(mask, simpleFramebuffer2, 0.0f, 1.0f, mode, splitView);
        int n = gridBoundsChain.size();
        int n2 = n - 2;
        for (int i = 0; i < n2; ++i) {
            SimpleFramebuffer src = (SimpleFramebuffer) (gridBoundsChain.get(i));
            GpuTextureView gpuTextureView = src.getColorAttachmentView();
            SimpleFramebuffer simpleFramebuffer3 = gridBoundsChain.get(i + 1);
            Intrinsics.checkNotNullExpressionValue((Object)simpleFramebuffer3, (String)"get(...)");
            this.boundsReduce(gpuTextureView, simpleFramebuffer3, src.textureWidth, src.textureHeight);
        }
        SimpleFramebuffer simpleFramebuffer4 = gridBoundsChain.get(n - 2);
        Intrinsics.checkNotNullExpressionValue((Object)simpleFramebuffer4, (String)"get(...)");
        SimpleFramebuffer pen = simpleFramebuffer4;
        this.boundsReduce(pen.getColorAttachmentView(), out, pen.textureWidth, pen.textureHeight);
    }

    private final boolean computeGpuHandBounds() {
        if (boundsInitPipeline == null || boundsReducePipeline == null || !this.renderMaskGrid() || !this.ensureGridBounds()) {
            return false;
        }
        SimpleFramebuffer simpleFramebuffer2 = maskGrid;
        Intrinsics.checkNotNull((Object)simpleFramebuffer2);
        GpuTextureView mask = simpleFramebuffer2.getColorAttachmentView();
        SimpleFramebuffer simpleFramebuffer3 = gridLeft;
        Intrinsics.checkNotNull((Object)simpleFramebuffer3);
        GpuTextureView gpuTextureView = simpleFramebuffer3.getColorAttachmentView();
        SimpleFramebuffer simpleFramebuffer4 = gridUnion;
        Intrinsics.checkNotNull((Object)simpleFramebuffer4);
        this.gridBoundsPass(mask, 0, gpuTextureView, simpleFramebuffer4);
        SimpleFramebuffer simpleFramebuffer5 = gridUnion;
        Intrinsics.checkNotNull((Object)simpleFramebuffer5);
        GpuTextureView union = simpleFramebuffer5.getColorAttachmentView();
        SimpleFramebuffer simpleFramebuffer6 = gridLeft;
        Intrinsics.checkNotNull((Object)simpleFramebuffer6);
        this.gridBoundsPass(mask, 1, union, simpleFramebuffer6);
        SimpleFramebuffer simpleFramebuffer7 = gridRight;
        Intrinsics.checkNotNull((Object)simpleFramebuffer7);
        this.gridBoundsPass(mask, 2, union, simpleFramebuffer7);
        return true;
    }

    @JvmStatic
    public static final void updateHandMask() {
        SimpleFramebuffer hand = handFbo;
        if (disabledAfterError || hand == null || handMaskPipeline == null || hand.getDepthAttachmentView() == null) {
            return;
        }
        try {
            if (!INSTANCE.renderMaskGrid()) {
                return;
            }
            SimpleFramebuffer simpleFramebuffer2 = maskGrid;
            Intrinsics.checkNotNull((Object)simpleFramebuffer2);
            GpuTexture gpuTexture = simpleFramebuffer2.getColorAttachment();
            if (gpuTexture == null) {
                return;
            }
            GpuTexture gridTex = gpuTexture;
            int px = gridTex.getFormat().pixelSize();
            int size = 36864 * px;
            GpuBuffer current = maskReadBuffer;
            if (current == null || current.isClosed()) {
                maskReadBuffer = RenderSystem.getDevice().createBuffer(ShaderHandsRenderer::updateHandMask$lambda$0, 9, (long)size);
            }
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            GpuBuffer gpuBuffer = maskReadBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            commandEncoder.copyTextureToBuffer(gridTex, gpuBuffer, 0L, () -> ShaderHandsRenderer.updateHandMask$lambda$1(px), 0);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @JvmStatic
    public static final boolean isHandCoveredAt(float guiX, float guiY, float guiW, float guiH) {
        int[] grid = handMaskCpu;
        if (!handMaskValid || grid == null || guiW < 0.5f || guiH < 0.5f) {
            return false;
        }
        int cx = Math.max(0, Math.min(255, (int)(guiX / guiW * (float)256)));
        int cyTop = Math.max(0, Math.min(143, (int)(guiY / guiH * (float)144)));
        int cy = 143 - cyTop;
        int packed = grid[cy * 256 + cx];
        return (packed >>> 24 & 0xFF) >= 110;
    }

    @JvmStatic
    public static final float handDepthAt(float guiX, float guiY, float guiW, float guiH) {
        int[] grid = handMaskCpu;
        if (!handMaskValid || grid == null || guiW < 0.5f || guiH < 0.5f) {
            return -1.0f;
        }
        int cx = Math.max(0, Math.min(255, (int)(guiX / guiW * (float)256)));
        int cyTop = Math.max(0, Math.min(143, (int)(guiY / guiH * (float)144)));
        int cy = 143 - cyTop;
        int packed = grid[cy * 256 + cx];
        if ((packed >>> 24 & 0xFF) < 110) {
            return -1.0f;
        }
        int r = packed & 0xFF;
        if (r <= 0 || r >= 255) {
            return -1.0f;
        }
        return 0.2f + (float)r / 255.0f * 1.8f;
    }

    @JvmStatic
    @Nullable
    public static final float[] handBoundsAt(float guiX, float guiY, float guiW, float guiH) {
        int cyTop;
        int[] grid = handMaskCpu;
        if (!handMaskValid || grid == null || guiW < 0.5f || guiH < 0.5f) {
            return null;
        }
        int cx = Math.max(0, Math.min(255, (int)(guiX / guiW * (float)256)));
        int start = INSTANCE.nearestCovered(grid, cx, 143 - (cyTop = Math.max(0, Math.min(143, (int)(guiY / guiH * (float)144)))));
        if (start < 0) {
            return null;
        }
        int[] stamp = floodStamp;
        if (stamp == null || stamp.length != 36864) {
            floodStamp = stamp = new int[36864];
            floodQueue = new int[36864];
        }
        Intrinsics.checkNotNull((Object)floodQueue);
        int[] queue = floodQueue;
        int gen = ++floodGen;
        int qh = 0;
        int qt = 0;
        queue[qt++] = start;
        stamp[start] = gen;
        int minX = 256;
        int maxX = -1;
        int minY = 144;
        int maxY = -1;
        while (qh < qt) {
            int idx = queue[qh++];
            int gx = idx % 256;
            int gy = idx / 256;
            if (gx < minX) {
                minX = gx;
            }
            if (gx > maxX) {
                maxX = gx;
            }
            if (gy < minY) {
                minY = gy;
            }
            if (gy > maxY) {
                maxY = gy;
            }
            for (int dir = 0; dir < 4; ++dir) {
                int nidx;
                int nx = gx + (switch (dir) {
                    case 0 -> -1;
                    case 1 -> 1;
                    default -> 0;
                });
                int ny = gy + (switch (dir) {
                    case 2 -> -1;
                    case 3 -> 1;
                    default -> 0;
                });
                if (nx < 0 || nx >= 256 || ny < 0 || ny >= 144 || stamp[nidx = ny * 256 + nx] == gen || (grid[nidx] >>> 24 & 0xFF) < 110) continue;
                stamp[nidx] = gen;
                queue[qt++] = nidx;
            }
        }
        float left = (float)minX / (float)256 * guiW;
        float right = (float)(maxX + 1) / (float)256 * guiW;
        float top = (float)(143 - maxY) / (float)144 * guiH;
        float bottom = (float)(144 - minY) / (float)144 * guiH;
        float[] fArray = new float[]{left, top, right - left, bottom - top};
        return fArray;
    }

    @JvmStatic
    @Nullable
    public static final List<float[]> handContours(float guiX, float guiY, float guiW, float guiH, @Nullable float[] isoPx) {
        int i;
        int x;
        int y;
        int cyTop;
        int[] grid = handMaskCpu;
        if (!handMaskValid || grid == null || guiW < 0.5f || guiH < 0.5f || isoPx == null) {
            return null;
        }
        int cx = Math.max(0, Math.min(255, (int)(guiX / guiW * (float)256)));
        int start = INSTANCE.nearestCovered(grid, cx, 143 - (cyTop = Math.max(0, Math.min(143, (int)(guiY / guiH * (float)144)))));
        if (start < 0) {
            return null;
        }
        int n = 36864;
        int[] stamp = floodStamp;
        if (stamp == null || stamp.length != n) {
            floodStamp = stamp = new int[n];
            floodQueue = new int[n];
            floodDist = new float[n];
        }
        Intrinsics.checkNotNull((Object)floodQueue);
        int[] queue = floodQueue;
        Intrinsics.checkNotNull((Object)floodDist);
        float[] dist = floodDist;
        float big = 1.0E9f;
        Arrays.fill(dist, big);
        int gen = ++floodGen;
        int qh = 0;
        int qt = 0;
        queue[qt++] = start;
        stamp[start] = gen;
        dist[start] = 0.0f;
        while (qh < qt) {
            int idx = queue[qh++];
            int gx = idx % 256;
            int gy = idx / 256;
            for (int dir = 0; dir < 4; ++dir) {
                int nidx;
                int nx = gx + (switch (dir) {
                    case 0 -> -1;
                    case 1 -> 1;
                    default -> 0;
                });
                int ny = gy + (switch (dir) {
                    case 2 -> -1;
                    case 3 -> 1;
                    default -> 0;
                });
                if (nx < 0 || nx >= 256 || ny < 0 || ny >= 144 || stamp[nidx = ny * 256 + nx] == gen || (grid[nidx] >>> 24 & 0xFF) < 110) continue;
                stamp[nidx] = gen;
                dist[nidx] = 0.0f;
                queue[qt++] = nidx;
            }
        }
        float cellW = guiW / (float)256;
        float cellH = guiH / (float)144;
        float diag = (float)Math.sqrt(cellW * cellW + cellH * cellH);
        for (y = 0; y < 144; ++y) {
            for (x = 0; x < 256; ++x) {
                i = y * 256 + x;
                float d = dist[i];
                if (x > 0) {
                    d = Math.min(d, dist[i - 1] + cellW);
                }
                if (y > 0) {
                    d = Math.min(d, dist[i - 256] + cellH);
                }
                if (x > 0 && y > 0) {
                    d = Math.min(d, dist[i - 256 - 1] + diag);
                }
                if (x < 255 && y > 0) {
                    d = Math.min(d, dist[i - 256 + 1] + diag);
                }
                dist[i] = d;
            }
        }
        for (y = 143; -1 < y; --y) {
            for (x = 255; -1 < x; --x) {
                i = y * 256 + x;
                float d = dist[i];
                if (x < 255) {
                    d = Math.min(d, dist[i + 1] + cellW);
                }
                if (y < 143) {
                    d = Math.min(d, dist[i + 256] + cellH);
                }
                if (x < 255 && y < 143) {
                    d = Math.min(d, dist[i + 256 + 1] + diag);
                }
                if (x > 0 && y < 143) {
                    d = Math.min(d, dist[i + 256 - 1] + diag);
                }
                dist[i] = d;
            }
        }
        ArrayList<float[]> result = new ArrayList<float[]>(isoPx.length);
        for (float iso : isoPx) {
            result.add(INSTANCE.marchSquares(dist, iso, cellW, cellH));
        }
        return result;
    }

    private final float[] marchSquares(float[] dist, float iso, float cellW, float cellH) {
        float[] buf = marchBuf;
        if (buf == null) {
            marchBuf = buf = new float[65536];
        }
        int count = 0;
        float[] pts = marchPts;
        for (int y = 0; y < 143; ++y) {
            for (int x = 0; x < 255; ++x) {
                float t;
                float c00 = dist[y * 256 + x];
                float c10 = dist[y * 256 + x + 1];
                float c01 = dist[(y + 1) * 256 + x];
                float c11 = dist[(y + 1) * 256 + x + 1];
                int np = 0;
                if (c00 < iso != c10 < iso) {
                    t = (iso - c00) / (c10 - c00);
                    pts[np * 2] = this.sxCell((float)x + t, cellW);
                    pts[np * 2 + 1] = this.syCell(y, cellH);
                    ++np;
                }
                if (c10 < iso != c11 < iso) {
                    t = (iso - c10) / (c11 - c10);
                    pts[np * 2] = this.sxCell(x + 1, cellW);
                    pts[np * 2 + 1] = this.syCell((float)y + t, cellH);
                    ++np;
                }
                if (c01 < iso != c11 < iso) {
                    t = (iso - c01) / (c11 - c01);
                    pts[np * 2] = this.sxCell((float)x + t, cellW);
                    pts[np * 2 + 1] = this.syCell(y + 1, cellH);
                    ++np;
                }
                if (c00 < iso != c01 < iso) {
                    t = (iso - c00) / (c01 - c00);
                    pts[np * 2] = this.sxCell(x, cellW);
                    pts[np * 2 + 1] = this.syCell((float)y + t, cellH);
                    ++np;
                }
                if (np >= 2 && count + 4 <= buf.length) {
                    buf[count++] = pts[0];
                    buf[count++] = pts[1];
                    buf[count++] = pts[2];
                    buf[count++] = pts[3];
                }
                if (np != 4 || count + 4 > buf.length) continue;
                buf[count++] = pts[4];
                buf[count++] = pts[5];
                buf[count++] = pts[6];
                buf[count++] = pts[7];
            }
        }
        float[] fArray = Arrays.copyOf(buf, count);
        Intrinsics.checkNotNullExpressionValue((Object)fArray, (String)"copyOf(...)");
        return fArray;
    }

    private final float sxCell(float gxf, float cellW) {
        return (gxf + 0.5f) * cellW;
    }

    private final float syCell(float gyf, float cellH) {
        return ((float)143 - gyf + 0.5f) * cellH;
    }

    private final int nearestCovered(int[] grid, int cx, int cy) {
        block0: for (int r = 0; r < 5; ++r) {
            int dy = -r;
            if (dy > r) continue;
            while (true) {
                int dx;
                if ((dx = -r) <= r) {
                    while (true) {
                        if (Math.max(Math.abs(dx), Math.abs(dy)) == r) {
                            int idx;
                            int x = cx + dx;
                            int y = cy + dy;
                            if (x >= 0 && x < 256 && y >= 0 && y < 144 && (grid[idx = y * 256 + x] >>> 24 & 0xFF) >= 110) {
                                return idx;
                            }
                        }
                        if (dx == r) break;
                        ++dx;
                    }
                }
                if (dy == r) continue block0;
                ++dy;
            }
        }
        return -1;
    }

    @JvmStatic
    public static final void endHandCapture() {
        if (!capturing) {
            return;
        }
        RenderSystem.outputColorTextureOverride = prevColorOverride;
        RenderSystem.outputDepthTextureOverride = prevDepthOverride;
        prevColorOverride = null;
        prevDepthOverride = null;
        capturing = false;
        handReady = true;
        handCapturedFrame = true;
    }

    @JvmStatic
    public static final void beginHandFrame() {
        handCapturedFrame = false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @JvmStatic
    public static final boolean wasHandCapturedThisFrame() {
        if (disabledAfterError) return false;
        if (!handCapturedFrame) return false;
        if (handFbo == null) return false;
        SimpleFramebuffer simpleFramebuffer2 = handFbo;
        Intrinsics.checkNotNull((Object)simpleFramebuffer2);
        if (simpleFramebuffer2.getColorAttachmentView() == null) return false;
        return true;
    }

    @JvmStatic
    @Nullable
    public static final GpuTextureView capturedHandDepthView() {
        GpuTextureView gpuTextureView;
        if (ShaderHandsRenderer.wasHandCapturedThisFrame()) {
            SimpleFramebuffer simpleFramebuffer2 = handFbo;
            Intrinsics.checkNotNull((Object)simpleFramebuffer2);
            gpuTextureView = simpleFramebuffer2.getDepthAttachmentView();
        } else {
            gpuTextureView = null;
        }
        return gpuTextureView;
    }

    @JvmStatic
    @Nullable
    public static final GpuTextureView capturedHandColorView() {
        GpuTextureView gpuTextureView;
        if (ShaderHandsRenderer.wasHandCapturedThisFrame()) {
            SimpleFramebuffer simpleFramebuffer2 = handFbo;
            Intrinsics.checkNotNull((Object)simpleFramebuffer2);
            gpuTextureView = simpleFramebuffer2.getColorAttachmentView();
        } else {
            gpuTextureView = null;
        }
        return gpuTextureView;
    }

    @JvmStatic
    @Nullable
    public static final GpuTextureView handBoundsLeftView() {
        GpuTextureView gpuTextureView;
        if (ShaderHandsRenderer.wasHandCapturedThisFrame() && boundsLeft != null) {
            SimpleFramebuffer simpleFramebuffer2 = boundsLeft;
            Intrinsics.checkNotNull((Object)simpleFramebuffer2);
            gpuTextureView = simpleFramebuffer2.getColorAttachmentView();
        } else {
            gpuTextureView = null;
        }
        return gpuTextureView;
    }

    @JvmStatic
    @Nullable
    public static final GpuTextureView handBoundsRightView() {
        GpuTextureView gpuTextureView;
        if (ShaderHandsRenderer.wasHandCapturedThisFrame() && boundsRight != null) {
            SimpleFramebuffer simpleFramebuffer2 = boundsRight;
            Intrinsics.checkNotNull((Object)simpleFramebuffer2);
            gpuTextureView = simpleFramebuffer2.getColorAttachmentView();
        } else {
            gpuTextureView = null;
        }
        return gpuTextureView;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void composite(int baseColor, @Nullable int[] glowGradient, boolean glassEnabled, boolean glowEnabled, int glowMode, float radius, float outlineWidthValue, float glowStrengthValue, boolean blending, float glassSaturation, float glassWhite, float glassDistort, float glassTint) {
        if (disabledAfterError || !sceneReady || !handReady) {
            handReady = false;
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Framebuffer main = mc.getFramebuffer();
        if (main == null || main.getColorAttachmentView() == null || glassPipeline == null || boundsWritePipeline == null || boundsLeft == null || boundsRight == null) {
            handReady = false;
            return;
        }
        try {
            GpuSampler linear = RenderSampler.linear();
            SimpleFramebuffer simpleFramebuffer2 = handFbo;
            Intrinsics.checkNotNull((Object)simpleFramebuffer2);
            GpuTextureView handView = simpleFramebuffer2.getColorAttachmentView();
            INSTANCE.computeHandBounds();
            INSTANCE.writeBounds(boundsLeft, boundsAData);
            INSTANCE.writeBounds(boundsRight, boundsBData);
            SimpleFramebuffer simpleFramebuffer3 = boundsLeft;
            Intrinsics.checkNotNull((Object)simpleFramebuffer3);
            GpuTextureView bl = simpleFramebuffer3.getColorAttachmentView();
            SimpleFramebuffer simpleFramebuffer4 = boundsRight;
            Intrinsics.checkNotNull((Object)simpleFramebuffer4);
            GpuTextureView br = simpleFramebuffer4.getColorAttachmentView();
            if (glassEnabled) {
                GpuTextureView gpuTextureView = main.getColorAttachmentView();
                SimpleFramebuffer simpleFramebuffer5 = sceneB;
                Intrinsics.checkNotNull((Object)simpleFramebuffer5);
                INSTANCE.glass(gpuTextureView, simpleFramebuffer5.getColorAttachmentView(), handView, bl, br, baseColor, glassSaturation, glassWhite, glassDistort, glassTint, linear);
            } else if (passthroughPipeline != null) {
                CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
                Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
                CommandEncoder plainEncoder = commandEncoder;
                Supplier<String> supplier = ShaderHandsRenderer::composite$lambda$0;
                GpuTextureView gpuTextureView = main.getColorAttachmentView();
                Intrinsics.checkNotNull((Object)gpuTextureView);
                AutoCloseable autoCloseable = (AutoCloseable)plainEncoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
                Throwable throwable = null;
                try {
                    RenderPass pass = (RenderPass)autoCloseable;
                    boolean bl2 = false;
                    RenderPipeline renderPipeline = passthroughPipeline;
                    Intrinsics.checkNotNull((Object)renderPipeline);
                    pass.setPipeline(renderPipeline);
                    pass.bindTexture("HandTex", handView, RenderSampler.linear());
                    pass.draw(0, 6);
// pass = Unit.INSTANCE;
                }
                catch (Throwable bl2) {
                    throwable = bl2;
                    throw bl2;
                }
                finally {
                    AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
                }
            }
            if (glowEnabled && glowDilatePipeline != null && glowGaussPipeline != null && outlinePipeline != null && outlineAddPipeline != null) {
                float outlineWidth;
                boolean doGlow = glowMode != 1;
                boolean doOutline = glowMode != 0;
                int[] gradient = glowGradient != null && glowGradient.length >= 4 ? glowGradient : INSTANCE.gradientColors(baseColor);
                float glowRadius = INSTANCE.clamp(radius + 3.0f, 1.0f, 31.0f);
                float glowStrength = doGlow ? glowStrengthValue : 0.0f;
                float f = outlineWidth = doOutline ? outlineWidthValue : 0.0f;
                if (doGlow) {
                    float halfRadius = Math.max(1.0f, glowRadius * 0.5f);
                    SimpleFramebuffer simpleFramebuffer6 = glow;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer6);
                    INSTANCE.glowDilate(handView, simpleFramebuffer6, 0.0f, 0.0f, 1.0f, gradient, bl, br, linear, true);
                    SimpleFramebuffer simpleFramebuffer7 = glow;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer7);
                    GpuTextureView gpuTextureView = simpleFramebuffer7.getColorAttachmentView();
                    SimpleFramebuffer simpleFramebuffer8 = glowSwap;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer8);
                    INSTANCE.glowGauss(gpuTextureView, simpleFramebuffer8, 1.0f, 0.0f, halfRadius, gradient, bl, br, linear);
                    SimpleFramebuffer simpleFramebuffer9 = glowSwap;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer9);
                    GpuTextureView gpuTextureView2 = simpleFramebuffer9.getColorAttachmentView();
                    SimpleFramebuffer simpleFramebuffer10 = glow;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer10);
                    INSTANCE.glowGauss(gpuTextureView2, simpleFramebuffer10, 0.0f, 1.0f, halfRadius, gradient, bl, br, linear);
                    SimpleFramebuffer simpleFramebuffer11 = glow;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer11);
                    GpuTextureView gpuTextureView3 = simpleFramebuffer11.getColorAttachmentView();
                    SimpleFramebuffer simpleFramebuffer12 = glowSwap;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer12);
                    INSTANCE.glowGauss(gpuTextureView3, simpleFramebuffer12, 1.0f, 0.0f, halfRadius, gradient, bl, br, linear);
                    SimpleFramebuffer simpleFramebuffer13 = glowSwap;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer13);
                    GpuTextureView gpuTextureView4 = simpleFramebuffer13.getColorAttachmentView();
                    SimpleFramebuffer simpleFramebuffer14 = glow;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer14);
                    INSTANCE.glowGauss(gpuTextureView4, simpleFramebuffer14, 0.0f, 1.0f, halfRadius, gradient, bl, br, linear);
                }
                GpuTextureView gpuTextureView = main.getColorAttachmentView();
                SimpleFramebuffer simpleFramebuffer15 = glow;
                Intrinsics.checkNotNull((Object)simpleFramebuffer15);
                INSTANCE.outline(gpuTextureView, handView, simpleFramebuffer15.getColorAttachmentView(), gradient, bl, br, glowRadius, glowStrength, outlineWidth, blending, linear);
            }
        }
        catch (Throwable throwable) {
            INSTANCE.fail();
        }
        finally {
            handReady = false;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void compositeUserShader(@Nullable RenderPipeline pipeline, int colorA, int colorB) {
        if (disabledAfterError || !sceneReady || !handReady || pipeline == null) {
            handReady = false;
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Framebuffer main = mc.getFramebuffer();
        SimpleFramebuffer hand = handFbo;
        if (main == null || main.getColorAttachmentView() == null || hand == null || sceneB == null || hand.getColorAttachmentView() == null || hand.getDepthAttachmentView() == null) {
            handReady = false;
            return;
        }
        try {
            Object object;
            userBuffer = INSTANCE.ensureBuffer(userBuffer, 96, "kimiko:shader_hands_user");
            GpuTextureView boundsLeftView = null;
            GpuTextureView boundsRightView = null;
            if (INSTANCE.computeGpuHandBounds()) {
                SimpleFramebuffer simpleFramebuffer2 = gridLeft;
                Intrinsics.checkNotNull((Object)simpleFramebuffer2);
                boundsLeftView = simpleFramebuffer2.getColorAttachmentView();
                SimpleFramebuffer simpleFramebuffer3 = gridRight;
                Intrinsics.checkNotNull((Object)simpleFramebuffer3);
                boundsRightView = simpleFramebuffer3.getColorAttachmentView();
            } else if (boundsLeft != null && boundsRight != null) {
                INSTANCE.computeHandBounds();
                INSTANCE.writeBounds(boundsLeft, boundsAData);
                INSTANCE.writeBounds(boundsRight, boundsBData);
                SimpleFramebuffer simpleFramebuffer4 = boundsLeft;
                Intrinsics.checkNotNull((Object)simpleFramebuffer4);
                boundsLeftView = simpleFramebuffer4.getColorAttachmentView();
                SimpleFramebuffer simpleFramebuffer5 = boundsRight;
                Intrinsics.checkNotNull((Object)simpleFramebuffer5);
                boundsRightView = simpleFramebuffer5.getColorAttachmentView();
            } else {
                handReady = false;
                return;
            }
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
            Object object2 = null;
            try {
                MemoryStack stack = (MemoryStack)autoCloseable;
                boolean bl = false;
                ByteBuffer data = stack.calloc(96);
                data.putFloat(0, (float)((double)(System.currentTimeMillis() % 20000000L) / 1000.0));
                data.putFloat(4, targetWidth);
                data.putFloat(8, targetHeight);
                data.putFloat(12, 0.0f);
                Intrinsics.checkNotNull((Object)data);
                INSTANCE.putColor(data, 16, colorA);
                INSTANCE.putColor(data, 32, colorB);
                data.putFloat(48, boundsAData[0]);
                data.putFloat(52, boundsAData[1]);
                data.putFloat(56, boundsAData[2]);
                data.putFloat(60, boundsAData[3]);
                data.putFloat(64, boundsBData[0]);
                data.putFloat(68, boundsBData[1]);
                data.putFloat(72, boundsBData[2]);
                data.putFloat(76, boundsBData[3]);
                data.putFloat(80, INSTANCE.tanHalfFov());
                data.position(0);
                GpuBuffer gpuBuffer = userBuffer;
                Intrinsics.checkNotNull((Object)gpuBuffer);
                encoder.writeToBuffer(gpuBuffer.slice(0L, 96L), data);
// object = Unit.INSTANCE;
            }
            catch (Throwable bl) {
                object2 = bl;
                throw bl;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)object2);
            }
            GpuSampler linear = RenderSampler.linear();
            Supplier<String> supplier = ShaderHandsRenderer::compositeUserShader$lambda$1;
            GpuTextureView gpuTextureView = main.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            object2 = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
            object = null;
            try {
                RenderPass pass = (RenderPass)object2;
                boolean bl = false;
                pass.setPipeline(pipeline);
                pass.bindTexture("HandTex", hand.getColorAttachmentView(), linear);
                SimpleFramebuffer simpleFramebuffer6 = sceneB;
                Intrinsics.checkNotNull((Object)simpleFramebuffer6);
                pass.bindTexture("SceneTex", simpleFramebuffer6.getColorAttachmentView(), linear);
                pass.bindTexture("HandDepthTex", hand.getDepthAttachmentView(), RenderSampler.nearest());
                pass.bindTexture("BoundsTexL", boundsLeftView, RenderSampler.nearest());
                pass.bindTexture("BoundsTexR", boundsRightView, RenderSampler.nearest());
                GpuBuffer gpuBuffer = userBuffer;
                Intrinsics.checkNotNull((Object)gpuBuffer);
                pass.setUniform("HandParams", gpuBuffer);
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
        }
        catch (Throwable throwable) {
            INSTANCE.fail();
        }
        finally {
            handReady = false;
        }
    }

    private final float tanHalfFov() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        double fov = mc.options != null ? (double)((Number)mc.options.getFov().getValue()).intValue() : 70.0;
        fov = Math.max(30.0, Math.min(140.0, fov));
        return (float)Math.tan(Math.toRadians(fov) * 0.5);
    }

    private final void computeHandBounds() {
        this.setBounds(boundsAData, 1.0f, 1.0f, 0.0f, 0.0f);
        this.setBounds(boundsBData, 1.0f, 1.0f, 0.0f, 0.0f);
        int[] grid = handMaskCpu;
        if (!handMaskValid || grid == null) {
            return;
        }
        int n = 36864;
        int[] stamp = floodStamp;
        if (stamp == null || stamp.length != n) {
            floodStamp = stamp = new int[n];
            floodQueue = new int[n];
        }
        Intrinsics.checkNotNull((Object)floodQueue);
        int[] queue = floodQueue;
        int gen = ++floodGen;
        int best1 = -1;
        int best2 = -1;
        int[] bb1 = null;
        int[] bb2 = null;
        for (int s = 0; s < n; ++s) {
            if ((grid[s] >>> 24 & 0xFF) < 110 || stamp[s] == gen) continue;
            int qh = 0;
            int qt = 0;
            queue[qt++] = s;
            stamp[s] = gen;
            int minX = 256;
            int maxX = -1;
            int minY = 144;
            int maxY = -1;
            int count = 0;
            while (qh < qt) {
                int idx = queue[qh++];
                int gx = idx % 256;
                int gy = idx / 256;
                ++count;
                if (gx < minX) {
                    minX = gx;
                }
                if (gx > maxX) {
                    maxX = gx;
                }
                if (gy < minY) {
                    minY = gy;
                }
                if (gy > maxY) {
                    maxY = gy;
                }
                for (int dir = 0; dir < 4; ++dir) {
                    int nidx;
                    int nx = gx + (switch (dir) {
                        case 0 -> -1;
                        case 1 -> 1;
                        default -> 0;
                    });
                    int ny = gy + (switch (dir) {
                        case 2 -> -1;
                        case 3 -> 1;
                        default -> 0;
                    });
                    if (nx < 0 || nx >= 256 || ny < 0 || ny >= 144 || stamp[nidx = ny * 256 + nx] == gen || (grid[nidx] >>> 24 & 0xFF) < 110) continue;
                    stamp[nidx] = gen;
                    queue[qt++] = nidx;
                }
            }
            if (count < 12) continue;
            int[] nArray = new int[]{minX, minY, maxX, maxY};
            int[] bb = nArray;
            if (count > best1) {
                best2 = best1;
                bb2 = bb1;
                best1 = count;
                bb1 = bb;
                continue;
            }
            if (count <= best2) continue;
            best2 = count;
            bb2 = bb;
        }
        if (bb1 == null) {
            return;
        }
        if (bb2 == null) {
            this.toTexBounds(bb1, boundsAData);
            this.toTexBounds(bb1, boundsBData);
            return;
        }
        float c1 = (float)(bb1[0] + bb1[2]) * 0.5f;
        float c2 = (float)(bb2[0] + bb2[2]) * 0.5f;
        if (c1 <= c2) {
            this.toTexBounds(bb1, boundsAData);
            this.toTexBounds(bb2, boundsBData);
        } else {
            this.toTexBounds(bb2, boundsAData);
            this.toTexBounds(bb1, boundsBData);
        }
    }

    private final void toTexBounds(int[] gb, float[] out) {
        out[0] = (float)gb[0] / 256.0f;
        out[1] = (float)gb[1] / 144.0f;
        out[2] = (float)(gb[2] + 1) / 256.0f;
        out[3] = (float)(gb[3] + 1) / 144.0f;
    }

    private final void setBounds(float[] b, float x, float y, float z, float w) {
        b[0] = x;
        b[1] = y;
        b[2] = z;
        b[3] = w;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void writeBounds(SimpleFramebuffer target, float[] b) {
        if (target == null || boundsWritePipeline == null) {
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
            ByteBuffer data = stack.calloc(16);
            data.putFloat(0, b[0]);
            data.putFloat(4, b[1]);
            data.putFloat(8, b[2]);
            data.putFloat(12, b[3]);
            data.position(0);
            GpuBuffer gpuBuffer = boundsWriteBuffer;
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
        Supplier<String> supplier = ShaderHandsRenderer::writeBounds$lambda$1;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0));
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = boundsWritePipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            GpuBuffer gpuBuffer = boundsWriteBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("BoundsWrite", gpuBuffer);
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
    @JvmStatic
    public static final void compositePlain() {
        if (disabledAfterError || !handReady) {
            handReady = false;
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Framebuffer main = mc.getFramebuffer();
        SimpleFramebuffer hand = handFbo;
        if (main == null || main.getColorAttachmentView() == null || passthroughPipeline == null || hand == null) {
            handReady = false;
            return;
        }
        try {
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            Supplier<String> supplier = ShaderHandsRenderer::compositePlain$lambda$0;
            GpuTextureView gpuTextureView = main.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
            Throwable throwable = null;
            try {
                RenderPass pass = (RenderPass)autoCloseable;
                boolean bl = false;
                RenderPipeline renderPipeline = passthroughPipeline;
                Intrinsics.checkNotNull((Object)renderPipeline);
                pass.setPipeline(renderPipeline);
                pass.bindTexture("HandTex", hand.getColorAttachmentView(), RenderSampler.linear());
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
            INSTANCE.fail();
        }
        finally {
            handReady = false;
        }
    }

    @JvmStatic
    public static final boolean isHandMaskReady() {
        return !disabledAfterError && handFbo != null && targetWidth > 0 && targetHeight > 0;
    }

    private final void kawaseBlur(int width, int height, GpuSampler sampler) {
        float rx = 1.0f / (float)width;
        float ry = 1.0f / (float)height;
        int margin = (int)Math.ceil(5.2f) + 2;
        RenderPipeline renderPipeline = kawaseDownPipeline;
        Intrinsics.checkNotNull((Object)renderPipeline);
        SimpleFramebuffer simpleFramebuffer2 = sceneA;
        Intrinsics.checkNotNull((Object)simpleFramebuffer2);
        this.kawasePass(renderPipeline, sceneCopyTextureView, simpleFramebuffer2, rx, ry, sampler, this.quad(width, 1, margin), this.quad(height, 1, margin));
        RenderPipeline renderPipeline2 = kawaseDownPipeline;
        Intrinsics.checkNotNull((Object)renderPipeline2);
        SimpleFramebuffer simpleFramebuffer3 = sceneA;
        Intrinsics.checkNotNull((Object)simpleFramebuffer3);
        GpuTextureView gpuTextureView = simpleFramebuffer3.getColorAttachmentView();
        SimpleFramebuffer simpleFramebuffer4 = sceneB;
        Intrinsics.checkNotNull((Object)simpleFramebuffer4);
        this.kawasePass(renderPipeline2, gpuTextureView, simpleFramebuffer4, rx, ry, sampler, this.quad(width, 2, margin), this.quad(height, 2, margin));
        RenderPipeline renderPipeline3 = kawaseDownPipeline;
        Intrinsics.checkNotNull((Object)renderPipeline3);
        SimpleFramebuffer simpleFramebuffer5 = sceneB;
        Intrinsics.checkNotNull((Object)simpleFramebuffer5);
        GpuTextureView gpuTextureView2 = simpleFramebuffer5.getColorAttachmentView();
        SimpleFramebuffer simpleFramebuffer6 = sceneA;
        Intrinsics.checkNotNull((Object)simpleFramebuffer6);
        this.kawasePass(renderPipeline3, gpuTextureView2, simpleFramebuffer6, rx, ry, sampler, this.quad(width, 3, margin), this.quad(height, 3, margin));
        RenderPipeline renderPipeline4 = kawaseUpPipeline;
        Intrinsics.checkNotNull((Object)renderPipeline4);
        SimpleFramebuffer simpleFramebuffer7 = sceneA;
        Intrinsics.checkNotNull((Object)simpleFramebuffer7);
        GpuTextureView gpuTextureView3 = simpleFramebuffer7.getColorAttachmentView();
        SimpleFramebuffer simpleFramebuffer8 = sceneB;
        Intrinsics.checkNotNull((Object)simpleFramebuffer8);
        this.kawasePass(renderPipeline4, gpuTextureView3, simpleFramebuffer8, rx, ry, sampler, this.quad(width, 2, margin), this.quad(height, 2, margin));
        RenderPipeline renderPipeline5 = kawaseUpPipeline;
        Intrinsics.checkNotNull((Object)renderPipeline5);
        SimpleFramebuffer simpleFramebuffer9 = sceneB;
        Intrinsics.checkNotNull((Object)simpleFramebuffer9);
        GpuTextureView gpuTextureView4 = simpleFramebuffer9.getColorAttachmentView();
        SimpleFramebuffer simpleFramebuffer10 = sceneA;
        Intrinsics.checkNotNull((Object)simpleFramebuffer10);
        this.kawasePass(renderPipeline5, gpuTextureView4, simpleFramebuffer10, rx, ry, sampler, this.quad(width, 1, margin), this.quad(height, 1, margin));
        RenderPipeline renderPipeline6 = kawaseUpPipeline;
        Intrinsics.checkNotNull((Object)renderPipeline6);
        SimpleFramebuffer simpleFramebuffer11 = sceneA;
        Intrinsics.checkNotNull((Object)simpleFramebuffer11);
        GpuTextureView gpuTextureView5 = simpleFramebuffer11.getColorAttachmentView();
        SimpleFramebuffer simpleFramebuffer12 = sceneB;
        Intrinsics.checkNotNull((Object)simpleFramebuffer12);
        this.kawasePass(renderPipeline6, gpuTextureView5, simpleFramebuffer12, rx, ry, sampler, width, height);
    }

    private final int quad(int full, int shift, int margin) {
        return Math.min(full, (full >> shift) + margin);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void kawasePass(RenderPipeline pipeline, GpuTextureView source, SimpleFramebuffer target, float rx, float ry, GpuSampler sampler, int scissorW, int scissorH) {
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(16);
            data.putFloat(0, rx);
            data.putFloat(4, ry);
            data.putFloat(8, 2.6f);
            data.position(0);
            GpuBuffer gpuBuffer = kawaseBuffer;
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
        Supplier<String> supplier = ShaderHandsRenderer::kawasePass$lambda$1;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            pass.setPipeline(pipeline);
            if (scissorW < target.textureWidth || scissorH < target.textureHeight) {
                pass.enableScissor(0, 0, scissorW, scissorH);
            }
            pass.bindTexture("Image", source, sampler);
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void glass(GpuTextureView target, GpuTextureView scene, GpuTextureView handMask, GpuTextureView boundsL, GpuTextureView boundsR, int color, float saturation, float whiteLift, float distortion, float tintAmount, GpuSampler sampler) {
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(32);
            Intrinsics.checkNotNull((Object)data);
            INSTANCE.putColor(data, 0, color);
            data.putFloat(16, saturation);
            data.putFloat(20, whiteLift);
            data.putFloat(24, distortion);
            data.putFloat(28, tintAmount);
            data.position(0);
            GpuBuffer gpuBuffer = glassBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 32L), data);
// stack = Unit.INSTANCE;
        }
        catch (Throwable bl) {
            throwable = bl;
            throw bl;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        Supplier<String> supplier = ShaderHandsRenderer::glass$lambda$1;
        GpuTextureView gpuTextureView = target;
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = glassPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            pass.bindTexture("SceneTex", scene, sampler);
            pass.bindTexture("HandTex", handMask, sampler);
            pass.bindTexture("BoundsTexL", boundsL, RenderSampler.nearest());
            pass.bindTexture("BoundsTexR", boundsR, RenderSampler.nearest());
            GpuBuffer gpuBuffer = glassBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("GlassConfig", gpuBuffer);
            GpuBuffer palette = ClientPalette.buffer();
            if (palette != null) {
                pass.setUniform("PaletteParams", palette);
            }
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
    private final void glowDilate(GpuTextureView source, SimpleFramebuffer target, float dirX, float dirY, float radius, int[] gradient, GpuTextureView boundsL, GpuTextureView boundsR, GpuSampler sampler, boolean clear) {
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(96);
            data.putFloat(0, dirX);
            data.putFloat(4, dirY);
            data.putFloat(8, 1.0f / (float)target.textureWidth);
            data.putFloat(12, 1.0f / (float)target.textureHeight);
            data.putFloat(16, radius);
            data.putFloat(20, 0.5f);
            data.putFloat(24, 0.5f);
            data.putFloat(28, 0.5f);
            Intrinsics.checkNotNull((Object)data);
            INSTANCE.putGradient(data, 32, gradient);
            data.position(0);
            GpuBuffer gpuBuffer = glowBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 96L), data);
// stack = Unit.INSTANCE;
        }
        catch (Throwable bl) {
            throwable = bl;
            throw bl;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        Supplier<String> supplier = ShaderHandsRenderer::glowDilate$lambda$1;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, clear ? OptionalInt.of(0) : OptionalInt.empty());
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = glowDilatePipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            pass.bindTexture("MaskTex", source, sampler);
            pass.bindTexture("BoundsTexL", boundsL, RenderSampler.nearest());
            pass.bindTexture("BoundsTexR", boundsR, RenderSampler.nearest());
            GpuBuffer gpuBuffer = glowBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("GlowConfig", gpuBuffer);
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
    private final void glowGauss(GpuTextureView source, SimpleFramebuffer target, float dirX, float dirY, float glowRadius, int[] gradient, GpuTextureView boundsL, GpuTextureView boundsR, GpuSampler sampler) {
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(256);
            int support = Math.max(1, (int)Math.ceil(glowRadius * 2.0f));
            float sigma = Math.max(0.75f, glowRadius * 0.5f);
            float gy = (float)Math.exp(-0.5f / (sigma * sigma));
            float seed = 1.0f / (2.5066283f * sigma);
            data.putFloat(0, dirX);
            data.putFloat(4, dirY);
            data.putFloat(8, 1.0f / (float)target.textureWidth);
            data.putFloat(12, 1.0f / (float)target.textureHeight);
            data.putFloat(16, seed);
            data.putFloat(20, gy);
            data.putFloat(24, gy * gy);
            data.putFloat(28, support);
            data.putFloat(32, 0.5f);
            data.putFloat(36, 0.5f);
            data.putFloat(40, 0.5f);
            data.putFloat(44, 1.0f);
            Intrinsics.checkNotNull((Object)data);
            INSTANCE.putGradient(data, 48, gradient);
            INSTANCE.putGlowTheme(data, 112);
            data.putFloat(136, targetWidth > 0 ? (float)targetWidth / (float)target.textureWidth : 1.0f);
            data.position(0);
            GpuBuffer gpuBuffer = gaussBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 256L), data);
// stack = Unit.INSTANCE;
        }
        catch (Throwable bl) {
            throwable = bl;
            throw bl;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        Supplier<String> supplier = ShaderHandsRenderer::glowGauss$lambda$1;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0));
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = glowGaussPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            pass.bindTexture("TextureIn", source, sampler);
            pass.bindTexture("BoundsTexL", boundsL, RenderSampler.nearest());
            pass.bindTexture("BoundsTexR", boundsR, RenderSampler.nearest());
            GpuBuffer gpuBuffer = gaussBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("GaussConfig", gpuBuffer);
            ThemeWaveUniform.bind(pass);
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
    private final void outline(GpuTextureView target, GpuTextureView handMask, GpuTextureView glowTex, int[] gradient, GpuTextureView boundsL, GpuTextureView boundsR, float glowRadius, float glowStrength, float outlineWidth, boolean blending, GpuSampler sampler) {
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(128);
            data.putFloat(0, 1.0f / (float)targetWidth);
            data.putFloat(4, 1.0f / (float)targetHeight);
            data.putFloat(8, outlineWidth);
            data.putFloat(12, glowStrength);
            data.putFloat(16, 0.5f);
            data.putFloat(20, 0.5f);
            data.putFloat(24, 0.5f);
            data.putFloat(28, glowRadius);
            Intrinsics.checkNotNull((Object)data);
            INSTANCE.putGradient(data, 32, gradient);
            INSTANCE.putGlowTheme(data, 96);
            data.position(0);
            GpuBuffer gpuBuffer = outlineBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 128L), data);
// stack = Unit.INSTANCE;
        }
        catch (Throwable bl) {
            throwable = bl;
            throw bl;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        Supplier<String> supplier = ShaderHandsRenderer::outline$lambda$1;
        GpuTextureView gpuTextureView = target;
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
        throwable = null;
        try {
            RenderPipeline renderPipeline;
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            if (blending) {
                RenderPipeline renderPipeline2 = outlineAddPipeline;
                renderPipeline = renderPipeline2;
                Intrinsics.checkNotNull((Object)renderPipeline2);
            } else {
                RenderPipeline renderPipeline3 = outlinePipeline;
                renderPipeline = renderPipeline3;
                Intrinsics.checkNotNull((Object)renderPipeline3);
            }
            pass.setPipeline(renderPipeline);
            pass.bindTexture("BaseMaskTex", handMask, sampler);
            pass.bindTexture("OutlineMaskTex", handMask, sampler);
            pass.bindTexture("GlowTex", glowTex, sampler);
            pass.bindTexture("BoundsTexL", boundsL, RenderSampler.nearest());
            pass.bindTexture("BoundsTexR", boundsR, RenderSampler.nearest());
            GpuBuffer gpuBuffer = outlineBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("OutlineConfig", gpuBuffer);
            ThemeWaveUniform.bind(pass);
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

    private final void init() {
        if (disabledAfterError) {
            return;
        }
        try {
            if (kawaseDownPipeline == null) {
                kawaseDownPipeline = this.registerKawase(KAWASE_DOWN_PIPELINE_ID, KAWASE_DOWN_SHADER);
            }
            if (kawaseUpPipeline == null) {
                kawaseUpPipeline = this.registerKawase(KAWASE_UP_PIPELINE_ID, KAWASE_UP_SHADER);
            }
            if (glassPipeline == null) {
                glassPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(GLASS_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(GLASS_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("GlassConfig", UniformType.UNIFORM_BUFFER).withUniform("PaletteParams", UniformType.UNIFORM_BUFFER).withSampler("SceneTex").withSampler("HandTex").withSampler("BoundsTexL").withSampler("BoundsTexR").withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (glowDilatePipeline == null) {
                glowDilatePipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(GLOW_DILATE_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(GLOW_DILATE_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("GlowConfig", UniformType.UNIFORM_BUFFER).withSampler("MaskTex").withSampler("BoundsTexL").withSampler("BoundsTexR").withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (glowGaussPipeline == null) {
                glowGaussPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(GLOW_GAUSS_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(GLOW_GAUSS_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("GaussConfig", UniformType.UNIFORM_BUFFER).withUniform("ThemeWaveParams", UniformType.UNIFORM_BUFFER).withSampler("TextureIn").withSampler("BoundsTexL").withSampler("BoundsTexR").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (outlinePipeline == null) {
                outlinePipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(OUTLINE_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(OUTLINE_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("OutlineConfig", UniformType.UNIFORM_BUFFER).withUniform("ThemeWaveParams", UniformType.UNIFORM_BUFFER).withSampler("BaseMaskTex").withSampler("OutlineMaskTex").withSampler("GlowTex").withSampler("BoundsTexL").withSampler("BoundsTexR").withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (outlineAddPipeline == null) {
                outlineAddPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(OUTLINE_ADD_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(OUTLINE_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("OutlineConfig", UniformType.UNIFORM_BUFFER).withUniform("ThemeWaveParams", UniformType.UNIFORM_BUFFER).withSampler("BaseMaskTex").withSampler("OutlineMaskTex").withSampler("GlowTex").withSampler("BoundsTexL").withSampler("BoundsTexR").withBlend(BlendFunction.LIGHTNING).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (boundsInitPipeline == null) {
                boundsInitPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(BOUNDS_INIT_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(BOUNDS_INIT_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("InitConfig", UniformType.UNIFORM_BUFFER).withSampler("MaskTex").withSampler("SplitTex").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (boundsReducePipeline == null) {
                boundsReducePipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(BOUNDS_REDUCE_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(BOUNDS_REDUCE_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("ReduceConfig", UniformType.UNIFORM_BUFFER).withSampler("BoundsTex").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (boundsWritePipeline == null) {
                boundsWritePipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(BOUNDS_WRITE_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(BOUNDS_WRITE_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("BoundsWrite", UniformType.UNIFORM_BUFFER).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (passthroughPipeline == null) {
                passthroughPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(PASSTHROUGH_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(PASSTHROUGH_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withSampler("HandTex").withBlend(new BlendFunction(SourceFactor.ONE, DestFactor.ONE_MINUS_SRC_ALPHA)).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (handMaskPipeline == null) {
                handMaskPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(HANDMASK_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(HANDMASK_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withSampler("HandTex").withSampler("HandDepth").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            kawaseBuffer = this.ensureBuffer(kawaseBuffer, 16, "kimiko:shader_hands_kawase");
            glassBuffer = this.ensureBuffer(glassBuffer, 32, "kimiko:shader_hands_glass");
            glowBuffer = this.ensureBuffer(glowBuffer, 96, "kimiko:shader_hands_glow");
            gaussBuffer = this.ensureBuffer(gaussBuffer, 256, "kimiko:shader_hands_gauss");
            outlineBuffer = this.ensureBuffer(outlineBuffer, 128, "kimiko:shader_hands_outline");
            reduceBuffer = this.ensureBuffer(reduceBuffer, 16, "kimiko:shader_hands_reduce");
            initBuffer = this.ensureBuffer(initBuffer, 16, "kimiko:shader_hands_bounds_initcfg");
            boundsWriteBuffer = this.ensureBuffer(boundsWriteBuffer, 16, "kimiko:shader_hands_bounds_write");
        }
        catch (Throwable throwable) {
            this.fail();
        }
    }

    private final void computeBoundsInto(GpuTextureView handMask, float minX, float maxX, SimpleFramebuffer finalTarget) {
        SimpleFramebuffer simpleFramebuffer2 = boundsLeft;
        Intrinsics.checkNotNull((Object)simpleFramebuffer2);
        this.computeBounds(handMask, minX, maxX, 0, simpleFramebuffer2.getColorAttachmentView(), finalTarget);
    }

    private final void computeBounds(GpuTextureView handMask, float minX, float maxX, int mode, GpuTextureView splitView, SimpleFramebuffer finalTarget) {
        SimpleFramebuffer simpleFramebuffer2 = boundsChain.get(0);
        Intrinsics.checkNotNullExpressionValue((Object)simpleFramebuffer2, (String)"get(...)");
        this.boundsInit(handMask, simpleFramebuffer2, minX, maxX, mode, splitView);
        int n = boundsChain.size();
        int n2 = n - 2;
        for (int i = 0; i < n2; ++i) {
            SimpleFramebuffer src = (SimpleFramebuffer) (boundsChain.get(i));
            GpuTextureView gpuTextureView = src.getColorAttachmentView();
            SimpleFramebuffer simpleFramebuffer3 = boundsChain.get(i + 1);
            Intrinsics.checkNotNullExpressionValue((Object)simpleFramebuffer3, (String)"get(...)");
            this.boundsReduce(gpuTextureView, simpleFramebuffer3, src.textureWidth, src.textureHeight);
        }
        SimpleFramebuffer simpleFramebuffer4 = boundsChain.get(n - 2);
        Intrinsics.checkNotNullExpressionValue((Object)simpleFramebuffer4, (String)"get(...)");
        SimpleFramebuffer pen = simpleFramebuffer4;
        this.boundsReduce(pen.getColorAttachmentView(), finalTarget, pen.textureWidth, pen.textureHeight);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void boundsInit(GpuTextureView mask, SimpleFramebuffer target, float minX, float maxX, int mode, GpuTextureView splitView) {
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(16);
            data.putFloat(0, minX);
            data.putFloat(4, maxX);
            data.putFloat(8, mode);
            data.position(0);
            GpuBuffer gpuBuffer = initBuffer;
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
        Supplier<String> supplier = ShaderHandsRenderer::boundsInit$lambda$1;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0));
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = boundsInitPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            pass.bindTexture("MaskTex", mask, RenderSampler.nearest());
            pass.bindTexture("SplitTex", splitView, RenderSampler.nearest());
            GpuBuffer gpuBuffer = initBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("InitConfig", gpuBuffer);
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
    private final void boundsReduce(GpuTextureView source, SimpleFramebuffer target, int sourceWidth, int sourceHeight) {
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(16);
            data.putFloat(0, 1.0f / (float)sourceWidth);
            data.putFloat(4, 1.0f / (float)sourceHeight);
            data.position(0);
            GpuBuffer gpuBuffer = reduceBuffer;
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
        Supplier<String> supplier = ShaderHandsRenderer::boundsReduce$lambda$1;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0));
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = boundsReducePipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            pass.bindTexture("BoundsTex", source, RenderSampler.nearest());
            GpuBuffer gpuBuffer = reduceBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("ReduceConfig", gpuBuffer);
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

    private final RenderPipeline registerKawase(Identifier pipelineId, Identifier fragment) {
        RenderPipeline renderPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(pipelineId).withVertexShader(VERTEX).withFragmentShader(fragment).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("KawaseParams", UniformType.UNIFORM_BUFFER).withSampler("Image").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"register(...)");
        return renderPipeline;
    }

    private final GpuBuffer ensureBuffer(GpuBuffer buffer, int size, String name) {
        if (buffer != null && !buffer.isClosed() && buffer.size() >= (long)size) {
            return buffer;
        }
        GpuBuffer gpuBuffer = buffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        GpuBuffer gpuBuffer2 = RenderSystem.getDevice().createBuffer(() -> ShaderHandsRenderer.ensureBuffer$lambda$0(name), 136, (long)size);
        Intrinsics.checkNotNullExpressionValue((Object)gpuBuffer2, (String)"createBuffer(...)");
        return gpuBuffer2;
    }

    private final boolean ensureTargets(int width, int height) {
        GpuDevice device = RenderSystem.tryGetDevice();
        if (device == null || width <= 0 || height <= 0) {
            return false;
        }
        if (sceneCopyTexture != null && targetWidth == width && targetHeight == height) {
            return true;
        }
        this.closeTargets();
        GpuTexture gpuTexture = sceneCopyTexture = device.createTexture(ShaderHandsRenderer::ensureTargets$lambda$0, 5, TextureFormat.RGBA8, width, height, 1, 1);
        Intrinsics.checkNotNull((Object)gpuTexture);
        sceneCopyTextureView = device.createTextureView(gpuTexture);
        sceneA = new SimpleFramebuffer("kimiko_shader_hands_scene_a", width, height, false);
        sceneB = new SimpleFramebuffer("kimiko_shader_hands_scene_b", width, height, false);
        handFbo = new SimpleFramebuffer("kimiko_shader_hands_hand", width, height, true);
        glow = new SimpleFramebuffer("kimiko_shader_hands_glow", Math.max(1, width / 2), Math.max(1, height / 2), false);
        glowSwap = new SimpleFramebuffer("kimiko_shader_hands_glow_swap", Math.max(1, width / 2), Math.max(1, height / 2), false);
        boundsChain.clear();
        int bw = width;
        int bh = height;
        int level = 0;
        boundsChain.add(new SimpleFramebuffer("kimiko_shader_hands_bounds_" + level, bw, bh, false));
        while (bw > 1 || bh > 1) {
            bw = Math.max(1, bw / 2);
            bh = Math.max(1, bh / 2);
            boundsChain.add(new SimpleFramebuffer("kimiko_shader_hands_bounds_" + ++level, bw, bh, false));
        }
        boundsUnion = new SimpleFramebuffer("kimiko_shader_hands_bounds_union", 1, 1, false);
        boundsLeft = new SimpleFramebuffer("kimiko_shader_hands_bounds_left", 1, 1, false);
        boundsRight = new SimpleFramebuffer("kimiko_shader_hands_bounds_right", 1, 1, false);
        targetWidth = width;
        targetHeight = height;
        return true;
    }

    private final int[] gradientColors(int base) {
        if (ClientPalette.count() >= 2) {
            float bp = ClientPalette.phase() * 20.0f;
            int[] nArray = new int[]{0xFF000000 | ClientPalette.loopColor(bp + 0.75f) & 0xFFFFFF, 0xFF000000 | ClientPalette.loopColor(bp + 0.5f) & 0xFFFFFF, 0xFF000000 | ClientPalette.loopColor(bp + 0.25f) & 0xFFFFFF, 0xFF000000 | ClientPalette.loopColor(bp) & 0xFFFFFF};
            return nArray;
        }
        int[] nArray = new int[]{base, ColorEngine.lerpColor(base, -1, 0.1f), base, ColorEngine.lerpColor(base, -16777216, 0.1f)};
        return nArray;
    }

    private final void putGradient(ByteBuffer buffer, int offset, int[] gradient) {
        for (int i = 0; i < 4; ++i) {
            this.putColor(buffer, offset + i * 16, gradient[i]);
        }
    }

    private final void putColor(ByteBuffer buffer, int offset, int argb) {
        buffer.putFloat(offset, (float)(argb >> 16 & 0xFF) / 255.0f);
        buffer.putFloat(offset + 4, (float)(argb >> 8 & 0xFF) / 255.0f);
        buffer.putFloat(offset + 8, (float)(argb & 0xFF) / 255.0f);
        buffer.putFloat(offset + 12, (float)(argb >>> 24 & 0xFF) / 255.0f);
    }

    private final float gaussian(float x, float sigma) {
        float safe = Math.max(0.1f, sigma);
        return (float)(1.0 / Math.sqrt(Math.PI * 2 * (double)safe * (double)safe) * Math.exp((double)(-(x * x)) / (2.0 * (double)safe * (double)safe)));
    }

    private final float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    private final void reset() {
        sceneReady = false;
        handReady = false;
    }

    private final void fail() {
        disabledAfterError = true;
        if (capturing) {
            RenderSystem.outputColorTextureOverride = prevColorOverride;
            RenderSystem.outputDepthTextureOverride = prevDepthOverride;
            capturing = false;
        }
        this.closeTargets();
        this.closeBuffers();
        this.reset();
    }

    @JvmStatic
    public static final void clear() {
        INSTANCE.closeTargets();
        INSTANCE.closeBuffers();
        INSTANCE.reset();
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
        sceneA = this.destroy(sceneA);
        sceneB = this.destroy(sceneB);
        handFbo = this.destroy(handFbo);
        glow = this.destroy(glow);
        glowSwap = this.destroy(glowSwap);
        Iterator<SimpleFramebuffer> iterator = boundsChain.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<SimpleFramebuffer> iterator2 = iterator;
        while (iterator2.hasNext()) {
            SimpleFramebuffer t = (SimpleFramebuffer) (iterator2.next());
            t.delete();
        }
        boundsChain.clear();
        boundsUnion = this.destroy(boundsUnion);
        boundsLeft = this.destroy(boundsLeft);
        boundsRight = this.destroy(boundsRight);
        maskGrid = this.destroy(maskGrid);
        this.closeGridBounds();
        handMaskValid = false;
        targetWidth = -1;
        targetHeight = -1;
    }

    private final void closeGridBounds() {
        Iterator<SimpleFramebuffer> iterator = gridBoundsChain.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<SimpleFramebuffer> iterator2 = iterator;
        while (iterator2.hasNext()) {
            SimpleFramebuffer t = (SimpleFramebuffer) (iterator2.next());
            t.delete();
        }
        gridBoundsChain.clear();
        gridUnion = this.destroy(gridUnion);
        gridLeft = this.destroy(gridLeft);
        gridRight = this.destroy(gridRight);
    }

    private final SimpleFramebuffer destroy(SimpleFramebuffer target) {
        block0: {
            SimpleFramebuffer simpleFramebuffer2 = target;
            if (simpleFramebuffer2 == null) break block0;
            simpleFramebuffer2.delete();
        }
        return null;
    }

    private final void closeBuffers() {
        kawaseBuffer = this.closeBuffer(kawaseBuffer);
        glassBuffer = this.closeBuffer(glassBuffer);
        glowBuffer = this.closeBuffer(glowBuffer);
        gaussBuffer = this.closeBuffer(gaussBuffer);
        outlineBuffer = this.closeBuffer(outlineBuffer);
        reduceBuffer = this.closeBuffer(reduceBuffer);
        initBuffer = this.closeBuffer(initBuffer);
        maskReadBuffer = this.closeBuffer(maskReadBuffer);
        userBuffer = this.closeBuffer(userBuffer);
    }

    private final GpuBuffer closeBuffer(GpuBuffer buffer) {
        block0: {
            GpuBuffer gpuBuffer = buffer;
            if (gpuBuffer == null) break block0;
            gpuBuffer.close();
        }
        return null;
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }

    private static final String captureScene$lambda$0() {
        return "kimiko:shader_hands_clear";
    }

    private static final String renderMaskGrid$lambda$0() {
        return "kimiko:hand_mask_grid";
    }

    private static final String updateHandMask$lambda$0() {
        return "kimiko:hand_mask_read";
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static final void updateHandMask$lambda$1(int $px) {
        try {
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder mapEncoder = commandEncoder;
            GpuBuffer gpuBuffer = maskReadBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            AutoCloseable autoCloseable = (AutoCloseable)mapEncoder.mapBuffer(gpuBuffer, true, false);
            Throwable throwable = null;
            try {
                GpuBuffer.MappedView view = (GpuBuffer.MappedView)autoCloseable;
                boolean bl = false;
                ByteBuffer byteBuffer = view.data();
                Intrinsics.checkNotNullExpressionValue((Object)byteBuffer, (String)"data(...)");
                ByteBuffer data = byteBuffer;
                int cells = 36864;
                int[] grid = maskBackBuffer;
                if (grid == null || grid.length != cells) {
                    grid = new int[cells];
                }
                for (int n = 0; n < 144; ++n) {
                    for (int o = 0; o < 256; ++o) {
                        grid[n * 256 + o] = data.getInt((o + n * 256) * $px);
                    }
                }
                maskBackBuffer = handMaskCpu;
                handMaskCpu = grid;
                handMaskValid = true;
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
            // empty catch block
        }
    }

    private static final String composite$lambda$0() {
        return "kimiko:shader_hands_plain";
    }

    private static final String compositeUserShader$lambda$1() {
        return "kimiko:shader_hands_user";
    }

    private static final String writeBounds$lambda$1() {
        return "kimiko:shader_hands_bounds_write";
    }

    private static final String compositePlain$lambda$0() {
        return "kimiko:shader_hands_plain";
    }

    private static final String kawasePass$lambda$1() {
        return "kimiko:shader_hands_kawase";
    }

    private static final String glass$lambda$1() {
        return "kimiko:shader_hands_glass";
    }

    private static final String glowDilate$lambda$1() {
        return "kimiko:shader_hands_glow_dilate";
    }

    private static final String glowGauss$lambda$1() {
        return "kimiko:shader_hands_glow_gauss";
    }

    private static final String outline$lambda$1() {
        return "kimiko:shader_hands_outline";
    }

    private static final String boundsInit$lambda$1() {
        return "kimiko:shader_hands_bounds_init";
    }

    private static final String boundsReduce$lambda$1() {
        return "kimiko:shader_hands_bounds_reduce";
    }

    private static final String ensureBuffer$lambda$0(String $name) {
        return $name;
    }

    private static final String ensureTargets$lambda$0() {
        return "kimiko:shader_hands_scene_copy";
    }

    static {
        float[] fArray = new float[]{1.0f, 1.0f, 0.0f, 0.0f};
        boundsAData = fArray;
        fArray = new float[]{1.0f, 1.0f, 0.0f, 0.0f};
        boundsBData = fArray;
        gridBoundsChain = new ArrayList();
        boundsChain = new ArrayList();
        targetWidth = -1;
        targetHeight = -1;
        marchPts = new float[8];
    }
}

