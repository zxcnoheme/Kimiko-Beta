/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.text.Text
 *  net.minecraft.client.network.message.MessageHandler
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 */
package mixin.chathads.fabric;

import mods.chathads.ChatHeads;
import net.minecraft.text.Text;
import net.minecraft.client.network.message.MessageHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value={MessageHandler.class})
public abstract class ChatListenerMixin {
    @ModifyArg(method={"method_45745"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ChatHud;addMessage(Lnet/minecraft/text/Text;)V", ordinal=0))
    public Text chatheads$handleAddedDisguisedMessage(Text message) {
        return ChatHeads.handleAddedMessage(message, null);
    }
}

