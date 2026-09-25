/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.fonts.core;

import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 #2\u00020\u0001:\u0001#BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0007\u0012\u0014\u0010\n\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0007\u0018\u00010\t\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\rJ\r\u0010\u000e\u001a\u00020\u0005\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0015R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0016R\"\u0010\n\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0007\u0018\u00010\t8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0017RL\u0010\u001b\u001a:\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0005 \u0019*\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00040\u00040\u0018j\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0005 \u0019*\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00040\u0004`\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR(\u0010\u001d\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0018j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0005`\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001cR0\u0010 \u001a\u001e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00050\u001ej\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0005`\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\"\u00a8\u0006$"}, d2={"Lrtx/kimiko/utils/render/fonts/core/FontFamily;", "", "", "name", "Ljava/util/function/Supplier;", "Ljava/awt/Font;", "primarySupplier", "", "fallbacks", "Ljava/util/function/IntFunction;", "lazyFallbacks", "<init>", "(Ljava/lang/String;Ljava/util/function/Supplier;Ljava/util/List;Ljava/util/function/IntFunction;)V", "()Ljava/lang/String;", "primary", "()Ljava/awt/Font;", "", "codePoint", "resolve", "(I)Ljava/awt/Font;", "findFont", "Ljava/lang/String;", "Ljava/util/function/Supplier;", "Ljava/util/function/IntFunction;", "Ljava/util/ArrayList;", "kotlin.jvm.PlatformType", "Lkotlin/collections/ArrayList;", "fallbackSuppliers", "Ljava/util/ArrayList;", "fallbackCache", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "resolvedFonts", "Ljava/util/HashMap;", "Ljava/awt/Font;", "Companion", "rtx.kimiko:kimiko"})
public final class FontFamily {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String name;
    @NotNull
    private final Supplier<Font> primarySupplier;
    @Nullable
    private final IntFunction<List<Font>> lazyFallbacks;
    @NotNull
    private final ArrayList<Supplier<Font>> fallbackSuppliers;
    @NotNull
    private final ArrayList<Font> fallbackCache;
    @NotNull
    private final HashMap<Integer, Font> resolvedFonts;
    @Nullable
    private Font primary;
    @NotNull
    private static final FontRenderContext CHECK_CONTEXT = new FontRenderContext(new AffineTransform(), RenderingHints.VALUE_TEXT_ANTIALIAS_ON, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);

    public FontFamily(@NotNull String name, @NotNull Supplier<Font> primarySupplier, @NotNull List<? extends Supplier<Font>> fallbacks, @Nullable IntFunction<List<Font>> lazyFallbacks) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter(primarySupplier, (String)"primarySupplier");
        Intrinsics.checkNotNullParameter(fallbacks, (String)"fallbacks");
        this.name = name;
        this.primarySupplier = primarySupplier;
        this.lazyFallbacks = lazyFallbacks;
        this.fallbackSuppliers = new ArrayList(fallbacks);
        this.fallbackCache = new ArrayList(fallbacks.size());
        this.resolvedFonts = new HashMap();
        int n = ((Collection)fallbacks).size();
        for (int i = 0; i < n; ++i) {
            this.fallbackCache.add(null);
        }
    }

    @NotNull
    public final String name() {
        return this.name;
    }

    @NotNull
    public final Font primary() {
        Font current = this.primary;
        if (current == null) {
            this.primary = current = this.primarySupplier.get();
        }
        return current;
    }

    @NotNull
    public final Font resolve(int codePoint) {
        Font font = this.resolvedFonts.computeIfAbsent(codePoint, this::findFont);
        return font;
    }

    private final Font findFont(int codePoint) {
        if (!Character.isValidCodePoint(codePoint)) {
            return this.primary();
        }
        Font primaryFont = this.primary();
        if (FontFamily.Companion.canDisplay(primaryFont, codePoint)) {
            return primaryFont;
        }
        int n = ((Collection)this.fallbackSuppliers).size();
        for (int i = 0; i < n; ++i) {
            Font font = this.fallbackCache.get(i);
            if (font == null) {
                font = this.fallbackSuppliers.get(i).get();
                this.fallbackCache.set(i, font);
            }
            if (!FontFamily.Companion.canDisplay(font, codePoint)) continue;
            return font;
        }
        if (this.lazyFallbacks != null) {
            for (Font font : this.lazyFallbacks.apply(codePoint)) {
                if (!FontFamily.Companion.canDisplay(font, codePoint)) continue;
                return font;
            }
        }
        return primaryFont;
    }

    @JvmStatic
    @NotNull
    public static final String fontKey(@Nullable Font font) {
        return Companion.fontKey(font);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J!\u0010\t\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u001d\u0010\r\u001a\u00020\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/utils/render/fonts/core/FontFamily.Companion;", "", "<init>", "()V", "Ljava/awt/Font;", "font", "", "codePoint", "", "canDisplay", "(Ljava/awt/Font;I)Z", "", "Lkotlin/jvm/JvmStatic;", "fontKey", "(Ljava/awt/Font;)Ljava/lang/String;", "Ljava/awt/font/FontRenderContext;", "CHECK_CONTEXT", "Ljava/awt/font/FontRenderContext;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final boolean canDisplay(Font font, int codePoint) {
            boolean bl;
            try {
                if (font == null || !font.canDisplay(codePoint)) {
                    return false;
                }
                FontRenderContext fontRenderContext = CHECK_CONTEXT;
                char[] cArray = Character.toChars(codePoint);
                Intrinsics.checkNotNullExpressionValue((Object)cArray, (String)"toChars(...)");
                char[] cArray2 = cArray;
                GlyphVector vector = font.createGlyphVector(fontRenderContext, new String(cArray2));
                bl = vector.getNumGlyphs() > 0 && vector.getGlyphCode(0) != font.getMissingGlyphCode();
            }
            catch (IllegalArgumentException ignored) {
                bl = false;
            }
            return bl;
        }

        @JvmStatic
        @NotNull
        public final String fontKey(@Nullable Font font) {
            if (font == null) {
                return "missing";
            }
            return font.getPSName() + "/" + font.getFontName(Locale.ROOT);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

