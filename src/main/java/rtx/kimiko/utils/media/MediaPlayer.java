/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.NativeImage
 *  net.minecraft.client.texture.NativeImageBackedTexture
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.media;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.media.LyricLine;
import rtx.kimiko.utils.media.Lyrics;
import rtx.kimiko.utils.media.LyricsService;
import rtx.kimiko.utils.media.MediaLog;
import rtx.kimiko.utils.media.MediaNative;
import rtx.kimiko.utils.media.MediaStatus;
import rtx.kimiko.utils.media.MediaTrack;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J\u0013\u0010\n\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u0003J\u000f\u0010\u000b\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000b\u0010\u0003J\u000f\u0010\f\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\f\u0010\u0003J\u000f\u0010\r\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\r\u0010\u0003J\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0013\u0010\u0012\u001a\u00020\u0011H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u0011H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0014\u0010\u0013J\u0013\u0010\u0015\u001a\u00020\u0011H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0015\u0010\u0013J\u0013\u0010\u0016\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0016\u0010\u0007J\u0013\u0010\u0018\u001a\u00020\u0017H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u001b\u001a\u00020\u001aH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001d\u0010\u0007J\u0013\u0010\u001f\u001a\u00020\u001eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001f\u0010 J\u0013\u0010!\u001a\u00020\u001eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b!\u0010 J\u0013\u0010#\u001a\u00020\"H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b#\u0010$J\u001b\u0010&\u001a\u00020\b2\u0006\u0010%\u001a\u00020\u001eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b&\u0010'J\u001b\u0010)\u001a\u00020\b2\u0006\u0010(\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b)\u0010*J\u0013\u0010+\u001a\u00020\u001eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b+\u0010 J\u0013\u0010,\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b,\u0010\u0007J\u0013\u0010.\u001a\u00020-H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b.\u0010/J\u0013\u00100\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b0\u0010\u0003J\u0013\u00101\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b1\u0010\u0003J\u0013\u00102\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b2\u0010\u0003J\u001b\u00104\u001a\u00020\b2\u0006\u00103\u001a\u00020\u001eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b4\u0010'J\u001d\u00106\u001a\u00020\u00042\b\u00105\u001a\u0004\u0018\u00010-H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b6\u00107J\u0013\u00108\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b8\u0010\u0003J\u0013\u0010:\u001a\u000209H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b:\u0010;J\u0013\u0010<\u001a\u00020\u0011H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b<\u0010\u0013J%\u0010>\u001a\u00020\u00042\b\u00105\u001a\u0004\u0018\u00010-2\u0006\u0010=\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b>\u0010?J\u0013\u0010@\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b@\u0010\u0003J\u000f\u0010B\u001a\u00020AH\u0002\u00a2\u0006\u0004\bB\u0010CJ\u000f\u0010D\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bD\u0010\u0003J\u000f\u0010E\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bE\u0010\u0003J\u000f\u0010F\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bF\u0010\u0003J\u0017\u0010I\u001a\u00020\u00112\u0006\u0010H\u001a\u00020GH\u0002\u00a2\u0006\u0004\bI\u0010JJ\u0019\u0010(\u001a\u00020-2\b\u0010K\u001a\u0004\u0018\u00010-H\u0002\u00a2\u0006\u0004\b(\u0010LR\u0014\u0010M\u001a\u00020A8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bM\u0010NR\u0014\u0010O\u001a\u00020A8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bO\u0010NR\u0014\u0010P\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bP\u0010QR\u0016\u0010R\u001a\u00020A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bR\u0010NR\u0016\u0010S\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010QR\u0016\u0010T\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010UR\u0016\u0010V\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010UR\u0016\u0010W\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bW\u0010QR\u0016\u0010X\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010UR\u0016\u0010Y\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010QR\u0018\u0010Z\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010[R\u0016\u0010\\\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\\\u0010]R\u0016\u0010^\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010]R\u0016\u0010_\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010]R\u0016\u0010`\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010aR\u0016\u0010b\u001a\u00020\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010cR\u001e\u0010e\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010fR\u0016\u0010g\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bg\u0010QR\u0016\u0010h\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bh\u0010]R\u0018\u0010i\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bi\u0010aR\u0016\u0010j\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bj\u0010QR\u0016\u0010k\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bk\u0010aR\u0016\u0010l\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bl\u0010QR\u0014\u0010m\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bm\u0010Q\u00a8\u0006n"}, d2={"Lrtx/kimiko/utils/media/MediaPlayer;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "init", "()Z", "", "shutdown", "tick", "settle", "report", "follow", "Lnet/minecraft/Identifier;", "getCover", "()Lnet/minecraft/Identifier;", "", "getCoverAccent", "()I", "getCoverWidth", "getCoverHeight", "isAvailable", "Lrtx/kimiko/utils/media/MediaTrack;", "getTrack", "()Lrtx/kimiko/utils/media/MediaTrack;", "Lrtx/kimiko/utils/media/MediaStatus;", "getStatus", "()Lrtx/kimiko/utils/media/MediaStatus;", "isPlaying", "", "getPositionMillis", "()J", "getDurationMillis", "Lrtx/kimiko/utils/media/Lyrics;", "getLyrics", "()Lrtx/kimiko/utils/media/Lyrics;", "offset", "setLyricsOffsetMillis", "(J)V", "value", "setTranscriptFallback", "(Z)V", "getLyricsTimeMillis", "isAdvertisement", "", "getAppId", "()Ljava/lang/String;", "next", "previous", "toggle", "millis", "seek", "process", "startVocalDetect", "(Ljava/lang/String;)Z", "stopVocalDetect", "", "vocalLevel", "()F", "vocalFrames", "muted", "setMuted", "(Ljava/lang/String;Z)Z", "reloadLyrics", "", "clock", "()D", "refreshTrack", "refreshCover", "releaseCover", "Lnet/minecraft/NativeImage;", "image", "dominantColor", "(Lnet/minecraft/NativeImage;)I", "raw", "(Ljava/lang/String;)Ljava/lang/String;", "CLOCK_SNAP_MILLIS", "D", "CLOCK_CATCHUP_MILLIS", "SETTLE_MILLIS", "J", "clockMillis", "clockNanos", "clockValid", "Z", "initialized", "revision", "transcriptFallback", "thumbnailVersion", "cover", "Lnet/minecraft/Identifier;", "coverAccent", "I", "coverWidth", "coverHeight", "track", "Lrtx/kimiko/utils/media/MediaTrack;", "lyrics", "Lrtx/kimiko/utils/media/Lyrics;", "Ljava/util/concurrent/CompletableFuture;", "pending", "Ljava/util/concurrent/CompletableFuture;", "lyricsOffsetMillis", "reportedLine", "settleTrack", "settleStamp", "pendingTrack", "lastTickAtMs", "TICK_MIN_INTERVAL_MS", "rtx.kimiko:kimiko"})
public final class MediaPlayer {
    @NotNull
    public static final MediaPlayer INSTANCE = new MediaPlayer();
    private static final double CLOCK_SNAP_MILLIS = 300.0;
    private static final double CLOCK_CATCHUP_MILLIS = 450.0;
    private static final long SETTLE_MILLIS = 400L;
    private static double clockMillis;
    private static long clockNanos;
    private static boolean clockValid;
    private static boolean initialized;
    private static long revision;
    private static boolean transcriptFallback;
    private static long thumbnailVersion;
    @Nullable
    private static Identifier cover;
    private static int coverAccent;
    private static int coverWidth;
    private static int coverHeight;
    @NotNull
    private static MediaTrack track;
    @NotNull
    private static Lyrics lyrics;
    @Nullable
    private static CompletableFuture<Lyrics> pending;
    private static long lyricsOffsetMillis;
    private static int reportedLine;
    @Nullable
    private static MediaTrack settleTrack;
    private static long settleStamp;
    @NotNull
    private static MediaTrack pendingTrack;
    private static long lastTickAtMs;
    private static final long TICK_MIN_INTERVAL_MS = 40L;

