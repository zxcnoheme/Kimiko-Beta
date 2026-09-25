/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.comparisons.ComparisonsKt
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.chat.commands.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.chat.commands.Command;
import rtx.kimiko.api.chat.commands.CommandManager;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0007\u001a\u00020\u00002\u0016\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u0004\"\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0000\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0013\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012\u00a2\u0006\u0004\b\u0013\u0010\u0014R$\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0015j\b\u0012\u0004\u0012\u00020\u0005`\u00168\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/api/chat/commands/helpers/TabCompleteHelper;", "", "<init>", "()V", "", "", "entries", "append", "([Ljava/lang/String;)Lrtx/kimiko/api/chat/commands/helpers/TabCompleteHelper;", "Lrtx/kimiko/api/chat/commands/CommandManager;", "manager", "addCommands", "(Lrtx/kimiko/api/chat/commands/CommandManager;)Lrtx/kimiko/api/chat/commands/helpers/TabCompleteHelper;", "sortAlphabetically", "()Lrtx/kimiko/api/chat/commands/helpers/TabCompleteHelper;", "prefix", "filterPrefix", "(Ljava/lang/String;)Lrtx/kimiko/api/chat/commands/helpers/TabCompleteHelper;", "Ljava/util/stream/Stream;", "stream", "()Ljava/util/stream/Stream;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "values", "Ljava/util/ArrayList;", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nTabCompleteHelper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TabCompleteHelper.kt\nrtx/kimiko/api/chat/commands/helpers/TabCompleteHelper\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,40:1\n1174#2,2:41\n*S KotlinDebug\n*F\n+ 1 TabCompleteHelper.kt\nrtx/kimiko/api/chat/commands/helpers/TabCompleteHelper\n*L\n28#1:41,2\n*E\n"})
public final class TabCompleteHelper {
    @NotNull
    private final ArrayList<String> values = new ArrayList();

    @NotNull
    public final TabCompleteHelper append(String ... entries) {
        Intrinsics.checkNotNullParameter((Object)entries, (String)"entries");
        for (String entry : entries) {
            if (entry == null) continue;
            this.values.add(entry);
        }
        return this;
    }

    @NotNull
    public final TabCompleteHelper append(@Nullable Collection<String> entries) {
        if (entries != null) {
            for (String entry : entries) {
                if (entry != null) {
                    this.values.add(entry);
                }
            }
        }
        return this;
    }

    @NotNull
    public final TabCompleteHelper addCommands(@Nullable CommandManager manager) {
        if (manager != null) {
            for (Command command : manager.getCommands()) {
                this.values.add(command.getName());
                this.values.addAll((Collection<String>)command.getAliases());
            }
        }
        return this;
    }

    @NotNull
    public final TabCompleteHelper sortAlphabetically() {
        this.values.sort(String.CASE_INSENSITIVE_ORDER);
        return this;
    }

    @NotNull
    public final TabCompleteHelper filterPrefix(@Nullable String prefix) {
        String lowered = prefix != null ? prefix.toLowerCase(Locale.ROOT) : "";
        this.values.removeIf(it -> !it.toLowerCase(Locale.ROOT).startsWith(lowered));
        return this;
    }

    @NotNull
    public final Stream<String> stream() {
        Stream<String> stream = this.values.stream().distinct();
        Intrinsics.checkNotNullExpressionValue(stream, "distinct(...)");
        return stream;
    }
}

