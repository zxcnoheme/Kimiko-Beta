/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmOverloads
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.events.impl.player;

import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.CancellableEvent;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u001a\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\u000b\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\rR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u000e\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;", "Lrtx/kimiko/api/events/CancellableEvent;", "Lnet/minecraft/Entity;", "target", "", "synthetic", "Lkotlin/jvm/JvmOverloads;", "<init>", "(Lnet/minecraft/Entity;Z)V", "getTarget", "()Lnet/minecraft/Entity;", "isSynthetic", "()Z", "Lnet/minecraft/Entity;", "Z", "rtx.kimiko:kimiko"})
public final class AttackEntityEvent
extends CancellableEvent {
    @NotNull
    private final Entity target;
    private final boolean synthetic;

    @JvmOverloads
    public AttackEntityEvent(@NotNull Entity target, boolean synthetic) {
        Intrinsics.checkNotNullParameter((Object)target, (String)"target");
        this.target = target;
        this.synthetic = synthetic;
    }

    public /* synthetic */ AttackEntityEvent(Entity entity2, boolean bl, int n, DefaultConstructorMarker defaultConstructorMarker) {
        this(entity2, ((n & 2) != 0 ? false : bl));
    }

    @NotNull
    public final Entity getTarget() {
        return this.target;
    }

    public final boolean isSynthetic() {
        return this.synthetic;
    }

    @JvmOverloads
    public AttackEntityEvent(@NotNull Entity target) {
        this(target, false, 2, null);
    }
}