    private MediaPlayer() {
    }

    @JvmStatic
    public static final boolean init() {
        if (!initialized && MediaNative.available() && (initialized = MediaNative.nativeInit())) {
            MediaLog.start();
        }
        return initialized;
    }

    @JvmStatic
    public static final void shutdown() {
        if (!initialized) {
            return;
        }
        initialized = false;
        revision = Long.MIN_VALUE;
        track = MediaTrack.EMPTY;
        lyrics = Lyrics.EMPTY;
        pending = null;
        settleTrack = null;
        pendingTrack = MediaTrack.EMPTY;
        clockValid = false;
        INSTANCE.releaseCover();
        MediaNative.nativeShutdown();
    }

    @JvmStatic
    public static final void tick() {
        long thumbnail;
        if (!initialized) {
            return;
        }
        long nowMs = System.currentTimeMillis();
        long l = nowMs - lastTickAtMs;
        boolean bl = 0L <= l ? l < 40L : false;
        if (bl) {
            return;
        }
        lastTickAtMs = nowMs;
        long current = MediaNative.nativeRevision();
        if (current != revision) {
            revision = current;
            INSTANCE.refreshTrack();
        }
        if ((thumbnail = MediaNative.nativeThumbnailVersion()) != thumbnailVersion) {
            thumbnailVersion = thumbnail;
            INSTANCE.refreshCover();
        }
        INSTANCE.settle();
        CompletableFuture<Lyrics> future = pending;
        if (future != null && future.isDone()) {
            pending = null;
            reportedLine = Integer.MIN_VALUE;
            if (Intrinsics.areEqual((Object)pendingTrack, (Object)track)) {
                Lyrics lyrics = future.getNow(Lyrics.EMPTY);
                Intrinsics.checkNotNullExpressionValue((Object)lyrics, (String)"getNow(...)");
                MediaPlayer.lyrics = lyrics;
                INSTANCE.report();
            } else {
                MediaLog.note("client", "dropped lyrics of '" + pendingTrack.display() + "', now playing '" + track.display() + "'");
                lyrics = Lyrics.EMPTY;
                settleTrack = track;
                settleStamp = System.currentTimeMillis();
            }
            pendingTrack = MediaTrack.EMPTY;
        }
        INSTANCE.follow();
    }

