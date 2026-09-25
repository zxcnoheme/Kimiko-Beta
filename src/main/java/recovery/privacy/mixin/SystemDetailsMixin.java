/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package recovery.privacy.mixin;

import java.util.function.Supplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets={"net.minecraft.util.SystemDetails"}, remap=false)
public abstract class SystemDetailsMixin {
    @Inject(method={"tryAddGroup(Ljava/lang/String;Ljava/lang/Runnable;)V"}, at={@At(value="HEAD")}, cancellable=true, require=1, remap=false)
    private void privacy$hardware(String string, Runnable runnable, CallbackInfo callbackInfo) {
        if (string.equals("hardware") || string.equals("processor") || string.equals("graphics") || string.equals("memory")) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"addSection(Ljava/lang/String;Ljava/util/function/Supplier;)V"}, at={@At(value="HEAD")}, cancellable=true, require=1, remap=false)
    private void privacy$details(String string, Supplier<String> supplier, CallbackInfo callbackInfo) {
        if (string.equals("Memory") || string.equals("CPUs") || string.equals("JVM Flags") || string.equals("Debug Flags")) {
            callbackInfo.cancel();
        }
    }
}

