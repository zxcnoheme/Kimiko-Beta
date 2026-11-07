package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;

/**
 * След снежка: за летящим снежком тянется короткий белый снежный след.
 */
public class SnowballTrailModule extends Module {
    private static final DustParticleEffect SNOW = new DustParticleEffect(0xFFFFFFFF, 0.3f);

    private int tickCounter;

    public SnowballTrailModule() {
        super("Snowball Trail NEW", "Белый снежный след за летящими снежками.", Category.VISUALS);
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
            if (!(entity instanceof SnowballEntity ball) || !ball.isAlive()) {
                continue;
            }
            Vec3d pos = ball.getLerpedPos(1.0f);
            double dx = pos.x - this.mc.player.getX();
            double dy = pos.y - this.mc.player.getY();
            double dz = pos.z - this.mc.player.getZ();
            if (dx * dx + dy * dy + dz * dz > 24.0 * 24.0) {
                continue;
            }
            world.addParticleClient(SNOW,
                    pos.x + (Math.random() - 0.5) * 0.1,
                    pos.y + (Math.random() - 0.5) * 0.1,
                    pos.z + (Math.random() - 0.5) * 0.1,
                    (Math.random() - 0.5) * 0.01, -0.005, (Math.random() - 0.5) * 0.01);
        }
    }
}
