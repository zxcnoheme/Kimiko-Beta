package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.effect.StatusEffectInstance;
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
 * Пульс лечения: при регенерации от краёв экрана расходится мягкая
 * зелёная волна — здоровье прибывает.
 */
public class HealPulseModule extends Module {
    private static final long PULSE_MS = 400L;
    private static final long COOLDOWN_MS = 800L;

    private final SliderSetting strength;
    private float lastHp = -1.0f;
    private long pulseStart;

    public HealPulseModule() {
        super("Heal Pulse NEW", "Зелёная волна от краёв экрана, когда здоровье восстанавливается.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((SliderSetting) new SliderSetting("Сила", "Яркость волны.").setValue(0.7f).range(0.2f, 1.0f));
        this.pulseStart = 0L;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled() || this.mc.player == null || this.mc.player.isDead()) {
            return;
        }
        float hp = this.mc.player.getHealth();
        if (this.lastHp >= 0.0f && this.lastHp != hp) {
            float delta = hp - this.lastHp;
            if ((delta > 0.0f && delta < 2.0f) || this.hasRegeneration()) {
                long now = System.currentTimeMillis();
                if (now - this.pulseStart >= COOLDOWN_MS) {
                    this.pulseStart = now;
                }
            }
        }
        this.lastHp = hp;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.pulseStart == 0L) {
            return;
        }
        long age = System.currentTimeMillis() - this.pulseStart;
        if (age > PULSE_MS) {
            return;
        }
        float t = (float) age / (float) PULSE_MS;
        float alpha = 0.12f * (1.0f - t) * this.strength.getValue();

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        // зелёная волна: сильнее у краёв, к центру мягче
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f,
                MenuColors.rgba(80, 230, 130, (int) (255 * alpha * 1.5f)),
                MenuColors.rgba(80, 230, 130, (int) (255 * alpha * 1.5f)),
                MenuColors.rgba(120, 255, 160, (int) (255 * alpha * 0.4f)),
                MenuColors.rgba(120, 255, 160, (int) (255 * alpha * 0.4f)));
        Render2D.flush();
    }

    private boolean hasRegeneration() {
        if (this.mc.player == null) {
            return false;
        }
        for (StatusEffectInstance instance : this.mc.player.getStatusEffects()) {
            if (instance.getEffectType().value() == net.minecraft.entity.effect.StatusEffects.REGENERATION.value()) {
                return true;
            }
        }
        return false;
    }
}
