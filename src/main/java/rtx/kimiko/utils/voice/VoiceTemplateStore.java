/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.io.CloseableKt
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.fabricmc.loader.api.FabricLoader
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.voice;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.voice.VoiceTemplate;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u000e\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n0\u0010H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0015\u001a\u00020\u00142\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0018\u001a\u00020\u0017H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u0017H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001a\u0010\u0019J\u001d\u0010\u001b\u001a\u00020\u00142\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u0003J\u0019\u0010\u001f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001e\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u001f\u0010!\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b!\u0010\u0016J\u0017\u0010\"\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\"\u0010#J\u0019\u0010$\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002\u00a2\u0006\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010)R \u0010,\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n0+8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010.\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010/\u00a8\u00060"}, d2={"Lrtx/kimiko/utils/voice/VoiceTemplateStore;", "", "<init>", "()V", "Ljava/nio/file/Path;", "Lkotlin/jvm/JvmStatic;", "directory", "()Ljava/nio/file/Path;", "", "key", "Lrtx/kimiko/utils/voice/VoiceTemplate;", "get", "(Ljava/lang/String;)Lrtx/kimiko/utils/voice/VoiceTemplate;", "", "has", "(Ljava/lang/String;)Z", "", "all", "()Ljava/util/Map;", "template", "", "put", "(Ljava/lang/String;Lrtx/kimiko/utils/voice/VoiceTemplate;)V", "", "clearAll", "()I", "clearAdaptedAll", "remove", "(Ljava/lang/String;)V", "ensureLoaded", "path", "read", "(Ljava/nio/file/Path;)Lrtx/kimiko/utils/voice/VoiceTemplate;", "write", "fileOf", "(Ljava/lang/String;)Ljava/nio/file/Path;", "sanitize", "(Ljava/lang/String;)Ljava/lang/String;", "WAKE_KEY", "Ljava/lang/String;", "MAGIC_V1", "I", "MAGIC_V2", "Ljava/util/concurrent/ConcurrentHashMap;", "CACHE", "Ljava/util/concurrent/ConcurrentHashMap;", "loaded", "Z", "rtx.kimiko:kimiko"})
public final class VoiceTemplateStore {
    @NotNull
    public static final VoiceTemplateStore INSTANCE = new VoiceTemplateStore();
    @NotNull
    public static final String WAKE_KEY = "__wake__";
    private static final int MAGIC_V1 = 1263948849;
    private static final int MAGIC_V2 = 1263948850;
    @NotNull
    private static final ConcurrentHashMap<String, VoiceTemplate> CACHE = new ConcurrentHashMap();
    private static volatile boolean loaded;

    private VoiceTemplateStore() {
    }

    @JvmStatic
    @NotNull
    public static final Path directory() {
        Path path = FabricLoader.getInstance().getGameDir().resolve("kimiko").resolve("voice");
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
        return path;
    }

    @JvmStatic
    @Nullable
    public static final VoiceTemplate get(@Nullable String key) {
        INSTANCE.ensureLoaded();
        return CACHE.get(INSTANCE.sanitize(key));
    }

    @JvmStatic
    public static final boolean has(@Nullable String key) {
        VoiceTemplate template = VoiceTemplateStore.get(key);
        return template != null && !template.isEmpty();
    }

    @JvmStatic
    @NotNull
    public static final Map<String, VoiceTemplate> all() {
        INSTANCE.ensureLoaded();
        return CACHE;
    }

    @JvmStatic
    public static final void put(@Nullable String key, @Nullable VoiceTemplate template) {
        String safe = INSTANCE.sanitize(key);
        if (template == null || template.isEmpty()) {
            VoiceTemplateStore.remove(safe);
            return;
        }
        INSTANCE.ensureLoaded();
        ((Map)CACHE).put(safe, template);
        INSTANCE.write(safe, template);
    }

    @JvmStatic
    public static final int clearAll() {
        INSTANCE.ensureLoaded();
        int count = CACHE.size();
        Iterator iterator = new ArrayList(CACHE.keySet()).iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (iterator2.hasNext()) {
            String key = (String)iterator2.next();
            VoiceTemplateStore.remove(key);
        }
        return count;
    }

    @JvmStatic
    public static final int clearAdaptedAll() {
        INSTANCE.ensureLoaded();
        int cleaned = 0;
        Iterator iterator = new ArrayList(CACHE.keySet()).iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (iterator2.hasNext()) {
            String key = (String)iterator2.next();
            VoiceTemplate template = CACHE.get(key);
            if (template == null || !template.clearAdapted()) continue;
            ++cleaned;
            if (template.isEmpty()) {
                VoiceTemplateStore.remove(key);
                continue;
            }
            Intrinsics.checkNotNull((Object)key);
            INSTANCE.write(key, template);
        }
        return cleaned;
    }

