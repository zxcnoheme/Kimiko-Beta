package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Круги от поплавка: пока поплавок заброшен, вокруг него
 * всплывают пузырьки — вода «живёт». Поплавок берём из
 * публичного поля PlayerEntity.fishHook (проверено по yarn
 * 1.21.11), без скана сущностей — так надёжнее и дешевле.
 */
public class FishingRippleModule extends Module {
    private final SliderSetting density;
    private int tickCounter;

    public FishingRippleModule() {
        super("Fishing Ripple NEW", "Пузырьки появляются вокруг заброшенного поплавка.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько пузырьков за раз.").setValue(1.0f).range(0.2f, 3.0f));
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
        // fishHook — публичное поле PlayerEntity в yarn 1.21.11
        FishingBobberEntity hook = this.mc.player.fishHook;
        if (hook == null || !hook.isAlive()) {
            return;
        }
        if (++this.tickCounter % 15 != 0) {
            return;
        }
        int count = Math.max(1, Math.round(2.0f * this.density.getValue()));
        Vec3d pos = hook.getLerpedPos(1.0f);
        // пузырьки чуть ниже поверхности поплавка, поднимаются вверх
        for (int i = 0; i < count; i++) {
            world.addParticleClient(ParticleTypes.BUBBLE,
                    pos.x + (Math.random() - 0.5) * 0.6,
                    pos.y + 0.02 + Math.random() * 0.06,
                    pos.z + (Math.random() - 0.5) * 0.6,
                    0.0, 0.01 + Math.random() * 0.02, 0.0);
        }
    }
}
