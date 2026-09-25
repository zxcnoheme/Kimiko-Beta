/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.comparisons.ComparisonsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.media;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.utils.media.MediaNative;
import rtx.kimiko.utils.media.RadioScanner;
import rtx.kimiko.utils.media.RadioStation;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0019\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000e\u0010\rJ\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u000bH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0013\u0010\u0012\u001a\u00020\u0011H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0015\u001a\u00020\u0014H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0018\u001a\u00020\u0017H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0017H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001c\u0010\u0003J\u0013\u0010\u001d\u001a\u00020\u0014H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001d\u0010\u0016J\u0013\u0010\u001e\u001a\u00020\u0014H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001e\u0010\u0016J\u0013\u0010\u001f\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001f\u0010\u0003J\u0013\u0010 \u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b \u0010\u0003J\u0013\u0010!\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b!\u0010\u0003J\u001b\u0010#\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u0011H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b#\u0010$J\u001d\u0010#\u001a\u00020\u00062\b\u0010&\u001a\u0004\u0018\u00010%H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b#\u0010'J\u0013\u0010\"\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\"\u0010\u0003J\u0013\u0010(\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b(\u0010\u0003J\u0017\u0010*\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b*\u0010$J\u0013\u0010+\u001a\u00020\u0011H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b+\u0010\u0013J\u0013\u0010,\u001a\u00020%H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b,\u0010-J\u0013\u0010.\u001a\u00020%H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b.\u0010-J\u0013\u0010/\u001a\u00020%H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b/\u0010-J\u0013\u00100\u001a\u00020\u0014H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b0\u0010\u0016J\u0013\u00101\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b1\u0010\u0003J\u000f\u00102\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b2\u0010\u0003J\u000f\u00103\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b3\u0010\u0003J\u000f\u00104\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b4\u0010\u0003J\u0013\u00105\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b5\u0010\u0003J\u000f\u00106\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b6\u0010\u0003J\u0017\u00108\u001a\u00020\u00062\u0006\u00107\u001a\u00020%H\u0002\u00a2\u0006\u0004\b8\u0010'J\u000f\u00109\u001a\u00020%H\u0002\u00a2\u0006\u0004\b9\u0010-J\u000f\u0010;\u001a\u00020:H\u0002\u00a2\u0006\u0004\b;\u0010<J\u0019\u0010>\u001a\b\u0012\u0004\u0012\u00020%0=H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b>\u0010?J\u0019\u0010@\u001a\b\u0012\u0004\u0012\u00020%0=H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b@\u0010?J\u0019\u0010A\u001a\b\u0012\u0004\u0012\u00020%0=H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bA\u0010?R\u0014\u0010B\u001a\u00020\u00118\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020\u00118\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bD\u0010CR\u0014\u0010E\u001a\u00020\u00118\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bE\u0010CR\u0014\u0010F\u001a\u00020\u00118\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bF\u0010CR\u0014\u0010G\u001a\u00020\u00118\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bG\u0010CR\u0014\u0010H\u001a\u00020\u00178\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bH\u0010IR\u0014\u0010J\u001a\u00020\u00178\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bJ\u0010IR\u0014\u0010K\u001a\u00020\u00178\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bK\u0010IR\u001c\u0010N\u001a\n M*\u0004\u0018\u00010L0L8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0014\u0010P\u001a\u00020%8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bP\u0010QR\u0014\u0010S\u001a\u00020R8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bS\u0010TR\u001a\u0010U\u001a\b\u0012\u0004\u0012\u00020\u000b0=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bU\u0010VR$\u0010Y\u001a\u0012\u0012\u0004\u0012\u00020\u000b0Wj\b\u0012\u0004\u0012\u00020\u000b`X8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bY\u0010ZR\u0016\u0010[\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b[\u0010\\R\u0016\u0010]\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b]\u0010CR\u0016\u0010^\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010\\R\u0016\u0010\u001d\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\\R\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010IR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010_R\u0016\u0010`\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010CR\u0016\u0010a\u001a\u00020%8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010QR\u0016\u0010b\u001a\u00020%8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010QR\u0016\u0010c\u001a\u00020%8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010QR\u0016\u0010d\u001a\u00020R8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bd\u0010TR\u0016\u0010e\u001a\u00020R8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010TR\u001e\u0010f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bf\u0010gR\u0016\u0010h\u001a\u00020R8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bh\u0010T\u00a8\u0006i"}, d2={"Lrtx/kimiko/utils/media/Radio;", "", "<init>", "()V", "Ljava/lang/Runnable;", "value", "", "Lkotlin/jvm/JvmStatic;", "listener", "(Ljava/lang/Runnable;)V", "", "Lrtx/kimiko/utils/media/RadioStation;", "stations", "()Ljava/util/List;", "available", "current", "()Lrtx/kimiko/utils/media/RadioStation;", "", "currentIndex", "()I", "", "isActive", "()Z", "", "volume", "()F", "setVolume", "(F)V", "rescan", "paused", "play", "stop", "pause", "toggle", "next", "select", "(I)V", "", "nameOrId", "(Ljava/lang/String;)V", "previous", "direction", "step", "state", "title", "()Ljava/lang/String;", "stationName", "error", "isPlaying", "onScanUpdate", "poll", "markDead", "load", "saveNow", "save", "json", "write", "payload", "Ljava/nio/file/Path;", "file", "()Ljava/nio/file/Path;", "", "availableNames", "()[Ljava/lang/String;", "stationNames", "stationLabels", "STATE_IDLE", "I", "STATE_CONNECTING", "STATE_BUFFERING", "STATE_PLAYING", "STATE_ERROR", "BAND_MIN", "F", "BAND_MAX", "BROADCAST_MAX", "Lcom/google/gson/Gson;", "kotlin.jvm.PlatformType", "GSON", "Lcom/google/gson/Gson;", "FILE", "Ljava/lang/String;", "", "POLL_INTERVAL_MS", "J", "BUILT_IN", "[Lrtx/kimiko/utils/media/RadioStation;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "STATIONS", "Ljava/util/ArrayList;", "loaded", "Z", "index", "active", "Ljava/lang/Runnable;", "cachedState", "cachedTitle", "cachedStation", "cachedError", "lastPoll", "autoSkipAt", "availableCache", "Ljava/util/List;", "availableStamp", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nRadio.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Radio.kt\nrtx/kimiko/utils/media/Radio\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,581:1\n1174#2,2:582\n*S KotlinDebug\n*F\n+ 1 Radio.kt\nrtx/kimiko/utils/media/Radio\n*L\n480#1:582,2\n*E\n"})
public final class Radio {
    @NotNull
    public static final Radio INSTANCE = new Radio();
    public static final int STATE_IDLE = 0;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_BUFFERING = 2;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_ERROR = 4;
    public static final float BAND_MIN = 87.5f;
    public static final float BAND_MAX = 116.0f;
    public static final float BROADCAST_MAX = 108.0f;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @NotNull
    private static final String FILE = "radio.json";
    private static final long POLL_INTERVAL_MS = 250L;
    @NotNull
    private static final RadioStation[] BUILT_IN;
    @NotNull
    private static final ArrayList<RadioStation> STATIONS;
    private static boolean loaded;
    private static int index;
    private static boolean active;
    private static boolean paused;
    private static float volume;
    @Nullable
    private static Runnable listener;
    private static int cachedState;
    @NotNull
    private static String cachedTitle;
    @NotNull
    private static String cachedStation;
    @NotNull
    private static String cachedError;
    private static long lastPoll;
    private static long autoSkipAt;
    @Nullable
    private static List<RadioStation> availableCache;
    private static long availableStamp;

