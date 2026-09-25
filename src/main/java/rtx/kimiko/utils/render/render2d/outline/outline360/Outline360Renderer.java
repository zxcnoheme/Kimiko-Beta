/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 */
package rtx.kimiko.utils.render.render2d.outline.outline360;

import com.mojang.blaze3d.buffers.GpuBuffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.util.Identifier;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.render2d.outline.outline360.BuiltOutline360;
import rtx.kimiko.utils.render.render2d.outline.outline360.Outline360RenderState;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \"2\u00060\u0001j\u0002`\u0002:\u0001\"B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\tJ!\u0010\f\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0004J#\u0010\u0011\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b\u0011\u0010\rJ\u0017\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0004R$\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0015j\b\u0012\u0004\u0012\u00020\n`\u00168\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001dR\u0016\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010!\u00a8\u0006#"}, d2={"Lrtx/kimiko/utils/render/render2d/outline/outline360/Outline360Renderer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lnet/minecraft/DrawContext;", "graphics", "", "beginFrame", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/utils/render/render2d/outline/outline360/BuiltOutline360;", "outline", "draw", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/utils/render/render2d/outline/outline360/BuiltOutline360;)V", "enqueue", "(Lrtx/kimiko/utils/render/render2d/outline/outline360/BuiltOutline360;)V", "flush", "submit", "normalize", "(Lrtx/kimiko/utils/render/render2d/outline/outline360/BuiltOutline360;)Lrtx/kimiko/utils/render/render2d/outline/outline360/BuiltOutline360;", "close", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "preparedOutlines", "Ljava/util/ArrayList;", "activeGraphics", "Lnet/minecraft/DrawContext;", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "paramsBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "rangesBuffer", "", "paramsDirty", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class Outline360Renderer
implements AutoCloseable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ArrayList<BuiltOutline360> preparedOutlines = new ArrayList(64);
    @Nullable
    private DrawContext activeGraphics;
    @Nullable
    private GpuBuffer paramsBuffer;
    @Nullable
    private GpuBuffer rangesBuffer;
    private boolean paramsDirty = true;
    private static final int MAX_OUTLINES = 256;
    private static final int MAX_RANGES = 1024;
    private static final int PARAMS_PER_OUTLINE = 4;
    private static final int PARAMS_PER_RANGE = 3;
    private static final int FLOATS_PER_PARAM = 4;
    private static final int OUTLINE_UNIFORM_BYTES = 16384;
    private static final int RANGE_UNIFORM_BYTES = 49152;
    @Nullable
    private static volatile Outline360Renderer instance;

    private Outline360Renderer() {
    }

    public final void beginFrame(@Nullable DrawContext graphics) {
        this.activeGraphics = graphics;
    }

    public final void draw(@Nullable DrawContext graphics, @Nullable BuiltOutline360 outline) {
        this.beginFrame(graphics);
        this.enqueue(outline);
        this.flush();
    }

    public final void enqueue(@Nullable BuiltOutline360 outline) {
        this.submit(this.activeGraphics, outline);
    }

    public final void flush() {
        this.activeGraphics = null;
    }

    private final void submit(DrawContext graphics, BuiltOutline360 outline) {
        if (graphics == null || outline == null || !outline.visible()) {
            return;
        }
        try {
            BuiltOutline360 normalized = this.normalize(outline);
            Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
            ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState().addSimpleElement((SimpleGuiElementRenderState)new Outline360RenderState(pose, normalized));
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    private final BuiltOutline360 normalize(BuiltOutline360 outline) {
        float maxRadius = Math.max(0.0f, Math.min(outline.width(), outline.height()) * 0.5f);
        float maxThickness = Math.max(0.0f, Math.min(outline.width(), outline.height()) * 0.5f);
        return new BuiltOutline360(outline.x(), outline.y(), outline.width(), outline.height(), Outline360Renderer.Companion.clamp(outline.radiusTopLeft(), 0.0f, maxRadius), Outline360Renderer.Companion.clamp(outline.radiusTopRight(), 0.0f, maxRadius), Outline360Renderer.Companion.clamp(outline.radiusBottomRight(), 0.0f, maxRadius), Outline360Renderer.Companion.clamp(outline.radiusBottomLeft(), 0.0f, maxRadius), Outline360Renderer.Companion.clamp(outline.thickness(), 0.0f, maxThickness), outline.defaultColor(), Math.max(outline.smoothness(), 0.0f), Math.max(outline.blendDegrees(), 0.0f), outline.angleOffsetDegrees(), outline.ranges());
    }

    @Override
    public void close() {
        this.preparedOutlines.clear();
        this.activeGraphics = null;
    }

    @JvmStatic
    @NotNull
    public static final Outline360Renderer getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final void closeInstance() {
        Companion.closeInstance();
    }

    public /* synthetic */ Outline360Renderer(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J'\u0010\u000f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001dR\u0014\u0010 \u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u001dR\u0014\u0010!\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010\u001dR\u0014\u0010\"\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010\u001dR\u0014\u0010#\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010\u001dR\u0018\u0010$\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%\u00a8\u0006&"}, d2={"Lrtx/kimiko/utils/render/render2d/outline/outline360/Outline360Renderer.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/render2d/outline/outline360/Outline360Renderer;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/utils/render/render2d/outline/outline360/Outline360Renderer;", "", "closeInstance", "Ljava/nio/ByteBuffer;", "data", "", "offset", "color", "putColor", "(Ljava/nio/ByteBuffer;II)V", "", "value", "min", "max", "clamp", "(FFF)F", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "MAX_OUTLINES", "I", "MAX_RANGES", "PARAMS_PER_OUTLINE", "PARAMS_PER_RANGE", "FLOATS_PER_PARAM", "OUTLINE_UNIFORM_BYTES", "RANGE_UNIFORM_BYTES", "instance", "Lrtx/kimiko/utils/render/render2d/outline/outline360/Outline360Renderer;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        @NotNull
        public final Outline360Renderer getInstance() {
            Outline360Renderer local = null;
            local = instance;
            if (local == null) {
                Class<Outline360Renderer> clazz = Outline360Renderer.class;
                synchronized (clazz) {
                    boolean bl = false;
                    local = instance;
                    if (local == null) {
                        local = new Outline360Renderer(null);
                        instance = local;
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return local;
        }

        @JvmStatic
        public final void closeInstance() {
            Outline360Renderer local = instance;
            if (local != null) {
                local.close();
                instance = null;
            }
        }

        private final void putColor(ByteBuffer data, int offset, int color) {
            data.putFloat(offset, (float)(color >>> 16 & 0xFF) / 255.0f);
            data.putFloat(offset + 4, (float)(color >>> 8 & 0xFF) / 255.0f);
            data.putFloat(offset + 8, (float)(color & 0xFF) / 255.0f);
            data.putFloat(offset + 12, (float)(color >>> 24 & 0xFF) / 255.0f);
        }

        private final float clamp(float value, float min, float max) {
            return Math.max(min, Math.min(max, value));
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
}

