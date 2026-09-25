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
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap
 *  it.unimi.dsi.fastutil.objects.ObjectCollection
 *  it.unimi.dsi.fastutil.objects.ObjectIterator
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.render.entity.state.EntityRenderState
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.client.render.command.OrderedRenderCommandQueueImpl
 *  net.minecraft.client.render.command.FallingBlockCommandRenderer
 *  net.minecraft.client.render.command.CustomCommandRenderer
 *  net.minecraft.client.render.command.ModelCommandRenderer
 *  net.minecraft.client.render.command.ItemCommandRenderer
 *  net.minecraft.client.render.command.BatchingRenderCommandQueue
 *  net.minecraft.client.render.command.ModelPartCommandRenderer
 *  net.minecraft.client.render.state.CameraRenderState
 *  net.minecraft.client.gl.GpuSampler
 *  net.minecraft.client.render.RenderLayers
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.VertexConsumerProvider
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.render.OutlineVertexConsumerProvider
 *  net.minecraft.client.gl.SimpleFramebuffer
 *  net.minecraft.client.render.block.BlockRenderManager
 *  net.minecraft.client.render.entity.EntityRenderManager
 *  net.minecraft.client.util.BufferAllocator
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.system.MemoryStack
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package rtx.kimiko.utils.render.modules.post.glowesp;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.SequencedMap;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.command.OrderedRenderCommandQueueImpl;
import net.minecraft.client.render.command.FallingBlockCommandRenderer;
import net.minecraft.client.render.command.CustomCommandRenderer;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.ItemCommandRenderer;
import net.minecraft.client.render.command.BatchingRenderCommandQueue;
import net.minecraft.client.render.command.ModelPartCommandRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.OutlineVertexConsumerProvider;
import net.minecraft.client.gl.SimpleFramebuffer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderManager;
import net.minecraft.client.util.BufferAllocator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.others.RenderSampler;
import rtx.kimiko.utils.render.others.profiler.RenderProfiler;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0088\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b.\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0085\u0001\u0010\u001b\u001a\u00020\u00192\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0007b\u0002\b\u001a\u00a2\u0006\u0004\b\u001b\u0010\u001cJ?\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001d2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u000f\u0010\"\u001a\u00020!H\u0002\u00a2\u0006\u0004\b\"\u0010#J\u0017\u0010&\u001a\u00020\u00112\u0006\u0010%\u001a\u00020$H\u0002\u00a2\u0006\u0004\b&\u0010'J=\u0010+\u001a\u00020\u00192\u0006\u0010)\u001a\u00020(2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010*\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b+\u0010,J9\u00103\u001a\u00020\u00192\u0006\u0010)\u001a\u00020(2\b\u0010.\u001a\u0004\u0018\u00010-2\u0006\u0010/\u001a\u00020-2\u0006\u00100\u001a\u00020-2\u0006\u00102\u001a\u000201H\u0002\u00a2\u0006\u0004\b3\u00104JC\u00109\u001a\u00020\u00192\u0006\u0010)\u001a\u00020(2\b\u00105\u001a\u0004\u0018\u00010-2\b\u0010/\u001a\u0004\u0018\u00010-2\u0006\u00107\u001a\u0002062\u0006\u00102\u001a\u0002012\u0006\u00108\u001a\u000201H\u0002\u00a2\u0006\u0004\b9\u0010:JS\u0010?\u001a\u00020\u00192\u0006\u0010)\u001a\u00020(2\u0006\u0010<\u001a\u00020;2\b\u00105\u001a\u0004\u0018\u00010-2\b\u0010=\u001a\u0004\u0018\u00010-2\u0006\u00107\u001a\u0002062\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010>\u001a\u00020\u00072\u0006\u00102\u001a\u000201H\u0002\u00a2\u0006\u0004\b?\u0010@J[\u0010E\u001a\u00020\u00192\u0006\u0010)\u001a\u00020(2\u0006\u00107\u001a\u00020-2\u0006\u0010A\u001a\u00020\u00112\b\u00105\u001a\u0004\u0018\u00010-2\b\u0010=\u001a\u0004\u0018\u00010-2\u0006\u0010B\u001a\u00020\u00072\u0006\u0010C\u001a\u00020\u00072\u0006\u0010D\u001a\u00020\u00072\u0006\u00102\u001a\u000201H\u0002\u00a2\u0006\u0004\bE\u0010FJ1\u0010G\u001a\u00020\u00192\u0006\u0010)\u001a\u00020(2\u0006\u00107\u001a\u00020-2\b\u00105\u001a\u0004\u0018\u00010-2\u0006\u00102\u001a\u000201H\u0002\u00a2\u0006\u0004\bG\u0010HJ\u000f\u0010I\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\bI\u0010\u0003J)\u0010N\u001a\u00020J2\b\u0010K\u001a\u0004\u0018\u00010J2\u0006\u0010D\u001a\u00020\r2\u0006\u0010M\u001a\u00020LH\u0002\u00a2\u0006\u0004\bN\u0010OJ\u001f\u0010R\u001a\u00020\u00112\u0006\u0010P\u001a\u00020\r2\u0006\u0010Q\u001a\u00020\rH\u0002\u00a2\u0006\u0004\bR\u0010SJ\u0013\u0010A\u001a\u00020\u0019H\u0007b\u0002\b\u001a\u00a2\u0006\u0004\bA\u0010\u0003J\u000f\u0010T\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\bT\u0010\u0003J\u001b\u0010U\u001a\u0004\u0018\u0001062\b\u00107\u001a\u0004\u0018\u000106H\u0002\u00a2\u0006\u0004\bU\u0010VJ\u000f\u0010W\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\bW\u0010\u0003J\u001b\u0010X\u001a\u0004\u0018\u00010J2\b\u0010K\u001a\u0004\u0018\u00010JH\u0002\u00a2\u0006\u0004\bX\u0010YJ'\u0010]\u001a\u00020\u00192\u0006\u0010K\u001a\u00020Z2\u0006\u0010[\u001a\u00020\r2\u0006\u0010\\\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b]\u0010^J\u0017\u0010a\u001a\u00020`2\u0006\u0010_\u001a\u00020LH\u0002\u00a2\u0006\u0004\ba\u0010bR\u0014\u0010c\u001a\u00020\r8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bc\u0010dR\u0014\u0010e\u001a\u00020\r8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\be\u0010dR\u0014\u0010f\u001a\u00020\r8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bf\u0010dR\u0014\u0010g\u001a\u00020\r8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bg\u0010dR\u0014\u0010h\u001a\u00020\r8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bh\u0010dR\u001c\u0010k\u001a\n j*\u0004\u0018\u00010i0i8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bk\u0010lR\u0014\u0010m\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bm\u0010dR\u0014\u0010n\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bn\u0010dR\u0014\u0010o\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bo\u0010dR\u0014\u0010p\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bp\u0010dR\u0014\u0010q\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bq\u0010dR\u0014\u0010r\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\br\u0010dR\u0014\u0010s\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bs\u0010dR\u0014\u0010t\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bt\u0010uR\u0014\u0010v\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bv\u0010uR\u0014\u0010w\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bw\u0010uR\u0014\u0010x\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bx\u0010uR\u0014\u0010y\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\by\u0010zR\u0014\u0010{\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b{\u0010zR\u0014\u0010|\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b|\u0010zR\u0014\u0010}\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b}\u0010zR\u0014\u0010~\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b~\u0010zR\u0014\u0010\u007f\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u007f\u0010zR\u0016\u0010\u0080\u0001\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010zR\u0016\u0010\u0081\u0001\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0081\u0001\u0010zR\u0016\u0010\u0082\u0001\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0082\u0001\u0010zR\u0016\u0010\u0083\u0001\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0083\u0001\u0010zR\u0016\u0010\u0084\u0001\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0084\u0001\u0010zR\u0016\u0010\u0085\u0001\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0085\u0001\u0010zR\u0016\u0010\u0086\u0001\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0086\u0001\u0010zR\u001b\u0010\u0087\u0001\u001a\u0004\u0018\u00010;8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0087\u0001\u0010\u0088\u0001R\u001b\u0010\u0089\u0001\u001a\u0004\u0018\u00010;8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0089\u0001\u0010\u0088\u0001R\u001b\u0010\u008a\u0001\u001a\u0004\u0018\u00010;8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008a\u0001\u0010\u0088\u0001R\u001b\u0010\u008b\u0001\u001a\u0004\u0018\u00010;8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008b\u0001\u0010\u0088\u0001R\u001b\u0010\u008c\u0001\u001a\u0004\u0018\u00010;8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008c\u0001\u0010\u0088\u0001R\u001b\u0010\u008d\u0001\u001a\u0004\u0018\u00010;8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008d\u0001\u0010\u0088\u0001R\u001b\u0010\u008e\u0001\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008e\u0001\u0010\u008f\u0001R\u001b\u0010\u0090\u0001\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0090\u0001\u0010\u008f\u0001R\u001b\u0010\u0091\u0001\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0091\u0001\u0010\u008f\u0001R\u001b\u0010\u0092\u0001\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0092\u0001\u0010\u008f\u0001R\u001b\u0010\u0093\u0001\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0093\u0001\u0010\u0094\u0001R\u001b\u0010\u0095\u0001\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0095\u0001\u0010\u0094\u0001R\u001b\u0010\u0096\u0001\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0096\u0001\u0010\u0094\u0001R\u001b\u0010\u0097\u0001\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0097\u0001\u0010\u0094\u0001R \u0010\u0099\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u0001060\u0098\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0099\u0001\u0010\u009a\u0001R\u0018\u0010\u009b\u0001\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u009b\u0001\u0010dR\u0018\u0010\u009c\u0001\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u009c\u0001\u0010dR\u0019\u0010\u009d\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009d\u0001\u0010\u009e\u0001R\u0018\u0010\u009f\u0001\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u009f\u0001\u0010dR\u0016\u0010\u00a0\u0001\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u00a0\u0001\u0010dR\u0018\u0010\u00a2\u0001\u001a\u00030\u00a1\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a2\u0001\u0010\u00a3\u0001R\u0018\u0010\u00a5\u0001\u001a\u00030\u00a4\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a5\u0001\u0010\u00a6\u0001R\u0019\u0010\"\u001a\u0004\u0018\u00010!8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\"\u0010\u00a7\u0001R+\u0010«\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u00a9\u00010\u00a8\u0001j\n\u0012\u0005\u0012\u00030\u00a9\u0001`\u00aa\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b«\u0001\u0010\u00ac\u0001R\u0018\u0010\u00ae\u0001\u001a\u00030\u00ad\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00ae\u0001\u0010\u00af\u0001R\u0018\u0010\u00b1\u0001\u001a\u00030\u00b0\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b1\u0001\u0010\u00b2\u0001R\u0018\u0010\u00b4\u0001\u001a\u00030\u00b3\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b4\u0001\u0010\u00b5\u0001R\u0018\u0010\u00b7\u0001\u001a\u00030\u00b6\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b7\u0001\u0010\u00b8\u0001R\u0018\u0010\u00ba\u0001\u001a\u00030\u00b9\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00ba\u0001\u0010»\u0001\u00a8\u0006\u00bc\u0001"}, d2={"Lrtx/kimiko/utils/render/modules/post/glowesp/GlowEspRenderer;", "", "<init>", "()V", "", "Lnet/minecraft/LivingEntity;", "targets", "", "partialTick", "Lnet/minecraft/MatrixStack;", "worldPose", "Lnet/minecraft/Vec3d;", "cameraPos", "", "mode", "iterations", "divider", "", "renderChams", "renderOutline", "", "rects", "depths", "", "colors", "", "Lkotlin/jvm/JvmStatic;", "render", "(Ljava/util/List;FLnet/minecraft/MatrixStack;Lnet/minecraft/Vec3d;IIFZZ[F[F[I)V", "Lnet/minecraft/MinecraftClient;", "mc", "captureSilhouette", "(Lnet/minecraft/MinecraftClient;Ljava/util/List;FLnet/minecraft/MatrixStack;Lnet/minecraft/Vec3d;)V", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "scratchSource", "()Lnet/minecraft/VertexConsumerProvider$Immediate;", "", "v", "finite", "(D)Z", "Lcom/mojang/blaze3d/systems/CommandEncoder;", "encoder", "hasDepth", "uploadChams", "(Lcom/mojang/blaze3d/systems/CommandEncoder;[F[F[IZ)V", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "silhouetteView", "entityDepth", "sceneDepth", "Lnet/minecraft/GpuSampler;", "sampler", "occlude", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lnet/minecraft/GpuSampler;)V", "source", "Lnet/minecraft/SimpleFramebuffer;", "target", "depthSampler", "chamsPass", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lnet/minecraft/SimpleFramebuffer;Lnet/minecraft/GpuSampler;Lnet/minecraft/GpuSampler;)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "check", "checkEnabled", "kawase", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lnet/minecraft/SimpleFramebuffer;FFLnet/minecraft/GpuSampler;)V", "clear", "dirX", "dirY", "size", "outlinePass", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lcom/mojang/blaze3d/textures/GpuTextureView;ZLcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;FFFLnet/minecraft/GpuSampler;)V", "blit", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuTextureView;Lnet/minecraft/GpuSampler;)V", "init", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "buffer", "", "name", "ensureBuffer", "(Lcom/mojang/blaze3d/buffers/GpuBuffer;ILjava/lang/String;)Lcom/mojang/blaze3d/buffers/GpuBuffer;", "width", "height", "ensureTargets", "(II)Z", "closeTargets", "destroy", "(Lnet/minecraft/SimpleFramebuffer;)Lnet/minecraft/SimpleFramebuffer;", "closeBuffers", "closeBuffer", "(Lcom/mojang/blaze3d/buffers/GpuBuffer;)Lcom/mojang/blaze3d/buffers/GpuBuffer;", "Ljava/nio/ByteBuffer;", "offset", "argb", "putColor", "(Ljava/nio/ByteBuffer;II)V", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "MODE_OUTER", "I", "MODE_INNER", "MODE_BOTH", "MAX_ITERATIONS", "MAX_RECTS", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "LOGGER", "Lorg/slf4j/Logger;", "CHAMS_RECTS_OFFSET", "CHAMS_META_OFFSET", "CHAMS_COLORS_OFFSET", "CHAMS_UNIFORM_SIZE", "KAWASE_UNIFORM_SIZE", "OUTLINE_UNIFORM_SIZE", "OCCLUDE_UNIFORM_SIZE", "INTERMEDIATE_DIVIDER", "F", "DEPTH_NEAR", "DEPTH_BIAS", "DEPTH_BIAS_SLOPE", "OCCLUDE_PIPELINE_ID", "Lnet/minecraft/Identifier;", "CHAMS_PIPELINE_ID", "KAWASE_DOWN_PIPELINE_ID", "KAWASE_UP_PIPELINE_ID", "OUTLINE_PIPELINE_ID", "BLIT_PIPELINE_ID", "VERTEX", "OCCLUDE_SHADER", "CHAMS_SHADER", "KAWASE_DOWN_SHADER", "KAWASE_UP_SHADER", "OUTLINE_SHADER", "BLIT_SHADER", "occludePipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "chamsPipeline", "kawaseDownPipeline", "kawaseUpPipeline", "outlinePipeline", "blitPipeline", "chamsBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "kawaseBuffer", "outlineBuffer", "occludeBuffer", "silhouette", "Lnet/minecraft/SimpleFramebuffer;", "visible", "chams", "outlineTemp", "", "levels", "[Lnet/minecraft/SimpleFramebuffer;", "targetWidth", "targetHeight", "disabledAfterError", "Z", "consecutiveErrors", "MAX_CONSECUTIVE_ERRORS", "Lnet/minecraft/OrderedRenderCommandQueueImpl;", "submitStorage", "Lnet/minecraft/OrderedRenderCommandQueueImpl;", "Lnet/minecraft/OutlineVertexConsumerProvider;", "outlineSource", "Lnet/minecraft/OutlineVertexConsumerProvider;", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "Ljava/util/ArrayList;", "Lnet/minecraft/BufferAllocator;", "Lkotlin/collections/ArrayList;", "scratchAllocators", "Ljava/util/ArrayList;", "Lnet/minecraft/ModelCommandRenderer;", "modelFeatureRenderer", "Lnet/minecraft/ModelCommandRenderer;", "Lnet/minecraft/ModelPartCommandRenderer;", "modelPartFeatureRenderer", "Lnet/minecraft/ModelPartCommandRenderer;", "Lnet/minecraft/ItemCommandRenderer;", "itemFeatureRenderer", "Lnet/minecraft/ItemCommandRenderer;", "Lnet/minecraft/FallingBlockCommandRenderer;", "blockFeatureRenderer", "Lnet/minecraft/FallingBlockCommandRenderer;", "Lnet/minecraft/CustomCommandRenderer;", "customFeatureRenderer", "Lnet/minecraft/CustomCommandRenderer;", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nGlowEspRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GlowEspRenderer.kt\nrtx/kimiko/utils/render/modules/post/glowesp/GlowEspRenderer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,694:1\n1#2:695\n*E\n"})
public final class GlowEspRenderer {
    @NotNull
    public static final GlowEspRenderer INSTANCE = new GlowEspRenderer();
    public static final int MODE_OUTER = 0;
    public static final int MODE_INNER = 1;
    public static final int MODE_BOTH = 2;
    public static final int MAX_ITERATIONS = 5;
    public static final int MAX_RECTS = 32;
    private static final Logger LOGGER = LoggerFactory.getLogger((String)"Kimiko/GlowEsp");
    private static final int CHAMS_RECTS_OFFSET = 16;
    private static final int CHAMS_META_OFFSET = 528;
    private static final int CHAMS_COLORS_OFFSET = 1040;
    private static final int CHAMS_UNIFORM_SIZE = 3088;
    private static final int KAWASE_UNIFORM_SIZE = 32;
    private static final int OUTLINE_UNIFORM_SIZE = 32;
    private static final int OCCLUDE_UNIFORM_SIZE = 16;
    private static final float INTERMEDIATE_DIVIDER = 12.0f;
    private static final float DEPTH_NEAR = 0.05f;
    private static final float DEPTH_BIAS = 0.02f;
    private static final float DEPTH_BIAS_SLOPE = 0.001f;
    @NotNull
    private static final Identifier OCCLUDE_PIPELINE_ID = INSTANCE.id("pipeline/post/glowesp/occlude");
    @NotNull
    private static final Identifier CHAMS_PIPELINE_ID = INSTANCE.id("pipeline/post/glowesp/chams");
    @NotNull
    private static final Identifier KAWASE_DOWN_PIPELINE_ID = INSTANCE.id("pipeline/post/glowesp/kawase_down");
    @NotNull
    private static final Identifier KAWASE_UP_PIPELINE_ID = INSTANCE.id("pipeline/post/glowesp/kawase_up");
    @NotNull
    private static final Identifier OUTLINE_PIPELINE_ID = INSTANCE.id("pipeline/post/glowesp/outline");
    @NotNull
    private static final Identifier BLIT_PIPELINE_ID = INSTANCE.id("pipeline/post/glowesp/blit");
    @NotNull
    private static final Identifier VERTEX = INSTANCE.id("post/glowesp/glowesp");
    @NotNull
    private static final Identifier OCCLUDE_SHADER = INSTANCE.id("post/glowesp/occlude");
    @NotNull
    private static final Identifier CHAMS_SHADER = INSTANCE.id("post/glowesp/chams");
    @NotNull
    private static final Identifier KAWASE_DOWN_SHADER = INSTANCE.id("post/glowesp/kawase_down");
    @NotNull
    private static final Identifier KAWASE_UP_SHADER = INSTANCE.id("post/glowesp/kawase_up");
    @NotNull
    private static final Identifier OUTLINE_SHADER = INSTANCE.id("post/glowesp/outline");
    @NotNull
    private static final Identifier BLIT_SHADER = INSTANCE.id("post/glowesp/blit");
    @Nullable
    private static RenderPipeline occludePipeline;
    @Nullable
    private static RenderPipeline chamsPipeline;
    @Nullable
    private static RenderPipeline kawaseDownPipeline;
    @Nullable
    private static RenderPipeline kawaseUpPipeline;
    @Nullable
    private static RenderPipeline outlinePipeline;
    @Nullable
    private static RenderPipeline blitPipeline;
    @Nullable
    private static GpuBuffer chamsBuffer;
    @Nullable
    private static GpuBuffer kawaseBuffer;
    @Nullable
    private static GpuBuffer outlineBuffer;
    @Nullable
    private static GpuBuffer occludeBuffer;
    @Nullable
    private static SimpleFramebuffer silhouette;
    @Nullable
    private static SimpleFramebuffer visible;
    @Nullable
    private static SimpleFramebuffer chams;
    @Nullable
    private static SimpleFramebuffer outlineTemp;
    @NotNull
    private static final SimpleFramebuffer[] levels;
    private static int targetWidth;
    private static int targetHeight;
    private static boolean disabledAfterError;
    private static int consecutiveErrors;
    private static final int MAX_CONSECUTIVE_ERRORS = 60;
    @NotNull
    private static final OrderedRenderCommandQueueImpl submitStorage;
    @NotNull
    private static final OutlineVertexConsumerProvider outlineSource;
    @Nullable
    private static VertexConsumerProvider.Immediate scratchSource;
    @NotNull
    private static final ArrayList<BufferAllocator> scratchAllocators;
    @NotNull
    private static final ModelCommandRenderer modelFeatureRenderer;
    @NotNull
    private static final ModelPartCommandRenderer modelPartFeatureRenderer;
    @NotNull
    private static final ItemCommandRenderer itemFeatureRenderer;
    @NotNull
    private static final FallingBlockCommandRenderer blockFeatureRenderer;
    @NotNull
    private static final CustomCommandRenderer customFeatureRenderer;