    private final void settle() {
        MediaTrack mediaTrack = settleTrack;
        if (mediaTrack == null) {
            return;
        }
        MediaTrack waiting = mediaTrack;
        if (System.currentTimeMillis() - settleStamp < 400L) {
            return;
        }
        if (!Intrinsics.areEqual((Object)waiting, (Object)track) || track.isEmpty() || MediaNative.nativeIsAdvertisement()) {
            settleTrack = null;
            return;
        }
        if (track.durationMillis() <= 0L) {
            settleStamp = System.currentTimeMillis();
            return;
        }
        settleTrack = null;
        pendingTrack = track;
        pending = LyricsService.request(track);
    }

    private final void report() {
        if (!MediaLog.active()) {
            return;
        }
        List<LyricLine> lines = lyrics.lines();
        int worded = 0;
        for (LyricLine line : lines) {
            if (!(!((Collection)line.words()).isEmpty())) continue;
            ++worded;
        }
        long first = lines.isEmpty() ? -1L : lines.get(0).startMillis();
        long last = lines.isEmpty() ? -1L : lines.get(lines.size() - 1).endMillis();
        MediaLog.note("client", "lyrics for '" + track.display() + "': lines=" + lines.size() + " synced=" + lyrics.synced() + " worded=" + worded + " span=" + first + ".." + last + " duration=" + MediaPlayer.getDurationMillis());
    }

    private final void follow() {
        if (!MediaLog.active() || !lyrics.synced()) {
            return;
        }
        long time = MediaPlayer.getLyricsTimeMillis();
        int index = lyrics.indexAt(time);
        if (index == reportedLine) {
            return;
        }
        reportedLine = index;
        if (index < 0) {
            return;
        }
        LyricLine line = lyrics.lines().get(index);
        MediaLog.note("sync", "pos=" + time + " offset=" + lyricsOffsetMillis + " line[" + index + "]=" + line.startMillis() + ".." + line.endMillis() + " lead=" + (time - line.startMillis()) + " '" + line.text() + "'");
    }

    @JvmStatic
    @Nullable
    public static final Identifier getCover() {
        return cover;
    }

    @JvmStatic
    public static final int getCoverAccent() {
        return coverAccent;
    }

    @JvmStatic
    public static final int getCoverWidth() {
        return coverWidth;
    }

    @JvmStatic
    public static final int getCoverHeight() {
        return coverHeight;
    }

    @JvmStatic
    public static final boolean isAvailable() {
        return initialized && MediaNative.nativeIsReady();
    }

    @JvmStatic
    @NotNull
    public static final MediaTrack getTrack() {
        return track;
    }

    @JvmStatic
    @NotNull
    public static final MediaStatus getStatus() {
        return initialized ? MediaStatus.Companion.of(MediaNative.nativeStatus()) : MediaStatus.CLOSED;
    }

