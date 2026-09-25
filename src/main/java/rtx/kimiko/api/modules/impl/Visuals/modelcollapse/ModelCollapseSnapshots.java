/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.modelcollapse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Visuals.trails.TrailEchoCapture;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u00014B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\n\u0010\u0003J1\u0010\u0012\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00100\u000f\u00a2\u0006\u0004\b\u0012\u0010\u0013J/\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00142\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00100\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001d\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001c\u001a\u00020\u0017\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010 \u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b \u0010!R \u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010$R \u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00140\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010$R \u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00140\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010$R\u0014\u0010'\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010(R\u0014\u0010*\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010(R\u0014\u0010+\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010(R\u0014\u0010,\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010(R\u0014\u0010-\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u0010.R\u0014\u00100\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0014\u00102\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u00103\u00a8\u00065"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSnapshots;", "", "<init>", "()V", "Lnet/minecraft/LivingEntity;", "entity", "", "Lkotlin/jvm/JvmStatic;", "noteAttack", "(Lnet/minecraft/LivingEntity;)V", "clear", "", "partialTick", "", "maxDistance", "Lkotlin/Function1;", "", "filter", "tick", "(FDLkotlin/jvm/functions/Function1;)V", "", "maxAgeMs", "Lkotlin/Function2;", "", "Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSnapshots$Snapshot;", "handler", "collectVanished", "(JLkotlin/jvm/functions/Function2;)V", "entityId", "take", "(I)Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSnapshots$Snapshot;", "now", "inDanger", "(Lnet/minecraft/LivingEntity;J)Z", "Ljava/util/HashMap;", "snapshots", "Ljava/util/HashMap;", "lastCapture", "attacked", "CAPTURE_INTERVAL_MS", "J", "IDLE_INTERVAL_MS", "SNAPSHOT_TTL_MS", "SNAPSHOT_USE_MS", "ATTACK_MEMORY_MS", "MAX_TRACKED", "I", "MAX_CAPTURES_PER_FRAME", "SNAPSHOT_RANGE", "D", "DANGER_HEALTH", "F", "Snapshot", "rtx.kimiko:kimiko"})
public final class ModelCollapseSnapshots {
    @NotNull
    public static final ModelCollapseSnapshots INSTANCE = new ModelCollapseSnapshots();
    @NotNull
    private static final HashMap<Integer, Snapshot> snapshots = new HashMap();
    @NotNull
    private static final HashMap<Integer, Long> lastCapture = new HashMap();
    @NotNull
    private static final HashMap<Integer, Long> attacked = new HashMap();
    private static final long CAPTURE_INTERVAL_MS = 100L;
    private static final long IDLE_INTERVAL_MS = 400L;
    private static final long SNAPSHOT_TTL_MS = 2500L;
    private static final long SNAPSHOT_USE_MS = 1200L;
    private static final long ATTACK_MEMORY_MS = 2000L;
    private static final int MAX_TRACKED = 8;
    private static final int MAX_CAPTURES_PER_FRAME = 2;
    private static final double SNAPSHOT_RANGE = 32.0;
    private static final float DANGER_HEALTH = 0.6f;

    private ModelCollapseSnapshots() {
    }

    @JvmStatic
    public static final void noteAttack(@NotNull LivingEntity entity) {
        Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
        ((Map)attacked).put(entity.getId(), System.currentTimeMillis());
    }

    @JvmStatic
    public static final void clear() {
        snapshots.clear();
        lastCapture.clear();
        attacked.clear();
    }

