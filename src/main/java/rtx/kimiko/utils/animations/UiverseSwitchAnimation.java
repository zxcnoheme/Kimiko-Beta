/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.ranges.RangesKt
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.animations;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u001b\n\u0002\u0010\t\n\u0002\b\u000b\u0018\u0000 12\u00020\u0001:\u00011B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\f\u0010\u0005J\r\u0010\r\u001a\u00020\u0007\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0010\u0010\u000eJ\r\u0010\u0011\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0012J\r\u0010\u0014\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0014\u0010\u000eJ\u000f\u0010\u0015\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J7\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ'\u0010!\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b!\u0010\"J'\u0010#\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b#\u0010\"R\u0016\u0010$\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010&\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010%R\u0016\u0010(\u001a\u00020'8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010%R\u0016\u0010+\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010,R\u0016\u0010-\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010,R\u0016\u0010.\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010,R\u0016\u0010/\u001a\u00020'8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u0010)R\u0016\u0010\r\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\r\u0010,R\u0016\u0010\u0010\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0010\u0010,R\u0016\u0010\u000f\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010,R\u0016\u00100\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u0010,\u00a8\u00062"}, d2={"Lrtx/kimiko/utils/animations/UiverseSwitchAnimation;", "", "", "initial", "<init>", "(Z)V", "target", "", "update", "(Z)F", "value", "", "reset", "translationProgress", "()F", "horizontalScale", "thumbProgress", "isOpening", "()Z", "isAnimating", "travelProgress", "settle", "()V", "time", "x1", "y1", "x2", "y2", "cubicBezier", "(FFFFF)F", "t", "first", "second", "sampleCurve", "(FFF)F", "sampleDerivative", "targetOn", "Z", "opening", "", "startedAtNanos", "J", "active", "colorProgress", "F", "colorFrom", "colorTo", "colorStartedAtNanos", "position", "Companion", "rtx.kimiko:kimiko"})
public final class UiverseSwitchAnimation {
    @NotNull
    private static final Companion Companion = new Companion(null);
    private boolean targetOn;
    private boolean opening;
    private long startedAtNanos;
    private boolean active;
    private float colorProgress;
    private float colorFrom;
    private float colorTo;
    private long colorStartedAtNanos;
    private float translationProgress;
    private float thumbProgress;
    private float horizontalScale;
    private float position;
    @Deprecated
    public static final long DURATION_NS = 200000000L;
    @Deprecated
    public static final float X1 = 0.075f;
    @Deprecated
    public static final float Y1 = 0.82f;
    @Deprecated
    public static final float X2 = 0.165f;
    @Deprecated
    public static final float Y2 = 1.0f;
    @Deprecated
    public static final float COLOR_X1 = 0.25f;
    @Deprecated
    public static final float COLOR_Y1 = 0.1f;
    @Deprecated
    public static final float COLOR_X2 = 0.25f;
    @Deprecated
    public static final float COLOR_Y2 = 1.0f;
    @Deprecated
    public static final float DERIVATIVE_EPSILON = 1.0E-5f;
    @Deprecated
    public static final float SOLVER_EPSILON = 1.0E-5f;

    public UiverseSwitchAnimation(boolean initial) {
        this.targetOn = initial;
        this.opening = initial;
        this.startedAtNanos = System.nanoTime() - 200000000L;
        this.colorFrom = this.colorProgress = initial ? 1.0f : 0.0f;
        this.colorTo = this.colorProgress;
        this.colorStartedAtNanos = this.startedAtNanos;
        this.translationProgress = initial ? 1.0f : 0.0f;
        this.thumbProgress = initial ? 1.0f : 0.0f;
        this.horizontalScale = 1.0f;
        this.position = initial ? 1.0f : 0.0f;
    }

    public /* synthetic */ UiverseSwitchAnimation(boolean bl, int n, DefaultConstructorMarker defaultConstructorMarker) {
        this(((n & 1) != 0 ? false : bl));
    }

