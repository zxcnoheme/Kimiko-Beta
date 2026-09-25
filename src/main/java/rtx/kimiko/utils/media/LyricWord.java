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

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\rJ\u0010\u0010\u0016\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\rJ\u0010\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u001aJB\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007H\u00c6\u0001\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001f\u001a\u00020\u000f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001f\u0010 J\u0011\u0010!\u001a\u00020\u0007H\u00d6\u0081\u0004\u00a2\u0006\u0004\b!\u0010\u001aJ\u0011\u0010\"\u001a\u00020\u0005H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\"\u0010\u0018R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b#\u0012\b\b$\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010%\u001a\u0004\b\u0003\u0010\rR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b#\u0012\b\b$\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010%\u001a\u0004\b\u0004\u0010\rR%\u0010\u0006\u001a\u00020\u00058\u0007z\f\b#\u0012\b\b$\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010&\u001a\u0004\b\u0006\u0010\u0018R%\u0010\b\u001a\u00020\u00078\u0007z\f\b#\u0012\b\b$\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010'\u001a\u0004\b\b\u0010\u001aR%\u0010\t\u001a\u00020\u00078\u0007z\f\b#\u0012\b\b$\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010'\u001a\u0004\b\t\u0010\u001a\u00a8\u0006("}, d2={"Lrtx/kimiko/utils/media/LyricWord;", "", "", "startMillis", "endMillis", "", "text", "", "begin", "end", "<init>", "(JJLjava/lang/String;II)V", "durationMillis", "()J", "millis", "", "contains", "(J)Z", "", "progress", "(J)F", "component1", "component2", "component3", "()Ljava/lang/String;", "component4", "()I", "component5", "copy", "(JJLjava/lang/String;II)Lrtx/kimiko/utils/media/LyricWord;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Lkotlin/jvm/JvmName;", "name", "J", "Ljava/lang/String;", "I", "rtx.kimiko:kimiko"})
public final class LyricWord {
    private final long startMillis;
    private final long endMillis;
    @NotNull
    private final String text;
    private final int begin;
    private final int end;

    public LyricWord(long startMillis, long endMillis, @NotNull String text, int begin, int end) {
        Intrinsics.checkNotNullParameter((Object)text, (String)"text");
        this.startMillis = startMillis;
        this.endMillis = endMillis;
        this.text = text;
        this.begin = begin;
        this.end = end;
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

    @JvmName(name="begin")
    public final int begin() {
        return this.begin;
    }

    @JvmName(name="end")
    public final int end() {
        return this.end;
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

    public final int component4() {
        return this.begin;
    }

    public final int component5() {
        return this.end;
    }

    @NotNull
    public final LyricWord copy(long startMillis, long endMillis, @NotNull String text, int begin, int end) {
        Intrinsics.checkNotNullParameter((Object)text, (String)"text");
        return new LyricWord(startMillis, endMillis, text, begin, end);
    }

    public static /* synthetic */ LyricWord copy$default(LyricWord lyricWord, long l, long l2, String string, int n, int n2, int n3, Object object) {
        if ((n3 & 1) != 0) {
            l = lyricWord.startMillis;
        }
        if ((n3 & 2) != 0) {
            l2 = lyricWord.endMillis;
        }
        if ((n3 & 4) != 0) {
            string = lyricWord.text;
        }
        if ((n3 & 8) != 0) {
            n = lyricWord.begin;
        }
        if ((n3 & 0x10) != 0) {
            n2 = lyricWord.end;
        }
        return lyricWord.copy(l, l2, string, n, n2);
    }

    @NotNull
    public String toString() {
        return "LyricWord(startMillis=" + this.startMillis + ", endMillis=" + this.endMillis + ", text=" + this.text + ", begin=" + this.begin + ", end=" + this.end + ")";
    }

    public int hashCode() {
        int result = Long.hashCode(this.startMillis);
        result = result * 31 + Long.hashCode(this.endMillis);
        result = result * 31 + this.text.hashCode();
        result = result * 31 + Integer.hashCode(this.begin);
        result = result * 31 + Integer.hashCode(this.end);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LyricWord)) {
            return false;
        }
        LyricWord lyricWord = (LyricWord)other;
        if (this.startMillis != lyricWord.startMillis) {
            return false;
        }
        if (this.endMillis != lyricWord.endMillis) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.text, (Object)lyricWord.text)) {
            return false;
        }
        if (this.begin != lyricWord.begin) {
            return false;
        }
        return this.end == lyricWord.end;
    }
}

