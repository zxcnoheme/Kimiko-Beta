/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.systems.RenderPass
 *  com.mojang.blaze3d.systems.RenderSystem
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.core.batch;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.core.batch.PageAllocator;
import rtx.kimiko.utils.render.core.uniform.UniformLayout;
import rtx.kimiko.utils.render.core.uniform.UniformWriter;
import rtx.kimiko.utils.render.render2d.BatchOverflow;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00060\u0003j\u0002`\u0004B1\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\n\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00028\u0000H$\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0012H\u0014\u00a2\u0006\u0004\b\u0015\u0010\u0016J'\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\nH\u0014\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0014\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001f\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001dH\u0016\u00a2\u0006\u0004\b\u001f\u0010 J\u0017\u0010#\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020!H\u0014\u00a2\u0006\u0004\b#\u0010$J\r\u0010%\u001a\u00020\n\u00a2\u0006\u0004\b%\u0010&J\u0015\u0010'\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n\u00a2\u0006\u0004\b'\u0010(J\u0015\u0010)\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n\u00a2\u0006\u0004\b)\u0010(J\u0017\u0010+\u001a\u00020*2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u00a2\u0006\u0004\b+\u0010,J\u000f\u0010-\u001a\u00020\u0012H\u0016\u00a2\u0006\u0004\b-\u0010\u0016J\u0015\u0010.\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00028\u0000\u00a2\u0006\u0004\b.\u0010/J\r\u00100\u001a\u00020\u0012\u00a2\u0006\u0004\b0\u0010\u0016J!\u00101\u001a\u00020\u00122\b\u0010\"\u001a\u0004\u0018\u00010!2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u00a2\u0006\u0004\b1\u00102J\u0011\u00104\u001a\u0004\u0018\u000103H\u0002\u00a2\u0006\u0004\b4\u00105J\u000f\u00106\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b6\u0010\u0016J\u000f\u00107\u001a\u00020\u0012H\u0016\u00a2\u0006\u0004\b7\u0010\u0016R\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u00108\u001a\u0004\b9\u0010:R\u0017\u0010\b\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\b\u0010;\u001a\u0004\b<\u0010=R\u0017\u0010\t\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\t\u00108\u001a\u0004\b>\u0010:R\u0017\u0010\u000b\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010?\u001a\u0004\b@\u0010&R\u0017\u0010\f\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\f\u0010?\u001a\u0004\bA\u0010&R\u0014\u0010C\u001a\u00020B8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bC\u0010DR\u001c\u0010F\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010E8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u0010GR\u0014\u0010H\u001a\u00020\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010?R\u0014\u0010I\u001a\u00020\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010?R\u0014\u0010K\u001a\u00020J8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bK\u0010LR\u0018\u0010M\u001a\u0004\u0018\u0001038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010NR\u0016\u0010O\u001a\u00020*8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bO\u0010PR\u001a\u0010S\u001a\b\u0012\u0004\u0012\u00020\u001d0E8&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\bQ\u0010R\u00a8\u0006T"}, d2={"Lrtx/kimiko/utils/render/core/batch/UiBatch;", "", "T", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "", "name", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "layout", "uniformName", "", "pageSize", "maxPages", "<init>", "(Ljava/lang/String;Lrtx/kimiko/utils/render/core/uniform/UniformLayout;Ljava/lang/String;II)V", "Lrtx/kimiko/utils/render/core/uniform/UniformWriter;", "writer", "item", "", "write", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Ljava/lang/Object;)V", "beforeWrite", "()V", "slot", "writeExtra", "(Lrtx/kimiko/utils/render/core/uniform/UniformWriter;Ljava/lang/Object;I)V", "count", "afterWrite", "(I)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "pageIndexOf", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)I", "Lcom/mojang/blaze3d/systems/RenderPass;", "pass", "bindExtraUniforms", "(Lcom/mojang/blaze3d/systems/RenderPass;)V", "capacity", "()I", "pageOf", "(I)I", "localOf", "", "isOwnerOf", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Z", "beginGuiFrame", "reserve", "(Ljava/lang/Object;)I", "prepareBuffers", "bindParams", "(Lcom/mojang/blaze3d/systems/RenderPass;Lcom/mojang/blaze3d/pipeline/RenderPipeline;)V", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "ensureBuffer", "()Lcom/mojang/blaze3d/buffers/GpuBuffer;", "closeBuffer", "close", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "getLayout", "()Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "getUniformName", "I", "getPageSize", "getMaxPages", "Lrtx/kimiko/utils/render/core/batch/PageAllocator;", "allocator", "Lrtx/kimiko/utils/render/core/batch/PageAllocator;", "", "items", "[Ljava/lang/Object;", "pageBytes", "totalBytes", "Ljava/nio/ByteBuffer;", "hostBuffer", "Ljava/nio/ByteBuffer;", "gpuBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "dirty", "Z", "getPipelines", "()[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipelines", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nUiBatch.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UiBatch.kt\nrtx/kimiko/utils/render/core/batch/UiBatch\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,241:1\n14266#2,2:242\n*S KotlinDebug\n*F\n+ 1 UiBatch.kt\nrtx/kimiko/utils/render/core/batch/UiBatch\n*L\n119#1:242,2\n*E\n"})
public abstract class UiBatch<T>
implements AutoCloseable {
    @NotNull
    private final String name;
    @NotNull
    private final UniformLayout layout;
    @NotNull
    private final String uniformName;
    private final int pageSize;
    private final int maxPages;
    @NotNull
    private final PageAllocator allocator;
    @NotNull
    private final Object[] items;
    private final int pageBytes;
    private final int totalBytes;
    @NotNull
    private final ByteBuffer hostBuffer;
    @Nullable
    private GpuBuffer gpuBuffer;
    private boolean dirty;

    public UiBatch(@NotNull String name, @NotNull UniformLayout layout, @NotNull String uniformName, int pageSize, int maxPages) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)layout, (String)"layout");
        Intrinsics.checkNotNullParameter((Object)uniformName, (String)"uniformName");
        this.name = name;
        this.layout = layout;
        this.uniformName = uniformName;
        this.pageSize = pageSize;
        this.maxPages = maxPages;
        this.allocator = new PageAllocator(this.pageSize, this.maxPages);
        this.items = new Object[this.pageSize * this.maxPages];
        this.pageBytes = this.layout.byteSize(this.pageSize);
        this.totalBytes = this.pageBytes * this.maxPages;
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(this.totalBytes).order(ByteOrder.nativeOrder());
        Intrinsics.checkNotNullExpressionValue((Object)byteBuffer, (String)"order(...)");
        this.hostBuffer = byteBuffer;
    }

    public /* synthetic */ UiBatch(String string, UniformLayout uniformLayout, String string2, int n, int n2, int n3, DefaultConstructorMarker defaultConstructorMarker) {
        this(string, uniformLayout, string2, n, ((n3 & 0x10) != 0 ? 1 : n2));
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final UniformLayout getLayout() {
        return this.layout;
    }

    @NotNull
    public final String getUniformName() {
        return this.uniformName;
    }

    public final int getPageSize() {
        return this.pageSize;
    }

    public final int getMaxPages() {
        return this.maxPages;
    }

    @NotNull
    public abstract RenderPipeline[] getPipelines();

    protected abstract void write(@NotNull UniformWriter var1, @NotNull T var2);

    protected void beforeWrite() {
    }

    protected void writeExtra(@NotNull UniformWriter writer, @NotNull T item, int slot) {
        Intrinsics.checkNotNullParameter((Object)writer, (String)"writer");
        Intrinsics.checkNotNullParameter(item, (String)"item");
    }

    protected void afterWrite(int count) {
    }

    public int pageIndexOf(@NotNull RenderPipeline pipeline) {
        Intrinsics.checkNotNullParameter((Object)pipeline, (String)"pipeline");
        return 0;
    }

    protected void bindExtraUniforms(@NotNull RenderPass pass) {
        Intrinsics.checkNotNullParameter((Object)pass, (String)"pass");
    }

    public final int capacity() {
        return this.allocator.getCapacity();
    }

    public final int pageOf(int slot) {
        return this.allocator.pageOf(slot);
    }

    public final int localOf(int slot) {
        return this.allocator.offsetInPage(slot);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean isOwnerOf(@Nullable RenderPipeline pipeline) {
        if (pipeline == null) return false;
        RenderPipeline[] $this$any$iv = this.getPipelines();
        boolean $i$f$any = false;
        int n = 0;
        int n2 = $this$any$iv.length;
        while (n < n2) {
            RenderPipeline element$iv;
            RenderPipeline it = element$iv = $this$any$iv[n];
            boolean bl = false;
            if (it == pipeline) {
                return true;
            }
            boolean bl2 = false;
            if (bl2) {
                return true;
            }
            ++n;
        }
        return false;
    }

    public void beginGuiFrame() {
        int previouslyUsed = this.allocator.used();
        this.allocator.reset();
        if (previouslyUsed > 0) {
            Arrays.fill(this.items, 0, previouslyUsed, null);
        }
        this.dirty = false;
    }

    public final synchronized int reserve(@NotNull T item) {
        Intrinsics.checkNotNullParameter(item, (String)"item");
        int slot = this.allocator.next();
        if (slot < 0) {
            return BatchOverflow.drop(this.name, this.allocator.getCapacity());
        }
        this.items[slot] = item;
        this.dirty = true;
        return slot;
    }

    public final void prepareBuffers() {
        int count = this.allocator.used();
        if (count <= 0 || !this.dirty) {
            return;
        }
        GpuBuffer gpuBuffer = this.ensureBuffer();
        if (gpuBuffer == null) {
            return;
        }
        GpuBuffer buffer = gpuBuffer;
        try {
            this.hostBuffer.clear();
            this.beforeWrite();
            for (int index = 0; index < count; ++index) {
                T item = (T)this.items[index];
                if (item == null) continue;
                UniformWriter writer = this.layout.writer(this.hostBuffer, index);
                this.write(writer, item);
                this.writeExtra(writer, item, index);
            }
            this.afterWrite(count);
            int bytes = this.layout.byteSize(count);
            this.hostBuffer.position(0);
            this.hostBuffer.limit(bytes);
            RenderSystem.getDevice().createCommandEncoder().writeToBuffer(buffer.slice(0L, (long)bytes), this.hostBuffer);
            this.hostBuffer.clear();
            this.dirty = false;
        }
        catch (RuntimeException ignored) {
            this.dirty = true;
        }
    }

    public final void bindParams(@Nullable RenderPass pass, @Nullable RenderPipeline pipeline) {
        if (pass == null || this.allocator.used() <= 0) {
            return;
        }
        if (this.dirty) {
            this.prepareBuffers();
        }
        GpuBuffer gpuBuffer = this.gpuBuffer;
        if (gpuBuffer == null) {
            return;
        }
        GpuBuffer buffer = gpuBuffer;
        if (this.maxPages <= 1) {
            pass.setUniform(this.uniformName, buffer);
        } else {
            RenderPipeline renderPipeline = pipeline;
            int page = renderPipeline == null ? 0 : this.pageIndexOf(renderPipeline);
            pass.setUniform(this.uniformName, buffer.slice((long)page * (long)this.pageBytes, (long)this.pageBytes));
        }
        this.bindExtraUniforms(pass);
    }

    private final GpuBuffer ensureBuffer() {
        GpuBuffer current = this.gpuBuffer;
        if (current != null && !current.isClosed() && current.size() >= (long)this.totalBytes) {
            return current;
        }
        this.closeBuffer();
        try {
            GpuBuffer created = RenderSystem.getDevice().createBuffer(() -> UiBatch.ensureBuffer$lambda$0(this), 136, (long)this.totalBytes);
            this.gpuBuffer = created;
            return created;
        }
        catch (RuntimeException ignored) {
            return null;
        }
    }

    private final void closeBuffer() {
        GpuBuffer gpuBuffer = this.gpuBuffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        this.gpuBuffer = null;
    }

    @Override
    public void close() {
        this.allocator.reset();
        Arrays.fill(this.items, null);
        this.dirty = false;
        this.closeBuffer();
    }

    private static final String ensureBuffer$lambda$0(UiBatch this$0) {
        String string = this$0.name.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toLowerCase(...)");
        return "kimiko_" + string + "_params";
    }
}

