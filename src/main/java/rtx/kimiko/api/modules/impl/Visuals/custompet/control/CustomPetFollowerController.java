/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.effect.StatusEffectInstance
 *  net.minecraft.entity.effect.StatusEffects
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.Entity.RemovalReason
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.world.BlockView
 *  net.minecraft.world.World
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos.Mutable
 *  net.minecraft.util.math.Direction.Axis
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.shape.VoxelShape
 *  net.minecraft.block.BlockState
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.random.Random
 *  net.minecraft.client.world.ClientWorld
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.custompet.control;

import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Visuals.custompet.CustomPetVariant;
import rtx.kimiko.api.modules.impl.Visuals.custompet.entity.CustomPetEntity;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b+\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 {2\u00020\u0001:\u0001{B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006JI\u0010\u0012\u001a\u00020\u00112\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0014\u0010\u0003JI\u0010\u0015\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0013J\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J!\u0010\u0019\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ7\u0010 \u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b \u0010!J7\u0010\"\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b\"\u0010!J'\u0010%\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u001d2\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b%\u0010&J\u0017\u0010'\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b'\u0010\u0018J/\u0010*\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b*\u0010+J\u001f\u0010,\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b,\u0010-J\u001f\u0010/\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b/\u00100J\u0017\u00101\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b1\u0010\u0018J'\u00102\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b2\u00103J\u001f\u00104\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b4\u00105J\u0017\u00107\u001a\u00020\u000b2\u0006\u00106\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b7\u00108J\u001f\u0010:\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u00072\u0006\u00109\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b:\u0010;J'\u0010:\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010<\u001a\u00020\u001d2\u0006\u0010=\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b:\u0010>J'\u0010?\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b?\u0010>J\u001f\u0010A\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010@\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\bA\u0010-J\u001f\u0010C\u001a\u00020\u00162\u0006\u0010B\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\bC\u0010DJ'\u0010E\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\bE\u0010>J\u0017\u0010F\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\bF\u0010\u0018J\u001f\u0010G\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\bG\u0010HJ\u001f\u0010L\u001a\u00020I2\u0006\u0010J\u001a\u00020I2\u0006\u0010K\u001a\u00020IH\u0002\u00a2\u0006\u0004\bL\u0010MJ\u001f\u0010O\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010N\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\bO\u0010PJ'\u0010Q\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u001d2\u0006\u0010N\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\bQ\u00103R\u0014\u0010S\u001a\u00020R8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bS\u0010TR\u0014\u0010V\u001a\u00020U8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bV\u0010WR\u0018\u0010X\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010YR\u0016\u0010Z\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010[R\u0016\u0010\\\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\\\u0010[R\u0016\u0010]\u001a\u00020I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u0016\u0010_\u001a\u00020I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010^R\u0016\u0010`\u001a\u00020I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010^R\u0016\u0010a\u001a\u00020I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010^R\u0016\u0010b\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010[R\u0018\u0010c\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010dR\u0018\u0010e\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010dR\u0018\u0010f\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bf\u0010dR\u0018\u0010g\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bg\u0010dR\u0018\u0010h\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bh\u0010dR\u0016\u0010i\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bi\u0010[R\u0016\u0010j\u001a\u00020I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bj\u0010^R\u0016\u0010k\u001a\u00020I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bk\u0010^R\u0016\u0010l\u001a\u00020I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bl\u0010^R\u0016\u0010m\u001a\u00020I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bm\u0010^R\u0016\u0010n\u001a\u00020I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bn\u0010^R\u0016\u0010o\u001a\u00020I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bo\u0010^R\u0016\u0010p\u001a\u00020I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bp\u0010^R\u0016\u0010q\u001a\u00020I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bq\u0010^R\u0016\u0010r\u001a\u00020I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\br\u0010^R\u0018\u0010s\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bs\u0010dR\u0016\u0010u\u001a\u00020t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bu\u0010vR\u001e\u0010y\u001a\n\u0012\u0004\u0012\u00020x\u0018\u00010w8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010z\u00a8\u0006|"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/control/CustomPetFollowerController;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;", "getPet", "()Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;", "Lnet/minecraft/PlayerEntity;", "player", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;", "variant", "", "owl", "chekushka", "goat", "nightmareBb", "ufo", "", "tick", "(Lnet/minecraft/PlayerEntity;Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;ZZZZZ)V", "reset", "ensurePet", "Lnet/minecraft/Vec3d;", "consumeSavedSpawn", "(Lnet/minecraft/PlayerEntity;)Lnet/minecraft/Vec3d;", "tickPet", "(Lnet/minecraft/PlayerEntity;Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;)V", "current", "playerPos", "", "playerDistance", "playerHorizontalSpeed", "tickRobotFlight", "(Lnet/minecraft/PlayerEntity;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;DD)V", "tickUfoFlight", "x", "z", "ufoHoverY", "(DDLnet/minecraft/PlayerEntity;)D", "pickUfoRoamTarget", "groundY", "preferredY", "ufoCeilingLimit", "(DDDD)D", "pickUfoAnchor", "(Lnet/minecraft/PlayerEntity;D)Lnet/minecraft/Vec3d;", "robotPos", "tickGazeReaction", "(Lnet/minecraft/PlayerEntity;Lnet/minecraft/Vec3d;)V", "pickRobotRoamTarget", "ceilingClearance", "(DDD)D", "shouldPickNewTarget", "(Lnet/minecraft/PlayerEntity;Lnet/minecraft/Vec3d;)Z", "pos", "isRainingAbove", "(Lnet/minecraft/Vec3d;)Z", "closeRange", "pickRoamTarget", "(Lnet/minecraft/PlayerEntity;Z)Lnet/minecraft/Vec3d;", "minRadius", "maxRadius", "(Lnet/minecraft/PlayerEntity;DD)Lnet/minecraft/Vec3d;", "computeFollowTarget", "smoothing", "resolveHeading", "point", "enforcePersonalSpace", "(Lnet/minecraft/Vec3d;Lnet/minecraft/PlayerEntity;)Lnet/minecraft/Vec3d;", "computeAirFollowTarget", "computeAirIdleTarget", "pickAirHoverOffset", "(Lnet/minecraft/PlayerEntity;Lnet/minecraft/Vec3d;)Lnet/minecraft/Vec3d;", "", "min", "max", "randomBetween", "(II)I", "fallbackY", "snapCurrentToGround", "(Lnet/minecraft/Vec3d;D)Lnet/minecraft/Vec3d;", "findGroundY", "Lnet/minecraft/MinecraftClient;", "mc", "Lnet/minecraft/MinecraftClient;", "Lnet/minecraft/Random;", "random", "Lnet/minecraft/Random;", "pet", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;", "petIsOwl", "Z", "petIsUfo", "ufoIdleTicks", "I", "ufoRoamPauseTicks", "ufoGroundedTicks", "ufoRestDuration", "ufoChasing", "roamTarget", "Lnet/minecraft/Vec3d;", "playerHeading", "airHoverOffset", "airSmoothedTarget", "airFlightDirection", "airChasing", "idleTicks", "followRefreshTicks", "followSide", "ticks", "flightTicksRemaining", "nextFlightAtTick", "gazeBuildTicks", "gazeLookActiveTicks", "gazeCooldownTicks", "savedPosition", "", "savedYaw", "F", "Ljava/lang/ref/WeakReference;", "Lnet/minecraft/World;", "savedLevel", "Ljava/lang/ref/WeakReference;", "Companion", "rtx.kimiko:kimiko"})
public final class CustomPetFollowerController {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final MinecraftClient mc;
    @NotNull
    private final Random random;
    @Nullable
    private CustomPetEntity pet;
    private boolean petIsOwl;
    private boolean petIsUfo;
    private int ufoIdleTicks;
    private int ufoRoamPauseTicks;
    private int ufoGroundedTicks;
    private int ufoRestDuration;
    private boolean ufoChasing;
    @Nullable
    private Vec3d roamTarget;
    @Nullable
    private Vec3d playerHeading;
    @Nullable
    private Vec3d airHoverOffset;
    @Nullable
    private Vec3d airSmoothedTarget;
    @Nullable
    private Vec3d airFlightDirection;
    private boolean airChasing;
    private int idleTicks;
    private int followRefreshTicks;
    private int followSide;
    private int ticks;
    private int flightTicksRemaining;
    private int nextFlightAtTick;
    private int gazeBuildTicks;
    private int gazeLookActiveTicks;
    private int gazeCooldownTicks;
    @Nullable
    private Vec3d savedPosition;
    private float savedYaw;
    @Nullable
    private WeakReference<World> savedLevel;
    private static final double MAX_WANDER_DISTANCE = 15.0;
    private static final double SNAP_BACK_DISTANCE = 18.0;
    private static final double FOLLOW_BREAK_DISTANCE = 13.0;
    private static final double FOLLOW_NEAR_DISTANCE = 6.0;
    private static final double TARGET_REACHED_DISTANCE = 0.45;
    private static final double STROLL_RADIUS_MIN = 2.0;
    private static final double STROLL_RADIUS_MAX = 6.0;
    private static final double PLAYER_PERSONAL_SPACE = 0.65;
    private static final double HEADING_MOTION_THRESHOLD_SQR = 9.0E-4;
    private static final double OWL_HOVER_RADIUS_MIN = 1.6;
    private static final double OWL_HOVER_RADIUS_MAX = 2.8;
    private static final double OWL_HOVER_HEIGHT_MIN = 1.6;
    private static final double OWL_HOVER_HEIGHT_MAX = 3.2;
    private static final int OWL_FLIGHT_COOLDOWN_MIN = 300;
    private static final int OWL_FLIGHT_COOLDOWN_MAX = 640;
    private static final int OWL_FLIGHT_DURATION_MIN = 90;
    private static final int OWL_FLIGHT_DURATION_MAX = 190;
    private static final double UFO_HOVER_HEIGHT = 1.95;
    private static final double UFO_PLAYER_LIFT = 1.65;
    private static final double UFO_FOLLOW_START = 8.0;
    private static final double UFO_FOLLOW_STOP = 3.0;
    private static final double UFO_ROAM_MIN = 2.2;
    private static final double UFO_ROAM_MAX = 7.5;
    private static final int UFO_LAND_DELAY = 460;
    private static final int UFO_REST_MIN = 200;
    private static final int UFO_REST_MAX = 400;
    private static final int UFO_TAKEOFF_LIFT_TICKS = 18;
    private static final double UFO_TRICK_CHANCE = 0.0022;

    public CustomPetFollowerController() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        this.mc = minecraftClient2;
        Random random2 = Random.create();
        Intrinsics.checkNotNullExpressionValue((Object)random2, (String)"create(...)");
        this.random = random2;
        this.ufoRestDuration = 200;
        this.followSide = 1;
    }

    @Nullable
    public final CustomPetEntity getPet() {
        return this.pet;
    }

    public final void tick(@Nullable PlayerEntity player, @Nullable CustomPetVariant variant, boolean owl, boolean chekushka, boolean goat, boolean nightmareBb, boolean ufo) {
        if (player == null || player.getEntityWorld() == null || this.mc.world == null) {
            this.reset();
            return;
        }
        this.ensurePet(player, variant, owl, chekushka, goat, nightmareBb, ufo);
        this.tickPet(player, variant);
    }

    public final void reset() {
        CustomPetEntity currentPet = this.pet;
        if (currentPet != null && !currentPet.isRemoved()) {
            this.savedPosition = currentPet.getEntityPos();
            this.savedYaw = currentPet.getYaw();
            this.savedLevel = new WeakReference<World>(currentPet.getEntityWorld());
            ClientWorld level = this.mc.world;
            if (level != null) {
                level.removeEntity(currentPet.getId(), Entity.RemovalReason.DISCARDED);
            }
            currentPet.discard();
        }
        this.pet = null;
        this.roamTarget = null;
        this.playerHeading = null;
        this.airHoverOffset = null;
        this.airSmoothedTarget = null;
        this.airFlightDirection = null;
        this.airChasing = false;
        this.idleTicks = 0;
        this.followRefreshTicks = 0;
        this.followSide = 1;
        this.flightTicksRemaining = 0;
        this.ufoIdleTicks = 0;
        this.ufoRoamPauseTicks = 0;
        this.ufoGroundedTicks = 0;
        this.ufoRestDuration = this.randomBetween(200, 400);
        this.ufoChasing = false;
    }

    private final void ensurePet(PlayerEntity player, CustomPetVariant variant, boolean owl, boolean chekushka, boolean goat, boolean nightmareBb, boolean ufo) {
        CustomPetEntity newPet;
        CustomPetEntity currentPet = this.pet;
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        CustomPetVariant activeVariant = variant != null ? variant : CustomPetVariant.NITWIT;
        if (currentPet != null && Intrinsics.areEqual((Object)currentPet.getEntityWorld(), (Object)level) && !currentPet.isRemoved() && currentPet.isChekushka() == chekushka && currentPet.isGoat() == goat && currentPet.isNightmareBb() == nightmareBb && currentPet.isUfo() == ufo) {
            currentPet.setPetVariant(activeVariant);
            currentPet.setOwl(owl);
            this.petIsOwl = owl;
            this.petIsUfo = ufo;
            return;
        }
        this.reset();
        this.petIsOwl = owl;
        this.petIsUfo = ufo;
        this.ufoIdleTicks = 0;
        this.pet = newPet = new CustomPetEntity((World)level);
        newPet.setPetVariant(activeVariant);
        newPet.setOwl(owl);
        newPet.setChekushka(chekushka);
        newPet.setGoat(goat);
        newPet.setNightmareBb(nightmareBb);
        newPet.setUfo(ufo);
        this.nextFlightAtTick = this.ticks + this.randomBetween(300, 640);
        this.airHoverOffset = null;
        this.airSmoothedTarget = null;
        this.airFlightDirection = null;
        this.airChasing = false;
        this.idleTicks = this.randomBetween(18, 34);
        this.followRefreshTicks = 0;
        this.followSide = this.random.nextBoolean() ? 1 : -1;
        Vec3d restored = this.consumeSavedSpawn(player);
        if (restored != null) {
            this.roamTarget = null;
            newPet.snapTo(restored, this.savedYaw);
        } else {
            Vec3d target;
            this.roamTarget = target = this.pickRoamTarget(player, true);
            newPet.snapTo(target, this.random.nextFloat() * 360.0f);
        }
        level.addEntity((Entity)newPet);
    }

    private final Vec3d consumeSavedSpawn(PlayerEntity player) {
        Vec3d saved = this.savedPosition;
        WeakReference<World> weakReference = this.savedLevel;
        World level = weakReference != null ? (World)weakReference.get() : null;
        this.savedPosition = null;
        this.savedLevel = null;
        if (saved == null || level == null || !Intrinsics.areEqual((Object)level, (Object)this.mc.world)) {
            return null;
        }
        if (saved.squaredDistanceTo(player.getEntityPos()) > 324.0) {
            return null;
        }
        return saved;
    }

    private final void tickPet(PlayerEntity player, CustomPetVariant variant) {
        StatusEffectInstance speedEffect;
        boolean robotMode;
        CustomPetEntity customPetEntity = this.pet;
        if (customPetEntity == null) {
            return;
        }
        CustomPetEntity activePet = customPetEntity;
        CustomPetVariant customPetVariant = variant;
        if (customPetVariant == null) {
            customPetVariant = CustomPetVariant.NITWIT;
        }
        activePet.setPetVariant(customPetVariant);
        Vec3d vec3d2 = activePet.getEntityPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d current = vec3d2;
        Vec3d groundedCurrent = this.snapCurrentToGround(current, player.getY());
        Vec3d vec3d3 = player.getEntityPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"position(...)");
        Vec3d playerPos = vec3d3;
        double playerDistanceSqr = current.squaredDistanceTo(playerPos);
        double playerDistance = Math.sqrt(playerDistanceSqr);
        double playerHorizontalSpeed = player.getVelocity().horizontalLength();
        if (this.petIsUfo) {
            this.tickUfoFlight(player, current, playerPos, playerDistance, playerHorizontalSpeed);
            return;
        }
        boolean bl = robotMode = variant != null && variant.isRobot();
        if (robotMode) {
            this.tickRobotFlight(player, current, playerPos, playerDistance, playerHorizontalSpeed);
            return;
        }
        int n = this.ticks;
        this.ticks = n + 1;
        boolean playerIdle = playerHorizontalSpeed < 0.02;
        boolean airbornePlayer = player.isGliding() || player.getAbilities().flying;
        boolean owlFlight = false;
        if (this.petIsOwl) {
            if (this.flightTicksRemaining > 0) {
                int n2 = this.flightTicksRemaining;
                this.flightTicksRemaining = n2 + -1;
                owlFlight = true;
                if (this.flightTicksRemaining == 0) {
                    this.nextFlightAtTick = this.ticks + this.randomBetween(300, 640);
                }
            } else if (!airbornePlayer && this.ticks >= this.nextFlightAtTick && playerHorizontalSpeed < 0.12 && playerDistance < 8.0) {
                this.flightTicksRemaining = this.randomBetween(90, 190);
                this.nextFlightAtTick = this.ticks + this.flightTicksRemaining + this.randomBetween(300, 640);
                owlFlight = true;
            }
        }
        boolean airborne = airbornePlayer || owlFlight;
        boolean playerMoving = playerHorizontalSpeed > 0.025 || Math.abs(player.getVelocity().y) > 0.05;
        boolean playerMovingFast = playerHorizontalSpeed > 0.17 || Math.abs(player.getVelocity().y) > 0.08;
        boolean shouldFollowPlayer = playerDistanceSqr > 169.0 || playerMoving && playerDistanceSqr > 36.0;
        boolean rainy = this.isRainingAbove(current);
        if (playerDistanceSqr > 324.0) {
            Vec3d target;
            this.roamTarget = target = this.pickRoamTarget(player, true);
            this.airFlightDirection = null;
            this.airChasing = false;
            this.flightTicksRemaining = 0;
            this.idleTicks = this.randomBetween(12, 22);
            this.followRefreshTicks = 0;
            activePet.snapTo(target, activePet.getYaw());
            activePet.setBehavior(target, 0.0, false, rainy, false, 1.0);
            return;
        }
        if (activePet.isMovementBlocked() && playerDistanceSqr <= 169.0) {
            this.roamTarget = null;
            this.airFlightDirection = null;
            this.airChasing = false;
            this.followRefreshTicks = 0;
            if (this.idleTicks <= 0) {
                this.idleTicks = this.randomBetween(18, 38);
            }
            int target = this.idleTicks;
            this.idleTicks = target + -1;
            activePet.setBehavior(groundedCurrent, 0.0, false, rainy, false, 1.0);
            return;
        }
        if (airborne) {
            this.idleTicks = 0;
            boolean airborneMoving = playerHorizontalSpeed > 0.08 || Math.abs(player.getVelocity().y) > 0.08;
            Vec3d desiredAirTarget = null;
            if (airborneMoving) {
                this.followRefreshTicks = 0;
                this.airHoverOffset = null;
                desiredAirTarget = this.computeAirFollowTarget(player, playerDistance, playerHorizontalSpeed);
            } else {
                if (this.airHoverOffset == null || this.followRefreshTicks <= 0) {
                    this.airHoverOffset = this.pickAirHoverOffset(player, current);
                    this.followRefreshTicks = this.randomBetween(20, 42);
// v5 = Unit.INSTANCE;
                } else {
                    this.followRefreshTicks--;
                }
                desiredAirTarget = this.computeAirIdleTarget(player);
            }
            if (this.airSmoothedTarget == null) {
                this.airSmoothedTarget = current;
            }
            double smoothing = Math.clamp(0.07 + player.getVelocity().length() * (player.isGliding() ? 0.025 : 0.05) + Math.max(0.0, playerDistance - 1.5) * 0.012, 0.07, player.isGliding() ? 0.2 : 0.28);
            Vec3d vec3d4 = this.airSmoothedTarget;
            Intrinsics.checkNotNull((Object)vec3d4);
            this.roamTarget = this.airSmoothedTarget = vec3d4.lerp(desiredAirTarget, smoothing);
// v7 = Unit.INSTANCE;
        } else if (shouldFollowPlayer) {
            this.airHoverOffset = null;
            this.airSmoothedTarget = null;
            this.airFlightDirection = null;
            this.airChasing = false;
            this.idleTicks = 0;
            if (this.followRefreshTicks <= 0 || this.roamTarget == null) {
                Vec3d vec3d5;
                if (playerDistance < 4.5 && this.random.nextFloat() < 0.08f) {
                    this.followSide *= -1;
                }
                Vec3d targetFollow = this.computeFollowTarget(player, playerDistance, playerHorizontalSpeed);
                float followSmoothing = (float)Math.clamp(0.22 + Math.max(0.0, playerDistance - 2.0) * 0.05 + playerHorizontalSpeed * 0.35, 0.22, 0.62);
                if (this.roamTarget == null) {
                    vec3d5 = targetFollow;
                } else {
                    Vec3d vec3d6 = this.roamTarget;
                    Intrinsics.checkNotNull((Object)vec3d6);
                    vec3d5 = vec3d6.lerp(targetFollow, (double)followSmoothing);
                }
                this.roamTarget = vec3d5;
                this.followRefreshTicks = playerDistance > 7.0 ? 1 : 2;
// v7 = Unit.INSTANCE;
            } else {
                this.followRefreshTicks--;
            }
        } else {
            this.airHoverOffset = null;
            this.airSmoothedTarget = null;
            this.airFlightDirection = null;
            this.airChasing = false;
            this.followRefreshTicks = 0;
            if (this.idleTicks > 0) {
                int targetFollow = this.idleTicks;
                this.idleTicks = targetFollow + -1;
                activePet.setBehavior(groundedCurrent, 0.0, false, rainy, false, 1.0);
                return;
            }
            if (this.roamTarget == null || this.shouldPickNewTarget(player, current)) {
                float idleChance;
                float f = idleChance = playerIdle ? 0.42f : 0.24f;
                if (this.random.nextFloat() < idleChance) {
                    this.roamTarget = null;
                    this.idleTicks = playerIdle ? this.randomBetween(50, 140) : this.randomBetween(18, 42);
                    activePet.setBehavior(groundedCurrent, 0.0, false, rainy, false, 1.0);
                    return;
                }
                int n4 = this.followSide = this.random.nextBoolean() ? 1 : -1;
                if (playerIdle) {
                    this.roamTarget = this.pickRoamTarget(player, 2.0, 6.0);
                } else {
                    boolean closeRange = current.squaredDistanceTo(playerPos) > 16.0 || player.getVelocity().horizontalLengthSquared() > 0.04;
                    this.roamTarget = this.pickRoamTarget(player, closeRange);
                }
            }
// v7 = Unit.INSTANCE;
        }
        Vec3d target = this.roamTarget;
        if (target == null) {
            this.idleTicks = this.randomBetween(18, 36);
            activePet.setBehavior(groundedCurrent, 0.0, false, rainy, false, 1.0);
            return;
        }
        Vec3d vec3d7 = target.subtract(current);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d7, (String)"subtract(...)");
        Vec3d toDesired = vec3d7;
        double horizontalDistance = Math.hypot(toDesired.x, toDesired.z);
        double totalDistance = toDesired.length();
        if (airborne) {
            boolean forceCatchup;
            StatusEffectInstance speedEffect2;
            StatusEffectInstance statusEffectInstance2 = speedEffect2 = player.getStatusEffect(StatusEffects.SPEED);
            int speedLevel = statusEffectInstance2 != null ? statusEffectInstance2.getAmplifier() + 1 : 0;
            double playerSpeed = player.getVelocity().length();
            double stopDistance = player.isGliding() ? 0.82 : 0.48;
            double startDistance = player.isGliding() ? 1.28 : 0.78;
            boolean bl2 = forceCatchup = playerDistance > 4.0 || playerSpeed > 1.2 || playerHorizontalSpeed > 0.55;
            if (forceCatchup || totalDistance >= startDistance) {
                this.airChasing = true;
            } else if (totalDistance <= stopDistance) {
                this.airChasing = false;
            }
            double speed = Math.clamp(0.22 + playerSpeed * (player.isGliding() ? 2.45 : 1.65) + Math.max(0.0, playerDistance - 1.0) * (player.isGliding() ? 0.24 : 0.18) + (double)speedLevel * 0.07 + (player.isSprinting() ? 0.08 : 0.0), 0.16, player.isGliding() ? (playerDistance > 18.0 ? 6.8 : (playerDistance > 12.0 ? 5.0 : (playerDistance > 7.0 ? 3.6 : 1.9))) : (playerDistance > 16.0 ? 3.2 : (playerDistance > 10.0 ? 2.4 : (playerDistance > 6.0 ? 1.55 : 0.95))));
            double animationSpeed = Math.clamp(1.15 + Math.max(0.0, playerDistance - 1.2) * 0.22 + playerSpeed * (player.isGliding() ? 1.8 : 1.35) + (double)speedLevel * 0.16, 1.0, player.isGliding() ? 3.1 : 2.4);
            activePet.setBehavior(target, speed, this.airChasing, false, true, animationSpeed);
            return;
        }
        if (horizontalDistance <= 0.45 && Math.abs(toDesired.y) <= 0.45 && !shouldFollowPlayer) {
            this.roamTarget = null;
            this.idleTicks = playerIdle ? this.randomBetween(50, 140) : this.randomBetween(18, 42);
            activePet.setBehavior(groundedCurrent, 0.0, false, rainy, false, 1.0);
            return;
        }
        StatusEffectInstance statusEffectInstance3 = speedEffect = player.getStatusEffect(StatusEffects.SPEED);
        int speedLevel = statusEffectInstance3 != null ? statusEffectInstance3.getAmplifier() + 1 : 0;
        double approachFactor = shouldFollowPlayer ? Math.clamp((horizontalDistance - 0.4) / 1.8, 0.12, 1.0) : 1.0;
        double speed = Math.clamp(0.055 + playerHorizontalSpeed * 0.31 + Math.max(0.0, playerDistance - 1.8) * 0.072 + (double)speedLevel * 0.028 + (player.isSprinting() ? 0.028 : 0.0), 0.045, playerDistance > 8.5 ? 0.52 : (playerDistance > 6.0 ? 0.42 : (playerDistance > 4.0 ? 0.31 : 0.22))) * approachFactor;
        double animationSpeed = Math.clamp(1.0 + Math.max(0.0, playerDistance - 1.4) * 0.14 + playerHorizontalSpeed * 0.88 + (double)speedLevel * 0.12 + (player.isSprinting() ? 0.06 : 0.0), 0.95, 1.85) * (shouldFollowPlayer ? Math.clamp(approachFactor + 0.3, 0.4, 1.0) : 1.0);
        activePet.setBehavior(target, speed, speed > 0.02, rainy, false, animationSpeed);
    }

    private final void tickRobotFlight(PlayerEntity player, Vec3d current, Vec3d playerPos, double playerDistance, double playerHorizontalSpeed) {
        boolean shouldFollow;
        CustomPetEntity customPetEntity = this.pet;
        if (customPetEntity == null) {
            return;
        }
        CustomPetEntity activePet = customPetEntity;
        if (playerDistance > 18.0) {
            Vec3d snap;
            this.airSmoothedTarget = snap = this.pickRobotRoamTarget(player);
            this.roamTarget = snap;
            this.airFlightDirection = null;
            this.followRefreshTicks = 0;
            this.idleTicks = this.randomBetween(30, 60);
            activePet.snapTo(snap, activePet.getYaw());
            activePet.setBehavior(snap, 0.0, false, false, true, 1.0);
            return;
        }
        this.airFlightDirection = this.resolveHeading(player, 0.18);
        boolean playerMoving = playerHorizontalSpeed > 0.08;
        boolean bl = shouldFollow = playerMoving && playerDistance > 4.0 || playerDistance > 15.0;
        if (shouldFollow) {
            this.roamTarget = null;
            this.idleTicks = 0;
        }
        Vec3d rawTarget = null;
        boolean wantsToMove = false;
        if (shouldFollow) {
            Vec3d vec3d2 = this.airFlightDirection;
            Intrinsics.checkNotNull((Object)vec3d2);
            Vec3d dir = vec3d2;
            Vec3d side = new Vec3d(-dir.z, 0.0, dir.x);
            double backOffset = playerDistance > 5.0 ? 0.5 : 1.2;
            double sideOffset = 1.3 * (double)this.followSide;
            double leadDist = Math.clamp(playerHorizontalSpeed * 4.5, 0.0, 2.2);
            Vec3d vec3d3 = playerPos.add(dir.multiply(leadDist - backOffset)).add(side.multiply(sideOffset));
            Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"add(...)");
            Vec3d followAnchor = vec3d3;
            double hoverY = player.getY() + 1.5;
            double clearance = this.ceilingClearance(followAnchor.x, followAnchor.z, hoverY);
            rawTarget = this.enforcePersonalSpace(new Vec3d(followAnchor.x, Math.max(hoverY, clearance), followAnchor.z), player);
            wantsToMove = true;
        } else if (this.idleTicks > 0) {
            Vec3d vec3d4;
            int dir = this.idleTicks;
            this.idleTicks = dir + -1;
            if (this.idleTicks <= 0) {
                this.roamTarget = null;
            }
            if ((vec3d4 = this.roamTarget) == null) {
                vec3d4 = current;
            }
            rawTarget = vec3d4;
            wantsToMove = false;
        } else if (this.roamTarget != null) {
            Vec3d vec3d5 = this.roamTarget;
            Intrinsics.checkNotNull((Object)vec3d5);
            double distToRoam = current.distanceTo(vec3d5);
            if (distToRoam < 1.5) {
                this.roamTarget = current;
                this.idleTicks = this.randomBetween(60, 140);
                rawTarget = current;
                wantsToMove = false;
            } else {
                Vec3d vec3d6 = this.roamTarget;
                Intrinsics.checkNotNull((Object)vec3d6);
                rawTarget = vec3d6;
                wantsToMove = true;
            }
        } else {
            Vec3d target;
            this.roamTarget = target = this.pickRobotRoamTarget(player);
            rawTarget = target;
            wantsToMove = true;
        }
        double bobPrimary = Math.sin((double)player.age * 0.075 + (double)this.followSide * 1.7) * 0.55;
        double bobSecondary = Math.sin((double)player.age * 0.125 + (double)this.followSide * 0.4) * 0.2;
        double verticalBob = bobPrimary + bobSecondary;
        double lateralSway = wantsToMove ? 0.0 : Math.sin((double)player.age * 0.018 + (double)this.followSide * 0.9) * 0.1;
        Vec3d vec3d7 = this.airFlightDirection;
        Intrinsics.checkNotNull((Object)vec3d7);
        Vec3d heading = vec3d7;
        Vec3d swayDir = new Vec3d(-heading.z, 0.0, heading.x);
        Vec3d desiredTarget = new Vec3d(rawTarget.x + swayDir.x * lateralSway, rawTarget.y + verticalBob, rawTarget.z + swayDir.z * lateralSway);
        if (this.airSmoothedTarget == null) {
            this.airSmoothedTarget = current;
        }
        double smoothing = wantsToMove ? Math.clamp(0.1 + playerHorizontalSpeed * 0.08 + Math.max(0.0, playerDistance - 1.5) * 0.018, 0.1, 0.34) : 0.07;
        Vec3d vec3d8 = this.airSmoothedTarget;
        Intrinsics.checkNotNull((Object)vec3d8);
        Vec3d vec3d9 = this.airSmoothedTarget = vec3d8.lerp(desiredTarget, smoothing);
        Intrinsics.checkNotNull((Object)vec3d9);
        Vec3d vec3d10 = vec3d9.subtract(playerPos);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d10, (String)"subtract(...)");
        Vec3d fromPlayer = vec3d10;
        if (fromPlayer.length() > 17.5) {
            this.airSmoothedTarget = playerPos.add(fromPlayer.normalize().multiply(17.5));
        }
        Vec3d vec3d11 = this.airSmoothedTarget;
        Intrinsics.checkNotNull((Object)vec3d11);
        double targetDist = current.distanceTo(vec3d11);
        double speed = wantsToMove ? Math.clamp(0.08 + targetDist * 0.06 + playerHorizontalSpeed * 0.8 + (player.isSprinting() ? 0.06 : 0.0), 0.06, shouldFollow ? 1.2 : 0.25) : 0.04;
        double animSpeed = Math.clamp(1.0 + targetDist * 0.08 + playerHorizontalSpeed * 0.6, 1.0, 1.8);
        this.tickGazeReaction(player, current);
        Vec3d vec3d12 = this.airSmoothedTarget;
        Intrinsics.checkNotNull((Object)vec3d12);
        activePet.setBehavior(vec3d12, speed, wantsToMove || targetDist > 0.3, false, true, animSpeed);
    }

    private final void tickUfoFlight(PlayerEntity player, Vec3d current, Vec3d playerPos, double playerDistance, double playerHorizontalSpeed) {
        boolean disturbed;
        boolean playerMoving;
        CustomPetEntity customPetEntity = this.pet;
        if (customPetEntity == null) {
            return;
        }
        CustomPetEntity activePet = customPetEntity;
        int n = this.ticks;
        this.ticks = n + 1;
        Vec3d vec3d2 = player.getVelocity();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getDeltaMovement(...)");
        Vec3d motion = vec3d2;
        boolean playerFlying = player.isGliding() || player.getAbilities().flying;
        boolean bl = playerMoving = playerHorizontalSpeed > 0.04 || Math.abs(motion.y) > 0.09;
        if (playerDistance > 18.0) {
            Vec3d snapTarget;
            Vec3d snap = this.pickUfoAnchor(player, playerHorizontalSpeed);
            double snapY = Math.max(this.findGroundY(snap.x, snap.z, player.getY()) + 1.95, player.getY() + 1.65);
            this.airSmoothedTarget = snapTarget = new Vec3d(snap.x, snapY, snap.z);
            this.roamTarget = null;
            this.airFlightDirection = null;
            this.ufoIdleTicks = 0;
            this.ufoRoamPauseTicks = 0;
            this.ufoChasing = false;
            activePet.setUfoGrounded(false);
            activePet.setUfoBoost(false);
            activePet.snapTo(snapTarget, activePet.getYaw());
            activePet.setBehavior(snapTarget, 0.0, false, false, true, 1.0);
            return;
        }
        this.airFlightDirection = this.resolveHeading(player, 0.16);
        boolean grounded = activePet.isUfoGrounded();
        if (grounded) {
            boolean calledAway;
            boolean bl2 = calledAway = playerDistance > 8.0 || playerFlying;
            if (!calledAway && this.ufoGroundedTicks < this.ufoRestDuration) {
                Vec3d rest;
                int n2 = this.ufoGroundedTicks;
                this.ufoGroundedTicks = n2 + 1;
                double restY = this.findGroundY(current.x, current.z, player.getY());
                this.airSmoothedTarget = rest = new Vec3d(current.x, restY, current.z);
                this.roamTarget = null;
                this.ufoRoamPauseTicks = 0;
                activePet.setUfoBoost(false);
                activePet.setBehavior(rest, 0.0, false, false, false, 1.0);
                return;
            }
            activePet.setUfoGrounded(false);
            this.ufoChasing = calledAway;
            this.ufoIdleTicks = 0;
            this.ufoGroundedTicks = 0;
            this.roamTarget = null;
            this.ufoRoamPauseTicks = 0;
            grounded = false;
        }
        if (activePet.isUfoTakingOff()) {
            int remaining = activePet.getUfoTakeoffTicks();
            double padY = this.findGroundY(current.x, current.z, player.getY());
            double hoverY = this.ufoHoverY(current.x, current.z, player);
            double raw = Math.clamp((double)(18 - remaining) / 18.0, 0.0, 1.0);
            double lift = raw * raw * (3.0 - 2.0 * raw);
            Vec3d liftTarget = new Vec3d(current.x, MathHelper.lerp((double)lift, (double)padY, (double)hoverY), current.z);
            if (this.airSmoothedTarget == null) {
                this.airSmoothedTarget = current;
            }
            Vec3d vec3d3 = this.airSmoothedTarget;
            Intrinsics.checkNotNull((Object)vec3d3);
            this.airSmoothedTarget = vec3d3.lerp(liftTarget, 0.35);
            activePet.setUfoBoost(false);
            this.tickGazeReaction(player, current);
            Vec3d vec3d4 = this.airSmoothedTarget;
            Intrinsics.checkNotNull((Object)vec3d4);
            activePet.setBehavior(vec3d4, lift > 0.001 ? 0.35 : 0.0, lift > 0.001, false, true, 1.0);
            return;
        }
        if (playerDistance > 8.0 || playerFlying) {
            this.ufoChasing = true;
        } else if (playerDistance < 3.0) {
            this.ufoChasing = false;
        }
        boolean bl3 = disturbed = playerMoving || playerFlying || this.ufoChasing;
        if (disturbed) {
            this.ufoIdleTicks = 0;
        } else if (this.ufoIdleTicks <= 460) {
            int padY = this.ufoIdleTicks;
            this.ufoIdleTicks = padY + 1;
        }
        boolean wantsLanding = this.ufoIdleTicks >= 460;
        double landingGroundY = this.findGroundY(current.x, current.z, player.getY());
        if (wantsLanding && current.y - landingGroundY <= 0.12) {
            activePet.setUfoGrounded(true);
            activePet.setUfoBoost(false);
            this.ufoGroundedTicks = 0;
            this.ufoRestDuration = this.randomBetween(200, 400);
            Vec3d vec3d5 = this.airSmoothedTarget = new Vec3d(current.x, landingGroundY, current.z);
            Intrinsics.checkNotNull((Object)vec3d5);
            activePet.setBehavior(vec3d5, 0.0, false, false, false, 1.0);
            return;
        }
        Vec3d rawTarget = null;
        boolean wantsToMove = false;
        if (wantsLanding) {
            this.roamTarget = null;
            this.ufoRoamPauseTicks = 0;
            rawTarget = new Vec3d(current.x, landingGroundY, current.z);
            wantsToMove = true;
        } else if (this.ufoChasing) {
            this.roamTarget = null;
            this.ufoRoamPauseTicks = 0;
            Vec3d anchor = this.pickUfoAnchor(player, playerHorizontalSpeed);
            rawTarget = new Vec3d(anchor.x, this.ufoHoverY(anchor.x, anchor.z, player), anchor.z);
            wantsToMove = true;
        } else {
            Vec3d target = this.roamTarget;
            if (target == null || target.squaredDistanceTo(playerPos) > 56.25) {
                Vec3d newTarget;
                this.roamTarget = newTarget = this.pickUfoRoamTarget(player);
                this.ufoRoamPauseTicks = 0;
            }
            Vec3d vec3d6 = this.roamTarget;
            Intrinsics.checkNotNull((Object)vec3d6);
            Vec3d validTarget = vec3d6;
            if (this.ufoRoamPauseTicks <= 0 && current.squaredDistanceTo(validTarget) < 0.5625) {
                this.ufoRoamPauseTicks = this.randomBetween(40, 150);
            }
            if (this.ufoRoamPauseTicks > 0) {
                int n3 = this.ufoRoamPauseTicks;
                this.ufoRoamPauseTicks = n3 + -1;
                if (this.ufoRoamPauseTicks == 0) {
                    this.roamTarget = this.pickUfoRoamTarget(player);
                }
                rawTarget = new Vec3d(current.x, this.ufoHoverY(current.x, current.z, player), current.z);
                wantsToMove = false;
            } else {
                rawTarget = validTarget;
                wantsToMove = true;
            }
        }
        if (!wantsLanding) {
            double bob = Math.sin((double)(this.ticks + this.followSide * 11) * 0.055) * 0.085;
            double sway = Math.sin((double)(this.ticks + this.followSide * 5) * 0.021) * 0.09;
            Vec3d vec3d7 = this.airFlightDirection;
            Intrinsics.checkNotNull((Object)vec3d7);
            Vec3d heading = vec3d7;
            Vec3d swayDir = new Vec3d(-heading.z, 0.0, heading.x);
            rawTarget = new Vec3d(rawTarget.x + swayDir.x * sway, rawTarget.y + bob, rawTarget.z + swayDir.z * sway);
        }
        if (this.airSmoothedTarget == null) {
            this.airSmoothedTarget = current;
        }
        double smoothing = this.ufoChasing ? Math.clamp(0.09 + playerHorizontalSpeed * 0.1 + Math.max(0.0, playerDistance - 1.5) * 0.02, 0.09, 0.32) : 0.07;
        Vec3d vec3d8 = this.airSmoothedTarget;
        Intrinsics.checkNotNull((Object)vec3d8);
        Vec3d vec3d9 = this.airSmoothedTarget = vec3d8.lerp(rawTarget, smoothing);
        Intrinsics.checkNotNull((Object)vec3d9);
        double targetDistance = current.distanceTo(vec3d9);
        double speed = 0.0;
        speed = !wantsToMove ? 0.03 : (this.ufoChasing ? Math.clamp(0.1 + targetDistance * 0.07 + playerHorizontalSpeed * (playerFlying ? 1.6 : 0.95) + (player.isSprinting() ? 0.05 : 0.0), 0.05, playerDistance > 9.0 ? 2.2 : (playerDistance > 5.0 ? 1.3 : 0.7)) : Math.clamp(0.045 + targetDistance * 0.02, 0.04, 0.16));
        double animationSpeed = Math.clamp(1.0 + targetDistance * 0.09 + playerHorizontalSpeed * 0.8, 1.0, 2.1);
        boolean boosting = this.ufoChasing && !wantsLanding && (playerDistance > 9.0 || playerHorizontalSpeed > 0.3 || playerFlying && targetDistance > 2.0);
        activePet.setUfoBoost(boosting);
        if (!wantsLanding && !boosting && !this.ufoChasing && this.random.nextDouble() < 0.0022) {
            activePet.triggerUfoSpin();
        }
        this.tickGazeReaction(player, current);
        Vec3d vec3d10 = this.airSmoothedTarget;
        Intrinsics.checkNotNull((Object)vec3d10);
        activePet.setBehavior(vec3d10, speed, wantsToMove && targetDistance > 0.05, false, true, animationSpeed);
    }

    private final double ufoHoverY(double x, double z, PlayerEntity player) {
        double groundY = this.findGroundY(x, z, player.getY());
        double hoverY = Math.max(groundY + 1.95, player.getY() + 1.65);
        return Math.min(hoverY, this.ufoCeilingLimit(x, z, groundY, hoverY));
    }

    private final Vec3d pickUfoRoamTarget(PlayerEntity player) {
        double angle = this.random.nextDouble() * (double)((float)Math.PI * 2);
        double radius = MathHelper.lerp((double)this.random.nextDouble(), (double)2.2, (double)7.0);
        double x = player.getX() + Math.cos(angle) * radius;
        double z = player.getZ() + Math.sin(angle) * radius;
        return this.enforcePersonalSpace(new Vec3d(x, this.ufoHoverY(x, z, player), z), player);
    }

    private final double ufoCeilingLimit(double x, double z, double groundY, double preferredY) {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return preferredY;
        }
        ClientWorld level = clientWorld3;
        int blockX = MathHelper.floor((double)x);
        int blockZ = MathHelper.floor((double)z);
        int from = MathHelper.floor((double)(groundY + 0.6));
        int to = MathHelper.ceil((double)(preferredY + 1.6));
        BlockPos.Mutable mutablePos = new BlockPos.Mutable(blockX, from, blockZ);
        int y = from;
        if (y <= to) {
            while (true) {
                mutablePos.setY(y);
                BlockState state = level.getBlockState((BlockPos)mutablePos);
                VoxelShape shape = state.getCollisionShape((BlockView)level, (BlockPos)mutablePos);
                if (!shape.isEmpty()) {
                    return Math.max(groundY + 0.55, (double)y + shape.getMin(Direction.Axis.Y) - 0.8);
                }
                if (y == to) break;
                ++y;
            }
        }
        return preferredY;
    }

    private final Vec3d pickUfoAnchor(PlayerEntity player, double playerHorizontalSpeed) {
        Vec3d vec3d2 = this.airFlightDirection;
        if (vec3d2 == null) {
            vec3d2 = this.resolveHeading(player, 1.0);
        }
        Vec3d direction = vec3d2;
        Vec3d side = new Vec3d(-direction.z, 0.0, direction.x);
        double lead = Math.clamp(playerHorizontalSpeed * 4.2, 0.0, 2.0);
        double back = playerHorizontalSpeed > 0.08 ? 1.0 : 1.6;
        double sideOffset = 1.25 * (double)this.followSide;
        Vec3d vec3d3 = player.getEntityPos().add(direction.multiply(lead - back)).add(side.multiply(sideOffset));
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"add(...)");
        Vec3d anchor = vec3d3;
        return this.enforcePersonalSpace(anchor, player);
    }

    private final void tickGazeReaction(PlayerEntity player, Vec3d robotPos) {
        CustomPetEntity customPetEntity = this.pet;
        if (customPetEntity == null) {
            return;
        }
        CustomPetEntity activePet = customPetEntity;
        Vec3d vec3d2 = player.getRotationVector();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getLookAngle(...)");
        Vec3d playerLook = vec3d2;
        Vec3d vec3d3 = robotPos.subtract(player.getEntityPos());
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"subtract(...)");
        Vec3d toRobot = vec3d3;
        double horizontalDistance = Math.hypot(toRobot.x, toRobot.z);
        boolean playerLookingAtRobot = false;
        if (horizontalDistance > 0.05 && horizontalDistance < 3.5) {
            Vec3d lookH = new Vec3d(playerLook.x, 0.0, playerLook.z);
            Vec3d toH = new Vec3d(toRobot.x, 0.0, toRobot.z);
            if (lookH.lengthSquared() > 1.0E-4 && toH.lengthSquared() > 1.0E-4) {
                Vec3d vec3d4 = lookH.normalize();
                Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"normalize(...)");
                lookH = vec3d4;
                Vec3d vec3d5 = toH.normalize();
                Intrinsics.checkNotNullExpressionValue((Object)vec3d5, (String)"normalize(...)");
                toH = vec3d5;
                double dot = lookH.x * toH.x + lookH.z * toH.z;
                boolean bl = playerLookingAtRobot = dot > 0.88;
            }
        }
        if (this.gazeCooldownTicks > 0) {
            int lookH = this.gazeCooldownTicks;
            this.gazeCooldownTicks = lookH + -1;
        }
        if (this.gazeLookActiveTicks > 0) {
            int lookH = this.gazeLookActiveTicks;
            this.gazeLookActiveTicks = lookH + -1;
            float yaw = (float)Math.toDegrees(MathHelper.atan2((double)(player.getZ() - robotPos.z), (double)(player.getX() - robotPos.x))) - 90.0f;
            activePet.overrideLookYaw(yaw, 1.0f);
            if (this.gazeLookActiveTicks == 0) {
                this.gazeCooldownTicks = this.randomBetween(80, 160);
            }
            return;
        }
        activePet.overrideLookYaw(0.0f, 0.0f);
        if (!playerLookingAtRobot || this.gazeCooldownTicks > 0) {
            if (!playerLookingAtRobot) {
                this.gazeBuildTicks = 0;
            }
            return;
        }
        int n = this.gazeBuildTicks;
        this.gazeBuildTicks = n + 1;
        if (this.gazeBuildTicks >= 25) {
            this.gazeBuildTicks = 0;
            if (this.random.nextFloat() < 0.65f) {
                this.gazeLookActiveTicks = this.randomBetween(40, 70);
            } else {
                this.gazeCooldownTicks = this.randomBetween(40, 90);
            }
        }
    }

    private final Vec3d pickRobotRoamTarget(PlayerEntity player) {
        double angle = this.random.nextDouble() * (double)((float)Math.PI * 2);
        double radius = 3.0 + this.random.nextDouble() * 12.0;
        double x = player.getX() + Math.cos(angle) * radius;
        double z = player.getZ() + Math.sin(angle) * radius;
        double baseY = player.getY() + 1.0 + this.random.nextDouble() * 3.0;
        double clearance = this.ceilingClearance(x, z, baseY);
        return this.enforcePersonalSpace(new Vec3d(x, Math.max(baseY, clearance), z), player);
    }

    private final double ceilingClearance(double x, double z, double preferredY) {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return preferredY;
        }
        ClientWorld level = clientWorld3;
        int blockX = MathHelper.floor((double)x);
        int blockZ = MathHelper.floor((double)z);
        int baseY = MathHelper.floor((double)preferredY);
        BlockPos.Mutable mutablePos = new BlockPos.Mutable(blockX, baseY, blockZ);
        int y = baseY;
        int n = baseY + 3;
        if (y <= n) {
            while (true) {
                mutablePos.setY(y);
                BlockState state = level.getBlockState((BlockPos)mutablePos);
                VoxelShape shape = state.getCollisionShape((BlockView)level, (BlockPos)mutablePos);
                if (!shape.isEmpty()) {
                    return (double)y + shape.getMax(Direction.Axis.Y) + 0.6;
                }
                if (y == n) break;
                ++y;
            }
        }
        return preferredY;
    }

    private final boolean shouldPickNewTarget(PlayerEntity player, Vec3d current) {
        Vec3d vec3d2 = this.roamTarget;
        if (vec3d2 == null) {
            return true;
        }
        Vec3d target = vec3d2;
        if (target.squaredDistanceTo(player.getEntityPos()) > 225.0) {
            return true;
        }
        return current.squaredDistanceTo(player.getEntityPos()) > 169.0 && current.squaredDistanceTo(target) > 25.0;
    }

    private final boolean isRainingAbove(Vec3d pos) {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return false;
        }
        ClientWorld level = clientWorld3;
        return level.hasRain(BlockPos.ofFloored((double)pos.x, (double)(pos.y + 1.1), (double)pos.z));
    }

    private final Vec3d pickRoamTarget(PlayerEntity player, boolean closeRange) {
        return closeRange ? this.pickRoamTarget(player, 1.6, 3.0) : this.pickRoamTarget(player, 2.5, 14.75);
    }

    private final Vec3d pickRoamTarget(PlayerEntity player, double minRadius, double maxRadius) {
        Vec3d vec3d2 = player.getVelocity();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getDeltaMovement(...)");
        Vec3d motion = vec3d2;
        double baseAngle = motion.horizontalLengthSquared() > 0.0025 ? Math.atan2(motion.z, motion.x) : this.random.nextDouble() * (double)((float)Math.PI * 2);
        double angle = baseAngle + MathHelper.lerp((double)this.random.nextDouble(), (double)-1.65, (double)1.65);
        double radius = MathHelper.lerp((double)this.random.nextDouble(), (double)minRadius, (double)maxRadius);
        double x = player.getX() + Math.cos(angle) * radius;
        double z = player.getZ() + Math.sin(angle) * radius;
        double y = this.findGroundY(x, z, player.getY());
        return this.enforcePersonalSpace(new Vec3d(x, y, z), player);
    }

    private final Vec3d computeFollowTarget(PlayerEntity player, double playerDistance, double playerHorizontalSpeed) {
        Vec3d direction = this.resolveHeading(player, 0.35);
        double leadDistance = Math.clamp(0.6 + playerHorizontalSpeed * 7.0 + Math.max(0.0, playerDistance - 3.0) * 0.35, 0.6, 3.0);
        Vec3d vec3d2 = player.getEntityPos().add(player.getVelocity().multiply(leadDistance));
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
        Vec3d predictedPlayerPos = vec3d2;
        double backOffset = playerDistance > 5.5 ? 0.65 : 1.45;
        double sideOffset = playerDistance > 5.5 ? 0.0 : 0.55 * (double)this.followSide;
        Vec3d side = new Vec3d(-direction.z, 0.0, direction.x);
        Vec3d vec3d3 = predictedPlayerPos.subtract(direction.multiply(backOffset)).add(side.multiply(sideOffset));
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"add(...)");
        Vec3d followPos = vec3d3;
        double y = this.findGroundY(followPos.x, followPos.z, player.getY());
        return this.enforcePersonalSpace(new Vec3d(followPos.x, y, followPos.z), player);
    }

    private final Vec3d resolveHeading(PlayerEntity player, double smoothing) {
        Vec3d vec3d2 = player.getVelocity();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getDeltaMovement(...)");
        Vec3d motion = vec3d2;
        Vec3d horizontal = new Vec3d(motion.x, 0.0, motion.z);
        if (horizontal.lengthSquared() > 9.0E-4) {
            Vec3d vec3d3 = horizontal.normalize();
            Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"normalize(...)");
            Vec3d direction = vec3d3;
            Vec3d currentHeading = this.playerHeading;
            if (currentHeading == null) {
                this.playerHeading = direction;
            } else {
                Vec3d vec3d4 = currentHeading.lerp(direction, smoothing);
                Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"lerp(...)");
                Vec3d blended = vec3d4;
                this.playerHeading = blended.lengthSquared() < 1.0E-6 ? direction : blended.normalize();
            }
        } else if (this.playerHeading == null) {
            float yaw = player.getYaw() * ((float)Math.PI / 180);
            this.playerHeading = new Vec3d((double)(-MathHelper.sin((double)yaw)), 0.0, (double)MathHelper.cos((double)yaw));
        }
        Vec3d vec3d5 = this.playerHeading;
        Intrinsics.checkNotNull((Object)vec3d5);
        return vec3d5;
    }

    private final Vec3d enforcePersonalSpace(Vec3d point, PlayerEntity player) {
        double dz;
        double dx = point.x - player.getX();
        double distSqr = dx * dx + (dz = point.z - player.getZ()) * dz;
        if (distSqr >= 0.42250000000000004) {
            return point;
        }
        double dist = Math.sqrt(distSqr);
        if (dist < 1.0E-4) {
            float yaw = player.getYaw() * ((float)Math.PI / 180);
            dx = -MathHelper.sin((double)yaw);
            dz = MathHelper.cos((double)yaw);
            dist = 1.0;
        }
        double scale = 0.65 / dist;
        return new Vec3d(player.getX() + dx * scale, point.y, player.getZ() + dz * scale);
    }

    private final Vec3d computeAirFollowTarget(PlayerEntity player, double playerDistance, double playerHorizontalSpeed) {
        double playerSpeed = player.getVelocity().length();
        Vec3d vec3d2 = this.airFlightDirection = this.resolveHeading(player, player.isGliding() ? 0.14 : 0.26);
        Intrinsics.checkNotNull((Object)vec3d2);
        Vec3d direction = vec3d2;
        Vec3d side = new Vec3d(-direction.z, 0.0, direction.x);
        boolean firstPerson = this.mc.options.getPerspective().isFirstPerson();
        double sideOffset = (player.isGliding() ? 0.82 : 0.65) * (double)this.followSide;
        double backOffset = (player.isGliding() ? 1.45 : 1.05) + Math.min(playerDistance * 0.08, player.isGliding() ? 0.55 : 0.35);
        if (player.isGliding() && firstPerson) {
            sideOffset *= 1.18;
            backOffset += 0.42;
        }
        double liftOffset = player.isGliding() ? 0.2 : 0.45;
        double leadDistance = Math.clamp(0.45 + playerHorizontalSpeed * (player.isGliding() ? 5.8 : 4.8) + Math.max(0.0, playerSpeed - 0.8) * (player.isGliding() ? 0.45 : 0.35), 0.45, player.isGliding() ? 2.8 : 1.6);
        Vec3d vec3d3 = player.getEntityPos().add(direction.multiply(leadDistance));
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"add(...)");
        Vec3d predictedPlayerPos = vec3d3;
        double bob = Math.sin((double)(player.age + this.followSide * 7) * 0.1) * 0.028;
        Vec3d vec3d4 = predictedPlayerPos.subtract(direction.multiply(backOffset)).add(side.multiply(sideOffset)).add(0.0, player.getY() - predictedPlayerPos.y + liftOffset + bob, 0.0);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"add(...)");
        return vec3d4;
    }

    private final Vec3d computeAirIdleTarget(PlayerEntity player) {
        Vec3d fallback = this.petIsOwl ? new Vec3d(1.9 * (double)this.followSide, 1.6, 0.0) : new Vec3d(1.6 * (double)this.followSide, 0.35, 0.0);
        Vec3d vec3d2 = this.airHoverOffset;
        if (vec3d2 == null) {
            vec3d2 = fallback;
        }
        Vec3d offset = vec3d2;
        double bobScale = this.petIsOwl ? 0.25 : 0.09;
        double bob = Math.sin((double)(player.age + this.followSide * 7) * 0.085) * bobScale;
        Vec3d vec3d3 = player.getEntityPos().add(offset.x, offset.y + bob, offset.z);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"add(...)");
        return vec3d3;
    }

    private final Vec3d pickAirHoverOffset(PlayerEntity player, Vec3d current) {
        if (this.petIsOwl) {
            double angle = this.random.nextDouble() * (double)((float)Math.PI * 2);
            double radius = MathHelper.lerp((double)this.random.nextDouble(), (double)1.6, (double)2.8);
            double height = MathHelper.lerp((double)this.random.nextDouble(), (double)1.6, (double)3.2);
            return new Vec3d(Math.cos(angle) * radius, height, Math.sin(angle) * radius);
        }
        Vec3d vec3d2 = current.subtract(player.getEntityPos());
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"subtract(...)");
        Vec3d offset = vec3d2;
        Vec3d horizontal = new Vec3d(offset.x, 0.0, offset.z);
        double horizontalLength = horizontal.length();
        if (horizontalLength < 0.9 || horizontalLength > 2.5) {
            double angle = this.random.nextDouble() * (double)((float)Math.PI * 2);
            double radius = MathHelper.lerp((double)this.random.nextDouble(), (double)1.35, (double)1.95);
            return new Vec3d(Math.cos(angle) * radius, MathHelper.lerp((double)this.random.nextDouble(), (double)0.28, (double)0.44), Math.sin(angle) * radius);
        }
        Vec3d vec3d3 = horizontal.normalize().multiply(Math.clamp(horizontalLength, 1.25, 1.95));
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"scale(...)");
        Vec3d normalized = vec3d3;
        double y = Math.clamp(offset.y, 0.24, 0.46);
        return new Vec3d(normalized.x, y, normalized.z);
    }

    private final int randomBetween(int min, int max) {
        return min + this.random.nextInt(max - min + 1);
    }

    private final Vec3d snapCurrentToGround(Vec3d current, double fallbackY) {
        double groundY = this.findGroundY(current.x, current.z, Math.max(current.y, fallbackY));
        return new Vec3d(current.x, groundY, current.z);
    }

    private final double findGroundY(double x, double z, double fallbackY) {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return fallbackY;
        }
        ClientWorld level = clientWorld3;
        int blockX = MathHelper.floor((double)x);
        int blockZ = MathHelper.floor((double)z);
        int baseY = MathHelper.floor((double)fallbackY);
        BlockPos.Mutable mutablePos = new BlockPos.Mutable(blockX, baseY + 2, blockZ);
        int n = baseY - 6;
        int y = baseY + 2;
        if (n <= y) {
            while (true) {
                mutablePos.setY(y);
                BlockState state = level.getBlockState((BlockPos)mutablePos);
                VoxelShape shape = state.getCollisionShape((BlockView)level, (BlockPos)mutablePos);
                if (!shape.isEmpty()) {
                    return (double)y + shape.getMax(Direction.Axis.Y);
                }
                if (y == n) break;
                --y;
            }
        }
        return fallbackY;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0011\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u0006R\u0014\u0010\f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u0006R\u0014\u0010\u000e\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0006R\u0014\u0010\u0010\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0006R\u0014\u0010\u0012\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0015R\u0014\u0010\u0018\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0015R\u0014\u0010\u0019\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0006R\u0014\u0010\u001a\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0006R\u0014\u0010\u001b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0006R\u0014\u0010\u001c\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0006R\u0014\u0010\u001d\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u0006R\u0014\u0010\u001e\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u0006R\u0014\u0010\u001f\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u0015R\u0014\u0010 \u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u0015R\u0014\u0010!\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010\u0015R\u0014\u0010\"\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010\u0015R\u0014\u0010#\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010\u0006\u00a8\u0006$"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/control/CustomPetFollowerController.Companion;", "", "<init>", "()V", "", "MAX_WANDER_DISTANCE", "D", "SNAP_BACK_DISTANCE", "FOLLOW_BREAK_DISTANCE", "FOLLOW_NEAR_DISTANCE", "TARGET_REACHED_DISTANCE", "STROLL_RADIUS_MIN", "STROLL_RADIUS_MAX", "PLAYER_PERSONAL_SPACE", "HEADING_MOTION_THRESHOLD_SQR", "OWL_HOVER_RADIUS_MIN", "OWL_HOVER_RADIUS_MAX", "OWL_HOVER_HEIGHT_MIN", "OWL_HOVER_HEIGHT_MAX", "", "OWL_FLIGHT_COOLDOWN_MIN", "I", "OWL_FLIGHT_COOLDOWN_MAX", "OWL_FLIGHT_DURATION_MIN", "OWL_FLIGHT_DURATION_MAX", "UFO_HOVER_HEIGHT", "UFO_PLAYER_LIFT", "UFO_FOLLOW_START", "UFO_FOLLOW_STOP", "UFO_ROAM_MIN", "UFO_ROAM_MAX", "UFO_LAND_DELAY", "UFO_REST_MIN", "UFO_REST_MAX", "UFO_TAKEOFF_LIFT_TICKS", "UFO_TRICK_CHANCE", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

