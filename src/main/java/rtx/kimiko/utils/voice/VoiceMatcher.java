/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.voice;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001 B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J3\u0010\n\u001a\u00020\b2\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ;\u0010\u0011\u001a\u00020\u00102\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u000f\u001a\u00020\u000eH\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u0011\u0010\u0012J3\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u000eH\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018R\u0019\u0010\u001a\u001a\u00020\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001bR\u0014\u0010\u001e\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001bR\u0014\u0010\u001f\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001b\u00a8\u0006!"}, d2={"Lrtx/kimiko/utils/voice/VoiceMatcher;", "", "<init>", "()V", "", "", "a", "b", "", "Lkotlin/jvm/JvmStatic;", "distance", "([[F[[F)F", "template", "test", "", "maxStart", "Lrtx/kimiko/utils/voice/VoiceMatcher$Prefix;", "matchPrefix", "([[F[[FI)Lrtx/kimiko/utils/voice/VoiceMatcher$Prefix;", "frames", "from", "tail", "([[FI)[[F", "cost", "([F[F)F", "Lkotlin/jvm/JvmField;", "INF", "F", "MAX_LENGTH_RATIO", "MIN_SPAN_RATIO", "MAX_SPAN_RATIO", "MIN_TEMPLATE_RATIO", "Prefix", "rtx.kimiko:kimiko"})
public final class VoiceMatcher {
    @NotNull
    public static final VoiceMatcher INSTANCE = new VoiceMatcher();
    @JvmField
    public static final float INF = 8.5070587E37f;
    private static final float MAX_LENGTH_RATIO = 1.8f;
    private static final float MIN_SPAN_RATIO = 0.55f;
    private static final float MAX_SPAN_RATIO = 1.8f;
    private static final float MIN_TEMPLATE_RATIO = 0.85f;

    private VoiceMatcher() {
    }

    @JvmStatic
    public static final float distance(@Nullable float[][] a, @Nullable float[][] b) {
        float total;
        if (a == null || b == null || ((Object[])a).length == 0 || ((Object[])b).length == 0) {
            return INF;
        }
        int n = ((Object[])a).length;
        int m = ((Object[])b).length;
        if ((float)n > (float)m * 1.8f || (float)m > (float)n * 1.8f) {
            return INF;
        }
        int band = Math.max(20, Math.max(n, m) / 2);
        float[] prev = new float[m + 1];
        float[] cur = new float[m + 1];
        Arrays.fill(prev, INF);
        prev[0] = 0.0f;
        int i = 1;
        if (i <= n) {
            while (true) {
                Arrays.fill(cur, INF);
                int lo = Math.max(1, i - band);
                int hi = Math.min(m, i + band);
                int j = lo;
                if (j <= hi) {
                    while (true) {
                        float best;
                        if (prev[j] < (best = prev[j - 1])) {
                            best = prev[j];
                        }
                        if (cur[j - 1] < best) {
                            best = cur[j - 1];
                        }
                        if (!(best >= INF)) {
                            cur[j] = best + INSTANCE.cost(a[i - 1], b[j - 1]);
                        }
                        if (j == hi) break;
                        ++j;
                    }
                }
                float[] swap = prev;
                prev = cur;
                cur = swap;
                if (i == n) break;
                ++i;
            }
        }
        return (total = prev[m]) >= INF ? INF : total / (float)(n + m);
    }

    @JvmStatic
    @NotNull
    public static final Prefix matchPrefix(@Nullable float[][] template, @Nullable float[][] test, int maxStart) {
        if (template == null || test == null || ((Object[])template).length == 0 || ((Object[])test).length == 0) {
            return new Prefix(INF, 0, 0);
        }
        int n = ((Object[])template).length;
        int m = ((Object[])test).length;
        float[] prev = new float[m + 1];
        float[] cur = new float[m + 1];
        int[] prevStart = new int[m + 1];
        int[] curStart = new int[m + 1];
        Arrays.fill(prev, INF);
        float best = INF;
        int bestStart = 0;
        int bestEnd = 0;
        int firstRow = Math.max(1, (int)Math.ceil((double)n * (double)0.85f));
        int i = 1;
        if (i <= n) {
            while (true) {
                Arrays.fill(cur, INF);
                int j = 1;
                if (j <= m) {
                    while (true) {
                        if (i == 1) {
                            if (j - 1 <= maxStart) {
                                cur[j] = INSTANCE.cost(template[0], test[j - 1]);
                                curStart[j] = j - 1;
                            }
                        } else {
                            float step = prev[j - 1];
                            int start = prevStart[j - 1];
                            if (prev[j] < step) {
                                step = prev[j];
                                start = prevStart[j];
                            }
                            if (cur[j - 1] < step) {
                                step = cur[j - 1];
                                start = curStart[j - 1];
                            }
                            if (!(step >= INF)) {
                                cur[j] = step + INSTANCE.cost(template[i - 1], test[j - 1]);
                                curStart[j] = start;
                            }
                        }
                        if (j == m) break;
                        ++j;
                    }
                }
                float[] swapF = prev;
                prev = cur;
                cur = swapF;
                int[] swapI = prevStart;
                prevStart = curStart;
                curStart = swapI;
                if (i >= firstRow) {
                    float minSpan = (float)i * 0.55f;
                    float maxSpan = (float)i * 1.8f;
                    int j2 = 1;
                    if (j2 <= m) {
                        while (true) {
                            float norm;
                            int span;
                            if (!(prev[j2] >= INF) && !((float)(span = j2 - prevStart[j2]) < minSpan) && !((float)span > maxSpan) && (norm = prev[j2] / (float)(i + span)) < best) {
                                best = norm;
                                bestStart = prevStart[j2];
                                bestEnd = j2;
                            }
                            if (j2 == m) break;
                            ++j2;
                        }
                    }
                }
                if (i == n) break;
                ++i;
            }
        }
        return new Prefix(best, bestStart, bestEnd);
    }

    @JvmStatic
    @Nullable
    public static final float[][] tail(@Nullable float[][] frames, int from) {
        if (frames == null || from >= ((Object[])frames).length) {
            return null;
        }
        int start = Math.max(0, from);
        int len = ((Object[])frames).length - start;
        if (len <= 0) {
            return null;
        }
        float[][] out = new float[len][];
        System.arraycopy(frames, start, out, 0, len);
        return out;
    }

    private final float cost(float[] a, float[] b) {
        float acc = 0.0f;
        int n = Math.min(a.length, b.length);
        for (int i = 0; i < n; ++i) {
            float d = a[i] - b[i];
            acc += d * d;
        }
        return (float)Math.sqrt(acc / (float)n);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B!\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\t\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\nR\u0019\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\t\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u000bR\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\t\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u000b\u00a8\u0006\f"}, d2={"Lrtx/kimiko/utils/voice/VoiceMatcher$Prefix;", "", "", "distance", "", "startFrame", "endFrame", "<init>", "(FII)V", "Lkotlin/jvm/JvmField;", "F", "I", "rtx.kimiko:kimiko"})
    public static final class Prefix {
        @JvmField
        public final float distance;
        @JvmField
        public final int startFrame;
        @JvmField
        public final int endFrame;

        public Prefix(float distance, int startFrame, int endFrame) {
            this.distance = distance;
            this.startFrame = startFrame;
            this.endFrame = endFrame;
        }
    }
}

