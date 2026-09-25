/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyReturnValue
 *  net.minecraft.entity.Entity
 *  net.minecraft.client.render.Frustum
 *  net.minecraft.client.render.entity.EntityRenderManager
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 */
package mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import rtx.kimiko.api.modules.impl.Utils.Optimization;

@Mixin(value={EntityRenderManager.class})
public abstract class OptimizationEntityCullingMixin {
    @ModifyReturnValue(method={"shouldRender"}, at={@At(value="RETURN")}, require=1)
    private boolean kimiko$occlusionCull(boolean original, Entity entity, Frustum frustum, double camX, double camY, double camZ) {
        return Optimization.shouldRenderEntity(original, entity, camX, camY, camZ);
    }
}

