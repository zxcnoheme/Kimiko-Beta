/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.cards;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0007\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0010J\u0010\u0010\u0013\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0010J\u0010\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015JB\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0007H\u00c6\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u0019\u001a\u00020\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001c\u001a\u00020\u001bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0011\u0010\u001e\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001e\u0010\u0010R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010!\u001a\u0004\b\u0003\u0010\u0010R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010!\u001a\u0004\b\u0004\u0010\u0010R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010!\u001a\u0004\b\u0005\u0010\u0010R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010!\u001a\u0004\b\u0006\u0010\u0010R%\u0010\b\u001a\u00020\u00078\u0007z\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010\"\u001a\u0004\b\b\u0010\u0015\u00a8\u0006#"}, d2={"Lrtx/kimiko/api/cards/CardsInvite;", "", "", "id", "fromId", "fromName", "fromAvatar", "", "expiresAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "now", "", "expired", "(J)Z", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "()J", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)Lrtx/kimiko/api/cards/CardsInvite;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Lkotlin/jvm/JvmName;", "name", "Ljava/lang/String;", "J", "rtx.kimiko:kimiko"})
public final class CardsInvite {
    @NotNull
    private final String id;
    @NotNull
    private final String fromId;
    @NotNull
    private final String fromName;
    @NotNull
    private final String fromAvatar;
    private final long expiresAt;

    public CardsInvite(@NotNull String id, @NotNull String fromId, @NotNull String fromName, @NotNull String fromAvatar, long expiresAt) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        Intrinsics.checkNotNullParameter((Object)fromId, (String)"fromId");
        Intrinsics.checkNotNullParameter((Object)fromName, (String)"fromName");
        Intrinsics.checkNotNullParameter((Object)fromAvatar, (String)"fromAvatar");
        this.id = id;
        this.fromId = fromId;
        this.fromName = fromName;
        this.fromAvatar = fromAvatar;
        this.expiresAt = expiresAt;
    }

    @JvmName(name="id")
    @NotNull
    public final String id() {
        return this.id;
    }

    @JvmName(name="fromId")
    @NotNull
    public final String fromId() {
        return this.fromId;
    }

    @JvmName(name="fromName")
    @NotNull
    public final String fromName() {
        return this.fromName;
    }

    @JvmName(name="fromAvatar")
    @NotNull
    public final String fromAvatar() {
        return this.fromAvatar;
    }

    @JvmName(name="expiresAt")
    public final long expiresAt() {
        return this.expiresAt;
    }

    public final boolean expired(long now) {
        return now >= this.expiresAt;
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.fromId;
    }

    @NotNull
    public final String component3() {
        return this.fromName;
    }

    @NotNull
    public final String component4() {
        return this.fromAvatar;
    }

    public final long component5() {
        return this.expiresAt;
    }

    @NotNull
    public final CardsInvite copy(@NotNull String id, @NotNull String fromId, @NotNull String fromName, @NotNull String fromAvatar, long expiresAt) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        Intrinsics.checkNotNullParameter((Object)fromId, (String)"fromId");
        Intrinsics.checkNotNullParameter((Object)fromName, (String)"fromName");
        Intrinsics.checkNotNullParameter((Object)fromAvatar, (String)"fromAvatar");
        return new CardsInvite(id, fromId, fromName, fromAvatar, expiresAt);
    }

    public static /* synthetic */ CardsInvite copy$default(CardsInvite cardsInvite, String string, String string2, String string3, String string4, long l, int n, Object object) {
        if ((n & 1) != 0) {
            string = cardsInvite.id;
        }
        if ((n & 2) != 0) {
            string2 = cardsInvite.fromId;
        }
        if ((n & 4) != 0) {
            string3 = cardsInvite.fromName;
        }
        if ((n & 8) != 0) {
            string4 = cardsInvite.fromAvatar;
        }
        if ((n & 0x10) != 0) {
            l = cardsInvite.expiresAt;
        }
        return cardsInvite.copy(string, string2, string3, string4, l);
    }

    @NotNull
    public String toString() {
        return "CardsInvite(id=" + this.id + ", fromId=" + this.fromId + ", fromName=" + this.fromName + ", fromAvatar=" + this.fromAvatar + ", expiresAt=" + this.expiresAt + ")";
    }

    public int hashCode() {
        int result = this.id.hashCode();
        result = result * 31 + this.fromId.hashCode();
        result = result * 31 + this.fromName.hashCode();
        result = result * 31 + this.fromAvatar.hashCode();
        result = result * 31 + Long.hashCode(this.expiresAt);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CardsInvite)) {
            return false;
        }
        CardsInvite cardsInvite = (CardsInvite)other;
        if (!Intrinsics.areEqual((Object)this.id, (Object)cardsInvite.id)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.fromId, (Object)cardsInvite.fromId)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.fromName, (Object)cardsInvite.fromName)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.fromAvatar, (Object)cardsInvite.fromAvatar)) {
            return false;
        }
        return this.expiresAt == cardsInvite.expiresAt;
    }
}

