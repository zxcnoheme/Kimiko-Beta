package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Отскок слайма: приземление на слизневый блок выбивает веер
 * зелёных частиц — блок «пружинит» под игроком. Только частицы,
 * без колец (кольца приземления уже делает Fall Impact Ring).
 */
public class SlimeBounceModule extends Module {
    private static final DustParticleEffect SLIME = new DustParticleEffect(0xFF7ACF5F, 0.4f);

    private final SliderSetting density;
    private double prevFallDistance;

    public SlimeBounceModule() {
        super("Slime Bounce NEW", "Веер зелёных частиц при приземлении на слизневый блок.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько частиц за отскок.").setValue(1.0f).range(0.4f, 2.0f));
        this.prevFallDistance = 0.0;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null) {
            this.prevFallDistance = 0.0;
            return;
        }
        if (this.isEnabled() && this.mc.player.isOnGround() && this.prevFallDistance > 1.0
                && world.getBlockState(this.mc.player.getBlockPos().down()).isOf(Blocks.SLIME_BLOCK)) {
            Vec3d pos = this.mc.player.getLerpedPos(1.0f);
            int count = MathHelper.clamp(Math.round(6.0f * this.density.getValue()), 3, 12);
            for (int i = 0; i < count; i++) {
                double ang = Math.random() * Math.PI * 2.0;
                double r = 0.15 + Math.random() * 0.3;
                double push = 0.06 + Math.random() * 0.1;
                world.addParticleClient(SLIME,
                        pos.x + MathHelper.cos(ang) * r,
                        pos.y + 0.05 + Math.random() * 0.1,
                        pos.z + MathHelper.sin(ang) * r,
                        MathHelper.cos(ang) * push,
                        0.05 + Math.random() * 0.1,
                        MathHelper.sin(ang) * push);
            }
        }
        this.prevFallDistance = this.mc.player.fallDistance;
    }
}
