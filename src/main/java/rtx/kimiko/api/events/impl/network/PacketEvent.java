/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.network.packet.Packet
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.events.impl.network;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.network.packet.Packet;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.CancellableEvent;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0019B\u001b\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0019\u0010\u000b\u001a\u00020\n2\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0004\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0012\u0010\u0011J!\u0010\u0015\u001a\u00020\u000f2\u0012\u0010\u0014\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00020\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0017R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0018\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/events/impl/network/PacketEvent;", "Lrtx/kimiko/api/events/CancellableEvent;", "Lnet/minecraft/Packet;", "packet", "Lrtx/kimiko/api/events/impl/network/PacketEvent$Direction;", "direction", "<init>", "(Lnet/minecraft/Packet;Lrtx/kimiko/api/events/impl/network/PacketEvent$Direction;)V", "getPacket", "()Lnet/minecraft/Packet;", "", "setPacket", "(Lnet/minecraft/Packet;)V", "getDirection", "()Lrtx/kimiko/api/events/impl/network/PacketEvent$Direction;", "", "isSend", "()Z", "isReceive", "Ljava/lang/Class;", "type", "is", "(Ljava/lang/Class;)Z", "Lnet/minecraft/Packet;", "Lrtx/kimiko/api/events/impl/network/PacketEvent$Direction;", "Direction", "rtx.kimiko:kimiko"})
public final class PacketEvent
extends CancellableEvent {
    @NotNull
    private Packet<?> packet;
    @NotNull
    private final Direction direction;

    public PacketEvent(@NotNull Packet<?> packet, @NotNull Direction direction) {
        Intrinsics.checkNotNullParameter(packet, (String)"packet");
        Intrinsics.checkNotNullParameter((Object)((Object)direction), (String)"direction");
        this.packet = packet;
        this.direction = direction;
    }

    @NotNull
    public final Packet<?> getPacket() {
        return this.packet;
    }

    public final void setPacket(@NotNull Packet<?> packet) {
        Intrinsics.checkNotNullParameter(packet, (String)"packet");
        this.packet = packet;
    }

    @NotNull
    public final Direction getDirection() {
        return this.direction;
    }

    public final boolean isSend() {
        return this.direction == Direction.SEND;
    }

    public final boolean isReceive() {
        return this.direction == Direction.RECEIVE;
    }

    public final boolean is(@NotNull Class<? extends Packet<?>> type) {
        Intrinsics.checkNotNullParameter(type, (String)"type");
        return type.isInstance(this.packet);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2={"Lrtx/kimiko/api/events/impl/network/PacketEvent$Direction;", "", "<init>", "(Ljava/lang/String;I)V", "SEND", "RECEIVE", "rtx.kimiko:kimiko"})
    public static enum Direction {
        SEND,
        RECEIVE;

        @NotNull
        public static EnumEntries<Direction> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

        
    }
}

