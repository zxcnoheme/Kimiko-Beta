/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 */
package rtx.kimiko.api.events.impl.player;

import kotlin.Metadata;
import rtx.kimiko.api.events.CancellableEvent;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000e\u0010\rJ\r\u0010\u000f\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000f\u0010\rJ\r\u0010\u0010\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0010\u0010\rJ\r\u0010\u0011\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0011\u0010\rJ\r\u0010\u0012\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0012\u0010\rJ\r\u0010\u0013\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0013\u0010\rJ\u0015\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0017J\u0015\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0019\u0010\u0017J\u0015\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0017J\u0015\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001b\u0010\u0017J\u0015\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001c\u0010\u0017J\u0015\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001d\u0010\u0017R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u001eR\u0016\u0010\u0004\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u001eR\u0016\u0010\u0005\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u001eR\u0016\u0010\u0006\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u001eR\u0016\u0010\u0007\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u001eR\u0016\u0010\b\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010\u001eR\u0016\u0010\t\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010\u001e\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/api/events/impl/player/PlayerMoveEvent;", "Lrtx/kimiko/api/events/CancellableEvent;", "", "forward", "backward", "left", "right", "jump", "sprint", "shift", "<init>", "(ZZZZZZZ)V", "isForward", "()Z", "isBackward", "isLeft", "isRight", "isJump", "isSprint", "isShift", "v", "", "setForward", "(Z)V", "setBackward", "setLeft", "setRight", "setJump", "setSprint", "setShift", "Z", "rtx.kimiko:kimiko"})
public final class PlayerMoveEvent
extends CancellableEvent {
    private boolean forward;
    private boolean backward;
    private boolean left;
    private boolean right;
    private boolean jump;
    private boolean sprint;
    private boolean shift;

    public PlayerMoveEvent(boolean forward, boolean backward, boolean left, boolean right, boolean jump, boolean sprint, boolean shift) {
        this.forward = forward;
        this.backward = backward;
        this.left = left;
        this.right = right;
        this.jump = jump;
        this.sprint = sprint;
        this.shift = shift;
    }

    public final boolean isForward() {
        return this.forward;
    }

    public final boolean isBackward() {
        return this.backward;
    }

    public final boolean isLeft() {
        return this.left;
    }

    public final boolean isRight() {
        return this.right;
    }

    public final boolean isJump() {
        return this.jump;
    }

    public final boolean isSprint() {
        return this.sprint;
    }

    public final boolean isShift() {
        return this.shift;
    }

    public final void setForward(boolean v) {
        this.forward = v;
    }

    public final void setBackward(boolean v) {
        this.backward = v;
    }

    public final void setLeft(boolean v) {
        this.left = v;
    }

    public final void setRight(boolean v) {
        this.right = v;
    }

    public final void setJump(boolean v) {
        this.jump = v;
    }

    public final void setSprint(boolean v) {
        this.sprint = v;
    }

    public final void setShift(boolean v) {
        this.shift = v;
    }
}

