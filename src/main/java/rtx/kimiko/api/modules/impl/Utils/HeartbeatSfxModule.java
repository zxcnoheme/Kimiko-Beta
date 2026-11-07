package rtx.kimiko.api.modules.impl.Utils;

import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Сердцебиение: когда сердец почти нет — барабан отбивает пульс,
 * тем быстрее, чем ближе смерть.
 */
public class HeartbeatSfxModule extends Module {
    private final BooleanSetting soundEnabled;
    private final SliderSetting volume;
    private long nextBeatAt;

    public HeartbeatSfxModule() {
        super("Heartbeat Sfx NEW", "Звук сердцебиения при низком здоровье, темп растёт.", Category.UTILS);
        this.soundEnabled = this.register(new BooleanSetting("Звук", "Включить звук сердцебиения.").setValue(true));
        this.volume = (SliderSetting) this.register((Setting) new SliderSetting("Громкость", "Громкость звука.").setValue(0.7f).range(0.1f, 1.0f));
        this.nextBeatAt = 0L;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled() || !this.soundEnabled.getValue() || this.mc.player == null) {
            return;
        }
        float hp = this.mc.player.getHealth();
        float max = this.mc.player.getMaxHealth();
        if (hp <= 0.0f || hp >= 6.0f || max <= 0.0f) {
            this.nextBeatAt = 0L;
            return;
        }
        long now = System.currentTimeMillis();
        if (this.nextBeatAt == 0L) {
            this.nextBeatAt = now;
        }
        if (now < this.nextBeatAt) {
            return;
        }
        // интервал сжимается с падением HP: 600 мс при пороге, всё быстрее к смерти
        float frac = Math.min(1.0f, hp / max);
        long interval = Math.max(200L, (long) (600.0f * frac));
        this.nextBeatAt = now + interval;
        this.mc.getSoundManager().play(PositionedSoundInstance.ui(
                SoundEvents.BLOCK_NOTE_BLOCK_BASEDRUM.value(), 0.8f, 0.5f * this.volume.getValue()));
    }
}
