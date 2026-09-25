/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ExperienceOrbEntity
 *  net.minecraft.entity.EquipmentSlot
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ItemEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Box
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.entity.death;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 @2\u00020\u0001:\u0004ABC@B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0004\b\t\u0010\nJ-\u0010\u0011\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0015\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\b\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u001c\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001a\u00a2\u0006\u0004\b\u001c\u0010\u001dJ?\u0010&\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 2\u0006\u0010#\u001a\u00020\"2\u0006\u0010%\u001a\u00020$2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b&\u0010'J'\u0010*\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020(2\u0006\u0010%\u001a\u00020$H\u0002\u00a2\u0006\u0004\b*\u0010+J'\u0010-\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\"2\u0006\u0010%\u001a\u00020$H\u0002\u00a2\u0006\u0004\b-\u0010.R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010/R0\u00102\u001a\u001e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\"00j\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\"`18\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00103R$\u00106\u001a\u0012\u0012\u0004\u0012\u00020(04j\b\u0012\u0004\u0012\u00020(`58\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b6\u00107R0\u00108\u001a\u001e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020$00j\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020$`18\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00103R0\u00109\u001a\u001e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020$00j\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020$`18\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u00103R$\u0010;\u001a\u0012\u0012\u0004\u0012\u00020:04j\b\u0012\u0004\u0012\u00020:`58\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u00107R\u0018\u0010<\u001a\u0004\u0018\u00010\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0016\u0010>\u001a\u00020$8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010?\u00a8\u0006D"}, d2={"Lrtx/kimiko/utils/entity/death/EntityDeathDetector;", "", "Ljava/util/function/Consumer;", "Lnet/minecraft/LivingEntity;", "onKill", "<init>", "(Ljava/util/function/Consumer;)V", "target", "", "onAttack", "(Lnet/minecraft/LivingEntity;)V", "Lnet/minecraft/Identifier;", "sound", "", "x", "y", "z", "onServerSound", "(Lnet/minecraft/Identifier;DDD)V", "", "entityId", "onServerEntitySound", "(Lnet/minecraft/Identifier;I)V", "clear", "()V", "maxDistance", "", "aggressive", "tick", "(DZ)V", "Lnet/minecraft/ClientWorld;", "level", "Lnet/minecraft/ClientPlayerEntity;", "player", "Lrtx/kimiko/utils/entity/death/EntityDeathDetector$Snapshot;", "snap", "", "now", "onEntityGone", "(Lnet/minecraft/ClientWorld;Lnet/minecraft/ClientPlayerEntity;Lrtx/kimiko/utils/entity/death/EntityDeathDetector$Snapshot;JDZ)V", "Lrtx/kimiko/utils/entity/death/EntityDeathDetector$Candidate;", "candidate", "isConfirmed", "(Lnet/minecraft/ClientWorld;Lrtx/kimiko/utils/entity/death/EntityDeathDetector$Candidate;J)Z", "entity", "checkSoftKill", "(Lnet/minecraft/LivingEntity;Lrtx/kimiko/utils/entity/death/EntityDeathDetector$Snapshot;J)Z", "Ljava/util/function/Consumer;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "snapshots", "Ljava/util/HashMap;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "candidates", "Ljava/util/ArrayList;", "myHits", "recentEntitySounds", "Lrtx/kimiko/utils/entity/death/EntityDeathDetector$DeathSound;", "recentDeathSounds", "lastLevel", "Ljava/lang/Object;", "tickCounter", "J", "Companion", "Snapshot", "Candidate", "DeathSound", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nEntityDeathDetector.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EntityDeathDetector.kt\nrtx/kimiko/utils/entity/death/EntityDeathDetector\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,317:1\n460#2,7:318\n*S KotlinDebug\n*F\n+ 1 EntityDeathDetector.kt\nrtx/kimiko/utils/entity/death/EntityDeathDetector\n*L\n82#1:318,7\n*E\n"})
public final class EntityDeathDetector {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Consumer<LivingEntity> onKill;
    @NotNull
    private final HashMap<Integer, Snapshot> snapshots;
    @NotNull
    private final ArrayList<Candidate> candidates;
    @NotNull
    private final HashMap<Integer, Long> myHits;
    @NotNull
    private final HashMap<Integer, Long> recentEntitySounds;
    @NotNull
    private final ArrayList<DeathSound> recentDeathSounds;
    @Nullable
    private Object lastLevel;
    private long tickCounter;
    private static final long MY_HIT_WINDOW_MS = 900L;
    private static final long HURT_WINDOW_MS = 600L;
    private static final long SOFT_KILL_HIT_WINDOW_MS = 1500L;
    private static final long SOFT_KILL_HURT_WINDOW_MS = 1200L;
    private static final long SIGNAL_MEMORY_MS = 1200L;
    private static final long CANDIDATE_TTL_MS = 1000L;
    private static final float LOW_HP = 8.0f;
    private static final double TELEPORT_JUMP_SQ = 64.0;
    private static final int FRESH_DROP_TICKS = 15;
    private static final int MAX_CANDIDATES = 16;
    @NotNull
    private static final EquipmentSlot[] GEAR_SLOTS;

