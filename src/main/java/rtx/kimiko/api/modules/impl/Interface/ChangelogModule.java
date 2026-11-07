package rtx.kimiko.api.modules.impl.Interface;

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
 * Changelog NEW: по бинду открывает HUD-панель «Что нового v1.6» слева —
 * список главных фич релиза. Закрывается тем же биндом; Esc не участвует
 * (панель — часть HUD, экран игры не открывает).
 */
public class ChangelogModule extends Module {
    private static final String[] LINES = {
            "Миникарта и вейпоинты",
            "20+ новых визуалов",
            "Графики FPS и сети",
            "Таймеры эффектов",
            "Автобэкап конфигов",
            "Мастер миграции настроек",
            "Предупреждение о снарядах",
            "Быстрые сообщения",
            "Хрома выстрела и фокус лука",
            "Гость блока"
    };

    private final BindSetting toggleBind;
    private boolean open;
    private boolean lastDown;
    private long openedAt;

    public ChangelogModule() {
        super("Changelog NEW", "Панель «Что нового v1.6» со списком главных изменений релиза.", Category.DISPLAY);
        this.toggleBind = (BindSetting) this.register((Setting) new BindSetting("Клавиша", "Открыть или закрыть панель изменений.").setKey(-1));
        this.open = false;
        this.lastDown = false;
        this.openedAt = 0L;
    }

    @Override
    protected void onDisable() {
        this.open = false;
        this.lastDown = false;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        boolean down = this.toggleBind.getValue().isDown(this.mc.getWindow().getHandle());
        if (down && !this.lastDown) {
            this.open = !this.open;
            this.openedAt = System.currentTimeMillis();
        }
        this.lastDown = down;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || !this.open) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float h = this.mc.getWindow().getScaledHeight() / sf;

        // мягкое появление
        long since = System.currentTimeMillis() - this.openedAt;
        float appear = Math.max(0.0f, Math.min(1.0f, since / 180.0f));
        int base = (int) (170.0f * appear);

        float x = 14.0f;
        float y = h * 0.28f;
        float w = 168.0f;
        float pad = 10.0f;
        float titleH = 17.0f;
        float lineH = 11.5f;
        float panelH = titleH + LINES.length * lineH + pad;

        // панель: полупрозрачное тёмное стекло слева
        Render2D.rect(x, y, w, panelH, 9.0f, MenuColors.rgba(9, 11, 17, base));
        Render2D.rect(x, y, 2.2f, panelH, 1.1f,
                MenuColors.rgba(120, 200, 255, (int) (200.0f * appear)));
        Render2D.rect(x + 2.2f, y, w - 2.2f, 0.8f, 0.0f,
                MenuColors.rgba(255, 255, 255, (int) (26.0f * appear)));

        Fonts.EXTRABOLD.draw("Что нового", x + pad + 2.0f, y + 4.0f, 10.5f,
                MenuColors.rgba(255, 255, 255, (int) (245.0f * appear)));
        String version = "v1.6";
        float versionW = Fonts.SEMIBOLD.width(version, 8.0f);
        Fonts.SEMIBOLD.draw(version, x + w - versionW - pad - 2.0f, y + 6.0f, 8.0f,
                MenuColors.rgba(120, 200, 255, (int) (220.0f * appear)));

        float ly = y + titleH + 1.0f;
        for (int i = 0; i < LINES.length; i++) {
            float rowIn = Math.max(0.0f, Math.min(1.0f, (since - 60.0f - i * 22.0f) / 140.0f));
            float indent = (1.0f - rowIn) * 5.0f;
            Render2D.circle(x + pad + 2.6f, ly + 5.4f, 1.1f, 8,
                    MenuColors.rgba(120, 200, 255, (int) (190.0f * appear * rowIn)));
            Fonts.SEMIBOLD.draw(LINES[i], x + pad + 8.0f + indent, ly + 1.6f, 8.0f,
                    MenuColors.rgba(226, 232, 244, (int) (215.0f * appear * rowIn)));
            ly += lineH;
        }
        Render2D.flush();
    }
}