    public final void tick(float partialTick, double maxDistance, @NotNull Function1<? super LivingEntity, Boolean> filter) {
        Intrinsics.checkNotNullParameter(filter, (String)"filter");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        ClientWorld clientWorld3 = minecraft.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        ClientPlayerEntity clientPlayerEntity2 = minecraft.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        long now = System.currentTimeMillis();
        snapshots.entrySet().removeIf(e -> now - e.getValue().getTakenMs() > 2500L);
        lastCapture.entrySet().removeIf(e -> now - e.getValue() > 2500L);
        attacked.entrySet().removeIf(e -> now - e.getValue() > 2000L);
        double range = Math.min(maxDistance, 32.0);
        double maxSq = range * range;
        int taken = 0;
        for (Entity entity : level.getEntities()) {
            if (taken >= 2) break;
            if (!(entity instanceof LivingEntity) || entity == player) continue;
            LivingEntity living = (LivingEntity)entity;
            if (living.isRemoved() || !living.isAlive() || entity.squaredDistanceTo((Entity)player) > maxSq) continue;
            if (!filter.invoke(living).booleanValue()) continue;
            boolean danger = this.inDanger(living, now);
            if (!danger && snapshots.size() >= 8 && !snapshots.containsKey(living.getId())) continue;
            Long last = lastCapture.get(living.getId());
            if (last != null && now - last < (danger ? 100L : 400L)) continue;
            lastCapture.put(living.getId(), now);
            List<TrailEchoCapture.EchoBox> boxes = TrailEchoCapture.capture(living, partialTick, false);
            if (boxes.isEmpty()) continue;
            Vec3d motion = living.getVelocity();
            snapshots.put(living.getId(), new Snapshot(boxes, MathHelper.lerp((double)partialTick, (double)entity.lastRenderX, (double)living.getX()), MathHelper.lerp((double)partialTick, (double)entity.lastRenderY, (double)living.getY()), MathHelper.lerp((double)partialTick, (double)entity.lastRenderZ, (double)living.getZ()), (float)motion.x, (float)motion.y, (float)motion.z, living.getHeight(), now, danger));
            ++taken;
        }
    }

