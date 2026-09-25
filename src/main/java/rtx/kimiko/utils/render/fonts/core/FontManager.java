/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.fonts.core;

import java.awt.Font;
import java.io.Closeable;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.fonts.core.FontFamily;
import rtx.kimiko.utils.render.fonts.core.FontManager;
import rtx.kimiko.utils.render.fonts.core.FontStrike;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u007f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\n*\u00019\u0018\u0000 @2\u00060\u0001j\u0002`\u0002:\u0002A@B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001f\u0010\n\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\r\u001a\u00020\f2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0004J%\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J+\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0016\u001a\u00020\u00052\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ/\u0010\u001e\u001a\u00020\u000f2\u001e\u0010\u001d\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u001c\"\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010!\u001a\u00020\u000f2\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011H\u0002\u00a2\u0006\u0004\b!\u0010\"J\u001d\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00120%2\u0006\u0010$\u001a\u00020#H\u0002\u00a2\u0006\u0004\b&\u0010'J\u0017\u0010(\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b(\u0010)J%\u0010*\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00052\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002\u00a2\u0006\u0004\b*\u0010+J\u000f\u0010,\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b,\u0010\u0004R0\u0010/\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0-j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f`.8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u00100R0\u00103\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u001101j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011`28\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u00104R0\u00107\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001205j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0012`68\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010<\u001a\u00020\u00128\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u001e\u0010>\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010?\u00a8\u0006B"}, d2={"Lrtx/kimiko/utils/render/fonts/core/FontManager;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "", "name", "", "size", "Lrtx/kimiko/utils/render/fonts/core/FontStrike;", "strike", "(Ljava/lang/String;F)Lrtx/kimiko/utils/render/fonts/core/FontStrike;", "Lrtx/kimiko/utils/render/fonts/core/FontFamily;", "family", "(Ljava/lang/String;)Lrtx/kimiko/utils/render/fonts/core/FontFamily;", "", "load", "Ljava/util/function/Supplier;", "Ljava/awt/Font;", "primary", "register", "(Ljava/lang/String;Ljava/util/function/Supplier;)V", "fileName", "fallback", "fontAsset", "(Ljava/lang/String;Ljava/util/function/Supplier;)Ljava/util/function/Supplier;", "loadFont", "(Ljava/lang/String;Ljava/awt/Font;)Ljava/awt/Font;", "", "bundledFallbacks", "loadSystemFallbacks", "([Ljava/util/function/Supplier;)V", "font", "addFallback", "(Ljava/util/function/Supplier;)V", "", "codePoint", "", "lazyUnicodeFallbacks", "(I)Ljava/util/List;", "lazyFont", "(Ljava/lang/String;)Ljava/awt/Font;", "cachedFont", "(Ljava/lang/String;Ljava/util/function/Supplier;)Ljava/awt/Font;", "close", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "families", "Ljava/util/LinkedHashMap;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "unicodeFallbacks", "Ljava/util/ArrayList;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "lazyFonts", "Ljava/util/HashMap;", "rtx/kimiko/utils/render/fonts/core/FontManager$strikes$1", "strikes", "Lrtx/kimiko/utils/render/fonts/core/FontManager$strikes$1;", "systemSans", "Ljava/awt/Font;", "bundledSansFallback", "Ljava/util/function/Supplier;", "Companion", "StrikeKey", "rtx.kimiko:kimiko"})
public final class FontManager
implements AutoCloseable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final LinkedHashMap<String, FontFamily> families = new LinkedHashMap();
    @NotNull
    private final ArrayList<Supplier<Font>> unicodeFallbacks = new ArrayList();
    @NotNull
    private final HashMap<String, Font> lazyFonts = new HashMap();
    @NotNull
    private final LinkedHashMap<StrikeKey, FontStrike> strikes = new LinkedHashMap<StrikeKey, FontStrike>(){

        protected boolean removeEldestEntry(Map.Entry<StrikeKey, FontStrike> eldest) {
            Intrinsics.checkNotNullParameter(eldest, (String)"eldest");
            if (this.size() <= 96) {
                return false;
            }
            eldest.getValue().close();
            return true;
        }
    };
    @NotNull
    private final Font systemSans = new Font("SansSerif", 0, 1);
    @Nullable
    private Supplier<Font> bundledSansFallback;
    private static final int MAX_STRIKES = 96;
    @NotNull
    private static final String FONT_ROOT = "/assets/kimiko/fonts/";
    @NotNull
    private static final String DEFAULT_NAME = "default";

    public FontManager() {
        this.load();
    }

    @NotNull
    public final FontStrike strike(@Nullable String name, float size) {
        String normalizedName = FontManager.Companion.normalizeName(name);
        FontFamily family = this.family(normalizedName);
        float normalizedSize = FontManager.Companion.normalizeSize(size);
        float density = Render2DCoordinateSpace.fontDensity();
        StrikeKey key = new StrikeKey(family.name(), normalizedSize, density);
        FontStrike fontStrike = this.strikes.computeIfAbsent(key, k -> new FontStrike(family, normalizedSize, density));
        Intrinsics.checkNotNullExpressionValue((Object)fontStrike, (String)"computeIfAbsent(...)");
        return fontStrike;
    }

    @NotNull
    public final FontFamily family(@Nullable String name) {
        FontFamily family = this.families.get(FontManager.Companion.normalizeName(name));
        if (family != null) {
            return family;
        }
        FontFamily fontFamily = this.families.get(DEFAULT_NAME);
        Intrinsics.checkNotNull((Object)fontFamily);
        return fontFamily;
    }

    private final void load() {
        Supplier<Font> segoe = this.fontAsset("SegoeProDisplay-Semibold.ttf", () -> FontManager.load$lambda$0(this));
        Supplier<Font> semiBold = this.fontAsset("semi_bold.otf", segoe);
        Supplier<Font> openSansBold = this.fontAsset("open-sans.bold.ttf", segoe);
        Supplier<Font> openSansSemiBold = this.fontAsset("open-sans.semibold.ttf", openSansBold);
        Supplier<Font> bold = this.fontAsset("bold.otf", openSansBold);
        Supplier<Font> medium = this.fontAsset("medium.otf", semiBold);
        Supplier<Font> zenter = this.fontAsset("ZenterSPDemo-Black.otf", segoe);
        Supplier<Font> icons = this.fontAsset("icons.ttf", segoe);
        Supplier<Font> notoSans = this.fontAsset("NotoSans-Regular.ttf", segoe);
        Supplier<Font> notoSymbols = this.fontAsset("NotoSansSymbols-Regular.ttf", segoe);
        Supplier<Font> notoSymbols2 = this.fontAsset("NotoSansSymbols2-Regular.ttf", segoe);
        Supplier<Font> notoArabic = this.fontAsset("NotoNaskhArabic-Regular.ttf", segoe);
        this.bundledSansFallback = notoSans;
        Supplier[] supplierArray = new Supplier[]{icons, notoSymbols, notoSymbols2, notoArabic, segoe, notoSans};
        this.loadSystemFallbacks(supplierArray);
        this.register(DEFAULT_NAME, segoe);
        this.register("segoe", segoe);
        this.register("segoe_semibold", segoe);
        this.register("semi_bold", semiBold);
        this.register("semibold", semiBold);
        this.register("open_sans_bold", openSansBold);
        this.register("opensans_bold", openSansBold);
        this.register("open_sans_semibold", openSansSemiBold);
        this.register("opensans_semibold", openSansSemiBold);
        this.register("open_sans", openSansSemiBold);
        this.register("opensans", openSansSemiBold);
        this.register("bold", bold);
        this.register("bold_otf", bold);
        this.register("medium", medium);
        this.register("medium_otf", medium);
        this.register("zenter", zenter);
        this.register("zenter_black", zenter);
        this.register("noto", notoSans);
        this.register("noto_sans", notoSans);
        this.register("noto_symbols", notoSymbols);
        this.register("noto_symbols_1", notoSymbols);
        this.register("noto_symbols_2", notoSymbols2);
        this.register("noto_symbols2", notoSymbols2);
        this.register("noto_arabic", notoArabic);
        this.register("arabic", notoArabic);
        this.register("noto_cjk_sc", notoSans);
        this.register("noto_chinese", notoSans);
        this.register("chinese", notoSans);
        this.register("noto_cjk_kr", notoSans);
        this.register("noto_korean", notoSans);
        this.register("korean", notoSans);
        this.register("noto_cjk_jp", notoSans);
        this.register("noto_japanese", notoSans);
        this.register("japanese", notoSans);
        this.register("icons", icons);
        this.register("icon", icons);
    }

    private final void register(String name, Supplier<Font> primary) {
        ((Map)this.families).put(FontManager.Companion.normalizeName(name), new FontFamily(FontManager.Companion.normalizeName(name), primary, (List<? extends Supplier<Font>>)this.unicodeFallbacks, arg_0 -> FontManager.register$lambda$0(this, arg_0)));
    }

    private final Supplier<Font> fontAsset(String fileName, Supplier<Font> fallback) {
        return () -> FontManager.fontAsset$lambda$0(this, fileName, fallback);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final Font loadFont(String fileName, Font fallback) {
        try {
            Closeable closeable = FontManager.class.getResourceAsStream(FONT_ROOT + fileName);
            Throwable throwable = null;
            try {
                InputStream input = (InputStream)closeable;
                boolean bl = false;
                if (input == null) {
                    Font font = fallback;
                    return font;
                }
                Font font = Font.createFont(0, input).deriveFont(0, 1.0f);
                Intrinsics.checkNotNullExpressionValue((Object)font, (String)"deriveFont(...)");
                Font font2 = font;
                return font2;
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
            }
        }
        catch (Exception exception) {
            return fallback;
        }
    }

    private final void loadSystemFallbacks(Supplier<Font> ... bundledFallbacks) {
        this.unicodeFallbacks.clear();
        for (Supplier<Font> font : bundledFallbacks) {
            this.addFallback(font);
        }
        this.addFallback(() -> FontManager.loadSystemFallbacks$lambda$0(this));
    }

    private final void addFallback(Supplier<Font> font) {
        if (font == null) {
            return;
        }
        this.unicodeFallbacks.add(font);
    }

    private final List<Font> lazyUnicodeFallbacks(int codePoint) {
        if (FontManager.Companion.isHangul(codePoint)) {
            List<Font> list = Collections.singletonList(this.lazyFont("NotoSansCJKkr-Regular.otf"));
            Intrinsics.checkNotNullExpressionValue(list, (String)"singletonList(...)");
            return list;
        }
        if (FontManager.Companion.isJapanese(codePoint)) {
            List<Font> list = Collections.singletonList(this.lazyFont("NotoSansCJKjp-Regular.otf"));
            Intrinsics.checkNotNullExpressionValue(list, (String)"singletonList(...)");
            return list;
        }
        if (FontManager.Companion.isCjk(codePoint)) {
            List<Font> list = Collections.singletonList(this.lazyFont("NotoSansCJKsc-Regular.otf"));
            Intrinsics.checkNotNullExpressionValue(list, (String)"singletonList(...)");
            return list;
        }
        return CollectionsKt.emptyList();
    }

    private final Font lazyFont(String fileName) {
        return this.cachedFont(fileName, () -> FontManager.lazyFont$lambda$0(this));
    }

    private final synchronized Font cachedFont(String fileName, Supplier<Font> fallback) {
        Font cached = this.lazyFonts.get(fileName);
        if (cached != null) {
            return cached;
        }
        Font font = fallback.get();
        Intrinsics.checkNotNullExpressionValue((Object)font, (String)"get(...)");
        Font loaded = this.loadFont(fileName, font);
        ((Map)this.lazyFonts).put(fileName, loaded);
        return loaded;
    }

    @Override
    public void close() {
        Iterator<FontStrike> iterator = this.strikes.values().iterator();
        while (iterator.hasNext()) {
            FontStrike strike = (FontStrike) (iterator.next());
            strike.close();
        }
        this.strikes.clear();
        this.families.clear();
        this.unicodeFallbacks.clear();
        this.lazyFonts.clear();
    }

    private static final FontStrike strike$lambda$0(FontFamily $family, float $normalizedSize, float $density, StrikeKey it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return new FontStrike($family, $normalizedSize, $density);
    }

    private static final FontStrike strike$lambda$1(Function1 $tmp0, Object p0) {
        return (FontStrike)$tmp0.invoke(p0);
    }

    private static final Font load$lambda$0(FontManager this$0) {
        return this$0.systemSans;
    }

    private static final List register$lambda$0(FontManager this$0, int codePoint) {
        return this$0.lazyUnicodeFallbacks(codePoint);
    }

    private static final Font fontAsset$lambda$0(FontManager this$0, String $fileName, Supplier $fallback) {
        return this$0.cachedFont($fileName, $fallback);
    }

    private static final Font loadSystemFallbacks$lambda$0(FontManager this$0) {
        return this$0.systemSans;
    }

    private static final Font lazyFont$lambda$0(FontManager this$0) {
        Supplier<Font> supplier = this$0.bundledSansFallback;
        Font font = supplier != null ? supplier.get() : null;
        if (font == null) {
            font = this$0.systemSans;
        }
        return font;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\n\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0010R\u0014\u0010\u0013\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0016\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/utils/render/fonts/core/FontManager.Companion;", "", "<init>", "()V", "", "size", "normalizeSize", "(F)F", "", "name", "normalizeName", "(Ljava/lang/String;)Ljava/lang/String;", "", "codePoint", "", "isHangul", "(I)Z", "isJapanese", "isCjk", "MAX_STRIKES", "I", "FONT_ROOT", "Ljava/lang/String;", "DEFAULT_NAME", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float normalizeSize(float size) {
            return Math.max(1.0f, Math.min(512.0f, (float)Math.round(size * 4.0f) / 4.0f));
        }

        private final String normalizeName(String name) {
            if (name == null || name.isBlank()) {
                return FontManager.DEFAULT_NAME;
            }
            return name.trim().toLowerCase(Locale.ROOT).replace('-', '_').replace(' ', '_');
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private final boolean isHangul(int codePoint) {
            boolean bl;
            boolean bl2;
            boolean bl3;
            if (4352 <= codePoint) {
                if (codePoint < 4608) {
                    return true;
                }
                bl3 = false;
            } else {
                bl3 = false;
            }
            if (bl3) return true;
            if (12592 <= codePoint) {
                if (codePoint < 12688) {
                    return true;
                }
                bl2 = false;
            } else {
                bl2 = false;
            }
            if (bl2) return true;
            if (43360 <= codePoint) {
                if (codePoint < 43392) {
                    return true;
                }
                bl = false;
            } else {
                bl = false;
            }
            if (bl) return true;
            if (44032 > codePoint) return false;
            if (codePoint >= 55216) return false;
            return true;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private final boolean isJapanese(int codePoint) {
            boolean bl;
            if (12352 <= codePoint) {
                if (codePoint < 12544) {
                    return true;
                }
                bl = false;
            } else {
                bl = false;
            }
            if (bl) return true;
            if (12784 > codePoint) return false;
            if (codePoint >= 12800) return false;
            return true;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private final boolean isCjk(int codePoint) {
            boolean bl;
            boolean bl2;
            boolean bl3;
            if (13312 <= codePoint) {
                if (codePoint < 19904) {
                    return true;
                }
                bl3 = false;
            } else {
                bl3 = false;
            }
            if (bl3) return true;
            if (19968 <= codePoint) {
                if (codePoint < 40960) {
                    return true;
                }
                bl2 = false;
            } else {
                bl2 = false;
            }
            if (bl2) return true;
            if (63744 <= codePoint) {
                if (codePoint < 64256) {
                    return true;
                }
                bl = false;
            } else {
                bl = false;
            }
            if (bl) return true;
            if (131072 > codePoint) return false;
            if (codePoint >= 195104) return false;
            return true;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\fJ.\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0015\u001a\u00020\u0014H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0011\u0010\u0017\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0017\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\fR\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001a\u001a\u0004\b\u001c\u0010\f\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/utils/render/fonts/core/FontManager$StrikeKey;", "", "", "name", "", "size", "density", "<init>", "(Ljava/lang/String;FF)V", "component1", "()Ljava/lang/String;", "component2", "()F", "component3", "copy", "(Ljava/lang/String;FF)Lrtx/kimiko/utils/render/fonts/core/FontManager$StrikeKey;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Ljava/lang/String;", "getName", "F", "getSize", "getDensity", "rtx.kimiko:kimiko"})
    private static final class StrikeKey {
        @NotNull
        private final String name;
        private final float size;
        private final float density;

        public StrikeKey(@NotNull String name, float size, float density) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            this.name = name;
            this.size = size;
            this.density = density;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        public final float getSize() {
            return this.size;
        }

        public final float getDensity() {
            return this.density;
        }

        @NotNull
        public final String component1() {
            return this.name;
        }

        public final float component2() {
            return this.size;
        }

        public final float component3() {
            return this.density;
        }

        @NotNull
        public final StrikeKey copy(@NotNull String name, float size, float density) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            return new StrikeKey(name, size, density);
        }

        public static /* synthetic */ StrikeKey copy$default(StrikeKey strikeKey, String string, float f, float f2, int n, Object object) {
            if ((n & 1) != 0) {
                string = strikeKey.name;
            }
            if ((n & 2) != 0) {
                f = strikeKey.size;
            }
            if ((n & 4) != 0) {
                f2 = strikeKey.density;
            }
            return strikeKey.copy(string, f, f2);
        }

        @NotNull
        public String toString() {
            return "StrikeKey(name=" + this.name + ", size=" + this.size + ", density=" + this.density + ")";
        }

        public int hashCode() {
            int result = this.name.hashCode();
            result = result * 31 + Float.hashCode(this.size);
            result = result * 31 + Float.hashCode(this.density);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StrikeKey)) {
                return false;
            }
            StrikeKey strikeKey = (StrikeKey)other;
            if (!Intrinsics.areEqual((Object)this.name, (Object)strikeKey.name)) {
                return false;
            }
            if (Float.compare(this.size, strikeKey.size) != 0) {
                return false;
            }
            return Float.compare(this.density, strikeKey.density) == 0;
        }
    }
}

