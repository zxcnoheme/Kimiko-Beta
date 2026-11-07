package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
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
 * Маркер щита: над игроками со щитом в левой руке висит маленький
 * значок щита, всегда повёрнутый к камере.
 */
public class ShieldMarkerModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/entity/shield_marker.png");
    private static final int TEXTURE_SIZE = 16;
    private static final float SIZE = 0.4f;

    private static boolean textureReady;
    private static boolean textureFailed;

    private final SliderSetting distance;

    public ShieldMarkerModule() {
        super("Shield Marker NEW", "Значок щита над головой игроков со щитом в левой руке.", Category.VISUALS);
        this.distance = (SliderSetting) this.register((Setting) new SliderSetting("Дистанция", "Максимальная дистанция маркера.").setValue(24.0f).range(8.0f, 48.0f));
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
        ensureTexture();
        if (!textureReady) {
            return;
        }
        float maxDist = this.distance.getValue();
        float maxDistSq = maxDist * maxDist;
        Vec3d cam = event.getCamera().getCameraPos();
        Quaternionf camRot = event.getCamera().getRotation();
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate = this.mc.getBufferBuilders().getEntityVertexConsumers();
        RenderLayer layer = ClientPipelines.MARKER.apply(TEXTURE);
        VertexConsumer buffer = immediate.getBuffer(layer);

        long now = System.currentTimeMillis();
        int drawn = 0;
        for (AbstractClientPlayerEntity other : world.getPlayers()) {
            if (other == this.mc.player || !other.isAlive() || other.isSpectator()) {
                continue;
            }
            if (!other.getEquippedStack(EquipmentSlot.OFFHAND).isOf(Items.SHIELD)) {
                continue;
            }
            double dx = other.getX() - cam.x;
            double dy = other.getY() - cam.y;
            double dz = other.getZ() - cam.z;
            if (dx * dx + dy * dy + dz * dz > maxDistSq) {
                continue;
            }
            float bob = (float) Math.sin(now * 0.003 + other.getId()) * 0.04f;
            float alpha = 0.85f;
            int argb = (((int) (alpha * 255.0f)) << 24) | 0xBFE3FF;

            stack.push();
            stack.translate(dx, dy + other.getHeight() + 0.4 + bob, dz);
            stack.multiply(camRot);
            MatrixStack.Entry entry = stack.peek();
            float s = SIZE * 0.5f;
            buffer.vertex(entry, -s, -s, 0.0f).texture(0.0f, 0.0f).color(argb);
            buffer.vertex(entry, s, -s, 0.0f).texture(1.0f, 0.0f).color(argb);
            buffer.vertex(entry, s, s, 0.0f).texture(1.0f, 1.0f).color(argb);
            buffer.vertex(entry, -s, s, 0.0f).texture(0.0f, 1.0f).color(argb);
            stack.pop();
            drawn++;
        }
        if (drawn > 0) {
            immediate.draw();
        }
    }

    private static void ensureTexture() {
        if (textureReady) {
            return;
        }
        try {
            NativeImage image = new NativeImage(TEXTURE_SIZE, TEXTURE_SIZE, false);
            for (int y = 0; y < TEXTURE_SIZE; y++) {
                for (int x = 0; x < TEXTURE_SIZE; x++) {
                    // контур щита со срезанными верхними углами
                    boolean edge = (x == 0 || y == 0 || x == TEXTURE_SIZE - 1 || y == TEXTURE_SIZE - 1)
                            && !(y < 2 && (x < 2 || x > TEXTURE_SIZE - 3));
                    boolean cornerCut = y < 2 && (x < 2 || x > TEXTURE_SIZE - 3);
                    boolean cross = ((x == 7 || x == 8) && y >= 2 && y <= 13)
                            || ((y == 7 || y == 8) && x >= 2 && x <= 13);
                    int a = (edge || (cross && !cornerCut)) ? 255 : 0;
                    image.setColorArgb(x, y, (a << 24) | 0xFFFFFF);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko shield marker", image);
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
            System.out.println("[KIMIKO] Shield Marker: не удалось создать текстуру (" + t + ")");
        }
    }
}
