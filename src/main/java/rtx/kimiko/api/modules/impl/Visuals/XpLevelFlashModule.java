package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
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
 * Вспышка уровня: когда уровень опыта вырос — короткая белая вспышка
 * как награда за новый уровень.
 */
public class XpLevelFlashModule extends Module {
    private static final long FLASH_MS = 300L;

    private final SliderSetting strength;
    private int lastLevel = Integer.MIN_VALUE;
    private long flashStart;

    public XpLevelFlashModule() {
        super("XP Level Flash NEW", "Белая вспышка экрана при повышении уровня опыта.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Яркость вспышки.").setValue(0.7f).range(0.2f, 1.0f));
        this.flashStart = 0L;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled() || this.mc.player == null) {
            return;
        }
        int level = this.mc.player.experienceLevel;
        if (this.lastLevel != Integer.MIN_VALUE && level > this.lastLevel) {
            this.flashStart = System.currentTimeMillis();
        }
        this.lastLevel = level;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.flashStart == 0L) {
            return;
        }
        long age = System.currentTimeMillis() - this.flashStart;
        if (age > FLASH_MS) {
            return;
        }
        float t = (float) age / (float) FLASH_MS;
        float alpha = 0.25f * (1.0f - t) * this.strength.getValue();

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f,
                MenuColors.rgba(255, 255, 255, (int) (255 * alpha)));
        Render2D.flush();
    }
}
