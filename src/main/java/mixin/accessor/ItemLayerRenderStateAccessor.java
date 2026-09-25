/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.item.ItemRenderState$Glint
 *  net.minecraft.client.render.item.ItemRenderState$LayerRenderState
 *  net.minecraft.client.render.item.model.special.SpecialModelRenderer
 *  net.minecraft.client.render.model.json.Transformation
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package mixin.accessor;

import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.render.model.json.Transformation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={ItemRenderState.LayerRenderState.class})
public interface ItemLayerRenderStateAccessor {
    @Accessor(value="glint")
    public ItemRenderState.Glint kimiko$getFoilType();

    @Accessor(value="transform")
    public Transformation kimiko$getItemTransform();

    @Accessor(value="useLight")
    public boolean kimiko$getUsesBlockLight();

    @Accessor(value="specialModelType")
    public SpecialModelRenderer<Object> kimiko$getSpecialRenderer();

    @Accessor(value="tints")
    public int[] kimiko$getTintLayers();
}

