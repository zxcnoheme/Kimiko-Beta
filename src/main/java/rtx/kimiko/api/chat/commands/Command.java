/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  net.minecraft.util.Formatting
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.text.MutableText
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.chat.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraft.util.Formatting;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.chat.commands.CommandManager;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.utils.chat.ChatMessage;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B/\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0005\"\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ%\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u00022\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005H&\u00a2\u0006\u0004\b\f\u0010\rJ+\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u000e2\u0006\u0010\t\u001a\u00020\u00022\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0019\u0010\u0012J\r\u0010\u001a\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0012J\u0013\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0013\u00a2\u0006\u0004\b\u001b\u0010\u0015J\u0013\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u0013\u00a2\u0006\u0004\b\u001c\u0010\u0015J\u0015\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010!\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u0002H\u0004\u00a2\u0006\u0004\b!\u0010\"J\u001f\u0010!\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u00022\u0006\u0010$\u001a\u00020#H\u0004\u00a2\u0006\u0004\b!\u0010%J\u000f\u0010&\u001a\u00020\u000bH\u0004\u00a2\u0006\u0004\b&\u0010'J\u0017\u0010!\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020(H\u0004\u00a2\u0006\u0004\b!\u0010*J\u0017\u0010!\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020+H\u0004\u00a2\u0006\u0004\b!\u0010,J\u0017\u0010-\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020(H\u0004\u00a2\u0006\u0004\b-\u0010*J\u0017\u0010-\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020+H\u0004\u00a2\u0006\u0004\b-\u0010,R\u0019\u00100\u001a\u00020.8\u0004X\u0085\u0004\u0092\u0002\u0002\b/\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0014\u00102\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0014\u00104\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00103R\u001a\u00105\u001a\b\u0012\u0004\u0012\u00020\u00020\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00106\u00a8\u00067"}, d2={"Lrtx/kimiko/api/chat/commands/Command;", "", "", "nameRaw", "descriptionRaw", "", "aliasesRaw", "<init>", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "label", "args", "", "execute", "(Ljava/lang/String;[Ljava/lang/String;)V", "Ljava/util/stream/Stream;", "tabComplete", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/util/stream/Stream;", "getShortDesc", "()Ljava/lang/String;", "", "getLongDesc", "()Ljava/util/List;", "", "hiddenFromHelp", "()Z", "getName", "getDescription", "getAliases", "getAllNames", "input", "matches", "(Ljava/lang/String;)Z", "message", "logDirect", "(Ljava/lang/String;)V", "Lnet/minecraft/Formatting;", "formatting", "(Ljava/lang/String;Lnet/minecraft/Formatting;)V", "usage", "()V", "Lnet/minecraft/Text;", "text", "(Lnet/minecraft/Text;)V", "Lnet/minecraft/MutableText;", "(Lnet/minecraft/MutableText;)V", "logDirectRaw", "Lnet/minecraft/MinecraftClient;", "Lkotlin/jvm/JvmField;", "mc", "Lnet/minecraft/MinecraftClient;", "name", "Ljava/lang/String;", "description", "aliases", "Ljava/util/List;", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Command.kt\nrtx/kimiko/api/chat/commands/Command\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,110:1\n1960#2,3:111\n*S KotlinDebug\n*F\n+ 1 Command.kt\nrtx/kimiko/api/chat/commands/Command\n*L\n47#1:111,3\n*E\n"})
public abstract class Command {
    @JvmField
    @NotNull
    protected final MinecraftClient mc;
    @NotNull
    private final String name;
    @NotNull
    private final String description;
    @NotNull
    private final List<String> aliases;

    protected Command(@NotNull String nameRaw, @Nullable String descriptionRaw, String ... aliasesRaw) {
        Intrinsics.checkNotNullParameter((Object)nameRaw, (String)"nameRaw");
        Intrinsics.checkNotNullParameter((Object)aliasesRaw, (String)"aliasesRaw");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        this.mc = minecraftClient2;
        String string = nameRaw.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toLowerCase(...)");
        this.name = string;
        String string2 = descriptionRaw;
        if (string2 == null) {
            string2 = "";
        }
        this.description = string2;
        this.aliases = List.of(Arrays.copyOf(aliasesRaw, aliasesRaw.length));
    }

    public abstract void execute(@NotNull String var1, @NotNull String[] var2);

