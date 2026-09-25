/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.entity.state.PlayerEntityRenderState
 *  net.minecraft.client.render.entity.PlayerEntityRenderer
 *  net.minecraft.client.render.entity.feature.FeatureRendererContext
 *  net.minecraft.client.render.entity.EntityRendererFactory$Context
 *  net.minecraft.client.render.entity.model.EntityModel
 *  net.minecraft.client.render.entity.model.PlayerEntityModel
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  net.minecraft.client.render.entity.LivingEntityRenderer
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.waveycapes;

import mods.waveycapes.renderlayers.CustomCapeRenderLayer;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={PlayerEntityRenderer.class})
public abstract class PlayerRendererMixin
extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityRenderState, PlayerEntityModel> {
    @Unique
    private boolean injectedCape = false;

    public PlayerRendererMixin(EntityRendererFactory.Context context, PlayerEntityModel entityModel, float f) {
        super(context, entityModel, f);
    }

    @Inject(method={"<init>*"}, at={@At(value="RETURN")})
    public void onCreate(CallbackInfo info) {
        this.injectedCape = true;
        this.addFeature(new CustomCapeRenderLayer((FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel>)(Object)this));
    }
}

