package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Тон ночного зрения: под эффектом ночного зрения экран мягко
 * подкрашивается зелёным — видно, что эффект активен.
 */
public class NightVisionTintModule extends Module {
    private final SliderSetting strength;

    public NightVisionTintModule() {
        super("Night Vision Tint NEW", "Лёгкий зелёный тон экрана под эффектом ночного зрения.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Насколько сильно подкрашивать экран.").setValue(0.5f).range(0.1f, 1.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null) {
            return;
        }
        if (!this.hasNightVision()) {
            return;
        }
        int alpha = Math.max(2, (int) (this.strength.getValue() * 10.0f));

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f,
                MenuColors.rgba(90, 255, 120, alpha));
        Render2D.flush();
    }

    private boolean hasNightVision() {
        if (this.mc.player == null) {
            return false;
        }
        for (StatusEffectInstance instance : this.mc.player.getStatusEffects()) {
            if (instance.getEffectType().value() == StatusEffects.NIGHT_VISION.value()) {
                return true;
            }
        }
        return false;
    }
}
