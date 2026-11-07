package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Chunk Monitor NEW: число загруженных чанков в квадрате 9x9 вокруг игрока
 * и текущий биом.
 */
public class ChunkMonitorModule extends Module {
    private final SliderSetting size;

    public ChunkMonitorModule() {
        super("Chunk Monitor NEW", "Показывает загруженные чанки вокруг тебя и текущий биом.", Category.DISPLAY);
        this.size = (SliderSetting) this.register((Setting) new SliderSetting("Сторона квадрата", "Сторона квадрата чанков (нечётная).").setValue(9.0f).range(3.0f, 15.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.world == null || this.mc.player == null) {
            return;
        }

        int half = Math.max(1, Math.round((this.size.getValue() - 1.0f) * 0.5f));
        int side = half * 2 + 1;
        int total = side * side;
        int loaded = 0;

        int pcx = MathHelper.floor(this.mc.player.getX()) >> 4;
        int pcz = MathHelper.floor(this.mc.player.getZ()) >> 4;
        ClientWorld world = this.mc.world;
        for (int dz = -half; dz <= half; dz++) {
            for (int dx = -half; dx <= half; dx++) {
                if (world.getChunkManager().isChunkLoaded(pcx + dx, pcz + dz)) {
                    loaded++;
                }
            }
        }

        String biome = this.biomeName();
        String chunks = "Чанки: " + loaded + "/" + total;

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;

        float cardW = Math.max(Fonts.SEMIBOLD.width(chunks, 8.0f), Fonts.REGULAR.width(biome, 7.5f)) + 20.0f;
        float cardH = 32.0f;
        float x = w - cardW - 14.0f;
        float y = 68.0f;

        float frac = total > 0 ? (float) loaded / (float) total : 0.0f;
        int chunkColor = frac > 0.85f ? MenuColors.rgba(120, 225, 140, 225)
                : frac > 0.5f ? MenuColors.rgba(255, 205, 100, 225)
                : MenuColors.rgba(255, 110, 110, 225);

        Render2D.rect(x, y, cardW, cardH, 9.0f, MenuColors.rgba(10, 12, 18, 200));
        Render2D.outline(x, y, cardW, cardH, 9.0f, 1.0f, MenuColors.rgba(255, 255, 255, 24));
        Fonts.SEMIBOLD.draw(chunks, x + 9.0f, y + 6.0f, 8.0f, chunkColor);
        Fonts.REGULAR.draw(biome, x + 9.0f, y + 18.0f, 7.5f, MenuColors.rgba(220, 228, 240, 190));
        Render2D.flush();
    }

    private String biomeName() {
        try {
            return this.mc.world.getBiome(this.mc.player.getBlockPos())
                    .getKey()
                    .map(key -> {
                        String path = key.getValue().getPath();
                        if (path.isEmpty()) {
                            return "unknown";
                        }
                        return path.replace('_', ' ');
                    })
                    .orElse("unknown");
        } catch (Throwable t) {
            return "unknown";
        }
    }
}
