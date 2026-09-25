/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  com.llamalad7.mixinextras.sugar.Local
 *  net.minecraft.text.Text
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.hud.ChatHud
 *  net.minecraft.client.gui.hud.ChatHud$Backend
 *  net.minecraft.network.message.MessageSignatureData
 *  net.minecraft.client.gui.hud.MessageIndicator
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.chatanim;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import mods.chatanim.config.ModConfig;
import net.minecraft.text.Text;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.client.gui.hud.MessageIndicator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Visuals.BetterMinecraft;

@Mixin(value={ChatHud.class})
public abstract class ChatComponentMixin {
    @Shadow
    private int scrolledLines;
    @Unique
    private long kimiko$chatAnimLastMessageTime = 0L;

    @Shadow
    private int getLineHeight() {
        return 0;
    }

    @Unique
    private float kimiko$chatAnimCalculateDisplacement() {
        ModConfig config = ModConfig.getConfig();
        if (!BetterMinecraft.chatAnimationsEnabled() || !config.enableMessageAnimation || this.scrolledLines != 0) {
            return 0.0f;
        }
        float fadeTime = config.fadeTimeMessage;
        float maxDisplacement = (float)this.getLineHeight() * 0.8f;
        long lifetime = System.currentTimeMillis() - this.kimiko$chatAnimLastMessageTime;
        float alpha = Math.min((float)lifetime / fadeTime, 1.0f);
        return maxDisplacement - alpha * maxDisplacement;
    }

    @WrapOperation(method={"render(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/font/TextRenderer;IIIZZ)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/gui/hud/ChatHud$Backend;IIZ)V")})
    private void kimiko$chatAnimWrapRender(ChatHud instance, ChatHud.Backend queueMessage, int restrictedMessageWidth, int restrictedMessage, boolean focused, Operation<Void> original, @Local(argsOnly=true) DrawContext graphics) {
        float displacement = this.kimiko$chatAnimCalculateDisplacement();
        if (displacement != 0.0f) {
            graphics.getMatrices().pushMatrix();
            graphics.getMatrices().translate(0.0f, displacement);
        }
        original.call(new Object[]{instance, queueMessage, restrictedMessageWidth, restrictedMessage, focused});
        if (displacement != 0.0f) {
            graphics.getMatrices().popMatrix();
        }
    }

    @Inject(method={"addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V"}, at={@At(value="TAIL")})
    private void kimiko$chatAnimAddMessage(Text contents, MessageSignatureData signature, MessageIndicator tag, CallbackInfo ci) {
        if (BetterMinecraft.chatAnimationsEnabled()) {
            this.kimiko$chatAnimLastMessageTime = System.currentTimeMillis();
        }
    }
}

