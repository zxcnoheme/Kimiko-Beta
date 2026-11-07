package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

import java.util.Random;

/**
 * Капли дождя на экране: в дождь на «стекле» проступают капли —
 * сильнее, если смотреть в небо.
 */
public class RainScreenDropsModule extends Module {
    private static final Identifier DROPS = Identifier.of("kimiko", "textures/ui/rain_screen_drops.png");
    private static boolean dropsReady;
    private static boolean dropsFailed;

    private final SliderSetting strength;

    public RainScreenDropsModule() {
        super("Rain Screen Drops NEW", "Капли дождя на экране, будто смотришь через стекло.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Заметность капель.").setValue(0.6f).range(0.2f, 1.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.world == null || this.mc.player == null) {
            return;
        }
        float rain = this.mc.world.getRainGradient(1.0f);
        if (rain <= 0.4f || this.mc.player.isSubmergedInWater()) {
            return;
        }
        // взгляд вверх — капель «больше»: экран ловит дождь напрямую
        float pitch = this.mc.player.getPitch();
        float upFactor = 1.0f + Math.max(0.0f, -pitch / 90.0f) * 0.8f;
        float alpha = rain * upFactor * this.strength.getValue() * 0.4f;
        if (alpha < 0.02f) {
            return;
        }
        if (!ensureDrops()) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        Render2D.imageUvNearest(DROPS.toString(), 0.0f, 0.0f, w, h, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f,
                MenuColors.rgba(205, 228, 255, (int) (255 * alpha)));
        Render2D.flush();
    }

    private static boolean ensureDrops() {
        if (dropsReady) {
            return true;
        }
        if (dropsFailed) {
            return false;
        }
        try {
            Random random = new Random(777001L);
            NativeImage image = new NativeImage(64, 64, true);
            // «стекающие» капли: вытянутые вертикальные штрихи с круглой головкой
            for (int i = 0; i < 22; i++) {
                int cx = 3 + random.nextInt(58);
                int cy = 3 + random.nextInt(58);
                int rh = 2 + random.nextInt(5);
                for (int y = cy; y < Math.min(64, cy + rh); y++) {
                    float fade = 1.0f - (y - cy) / (float) rh;
                    int x0 = cx - (y == cy ? 1 : 0);
                    for (int x = Math.max(0, x0); x < Math.min(64, cx + (y == cy ? 2 : 1)); x++) {
                        int a = (int) ((y == cy ? 170 : 120 * fade));
                        int base = image.getColorArgb(x, y) & 0xFFFFFF;
                        if (a > (base >>> 24) || (base >>> 24) == 0) {
                            image.setColorArgb(x, y, (Math.max(a, 70) << 24) | 0xD4E6FF);
                        }
                    }
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko rain screen drops", image);
            tex.upload();
            if (tex.getGlTextureView() == null && tex.getGlTexture() != null) {
                com.mojang.blaze3d.textures.GpuTextureView view =
                        com.mojang.blaze3d.systems.RenderSystem.getDevice().createTextureView(tex.getGlTexture());
                ((mixin.skins.AbstractTextureAccessor) (Object) tex).kimiko$setView(view);
            }
            MinecraftClient.getInstance().getTextureManager().registerTexture(DROPS, tex);
            dropsReady = true;
        } catch (Throwable t) {
            dropsFailed = true;
            System.out.println("[KIMIKO] Rain Screen Drops: не удалось создать текстуру (" + t + ")");
        }
        return dropsReady;
    }
}
