/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.systems.GpuDevice
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  kotlin.Metadata
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.BufferBuilder
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.client.util.BufferAllocator
 *  net.minecraft.client.render.BuiltBuffer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.others;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexFormat;
import kotlin.Metadata;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.render.BuiltBuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\u000e\u001a\u00020\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\u0003R\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0011R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/utils/render/others/FullscreenQuadBuffer;", "", "<init>", "()V", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "Lkotlin/jvm/JvmStatic;", "getOrCreate", "()Lcom/mojang/blaze3d/buffers/GpuBuffer;", "Lnet/minecraft/BufferAllocator;", "allocator", "Lnet/minecraft/BuiltBuffer;", "buildFullscreenQuad", "(Lnet/minecraft/BufferAllocator;)Lnet/minecraft/BuiltBuffer;", "", "close", "", "VERTEX_COUNT", "I", "BUFFER_SIZE", "buffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "rtx.kimiko:kimiko"})
public final class FullscreenQuadBuffer {
    @NotNull
    public static final FullscreenQuadBuffer INSTANCE = new FullscreenQuadBuffer();
    private static final int VERTEX_COUNT = 6;
    private static final int BUFFER_SIZE = 6 * VertexFormats.POSITION.getVertexSize();
    @Nullable
    private static GpuBuffer buffer;

    private FullscreenQuadBuffer() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    @Nullable
    public static final GpuBuffer getOrCreate() {
        GpuDevice gpuDevice = RenderSystem.tryGetDevice();
        if (gpuDevice == null) {
            return null;
        }
        GpuDevice device = gpuDevice;
        GpuBuffer current = buffer;
        if (current != null && !current.isClosed()) {
            return current;
        }
        AutoCloseable autoCloseable = (AutoCloseable)new BufferAllocator(BUFFER_SIZE);
        Throwable throwable = null;
        try {
            GpuBuffer gpuBuffer;
            BufferAllocator allocator = (BufferAllocator)autoCloseable;
            boolean bl = false;
            AutoCloseable autoCloseable2 = (AutoCloseable)INSTANCE.buildFullscreenQuad(allocator);
            Throwable throwable2 = null;
            try {
                GpuBuffer created;
                BuiltBuffer meshData = (BuiltBuffer)autoCloseable2;
                boolean bl2 = false;
                GpuBuffer gpuBuffer2 = device.createBuffer(FullscreenQuadBuffer::getOrCreate$lambda$0$0$0, 32, meshData.getBuffer());
                Intrinsics.checkNotNullExpressionValue((Object)gpuBuffer2, (String)"createBuffer(...)");
                buffer = created = gpuBuffer2;
                gpuBuffer = created;
            }
            catch (Throwable throwable3) {
                try {
                    try {
                        throwable2 = throwable3;
                        throw throwable3;
                    }
                    catch (Throwable throwable4) {
                        AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable2, throwable2);
                        throw throwable4;
                    }
                }
                catch (Throwable throwable5) {
                    throwable = throwable5;
                    throw throwable5;
                }
            }
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable2, (Throwable)throwable2);
            GpuBuffer gpuBuffer3 = gpuBuffer;
            return gpuBuffer3;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
    }

    private final BuiltBuffer buildFullscreenQuad(BufferAllocator allocator) {
        BufferBuilder builder = new BufferBuilder(allocator, VertexFormat.DrawMode.TRIANGLES, VertexFormats.POSITION);
        builder.vertex(-1.0f, -1.0f, 0.0f);
        builder.vertex(1.0f, -1.0f, 0.0f);
        builder.vertex(1.0f, 1.0f, 0.0f);
        builder.vertex(-1.0f, -1.0f, 0.0f);
        builder.vertex(1.0f, 1.0f, 0.0f);
        builder.vertex(-1.0f, 1.0f, 0.0f);
        BuiltBuffer builtBuffer2 = builder.end();
        Intrinsics.checkNotNullExpressionValue((Object)builtBuffer2, (String)"buildOrThrow(...)");
        return builtBuffer2;
    }

    @JvmStatic
    public static final void close() {
        GpuBuffer gpuBuffer = buffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        buffer = null;
    }

    private static final String getOrCreate$lambda$0$0$0() {
        return "kimiko:fullscreen_quad";
    }
}

