/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.media;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.media.LyricWord;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\rJ\u0010\u0010\u0018\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\rJ\u0010\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u001cJ>\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0001\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u001b\u0010 \u001a\u00020\u000f2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b \u0010!J\u0011\u0010#\u001a\u00020\"H\u00d6\u0081\u0004\u00a2\u0006\u0004\b#\u0010$J\u0011\u0010%\u001a\u00020\u0005H\u00d6\u0081\u0004\u00a2\u0006\u0004\b%\u0010\u001aR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010(\u001a\u0004\b\u0003\u0010\rR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010(\u001a\u0004\b\u0004\u0010\rR%\u0010\u0006\u001a\u00020\u00058\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010)\u001a\u0004\b\u0006\u0010\u001aR+\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010*\u001a\u0004\b\t\u0010\u001c\u00a8\u0006+"}, d2={"Lrtx/kimiko/utils/media/LyricLine;", "", "", "startMillis", "endMillis", "", "text", "", "Lrtx/kimiko/utils/media/LyricWord;", "words", "<init>", "(JJLjava/lang/String;Ljava/util/List;)V", "durationMillis", "()J", "millis", "", "contains", "(J)Z", "", "progress", "(J)F", "wordAt", "(J)Lrtx/kimiko/utils/media/LyricWord;", "component1", "component2", "component3", "()Ljava/lang/String;", "component4", "()Ljava/util/List;", "copy", "(JJLjava/lang/String;Ljava/util/List;)Lrtx/kimiko/utils/media/LyricLine;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Lkotlin/jvm/JvmName;", "name", "J", "Ljava/lang/String;", "Ljava/util/List;", "rtx.kimiko:kimiko"})
public final class LyricLine {
    private final long startMillis;
    private final long endMillis;
    @NotNull
    private final String text;
    @NotNull
    private final List<LyricWord> words;

    public LyricLine(long startMillis, long endMillis, @NotNull String text, @NotNull List<LyricWord> words) {
        Intrinsics.checkNotNullParameter((Object)text, (String)"text");
        Intrinsics.checkNotNullParameter(words, (String)"words");
        this.startMillis = startMillis;
        this.endMillis = endMillis;
        this.text = text;
        this.words = words;
    }

    @JvmName(name="startMillis")
    public final long startMillis() {
        return this.startMillis;
    }

    @JvmName(name="endMillis")
    public final long endMillis() {
        return this.endMillis;
    }

    @JvmName(name="text")
    @NotNull
    public final String text() {
        return this.text;
    }

    @JvmName(name="words")
    @NotNull
    public final List<LyricWord> words() {
        return this.words;
    }

    public final long durationMillis() {
        return Math.max(0L, this.endMillis - this.startMillis);
    }

    public final boolean contains(long millis) {
        return millis >= this.startMillis && millis < this.endMillis;
    }

    public final float progress(long millis) {
        long duration = this.durationMillis();
        if (duration <= 0L) {
            return millis >= this.startMillis ? 1.0f : 0.0f;
        }
        return RangesKt.coerceIn((float)((float)(millis - this.startMillis) / (float)duration), (float)0.0f, (float)1.0f);
    }

    @Nullable
    public final LyricWord wordAt(long millis) {
        for (LyricWord word : this.words) {
            if (!word.contains(millis)) continue;
            return word;
        }
        return null;
    }

    public final long component1() {
        return this.startMillis;
    }

    public final long component2() {
        return this.endMillis;
    }

    @NotNull
    public final String component3() {
        return this.text;
    }

    @NotNull
    public final List<LyricWord> component4() {
        return this.words;
    }

    @NotNull
    public final LyricLine copy(long startMillis, long endMillis, @NotNull String text, @NotNull List<LyricWord> words) {
        Intrinsics.checkNotNullParameter((Object)text, (String)"text");
        Intrinsics.checkNotNullParameter(words, (String)"words");
        return new LyricLine(startMillis, endMillis, text, words);
    }

    public static /* synthetic */ LyricLine copy$default(LyricLine lyricLine, long l, long l2, String string, List list, int n, Object object) {
        if ((n & 1) != 0) {
            l = lyricLine.startMillis;
        }
        if ((n & 2) != 0) {
            l2 = lyricLine.endMillis;
        }
        if ((n & 4) != 0) {
            string = lyricLine.text;
        }
        if ((n & 8) != 0) {
            list = lyricLine.words;
        }
        return lyricLine.copy(l, l2, string, list);
    }

    @NotNull
    public String toString() {
        return "LyricLine(startMillis=" + this.startMillis + ", endMillis=" + this.endMillis + ", text=" + this.text + ", words=" + this.words + ")";
    }

    public int hashCode() {
        int result = Long.hashCode(this.startMillis);
        result = result * 31 + Long.hashCode(this.endMillis);
        result = result * 31 + this.text.hashCode();
        result = result * 31 + ((Object)this.words).hashCode();
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LyricLine)) {
            return false;
        }
        LyricLine lyricLine = (LyricLine)other;
        if (this.startMillis != lyricLine.startMillis) {
            return false;
        }
        if (this.endMillis != lyricLine.endMillis) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.text, (Object)lyricLine.text)) {
            return false;
        }
        return Intrinsics.areEqual(this.words, lyricLine.words);
    }
}

