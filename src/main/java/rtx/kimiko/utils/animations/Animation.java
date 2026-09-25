/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.animations;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.animations.AnimationCalculation;
import rtx.kimiko.utils.animations.Direction;
import rtx.kimiko.utils.time.TimerUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u0006\u0010\tJ\u0017\u0010\f\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0003J\u000f\u0010\u0010\u001a\u00020\u000eH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0014H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0014H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0014H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0014H\u0016\u00a2\u0006\u0004\b\u001c\u0010\u0017J\u0011\u0010\u001d\u001a\u0004\u0018\u00010\nH\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\nH\u0014\u00a2\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b!\u0010\u0003R\u0019\u0010$\u001a\u00020\"8\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u001b\u0010\u0005\u001a\u00020\u00048\u0004@\u0004X\u0085\u000e\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\u0005\u0010&R\u001b\u0010\u000b\u001a\u00020\n8\u0004@\u0004X\u0085\u000e\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\u000b\u0010'R\u001b\u0010\u0015\u001a\u00020\u00148\u0004@\u0004X\u0085\u000e\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\u0015\u0010(\u00a8\u0006)"}, d2={"Lrtx/kimiko/utils/animations/Animation;", "Lrtx/kimiko/utils/animations/AnimationCalculation;", "<init>", "()V", "", "ms", "setMs", "(I)Lrtx/kimiko/utils/animations/Animation;", "", "(J)Lrtx/kimiko/utils/animations/Animation;", "", "value", "setValue", "(D)Lrtx/kimiko/utils/animations/Animation;", "", "reset", "update", "", "isDone", "()Z", "Lrtx/kimiko/utils/animations/Direction;", "direction", "isFinished", "(Lrtx/kimiko/utils/animations/Direction;)Z", "getDirection", "()Lrtx/kimiko/utils/animations/Direction;", "setDirection", "(Lrtx/kimiko/utils/animations/Direction;)V", "isDirection", "getOutput", "()Ljava/lang/Double;", "endValue", "()D", "adjustTimer", "Lrtx/kimiko/utils/time/TimerUtil;", "Lkotlin/jvm/JvmField;", "counter", "Lrtx/kimiko/utils/time/TimerUtil;", "I", "D", "Lrtx/kimiko/utils/animations/Direction;", "rtx.kimiko:kimiko"})
public class Animation
implements AnimationCalculation {
    @JvmField
    @NotNull
    public final TimerUtil counter = new TimerUtil();
    @JvmField
    protected int ms;
    @JvmField
    protected double value;
    @JvmField
    @NotNull
    protected Direction direction = Direction.FORWARDS;

    @NotNull
    public Animation setMs(int ms) {
        this.ms = ms;
        return this;
    }

    @NotNull
    public Animation setMs(long ms) {
        return this.setMs((int)ms);
    }

    @NotNull
    public Animation setValue(double value) {
        this.value = value;
        return this;
    }

    public void reset() {
        this.counter.resetCounter();
    }

    public void update() {
    }

    public boolean isDone() {
        return this.counter.isReached(this.ms);
    }

    public boolean isFinished(@NotNull Direction direction) {
        Intrinsics.checkNotNullParameter((Object)((Object)direction), (String)"direction");
        return this.direction == direction && this.isDone();
    }

    @NotNull
    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(@NotNull Direction direction) {
        Intrinsics.checkNotNullParameter((Object)((Object)direction), (String)"direction");
        if (this.direction != direction) {
            this.direction = direction;
            this.adjustTimer();
        }
    }

    public boolean isDirection(@NotNull Direction direction) {
        Intrinsics.checkNotNullParameter((Object)((Object)direction), (String)"direction");
        return this.direction == direction;
    }

    @Nullable
    public Double getOutput() {
        double time = (1.0 - this.calculation(this.counter.getTime())) * this.value;
        return this.direction == Direction.FORWARDS ? Double.valueOf(this.endValue()) : Double.valueOf(this.isDone() ? 0.0 : time);
    }

    protected double endValue() {
        return this.isDone() ? this.value : this.calculation(this.counter.getTime()) * this.value;
    }

    private final void adjustTimer() {
        this.counter.setTime(System.currentTimeMillis() - ((long)this.ms - Math.min((long)this.ms, this.counter.getTime())));
    }
}

