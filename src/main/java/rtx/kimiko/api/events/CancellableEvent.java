/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 */
package rtx.kimiko.api.events;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import rtx.kimiko.api.events.Event;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\b\u0010\u0003J\u0017\u0010\n\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bR\u001b\u0010\t\u001a\u00020\u00048\u0004@\u0004X\u0085\u000e\u0092\u0002\u0002\b\f\u00a2\u0006\u0006\n\u0004\b\t\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/events/CancellableEvent;", "Lrtx/kimiko/api/events/Event;", "<init>", "()V", "", "isCancelled", "()Z", "", "cancel", "cancelled", "setCancelled", "(Z)V", "Lkotlin/jvm/JvmField;", "Z", "rtx.kimiko:kimiko"})
public abstract class CancellableEvent
extends Event {
    @JvmField
    protected boolean cancelled;

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void cancel() {
        this.cancelled = true;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}

