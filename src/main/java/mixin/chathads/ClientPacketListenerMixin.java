/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.sugar.Share
 *  com.llamalad7.mixinextras.sugar.ref.LocalRef
 *  net.minecraft.client.network.ClientPlayNetworkHandler
 *  net.minecraft.client.network.PlayerListEntry
 *  net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket
 *  net.minecraft.network.message.SignedMessage
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.chathads;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import java.util.UUID;
import mods.chathads.ChatHeads;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;
import net.minecraft.network.message.SignedMessage;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ClientPlayNetworkHandler.class}, priority=990)
public abstract class ClientPacketListenerMixin {
    @Shadow
    @Nullable
    public abstract PlayerListEntry getPlayerListEntry(UUID var1);

    @Inject(method={"onChatMessage"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/message/MessageHandler;onChatMessage(Lnet/minecraft/network/message/SignedMessage;Lcom/mojang/authlib/GameProfile;Lnet/minecraft/network/message/MessageType$Parameters;)V")})
    public void chatheads$captureSenderInfo(ChatMessageS2CPacket packet, CallbackInfo ci, @Share(value="senderInfo") LocalRef<PlayerListEntry> senderInfo) {
        PlayerListEntry playerInfo = this.getPlayerListEntry(packet.sender());
        senderInfo.set(playerInfo);
    }

    @ModifyArg(method={"onChatMessage"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/network/message/MessageHandler;onChatMessage(Lnet/minecraft/network/message/SignedMessage;Lcom/mojang/authlib/GameProfile;Lnet/minecraft/network/message/MessageType$Parameters;)V"), index=0)
    public SignedMessage chatheads$rememberSenderInfo(SignedMessage playerChatMessage, @Share(value="senderInfo") LocalRef<PlayerListEntry> senderInfo) {
        ChatHeads.setOwner(playerChatMessage, (PlayerListEntry)senderInfo.get());
        return playerChatMessage;
    }
}

