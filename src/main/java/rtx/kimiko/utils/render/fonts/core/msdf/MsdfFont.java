/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2FloatMap
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.Identifier
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.fonts.core.msdf;

import it.unimi.dsi.fastutil.longs.Long2FloatMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfGlyph;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\b\u0004\u0018\u0000 22\u00020\u0001:\u00012BS\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0012J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0013J\r\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0013J\u0015\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0016J\u0015\u0010\b\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\u0016J\u0015\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u001d\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000f\u0010 J\u001f\u0010#\u001a\u00020\u00072\b\u0010\"\u001a\u0004\u0018\u00010!2\u0006\u0010\u0014\u001a\u00020\u0007\u00a2\u0006\u0004\b#\u0010$J\u001f\u0010&\u001a\u00020%2\b\u0010\"\u001a\u0004\u0018\u00010!2\u0006\u0010\u0014\u001a\u00020\u0007\u00a2\u0006\u0004\b&\u0010'R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010(R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010)R\u0014\u0010\u0006\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010)R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010*R\u0014\u0010\t\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010*R\u0014\u0010\n\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010*R \u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f0\u000b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010+R\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000f\u0010,R\u0014\u0010-\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u001c\u00100\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u00101\u00a8\u00063"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont;", "", "Lnet/minecraft/Identifier;", "atlasTexture", "", "atlasWidth", "atlasHeight", "", "lineHeight", "ascender", "descender", "", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfGlyph;", "glyphs", "Lit/unimi/dsi/fastutil/longs/Long2FloatMap;", "kerning", "<init>", "(Lnet/minecraft/Identifier;IIFFFLjava/util/Map;Lit/unimi/dsi/fastutil/longs/Long2FloatMap;)V", "()Lnet/minecraft/Identifier;", "()I", "size", "ascent", "(F)F", "descent", "codePoint", "", "hasGlyph", "(I)Z", "glyph", "(I)Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfGlyph;", "left", "right", "(II)F", "", "text", "width", "(Ljava/lang/String;F)F", "", "glyphBounds", "(Ljava/lang/String;F)[F", "Lnet/minecraft/Identifier;", "I", "F", "Ljava/util/Map;", "Lit/unimi/dsi/fastutil/longs/Long2FloatMap;", "fallback", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfGlyph;", "", "fastGlyphs", "[Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfGlyph;", "Companion", "rtx.kimiko:kimiko"})
public final class MsdfFont {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Identifier atlasTexture;
    private final int atlasWidth;
    private final int atlasHeight;
    private final float lineHeight;
    private final float ascender;
    private final float descender;
    @NotNull
    private final Map<Integer, MsdfGlyph> glyphs;
    @NotNull
    private final Long2FloatMap kerning;
    @NotNull
    private final MsdfGlyph fallback;
    @NotNull
    private final MsdfGlyph[] fastGlyphs;
    private static final int FAST_GLYPH_LIMIT = 2048;
    @NotNull
    private static final MsdfGlyph MISSING = MsdfGlyph.Companion.nonDrawable(0.0f);

    public MsdfFont(@NotNull Identifier atlasTexture, int atlasWidth, int atlasHeight, float lineHeight, float ascender, float descender, @NotNull Map<Integer, MsdfGlyph> glyphs, @NotNull Long2FloatMap kerning) {
        MsdfGlyph[] msdfGlyphArray;
        Intrinsics.checkNotNullParameter((Object)atlasTexture, (String)"atlasTexture");
        Intrinsics.checkNotNullParameter(glyphs, (String)"glyphs");
        Intrinsics.checkNotNullParameter((Object)kerning, (String)"kerning");
        this.atlasTexture = atlasTexture;
        this.atlasWidth = atlasWidth;
        this.atlasHeight = atlasHeight;
        this.lineHeight = lineHeight;
        this.ascender = ascender;
        this.descender = descender;
        this.glyphs = glyphs;
        this.kerning = kerning;
        MsdfGlyph msdfGlyph = this.glyphs.get(63);
        if (msdfGlyph == null) {
            msdfGlyph = MISSING;
        }
        this.fallback = msdfGlyph;
        MsdfGlyph[] fast = new MsdfGlyph[2048];
        for (int i = 0; i < 2048; ++i) {
            MsdfGlyph g = this.glyphs.get(i);
            if (g == null) continue;
            fast[i] = g;
        }
        this.fastGlyphs = fast;
    }