    private Radio() {
    }

    @JvmStatic
    public static final void listener(@Nullable Runnable value) {
        listener = value;
    }

    @JvmStatic
    @NotNull
    public static final List<RadioStation> stations() {
        INSTANCE.load();
        return STATIONS;
    }

    @JvmStatic
    @NotNull
    public static final List<RadioStation> available() {
        INSTANCE.load();
        long now = System.currentTimeMillis();
        List<RadioStation> cache2 = availableCache;
        if (cache2 != null && now - availableStamp < 600L) {
            return cache2;
        }
        ArrayList<RadioStation> list = new ArrayList<RadioStation>();
        Iterator<RadioStation> iterator = STATIONS.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<RadioStation> iterator2 = iterator;
        while (iterator2.hasNext()) {
            RadioStation station = (RadioStation) (iterator2.next());
            if (!RadioScanner.online(station.url())) continue;
            list.add(station);
        }
        if (list.isEmpty() && !RadioScanner.scanning()) {
            list.addAll((Collection)STATIONS);
        }
        List<RadioStation> copy = List.copyOf((Collection)list);
        availableCache = copy;
        availableStamp = now;
        Intrinsics.checkNotNull(copy);
        return copy;
    }

    @JvmStatic
    @Nullable
    public static final RadioStation current() {
        INSTANCE.load();
        if (STATIONS.isEmpty()) {
            return null;
        }
        return STATIONS.get(Math.max(0, Math.min(STATIONS.size() - 1, index)));
    }