    @JvmStatic
    public static final boolean isPlaying() {
        return MediaPlayer.getStatus().playing();
    }

    @JvmStatic
    public static final long getPositionMillis() {
        return Math.round(INSTANCE.clock());
    }

    @JvmStatic
    public static final long getDurationMillis() {
        return initialized ? MediaNative.nativeDurationMillis() : 0L;
    }

    @JvmStatic
    @NotNull
    public static final Lyrics getLyrics() {
        return lyrics;
    }

    @JvmStatic
    public static final void setLyricsOffsetMillis(long offset) {
        if (lyricsOffsetMillis != offset) {
            lyricsOffsetMillis = offset;
            reportedLine = Integer.MIN_VALUE;
        }
    }

    @JvmStatic
    public static final void setTranscriptFallback(boolean value) {
        if (initialized && transcriptFallback != value) {
            transcriptFallback = value;
            MediaNative.nativeSetTranscriptFallback(value);
            LyricsService.setTranscripts(value);
            MediaLog.note("client", "youtube auto captions allowed=" + value);
            MediaPlayer.reloadLyrics();
        }
    }

    @JvmStatic
    public static final long getLyricsTimeMillis() {
        return MediaPlayer.getPositionMillis() + lyricsOffsetMillis;
    }

    @JvmStatic
    public static final boolean isAdvertisement() {
        return initialized && MediaNative.nativeIsAdvertisement();
    }

    @JvmStatic
    @NotNull
    public static final String getAppId() {
        return track.appId();
    }

    @JvmStatic
    public static final void next() {
        if (initialized) {
            MediaNative.nativeNext();
        }
    }

    @JvmStatic
    public static final void previous() {
        if (initialized) {
            MediaNative.nativePrevious();
        }
    }

    @JvmStatic
    public static final void toggle() {
        if (initialized) {
            MediaNative.nativeToggle();
        }
    }

    @JvmStatic
    public static final void seek(long millis) {
        if (initialized) {
            clockMillis = millis;
            clockNanos = System.nanoTime();
            clockValid = true;
            MediaNative.nativeSeek(millis);
        }
    }

    @JvmStatic
    public static final boolean startVocalDetect(@Nullable String process) {
        CharSequence charSequence;
        return initialized && !((charSequence = (CharSequence)process) == null || charSequence.length() == 0) && MediaNative.nativeVocalStart(process);
    }

    @JvmStatic
    public static final void stopVocalDetect() {
        if (initialized) {
            MediaNative.nativeVocalStop();
        }
    }

    @JvmStatic
    public static final float vocalLevel() {
        return initialized ? MediaNative.nativeVocalLevel() : -1.0f;
    }

    @JvmStatic
    public static final int vocalFrames() {
        return initialized ? MediaNative.nativeVocalFrames() : -1;
    }

    @JvmStatic
    public static final boolean setMuted(@Nullable String process, boolean muted) {
        CharSequence charSequence;
        return initialized && !((charSequence = (CharSequence)process) == null || charSequence.length() == 0) && MediaNative.nativeSetSessionMuted(process, muted);
    }

    @JvmStatic
    public static final void reloadLyrics() {
        LyricsService.forget(track);
        lyrics = Lyrics.EMPTY;
        settleTrack = null;
        pendingTrack = track;
        pending = LyricsService.request(track);
    }

    private final double clock() {
        if (!initialized) {
            clockValid = false;
            return 0.0;
        }
        long now = System.nanoTime();
        double reported = MediaNative.nativePositionMillis();
        if (!clockValid) {
            clockMillis = reported;
            clockNanos = now;
            clockValid = true;
            return clockMillis;
        }
        double elapsed = Math.max(0.0, (double)(now - clockNanos) / 1000000.0);
        clockNanos = now;
        boolean running = MediaStatus.Companion.of(MediaNative.nativeStatus()).playing();
        if (running) {
            clockMillis += elapsed;
        }
        double drift = reported - clockMillis;
        if (!running || Math.abs(drift) >= 300.0) {
            clockMillis = reported;
        } else if (elapsed > 0.0) {
            clockMillis += drift * Math.min(1.0, elapsed / 450.0);
        }
        long duration = MediaNative.nativeDurationMillis();
        if (duration > 0L) {
            clockMillis = Math.max(0.0, Math.min((double)duration, clockMillis));
        }
        return clockMillis;
    }

