/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.screen.Screen
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.shulkerview;

import mods.shulkerview.hook.ShulkerPreviewGuiGraphics;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Screen.class})
public abstract class ScreenMixin {
    @Inject(method={"renderWithTooltip"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/screen/Screen;render(Lnet/minecraft/client/gui/DrawContext;IIF)V")})
    private void kimiko$shulkerPreviewCaptureMouse(DrawContext graphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        ((ShulkerPreviewGuiGraphics)graphics).kimiko$setMouse(mouseX, mouseY);
    }
}

