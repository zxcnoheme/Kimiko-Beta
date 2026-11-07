package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientChunkManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Heightmap;
import net.minecraft.world.chunk.WorldChunk;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Светлячки: тёплые светящиеся точки дрейфуют над землёй в сумерках и ночью
 * в лесах, на лугах и болотах. Плотность дышит — то гуще, то реже.
 */
public class FirefliesModule extends Module {
    private static final DustParticleEffect GLOW = new DustParticleEffect(0xFFFFD980, 0.55f);
    private static final DustParticleEffect GLOW_DIM = new DustParticleEffect(0xFFC9E86B, 0.4f);

    private final ModeSetting where;
    private final SliderSetting density;
    private int tickCounter;

    public FirefliesModule() {
        super("Fireflies NEW", "Светлячки над травой в сумерках и ночью — в лесах, на лугах и болотах.", Category.VISUALS);
        this.where = (ModeSetting) this.register((Setting) new ModeSetting("Где светятся", "Биомы для светлячков.", "Леса и луга", "Леса и луга", "Везде"));
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько светляков появляется.").setValue(1.0f).range(0.2f, 3.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null || world.getAmbientDarkness() < 3) {
            return;
        }
        if (++this.tickCounter % 6 != 0) {
            return;
        }
        // «дыхание» плотности
        float breathe = 0.55f + 0.45f * (float) Math.abs(Math.sin(world.getTime() * 0.02));
        int count = Math.round(this.density.getValue() * breathe);
        for (int i = 0; i < count; i++) {
            float ang = (float) (Math.random() * Math.PI * 2.0);
            float dist = 5.0f + (float) Math.random() * 9.0f;
            double x = this.mc.player.getX() + MathHelper.cos(ang) * dist;
            double z = this.mc.player.getZ() + MathHelper.sin(ang) * dist;
            if (!this.biomeAllowed(world, x, z)) {
                continue;
            }
            int top = topY(world, MathHelper.floor(x), MathHelper.floor(z));
            if (top <= world.getBottomY() + 1) {
                continue;
            }
            double y = top + 0.6 + Math.random() * 1.8;
            world.addParticleClient(Math.random() < 0.3 ? GLOW_DIM : GLOW, x, y, z,
                    (Math.random() - 0.5) * 0.02, 0.01 + Math.random() * 0.02, (Math.random() - 0.5) * 0.02);
        }
    }

    private boolean biomeAllowed(ClientWorld world, double x, double z) {
        if (this.where.is("Везде")) {
            return true;
        }
        String path = world.getBiome(BlockPos.ofFloored(x, this.mc.player.getY(), z))
                .getKey().map(k -> k.getValue().getPath()).orElse("");
        return path.contains("forest") || path.contains("plains") || path.contains("meadow")
                || path.contains("swamp") || path.contains("taiga") || path.contains("cherry")
                || path.contains("grove") || path.contains("flower");
    }

    /** Верхний «поверхностный» блок по heightmap (вода тоже считается поверхностью). */
    static int topY(ClientWorld world, int x, int z) {
        if (world.getChunkManager() instanceof ClientChunkManager chunks) {
            WorldChunk chunk = chunks.getWorldChunk(x >> 4, z >> 4);
            if (chunk != null) {
                Heightmap hm = chunk.getHeightmap(Heightmap.Type.WORLD_SURFACE);
                if (hm != null) {
                    return hm.get(x & 15, z & 15);
                }
            }
        }
        return Integer.MIN_VALUE;
    }
}
