package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Статистика сессии: время в игре, пройденная дистанция, прыжки —
 * компактная карточка.
 */
public class SessionStatsModule extends Module {
    private final BooleanSetting showTime;
    private final BooleanSetting showDistance;
    private final BooleanSetting showJumps;

    private long startMs;
    private double lastX;
    private double lastZ;
    private double distance;
    private int jumps;
    private boolean wasOnGround;

    public SessionStatsModule() {
        super("Session Stats NEW", "Время сессии, пройденная дистанция и прыжки — карточка сбоку.", Category.DISPLAY);
        this.showTime = (BooleanSetting) this.register((Setting) new BooleanSetting("Время", "Сколько длится сессия.", true));
        this.showDistance = (BooleanSetting) this.register((Setting) new BooleanSetting("Дистанция", "Пройдено блоков.", true));
        this.showJumps = (BooleanSetting) this.register((Setting) new BooleanSetting("Прыжки", "Количество прыжков.", true));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || this.mc.player == null) {
            return;
        }
        if (startMs == 0L) {
            this.startMs = System.currentTimeMillis();
            this.lastX = this.mc.player.getX();
            this.lastZ = this.mc.player.getZ();
        }
        double dx = this.mc.player.getX() - this.lastX;
        double dz = this.mc.player.getZ() - this.lastZ;
        this.distance += Math.sqrt(dx * dx + dz * dz);
        this.lastX = this.mc.player.getX();
        this.lastZ = this.mc.player.getZ();
        boolean onGround = this.mc.player.isOnGround();
        if (onGround && !this.wasOnGround) {
            this.jumps++;
        }
        this.wasOnGround = onGround;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.world == null || this.mc.player == null || startMs == 0L) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float x = 14.0f;
        float y = 30.0f;
        float lineH = 12.0f;
        int rows = 0;
        if (this.showTime.getValue()) {
            rows++;
        }
        if (this.showDistance.getValue()) {
            rows++;
        }
        if (this.showJumps.getValue()) {
            rows++;
        }
        float pad = 8.0f;
        float boxH = rows * lineH + pad * 2.0f - 3.0f;
        Render2D.rect(x, y, 120.0f, boxH, 8.0f, MenuColors.rgba(9, 11, 17, 150));
        float ty = y + pad;
        if (this.showTime.getValue()) {
            long sec = (System.currentTimeMillis() - startMs) / 1000L;
            String time = String.format("Сессия: %02d:%02d:%02d", sec / 3600, sec % 3600 / 60, sec % 60);
            Fonts.SEMIBOLD.draw(time, x + pad, ty, 7.5f, MenuColors.rgba(235, 240, 250, 210));
            ty += lineH;
        }
        if (this.showDistance.getValue()) {
            Fonts.SEMIBOLD.draw("Путь: " + (int) distance + " м", x + pad, ty, 7.5f, MenuColors.rgba(235, 240, 250, 210));
            ty += lineH;
        }
        if (this.showJumps.getValue()) {
            Fonts.SEMIBOLD.draw("Прыжки: " + jumps, x + pad, ty, 7.5f, MenuColors.rgba(235, 240, 250, 210));
        }
        Render2D.flush();
    }
}
