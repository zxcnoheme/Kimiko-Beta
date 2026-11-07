package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Рябь крон: из листвы рядом с игроком слетают пылинки цвета листвы
 * (в цветущей вишне — розовые). Спавн внутри кроны, с лимитом бюджета.
 */
public class LeafSwayLiteModule extends Module {
    private static final DustParticleEffect GREEN = new DustParticleEffect(0xFF5FA746, 0.35f);
    private static final DustParticleEffect CHERRY = new DustParticleEffect(0xFFFFA8C8, 0.35f);
    private static final int MAX_PER_SECOND = 60;

    private final SliderSetting density;
    private int tickCounter;
    private int secondBudget;

    public LeafSwayLiteModule() {
        super("Leaf Shimmer NEW", "Пылинки цвета листвы слетают из крон рядом с игроком.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько колонок проверяется.").setValue(1.0f).range(0.2f, 3.0f));
        this.tickCounter = 0;
        this.secondBudget = MAX_PER_SECOND;
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
        if (++this.tickCounter % 20 == 0) {
            this.secondBudget = MAX_PER_SECOND;
        }
        if (this.tickCounter % 4 != 0) {
            return;
        }
        int attempts = MathHelper.clamp(Math.round(3.0f * this.density.getValue()), 1, 12);
        boolean cherry = this.isCherry(world);
        double px = this.mc.player.getX();
        double py = this.mc.player.getY();
        double pz = this.mc.player.getZ();
        int baseY = MathHelper.floor(py);

        for (int i = 0; i < attempts && this.secondBudget > 0; i++) {
            float ang = (float) (Math.random() * Math.PI * 2.0);
            float dist = 3.0f + (float) Math.random() * 9.0f;
            int x = MathHelper.floor(px + MathHelper.cos(ang) * dist);
            int z = MathHelper.floor(pz + MathHelper.sin(ang) * dist);
            int startDy = MathHelper.floor(Math.random() * 11.0) - 5;
            for (int dy = startDy; dy >= -6; dy--) {
                int y = baseY + dy;
                if (y < world.getBottomY()) {
                    break;
                }
                BlockPos pos = new BlockPos(x, y, z);
                BlockState state = world.getBlockState(pos);
                if (!state.isIn(BlockTags.LEAVES)) {
                    continue;
                }
                // пылинка внутри кроны, на уровне найденного блока
                world.addParticleClient(cherry ? CHERRY : GREEN,
                        x + 0.15 + Math.random() * 0.7,
                        y + 0.15 + Math.random() * 0.6,
                        z + 0.15 + Math.random() * 0.7,
                        (Math.random() - 0.5) * 0.02,
                        -0.01 - Math.random() * 0.02,
                        (Math.random() - 0.5) * 0.02);
                this.secondBudget--;
                break;
            }
        }
    }

    private boolean isCherry(ClientWorld world) {
        String path = world.getBiome(this.mc.player.getBlockPos())
                .getKey().map(k -> k.getValue().getPath()).orElse("");
        return path.contains("cherry");
    }
}
