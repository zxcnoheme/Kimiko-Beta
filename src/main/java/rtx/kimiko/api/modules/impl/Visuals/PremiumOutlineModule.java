package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

/**
 * Block Outline: обводка блока под прицелом — три вида (свечение, заливка,
 * окантовка) и любой цвет.
 */
public class PremiumOutlineModule extends Module {
    private static final Identifier GLOW_TEX = Identifier.of("kimiko", "textures/ui/premium_outline.png");
    private static final Identifier SHARP_TEX = Identifier.of("kimiko", "textures/ui/premium_outline_sharp.png");
    private static final Identifier FILL_TEX = Identifier.of("kimiko", "textures/ui/premium_outline_fill.png");
    private static final int TEX_SIZE = 32;

    private static boolean glowReady;
    private static boolean sharpReady;
    private static boolean fillReady;
    private static boolean textureFailed;
    private static PremiumOutlineModule instance;

    private final ModeSetting style;
    private final ColorSetting color;
    private final SliderSetting glow;

    public PremiumOutlineModule() {
        super("Block Outline NEW", "Обводка блока под прицелом: вид (свечение / заливка / окантовка), цвет и яркость.", Category.VISUALS);
        this.style = (ModeSetting) this.register((Setting) new ModeSetting("Вид", "Стиль обводки.", "Шейдер", "Шейдер", "Заливка", "Окантовка"));
        this.color = (ColorSetting) this.register((Setting) new ColorSetting("Цвет", "Цвет обводки.").value(0xFFFFFFFF));
        this.glow = (SliderSetting) this.register((Setting) new SliderSetting("Яркость", "Яркость обводки.").setValue(0.7f).range(0.2f, 1.0f));
        instance = this;
    }

    /** Для миксина: гасить ванильную обводку, пока включён любой вид. */
    public static boolean cancelVanilla() {
        PremiumOutlineModule module = instance;
        return module != null && module.isEnabled();
    }

    @EventHandler
    private void onWorldRender(WorldRenderEvent event) {
        if (!this.isEnabled() || this.textureFailed || this.mc.player == null || this.mc.world == null) {
            return;
        }
        if (!(this.mc.crosshairTarget instanceof BlockHitResult hit) || hit.getType() != HitResult.Type.BLOCK) {
            return;
        }
        String styleNow = this.style.getValue();
        if (!ensureTexture(styleNow)) {
            return;
        }
        BlockPos pos = hit.getBlockPos();
        Vec3d cam = event.getCamera().getCameraPos();
        double dx = pos.getX() - cam.x;
        double dy = pos.getY() - cam.y;
        double dz = pos.getZ() - cam.z;
        double distSq = dx * dx + dy * dy + dz * dz;
        double reach = this.mc.player.getEntityInteractionRange() + 1.0;
        if (distSq > reach * reach) {
            return;
        }

        long now = System.currentTimeMillis();
        float pulse = 0.72f + 0.28f * (float) Math.sin(now * 0.005);
        float alpha = MathHelper.clamp(this.glow.getValue() * pulse, 0.0f, 1.0f);
        int tint = this.color.getColor() & 0xFFFFFF;
        int argb = (((int) (alpha * 255.0f)) << 24) | tint;

        Identifier tex = "Заливка".equals(styleNow) ? FILL_TEX
                : "Окантовка".equals(styleNow) ? SHARP_TEX : GLOW_TEX;

        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate = this.mc.getBufferBuilders().getEntityVertexConsumers();
        RenderLayer layer = ClientPipelines.MARKER.apply(tex);
        VertexConsumer buffer = immediate.getBuffer(layer);

        float e = 0.004f;
        float s = 1.0f + e * 2.0f;
        stack.push();
        stack.translate(dx - e, dy - e, dz - e);
        MatrixStack.Entry entry = stack.peek();

        // низ (y=0)
        quad(buffer, entry, 0, 0, 0, s, 0, 0, s, 0, s, 0, 0, s, argb);
        // верх (y=s)
        quad(buffer, entry, 0, s, 0, 0, s, s, s, s, s, s, s, 0, argb);
        // север (z=0)
        quad(buffer, entry, 0, 0, 0, 0, s, 0, s, s, 0, s, 0, 0, argb);
        // юг (z=s)
        quad(buffer, entry, 0, 0, s, s, 0, s, s, s, s, 0, s, s, argb);
        // запад (x=0)
        quad(buffer, entry, 0, 0, 0, 0, 0, s, 0, s, s, 0, s, 0, argb);
        // восток (x=s)
        quad(buffer, entry, s, 0, 0, s, s, 0, s, s, s, s, 0, s, argb);

        stack.pop();
        immediate.draw();
    }