    public final float update(boolean target) {
        long now = System.nanoTime();
        if (target != this.targetOn) {
            this.targetOn = target;
            this.opening = target;
            float startRaw = target ? this.position : 1.0f - this.position;
            this.startedAtNanos = now - (long)(startRaw * (float)200000000L);
            this.colorFrom = this.colorProgress;
            this.colorTo = target ? 1.0f : 0.0f;
            this.colorStartedAtNanos = now;
            this.active = true;
        }
        if (!this.active) {
            this.settle();
            return this.colorProgress;
        }
        float raw = RangesKt.coerceIn((float)((float)(now - this.startedAtNanos) / 2.0E8f), (float)0.0f, (float)1.0f);
        this.position = this.opening ? raw : 1.0f - raw;
        float progress = raw;
        float colorRaw = RangesKt.coerceIn((float)((float)(now - this.colorStartedAtNanos) / 2.0E8f), (float)0.0f, (float)1.0f);
        this.colorProgress = this.colorFrom + (this.colorTo - this.colorFrom) * colorRaw;
        float wobble = raw < 0.5f ? raw * 2.0f : (1.0f - raw) * 2.0f;
        this.horizontalScale = 1.0f + wobble * 2.6f;
        this.thumbProgress = this.translationProgress = this.opening ? progress : 1.0f - progress;
        if (raw >= 1.0f) {
            this.active = false;
            this.settle();
        }
        return this.colorProgress;
    }

    public final void reset(boolean value) {
        this.targetOn = value;
        this.opening = value;
        this.active = false;
        this.colorStartedAtNanos = this.startedAtNanos = System.nanoTime() - 200000000L;
        this.settle();
        this.position = value ? 1.0f : 0.0f;
    }

    public final float translationProgress() {
        return this.translationProgress;
    }

    public final float horizontalScale() {
        return this.horizontalScale;
    }

    public final float thumbProgress() {
        return this.thumbProgress;
    }

    public final boolean isOpening() {
        return this.opening;
    }

    public final boolean isAnimating() {
        return this.active;
    }

    public final float travelProgress() {
        return this.translationProgress;
    }

    private final void settle() {
        this.colorFrom = this.colorProgress = this.targetOn ? 1.0f : 0.0f;
        this.colorTo = this.colorProgress;
        this.translationProgress = this.targetOn ? 1.0f : 0.0f;
        this.thumbProgress = this.targetOn ? 1.0f : 0.0f;
        this.horizontalScale = 1.0f;
    }

    private final float cubicBezier(float time, float x1, float y1, float x2, float y2) {
        if (time <= 0.0f) {
            return 0.0f;
        }
        if (time >= 1.0f) {
            return 1.0f;
        }
        float u = time;
        int iteration = 0;
        while (iteration++ < 8) {
            float next;
            float error = this.sampleCurve(u, x1, x2) - time;
            float derivative = this.sampleDerivative(u, x1, x2);
            if (Math.abs(derivative) < 1.0E-5f || (next = u - error / derivative) < 0.0f || next > 1.0f) break;
            u = next;
            if (!(Math.abs(error) < 1.0E-5f)) continue;
            return this.sampleCurve(u, y1, y2);
        }
        float low = 0.0f;
        float high = 1.0f;
        int iterationCount = 0;
        while (iterationCount++ < 16) {
            float middle = (low + high) * 0.5f;
            if (this.sampleCurve(middle, x1, x2) < time) {
                low = middle;
                continue;
            }
            high = middle;
        }
        return this.sampleCurve((low + high) * 0.5f, y1, y2);
    }

    private final float sampleCurve(float t, float first, float second) {
        float inverse = 1.0f - t;
        return 3.0f * inverse * inverse * t * first + 3.0f * inverse * t * t * second + t * t * t;
    }

    private final float sampleDerivative(float t, float first, float second) {
        float inverse = 1.0f - t;
        return 3.0f * inverse * inverse * first + 6.0f * inverse * t * (second - first) + 3.0f * t * t * (1.0f - second);
    }

    public UiverseSwitchAnimation() {
        this(false, 1, null);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\f\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\n\u0010\tR\u0014\u0010\u000b\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\tR\u0014\u0010\f\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\r\u0010\tR\u0014\u0010\u000e\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\tR\u0014\u0010\u000f\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\tR\u0014\u0010\u0010\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\tR\u0014\u0010\u0011\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\tR\u0014\u0010\u0012\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\t\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/utils/animations/UiverseSwitchAnimation.Companion;", "", "<init>", "()V", "", "DURATION_NS", "J", "", "X1", "F", "Y1", "X2", "Y2", "COLOR_X1", "COLOR_Y1", "COLOR_X2", "COLOR_Y2", "DERIVATIVE_EPSILON", "SOLVER_EPSILON", "rtx.kimiko:kimiko"})
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

