/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Pseudo
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets={"net/caffeinemc/mods/sodium/client/render/chunk/RenderSection"}, remap=false)
public abstract class SodiumSectionFadeMixin {
    @Inject(method={"getCurrentVisibility"}, at={@At(value="HEAD")}, cancellable=true, require=0, remap=false)
    private void kimiko$noChunkFade(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(Float.valueOf(1.0f));
    }
}

