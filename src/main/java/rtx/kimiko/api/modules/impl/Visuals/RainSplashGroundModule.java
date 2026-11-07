package rtx.kimiko.api.modules.impl.Visuals;

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
 * Rain Splashes: во время дождя у земли вокруг игрока вспыхивают маленькие
 * брызги — только на твёрдых колонках, водная поверхность пропускается.
 */
public class RainSplashGroundModule extends Module {
    private final SliderSetting density;
    private int tickCounter;

    public RainSplashGroundModule() {
        super("Rain Splashes NEW", "Брызги дождя о землю вокруг игрока во время дождя.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько брызг появляется.").setValue(1.0f).range(0.3f, 2.5f));
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
        if (++this.tickCounter % 3 != 0) {
            return;
        }
        int count = Math.round((4.0f + (float) (Math.random() * 3.0)) * this.density.getValue());
        for (int i = 0; i < count; i++) {
            double x = this.mc.player.getX() + (Math.random() - 0.5) * 20.0;
            double z = this.mc.player.getZ() + (Math.random() - 0.5) * 20.0;
            int fx = MathHelper.floor(x);
            int fz = MathHelper.floor(z);
            int top = FirefliesModule.topY(world, fx, fz);
            if (top <= world.getBottomY() + 1) {
                continue;
            }
            // колонка с водой — там брызги уже делает ваниль, пропускаем
            BlockPos below = new BlockPos(fx, top - 1, fz);
            if (!world.getFluidState(below).isEmpty()) {
                continue;
            }
            world.addParticleClient(ParticleTypes.SPLASH, x, top + 0.1, z,
                    (Math.random() - 0.5) * 0.08, 0.05 + Math.random() * 0.1, (Math.random() - 0.5) * 0.08);
        }
    }
}
