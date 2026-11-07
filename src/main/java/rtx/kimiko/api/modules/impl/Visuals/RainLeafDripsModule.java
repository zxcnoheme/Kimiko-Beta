package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleTypes;
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
 * Капли с листвы: в дождь с нижней стороны крон деревьев
 * срываются капли и падают вниз.
 */
public class RainLeafDripsModule extends Module {
    private final SliderSetting density;
    private int tickCounter;

    public RainLeafDripsModule() {
        super("Rain Leaf Drips NEW", "Капли срываются с листвы во время дождя.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько капель за раз.").setValue(1.0f).range(0.2f, 3.0f));
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
        if (world.getRainGradient(1.0f) <= 0.5f) {
            return;
        }
        if (++this.tickCounter % 8 != 0) {
            return;
        }
        int columns = Math.round(4.0f * this.density.getValue());
        for (int i = 0; i < columns; i++) {
            float ang = (float) (Math.random() * Math.PI * 2.0);
            float dist = 1.0f + (float) Math.random() * 9.0f;
            double x = this.mc.player.getX() + MathHelper.cos(ang) * dist;
            double z = this.mc.player.getZ() + MathHelper.sin(ang) * dist;
            int bx = MathHelper.floor(x);
            int bz = MathHelper.floor(z);
            int top = FirefliesModule.topY(world, bx, bz);
            if (top <= world.getBottomY() + 1) {
                continue;
            }
            BlockPos leavesPos = new BlockPos(bx, top - 1, bz);
            if (!world.getBlockState(leavesPos).isIn(BlockTags.LEAVES)) {
                continue;
            }
            world.addParticleClient(ParticleTypes.SPLASH,
                    x, leavesPos.getY() - 0.15, z,
                    (Math.random() - 0.5) * 0.03, -0.2 - Math.random() * 0.1, (Math.random() - 0.5) * 0.03);
        }
    }
}
