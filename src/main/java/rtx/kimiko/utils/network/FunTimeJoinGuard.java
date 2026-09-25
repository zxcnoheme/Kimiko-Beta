/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.client.network.ServerAddress
 *  net.minecraft.client.network.ServerInfo
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.network;

import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u001b\u001cB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J/\u0010\u000b\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000b\u0010\fJ1\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0019\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/utils/network/FunTimeJoinGuard;", "", "<init>", "()V", "Lnet/minecraft/ServerAddress;", "serverAddress", "Lnet/minecraft/ServerInfo;", "serverData", "", "transfer", "Lkotlin/jvm/JvmStatic;", "matches", "(Lnet/minecraft/ServerAddress;Lnet/minecraft/ServerInfo;Z)Z", "Lrtx/kimiko/utils/network/FunTimeJoinGuard$Match;", "matched", "(Lnet/minecraft/ServerAddress;Lnet/minecraft/ServerInfo;Z)Lrtx/kimiko/utils/network/FunTimeJoinGuard$Match;", "", "value", "Lrtx/kimiko/utils/network/FunTimeJoinGuard$Guarded;", "guardedOf", "(Ljava/lang/String;)Lrtx/kimiko/utils/network/FunTimeJoinGuard$Guarded;", "", "GUARDED", "Ljava/util/List;", "Lkotlin/jvm/JvmField;", "bypass", "Z", "Match", "Guarded", "rtx.kimiko:kimiko"})
public final class FunTimeJoinGuard {
    @NotNull
    public static final FunTimeJoinGuard INSTANCE = new FunTimeJoinGuard();
    @NotNull
    private static final List<Guarded> GUARDED;
    @JvmField
    public static volatile boolean bypass;

    private FunTimeJoinGuard() {
    }

    @JvmStatic
    public static final boolean matches(@Nullable ServerAddress serverAddress, @Nullable ServerInfo serverData, boolean transfer) {
        return FunTimeJoinGuard.matched(serverAddress, serverData, transfer) != null;
    }

    @JvmStatic
    @Nullable
    public static final Match matched(@Nullable ServerAddress serverAddress, @Nullable ServerInfo serverData, boolean transfer) {
        try {
            Guarded guarded;
            if (serverAddress != null && (guarded = INSTANCE.guardedOf(serverAddress.getAddress())) != null) {
                String string = guarded.getDisplay();
                String string2 = serverAddress.getAddress();
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getHost(...)");
                return new Match(string, ((Object)StringsKt.trim((CharSequence)string2)).toString());
            }
            if (!transfer && serverData != null && (guarded = INSTANCE.guardedOf(serverData.address)) != null) {
                String string = guarded.getDisplay();
                String string3 = serverData.address;
                Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"ip");
                return new Match(string, ((Object)StringsKt.trim((CharSequence)string3)).toString());
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return null;
    }

    private final Guarded guardedOf(String value) {
        if (value == null || StringsKt.isBlank((CharSequence)value)) {
            return null;
        }
        String lower = value.toLowerCase(Locale.ROOT);
        StringBuilder normalized = new StringBuilder(lower.length());
        int len = lower.length();
        for (int i = 0; i <= len; ++i) {
            char c = i < len ? lower.charAt(i) : '.';
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
                normalized.append(c);
            } else if (c == '.' && normalized.length() > 0) {
                String label = normalized.toString();
                for (Guarded guarded : FunTimeJoinGuard.GUARDED) {
                    if (String.valueOf(label).contains(guarded.getKeyword())) {
                        return guarded;
                    }
                }
                normalized.setLength(0);
            }
        }
        return null;
    }

