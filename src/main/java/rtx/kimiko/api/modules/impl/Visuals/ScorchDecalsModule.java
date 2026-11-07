package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.world.ClientWorld;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.network.PacketReceiveEvent;
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
 * Подпалины от взрывов: на месте каждого взрыва на землю ложится
 * тёмное пятно с рваными краями (декаль), которое медленно выцветает.
 */
public class ScorchDecalsModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/entity/scorch.png");
    private static final int TEXTURE_SIZE = 64;
    private static final long LIFETIME_MS = 90_000L;
    private static final long FADE_MS = 15_000L;
    private static final int MAX_DECALS = 40;

    private static boolean textureReady;
    private static boolean textureFailed;

    private static final class Decal {
        double x;
        double y;
        double z;
        float radius;
        float rotDeg;
        long bornAt;
    }

    private static final List<Decal> decals = new ArrayList<>();
    private static final List<Vec3d[]> pending = new ArrayList<>();

    private final SliderSetting sizeScale;
    private final SliderSetting lifeMinutes;

    public ScorchDecalsModule() {
        super("Scorch Decals NEW", "Тёмные подпалины на земле от взрывов: рваные края, медленно выцветают за пару минут.", Category.VISUALS);
        this.sizeScale = (SliderSetting) this.register((Setting) new SliderSetting("Размер пятна", "Множитель размера подпалины.").setValue(1.0f).range(0.5f, 2.0f));
        this.lifeMinutes = (SliderSetting) this.register((Setting) new SliderSetting("Время жизни", "Сколько секунд живёт пятно.").setValue(90.0f).range(20.0f, 180.0f));
    }

    @EventHandler
    private void onPacketReceive(PacketReceiveEvent event) {
        ExplosionS2CPacket explosion = event.getPacketAs(ExplosionS2CPacket.class);
        if (explosion != null) {
            synchronized (pending) {
                pending.add(new Vec3d[]{explosion.center(), new Vec3d(explosion.radius(), 0, 0)});
            }
        }
    }

    @EventHandler
    private void onWorldRender(WorldRenderEvent event) {
        if (this.textureFailed) {
            return;
        }
        // обработка отложенных взрывов (чтобы искать землю уже на клиентском потоке)
        synchronized (pending) {
            Iterator<Vec3d[]> it = pending.iterator();
            while (it.hasNext()) {
                Vec3d[] p = it.next();
                it.remove();
                if (!this.isEnabled() || this.mc.world == null || this.mc.player == null) {
                    continue;
                }
                this.tryPlaceDecal(this.mc.world, p[0], (float) p[1].x);
            }
        }
        if (!this.isEnabled() || this.mc.world == null || decals.isEmpty()) {
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

        Iterator<Decal> it = decals.iterator();
        int drawn = 0;
        while (it.hasNext()) {
            Decal d = it.next();
            long life = (long) (this.lifeMinutes.getValue() * 1000.0f);
            long age = now - d.bornAt;
            if (age > life) {
                it.remove();
                continue;
            }
            double dx = d.x - cam.x;
            double dy = d.y - cam.y;
            double dz = d.z - cam.z;
            if (dx * dx + dy * dy + dz * dz > 128.0 * 128.0) {
                continue;
            }
            float fade = age > life - FADE_MS ? (float) (life - age) / (float) FADE_MS : 1.0f;
            float alpha = 0.85f * MathHelper.clamp(fade, 0.0f, 1.0f);
            if (alpha <= 0.02f) {
                continue;
            }
            float size = d.radius * this.sizeScale.getValue() * 1.9f;

            stack.push();
            stack.translate(dx, d.y - cam.y + 0.025, dz);
            stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(d.rotDeg));
            stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90.0f));
            MatrixStack.Entry entry = stack.peek();
            float s = size * 0.5f;
            int argb = (((int) (alpha * 255.0f)) << 24) | 0xFFFFFF;
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

    private void tryPlaceDecal(ClientWorld world, Vec3d center, float radius) {
        double gx = center.x;
        double gz = center.z;
        int startX = MathHelper.floor(center.y + 2.0);
        for (int y = startX; y >= MathHelper.floor(center.y) - 8; y--) {
            BlockPos pos = BlockPos.ofFloored(gx, y, gz);
            if (world.getBlockState(pos).isSolidBlock(world, pos)) {
                Decal d = new Decal();
                d.x = gx;
                d.y = y + 1.0;
                d.z = gz;
                d.radius = MathHelper.clamp(radius, 1.5f, 5.5f);
                d.rotDeg = (float) (Math.random() * 360.0);
                d.bornAt = System.currentTimeMillis();
                decals.add(d);
                while (decals.size() > MAX_DECALS) {
                    decals.remove(0);
                }
                return;
            }
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
                    float d = (float) Math.sqrt(dx * dx + dy * dy);
                    // рваные края: шум поверх радиуса
                    float n = hashNoise(x * 0.55f, y * 0.55f);
                    float edge = 1.05f - d + (n - 0.5f) * 0.38f;
                    float a = MathHelper.clamp(edge, 0.0f, 1.0f);
                    a = a * a * (0.75f + 0.25f * n);
                    int alpha = (int) (a * 255.0f);
                    image.setColorArgb(x, y, (alpha << 24) | 0x0A0908);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko scorch", image);
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
            System.out.println("[KIMIKO] Scorch Decals: не удалось создать текстуру (" + t + ")");
        }
    }

    /** Детерминированный хеш-шум 0..1 (без библиотек). */
    private static float hashNoise(float x, float y) {
        int ix = (int) x;
        int iy = (int) y;
        float fx = x - ix;
        float fy = y - iy;
        float h00 = hashCell(ix, iy);
        float h10 = hashCell(ix + 1, iy);
        float h01 = hashCell(ix, iy + 1);
        float h11 = hashCell(ix + 1, iy + 1);
        float sx = fx * fx * (3.0f - 2.0f * fx);
        float sy = fy * fy * (3.0f - 2.0f * fy);
        return MathHelper.lerp(MathHelper.lerp(h00, h10, sx), MathHelper.lerp(h01, h11, sx), sy);
    }

    private static float hashCell(int x, int y) {
        int h = x * 374761393 + y * 668265263;
        h = (h ^ (h >> 13)) * 1274126177;
        return ((h ^ (h >> 16)) & 0xFFFF) / 65535.0f;
    }
}
