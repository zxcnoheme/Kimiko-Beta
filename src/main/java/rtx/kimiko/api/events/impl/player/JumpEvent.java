/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.player.PlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.events.impl.player;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.CancellableEvent;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/events/impl/player/JumpEvent;", "Lrtx/kimiko/api/events/CancellableEvent;", "Lnet/minecraft/PlayerEntity;", "player", "<init>", "(Lnet/minecraft/PlayerEntity;)V", "getPlayer", "()Lnet/minecraft/PlayerEntity;", "Lnet/minecraft/PlayerEntity;", "rtx.kimiko:kimiko"})
public final class JumpEvent
extends CancellableEvent {
    @NotNull
    private final PlayerEntity player;

    public JumpEvent(@NotNull PlayerEntity player) {
        Intrinsics.checkNotNullParameter((Object)player, (String)"player");
        this.player = player;
    }

    @NotNull
    public final PlayerEntity getPlayer() {
        return this.player;
    }
}

