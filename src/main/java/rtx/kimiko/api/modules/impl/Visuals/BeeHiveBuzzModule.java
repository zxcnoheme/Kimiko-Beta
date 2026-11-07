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
 * Гул улья: вокруг пчелиных гнёзд и ульев кружится жёлтая пыльца —
 * домик пчёл «жужжит» даже без пчёл.
 */
public class BeeHiveBuzzModule extends Module {
    private static final DustParticleEffect POLLEN = new DustParticleEffect(0xFFFFD24C, 0.3f);

    private final SliderSetting density;
    private int tickCounter;

    public BeeHiveBuzzModule() {
        super("Bee Hive Buzz NEW", "Жёлтая пыльца кружит вокруг пчелиных гнёзд и ульев.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько пыльцы у улья.").setValue(1.0f).range(0.2f, 3.0f));
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
        if (++this.tickCounter % 12 != 0) {
            return;
        }
        int cap = MathHelper.clamp(Math.round(2.0f * this.density.getValue()), 1, 3);
        int found = 0;
        for (BlockPos pos : BlockPos.iterateOutwards(this.mc.player.getBlockPos(), 5, 3, 5)) {
            if (!world.getBlockState(pos).isOf(Blocks.BEEHIVE) && !world.getBlockState(pos).isOf(Blocks.BEE_NEST)) {
                continue;
            }
            int count = MathHelper.clamp(Math.round(2.0f * this.density.getValue()), 1, 4);
            for (int i = 0; i < count; i++) {
                double ang = Math.random() * Math.PI * 2.0;
                double r = 0.45 + Math.random() * 0.35;
                world.addParticleClient(POLLEN,
                        pos.getX() + 0.5 + MathHelper.cos(ang) * r,
                        pos.getY() + 0.4 + Math.random() * 0.6,
                        pos.getZ() + 0.5 + MathHelper.sin(ang) * r,
                        (Math.random() - 0.5) * 0.02,
                        -0.005 - Math.random() * 0.01,
                        (Math.random() - 0.5) * 0.02);
            }
            if (++found >= cap) {
                break;
            }
        }
    }
}
