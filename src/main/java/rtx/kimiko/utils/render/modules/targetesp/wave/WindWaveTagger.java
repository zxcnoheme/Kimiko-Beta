/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.SetsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.BlockRenderLayer
 *  net.minecraft.world.BlockRenderView
 *  net.minecraft.block.Blocks
 *  net.minecraft.block.Block
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.block.BlockState
 *  net.minecraft.registry.tag.BlockTags
 *  net.minecraft.util.math.ChunkSectionPos
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.BlockRenderLayers
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.modules.targetesp.wave;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.world.BlockRenderView;
import net.minecraft.block.Blocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.BlockRenderLayers;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001'B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J3\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eJ;\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000fH\u0007b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0016R\u0014\u0010\u001a\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0016R\u0014\u0010\u001b\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0016R\u0014\u0010\u001c\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0016R\u0014\u0010\u001d\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u0016R\u0014\u0010\u001e\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u0016R\u0014\u0010\u001f\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u0016R\u0014\u0010 \u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u0016R\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u001a\u0010%\u001a\b\u0012\u0004\u0012\u00020\"0!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010$R\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020\"0!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010$\u00a8\u0006("}, d2={"Lrtx/kimiko/utils/render/modules/targetesp/wave/WindWaveTagger;", "", "<init>", "()V", "Lnet/minecraft/VertexConsumer;", "delegate", "Lnet/minecraft/BlockState;", "state", "Lnet/minecraft/BlockPos;", "pos", "Lnet/minecraft/BlockRenderView;", "level", "Lkotlin/jvm/JvmStatic;", "wrap", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/BlockState;Lnet/minecraft/BlockPos;Lnet/minecraft/BlockRenderView;)Lnet/minecraft/VertexConsumer;", "Lnet/minecraft/BlockRenderLayer;", "layer", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/BlockState;Lnet/minecraft/BlockPos;Lnet/minecraft/BlockRenderView;Lnet/minecraft/BlockRenderLayer;)Lnet/minecraft/VertexConsumer;", "", "kindOf", "(Lnet/minecraft/BlockState;)I", "TAG_PLANT", "I", "TAG_LEAF", "TAG_VINE", "KIND_NONE", "KIND_GROUND", "KIND_LEAF", "KIND_HANGING", "KIND_VINE", "ANCHOR_NONE", "ANCHOR_BOTTOM", "ANCHOR_TOP", "", "Lnet/minecraft/Block;", "GROUND_BLOCKS", "Ljava/util/Set;", "HANGING_BLOCKS", "EXCLUDED_BLOCKS", "TaggingConsumer", "rtx.kimiko:kimiko"})
public final class WindWaveTagger {
    @NotNull
    public static final WindWaveTagger INSTANCE = new WindWaveTagger();
    private static final int TAG_PLANT = 254;
    private static final int TAG_LEAF = 253;
    private static final int TAG_VINE = 252;
    private static final int KIND_NONE = 0;
    private static final int KIND_GROUND = 1;
    private static final int KIND_LEAF = 2;
    private static final int KIND_HANGING = 3;
    private static final int KIND_VINE = 4;
    private static final int ANCHOR_NONE = 0;
    private static final int ANCHOR_BOTTOM = 1;
    private static final int ANCHOR_TOP = 2;
    @NotNull
    private static final Set<Block> GROUND_BLOCKS;
    @NotNull
    private static final Set<Block> HANGING_BLOCKS;
    @NotNull
    private static final Set<Block> EXCLUDED_BLOCKS;

    private WindWaveTagger() {
    }

    @JvmStatic
    @NotNull
    public static final VertexConsumer wrap(@NotNull VertexConsumer delegate, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull BlockRenderView level) {
        Intrinsics.checkNotNullParameter((Object)delegate, (String)"delegate");
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
        Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
        Intrinsics.checkNotNullParameter((Object)level, (String)"level");
        BlockRenderLayer blockRenderLayer2 = BlockRenderLayers.getBlockLayer((BlockState)state);
        Intrinsics.checkNotNullExpressionValue((Object)blockRenderLayer2, (String)"getChunkRenderType(...)");
        return WindWaveTagger.wrap(delegate, state, pos, level, blockRenderLayer2);
    }

