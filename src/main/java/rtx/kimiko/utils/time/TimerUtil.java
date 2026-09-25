/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.time;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0016\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\r\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0006\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0006\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0011\u0010\bJ\r\u0010\u0012\u001a\u00020\n\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0014\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0014\u0010\fJ\r\u0010\u0014\u001a\u00020\n\u00a2\u0006\u0004\b\u0014\u0010\u0013R\u0016\u0010\u0015\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/utils/time/TimerUtil;", "", "<init>", "()V", "", "resetCounter", "", "getLastMS", "()J", "time", "", "isReached", "(J)Z", "newValue", "setLastMS", "(J)V", "setTime", "getTime", "isRunning", "()Z", "hasTimeElapsed", "lastMS", "J", "Companion", "rtx.kimiko:kimiko"})
public class TimerUtil {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private long lastMS = System.currentTimeMillis();

    public TimerUtil() {
        this.resetCounter();
    }

    public final void resetCounter() {
        this.lastMS = System.currentTimeMillis();
    }

    public final long getLastMS() {
        return this.lastMS;
    }

    public final boolean isReached(long time) {
        return System.currentTimeMillis() - this.lastMS > time;
    }

    public final void setLastMS(long newValue) {
        this.lastMS = System.currentTimeMillis() + newValue;
    }

    public final void setTime(long time) {
        this.lastMS = time;
    }

    public final long getTime() {
        return System.currentTimeMillis() - this.lastMS;
    }

    public final boolean isRunning() {
        return System.currentTimeMillis() - this.lastMS <= 0L;
    }

    public final boolean hasTimeElapsed(long time) {
        return System.currentTimeMillis() - this.lastMS > time;
    }

    public final boolean hasTimeElapsed() {
        return this.lastMS < System.currentTimeMillis();
    }

    @JvmStatic
    @NotNull
    public static final TimerUtil create() {
        return Companion.create();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/utils/time/TimerUtil.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/time/TimerUtil;", "Lkotlin/jvm/JvmStatic;", "create", "()Lrtx/kimiko/utils/time/TimerUtil;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final TimerUtil create() {
            return new TimerUtil();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

