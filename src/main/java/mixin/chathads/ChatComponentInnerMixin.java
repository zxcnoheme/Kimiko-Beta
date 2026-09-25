/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.sugar.Local
 *  com.llamalad7.mixinextras.sugar.Share
 *  com.llamalad7.mixinextras.sugar.ref.LocalIntRef
 *  net.minecraft.client.gui.hud.ChatHudLine$Visible
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArgs
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.invoke.arg.Args
 */
package mixin.chathads;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import mods.chathads.ChatHeads;
import mods.chathads.HeadData;
import mods.chathads.config.RenderPosition;
import net.minecraft.client.gui.hud.ChatHudLine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(targets={"net/minecraft/client/gui/hud/ChatHud$1"})
public abstract class ChatComponentInnerMixin {
    @ModifyArgs(method={"accept"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ChatHud$Backend;text(IFLnet/minecraft/text/OrderedText;)Z"))
    private void chatheads$renderChatHeadAndOffsetChatMessage(Args args, @Local(argsOnly=true) ChatHudLine.Visible line, @Share(value="chatOffset") LocalIntRef chatOffset) {
        if (ChatHeads.CONFIG.renderPosition() == RenderPosition.BEFORE_LINE) {
            int y = (Integer)args.get(0);
            float opacity = ((Float)args.get(1)).floatValue();
            HeadData headData = ChatHeads.getHeadData(line);
            chatOffset.set(ChatHeads.getChatOffset(headData));
            if (ChatHeads.guiGraphics != null && headData != HeadData.EMPTY) {
                ChatHeads.renderChatHead(ChatHeads.guiGraphics, 0, y, headData.playerInfo, opacity);
            }
            ChatHeads.chatGraphicsAccess.updatePose(matrix3x2f -> matrix3x2f.translate((float)chatOffset.get(), 0.0f));
        }
    }

    @Inject(method={"accept"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ChatHud$Backend;text(IFLnet/minecraft/text/OrderedText;)Z", shift=At.Shift.AFTER)})
    private void chatheads$undoChatMessageOffset(CallbackInfo ci, @Share(value="chatOffset") LocalIntRef chatOffset) {
        if (ChatHeads.CONFIG.renderPosition() == RenderPosition.BEFORE_LINE) {
            ChatHeads.chatGraphicsAccess.updatePose(matrix3x2f -> matrix3x2f.translate((float)(-chatOffset.get()), 0.0f));
        }
    }

    @Inject(method={"accept"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ChatHud$Backend;indicatorIcon(IIZLnet/minecraft/client/gui/hud/MessageIndicator;Lnet/minecraft/client/gui/hud/MessageIndicator$Icon;)V")})
    private void chatheads$offsetTagIcon(CallbackInfo ci, @Share(value="chatOffset") LocalIntRef chatOffset) {
        if (ChatHeads.CONFIG.renderPosition() == RenderPosition.BEFORE_LINE) {
            ChatHeads.chatGraphicsAccess.updatePose(matrix3x2f -> matrix3x2f.translate((float)chatOffset.get(), 0.0f));
        }
    }

    @Inject(method={"accept"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ChatHud$Backend;indicatorIcon(IIZLnet/minecraft/client/gui/hud/MessageIndicator;Lnet/minecraft/client/gui/hud/MessageIndicator$Icon;)V", shift=At.Shift.AFTER)})
    private void chatheads$undoTagIconOffset(CallbackInfo ci, @Share(value="chatOffset") LocalIntRef chatOffset) {
        if (ChatHeads.CONFIG.renderPosition() == RenderPosition.BEFORE_LINE) {
            ChatHeads.chatGraphicsAccess.updatePose(matrix3x2f -> matrix3x2f.translate((float)(-chatOffset.get()), 0.0f));
        }
    }
}

