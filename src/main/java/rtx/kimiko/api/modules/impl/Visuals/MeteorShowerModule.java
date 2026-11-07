package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
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
 * Метеорный поток: ночью изредка в небе начинается «звездопад» —
 * метеоры летят по диагонали в верхней полусфере вокруг игрока.
 */
public class MeteorShowerModule extends Module {
    private static final DustParticleEffect METEOR = new DustParticleEffect(0xFFFFF8E7, 0.4f);
    private static final long SHOWER_MS = 10000L;

    private final SliderSetting frequency;
    private boolean showerActive;
    private long showerEnd;
    private long nextShowerAt;
    private int tickCounter;

    public MeteorShowerModule() {
        super("Meteor Shower NEW", "Ночью в небе вспыхивает поток падающих метеоров.", Category.VISUALS);
        this.frequency = (SliderSetting) this.register((Setting) new SliderSetting("Частота", "Как часто начинается поток (в минутах).").setValue(3.0f).range(1.0f, 6.0f));
        this.showerActive = false;
        this.showerEnd = 0L;
        this.nextShowerAt = 0L;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null) {
            this.showerActive = false;
            this.nextShowerAt = 0L;
            return;
        }
        long tod = ((world.getTimeOfDay() % 24000L) + 24000L) % 24000L;
        boolean night = tod >= 13000L && tod <= 23000L;
        if (!night) {
            this.showerActive = false;
            this.nextShowerAt = 0L;
            return;
        }
        long now = System.currentTimeMillis();
        if (this.nextShowerAt == 0L) {
            this.nextShowerAt = now + this.intervalMs();
        }
        if (!this.showerActive && now >= this.nextShowerAt) {
            this.showerActive = true;
            this.showerEnd = now + SHOWER_MS;
        }
        if (!this.showerActive) {
            return;
        }
        if (now > this.showerEnd) {
            this.showerActive = false;
            this.nextShowerAt = now + this.intervalMs();
            return;
        }
        if (++this.tickCounter % 2 != 0) {
            return;
        }
        this.spawnMeteor(world);
    }

    /** Метеор: точка на верхней полусфере радиусом ~50 + скорость по диагонали вниз. */
    private void spawnMeteor(ClientWorld world) {
        double azim = Math.random() * Math.PI * 2.0;
        double dist = 30.0 + Math.random() * 20.0;
        double height = 25.0 + Math.random() * 15.0;
        double sx = this.mc.player.getX() + MathHelper.cos((float) azim) * dist;
        double sy = this.mc.player.getY() + height;
        double sz = this.mc.player.getZ() + MathHelper.sin((float) azim) * dist;
        // летит по диагонали в сторону точки рядом с игроком
        double tx = this.mc.player.getX() + (Math.random() - 0.5) * 20.0;
        double tz = this.mc.player.getZ() + (Math.random() - 0.5) * 20.0;
        Vec3d dir = new Vec3d(tx - sx, -sy * 0.5, tz - sz).normalize();
        double speed = 1.2 + Math.random() * 0.4;
        world.addParticleClient(METEOR, sx, sy, sz,
                dir.x * speed, dir.y * speed, dir.z * speed);
    }

    private long intervalMs() {
        float minutes = this.frequency.getValue();
        long base = (long) (minutes * 60000.0f);
        return base + (long) ((Math.random() - 0.5) * 120000.0);
    }
}
