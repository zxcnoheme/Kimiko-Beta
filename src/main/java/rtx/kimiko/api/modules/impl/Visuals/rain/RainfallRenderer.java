/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.ArrayDeque
 *  kotlin.internal.ProgressionUtilKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.ranges.RangesKt
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.Heightmap.Type
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.rain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00a6\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 `2\u00020\u0001:\u0005abcd`B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J=\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\n\u00a2\u0006\u0004\b\u0010\u0010\u0011J/\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J'\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J/\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010!\u001a\u00020 2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b!\u0010\"JQ\u0010(\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020 2\b\b\u0002\u0010'\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b(\u0010)J/\u0010-\u001a\u00020\u00042\u0006\u0010+\u001a\u00020*2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010,\u001a\u00020 H\u0002\u00a2\u0006\u0004\b-\u0010.J=\u00106\u001a\u00020\u00042\u0006\u00100\u001a\u00020/2\u0006\u00102\u001a\u0002012\u0006\u0010\t\u001a\u00020\b2\u0006\u00103\u001a\u00020\n2\u0006\u00104\u001a\u00020\n2\u0006\u00105\u001a\u00020\n\u00a2\u0006\u0004\b6\u00107J7\u0010?\u001a\u00020\u00042\u0006\u00109\u001a\u0002082\u0006\u0010;\u001a\u00020:2\u0006\u0010=\u001a\u00020<2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010>\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b?\u0010@J7\u0010C\u001a\u00020\u00042\u0006\u00109\u001a\u0002082\u0006\u0010;\u001a\u00020:2\u0006\u0010B\u001a\u00020A2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010>\u001a\u00020\nH\u0002\u00a2\u0006\u0004\bC\u0010DJO\u0010K\u001a\u00020\u00042\u0006\u00109\u001a\u0002082\u0006\u0010;\u001a\u00020:2\u0006\u0010E\u001a\u00020\n2\u0006\u0010F\u001a\u00020\n2\u0006\u0010G\u001a\u00020\n2\u0006\u0010H\u001a\u00020\n2\u0006\u0010I\u001a\u00020\n2\u0006\u0010J\u001a\u00020\nH\u0002\u00a2\u0006\u0004\bK\u0010LR$\u0010O\u001a\u0012\u0012\u0004\u0012\u00020*0Mj\b\u0012\u0004\u0012\u00020*`N8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bO\u0010PR\u001a\u0010R\u001a\b\u0012\u0004\u0012\u00020A0Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bR\u0010SR\u001a\u0010T\u001a\b\u0012\u0004\u0012\u00020A0Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u0010SR\u001a\u0010U\u001a\b\u0012\u0004\u0012\u00020<0Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bU\u0010SR\u001a\u0010V\u001a\b\u0012\u0004\u0012\u00020<0Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bV\u0010SR0\u0010[\u001a\u001e\u0012\u0004\u0012\u00020X\u0012\u0004\u0012\u00020Y0Wj\u000e\u0012\u0004\u0012\u00020X\u0012\u0004\u0012\u00020Y`Z8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b[\u0010\\R\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b^\u0010_\u00a8\u0006e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/rain/RainfallRenderer;", "", "<init>", "()V", "", "clear", "Lnet/minecraft/ClientWorld;", "level", "Lnet/minecraft/Vec3d;", "camPos", "", "dt", "", "count", "speedMul", "wetLevel", "update", "(Lnet/minecraft/ClientWorld;Lnet/minecraft/Vec3d;FIFF)V", "", "x", "y", "z", "spawnShards", "(DDDI)V", "updateFootsteps", "(Lnet/minecraft/ClientWorld;Lnet/minecraft/Vec3d;F)V", "Lnet/minecraft/PlayerEntity;", "player", "pos", "impact", "landingSplash", "(Lnet/minecraft/ClientWorld;Lnet/minecraft/PlayerEntity;Lnet/minecraft/Vec3d;F)V", "", "onExposedSurface", "(Lnet/minecraft/ClientWorld;Lnet/minecraft/Vec3d;)Z", "maxR", "life", "strength", "splash", "shape", "spawnRipple", "(DDDFFFZI)V", "Lrtx/kimiko/api/modules/impl/Visuals/rain/RainfallRenderer$Drop;", "drop", "initial", "respawn", "(Lrtx/kimiko/api/modules/impl/Visuals/rain/RainfallRenderer$Drop;Lnet/minecraft/ClientWorld;Lnet/minecraft/Vec3d;Z)V", "Lnet/minecraft/MatrixStack;", "stack", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "provider", "dropAlpha", "rippleAlpha", "footAlpha", "render", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/Vec3d;FFF)V", "Lnet/minecraft/VertexConsumer;", "consumer", "Lnet/minecraft/MatrixStack$Entry;", "pose", "Lrtx/kimiko/api/modules/impl/Visuals/rain/RainfallRenderer$Shard;", "shard", "alphaMul", "renderShard", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lrtx/kimiko/api/modules/impl/Visuals/rain/RainfallRenderer$Shard;Lnet/minecraft/Vec3d;F)V", "Lrtx/kimiko/api/modules/impl/Visuals/rain/RainfallRenderer$Ripple;", "ripple", "renderRipple", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lrtx/kimiko/api/modules/impl/Visuals/rain/RainfallRenderer$Ripple;Lnet/minecraft/Vec3d;F)V", "cx", "cy", "cz", "radius", "width", "alpha", "ring", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;FFFFFF)V", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "drops", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayDeque;", "ripples", "Lkotlin/collections/ArrayDeque;", "ripplePool", "shards", "shardPool", "Ljava/util/HashMap;", "Ljava/util/UUID;", "Lrtx/kimiko/api/modules/impl/Visuals/rain/RainfallRenderer$FootState;", "Lkotlin/collections/HashMap;", "footStates", "Ljava/util/HashMap;", "Ljava/util/Random;", "random", "Ljava/util/Random;", "Companion", "Drop", "Ripple", "FootState", "Shard", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nRainfallRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RainfallRenderer.kt\nrtx/kimiko/api/modules/impl/Visuals/rain/RainfallRenderer\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,591:1\n460#2,7:592\n*S KotlinDebug\n*F\n+ 1 RainfallRenderer.kt\nrtx/kimiko/api/modules/impl/Visuals/rain/RainfallRenderer\n*L\n194#1:592,7\n*E\n"})
public final class RainfallRenderer {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ArrayList<Drop> drops = new ArrayList();
    @NotNull
    private final ArrayDeque<Ripple> ripples = new ArrayDeque();
    @NotNull
    private final ArrayDeque<Ripple> ripplePool = new ArrayDeque();
    @NotNull
    private final ArrayDeque<Shard> shards = new ArrayDeque();
    @NotNull
    private final ArrayDeque<Shard> shardPool = new ArrayDeque();
    @NotNull
    private final HashMap<UUID, FootState> footStates = new HashMap();
    @NotNull
    private final Random random = new Random();
    private static final int MAX_DROPS = 1600;
    private static final float RADIUS = 16.0f;
    private static final float SPAWN_BOTTOM = 5.0f;
    private static final float SPAWN_TOP = 18.0f;
    private static final int STREAK_RGB = 12570342;
    private static final int HEAD_RGB = 14477560;
    private static final int RIPPLE_RGB = 13951732;
    private static final int RIPPLE_CAP = 340;
    private static final int RAIN_RIPPLE_CAP = 300;
    private static final double RIPPLE_RADIUS_SQ = 196.0;
    private static final float RIPPLE_VIEW_SQ = 676.0f;
    private static final float SPLASH_LIFE = 0.22f;
    private static final int SHARD_CAP = 600;
    private static final double SHARD_RADIUS_SQ = 121.0;
    private static final float SHARD_VIEW_SQ = 484.0f;
    private static final double FOOT_RANGE_SQ = 1600.0;
    private static final double FOOT_STEP_LENGTH = 0.72;
    private static final double FOOT_STEP_SNEAK = 0.45;
    private static final double FOOT_SIDE_OFFSET = 0.11875;
    private static final int RING_SEGMENTS = 18;
    private static final float SEGMENT_ANGLE = 0.34906584f;

