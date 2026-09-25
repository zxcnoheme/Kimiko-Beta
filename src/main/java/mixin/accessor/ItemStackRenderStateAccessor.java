/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.item.ItemRenderState
 *  net.minecraft.client.render.item.ItemRenderState$LayerRenderState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package mixin.accessor;

import net.minecraft.client.render.item.ItemRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={ItemRenderState.class})
public interface ItemStackRenderStateAccessor {
    @Accessor(value="layerCount")
    public int kimiko$getActiveLayerCount();

    @Accessor(value="layers")
    public ItemRenderState.LayerRenderState[] kimiko$getLayers();
}

