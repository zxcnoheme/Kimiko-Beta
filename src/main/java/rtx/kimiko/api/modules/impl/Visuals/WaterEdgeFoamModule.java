package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleTypes;
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
 * Пена на кромке воды: у берега, где вода встречается с сушей,
 * изредка всплескивают маленькие частицы-брызги.
 */
public class WaterEdgeFoamModule extends Module {
    private static final int SCAN_COLUMNS = 16;
    private static final float SCAN_RADIUS = 12.0f;

    private final SliderSetting density;
    private int tickCounter;

    public WaterEdgeFoamModule() {
        super("Water Foam NEW", "Мелкая пена и брызги на кромке воды у берега.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько брызг у кромки.").setValue(1.0f).range(0.5f, 3.0f));
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
        if (++this.tickCounter % 20 != 0) {
            return;
        }
        java.util.Random random = new java.util.Random(world.getTime() / 20);
        int count = Math.max(1, Math.round(this.density.getValue() * 2.0f));
        for (int i = 0; i < SCAN_COLUMNS; i++) {
            double x = this.mc.player.getX() + (random.nextDouble() - 0.5) * SCAN_RADIUS * 2.0;
            double z = this.mc.player.getZ() + (random.nextDouble() - 0.5) * SCAN_RADIUS * 2.0;
            int bx = MathHelper.floor(x);
            int bz = MathHelper.floor(z);
            int top = FirefliesModule.topY(world, bx, bz);
            if (top <= world.getBottomY() + 1) {
                continue;
            }
            BlockPos water = new BlockPos(bx, top - 1, bz);
            if (!world.getFluidState(water).isIn(FluidTags.WATER)) {
                continue;
            }
            if (!isEdge(world, bx, top - 1, bz)) {
                continue;
            }
            for (int p = 0; p < count; p++) {
                world.addParticleClient(ParticleTypes.SPLASH,
                        bx + 0.15 + random.nextDouble() * 0.7,
                        top - 0.35 + random.nextDouble() * 0.2,
                        bz + 0.15 + random.nextDouble() * 0.7,
                        (random.nextDouble() - 0.5) * 0.04, 0.08 + random.nextDouble() * 0.08,
                        (random.nextDouble() - 0.5) * 0.04);
            }
        }
    }

    /** Берег — хотя бы один из четырёх горизонтальных соседей не вода. */
    private static boolean isEdge(ClientWorld world, int bx, int wy, int bz) {
        return !world.getFluidState(new BlockPos(bx + 1, wy, bz)).isIn(FluidTags.WATER)
                || !world.getFluidState(new BlockPos(bx - 1, wy, bz)).isIn(FluidTags.WATER)
                || !world.getFluidState(new BlockPos(bx, wy, bz + 1)).isIn(FluidTags.WATER)
                || !world.getFluidState(new BlockPos(bx, wy, bz - 1)).isIn(FluidTags.WATER);
    }
}
