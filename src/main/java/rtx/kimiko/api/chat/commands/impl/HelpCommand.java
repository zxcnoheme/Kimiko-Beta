/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  net.minecraft.util.Formatting
 *  net.minecraft.text.ClickEvent
 *  net.minecraft.text.ClickEvent.RunCommand
 *  net.minecraft.text.Text
 *  net.minecraft.text.HoverEvent
 *  net.minecraft.text.HoverEvent.ShowText
 *  net.minecraft.text.Style
 *  net.minecraft.text.MutableText
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.chat.commands.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraft.util.Formatting;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Text;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.text.MutableText;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.chat.commands.Command;
import rtx.kimiko.api.chat.commands.CommandManager;
import rtx.kimiko.api.chat.commands.helpers.CommandDividers;
import rtx.kimiko.api.chat.commands.helpers.Paginator;
import rtx.kimiko.api.chat.commands.helpers.TabCompleteHelper;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ+\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000eH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/api/chat/commands/impl/HelpCommand;", "Lrtx/kimiko/api/chat/commands/Command;", "<init>", "()V", "", "label", "", "args", "", "execute", "(Ljava/lang/String;[Ljava/lang/String;)V", "Ljava/util/stream/Stream;", "tabComplete", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/util/stream/Stream;", "", "getLongDesc", "()Ljava/util/List;", "value", "", "isInteger", "(Ljava/lang/String;)Z", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nHelpCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HelpCommand.kt\nrtx/kimiko/api/chat/commands/impl/HelpCommand\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,99:1\n777#2:100\n873#2,2:101\n1739#2:103\n1814#2,3:104\n*S KotlinDebug\n*F\n+ 1 HelpCommand.kt\nrtx/kimiko/api/chat/commands/impl/HelpCommand\n*L\n21#1:100\n21#1:101,2\n23#1:103\n23#1:104,3\n*E\n"})
public final class HelpCommand
extends Command {
    public HelpCommand() {
        super("help", "Shows available commands", new String[0]);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void execute(@NotNull String label, @NotNull String[] args) {
        Intrinsics.checkNotNullParameter((Object)label, (String)"label");
        Intrinsics.checkNotNullParameter((Object)args, (String)"args");
        CommandManager manager = CommandManager.Companion.getInstance();
        if (args.length == 0 || this.isInteger(args[0])) {
            int page = !(args.length == 0) && this.isInteger(args[0]) ? Integer.parseInt(args[0]) : 1;
            List<Command> commands = new ArrayList<>();
            for (Command c : manager.getCommands()) {
                if (!c.hiddenFromHelp()) {
                    commands.add(c);
                }
            }
            List<String> rowTexts = new ArrayList<>(commands.size());
            for (Command c : commands) {
                rowTexts.add(manager.getPrefix() + c.getName() + " - " + c.getShortDesc());
            }
            String title = "COMMANDS";
            int lineCount = CommandDividers.calcLineCountForContent(title, rowTexts);
            Paginator<Command> paginator = new Paginator<Command>(commands, 0, 2, null);
            paginator.setPage(page);
            int finalLineCount = lineCount;
            paginator.display(() -> HelpCommand.execute$lambda$2(this, title, finalLineCount), arg_0 -> HelpCommand.execute$lambda$3(manager, label, arg_0), manager.getPrefix() + label);
            this.logDirectRaw(CommandDividers.footer(title, finalLineCount));
            return;
        }
        Command command = manager.getCommand(args[0]);
        if (command == null) {
            this.logDirect("Command '" + args[0] + "' not found.", Formatting.RED);
            return;
        }
        String string = command.getName().toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toUpperCase(...)");
        String title = string;
        List<String> descLines = command.getLongDesc();
        int lineCount = CommandDividers.calcLineCountForContent(title, (Iterable<String>)descLines);
        this.logDirectRaw(CommandDividers.header(title, lineCount));
        String prefix = manager.getPrefix();
        int n = ((Collection)descLines).size();
        for (int i = 0; i < n; ++i) {
            String line = descLines.get(i);
            if (((CharSequence)line).length() == 0) continue;
            if (i == 0) {
                MutableText mutableText2 = Text.literal((String)("Desc: " + line)).formatted(Formatting.GRAY);
                Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"withStyle(...)");
                this.logDirectRaw(mutableText2);
                continue;
            }
            if (line.startsWith("> ")) {
                String string2 = line.substring(2);
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
                MutableText mutableText3 = Text.literal((String)(prefix + string2)).formatted(Formatting.WHITE);
                Intrinsics.checkNotNullExpressionValue((Object)mutableText3, (String)"withStyle(...)");
                this.logDirectRaw(mutableText3);
                continue;
            }
            MutableText mutableText4 = Text.literal((String)line).formatted(Formatting.WHITE);
            Intrinsics.checkNotNullExpressionValue((Object)mutableText4, (String)"withStyle(...)");
            this.logDirectRaw(mutableText4);
        }
        this.logDirectRaw(CommandDividers.footer(title, lineCount));
    }

    @Override
    @NotNull
    public Stream<String> tabComplete(@NotNull String label, @NotNull String[] args) {
        Intrinsics.checkNotNullParameter((Object)label, (String)"label");
        Intrinsics.checkNotNullParameter((Object)args, (String)"args");
        if (args.length == 1) {
            return new TabCompleteHelper().filterPrefix(args[0]).addCommands(CommandManager.Companion.getInstance()).stream();
        }
        Stream<String> stream = Stream.empty();
        Intrinsics.checkNotNullExpressionValue(stream, (String)"empty(...)");
        return stream;
    }

    @Override
    @NotNull
    public List<String> getLongDesc() {
        return List.of("Shows command help.", "Usage:", "> help", "> help <command>");
    }

    private final boolean isInteger(String value) {
        boolean bl;
        try {
            Integer.parseInt(value);
            bl = true;
        }
        catch (NumberFormatException ignored) {
            bl = false;
        }
        return bl;
    }

    private static final void execute$lambda$2(HelpCommand this$0, String $title, int $finalLineCount) {
        this$0.logDirectRaw(CommandDividers.header($title, $finalLineCount));
    }

    private static final Style execute$lambda$3$0(MutableText $hover, CommandManager $manager, String $label, Command $command, Style style) {
        Intrinsics.checkNotNullParameter((Object)style, (String)"style");
        return style.withHoverEvent((HoverEvent)new HoverEvent.ShowText((Text)$hover)).withClickEvent((ClickEvent)new ClickEvent.RunCommand($manager.getPrefix() + $label + " " + $command.getName()));
    }

    private static final Text execute$lambda$3(CommandManager $manager, String $label, Command command) {
        Intrinsics.checkNotNullParameter((Object)command, (String)"command");
        String fullName = $manager.getPrefix() + command.getName();
        MutableText mutableText2 = Text.literal((String)fullName).formatted(Formatting.WHITE).append((Text)Text.literal((String)("\n" + command.getShortDesc())).formatted(Formatting.GRAY));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"append(...)");
        MutableText hover = mutableText2;
        return (Text)Text.literal((String)fullName).formatted(Formatting.WHITE).append((Text)Text.literal((String)(" - " + command.getShortDesc())).formatted(Formatting.GRAY)).styled(arg_0 -> HelpCommand.execute$lambda$3$0(hover, $manager, $label, command, arg_0));
    }
}

