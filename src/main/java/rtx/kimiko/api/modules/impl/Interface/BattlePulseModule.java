package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.player.AttackEntityEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * Battle Stats NEW: карточка боя — время в бою и число твоих ударов в окне 5 с.
 * Карточка держится 6 с после последнего удара; сбрасывается при получении урона.
 */
public class BattlePulseModule extends Module {
    private static final long WINDOW_NANOS = 5_000_000_000L;
    private static final long HOLD_NANOS = 6_000_000_000L;

    private final SliderSetting showTime;
    private final ArrayDeque<Long> hits = new ArrayDeque<>();
    private long lastHitNanos;
    private boolean wasHurt;

    public BattlePulseModule() {
        super("Battle Stats NEW", "Карточка боя: время в бою и твои удары за окно 5 секунд.", Category.DISPLAY);
        this.showTime = (SliderSetting) this.register((Setting) new SliderSetting("Секунд боя", "Сколько секунд боя показывать до скрытия.").setValue(5.0f).range(3.0f, 10.0f));
        this.lastHitNanos = 0L;
        this.wasHurt = false;
    }

    @EventHandler
    private void onAttack(AttackEntityEvent event) {
        if (!this.isEnabled() || event.isSynthetic() || this.mc.player == null) {
            return;
        }
        long now = System.nanoTime();
        this.hits.addLast(now);
        this.lastHitNanos = now;
        while (this.hits.size() > 64) {
            this.hits.pollFirst();
        }
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled() || this.mc.player == null) {
            return;
        }
        // сброс серии при получении урона
        boolean hurt = this.mc.player.hurtTime > 0;
        if (hurt && !this.wasHurt) {
            this.hits.clear();
        }
        this.wasHurt = hurt;

        // чистка старых ударов
        long now = System.nanoTime();
        long window = (long) (this.showTime.getValue() * 1_000_000_000L);
        Iterator<Long> it = this.hits.iterator();
        while (it.hasNext()) {
            if (now - it.next() > Math.max(WINDOW_NANOS, window)) {
                it.remove();
            } else {
                break;
            }
        }
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null) {
            return;
        }
        long now = System.nanoTime();
        if (this.hits.isEmpty() || now - this.lastHitNanos > HOLD_NANOS) {
            return;
        }

        long window = (long) (this.showTime.getValue() * 1_000_000_000L);
        long oldest = this.hits.peekFirst();
        float combatSeconds = Math.max(0.0f, Math.min(this.showTime.getValue(),
                (now - oldest) / 1_000_000_000.0f));
        int hitCount = this.hits.size();

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;

        String text = "В бою: " + String.format(java.util.Locale.US, "%.1f", combatSeconds) + "с · Удары: " + hitCount;
        float cardW = Fonts.SEMIBOLD.width(text, 8.5f) + 20.0f;
        float cardH = 22.0f;
        float x = (w - cardW) * 0.5f;
        float y = 14.0f;

        Render2D.rect(x, y, cardW, cardH, 8.0f, MenuColors.rgba(20, 10, 10, 205));
        Render2D.outline(x, y, cardW, cardH, 8.0f, 1.0f, MenuColors.rgba(255, 120, 110, 60));
        Fonts.SEMIBOLD.draw(text, x + 10.0f, y + (cardH - 9.0f) * 0.5f, 8.5f, MenuColors.rgba(255, 190, 180, 235));
        Render2D.flush();
    }
}
