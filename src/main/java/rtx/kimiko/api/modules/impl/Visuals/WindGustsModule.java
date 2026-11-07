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
 * Порывы ветра: каждые 15–35 секунд мимо пробегает порыв — с крон
 * срываются листья и летят по ветру волной.
 */
public class WindGustsModule extends Module {
    private static final DustParticleEffect LEAF = new DustParticleEffect(0xFF63A83F, 0.5f);
    private static final DustParticleEffect LEAF_DRY = new DustParticleEffect(0xFFB99A45, 0.5f);

    private final SliderSetting frequency;
    private int nextGust;
    private int gustTicksLeft;
    private float gustDirX;
    private float gustDirZ;

    public WindGustsModule() {
        super("Wind Gusts NEW", "Периодические порывы ветра: с крон срываются листья и летят волной в одну сторону.", Category.VISUALS);
        this.frequency = (SliderSetting) this.register((Setting) new SliderSetting("Частота", "Средняя пауза между порывами (сек).").setValue(25.0f).range(8.0f, 60.0f));
        this.nextGust = 300;
        this.gustTicksLeft = 0;
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
        if (this.gustTicksLeft <= 0) {
            if (--this.nextGust <= 0) {
                this.nextGust = MathHelper.floor(this.frequency.getValue() * 20.0f * (0.6f + (float) Math.random() * 0.8f));
                this.gustTicksLeft = 45 + (int) (Math.random() * 40);
                float ang = (float) (Math.random() * Math.PI * 2.0);
                this.gustDirX = MathHelper.cos(ang);
                this.gustDirZ = MathHelper.sin(ang);
            }
            return;
        }
        this.gustTicksLeft--;
        if (this.gustTicksLeft % 3 != 0) {
            return;
        }
        // во время порыва: ищем листву поблизости и срываем с неё листья
        for (int i = 0; i < 5; i++) {
            double x = this.mc.player.getX() + (Math.random() - 0.5) * 22.0;
            double z = this.mc.player.getZ() + (Math.random() - 0.5) * 22.0;
            int bx = MathHelper.floor(x);
            int bz = MathHelper.floor(z);
            int eyeY = MathHelper.floor(this.mc.player.getEyeY());
            for (int y = Math.min(eyeY + 9, world.getTopYInclusive() - 1); y >= eyeY - 3; y--) {
                BlockPos pos = new BlockPos(bx, y, bz);
                BlockState state = world.getBlockState(pos);
                if (state.isAir()) {
                    continue;
                }
                if (state.isIn(BlockTags.LEAVES)) {
                    DustParticleEffect fx = Math.random() < 0.75f ? LEAF : LEAF_DRY;
                    float gustPower = 0.16f + (float) Math.random() * 0.14f;
                    world.addParticleClient(fx, x, y - 0.2, z,
                            this.gustDirX * gustPower, -0.02 - Math.random() * 0.04, this.gustDirZ * gustPower);
                    break;
                }
                break;
            }
        }
    }
}
