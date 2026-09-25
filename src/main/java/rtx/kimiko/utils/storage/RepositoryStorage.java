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
 *  kotlin.Pair
 *  kotlin.TuplesKt
 *  kotlin.Unit
 *  kotlin.collections.MapsKt
 *  kotlin.io.CloseableKt
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.fabricmc.loader.api.FabricLoader
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.Reader;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Map;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J\u001b\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\rJ#\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0011J#\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0013\u0010\u0011J\u0013\u0010\u0014\u001a\u00020\u000fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0014\u0010\u0003J\u0017\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u0019J\u0017\u0010\u001c\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u001c\u0010 \u001a\n \u001f*\u0004\u0018\u00010\u001e0\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010#R\u0014\u0010%\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010#R \u0010'\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(\u00a8\u0006)"}, d2={"Lrtx/kimiko/utils/storage/RepositoryStorage;", "", "<init>", "()V", "Ljava/nio/file/Path;", "Lkotlin/jvm/JvmStatic;", "configRoot", "()Ljava/nio/file/Path;", "root", "", "name", "Lcom/google/gson/JsonObject;", "readObject", "(Ljava/lang/String;)Lcom/google/gson/JsonObject;", "object", "", "write", "(Ljava/lang/String;Lcom/google/gson/JsonObject;)V", "defaultObject", "ensureObject", "migratePreviousFormat", "oldFile", "migrateFile", "(Ljava/nio/file/Path;)V", "file", "(Ljava/lang/String;)Ljava/nio/file/Path;", "previousFormatFile", "legacyFile", "fileName", "(Ljava/lang/String;)Ljava/lang/String;", "Lcom/google/gson/Gson;", "kotlin.jvm.PlatformType", "GSON", "Lcom/google/gson/Gson;", "CONFIG_ROOT", "Ljava/nio/file/Path;", "ROOT", "LEGACY_ROOT", "", "FILE_NAMES", "Ljava/util/Map;", "rtx.kimiko:kimiko"})
public final class RepositoryStorage {
    @NotNull
    public static final RepositoryStorage INSTANCE = new RepositoryStorage();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @NotNull
    private static final Path CONFIG_ROOT;
    @NotNull
    private static final Path ROOT;
    @NotNull
    private static final Path LEGACY_ROOT;
    @NotNull
    private static final Map<String, String> FILE_NAMES;

    private RepositoryStorage() {
    }

    @JvmStatic
    @NotNull
    public static final Path configRoot() {
        return CONFIG_ROOT;
    }

