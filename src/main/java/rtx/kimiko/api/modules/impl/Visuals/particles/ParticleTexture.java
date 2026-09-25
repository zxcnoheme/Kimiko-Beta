/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.Identifier
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.particles;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0015\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0015\u0010\tR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0019\u0010\u000b\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/ParticleTexture;", "", "", "mode", "Lnet/minecraft/Identifier;", "id", "<init>", "(Ljava/lang/String;Lnet/minecraft/Identifier;)V", "component1", "()Ljava/lang/String;", "component2", "()Lnet/minecraft/Identifier;", "copy", "(Ljava/lang/String;Lnet/minecraft/Identifier;)Lrtx/kimiko/api/modules/impl/Visuals/particles/ParticleTexture;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Ljava/lang/String;", "getMode", "Lnet/minecraft/Identifier;", "getId", "rtx.kimiko:kimiko"})
public final class ParticleTexture {
    @NotNull
    private final String mode;
    @NotNull
    private final Identifier id;

    public ParticleTexture(@NotNull String mode, @NotNull Identifier id) {
        Intrinsics.checkNotNullParameter((Object)mode, (String)"mode");
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        this.mode = mode;
        this.id = id;
    }

    @NotNull
    public final String getMode() {
        return this.mode;
    }

    @NotNull
    public final Identifier getId() {
        return this.id;
    }

    @NotNull
    public final String component1() {
        return this.mode;
    }

    @NotNull
    public final Identifier component2() {
        return this.id;
    }

    @NotNull
    public final ParticleTexture copy(@NotNull String mode, @NotNull Identifier id) {
        Intrinsics.checkNotNullParameter((Object)mode, (String)"mode");
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        return new ParticleTexture(mode, id);
    }

    public static /* synthetic */ ParticleTexture copy$default(ParticleTexture particleTexture, String string, Identifier identifier2, int n, Object object) {
        if ((n & 1) != 0) {
            string = particleTexture.mode;
        }
        if ((n & 2) != 0) {
            identifier2 = particleTexture.id;
        }
        return particleTexture.copy(string, identifier2);
    }

    @NotNull
    public String toString() {
        return "ParticleTexture(mode=" + this.mode + ", id=" + this.id + ")";
    }

    public int hashCode() {
        int result = this.mode.hashCode();
        result = result * 31 + this.id.hashCode();
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ParticleTexture)) {
            return false;
        }
        ParticleTexture particleTexture = (ParticleTexture)other;
        if (!Intrinsics.areEqual((Object)this.mode, (Object)particleTexture.mode)) {
            return false;
        }
        return Intrinsics.areEqual((Object)this.id, (Object)particleTexture.id);
    }
}

