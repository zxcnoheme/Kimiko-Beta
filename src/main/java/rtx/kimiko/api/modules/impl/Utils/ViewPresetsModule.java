package rtx.kimiko.api.modules.impl.Utils;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * View Presets NEW: клавиша циклично применяет готовые пресеты вида
 * (FOV / яркость / чувствительность мыши) и показывает плашку 1.5 с.
 */
public class ViewPresetsModule extends Module {
    private static final String[] NAMES = {"PvP", "Кино", "Стройка"};
    private static final int[] FOV = {90, 70, 80};
    private static final double[] GAMMA = {0.0, 0.3, 0.6};
    private static final double[] SENS = {0.5, 0.3, 0.4};

    private final BindSetting bind;
    private int index;
    private long plateUntilNanos;
    private boolean lastDown;

    public ViewPresetsModule() {
        super("View Presets NEW", "Клавиша циклично применяет пресеты вида: PvP / Кино / Стройка (FOV, яркость, чувствительность).", Category.UTILS);
        this.bind = (BindSetting) this.register((Setting) new BindSetting("Клавиша", "Следующий пресет.").setKey(-1));
        this.index = 0;
        this.plateUntilNanos = 0L;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        boolean down = this.bind.getValue().isDown(this.mc.getWindow().getHandle());
        if (down && !this.lastDown) {
            this.index = (this.index + 1) % NAMES.length;
            this.apply();
            this.plateUntilNanos = System.nanoTime() + 1_500_000_000L;
        }
        this.lastDown = down;
    }

    private void apply() {
        try {
            this.mc.options.getFov().setValue(FOV[this.index]);
            this.mc.options.getGamma().setValue(GAMMA[this.index]);
            this.mc.options.getMouseSensitivity().setValue(SENS[this.index]);
        } catch (Throwable t) {
            System.out.println("[KIMIKO] ViewPresets: не удалось применить пресет (" + t + ")");
        }
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || System.nanoTime() >= this.plateUntilNanos) {
            return;
        }

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;

        String title = NAMES[this.index];
        String sub = "FOV " + FOV[this.index] + " · яркость " + Math.round(GAMMA[this.index] * 100.0) + "% · сенса " + Math.round(SENS[this.index] * 100.0) + "%";
        float cardW = Math.max(Fonts.SEMIBOLD.width(title, 11.0f), Fonts.REGULAR.width(sub, 7.5f)) + 24.0f;
        float cardH = 34.0f;
        float x = (w - cardW) * 0.5f;
        float y = 46.0f;

        long left = this.plateUntilNanos - System.nanoTime();
        float fade = Math.max(0.0f, Math.min(1.0f, left / 300_000_000.0f));
        int a = Math.round(235 * fade);

        Render2D.rect(x, y, cardW, cardH, 10.0f, MenuColors.rgba(11, 13, 19, a));
        Render2D.outline(x, y, cardW, cardH, 10.0f, 1.0f, MenuColors.rgba(255, 255, 255, (int) (26 * fade)));
        Fonts.SEMIBOLD.draw(title, x + (cardW - Fonts.SEMIBOLD.width(title, 11.0f)) * 0.5f, y + 6.0f, 11.0f, MenuColors.rgba(255, 255, 255, a));
        Fonts.REGULAR.draw(sub, x + (cardW - Fonts.REGULAR.width(sub, 7.5f)) * 0.5f, y + 20.0f, 7.5f, MenuColors.rgba(220, 228, 240, (int) (190 * fade)));
        Render2D.flush();
    }
}
