/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.voice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.voice.VoiceMatcher;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 ,2\u00020\u0001:\u0002-,B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00072\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u00a2\u0006\u0004\b\b\u0010\tJ\u001d\u0010\n\u001a\u00020\u00072\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u00a2\u0006\u0004\b\n\u0010\tJ\u000f\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0011\u0010\u0010J\u0013\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u00a2\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0016\u0010\rJ\r\u0010\u0017\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0017\u0010\u0010J\r\u0010\u0018\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0018\u0010\rJ\r\u0010\u001a\u001a\u00020\u0019\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001d\u0010\u001d\u001a\u00020\u00192\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u00a2\u0006\u0004\b\u001d\u0010\u001eJ%\u0010 \u001a\u00020\u00192\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u001f\u001a\u00020\u000b\u00a2\u0006\u0004\b \u0010!J%\u0010#\u001a\u00020\"2\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u001f\u001a\u00020\u000b\u00a2\u0006\u0004\b#\u0010$R$\u0010'\u001a\u0012\u0012\u0004\u0012\u00020\u00130%j\b\u0012\u0004\u0012\u00020\u0013`&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010\u001a\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010)R\u0016\u0010*\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010+\u00a8\u0006."}, d2={"Lrtx/kimiko/utils/voice/VoiceTemplate;", "", "<init>", "()V", "", "", "frames", "", "addRecorded", "([[F)V", "addAdapted", "", "adaptedCount", "()I", "", "evictOldestAdapted", "()Z", "clearAdapted", "", "Lrtx/kimiko/utils/voice/VoiceTemplate$Take;", "snapshot", "()Ljava/util/List;", "takeCount", "isEmpty", "maxTakeFrames", "", "spread", "()F", "test", "distanceTo", "([[F)F", "maxStart", "alignedDistance", "([[FI)F", "Lrtx/kimiko/utils/voice/VoiceMatcher$Prefix;", "prefixMatch", "([[FI)Lrtx/kimiko/utils/voice/VoiceMatcher$Prefix;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "takes", "Ljava/util/ArrayList;", "F", "spreadReady", "Z", "Companion", "Take", "rtx.kimiko:kimiko"})
public final class VoiceTemplate {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ArrayList<Take> takes = new ArrayList();
    private float spread = -1.0f;
    private boolean spreadReady;
    public static final int MAX_TAKES = 5;
    public static final int MAX_ADAPTED = 3;
    public static final int MIN_TAKE_FRAMES = 20;

    public final synchronized void addRecorded(@Nullable float[][] frames) {
        if (frames == null || ((Object[])frames).length < 20) {
            return;
        }
        if (this.takes.size() >= 5 && !this.evictOldestAdapted()) {
            this.takes.remove(0);
        }
        this.takes.add(new Take(frames, false));
        this.spreadReady = false;
    }

    public final synchronized void addAdapted(@Nullable float[][] frames) {
        if (frames == null || ((Object[])frames).length < 20) {
            return;
        }
        if (this.adaptedCount() >= 3 && !this.evictOldestAdapted()) {
            return;
        }
        this.takes.add(new Take(frames, true));
        this.spreadReady = false;
    }

