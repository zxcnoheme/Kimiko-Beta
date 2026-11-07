package rtx.kimiko.api.modules.impl.Utils;

import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;
import net.minecraft.sound.SoundEvents;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.network.PacketReceiveEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;

/**
 * Пинг чата: упомянули твой ник в чате — мягкий колокольчик.
 */
public class ChatPingModule extends Module {
    private static final long COOLDOWN_MS = 2000L;

    private long lastPingAt;

    public ChatPingModule() {
        super("Chat Ping NEW", "Звук колокольчика, когда в чате упоминают твой ник.", Category.UTILS);
        this.lastPingAt = 0L;
    }

    @EventHandler
    private void onPacketReceive(PacketReceiveEvent event) {
        if (!this.isEnabled() || this.mc.player == null) {
            return;
        }
        ChatMessageS2CPacket packet = event.getPacketAs(ChatMessageS2CPacket.class);
        if (packet == null) {
            return;
        }
        String text;
        try {
            text = packet.unsignedContent() != null
                    ? packet.unsignedContent().getString()
                    : packet.body().content();
        } catch (Throwable t) {
            return;
        }
        if (text == null || text.isEmpty()) {
            return;
        }
        String name = this.mc.player.getGameProfile().name();
        if (name == null || name.isEmpty() || !text.contains(name)) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - this.lastPingAt < COOLDOWN_MS) {
            return;
        }
        this.lastPingAt = now;
        this.mc.getSoundManager().play(PositionedSoundInstance.ui(
                SoundEvents.BLOCK_NOTE_BLOCK_BELL.value(), 1.4f, 0.7f));
    }
}
