/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyReturnValue
 *  net.minecraft.client.option.GameOptions
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 */
package mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import rtx.kimiko.api.modules.impl.Utils.Optimization;

@Mixin(value={GameOptions.class})
public abstract class OptimizationOptionsMixin {
    @ModifyReturnValue(method={"getClampedViewDistance"}, at={@At(value="RETURN")}, require=1)
    private int kimiko$capRenderDistance(int original) {
        return Optimization.capEffectiveRenderDistance(original);
    }
}

