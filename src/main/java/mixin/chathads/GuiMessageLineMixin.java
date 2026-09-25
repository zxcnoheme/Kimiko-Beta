/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.hud.ChatHudLine$Visible
 *  org.jetbrains.annotations.NotNull
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.chathads;

import mods.chathads.ChatHeads;
import mods.chathads.HeadData;
import mods.chathads.mixininterface.HeadRenderable;
import net.minecraft.client.gui.hud.ChatHudLine;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ChatHudLine.Visible.class})
public abstract class GuiMessageLineMixin
implements HeadRenderable {
    @Unique
    @NotNull
    public HeadData chatheads$headData = HeadData.EMPTY;

    @Inject(method={"<init>"}, at={@At(value="TAIL")})
    public void chatheads$setOwnerForFirstLine(CallbackInfo callbackInfo) {
        this.chatheads$headData = ChatHeads.getLineData();
        ChatHeads.setLineData(HeadData.EMPTY);
    }

    @Override
    @NotNull
    public HeadData chatheads$getHeadData() {
        return this.chatheads$headData;
    }
}

