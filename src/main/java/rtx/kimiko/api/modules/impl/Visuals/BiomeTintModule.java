package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Тон биома: у особенных биомов экран мягко подкрашивается
 * в характерный цвет — вишня розовеет, болото зеленеет,
 * Незер наливается малиной и бирюзой, меза — охрой.
 */
public class BiomeTintModule extends Module {
    private final SliderSetting strength;

    public BiomeTintModule() {
        super("Biome Tint NEW", "Лёгкие цветные тона в особенных биомах: вишня, болото, Незер и меза.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Насколько сильно подкрашивать экран.").setValue(0.6f).range(0.1f, 1.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.world == null || this.mc.player == null) {
            return;
        }
        String path = this.mc.world.getBiome(this.mc.player.getBlockPos()).getKey()
                .map(key -> key.getValue().getPath()).orElse("");
        int[] tint = tintFor(path);
        if (tint == null) {
            return;
        }
        int alpha = Math.max(3, (int) (tint[3] * this.strength.getValue()));

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f,
                MenuColors.rgba(tint[0], tint[1], tint[2], alpha));
        Render2D.flush();
    }

    /** [r, g, b, базовая альфа] для биома или null. */
    private static int[] tintFor(String path) {
        if (path.contains("cherry_grove")) {
            return new int[]{255, 170, 205, 14};
        }
        if (path.contains("swamp")) {
            return new int[]{110, 160, 80, 14};
        }
        if (path.contains("crimson_forest")) {
            return new int[]{220, 60, 90, 15};
        }
        if (path.contains("warped_forest")) {
            return new int[]{60, 200, 190, 15};
        }
        if (path.contains("badlands")) {
            return new int[]{200, 140, 70, 16};
        }
        return null;
    }
}
