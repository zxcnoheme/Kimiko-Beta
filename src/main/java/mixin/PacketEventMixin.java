/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.channel.ChannelPipeline
 *  io.netty.handler.timeout.ReadTimeoutHandler
 *  net.minecraft.network.ClientConnection
 *  net.minecraft.network.packet.Packet
 *  net.minecraft.network.NetworkSide
 *  net.minecraft.network.handler.PacketSizeLogger
 *  net.minecraft.network.DisconnectionInfo
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.ReadTimeoutHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.handler.PacketSizeLogger;
import net.minecraft.network.DisconnectionInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.impl.network.PacketEvent;
import rtx.kimiko.api.events.impl.network.PacketReceiveEvent;
import rtx.kimiko.api.events.impl.network.PacketSendEvent;
import rtx.kimiko.utils.network.Network;

@Mixin(value={ClientConnection.class})
public abstract class PacketEventMixin {
    @Inject(method={"send(Lnet/minecraft/network/packet/Packet;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$onPacketSend(Packet<?> packet, CallbackInfo ci) {
        PacketEvent unified;
        PacketSendEvent sendEvent;
        EventBus bus = EventBus.get();
        if (bus.hasListeners(PacketSendEvent.class) && (sendEvent = bus.post(new PacketSendEvent(packet))).isCancelled()) {
            ci.cancel();
            return;
        }
        if (bus.hasListeners(PacketEvent.class) && (unified = bus.post(new PacketEvent(packet, PacketEvent.Direction.SEND))).isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/packet/Packet;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$onPacketReceive(ChannelHandlerContext ctx, Packet<?> packet, CallbackInfo ci) {
        PacketEvent unified;
        PacketReceiveEvent receiveEvent;
        Network.handlePacket(packet);
        EventBus bus = EventBus.get();
        if (bus.hasListeners(PacketReceiveEvent.class) && (receiveEvent = bus.post(new PacketReceiveEvent(packet))).isCancelled()) {
            ci.cancel();
            return;
        }
        if (bus.hasListeners(PacketEvent.class) && (unified = bus.post(new PacketEvent(packet, PacketEvent.Direction.RECEIVE))).isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"addHandlers"}, at={@At(value="HEAD")}, require=0)
    private static void kimiko$raiseReadTimeout(ChannelPipeline pipeline, NetworkSide flow, boolean memoryConnection, PacketSizeLogger monitor, CallbackInfo ci) {
        try {
            if (flow != NetworkSide.CLIENTBOUND || pipeline.get("timeout") == null) {
                return;
            }
            pipeline.replace("timeout", "timeout", (ChannelHandler)new ReadTimeoutHandler(60));
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @Inject(method={"exceptionCaught"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$logNetworkFailure(ChannelHandlerContext ctx, Throwable throwable, CallbackInfo ci) {
        Network.logConnectionFailure(throwable);
    }

    @Inject(method="disconnect(Lnet/minecraft/network/DisconnectionInfo;)V", at=@At(value="HEAD"), require=0)
    private void kimiko$logDisconnect(DisconnectionInfo details, CallbackInfo ci) {
        Network.logDisconnect(details == null || details.reason() == null ? null : details.reason().getString());
    }
}

