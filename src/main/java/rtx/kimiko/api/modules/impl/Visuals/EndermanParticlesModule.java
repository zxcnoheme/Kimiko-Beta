package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;

/**
 * Частицы эндермена: вокруг эндерменов вьются фиолетовые портальные
 * частицы, как будто они только что телепортировались.
 */
public class EndermanParticlesModule extends Module {
    private int tickCounter;

    public EndermanParticlesModule() {
        super("Enderman Particles NEW", "Портальные частицы вьются вокруг эндерменов.", Category.VISUALS);
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
            if (!(entity instanceof EndermanEntity enderman) || !enderman.isAlive()) {
                continue;
            }
            Vec3d pos = enderman.getLerpedPos(1.0f);
            double dx = pos.x - this.mc.player.getX();
            double dy = pos.y - this.mc.player.getY();
            double dz = pos.z - this.mc.player.getZ();
            if (dx * dx + dy * dy + dz * dz > 20.0 * 20.0) {
                continue;
            }
            for (int i = 0; i < 2; i++) {
                double ang = Math.random() * Math.PI * 2.0;
                double r = 0.3 + Math.random() * 0.5;
                world.addParticleClient(ParticleTypes.PORTAL,
                        pos.x + MathHelper.cos((float) ang) * r,
                        pos.y + 0.2 + Math.random() * 2.4,
                        pos.z + MathHelper.sin((float) ang) * r,
                        (Math.random() - 0.5) * 0.05, -0.02 - Math.random() * 0.03, (Math.random() - 0.5) * 0.05);
            }
        }
    }
}
