/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.JvmField
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
package rtx.kimiko.utils.render.render2d.rectangle.rectdefault;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
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
import rtx.kimiko.utils.render.others.RoundedScissor;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.render2d.rectangle.rectdefault.BuiltRectangle;
import rtx.kimiko.utils.render.render2d.rectangle.rectdefault.DefaultRectangleRenderState;
import rtx.kimiko.utils.render.render2d.rectangle.rectdefault.RectangleBatch;
import rtx.kimiko.utils.render.util.scissor.ScissorUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 '2\u00060\u0001j\u0002`\u0002:\u0001'B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\tJ!\u0010\f\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0004J#\u0010\u0011\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b\u0011\u0010\rJ\u0017\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0004R0\u0010\u0018\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00160\u0015j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u0016`\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010$\u001a\u00020!8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010#R\u0018\u0010%\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010&\u00a8\u0006("}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/DefaultRectangleRenderer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lnet/minecraft/DrawContext;", "graphics", "", "beginFrame", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/BuiltRectangle;", "rectangle", "draw", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/BuiltRectangle;)V", "enqueue", "(Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/BuiltRectangle;)V", "flush", "submit", "normalize", "(Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/BuiltRectangle;)Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/BuiltRectangle;", "close", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "pages", "Ljava/util/ArrayList;", "", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "pageBuffers", "[Lcom/mojang/blaze3d/buffers/GpuBuffer;", "", "pageDirty", "[Z", "", "totalCount", "I", "boundPage", "activeGraphics", "Lnet/minecraft/DrawContext;", "Companion", "rtx.kimiko:kimiko"})
public final class DefaultRectangleRenderer
implements AutoCloseable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ArrayList<List<BuiltRectangle>> pages = new ArrayList();
    @NotNull
    private final GpuBuffer[] pageBuffers = new GpuBuffer[16];
    @NotNull
    private final boolean[] pageDirty = new boolean[16];
    private int totalCount;
    private int boundPage;
    @Nullable
    private DrawContext activeGraphics;
    private static final int PAGE_SIZE = 448;
    private static final int MAX_PAGES = 16;
    private static final int PARAMS_PER_RECTANGLE = 9;
    private static final int FLOATS_PER_PARAM = 4;
    private static final int PAGE_BYTES = 64512;
    @Nullable
    private static volatile DefaultRectangleRenderer instance;
    @JvmField
    @NotNull
    public static final RenderPipeline[] PIPELINES;
    @JvmField
    @NotNull
    public static final RenderPipeline RECTANGLE_PIPELINE;

    private DefaultRectangleRenderer() {
    }

    public final void beginFrame(@Nullable DrawContext graphics) {
        this.activeGraphics = graphics;
    }

    public final void draw(@Nullable DrawContext graphics, @Nullable BuiltRectangle rectangle) {
        this.beginFrame(graphics);
        this.enqueue(rectangle);
        this.flush();
    }

    public final void enqueue(@Nullable BuiltRectangle rectangle) {
        this.submit(this.activeGraphics, rectangle);
    }

    public final void flush() {
        this.activeGraphics = null;
    }

    private final void submit(DrawContext graphics, BuiltRectangle rectangle) {
        if (graphics == null || rectangle == null || !rectangle.visible()) {
            return;
        }
        try {
            BuiltRectangle normalized = this.normalize(rectangle);
            Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
            ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState().addSimpleElement((SimpleGuiElementRenderState)new DefaultRectangleRenderState(pose, normalized, ScissorUtil.current()));
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    private final BuiltRectangle normalize(BuiltRectangle rectangle) {
        boolean lens = rectangle.smoothness() < 0.0f;
        float maxRadius = lens ? Float.MAX_VALUE : Math.max(0.0f, Math.max(rectangle.width(), rectangle.height()) * 0.5f);
        float sx = 0.0f;
        float sy = 0.0f;
        float sw = 0.0f;
        float sh = 0.0f;
        float srtl = 0.0f;
        float srtr = 0.0f;
        float srbr = 0.0f;
        float srbl = 0.0f;
        float scos = 1.0f;
        float ssin = 0.0f;
        float sfade = 0.0f;
        if (RoundedScissor.isEnabled()) {
            scos = RoundedScissor.cos();
            ssin = RoundedScissor.sin();
            sx = RoundedScissor.x();
            sy = RoundedScissor.y();
            sw = RoundedScissor.width();
            sh = RoundedScissor.height();
            srtl = RoundedScissor.radiusTopLeft();
            srtr = RoundedScissor.radiusTopRight();
            srbr = RoundedScissor.radiusBottomRight();
            srbl = RoundedScissor.radiusBottomLeft();
            sfade = RoundedScissor.fadeTop();
        } else {
            sx = rectangle.scissorX();
            sy = rectangle.scissorY();
            sw = rectangle.scissorWidth();
            sh = rectangle.scissorHeight();
            srtl = rectangle.scissorRadiusTopLeft();
            srtr = rectangle.scissorRadiusTopRight();
            srbr = rectangle.scissorRadiusBottomRight();
            srbl = rectangle.scissorRadiusBottomLeft();
            sfade = rectangle.scissorFade();
        }
        float radiusTopLeft = DefaultRectangleRenderer.Companion.clamp(rectangle.radiusTopLeft(), 0.0f, maxRadius);
        float radiusTopRight = DefaultRectangleRenderer.Companion.clamp(rectangle.radiusTopRight(), 0.0f, maxRadius);
        float radiusBottomRight = DefaultRectangleRenderer.Companion.clamp(rectangle.radiusBottomRight(), 0.0f, maxRadius);
        float radiusBottomLeft = DefaultRectangleRenderer.Companion.clamp(rectangle.radiusBottomLeft(), 0.0f, maxRadius);
        if (radiusTopLeft == rectangle.radiusTopLeft() && radiusTopRight == rectangle.radiusTopRight() && radiusBottomRight == rectangle.radiusBottomRight() && radiusBottomLeft == rectangle.radiusBottomLeft() && sx == rectangle.scissorX() && sy == rectangle.scissorY() && sw == rectangle.scissorWidth() && sh == rectangle.scissorHeight() && srtl == rectangle.scissorRadiusTopLeft() && srtr == rectangle.scissorRadiusTopRight() && srbr == rectangle.scissorRadiusBottomRight() && srbl == rectangle.scissorRadiusBottomLeft() && scos == rectangle.scissorCos() && ssin == rectangle.scissorSin()) {
            return rectangle;
        }
        return new BuiltRectangle(rectangle.x(), rectangle.y(), rectangle.width(), rectangle.height(), radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, rectangle.colorTopLeft(), rectangle.colorTopRight(), rectangle.colorBottomRight(), rectangle.colorBottomLeft(), rectangle.smoothness(), sx, sy, sw, sh, srtl, srtr, srbr, srbl, rectangle.paletteMode(), rectangle.paletteTint(), rectangle.paletteAlpha(), scos, ssin, sfade);
    }

    @Override
    public void close() {
        for (List<BuiltRectangle> page : this.pages) {
            page.clear();
        }
        this.activeGraphics = null;
        for (int i = 0; i < this.pageBuffers.length; i++) {
            if (this.pageBuffers[i] != null) {
                this.pageBuffers[i].close();
                this.pageBuffers[i] = null;
            }
        }
    }

    @JvmStatic
    @NotNull
    public static final DefaultRectangleRenderer getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final void closeInstance() {
        Companion.closeInstance();
    }

    @JvmStatic
    public static final int pageOf(int slot) {
        return Companion.pageOf(slot);
    }

    @JvmStatic
    public static final int localOf(int slot) {
        return Companion.localOf(slot);
    }

    public /* synthetic */ DefaultRectangleRenderer(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    static {
        PIPELINES = RectangleBatch.PIPELINES;
        RECTANGLE_PIPELINE = PIPELINES[0];
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J\u001b\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\rJ'\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001bH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010!R\u0014\u0010#\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010!R\u0014\u0010$\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010!R\u0014\u0010%\u001a\u00020\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010!R\u0018\u0010&\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u001f\u0010+\u001a\b\u0012\u0004\u0012\u00020)0(8\u0006X\u0087\u0004\u0092\u0002\u0002\b*\u00a2\u0006\u0006\n\u0004\b+\u0010,R\u0019\u0010-\u001a\u00020)8\u0006X\u0087\u0004\u0092\u0002\u0002\b*\u00a2\u0006\u0006\n\u0004\b-\u0010.\u00a8\u0006/"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/DefaultRectangleRenderer.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/DefaultRectangleRenderer;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/DefaultRectangleRenderer;", "", "closeInstance", "", "slot", "pageOf", "(I)I", "localOf", "Ljava/nio/ByteBuffer;", "data", "offset", "color", "putColor", "(Ljava/nio/ByteBuffer;II)V", "", "value", "min", "max", "clamp", "(FFF)F", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "PAGE_SIZE", "I", "MAX_PAGES", "PARAMS_PER_RECTANGLE", "FLOATS_PER_PARAM", "PAGE_BYTES", "instance", "Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/DefaultRectangleRenderer;", "", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "PIPELINES", "[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "RECTANGLE_PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        @NotNull
        public final DefaultRectangleRenderer getInstance() {
            DefaultRectangleRenderer local = null;
            local = instance;
            if (local == null) {
                Class<DefaultRectangleRenderer> clazz = DefaultRectangleRenderer.class;
                synchronized (clazz) {
                    boolean bl = false;
                    local = instance;
                    if (local == null) {
                        local = new DefaultRectangleRenderer(null);
                        instance = local;
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return local;
        }

        @JvmStatic
        public final void closeInstance() {
            DefaultRectangleRenderer local = instance;
            if (local != null) {
                local.close();
                instance = null;
            }
        }

        @JvmStatic
        public final int pageOf(int slot) {
            return slot / 448;
        }

        @JvmStatic
        public final int localOf(int slot) {
            return slot % 448;
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

