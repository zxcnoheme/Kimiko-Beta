package rtx.kimiko.api.modules.impl.Utils;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.client.sound.PositionedSoundInstance;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Напоминания: таймер на N минут — плашка с обратным отсчётом
 * и мягкий сигнал в конце («проверь печку»).
 */
public class RemindersModule extends Module {
    private final ModeSetting minutes;
    private final BindSetting startBind;
    private final SliderSetting yShift;
    private long endsAt;

    public RemindersModule() {
        super("Reminders NEW", "Таймер-напоминание: запусти на N минут — отсчёт и сигнал в конце.", Category.UTILS);
        this.minutes = (ModeSetting) this.register((Setting) new ModeSetting("Время", "Сколько минут отсчитывать.", "10", "5", "10", "15", "20", "30", "60"));
        this.startBind = (BindSetting) this.register((Setting) new BindSetting("Запустить", "Клавиша запуска таймера.").setKey(-1));
        this.yShift = (SliderSetting) this.register((Setting) new SliderSetting("Смещение", "Позиция плашки по высоте.").setValue(70.0f).range(24.0f, 200.0f));
        this.endsAt = 0L;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled() || this.mc.player == null) {
            return;
        }
        boolean down = this.startBind.getValue().isDown(this.mc.getWindow().getHandle());
        if (down && this.endsAt == 0L) {
            int mins = 10;
            try {
                mins = Integer.parseInt(this.minutes.getValue());
            } catch (NumberFormatException ignored) {
            }
            this.endsAt = System.currentTimeMillis() + mins * 60_000L;
        }
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (this.endsAt == 0L || this.mc.player == null) {
            return;
        }
        long left = this.endsAt - System.currentTimeMillis();
        if (left <= 0L) {
            this.endsAt = 0L;
            this.mc.getSoundManager().play(PositionedSoundInstance.ui(SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value(), 1.2f, 0.9f));
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        long sec = (left + 999L) / 1000L;
        String label = "Напоминание через " + String.format("%02d:%02d", sec / 60, sec % 60);
        float textW = Fonts.SEMIBOLD.width(label, 8.5f);
        Render2D.rect(w * 0.5f - textW * 0.5f - 9.0f, this.yShift.getValue(), textW + 18.0f, 15.0f, 7.5f,
                MenuColors.rgba(9, 11, 17, 170));
        Fonts.SEMIBOLD.draw(label, w * 0.5f - textW * 0.5f, this.yShift.getValue() + 4.0f, 8.5f,
                left < 30_000L ? MenuColors.rgba(255, 190, 110, 240) : MenuColors.rgba(235, 240, 250, 220));
        Render2D.flush();
    }
}
