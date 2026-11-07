package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Void Fog: глубоко под границей мира экран затягивает чёрная дымка с краёв,
 * а вокруг игрока мелькают тёмные частицы.
 */
public class VoidFogModule extends Module {
    private static final DustParticleEffect DARK = new DustParticleEffect(0xFF0A0A0C, 0.3f);

    private final SliderSetting strength;
    private int tickCounter;

    public VoidFogModule() {
        super("Void Fog NEW", "Чёрная дымка с краёв экрана и тёмные частицы глубоко под миром.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Плотность дымки.").setValue(1.0f).range(0.2f, 2.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null) {
            return;
        }
        // глубина: от -20 вниз, максимум 0.55
        float depth = MathHelper.clamp((-20.0f - (float) this.mc.player.getY()) / 40.0f, 0.0f, 0.55f);
        if (depth <= 0.0f) {
            return;
        }
        int a = (int) (Math.min(1.0f, depth * this.strength.getValue()) * 255.0f);
        if (a <= 0) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        int dark = MenuColors.rgba(0, 0, 0, a);
        int clear = MenuColors.rgba(0, 0, 0, 0);
        float side = w * 0.32f;
        float vert = h * 0.38f;
        Render2D.rect(0.0f, 0.0f, w, vert, 0.0f, dark, dark, clear, clear);
        Render2D.rect(0.0f, h - vert, w, vert, 0.0f, clear, clear, dark, dark);
        Render2D.rect(0.0f, 0.0f, side, h, 0.0f, dark, clear, clear, dark);
        Render2D.rect(w - side, 0.0f, side, h, 0.0f, clear, dark, dark, clear);
        Render2D.flush();
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null || this.mc.player.getY() >= -20.0) {
            return;
        }
        if (++this.tickCounter % 8 != 0) {
            return;
        }
        int count = Math.random() < 0.35 ? 2 : 1;
        for (int i = 0; i < count; i++) {
            double x = this.mc.player.getX() + (Math.random() - 0.5) * 8.0;
            double y = this.mc.player.getY() + Math.random() * 3.0 - 1.0;
            double z = this.mc.player.getZ() + (Math.random() - 0.5) * 8.0;
            world.addParticleClient(DARK, x, y, z, 0.0, 0.01, 0.0);
        }
    }
}
