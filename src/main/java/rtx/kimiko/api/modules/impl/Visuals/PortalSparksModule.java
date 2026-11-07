package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Искры порталов: стоя рядом с незер-порталом, видишь фиолетовые искры,
 * стекающие вдоль его рамки.
 */
public class PortalSparksModule extends Module {
    private final SliderSetting density;
    private int tickCounter;

    public PortalSparksModule() {
        super("Portal Sparks NEW", "У активных незер-порталов сыплются фиолетовые искры.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько искр появляется.").setValue(1.0f).range(0.2f, 3.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null || ++this.tickCounter % 10 != 0) {
            return;
        }
        BlockPos center = this.mc.player.getBlockPos();
        BlockPos found = null;
        for (BlockPos pos : BlockPos.iterateOutwards(center, 4, 3, 4)) {
            if (world.getBlockState(pos).isOf(Blocks.NETHER_PORTAL)) {
                found = pos.toImmutable();
                break;
            }
        }
        if (found == null) {
            return;
        }
        Vec3d portalCenter = Vec3d.ofCenter(found);
        int count = Math.round(this.density.getValue() * 3.0f);
        for (int i = 0; i < count; i++) {
            double x = portalCenter.x + (Math.random() - 0.5) * 1.6;
            double y = portalCenter.y + (Math.random() - 0.5) * 2.2;
            double z = portalCenter.z + (Math.random() - 0.5) * 1.6;
            world.addParticleClient(ParticleTypes.PORTAL, x, y, z,
                    (Math.random() - 0.5) * 0.08, -0.06 - Math.random() * 0.08, (Math.random() - 0.5) * 0.08);
        }
    }
}
