package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Искры костра: рядом с обычными и адскими кострами вверх
 * взлетают маленькие язычки пламени.
 */
public class CampfireSparksModule extends Module {
    private static final int SCAN_XZ = 2;
    private static final int SCAN_Y = 1;

    private final SliderSetting density;
    private int tickCounter;

    public CampfireSparksModule() {
        super("Campfire Sparks NEW", "Искры и язычки пламени над кострами рядом.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько искр появляется.").setValue(1.0f).range(0.5f, 3.0f));
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
        if (++this.tickCounter % 12 != 0) {
            return;
        }
        int px = MathHelper.floor(this.mc.player.getX());
        int py = MathHelper.floor(this.mc.player.getY());
        int pz = MathHelper.floor(this.mc.player.getZ());
        java.util.Random random = new java.util.Random(world.getTime() / 12);
        for (int dx = -SCAN_XZ; dx <= SCAN_XZ; dx++) {
            for (int dy = -SCAN_Y; dy <= SCAN_Y; dy++) {
                for (int dz = -SCAN_XZ; dz <= SCAN_XZ; dz++) {
                    BlockPos pos = new BlockPos(px + dx, py + dy, pz + dz);
                    BlockState state = world.getBlockState(pos);
                    if (!state.isOf(Blocks.CAMPFIRE) && !state.isOf(Blocks.SOUL_CAMPFIRE)) {
                        continue;
                    }
                    int count = Math.max(1, Math.round(this.density.getValue() * (1.0f + (random.nextFloat() < 0.5f ? 1.0f : 0.0f))));
                    for (int i = 0; i < count; i++) {
                        world.addParticleClient(ParticleTypes.SMALL_FLAME,
                                pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.4,
                                pos.getY() + 0.6,
                                pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.4,
                                (random.nextDouble() - 0.5) * 0.02, 0.05 + random.nextDouble() * 0.05,
                                (random.nextDouble() - 0.5) * 0.02);
                    }
                }
            }
        }
    }
}
