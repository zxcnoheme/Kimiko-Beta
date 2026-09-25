/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.ClientConnection
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.chathads;

import mods.chathads.ChatHeads;
import net.minecraft.network.ClientConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ClientConnection.class})
public abstract class ConnectionMixin {
    @Inject(method={"connect(Ljava/lang/String;ILnet/minecraft/network/listener/ClientLoginPacketListener;)V", "connect(Ljava/lang/String;ILnet/minecraft/network/state/NetworkState;Lnet/minecraft/network/state/NetworkState;Lnet/minecraft/network/listener/ClientPacketListener;Z)V"}, at={@At(value="HEAD")})
    public void chatheads$resetServerKnowledge(CallbackInfo ci) {
        ChatHeads.serverSentUuid = false;
        ChatHeads.serverDisabledChatHeads = false;
    }
}