    private final int adaptedCount() {
        int n = 0;
        Iterator<Take> iterator = this.takes.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Take> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Take take = (Take) (iterator2.next());
            if (!take.adapted) continue;
            ++n;
        }
        return n;
    }

    private final boolean evictOldestAdapted() {
        int n = ((Collection)this.takes).size();
        for (int i = 0; i < n; ++i) {
            if (!this.takes.get((int)i).adapted) continue;
            this.takes.remove(i);
            this.spreadReady = false;
            return true;
        }
        return false;
    }

    public final synchronized boolean clearAdapted() {
        boolean removed = this.takes.removeIf(it -> it.adapted);
        if (removed) {
            this.spreadReady = false;
        }
        return removed;
    }

    @NotNull
    public final synchronized List<Take> snapshot() {
        return new ArrayList(this.takes);
    }

    public final synchronized int takeCount() {
        return this.takes.size();
    }

    public final synchronized boolean isEmpty() {
        return this.takes.isEmpty();
    }

    public final synchronized int maxTakeFrames() {
        int max = 0;
        Iterator<Take> iterator = this.takes.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Take> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Take take = (Take) (iterator2.next());
            max = Math.max(max, ((Object[])take.frames).length);
        }
        return max;
    }

    public final synchronized float spread() {
        if (this.spreadReady) {
            return this.spread;
        }
        this.spreadReady = true;
        this.spread = -1.0f;
        if (this.takes.size() < 2) {
            return this.spread;
        }
        float sum = 0.0f;
        int count = 0;
        int n = ((Collection)this.takes).size();
        for (int i = 0; i < n; ++i) {
            int n2 = this.takes.size();
            for (int j = i + 1; j < n2; ++j) {
                float[][] a = this.takes.get((int)i).frames;
                float[][] b = this.takes.get((int)j).frames;
                float d = VoiceMatcher.distance(a, b);
                if (d >= VoiceMatcher.INF) {
                    d = Math.min(VoiceMatcher.matchPrefix((float[][])a, (float[][])b, (int)Math.max((int)4, (int)(((Object[])b).length / 4))).distance, VoiceMatcher.matchPrefix((float[][])b, (float[][])a, (int)Math.max((int)4, (int)(((Object[])a).length / 4))).distance);
                }
                if (!(d < VoiceMatcher.INF)) continue;
                sum += d;
                ++count;
            }
        }
        if (count > 0) {
            this.spread = sum / (float)count;
        }
        return this.spread;
    }

    public final synchronized float distanceTo(@Nullable float[][] test) {
        float best = VoiceMatcher.INF;
        Iterator<Take> iterator = this.takes.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Take> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Take take = (Take) (iterator2.next());
            float d = VoiceMatcher.distance(take.frames, test);
            if (!(d < best)) continue;
            best = d;
        }
        return best;
    }

    public final synchronized float alignedDistance(@Nullable float[][] test, int maxStart) {
        float best = VoiceMatcher.INF;
        Iterator<Take> iterator = this.takes.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Take> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Take take = (Take) (iterator2.next());
            float d = VoiceMatcher.matchPrefix((float[][])take.frames, (float[][])test, (int)maxStart).distance;
            if (!(d < best)) continue;
            best = d;
        }
        return best;
    }

    @NotNull
    public final synchronized VoiceMatcher.Prefix prefixMatch(@Nullable float[][] test, int maxStart) {
        VoiceMatcher.Prefix best = null;
        Iterator<Take> iterator = this.takes.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Take> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Take take = (Take) (iterator2.next());
            VoiceMatcher.Prefix p = VoiceMatcher.matchPrefix(take.frames, test, maxStart);
            if (best != null && !(p.distance < best.distance)) continue;
            best = p;
        }
        VoiceMatcher.Prefix prefix = best;
        if (prefix == null) {
            prefix = new VoiceMatcher.Prefix(VoiceMatcher.INF, 0, 0);
        }
        return prefix;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006\u00a8\u0006\t"}, d2={"Lrtx/kimiko/utils/voice/VoiceTemplate.Companion;", "", "<init>", "()V", "", "MAX_TAKES", "I", "MAX_ADAPTED", "MIN_TAKE_FRAMES", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bR\u001f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\t\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\nR\u0019\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\t\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u000b\u00a8\u0006\f"}, d2={"Lrtx/kimiko/utils/voice/VoiceTemplate$Take;", "", "", "", "frames", "", "adapted", "<init>", "([[FZ)V", "Lkotlin/jvm/JvmField;", "[[F", "Z", "rtx.kimiko:kimiko"})
    public static final class Take {
        @JvmField
        @NotNull
        public final float[][] frames;
        @JvmField
        public final boolean adapted;

        public Take(@NotNull float[][] frames, boolean adapted) {
            Intrinsics.checkNotNullParameter((Object)frames, (String)"frames");
            this.frames = frames;
            this.adapted = adapted;
        }
    }
}

