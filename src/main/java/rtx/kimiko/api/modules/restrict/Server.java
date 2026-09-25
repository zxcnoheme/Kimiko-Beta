package rtx.kimiko.api.modules.restrict;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import sigil.protect.Level;
import sigil.protect.Protect;

@Protect(value=Level.OFF)
public enum Server {
    FT("FunTime", new String[]{"funtime"}),
    RW("ReallyWorld", new String[]{"reallyworld"}),
    HW("HolyWorld", new String[]{"holyworld", "hollyworld", "playhw"}),
    SATURN("Saturn-X", new String[]{"saturn-x", "saturnx"}),
    ST("Space Times", new String[]{"space-times", "spacetimes"}),
    WM("WellMine", new String[]{"wellmine"});

    @NotNull
    private final String display;
    @NotNull
    private final String[] keywords;
    private Server(String display, String ... keywords) {
        this.display = display;
        this.keywords = keywords;
    }

    @NotNull
    public final String display() {
        return this.display;
    }

    public final boolean matches(@NotNull String ipLower, @NotNull String ipNorm, @NotNull String brandNorm) {
        Intrinsics.checkNotNullParameter((Object)ipLower, (String)"ipLower");
        Intrinsics.checkNotNullParameter((Object)ipNorm, (String)"ipNorm");
        Intrinsics.checkNotNullParameter((Object)brandNorm, (String)"brandNorm");
        for (String kw : this.keywords) {
            if (((CharSequence)ipLower).length() > 0 && String.valueOf(ipLower).contains(kw)) {
                return true;
            }
            if (((CharSequence)ipNorm).length() > 0 && String.valueOf(ipNorm).contains(kw)) {
                return true;
            }
            if (!(((CharSequence)brandNorm).length() > 0) || !String.valueOf(brandNorm).contains(kw)) continue;
            return true;
        }
        return false;
    }

    @NotNull
    public static EnumEntries<Server> getEntries() {
        return EnumEntriesKt.enumEntries(values());
    }

    
}
