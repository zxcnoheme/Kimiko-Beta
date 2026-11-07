package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Пульс хотбара: при смене выбранного слота вокруг него вспыхивает
 * белая рамка, которая за 300 мс плавно гаснет.
 */
public class HotbarPulseModule extends Module {
    private static final long PULSE_MS = 300L;

    private int lastSlot = Integer.MIN_VALUE;
    private int pulseSlot;
    private long pulseStart;

    public HotbarPulseModule() {
        super("Hotbar Pulse NEW", "Белая рамка-пульс вокруг выбранного слота хотбара.", Category.VISUALS);
        this.pulseSlot = 0;
        this.pulseStart = 0L;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled() || this.mc.player == null) {
            return;
        }
        int slot = this.mc.player.getInventory().getSelectedSlot();
        if (this.lastSlot != Integer.MIN_VALUE && slot != this.lastSlot) {
            this.pulseSlot = slot;
            this.pulseStart = System.currentTimeMillis();
        }
        this.lastSlot = slot;
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
        int alpha = (int) (230 * (1.0f - t));
        if (alpha <= 0) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        // ванильный хотбар: ширина 182, слот 20x20, y = h - 23
        float x = w * 0.5f - 91.0f + this.pulseSlot * 20.0f;
        float y = h - 23.0f;
        Render2D.outline(x, y, 20.0f, 20.0f, 0.0f, 1.5f, (alpha << 24) | 0xFFFFFF);
        Render2D.rect(x, y, 20.0f, 20.0f, 0.0f, (alpha / 5 << 24) | 0xFFFFFF);
        Render2D.flush();
    }
}
