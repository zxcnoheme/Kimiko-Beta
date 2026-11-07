package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;

/**
 * Перья попугая: вокруг попугаев кружат яркие пылинки-перья в цвет
 * оперения — красные, синие и зелёные.
 */
public class ParrotFeatherModule extends Module {
    private static final DustParticleEffect[] FEATHERS = {
            new DustParticleEffect(0xFFFF5555, 0.3f),
            new DustParticleEffect(0xFF5588FF, 0.3f),
            new DustParticleEffect(0xFF55CC55, 0.3f)
    };

    private int tickCounter;

    public ParrotFeatherModule() {
        super("Parrot Feathers NEW", "Цветные пылинки-перья кружат вокруг попугаев.", Category.VISUALS);
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
        if (++this.tickCounter % 6 != 0) {
            return;
        }
        for (Entity entity : world.getEntities()) {
            if (!(entity instanceof ParrotEntity parrot) || !parrot.isAlive()) {
                continue;
            }
            Vec3d pos = parrot.getLerpedPos(1.0f);
            double dx = pos.x - this.mc.player.getX();
            double dy = pos.y - this.mc.player.getY();
            double dz = pos.z - this.mc.player.getZ();
            if (dx * dx + dy * dy + dz * dz > 16.0 * 16.0) {
                continue;
            }
            for (int i = 0; i < 2; i++) {
                DustParticleEffect feather = FEATHERS[(int) (Math.random() * FEATHERS.length)];
                double ang = Math.random() * Math.PI * 2.0;
                double r = 0.25 + Math.random() * 0.4;
                world.addParticleClient(feather,
                        pos.x + MathHelper.cos((float) ang) * r,
                        pos.y + 0.3 + Math.random() * 0.8,
                        pos.z + MathHelper.sin((float) ang) * r,
                        (Math.random() - 0.5) * 0.02, -0.01 - Math.random() * 0.02, (Math.random() - 0.5) * 0.02);
            }
        }
    }
}
