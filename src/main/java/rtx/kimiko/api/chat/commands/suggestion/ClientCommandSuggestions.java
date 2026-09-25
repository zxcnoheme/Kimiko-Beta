/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.font.TextRenderer
 *  net.minecraft.client.gui.widget.TextFieldWidget
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.gui.screen.ChatInputSuggestor
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.chat.commands.suggestion;

import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import mixin.accessor.CommandSuggestionsAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ChatInputSuggestor;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.chat.commands.CommandManager;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\n\u0012\u0006\u0010\u0011\u001a\u00020\r\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/api/chat/commands/suggestion/ClientCommandSuggestions;", "Lnet/minecraft/ChatInputSuggestor;", "Lnet/minecraft/MinecraftClient;", "minecraft", "Lnet/minecraft/Screen;", "screen", "Lnet/minecraft/TextFieldWidget;", "input", "Lnet/minecraft/TextRenderer;", "font", "", "commandsOnly", "onlyShowIfCursorPastError", "", "lineStartOffset", "suggestionLineLimit", "anchorToBottom", "fillColor", "<init>", "(Lnet/minecraft/MinecraftClient;Lnet/minecraft/Screen;Lnet/minecraft/TextFieldWidget;Lnet/minecraft/TextRenderer;ZZIIZI)V", "", "updateCommandInfo", "()V", "editBox", "Lnet/minecraft/TextFieldWidget;", "rtx.kimiko:kimiko"})
public class ClientCommandSuggestions
extends ChatInputSuggestor {
    @NotNull
    private final TextFieldWidget editBox;

    public ClientCommandSuggestions(@NotNull MinecraftClient minecraft, @NotNull Screen screen, @NotNull TextFieldWidget input, @NotNull TextRenderer font, boolean commandsOnly, boolean onlyShowIfCursorPastError, int lineStartOffset, int suggestionLineLimit, boolean anchorToBottom, int fillColor) {
        super(minecraft, screen, input, font, commandsOnly, onlyShowIfCursorPastError, lineStartOffset, suggestionLineLimit, anchorToBottom, fillColor);
        this.editBox = input;
    }

    public void refresh() {
        CompletableFuture<Suggestions> pending;
        String string = this.editBox.getText();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getValue(...)");
        String text = string;
        String prefix = CommandManager.Companion.get().getPrefix();
        Intrinsics.checkNotNull((Object)((Object)this), (String)"null cannot be cast to non-null type kotlin.Any");
        CommandSuggestionsAccessor accessor = (CommandSuggestionsAccessor)((Object)this);
        if (accessor.isKeepSuggestions()) {
            return;
        }
        if (!String.valueOf(text).startsWith(prefix)) {
            super.refresh();
            return;
        }
        CompletableFuture<Suggestions> completableFuture = pending = accessor.getPendingSuggestions();
        if (completableFuture != null) {
            completableFuture.cancel(false);
        }
        accessor.setPendingSuggestions(null);
        accessor.setCurrentParse(null);
        this.editBox.setSuggestion("");
        String string2 = text.substring(prefix.length());
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
        String commandPart = string2;
        int lastSpace = String.valueOf(text).lastIndexOf((char)' ');
        int tokenStart = lastSpace >= 0 ? lastSpace + 1 : 0;
        SuggestionsBuilder builder = new SuggestionsBuilder(text, tokenStart);
        Stream<String> completions = CommandManager.Companion.get().tabComplete(commandPart);
        if (lastSpace < 0) {
            completions.map(it -> prefix + it).forEach(builder::suggest);
        } else {
            completions.forEach(builder::suggest);
        }
        Suggestions suggestions = builder.build();
        if (suggestions.isEmpty()) {
            this.clearWindow();
            return;
        }
        accessor.setPendingSuggestions(CompletableFuture.completedFuture(suggestions));
        accessor.invokeShowSuggestions(false);
    }
}

