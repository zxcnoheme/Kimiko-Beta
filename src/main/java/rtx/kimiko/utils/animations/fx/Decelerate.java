/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.animations.fx;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.animations.fx.Direction;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001:\u0001\u001fB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0019\u0010\u001a\u001a\u00020\u00188\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u001cR\u0016\u0010\n\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\n\u0010\u001dR\u0016\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u001e\u00a8\u0006 "}, d2={"Lrtx/kimiko/utils/animations/fx/Decelerate;", "", "<init>", "()V", "", "ms", "", "setMs", "(J)V", "", "value", "setValue", "(D)V", "Lrtx/kimiko/utils/animations/fx/Direction;", "direction", "setDirection", "(Lrtx/kimiko/utils/animations/fx/Direction;)V", "getDirection", "()Lrtx/kimiko/utils/animations/fx/Direction;", "getValue", "()D", "", "isFinished", "()Z", "Lrtx/kimiko/utils/animations/fx/Decelerate$Counter;", "Lkotlin/jvm/JvmField;", "counter", "Lrtx/kimiko/utils/animations/fx/Decelerate$Counter;", "J", "D", "Lrtx/kimiko/utils/animations/fx/Direction;", "Counter", "rtx.kimiko:kimiko"})
public class Decelerate {
    @JvmField
    @NotNull
    public final Counter counter = new Counter();
    private long ms = 200L;
    private double value = 1.0;
    @NotNull
    private Direction direction = Direction.IN;

    public void setMs(long ms) {
        this.ms = ms;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setDirection(@NotNull Direction direction) {
        Intrinsics.checkNotNullParameter((Object)((Object)direction), (String)"direction");
        this.direction = direction;
    }

    @NotNull
    public Direction getDirection() {
        return this.direction;
    }

    public double getValue() {
        double elapsed = this.counter.getElapsed();
        double progress = this.ms > 0L ? Math.min(elapsed / (double)this.ms, 1.0) : 1.0;
        double eased = 1.0 - (1.0 - progress) * (1.0 - progress);
        return this.direction == Direction.OUT ? this.value * (1.0 - eased) : this.value * eased;
    }

    public boolean isFinished() {
        return this.counter.getElapsed() >= this.ms;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\u0004\b\u000b\u0010\u0003R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\f\u00a8\u0006\r"}, d2={"Lrtx/kimiko/utils/animations/fx/Decelerate$Counter;", "", "<init>", "()V", "", "time", "", "setTime", "(J)V", "getElapsed", "()J", "reset", "J", "rtx.kimiko:kimiko"})
    public static final class Counter {
        private long time = System.currentTimeMillis();

        public final void setTime(long time) {
            this.time = time;
        }

        public final long getElapsed() {
            return System.currentTimeMillis() - this.time;
        }

        public final void reset() {
            this.time = System.currentTimeMillis();
        }
    }
}

