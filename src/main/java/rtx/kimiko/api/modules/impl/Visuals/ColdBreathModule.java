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
 * Пар на морозе: изо рта игрока в холодных биомах вырывается облачко пара.
 */
public class ColdBreathModule extends Module {
    private final SliderSetting interval;
    private int nextBreath;

    public ColdBreathModule() {
        super("Cold Breath NEW", "В холодных биомах изо рта клубится пар при дыхании.", Category.VISUALS);
        this.interval = (SliderSetting) this.register((Setting) new SliderSetting("Частота", "Секунд между выдохами.").setValue(2.0f).range(1.0f, 5.0f));
        this.nextBreath = 0;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        ClientPlayerEntity player = this.mc.player;
        if (world == null || player == null || player.isDead()) {
            return;
        }
        if (!isCold(world, player.getX(), player.getZ())) {
            return;
        }
        if (--this.nextBreath > 0) {
            return;
        }
        boolean sprinting = player.isSprinting();
        this.nextBreath = MathHelper.floor(this.interval.getValue() * (sprinting ? 10.0f : 20.0f));

        Vec3d look = player.getRotationVec(1.0f);
        double x = player.getX() + look.x * 0.3;
        double y = player.getEyeY() - 0.18 + look.y * 0.3;
        double z = player.getZ() + look.z * 0.3;
        float drift = 0.02f;
        this.mc.world.addParticleClient(ParticleTypes.CLOUD, x, y, z,
                look.x * drift, 0.015, look.z * drift);
        this.mc.world.addParticleClient(ParticleTypes.CLOUD, x + (Math.random() - 0.5) * 0.05,
                y - 0.03, z + (Math.random() - 0.5) * 0.05,
                look.x * drift * 0.6, 0.01, look.z * drift * 0.6);
    }

    static boolean isCold(ClientWorld world, double x, double z) {
        return world.getBiome(net.minecraft.util.math.BlockPos.ofFloored(x, 64, z))
                .value()
                .getTemperature() < 0.25f;
    }
}
