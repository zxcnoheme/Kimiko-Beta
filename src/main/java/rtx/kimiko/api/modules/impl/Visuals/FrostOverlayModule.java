package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.render.render2d.Render2D;

import java.util.Random;

/**
 * Иней на экране: в холодных биомах по краям экрана нарастают ледяные узоры.
 */
public class FrostOverlayModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/ui/frost.png");
    private static boolean textureReady;
    private static boolean textureFailed;

    private final SliderSetting strength;

    public FrostOverlayModule() {
        super("Frost Overlay NEW", "Ледяные узоры по краям экрана в холодных биомах.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Насколько сильно намерзает иней.").setValue(0.6f).range(0.2f, 1.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null || this.mc.world == null) {
            return;
        }
        float temp;
        try {
            temp = this.mc.world.getBiome(this.mc.player.getBlockPos()).value().getTemperature();
        } catch (Throwable ignored) {
            return;
        }
        float cold = MathHelper.clamp((0.4f - temp) / 0.4f, 0.0f, 1.0f);
        if (cold <= 0.05f) {
            return;
        }
        if (!ensureTexture()) {
            return;
        }
        float alpha = cold * this.strength.getValue() * 0.55f;
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        // рамка-иней: растянутая текстура, прозрачная к центру кадра
        int tint = ((int) (MathHelper.clamp(alpha, 0.0f, 1.0f) * 255.0f) << 24) | 0xFFFFFF;
        Render2D.imageUvNearest(TEXTURE.toString(), 0.0f, 0.0f, w, h, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, tint);
        Render2D.flush();
    }

    private static boolean ensureTexture() {
        if (textureReady) {
            return true;
        }
        if (textureFailed) {
            return false;
        }
        try {
            Random random = new Random(99L);
            NativeImage image = new NativeImage(64, 64, false);
            // морозные кристаллы: ветвящиеся светлые нити от краёв, к центру затухают
            for (int i = 0; i < 60; i++) {
                int edge = random.nextInt(4);
                int len = 14 + random.nextInt(22);
                int x = edge == 0 ? 0 : edge == 1 ? 63 : random.nextInt(64);
                int y = edge == 2 ? 0 : edge == 3 ? 63 : random.nextInt(64);
                float dirX = edge == 0 ? 1.0f : edge == 1 ? -1.0f : (random.nextFloat() - 0.5f);
                float dirY = edge == 2 ? 1.0f : edge == 3 ? -1.0f : (random.nextFloat() - 0.5f);
                float norm = MathHelper.sqrt(dirX * dirX + dirY * dirY);
                dirX /= norm;
                dirY /= norm;
                for (int step = 0; step < len; step++) {
                    int px = MathHelper.clamp(x + (int) (dirX * step), 0, 63);
                    int py = MathHelper.clamp(y + (int) (dirY * step), 0, 63);
                    float fade = 1.0f - (float) step / (float) len;
                    int a = (int) (fade * 200.0f);
                    int prev = image.getColorArgb(px, py) >>> 24;
                    if (a > prev) {
                        image.setColorArgb(px, py, (a << 24) | 0xCFEBFF);
                    }
                    // ответвление
                    if (random.nextInt(6) == 0) {
                        float bx = dirX + (random.nextFloat() - 0.5f) * 1.2f;
                        float by = dirY + (random.nextFloat() - 0.5f) * 1.2f;
                        int bx2 = MathHelper.clamp(px + (int) (bx * 3), 0, 63);
                        int by2 = MathHelper.clamp(py + (int) (by * 3), 0, 63);
                        int prev2 = image.getColorArgb(bx2, by2) >>> 24;
                        int a2 = (int) (fade * 130.0f);
                        if (a2 > prev2) {
                            image.setColorArgb(bx2, by2, (a2 << 24) | 0xCFEBFF);
                        }
                    }
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko frost", image);
            tex.upload();
            if (tex.getGlTextureView() == null && tex.getGlTexture() != null) {
                com.mojang.blaze3d.textures.GpuTextureView view =
                        com.mojang.blaze3d.systems.RenderSystem.getDevice().createTextureView(tex.getGlTexture());
                ((mixin.skins.AbstractTextureAccessor) (Object) tex).kimiko$setView(view);
            }
            MinecraftClient.getInstance().getTextureManager().registerTexture(TEXTURE, tex);
            textureReady = true;
        } catch (Throwable t) {
            textureFailed = true;
            System.out.println("[KIMIKO] Frost Overlay: не удалось создать текстуру (" + t + ")");
        }
        return textureReady;
    }
}
