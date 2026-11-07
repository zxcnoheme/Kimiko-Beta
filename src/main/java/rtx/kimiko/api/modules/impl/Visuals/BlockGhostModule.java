package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

/**
 * Block Ghost NEW: подсвечивает позицию, куда встанет блок при клике —
 * полупрозрачный белый каркас-куб (6 MARKER-граней с рамочной текстурой
 * 16×16: контур 2px, центр прозрачный) с инфлейтом 0.002.
 */
public class BlockGhostModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/entity/block_ghost.png");
    private static final int TEXTURE_SIZE = 16;
    private static final int FRAME_PX = 2;
    private static final float INFLATE = 0.002f;
    private static final float ALPHA = 0.35f;
    /** Стандартный радиус взаимодействия с блоками (креатив) — с запасом над выживанием 4.5. */
    private static final double REACH_SQ = 5.0 * 5.0;

    private static boolean textureReady;
    private static boolean textureFailed;

    private final BooleanSetting onlyWithBlock;

    public BlockGhostModule() {
        super("Block Ghost NEW", "Каркас-куб в позиции будущей установки блока по прицелу.", Category.VISUALS);
        this.onlyWithBlock = (BooleanSetting) this.register((Setting) new BooleanSetting("Только с блоком", "Показывать призрака, только если в руке блок.", true));
    }

    @EventHandler
    private void onWorldRender(WorldRenderEvent event) {
        if (!this.isEnabled() || this.textureFailed) {
            return;
        }
        if (this.mc.world == null || this.mc.player == null) {
            return;
        }
        if (!(this.mc.crosshairTarget instanceof BlockHitResult hit) || hit.getType() != HitResult.Type.BLOCK) {
            return;
        }
        if (this.onlyWithBlock.getValue()) {
            if (this.mc.player.getMainHandStack().isEmpty()
                    || !(this.mc.player.getMainHandStack().getItem() instanceof BlockItem)) {
                return;
            }
        }

        BlockPos place = hit.getBlockPos().offset(hit.getSide());
        if (!this.mc.world.getBlockState(place).isAir()) {
            return;
        }
        // дотягивается ли игрок до этой позиции
        Vec3d center = new Vec3d(place.getX() + 0.5, place.getY() + 0.5, place.getZ() + 0.5);
        Vec3d eye = this.mc.player.getCameraPosVec(event.getPartialTicks());
        if (eye.squaredDistanceTo(center) > REACH_SQ) {
            return;
        }

        ensureTexture();
        if (!textureReady) {
            return;
        }

        float argbAlpha = ALPHA * this.visualAlpha();
        if (argbAlpha <= 0.01f) {
            return;
        }
        int argb = ((int) (argbAlpha * 255.0f) << 24) | 0xFFFFFF;

        Vec3d cam = event.getCamera().getCameraPos();
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate = this.mc.getBufferBuilders().getEntityVertexConsumers();
        RenderLayer layer = ClientPipelines.MARKER.apply(TEXTURE);
        VertexConsumer buffer = immediate.getBuffer(layer);

        float lo = -INFLATE;
        float hi = 1.0f + INFLATE;

        stack.push();
        stack.translate(place.getX() - cam.x, place.getY() - cam.y, place.getZ() - cam.z);
        MatrixStack.Entry entry = stack.peek();

        // верх (+Y)
        quad(buffer, entry, argb, lo, hi, hi, hi, hi, hi, hi, hi, lo, hi, lo, hi);
        // низ (-Y)
        quad(buffer, entry, argb, lo, lo, lo, hi, lo, lo, hi, lo, hi, lo, lo, hi);
        // север (-Z)
        quad(buffer, entry, argb, lo, lo, lo, lo, hi, lo, hi, hi, lo, hi, lo, lo);
        // юг (+Z)
        quad(buffer, entry, argb, hi, lo, hi, hi, hi, hi, lo, hi, hi, lo, lo, hi);
        // запад (-X)
        quad(buffer, entry, argb, lo, lo, lo, lo, lo, hi, lo, hi, hi, lo, hi, lo);
        // восток (+X)
        quad(buffer, entry, argb, hi, lo, hi, hi, hi, hi, hi, hi, lo, hi, lo, hi);

        stack.pop();
        immediate.draw();
    }

    /** Один MARKER-квад с UV 0..1 по всему кадру текстуры (рамка симметрична). */
    private static void quad(VertexConsumer buffer, MatrixStack.Entry entry, int argb,
                             float x1, float y1, float z1,
                             float x2, float y2, float z2,
                             float x3, float y3, float z3,
                             float x4, float y4, float z4) {
        buffer.vertex(entry, x1, y1, z1).texture(0.0f, 0.0f).color(argb);
        buffer.vertex(entry, x2, y2, z2).texture(1.0f, 0.0f).color(argb);
        buffer.vertex(entry, x3, y3, z3).texture(1.0f, 1.0f).color(argb);
        buffer.vertex(entry, x4, y4, z4).texture(0.0f, 1.0f).color(argb);
    }

    /** Рамочная текстура 16×16: контур 2px белым, центр полностью прозрачный. */
    private static void ensureTexture() {
        if (textureReady) {
            return;
        }
        try {
            NativeImage image = new NativeImage(TEXTURE_SIZE, TEXTURE_SIZE, false);
            for (int y = 0; y < TEXTURE_SIZE; y++) {
                for (int x = 0; x < TEXTURE_SIZE; x++) {
                    boolean frame = x < FRAME_PX || y < FRAME_PX
                            || x >= TEXTURE_SIZE - FRAME_PX || y >= TEXTURE_SIZE - FRAME_PX;
                    int a = frame ? 255 : 0;
                    image.setColorArgb(x, y, (a << 24) | 0xFFFFFF);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko block ghost", image);
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
            System.out.println("[KIMIKO] Block Ghost: не удалось создать текстуру (" + t + ")");
        }
    }
}
