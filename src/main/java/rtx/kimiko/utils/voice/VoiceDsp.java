/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.voice;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0014\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\b*\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001LB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001f\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\f\u0010\rJ%\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0011J+\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0013\u0010\u0014J#\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\u0006\u0010\u0019\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u001f\u0010$\u001a\u00020#2\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b$\u0010%J#\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002\u00a2\u0006\u0004\b&\u0010\u0014J\u001d\u0010'\u001a\u00020\u001c2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002\u00a2\u0006\u0004\b'\u0010(J\u001f\u0010+\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b+\u0010,J\u0017\u0010.\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b.\u0010/J\u0017\u00101\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b1\u0010/R\u0014\u00102\u001a\u00020\"8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0014\u00104\u001a\u00020\"8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b4\u00103R\u0014\u00105\u001a\u00020\"8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b5\u00103R\u0014\u00106\u001a\u00020\"8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b6\u00103R\u0014\u00107\u001a\u00020\"8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b7\u00103R\u0014\u00108\u001a\u00020\"8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b8\u00103R\u0014\u00109\u001a\u00020\"8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b9\u00103R\u0014\u0010:\u001a\u00020\"8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b:\u00103R\u0014\u0010;\u001a\u00020\"8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b;\u00103R\u0014\u0010<\u001a\u00020\"8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b<\u00103R\u0014\u0010=\u001a\u00020\"8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b=\u00103R\u0014\u0010>\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u0010?R\u0014\u0010A\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bA\u00103R\u0014\u0010B\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010CR\u001a\u0010E\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010FR\u001a\u0010G\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010FR\u0014\u0010H\u001a\u00020#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010IR\u0014\u0010J\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010CR\u0014\u0010K\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bK\u0010C\u00a8\u0006M"}, d2={"Lrtx/kimiko/utils/voice/VoiceDsp;", "", "<init>", "()V", "", "samples", "", "Lkotlin/jvm/JvmStatic;", "rms", "([S)F", "pcm48k", "Lrtx/kimiko/utils/voice/VoiceDsp$Extracted;", "extract", "([S)Lrtx/kimiko/utils/voice/VoiceDsp$Extracted;", "", "", "features", "([S)[[F", "frames", "renormalise", "([[F)[[F", "deepCopy", "pcm", "decimate", "([S)[F", "x", "frameEnergies", "([F)[F", "", "preEmphasis", "([F)V", "cepstra", "([F)[[F", "energy", "", "", "trimRange", "([FI)[I", "withDeltas", "normalise", "([[F)V", "re", "im", "fft", "([F[F)V", "hz", "hzToMel", "(F)F", "mel", "melToHz", "IN_RATE", "I", "RATE", "DECIM", "FRAME", "HOP", "FFT_SIZE", "SPECTRUM", "MEL_FILTERS", "CEPSTRA", "STATIC_DIMS", "DIMS", "MEL_LOW", "F", "MEL_HIGH", "FIR_TAPS", "FIR", "[F", "WINDOW", "MEL_BANK", "[[F", "DCT", "BIT_REV", "[I", "COS_TABLE", "SIN_TABLE", "Extracted", "rtx.kimiko:kimiko"})
public final class VoiceDsp {
    @NotNull
    public static final VoiceDsp INSTANCE;
    public static final int IN_RATE = 48000;
    public static final int RATE = 16000;
    public static final int DECIM = 3;
    public static final int FRAME = 400;
    public static final int HOP = 160;
    public static final int FFT_SIZE = 512;
    public static final int SPECTRUM = 257;
    public static final int MEL_FILTERS = 26;
    public static final int CEPSTRA = 13;
    public static final int STATIC_DIMS = 12;
    public static final int DIMS = 24;
    private static final float MEL_LOW = 80.0f;
    private static final float MEL_HIGH = 7600.0f;
    private static final int FIR_TAPS = 31;
    @NotNull
    private static final float[] FIR;
    @NotNull
    private static final float[] WINDOW;
    @NotNull
    private static final float[][] MEL_BANK;
    @NotNull
    private static final float[][] DCT;
    @NotNull
    private static final int[] BIT_REV;
    @NotNull
    private static final float[] COS_TABLE;
    @NotNull
    private static final float[] SIN_TABLE;

    private VoiceDsp() {
    }

    @JvmStatic
    public static final float rms(@Nullable short[] samples) {
        if (samples == null || samples.length == 0) {
            return 0.0f;
        }
        double acc = 0.0;
        for (short s : samples) {
            double v = (double)s / 32768.0;
            acc += v * v;
        }
        return (float)Math.sqrt(acc / (double)samples.length);
    }

