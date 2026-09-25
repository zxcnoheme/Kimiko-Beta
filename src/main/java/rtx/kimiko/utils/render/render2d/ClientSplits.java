/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.systems.RenderSystem
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.system.MemoryStack
 */
package rtx.kimiko.utils.render.render2d;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.systems.RenderSystem;
import java.nio.ByteBuffer;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryStack;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0014\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003Jc\u0010\u0013\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0013\u0010\u0014Jc\u0010\u0015\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0015\u0010\u0014Jc\u0010\u001b\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001b\u0010\u0014J\u0013\u0010\u001c\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001c\u0010\u0003J\u0015\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0013\u0010 \u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b \u0010\u0003J\u0011\u0010!\u001a\u0004\u0018\u00010\u001dH\u0002\u00a2\u0006\u0004\b!\u0010\u001fR\u0014\u0010\"\u001a\u00020\u00128\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010#R\u0014\u0010%\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010#R\u0014\u0010&\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010#R\u0014\u0010'\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010#R\u0014\u0010(\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010#R\u0014\u0010)\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010#R\u0014\u0010*\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010#R\u0014\u0010+\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010#R\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0016\u0010/\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u0010#R\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u00100\u00a8\u00061"}, d2={"Lrtx/kimiko/utils/render/render2d/ClientSplits;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "reset", "", "parentCx", "parentCy", "parentHw", "parentHh", "childCx", "childCy", "childHw", "childHh", "childRadius", "k", "", "add", "(FFFFFFFFFF)I", "setBubble", "tailLeftX", "tailTopY", "tailHalfWidth", "tailHeight", "round", "addTail", "update", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "buffer", "()Lcom/mojang/blaze3d/buffers/GpuBuffer;", "closeBuffer", "ensureBuffer", "MAX_SPLITS", "I", "TAIL_SLOT", "BUBBLE_SLOT", "VEC4_PER_SPLIT", "FLOATS_PER_VEC4", "FLOATS_PER_SPLIT", "FLOATS", "DYNAMIC_FLOATS", "UNIFORM_BYTES", "", "data", "[F", "activeCount", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "rtx.kimiko:kimiko"})
public final class ClientSplits {
    @NotNull
    public static final ClientSplits INSTANCE = new ClientSplits();
    public static final int MAX_SPLITS = 16;
    private static final int TAIL_SLOT = 15;
    private static final int BUBBLE_SLOT = 14;
    private static final int VEC4_PER_SPLIT = 3;
    private static final int FLOATS_PER_VEC4 = 4;
    private static final int FLOATS_PER_SPLIT = 12;
    private static final int FLOATS = 192;
    private static final int DYNAMIC_FLOATS = 168;
    private static final int UNIFORM_BYTES = 768;
    @NotNull
    private static final float[] data = new float[192];
    private static int activeCount;
    @Nullable
    private static GpuBuffer buffer;

    private ClientSplits() {
    }

    @JvmStatic
    public static final void reset() {
        activeCount = 0;
        Arrays.fill(data, 0, 168, 0.0f);
    }

    @JvmStatic
    public static final int add(float parentCx, float parentCy, float parentHw, float parentHh, float childCx, float childCy, float childHw, float childHh, float childRadius, float k) {
        if (activeCount >= 14) {
            return 0;
        }
        int n = activeCount;
        activeCount = n + 1;
        int i = n;
        int o = i * 3 * 4;
        ClientSplits.data[o] = parentCx;
        ClientSplits.data[o + 1] = parentCy;
        ClientSplits.data[o + 2] = parentHw;
        ClientSplits.data[o + 3] = parentHh;
        ClientSplits.data[o + 4] = childCx;
        ClientSplits.data[o + 5] = childCy;
        ClientSplits.data[o + 6] = childHw;
        ClientSplits.data[o + 7] = childHh;
        ClientSplits.data[o + 8] = childRadius;
        ClientSplits.data[o + 9] = k;
        return i + 1;
    }

    @JvmStatic
    public static final int setBubble(float parentCx, float parentCy, float parentHw, float parentHh, float childCx, float childCy, float childHw, float childHh, float childRadius, float k) {
        int i = 14;
        int o = i * 12;
        ClientSplits.data[o] = parentCx;
        ClientSplits.data[o + 1] = parentCy;
        ClientSplits.data[o + 2] = parentHw;
        ClientSplits.data[o + 3] = parentHh;
        ClientSplits.data[o + 4] = childCx;
        ClientSplits.data[o + 5] = childCy;
        ClientSplits.data[o + 6] = childHw;
        ClientSplits.data[o + 7] = childHh;
        ClientSplits.data[o + 8] = childRadius;
        ClientSplits.data[o + 9] = k;
        ClientSplits.data[o + 10] = 0.0f;
        return i + 1;
    }

    @JvmStatic
    public static final int addTail(float parentCx, float parentCy, float parentHw, float parentHh, float tailLeftX, float tailTopY, float tailHalfWidth, float tailHeight, float round, float k) {
        int i = 15;
        int o = i * 3 * 4;
        ClientSplits.data[o] = parentCx;
        ClientSplits.data[o + 1] = parentCy;
        ClientSplits.data[o + 2] = parentHw;
        ClientSplits.data[o + 3] = parentHh;
        ClientSplits.data[o + 4] = tailLeftX;
        ClientSplits.data[o + 5] = tailTopY;
        ClientSplits.data[o + 6] = tailHalfWidth;
        ClientSplits.data[o + 7] = tailHeight;
        ClientSplits.data[o + 8] = round;
        ClientSplits.data[o + 9] = k;
        ClientSplits.data[o + 10] = 1.0f;
        return i + 1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void update() {
        GpuBuffer gpuBuffer = INSTANCE.ensureBuffer();
        if (gpuBuffer == null) {
            return;
        }
        GpuBuffer buf = gpuBuffer;
        try {
            AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
            Throwable throwable = null;
            try {
                MemoryStack stack = (MemoryStack)autoCloseable;
                boolean bl = false;
                ByteBuffer bytes = stack.calloc(768);
                for (int i = 0; i < 192; ++i) {
                    bytes.putFloat(i * 4, data[i]);
                }
                bytes.position(0);
                RenderSystem.getDevice().createCommandEncoder().writeToBuffer(buf.slice(0L, 768L), bytes);
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
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    @JvmStatic
    @Nullable
    public static final GpuBuffer buffer() {
        return INSTANCE.ensureBuffer();
    }

    @JvmStatic
    public static final void closeBuffer() {
        GpuBuffer gpuBuffer = buffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        buffer = null;
    }

    private final GpuBuffer ensureBuffer() {
        GpuBuffer current = buffer;
        if (current != null && !current.isClosed() && current.size() >= 768L) {
            return current;
        }
        ClientSplits.closeBuffer();
        try {
            GpuBuffer created = RenderSystem.getDevice().createBuffer(ClientSplits::ensureBuffer$lambda$0, 136, 768L);
            buffer = created;
            return created;
        }
        catch (RuntimeException ignored) {
            return null;
        }
    }

    private static final String ensureBuffer$lambda$0() {
        return "kimiko_client_splits";
    }
}

