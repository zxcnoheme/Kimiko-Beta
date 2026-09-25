/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.hud.ChatHudLine
 *  net.minecraft.client.gui.hud.ChatHud
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.chathads;

import mods.chathads.ChatHeads;
import mods.chathads.HeadData;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ChatHud.class}, priority=10100)
public abstract class ChatComponentMixin2 {
    @Inject(method={"addVisibleMessage(Lnet/minecraft/client/gui/hud/ChatHudLine;)V"}, at={@At(value="HEAD")})
    private void chatheads$transferMessageOwner(ChatHudLine guiMessage, CallbackInfo ci) {
        ChatHeads.lineData = ChatHeads.lastSenderData;
    }

    @ModifyArg(method={"addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ChatHud;addVisibleMessage(Lnet/minecraft/client/gui/hud/ChatHudLine;)V"))
    private ChatHudLine chatheads$setOwner(ChatHudLine message) {
        ChatHeads.setHeadData(message, ChatHeads.lastSenderData);
        return message;
    }

    @ModifyArg(method={"addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ChatHud;addMessage(Lnet/minecraft/client/gui/hud/ChatHudLine;)V"))
    private ChatHudLine chatheads$setOwner2(ChatHudLine message) {
        ChatHeads.setHeadData(message, ChatHeads.lastSenderData);
        return message;
    }

    @Inject(method={"addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V"}, at={@At(value="RETURN")})
    private void chatheads$forgetSender(CallbackInfo ci) {
        ChatHeads.lastSenderData = HeadData.EMPTY;
    }
}

