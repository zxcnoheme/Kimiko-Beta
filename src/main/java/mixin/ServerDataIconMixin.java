/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.network.ServerInfo
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.client.network.ServerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.utils.network.ServerIconHarvester;

@Mixin(value={ServerInfo.class})
public class ServerDataIconMixin {
    @Shadow
    public String address;
    @Shadow
    public String name;

    @Inject(method={"setFavicon"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$harvestIcon(byte[] bytes, CallbackInfo ci) {
        ServerIconHarvester.capture(this.address != null && !this.address.isBlank() ? this.address : this.name, bytes);
    }
}

