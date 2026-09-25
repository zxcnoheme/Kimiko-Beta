/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.entity.state.PlayerEntityRenderState
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.entity.Entity
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.render.entity.feature.FeatureRendererContext
 *  net.minecraft.client.render.entity.feature.FeatureRenderer
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.entity.model.PlayerEntityModel
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 */
package mods.waveycapes.renderlayers;

import mods.waveycapes.WaveyCapesBase;
import mods.waveycapes.WaveyCapesMod;
import mods.waveycapes.compat.PlayerWrapper;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.entity.Entity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import rtx.kimiko.api.modules.impl.Visuals.Customization;

public class CustomCapeRenderLayer
extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {
    public CustomCapeRenderLayer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(MatrixStack poseStack, OrderedRenderCommandQueue submitNodeCollector, int packedLight, PlayerEntityRenderState renderState, float f, float g) {
        Entity entity;
        PlayerWrapper capeRenderInfo = new PlayerWrapper(renderState);
        float delta = MinecraftClient.getInstance().getRenderTickCounter().getTickProgress(false);
        if (capeRenderInfo.isPlayerInvisible()) {
            return;
        }
        if (capeRenderInfo.hasElytraEquipped()) {
            return;
        }
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world != null && (entity = mc.world.getEntityById(renderState.id)) instanceof AbstractClientPlayerEntity) {
            AbstractClientPlayerEntity player = (AbstractClientPlayerEntity)entity;
            Customization customization = Customization.getInstance();
            if (customization != null && customization.wingsEnabledFor(player)) {
                return;
            }
        }
        if (!capeRenderInfo.isCapeVisible()) {
            return;
        }
        poseStack.push();
        ((PlayerEntityModel)this.getContextModel()).getRootPart().applyTransform(poseStack);
        ((PlayerEntityModel)this.getContextModel()).body.applyTransform(poseStack);
        if (capeRenderInfo.hasChestplateEquipped()) {
            poseStack.translate(0.0f, -0.053125f, 0.06875f);
        }
        if (WaveyCapesBase.INSTANCE == null) {
            WaveyCapesMod.INSTANCE.init();
        }
        if (WaveyCapesBase.INSTANCE != null) {
            WaveyCapesBase.INSTANCE.getRenderer().render(capeRenderInfo, poseStack, submitNodeCollector, packedLight, delta);
        }
        poseStack.pop();
    }
}

