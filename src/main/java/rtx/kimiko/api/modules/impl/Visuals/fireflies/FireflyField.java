/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos.Mutable
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.Heightmap.Type
 *  net.minecraft.util.Identifier
 *  net.minecraft.registry.tag.BlockTags
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.world.ClientWorld
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 *  org.joml.Vector3fc
 */
package rtx.kimiko.api.modules.impl.Visuals.fireflies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.util.Identifier;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3fc;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.others.WorldVertex;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 J2\u00020\u0001:\u0002KJB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J=\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\n\u00a2\u0006\u0004\b\u0010\u0010\u0011J'\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJU\u0010&\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010%\u001a\u00020\n\u00a2\u0006\u0004\b&\u0010'J\u001f\u0010*\u001a\u00020\n2\u0006\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b*\u0010+J/\u00101\u001a\u00020\u00042\u0006\u0010-\u001a\u00020,2\u0006\u0010/\u001a\u00020.2\u0006\u0010#\u001a\u00020\n2\u0006\u00100\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b1\u00102J\u001f\u00104\u001a\u00020\f2\u0006\u00103\u001a\u00020\f2\u0006\u0010%\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b4\u00105R$\u00109\u001a\u0012\u0012\u0004\u0012\u00020706j\b\u0012\u0004\u0012\u000207`88\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u0010:R\u0014\u0010<\u001a\u00020;8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0014\u0010?\u001a\u00020>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010@R\u0016\u0010A\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u0010BR\u0016\u0010C\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bC\u0010DR\u0016\u0010E\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bE\u0010DR\u0016\u0010F\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u0010GR\u0016\u0010H\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bH\u0010I\u00a8\u0006L"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/fireflies/FireflyField;", "", "<init>", "()V", "", "clear", "Lnet/minecraft/ClientWorld;", "level", "Lnet/minecraft/Vec3d;", "camPos", "", "dt", "", "target", "radius", "speedMul", "update", "(Lnet/minecraft/ClientWorld;Lnet/minecraft/Vec3d;FIFF)V", "", "trySpawn", "(Lnet/minecraft/ClientWorld;Lnet/minecraft/Vec3d;F)Z", "", "x", "y", "z", "spawn", "(DDD)V", "Lnet/minecraft/MatrixStack;", "stack", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "provider", "Lnet/minecraft/Camera;", "camera", "primaryColor", "secondaryColor", "size", "intensity", "alpha", "render", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/Camera;IIFFFF)V", "t", "duty", "blinkCurve", "(FF)F", "Lnet/minecraft/VertexConsumer;", "consumer", "Lnet/minecraft/MatrixStack$Entry;", "pose", "color", "quad", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;FI)V", "rgb", "withAlpha", "(IF)I", "Ljava/util/ArrayList;", "Lrtx/kimiko/api/modules/impl/Visuals/fireflies/FireflyField$Firefly;", "Lkotlin/collections/ArrayList;", "flies", "Ljava/util/ArrayList;", "Ljava/util/Random;", "random", "Ljava/util/Random;", "Lnet/minecraft/BlockPos$Mutable;", "scratch", "Lnet/minecraft/BlockPos$Mutable;", "rescanCooldown", "F", "lastCamChunkX", "I", "lastCamChunkZ", "noTreesNearby", "Z", "clock", "D", "Companion", "Firefly", "rtx.kimiko:kimiko"})
public final class FireflyField {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ArrayList<Firefly> flies = new ArrayList();
    @NotNull
    private final Random random = new Random();
    @NotNull
    private final BlockPos.Mutable scratch = new BlockPos.Mutable();
    private float rescanCooldown;
    private int lastCamChunkX = Integer.MIN_VALUE;
    private int lastCamChunkZ = Integer.MIN_VALUE;
    private boolean noTreesNearby;
    private double clock;
    public static final int MAX_FIREFLIES = 400;
    private static final int SPAWN_ATTEMPTS_PER_FRAME = 12;
    private static final int SPAWN_MAX_PER_FRAME = 4;
    private static final int PLACEMENT_TRIES = 3;
    private static final float RESCAN_SECONDS = 1.5f;
    private static final float SPAWN_MIN_RADIUS = 3.0f;
    private static final float DESPAWN_MARGIN = 6.0f;
    private static final double MAX_HEIGHT_ABOVE_CAMERA = 40.0;
    private static final int MAX_CANOPY_DEPTH = 6;
    private static final double SIDE_JITTER = 1.6;
    private static final double BELOW_CANOPY = 2.5;
    private static final double ABOVE_CANOPY = 0.8;
    private static final float MIN_LIFE = 9.0f;
    private static final float MAX_LIFE = 22.0f;
    private static final float FADE_IN = 1.6f;
    private static final float FADE_OUT = 2.2f;
    private static final float HALO_SCALE = 3.2f;
    @NotNull
    private static final Identifier GLOW_TEXTURE;