    public EntityDeathDetector(@NotNull Consumer<LivingEntity> onKill) {
        Intrinsics.checkNotNullParameter(onKill, (String)"onKill");
        this.onKill = onKill;
        this.snapshots = new HashMap();
        this.candidates = new ArrayList();
        this.myHits = new HashMap();
        this.recentEntitySounds = new HashMap();
        this.recentDeathSounds = new ArrayList();
    }

    public final void onAttack(@NotNull LivingEntity target) {
        Intrinsics.checkNotNullParameter((Object)target, (String)"target");
        ((Map)this.myHits).put(target.getId(), System.currentTimeMillis());
    }

    public final void onServerSound(@NotNull Identifier sound, double x, double y, double z) {
        Intrinsics.checkNotNullParameter((Object)sound, (String)"sound");
        String string = sound.getPath();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getPath(...)");
        if (!String.valueOf(string).contains("death")) {
            return;
        }
        long now = System.currentTimeMillis();
        if (this.recentDeathSounds.size() >= 24) {
            this.recentDeathSounds.remove(0);
        }
        this.recentDeathSounds.add(new DeathSound(x, y, z, now));
    }

    public final void onServerEntitySound(@NotNull Identifier sound, int entityId) {
        Intrinsics.checkNotNullParameter((Object)sound, (String)"sound");
        String string = sound.getPath();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getPath(...)");
        if (!String.valueOf(string).contains("death")) {
            return;
        }
        ((Map)this.recentEntitySounds).put(entityId, System.currentTimeMillis());
    }

    public final void clear() {
        this.snapshots.clear();
        this.candidates.clear();
        this.myHits.clear();
        this.recentEntitySounds.clear();
        this.recentDeathSounds.clear();
        this.lastLevel = null;
    }

