package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Пещерные светлячки: глубоко под землёй над лужами лавы парят тёплые
 * янтарные светлячки — жар от лавы делает их заметными в темноте.
 */
public class CaveFirefliesModule extends Module {
    private static final DustParticleEffect WARM = new DustParticleEffect(0xFFFFB25E, 0.35f);

    private final SliderSetting density;
    private int tickCounter;

    public CaveFirefliesModule() {
        super("Cave Fireflies NEW", "Тёплые светлячки парят над лавой глубоко под землёй.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько светляков появляется у лавы.").setValue(1.0f).range(0.2f, 3.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null || this.mc.player.getY() >= 40.0) {
            return;
        }
        if (++this.tickCounter % 3 != 0) {
            return;
        }
        BlockPos base = BlockPos.ofFloored(this.mc.player.getX(), this.mc.player.getY(), this.mc.player.getZ());
        int count = Math.max(1, Math.round(this.density.getValue()));
        int spawned = 0;
        // скан 6x2x6 (147 итераций) вокруг игрока
        for (int dx = -3; dx <= 3 && spawned < count; dx++) {
            for (int dy = -1; dy <= 1 && spawned < count; dy++) {
                for (int dz = -3; dz <= 3 && spawned < count; dz++) {
                    BlockPos pos = base.add(dx, dy, dz);
                    if (!world.getFluidState(pos).isIn(FluidTags.LAVA)) {
                        continue;
                    }
                    double lx = pos.getX() + 0.15 + Math.random() * 0.7;
                    double ly = pos.getY() + 1.05 + Math.random() * 0.5;
                    double lz = pos.getZ() + 0.15 + Math.random() * 0.7;
                    world.addParticleClient(WARM, lx, ly, lz,
                            (Math.random() - 0.5) * 0.015, 0.005 + Math.random() * 0.015, (Math.random() - 0.5) * 0.015);
                    spawned++;
                }
            }
        }
    }
}
