package rtx.kimiko.utils.media;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jna.Library;
import com.sun.jna.Native;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.*;
import java.util.regex.Pattern;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class MediaNative {
    @NotNull
    public static final MediaNative INSTANCE = new MediaNative();

    public static final int CAN_PLAY = 1;
    public static final int CAN_PAUSE = 2;
    public static final int CAN_NEXT = 4;
    public static final int CAN_PREVIOUS = 8;
    public static final int CAN_STOP = 16;
    public static final int CAN_SEEK = 32;
    public static final int CAN_SHUFFLE = 64;
    public static final int CAN_REPEAT = 128;
    public static final int REPEAT_NONE = 0;
    public static final int REPEAT_TRACK = 1;
    public static final int REPEAT_LIST = 2;

    @NotNull
    private static final String RESOURCE = "/assets/kimiko/natives/OptMedia.dll";
    @Nullable
    private static Boolean available;
    private static OptMediaLibrary optMedia;

    private static volatile String currentTitle = "";
    private static volatile String currentArtist = "";
    private static volatile String currentAppId = "";
    private static volatile boolean isPlaying = false;
    private static volatile long currentDurationMs = 0L;
    private static volatile long currentPositionMs = 0L;
    private static volatile long revision = 1L;
    private static volatile long thumbnailVersion = 1L;
    private static volatile byte[] currentThumbnail = null;
    private static volatile int lastThumbnailHash = 0;

    private static volatile ScheduledExecutorService poller;
    private static volatile boolean transcriptFallback = true;

    private MediaNative() {
    }

    @JvmStatic
    public static synchronized boolean available() {
        if (available == null) {
            String os = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);
            available = os.contains("win") && load();
        }
        return available != null && available;
    }

    private static synchronized boolean load() {
        if (optMedia != null) {
            return true;
        }
        try {
            InputStream in = MediaNative.class.getResourceAsStream(RESOURCE);
            if (in == null) {
                Path devPath = Path.of("src/main/resources" + RESOURCE);
                if (Files.exists(devPath)) {
                    in = Files.newInputStream(devPath);
                } else {
                    Path absDevPath = Path.of("G:/KimikoSRC/src/main/resources" + RESOURCE);
                    if (Files.exists(absDevPath)) {
                        in = Files.newInputStream(absDevPath);
                    } else {
                        Path buildPath = Path.of("build/resources/main" + RESOURCE);
                        if (Files.exists(buildPath)) {
                            in = Files.newInputStream(buildPath);
                        } else {
                            return false;
                        }
                    }
                }
            }
            Path tempDll = Files.createTempFile("OptMedia_", ".dll");
            tempDll.toFile().deleteOnExit();
            Files.copy(in, tempDll, StandardCopyOption.REPLACE_EXISTING);
            in.close();

            Map<String, Object> options = new HashMap<>();
            options.put(Library.OPTION_ALLOW_OBJECTS, Boolean.TRUE);
            optMedia = (OptMediaLibrary) Native.load(tempDll.toAbsolutePath().toString(), OptMediaLibrary.class, options);
            return optMedia != null;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }

    @JvmStatic
    public static synchronized boolean nativeInit() {
        if (!available() || optMedia == null) {
            return false;
        }
        if (poller == null || poller.isShutdown()) {
            poller = Executors.newSingleThreadScheduledExecutor(r -> {
                Thread t = new Thread(r, "Kimiko-Media-Poller");
                t.setDaemon(true);
                return t;
            });
            poller.scheduleWithFixedDelay(MediaNative::poll, 0L, 200L, TimeUnit.MILLISECONDS);
        }
        return true;
    }

    @JvmStatic
    public static synchronized void nativeShutdown() {
        if (poller != null) {
            poller.shutdownNow();
            poller = null;
        }
        if (optMedia != null) {
            try {
                optMedia.Deinitialize();
            } catch (Throwable ignored) {}
        }
        currentTitle = "";
        currentArtist = "";
        currentAppId = "";
        isPlaying = false;
        currentDurationMs = 0L;
        currentPositionMs = 0L;
        currentThumbnail = null;
    }

    private static void poll() {
        if (optMedia == null) return;
        try {
            OptMediaArtist artist = new OptMediaArtist();
            boolean ok = optMedia.GetCurrentMediaInfo(artist);
            if (ok) {
                String t = artist.getTitle();
                String a = artist.getArtist();
                String app = artist.getSource();
                boolean playing = artist.isPlaying();
                long dur = Math.max(0L, artist.durationMs);
                long pos = Math.max(0L, artist.positionMs);
                byte[] art = artist.getAlbumArt();

                boolean trackChanged = !Objects.equals(t, currentTitle)
                        || !Objects.equals(a, currentArtist)
                        || playing != isPlaying
                        || dur != currentDurationMs;

                if (trackChanged) {
                    currentTitle = t;
                    currentArtist = a;
                    currentAppId = app;
                    isPlaying = playing;
                    currentDurationMs = dur;
                    currentPositionMs = pos;
                    revision++;
                } else {
                    currentPositionMs = pos;
                }

                if (art != null && art.length > 0) {
                    int h = Arrays.hashCode(art);
                    if (h != lastThumbnailHash) {
                        lastThumbnailHash = h;
                        currentThumbnail = art;
                        thumbnailVersion++;
                    }
                } else if (currentThumbnail != null && (t == null || t.isEmpty())) {
                    currentThumbnail = null;
                    lastThumbnailHash = 0;
                    thumbnailVersion++;
                }

                optMedia.FreeMediaInfo(artist);
            } else {
                if (isPlaying) {
                    isPlaying = false;
                    revision++;
                }
            }
        } catch (Throwable ignored) {
        }
    }

    @JvmStatic
    public static boolean nativeIsReady() {
        return optMedia != null;
    }

    @JvmStatic
    public static boolean nativeIsActive() {
        return optMedia != null && isPlaying;
    }

    @JvmStatic
    public static long nativeRevision() {
        return revision;
    }

    @JvmStatic
    @Nullable
    public static String nativeTitle() {
        return currentTitle;
    }

    @JvmStatic
    @Nullable
    public static String nativeArtist() {
        return currentArtist;
    }

    @JvmStatic
    @Nullable
    public static String nativeAlbumTitle() {
        return "";
    }

    @JvmStatic
    @Nullable
    public static String nativeAlbumArtist() {
        return currentArtist;
    }

    @JvmStatic
    @Nullable
    public static String nativeAppId() {
        return currentAppId;
    }

    @JvmStatic
    public static int nativeTrackNumber() {
        return 0;
    }

    @JvmStatic
    public static int nativeStatus() {
        if (currentTitle == null || currentTitle.isEmpty()) {
            return 0; // CLOSED
        }
        return isPlaying ? 4 : 5; // 4 = PLAYING, 5 = PAUSED
    }

    @JvmStatic
    public static int nativeCapabilities() {
        return CAN_PLAY | CAN_PAUSE | CAN_NEXT | CAN_PREVIOUS | CAN_SEEK;
    }

    @JvmStatic
    public static long nativeDurationMillis() {
        return currentDurationMs;
    }

    @JvmStatic
    public static long nativePositionMillis() {
        return currentPositionMs;
    }

    @JvmStatic
    public static boolean nativeShuffle() {
        return false;
    }

    @JvmStatic
    public static int nativeRepeat() {
        return REPEAT_NONE;
    }

    @JvmStatic
    public static long nativeThumbnailVersion() {
        return thumbnailVersion;
    }

    @JvmStatic
    @Nullable
    public static byte[] nativeThumbnail() {
        return currentThumbnail;
    }

    @JvmStatic
    @Nullable
    public static String[] nativeSessions() {
        return currentAppId.isEmpty() ? new String[0] : new String[]{ currentAppId };
    }

    @JvmStatic
    @Nullable
    public static String nativeSelectedSession() {
        return currentAppId;
    }

    @JvmStatic
    public static void nativeSelectSession(@Nullable String appId) {
    }

    @JvmStatic
    public static void nativePlay() {
        if (optMedia != null) {
            optMedia.Play();
        }
    }

    @JvmStatic
    public static void nativePause() {
        if (optMedia != null) {
            optMedia.Pause();
        }
    }

    @JvmStatic
    public static void nativeToggle() {
        if (optMedia != null) {
            optMedia.TogglePlayPause();
        }
    }

    @JvmStatic
    public static void nativeStop() {
        if (optMedia != null) {
            optMedia.Pause();
        }
    }

    @JvmStatic
    public static void nativeNext() {
        if (optMedia != null) {
            optMedia.SkipNext();
        }
    }

    @JvmStatic
    public static void nativePrevious() {
        if (optMedia != null) {
            optMedia.SkipPrevious();
        }
    }

    @JvmStatic
    public static void nativeSeek(long millis) {
        if (optMedia != null) {
            optMedia.SeekToMs(millis);
            currentPositionMs = millis;
        }
    }

    @JvmStatic
    public static void nativeSkip(long delta) {
        if (optMedia != null) {
            long target = Math.max(0L, currentPositionMs + delta);
            optMedia.SeekToMs(target);
            currentPositionMs = target;
        }
    }

    @JvmStatic
    public static void nativeSetShuffle(boolean value) {
    }

    @JvmStatic
    public static void nativeSetRepeat(int mode) {
    }

    @JvmStatic
    public static boolean nativeIsAdvertisement() {
        return false;
    }

    @JvmStatic
    public static boolean nativeSetSessionMuted(@Nullable String process, boolean muted) {
        return false;
    }

    @JvmStatic
    public static int nativeSessionMuted(@Nullable String process) {
        return 0;
    }

    @JvmStatic
    public static boolean nativeVocalStart(@Nullable String process) {
        return false;
    }

    @JvmStatic
    public static boolean nativeVocalStartPid(int pid) {
        return false;
    }

    @JvmStatic
    public static void nativeVocalStop() {
    }

    @JvmStatic
    public static float nativeVocalLevel() {
        return -1.0f;
    }

    @JvmStatic
    public static int nativeVocalFrames() {
        return -1;
    }

    @JvmStatic
    public static float nativeVocalRawPeak() {
        return 0.0f;
    }

    @JvmStatic
    public static int nativeVocalFlags() {
        return 0;
    }

    @JvmStatic
    public static boolean nativeLogTo(@Nullable String path) {
        return true;
    }

    @JvmStatic
    public static void nativeLogNote(@Nullable String tag, @Nullable String message) {
    }

    @JvmStatic
    public static void nativeSetTranscriptFallback(boolean value) {
        transcriptFallback = value;
    }

    @JvmStatic
    @Nullable
    public static String nativeFetchLyrics(@Nullable String title, @Nullable String artist, @Nullable String album, int durationSeconds) {
        if (title == null || title.isBlank()) {
            return null;
        }
        try {
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(4))
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();

            String cleanArtist = cleanArtistName(artist);
            String cleanTitle = cleanLyricsTitle(title, cleanArtist);

            // 1. If artist is known and not generic, try exact get
            if (!cleanArtist.isBlank() && !cleanArtist.equalsIgnoreCase("Mix") && !cleanArtist.equalsIgnoreCase("YouTube")) {
                String exactUrl = "https://lrclib.net/api/get?track_name=" + URLEncoder.encode(cleanTitle, StandardCharsets.UTF_8)
                        + "&artist_name=" + URLEncoder.encode(cleanArtist, StandardCharsets.UTF_8)
                        + (durationSeconds > 0 ? "&duration=" + durationSeconds : "");
                String res = queryLrcLib(client, exactUrl, false, durationSeconds);
                if (res != null && !res.isBlank()) {
                    return res;
                }

                // Also try searching with track + artist together
                String searchArtistUrl = "https://lrclib.net/api/search?q=" + URLEncoder.encode(cleanTitle + " " + cleanArtist, StandardCharsets.UTF_8);
                res = queryLrcLib(client, searchArtistUrl, true, durationSeconds);
                if (res != null && !res.isBlank()) {
                    return res;
                }
            }

            // 2. Try search with cleanTitle
            String searchUrl = "https://lrclib.net/api/search?q=" + URLEncoder.encode(cleanTitle, StandardCharsets.UTF_8);
            String res = queryLrcLib(client, searchUrl, true, durationSeconds);
            if (res != null && !res.isBlank()) {
                return res;
            }

            // 3. If title contains dash (common in YouTube: Artist - Track), split and search
            if (title.contains("-") || title.contains("–") || title.contains("—")) {
                String[] parts = title.split("[-–—]", 2);
                if (parts.length == 2) {
                    String p0 = cleanArtistName(parts[0].trim());
                    String p1 = cleanLyricsTitle(parts[1].trim(), null);

                    String searchUrl2 = "https://lrclib.net/api/search?q=" + URLEncoder.encode(p0 + " " + p1, StandardCharsets.UTF_8);
                    res = queryLrcLib(client, searchUrl2, true, durationSeconds);
                    if (res != null && !res.isBlank()) {
                        return res;
                    }

                    String exactUrl2 = "https://lrclib.net/api/get?track_name=" + URLEncoder.encode(p1, StandardCharsets.UTF_8)
                            + "&artist_name=" + URLEncoder.encode(p0, StandardCharsets.UTF_8);
                    res = queryLrcLib(client, exactUrl2, false, durationSeconds);
                    if (res != null && !res.isBlank()) {
                        return res;
                    }

                    // Try searching p1 alone (track name without artist)
                    if (!p1.isBlank() && p1.length() >= 3) {
                        String searchUrlP1 = "https://lrclib.net/api/search?q=" + URLEncoder.encode(p1, StandardCharsets.UTF_8);
                        res = queryLrcLib(client, searchUrlP1, true, durationSeconds);
                        if (res != null && !res.isBlank()) {
                            return res;
                        }
                    }
                }
            }
        } catch (Throwable t) {
            MediaLog.note("lyrics", "fetch error: " + t.getMessage());
        }
        return null;
    }

    private static String cleanLyricsTitle(String title, @Nullable String cleanArtist) {
        if (title == null) return "";
        String s = title.replaceAll("[⋆★☆•·*]", " ")
                        .replaceAll("\\[[^\\]]*\\]", " ")
                        .replaceAll("\\([^\\)]*\\)", " ")
                        .replaceAll("(?i)\\b(official\\s+(video|audio|music\\s+video)|lyrics|lyric\\s+video|remix|hd|4k|ft\\.?.*|feat\\.?.*)\\b", " ")
                        .replaceAll("\\s+", " ")
                        .trim();
        if (cleanArtist != null && !cleanArtist.isBlank()) {
            String prefix = Pattern.quote(cleanArtist) + "\\s*[-–—:]\\s*";
            String stripped = s.replaceFirst("(?i)^" + prefix, "").trim();
            if (!stripped.isEmpty()) {
                s = stripped;
            }
        }
        return s.isEmpty() ? title.trim() : s;
    }

    private static String cleanArtistName(String artist) {
        if (artist == null) return "";
        String s = artist.replaceAll("[⋆★☆•·*]", " ")
                         .replaceAll("(?i)\\s*-\\s*topic$", "")
                         .replaceAll("(?i)\\s+topic$", "")
                         .replaceAll("(?i)\\s*vevo$", "")
                         .replaceAll("\\[[^\\]]*\\]", " ")
                         .replaceAll("\\([^\\)]*\\)", " ")
                         .replaceAll("\\s+", " ")
                         .trim();
        return s.isEmpty() ? artist.trim() : s;
    }

    @Nullable
    private static String plainToLrc(@Nullable String plain, int durationSeconds) {
        if (plain == null || plain.isBlank()) return null;
        String[] rawLines = plain.split("\\R");
        List<String> validLines = new ArrayList<>();
        Pattern sectionPattern = Pattern.compile("^\\[[A-Za-z\\s0-9_\\-:]+\\]$");
        for (String l : rawLines) {
            String trimmed = l.trim();
            if (!trimmed.isEmpty() && !sectionPattern.matcher(trimmed).matches()) {
                validLines.add(trimmed);
            }
        }
        if (validLines.isEmpty()) return null;

        int dur = durationSeconds > 0 ? durationSeconds : Math.max(60, validLines.size() * 4);
        double intro = Math.min(12.0, Math.max(4.0, dur * 0.07));
        double outro = Math.min(12.0, Math.max(4.0, dur * 0.07));
        double singTime = Math.max(10.0, dur - intro - outro);

        int totalWeight = 0;
        int[] weights = new int[validLines.size()];
        for (int i = 0; i < validLines.size(); i++) {
            weights[i] = Math.max(1, validLines.get(i).length());
            totalWeight += weights[i];
        }

        StringBuilder sb = new StringBuilder();
        int cumWeight = 0;
        for (int i = 0; i < validLines.size(); i++) {
            double t = intro + ((double) cumWeight / totalWeight) * singTime;
            int m = (int) (t / 60.0);
            double s = t % 60.0;
            sb.append(String.format(Locale.ROOT, "[%02d:%05.2f]%s\n", m, s, validLines.get(i)));
            cumWeight += weights[i];
        }
        return sb.toString();
    }

    private static String queryLrcLib(HttpClient client, String url, boolean isArray, int durationSeconds) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "KimikoClient/1.0 (https://github.com)")
                    .timeout(Duration.ofSeconds(5))
                    .GET()
                    .build();
            HttpResponse<String> resp = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            if (resp.statusCode() == 200) {
                String body = resp.body();
                if (body == null || body.isBlank()) return null;

                if (isArray) {
                    JsonArray arr = JsonParser.parseString(body).getAsJsonArray();
                    // 1. Try to find syncedLyrics first
                    for (JsonElement e : arr) {
                        if (e.isJsonObject()) {
                            JsonObject o = e.getAsJsonObject();
                            if (o.has("syncedLyrics") && !o.get("syncedLyrics").isJsonNull()) {
                                String synced = o.get("syncedLyrics").getAsString();
                                if (synced != null && !synced.isBlank()) return synced;
                            }
                        }
                    }
                    // 2. If no syncedLyrics found, take plainLyrics and synthesize timestamps
                    for (JsonElement e : arr) {
                        if (e.isJsonObject()) {
                            JsonObject o = e.getAsJsonObject();
                            if (o.has("plainLyrics") && !o.get("plainLyrics").isJsonNull()) {
                                String plain = o.get("plainLyrics").getAsString();
                                if (plain != null && !plain.isBlank()) {
                                    return plainToLrc(plain, durationSeconds);
                                }
                            }
                        }
                    }
                } else {
                    JsonObject o = JsonParser.parseString(body).getAsJsonObject();
                    if (o.has("syncedLyrics") && !o.get("syncedLyrics").isJsonNull()) {
                        String synced = o.get("syncedLyrics").getAsString();
                        if (synced != null && !synced.isBlank()) return synced;
                    }
                    if (o.has("plainLyrics") && !o.get("plainLyrics").isJsonNull()) {
                        String plain = o.get("plainLyrics").getAsString();
                        if (plain != null && !plain.isBlank()) {
                            return plainToLrc(plain, durationSeconds);
                        }
                    }
                }
            }
        } catch (Throwable ignored) {
        }
        return null;
    }

    @JvmStatic
    public static boolean nativeRadioPlay(@Nullable String url) {
        return false;
    }

    @JvmStatic
    public static void nativeRadioStop() {
    }

    @JvmStatic
    public static int nativeRadioState() {
        return 0;
    }

    @JvmStatic
    @Nullable
    public static String nativeRadioTitle() {
        return "";
    }

    @JvmStatic
    @Nullable
    public static String nativeRadioStation() {
        return "";
    }

    @JvmStatic
    @Nullable
    public static String nativeRadioError() {
        return "";
    }

    @JvmStatic
    public static void nativeRadioSetVolume(float volume) {
    }

    @JvmStatic
    public static float nativeRadioVolume() {
        return 1.0f;
    }
}
