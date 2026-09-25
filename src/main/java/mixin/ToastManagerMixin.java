/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.toast.Toast
 *  net.minecraft.client.toast.SystemToast
 *  net.minecraft.client.toast.SystemToast$Type
 *  net.minecraft.client.toast.ToastManager
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ToastManager.class})
public class ToastManagerMixin {
    @Inject(method={"add"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$dropUnsecureChatWarning(Toast toast, CallbackInfo ci) {
        SystemToast systemToast;
        if (toast instanceof SystemToast && (systemToast = (SystemToast)toast).getType() == SystemToast.Type.UNSECURE_SERVER_WARNING) {
            ci.cancel();
        }
    }
}

