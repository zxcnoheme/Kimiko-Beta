/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.screen.ScreenHandler
 *  net.minecraft.screen.BrewingStandScreenHandler
 *  net.minecraft.item.ItemStack
 *  net.minecraft.block.BrewingStandBlock
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.hit.HitResult
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.hit.BlockHitResult
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.gui.screen.ingame.BrewingStandScreen
 *  net.minecraft.client.world.ClientWorld
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.brewviewer;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.BrewingStandScreenHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.block.BrewingStandBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.hit.HitResult;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.BrewingStandScreen;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u00011B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0010\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\r\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u0017\u001a\u00020\u00162\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0012H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0018J'\u0010\u0019\u001a\u00020\u00162\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0012H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0019\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ/\u0010!\u001a\u00020 2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001f\u001a\u00020\u001eH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b!\u0010\"J\u0013\u0010#\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b#\u0010\u0003R\u0014\u0010$\u001a\u00020\u001e8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020&8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010(R0\u0010,\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00120*j\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0012`+8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0018\u0010.\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010/R\u0016\u00100\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u0010(\u00a8\u00062"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewStandCache;", "", "<init>", "()V", "Lnet/minecraft/MinecraftClient;", "mc", "", "Lkotlin/jvm/JvmStatic;", "tick", "(Lnet/minecraft/MinecraftClient;)V", "Lnet/minecraft/BlockPos;", "resolveTargetPos", "(Lnet/minecraft/MinecraftClient;)Lnet/minecraft/BlockPos;", "pos", "Lnet/minecraft/BrewingStandScreenHandler;", "menu", "capture", "(Lnet/minecraft/BlockPos;Lnet/minecraft/BrewingStandScreenHandler;)V", "Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewStandCache$Entry;", "get", "(Lnet/minecraft/BlockPos;)Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewStandCache$Entry;", "entry", "", "progress", "(Lnet/minecraft/MinecraftClient;Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewStandCache$Entry;)F", "secondsLeft", "ticksLeftNow", "(Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewStandCache$Entry;)F", "finish", "(Lnet/minecraft/MinecraftClient;Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewStandCache$Entry;)V", "", "slot", "Lnet/minecraft/ItemStack;", "previewResult", "(Lnet/minecraft/MinecraftClient;Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewStandCache$Entry;I)Lnet/minecraft/ItemStack;", "clear", "BREW_TICKS", "I", "", "MS_PER_TICK", "J", "WARMUP_MS", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "entries", "Ljava/util/HashMap;", "openPos", "Lnet/minecraft/BlockPos;", "openedAtMs", "Entry", "rtx.kimiko:kimiko"})
public final class BrewStandCache {
    @NotNull
    public static final BrewStandCache INSTANCE = new BrewStandCache();
    public static final int BREW_TICKS = 400;
    private static final long MS_PER_TICK = 50L;
    private static final long WARMUP_MS = 400L;
    @NotNull
    private static final HashMap<BlockPos, Entry> entries = new HashMap();
    @Nullable
    private static BlockPos openPos;
    private static long openedAtMs;

    private BrewStandCache() {
    }

    @JvmStatic
    public static final void tick(@Nullable MinecraftClient mc) {
        if (mc == null || mc.world == null) {
            openPos = null;
            return;
        }
        Screen screen = mc.currentScreen;
        if (screen instanceof BrewingStandScreen) {
            BlockPos pos;
            if (openPos == null) {
                openPos = INSTANCE.resolveTargetPos(mc);
                openedAtMs = System.currentTimeMillis();
            }
            if ((pos = openPos) != null) {
                ScreenHandler screenHandler2 = ((BrewingStandScreen)screen).getScreenHandler();
                Intrinsics.checkNotNullExpressionValue((Object)screenHandler2, (String)"getMenu(...)");
                INSTANCE.capture(pos, (BrewingStandScreenHandler)screenHandler2);
            }
        } else {
            openPos = null;
        }
    }

    private final BlockPos resolveTargetPos(MinecraftClient mc) {
        HitResult hit = mc.crosshairTarget;
        if (hit instanceof BlockHitResult blockHit && mc.world != null) {
            BlockPos bpos = blockHit.getBlockPos();
            if (mc.world.getBlockState(bpos).getBlock() instanceof BrewingStandBlock) {
                return bpos.toImmutable();
            }
        }
        return null;
    }

