/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.math.Vec3d
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.events.impl.player;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.Event;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000e\u0010\nJ\r\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0011R\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u0011R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0012\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/api/events/impl/player/PlayerVelocityStrafeEvent;", "Lrtx/kimiko/api/events/Event;", "Lnet/minecraft/Vec3d;", "velocity", "movementInput", "", "speed", "<init>", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;F)V", "getVelocity", "()Lnet/minecraft/Vec3d;", "", "setVelocity", "(Lnet/minecraft/Vec3d;)V", "getMovementInput", "getSpeed", "()F", "Lnet/minecraft/Vec3d;", "F", "rtx.kimiko:kimiko"})
public final class PlayerVelocityStrafeEvent
extends Event {
    @NotNull
    private Vec3d velocity;
    @NotNull
    private final Vec3d movementInput;
    private final float speed;

    public PlayerVelocityStrafeEvent(@NotNull Vec3d velocity, @NotNull Vec3d movementInput, float speed) {
        Intrinsics.checkNotNullParameter((Object)velocity, (String)"velocity");
        Intrinsics.checkNotNullParameter((Object)movementInput, (String)"movementInput");
        this.velocity = velocity;
        this.movementInput = movementInput;
        this.speed = speed;
    }

    @NotNull
    public final Vec3d getVelocity() {
        return this.velocity;
    }

    public final void setVelocity(@NotNull Vec3d velocity) {
        Intrinsics.checkNotNullParameter((Object)velocity, (String)"velocity");
        this.velocity = velocity;
    }

    @NotNull
    public final Vec3d getMovementInput() {
        return this.movementInput;
    }

    public final float getSpeed() {
        return this.speed;
    }
}

