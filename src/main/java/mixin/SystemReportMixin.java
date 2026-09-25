/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.SystemDetails
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.util.SystemDetails;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SystemDetails.class})
public abstract class SystemReportMixin {
    @Inject(method={"tryAddGroup"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$skipOshiHardwareReport(String group, Runnable runnable, CallbackInfo ci) {
        if ("hardware".equals(group)) {
            ci.cancel();
        }
    }
}

