/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmOverloads
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.animations;

import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.animations.Easing;
import rtx.kimiko.utils.animations.Easings;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0007\n\u0002\b\u000e\b\u0016\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0007\u00a2\u0006\u0004\b\n\u0010\tJ\r\u0010\u000b\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000b\u0010\tJ\r\u0010\f\u001a\u00020\u0007\u00a2\u0006\u0004\b\f\u0010\tJ\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0011\u0010\tJ\r\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0013\u0010\u0014J7\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00122\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0017b\u0002\b\u001a\u00a2\u0006\u0004\b\u001b\u0010\u001cJ'\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0018H\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u0018H\u0016\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u0018H\u0016\u00a2\u0006\u0004\b \u0010\u001fJ\u000f\u0010!\u001a\u00020\u0018H\u0016\u00a2\u0006\u0004\b!\u0010\u001fJ\u000f\u0010\"\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\"\u0010\tJ\u001f\u0010#\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b#\u0010$J'\u0010(\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b(\u0010)J\u000f\u0010+\u001a\u00020*H\u0016\u00a2\u0006\u0004\b+\u0010,J\u000f\u0010-\u001a\u00020*H\u0016\u00a2\u0006\u0004\b-\u0010,J\u0017\u0010.\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b.\u0010\u0010R\u0016\u0010%\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010/R\u0016\u00100\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0016\u00102\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u00101R\u0016\u00103\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u00101R\u0016\u0010\r\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\r\u00101R\u0016\u00104\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00101R\u0016\u0010\u0017\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u00105R\u0016\u00106\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u00107\u00a8\u00068"}, d2={"Lrtx/kimiko/utils/animations/SmoothAnimation;", "", "<init>", "()V", "", "getStart", "()J", "", "getDuration", "()D", "getFromValue", "getToValue", "getValue", "value", "", "setValue", "(D)V", "getPrevValue", "Lrtx/kimiko/utils/animations/Easing;", "getEasing", "()Lrtx/kimiko/utils/animations/Easing;", "valueTo", "durationSeconds", "easing", "", "safe", "Lkotlin/jvm/JvmOverloads;", "run", "(DDLrtx/kimiko/utils/animations/Easing;Z)Lrtx/kimiko/utils/animations/SmoothAnimation;", "(DDZ)Lrtx/kimiko/utils/animations/SmoothAnimation;", "update", "()Z", "isAlive", "isFinished", "calculatePart", "check", "(ZD)Z", "start", "end", "pct", "interpolate", "(DDD)D", "", "get", "()F", "getPrev", "set", "J", "duration", "D", "fromValue", "toValue", "prevValue", "Lrtx/kimiko/utils/animations/Easing;", "finished", "Z", "rtx.kimiko:kimiko"})
public class SmoothAnimation {
    private long start;
    private double duration;
    private double fromValue;
    private double toValue;
    private double value;
    private double prevValue;
    @NotNull
    private Easing easing = Easings.EXPO_OUT;
    private boolean finished;

    public final long getStart() {
        return this.start;
    }

    public final double getDuration() {
        return this.duration;
    }

    public final double getFromValue() {
        return this.fromValue;
    }

    public final double getToValue() {
        return this.toValue;
    }

    public final double getValue() {
        return this.value;
    }

    public final void setValue(double value) {
        this.value = value;
    }

    public final double getPrevValue() {
        return this.prevValue;
    }

    @NotNull
    public final Easing getEasing() {
        return this.easing;
    }

    @JvmOverloads
    @NotNull
    public SmoothAnimation run(double valueTo, double durationSeconds, @NotNull Easing easing, boolean safe) {
        Intrinsics.checkNotNullParameter((Object)easing, (String)"easing");
        if (this.check(safe, valueTo)) {
            return this;
        }
        this.easing = easing;
        this.start = System.currentTimeMillis();
        this.duration = durationSeconds * 1000.0;
        this.fromValue = this.value;
        this.toValue = valueTo;
        this.finished = this.fromValue == this.toValue;
        return this;
    }

    public static /* synthetic */ SmoothAnimation run$default(SmoothAnimation smoothAnimation, double d, double d2, Easing easing, boolean bl, int n, Object object) {
        if (object != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: run");
        }
        if ((n & 4) != 0) {
            easing = Easings.EXPO_OUT;
        }
        if ((n & 8) != 0) {
            bl = false;
        }
        return smoothAnimation.run(d, d2, easing, bl);
    }

    @NotNull
    public SmoothAnimation run(double valueTo, double durationSeconds, boolean safe) {
        return this.run(valueTo, durationSeconds, Easings.EXPO_OUT, safe);
    }

    public boolean update() {
        this.prevValue = this.value;
        boolean alive = this.isAlive();
        if ((double)(System.currentTimeMillis() - this.start) > this.duration / 1.5) {
            boolean bl = this.finished = this.fromValue == this.toValue;
        }
        if (alive) {
            double part = RangesKt.coerceIn((double)this.calculatePart(), (double)0.0, (double)1.0);
            this.value = this.interpolate(this.fromValue, this.toValue, this.easing.ease(part));
        } else {
            this.start = 0L;
            this.value = this.toValue;
        }
        return alive;
    }

    public boolean isAlive() {
        return !this.isFinished();
    }

    public boolean isFinished() {
        return this.calculatePart() >= 1.0;
    }

    public double calculatePart() {
        if (this.duration <= 0.0) {
            return 1.0;
        }
        return (double)(System.currentTimeMillis() - this.start) / this.duration;
    }

    public boolean check(boolean safe, double valueTo) {
        return safe && this.isAlive() && (valueTo == this.fromValue || valueTo == this.toValue || valueTo == this.value);
    }

    public double interpolate(double start, double end, double pct) {
        return start + (end - start) * pct;
    }

    public float get() {
        return (float)this.value;
    }

    public float getPrev() {
        return (float)this.prevValue;
    }

    public void set(double value) {
        SmoothAnimation.run$default(this, value, 1.0E-4, null, false, 12, null);
        this.update();
        this.value = value;
    }

    @JvmOverloads
    @NotNull
    public final SmoothAnimation run(double valueTo, double durationSeconds, @NotNull Easing easing) {
        Intrinsics.checkNotNullParameter((Object)easing, (String)"easing");
        return SmoothAnimation.run$default(this, valueTo, durationSeconds, easing, false, 8, null);
    }

    @JvmOverloads
    @NotNull
    public final SmoothAnimation run(double valueTo, double durationSeconds) {
        return SmoothAnimation.run$default(this, valueTo, durationSeconds, null, false, 12, null);
    }
}

