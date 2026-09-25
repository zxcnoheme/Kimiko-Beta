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

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\u000bJ\u0010\u0010\r\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000bJ8\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0013\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0018\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0018\u0010\u000bR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u0019\u0012\b\b\u0004\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u0003\u0010\u000bR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\u0019\u0012\b\b\u0004\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u0004\u0010\u000bR%\u0010\u0006\u001a\u00020\u00058\u0007z\f\b\u0019\u0012\b\b\u0004\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b\u0006\u0010\u000eR%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b\u0019\u0012\b\b\u0004\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001a\u001a\u0004\b\u0007\u0010\u000b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/cards/CardsUser;", "", "", "id", "name", "", "inGame", "avatar", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "()Z", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)Lrtx/kimiko/api/cards/CardsUser;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Lkotlin/jvm/JvmName;", "Ljava/lang/String;", "Z", "rtx.kimiko:kimiko"})
public final class CardsUser {
    @NotNull
    private final String id;
    @NotNull
    private final String name;
    private final boolean inGame;
    @NotNull
    private final String avatar;

    public CardsUser(@NotNull String id, @NotNull String name, boolean inGame, @NotNull String avatar) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)avatar, (String)"avatar");
        this.id = id;
        this.name = name;
        this.inGame = inGame;
        this.avatar = avatar;
    }

    @JvmName(name="id")
    @NotNull
    public final String id() {
        return this.id;
    }

    @JvmName(name="name")
    @NotNull
    public final String name() {
        return this.name;
    }

    @JvmName(name="inGame")
    public final boolean inGame() {
        return this.inGame;
    }

    @JvmName(name="avatar")
    @NotNull
    public final String avatar() {
        return this.avatar;
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    public final boolean component3() {
        return this.inGame;
    }

    @NotNull
    public final String component4() {
        return this.avatar;
    }

    @NotNull
    public final CardsUser copy(@NotNull String id, @NotNull String name, boolean inGame, @NotNull String avatar) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)avatar, (String)"avatar");
        return new CardsUser(id, name, inGame, avatar);
    }

    public static /* synthetic */ CardsUser copy$default(CardsUser cardsUser, String string, String string2, boolean bl, String string3, int n, Object object) {
        if ((n & 1) != 0) {
            string = cardsUser.id;
        }
        if ((n & 2) != 0) {
            string2 = cardsUser.name;
        }
        if ((n & 4) != 0) {
            bl = cardsUser.inGame;
        }
        if ((n & 8) != 0) {
            string3 = cardsUser.avatar;
        }
        return cardsUser.copy(string, string2, bl, string3);
    }

    @NotNull
    public String toString() {
        return "CardsUser(id=" + this.id + ", name=" + this.name + ", inGame=" + this.inGame + ", avatar=" + this.avatar + ")";
    }

    public int hashCode() {
        int result = this.id.hashCode();
        result = result * 31 + this.name.hashCode();
        result = result * 31 + Boolean.hashCode(this.inGame);
        result = result * 31 + this.avatar.hashCode();
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CardsUser)) {
            return false;
        }
        CardsUser cardsUser = (CardsUser)other;
        if (!Intrinsics.areEqual((Object)this.id, (Object)cardsUser.id)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.name, (Object)cardsUser.name)) {
            return false;
        }
        if (this.inGame != cardsUser.inGame) {
            return false;
        }
        return Intrinsics.areEqual((Object)this.avatar, (Object)cardsUser.avatar);
    }
}