    @JvmStatic
    @NotNull
    public static final VertexConsumer wrap(@NotNull VertexConsumer delegate, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull BlockRenderView level, @NotNull BlockRenderLayer layer) {
        Intrinsics.checkNotNullParameter((Object)delegate, (String)"delegate");
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
        Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
        Intrinsics.checkNotNullParameter((Object)level, (String)"level");
        Intrinsics.checkNotNullParameter((Object)layer, (String)"layer");
        if (layer != BlockRenderLayer.SOLID && layer != BlockRenderLayer.CUTOUT) {
            return delegate;
        }
        int kind = INSTANCE.kindOf(state);
        if (kind == 0) {
            return delegate;
        }
        float blockY = ChunkSectionPos.getLocalCoord((int)pos.getY());
        if (kind == 2) {
            return new TaggingConsumer(delegate, 253, 0, blockY);
        }
        if (kind == 4) {
            return new TaggingConsumer(delegate, 252, 0, blockY);
        }
        if (kind == 1) {
            BlockState blockState2 = level.getBlockState(pos.down());
            Intrinsics.checkNotNullExpressionValue((Object)blockState2, (String)"getBlockState(...)");
            boolean anchored = INSTANCE.kindOf(blockState2) != 1;
            return new TaggingConsumer(delegate, 254, anchored ? 1 : 0, blockY);
        }
        BlockState blockState3 = level.getBlockState(pos.up());
        Intrinsics.checkNotNullExpressionValue((Object)blockState3, (String)"getBlockState(...)");
        boolean anchored = INSTANCE.kindOf(blockState3) != 3;
        return new TaggingConsumer(delegate, 254, anchored ? 2 : 0, blockY);
    }

    private final int kindOf(BlockState state) {
        Block block2 = state.getBlock();
        Intrinsics.checkNotNullExpressionValue((Object)block2, (String)"getBlock(...)");
        Block block = block2;
        if (EXCLUDED_BLOCKS.contains(block)) {
            return 0;
        }
        if (state.isIn(BlockTags.LEAVES)) {
            return 2;
        }
        if (block == Blocks.VINE) {
            return 4;
        }
        if (HANGING_BLOCKS.contains(block)) {
            return 3;
        }
        if (GROUND_BLOCKS.contains(block)) {
            return 1;
        }
        if (state.isIn(BlockTags.SMALL_FLOWERS) || state.isIn(BlockTags.CROPS) || state.isIn(BlockTags.SAPLINGS) || state.isIn(BlockTags.FLOWERS)) {
            return 1;
        }
        return 0;
    }

