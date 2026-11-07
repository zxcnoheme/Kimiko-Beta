package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

import org.joml.Quaternionf;

/**
 * Северное сияние: ночью в снежных биомах высоко в небе колышутся
 * широкие полупрозрачные зелёно-бирюзовые ленты.
 */
public class AuroraModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/entity/aurora_curtain.png");
    private static final int TEX_WIDTH = 32;
    private static final int TEX_HEIGHT = 64;
    private static final int[] COLORS = {0xFF54FFB0, 0xFF59C8FF};
    private static final int RIBBONS = 4;
    private static final float RIBBON_WIDTH = 12.0f;
    private static final float RIBBON_HEIGHT = 22.0f;
    private static final float SKY_OFFSET = 40.0f;

    private static boolean textureReady;
    private static boolean textureFailed;

    private final SliderSetting brightness;

    public AuroraModule() {
        super("Aurora NEW", "Зелёно-бирюзовые ленты северного сияния ночью в снежных биомах.", Category.VISUALS);
        this.brightness = (SliderSetting) this.register((Setting) new SliderSetting("Яркость", "Яркость сияния.").setValue(1.0f).range(0.3f, 2.0f));
    }

    @EventHandler
    private void onWorldRender(WorldRenderEvent event) {
        if (!this.isEnabled() || event.isPortalPass() || this.textureFailed) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null) {
            return;
        }
        float temp = world.getBiome(this.mc.player.getBlockPos()).value().getTemperature();
        if (temp >= 0.2f) {
            return;
        }
        int darkness = world.getAmbientDarkness();
        if (darkness < 3) {
            return;
        }
        float nightFade = MathHelper.clamp((darkness - 3) / 2.0f, 0.0f, 1.0f);
        ensureTexture();
        if (!textureReady) {
            return;
        }

        long now = System.currentTimeMillis();
        Vec3d cam = event.getCamera().getCameraPos();
        Quaternionf camRot = event.getCamera().getRotation();
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate = this.mc.getBufferBuilders().getEntityVertexConsumers();
        RenderLayer layer = ClientPipelines.MARKER.apply(TEXTURE);
        VertexConsumer buffer = immediate.getBuffer(layer);

        for (int i = 0; i < RIBBONS; i++) {
            float phase = i * 1.7f;
            // медленное колыхание ленты по горизонтали
            float wave = (float) Math.sin(now * 0.00025 + phase) * 6.0f
                    + (float) Math.sin(now * 0.00011 + phase * 2.0f) * 3.0f;
            float cx = (float) cam.x + (i - (RIBBONS - 1) * 0.5f) * 9.0f + wave;
            float cz = (float) cam.z - 26.0f + (i % 2) * 4.0f;
            float cy = (float) cam.y + SKY_OFFSET;
            float flicker = 0.09f + 0.03f * (float) Math.sin(now * 0.0004 + phase * 3.0f);
            float alpha = MathHelper.clamp(flicker * this.brightness.getValue() * nightFade, 0.0f, 0.14f);
            if (alpha <= 0.01f) {
                continue;
            }
            int argb = (((int) (alpha * 255.0f)) << 24) | (COLORS[i % COLORS.length] & 0xFFFFFF);

            stack.push();
            stack.translate(cx - cam.x, cy - cam.y, cz - cam.z);
            stack.multiply(camRot);
            MatrixStack.Entry entry = stack.peek();
            float hw = RIBBON_WIDTH * 0.5f;
            float hh = RIBBON_HEIGHT * 0.5f;
            buffer.vertex(entry, -hw, -hh, 0.0f).texture(0.0f, 0.0f).color(argb);
            buffer.vertex(entry, hw, -hh, 0.0f).texture(1.0f, 0.0f).color(argb);
            buffer.vertex(entry, hw, hh, 0.0f).texture(1.0f, 1.0f).color(argb);
            buffer.vertex(entry, -hw, hh, 0.0f).texture(0.0f, 1.0f).color(argb);
            stack.pop();
        }
        immediate.draw();
    }

    private static void ensureTexture() {
        if (textureReady) {
            return;
        }
        try {
            NativeImage image = new NativeImage(TEX_WIDTH, TEX_HEIGHT, false);
            for (int y = 0; y < TEX_HEIGHT; y++) {
                // вертикальный градиент альфы: у нижней кромки ярче
                float v = y / (float) (TEX_HEIGHT - 1);
                float vertical = 0.15f + 0.85f * (float) Math.pow(1.0f - v, 1.5);
                for (int x = 0; x < TEX_WIDTH; x++) {
                    float u = x / (float) (TEX_WIDTH - 1);
                    float across = MathHelper.clamp(1.0f - Math.abs(u - 0.5f) * 2.0f, 0.0f, 1.0f);
                    float a = (float) Math.sqrt(across) * vertical;
                    image.setColorArgb(x, y, ((int) (a * 255.0f) << 24) | 0xFFFFFF);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko aurora curtain", image);
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
            System.out.println("[KIMIKO] Aurora: не удалось создать текстуру (" + t + ")");
        }
    }
}