    static {
        GUARDED = List.of(new Guarded("FunTime", "funtime"), new Guarded("ReallyWorld", "reallyworld"));
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\bJ$\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0011\u001a\u00020\u0010H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0013\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0014\u001a\u0004\b\u0016\u0010\b\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/utils/network/FunTimeJoinGuard$Guarded;", "", "", "display", "keyword", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "component1", "()Ljava/lang/String;", "component2", "copy", "(Ljava/lang/String;Ljava/lang/String;)Lrtx/kimiko/utils/network/FunTimeJoinGuard$Guarded;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Ljava/lang/String;", "getDisplay", "getKeyword", "rtx.kimiko:kimiko"})
    private static final class Guarded {
        @NotNull
        private final String display;
        @NotNull
        private final String keyword;

        public Guarded(@NotNull String display, @NotNull String keyword) {
            Intrinsics.checkNotNullParameter((Object)display, (String)"display");
            Intrinsics.checkNotNullParameter((Object)keyword, (String)"keyword");
            this.display = display;
            this.keyword = keyword;
        }

        @NotNull
        public final String getDisplay() {
            return this.display;
        }

        @NotNull
        public final String getKeyword() {
            return this.keyword;
        }

        @NotNull
        public final String component1() {
            return this.display;
        }

        @NotNull
        public final String component2() {
            return this.keyword;
        }

        @NotNull
        public final Guarded copy(@NotNull String display, @NotNull String keyword) {
            Intrinsics.checkNotNullParameter((Object)display, (String)"display");
            Intrinsics.checkNotNullParameter((Object)keyword, (String)"keyword");
            return new Guarded(display, keyword);
        }

        public static /* synthetic */ Guarded copy$default(Guarded guarded, String string, String string2, int n, Object object) {
            if ((n & 1) != 0) {
                string = guarded.display;
            }
            if ((n & 2) != 0) {
                string2 = guarded.keyword;
            }
            return guarded.copy(string, string2);
        }

        @NotNull
        public String toString() {
            return "Guarded(display=" + this.display + ", keyword=" + this.keyword + ")";
        }

        public int hashCode() {
            int result = this.display.hashCode();
            result = result * 31 + this.keyword.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Guarded)) {
                return false;
            }
            Guarded guarded = (Guarded)other;
            if (!Intrinsics.areEqual((Object)this.display, (Object)guarded.display)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.keyword, (Object)guarded.keyword);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\bJ$\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0011\u001a\u00020\u0010H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0013\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\bR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0003\u0010\bR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0004\u0010\b\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/utils/network/FunTimeJoinGuard$Match;", "", "", "display", "address", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "component1", "()Ljava/lang/String;", "component2", "copy", "(Ljava/lang/String;Ljava/lang/String;)Lrtx/kimiko/utils/network/FunTimeJoinGuard$Match;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Lkotlin/jvm/JvmName;", "name", "Ljava/lang/String;", "rtx.kimiko:kimiko"})
    public static final class Match {
        @NotNull
        private final String display;
        @NotNull
        private final String address;

        public Match(@NotNull String display, @NotNull String address) {
            Intrinsics.checkNotNullParameter((Object)display, (String)"display");
            Intrinsics.checkNotNullParameter((Object)address, (String)"address");
            this.display = display;
            this.address = address;
        }

        @JvmName(name="display")
        @NotNull
        public final String display() {
            return this.display;
        }

        @JvmName(name="address")
        @NotNull
        public final String address() {
            return this.address;
        }

        @NotNull
        public final String component1() {
            return this.display;
        }

        @NotNull
        public final String component2() {
            return this.address;
        }

        @NotNull
        public final Match copy(@NotNull String display, @NotNull String address) {
            Intrinsics.checkNotNullParameter((Object)display, (String)"display");
            Intrinsics.checkNotNullParameter((Object)address, (String)"address");
            return new Match(display, address);
        }

        public static /* synthetic */ Match copy$default(Match match, String string, String string2, int n, Object object) {
            if ((n & 1) != 0) {
                string = match.display;
            }
            if ((n & 2) != 0) {
                string2 = match.address;
            }
            return match.copy(string, string2);
        }

        @NotNull
        public String toString() {
            return "Match(display=" + this.display + ", address=" + this.address + ")";
        }

        public int hashCode() {
            int result = this.display.hashCode();
            result = result * 31 + this.address.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Match)) {
                return false;
            }
            Match match = (Match)other;
            if (!Intrinsics.areEqual((Object)this.display, (Object)match.display)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.address, (Object)match.address);
        }
    }
}

