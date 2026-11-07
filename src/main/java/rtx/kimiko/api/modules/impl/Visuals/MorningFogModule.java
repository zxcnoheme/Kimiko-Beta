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
 * Утренний туман: на рассвете над землёй лежат широкие пласты тумана,
 * которые тают к полудню.
 */
public class MorningFogModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/entity/fog_bank.png");
    private static final int TEXTURE_SIZE = 32;

    private static boolean textureReady;
    private static boolean textureFailed;

    private final SliderSetting strength;

    public MorningFogModule() {
        super("Morning Fog NEW", "Пласты тумана над землёй на рассвете, тают к полудню.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Густота тумана.").setValue(0.6f).range(0.2f, 1.0f));
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
        float dayFrac = (world.getTimeOfDay() % 24000L) / 24000.0f;
        // рассветное окно: 0.94–1.0 и 0.0–0.10 (06:00 ± час-полтора)
        float morning = bellAt(dayFrac - 1.0f, 0.985f, 0.045f) + bellAt(dayFrac, 0.015f, 0.045f);
        if (morning <= 0.03f) {
            return;
        }
        ensureTexture();
        if (!textureReady) {
            return;
        }
        long now = System.currentTimeMillis();
        Vec3d cam = event.getCamera().getCameraPos();
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate = this.mc.getBufferBuilders().getEntityVertexConsumers();
        RenderLayer layer = ClientPipelines.MARKER.apply(TEXTURE);
        VertexConsumer buffer = immediate.getBuffer(layer);

        int drawn = 0;
        for (int i = 0; i < 6; i++) {
            double ang = i * Math.PI / 3.0 + dayFrac * 6.0;
            double fx = cam.x + Math.cos(ang) * (10.0 + (i % 3) * 6.0);
            double fz = cam.z + Math.sin(ang) * (10.0 + (i % 3) * 6.0);
            int top = FirefliesModule.topY(world, MathHelper.floor(fx), MathHelper.floor(fz));
            if (top <= world.getBottomY() + 1) {
                continue;
            }
            float alpha = morning * this.strength.getValue() * 0.22f;
            float w = 10.0f + (i % 2) * 4.0f;
            float drift = (now * 0.0004f % 1.0f) * 2.0f;

            stack.push();
            stack.translate(fx - cam.x + drift, top - cam.y + 0.7, fz - cam.z);
            stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(i * 51.0f));
            stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90.0f));
            MatrixStack.Entry entry = stack.peek();
            int argb = (((int) (alpha * 255.0f)) << 24) | 0xE8EEF5;
            buffer.vertex(entry, -w, -2.0f, 0.0f).texture(0.0f, 0.0f).color(argb);
            buffer.vertex(entry, w, -2.0f, 0.0f).texture(1.0f, 0.0f).color(argb);
            buffer.vertex(entry, w, 2.0f, 0.0f).texture(1.0f, 1.0f).color(argb);
            buffer.vertex(entry, -w, 2.0f, 0.0f).texture(0.0f, 1.0f).color(argb);
            stack.pop();
            drawn++;
        }
        if (drawn > 0) {
            immediate.draw();
        }
    }

    private static float bellAt(float value, float center, float radius) {
        float d = Math.abs(value - center);
        if (d > radius) {
            return 0.0f;
        }
        float t = 1.0f - d / radius;
        return t * t;
    }

    private static void ensureTexture() {
        if (textureReady) {
            return;
        }
        try {
            NativeImage image = new NativeImage(TEXTURE_SIZE, TEXTURE_SIZE, false);
            for (int y = 0; y < TEXTURE_SIZE; y++) {
                for (int x = 0; x < TEXTURE_SIZE; x++) {
                    // мягкая горизонтальная полоса: яркая в середине, тает к краям
                    float across = 1.0f - Math.abs(y - (TEXTURE_SIZE - 1) * 0.5f) / ((TEXTURE_SIZE - 1) * 0.5f);
                    float along = MathHelper.clamp(1.2f - Math.abs(x - (TEXTURE_SIZE - 1) * 0.5f) / ((TEXTURE_SIZE - 1) * 0.5f), 0.0f, 1.0f);
                    float a = MathHelper.clamp(across * across * along, 0.0f, 1.0f);
                    image.setColorArgb(x, y, ((int) (a * 255.0f) << 24) | 0xFFFFFF);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko fog bank", image);
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
            System.out.println("[KIMIKO] Morning Fog: не удалось создать текстуру (" + t + ")");
        }
    }
}
