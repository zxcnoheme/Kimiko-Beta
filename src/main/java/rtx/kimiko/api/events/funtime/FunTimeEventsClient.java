/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.comparisons.ComparisonsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.events.funtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.funtime.FunTimeApi;
import rtx.kimiko.api.events.funtime.FunTimeApiException;
import rtx.kimiko.api.events.funtime.FunTimeEvent;
import rtx.kimiko.api.events.funtime.FunTimeEventsSnapshot;
import rtx.kimiko.api.events.funtime.FunTimeMine;
import rtx.kimiko.api.events.funtime.FunTimeMinesSnapshot;
import rtx.kimiko.api.lang.I18n;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 #2\u00020\u0001:\u0001#B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\r\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0003J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0010\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0010\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0003J\u0017\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aR\"\u0010\u001d\u001a\u0010\u0012\f\u0012\n \u001c*\u0004\u0018\u00010\u00070\u00070\u001b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\"\u0010\u001f\u001a\u0010\u0012\f\u0012\n \u001c*\u0004\u0018\u00010\n0\n0\u001b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001eR\u0018\u0010!\u001a\u0004\u0018\u00010 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b!\u0010\"\u00a8\u0006$"}, d2={"Lrtx/kimiko/api/events/funtime/FunTimeEventsClient;", "", "<init>", "()V", "", "start", "shutdown", "Lrtx/kimiko/api/events/funtime/FunTimeEventsSnapshot;", "snapshot", "()Lrtx/kimiko/api/events/funtime/FunTimeEventsSnapshot;", "Lrtx/kimiko/api/events/funtime/FunTimeMinesSnapshot;", "minesSnapshot", "()Lrtx/kimiko/api/events/funtime/FunTimeMinesSnapshot;", "Lrtx/kimiko/api/events/funtime/FunTimeEvent;", "event", "", "remainingSeconds", "(Lrtx/kimiko/api/events/funtime/FunTimeEvent;)I", "Lrtx/kimiko/api/events/funtime/FunTimeMine;", "mine", "", "(Lrtx/kimiko/api/events/funtime/FunTimeMine;)J", "refresh", "", "error", "setOffline", "(Ljava/lang/String;)V", "Ljava/util/concurrent/atomic/AtomicReference;", "kotlin.jvm.PlatformType", "events", "Ljava/util/concurrent/atomic/AtomicReference;", "mines", "Ljava/util/concurrent/ScheduledExecutorService;", "executor", "Ljava/util/concurrent/ScheduledExecutorService;", "Companion", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nFunTimeEventsClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FunTimeEventsClient.kt\nrtx/kimiko/api/events/funtime/FunTimeEventsClient\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,100:1\n777#2:101\n873#2,2:102\n1#3:104\n*S KotlinDebug\n*F\n+ 1 FunTimeEventsClient.kt\nrtx/kimiko/api/events/funtime/FunTimeEventsClient\n*L\n58#1:101\n58#1:102,2\n*E\n"})
public final class FunTimeEventsClient {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final AtomicReference<FunTimeEventsSnapshot> events = new AtomicReference<FunTimeEventsSnapshot>(FunTimeEventsSnapshot.Companion.offline(I18n.tr("Нет связи с API HolyWorld")));
    @NotNull
    private final AtomicReference<FunTimeMinesSnapshot> mines = new AtomicReference<FunTimeMinesSnapshot>(FunTimeMinesSnapshot.Companion.offline(I18n.tr("Нет связи с API HolyWorld")));
    @Nullable
    private ScheduledExecutorService executor;
    @JvmField
    @NotNull
    public static final FunTimeEventsClient INSTANCE = new FunTimeEventsClient();
    private static final int POLL_SECONDS = 12;

    private FunTimeEventsClient() {
    }

    public final synchronized void start() {
    }

    public final synchronized void shutdown() {
        ScheduledExecutorService scheduledExecutorService = this.executor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
        this.executor = null;
    }

    @NotNull
    public final FunTimeEventsSnapshot snapshot() {
        FunTimeEventsSnapshot funTimeEventsSnapshot = this.events.get();
        Intrinsics.checkNotNullExpressionValue((Object)funTimeEventsSnapshot, (String)"get(...)");
        return funTimeEventsSnapshot;
    }

    @NotNull
    public final FunTimeMinesSnapshot minesSnapshot() {
        FunTimeMinesSnapshot funTimeMinesSnapshot = this.mines.get();
        Intrinsics.checkNotNullExpressionValue((Object)funTimeMinesSnapshot, (String)"get(...)");
        return funTimeMinesSnapshot;
    }

    public final int remainingSeconds(@NotNull FunTimeEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        return 0;
    }

    public final long remainingSeconds(@NotNull FunTimeMine mine) {
        Intrinsics.checkNotNullParameter((Object)mine, (String)"mine");
        if (mine.refillAtMs() <= 0L) {
            return 0L;
        }
        return Math.max(0L, (mine.refillAtMs() - System.currentTimeMillis()) / 1000L);
    }