    @NotNull
    public final Identifier atlasTexture() {
        return this.atlasTexture;
    }

    public final int atlasWidth() {
        return this.atlasWidth;
    }

    public final int atlasHeight() {
        return this.atlasHeight;
    }

    public final float ascent(float size) {
        return this.ascender * size;
    }

    public final float descent(float size) {
        return this.descender * size;
    }

    public final float lineHeight(float size) {
        return this.lineHeight * size;
    }

    public final boolean hasGlyph(int codePoint) {
        boolean bl = 0 <= codePoint ? codePoint < 2048 : false;
        if (bl) {
            return this.fastGlyphs[codePoint] != null;
        }
        return this.glyphs.containsKey(codePoint);
    }

    @NotNull
    public final MsdfGlyph glyph(int codePoint) {
        boolean bl = 0 <= codePoint ? codePoint < 2048 : false;
        if (bl) {
            MsdfGlyph fast = this.fastGlyphs[codePoint];
            if (fast != null) {
                return fast;
            }
        } else {
            MsdfGlyph glyph = this.glyphs.get(codePoint);
            if (glyph != null) {
                return glyph;
            }
        }
        if (codePoint <= 32 || Character.isWhitespace(codePoint) || !Character.isDefined(codePoint)) {
            return MISSING;
        }
        return this.fallback;
    }

    public final float kerning(int left, int right) {
        if (this.kerning.isEmpty()) {
            return 0.0f;
        }
        return this.kerning.get((long)left << 32 | (long)right & 0xFFFFFFFFL);
    }

    public final float width(@Nullable String text, float size) {
        CharSequence charSequence = text;
        if (charSequence == null || charSequence.length() == 0) {
            return 0.0f;
        }
        float maxWidth = 0.0f;
        float cursor = 0.0f;
        int prev = -1;
        int i = 0;
        while (i < text.length()) {
            int cp = text.codePointAt(i);
            i += Character.charCount(cp);
            if (cp == 10) {
                maxWidth = Math.max(maxWidth, cursor);
                cursor = 0.0f;
                prev = -1;
                continue;
            }
            MsdfGlyph glyph = this.glyph(cp);
            if (prev != -1) {
                cursor += this.kerning(prev, cp) * size;
            }
            cursor += glyph.advance() * size;
            prev = cp;
        }
        return Math.max(maxWidth, cursor);
    }

    @NotNull
    public final float[] glyphBounds(@Nullable String text, float size) {
        if (text == null || text.isEmpty()) {
            return new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        }
        float ascent = this.ascender * size;
        float left = Float.MAX_VALUE;
        float right = -3.4028235E38f;
        float top = Float.MAX_VALUE;
        float bottom = -3.4028235E38f;
        boolean any = false;
        float penX = 0.0f;
        int prev = -1;
        int i = 0;
        while (i < text.length()) {
            int cp = text.codePointAt(i);
            i += Character.charCount(cp);
            if (cp == 10) {
                prev = -1;
                continue;
            }
            MsdfGlyph glyph = this.glyph(cp);
            if (prev != -1) {
                penX += this.kerning(prev, cp) * size;
            }
            if (glyph.drawable()) {
                left = Math.min(left, penX + glyph.planeLeft() * size);
                right = Math.max(right, penX + glyph.planeRight() * size);
                top = Math.min(top, ascent - glyph.planeTop() * size);
                bottom = Math.max(bottom, ascent - glyph.planeBottom() * size);
                any = true;
            }
            penX += glyph.advance() * size;
            prev = cp;
        }
        if (any) {
            return new float[]{left, top, right, bottom};
        }
        return new float[]{0.0f, 0.0f, 0.0f, 0.0f};
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont.Companion;", "", "<init>", "()V", "", "FAST_GLYPH_LIMIT", "I", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfGlyph;", "MISSING", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfGlyph;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

