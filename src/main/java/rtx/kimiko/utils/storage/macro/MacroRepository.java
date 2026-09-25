/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.storage.macro;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.storage.RepositoryStorage;
import rtx.kimiko.utils.storage.macro.Macro;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0006\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0003J%\u0010\f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u0013\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0015\u00a2\u0006\u0004\b\u0016\u0010\u0014J\r\u0010\u0017\u001a\u00020\n\u00a2\u0006\u0004\b\u0017\u0010\u0018R$\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0019j\b\u0012\u0004\u0012\u00020\u0012`\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001c\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/utils/storage/macro/MacroRepository;", "", "<init>", "()V", "", "load", "save", "", "name", "message", "", "key", "addMacroAndSave", "(Ljava/lang/String;Ljava/lang/String;I)V", "deleteMacroAndSave", "(Ljava/lang/String;)V", "clearListAndSave", "", "Lrtx/kimiko/utils/storage/macro/Macro;", "getMacroList", "()Ljava/util/List;", "", "getMacroNames", "size", "()I", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "macros", "Ljava/util/ArrayList;", "Companion", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nMacroRepository.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MacroRepository.kt\nrtx/kimiko/utils/storage/macro/MacroRepository\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,83:1\n1739#2:84\n1814#2,3:85\n*S KotlinDebug\n*F\n+ 1 MacroRepository.kt\nrtx/kimiko/utils/storage/macro/MacroRepository\n*L\n64#1:84\n64#1:85,3\n*E\n"})
public final class MacroRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ArrayList<Macro> macros = new ArrayList();
    @NotNull
    private static final String KEY = "macros";
    @Nullable
    private static MacroRepository instance;

    private MacroRepository() {
        this.load();
    }

    private final void load() {
        this.macros.clear();
        JsonObject obj = RepositoryStorage.readObject(KEY);
        if (obj.has(KEY)) {
            Iterator iterator = obj.getAsJsonArray(KEY).iterator();
            Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
            Iterator iterator2 = iterator;
            while (iterator2.hasNext()) {
                JsonElement el = (JsonElement)iterator2.next();
                JsonObject m = el.getAsJsonObject();
                String string = m.get("name").getAsString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
                String string2 = m.get("message").getAsString();
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getAsString(...)");
                this.macros.add(new Macro(string, string2, m.get("key").getAsInt()));
            }
        }
    }

    private final void save() {
        JsonObject obj = new JsonObject();
        JsonArray arr = new JsonArray();
        Iterator<Macro> iterator = this.macros.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Macro> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Macro m = (Macro) (iterator2.next());
            JsonObject entry = new JsonObject();
            entry.addProperty("name", m.name());
            entry.addProperty("message", m.message());
            entry.addProperty("key", (Number)m.key());
            arr.add((JsonElement)entry);
        }
        obj.add(KEY, (JsonElement)arr);
        RepositoryStorage.write(KEY, obj);
    }

    public final void addMacroAndSave(@NotNull String name, @NotNull String message, int key) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        this.macros.removeIf(it -> it.name().equalsIgnoreCase(name));
        this.macros.add(new Macro(name, message, key));
        this.save();
    }

    public final void deleteMacroAndSave(@Nullable String name) {
        if (name != null) {
            this.macros.removeIf(it -> it.name().equalsIgnoreCase(name));
            this.save();
        }
    }

    public final void clearListAndSave() {
        this.macros.clear();
        this.save();
    }

    @NotNull
    public final List<Macro> getMacroList() {
        return new ArrayList(this.macros);
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public final List<String> getMacroNames() {
        ArrayList<String> names = new ArrayList<>(this.macros.size());
        for (Macro m : this.macros) {
            names.add(m.name());
        }
        return names;
    }

    public final int size() {
        return this.macros.size();
    }

    @JvmStatic
    @NotNull
    public static final MacroRepository getInstance() {
        return Companion.getInstance();
    }

    public /* synthetic */ MacroRepository(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2={"Lrtx/kimiko/utils/storage/macro/MacroRepository.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/storage/macro/MacroRepository;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/utils/storage/macro/MacroRepository;", "", "KEY", "Ljava/lang/String;", "instance", "Lrtx/kimiko/utils/storage/macro/MacroRepository;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final MacroRepository getInstance() {
            MacroRepository repo = instance;
            if (repo == null) {
                repo = new MacroRepository(null);
                instance = repo;
            }
            return repo;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

