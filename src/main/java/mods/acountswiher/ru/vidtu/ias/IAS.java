/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package mods.acountswiher.ru.vidtu.ias;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import mods.acountswiher.ru.vidtu.ias.config.IASConfig;
import mods.acountswiher.ru.vidtu.ias.config.IASStorage;
import mods.acountswiher.ru.vidtu.ias.utils.Holder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import recovery.privacy.NetworkPolicy;

public final class IAS {
    @NotNull
    public static final String CLIENT_ID = "54fd49e4-2103-4044-9603-2b028c814ec3";
    @NotNull
    public static final Duration TIMEOUT = Duration.ofSeconds(Long.getLong("ias.timeout", 15L));
    @NotNull
    public static final String USER_AGENT = "IAS/%s (https://github.com/The-Fireplace-Minecraft-Mods/In-Game-Account-Switcher; pig@vidtu.ru)".formatted(IAS.class.getPackage().getImplementationVersion());
    @Nullable
    private static ScheduledExecutorService executor;
    private static Path gameDirectory;
    private static Path configDirectory;
    private static boolean disabled;

    @Contract(value="-> fail", pure=true)
    private IAS() {
        throw new AssertionError((Object)"No instances.");
    }

    public static void init(@NotNull Path gamePath, @NotNull Path configPath) {
        gameDirectory = gamePath;
        configDirectory = configPath;
        try {
            IAS.disclaimersStorage();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        try {
            IAS.loadConfig();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        try {
            IAS.loadStorage();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        executor = Executors.newSingleThreadScheduledExecutor(r -> new Thread(r, "IAS"));
        if (Boolean.getBoolean("ias.skipDisableScanning")) {
            return;
        }
        String version = String.valueOf(IAS.class.getPackage().getImplementationVersion());
        Holder task = new Holder();
        task.set(executor.scheduleWithFixedDelay(() -> {
            try {
                if (disabled || Boolean.getBoolean("ias.skipDisableScanning")) {
                    return;
                }
                HttpClient client = NetworkPolicy.redirects(HttpClient.newBuilder().connectTimeout(TIMEOUT).version(HttpClient.Version.HTTP_2), HttpClient.Redirect.NORMAL).executor(Runnable::run).build();
                HttpResponse<Stream<String>> response = NetworkPolicy.send(client, HttpRequest.newBuilder().uri(new URI("https://raw.githubusercontent.com/The-Fireplace-Minecraft-Mods/In-Game-Account-Switcher/main/.ias/disabled_v1")).header("User-Agent", USER_AGENT).timeout(TIMEOUT).GET().build(), HttpResponse.BodyHandlers.ofLines());
                int code = response.statusCode();
                if (code < 200 || code > 299) {
                    return;
                }
                boolean bl = disabled = disabled || response.body().anyMatch(line -> "ALL".equalsIgnoreCase(line = line.strip()) || version.equalsIgnoreCase((String)line));
                if (!disabled) {
                    return;
                }
                ScheduledFuture actualTask = (ScheduledFuture)task.get();
                if (actualTask == null) {
                    return;
                }
                actualTask.cancel(false);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }, 0L, 60L, TimeUnit.MINUTES));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void close() {
        block7: {
            try {
                ScheduledExecutorService executor = IAS.executor;
                if (executor == null) break block7;
                executor.shutdown();
                if (!executor.awaitTermination(30L, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                    if (!executor.awaitTermination(30L, TimeUnit.SECONDS)) {
                        // empty if block
                    }
                }
            }
            catch (InterruptedException e) {
                ScheduledExecutorService executor = IAS.executor;
                if (executor != null) {
                    executor.shutdownNow();
                }
                Thread.currentThread().interrupt();
            }
        }
        executor = null;
        if (gameDirectory == null) return;
        try {
            IAS.disclaimersStorage();
            return;
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @Contract(pure=true)
    @NotNull
    public static ScheduledExecutorService executor() {
        ScheduledExecutorService executor = IAS.executor;
        Objects.requireNonNull(executor, "IAS executor is not available.");
        return executor;
    }

    @Contract(pure=true)
    @NotNull
    public static Path configDirectory() {
        Path configDirectory = IAS.configDirectory;
        Objects.requireNonNull(configDirectory, "IAS config directory is not available.");
        return configDirectory;
    }

    @Contract(pure=true)
    public static boolean disabled() {
        return disabled;
    }

    public static void loadConfig() {
        IASConfig.load(configDirectory);
    }

    public static void saveConfig() {
        IASConfig.save(configDirectory);
    }

    public static void loadStorage() {
        IASStorage.load(gameDirectory);
    }

    public static void saveStorage() {
        IASStorage.save(gameDirectory);
    }

    public static void disclaimersStorage() {
        IASStorage.disclaimers(gameDirectory);
    }

    public static void gameDisclaimerShownStorage() {
        IASStorage.gameDisclaimerShown(gameDirectory);
    }

    static {
        disabled = false;
    }
}

