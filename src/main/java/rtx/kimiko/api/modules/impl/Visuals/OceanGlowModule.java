package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Океанское свечение: ночью над поверхностью воды в океане мерцают
 * редкие бирюзовые искры — будто под водой плавает планктон.
 */
public class OceanGlowModule extends Module {
    private static final DustParticleEffect SPARK = new DustParticleEffect(0xFF4FF0C8, 0.35f);

    private final SliderSetting density;
    private int tickCounter;

    public OceanGlowModule() {
        super("Ocean Glow NEW", "Бирюзовые искры над водой океана ночью.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько искр появляется за раз.").setValue(1.0f).range(0.2f, 3.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null || world.getAmbientDarkness() < 2) {
            return;
        }
        if (++this.tickCounter % 8 != 0) {
            return;
        }
        int count = Math.max(1, Math.round((3.0f + (Math.random() < 0.5f ? 1.0f : 0.0f)) * this.density.getValue()));
        for (int i = 0; i < count; i++) {
            float ang = (float) (Math.random() * Math.PI * 2.0);
            float dist = 2.0f + (float) Math.random() * 12.0f;
            double x = this.mc.player.getX() + MathHelper.cos(ang) * dist;
            double z = this.mc.player.getZ() + MathHelper.sin(ang) * dist;
            int bx = MathHelper.floor(x);
            int bz = MathHelper.floor(z);
            if (!isOceanSurface(world, bx, bz)) {
                continue;
            }
            int top = FirefliesModule.topY(world, bx, bz);
            if (top <= world.getBottomY() + 1) {
                continue;
            }
            double y = top + 0.05 + Math.random() * 0.12;
            world.addParticleClient(SPARK,
                    x, y, z,
                    (Math.random() - 0.5) * 0.015, 0.005 + Math.random() * 0.015, (Math.random() - 0.5) * 0.015);
        }
    }

    /** Колонка с водой сверху и океанским биомом — как в скане MoonGlint. */
    private static boolean isOceanSurface(ClientWorld world, int x, int z) {
        int top = FirefliesModule.topY(world, x, z);
        if (top <= world.getBottomY() + 1) {
            return false;
        }
        if (!world.getFluidState(new BlockPos(x, top - 1, z)).isIn(FluidTags.WATER)) {
            return false;
        }
        String path = world.getBiome(BlockPos.ofFloored(x + 0.5, top, z + 0.5))
                .getKey().map(k -> k.getValue().getPath()).orElse("");
        return path.contains("ocean");
    }
}
