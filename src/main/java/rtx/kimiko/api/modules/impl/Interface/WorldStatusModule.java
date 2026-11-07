package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Статус мира: игровое время, счётчик дней и онлайн сервера —
 * компактная строка сверху экрана.
 */
public class WorldStatusModule extends Module {
    private final BooleanSetting showTime;
    private final BooleanSetting showDay;
    private final BooleanSetting showOnline;

    public WorldStatusModule() {
        super("World Status NEW", "Игровое время, номер дня и онлайн сервера — одна строка сверху.", Category.DISPLAY);
        this.showTime = (BooleanSetting) this.register((Setting) new BooleanSetting("Время", "Игровое время в формате чч:мм.", true));
        this.showDay = (BooleanSetting) this.register((Setting) new BooleanSetting("День", "Счётчик игровых дней.", true));
        this.showOnline = (BooleanSetting) this.register((Setting) new BooleanSetting("Онлайн", "Игроков на сервере.", true));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.world == null || this.mc.player == null) {
            return;
        }
        StringBuilder text = new StringBuilder();
        if (this.showTime.getValue()) {
            long ticks = this.mc.world.getTimeOfDay() % 24000L;
            int hours = (int) ((ticks / 1000L + 6L) % 24L);
            int minutes = (int) (ticks % 1000L * 60L / 1000L);
            text.append(String.format("%02d:%02d", hours, minutes));
        }
        if (this.showDay.getValue()) {
            if (text.length() > 0) {
                text.append("  ·  ");
            }
            text.append("День ").append(this.mc.world.getTime() / 24000L + 1L);
        }
        if (this.showOnline.getValue()) {
            if (text.length() > 0) {
                text.append("  ·  ");
            }
            if (this.mc.getNetworkHandler() != null) {
                int online = 0;
                for (PlayerListEntry entry : this.mc.getNetworkHandler().getPlayerList()) {
                    online++;
                }
                text.append("Онлайн: ").append(online);
            } else {
                text.append("Одиночная игра");
            }
        }
        if (text.length() == 0) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float textW = Fonts.SEMIBOLD.width(text.toString(), 8.0f);
        Render2D.rect(w * 0.5f - textW * 0.5f - 8.0f, 6.0f, textW + 16.0f, 14.0f, 7.0f,
                MenuColors.rgba(9, 11, 17, 150));
        Fonts.SEMIBOLD.draw(text.toString(), w * 0.5f - textW * 0.5f, 9.5f, 8.0f,
                MenuColors.rgba(235, 240, 250, 220));
        Render2D.flush();
    }
}