    private final void capture(BlockPos pos, BrewingStandScreenHandler menu) {
        Entry entry2 = entries.computeIfAbsent(pos, k -> new Entry());
        boolean incomingEmpty = menu.getBrewTime() <= 0;
        if (incomingEmpty) {
            for (int i = 0; i < 5 && incomingEmpty; ++i) {
                incomingEmpty = menu.getSlot(i).getStack().isEmpty();
            }
        }
        if (incomingEmpty && entry2.hasContent() && System.currentTimeMillis() - openedAtMs < 400L) {
            return;
        }
        for (int i = 0; i < 3; ++i) {
            entry2.bottles[i] = menu.getSlot(i).getStack().copy();
        }
        ItemStack itemStack2 = menu.getSlot(3).getStack().copy();
        Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"copy(...)");
        entry2.ingredient = itemStack2;
        ItemStack itemStack3 = menu.getSlot(4).getStack().copy();
        Intrinsics.checkNotNullExpressionValue((Object)itemStack3, (String)"copy(...)");
        entry2.fuel = itemStack3;
        entry2.ticksLeft = menu.getBrewTime();
        entry2.capturedAtMs = System.currentTimeMillis();
        entry2.brewing = entry2.ticksLeft > 0 && !entry2.ingredient.isEmpty();
        entry2.finished = false;
    }

    @JvmStatic
    @Nullable
    public static final Entry get(@NotNull BlockPos pos) {
        Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
        return entries.get(pos);
    }

    @JvmStatic
    public static final float progress(@Nullable MinecraftClient mc, @Nullable Entry entry) {
        block7: {
            block6: {
                if (entry == null) break block6;
                MinecraftClient minecraftClient2 = mc;
                if ((minecraftClient2 != null ? minecraftClient2.world : null) != null) break block7;
            }
            return -1.0f;
        }
        if (entry.finished) {
            return 1.0f;
        }
        if (!entry.brewing) {
            return -1.0f;
        }
        float left = INSTANCE.ticksLeftNow(entry);
        if (left <= 0.0f) {
            INSTANCE.finish(mc, entry);
            return 1.0f;
        }
        return 1.0f - left / (float)400;
    }

    @JvmStatic
    public static final float secondsLeft(@Nullable MinecraftClient mc, @Nullable Entry entry) {
        if (entry == null || !entry.brewing || entry.finished) {
            return 0.0f;
        }
        return Math.max(0.0f, INSTANCE.ticksLeftNow(entry) / 20.0f);
    }

    private final float ticksLeftNow(Entry entry) {
        float elapsedTicks = (float)(System.currentTimeMillis() - entry.capturedAtMs) / 50.0f;
        return (float)entry.ticksLeft - elapsedTicks;
    }

    private final void finish(MinecraftClient mc, Entry entry) {
        entry.finished = true;
        entry.brewing = false;
        if (mc.world == null) {
            return;
        }
        BrewingRecipeRegistry brewing = mc.world.getBrewingRecipeRegistry();
        if (brewing == null) {
            return;
        }
        for (int i = 0; i < 3; ++i) {
            ItemStack bottle = entry.bottles[i];
            if (bottle.isEmpty() || entry.ingredient.isEmpty() || !brewing.hasRecipe(bottle, entry.ingredient)) continue;
            entry.bottles[i] = brewing.craft(entry.ingredient, bottle);
        }
        if (!entry.ingredient.isEmpty()) {
            entry.ingredient.decrement(1);
        }
    }

    @JvmStatic
    @NotNull
    public static final ItemStack previewResult(@Nullable MinecraftClient mc, @Nullable Entry entry, int slot) {
        if (entry == null || mc == null || mc.world == null) {
            return ItemStack.EMPTY;
        }
        ItemStack bottle = entry.bottles[slot];
        if (bottle.isEmpty() || entry.ingredient.isEmpty()) {
            return ItemStack.EMPTY;
        }
        BrewingRecipeRegistry brewing = mc.world.getBrewingRecipeRegistry();
        if (brewing == null || !brewing.hasRecipe(bottle, entry.ingredient)) {
            return ItemStack.EMPTY;
        }
        return brewing.craft(entry.ingredient, bottle);
    }

    @JvmStatic
    public static final void clear() {
        entries.clear();
        openPos = null;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006R$\u0010\u000b\u001a\r\u0012\t\u0012\u00070\b\u00a2\u0006\u0002\b\t0\u00078\u0006X\u0087\u0004\u0092\u0002\u0002\b\n\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u001b\u0010\r\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\n\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u001b\u0010\u000f\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\n\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\u00108\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\n\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\n\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0016\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\n\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0018\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\n\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0017\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewStandCache$Entry;", "", "<init>", "()V", "", "hasContent", "()Z", "", "Lnet/minecraft/ItemStack;", "Lkotlin/jvm/internal/EnhancedNullability;", "Lkotlin/jvm/JvmField;", "bottles", "[Lnet/minecraft/ItemStack;", "ingredient", "Lnet/minecraft/ItemStack;", "fuel", "", "ticksLeft", "I", "", "capturedAtMs", "J", "brewing", "Z", "finished", "rtx.kimiko:kimiko"})
    public static final class Entry {
        @JvmField
        @NotNull
        public final ItemStack[] bottles;
        @JvmField
        @NotNull
        public ItemStack ingredient;
        @JvmField
        @NotNull
        public ItemStack fuel;
        @JvmField
        public int ticksLeft;
        @JvmField
        public long capturedAtMs;
        @JvmField
        public boolean brewing;
        @JvmField
        public boolean finished;

        public Entry() {
            ItemStack[] class_1799Array = new ItemStack[]{ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY};
            this.bottles = class_1799Array;
            ItemStack itemStack2 = ItemStack.EMPTY;
            Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"EMPTY");
            this.ingredient = itemStack2;
            ItemStack itemStack3 = ItemStack.EMPTY;
            Intrinsics.checkNotNullExpressionValue((Object)itemStack3, (String)"EMPTY");
            this.fuel = itemStack3;
        }

        public final boolean hasContent() {
            for (ItemStack itemStack2 : this.bottles) {
                Intrinsics.checkNotNull((Object)itemStack2);
                ItemStack bottle = itemStack2;
                if (bottle.isEmpty()) continue;
                return true;
            }
            return !this.ingredient.isEmpty() || !this.fuel.isEmpty();
        }
    }
}

