package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.effect.StatusEffectInstance;
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
 * Пузырьки эффектов: пока на игроке активны эффекты зелий,
 * вокруг него всплывают пузырьки цвета первого эффекта из
 * списка getStatusEffects() — цвет берётся из getColor().
 */
public class PotionBubblesModule extends Module {
    private final SliderSetting density;
    private int tickCounter;

    public PotionBubblesModule() {
        super("Potion Bubbles NEW", "Цветные пузырьки всплывают вокруг игрока под эффектами зелий.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Насколько часто появляются пузырьки.").setValue(1.0f).range(0.2f, 3.0f));
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
        if (this.mc.player.getStatusEffects().isEmpty()) {
            return;
        }
        if (++this.tickCounter % 6 != 0) {
            return;
        }
        StatusEffectInstance effect = this.mc.player.getStatusEffects().iterator().next();
        int argb = 0xFF000000 | effect.getEffectType().value().getColor();
        DustParticleEffect bubble = new DustParticleEffect(argb, 0.3f);
        int count = Math.max(1, Math.round(this.density.getValue()));
        Vec3d pos = this.mc.player.getLerpedPos(1.0f);
        for (int i = 0; i < count; i++) {
            double ang = Math.random() * Math.PI * 2.0;
            double r = 0.35 + Math.random() * 0.45;
            world.addParticleClient(bubble,
                    pos.x + MathHelper.cos(ang) * r,
                    pos.y + 0.15 + Math.random() * 1.3,
                    pos.z + MathHelper.sin(ang) * r,
                    (Math.random() - 0.5) * 0.01, 0.02 + Math.random() * 0.02, (Math.random() - 0.5) * 0.01);
        }
    }
}
