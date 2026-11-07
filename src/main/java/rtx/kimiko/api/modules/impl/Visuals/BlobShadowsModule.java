package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

/**
 * Мягкие тени (blob shadows): эллипс тени под каждой сущностью.
 * Размер и плотность тени зависят от высоты сущности над землёй —
 * картинка сразу становится объёмной без шейдеров.
 */
public class BlobShadowsModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/entity/blob_shadow.png");
    private static final int TEXTURE_SIZE = 64;
    private static final float MAX_RENDER_DIST = 48.0f;
    private static final int GROUND_SCAN = 10;

    private static boolean textureReady;
    private static boolean textureFailed;

    public BlobShadowsModule() {
        super("Blob Shadows", "Мягкие тени-эллипсы под сущностями: чем выше сущность, тем бледнее и больше тень.", Category.VISUALS);
        this.mode = (ModeSetting) this.register((Setting) new ModeSetting("Кого показывать", "Чьи тени рисовать.", "Все сущности", "Все сущности", "Только игроки"));
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Общая плотность тени.").setValue(0.55f).range(0.15f, 1.0f));
        this.scale = (SliderSetting) this.register((Setting) new SliderSetting("Размер", "Множитель размера тени.").setValue(1.0f).range(0.5f, 2.0f));
        this.fallFade = (BooleanSetting) this.register((Setting) new BooleanSetting("Бледнеть в полёте", "Тень тает с высотой над землёй.", true));
    }

    private final ModeSetting mode;
    private final SliderSetting strength;
    private final SliderSetting scale;
    private final BooleanSetting fallFade;

    @EventHandler
    private void onWorldRender(WorldRenderEvent event) {
        if (!this.isEnabled() || event.isPortalPass()) {
            return;
        }
        MinecraftClient mc = this.mc;
        ClientWorld world = mc.world;
        if (world == null || mc.player == null || textureFailed) {
            return;
        }
        ensureTexture();
        if (!textureReady) {
            return;
        }
        boolean playersOnly = this.mode.is("Только игроки");
        Vec3d cam = event.getCamera().getCameraPos();
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate = mc.getBufferBuilders().getEntityVertexConsumers();
        VertexConsumer buffer = immediate.getBuffer(ClientPipelines.MARKER.apply(TEXTURE));

        int drawn = 0;
        for (Entity entity : world.getEntities()) {
            if (drawn >= 120) {
                break;
            }
            if (entity == mc.player || !entity.isAlive() || entity.isSpectator()) {
                continue;
            }
            if (playersOnly && !(entity instanceof PlayerEntity)) {
                continue;
            }
            if (!(entity instanceof LivingEntity)) {
                continue;
            }
            double dx = entity.getX() - cam.x;
            double dy = entity.getY() - cam.y;
            double dz = entity.getZ() - cam.z;
            if (dx * dx + dy * dy + dz * dz > MAX_RENDER_DIST * MAX_RENDER_DIST) {
                continue;
            }
            double groundY = findGroundY(world, entity);
            if (groundY == Double.NEGATIVE_INFINITY) {
                continue;
            }
            float height = (float) (entity.getY() - groundY);
            float fade = this.fallFade.getValue() ? MathHelper.clamp(1.0f - height / 7.0f, 0.0f, 1.0f) : 1.0f;
            if (fade <= 0.02f) {
                continue;
            }
            float alpha = MathHelper.clamp(fade * this.strength.getValue(), 0.0f, 1.0f);
            float size = (entity.getWidth() * 2.6f + 0.4f) * this.scale.getValue() * (1.0f + (1.0f - fade) * 0.5f);

            stack.push();
            stack.translate(dx, groundY - cam.y + 0.02, dz);
            stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90.0f));
            MatrixStack.Entry entry = stack.peek();
            float s = size * 0.5f;
            int argb = (((int) (alpha * 255.0f)) << 24) | 0xFFFFFF;
            putCorner(buffer, entry, -s, -s, 0.0f, 0.0f, argb);
            putCorner(buffer, entry, s, -s, 1.0f, 0.0f, argb);
            putCorner(buffer, entry, s, s, 1.0f, 1.0f, argb);
            putCorner(buffer, entry, -s, s, 0.0f, 1.0f, argb);
            stack.pop();
            drawn++;
        }
        immediate.draw();
    }

    private static void putCorner(VertexConsumer buffer, MatrixStack.Entry entry, float x, float y, float u, float v, int argb) {
        buffer.vertex(entry, x, y, 0.0f)
                .texture(u, v)
                .color(argb);
    }

    private static double findGroundY(ClientWorld world, Entity entity) {
        int x = MathHelper.floor(entity.getX());
        int z = MathHelper.floor(entity.getZ());
        int startY = MathHelper.floor(entity.getY());
        for (int i = 0; i < GROUND_SCAN; i++) {
            int y = startY - i;
            if (y < world.getBottomY()) {
                break;
            }
            BlockPos pos = new BlockPos(x, y, z);
            if (world.getBlockState(pos).isSolidBlock(world, pos)) {
                return y + 1.0;
            }
        }
        return Double.NEGATIVE_INFINITY;
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
                    float t = MathHelper.clamp(1.0f - d, 0.0f, 1.0f);
                    int a = (int) (t * t * 255.0f);
                    image.setColorArgb(x, y, (a << 24) | 0xFFFFFF);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko blob shadow", image);
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
            System.out.println("[KIMIKO] Blob Shadows: не удалось создать текстуру (" + t + ")");
        }
    }
}
