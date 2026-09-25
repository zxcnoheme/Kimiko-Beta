/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.events.funtime;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.funtime.FunTimeEvent;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u0000 &2\u00020\u0001:\u0001&B;\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0016JN\u0010\u0017\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u00c6\u0001\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001b\u0010\u001a\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001d\u001a\u00020\u001cH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0011\u0010\u001f\u001a\u00020\nH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001f\u0010\u0016R+\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0007z\f\b \u0012\b\b!\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010\"\u001a\u0004\b\u0004\u0010\u000fR+\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0007z\f\b \u0012\b\b!\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010\"\u001a\u0004\b\u0005\u0010\u000fR%\u0010\u0007\u001a\u00020\u00068\u0007z\f\b \u0012\b\b!\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010#\u001a\u0004\b\u0007\u0010\u0012R%\u0010\t\u001a\u00020\b8\u0007z\f\b \u0012\b\b!\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010$\u001a\u0004\b\t\u0010\u0014R%\u0010\u000b\u001a\u00020\n8\u0007z\f\b \u0012\b\b!\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010%\u001a\u0004\b\u000b\u0010\u0016\u00a8\u0006'"}, d2={"Lrtx/kimiko/api/events/funtime/FunTimeEventsSnapshot;", "", "", "Lrtx/kimiko/api/events/funtime/FunTimeEvent;", "current", "nearest", "", "generatedAt", "", "online", "", "error", "<init>", "(Ljava/util/List;Ljava/util/List;JZLjava/lang/String;)V", "component1", "()Ljava/util/List;", "component2", "component3", "()J", "component4", "()Z", "component5", "()Ljava/lang/String;", "copy", "(Ljava/util/List;Ljava/util/List;JZLjava/lang/String;)Lrtx/kimiko/api/events/funtime/FunTimeEventsSnapshot;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Lkotlin/jvm/JvmName;", "name", "Ljava/util/List;", "J", "Z", "Ljava/lang/String;", "Companion", "rtx.kimiko:kimiko"})
public final class FunTimeEventsSnapshot {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final List<FunTimeEvent> current;
    @NotNull
    private final List<FunTimeEvent> nearest;
    private final long generatedAt;
    private final boolean online;
    @NotNull
    private final String error;

    public FunTimeEventsSnapshot(@NotNull List<FunTimeEvent> current, @NotNull List<FunTimeEvent> nearest, long generatedAt, boolean online, @NotNull String error) {
        Intrinsics.checkNotNullParameter(current, (String)"current");
        Intrinsics.checkNotNullParameter(nearest, (String)"nearest");
        Intrinsics.checkNotNullParameter((Object)error, (String)"error");
        this.current = current;
        this.nearest = nearest;
        this.generatedAt = generatedAt;
        this.online = online;
        this.error = error;
    }

    @JvmName(name="current")
    @NotNull
    public final List<FunTimeEvent> current() {
        return this.current;
    }

    @JvmName(name="nearest")
    @NotNull
    public final List<FunTimeEvent> nearest() {
        return this.nearest;
    }

    @JvmName(name="generatedAt")
    public final long generatedAt() {
        return this.generatedAt;
    }

    @JvmName(name="online")
    public final boolean online() {
        return this.online;
    }

    @JvmName(name="error")
    @NotNull
    public final String error() {
        return this.error;
    }

    @NotNull
    public final List<FunTimeEvent> component1() {
        return this.current;
    }

    @NotNull
    public final List<FunTimeEvent> component2() {
        return this.nearest;
    }

    public final long component3() {
        return this.generatedAt;
    }

    public final boolean component4() {
        return this.online;
    }

    @NotNull
    public final String component5() {
        return this.error;
    }

    @NotNull
    public final FunTimeEventsSnapshot copy(@NotNull List<FunTimeEvent> current, @NotNull List<FunTimeEvent> nearest, long generatedAt, boolean online, @NotNull String error) {
        Intrinsics.checkNotNullParameter(current, (String)"current");
        Intrinsics.checkNotNullParameter(nearest, (String)"nearest");
        Intrinsics.checkNotNullParameter((Object)error, (String)"error");
        return new FunTimeEventsSnapshot(current, nearest, generatedAt, online, error);
    }

    public static /* synthetic */ FunTimeEventsSnapshot copy$default(FunTimeEventsSnapshot funTimeEventsSnapshot, List list, List list2, long l, boolean bl, String string, int n, Object object) {
        if ((n & 1) != 0) {
            list = funTimeEventsSnapshot.current;
        }
        if ((n & 2) != 0) {
            list2 = funTimeEventsSnapshot.nearest;
        }
        if ((n & 4) != 0) {
            l = funTimeEventsSnapshot.generatedAt;
        }
        if ((n & 8) != 0) {
            bl = funTimeEventsSnapshot.online;
        }
        if ((n & 0x10) != 0) {
            string = funTimeEventsSnapshot.error;
        }
        return funTimeEventsSnapshot.copy(list, list2, l, bl, string);
    }

    @NotNull
    public String toString() {
        return "FunTimeEventsSnapshot(current=" + this.current + ", nearest=" + this.nearest + ", generatedAt=" + this.generatedAt + ", online=" + this.online + ", error=" + this.error + ")";
    }

    public int hashCode() {
        int result = ((Object)this.current).hashCode();
        result = result * 31 + ((Object)this.nearest).hashCode();
        result = result * 31 + Long.hashCode(this.generatedAt);
        result = result * 31 + Boolean.hashCode(this.online);
        result = result * 31 + this.error.hashCode();
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FunTimeEventsSnapshot)) {
            return false;
        }
        FunTimeEventsSnapshot funTimeEventsSnapshot = (FunTimeEventsSnapshot)other;
        if (!Intrinsics.areEqual(this.current, funTimeEventsSnapshot.current)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.nearest, funTimeEventsSnapshot.nearest)) {
            return false;
        }
        if (this.generatedAt != funTimeEventsSnapshot.generatedAt) {
            return false;
        }
        if (this.online != funTimeEventsSnapshot.online) {
            return false;
        }
        return Intrinsics.areEqual((Object)this.error, (Object)funTimeEventsSnapshot.error);
    }

    @JvmStatic
    @NotNull
    public static final FunTimeEventsSnapshot offline(@NotNull String error) {
        return Companion.offline(error);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/api/events/funtime/FunTimeEventsSnapshot.Companion;", "", "<init>", "()V", "", "error", "Lrtx/kimiko/api/events/funtime/FunTimeEventsSnapshot;", "Lkotlin/jvm/JvmStatic;", "offline", "(Ljava/lang/String;)Lrtx/kimiko/api/events/funtime/FunTimeEventsSnapshot;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final FunTimeEventsSnapshot offline(@NotNull String error) {
            Intrinsics.checkNotNullParameter((Object)error, (String)"error");
            return new FunTimeEventsSnapshot(CollectionsKt.emptyList(), CollectionsKt.emptyList(), 0L, false, error);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

