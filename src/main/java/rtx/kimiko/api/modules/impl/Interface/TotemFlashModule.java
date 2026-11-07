package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.player.TotemPopEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Totem Flash NEW: золотая вспышка на весь экран, когда твой тотем ломается.
 * Затухание 500 мс; настраивается только сила вспышки.
 */
public class TotemFlashModule extends Module {
    private final SliderSetting strength;
    private long flashStartNanos;
    private boolean flashing;

    public TotemFlashModule() {
        super("Totem Flash NEW", "Золотая вспышка экрана при срабатывании твоего тотема.", Category.DISPLAY);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Максимальная яркость вспышки.").setValue(0.6f).range(0.1f, 1.0f));
        this.flashStartNanos = 0L;
        this.flashing = false;
    }

    @EventHandler
    private void onTotemPop(TotemPopEvent event) {
        if (!this.isEnabled() || this.mc.player == null) {
            return;
        }
        if (event.getEntity() == this.mc.player) {
            this.flashStartNanos = System.nanoTime();
            this.flashing = true;
        }
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || !this.flashing) {
            return;
        }
        long elapsed = System.nanoTime() - this.flashStartNanos;
        if (elapsed >= 500_000_000L) {
            this.flashing = false;
            return;
        }

        float t = 1.0f - elapsed / 500_000_000.0f;
        t = t * t * (3.0f - 2.0f * t); // плавное затухание
        int alpha = Math.round(255.0f * this.strength.getValue() * t);
        if (alpha <= 0) {
            return;
        }

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;

        Render2D.rect(0.0f, 0.0f, w, h, 0.0f, MenuColors.rgba(255, 196, 40, alpha));
        Render2D.flush();
    }
}
