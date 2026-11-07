package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.TridentItem;
import net.minecraft.item.consume.UseAction;
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
 * Shot Chroma NEW: в момент релиза лука/трезубца на 250 мс по периметру
 * экрана расходятся наружу три тонкие хроматические полосы — красная,
 * синяя и зелёная.
 */
public class BowChromaModule extends Module {
    private static final long DURATION_MS = 250L;
    private static final float BAND_ALPHA = 40.0f;
    private static final float BAND_THICKNESS = 1.5f;
    private static final float BAND_SPACING = 3.0f;

    private final SliderSetting strength;
    private boolean wasCharging;
    private long releaseAt;

    public BowChromaModule() {
        super("Shot Chroma NEW", "Хроматические полосы по краю экрана в момент выстрела из лука.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Яркость и разлёт полос.").setValue(1.0f).range(0.3f, 2.0f));
        this.wasCharging = false;
        this.releaseAt = 0L;
    }

    @Override
    protected void onDisable() {
        this.wasCharging = false;
        this.releaseAt = 0L;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre()) {
            return;
        }
        boolean charging = this.mc.player != null && this.mc.player.isUsingItem() && this.isChargedItem();
        if (this.wasCharging && !charging && this.mc.player != null) {
            this.releaseAt = System.currentTimeMillis();
        }
        this.wasCharging = charging;
    }

    private boolean isChargedItem() {
        if (this.mc.player == null) {
            return false;
        }
        if (this.mc.player.getActiveItem().getUseAction() == UseAction.BOW) {
            return true;
        }
        return this.mc.player.getActiveItem().getItem() instanceof TridentItem;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.releaseAt == 0L) {
            return;
        }
        long elapsed = System.currentTimeMillis() - this.releaseAt;
        if (elapsed < 0L || elapsed >= DURATION_MS) {
            if (elapsed >= DURATION_MS) {
                this.releaseAt = 0L;
            }
            return;
        }
        float t = elapsed / (float) DURATION_MS;
        float fade = 1.0f - t;
        float scale = this.strength.getValue();

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;

        // три полосы стартуют чуть внутри экрана и уезжают наружу, растворяясь
        for (int i = 0; i < 3; i++) {
            float inset = (1.0f - t) * (1.5f + i * BAND_SPACING) * Math.max(0.4f, scale);
            float alphaF = Math.min(BAND_ALPHA, BAND_ALPHA * scale) * fade;
            int alpha = (int) Math.max(0.0f, Math.min(255.0f, alphaF));
            if (alpha <= 0) {
                continue;
            }
            int color = i == 0 ? MenuColors.rgba(255, 64, 64, alpha)
                    : i == 1 ? MenuColors.rgba(72, 128, 255, alpha)
                    : MenuColors.rgba(72, 255, 108, alpha);
            float th = BAND_THICKNESS;

            // периметр: верх, низ, лево, право
            Render2D.rect(0.0f, inset, w, th, 0.0f, color);
            Render2D.rect(0.0f, h - inset - th, w, th, 0.0f, color);
            Render2D.rect(inset, 0.0f, th, h, 0.0f, color);
            Render2D.rect(w - inset - th, 0.0f, th, h, 0.0f, color);
        }
        Render2D.flush();
    }
}
