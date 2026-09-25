/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  net.minecraft.particle.ParticleEffect
 *  net.minecraft.particle.ParticleTypes
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.world.ClientWorld
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.killeffect;

import java.util.concurrent.ThreadLocalRandom;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J?\u0010\u000e\u001a\u00020\r2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\t\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\r\u00a2\u0006\u0004\b\u0010\u0010\u0003J\r\u0010\u0011\u001a\u00020\r\u00a2\u0006\u0004\b\u0011\u0010\u0003\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectParticleSystem;", "", "<init>", "()V", "Lnet/minecraft/Vec3d;", "center", "", "lifetimeMin", "lifetimeMax", "", "maxFlight", "count", "gravity", "", "spawnBurst", "(Lnet/minecraft/Vec3d;IIFIF)V", "tick", "clear", "rtx.kimiko:kimiko"})
public final class KillEffectParticleSystem {
    public final void spawnBurst(@Nullable Vec3d center, int lifetimeMin, int lifetimeMax, float maxFlight, int count, float gravity) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        ClientWorld clientWorld3 = minecraft.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        if (center == null) {
            return;
        }
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double velocity = maxFlight / Math.max((float)lifetimeMin / 50.0f, 1.0f) * 0.7f;
        int safeCount = RangesKt.coerceIn((int)count, (int)0, (int)32);
        for (int i = 0; i < safeCount; ++i) {
            level.addParticleClient((ParticleEffect)ParticleTypes.END_ROD, center.x, center.y, center.z, random.nextDouble(-velocity, velocity), random.nextDouble(-velocity * 0.25, velocity * 0.55) - (double)gravity, random.nextDouble(-velocity, velocity));
        }
    }

    public final void tick() {
    }

    public final void clear() {
    }
}

