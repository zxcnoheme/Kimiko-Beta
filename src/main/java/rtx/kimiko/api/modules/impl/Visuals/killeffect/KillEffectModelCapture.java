/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2ObjectSortedMaps
 *  it.unimi.dsi.fastutil.objects.ObjectCollection
 *  it.unimi.dsi.fastutil.objects.ObjectIterator
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.entity.state.EntityRenderState
 *  net.minecraft.client.render.state.WorldRenderState
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.client.render.command.OrderedRenderCommandQueueImpl
 *  net.minecraft.client.render.command.ModelCommandRenderer
 *  net.minecraft.client.render.command.ItemCommandRenderer
 *  net.minecraft.client.render.command.BatchingRenderCommandQueue
 *  net.minecraft.client.render.command.ModelPartCommandRenderer
 *  net.minecraft.client.render.state.CameraRenderState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.render.OutlineVertexConsumerProvider
 *  net.minecraft.client.render.WorldRenderer
 *  net.minecraft.client.render.entity.EntityRenderManager
 *  net.minecraft.client.util.BufferAllocator
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.killeffect;

import it.unimi.dsi.fastutil.objects.Object2ObjectSortedMaps;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.SequencedMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.state.WorldRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.command.OrderedRenderCommandQueueImpl;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.ItemCommandRenderer;
import net.minecraft.client.render.command.BatchingRenderCommandQueue;
import net.minecraft.client.render.command.ModelPartCommandRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.OutlineVertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.entity.EntityRenderManager;
import net.minecraft.client.util.BufferAllocator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0013\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0004KLMNB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\n\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ'\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ'\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJU\u0010\u001c\u001a\u00020\u001b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u00112\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ/\u0010!\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b!\u0010\"J\u001f\u0010#\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b#\u0010$J#\u0010%\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007b\u0002\b\t\u00a2\u0006\u0004\b%\u0010&J'\u0010*\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b*\u0010+J/\u0010.\u001a\u00020\f2\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\f2\u0006\u0010-\u001a\u00020,H\u0002\u00a2\u0006\u0004\b.\u0010/J\u0017\u00102\u001a\u0002012\u0006\u00100\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b2\u00103R\u0014\u00105\u001a\u0002048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u00106R\u0014\u00107\u001a\u0002048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u00106R\u0014\u00109\u001a\u0002088\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u0010:R\u0014\u0010<\u001a\u00020;8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0014\u0010?\u001a\u00020>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010E\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010FR\u001a\u0010I\u001a\b\u0012\u0004\u0012\u00020H0G8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010J\u00a8\u0006O"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture;", "", "<init>", "()V", "Lnet/minecraft/LivingEntity;", "entity", "", "partialTick", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$CapturedModel;", "Lkotlin/jvm/JvmStatic;", "capture", "(Lnet/minecraft/LivingEntity;F)Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$CapturedModel;", "Lnet/minecraft/Vec3d;", "origin", "captureRenderedModel", "(Lnet/minecraft/LivingEntity;FLnet/minecraft/Vec3d;)Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$CapturedModel;", "captureHumanoidFallback", "", "vertices", "yaw", "", "minX", "minY", "minZ", "maxX", "maxY", "maxZ", "", "appendCuboid", "(Ljava/util/List;FDDDDDD)V", "x", "y", "z", "rotate", "(FDDD)Lnet/minecraft/Vec3d;", "captureBoundingBoxFallback", "(Lnet/minecraft/LivingEntity;Lnet/minecraft/Vec3d;)Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$CapturedModel;", "interpolatedPosition", "(Lnet/minecraft/LivingEntity;F)Lnet/minecraft/Vec3d;", "a", "b", "c", "triangleArea", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;)D", "Ljava/util/Random;", "random", "sampleTriangle", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Ljava/util/Random;)Lnet/minecraft/Vec3d;", "value", "", "isFinite", "(F)Z", "", "MAX_CAPTURED_VERTICES", "I", "MIN_CAPTURED_VERTICES", "Lnet/minecraft/OrderedRenderCommandQueueImpl;", "STORAGE", "Lnet/minecraft/OrderedRenderCommandQueueImpl;", "Lnet/minecraft/OutlineVertexConsumerProvider;", "OUTLINE_SOURCE", "Lnet/minecraft/OutlineVertexConsumerProvider;", "Lnet/minecraft/ModelCommandRenderer;", "MODEL_RENDERER", "Lnet/minecraft/ModelCommandRenderer;", "Lnet/minecraft/ModelPartCommandRenderer;", "MODEL_PART_RENDERER", "Lnet/minecraft/ModelPartCommandRenderer;", "Lnet/minecraft/ItemCommandRenderer;", "ITEM_RENDERER", "Lnet/minecraft/ItemCommandRenderer;", "", "", "HUMANOID_CUBOIDS", "[[D", "CapturedModel", "SurfaceQuad", "CaptureSource", "CapturingVertexConsumer", "rtx.kimiko:kimiko"})
public final class KillEffectModelCapture {
    @NotNull
    public static final KillEffectModelCapture INSTANCE = new KillEffectModelCapture();
    private static final int MAX_CAPTURED_VERTICES = 24000;
    private static final int MIN_CAPTURED_VERTICES = 8;
    @NotNull
    private static final OrderedRenderCommandQueueImpl STORAGE = new OrderedRenderCommandQueueImpl();
    @NotNull
    private static final OutlineVertexConsumerProvider OUTLINE_SOURCE = new OutlineVertexConsumerProvider();
    @NotNull
    private static final ModelCommandRenderer MODEL_RENDERER = new ModelCommandRenderer();
    @NotNull
    private static final ModelPartCommandRenderer MODEL_PART_RENDERER = new ModelPartCommandRenderer();
    @NotNull
    private static final ItemCommandRenderer ITEM_RENDERER = new ItemCommandRenderer();
    @NotNull
    private static final double[][] HUMANOID_CUBOIDS;

