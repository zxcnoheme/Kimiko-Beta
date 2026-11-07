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
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.player.AttackEntityEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

import org.joml.Quaternionf;

/**
 * Эхо смерти: на месте последней убитой цели на пару секунд
 * остаётся полупрозрачный серо-белый столб-призрак.
 */
public class DeathEchoModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/entity/death_echo.png");
    private static final int TEX_WIDTH = 16;
    private static final int TEX_HEIGHT = 32;
    private static final float WIDTH = 0.8f;
    private static final float HEIGHT = 2.0f;

    private static boolean textureReady;
    private static boolean textureFailed;

    private final SliderSetting showSec;
    private LivingEntity target;
    private boolean wasAlive;
    private double deathX;
    private double deathY;
    private double deathZ;
    private long deathAt;

    public DeathEchoModule() {
        super("Death Echo NEW", "Столб-призрак на месте последней убитой цели.", Category.VISUALS);
        this.showSec = (SliderSetting) this.register((Setting) new SliderSetting("Время", "Сколько секунд висит эхо.").setValue(2.5f).range(1.0f, 5.0f));
    }

    @EventHandler
    private void onAttack(AttackEntityEvent event) {
        if (!this.isEnabled() || event.isSynthetic()) {
            return;
        }
        if (event.getTarget() instanceof LivingEntity living) {
            this.target = living;
            this.wasAlive = living.isAlive();
        }
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        if (this.target == null) {
            return;
        }
        boolean alive = this.target.isAlive();
        if (this.wasAlive && !alive) {
            this.deathX = this.target.getX();
            this.deathY = this.target.getY();
            this.deathZ = this.target.getZ();
            this.deathAt = System.currentTimeMillis();
        }
        this.wasAlive = alive;
        if (!alive && System.currentTimeMillis() - this.deathAt > this.showSec.getValue() * 1000.0f + 2000.0f) {
            this.target = null;
        }
    }

    @EventHandler
    private void onWorldRender(WorldRenderEvent event) {
        if (!this.isEnabled() || event.isPortalPass() || this.deathAt == 0L || this.textureFailed) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null) {
            return;
        }
        long showMs = (long) (this.showSec.getValue() * 1000.0f);
        long age = System.currentTimeMillis() - this.deathAt;
        if (age > showMs) {
            return;
        }
        ensureTexture();
        if (!textureReady) {
            return;
        }
        float t = (float) age / (float) showMs;
        float alpha = (1.0f - t) * (1.0f - t) * 0.55f;
        if (alpha <= 0.02f) {
            return;
        }
        Vec3d cam = event.getCamera().getCameraPos();
        Quaternionf camRot = event.getCamera().getRotation();
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate = this.mc.getBufferBuilders().getEntityVertexConsumers();
        RenderLayer layer = ClientPipelines.MARKER.apply(TEXTURE);
        VertexConsumer buffer = immediate.getBuffer(layer);

        stack.push();
        stack.translate(this.deathX - cam.x, this.deathY - cam.y, this.deathZ - cam.z);
        stack.multiply(camRot);
        MatrixStack.Entry entry = stack.peek();
        int argb = (((int) (alpha * 255.0f)) << 24) | 0xE8E8EC;
        buffer.vertex(entry, -WIDTH * 0.5f, 0.0f, 0.0f).texture(0.0f, 0.0f).color(argb);
        buffer.vertex(entry, WIDTH * 0.5f, 0.0f, 0.0f).texture(1.0f, 0.0f).color(argb);
        buffer.vertex(entry, WIDTH * 0.5f, HEIGHT, 0.0f).texture(1.0f, 1.0f).color(argb);
        buffer.vertex(entry, -WIDTH * 0.5f, HEIGHT, 0.0f).texture(0.0f, 1.0f).color(argb);
        stack.pop();
        immediate.draw();
    }

    private static void ensureTexture() {
        if (textureReady) {
            return;
        }
        try {
            NativeImage image = new NativeImage(TEX_WIDTH, TEX_HEIGHT, false);
            for (int y = 0; y < TEX_HEIGHT; y++) {
                // снизу ярче, к верху тает
                float v = y / (float) (TEX_HEIGHT - 1);
                float vertical = 0.3f + 0.7f * (1.0f - v);
                for (int x = 0; x < TEX_WIDTH; x++) {
                    float across = Math.abs(x - (TEX_WIDTH - 1) * 0.5f) / ((TEX_WIDTH - 1) * 0.5f);
                    float a = Math.max(0.0f, 1.0f - across * across) * vertical;
                    image.setColorArgb(x, y, ((int) (a * 255.0f) << 24) | 0xFFFFFF);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko death echo", image);
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
            System.out.println("[KIMIKO] Death Echo: не удалось создать текстуру (" + t + ")");
        }
    }
}
