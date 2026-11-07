package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DustParticleEffect;
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
 * Руны стола зачарований: над столом вьются ванильные рунные частицы
 * и всплывает лиловая пылинка — магия «работает» даже без книги.
 */
public class EnchantTableRunesModule extends Module {
    private static final DustParticleEffect RUNE_DUST = new DustParticleEffect(0xFFB08CFF, 0.3f);

    private final SliderSetting density;
    private int tickCounter;

    public EnchantTableRunesModule() {
        super("Enchant Runes NEW", "Рунные частицы вьются над столами зачарований.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько рун появляется.").setValue(1.0f).range(0.2f, 3.0f));
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
        int cap = MathHelper.clamp(Math.round(2.0f * this.density.getValue()), 1, 3);
        int found = 0;
        for (BlockPos pos : BlockPos.iterateOutwards(this.mc.player.getBlockPos(), 4, 2, 4)) {
            if (!world.getBlockState(pos).isOf(Blocks.ENCHANTING_TABLE)) {
                continue;
            }
            int runes = MathHelper.clamp(Math.round(2.0f * this.density.getValue()), 1, 4);
            for (int i = 0; i < runes; i++) {
                world.addParticleClient(ParticleTypes.ENCHANT,
                        pos.getX() + 0.5 + (Math.random() - 0.5) * 0.9,
                        pos.getY() + 1.15 + Math.random() * 0.5,
                        pos.getZ() + 0.5 + (Math.random() - 0.5) * 0.9,
                        0.0, 0.0, 0.0);
            }
            world.addParticleClient(RUNE_DUST,
                    pos.getX() + 0.5 + (Math.random() - 0.5) * 0.7,
                    pos.getY() + 1.1 + Math.random() * 0.4,
                    pos.getZ() + 0.5 + (Math.random() - 0.5) * 0.7,
                    0.0, 0.01 + Math.random() * 0.02, 0.0);
            if (++found >= cap) {
                break;
            }
        }
    }
}
