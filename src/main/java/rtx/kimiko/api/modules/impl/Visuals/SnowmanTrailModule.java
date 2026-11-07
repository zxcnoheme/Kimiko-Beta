package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;

/**
 * След снежного голема: за идущим снеговиком остаются белые снежные
 * пылинки, как будто он осыпается снегом.
 */
public class SnowmanTrailModule extends Module {
    private static final DustParticleEffect SNOW = new DustParticleEffect(0xFFEFF6FF, 0.3f);

    private int tickCounter;

    public SnowmanTrailModule() {
        super("Snow Golem Trail NEW", "Снежные пылинки осыпаются со снежных големов.", Category.VISUALS);
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
        if (++this.tickCounter % 4 != 0) {
            return;
        }
        for (Entity entity : world.getEntities()) {
            if (!(entity instanceof SnowGolemEntity golem) || !golem.isAlive()) {
                continue;
            }
            Vec3d pos = golem.getLerpedPos(1.0f);
            double dx = pos.x - this.mc.player.getX();
            double dy = pos.y - this.mc.player.getY();
            double dz = pos.z - this.mc.player.getZ();
            if (dx * dx + dy * dy + dz * dz > 16.0 * 16.0) {
                continue;
            }
            double ang = Math.random() * Math.PI * 2.0;
            double r = 0.1 + Math.random() * 0.3;
            world.addParticleClient(SNOW,
                    pos.x + MathHelper.cos((float) ang) * r,
                    pos.y + 0.1 + Math.random() * 1.2,
                    pos.z + MathHelper.sin((float) ang) * r,
                    (Math.random() - 0.5) * 0.02, -0.01 - Math.random() * 0.02, (Math.random() - 0.5) * 0.02);
        }
    }
}
