/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.comparisons.ComparisonsKt
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.ranges.RangesKt
 *  net.minecraft.client.texture.NativeImage
 *  net.minecraft.client.render.item.ItemRenderState
 *  net.minecraft.client.render.item.ItemRenderState.LayerRenderState
 *  net.minecraft.client.texture.Sprite
 *  net.minecraft.item.ItemStack
 *  net.minecraft.component.type.PotionContentsComponent
 *  net.minecraft.world.World
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.texture.SpriteContents
 *  net.minecraft.client.render.model.BakedQuad
 *  net.minecraft.item.ItemDisplayContext
 *  net.minecraft.component.type.DyedColorComponent
 *  net.minecraft.component.DataComponentTypes
 *  net.minecraft.util.math.ColorHelper
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals.lootview;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import mixin.accessor.ItemLayerRenderStateAccessor;
import mixin.accessor.ItemStackRenderStateAccessor;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.item.ItemStack;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.world.World;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.SpriteContents;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.util.math.ColorHelper;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.modules.impl.Visuals.lootview.LootShape;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0018\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\u0003J\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ!\u0010\u0010\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0010\u0010\nJ)\u0010\u0014\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015JG\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b\u001f\u0010 J/\u0010!\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b!\u0010\"J!\u0010#\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b#\u0010$J\u0017\u0010'\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0002\u00a2\u0006\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010*R\u0014\u0010,\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010*R\u0014\u0010-\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010*R\u0014\u0010.\u001a\u00020%8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010/R\u0014\u00100\u001a\u00020%8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u0010/R0\u00103\u001a\u001e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\b01j\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\b`28\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u00104\u00a8\u00065"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/lootview/LootViewShapes;", "", "<init>", "()V", "Lnet/minecraft/ItemStack;", "stack", "", "points", "Lrtx/kimiko/api/modules/impl/Visuals/lootview/LootShape;", "get", "(Lnet/minecraft/ItemStack;I)Lrtx/kimiko/api/modules/impl/Visuals/lootview/LootShape;", "", "clear", "", "cacheKey", "(Lnet/minecraft/ItemStack;I)J", "build", "", "mask", "color", "scatter", "([ZII)Lrtx/kimiko/api/modules/impl/Visuals/lootview/LootShape;", "Lnet/minecraft/Sprite;", "sprite", "tint", "", "bucketW", "bucketR", "bucketG", "bucketB", "", "sampleSprite", "(Lnet/minecraft/Sprite;I[Z[F[F[F[F)Z", "dominantColor", "([F[F[F[F)I", "fallback", "(II)Lrtx/kimiko/api/modules/impl/Visuals/lootview/LootShape;", "", "value", "fract", "(F)F", "GRID", "I", "ALPHA_COLOR", "ALPHA_MASK", "MAX_CACHE", "WORLD_SPAN", "F", "TAU", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "cache", "Ljava/util/HashMap;", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nLootViewShapes.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LootViewShapes.kt\nrtx/kimiko/api/modules/impl/Visuals/lootview/LootViewShapes\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,250:1\n1174#2,2:251\n*S KotlinDebug\n*F\n+ 1 LootViewShapes.kt\nrtx/kimiko/api/modules/impl/Visuals/lootview/LootViewShapes\n*L\n129#1:251,2\n*E\n"})
public final class LootViewShapes {
    @NotNull
    public static final LootViewShapes INSTANCE = new LootViewShapes();
    private static final int GRID = 28;
    private static final int ALPHA_COLOR = 40;
    private static final int ALPHA_MASK = 96;
    private static final int MAX_CACHE = 512;
    private static final float WORLD_SPAN = 0.5f;
    private static final float TAU = (float)Math.PI * 2;
    @NotNull
    private static final HashMap<Long, LootShape> cache = new HashMap();

    private LootViewShapes() {
    }

    @NotNull
    public final LootShape get(@NotNull ItemStack stack, int points) {
        LootShape lootShape;
        LootShape lootShape2;
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        long key = this.cacheKey(stack, points);
        LootShape cached = cache.get(key);
        if (cached != null) {
            return cached;
        }
        if (cache.size() >= 512) {
            cache.clear();
        }
        try {
            lootShape2 = this.build(stack, points);
        }
        catch (RuntimeException exception) {
            lootShape2 = null;
        }
        if ((lootShape = lootShape2) == null) {
            lootShape = LootViewShapes.fallback$default(this, points, 0, 2, null);
        }
        LootShape shape = lootShape;
        ((Map)cache).put(key, shape);
        return shape;
    }

    public final void clear() {
        cache.clear();
    }

    private final long cacheKey(ItemStack stack, int points) {
        PotionContentsComponent potion = (PotionContentsComponent)stack.get(DataComponentTypes.POTION_CONTENTS);
        DyedColorComponent dyed = (DyedColorComponent)stack.get(DataComponentTypes.DYED_COLOR);
        PotionContentsComponent potionContentsComponent2 = potion;
        int disc = potionContentsComponent2 != null ? potionContentsComponent2.getColor() : 0;
        DyedColorComponent dyedColorComponent2 = dyed;
        disc = disc * 31 + (dyedColorComponent2 != null ? dyedColorComponent2.rgb() : 0);
        return (long)System.identityHashCode(stack.getItem()) * -7046029254386353131L ^ ((long)disc & 0xFFFFFFFFL) << 8 ^ (long)points;
    }

    private final LootShape build(ItemStack stack, int points) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        ItemRenderState state = new ItemRenderState();
        mc.getItemModelManager().clearAndUpdate(state, stack, ItemDisplayContext.GROUND, (World)mc.world, null, 0);
        ItemStackRenderStateAccessor stateAccessor = (ItemStackRenderStateAccessor)state;
        ItemRenderState.LayerRenderState[] layers = stateAccessor.kimiko$getLayers();
        int layerCount = Math.min(stateAccessor.kimiko$getActiveLayerCount(), layers.length);
        boolean[] mask = new boolean[784];
        float[] bucketW = new float[4096];
        float[] bucketR = new float[4096];
        float[] bucketG = new float[4096];
        float[] bucketB = new float[4096];
        HashSet<Sprite> seenSprites = new HashSet<Sprite>();
        boolean sampled = false;
        for (int layerIndex = 0; layerIndex < layerCount; ++layerIndex) {
            ItemRenderState.LayerRenderState layer = layers[layerIndex];
            if (layer == null) continue;
            ItemLayerRenderStateAccessor layerAccessor = (ItemLayerRenderStateAccessor)layer;
            if (layerAccessor.kimiko$getSpecialRenderer() != null) continue;
            List quads = layer.getQuads();
            if (quads == null || quads.isEmpty()) continue;
            int[] tints = layerAccessor.kimiko$getTintLayers();
            for (Object e : quads) {
                BakedQuad quad = (BakedQuad)e;
                Sprite sprite = quad.sprite();
                if (sprite == null || !seenSprites.add(sprite)) continue;
                int tintIndex = quad.tintIndex();
                int tint = tints != null && tintIndex >= 0 && tintIndex < tints.length ? tints[tintIndex] : -1;
                if (!this.sampleSprite(sprite, tint, mask, bucketW, bucketR, bucketG, bucketB)) continue;
                sampled = true;
            }
        }
        if (!sampled) {
            return null;
        }
        int color = this.dominantColor(bucketW, bucketR, bucketG, bucketB);
        return this.scatter(mask, points, color);
    }

    private final LootShape scatter(boolean[] mask, int points, int color) {
        ArrayList<Integer> cells = new ArrayList<Integer>(196);
        for (int y = 0; y < 28; ++y) {
            for (int x = 0; x < 28; ++x) {
                if (!mask[y * 28 + x]) continue;
                cells.add(x << 8 | y);
            }
        }
        if (cells.size() < 3) {
            return null;
        }
        if (cells.size() > 1) {
            cells.sort((a, b) -> {
                int ax = a >> 8;
                int ay = a & 0xFF;
                int bx = b >> 8;
                int by = b & 0xFF;
                int h1 = ax * 73856093 ^ ay * 19349663 ^ (ax + 7) * (ay + 13) * 83492791;
                int h2 = bx * 73856093 ^ by * 19349663 ^ (bx + 7) * (by + 13) * 83492791;
                return Integer.compare(h1, h2);
            });
        }
        int count = Math.min(points, cells.size());
        float[] xs = new float[count];
        float[] zs = new float[count];
        float[] jitter = new float[count];
        float cellSize = 0.017857144f;
        float half = 14.0f;
        for (int k = 0; k < count; ++k) {
            Object e = cells.get(k * cells.size() / count);
            Intrinsics.checkNotNullExpressionValue(e, (String)"get(...)");
            int cell = ((Number)e).intValue();
            int x = cell >> 8;
            int y = cell & 0xFF;
            xs[k] = ((float)x + 0.5f - half) * cellSize;
            zs[k] = (27.5f - (float)y - half) * cellSize;
            jitter[k] = 0.78f + 0.44f * this.fract((float)Math.sin((float)k * 12.9898f) * 43758.547f);
        }
        return new LootShape(xs, zs, jitter, color, count);
    }

    private final boolean sampleSprite(Sprite sprite, int tint, boolean[] mask, float[] bucketW, float[] bucketR, float[] bucketG, float[] bucketB) {
        SpriteContents spriteContents2 = sprite.getContents();
        Intrinsics.checkNotNullExpressionValue((Object)spriteContents2, (String)"contents(...)");
        SpriteContents contents = spriteContents2;
        NativeImage nativeImage2 = contents.image;
        if (nativeImage2 == null) {
            return false;
        }
        NativeImage image = nativeImage2;
        int frameW = Math.min(contents.width, image.getWidth());
        int frameH = Math.min(contents.height, image.getHeight());
        if (frameW <= 0 || frameH <= 0) {
            return false;
        }
        int tintR = tint == -1 ? 255 : ColorHelper.getRed((int)tint);
        int tintG = tint == -1 ? 255 : ColorHelper.getGreen((int)tint);
        int tintB = tint == -1 ? 255 : ColorHelper.getBlue((int)tint);
        boolean any = false;
        for (int gy = 0; gy < 28; ++gy) {
            int sy = gy * frameH / 28;
            for (int gx = 0; gx < 28; ++gx) {
                int sx = gx * frameW / 28;
                int pixel = image.getColorArgb(sx, sy);
                int alpha = ColorHelper.getAlpha((int)pixel);
                if (alpha < 40) continue;
                any = true;
                if (alpha >= 96) {
                    mask[gy * 28 + gx] = true;
                }
                int r = ColorHelper.getRed((int)pixel) * tintR / 255;
                int g = ColorHelper.getGreen((int)pixel) * tintG / 255;
                int b = ColorHelper.getBlue((int)pixel) * tintB / 255;
                int maxC = Math.max(r, Math.max(g, b));
                int minC = Math.min(r, Math.min(g, b));
                float saturation = maxC > 0 ? (float)(maxC - minC) / (float)maxC : 0.0f;
                float weight = (float)alpha / 255.0f * (0.15f + saturation) * (0.2f + 0.8f * (float)maxC / 255.0f);
                if (weight <= 0.0f) continue;
                int bucket = r >> 4 << 8 | g >> 4 << 4 | b >> 4;
                bucketW[bucket] = bucketW[bucket] + weight;
                bucketR[bucket] = bucketR[bucket] + (float)r * weight;
                bucketG[bucket] = bucketG[bucket] + (float)g * weight;
                bucketB[bucket] = bucketB[bucket] + (float)b * weight;
            }
        }
        return any;
    }

    private final int dominantColor(float[] bucketW, float[] bucketR, float[] bucketG, float[] bucketB) {
        int best = -1;
        float bestW = 0.0f;
        for (int i = 0; i < 4096; ++i) {
            if (!(bucketW[i] > bestW)) continue;
            bestW = bucketW[i];
            best = i;
        }
        if (best < 0 || bestW <= 0.0f) {
            return -1;
        }
        int r = RangesKt.coerceIn((int)((int)(bucketR[best] / bestW)), (int)0, (int)255);
        int g = RangesKt.coerceIn((int)((int)(bucketG[best] / bestW)), (int)0, (int)255);
        int b = RangesKt.coerceIn((int)((int)(bucketB[best] / bestW)), (int)0, (int)255);
        float[] hsb = Color.RGBtoHSB(r, g, b, null);
        float saturation = Math.min(1.0f, hsb[1] * 1.2f);
        float brightness = Math.max(0.55f, hsb[2]);
        return Color.HSBtoRGB(hsb[0], saturation, brightness) | 0xFF000000;
    }

    private final LootShape fallback(int points, int color) {
        float[] xs = new float[points];
        float[] zs = new float[points];
        float[] jitter = new float[points];
        for (int i = 0; i < points; ++i) {
            float angle = (float)Math.PI * 2 * (float)i / (float)points;
            xs[i] = (float)Math.cos(angle) * 0.5f * 0.4f;
            zs[i] = (float)Math.sin(angle) * 0.5f * 0.4f;
            jitter[i] = 0.78f + 0.44f * this.fract((float)Math.sin((float)i * 12.9898f) * 43758.547f);
        }
        return new LootShape(xs, zs, jitter, color, points);
    }

    static /* synthetic */ LootShape fallback$default(LootViewShapes lootViewShapes, int n, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = -1;
        }
        return lootViewShapes.fallback(n, n2);
    }

    private final float fract(float value) {
        return value - (float)Math.floor(value);
    }
}

