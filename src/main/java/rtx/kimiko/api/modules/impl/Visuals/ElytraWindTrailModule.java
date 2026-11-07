package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Ветер элитры: при быстром полёте за спиной игрока остаются белесые
 * полоски-частицы, летящие против вектора движения.
 */
public class ElytraWindTrailModule extends Module {
    private static final DustParticleEffect WIND = new DustParticleEffect(0xFFDDDDDD, 0.4f);

    private final SliderSetting density;
    private int tickCounter;

    public ElytraWindTrailModule() {
        super("Elytra Wind NEW", "Белесые полоски ветра за спиной при полёте на элитрах.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько полосок появляется.").setValue(1.0f).range(0.5f, 3.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null || !this.mc.player.isGliding()) {
            return;
        }
        Vec3d vel = this.mc.player.getVelocity();
        if (vel.length() <= 0.5) {
            return;
        }
        if (++this.tickCounter % 2 != 0) {
            return;
        }
        Vec3d back = vel.normalize().negate();
        int count = Math.max(1, Math.round(this.density.getValue() * 2.0f));
        for (int i = 0; i < count; i++) {
            double px = this.mc.player.getX() + back.x * 0.6 + (Math.random() - 0.5) * 0.5;
            double py = this.mc.player.getY() + 0.9 + (Math.random() - 0.5) * 0.5;
            double pz = this.mc.player.getZ() + back.z * 0.6 + (Math.random() - 0.5) * 0.5;
            float speed = 0.25f + (float) Math.random() * 0.2f;
            world.addParticleClient(WIND, px, py, pz,
                    back.x * speed, (Math.random() - 0.5) * 0.05, back.z * speed);
        }
    }
}
