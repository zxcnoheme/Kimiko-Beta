/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.OverlayTexture
 *  net.minecraft.client.model.ModelPart
 */
package mods.waveycapes;

import mods.waveycapes.compat.PlayerWrapper;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.model.ModelPart;

public interface CapeRenderer {
    default public void render(PlayerWrapper capeRenderInfo, int part, ModelPart model, MatrixStack poseStack, VertexConsumer vertexConsumer, int light, int overlay) {
        model.render(poseStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
    }

    public RenderLayer getRenderType(PlayerWrapper var1);

    default public boolean vanillaUvValues() {
        return true;
    }
}

