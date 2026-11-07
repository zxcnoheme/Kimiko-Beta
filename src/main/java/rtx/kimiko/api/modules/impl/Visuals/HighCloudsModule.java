package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

/**
 * Высокие перистые облака: пять больших мягких «перьев» заметно выше
 * ванильного слоя облаков. Медленно дрейфуют по X, днём гуще, ночью реже,
 * в дождь исчезают. Видны только когда игрок ниже облачного слоя.
 */
public class HighCloudsModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/entity/high_clouds.png");
    private static final int TEXTURE_SIZE = 128;
    private static final int CLOUD_COUNT = 5;
    private static final float WRAP_SPAN = 480.0f;
    private static final float SKIP_ABOVE_Y = 180.0f;

    private static boolean textureReady;
    private static boolean textureFailed;

    private final SliderSetting strength;

    public HighCloudsModule() {
        super("High Clouds NEW", "Перистые облака выше ванильных: мягкие «перья» медленно плывут в вышине.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Яркость облаков.").setValue(1.0f).range(0.2f, 2.0f));
    }

    @EventHandler
    private void onWorldRender(WorldRenderEvent event) {
        if (!this.isEnabled() || event.isPortalPass()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null || textureFailed) {
            return;
        }
        if (this.mc.player.getY() > SKIP_ABOVE_Y) {
            return;
        }
        float rain = world.getRainGradient(1.0f);
        if (rain > 0.5f) {
            return;
        }
        float night = MathHelper.clamp(world.getAmbientDarkness() / 4.0f, 0.0f, 1.0f);
        float alpha = (0.10f + (0.03f - 0.10f) * night) * this.strength.getValue();
        if (alpha <= 0.004f) {
            return;
        }
        ensureTexture();
        if (!textureReady) {
            return;
        }
        float drift = world.getTime() * 0.0005f;
        Vec3d cam = event.getCamera().getCameraPos();
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate = this.mc.getBufferBuilders().getEntityVertexConsumers();
        VertexConsumer buffer = immediate.getBuffer(ClientPipelines.MARKER.apply(TEXTURE));
        int argb = (((int) (alpha * 255.0f)) << 24) | 0xFFFFFF;

        for (int i = 0; i < CLOUD_COUNT; i++) {
            float halfW = 28.0f + i * 7.0f;
            float halfD = 10.0f + i * 2.5f;
            float y = (float) cam.y + 70.0f + i * 5.0f;
            float seedX = (i * 97.0f) % WRAP_SPAN;
            float x = wrap(seedX + drift * (30.0f + i * 6.0f)) - WRAP_SPAN * 0.5f;
            float z = (i * 83.0f) % 260.0f - 130.0f;
            float u0 = (i % 2) * 0.5f;
            float u1 = u0 + 0.5f;
            stack.push();
            stack.translate(x, y - cam.y, z);
            stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90.0f));
            MatrixStack.Entry entry = stack.peek();
            putCorner(buffer, entry, -halfW, -halfD, u0, 0.0f, argb);
            putCorner(buffer, entry, halfW, -halfD, u1, 0.0f, argb);
            putCorner(buffer, entry, halfW, halfD, u1, 1.0f, argb);
            putCorner(buffer, entry, -halfW, halfD, u0, 1.0f, argb);
            stack.pop();
        }
        immediate.draw();
    }

    private static void putCorner(VertexConsumer buffer, MatrixStack.Entry entry, float x, float z, float u, float v, int argb) {
        buffer.vertex(entry, x, z, 0.0f)
                .texture(u, v)
                .color(argb);
    }

    private static float wrap(float value) {
        return ((value % WRAP_SPAN) + WRAP_SPAN) % WRAP_SPAN;
    }

    private static float gauss(float v, float center, float spread) {
        float d = v - center;
        return (float) Math.exp(-(d * d) / (2.0f * spread * spread));
    }

    private static void ensureTexture() {
        if (textureReady) {
            return;
        }
        try {
            NativeImage image = new NativeImage(TEXTURE_SIZE, TEXTURE_SIZE, false);
            for (int y = 0; y < TEXTURE_SIZE; y++) {
                float v = y / (float) (TEXTURE_SIZE - 1);
                for (int x = 0; x < TEXTURE_SIZE; x++) {
                    float u = x / (float) (TEXTURE_SIZE - 1);
                    // несколько горизонтальных размытых полос — «перья»
                    float bands = gauss(v, 0.22f, 0.09f) * 0.95f
                            + gauss(v, 0.45f, 0.06f) * 0.75f
                            + gauss(v, 0.66f, 0.11f) * 0.65f
                            + gauss(v, 0.86f, 0.05f) * 0.50f;
                    float edge = MathHelper.clamp(Math.min(u, 1.0f - u) * 7.0f, 0.0f, 1.0f);
                    float wisp = 0.72f + 0.28f * (float) Math.sin(u * 24.0f + v * 11.0f);
                    float a = MathHelper.clamp(bands * edge * wisp, 0.0f, 1.0f);
                    image.setColorArgb(x, y, (((int) (a * 255.0f)) << 24) | 0xFFFFFF);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko high clouds", image);
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
            System.out.println("[KIMIKO] High Clouds: не удалось создать текстуру (" + t + ")");
        }
    }
}
