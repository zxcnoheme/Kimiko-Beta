/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 */
package rtx.kimiko.utils.render.render2d.rectangle.recthalficon;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.util.Identifier;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.fonts.core.TextRenderer;
import rtx.kimiko.utils.render.modules.post.GuiRenderStateLayerAccessor;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.render2d.rectangle.recthalficon.BuiltHalfIconRectangle;
import rtx.kimiko.utils.render.render2d.rectangle.recthalficon.HalfIconRectangleRenderState;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000 (2\u00060\u0001j\u0002`\u0002:\u0004)*+(B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\tJ!\u0010\f\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0004J#\u0010\u0011\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b\u0011\u0010\rJ-\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u001c\u0010\u0004R0\u0010!\u001a\u001e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u001dj\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f` 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0018\u0010#\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010&\u001a\u00020%8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'\u00a8\u0006,"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lnet/minecraft/DrawContext;", "graphics", "", "beginFrame", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;", "rectangle", "draw", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;)V", "enqueue", "(Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;)V", "flush", "submit", "Lrtx/kimiko/utils/render/fonts/core/TextRenderer$GlyphLayout;", "layout", "Lrtx/kimiko/utils/render/fonts/core/TextRenderer$GlyphPage;", "page", "", "Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderState$IconQuad;", "buildIconQuads", "(Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;Lrtx/kimiko/utils/render/fonts/core/TextRenderer$GlyphLayout;Lrtx/kimiko/utils/render/fonts/core/TextRenderer$GlyphPage;)Ljava/util/List;", "normalize", "(Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;)Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;", "close", "Ljava/util/LinkedHashMap;", "Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer$FrameBatchKey;", "Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderState;", "Lkotlin/collections/LinkedHashMap;", "frameBatches", "Ljava/util/LinkedHashMap;", "activeGraphics", "Lnet/minecraft/DrawContext;", "", "paramsDirty", "Z", "Companion", "FrameBatchKey", "PoseKey", "RotatedPoint", "rtx.kimiko:kimiko"})
public final class HalfIconRectangleRenderer
implements AutoCloseable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final LinkedHashMap<FrameBatchKey, HalfIconRectangleRenderState> frameBatches = new LinkedHashMap(32);
    @Nullable
    private DrawContext activeGraphics;
    private boolean paramsDirty = true;
    private static final int MAX_ICON_COLUMNS = 96;
    private static final int MAX_ICON_ROWS = 96;
    @Nullable
    private static volatile HalfIconRectangleRenderer instance;

    private HalfIconRectangleRenderer() {
    }

    public final void beginFrame(@Nullable DrawContext graphics) {
        if (this.activeGraphics != graphics) {
            this.frameBatches.clear();
        }
        this.activeGraphics = graphics;
    }

    public final void draw(@Nullable DrawContext graphics, @Nullable BuiltHalfIconRectangle rectangle) {
        this.beginFrame(graphics);
        this.enqueue(rectangle);
        this.flush();
    }

    public final void enqueue(@Nullable BuiltHalfIconRectangle rectangle) {
        this.submit(this.activeGraphics, rectangle);
    }

    public final void flush() {
        this.activeGraphics = null;
        this.frameBatches.clear();
    }

    private final void submit(DrawContext graphics, BuiltHalfIconRectangle rectangle) {
        if (graphics == null || rectangle == null || !rectangle.iconsVisible()) {
            return;
        }
        try {
            BuiltHalfIconRectangle normalized = this.normalize(rectangle);
            TextRenderer.GlyphLayout layout = TextRenderer.Companion.getInstance().glyphLayout(normalized.fontName(), normalized.icon(), normalized.iconSize());
            if (layout.empty()) {
                return;
            }
            Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
            GuiRenderState guiState = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
            Intrinsics.checkNotNull((Object)guiState, (String)"null cannot be cast to non-null type rtx.kimiko.utils.render.modules.post.GuiRenderStateLayerAccessor");
            int layerSerial = ((GuiRenderStateLayerAccessor)guiState).kimiko$getLayerSerial();
            PoseKey poseKey = PoseKey.Companion.of(pose);
            for (TextRenderer.GlyphPage page : layout.pages()) {
                List<HalfIconRectangleRenderState.IconQuad> quads;
                if (page.glyphs().isEmpty() || (quads = this.buildIconQuads(normalized, layout, page)).isEmpty()) continue;
                FrameBatchKey key = new FrameBatchKey(guiState, layerSerial, page.textureSetup(), poseKey);
                HalfIconRectangleRenderState state = this.frameBatches.get(key);
                if (state == null) {
                    HalfIconRectangleRenderState created = new HalfIconRectangleRenderState(pose, page.textureSetup());
                    created.add(normalized, quads);
                    ((Map)this.frameBatches).put(key, created);
                    guiState.addSimpleElement((SimpleGuiElementRenderState)created);
                    continue;
                }
                state.add(normalized, quads);
            }
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    private final List<HalfIconRectangleRenderState.IconQuad> buildIconQuads(BuiltHalfIconRectangle rectangle, TextRenderer.GlyphLayout layout, TextRenderer.GlyphPage page) {
        ArrayList<HalfIconRectangleRenderState.IconQuad> quads = new ArrayList<HalfIconRectangleRenderState.IconQuad>(64);
        float spacingX = Math.max(rectangle.spacingX(), Math.max(layout.width(), 1.0f));
        float spacingY = Math.max(rectangle.spacingY(), Math.max(layout.height(), 1.0f));
        float startX = rectangle.x() + rectangle.paddingX() + layout.width() * 0.5f;
        float startY = rectangle.y() + rectangle.paddingY() + layout.height() * 0.5f;
        float endX = rectangle.x() + rectangle.width() - rectangle.paddingX() - layout.width() * 0.5f;
        float endY = rectangle.y() + rectangle.height() - rectangle.paddingY() - layout.height() * 0.5f;
        if (endX < startX || endY < startY) {
            return CollectionsKt.emptyList();
        }
        int rows = Math.min(96, Math.max(1, (int)Math.floor((endY - startY) / spacingY) + 1));
        int columns = Math.min(96, Math.max(1, (int)Math.floor((endX - startX) / spacingX) + 1));
        for (int row = 0; row < rows; ++row) {
            for (int column = 0; column < columns; ++column) {
                float centerX = startX + (float)column * spacingX + HalfIconRectangleRenderer.Companion.randomSigned(rectangle.seed(), column, row, 1) * rectangle.jitterX();
                float centerY = startY + (float)row * spacingY + HalfIconRectangleRenderer.Companion.randomSigned(rectangle.seed(), column, row, 2) * rectangle.jitterY();
                float angle = (float)Math.toRadians(HalfIconRectangleRenderer.Companion.randomSigned(rectangle.seed(), column, row, 3) * rectangle.rotationMaxDegrees());
                float s = (float)Math.sin(angle);
                float c = (float)Math.cos(angle);
                float originX = centerX - layout.width() * 0.5f;
                float originY = centerY - layout.height() * 0.5f;
                int iconColor = HalfIconRectangleRenderer.Companion.withAlphaMultiplier(rectangle.iconColor(), 1.0f - HalfIconRectangleRenderer.Companion.clamp((centerY - rectangle.y()) / Math.max(rectangle.height(), 1.0f), 0.0f, 1.0f) * 0.92f);
                for (TextRenderer.Glyph glyph : page.glyphs()) {
                    float x0 = originX + glyph.x0();
                    float y0 = originY + glyph.y0();
                    float x1 = originX + glyph.x1();
                    float y1 = originY + glyph.y1();
                    RotatedPoint topLeft = HalfIconRectangleRenderer.Companion.rotate(x0, y0, centerX, centerY, s, c);
                    RotatedPoint bottomLeft = HalfIconRectangleRenderer.Companion.rotate(x0, y1, centerX, centerY, s, c);
                    RotatedPoint bottomRight = HalfIconRectangleRenderer.Companion.rotate(x1, y1, centerX, centerY, s, c);
                    RotatedPoint topRight = HalfIconRectangleRenderer.Companion.rotate(x1, y0, centerX, centerY, s, c);
                    quads.add(new HalfIconRectangleRenderState.IconQuad(topLeft.getX(), topLeft.getY(), glyph.u0(), glyph.v0(), bottomLeft.getX(), bottomLeft.getY(), glyph.u0(), glyph.v1(), bottomRight.getX(), bottomRight.getY(), glyph.u1(), glyph.v1(), topRight.getX(), topRight.getY(), glyph.u1(), glyph.v0(), iconColor));
                }
            }
        }
        return quads;
    }

    private final BuiltHalfIconRectangle normalize(BuiltHalfIconRectangle rectangle) {
        float maxRadius = Math.max(0.0f, Math.min(rectangle.width(), rectangle.height()) * 0.5f);
        float iconSize = Math.max(rectangle.iconSize(), 1.0f);
        return new BuiltHalfIconRectangle(rectangle.x(), rectangle.y(), rectangle.width(), rectangle.height(), HalfIconRectangleRenderer.Companion.clamp(rectangle.radiusTopLeft(), 0.0f, maxRadius), HalfIconRectangleRenderer.Companion.clamp(rectangle.radiusTopRight(), 0.0f, maxRadius), HalfIconRectangleRenderer.Companion.clamp(rectangle.radiusBottomRight(), 0.0f, maxRadius), HalfIconRectangleRenderer.Companion.clamp(rectangle.radiusBottomLeft(), 0.0f, maxRadius), rectangle.colorTopLeft(), rectangle.colorTopRight(), rectangle.colorBottomRight(), rectangle.colorBottomLeft(), Math.max(rectangle.smoothness(), 0.0f), rectangle.fontName(), rectangle.icon(), iconSize, rectangle.iconColor(), Math.max(rectangle.spacingX(), iconSize), Math.max(rectangle.spacingY(), iconSize), Math.max(rectangle.paddingX(), 0.0f), Math.max(rectangle.paddingY(), 0.0f), Math.max(rectangle.jitterX(), 0.0f), Math.max(rectangle.jitterY(), 0.0f), HalfIconRectangleRenderer.Companion.clamp(rectangle.rotationMaxDegrees(), 0.0f, 90.0f), rectangle.seed());
    }

    @Override
    public void close() {
        this.frameBatches.clear();
        this.activeGraphics = null;
    }

    @JvmStatic
    @NotNull
    public static final HalfIconRectangleRenderer getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final void closeInstance() {
        Companion.closeInstance();
    }

    public /* synthetic */ HalfIconRectangleRenderer(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J?\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J/\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ'\u0010\u001f\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u001f\u0010#\u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b#\u0010$J\u0017\u0010(\u001a\u00020'2\u0006\u0010&\u001a\u00020%H\u0002\u00a2\u0006\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010+R\u0018\u0010-\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010.\u00a8\u0006/"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer;", "", "closeInstance", "", "x", "y", "centerX", "centerY", "s", "c", "Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer$RotatedPoint;", "rotate", "(FFFFFF)Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer$RotatedPoint;", "", "seed", "", "column", "row", "salt", "randomSigned", "(JIII)F", "value", "min", "max", "clamp", "(FFF)F", "color", "multiplier", "withAlphaMultiplier", "(IF)I", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "MAX_ICON_COLUMNS", "I", "MAX_ICON_ROWS", "instance", "Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        @NotNull
        public final HalfIconRectangleRenderer getInstance() {
            HalfIconRectangleRenderer local = null;
            local = instance;
            if (local == null) {
                Class<HalfIconRectangleRenderer> clazz = HalfIconRectangleRenderer.class;
                synchronized (clazz) {
                    boolean bl = false;
                    local = instance;
                    if (local == null) {
                        local = new HalfIconRectangleRenderer(null);
                        instance = local;
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return local;
        }

        @JvmStatic
        public final void closeInstance() {
            HalfIconRectangleRenderer local = instance;
            if (local != null) {
                local.close();
                instance = null;
            }
        }

        private final RotatedPoint rotate(float x, float y, float centerX, float centerY, float s, float c) {
            float localX = x - centerX;
            float localY = y - centerY;
            return new RotatedPoint(centerX + localX * c - localY * s, centerY + localX * s + localY * c);
        }

        private final float randomSigned(long seed, int column, int row, int salt) {
            long value = seed ^ (long)column * -7046029254386353131L ^ (long)row * -4658895280553007687L ^ (long)salt * -7723592293110705685L;
            value ^= value >>> 30;
            value *= -4658895280553007687L;
            value ^= value >>> 27;
            value *= -7723592293110705685L;
            value ^= value >>> 31;
            return (float)(value & 0xFFFFFFL) / 1.6777215E7f * 2.0f - 1.0f;
        }

        private final float clamp(float value, float min, float max) {
            return Math.max(min, Math.min(max, value));
        }

        private final int withAlphaMultiplier(int color, float multiplier) {
            int alpha = Math.round((float)(color >>> 24 & 0xFF) * this.clamp(multiplier, 0.0f, 1.0f));
            return alpha << 24 | color & 0xFFFFFF;
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

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013J8\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u00c6\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001a\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u000fJ\u0011\u0010\u001c\u001a\u00020\u001bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\rR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010 \u001a\u0004\b!\u0010\u000fR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\"\u001a\u0004\b#\u0010\u0011R\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010$\u001a\u0004\b%\u0010\u0013\u00a8\u0006&"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer$FrameBatchKey;", "", "Lnet/minecraft/GuiRenderState;", "state", "", "layerSerial", "Lnet/minecraft/TextureSetup;", "textureSetup", "Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer$PoseKey;", "pose", "<init>", "(Lnet/minecraft/GuiRenderState;ILnet/minecraft/TextureSetup;Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer$PoseKey;)V", "component1", "()Lnet/minecraft/GuiRenderState;", "component2", "()I", "component3", "()Lnet/minecraft/TextureSetup;", "component4", "()Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer$PoseKey;", "copy", "(Lnet/minecraft/GuiRenderState;ILnet/minecraft/TextureSetup;Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer$PoseKey;)Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer$FrameBatchKey;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/GuiRenderState;", "getState", "I", "getLayerSerial", "Lnet/minecraft/TextureSetup;", "getTextureSetup", "Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer$PoseKey;", "getPose", "rtx.kimiko:kimiko"})
    private static final class FrameBatchKey {
        @NotNull
        private final GuiRenderState state;
        private final int layerSerial;
        @NotNull
        private final TextureSetup textureSetup;
        @NotNull
        private final PoseKey pose;

        public FrameBatchKey(@NotNull GuiRenderState state, int layerSerial, @NotNull TextureSetup textureSetup, @NotNull PoseKey pose) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            Intrinsics.checkNotNullParameter((Object)textureSetup, (String)"textureSetup");
            Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
            this.state = state;
            this.layerSerial = layerSerial;
            this.textureSetup = textureSetup;
            this.pose = pose;
        }

        @NotNull
        public final GuiRenderState getState() {
            return this.state;
        }

        public final int getLayerSerial() {
            return this.layerSerial;
        }

        @NotNull
        public final TextureSetup getTextureSetup() {
            return this.textureSetup;
        }

        @NotNull
        public final PoseKey getPose() {
            return this.pose;
        }

        @NotNull
        public final GuiRenderState component1() {
            return this.state;
        }

        public final int component2() {
            return this.layerSerial;
        }

        @NotNull
        public final TextureSetup component3() {
            return this.textureSetup;
        }

        @NotNull
        public final PoseKey component4() {
            return this.pose;
        }

        @NotNull
        public final FrameBatchKey copy(@NotNull GuiRenderState state, int layerSerial, @NotNull TextureSetup textureSetup, @NotNull PoseKey pose) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            Intrinsics.checkNotNullParameter((Object)textureSetup, (String)"textureSetup");
            Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
            return new FrameBatchKey(state, layerSerial, textureSetup, pose);
        }

        public static /* synthetic */ FrameBatchKey copy$default(FrameBatchKey frameBatchKey, GuiRenderState guiRenderState2, int n, TextureSetup textureSetup2, PoseKey poseKey, int n2, Object object) {
            if ((n2 & 1) != 0) {
                guiRenderState2 = frameBatchKey.state;
            }
            if ((n2 & 2) != 0) {
                n = frameBatchKey.layerSerial;
            }
            if ((n2 & 4) != 0) {
                textureSetup2 = frameBatchKey.textureSetup;
            }
            if ((n2 & 8) != 0) {
                poseKey = frameBatchKey.pose;
            }
            return frameBatchKey.copy(guiRenderState2, n, textureSetup2, poseKey);
        }

        @NotNull
        public String toString() {
            return "FrameBatchKey(state=" + this.state + ", layerSerial=" + this.layerSerial + ", textureSetup=" + this.textureSetup + ", pose=" + this.pose + ")";
        }

        public int hashCode() {
            int result = this.state.hashCode();
            result = result * 31 + Integer.hashCode(this.layerSerial);
            result = result * 31 + this.textureSetup.hashCode();
            result = result * 31 + this.pose.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FrameBatchKey)) {
                return false;
            }
            FrameBatchKey frameBatchKey = (FrameBatchKey)other;
            if (!Intrinsics.areEqual((Object)this.state, (Object)frameBatchKey.state)) {
                return false;
            }
            if (this.layerSerial != frameBatchKey.layerSerial) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.textureSetup, (Object)frameBatchKey.textureSetup)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.pose, (Object)frameBatchKey.pose);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0082\b\u0018\u0000 %2\u00020\u0001:\u0001%B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\fJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\fJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\fJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\fJL\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0019\u001a\u00020\u0018H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001c\u001a\u00020\u001bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001e\u001a\u0004\b \u0010\fR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b!\u0010\fR\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\"\u0010\fR\u0017\u0010\u0007\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b#\u0010\fR\u0017\u0010\b\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u001e\u001a\u0004\b$\u0010\f\u00a8\u0006&"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer$PoseKey;", "", "", "m00", "m01", "m10", "m11", "m20", "m21", "<init>", "(FFFFFF)V", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "copy", "(FFFFFF)Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer$PoseKey;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "F", "getM00", "getM01", "getM10", "getM11", "getM20", "getM21", "Companion", "rtx.kimiko:kimiko"})
    private static final class PoseKey {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final float m00;
        private final float m01;
        private final float m10;
        private final float m11;
        private final float m20;
        private final float m21;

        public PoseKey(float m00, float m01, float m10, float m11, float m20, float m21) {
            this.m00 = m00;
            this.m01 = m01;
            this.m10 = m10;
            this.m11 = m11;
            this.m20 = m20;
            this.m21 = m21;
        }

        public final float getM00() {
            return this.m00;
        }

        public final float getM01() {
            return this.m01;
        }

        public final float getM10() {
            return this.m10;
        }

        public final float getM11() {
            return this.m11;
        }

        public final float getM20() {
            return this.m20;
        }

        public final float getM21() {
            return this.m21;
        }

        public final float component1() {
            return this.m00;
        }

        public final float component2() {
            return this.m01;
        }

        public final float component3() {
            return this.m10;
        }

        public final float component4() {
            return this.m11;
        }

        public final float component5() {
            return this.m20;
        }

        public final float component6() {
            return this.m21;
        }

        @NotNull
        public final PoseKey copy(float m00, float m01, float m10, float m11, float m20, float m21) {
            return new PoseKey(m00, m01, m10, m11, m20, m21);
        }

        public static /* synthetic */ PoseKey copy$default(PoseKey poseKey, float f, float f2, float f3, float f4, float f5, float f6, int n, Object object) {
            if ((n & 1) != 0) {
                f = poseKey.m00;
            }
            if ((n & 2) != 0) {
                f2 = poseKey.m01;
            }
            if ((n & 4) != 0) {
                f3 = poseKey.m10;
            }
            if ((n & 8) != 0) {
                f4 = poseKey.m11;
            }
            if ((n & 0x10) != 0) {
                f5 = poseKey.m20;
            }
            if ((n & 0x20) != 0) {
                f6 = poseKey.m21;
            }
            return poseKey.copy(f, f2, f3, f4, f5, f6);
        }

        @NotNull
        public String toString() {
            return "PoseKey(m00=" + this.m00 + ", m01=" + this.m01 + ", m10=" + this.m10 + ", m11=" + this.m11 + ", m20=" + this.m20 + ", m21=" + this.m21 + ")";
        }

        public int hashCode() {
            int result = Float.hashCode(this.m00);
            result = result * 31 + Float.hashCode(this.m01);
            result = result * 31 + Float.hashCode(this.m10);
            result = result * 31 + Float.hashCode(this.m11);
            result = result * 31 + Float.hashCode(this.m20);
            result = result * 31 + Float.hashCode(this.m21);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PoseKey)) {
                return false;
            }
            PoseKey poseKey = (PoseKey)other;
            if (Float.compare(this.m00, poseKey.m00) != 0) {
                return false;
            }
            if (Float.compare(this.m01, poseKey.m01) != 0) {
                return false;
            }
            if (Float.compare(this.m10, poseKey.m10) != 0) {
                return false;
            }
            if (Float.compare(this.m11, poseKey.m11) != 0) {
                return false;
            }
            if (Float.compare(this.m20, poseKey.m20) != 0) {
                return false;
            }
            return Float.compare(this.m21, poseKey.m21) == 0;
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer$PoseKey.Companion;", "", "<init>", "()V", "Lorg/joml/Matrix3x2f;", "matrix", "Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer$PoseKey;", "of", "(Lorg/joml/Matrix3x2f;)Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer$PoseKey;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final PoseKey of(@NotNull Matrix3x2f matrix) {
                Intrinsics.checkNotNullParameter((Object)matrix, (String)"matrix");
                return new PoseKey(matrix.m00(), matrix.m01(), matrix.m10(), matrix.m11(), matrix.m20(), matrix.m21());
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\bJ$\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0011\u001a\u00020\u0010H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0014\u001a\u00020\u0013H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0018\u0010\b\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer$RotatedPoint;", "", "", "x", "y", "<init>", "(FF)V", "component1", "()F", "component2", "copy", "(FF)Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderer$RotatedPoint;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "F", "getX", "getY", "rtx.kimiko:kimiko"})
    private static final class RotatedPoint {
        private final float x;
        private final float y;

        public RotatedPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public final float getX() {
            return this.x;
        }

        public final float getY() {
            return this.y;
        }

        public final float component1() {
            return this.x;
        }

        public final float component2() {
            return this.y;
        }

        @NotNull
        public final RotatedPoint copy(float x, float y) {
            return new RotatedPoint(x, y);
        }

        public static /* synthetic */ RotatedPoint copy$default(RotatedPoint rotatedPoint, float f, float f2, int n, Object object) {
            if ((n & 1) != 0) {
                f = rotatedPoint.x;
            }
            if ((n & 2) != 0) {
                f2 = rotatedPoint.y;
            }
            return rotatedPoint.copy(f, f2);
        }

        @NotNull
        public String toString() {
            return "RotatedPoint(x=" + this.x + ", y=" + this.y + ")";
        }

        public int hashCode() {
            int result = Float.hashCode(this.x);
            result = result * 31 + Float.hashCode(this.y);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RotatedPoint)) {
                return false;
            }
            RotatedPoint rotatedPoint = (RotatedPoint)other;
            if (Float.compare(this.x, rotatedPoint.x) != 0) {
                return false;
            }
            return Float.compare(this.y, rotatedPoint.y) == 0;
        }
    }
}

