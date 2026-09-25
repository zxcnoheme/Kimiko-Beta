/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.projectile.thrown.EnderPearlEntity
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.block.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Position
 *  net.minecraft.util.hit.HitResult.Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.Identifier
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.RaycastContext
 *  net.minecraft.world.RaycastContext.FluidHandling
 *  net.minecraft.world.RaycastContext.ShapeType
 *  net.minecraft.util.hit.BlockHitResult
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  net.minecraft.util.math.RotationAxis
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 *  org.joml.Vector3f
 */
package rtx.kimiko.api.modules.impl.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.RaycastContext;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.restrict.Server;
import rtx.kimiko.api.modules.restrict.ServerRule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;
import sigil.protect.Level;
import sigil.protect.Protect;

@ServerRule(mode=ServerRule.Mode.BLOCK, servers={Server.ST})
@Feature(value={"trowpearlhelper"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00da\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 `2\u00020\u0001:\u0003ab`B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003J\u001b\u0010\r\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0003b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eJ'\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J)\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ/\u0010 \u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000fH\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u0011\u00a2\u0006\u0004\b \u0010!Jo\u00101\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\"2\u0006\u0010%\u001a\u00020$2\u0006\u0010'\u001a\u00020&2\u0006\u0010)\u001a\u00020(2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*2\u0006\u0010.\u001a\u00020\u00162\u0006\u0010/\u001a\u00020(2\u0006\u00100\u001a\u00020\u0014H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u0011\u00a2\u0006\u0004\b1\u00102J!\u00105\u001a\u0004\u0018\u0001042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u00103\u001a\u00020*H\u0002\u00a2\u0006\u0004\b5\u00106J\u0019\u00108\u001a\u0004\u0018\u0001072\u0006\u0010\u001b\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b8\u00109J\u001f\u0010;\u001a\u00020*2\u0006\u0010:\u001a\u0002042\u0006\u0010\u001c\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b;\u0010<J/\u0010@\u001a\u00020>2\u0006\u0010\u001c\u001a\u00020\u00162\u0006\u0010=\u001a\u00020\u0016H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078?\u00a2\u0006\u0004\b@\u0010AJ/\u0010D\u001a\u00020\u00162\u0006\u0010B\u001a\u00020(2\u0006\u0010C\u001a\u00020(H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078?\u00a2\u0006\u0004\bD\u0010EJ'\u0010G\u001a\u00020\u001d2\u0006\u0010F\u001a\u00020\u0016H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078?\u00a2\u0006\u0004\bG\u0010HR\u0014\u0010J\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010KR$\u0010N\u001a\u0012\u0012\u0004\u0012\u0002040Lj\b\u0012\u0004\u0012\u000204`M8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010OR$\u0010R\u001a\u0012\u0012\u0004\u0012\u00020\u00140Pj\b\u0012\u0004\u0012\u00020\u0014`Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bR\u0010SR$\u0010T\u001a\u0012\u0012\u0004\u0012\u00020\u00140Pj\b\u0012\u0004\u0012\u00020\u0014`Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u0010SR0\u0010W\u001a\u001e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140Uj\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0014`V8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bW\u0010XR0\u0010Y\u001a\u001e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u0002040Uj\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u000204`V8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bY\u0010XR\u001c\u0010\\\u001a\n [*\u0004\u0018\u00010Z0Z8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\\\u0010]R\u0016\u0010^\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010_\u00ca\u0001\u001e\bc\u0012\n\bd\u0012\u0006\b\n0e8f\u0012\u000e\bg\u0012\n\b\fJ\u0006\b\n0h8i\u00ca\u0001\u0010\bj\u0012\f\b\u0006\u0012\b\b\fJ\u0004\b\b(k\u00a8\u0006l"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/TrowPearlHelper;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onDisable", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "", "now", "STD", "updateMarks", "(J)V", "", "pearlId", "Lnet/minecraft/Vec3d;", "landing", "trackMark", "(ILnet/minecraft/Vec3d;J)V", "Lnet/minecraft/Entity;", "pearl", "eye", "", "thrownAtExistingMark", "(Lnet/minecraft/Entity;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;)Z", "renderMarks", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;J)V", "Lnet/minecraft/MatrixStack;", "stack", "Lnet/minecraft/VertexConsumer;", "buffer", "Lorg/joml/Quaternionf;", "cameraRotation", "", "spinDegrees", "", "worldX", "worldY", "worldZ", "cameraPos", "size", "color", "drawMarker", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumer;Lorg/joml/Quaternionf;FDDDLnet/minecraft/Vec3d;FI)V", "radius", "Lrtx/kimiko/api/modules/impl/Utils/TrowPearlHelper$Mark;", "nearest", "(Lnet/minecraft/Vec3d;D)Lrtx/kimiko/api/modules/impl/Utils/TrowPearlHelper$Mark;", "Lrtx/kimiko/api/modules/impl/Utils/TrowPearlHelper$Flight;", "simulateFlight", "(Lnet/minecraft/Entity;)Lrtx/kimiko/api/modules/impl/Utils/TrowPearlHelper$Flight;", "mark", "launchPitch", "(Lrtx/kimiko/api/modules/impl/Utils/TrowPearlHelper$Mark;Lnet/minecraft/Vec3d;)D", "target", "", "MAX", "lookRotation", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;)[F", "pitch", "yaw", "viewVector", "(FF)Lnet/minecraft/Vec3d;", "pos", "inFront", "(Lnet/minecraft/Vec3d;)Z", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "markerSize", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "marks", "Ljava/util/ArrayList;", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "seenOwnPearls", "Ljava/util/HashSet;", "aimedOwnPearls", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "ownTotalSteps", "Ljava/util/HashMap;", "pearlMarks", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "pitchPool", "Ljava/util/concurrent/ExecutorService;", "pitchCloudDownUntil", "J", "Companion", "Flight", "Mark", "Lrtx/kimiko/api/modules/restrict/ServerRule;", "mode", "Lrtx/kimiko/api/modules/restrict/ServerRule$Mode;", "BLOCK", "servers", "Lrtx/kimiko/api/modules/restrict/Server;", "ST", "Lrtx/kimiko/api/liteapi/Feature;", "trowpearlhelper", "rtx.kimiko:kimiko"})
public final class TrowPearlHelper
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final NumberSetting markerSize = (NumberSetting)this.register((Setting)new NumberSetting("Размер метки", "Размер метки в мире (блоки).", 2.0, 1.5, 2.5, 0.1));
    @NotNull
    private final ArrayList<Mark> marks = new ArrayList();
    @NotNull
    private final HashSet<Integer> seenOwnPearls = new HashSet();
    @NotNull
    private final HashSet<Integer> aimedOwnPearls = new HashSet();
    @NotNull
    private final HashMap<Integer, Integer> ownTotalSteps = new HashMap();
    @NotNull
    private final HashMap<Integer, Mark> pearlMarks = new HashMap();
    private final ExecutorService pitchPool = Executors.newSingleThreadExecutor(TrowPearlHelper::pitchPool$lambda$0);
    private volatile long pitchCloudDownUntil;
    @NotNull
    private static final Identifier MARK_TEXTURE;
    private static final int COLOR_ALIGNED;
    private static final int COLOR_IDLE;
    private static final float ALIGN_DEGREES = 10.0f;
    private static final double MARKER_DISTANCE = 15.0;
    private static final double MIN_TARGET_DISTANCE = 7.0;
    private static final double AIM_TOLERANCE_DEGREES = 12.0;
    private static final double DEDUP_RADIUS = 2.5;
    private static final double CONSUME_RADIUS = 4.0;
    private static final float CONSUME_PROGRESS = 0.85f;
    private static final long GRACE_MS = 2000L;
    private static final float APPEAR_MS = 350.0f;
    private static final float DISAPPEAR_MS = 350.0f;
    private static final float DEATH_SPIN_DEGREES = 360.0f;
    private static final float APPEAR_SPIN_DEGREES = 360.0f;
    private static final int SIMULATION_STEPS = 160;
    private static final double GRAVITY = 0.03;
    private static final double DRAG_AIR = 0.99;
    private static final double DRAG_WATER = 0.8;
    private static final float SMOOTHING = 0.9f;
    private static final long PITCH_TTL_MS = 800L;
    private static final boolean CLOUD_MODE;

    public TrowPearlHelper() {
        super("Trow Pearl Helper", "Метка, куда целиться, чтобы попасть жемчугом почти в цель.", Category.UTILS);
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        this.marks.clear();
        this.seenOwnPearls.clear();
        this.aimedOwnPearls.clear();
        this.ownTotalSteps.clear();
        this.pearlMarks.clear();
        this.pitchCloudDownUntil = 0L;
    }

    @EventHandler
    private final void onWorldRender(WorldRenderEvent event) {
        if (event.isPortalPass()) {
            return;
        }
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            TrowPearlHelper $this$onWorldRender_u24lambda_u240 = this;
            boolean bl = false;
            $this$onWorldRender_u24lambda_u240.marks.clear();
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        if (this.mc.world == null) {
            this.marks.clear();
            return;
        }
        long now = System.currentTimeMillis();
        this.updateMarks(now);
        if (!((Collection)this.marks).isEmpty()) {
            this.renderMarks(event, now);
        }
    }

    @Protect(value=Level.STD)
    private final void updateMarks(long now) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        Vec3d vec3d2 = player.getCameraPosVec(1.0f);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getEyePosition(...)");
        Vec3d eye = vec3d2;
        HashSet<Integer> ownPresent = new HashSet<Integer>();
        for (Entity entity : level.getEntities()) {
            boolean validLanding;
            if (!(entity instanceof EnderPearlEntity)) continue;
            boolean mine = Intrinsics.areEqual((Object)((EnderPearlEntity)entity).getOwner(), (Object)player);
            Flight flight = this.simulateFlight(entity);
            Vec3d landing = flight != null ? flight.getLanding() : null;
            boolean bl = validLanding = landing != null && eye.distanceTo(landing) >= 7.0;
            if (!mine) continue;
            int id = ((EnderPearlEntity)entity).getId();
            ownPresent.add(id);
            if (this.seenOwnPearls.add(id)) {
                if (this.thrownAtExistingMark(entity, eye, landing)) {
                    this.aimedOwnPearls.add(id);
                }
                if (flight != null) {
                    ((Map)this.ownTotalSteps).put(id, Math.max(1, flight.getSteps()));
                }
            }
            if (flight != null && landing != null) {
                Mark target;
                Integer n = this.ownTotalSteps.getOrDefault(id, flight.getSteps());
                Intrinsics.checkNotNullExpressionValue((Object)n, (String)"getOrDefault(...)");
                int total = ((Number)n).intValue();
                float progress = 1.0f - (float)flight.getSteps() / (float)Math.max(1, total);
                if (progress >= 0.85f && (target = this.nearest(landing, 4.0)) != null && target.getDyingAt() < 0L) {
                    target.setDyingAt(now);
                }
            }
            if (!validLanding || landing == null || this.aimedOwnPearls.contains(id)) continue;
            this.trackMark(id, landing, now);
        }
        this.seenOwnPearls.retainAll((Collection)ownPresent);
        this.aimedOwnPearls.retainAll((Collection)ownPresent);
        this.ownTotalSteps.keySet().retainAll((Collection)ownPresent);
        this.pearlMarks.keySet().retainAll((Collection)ownPresent);
    }

    private final void trackMark(int pearlId, Vec3d landing, long now) {
        Mark owned = this.pearlMarks.get(pearlId);
        if (owned != null && this.marks.contains(owned)) {
            if (owned.getDyingAt() < 0L) {
                owned.setLanding(landing);
                owned.setLastSeen(now);
            }
            return;
        }
        Mark near = this.nearest(landing, 2.5);
        if (near != null) {
            if (near.getDyingAt() < 0L) {
                near.setLanding(landing);
                near.setLastSeen(now);
            }
            ((Map)this.pearlMarks).put(pearlId, near);
            return;
        }
        Mark m = new Mark(landing, now);
        this.marks.add(m);
        ((Map)this.pearlMarks).put(pearlId, m);
    }

    private final boolean thrownAtExistingMark(Entity pearl, Vec3d eye, Vec3d landing) {
        Vec3d vec3d2 = pearl.getVelocity();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getDeltaMovement(...)");
        Vec3d velocity = vec3d2;
        if (velocity.lengthSquared() < 1.0E-6) {
            return false;
        }
        Vec3d vec3d3 = velocity.normalize();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"normalize(...)");
        Vec3d dir = vec3d3;
        Iterator<Mark> iterator = this.marks.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Mark> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Mark mark = (Mark) (iterator2.next());
            double p = this.launchPitch(mark, eye);
            if (Double.isNaN(p)) {
                if (landing == null || !(mark.getLanding().squaredDistanceTo(landing) < 16.0)) continue;
                return true;
            }
            float[] look = this.lookRotation(eye, mark.getLanding());
            float pitch = (float)(-Math.toDegrees(p));
            Vec3d aimDir = this.viewVector(pitch, look[0]);
            float dot = MathHelper.clamp((float)((float)dir.dotProduct(aimDir)), (float)-1.0f, (float)1.0f);
            if (!(Math.toDegrees(Math.acos(dot)) < 12.0)) continue;
            return true;
        }
        return false;
    }

    @Protect(value=Level.STD)
    private final void renderMarks(WorldRenderEvent event, long now) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        Vec3d vec3d2 = player.getCameraPosVec(event.getPartialTicks());
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getEyePosition(...)");
        Vec3d eye = vec3d2;
        Camera camera2 = event.getCamera();
        if (camera2 == null) {
            Camera camera3 = this.mc.gameRenderer.getCamera();
            camera2 = camera3;
            Intrinsics.checkNotNullExpressionValue((Object)camera3, (String)"getMainCamera(...)");
        }
        Camera camera = camera2;
        Vec3d vec3d3 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"position(...)");
        Vec3d cameraPos = vec3d3;
        Quaternionf quaternionf = camera.getRotation();
        Intrinsics.checkNotNullExpressionValue((Object)quaternionf, (String)"rotation(...)");
        Quaternionf cameraRotation = quaternionf;
        float lerp = MathHelper.clamp((float)0.100000024f, (float)0.05f, (float)1.0f);
        float size = this.markerSize.getFloat();
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        RenderLayer renderLayer2 = ClientPipelines.MARKER.apply(MARK_TEXTURE);
        Intrinsics.checkNotNullExpressionValue((Object)renderLayer2, (String)"apply(...)");
        RenderLayer renderType = renderLayer2;
        VertexConsumer vertexConsumer2 = provider.getBuffer(renderType);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer buffer = vertexConsumer2;
        boolean drewAny = false;
        Iterator<Mark> iterator = this.marks.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Mark> it = iterator;
        while (it.hasNext()) {
            Vec3d markPos;
            Mark mark = (Mark) (it.next());
            if (mark.getDyingAt() < 0L && now - mark.getLastSeen() > 2000L) {
                mark.setDyingAt(now);
            }
            float appear = MathHelper.clamp((float)((float)(now - mark.getBornAt()) / 350.0f), (float)0.0f, (float)1.0f);
            float alpha = TrowPearlHelper.Companion.easeOutCubic(appear);
            float scale = TrowPearlHelper.Companion.easeOutCubic(appear);
            float spin = (1.0f - TrowPearlHelper.Companion.easeOutBack(appear)) * 360.0f;
            if (mark.getDyingAt() >= 0L) {
                float die = (float)(now - mark.getDyingAt()) / 350.0f;
                if (die >= 1.0f) {
                    it.remove();
                    continue;
                }
                float clampedDie = MathHelper.clamp((float)die, (float)0.0f, (float)1.0f);
                alpha *= 1.0f - clampedDie;
                scale *= 1.0f - TrowPearlHelper.Companion.easeInCubic(clampedDie);
                spin = TrowPearlHelper.Companion.easeOutBack(clampedDie) * 360.0f;
            }
            if (alpha <= 0.01f || scale <= 0.01f) continue;
            float[] look = this.lookRotation(eye, mark.getLanding());
            float yaw = look[0];
            float pitch = (float)(-Math.toDegrees(this.launchPitch(mark, eye)));
            markPos = eye.add(this.viewVector(pitch, yaw).multiply(15.0));
            if (!this.inFront(markPos) || !TrowPearlHelper.Companion.isFinite(markPos)) continue;
            Vector3f viewOffset = new Quaternionf((Quaternionfc)cameraRotation).conjugate().transform(new Vector3f((float)(markPos.x - cameraPos.x), (float)(markPos.y - cameraPos.y), (float)(markPos.z - cameraPos.z)));
            if (!mark.getViewInit()) {
                mark.getView()[0] = viewOffset.x;
                mark.getView()[1] = viewOffset.y;
                mark.getView()[2] = viewOffset.z;
                mark.setViewInit(true);
            } else {
                float[] fArray = mark.getView();
                fArray[0] = fArray[0] + (viewOffset.x - mark.getView()[0]) * lerp;
                fArray = mark.getView();
                fArray[1] = fArray[1] + (viewOffset.y - mark.getView()[1]) * lerp;
                fArray = mark.getView();
                fArray[2] = fArray[2] + (viewOffset.z - mark.getView()[2]) * lerp;
            }
            Vector3f drawOffset = new Quaternionf((Quaternionfc)cameraRotation).transform(new Vector3f(mark.getView()[0], mark.getView()[1], mark.getView()[2]));
            boolean aligned = TrowPearlHelper.Companion.shortestAngle(yaw - player.getYaw()) < 10.0f && Math.abs(player.getPitch() - pitch) < 10.0f;
            int color = ColorEngine.multAlpha(aligned ? COLOR_ALIGNED : COLOR_IDLE, alpha);
            this.drawMarker(stack, buffer, cameraRotation, spin, cameraPos.x + (double)drawOffset.x, cameraPos.y + (double)drawOffset.y, cameraPos.z + (double)drawOffset.z, cameraPos, size * scale, color);
            drewAny = true;
        }
        if (drewAny) {
            provider.draw(renderType);
        }
    }

    @Protect(value=Level.STD)
    private final void drawMarker(MatrixStack stack, VertexConsumer buffer, Quaternionf cameraRotation, float spinDegrees, double worldX, double worldY, double worldZ, Vec3d cameraPos, float size, int color) {
        float half = size * 0.5f;
        stack.push();
        stack.translate(worldX - cameraPos.x, worldY - cameraPos.y, worldZ - cameraPos.z);
        stack.multiply((Quaternionfc)cameraRotation);
        if (!(spinDegrees == 0.0f)) {
            stack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(spinDegrees));
        }
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        buffer.vertex(pose, -half, -half, 0.0f).texture(0.0f, 1.0f).color(color);
        buffer.vertex(pose, half, -half, 0.0f).texture(1.0f, 1.0f).color(color);
        buffer.vertex(pose, half, half, 0.0f).texture(1.0f, 0.0f).color(color);
        buffer.vertex(pose, -half, half, 0.0f).texture(0.0f, 0.0f).color(color);
        stack.pop();
    }

    private final Mark nearest(Vec3d landing, double radius) {
        Mark best = null;
        double bestSqr = radius * radius;
        Iterator<Mark> iterator = this.marks.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Mark> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Mark mark = (Mark) (iterator2.next());
            double d = mark.getLanding().squaredDistanceTo(landing);
            if (!(d < bestSqr)) continue;
            bestSqr = d;
            best = mark;
        }
        return best;
    }

    private final Flight simulateFlight(Entity pearl) {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return null;
        }
        ClientWorld level = clientWorld3;
        Vec3d vec3d2 = pearl.getEntityPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d pos = vec3d2;
        Vec3d vec3d3 = pearl.getVelocity();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"getDeltaMovement(...)");
        Vec3d velocity = vec3d3;
        for (int i = 0; i < 160; ++i) {
            boolean inWater = level.getBlockState(BlockPos.ofFloored((Position)((Position)pos))).isOf(Blocks.WATER);
            velocity = velocity.subtract(0.0, 0.03, 0.0).multiply(inWater ? 0.8 : 0.99);
            Vec3d next = pos.add(velocity);
            BlockHitResult hit = level.raycast(new RaycastContext(pos, next, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, pearl));
            if (hit != null && hit.getType() == HitResult.Type.BLOCK) {
                return new Flight(hit.getPos(), i + 1);
            }
            pos = next;
        }
        return null;
    }

    private final double launchPitch(Mark mark, Vec3d eye) {
        boolean stale;
        BlockPos blockPos2 = BlockPos.ofFloored((Position)((Position)mark.getLanding()));
        Intrinsics.checkNotNullExpressionValue((Object)blockPos2, (String)"containing(...)");
        BlockPos block = blockPos2;
        double bx = (double)block.getX() + 0.5;
        double by = (double)block.getY() + 1.0;
        double bz = (double)block.getZ() + 0.5;
        if (!CLOUD_MODE) {
            double d;
            try {
                d = TrowPearlHelper.Companion.solvePitchCore(bx, by, bz, eye.x, eye.y, eye.z);
            }
            catch (Throwable t) {
                d = Double.NaN;
            }
            return d;
        }
        long now = System.currentTimeMillis();
        boolean bl = stale = Double.isNaN(mark.getCloudPitch()) || now - mark.getCloudPitchAt() > 800L;
        if (stale && now >= this.pitchCloudDownUntil && mark.getPitchInFlight().compareAndSet(false, true)) {
            double ex = eye.x;
            double ey = eye.y;
            double ez = eye.z;
            this.pitchPool.execute(() -> TrowPearlHelper.launchPitch$lambda$0(bx, by, bz, ex, ey, ez, mark, this));
        }
        return mark.getCloudPitch();
    }

    @Protect(value=Level.MAX)
    private final float[] lookRotation(Vec3d eye, Vec3d target) {
        double dx = target.x - eye.x;
        double dy = target.y - eye.y;
        double dz = target.z - eye.z;
        double horizontal = Math.sqrt(dx * dx + dz * dz);
        float yaw = (float)(Math.toDegrees(Math.atan2(dz, dx)) - 90.0);
        float pitch = (float)(-Math.toDegrees(Math.atan2(dy, horizontal)));
        float[] fArray = new float[]{yaw, pitch};
        return fArray;
    }

    @Protect(value=Level.MAX)
    private final Vec3d viewVector(float pitch, float yaw) {
        double pitchRad = (double)pitch * (double)((float)Math.PI / 180);
        double yawRad = -((double)yaw) * (double)((float)Math.PI / 180);
        double cosPitch = Math.cos(pitchRad);
        return new Vec3d(Math.sin(yawRad) * cosPitch, -Math.sin(pitchRad), Math.cos(yawRad) * cosPitch);
    }

    @Protect(value=Level.MAX)
    private final boolean inFront(Vec3d pos) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return false;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        Vec3d vec3d2 = player.getRotationVector();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getLookAngle(...)");
        Vec3d look = vec3d2;
        Vec3d vec3d3 = pos.subtract(player.getEntityPos()).normalize();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"normalize(...)");
        Vec3d toPos = vec3d3;
        return look.dotProduct(toPos) > 0.1;
    }

    private static final Thread pitchPool$lambda$0(Runnable r) {
        Thread t = new Thread(r, "kimiko-pitch-cloud");
        t.setDaemon(true);
        return t;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static final void launchPitch$lambda$0(double $bx, double $by, double $bz, double $ex, double $ey, double $ez, Mark $mark, TrowPearlHelper this$0) {
        try {
            double v = TrowPearlHelper.Companion.solvePitchCore($bx, $by, $bz, $ex, $ey, $ez);
            long t = System.currentTimeMillis();
            if (Double.isNaN($mark.getCloudPitch())) {
                $mark.setBornAt(t);
            }
            $mark.setCloudPitch(v);
            $mark.setCloudPitchAt(t);
        }
        catch (Throwable cloudDown) {
            this$0.pitchCloudDownUntil = System.currentTimeMillis() + 3000L;
        }
        finally {
            $mark.getPitchInFlight().set(false);
        }
    }

    @JvmStatic
    private static final double solvePitchCore(double bx, double by, double bz, double ex, double ey, double ez) {
        return TrowPearlHelper.Companion.solvePitchCore(bx, by, bz, ex, ey, ez);
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/features/pearl/pearl-mark.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        MARK_TEXTURE = identifier2;
        COLOR_ALIGNED = ColorEngine.rgba(85, 255, 106, 255);
        COLOR_IDLE = ColorEngine.rgba(255, 255, 255, 255);
        CLOUD_MODE = System.getProperty("cloud.url") != null;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u000f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003JC\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0003b\u0002\b\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u0016J\u0017\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0016R\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010 R\u0014\u0010\"\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010%R\u0014\u0010'\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010%R\u0014\u0010(\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010%R\u0014\u0010)\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010%R\u0014\u0010*\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010#R\u0014\u0010,\u001a\u00020+8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010#R\u0014\u0010/\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u0010#R\u0014\u00100\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u0010#R\u0014\u00101\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u0010#R\u0014\u00102\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u0010 R\u0014\u00103\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b3\u0010%R\u0014\u00104\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u0010%R\u0014\u00105\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u0010%R\u0014\u00106\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u0010#R\u0014\u00107\u001a\u00020+8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u0010-R\u0014\u00108\u001a\u00020\u00108\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00109\u00a8\u0006:"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/TrowPearlHelper.Companion;", "", "<init>", "()V", "", "bx", "by", "bz", "ex", "ey", "ez", "Lkotlin/jvm/JvmStatic;", "solvePitchCore", "(DDDDDD)D", "Lnet/minecraft/Vec3d;", "v", "", "isFinite", "(Lnet/minecraft/Vec3d;)Z", "", "delta", "shortestAngle", "(F)F", "t", "easeOutCubic", "easeInCubic", "easeOutBack", "Lnet/minecraft/Identifier;", "MARK_TEXTURE", "Lnet/minecraft/Identifier;", "", "COLOR_ALIGNED", "I", "COLOR_IDLE", "ALIGN_DEGREES", "F", "MARKER_DISTANCE", "D", "MIN_TARGET_DISTANCE", "AIM_TOLERANCE_DEGREES", "DEDUP_RADIUS", "CONSUME_RADIUS", "CONSUME_PROGRESS", "", "GRACE_MS", "J", "APPEAR_MS", "DISAPPEAR_MS", "DEATH_SPIN_DEGREES", "APPEAR_SPIN_DEGREES", "SIMULATION_STEPS", "GRAVITY", "DRAG_AIR", "DRAG_WATER", "SMOOTHING", "PITCH_TTL_MS", "CLOUD_MODE", "Z", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        private final double solvePitchCore(double bx, double by, double bz, double ex, double ey, double ez) {
            double a = Math.hypot(bx - ex, bz - ez);
            double y2 = 6.125 * (by - ey);
            y2 = 0.05 * (0.05 * (a * a) + y2);
            y2 = Math.sqrt(Math.max(0.0, 9.37890625 - y2));
            double d = 3.0625 - y2;
            double high = Math.atan2(d * d + y2, 0.05 * a);
            double low = Math.atan2(d, 0.05 * a);
            return Math.min(high, low);
        }

        private final boolean isFinite(Vec3d v) {
            return Double.isFinite(v.x) && Double.isFinite(v.y) && Double.isFinite(v.z);
        }

        private final float shortestAngle(float delta) {
            return Math.abs((delta % 360.0f + 540.0f) % 360.0f - 180.0f);
        }

        private final float easeOutCubic(float t) {
            float u = 1.0f - t;
            return 1.0f - u * u * u;
        }

        private final float easeInCubic(float t) {
            return t * t * t;
        }

        private final float easeOutBack(float t) {
            float c1 = 1.70158f;
            float c3 = c1 + 1.0f;
            float u = t - 1.0f;
            return 1.0f + c3 * u * u * u + c1 * u * u;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0012\u0010\u000bJ\u0011\u0010\u0014\u001a\u00020\u0013H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0019\u0010\u000b\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/TrowPearlHelper$Flight;", "", "Lnet/minecraft/Vec3d;", "landing", "", "steps", "<init>", "(Lnet/minecraft/Vec3d;I)V", "component1", "()Lnet/minecraft/Vec3d;", "component2", "()I", "copy", "(Lnet/minecraft/Vec3d;I)Lrtx/kimiko/api/modules/impl/Utils/TrowPearlHelper$Flight;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/Vec3d;", "getLanding", "I", "getSteps", "rtx.kimiko:kimiko"})
    private static final class Flight {
        @NotNull
        private final Vec3d landing;
        private final int steps;

        public Flight(@NotNull Vec3d landing, int steps) {
            Intrinsics.checkNotNullParameter((Object)landing, (String)"landing");
            this.landing = landing;
            this.steps = steps;
        }

        @NotNull
        public final Vec3d getLanding() {
            return this.landing;
        }

        public final int getSteps() {
            return this.steps;
        }

        @NotNull
        public final Vec3d component1() {
            return this.landing;
        }

        public final int component2() {
            return this.steps;
        }

        @NotNull
        public final Flight copy(@NotNull Vec3d landing, int steps) {
            Intrinsics.checkNotNullParameter((Object)landing, (String)"landing");
            return new Flight(landing, steps);
        }

        public static /* synthetic */ Flight copy$default(Flight flight, Vec3d vec3d2, int n, int n2, Object object) {
            if ((n2 & 1) != 0) {
                vec3d2 = flight.landing;
            }
            if ((n2 & 2) != 0) {
                n = flight.steps;
            }
            return flight.copy(vec3d2, n);
        }

        @NotNull
        public String toString() {
            return "Flight(landing=" + this.landing + ", steps=" + this.steps + ")";
        }

        public int hashCode() {
            int result = this.landing.hashCode();
            result = result * 31 + Integer.hashCode(this.steps);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Flight)) {
                return false;
            }
            Flight flight = (Flight)other;
            if (!Intrinsics.areEqual((Object)this.landing, (Object)flight.landing)) {
                return false;
            }
            return this.steps == flight.steps;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\"\u0010\u0015\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\r\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u0017\u0010\u0019\u001a\u00020\u00188\u0006\u00a2\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\"\u0010\u001e\u001a\u00020\u001d8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\"\u0010%\u001a\u00020$8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\"\u0010+\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b+\u0010\r\u001a\u0004\b,\u0010\u000f\"\u0004\b-\u0010\u0011R\u0017\u0010/\u001a\u00020.8\u0006\u00a2\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102\u00a8\u00063"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/TrowPearlHelper$Mark;", "", "Lnet/minecraft/Vec3d;", "landing", "", "bornAt", "<init>", "(Lnet/minecraft/Vec3d;J)V", "Lnet/minecraft/Vec3d;", "getLanding", "()Lnet/minecraft/Vec3d;", "setLanding", "(Lnet/minecraft/Vec3d;)V", "J", "getBornAt", "()J", "setBornAt", "(J)V", "lastSeen", "getLastSeen", "setLastSeen", "dyingAt", "getDyingAt", "setDyingAt", "", "view", "[F", "getView", "()[F", "", "viewInit", "Z", "getViewInit", "()Z", "setViewInit", "(Z)V", "", "cloudPitch", "D", "getCloudPitch", "()D", "setCloudPitch", "(D)V", "cloudPitchAt", "getCloudPitchAt", "setCloudPitchAt", "Ljava/util/concurrent/atomic/AtomicBoolean;", "pitchInFlight", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getPitchInFlight", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "rtx.kimiko:kimiko"})
    private static final class Mark {
        @NotNull
        private Vec3d landing;
        private volatile long bornAt;
        private long lastSeen;
        private long dyingAt;
        @NotNull
        private final float[] view;
        private boolean viewInit;
        private volatile double cloudPitch;
        private volatile long cloudPitchAt;
        @NotNull
        private final AtomicBoolean pitchInFlight;

        public Mark(@NotNull Vec3d landing, long bornAt) {
            Intrinsics.checkNotNullParameter((Object)landing, (String)"landing");
            this.landing = landing;
            this.lastSeen = this.bornAt = bornAt;
            this.dyingAt = -1L;
            this.view = new float[3];
            this.cloudPitch = Double.NaN;
            this.pitchInFlight = new AtomicBoolean(false);
        }

        @NotNull
        public final Vec3d getLanding() {
            return this.landing;
        }

        public final void setLanding(@NotNull Vec3d vec3d2) {
            Intrinsics.checkNotNullParameter((Object)vec3d2, (String)"<set-?>");
            this.landing = vec3d2;
        }

        public final long getBornAt() {
            return this.bornAt;
        }

        public final void setBornAt(long l) {
            this.bornAt = l;
        }

        public final long getLastSeen() {
            return this.lastSeen;
        }

        public final void setLastSeen(long l) {
            this.lastSeen = l;
        }

        public final long getDyingAt() {
            return this.dyingAt;
        }

        public final void setDyingAt(long l) {
            this.dyingAt = l;
        }

        @NotNull
        public final float[] getView() {
            return this.view;
        }

        public final boolean getViewInit() {
            return this.viewInit;
        }

        public final void setViewInit(boolean bl) {
            this.viewInit = bl;
        }

        public final double getCloudPitch() {
            return this.cloudPitch;
        }

        public final void setCloudPitch(double d) {
            this.cloudPitch = d;
        }

        public final long getCloudPitchAt() {
            return this.cloudPitchAt;
        }

        public final void setCloudPitchAt(long l) {
            this.cloudPitchAt = l;
        }

        @NotNull
        public final AtomicBoolean getPitchInFlight() {
            return this.pitchInFlight;
        }
    }
}

