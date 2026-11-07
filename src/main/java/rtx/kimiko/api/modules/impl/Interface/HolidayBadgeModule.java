package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

import java.time.LocalDate;
import java.time.MonthDay;

/**
 * Праздничный бейдж: в особые даты рядом с ватермаркой появляется
 * маленькая праздничная плашка.
 */
public class HolidayBadgeModule extends Module {
    private String cachedKey;
    private String label;
    private int color;

    public HolidayBadgeModule() {
        super("Holiday Badge NEW", "Праздничная плашка рядом с ватермаркой в особые даты.", Category.DISPLAY);
        this.cachedKey = null;
        this.label = null;
        this.color = 0;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || !this.resolveHoliday(LocalDate.now())) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float textW = Fonts.SEMIBOLD.width(this.label, 8.0f);
        float x = w - textW - 16.0f;
        float y = 24.0f;
        Render2D.rect(x - 7.0f, y - 3.0f, textW + 14.0f, 14.0f, 7.0f, MenuColors.rgba(9, 11, 17, 165));
        Fonts.SEMIBOLD.draw(this.label, x, y, 8.0f, MenuColors.withAlpha(this.color, 235));
        Render2D.flush();
    }

    /** Обновляет label/color по дате; возвращает true, если сегодня праздник. */
    private boolean resolveHoliday(LocalDate date) {
        String key = MonthDay.from(date).toString();
        if (key.equals(this.cachedKey)) {
            return this.label != null;
        }
        this.cachedKey = key;
        this.label = null;
        MonthDay today = MonthDay.from(date);
        if (today.getMonthValue() == 10 && today.getDayOfMonth() == 31) {
            this.label = "Halloween!";
            this.color = MenuColors.rgba(255, 150, 60, 255);
        } else if ((today.getMonthValue() == 12 && today.getDayOfMonth() == 31)
                || (today.getMonthValue() == 1 && today.getDayOfMonth() <= 7)) {
            this.label = "Winter!";
            this.color = MenuColors.rgba(150, 210, 255, 255);
        } else if (today.getMonthValue() == 2 && today.getDayOfMonth() == 14) {
            this.label = "Valentine!";
            this.color = MenuColors.rgba(255, 110, 150, 255);
        }
        return this.label != null;
    }
}
