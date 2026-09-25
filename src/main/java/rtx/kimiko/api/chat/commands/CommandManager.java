/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  kotlin.Metadata
 *  kotlin.collections.ArraysKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.Regex
 *  kotlin.text.StringsKt
 *  net.minecraft.util.Formatting
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.text.MutableText
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.chat.commands;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.minecraft.util.Formatting;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.chat.commands.Command;
import rtx.kimiko.api.chat.commands.impl.BindCommand;
import rtx.kimiko.api.chat.commands.impl.CardsCommand;
import rtx.kimiko.api.chat.commands.impl.HelpCommand;
import rtx.kimiko.api.chat.commands.impl.MacroCommand;
import rtx.kimiko.api.chat.commands.impl.PartyChatCommand;
import rtx.kimiko.api.chat.commands.impl.PartyCommand;
import rtx.kimiko.api.chat.commands.impl.PrefixCommand;
import rtx.kimiko.api.chat.commands.impl.ViewModelCommand;
import rtx.kimiko.utils.chat.ChatMessage;
import rtx.kimiko.utils.sounds.Sounds;
import rtx.kimiko.utils.storage.RepositoryStorage;
import sigil.protect.Level;
import sigil.protect.Protect;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0007\u0018\u0000 92\u00020\u0001:\u00019B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0007b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003J'\u0010\f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0013\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0016\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0013\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0016\u0010\u0015J\u0013\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u0017\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u0017\u00a2\u0006\u0004\b\u001a\u0010\u0019J\r\u0010\u001b\u001a\u00020\u000e\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001e\u001a\u00020\u00042\b\u0010\u001d\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0004\b\u001e\u0010\u001fJ)\u0010 \u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0007b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b \u0010\u001fJ\u001d\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0!2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0004\b\"\u0010#J\u001d\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000e0!2\u0006\u0010$\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b%\u0010#J\r\u0010&\u001a\u00020\u0004\u00a2\u0006\u0004\b&\u0010\u0003J\u0017\u0010)\u001a\u00020\u00042\b\u0010(\u001a\u0004\u0018\u00010'\u00a2\u0006\u0004\b)\u0010*J\u0015\u0010,\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u000e\u00a2\u0006\u0004\b,\u0010\u001fJ\u0015\u0010-\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u000e\u00a2\u0006\u0004\b-\u0010\u001fJ\u0015\u0010.\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u000e\u00a2\u0006\u0004\b.\u0010\u001fJ\u0015\u00101\u001a\u00020\u00042\u0006\u00100\u001a\u00020/\u00a2\u0006\u0004\b1\u00102R\u001a\u00104\u001a\b\u0012\u0004\u0012\u00020\n038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0016\u0010\u001d\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u00106R\u0018\u00107\u001a\u0004\u0018\u00010'8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00108\u00a8\u0006:"}, d2={"Lrtx/kimiko/api/chat/commands/CommandManager;", "", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "init", "Lrtx/kimiko/api/chat/commands/Command;", "command", "register", "(Lrtx/kimiko/api/chat/commands/Command;)V", "", "input", "", "isClientCommand", "(Ljava/lang/String;)Z", "name", "getCommand", "(Ljava/lang/String;)Lrtx/kimiko/api/chat/commands/Command;", "find", "", "getCommands", "()Ljava/util/List;", "getAll", "getPrefix", "()Ljava/lang/String;", "prefix", "setPrefix", "(Ljava/lang/String;)V", "executeRaw", "Ljava/util/stream/Stream;", "tabComplete", "(Ljava/lang/String;)Ljava/util/stream/Stream;", "partial", "getCommandSuggestions", "refreshRuntimeState", "Ljava/lang/Runnable;", "callback", "setStateRefreshCallback", "(Ljava/lang/Runnable;)V", "message", "sendMessage", "sendSuccess", "sendError", "Lnet/minecraft/Text;", "text", "sendRaw", "(Lnet/minecraft/Text;)V", "", "commands", "Ljava/util/List;", "Ljava/lang/String;", "onStateRefresh", "Ljava/lang/Runnable;", "Companion", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nCommandManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CommandManager.kt\nrtx/kimiko/api/chat/commands/CommandManager\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,171:1\n296#2,2:172\n37#3,2:174\n37#3,2:176\n*S KotlinDebug\n*F\n+ 1 CommandManager.kt\nrtx/kimiko/api/chat/commands/CommandManager\n*L\n57#1:172,2\n82#1:174,2\n97#1:176,2\n*E\n"})
public final class CommandManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final List<Command> commands = new CopyOnWriteArrayList();
    @NotNull
    private String prefix = ".";
    @Nullable
    private Runnable onStateRefresh;
    @Nullable
    private static CommandManager instance;

    private CommandManager() {
        instance = this;
    }

    @Protect(value=Level.CROWN)
    public final void init() {
        String string;
        JsonObject cfg = RepositoryStorage.readObject("prefix");
        if (cfg.has("prefix")) {
            String string2 = cfg.get("prefix").getAsString();
            string = string2;
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getAsString(...)");
        } else {
            string = ".";
        }
        this.prefix = string;
        this.register(new HelpCommand());
        this.register(new MacroCommand());
        this.register(new BindCommand());
        this.register(new PrefixCommand());
        this.register(new PartyCommand());
        this.register(new PartyChatCommand());
        this.register(new CardsCommand());
        this.register(new ViewModelCommand());
    }

    @Protect(value=Level.CROWN)
    public final void register(@NotNull Command command) {
        Intrinsics.checkNotNullParameter((Object)command, (String)"command");
        this.commands.add(command);
    }

    public final boolean isClientCommand(@Nullable String input) {
        return input != null && String.valueOf(input).startsWith(this.prefix);
    }

    @Nullable
    public final Command getCommand(@NotNull String name) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        for (Command cmd : this.commands) {
            if (cmd.matches(name)) {
                return cmd;
            }
        }
        return null;
    }

    @Nullable
    public final Command find(@NotNull String name) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        return this.getCommand(name);
    }

    @NotNull
    public final List<Command> getCommands() {
        return new ArrayList(this.commands);
    }

    @NotNull
    public final List<Command> getAll() {
        List<Command> list = Collections.unmodifiableList(this.commands);
        Intrinsics.checkNotNullExpressionValue(list, (String)"unmodifiableList(...)");
        return list;
    }

    @NotNull
    public final String getPrefix() {
        return this.prefix;
    }

    public final void setPrefix(@Nullable String prefix) {
        this.prefix = prefix == null || StringsKt.isBlank((CharSequence)prefix) ? "." : prefix;
        JsonObject obj = new JsonObject();
        obj.addProperty("prefix", this.prefix);
        RepositoryStorage.write("prefix", obj);
    }

    /*
     * WARNING - void declaration
     */
    @Protect(value=Level.CROWN)
    public final void executeRaw(@Nullable String input) {
        String[] stringArray;
        if (input == null || StringsKt.isBlank((CharSequence)input)) {
            this.executeRaw("help");
            return;
        }
        CharSequence charSequence = ((Object)StringsKt.trim((CharSequence)input)).toString();
        Regex regex = new Regex("\\s+");
        int n = 2;
        List parts = regex.split(charSequence, n);
        String label = (String)parts.get(0);
        if (parts.size() > 1) {
            stringArray = ((String)parts.get(1)).split("\\s+");
        } else {
            stringArray = new String[]{};
        }
        String[] args = stringArray;
        Command command = this.getCommand(label);
        if (command == null) {
            this.sendError("Unknown command. Use " + this.prefix + "help.");
            return;
        }
        try {
            command.execute(label, args);
        }
        catch (Exception ex) {
            this.sendError("Command error: " + ex.getMessage());
        }
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public final Stream<String> tabComplete(@Nullable String input) {
        String text = input == null ? "" : input;
        String[] args = text.split("\\s+", -1);
        if (args.length <= 1) {
            String string2;
            if (args.length == 0) {
                string2 = "";
            } else {
                String string3 = args[0].toLowerCase(Locale.ROOT);
                string2 = string3;
                Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toLowerCase(...)");
            }
            String partial = string2;
            return this.getCommandSuggestions(partial);
        }
        Command command = this.getCommand(args[0]);
        if (command != null) {
            return command.tabComplete(args[0], (String[])ArraysKt.copyOfRange((Object[])args, (int)1, (int)args.length));
        }
        Stream<String> stream = Stream.empty();
        Intrinsics.checkNotNullExpressionValue(stream, (String)"empty(...)");
        return stream;
    }

    private final Stream<String> getCommandSuggestions(String partial) {
        LinkedHashSet<String> suggestions = new LinkedHashSet<String>();
        for (Command command : this.commands) {
            String string = command.getName().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toLowerCase(...)");
            if (String.valueOf(string).startsWith(partial)) {
                suggestions.add(command.getName());
            }
            for (String alias : command.getAliases()) {
                String string2 = alias.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
                if (!String.valueOf(string2).startsWith(partial)) continue;
                suggestions.add(alias);
            }
        }
        Stream<String> stream = suggestions.stream().sorted();
        Intrinsics.checkNotNullExpressionValue(stream, (String)"sorted(...)");
        return stream;
    }

    public final void refreshRuntimeState() {
        block0: {
            Runnable runnable = this.onStateRefresh;
            if (runnable == null) break block0;
            runnable.run();
        }
    }

    public final void setStateRefreshCallback(@Nullable Runnable callback) {
        this.onStateRefresh = callback;
    }

    public final void sendMessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        ChatMessage.brandmessage(message);
    }

    public final void sendSuccess(@NotNull String message) {
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        MutableText mutableText2 = ChatMessage.brandmessage().copy().append((Text)Text.literal((String)"\u2192 ").formatted(Formatting.DARK_GRAY)).append((Text)Text.literal((String)message).formatted(Formatting.WHITE));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"append(...)");
        this.sendRaw((Text)mutableText2);
    }

    public final void sendError(@NotNull String message) {
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        Sounds.play("command_error");
        MutableText mutableText2 = ChatMessage.brandmessage().copy().append((Text)Text.literal((String)"\u2192 ").formatted(Formatting.DARK_GRAY)).append((Text)Text.literal((String)message).formatted(Formatting.RED));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"append(...)");
        this.sendRaw((Text)mutableText2);
    }

    public final void sendRaw(@NotNull Text text) {
        block0: {
            ClientPlayerEntity player;
            Intrinsics.checkNotNullParameter((Object)text, (String)"text");
            ClientPlayerEntity clientPlayerEntity2 = player = MinecraftClient.getInstance().player;
            if (clientPlayerEntity2 == null) break block0;
            clientPlayerEntity2.sendMessage(text, false);
        }
    }

    @JvmStatic
    @NotNull
    public static final CommandManager get() {
        return Companion.get();
    }

    @JvmStatic
    @NotNull
    public static final CommandManager getInstance() {
        return Companion.getInstance();
    }

    public /* synthetic */ CommandManager(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/api/chat/commands/CommandManager.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/chat/commands/CommandManager;", "Lkotlin/jvm/JvmStatic;", "get", "()Lrtx/kimiko/api/chat/commands/CommandManager;", "getInstance", "instance", "Lrtx/kimiko/api/chat/commands/CommandManager;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final CommandManager get() {
            if (instance == null) {
                new CommandManager(null);
            }
            CommandManager commandManager = instance;
            Intrinsics.checkNotNull((Object)commandManager);
            return commandManager;
        }

        @JvmStatic
        @NotNull
        public final CommandManager getInstance() {
            return this.get();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

