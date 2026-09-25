/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.DrawContext
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 */
package mixin.shulkerview;

import mods.shulkerview.hook.ShulkerPreviewGuiGraphics;
import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value={DrawContext.class})
public abstract class GuiGraphicsExtractorMixin
implements ShulkerPreviewGuiGraphics {
    @Unique
    private int kimiko$shulkerPreviewMouseX = Integer.MIN_VALUE;
    @Unique
    private int kimiko$shulkerPreviewMouseY = Integer.MIN_VALUE;

    @Override
    public int kimiko$getMouseX() {
        return this.kimiko$shulkerPreviewMouseX;
    }

    @Override
    public int kimiko$getMouseY() {
        return this.kimiko$shulkerPreviewMouseY;
    }

    @Override
    public void kimiko$setMouse(int mouseX, int mouseY) {
        this.kimiko$shulkerPreviewMouseX = mouseX;
        this.kimiko$shulkerPreviewMouseY = mouseY;
    }
}

