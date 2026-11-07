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
 * Дым костров: возле костра поднимается уютный длинный дым,
 * изредка — густые клубы.
 */
public class CampfireSmokeModule extends Module {
    private final SliderSetting density;
    private int tickCounter;

    public CampfireSmokeModule() {
        super("Campfire Smoke NEW", "Уютный длинный дым тянется вверх из костров рядом.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько костров дымит.").setValue(1.0f).range(0.2f, 3.0f));
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
        if (++this.tickCounter % 8 != 0) {
            return;
        }
        int cap = MathHelper.clamp(Math.round(2.0f * this.density.getValue()), 1, 4);
        int found = 0;
        for (BlockPos pos : BlockPos.iterateOutwards(this.mc.player.getBlockPos(), 6, 3, 6)) {
            if (!world.getBlockState(pos).isOf(Blocks.CAMPFIRE)) {
                continue;
            }
            double x = pos.getX() + 0.5 + (Math.random() - 0.5) * 0.3;
            double y = pos.getY() + 0.45 + Math.random() * 0.15;
            double z = pos.getZ() + 0.5 + (Math.random() - 0.5) * 0.3;
            if (Math.random() < 0.12f) {
                world.addParticleClient(ParticleTypes.LARGE_SMOKE, x, y, z,
                        (Math.random() - 0.5) * 0.01, 0.03 + Math.random() * 0.02, (Math.random() - 0.5) * 0.01);
            } else {
                world.addParticleClient(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z,
                        (Math.random() - 0.5) * 0.01, 0.02 + Math.random() * 0.02, (Math.random() - 0.5) * 0.01);
            }
            if (++found >= cap) {
                break;
            }
        }
    }
}
