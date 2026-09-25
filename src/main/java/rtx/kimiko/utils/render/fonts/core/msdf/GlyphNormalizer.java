/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.fonts.core.msdf;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\fR0\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\rj\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n`\u000e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/GlyphNormalizer;", "", "<init>", "()V", "", "codePoint", "", "Lkotlin/jvm/JvmStatic;", "isSmallCap", "(I)Z", "", "normalize", "(I)Ljava/lang/String;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "SMALL_CAPS", "Ljava/util/HashMap;", "rtx.kimiko:kimiko"})
public final class GlyphNormalizer {
    @NotNull
    public static final GlyphNormalizer INSTANCE = new GlyphNormalizer();
    @NotNull
    private static final HashMap<Integer, String> SMALL_CAPS = new HashMap();

    private GlyphNormalizer() {
    }

    @JvmStatic
    public static final boolean isSmallCap(int codePoint) {
        return SMALL_CAPS.containsKey(codePoint);
    }

    @JvmStatic
    @Nullable
    public static final String normalize(int codePoint) {
        String smallCap = SMALL_CAPS.get(codePoint);
        if (smallCap != null) {
            return smallCap;
        }
        char[] cArray = Character.toChars(codePoint);
        Intrinsics.checkNotNullExpressionValue((Object)cArray, (String)"toChars(...)");
        char[] cArray2 = cArray;
        String raw = new String(cArray2);
        String nfkc = Normalizer.normalize(raw, Normalizer.Form.NFKC);
        if (!Intrinsics.areEqual((Object)nfkc, (Object)raw)) {
            Intrinsics.checkNotNull((Object)nfkc);
            if (((CharSequence)nfkc).length() > 0) {
                return nfkc;
            }
        }
        return null;
    }

    static {
        ((Map)SMALL_CAPS).put(7424, "A");
        ((Map)SMALL_CAPS).put(665, "B");
        ((Map)SMALL_CAPS).put(7428, "C");
        ((Map)SMALL_CAPS).put(7429, "D");
        ((Map)SMALL_CAPS).put(7431, "E");
        ((Map)SMALL_CAPS).put(42800, "F");
        ((Map)SMALL_CAPS).put(610, "G");
        ((Map)SMALL_CAPS).put(668, "H");
        ((Map)SMALL_CAPS).put(618, "I");
        ((Map)SMALL_CAPS).put(7434, "J");
        ((Map)SMALL_CAPS).put(7435, "K");
        ((Map)SMALL_CAPS).put(671, "L");
        ((Map)SMALL_CAPS).put(7437, "M");
        ((Map)SMALL_CAPS).put(628, "N");
        ((Map)SMALL_CAPS).put(7439, "O");
        ((Map)SMALL_CAPS).put(7448, "P");
        ((Map)SMALL_CAPS).put(491, "Q");
        ((Map)SMALL_CAPS).put(640, "R");
        ((Map)SMALL_CAPS).put(42801, "S");
        ((Map)SMALL_CAPS).put(7451, "T");
        ((Map)SMALL_CAPS).put(7452, "U");
        ((Map)SMALL_CAPS).put(7456, "V");
        ((Map)SMALL_CAPS).put(7457, "W");
        ((Map)SMALL_CAPS).put(7521, "X");
        ((Map)SMALL_CAPS).put(655, "Y");
        ((Map)SMALL_CAPS).put(7458, "Z");
        ((Map)SMALL_CAPS).put(7462, "Г");
        ((Map)SMALL_CAPS).put(7467, "Л");
        ((Map)SMALL_CAPS).put(7464, "П");
        ((Map)SMALL_CAPS).put(7465, "Р");
        ((Map)SMALL_CAPS).put(7466, "\u03a8");
        ((Map)SMALL_CAPS).put(1171, "F");
    }
}

