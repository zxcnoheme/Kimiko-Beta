/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.math.Vec3d
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.party;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0012\u0018\u0000 +2\u00020\u0001:\u0001+BW\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0013J\r\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0013J\r\u0010\t\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\u0013J\r\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u000e\u0010\u0014J\r\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0015J\r\u0010\u0017\u001a\u00020\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\n\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\n\u00a2\u0006\u0004\b\u001f\u0010 J\u0015\u0010!\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\n\u00a2\u0006\u0004\b!\u0010\"J\u0015\u0010#\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\n\u00a2\u0006\u0004\b#\u0010\"J\r\u0010$\u001a\u00020\u000f\u00a2\u0006\u0004\b$\u0010\u0015R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010%R\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010%R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010&R\u0014\u0010\u0007\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010&R\u0014\u0010\b\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010&R\u0014\u0010\t\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010%R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010'R\u0014\u0010\f\u001a\u00020\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010'R\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010(R\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010)R\u0016\u0010*\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010'\u00a8\u0006,"}, d2={"Lrtx/kimiko/api/party/PartyMarker;", "", "", "id", "from", "", "x", "y", "z", "dim", "", "spawnMs", "ttlMs", "", "color", "", "rainbow", "<init>", "(Ljava/lang/String;Ljava/lang/String;DDDLjava/lang/String;JJIZ)V", "()Ljava/lang/String;", "()I", "()Z", "Lnet/minecraft/Vec3d;", "pos", "()Lnet/minecraft/Vec3d;", "now", "", "forceExpireSoon", "(J)V", "expiryMs", "()J", "expired", "(J)Z", "ageMs", "(J)J", "remainingMs", "finiteAndSafe", "Ljava/lang/String;", "D", "J", "I", "Z", "forceExpireAt", "Companion", "rtx.kimiko:kimiko"})
public final class PartyMarker {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String id;
    @NotNull
    private final String from;
    private final double x;
    private final double y;
    private final double z;
    @NotNull
    private final String dim;
    private final long spawnMs;
    private final long ttlMs;
    private final int color;
    private final boolean rainbow;
    private volatile long forceExpireAt;
    public static final long DISAPPEAR_MS = 300L;

    public PartyMarker(@NotNull String id, @NotNull String from, double x, double y, double z, @NotNull String dim, long spawnMs, long ttlMs, int color, boolean rainbow) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        Intrinsics.checkNotNullParameter((Object)from, (String)"from");
        Intrinsics.checkNotNullParameter((Object)dim, (String)"dim");
        this.id = id;
        this.from = from;
        this.x = x;
        this.y = y;
        this.z = z;
        this.dim = dim;
        this.spawnMs = spawnMs;
        this.ttlMs = ttlMs;
        this.color = color;
        this.rainbow = rainbow;
        this.forceExpireAt = Long.MAX_VALUE;
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
    public final String dim() {
        return this.dim;
    }

    public final int color() {
        return this.color;
    }

    public final boolean rainbow() {
        return this.rainbow;
    }

    @NotNull
    public final Vec3d pos() {
        return new Vec3d(this.x, this.y, this.z);
    }

    public final void forceExpireSoon(long now) {
        long at = now + 300L;
        if (at < this.forceExpireAt) {
            this.forceExpireAt = at;
        }
    }

    private final long expiryMs() {
        return Math.min(this.spawnMs + this.ttlMs, this.forceExpireAt);
    }

    public final boolean expired(long now) {
        return now >= this.expiryMs();
    }

    public final long ageMs(long now) {
        return now - this.spawnMs;
    }

    public final long remainingMs(long now) {
        return this.expiryMs() - now;
    }

    public final boolean finiteAndSafe() {
        return Math.abs(this.x) <= Double.MAX_VALUE && Math.abs(this.y) <= Double.MAX_VALUE && Math.abs(this.z) <= Double.MAX_VALUE && Math.abs(this.x) <= 3.0E7 && Math.abs(this.y) <= 3.0E7 && Math.abs(this.z) <= 3.0E7;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/api/party/PartyMarker.Companion;", "", "<init>", "()V", "", "DISAPPEAR_MS", "J", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

