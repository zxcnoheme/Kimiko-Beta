/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.text.Text
 *  net.minecraft.text.Style
 *  net.minecraft.text.MutableText
 *  net.minecraft.text.TextContent
 *  net.minecraft.text.PlainTextContent.Literal
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.modules.rank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.text.Text;
import net.minecraft.text.Style;
import net.minecraft.text.MutableText;
import net.minecraft.text.TextContent;
import net.minecraft.text.PlainTextContent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.network.Network;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0018\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ)\u0010\f\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u000e\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0015\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ'\u0010!\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b#\u0010$J\u001f\u0010'\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010&\u001a\u00020%H\u0002\u00a2\u0006\u0004\b'\u0010(J\u001f\u0010,\u001a\u00020+2\u0006\u0010)\u001a\u00020\u00172\u0006\u0010*\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b,\u0010-J\u001f\u0010\u000b\u001a\u00020+2\u0006\u0010*\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000b\u0010.R0\u00101\u001a\u001e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00050/j\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0005`08\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b1\u00102R0\u00103\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050/j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`08\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u00102R0\u00104\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00170/j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0017`08\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00102R$\u00107\u001a\u0012\u0012\u0004\u0012\u00020\u001705j\b\u0012\u0004\u0012\u00020\u0017`68\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00108R$\u0010;\u001a\u0012\u0012\u0004\u0012\u00020\u000509j\b\u0012\u0004\u0012\u00020\u0005`:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u0010<\u00a8\u0006="}, d2={"Lrtx/kimiko/utils/render/modules/rank/ReallyWorldRanks;", "", "<init>", "()V", "", "", "Lkotlin/jvm/JvmStatic;", "orderedLabels", "()Ljava/util/List;", "Lnet/minecraft/Text;", "name", "label", "applySelfRank", "(Lnet/minecraft/Text;Ljava/lang/String;)Lnet/minecraft/Text;", "stripGlyphs", "(Lnet/minecraft/Text;)Lnet/minecraft/Text;", "text", "", "containsGlyph", "(Ljava/lang/String;)Z", "nick", "glyphPrecedesNick", "(Ljava/lang/String;Ljava/lang/String;)Z", "", "c", "isNameChar", "(C)Z", "glyphReplacement", "Lnet/minecraft/MutableText;", "walk", "(Lnet/minecraft/Text;Ljava/lang/String;)Lnet/minecraft/MutableText;", "Lnet/minecraft/Style;", "style", "rewriteLiteral", "(Ljava/lang/String;Lnet/minecraft/Style;Ljava/lang/String;)Lnet/minecraft/MutableText;", "trimLeading", "(Lnet/minecraft/Text;)Lnet/minecraft/MutableText;", "", "trimming", "trimWalk", "(Lnet/minecraft/Text;[Z)Lnet/minecraft/MutableText;", "glyph", "key", "", "rank", "(CLjava/lang/String;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "KEYS", "Ljava/util/LinkedHashMap;", "LABELS", "LABEL_TO_GLYPH", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "GLYPHS", "Ljava/util/HashSet;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "LABEL_ORDER", "Ljava/util/ArrayList;", "rtx.kimiko:kimiko"})
public final class ReallyWorldRanks {
    @NotNull
    public static final ReallyWorldRanks INSTANCE = new ReallyWorldRanks();
    @NotNull
    private static final LinkedHashMap<Character, String> KEYS = new LinkedHashMap();
    @NotNull
    private static final LinkedHashMap<String, String> LABELS = new LinkedHashMap();
    @NotNull
    private static final LinkedHashMap<String, Character> LABEL_TO_GLYPH = new LinkedHashMap();
    @NotNull
    private static final HashSet<Character> GLYPHS = new HashSet();
    @NotNull
    private static final ArrayList<String> LABEL_ORDER = new ArrayList();

    private ReallyWorldRanks() {
    }

    @JvmStatic
    @NotNull
    public static final List<String> orderedLabels() {
        return LABEL_ORDER;
    }

    @JvmStatic
    @Nullable
    public static final Text applySelfRank(@Nullable Text name, @Nullable String label) {
        Character glyph;
        block5: {
            block4: {
                if (name == null || label == null || !Network.isReallyWorld()) {
                    return name;
                }
                glyph = LABEL_TO_GLYPH.get(label);
                if (glyph == null) break block4;
                String string = name.getString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getString(...)");
                if (INSTANCE.containsGlyph(string)) break block5;
            }
            return name;
        }
        return (Text)INSTANCE.walk(name, String.valueOf(glyph.charValue()));
    }

