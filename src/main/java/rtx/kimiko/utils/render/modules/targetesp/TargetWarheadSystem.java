/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Vector3f
 */
package rtx.kimiko.utils.render.modules.targetesp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import rtx.kimiko.utils.render.modules.post.trailecho.TrailEchoGlass;
import rtx.kimiko.utils.render.modules.targetesp.TargetEspColorProvider;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 F2\u00020\u0001:\u0004GHIFB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\u0003J\r\u0010\t\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\u0003J]\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0019\u00a2\u0006\u0004\b\u001b\u0010\u001cJG\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ?\u0010!\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b!\u0010\"J\u001f\u0010%\u001a\u00020\u00072\u0006\u0010$\u001a\u00020#2\u0006\u0010\u0015\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b%\u0010&J\u0017\u0010)\u001a\u00020\u00072\u0006\u0010(\u001a\u00020'H\u0002\u00a2\u0006\u0004\b)\u0010*J!\u0010+\u001a\u0004\u0018\u00010'2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b+\u0010,J?\u00101\u001a\u00020\u00072\u0006\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u00020-2\u0006\u00100\u001a\u00020-2\u0006\u0010(\u001a\u00020'2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b1\u00102J\u001f\u00103\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b3\u00104R$\u00107\u001a\u0012\u0012\u0004\u0012\u00020'05j\b\u0012\u0004\u0012\u00020'`68\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00108R$\u0010:\u001a\u0012\u0012\u0004\u0012\u00020905j\b\u0012\u0004\u0012\u000209`68\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u00108R\u0014\u0010<\u001a\u00020;8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0016\u0010>\u001a\u00020#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0016\u0010@\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u0010AR\u0016\u0010B\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0016\u0010D\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010E\u00a8\u0006J"}, d2={"Lrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem;", "", "<init>", "()V", "", "isIdle", "()Z", "", "requestLaunch", "clear", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "provider", "Lnet/minecraft/MatrixStack$Entry;", "pose", "Lnet/minecraft/Vec3d;", "cameraPos", "basePos", "", "bbWidth", "bbHeight", "", "now", "dt", "Lrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem$Params;", "params", "Lrtx/kimiko/utils/render/modules/targetesp/TargetEspColorProvider;", "colors", "render", "(Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;FFJFLrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem$Params;Lrtx/kimiko/utils/render/modules/targetesp/TargetEspColorProvider;)V", "update", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;FFJFLrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem$Params;)V", "Lnet/minecraft/VertexConsumer;", "consumer", "emitBodies", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;JLrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem$Params;Lrtx/kimiko/utils/render/modules/targetesp/TargetEspColorProvider;)Z", "", "count", "syncCount", "(IJ)V", "Lrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem$Warhead;", "w", "randomizePose", "(Lrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem$Warhead;)V", "pickLaunch", "(Lnet/minecraft/Vec3d;J)Lrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem$Warhead;", "", "ix", "iy", "iz", "shatter", "(DDDLrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem$Warhead;Lrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem$Params;J)V", "updateShards", "(JF)V", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "warheads", "Ljava/util/ArrayList;", "Lrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem$Shard;", "shards", "Ljava/util/Random;", "random", "Ljava/util/Random;", "pendingLaunches", "I", "orbitAngle", "F", "seedCounter", "J", "hidden", "Z", "Companion", "Params", "Warhead", "Shard", "rtx.kimiko:kimiko"})
public final class TargetWarheadSystem {
    @NotNull
    public static final Companion Companion;
    @NotNull
    private final ArrayList<Warhead> warheads = new ArrayList();
    @NotNull
    private final ArrayList<Shard> shards = new ArrayList();
    @NotNull
    private final Random random = new Random();
    private int pendingLaunches;
    private float orbitAngle;
    private long seedCounter = 1L;
    private boolean hidden;
    private static final float TWO_PI = (float)Math.PI * 2;
    private static final int SIDES = 10;
    private static final float CONE_RADIUS_RATIO = 0.17f;
    private static final long GROW_MS = 380L;
    private static final long RESPAWN_DELAY_MS = 140L;
    private static final float RECOIL_T = 0.12f;
    private static final float RECOIL_V = 3.6f;
    private static final float ACCEL_T = 0.08f;
    private static final float CARRY_T = 0.09f;
    private static final int MAX_SHARDS = 160;
    private static final long HIDE_STAGGER_MS = 45L;
    private static final float AIM_FOLLOW = 14.0f;
    @NotNull
    private static final float[] PROFILE_T;
    @NotNull
    private static final float[] PROFILE_R;
    @NotNull
    private static final float[] COS;
    @NotNull
    private static final float[] SIN;
    private static final Vector3f LIGHT;

