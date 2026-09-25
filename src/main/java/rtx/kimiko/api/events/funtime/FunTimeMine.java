/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.events.funtime;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 \"2\u00020\u0001:\u0001\"B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\rJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\rJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\rJ\u0010\u0010\u0012\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013JL\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\bH\u00c6\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001b\u001a\u00020\u001aH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0011\u0010\u001d\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001d\u0010\rR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b\u0003\u0010\rR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010 \u001a\u0004\b\u0004\u0010\rR%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010 \u001a\u0004\b\u0005\u0010\rR%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b\u0006\u0010\rR%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010 \u001a\u0004\b\u0007\u0010\rR%\u0010\t\u001a\u00020\b8\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010!\u001a\u0004\b\t\u0010\u0013\u00a8\u0006#"}, d2={"Lrtx/kimiko/api/events/funtime/FunTimeMine;", "", "", "serverId", "serverRuName", "mineName", "rarity", "nextRarity", "", "refillAtMs", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "component6", "()J", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)Lrtx/kimiko/api/events/funtime/FunTimeMine;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Lkotlin/jvm/JvmName;", "name", "Ljava/lang/String;", "J", "Companion", "rtx.kimiko:kimiko"})
public final class FunTimeMine {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String serverId;
    @NotNull
    private final String serverRuName;
    @NotNull
    private final String mineName;
    @NotNull
    private final String rarity;
    @NotNull
    private final String nextRarity;
    private final long refillAtMs;
    @NotNull
    public static final String RARITY_DEFAULT = "default";
    @NotNull
    public static final String RARITY_MYTHICAL = "mythical";
    @NotNull
    public static final String RARITY_LEGENDARY = "legendary";

    public FunTimeMine(@NotNull String serverId, @NotNull String serverRuName, @NotNull String mineName, @NotNull String rarity, @NotNull String nextRarity, long refillAtMs) {
        Intrinsics.checkNotNullParameter((Object)serverId, (String)"serverId");
        Intrinsics.checkNotNullParameter((Object)serverRuName, (String)"serverRuName");
        Intrinsics.checkNotNullParameter((Object)mineName, (String)"mineName");
        Intrinsics.checkNotNullParameter((Object)rarity, (String)"rarity");
        Intrinsics.checkNotNullParameter((Object)nextRarity, (String)"nextRarity");
        this.serverId = serverId;
        this.serverRuName = serverRuName;
        this.mineName = mineName;
        this.rarity = rarity;
        this.nextRarity = nextRarity;
        this.refillAtMs = refillAtMs;
    }

    @JvmName(name="serverId")
    @NotNull
    public final String serverId() {
        return this.serverId;
    }

    @JvmName(name="serverRuName")
    @NotNull
    public final String serverRuName() {
        return this.serverRuName;
    }

    @JvmName(name="mineName")
    @NotNull
    public final String mineName() {
        return this.mineName;
    }

    @JvmName(name="rarity")
    @NotNull
    public final String rarity() {
        return this.rarity;
    }

    @JvmName(name="nextRarity")
    @NotNull
    public final String nextRarity() {
        return this.nextRarity;
    }

    @JvmName(name="refillAtMs")
    public final long refillAtMs() {
        return this.refillAtMs;
    }

    @NotNull
    public final String component1() {
        return this.serverId;
    }

    @NotNull
    public final String component2() {
        return this.serverRuName;
    }

    @NotNull
    public final String component3() {
        return this.mineName;
    }

    @NotNull
    public final String component4() {
        return this.rarity;
    }

    @NotNull
    public final String component5() {
        return this.nextRarity;
    }

    public final long component6() {
        return this.refillAtMs;
    }

    @NotNull
    public final FunTimeMine copy(@NotNull String serverId, @NotNull String serverRuName, @NotNull String mineName, @NotNull String rarity, @NotNull String nextRarity, long refillAtMs) {
        Intrinsics.checkNotNullParameter((Object)serverId, (String)"serverId");
        Intrinsics.checkNotNullParameter((Object)serverRuName, (String)"serverRuName");
        Intrinsics.checkNotNullParameter((Object)mineName, (String)"mineName");
        Intrinsics.checkNotNullParameter((Object)rarity, (String)"rarity");
        Intrinsics.checkNotNullParameter((Object)nextRarity, (String)"nextRarity");
        return new FunTimeMine(serverId, serverRuName, mineName, rarity, nextRarity, refillAtMs);
    }

    public static /* synthetic */ FunTimeMine copy$default(FunTimeMine funTimeMine, String string, String string2, String string3, String string4, String string5, long l, int n, Object object) {
        if ((n & 1) != 0) {
            string = funTimeMine.serverId;
        }
        if ((n & 2) != 0) {
            string2 = funTimeMine.serverRuName;
        }
        if ((n & 4) != 0) {
            string3 = funTimeMine.mineName;
        }
        if ((n & 8) != 0) {
            string4 = funTimeMine.rarity;
        }
        if ((n & 0x10) != 0) {
            string5 = funTimeMine.nextRarity;
        }
        if ((n & 0x20) != 0) {
            l = funTimeMine.refillAtMs;
        }
        return funTimeMine.copy(string, string2, string3, string4, string5, l);
    }

    @NotNull
    public String toString() {
        return "FunTimeMine(serverId=" + this.serverId + ", serverRuName=" + this.serverRuName + ", mineName=" + this.mineName + ", rarity=" + this.rarity + ", nextRarity=" + this.nextRarity + ", refillAtMs=" + this.refillAtMs + ")";
    }

    public int hashCode() {
        int result = this.serverId.hashCode();
        result = result * 31 + this.serverRuName.hashCode();
        result = result * 31 + this.mineName.hashCode();
        result = result * 31 + this.rarity.hashCode();
        result = result * 31 + this.nextRarity.hashCode();
        result = result * 31 + Long.hashCode(this.refillAtMs);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FunTimeMine)) {
            return false;
        }
        FunTimeMine funTimeMine = (FunTimeMine)other;
        if (!Intrinsics.areEqual((Object)this.serverId, (Object)funTimeMine.serverId)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.serverRuName, (Object)funTimeMine.serverRuName)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.mineName, (Object)funTimeMine.mineName)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.rarity, (Object)funTimeMine.rarity)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.nextRarity, (Object)funTimeMine.nextRarity)) {
            return false;
        }
        return this.refillAtMs == funTimeMine.refillAtMs;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/events/funtime/FunTimeMine.Companion;", "", "<init>", "()V", "", "RARITY_DEFAULT", "Ljava/lang/String;", "RARITY_MYTHICAL", "RARITY_LEGENDARY", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

