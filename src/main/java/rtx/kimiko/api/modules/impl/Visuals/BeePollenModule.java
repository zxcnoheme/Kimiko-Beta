package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Пыльца пчёл: вокруг летающих пчёл кружатся жёлтые пылинки —
 * пчёлы «пачкаются» цветочной пыльцой.
 */
public class BeePollenModule extends Module {
    private static final DustParticleEffect POLLEN = new DustParticleEffect(0xFFFFD24C, 0.3f);

    private final SliderSetting density;
    private int tickCounter;

    public BeePollenModule() {
        super("Bee Pollen NEW", "Жёлтые пылинки пыльцы кружат вокруг пчёл.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько пылинок у каждой пчелы.").setValue(1.0f).range(0.2f, 3.0f));
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
        if (++this.tickCounter % 10 != 0) {
            return;
        }
        int count = Math.max(1, Math.round(2.0f * this.density.getValue()));
        for (Entity entity : world.getEntities()) {
            if (!(entity instanceof BeeEntity bee) || !bee.isAlive()) {
                continue;
            }
            Vec3d pos = bee.getLerpedPos(1.0f);
            double dx = pos.x - this.mc.player.getX();
            double dy = pos.y - this.mc.player.getY();
            double dz = pos.z - this.mc.player.getZ();
            if (dx * dx + dy * dy + dz * dz > 12.0 * 12.0) {
                continue;
            }
            for (int i = 0; i < count; i++) {
                double ang = Math.random() * Math.PI * 2.0;
                double r = 0.25 + Math.random() * 0.35;
                world.addParticleClient(POLLEN,
                        pos.x + MathHelper.cos(ang) * r,
                        pos.y + 0.15 + Math.random() * 0.45,
                        pos.z + MathHelper.sin(ang) * r,
                        (Math.random() - 0.5) * 0.02, -0.005 - Math.random() * 0.015, (Math.random() - 0.5) * 0.02);
            }
        }
    }
}
