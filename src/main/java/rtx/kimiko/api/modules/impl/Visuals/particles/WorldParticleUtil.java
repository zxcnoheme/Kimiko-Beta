/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.world.BlockView
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.block.BlockState
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.world.ClientWorld
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.particles;

import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.world.BlockView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J5\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eJ3\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000fH\u0007b\u0002\b\f\u00a2\u0006\u0004\b\u0014\u0010\u0015J/\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/WorldParticleUtil;", "", "<init>", "()V", "Lnet/minecraft/MinecraftClient;", "client", "Ljava/util/Random;", "random", "", "maxDistance", "Lnet/minecraft/Vec3d;", "at", "Lkotlin/jvm/JvmStatic;", "randomSpawnPosition", "(Lnet/minecraft/MinecraftClient;Ljava/util/Random;FLnet/minecraft/Vec3d;)Lnet/minecraft/Vec3d;", "", "x", "y", "z", "", "isSolidCollisionBlock", "(Lnet/minecraft/MinecraftClient;DDD)Z", "minDistance", "randomVec", "(Ljava/util/Random;Lnet/minecraft/Vec3d;FF)Lnet/minecraft/Vec3d;", "rtx.kimiko:kimiko"})
public final class WorldParticleUtil {
    @NotNull
    public static final WorldParticleUtil INSTANCE = new WorldParticleUtil();

    private WorldParticleUtil() {
    }

    @JvmStatic
    @Nullable
    public static final Vec3d randomSpawnPosition(@NotNull MinecraftClient client, @NotNull Random random, float maxDistance, @NotNull Vec3d at) {
        Intrinsics.checkNotNullParameter((Object)client, (String)"client");
        Intrinsics.checkNotNullParameter((Object)random, (String)"random");
        Intrinsics.checkNotNullParameter((Object)at, (String)"at");
        if (client.world == null) {
            return null;
        }
        for (int attempt = 0; attempt < 10; ++attempt) {
            Vec3d vec = INSTANCE.randomVec(random, at, maxDistance / 5.0f, maxDistance);
            if (WorldParticleUtil.isSolidCollisionBlock(client, vec.x, vec.y, vec.z)) continue;
            return vec;
        }
        return null;
    }

    @JvmStatic
    public static final boolean isSolidCollisionBlock(@NotNull MinecraftClient client, double x, double y, double z) {
        Intrinsics.checkNotNullParameter((Object)client, (String)"client");
        ClientWorld clientWorld3 = client.world;
        if (clientWorld3 == null) {
            return false;
        }
        ClientWorld level = clientWorld3;
        BlockPos blockPos2 = BlockPos.ofFloored((double)x, (double)y, (double)z);
        Intrinsics.checkNotNullExpressionValue((Object)blockPos2, (String)"containing(...)");
        BlockPos pos = blockPos2;
        BlockState blockState2 = level.getBlockState(pos);
        Intrinsics.checkNotNullExpressionValue((Object)blockState2, (String)"getBlockState(...)");
        BlockState state = blockState2;
        return !state.isAir() && !state.getCollisionShape((BlockView)level, pos).isEmpty();
    }

    private final Vec3d randomVec(Random random, Vec3d at, float minDistance, float maxDistance) {
        float minDist = Math.min(minDistance, maxDistance);
        float maxDist = Math.max(minDistance, maxDistance);
        float yaw = -180.0f + random.nextFloat() * 360.0f;
        float pitch = -90.0f + random.nextFloat() * 180.0f;
        float distance = minDist == maxDist ? minDist : minDist + random.nextFloat() * (maxDist - minDist);
        double yawRadians = Math.toRadians(yaw);
        double pitchRadians = Math.toRadians(pitch);
        Vec3d vec3d2 = at.add(Math.cos(pitchRadians) * Math.sin(yawRadians) * (double)(-distance), Math.sin(pitchRadians) * (double)(-distance), Math.cos(pitchRadians) * Math.cos(yawRadians) * (double)distance);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
        return vec3d2;
    }
}