    @JvmStatic
    @Nullable
    public static final Extracted extract(@Nullable short[] pcm48k) {
        if (pcm48k == null || pcm48k.length < 4800) {
            return null;
        }
        float[] x = INSTANCE.decimate(pcm48k);
        float[] energy = INSTANCE.frameEnergies(x);
        if (energy.length == 0) {
            return null;
        }
        INSTANCE.preEmphasis(x);
        float[][] cepstra = INSTANCE.cepstra(x);
        if (cepstra == null || ((Object[])cepstra).length < 6) {
            return null;
        }
        int frames = Math.min(((Object[])cepstra).length, energy.length);
        int[] range = INSTANCE.trimRange(energy, frames);
        int len = range[1] - range[0] + 1;
        if (len < 6) {
            return null;
        }
        float[][] kept = new float[len][];
        float[] keptEnergy = new float[len];
        System.arraycopy(cepstra, range[0], kept, 0, len);
        System.arraycopy(energy, range[0], keptEnergy, 0, len);
        float[][] out = INSTANCE.withDeltas(kept);
        return new Extracted(out, keptEnergy);
    }

    @JvmStatic
    @Nullable
    public static final float[][] features(@Nullable short[] pcm48k) {
        Extracted extracted = VoiceDsp.extract(pcm48k);
        if (extracted == null) {
            return null;
        }
        Extracted extracted2 = extracted;
        float[][] copy = INSTANCE.deepCopy(extracted2.frames);
        INSTANCE.normalise(copy);
        return copy;
    }

    @JvmStatic
    @Nullable
    public static final float[][] renormalise(@Nullable float[][] frames) {
        if (frames == null || ((Object[])frames).length == 0) {
            return frames;
        }
        float[][] copy = INSTANCE.deepCopy(frames);
        INSTANCE.normalise(copy);
        return copy;
    }

    private final float[][] deepCopy(float[][] frames) {
        int n = 0;
        int n2 = ((Object[])frames).length;
        float[][] fArrayArray = new float[n2][];
        while (n < n2) {
            int n3 = n++;
            fArrayArray[n3] = (float[])frames[n3].clone();
        }
        return fArrayArray;
    }

    private final float[] decimate(short[] pcm) {
        int outLen = pcm.length / 3;
        float[] out = new float[outLen];
        int mid = 15;
        for (int i = 0; i < outLen; ++i) {
            int centre = i * 3;
            float acc = 0.0f;
            for (int t = 0; t < 31; ++t) {
                int idx = centre + t - mid;
                if (idx < 0 || idx >= pcm.length) continue;
                acc += FIR[t] * ((float)pcm[idx] / 32768.0f);
            }
            out[i] = acc;
        }
        return out;
    }

    private final float[] frameEnergies(float[] x) {
        int frames = (x.length - 400) / 160 + 1;
        if (frames <= 0) {
            return new float[0];
        }
        float[] out = new float[frames];
        for (int f = 0; f < frames; ++f) {
            int start = f * 160;
            float acc = 0.0f;
            for (int i = 0; i < 400 && start + i < x.length; ++i) {
                acc += x[start + i] * x[start + i];
            }
            out[f] = acc;
        }
        return out;
    }

    private final void preEmphasis(float[] x) {
        for (int i = x.length - 1; 0 < i; --i) {
            int n = i;
            x[n] = x[n] - 0.97f * x[i - 1];
        }
    }

    private final float[][] cepstra(float[] x) {
        int frames = (x.length - 400) / 160 + 1;
        if (frames <= 0) {
            return null;
        }
        int n = 0;
        float[][] fArrayArray = new float[frames][];
        while (n < frames) {
            int n2 = n++;
            fArrayArray[n2] = new float[12];
        }
        float[][] out = fArrayArray;
        float[] re = new float[512];
        float[] im = new float[512];
        float[] power = new float[257];
        float[] energies = new float[26];
        for (int f = 0; f < frames; ++f) {
            float acc;
            int start = f * 160;
            Arrays.fill(re, 0.0f);
            Arrays.fill(im, 0.0f);
            for (int i = 0; i < 400; ++i) {
                re[i] = x[start + i] * WINDOW[i];
            }
            this.fft(re, im);
            for (int k = 0; k < 257; ++k) {
                power[k] = re[k] * re[k] + im[k] * im[k];
            }
            for (int m = 0; m < 26; ++m) {
                acc = 0.0f;
                float[] filter = MEL_BANK[m];
                for (int k = 0; k < 257; ++k) {
                    if (!(filter[k] > 0.0f)) continue;
                    acc += filter[k] * power[k];
                }
                energies[m] = (float)Math.log((double)acc + 1.0E-10);
            }
            for (int c = 1; c < 13; ++c) {
                acc = 0.0f;
                float[] basis = DCT[c];
                for (int m = 0; m < 26; ++m) {
                    acc += energies[m] * basis[m];
                }
                out[f][c - 1] = acc;
            }
        }
        return out;
    }

