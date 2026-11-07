package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Частицы эндер-сундука: вокруг эндер-сундуков вьются фиолетовые
 * частицы портала.
 */
public class EnderChestParticlesModule extends Module {
    private final SliderSetting density;
    private int tickCounter;

    public EnderChestParticlesModule() {
        super("Ender Chest Particles NEW", "Фиолетовые частицы портала вьются вокруг эндер-сундуков.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько частиц у каждого сундука.").setValue(1.0f).range(0.2f, 3.0f));
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
        int px = MathHelper.floor(this.mc.player.getX());
        int py = MathHelper.floor(this.mc.player.getY());
        int pz = MathHelper.floor(this.mc.player.getZ());
        for (int dx = -2; dx <= 2; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -2; dz <= 2; dz++) {
                    BlockPos pos = new BlockPos(px + dx, py + dy, pz + dz);
                    if (!world.getBlockState(pos).isOf(Blocks.ENDER_CHEST)) {
                        continue;
                    }
                    int count = Math.max(1, Math.round(2.0f * this.density.getValue()));
                    for (int i = 0; i < count; i++) {
                        double ang = Math.random() * Math.PI * 2.0;
                        double r = 0.4 + Math.random() * 0.25;
                        world.addParticleClient(ParticleTypes.PORTAL,
                                pos.getX() + 0.5 + Math.cos(ang) * r,
                                pos.getY() + 0.5 + Math.random() * 0.6,
                                pos.getZ() + 0.5 + Math.sin(ang) * r,
                                Math.cos(ang) * -0.03, (Math.random() - 0.5) * 0.05, Math.sin(ang) * -0.03);
                    }
                }
            }
        }
    }
}
