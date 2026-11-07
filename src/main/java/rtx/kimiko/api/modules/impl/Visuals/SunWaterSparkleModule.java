package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Солнечные искры: днём в ясную погоду над поверхностью воды
 * мерцают золотые пылинки.
 */
public class SunWaterSparkleModule extends Module {
    private static final DustParticleEffect SPARKLE = new DustParticleEffect(0xFFFFD98A, 0.4f);
    private static final int SCAN_COLUMNS = 8;
    private static final float SCAN_RADIUS = 14.0f;

    private final SliderSetting density;
    private int tickCounter;

    public SunWaterSparkleModule() {
        super("Sun Sparkle NEW", "Золотые мерцающие искры над водой днём.", Category.VISUALS);
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
        if (world.getAmbientDarkness() >= 2) {
            return;
        }
        if (world.getRainGradient(1.0f) >= 0.3f) {
            return;
        }
        if (++this.tickCounter % 6 != 0) {
            return;
        }
        java.util.Random random = new java.util.Random(world.getTime() / 6);
        int count = Math.max(1, Math.round(this.density.getValue()));
        for (int i = 0; i < SCAN_COLUMNS; i++) {
            double x = this.mc.player.getX() + (random.nextDouble() - 0.5) * SCAN_RADIUS * 2.0;
            double z = this.mc.player.getZ() + (random.nextDouble() - 0.5) * SCAN_RADIUS * 2.0;
            int bx = MathHelper.floor(x);
            int bz = MathHelper.floor(z);
            int top = FirefliesModule.topY(world, bx, bz);
            if (top <= world.getBottomY() + 1) {
                continue;
            }
            if (!world.getFluidState(new BlockPos(bx, top - 1, bz)).isIn(FluidTags.WATER)) {
                continue;
            }
            for (int p = 0; p < count; p++) {
                world.addParticleClient(SPARKLE,
                        bx + 0.1 + random.nextDouble() * 0.8,
                        top + 0.05 + random.nextDouble() * 0.15,
                        bz + 0.1 + random.nextDouble() * 0.8,
                        0.0, 0.0, 0.0);
            }
        }
    }
}
