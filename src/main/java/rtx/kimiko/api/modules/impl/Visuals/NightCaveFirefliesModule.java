package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Глубинные светлячки-глобивормы: очень глубоко под землёй в воздухе
 * неподвижно мерцают бирюзовые пылинки — как светящиеся червячки в пещерах.
 */
public class NightCaveFirefliesModule extends Module {
    private static final DustParticleEffect GLOWWORM = new DustParticleEffect(0xFF4FF0D0, 0.3f);

    private final SliderSetting density;
    private int tickCounter;

    public NightCaveFirefliesModule() {
        super("Cave Glowworms NEW", "Бирюзовые светящиеся червячки глубоко под землёй.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько червячков появляется.").setValue(1.0f).range(0.2f, 3.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null || this.mc.player.getY() >= 30.0) {
            return;
        }
        if (++this.tickCounter % 12 != 0) {
            return;
        }
        int count = Math.max(1, Math.round(2.0f * this.density.getValue()));
        for (int i = 0; i < count; i++) {
            double ang = Math.random() * Math.PI * 2.0;
            double dist = 1.5 + Math.random() * 4.5;
            double x = this.mc.player.getX() + Math.cos(ang) * dist;
            double z = this.mc.player.getZ() + Math.sin(ang) * dist;
            double y = this.mc.player.getY() + (Math.random() - 0.5) * 5.0;
            BlockPos pos = BlockPos.ofFloored(x, y, z);
            if (!world.getBlockState(pos).isAir()) {
                continue;
            }
            // статичные пылинки: почти нулевая скорость
            world.addParticleClient(GLOWWORM, x, y, z, 0.0, 0.0, 0.0);
        }
    }
}