    private GlowEspRenderer() {
    }

    @JvmStatic
    public static final void render(@Nullable List<? extends LivingEntity> targets, float partialTick, @Nullable MatrixStack worldPose, @Nullable Vec3d cameraPos, int mode, int iterations, float divider, boolean renderChams, boolean renderOutline, @Nullable float[] rects, @Nullable float[] depths, @Nullable int[] colors) {
        block14: {
            if (disabledAfterError || targets == null || targets.isEmpty() || cameraPos == null) {
                return;
            }
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            Framebuffer main = mc.getFramebuffer();
            if (main == null || main.getColorAttachmentView() == null) {
                return;
            }
            INSTANCE.init();
            if (chamsPipeline == null || kawaseDownPipeline == null || kawaseUpPipeline == null || outlinePipeline == null || blitPipeline == null || occludePipeline == null || chamsBuffer == null || kawaseBuffer == null || outlineBuffer == null || occludeBuffer == null) {
                return;
            }
            if (!INSTANCE.ensureTargets(main.textureWidth, main.textureHeight)) {
                return;
            }
            try {
                int i;
                RenderProfiler.Scope captureScope = RenderProfiler.begin("world.glowesp.capture");
                INSTANCE.captureSilhouette(mc, targets, partialTick, worldPose, cameraPos);
                RenderProfiler.end(captureScope);
                CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
                Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
                CommandEncoder encoder = commandEncoder;
                GpuSampler linear = RenderSampler.linear();
                GpuSampler nearest = RenderSampler.nearest();
                GpuTextureView sceneDepth = main.getDepthAttachmentView();
                SimpleFramebuffer simpleFramebuffer2 = silhouette;
                Intrinsics.checkNotNull((Object)simpleFramebuffer2);
                GpuTextureView entityDepth = simpleFramebuffer2.getDepthAttachmentView();
                GpuTextureView baseView = null;
                if (sceneDepth != null && entityDepth != null) {
                    RenderProfiler.Scope occludeScope = RenderProfiler.begin("world.glowesp.occlude");
                    SimpleFramebuffer simpleFramebuffer3 = silhouette;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer3);
                    INSTANCE.occlude(encoder, simpleFramebuffer3.getColorAttachmentView(), entityDepth, sceneDepth, nearest);
                    RenderProfiler.end(occludeScope);
                    SimpleFramebuffer simpleFramebuffer4 = visible;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer4);
                    baseView = simpleFramebuffer4.getColorAttachmentView();
                } else {
                    SimpleFramebuffer simpleFramebuffer5 = silhouette;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer5);
                    baseView = simpleFramebuffer5.getColorAttachmentView();
                }
                RenderProfiler.Scope chamsScope = RenderProfiler.begin("world.glowesp.chams");
                INSTANCE.uploadChams(encoder, rects, depths, colors, entityDepth != null);
                GpuTextureView gpuTextureView = entityDepth;
                if (gpuTextureView == null) {
                    gpuTextureView = baseView;
                }
                SimpleFramebuffer simpleFramebuffer6 = chams;
                Intrinsics.checkNotNull((Object)simpleFramebuffer6);
                INSTANCE.chamsPass(encoder, baseView, gpuTextureView, simpleFramebuffer6, linear, nearest);
                RenderProfiler.end(chamsScope);
                SimpleFramebuffer simpleFramebuffer7 = chams;
                Intrinsics.checkNotNull((Object)simpleFramebuffer7);
                GpuTextureView chamsView = simpleFramebuffer7.getColorAttachmentView();
                if (renderOutline) {
                    RenderProfiler.Scope outlineScope = RenderProfiler.begin("world.glowesp.outline");
                    SimpleFramebuffer simpleFramebuffer8 = outlineTemp;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer8);
                    GpuTextureView gpuTextureView2 = simpleFramebuffer8.getColorAttachmentView();
                    Intrinsics.checkNotNull((Object)gpuTextureView2);
                    INSTANCE.outlinePass(encoder, gpuTextureView2, true, chamsView, chamsView, 1.0f, 0.0f, 1.0f, linear);
                    GpuTextureView gpuTextureView3 = main.getColorAttachmentView();
                    Intrinsics.checkNotNull((Object)gpuTextureView3);
                    SimpleFramebuffer simpleFramebuffer9 = outlineTemp;
                    Intrinsics.checkNotNull((Object)simpleFramebuffer9);
                    INSTANCE.outlinePass(encoder, gpuTextureView3, false, simpleFramebuffer9.getColorAttachmentView(), chamsView, 0.0f, 1.0f, 1.0f, linear);
                    RenderProfiler.end(outlineScope);
                }
                RenderProfiler.Scope bloomScope = RenderProfiler.begin("world.glowesp.bloom");
                int steps = MathHelper.clamp((int)iterations, (int)1, (int)5);
                RenderPipeline renderPipeline = kawaseDownPipeline;
                Intrinsics.checkNotNull((Object)renderPipeline);
                SimpleFramebuffer simpleFramebuffer10 = levels[1];
                Intrinsics.checkNotNull((Object)simpleFramebuffer10);
                INSTANCE.kawase(encoder, renderPipeline, chamsView, null, simpleFramebuffer10, 12.0f, 0.0f, linear);
                for (i = 1; i < steps; ++i) {
                    RenderPipeline renderPipeline2 = kawaseDownPipeline;
                    Intrinsics.checkNotNull((Object)renderPipeline2);
                    SimpleFramebuffer simpleFramebuffer11 = levels[i];
                    Intrinsics.checkNotNull((Object)simpleFramebuffer11);
                    GpuTextureView gpuTextureView4 = simpleFramebuffer11.getColorAttachmentView();
                    SimpleFramebuffer simpleFramebuffer12 = levels[i + 1];
                    Intrinsics.checkNotNull((Object)simpleFramebuffer12);
                    INSTANCE.kawase(encoder, renderPipeline2, gpuTextureView4, null, simpleFramebuffer12, 12.0f, 0.0f, linear);
                }
                for (i = steps; 1 < i; --i) {
                    RenderPipeline renderPipeline3 = kawaseUpPipeline;
                    Intrinsics.checkNotNull((Object)renderPipeline3);
                    SimpleFramebuffer simpleFramebuffer13 = levels[i];
                    Intrinsics.checkNotNull((Object)simpleFramebuffer13);
                    GpuTextureView gpuTextureView5 = simpleFramebuffer13.getColorAttachmentView();
                    SimpleFramebuffer simpleFramebuffer14 = levels[i];
                    Intrinsics.checkNotNull((Object)simpleFramebuffer14);
                    GpuTextureView gpuTextureView6 = simpleFramebuffer14.getColorAttachmentView();
                    SimpleFramebuffer simpleFramebuffer15 = levels[i - 1];
                    Intrinsics.checkNotNull((Object)simpleFramebuffer15);
                    INSTANCE.kawase(encoder, renderPipeline3, gpuTextureView5, gpuTextureView6, simpleFramebuffer15, 12.0f, 0.0f, linear);
                }
                SimpleFramebuffer simpleFramebuffer16 = levels[1];
                Intrinsics.checkNotNull((Object)simpleFramebuffer16);
                GpuTextureView blurredView = simpleFramebuffer16.getColorAttachmentView();
                GpuTextureView inView = mode == 1 ? chamsView : blurredView;
                GpuTextureView checkView = mode == 0 ? chamsView : blurredView;
                RenderPipeline renderPipeline4 = kawaseUpPipeline;
                Intrinsics.checkNotNull((Object)renderPipeline4);
                SimpleFramebuffer simpleFramebuffer17 = levels[0];
                Intrinsics.checkNotNull((Object)simpleFramebuffer17);
                INSTANCE.kawase(encoder, renderPipeline4, inView, checkView, simpleFramebuffer17, divider, 1.0f, linear);
                GpuTextureView gpuTextureView7 = main.getColorAttachmentView();
                Intrinsics.checkNotNull((Object)gpuTextureView7);
                SimpleFramebuffer simpleFramebuffer18 = levels[0];
                Intrinsics.checkNotNull((Object)simpleFramebuffer18);
                INSTANCE.blit(encoder, gpuTextureView7, simpleFramebuffer18.getColorAttachmentView(), linear);
                RenderProfiler.end(bloomScope);
                if (renderChams) {
                    GpuTextureView gpuTextureView8 = main.getColorAttachmentView();
                    Intrinsics.checkNotNull((Object)gpuTextureView8);
                    INSTANCE.blit(encoder, gpuTextureView8, chamsView, linear);
                }
                consecutiveErrors = 0;
            }
            catch (Throwable throwable) {
                if (consecutiveErrors == 0) {
                    LOGGER.error("[GlowEsp] render pass failed (will rebuild and retry)", throwable);
                }
                int n = consecutiveErrors;
                consecutiveErrors = n + 1;
                INSTANCE.closeTargets();
                INSTANCE.closeBuffers();
                if (consecutiveErrors < 60) break block14;
                disabledAfterError = true;
                LOGGER.error("[GlowEsp] disabled after {} consecutive failures (GPU/driver unsupported?)", (Object)consecutiveErrors);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void captureSilhouette(MinecraftClient mc, List<? extends LivingEntity> targets, float partialTick, MatrixStack worldPose, Vec3d cameraPos) {
        EntityRenderManager entityRenderManager2 = mc.getEntityRenderDispatcher();
        Intrinsics.checkNotNullExpressionValue((Object)entityRenderManager2, (String)"getEntityRenderDispatcher(...)");
        EntityRenderManager dispatcher = entityRenderManager2;
        CameraRenderState cameraRenderState2 = mc.worldRenderer.worldRenderState.cameraRenderState;
        Intrinsics.checkNotNullExpressionValue((Object)cameraRenderState2, (String)"cameraRenderState");
        CameraRenderState cameraState = cameraRenderState2;
        MatrixStack matrixStack2 = worldPose;
        if (matrixStack2 == null) {
            matrixStack2 = new MatrixStack();
        }
        MatrixStack pose = matrixStack2;
        BlockRenderManager blockRenderManager2 = mc.getBlockRenderManager();
        Intrinsics.checkNotNullExpressionValue((Object)blockRenderManager2, (String)"getBlockRenderer(...)");
        BlockRenderManager blockRenderer = blockRenderManager2;
        CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
        Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
        CommandEncoder encoder = commandEncoder;
        SimpleFramebuffer simpleFramebuffer2 = silhouette;
        Intrinsics.checkNotNull((Object)simpleFramebuffer2);
        SimpleFramebuffer silhouetteTarget = simpleFramebuffer2;
        Supplier<String> supplier = GlowEspRenderer::captureSilhouette$lambda$0;
        GpuTextureView gpuTextureView = silhouetteTarget.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0), silhouetteTarget.getDepthAttachmentView(), OptionalDouble.of(1.0));
        Throwable throwable = null;
        try {
            RenderPass it = (RenderPass)autoCloseable;
            boolean bl = false;
// it = Unit.INSTANCE;
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        GpuTextureView prevColor = RenderSystem.outputColorTextureOverride;
        GpuTextureView prevDepth = RenderSystem.outputDepthTextureOverride;
        RenderSystem.outputColorTextureOverride = silhouetteTarget.getColorAttachmentView();
        RenderSystem.outputDepthTextureOverride = silhouetteTarget.getDepthAttachmentView();
        try {
            VertexConsumerProvider.Immediate scratch = this.scratchSource();
            for (LivingEntity livingEntity2 : targets) {
                submitStorage.clear();
                try {
                    EntityRenderState state = dispatcher.getAndUpdateRenderState((Entity)livingEntity2, partialTick);
                    if (state == null) continue;
                    state.outlineColor = 0;
                    double x = MathHelper.lerp((double)partialTick, (double)livingEntity2.lastRenderX, (double)livingEntity2.getX()) - cameraPos.x;
                    double y = MathHelper.lerp((double)partialTick, (double)livingEntity2.lastRenderY, (double)livingEntity2.getY()) - cameraPos.y;
                    double z = MathHelper.lerp((double)partialTick, (double)livingEntity2.lastRenderZ, (double)livingEntity2.getZ()) - cameraPos.z;
                    if (!this.finite(x) || !this.finite(y) || !this.finite(z)) continue;
                    dispatcher.render(state, cameraState, x, y, z, pose, (OrderedRenderCommandQueue)submitStorage);
                }
                catch (Exception ignored) {
                    continue;
                }
                for (BatchingRenderCommandQueue collection : submitStorage.getBatchingQueues().values()) {
                    try {
                        modelFeatureRenderer.render(collection, scratch, outlineSource, scratch);
                    }
                    catch (Throwable throwable3) {
                        // empty catch block
                    }
                    try {
                        modelPartFeatureRenderer.render(collection, scratch, outlineSource, scratch);
                    }
                    catch (Throwable throwable4) {
                        // empty catch block
                    }
                    try {
                        itemFeatureRenderer.render(collection, scratch, outlineSource);
                    }
                    catch (Throwable throwable5) {
                        // empty catch block
                    }
                    if (blockRenderer != null) {
                        try {
                            blockFeatureRenderer.render(collection, scratch, blockRenderer, outlineSource);
                        }
                        catch (Throwable throwable6) {
                            // empty catch block
                        }
                    }
                    try {
                        customFeatureRenderer.render(collection, scratch);
                    }
                    catch (Throwable throwable7) {}
                }
                try {
                    scratch.draw();
                }
                catch (Throwable throwable8) {}
            }
        }
        finally {
            RenderSystem.outputColorTextureOverride = prevColor;
            RenderSystem.outputDepthTextureOverride = prevDepth;
            submitStorage.clear();
        }
    }

    private final VertexConsumerProvider.Immediate scratchSource() {
        VertexConsumerProvider.Immediate source = scratchSource;
        if (source == null) {
            Object2ObjectLinkedOpenHashMap fixed = new Object2ObjectLinkedOpenHashMap();
            RenderLayer[] class_1921Array = new RenderLayer[]{RenderLayers.armorEntityGlint(), RenderLayers.glint(), RenderLayers.glintTranslucent(), RenderLayers.entityGlint()};
            for (RenderLayer renderLayer2 : class_1921Array) {
                Intrinsics.checkNotNull((Object)renderLayer2);
                RenderLayer type = renderLayer2;
                BufferAllocator builder = new BufferAllocator(type.getExpectedBufferSize());
                scratchAllocators.add(builder);
                ((Map)fixed).put(type, builder);
            }
            BufferAllocator fallback = new BufferAllocator(0x200000);
            scratchAllocators.add(fallback);
            scratchSource = source = VertexConsumerProvider.immediate((SequencedMap)((SequencedMap)fixed), (BufferAllocator)fallback);
        }
        return source;
    }

    private final boolean finite(double v) {
        return Math.abs(v) <= Double.MAX_VALUE && Math.abs(v) < 1000000.0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void uploadChams(CommandEncoder encoder, float[] rects, float[] depths, int[] colors, boolean hasDepth) {
        int count = rects == null ? 0 : Math.min(32, rects.length / 4);
        float far = 0.0f;
        far = 256.0f;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.options != null) {
            far = Math.max(192.0f, (float)(((Number)mc.options.getViewDistance().getValue()).intValue() + 1) * 16.0f);
        }
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            int i;
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(3088);
            data.putFloat(0, count);
            data.putFloat(4, 0.05f);
            data.putFloat(8, far);
            data.putFloat(12, hasDepth && depths != null ? 1.0f : 0.0f);
            for (i = 0; i < count; ++i) {
                int offset = 16 + i * 16;
                Intrinsics.checkNotNull((Object)rects);
                data.putFloat(offset, rects[i * 4]);
                data.putFloat(offset + 4, rects[i * 4 + 1]);
                data.putFloat(offset + 8, rects[i * 4 + 2]);
                data.putFloat(offset + 12, rects[i * 4 + 3]);
                data.putFloat(528 + i * 16, depths != null && depths.length > i ? depths[i] : 1.0f);
            }
            int n = count * 4;
            for (i = 0; i < n; ++i) {
                int argb = colors != null && colors.length > i ? colors[i] : -1;
                Intrinsics.checkNotNull((Object)data);
                INSTANCE.putColor(data, 1040 + i * 16, argb);
            }
            data.position(0);
            GpuBuffer gpuBuffer = chamsBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 3088L), data);
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
    private final void occlude(CommandEncoder encoder, GpuTextureView silhouetteView, GpuTextureView entityDepth, GpuTextureView sceneDepth, GpuSampler sampler) {
        float far = 0.0f;
        far = 256.0f;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.options != null) {
            far = Math.max(192.0f, (float)(((Number)mc.options.getViewDistance().getValue()).intValue() + 1) * 16.0f);
        }
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(16);
            data.putFloat(0, 0.05f);
            data.putFloat(4, far);
            data.putFloat(8, 0.02f);
            data.putFloat(12, 0.001f);
            data.position(0);
            GpuBuffer gpuBuffer = occludeBuffer;
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
        Supplier<String> supplier = GlowEspRenderer::occlude$lambda$1;
        SimpleFramebuffer simpleFramebuffer2 = visible;
        Intrinsics.checkNotNull((Object)simpleFramebuffer2);
        GpuTextureView gpuTextureView = simpleFramebuffer2.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0));
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = occludePipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            pass.bindTexture("SilhouetteTex", silhouetteView, sampler);
            pass.bindTexture("EntityDepth", entityDepth, sampler);
            pass.bindTexture("SceneDepth", sceneDepth, sampler);
            GpuBuffer gpuBuffer = occludeBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("OccludeParams", gpuBuffer);
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
    private final void chamsPass(CommandEncoder encoder, GpuTextureView source, GpuTextureView entityDepth, SimpleFramebuffer target, GpuSampler sampler, GpuSampler depthSampler) {
        Supplier<String> supplier = GlowEspRenderer::chamsPass$lambda$0;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0));
        Throwable throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = chamsPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            pass.bindTexture("SilhouetteTex", source, sampler);
            pass.bindTexture("EntityDepth", entityDepth, depthSampler);
            GpuBuffer gpuBuffer = chamsBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("ChamsParams", gpuBuffer);
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
    private final void kawase(CommandEncoder encoder, RenderPipeline pipeline, GpuTextureView source, GpuTextureView check, SimpleFramebuffer target, float divider, float checkEnabled, GpuSampler sampler) {
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(32);
            data.putFloat(0, 1.0f);
            data.putFloat(4, 1.0f);
            data.putFloat(8, 1.0f / (float)target.textureWidth);
            data.putFloat(12, 1.0f / (float)target.textureHeight);
            data.putFloat(16, target.textureWidth);
            data.putFloat(20, target.textureHeight);
            data.putFloat(24, divider);
            data.putFloat(28, checkEnabled);
            data.position(0);
            GpuBuffer gpuBuffer = kawaseBuffer;
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
        Supplier<String> supplier = GlowEspRenderer::kawase$lambda$1;
        GpuTextureView gpuTextureView = target.getColorAttachmentView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.of(0));
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            pass.setPipeline(pipeline);
            pass.bindTexture("InTexture", source, sampler);
            if (pipeline == kawaseUpPipeline) {
                GpuTextureView gpuTextureView2 = check;
                if (gpuTextureView2 == null) {
                    gpuTextureView2 = source;
                }
                pass.bindTexture("CheckTexture", gpuTextureView2, sampler);
            }
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
    private final void outlinePass(CommandEncoder encoder, GpuTextureView target, boolean clear, GpuTextureView source, GpuTextureView check, float dirX, float dirY, float size, GpuSampler sampler) {
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(32);
            data.putFloat(0, 1.0f / (float)targetWidth);
            data.putFloat(4, 1.0f / (float)targetHeight);
            data.putFloat(8, dirX);
            data.putFloat(12, dirY);
            data.putFloat(16, size);
            data.position(0);
            GpuBuffer gpuBuffer = outlineBuffer;
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
        autoCloseable = (AutoCloseable)encoder.createRenderPass(GlowEspRenderer::outlinePass$lambda$1, target, clear ? OptionalInt.of(0) : OptionalInt.empty());
        throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = outlinePipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            pass.bindTexture("TextureIn", source, sampler);
            GpuTextureView gpuTextureView = check;
            if (gpuTextureView == null) {
                gpuTextureView = source;
            }
            pass.bindTexture("TextureToCheck", gpuTextureView, sampler);
            GpuBuffer gpuBuffer = outlineBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            pass.setUniform("OutlineParams", gpuBuffer);
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
    private final void blit(CommandEncoder encoder, GpuTextureView target, GpuTextureView source, GpuSampler sampler) {
        AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(GlowEspRenderer::blit$lambda$0, target, OptionalInt.empty());
        Throwable throwable = null;
        try {
            RenderPass pass = (RenderPass)autoCloseable;
            boolean bl = false;
            RenderPipeline renderPipeline = blitPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            pass.setPipeline(renderPipeline);
            pass.bindTexture("InTexture", source, sampler);
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
            if (occludePipeline == null) {
                occludePipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(OCCLUDE_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(OCCLUDE_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("OccludeParams", UniformType.UNIFORM_BUFFER).withSampler("SilhouetteTex").withSampler("EntityDepth").withSampler("SceneDepth").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (chamsPipeline == null) {
                chamsPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(CHAMS_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(CHAMS_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("ChamsParams", UniformType.UNIFORM_BUFFER).withSampler("SilhouetteTex").withSampler("EntityDepth").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (kawaseDownPipeline == null) {
                kawaseDownPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(KAWASE_DOWN_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(KAWASE_DOWN_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("KawaseParams", UniformType.UNIFORM_BUFFER).withSampler("InTexture").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (kawaseUpPipeline == null) {
                kawaseUpPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(KAWASE_UP_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(KAWASE_UP_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("KawaseParams", UniformType.UNIFORM_BUFFER).withSampler("InTexture").withSampler("CheckTexture").withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (outlinePipeline == null) {
                outlinePipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(OUTLINE_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(OUTLINE_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("OutlineParams", UniformType.UNIFORM_BUFFER).withSampler("TextureIn").withSampler("TextureToCheck").withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            if (blitPipeline == null) {
                blitPipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(BLIT_PIPELINE_ID).withVertexShader(VERTEX).withFragmentShader(BLIT_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withSampler("InTexture").withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            }
            chamsBuffer = this.ensureBuffer(chamsBuffer, 3088, "kimiko:glow_esp_chams");
            kawaseBuffer = this.ensureBuffer(kawaseBuffer, 32, "kimiko:glow_esp_kawase");
            outlineBuffer = this.ensureBuffer(outlineBuffer, 32, "kimiko:glow_esp_outline");
            occludeBuffer = this.ensureBuffer(occludeBuffer, 16, "kimiko:glow_esp_occlude");
        }
        catch (Throwable throwable) {
            LOGGER.error("[GlowEsp] pipeline/shader init failed — effect disabled", throwable);
            disabledAfterError = true;
            occludePipeline = null;
            chamsPipeline = null;
            kawaseDownPipeline = null;
            kawaseUpPipeline = null;
            outlinePipeline = null;
            blitPipeline = null;
            this.closeBuffers();
        }
    }

    private final GpuBuffer ensureBuffer(GpuBuffer buffer, int size, String name) {
        if (buffer != null && !buffer.isClosed() && buffer.size() >= (long)size) {
            return buffer;
        }
        GpuBuffer gpuBuffer = buffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        GpuBuffer gpuBuffer2 = RenderSystem.getDevice().createBuffer(() -> GlowEspRenderer.ensureBuffer$lambda$0(name), 136, (long)size);
        Intrinsics.checkNotNullExpressionValue((Object)gpuBuffer2, (String)"createBuffer(...)");
        return gpuBuffer2;
    }

    private final boolean ensureTargets(int width, int height) {
        GpuDevice device = RenderSystem.tryGetDevice();
        if (device == null || width <= 0 || height <= 0) {
            return false;
        }
        if (silhouette != null && visible != null && chams != null && outlineTemp != null && levels[0] != null && targetWidth == width && targetHeight == height) {
            return true;
        }
        this.closeTargets();
        silhouette = new SimpleFramebuffer("kimiko_glow_esp_silhouette", width, height, true);
        visible = new SimpleFramebuffer("kimiko_glow_esp_visible", width, height, false);
        chams = new SimpleFramebuffer("kimiko_glow_esp_chams", width, height, false);
        outlineTemp = new SimpleFramebuffer("kimiko_glow_esp_outline", width, height, false);
        int n = levels.length;
        for (int i = 0; i < n; ++i) {
            int scale = i == 0 ? 1 : 1 << i - 1;
            GlowEspRenderer.levels[i] = new SimpleFramebuffer("kimiko_glow_esp_level_" + i, Math.max(1, width / scale), Math.max(1, height / scale), false);
        }
        targetWidth = width;
        targetHeight = height;
        return true;
    }

    @JvmStatic
    public static final void clear() {
        INSTANCE.closeTargets();
        INSTANCE.closeBuffers();
        scratchSource = null;
        Iterator<BufferAllocator> iterator = scratchAllocators.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<BufferAllocator> iterator2 = iterator;
        while (iterator2.hasNext()) {
            BufferAllocator bufferAllocator2 = iterator2.next();
            Intrinsics.checkNotNullExpressionValue((Object)bufferAllocator2, (String)"next(...)");
            BufferAllocator builder = bufferAllocator2;
            try {
                builder.close();
            }
            catch (Throwable throwable) {}
        }
        scratchAllocators.clear();
    }

    private final void closeTargets() {
        silhouette = this.destroy(silhouette);
        visible = this.destroy(visible);
        chams = this.destroy(chams);
        outlineTemp = this.destroy(outlineTemp);
        int n = levels.length;
        for (int i = 0; i < n; ++i) {
            GlowEspRenderer.levels[i] = this.destroy(levels[i]);
        }
        targetWidth = -1;
        targetHeight = -1;
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
        chamsBuffer = this.closeBuffer(chamsBuffer);
        kawaseBuffer = this.closeBuffer(kawaseBuffer);
        outlineBuffer = this.closeBuffer(outlineBuffer);
        occludeBuffer = this.closeBuffer(occludeBuffer);
    }

    private final GpuBuffer closeBuffer(GpuBuffer buffer) {
        block0: {
            GpuBuffer gpuBuffer = buffer;
            if (gpuBuffer == null) break block0;
            gpuBuffer.close();
        }
        return null;
    }

    private final void putColor(ByteBuffer buffer, int offset, int argb) {
        buffer.putFloat(offset, (float)(argb >> 16 & 0xFF) / 255.0f);
        buffer.putFloat(offset + 4, (float)(argb >> 8 & 0xFF) / 255.0f);
        buffer.putFloat(offset + 8, (float)(argb & 0xFF) / 255.0f);
        buffer.putFloat(offset + 12, (float)(argb >>> 24 & 0xFF) / 255.0f);
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }

    private static final String captureSilhouette$lambda$0() {
        return "kimiko:glow_esp_clear";
    }

    private static final String occlude$lambda$1() {
        return "kimiko:glow_esp_occlude";
    }

    private static final String chamsPass$lambda$0() {
        return "kimiko:glow_esp_chams";
    }

    private static final String kawase$lambda$1() {
        return "kimiko:glow_esp_kawase";
    }

    private static final String outlinePass$lambda$1() {
        return "kimiko:glow_esp_outline";
    }

    private static final String blit$lambda$0() {
        return "kimiko:glow_esp_blit";
    }

    private static final String ensureBuffer$lambda$0(String $name) {
        return $name;
    }

    static {
        levels = new SimpleFramebuffer[6];
        targetWidth = -1;
        targetHeight = -1;
        submitStorage = new OrderedRenderCommandQueueImpl();
        outlineSource = new OutlineVertexConsumerProvider();
        scratchAllocators = new ArrayList();
        modelFeatureRenderer = new ModelCommandRenderer();
        modelPartFeatureRenderer = new ModelPartCommandRenderer();
        itemFeatureRenderer = new ItemCommandRenderer();
        blockFeatureRenderer = new FallingBlockCommandRenderer();
        customFeatureRenderer = new CustomCommandRenderer();
    }
}