    public final void collectVanished(long maxAgeMs, @NotNull Function2<? super Integer, ? super Snapshot, Boolean> handler) {
        Intrinsics.checkNotNullParameter(handler, (String)"handler");
        if (snapshots.isEmpty()) {
            return;
        }
        ClientWorld clientWorld3 = MinecraftClient.getInstance().world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        long now = System.currentTimeMillis();
        Iterator<Map.Entry<Integer, Snapshot>> iterator = snapshots.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Snapshot> entry = iterator.next();
            if (now - entry.getValue().getTakenMs() > maxAgeMs || !entry.getValue().getDanger()) continue;
            int id = entry.getKey();
            Entity entity = level.getEntityById(id);
            boolean gone = entity == null || entity.isRemoved() || (entity instanceof LivingEntity && !((LivingEntity)entity).isAlive());
            if (!gone) continue;
            Snapshot snapshot = entry.getValue();
            if (!handler.invoke(id, snapshot).booleanValue()) continue;
            iterator.remove();
            lastCapture.remove(id);
        }
    }

    @Nullable
    public final Snapshot take(int entityId) {
        Snapshot snapshot = snapshots.remove(entityId);
        if (snapshot == null) {
            return null;
        }
        Snapshot snapshot2 = snapshot;
        lastCapture.remove(entityId);
        if (System.currentTimeMillis() - snapshot2.getTakenMs() > 1200L) {
            return null;
        }
        return snapshot2;
    }

    private final boolean inDanger(LivingEntity entity, long now) {
        if (entity.hurtTime > 0) {
            return true;
        }
        float max = entity.getMaxHealth();
        if (max > 0.0f && entity.getHealth() <= max * 0.6f) {
            return true;
        }
        Long l = attacked.get(entity.getId());
        if (l == null) {
            return false;
        }
        long hit = l;
        return now - hit <= 2000L;
    }

    private static final boolean tick$lambda$0(long $now, Map.Entry entry) {
        Intrinsics.checkNotNullParameter((Object)entry, (String)"entry");
        return $now - ((Snapshot)entry.getValue()).getTakenMs() > 2500L;
    }

    private static final boolean tick$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final boolean tick$lambda$2(long $now, Map.Entry entry) {
        Intrinsics.checkNotNullParameter((Object)entry, (String)"entry");
        Object v = entry.getValue();
        Intrinsics.checkNotNullExpressionValue(v, (String)"<get-value>(...)");
        return $now - ((Number)v).longValue() > 2500L;
    }

    private static final boolean tick$lambda$3(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final boolean tick$lambda$4(long $now, Map.Entry entry) {
        Intrinsics.checkNotNullParameter((Object)entry, (String)"entry");
        Object v = entry.getValue();
        Intrinsics.checkNotNullExpressionValue(v, (String)"<get-value>(...)");
        return $now - ((Number)v).longValue() > 2000L;
    }

    private static final boolean tick$lambda$5(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\u0018\u00002\u00020\u0001B]\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0007\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0017\u001a\u0004\b\u001a\u0010\u0019R\u0017\u0010\b\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0017\u001a\u0004\b\u001b\u0010\u0019R\u0017\u0010\n\u001a\u00020\t8\u0006\u00a2\u0006\f\n\u0004\b\n\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u000b\u001a\u00020\t8\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\u001c\u001a\u0004\b\u001f\u0010\u001eR\u0017\u0010\f\u001a\u00020\t8\u0006\u00a2\u0006\f\n\u0004\b\f\u0010\u001c\u001a\u0004\b \u0010\u001eR\u0017\u0010\r\u001a\u00020\t8\u0006\u00a2\u0006\f\n\u0004\b\r\u0010\u001c\u001a\u0004\b!\u0010\u001eR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010\"\u001a\u0004\b#\u0010$R\u0017\u0010\u0011\u001a\u00020\u00108\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010%\u001a\u0004\b&\u0010'\u00a8\u0006("}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSnapshots$Snapshot;", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$EchoBox;", "boxes", "", "x", "y", "z", "", "motionX", "motionY", "motionZ", "height", "", "takenMs", "", "danger", "<init>", "(Ljava/util/List;DDDFFFFJZ)V", "Ljava/util/List;", "getBoxes", "()Ljava/util/List;", "D", "getX", "()D", "getY", "getZ", "F", "getMotionX", "()F", "getMotionY", "getMotionZ", "getHeight", "J", "getTakenMs", "()J", "Z", "getDanger", "()Z", "rtx.kimiko:kimiko"})
    public static final class Snapshot {
        @NotNull
        private final List<TrailEchoCapture.EchoBox> boxes;
        private final double x;
        private final double y;
        private final double z;
        private final float motionX;
        private final float motionY;
        private final float motionZ;
        private final float height;
        private final long takenMs;
        private final boolean danger;

        public Snapshot(@NotNull List<TrailEchoCapture.EchoBox> boxes, double x, double y, double z, float motionX, float motionY, float motionZ, float height, long takenMs, boolean danger) {
            Intrinsics.checkNotNullParameter(boxes, (String)"boxes");
            this.boxes = boxes;
            this.x = x;
            this.y = y;
            this.z = z;
            this.motionX = motionX;
            this.motionY = motionY;
            this.motionZ = motionZ;
            this.height = height;
            this.takenMs = takenMs;
            this.danger = danger;
        }

        @NotNull
        public final List<TrailEchoCapture.EchoBox> getBoxes() {
            return this.boxes;
        }

        public final double getX() {
            return this.x;
        }

        public final double getY() {
            return this.y;
        }

        public final double getZ() {
            return this.z;
        }

        public final float getMotionX() {
            return this.motionX;
        }

        public final float getMotionY() {
            return this.motionY;
        }

        public final float getMotionZ() {
            return this.motionZ;
        }

        public final float getHeight() {
            return this.height;
        }

        public final long getTakenMs() {
            return this.takenMs;
        }

        public final boolean getDanger() {
            return this.danger;
        }
    }
}

