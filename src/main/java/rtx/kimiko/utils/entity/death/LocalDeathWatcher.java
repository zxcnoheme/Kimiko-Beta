/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.network.packet.Packet
 *  net.minecraft.network.packet.s2c.play.GameJoinS2CPacket
 *  net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket
 *  net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket
 *  net.minecraft.client.network.ClientPlayerEntity
 *  net.minecraft.network.packet.s2c.play.EntityDamageS2CPacket
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.entity.death;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityDamageS2CPacket;
import net.minecraft.client.gui.screen.DeathScreen;
import rtx.kimiko.utils.render.modules.post.wasted.WastedState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u001a\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u001f\u0010\t\u001a\u00020\u00042\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJ\u001d\u0010\r\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u000f\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0013\u0010\u0010\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u0013\u0010\u0012\u001a\u00020\u0011H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0014\u0010\u0003J\u0013\u0010\u0016\u001a\u00020\u0015H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0019\u001a\u00020\u0018H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0019\u0010\u001aJ'\u0010 \u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b \u0010!J\u0017\u0010\"\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b\"\u0010#J\u0017\u0010$\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b$\u0010#J\u001f\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0014\u0010&J\u0017\u0010'\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010*R\u0014\u0010,\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010*R\u0014\u0010-\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010*R\u0014\u0010/\u001a\u00020.8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0014\u00104\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u0010*R\u0014\u00105\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u0010*R\u0014\u00106\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u0010*R\u0014\u00107\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u0010*R\u0016\u00108\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b8\u0010*R\u0016\u00109\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u0010*R\u0016\u0010:\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u0010*R\u0016\u0010;\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b;\u0010*R\u0016\u0010<\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010*R\u0016\u0010=\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b=\u0010*R\u0016\u0010>\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010*R\u0016\u0010?\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b?\u0010@R\u0016\u0010A\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u0010*R\u0016\u0010B\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0016\u0010D\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u0016\u0010F\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u0010CR\u0016\u0010G\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bG\u0010ER\u0016\u0010H\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bH\u0010ER\u0016\u0010I\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bI\u0010*R\u0016\u0010J\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bJ\u0010*R\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010CR\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010E\u00a8\u0006K"}, d2={"Lrtx/kimiko/utils/entity/death/LocalDeathWatcher;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "reset", "Lnet/minecraft/Packet;", "packet", "onPacket", "(Lnet/minecraft/Packet;)V", "Lnet/minecraft/Entity;", "entity", "notifyDeathAnimation", "(Lnet/minecraft/Entity;)V", "notifyTotem", "notifyDeathScreen", "", "poll", "()Z", "markHandled", "Lnet/minecraft/Vec3d;", "deathPosition", "()Lnet/minecraft/Vec3d;", "", "deathYaw", "()F", "Lnet/minecraft/ClientPlayerEntity;", "player", "health", "", "now", "takeSnapshot", "(Lnet/minecraft/ClientPlayerEntity;FJ)V", "killedRecently", "(J)Z", "revived", "fromPrevious", "(JZ)V", "signalWeak", "(J)V", "CONFIRM_DELAY_MS", "J", "TOTEM_VETO_MS", "SIGNAL_STALE_MS", "RELATCH_MS", "", "JOIN_GRACE_TICKS", "I", "", "TELEPORT_JUMP_SQ", "D", "REVIVE_PAIR_MS", "HURT_WINDOW_MS", "RESPAWN_HURT_WINDOW_MS", "LOGIN_BLOCK_MS", "strongAtMs", "weakAtMs", "totemAtMs", "weakBlockedUntilMs", "respawnAtMs", "loginAtMs", "lastHurtMs", "latched", "Z", "latchedAtMs", "lastPos", "Lnet/minecraft/Vec3d;", "lastYaw", "F", "prevPos", "prevYaw", "prevHealth", "jumpAtMs", "reviveAtMs", "rtx.kimiko:kimiko"})
public final class LocalDeathWatcher {
    @NotNull
    public static final LocalDeathWatcher INSTANCE = new LocalDeathWatcher();
    private static final long CONFIRM_DELAY_MS = 120L;
    private static final long TOTEM_VETO_MS = 400L;
    private static final long SIGNAL_STALE_MS = 1500L;
    private static final long RELATCH_MS = 1000L;
    private static final int JOIN_GRACE_TICKS = 20;
    private static final double TELEPORT_JUMP_SQ = 64.0;
    private static final long REVIVE_PAIR_MS = 700L;
    private static final long HURT_WINDOW_MS = 1500L;
    private static final long RESPAWN_HURT_WINDOW_MS = 5000L;
    private static final long LOGIN_BLOCK_MS = 3000L;
    private static volatile long strongAtMs;
    private static volatile long weakAtMs;
    private static volatile long totemAtMs;
    private static volatile long weakBlockedUntilMs;
    private static volatile long respawnAtMs;
    private static volatile long loginAtMs;
    private static volatile long lastHurtMs;
    private static volatile long suppressUntilMs;
    private static boolean latched;
    private static long latchedAtMs;
    @NotNull
    private static Vec3d lastPos;
    private static float lastYaw;
    @NotNull
    private static Vec3d prevPos;
    private static float prevYaw;
    private static float prevHealth;
    private static long jumpAtMs;
    private static long reviveAtMs;
    @NotNull
    private static Vec3d deathPosition;
    private static float deathYaw;