    @JvmStatic
    public static final int currentIndex() {
        INSTANCE.load();
        return index;
    }

    @JvmStatic
    public static final boolean isActive() {
        return active;
    }

    @JvmStatic
    public static final float volume() {
        INSTANCE.load();
        return volume;
    }

    @JvmStatic
    public static final void setVolume(float value) {
        INSTANCE.load();
        volume = Math.max(0.0f, Math.min(1.0f, value));
        if (MediaNative.available()) {
            MediaNative.nativeRadioSetVolume(volume);
        }
        INSTANCE.save();
    }

    @JvmStatic
    public static final void rescan() {
        INSTANCE.load();
        RadioScanner.scan((Iterable<RadioStation>)STATIONS, true);
    }

    @JvmStatic
    public static final boolean paused() {
        return paused;
    }

    @JvmStatic
    public static final boolean play() {
        RadioStation station = Radio.current();
        if (station == null || !station.valid() || !MediaNative.available()) {
            return false;
        }
        boolean resumed = paused;
        paused = false;
        active = true;
        if (resumed) {
            INSTANCE.save();
        }
        cachedState = 1;
        cachedTitle = "";
        String string = station.name();
        if (string == null) {
            string = "";
        }
        cachedStation = string;
        cachedError = "";
        autoSkipAt = System.currentTimeMillis() + 12000L;
        MediaNative.nativeRadioSetVolume(volume);
        return MediaNative.nativeRadioPlay(station.url());
    }

    @JvmStatic
    public static final void stop() {
        active = false;
        cachedState = 0;
        cachedTitle = "";
        cachedError = "";
        autoSkipAt = 0L;
        if (MediaNative.available()) {
            MediaNative.nativeRadioStop();
        }
    }

    @JvmStatic
    public static final void pause() {
        paused = true;
        Radio.stop();
        Radio.saveNow();
    }

    @JvmStatic
    public static final void toggle() {
        if (active) {
            Radio.pause();
        } else {
            Radio.play();
        }
    }

    @JvmStatic
    public static final void select(int next) {
        INSTANCE.load();
        if (STATIONS.isEmpty()) {
            return;
        }
        int size = STATIONS.size();
        index = (next % size + size) % size;
        INSTANCE.save();
        if (active && !paused) {
            Radio.play();
        }
    }

    @JvmStatic
    public static final void select(@Nullable String nameOrId) {
        INSTANCE.load();
        int n = ((Collection)STATIONS).size();
        for (int i = 0; i < n; ++i) {
            RadioStation station = (RadioStation) (STATIONS.get(i));
            if (!Intrinsics.areEqual((Object)station.id(), (Object)nameOrId) && !Intrinsics.areEqual((Object)station.name(), (Object)nameOrId)) continue;
            Radio.select(i);
            return;
        }
    }

    @JvmStatic
    public static final void next() {
        INSTANCE.step(1);
    }

    @JvmStatic
    public static final void previous() {
        INSTANCE.step(-1);
    }

    private final void step(int direction) {
        int position;
        RadioStation station;
        this.load();
        if (STATIONS.isEmpty()) {
            return;
        }
        List<RadioStation> pool = Radio.available();
        if (pool.isEmpty()) {
            Radio.select(Radio.currentIndex() + direction);
            return;
        }
        RadioStation radioStation = station = Radio.current();
        int n = position = radioStation == null ? -1 : pool.indexOf(radioStation);
        if (position < 0) {
            RadioStation radioStation2 = station;
            float frequency = radioStation2 != null ? radioStation2.frequency() : 87.5f;
            position = 0;
            int n2 = ((Collection)pool).size();
            for (int i = 0; i < n2; ++i) {
                boolean match = direction > 0 ? pool.get(i).frequency() > frequency : pool.get(i).frequency() < frequency;
                if (!match) continue;
                position = i;
                break;
            }
            Radio.select(STATIONS.indexOf(pool.get(position)));
            return;
        }
        int size = pool.size();
        int target = ((position + direction) % size + size) % size;
        Radio.select(STATIONS.indexOf(pool.get(target)));
    }

