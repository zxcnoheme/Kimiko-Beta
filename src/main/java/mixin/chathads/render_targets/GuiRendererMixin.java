/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.render.GuiRenderer
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.chathads.render_targets;

import mods.chathads.ChatHeads;
import net.minecraft.client.gui.render.GuiRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={GuiRenderer.class})
public abstract class GuiRendererMixin {
    @Inject(method={"prepare"}, at={@At(value="HEAD")})
    public void chatheads$isInsideGui(CallbackInfo ci) {
        ChatHeads.customHeadRendering = true;
    }

    @Inject(method={"prepare"}, at={@At(value="RETURN")})
    public void chatheads$isOutsideGui(CallbackInfo ci) {
        ChatHeads.customHeadRendering = false;
    }
}