    public final void clear() {
        this.flies.clear();
        this.clock = 0.0;
        this.rescanCooldown = 0.0f;
        this.noTreesNearby = false;
        this.lastCamChunkX = Integer.MIN_VALUE;
        this.lastCamChunkZ = Integer.MIN_VALUE;
    }

    public final void update(@NotNull ClientWorld level, @NotNull Vec3d camPos, float dt, int target, float radius, float speedMul) {
        Intrinsics.checkNotNullParameter((Object)level, (String)"level");
        Intrinsics.checkNotNullParameter((Object)camPos, (String)"camPos");
        int wanted = Math.min(target, 400);
        float despawnSq = (radius + 6.0f) * (radius + 6.0f);
        this.clock += (double)(dt * speedMul);
        Iterator<Firefly> iterator = this.flies.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Firefly> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Firefly fly = (Firefly) (iterator2.next());
            fly.setAge(fly.getAge() + dt * speedMul);
            double dx = fly.getHomeX() - camPos.x;
            double dz = fly.getHomeZ() - camPos.z;
            if (fly.getAge() >= fly.getLife() || dx * dx + dz * dz > (double)despawnSq || this.flies.size() > wanted) {
                iterator2.remove();
                continue;
            }
            double t = this.clock;
            double r = fly.getWander();
            fly.setX(fly.getHomeX() + r * (Math.sin(t * 0.61 + (double)fly.getPhaseA()) * 0.7 + Math.sin(t * 1.37 + (double)fly.getPhaseB()) * 0.3));
            fly.setY(fly.getHomeY() + r * 0.45 * (Math.sin(t * 0.83 + (double)fly.getPhaseB()) * 0.6 + Math.sin(t * 1.91 + (double)fly.getPhaseC()) * 0.4));
            fly.setZ(fly.getHomeZ() + r * (Math.cos(t * 0.53 + (double)fly.getPhaseC()) * 0.7 + Math.cos(t * 1.19 + (double)fly.getPhaseA()) * 0.3));
        }
        int camChunkX = MathHelper.floor((double)camPos.x) >> 4;
        int camChunkZ = MathHelper.floor((double)camPos.z) >> 4;
        if (camChunkX != this.lastCamChunkX || camChunkZ != this.lastCamChunkZ) {
            this.lastCamChunkX = camChunkX;
            this.lastCamChunkZ = camChunkZ;
            this.noTreesNearby = false;
            this.rescanCooldown = 0.0f;
        }
        if (this.flies.size() >= wanted) {
            return;
        }
        if (this.noTreesNearby) {
            this.rescanCooldown -= dt;
            if (this.rescanCooldown > 0.0f) {
                return;
            }
            this.noTreesNearby = false;
        }
        int spawned = 0;
        boolean found = false;
        for (int attempts = 12; attempts > 0 && this.flies.size() < wanted && spawned < 4; --attempts) {
            if (!this.trySpawn(level, camPos, radius)) continue;
            ++spawned;
            found = true;
        }
        if (!found && this.flies.isEmpty()) {
            this.noTreesNearby = true;
            this.rescanCooldown = 1.5f;
        }
    }

    private final boolean trySpawn(ClientWorld level, Vec3d camPos, float radius) {
        int bz;
        double angle = this.random.nextDouble() * Math.PI * 2.0;
        double dist = (double)3.0f + Math.sqrt(this.random.nextDouble()) * (double)(radius - 3.0f);
        int bx = MathHelper.floor((double)(camPos.x + Math.cos(angle) * dist));
        if (!level.isChunkLoaded(bx >> 4, (bz = MathHelper.floor((double)(camPos.z + Math.sin(angle) * dist))) >> 4)) {
            return false;
        }
        int top = level.getTopY(Heightmap.Type.MOTION_BLOCKING, bx, bz);
        if (top <= level.getBottomY() || (double)top - camPos.y > 40.0) {
            return false;
        }
        this.scratch.set(bx, top - 1, bz);
        if (!level.getBlockState((BlockPos)this.scratch).isIn(BlockTags.LEAVES)) {
            return false;
        }
        int canopyBottom = top - 1;
        for (int depth = 0; depth < 6; ++depth) {
            this.scratch.set(bx, canopyBottom - 1, bz);
            if (!level.getBlockState((BlockPos)this.scratch).isIn(BlockTags.LEAVES)) break;
            --canopyBottom;
        }
        for (int i = 0; i < 3; ++i) {
            double jitterX = (this.random.nextDouble() - 0.5) * 2.0 * 1.6;
            double jitterZ = (this.random.nextDouble() - 0.5) * 2.0 * 1.6;
            double yLow = (double)canopyBottom - 2.5;
            double yHigh = (double)top + 0.8;
            double fy = yLow + this.random.nextDouble() * (yHigh - yLow);
            double fx = (double)bx + 0.5 + jitterX;
            double fz = (double)bz + 0.5 + jitterZ;
            this.scratch.set(MathHelper.floor((double)fx), MathHelper.floor((double)fy), MathHelper.floor((double)fz));
            if (!level.getBlockState((BlockPos)this.scratch).isAir()) continue;
            this.spawn(fx, fy, fz);
            return true;
        }
        return false;
    }

    private final void spawn(double x, double y, double z) {
        Firefly fly = new Firefly();
        fly.setHomeX(x);
        fly.setHomeY(y);
        fly.setHomeZ(z);
        fly.setX(x);
        fly.setY(y);
        fly.setZ(z);
        fly.setAge(0.0f);
        fly.setLife(9.0f + this.random.nextFloat() * 13.0f);
        fly.setWander(0.6f + this.random.nextFloat() * 0.9f);
        fly.setPhaseA(this.random.nextFloat() * 6.2832f);
        fly.setPhaseB(this.random.nextFloat() * 6.2832f);
        fly.setPhaseC(this.random.nextFloat() * 6.2832f);
        fly.setBlinkPhase(this.random.nextFloat() * 6.2832f);
        fly.setBlinkSpeed(0.35f + this.random.nextFloat() * 0.75f);
        fly.setBlinkDuty(0.35f + this.random.nextFloat() * 0.35f);
        fly.setScale(0.75f + this.random.nextFloat() * 0.5f);
        fly.setTint(this.random.nextFloat());
        this.flies.add(fly);
    }

    public final void render(@NotNull MatrixStack stack, @NotNull VertexConsumerProvider.Immediate provider, @NotNull Camera camera, int primaryColor, int secondaryColor, float size, float intensity, float radius, float alpha) {
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)provider, (String)"provider");
        Intrinsics.checkNotNullParameter((Object)camera, (String)"camera");
        if (this.flies.isEmpty() || alpha <= 0.004f) {
            return;
        }
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d camPos = vec3d2;
        Quaternionf quaternionf = camera.getRotation();
        Intrinsics.checkNotNullExpressionValue((Object)quaternionf, (String)"rotation(...)");
        Quaternionf rotation = quaternionf;
        Vector3fc vector3fc = camera.getHorizontalPlane();
        Intrinsics.checkNotNullExpressionValue((Object)vector3fc, (String)"forwardVector(...)");
        Vector3fc forward = vector3fc;
        RenderLayer renderLayer2 = ClientPipelines.WORLD_PARTICLES_GLOW.apply(GLOW_TEXTURE);
        Intrinsics.checkNotNullExpressionValue((Object)renderLayer2, (String)"apply(...)");
        RenderLayer renderType = renderLayer2;
        VertexConsumer vertexConsumer2 = provider.getBuffer(renderType);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer consumer = vertexConsumer2;
        float fadeStart = radius * 0.7f;
        float fadeRange = Math.max(1.0f, radius + 6.0f - fadeStart);
        for (Firefly fly : this.flies) {
            float blink;
            float dist;
            float distFade;
            float glow;
            float lifeFade = Math.min(1.0f, Math.min(fly.getAge() / 1.6f, (fly.getLife() - fly.getAge()) / 2.2f));
            if (lifeFade <= 0.0f) continue;
            double rx = fly.getX() - camPos.x;
            double ry = fly.getY() - camPos.y;
            double rz = fly.getZ() - camPos.z;
            if (rx * (double)forward.x() + ry * (double)forward.y() + rz * (double)forward.z() < -1.0 || (glow = lifeFade * (distFade = 1.0f - MathHelper.clamp((float)(((dist = (float)Math.sqrt(rx * rx + ry * ry + rz * rz)) - fadeStart) / fadeRange), (float)0.0f, (float)1.0f)) * (blink = this.blinkCurve((float)(this.clock * (double)fly.getBlinkSpeed() + (double)fly.getBlinkPhase()), fly.getBlinkDuty())) * alpha) <= 0.01f) continue;
            int base = primaryColor == secondaryColor ? primaryColor : ColorEngine.lerpColor(primaryColor, secondaryColor, fly.getTint());
            int core = ColorEngine.lerpColor(base, -1, 0.55f);
            float s = size * fly.getScale();
            float haloAlpha = MathHelper.clamp((float)(glow * intensity * 0.32f), (float)0.0f, (float)1.0f);
            float coreAlpha = MathHelper.clamp((float)(glow * Math.min(1.0f, intensity * 0.5f + 0.5f)), (float)0.0f, (float)1.0f);
            stack.push();
            stack.translate(rx, ry, rz);
            stack.multiply((Quaternionfc)rotation);
            MatrixStack.Entry pose = stack.peek();
            this.quad(consumer, pose, s * 3.2f, this.withAlpha(base, haloAlpha));
            this.quad(consumer, pose, s, this.withAlpha(core, coreAlpha));
            stack.pop();
        }
        provider.draw(renderType);
    }

    private final float blinkCurve(float t, float duty) {
        float phase = t - (float)MathHelper.floor((float)t);
        if (phase >= duty) {
            float tail = (phase - duty) / (1.0f - duty);
            return 0.08f + 0.12f * (1.0f - tail) * (1.0f - tail);
        }
        float u = phase / duty;
        float bell = (float)Math.sin((double)u * Math.PI);
        return 0.2f + 0.8f * bell * bell;
    }

    private final void quad(VertexConsumer consumer, MatrixStack.Entry pose, float size, int color) {
        float half = size * 0.5f;
        WorldVertex.textured(consumer, pose, -half, -half, 0.0f, 0.0f, 0.0f, color);
        WorldVertex.textured(consumer, pose, half, -half, 0.0f, 1.0f, 0.0f, color);
        WorldVertex.textured(consumer, pose, half, half, 0.0f, 1.0f, 1.0f, color);
        WorldVertex.textured(consumer, pose, -half, half, 0.0f, 0.0f, 1.0f, color);
    }

    private final int withAlpha(int rgb, float alpha) {
        return RangesKt.coerceIn((int)Math.round(alpha * 255.0f), (int)0, (int)255) << 24 | rgb & 0xFFFFFF;
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/particle/glow.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        GLOW_TEXTURE = identifier2;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\fR\u0014\u0010\u000e\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\fR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0011R\u0014\u0010\u0014\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0011R\u0014\u0010\u0015\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0011R\u0014\u0010\u0016\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\fR\u0014\u0010\u0017\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\fR\u0014\u0010\u0018\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\fR\u0014\u0010\u0019\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\fR\u0014\u0010\u001a\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\fR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001d\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/fireflies/FireflyField.Companion;", "", "<init>", "()V", "", "MAX_FIREFLIES", "I", "SPAWN_ATTEMPTS_PER_FRAME", "SPAWN_MAX_PER_FRAME", "PLACEMENT_TRIES", "", "RESCAN_SECONDS", "F", "SPAWN_MIN_RADIUS", "DESPAWN_MARGIN", "", "MAX_HEIGHT_ABOVE_CAMERA", "D", "MAX_CANOPY_DEPTH", "SIDE_JITTER", "BELOW_CANOPY", "ABOVE_CANOPY", "MIN_LIFE", "MAX_LIFE", "FADE_IN", "FADE_OUT", "HALO_SCALE", "Lnet/minecraft/Identifier;", "GLOW_TEXTURE", "Lnet/minecraft/Identifier;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0015\n\u0002\u0010\u0007\n\u0002\b%\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\"\u0010\u000e\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u0006\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\"\u0010\u0011\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u0006\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR\"\u0010\u0014\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u0006\u001a\u0004\b\u0015\u0010\b\"\u0004\b\u0016\u0010\nR\"\u0010\u0017\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0017\u0010\u0006\u001a\u0004\b\u0018\u0010\b\"\u0004\b\u0019\u0010\nR\"\u0010\u001b\u001a\u00020\u001a8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010!\u001a\u00020\u001a8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b!\u0010\u001c\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R\"\u0010$\u001a\u00020\u001a8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b$\u0010\u001c\u001a\u0004\b%\u0010\u001e\"\u0004\b&\u0010 R\"\u0010'\u001a\u00020\u001a8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b'\u0010\u001c\u001a\u0004\b(\u0010\u001e\"\u0004\b)\u0010 R\"\u0010*\u001a\u00020\u001a8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b*\u0010\u001c\u001a\u0004\b+\u0010\u001e\"\u0004\b,\u0010 R\"\u0010-\u001a\u00020\u001a8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b-\u0010\u001c\u001a\u0004\b.\u0010\u001e\"\u0004\b/\u0010 R\"\u00100\u001a\u00020\u001a8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b0\u0010\u001c\u001a\u0004\b1\u0010\u001e\"\u0004\b2\u0010 R\"\u00103\u001a\u00020\u001a8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b3\u0010\u001c\u001a\u0004\b4\u0010\u001e\"\u0004\b5\u0010 R\"\u00106\u001a\u00020\u001a8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b6\u0010\u001c\u001a\u0004\b7\u0010\u001e\"\u0004\b8\u0010 R\"\u00109\u001a\u00020\u001a8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b9\u0010\u001c\u001a\u0004\b:\u0010\u001e\"\u0004\b;\u0010 R\"\u0010<\u001a\u00020\u001a8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b<\u0010\u001c\u001a\u0004\b=\u0010\u001e\"\u0004\b>\u0010 \u00a8\u0006?"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/fireflies/FireflyField$Firefly;", "", "<init>", "()V", "", "homeX", "D", "getHomeX", "()D", "setHomeX", "(D)V", "homeY", "getHomeY", "setHomeY", "homeZ", "getHomeZ", "setHomeZ", "x", "getX", "setX", "y", "getY", "setY", "z", "getZ", "setZ", "", "age", "F", "getAge", "()F", "setAge", "(F)V", "life", "getLife", "setLife", "wander", "getWander", "setWander", "phaseA", "getPhaseA", "setPhaseA", "phaseB", "getPhaseB", "setPhaseB", "phaseC", "getPhaseC", "setPhaseC", "blinkPhase", "getBlinkPhase", "setBlinkPhase", "blinkSpeed", "getBlinkSpeed", "setBlinkSpeed", "blinkDuty", "getBlinkDuty", "setBlinkDuty", "scale", "getScale", "setScale", "tint", "getTint", "setTint", "rtx.kimiko:kimiko"})
    private static final class Firefly {
        private double homeX;
        private double homeY;
        private double homeZ;
        private double x;
        private double y;
        private double z;
        private float age;
        private float life = 12.0f;
        private float wander = 1.0f;
        private float phaseA;
        private float phaseB;
        private float phaseC;
        private float blinkPhase;
        private float blinkSpeed = 1.0f;
        private float blinkDuty = 0.5f;
        private float scale = 1.0f;
        private float tint;

        public final double getHomeX() {
            return this.homeX;
        }

        public final void setHomeX(double d) {
            this.homeX = d;
        }

        public final double getHomeY() {
            return this.homeY;
        }

        public final void setHomeY(double d) {
            this.homeY = d;
        }

        public final double getHomeZ() {
            return this.homeZ;
        }

        public final void setHomeZ(double d) {
            this.homeZ = d;
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

        public final float getWander() {
            return this.wander;
        }

        public final void setWander(float f) {
            this.wander = f;
        }

        public final float getPhaseA() {
            return this.phaseA;
        }

        public final void setPhaseA(float f) {
            this.phaseA = f;
        }

        public final float getPhaseB() {
            return this.phaseB;
        }

        public final void setPhaseB(float f) {
            this.phaseB = f;
        }

        public final float getPhaseC() {
            return this.phaseC;
        }

        public final void setPhaseC(float f) {
            this.phaseC = f;
        }

        public final float getBlinkPhase() {
            return this.blinkPhase;
        }

        public final void setBlinkPhase(float f) {
            this.blinkPhase = f;
        }

        public final float getBlinkSpeed() {
            return this.blinkSpeed;
        }

        public final void setBlinkSpeed(float f) {
            this.blinkSpeed = f;
        }

        public final float getBlinkDuty() {
            return this.blinkDuty;
        }

        public final void setBlinkDuty(float f) {
            this.blinkDuty = f;
        }

        public final float getScale() {
            return this.scale;
        }

        public final void setScale(float f) {
            this.scale = f;
        }

        public final float getTint() {
            return this.tint;
        }

        public final void setTint(float f) {
            this.tint = f;
        }
    }
}

