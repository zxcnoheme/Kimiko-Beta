package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DustParticleEffect;
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
 * Песчаная буря: в пустыне воздух затягивает жёлто-песчаной мглой,
 * а по горизонтали летят песчинки.
 */
public class SandstormModule extends Module {
    private static final DustParticleEffect SAND = new DustParticleEffect(0xFFD9B878, 0.3f);

    private final SliderSetting strength;
    private final SliderSetting density;
    private int tickCounter;

    public SandstormModule() {
        super("Sandstorm NEW", "Песчаная мгла и летящие песчинки в пустынных биомах.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Плотность мглы.").setValue(0.6f).range(0.1f, 1.0f));
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько песчинок появляется.").setValue(1.0f).range(0.3f, 3.0f));
        this.tickCounter = 0;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled() || this.mc.world == null || this.mc.player == null) {
            return;
        }
        if (!this.isDesert(this.mc.world)) {
            return;
        }
        if (++this.tickCounter % 3 != 0) {
            return;
        }
        int count = Math.round(3.0f * this.density.getValue());
        for (int i = 0; i < count; i++) {
            double x = this.mc.player.getX() + (Math.random() - 0.5) * 16.0;
            double y = this.mc.player.getY() + 0.5 + (Math.random() - 0.5) * 4.0;
            double z = this.mc.player.getZ() + (Math.random() - 0.5) * 16.0;
            // горизонтальный порыв ветра в случайную сторону
            double vx = (Math.random() - 0.5) * 0.35;
            double vz = (Math.random() - 0.5) * 0.35;
            this.mc.world.addParticleClient(SAND, x, y, z, vx, 0.01 + Math.random() * 0.02, vz);
        }
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.world == null || this.mc.player == null) {
            return;
        }
        if (!this.isDesert(this.mc.world)) {
            return;
        }
        int alpha = Math.max(4, (int) (14.0f * this.strength.getValue()));

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f,
                MenuColors.rgba(210, 180, 110, alpha));
        Render2D.flush();
    }

    private boolean isDesert(ClientWorld world) {
        String path = world.getBiome(this.mc.player.getBlockPos()).getKey()
                .map(key -> key.getValue().getPath()).orElse("");
        return path.contains("desert") || path.contains("dunes");
    }
}
