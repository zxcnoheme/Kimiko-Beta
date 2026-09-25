/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyReturnValue
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Pseudo
 *  org.spongepowered.asm.mixin.injection.At
 */
package mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import rtx.kimiko.api.modules.impl.Visuals.WastedDeath;

@Pseudo
@Mixin(targets={"net/caffeinemc/mods/sodium/client/render/chunk/RenderSectionManager"}, remap=false)
public abstract class SodiumWastedCullingMixin {
    @ModifyReturnValue(method={"shouldUseOcclusionCulling"}, at={@At(value="RETURN")}, require=0, remap=false)
    private boolean kimiko$wastedDisableOcclusion(boolean original) {
        return original && !WastedDeath.isRunning();
    }
}

