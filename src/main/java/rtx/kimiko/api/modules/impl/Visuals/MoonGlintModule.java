package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

import java.util.ArrayList;
import java.util.List;

/**
 * Блик луны на воде: ночью по поверхности воды тянется мерцающая
 * светлая дорожка в сторону луны.
 */
public class MoonGlintModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/entity/moon_glint.png");
    private static final int TEXTURE_SIZE = 32;

    private static boolean textureReady;
    private static boolean textureFailed;
    private static final List<float[]> waterSpots = new ArrayList<>();

    private final SliderSetting strength;

    public MoonGlintModule() {
        super("Moon Glint NEW", "Мерцающая лунная дорожка на воде ночью.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Яркость", "Яркость дорожки.").setValue(0.6f).range(0.2f, 1.0f));
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
        float clear = 1.0f - MathHelper.clamp(world.getRainGradient(event.getPartialTicks()), 0.0f, 1.0f);
        float moonAlpha = night * clear;
        if (moonAlpha <= 0.05f) {
            return;
        }
        scanWater(world, event.getCamera().getCameraPos());
        if (waterSpots.isEmpty()) {
            return;
        }
        ensureTexture();
        if (!textureReady) {
            return;
        }

        // азимут луны: привязан ко времени суток
        float dayFrac = (world.getTimeOfDay() % 24000L) / 24000.0f;
        float moonAzimuth = dayFrac * (float) (Math.PI * 2.0);
        float dirX = MathHelper.cos(moonAzimuth);
        float dirZ = MathHelper.sin(moonAzimuth);

        long now = System.currentTimeMillis();
        Vec3d cam = event.getCamera().getCameraPos();
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate = this.mc.getBufferBuilders().getEntityVertexConsumers();
        RenderLayer layer = ClientPipelines.MARKER.apply(TEXTURE);
        VertexConsumer buffer = immediate.getBuffer(layer);

        int drawn = 0;
        for (float[] spot : waterSpots) {
            double dx = spot[0] - cam.x;
            double dy = spot[1] - cam.y;
            double dz = spot[2] - cam.z;
            if (dx * dx + dy * dy + dz * dz > 40.0 * 40.0) {
                continue;
            }
            float flicker = 0.55f + 0.45f * (float) Math.sin(now * 0.003 + spot[3] * 7.0f);
            float alpha = moonAlpha * this.strength.getValue() * 0.4f * flicker;
            if (alpha <= 0.02f) {
                continue;
            }
            float length = 2.2f + spot[3] * 2.0f;
            float width = 0.35f + spot[3] * 0.3f;

            stack.push();
            stack.translate(spot[0] - cam.x, spot[1] - cam.y + 0.05, spot[2] - cam.z);
            stack.multiply(net.minecraft.util.math.RotationAxis.POSITIVE_Y.rotationDegrees(
                    (float) Math.toDegrees(Math.atan2(dirZ, dirX))));
            stack.multiply(net.minecraft.util.math.RotationAxis.POSITIVE_X.rotationDegrees(-90.0f));
            MatrixStack.Entry entry = stack.peek();
            int argb = (((int) (alpha * 255.0f)) << 24) | 0xDCE9FF;
            buffer.vertex(entry, -length, -width, 0.0f).texture(0.0f, 0.0f).color(argb);
            buffer.vertex(entry, length, -width, 0.0f).texture(1.0f, 0.0f).color(argb);
            buffer.vertex(entry, length, width, 0.0f).texture(1.0f, 1.0f).color(argb);
            buffer.vertex(entry, -length, width, 0.0f).texture(0.0f, 1.0f).color(argb);
            stack.pop();
            drawn++;
        }
        if (drawn > 0) {
            immediate.draw();
        }
    }

    /** Поиск воды рядом — случайная выборка колонок, стабильная в пределах секунды. */
    private static void scanWater(ClientWorld world, Vec3d cam) {
        waterSpots.clear();
        // простая выборка случайных колонок вокруг игрока
        java.util.Random random = new java.util.Random(world.getTime() / 20);
        for (int i = 0; i < 24; i++) {
            double x = cam.x + (random.nextDouble() - 0.5) * 24.0;
            double z = cam.z + (random.nextDouble() - 0.5) * 24.0;
            int bx = MathHelper.floor(x);
            int bz = MathHelper.floor(z);
            int top = FirefliesModule.topY(world, bx, bz);
            if (top <= world.getBottomY() + 1) {
                continue;
            }
            BlockPos below = new BlockPos(bx, top - 1, bz);
            if (world.getFluidState(below).isIn(FluidTags.WATER)) {
                waterSpots.add(new float[]{(float) x, (float) top, (float) z, random.nextFloat()});
            }
        }
    }

    private static void ensureTexture() {
        if (textureReady) {
            return;
        }
        try {
            NativeImage image = new NativeImage(TEXTURE_SIZE, TEXTURE_SIZE, false);
            float halfY = (TEXTURE_SIZE - 1) * 0.5f;
            for (int y = 0; y < TEXTURE_SIZE; y++) {
                for (int x = 0; x < TEXTURE_SIZE; x++) {
                    float across = Math.abs(y - halfY) / halfY;
                    float along = x / (float) (TEXTURE_SIZE - 1);
                    // яркое пятно по центру, хвост к краю
                    float a = MathHelper.clamp(1.0f - across * across, 0.0f, 1.0f)
                            * MathHelper.clamp(1.3f - along * 1.3f, 0.0f, 1.0f);
                    image.setColorArgb(x, y, ((int) (a * 255.0f) << 24) | 0xFFFFFF);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko moon glint", image);
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
            System.out.println("[KIMIKO] Moon Glint: не удалось создать текстуру (" + t + ")");
        }
    }
}