    @JvmStatic
    public static final void remove(@Nullable String key) {
        String safe = INSTANCE.sanitize(key);
        INSTANCE.ensureLoaded();
        CACHE.remove(safe);
        try {
            Files.deleteIfExists(INSTANCE.fileOf(safe));
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final synchronized void ensureLoaded() {
        if (loaded) {
            return;
        }
        loaded = true;
        Path dir = VoiceTemplateStore.directory();
        if (!Files.isDirectory(dir, new LinkOption[0])) {
            return;
        }
        try (Stream<Path> stream = Files.list(dir)) {
            stream.filter(p -> p.getFileName().toString().endsWith(".vt"))
                  .forEach(p -> {
                      String name = p.getFileName().toString();
                      String key = name.substring(0, name.length() - 3);
                      VoiceTemplate template = INSTANCE.read(p);
                      if (template != null && !template.isEmpty()) {
                          CACHE.put(key, template);
                      }
                  });
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final VoiceTemplate read(Path path) {
        try {
            Closeable closeable = new DataInputStream(Files.newInputStream(path, new OpenOption[0]));
            Throwable throwable = null;
            try {
                boolean v2;
                DataInputStream input = (DataInputStream)closeable;
                boolean bl = false;
                int magic = input.readInt();
                boolean bl2 = v2 = magic == 1263948850;
                if (!v2 && magic != 1263948849) {
                    VoiceTemplate voiceTemplate = null;
                    return voiceTemplate;
                }
                int takes = input.readInt();
                if (takes <= 0 || takes > 8) {
                    VoiceTemplate voiceTemplate = null;
                    return voiceTemplate;
                }
                VoiceTemplate template = new VoiceTemplate();
                for (int t = 0; t < takes; ++t) {
                    boolean adapted;
                    int frames = input.readInt();
                    int dims = input.readInt();
                    if (frames <= 0 || frames > 4096 || dims != 24) {
                        VoiceTemplate voiceTemplate = null;
                        return voiceTemplate;
                    }
                    boolean bl3 = adapted = v2 && input.readBoolean();
                    if (frames < 20) {
                        input.skipBytes(frames * dims * 4);
                        continue;
                    }
                    int n = 0;
                    float[][] fArrayArray = new float[frames][];
                    while (n < frames) {
                        int n2 = n++;
                        fArrayArray[n2] = new float[dims];
                    }
                    float[][] data = fArrayArray;
                    for (int f = 0; f < frames; ++f) {
                        for (int d = 0; d < dims; ++d) {
                            data[f][d] = input.readFloat();
                        }
                    }
                    if (adapted) {
                        template.addAdapted(data);
                        continue;
                    }
                    template.addRecorded(data);
                }
                VoiceTemplate voiceTemplate = template;
                return voiceTemplate;
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
            }
        }
        catch (IOException e) {
            return null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void write(String key, VoiceTemplate template) {
        Path dir = VoiceTemplateStore.directory();
        try {
            Files.createDirectories(dir, new FileAttribute[0]);
        }
        catch (IOException e) {
            return;
        }
        List<VoiceTemplate.Take> takes = template.snapshot();
        try {
            Closeable closeable = new DataOutputStream(Files.newOutputStream(this.fileOf(key), new OpenOption[0]));
            Throwable throwable = null;
            try {
                DataOutputStream out = (DataOutputStream)closeable;
                boolean bl = false;
                out.writeInt(1263948850);
                out.writeInt(takes.size());
                for (VoiceTemplate.Take take : takes) {
                    out.writeInt(((Object[])take.frames).length);
                    out.writeInt(24);
                    out.writeBoolean(take.adapted);
                    float[][] fArray = take.frames;
                    int n = ((Object[])fArray).length;
                    for (int i = 0; i < n; ++i) {
                        float[] frame = fArray[i];
                        for (int d = 0; d < 24; ++d) {
                            out.writeFloat(d < frame.length ? frame[d] : 0.0f);
                        }
                    }
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
        catch (IOException iOException) {
            // empty catch block
        }
    }

    private final Path fileOf(String key) {
        Path path = VoiceTemplateStore.directory().resolve(key + ".vt");
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
        return path;
    }

    private final String sanitize(String key) {
        CharSequence charSequence = key;
        if (charSequence == null || charSequence.length() == 0) {
            return "unknown";
        }
        StringBuilder sb = new StringBuilder(key.length());
        char[] cArray = key.toCharArray();
        Intrinsics.checkNotNullExpressionValue((Object)cArray, (String)"toCharArray(...)");
        for (int n : cArray) {
            if (n == 32) continue;
            sb.append((char)(Character.isLetterOrDigit((char)n) || n == 95 || n == 45 ? n : 95));
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        return string;
    }
}

