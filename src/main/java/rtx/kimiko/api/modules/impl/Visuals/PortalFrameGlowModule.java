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
 * Свечение рамки портала: вдоль граней активного незер-портала
 * вспыхивают фиолетовые пылинки — рамка словно «дышит».
 */
public class PortalFrameGlowModule extends Module {
    private static final DustParticleEffect GLOW = new DustParticleEffect(0xFFB04CFF, 0.4f);

    private final SliderSetting density;
    private int tickCounter;

    public PortalFrameGlowModule() {
        super("Portal Frame Glow NEW", "Фиолетовые пылинки мерцают вдоль граней незер-портала.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько искр на блок портала.").setValue(1.0f).range(0.2f, 3.0f));
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
        for (BlockPos pos : BlockPos.iterateOutwards(this.mc.player.getBlockPos(), 5, 4, 5)) {
            if (!world.getBlockState(pos).isOf(Blocks.NETHER_PORTAL)) {
                continue;
            }
            int sparks = MathHelper.clamp(Math.round(2.0f * this.density.getValue()), 1, 4);
            for (int i = 0; i < sparks; i++) {
                // случайная точка на границе блока портала
                double lx = 0.05 + Math.random() * 0.9;
                double ly = 0.05 + Math.random() * 0.9;
                double lz = 0.05 + Math.random() * 0.9;
                int face = (int) (Math.random() * 6.0);
                if (face == 0) {
                    lx = 0.02;
                } else if (face == 1) {
                    lx = 0.98;
                } else if (face == 2) {
                    ly = 0.02;
                } else if (face == 3) {
                    ly = 0.98;
                } else if (face == 4) {
                    lz = 0.02;
                } else {
                    lz = 0.98;
                }
                world.addParticleClient(GLOW,
                        pos.getX() + lx,
                        pos.getY() + ly,
                        pos.getZ() + lz,
                        (Math.random() - 0.5) * 0.02,
                        (Math.random() - 0.5) * 0.02,
                        (Math.random() - 0.5) * 0.02);
            }
            if (++found >= cap) {
                break;
            }
        }
    }
}
