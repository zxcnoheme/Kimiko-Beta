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
package mixin;

import net.minecraft.text.Text;
import net.minecraft.client.network.message.MessageHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import rtx.kimiko.api.modules.impl.Utils.StreamerMode;

@Mixin(value={MessageHandler.class})
public abstract class ChatRankMixin {
    @ModifyArg(method={"processChatMessageInternal"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ChatHud;addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V", ordinal=0), require=0)
    private Text kimiko$playerChatRank(Text message) {
        return StreamerMode.applySelfRankInChat(message);
    }

    @ModifyArg(method={"onGameMessage"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ChatHud;addMessage(Lnet/minecraft/text/Text;)V", ordinal=0), require=0)
    private Text kimiko$systemChatRank(Text message) {
        return StreamerMode.applySelfRankInChat(message);
    }
}

