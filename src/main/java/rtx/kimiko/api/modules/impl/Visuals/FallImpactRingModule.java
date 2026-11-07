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
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Кольцо от жёсткого приземления: после падения с высоты
 * (fallDistance > 3) по земле расходится светящееся MARKER-кольцо,
 * радиус растёт до 1.5 м за 400 мс, альфа плавно тает.
 */
public class FallImpactRingModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/entity/fall_impact_ring.png");
    private static final int TEXTURE_SIZE = 64;
    private static final long RING_MS = 400L;
    private static final float MAX_RADIUS = 1.5f;

    private static boolean textureReady;
    private static boolean textureFailed;

    /** Активное кольцо: позиция и время появления. */
    private record Ring(double x, double y, double z, long startMs) { }

    private final SliderSetting strength;
    private final List<Ring> rings = new ArrayList<>();
    private double prevFallDistance;

    public FallImpactRingModule() {
        super("Fall Impact Ring NEW", "Расширяющееся кольцо на земле после жёсткого приземления.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Размер и яркость кольца.").setValue(1.0f).range(0.4f, 2.0f));
        this.prevFallDistance = 0.0;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null) {
            this.rings.clear();
            this.prevFallDistance = 0.0;
            return;
        }
        if (this.isEnabled() && this.mc.player.isOnGround() && this.prevFallDistance > 3.0) {
            Vec3d pos = this.mc.player.getLerpedPos(1.0f);
            this.rings.add(new Ring(pos.x, pos.y + 0.02, pos.z, System.currentTimeMillis()));
        }
        this.prevFallDistance = this.mc.player.fallDistance;
    }

    @EventHandler
    private void onWorldRender(WorldRenderEvent event) {
        if (!this.isEnabled() || event.isPortalPass() || this.rings.isEmpty() || this.textureFailed) {
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
        VertexConsumer buffer = immediate.getBuffer(ClientPipelines.MARKER.apply(TEXTURE));
        Iterator<Ring> it = this.rings.iterator();
        while (it.hasNext()) {
            Ring ring = it.next();
            float t = (now - ring.startMs()) / (float) RING_MS;
            if (t >= 1.0f) {
                it.remove();
                continue;
            }
            float radius = MAX_RADIUS * this.strength.getValue() * MathHelper.sqrt(t);
            float alpha = (1.0f - t) * 0.75f;
            int argb = (((int) (alpha * 255.0f)) << 24) | 0xFFFFFF;
            stack.push();
            stack.translate(ring.x() - cam.x, ring.y() - cam.y, ring.z() - cam.z);
            stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90.0f));
            MatrixStack.Entry entry = stack.peek();
            buffer.vertex(entry, -radius, -radius, 0.0f).texture(0.0f, 0.0f).color(argb);
            buffer.vertex(entry, radius, -radius, 0.0f).texture(1.0f, 0.0f).color(argb);
            buffer.vertex(entry, radius, radius, 0.0f).texture(1.0f, 1.0f).color(argb);
            buffer.vertex(entry, -radius, radius, 0.0f).texture(0.0f, 1.0f).color(argb);
            stack.pop();
        }
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
                    float dx = (x - half) / half;
                    float dy = (y - half) / half;
                    float d = (float) Math.sqrt(dx * dx + dy * dy);
                    // кольцо шириной ~2px на радиусе 70%
                    float band = MathHelper.clamp(1.0f - Math.abs(d - 0.7f) / 0.045f, 0.0f, 1.0f);
                    image.setColorArgb(x, y, ((int) (band * 255.0f) << 24) | 0xFFFFFF);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko fall impact ring", image);
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
            System.out.println("[KIMIKO] Fall Impact Ring: не удалось создать текстуру (" + t + ")");
        }
    }
}
