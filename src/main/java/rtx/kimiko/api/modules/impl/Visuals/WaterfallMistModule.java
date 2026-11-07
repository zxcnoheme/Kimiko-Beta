package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Туман водопада: рядом с текучей (падающей) водой поднимаются белые
 * облачные частицы — водяная пыль от водопада.
 */
public class WaterfallMistModule extends Module {
    private final SliderSetting density;
    private int tickCounter;

    public WaterfallMistModule() {
        super("Waterfall Mist NEW", "Водяной туман-облачка у текучей воды и водопадов.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько облачков появляется.").setValue(1.0f).range(0.2f, 3.0f));
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
        if (++this.tickCounter % 10 != 0) {
            return;
        }
        BlockPos base = BlockPos.ofFloored(this.mc.player.getX(), this.mc.player.getY(), this.mc.player.getZ());
        int count = Math.max(1, Math.round(2.0f * this.density.getValue()));
        int spawned = 0;
        // скан 6x4x6 (144 итерации) вокруг игрока
        for (int dx = -3; dx < 3 && spawned < count; dx++) {
            for (int dy = 0; dy < 4 && spawned < count; dy++) {
                for (int dz = -3; dz < 3 && spawned < count; dz++) {
                    BlockPos pos = base.add(dx, dy, dz);
                    if (!world.getFluidState(pos).isIn(FluidTags.WATER) || world.getFluidState(pos).isStill()) {
                        continue;
                    }
                    world.addParticleClient(ParticleTypes.CLOUD,
                            pos.getX() + 0.2 + Math.random() * 0.6,
                            pos.getY() + 0.4 + Math.random() * 0.5,
                            pos.getZ() + 0.2 + Math.random() * 0.6,
                            (Math.random() - 0.5) * 0.05, 0.01 + Math.random() * 0.03, (Math.random() - 0.5) * 0.05);
                    spawned++;
                }
            }
        }
    }
}
