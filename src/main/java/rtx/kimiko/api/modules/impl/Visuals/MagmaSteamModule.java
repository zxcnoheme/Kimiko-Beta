package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Пар от магмы: когда идёт дождь, над магмой и лавой возле игрока
 * поднимаются струйки пара.
 */
public class MagmaSteamModule extends Module {
    private final SliderSetting density;
    private int tickCounter;

    public MagmaSteamModule() {
        super("Magma Steam NEW", "В дождь над магмой и лавой рядом поднимается пар.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько струй пара.").setValue(1.0f).range(0.3f, 3.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null || ++this.tickCounter % 12 != 0) {
            return;
        }
        if (world.getRainGradient(1.0f) < 0.35f) {
            return;
        }
        BlockPos center = this.mc.player.getBlockPos();
        int found = 0;
        int max = Math.round(this.density.getValue() * 2.0f);
        for (BlockPos pos : BlockPos.iterateOutwards(center, 5, 2, 5)) {
            if (found >= max) {
                break;
            }
            boolean hot = world.getBlockState(pos).isOf(Blocks.MAGMA_BLOCK)
                    || world.getBlockState(pos).isOf(Blocks.LAVA);
            if (!hot) {
                continue;
            }
            // сверху должно быть пусто — пар поднимается
            BlockPos above = pos.up();
            if (!world.getBlockState(above).isAir()) {
                continue;
            }
            world.addParticleClient(ParticleTypes.CLOUD,
                    pos.getX() + 0.3 + Math.random() * 0.4,
                    pos.getY() + 1.05,
                    pos.getZ() + 0.3 + Math.random() * 0.4,
                    0.0, 0.03 + Math.random() * 0.03, 0.0);
            found++;
        }
    }
}
