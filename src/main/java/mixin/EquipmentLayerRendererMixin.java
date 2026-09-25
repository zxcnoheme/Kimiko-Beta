/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  net.minecraft.client.render.entity.state.LivingEntityRenderState
 *  net.minecraft.client.render.entity.equipment.EquipmentRenderer
 *  net.minecraft.client.texture.Sprite
 *  net.minecraft.client.render.command.ModelCommandRenderer$CrumblingOverlayCommand
 *  net.minecraft.client.render.command.RenderCommandQueue
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.client.model.Model
 *  net.minecraft.client.util.math.MatrixStack
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 */
package mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.equipment.EquipmentRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.RenderCommandQueue;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.model.Model;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import rtx.kimiko.api.modules.impl.Visuals.HitColor;

@Mixin(value={EquipmentRenderer.class})
public abstract class EquipmentLayerRendererMixin {
    @WrapOperation(method={"render(Lnet/minecraft/client/render/entity/equipment/EquipmentModel$LayerType;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;ILnet/minecraft/util/Identifier;II)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/command/RenderCommandQueue;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/RenderLayer;IIILnet/minecraft/client/texture/Sprite;ILnet/minecraft/client/render/command/ModelCommandRenderer$CrumblingOverlayCommand;)V")}, require=0)
    private void kimiko$tintHitArmor(RenderCommandQueue collector, Model model, Object state, MatrixStack poseStack, RenderLayer renderType, int light, int overlay, int color, Sprite sprite, int outlineColor, ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlay, Operation<Void> original) {
        Integer n;
        if (state instanceof LivingEntityRenderState) {
            LivingEntityRenderState livingState = (LivingEntityRenderState)state;
            n = HitColor.tintFor(livingState);
        } else {
            n = null;
        }
        Integer hitColor = n;
        original.call(new Object[]{collector, model, state, poseStack, renderType, light, overlay, hitColor != null ? hitColor : color, sprite, outlineColor, crumblingOverlay});
    }
}

