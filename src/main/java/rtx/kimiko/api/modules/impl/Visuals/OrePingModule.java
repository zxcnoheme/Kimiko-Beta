package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.block.Block;
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
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Пинг руд: возле рудных блоков изредка вспыхивают пылинки —
 * бирюзовые у ценных руд, белые у обычных.
 */
public class OrePingModule extends Module {
    private static final DustParticleEffect CYAN = new DustParticleEffect(0xFF6FF7E4, 0.3f);
    private static final DustParticleEffect WHITE = new DustParticleEffect(0xFFFFFFFF, 0.3f);

    private final ModeSetting mode;
    private final SliderSetting density;
    private int tickCounter;

    public OrePingModule() {
        super("Ore Ping NEW", "Пылинки-«пинги» возле рудных блоков под землёй.", Category.VISUALS);
        this.mode = (ModeSetting) this.register((Setting) new ModeSetting("Какие руды", "Какие руды подсвечивать.", "Только ценные", "Только ценные", "Все"));
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько пылинок у каждой руды.").setValue(1.0f).range(0.2f, 3.0f));
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
        for (int dx = -2; dx <= 2; dx++) {
            for (int dy = -2; dy <= 1; dy++) {
                for (int dz = -2; dz <= 2; dz++) {
                    BlockPos pos = new BlockPos(px + dx, py + dy, pz + dz);
                    Block block = world.getBlockState(pos).getBlock();
                    DustParticleEffect color = colorFor(block);
                    if (color == null) {
                        continue;
                    }
                    world.addParticleClient(color,
                            pos.getX() + 0.2 + Math.random() * 0.6,
                            pos.getY() + 0.2 + Math.random() * 0.6,
                            pos.getZ() + 0.2 + Math.random() * 0.6,
                            0.0, 0.01 + Math.random() * 0.02, 0.0);
                }
            }
        }
    }

    /** null — руда не интересует этот режим. */
    private DustParticleEffect colorFor(Block block) {
        boolean valuable = block == Blocks.DIAMOND_ORE || block == Blocks.DEEPSLATE_DIAMOND_ORE
                || block == Blocks.EMERALD_ORE || block == Blocks.ANCIENT_DEBRIS;
        if (!valuable && this.mode.is("Только ценные")) {
            return null;
        }
        boolean ore = valuable || block == Blocks.GOLD_ORE || block == Blocks.IRON_ORE;
        if (!ore) {
            return null;
        }
        if (Math.random() > this.density.getValue() * 0.5f) {
            return null;
        }
        return valuable ? CYAN : WHITE;
    }
}
