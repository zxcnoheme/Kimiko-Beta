/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.objects.ObjectCollection
 *  it.unimi.dsi.fastutil.objects.ObjectIterator
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos.Mutable
 *  net.minecraft.util.math.Box
 *  net.minecraft.block.BlockState
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.world.ClientWorld
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.modules.optimization;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001EB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J%\u0010\f\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ5\u0010\u0015\u001a\u00020\u00142\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0010H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0015\u0010\u0016J;\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0017H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0019\u0010\u001aJG\u0010$\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b$\u0010%JW\u0010)\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b)\u0010*R\u0014\u0010,\u001a\u00020+8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010/\u001a\u00020.8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0014\u00101\u001a\u00020.8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b1\u00100R\u0014\u00102\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0014\u00104\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u00103R\u0014\u00105\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u00106R\u0014\u00107\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u00106R\u0014\u00108\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010:\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b:\u00109R\u001a\u0010;\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u001a\u0010=\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010<R\u0014\u0010?\u001a\u00020>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010@R\u0018\u0010A\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u0010BR\u0016\u0010C\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bC\u00106R\u0016\u0010D\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u00109\u00a8\u0006F"}, d2={"Lrtx/kimiko/utils/render/modules/optimization/OcclusionCuller;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "beginFrame", "Lit/unimi/dsi/fastutil/ints/Int2ObjectOpenHashMap;", "Lrtx/kimiko/utils/render/modules/optimization/OcclusionCuller$Entry;", "cache", "", "now", "sweep", "(Lit/unimi/dsi/fastutil/ints/Int2ObjectOpenHashMap;J)V", "Lnet/minecraft/Entity;", "entity", "", "camX", "camY", "camZ", "", "isExposed", "(Lnet/minecraft/Entity;DDD)Z", "", "tier", "isVisible", "(Lnet/minecraft/Entity;DDDI)Z", "Lnet/minecraft/ClientWorld;", "level", "x0", "y0", "z0", "Lnet/minecraft/Box;", "box", "maxSteps", "blockedOnOverflow", "raycastVisible", "(Lnet/minecraft/ClientWorld;DDDLnet/minecraft/Box;IZ)Z", "x1", "y1", "z1", "sightBlocked", "(Lnet/minecraft/ClientWorld;DDDDDDIZ)Z", "", "BUDGET", "[I", "", "HIDDEN_TTL", "[J", "VISIBLE_TTL", "MIN_DIST_SQ", "D", "MAX_ENTITY_SIZE", "MAX_RAY_STEPS", "I", "EXPOSED_RAY_STEPS", "EXPOSED_HIDDEN_TTL", "J", "EXPOSED_VISIBLE_TTL", "CACHE", "Lit/unimi/dsi/fastutil/ints/Int2ObjectOpenHashMap;", "EXPOSED_CACHE", "Lnet/minecraft/BlockPos$Mutable;", "POS", "Lnet/minecraft/BlockPos$Mutable;", "lastLevel", "Lnet/minecraft/ClientWorld;", "checksThisFrame", "lastSweep", "Entry", "rtx.kimiko:kimiko"})
public final class OcclusionCuller {
    @NotNull
    public static final OcclusionCuller INSTANCE = new OcclusionCuller();
    @NotNull
    private static final int[] BUDGET;
    @NotNull
    private static final long[] HIDDEN_TTL;
    @NotNull
    private static final long[] VISIBLE_TTL;
    private static final double MIN_DIST_SQ = 64.0;
    private static final double MAX_ENTITY_SIZE = 6.0;
    private static final int MAX_RAY_STEPS = 172;
    private static final int EXPOSED_RAY_STEPS = 512;
    private static final long EXPOSED_HIDDEN_TTL = 100L;
    private static final long EXPOSED_VISIBLE_TTL = 200L;
    @NotNull
    private static final Int2ObjectOpenHashMap<Entry> CACHE;
    @NotNull
    private static final Int2ObjectOpenHashMap<Entry> EXPOSED_CACHE;
    @NotNull
    private static final BlockPos.Mutable POS;
    @Nullable
    private static ClientWorld lastLevel;
    private static int checksThisFrame;
    private static long lastSweep;

    private OcclusionCuller() {
    }

