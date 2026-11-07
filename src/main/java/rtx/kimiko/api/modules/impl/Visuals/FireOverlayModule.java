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
 * Fire Screen FX: когда игрок горит — оранжево-красные языки пламени вдоль
 * нижнего края экрана. Текстура процедурная, альфа пульсирует, UV медленно
 * «прокручивается» для эффекта живого огня.
 */
public class FireOverlayModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/ui/fire_screen_fx.png");
    private static final int TEX_W = 64;
    private static final int TEX_H = 32;
    private static boolean textureReady;
    private static boolean textureFailed;

    private final SliderSetting strength;

    public FireOverlayModule() {
        super("Fire Screen FX NEW", "Языки пламени по нижнему краю экрана, когда игрок горит.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Яркость пламени.").setValue(1.0f).range(0.3f, 2.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null || !this.mc.player.isOnFire() || this.textureFailed) {
            return;
        }
        if (!ensureTexture()) {
            return;
        }
        float now = System.currentTimeMillis();
        // альфа пульсирует
        float pulse = 0.7f + 0.3f * (float) Math.sin(now * 0.005);
        float alpha = MathHelper.clamp(this.strength.getValue() * pulse, 0.0f, 1.0f);
        if (alpha <= 0.02f) {
            return;
        }
        int tint = ((int) (alpha * 255.0f) << 24) | 0xFFFFFF;
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        float fh = h * 0.15f;
        float y0 = h - fh;
        // патч-анимация: сдвиг UV по времени (u0 = t * 0.1); два куска,
        // чтобы выборка не выходила за границы текстуры
        float t = now / 1000.0f * 0.1f;
        float u = t - (float) Math.floor(t);
        float first = w * (1.0f - u);
        Render2D.imageUvNearest(TEXTURE.toString(), 0.0f, y0, first, fh, 0.0f, 0.0f, u, 0.0f, 1.0f, 1.0f, tint);
        if (u > 0.001f) {
            Render2D.imageUvNearest(TEXTURE.toString(), first, y0, w - first, fh, 0.0f, 0.0f, 0.0f, 0.0f, u, 1.0f, tint);
        }
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
            Random random = new Random(0xF12EL);
            float phase1 = random.nextFloat() * 6.2831f;
            float phase2 = random.nextFloat() * 6.2831f;
            NativeImage image = new NativeImage(TEX_W, TEX_H, false);
            for (int x = 0; x < TEX_W; x++) {
                float nu = x / (float) TEX_W;
                // «шум», периодичный по ширине — текстура стыкуется сама с собой
                float n = 0.55f * (0.5f + 0.5f * (float) Math.sin(nu * 6.2831f * 3.0 + phase1))
                        + 0.45f * (0.5f + 0.5f * (float) Math.sin(nu * 6.2831f * 7.0 + phase2));
                // высота языка = шум * (0.5 + rand)
                float amp = MathHelper.clamp(n, 0.0f, 1.0f) * (0.5f + random.nextFloat() * 0.5f);
                int tongue = Math.max(4, Math.round(amp * (TEX_H - 4)));
                int top = TEX_H - tongue;
                for (int y = top; y < TEX_H; y++) {
                    float grad = (y - top) / (float) tongue; // 0 у кончика, 1 у основания
                    int a = (int) (Math.pow(grad, 1.3) * 255.0);
                    float inv = 1.0f - grad;
                    int r = 224 + (int) (31.0f * inv);
                    int g = 40 + (int) (110.0f * inv);
                    int b = 16 + (int) (34.0f * inv);
                    image.setColorArgb(x, y, (a << 24) | (r << 16) | (g << 8) | b);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko fire screen fx", image);
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
            System.out.println("[KIMIKO] Fire Screen FX: не удалось создать текстуру (" + t + ")");
        }
        return textureReady;
    }
}
