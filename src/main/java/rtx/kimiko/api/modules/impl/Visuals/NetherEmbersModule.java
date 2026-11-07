package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Угольки Незера: возле лавовых озёр в воздух поднимаются тлеющие угольки.
 */
public class NetherEmbersModule extends Module {
    private final SliderSetting density;
    private int tickCounter;

    public NetherEmbersModule() {
        super("Nether Embers NEW", "Тлеющие угольки поднимаются от лавы рядом с тобой (в Незере — всегда).", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько угольков появляется.").setValue(1.0f).range(0.3f, 3.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null || ++this.tickCounter % 7 != 0) {
            return;
        }
        boolean nether = "the_nether".equals(world.getRegistryKey().getValue().getPath());
        boolean raining = world.getRainGradient(1.0f) > 0.3f;
        if (!nether && !raining) {
            return;
        }
        BlockPos center = this.mc.player.getBlockPos();
        int found = 0;
        int max = Math.round(this.density.getValue() * 2.0f);
        for (BlockPos pos : BlockPos.iterateOutwards(center, 7, 2, 7)) {
            if (found >= max) {
                break;
            }
            boolean lava = world.getBlockState(pos).isOf(Blocks.LAVA);
            if (!lava) {
                continue;
            }
            BlockPos above = pos.up();
            if (!world.getBlockState(above).isAir()) {
                continue;
            }
            world.addParticleClient(ParticleTypes.SMALL_FLAME,
                    pos.getX() + 0.2 + Math.random() * 0.6,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.2 + Math.random() * 0.6,
                    (Math.random() - 0.5) * 0.02, 0.03 + Math.random() * 0.04, (Math.random() - 0.5) * 0.02);
            found++;
        }
    }
}
