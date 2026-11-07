package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.player.AttackEntityEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Комбо-счётчик: серия попаданий без получения урона — растёт бейдж
 * под прицелом, сбрасывается через 4 секунды или при получении удара.
 */
public class ComboCounterModule extends Module {
    private final SliderSetting resetSeconds;
    private int combo;
    private long lastHit;
    private int lastHurtTime;

    public ComboCounterModule() {
        super("Combo Counter NEW", "Серия твоих попаданий без ответа — бейдж под прицелом.", Category.DISPLAY);
        this.resetSeconds = (SliderSetting) this.register((Setting) new SliderSetting("Сброс", "Секунд без попаданий до сброса.").setValue(4.0f).range(2.0f, 10.0f));
        this.combo = 0;
    }

    @EventHandler
    private void onAttack(AttackEntityEvent event) {
        if (!this.isEnabled() || event.isSynthetic() || this.mc.player == null) {
            return;
        }
        this.combo++;
        this.lastHit = System.currentTimeMillis();
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (this.mc.player == null) {
            this.combo = 0;
            return;
        }
        // сброс: получил урон или слишком долго без попаданий
        if (this.mc.player.hurtTime > this.lastHurtTime && this.combo > 0) {
            this.combo = 0;
        }
        this.lastHurtTime = this.mc.player.hurtTime;
        if (this.combo > 0 && System.currentTimeMillis() - this.lastHit > (long) (this.resetSeconds.getValue() * 1000.0f)) {
            this.combo = 0;
        }
        if (this.combo < 2 || !this.isEnabled()) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        String label = "×" + this.combo;
        float size = this.combo >= 5 ? 12.0f : 10.0f;
        int color = this.combo >= 5 ? MenuColors.rgba(255, 90, 70, 240) : MenuColors.rgba(235, 240, 250, 230);
        float tw = Fonts.EXTRABOLD.width(label, size);
        Fonts.EXTRABOLD.draw(label, w * 0.5f - tw * 0.5f, h * 0.5f + 22.0f, size, color);
        Render2D.flush();
    }
}
