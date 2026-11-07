package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.particle.DustParticleEffect;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Искры опыта: вокруг orbs опыта вспыхивают маленькие белые искры.
 */
public class XpSparkleModule extends Module {
    private static final DustParticleEffect SPARK = new DustParticleEffect(0xFFBFFF5E, 0.3f);

    private final SliderSetting density;
    private int tickCounter;

    public XpSparkleModule() {
        super("XP Sparkle NEW", "Белые искры мерцают вокруг сфер опыта.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько искр у каждого орба.").setValue(1.0f).range(0.2f, 3.0f));
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
        if (++this.tickCounter % 8 != 0) {
            return;
        }
        double px = this.mc.player.getX();
        double py = this.mc.player.getY();
        double pz = this.mc.player.getZ();
        for (Entity entity : world.getEntities()) {
            if (!(entity instanceof ExperienceOrbEntity orb) || !orb.isAlive()) {
                continue;
            }
            double dx = orb.getX() - px;
            double dy = orb.getY() - py;
            double dz = orb.getZ() - pz;
            if (dx * dx + dy * dy + dz * dz > 10.0 * 10.0) {
                continue;
            }
            int count = Math.max(1, Math.round(this.density.getValue()));
            for (int i = 0; i < count; i++) {
                world.addParticleClient(SPARK,
                        orb.getX() + (Math.random() - 0.5) * 0.5,
                        orb.getY() + 0.1 + Math.random() * 0.4,
                        orb.getZ() + (Math.random() - 0.5) * 0.5,
                        (Math.random() - 0.5) * 0.02, 0.01 + Math.random() * 0.02, (Math.random() - 0.5) * 0.02);
            }
        }
    }
}
