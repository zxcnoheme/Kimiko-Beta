/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.others;

import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u001aB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J+\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\tH\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u0012\u0010\u0003R\u0014\u0010\u0013\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R$\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/utils/render/others/ScreenShake;", "", "<init>", "()V", "", "amplitudeDeg", "", "durationMs", "frequencyHz", "", "Lkotlin/jvm/JvmStatic;", "add", "(FIF)V", "", "out", "", "sample", "([F)Z", "clear", "MAX_IMPULSES", "I", "Ljava/util/ArrayList;", "Lrtx/kimiko/utils/render/others/ScreenShake$Impulse;", "Lkotlin/collections/ArrayList;", "impulses", "Ljava/util/ArrayList;", "Impulse", "rtx.kimiko:kimiko"})
public final class ScreenShake {
    @NotNull
    public static final ScreenShake INSTANCE = new ScreenShake();
    private static final int MAX_IMPULSES = 8;
    @NotNull
    private static final ArrayList<Impulse> impulses = new ArrayList();

    private ScreenShake() {
    }

    @JvmStatic
    public static final synchronized void add(float amplitudeDeg, int durationMs, float frequencyHz) {
        if (amplitudeDeg <= 0.0f || durationMs <= 0) {
            return;
        }
        if (impulses.size() >= 8) {
            impulses.remove(0);
        }
        impulses.add(new Impulse(System.currentTimeMillis(), durationMs, amplitudeDeg, frequencyHz));
    }

    @JvmStatic
    public static final synchronized boolean sample(@NotNull float[] out) {
        Intrinsics.checkNotNullParameter((Object)out, (String)"out");
        out[0] = 0.0f;
        out[1] = 0.0f;
        if (impulses.isEmpty()) {
            return false;
        }
        long now = System.currentTimeMillis();
        boolean any = false;
        for (int i = impulses.size() - 1; i >= 0; i--) {
            Impulse impulse = impulses.get(i);
            float t = (float)(now - impulse.getStart()) / (float)impulse.getDurationMs();
            if (t >= 1.0f) {
                impulses.remove(i);
                continue;
            }
            float decay = (1.0f - t) * (1.0f - t);
            float ang = t * (float)impulse.getDurationMs() * 0.001f * impulse.getFrequencyHz() * ((float)Math.PI * 2);
            float phase = (float)(impulse.getStart() % 1000L) * 0.006283f;
            float yaw = (float)(Math.sin(ang + phase) * 0.7 + Math.sin((double)ang * 2.3 + (double)phase * 1.7) * 0.3);
            float pitch = (float)(Math.cos((double)ang * 1.13 + (double)phase * 0.6) * 0.7 + Math.sin((double)ang * 1.71 + (double)phase * 2.3) * 0.3);
            out[0] = out[0] + impulse.getAmplitude() * decay * yaw;
            out[1] = out[1] + impulse.getAmplitude() * decay * pitch * 0.8f;
            any = true;
        }
        return any;
    }

    @JvmStatic
    public static final synchronized void clear() {
        impulses.clear();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0010J8\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0018\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0018\u0010\u000eJ\u0011\u0010\u001a\u001a\u00020\u0019H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010\u000eR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010 \u001a\u0004\b!\u0010\u0010R\u0017\u0010\b\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\b\u0010 \u001a\u0004\b\"\u0010\u0010\u00a8\u0006#"}, d2={"Lrtx/kimiko/utils/render/others/ScreenShake$Impulse;", "", "", "start", "", "durationMs", "", "amplitude", "frequencyHz", "<init>", "(JIFF)V", "component1", "()J", "component2", "()I", "component3", "()F", "component4", "copy", "(JIFF)Lrtx/kimiko/utils/render/others/ScreenShake$Impulse;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "J", "getStart", "I", "getDurationMs", "F", "getAmplitude", "getFrequencyHz", "rtx.kimiko:kimiko"})
    private static final class Impulse {
        private final long start;
        private final int durationMs;
        private final float amplitude;
        private final float frequencyHz;

        public Impulse(long start, int durationMs, float amplitude, float frequencyHz) {
            this.start = start;
            this.durationMs = durationMs;
            this.amplitude = amplitude;
            this.frequencyHz = frequencyHz;
        }

        public final long getStart() {
            return this.start;
        }

        public final int getDurationMs() {
            return this.durationMs;
        }

        public final float getAmplitude() {
            return this.amplitude;
        }

        public final float getFrequencyHz() {
            return this.frequencyHz;
        }

        public final long component1() {
            return this.start;
        }

        public final int component2() {
            return this.durationMs;
        }

        public final float component3() {
            return this.amplitude;
        }

        public final float component4() {
            return this.frequencyHz;
        }

        @NotNull
        public final Impulse copy(long start, int durationMs, float amplitude, float frequencyHz) {
            return new Impulse(start, durationMs, amplitude, frequencyHz);
        }

        public static /* synthetic */ Impulse copy$default(Impulse impulse, long l, int n, float f, float f2, int n2, Object object) {
            if ((n2 & 1) != 0) {
                l = impulse.start;
            }
            if ((n2 & 2) != 0) {
                n = impulse.durationMs;
            }
            if ((n2 & 4) != 0) {
                f = impulse.amplitude;
            }
            if ((n2 & 8) != 0) {
                f2 = impulse.frequencyHz;
            }
            return impulse.copy(l, n, f, f2);
        }

        @NotNull
        public String toString() {
            return "Impulse(start=" + this.start + ", durationMs=" + this.durationMs + ", amplitude=" + this.amplitude + ", frequencyHz=" + this.frequencyHz + ")";
        }

        public int hashCode() {
            int result = Long.hashCode(this.start);
            result = result * 31 + Integer.hashCode(this.durationMs);
            result = result * 31 + Float.hashCode(this.amplitude);
            result = result * 31 + Float.hashCode(this.frequencyHz);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Impulse)) {
                return false;
            }
            Impulse impulse = (Impulse)other;
            if (this.start != impulse.start) {
                return false;
            }
            if (this.durationMs != impulse.durationMs) {
                return false;
            }
            if (Float.compare(this.amplitude, impulse.amplitude) != 0) {
                return false;
            }
            return Float.compare(this.frequencyHz, impulse.frequencyHz) == 0;
        }
    }
}

