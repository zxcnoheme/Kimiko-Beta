package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Искры адского пламени: над душевным огнём и кострами всплывают
 * голубые пылинки.
 */
public class SoulFireSparksModule extends Module {
    private static final DustParticleEffect SPARK = new DustParticleEffect(0xFF59C8FF, 0.3f);

    private final SliderSetting density;
    private int tickCounter;

    public SoulFireSparksModule() {
        super("Soul Fire Sparks NEW", "Голубые искры всплывают над душевным огнём.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько искр у каждого огня.").setValue(1.0f).range(0.2f, 3.0f));
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
        if (++this.tickCounter % 10 != 0) {
            return;
        }
        int px = MathHelper.floor(this.mc.player.getX());
        int py = MathHelper.floor(this.mc.player.getY());
        int pz = MathHelper.floor(this.mc.player.getZ());
        for (int dx = -2; dx <= 2; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -2; dz <= 2; dz++) {
                    BlockPos pos = new BlockPos(px + dx, py + dy, pz + dz);
                    if (!world.getBlockState(pos).isOf(Blocks.SOUL_FIRE)
                            && !world.getBlockState(pos).isOf(Blocks.SOUL_CAMPFIRE)) {
                        continue;
                    }
                    int count = Math.max(1, (int) Math.round(1.0f + Math.random() * this.density.getValue()));
                    for (int i = 0; i < count; i++) {
                        world.addParticleClient(SPARK,
                                pos.getX() + 0.2 + Math.random() * 0.6,
                                pos.getY() + 1.0 + Math.random() * 0.3,
                                pos.getZ() + 0.2 + Math.random() * 0.6,
                                (Math.random() - 0.5) * 0.01, 0.03 + Math.random() * 0.05, (Math.random() - 0.5) * 0.01);
                    }
                }
            }
        }
    }
}
