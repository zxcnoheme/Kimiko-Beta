/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.entity.state.LivingEntityRenderState
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.client.render.entity.feature.FeatureRenderer
 *  net.minecraft.client.render.entity.model.EntityModel
 *  net.minecraft.client.render.entity.LivingEntityRenderer
 *  net.minecraft.client.render.entity.feature.CapeFeatureRenderer
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package mixin.waveycapes;

import mods.waveycapes.support.ModSupport;
import mods.waveycapes.support.SupportManager;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={LivingEntityRenderer.class})
public class LivingEntityRendererMixin<S extends LivingEntity, T extends LivingEntityRenderState, M extends EntityModel<? super T>> {
    @Inject(method={"addFeature"}, at={@At(value="HEAD")}, cancellable=true)
    private void addLayer(FeatureRenderer<T, M> renderLayer, CallbackInfoReturnable<Boolean> info) {
        if (renderLayer instanceof CapeFeatureRenderer) {
            info.cancel();
            return;
        }
        for (ModSupport support : SupportManager.getSupportedMods()) {
            if (!support.blockFeatureRenderer(renderLayer)) continue;
            info.cancel();
            return;
        }
    }
}

