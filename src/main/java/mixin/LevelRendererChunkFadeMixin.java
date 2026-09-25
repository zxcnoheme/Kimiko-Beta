/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  net.minecraft.client.gl.DynamicUniforms$ChunkSectionsValue
 *  net.minecraft.client.render.WorldRenderer
 *  org.joml.Matrix4fc
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 */
package mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gl.DynamicUniforms;
import net.minecraft.client.render.WorldRenderer;
import org.joml.Matrix4fc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={WorldRenderer.class}, priority=1500)
public abstract class LevelRendererChunkFadeMixin {
    @WrapOperation(method={"renderBlockLayers"}, at={@At(value="NEW", target="(Lorg/joml/Matrix4fc;IIIFII)Lnet/minecraft/client/gl/DynamicUniforms$ChunkSectionsValue;")}, require=0)
    private DynamicUniforms.ChunkSectionsValue kimiko$fullSectionVisibility(Matrix4fc matrix, int x, int y, int z, float visibility, int keyCodec, int elementCodec, Operation<DynamicUniforms.ChunkSectionsValue> original) {
        return (DynamicUniforms.ChunkSectionsValue)original.call(new Object[]{matrix, x, y, z, Float.valueOf(1.0f), keyCodec, elementCodec});
    }
}

