package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;

/**
 * След ифрита: за огненными шарами тянется оранжевый огненный след,
 * а вокруг самих ифритов вьются жаркие пылинки.
 */
public class BlazeTrailModule extends Module {
    private static final DustParticleEffect FIRE = new DustParticleEffect(0xFFFF8C3A, 0.35f);

    private int tickCounter;

    public BlazeTrailModule() {
        super("Blaze Trail NEW", "Огненный след за шарами ифритов и пылинки вокруг самих ифритов.", Category.VISUALS);
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
            boolean fireball = entity instanceof SmallFireballEntity;
            boolean blaze = entity instanceof BlazeEntity;
            if ((!fireball && !blaze) || !entity.isAlive()) {
                continue;
            }
            Vec3d pos = entity.getLerpedPos(1.0f);
            double dx = pos.x - this.mc.player.getX();
            double dy = pos.y - this.mc.player.getY();
            double dz = pos.z - this.mc.player.getZ();
            if (dx * dx + dy * dy + dz * dz > 24.0 * 24.0) {
                continue;
            }
            if (fireball) {
                // след точно за огненным шаром
                world.addParticleClient(FIRE,
                        pos.x + (Math.random() - 0.5) * 0.1,
                        pos.y + (Math.random() - 0.5) * 0.1,
                        pos.z + (Math.random() - 0.5) * 0.1,
                        (Math.random() - 0.5) * 0.02, 0.01 + Math.random() * 0.02, (Math.random() - 0.5) * 0.02);
            } else {
                // жаркие пылинки вокруг тела ифрита
                double ang = Math.random() * Math.PI * 2.0;
                double r = 0.3 + Math.random() * 0.4;
                world.addParticleClient(FIRE,
                        pos.x + MathHelper.cos((float) ang) * r,
                        pos.y + 0.3 + Math.random() * 1.3,
                        pos.z + MathHelper.sin((float) ang) * r,
                        (Math.random() - 0.5) * 0.02, 0.02 + Math.random() * 0.03, (Math.random() - 0.5) * 0.02);
            }
        }
    }
}
