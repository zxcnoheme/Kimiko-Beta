/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  net.fabricmc.loader.api.FabricLoader
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.chat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001d\u0010\f\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u0003R\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R$\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0015j\b\u0012\u0004\u0012\u00020\u0007`\u00168\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0017R\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001c\u001a\u00020\u00188\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001a\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/utils/chat/ChatHistory;", "", "<init>", "()V", "", "load", "", "", "Lkotlin/jvm/JvmStatic;", "entries", "()Ljava/util/List;", "message", "add", "(Ljava/lang/String;)V", "save", "Ljava/nio/file/Path;", "FILE", "Ljava/nio/file/Path;", "", "MAX", "I", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "Ljava/util/ArrayList;", "", "loaded", "Z", "Lkotlin/jvm/JvmField;", "seeding", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nChatHistory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChatHistory.kt\nrtx/kimiko/utils/chat/ChatHistory\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,64:1\n1#2:65\n*E\n"})
public final class ChatHistory {
    @NotNull
    public static final ChatHistory INSTANCE = new ChatHistory();
    @NotNull
    private static final Path FILE;
    private static final int MAX = 200;
    @NotNull
    private static final ArrayList<String> entries;
    private static boolean loaded;
    @JvmField
    public static volatile boolean seeding;

    private ChatHistory() {
    }

    private final synchronized void load() {
        if (loaded) {
            return;
        }
        loaded = true;
        try {
            if (Files.exists(FILE, new LinkOption[0])) {
                for (String line : Files.readAllLines(FILE, StandardCharsets.UTF_8)) {
                    CharSequence charSequence = line;
                    if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) continue;
                    entries.add(line);
                }
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    @JvmStatic
    @NotNull
    public static final synchronized List<String> entries() {
        INSTANCE.load();
        return new ArrayList(entries);
    }

    @JvmStatic
    public static final synchronized void add(@Nullable String message) {
        if (message == null) {
            return;
        }
        String m = ((Object)StringsKt.trim((CharSequence)message)).toString();
        if (((CharSequence)m).length() == 0) {
            return;
        }
        INSTANCE.load();
        if (!((Collection)entries).isEmpty() && Intrinsics.areEqual((Object)entries.get(entries.size() - 1), (Object)m)) {
            return;
        }
        entries.add(m);
        while (entries.size() > 200) {
            entries.remove(0);
        }
        INSTANCE.save();
    }

    private final void save() {
        try {
            Path path = FILE.getParent();
            if (path != null) {
                Path it = path;
                boolean bl = false;
                Files.createDirectories(it, new FileAttribute[0]);
            }
            Files.write(FILE, (Iterable<? extends CharSequence>)entries, StandardCharsets.UTF_8, new OpenOption[0]);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    static {
        Path path = FabricLoader.getInstance().getGameDir().resolve("kimiko").resolve("command-history.txt");
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
        FILE = path;
        entries = new ArrayList();
    }
}

