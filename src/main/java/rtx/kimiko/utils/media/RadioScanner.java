/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.media;

import java.io.Closeable;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLConnection;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import recovery.privacy.NetworkPolicy;
import rtx.kimiko.utils.media.Radio;
import rtx.kimiko.utils.media.RadioStation;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000b\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\r\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\r\u0010\fJ\u0013\u0010\u000e\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0011J-\u0010\u0016\u001a\u00020\u00152\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0013H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u0014\u001a\u00020\u00132\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0014\u0010\u0018J\u001d\u0010\u0019\u001a\u00020\u00152\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0019\u0010\u001aJ)\u0010\u001f\u001a\u00020\u00152\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001e\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001f\u0010 J%\u0010!\u001a\u00020\u00152\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001e\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b#\u0010\fJ\u000f\u0010%\u001a\u00020$H\u0002\u00a2\u0006\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020\u00068\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020\u00068\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b)\u0010(R\u0014\u0010*\u001a\u00020\u00068\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b*\u0010(R\u0014\u0010+\u001a\u00020\u00068\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b+\u0010(R\u0014\u0010,\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010(R\u0014\u0010-\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010(R\u0014\u0010.\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010/R \u00101\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0006008\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b1\u00102R \u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0013008\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u00102R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00106R\u0018\u00107\u001a\u0004\u0018\u00010$8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00108\u00a8\u00069"}, d2={"Lrtx/kimiko/utils/media/RadioScanner;", "", "<init>", "()V", "", "url", "", "Lkotlin/jvm/JvmStatic;", "status", "(Ljava/lang/String;)I", "", "online", "(Ljava/lang/String;)Z", "settled", "scanning", "()Z", "pending", "()I", "wasOnline", "", "stamp", "", "restore", "(Ljava/lang/String;ZJ)V", "(Ljava/lang/String;)J", "markOffline", "(Ljava/lang/String;)V", "", "Lrtx/kimiko/utils/media/RadioStation;", "stations", "force", "scan", "(Ljava/lang/Iterable;Z)V", "queue", "(Ljava/lang/String;Z)V", "probe", "Ljava/util/concurrent/ExecutorService;", "executor", "()Ljava/util/concurrent/ExecutorService;", "UNKNOWN", "I", "CHECKING", "ONLINE", "OFFLINE", "CONNECT_TIMEOUT_MS", "READ_TIMEOUT_MS", "RECHECK_INTERVAL_MS", "J", "Ljava/util/concurrent/ConcurrentHashMap;", "STATUS", "Ljava/util/concurrent/ConcurrentHashMap;", "STAMPS", "Ljava/util/concurrent/atomic/AtomicInteger;", "PENDING", "Ljava/util/concurrent/atomic/AtomicInteger;", "pool", "Ljava/util/concurrent/ExecutorService;", "rtx.kimiko:kimiko"})
public final class RadioScanner {
    @NotNull
    public static final RadioScanner INSTANCE = new RadioScanner();
    public static final int UNKNOWN = 0;
    public static final int CHECKING = 1;
    public static final int ONLINE = 2;
    public static final int OFFLINE = 3;
    private static final int CONNECT_TIMEOUT_MS = 4000;
    private static final int READ_TIMEOUT_MS = 4000;
    private static final long RECHECK_INTERVAL_MS = 21600000L;
    @NotNull
    private static final ConcurrentHashMap<String, Integer> STATUS = new ConcurrentHashMap();
    @NotNull
    private static final ConcurrentHashMap<String, Long> STAMPS = new ConcurrentHashMap();
    @NotNull
    private static final AtomicInteger PENDING = new AtomicInteger();
    @Nullable
    private static ExecutorService pool;

    private RadioScanner() {
    }

    @JvmStatic
    public static final int status(@Nullable String url) {
        return ((Number)((Map)STATUS).getOrDefault(url, 0)).intValue();
    }

    @JvmStatic
    public static final boolean online(@Nullable String url) {
        return RadioScanner.status(url) == 2;
    }

    @JvmStatic
    public static final boolean settled(@Nullable String url) {
        int state = RadioScanner.status(url);
        return state == 2 || state == 3;
    }

