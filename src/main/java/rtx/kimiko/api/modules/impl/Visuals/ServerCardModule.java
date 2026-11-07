package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.network.ServerInfo;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Карточка сервера: при подключении сверху экрана на несколько секунд
 * появляется адрес сервера, пинг и тикающее время сессии.
 */
public class ServerCardModule extends Module {
    private static final long CARD_MS = 4000L;
    private static final long FADE_IN_MS = 250L;
    private static final long FADE_OUT_MS = 600L;

    private boolean hadHandler;
    private long connectedAt;

    public ServerCardModule() {
        super("Server Card NEW", "Карточка сервера с адресом, пингом и временем сессии при входе.", Category.VISUALS);
        this.connectedAt = 0L;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        if (this.mc.getNetworkHandler() == null) {
            this.hadHandler = false;
            return;
        }
        if (!this.hadHandler) {
            this.hadHandler = true;
            this.connectedAt = System.currentTimeMillis();
        }
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.connectedAt == 0L || this.mc.getNetworkHandler() == null || this.mc.world == null) {
            return;
        }
        long age = System.currentTimeMillis() - this.connectedAt;
        if (age > CARD_MS) {
            return;
        }
        float fadeIn = Math.min(age / (float) FADE_IN_MS, 1.0f);
        float fadeOut = Math.min((CARD_MS - age) / (float) FADE_OUT_MS, 1.0f);
        float fade = Math.min(fadeIn, fadeOut);
        if (fade <= 0.0f) {
            return;
        }

        ServerInfo entry = this.mc.getCurrentServerEntry();
        boolean single = entry == null || entry.address == null || entry.address.isEmpty();
        StringBuilder text = new StringBuilder();
        if (single) {
            text.append("Одиночный мир");
        } else {
            text.append(entry.address);
            int ping = this.ownPing();
            text.append("  ·  ").append(ping).append(" мс");
        }
        long seconds = age / 1000L;
        text.append("  ·  ").append(String.format("%02d:%02d", seconds / 60L, seconds % 60L));

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float textW = Fonts.SEMIBOLD.width(text.toString(), 8.0f);
        Render2D.rect(w * 0.5f - textW * 0.5f - 8.0f, 6.0f, textW + 16.0f, 14.0f, 7.0f,
                MenuColors.rgba(9, 11, 17, (int) (150 * fade)));
        Fonts.SEMIBOLD.draw(text.toString(), w * 0.5f - textW * 0.5f, 9.5f, 8.0f,
                MenuColors.rgba(235, 240, 250, (int) (220 * fade)));
        Render2D.flush();
    }

    /** Пинг своей записи в списке игроков (0 в одиночке). */
    private int ownPing() {
        if (this.mc.player == null || this.mc.getNetworkHandler() == null) {
            return 0;
        }
        PlayerListEntry info = this.mc.getNetworkHandler().getPlayerListEntry(this.mc.player.getUuid());
        return info == null ? 0 : Math.max(0, info.getLatency());
    }
}