    /*
     * WARNING - void declaration
     */
    private final void refresh() {
        try {
                        long now = System.currentTimeMillis();
            List<FunTimeEvent> currentEvents = new ArrayList<>(FunTimeApi.INSTANCE.events());
            currentEvents.sort((a, b) -> {
                int r = Integer.compare(Companion.access$rarityWeight(Companion, b.rarity()),
                                        Companion.access$rarityWeight(Companion, a.rarity()));
                if (r != 0) return r;
                return Integer.compare(a.anarchy(), b.anarchy());
            });
            this.events.set(new FunTimeEventsSnapshot(CollectionsKt.toList((Iterable)currentEvents), CollectionsKt.emptyList(), now, true, ""));
            List<FunTimeMine> allMines = FunTimeApi.INSTANCE.mines();
            ArrayList<FunTimeMine> notableMines = new ArrayList<>();
            for (FunTimeMine m : allMines) {
                if (FunTimeEventsClient.Companion.isNotableRarity(m.rarity())) {
                    notableMines.add(m);
                }
            }
            this.mines.set(new FunTimeMinesSnapshot(notableMines, now, true, ""));
        }
        catch (FunTimeApiException api) {
            String string = api.getMessage();
            if (string == null) {
                string = "";
            }
            this.setOffline(string);
        }
        catch (Exception unexpected) {
            String string = unexpected.getClass().getSimpleName();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getSimpleName(...)");
            this.setOffline(string);
        }
    }

    private final void setOffline(String error) {
        FunTimeEventsSnapshot previousEvents = this.events.get();
        Intrinsics.checkNotNull((Object)previousEvents);
        this.events.set(FunTimeEventsSnapshot.copy$default(previousEvents, null, null, 0L, false, error, 7, null));
        FunTimeMinesSnapshot previousMines = this.mines.get();
        Intrinsics.checkNotNull((Object)previousMines);
        this.mines.set(FunTimeMinesSnapshot.copy$default(previousMines, null, 0L, false, error, 3, null));
    }

    private static final Thread start$lambda$0(Runnable runnable) {
        Thread thread;
        Thread $this$start_u24lambda_u240_u240 = thread = new Thread(runnable, "kimiko-holyworld-events");
        boolean bl = false;
        $this$start_u24lambda_u240_u240.setDaemon(true);
        return thread;
    }

    private static final void start$lambda$1$refresh(FunTimeEventsClient funTimeEventsClient) {
        Intrinsics.checkNotNullParameter((Object)funTimeEventsClient, (String)"<this>");
        funTimeEventsClient.refresh();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u000b\u0010\fR\u0019\u0010\u000f\u001a\u00020\r8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/api/events/funtime/FunTimeEventsClient.Companion;", "", "<init>", "()V", "", "rarity", "", "isNotableRarity", "(Ljava/lang/String;)Z", "rare", "", "rarityWeight", "(Ljava/lang/String;)I", "Lrtx/kimiko/api/events/funtime/FunTimeEventsClient;", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/events/funtime/FunTimeEventsClient;", "POLL_SECONDS", "I", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final boolean isNotableRarity(String rarity) {
            if (rarity == null) {
                return false;
            }
            String string = rarity;
            Locale locale = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
            String string2 = string.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
            String s = string2;
            return String.valueOf(s).contains("легендарн") || String.valueOf(s).contains("мифическ") || String.valueOf(s).contains("эпическ") || String.valueOf(s).contains("редк");
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private final int rarityWeight(String rare) {
            String string = rare;
            if (string == null) return 0;
            String string2 = string;
            Locale locale = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
            String string3 = string2.toUpperCase(locale);
            String string4 = string3;
            Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toUpperCase(...)");
            String string5 = string4;
            if (string5 == null) return 0;
            int n = -1;
            switch (string5.hashCode()) {
                case 909442021: {
                    if (string5.equals("MYTHICAL")) {
                        n = 1;
                    }
                    break;
                }
                case 2507938: {
                    if (string5.equals("RARE")) {
                        n = 2;
                    }
                    break;
                }
                case 2134789: {
                    if (string5.equals("EPIC")) {
                        n = 3;
                    }
                    break;
                }
                case 705031963: {
                    if (string5.equals("LEGENDARY")) {
                        n = 4;
                    }
                    break;
                }
                case -1986416409: {
                    if (string5.equals("NORMAL")) {
                        n = 5;
                    }
                    break;
                }
            }
            switch (n) {
                case 1: {
                    return 5;
                }
                case 4: {
                    return 4;
                }
                case 3: {
                    return 3;
                }
                case 2: {
                    return 2;
                }
                case 5: {
                    return 1;
                }
                default: {
                    return 0;
                }
            }
        }

        public static final /* synthetic */ int access$rarityWeight(Companion $this, String rare) {
            return $this.rarityWeight(rare);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

