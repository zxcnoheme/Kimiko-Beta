/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.client.gui.DrawContext
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package mixin.accessor;

import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={DrawContext.class})
public interface GuiGraphicsExtractorAccessor {
    @Accessor(value="state")
    public GuiRenderState kimiko$getGuiRenderState();
}

