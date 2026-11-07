package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.player.AttackEntityEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

import org.joml.Quaternionf;

/**
 * Стрелка цели: над последней атакованной живой целью висит
 * светло-красный ромб, слегка покачиваясь.
 */
public class TargetArrowModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/entity/target_arrow.png");
    private static final int TEXTURE_SIZE = 16;
    private static final float SIZE = 0.5f;
    private static final int MARKER_COLOR = 0xFFFF8A7A;

    private static boolean textureReady;
    private static boolean textureFailed;

    private final SliderSetting distance;
    private LivingEntity target;

    public TargetArrowModule() {
        super("Target Arrow NEW", "Светло-красный ромб над последней атакованной целью.", Category.VISUALS);
        this.distance = (SliderSetting) this.register((Setting) new SliderSetting("Дистанция", "Максимальная дистанция маркера.").setValue(64.0f).range(16.0f, 128.0f));
    }

    @EventHandler
    private void onAttack(AttackEntityEvent event) {
        if (!this.isEnabled() || event.isSynthetic()) {
            return;
        }
        if (event.getTarget() instanceof LivingEntity living && living.isAlive()) {
            this.target = living;
        }
    }

    @EventHandler
    private void onWorldRender(WorldRenderEvent event) {
        if (!this.isEnabled() || event.isPortalPass() || this.textureFailed) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null || this.target == null || !this.target.isAlive()) {
            return;
        }
        float maxDist = this.distance.getValue();
        double dx = this.target.getX() - this.mc.player.getX();
        double dy = this.target.getY() - this.mc.player.getY();
        double dz = this.target.getZ() - this.mc.player.getZ();
        if (dx * dx + dy * dy + dz * dz > maxDist * maxDist) {
            return;
        }
        ensureTexture();
        if (!textureReady) {
            return;
        }
        Vec3d cam = event.getCamera().getCameraPos();
        Quaternionf camRot = event.getCamera().getRotation();
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate = this.mc.getBufferBuilders().getEntityVertexConsumers();
        RenderLayer layer = ClientPipelines.MARKER.apply(TEXTURE);
        VertexConsumer buffer = immediate.getBuffer(layer);

        float bob = (float) Math.sin(System.currentTimeMillis() * 0.004) * 0.05f;
        float alpha = 0.85f;
        int argb = (((int) (alpha * 255.0f)) << 24) | (MARKER_COLOR & 0xFFFFFF);

        stack.push();
        stack.translate(this.target.getX() - cam.x,
                this.target.getY() - cam.y + this.target.getHeight() + 0.5 + bob,
                this.target.getZ() - cam.z);
        stack.multiply(camRot);
        MatrixStack.Entry entry = stack.peek();
        float s = SIZE * 0.5f;
        buffer.vertex(entry, -s, -s, 0.0f).texture(0.0f, 0.0f).color(argb);
        buffer.vertex(entry, s, -s, 0.0f).texture(1.0f, 0.0f).color(argb);
        buffer.vertex(entry, s, s, 0.0f).texture(1.0f, 1.0f).color(argb);
        buffer.vertex(entry, -s, s, 0.0f).texture(0.0f, 1.0f).color(argb);
        stack.pop();
        immediate.draw();
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
                    // ромб с мягким краем
                    float d = half - (Math.abs(x - half) + Math.abs(y - half));
                    float a = MathHelper.clamp(d * 0.6f, 0.0f, 1.0f);
                    image.setColorArgb(x, y, ((int) (a * 255.0f) << 24) | 0xFFFFFF);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko target arrow", image);
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
            System.out.println("[KIMIKO] Target Arrow: не удалось создать текстуру (" + t + ")");
        }
    }
}