    private KillEffectModelCapture() {
    }

    @JvmStatic
    @NotNull
    public static final CapturedModel capture(@Nullable LivingEntity entity, float partialTick) {
        CapturedModel humanoid;
        if (entity == null) {
            return CapturedModel.Companion.empty();
        }
        Vec3d origin = KillEffectModelCapture.interpolatedPosition(entity, partialTick);
        CapturedModel captured = INSTANCE.captureRenderedModel(entity, partialTick, origin);
        if (!captured.isEmpty()) {
            return captured;
        }
        if (entity instanceof PlayerEntity && !(humanoid = INSTANCE.captureHumanoidFallback(entity, partialTick, origin)).isEmpty()) {
            return humanoid;
        }
        return INSTANCE.captureBoundingBoxFallback(entity, origin);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final CapturedModel captureRenderedModel(LivingEntity entity, float partialTick, Vec3d origin) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        EntityRenderManager entityRenderManager2 = minecraft.getEntityRenderDispatcher();
        if (entityRenderManager2 == null) {
            return CapturedModel.Companion.empty();
        }
        EntityRenderManager dispatcher = entityRenderManager2;
        WorldRenderer worldRenderer2 = minecraft.worldRenderer;
        if (worldRenderer2 == null) {
            return CapturedModel.Companion.empty();
        }
        WorldRenderer levelRenderer = worldRenderer2;
        WorldRenderState worldRenderState2 = levelRenderer.worldRenderState;
        if (worldRenderState2 == null) {
            return CapturedModel.Companion.empty();
        }
        WorldRenderState levelRenderState = worldRenderState2;
        CameraRenderState cameraRenderState2 = levelRenderState.cameraRenderState;
        if (cameraRenderState2 == null) {
            return CapturedModel.Companion.empty();
        }
        CameraRenderState cameraState = cameraRenderState2;
        CaptureSource source = new CaptureSource(24000);
        STORAGE.clear();
        try {
            EntityRenderState state = dispatcher.getAndUpdateRenderState((Entity)entity, partialTick);
            if (state == null) {
                return CapturedModel.Companion.empty();
            }
            state.outlineColor = 0;
            state.shadowPieces.clear();
            dispatcher.render(state, cameraState, 0.0, 0.0, 0.0, new MatrixStack(), (OrderedRenderCommandQueue)STORAGE);
            ObjectIterator objectIterator = ((ObjectCollection)STORAGE.getBatchingQueues().values()).iterator();
            Intrinsics.checkNotNullExpressionValue((Object)objectIterator, (String)"iterator(...)");
            ObjectIterator objectIterator2 = objectIterator;
            while (objectIterator2.hasNext()) {
                BatchingRenderCommandQueue collection = (BatchingRenderCommandQueue)objectIterator2.next();
                try {
                    MODEL_RENDERER.render(collection, (VertexConsumerProvider.Immediate)source, OUTLINE_SOURCE, (VertexConsumerProvider.Immediate)source);
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
                try {
                    MODEL_PART_RENDERER.render(collection, (VertexConsumerProvider.Immediate)source, OUTLINE_SOURCE, (VertexConsumerProvider.Immediate)source);
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
                try {
                    ITEM_RENDERER.render(collection, (VertexConsumerProvider.Immediate)source, OUTLINE_SOURCE);
                }
                catch (Throwable throwable) {}
            }
        }
        catch (Throwable throwable) {
        }
        finally {
            STORAGE.clear();
        }
        return CapturedModel.Companion.fromLocalVertices(source.vertices(), origin);
    }

    private final CapturedModel captureHumanoidFallback(LivingEntity entity, float partialTick, Vec3d origin) {
        float yaw = MathHelper.lerpAngleDegrees((float)partialTick, (float)entity.lastBodyYaw, (float)entity.bodyYaw);
        ArrayList vertices = new ArrayList();
        double[][] dArray = HUMANOID_CUBOIDS;
        int n = ((Object[])dArray).length;
        for (int i = 0; i < n; ++i) {
            double[] cuboid = dArray[i];
            this.appendCuboid(vertices, yaw, cuboid[0], cuboid[1], cuboid[2], cuboid[3], cuboid[4], cuboid[5]);
        }
        return CapturedModel.Companion.fromLocalVertices(vertices, origin);
    }

    private final void appendCuboid(List<Vec3d> vertices, float yaw, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        Vec3d nnn = this.rotate(yaw, minX, minY, minZ);
        Vec3d pnn = this.rotate(yaw, maxX, minY, minZ);
        Vec3d ppn = this.rotate(yaw, maxX, maxY, minZ);
        Vec3d npn = this.rotate(yaw, minX, maxY, minZ);
        Vec3d nnp = this.rotate(yaw, minX, minY, maxZ);
        Vec3d pnp = this.rotate(yaw, maxX, minY, maxZ);
        Vec3d ppp = this.rotate(yaw, maxX, maxY, maxZ);
        Vec3d npp = this.rotate(yaw, minX, maxY, maxZ);
        vertices.add(nnn);
        vertices.add(pnn);
        vertices.add(ppn);
        vertices.add(npn);
        vertices.add(pnp);
        vertices.add(nnp);
        vertices.add(npp);
        vertices.add(ppp);
        vertices.add(nnp);
        vertices.add(nnn);
        vertices.add(npn);
        vertices.add(npp);
        vertices.add(pnn);
        vertices.add(pnp);
        vertices.add(ppp);
        vertices.add(ppn);
        vertices.add(npn);
        vertices.add(ppn);
        vertices.add(ppp);
        vertices.add(npp);
        vertices.add(nnp);
        vertices.add(pnp);
        vertices.add(pnn);
        vertices.add(nnn);
    }

    private final Vec3d rotate(float yaw, double x, double y, double z) {
        double radians = Math.toRadians(-yaw);
        double sin = Math.sin(radians);
        double cos = Math.cos(radians);
        return new Vec3d(x * cos - z * sin, y, x * sin + z * cos);
    }

    private final CapturedModel captureBoundingBoxFallback(LivingEntity entity, Vec3d origin) {
        double halfWidth = Math.max(0.05, (double)entity.getWidth() * 0.5);
        double height = Math.max(0.1, (double)entity.getHeight());
        double minX = -halfWidth;
        double maxX = halfWidth;
        double minZ = -halfWidth;
        double maxZ = halfWidth;
        ArrayList vertices = new ArrayList();
        this.appendCuboid(vertices, 0.0f, minX, 0.0, minZ, maxX, height, maxZ);
        return CapturedModel.Companion.fromLocalVertices(vertices, origin);
    }

    @JvmStatic
    @NotNull
    public static final Vec3d interpolatedPosition(@NotNull LivingEntity entity, float partialTick) {
        Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
        return new Vec3d(MathHelper.lerp((double)partialTick, (double)entity.lastRenderX, (double)entity.getX()), MathHelper.lerp((double)partialTick, (double)entity.lastRenderY, (double)entity.getY()), MathHelper.lerp((double)partialTick, (double)entity.lastRenderZ, (double)entity.getZ()));
    }

    private final double triangleArea(Vec3d a, Vec3d b, Vec3d c) {
        Vec3d vec3d2 = b.subtract(a);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"subtract(...)");
        Vec3d ab = vec3d2;
        Vec3d vec3d3 = c.subtract(a);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"subtract(...)");
        Vec3d ac = vec3d3;
        double crossX = ab.y * ac.z - ab.z * ac.y;
        double crossY = ab.z * ac.x - ab.x * ac.z;
        double crossZ = ab.x * ac.y - ab.y * ac.x;
        return Math.sqrt(crossX * crossX + crossY * crossY + crossZ * crossZ) * 0.5;
    }

