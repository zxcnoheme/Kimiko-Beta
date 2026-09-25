/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.floats.FloatArrayList
 *  it.unimi.dsi.fastutil.ints.IntArrayList
 *  it.unimi.dsi.fastutil.objects.Object2ObjectSortedMaps
 *  it.unimi.dsi.fastutil.objects.ObjectCollection
 *  it.unimi.dsi.fastutil.objects.ObjectIterator
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.client.render.entity.state.EntityRenderState
 *  net.minecraft.client.render.state.WorldRenderState
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.client.render.command.OrderedRenderCommandQueueImpl
 *  net.minecraft.client.render.command.ModelCommandRenderer
 *  net.minecraft.client.render.command.BatchingRenderCommandQueue
 *  net.minecraft.client.render.command.ModelPartCommandRenderer
 *  net.minecraft.client.render.state.CameraRenderState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.world.BlockRenderView
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.render.OverlayTexture
 *  net.minecraft.client.render.OutlineVertexConsumerProvider
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  net.minecraft.client.render.WorldRenderer
 *  net.minecraft.entity.player.PlayerSkinType
 *  net.minecraft.entity.player.SkinTextures
 *  net.minecraft.client.render.entity.EntityRenderManager
 *  net.minecraft.client.util.BufferAllocator
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.trails;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.objects.Object2ObjectSortedMaps;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SequencedMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import mixin.accessor.RenderSetupAccessor;
import mixin.accessor.RenderTypeAccessor;
import mixin.accessor.TextureBindingAccessor;
import net.minecraft.client.render.RenderSetup;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.state.WorldRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.command.OrderedRenderCommandQueueImpl;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.BatchingRenderCommandQueue;
import net.minecraft.client.render.command.ModelPartCommandRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.BlockRenderView;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.OutlineVertexConsumerProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.entity.player.PlayerSkinType;
import net.minecraft.entity.player.SkinTextures;
import net.minecraft.client.render.entity.EntityRenderManager;
import net.minecraft.client.util.BufferAllocator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0010\n\u0002\u0010\u0015\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0003stuB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J1\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eJ#\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J7\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0017\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J-\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJo\u0010,\u001a\u00020+2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b,\u0010-Jg\u00106\u001a\u00020+2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020.2\u0006\u00100\u001a\u00020.2\u0006\u00101\u001a\u00020.2\u0006\u0010%\u001a\u00020.2\u0006\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u00020\u00062\u0006\u00105\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b6\u00107J?\u0010;\u001a\u00020+2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u00108\u001a\u00020.2\u0006\u00109\u001a\u00020\u00062\u0006\u0010:\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b;\u0010<J\u001d\u0010=\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b=\u0010>J'\u0010C\u001a\u00020\b2\u0006\u0010@\u001a\u00020?2\u0006\u0010A\u001a\u00020\u00122\u0006\u0010B\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\bC\u0010DJ'\u0010F\u001a\u00020\u00122\u0006\u0010E\u001a\u00020.2\u0006\u0010A\u001a\u00020\u00122\u0006\u0010B\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\bF\u0010GJO\u0010M\u001a\u00020\u000b2\u0006\u0010E\u001a\u00020.2\u0006\u0010H\u001a\u00020.2\u0006\u0010I\u001a\u00020?2\u0006\u0010J\u001a\u00020?2\u0006\u0010K\u001a\u00020?2\u0006\u0010A\u001a\u00020\u00122\u0006\u0010B\u001a\u00020\u00122\u0006\u0010L\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\bM\u0010NJ\u0019\u0010Q\u001a\u0004\u0018\u00010\u00152\u0006\u0010P\u001a\u00020OH\u0002\u00a2\u0006\u0004\bQ\u0010RJ\u0019\u0010S\u001a\u0004\u0018\u00010\u00152\u0006\u0010P\u001a\u00020OH\u0002\u00a2\u0006\u0004\bS\u0010RJ\u0017\u0010U\u001a\u00020\b2\u0006\u0010T\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bU\u0010VR\u0014\u0010W\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bW\u0010XR\u0014\u0010Y\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bY\u0010ZR\u0014\u0010[\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b[\u0010ZR\u0014\u0010\\\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\\\u0010XR\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010a\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\ba\u0010bR\u0014\u0010d\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bd\u0010eR\u0014\u0010g\u001a\u00020f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bg\u0010hR\"\u0010j\u001a\u0010\u0012\u0004\u0012\u00020O\u0012\u0006\u0012\u0004\u0018\u00010\u00150i8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bj\u0010kR\u0014\u0010l\u001a\u00020\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bl\u0010mR\u0014\u0010o\u001a\u00020n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bo\u0010pR\u0016\u0010q\u001a\u00020.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bq\u0010r\u00a8\u0006v"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture;", "", "<init>", "()V", "Lnet/minecraft/LivingEntity;", "entity", "", "partialTick", "", "skinOnly", "", "Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$EchoBox;", "Lkotlin/jvm/JvmStatic;", "capture", "(Lnet/minecraft/LivingEntity;FZ)Ljava/util/List;", "boxes", "removeOverlays", "(Ljava/util/List;)Ljava/util/List;", "", "entityLight", "(Lnet/minecraft/LivingEntity;)I", "Lnet/minecraft/Identifier;", "filter", "fallbackLight", "captureRendered", "(Lnet/minecraft/LivingEntity;FLnet/minecraft/Identifier;I)Ljava/util/List;", "Lnet/minecraft/AbstractClientPlayerEntity;", "player", "captureHumanoidFallback", "(Lnet/minecraft/AbstractClientPlayerEntity;FI)Ljava/util/List;", "Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$CapturingVertexConsumer;", "c", "ox", "oy", "oz", "w", "h", "d", "tu", "tv", "g", "cs", "sn", "", "appendSkinCube", "(Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$CapturingVertexConsumer;FFFFFFFFFFF)V", "", "a", "b", "cc", "uA", "vA", "uB", "vB", "skinQuad", "(Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$CapturingVertexConsumer;[F[F[F[FFFFFFF)V", "p", "u", "v", "skinVertex", "(Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$CapturingVertexConsumer;[FFFFF)V", "buildBoxes", "(Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$CapturingVertexConsumer;)Ljava/util/List;", "", "tex", "q", "count", "sameTexture", "([III)Z", "pos", "uniqueCorners", "([FII)I", "uv", "col", "lightList", "overlayList", "texture", "makeBox", "([F[F[I[I[IIILnet/minecraft/Identifier;)Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$EchoBox;", "Lnet/minecraft/RenderLayer;", "type", "textureOf", "(Lnet/minecraft/RenderLayer;)Lnet/minecraft/Identifier;", "resolveTexture", "value", "isFinite", "(F)Z", "MAX_VERTICES", "I", "CORNER_EPS", "F", "MIN_HALF", "FULL_BRIGHT", "Lnet/minecraft/OrderedRenderCommandQueueImpl;", "STORAGE", "Lnet/minecraft/OrderedRenderCommandQueueImpl;", "Lnet/minecraft/OutlineVertexConsumerProvider;", "OUTLINE_SOURCE", "Lnet/minecraft/OutlineVertexConsumerProvider;", "Lnet/minecraft/ModelCommandRenderer;", "MODEL_RENDERER", "Lnet/minecraft/ModelCommandRenderer;", "Lnet/minecraft/ModelPartCommandRenderer;", "MODEL_PART_RENDERER", "Lnet/minecraft/ModelPartCommandRenderer;", "Ljava/util/HashMap;", "TEXTURE_CACHE", "Ljava/util/HashMap;", "SHARED_CONSUMER", "Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$CapturingVertexConsumer;", "Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$CaptureSource;", "SHARED_SOURCE", "Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$CaptureSource;", "VOLUMES", "[F", "EchoBox", "CaptureSource", "CapturingVertexConsumer", "rtx.kimiko:kimiko"})
public final class TrailEchoCapture {
    @NotNull
    public static final TrailEchoCapture INSTANCE = new TrailEchoCapture();
    private static final int MAX_VERTICES = 24000;
    private static final float CORNER_EPS = 1.0E-4f;
    private static final float MIN_HALF = 0.004f;
    private static final int FULL_BRIGHT = 0xF000F0;
    @NotNull
    private static final OrderedRenderCommandQueueImpl STORAGE = new OrderedRenderCommandQueueImpl();
    @NotNull
    private static final OutlineVertexConsumerProvider OUTLINE_SOURCE = new OutlineVertexConsumerProvider();
    @NotNull
    private static final ModelCommandRenderer MODEL_RENDERER = new ModelCommandRenderer();
    @NotNull
    private static final ModelPartCommandRenderer MODEL_PART_RENDERER = new ModelPartCommandRenderer();
    @NotNull
    private static final HashMap<RenderLayer, Identifier> TEXTURE_CACHE = new HashMap();
    @NotNull
    private static final CapturingVertexConsumer SHARED_CONSUMER = new CapturingVertexConsumer();
    @NotNull
    private static final CaptureSource SHARED_SOURCE = new CaptureSource(SHARED_CONSUMER);
    @NotNull
    private static float[] VOLUMES = new float[64];

