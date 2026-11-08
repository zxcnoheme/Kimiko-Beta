package rtx.kimiko.api.modules.impl.Utils;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.projectile.ProjectileUtil;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;

/**
 * Атака сквозь растительность: когда включено — удар по противнику,
 * стоящему в траве/цветах, не ломает растения (клик бьёт по врагу за растением).
 */
public class FoliageFightModule extends Module {
    private static FoliageFightModule instance;

    private final ModeSetting foliageMode;

    public FoliageFightModule() {
        super("Attack Through Grass NEW", "PvP: клик по противнику, стоящему в траве/цветах, бьёт по нему, не ломая растения.", Category.UTILS);
        this.foliageMode = (ModeSetting) this.register((Setting) new ModeSetting("Растения", "Какую растительность пробивать атакой.", "Трава и цветы", "Трава и цветы", "Вся растительность"));
        instance = this;
    }

    /** Вызывается из MinecraftMixin при doAttack: true — клик обработан (атакой по врагу). */
    public static boolean tryAttack(MinecraftClient mc) {
        FoliageFightModule module = instance;
        if (module == null || !module.isEnabled() || mc.player == null || mc.world == null) {
            return false;
        }
        ClientPlayerEntity player = mc.player;
        if (!(mc.crosshairTarget instanceof BlockHitResult hit) || hit.getType() != HitResult.Type.BLOCK) {
            return false;
        }
        BlockState state = mc.world.getBlockState(hit.getBlockPos());
        if (!module.isFoliage(state)) {
            return false;
        }
        double reach = player.getEntityInteractionRange();
        if (reach <= 0.0) {
            return false;
        }
        Vec3d eye = player.getCameraPosVec(1.0f);
        Vec3d look = player.getRotationVec(1.0f);
        Vec3d end = eye.add(look.x * reach, look.y * reach, look.z * reach);

        net.minecraft.util.hit.EntityHitResult entityHit = ProjectileUtil.raycast(player, eye, end,
                player.getBoundingBox().stretch(look.multiply(reach)).expand(1.0),
                e -> e instanceof LivingEntity && e.isAlive() && e != player && !e.isSpectator(), reach);
        if (entityHit == null || !(entityHit.getEntity() instanceof LivingEntity target)) {
            return false;
        }
        // враг должен быть на расстоянии клика, а не за пределами досягаемости
        if (eye.distanceTo(target.getLerpedPos(1.0f)) > reach + 0.5) {
            return false;
        }
        player.attack(target);
        player.swingHand(Hand.MAIN_HAND);
        return true;
    }

    private boolean isFoliage(BlockState state) {
        if (state.isIn(BlockTags.FLOWERS)
                || state.isOf(Blocks.SHORT_GRASS)
                || state.isOf(Blocks.TALL_GRASS)
                || state.isOf(Blocks.FERN)
                || state.isOf(Blocks.LARGE_FERN)
                || state.isOf(Blocks.DEAD_BUSH)
                || state.isOf(Blocks.SWEET_BERRY_BUSH)) {
            return true;
        }
        if (this.foliageMode.is("Вся растительность")) {
            return state.isIn(BlockTags.LEAVES)
                    || state.isIn(BlockTags.CROPS)
                    || state.isOf(Blocks.WHEAT)
                    || state.isOf(Blocks.SUGAR_CANE);
        }
        return false;
    }
}
