/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.entity.state.EntityRenderState
 *  net.minecraft.client.render.entity.equipment.EquipmentModelLoader
 *  net.minecraft.client.item.ItemModelManager
 *  net.minecraft.client.texture.TextureManager
 *  net.minecraft.client.texture.AtlasManager
 *  net.minecraft.client.texture.PlayerSkinCache
 *  net.minecraft.entity.Entity
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.option.GameOptions
 *  net.minecraft.client.font.TextRenderer
 *  net.minecraft.client.render.MapRenderer
 *  net.minecraft.resource.ResourceManager
 *  net.minecraft.client.render.entity.model.LoadedEntityModels
 *  net.minecraft.client.render.block.BlockRenderManager
 *  net.minecraft.client.render.entity.EntityRenderer
 *  net.minecraft.client.render.entity.EntityRenderManager
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package mixin;

import java.util.function.Supplier;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.equipment.EquipmentModelLoader;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.texture.AtlasManager;
import net.minecraft.client.texture.PlayerSkinCache;
import net.minecraft.entity.Entity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.MapRenderer;
import net.minecraft.resource.ResourceManager;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRenderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rtx.kimiko.api.modules.impl.Visuals.custompet.render.CustomPetRendererBridge;

@Mixin(value={EntityRenderManager.class})
public abstract class CustomPetEntityRenderDispatcherMixin {
    @Inject(method={"<init>"}, at={@At(value="TAIL")})
    private void wirst$bootstrapCustomPetRenderer(MinecraftClient minecraft, TextureManager textureManager, ItemModelManager itemModelResolver, MapRenderer mapRenderer, BlockRenderManager blockRenderDispatcher, AtlasManager atlasManager, TextRenderer font, GameOptions options, Supplier<LoadedEntityModels> modelSetSupplier, EquipmentModelLoader equipmentAssetManager, PlayerSkinCache playerSkinRenderCache, CallbackInfo ci) {
        CustomPetRendererBridge.bootstrap((EntityRenderManager)(Object)this, minecraft, blockRenderDispatcher, itemModelResolver, mapRenderer, atlasManager, font, modelSetSupplier, equipmentAssetManager, playerSkinRenderCache);
    }

    @Inject(method={"reload"}, at={@At(value="TAIL")})
    private void wirst$reloadCustomPetRenderer(ResourceManager resourceManager, CallbackInfo ci) {
        CustomPetRendererBridge.reload();
    }

    @Inject(method="getRenderer(Lnet/minecraft/entity/Entity;)Lnet/minecraft/client/render/entity/EntityRenderer;", at=@At(value="HEAD"), cancellable=true)
    private <T extends Entity> void wirst$useCustomPetRenderer(T entity, CallbackInfoReturnable<EntityRenderer<? super T, ?>> cir) {
        EntityRenderer<? super T, ?> renderer = CustomPetRendererBridge.getCustomRenderer(entity);
        if (renderer != null) {
            cir.setReturnValue(renderer);
        }
    }

    @Inject(method="getRenderer(Lnet/minecraft/client/render/entity/state/EntityRenderState;)Lnet/minecraft/client/render/entity/EntityRenderer;", at=@At(value="HEAD"), cancellable=true)
    private <S extends EntityRenderState> void wirst$useCustomPetStateRenderer(S renderState, CallbackInfoReturnable<EntityRenderer<?, ? super S>> cir) {
        EntityRenderer<?, ? super S> renderer = CustomPetRendererBridge.getCustomRenderer(renderState);
        if (renderer != null) {
            cir.setReturnValue(renderer);
        }
    }
}