    @JvmStatic
    public static final void beginFrame() {
        long now;
        checksThisFrame = 0;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.world != lastLevel) {
            CACHE.clear();
            EXPOSED_CACHE.clear();
            lastLevel = mc.world;
        }
        if ((now = System.currentTimeMillis()) - lastSweep > 5000L) {
            lastSweep = now;
            INSTANCE.sweep(CACHE, now);
            INSTANCE.sweep(EXPOSED_CACHE, now);
        }
    }

    private final void sweep(Int2ObjectOpenHashMap<Entry> cache2, long now) {
        ObjectIterator objectIterator = ((ObjectCollection)cache2.values()).iterator();
        Intrinsics.checkNotNullExpressionValue((Object)objectIterator, (String)"iterator(...)");
        ObjectIterator entries = objectIterator;
        while (entries.hasNext()) {
            if (now - ((Entry)entries.next()).next <= 5000L) continue;
            entries.remove();
        }
    }

    @JvmStatic
    public static final boolean isExposed(@Nullable Entity entity, double camX, double camY, double camZ) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        ClientWorld level = mc.world;
        if (level == null || entity == null || entity == mc.player) {
            return true;
        }
        long now = System.currentTimeMillis();
        Entry entry = (Entry)EXPOSED_CACHE.get(entity.getId());
        if (entry != null && now < entry.next) {
            return entry.visible;
        }
        Box box2 = entity.getBoundingBox().expand(0.05);
        Intrinsics.checkNotNullExpressionValue((Object)box2, (String)"inflate(...)");
        boolean visible = INSTANCE.raycastVisible(level, camX, camY, camZ, box2, 512, true);
        if (entry == null) {
            entry = new Entry();
            ((Map)EXPOSED_CACHE).put(entity.getId(), entry);
        }
        entry.visible = visible;
        entry.next = now + (visible ? 200L : 100L) + (long)(entity.getId() * 17 & 0x1F);
        return visible;
    }

    @JvmStatic
    public static final boolean isVisible(@NotNull Entity entity, double camX, double camY, double camZ, int tier) {
        double dz;
        double dy;
        Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        ClientWorld level = mc.world;
        if (level == null || entity == mc.player || entity.isGlowing()) {
            return true;
        }
        if (entity instanceof PlayerEntity) {
            return true;
        }
        double dx = entity.getX() - camX;
        if (dx * dx + (dy = entity.getY() - camY) * dy + (dz = entity.getZ() - camZ) * dz < 64.0) {
            return true;
        }
        Box box2 = entity.getBoundingBox();
        Intrinsics.checkNotNullExpressionValue((Object)box2, (String)"getBoundingBox(...)");
        Box box = box2;
        if (box.getLengthX() > 6.0 || box.getLengthY() > 6.0 || box.getLengthZ() > 6.0) {
            return true;
        }
        int t = MathHelper.clamp((int)tier, (int)0, (int)2);
        long now = System.currentTimeMillis();
        Entry entry = (Entry)CACHE.get(entity.getId());
        if (entry != null && now < entry.next) {
            return entry.visible;
        }
        if (checksThisFrame >= BUDGET[t]) {
            return entry == null || entry.visible;
        }
        int n = checksThisFrame;
        checksThisFrame = n + 1;
        Box box3 = box.expand(0.1);
        Intrinsics.checkNotNullExpressionValue((Object)box3, (String)"inflate(...)");
        boolean visible = INSTANCE.raycastVisible(level, camX, camY, camZ, box3, 172, false);
        if (entry == null) {
            entry = new Entry();
            ((Map)CACHE).put(entity.getId(), entry);
        }
        entry.visible = visible;
        entry.next = now + (visible ? VISIBLE_TTL[t] : HIDDEN_TTL[t]) + (long)(entity.getId() * 31 & 0x3F);
        return visible;
    }

    private final boolean raycastVisible(ClientWorld level, double x0, double y0, double z0, Box box, int maxSteps, boolean blockedOnOverflow) {
        double tz;
        if (!this.sightBlocked(level, x0, y0, z0, (box.minX + box.maxX) * 0.5, (box.minY + box.maxY) * 0.5, (box.minZ + box.maxZ) * 0.5, maxSteps, blockedOnOverflow)) {
            return true;
        }
        for (int i = 0; i < 8; ++i) {
            double tx = (i & 1) == 0 ? box.minX : box.maxX;
            double ty = (i & 2) == 0 ? box.minY : box.maxY;
            double d = tz = (i & 4) == 0 ? box.minZ : box.maxZ;
            if (this.sightBlocked(level, x0, y0, z0, tx, ty, tz, maxSteps, blockedOnOverflow)) continue;
            return true;
        }
        double midY = (box.minY + box.maxY) * 0.5;
        for (int i = 0; i < 4; ++i) {
            double tx = (i & 1) == 0 ? box.minX : box.maxX;
            double d = tz = (i & 2) == 0 ? box.minZ : box.maxZ;
            if (this.sightBlocked(level, x0, y0, z0, tx, midY, tz, maxSteps, blockedOnOverflow)) continue;
            return true;
        }
        return false;
    }

    private final boolean sightBlocked(ClientWorld level, double x0, double y0, double z0, double x1, double y1, double z1, int maxSteps, boolean blockedOnOverflow) {
        double dx = x1 - x0;
        double dy = y1 - y0;
        double dz = z1 - z0;
        int x = MathHelper.floor(x0);
        int y = MathHelper.floor(y0);
        int z = MathHelper.floor(z0);
        int ex = MathHelper.floor(x1);
        int ey = MathHelper.floor(y1);
        int ez = MathHelper.floor(z1);
        int stepX = dx > 0.0 ? 1 : (dx < 0.0 ? -1 : 0);
        int stepY = dy > 0.0 ? 1 : (dy < 0.0 ? -1 : 0);
        int stepZ = dz > 0.0 ? 1 : (dz < 0.0 ? -1 : 0);
        double tDeltaX = stepX == 0 ? Double.MAX_VALUE : Math.abs(1.0 / dx);
        double tDeltaY = stepY == 0 ? Double.MAX_VALUE : Math.abs(1.0 / dy);
        double tDeltaZ = stepZ == 0 ? Double.MAX_VALUE : Math.abs(1.0 / dz);
        double tMaxX = stepX == 0 ? Double.MAX_VALUE : (stepX > 0 ? Math.floor(x0) + 1.0 - x0 : x0 - Math.floor(x0)) * tDeltaX;
        double tMaxY = stepY == 0 ? Double.MAX_VALUE : (stepY > 0 ? Math.floor(y0) + 1.0 - y0 : y0 - Math.floor(y0)) * tDeltaY;
        double tMaxZ = stepZ == 0 ? Double.MAX_VALUE : (stepZ > 0 ? Math.floor(z0) + 1.0 - z0 : z0 - Math.floor(z0)) * tDeltaZ;
        for (int i = 0; i < maxSteps; ++i) {
            if (Math.min(tMaxX, Math.min(tMaxY, tMaxZ)) >= 1.0) {
                return false;
            }
            if (tMaxX <= tMaxY && tMaxX <= tMaxZ) {
                x += stepX;
                tMaxX += tDeltaX;
            } else if (tMaxY <= tMaxZ) {
                y += stepY;
                tMaxY += tDeltaY;
            } else {
                z += stepZ;
                tMaxZ += tDeltaZ;
            }
            if (x == ex && y == ey && z == ez) {
                return false;
            }
            BlockState state = level.getBlockState(POS.set(x, y, z));
            if (state.isAir() || !state.isOpaque()) continue;
            return true;
        }
        return blockedOnOverflow;
    }

    static {
        BUDGET = new int[]{16, 24, 32};
        HIDDEN_TTL = new long[]{150L, 120L, 90L};
        VISIBLE_TTL = new long[]{450L, 350L, 250L};
        CACHE = new Int2ObjectOpenHashMap();
        EXPOSED_CACHE = new Int2ObjectOpenHashMap();
        POS = new BlockPos.Mutable();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0006\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u001b\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/utils/render/modules/optimization/OcclusionCuller$Entry;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmField;", "visible", "Z", "", "next", "J", "rtx.kimiko:kimiko"})
    private static final class Entry {
        @JvmField
        public boolean visible = true;
        @JvmField
        public long next;
    }
}

