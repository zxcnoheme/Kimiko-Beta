package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.world.ClientWorld;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.render.render2d.Render2D;

import java.util.Random;

/**
 * Thunder Boost: усиление грозы — собственные вспышки молний на весь экран,
 * независимые от ванильных. Вспышка двухфазная: основной спад и мини-повтор.
 */
public class ThunderFlashModule extends Module {
    private static final long MAIN_MS = 180L;
    private static final long GAP_MS = 60L;
    private static final long MINI_MS = 120L;

    private final SliderSetting strength;
    private final Random random = new Random();
    private long flashStart;

    public ThunderFlashModule() {
        super("Thunder Boost NEW", "Усиленные вспышки молний во время грозы.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Яркость вспышек.").setValue(1.0f).range(0.3f, 2.0f));
        this.flashStart = 0L;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || !world.isThundering()) {
            this.flashStart = 0L;
            return;
        }
        long now = System.currentTimeMillis();
        if (this.flashStart != 0L && now - this.flashStart > MAIN_MS + GAP_MS + MINI_MS) {
            this.flashStart = 0L;
        }
        // в среднем раз в ~6 секунд (120 тиков)
        if (this.flashStart == 0L && world.getThunderGradient(1.0f) > 0.4f && this.random.nextInt(120) == 0) {
            this.flashStart = now;
        }
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.flashStart == 0L || this.mc.world == null) {
            return;
        }
        long age = System.currentTimeMillis() - this.flashStart;
        float a;
        if (age < MAIN_MS) {
            a = 0.25f * (1.0f - (float) age / (float) MAIN_MS);
        } else if (age >= MAIN_MS + GAP_MS && age < MAIN_MS + GAP_MS + MINI_MS) {
            a = 0.15f * (1.0f - (float) (age - MAIN_MS - GAP_MS) / (float) MINI_MS);
        } else {
            return;
        }
        int alpha = (int) (Math.max(0.0f, Math.min(1.0f, a * this.strength.getValue())) * 255.0f);
        if (alpha <= 0) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f, (alpha << 24) | 0xFFFFFF);
        Render2D.flush();
    }
}