    private LocalDeathWatcher() {
    }

    @JvmStatic
    public static final void reset() {
        strongAtMs = 0L;
        weakAtMs = 0L;
        totemAtMs = 0L;
        weakBlockedUntilMs = 0L;
        respawnAtMs = 0L;
        jumpAtMs = 0L;
        reviveAtMs = 0L;
        lastHurtMs = 0L;
        latched = false;
        latchedAtMs = 0L;
    }

    @JvmStatic
    public static final void suppress(long durationMs) {
        suppressUntilMs = System.currentTimeMillis() + durationMs;
        LocalDeathWatcher.reset();
    }

    @JvmStatic
    public static final void onPacket(@NotNull Packet<?> packet) {
        Intrinsics.checkNotNullParameter(packet, (String)"packet");
        long now = System.currentTimeMillis();
        if (packet instanceof DeathMessageS2CPacket) {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null && ((DeathMessageS2CPacket)packet).playerId() == player.getId()) {
                strongAtMs = now;
            }
            return;
        }
        if (packet instanceof PlayerRespawnS2CPacket) {
            if (((PlayerRespawnS2CPacket)packet).flag() != 3) {
                respawnAtMs = now;
            }
            return;
        }
        if (packet instanceof GameJoinS2CPacket) {
            loginAtMs = now;
            LocalDeathWatcher.reset();
            return;
        }
        if (packet instanceof EntityDamageS2CPacket) {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null && ((EntityDamageS2CPacket)packet).entityId() == player.getId()) {
                lastHurtMs = now;
            }
            return;
        }
        if (packet instanceof HealthUpdateS2CPacket && ((HealthUpdateS2CPacket)packet).getHealth() <= 0.0f) {
            INSTANCE.signalWeak(now);
        }
    }

    @JvmStatic
    public static final void notifyDeathAnimation(@Nullable Entity entity) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (entity != null && player != null && entity.getId() == player.getId()) {
            strongAtMs = System.currentTimeMillis();
        }
    }

    @JvmStatic
    public static final void notifyTotem(@Nullable Entity entity) {
        if (entity != null && entity == MinecraftClient.getInstance().player) {
            totemAtMs = System.currentTimeMillis();
        }
    }

    @JvmStatic
    public static final void notifyDeathScreen() {
        strongAtMs = System.currentTimeMillis();
    }

    @JvmStatic
    public static final boolean poll() {
        boolean pending;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        ClientPlayerEntity player = minecraft.player;
        if (player == null || minecraft.world == null) {
            LocalDeathWatcher.reset();
            return false;
        }
        if (player.isCreative() || player.isSpectator()) {
            LocalDeathWatcher.reset();
            return false;
        }
        long now = System.currentTimeMillis();
        if (now < suppressUntilMs) {
            strongAtMs = 0L;
            weakAtMs = 0L;
            respawnAtMs = 0L;
            jumpAtMs = 0L;
            reviveAtMs = 0L;
            return false;
        }
        if (WastedState.isActive()) {
            strongAtMs = 0L;
            weakAtMs = 0L;
            respawnAtMs = 0L;
            jumpAtMs = 0L;
            reviveAtMs = 0L;
            latched = true;
            latchedAtMs = now;
            return false;
        }
        float health = player.getHealth();
        boolean alive = health > 0.0f && player.deathTime <= 0;
        boolean bl = pending = strongAtMs != 0L || weakAtMs != 0L || respawnAtMs != 0L;
        if (player.hurtTime > 0) {
            lastHurtMs = now;
        }
        if (alive && !pending) {
            INSTANCE.takeSnapshot(player, health, now);
        }
        if (latched) {
            strongAtMs = 0L;
            weakAtMs = 0L;
            respawnAtMs = 0L;
            jumpAtMs = 0L;
            reviveAtMs = 0L;
            if (alive && now - latchedAtMs >= 4000L) {
                latched = false;
            }
            return false;
        }
        if (now - loginAtMs < 3000L) {
            strongAtMs = 0L;
            weakAtMs = 0L;
            respawnAtMs = 0L;
            jumpAtMs = 0L;
            reviveAtMs = 0L;
            return false;
        }
        if (respawnAtMs != 0L) {
            if (now - respawnAtMs > 1500L) {
                respawnAtMs = 0L;
            } else if (!alive && INSTANCE.killedRecently(now)) {
                strongAtMs = respawnAtMs;
                respawnAtMs = 0L;
            } else {
                respawnAtMs = 0L;
            }
        }
        if (!alive && !player.isSpectator() && player.age >= 20) {
            INSTANCE.signalWeak(now);
        }
        if (strongAtMs != 0L && now - strongAtMs > 1500L) {
            strongAtMs = 0L;
        }
        if (weakAtMs != 0L && now - weakAtMs > 1500L) {
            weakAtMs = 0L;
        }
        boolean hasDeathScreen = minecraft.currentScreen instanceof DeathScreen;
        if (strongAtMs != 0L && (!alive || hasDeathScreen)) {
            INSTANCE.markHandled(now, false);
            return true;
        }
        if (INSTANCE.revived(now) && player.age >= 20 && Math.abs(reviveAtMs - totemAtMs) > 400L) {
            INSTANCE.markHandled(now, true);
            return true;
        }
        long weak = weakAtMs;
        if (weak == 0L || player.age < 20) {
            return false;
        }
        if (player.isSpectator() || player.isCreative()) {
            weakAtMs = 0L;
            return false;
        }
        if (Math.abs(weak - totemAtMs) <= 400L) {
            weakAtMs = 0L;
            weakBlockedUntilMs = totemAtMs + 400L;
            return false;
        }
        if (now - weak < 120L) {
            return false;
        }
        INSTANCE.markHandled(now, false);
        return true;
    }

    @JvmStatic
    public static final void markHandled() {
        INSTANCE.markHandled(System.currentTimeMillis(), false);
    }

    @JvmStatic
    @NotNull
    public static final Vec3d deathPosition() {
        return deathPosition;
    }

    @JvmStatic
    public static final float deathYaw() {
        return deathYaw;
    }

    private final void takeSnapshot(ClientPlayerEntity player, float health, long now) {
        Vec3d vec3d2 = player.getEntityPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d position = vec3d2;
        if (lastPos != Vec3d.ZERO) {
            if (lastPos.squaredDistanceTo(position) > 64.0) {
                jumpAtMs = now;
            }
            float maxHealth = Math.max(1.0f, player.getMaxHealth());
            boolean wasDead = prevHealth <= 0.0f && prevHealth >= -0.5f;
            if (wasDead && health >= maxHealth * 0.85f && health - prevHealth >= maxHealth * 0.5f) {
                reviveAtMs = now;
            }
        }
        prevPos = lastPos;
        prevYaw = lastYaw;
        lastPos = position;
        lastYaw = player.getYaw();
        prevHealth = health;
    }

    private final boolean killedRecently(long now) {
        if (weakAtMs != 0L || strongAtMs != 0L) {
            return true;
        }
        if (now - lastHurtMs <= 5000L) {
            return true;
        }
        ClientPlayerEntity clientPlayerEntity2 = MinecraftClient.getInstance().player;
        if (clientPlayerEntity2 == null) {
            return false;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        float maxHealth = Math.max(1.0f, player.getMaxHealth());
        return prevHealth >= 0.0f && prevHealth <= maxHealth * 0.5f;
    }

    private final boolean revived(long now) {
        if (jumpAtMs == 0L || reviveAtMs == 0L) {
            return false;
        }
        if (Math.abs(jumpAtMs - reviveAtMs) > 700L) {
            return false;
        }
        return now - lastHurtMs <= 1500L;
    }

    private final void markHandled(long now, boolean fromPrevious) {
        latched = true;
        latchedAtMs = now;
        strongAtMs = 0L;
        weakAtMs = 0L;
        respawnAtMs = 0L;
        jumpAtMs = 0L;
        reviveAtMs = 0L;
        lastHurtMs = 0L;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (lastPos == Vec3d.ZERO && player != null) {
            Vec3d vec3d2 = player.getEntityPos();
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
            lastPos = vec3d2;
            lastYaw = player.getYaw();
        }
        boolean usePrevious = fromPrevious && prevPos != Vec3d.ZERO;
        deathPosition = usePrevious ? prevPos : lastPos;
        deathYaw = usePrevious ? prevYaw : lastYaw;
    }

    private final void signalWeak(long now) {
        if (now < weakBlockedUntilMs) {
            return;
        }
        long weak = weakAtMs;
        if (weak == 0L || now - weak > 1500L) {
            weakAtMs = now;
        }
    }

    static {
        Vec3d vec3d2 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
        lastPos = vec3d2;
        Vec3d vec3d3 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"ZERO");
        prevPos = vec3d3;
        prevHealth = -1.0f;
        Vec3d vec3d4 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"ZERO");
        deathPosition = vec3d4;
    }
}

