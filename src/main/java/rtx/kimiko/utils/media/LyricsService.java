/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.fabricmc.loader.api.FabricLoader
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.media;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.CRC32;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.media.Lyrics;
import rtx.kimiko.utils.media.LyricsParser;
import rtx.kimiko.utils.media.MediaLog;
import rtx.kimiko.utils.media.MediaNative;
import rtx.kimiko.utils.media.MediaTrack;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ!\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0003J\u001f\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ!\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u001d\u001a\u0004\u0018\u00010\u0013H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0014\u0010 R\u0014\u0010!\u001a\u00020\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"R \u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\r0#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u001c\u0010(\u001a\n '*\u0004\u0018\u00010&0&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010)R \u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020*0#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b+\u0010%R\u0014\u0010,\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010.\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010/R\u0016\u00100\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u0010/R\u0014\u00101\u001a\u00020*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b1\u00102\u00a8\u00063"}, d2={"Lrtx/kimiko/utils/media/LyricsService;", "", "<init>", "()V", "", "value", "", "Lkotlin/jvm/JvmStatic;", "setTranscripts", "(Z)V", "Lrtx/kimiko/utils/media/MediaTrack;", "track", "Ljava/util/concurrent/CompletableFuture;", "Lrtx/kimiko/utils/media/Lyrics;", "request", "(Lrtx/kimiko/utils/media/MediaTrack;)Ljava/util/concurrent/CompletableFuture;", "forget", "(Lrtx/kimiko/utils/media/MediaTrack;)V", "sweep", "", "key", "resolve", "(Ljava/lang/String;Lrtx/kimiko/utils/media/MediaTrack;)Lrtx/kimiko/utils/media/Lyrics;", "Ljava/nio/file/Path;", "file", "discard", "(Ljava/nio/file/Path;)V", "read", "(Ljava/nio/file/Path;)Ljava/lang/String;", "content", "write", "(Ljava/nio/file/Path;Ljava/lang/String;)V", "(Lrtx/kimiko/utils/media/MediaTrack;)Ljava/lang/String;", "DIRECTORY", "Ljava/nio/file/Path;", "Ljava/util/concurrent/ConcurrentHashMap;", "MEMORY", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "WORKER", "Ljava/util/concurrent/ExecutorService;", "", "FAILURES", "VERSION", "Ljava/lang/String;", "swept", "Z", "transcripts", "RETRY_DELAYS_MS", "[J", "rtx.kimiko:kimiko"})
public final class LyricsService {
    @NotNull
    public static final LyricsService INSTANCE = new LyricsService();
    @NotNull
    private static final Path DIRECTORY;
    @NotNull
    private static final ConcurrentHashMap<String, Lyrics> MEMORY;
    private static final ExecutorService WORKER;
    @NotNull
    private static final ConcurrentHashMap<String, long[]> FAILURES;
    @NotNull
    private static final String VERSION = "v6";
    private static boolean swept;
    private static volatile boolean transcripts;
    @NotNull
    private static final long[] RETRY_DELAYS_MS;

    private LyricsService() {
    }

    @JvmStatic
    public static final void setTranscripts(boolean value) {
        transcripts = value;
    }

    @JvmStatic
    @NotNull
    public static final CompletableFuture<Lyrics> request(@NotNull MediaTrack track) {
        Intrinsics.checkNotNullParameter((Object)track, (String)"track");
        if (track.isEmpty() || !MediaNative.available()) {
            CompletableFuture<Lyrics> completableFuture = CompletableFuture.completedFuture(Lyrics.EMPTY);
            Intrinsics.checkNotNullExpressionValue(completableFuture, (String)"completedFuture(...)");
            return completableFuture;
        }
        String key = INSTANCE.key(track);
        Lyrics cached = MEMORY.get(key);
        if (cached != null && !cached.isEmpty()) {
            CompletableFuture<Lyrics> completableFuture = CompletableFuture.completedFuture(cached);
            Intrinsics.checkNotNullExpressionValue(completableFuture, (String)"completedFuture(...)");
            return completableFuture;
        }
        long[] failure = FAILURES.get(key);
        if (failure != null && System.currentTimeMillis() < failure[1]) {
            Lyrics lyrics = cached;
            if (lyrics == null) {
                lyrics = Lyrics.EMPTY;
            }
            CompletableFuture<Lyrics> completableFuture = CompletableFuture.completedFuture(lyrics);
            Intrinsics.checkNotNullExpressionValue(completableFuture, (String)"completedFuture(...)");
            return completableFuture;
        }
        CompletableFuture<Lyrics> completableFuture = CompletableFuture.supplyAsync(() -> LyricsService.request$lambda$0(key, track), WORKER);
        Intrinsics.checkNotNullExpressionValue(completableFuture, (String)"supplyAsync(...)");
        return completableFuture;
    }