    /*
     * WARNING - void declaration
     */
    public final void tick(double maxDistance, boolean aggressive) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        ClientWorld level = minecraft.world;
        ClientPlayerEntity player = minecraft.player;
        if (level == null || player == null) {
            this.clear();
            return;
        }
        if (this.lastLevel != level) {
            this.clear();
            this.lastLevel = level;
        }
        long now = System.currentTimeMillis();
        this.myHits.values().removeIf(it -> now - it > 1000L);
        this.recentEntitySounds.values().removeIf(at -> now - at > 1200L);
        this.recentDeathSounds.removeIf(sound -> now - sound.getTime() > 1200L);
        ++this.tickCounter;
        long tick = this.tickCounter;
        double scanRange = maxDistance + 8.0;
        for (Object t : level.getEntities()) {
            Snapshot snap;
            Object object;
Map $this$getOrPut$iv = this.snapshots;
            Intrinsics.checkNotNullExpressionValue(t, (String)"next(...)");
            Entity entity = (Entity)t;
            if (!(entity instanceof LivingEntity) || entity == player || entity.squaredDistanceTo((Entity)player) > scanRange * scanRange) continue;
            Map map = this.snapshots;
            Integer key$iv = ((LivingEntity)entity).getId();
            boolean $i$f$getOrPut = false;
            Object value$iv = $this$getOrPut$iv.get(key$iv);
            if (value$iv == null) {
                boolean bl = false;
                Snapshot answer$iv = new Snapshot((LivingEntity)entity);
                $this$getOrPut$iv.put(key$iv, answer$iv);
                object = answer$iv;
            } else {
                object = value$iv;
            }
            boolean existed = (snap = (Snapshot)object).getLastSeenTick() != 0L;
            snap.update((LivingEntity)entity, now, tick);
            if (!existed || !this.checkSoftKill((LivingEntity)entity, snap, now)) continue;
            this.onKill.accept((LivingEntity)entity);
        }
        Iterator<Snapshot> snapshotIterator = this.snapshots.values().iterator();
        while (snapshotIterator.hasNext()) {
            boolean gone;
            Snapshot snap = (Snapshot) (snapshotIterator.next());
            if (snap.getLastSeenTick() == tick) continue;
            boolean bl = gone = snap.getEntity().isRemoved() || level.getEntityById(snap.getEntity().getId()) == null;
            if (!gone) {
                if (tick - snap.getLastSeenTick() <= 3L) continue;
                snapshotIterator.remove();
                continue;
            }
            snapshotIterator.remove();
            this.onEntityGone(level, player, snap, now, maxDistance, aggressive);
        }
        Iterator<Candidate> iterator = this.candidates.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Candidate> candidateIterator = iterator;
        while (candidateIterator.hasNext()) {
            Candidate candidate = (Candidate) (candidateIterator.next());
            if (now - candidate.getRemovedMs() > 1000L) {
                candidateIterator.remove();
                continue;
            }
            if (!this.isConfirmed(level, candidate, now)) continue;
            candidateIterator.remove();
            this.onKill.accept(candidate.getEntity());
        }
    }

    private final void onEntityGone(ClientWorld level, ClientPlayerEntity player, Snapshot snap, long now, double maxDistance, boolean aggressive) {
        boolean evidence;
        LivingEntity entity = snap.getEntity();
        if (entity.isDead() || entity.getHealth() <= 0.0f || entity.deathTime > 0) {
            this.onKill.accept(entity);
            return;
        }
        if (snap.jumpedFar()) {
            return;
        }
        if (!level.isChunkLoaded(BlockPos.ofFloored((double)snap.getX(), (double)snap.getY(), (double)snap.getZ()))) {
            return;
        }
        if (player.squaredDistanceTo(snap.getX(), snap.getY(), snap.getZ()) > maxDistance * maxDistance) {
            return;
        }
        boolean hurtRecently = now - snap.getLastHurtMs() <= 600L;
        boolean lowHealth = snap.getHealth() <= 8.0f;
        Long hitAt = this.myHits.get(entity.getId());
        boolean myHit = hitAt != null && now - hitAt <= 900L;
        boolean bl = evidence = hurtRecently || lowHealth || myHit;
        if (hurtRecently && lowHealth) {
            this.onKill.accept(entity);
            return;
        }
        if (aggressive && myHit) {
            this.onKill.accept(entity);
            return;
        }
        Candidate candidate = new Candidate(entity, snap.getX(), snap.getY(), snap.getZ(), now, evidence);
        if (this.isConfirmed(level, candidate, now)) {
            this.onKill.accept(entity);
            return;
        }
        if (this.candidates.size() >= 16) {
            this.candidates.remove(0);
        }
        this.candidates.add(candidate);
    }

    private final boolean isConfirmed(ClientWorld level, Candidate candidate, long now) {
        PlayerEntity replacement;
        PlayerEntity original;
        LivingEntity soundEvent;
        Long soundAt = this.recentEntitySounds.get(candidate.getEntity().getId());
        if (soundAt != null && Math.abs(now - soundAt) <= 1200L) {
            return true;
        }
        double soundRange = candidate.getEvidence() ? 8.0 : 4.0;
        for (DeathSound sound : this.recentDeathSounds) {
            double dx = sound.getX() - candidate.getX();
            double dy = sound.getY() - candidate.getY();
            double dz = sound.getZ() - candidate.getZ();
            if (dx * dx + dy * dy + dz * dz <= soundRange * soundRange) {
                return true;
            }
        }
        soundEvent = candidate.getEntity();
        PlayerEntity playerEntity2 = original = soundEvent instanceof PlayerEntity ? (PlayerEntity)soundEvent : null;
        if (original != null && (replacement = level.getPlayerByUuid(original.getUuid())) != null && replacement != original) {
            return true;
        }
        Box orbBox = new Box(candidate.getX() - 5.0, candidate.getY() - 3.0, candidate.getZ() - 5.0, candidate.getX() + 5.0, candidate.getY() + 4.0, candidate.getZ() + 5.0);
        for (Object e : level.getNonSpectatingEntities(ExperienceOrbEntity.class, orbBox)) {
            Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
            ExperienceOrbEntity orb = (ExperienceOrbEntity)e;
            if (orb.age > 15) continue;
            return true;
        }
        if (candidate.getEvidence()) {
            Box itemBox = new Box(candidate.getX() - 4.0, candidate.getY() - 3.0, candidate.getZ() - 4.0, candidate.getX() + 4.0, candidate.getY() + 4.0, candidate.getZ() + 4.0);
            for (Object e : level.getNonSpectatingEntities(ItemEntity.class, itemBox)) {
                Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
                ItemEntity item = (ItemEntity)e;
                if (item.age > 15) continue;
                return true;
            }
        }
        return false;
    }

    private final boolean checkSoftKill(LivingEntity entity, Snapshot snap, long now) {
        boolean jumped;
        boolean hurtRecently;
        Long hitAt = this.myHits.get(entity.getId());
        boolean myHit = hitAt != null && now - hitAt <= 1500L;
        boolean bl = hurtRecently = snap.getLastHurtMs() > 0L && now - snap.getLastHurtMs() <= 1200L;
        if (!myHit && !hurtRecently) {
            return false;
        }
        float max = Math.max(1.0f, snap.getMaxHealth());
        boolean bl2 = jumped = snap.getPrevHealth() <= max * 0.4f && snap.getHealth() >= max * 0.85f && snap.getHealth() - snap.getPrevHealth() >= max * 0.5f;
        if (jumped) {
            return true;
        }
        return snap.getPrevGear() >= 2 && snap.getGearCount() == 0;
    }

    private static final boolean tick$lambda$0(long $now, Long at) {
        Intrinsics.checkNotNullParameter((Object)at, (String)"at");
        return $now - at > 900L;
    }

    private static final boolean tick$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final boolean tick$lambda$2(long $now, Long at) {
        Intrinsics.checkNotNullParameter((Object)at, (String)"at");
        return $now - at > 1200L;
    }

    private static final boolean tick$lambda$3(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final boolean tick$lambda$4(long $now, DeathSound soundEvent) {
        Intrinsics.checkNotNullParameter((Object)soundEvent, (String)"soundEvent");
        return $now - soundEvent.getTime() > 1200L;
    }

    private static final boolean tick$lambda$5(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    static {
        EquipmentSlot[] class_1304Array = new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND, EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
        GEAR_SLOTS = class_1304Array;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\b\u0002\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\f\u0010\rR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0011\u001a\u0004\b\u0014\u0010\u0013R\u0017\u0010\u0007\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u000b\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/entity/death/EntityDeathDetector$Candidate;", "", "Lnet/minecraft/LivingEntity;", "entity", "", "x", "y", "z", "", "removedMs", "", "evidence", "<init>", "(Lnet/minecraft/LivingEntity;DDDJZ)V", "Lnet/minecraft/LivingEntity;", "getEntity", "()Lnet/minecraft/LivingEntity;", "D", "getX", "()D", "getY", "getZ", "J", "getRemovedMs", "()J", "Z", "getEvidence", "()Z", "rtx.kimiko:kimiko"})
    private static final class Candidate {
        @NotNull
        private final LivingEntity entity;
        private final double x;
        private final double y;
        private final double z;
        private final long removedMs;
        private final boolean evidence;

        public Candidate(@NotNull LivingEntity entity, double x, double y, double z, long removedMs, boolean evidence) {
            Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
            this.entity = entity;
            this.x = x;
            this.y = y;
            this.z = z;
            this.removedMs = removedMs;
            this.evidence = evidence;
        }

        @NotNull
        public final LivingEntity getEntity() {
            return this.entity;
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

        public final long getRemovedMs() {
            return this.removedMs;
        }

        public final boolean getEvidence() {
            return this.evidence;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\u000bR\u0014\u0010\r\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000bR\u0014\u0010\u000f\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000bR\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0018R\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001d\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/utils/entity/death/EntityDeathDetector.Companion;", "", "<init>", "()V", "Lnet/minecraft/LivingEntity;", "entity", "", "countGear", "(Lnet/minecraft/LivingEntity;)I", "", "MY_HIT_WINDOW_MS", "J", "HURT_WINDOW_MS", "SOFT_KILL_HIT_WINDOW_MS", "SOFT_KILL_HURT_WINDOW_MS", "SIGNAL_MEMORY_MS", "CANDIDATE_TTL_MS", "", "LOW_HP", "F", "", "TELEPORT_JUMP_SQ", "D", "FRESH_DROP_TICKS", "I", "MAX_CANDIDATES", "", "Lnet/minecraft/EquipmentSlot;", "GEAR_SLOTS", "[Lnet/minecraft/EquipmentSlot;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final int countGear(LivingEntity entity) {
            int count = 0;
            for (EquipmentSlot slot : GEAR_SLOTS) {
                if (entity.getEquippedStack(slot).isEmpty()) continue;
                ++count;
            }
            return count;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\f\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\n\u001a\u0004\b\r\u0010\fR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/utils/entity/death/EntityDeathDetector$DeathSound;", "", "", "x", "y", "z", "", "time", "<init>", "(DDDJ)V", "D", "getX", "()D", "getY", "getZ", "J", "getTime", "()J", "rtx.kimiko:kimiko"})
    private static final class DeathSound {
        private final double x;
        private final double y;
        private final double z;
        private final long time;

        public DeathSound(double x, double y, double z, long time) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = time;
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

        public final long getTime() {
            return this.time;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0015\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0013\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J%\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0005R\"\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\u001a\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\"\u0010\u001d\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\u0015\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\"\u0010 \u001a\u00020\u00138\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b \u0010\u0015\u001a\u0004\b!\u0010\u0017\"\u0004\b\"\u0010\u0019R\"\u0010#\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b#\u0010\u0015\u001a\u0004\b$\u0010\u0017\"\u0004\b%\u0010\u0019R\"\u0010&\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b&\u0010\u0015\u001a\u0004\b'\u0010\u0017\"\u0004\b(\u0010\u0019R\"\u0010*\u001a\u00020)8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\"\u00100\u001a\u00020)8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b0\u0010+\u001a\u0004\b1\u0010-\"\u0004\b2\u0010/R\"\u00103\u001a\u00020)8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b3\u0010+\u001a\u0004\b4\u0010-\"\u0004\b5\u0010/R\"\u00107\u001a\u0002068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\"\u0010=\u001a\u0002068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b=\u00108\u001a\u0004\b>\u0010:\"\u0004\b?\u0010<R\"\u0010@\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b@\u0010A\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\"\u0010F\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bF\u0010A\u001a\u0004\bG\u0010C\"\u0004\bH\u0010E\u00a8\u0006I"}, d2={"Lrtx/kimiko/utils/entity/death/EntityDeathDetector$Snapshot;", "", "Lnet/minecraft/LivingEntity;", "entity", "<init>", "(Lnet/minecraft/LivingEntity;)V", "", "now", "tick", "", "update", "(Lnet/minecraft/LivingEntity;JJ)V", "", "jumpedFar", "()Z", "Lnet/minecraft/LivingEntity;", "getEntity", "()Lnet/minecraft/LivingEntity;", "setEntity", "", "x", "D", "getX", "()D", "setX", "(D)V", "y", "getY", "setY", "z", "getZ", "setZ", "prevX", "getPrevX", "setPrevX", "prevY", "getPrevY", "setPrevY", "prevZ", "getPrevZ", "setPrevZ", "", "health", "F", "getHealth", "()F", "setHealth", "(F)V", "prevHealth", "getPrevHealth", "setPrevHealth", "maxHealth", "getMaxHealth", "setMaxHealth", "", "gearCount", "I", "getGearCount", "()I", "setGearCount", "(I)V", "prevGear", "getPrevGear", "setPrevGear", "lastHurtMs", "J", "getLastHurtMs", "()J", "setLastHurtMs", "(J)V", "lastSeenTick", "getLastSeenTick", "setLastSeenTick", "rtx.kimiko:kimiko"})
    private static final class Snapshot {
        @NotNull
        private LivingEntity entity;
        private double x;
        private double y;
        private double z;
        private double prevX;
        private double prevY;
        private double prevZ;
        private float health;
        private float prevHealth;
        private float maxHealth;
        private int gearCount;
        private int prevGear;
        private long lastHurtMs;
        private long lastSeenTick;

        public Snapshot(@NotNull LivingEntity entity) {
            Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
            this.entity = entity;
            this.x = this.entity.getX();
            this.y = this.entity.getY();
            this.z = this.entity.getZ();
            this.prevX = this.entity.getX();
            this.prevY = this.entity.getY();
            this.prevZ = this.entity.getZ();
            this.health = this.entity.getHealth();
            this.prevHealth = this.entity.getHealth();
            this.maxHealth = this.entity.getMaxHealth();
            this.prevGear = this.gearCount = Companion.countGear(this.entity);
        }

        @NotNull
        public final LivingEntity getEntity() {
            return this.entity;
        }

        public final void setEntity(@NotNull LivingEntity livingEntity2) {
            Intrinsics.checkNotNullParameter((Object)livingEntity2, (String)"<set-?>");
            this.entity = livingEntity2;
        }

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

        public final double getPrevX() {
            return this.prevX;
        }

        public final void setPrevX(double d) {
            this.prevX = d;
        }

        public final double getPrevY() {
            return this.prevY;
        }

        public final void setPrevY(double d) {
            this.prevY = d;
        }

        public final double getPrevZ() {
            return this.prevZ;
        }

        public final void setPrevZ(double d) {
            this.prevZ = d;
        }

        public final float getHealth() {
            return this.health;
        }

        public final void setHealth(float f) {
            this.health = f;
        }

        public final float getPrevHealth() {
            return this.prevHealth;
        }

        public final void setPrevHealth(float f) {
            this.prevHealth = f;
        }

        public final float getMaxHealth() {
            return this.maxHealth;
        }

        public final void setMaxHealth(float f) {
            this.maxHealth = f;
        }

        public final int getGearCount() {
            return this.gearCount;
        }

        public final void setGearCount(int n) {
            this.gearCount = n;
        }

        public final int getPrevGear() {
            return this.prevGear;
        }

        public final void setPrevGear(int n) {
            this.prevGear = n;
        }

        public final long getLastHurtMs() {
            return this.lastHurtMs;
        }

        public final void setLastHurtMs(long l) {
            this.lastHurtMs = l;
        }

        public final long getLastSeenTick() {
            return this.lastSeenTick;
        }

        public final void setLastSeenTick(long l) {
            this.lastSeenTick = l;
        }

        public final void update(@NotNull LivingEntity entity, long now, long tick) {
            Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
            this.entity = entity;
            this.prevX = this.x;
            this.prevY = this.y;
            this.prevZ = this.z;
            this.x = entity.getX();
            this.y = entity.getY();
            this.z = entity.getZ();
            this.prevHealth = this.health;
            this.health = entity.getHealth();
            this.maxHealth = entity.getMaxHealth();
            this.prevGear = this.gearCount;
            this.gearCount = Companion.countGear(entity);
            if (entity.hurtTime > 0) {
                this.lastHurtMs = now;
            }
            this.lastSeenTick = tick;
        }

        public final boolean jumpedFar() {
            double dx = this.x - this.prevX;
            double dy = this.y - this.prevY;
            double dz = this.z - this.prevZ;
            return dx * dx + dy * dy + dz * dz > 64.0;
        }
    }
}