    @JvmStatic
    @NotNull
    public static final Path root() {
        return ROOT;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    @NotNull
    public static final JsonObject readObject(@NotNull String name) {
        JsonObject jsonObject;
        Object legacyFile;
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Object file = INSTANCE.file(name);
        if (!Files.exists((Path)file, new LinkOption[0])) {
            Path previousFormatFile = INSTANCE.previousFormatFile(name);
            if (Files.exists(previousFormatFile, new LinkOption[0])) {
                file = previousFormatFile;
            } else {
                legacyFile = INSTANCE.legacyFile(name);
                if (!Files.exists((Path)legacyFile, new LinkOption[0])) {
                    return new JsonObject();
                }
                file = legacyFile;
            }
        }
        try {
            JsonObject jsonObject2;
            legacyFile = Files.newBufferedReader((Path)file);
            Throwable throwable = null;
            try {
                BufferedReader reader = (BufferedReader)legacyFile;
                boolean bl = false;
                jsonObject2 = JsonParser.parseReader((Reader)reader).getAsJsonObject();
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                CloseableKt.closeFinally((Closeable)legacyFile, (Throwable)throwable);
            }
            jsonObject = jsonObject2;
            Intrinsics.checkNotNull((Object)jsonObject);
        }
        catch (Exception ex) {
            jsonObject = new JsonObject();
        }
        return jsonObject;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void write(@NotNull String name, @NotNull JsonObject object) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)object, (String)"object");
        try {
            Files.createDirectories(ROOT, new FileAttribute[0]);
            Path file = INSTANCE.file(name);
            Closeable closeable = Files.newBufferedWriter(file, new OpenOption[0]);
            Throwable throwable = null;
            try {
                BufferedWriter writer = (BufferedWriter)closeable;
                boolean bl = false;
                GSON.toJson((JsonElement)object, (Appendable)writer);
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

    @JvmStatic
    public static final void ensureObject(@NotNull String name, @NotNull JsonObject defaultObject) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)defaultObject, (String)"defaultObject");
        Path file = INSTANCE.file(name);
        if (Files.exists(file, new LinkOption[0])) {
            return;
        }
        JsonObject existing = RepositoryStorage.readObject(name);
        RepositoryStorage.write(name, existing.isEmpty() ? defaultObject : existing);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void migratePreviousFormat() {
        if (!Files.exists(CONFIG_ROOT, new LinkOption[0])) {
            return;
        }
        try (Stream<Path> files = Files.walk(CONFIG_ROOT, new FileVisitOption[0])) {
            files.filter(Files::isRegularFile)
                 .filter(it -> it.getFileName().toString().endsWith(".tria"))
                 .forEach(INSTANCE::migrateFile);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private final void migrateFile(Path oldFile) {
        String oldName = ((Object)oldFile.getFileName()).toString();
        String string = oldName.substring(0, oldName.length() - 5);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
        Path newFile = oldFile.resolveSibling(string + ".kimiko");
        if (Files.exists(newFile, new LinkOption[0])) {
            return;
        }
        try {
            Files.move(oldFile, newFile, new CopyOption[0]);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private final Path file(String name) {
        Path path = ROOT.resolve(this.fileName(name) + ".kimiko");
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
        return path;
    }

    private final Path previousFormatFile(String name) {
        Path path = ROOT.resolve(this.fileName(name) + ".tria");
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
        return path;
    }

    private final Path legacyFile(String name) {
        String fileName = this.fileName(name);
        Path mappedLegacy = LEGACY_ROOT.resolve(fileName + ".json");
        if (Files.exists(mappedLegacy, new LinkOption[0])) {
            Intrinsics.checkNotNull((Object)mappedLegacy);
            return mappedLegacy;
        }
        Path path = LEGACY_ROOT.resolve(name + ".json");
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
        return path;
    }

    private final String fileName(String name) {
        return FILE_NAMES.getOrDefault(name, name);
    }

    private static final boolean migratePreviousFormat$lambda$0$0(Path it) {
        return Files.isRegularFile(it, new LinkOption[0]);
    }

    private static final boolean migratePreviousFormat$lambda$0$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final boolean migratePreviousFormat$lambda$0$2(Path it) {
        return String.valueOf(((Object)it.getFileName()).toString()).endsWith(".tria");
    }

    private static final boolean migratePreviousFormat$lambda$0$3(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final Unit migratePreviousFormat$lambda$0$4(Path it) {
        Intrinsics.checkNotNull((Object)it);
        INSTANCE.migrateFile(it);
        return Unit.INSTANCE;
    }

    private static final void migratePreviousFormat$lambda$0$5(Function1 $tmp0, Object p0) {
        $tmp0.invoke(p0);
    }

    static {
        Path path = FabricLoader.getInstance().getGameDir().resolve("kimiko").resolve("configs");
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
        CONFIG_ROOT = path;
        Path path2 = CONFIG_ROOT.resolve("system");
        Intrinsics.checkNotNullExpressionValue((Object)path2, (String)"resolve(...)");
        ROOT = path2;
        Path path3 = FabricLoader.getInstance().getGameDir().resolve("kimiko");
        Intrinsics.checkNotNullExpressionValue((Object)path3, (String)"resolve(...)");
        LEGACY_ROOT = path3;
        FILE_NAMES = MapsKt.mapOf((Pair)TuplesKt.to((Object)"waypoints", (Object)"way"));
    }
}