    public final void clear() {
        this.drops.clear();
        this.ripples.clear();
        this.shards.clear();
        this.footStates.clear();
    }

    public final void update(@NotNull ClientWorld level, @NotNull Vec3d camPos, float dt, int count, float speedMul, float wetLevel) {
        Intrinsics.checkNotNullParameter((Object)level, (String)"level");
        Intrinsics.checkNotNullParameter((Object)camPos, (String)"camPos");
        int target = Math.min(count, 1600);
        while (this.drops.size() < target) {
            this.drops.add(new Drop());
        }
        while (this.drops.size() > target) {
            this.drops.remove(this.drops.size() - 1);
        }
        Iterator<Drop> iterator = this.drops.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Drop> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Drop drop = (Drop) (iterator2.next());
            if (!drop.getAlive()) {
                this.respawn(drop, level, camPos, true);
                continue;
            }
            drop.setVx(drop.getDriftX());
            drop.setVz(drop.getDriftZ());
            drop.setVy(-drop.getSpeed() * speedMul);
            drop.setX(drop.getX() + (double)(drop.getVx() * dt));
            drop.setY(drop.getY() + (double)(drop.getVy() * dt));
            drop.setZ(drop.getZ() + (double)(drop.getVz() * dt));
            double dxc = drop.getX() - camPos.x;
            double dzc = drop.getZ() - camPos.z;
            if (drop.getY() < drop.getGroundY()) {
                if (dxc * dxc + dzc * dzc < 196.0 && this.ripples.size() < 300) {
                    RainfallRenderer.spawnRipple$default(this, drop.getX(), drop.getGroundY() + 0.02, drop.getZ(), 0.34f + this.random.nextFloat() * 0.26f, 0.7f + this.random.nextFloat() * 0.35f, 0.65f + this.random.nextFloat() * 0.35f, true, 0, 128, null);
                }
                if (dxc * dxc + dzc * dzc < 121.0) {
                    this.spawnShards(drop.getX(), drop.getGroundY() + 0.02, drop.getZ(), 3);
                }
                this.respawn(drop, level, camPos, false);
                continue;
            }
            if (!(drop.getY() > Math.max(camPos.y, drop.getGroundY()) + (double)18.0f + 12.0) && !(dxc * dxc + dzc * dzc > 400.0)) continue;
            this.respawn(drop, level, camPos, false);
        }
        if (wetLevel > 0.12f) {
            this.updateFootsteps(level, camPos, dt);
        } else if (!((Map)this.footStates).isEmpty()) {
            this.footStates.clear();
        }
        Iterator iterator3 = this.ripples.iterator();
        while (iterator3.hasNext()) {
            Ripple ripple = (Ripple)iterator3.next();
            ripple.setAge(ripple.getAge() + dt);
            if (!(ripple.getAge() >= ripple.getLife())) continue;
            iterator3.remove();
            if (this.ripplePool.size() >= 340) continue;
            this.ripplePool.addLast(ripple);
        }
        Iterator shardIterator = this.shards.iterator();
        while (shardIterator.hasNext()) {
            Shard shard = (Shard)shardIterator.next();
            shard.setAge(shard.getAge() + dt);
            shard.setVy(shard.getVy() - 11.5f * dt);
            shard.setX(shard.getX() + (double)(shard.getVx() * dt));
            shard.setY(shard.getY() + (double)(shard.getVy() * dt));
            shard.setZ(shard.getZ() + (double)(shard.getVz() * dt));
            if (!(shard.getAge() >= shard.getLife()) && !(shard.getY() <= shard.getFloorY())) continue;
            shardIterator.remove();
            if (this.shardPool.size() >= 600) continue;
            this.shardPool.addLast(shard);
        }
    }

    private final void spawnShards(double x, double y, double z, int count) {
        for (int i = 0; i < count; ++i) {
            if (this.shards.size() >= 600) {
                return;
            }
            Shard shard = (Shard)this.shardPool.removeFirstOrNull();
            if (shard == null) {
                shard = new Shard();
            }
            Shard shard2 = shard;
            float angle = this.random.nextFloat() * 6.2832f;
            float horiz = 0.5f + this.random.nextFloat() * 1.5f;
            shard2.setX(x);
            shard2.setY(y + 0.015);
            shard2.setZ(z);
            shard2.setVx((float)Math.cos(angle) * horiz);
            shard2.setVz((float)Math.sin(angle) * horiz);
            shard2.setVy(1.4f + this.random.nextFloat() * 2.0f);
            shard2.setAge(0.0f);
            shard2.setLife(0.22f + this.random.nextFloat() * 0.16f);
            shard2.setWidth(0.006f + this.random.nextFloat() * 0.006f);
            shard2.setFloorY(y - 0.03);
            this.shards.addLast(shard2);
        }
    }

    /*
     * WARNING - void declaration
     */
    private final void updateFootsteps(ClientWorld level, Vec3d camPos, float dt) {
        for (AbstractClientPlayerEntity player : level.getPlayers()) {
            Vec3d pos = player.getEntityPos();
            if (pos.squaredDistanceTo(camPos) > 1600.0) continue;
            FootState state = this.footStates.computeIfAbsent(player.getUuid(), k -> new FootState());
            Vec3d prev = state.getPrevPos();
            state.setPrevPos(pos);
            if (prev == null) {
                state.setWasOnGround(player.isOnGround());
                continue;
            }
            double dx = pos.x - prev.x;
            double dz = pos.z - prev.z;
            double dy = pos.y - prev.y;
            boolean onGround = player.isOnGround();
            if (onGround) {
                if (!state.getWasOnGround() && state.getMaxFall() > 0.22) {
                    float impact = Math.min(1.5f, 0.35f + (float)state.getMaxFall() * 1.6f);
                    this.landingSplash(level, (PlayerEntity)player, pos, impact);
                    state.setAccum(0.0);
                }
                state.setMaxFall(0.0);
            } else if (dy < -1.0E-4) {
                state.setMaxFall(Math.max(state.getMaxFall(), -dy));
            }
            state.setWasOnGround(onGround);
            if (!onGround) continue;
            double step = Math.sqrt(dx * dx + dz * dz);
            if (step > 2.0) {
                state.setAccum(0.0);
                continue;
            }
            boolean sneaking = player.isInSneakingPose();
            state.setAccum(state.getAccum() + step);
            if (state.getAccum() < (sneaking ? 0.45 : 0.72)) continue;
            state.setAccum(0.0);
            if (step < 1.0E-4 || player.isTouchingWater() || !this.onExposedSurface(level, pos)) continue;
            double bodyYaw = Math.toRadians(player.bodyYaw);
            double rightX = Math.cos(bodyYaw);
            double rightZ = Math.sin(bodyYaw);
            state.setLeftFoot(!state.getLeftFoot());
            double sideMul = state.getLeftFoot() ? 1.0 : -1.0;
            double fx = pos.x + rightX * 0.11875 * sideMul;
            double fz = pos.z + rightZ * 0.11875 * sideMul;
            boolean sprinting = player.isSprinting();
            float size = sneaking ? 0.085f : (sprinting ? 0.14f : 0.11f);
            float strength = sneaking ? 0.4f : (sprinting ? 1.05f : 0.75f);
            this.spawnRipple(fx, pos.y + 0.02, fz, size, 0.6f, strength, false, 1);
            if (!sprinting) continue;
            this.spawnShards(fx, pos.y + 0.02, fz, 1);
        }
        if (this.footStates.size() > 64) {
            this.footStates.clear();
        }
    }

    private final void landingSplash(ClientWorld level, PlayerEntity player, Vec3d pos, float impact) {
        if (player.isTouchingWater() || !this.onExposedSurface(level, pos)) {
            return;
        }
        double bodyYaw = Math.toRadians(player.bodyYaw);
        double rightX = Math.cos(bodyYaw);
        double rightZ = Math.sin(bodyYaw);
        int side = -1;
        int n = ProgressionUtilKt.getProgressionLastElement((int)-1, (int)1, (int)2);
        if (side <= n) {
            while (true) {
                this.spawnRipple(pos.x + rightX * 0.11875 * (double)side, pos.y + 0.02, pos.z + rightZ * 0.11875 * (double)side, 0.12f + 0.06f * impact, 0.7f, impact, false, 1);
                if (side == n) break;
                side += 2;
            }
        }
        this.spawnShards(pos.x, pos.y + 0.02, pos.z, (int)(2.0f + impact * 3.0f));
    }

    private final boolean onExposedSurface(ClientWorld level, Vec3d pos) {
        int surface = level.getTopY(Heightmap.Type.MOTION_BLOCKING, (int)pos.x, (int)pos.z);
        return pos.y >= (double)surface - 1.5 && pos.y <= (double)surface + 0.6;
    }

    private final void spawnRipple(double x, double y, double z, float maxR, float life, float strength, boolean splash, int shape) {
        while (this.ripples.size() >= 340) {
            this.ripples.removeFirst();
        }
        Ripple ripple = (Ripple)this.ripplePool.removeFirstOrNull();
        if (ripple == null) {
            ripple = new Ripple();
        }
        Ripple ripple2 = ripple;
        ripple2.setX(x);
        ripple2.setY(y);
        ripple2.setZ(z);
        ripple2.setAge(0.0f);
        ripple2.setLife(life);
        ripple2.setMaxR(maxR);
        ripple2.setStrength(strength);
        ripple2.setSplash(splash);
        ripple2.setShape(shape);
        this.ripples.addLast(ripple2);
    }

    static /* synthetic */ void spawnRipple$default(RainfallRenderer rainfallRenderer, double d, double d2, double d3, float f, float f2, float f3, boolean bl, int n, int n2, Object object) {
        if ((n2 & 0x80) != 0) {
            n = 0;
        }
        rainfallRenderer.spawnRipple(d, d2, d3, f, f2, f3, bl, n);
    }

    private final void respawn(Drop drop, ClientWorld level, Vec3d camPos, boolean initial) {
        float angle = this.random.nextFloat() * ((float)Math.PI * 2);
        float radius = (float)Math.sqrt(this.random.nextFloat()) * 16.0f;
        drop.setX(camPos.x + (double)((float)Math.cos(angle) * radius));
        drop.setZ(camPos.z + (double)((float)Math.sin(angle) * radius));
        drop.setSpeed(14.0f + this.random.nextFloat() * 16.0f);
        drop.setLen(drop.getSpeed() * 0.026f + this.random.nextFloat() * 0.08f);
        drop.setWidth(0.0035f + this.random.nextFloat() * 0.0045f);
        drop.setDriftX((this.random.nextFloat() - 0.5f) * 0.12f);
        drop.setDriftZ((this.random.nextFloat() - 0.5f) * 0.12f);
        drop.setGroundY(level.getTopY(Heightmap.Type.MOTION_BLOCKING, (int)drop.getX(), (int)drop.getZ()));
        double base = Math.max(camPos.y, drop.getGroundY());
        drop.setY(base + (double)(initial ? this.random.nextFloat() * 18.0f : 5.0f + this.random.nextFloat() * 13.0f));
        drop.setVx(0.0f);
        drop.setVy(-drop.getSpeed());
        drop.setVz(0.0f);
        drop.setAlive(true);
    }

    public final void render(@NotNull MatrixStack stack, @NotNull VertexConsumerProvider.Immediate provider, @NotNull Vec3d camPos, float dropAlpha, float rippleAlpha, float footAlpha) {
        Iterator iterator;
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)provider, (String)"provider");
        Intrinsics.checkNotNullParameter((Object)camPos, (String)"camPos");
        if (this.drops.isEmpty() && this.ripples.isEmpty()) {
            return;
        }
        RenderLayer renderType = ClientPipelines.WORLD_PARTICLES_COLOR;
        VertexConsumer vertexConsumer2 = provider.getBuffer(renderType);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer consumer = vertexConsumer2;
        stack.push();
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        if (dropAlpha > 0.01f) {
            Iterator iterator2 = this.drops.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator2, (String)"iterator(...)");
            iterator = iterator2;
            while (iterator.hasNext()) {
                float sz;
                float sy;
                float tz;
                float vlen;
                float rz;
                float ry;
                float rx;
                float distSq;
                Object e = iterator.next();
                Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
                Drop drop = (Drop)e;
                if (!drop.getAlive() || (distSq = (rx = (float)(drop.getX() - camPos.x)) * rx + (ry = (float)(drop.getY() - camPos.y)) * ry + (rz = (float)(drop.getZ() - camPos.z)) * rz) < 0.36f) continue;
                float dist = (float)Math.sqrt(distSq);
                float horiz = (float)Math.sqrt(rx * rx + rz * rz);
                float fade = 1.0f - RainfallRenderer.Companion.smoothstep(13.0f, 16.0f, horiz);
                if ((fade *= RainfallRenderer.Companion.smoothstep(0.7f, 1.6f, dist)) <= 0.02f || (vlen = (float)Math.sqrt(drop.getVx() * drop.getVx() + drop.getVy() * drop.getVy() + drop.getVz() * drop.getVz())) < 0.001f) continue;
                float inv = drop.getLen() / vlen;
                float tx = -drop.getVx() * inv;
                float ty = -drop.getVy() * inv;
                float sx = ty * rz - (tz = -drop.getVz() * inv) * ry;
                float sl = (float)Math.sqrt(sx * sx + (sy = tz * rx - tx * rz) * sy + (sz = tx * ry - ty * rx) * sz);
                if (sl < 1.0E-4f) continue;
                float half = drop.getWidth() / sl;
                int a = RangesKt.coerceIn((int)((int)(dropAlpha * fade * 150.0f)), (int)0, (int)255);
                int aTop = (int)((float)a * 0.12f);
                int bottom = a << 24 | 0xBFCEE6;
                int top = aTop << 24 | 0xBFCEE6;
                consumer.vertex(pose, rx - (sx *= half), ry - (sy *= half), rz - (sz *= half)).color(bottom);
                consumer.vertex(pose, rx + sx, ry + sy, rz + sz).color(bottom);
                consumer.vertex(pose, rx + sx + tx, ry + sy + ty, rz + sz + tz).color(top);
                consumer.vertex(pose, rx - sx + tx, ry - sy + ty, rz - sz + tz).color(top);
            }
        }
        if (rippleAlpha > 0.01f || footAlpha > 0.01f) {
            for (Ripple ripple : this.ripples) {
                this.renderRipple(consumer, pose, ripple, camPos, ripple.getShape() == 1 ? footAlpha : rippleAlpha);
            }
        }
        if (dropAlpha > 0.01f) {
            for (Shard shard : this.shards) {
                this.renderShard(consumer, pose, shard, camPos, dropAlpha);
            }
        }
        stack.pop();
        provider.draw(renderType);
    }

    private final void renderShard(VertexConsumer consumer, MatrixStack.Entry pose, Shard shard, Vec3d camPos, float alphaMul) {
        float sz;
        float sy;
        float tz;
        float rz;
        float ry;
        float rx = (float)(shard.getX() - camPos.x);
        float distSq = rx * rx + (ry = (float)(shard.getY() - camPos.y)) * ry + (rz = (float)(shard.getZ() - camPos.z)) * rz;
        if (distSq > 484.0f || distSq < 0.09f) {
            return;
        }
        float t = RangesKt.coerceIn((float)(shard.getAge() / shard.getLife()), (float)0.0f, (float)1.0f);
        float fade = 1.0f - t;
        int a = RangesKt.coerceIn((int)((int)(alphaMul * fade * fade * 235.0f)), (int)0, (int)255);
        if (a <= 3) {
            return;
        }
        float vlen = (float)Math.sqrt(shard.getVx() * shard.getVx() + shard.getVy() * shard.getVy() + shard.getVz() * shard.getVz());
        if (vlen < 0.001f) {
            return;
        }
        float len = 0.035f + vlen * 0.022f;
        float inv = len / vlen;
        float tx = shard.getVx() * inv;
        float ty = shard.getVy() * inv;
        float sx = ty * rz - (tz = shard.getVz() * inv) * ry;
        float sl = (float)Math.sqrt(sx * sx + (sy = tz * rx - tx * rz) * sy + (sz = tx * ry - ty * rx) * sz);
        if (sl < 1.0E-4f) {
            return;
        }
        float half = shard.getWidth() / sl;
        int head = a << 24 | 0xDCE8F8;
        int tail = a / 4 << 24 | 0xDCE8F8;
        consumer.vertex(pose, rx - (sx *= half), ry - (sy *= half), rz - (sz *= half)).color(tail);
        consumer.vertex(pose, rx + sx, ry + sy, rz + sz).color(tail);
        consumer.vertex(pose, rx + sx + tx, ry + sy + ty, rz + sz + tz).color(head);
        consumer.vertex(pose, rx - sx + tx, ry - sy + ty, rz - sz + tz).color(head);
    }

    private final void renderRipple(VertexConsumer consumer, MatrixStack.Entry pose, Ripple ripple, Vec3d camPos, float alphaMul) {
        int spotAlpha;
        float cz;
        float cy;
        float cx = (float)(ripple.getX() - camPos.x);
        float distSq = cx * cx + (cy = (float)(ripple.getY() - camPos.y)) * cy + (cz = (float)(ripple.getZ() - camPos.z)) * cz;
        if (distSq > 676.0f) {
            return;
        }
        float t = RangesKt.coerceIn((float)(ripple.getAge() / ripple.getLife()), (float)0.0f, (float)1.0f);
        float ease = 1.0f - (1.0f - t) * (1.0f - t);
        float fadeT = 1.0f - t;
        float alpha = fadeT * fadeT * ripple.getStrength() * alphaMul * 175.0f;
        if (alpha < 3.0f) {
            return;
        }
        if (ripple.getShape() == 1) {
            float radius = ripple.getMaxR() * (0.3f + 0.7f * ease);
            float width = ripple.getMaxR() * (0.28f - 0.1f * t);
            this.ring(consumer, pose, cx, cy, cz, radius, width, alpha);
            return;
        }
        float radius = ripple.getMaxR() * (0.12f + 0.88f * ease);
        float width = ripple.getMaxR() * (0.24f - 0.1f * t);
        this.ring(consumer, pose, cx, cy, cz, radius, width, alpha);
        if (t > 0.22f) {
            this.ring(consumer, pose, cx, cy, cz, radius * 0.55f, width * 0.8f, alpha * 0.45f);
        }
        if ((spotAlpha = RangesKt.coerceIn((int)((int)((1.0f - t * 0.85f) * ripple.getStrength() * alphaMul * 34.0f)), (int)0, (int)255)) > 2) {
            float spotR = radius + width * 1.6f;
            int spotColor = spotAlpha << 24 | 0xD4E2F4;
            float pc = 1.0f;
            float ps = 0.0f;
            for (int i = 1; i < 19; ++i) {
                float angle = (float)i * 0.34906584f;
                float c = (float)Math.cos(angle);
                float sN = (float)Math.sin(angle);
                consumer.vertex(pose, cx, cy, cz).color(spotColor);
                consumer.vertex(pose, cx, cy, cz).color(spotColor);
                consumer.vertex(pose, cx + c * spotR, cy, cz + sN * spotR).color(13951732);
                consumer.vertex(pose, cx + pc * spotR, cy, cz + ps * spotR).color(13951732);
                pc = c;
                ps = sN;
            }
        }
        if (ripple.getSplash() && ripple.getAge() < 0.22f) {
            float st = ripple.getAge() / 0.22f;
            float h = 0.17f * (float)Math.sin(st * 3.1416f);
            float w = 0.028f;
            int sa = RangesKt.coerceIn((int)((int)((1.0f - st) * ripple.getStrength() * alphaMul * 200.0f)), (int)0, (int)255);
            int col = sa << 24 | 0xDCE8F8;
            int colTop = sa / 4 << 24 | 0xDCE8F8;
            consumer.vertex(pose, cx - w, cy, cz).color(col);
            consumer.vertex(pose, cx + w, cy, cz).color(col);
            consumer.vertex(pose, cx + w, cy + h, cz).color(colTop);
            consumer.vertex(pose, cx - w, cy + h, cz).color(colTop);
            consumer.vertex(pose, cx, cy, cz - w).color(col);
            consumer.vertex(pose, cx, cy, cz + w).color(col);
            consumer.vertex(pose, cx, cy + h, cz + w).color(colTop);
            consumer.vertex(pose, cx, cy + h, cz - w).color(colTop);
        }
    }

    private final void ring(VertexConsumer consumer, MatrixStack.Entry pose, float cx, float cy, float cz, float radius, float width, float alpha) {
        int mid = RangesKt.coerceIn((int)((int)alpha), (int)0, (int)255);
        if (mid <= 0) {
            return;
        }
        int midColor = mid << 24 | 0xD4E2F4;
        int edgeColor = 13951732;
        float inner = Math.max(0.01f, radius - width);
        float outer = radius + width;
        float prevCos = 1.0f;
        float prevSin = 0.0f;
        for (int i = 1; i < 19; ++i) {
            float angle = (float)i * 0.34906584f;
            float c = (float)Math.cos(angle);
            float s = (float)Math.sin(angle);
            consumer.vertex(pose, cx + prevCos * inner, cy, cz + prevSin * inner).color(edgeColor);
            consumer.vertex(pose, cx + c * inner, cy, cz + s * inner).color(edgeColor);
            consumer.vertex(pose, cx + c * radius, cy, cz + s * radius).color(midColor);
            consumer.vertex(pose, cx + prevCos * radius, cy, cz + prevSin * radius).color(midColor);
            consumer.vertex(pose, cx + prevCos * radius, cy, cz + prevSin * radius).color(midColor);
            consumer.vertex(pose, cx + c * radius, cy, cz + s * radius).color(midColor);
            consumer.vertex(pose, cx + c * outer, cy, cz + s * outer).color(edgeColor);
            consumer.vertex(pose, cx + prevCos * outer, cy, cz + prevSin * outer).color(edgeColor);
            prevCos = c;
            prevSin = s;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0006\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\tR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\fR\u0014\u0010\u0012\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\fR\u0014\u0010\u0013\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\fR\u0014\u0010\u0014\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\fR\u0014\u0010\u0015\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\fR\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u000eR\u0014\u0010\u001a\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u000eR\u0014\u0010\u001b\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\fR\u0014\u0010\u001c\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0018R\u0014\u0010\u001d\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u000eR\u0014\u0010\u001e\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u0018R\u0014\u0010\u001f\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u0018R\u0014\u0010 \u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u0018R\u0014\u0010!\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010\u0018R\u0014\u0010\"\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010\fR\u0014\u0010#\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010\u000e\u00a8\u0006$"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/rain/RainfallRenderer.Companion;", "", "<init>", "()V", "", "edge0", "edge1", "value", "smoothstep", "(FFF)F", "", "MAX_DROPS", "I", "RADIUS", "F", "SPAWN_BOTTOM", "SPAWN_TOP", "STREAK_RGB", "HEAD_RGB", "RIPPLE_RGB", "RIPPLE_CAP", "RAIN_RIPPLE_CAP", "", "RIPPLE_RADIUS_SQ", "D", "RIPPLE_VIEW_SQ", "SPLASH_LIFE", "SHARD_CAP", "SHARD_RADIUS_SQ", "SHARD_VIEW_SQ", "FOOT_RANGE_SQ", "FOOT_STEP_LENGTH", "FOOT_STEP_SNEAK", "FOOT_SIDE_OFFSET", "RING_SEGMENTS", "SEGMENT_ANGLE", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float smoothstep(float edge0, float edge1, float value) {
            float t = RangesKt.coerceIn((float)((value - edge0) / (edge1 - edge0)), (float)0.0f, (float)1.0f);
            return t * t * (3.0f - 2.0f * t);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\"\u0010\u000e\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u0006\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\"\u0010\u0012\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0018\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\"\u0010\u001b\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001b\u0010\u0013\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\"\u0010\u001e\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001e\u0010\u0013\u001a\u0004\b\u001f\u0010\u0015\"\u0004\b \u0010\u0017R\"\u0010!\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b!\u0010\u0013\u001a\u0004\b\"\u0010\u0015\"\u0004\b#\u0010\u0017R\"\u0010$\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b$\u0010\u0006\u001a\u0004\b%\u0010\b\"\u0004\b&\u0010\nR\"\u0010(\u001a\u00020'8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\"\u0010.\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b.\u0010\u0013\u001a\u0004\b/\u0010\u0015\"\u0004\b0\u0010\u0017R\"\u00101\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b1\u0010\u0013\u001a\u0004\b2\u0010\u0015\"\u0004\b3\u0010\u0017R\"\u00104\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b4\u0010\u0013\u001a\u0004\b5\u0010\u0015\"\u0004\b6\u0010\u0017\u00a8\u00067"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/rain/RainfallRenderer$Drop;", "", "<init>", "()V", "", "x", "D", "getX", "()D", "setX", "(D)V", "y", "getY", "setY", "z", "getZ", "setZ", "", "speed", "F", "getSpeed", "()F", "setSpeed", "(F)V", "len", "getLen", "setLen", "width", "getWidth", "setWidth", "driftX", "getDriftX", "setDriftX", "driftZ", "getDriftZ", "setDriftZ", "groundY", "getGroundY", "setGroundY", "", "alive", "Z", "getAlive", "()Z", "setAlive", "(Z)V", "vx", "getVx", "setVx", "vy", "getVy", "setVy", "vz", "getVz", "setVz", "rtx.kimiko:kimiko"})
    private static final class Drop {
        private double x;
        private double y;
        private double z;
        private float speed = 20.0f;
        private float len = 0.5f;
        private float width = 0.015f;
        private float driftX;
        private float driftZ;
        private double groundY = Double.NEGATIVE_INFINITY;
        private boolean alive;
        private float vx;
        private float vy = -20.0f;
        private float vz;

        public final double getX() {
            return this.x;
        }

        public final void setX(double d) {
            this.x = d;
        }

        public final double getY() {
            return this.y;
        }

        public final void setY(double d) {
            this.y = d;
        }

        public final double getZ() {
            return this.z;
        }

        public final void setZ(double d) {
            this.z = d;
        }

        public final float getSpeed() {
            return this.speed;
        }

        public final void setSpeed(float f) {
            this.speed = f;
        }

        public final float getLen() {
            return this.len;
        }

        public final void setLen(float f) {
            this.len = f;
        }

        public final float getWidth() {
            return this.width;
        }

        public final void setWidth(float f) {
            this.width = f;
        }

        public final float getDriftX() {
            return this.driftX;
        }

        public final void setDriftX(float f) {
            this.driftX = f;
        }

        public final float getDriftZ() {
            return this.driftZ;
        }

        public final void setDriftZ(float f) {
            this.driftZ = f;
        }

        public final double getGroundY() {
            return this.groundY;
        }

        public final void setGroundY(double d) {
            this.groundY = d;
        }

        public final boolean getAlive() {
            return this.alive;
        }

        public final void setAlive(boolean bl) {
            this.alive = bl;
        }

        public final float getVx() {
            return this.vx;
        }

        public final void setVx(float f) {
            this.vx = f;
        }

        public final float getVy() {
            return this.vy;
        }

        public final void setVy(float f) {
            this.vy = f;
        }

        public final float getVz() {
            return this.vz;
        }

        public final void setVz(float f) {
            this.vz = f;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R\"\u0010\u001c\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001c\u0010\r\u001a\u0004\b\u001d\u0010\u000f\"\u0004\b\u001e\u0010\u0011\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/rain/RainfallRenderer$FootState;", "", "<init>", "()V", "Lnet/minecraft/Vec3d;", "prevPos", "Lnet/minecraft/Vec3d;", "getPrevPos", "()Lnet/minecraft/Vec3d;", "setPrevPos", "(Lnet/minecraft/Vec3d;)V", "", "accum", "D", "getAccum", "()D", "setAccum", "(D)V", "", "leftFoot", "Z", "getLeftFoot", "()Z", "setLeftFoot", "(Z)V", "wasOnGround", "getWasOnGround", "setWasOnGround", "maxFall", "getMaxFall", "setMaxFall", "rtx.kimiko:kimiko"})
    private static final class FootState {
        @Nullable
        private Vec3d prevPos;
        private double accum;
        private boolean leftFoot;
        private boolean wasOnGround = true;
        private double maxFall;

        @Nullable
        public final Vec3d getPrevPos() {
            return this.prevPos;
        }

        public final void setPrevPos(@Nullable Vec3d vec3d2) {
            this.prevPos = vec3d2;
        }

        public final double getAccum() {
            return this.accum;
        }

        public final void setAccum(double d) {
            this.accum = d;
        }

        public final boolean getLeftFoot() {
            return this.leftFoot;
        }

        public final void setLeftFoot(boolean bl) {
            this.leftFoot = bl;
        }

        public final boolean getWasOnGround() {
            return this.wasOnGround;
        }

        public final void setWasOnGround(boolean bl) {
            this.wasOnGround = bl;
        }

        public final double getMaxFall() {
            return this.maxFall;
        }

        public final void setMaxFall(double d) {
            this.maxFall = d;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\"\u0010\u000e\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u0006\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\"\u0010\u0012\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0018\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\"\u0010\u001b\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001b\u0010\u0013\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\"\u0010\u001e\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001e\u0010\u0013\u001a\u0004\b\u001f\u0010\u0015\"\u0004\b \u0010\u0017R\"\u0010\"\u001a\u00020!8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\"\u0010)\u001a\u00020(8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.\u00a8\u0006/"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/rain/RainfallRenderer$Ripple;", "", "<init>", "()V", "", "x", "D", "getX", "()D", "setX", "(D)V", "y", "getY", "setY", "z", "getZ", "setZ", "", "age", "F", "getAge", "()F", "setAge", "(F)V", "life", "getLife", "setLife", "maxR", "getMaxR", "setMaxR", "strength", "getStrength", "setStrength", "", "splash", "Z", "getSplash", "()Z", "setSplash", "(Z)V", "", "shape", "I", "getShape", "()I", "setShape", "(I)V", "rtx.kimiko:kimiko"})
    private static final class Ripple {
        private double x;
        private double y;
        private double z;
        private float age;
        private float life = 0.85f;
        private float maxR = 0.45f;
        private float strength = 1.0f;
        private boolean splash = true;
        private int shape;

        public final double getX() {
            return this.x;
        }

        public final void setX(double d) {
            this.x = d;
        }

        public final double getY() {
            return this.y;
        }

        public final void setY(double d) {
            this.y = d;
        }

        public final double getZ() {
            return this.z;
        }

        public final void setZ(double d) {
            this.z = d;
        }

        public final float getAge() {
            return this.age;
        }

        public final void setAge(float f) {
            this.age = f;
        }

        public final float getLife() {
            return this.life;
        }

        public final void setLife(float f) {
            this.life = f;
        }

        public final float getMaxR() {
            return this.maxR;
        }

        public final void setMaxR(float f) {
            this.maxR = f;
        }

        public final float getStrength() {
            return this.strength;
        }

        public final void setStrength(float f) {
            this.strength = f;
        }

        public final boolean getSplash() {
            return this.splash;
        }

        public final void setSplash(boolean bl) {
            this.splash = bl;
        }

        public final int getShape() {
            return this.shape;
        }

        public final void setShape(int n) {
            this.shape = n;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0019\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\"\u0010\u000e\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u0006\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\"\u0010\u0012\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0018\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\"\u0010\u001b\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001b\u0010\u0013\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\"\u0010\u001e\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001e\u0010\u0013\u001a\u0004\b\u001f\u0010\u0015\"\u0004\b \u0010\u0017R\"\u0010!\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b!\u0010\u0013\u001a\u0004\b\"\u0010\u0015\"\u0004\b#\u0010\u0017R\"\u0010$\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b$\u0010\u0013\u001a\u0004\b%\u0010\u0015\"\u0004\b&\u0010\u0017R\"\u0010'\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b'\u0010\u0006\u001a\u0004\b(\u0010\b\"\u0004\b)\u0010\n\u00a8\u0006*"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/rain/RainfallRenderer$Shard;", "", "<init>", "()V", "", "x", "D", "getX", "()D", "setX", "(D)V", "y", "getY", "setY", "z", "getZ", "setZ", "", "vx", "F", "getVx", "()F", "setVx", "(F)V", "vy", "getVy", "setVy", "vz", "getVz", "setVz", "age", "getAge", "setAge", "life", "getLife", "setLife", "width", "getWidth", "setWidth", "floorY", "getFloorY", "setFloorY", "rtx.kimiko:kimiko"})
    private static final class Shard {
        private double x;
        private double y;
        private double z;
        private float vx;
        private float vy;
        private float vz;
        private float age;
        private float life = 0.3f;
        private float width = 0.01f;
        private double floorY;

        public final double getX() {
            return this.x;
        }

        public final void setX(double d) {
            this.x = d;
        }

        public final double getY() {
            return this.y;
        }

        public final void setY(double d) {
            this.y = d;
        }

        public final double getZ() {
            return this.z;
        }

        public final void setZ(double d) {
            this.z = d;
        }

        public final float getVx() {
            return this.vx;
        }

        public final void setVx(float f) {
            this.vx = f;
        }

        public final float getVy() {
            return this.vy;
        }

        public final void setVy(float f) {
            this.vy = f;
        }

        public final float getVz() {
            return this.vz;
        }

        public final void setVz(float f) {
            this.vz = f;
        }

        public final float getAge() {
            return this.age;
        }

        public final void setAge(float f) {
            this.age = f;
        }

        public final float getLife() {
            return this.life;
        }

        public final void setLife(float f) {
            this.life = f;
        }

        public final float getWidth() {
            return this.width;
        }

        public final void setWidth(float f) {
            this.width = f;
        }

        public final double getFloorY() {
            return this.floorY;
        }

        public final void setFloorY(double d) {
            this.floorY = d;
        }
    }
}