    @JvmStatic
    public static final int state() {
        INSTANCE.poll();
        return active ? cachedState : 0;
    }

    @JvmStatic
    @NotNull
    public static final String title() {
        INSTANCE.poll();
        return cachedTitle;
    }

    @JvmStatic
    @NotNull
    public static final String stationName() {
        INSTANCE.poll();
        RadioStation station = Radio.current();
        if (!StringsKt.isBlank((CharSequence)cachedStation)) {
            return cachedStation;
        }
        return station != null ? station.name() : "";
    }

    @JvmStatic
    @NotNull
    public static final String error() {
        INSTANCE.poll();
        return cachedError;
    }

    @JvmStatic
    public static final boolean isPlaying() {
        return Radio.state() == 3;
    }

    @JvmStatic
    public static final void onScanUpdate() {
        block0: {
            availableCache = null;
            Runnable runnable = listener;
            if (runnable == null) break block0;
            runnable.run();
        }
    }

    private final void poll() {
        String error;
        String string;
        String station;
        CharSequence charSequence;
        if (!active || !MediaNative.available()) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - lastPoll < 250L) {
            return;
        }
        lastPoll = now;
        cachedState = MediaNative.nativeRadioState();
        String title = MediaNative.nativeRadioTitle();
        String string2 = title;
        if (string2 == null) {
            string2 = cachedTitle = "";
        }
        if (!((charSequence = (CharSequence)(station = MediaNative.nativeRadioStation())) == null || StringsKt.isBlank((CharSequence)charSequence))) {
            cachedStation = station;
        }
        if ((string = (error = MediaNative.nativeRadioError())) == null) {
            string = cachedError = "";
        }
        if (cachedState == 3) {
            autoSkipAt = 0L;
            return;
        }
        if (cachedState == 4) {
            this.markDead();
            return;
        }
        if (autoSkipAt > 0L && now > autoSkipAt) {
            this.markDead();
        }
    }

    private final void markDead() {
        List<RadioStation> pool;
        RadioStation station = Radio.current();
        autoSkipAt = 0L;
        if (station != null) {
            RadioScanner.markOffline(station.url());
        }
        if ((pool = Radio.available()).isEmpty() || pool.size() == 1 && CollectionsKt.contains((Iterable)pool, (Object)station)) {
            Radio.stop();
            return;
        }
        Radio.next();
    }

    private final void load() {
        block14: {
            int i;
            int n;
            Path path2;
            if (loaded) {
                return;
            }
            loaded = true;
            STATIONS.clear();
            CollectionsKt.addAll((Collection)STATIONS, (Object[])BUILT_IN);
            try {
                path2 = this.file();
                if (Files.isRegularFile(path2, new LinkOption[0])) {
                    JsonObject entry;
                    JsonArray array;
                    JsonObject root = JsonParser.parseString((String)Files.readString(path2, StandardCharsets.UTF_8)).getAsJsonObject();
                    if (root.has("volume")) {
                        volume = Math.max(0.0f, Math.min(1.0f, root.get("volume").getAsFloat()));
                    }
                    if (root.has("paused")) {
                        paused = root.get("paused").getAsBoolean();
                    }
                    if (root.has("stations")) {
                        array = root.getAsJsonArray("stations");
                        n = array.size();
                        for (i = 0; i < n; ++i) {
                            CharSequence charSequence;
                            String url;
                            entry = array.get(i).getAsJsonObject();
                            String name = entry.has("name") ? entry.get("name").getAsString() : null;
                            String string = url = entry.has("url") ? entry.get("url").getAsString() : null;
                            if (name == null || (charSequence = (CharSequence)url) == null || StringsKt.isBlank((CharSequence)charSequence)) continue;
                            String genre = entry.has("genre") ? entry.get("genre").getAsString() : "";
                            float frequency = entry.has("frequency") ? entry.get("frequency").getAsFloat() : 116.0f + (float)i * 0.6f;
                            STATIONS.add(new RadioStation("user:" + name, name, genre, url, frequency, true, false));
                        }
                    }
                    if (root.has("checked")) {
                        array = root.getAsJsonArray("checked");
                        n = array.size();
                        for (i = 0; i < n; ++i) {
                            entry = array.get(i).getAsJsonObject();
                            if (!entry.has("url")) continue;
                            RadioScanner.restore(entry.get("url").getAsString(), entry.has("online") && entry.get("online").getAsBoolean(), entry.has("stamp") ? entry.get("stamp").getAsLong() : 0L);
                        }
                    }
                }
            }
            catch (Throwable ignored) {
                // empty catch block
            }
            if (STATIONS.size() > 1) {
                STATIONS.sort(Comparator.comparingDouble(RadioStation::frequency));
            }
            try {
                JsonObject root;
                path2 = this.file();
                if (!Files.isRegularFile(path2, new LinkOption[0]) || !(root = JsonParser.parseString((String)Files.readString(path2, StandardCharsets.UTF_8)).getAsJsonObject()).has("station")) break block14;
                String id = root.get("station").getAsString();
                n = ((Collection)STATIONS).size();
                for (i = 0; i < n; ++i) {
                    if (!Intrinsics.areEqual((Object)STATIONS.get(i).id(), (Object)id)) continue;
                    index = i;
                    break;
                }
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
        RadioScanner.scan((Iterable<RadioStation>)STATIONS, false);
    }

    @JvmStatic
    public static final void saveNow() {
        if (!loaded) {
            return;
        }
        INSTANCE.write(INSTANCE.payload());
    }

    private final void save() {
        String json = this.payload();
        Thread thread = new Thread(() -> Radio.save$lambda$0(json), "kimiko-radio-config");
        thread.setDaemon(true);
        thread.start();
    }

    private final void write(String json) {
        try {
            Path path = this.file();
            Files.createDirectories(path.getParent(), new FileAttribute[0]);
            Files.writeString(path, (CharSequence)json, StandardCharsets.UTF_8, new OpenOption[0]);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private final String payload() {
        JsonObject root = new JsonObject();
        RadioStation station = Radio.current();
        Object object = station;
        if (object == null || (object = ((RadioStation)object).id()) == null) {
            object = "";
        }
        root.addProperty("station", (String)object);
        root.addProperty("volume", (Number)Float.valueOf(volume));
        root.addProperty("paused", Boolean.valueOf(paused));
        JsonArray stations = new JsonArray();
        JsonArray checked = new JsonArray();
        Iterator<RadioStation> iterator = STATIONS.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<RadioStation> iterator2 = iterator;
        while (iterator2.hasNext()) {
            JsonObject item;
            RadioStation entry = (RadioStation) (iterator2.next());
            if (!entry.builtIn()) {
                item = new JsonObject();
                item.addProperty("name", entry.name());
                item.addProperty("genre", entry.genre());
                item.addProperty("url", entry.url());
                item.addProperty("frequency", (Number)Float.valueOf(entry.frequency()));
                stations.add((JsonElement)item);
            }
            if (!RadioScanner.settled(entry.url())) continue;
            item = new JsonObject();
            item.addProperty("url", entry.url());
            item.addProperty("online", Boolean.valueOf(RadioScanner.online(entry.url())));
            item.addProperty("stamp", (Number)RadioScanner.stamp(entry.url()));
            checked.add((JsonElement)item);
        }
        root.add("stations", (JsonElement)stations);
        root.add("checked", (JsonElement)checked);
        String string = GSON.toJson((JsonElement)root);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toJson(...)");
        return string;
    }

    private final Path file() {
        Path path = ConfigManager.Companion.systemConfigDirectory().resolve(FILE);
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
        return path;
    }

    @JvmStatic
    @NotNull
    public static final String[] availableNames() {
        List<RadioStation> pool = Radio.available();
        int n = pool.size();
        String[] stringArray = new String[n];
        for (int i = 0; i < n; ++i) {
            int n2 = i;
            String string = pool.get(n2).name();
            if (string == null) {
                string = "";
            }
            stringArray[n2] = string;
        }
        return stringArray;
    }

    @JvmStatic
    @NotNull
    public static final String[] stationNames() {
        INSTANCE.load();
        int n = STATIONS.size();
        String[] stringArray = new String[n];
        for (int i = 0; i < n; ++i) {
            int n2 = i;
            String string = STATIONS.get(n2).name();
            if (string == null) {
                string = "";
            }
            stringArray[n2] = string;
        }
        return stringArray;
    }

    @JvmStatic
    @NotNull
    public static final String[] stationLabels() {
        INSTANCE.load();
        int n = STATIONS.size();
        String[] stringArray = new String[n];
        for (int i = 0; i < n; ++i) {
            RadioStation entry = STATIONS.get(i);
            stringArray[i] = entry.dial() + " \u00b7 " + entry.name();
        }
        return stringArray;
    }

    private static final void save$lambda$0(String $json) {
        INSTANCE.write($json);
    }

    static {
        RadioStation[] radioStationArray = new RadioStation[]{new RadioStation("retro", "Ретро FM", "хиты 80-90х", "https://retroserver.streamr.ru:8043/retro256.mp3", 88.3f), new RadioStation("jazz", "Радио Jazz", "джаз", "https://nashe1.hostingradio.ru/jazz-128.mp3", 89.1f), new RadioStation("dorojnoe", "Дорожное радио", "шансон", "https://dorognoe.hostingradio.ru:8000/dorognoe", 96.0f), new RadioStation("vesti", "Вести FM", "разговорное", "https://icecast-vgtrk.cdnvideo.ru/vestifm_mp3_192kbps", 97.6f), new RadioStation("ultra", "Радио Ultra", "альтернатива", "https://nashe1.hostingradio.ru/ultra-128.mp3", 100.5f), new RadioStation("dfm", "DFM", "танцевальное", "https://dfm.hostingradio.ru/dfm96.aacp", 101.2f), new RadioStation("nashe", "Наше Радио", "русский рок", "https://nashe1.hostingradio.ru/nashe-128.mp3", 101.8f), new RadioStation("maximum", "Радио Maximum", "рок", "https://maximum.hostingradio.ru/maximum96.aacp", 103.7f), new RadioStation("rusradio", "Русское Радио", "поп", "https://rusradio.hostingradio.ru/rusradio128.mp3", 105.7f), new RadioStation("europa", "Европа Плюс", "поп", "https://ep128.hostingradio.ru:8030/ep128", 106.2f), new RadioStation("record", "Radio Record", "танцевальное", "https://radiorecord.hostingradio.ru/rr_main96.aacp", 106.3f), new RadioStation("roks", "Radio ROKS", "рок", "https://online.radioroks.ua/RadioROKS_HD", 103.6f), new RadioStation("kiss", "Kiss FM", "танцевальное", "https://online.kissfm.ua/KissFM_Digital", 100.0f), new RadioStation("record_deep", "Record Deep", "deep house", "https://radiorecord.hostingradio.ru/deep96.aacp", 108.4f), new RadioStation("record_trance", "Trancemission", "trance", "https://radiorecord.hostingradio.ru/tm96.aacp", 109.0f), new RadioStation("record_chill", "Record Chillout", "chillout", "https://radiorecord.hostingradio.ru/chil96.aacp", 109.6f), new RadioStation("groove_salad", "Groove Salad", "downtempo", "https://ice1.somafm.com/groovesalad-128-mp3", 110.4f), new RadioStation("drone_zone", "Drone Zone", "ambient", "https://ice1.somafm.com/dronezone-128-mp3", 111.0f), new RadioStation("lush", "SomaFM Lush", "dream pop", "https://ice1.somafm.com/lush-128-mp3", 111.6f), new RadioStation("secret_agent", "Secret Agent", "spy jazz", "https://ice1.somafm.com/secretagent-128-mp3", 112.2f), new RadioStation("beat_blender", "Beat Blender", "deep house", "https://ice1.somafm.com/beatblender-128-mp3", 112.8f), new RadioStation("paradise", "Radio Paradise", "eclectic", "https://stream.radioparadise.com/mp3-128", 113.6f), new RadioStation("paradise_mellow", "RP Mellow Mix", "mellow", "https://stream.radioparadise.com/mellow-128", 114.2f), new RadioStation("chillhop", "FluxFM Chillhop", "lo-fi", "https://streams.fluxfm.de/Chillhop/mp3-320/streams.fluxfm.de/", 115.0f), new RadioStation("ilove", "I Love Radio", "поп", "https://streams.ilovemusic.de/iloveradio1.mp3", 115.6f)};
        BUILT_IN = radioStationArray;
        STATIONS = new ArrayList();
        volume = 0.5f;
        cachedTitle = "";
        cachedStation = "";
        cachedError = "";
    }
}

