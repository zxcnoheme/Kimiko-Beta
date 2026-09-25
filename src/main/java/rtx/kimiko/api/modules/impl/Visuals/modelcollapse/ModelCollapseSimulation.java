/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.textures.GpuTexture
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.comparisons.ComparisonsKt
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.render.RenderLayers
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.world.BlockView
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos.Mutable
 *  net.minecraft.util.math.Box
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.shape.VoxelShape
 *  net.minecraft.block.BlockState
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.render.OverlayTexture
 *  net.minecraft.client.world.ClientWorld
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Vector3f
 */
package rtx.kimiko.api.modules.impl.Visuals.modelcollapse;

import com.mojang.blaze3d.textures.GpuTexture;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.world.BlockView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.impl.Visuals.modelcollapse.ModelCollapseSnapshots;
import rtx.kimiko.api.modules.impl.Visuals.trails.TrailEchoCapture;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00c2\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u008e\u00012\u00020\u0001:\n\u008f\u0001\u0090\u0001\u0091\u0001\u0092\u0001\u008e\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\u0003J\r\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bJ%\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0012\u0010\u0013J%\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\t\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\t\u00a2\u0006\u0004\b\u001b\u0010\u001cJm\u0010)\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\t2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b)\u0010*J-\u00100\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020\t2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020.0-H\u0002\u00a2\u0006\u0004\b0\u00101J\u001d\u00104\u001a\u00020\u00072\u0006\u00103\u001a\u0002022\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b4\u00105J?\u0010?\u001a\u00020\u00072\u0006\u00107\u001a\u0002062\u0006\u00109\u001a\u0002082\u0006\u0010:\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020 2\u0006\u0010>\u001a\u00020=H\u0002\u00a2\u0006\u0004\b?\u0010@J7\u0010E\u001a\u00020\u00042\u0006\u00107\u001a\u0002062\u0006\u0010A\u001a\u00020 2\u0006\u0010B\u001a\u00020 2\u0006\u0010C\u001a\u00020 2\u0006\u0010D\u001a\u00020 H\u0002\u00a2\u0006\u0004\bE\u0010FJ\u001f\u0010H\u001a\u00020\u00072\u0006\u00103\u001a\u0002022\u0006\u0010G\u001a\u00020=H\u0002\u00a2\u0006\u0004\bH\u0010IJ_\u0010U\u001a\u00020\u00042\u0006\u0010K\u001a\u00020J2\u0006\u0010M\u001a\u00020L2\u0006\u0010N\u001a\u00020.2\u0006\u00109\u001a\u0002082\u0006\u0010O\u001a\u00020 2\u0006\u0010P\u001a\u00020 2\u0006\u0010Q\u001a\u00020 2\u0006\u0010R\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020\u00042\u0006\u0010T\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bU\u0010VJo\u0010^\u001a\u00020\u00072\u0006\u0010K\u001a\u00020J2\u0006\u0010X\u001a\u00020W2\u0006\u0010N\u001a\u00020.2\u0006\u00109\u001a\u0002082\u0006\u0010Y\u001a\u00020\t2\u0006\u0010O\u001a\u00020 2\u0006\u0010P\u001a\u00020 2\u0006\u0010Q\u001a\u00020 2\u0006\u0010Z\u001a\u00020\t2\u0006\u0010[\u001a\u00020\u000e2\u0006\u0010\\\u001a\u00020\u000e2\u0006\u0010]\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b^\u0010_JO\u0010c\u001a\u00020\u00072\u0006\u0010K\u001a\u00020J2\u0006\u0010M\u001a\u00020L2\u0006\u0010a\u001a\u00020`2\u0006\u0010O\u001a\u00020 2\u0006\u0010P\u001a\u00020 2\u0006\u0010Q\u001a\u00020 2\u0006\u0010b\u001a\u00020\u000e2\u0006\u0010T\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bc\u0010dJ\u001f\u0010f\u001a\u00020\t2\u0006\u0010+\u001a\u00020\u001e2\u0006\u0010e\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\bf\u0010gJ\u001f\u0010h\u001a\u00020\t2\u0006\u0010D\u001a\u00020\u000e2\u0006\u0010e\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\bh\u0010iJ\u0017\u0010m\u001a\u00020l2\u0006\u0010k\u001a\u00020jH\u0002\u00a2\u0006\u0004\bm\u0010nJ\u001f\u0010q\u001a\u00020\u000e2\u0006\u0010o\u001a\u00020\u000e2\u0006\u0010p\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\bq\u0010rJ\u008d\u0001\u0010w\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020\t2\u0006\u0010s\u001a\u00020\u000e2\u0006\u0010t\u001a\u00020\u000e2\u0006\u0010e\u001a\u00020\u000e2\u0006\u0010u\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\u000e2\u0006\u0010v\u001a\u00020\u000e2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020`0-H\u0002\u00a2\u0006\u0004\bw\u0010xJ7\u0010}\u001a\u00020\t2\u0006\u0010+\u001a\u00020\u001e2\u0006\u0010y\u001a\u00020\u000e2\u0006\u0010z\u001a\u00020\u000e2\u0006\u0010{\u001a\u00020\u000e2\u0006\u0010|\u001a\u00020lH\u0002\u00a2\u0006\u0004\b}\u0010~J&\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\f\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0002\u00a2\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001R)\u0010\u0084\u0001\u001a\u0014\u0012\u0004\u0012\u0002080\u0082\u0001j\t\u0012\u0004\u0012\u000208`\u0083\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0018\u0010\u0087\u0001\u001a\u00030\u0086\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0087\u0001\u0010\u0088\u0001R\u0018\u0010\u008a\u0001\u001a\u00030\u0089\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0019\u0010\u008c\u0001\u001a\u00020=8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008c\u0001\u0010\u008d\u0001\u00a8\u0006\u0093\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation;", "", "<init>", "()V", "", "isIdle", "()Z", "", "clear", "", "totalCubes", "()I", "Lnet/minecraft/LivingEntity;", "entity", "", "partialTick", "Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$Settings;", "settings", "spawn", "(Lnet/minecraft/LivingEntity;FLrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$Settings;)Z", "entityId", "Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSnapshots$Snapshot;", "snapshot", "spawnFrozen", "(ILrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSnapshots$Snapshot;Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$Settings;)Z", "release", "(I)Z", "dropFrozen", "(I)V", "", "Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$EchoBox;", "captured", "", "originX", "originY", "originZ", "centerY", "motionX", "motionY", "motionZ", "frozen", "buildPile", "(ILjava/util/List;DDDFFFFLrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$Settings;Z)Z", "box", "texIndex", "", "Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$ShellQuad;", "out", "appendShell", "(Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$EchoBox;ILjava/util/List;)V", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "renderAndStep", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$Settings;)V", "Lnet/minecraft/ClientWorld;", "level", "Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$Pile;", "pile", "h", "restitution", "voidY", "", "ageMs", "stepPile", "(Lnet/minecraft/ClientWorld;Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$Pile;FFDJ)V", "x", "y", "z", "half", "collides", "(Lnet/minecraft/ClientWorld;DDDD)Z", "now", "render", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;J)V", "Lnet/minecraft/VertexConsumer;", "consumer", "Lnet/minecraft/MatrixStack$Entry;", "pose", "quad", "camX", "camY", "camZ", "frontY", "keepAbove", "useOverlay", "emitShell", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$ShellQuad;Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$Pile;DDDFZZ)Z", "Lorg/joml/Matrix4f;", "matrix", "index", "overlay", "normalX", "normalY", "normalZ", "emitShellVertex", "(Lnet/minecraft/VertexConsumer;Lorg/joml/Matrix4f;Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$ShellQuad;Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$Pile;IDDDIFFF)V", "Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$CollapseCube;", "cube", "scale", "emitCube", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$CollapseCube;DDDFZ)V", "cubeSize", "surfaceCellCount", "(Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$EchoBox;F)I", "gridCount", "(FF)I", "Lnet/minecraft/Identifier;", "texture", "", "textureSize", "(Lnet/minecraft/Identifier;)[F", "value", "size", "snapToTexel", "(FF)F", "texWidth", "texHeight", "keepChance", "impulse", "voxelizeBox", "(Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$EchoBox;IFFFFDDDFFFFFLjava/util/List;)V", "px", "py", "pz", "outUv", "sampleUv", "(Lrtx/kimiko/api/modules/impl/Visuals/trails/TrailEchoCapture$EchoBox;FFF[F)I", "boxes", "dropSameTextureOverlays", "(Ljava/util/List;)Ljava/util/List;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "piles", "Ljava/util/ArrayList;", "Ljava/util/Random;", "random", "Ljava/util/Random;", "Lnet/minecraft/BlockPos$Mutable;", "blockPos", "Lnet/minecraft/BlockPos$Mutable;", "lastFrameNs", "J", "Companion", "Settings", "Pile", "ShellQuad", "CollapseCube", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nModelCollapseSimulation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ModelCollapseSimulation.kt\nrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,951:1\n1174#2,2:952\n*S KotlinDebug\n*F\n+ 1 ModelCollapseSimulation.kt\nrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation\n*L\n156#1:952,2\n*E\n"})
public final class ModelCollapseSimulation {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ArrayList<Pile> piles = new ArrayList();
    @NotNull
    private final Random random = new Random();
    @NotNull
    private final BlockPos.Mutable blockPos = new BlockPos.Mutable();
    private long lastFrameNs;
    private static final int MAX_PILES = 8;
    private static final int MAX_CUBES_PER_PILE = 2800;
    private static final int MAX_CUBES_TOTAL = 6500;
    private static final float MAX_STEP = 0.016666668f;
    private static final float GRAVITY = 12.5f;
    private static final float AIR_DRAG = 0.85f;
    private static final float GROUND_FRICTION = 0.62f;
    private static final float MIN_BOUNCE = 0.55f;
    private static final float REST_SPEED_SQ = 0.09f;
    private static final long SHRINK_MS = 600L;
    private static final long HURT_OVERLAY_MS = 450L;
    @NotNull
    private static final float[] UV_SCRATCH = new float[2];
    @NotNull
    private static final float[] CLIP = new float[30];
    @NotNull
    private static final Vector3f POSITION_SCRATCH = new Vector3f();
    @NotNull
    private static final Vector3f NORMAL_SCRATCH = new Vector3f();
    @NotNull
    private static final float[] CORNERS_X = new float[8];
    @NotNull
    private static final float[] CORNERS_Y = new float[8];
    @NotNull
    private static final float[] CORNERS_Z = new float[8];
    @NotNull
    private static final int[] FACES;

    public final boolean isIdle() {
        return this.piles.isEmpty();
    }

    public final void clear() {
        this.piles.clear();
        this.lastFrameNs = 0L;
    }

    public final int totalCubes() {
        int total = 0;
        Iterator<Pile> iterator = this.piles.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Pile> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Pile pile = (Pile) (iterator2.next());
            total += pile.getCubes().size();
        }
        return total;
    }

    public final boolean spawn(@NotNull LivingEntity entity, float partialTick, @NotNull Settings settings) {
        Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
        Intrinsics.checkNotNullParameter((Object)settings, (String)"settings");
        ModelCollapseSnapshots.Snapshot snapshot = ModelCollapseSnapshots.INSTANCE.take(entity.getId());
        boolean gone = entity.isRemoved() || !entity.isAlive();
        double originX = snapshot != null && gone ? snapshot.getX() : MathHelper.lerp((double)partialTick, (double)entity.lastRenderX, (double)entity.getX());
        double originY = snapshot != null && gone ? snapshot.getY() : MathHelper.lerp((double)partialTick, (double)entity.lastRenderY, (double)entity.getY());
        double originZ = snapshot != null && gone ? snapshot.getZ() : MathHelper.lerp((double)partialTick, (double)entity.lastRenderZ, (double)entity.getZ());
        int n = entity.getId();
        Object object = snapshot;
        if (object == null || (object = ((ModelCollapseSnapshots.Snapshot)object).getBoxes()) == null) {
            object = TrailEchoCapture.capture(entity, partialTick, false);
        }
        ModelCollapseSnapshots.Snapshot snapshot2 = snapshot;
        ModelCollapseSnapshots.Snapshot snapshot3 = snapshot;
        ModelCollapseSnapshots.Snapshot snapshot4 = snapshot;
        ModelCollapseSnapshots.Snapshot snapshot5 = snapshot;
        return this.buildPile(n, (List<TrailEchoCapture.EchoBox>)object, originX, originY, originZ, (snapshot2 != null ? snapshot2.getHeight() : entity.getHeight()) * 0.5f, snapshot3 != null ? snapshot3.getMotionX() : (float)entity.getVelocity().x, snapshot4 != null ? snapshot4.getMotionY() : (float)entity.getVelocity().y, snapshot5 != null ? snapshot5.getMotionZ() : (float)entity.getVelocity().z, settings, false);
    }

    public final boolean spawnFrozen(int entityId, @NotNull ModelCollapseSnapshots.Snapshot snapshot, @NotNull Settings settings) {
        Intrinsics.checkNotNullParameter((Object)snapshot, (String)"snapshot");
        Intrinsics.checkNotNullParameter((Object)settings, (String)"settings");
        return this.buildPile(entityId, snapshot.getBoxes(), snapshot.getX(), snapshot.getY(), snapshot.getZ(), snapshot.getHeight() * 0.5f, snapshot.getMotionX(), snapshot.getMotionY(), snapshot.getMotionZ(), settings, true);
    }

    public final boolean release(int entityId) {
        Iterator<Pile> iterator = this.piles.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Pile> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Pile pile = (Pile) (iterator2.next());
            if (pile.getEntityId() != entityId || !pile.getFrozen()) continue;
            pile.setFrozen(false);
            pile.setSpawnMs(System.currentTimeMillis());
            return true;
        }
        return false;
    }

    public final void dropFrozen(int entityId) {
        this.piles.removeIf(p -> p.getEntityId() == entityId && p.getFrozen());
    }

    private final boolean buildPile(int entityId, List<TrailEchoCapture.EchoBox> captured, double originX, double originY, double originZ, float centerY, float motionX, float motionY, float motionZ, Settings settings, boolean frozen) {
        if (this.piles.size() >= 8) {
            return false;
        }
        List<TrailEchoCapture.EchoBox> boxes = this.dropSameTextureOverlays(captured);
        if (boxes.isEmpty()) {
            return false;
        }
        float cubeSize = MathHelper.clamp((float)settings.getCubeSize(), (float)0.02f, (float)0.25f);
        int prospective = 0;
        for (TrailEchoCapture.EchoBox box : boxes) {
            prospective += this.surfaceCellCount(box, cubeSize);
        }
        if (prospective <= 0) {
            return false;
        }
        int budget = Math.min(2800, 6500 - this.totalCubes());
        if (budget < 40) {
            return false;
        }
        float keepChance = Math.min(1.0f, (float)budget / (float)prospective);
        float impulse = Math.max(0.0f, settings.getImpulse());
        ArrayList<Identifier> textures = new ArrayList<Identifier>();
        ArrayList<float[]> texSizes = new ArrayList<float[]>();
        ArrayList shell = new ArrayList();
        for (TrailEchoCapture.EchoBox echoBox : boxes) {
            int texIndex = textures.indexOf(echoBox.getTexture());
            if (texIndex < 0) {
                texIndex = textures.size();
                textures.add(echoBox.getTexture());
                texSizes.add(this.textureSize(echoBox.getTexture()));
            }
            this.appendShell(echoBox, texIndex, shell);
        }
        ArrayList cubes = new ArrayList(Math.min(budget, prospective));
        for (TrailEchoCapture.EchoBox box : boxes) {
            int texIndex = textures.indexOf(box.getTexture());
            if (texIndex < 0) continue;
            Object e = texSizes.get(texIndex);
            Intrinsics.checkNotNullExpressionValue(e, (String)"get(...)");
            float[] texSize = (float[])e;
            this.voxelizeBox(box, texIndex, texSize[0], texSize[1], cubeSize, keepChance, originX, originY, originZ, centerY, motionX, motionY, motionZ, impulse, cubes);
            if (cubes.size() < budget) continue;
        }
        if (cubes.isEmpty()) {
            return false;
        }
        while (cubes.size() > budget) {
            cubes.remove(cubes.size() - 1);
        }
        cubes.sort(java.util.Comparator.comparingInt(CollapseCube::getTexIndex));
        float f = Float.MAX_VALUE;
        float shellMaxY = -3.4028235E38f;
        Iterator iterator = shell.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator texIndex = iterator;
        while (texIndex.hasNext()) {
            Object e = texIndex.next();
            Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
            ShellQuad quad = (ShellQuad)e;
            f = Math.min(f, quad.getMinY());
            shellMaxY = Math.max(shellMaxY, quad.getMaxY());
        }
        if (shell.isEmpty()) {
            Iterator iterator2 = cubes.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator2, (String)"iterator(...)");
            texIndex = iterator2;
            while (texIndex.hasNext()) {
                Object e = texIndex.next();
                Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
                CollapseCube cube = (CollapseCube)e;
                float local = (float)(cube.getY() - originY);
                f = Math.min(f, local);
                shellMaxY = Math.max(shellMaxY, local);
            }
        }
        float span = Math.max(1.0E-4f, shellMaxY - f);
        long waveMs = MathHelper.clamp((long)settings.getWaveMs(), (long)0L, (long)3000L);
        Iterator iterator3 = cubes.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator3, (String)"iterator(...)");
        Iterator iterator4 = iterator3;
        while (iterator4.hasNext()) {
            Object e = iterator4.next();
            Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
            CollapseCube cube = (CollapseCube)e;
            float frac = MathHelper.clamp((float)(((float)(cube.getY() - originY) - f) / span), (float)0.0f, (float)1.0f);
            if (settings.getTopDown()) {
                frac = 1.0f - frac;
            }
            float jitter = (this.random.nextFloat() - 0.5f) * 0.06f;
            cube.setReleaseMs(Math.round(MathHelper.clamp((float)(frac + jitter), (float)0.0f, (float)1.0f) * (float)waveMs));
        }
        Pile pile = new Pile(entityId, System.currentTimeMillis(), Math.max(500L, settings.getLifeMs()), originX, originY, originZ, (List<Identifier>)textures, cubes, shell, f, shellMaxY, waveMs, settings.getTopDown());
        pile.setFrozen(frozen);
        this.piles.add(pile);
        return true;
    }

    private final void appendShell(TrailEchoCapture.EchoBox box, int texIndex, List<ShellQuad> out) {
        int n = box.getQuadCount();
        for (int q = 0; q < n; ++q) {
            int o = q * 12;
            float[] pos = new float[12];
            for (int i = 0; i < 4; ++i) {
                pos[i * 3] = box.getCx() + box.getVerts()[o + i * 3];
                pos[i * 3 + 1] = box.getCy() + box.getVerts()[o + i * 3 + 1];
                pos[i * 3 + 2] = box.getCz() + box.getVerts()[o + i * 3 + 2];
            }
            float[] uv = new float[8];
            for (int i = 0; i < 8; ++i) {
                uv[i] = box.getUvs()[q * 8 + i];
            }
            float e1y = pos[4] - pos[1];
            float e2z = pos[11] - pos[2];
            float e1z = pos[5] - pos[2];
            float e2y = pos[10] - pos[1];
            float nx = e1y * e2z - e1z * e2y;
            float e2x = pos[9] - pos[0];
            float e1x = pos[3] - pos[0];
            float ny = e1z * e2x - e1x * e2z;
            float nz = e1x * e2y - e1y * e2x;
            float len = (float)Math.sqrt(nx * nx + ny * ny + nz * nz);
            if (len < 1.0E-6f) {
                nx = 0.0f;
                ny = 1.0f;
                nz = 0.0f;
            } else {
                nx /= len;
                ny /= len;
                nz /= len;
            }
            out.add(new ShellQuad(texIndex, pos, uv, box.getColors()[q], box.getLights()[q], box.getOverlays()[q], nx, ny, nz));
        }
    }

    public final void renderAndStep(@NotNull WorldRenderEvent event, @NotNull Settings settings) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        Intrinsics.checkNotNullParameter((Object)settings, (String)"settings");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        ClientWorld level = minecraft.world;
        if (level == null) {
            this.clear();
            return;
        }
        if (this.piles.isEmpty()) {
            this.lastFrameNs = 0L;
            return;
        }
        long now = System.currentTimeMillis();
        long nowNs = System.nanoTime();
        float dt = this.lastFrameNs == 0L ? 0.0f : (float)((double)(nowNs - this.lastFrameNs) / 1.0E9);
        this.lastFrameNs = nowNs;
        dt = MathHelper.clamp((float)dt, (float)0.0f, (float)0.1f);
        for (int i = this.piles.size() - 1; -1 < i; --i) {
            Pile pile = (Pile) (this.piles.get(i));
            if (pile.getFrozen() || now - pile.getSpawnMs() <= pile.getLifeMs() && !pile.getCubes().isEmpty()) continue;
            this.piles.remove(i);
        }
        if (this.piles.isEmpty()) {
            return;
        }
        if (dt > 0.0f) {
            int steps = MathHelper.clamp((int)((int)Math.ceil(dt / 0.016666668f)), (int)1, (int)4);
            float h = dt / (float)steps;
            float restitution = MathHelper.clamp((float)settings.getRestitution(), (float)0.0f, (float)0.9f);
            double voidY = (double)level.getBottomY() - 24.0;
            Iterator<Pile> iterator = this.piles.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
            Iterator<Pile> iterator2 = iterator;
            while (iterator2.hasNext()) {
                Pile pile = (Pile) (iterator2.next());
                if (pile.getFrozen()) continue;
                long age = now - pile.getSpawnMs();
                for (int step = 0; step < steps; ++step) {
                    this.stepPile(level, pile, h, restitution, voidY, age);
                }
                pile.getCubes().removeIf(CollapseCube::getDead);
            }
        }
        this.render(event, now);
    }

    private final void stepPile(ClientWorld level, Pile pile, float h, float restitution, double voidY, long ageMs) {
        for (CollapseCube cube : pile.getCubes()) {
            if (cube.getSleeping() || cube.getDead()) continue;
            if (!cube.getReleased()) {
                if (ageMs < cube.getReleaseMs()) continue;
                cube.setReleased(true);
                for (int escape = 0; escape < 8 && this.collides(level, cube.getX(), cube.getY(), cube.getZ(), cube.getHalf()); ++escape) {
                    cube.setY(cube.getY() + 0.06);
                }
            }
            cube.setVy(cube.getVy() - 12.5f * h);
            float drag = 1.0f - 0.85f * h;
            cube.setVx(cube.getVx() * drag);
            cube.setVy(cube.getVy() * drag);
            cube.setVz(cube.getVz() * drag);
            double half = cube.getHalf();
            boolean grounded = false;
            double nx = cube.getX() + (double)(cube.getVx() * h);
            if (!(cube.getVx() == 0.0f)) {
                if (this.collides(level, nx, cube.getY(), cube.getZ(), half) && !this.collides(level, cube.getX(), cube.getY(), cube.getZ(), half)) {
                    cube.setVx(-cube.getVx() * restitution * 0.7f);
                } else {
                    cube.setX(nx);
                }
            }
            double nz = cube.getZ() + (double)(cube.getVz() * h);
            if (!(cube.getVz() == 0.0f)) {
                if (this.collides(level, cube.getX(), cube.getY(), nz, half) && !this.collides(level, cube.getX(), cube.getY(), cube.getZ(), half)) {
                    cube.setVz(-cube.getVz() * restitution * 0.7f);
                } else {
                    cube.setZ(nz);
                }
            }
            double ny = cube.getY() + (double)(cube.getVy() * h);
            if (this.collides(level, cube.getX(), ny, cube.getZ(), half) && !this.collides(level, cube.getX(), cube.getY(), cube.getZ(), half)) {
                if (cube.getVy() < 0.0f) {
                    grounded = true;
                    float bounced = -cube.getVy() * restitution;
                    cube.setVy(bounced > 0.55f ? bounced : 0.0f);
                    cube.setVx(cube.getVx() * 0.62f);
                    cube.setVz(cube.getVz() * 0.62f);
                    cube.setAngVel(cube.getAngVel() * 0.55f);
                } else {
                    cube.setVy(-cube.getVy() * 0.25f);
                }
            } else {
                cube.setY(ny);
            }
            cube.setAngle(cube.getAngle() + cube.getAngVel() * h);
            float speedSq = cube.getVx() * cube.getVx() + cube.getVy() * cube.getVy() + cube.getVz() * cube.getVz();
            if (grounded && speedSq < 0.09f) {
                int n = cube.getRestSteps();
                cube.setRestSteps(n + 1);
                if (cube.getRestSteps() > 6) {
                    cube.setSleeping(true);
                    cube.setAngVel(0.0f);
                    cube.setVx(0.0f);
                    cube.setVy(0.0f);
                    cube.setVz(0.0f);
                }
            } else {
                cube.setRestSteps(0);
            }
            if (!(cube.getY() < voidY)) continue;
            cube.setDead(true);
        }
    }

    private final boolean collides(ClientWorld level, double x, double y, double z, double half) {
        double minX = x - half;
        double minY = y - half;
        double minZ = z - half;
        double maxX = x + half;
        double maxY = y + half;
        double maxZ = z + half;
        int x0 = MathHelper.floor((double)minX);
        int x1 = MathHelper.floor((double)maxX);
        int y0 = MathHelper.floor((double)minY);
        int y1 = MathHelper.floor((double)maxY);
        int z0 = MathHelper.floor((double)minZ);
        int z1 = MathHelper.floor((double)maxZ);
        int bx = x0;
        if (bx <= x1) {
            while (true) {
                int by;
                if ((by = y0) <= y1) {
                    while (true) {
                        int bz;
                        if ((bz = z0) <= z1) {
                            while (true) {
                                this.blockPos.set(bx, by, bz);
                                BlockState state = level.getBlockState((BlockPos)this.blockPos);
                                if (!state.isAir()) {
                                    VoxelShape shape = (VoxelShape) (state.getCollisionShape((BlockView)level, (BlockPos)this.blockPos));
                                    if (!shape.isEmpty()) {
                                        Box bounds = (Box) (shape.getBoundingBox());
                                        if (minX < (double)bx + bounds.maxX && maxX > (double)bx + bounds.minX && minY < (double)by + bounds.maxY && maxY > (double)by + bounds.minY && minZ < (double)bz + bounds.maxZ && maxZ > (double)bz + bounds.minZ) {
                                            return true;
                                        }
                                    }
                                }
                                if (bz == z1) break;
                                ++bz;
                            }
                        }
                        if (by == y1) break;
                        ++by;
                    }
                }
                if (bx == x1) break;
                ++bx;
            }
        }
        return false;
    }

    private final void render(WorldRenderEvent event, long now) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        Vec3d vec3d2 = event.getCamera().getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cam = vec3d2;
        MatrixStack.Entry entry2 = event.getStack().peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        VertexConsumerProvider.Immediate immediate2 = minecraft.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        Iterator<Pile> iterator = this.piles.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Pile> iterator2 = iterator;
        while (iterator2.hasNext()) {
            boolean keepAbove;
            float scale;
            float f;
            Pile pile = (Pile) (iterator2.next());
            long age = pile.getFrozen() ? 0L : now - pile.getSpawnMs();
            long shrinkStart = pile.getLifeMs() - 600L;
            if (age <= shrinkStart) {
                f = 1.0f;
            } else {
                float t = MathHelper.clamp((float)((float)(age - shrinkStart) / 600.0f), (float)0.0f, (float)1.0f);
                f = 1.0f - t * t * (3.0f - 2.0f * t);
            }
            if ((scale = f) <= 0.02f) continue;
            boolean useOverlay = now - pile.getCreatedMs() < 450L;
            float span = Math.max(1.0E-4f, pile.getShellMaxY() - pile.getShellMinY());
            float progress = pile.getFrozen() ? 0.0f : (pile.getWaveMs() <= 0L ? 1.0f : MathHelper.clamp((float)((float)age / (float)pile.getWaveMs()), (float)0.0f, (float)1.0f));
            float frontLocal = pile.getTopDown() ? pile.getShellMaxY() - progress * span : pile.getShellMinY() + progress * span;
            double frontWorld = pile.getOriginY() + (double)frontLocal;
            boolean bl = keepAbove = !pile.getTopDown();
            boolean shellAlive = progress < 1.0f && !((Collection)pile.getShell()).isEmpty();
            int n = ((Collection)pile.getTextures()).size();
            for (int texIndex = 0; texIndex < n; ++texIndex) {
                RenderLayer renderType = (RenderLayer) (RenderLayers.entityCutoutNoCull((Identifier)pile.getTextures().get(texIndex)));
                VertexConsumer consumer = provider.getBuffer(renderType);
                boolean drew = false;
                if (shellAlive) {
                    for (ShellQuad quad : pile.getShell()) {
                        if (quad.getTexIndex() != texIndex || (!keepAbove ? quad.getMinY() > frontLocal : quad.getMaxY() < frontLocal) || !this.emitShell(consumer, pose, quad, pile, cam.x, cam.y, cam.z, frontLocal, keepAbove, useOverlay)) continue;
                        drew = true;
                    }
                }
                for (CollapseCube cube : pile.getCubes()) {
                    if (cube.getTexIndex() != texIndex || cube.getDead()) continue;
                    if (!cube.getReleased() && shellAlive) {
                        boolean shattered = keepAbove ? cube.getY() <= frontWorld : cube.getY() >= frontWorld;
                        if (!shattered) continue;
                    }
                    this.emitCube(consumer, pose, cube, cam.x, cam.y, cam.z, scale, useOverlay);
                    drew = true;
                }
                if (!drew) continue;
                provider.draw(renderType);
            }
        }
    }

    private final boolean emitShell(VertexConsumer consumer, MatrixStack.Entry pose, ShellQuad quad, Pile pile, double camX, double camY, double camZ, float frontY, boolean keepAbove, boolean useOverlay) {
        int count = 0;
        for (int i = 0; i < 4; ++i) {
            int j = i + 1 & 3;
            float xi = quad.getPos()[i * 3];
            float yi = quad.getPos()[i * 3 + 1];
            float zi = quad.getPos()[i * 3 + 2];
            float xj = quad.getPos()[j * 3];
            float yj = quad.getPos()[j * 3 + 1];
            float zj = quad.getPos()[j * 3 + 2];
            float ui = quad.getUv()[i * 2];
            float vi = quad.getUv()[i * 2 + 1];
            float uj = quad.getUv()[j * 2];
            float vj = quad.getUv()[j * 2 + 1];
            boolean insideI = keepAbove ? yi >= frontY : yi <= frontY;
            boolean insideJ = keepAbove ? yj >= frontY : yj <= frontY;
            if (insideI) {
                int b = count * 5;
                ModelCollapseSimulation.CLIP[b] = xi;
                ModelCollapseSimulation.CLIP[b + 1] = yi;
                ModelCollapseSimulation.CLIP[b + 2] = zi;
                ModelCollapseSimulation.CLIP[b + 3] = ui;
                ModelCollapseSimulation.CLIP[b + 4] = vi;
                ++count;
            }
            if (insideI == insideJ) continue;
            float denom = yj - yi;
            float t = Math.abs(denom) < 1.0E-6f ? 0.0f : MathHelper.clamp((float)((frontY - yi) / denom), (float)0.0f, (float)1.0f);
            int b = count * 5;
            ModelCollapseSimulation.CLIP[b] = xi + (xj - xi) * t;
            ModelCollapseSimulation.CLIP[b + 1] = yi + (yj - yi) * t;
            ModelCollapseSimulation.CLIP[b + 2] = zi + (zj - zi) * t;
            ModelCollapseSimulation.CLIP[b + 3] = ui + (uj - ui) * t;
            ModelCollapseSimulation.CLIP[b + 4] = vi + (vj - vi) * t;
            ++count;
        }
        if (count < 3) {
            return false;
        }
        int overlay = useOverlay ? quad.getOverlay() : OverlayTexture.DEFAULT_UV;
        Matrix4f matrix4f = pose.getPositionMatrix();
        Intrinsics.checkNotNullExpressionValue((Object)matrix4f, (String)"pose(...)");
        Matrix4f matrix = matrix4f;
        pose.transformNormal(quad.getNx(), quad.getNy(), quad.getNz(), NORMAL_SCRATCH);
        float tnx = ModelCollapseSimulation.NORMAL_SCRATCH.x;
        float tny = ModelCollapseSimulation.NORMAL_SCRATCH.y;
        float tnz = ModelCollapseSimulation.NORMAL_SCRATCH.z;
        int n = count - 1;
        for (int k = 1; k < n; ++k) {
            this.emitShellVertex(consumer, matrix, quad, pile, 0, camX, camY, camZ, overlay, tnx, tny, tnz);
            this.emitShellVertex(consumer, matrix, quad, pile, k, camX, camY, camZ, overlay, tnx, tny, tnz);
            this.emitShellVertex(consumer, matrix, quad, pile, k + 1, camX, camY, camZ, overlay, tnx, tny, tnz);
            this.emitShellVertex(consumer, matrix, quad, pile, k + 1, camX, camY, camZ, overlay, tnx, tny, tnz);
        }
        return true;
    }

    private final void emitShellVertex(VertexConsumer consumer, Matrix4f matrix, ShellQuad quad, Pile pile, int index, double camX, double camY, double camZ, int overlay, float normalX, float normalY, float normalZ) {
        int b = index * 5;
        matrix.transformPosition((float)(pile.getOriginX() + (double)CLIP[b] - camX), (float)(pile.getOriginY() + (double)CLIP[b + 1] - camY), (float)(pile.getOriginZ() + (double)CLIP[b + 2] - camZ), POSITION_SCRATCH);
        consumer.vertex(ModelCollapseSimulation.POSITION_SCRATCH.x, ModelCollapseSimulation.POSITION_SCRATCH.y, ModelCollapseSimulation.POSITION_SCRATCH.z).color(quad.getColor()).texture(CLIP[b + 3], CLIP[b + 4]).overlay(overlay).light(quad.getLight()).normal(normalX, normalY, normalZ);
    }

    private final void emitCube(VertexConsumer consumer, MatrixStack.Entry pose, CollapseCube cube, double camX, double camY, double camZ, float scale, boolean useOverlay) {
        float half = cube.getHalf() * scale;
        if (half < 0.003f) {
            return;
        }
        float c = (float)Math.cos(cube.getAngle());
        float s = (float)Math.sin(cube.getAngle());
        float t = 1.0f - c;
        float ax = cube.getAxX();
        float ay = cube.getAxY();
        float az = cube.getAxZ();
        float m00 = c + ax * ax * t;
        float m01 = ax * ay * t - az * s;
        float m02 = ax * az * t + ay * s;
        float m10 = ay * ax * t + az * s;
        float m11 = c + ay * ay * t;
        float m12 = ay * az * t - ax * s;
        float m20 = az * ax * t - ay * s;
        float m21 = az * ay * t + ax * s;
        float m22 = c + az * az * t;
        float bx = (float)(cube.getX() - camX);
        float by = (float)(cube.getY() - camY);
        float bz = (float)(cube.getZ() - camZ);
        Matrix4f matrix4f = pose.getPositionMatrix();
        Intrinsics.checkNotNullExpressionValue((Object)matrix4f, (String)"pose(...)");
        Matrix4f matrix = matrix4f;
        for (int i = 0; i < 8; ++i) {
            float lx = (i & 1) != 0 ? half : -half;
            float ly = (i & 2) != 0 ? half : -half;
            float lz = (i & 4) != 0 ? half : -half;
            matrix.transformPosition(bx + m00 * lx + m01 * ly + m02 * lz, by + m10 * lx + m11 * ly + m12 * lz, bz + m20 * lx + m21 * ly + m22 * lz, POSITION_SCRATCH);
            ModelCollapseSimulation.CORNERS_X[i] = ModelCollapseSimulation.POSITION_SCRATCH.x;
            ModelCollapseSimulation.CORNERS_Y[i] = ModelCollapseSimulation.POSITION_SCRATCH.y;
            ModelCollapseSimulation.CORNERS_Z[i] = ModelCollapseSimulation.POSITION_SCRATCH.z;
        }
        int overlay = useOverlay ? cube.getOverlay() : OverlayTexture.DEFAULT_UV;
        for (int face = 0; face < 6; ++face) {
            int axis = face >> 1;
            float sign = (face & 1) == 0 ? 1.0f : -1.0f;
            float nx = sign * (switch (axis) {
                case 0 -> m00;
                case 1 -> m01;
                default -> m02;
            });
            float ny = sign * (switch (axis) {
                case 0 -> m10;
                case 1 -> m11;
                default -> m12;
            });
            float nz = sign * (switch (axis) {
                case 0 -> m20;
                case 1 -> m21;
                default -> m22;
            });
            pose.transformNormal(nx, ny, nz, NORMAL_SCRATCH);
            float tnx = ModelCollapseSimulation.NORMAL_SCRATCH.x;
            float tny = ModelCollapseSimulation.NORMAL_SCRATCH.y;
            float tnz = ModelCollapseSimulation.NORMAL_SCRATCH.z;
            int base = face * 4;
            for (int v = 0; v < 4; ++v) {
                int corner = FACES[base + v];
                consumer.vertex(CORNERS_X[corner], CORNERS_Y[corner], CORNERS_Z[corner]).color(cube.getColor()).texture(cube.getU(), cube.getV()).overlay(overlay).light(cube.getLight()).normal(tnx, tny, tnz);
            }
        }
    }

    private final int surfaceCellCount(TrailEchoCapture.EchoBox box, float cubeSize) {
        int na = this.gridCount(box.getHalf()[0], cubeSize);
        int nb = this.gridCount(box.getHalf()[1], cubeSize);
        int nn = this.gridCount(box.getHalf()[2], cubeSize);
        int total = na * nb * nn;
        if (na <= 2 || nb <= 2 || nn <= 2) {
            return total;
        }
        return total - (na - 2) * (nb - 2) * (nn - 2);
    }

    private final int gridCount(float half, float cubeSize) {
        return MathHelper.clamp((int)Math.round(half * 2.0f / cubeSize), (int)1, (int)14);
    }

    private final float[] textureSize(Identifier texture) {
        float[] fArray;
        try {
            GpuTexture gpuTexture = MinecraftClient.getInstance().getTextureManager().getTexture(texture).getGlTexture();
            Intrinsics.checkNotNullExpressionValue((Object)gpuTexture, (String)"getTexture(...)");
            GpuTexture gpu = gpuTexture;
            float[] fArray2 = new float[]{gpu.getWidth(0), gpu.getHeight(0)};
            fArray = fArray2;
        }
        catch (Throwable ignored) {
            float[] fArray3 = new float[]{0.0f, 0.0f};
            fArray = fArray3;
        }
        return fArray;
    }

    private final float snapToTexel(float value, float size) {
        if (size < 1.0f) {
            return value;
        }
        int texel = MathHelper.clamp((int)MathHelper.floor((float)(value * size)), (int)0, (int)(MathHelper.floor((float)size) - 1));
        return ((float)texel + 0.5f) / size;
    }

    private final void voxelizeBox(TrailEchoCapture.EchoBox box, int texIndex, float texWidth, float texHeight, float cubeSize, float keepChance, double originX, double originY, double originZ, float centerY, float motionX, float motionY, float motionZ, float impulse, List<CollapseCube> out) {
        float[] axes = box.getAxes();
        int na = this.gridCount(box.getHalf()[0], cubeSize);
        int nb = this.gridCount(box.getHalf()[1], cubeSize);
        int nn = this.gridCount(box.getHalf()[2], cubeSize);
        float stepA = box.getHalf()[0] * 2.0f / (float)na;
        float stepB = box.getHalf()[1] * 2.0f / (float)nb;
        float stepN = box.getHalf()[2] * 2.0f / (float)nn;
        float cellSize = Math.max(stepA, Math.max(stepB, stepN));
        for (int ia = 0; ia < na; ++ia) {
            for (int ib = 0; ib < nb; ++ib) {
                for (int ic = 0; ic < nn; ++ic) {
                    float rz;
                    float ry;
                    boolean surface;
                    boolean bl = surface = ia == 0 || ia == na - 1 || ib == 0 || ib == nb - 1 || ic == 0 || ic == nn - 1;
                    if (!surface || keepChance < 1.0f && this.random.nextFloat() > keepChance) continue;
                    float la = -box.getHalf()[0] + ((float)ia + 0.5f) * stepA;
                    float lb = -box.getHalf()[1] + ((float)ib + 0.5f) * stepB;
                    float ln = -box.getHalf()[2] + ((float)ic + 0.5f) * stepN;
                    float localX = axes[0] * la + axes[3] * lb + axes[6] * ln;
                    float localY = axes[1] * la + axes[4] * lb + axes[7] * ln;
                    float localZ = axes[2] * la + axes[5] * lb + axes[8] * ln;
                    float sa = la;
                    float sb = lb;
                    float sn = ln;
                    float da = box.getHalf()[0] - Math.abs(la);
                    float db = box.getHalf()[1] - Math.abs(lb);
                    float dn = box.getHalf()[2] - Math.abs(ln);
                    if (da <= db && da <= dn) {
                        sa = la >= 0.0f ? box.getHalf()[0] : -box.getHalf()[0];
                    } else if (db <= dn) {
                        sb = lb >= 0.0f ? box.getHalf()[1] : -box.getHalf()[1];
                    } else {
                        sn = ln >= 0.0f ? box.getHalf()[2] : -box.getHalf()[2];
                    }
                    float surfX = axes[0] * sa + axes[3] * sb + axes[6] * sn;
                    float surfY = axes[1] * sa + axes[4] * sb + axes[7] * sn;
                    float surfZ = axes[2] * sa + axes[5] * sb + axes[8] * sn;
                    int quad = this.sampleUv(box, surfX, surfY, surfZ, UV_SCRATCH);
                    double wx = originX + (double)box.getCx() + (double)localX;
                    double wy = originY + (double)box.getCy() + (double)localY;
                    double wz = originZ + (double)box.getCz() + (double)localZ;
                    float dirX = box.getCx() + localX;
                    float dirY = box.getCy() + localY - centerY;
                    float dirZ = box.getCz() + localZ;
                    float len = (float)Math.sqrt(dirX * dirX + dirY * dirY + dirZ * dirZ);
                    if (len < 1.0E-4f) {
                        dirX = this.random.nextFloat() - 0.5f;
                        dirY = this.random.nextFloat() * 0.6f;
                        dirZ = this.random.nextFloat() - 0.5f;
                        len = Math.max(1.0E-4f, (float)Math.sqrt(dirX * dirX + dirY * dirY + dirZ * dirZ));
                    }
                    float burst = impulse * (1.1f + this.random.nextFloat() * 2.1f);
                    float vx = (dirX /= len) * burst * 0.8f + motionX * 2.0f + (this.random.nextFloat() - 0.5f) * 0.5f * impulse;
                    float vy = (dirY /= len) * burst * 0.45f + impulse * (0.5f + this.random.nextFloat() * 1.3f) + (this.random.nextFloat() - 0.5f) * 0.3f;
                    float vz = (dirZ /= len) * burst * 0.8f + motionZ * 2.0f + (this.random.nextFloat() - 0.5f) * 0.5f * impulse;
                    float rx = this.random.nextFloat() * 2.0f - 1.0f;
                    float rl = (float)Math.sqrt(rx * rx + (ry = this.random.nextFloat() * 2.0f - 1.0f) * ry + (rz = this.random.nextFloat() * 2.0f - 1.0f) * rz);
                    if (rl < 1.0E-4f) {
                        rx = 0.0f;
                        ry = 1.0f;
                        rz = 0.0f;
                        rl = 1.0f;
                    }
                    out.add(new CollapseCube(wx, wy, wz, vx, vy + motionY, vz, cellSize * 0.5f * (0.98f + this.random.nextFloat() * 0.12f), this.snapToTexel(UV_SCRATCH[0], texWidth), this.snapToTexel(UV_SCRATCH[1], texHeight), box.getColors()[quad], box.getLights()[quad], box.getOverlays()[quad], texIndex, rx /= rl, ry /= rl, rz /= rl, this.random.nextFloat() * (float)Math.PI * 2.0f, (this.random.nextFloat() * 2.0f - 1.0f) * (3.0f + impulse * 5.0f)));
                }
            }
        }
    }

    private final int sampleUv(TrailEchoCapture.EchoBox box, float px, float py, float pz, float[] outUv) {
        float[] verts = box.getVerts();
        float[] uvs = box.getUvs();
        float bestDist = Float.MAX_VALUE;
        int bestQuad = 0;
        float bestS = 0.5f;
        float bestT = 0.5f;
        int n = box.getQuadCount();
        for (int q = 0; q < n; ++q) {
            float cz;
            float ddz;
            float cy;
            float ddy;
            float t;
            float s;
            float cx;
            float ddx;
            float dist;
            int o = q * 12;
            float v0x = verts[o];
            float v0y = verts[o + 1];
            float v0z = verts[o + 2];
            float e1x = verts[o + 3] - v0x;
            float e1y = verts[o + 4] - v0y;
            float e1z = verts[o + 5] - v0z;
            float e2x = verts[o + 9] - v0x;
            float e2y = verts[o + 10] - v0y;
            float e2z = verts[o + 11] - v0z;
            float dx = px - v0x;
            float dy = py - v0y;
            float dz = pz - v0z;
            float len1 = e1x * e1x + e1y * e1y + e1z * e1z;
            float len2 = e2x * e2x + e2y * e2y + e2z * e2z;
            if (len1 < 1.0E-8f || len2 < 1.0E-8f || !((dist = (ddx = px - (cx = v0x + e1x * (s = MathHelper.clamp((float)((dx * e1x + dy * e1y + dz * e1z) / len1), (float)0.0f, (float)1.0f)) + e2x * (t = MathHelper.clamp((float)((dx * e2x + dy * e2y + dz * e2z) / len2), (float)0.0f, (float)1.0f)))) * ddx + (ddy = py - (cy = v0y + e1y * s + e2y * t)) * ddy + (ddz = pz - (cz = v0z + e1z * s + e2z * t)) * ddz) < bestDist)) continue;
            bestDist = dist;
            bestQuad = q;
            bestS = s;
            bestT = t;
        }
        float u0 = uvs[bestQuad * 8];
        float v0 = uvs[bestQuad * 8 + 1];
        float u1 = uvs[bestQuad * 8 + 2];
        float v1 = uvs[bestQuad * 8 + 3];
        float u3 = uvs[bestQuad * 8 + 6];
        float v3 = uvs[bestQuad * 8 + 7];
        outUv[0] = u0 + (u1 - u0) * bestS + (u3 - u0) * bestT;
        outUv[1] = v0 + (v1 - v0) * bestS + (v3 - v0) * bestT;
        return bestQuad;
    }

    private final List<TrailEchoCapture.EchoBox> dropSameTextureOverlays(List<TrailEchoCapture.EchoBox> boxes) {
        if (boxes.size() < 2) {
            return boxes;
        }
        ArrayList<TrailEchoCapture.EchoBox> kept = new ArrayList<TrailEchoCapture.EchoBox>(boxes.size());
        int n = ((Collection)boxes).size();
        block0: for (int i = 0; i < n; ++i) {
            TrailEchoCapture.EchoBox a = boxes.get(i);
            float va = a.getHalf()[0] * a.getHalf()[1] * a.getHalf()[2];
            int n2 = ((Collection)boxes).size();
            for (int j = 0; j < n2; ++j) {
                float vb;
                float dz;
                float dy;
                float dx;
                if (i == j) continue;
                TrailEchoCapture.EchoBox b = boxes.get(j);
                if (Intrinsics.areEqual((Object)a.getTexture(), (Object)b.getTexture()) && !((dx = a.getCx() - b.getCx()) * dx + (dy = a.getCy() - b.getCy()) * dy + (dz = a.getCz() - b.getCz()) * dz > 9.0E-4f) && va > (vb = b.getHalf()[0] * b.getHalf()[1] * b.getHalf()[2]) * 1.05f) continue block0;
            }
            kept.add(a);
        }
        return kept;
    }

    private static final boolean dropFrozen$lambda$0(int $entityId, Pile pile) {
        Intrinsics.checkNotNullParameter((Object)pile, (String)"pile");
        return pile.getEntityId() == $entityId && pile.getFrozen();
    }

    private static final boolean dropFrozen$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final boolean renderAndStep$lambda$0(CollapseCube cube) {
        Intrinsics.checkNotNullParameter((Object)cube, (String)"cube");
        return cube.getDead();
    }

    private static final boolean renderAndStep$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    static {
        int[] nArray = new int[]{1, 3, 7, 5, 0, 2, 6, 4, 2, 3, 7, 6, 0, 1, 5, 4, 4, 5, 7, 6, 0, 1, 3, 2};
        FACES = nArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b-\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\t\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0097\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\r\u0012\u0006\u0010\u0012\u001a\u00020\u0006\u0012\u0006\u0010\u0013\u001a\u00020\u0006\u0012\u0006\u0010\u0014\u001a\u00020\u0006\u0012\u0006\u0010\u0015\u001a\u00020\u0006\u0012\u0006\u0010\u0016\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0017\u0010\u0018R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0003\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\"\u0010\u0004\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0004\u0010\u0019\u001a\u0004\b\u001e\u0010\u001b\"\u0004\b\u001f\u0010\u001dR\"\u0010\u0005\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0019\u001a\u0004\b \u0010\u001b\"\u0004\b!\u0010\u001dR\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0007\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\"\u0010\b\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\b\u0010\"\u001a\u0004\b'\u0010$\"\u0004\b(\u0010&R\"\u0010\t\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\t\u0010\"\u001a\u0004\b)\u0010$\"\u0004\b*\u0010&R\u0017\u0010\n\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\n\u0010\"\u001a\u0004\b+\u0010$R\u0017\u0010\u000b\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\"\u001a\u0004\b,\u0010$R\u0017\u0010\f\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\f\u0010\"\u001a\u0004\b-\u0010$R\u0017\u0010\u000e\u001a\u00020\r8\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010.\u001a\u0004\b/\u00100R\u0017\u0010\u000f\u001a\u00020\r8\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010.\u001a\u0004\b1\u00100R\u0017\u0010\u0010\u001a\u00020\r8\u0006\u00a2\u0006\f\n\u0004\b\u0010\u0010.\u001a\u0004\b2\u00100R\u0017\u0010\u0011\u001a\u00020\r8\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010.\u001a\u0004\b3\u00100R\u0017\u0010\u0012\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0012\u0010\"\u001a\u0004\b4\u0010$R\u0017\u0010\u0013\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0013\u0010\"\u001a\u0004\b5\u0010$R\u0017\u0010\u0014\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0014\u0010\"\u001a\u0004\b6\u0010$R\"\u0010\u0015\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\"\u001a\u0004\b7\u0010$\"\u0004\b8\u0010&R\"\u0010\u0016\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\"\u001a\u0004\b9\u0010$\"\u0004\b:\u0010&R\"\u0010<\u001a\u00020;8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\"\u0010B\u001a\u00020\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bB\u0010.\u001a\u0004\bC\u00100\"\u0004\bD\u0010ER\"\u0010F\u001a\u00020;8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bF\u0010=\u001a\u0004\bG\u0010?\"\u0004\bH\u0010AR\"\u0010I\u001a\u00020;8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bI\u0010=\u001a\u0004\bJ\u0010?\"\u0004\bK\u0010AR\"\u0010M\u001a\u00020L8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bM\u0010N\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010R\u00a8\u0006S"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$CollapseCube;", "", "", "x", "y", "z", "", "vx", "vy", "vz", "half", "u", "v", "", "color", "light", "overlay", "texIndex", "axX", "axY", "axZ", "angle", "angVel", "<init>", "(DDDFFFFFFIIIIFFFFF)V", "D", "getX", "()D", "setX", "(D)V", "getY", "setY", "getZ", "setZ", "F", "getVx", "()F", "setVx", "(F)V", "getVy", "setVy", "getVz", "setVz", "getHalf", "getU", "getV", "I", "getColor", "()I", "getLight", "getOverlay", "getTexIndex", "getAxX", "getAxY", "getAxZ", "getAngle", "setAngle", "getAngVel", "setAngVel", "", "sleeping", "Z", "getSleeping", "()Z", "setSleeping", "(Z)V", "restSteps", "getRestSteps", "setRestSteps", "(I)V", "dead", "getDead", "setDead", "released", "getReleased", "setReleased", "", "releaseMs", "J", "getReleaseMs", "()J", "setReleaseMs", "(J)V", "rtx.kimiko:kimiko"})
    private static final class CollapseCube {
        private double x;
        private double y;
        private double z;
        private float vx;
        private float vy;
        private float vz;
        private final float half;
        private final float u;
        private final float v;
        private final int color;
        private final int light;
        private final int overlay;
        private final int texIndex;
        private final float axX;
        private final float axY;
        private final float axZ;
        private float angle;
        private float angVel;
        private boolean sleeping;
        private int restSteps;
        private boolean dead;
        private boolean released;
        private long releaseMs;

        public CollapseCube(double x, double y, double z, float vx, float vy, float vz, float half, float u, float v, int color, int light, int overlay, int texIndex, float axX, float axY, float axZ, float angle, float angVel) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.vx = vx;
            this.vy = vy;
            this.vz = vz;
            this.half = half;
            this.u = u;
            this.v = v;
            this.color = color;
            this.light = light;
            this.overlay = overlay;
            this.texIndex = texIndex;
            this.axX = axX;
            this.axY = axY;
            this.axZ = axZ;
            this.angle = angle;
            this.angVel = angVel;
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

        public final float getHalf() {
            return this.half;
        }

        public final float getU() {
            return this.u;
        }

        public final float getV() {
            return this.v;
        }

        public final int getColor() {
            return this.color;
        }

        public final int getLight() {
            return this.light;
        }

        public final int getOverlay() {
            return this.overlay;
        }

        public final int getTexIndex() {
            return this.texIndex;
        }

        public final float getAxX() {
            return this.axX;
        }

        public final float getAxY() {
            return this.axY;
        }

        public final float getAxZ() {
            return this.axZ;
        }

        public final float getAngle() {
            return this.angle;
        }

        public final void setAngle(float f) {
            this.angle = f;
        }

        public final float getAngVel() {
            return this.angVel;
        }

        public final void setAngVel(float f) {
            this.angVel = f;
        }

        public final boolean getSleeping() {
            return this.sleeping;
        }

        public final void setSleeping(boolean bl) {
            this.sleeping = bl;
        }

        public final int getRestSteps() {
            return this.restSteps;
        }

        public final void setRestSteps(int n) {
            this.restSteps = n;
        }

        public final boolean getDead() {
            return this.dead;
        }

        public final void setDead(boolean bl) {
            this.dead = bl;
        }

        public final boolean getReleased() {
            return this.released;
        }

        public final void setReleased(boolean bl) {
            this.released = bl;
        }

        public final long getReleaseMs() {
            return this.releaseMs;
        }

        public final void setReleaseMs(long l) {
            this.releaseMs = l;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\u000bR\u0014\u0010\r\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000bR\u0014\u0010\u000f\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000bR\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0017R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00198\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u0017R\u0014\u0010\u001e\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u0017R\u0014\u0010\u001f\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u0017R\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"\u00a8\u0006#"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation.Companion;", "", "<init>", "()V", "", "MAX_PILES", "I", "MAX_CUBES_PER_PILE", "MAX_CUBES_TOTAL", "", "MAX_STEP", "F", "GRAVITY", "AIR_DRAG", "GROUND_FRICTION", "MIN_BOUNCE", "REST_SPEED_SQ", "", "SHRINK_MS", "J", "HURT_OVERLAY_MS", "", "UV_SCRATCH", "[F", "CLIP", "Lorg/joml/Vector3f;", "POSITION_SCRATCH", "Lorg/joml/Vector3f;", "NORMAL_SCRATCH", "CORNERS_X", "CORNERS_Y", "CORNERS_Z", "", "FACES", "[I", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b%\b\u0002\u0018\u00002\u00020\u0001B\u0081\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u000b\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0015\u001a\u00020\u0013\u0012\u0006\u0010\u0016\u001a\u00020\u0004\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b#\u0010 R\u0017\u0010\b\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\b\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010\t\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\t\u0010$\u001a\u0004\b'\u0010&R\u0017\u0010\n\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\n\u0010$\u001a\u0004\b(\u0010&R\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0006\u00a2\u0006\f\n\u0004\b\r\u0010)\u001a\u0004\b*\u0010+R\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006\u00a2\u0006\f\n\u0004\b\u0010\u0010)\u001a\u0004\b,\u0010+R\u001d\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u000b8\u0006\u00a2\u0006\f\n\u0004\b\u0012\u0010)\u001a\u0004\b-\u0010+R\u0017\u0010\u0014\u001a\u00020\u00138\u0006\u00a2\u0006\f\n\u0004\b\u0014\u0010.\u001a\u0004\b/\u00100R\u0017\u0010\u0015\u001a\u00020\u00138\u0006\u00a2\u0006\f\n\u0004\b\u0015\u0010.\u001a\u0004\b1\u00100R\u0017\u0010\u0016\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0016\u0010\u001e\u001a\u0004\b2\u0010 R\u0017\u0010\u0018\u001a\u00020\u00178\u0006\u00a2\u0006\f\n\u0004\b\u0018\u00103\u001a\u0004\b4\u00105R\"\u00106\u001a\u00020\u00178\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b6\u00103\u001a\u0004\b7\u00105\"\u0004\b8\u00109R\u0017\u0010:\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b:\u0010\u001e\u001a\u0004\b;\u0010 \u00a8\u0006<"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$Pile;", "", "", "entityId", "", "spawnMs", "lifeMs", "", "originX", "originY", "originZ", "", "Lnet/minecraft/Identifier;", "textures", "", "Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$CollapseCube;", "cubes", "Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$ShellQuad;", "shell", "", "shellMinY", "shellMaxY", "waveMs", "", "topDown", "<init>", "(IJJDDDLjava/util/List;Ljava/util/List;Ljava/util/List;FFJZ)V", "I", "getEntityId", "()I", "J", "getSpawnMs", "()J", "setSpawnMs", "(J)V", "getLifeMs", "D", "getOriginX", "()D", "getOriginY", "getOriginZ", "Ljava/util/List;", "getTextures", "()Ljava/util/List;", "getCubes", "getShell", "F", "getShellMinY", "()F", "getShellMaxY", "getWaveMs", "Z", "getTopDown", "()Z", "frozen", "getFrozen", "setFrozen", "(Z)V", "createdMs", "getCreatedMs", "rtx.kimiko:kimiko"})
    private static final class Pile {
        private final int entityId;
        private long spawnMs;
        private final long lifeMs;
        private final double originX;
        private final double originY;
        private final double originZ;
        @NotNull
        private final List<Identifier> textures;
        @NotNull
        private final List<CollapseCube> cubes;
        @NotNull
        private final List<ShellQuad> shell;
        private final float shellMinY;
        private final float shellMaxY;
        private final long waveMs;
        private final boolean topDown;
        private boolean frozen;
        private final long createdMs;

        public Pile(int entityId, long spawnMs, long lifeMs, double originX, double originY, double originZ, @NotNull List<Identifier> textures, @NotNull List<CollapseCube> cubes, @NotNull List<ShellQuad> shell, float shellMinY, float shellMaxY, long waveMs, boolean topDown) {
            Intrinsics.checkNotNullParameter(textures, (String)"textures");
            Intrinsics.checkNotNullParameter(cubes, (String)"cubes");
            Intrinsics.checkNotNullParameter(shell, (String)"shell");
            this.entityId = entityId;
            this.spawnMs = spawnMs;
            this.lifeMs = lifeMs;
            this.originX = originX;
            this.originY = originY;
            this.originZ = originZ;
            this.textures = textures;
            this.cubes = cubes;
            this.shell = shell;
            this.shellMinY = shellMinY;
            this.shellMaxY = shellMaxY;
            this.waveMs = waveMs;
            this.topDown = topDown;
            this.createdMs = this.spawnMs;
        }

        public final int getEntityId() {
            return this.entityId;
        }

        public final long getSpawnMs() {
            return this.spawnMs;
        }

        public final void setSpawnMs(long l) {
            this.spawnMs = l;
        }

        public final long getLifeMs() {
            return this.lifeMs;
        }

        public final double getOriginX() {
            return this.originX;
        }

        public final double getOriginY() {
            return this.originY;
        }

        public final double getOriginZ() {
            return this.originZ;
        }

        @NotNull
        public final List<Identifier> getTextures() {
            return this.textures;
        }

        @NotNull
        public final List<CollapseCube> getCubes() {
            return this.cubes;
        }

        @NotNull
        public final List<ShellQuad> getShell() {
            return this.shell;
        }

        public final float getShellMinY() {
            return this.shellMinY;
        }

        public final float getShellMaxY() {
            return this.shellMaxY;
        }

        public final long getWaveMs() {
            return this.waveMs;
        }

        public final boolean getTopDown() {
            return this.topDown;
        }

        public final boolean getFrozen() {
            return this.frozen;
        }

        public final void setFrozen(boolean bl) {
            this.frozen = bl;
        }

        public final long getCreatedMs() {
            return this.createdMs;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u000eJ\u0010\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0011J\u0010\u0010\u0014\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015JL\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\tH\u00c6\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u0019\u001a\u00020\t2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001c\u001a\u00020\u001bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0011\u0010\u001f\u001a\u00020\u001eH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001f\u0010 R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010!\u001a\u0004\b\"\u0010\u000eR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010!\u001a\u0004\b#\u0010\u000eR\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010$\u001a\u0004\b%\u0010\u0011R\u0017\u0010\u0007\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010!\u001a\u0004\b&\u0010\u000eR\u0017\u0010\b\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\b\u0010$\u001a\u0004\b'\u0010\u0011R\u0017\u0010\n\u001a\u00020\t8\u0006\u00a2\u0006\f\n\u0004\b\n\u0010(\u001a\u0004\b)\u0010\u0015\u00a8\u0006*"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$Settings;", "", "", "cubeSize", "impulse", "", "lifeMs", "restitution", "waveMs", "", "topDown", "<init>", "(FFJFJZ)V", "component1", "()F", "component2", "component3", "()J", "component4", "component5", "component6", "()Z", "copy", "(FFJFJZ)Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$Settings;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "F", "getCubeSize", "getImpulse", "J", "getLifeMs", "getRestitution", "getWaveMs", "Z", "getTopDown", "rtx.kimiko:kimiko"})
    public static final class Settings {
        private final float cubeSize;
        private final float impulse;
        private final long lifeMs;
        private final float restitution;
        private final long waveMs;
        private final boolean topDown;

        public Settings(float cubeSize, float impulse, long lifeMs, float restitution, long waveMs, boolean topDown) {
            this.cubeSize = cubeSize;
            this.impulse = impulse;
            this.lifeMs = lifeMs;
            this.restitution = restitution;
            this.waveMs = waveMs;
            this.topDown = topDown;
        }

        public final float getCubeSize() {
            return this.cubeSize;
        }

        public final float getImpulse() {
            return this.impulse;
        }

        public final long getLifeMs() {
            return this.lifeMs;
        }

        public final float getRestitution() {
            return this.restitution;
        }

        public final long getWaveMs() {
            return this.waveMs;
        }

        public final boolean getTopDown() {
            return this.topDown;
        }

        public final float component1() {
            return this.cubeSize;
        }

        public final float component2() {
            return this.impulse;
        }

        public final long component3() {
            return this.lifeMs;
        }

        public final float component4() {
            return this.restitution;
        }

        public final long component5() {
            return this.waveMs;
        }

        public final boolean component6() {
            return this.topDown;
        }

        @NotNull
        public final Settings copy(float cubeSize, float impulse, long lifeMs, float restitution, long waveMs, boolean topDown) {
            return new Settings(cubeSize, impulse, lifeMs, restitution, waveMs, topDown);
        }

        public static /* synthetic */ Settings copy$default(Settings settings, float f, float f2, long l, float f3, long l2, boolean bl, int n, Object object) {
            if ((n & 1) != 0) {
                f = settings.cubeSize;
            }
            if ((n & 2) != 0) {
                f2 = settings.impulse;
            }
            if ((n & 4) != 0) {
                l = settings.lifeMs;
            }
            if ((n & 8) != 0) {
                f3 = settings.restitution;
            }
            if ((n & 0x10) != 0) {
                l2 = settings.waveMs;
            }
            if ((n & 0x20) != 0) {
                bl = settings.topDown;
            }
            return settings.copy(f, f2, l, f3, l2, bl);
        }

        @NotNull
        public String toString() {
            return "Settings(cubeSize=" + this.cubeSize + ", impulse=" + this.impulse + ", lifeMs=" + this.lifeMs + ", restitution=" + this.restitution + ", waveMs=" + this.waveMs + ", topDown=" + this.topDown + ")";
        }

        public int hashCode() {
            int result = Float.hashCode(this.cubeSize);
            result = result * 31 + Float.hashCode(this.impulse);
            result = result * 31 + Long.hashCode(this.lifeMs);
            result = result * 31 + Float.hashCode(this.restitution);
            result = result * 31 + Long.hashCode(this.waveMs);
            result = result * 31 + Boolean.hashCode(this.topDown);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Settings)) {
                return false;
            }
            Settings settings = (Settings)other;
            if (Float.compare(this.cubeSize, settings.cubeSize) != 0) {
                return false;
            }
            if (Float.compare(this.impulse, settings.impulse) != 0) {
                return false;
            }
            if (this.lifeMs != settings.lifeMs) {
                return false;
            }
            if (Float.compare(this.restitution, settings.restitution) != 0) {
                return false;
            }
            if (this.waveMs != settings.waveMs) {
                return false;
            }
            return this.topDown == settings.topDown;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0019\b\u0002\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0013\u001a\u0004\b\u0016\u0010\u0015R\u0017\u0010\u0007\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0010\u001a\u0004\b\u0017\u0010\u0012R\u0017\u0010\b\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0010\u001a\u0004\b\u0018\u0010\u0012R\u0017\u0010\t\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0010\u001a\u0004\b\u0019\u0010\u0012R\u0017\u0010\u000b\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\f\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\f\u0010\u001a\u001a\u0004\b\u001d\u0010\u001cR\u0017\u0010\r\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\r\u0010\u001a\u001a\u0004\b\u001e\u0010\u001cR\u0017\u0010\u001f\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\u001f\u0010\u001a\u001a\u0004\b \u0010\u001cR\u0017\u0010!\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b!\u0010\u001a\u001a\u0004\b\"\u0010\u001c\u00a8\u0006#"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$ShellQuad;", "", "", "texIndex", "", "pos", "uv", "color", "light", "overlay", "", "nx", "ny", "nz", "<init>", "(I[F[FIIIFFF)V", "I", "getTexIndex", "()I", "[F", "getPos", "()[F", "getUv", "getColor", "getLight", "getOverlay", "F", "getNx", "()F", "getNy", "getNz", "minY", "getMinY", "maxY", "getMaxY", "rtx.kimiko:kimiko"})
    private static final class ShellQuad {
        private final int texIndex;
        @NotNull
        private final float[] pos;
        @NotNull
        private final float[] uv;
        private final int color;
        private final int light;
        private final int overlay;
        private final float nx;
        private final float ny;
        private final float nz;
        private final float minY;
        private final float maxY;

        public ShellQuad(int texIndex, @NotNull float[] pos, @NotNull float[] uv, int color, int light, int overlay, float nx, float ny, float nz) {
            Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
            Intrinsics.checkNotNullParameter((Object)uv, (String)"uv");
            this.texIndex = texIndex;
            this.pos = pos;
            this.uv = uv;
            this.color = color;
            this.light = light;
            this.overlay = overlay;
            this.nx = nx;
            this.ny = ny;
            this.nz = nz;
            this.minY = Math.min(Math.min(this.pos[1], this.pos[4]), Math.min(this.pos[7], this.pos[10]));
            this.maxY = Math.max(Math.max(this.pos[1], this.pos[4]), Math.max(this.pos[7], this.pos[10]));
        }

        public final int getTexIndex() {
            return this.texIndex;
        }

        @NotNull
        public final float[] getPos() {
            return this.pos;
        }

        @NotNull
        public final float[] getUv() {
            return this.uv;
        }

        public final int getColor() {
            return this.color;
        }

        public final int getLight() {
            return this.light;
        }

        public final int getOverlay() {
            return this.overlay;
        }

        public final float getNx() {
            return this.nx;
        }

        public final float getNy() {
            return this.ny;
        }

        public final float getNz() {
            return this.nz;
        }

        public final float getMinY() {
            return this.minY;
        }

        public final float getMaxY() {
            return this.maxY;
        }
    }
}

