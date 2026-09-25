/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resource.server.ServerResourcePackLoader
 *  net.minecraft.resource.ResourcePack
 *  net.minecraft.resource.ResourceType
 *  net.minecraft.resource.ResourcePackProfile
 *  net.minecraft.client.resource.server.ReloadScheduler$PackInfo
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package mixin.chathads;

import java.util.List;
import mods.chathads.ChatHeads;
import net.minecraft.client.resource.server.ServerResourcePackLoader;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.client.resource.server.ReloadScheduler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ServerResourcePackLoader.class})
public abstract class DownloadedPackSourceMixin {
    @Inject(method={"toProfiles"}, at={@At(value="RETURN")})
    public void chatheads$checkForDisableResource(List<ReloadScheduler.PackInfo> list, CallbackInfoReturnable<List<ResourcePackProfile>> cir) {
        List<ResourcePackProfile> packs = cir.getReturnValue();
        if (packs == null) {
            return;
        }
        for (ResourcePackProfile serverPack : packs) {
            try (ResourcePack resources = serverPack.createResourcePack()) {
                if (resources != null && resources.open(ResourceType.CLIENT_RESOURCES, ChatHeads.DISABLE_RESOURCE) != null) {
                    ChatHeads.serverDisabledChatHeads = true;
                }
            }
        }
    }
}

