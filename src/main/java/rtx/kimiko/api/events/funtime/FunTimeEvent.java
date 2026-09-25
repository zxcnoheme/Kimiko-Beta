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
package rtx.kimiko.api.events.funtime;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\fJB\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0018\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0018\u0010\u000eJ\u0011\u0010\u0019\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0019\u0010\fR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u001a\u0012\b\b\u0003\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u0003\u0010\fR%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b\u001a\u0012\b\b\u0003\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b\u0005\u0010\u000eR%\u0010\u0006\u001a\u00020\u00048\u0007z\f\b\u001a\u0012\b\b\u0003\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b\u0006\u0010\u000eR%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b\u001a\u0012\b\b\u0003\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001b\u001a\u0004\b\u0007\u0010\fR%\u0010\b\u001a\u00020\u00028\u0007z\f\b\u001a\u0012\b\b\u0003\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010\u001b\u001a\u0004\b\b\u0010\f\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/api/events/funtime/FunTimeEvent;", "", "", "name", "", "anarchy", "seconds", "status", "rarity", "<init>", "(Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;)V", "component1", "()Ljava/lang/String;", "component2", "()I", "component3", "component4", "component5", "copy", "(Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;)Lrtx/kimiko/api/events/funtime/FunTimeEvent;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Lkotlin/jvm/JvmName;", "Ljava/lang/String;", "I", "rtx.kimiko:kimiko"})
public final class FunTimeEvent {
    @NotNull
    private final String name;
    private final int anarchy;
    private final int seconds;
    @NotNull
    private final String status;
    @NotNull
    private final String rarity;

    public FunTimeEvent(@NotNull String name, int anarchy, int seconds, @NotNull String status, @NotNull String rarity) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)status, (String)"status");
        Intrinsics.checkNotNullParameter((Object)rarity, (String)"rarity");
        this.name = name;
        this.anarchy = anarchy;
        this.seconds = seconds;
        this.status = status;
        this.rarity = rarity;
    }

    @JvmName(name="name")
    @NotNull
    public final String name() {
        return this.name;
    }

    @JvmName(name="anarchy")
    public final int anarchy() {
        return this.anarchy;
    }

    @JvmName(name="seconds")
    public final int seconds() {
        return this.seconds;
    }

    @JvmName(name="status")
    @NotNull
    public final String status() {
        return this.status;
    }

    @JvmName(name="rarity")
    @NotNull
    public final String rarity() {
        return this.rarity;
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    public final int component2() {
        return this.anarchy;
    }

    public final int component3() {
        return this.seconds;
    }

    @NotNull
    public final String component4() {
        return this.status;
    }

    @NotNull
    public final String component5() {
        return this.rarity;
    }

    @NotNull
    public final FunTimeEvent copy(@NotNull String name, int anarchy, int seconds, @NotNull String status, @NotNull String rarity) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)status, (String)"status");
        Intrinsics.checkNotNullParameter((Object)rarity, (String)"rarity");
        return new FunTimeEvent(name, anarchy, seconds, status, rarity);
    }

    public static /* synthetic */ FunTimeEvent copy$default(FunTimeEvent funTimeEvent, String string, int n, int n2, String string2, String string3, int n3, Object object) {
        if ((n3 & 1) != 0) {
            string = funTimeEvent.name;
        }
        if ((n3 & 2) != 0) {
            n = funTimeEvent.anarchy;
        }
        if ((n3 & 4) != 0) {
            n2 = funTimeEvent.seconds;
        }
        if ((n3 & 8) != 0) {
            string2 = funTimeEvent.status;
        }
        if ((n3 & 0x10) != 0) {
            string3 = funTimeEvent.rarity;
        }
        return funTimeEvent.copy(string, n, n2, string2, string3);
    }

    @NotNull
    public String toString() {
        return "FunTimeEvent(name=" + this.name + ", anarchy=" + this.anarchy + ", seconds=" + this.seconds + ", status=" + this.status + ", rarity=" + this.rarity + ")";
    }

    public int hashCode() {
        int result = this.name.hashCode();
        result = result * 31 + Integer.hashCode(this.anarchy);
        result = result * 31 + Integer.hashCode(this.seconds);
        result = result * 31 + this.status.hashCode();
        result = result * 31 + this.rarity.hashCode();
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FunTimeEvent)) {
            return false;
        }
        FunTimeEvent funTimeEvent = (FunTimeEvent)other;
        if (!Intrinsics.areEqual((Object)this.name, (Object)funTimeEvent.name)) {
            return false;
        }
        if (this.anarchy != funTimeEvent.anarchy) {
            return false;
        }
        if (this.seconds != funTimeEvent.seconds) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.status, (Object)funTimeEvent.status)) {
            return false;
        }
        return Intrinsics.areEqual((Object)this.rarity, (Object)funTimeEvent.rarity);
    }
}