    private TrailEchoCapture() {
    }

    @JvmStatic
    @NotNull
    public static final List<EchoBox> capture(@NotNull LivingEntity entity, float partialTick, boolean skinOnly) {
        Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
        Identifier skin = skinOnly && entity instanceof AbstractClientPlayerEntity ? ((AbstractClientPlayerEntity)entity).getSkin().body().texturePath() : null;
        int fallbackLight = INSTANCE.entityLight(entity);
        List<EchoBox> rendered = INSTANCE.captureRendered(entity, partialTick, skin, fallbackLight);
        if (rendered.isEmpty() && entity instanceof AbstractClientPlayerEntity) {
            rendered = INSTANCE.captureHumanoidFallback((AbstractClientPlayerEntity)entity, partialTick, fallbackLight);
        }
        return skinOnly ? INSTANCE.removeOverlays(rendered) : rendered;
    }

    private final List<EchoBox> removeOverlays(List<EchoBox> boxes) {
        int size = boxes.size();
        if (size < 2) {
            return boxes;
        }
        if (VOLUMES.length < size) {
            VOLUMES = new float[size];
        }
        float[] volumes = VOLUMES;
        for (int i = 0; i < size; ++i) {
            float[] half = boxes.get(i).getHalf();
            volumes[i] = half[0] * half[1] * half[2];
        }
        ArrayList<EchoBox> kept = new ArrayList<EchoBox>(size);
        block1: for (int i = 0; i < size; ++i) {
            EchoBox a = boxes.get(i);
            float va = volumes[i];
            for (int j = 0; j < size; ++j) {
                float dz;
                float dy;
                if (i == j) continue;
                EchoBox b = boxes.get(j);
                float dx = a.getCx() - b.getCx();
                if (!(dx * dx + (dy = a.getCy() - b.getCy()) * dy + (dz = a.getCz() - b.getCz()) * dz > 9.0E-4f) && va > volumes[j] * 1.05f) continue block1;
            }
            kept.add(a);
        }
        return kept;
    }

