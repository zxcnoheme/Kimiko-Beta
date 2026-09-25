/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.party;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\f\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0002\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0010J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0011J\r\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0011J\r\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\u0010J\r\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\u0012J\r\u0010\n\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u0012J\r\u0010\u000b\u001a\u00020\b\u00a2\u0006\u0004\b\u000b\u0010\u0012J\r\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0014R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0015R\u0014\u0010\u0006\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0015R\u0014\u0010\u0007\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0014R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0016R\u0014\u0010\n\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0016R\u0014\u0010\u000b\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u0016R\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\u0017\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/api/party/PartyMember;", "", "", "name", "", "leader", "online", "world", "", "x", "y", "z", "", "hp", "<init>", "(Ljava/lang/String;ZZLjava/lang/String;DDDF)V", "()Ljava/lang/String;", "()Z", "()D", "()F", "Ljava/lang/String;", "Z", "D", "F", "rtx.kimiko:kimiko"})
public final class PartyMember {
    @NotNull
    private final String name;
    private final boolean leader;
    private final boolean online;
    @NotNull
    private final String world;
    private final double x;
    private final double y;
    private final double z;
    private final float hp;

    public PartyMember(@NotNull String name, boolean leader, boolean online, @NotNull String world, double x, double y, double z, float hp) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)world, (String)"world");
        this.name = name;
        this.leader = leader;
        this.online = online;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.hp = hp;
    }

    public /* synthetic */ PartyMember(String string, boolean bl, boolean bl2, String string2, double d, double d2, double d3, float f, int n, DefaultConstructorMarker defaultConstructorMarker) {
        this(string, bl, bl2, ((n & 8) != 0 ? "" : string2), ((n & 0x10) != 0 ? 0.0 : d), ((n & 0x20) != 0 ? 0.0 : d2), ((n & 0x40) != 0 ? 0.0 : d3), ((n & 0x80) != 0 ? 20.0f : f));
    }

    @NotNull
    public final String name() {
        return this.name;
    }

    public final boolean leader() {
        return this.leader;
    }

    public final boolean online() {
        return this.online;
    }

    @NotNull
    public final String world() {
        return this.world;
    }

    public final double x() {
        return this.x;
    }

    public final double y() {
        return this.y;
    }

    public final double z() {
        return this.z;
    }

    public final float hp() {
        return this.hp;
    }
}