    private final int[] trimRange(float[] energy, int frames) {
        int last;
        int first;
        float peak = 0.0f;
        for (int f = 0; f < frames; ++f) {
            if (!(energy[f] > peak)) continue;
            peak = energy[f];
        }
        if (peak <= 0.0f) {
            int[] f = new int[]{0, frames - 1};
            return f;
        }
        float[] sorted = new float[frames];
        System.arraycopy(energy, 0, sorted, 0, frames);
        Arrays.sort(sorted);
        float floor = sorted[frames / 10];
        float gate = Math.max(peak * 0.01f, floor * 2.5f);
        for (first = 0; first < frames && energy[first] < gate; ++first) {
        }
        for (last = frames - 1; last > first && energy[last] < gate; --last) {
        }
        first = Math.max(0, first - 3);
        last = Math.min(frames - 1, last + 3);
        int[] nArray = new int[]{first, last};
        return nArray;
    }

    private final float[][] withDeltas(float[][] cepstra) {
        int frames = ((Object[])cepstra).length;
        int n = 0;
        float[][] fArrayArray = new float[frames][];
        while (n < frames) {
            int n2 = n++;
            fArrayArray[n2] = new float[24];
        }
        float[][] out = fArrayArray;
        for (int f = 0; f < frames; ++f) {
            System.arraycopy(cepstra[f], 0, out[f], 0, 12);
            for (int d = 0; d < 12; ++d) {
                float p1 = cepstra[Math.min(frames - 1, f + 1)][d];
                float m1 = cepstra[Math.max(0, f - 1)][d];
                float p2 = cepstra[Math.min(frames - 1, f + 2)][d];
                float m2 = cepstra[Math.max(0, f - 2)][d];
                out[f][12 + d] = (p1 - m1 + 2.0f * (p2 - m2)) / 10.0f;
            }
        }
        return out;
    }

    private final void normalise(float[][] frames) {
        int n = ((Object[])frames).length;
        for (int d = 0; d < 24; ++d) {
            float sum = 0.0f;
            int n2 = ((Object[])frames).length;
            for (int i = 0; i < n2; ++i) {
                float[] frame = frames[i];
                sum += frame[d];
            }
            float mean = sum / (float)n;
            float variance = 0.0f;
            int n3 = ((Object[])frames).length;
            for (int frame = 0; frame < n3; ++frame) {
                float[] frame2 = frames[frame];
                float diff = frame2[d] - mean;
                variance += diff * diff;
            }
            float scale = 1.0f / Math.max(0.001f, (float)Math.sqrt(variance / (float)n));
            int n4 = ((Object[])frames).length;
            for (n3 = 0; n3 < n4; ++n3) {
                float[] frame = frames[n3];
                frame[d] = (frame[d] - mean) * scale;
            }
        }
    }

    private final void fft(float[] re, float[] im) {
        for (int i = 0; i < 512; ++i) {
            int j = BIT_REV[i];
            if (j <= i) continue;
            float tr = re[i];
            re[i] = re[j];
            re[j] = tr;
            float ti = im[i];
            im[i] = im[j];
            im[j] = ti;
        }
        for (int size = 2; size <= 512; size <<= 1) {
            int half = size >> 1;
            int step = 512 / size;
            for (int i = 0; i < 512; i += size) {
                for (int j = 0; j < half; ++j) {
                    int tw = j * step;
                    float c = COS_TABLE[tw];
                    float s = SIN_TABLE[tw];
                    int a = i + j;
                    int b = a + half;
                    float tr = re[b] * c - im[b] * s;
                    float ti = re[b] * s + im[b] * c;
                    re[b] = re[a] - tr;
                    im[b] = im[a] - ti;
                    re[a] = re[a] + tr;
                    im[a] = im[a] + ti;
                }
            }
        }
    }

