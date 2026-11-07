package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Всплеск при входе в воду: с плацдармом брызг и пузырьков, сила зависит
 * от скорости падения.
 */
public class WaterSplashModule extends Module {
    private final SliderSetting strength;
    private boolean wasInWater;

    public WaterSplashModule() {
        super("Water Splash NEW", "При входе в воду — брызги и кольцо пузырьков; чем быстрее падаешь, тем мощнее.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Множитель количества брызг.").setValue(1.0f).range(0.3f, 2.0f));
        this.wasInWater = false;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        ClientPlayerEntity player = this.mc.player;
        if (world == null || player == null) {
            this.wasInWater = false;
            return;
        }
        boolean inWater = player.isTouchingWater();
        if (inWater && !this.wasInWater) {
            double speed = Math.abs(player.getVelocity().y);
            // упавший в этот тик с заметной скоростью — всплеск
            if (player.fallDistance > 0.4f || speed > 0.35) {
                int count = MathHelper.clamp((int) (8.0f + (float) Math.min(speed, 1.2) * 18.0f * this.strength.getValue()), 8, 40);
                Vec3d feet = player.getLerpedPos(1.0f);
                for (int i = 0; i < count; i++) {
                    float ang = (float) (Math.random() * Math.PI * 2.0);
                    float dist = 0.25f + (float) Math.random() * 0.45f;
                    double px = feet.x + MathHelper.cos(ang) * dist;
                    double pz = feet.z + MathHelper.sin(ang) * dist;
                    world.addParticleClient(ParticleTypes.SPLASH, px, feet.y + 0.1, pz,
                            MathHelper.cos(ang) * 0.14, 0.22 + Math.random() * 0.3, MathHelper.sin(ang) * 0.14);
                }
                for (int i = 0; i < count / 2; i++) {
                    double px = feet.x + (Math.random() - 0.5) * 1.0;
                    double pz = feet.z + (Math.random() - 0.5) * 1.0;
                    world.addParticleClient(ParticleTypes.BUBBLE, px, feet.y + 0.05, pz,
                            0.0, -0.04, 0.0);
                }
            }
        }
        this.wasInWater = inWater;
    }
}
