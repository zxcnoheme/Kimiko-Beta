/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.ArraysKt
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.jvm.internal.StringCompanionObject
 *  kotlin.text.StringsKt
 *  net.minecraft.util.Formatting
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.chat.commands.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.chat.commands.Command;
import rtx.kimiko.api.chat.commands.helpers.CommandDividers;
import rtx.kimiko.api.chat.commands.helpers.TabCompleteHelper;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.ClickGui;
import rtx.kimiko.utils.chat.ChatMessage;
import rtx.kimiko.utils.key.KeyBind;
import rtx.kimiko.utils.key.KeyHelper;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ+\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000eH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J%\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J%\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0017\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/chat/commands/impl/BindCommand;", "Lrtx/kimiko/api/chat/commands/Command;", "<init>", "()V", "", "label", "", "args", "", "execute", "(Ljava/lang/String;[Ljava/lang/String;)V", "Ljava/util/stream/Stream;", "tabComplete", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/util/stream/Stream;", "", "getLongDesc", "()Ljava/util/List;", "Lrtx/kimiko/api/modules/ModuleManager;", "modules", "setBind", "(Lrtx/kimiko/api/modules/ModuleManager;[Ljava/lang/String;)V", "clearBind", "clearAll", "(Lrtx/kimiko/api/modules/ModuleManager;)V", "listBinds", "Companion", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nBindCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BindCommand.kt\nrtx/kimiko/api/chat/commands/impl/BindCommand\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,166:1\n37#2,2:167\n37#2,2:173\n37#2,2:182\n1739#3:169\n1814#3,3:170\n777#3:175\n873#3,2:176\n1739#3:178\n1814#3,3:179\n777#3:184\n873#3,2:185\n777#3:187\n873#3,2:188\n1739#3:190\n1814#3,3:191\n*S KotlinDebug\n*F\n+ 1 BindCommand.kt\nrtx/kimiko/api/chat/commands/impl/BindCommand\n*L\n43#1:167,2\n49#1:173,2\n60#1:182,2\n49#1:169\n49#1:170,3\n58#1:175\n58#1:176,2\n59#1:178\n59#1:179,3\n130#1:184\n130#1:185,2\n131#1:187\n131#1:188,2\n139#1:190\n139#1:191,3\n*E\n"})
public final class BindCommand
extends Command {
    @NotNull
    public static final Companion Companion = new Companion(null);

    public BindCommand() {
        super("bind", "Manage module binds", new String[]{"b"});
    }

    @Override
    public void execute(@NotNull String label, @NotNull String[] args) {
        Intrinsics.checkNotNullParameter((Object)label, (String)"label");
        Intrinsics.checkNotNullParameter((Object)args, (String)"args");
        ModuleManager modules = ModuleManager.Companion.get();
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
            case "add": 
            case "set": {
                this.setBind(modules, args);
                break;
            }
            case "del": 
            case "delete": 
            case "remove": {
                this.clearBind(modules, args);
                break;
            }
            case "clear": {
                this.clearAll(modules);
                break;
            }
            case "list": {
                this.listBinds(modules);
                break;
            }
            default: {
                this.usage();
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    @Override
    @NotNull
    public Stream<String> tabComplete(@NotNull String label, @NotNull String[] args) {
        Intrinsics.checkNotNullParameter((Object)label, (String)"label");
        Intrinsics.checkNotNullParameter((Object)args, (String)"args");
        if (args.length == 1) {
            String[] stringArray = new String[]{"add", "remove", "list", "clear"};
            return new TabCompleteHelper().append(stringArray).sortAlphabetically().filterPrefix(args[0]).stream();
        }
        if (args.length >= 2 && BindCommand.Companion.isAddAction(args[0])) {
            String moduleCandidate = BindCommand.Companion.joinArgs(args, 1, args.length - 1);
            if (args.length >= 3 && ModuleManager.Companion.get().findByName(moduleCandidate) != null) {
                Collection<String> allKeyNames = KeyHelper.getAllKeyNames();
                String[] stringArray = allKeyNames.toArray(new String[0]);
                return new TabCompleteHelper().append(Arrays.copyOf(stringArray, stringArray.length)).filterPrefix(args[args.length - 1]).stream();
            }
            String prefix = BindCommand.Companion.joinArgs(args, 1, args.length);
            List<Module> allModules = ModuleManager.Companion.get().getAll();
            ArrayList<String> names = new ArrayList<>(allModules.size());
            for (Module m : allModules) {
                names.add(m.getName());
            }
            return new TabCompleteHelper().append(names).filterPrefix(prefix).stream();
        }
        if (args.length >= 2 && BindCommand.Companion.isRemoveAction(args[0])) {
            String prefix = BindCommand.Companion.joinArgs(args, 1, args.length);
            List<Module> allModules = ModuleManager.Companion.get().getAll();
            ArrayList<String> names = new ArrayList<>();
            for (Module m : allModules) {
                if (m.getBind().isBound() && !(m instanceof ClickGui)) {
                    names.add(m.getName());
                }
            }
            return new TabCompleteHelper().append(names).filterPrefix(prefix).stream();
        }
        Stream<String> stream = Stream.empty();
        Intrinsics.checkNotNullExpressionValue(stream, (String)"empty(...)");
        return stream;
    }

    @Override
    @NotNull
    public List<String> getLongDesc() {
        return List.of("Manages module key binds.", "Usage:", "> bind add <module> <key>", "> bind remove <module>", "> bind list", "> bind clear");
    }

    private final void setBind(ModuleManager modules, String[] args) {
        if (args.length < 3) {
            this.logDirect("Usage: bind add <module> <key>", Formatting.RED);
            return;
        }
        String keyArg = args[args.length - 1];
        String moduleName = BindCommand.Companion.joinArgs(args, 1, args.length - 1);
        Module module = modules.findByName(moduleName);
        if (module == null) {
            this.logDirect("Module not found: " + moduleName, Formatting.RED);
            return;
        }
        int code = KeyHelper.getKeyCode(keyArg);
        if (code == -1 || code < 0) {
            this.logDirect("Unknown key: " + keyArg, Formatting.RED);
            return;
        }
        module.setBind(KeyHelper.isMouse(code) ? KeyBind.Companion.mouse(code) : KeyBind.Companion.keyboard(code));
        this.logDirect(ChatMessage.accentGradient(module.getName() + " bound to " + module.getBind().getDisplayName()));
    }

    private final void clearBind(ModuleManager modules, String[] args) {
        if (args.length < 2) {
            this.logDirect("Usage: bind remove <module>", Formatting.RED);
            return;
        }
        String moduleName = BindCommand.Companion.joinArgs(args, 1, args.length);
        Module module = modules.findByName(moduleName);
        if (module == null) {
            this.logDirect("Module not found: " + moduleName, Formatting.RED);
            return;
        }
        if (module instanceof ClickGui) {
            this.logDirect("ClickGui bind is protected and cannot be removed.", Formatting.RED);
            return;
        }
        module.setBind(KeyBind.NONE);
        this.logDirect(ChatMessage.accentGradient(module.getName() + " bind removed."));
    }

    private final void clearAll(ModuleManager modules) {
        for (Module module : modules.getAll()) {
            if (module instanceof ClickGui) continue;
            module.setBind(KeyBind.NONE);
        }
        this.logDirect("All binds removed.");
    }

    /*
     * WARNING - void declaration
     */
    private final void listBinds(ModuleManager modules) {
        List<Module> bound = new ArrayList<>();
        for (Module m : modules.getAll()) {
            if (m.getBind().isBound() && !(m instanceof ClickGui)) {
                bound.add(m);
            }
        }
        bound.sort(Comparator.comparing(Module::getName, String.CASE_INSENSITIVE_ORDER));
        if (bound.isEmpty()) {
            this.logDirect("No module binds.", Formatting.RED);
            return;
        }
        String title = "BINDS";
        List<String> rowTexts = new ArrayList<>(bound.size());
        for (Module m : bound) {
            rowTexts.add(m.getName() + " -> " + m.getBind().getDisplayName());
        }
        int lineCount = CommandDividers.calcLineCountForContent(title, rowTexts);
        this.logDirectRaw(CommandDividers.header(title, lineCount));
        for (Module module : bound) {
            this.logDirect(module.getName() + " -> " + module.getBind().getDisplayName());
        }
        this.logDirectRaw(CommandDividers.footer(title, lineCount));
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\t\u0010\bJ-\u0010\u000f\u001a\u00020\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/chat/commands/impl/BindCommand.Companion;", "", "<init>", "()V", "", "action", "", "isAddAction", "(Ljava/lang/String;)Z", "isRemoveAction", "", "args", "", "from", "toExclusive", "joinArgs", "([Ljava/lang/String;II)Ljava/lang/String;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final boolean isAddAction(String action) {
            return StringsKt.equals((String)"add", (String)action, (boolean)true) || StringsKt.equals((String)"set", (String)action, (boolean)true);
        }

        private final boolean isRemoveAction(String action) {
            return StringsKt.equals((String)"remove", (String)action, (boolean)true) || StringsKt.equals((String)"del", (String)action, (boolean)true) || StringsKt.equals((String)"delete", (String)action, (boolean)true);
        }

        private final String joinArgs(String[] args, int from, int toExclusive) {
            if (from >= toExclusive) {
                return "";
            }
            return String.join(" ", java.util.Arrays.copyOfRange(args, from, toExclusive));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

