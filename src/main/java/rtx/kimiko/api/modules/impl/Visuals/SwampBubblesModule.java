package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleTypes;
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
 * Болотные пузыри: в болотной воде изредка всплывают пузырьки
 * газа — у самой поверхности воды.
 */
public class SwampBubblesModule extends Module {
    private final SliderSetting density;
    private int tickCounter;

    public SwampBubblesModule() {
        super("Swamp Bubbles NEW", "Пузырьки газа всплывают в болотной воде.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько пузырей появляется за раз.").setValue(1.0f).range(0.2f, 3.0f));
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
        if (++this.tickCounter % 10 != 0) {
            return;
        }
        int count = Math.max(1, Math.round(this.density.getValue()));
        for (int i = 0; i < count; i++) {
            float ang = (float) (Math.random() * Math.PI * 2.0);
            float dist = 1.5f + (float) Math.random() * 6.5f;
            double x = this.mc.player.getX() + MathHelper.cos(ang) * dist;
            double z = this.mc.player.getZ() + MathHelper.sin(ang) * dist;
            int bx = MathHelper.floor(x);
            int bz = MathHelper.floor(z);
            int top = FirefliesModule.topY(world, bx, bz);
            if (top <= world.getBottomY() + 1) {
                continue;
            }
            if (!world.getFluidState(new BlockPos(bx, top - 1, bz)).isIn(FluidTags.WATER)) {
                continue;
            }
            String path = world.getBiome(BlockPos.ofFloored(x, top, z))
                    .getKey().map(k -> k.getValue().getPath()).orElse("");
            if (!path.contains("swamp")) {
                continue;
            }
            world.addParticleClient(ParticleTypes.BUBBLE,
                    x, top - 0.1, z,
                    (Math.random() - 0.5) * 0.02, 0.01 + Math.random() * 0.02, (Math.random() - 0.5) * 0.02);
        }
    }
}
