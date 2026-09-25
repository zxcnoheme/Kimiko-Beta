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
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.io.CloseableKt
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.jvm.internal.StringCompanionObject
 *  kotlin.text.Regex
 *  kotlin.text.StringsKt
 *  net.fabricmc.loader.api.FabricLoader
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.Reader;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.config.ConfigLibrary;
import rtx.kimiko.api.drags.DragSystem;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.module.ModuleToggleEvent;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.ClickGui;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.MultiSelectSetting;
import rtx.kimiko.api.modules.settings.impl.PositionSettings;
import rtx.kimiko.api.modules.settings.impl.RangeSliderSetting;
import rtx.kimiko.api.modules.settings.impl.SelectSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.modules.settings.impl.TextSetting;
import rtx.kimiko.api.ui.theme.Theme;
import rtx.kimiko.api.ui.theme.ThemeManager;
import rtx.kimiko.api.ui.theme.ThemeWave;
import rtx.kimiko.utils.key.KeyBind;
import rtx.kimiko.utils.storage.RepositoryStorage;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0003b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001b\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\nH\u0003b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\fR\u0016\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000fR\u0016\u0010\u0011\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u000fR\u0016\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/config/ConfigManager;", "", "<init>", "()V", "Lrtx/kimiko/api/events/impl/module/ModuleToggleEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onModuleToggle", "(Lrtx/kimiko/api/events/impl/module/ModuleToggleEvent;)V", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "", "initialized", "Z", "loading", "dirty", "", "lastAutosaveMs", "J", "", "lastSavedModuleConfig", "Ljava/lang/String;", "Lcom/google/gson/JsonObject;", "activeRoot", "Lcom/google/gson/JsonObject;", "Companion", "rtx.kimiko:kimiko"})
public final class ConfigManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private boolean initialized;
    private boolean loading;
    private boolean dirty;
    private long lastAutosaveMs;
    @Nullable
    private String lastSavedModuleConfig;
    @NotNull
    private JsonObject activeRoot = new JsonObject();
    @NotNull
    private static final Gson GSON;
    @NotNull
    private static final ConfigManager INSTANCE;
    @NotNull
    private static final String MODULE_CONFIG = "autocfg";
    @NotNull
    private static final String CONFIG_EXTENSION = ".kimiko";
    @NotNull
    private static final String PREVIOUS_CONFIG_EXTENSION = ".tria";
    private static final long AUTOSAVE_INTERVAL_MS = 5000L;
    @NotNull
    private static final ExecutorService IO_EXECUTOR;

    private ConfigManager() {
    }

    @EventHandler
    private final void onModuleToggle(ModuleToggleEvent event) {
        this.dirty = true;
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        if (!event.isPost()) {
            return;
        }
        if (!this.dirty) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - this.lastAutosaveMs < 5000L) {
            return;
        }
        this.lastAutosaveMs = now;
        ConfigManager.Companion.saveModules(false);
    }

    private static final Thread IO_EXECUTOR$lambda$0(Runnable r) {
        Thread thread = new Thread(r, "kimiko-config-io");
        thread.setDaemon(true);
        return thread;
    }

    @JvmStatic
    @NotNull
    public static final Path userConfigDirectory() {
        return Companion.userConfigDirectory();
    }

    @JvmStatic
    @NotNull
    public static final Path systemConfigDirectory() {
        return Companion.systemConfigDirectory();
    }

    @JvmStatic
    public static final void init() {
        Companion.init();
    }

    @JvmStatic
    public static final void loadAll() {
        Companion.loadAll();
    }

    @JvmStatic
    public static final void saveAll() {
        Companion.saveAll();
    }

    @JvmStatic
    public static final void markDirty() {
        Companion.markDirty();
    }

    @JvmStatic
    public static final boolean isLoading() {
        return Companion.isLoading();
    }

    @JvmStatic
    public static final void applyActiveDrags() {
        Companion.applyActiveDrags();
    }

    @JvmStatic
    @NotNull
    public static final List<String> listProfiles() {
        return Companion.listProfiles();
    }

    @JvmStatic
    @NotNull
    public static final JsonObject currentRoot() {
        return Companion.currentRoot();
    }

    @JvmStatic
    @Nullable
    public static final JsonObject readProfileRoot(@NotNull String name) {
        return Companion.readProfileRoot(name);
    }

    @JvmStatic
    public static final long profileModified(@NotNull String name) {
        return Companion.profileModified(name);
    }

    @JvmStatic
    public static final int rootEnabledCount(@Nullable JsonObject root) {
        return Companion.rootEnabledCount(root);
    }

    @JvmStatic
    public static final void applyRootTransient(@Nullable JsonObject root) {
        Companion.applyRootTransient(root);
    }

    @JvmStatic
    public static final boolean writeProfile(@Nullable String name, @Nullable JsonObject root) {
        return Companion.writeProfile(name, root);
    }

    @JvmStatic
    public static final boolean writeProfile(@Nullable String name, @Nullable JsonObject root, @Nullable Runnable onDone) {
        return Companion.writeProfile(name, root, onDone);
    }

    @JvmStatic
    public static final boolean renameProfile(@Nullable String from, @Nullable String to) {
        return Companion.renameProfile(from, to);
    }

    @JvmStatic
    public static final void saveProfile(@Nullable String name) {
        Companion.saveProfile(name);
    }

    @JvmStatic
    public static final void loadProfile(@NotNull String name) {
        Companion.loadProfile(name);
    }

    @JvmStatic
    public static final void deleteProfile(@NotNull String name) {
        Companion.deleteProfile(name);
    }

    @JvmStatic
    public static final boolean profileExists(@NotNull String name) {
        return Companion.profileExists(name);
    }

    @JvmStatic
    public static final void resetToFactory() {
        Companion.resetToFactory();
    }

    @JvmStatic
    public static final int profileEnabledCount(@NotNull String name) {
        return Companion.profileEnabledCount(name);
    }

    @JvmStatic
    @NotNull
    public static final String nextProfileName() {
        return Companion.nextProfileName();
    }

    @JvmStatic
    @NotNull
    public static final Path profilesDirectory() {
        return Companion.profilesDirectory();
    }

    static {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Intrinsics.checkNotNullExpressionValue((Object)gson, (String)"create(...)");
        GSON = gson;
        INSTANCE = new ConfigManager();
        ExecutorService executorService = Executors.newSingleThreadExecutor(ConfigManager::IO_EXECUTOR$lambda$0);
        Intrinsics.checkNotNullExpressionValue((Object)executorService, (String)"newSingleThreadExecutor(...)");
        IO_EXECUTOR = executorService;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J\u0013\u0010\n\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u0003J\u0013\u0010\u000b\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\u0003J\u0013\u0010\f\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\u0003J\u0013\u0010\r\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u0003J\u0013\u0010\u000f\u001a\u00020\u000eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0003J\u000f\u0010\u0012\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0003J\u0017\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J#\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u00182\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001cJ\u0017\u0010\u001e\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010 \u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b \u0010\u001fJ\u0013\u0010!\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b!\u0010\u0003J\u0019\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b$\u0010%J\u0013\u0010&\u001a\u00020\u0016H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b&\u0010\u001cJ\u001d\u0010(\u001a\u0004\u0018\u00010\u00162\u0006\u0010'\u001a\u00020#H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b(\u0010)J\u001b\u0010+\u001a\u00020*2\u0006\u0010'\u001a\u00020#H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b+\u0010,J\u001d\u0010.\u001a\u00020-2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b.\u0010/J\u001d\u00100\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b0\u0010\u001fJ'\u00101\u001a\u00020\u000e2\b\u0010'\u001a\u0004\u0018\u00010#2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b1\u00102J1\u00101\u001a\u00020\u000e2\b\u0010'\u001a\u0004\u0018\u00010#2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u00104\u001a\u0004\u0018\u000103H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b1\u00105J'\u00108\u001a\u00020\u000e2\b\u00106\u001a\u0004\u0018\u00010#2\b\u00107\u001a\u0004\u0018\u00010#H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b8\u00109J\u001d\u0010:\u001a\u00020\t2\b\u0010'\u001a\u0004\u0018\u00010#H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b:\u0010;J\u001b\u0010<\u001a\u00020\t2\u0006\u0010'\u001a\u00020#H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b<\u0010;J\u001b\u0010=\u001a\u00020\t2\u0006\u0010'\u001a\u00020#H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b=\u0010;J\u001b\u0010>\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020#H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b>\u0010?J\u0013\u0010@\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b@\u0010\u0003J\u0017\u0010C\u001a\u00020\t2\u0006\u0010B\u001a\u00020AH\u0002\u00a2\u0006\u0004\bC\u0010DJ\u001b\u0010E\u001a\u00020-2\u0006\u0010'\u001a\u00020#H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\bE\u0010FJ\u0013\u0010G\u001a\u00020#H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\bG\u0010HJ\u0013\u0010I\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\bI\u0010\u0007J\u0019\u0010J\u001a\u00020\u00042\b\u0010'\u001a\u0004\u0018\u00010#H\u0002\u00a2\u0006\u0004\bJ\u0010KJ\u0019\u0010L\u001a\u00020\u00042\b\u0010'\u001a\u0004\u0018\u00010#H\u0002\u00a2\u0006\u0004\bL\u0010KJ\u0019\u0010M\u001a\u00020#2\b\u0010'\u001a\u0004\u0018\u00010#H\u0002\u00a2\u0006\u0004\bM\u0010NJ\u0017\u0010Q\u001a\u00020\u00162\u0006\u0010P\u001a\u00020OH\u0002\u00a2\u0006\u0004\bQ\u0010RJ;\u0010V\u001a\u00020#2\"\u0010U\u001a\u001e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020-0Sj\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020-`T2\u0006\u0010'\u001a\u00020#H\u0002\u00a2\u0006\u0004\bV\u0010WJ\u0019\u0010Y\u001a\u0004\u0018\u00010X2\u0006\u0010B\u001a\u00020AH\u0002\u00a2\u0006\u0004\bY\u0010ZJ\u001f\u0010\\\u001a\u00020\t2\u0006\u0010P\u001a\u00020O2\u0006\u0010[\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\\\u0010]J\u001f\u0010_\u001a\u00020\t2\u0006\u0010B\u001a\u00020A2\u0006\u0010^\u001a\u00020XH\u0002\u00a2\u0006\u0004\b_\u0010`J\u000f\u0010a\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\ba\u0010\u001cJ!\u0010d\u001a\u0004\u0018\u00010\u00162\u0006\u0010b\u001a\u00020\u00162\u0006\u0010c\u001a\u00020#H\u0002\u00a2\u0006\u0004\bd\u0010eJ!\u0010h\u001a\u0004\u0018\u00010X2\u0006\u0010f\u001a\u00020\u00162\u0006\u0010g\u001a\u00020#H\u0002\u00a2\u0006\u0004\bh\u0010iJ\u000f\u0010j\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bj\u0010\u0007J\u0017\u0010l\u001a\u00020\u00162\u0006\u0010k\u001a\u00020#H\u0002\u00a2\u0006\u0004\bl\u0010)J'\u0010o\u001a\u00020\u000e2\u0006\u0010m\u001a\u00020\u00162\u0006\u0010k\u001a\u00020#2\u0006\u0010n\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\bo\u0010pJ'\u0010q\u001a\u00020-2\u0006\u0010m\u001a\u00020\u00162\u0006\u0010k\u001a\u00020#2\u0006\u0010n\u001a\u00020-H\u0002\u00a2\u0006\u0004\bq\u0010rR\u0014\u0010t\u001a\u00020s8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bt\u0010uR\u0014\u0010w\u001a\u00020v8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bw\u0010xR\u0014\u0010y\u001a\u00020#8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\by\u0010zR\u0014\u0010{\u001a\u00020#8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b{\u0010zR\u0014\u0010|\u001a\u00020#8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b|\u0010zR\u0014\u0010}\u001a\u00020*8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b}\u0010~R\u0017\u0010\u0080\u0001\u001a\u00020\u007f8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001\u00a8\u0006\u0082\u0001"}, d2={"Lrtx/kimiko/api/config/ConfigManager.Companion;", "", "<init>", "()V", "Ljava/nio/file/Path;", "Lkotlin/jvm/JvmStatic;", "userConfigDirectory", "()Ljava/nio/file/Path;", "systemConfigDirectory", "", "init", "loadAll", "saveAll", "markDirty", "", "isLoading", "()Z", "ensureDefaultSystemFiles", "loadModules", "force", "saveModules", "(Z)V", "Lcom/google/gson/JsonObject;", "root", "Ljava/util/concurrent/Future;", "submitWrite", "(Lcom/google/gson/JsonObject;Z)Ljava/util/concurrent/Future;", "buildModuleRoot", "()Lcom/google/gson/JsonObject;", "currentDrags", "applyModuleRoot", "(Lcom/google/gson/JsonObject;)V", "applyDrags", "applyActiveDrags", "", "", "listProfiles", "()Ljava/util/List;", "currentRoot", "name", "readProfileRoot", "(Ljava/lang/String;)Lcom/google/gson/JsonObject;", "", "profileModified", "(Ljava/lang/String;)J", "", "rootEnabledCount", "(Lcom/google/gson/JsonObject;)I", "applyRootTransient", "writeProfile", "(Ljava/lang/String;Lcom/google/gson/JsonObject;)Z", "Ljava/lang/Runnable;", "onDone", "(Ljava/lang/String;Lcom/google/gson/JsonObject;Ljava/lang/Runnable;)Z", "from", "to", "renameProfile", "(Ljava/lang/String;Ljava/lang/String;)Z", "saveProfile", "(Ljava/lang/String;)V", "loadProfile", "deleteProfile", "profileExists", "(Ljava/lang/String;)Z", "resetToFactory", "Lrtx/kimiko/api/modules/settings/Setting;", "setting", "resetSetting", "(Lrtx/kimiko/api/modules/settings/Setting;)V", "profileEnabledCount", "(Ljava/lang/String;)I", "nextProfileName", "()Ljava/lang/String;", "profilesDirectory", "profilePath", "(Ljava/lang/String;)Ljava/nio/file/Path;", "previousProfilePath", "sanitizeProfileName", "(Ljava/lang/String;)Ljava/lang/String;", "Lrtx/kimiko/api/modules/Module;", "module", "writeSettings", "(Lrtx/kimiko/api/modules/Module;)Lcom/google/gson/JsonObject;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "occurrences", "occurrenceKey", "(Ljava/util/HashMap;Ljava/lang/String;)Ljava/lang/String;", "Lcom/google/gson/JsonElement;", "serializeSetting", "(Lrtx/kimiko/api/modules/settings/Setting;)Lcom/google/gson/JsonElement;", "savedSettings", "applySettings", "(Lrtx/kimiko/api/modules/Module;Lcom/google/gson/JsonObject;)V", "saved", "applySetting", "(Lrtx/kimiko/api/modules/settings/Setting;Lcom/google/gson/JsonElement;)V", "readModuleConfig", "modules", "moduleName", "findModuleObject", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonObject;", "settings", "settingName", "findSettingValue", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonElement;", "moduleConfigPath", "key", "objectWithArray", "object", "fallback", "readBoolean", "(Lcom/google/gson/JsonObject;Ljava/lang/String;Z)Z", "readInt", "(Lcom/google/gson/JsonObject;Ljava/lang/String;I)I", "Lcom/google/gson/Gson;", "GSON", "Lcom/google/gson/Gson;", "Lrtx/kimiko/api/config/ConfigManager;", "INSTANCE", "Lrtx/kimiko/api/config/ConfigManager;", "MODULE_CONFIG", "Ljava/lang/String;", "CONFIG_EXTENSION", "PREVIOUS_CONFIG_EXTENSION", "AUTOSAVE_INTERVAL_MS", "J", "Ljava/util/concurrent/ExecutorService;", "IO_EXECUTOR", "Ljava/util/concurrent/ExecutorService;", "rtx.kimiko:kimiko"})
    @SourceDebugExtension(value={"SMAP\nConfigManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConfigManager.kt\nrtx/kimiko/api/config/ConfigManager.Companion\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,860:1\n37#2,2:861\n37#2,2:866\n2945#3,3:863\n1#4:868\n*S KotlinDebug\n*F\n+ 1 ConfigManager.kt\nrtx/kimiko/api/config/ConfigManager.Companion\n*L\n551#1:861,2\n754#1:866,2\n606#1:863,3\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final Path userConfigDirectory() {
            return RepositoryStorage.configRoot();
        }

        @JvmStatic
        @NotNull
        public final Path systemConfigDirectory() {
            return RepositoryStorage.root();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        public final void init() {
            if (INSTANCE.initialized) {
                return;
            }
            INSTANCE.initialized = true;
            RepositoryStorage.migratePreviousFormat();
            this.ensureDefaultSystemFiles();
            INSTANCE.loading = true;
            try {
                this.loadAll();
            }
            finally {
                INSTANCE.loading = false;
            }
            this.saveModules(true);
            EventBus.Companion.get().subscribe(INSTANCE);
        }

        @JvmStatic
        public final void loadAll() {
            this.loadModules();
        }

        @JvmStatic
        public final void saveAll() {
            try {
                JsonObject root = this.buildModuleRoot();
                INSTANCE.dirty = false;
                this.submitWrite(root, true).get(5L, TimeUnit.SECONDS);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }

        @JvmStatic
        public final void markDirty() {
            if (INSTANCE.initialized && !INSTANCE.loading) {
                INSTANCE.dirty = true;
            }
        }

        @JvmStatic
        public final boolean isLoading() {
            return INSTANCE.loading;
        }

        private final void ensureDefaultSystemFiles() {
            RepositoryStorage.ensureObject("friends", this.objectWithArray("list"));
            RepositoryStorage.ensureObject("waypoints", this.objectWithArray("ways"));
            RepositoryStorage.ensureObject("macros", this.objectWithArray("macros"));
            RepositoryStorage.ensureObject("staff", this.objectWithArray("list"));
            JsonObject prefix = new JsonObject();
            prefix.addProperty("prefix", ".");
            RepositoryStorage.ensureObject("prefix", prefix);
        }

        private final void loadModules() {
            this.applyModuleRoot(this.readModuleConfig());
        }

        private final void saveModules(boolean force) {
            if (!force && !INSTANCE.dirty) {
                return;
            }
            try {
                JsonObject root = this.buildModuleRoot();
                INSTANCE.dirty = false;
                this.submitWrite(root, force);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }

        private final Future<?> submitWrite(JsonObject root, boolean force) {
            Future<?> future = IO_EXECUTOR.submit(() -> Companion.submitWrite$lambda$0(root, force));
            Intrinsics.checkNotNullExpressionValue(future, (String)"submit(...)");
            return future;
        }

        private final JsonObject buildModuleRoot() {
            JsonObject root = new JsonObject();
            root.addProperty("version", (Number)1);
            root.addProperty("theme", ThemeManager.current().name());
            JsonObject modules = new JsonObject();
            for (Module module : ModuleManager.Companion.get().getAll()) {
                JsonObject entry = new JsonObject();
                entry.addProperty("enabled", Boolean.valueOf(module.isEnabled() || ModuleManager.Companion.get().isSuspended(module)));
                entry.addProperty("bind", (Number)module.getBind().getCode());
                entry.addProperty("bindMode", module.getBindMode().name());
                entry.addProperty("bindType", module.getBindType().name());
                entry.add("settings", (JsonElement)this.writeSettings(module));
                modules.add(module.getName(), (JsonElement)entry);
            }
            root.add("modules", (JsonElement)modules);
            root.add("drags", (JsonElement)this.currentDrags());
            return root;
        }

        private final JsonObject currentDrags() {
            JsonObject live = DragSystem.Companion.get().writeDrags();
            if (!live.entrySet().isEmpty()) {
                return live;
            }
            if (INSTANCE.activeRoot.has("drags") && INSTANCE.activeRoot.get("drags").isJsonObject()) {
                JsonObject jsonObject = INSTANCE.activeRoot.getAsJsonObject("drags").deepCopy();
                Intrinsics.checkNotNullExpressionValue((Object)jsonObject, (String)"deepCopy(...)");
                return jsonObject;
            }
            return live;
        }

        private final void applyModuleRoot(JsonObject root) {
            JsonObject saved;
            INSTANCE.activeRoot = root;
            if (root.has("theme") && root.get("theme").isJsonPrimitive()) {
                try {
                    String string = root.get("theme").getAsString();
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
                    Theme parsed = Theme.valueOf(string);
                    ThemeWave.cancel();
                    ThemeManager.set(parsed);
                }
                catch (Exception parsed) {
                    // empty catch block
                }
            }
            JsonObject modules = root.has("modules") && root.get("modules").isJsonObject() ? root.getAsJsonObject("modules") : new JsonObject();
            for (Module module : ModuleManager.Companion.get().getAll()) {
                String string;
                Intrinsics.checkNotNull((Object)modules);
                saved = this.findModuleObject(modules, module.getName());
                if (saved == null) continue;
                if (saved.has("bind")) {
                    module.setBind(new KeyBind(this.readInt(saved, "bind", KeyBind.NONE.getCode())));
                }
                if (saved.has("bindMode")) {
                    try {
                        string = saved.get("bindMode").getAsString();
                        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
                        module.setBindMode(Module.BindMode.valueOf(string));
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
                if (saved.has("bindType")) {
                    try {
                        string = saved.get("bindType").getAsString();
                        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
                        module.setBindType(Module.BindType.valueOf(string));
                    }
                    catch (Exception exception) {}
                } else {
                    module.setBindType(Module.BindType.KEY);
                }
                if (!saved.has("settings") || !saved.get("settings").isJsonObject()) continue;
                JsonObject jsonObject = saved.getAsJsonObject("settings");
                Intrinsics.checkNotNullExpressionValue((Object)jsonObject, (String)"getAsJsonObject(...)");
                this.applySettings(module, jsonObject);
            }
            for (Module module : ModuleManager.Companion.get().getAll()) {
                Intrinsics.checkNotNull((Object)modules);
                saved = this.findModuleObject(modules, module.getName());
                module.setEnabled(saved != null ? this.readBoolean(saved, "enabled", module.defaultEnabled()) : module.defaultEnabled());
            }
            this.applyDrags(root);
        }

        private final void applyDrags(JsonObject root) {
            if (root.has("drags") && root.get("drags").isJsonObject()) {
                DragSystem.Companion.get().applyDrags(root.getAsJsonObject("drags"));
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        public final void applyActiveDrags() {
            INSTANCE.loading = true;
            try {
                this.applyDrags(INSTANCE.activeRoot);
            }
            finally {
                INSTANCE.loading = false;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        @NotNull
        public final List<String> listProfiles() {
            Path dir = this.profilesDirectory();
            if (!Files.exists(dir, new LinkOption[0])) {
                return new ArrayList<>();
            }
            ArrayList<String> names = new ArrayList<>();
            try (Stream<Path> stream = Files.list(dir)) {
                stream.forEach(p -> {
                    String file = p.getFileName().toString();
                    if (file.endsWith(ConfigManager.CONFIG_EXTENSION)) {
                        String string = file.substring(0, file.length() - ConfigManager.CONFIG_EXTENSION.length());
                        names.add(string);
                    } else if (file.endsWith(ConfigManager.PREVIOUS_CONFIG_EXTENSION)) {
                        String string = file.substring(0, file.length() - ConfigManager.PREVIOUS_CONFIG_EXTENSION.length());
                        Path replacement = p.resolveSibling(string + ConfigManager.CONFIG_EXTENSION);
                        if (!Files.exists(replacement, new LinkOption[0])) {
                            names.add(string);
                        }
                    }
                });
            } catch (Exception exception) {
                // empty catch block
            }
            names.sort(String.CASE_INSENSITIVE_ORDER);
            return names;
        }

        @JvmStatic
        @NotNull
        public final JsonObject currentRoot() {
            JsonObject jsonObject;
            try {
                jsonObject = this.buildModuleRoot();
            }
            catch (Exception ex) {
                jsonObject = new JsonObject();
            }
            return jsonObject;
        }

        @JvmStatic
        @Nullable
        public final JsonObject readProfileRoot(@NotNull String name) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            Path file = this.profilePath(name);
            if (!Files.exists(file, new LinkOption[0])) {
                file = this.previousProfilePath(name);
            }
            if (!Files.exists(file, new LinkOption[0])) {
                return null;
            }
            try (BufferedReader reader = Files.newBufferedReader(file)) {
                JsonElement parsed = JsonParser.parseReader((Reader)reader);
                return parsed != null && parsed.isJsonObject() ? parsed.getAsJsonObject() : null;
            } catch (Exception ex) {
                return null;
            }
        }

        @JvmStatic
        public final long profileModified(@NotNull String name) {
            long l;
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            Path file = this.profilePath(name);
            if (!Files.exists(file, new LinkOption[0])) {
                file = this.previousProfilePath(name);
            }
            try {
                l = Files.exists(file, new LinkOption[0]) ? Files.getLastModifiedTime(file, new LinkOption[0]).toMillis() : 0L;
            }
            catch (Exception ex) {
                l = 0L;
            }
            return l;
        }

        @JvmStatic
        public final int rootEnabledCount(@Nullable JsonObject root) {
            if (root == null || !root.has("modules") || !root.get("modules").isJsonObject()) {
                return 0;
            }
            JsonObject modules = root.getAsJsonObject("modules");
            int count = 0;
            for (String key : modules.keySet()) {
                if (!modules.get(key).isJsonObject()) continue;
                JsonObject jsonObject = modules.getAsJsonObject(key);
                Intrinsics.checkNotNullExpressionValue((Object)jsonObject, (String)"getAsJsonObject(...)");
                if (!this.readBoolean(jsonObject, "enabled", false)) continue;
                ++count;
            }
            return count;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        public final void applyRootTransient(@Nullable JsonObject root) {
            if (root == null) {
                return;
            }
            INSTANCE.loading = true;
            try {
                this.applyModuleRoot(root);
            }
            catch (Exception exception) {
            }
            finally {
                INSTANCE.loading = false;
            }
            this.saveModules(true);
        }

        @JvmStatic
        public final boolean writeProfile(@Nullable String name, @Nullable JsonObject root) {
            return this.writeProfile(name, root, null);
        }

        @JvmStatic
        public final boolean writeProfile(@Nullable String name, @Nullable JsonObject root, @Nullable Runnable onDone) {
            String safe = this.sanitizeProfileName(name);
            if (((CharSequence)safe).length() == 0 || root == null) {
                return false;
            }
            JsonObject copy = root.deepCopy();
            IO_EXECUTOR.submit(() -> Companion.writeProfile$lambda$0(safe, onDone, copy));
            return true;
        }

        @JvmStatic
        public final boolean renameProfile(@Nullable String from, @Nullable String to) {
            boolean bl;
            String safeFrom = this.sanitizeProfileName(from);
            String safeTo = this.sanitizeProfileName(to);
            if (((CharSequence)safeFrom).length() == 0 || ((CharSequence)safeTo).length() == 0 || StringsKt.equals((String)safeFrom, (String)safeTo, (boolean)true)) {
                return false;
            }
            if (this.profileExists(safeTo)) {
                return false;
            }
            try {
                Path source = this.profilePath(safeFrom);
                if (!Files.exists(source, new LinkOption[0])) {
                    source = this.previousProfilePath(safeFrom);
                }
                if (!Files.exists(source, new LinkOption[0])) {
                    return false;
                }
                Files.move(source, this.profilePath(safeTo), new CopyOption[0]);
                bl = true;
            }
            catch (Exception ex) {
                bl = false;
            }
            return bl;
        }

        @JvmStatic
        public final void saveProfile(@Nullable String name) {
            if (ConfigLibrary.Companion.get().savingBlocked()) {
                return;
            }
            String safe = this.sanitizeProfileName(name);
            if (((CharSequence)safe).length() == 0) {
                return;
            }
            JsonObject root = null;
            try {
                root = this.buildModuleRoot();
            }
            catch (Exception ex) {
                return;
            }
            JsonObject finalRoot = root;
            IO_EXECUTOR.submit(() -> Companion.saveProfile$lambda$0(safe, finalRoot));
        }

        @JvmStatic
        public final void loadProfile(@NotNull String name) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            Path file = this.profilePath(name);
            if (!Files.exists(file, new LinkOption[0])) {
                file = this.previousProfilePath(name);
            }
            if (!Files.exists(file, new LinkOption[0])) {
                return;
            }
            Path source = file;
            IO_EXECUTOR.submit(() -> Companion.loadProfile$lambda$0(source, name));
        }

        @JvmStatic
        public final void deleteProfile(@NotNull String name) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            try {
                Files.deleteIfExists(this.profilePath(name));
                Files.deleteIfExists(this.previousProfilePath(name));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }

        @JvmStatic
        public final boolean profileExists(@NotNull String name) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            return Files.exists(this.profilePath(name), new LinkOption[0]) || Files.exists(this.previousProfilePath(name), new LinkOption[0]);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        public final void resetToFactory() {
            ConfigLibrary.Companion.get().onLocalApplied(null);
            INSTANCE.loading = true;
            try {
                ThemeWave.cancel();
                ThemeManager.set(Theme.KIMIKO);
                for (Module module : ModuleManager.Companion.get().getAll()) {
                    for (Setting setting : module.getSettings().all()) {
                        this.resetSetting(setting);
                    }
                    if (module instanceof ClickGui) {
                        module.setBind(KeyBind.Companion.keyboard(344));
                    } else {
                        module.setBind(KeyBind.NONE);
                    }
                    module.setEnabled(module.defaultEnabled());
                }
                for (Draggable element : DragSystem.Companion.get().getAll()) {
                    element.resetToDefault();
                }
            }
            finally {
                INSTANCE.loading = false;
            }
            try {
                Files.deleteIfExists(this.moduleConfigPath());
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.saveModules(true);
        }

        private final void resetSetting(Setting setting) {
            try {
                Setting setting2 = setting;
                if (setting2 instanceof BooleanSetting) {
                    ((BooleanSetting)setting).setValue(((BooleanSetting)setting).getDefaultValue());
                } else if (setting2 instanceof SliderSetting) {
                    ((SliderSetting)setting).setValue(((SliderSetting)setting).getDefaultValue());
                } else if (setting2 instanceof RangeSliderSetting) {
                    ((RangeSliderSetting)setting).setValue(((RangeSliderSetting)setting).getDefaultMinValue(), ((RangeSliderSetting)setting).getDefaultMaxValue());
                } else if (setting2 instanceof SelectSetting) {
                    ((SelectSetting)setting).setSelected(((SelectSetting)setting).getDefaultSelected());
                } else if (setting2 instanceof MultiSelectSetting) {
                    MultiSelectSetting ms = (MultiSelectSetting)setting;
                    String[] stringArray = ms.getDefaultSelected().toArray(new String[0]);
                    ms.selected(Arrays.copyOf(stringArray, stringArray.length));
                } else if (setting2 instanceof ColorSetting) {
                    ((ColorSetting)setting).setColor(((ColorSetting)setting).getDefaultColor());
                } else if (setting2 instanceof TextSetting) {
                    ((TextSetting)setting).setText(((TextSetting)setting).getDefaultText());
                } else if (setting2 instanceof BindSetting) {
                    ((BindSetting)setting).setKey(((BindSetting)setting).getDefaultKey());
                } else if (setting2 instanceof PositionSettings) {
                    if (((PositionSettings)setting).isDefaultXCaptured()) {
                        ((PositionSettings)setting).setX(((PositionSettings)setting).getDefaultX());
                    }
                    if (((PositionSettings)setting).isDefaultYCaptured()) {
                        ((PositionSettings)setting).setY(((PositionSettings)setting).getDefaultY());
                    }
                    if (((PositionSettings)setting).isDefaultZCaptured()) {
                        ((PositionSettings)setting).setZ(((PositionSettings)setting).getDefaultZ());
                    }
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        public final int profileEnabledCount(@NotNull String name) {
            int n;
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            Path file = this.profilePath(name);
            if (!Files.exists(file, new LinkOption[0])) {
                file = this.previousProfilePath(name);
            }
            if (!Files.exists(file, new LinkOption[0])) {
                return 0;
            }
            try {
                int n2;
                Closeable closeable = Files.newBufferedReader(file);
                Throwable throwable = null;
                try {
                    BufferedReader reader = (BufferedReader)closeable;
                    boolean bl = false;
                    JsonElement parsed = JsonParser.parseReader((Reader)reader);
                    if (parsed == null || !parsed.isJsonObject()) {
                        int n3 = 0;
                        return n3;
                    }
                    JsonObject root = parsed.getAsJsonObject();
                    if (!root.has("modules") || !root.get("modules").isJsonObject()) {
                        int n4 = 0;
                        return n4;
                    }
                    JsonObject modules = root.getAsJsonObject("modules");
                    int count = 0;
                    for (String key : modules.keySet()) {
                        if (!modules.get(key).isJsonObject()) continue;
                        JsonObject jsonObject = modules.getAsJsonObject(key);
                        Intrinsics.checkNotNullExpressionValue((Object)jsonObject, (String)"getAsJsonObject(...)");
                        if (!Companion.readBoolean(jsonObject, "enabled", false)) continue;
                        ++count;
                    }
                    n2 = count;
                }
                catch (Throwable throwable2) {
                    throwable = throwable2;
                    throw throwable2;
                }
                finally {
                    CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
                }
                n = n2;
            }
            catch (Exception ex) {
                n = 0;
            }
            return n;
        }

        @JvmStatic
        @NotNull
        public final String nextProfileName() {
            List<String> existing = this.listProfiles();
            int i = 1;
            while (true) {
                boolean bl;
                String candidate;
                block5: {
                    candidate = "Config " + i;
                    Iterable $this$none$iv = existing;
                    boolean $i$f$none = false;
                    if ($this$none$iv instanceof Collection && ((Collection)$this$none$iv).isEmpty()) {
                        bl = true;
                    } else {
                        for (Object element$iv : $this$none$iv) {
                            String it = (String)element$iv;
                            boolean bl2 = false;
                            if (!StringsKt.equals((String)it, (String)candidate, (boolean)true)) continue;
                            bl = false;
                            break block5;
                        }
                        bl = true;
                    }
                }
                if (bl) {
                    return candidate;
                }
                ++i;
            }
        }

        @JvmStatic
        @NotNull
        public final Path profilesDirectory() {
            Path path = RepositoryStorage.root().resolve("profiles");
            Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
            return path;
        }

        private final Path profilePath(String name) {
            Path path = this.profilesDirectory().resolve(this.sanitizeProfileName(name) + ConfigManager.CONFIG_EXTENSION);
            Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
            return path;
        }

        private final Path previousProfilePath(String name) {
            Path path = this.profilesDirectory().resolve(this.sanitizeProfileName(name) + ConfigManager.PREVIOUS_CONFIG_EXTENSION);
            Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
            return path;
        }

        private final String sanitizeProfileName(String name) {
            if (name == null) {
                return "";
            }
            CharSequence charSequence = ((Object)StringsKt.trim((CharSequence)name)).toString();
            Regex regex = new Regex("[\\\\/:*?\"<>|]");
            String string = "_";
            return regex.replace(charSequence, string);
        }

        private final JsonObject writeSettings(Module module) {
            JsonObject settings = new JsonObject();
            HashMap<String, Integer> occurrences = new HashMap<String, Integer>();
            for (Setting setting : module.getSettings().all()) {
                String key = this.occurrenceKey(occurrences, setting.getName());
                JsonElement value = this.serializeSetting(setting);
                if (value == null) continue;
                settings.add(key, value);
            }
            return settings;
        }

        private final String occurrenceKey(HashMap<String, Integer> occurrences, String name) {
            Integer n = occurrences.get(name);
            int count = (n != null ? n : 0) + 1;
            ((Map)occurrences).put(name, count);
            return count == 1 ? name : name + "#" + count;
        }

        private final JsonElement serializeSetting(Setting setting) {
            if (setting instanceof BooleanSetting) {
                return GSON.toJsonTree((Object)((BooleanSetting)setting).getValue());
            }
            if (setting instanceof SliderSetting) {
                return GSON.toJsonTree((Object)Float.valueOf(((SliderSetting)setting).getFloat()));
            }
            if (setting instanceof RangeSliderSetting) {
                JsonObject range = new JsonObject();
                range.addProperty("min", (Number)Float.valueOf(((RangeSliderSetting)setting).getMinValue()));
                range.addProperty("max", (Number)Float.valueOf(((RangeSliderSetting)setting).getMaxValue()));
                return (JsonElement)range;
            }
            if (setting instanceof PositionSettings) {
                JsonObject position = new JsonObject();
                position.addProperty("x", (Number)Float.valueOf(((PositionSettings)setting).getX()));
                position.addProperty("y", (Number)Float.valueOf(((PositionSettings)setting).getY()));
                position.addProperty("z", (Number)Float.valueOf(((PositionSettings)setting).getZ()));
                return (JsonElement)position;
            }
            if (setting instanceof SelectSetting) {
                return GSON.toJsonTree((Object)((SelectSetting)setting).getValue());
            }
            if (setting instanceof MultiSelectSetting) {
                JsonArray array = new JsonArray();
                for (String selected : ((MultiSelectSetting)setting).getSelected()) {
                    array.add(selected);
                }
                return (JsonElement)array;
            }
            if (setting instanceof BindSetting) {
                JsonObject bind = new JsonObject();
                bind.addProperty("key", (Number)((BindSetting)setting).getKey());
                bind.addProperty("type", ((BindSetting)setting).getType().name());
                return (JsonElement)bind;
            }
            if (setting instanceof ColorSetting) {
                return GSON.toJsonTree((Object)((ColorSetting)setting).getColor());
            }
            if (setting instanceof TextSetting) {
                return GSON.toJsonTree((Object)((TextSetting)setting).getText());
            }
            return null;
        }

        private final void applySettings(Module module, JsonObject savedSettings) {
            HashMap<String, Integer> occurrences = new HashMap<String, Integer>();
            for (Setting setting : module.getSettings().all()) {
                String key = this.occurrenceKey(occurrences, setting.getName());
                JsonElement saved = savedSettings.has(key) ? savedSettings.get(key) : this.findSettingValue(savedSettings, setting.getName());
                if (saved == null || saved.isJsonNull()) continue;
                this.applySetting(setting, saved);
            }
        }

        private final void applySetting(Setting setting, JsonElement saved) {
            try {
                if (setting instanceof BooleanSetting && saved.isJsonPrimitive()) {
                    ((BooleanSetting)setting).setValue(saved.getAsBoolean());
                    return;
                }
                if (setting instanceof SliderSetting && saved.isJsonPrimitive()) {
                    ((SliderSetting)setting).setValue(saved.getAsFloat());
                    return;
                }
                if (setting instanceof RangeSliderSetting && saved.isJsonObject()) {
                    JsonObject range = saved.getAsJsonObject();
                    if (range.has("min") && range.has("max")) {
                        ((RangeSliderSetting)setting).setValue(range.get("min").getAsFloat(), range.get("max").getAsFloat());
                    }
                    return;
                }
                if (setting instanceof PositionSettings && saved.isJsonObject()) {
                    JsonObject position = saved.getAsJsonObject();
                    if (position.has("x")) {
                        ((PositionSettings)setting).setX(position.get("x").getAsFloat());
                    }
                    if (position.has("y")) {
                        ((PositionSettings)setting).setY(position.get("y").getAsFloat());
                    }
                    if (position.has("z")) {
                        ((PositionSettings)setting).setZ(position.get("z").getAsFloat());
                    }
                    return;
                }
                if (setting instanceof SelectSetting && saved.isJsonPrimitive()) {
                    SelectSetting selectSetting = (SelectSetting)setting;
                    String string = saved.getAsString();
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
                    selectSetting.setSelected(string);
                    return;
                }
                if (setting instanceof MultiSelectSetting && saved.isJsonArray()) {
                    MultiSelectSetting ms = (MultiSelectSetting)setting;
                    ArrayList<String> selected = new ArrayList<String>();
                    for (JsonElement element : saved.getAsJsonArray()) {
                        if (!element.isJsonPrimitive()) continue;
                        String option = element.getAsString();
                        if (!ms.getOptions().contains(option)) continue;
                        selected.add(option);
                    }
                    if (selected.size() >= ms.getMinSelectedCount()) {
                        String[] stringArray2 = selected.toArray(new String[0]);
                        ms.selected(Arrays.copyOf(stringArray2, stringArray2.length));
                    }
                    return;
                }
                if (setting instanceof BindSetting && saved.isJsonObject()) {
                    JsonObject bind = saved.getAsJsonObject();
                    BindSetting bindSetting = (BindSetting)setting;
                    Intrinsics.checkNotNull((Object)bind);
                    bindSetting.setKey(this.readInt(bind, "key", ((BindSetting)setting).getKey()));
                    if (bind.has("type")) {
                        BindSetting bindSetting2 = (BindSetting)setting;
                        String string = bind.get("type").getAsString();
                        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
                        bindSetting2.setType(BindSetting.Type.valueOf(string));
                    }
                    return;
                }
                if (setting instanceof ColorSetting && saved.isJsonPrimitive()) {
                    ((ColorSetting)setting).setColor(saved.getAsInt());
                    return;
                }
                if (setting instanceof TextSetting && saved.isJsonPrimitive()) {
                    ((TextSetting)setting).setText(saved.getAsString());
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private final JsonObject readModuleConfig() {
            JsonObject jsonObject;
            Object legacy;
            Object path = this.moduleConfigPath();
            if (!Files.exists((Path)path, new LinkOption[0])) {
                Path previousFormat = RepositoryStorage.root().resolve("autocfg.tria");
                if (Files.exists(previousFormat, new LinkOption[0])) {
                    Intrinsics.checkNotNull((Object)previousFormat);
                    path = previousFormat;
                } else {
                    legacy = FabricLoader.getInstance().getGameDir().resolve("kimiko").resolve("autocfg.json");
                    if (Files.exists((Path)legacy, new LinkOption[0])) {
                        Intrinsics.checkNotNull((Object)legacy);
                        path = legacy;
                    }
                }
            }
            if (!Files.exists((Path)path, new LinkOption[0])) {
                return new JsonObject();
            }
            try {
                JsonObject jsonObject2;
                legacy = Files.newBufferedReader((Path)path);
                Throwable throwable = null;
                try {
                    BufferedReader reader = (BufferedReader)legacy;
                    boolean bl = false;
                    JsonElement parsed = JsonParser.parseReader((Reader)reader);
                    jsonObject2 = parsed != null && parsed.isJsonObject() ? parsed.getAsJsonObject() : new JsonObject();
                }
                catch (Throwable throwable2) {
                    throwable = throwable2;
                    throw throwable2;
                }
                finally {
                    CloseableKt.closeFinally((Closeable)legacy, (Throwable)throwable);
                }
                jsonObject = jsonObject2;
                Intrinsics.checkNotNull((Object)jsonObject);
            }
            catch (Exception ex) {
                jsonObject = new JsonObject();
            }
            return jsonObject;
        }

        private final JsonObject findModuleObject(JsonObject modules, String moduleName) {
            if (modules.has(moduleName) && modules.get(moduleName).isJsonObject()) {
                return modules.getAsJsonObject(moduleName);
            }
            for (String key : modules.keySet()) {
                if (!StringsKt.equals((String)key, (String)moduleName, (boolean)true) || !modules.get(key).isJsonObject()) continue;
                return modules.getAsJsonObject(key);
            }
            String compact = String.valueOf(moduleName).replace(" ", "");
            for (String key : modules.keySet()) {
                Intrinsics.checkNotNull((Object)key);
                if (!StringsKt.equals((String)String.valueOf(key).replace(" ", ""), (String)compact, (boolean)true) || !modules.get(key).isJsonObject()) continue;
                return modules.getAsJsonObject(key);
            }
            return null;
        }

        private final JsonElement findSettingValue(JsonObject settings, String settingName) {
            if (settings.has(settingName)) {
                return settings.get(settingName);
            }
            for (String key : settings.keySet()) {
                if (!StringsKt.equals((String)key, (String)settingName, (boolean)true)) continue;
                return settings.get(key);
            }
            return null;
        }

        private final Path moduleConfigPath() {
            Path path = RepositoryStorage.root().resolve("autocfg.kimiko");
            Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
            return path;
        }

        private final JsonObject objectWithArray(String key) {
            JsonObject object = new JsonObject();
            object.add(key, (JsonElement)new JsonArray());
            return object;
        }

        private final boolean readBoolean(JsonObject object, String key, boolean fallback) {
            boolean bl;
            try {
                bl = object.has(key) ? object.get(key).getAsBoolean() : fallback;
            }
            catch (Exception ignored) {
                bl = fallback;
            }
            return bl;
        }

        private final int readInt(JsonObject object, String key, int fallback) {
            int n;
            try {
                n = object.has(key) ? object.get(key).getAsInt() : fallback;
            }
            catch (Exception ignored) {
                n = fallback;
            }
            return n;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private static final void submitWrite$lambda$0(JsonObject $root, boolean $force) {
            block7: {
                try {
                    Files.createDirectories(RepositoryStorage.root(), new FileAttribute[0]);
                    String serialized = GSON.toJson((JsonElement)$root);
                    if (!$force && Intrinsics.areEqual((Object)serialized, (Object)INSTANCE.lastSavedModuleConfig)) break block7;
                    Closeable closeable = Files.newBufferedWriter(Companion.moduleConfigPath(), new OpenOption[0]);
                    Throwable throwable = null;
                    try {
                        BufferedWriter writer = (BufferedWriter)closeable;
                        boolean bl = false;
                        writer.write(serialized);
                        Unit unit = Unit.INSTANCE;
                    }
                    catch (Throwable throwable2) {
                        throwable = throwable2;
                        throw throwable2;
                    }
                    finally {
                        CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
                    }
                    INSTANCE.lastSavedModuleConfig = serialized;
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }


        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private static final void writeProfile$lambda$0(String $safe, Runnable $onDone, JsonObject $copy) {
            try {
                Path dir = Companion.profilesDirectory();
                Files.createDirectories(dir, new FileAttribute[0]);
                Closeable closeable = Files.newBufferedWriter(dir.resolve($safe + ConfigManager.CONFIG_EXTENSION), new OpenOption[0]);
                Throwable throwable = null;
                try {
                    BufferedWriter writer = (BufferedWriter)closeable;
                    boolean bl = false;
                    writer.write(GSON.toJson((JsonElement)$copy));
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
            catch (Exception exception) {
                // empty catch block
            }
            if ($onDone != null) {
                MinecraftClient.getInstance().execute($onDone);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private static final void saveProfile$lambda$0(String $safe, JsonObject $root) {
            try {
                Path dir = Companion.profilesDirectory();
                Files.createDirectories(dir, new FileAttribute[0]);
                Closeable closeable = Files.newBufferedWriter(dir.resolve($safe + ConfigManager.CONFIG_EXTENSION), new OpenOption[0]);
                Throwable throwable = null;
                try {
                    BufferedWriter writer = (BufferedWriter)closeable;
                    boolean bl = false;
                    writer.write(GSON.toJson((JsonElement)$root));
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
            catch (Exception exception) {
                // empty catch block
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private static final void loadProfile$lambda$0$1(JsonObject $parsedRoot, String $name) {
            INSTANCE.loading = true;
            try {
                Companion.applyModuleRoot($parsedRoot);
            }
            catch (Exception exception) {
            }
            finally {
                INSTANCE.loading = false;
            }
            Companion.saveModules(true);
            ConfigLibrary.Companion.get().onLocalApplied($name);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private static final void loadProfile$lambda$0(Path $source, String $name) {
            JsonObject root = null;
            try {
                Closeable closeable = Files.newBufferedReader($source);
                Throwable throwable = null;
                try {
                    BufferedReader reader = (BufferedReader)closeable;
                    boolean bl = false;
                    JsonElement parsed = JsonParser.parseReader((Reader)reader);
                    if (parsed != null && parsed.isJsonObject()) {
                        root = parsed.getAsJsonObject();
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
            catch (Exception exception) {
                // empty catch block
            }
            JsonObject parsedRoot = root;
            if (parsedRoot != null) {
                MinecraftClient.getInstance().execute(() -> Companion.loadProfile$lambda$0$1(parsedRoot, $name));
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

