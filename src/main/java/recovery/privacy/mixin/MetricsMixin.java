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

import java.util.Collections;
import java.util.Set;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets={"net.minecraft.util.profiler.ServerSamplerSource"}, remap=false)
public abstract class MetricsMixin {
    @Inject(method={"createSystemSamplers()Ljava/util/Set;"}, at={@At(value="HEAD")}, cancellable=true, require=1, remap=false)
    private static void privacy$metrics(CallbackInfoReturnable<Set<?>> callbackInfoReturnable) {
        callbackInfoReturnable.setReturnValue(Collections.emptySet());
    }
}

