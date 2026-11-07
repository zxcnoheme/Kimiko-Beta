package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Круги на воде от лилий: вокруг кувшинки по воде расходится
 * лёгкая рябь из голубоватых пылинок.
 */
public class LilyPadRippleModule extends Module {
    private static final DustParticleEffect RIPPLE = new DustParticleEffect(0xFFBFE8FF, 0.3f);

    private final SliderSetting density;
    private int tickCounter;

    public LilyPadRippleModule() {
        super("Lily Pad Ripple NEW", "Рябь расходится по воде вокруг кувшинок.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Насколько часто появляется рябь.").setValue(1.0f).range(0.2f, 3.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null) {
            return;
        }
        if (++this.tickCounter % 12 != 0) {
            return;
        }
        int columns = Math.round(8.0f * this.density.getValue());
        for (int i = 0; i < columns; i++) {
            float ang = (float) (Math.random() * Math.PI * 2.0);
            float dist = 1.0f + (float) Math.random() * 7.0f;
            double x = this.mc.player.getX() + MathHelper.cos(ang) * dist;
            double z = this.mc.player.getZ() + MathHelper.sin(ang) * dist;
            int bx = MathHelper.floor(x);
            int bz = MathHelper.floor(z);
            int top = FirefliesModule.topY(world, bx, bz);
            if (top <= world.getBottomY() + 1) {
                continue;
            }
            if (!world.getBlockState(new BlockPos(bx, top - 1, bz)).isOf(Blocks.LILY_PAD)) {
                continue;
            }
            double y = top - 0.1;
            double baseAng = Math.random() * Math.PI * 2.0;
            for (int k = 0; k < 3; k++) {
                double a = baseAng + k * (Math.PI * 2.0 / 3.0);
                double r = 0.45 + Math.random() * 0.2;
                world.addParticleClient(RIPPLE,
                        bx + 0.5 + Math.cos(a) * r, y, bz + 0.5 + Math.sin(a) * r,
                        Math.cos(a) * 0.03, 0.005, Math.sin(a) * 0.03);
            }
        }
    }
}
