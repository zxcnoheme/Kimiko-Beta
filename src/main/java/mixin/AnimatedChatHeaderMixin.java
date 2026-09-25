/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.text.OrderedText
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyArgs
 *  org.spongepowered.asm.mixin.injection.invoke.arg.Args
 */
package mixin;

import net.minecraft.text.OrderedText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import rtx.kimiko.api.chat.commands.helpers.AnimatedChatText;

@Mixin(targets={"net/minecraft/client/gui/hud/ChatHud$1"})
public abstract class AnimatedChatHeaderMixin {
    @ModifyArgs(method={"accept"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ChatHud$Backend;text(IFLnet/minecraft/text/OrderedText;)Z"))
    private void kimiko$animateHeader(Args args) {
        OrderedText text = (OrderedText)args.get(2);
        if (text != null && AnimatedChatText.hasSentinel(text)) {
            args.set(2, (Object)AnimatedChatText.animate(text));
        }
    }
}

