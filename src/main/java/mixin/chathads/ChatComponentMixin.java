/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.sugar.Local
 *  net.minecraft.client.gui.hud.ChatHudLine
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.hud.ChatHud
 *  net.minecraft.client.gui.hud.ChatHud$Backend
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.chathads;

import com.llamalad7.mixinextras.sugar.Local;
import mods.chathads.ChatHeads;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ChatHud.class}, priority=990)
public abstract class ChatComponentMixin {
    @Inject(method={"render(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/font/TextRenderer;IIIZZ)V"}, at={@At(value="HEAD")})
    private static void chatheads$captureGuiGraphics(CallbackInfo ci, @Local(argsOnly=true) DrawContext guiGraphics) {
        ChatHeads.guiGraphics = guiGraphics;
    }

    @Inject(method={"render(Lnet/minecraft/client/font/DrawnTextConsumer;IIZ)V"}, at={@At(value="HEAD")})
    private static void chatheads$noGraphics(CallbackInfo ci) {
        ChatHeads.guiGraphics = null;
    }

    @Inject(method={"render(Lnet/minecraft/client/gui/hud/ChatHud$Backend;IIZ)V"}, at={@At(value="HEAD")})
    private static void chatheads$captureChatGraphicsAccess(CallbackInfo ci, @Local(argsOnly=true) ChatHud.Backend chatGraphicsAccess) {
        ChatHeads.chatGraphicsAccess = chatGraphicsAccess;
    }

    @ModifyArg(method={"method_75802"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ChatHud$Backend;fill(IIIII)V"), index=2)
    private static int chatheads$fixTextOverflow(int original) {
        return original + ChatHeads.getTextWidthDifference(ChatHeads.getLineData());
    }

    @Inject(method={"render(Lnet/minecraft/client/gui/hud/ChatHud$Backend;IIZ)V"}, at={@At(value="RETURN")})
    private static void chatheads$forgetGraphics(CallbackInfo ci) {
        ChatHeads.guiGraphics = null;
        ChatHeads.chatGraphicsAccess = null;
    }

    @Inject(method={"addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ChatHud;addVisibleMessage(Lnet/minecraft/client/gui/hud/ChatHudLine;)V")})
    private void chatheads$nonRefreshingPath(CallbackInfo ci) {
        ChatHeads.refreshing = false;
    }

    @ModifyArg(method={"refresh()V"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ChatHud;addVisibleMessage(Lnet/minecraft/client/gui/hud/ChatHudLine;)V"))
    private ChatHudLine chatheads$transferMessageOwner(ChatHudLine guiMessage) {
        ChatHeads.refreshing = true;
        ChatHeads.refreshingLineData = ChatHeads.getHeadData(guiMessage);
        return guiMessage;
    }

    @Inject(method={"refresh()V"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ChatHud;addVisibleMessage(Lnet/minecraft/client/gui/hud/ChatHudLine;)V", shift=At.Shift.AFTER)})
    private void chatheads$finishedRefreshing(CallbackInfo ci) {
        ChatHeads.refreshing = false;
    }
}

