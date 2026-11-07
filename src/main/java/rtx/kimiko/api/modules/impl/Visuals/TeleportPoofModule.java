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
 * Пуф телепорта: резкий скачок позиции игрока вызывает вспышку —
 * фиолетовые частицы портала в старой точке и искры энд-стержня в новой.
 */
public class TeleportPoofModule extends Module {
    private static final double JUMP_SQUARED = 8.0 * 8.0;

    private final SliderSetting power;
    private Vec3d lastPos;

    public TeleportPoofModule() {
        super("Teleport Poof NEW", "Вспышка частиц при резком телепорте игрока.", Category.VISUALS);
        this.power = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Количество и разлёт частиц.").setValue(1.0f).range(0.5f, 2.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null) {
            this.lastPos = null;
            return;
        }
        Vec3d pos = new Vec3d(this.mc.player.getX(), this.mc.player.getY(), this.mc.player.getZ());
        if (this.lastPos != null && this.lastPos.squaredDistanceTo(pos) > JUMP_SQUARED) {
            this.spawnBurst(world, this.lastPos, pos);
        }
        this.lastPos = pos;
    }

    private void spawnBurst(ClientWorld world, Vec3d from, Vec3d to) {
        float p = this.power.getValue();
        int count = Math.round(12.0f * p);
        for (int i = 0; i < count; i++) {
            world.addParticleClient(ParticleTypes.PORTAL,
                    from.x + (Math.random() - 0.5) * 0.8,
                    from.y + 0.9 + (Math.random() - 0.5) * 0.8,
                    from.z + (Math.random() - 0.5) * 0.8,
                    (Math.random() - 0.5) * 0.3 * p, (Math.random() - 0.2) * 0.3 * p,
                    (Math.random() - 0.5) * 0.3 * p);
        }
        for (int i = 0; i < count; i++) {
            world.addParticleClient(ParticleTypes.END_ROD,
                    to.x + (Math.random() - 0.5) * 0.8,
                    to.y + 0.9 + (Math.random() - 0.5) * 0.8,
                    to.z + (Math.random() - 0.5) * 0.8,
                    (Math.random() - 0.5) * 0.15 * p, Math.random() * 0.12 * p,
                    (Math.random() - 0.5) * 0.15 * p);
        }
    }
}