    private final void refreshTrack() {
        MediaTrack updated = new MediaTrack(this.value(MediaNative.nativeTitle()), this.value(MediaNative.nativeArtist()), this.value(MediaNative.nativeAlbumTitle()), this.value(MediaNative.nativeAlbumArtist()), MediaNative.nativeTrackNumber(), this.value(MediaNative.nativeAppId()), MediaNative.nativeDurationMillis());
        if (Intrinsics.areEqual((Object)updated, (Object)track)) {
            return;
        }
        track = updated;
        clockValid = false;
        lyrics = Lyrics.EMPTY;
        reportedLine = Integer.MIN_VALUE;
        MediaLog.note("client", "track '" + updated.display() + "' album='" + updated.albumTitle() + "' app='" + updated.appId() + "' len=" + updated.durationMillis() + " ad=" + MediaPlayer.isAdvertisement());
        pending = null;
        settleTrack = updated.isEmpty() ? null : updated;
        settleStamp = System.currentTimeMillis();
    }

    private final void refreshCover() {
        this.releaseCover();
        byte[] payload = MediaNative.nativeThumbnail();
        if (payload == null || payload.length == 0) {
            return;
        }
        try {
            NativeImage nativeImage2 = NativeImage.read((byte[])payload);
            Intrinsics.checkNotNullExpressionValue((Object)nativeImage2, (String)"read(...)");
            NativeImage image = nativeImage2;
            coverAccent = this.dominantColor(image);
            coverWidth = image.getWidth();
            coverHeight = image.getHeight();
            Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)("dynamic/media_cover_" + Long.toHexString(thumbnailVersion & 0xFFFFFFFL)));
            Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
            Identifier id = identifier2;
            MinecraftClient.getInstance().getTextureManager().registerTexture(id, (AbstractTexture)new NativeImageBackedTexture(MediaPlayer::refreshCover$lambda$0, image));
            cover = id;
        }
        catch (Throwable failure) {
            cover = null;
            coverAccent = 0;
            coverWidth = 0;
            coverHeight = 0;
        }
    }

    private final void releaseCover() {
        Identifier current = cover;
        if (current != null) {
            try {
                MinecraftClient.getInstance().getTextureManager().destroyTexture(current);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            cover = null;
        }
        coverAccent = 0;
        coverWidth = 0;
        coverHeight = 0;
    }

    private final int dominantColor(NativeImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        if (width <= 0 || height <= 0) {
            return 0;
        }
        int stride = Math.max(1, Math.min(width, height) / 32);
        long red = 0L;
        long green = 0L;
        long blue = 0L;
        long total = 0L;
        for (int y = 0; y < height; y += stride) {
            for (int x = 0; x < width; x += stride) {
                int argb = image.getColorArgb(x, y);
                int r = argb >> 16 & 0xFF;
                int g = argb >> 8 & 0xFF;
                int b = argb & 0xFF;
                int high = Math.max(r, Math.max(g, b));
                int low = Math.min(r, Math.min(g, b));
                long weight = 1L + (long)(high - low) * (long)high / 255L;
                red += (long)r * weight;
                green += (long)g * weight;
                blue += (long)b * weight;
                total += weight;
            }
        }
        if (total == 0L) {
            return 0;
        }
        int r = (int)(red / total);
        int g = (int)(green / total);
        int b = (int)(blue / total);
        int high = Math.max(r, Math.max(g, b));
        if (high < 60) {
            return -7827288;
        }
        float boost = Math.min(3.0f, 225.0f / (float)high);
        r = Math.min(255, Math.round((float)r * boost));
        g = Math.min(255, Math.round((float)g * boost));
        b = Math.min(255, Math.round((float)b * boost));
        return 0xFF000000 | r << 16 | g << 8 | b;
    }

    private final String value(String raw) {
        String string = raw;
        if (string == null) {
            string = "";
        }
        return string;
    }

    private static final String refreshCover$lambda$0() {
        return "kimiko_media_cover";
    }

    static {
        revision = Long.MIN_VALUE;
        transcriptFallback = true;
        thumbnailVersion = Long.MIN_VALUE;
        track = MediaTrack.EMPTY;
        lyrics = Lyrics.EMPTY;
        reportedLine = Integer.MIN_VALUE;
        pendingTrack = MediaTrack.EMPTY;
    }
}

