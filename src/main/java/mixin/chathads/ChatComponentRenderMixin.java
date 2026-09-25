/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.hud.ChatHud
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.chathads;

import mods.chathads.ChatHeads;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ChatHud.class})
public abstract class ChatComponentRenderMixin {
    @Inject(method={"render(Lnet/minecraft/client/gui/hud/ChatHud$Backend;IIZ)V"}, at={@At(value="HEAD")})
    public void chatheads$isInsideChat(CallbackInfo ci) {
        ChatHeads.customHeadRendering = true;
    }

    @Inject(method={"render(Lnet/minecraft/client/gui/hud/ChatHud$Backend;IIZ)V"}, at={@At(value="RETURN")})
    public void chatheads$isOutsideChat(CallbackInfo ci) {
        ChatHeads.customHeadRendering = false;
    }
}

