/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.network.packet.Packet
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.events.impl.network;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.network.packet.Packet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.CancellableEvent;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J+\u0010\u000b\u001a\u0004\u0018\u00018\u0000\"\f\b\u0000\u0010\b*\u0006\u0012\u0002\b\u00030\u00022\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t\u00a2\u0006\u0004\b\u000b\u0010\fJ!\u0010\u000e\u001a\u00020\r2\u0012\u0010\n\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00020\t\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/events/impl/network/PacketReceiveEvent;", "Lrtx/kimiko/api/events/CancellableEvent;", "Lnet/minecraft/Packet;", "packet", "<init>", "(Lnet/minecraft/Packet;)V", "getPacket", "()Lnet/minecraft/Packet;", "T", "Ljava/lang/Class;", "type", "getPacketAs", "(Ljava/lang/Class;)Lnet/minecraft/Packet;", "", "is", "(Ljava/lang/Class;)Z", "Lnet/minecraft/Packet;", "rtx.kimiko:kimiko"})
public final class PacketReceiveEvent
extends CancellableEvent {
    @NotNull
    private final Packet<?> packet;

    public PacketReceiveEvent(@NotNull Packet<?> packet) {
        Intrinsics.checkNotNullParameter(packet, (String)"packet");
        this.packet = packet;
    }

    @NotNull
    public final Packet<?> getPacket() {
        return this.packet;
    }

    @Nullable
    public final <T extends Packet<?>> T getPacketAs(@NotNull Class<T> type) {
        Packet<?> packet2;
        Intrinsics.checkNotNullParameter(type, (String)"type");
        if (type.isInstance(this.packet)) {
            Packet<?> packet3 = this.packet;
            packet2 = packet3;
            Intrinsics.checkNotNull(packet3, (String)"null cannot be cast to non-null type T of rtx.kimiko.api.events.impl.network.PacketReceiveEvent.getPacketAs");
        } else {
            packet2 = null;
        }
        return (T)packet2;
    }

    public final boolean is(@NotNull Class<? extends Packet<?>> type) {
        Intrinsics.checkNotNullParameter(type, (String)"type");
        return type.isInstance(this.packet);
    }
}

