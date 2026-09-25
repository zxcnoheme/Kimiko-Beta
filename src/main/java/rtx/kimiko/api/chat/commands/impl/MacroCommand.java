/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.ArraysKt
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  net.minecraft.util.Formatting
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.chat.commands.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.chat.commands.Command;
import rtx.kimiko.api.chat.commands.helpers.CommandDividers;
import rtx.kimiko.api.chat.commands.helpers.TabCompleteHelper;
import rtx.kimiko.utils.key.KeyHelper;
import rtx.kimiko.utils.storage.macro.Macro;
import rtx.kimiko.utils.storage.macro.MacroRepository;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ+\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000eH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/chat/commands/impl/MacroCommand;", "Lrtx/kimiko/api/chat/commands/Command;", "<init>", "()V", "", "label", "", "args", "", "execute", "(Ljava/lang/String;[Ljava/lang/String;)V", "Ljava/util/stream/Stream;", "tabComplete", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/util/stream/Stream;", "", "getLongDesc", "()Ljava/util/List;", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nMacroCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MacroCommand.kt\nrtx/kimiko/api/chat/commands/impl/MacroCommand\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,105:1\n1739#2:106\n1814#2,3:107\n37#3,2:110\n37#3,2:112\n*S KotlinDebug\n*F\n+ 1 MacroCommand.kt\nrtx/kimiko/api/chat/commands/impl/MacroCommand\n*L\n65#1:106\n65#1:107,3\n86#1:110,2\n90#1:112,2\n*E\n"})
public final class MacroCommand
extends Command {
    public MacroCommand() {
        super("macro", "Manage macros", new String[0]);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void execute(@NotNull String label, @NotNull String[] args) {
        Intrinsics.checkNotNullParameter((Object)label, (String)"label");
        Intrinsics.checkNotNullParameter((Object)args, (String)"args");
        MacroRepository repo = MacroRepository.Companion.getInstance();
        if (args.length == 0) {
            this.usage();
            return;
        }
        String string = args[0];
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string2 = string.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
        switch (string2) {
            case "add": {
                if (args.length < 4) {
                    this.logDirect("Usage: macro add <name> <message> <key>", Formatting.RED);
                    return;
                }
                String name = args[1];
                String keyArg = args[args.length - 1];
                int key = KeyHelper.getKeyCode(keyArg);
                if (key < 0) {
                    this.logDirect("Unknown key: " + keyArg, Formatting.RED);
                    return;
                }
                String message = String.join(" ", java.util.Arrays.copyOfRange(args, 2, args.length - 1));
                repo.addMacroAndSave(name, message, key);
                this.logDirect("Macro " + name + " added [" + KeyHelper.getKeyName(key) + "]", Formatting.GREEN);
                break;
            }
            case "del": 
            case "delete": 
            case "remove": {
                if (args.length < 2) {
                    this.logDirect("Usage: macro remove <name>", Formatting.RED);
                    return;
                }
                repo.deleteMacroAndSave(args[1]);
                this.logDirect("Macro " + args[1] + " removed.", Formatting.GREEN);
                break;
            }
            case "clear": {
                int count = repo.size();
                repo.clearListAndSave();
                this.logDirect("Macros cleared. Removed: " + count, Formatting.GREEN);
                break;
            }
            case "list": {
                if (repo.getMacroList().isEmpty()) {
                    this.logDirect("Macro list is empty.", Formatting.RED);
                    return;
                }
                String title = "MACROS";
                List<Macro> macroList = repo.getMacroList();
                List<String> rowTexts = new ArrayList<>(macroList.size());
                for (Macro m : macroList) {
                    rowTexts.add(m.name() + " [" + KeyHelper.getKeyName(m.key()) + "] " + m.message());
                }
                int lineCount = CommandDividers.calcLineCountForContent(title, rowTexts);
                this.logDirectRaw(CommandDividers.header(title, lineCount));
                for (Macro macro : repo.getMacroList()) {
                    this.logDirect("\u00a7f" + macro.name() + " \u00a78[" + KeyHelper.getKeyName(macro.key()) + "] \u00a77" + macro.message());
                }
                this.logDirectRaw(CommandDividers.footer(title, lineCount));
                break;
            }
            default: {
                this.usage();
            }
        }
    }

    @Override
    @NotNull
    public Stream<String> tabComplete(@NotNull String label, @NotNull String[] args) {
        Intrinsics.checkNotNullParameter((Object)label, (String)"label");
        Intrinsics.checkNotNullParameter((Object)args, (String)"args");
        if (args.length == 1) {
            return new TabCompleteHelper().append("add", "remove", "list", "clear").sortAlphabetically().filterPrefix(args[0]).stream();
        }
        if (args.length >= 4 && args[0].equalsIgnoreCase("add")) {
            return new TabCompleteHelper().append(KeyHelper.getAllKeyNames().toArray(new String[0])).filterPrefix(args[args.length - 1]).stream();
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
            return new TabCompleteHelper().append(MacroRepository.Companion.getInstance().getMacroNames().toArray(new String[0])).filterPrefix(args[1]).stream();
        }
        return Stream.empty();
    }

    @Override
    @NotNull
    public List<String> getLongDesc() {
        return List.of("Manages macros.", "Usage:", "> macro add <name> <message> <key>", "> macro remove <name>", "> macro list", "> macro clear");
    }
}

