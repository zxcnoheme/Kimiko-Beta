package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

/**
 * Хруст льда: при ходьбе по льду у ног летят ледяные пылинки
 * и слышен слабый хруст — лёд «постукивает» под шагами.
 */
public class IceCrackleModule extends Module {
    private static final DustParticleEffect ICE_DUST = new DustParticleEffect(0xFFCFE8FF, 0.3f);

    private final SliderSetting density;
    private int tickCounter;

    public IceCrackleModule() {
        super("Ice Crackle NEW", "Ледяные пылинки и тихий хруст под ногами при ходьбе по льду.", Category.VISUALS);
        this.density = (SliderSetting) this.register((Setting) new SliderSetting("Плотность", "Сколько пылинок за шаг.").setValue(1.0f).range(0.2f, 3.0f));
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
        if (++this.tickCounter % 12 != 0) {
            return;
        }
        if (!this.mc.player.isOnGround()) {
            return;
        }
        Vec3d velocity = this.mc.player.getVelocity();
        double speed = Math.sqrt(velocity.x * velocity.x + velocity.z * velocity.z);
        if (speed <= 0.1) {
            return;
        }
        BlockState under = world.getBlockState(this.mc.player.getBlockPos().down());
        if (!under.isOf(Blocks.ICE) && !under.isOf(Blocks.FROSTED_ICE)) {
            return;
        }
        int count = MathHelper.clamp(Math.round(2.0f * this.density.getValue()), 1, 5);
        double px = this.mc.player.getX();
        double py = this.mc.player.getY();
        double pz = this.mc.player.getZ();
        for (int i = 0; i < count; i++) {
            double ang = Math.random() * Math.PI * 2.0;
            double r = 0.1 + Math.random() * 0.35;
            world.addParticleClient(ICE_DUST,
                    px + MathHelper.cos(ang) * r,
                    py + 0.05,
                    pz + MathHelper.sin(ang) * r,
                    (Math.random() - 0.5) * 0.05,
                    0.03 + Math.random() * 0.05,
                    (Math.random() - 0.5) * 0.05);
        }
        try {
            this.mc.getSoundManager().play(PositionedSoundInstance.ui(SoundEvents.BLOCK_SNOW_PLACE,
                    0.9f + (float) Math.random() * 0.2f, 0.3f));
        } catch (Throwable ignored) {
        }
    }
}
