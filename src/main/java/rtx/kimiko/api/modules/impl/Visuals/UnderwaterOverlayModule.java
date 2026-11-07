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
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

import java.util.Random;

/**
 * Подводный оверлей: свой вид под водой — синева, капли на стекле или чисто.
 */
public class UnderwaterOverlayModule extends Module {
    private static final Identifier DROPS = Identifier.of("kimiko", "textures/ui/water_drops.png");
    private static boolean dropsReady;
    private static boolean dropsFailed;

    private final ModeSetting style;
    private final SliderSetting strength;

    public UnderwaterOverlayModule() {
        super("Underwater View NEW", "Свой вид под водой: мягкая синева, капли на «стекле» или чистая картинка.", Category.VISUALS);
        this.style = (ModeSetting) this.register((Setting) new ModeSetting("Стиль", "Как выглядит подводный мир.", "Синева + капли", "Синева", "Синева + капли", "Чисто"));
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Насколько сильно заливать синевой.").setValue(0.6f).range(0.2f, 1.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null) {
            return;
        }
        if (!this.mc.player.isSubmergedInWater()) {
            return;
        }
        String styleNow = this.style.getValue();
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;

        if (!"Чисто".equals(styleNow)) {
            float alpha = 0.16f * this.strength.getValue();
            Render2D.rect(0.0f, 0.0f, w, h, 0.0f,
                    MenuColors.rgba(30, 90, 150, (int) (255 * alpha)),
                    MenuColors.rgba(30, 90, 150, (int) (255 * alpha)),
                    MenuColors.rgba(16, 60, 110, (int) (255 * alpha * 1.4f)),
                    MenuColors.rgba(16, 60, 110, (int) (255 * alpha * 1.4f)));
        }
        if ("Синева + капли".equals(styleNow)) {
            if (ensureDrops()) {
                Render2D.imageUvNearest(DROPS.toString(), 0.0f, 0.0f, w, h, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f,
                        MenuColors.rgba(210, 235, 255, (int) (255 * 0.35f * this.strength.getValue())));
            }
        }
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
            Random random = new Random(12345L);
            NativeImage image = new NativeImage(64, 64, true);
            // несколько «капель»: светлые овалы со смещением
            for (int i = 0; i < 26; i++) {
                int cx = 4 + random.nextInt(56);
                int cy = 4 + random.nextInt(56);
                int rw = 1 + random.nextInt(2);
                int rh = 3 + random.nextInt(6);
                for (int y = Math.max(0, cy - rh); y < Math.min(64, cy + rh); y++) {
                    for (int x = Math.max(0, cx - rw); x < Math.min(64, cx + rw + 1); x++) {
                        float dx = (x - cx) / (float) rw;
                        float dy = (y - cy) / (float) rh;
                        float d = dx * dx + dy * dy;
                        if (d <= 1.0f) {
                            int a = (int) ((1.0f - d) * 150.0f);
                            int base = image.getColorArgb(x, y) & 0xFFFFFF;
                            if (a > (base >>> 24) || (base >>> 24) == 0) {
                                image.setColorArgb(x, y, (Math.max(a, 60) << 24) | 0xD8ECFF);
                            }
                        }
                    }
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko water drops", image);
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
            System.out.println("[KIMIKO] Underwater View: не удалось создать текстуру (" + t + ")");
        }
        return dropsReady;
    }
}
