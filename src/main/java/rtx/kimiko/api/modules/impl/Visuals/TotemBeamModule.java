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
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.player.TotemPopEvent;
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
 * Луч тотема: когда у игрока срабатывает тотем бессмертия,
 * на месте игрока на пару секунд вспыхивает золотой столб света.
 */
public class TotemBeamModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/entity/totem_beam.png");
    private static final int TEX_WIDTH = 32;
    private static final int TEX_HEIGHT = 64;
    private static final long BEAM_MS = 2000L;
    private static final float BEAM_WIDTH = 0.6f;
    private static final float BEAM_HEIGHT = 4.0f;

    private static boolean textureReady;
    private static boolean textureFailed;

    /** Активный луч: позиция и время появления. */
    private record Beam(double x, double y, double z, long startMs) {
    }

    private final SliderSetting strength;
    private final List<Beam> beams = new ArrayList<>();

    public TotemBeamModule() {
        super("Totem Beam NEW", "Золотой столб света на месте срабатывания тотема бессмертия.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Яркость луча.").setValue(1.0f).range(0.3f, 2.0f));
    }

    @EventHandler
    private void onTotemPop(TotemPopEvent event) {
        if (!this.isEnabled() || this.mc.player == null || event.getEntity() != this.mc.player) {
            return;
        }
        this.beams.add(new Beam(this.mc.player.getX(), this.mc.player.getY(), this.mc.player.getZ(),
                System.currentTimeMillis()));
    }

    @EventHandler
    private void onWorldRender(WorldRenderEvent event) {
        if (!this.isEnabled() || event.isPortalPass() || this.beams.isEmpty() || this.textureFailed) {
            return;
        }
        ensureTexture();
        if (!textureReady) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null) {
            return;
        }
        long now = System.currentTimeMillis();
        Vec3d cam = event.getCamera().getCameraPos();
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate = this.mc.getBufferBuilders().getEntityVertexConsumers();
        VertexConsumer buffer = immediate.getBuffer(ClientPipelines.MARKER.apply(TEXTURE));
        Iterator<Beam> it = this.beams.iterator();
        while (it.hasNext()) {
            Beam beam = it.next();
            float t = (now - beam.startMs()) / (float) BEAM_MS;
            if (t >= 1.0f) {
                it.remove();
                continue;
            }
            float alpha = MathHelper.clamp((1.0f - t) * 0.8f * this.strength.getValue(), 0.0f, 1.0f);
            int argb = (((int) (alpha * 255.0f)) << 24) | 0xFFE8A84B;
            float hw = BEAM_WIDTH * 0.5f;
            stack.push();
            stack.translate(beam.x() - cam.x, beam.y() - cam.y + BEAM_HEIGHT * 0.5f, beam.z() - cam.z);
            // билборд: квад всегда повёрнут к камере
            stack.multiply(event.getCamera().getRotation());
            MatrixStack.Entry entry = stack.peek();
            buffer.vertex(entry, -hw, -BEAM_HEIGHT * 0.5f, 0.0f).texture(0.0f, 1.0f).color(argb);
            buffer.vertex(entry, hw, -BEAM_HEIGHT * 0.5f, 0.0f).texture(1.0f, 1.0f).color(argb);
            buffer.vertex(entry, hw, BEAM_HEIGHT * 0.5f, 0.0f).texture(1.0f, 0.0f).color(argb);
            buffer.vertex(entry, -hw, BEAM_HEIGHT * 0.5f, 0.0f).texture(0.0f, 0.0f).color(argb);
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
            float halfW = (TEX_WIDTH - 1) * 0.5f;
            for (int y = 0; y < TEX_HEIGHT; y++) {
                for (int x = 0; x < TEX_WIDTH; x++) {
                    // вертикальный градиент: ярче у земли, тает к небу, мягкие края
                    float base = y / (float) (TEX_HEIGHT - 1);
                    float across = Math.abs(x - halfW) / halfW;
                    float a = base * (1.0f - across * across);
                    image.setColorArgb(x, y, ((int) (Math.max(0.0f, a) * 255.0f) << 24) | 0xFFFFFF);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko totem beam", image);
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
            System.out.println("[KIMIKO] Totem Beam: не удалось создать текстуру (" + t + ")");
        }
    }
}
