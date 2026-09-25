/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  net.minecraft.client.render.model.BlockModelPart
 *  net.minecraft.world.BlockRenderView
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.block.BlockState
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.block.BlockRenderManager
 *  net.minecraft.client.render.chunk.SectionBuilder
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 */
package mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import java.util.List;
import net.minecraft.client.render.model.BlockModelPart;
import net.minecraft.world.BlockRenderView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.chunk.SectionBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import rtx.kimiko.utils.render.modules.targetesp.wave.WindWaveTagger;

@Mixin(value={SectionBuilder.class})
public class SectionCompilerMixin {
    @WrapOperation(method={"build"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/block/BlockRenderManager;renderBlock(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;ZLjava/util/List;)V")}, require=0)
    private void kimiko$tagWavingVertices(BlockRenderManager dispatcher, BlockState state, BlockPos pos, BlockRenderView level, MatrixStack poseStack, VertexConsumer consumer, boolean checkSides, List<BlockModelPart> parts, Operation<Void> original) {
        VertexConsumer target = consumer;
        try {
            target = WindWaveTagger.wrap(consumer, state, pos, level);
        }
        catch (Throwable ignored) {
            target = consumer;
        }
        original.call(new Object[]{dispatcher, state, pos, level, poseStack, target, checkSides, parts});
    }
}

