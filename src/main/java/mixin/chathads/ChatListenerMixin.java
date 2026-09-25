/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.sugar.Local
 *  net.minecraft.text.Text
 *  net.minecraft.network.message.SignedMessage
 *  net.minecraft.client.network.message.MessageHandler
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 */
package mixin.chathads;

import com.llamalad7.mixinextras.sugar.Local;
import mods.chathads.ChatHeads;
import net.minecraft.text.Text;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.client.network.message.MessageHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value={MessageHandler.class})
public abstract class ChatListenerMixin {
    @ModifyArg(method={"processChatMessageInternal"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ChatHud;addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V", ordinal=0))
    public Text chatheads$handleAddedPlayerMessage(Text message, @Local(argsOnly=true) SignedMessage playerChatMessage) {
        return ChatHeads.handleAddedMessage(message, ChatHeads.getOwner(playerChatMessage));
    }

    @ModifyArg(method={"onGameMessage"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ChatHud;addMessage(Lnet/minecraft/text/Text;)V", ordinal=0))
    public Text chatheads$handleAddedSystemMessage(Text message) {
        if (ChatHeads.CONFIG.handleSystemMessages()) {
            return ChatHeads.handleAddedMessage(message, null);
        }
        return message;
    }
}

