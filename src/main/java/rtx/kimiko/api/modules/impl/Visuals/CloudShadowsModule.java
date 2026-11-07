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
 * Тени облаков: мягкие тёмные пятна плывут по земле в направлении облаков
 * днём — мир ощущается объёмным.
 */
public class CloudShadowsModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/entity/cloud_shadow.png");
    private static final int TEXTURE_SIZE = 32;
    private static final int SHADOW_COUNT = 7;

    private static boolean textureReady;
    private static boolean textureFailed;
    private static final double[] offX = new double[SHADOW_COUNT];
    private static final double[] offZ = new double[SHADOW_COUNT];
    private static final float[] blobSize = new float[SHADOW_COUNT];
    private static boolean initialized;

    private final SliderSetting strength;

    public CloudShadowsModule() {
        super("Cloud Shadows NEW", "Плывущие тени облаков на земле днём.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Заметность теней.").setValue(0.6f).range(0.2f, 1.0f));
    }

    @EventHandler
    private void onWorldRender(WorldRenderEvent event) {
        if (!this.isEnabled() || this.textureFailed) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null) {
            return;
        }
        float night = MathHelper.clamp(world.getAmbientDarkness() / 4.0f, 0.0f, 1.0f);
        float day = 1.0f - night;
        if (day <= 0.05f) {
            return;
        }
        ensureTexture();
        if (!textureReady) {
            return;
        }
        initBlobs();
        long now = System.currentTimeMillis();
        double drift = now * 0.0007; // медленный дрейф по миру

        Vec3d cam = event.getCamera().getCameraPos();
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate = this.mc.getBufferBuilders().getEntityVertexConsumers();
        RenderLayer layer = ClientPipelines.MARKER.apply(TEXTURE);
        VertexConsumer buffer = immediate.getBuffer(layer);

        int drawn = 0;
        for (int i = 0; i < SHADOW_COUNT; i++) {
            // позиция пятна: сетка вокруг игрока + дрейф
            double gx = Math.floor(cam.x / 70.0) * 70.0 + ((i * 13) % 70) + (drift % 70.0);
            double gz = Math.floor(cam.z / 70.0) * 70.0 + ((i * 29) % 70) + (drift * 0.35 % 70.0);
            double dx = gx - cam.x;
            double dz = gz - cam.z;
            if (dx * dx + dz * dz > 75.0 * 75.0) {
                continue;
            }
            int top = FirefliesModule.topY(world, MathHelper.floor(gx), MathHelper.floor(gz));
            if (top <= world.getBottomY() + 1) {
                continue;
            }
            float size = blobSize[i];
            float alpha = this.strength.getValue() * day * 0.16f;

            stack.push();
            stack.translate(gx - cam.x, top - cam.y + 0.04, gz - cam.z);
            stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90.0f));
            MatrixStack.Entry entry = stack.peek();
            float half = size * 0.5f;
            int argb = (((int) (alpha * 255.0f)) << 24) | 0x0A0C14;
            buffer.vertex(entry, -half, -half, 0.0f).texture(0.0f, 0.0f).color(argb);
            buffer.vertex(entry, half, -half, 0.0f).texture(1.0f, 0.0f).color(argb);
            buffer.vertex(entry, half, half, 0.0f).texture(1.0f, 1.0f).color(argb);
            buffer.vertex(entry, -half, half, 0.0f).texture(0.0f, 1.0f).color(argb);
            stack.pop();
            drawn++;
        }
        if (drawn > 0) {
            immediate.draw();
        }
    }

    private static void initBlobs() {
        if (initialized) {
            return;
        }
        initialized = true;
        java.util.Random random = new java.util.Random(4242L);
        for (int i = 0; i < SHADOW_COUNT; i++) {
            blobSize[i] = 14.0f + random.nextFloat() * 20.0f;
        }
    }

    private static void ensureTexture() {
        if (textureReady) {
            return;
        }
        try {
            NativeImage image = new NativeImage(TEXTURE_SIZE, TEXTURE_SIZE, false);
            float half = (TEXTURE_SIZE - 1) * 0.5f;
            for (int y = 0; y < TEXTURE_SIZE; y++) {
                for (int x = 0; x < TEXTURE_SIZE; x++) {
                    float dx = (x - half) / half;
                    float dy = (y - half) / half;
                    float d = (float) Math.sqrt(dx * dx + dy * dy);
                    float a = MathHelper.clamp(1.0f - d * d, 0.0f, 1.0f);
                    image.setColorArgb(x, y, ((int) (a * 255.0f) << 24) | 0xFFFFFF);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko cloud shadow", image);
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
            System.out.println("[KIMIKO] Cloud Shadows: не удалось создать текстуру (" + t + ")");
        }
    }
}