    private final Vec3d sampleTriangle(Vec3d a, Vec3d b, Vec3d c, Random random) {
        double v;
        double u = random.nextDouble();
        if (u + (v = random.nextDouble()) > 1.0) {
            u = 1.0 - u;
            v = 1.0 - v;
        }
        Vec3d vec3d2 = a.add(b.subtract(a).multiply(u)).add(c.subtract(a).multiply(v));
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
        return vec3d2;
    }

    private final boolean isFinite(float value) {
        return Float.isFinite(value) && Math.abs(value) < 512.0f;
    }

    static {
        double[][] dArrayArray = new double[6][];
        double[] dArray = new double[]{-0.25, 1.22, -0.25, 0.25, 1.72, 0.25};
        dArrayArray[0] = dArray;
        dArray = new double[]{-0.25, 0.72, -0.125, 0.25, 1.22, 0.125};
        dArrayArray[1] = dArray;
        dArray = new double[]{-0.43, 0.72, -0.105, -0.25, 1.22, 0.105};
        dArrayArray[2] = dArray;
        dArray = new double[]{0.25, 0.72, -0.105, 0.43, 1.22, 0.105};
        dArrayArray[3] = dArray;
        dArray = new double[]{-0.24, 0.0, -0.105, -0.02, 0.72, 0.105};
        dArrayArray[4] = dArray;
        dArray = new double[]{0.02, 0.0, -0.105, 0.24, 0.72, 0.105};
        dArrayArray[5] = dArray;
        HUMANOID_CUBOIDS = dArrayArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u0013\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$CaptureSource;", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "", "maxVertices", "<init>", "(I)V", "Lnet/minecraft/RenderLayer;", "renderType", "Lnet/minecraft/VertexConsumer;", "getBuffer", "(Lnet/minecraft/RenderLayer;)Lnet/minecraft/VertexConsumer;", "", "Lnet/minecraft/Vec3d;", "vertices", "()Ljava/util/List;", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$CapturingVertexConsumer;", "consumer", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$CapturingVertexConsumer;", "Companion", "rtx.kimiko:kimiko"})
    private static final class CaptureSource
    extends VertexConsumerProvider.Immediate {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @NotNull
        private final CapturingVertexConsumer consumer;
        @NotNull
        private static final BufferAllocator SCRATCH = new BufferAllocator(256);

        public CaptureSource(int maxVertices) {
            super(SCRATCH, (SequencedMap)Object2ObjectSortedMaps.emptyMap());
            this.consumer = new CapturingVertexConsumer(maxVertices);
        }

        @NotNull
        public VertexConsumer getBuffer(@NotNull RenderLayer renderType) {
            Intrinsics.checkNotNullParameter((Object)renderType, (String)"renderType");
            return this.consumer;
        }

        @NotNull
        public final List<Vec3d> vertices() {
            return this.consumer.vertices();
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$CaptureSource.Companion;", "", "<init>", "()V", "Lnet/minecraft/BufferAllocator;", "SCRATCH", "Lnet/minecraft/BufferAllocator;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB5\b\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0010R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0013R%\u0010\t\u001a\u00020\u00038\u0007z\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010\u0016\u001a\u0004\b\t\u0010\u0017R\u0011\u0010\u0019\u001a\u00020\u00188F\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$CapturedModel;", "", "", "Lnet/minecraft/Vec3d;", "vertices", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$SurfaceQuad;", "surfaces", "", "totalArea", "center", "<init>", "(Ljava/util/List;Ljava/util/List;DLnet/minecraft/Vec3d;)V", "Ljava/util/Random;", "random", "sample", "(Ljava/util/Random;)Lnet/minecraft/Vec3d;", "Ljava/util/List;", "getVertices", "()Ljava/util/List;", "D", "Lkotlin/jvm/JvmName;", "name", "Lnet/minecraft/Vec3d;", "()Lnet/minecraft/Vec3d;", "", "isEmpty", "()Z", "Companion", "rtx.kimiko:kimiko"})
    public static final class CapturedModel {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @NotNull
        private final List<Vec3d> vertices;
        @NotNull
        private final List<SurfaceQuad> surfaces;
        private final double totalArea;
        @NotNull
        private final Vec3d center;
        @NotNull
        private static final CapturedModel EMPTY;

        private CapturedModel(List<Vec3d> vertices, List<SurfaceQuad> surfaces, double totalArea, Vec3d center) {
            this.vertices = vertices;
            this.surfaces = surfaces;
            this.totalArea = totalArea;
            this.center = center;
        }

        @NotNull
        public final List<Vec3d> getVertices() {
            return this.vertices;
        }

        @JvmName(name="center")
        @NotNull
        public final Vec3d center() {
            return this.center;
        }

        public final boolean isEmpty() {
            return this.vertices.isEmpty();
        }

        @NotNull
        public final Vec3d sample(@NotNull Random random) {
            Intrinsics.checkNotNullParameter((Object)random, (String)"random");
            if (!this.surfaces.isEmpty() && this.totalArea > 1.0E-7) {
                double cursor = random.nextDouble() * this.totalArea;
                for (SurfaceQuad surface : this.surfaces) {
                    if (!((cursor -= surface.getArea()) <= 0.0)) continue;
                    return surface.sample(random);
                }
                return this.surfaces.get(this.surfaces.size() - 1).sample(random);
            }
            Vec3d vertex = this.vertices.get(random.nextInt(this.vertices.size()));
            Vec3d vec3d2 = vertex.add((random.nextDouble() - 0.5) * 0.025, (random.nextDouble() - 0.5) * 0.025, (random.nextDouble() - 0.5) * 0.025);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
            return vec3d2;
        }

        @JvmStatic
        @NotNull
        public static final CapturedModel empty() {
            return Companion.empty();
        }

        @JvmStatic
        @NotNull
        public static final CapturedModel fromLocalVertices(@Nullable List<? extends Vec3d> localVertices, @NotNull Vec3d origin) {
            return Companion.fromLocalVertices(localVertices, origin);
        }

        public /* synthetic */ CapturedModel(List vertices, List surfaces, double totalArea, Vec3d center, DefaultConstructorMarker $constructor_marker) {
            this(vertices, surfaces, totalArea, center);
        }

        static {
            List list = CollectionsKt.emptyList();
            List list2 = CollectionsKt.emptyList();
            Vec3d vec3d2 = Vec3d.ZERO;
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
            EMPTY = new CapturedModel(list, list2, 0.0, vec3d2);
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J+\u0010\f\u001a\u00020\u00042\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$CapturedModel.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$CapturedModel;", "Lkotlin/jvm/JvmStatic;", "empty", "()Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$CapturedModel;", "", "Lnet/minecraft/Vec3d;", "localVertices", "origin", "fromLocalVertices", "(Ljava/util/List;Lnet/minecraft/Vec3d;)Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$CapturedModel;", "EMPTY", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$CapturedModel;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            @JvmStatic
            @NotNull
            public final CapturedModel empty() {
                return EMPTY;
            }

            /*
             * WARNING - void declaration
             */
            @JvmStatic
            @NotNull
            public final CapturedModel fromLocalVertices(@Nullable List<? extends Vec3d> localVertices, @NotNull Vec3d origin) {
                Intrinsics.checkNotNullParameter((Object)origin, (String)"origin");
                if (localVertices == null || localVertices.size() < 8) {
                    return this.empty();
                }
                ArrayList<Vec3d> vertices = new ArrayList<Vec3d>(localVertices.size());
                for (Vec3d vec3d2 : localVertices) {
                    vertices.add(origin.add(vec3d2));
                }
                Vec3d min = vertices.get(0);
                Vec3d max = vertices.get(0);
                for (Vec3d vertex : vertices) {
                    min = new Vec3d(Math.min(min.x, vertex.x), Math.min(min.y, vertex.y), Math.min(min.z, vertex.z));
                    max = new Vec3d(Math.max(max.x, vertex.x), Math.max(max.y, vertex.y), Math.max(max.z, vertex.z));
                }
                Vec3d center = new Vec3d((min.x + max.x) * 0.5, (min.y + max.y) * 0.5, (min.z + max.z) * 0.5);
                ArrayList<SurfaceQuad> surfaces = new ArrayList<SurfaceQuad>();
                double totalArea = 0.0;
                int i = 0;
                while (i + 3 < vertices.size()) {
                    Object e4 = vertices.get(i);
                    Intrinsics.checkNotNullExpressionValue(e4, (String)"get(...)");
                    Vec3d vec3d5 = (Vec3d)e4;
                    Object e5 = vertices.get(i + 1);
                    Intrinsics.checkNotNullExpressionValue(e5, (String)"get(...)");
                    Vec3d vec3d6 = (Vec3d)e5;
                    Object e6 = vertices.get(i + 2);
                    Intrinsics.checkNotNullExpressionValue(e6, (String)"get(...)");
                    Vec3d vec3d7 = (Vec3d)e6;
                    Object e7 = vertices.get(i + 3);
                    Intrinsics.checkNotNullExpressionValue(e7, (String)"get(...)");
                    SurfaceQuad surface = new SurfaceQuad(vec3d5, vec3d6, vec3d7, (Vec3d)e7);
                    if (surface.getArea() > 1.0E-7) {
                        surfaces.add(surface);
                        totalArea += surface.getArea();
                    }
                    i += 4;
                }
                return new CapturedModel(vertices, surfaces, totalArea, center, null);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0018\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J'\u0010\n\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ/\u0010\u0010\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0013J\u001f\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u0019J'\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u001b\u0010\u000bJ\u0017\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0013\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u001f\u00a2\u0006\u0004\b!\u0010\"R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010#R$\u0010!\u001a\u0012\u0012\u0004\u0012\u00020 0$j\b\u0012\u0004\u0012\u00020 `%8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010&\u00a8\u0006'"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$CapturingVertexConsumer;", "Lnet/minecraft/VertexConsumer;", "", "maxVertices", "<init>", "(I)V", "", "x", "y", "z", "addVertex", "(FFF)Lnet/minecraft/VertexConsumer;", "red", "green", "blue", "alpha", "setColor", "(IIII)Lnet/minecraft/VertexConsumer;", "color", "(I)Lnet/minecraft/VertexConsumer;", "u", "v", "setUv", "(FF)Lnet/minecraft/VertexConsumer;", "setUv1", "(II)Lnet/minecraft/VertexConsumer;", "setUv2", "setNormal", "width", "setLineWidth", "(F)Lnet/minecraft/VertexConsumer;", "", "Lnet/minecraft/Vec3d;", "vertices", "()Ljava/util/List;", "I", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "Ljava/util/ArrayList;", "rtx.kimiko:kimiko"})
    private static final class CapturingVertexConsumer
    implements VertexConsumer {
        private final int maxVertices;
        @NotNull
        private final ArrayList<Vec3d> vertices;

        public CapturingVertexConsumer(int maxVertices) {
            this.maxVertices = maxVertices;
            this.vertices = new ArrayList();
        }

        @NotNull
        public VertexConsumer vertex(float x, float y, float z) {
            if (this.vertices.size() < this.maxVertices && INSTANCE.isFinite(x) && INSTANCE.isFinite(y) && INSTANCE.isFinite(z)) {
                this.vertices.add(new Vec3d((double)x, (double)y, (double)z));
            }
            return this;
        }

        @NotNull
        public VertexConsumer color(int red, int green, int blue, int alpha) {
            return this;
        }

        @NotNull
        public VertexConsumer color(int color) {
            return this;
        }

        @NotNull
        public VertexConsumer texture(float u, float v) {
            return this;
        }

        @NotNull
        public VertexConsumer overlay(int u, int v) {
            return this;
        }

        @NotNull
        public VertexConsumer light(int u, int v) {
            return this;
        }

        @NotNull
        public VertexConsumer normal(float x, float y, float z) {
            return this;
        }

        @NotNull
        public VertexConsumer lineWidth(float width) {
            return this;
        }

        @NotNull
        public final List<Vec3d> vertices() {
            return this.vertices;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\b\u0082\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000b\u0010\fB)\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000b\u0010\rJ\u0015\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0013J\u0010\u0010\u0016\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0013J\u0010\u0010\u0017\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u0018JV\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007H\u00c6\u0001\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u001b\u0010\u001f\u001a\u00020\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001f\u0010 J\u0011\u0010\"\u001a\u00020!H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\"\u0010#J\u0011\u0010%\u001a\u00020$H\u00d6\u0081\u0004\u00a2\u0006\u0004\b%\u0010&R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010'\u001a\u0004\b(\u0010\u0013R\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010'\u001a\u0004\b)\u0010\u0013R\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010'\u001a\u0004\b*\u0010\u0013R\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010'\u001a\u0004\b+\u0010\u0013R\u0017\u0010\b\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\b\u0010,\u001a\u0004\b-\u0010\u0018R\u0017\u0010\t\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\t\u0010,\u001a\u0004\b.\u0010\u0018R\u0017\u0010\n\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\n\u0010,\u001a\u0004\b/\u0010\u0018\u00a8\u00060"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$SurfaceQuad;", "", "Lnet/minecraft/Vec3d;", "a", "b", "c", "d", "", "firstArea", "secondArea", "area", "<init>", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;DDD)V", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;)V", "Ljava/util/Random;", "random", "sample", "(Ljava/util/Random;)Lnet/minecraft/Vec3d;", "component1", "()Lnet/minecraft/Vec3d;", "component2", "component3", "component4", "component5", "()D", "component6", "component7", "copy", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;DDD)Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectModelCapture$SurfaceQuad;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/Vec3d;", "getA", "getB", "getC", "getD", "D", "getFirstArea", "getSecondArea", "getArea", "rtx.kimiko:kimiko"})
    private static final class SurfaceQuad {
        @NotNull
        private final Vec3d a;
        @NotNull
        private final Vec3d b;
        @NotNull
        private final Vec3d c;
        @NotNull
        private final Vec3d d;
        private final double firstArea;
        private final double secondArea;
        private final double area;

        public SurfaceQuad(@NotNull Vec3d a, @NotNull Vec3d b, @NotNull Vec3d c, @NotNull Vec3d d, double firstArea, double secondArea, double area) {
            Intrinsics.checkNotNullParameter((Object)a, (String)"a");
            Intrinsics.checkNotNullParameter((Object)b, (String)"b");
            Intrinsics.checkNotNullParameter((Object)c, (String)"c");
            Intrinsics.checkNotNullParameter((Object)d, (String)"d");
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.firstArea = firstArea;
            this.secondArea = secondArea;
            this.area = area;
        }

        @NotNull
        public final Vec3d getA() {
            return this.a;
        }

        @NotNull
        public final Vec3d getB() {
            return this.b;
        }

        @NotNull
        public final Vec3d getC() {
            return this.c;
        }

        @NotNull
        public final Vec3d getD() {
            return this.d;
        }

        public final double getFirstArea() {
            return this.firstArea;
        }

        public final double getSecondArea() {
            return this.secondArea;
        }

        public final double getArea() {
            return this.area;
        }

        public SurfaceQuad(@NotNull Vec3d a, @NotNull Vec3d b, @NotNull Vec3d c, @NotNull Vec3d d) {
            this(a, b, c, d, INSTANCE.triangleArea(a, b, c), INSTANCE.triangleArea(a, c, d), INSTANCE.triangleArea(a, b, c) + INSTANCE.triangleArea(a, c, d));
        }

        @NotNull
        public final Vec3d sample(@NotNull Random random) {
            Intrinsics.checkNotNullParameter((Object)random, (String)"random");
            if (random.nextDouble() * this.area <= this.firstArea) {
                return INSTANCE.sampleTriangle(this.a, this.b, this.c, random);
            }
            return INSTANCE.sampleTriangle(this.a, this.c, this.d, random);
        }

        @NotNull
        public final Vec3d component1() {
            return this.a;
        }

        @NotNull
        public final Vec3d component2() {
            return this.b;
        }

        @NotNull
        public final Vec3d component3() {
            return this.c;
        }

        @NotNull
        public final Vec3d component4() {
            return this.d;
        }

        public final double component5() {
            return this.firstArea;
        }

        public final double component6() {
            return this.secondArea;
        }

        public final double component7() {
            return this.area;
        }

        @NotNull
        public final SurfaceQuad copy(@NotNull Vec3d a, @NotNull Vec3d b, @NotNull Vec3d c, @NotNull Vec3d d, double firstArea, double secondArea, double area) {
            Intrinsics.checkNotNullParameter((Object)a, (String)"a");
            Intrinsics.checkNotNullParameter((Object)b, (String)"b");
            Intrinsics.checkNotNullParameter((Object)c, (String)"c");
            Intrinsics.checkNotNullParameter((Object)d, (String)"d");
            return new SurfaceQuad(a, b, c, d, firstArea, secondArea, area);
        }

        public static /* synthetic */ SurfaceQuad copy$default(SurfaceQuad surfaceQuad, Vec3d vec3d2, Vec3d vec3d3, Vec3d vec3d4, Vec3d vec3d5, double d, double d2, double d3, int n, Object object) {
            if ((n & 1) != 0) {
                vec3d2 = surfaceQuad.a;
            }
            if ((n & 2) != 0) {
                vec3d3 = surfaceQuad.b;
            }
            if ((n & 4) != 0) {
                vec3d4 = surfaceQuad.c;
            }
            if ((n & 8) != 0) {
                vec3d5 = surfaceQuad.d;
            }
            if ((n & 0x10) != 0) {
                d = surfaceQuad.firstArea;
            }
            if ((n & 0x20) != 0) {
                d2 = surfaceQuad.secondArea;
            }
            if ((n & 0x40) != 0) {
                d3 = surfaceQuad.area;
            }
            return surfaceQuad.copy(vec3d2, vec3d3, vec3d4, vec3d5, d, d2, d3);
        }

        @NotNull
        public String toString() {
            return "SurfaceQuad(a=" + this.a + ", b=" + this.b + ", c=" + this.c + ", d=" + this.d + ", firstArea=" + this.firstArea + ", secondArea=" + this.secondArea + ", area=" + this.area + ")";
        }

        public int hashCode() {
            int result = this.a.hashCode();
            result = result * 31 + this.b.hashCode();
            result = result * 31 + this.c.hashCode();
            result = result * 31 + this.d.hashCode();
            result = result * 31 + Double.hashCode(this.firstArea);
            result = result * 31 + Double.hashCode(this.secondArea);
            result = result * 31 + Double.hashCode(this.area);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SurfaceQuad)) {
                return false;
            }
            SurfaceQuad surfaceQuad = (SurfaceQuad)other;
            if (!Intrinsics.areEqual((Object)this.a, (Object)surfaceQuad.a)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.b, (Object)surfaceQuad.b)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.c, (Object)surfaceQuad.c)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.d, (Object)surfaceQuad.d)) {
                return false;
            }
            if (Double.compare(this.firstArea, surfaceQuad.firstArea) != 0) {
                return false;
            }
            if (Double.compare(this.secondArea, surfaceQuad.secondArea) != 0) {
                return false;
            }
            return Double.compare(this.area, surfaceQuad.area) == 0;
        }
    }
}