    private final int entityLight(LivingEntity entity) {
        int n;
        ClientWorld clientWorld3 = MinecraftClient.getInstance().world;
        if (clientWorld3 == null) {
            return 0xF000F0;
        }
        ClientWorld level = clientWorld3;
        try {
            n = WorldRenderer.getLightmapCoordinates((BlockRenderView)((BlockRenderView)level), (BlockPos)BlockPos.ofFloored((double)entity.getX(), (double)(entity.getY() + (double)(entity.getHeight() * 0.5f)), (double)entity.getZ()));
        }
        catch (Throwable ignored) {
            n = 0xF000F0;
        }
        return n;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final List<EchoBox> captureRendered(LivingEntity entity, float partialTick, Identifier filter, int fallbackLight) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        EntityRenderManager entityRenderManager2 = minecraft.getEntityRenderDispatcher();
        if (entityRenderManager2 == null) {
            return CollectionsKt.emptyList();
        }
        EntityRenderManager dispatcher = entityRenderManager2;
        WorldRenderer worldRenderer2 = minecraft.worldRenderer;
        if (worldRenderer2 == null) {
            return CollectionsKt.emptyList();
        }
        WorldRenderer levelRenderer = worldRenderer2;
        WorldRenderState worldRenderState2 = levelRenderer.worldRenderState;
        if (worldRenderState2 == null) {
            return CollectionsKt.emptyList();
        }
        WorldRenderState levelRenderState = worldRenderState2;
        CameraRenderState cameraRenderState2 = levelRenderState.cameraRenderState;
        if (cameraRenderState2 == null) {
            return CollectionsKt.emptyList();
        }
        CameraRenderState cameraState = cameraRenderState2;
        CaptureSource source = SHARED_SOURCE;
        source.setFilter(filter);
        source.getConsumer().reset(24000, fallbackLight);
        STORAGE.clear();
        try {
            EntityRenderState state = dispatcher.getAndUpdateRenderState((Entity)entity, partialTick);
            if (state == null) {
                return CollectionsKt.emptyList();
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
                catch (Throwable throwable) {}
            }
        }
        catch (Throwable ignored) {
            List list = CollectionsKt.emptyList();
            return list;
        }
        finally {
            STORAGE.clear();
        }
        return this.buildBoxes(source.getConsumer());
    }

