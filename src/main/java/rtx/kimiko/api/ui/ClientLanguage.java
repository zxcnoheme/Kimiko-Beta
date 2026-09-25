/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.fabricmc.loader.api.FabricLoader
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.lang.I18n;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0015\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J\u0013\u0010\t\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0007J\u0013\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\fJ\u0013\u0010\u000f\u001a\u00020\u000eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0015\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0015\u0010\u0014J\u001b\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u000eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0019\u0010\u0019\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0019\u0010\u001b\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u0010R\u0014\u0010\u001d\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001eR\u0014\u0010 \u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b \u0010\u001eR\u0014\u0010!\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b!\u0010\u001eR\u0014\u0010\"\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\"\u0010\u001eR\u0014\u0010#\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b#\u0010\u001eR\u0014\u0010$\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b$\u0010\u001eR\u0014\u0010%\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b%\u0010\u001eR\u0014\u0010&\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b&\u0010\u001eR\u0014\u0010'\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b'\u0010\u001eR\u001f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00040(8\u0006X\u0087\u0004\u0092\u0002\u0002\b)\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u001f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040(8\u0006X\u0087\u0004\u0092\u0002\u0002\b)\u00a2\u0006\u0006\n\u0004\b,\u0010+R\u001f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00040(8\u0006X\u0087\u0004\u0092\u0002\u0002\b)\u00a2\u0006\u0006\n\u0004\b-\u0010+R\u0014\u0010/\u001a\u00020.8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0016\u00101\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u00102\u00a8\u00063"}, d2={"Lrtx/kimiko/api/ui/ClientLanguage;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "get", "()Ljava/lang/String;", "code", "short", "", "isRussian", "()Z", "isEnglish", "", "index", "()I", "value", "", "set", "(Ljava/lang/String;)V", "setCode", "next", "setIndex", "(I)V", "indexOf", "(Ljava/lang/String;)I", "indexOfCode", "load", "RUSSIAN", "Ljava/lang/String;", "UKRAINIAN", "ENGLISH", "GERMAN", "POLISH", "CODE_RU", "CODE_UK", "CODE_EN", "CODE_DE", "CODE_PL", "", "Lkotlin/jvm/JvmField;", "OPTIONS", "[Ljava/lang/String;", "SHORT_OPTIONS", "CODES", "Ljava/nio/file/Path;", "FILE", "Ljava/nio/file/Path;", "currentIndex", "I", "rtx.kimiko:kimiko"})
public final class ClientLanguage {
    @NotNull
    public static final ClientLanguage INSTANCE = new ClientLanguage();
    @NotNull
    public static final String RUSSIAN = "Русский";
    @NotNull
    public static final String UKRAINIAN = "Українська";
    @NotNull
    public static final String ENGLISH = "English";
    @NotNull
    public static final String GERMAN = "Deutsch";
    @NotNull
    public static final String POLISH = "Polski";
    @NotNull
    public static final String CODE_RU = "ru";
    @NotNull
    public static final String CODE_UK = "uk";
    @NotNull
    public static final String CODE_EN = "en";
    @NotNull
    public static final String CODE_DE = "de";
    @NotNull
    public static final String CODE_PL = "pl";
    @JvmField
    @NotNull
    public static final String[] OPTIONS;
    @JvmField
    @NotNull
    public static final String[] SHORT_OPTIONS;
    @JvmField
    @NotNull
    public static final String[] CODES;
    @NotNull
    private static final Path FILE;
    private static volatile int currentIndex;

    private ClientLanguage() {
    }

    @JvmStatic
    @NotNull
    public static final String get() {
        return OPTIONS[currentIndex];
    }

    @JvmStatic
    @NotNull
    public static final String code() {
        return CODES[currentIndex];
    }

    @JvmStatic
    @NotNull
    public static final String shortName() {
        return SHORT_OPTIONS[currentIndex];
    }

    @JvmStatic
    public static final boolean isRussian() {
        return currentIndex == 0;
    }

    @JvmStatic
    public static final boolean isEnglish() {
        return currentIndex == 2;
    }

    @JvmStatic
    public static final int index() {
        return currentIndex;
    }

    @JvmStatic
    public static final void set(@Nullable String value) {
        ClientLanguage.setIndex(INSTANCE.indexOf(value));
    }

    @JvmStatic
    public static final void setCode(@Nullable String value) {
        ClientLanguage.setIndex(INSTANCE.indexOfCode(value));
    }

    @JvmStatic
    public static final void setIndex(int next) {
        if (next < 0 || next >= OPTIONS.length || next == currentIndex) {
            return;
        }
        currentIndex = next;
        I18n.invalidate();
        try {
            Files.createDirectories(FILE.getParent(), new FileAttribute[0]);
            Files.writeString(FILE, (CharSequence)CODES[next], StandardCharsets.UTF_8, new OpenOption[0]);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private final int indexOf(String value) {
        if (value == null) {
            return -1;
        }
        String trimmed = ((Object)StringsKt.trim((CharSequence)value)).toString();
        int n = OPTIONS.length;
        for (int i = 0; i < n; ++i) {
            if (!StringsKt.equals((String)OPTIONS[i], (String)trimmed, (boolean)true)) continue;
            return i;
        }
        return this.indexOfCode(trimmed);
    }

    private final int indexOfCode(String value) {
        if (value == null) {
            return -1;
        }
        String string = ((Object)StringsKt.trim((CharSequence)value)).toString();
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string2 = string.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
        String trimmed = string2;
        if (((CharSequence)trimmed).length() == 0) {
            return -1;
        }
        int n = CODES.length;
        for (int i = 0; i < n; ++i) {
            if (!Intrinsics.areEqual((Object)CODES[i], (Object)trimmed)) {
                String string3 = SHORT_OPTIONS[i];
                Locale locale2 = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue((Object)locale2, (String)"ROOT");
                String string4 = string3.toLowerCase(locale2);
                Intrinsics.checkNotNullExpressionValue((Object)string4, (String)"toLowerCase(...)");
                if (!Intrinsics.areEqual((Object)string4, (Object)trimmed)) continue;
            }
            return i;
        }
        return switch (trimmed) {
            case "russian", "русский" -> 0;
            case "ukrainian", "українська", "украинский" -> 1;
            case "english" -> 2;
            case "german", "deutsch" -> 3;
            case "polski", "polish" -> 4;
            default -> -1;
        };
    }

    private final int load() {
        try {
            if (Files.exists(FILE, new LinkOption[0])) {
                String string = Files.readString(FILE, StandardCharsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"readString(...)");
                String raw = ((Object)StringsKt.trim((CharSequence)string)).toString();
                int found = this.indexOf(raw);
                if (found >= 0) {
                    return found;
                }
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return 0;
    }

    static {
        String[] stringArray = new String[]{RUSSIAN, UKRAINIAN, ENGLISH, GERMAN, POLISH};
        OPTIONS = stringArray;
        stringArray = new String[]{"RU", "UA", "EN", "DE", "PL"};
        SHORT_OPTIONS = stringArray;
        stringArray = new String[]{CODE_RU, CODE_UK, CODE_EN, CODE_DE, CODE_PL};
        CODES = stringArray;
        Path path = FabricLoader.getInstance().getGameDir().resolve("kimiko").resolve("language.txt");
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
        FILE = path;
        currentIndex = INSTANCE.load();
    }
}

