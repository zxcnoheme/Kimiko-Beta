/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.hud.ChatHudLine
 *  org.jetbrains.annotations.NotNull
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 */
package mixin.chathads;

import mods.chathads.HeadData;
import mods.chathads.mixininterface.HeadRenderable;
import net.minecraft.client.gui.hud.ChatHudLine;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value={ChatHudLine.class})
public abstract class GuiMessageMixin
implements HeadRenderable {
    @Unique
    @NotNull
    public HeadData chatheads$headData = HeadData.EMPTY;

    @Override
    public void chatheads$setHeadData(@NotNull HeadData headData) {
        this.chatheads$headData = headData;
    }

    @Override
    @NotNull
    public HeadData chatheads$getHeadData() {
        return this.chatheads$headData;
    }
}