    @NotNull
    public Stream<String> tabComplete(@NotNull String label, @NotNull String[] args) {
        Intrinsics.checkNotNullParameter((Object)label, (String)"label");
        Intrinsics.checkNotNullParameter((Object)args, (String)"args");
        Stream<String> stream = Stream.empty();
        Intrinsics.checkNotNullExpressionValue(stream, (String)"empty(...)");
        return stream;
    }

    @NotNull
    public String getShortDesc() {
        return I18n.tr(this.description);
    }

    @NotNull
    public List<String> getLongDesc() {
        return List.of(I18n.tr(this.description), "", "Usage:", "> " + this.name + " - " + I18n.tr(this.description));
    }

    public boolean hiddenFromHelp() {
        return false;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @NotNull
    public final List<String> getAliases() {
        return this.aliases;
    }

    @NotNull
    public final List<String> getAllNames() {
        ArrayList<String> names = new ArrayList<String>();
        names.add(this.name);
        names.addAll((Collection)this.aliases);
        return names;
    }

    public final boolean matches(@NotNull String input) {
        boolean bl;
        block4: {
            Intrinsics.checkNotNullParameter((Object)input, (String)"input");
            if (StringsKt.equals((String)this.name, (String)input, (boolean)true)) {
                return true;
            }
            Iterable $this$any$iv = this.aliases;
            boolean $i$f$any = false;
            if ($this$any$iv instanceof Collection && ((Collection)$this$any$iv).isEmpty()) {
                bl = false;
            } else {
                for (Object element$iv : $this$any$iv) {
                    String it = (String)element$iv;
                    boolean bl2 = false;
                    if (!StringsKt.equals((String)it, (String)input, (boolean)true)) continue;
                    bl = true;
                    break block4;
                }
                bl = false;
            }
        }
        return bl;
    }

    protected final void logDirect(@NotNull String message) {
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        ChatMessage.brandmessage(message);
    }

    protected final void logDirect(@NotNull String message, @NotNull Formatting formatting) {
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        Intrinsics.checkNotNullParameter((Object)formatting, (String)"formatting");
        CommandManager manager = CommandManager.Companion.getInstance();
        if (manager == null) {
            ChatMessage.brandmessage(message);
            return;
        }
        if (formatting == Formatting.RED) {
            manager.sendError(message);
        } else if (formatting == Formatting.GREEN) {
            manager.sendSuccess(message);
        } else {
            manager.sendMessage(message);
        }
    }

    protected final void usage() {
        Sounds.play("command_error");
        CommandManager manager = CommandManager.Companion.getInstance();
        Object object = manager;
        if (object == null || (object = ((CommandManager)object).getPrefix()) == null) {
            object = ".";
        }
        Object prefix = object;
        String helpRef = (String)prefix + "help " + this.name;
        Object[] objectArray = new Object[]{this.name};
        MutableText mutableText2 = Text.literal((String)I18n.tr("Команда %s использована некорректно. Используйте ", objectArray)).formatted(Formatting.RED);
        objectArray = new Formatting[]{Formatting.RED, Formatting.BOLD};
        MutableText mutableText3 = mutableText2.append((Text)Text.literal((String)helpRef).formatted((Formatting[])objectArray)).append((Text)Text.literal((String)I18n.tr(" чтобы посмотреть список команд.")).formatted(Formatting.RED));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText3, (String)"append(...)");
        MutableText message = mutableText3;
        if (manager == null) {
            ChatMessage.brandmessage((Text)message);
            return;
        }
        MutableText mutableText4 = ChatMessage.brandmessage().copy().append((Text)Text.literal((String)"\u2192 ").formatted(Formatting.DARK_GRAY)).append((Text)message);
        Intrinsics.checkNotNullExpressionValue((Object)mutableText4, (String)"append(...)");
        manager.sendRaw((Text)mutableText4);
    }

    protected final void logDirect(@NotNull Text text) {
        Intrinsics.checkNotNullParameter((Object)text, (String)"text");
        ChatMessage.brandmessage(text);
    }

    protected final void logDirect(@NotNull MutableText text) {
        Intrinsics.checkNotNullParameter((Object)text, (String)"text");
        ChatMessage.brandmessage((Text)text);
    }

    protected final void logDirectRaw(@NotNull Text text) {
        Intrinsics.checkNotNullParameter((Object)text, (String)"text");
        CommandManager.Companion.getInstance().sendRaw(text);
    }

    protected final void logDirectRaw(@NotNull MutableText text) {
        Intrinsics.checkNotNullParameter((Object)text, (String)"text");
        CommandManager.Companion.getInstance().sendRaw((Text)text);
    }
}

