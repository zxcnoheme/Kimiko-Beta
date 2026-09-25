/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 */
package rtx.kimiko.api.events.impl.input;

import kotlin.Metadata;
import rtx.kimiko.api.events.CancellableEvent;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\u0005R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/api/events/impl/input/HotBarScrollEvent;", "Lrtx/kimiko/api/events/CancellableEvent;", "", "vertical", "<init>", "(D)V", "getVertical", "()D", "", "setVertical", "D", "rtx.kimiko:kimiko"})
public final class HotBarScrollEvent
extends CancellableEvent {
    private double vertical;

    public HotBarScrollEvent(double vertical) {
        this.vertical = vertical;
    }

    public final double getVertical() {
        return this.vertical;
    }

    public final void setVertical(double vertical) {
        this.vertical = vertical;
    }
}

