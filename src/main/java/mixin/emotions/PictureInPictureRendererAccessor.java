/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  net.minecraft.client.gui.render.SpecialGuiElementRenderer
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package mixin.emotions;

import com.mojang.blaze3d.textures.GpuTextureView;
import net.minecraft.client.gui.render.SpecialGuiElementRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={SpecialGuiElementRenderer.class})
public interface PictureInPictureRendererAccessor {
    @Accessor(value="textureView")
    public GpuTextureView kimiko$textureView();
}

