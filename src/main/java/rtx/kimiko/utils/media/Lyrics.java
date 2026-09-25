/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.media;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.media.LyricLine;
import rtx.kimiko.utils.media.LyricWord;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u001f\b\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u00a2\u0006\u0004\b\u0004\u0010\tJ\r\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\nJ\r\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\u0004\b\u000b\u0010\nJ\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u0004\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u0004\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u0013\u0010\u0012J\u0017\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u0017R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0018\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/utils/media/Lyrics;", "", "", "Lrtx/kimiko/utils/media/LyricLine;", "lines", "", "synced", "<init>", "(Ljava/util/List;Z)V", "()Ljava/util/List;", "()Z", "isEmpty", "", "millis", "", "indexAt", "(J)I", "lineAt", "(J)Lrtx/kimiko/utils/media/LyricLine;", "lineAfter", "Lrtx/kimiko/utils/media/LyricWord;", "wordAt", "(J)Lrtx/kimiko/utils/media/LyricWord;", "Ljava/util/List;", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class Lyrics {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final List<LyricLine> lines;
    private final boolean synced;
    @JvmField
    @NotNull
    public static final Lyrics EMPTY = new Lyrics(CollectionsKt.emptyList(), false);

    public Lyrics(@NotNull List<LyricLine> lines, boolean synced) {
        Intrinsics.checkNotNullParameter(lines, (String)"lines");
        this.lines = lines;
        this.synced = synced;
    }

    @NotNull
    public final List<LyricLine> lines() {
        return this.lines;
    }

    public final boolean synced() {
        return this.synced;
    }

    public final boolean isEmpty() {
        return this.lines.isEmpty();
    }

    public final int indexAt(long millis) {
        if (!this.synced) {
            return -1;
        }
        int low = 0;
        int high = this.lines.size() - 1;
        while (low <= high) {
            int middle = low + high >>> 1;
            LyricLine line = this.lines.get(middle);
            if (millis < line.startMillis()) {
                high = middle - 1;
                continue;
            }
            if (millis >= line.endMillis()) {
                low = middle + 1;
                continue;
            }
            return middle;
        }
        return -1;
    }

    @Nullable
    public final LyricLine lineAt(long millis) {
        int index = this.indexAt(millis);
        return index < 0 ? null : this.lines.get(index);
    }

    @Nullable
    public final LyricLine lineAfter(long millis) {
        for (LyricLine line : this.lines) {
            if (line.startMillis() <= millis) continue;
            return line;
        }
        return null;
    }

    @Nullable
    public final LyricWord wordAt(long millis) {
        LyricLine lyricLine = this.lineAt(millis);
        return lyricLine != null ? lyricLine.wordAt(millis) : null;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/utils/media/Lyrics.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/media/Lyrics;", "Lkotlin/jvm/JvmField;", "EMPTY", "Lrtx/kimiko/utils/media/Lyrics;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