    private final float hzToMel(float hz) {
        return (float)(2595.0 * Math.log10(1.0 + (double)hz / 700.0));
    }

    private final float melToHz(float mel) {
        return (float)(700.0 * (Math.pow(10.0, (double)mel / 2595.0) - 1.0));
    }

    static {
        int i;
        int i2;
        int n;
        INSTANCE = new VoiceDsp();
        FIR = new float[31];
        WINDOW = new float[400];
        int n2 = 0;
        float[][] fArrayArray = new float[26][];
        while (n2 < 26) {
            n = n2++;
            fArrayArray[n] = new float[257];
        }
        MEL_BANK = fArrayArray;
        n2 = 0;
        fArrayArray = new float[13][];
        while (n2 < 13) {
            n = n2++;
            fArrayArray[n] = new float[26];
        }
        DCT = fArrayArray;
        BIT_REV = new int[512];
        COS_TABLE = new float[256];
        SIN_TABLE = new float[256];
        float fc = 0.14583333f;
        float sum = 0.0f;
        int mid = 15;
        for (i2 = 0; i2 < 31; ++i2) {
            int k = i2 - mid;
            float sinc = k == 0 ? 2.0f * fc : (float)(Math.sin(Math.PI * 2 * (double)fc * (double)k) / (Math.PI * (double)k));
            float w = (float)(0.54 - 0.46 * Math.cos(Math.PI * 2 * (double)i2 / (double)30));
            VoiceDsp.FIR[i2] = sinc * w;
            sum += FIR[i2];
        }
        i2 = 0;
        while (i2 < 31) {
            float[] k = FIR;
            int sinc = i2++;
            k[sinc] = k[sinc] / sum;
        }
        for (i2 = 0; i2 < 400; ++i2) {
            VoiceDsp.WINDOW[i2] = (float)(0.54 - 0.46 * Math.cos(Math.PI * 2 * (double)i2 / (double)399));
        }
        float melLow = INSTANCE.hzToMel(80.0f);
        float melHigh = INSTANCE.hzToMel(7600.0f);
        float[] points = new float[28];
        int n3 = points.length;
        for (int i3 = 0; i3 < n3; ++i3) {
            float mel = melLow + (melHigh - melLow) * (float)i3 / (float)27;
            points[i3] = INSTANCE.melToHz(mel) * (float)512 / (float)16000;
        }
        for (int m = 0; m < 26; ++m) {
            float left = points[m];
            float centre = points[m + 1];
            float right = points[m + 2];
            for (int k = 0; k < 257; ++k) {
                float v = 0.0f;
                if ((float)k >= left && (float)k <= centre && centre > left) {
                    v = ((float)k - left) / (centre - left);
                } else if ((float)k > centre && (float)k <= right && right > centre) {
                    v = (right - (float)k) / (right - centre);
                }
                VoiceDsp.MEL_BANK[m][k] = v;
            }
        }
        for (int c = 0; c < 13; ++c) {
            for (int m = 0; m < 26; ++m) {
                VoiceDsp.DCT[c][m] = (float)Math.cos(Math.PI * (double)c * ((double)m + 0.5) / (double)26);
            }
        }
        int bits = Integer.numberOfTrailingZeros(512);
        for (i = 0; i < 512; ++i) {
            int rev = 0;
            for (int b = 0; b < bits; ++b) {
                rev |= (i >> b & 1) << bits - 1 - b;
            }
            VoiceDsp.BIT_REV[i] = rev;
        }
        for (i = 0; i < 256; ++i) {
            VoiceDsp.COS_TABLE[i] = (float)Math.cos(Math.PI * -2 * (double)i / (double)512);
            VoiceDsp.SIN_TABLE[i] = (float)Math.sin(Math.PI * -2 * (double)i / (double)512);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u001f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\b\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\tR\u0019\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u0092\u0002\u0002\b\b\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/utils/voice/VoiceDsp$Extracted;", "", "", "", "frames", "energy", "<init>", "([[F[F)V", "Lkotlin/jvm/JvmField;", "[[F", "[F", "rtx.kimiko:kimiko"})
    public static final class Extracted {
        @JvmField
        @NotNull
        public final float[][] frames;
        @JvmField
        @NotNull
        public final float[] energy;

        public Extracted(@NotNull float[][] frames, @NotNull float[] energy) {
            Intrinsics.checkNotNullParameter((Object)frames, (String)"frames");
            Intrinsics.checkNotNullParameter((Object)energy, (String)"energy");
            this.frames = frames;
            this.energy = energy;
        }
    }
}

