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
 * Глубокие звёзды: плотное звёздное небо с мерцанием и «млечным путём» —
 * сотни звёзд на небесной сфере, часть сгущена в полосу.
 */
public class DeepStarsModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/entity/deep_star.png");
    private static final int TEXTURE_SIZE = 16;
    private static final int STAR_COUNT = 260;
    private static final float SKY_RADIUS = 90.0f;

    private static boolean textureReady;
    private static boolean textureFailed;
    private static float[] starDirs;
    private static float[] starSizes;
    private static float[] starPhases;

    private final SliderSetting density;
    private final SliderSetting twinkle;

    public DeepStarsModule() {
        super("Deep Stars NEW", "Плотное звёздное небо с мерцанием и полосой млечного пути.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Доля видимых звёзд.").setValue(1.0f).range(0.3f, 1.0f));
        this.twinkle = (SliderSetting) this.register((Setting) new SliderSetting("Мерцание", "Сила мерцания звёзд.").setValue(0.6f).range(0.0f, 1.0f));
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
        float clear = 1.0f - MathHelper.clamp(world.getRainGradient(event.getPartialTicks()) * 1.4f, 0.0f, 1.0f);
        float alphaBase = night * clear;
        if (alphaBase <= 0.02f) {
            return;
        }
        ensureTexture();
        if (!textureReady) {
            return;
        }
        initStars();

        long now = System.currentTimeMillis();
        Vec3d cam = event.getCamera().getCameraPos();
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate = this.mc.getBufferBuilders().getEntityVertexConsumers();
        RenderLayer layer = ClientPipelines.MARKER.apply(TEXTURE);
        VertexConsumer buffer = immediate.getBuffer(layer);

        stack.push();
        stack.translate(-cam.x, -cam.y, -cam.z);
        // небесная сфера вращается вместе со временем суток
        float dayFrac = (world.getTimeOfDay() % 24000L) / 24000.0f;
        stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(dayFrac * 360.0f));

        int visible = Math.round(STAR_COUNT * this.density.getValue());
        for (int i = 0; i < visible; i++) {
            float twinkle = 1.0f - this.twinkle.getValue() * (0.35f + 0.3f * (float) Math.sin(now * 0.002 * (1.0f + i % 5 * 0.4f) + starPhases[i]));
            float alpha = alphaBase * twinkle;
            if (alpha <= 0.03f) {
                continue;
            }
            float sx = starDirs[i * 3] * SKY_RADIUS;
            float sy = starDirs[i * 3 + 1] * SKY_RADIUS;
            float sz = starDirs[i * 3 + 2] * SKY_RADIUS;
            float size = starSizes[i] * (0.55f + 0.45f * twinkle);
            int argb = (((int) (alpha * 255.0f)) << 24) | 0xFFFFFF;

            MatrixStack.Entry entry = stack.peek();
            buffer.vertex(entry, sx - size, sy - size, sz).texture(0.0f, 0.0f).color(argb);
            buffer.vertex(entry, sx + size, sy - size, sz).texture(1.0f, 0.0f).color(argb);
            buffer.vertex(entry, sx + size, sy + size, sz).texture(1.0f, 1.0f).color(argb);
            buffer.vertex(entry, sx - size, sy + size, sz).texture(0.0f, 1.0f).color(argb);
        }
        stack.pop();
        immediate.draw();
    }

    /** Детерминированные направления: 60% — равномерно, 40% — полоса «млечного пути». */
    private static void initStars() {
        if (starDirs != null) {
            return;
        }
        starDirs = new float[STAR_COUNT * 3];
        starSizes = new float[STAR_COUNT];
        starPhases = new float[STAR_COUNT];
        java.util.Random random = new java.util.Random(777L);
        // базис полосы млечного пути
        float bx = 1.0f;
        float by = 0.0f;
        float bz = 0.0f;
        float n1x = 0.0f;
        float n1y = 0.55f;
        float n1z = 0.0f;
        for (int i = 0; i < STAR_COUNT; i++) {
            float x;
            float y;
            float z;
            if (random.nextFloat() < 0.4f) {
                // точка на большом круге, наклонённом n1
                float t = random.nextFloat() * (float) (Math.PI * 2.0);
                float spread = (random.nextFloat() - 0.5f) * 0.35f;
                x = (float) Math.cos(t) * bx + (float) Math.sin(t) * 0.0f + n1x * spread;
                y = n1y * spread + 0.12f;
                z = (float) Math.sin(t) * bz + n1z * spread + n1y * 0.2f * spread;
            } else {
                x = random.nextFloat() * 2.0f - 1.0f;
                y = random.nextFloat();
                z = random.nextFloat() * 2.0f - 1.0f;
            }
            float len = (float) Math.sqrt(x * x + y * y + z * z);
            if (len < 1.0E-4f) {
                len = 1.0f;
            }
            starDirs[i * 3] = x / len;
            starDirs[i * 3 + 1] = Math.abs(y) / len * 0.9f + 0.1f;
            starDirs[i * 3 + 2] = z / len;
            starSizes[i] = 0.14f + random.nextFloat() * random.nextFloat() * 0.5f;
            starPhases[i] = random.nextFloat() * 6.28f;
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
                    float a = MathHelper.clamp(1.0f - d, 0.0f, 1.0f);
                    image.setColorArgb(x, y, ((int) (a * a * 255.0f) << 24) | 0xFFFFFF);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko deep star", image);
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
            System.out.println("[KIMIKO] Deep Stars: не удалось создать текстуру (" + t + ")");
        }
    }
}
