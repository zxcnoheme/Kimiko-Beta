/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelTransform
 *  net.minecraft.client.model.Dilation
 *  net.minecraft.client.model.ModelPartBuilder
 *  net.minecraft.client.model.ModelData
 *  net.minecraft.client.model.ModelPartData
 *  net.minecraft.client.model.ModelPart
 */
package mods.waveycapes;

import java.util.function.IntUnaryOperator;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelPart;

public class NMSUtil {
    public static ModelPart[] buildCape(int texWidth, int texHight, IntUnaryOperator uvX, IntUnaryOperator uvY) {
        ModelPart[] customCape = new ModelPart[16];
        ModelData meshDefinition = new ModelData();
        ModelPartData partDefinition = meshDefinition.getRoot();
        for (int i = 0; i < 16; ++i) {
            partDefinition.addChild("customCape_" + i, ModelPartBuilder.create().uv(uvX.applyAsInt(i), uvY.applyAsInt(i)).cuboid(-5.0f, (float)i, -1.0f, 10.0f, 1.0f, 1.0f, Dilation.NONE, 1.0f, 0.5f), ModelTransform.origin((float)0.0f, (float)0.0f, (float)0.0f));
        }
        ModelPart modelPart = partDefinition.createPart(texWidth, texHight);
        for (int i = 0; i < 16; ++i) {
            customCape[i] = modelPart.getChild("customCape_" + i);
        }
        return customCape;
    }
}

