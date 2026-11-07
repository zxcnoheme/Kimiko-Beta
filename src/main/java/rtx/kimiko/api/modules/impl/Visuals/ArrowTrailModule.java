package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;

/**
 * След стрелы: за летящими стрелами и трезубцами остаётся лёгкий
 * серо-белый след из пылинок.
 */
public class ArrowTrailModule extends Module {
    private static final DustParticleEffect TRAIL = new DustParticleEffect(0xFFE4E7EC, 0.3f);

    private int tickCounter;

    public ArrowTrailModule() {
        super("Arrow Trail NEW", "Лёгкий серо-белый след за летящими стрелами и трезубцами.", Category.VISUALS);
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
        if (++this.tickCounter % 2 != 0) {
            return;
        }
        for (Entity entity : world.getEntities()) {
            boolean arrow = entity instanceof ArrowEntity;
            boolean trident = entity instanceof TridentEntity;
            if ((!arrow && !trident) || !entity.isAlive()) {
                continue;
            }
            Vec3d pos = entity.getLerpedPos(1.0f);
            double dx = pos.x - this.mc.player.getX();
            double dy = pos.y - this.mc.player.getY();
            double dz = pos.z - this.mc.player.getZ();
            if (dx * dx + dy * dy + dz * dz > 32.0 * 32.0) {
                continue;
            }
            world.addParticleClient(TRAIL,
                    pos.x + (Math.random() - 0.5) * 0.1,
                    pos.y + (Math.random() - 0.5) * 0.1,
                    pos.z + (Math.random() - 0.5) * 0.1,
                    (Math.random() - 0.5) * 0.01, -0.004, (Math.random() - 0.5) * 0.01);
        }
    }
}
