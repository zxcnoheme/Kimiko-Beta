package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Падающие листья: под кронами деревьев медленно кружатся листья,
 * цвет подстраивается (сакура — розовые, лиственные — зелёные).
 */
public class FallingLeavesModule extends Module {
    private static final DustParticleEffect GREEN = new DustParticleEffect(0xFF5FA746, 0.5f);
    private static final DustParticleEffect PINK = new DustParticleEffect(0xFFF59BC0, 0.5f);
    private static final DustParticleEffect YELLOW = new DustParticleEffect(0xFFC9A83C, 0.5f);

    private final SliderSetting density;
    private int tickCounter;

    public FallingLeavesModule() {
        super("Falling Leaves", "Под кронами медленно падают листья, слегка сносимые ветром.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Как часто появляются листья.").setValue(1.0f).range(0.2f, 3.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null) {
            return;
        }
        if (++this.tickCounter % 5 != 0) {
            return;
        }
        int attempts = Math.round(this.density.getValue() * 2.0f);
        for (int i = 0; i < attempts; i++) {
            double x = this.mc.player.getX() + (Math.random() - 0.5) * 14.0;
            double z = this.mc.player.getZ() + (Math.random() - 0.5) * 14.0;
            int bx = MathHelper.floor(x);
            int bz = MathHelper.floor(z);
            int eyeY = MathHelper.floor(this.mc.player.getEyeY());
            // ищем листву в колонке над глазами
            for (int y = Math.min(eyeY + 8, world.getTopYInclusive() - 1); y >= eyeY - 4; y--) {
                BlockPos pos = new BlockPos(bx, y, bz);
                BlockState state = world.getBlockState(pos);
                if (state.isAir()) {
                    continue;
                }
                if (state.isIn(BlockTags.LEAVES)) {
                    DustParticleEffect fx = GREEN;
                    String biome = world.getBiome(pos).getKey().map(k -> k.getValue().getPath()).orElse("");
                    if (biome.contains("cherry")) {
                        fx = PINK;
                    } else if (biome.contains("savanna") || biome.contains("jungle")) {
                        fx = YELLOW;
                    }
                    world.addParticleClient(fx, x, y - 0.15, z,
                            (Math.random() - 0.5) * 0.06, -0.05 - Math.random() * 0.05, (Math.random() - 0.5) * 0.06);
                    break;
                }
                // упёрлись в твёрдый блок выше глаз — колонка закрыта кроной дальше не ищем
                break;
            }
        }
    }
}