    private final List<EchoBox> captureHumanoidFallback(AbstractClientPlayerEntity player, float partialTick, int fallbackLight) {
        SkinTextures skinTextures2 = player.getSkin();
        Intrinsics.checkNotNullExpressionValue((Object)skinTextures2, (String)"getSkin(...)");
        SkinTextures skin = skinTextures2;
        Identifier identifier2 = skin.body().texturePath();
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"texturePath(...)");
        Identifier texture = identifier2;
        boolean slim = skin.model() == PlayerSkinType.SLIM;
        float yaw = MathHelper.lerpAngleDegrees((float)partialTick, (float)player.lastBodyYaw, (float)player.bodyYaw);
        double angle = Math.toRadians(180.0f - yaw);
        float cs = (float)Math.cos(angle);
        float sn = (float)Math.sin(angle);
        CapturingVertexConsumer consumer = SHARED_CONSUMER;
        consumer.reset(24000, fallbackLight);
        consumer.texture(texture);
        float armW = slim ? 3.0f : 4.0f;
        float armY = slim ? 0.5f : 0.0f;
        float armRX = slim ? -7.0f : -8.0f;
        float[][] fArrayArray = new float[12][];
        float[] fArray = new float[]{-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 0.0f, 0.0f, 0.0f};
        fArrayArray[0] = fArray;
        fArray = new float[]{-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 32.0f, 0.0f, 0.5f};
        fArrayArray[1] = fArray;
        fArray = new float[]{-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 16.0f, 16.0f, 0.0f};
        fArrayArray[2] = fArray;
        fArray = new float[]{-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 16.0f, 32.0f, 0.25f};
        fArrayArray[3] = fArray;
        fArray = new float[]{armRX, armY, -2.0f, armW, 12.0f, 4.0f, 40.0f, 16.0f, 0.0f};
        fArrayArray[4] = fArray;
        fArray = new float[]{armRX, armY, -2.0f, armW, 12.0f, 4.0f, 40.0f, 32.0f, 0.25f};
        fArrayArray[5] = fArray;
        fArray = new float[]{4.0f, armY, -2.0f, armW, 12.0f, 4.0f, 32.0f, 48.0f, 0.0f};
        fArrayArray[6] = fArray;
        fArray = new float[]{4.0f, armY, -2.0f, armW, 12.0f, 4.0f, 48.0f, 48.0f, 0.25f};
        fArrayArray[7] = fArray;
        fArray = new float[]{-3.9f, 12.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.0f, 16.0f, 0.0f};
        fArrayArray[8] = fArray;
        fArray = new float[]{-3.9f, 12.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.0f, 32.0f, 0.25f};
        fArrayArray[9] = fArray;
        fArray = new float[]{-0.1f, 12.0f, -2.0f, 4.0f, 12.0f, 4.0f, 16.0f, 48.0f, 0.0f};
        fArrayArray[10] = fArray;
        fArray = new float[]{-0.1f, 12.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.0f, 48.0f, 0.25f};
        fArrayArray[11] = fArray;
        float[][] parts = fArrayArray;
        int n = ((Object[])parts).length;
        for (int i = 0; i < n; ++i) {
            float[] p = parts[i];
            this.appendSkinCube(consumer, p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7], p[8], cs, sn);
        }
        return this.buildBoxes(consumer);
    }

    private final void appendSkinCube(CapturingVertexConsumer c, float ox, float oy, float oz, float w, float h, float d, float tu, float tv, float g, float cs, float sn) {
        float x0 = ox - g;
        float y0 = oy - g;
        float z0 = oz - g;
        float x1 = ox + w + g;
        float y1 = oy + h + g;
        float z1 = oz + d + g;
        float u0 = tu;
        float u1 = u0 + d;
        float u2 = u1 + w;
        float u3 = u2 + w;
        float u4 = u2 + d;
        float u5 = u4 + w;
        float v0 = tv;
        float v1 = v0 + d;
        float v2 = v1 + h;
        float[][] fArrayArray = new float[8][];
        float[] fArray = new float[]{x0, y0, z0};
        fArrayArray[0] = fArray;
        fArray = new float[]{x1, y0, z0};
        fArrayArray[1] = fArray;
        fArray = new float[]{x1, y1, z0};
        fArrayArray[2] = fArray;
        fArray = new float[]{x0, y1, z0};
        fArrayArray[3] = fArray;
        fArray = new float[]{x0, y0, z1};
        fArrayArray[4] = fArray;
        fArray = new float[]{x1, y0, z1};
        fArrayArray[5] = fArray;
        fArray = new float[]{x1, y1, z1};
        fArrayArray[6] = fArray;
        fArray = new float[]{x0, y1, z1};
        fArrayArray[7] = fArray;
        float[][] v = fArrayArray;
        this.skinQuad(c, v[5], v[4], v[0], v[1], u1, v0, u2, v1, cs, sn);
        this.skinQuad(c, v[2], v[3], v[7], v[6], u2, v1, u3, v0, cs, sn);
        this.skinQuad(c, v[0], v[4], v[7], v[3], u0, v1, u1, v2, cs, sn);
        this.skinQuad(c, v[1], v[0], v[3], v[2], u1, v1, u2, v2, cs, sn);
        this.skinQuad(c, v[5], v[1], v[2], v[6], u2, v1, u4, v2, cs, sn);
        this.skinQuad(c, v[4], v[5], v[6], v[7], u4, v1, u5, v2, cs, sn);
    }

    private final void skinQuad(CapturingVertexConsumer c, float[] a, float[] b, float[] cc, float[] d, float uA, float vA, float uB, float vB, float cs, float sn) {
        this.skinVertex(c, a, uB, vA, cs, sn);
        this.skinVertex(c, b, uA, vA, cs, sn);
        this.skinVertex(c, cc, uA, vB, cs, sn);
        this.skinVertex(c, d, uB, vB, cs, sn);
    }

    private final void skinVertex(CapturingVertexConsumer c, float[] p, float u, float v, float cs, float sn) {
        float lx = -p[0] / 16.0f;
        float ly = 1.501f - p[1] / 16.0f;
        float lz = p[2] / 16.0f;
        float wx = lx * cs + lz * sn;
        float wz = -lx * sn + lz * cs;
        c.vertex(wx, ly, wz);
        c.texture(u / 64.0f, v / 64.0f);
        c.color(-1);
    }

    private final List<EchoBox> buildBoxes(CapturingVertexConsumer c) {
        int take;
        float[] pos = c.getPositions().elements();
        float[] uv = c.getUvs().elements();
        int[] col = c.getColors().elements();
        int[] lit = c.getLights().elements();
        int[] ovl = c.getOverlays().elements();
        int[] tex = c.getTextures().elements();
        ArrayList<Identifier> textures = c.getTextureList();
        int quadCount = c.getPositions().size() / 12;
        ArrayList<EchoBox> result = new ArrayList<EchoBox>();
        for (int q = 0; q < quadCount; q += take) {
            take = 1;
            if (q + 6 <= quadCount) {
                Intrinsics.checkNotNull((Object)tex);
                if (this.sameTexture(tex, q, 6)) {
                    Intrinsics.checkNotNull((Object)pos);
                    if (this.uniqueCorners(pos, q, 6) == 8) {
                        take = 6;
                    }
                }
            }
            Identifier texture = textures.get(tex[q * 4]);
            Intrinsics.checkNotNull((Object)pos);
            Intrinsics.checkNotNull((Object)uv);
            Intrinsics.checkNotNull((Object)col);
            Intrinsics.checkNotNull((Object)lit);
            Intrinsics.checkNotNull((Object)ovl);
            result.add(this.makeBox(pos, uv, col, lit, ovl, q, take, texture));
        }
        return result;
    }

    private final boolean sameTexture(int[] tex, int q, int count) {
        int first = tex[q * 4];
        for (int i = 1; i < count; ++i) {
            if (tex[(q + i) * 4] == first) continue;
            return false;
        }
        return true;
    }

    private final int uniqueCorners(float[] pos, int q, int count) {
        int n = count * 4;
        int base = q * 12;
        int unique = 0;
        for (int i = 0; i < n; ++i) {
            int io = base + i * 3;
            float ix = pos[io];
            float iy = pos[io + 1];
            float iz = pos[io + 2];
            boolean seen = false;
            for (int j = 0; j < i; ++j) {
                int jo = base + j * 3;
                if (!(Math.abs(pos[jo] - ix) < 1.0E-4f) || !(Math.abs(pos[jo + 1] - iy) < 1.0E-4f) || !(Math.abs(pos[jo + 2] - iz) < 1.0E-4f)) continue;
                seen = true;
                break;
            }
            if (seen || ++unique <= 8) continue;
            return unique;
        }
        return unique;
    }

    private final EchoBox makeBox(float[] pos, float[] uv, int[] col, int[] lightList, int[] overlayList, int q, int count, Identifier texture) {
        float dotAB;
        float bl;
        int n = count * 4;
        int base = q * 12;
        float cx = 0.0f;
        float cy = 0.0f;
        float cz = 0.0f;
        for (int i = 0; i < n; ++i) {
            cx += pos[base + i * 3];
            cy += pos[base + i * 3 + 1];
            cz += pos[base + i * 3 + 2];
        }
        cx /= (float)n;
        cy /= (float)n;
        cz /= (float)n;
        float ax = pos[base + 3] - pos[base];
        float ay = pos[base + 4] - pos[base + 1];
        float az = pos[base + 5] - pos[base + 2];
        float al = (float)Math.sqrt(ax * ax + ay * ay + az * az);
        if (al < 1.0E-6f) {
            ax = 1.0f;
            ay = 0.0f;
            az = 0.0f;
            al = 1.0f;
        }
        ax /= al;
        ay /= al;
        az /= al;
        float bx = pos[base + 9] - pos[base];
        float by = pos[base + 10] - pos[base + 1];
        float bz = pos[base + 11] - pos[base + 2];
        if ((bl = (float)Math.sqrt((bx -= ax * (dotAB = ax * bx + ay * by + az * bz)) * bx + (by -= ay * dotAB) * by + (bz -= az * dotAB) * bz)) < 1.0E-6f) {
            float hx = Math.abs(ay) < 0.9f ? 0.0f : 1.0f;
            float hy = Math.abs(ay) < 0.9f ? 1.0f : 0.0f;
            bx = ay * 0.0f - az * hy;
            bl = (float)Math.sqrt(bx * bx + (by = az * hx - ax * 0.0f) * by + (bz = ax * hy - ay * hx) * bz);
            if (bl < 1.0E-6f) {
                bx = 0.0f;
                by = 1.0f;
                bz = 0.0f;
                bl = 1.0f;
            }
        }
        float nx = ay * (bz /= bl) - az * (by /= bl);
        float ny = az * (bx /= bl) - ax * bz;
        float nz = ax * by - ay * bx;
        float ha = 0.0f;
        float hb = 0.0f;
        float hn = 0.0f;
        float[] verts = new float[n * 3];
        float[] uvs = new float[n * 2];
        for (int i = 0; i < n; ++i) {
            float lx = pos[base + i * 3] - cx;
            float ly = pos[base + i * 3 + 1] - cy;
            float lz = pos[base + i * 3 + 2] - cz;
            verts[i * 3] = lx;
            verts[i * 3 + 1] = ly;
            verts[i * 3 + 2] = lz;
            uvs[i * 2] = uv[(q * 4 + i) * 2];
            uvs[i * 2 + 1] = uv[(q * 4 + i) * 2 + 1];
            ha = Math.max(ha, Math.abs(lx * ax + ly * ay + lz * az));
            hb = Math.max(hb, Math.abs(lx * bx + ly * by + lz * bz));
            hn = Math.max(hn, Math.abs(lx * nx + ly * ny + lz * nz));
        }
        int[] colors = new int[count];
        int[] lights = new int[count];
        int[] overlays = new int[count];
        for (int i = 0; i < count; ++i) {
            colors[i] = col[(q + i) * 4];
            lights[i] = lightList[(q + i) * 4];
            overlays[i] = overlayList[(q + i) * 4];
        }
        float[] fArray = new float[]{ax, ay, az, bx, by, bz, nx, ny, nz};
        float[] fArray2 = fArray;
        fArray = new float[]{Math.max(ha, 0.004f), Math.max(hb, 0.004f), Math.max(hn, 0.004f)};
        return new EchoBox(cx, cy, cz, fArray2, fArray, verts, uvs, colors, lights, overlays, texture);
    }

    private final Identifier textureOf(RenderLayer type) {
        if (TEXTURE_CACHE.containsKey(type)) {
            return TEXTURE_CACHE.get(type);
        }
        if (TEXTURE_CACHE.size() > 512) {
            TEXTURE_CACHE.clear();
        }
        Identifier resolved = this.resolveTexture(type);
        ((Map)TEXTURE_CACHE).put(type, resolved);
        return resolved;
    }

    private final Identifier resolveTexture(RenderLayer type) {
        try {
            if (!(type instanceof RenderTypeAccessor)) {
                return null;
            }
            RenderSetup setup = ((RenderTypeAccessor) type).kimiko$getState();
            if (!((Object) setup instanceof RenderSetupAccessor)) {
                return null;
            }
            Map<String, Object> textures = ((RenderSetupAccessor) (Object) setup).kimiko$getTextures();
            if (textures == null) {
                return null;
            }
            Object binding = textures.get("Sampler0");
            if (!(binding instanceof TextureBindingAccessor)) {
                return null;
            }
            Identifier location = ((TextureBindingAccessor) binding).kimiko$getLocation();
            if (location == null) {
                return null;
            }
            if (location.getPath().contains("glint")) {
                return null;
            }
            return location;
        } catch (Throwable ignored) {
            return null;
        }
    }

    private final boolean isFinite(float value) {
        return Float.isFinite(value) && Math.abs(value) < 512.0f;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u000b\u001a\u0004\b\f\u0010\rR$\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0016"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$CaptureSource;", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$CapturingVertexConsumer;", "consumer", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$CapturingVertexConsumer;)V", "Lnet/minecraft/RenderLayer;", "renderType", "Lnet/minecraft/VertexConsumer;", "getBuffer", "(Lnet/minecraft/RenderLayer;)Lnet/minecraft/VertexConsumer;", "Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$CapturingVertexConsumer;", "getConsumer", "()Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$CapturingVertexConsumer;", "Lnet/minecraft/Identifier;", "filter", "Lnet/minecraft/Identifier;", "getFilter", "()Lnet/minecraft/Identifier;", "setFilter", "(Lnet/minecraft/Identifier;)V", "Companion", "rtx.kimiko:kimiko"})
    private static final class CaptureSource
    extends VertexConsumerProvider.Immediate {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @NotNull
        private final CapturingVertexConsumer consumer;
        @Nullable
        private Identifier filter;
        @NotNull
        private static final BufferAllocator SCRATCH = new BufferAllocator(256);

        public CaptureSource(@NotNull CapturingVertexConsumer consumer) {
            super(SCRATCH, (SequencedMap)Object2ObjectSortedMaps.emptyMap());
            this.consumer = consumer;
        }

        @NotNull
        public final CapturingVertexConsumer getConsumer() {
            return this.consumer;
        }

        @Nullable
        public final Identifier getFilter() {
            return this.filter;
        }

        public final void setFilter(@Nullable Identifier identifier2) {
            this.filter = identifier2;
        }

        @NotNull
        public VertexConsumer getBuffer(@NotNull RenderLayer renderType) {
            Intrinsics.checkNotNullParameter((Object)renderType, (String)"renderType");
            Identifier texture = INSTANCE.textureOf(renderType);
            Identifier current = this.filter;
            this.consumer.texture((Identifier)(current != null && !Intrinsics.areEqual((Object)texture, (Object)current) ? null : texture));
            return this.consumer;
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$CaptureSource.Companion;", "", "<init>", "()V", "Lnet/minecraft/BufferAllocator;", "SCRATCH", "Lnet/minecraft/BufferAllocator;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012J/\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u001aJ\u001f\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u001f\u0010 J\u001f\u0010!\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b!\u0010 J'\u0010\"\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\"\u0010\u0012J\u0017\u0010$\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b$\u0010%R\u0017\u0010'\u001a\u00020&8\u0006\u00a2\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R\u0017\u0010+\u001a\u00020&8\u0006\u00a2\u0006\f\n\u0004\b+\u0010(\u001a\u0004\b,\u0010*R\u0017\u0010.\u001a\u00020-8\u0006\u00a2\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u00101R\u0017\u00102\u001a\u00020-8\u0006\u00a2\u0006\f\n\u0004\b2\u0010/\u001a\u0004\b3\u00101R\u0017\u00104\u001a\u00020-8\u0006\u00a2\u0006\f\n\u0004\b4\u0010/\u001a\u0004\b5\u00101R\u0017\u00106\u001a\u00020-8\u0006\u00a2\u0006\f\n\u0004\b6\u0010/\u001a\u0004\b7\u00101R\u001d\u00109\u001a\b\u0012\u0004\u0012\u00020\n088\u0006\u00a2\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010=R\u0016\u0010\u0006\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010=R\u0016\u0010>\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010=R\u0016\u0010@\u001a\u00020?8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u0010A\u00a8\u0006B"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$CapturingVertexConsumer;", "Lnet/minecraft/VertexConsumer;", "<init>", "()V", "", "maxVertices", "fallbackLight", "", "reset", "(II)V", "Lnet/minecraft/Identifier;", "texture", "(Lnet/minecraft/Identifier;)V", "", "x", "y", "z", "addVertex", "(FFF)Lnet/minecraft/VertexConsumer;", "red", "green", "blue", "alpha", "setColor", "(IIII)Lnet/minecraft/VertexConsumer;", "color", "(I)Lnet/minecraft/VertexConsumer;", "u", "v", "setUv", "(FF)Lnet/minecraft/VertexConsumer;", "setUv1", "(II)Lnet/minecraft/VertexConsumer;", "setUv2", "setNormal", "width", "setLineWidth", "(F)Lnet/minecraft/VertexConsumer;", "Lit/unimi/dsi/fastutil/floats/FloatArrayList;", "positions", "Lit/unimi/dsi/fastutil/floats/FloatArrayList;", "getPositions", "()Lit/unimi/dsi/fastutil/floats/FloatArrayList;", "uvs", "getUvs", "Lit/unimi/dsi/fastutil/ints/IntArrayList;", "colors", "Lit/unimi/dsi/fastutil/ints/IntArrayList;", "getColors", "()Lit/unimi/dsi/fastutil/ints/IntArrayList;", "lights", "getLights", "overlays", "getOverlays", "textures", "getTextures", "Ljava/util/ArrayList;", "textureList", "Ljava/util/ArrayList;", "getTextureList", "()Ljava/util/ArrayList;", "I", "textureIndex", "", "accepted", "Z", "rtx.kimiko:kimiko"})
    private static final class CapturingVertexConsumer
    implements VertexConsumer {
        @NotNull
        private final FloatArrayList positions = new FloatArrayList();
        @NotNull
        private final FloatArrayList uvs = new FloatArrayList();
        @NotNull
        private final IntArrayList colors = new IntArrayList();
        @NotNull
        private final IntArrayList lights = new IntArrayList();
        @NotNull
        private final IntArrayList overlays = new IntArrayList();
        @NotNull
        private final IntArrayList textures = new IntArrayList();
        @NotNull
        private final ArrayList<Identifier> textureList = new ArrayList();
        private int maxVertices;
        private int fallbackLight = 0xF000F0;
        private int textureIndex = -1;
        private boolean accepted;

        @NotNull
        public final FloatArrayList getPositions() {
            return this.positions;
        }

        @NotNull
        public final FloatArrayList getUvs() {
            return this.uvs;
        }

        @NotNull
        public final IntArrayList getColors() {
            return this.colors;
        }

        @NotNull
        public final IntArrayList getLights() {
            return this.lights;
        }

        @NotNull
        public final IntArrayList getOverlays() {
            return this.overlays;
        }

        @NotNull
        public final IntArrayList getTextures() {
            return this.textures;
        }

        @NotNull
        public final ArrayList<Identifier> getTextureList() {
            return this.textureList;
        }

        public final void reset(int maxVertices, int fallbackLight) {
            this.maxVertices = maxVertices;
            this.fallbackLight = fallbackLight;
            this.positions.clear();
            this.uvs.clear();
            this.colors.clear();
            this.lights.clear();
            this.overlays.clear();
            this.textures.clear();
            this.textureList.clear();
            this.textureIndex = -1;
            this.accepted = false;
        }

        public final void texture(@Nullable Identifier texture) {
            if (texture == null) {
                this.textureIndex = -1;
                return;
            }
            int index = this.textureList.indexOf(texture);
            if (index < 0) {
                index = this.textureList.size();
                this.textureList.add(texture);
            }
            this.textureIndex = index;
        }

        @NotNull
        public VertexConsumer vertex(float x, float y, float z) {
            boolean bl = this.accepted = this.textureIndex >= 0 && this.textures.size() < this.maxVertices && INSTANCE.isFinite(x) && INSTANCE.isFinite(y) && INSTANCE.isFinite(z);
            if (this.accepted) {
                this.positions.add(x);
                this.positions.add(y);
                this.positions.add(z);
                this.uvs.add(0.0f);
                this.uvs.add(0.0f);
                this.colors.add(-1);
                this.lights.add(this.fallbackLight);
                this.overlays.add(OverlayTexture.DEFAULT_UV);
                this.textures.add(this.textureIndex);
            }
            return this;
        }

        @NotNull
        public VertexConsumer color(int red, int green, int blue, int alpha) {
            if (this.accepted) {
                this.colors.set(this.colors.size() - 1, alpha << 24 | red << 16 | green << 8 | blue);
            }
            return this;
        }

        @NotNull
        public VertexConsumer color(int color) {
            if (this.accepted) {
                this.colors.set(this.colors.size() - 1, color);
            }
            return this;
        }

        @NotNull
        public VertexConsumer texture(float u, float v) {
            if (this.accepted) {
                this.uvs.set(this.uvs.size() - 2, u);
                this.uvs.set(this.uvs.size() - 1, v);
            }
            return this;
        }

        @NotNull
        public VertexConsumer overlay(int u, int v) {
            if (this.accepted) {
                this.overlays.set(this.overlays.size() - 1, u & 0xFFFF | (v & 0xFFFF) << 16);
            }
            return this;
        }

        @NotNull
        public VertexConsumer light(int u, int v) {
            if (this.accepted) {
                this.lights.set(this.lights.size() - 1, u & 0xFFFF | (v & 0xFFFF) << 16);
            }
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
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0013\u001a\u0004\b\u0016\u0010\u0015R\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015R\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\b\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0018\u001a\u0004\b\u001b\u0010\u001aR\u0017\u0010\t\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0018\u001a\u0004\b\u001c\u0010\u001aR\u0017\u0010\n\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\n\u0010\u0018\u001a\u0004\b\u001d\u0010\u001aR\u0017\u0010\f\u001a\u00020\u000b8\u0006\u00a2\u0006\f\n\u0004\b\f\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\r\u001a\u00020\u000b8\u0006\u00a2\u0006\f\n\u0004\b\r\u0010\u001e\u001a\u0004\b!\u0010 R\u0017\u0010\u000e\u001a\u00020\u000b8\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010\u001e\u001a\u0004\b\"\u0010 R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006\u00a2\u0006\f\n\u0004\b\u0010\u0010#\u001a\u0004\b$\u0010%R\u0011\u0010)\u001a\u00020&8F\u00a2\u0006\u0006\u001a\u0004\b'\u0010(\u00a8\u0006*"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$EchoBox;", "", "", "cx", "cy", "cz", "", "axes", "half", "verts", "uvs", "", "colors", "lights", "overlays", "Lnet/minecraft/Identifier;", "texture", "<init>", "(FFF[F[F[F[F[I[I[ILnet/minecraft/Identifier;)V", "F", "getCx", "()F", "getCy", "getCz", "[F", "getAxes", "()[F", "getHalf", "getVerts", "getUvs", "[I", "getColors", "()[I", "getLights", "getOverlays", "Lnet/minecraft/Identifier;", "getTexture", "()Lnet/minecraft/Identifier;", "", "getQuadCount", "()I", "quadCount", "rtx.kimiko:kimiko"})
    public static final class EchoBox {
        private final float cx;
        private final float cy;
        private final float cz;
        @NotNull
        private final float[] axes;
        @NotNull
        private final float[] half;
        @NotNull
        private final float[] verts;
        @NotNull
        private final float[] uvs;
        @NotNull
        private final int[] colors;
        @NotNull
        private final int[] lights;
        @NotNull
        private final int[] overlays;
        @NotNull
        private final Identifier texture;

        public EchoBox(float cx, float cy, float cz, @NotNull float[] axes, @NotNull float[] half, @NotNull float[] verts, @NotNull float[] uvs, @NotNull int[] colors, @NotNull int[] lights, @NotNull int[] overlays, @NotNull Identifier texture) {
            Intrinsics.checkNotNullParameter((Object)axes, (String)"axes");
            Intrinsics.checkNotNullParameter((Object)half, (String)"half");
            Intrinsics.checkNotNullParameter((Object)verts, (String)"verts");
            Intrinsics.checkNotNullParameter((Object)uvs, (String)"uvs");
            Intrinsics.checkNotNullParameter((Object)colors, (String)"colors");
            Intrinsics.checkNotNullParameter((Object)lights, (String)"lights");
            Intrinsics.checkNotNullParameter((Object)overlays, (String)"overlays");
            Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
            this.cx = cx;
            this.cy = cy;
            this.cz = cz;
            this.axes = axes;
            this.half = half;
            this.verts = verts;
            this.uvs = uvs;
            this.colors = colors;
            this.lights = lights;
            this.overlays = overlays;
            this.texture = texture;
        }

        public final float getCx() {
            return this.cx;
        }

        public final float getCy() {
            return this.cy;
        }

        public final float getCz() {
            return this.cz;
        }

        @NotNull
        public final float[] getAxes() {
            return this.axes;
        }

        @NotNull
        public final float[] getHalf() {
            return this.half;
        }

        @NotNull
        public final float[] getVerts() {
            return this.verts;
        }

        @NotNull
        public final float[] getUvs() {
            return this.uvs;
        }

        @NotNull
        public final int[] getColors() {
            return this.colors;
        }

        @NotNull
        public final int[] getLights() {
            return this.lights;
        }

        @NotNull
        public final int[] getOverlays() {
            return this.overlays;
        }

        @NotNull
        public final Identifier getTexture() {
            return this.texture;
        }

        public final int getQuadCount() {
            return this.colors.length;
        }
    }
}

