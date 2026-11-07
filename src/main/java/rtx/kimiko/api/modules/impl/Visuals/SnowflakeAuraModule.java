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
 * Снежная аура: в холодных биомах вокруг игрока медленно
 * кружатся и падают белые пылинки-снежинки.
 */
public class SnowflakeAuraModule extends Module {
    private static final DustParticleEffect FLAKE = new DustParticleEffect(0xFFEFF6FF, 0.35f);
    private static final float COLD_THRESHOLD = 0.3f;
    private static final float RADIUS = 3.0f;

    private final SliderSetting density;
    private int tickCounter;

    public SnowflakeAuraModule() {
        super("Snowflake Aura NEW", "Белые снежинки-пылинки вокруг игрока в холодных биомах.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько снежинок появляется.").setValue(1.0f).range(0.5f, 3.0f));
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
        float temp = world.getBiome(this.mc.player.getBlockPos()).value().getTemperature();
        if (temp >= COLD_THRESHOLD) {
            return;
        }
        int count = Math.max(1, Math.round(this.density.getValue()));
        for (int i = 0; i < count; i++) {
            float ang = (float) (Math.random() * Math.PI * 2.0);
            float dist = (float) Math.random() * RADIUS;
            double x = this.mc.player.getX() + MathHelper.cos(ang) * dist;
            double z = this.mc.player.getZ() + MathHelper.sin(ang) * dist;
            double y = this.mc.player.getY() + 1.2 + Math.random() * 1.6;
            world.addParticleClient(FLAKE, x, y, z,
                    (Math.random() - 0.5) * 0.01, -0.02 - Math.random() * 0.02,
                    (Math.random() - 0.5) * 0.01);
        }
    }
}
