/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.network.ClientPlayNetworkHandler
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals.killeffect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001e\u001dB\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\f\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\f\u00a2\u0006\u0004\b\u0015\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0016R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0017R$\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0018j\b\u0012\u0004\u0012\u00020\u0019`\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001c\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDeathMemoryTracker;", "", "", "maxMemoryMs", "Ljava/util/function/Consumer;", "Lnet/minecraft/LivingEntity;", "onTrigger", "<init>", "(JLjava/util/function/Consumer;)V", "entity", "", "allowNonPlayer", "", "remember", "(Lnet/minecraft/LivingEntity;Z)V", "tick", "()V", "", "entityId", "forget", "(I)V", "clear", "J", "Ljava/util/function/Consumer;", "Ljava/util/ArrayList;", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDeathMemoryTracker$DeathMemory;", "Lkotlin/collections/ArrayList;", "memories", "Ljava/util/ArrayList;", "Companion", "DeathMemory", "rtx.kimiko:kimiko"})
public final class KillEffectDeathMemoryTracker {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final long maxMemoryMs;
    @NotNull
    private final Consumer<LivingEntity> onTrigger;
    @NotNull
    private final ArrayList<DeathMemory> memories;
    private static final int MAX_MEMORIES = 12;
    private static final long PLAYER_LIST_GRACE_MS = 150L;
    private static final long REMOVAL_FALLBACK_MS = 250L;
    private static final long RESPAWN_WAIT_MS = 1500L;

    public KillEffectDeathMemoryTracker(long maxMemoryMs, @NotNull Consumer<LivingEntity> onTrigger) {
        Intrinsics.checkNotNullParameter(onTrigger, (String)"onTrigger");
        this.maxMemoryMs = maxMemoryMs;
        this.onTrigger = onTrigger;
        this.memories = new ArrayList();
    }

    public final void remember(@NotNull LivingEntity entity, boolean allowNonPlayer) {
        Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (Intrinsics.areEqual((Object)entity, (Object)minecraft.player) || entity.age < 2) {
            return;
        }
        Iterator<DeathMemory> iterator = this.memories.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<DeathMemory> iterator2 = iterator;
        while (iterator2.hasNext()) {
            DeathMemory existing = (DeathMemory) (iterator2.next());
            if (!existing.matches(entity)) continue;
            existing.refresh(entity, this.maxMemoryMs);
            return;
        }
        if (!allowNonPlayer && !(entity instanceof PlayerEntity)) {
            return;
        }
        if (this.memories.size() >= 12) {
            this.memories.remove(0);
        }
        this.memories.add(new DeathMemory(entity, this.maxMemoryMs));
    }

    public final void tick() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        Iterator<DeathMemory> iterator = this.memories.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<DeathMemory> iterator2 = iterator;
        while (iterator2.hasNext()) {
            DeathMemory memory = (DeathMemory) (iterator2.next());
            if (!memory.isActive()) {
                iterator2.remove();
                continue;
            }
            memory.observe(minecraft);
            if (!memory.getTriggered() && memory.shouldTrigger(minecraft)) {
                this.onTrigger.accept(memory.getEntity());
                memory.markTriggered();
            }
            if (!memory.shouldDiscard(minecraft)) continue;
            iterator2.remove();
        }
    }

    public final void forget(int entityId) {
        this.memories.removeIf(memory -> memory.getEntity().getId() == entityId);
    }

    public final void clear() {
        this.memories.clear();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\tR\u0014\u0010\u000b\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\t\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDeathMemoryTracker.Companion;", "", "<init>", "()V", "", "MAX_MEMORIES", "I", "", "PLAYER_LIST_GRACE_MS", "J", "REMOVAL_FALLBACK_MS", "RESPAWN_WAIT_MS", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\r\u0010\u0007J\u0015\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0012\u0010\u0011J\u0015\u0010\u0013\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0011J\u0017\u0010\u0018\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0011J\r\u0010\u0019\u001a\u00020\f\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\t\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0019\u0010\u001d\u001a\u00020\t2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u000bR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\u0016R\"\u0010\"\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\"\u0010(\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010\u001c\"\u0004\b+\u0010,R\u0016\u0010-\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010)R\u0016\u0010.\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010)R\u0016\u0010/\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u0010)R\u0016\u00101\u001a\u0002008\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u00102R\u0016\u00104\u001a\u0002038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0016\u00106\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u0010#R\u0016\u00107\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u0010)\u00a8\u00068"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDeathMemoryTracker$DeathMemory;", "", "Lnet/minecraft/LivingEntity;", "entity", "", "durationMs", "<init>", "(Lnet/minecraft/LivingEntity;J)V", "other", "", "matches", "(Lnet/minecraft/LivingEntity;)Z", "", "refresh", "Lnet/minecraft/MinecraftClient;", "minecraft", "shouldTrigger", "(Lnet/minecraft/MinecraftClient;)Z", "shouldDiscard", "observe", "(Lnet/minecraft/MinecraftClient;)V", "captureAttackState", "(Lnet/minecraft/LivingEntity;)V", "isStillListedPlayer", "hasReplacementPlayer", "markTriggered", "()V", "isActive", "()Z", "isConfirmedDead", "Lnet/minecraft/LivingEntity;", "getEntity", "()Lnet/minecraft/LivingEntity;", "setEntity", "expiresAt", "J", "getExpiresAt", "()J", "setExpiresAt", "(J)V", "triggered", "Z", "getTriggered", "setTriggered", "(Z)V", "removedFromWorld", "wasLowHealth", "sawDamageFeedback", "", "healthAtAttack", "F", "", "hurtTimeAtAttack", "I", "removedAt", "spectatorAtAttack", "rtx.kimiko:kimiko"})
    private static final class DeathMemory {
        @NotNull
        private LivingEntity entity;
        private long expiresAt;
        private boolean triggered;
        private boolean removedFromWorld;
        private boolean wasLowHealth;
        private boolean sawDamageFeedback;
        private float healthAtAttack;
        private int hurtTimeAtAttack;
        private long removedAt;
        private boolean spectatorAtAttack;

        public DeathMemory(@NotNull LivingEntity entity, long durationMs) {
            Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
            this.entity = entity;
            this.expiresAt = System.currentTimeMillis() + durationMs;
            this.captureAttackState(this.entity);
        }

        @NotNull
        public final LivingEntity getEntity() {
            return this.entity;
        }

        public final void setEntity(@NotNull LivingEntity livingEntity2) {
            Intrinsics.checkNotNullParameter((Object)livingEntity2, (String)"<set-?>");
            this.entity = livingEntity2;
        }

        public final long getExpiresAt() {
            return this.expiresAt;
        }

        public final void setExpiresAt(long l) {
            this.expiresAt = l;
        }

        public final boolean getTriggered() {
            return this.triggered;
        }

        public final void setTriggered(boolean bl) {
            this.triggered = bl;
        }

        public final boolean matches(@NotNull LivingEntity other) {
            Intrinsics.checkNotNullParameter((Object)other, (String)"other");
            return this.entity.getId() == other.getId();
        }

        public final void refresh(@NotNull LivingEntity entity, long durationMs) {
            Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
            if (this.isConfirmedDead(this.entity) || this.removedFromWorld) {
                return;
            }
            this.entity = entity;
            this.triggered = false;
            this.removedFromWorld = false;
            this.wasLowHealth = false;
            this.sawDamageFeedback = false;
            this.removedAt = 0L;
            this.expiresAt = System.currentTimeMillis() + durationMs;
            this.captureAttackState(entity);
        }

        public final boolean shouldTrigger(@NotNull MinecraftClient minecraft) {
            Intrinsics.checkNotNullParameter((Object)minecraft, (String)"minecraft");
            if (this.isConfirmedDead(this.entity)) {
                return true;
            }
            if (this.entity instanceof PlayerEntity && !this.spectatorAtAttack && this.entity.isSpectator()) {
                return true;
            }
            if (!this.removedFromWorld) {
                return false;
            }
            if (this.wasLowHealth || this.sawDamageFeedback) {
                return true;
            }
            if (this.hasReplacementPlayer(minecraft)) {
                return true;
            }
            long removedFor = System.currentTimeMillis() - this.removedAt;
            return this.isStillListedPlayer(minecraft) && removedFor >= 150L || removedFor >= 250L;
        }

        public final boolean shouldDiscard(@NotNull MinecraftClient minecraft) {
            Intrinsics.checkNotNullParameter((Object)minecraft, (String)"minecraft");
            if (this.triggered) {
                return false;
            }
            return minecraft.world == null;
        }

        public final void observe(@NotNull MinecraftClient minecraft) {
            LivingEntity current;
            Intrinsics.checkNotNullParameter((Object)minecraft, (String)"minecraft");
            ClientWorld clientWorld3 = minecraft.world;
            if (clientWorld3 == null) {
                return;
            }
            ClientWorld level = clientWorld3;
            if (this.entity.getHealth() <= 1.0f) {
                this.wasLowHealth = true;
            }
            if (this.entity.getHealth() < this.healthAtAttack || this.entity.hurtTime > this.hurtTimeAtAttack) {
                this.sawDamageFeedback = true;
            }
            Entity entity2 = level.getEntityById(this.entity.getId());
            current = (entity2 instanceof LivingEntity) ? (LivingEntity)entity2 : null;
            if (this.entity.isRemoved() || current == null || !Intrinsics.areEqual((Object)current, (Object)this.entity)) {
                this.removedFromWorld = true;
                if (this.removedAt == 0L) {
                    this.removedAt = System.currentTimeMillis();
                    this.expiresAt = Math.max(this.expiresAt, this.removedAt + 1500L);
                }
            }
        }

        private final void captureAttackState(LivingEntity entity) {
            this.healthAtAttack = entity.getHealth();
            this.hurtTimeAtAttack = entity.hurtTime;
            this.wasLowHealth = this.healthAtAttack <= 1.0f;
            this.spectatorAtAttack = entity.isSpectator();
        }

        private final boolean isStillListedPlayer(MinecraftClient minecraft) {
            LivingEntity livingEntity2 = this.entity;
            PlayerEntity playerEntity2 = livingEntity2 instanceof PlayerEntity ? (PlayerEntity)livingEntity2 : null;
            if (playerEntity2 == null) {
                return false;
            }
            PlayerEntity player = playerEntity2;
            ClientPlayNetworkHandler clientPlayNetworkHandler2 = minecraft.getNetworkHandler();
            if (clientPlayNetworkHandler2 == null) {
                return false;
            }
            ClientPlayNetworkHandler conn = clientPlayNetworkHandler2;
            return conn.getPlayerListEntry(player.getUuid()) != null;
        }

        private final boolean hasReplacementPlayer(MinecraftClient minecraft) {
            LivingEntity livingEntity2 = this.entity;
            PlayerEntity playerEntity2 = livingEntity2 instanceof PlayerEntity ? (PlayerEntity)livingEntity2 : null;
            if (playerEntity2 == null) {
                return false;
            }
            PlayerEntity original = playerEntity2;
            ClientWorld clientWorld3 = minecraft.world;
            if (clientWorld3 == null) {
                return false;
            }
            ClientWorld level = clientWorld3;
            PlayerEntity replacement = level.getPlayerByUuid(original.getUuid());
            if (replacement != null && !Intrinsics.areEqual((Object)replacement, (Object)this.entity)) {
                return true;
            }
            String name = original.getGameProfile().name();
            for (Object e : level.getPlayers()) {
                Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
                AbstractClientPlayerEntity player = (AbstractClientPlayerEntity)e;
                if (Intrinsics.areEqual((Object)player, (Object)this.entity) || !StringsKt.equals((String)player.getGameProfile().name(), (String)name, (boolean)true)) continue;
                return true;
            }
            return false;
        }

        public final void markTriggered() {
            this.triggered = true;
            this.expiresAt = System.currentTimeMillis() + 300L;
        }

        public final boolean isActive() {
            return System.currentTimeMillis() <= this.expiresAt;
        }

        private final boolean isConfirmedDead(LivingEntity entity) {
            return entity != null && (entity.getHealth() <= 0.0f || entity.isDead() || entity.deathTime > 0);
        }
    }
}

