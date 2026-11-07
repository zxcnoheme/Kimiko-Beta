package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DustParticleEffect;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Пещерная пыль: глубоко под землёй в воздухе лениво дрейфуют
 * серые пылинки.
 */
public class CaveDustModule extends Module {
    private static final DustParticleEffect DUST = new DustParticleEffect(0xFF9A948A, 0.25f);
    private static final float CAVE_LEVEL = 45.0f;
    private static final float RADIUS = 6.0f;

    private final SliderSetting density;
    private int tickCounter;

    public CaveDustModule() {
        super("Cave Dust NEW", "Дрейфующая серая пыль в пещерах глубоко под землёй.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько пылинок появляется.").setValue(1.0f).range(0.5f, 3.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null || this.mc.player.getY() >= CAVE_LEVEL) {
            return;
        }
        if (++this.tickCounter % 10 != 0) {
            return;
        }
        int count = Math.round(this.density.getValue() * (2.0f + (Math.random() < 0.5f ? 1.0f : 0.0f)));
        for (int i = 0; i < count; i++) {
            double x = this.mc.player.getX() + (Math.random() - 0.5) * RADIUS * 2.0;
            double y = this.mc.player.getY() + (Math.random() - 0.5) * 5.0;
            double z = this.mc.player.getZ() + (Math.random() - 0.5) * RADIUS * 2.0;
            world.addParticleClient(DUST, x, y, z,
                    (Math.random() - 0.5) * 0.01, 0.005 + Math.random() * 0.01,
                    (Math.random() - 0.5) * 0.01);
        }
    }
}
