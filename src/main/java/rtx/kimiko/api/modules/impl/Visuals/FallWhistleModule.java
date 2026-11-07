package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Свист падения: при быстром падении слышен нарастающий свист ветра.
 */
public class FallWhistleModule extends Module {
    private final SliderSetting volume;
    private int cooldown;

    public FallWhistleModule() {
        super("Fall Whistle NEW", "Свист ветра при быстром падении — громкость по скорости.", Category.VISUALS);
        this.volume = (SliderSetting) this.register((Setting) new SliderSetting("Громкость", "Громкость свиста.").setValue(0.5f).range(0.1f, 1.0f));
        this.cooldown = 0;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientPlayerEntity player = this.mc.player;
        if (player == null || player.isGliding() || player.isOnGround()) {
            return;
        }
        double fallSpeed = -player.getVelocity().y;
        if (fallSpeed < 0.55 || this.cooldown > 0) {
            if (this.cooldown > 0) {
                this.cooldown--;
            }
            return;
        }
        this.cooldown = 45;
        float pitch = MathHelper.clamp(0.9f + (float) fallSpeed * 0.35f, 1.0f, 1.8f);
        float vol = MathHelper.clamp((float) fallSpeed * 0.5f, 0.0f, 1.0f) * this.volume.getValue();
        if (vol <= 0.02f) {
            return;
        }
        this.mc.getSoundManager().play(PositionedSoundInstance.ui(SoundEvents.ITEM_ELYTRA_FLYING, pitch, vol));
    }
}
