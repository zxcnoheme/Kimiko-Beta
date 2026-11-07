package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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
 * Панель диагностики: клавиша тогглит компактную левую карточку
 * с FPS, координатами, чанком, биомом, временем, пингом и сущностями рядом.
 */
public class DiagnosticsPanelModule extends Module {
    private static final int ROWS = 7;
    private static final float ROW_H = 11.0f;
    private static final float TEXT_SIZE = 8.0f;

    private final BindSetting bind;
    private boolean panelOn;
    private boolean lastDown;
    private String cachedBiome = "";
    private int cachedPing;
    private boolean pingKnown;
    private int cachedEntities;
    private int cacheCounter;

    public DiagnosticsPanelModule() {
        super("F3 Panel NEW", "Клавиша тогглит левую панель диагностики: FPS, XYZ, чанк, биом, время, пинг, сущности.", Category.DISPLAY);
        this.bind = (BindSetting) this.register((Setting) new BindSetting("Клавиша", "Клавиша показа панели диагностики.").setKey(-1));
        this.panelOn = false;
        this.lastDown = false;
        this.pingKnown = false;
        this.cacheCounter = 0;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled() || this.mc.player == null) {
            return;
        }
        boolean down = this.bind.getValue().isDown(this.mc.getWindow().getHandle());
        if (down && !this.lastDown) {
            this.panelOn = !this.panelOn;
        }
        this.lastDown = down;
        if (++this.cacheCounter >= 10) {
            this.cacheCounter = 0;
            this.refreshCache();
        }
    }

    private void refreshCache() {
        if (this.mc.world == null || this.mc.player == null) {
            return;
        }
        this.cachedBiome = this.mc.world.getBiome(this.mc.player.getBlockPos())
                .getKey().map(k -> k.getValue().getPath()).orElse("");
        this.cachedPing = 0;
        this.pingKnown = false;
        try {
            if (this.mc.getNetworkHandler() != null) {
                PlayerListEntry entry = this.mc.getNetworkHandler().getPlayerListEntry(this.mc.player.getUuid());
                if (entry != null) {
                    this.cachedPing = Math.max(0, entry.getLatency());
                    this.pingKnown = true;
                }
            }
        } catch (Throwable ignored) {
        }
        int near = 0;
        for (Entity entity : this.mc.world.getEntities()) {
            if (entity == this.mc.player || !(entity instanceof LivingEntity) || !entity.isAlive()) {
                continue;
            }
            if (entity.squaredDistanceTo(this.mc.player) <= 16.0 * 16.0) {
                near++;
            }
        }
        this.cachedEntities = near;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || !this.panelOn || this.mc.world == null || this.mc.player == null) {
            return;
        }
        String[] labels = {"FPS", "XYZ", "Chunk", "Biome", "Time", "Ping", "Mobs"};
        String[] values = new String[ROWS];
        values[0] = String.valueOf(this.mc.getCurrentFps());
        values[1] = String.format("%.1f / %.1f / %.1f",
                this.mc.player.getX(), this.mc.player.getY(), this.mc.player.getZ());
        values[2] = (this.mc.player.getBlockPos().getX() >> 4) + " / " + (this.mc.player.getBlockPos().getZ() >> 4);
        String biome = this.cachedBiome;
        if (biome.length() > 22) {
            biome = biome.substring(0, 22);
        }
        values[3] = biome.isEmpty() ? "-" : biome;
        long ticks = this.mc.world.getTimeOfDay() % 24000L;
        int hours = (int) ((ticks / 1000L + 6L) % 24L);
        int minutes = (int) (ticks % 1000L * 60L / 1000L);
        values[4] = String.format("%02d:%02d", hours, minutes);
        values[5] = this.pingKnown ? this.cachedPing + " ms" : "-";
        values[6] = String.valueOf(this.cachedEntities);

        float valueCol = 66.0f;
        float width = valueCol;
        for (String value : values) {
            width = Math.max(width, valueCol + Fonts.SEMIBOLD.width(value, TEXT_SIZE) + 10.0f);
        }
        float height = ROWS * ROW_H + 10.0f;
        float x = 10.0f;
        float y = 10.0f;

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        Render2D.rect(x, y, width, height, 8.0f, MenuColors.rgba(9, 11, 17, 150));
        for (int i = 0; i < ROWS; i++) {
            float rowY = y + 5.0f + i * ROW_H + 2.0f;
            Fonts.SEMIBOLD.draw(labels[i], x + 9.0f, rowY, TEXT_SIZE, MenuColors.rgba(140, 150, 170, 200));
            Fonts.SEMIBOLD.draw(values[i], x + valueCol, rowY, TEXT_SIZE, MenuColors.rgba(235, 240, 250, 220));
        }
        Render2D.flush();
    }
}
