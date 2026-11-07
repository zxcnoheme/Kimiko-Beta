package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;

/**
 * Пузыри ведьмы: вокруг ведьм поднимаются фиолетовые зельеваренные
 * пузырьки-пылинки.
 */
public class WitchPurpleModule extends Module {
    private static final DustParticleEffect PURPLE = new DustParticleEffect(0xFFB04CFF, 0.3f);

    private int tickCounter;

    public WitchPurpleModule() {
        super("Witch Bubbles NEW", "Фиолетовые зельеваренные пузырьки вокруг ведьм.", Category.VISUALS);
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
        if (++this.tickCounter % 5 != 0) {
            return;
        }
        for (Entity entity : world.getEntities()) {
            if (!(entity instanceof WitchEntity witch) || !witch.isAlive()) {
                continue;
            }
            Vec3d pos = witch.getLerpedPos(1.0f);
            double dx = pos.x - this.mc.player.getX();
            double dy = pos.y - this.mc.player.getY();
            double dz = pos.z - this.mc.player.getZ();
            if (dx * dx + dy * dy + dz * dz > 20.0 * 20.0) {
                continue;
            }
            for (int i = 0; i < 2; i++) {
                double ang = Math.random() * Math.PI * 2.0;
                double r = 0.25 + Math.random() * 0.4;
                world.addParticleClient(PURPLE,
                        pos.x + MathHelper.cos((float) ang) * r,
                        pos.y + 0.2 + Math.random() * 1.6,
                        pos.z + MathHelper.sin((float) ang) * r,
                        (Math.random() - 0.5) * 0.02, 0.02 + Math.random() * 0.03, (Math.random() - 0.5) * 0.02);
            }
        }
    }
}
