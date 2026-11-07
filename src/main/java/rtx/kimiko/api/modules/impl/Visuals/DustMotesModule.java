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
 * Пыль в лучах света: у факелов, фонарей и светящихся блоков в воздухе
 * висят и медленно плывут пылинки.
 */
public class DustMotesModule extends Module {
    private static final DustParticleEffect MOTE = new DustParticleEffect(0xFFF2E9D0, 0.32f);

    private final SliderSetting density;
    private int tickCounter;

    public DustMotesModule() {
        super("Light Dust NEW", "Пылинки, плывущие в воздухе у факелов, фонарей и светокамня.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько пылинок у одного источника.").setValue(1.0f).range(0.3f, 3.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null || ++this.tickCounter % 15 != 0) {
            return;
        }
        BlockPos center = this.mc.player.getBlockPos();
        for (BlockPos pos : BlockPos.iterateOutwards(center, 5, 3, 5)) {
            if (!isLightSource(world, pos)) {
                continue;
            }
            int count = Math.round(this.density.getValue());
            for (int i = 0; i < count; i++) {
                double x = pos.getX() + 0.5 + (Math.random() - 0.5) * 1.8;
                double y = pos.getY() + 0.2 + Math.random() * 0.9;
                double z = pos.getZ() + 0.5 + (Math.random() - 0.5) * 1.8;
                world.addParticleClient(MOTE, x, y, z,
                        (Math.random() - 0.5) * 0.015, 0.008 + Math.random() * 0.012, (Math.random() - 0.5) * 0.015);
            }
        }
    }

    private static boolean isLightSource(ClientWorld world, BlockPos pos) {
        return world.getBlockState(pos).isOf(Blocks.TORCH)
                || world.getBlockState(pos).isOf(Blocks.WALL_TORCH)
                || world.getBlockState(pos).isOf(Blocks.LANTERN)
                || world.getBlockState(pos).isOf(Blocks.SOUL_LANTERN)
                || world.getBlockState(pos).isOf(Blocks.GLOWSTONE)
                || world.getBlockState(pos).isOf(Blocks.SEA_LANTERN)
                || world.getBlockState(pos).isOf(Blocks.SHROOMLIGHT)
                || world.getBlockState(pos).isOf(Blocks.JACK_O_LANTERN)
                || world.getBlockState(pos).isOf(Blocks.END_ROD);
    }
}
