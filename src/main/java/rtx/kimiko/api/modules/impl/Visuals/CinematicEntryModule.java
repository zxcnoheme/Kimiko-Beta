package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Кинематографичный вход: при заходе в мир — мягкая белая вспышка,
 * которая плавно уходит.
 */
public class CinematicEntryModule extends Module {
    private static final long FLASH_MS = 900L;

    private final SliderSetting strength;
    private boolean worldWasNull = true;
    private long flashStart;

    public CinematicEntryModule() {
        super("Cinematic Entry NEW", "Мягкая белая вспышка при входе в мир.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Яркость вспышки.").setValue(0.7f).range(0.2f, 1.0f));
        this.flashStart = 0L;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (this.mc.world == null) {
            this.worldWasNull = true;
            return;
        }
        if (this.worldWasNull) {
            this.worldWasNull = false;
            this.flashStart = System.currentTimeMillis();
        }
        if (!this.isEnabled() || this.flashStart == 0L) {
            return;
        }
        long age = System.currentTimeMillis() - this.flashStart;
        if (age > FLASH_MS) {
            return;
        }
        float t = (float) age / (float) FLASH_MS;
        float ease = 1.0f - (1.0f - t) * (1.0f - t);
        float alpha = (1.0f - ease) * this.strength.getValue() * 0.8f;
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        Render2D.rect(0.0f, 0.0f, this.mc.getWindow().getScaledWidth() / sf,
                this.mc.getWindow().getScaledHeight() / sf, 0.0f,
                (MathHelper255.clamp01(alpha)));
        Render2D.flush();
    }

    /** Локальный хелпер, чтобы не тянуть MathHelper. */
    private static final class MathHelper255 {
        static int clamp01(float v) {
            int a = (int) (Math.max(0.0f, Math.min(1.0f, v)) * 255.0f);
            return (a << 24) | 0xFFFFFF;
        }
    }
}
