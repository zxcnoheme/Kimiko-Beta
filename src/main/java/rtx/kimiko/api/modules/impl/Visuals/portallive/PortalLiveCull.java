/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  it.unimi.dsi.fastutil.objects.ObjectList
 *  it.unimi.dsi.fastutil.objects.ObjectListIterator
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.render.Frustum
 *  net.minecraft.client.render.BuiltChunkStorage
 *  net.minecraft.client.render.chunk.ChunkBuilder.BuiltChunk
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.portallive;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.BuiltChunkStorage;
import net.minecraft.client.render.chunk.ChunkBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\r\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003JQ\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0010\u0010\t\u001a\f\u0012\b\u0012\u00060\u0007R\u00020\b0\u00062\u0010\u0010\n\u001a\f\u0012\b\u0012\u00060\u0007R\u00020\b0\u00062\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0007b\u0002\b\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012J7\u0010\u0013\u001a\u00020\u000f2\u0010\u0010\t\u001a\f\u0012\b\u0012\u00060\u0007R\u00020\b0\u00062\u0010\u0010\n\u001a\f\u0012\b\u0012\u00060\u0007R\u00020\b0\u0006H\u0007b\u0002\b\u0010\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0016\u001a\u00020\u0015H\u0007b\u0002\b\u0010\u00a2\u0006\u0004\b\u0016\u0010\u0017J3\u0010\u001d\u001a\u00020\u00192\n\u0010\u0018\u001a\u00060\u0007R\u00020\b2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u001e\u0010!\u001a\f\u0012\b\u0012\u00060\u0007R\u00020\b0\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u001e\u0010#\u001a\f\u0012\b\u0012\u00060\u0007R\u00020\b0\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010\"R\u0016\u0010$\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%\u00a8\u0006&"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveCull;", "", "<init>", "()V", "Lnet/minecraft/BuiltChunkStorage;", "viewArea", "Lit/unimi/dsi/fastutil/objects/ObjectArrayList;", "Lnet/minecraft/ChunkBuilder$BuiltChunk;", "Lnet/minecraft/ChunkBuilder;", "visible", "nearby", "Lnet/minecraft/Camera;", "camera", "Lnet/minecraft/Frustum;", "frustum", "", "Lkotlin/jvm/JvmStatic;", "beginPortalCull", "(Lnet/minecraft/BuiltChunkStorage;Lit/unimi/dsi/fastutil/objects/ObjectArrayList;Lit/unimi/dsi/fastutil/objects/ObjectArrayList;Lnet/minecraft/Camera;Lnet/minecraft/Frustum;)V", "restoreAfterPortalPass", "(Lit/unimi/dsi/fastutil/objects/ObjectArrayList;Lit/unimi/dsi/fastutil/objects/ObjectArrayList;)V", "", "hasStash", "()Z", "section", "", "x", "y", "z", "distSqr", "(Lnet/minecraft/ChunkBuilder$BuiltChunk;DDD)D", "NEARBY_DIST_SQR", "D", "stashVisible", "Lit/unimi/dsi/fastutil/objects/ObjectArrayList;", "stashNearby", "stashed", "Z", "rtx.kimiko:kimiko"})
public final class PortalLiveCull {
    @NotNull
    public static final PortalLiveCull INSTANCE = new PortalLiveCull();
    private static final double NEARBY_DIST_SQR = 1024.0;
    @NotNull
    private static final ObjectArrayList<ChunkBuilder.BuiltChunk> stashVisible = new ObjectArrayList();
    @NotNull
    private static final ObjectArrayList<ChunkBuilder.BuiltChunk> stashNearby = new ObjectArrayList();
    private static boolean stashed;

    private PortalLiveCull() {
    }

    @JvmStatic
    public static final void beginPortalCull(@Nullable BuiltChunkStorage viewArea, @NotNull ObjectArrayList<ChunkBuilder.BuiltChunk> visible, @NotNull ObjectArrayList<ChunkBuilder.BuiltChunk> nearby, @NotNull Camera camera, @NotNull Frustum frustum) {
        Intrinsics.checkNotNullParameter(visible, (String)"visible");
        Intrinsics.checkNotNullParameter(nearby, (String)"nearby");
        Intrinsics.checkNotNullParameter((Object)camera, (String)"camera");
        Intrinsics.checkNotNullParameter((Object)frustum, (String)"frustum");
        if (!stashed) {
            stashVisible.clear();
            stashVisible.addAll((ObjectList)visible);
            stashNearby.clear();
            stashNearby.addAll((ObjectList)nearby);
            stashed = true;
        }
        visible.clear();
        nearby.clear();
        if (viewArea == null) {
            return;
        }
        if (viewArea.chunks == null) {
            return;
        }
        for (ChunkBuilder.BuiltChunk section : viewArea.chunks) {
            if (section != null && frustum.isVisible(section.getBoundingBox())) {
                visible.add(section);
            }
        }
        Vec3d cam = camera.getCameraPos();
        double camX = cam.x;
        double camY = cam.y;
        double camZ = cam.z;
        visible.sort(Comparator.comparingDouble(section -> INSTANCE.distSqr(section, camX, camY, camZ)));
        for (ChunkBuilder.BuiltChunk section : visible) {
            if (section != null && INSTANCE.distSqr(section, camX, camY, camZ) < 1024.0) {
                nearby.add(section);
            } else {
                break;
            }
        }
    }

    @JvmStatic
    public static final void restoreAfterPortalPass(@NotNull ObjectArrayList<ChunkBuilder.BuiltChunk> visible, @NotNull ObjectArrayList<ChunkBuilder.BuiltChunk> nearby) {
        Intrinsics.checkNotNullParameter(visible, (String)"visible");
        Intrinsics.checkNotNullParameter(nearby, (String)"nearby");
        if (!stashed) {
            return;
        }
        visible.clear();
        visible.addAll((ObjectList)stashVisible);
        nearby.clear();
        nearby.addAll((ObjectList)stashNearby);
        stashVisible.clear();
        stashNearby.clear();
        stashed = false;
    }

    @JvmStatic
    public static final boolean hasStash() {
        return stashed;
    }

    private final double distSqr(ChunkBuilder.BuiltChunk section, double x, double y, double z) {
        BlockPos blockPos2 = section.getOrigin();
        Intrinsics.checkNotNullExpressionValue((Object)blockPos2, (String)"getRenderOrigin(...)");
        BlockPos origin = blockPos2;
        double dx = (double)origin.getX() + 8.0 - x;
        double dy = (double)origin.getY() + 8.0 - y;
        double dz = (double)origin.getZ() + 8.0 - z;
        return dx * dx + dy * dy + dz * dz;
    }

    private static final double beginPortalCull$lambda$0(double $camX, double $camY, double $camZ, ChunkBuilder.BuiltChunk section) {
        Intrinsics.checkNotNull((Object)section);
        return INSTANCE.distSqr(section, $camX, $camY, $camZ);
    }

    private static final double beginPortalCull$lambda$1(Function1 $tmp0, Object p0) {
        return ((Number)$tmp0.invoke(p0)).doubleValue();
    }
}

