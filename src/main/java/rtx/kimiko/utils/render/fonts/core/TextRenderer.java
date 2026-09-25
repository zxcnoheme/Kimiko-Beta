/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.BlendFunction
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Snippet
 *  com.mojang.blaze3d.platform.DepthTestFunction
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.ScreenRect
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 */
package rtx.kimiko.utils.render.fonts.core;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.ScreenRect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.fonts.core.BuiltText;
import rtx.kimiko.utils.render.fonts.core.FontManager;
import rtx.kimiko.utils.render.fonts.core.FontStrike;
import rtx.kimiko.utils.render.fonts.core.GlyphAtlasPage;
import rtx.kimiko.utils.render.fonts.core.LayoutGlyph;
import rtx.kimiko.utils.render.fonts.core.TextLayout;
import rtx.kimiko.utils.render.fonts.core.TextRenderState;
import rtx.kimiko.utils.render.modules.post.GuiRenderStateLayerAccessor;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.util.scissor.ScissorUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 ,2\u00060\u0001j\u0002`\u0002:\u0006-./01,B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\f\u0010\rJ!\u0010\u000e\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0004J)\u0010\u0015\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u000b\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016J)\u0010\u0018\u001a\u00020\u00172\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u000b\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0018\u0010\u0019J#\u0010\u001a\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b\u001a\u0010\u000fJ\u0017\u0010\u001b\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b \u0010\u0004R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#R0\u0010(\u001a\u001e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&0$j\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&`'8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0018\u0010*\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010+\u00a8\u00062"}, d2={"Lrtx/kimiko/utils/render/fonts/core/TextRenderer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lnet/minecraft/DrawContext;", "graphics", "", "beginFrame", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/utils/render/fonts/core/BuiltText;", "text", "enqueue", "(Lrtx/kimiko/utils/render/fonts/core/BuiltText;)V", "draw", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/utils/render/fonts/core/BuiltText;)V", "flush", "", "fontName", "", "size", "width", "(Ljava/lang/String;Ljava/lang/String;F)F", "Lrtx/kimiko/utils/render/fonts/core/TextRenderer$GlyphLayout;", "glyphLayout", "(Ljava/lang/String;Ljava/lang/String;F)Lrtx/kimiko/utils/render/fonts/core/TextRenderer$GlyphLayout;", "submit", "normalize", "(Lrtx/kimiko/utils/render/fonts/core/BuiltText;)Lrtx/kimiko/utils/render/fonts/core/BuiltText;", "", "needsColorNormalization", "(Lrtx/kimiko/utils/render/fonts/core/BuiltText;)Z", "close", "Lrtx/kimiko/utils/render/fonts/core/FontManager;", "fontManager", "Lrtx/kimiko/utils/render/fonts/core/FontManager;", "Ljava/util/LinkedHashMap;", "Lrtx/kimiko/utils/render/fonts/core/TextRenderer$FrameBatchKey;", "Lrtx/kimiko/utils/render/fonts/core/TextRenderState;", "Lkotlin/collections/LinkedHashMap;", "frameBatches", "Ljava/util/LinkedHashMap;", "activeGraphics", "Lnet/minecraft/DrawContext;", "Companion", "FrameBatchKey", "PoseKey", "GlyphLayout", "GlyphPage", "Glyph", "rtx.kimiko:kimiko"})
public final class TextRenderer
implements AutoCloseable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final FontManager fontManager = new FontManager();
    @NotNull
    private final LinkedHashMap<FrameBatchKey, TextRenderState> frameBatches = new LinkedHashMap(64);
    @Nullable
    private DrawContext activeGraphics;
    @Nullable
    private static volatile TextRenderer instance;
    @JvmField
    @NotNull
    public static final RenderPipeline TEXT_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderPipeline TEXT_FADE_PIPELINE;

    private TextRenderer() {
    }

    public final void beginFrame(@Nullable DrawContext graphics) {
        if (this.activeGraphics != graphics) {
            this.frameBatches.clear();
        }
        this.activeGraphics = graphics;
    }

    public final void enqueue(@Nullable BuiltText text) {
        this.submit(this.activeGraphics, text);
    }

    public final void draw(@Nullable DrawContext graphics, @Nullable BuiltText text) {
        this.beginFrame(graphics);
        this.enqueue(text);
        this.flush();
    }

    public final void flush() {
        this.activeGraphics = null;
        this.frameBatches.clear();
    }

    public final float width(@Nullable String fontName, @Nullable String text, float size) {
        CharSequence charSequence = text;
        if (charSequence == null || charSequence.length() == 0) {
            return 0.0f;
        }
        return this.fontManager.strike(fontName, size).width(text);
    }

    @NotNull
    public final GlyphLayout glyphLayout(@Nullable String fontName, @Nullable String text, float size) {
        if (text == null || text.isEmpty() || size <= 0.0f) {
            return GlyphLayout.EMPTY;
        }
        try {
            FontStrike strike = this.fontManager.strike(fontName, size);
            TextLayout layout = strike.layout(text);
            if (layout.empty()) {
                return GlyphLayout.EMPTY;
            }
            strike.uploadDirtyPages();
            ArrayList<GlyphPage> pages = new ArrayList<GlyphPage>(layout.pages().size());
            for (TextLayout.Page page : layout.pages()) {
                ArrayList<Glyph> glyphs = new ArrayList<Glyph>(page.glyphs().size());
                for (LayoutGlyph glyph : page.glyphs()) {
                    glyphs.add(new Glyph(glyph.x0(), glyph.y0(), glyph.x1(), glyph.y1(), glyph.u0(), glyph.v0(), glyph.u1(), glyph.v1()));
                }
                pages.add(new GlyphPage(page.page().textureSetup(), page.page().textureView(), page.page().size(), glyphs));
            }
            return new GlyphLayout(pages, layout.width(), layout.height());
        }
        catch (RuntimeException exception) {
            return GlyphLayout.EMPTY;
        }
    }

    private final void submit(DrawContext graphics, BuiltText text) {
        BuiltText normalized;
        if (graphics == null || text == null) {
            return;
        }
        BuiltText builtText = normalized = this.needsColorNormalization(text) ? this.normalize(text) : text;
        if (!normalized.visible()) {
            return;
        }
        try {
            FontStrike strike = this.fontManager.strike(normalized.fontName(), normalized.size());
            Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
            TextLayout layout = strike.layout(normalized.text());
            if (layout.empty()) {
                return;
            }
            strike.uploadDirtyPages();
            GuiRenderState guiState = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
            Intrinsics.checkNotNull((Object)guiState, (String)"null cannot be cast to non-null type rtx.kimiko.utils.render.modules.post.GuiRenderStateLayerAccessor");
            int layerSerial = ((GuiRenderStateLayerAccessor)guiState).kimiko$getLayerSerial();
            PoseKey poseKey = PoseKey.Companion.of(pose);
            ScreenRect scissorArea = ScissorUtil.current();
            RenderPipeline pipeline = normalized.hasHorizontalFade() ? TEXT_FADE_PIPELINE : TEXT_PIPELINE;
            for (TextLayout.Page page : layout.pages()) {
                if (page.glyphs().isEmpty()) continue;
                FrameBatchKey key = new FrameBatchKey(guiState, layerSerial, page.page(), poseKey, scissorArea, pipeline);
                TextRenderState state = this.frameBatches.get(key);
                if (state == null) {
                    state = new TextRenderState(pose, page.page(), scissorArea, pipeline);
                    ((Map)this.frameBatches).put(key, state);
                    guiState.addPreparedTextElement((SimpleGuiElementRenderState)state);
                }
                state.add(page.glyphs(), normalized.x(), normalized.y(), normalized.colorTopLeft(), normalized.colorTopRight(), normalized.colorBottomRight(), normalized.colorBottomLeft(), normalized.rotationDegrees(), normalized.rotationOriginX(), normalized.rotationOriginY(), normalized.fadeLeft(), normalized.fadeRight(), normalized.fadeLeftX(), normalized.fadeRightX(), normalized.fadeWidth(), normalized.fadeLeftStrength(), normalized.fadeRightStrength(), normalized.snapOrigin());
            }
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    private final BuiltText normalize(BuiltText text) {
        return new BuiltText(text.fontName(), text.text(), text.x(), text.y(), text.size(), TextRenderer.Companion.normalizeColor(text.colorTopLeft()), TextRenderer.Companion.normalizeColor(text.colorTopRight()), TextRenderer.Companion.normalizeColor(text.colorBottomRight()), TextRenderer.Companion.normalizeColor(text.colorBottomLeft()), text.rotationDegrees(), text.rotationOriginX(), text.rotationOriginY(), text.fadeLeft(), text.fadeRight(), text.fadeLeftX(), text.fadeRightX(), text.fadeWidth(), text.fadeLeftStrength(), text.fadeRightStrength(), text.snapOrigin());
    }

    private final boolean needsColorNormalization(BuiltText text) {
        return TextRenderer.Companion.needsColorNormalization(text.colorTopLeft()) || TextRenderer.Companion.needsColorNormalization(text.colorTopRight()) || TextRenderer.Companion.needsColorNormalization(text.colorBottomRight()) || TextRenderer.Companion.needsColorNormalization(text.colorBottomLeft());
    }

    @Override
    public void close() {
        this.activeGraphics = null;
        this.frameBatches.clear();
        this.fontManager.close();
    }

    @JvmStatic
    @NotNull
    public static final TextRenderer getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final void closeInstance() {
        Companion.closeInstance();
    }

    public /* synthetic */ TextRenderer(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    static {
        RenderPipeline renderPipeline = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(TextRenderer.Companion.id("pipeline/text_atlas")).withVertexShader(TextRenderer.Companion.id("core/text_atlas")).withFragmentShader(TextRenderer.Companion.id("core/text_atlas")).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withSampler("Sampler0").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"build(...)");
        TEXT_PIPELINE = renderPipeline;
        RenderPipeline renderPipeline2 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(TextRenderer.Companion.id("pipeline/text_atlas_fade")).withVertexShader(TextRenderer.Companion.id("core/text_atlas_fade")).withFragmentShader(TextRenderer.Companion.id("core/text_atlas_fade")).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withSampler("Sampler0").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline2, (String)"build(...)");
        TEXT_FADE_PIPELINE = renderPipeline2;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0019\u0010\u001a\u001a\u00020\u00188\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0019\u0010\u001c\u001a\u00020\u00188\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001b\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/utils/render/fonts/core/TextRenderer.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/fonts/core/TextRenderer;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/utils/render/fonts/core/TextRenderer;", "", "closeInstance", "", "color", "", "needsColorNormalization", "(I)Z", "normalizeColor", "(I)I", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "instance", "Lrtx/kimiko/utils/render/fonts/core/TextRenderer;", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "TEXT_PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "TEXT_FADE_PIPELINE", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        @NotNull
        public final TextRenderer getInstance() {
            TextRenderer local = null;
            local = instance;
            if (local == null) {
                Class<TextRenderer> clazz = TextRenderer.class;
                synchronized (clazz) {
                    boolean bl = false;
                    local = instance;
                    if (local == null) {
                        local = new TextRenderer(null);
                        instance = local;
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return local;
        }

        @JvmStatic
        public final void closeInstance() {
            TextRenderer local = instance;
            if (local != null) {
                local.close();
                instance = null;
            }
        }

        private final boolean needsColorNormalization(int color) {
            return false;
        }

        private final int normalizeColor(int color) {
            if (this.needsColorNormalization(color)) {
                return color | 0xFF000000;
            }
            return color;
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

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0082\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u001bJN\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\r\u001a\u00020\fH\u00c6\u0001\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010 \u001a\u00020\u001f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b \u0010!J\u0011\u0010\"\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\"\u0010\u0013J\u0011\u0010$\u001a\u00020#H\u00d6\u0081\u0004\u00a2\u0006\u0004\b$\u0010%R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010&\u001a\u0004\b'\u0010\u0011R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010(\u001a\u0004\b)\u0010\u0013R\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010*\u001a\u0004\b+\u0010\u0015R\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010,\u001a\u0004\b-\u0010\u0017R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010.\u001a\u0004\b/\u0010\u0019R\u0017\u0010\r\u001a\u00020\f8\u0006\u00a2\u0006\f\n\u0004\b\r\u00100\u001a\u0004\b1\u0010\u001b\u00a8\u00062"}, d2={"Lrtx/kimiko/utils/render/fonts/core/TextRenderer$FrameBatchKey;", "", "Lnet/minecraft/GuiRenderState;", "state", "", "layerSerial", "Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "page", "Lrtx/kimiko/utils/render/fonts/core/TextRenderer$PoseKey;", "pose", "Lnet/minecraft/ScreenRect;", "scissorArea", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "<init>", "(Lnet/minecraft/GuiRenderState;ILrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;Lrtx/kimiko/utils/render/fonts/core/TextRenderer$PoseKey;Lnet/minecraft/ScreenRect;Lcom/mojang/blaze3d/pipeline/RenderPipeline;)V", "component1", "()Lnet/minecraft/GuiRenderState;", "component2", "()I", "component3", "()Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "component4", "()Lrtx/kimiko/utils/render/fonts/core/TextRenderer$PoseKey;", "component5", "()Lnet/minecraft/ScreenRect;", "component6", "()Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "copy", "(Lnet/minecraft/GuiRenderState;ILrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;Lrtx/kimiko/utils/render/fonts/core/TextRenderer$PoseKey;Lnet/minecraft/ScreenRect;Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Lrtx/kimiko/utils/render/fonts/core/TextRenderer$FrameBatchKey;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/GuiRenderState;", "getState", "I", "getLayerSerial", "Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "getPage", "Lrtx/kimiko/utils/render/fonts/core/TextRenderer$PoseKey;", "getPose", "Lnet/minecraft/ScreenRect;", "getScissorArea", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "getPipeline", "rtx.kimiko:kimiko"})
    private static final class FrameBatchKey {
        @NotNull
        private final GuiRenderState state;
        private final int layerSerial;
        @NotNull
        private final GlyphAtlasPage page;
        @NotNull
        private final PoseKey pose;
        @Nullable
        private final ScreenRect scissorArea;
        @NotNull
        private final RenderPipeline pipeline;

        public FrameBatchKey(@NotNull GuiRenderState state, int layerSerial, @NotNull GlyphAtlasPage page, @NotNull PoseKey pose, @Nullable ScreenRect scissorArea, @NotNull RenderPipeline pipeline) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            Intrinsics.checkNotNullParameter((Object)page, (String)"page");
            Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
            Intrinsics.checkNotNullParameter((Object)pipeline, (String)"pipeline");
            this.state = state;
            this.layerSerial = layerSerial;
            this.page = page;
            this.pose = pose;
            this.scissorArea = scissorArea;
            this.pipeline = pipeline;
        }

        @NotNull
        public final GuiRenderState getState() {
            return this.state;
        }

        public final int getLayerSerial() {
            return this.layerSerial;
        }

        @NotNull
        public final GlyphAtlasPage getPage() {
            return this.page;
        }

        @NotNull
        public final PoseKey getPose() {
            return this.pose;
        }

        @Nullable
        public final ScreenRect getScissorArea() {
            return this.scissorArea;
        }

        @NotNull
        public final RenderPipeline getPipeline() {
            return this.pipeline;
        }

        @NotNull
        public final GuiRenderState component1() {
            return this.state;
        }

        public final int component2() {
            return this.layerSerial;
        }

        @NotNull
        public final GlyphAtlasPage component3() {
            return this.page;
        }

        @NotNull
        public final PoseKey component4() {
            return this.pose;
        }

        @Nullable
        public final ScreenRect component5() {
            return this.scissorArea;
        }

        @NotNull
        public final RenderPipeline component6() {
            return this.pipeline;
        }

        @NotNull
        public final FrameBatchKey copy(@NotNull GuiRenderState state, int layerSerial, @NotNull GlyphAtlasPage page, @NotNull PoseKey pose, @Nullable ScreenRect scissorArea, @NotNull RenderPipeline pipeline) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            Intrinsics.checkNotNullParameter((Object)page, (String)"page");
            Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
            Intrinsics.checkNotNullParameter((Object)pipeline, (String)"pipeline");
            return new FrameBatchKey(state, layerSerial, page, pose, scissorArea, pipeline);
        }

        public static /* synthetic */ FrameBatchKey copy$default(FrameBatchKey frameBatchKey, GuiRenderState guiRenderState2, int n, GlyphAtlasPage glyphAtlasPage, PoseKey poseKey, ScreenRect screenRect2, RenderPipeline renderPipeline, int n2, Object object) {
            if ((n2 & 1) != 0) {
                guiRenderState2 = frameBatchKey.state;
            }
            if ((n2 & 2) != 0) {
                n = frameBatchKey.layerSerial;
            }
            if ((n2 & 4) != 0) {
                glyphAtlasPage = frameBatchKey.page;
            }
            if ((n2 & 8) != 0) {
                poseKey = frameBatchKey.pose;
            }
            if ((n2 & 0x10) != 0) {
                screenRect2 = frameBatchKey.scissorArea;
            }
            if ((n2 & 0x20) != 0) {
                renderPipeline = frameBatchKey.pipeline;
            }
            return frameBatchKey.copy(guiRenderState2, n, glyphAtlasPage, poseKey, screenRect2, renderPipeline);
        }

        @NotNull
        public String toString() {
            return "FrameBatchKey(state=" + this.state + ", layerSerial=" + this.layerSerial + ", page=" + this.page + ", pose=" + this.pose + ", scissorArea=" + this.scissorArea + ", pipeline=" + this.pipeline + ")";
        }

        public int hashCode() {
            int result = this.state.hashCode();
            result = result * 31 + Integer.hashCode(this.layerSerial);
            result = result * 31 + this.page.hashCode();
            result = result * 31 + this.pose.hashCode();
            result = result * 31 + (this.scissorArea == null ? 0 : this.scissorArea.hashCode());
            result = result * 31 + this.pipeline.hashCode();
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
            if (!Intrinsics.areEqual((Object)this.page, (Object)frameBatchKey.page)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.pose, (Object)frameBatchKey.pose)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.scissorArea, (Object)frameBatchKey.scissorArea)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.pipeline, (Object)frameBatchKey.pipeline);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u000eJ\u0010\u0010\u0012\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u000eJ\u0010\u0010\u0013\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u000eJ\u0010\u0010\u0014\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u000eJ\u0010\u0010\u0015\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u000eJ`\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001d\u001a\u00020\u001cH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0011\u0010 \u001a\u00020\u001fH\u00d6\u0081\u0004\u00a2\u0006\u0004\b \u0010!R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010$\u001a\u0004\b\u0003\u0010\u000eR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010$\u001a\u0004\b\u0004\u0010\u000eR%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010$\u001a\u0004\b\u0005\u0010\u000eR%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010$\u001a\u0004\b\u0006\u0010\u000eR%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010$\u001a\u0004\b\u0007\u0010\u000eR%\u0010\b\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010$\u001a\u0004\b\b\u0010\u000eR%\u0010\t\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010$\u001a\u0004\b\t\u0010\u000eR%\u0010\n\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010$\u001a\u0004\b\n\u0010\u000e\u00a8\u0006%"}, d2={"Lrtx/kimiko/utils/render/fonts/core/TextRenderer$Glyph;", "", "", "x0", "y0", "x1", "y1", "u0", "v0", "u1", "v1", "<init>", "(FFFFFFFF)V", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(FFFFFFFF)Lrtx/kimiko/utils/render/fonts/core/TextRenderer$Glyph;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "rtx.kimiko:kimiko"})
    public static final class Glyph {
        private final float x0;
        private final float y0;
        private final float x1;
        private final float y1;
        private final float u0;
        private final float v0;
        private final float u1;
        private final float v1;

        public Glyph(float x0, float y0, float x1, float y1, float u0, float v0, float u1, float v1) {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
            this.u0 = u0;
            this.v0 = v0;
            this.u1 = u1;
            this.v1 = v1;
        }

        @JvmName(name="x0")
        public final float x0() {
            return this.x0;
        }

        @JvmName(name="y0")
        public final float y0() {
            return this.y0;
        }

        @JvmName(name="x1")
        public final float x1() {
            return this.x1;
        }

        @JvmName(name="y1")
        public final float y1() {
            return this.y1;
        }

        @JvmName(name="u0")
        public final float u0() {
            return this.u0;
        }

        @JvmName(name="v0")
        public final float v0() {
            return this.v0;
        }

        @JvmName(name="u1")
        public final float u1() {
            return this.u1;
        }

        @JvmName(name="v1")
        public final float v1() {
            return this.v1;
        }

        public final float component1() {
            return this.x0;
        }

        public final float component2() {
            return this.y0;
        }

        public final float component3() {
            return this.x1;
        }

        public final float component4() {
            return this.y1;
        }

        public final float component5() {
            return this.u0;
        }

        public final float component6() {
            return this.v0;
        }

        public final float component7() {
            return this.u1;
        }

        public final float component8() {
            return this.v1;
        }

        @NotNull
        public final Glyph copy(float x0, float y0, float x1, float y1, float u0, float v0, float u1, float v1) {
            return new Glyph(x0, y0, x1, y1, u0, v0, u1, v1);
        }

        public static /* synthetic */ Glyph copy$default(Glyph glyph, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, Object object) {
            if ((n & 1) != 0) {
                f = glyph.x0;
            }
            if ((n & 2) != 0) {
                f2 = glyph.y0;
            }
            if ((n & 4) != 0) {
                f3 = glyph.x1;
            }
            if ((n & 8) != 0) {
                f4 = glyph.y1;
            }
            if ((n & 0x10) != 0) {
                f5 = glyph.u0;
            }
            if ((n & 0x20) != 0) {
                f6 = glyph.v0;
            }
            if ((n & 0x40) != 0) {
                f7 = glyph.u1;
            }
            if ((n & 0x80) != 0) {
                f8 = glyph.v1;
            }
            return glyph.copy(f, f2, f3, f4, f5, f6, f7, f8);
        }

        @NotNull
        public String toString() {
            return "Glyph(x0=" + this.x0 + ", y0=" + this.y0 + ", x1=" + this.x1 + ", y1=" + this.y1 + ", u0=" + this.u0 + ", v0=" + this.v0 + ", u1=" + this.u1 + ", v1=" + this.v1 + ")";
        }

        public int hashCode() {
            int result = Float.hashCode(this.x0);
            result = result * 31 + Float.hashCode(this.y0);
            result = result * 31 + Float.hashCode(this.x1);
            result = result * 31 + Float.hashCode(this.y1);
            result = result * 31 + Float.hashCode(this.u0);
            result = result * 31 + Float.hashCode(this.v0);
            result = result * 31 + Float.hashCode(this.u1);
            result = result * 31 + Float.hashCode(this.v1);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Glyph)) {
                return false;
            }
            Glyph glyph = (Glyph)other;
            if (Float.compare(this.x0, glyph.x0) != 0) {
                return false;
            }
            if (Float.compare(this.y0, glyph.y0) != 0) {
                return false;
            }
            if (Float.compare(this.x1, glyph.x1) != 0) {
                return false;
            }
            if (Float.compare(this.y1, glyph.y1) != 0) {
                return false;
            }
            if (Float.compare(this.u0, glyph.u0) != 0) {
                return false;
            }
            if (Float.compare(this.v0, glyph.v0) != 0) {
                return false;
            }
            if (Float.compare(this.u1, glyph.u1) != 0) {
                return false;
            }
            return Float.compare(this.v1, glyph.v1) == 0;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fR%\u0010\u0006\u001a\u00020\u00058\u0007z\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u000f\u001a\u0004\b\u0006\u0010\u0010R%\u0010\u0007\u001a\u00020\u00058\u0007z\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010\u000f\u001a\u0004\b\u0007\u0010\u0010R+\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0007z\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0011\u001a\u0004\b\u0004\u0010\u0012\u00a8\u0006\u0014"}, d2={"Lrtx/kimiko/utils/render/fonts/core/TextRenderer$GlyphLayout;", "", "", "Lrtx/kimiko/utils/render/fonts/core/TextRenderer$GlyphPage;", "pages", "", "width", "height", "<init>", "(Ljava/util/List;FF)V", "", "empty", "()Z", "Lkotlin/jvm/JvmName;", "name", "F", "()F", "Ljava/util/List;", "()Ljava/util/List;", "Companion", "rtx.kimiko:kimiko"})
    public static final class GlyphLayout {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final float width;
        private final float height;
        @NotNull
        private final List<GlyphPage> pages;
        @JvmField
        @NotNull
        public static final GlyphLayout EMPTY = new GlyphLayout(CollectionsKt.emptyList(), 0.0f, 0.0f);

        public GlyphLayout(@NotNull List<GlyphPage> pages, float width, float height) {
            Intrinsics.checkNotNullParameter(pages, (String)"pages");
            this.width = width;
            this.height = height;
            List list = List.copyOf((Collection)pages);
            Intrinsics.checkNotNullExpressionValue(list, (String)"copyOf(...)");
            this.pages = list;
        }

        @JvmName(name="width")
        public final float width() {
            return this.width;
        }

        @JvmName(name="height")
        public final float height() {
            return this.height;
        }

        @JvmName(name="pages")
        @NotNull
        public final List<GlyphPage> pages() {
            return this.pages;
        }

        public final boolean empty() {
            return this.pages.isEmpty();
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/utils/render/fonts/core/TextRenderer$GlyphLayout.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/fonts/core/TextRenderer$GlyphLayout;", "Lkotlin/jvm/JvmField;", "EMPTY", "Lrtx/kimiko/utils/render/fonts/core/TextRenderer$GlyphLayout;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\u0004\b\u000b\u0010\fR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0003\u0010\u0010R%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0011\u001a\u0004\b\u0005\u0010\u0012R%\u0010\u0007\u001a\u00020\u00068\u0007z\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0013\u001a\u0004\b\u0007\u0010\u0014R+\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0007z\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010\u0015\u001a\u0004\b\n\u0010\u0016\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/utils/render/fonts/core/TextRenderer$GlyphPage;", "", "Lnet/minecraft/TextureSetup;", "textureSetup", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "textureView", "", "atlasSize", "", "Lrtx/kimiko/utils/render/fonts/core/TextRenderer$Glyph;", "glyphs", "<init>", "(Lnet/minecraft/TextureSetup;Lcom/mojang/blaze3d/textures/GpuTextureView;ILjava/util/List;)V", "Lkotlin/jvm/JvmName;", "name", "Lnet/minecraft/TextureSetup;", "()Lnet/minecraft/TextureSetup;", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "()Lcom/mojang/blaze3d/textures/GpuTextureView;", "I", "()I", "Ljava/util/List;", "()Ljava/util/List;", "rtx.kimiko:kimiko"})
    public static final class GlyphPage {
        @NotNull
        private final TextureSetup textureSetup;
        @NotNull
        private final GpuTextureView textureView;
        private final int atlasSize;
        @NotNull
        private final List<Glyph> glyphs;

        public GlyphPage(@NotNull TextureSetup textureSetup, @NotNull GpuTextureView textureView, int atlasSize, @NotNull List<Glyph> glyphs) {
            Intrinsics.checkNotNullParameter((Object)textureSetup, (String)"textureSetup");
            Intrinsics.checkNotNullParameter((Object)textureView, (String)"textureView");
            Intrinsics.checkNotNullParameter(glyphs, (String)"glyphs");
            this.textureSetup = textureSetup;
            this.textureView = textureView;
            this.atlasSize = atlasSize;
            List list = List.copyOf((Collection)glyphs);
            Intrinsics.checkNotNullExpressionValue(list, (String)"copyOf(...)");
            this.glyphs = list;
        }

        @JvmName(name="textureSetup")
        @NotNull
        public final TextureSetup textureSetup() {
            return this.textureSetup;
        }

        @JvmName(name="textureView")
        @NotNull
        public final GpuTextureView textureView() {
            return this.textureView;
        }

        @JvmName(name="atlasSize")
        public final int atlasSize() {
            return this.atlasSize;
        }

        @JvmName(name="glyphs")
        @NotNull
        public final List<Glyph> glyphs() {
            return this.glyphs;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0082\b\u0018\u0000 %2\u00020\u0001:\u0001%B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\fJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\fJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\fJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\fJL\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0019\u001a\u00020\u0018H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001c\u001a\u00020\u001bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001e\u001a\u0004\b \u0010\fR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b!\u0010\fR\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\"\u0010\fR\u0017\u0010\u0007\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b#\u0010\fR\u0017\u0010\b\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u001e\u001a\u0004\b$\u0010\f\u00a8\u0006&"}, d2={"Lrtx/kimiko/utils/render/fonts/core/TextRenderer$PoseKey;", "", "", "m00", "m01", "m10", "m11", "m20", "m21", "<init>", "(FFFFFF)V", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "copy", "(FFFFFF)Lrtx/kimiko/utils/render/fonts/core/TextRenderer$PoseKey;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "F", "getM00", "getM01", "getM10", "getM11", "getM20", "getM21", "Companion", "rtx.kimiko:kimiko"})
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

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/utils/render/fonts/core/TextRenderer$PoseKey.Companion;", "", "<init>", "()V", "Lorg/joml/Matrix3x2f;", "matrix", "Lrtx/kimiko/utils/render/fonts/core/TextRenderer$PoseKey;", "of", "(Lorg/joml/Matrix3x2f;)Lrtx/kimiko/utils/render/fonts/core/TextRenderer$PoseKey;", "rtx.kimiko:kimiko"})
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
}

