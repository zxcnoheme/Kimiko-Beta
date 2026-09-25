/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.ranges.RangesKt
 */
package rtx.kimiko.api.drags;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.ranges.RangesKt;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\b\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\r\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\r\u0010\fJ\u0015\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0012\u0010\tJ\u0015\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0013\u0010\fR\u001b\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0014\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0015R\u001b\u0010\u0004\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0014\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u0015R\u001b\u0010\u0005\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0014\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0015R\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/api/drags/DragLerpAnim;", "", "", "anim", "to", "speed", "<init>", "(FFF)V", "getAnim", "()F", "", "setAnim", "(F)V", "setTo", "", "value", "setToAsBoolean", "(Z)V", "getTo", "setSpeed", "Lkotlin/jvm/JvmField;", "F", "", "lastTickNs", "J", "rtx.kimiko:kimiko"})
public final class DragLerpAnim {
    @JvmField
    public float anim;
    @JvmField
    public float to;
    @JvmField
    public float speed;
    private long lastTickNs;

    public DragLerpAnim(float anim, float to, float speed) {
        this.anim = anim;
        this.to = to;
        this.speed = speed;
        this.lastTickNs = System.nanoTime();
    }

    public final float getAnim() {
        long now = System.nanoTime();
        float dt = Math.min((float)(now - this.lastTickNs) / 1.0E9f, 0.05f);
        this.lastTickNs = now;
        float step = RangesKt.coerceIn((float)(this.speed * 2.0f), (float)0.0f, (float)0.999f);
        float blend = 1.0f - (float)Math.pow(1.0f - step, dt * 100.0f);
        this.anim += (this.to - this.anim) * blend;
        return this.anim;
    }

    public final void setAnim(float anim) {
        this.anim = anim;
        this.lastTickNs = System.nanoTime();
    }

    public final void setTo(float to) {
        this.to = to;
    }

    public final void setToAsBoolean(boolean value) {
        this.to = value ? 1.0f : 0.0f;
    }

    public final float getTo() {
        return this.to;
    }

    public final void setSpeed(float speed) {
        this.speed = speed;
    }
}

