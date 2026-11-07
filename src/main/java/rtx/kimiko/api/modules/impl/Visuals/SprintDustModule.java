package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Пыль спринта: при беге по земле из-под ног против движения
 * вырываются маленькие облачка.
 */
public class SprintDustModule extends Module {
    private final SliderSetting density;
    private int tickCounter;

    public SprintDustModule() {
        super("Sprint Dust NEW", "Маленькие облачка пыли у ног при спринте.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько облачек появляется.").setValue(1.0f).range(0.5f, 3.0f));
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
        if (!this.mc.player.isSprinting() || !this.mc.player.isOnGround()) {
            return;
        }
        if (++this.tickCounter % 5 != 0) {
            return;
        }
        Vec3d vel = this.mc.player.getVelocity();
        double vx = vel.x;
        double vz = vel.z;
        double len = Math.sqrt(vx * vx + vz * vz);
        double backX = 0.0;
        double backZ = 0.0;
        if (len > 0.01) {
            backX = -vx / len;
            backZ = -vz / len;
        }
        int count = Math.max(1, Math.round(this.density.getValue()));
        for (int i = 0; i < count; i++) {
            world.addParticleClient(ParticleTypes.CLOUD,
                    this.mc.player.getX() + backX * 0.3 + (Math.random() - 0.5) * 0.25,
                    this.mc.player.getY() + 0.1,
                    this.mc.player.getZ() + backZ * 0.3 + (Math.random() - 0.5) * 0.25,
                    backX * 0.05, 0.01 + Math.random() * 0.02, backZ * 0.05);
        }
    }
}
