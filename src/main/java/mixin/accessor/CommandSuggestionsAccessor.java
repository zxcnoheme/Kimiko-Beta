/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.ParseResults
 *  com.mojang.brigadier.suggestion.Suggestions
 *  net.minecraft.client.gui.screen.ChatInputSuggestor
 *  net.minecraft.text.OrderedText
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package mixin.accessor;

import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.suggestion.Suggestions;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.gui.screen.ChatInputSuggestor;
import net.minecraft.text.OrderedText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={ChatInputSuggestor.class})
public interface CommandSuggestionsAccessor {
    @Accessor(value="pendingSuggestions")
    public CompletableFuture<Suggestions> getPendingSuggestions();

    @Accessor(value="pendingSuggestions")
    public void setPendingSuggestions(CompletableFuture<Suggestions> var1);

    @Accessor(value="parse")
    public ParseResults<?> getCurrentParse();

    @Accessor(value="parse")
    public void setCurrentParse(ParseResults<?> var1);

    @Accessor(value="messages")
    public List<OrderedText> getCommandUsage();

    @Accessor(value="completingSuggestions")
    public boolean isKeepSuggestions();

    @Invoker(value="show")
    public void invokeShowSuggestions(boolean var1);
}