    @JvmStatic
    @Nullable
    public static final Text stripGlyphs(@Nullable Text name) {
        block3: {
            block2: {
                if (name == null || GLYPHS.isEmpty()) break block2;
                String string = name.getString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getString(...)");
                if (INSTANCE.containsGlyph(string)) break block3;
            }
            return name;
        }
        return (Text)INSTANCE.trimLeading((Text)INSTANCE.walk(name, ""));
    }

    private final boolean containsGlyph(String text) {
        int n = ((CharSequence)text).length();
        for (int i = 0; i < n; ++i) {
            if (!GLYPHS.contains(Character.valueOf(text.charAt(i)))) continue;
            return true;
        }
        return false;
    }

    @JvmStatic
    public static final boolean glyphPrecedesNick(@Nullable String text, @Nullable String nick) {
        CharSequence charSequence;
        if (text == null || (charSequence = (CharSequence)nick) == null || charSequence.length() == 0 || GLYPHS.isEmpty()) {
            return false;
        }
        int n = ((CharSequence)text).length();
        for (int i = 0; i < n; ++i) {
            int after;
            int j;
            if (!GLYPHS.contains(Character.valueOf(text.charAt(i)))) continue;
            for (j = i + 1; j < text.length() && Character.isWhitespace(text.charAt(j)); ++j) {
            }
            if (!StringsKt.regionMatches((String)text, (int)j, (String)nick, (int)0, (int)nick.length(), (boolean)true) || (after = j + nick.length()) < text.length() && INSTANCE.isNameChar(text.charAt(after))) continue;
            return true;
        }
        return false;
    }

    private final boolean isNameChar(char c) {
        return Character.isLetterOrDigit(c) || c == '_';
    }

