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
 * Падающая звезда: ночью изредка по небу проносится метеор
 * с коротким хвостом из белых искр.
 */
public class ShootingStarModule extends Module {
    private static final DustParticleEffect TAIL = new DustParticleEffect(0xFFFFFFFF, 0.3f);
    private static final long FLIGHT_MS = 1500L;

    private final SliderSetting density;
    private long nextStarAt;
    private boolean flying;
    private long flightStart;
    private double sx, sy, sz, svx, svy, svz;

    public ShootingStarModule() {
        super("Shooting Star NEW", "Ночью по небу изредка пролетает падающая звезда.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Как часто появляются звёзды.").setValue(1.0f).range(0.2f, 3.0f));
        this.nextStarAt = 0L;
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
        if (world.getAmbientDarkness() < 3) {
            this.flying = false;
            return;
        }
        long now = System.currentTimeMillis();
        if (this.nextStarAt == 0L) {
            this.nextStarAt = now + 8000L;
        }
        if (this.flying) {
            updateFlight(world, now);
        } else if (now >= this.nextStarAt) {
            spawnStar(world, now);
        }
    }

    private void updateFlight(ClientWorld world, long now) {
        double t = (now - this.flightStart) / (double) FLIGHT_MS;
        if (t >= 1.0) {
            this.flying = false;
            return;
        }
        double len = Math.sqrt(this.svx * this.svx + this.svy * this.svy + this.svz * this.svz);
        if (len < 0.001) {
            this.flying = false;
            return;
        }
        double ux = this.svx / len;
        double uy = this.svy / len;
        double uz = this.svz / len;
        double elapsed = t * FLIGHT_MS / 1000.0;
        for (int i = 0; i < 4; i++) {
            double back = i * 0.8 + Math.random() * 0.4;
            world.addParticleClient(TAIL,
                    this.sx + this.svx * elapsed - ux * back,
                    this.sy + this.svy * elapsed - uy * back,
                    this.sz + this.svz * elapsed - uz * back,
                    (Math.random() - 0.5) * 0.01, (Math.random() - 0.5) * 0.01, (Math.random() - 0.5) * 0.01);
        }
    }

    private void spawnStar(ClientWorld world, long now) {
        double px = this.mc.player.getX();
        double py = this.mc.player.getY();
        double pz = this.mc.player.getZ();
        double az = Math.random() * Math.PI * 2.0;
        double el = Math.toRadians(25.0 + Math.random() * 45.0);
        this.sx = px + Math.cos(az) * Math.cos(el) * 60.0;
        this.sy = py + Math.sin(el) * 60.0 + 8.0;
        this.sz = pz + Math.sin(az) * Math.cos(el) * 60.0;
        double haz = az + (Math.random() - 0.5) * 1.0;
        double speed = 18.0 + Math.random() * 8.0;
        this.svx = Math.cos(haz) * speed;
        this.svy = -2.0 - Math.random() * 2.0;
        this.svz = Math.sin(haz) * speed;
        this.flightStart = now;
        this.flying = true;
        double interval = 30000.0 / MathHelper.clamp(this.density.getValue(), 0.2f, 3.0f);
        this.nextStarAt = now + (long) (interval + Math.random() * interval);
    }
}
