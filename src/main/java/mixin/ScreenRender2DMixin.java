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
package mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.utils.render.render2d.Render2D;

@Mixin(value={Screen.class})
public abstract class ScreenRender2DMixin {
    @Inject(method={"render"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$r2dBegin(DrawContext graphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        Render2D.beginFrame(graphics);
    }

    @Inject(method={"render"}, at={@At(value="RETURN")}, require=0)
    private void kimiko$r2dFlush(DrawContext graphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        Render2D.flush();
    }
}