    private final MutableText walk(Text text, String glyphReplacement) {
        MutableText mutableText2;
        TextContent textContent2 = text.getContent();
        Intrinsics.checkNotNullExpressionValue((Object)textContent2, (String)"getContents(...)");
        TextContent contents = textContent2;
        if (contents instanceof PlainTextContent.Literal) {
            String string = ((PlainTextContent.Literal)contents).string();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"text(...)");
            Style style2 = text.getStyle();
            Intrinsics.checkNotNullExpressionValue((Object)style2, (String)"getStyle(...)");
            mutableText2 = this.rewriteLiteral(string, style2, glyphReplacement);
        } else {
            MutableText mutableText3 = text.copyContentOnly();
            Intrinsics.checkNotNull((Object)mutableText3);
            mutableText2 = mutableText3;
        }
        MutableText result = mutableText2;
        for (Object e : text.getSiblings()) {
            Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
            Text sibling = (Text)e;
            result.append((Text)this.walk(sibling, glyphReplacement));
        }
        return result;
    }

    private final MutableText rewriteLiteral(String text, Style style, String glyphReplacement) {
        StringBuilder buffer = new StringBuilder(text.length());
        int n = ((CharSequence)text).length();
        for (int i = 0; i < n; ++i) {
            char c = text.charAt(i);
            StringBuilder stringBuilder = GLYPHS.contains(Character.valueOf(c)) ? buffer.append(glyphReplacement) : buffer.append(c);
        }
        MutableText mutableText2 = Text.literal((String)buffer.toString()).setStyle(style);
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"setStyle(...)");
        return mutableText2;
    }

    private final MutableText trimLeading(Text text) {
        boolean[] blArray = new boolean[]{true};
        return this.trimWalk(text, blArray);
    }

    private final MutableText trimWalk(Text text, boolean[] trimming) {
        TextContent textContent2 = text.getContent();
        Intrinsics.checkNotNullExpressionValue((Object)textContent2, (String)"getContents(...)");
        TextContent contents = textContent2;
        MutableText result = null;
        if (contents instanceof PlainTextContent.Literal) {
            String string = ((PlainTextContent.Literal)contents).string();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"text(...)");
            String s = string;
            if (trimming[0] && ((CharSequence)s).length() > 0) {
                int i;
                for (i = 0; i < s.length() && Character.isWhitespace(s.charAt(i)); ++i) {
                }
                String string2 = s.substring(i);
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
                s = string2;
                if (((CharSequence)s).length() > 0) {
                    trimming[0] = false;
                }
            }
            MutableText mutableText2 = Text.literal((String)s).setStyle(text.getStyle());
            Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"setStyle(...)");
            result = mutableText2;
        } else {
            MutableText mutableText3 = text.copyContentOnly();
            Intrinsics.checkNotNullExpressionValue((Object)mutableText3, (String)"plainCopy(...)");
            result = mutableText3;
            trimming[0] = false;
        }
        for (Object e : text.getSiblings()) {
            Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
            Text sibling = (Text)e;
            result.append((Text)this.trimWalk(sibling, trimming));
        }
        return result;
    }

    private final void rank(char glyph, String key) {
        ((Map)KEYS).put(Character.valueOf(glyph), key);
    }

    private final void label(String key, String label) {
        ((Map)LABELS).put(key, label);
    }

    static {
        INSTANCE.rank('\ua500', "player");
        INSTANCE.rank('\ua504', "hero");
        INSTANCE.rank('\ua508', "titan");
        INSTANCE.rank('\ua512', "avenger");
        INSTANCE.rank('\ua516', "overlord");
        INSTANCE.rank('\ua520', "magister");
        INSTANCE.rank('\ua524', "imperator");
        INSTANCE.rank('\ua528', "dragon");
        INSTANCE.rank('\ua532', "bull");
        INSTANCE.rank('\ua552', "rabbit");
        INSTANCE.rank('\ua536', "tiger");
        INSTANCE.rank('\ua544', "dracula");
        INSTANCE.rank('\ua556', "bunny");
        INSTANCE.rank('\ua540', "hydra");
        INSTANCE.rank('\ua541', "god");
        INSTANCE.rank('\ua548', "cobra");
        INSTANCE.rank('\ua545', "vampire");
        INSTANCE.rank('\ua549', "pegas");
        INSTANCE.rank('\ua501', "media");
        INSTANCE.rank('\ua505', "yt");
        INSTANCE.rank('\ua560', "d.helper");
        INSTANCE.rank('\ua509', "helper");
        INSTANCE.rank('\ua513', "ml.moder");
        INSTANCE.rank('\ua517', "moder");
        INSTANCE.rank('\ua521', "moder+");
        INSTANCE.rank('\ua525', "st.moder");
        INSTANCE.rank('\ua529', "gl.moder");
        INSTANCE.rank('\ua533', "ml.admin");
        INSTANCE.rank('\ua537', "admin");
        INSTANCE.label("player", "Player");
        INSTANCE.label("hero", "Hero");
        INSTANCE.label("titan", "Titan");
        INSTANCE.label("avenger", "Avenger");
        INSTANCE.label("overlord", "Overlord");
        INSTANCE.label("magister", "Magister");
        INSTANCE.label("imperator", "Imperator");
        INSTANCE.label("dragon", "Dragon");
        INSTANCE.label("bull", "Bull");
        INSTANCE.label("rabbit", "Rabbit");
        INSTANCE.label("tiger", "Tiger");
        INSTANCE.label("dracula", "Dracula");
        INSTANCE.label("bunny", "Bunny");
        INSTANCE.label("hydra", "Hydra");
        INSTANCE.label("god", "GOD");
        INSTANCE.label("cobra", "Cobra");
        INSTANCE.label("vampire", "Vampire");
        INSTANCE.label("pegas", "Pegas");
        INSTANCE.label("media", "Media");
        INSTANCE.label("yt", "YT");
        INSTANCE.label("d.helper", "D.Helper");
        INSTANCE.label("helper", "Helper");
        INSTANCE.label("ml.moder", "Ml.Moder");
        INSTANCE.label("moder", "Moder");
        INSTANCE.label("moder+", "Moder+");
        INSTANCE.label("st.moder", "St.Moder");
        INSTANCE.label("gl.moder", "Gl.Moder");
        INSTANCE.label("ml.admin", "Ml.Admin");
        INSTANCE.label("admin", "Admin");
        for (Map.Entry<Character, String> entry : KEYS.entrySet()) {
            char glyph = entry.getKey();
            String key = entry.getValue();
            String label = LABELS.get(key);
            if (label == null) continue;
            GLYPHS.add(glyph);
            if (LABEL_TO_GLYPH.containsKey(label)) continue;
            LABEL_TO_GLYPH.put(label, glyph);
            LABEL_ORDER.add(label);
        }
    }
}

