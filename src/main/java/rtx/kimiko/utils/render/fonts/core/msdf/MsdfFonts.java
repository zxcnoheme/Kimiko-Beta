/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package rtx.kimiko.utils.render.fonts.core.msdf;

import java.util.EnumMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfAtlasPack;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfFont;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfFontLoader;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0013\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\u0003R\u001c\u0010\u000e\u001a\n \r*\u0004\u0018\u00010\f0\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R \u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R \u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00100\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0015\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFonts;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/fonts/Fonts;", "font", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont;", "Lkotlin/jvm/JvmStatic;", "get", "(Lrtx/kimiko/utils/render/fonts/Fonts;)Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont;", "", "clear", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "LOGGER", "Lorg/slf4j/Logger;", "", "RETRY_MS", "J", "", "CACHE", "Ljava/util/Map;", "FAILED", "rtx.kimiko:kimiko"})
public final class MsdfFonts {
    @NotNull
    public static final MsdfFonts INSTANCE = new MsdfFonts();
    private static final Logger LOGGER = LoggerFactory.getLogger((String)"Kimiko/MSDF");
    private static final long RETRY_MS = 3000L;
    @NotNull
    private static final Map<Fonts, MsdfFont> CACHE = new EnumMap(Fonts.class);
    @NotNull
    private static final Map<Fonts, Long> FAILED = new EnumMap(Fonts.class);

    private MsdfFonts() {
    }

    @JvmStatic
    @Nullable
    public static final MsdfFont get(@Nullable Fonts font) {
        MsdfFont loaded;
        MsdfFont cached;
        Fonts key;
        Fonts fonts = font;
        if (fonts == null) {
            fonts = Fonts.DEFAULT;
        }
        if (!(key = fonts).vector()) {
            return key == Fonts.DEFAULT ? null : MsdfFonts.get(Fonts.DEFAULT);
        }
        MsdfFont msdfFont = cached = CACHE.get((Object)key);
        if (msdfFont != null) {
            return msdfFont;
        }
        Long failedAt = FAILED.get((Object)key);
        if (failedAt != null) {
            if (System.currentTimeMillis() - failedAt < 3000L) {
                return key == Fonts.DEFAULT ? null : MsdfFonts.get(Fonts.DEFAULT);
            }
            FAILED.remove((Object)key);
        }
        if ((loaded = MsdfFontLoader.load(key.atlas())) == null) {
            FAILED.put(key, System.currentTimeMillis());
            Object[] objectArray = new Object[]{key.id(), Fonts.DEFAULT.id(), 3000L};
            LOGGER.warn("[MSDF] Font '{}' failed to load, falling back to '{}' (retry in {} ms)", objectArray);
            return key == Fonts.DEFAULT ? null : MsdfFonts.get(Fonts.DEFAULT);
        }
        CACHE.put(key, loaded);
        return loaded;
    }

    @JvmStatic
    public static final void clear() {
        CACHE.clear();
        FAILED.clear();
        MsdfAtlasPack.invalidate();
    }
}

