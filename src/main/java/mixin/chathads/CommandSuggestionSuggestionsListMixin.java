/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.sugar.Share
 *  com.llamalad7.mixinextras.sugar.ref.LocalRef
 *  com.mojang.brigadier.suggestion.Suggestion
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.screen.ChatInputSuggestor
 *  net.minecraft.client.gui.screen.ChatInputSuggestor$SuggestionWindow
 *  net.minecraft.client.network.ClientPlayNetworkHandler
 *  net.minecraft.client.network.PlayerListEntry
 *  net.minecraft.client.util.math.Rect2i
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.chathads;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.mojang.brigadier.suggestion.Suggestion;
import java.util.List;
import mods.chathads.ChatHeads;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatInputSuggestor;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.math.Rect2i;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ChatInputSuggestor.SuggestionWindow.class})
public abstract class CommandSuggestionSuggestionsListMixin {
    @Shadow
    @Final
    private Rect2i area;
    @Shadow
    @Final
    private List<Suggestion> suggestions;

    @ModifyVariable(method={"render"}, at=@At(value="HEAD"), argsOnly=true)
    public DrawContext chatheads$captureGuiGraphics(DrawContext guiGraphics, @Share(value="graphics") LocalRef<DrawContext> graphicsRef) {
        graphicsRef.set(guiGraphics);
        return guiGraphics;
    }

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    public void chatheads$fixOutOfBoundChatHeads(ChatInputSuggestor commandSuggestions, int x, int y, int width, List<Suggestion> suggestions, boolean narrateFirstSuggestion, CallbackInfo ci) {
        ClientPlayNetworkHandler connection = MinecraftClient.getInstance().getNetworkHandler();
        if (connection == null) {
            return;
        }
        if (this.area.getX() - (ChatHeads.headWidth(false) + 2) < 3) {
            for (Suggestion suggestion : this.suggestions) {
                PlayerListEntry playerInfo = connection.getPlayerListEntry(suggestion.getText());
                if (playerInfo == null) continue;
                this.area.setStartPos(3 + (ChatHeads.headWidth(false) + 2), this.area.getY());
                break;
            }
        }
    }

    @ModifyVariable(method={"render"}, at=@At(value="STORE"), ordinal=0)
    public Suggestion chatheads$captureSuggestion(Suggestion suggestion, @Share(value="player") LocalRef<PlayerListEntry> playerRef) {
        ClientPlayNetworkHandler connection = MinecraftClient.getInstance().getNetworkHandler();
        if (connection == null) {
            return suggestion;
        }
        playerRef.set(connection.getPlayerListEntry(suggestion.getText()));
        return suggestion;
    }

    @ModifyArg(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V", ordinal=4), index=0)
    public int chatheads$enlargeBackground(int x, @Share(value="player") LocalRef<PlayerListEntry> playerRef) {
        if (playerRef.get() != null) {
            return x - (ChatHeads.headWidth(false) + 2);
        }
        return x;
    }

    @ModifyArg(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)V", ordinal=0), index=3)
    public int chatheads$renderChatHead(int y, @Share(value="player") LocalRef<PlayerListEntry> playerRef, @Share(value="graphics") LocalRef<DrawContext> graphicsRef) {
        int x = this.area.getX() - ChatHeads.headWidth(false);
        if (playerRef.get() != null) {
            ChatHeads.renderChatHead((DrawContext)graphicsRef.get(), x, y, (PlayerListEntry)playerRef.get(), 1.0f, false);
        }
        return y;
    }
}

