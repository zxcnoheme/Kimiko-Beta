/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.RenderLayers
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.model.ModelPart
 */
package mods.waveycapes;

import mods.waveycapes.CapeRenderer;
import mods.waveycapes.compat.PlayerWrapper;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.model.ModelPart;
import rtx.kimiko.utils.render.others.cape.CapeVertexColor;

public class VanillaCapeRenderer
implements CapeRenderer {
    @Override
    public void render(PlayerWrapper capeRenderInfo, int part, ModelPart model, MatrixStack poseStack, VertexConsumer vertexConsumer, int light, int overlay) {
        float y = ((float)part + 0.5f) / 16.0f;
        int color = capeRenderInfo.isLocalPlayer() ? CapeVertexColor.at(y, y, 1.0f) : -1;
        model.render(poseStack, vertexConsumer, light, overlay, color);
    }

    @Override
    public RenderLayer getRenderType(PlayerWrapper capeRenderInfo) {
        Identifier cape = capeRenderInfo.getCapeTexture();
        if (cape != null) {
            return RenderLayers.entityTranslucent((Identifier)cape);
        }
        return null;
    }

    @Override
    public boolean vanillaUvValues() {
        return true;
    }
}

