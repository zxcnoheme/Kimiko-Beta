package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Net Graph NEW: спарклайн своего пинга (48 значений, обновление раз в 500 мс)
 * с подписью текущего значения.
 */
public class NetGraphModule extends Module {
    private static final int BARS = 48;
    private static final long SAMPLE_INTERVAL_MS = 500L;

    private final SliderSetting scale;
    private final float[] history = new float[BARS];
    private int cursor;
    private long lastSampleMs;
    private int lastPing;

    public NetGraphModule() {
        super("Net Graph NEW", "Микрограф твоего пинга с текущим значением.", Category.DISPLAY);
        this.scale = (SliderSetting) this.register((Setting) new SliderSetting("Масштаб", "Верхняя граница графика (мс).").setValue(250.0f).range(100.0f, 1000.0f));
        this.cursor = 0;
        this.lastSampleMs = 0L;
        this.lastPing = 0;
    }

    private int samplePing() {
        try {
            ClientPlayNetworkHandler handler = this.mc.getNetworkHandler();
            if (handler == null || this.mc.player == null) {
                return -1;
            }
            PlayerListEntry entry = handler.getPlayerListEntry(this.mc.player.getUuid());
            if (entry == null) {
                return -1;
            }
            return Math.max(0, entry.getLatency());
        } catch (Throwable t) {
            return -1;
        }
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled()) {
            return;
        }

        long now = System.currentTimeMillis();
        if (now - this.lastSampleMs >= SAMPLE_INTERVAL_MS) {
            this.lastSampleMs = now;
            int ping = this.samplePing();
            if (ping >= 0) {
                this.lastPing = ping;
                this.history[this.cursor % BARS] = Math.min(ping, this.scale.getValue());
                this.cursor++;
            }
        }

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float barW = 2.2f;
        float graphW = BARS * (barW + 0.8f);
        float graphH = 20.0f;
        float x = w - graphW - 14.0f;
        float y = 92.0f;

        boolean hasData = this.cursor > 0;
        for (int i = 0; i < BARS; i++) {
            if (!hasData) {
                break;
            }
            float v = this.history[(this.cursor + i) % BARS];
            if (v <= 0.0f) {
                continue;
            }
            float bh = Math.max(1.0f, v / this.scale.getValue() * graphH);
            int color = v <= this.scale.getValue() * 0.33f ? MenuColors.rgba(110, 220, 130, 200)
                    : v <= this.scale.getValue() * 0.66f ? MenuColors.rgba(255, 200, 90, 210)
                    : MenuColors.rgba(255, 90, 90, 220);
            Render2D.rect(x + i * (barW + 0.8f), y + graphH - bh, barW, bh, 1.0f, color);
        }

        String label = hasData ? this.lastPing + " мс" : "нет данных";
        int labelColor = this.lastPing <= this.scale.getValue() * 0.33f ? MenuColors.rgba(150, 235, 165, 210)
                : this.lastPing <= this.scale.getValue() * 0.66f ? MenuColors.rgba(255, 220, 140, 210)
                : MenuColors.rgba(255, 130, 130, 220);
        Fonts.SEMIBOLD.draw(label, x, y + graphH + 3.0f, 7.5f, labelColor);
        Render2D.flush();
    }
}
