/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.screen.ingame.BookScreen
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.chathads.render_targets;

import mods.chathads.ChatHeads;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={BookScreen.class})
public abstract class BookViewScreenMixin {
    @Inject(method={"render"}, at={@At(value="HEAD")})
    public void chatheads$isInsideBook(CallbackInfo ci) {
        ChatHeads.customHeadRendering = true;
    }

    @Inject(method={"render"}, at={@At(value="RETURN")})
    public void chatheads$isOutsideBook(CallbackInfo ci) {
        ChatHeads.customHeadRendering = false;
    }
}