    @JvmStatic
    public static final void forget(@NotNull MediaTrack track) {
        Intrinsics.checkNotNullParameter((Object)track, (String)"track");
        String key = INSTANCE.key(track);
        MEMORY.remove(key);
        FAILURES.remove(key);
        try {
            Files.deleteIfExists(DIRECTORY.resolve(key + ".lrc"));
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void sweep() {
        if (swept) {
            return;
        }
        swept = true;
        try {
            Path marker = DIRECTORY.resolve("version");
            if (Files.exists(marker, new LinkOption[0])) {
                String string = Files.readString(marker, StandardCharsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"readString(...)");
                if (Intrinsics.areEqual((Object)((Object)StringsKt.trim((CharSequence)string)).toString(), (Object)VERSION)) {
                    return;
                }
            }
            if (Files.isDirectory(DIRECTORY, new LinkOption[0])) {
                Closeable closeable = Files.newDirectoryStream(DIRECTORY, "*.lrc");
                Throwable throwable = null;
                try {
                    DirectoryStream entries = (DirectoryStream)closeable;
                    boolean bl = false;
                    Iterator iterator = entries.iterator();
                    Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
                    Iterator iterator2 = iterator;
                    while (iterator2.hasNext()) {
                        Path entry = (Path)iterator2.next();
                        Files.deleteIfExists(entry);
                    }
                    Unit unit = Unit.INSTANCE;
                }
                catch (Throwable throwable2) {
                    throwable = throwable2;
                    throw throwable2;
                }
                finally {
                    CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
                }
            }
            Files.createDirectories(DIRECTORY, new FileAttribute[0]);
            Files.writeString(marker, (CharSequence)VERSION, StandardCharsets.UTF_8, new OpenOption[0]);
            MediaLog.note("client", "lyrics cache rebuilt for v6");
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    private final Lyrics resolve(String key, MediaTrack track) {
        this.sweep();
        Path file = DIRECTORY.resolve(key + ".lrc");
        Intrinsics.checkNotNull((Object)file);
        Lyrics cached = LyricsParser.parse(this.read(file));
        if (cached.synced()) {
            ((Map)MEMORY).put(key, cached);
            FAILURES.remove(key);
            return cached;
        }
        long started = System.currentTimeMillis();
        String content = MediaNative.nativeFetchLyrics(track.title(), track.artist(), track.albumTitle(), track.durationSeconds());
        String string = content;
        MediaLog.note("client", "fetch '" + track.display() + "' took " + (System.currentTimeMillis() - started) + "ms, " + (string != null ? string.length() : 0) + " chars");
        Lyrics fetched = LyricsParser.parse(content);
        if (fetched.synced()) {
            this.write(file, content);
        } else {
            this.discard(file);
        }
        Lyrics resolved = fetched.isEmpty() ? cached : fetched;
        ((Map)MEMORY).put(key, resolved);
        if (resolved.synced()) {
            FAILURES.remove(key);
        } else {
            long[] failure = FAILURES.get(key);
            int attempt = failure == null ? 0 : Math.min((int)failure[0] + 1, RETRY_DELAYS_MS.length - 1);
            Map map = FAILURES;
            long[] lArray = new long[]{attempt, System.currentTimeMillis() + RETRY_DELAYS_MS[attempt]};
            map.put(key, lArray);
        }
        return resolved;
    }

    private final void discard(Path file) {
        try {
            Files.deleteIfExists(file);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    private final String read(Path file) {
        String string;
        try {
            string = Files.exists(file, new LinkOption[0]) ? Files.readString(file, StandardCharsets.UTF_8) : null;
        }
        catch (IOException failure) {
            string = null;
        }
        return string;
    }

    private final void write(Path file, String content) {
        try {
            Files.createDirectories(DIRECTORY, new FileAttribute[0]);
            Files.writeString(file, (CharSequence)content, StandardCharsets.UTF_8, new OpenOption[0]);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    private final String key(MediaTrack track) {
        CRC32 checksum = new CRC32();
        Object object = new CharSequence[]{track.title(), track.artist(), track.albumTitle(), String.valueOf(track.durationSeconds()), VERSION, transcripts ? "asr1" : "asr0"};
        String source = String.join((CharSequence)"|", (CharSequence[])object);
        Intrinsics.checkNotNull((Object)source);
        object = source;
        Charset charset = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue((Object)charset, (String)"UTF_8");
        byte[] byArray = ((String)object).getBytes(charset);
        Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"getBytes(...)");
        checksum.update(byArray);
        String string = Long.toHexString(checksum.getValue());
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toHexString(...)");
        return string;
    }

    private static final Thread WORKER$lambda$0(Runnable task) {
        Thread thread = new Thread(task, "kimiko-lyrics");
        thread.setDaemon(true);
        return thread;
    }

    private static final Lyrics request$lambda$0(String $key, MediaTrack $track) {
        return INSTANCE.resolve($key, $track);
    }

    static {
        Path path = FabricLoader.getInstance().getGameDir().resolve("kimiko").resolve("lyrics");
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
        DIRECTORY = path;
        MEMORY = new ConcurrentHashMap();
        WORKER = Executors.newSingleThreadExecutor(LyricsService::WORKER$lambda$0);
        FAILURES = new ConcurrentHashMap();
        transcripts = true;
        long[] lArray = new long[]{30000L, 120000L, 600000L, 3600000L};
        RETRY_DELAYS_MS = lArray;
    }
}

