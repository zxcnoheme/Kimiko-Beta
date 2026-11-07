package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.world.ClientWorld;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Nether Tint: лёгкий красный тон экрана в незере — атмосфера жара и опасности.
 */
public class NetherTintModule extends Module {
    private final SliderSetting strength;

    public NetherTintModule() {
        super("Nether Tint NEW", "Лёгкий красный тон экрана в Незере.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Насколько сильно красит экран.").setValue(1.0f).range(0.2f, 1.5f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || !"the_nether".equals(world.getRegistryKey().getValue().getPath())) {
            return;
        }
        int a = (int) (18.0f * this.strength.getValue());
        if (a <= 0) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f, MenuColors.rgba(180, 30, 20, a));
        Render2D.flush();
    }
}
