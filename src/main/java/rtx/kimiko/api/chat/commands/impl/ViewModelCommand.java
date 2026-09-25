/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.util.Formatting
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.chat.commands.impl;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.chat.commands.Command;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.impl.Visuals.ViewModel;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ+\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000eH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/chat/commands/impl/ViewModelCommand;", "Lrtx/kimiko/api/chat/commands/Command;", "<init>", "()V", "", "label", "", "args", "", "execute", "(Ljava/lang/String;[Ljava/lang/String;)V", "Ljava/util/stream/Stream;", "tabComplete", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/util/stream/Stream;", "", "getLongDesc", "()Ljava/util/List;", "rtx.kimiko:kimiko"})
public final class ViewModelCommand
extends Command {
    public ViewModelCommand() {
        super("vm", "View Model: управление расположением рук.", new String[]{"viewmodel"});
    }

    @Override
    public void execute(@NotNull String label, @NotNull String[] args) {
        Intrinsics.checkNotNullParameter((Object)label, (String)"label");
        Intrinsics.checkNotNullParameter((Object)args, (String)"args");
        if (args.length == 0) {
            this.usage();
            return;
        }
        if (StringsKt.equals((String)"reset", (String)args[0], (boolean)true)) {
            ViewModel vm = ViewModel.Companion.getInstance();
            if (vm == null) {
                this.logDirect(I18n.tr("Модуль View Model недоступен."), Formatting.RED);
                return;
            }
            vm.resetLayout();
            this.logDirect(I18n.tr("Расположение рук сброшено."), Formatting.GREEN);
            return;
        }
        this.usage();
    }

    @Override
    @NotNull
    public Stream<String> tabComplete(@NotNull String label, @NotNull String[] args) {
        Intrinsics.checkNotNullParameter((Object)label, (String)"label");
        Intrinsics.checkNotNullParameter((Object)args, (String)"args");
        if (args.length == 1) {
            String prefix = args[0].toLowerCase(Locale.ROOT);
            return Stream.of("reset").filter(it -> it.startsWith(prefix));
        }
        return Stream.empty();
    }

    @Override
    @NotNull
    public List<String> getLongDesc() {
        return List.of(I18n.tr("Управление View Model."), I18n.tr("> vm reset — сбросить положение и размер рук"));
    }

    private static final boolean tabComplete$lambda$0(String[] $args, String it) {
        Intrinsics.checkNotNull((Object)it);
        String string = $args[0].toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toLowerCase(...)");
        return String.valueOf(it).startsWith(string);
    }

    private static final boolean tabComplete$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }
}

