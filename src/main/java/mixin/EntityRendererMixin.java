/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.entity.state.EntityRenderState
 *  net.minecraft.client.render.entity.state.ItemEntityRenderState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ItemEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.client.render.Frustum
 *  net.minecraft.client.render.entity.EntityRenderer
 *  net.minecraft.client.render.entity.ItemEntityRenderer
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package mixin;

import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.ItemEntityRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rtx.kimiko.api.modules.impl.Utils.Optimization;
import rtx.kimiko.api.modules.impl.Visuals.ItemPhysics;
import rtx.kimiko.api.modules.impl.Visuals.ModelCollapse;
import rtx.kimiko.api.modules.impl.Visuals.NameTags;
import rtx.kimiko.api.modules.impl.Visuals.NoRender;

@Mixin(value={EntityRenderer.class})
public abstract class EntityRendererMixin {
    @Inject(method={"shouldRender"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$skipHiddenEntities(Entity entity, Frustum frustum, double camX, double camY, double camZ, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living;
        if (entity instanceof LivingEntity && ModelCollapse.shouldHideEntity(living = (LivingEntity)entity)) {
            cir.setReturnValue(false);
            return;
        }
        if (!(entity instanceof PlayerEntity) && NameTags.hidesNameTagFor(entity)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method={"updateRenderState"}, at={@At(value="TAIL")}, require=0)
    private void kimiko$noEntityEffects(Entity entity, EntityRenderState state, float partialTick, CallbackInfo ci) {
        if (NoRender.isActive("Огонь на сущностях")) {
            state.onFire = false;
        }
        if (NoRender.isActive("Свечение")) {
            state.outlineColor = 0;
        }
    }

    @Inject(method={"updateShadow(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/render/entity/state/EntityRenderState;)V"}, at={@At(value="TAIL")}, require=0)
    private void kimiko$scaleItemShadow(Entity entity, EntityRenderState state, CallbackInfo ci) {
        if (!((Object)this instanceof ItemEntityRenderer)) {
            return;
        }
        ItemPhysics physics = ItemPhysics.getInstance();
        if (physics == null || !physics.isEnabled() || !physics.isNormalMode()) {
            return;
        }
        if (!(entity instanceof ItemEntity) || !(state instanceof ItemEntityRenderState)) {
            return;
        }
        float scale = physics.groundItemScale();
        if (Math.abs(scale - 1.0f) > 0.001f) {
            state.shadowRadius *= scale;
        }
    }

    @Inject(method={"updateShadow(Lnet/minecraft/client/render/entity/state/EntityRenderState;Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/world/World;)V"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$skipShadow(CallbackInfo ci) {
        if (Optimization.hideEntityShadows()) {
            ci.cancel();
        }
    }
}

