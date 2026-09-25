/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.time;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\b\u0016\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\bJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\u0003J\r\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\f\u00a2\u0006\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f8\u0006@BX\u0086\u000e\u00a2\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u000e\u00a8\u0006\u0016"}, d2={"Lrtx/kimiko/utils/time/StopWatch;", "", "<init>", "()V", "", "delay", "", "finished", "(D)Z", "every", "", "reset", "", "elapsedTime", "()J", "ms", "setMs", "(J)Lrtx/kimiko/utils/time/StopWatch;", "value", "startTime", "J", "getStartTime", "rtx.kimiko:kimiko"})
public class StopWatch {
    private long startTime;

    public StopWatch() {
        this.reset();
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final boolean finished(double delay) {
        return (double)System.currentTimeMillis() - delay >= (double)this.startTime;
    }

    public final boolean every(double delay) {
        boolean finished = this.finished(delay);
        if (finished) {
            this.reset();
        }
        return finished;
    }

    public final void reset() {
        this.startTime = System.currentTimeMillis();
    }

    public final long elapsedTime() {
        return System.currentTimeMillis() - this.startTime;
    }

    @NotNull
    public final StopWatch setMs(long ms) {
        this.startTime = System.currentTimeMillis() - ms;
        return this;
    }
}

