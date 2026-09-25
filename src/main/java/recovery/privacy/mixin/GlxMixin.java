/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package recovery.privacy.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets={"com.mojang.blaze3d.platform.GLX"}, remap=false)
public abstract class GlxMixin {
    @Inject(method={"_getCpuInfo()Ljava/lang/String;"}, at={@At(value="HEAD")}, cancellable=true, require=1, remap=false)
    private static void privacy$cpu(CallbackInfoReturnable<String> callbackInfoReturnable) {
        callbackInfoReturnable.setReturnValue("Disabled (privacy build)");
    }
}

