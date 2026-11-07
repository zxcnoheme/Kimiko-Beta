package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.block.BlockState;
import net.minecraft.block.CandleBlock;
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
 * Искры свечей: над любой свечой (включая цветные) поднимается
 * маленькая тёплая искра — пламя «трещит».
 */
public class CandleLightModule extends Module {
    private static final DustParticleEffect SPARK = new DustParticleEffect(0xFFFFC96B, 0.25f);

    private final SliderSetting density;
    private int tickCounter;

    public CandleLightModule() {
        super("Candle Sparks NEW", "Тёплые искры поднимаются над свечами рядом.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько свечей искрят.").setValue(1.0f).range(0.2f, 3.0f));
        this.tickCounter = 0;
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
        int cap = MathHelper.clamp(Math.round(2.0f * this.density.getValue()), 1, 4);
        int found = 0;
        for (BlockPos pos : BlockPos.iterateOutwards(this.mc.player.getBlockPos(), 4, 3, 4)) {
            BlockState state = world.getBlockState(pos);
            if (!(state.getBlock() instanceof CandleBlock)) {
                continue;
            }
            world.addParticleClient(SPARK,
                    pos.getX() + 0.3 + Math.random() * 0.4,
                    pos.getY() + 0.55 + Math.random() * 0.35,
                    pos.getZ() + 0.3 + Math.random() * 0.4,
                    (Math.random() - 0.5) * 0.01,
                    0.01 + Math.random() * 0.03,
                    (Math.random() - 0.5) * 0.01);
            if (++found >= cap) {
                break;
            }
        }
    }
}
