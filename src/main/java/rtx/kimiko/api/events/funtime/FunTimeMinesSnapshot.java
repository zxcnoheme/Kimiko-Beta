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
import rtx.kimiko.api.events.funtime.FunTimeMine;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u0000 $2\u00020\u0001:\u0001$B-\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0014J>\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\tH\u00c6\u0001\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0018\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001b\u001a\u00020\u001aH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0011\u0010\u001d\u001a\u00020\tH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001d\u0010\u0014R+\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010 \u001a\u0004\b\u0004\u0010\u000eR%\u0010\u0006\u001a\u00020\u00058\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010!\u001a\u0004\b\u0006\u0010\u0010R%\u0010\b\u001a\u00020\u00078\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010\"\u001a\u0004\b\b\u0010\u0012R%\u0010\n\u001a\u00020\t8\u0007z\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010#\u001a\u0004\b\n\u0010\u0014\u00a8\u0006%"}, d2={"Lrtx/kimiko/api/events/funtime/FunTimeMinesSnapshot;", "", "", "Lrtx/kimiko/api/events/funtime/FunTimeMine;", "mines", "", "generatedAt", "", "online", "", "error", "<init>", "(Ljava/util/List;JZLjava/lang/String;)V", "component1", "()Ljava/util/List;", "component2", "()J", "component3", "()Z", "component4", "()Ljava/lang/String;", "copy", "(Ljava/util/List;JZLjava/lang/String;)Lrtx/kimiko/api/events/funtime/FunTimeMinesSnapshot;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Lkotlin/jvm/JvmName;", "name", "Ljava/util/List;", "J", "Z", "Ljava/lang/String;", "Companion", "rtx.kimiko:kimiko"})
public final class FunTimeMinesSnapshot {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final List<FunTimeMine> mines;
    private final long generatedAt;
    private final boolean online;
    @NotNull
    private final String error;

    public FunTimeMinesSnapshot(@NotNull List<FunTimeMine> mines, long generatedAt, boolean online, @NotNull String error) {
        Intrinsics.checkNotNullParameter(mines, (String)"mines");
        Intrinsics.checkNotNullParameter((Object)error, (String)"error");
        this.mines = mines;
        this.generatedAt = generatedAt;
        this.online = online;
        this.error = error;
    }

    @JvmName(name="mines")
    @NotNull
    public final List<FunTimeMine> mines() {
        return this.mines;
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
    public final List<FunTimeMine> component1() {
        return this.mines;
    }

    public final long component2() {
        return this.generatedAt;
    }

    public final boolean component3() {
        return this.online;
    }

    @NotNull
    public final String component4() {
        return this.error;
    }

    @NotNull
    public final FunTimeMinesSnapshot copy(@NotNull List<FunTimeMine> mines, long generatedAt, boolean online, @NotNull String error) {
        Intrinsics.checkNotNullParameter(mines, (String)"mines");
        Intrinsics.checkNotNullParameter((Object)error, (String)"error");
        return new FunTimeMinesSnapshot(mines, generatedAt, online, error);
    }

    public static /* synthetic */ FunTimeMinesSnapshot copy$default(FunTimeMinesSnapshot funTimeMinesSnapshot, List list, long l, boolean bl, String string, int n, Object object) {
        if ((n & 1) != 0) {
            list = funTimeMinesSnapshot.mines;
        }
        if ((n & 2) != 0) {
            l = funTimeMinesSnapshot.generatedAt;
        }
        if ((n & 4) != 0) {
            bl = funTimeMinesSnapshot.online;
        }
        if ((n & 8) != 0) {
            string = funTimeMinesSnapshot.error;
        }
        return funTimeMinesSnapshot.copy(list, l, bl, string);
    }

    @NotNull
    public String toString() {
        return "FunTimeMinesSnapshot(mines=" + this.mines + ", generatedAt=" + this.generatedAt + ", online=" + this.online + ", error=" + this.error + ")";
    }

    public int hashCode() {
        int result = ((Object)this.mines).hashCode();
        result = result * 31 + Long.hashCode(this.generatedAt);
        result = result * 31 + Boolean.hashCode(this.online);
        result = result * 31 + this.error.hashCode();
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FunTimeMinesSnapshot)) {
            return false;
        }
        FunTimeMinesSnapshot funTimeMinesSnapshot = (FunTimeMinesSnapshot)other;
        if (!Intrinsics.areEqual(this.mines, funTimeMinesSnapshot.mines)) {
            return false;
        }
        if (this.generatedAt != funTimeMinesSnapshot.generatedAt) {
            return false;
        }
        if (this.online != funTimeMinesSnapshot.online) {
            return false;
        }
        return Intrinsics.areEqual((Object)this.error, (Object)funTimeMinesSnapshot.error);
    }

    @JvmStatic
    @NotNull
    public static final FunTimeMinesSnapshot offline(@NotNull String error) {
        return Companion.offline(error);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/api/events/funtime/FunTimeMinesSnapshot.Companion;", "", "<init>", "()V", "", "error", "Lrtx/kimiko/api/events/funtime/FunTimeMinesSnapshot;", "Lkotlin/jvm/JvmStatic;", "offline", "(Ljava/lang/String;)Lrtx/kimiko/api/events/funtime/FunTimeMinesSnapshot;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final FunTimeMinesSnapshot offline(@NotNull String error) {
            Intrinsics.checkNotNullParameter((Object)error, (String)"error");
            return new FunTimeMinesSnapshot(CollectionsKt.emptyList(), 0L, false, error);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

