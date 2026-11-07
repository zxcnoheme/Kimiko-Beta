package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.block.BlockState;
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
 * Волны травы: над травой рядом с игроком поднимаются зелёные пылинки,
 * которые качаются синусоидой по ветру — трава «волнуется».
 */
public class GrassWaveLiteModule extends Module {
    private static final DustParticleEffect BLADE = new DustParticleEffect(0xFF6CBF4C, 0.25f);

    private final SliderSetting density;
    private int tickCounter;

    public GrassWaveLiteModule() {
        super("Grass Waves NEW", "Зелёные пылинки над травой качаются волной — трава волнуется на ветру.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько точек проверяется.").setValue(1.0f).range(0.2f, 3.0f));
        this.tickCounter = 0;
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
        int attempts = MathHelper.clamp(Math.round(4.0f * this.density.getValue()), 1, 16);
        long time = world.getTime();
        double px = this.mc.player.getX();
        double py = this.mc.player.getY();
        double pz = this.mc.player.getZ();
        int feetY = MathHelper.floor(py);

        for (int i = 0; i < attempts; i++) {
            float ang = (float) (Math.random() * Math.PI * 2.0);
            float dist = 2.0f + (float) Math.random() * 8.0f;
            int x = MathHelper.floor(px + MathHelper.cos(ang) * dist);
            int z = MathHelper.floor(pz + MathHelper.sin(ang) * dist);
            for (int dy = 0; dy >= -1; dy--) {
                int y = feetY + dy;
                if (y < world.getBottomY()) {
                    break;
                }
                BlockPos pos = new BlockPos(x, y, z);
                BlockState state = world.getBlockState(pos);
                boolean shortGrass = state.isOf(Blocks.SHORT_GRASS);
                boolean tallGrass = state.isOf(Blocks.TALL_GRASS);
                if (!shortGrass && !tallGrass) {
                    continue;
                }
                double top = shortGrass ? 0.55 + Math.random() * 0.35 : 1.1 + Math.random() * 0.7;
                double wx = x + 0.2 + Math.random() * 0.6;
                double wz = z + 0.2 + Math.random() * 0.6;
                // скорость по волне: качание по синусоиде от времени и позиции
                double vx = Math.sin(time * 0.002 + x * 0.5) * 0.06;
                world.addParticleClient(BLADE, wx, y + top, wz,
                        vx, 0.01 + Math.random() * 0.02, (Math.random() - 0.5) * 0.02);
                break;
            }
        }
    }
}
