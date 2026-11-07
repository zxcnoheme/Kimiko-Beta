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
 * Следы шагов: на песке, снегу, гравии и тропинках за игроком остаются
 * отпечатки, которые медленно затираются.
 */
public class FootprintsModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/entity/footprint.png");
    private static final int TEXTURE_SIZE = 16;
    private static final long TTL_MS = 10_000L;
    private static final int MAX_PRINTS = 120;

    private static boolean textureReady;
    private static boolean textureFailed;

    private static final class Print {
        double x;
        double y;
        double z;
        float yawDeg;
        long bornAt;
    }

    private static final List<Print> prints = new ArrayList<>();
    private double lastX;
    private double lastZ;
    private double walked;
    private boolean leftFoot;
    private boolean lastSneaking;

    private final SliderSetting interval;

    public FootprintsModule() {
        super("Footprints", "Отпечатки шагов на песке, снегу, гравии и тропинках — затираются за 10 секунд.", Category.VISUALS);
        this.interval = (SliderSetting) this.register((Setting) new SliderSetting("Частота", "Шагов между отпечатками (в блоках).").setValue(1.6f).range(0.8f, 3.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null || this.mc.player.isGliding() || this.mc.player.isSwimming()) {
            return;
        }
        if (this.mc.player.isSneaking() && !this.lastSneaking) {
            // присел — сбрасываем шаг
        }
        this.lastSneaking = this.mc.player.isSneaking();

        double dx = this.mc.player.getX() - this.lastX;
        double dz = this.mc.player.getZ() - this.lastZ;
        this.walked += Math.sqrt(dx * dx + dz * dz);
        this.lastX = this.mc.player.getX();
        this.lastZ = this.mc.player.getZ();
        if (this.walked < this.interval.getValue()) {
            return;
        }
        this.walked = 0.0;

        int bx = MathHelper.floor(this.mc.player.getX());
        int bz = MathHelper.floor(this.mc.player.getZ());
        int top = FirefliesModule.topY(world, bx, bz);
        if (top <= world.getBottomY() + 1) {
            return;
        }
        BlockPos groundPos = new BlockPos(bx, top - 1, bz);
        String blockPath = world.getBlockState(groundPos).getBlock().toString().toLowerCase();
        boolean soft = blockPath.contains("sand") || blockPath.contains("snow")
                || blockPath.contains("gravel") || blockPath.contains("path") || blockPath.contains("dirt");
        if (!soft) {
            return;
        }

        // смещение под левую/правую ногу — перпендикуляр взгляда
        float yawRad = (float) Math.toRadians(this.mc.player.getYaw());
        float sideX = MathHelper.cos(yawRad) * (this.leftFoot ? -0.16f : 0.16f);
        float sideZ = MathHelper.sin(yawRad) * (this.leftFoot ? -0.16f : 0.16f);
        this.leftFoot = !this.leftFoot;

        Print p = new Print();
        p.x = this.mc.player.getX() + sideX;
        p.y = top + 0.012;
        p.z = this.mc.player.getZ() + sideZ;
        p.yawDeg = this.mc.player.getYaw();
        p.bornAt = System.currentTimeMillis();
        prints.add(p);
        while (prints.size() > MAX_PRINTS) {
            prints.remove(0);
        }
    }

    @EventHandler
    private void onWorldRender(WorldRenderEvent event) {
        if (prints.isEmpty() || this.textureFailed || this.mc.world == null) {
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

        Iterator<Print> it = prints.iterator();
        int drawn = 0;
        while (it.hasNext()) {
            Print p = it.next();
            long age = now - p.bornAt;
            if (age > TTL_MS) {
                it.remove();
                continue;
            }
            double dx = p.x - cam.x;
            double dy = p.y - cam.y;
            double dz = p.z - cam.z;
            if (dx * dx + dy * dy + dz * dz > 40.0 * 40.0) {
                continue;
            }
            float fade = 1.0f - (float) age / (float) TTL_MS;
            float appear = MathHelper.clamp(age / 220.0f, 0.0f, 1.0f);
            float alpha = 0.55f * fade * appear;

            stack.push();
            stack.translate(dx, dy, dz);
            stack.multiply(net.minecraft.util.math.RotationAxis.POSITIVE_Y.rotationDegrees(-p.yawDeg));
            stack.multiply(net.minecraft.util.math.RotationAxis.POSITIVE_X.rotationDegrees(-90.0f));
            MatrixStack.Entry entry = stack.peek();
            float hw = 0.11f;
            float hl = 0.17f;
            int argb = (((int) (alpha * 255.0f)) << 24) | 0x1A1611;
            buffer.vertex(entry, -hw, -hl, 0.0f).texture(0.0f, 0.0f).color(argb);
            buffer.vertex(entry, hw, -hl, 0.0f).texture(1.0f, 0.0f).color(argb);
            buffer.vertex(entry, hw, hl, 0.0f).texture(1.0f, 1.0f).color(argb);
            buffer.vertex(entry, -hw, hl, 0.0f).texture(0.0f, 1.0f).color(argb);
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
            // силуэт ступни: овал + носок
            float cx = (TEXTURE_SIZE - 1) * 0.5f;
            for (int y = 0; y < TEXTURE_SIZE; y++) {
                for (int x = 0; x < TEXTURE_SIZE; x++) {
                    float u = (x - cx) / halfW(y);
                    float v = (y - (TEXTURE_SIZE - 1) * 0.15f) / ((TEXTURE_SIZE - 1) * 0.8f);
                    float d = u * u + v * v;
                    float a = MathHelper.clamp(1.0f - d, 0.0f, 1.0f);
                    image.setColorArgb(x, y, ((int) (a * 255.0f) << 24) | 0xFFFFFF);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko footprint", image);
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
            System.out.println("[KIMIKO] Footprints: не удалось создать текстуру (" + t + ")");
        }
    }

    private static float halfW(int y) {
        // носок уже пятки
        float t = y / (float) (TEXTURE_SIZE - 1);
        return (TEXTURE_SIZE - 1) * (0.36f - 0.10f * t);
    }
}
