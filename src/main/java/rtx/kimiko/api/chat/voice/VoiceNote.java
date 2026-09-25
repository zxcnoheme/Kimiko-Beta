/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.ArraysKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.Regex
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.chat.voice;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u0012\n\u0002\b\f\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ!\u0010\r\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\r\u0010\u000eJ!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000f\u001a\u00020\u000bH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u000bH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0016\u001a\u00020\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0016\u0010\u0017J#\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001aH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001c\u0010\u001dJ#\u0010#\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b#\u0010$J\u001b\u0010&\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u001aH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020\u001a8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020\u001a8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b*\u0010)R\u0014\u0010+\u001a\u00020\u001a8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b+\u0010)R\u0014\u0010,\u001a\u00020\u001a8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b,\u0010)R\u0014\u0010-\u001a\u00020\u001a8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b-\u0010)R\u0014\u0010.\u001a\u00020\u001a8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b.\u0010)R\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u00101\u00a8\u00062"}, d2={"Lrtx/kimiko/api/chat/voice/VoiceNote;", "", "<init>", "()V", "", "value", "", "Lkotlin/jvm/JvmStatic;", "isId", "(Ljava/lang/String;)Z", "", "", "frames", "pack", "(Ljava/util/List;)[B", "blob", "unpack", "([B)Ljava/util/List;", "peaks", "peaksToBase64", "([B)Ljava/lang/String;", "text", "peaksFromBase64", "(Ljava/lang/String;)[B", "", "levels", "", "count", "resample", "([FI)[B", "", "samples", "", "gain", "", "applyGain", "([SF)V", "ms", "formatDuration", "(I)Ljava/lang/String;", "PEAK_COUNT", "I", "FRAME_MS", "MAX_MS", "MIN_MS", "MAX_FRAMES", "MAX_BLOB_BYTES", "Lkotlin/text/Regex;", "ID_REGEX", "Lkotlin/text/Regex;", "rtx.kimiko:kimiko"})
public final class VoiceNote {
    @NotNull
    public static final VoiceNote INSTANCE = new VoiceNote();
    public static final int PEAK_COUNT = 26;
    public static final int FRAME_MS = 20;
    public static final int MAX_MS = 60000;
    public static final int MIN_MS = 500;
    public static final int MAX_FRAMES = 3000;
    public static final int MAX_BLOB_BYTES = 1500000;
    @NotNull
    private static final Regex ID_REGEX = new Regex("^[0-9a-f]{24}$");

    private VoiceNote() {
    }

    @JvmStatic
    public static final boolean isId(@Nullable String value) {
        return value != null && ID_REGEX.matches((CharSequence)value);
    }

    @JvmStatic
    @NotNull
    public static final byte[] pack(@NotNull List<byte[]> frames) {
        Intrinsics.checkNotNullParameter(frames, (String)"frames");
        int size = 0;
        for (byte[] f : frames) {
            size += f.length + 2;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream(size);
        for (byte[] f : frames) {
            if (f.length == 0 || f.length > 65535) continue;
            out.write(f.length >>> 8 & 0xFF);
            out.write(f.length & 0xFF);
            out.write(f, 0, f.length);
        }
        byte[] byArray = out.toByteArray();
        Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"toByteArray(...)");
        return byArray;
    }

    @JvmStatic
    @NotNull
    public static final List<byte[]> unpack(@NotNull byte[] blob) {
        int len;
        Intrinsics.checkNotNullParameter((Object)blob, (String)"blob");
        ArrayList<byte[]> frames = new ArrayList<byte[]>();
        int i = 0;
        while (i + 2 <= blob.length && (len = (blob[i] & 0xFF) << 8 | blob[i + 1] & 0xFF) > 0 && (i += 2) + len <= blob.length) {
            frames.add(ArraysKt.copyOfRange((byte[])blob, (int)i, (int)(i + len)));
            i += len;
        }
        return frames;
    }

    @JvmStatic
    @NotNull
    public static final String peaksToBase64(@NotNull byte[] peaks) {
        Intrinsics.checkNotNullParameter((Object)peaks, (String)"peaks");
        String string = Base64.getEncoder().withoutPadding().encodeToString(peaks);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"encodeToString(...)");
        return string;
    }

    @JvmStatic
    @NotNull
    public static final byte[] peaksFromBase64(@Nullable String text) {
        if (text == null || StringsKt.isBlank(text)) {
            return new byte[0];
        }
        try {
            byte[] raw = Base64.getDecoder().decode(text.trim());
            if (raw.length > 26) {
                return Arrays.copyOf(raw, 26);
            }
            return raw;
        }
        catch (Throwable t) {
            return new byte[0];
        }
    }

    @JvmStatic
    @NotNull
    public static final byte[] resample(@NotNull float[] levels, int count) {
        Intrinsics.checkNotNullParameter((Object)levels, (String)"levels");
        if (count <= 0) {
            return new byte[0];
        }
        byte[] out = new byte[count];
        if (levels.length == 0) {
            return out;
        }
        float loudest = 0.0f;
        int n = levels.length;
        for (int i = 0; i < n; ++i) {
            float v = levels[i];
            if (!(v > loudest)) continue;
            loudest = v;
        }
        float norm = loudest > 0.02f ? 1.0f / loudest : 0.0f;
        for (int i = 0; i < count; ++i) {
            int from = (int)((long)i * (long)levels.length / (long)count);
            int to = Math.max(from + 1, (int)((long)(i + 1) * (long)levels.length / (long)count));
            float peak = 0.0f;
            int n2 = Math.min(to, levels.length);
            for (int j = from; j < n2; ++j) {
                if (!(levels[j] > peak)) continue;
                peak = levels[j];
            }
            float scaled = (float)Math.sqrt(peak * norm);
            out[i] = (byte)Math.round(Math.min(1.0f, scaled) * 255.0f);
        }
        return out;
    }

    @JvmStatic
    public static final void applyGain(@NotNull short[] samples, float gain) {
        Intrinsics.checkNotNullParameter((Object)samples, (String)"samples");
        if (gain == 1.0f) {
            return;
        }
        int n = samples.length;
        for (int i = 0; i < n; ++i) {
            int v = Math.round((float)samples[i] * gain);
            if (v > Short.MAX_VALUE) {
                v = Short.MAX_VALUE;
            } else if (v < Short.MIN_VALUE) {
                v = Short.MIN_VALUE;
            }
            samples[i] = (short)v;
        }
    }

    @JvmStatic
    @NotNull
    public static final String formatDuration(int ms) {
        int total = Math.max(0, ms) / 1000;
        String string = "%d:%02d";
        Object[] objectArray = new Object[]{total / 60, total % 60};
        String string2 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
        return string2;
    }
}