    private static void quad(VertexConsumer buffer, MatrixStack.Entry entry,
                             float x1, float y1, float z1, float x2, float y2, float z2,
                             float x3, float y3, float z3, float x4, float y4, float z4, int argb) {
        buffer.vertex(entry, x1, y1, z1).texture(0.0f, 0.0f).color(argb);
        buffer.vertex(entry, x2, y2, z2).texture(1.0f, 0.0f).color(argb);
        buffer.vertex(entry, x3, y3, z3).texture(1.0f, 1.0f).color(argb);
        buffer.vertex(entry, x4, y4, z4).texture(0.0f, 1.0f).color(argb);
    }

    private static boolean ensureTexture(String styleNow) {
        if ("Заливка".equals(styleNow)) {
            if (fillReady || textureFailed) {
                return fillReady;
            }
            try {
                NativeImage image = new NativeImage(8, 8, false);
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        image.setColorArgb(x, y, 0xFFFFFFFF);
                    }
                }
                registerTex(FILL_TEX, image, "fill");
                fillReady = true;
            } catch (Throwable t) {
                textureFailed = true;
            }
            return fillReady;
        }
        boolean sharp = "Окантовка".equals(styleNow);
        boolean ready = sharp ? sharpReady : glowReady;
        if (ready || textureFailed) {
            return ready;
        }
        try {
            NativeImage image = new NativeImage(TEX_SIZE, TEX_SIZE, false);
            int border = sharp ? 2 : 4;
            for (int y = 0; y < TEX_SIZE; y++) {
                for (int x = 0; x < TEX_SIZE; x++) {
                    float d = Math.min(Math.min(x, TEX_SIZE - 1 - x), Math.min(y, TEX_SIZE - 1 - y));
                    float a;
                    if (d < border) {
                        a = 1.0f - d / (float) border * (sharp ? 0.2f : 0.55f);
                    } else if (!sharp) {
                        a = MathHelper.clamp(1.0f - (d - border) / 6.0f, 0.0f, 1.0f) * 0.45f;
                        if (d > border + 6) {
                            a = 0.0f;
                        }
                    } else {
                        a = 0.0f;
                    }
                    image.setColorArgb(x, y, ((int) (a * 255.0f) << 24) | 0xFFFFFF);
                }
            }
            registerTex(sharp ? SHARP_TEX : GLOW_TEX, image, sharp ? "sharp" : "glow");
            if (sharp) {
                sharpReady = true;
            } else {
                glowReady = true;
            }
        } catch (Throwable t) {
            textureFailed = true;
            System.out.println("[KIMIKO] Block Outline: не удалось создать текстуру (" + t + ")");
        }
        return sharp ? sharpReady : glowReady;
    }

    private static void registerTex(Identifier id, NativeImage image, String tag) {
        try {
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko outline " + tag, image);
            tex.upload();
            if (tex.getGlTextureView() == null && tex.getGlTexture() != null) {
                com.mojang.blaze3d.textures.GpuTextureView view =
                        com.mojang.blaze3d.systems.RenderSystem.getDevice().createTextureView(tex.getGlTexture());
                ((mixin.skins.AbstractTextureAccessor) (Object) tex).kimiko$setView(view);
            }
            MinecraftClient.getInstance().getTextureManager().registerTexture(id, tex);
        } catch (Throwable t) {
            System.out.println("[KIMIKO] Block Outline: регистрация текстуры не удалась (" + t + ")");
        }
    }
}
