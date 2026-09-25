/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.hud.ChatHudLine$Visible
 *  net.minecraft.client.gui.hud.MessageIndicator
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package mixin.chatanim;

import mods.chatanim.config.ModConfig;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.gui.hud.MessageIndicator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rtx.kimiko.api.modules.impl.Visuals.BetterMinecraft;

@Mixin(value={ChatHudLine.Visible.class})
public abstract class GuiMessageMixin {
    @Inject(method={"indicator"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$chatAnimRemoveIndicator(CallbackInfoReturnable<MessageIndicator> cir) {
        if (BetterMinecraft.chatAnimationsEnabled() && ModConfig.getConfig().removeMessageIndicator) {
            cir.setReturnValue(null);
        }
    }
}

