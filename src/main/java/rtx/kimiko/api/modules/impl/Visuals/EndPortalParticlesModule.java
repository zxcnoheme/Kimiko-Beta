package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.block.Blocks;
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
 * Частицы энд-портала: из плоскости портала в Энд поднимаются
 * фиолетовые «спутники» — портал кажется живым.
 */
public class EndPortalParticlesModule extends Module {
    private final SliderSetting density;
    private int tickCounter;

    public EndPortalParticlesModule() {
        super("End Portal FX NEW", "Фиолетовые частицы поднимаются из плоскости энд-портала.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько частиц на блок портала.").setValue(1.0f).range(0.2f, 3.0f));
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
        int cap = MathHelper.clamp(Math.round(2.0f * this.density.getValue()), 1, 4);
        int found = 0;
        for (BlockPos pos : BlockPos.iterateOutwards(this.mc.player.getBlockPos(), 5, 3, 5)) {
            if (!world.getBlockState(pos).isOf(Blocks.END_PORTAL)) {
                continue;
            }
            int count = MathHelper.clamp(Math.round(3.0f * this.density.getValue()), 1, 6);
            for (int i = 0; i < count; i++) {
                world.addParticleClient(ParticleTypes.PORTAL,
                        pos.getX() + 0.15 + Math.random() * 0.7,
                        pos.getY() + 0.15 + Math.random() * 0.7,
                        pos.getZ() + 0.15 + Math.random() * 0.7,
                        (Math.random() - 0.5) * 0.05,
                        0.05 + Math.random() * 0.08,
                        (Math.random() - 0.5) * 0.05);
            }
            if (++found >= cap) {
                break;
            }
        }
    }
}
