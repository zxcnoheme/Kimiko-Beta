/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.party;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\nJ\r\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\nJ\r\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\nJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u000fR\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u000fR\u0014\u0010\u0005\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u000fR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/party/PartyInvite;", "", "", "id", "from", "party", "", "expiresAtMs", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "()Ljava/lang/String;", "now", "", "expired", "(J)Z", "Ljava/lang/String;", "J", "rtx.kimiko:kimiko"})
public final class PartyInvite {
    @NotNull
    private final String id;
    @NotNull
    private final String from;
    @NotNull
    private final String party;
    private final long expiresAtMs;

    public PartyInvite(@NotNull String id, @NotNull String from, @NotNull String party, long expiresAtMs) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        Intrinsics.checkNotNullParameter((Object)from, (String)"from");
        Intrinsics.checkNotNullParameter((Object)party, (String)"party");
        this.id = id;
        this.from = from;
        this.party = party;
        this.expiresAtMs = expiresAtMs;
    }

    @NotNull
    public final String id() {
        return this.id;
    }

    @NotNull
    public final String from() {
        return this.from;
    }

    @NotNull
    public final String party() {
        return this.party;
    }

    public final boolean expired(long now) {
        return now >= this.expiresAtMs;
    }
}