    static {
        GROUND_BLOCKS = Set.of(Blocks.SHORT_GRASS, Blocks.TALL_GRASS, Blocks.FERN, Blocks.LARGE_FERN, Blocks.DEAD_BUSH, Blocks.BUSH, Blocks.SHORT_DRY_GRASS, Blocks.TALL_DRY_GRASS, Blocks.FIREFLY_BUSH, Blocks.SUGAR_CANE, Blocks.BAMBOO, Blocks.BAMBOO_SAPLING, Blocks.SEAGRASS, Blocks.TALL_SEAGRASS, Blocks.KELP, Blocks.KELP_PLANT, Blocks.SWEET_BERRY_BUSH, Blocks.NETHER_WART, Blocks.CRIMSON_ROOTS, Blocks.WARPED_ROOTS, Blocks.NETHER_SPROUTS, Blocks.CRIMSON_FUNGUS, Blocks.WARPED_FUNGUS, Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM, Blocks.TWISTING_VINES, Blocks.TWISTING_VINES_PLANT, Blocks.SMALL_DRIPLEAF, Blocks.MELON_STEM, Blocks.PUMPKIN_STEM, Blocks.ATTACHED_MELON_STEM, Blocks.ATTACHED_PUMPKIN_STEM, Blocks.TORCHFLOWER_CROP, Blocks.PITCHER_CROP, Blocks.PITCHER_PLANT, Blocks.SUNFLOWER, Blocks.LILAC, Blocks.ROSE_BUSH, Blocks.PEONY);
        HANGING_BLOCKS = Set.of(Blocks.WEEPING_VINES, Blocks.WEEPING_VINES_PLANT, Blocks.CAVE_VINES, Blocks.CAVE_VINES_PLANT, Blocks.HANGING_ROOTS, Blocks.SPORE_BLOSSOM, Blocks.PALE_HANGING_MOSS);
        EXCLUDED_BLOCKS = Set.of(Blocks.MANGROVE_PROPAGULE, Blocks.CHORUS_FLOWER, Blocks.AZALEA, Blocks.FLOWERING_AZALEA);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ'\u0010\r\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ/\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0016J\u001f\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001cJ'\u0010\u001e\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u001e\u0010\u000eJ\u0017\u0010 \u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b \u0010!R\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0002\u0010\"R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010#R\u0014\u0010\u0005\u001a\u00020\u00038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010#R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010$R\u0016\u0010&\u001a\u00020%8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'\u00a8\u0006("}, d2={"Lrtx/kimiko/utils/render/modules/targetesp/wave/WindWaveTagger$TaggingConsumer;", "Lnet/minecraft/VertexConsumer;", "delegate", "", "waveAlpha", "anchorMode", "", "blockY", "<init>", "(Lnet/minecraft/VertexConsumer;IIF)V", "x", "y", "z", "addVertex", "(FFF)Lnet/minecraft/VertexConsumer;", "r", "g", "b", "a", "setColor", "(IIII)Lnet/minecraft/VertexConsumer;", "color", "(I)Lnet/minecraft/VertexConsumer;", "u", "v", "setUv", "(FF)Lnet/minecraft/VertexConsumer;", "setUv1", "(II)Lnet/minecraft/VertexConsumer;", "setUv2", "setNormal", "width", "setLineWidth", "(F)Lnet/minecraft/VertexConsumer;", "Lnet/minecraft/VertexConsumer;", "I", "F", "", "waveCurrent", "Z", "rtx.kimiko:kimiko"})
    private static final class TaggingConsumer
    implements VertexConsumer {
        @NotNull
        private final VertexConsumer delegate;
        private final int waveAlpha;
        private final int anchorMode;
        private final float blockY;
        private boolean waveCurrent;

        public TaggingConsumer(@NotNull VertexConsumer delegate, int waveAlpha, int anchorMode, float blockY) {
            Intrinsics.checkNotNullParameter((Object)delegate, (String)"delegate");
            this.delegate = delegate;
            this.waveAlpha = waveAlpha;
            this.anchorMode = anchorMode;
            this.blockY = blockY;
        }

        @NotNull
        public VertexConsumer vertex(float x, float y, float z) {
            float local = y - this.blockY;
            this.waveCurrent = switch (this.anchorMode) {
                case 1 -> {
                    if (local >= 0.5f) {
                        yield true;
                    }
                    yield false;
                }
                case 2 -> {
                    if (local <= 0.5f) {
                        yield true;
                    }
                    yield false;
                }
                default -> true;
            };
            this.delegate.vertex(x, y, z);
            return this;
        }

        @NotNull
        public VertexConsumer color(int r, int g, int b, int a) {
            this.delegate.color(r, g, b, this.waveCurrent ? this.waveAlpha : a);
            return this;
        }

        @NotNull
        public VertexConsumer color(int color) {
            this.delegate.color(this.waveCurrent ? color & 0xFFFFFF | this.waveAlpha << 24 : color);
            return this;
        }

        @NotNull
        public VertexConsumer texture(float u, float v) {
            this.delegate.texture(u, v);
            return this;
        }

        @NotNull
        public VertexConsumer overlay(int u, int v) {
            this.delegate.overlay(u, v);
            return this;
        }

        @NotNull
        public VertexConsumer light(int u, int v) {
            this.delegate.light(u, v);
            return this;
        }

        @NotNull
        public VertexConsumer normal(float x, float y, float z) {
            this.delegate.normal(x, y, z);
            return this;
        }

        @NotNull
        public VertexConsumer lineWidth(float width) {
            this.delegate.lineWidth(width);
            return this;
        }
    }
}