    public final boolean isIdle() {
        if (!((Collection)this.shards).isEmpty()) {
            return false;
        }
        Iterator<Warhead> iterator = this.warheads.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Warhead> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Warhead w = (Warhead) (iterator2.next());
            if (!(w.getScale() > 0.001f)) continue;
            return false;
        }
        return true;
    }

    public final void requestLaunch() {
        this.pendingLaunches = Math.min(this.pendingLaunches + 1, 3);
    }

    public final void clear() {
        this.warheads.clear();
        this.shards.clear();
        this.pendingLaunches = 0;
        this.hidden = false;
    }

    public final void render(@NotNull VertexConsumerProvider.Immediate provider, @NotNull MatrixStack.Entry pose, @NotNull Vec3d cameraPos, @NotNull Vec3d basePos, float bbWidth, float bbHeight, long now, float dt, @NotNull Params params, @NotNull TargetEspColorProvider colors) {
        Intrinsics.checkNotNullParameter((Object)provider, (String)"provider");
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)cameraPos, (String)"cameraPos");
        Intrinsics.checkNotNullParameter((Object)basePos, (String)"basePos");
        Intrinsics.checkNotNullParameter((Object)params, (String)"params");
        Intrinsics.checkNotNullParameter((Object)colors, (String)"colors");
        this.update(cameraPos, basePos, bbWidth, bbHeight, now, dt, params);
        boolean glassOn = TrailEchoGlass.capture(MinecraftClient.getInstance().getFramebuffer());
        RenderLayer type = glassOn ? (params.getThroughWalls() ? ClientPipelines.TARGET_WARHEAD_GLASS_NODEPTH : ClientPipelines.TRAIL_ECHO_GLASS) : (params.getThroughWalls() ? ClientPipelines.TARGET_CIRCLE_NODEPTH : ClientPipelines.WORLD_PARTICLES_COLOR);
        VertexConsumer vertexConsumer2 = provider.getBuffer(type);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer consumer = vertexConsumer2;
        if (this.emitBodies(consumer, pose, cameraPos, now, params, colors)) {
            provider.draw(type);
        }
    }

    private final void update(Vec3d cameraPos, Vec3d basePos, float bbWidth, float bbHeight, long now, float dt, Params params) {
        this.syncCount(params.getCount(), now);
        if (params.getVisible() == this.hidden) {
            this.hidden = !params.getVisible();
            int n = ((Collection)this.warheads).size();
            for (int i = 0; i < n; ++i) {
                Warhead w = (Warhead) (this.warheads.get(i));
                if (this.hidden) {
                    w.setHideMs(now + (long)i * 45L);
                    w.setHideFrom(Math.max(0.0f, w.getScale()));
                    continue;
                }
                w.setShowMs(now + (long)i * 45L);
                w.setShowFrom(MathHelper.clamp((float)w.getScale(), (float)0.0f, (float)1.0f));
            }
            if (!this.hidden) {
                this.pendingLaunches = 0;
            }
        }
        double cx = basePos.x;
        double cy = basePos.y + (double)bbHeight * 0.5;
        double cz = basePos.z;
        this.orbitAngle = (this.orbitAngle + params.getSpinSpeed() * dt) % ((float)Math.PI * 2);
        float radius = Math.max(0.5f, bbWidth * 0.5f + params.getOrbitDistance());
        float semiA = bbWidth * 0.5f + 0.04f;
        float semiB = bbHeight * 0.5f + 0.04f;
        double minY = basePos.y + (double)bbHeight * 0.12;
        double maxY = basePos.y + (double)bbHeight * 0.88;
        Iterator<Warhead> iterator = this.warheads.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Warhead> iterator2 = iterator;
        while (iterator2.hasNext()) {
            float fz;
            float fy;
            Warhead w = (Warhead) (iterator2.next());
            float theta = w.getPhase() + this.orbitAngle;
            float cosT = (float)Math.cos(theta);
            float sinT = (float)Math.sin(theta);
            double bob = Math.sin((double)now / 420.0 + (double)w.getPhase() * 3.0) * 0.04;
            double ox = cx + (double)(cosT * radius);
            double oy = basePos.y + (double)(bbHeight * w.getLevel()) + bob;
            double oz = cz + (double)(sinT * radius);
            double drop = Math.tan(w.getPitch()) * (double)radius * (w.getLevel() >= 0.5f ? 1.0 : -1.0);
            double aimY = MathHelper.clamp((double)(oy - drop), (double)minY, (double)maxY);
            w.setAimY(aimY);
            float ddx = (float)(cx - ox);
            float ddy = (float)(aimY - oy);
            float ddz = (float)(cz - oz);
            float len = (float)Math.sqrt(ddx * ddx + ddy * ddy + ddz * ddz);
            if (len < 1.0E-4f) {
                len = 1.0f;
            }
            ddx /= len;
            ddy /= len;
            ddz /= len;
            if (this.hidden) {
                float hp = MathHelper.clamp((float)((float)(now - w.getHideMs()) / 380.0f), (float)0.0f, (float)1.0f);
                w.setScale(Math.max(0.0f, w.getHideFrom() * (1.0f - TargetWarheadSystem.Companion.easeInBack(hp))));
            } else {
                float f;
                float grow = MathHelper.clamp((float)((float)(now - w.getSpawnMs()) / 380.0f), (float)0.0f, (float)1.0f);
                float growScale = TargetWarheadSystem.Companion.easeOutBack(grow);
                if (now < w.getShowMs()) {
                    f = w.getShowFrom();
                } else {
                    float sp = MathHelper.clamp((float)((float)(now - w.getShowMs()) / 380.0f), (float)0.0f, (float)1.0f);
                    f = w.getShowFrom() + (1.0f - w.getShowFrom()) * TargetWarheadSystem.Companion.easeOutBack(sp);
                }
                float showScale = f;
                w.setScale(Math.min(growScale, showScale));
            }
            w.setStretch(1.0f);
            if (!w.getLaunching()) {
                if (!w.getSettled() || now < w.getSpawnMs()) {
                    w.setX(ox);
                    w.setY(oy);
                    w.setZ(oz);
                    w.setVx(-sinT * radius * params.getSpinSpeed());
                    w.setVy(0.0f);
                    w.setVz(cosT * radius * params.getSpinSpeed());
                    w.setDx(ddx);
                    w.setDy(ddy);
                    w.setDz(ddz);
                    w.setSettled(true);
                } else {
                    float tz;
                    float ty;
                    float tx;
                    float tl;
                    float sdt = Math.min(dt, 0.05f);
                    float k = w.getOmega() * w.getOmega();
                    float c = 2.0f * w.getZeta() * w.getOmega();
                    w.setVx(w.getVx() + (k * (float)(ox - w.getX()) - c * w.getVx()) * sdt);
                    w.setVy(w.getVy() + (k * (float)(oy - w.getY()) - c * w.getVy()) * sdt);
                    w.setVz(w.getVz() + (k * (float)(oz - w.getZ()) - c * w.getVz()) * sdt);
                    w.setX(w.getX() + (double)(w.getVx() * sdt));
                    w.setY(w.getY() + (double)(w.getVy() * sdt));
                    w.setZ(w.getZ() + (double)(w.getVz() * sdt));
                    double hx = w.getX() - cx;
                    double hz = w.getZ() - cz;
                    double hd = Math.sqrt(hx * hx + hz * hz);
                    double minD = (double)bbWidth * 0.5 + (double)params.getSize() * 0.55;
                    if (hd < minD && hd > 1.0E-4) {
                        double nx = hx / hd;
                        double nz = hz / hd;
                        w.setX(cx + nx * minD);
                        w.setZ(cz + nz * minD);
                        float inward = (float)((double)w.getVx() * nx + (double)w.getVz() * nz);
                        if (inward < 0.0f) {
                            w.setVx(w.getVx() - (float)nx * inward);
                            w.setVz(w.getVz() - (float)nz * inward);
                        }
                    }
                    if ((tl = (float)Math.sqrt((tx = (float)(cx - w.getX())) * tx + (ty = (float)(w.getAimY() - w.getY())) * ty + (tz = (float)(cz - w.getZ())) * tz)) > 1.0E-4f) {
                        float f = Math.min(1.0f, 14.0f * sdt);
                        w.setDx(w.getDx() + ((tx /= tl) - w.getDx()) * f);
                        w.setDy(w.getDy() + ((ty /= tl) - w.getDy()) * f);
                        w.setDz(w.getDz() + ((tz /= tl) - w.getDz()) * f);
                        float dl = (float)Math.sqrt(w.getDx() * w.getDx() + w.getDy() * w.getDy() + w.getDz() * w.getDz());
                        if (dl > 1.0E-4f) {
                            w.setDx(w.getDx() / dl);
                            w.setDy(w.getDy() / dl);
                            w.setDz(w.getDz() / dl);
                        }
                    }
                }
                w.setCarryVx(w.getVx());
                w.setCarryVz(w.getVz());
                continue;
            }
            float t = (float)(now - w.getLaunchMs()) / 1000.0f;
            float fx = (float)(cx - w.getX());
            float dist = (float)Math.sqrt(fx * fx + (fy = (float)(w.getAimY() - w.getY())) * fy + (fz = (float)(cz - w.getZ())) * fz);
            if (dist > 1.0E-4f) {
                w.setDx(fx /= dist);
                w.setDy(fy /= dist);
                w.setDz(fz /= dist);
            }
            float v = t < 0.12f ? -3.6f * params.getSize() * (float)Math.sin(Math.PI * (double)t / (double)0.12f) : params.getFlightSpeed() * TargetWarheadSystem.Companion.smoothstep((t - 0.12f) / 0.08f);
            float carry = (float)Math.exp(-t / 0.09f);
            w.setStretch(1.0f + 0.7f * MathHelper.clamp((float)(v / params.getFlightSpeed()), (float)0.0f, (float)1.0f));
            float entry = TargetWarheadSystem.Companion.rayEllipsoid((float)(w.getX() - cx), (float)(w.getY() - cy), (float)(w.getZ() - cz), w.getDx(), w.getDy(), w.getDz(), semiA, semiB);
            float remaining = entry >= 0.0f ? Math.min(entry, dist) : dist;
            float advance = v * dt;
            if (v > 0.0f && advance >= remaining) {
                double ix = w.getX() + (double)(w.getDx() * remaining);
                double iy = w.getY() + (double)(w.getDy() * remaining);
                double iz = w.getZ() + (double)(w.getDz() * remaining);
                this.shatter(ix, iy, iz, w, params, now);
                w.setLaunching(false);
                w.setSpawnMs(now + 140L);
                long l = this.seedCounter;
                this.seedCounter = l + 1L;
                w.setSeed(l);
                w.setColorIndex((w.getColorIndex() + 47) % 360);
                w.setScale(0.0f);
                this.randomizePose(w);
                w.setSettled(false);
                w.setX(ox);
                w.setY(oy);
                w.setZ(oz);
                continue;
            }
            w.setX(w.getX() + (double)(w.getDx() * advance + w.getCarryVx() * carry * dt));
            w.setY(w.getY() + (double)(w.getDy() * advance));
            w.setZ(w.getZ() + (double)(w.getDz() * advance + w.getCarryVz() * carry * dt));
        }
        while (this.pendingLaunches > 0 && !this.hidden) {
            Warhead pick = this.pickLaunch(cameraPos, now);
            if (pick == null) break;
            pick.setLaunching(true);
            pick.setLaunchMs(now);
            --this.pendingLaunches;
        }
        this.updateShards(now, dt);
    }

    private final boolean emitBodies(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d cameraPos, long now, Params params, TargetEspColorProvider colors) {
        boolean drew = false;
        double camX = cameraPos.x;
        double camY = cameraPos.y;
        double camZ = cameraPos.z;
        for (Warhead w : this.warheads) {
            if (now < w.getSpawnMs() || w.getScale() <= 0.01f) continue;
            float rx = (float)(w.getX() - camX);
            float ry = (float)(w.getY() - camY);
            float rz = (float)(w.getZ() - camZ);
            float camDist = (float)Math.sqrt(rx * rx + ry * ry + rz * rz);
            float nearFade = MathHelper.clamp((float)((camDist - 0.45f) / 0.6f), (float)0.0f, (float)1.0f);
            float a = params.getAlpha() * params.getGlassAlpha() * nearFade;
            if (a <= 0.02f) continue;
            int color = colors.color(w.getColorIndex(), a);
            float length = params.getSize() * w.getScale();
            float radiusCone = params.getSize() * 0.17f * w.getScale();
            float tipX = rx + w.getDx() * length * 0.5f;
            float tipY = ry + w.getDy() * length * 0.5f;
            float tipZ = rz + w.getDz() * length * 0.5f;
            TargetWarheadSystem.Companion.emitCone(consumer, pose, tipX, tipY, tipZ, w.getDx(), w.getDy(), w.getDz(), length * w.getStretch(), radiusCone, color, params.getParamU(), params.getParamV());
            drew = true;
        }
        for (Shard s : this.shards) {
            float t = (float)(now - s.getSpawnMs()) / (float)s.getLifeMs();
            float env = 1.0f - MathHelper.clamp((float)((t - 0.6f) / 0.4f), (float)0.0f, (float)1.0f);
            if (env <= 0.01f) continue;
            float rx = (float)(s.getX() - camX);
            float ry = (float)(s.getY() - camY);
            float rz = (float)(s.getZ() - camZ);
            float camDist = (float)Math.sqrt(rx * rx + ry * ry + rz * rz);
            float nearFade = MathHelper.clamp((float)((camDist - 0.3f) / 0.5f), (float)0.0f, (float)1.0f);
            float a = params.getAlpha() * Math.min(1.0f, params.getGlassAlpha() + 0.1f) * env * nearFade;
            if (a <= 0.02f) continue;
            int color = colors.color(s.getColorIndex(), a);
            TargetWarheadSystem.Companion.emitShard(consumer, pose, rx, ry, rz, s, env, color, params.getParamU(), params.getParamV());
            drew = true;
        }
        return drew;
    }

    private final void syncCount(int count, long now) {
        int i;
        int target = Math.max(1, count);
        if (this.warheads.size() == target) {
            return;
        }
        this.warheads.clear();
        int[] order = new int[target];
        for (int k = 0; k < target; ++k) {
            order[k] = k;
        }
        for (i = target - 1; 0 < i; --i) {
            int j = this.random.nextInt(i + 1);
            int tmp = order[i];
            order[i] = order[j];
            order[j] = tmp;
        }
        for (i = 0; i < target; ++i) {
            float phase = (float)Math.PI * 2 * (float)i / (float)target;
            long l = this.seedCounter;
            this.seedCounter = l + 1L;
            Warhead w = new Warhead(phase, i * 360 / target % 360, now + (long)i * 60L, l);
            this.randomizePose(w);
            w.setLevel(0.15f + 0.7f * ((float)order[i] + this.random.nextFloat() * 0.6f + 0.2f) / (float)target);
            this.warheads.add(w);
        }
    }

    private final void randomizePose(Warhead w) {
        w.setLevel(0.15f + this.random.nextFloat() * 0.7f);
        w.setPitch((float)Math.toRadians((double)this.random.nextFloat() * 10.0));
        w.setOmega(8.5f + this.random.nextFloat() * 4.5f);
        w.setZeta(0.5f + this.random.nextFloat() * 0.25f);
    }

    private final Warhead pickLaunch(Vec3d cameraPos, long now) {
        Warhead best = null;
        double bestDist = Double.MAX_VALUE;
        Iterator<Warhead> iterator = this.warheads.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Warhead> iterator2 = iterator;
        while (iterator2.hasNext()) {
            double dz;
            double dy;
            double dx;
            double d;
            Warhead w = (Warhead) (iterator2.next());
            if (w.getLaunching() || now - w.getSpawnMs() < 380L || !((d = (dx = w.getX() - cameraPos.x) * dx + (dy = w.getY() - cameraPos.y) * dy + (dz = w.getZ() - cameraPos.z) * dz) < bestDist)) continue;
            bestDist = d;
            best = w;
        }
        return best;
    }

    private final void shatter(double ix, double iy, double iz, Warhead w, Params params, long now) {
        int count = Math.max(4, params.getShardCount());
        int dust = count / 2;
        int n = count + dust;
        for (int i = 0; i < n; ++i) {
            boolean small = i >= count;
            float rx = this.random.nextFloat() * 2.0f - 1.0f;
            float ry = this.random.nextFloat() * 2.0f - 1.0f;
            float rz = this.random.nextFloat() * 2.0f - 1.0f;
            float rl = Math.max(0.2f, (float)Math.sqrt(rx * rx + ry * ry + rz * rz));
            float spread = small ? 1.1f : 0.85f;
            float vx = rx / rl * spread - w.getDx() * 0.55f;
            float vy = ry / rl * spread - w.getDy() * 0.55f + 0.35f;
            float vz = rz / rl * spread - w.getDz() * 0.55f;
            float speed = (small ? 3.2f : 2.4f) + this.random.nextFloat() * 2.6f;
            vx *= speed;
            vy *= speed;
            vz *= speed;
            float axX = this.random.nextFloat() * 2.0f - 1.0f;
            float axY = this.random.nextFloat() * 2.0f - 1.0f;
            float axZ = this.random.nextFloat() * 2.0f - 1.0f;
            float al = Math.max(0.2f, (float)Math.sqrt(axX * axX + axY * axY + axZ * axZ));
            float sizeBase = params.getSize() * (small ? 0.05f : 0.09f);
            float size = sizeBase * (0.6f + this.random.nextFloat() * 0.8f);
            float[] verts = new float[12];
            for (int k = 0; k < 4; ++k) {
                verts[k * 3] = (this.random.nextFloat() * 2.0f - 1.0f) * size;
                verts[k * 3 + 1] = (this.random.nextFloat() * 2.0f - 1.0f) * size * 0.45f;
                verts[k * 3 + 2] = (this.random.nextFloat() * 2.0f - 1.0f) * size;
            }
            int life = (small ? 380 : 560) + this.random.nextInt(340);
            this.shards.add(new Shard(ix + (double)w.getDx() * -0.02, iy, iz + (double)w.getDz() * -0.02, vx, vy, vz, axX / al, axY / al, axZ / al, (6.0f + this.random.nextFloat() * 14.0f) * (this.random.nextBoolean() ? 1.0f : -1.0f), size, now, life, (w.getColorIndex() + i * 9) % 360, verts));
        }
        while (this.shards.size() > 160) {
            this.shards.remove(0);
        }
    }

    private final void updateShards(long now, float dt) {
        float drag = (float)Math.exp(-1.8f * dt);
        for (int i = this.shards.size() - 1; -1 < i; --i) {
            Shard s = (Shard) (this.shards.get(i));
            if (now - s.getSpawnMs() > (long)s.getLifeMs()) {
                this.shards.remove(i);
                continue;
            }
            s.setVy(s.getVy() - 9.5f * dt);
            s.setVx(s.getVx() * drag);
            s.setVy(s.getVy() * drag);
            s.setVz(s.getVz() * drag);
            s.setX(s.getX() + (double)(s.getVx() * dt));
            s.setY(s.getY() + (double)(s.getVy() * dt));
            s.setZ(s.getZ() + (double)(s.getVz() * dt));
            s.setAngle(s.getAngle() + s.getRotSpeed() * dt);
        }
    }

    static {
        int n;
        Companion = new Companion(null);
        COS = new float[11];
        SIN = new float[11];
        LIGHT = new Vector3f(0.35f, 0.85f, 0.4f).normalize();
        ArrayList<Float> ts = new ArrayList<Float>();
        ArrayList<Float> rs = new ArrayList<Float>();
        ts.add(Float.valueOf(0.0f));
        rs.add(Float.valueOf(1.0f));
        ts.add(Float.valueOf(0.09f));
        rs.add(Float.valueOf(1.0f));
        float[] fArray = new float[]{0.22f, 0.38f, 0.54f, 0.7f, 0.85f};
        float[] grooves = fArray;
        float w = 0.025f;
        for (float g : grooves) {
            ts.add(Float.valueOf(g - w));
            rs.add(Float.valueOf(1.0f));
            ts.add(Float.valueOf(g));
            rs.add(Float.valueOf(0.76f));
            ts.add(Float.valueOf(g + w));
            rs.add(Float.valueOf(1.0f));
        }
        ts.add(Float.valueOf(1.0f));
        rs.add(Float.valueOf(0.0f));
        int n2 = 0;
        int n3 = ts.size();
        float[] fArray2 = new float[n3];
        while (n2 < n3) {
            n = n2++;
            Object e = ts.get(n);
            Intrinsics.checkNotNullExpressionValue(e, (String)"get(...)");
            fArray2[n] = ((Number)e).floatValue();
        }
        PROFILE_T = fArray2;
        n3 = ts.size();
        fArray2 = new float[n3];
        for (n2 = 0; n2 < n3; ++n2) {
            n = n2;
            Object e = ts.get(n);
            Intrinsics.checkNotNullExpressionValue(e, (String)"get(...)");
            float t = ((Number)e).floatValue();
            float taper = t <= 0.09f ? 1.0f : (float)Math.pow(1.0f - (t - 0.09f) / 0.91f, 0.92);
            Object e2 = rs.get(n);
            Intrinsics.checkNotNullExpressionValue(e2, (String)"get(...)");
            fArray2[n] = taper * ((Number)e2).floatValue();
        }
        PROFILE_R = fArray2;
        for (int j = 0; j < 11; ++j) {
            float phi = (float)Math.PI * 2 * (float)j / (float)10;
            TargetWarheadSystem.COS[j] = (float)Math.cos(phi);
            TargetWarheadSystem.SIN[j] = (float)Math.sin(phi);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0014\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\t\u0010\u0007JO\u0010\u0012\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013Jw\u0010\"\u001a\u00020!2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\"\u0010#J_\u0010*\u001a\u00020!2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b*\u0010+JW\u00101\u001a\u00020!2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020\u001d2\u0006\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b1\u00102J'\u00106\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b6\u00107J\u001f\u0010:\u001a\u00020\u001d2\u0006\u00108\u001a\u00020\u001d2\u0006\u00109\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b:\u0010;R\u0014\u0010<\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0014\u0010>\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u0010=R\u0014\u0010B\u001a\u00020A8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020A8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u0010CR\u0014\u0010E\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bE\u0010=R\u0014\u0010F\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bF\u0010=R\u0014\u0010G\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u0010=R\u0014\u0010H\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bH\u0010=R\u0014\u0010I\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bI\u0010?R\u0014\u0010J\u001a\u00020A8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bJ\u0010CR\u0014\u0010K\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bK\u0010=R\u0014\u0010L\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010MR\u0014\u0010N\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010MR\u0014\u0010O\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bO\u0010MR\u0014\u0010P\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bP\u0010MR\u001c\u0010S\u001a\n R*\u0004\u0018\u00010Q0Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bS\u0010T\u00a8\u0006U"}, d2={"Lrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem.Companion;", "", "<init>", "()V", "", "t", "smoothstep", "(F)F", "easeInBack", "easeOutBack", "px", "py", "pz", "dx", "dy", "dz", "a", "b", "rayEllipsoid", "(FFFFFFFF)F", "Lnet/minecraft/VertexConsumer;", "c", "Lnet/minecraft/MatrixStack$Entry;", "pose", "tipX", "tipY", "tipZ", "length", "radius", "", "color", "u", "v", "", "emitCone", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;FFFFFFFFIFF)V", "x", "y", "z", "Lrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem$Shard;", "s", "scale", "emitShard", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;FFFLrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem$Shard;FIFF)V", "", "p", "i", "j", "k", "face", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;[FIIIIFF)V", "nx", "ny", "nz", "shadeFor", "(FFF)F", "argb", "f", "scaleRgb", "(IF)I", "TWO_PI", "F", "SIDES", "I", "CONE_RADIUS_RATIO", "", "GROW_MS", "J", "RESPAWN_DELAY_MS", "RECOIL_T", "RECOIL_V", "ACCEL_T", "CARRY_T", "MAX_SHARDS", "HIDE_STAGGER_MS", "AIM_FOLLOW", "PROFILE_T", "[F", "PROFILE_R", "COS", "SIN", "Lorg/joml/Vector3f;", "kotlin.jvm.PlatformType", "LIGHT", "Lorg/joml/Vector3f;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float smoothstep(float t) {
            float u = MathHelper.clamp((float)t, (float)0.0f, (float)1.0f);
            return u * u * (3.0f - 2.0f * u);
        }

        private final float easeInBack(float t) {
            float c1 = 1.70158f;
            float c3 = c1 + 1.0f;
            float u = MathHelper.clamp((float)t, (float)0.0f, (float)1.0f);
            return c3 * u * u * u - c1 * u * u;
        }

        private final float easeOutBack(float t) {
            float c1 = 1.70158f;
            float c3 = c1 + 1.0f;
            float u = MathHelper.clamp((float)t, (float)0.0f, (float)1.0f) - 1.0f;
            return 1.0f + c3 * u * u * u + c1 * u * u;
        }

        private final float rayEllipsoid(float px, float py, float pz, float dx, float dy, float dz, float a, float b) {
            float ox = px / a;
            float oy = py / b;
            float oz = pz / a;
            float vx = dx / a;
            float vy = dy / b;
            float vz = dz / a;
            float qa = vx * vx + vy * vy + vz * vz;
            float qb = 2.0f * (ox * vx + oy * vy + oz * vz);
            float qc = ox * ox + oy * oy + oz * oz - 1.0f;
            float disc = qb * qb - 4.0f * qa * qc;
            if (qa <= 1.0E-8f || disc < 0.0f) {
                return -1.0f;
            }
            float t = (-qb - (float)Math.sqrt(disc)) / (2.0f * qa);
            return t >= 0.0f ? t : -1.0f;
        }

        private final void emitCone(VertexConsumer c, MatrixStack.Entry pose, float tipX, float tipY, float tipZ, float dx, float dy, float dz, float length, float radius, int color, float u, float v) {
            float hx = 0.0f;
            float hy = 1.0f;
            float hz = 0.0f;
            if (Math.abs(dy) > 0.9f) {
                hx = 1.0f;
                hy = 0.0f;
            }
            float e1x = dy * hz - dz * hy;
            float e1y = dz * hx - dx * hz;
            float e1z = dx * hy - dy * hx;
            float e1l = Math.max(1.0E-5f, (float)Math.sqrt(e1x * e1x + e1y * e1y + e1z * e1z));
            float e2x = dy * (e1z /= e1l) - dz * (e1y /= e1l);
            float e2y = dz * (e1x /= e1l) - dx * e1z;
            float e2z = dx * e1y - dy * e1x;
            float[] prev = new float[33];
            float[] cur = new float[33];
            float[] prevArr = prev;
            float[] curArr = cur;
            int n = PROFILE_T.length;
            for (int ri = 0; ri < n; ++ri) {
                float t = PROFILE_T[ri];
                float r = radius * PROFILE_R[ri];
                float back = length * (1.0f - t);
                float ccx = tipX - dx * back;
                float ccy = tipY - dy * back;
                float ccz = tipZ - dz * back;
                for (int j = 0; j < 11; ++j) {
                    int o = j * 3;
                    curArr[o] = ccx + r * (COS[j] * e1x + SIN[j] * e2x);
                    curArr[o + 1] = ccy + r * (COS[j] * e1y + SIN[j] * e2y);
                    curArr[o + 2] = ccz + r * (COS[j] * e1z + SIN[j] * e2z);
                }
                if (ri == 0) {
                    float capShade = this.shadeFor(-dx, -dy, -dz);
                    int capColor = this.scaleRgb(color, capShade);
                    for (int j = 0; j < 10; ++j) {
                        int o = j * 3;
                        int n2 = o + 3;
                        c.vertex(pose, ccx, ccy, ccz).texture(u, v).color(capColor);
                        c.vertex(pose, curArr[n2], curArr[n2 + 1], curArr[n2 + 2]).texture(u, v).color(capColor);
                        c.vertex(pose, curArr[o], curArr[o + 1], curArr[o + 2]).texture(u, v).color(capColor);
                        c.vertex(pose, curArr[o], curArr[o + 1], curArr[o + 2]).texture(u, v).color(capColor);
                    }
                } else {
                    float rPrev = radius * PROFILE_R[ri - 1];
                    float slope = (rPrev - r) / Math.max(1.0E-4f, length * (t - PROFILE_T[ri - 1]));
                    for (int j = 0; j < 10; ++j) {
                        float cm = (COS[j] + COS[j + 1]) * 0.5f;
                        float sm = (SIN[j] + SIN[j + 1]) * 0.5f;
                        float nx = cm * e1x + sm * e2x + dx * slope;
                        float ny = cm * e1y + sm * e2y + dy * slope;
                        float nz = cm * e1z + sm * e2z + dz * slope;
                        int faceColor = this.scaleRgb(color, this.shadeFor(nx, ny, nz));
                        int o = j * 3;
                        int n3 = o + 3;
                        c.vertex(pose, prevArr[o], prevArr[o + 1], prevArr[o + 2]).texture(u, v).color(faceColor);
                        c.vertex(pose, prevArr[n3], prevArr[n3 + 1], prevArr[n3 + 2]).texture(u, v).color(faceColor);
                        c.vertex(pose, curArr[n3], curArr[n3 + 1], curArr[n3 + 2]).texture(u, v).color(faceColor);
                        c.vertex(pose, curArr[o], curArr[o + 1], curArr[o + 2]).texture(u, v).color(faceColor);
                    }
                }
                float[] swap = prevArr;
                prevArr = curArr;
                curArr = swap;
            }
        }

        private final void emitShard(VertexConsumer c, MatrixStack.Entry pose, float x, float y, float z, Shard s, float scale, int color, float u, float v) {
            float cosA = (float)Math.cos(s.getAngle());
            float sinA = (float)Math.sin(s.getAngle());
            float oneMinus = 1.0f - cosA;
            float[] p = new float[12];
            for (int k = 0; k < 4; ++k) {
                float lx = s.getVerts()[k * 3] * scale;
                float ly = s.getVerts()[k * 3 + 1] * scale;
                float lz = s.getVerts()[k * 3 + 2] * scale;
                float dot = s.getAx() * lx + s.getAy() * ly + s.getAz() * lz;
                float crX = s.getAy() * lz - s.getAz() * ly;
                float crY = s.getAz() * lx - s.getAx() * lz;
                float crZ = s.getAx() * ly - s.getAy() * lx;
                p[k * 3] = x + lx * cosA + crX * sinA + s.getAx() * dot * oneMinus;
                p[k * 3 + 1] = y + ly * cosA + crY * sinA + s.getAy() * dot * oneMinus;
                p[k * 3 + 2] = z + lz * cosA + crZ * sinA + s.getAz() * dot * oneMinus;
            }
            this.face(c, pose, p, 0, 1, 2, color, u, v);
            this.face(c, pose, p, 0, 3, 1, color, u, v);
            this.face(c, pose, p, 1, 3, 2, color, u, v);
            this.face(c, pose, p, 2, 3, 0, color, u, v);
        }

        private final void face(VertexConsumer c, MatrixStack.Entry pose, float[] p, int i, int j, int k, int color, float u, float v) {
            float ax = p[j * 3] - p[i * 3];
            float ay = p[j * 3 + 1] - p[i * 3 + 1];
            float az = p[j * 3 + 2] - p[i * 3 + 2];
            float bx = p[k * 3] - p[i * 3];
            float by = p[k * 3 + 1] - p[i * 3 + 1];
            float bz = p[k * 3 + 2] - p[i * 3 + 2];
            float nx = ay * bz - az * by;
            float ny = az * bx - ax * bz;
            float nz = ax * by - ay * bx;
            int faceColor = this.scaleRgb(color, this.shadeFor(nx, ny, nz));
            c.vertex(pose, p[i * 3], p[i * 3 + 1], p[i * 3 + 2]).texture(u, v).color(faceColor);
            c.vertex(pose, p[j * 3], p[j * 3 + 1], p[j * 3 + 2]).texture(u, v).color(faceColor);
            c.vertex(pose, p[k * 3], p[k * 3 + 1], p[k * 3 + 2]).texture(u, v).color(faceColor);
            c.vertex(pose, p[k * 3], p[k * 3 + 1], p[k * 3 + 2]).texture(u, v).color(faceColor);
        }

        private final float shadeFor(float nx, float ny, float nz) {
            float l = (float)Math.sqrt(nx * nx + ny * ny + nz * nz);
            if (l < 1.0E-6f) {
                return 0.85f;
            }
            float d = (nx * LIGHT.x + ny * LIGHT.y + nz * LIGHT.z) / l;
            return 0.72f + 0.28f * Math.max(0.0f, d);
        }

        private final int scaleRgb(int argb, float f) {
            int a = argb >>> 24 & 0xFF;
            int r = Math.min(255, Math.round((float)(argb >>> 16 & 0xFF) * f));
            int g = Math.min(255, Math.round((float)(argb >>> 8 & 0xFF) * f));
            int b = Math.min(255, Math.round((float)(argb & 0xFF) * f));
            return a << 24 | r << 16 | g << 8 | b;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\"\u0010\u0015\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\r\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\"\u0010\u0018\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\r\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u0011R\"\u0010\u001b\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001b\u0010\r\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011R\"\u0010\u001f\u001a\u00020\u001e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\"\u0010%\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b%\u0010\r\u001a\u0004\b&\u0010\u000f\"\u0004\b'\u0010\u0011R\"\u0010(\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b(\u0010\r\u001a\u0004\b)\u0010\u000f\"\u0004\b*\u0010\u0011R\"\u0010+\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b+\u0010\u0006\u001a\u0004\b,\u0010\b\"\u0004\b-\u0010\nR\"\u0010.\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b.\u0010\r\u001a\u0004\b/\u0010\u000f\"\u0004\b0\u0010\u0011R\"\u00101\u001a\u00020\u001e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b1\u0010 \u001a\u0004\b2\u0010\"\"\u0004\b3\u0010$\u00a8\u00064"}, d2={"Lrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem$Params;", "", "<init>", "()V", "", "count", "I", "getCount", "()I", "setCount", "(I)V", "", "orbitDistance", "F", "getOrbitDistance", "()F", "setOrbitDistance", "(F)V", "size", "getSize", "setSize", "spinSpeed", "getSpinSpeed", "setSpinSpeed", "alpha", "getAlpha", "setAlpha", "glassAlpha", "getGlassAlpha", "setGlassAlpha", "", "throughWalls", "Z", "getThroughWalls", "()Z", "setThroughWalls", "(Z)V", "paramU", "getParamU", "setParamU", "paramV", "getParamV", "setParamV", "shardCount", "getShardCount", "setShardCount", "flightSpeed", "getFlightSpeed", "setFlightSpeed", "visible", "getVisible", "setVisible", "rtx.kimiko:kimiko"})
    public static final class Params {
        private int count = 6;
        private float orbitDistance = 1.0f;
        private float size = 0.5f;
        private float spinSpeed = 1.4f;
        private float alpha = 1.0f;
        private float glassAlpha = 0.85f;
        private boolean throughWalls;
        private float paramU;
        private float paramV;
        private int shardCount = 18;
        private float flightSpeed = 20.0f;
        private boolean visible = true;

        public final int getCount() {
            return this.count;
        }

        public final void setCount(int n) {
            this.count = n;
        }

        public final float getOrbitDistance() {
            return this.orbitDistance;
        }

        public final void setOrbitDistance(float f) {
            this.orbitDistance = f;
        }

        public final float getSize() {
            return this.size;
        }

        public final void setSize(float f) {
            this.size = f;
        }

        public final float getSpinSpeed() {
            return this.spinSpeed;
        }

        public final void setSpinSpeed(float f) {
            this.spinSpeed = f;
        }

        public final float getAlpha() {
            return this.alpha;
        }

        public final void setAlpha(float f) {
            this.alpha = f;
        }

        public final float getGlassAlpha() {
            return this.glassAlpha;
        }

        public final void setGlassAlpha(float f) {
            this.glassAlpha = f;
        }

        public final boolean getThroughWalls() {
            return this.throughWalls;
        }

        public final void setThroughWalls(boolean bl) {
            this.throughWalls = bl;
        }

        public final float getParamU() {
            return this.paramU;
        }

        public final void setParamU(float f) {
            this.paramU = f;
        }

        public final float getParamV() {
            return this.paramV;
        }

        public final void setParamV(float f) {
            this.paramV = f;
        }

        public final int getShardCount() {
            return this.shardCount;
        }

        public final void setShardCount(int n) {
            this.shardCount = n;
        }

        public final float getFlightSpeed() {
            return this.flightSpeed;
        }

        public final void setFlightSpeed(float f) {
            this.flightSpeed = f;
        }

        public final boolean getVisible() {
            return this.visible;
        }

        public final void setVisible(boolean bl) {
            this.visible = bl;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b(\b\u0002\u0018\u00002\u00020\u0001B\u007f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\u0006\u0010\u000e\u001a\u00020\u0006\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0011\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\"\u0010\u0004\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001cR\"\u0010\u0005\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001cR\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0007\u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\"\u0010\b\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\b\u0010!\u001a\u0004\b&\u0010#\"\u0004\b'\u0010%R\"\u0010\t\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\t\u0010!\u001a\u0004\b(\u0010#\"\u0004\b)\u0010%R\u0017\u0010\n\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\n\u0010!\u001a\u0004\b*\u0010#R\u0017\u0010\u000b\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010!\u001a\u0004\b+\u0010#R\u0017\u0010\f\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\f\u0010!\u001a\u0004\b,\u0010#R\u0017\u0010\r\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\r\u0010!\u001a\u0004\b-\u0010#R\u0017\u0010\u000e\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010!\u001a\u0004\b.\u0010#R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006\u00a2\u0006\f\n\u0004\b\u0010\u0010/\u001a\u0004\b0\u00101R\u0017\u0010\u0012\u001a\u00020\u00118\u0006\u00a2\u0006\f\n\u0004\b\u0012\u00102\u001a\u0004\b3\u00104R\u0017\u0010\u0013\u001a\u00020\u00118\u0006\u00a2\u0006\f\n\u0004\b\u0013\u00102\u001a\u0004\b5\u00104R\u0017\u0010\u0015\u001a\u00020\u00148\u0006\u00a2\u0006\f\n\u0004\b\u0015\u00106\u001a\u0004\b7\u00108R\"\u00109\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b9\u0010!\u001a\u0004\b:\u0010#\"\u0004\b;\u0010%\u00a8\u0006<"}, d2={"Lrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem$Shard;", "", "", "x", "y", "z", "", "vx", "vy", "vz", "ax", "ay", "az", "rotSpeed", "size", "", "spawnMs", "", "lifeMs", "colorIndex", "", "verts", "<init>", "(DDDFFFFFFFFJII[F)V", "D", "getX", "()D", "setX", "(D)V", "getY", "setY", "getZ", "setZ", "F", "getVx", "()F", "setVx", "(F)V", "getVy", "setVy", "getVz", "setVz", "getAx", "getAy", "getAz", "getRotSpeed", "getSize", "J", "getSpawnMs", "()J", "I", "getLifeMs", "()I", "getColorIndex", "[F", "getVerts", "()[F", "angle", "getAngle", "setAngle", "rtx.kimiko:kimiko"})
    private static final class Shard {
        private double x;
        private double y;
        private double z;
        private float vx;
        private float vy;
        private float vz;
        private final float ax;
        private final float ay;
        private final float az;
        private final float rotSpeed;
        private final float size;
        private final long spawnMs;
        private final int lifeMs;
        private final int colorIndex;
        @NotNull
        private final float[] verts;
        private float angle;

        public Shard(double x, double y, double z, float vx, float vy, float vz, float ax, float ay, float az, float rotSpeed, float size, long spawnMs, int lifeMs, int colorIndex, @NotNull float[] verts) {
            Intrinsics.checkNotNullParameter((Object)verts, (String)"verts");
            this.x = x;
            this.y = y;
            this.z = z;
            this.vx = vx;
            this.vy = vy;
            this.vz = vz;
            this.ax = ax;
            this.ay = ay;
            this.az = az;
            this.rotSpeed = rotSpeed;
            this.size = size;
            this.spawnMs = spawnMs;
            this.lifeMs = lifeMs;
            this.colorIndex = colorIndex;
            this.verts = verts;
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

        public final float getAx() {
            return this.ax;
        }

        public final float getAy() {
            return this.ay;
        }

        public final float getAz() {
            return this.az;
        }

        public final float getRotSpeed() {
            return this.rotSpeed;
        }

        public final float getSize() {
            return this.size;
        }

        public final long getSpawnMs() {
            return this.spawnMs;
        }

        public final int getLifeMs() {
            return this.lifeMs;
        }

        public final int getColorIndex() {
            return this.colorIndex;
        }

        @NotNull
        public final float[] getVerts() {
            return this.verts;
        }

        public final float getAngle() {
            return this.angle;
        }

        public final void setAngle(float f) {
            this.angle = f;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b=\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u000b\u001a\u0004\b\f\u0010\rR\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0007\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\b\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\b\u0010\u0013\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\"\u0010\u001a\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\u000b\u001a\u0004\b\u001b\u0010\r\"\u0004\b\u001c\u0010\u001dR\"\u0010\u001e\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001e\u0010\u000b\u001a\u0004\b\u001f\u0010\r\"\u0004\b \u0010\u001dR\"\u0010\"\u001a\u00020!8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\"\u0010(\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b(\u0010\u0013\u001a\u0004\b)\u0010\u0015\"\u0004\b*\u0010\u0017R\"\u0010+\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b+\u0010\u000b\u001a\u0004\b,\u0010\r\"\u0004\b-\u0010\u001dR\"\u0010.\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b.\u0010\u000b\u001a\u0004\b/\u0010\r\"\u0004\b0\u0010\u001dR\"\u00102\u001a\u0002018\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b2\u00103\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\"\u00108\u001a\u0002018\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b8\u00103\u001a\u0004\b9\u00105\"\u0004\b:\u00107R\"\u0010;\u001a\u0002018\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b;\u00103\u001a\u0004\b<\u00105\"\u0004\b=\u00107R\"\u0010>\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b>\u0010\u000b\u001a\u0004\b?\u0010\r\"\u0004\b@\u0010\u001dR\"\u0010A\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bA\u0010\u000b\u001a\u0004\bB\u0010\r\"\u0004\bC\u0010\u001dR\"\u0010D\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bD\u0010\u000b\u001a\u0004\bE\u0010\r\"\u0004\bF\u0010\u001dR\"\u0010G\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bG\u0010\u000b\u001a\u0004\bH\u0010\r\"\u0004\bI\u0010\u001dR\"\u0010J\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bJ\u0010\u000b\u001a\u0004\bK\u0010\r\"\u0004\bL\u0010\u001dR\"\u0010M\u001a\u0002018\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bM\u00103\u001a\u0004\bN\u00105\"\u0004\bO\u00107R\"\u0010P\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bP\u0010\u0013\u001a\u0004\bQ\u0010\u0015\"\u0004\bR\u0010\u0017R\"\u0010S\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bS\u0010\u000b\u001a\u0004\bT\u0010\r\"\u0004\bU\u0010\u001dR\"\u0010V\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bV\u0010\u0013\u001a\u0004\bW\u0010\u0015\"\u0004\bX\u0010\u0017R\"\u0010Y\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bY\u0010\u000b\u001a\u0004\bZ\u0010\r\"\u0004\b[\u0010\u001dR\"\u0010\\\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\\\u0010\u000b\u001a\u0004\b]\u0010\r\"\u0004\b^\u0010\u001dR\"\u0010_\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b_\u0010\u000b\u001a\u0004\b`\u0010\r\"\u0004\ba\u0010\u001dR\"\u0010b\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bb\u0010\u000b\u001a\u0004\bc\u0010\r\"\u0004\bd\u0010\u001dR\"\u0010e\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\be\u0010\u000b\u001a\u0004\bf\u0010\r\"\u0004\bg\u0010\u001dR\"\u0010h\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bh\u0010\u000b\u001a\u0004\bi\u0010\r\"\u0004\bj\u0010\u001dR\"\u0010k\u001a\u00020!8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bk\u0010#\u001a\u0004\bl\u0010%\"\u0004\bm\u0010'\u00a8\u0006n"}, d2={"Lrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem$Warhead;", "", "", "phase", "", "colorIndex", "", "spawnMs", "seed", "<init>", "(FIJJ)V", "F", "getPhase", "()F", "I", "getColorIndex", "()I", "setColorIndex", "(I)V", "J", "getSpawnMs", "()J", "setSpawnMs", "(J)V", "getSeed", "setSeed", "level", "getLevel", "setLevel", "(F)V", "pitch", "getPitch", "setPitch", "", "launching", "Z", "getLaunching", "()Z", "setLaunching", "(Z)V", "launchMs", "getLaunchMs", "setLaunchMs", "carryVx", "getCarryVx", "setCarryVx", "carryVz", "getCarryVz", "setCarryVz", "", "x", "D", "getX", "()D", "setX", "(D)V", "y", "getY", "setY", "z", "getZ", "setZ", "dx", "getDx", "setDx", "dy", "getDy", "setDy", "dz", "getDz", "setDz", "scale", "getScale", "setScale", "stretch", "getStretch", "setStretch", "aimY", "getAimY", "setAimY", "hideMs", "getHideMs", "setHideMs", "hideFrom", "getHideFrom", "setHideFrom", "showMs", "getShowMs", "setShowMs", "showFrom", "getShowFrom", "setShowFrom", "vx", "getVx", "setVx", "vy", "getVy", "setVy", "vz", "getVz", "setVz", "omega", "getOmega", "setOmega", "zeta", "getZeta", "setZeta", "settled", "getSettled", "setSettled", "rtx.kimiko:kimiko"})
    private static final class Warhead {
        private final float phase;
        private int colorIndex;
        private long spawnMs;
        private long seed;
        private float level;
        private float pitch;
        private boolean launching;
        private long launchMs;
        private float carryVx;
        private float carryVz;
        private double x;
        private double y;
        private double z;
        private float dx;
        private float dy;
        private float dz;
        private float scale;
        private float stretch;
        private double aimY;
        private long hideMs;
        private float hideFrom;
        private long showMs;
        private float showFrom;
        private float vx;
        private float vy;
        private float vz;
        private float omega;
        private float zeta;
        private boolean settled;

        public Warhead(float phase, int colorIndex, long spawnMs, long seed) {
            this.phase = phase;
            this.colorIndex = colorIndex;
            this.spawnMs = spawnMs;
            this.seed = seed;
            this.level = 0.5f;
            this.dz = 1.0f;
            this.stretch = 1.0f;
            this.hideFrom = 1.0f;
            this.showFrom = 1.0f;
            this.omega = 11.0f;
            this.zeta = 0.65f;
        }

        public final float getPhase() {
            return this.phase;
        }

        public final int getColorIndex() {
            return this.colorIndex;
        }

        public final void setColorIndex(int n) {
            this.colorIndex = n;
        }

        public final long getSpawnMs() {
            return this.spawnMs;
        }

        public final void setSpawnMs(long l) {
            this.spawnMs = l;
        }

        public final long getSeed() {
            return this.seed;
        }

        public final void setSeed(long l) {
            this.seed = l;
        }

        public final float getLevel() {
            return this.level;
        }

        public final void setLevel(float f) {
            this.level = f;
        }

        public final float getPitch() {
            return this.pitch;
        }

        public final void setPitch(float f) {
            this.pitch = f;
        }

        public final boolean getLaunching() {
            return this.launching;
        }

        public final void setLaunching(boolean bl) {
            this.launching = bl;
        }

        public final long getLaunchMs() {
            return this.launchMs;
        }

        public final void setLaunchMs(long l) {
            this.launchMs = l;
        }

        public final float getCarryVx() {
            return this.carryVx;
        }

        public final void setCarryVx(float f) {
            this.carryVx = f;
        }

        public final float getCarryVz() {
            return this.carryVz;
        }

        public final void setCarryVz(float f) {
            this.carryVz = f;
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

        public final float getDx() {
            return this.dx;
        }

        public final void setDx(float f) {
            this.dx = f;
        }

        public final float getDy() {
            return this.dy;
        }

        public final void setDy(float f) {
            this.dy = f;
        }

        public final float getDz() {
            return this.dz;
        }

        public final void setDz(float f) {
            this.dz = f;
        }

        public final float getScale() {
            return this.scale;
        }

        public final void setScale(float f) {
            this.scale = f;
        }

        public final float getStretch() {
            return this.stretch;
        }

        public final void setStretch(float f) {
            this.stretch = f;
        }

        public final double getAimY() {
            return this.aimY;
        }

        public final void setAimY(double d) {
            this.aimY = d;
        }

        public final long getHideMs() {
            return this.hideMs;
        }

        public final void setHideMs(long l) {
            this.hideMs = l;
        }

        public final float getHideFrom() {
            return this.hideFrom;
        }

        public final void setHideFrom(float f) {
            this.hideFrom = f;
        }

        public final long getShowMs() {
            return this.showMs;
        }

        public final void setShowMs(long l) {
            this.showMs = l;
        }

        public final float getShowFrom() {
            return this.showFrom;
        }

        public final void setShowFrom(float f) {
            this.showFrom = f;
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

        public final float getOmega() {
            return this.omega;
        }

        public final void setOmega(float f) {
            this.omega = f;
        }

        public final float getZeta() {
            return this.zeta;
        }

        public final void setZeta(float f) {
            this.zeta = f;
        }

        public final boolean getSettled() {
            return this.settled;
        }

        public final void setSettled(boolean bl) {
            this.settled = bl;
        }
    }
}

