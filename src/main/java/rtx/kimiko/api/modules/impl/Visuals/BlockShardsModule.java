package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.world.ClientWorld;
import org.joml.Quaternionf;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Осколки блоков: при ломании блока разлетаются 3D-кусочки в цвете блока —
 * падают, отскакивают от земли и растворяются. Физика лёгкая (без коллизий по стенам).
 */
public class BlockShardsModule extends Module {
    private static final Identifier TEXTURE = Identifier.of("kimiko", "textures/entity/shard.png");
    private static final int TEXTURE_SIZE = 16;
    private static final int MAX_SHARDS = 320;
    private static final long SPAWN_INTERVAL_MS = 220L;

    private static boolean textureReady;
    private static boolean textureFailed;

    private static final class Shard {
        double x;
        double y;
        double z;
        double vx;
        double vy;
        double vz;
        float size;
        int argb;
        float groundY;
        long bornAt;
        long ttlMs;
        float phase;
    }

    private static final List<Shard> shards = new ArrayList<>();
    private static final Map<Long, Long> lastSpawnAt = new HashMap<>();
    private static BlockShardsModule instance;
    private static long lastNano;

    private final SliderSetting density;
    private final SliderSetting sizeScale;
    private final BooleanSetting digDebris;

    public BlockShardsModule() {
        super("Block Shards", "Ломаемый блок разлетается на кусочки в цвете блока: падают, отскачивают и тают. Работает и при копании.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Количество", "Сколько осколков на блок.").setValue(8.0f).range(3.0f, 16.0f));
        this.sizeScale = (SliderSetting) this.register((Setting) new SliderSetting("Размер", "Множитель размера осколков.").setValue(1.0f).range(0.5f, 1.8f));
        this.digDebris = (BooleanSetting) this.register((Setting) new BooleanSetting("Крошка при копании", "Осколки летят и во время добычи, а не только при разломе.", true));
        instance = this;
    }

    /** Вызывается из ParticleEngineMixin при спавне блочных частиц (крошка/разлом). */
    public static void onBlockParticles(BlockState state, double x, double y, double z) {
        BlockShardsModule module = instance;
        if (module == null || !module.isEnabled() || textureFailed || module.mc.world == null) {
            return;
        }
        long blockKey = BlockPos.ofFloored(x, y, z).asLong();
        long now = System.currentTimeMillis();
        Long last = lastSpawnAt.get(blockKey);
        if (last != null && now - last < SPAWN_INTERVAL_MS) {
            return;
        }
        lastSpawnAt.put(blockKey, now);
        if (lastSpawnAt.size() > 512) {
            lastSpawnAt.clear();
        }
        if (shards.size() >= MAX_SHARDS) {
            return;
        }

        ClientWorld world = module.mc.world;
        BlockPos pos = BlockPos.ofFloored(x, y, z);
        int mapColor = state.getMapColor(world, pos).color;
        float baseShade = 0.75f + (float) Math.random() * 0.4f;
        int baseR = MathHelper.clamp((int) ((mapColor >> 16 & 0xFF) * baseShade), 0, 255);
        int baseG = MathHelper.clamp((int) ((mapColor >> 8 & 0xFF) * baseShade), 0, 255);
        int baseB = MathHelper.clamp((int) ((mapColor & 0xFF) * baseShade), 0, 255);

        float groundY = Float.NaN;
        int startX = MathHelper.floor(y);
        for (int i = 0; i < 6; i++) {
            int by = startX - i;
            BlockPos gp = new BlockPos(MathHelper.floor(x), by, MathHelper.floor(z));
            if (world.getBlockState(gp).isSolidBlock(world, gp)) {
                groundY = by + 1.0f;
                break;
            }
        }

        int count = Math.round(module.density.getValue());
        boolean dig = module.digDebris.getValue();
        for (int i = 0; i < count; i++) {
            Shard s = new Shard();
            double ang = Math.random() * Math.PI * 2.0;
            double speed = (dig ? 1.2 : 2.2) + Math.random() * (dig ? 1.2 : 2.6);
            s.x = x + (Math.random() - 0.5) * 0.7;
            s.y = y + Math.random() * 0.5;
            s.z = z + (Math.random() - 0.5) * 0.7;
            s.vx = Math.cos(ang) * speed * 0.6;
            s.vz = Math.sin(ang) * speed * 0.6;
            s.vy = (dig ? 1.2 : 2.4) + Math.random() * (dig ? 1.5 : 2.8);
            float shade = 0.85f + (float) Math.random() * 0.3f;
            int r = MathHelper.clamp((int) (baseR * shade), 0, 255);
            int g = MathHelper.clamp((int) (baseG * shade), 0, 255);
            int b = MathHelper.clamp((int) (baseB * shade), 0, 255);
            s.argb = 0xFF000000 | (r << 16) | (g << 8) | b;
            s.size = (0.05f + (float) Math.random() * 0.07f) * module.sizeScale.getValue();
            s.ttlMs = 1300L + (long) (Math.random() * 900.0);
            s.groundY = groundY;
            s.phase = (float) Math.random() * 6.28f;
            shards.add(s);
        }
        while (shards.size() > MAX_SHARDS) {
            shards.remove(0);
        }
    }

    @EventHandler
    private void onWorldRender(WorldRenderEvent event) {
        if (shards.isEmpty() || this.textureFailed || this.mc.world == null) {
            return;
        }
        ensureTexture();
        if (!textureReady) {
            return;
        }
        long now = System.currentTimeMillis();
        long nano = System.nanoTime();
        float dt = lastNano == 0L ? 0.033f : MathHelper.clamp((nano - lastNano) / 1.0E9f, 0.005f, 0.05f);
        lastNano = nano;
        double gravity = 22.0;

        Vec3d cam = event.getCamera().getCameraPos();
        Quaternionf camRot = event.getCamera().getRotation();
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate = this.mc.getBufferBuilders().getEntityVertexConsumers();
        RenderLayer layer = ClientPipelines.MARKER.apply(TEXTURE);
        VertexConsumer buffer = immediate.getBuffer(layer);

        Iterator<Shard> it = shards.iterator();
        int drawn = 0;
        while (it.hasNext()) {
            Shard s = it.next();
            long age = now - s.bornAt;
            if (age > s.ttlMs) {
                it.remove();
                continue;
            }
            // физика
            s.vy -= gravity * dt;
            s.x += s.vx * dt;
            s.y += s.vy * dt;
            s.z += s.vz * dt;
            if (!Float.isNaN(s.groundY) && s.y - s.size * 0.5f < s.groundY && s.vy < 0.0) {
                s.y = s.groundY + s.size * 0.5f;
                s.vy = -s.vy * 0.34;
                s.vx *= 0.62;
                s.vz *= 0.62;
                if (Math.abs(s.vy) < 0.35) {
                    s.vy = 0.0;
                    s.vx *= 0.82;
                    s.vz *= 0.82;
                }
            }
            double dx = s.x - cam.x;
            double dy = s.y - cam.y;
            double dz = s.z - cam.z;
            if (dx * dx + dy * dy + dz * dz > 64.0 * 64.0) {
                continue;
            }
            float fade = age > s.ttlMs - 350L ? (float) (s.ttlMs - age) / 350.0f : 1.0f;
            float pulse = 0.92f + 0.08f * (float) Math.sin(s.phase + age * 0.01f);
            int argb = (MathHelper.clamp((int) (fade * 255.0f), 0, 255) << 24) | (s.argb & 0xFFFFFF);
            if ((argb >>> 24) <= 2) {
                continue;
            }

            stack.push();
            stack.translate(dx, dy, dz);
            stack.multiply(camRot);
            MatrixStack.Entry entry = stack.peek();
            float half = s.size * 0.5f * pulse;
            buffer.vertex(entry, -half, -half, 0.0f).texture(0.0f, 0.0f).color(argb);
            buffer.vertex(entry, half, -half, 0.0f).texture(1.0f, 0.0f).color(argb);
            buffer.vertex(entry, half, half, 0.0f).texture(1.0f, 1.0f).color(argb);
            buffer.vertex(entry, -half, half, 0.0f).texture(0.0f, 1.0f).color(argb);
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
                    float dx = Math.abs(x - half) / half;
                    float dy = Math.abs(y - half) / half;
                    float d = Math.max(dx, dy);
                    float a = MathHelper.clamp((1.0f - d) * 3.2f, 0.0f, 1.0f);
                    image.setColorArgb(x, y, ((int) (a * 255.0f) << 24) | 0xFFFFFF);
                }
            }
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko shard", image);
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
            System.out.println("[KIMIKO] Block Shards: не удалось создать текстуру (" + t + ")");
        }
    }
}
