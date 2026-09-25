/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.command.ModelCommandRenderer$CrumblingOverlayCommand
 *  net.minecraft.client.render.block.entity.state.BlockEntityRenderState
 *  net.minecraft.util.math.Position
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.block.entity.BlockEntity
 *  net.minecraft.client.render.block.entity.BlockEntityRenderManager
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package mixin;

import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRenderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rtx.kimiko.api.modules.impl.Utils.Optimization;

@Mixin(value={BlockEntityRenderManager.class})
public abstract class OptimizationBlockEntityMixin {
    @Shadow
    private Vec3d cameraPos;

    @Inject(method={"getRenderState"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private <E extends BlockEntity, S extends BlockEntityRenderState> void kimiko$cullDistantBlockEntities(E blockEntity, float partialTick, ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlay, CallbackInfoReturnable<S> cir) {
        if (this.cameraPos == null) {
            return;
        }
        double distSq = blockEntity.getPos().getSquaredDistance((Position)this.cameraPos);
        if (!Optimization.allowBlockEntity(distSq)) {
            cir.setReturnValue(null);
        }
    }
}

