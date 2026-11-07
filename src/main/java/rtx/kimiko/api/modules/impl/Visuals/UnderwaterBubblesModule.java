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
 * Пузырьки под водой: когда игрок плывёт, от его лица вперёд-вверх
 * поднимаются мелкие пузырьки воздуха.
 */
public class UnderwaterBubblesModule extends Module {
    private final SliderSetting density;
    private int tickCounter;

    public UnderwaterBubblesModule() {
        super("Underwater Bubbles NEW", "Пузырьки воздуха от лица при плавании под водой.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько пузырьков появляется.").setValue(1.0f).range(0.5f, 3.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null || !this.mc.player.isSubmergedInWater()) {
            return;
        }
        Vec3d vel = this.mc.player.getVelocity();
        if (vel.horizontalLength() <= 0.1) {
            return;
        }
        if (++this.tickCounter % 4 != 0) {
            return;
        }
        Vec3d look = this.mc.player.getRotationVec(1.0f);
        double px = this.mc.player.getX() + look.x * 0.45;
        double py = this.mc.player.getEyeY() + look.y * 0.45;
        double pz = this.mc.player.getZ() + look.z * 0.45;
        int count = Math.round(this.density.getValue()) + (Math.random() < 0.5 ? 1 : 0);
        for (int i = 0; i < count; i++) {
            world.addParticleClient(ParticleTypes.BUBBLE,
                    px + (Math.random() - 0.5) * 0.2,
                    py + (Math.random() - 0.5) * 0.15,
                    pz + (Math.random() - 0.5) * 0.2,
                    look.x * 0.08, 0.05 + Math.random() * 0.05, look.z * 0.08);
        }
    }
}
