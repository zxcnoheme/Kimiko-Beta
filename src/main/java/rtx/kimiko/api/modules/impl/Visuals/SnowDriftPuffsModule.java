package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Позёмка: в снежных биомах по земле стелется снег —
 * белые пушинки летят низко над поверхностью в одном направлении,
 * которое медленно меняется каждые пять секунд.
 */
public class SnowDriftPuffsModule extends Module {
    private static final DustParticleEffect PUFF = new DustParticleEffect(0xFFEFF6FF, 0.3f);
    private static final long DIRECTION_TICKS = 100L;

    private final SliderSetting density;
    private int tickCounter;
    private float driftAngle;
    private long driftUntil;

    public SnowDriftPuffsModule() {
        super("Snow Drift NEW", "Позёмка: белый снег стелется по земле в снежных биомах.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько снежинок появляется за раз.").setValue(1.0f).range(0.2f, 3.0f));
        this.driftAngle = 0.0f;
        this.driftUntil = 0L;
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
        float temp = world.getBiome(this.mc.player.getBlockPos()).value().getTemperature();
        if (temp >= 0.2f) {
            return;
        }
        if (world.getTime() >= this.driftUntil) {
            this.driftAngle = (float) (Math.random() * Math.PI * 2.0);
            this.driftUntil = world.getTime() + DIRECTION_TICKS;
        }
        if (++this.tickCounter % 6 != 0) {
            return;
        }
        int count = Math.max(1, Math.round(3.0f * this.density.getValue()));
        float dx = MathHelper.cos(this.driftAngle);
        float dz = MathHelper.sin(this.driftAngle);
        for (int i = 0; i < count; i++) {
            float side = (float) (Math.random() * Math.PI * 2.0);
            float dist = 3.0f + (float) Math.random() * 8.0f;
            double x = this.mc.player.getX() + dx * dist + MathHelper.cos(side) * 1.5;
            double z = this.mc.player.getZ() + dz * dist + MathHelper.sin(side) * 1.5;
            int bx = MathHelper.floor(x);
            int bz = MathHelper.floor(z);
            int top = FirefliesModule.topY(world, bx, bz);
            if (top <= world.getBottomY() + 1) {
                continue;
            }
            double speed = 0.12 + Math.random() * 0.1;
            world.addParticleClient(PUFF,
                    x, top + 0.1, z,
                    dx * speed, 0.005 + Math.random() * 0.01, dz * speed);
        }
    }
}
