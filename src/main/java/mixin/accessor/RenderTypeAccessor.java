/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.RenderSetup
 *  net.minecraft.client.render.RenderLayer
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package mixin.accessor;

import net.minecraft.client.render.RenderSetup;
import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={RenderLayer.class})
public interface RenderTypeAccessor {
    @Accessor(value="renderSetup")
    public RenderSetup kimiko$getState();
}

