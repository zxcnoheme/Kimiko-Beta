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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
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
 * Лужи после дождя: на плоской земле во время и после дождя появляются
 * еле заметные мокрые блики, которые медленно высыхают.
 */
public class WetGroundModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/entity/wet_spot.png");
    private static final int TEXTURE_SIZE = 32;
    private static final int MAX_SPOTS = 60;

    private static boolean textureReady;
    private static boolean textureFailed;

    private static final class Spot {
        double x;
        double y;
        double z;
        float size;
        float rotDeg;
        long bornAt;
        long ttlMs;
    }

    private static final List<Spot> spots = new ArrayList<>();
    private int tickCounter;

    private final SliderSetting density;
    private final SliderSetting drySeconds;

    public WetGroundModule() {
        super("Wet Ground NEW", "Во время дождя и сразу после на земле мерцают мокрые пятна, которые высыхают.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Как часто появляются пятна.").setValue(1.0f).range(0.2f, 3.0f));
        this.drySeconds = (SliderSetting) this.register((Setting) new SliderSetting("Высыхание", "Секунд высыхания после дождя.").setValue(75.0f).range(20.0f, 180.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null) {
            spots.clear();
            return;
        }
        float rain = world.getRainGradient(1.0f);
        if (rain < 0.25f) {
            return;
        }
        if (++this.tickCounter % 30 != 0) {
            return;
        }
        if (spots.size() >= MAX_SPOTS) {
            return;
        }
        int attempts = Math.round(this.density.getValue() * 2.0f);
        for (int i = 0; i < attempts; i++) {
            double x = this.mc.player.getX() + (Math.random() - 0.5) * 26.0;
            double z = this.mc.player.getZ() + (Math.random() - 0.5) * 26.0;
            int top = FirefliesModule.topY(world, MathHelper.floor(x), MathHelper.floor(z));
            if (top <= world.getBottomY() + 1) {
                continue;
            }
            BlockPos pos = new BlockPos(MathHelper.floor(x), top - 1, MathHelper.floor(z));
            if (!world.getBlockState(pos).isSolidBlock(world, pos)) {
                continue;
            }
            // не ставим в воду: поверхность воды тоже попадает в heightmap
            if (!world.getFluidState(pos).isEmpty()) {
                continue;
            }
            Spot s = new Spot();
            s.x = x;
            s.y = top;
            s.z = z;
            s.size = 1.1f + (float) Math.random() * 1.6f;
            s.rotDeg = (float) (Math.random() * 360.0);
            s.bornAt = System.currentTimeMillis();
            s.ttlMs = (long) (this.drySeconds.getValue() * 1000.0f);
            spots.add(s);
        }
        while (spots.size() > MAX_SPOTS) {
            spots.remove(0);
        }
    }

    @EventHandler
    private void onWorldRender(WorldRenderEvent event) {
        if (spots.isEmpty() || this.textureFailed || this.mc.world == null) {
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

        Iterator<Spot> it = spots.iterator();
        int drawn = 0;
        while (it.hasNext()) {
            Spot s = it.next();
            long age = now - s.bornAt;
            if (age > s.ttlMs) {
                it.remove();
                continue;
            }
            double dx = s.x - cam.x;
            double dy = s.y - cam.y;
            double dz = s.z - cam.z;
            if (dx * dx + dy * dy + dz * dz > 64.0 * 64.0) {
                continue;
            }
            // появление и высыхание
            float appear = MathHelper.clamp(age / 900.0f, 0.0f, 1.0f);
            float dry = 1.0f - MathHelper.clamp((age - s.ttlMs * 0.55f) / (float) (s.ttlMs * 0.45f), 0.0f, 1.0f);
            float alpha = 0.34f * appear * dry;
            if (alpha <= 0.01f) {
                continue;
            }
            stack.push();
            stack.translate(dx, s.y - cam.y + 0.03, dz);
            stack.multiply(net.minecraft.util.math.RotationAxis.POSITIVE_Y.rotationDegrees(s.rotDeg));
            stack.multiply(net.minecraft.util.math.RotationAxis.POSITIVE_X.rotationDegrees(-90.0f));
            MatrixStack.Entry entry = stack.peek();
            float sx = s.size * 0.5f;
            float sz = s.size * 0.36f;
            int argb = (((int) (alpha * 255.0f)) << 24) | 0xFFFFFF;
            buffer.vertex(entry, -sx, -sz, 0.0f).texture(0.0f, 0.0f).color(argb);
            buffer.vertex(entry, sx, -sz, 0.0f).texture(1.0f, 0.0f).color(argb);
            buffer.vertex(entry, sx, sz, 0.0f).texture(1.0f, 1.0f).color(argb);
            buffer.vertex(entry, -sx, sz, 0.0f).texture(0.0f, 1.0f).color(argb);
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
            float half = (TEXTURE_SIZE - 1) * 0.5f;
            for (int y = 0; y < TEXTURE_SIZE; y++) {
                for (int x = 0; x < TEXTURE_SIZE; x++) {
                    float dx = (x - half) / half;
                    float dy = (y - half) / half;
                    float d = (float) Math.sqrt(dx * dx * 0.8 + dy * dy);
                    float a = MathHelper.clamp(1.0f - d * d * 1.4f, 0.0f, 1.0f);
                    // блик в верхне-левой части
                    float spec = MathHelper.clamp(1.0f - (float) Math.sqrt((dx + 0.35) * (dx + 0.35) + (dy + 0.3) * (dy + 0.3)) * 1.6f, 0.0f, 1.0f);
                    int alpha = (int) (a * (160 + 95 * spec));
                    image.setColorArgb(x, y, (alpha << 24) | 0xBFD8E8);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko wet spot", image);
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
            System.out.println("[KIMIKO] Wet Ground: не удалось создать текстуру (" + t + ")");
        }
    }
}
