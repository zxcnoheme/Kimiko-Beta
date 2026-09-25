/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.Constant
 *  org.spongepowered.asm.mixin.injection.ModifyConstant
 */
package mixin.waveycapes;

import mods.waveycapes.CustomCapeRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value={CustomCapeRenderer.class}, remap=false)
public class WaveyCapeThicknessMixin {
    private static final float KIMIKO_THIN_CAPE_DEPTH = -0.015625f;

    @ModifyConstant(method={"renderSmoothCape"}, constant={@Constant(floatValue=-0.0625f)}, remap=false)
    private float kimiko$thinWaveyCapeDepth(float original) {
        return -0.015625f;
    }
}

