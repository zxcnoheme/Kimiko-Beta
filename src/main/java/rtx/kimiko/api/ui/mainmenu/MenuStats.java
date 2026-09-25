/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.mainmenu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.lang.management.ManagementFactory;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.lang.I18n;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u0013\u0010\b\u001a\u00020\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\u0013\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\fJ\u0013\u0010\u000e\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\fJ\u001b\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0003J\u000f\u0010\u0019\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010\"\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010$\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010#R\u0016\u0010%\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0016\u0010'\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u0010!R\u0016\u0010(\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010!R\u0016\u0010)\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010!\u00a8\u0006*"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuStats;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "touch", "", "launches", "()I", "", "totalSeconds", "()J", "sessionSeconds", "firstLaunch", "seconds", "", "formatDuration", "(J)Ljava/lang/String;", "", "force", "flush", "(Z)V", "load", "Ljava/nio/file/Path;", "file", "()Ljava/nio/file/Path;", "Lcom/google/gson/Gson;", "GSON", "Lcom/google/gson/Gson;", "FILE", "Ljava/lang/String;", "FLUSH_INTERVAL_MS", "J", "loaded", "Z", "counted", "launchCount", "I", "baseSeconds", "firstLaunchMs", "lastFlush", "rtx.kimiko:kimiko"})
public final class MenuStats {
    @NotNull
    public static final MenuStats INSTANCE = new MenuStats();
    @NotNull
    private static final Gson GSON;
    @NotNull
    private static final String FILE = "mainmenu_stats.json";
    private static final long FLUSH_INTERVAL_MS = 20000L;
    private static boolean loaded;
    private static boolean counted;
    private static int launchCount;
    private static long baseSeconds;
    private static long firstLaunchMs;
    private static long lastFlush;

    private MenuStats() {
    }

    @JvmStatic
    public static final void touch() {
        long now;
        INSTANCE.load();
        if (!counted) {
            counted = true;
            int n = launchCount;
            launchCount = n + 1;
            if (firstLaunchMs <= 0L) {
                firstLaunchMs = System.currentTimeMillis();
            }
            INSTANCE.flush(true);
        }
        if ((now = System.currentTimeMillis()) - lastFlush > 20000L) {
            INSTANCE.flush(true);
        }
    }

    @JvmStatic
    public static final int launches() {
        INSTANCE.load();
        return launchCount;
    }

    @JvmStatic
    public static final long totalSeconds() {
        INSTANCE.load();
        return baseSeconds + MenuStats.sessionSeconds();
    }

    @JvmStatic
    public static final long sessionSeconds() {
        long l;
        try {
            l = ManagementFactory.getRuntimeMXBean().getUptime() / 1000L;
        }
        catch (Throwable ignored) {
            l = 0L;
        }
        return l;
    }

    @JvmStatic
    public static final long firstLaunch() {
        INSTANCE.load();
        return firstLaunchMs;
    }

    @JvmStatic
    @NotNull
    public static final String formatDuration(long seconds) {
        long hours = seconds / 3600L;
        long minutes = seconds % 3600L / 60L;
        if (hours > 0L) {
            Object[] objectArray = new Object[]{hours, minutes};
            return I18n.tr("%dч %dм", objectArray);
        }
        Object[] objectArray = new Object[]{minutes, seconds % 60L};
        return I18n.tr("%dм %dс", objectArray);
    }

    private final void flush(boolean force) {
        if (!force) {
            return;
        }
        lastFlush = System.currentTimeMillis();
        JsonObject root = new JsonObject();
        root.addProperty("launches", (Number)launchCount);
        root.addProperty("seconds", (Number)(baseSeconds + MenuStats.sessionSeconds()));
        root.addProperty("first", (Number)firstLaunchMs);
        String json = GSON.toJson((JsonElement)root);
        Thread thread = new Thread(() -> MenuStats.flush$lambda$0(json), "kimiko-menu-stats");
        thread.setDaemon(true);
        thread.start();
    }

    private final void load() {
        if (loaded) {
            return;
        }
        loaded = true;
        try {
            Path path = this.file();
            if (!Files.isRegularFile(path, new LinkOption[0])) {
                return;
            }
            JsonObject root = JsonParser.parseString((String)Files.readString(path, StandardCharsets.UTF_8)).getAsJsonObject();
            if (root.has("launches")) {
                launchCount = root.get("launches").getAsInt();
            }
            if (root.has("seconds")) {
                baseSeconds = root.get("seconds").getAsLong();
            }
            if (root.has("first")) {
                firstLaunchMs = root.get("first").getAsLong();
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private final Path file() {
        Path path = ConfigManager.Companion.systemConfigDirectory().resolve(FILE);
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
        return path;
    }

    private static final void flush$lambda$0(String $json) {
        try {
            Path path = INSTANCE.file();
            Files.createDirectories(path.getParent(), new FileAttribute[0]);
            Files.writeString(path, (CharSequence)$json, StandardCharsets.UTF_8, new OpenOption[0]);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    static {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Intrinsics.checkNotNullExpressionValue((Object)gson, (String)"create(...)");
        GSON = gson;
    }
}

