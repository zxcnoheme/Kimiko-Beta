/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.animations;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.animations.Animation;
import rtx.kimiko.utils.animations.Decelerate;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0006\u00a2\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0013R\u0016\u0010\r\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\r\u0010\u0013R\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/utils/animations/DecelerateValue;", "", "", "ms", "<init>", "(I)V", "", "target", "update", "(F)F", "", "snap", "(F)V", "current", "()F", "Lrtx/kimiko/utils/animations/Decelerate;", "anim", "Lrtx/kimiko/utils/animations/Decelerate;", "from", "F", "to", "", "initialised", "Z", "rtx.kimiko:kimiko"})
public final class DecelerateValue {
    @NotNull
    private final Decelerate anim;
    private float from;
    private float to;
    private float current;
    private boolean initialised;

    /*
     * WARNING - void declaration
     */
    public DecelerateValue(int ms) {
        Decelerate decelerate;
        Animation animation = new Decelerate().setMs(ms).setValue(1.0);
        Intrinsics.checkNotNull((Object)animation, (String)"null cannot be cast to non-null type rtx.kimiko.utils.animations.Decelerate");
        decelerate = (Decelerate)animation;
        decelerate.counter.setTime(System.currentTimeMillis() - 10000L);
        this.anim = decelerate;
    }

    public final float update(float target) {
        if (!this.initialised) {
            this.snap(target);
            return this.current;
        }
        if (!(target == this.to)) {
            this.from = this.current;
            this.to = target;
            this.anim.reset();
        }
        Double d = this.anim.getOutput();
        this.current = this.from + (this.to - this.from) * (float)(d != null ? d : 0.0);
        return this.current;
    }

    public final void snap(float target) {
        this.initialised = true;
        this.from = target;
        this.to = target;
        this.current = target;
        this.anim.counter.setTime(System.currentTimeMillis() - 10000L);
    }

    public final float current() {
        return this.current;
    }
}