    @JvmStatic
    public static final boolean scanning() {
        return PENDING.get() > 0;
    }

    @JvmStatic
    public static final int pending() {
        return PENDING.get();
    }

    @JvmStatic
    public static final void restore(@Nullable String url, boolean wasOnline, long stamp) {
        CharSequence charSequence = url;
        if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) {
            return;
        }
        if (System.currentTimeMillis() - stamp > 21600000L) {
            return;
        }
        ((Map)STATUS).put(url, wasOnline ? 2 : 3);
        ((Map)STAMPS).put(url, stamp);
    }

    @JvmStatic
    public static final long stamp(@Nullable String url) {
        return ((Number)((Map)STAMPS).getOrDefault(url, 0L)).longValue();
    }

    @JvmStatic
    public static final void markOffline(@Nullable String url) {
        CharSequence charSequence = url;
        if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) {
            return;
        }
        ((Map)STATUS).put(url, 3);
        ((Map)STAMPS).put(url, System.currentTimeMillis());
    }

    @JvmStatic
    public static final void scan(@NotNull Iterable<RadioStation> stations, boolean force) {
        Intrinsics.checkNotNullParameter(stations, (String)"stations");
        for (RadioStation station : stations) {
            if (!station.valid()) continue;
            RadioScanner.queue(station.url(), force);
        }
    }

    @JvmStatic
    public static final void queue(@Nullable String url, boolean force) {
        boolean fresh;
        CharSequence charSequence = url;
        if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) {
            return;
        }
        Integer state = STATUS.get(url);
        if (state != null) {
            int n = 1;
            if (state == n) {
                return;
            }
        }
        Long l = STAMPS.getOrDefault(url, 0L);
        Intrinsics.checkNotNullExpressionValue((Object)l, (String)"getOrDefault(...)");
        long stamp = ((Number)l).longValue();
        boolean bl = fresh = System.currentTimeMillis() - stamp < 21600000L;
        if (!force && state != null && state != 0 && fresh) {
            return;
        }
        ((Map)STATUS).put(url, 1);
        PENDING.incrementAndGet();
        INSTANCE.executor().execute(() -> RadioScanner.queue$lambda$0(url));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final boolean probe(String url) {
        HttpURLConnection connection = null;
        try {
            URLConnection uRLConnection = NetworkPolicy.openConnection(URI.create(url).toURL());
            connection = (HttpURLConnection) uRLConnection;
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(4000);
            connection.setReadTimeout(4000);
            NetworkPolicy.redirects(connection, true);
            connection.setRequestProperty("User-Agent", "Kimiko/1.0");
            connection.setRequestProperty("Icy-MetaData", "1");
            NetworkPolicy.httpConnect(connection);
            int code = NetworkPolicy.responseCode(connection);
            if (code < 200 || code >= 300) {
                return false;
            }
            String type = connection.getContentType();
            if (type != null) {
                String lowered = type.toLowerCase(Locale.ROOT);
                boolean audio = lowered.startsWith("audio") || lowered.contains("ogg") || lowered.contains("mpegurl") || lowered.contains("octet-stream");
                if (!audio) {
                    return false;
                }
            }
            try (InputStream stream = (InputStream) NetworkPolicy.input(connection)) {
                byte[] probe = new byte[512];
                return stream.read(probe) > 0;
            }
        } catch (Throwable ignored) {
            return false;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private final synchronized ExecutorService executor() {
        ExecutorService service = pool;
        if (service == null) {
            pool = service = Executors.newFixedThreadPool(6, RadioScanner::executor$lambda$0);
        }
        ExecutorService executorService = service;
        Intrinsics.checkNotNull((Object)executorService);
        return executorService;
    }

    private static final void queue$lambda$0(String $url) {
        boolean reachable = INSTANCE.probe($url);
        ((Map)STATUS).put($url, reachable ? 2 : 3);
        ((Map)STAMPS).put($url, System.currentTimeMillis());
        PENDING.decrementAndGet();
        Radio.onScanUpdate();
    }

    private static final Thread executor$lambda$0(Runnable runnable) {
        Thread thread = new Thread(runnable, "kimiko-radio-scan");
        thread.setDaemon(true);
        return thread;
    }
}

