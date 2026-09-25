/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.fonts.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphMetrics;
import java.awt.font.GlyphVector;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.fonts.core.FontFamily;
import rtx.kimiko.utils.render.fonts.core.FontQuality;
import rtx.kimiko.utils.render.fonts.core.FontStrike;
import rtx.kimiko.utils.render.fonts.core.GlyphAtlasPage;
import rtx.kimiko.utils.render.fonts.core.GlyphInfo;
import rtx.kimiko.utils.render.fonts.core.LayoutGlyph;
import rtx.kimiko.utils.render.fonts.core.TextLayout;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0002CF\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0003OPQB\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0005\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0005\u00a2\u0006\u0004\b\f\u0010\u000bJ\u0015\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010 \u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b \u0010\u001bJ!\u0010!\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0002\u00a2\u0006\u0004\b!\u0010\"J\r\u0010$\u001a\u00020#\u00a2\u0006\u0004\b$\u0010%J'\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\r2\u0006\u0010'\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010(J\u0017\u0010)\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b)\u0010\u0019J'\u0010*\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\r2\u0006\u0010'\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b*\u0010(J!\u0010-\u001a\u0004\u0018\u00010,2\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010+\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b-\u0010.J\u0017\u0010/\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b/\u00100J\u0017\u00103\u001a\u00020#2\u0006\u00102\u001a\u000201H\u0002\u00a2\u0006\u0004\b3\u00104J\u000f\u00105\u001a\u00020#H\u0016\u00a2\u0006\u0004\b5\u0010%R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u00106R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u00107R\u0014\u0010\u0007\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u00107R\u0014\u00108\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00107R\u0014\u00109\u001a\u00020\r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u0010:R\u0014\u0010<\u001a\u00020;8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010=R0\u0010A\u001a\u001e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020\u000f0>j\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020\u000f`@8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bA\u0010BR\u0014\u0010D\u001a\u00020C8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u0014\u0010G\u001a\u00020F8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010HR$\u0010L\u001a\u0012\u0012\u0004\u0012\u00020J0Ij\b\u0012\u0004\u0012\u00020J`K8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010MR\u0014\u0010\n\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u00107R\u0014\u0010\f\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u00107R\u0014\u0010N\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u00107\u00a8\u0006R"}, d2={"Lrtx/kimiko/utils/render/fonts/core/FontStrike;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "Lrtx/kimiko/utils/render/fonts/core/FontFamily;", "family", "", "size", "density", "<init>", "(Lrtx/kimiko/utils/render/fonts/core/FontFamily;FF)V", "ascent", "()F", "lineHeight", "", "codePoint", "Lrtx/kimiko/utils/render/fonts/core/GlyphInfo;", "glyph", "(I)Lrtx/kimiko/utils/render/fonts/core/GlyphInfo;", "Ljava/awt/Font;", "fontFor", "(I)Ljava/awt/Font;", "", "text", "Lrtx/kimiko/utils/render/fonts/core/TextLayout;", "layout", "(Ljava/lang/String;)Lrtx/kimiko/utils/render/fonts/core/TextLayout;", "width", "(Ljava/lang/String;)F", "sourceFont", "Lrtx/kimiko/utils/render/fonts/core/FontStrike$RunLayout;", "layoutRun", "(Ljava/awt/Font;Ljava/lang/String;)Lrtx/kimiko/utils/render/fonts/core/FontStrike$RunLayout;", "measureWidth", "measureRun", "(Ljava/awt/Font;Ljava/lang/String;)F", "", "uploadDirtyPages", "()V", "glyphCode", "fallbackAdvance", "(Ljava/awt/Font;IF)Lrtx/kimiko/utils/render/fonts/core/GlyphInfo;", "buildLayout", "bakeGlyph", "height", "Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage$Allocation;", "allocate", "(II)Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage$Allocation;", "physicalFont", "(Ljava/awt/Font;)Ljava/awt/Font;", "Ljava/awt/image/BufferedImage;", "image", "strengthenSmallGlyph", "(Ljava/awt/image/BufferedImage;)V", "close", "Lrtx/kimiko/utils/render/fonts/core/FontFamily;", "F", "oversample", "glyphPadding", "I", "Ljava/awt/font/FontRenderContext;", "renderContext", "Ljava/awt/font/FontRenderContext;", "Ljava/util/HashMap;", "Lrtx/kimiko/utils/render/fonts/core/FontStrike$GlyphKey;", "Lkotlin/collections/HashMap;", "glyphs", "Ljava/util/HashMap;", "rtx/kimiko/utils/render/fonts/core/FontStrike$layouts$1", "layouts", "Lrtx/kimiko/utils/render/fonts/core/FontStrike$layouts$1;", "rtx/kimiko/utils/render/fonts/core/FontStrike$widths$1", "widths", "Lrtx/kimiko/utils/render/fonts/core/FontStrike$widths$1;", "Ljava/util/ArrayList;", "Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "Lkotlin/collections/ArrayList;", "pages", "Ljava/util/ArrayList;", "spaceAdvance", "ShapedGlyph", "RunLayout", "GlyphKey", "rtx.kimiko:kimiko"})
public final class FontStrike
implements AutoCloseable {
    @NotNull
    private final FontFamily family;
    private final float size;
    private final float density;
    private final float oversample;
    private final int glyphPadding;
    @NotNull
    private final FontRenderContext renderContext;
    @NotNull
    private final HashMap<GlyphKey, GlyphInfo> glyphs;
    @NotNull
    private final LinkedHashMap<String, TextLayout> layouts;
    @NotNull
    private final LinkedHashMap<String, Float> widths;
    @NotNull
    private final ArrayList<GlyphAtlasPage> pages;
    private final float ascent;
    private final float lineHeight;
    private final float spaceAdvance;

    public FontStrike(@NotNull FontFamily family, float size, float density) {
        Intrinsics.checkNotNullParameter((Object)family, (String)"family");
        this.family = family;
        this.size = size;
        this.density = density > 0.0f ? density : 1.0f;
        this.oversample = FontQuality.rasterScaleFor(this.size, this.density);
        this.glyphPadding = FontQuality.glyphPadding(this.oversample);
        this.renderContext = new FontRenderContext(new AffineTransform(), RenderingHints.VALUE_TEXT_ANTIALIAS_ON, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        this.glyphs = new HashMap();
        this.layouts = new LinkedHashMap<String, TextLayout>(){

            protected boolean removeEldestEntry(Map.Entry<String, TextLayout> eldest) {
                Intrinsics.checkNotNullParameter(eldest, (String)"eldest");
                return this.size() > 2048;
            }
        };
        this.widths = new LinkedHashMap<String, Float>(){

            protected boolean removeEldestEntry(Map.Entry<String, Float> eldest) {
                Intrinsics.checkNotNullParameter(eldest, (String)"eldest");
                return this.size() > 2048;
            }
        };
        this.pages = new ArrayList();
        Font metricsFont = this.family.primary().deriveFont(0, this.size * this.oversample);
        LineMetrics metrics = metricsFont.getLineMetrics("Mg", this.renderContext);
        this.ascent = metrics.getAscent() / this.oversample;
        this.lineHeight = Math.max(this.size, metrics.getHeight() / this.oversample);
        this.spaceAdvance = Math.max(metricsFont.createGlyphVector(this.renderContext, " ").getGlyphMetrics(0).getAdvance() / this.oversample, this.size * 0.25f);
    }

    public final float ascent() {
        return this.ascent;
    }

    public final float lineHeight() {
        return this.lineHeight;
    }

    @NotNull
    public final GlyphInfo glyph(int codePoint) {
        if (codePoint == 9) {
            GlyphInfo space = this.glyph(32);
            return space.withAdvance(space.advance() * 4.0f);
        }
        Font sourceFont = this.family.resolve(codePoint);
        Font font = this.physicalFont(sourceFont);
        char[] cArray = Character.toChars(codePoint);
        Intrinsics.checkNotNullExpressionValue((Object)cArray, (String)"toChars(...)");
        char[] cArray2 = cArray;
        GlyphVector vector = font.createGlyphVector(this.renderContext, new String(cArray2));
        int glyphCode = vector.getGlyphCode(0);
        float advance = vector.getGlyphMetrics(0).getAdvance() / this.oversample;
        return this.glyph(sourceFont, glyphCode, advance);
    }

    @NotNull
    public final Font fontFor(int codePoint) {
        return this.family.resolve(codePoint);
    }

    @NotNull
    public final TextLayout layout(@Nullable String text) {
        CharSequence charSequence = text;
        if (charSequence == null || charSequence.length() == 0) {
            return TextLayout.EMPTY;
        }
        TextLayout textLayout = this.layouts.computeIfAbsent(text, this::buildLayout);
        Intrinsics.checkNotNullExpressionValue((Object)textLayout, (String)"computeIfAbsent(...)");
        return textLayout;
    }

    public final float width(@Nullable String text) {
        CharSequence charSequence = text;
        if (charSequence == null || charSequence.length() == 0) {
            return 0.0f;
        }
        Float f = this.widths.computeIfAbsent(text, this::measureWidth);
        Intrinsics.checkNotNullExpressionValue((Object)f, (String)"computeIfAbsent(...)");
        return ((Number)f).floatValue();
    }

    @NotNull
    public final RunLayout layoutRun(@NotNull Font sourceFont, @Nullable String text) {
        float shiftX;
        Intrinsics.checkNotNullParameter((Object)sourceFont, (String)"sourceFont");
        CharSequence charSequence = text;
        if (charSequence == null || charSequence.length() == 0) {
            return RunLayout.EMPTY;
        }
        Font font = this.physicalFont(sourceFont);
        char[] cArray = text.toCharArray();
        Intrinsics.checkNotNullExpressionValue((Object)cArray, (String)"toCharArray(...)");
        char[] chars = cArray;
        int flags = new Bidi(text, -2).isRightToLeft() ? 1 : 0;
        GlyphVector vector = font.layoutGlyphVector(this.renderContext, chars, 0, chars.length, flags);
        int glyphCount = vector.getNumGlyphs();
        if (glyphCount == 0) {
            return RunLayout.EMPTY;
        }
        ArrayList<ShapedGlyph> shapedGlyphs = new ArrayList<ShapedGlyph>(glyphCount);
        float minX = 0.0f;
        float maxX = 0.0f;
        for (int glyphIndex = 0; glyphIndex < glyphCount; ++glyphIndex) {
            Point2D position = vector.getGlyphPosition(glyphIndex);
            Point2D next = vector.getGlyphPosition(glyphIndex + 1);
            float glyphX = (float)position.getX() / this.oversample;
            float glyphY = (float)position.getY() / this.oversample;
            float advance = Math.abs((float)(next.getX() - position.getX()) / this.oversample);
            GlyphInfo glyph = this.glyph(sourceFont, vector.getGlyphCode(glyphIndex), advance);
            if (glyph.drawable()) {
                minX = Math.min(minX, glyphX + glyph.xOffset());
                maxX = Math.max(maxX, glyphX + glyph.xOffset() + glyph.width());
            } else {
                maxX = Math.max(maxX, glyphX + glyph.advance());
            }
            shapedGlyphs.add(new ShapedGlyph(glyph, glyphX, glyphY));
        }
        float runAdvance = Math.max(Math.abs((float)vector.getGlyphPosition(glyphCount).getX() / this.oversample), maxX - minX);
        float f = shiftX = minX < 0.0f ? -minX : 0.0f;
        if (shiftX > 0.0f) {
            ArrayList<ShapedGlyph> shifted = new ArrayList<ShapedGlyph>(shapedGlyphs.size());
            Iterator iterator = shapedGlyphs.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
            Iterator iterator2 = iterator;
            while (iterator2.hasNext()) {
                Object e = iterator2.next();
                Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
                ShapedGlyph shapedGlyph = (ShapedGlyph)e;
                shifted.add(new ShapedGlyph(shapedGlyph.glyph(), shapedGlyph.x() + shiftX, shapedGlyph.y()));
            }
            shapedGlyphs = shifted;
            runAdvance += shiftX;
        }
        return new RunLayout((List<ShapedGlyph>)shapedGlyphs, runAdvance);
    }

    private final float measureWidth(String text) {
        float cursorX = 0.0f;
        float maxWidth = 0.0f;
        int i = 0;
        while (i < text.length()) {
            int nextCodePoint;
            int codePoint = text.codePointAt(i);
            if (codePoint == 10) {
                i += Character.charCount(codePoint);
                maxWidth = Math.max(maxWidth, cursorX);
                cursorX = 0.0f;
                continue;
            }
            if (codePoint == 9) {
                i += Character.charCount(codePoint);
                cursorX += this.spaceAdvance * 4.0f;
                continue;
            }
            if (Character.isISOControl(codePoint)) {
                i += Character.charCount(codePoint);
                continue;
            }
            Font font = this.fontFor(codePoint);
            int runStart = i;
            i += Character.charCount(codePoint);
            while (i < text.length() && (nextCodePoint = text.codePointAt(i)) != 10 && nextCodePoint != 9 && !Character.isISOControl(nextCodePoint)) {
                Font nextFont = this.fontFor(nextCodePoint);
                if (!Intrinsics.areEqual((Object)FontFamily.Companion.fontKey(font), (Object)FontFamily.Companion.fontKey(nextFont))) break;
                i += Character.charCount(nextCodePoint);
            }
            String string = text.substring(runStart, i);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            cursorX += this.measureRun(font, string);
        }
        return Math.max(maxWidth, cursorX);
    }

    private final float measureRun(Font sourceFont, String text) {
        CharSequence charSequence = text;
        if (charSequence == null || charSequence.length() == 0) {
            return 0.0f;
        }
        Font font = this.physicalFont(sourceFont);
        char[] cArray = text.toCharArray();
        Intrinsics.checkNotNullExpressionValue((Object)cArray, (String)"toCharArray(...)");
        char[] chars = cArray;
        int flags = new Bidi(text, -2).isRightToLeft() ? 1 : 0;
        GlyphVector vector = font.layoutGlyphVector(this.renderContext, chars, 0, chars.length, flags);
        int glyphCount = vector.getNumGlyphs();
        if (glyphCount == 0) {
            return 0.0f;
        }
        float advance = Math.abs((float)vector.getGlyphPosition(glyphCount).getX() / this.oversample);
        if (advance > 0.0f) {
            return advance;
        }
        return (float)font.getStringBounds(text, this.renderContext).getWidth() / this.oversample;
    }

    public final void uploadDirtyPages() {
        Iterator<GlyphAtlasPage> iterator = this.pages.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<GlyphAtlasPage> iterator2 = iterator;
        while (iterator2.hasNext()) {
            GlyphAtlasPage page = (GlyphAtlasPage) (iterator2.next());
            page.uploadIfDirty();
        }
    }

    private final GlyphInfo glyph(Font sourceFont, int glyphCode, float fallbackAdvance) {
        GlyphKey key = new GlyphKey(FontFamily.Companion.fontKey(sourceFont), glyphCode);
        GlyphInfo glyphInfo = this.glyphs.computeIfAbsent(key, it -> this.bakeGlyph(sourceFont, glyphCode, fallbackAdvance));
        Intrinsics.checkNotNullExpressionValue((Object)glyphInfo, (String)"computeIfAbsent(...)");
        return glyphInfo;
    }

    private final TextLayout buildLayout(String text) {
        LinkedHashMap<GlyphAtlasPage, List<LayoutGlyph>> batches = new LinkedHashMap<>();
        float cursorX = 0.0f;
        float cursorY = 0.0f;
        float maxWidth = 0.0f;
        float baselineY = this.ascent;
        int i = 0;
        while (i < text.length()) {
            int nextCodePoint;
            int codePoint = text.codePointAt(i);
            if (codePoint == 10) {
                i += Character.charCount(codePoint);
                maxWidth = Math.max(maxWidth, cursorX);
                cursorX = 0.0f;
                cursorY += this.lineHeight;
                continue;
            }
            if (codePoint == 9) {
                i += Character.charCount(codePoint);
                cursorX += this.glyph(9).advance();
                continue;
            }
            if (Character.isISOControl(codePoint)) {
                i += Character.charCount(codePoint);
                continue;
            }
            Font font = this.fontFor(codePoint);
            int runStart = i;
            i += Character.charCount(codePoint);
            while (i < text.length() && (nextCodePoint = text.codePointAt(i)) != 10 && nextCodePoint != 9 && !Character.isISOControl(nextCodePoint)) {
                Font nextFont = this.fontFor(nextCodePoint);
                if (!Intrinsics.areEqual((Object)FontFamily.Companion.fontKey(font), (Object)FontFamily.Companion.fontKey(nextFont))) break;
                i += Character.charCount(nextCodePoint);
            }
            String string = text.substring(runStart, i);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            RunLayout run = this.layoutRun(font, string);
            for (ShapedGlyph shapedGlyph : run.glyphs()) {
                GlyphInfo glyph = shapedGlyph.glyph();
                if (!glyph.drawable()) continue;
                float x0 = cursorX + shapedGlyph.x() + glyph.xOffset();
                float y0 = baselineY + cursorY + shapedGlyph.y() + glyph.yOffset();
                float x1 = x0 + glyph.width();
                float y1 = y0 + glyph.height();
                GlyphAtlasPage glyphAtlasPage = glyph.page();
                Intrinsics.checkNotNull((Object)glyphAtlasPage);
                batches.computeIfAbsent(glyphAtlasPage, k -> new ArrayList<>()).add(new LayoutGlyph(glyph.page(), x0, y0, x1, y1, glyph.u0(), glyph.v0(), glyph.u1(), glyph.v1()));
            }
            cursorX += run.advance();
        }
        maxWidth = Math.max(maxWidth, cursorX);
        ArrayList<TextLayout.Page> layoutPages = new ArrayList<TextLayout.Page>(batches.size());
        for (Map.Entry<GlyphAtlasPage, List<LayoutGlyph>> entry : batches.entrySet()) {
            layoutPages.add(new TextLayout.Page(entry.getKey(), entry.getValue()));
        }
        return new TextLayout((List<TextLayout.Page>)layoutPages, maxWidth, cursorY + this.lineHeight);
    }

    private final GlyphInfo bakeGlyph(Font sourceFont, int glyphCode, float fallbackAdvance) {
        Font font = this.physicalFont(sourceFont);
        int[] nArray = new int[]{glyphCode};
        GlyphVector glyphVector = font.createGlyphVector(this.renderContext, nArray);
        Intrinsics.checkNotNullExpressionValue((Object)glyphVector, (String)"createGlyphVector(...)");
        GlyphVector vector = glyphVector;
        GlyphMetrics metrics = vector.getGlyphMetrics(0);
        float advance = Math.max(fallbackAdvance > 0.0f ? fallbackAdvance : metrics.getAdvance() / this.oversample, 0.0f);
        Rectangle rectangle = vector.getPixelBounds(this.renderContext, 0.0f, 0.0f);
        Intrinsics.checkNotNullExpressionValue((Object)rectangle, (String)"getPixelBounds(...)");
        Rectangle bounds = rectangle;
        if (bounds.width <= 0 || bounds.height <= 0) {
            return GlyphInfo.Companion.empty(advance);
        }
        int imageWidth = bounds.width + this.glyphPadding * 2;
        int imageHeight = bounds.height + this.glyphPadding * 2;
        if (imageWidth <= 0 || imageHeight <= 0 || imageWidth > 2048 || imageHeight > 2048) {
            return GlyphInfo.Companion.empty(advance);
        }
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, 2);
        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        graphics.setColor(Color.WHITE);
        graphics.drawGlyphVector(vector, this.glyphPadding - bounds.x, this.glyphPadding - bounds.y);
        graphics.dispose();
        this.strengthenSmallGlyph(image);
        GlyphAtlasPage.Allocation allocation = this.allocate(imageWidth, imageHeight);
        if (allocation == null) {
            return GlyphInfo.Companion.empty(advance);
        }
        GlyphAtlasPage.Allocation allocation2 = allocation;
        allocation2.page().copy(image, allocation2.x(), allocation2.y());
        float xOffset = (float)(bounds.x - this.glyphPadding) / this.oversample;
        float yOffset = (float)(bounds.y - this.glyphPadding) / this.oversample;
        float width = (float)imageWidth / this.oversample;
        float height = (float)imageHeight / this.oversample;
        return new GlyphInfo(allocation2.page(), allocation2.u0(), allocation2.v0(), allocation2.u1(), allocation2.v1(), xOffset, yOffset, width, height, advance);
    }

    private final GlyphAtlasPage.Allocation allocate(int width, int height) {
        Iterator<GlyphAtlasPage> iterator = this.pages.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<GlyphAtlasPage> iterator2 = iterator;
        while (iterator2.hasNext()) {
            GlyphAtlasPage page = (GlyphAtlasPage) (iterator2.next());
            GlyphAtlasPage.Allocation allocation = page.allocate(width, height);
            if (allocation == null) continue;
            return allocation;
        }
        GlyphAtlasPage page = new GlyphAtlasPage(2048);
        this.pages.add(page);
        return page.allocate(width, height);
    }

    private final Font physicalFont(Font sourceFont) {
        Font font = sourceFont.deriveFont(0, this.size * this.oversample);
        Intrinsics.checkNotNullExpressionValue((Object)font, (String)"deriveFont(...)");
        return font;
    }

    private final void strengthenSmallGlyph(BufferedImage image) {
        float weight = FontQuality.coverageWeight(this.size * this.density);
        if (weight <= 0.0f) {
            return;
        }
        int width = image.getWidth();
        int height = image.getHeight();
        int[] source = image.getRGB(0, 0, width, height, null, 0, width);
        int[] target = (int[])source.clone();
        int n = height - 1;
        for (int y = 1; y < n; ++y) {
            int row = y * width;
            int n2 = width - 1;
            for (int x = 1; x < n2; ++x) {
                int alpha;
                int index = row + x;
                int neighborAlpha = alpha = source[index] >>> 24 & 0xFF;
                neighborAlpha = Math.max(neighborAlpha, source[index - 1] >>> 24 & 0xFF);
                neighborAlpha = Math.max(neighborAlpha, source[index + 1] >>> 24 & 0xFF);
                neighborAlpha = Math.max(neighborAlpha, source[index - width] >>> 24 & 0xFF);
                neighborAlpha = Math.max(neighborAlpha, source[index + width] >>> 24 & 0xFF);
                neighborAlpha = Math.max(neighborAlpha, source[index - width - 1] >>> 24 & 0xFF);
                neighborAlpha = Math.max(neighborAlpha, source[index - width + 1] >>> 24 & 0xFF);
                neighborAlpha = Math.max(neighborAlpha, source[index + width - 1] >>> 24 & 0xFF);
                neighborAlpha = Math.max(neighborAlpha, source[index + width + 1] >>> 24 & 0xFF);
                int boostedAlpha = Math.min(255, Math.round((float)alpha + (float)(neighborAlpha - alpha) * weight));
                target[index] = boostedAlpha << 24 | 0xFFFFFF;
            }
        }
        image.setRGB(0, 0, width, height, target, 0, width);
    }

    @Override
    public void close() {
        this.glyphs.clear();
        this.layouts.clear();
        this.widths.clear();
        for (GlyphAtlasPage page : this.pages) {
            page.close();
        }
        this.pages.clear();
    }

    private static final TextLayout layout$lambda$0(FontStrike this$0, String it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return this$0.buildLayout(it);
    }

    private static final TextLayout layout$lambda$1(Function1 $tmp0, Object p0) {
        return (TextLayout)$tmp0.invoke(p0);
    }

    private static final Float width$lambda$0(FontStrike this$0, String it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return Float.valueOf(this$0.measureWidth(it));
    }

    private static final Float width$lambda$1(Function1 $tmp0, Object p0) {
        return (Float)$tmp0.invoke(p0);
    }

    private static final GlyphInfo glyph$lambda$0(FontStrike this$0, Font $sourceFont, int $glyphCode, float $fallbackAdvance, GlyphKey it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return this$0.bakeGlyph($sourceFont, $glyphCode, $fallbackAdvance);
    }

    private static final GlyphInfo glyph$lambda$1(Function1 $tmp0, Object p0) {
        return (GlyphInfo)$tmp0.invoke(p0);
    }

    private static final List buildLayout$lambda$0(GlyphAtlasPage it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return new ArrayList();
    }

    private static final List buildLayout$lambda$1(Function1 $tmp0, Object p0) {
        return (List)$tmp0.invoke(p0);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\t\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0012\u0010\u000bJ\u0011\u0010\u0013\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\tR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u000b\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/utils/render/fonts/core/FontStrike$GlyphKey;", "", "", "fontKey", "", "glyphCode", "<init>", "(Ljava/lang/String;I)V", "component1", "()Ljava/lang/String;", "component2", "()I", "copy", "(Ljava/lang/String;I)Lrtx/kimiko/utils/render/fonts/core/FontStrike$GlyphKey;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Ljava/lang/String;", "getFontKey", "I", "getGlyphCode", "rtx.kimiko:kimiko"})
    private static final class GlyphKey {
        @NotNull
        private final String fontKey;
        private final int glyphCode;

        public GlyphKey(@NotNull String fontKey, int glyphCode) {
            Intrinsics.checkNotNullParameter((Object)fontKey, (String)"fontKey");
            this.fontKey = fontKey;
            this.glyphCode = glyphCode;
        }

        @NotNull
        public final String getFontKey() {
            return this.fontKey;
        }

        public final int getGlyphCode() {
            return this.glyphCode;
        }

        @NotNull
        public final String component1() {
            return this.fontKey;
        }

        public final int component2() {
            return this.glyphCode;
        }

        @NotNull
        public final GlyphKey copy(@NotNull String fontKey, int glyphCode) {
            Intrinsics.checkNotNullParameter((Object)fontKey, (String)"fontKey");
            return new GlyphKey(fontKey, glyphCode);
        }

        public static /* synthetic */ GlyphKey copy$default(GlyphKey glyphKey, String string, int n, int n2, Object object) {
            if ((n2 & 1) != 0) {
                string = glyphKey.fontKey;
            }
            if ((n2 & 2) != 0) {
                n = glyphKey.glyphCode;
            }
            return glyphKey.copy(string, n);
        }

        @NotNull
        public String toString() {
            return "GlyphKey(fontKey=" + this.fontKey + ", glyphCode=" + this.glyphCode + ")";
        }

        public int hashCode() {
            int result = this.fontKey.hashCode();
            result = result * 31 + Integer.hashCode(this.glyphCode);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GlyphKey)) {
                return false;
            }
            GlyphKey glyphKey = (GlyphKey)other;
            if (!Intrinsics.areEqual((Object)this.fontKey, (Object)glyphKey.fontKey)) {
                return false;
            }
            return this.glyphCode == glyphKey.glyphCode;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ*\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0014\u001a\u00020\u0013H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0017\u001a\u00020\u0016H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018R+\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0007z\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u0004\u0010\nR%\u0010\u0006\u001a\u00020\u00058\u0007z\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b\u0006\u0010\f\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/utils/render/fonts/core/FontStrike$RunLayout;", "", "", "Lrtx/kimiko/utils/render/fonts/core/FontStrike$ShapedGlyph;", "glyphs", "", "advance", "<init>", "(Ljava/util/List;F)V", "component1", "()Ljava/util/List;", "component2", "()F", "copy", "(Ljava/util/List;F)Lrtx/kimiko/utils/render/fonts/core/FontStrike$RunLayout;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "Ljava/util/List;", "F", "Companion", "rtx.kimiko:kimiko"})
    public static final class RunLayout {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @NotNull
        private final List<ShapedGlyph> glyphs;
        private final float advance;
        @JvmField
        @NotNull
        public static final RunLayout EMPTY = new RunLayout(CollectionsKt.emptyList(), 0.0f);

        public RunLayout(@NotNull List<ShapedGlyph> glyphs, float advance) {
            Intrinsics.checkNotNullParameter(glyphs, (String)"glyphs");
            this.glyphs = glyphs;
            this.advance = advance;
        }

        @JvmName(name="glyphs")
        @NotNull
        public final List<ShapedGlyph> glyphs() {
            return this.glyphs;
        }

        @JvmName(name="advance")
        public final float advance() {
            return this.advance;
        }

        @NotNull
        public final List<ShapedGlyph> component1() {
            return this.glyphs;
        }

        public final float component2() {
            return this.advance;
        }

        @NotNull
        public final RunLayout copy(@NotNull List<ShapedGlyph> glyphs, float advance) {
            Intrinsics.checkNotNullParameter(glyphs, (String)"glyphs");
            return new RunLayout(glyphs, advance);
        }

        public static /* synthetic */ RunLayout copy$default(RunLayout runLayout, List list, float f, int n, Object object) {
            if ((n & 1) != 0) {
                list = runLayout.glyphs;
            }
            if ((n & 2) != 0) {
                f = runLayout.advance;
            }
            return runLayout.copy(list, f);
        }

        @NotNull
        public String toString() {
            return "RunLayout(glyphs=" + this.glyphs + ", advance=" + this.advance + ")";
        }

        public int hashCode() {
            int result = ((Object)this.glyphs).hashCode();
            result = result * 31 + Float.hashCode(this.advance);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RunLayout)) {
                return false;
            }
            RunLayout runLayout = (RunLayout)other;
            if (!Intrinsics.areEqual(this.glyphs, runLayout.glyphs)) {
                return false;
            }
            return Float.compare(this.advance, runLayout.advance) == 0;
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/utils/render/fonts/core/FontStrike$RunLayout.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/fonts/core/FontStrike$RunLayout;", "Lkotlin/jvm/JvmField;", "EMPTY", "Lrtx/kimiko/utils/render/fonts/core/FontStrike$RunLayout;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\fJ.\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0015\u001a\u00020\u0014H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0011\u0010\u0018\u001a\u00020\u0017H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u0003\u0010\nR%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001d\u001a\u0004\b\u0005\u0010\fR%\u0010\u0006\u001a\u00020\u00048\u0007z\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001d\u001a\u0004\b\u0006\u0010\f\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/utils/render/fonts/core/FontStrike$ShapedGlyph;", "", "Lrtx/kimiko/utils/render/fonts/core/GlyphInfo;", "glyph", "", "x", "y", "<init>", "(Lrtx/kimiko/utils/render/fonts/core/GlyphInfo;FF)V", "component1", "()Lrtx/kimiko/utils/render/fonts/core/GlyphInfo;", "component2", "()F", "component3", "copy", "(Lrtx/kimiko/utils/render/fonts/core/GlyphInfo;FF)Lrtx/kimiko/utils/render/fonts/core/FontStrike$ShapedGlyph;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "Lrtx/kimiko/utils/render/fonts/core/GlyphInfo;", "F", "rtx.kimiko:kimiko"})
    public static final class ShapedGlyph {
        @NotNull
        private final GlyphInfo glyph;
        private final float x;
        private final float y;

        public ShapedGlyph(@NotNull GlyphInfo glyph, float x, float y) {
            Intrinsics.checkNotNullParameter((Object)glyph, (String)"glyph");
            this.glyph = glyph;
            this.x = x;
            this.y = y;
        }

        @JvmName(name="glyph")
        @NotNull
        public final GlyphInfo glyph() {
            return this.glyph;
        }

        @JvmName(name="x")
        public final float x() {
            return this.x;
        }

        @JvmName(name="y")
        public final float y() {
            return this.y;
        }

        @NotNull
        public final GlyphInfo component1() {
            return this.glyph;
        }

        public final float component2() {
            return this.x;
        }

        public final float component3() {
            return this.y;
        }

        @NotNull
        public final ShapedGlyph copy(@NotNull GlyphInfo glyph, float x, float y) {
            Intrinsics.checkNotNullParameter((Object)glyph, (String)"glyph");
            return new ShapedGlyph(glyph, x, y);
        }

        public static /* synthetic */ ShapedGlyph copy$default(ShapedGlyph shapedGlyph, GlyphInfo glyphInfo, float f, float f2, int n, Object object) {
            if ((n & 1) != 0) {
                glyphInfo = shapedGlyph.glyph;
            }
            if ((n & 2) != 0) {
                f = shapedGlyph.x;
            }
            if ((n & 4) != 0) {
                f2 = shapedGlyph.y;
            }
            return shapedGlyph.copy(glyphInfo, f, f2);
        }

        @NotNull
        public String toString() {
            return "ShapedGlyph(glyph=" + this.glyph + ", x=" + this.x + ", y=" + this.y + ")";
        }

        public int hashCode() {
            int result = this.glyph.hashCode();
            result = result * 31 + Float.hashCode(this.x);
            result = result * 31 + Float.hashCode(this.y);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ShapedGlyph)) {
                return false;
            }
            ShapedGlyph shapedGlyph = (ShapedGlyph)other;
            if (!Intrinsics.areEqual((Object)this.glyph, (Object)shapedGlyph.glyph)) {
                return false;
            }
            if (Float.compare(this.x, shapedGlyph.x) != 0) {
                return false;
            }
            return Float.compare(this.y, shapedGlyph.y) == 0;
        }
    }
}

