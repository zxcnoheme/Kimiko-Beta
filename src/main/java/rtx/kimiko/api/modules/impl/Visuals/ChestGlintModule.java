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
 * Блеск сундуков: рядом с сундуками и бочками изредка вспыхивают
 * золотые пылинки — намёк на хранилище.
 */
public class ChestGlintModule extends Module {
    private static final DustParticleEffect GLINT = new DustParticleEffect(0xFFFFC96B, 0.3f);

    private final SliderSetting density;
    private int tickCounter;

    public ChestGlintModule() {
        super("Chest Glint NEW", "Золотые пылинки мерцают возле сундуков и бочек.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько пылинок у каждого сундука.").setValue(1.0f).range(0.2f, 3.0f));
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
        if (++this.tickCounter % 15 != 0) {
            return;
        }
        int px = MathHelper.floor(this.mc.player.getX());
        int py = MathHelper.floor(this.mc.player.getY());
        int pz = MathHelper.floor(this.mc.player.getZ());
        for (int dx = -3; dx <= 2; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -3; dz <= 2; dz++) {
                    BlockPos pos = new BlockPos(px + dx, py + dy, pz + dz);
                    if (!world.getBlockState(pos).isOf(Blocks.CHEST)
                            && !world.getBlockState(pos).isOf(Blocks.TRAPPED_CHEST)
                            && !world.getBlockState(pos).isOf(Blocks.BARREL)) {
                        continue;
                    }
                    int count = Math.max(1, (int) Math.round(1.0f + Math.random() * this.density.getValue()));
                    for (int i = 0; i < count; i++) {
                        world.addParticleClient(GLINT,
                                pos.getX() + 0.2 + Math.random() * 0.6,
                                pos.getY() + 0.15 + Math.random() * 0.7,
                                pos.getZ() + 0.2 + Math.random() * 0.6,
                                0.0, 0.01 + Math.random() * 0.02, 0.0);
                    }
                }
            }
        }
    }
}
