/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.caffeinemc.mods.sodium.client.render.chunk.ChunkRenderMatrices
 *  net.minecraft.client.render.BlockRenderLayerGroup
 *  net.minecraft.client.gl.GpuSampler
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.MinecraftClient
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Pseudo
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.caffeinemc.mods.sodium.client.render.chunk.ChunkRenderMatrices;
import net.minecraft.client.render.BlockRenderLayerGroup;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Visuals.FogBlur;
import rtx.kimiko.utils.render.modules.post.customsky.CustomSkyRenderer;
import rtx.kimiko.utils.render.modules.post.fogblur.FogBlurRenderer;

@Pseudo
@Mixin(targets={"net/caffeinemc/mods/sodium/client/render/SodiumWorldRenderer"}, remap=false)
public abstract class SodiumTerrainHooksMixin {
    @Inject(method={"drawChunkLayer"}, at={@At(value="HEAD")}, require=0, remap=false)
    private void kimiko$captureOpaqueDepth(BlockRenderLayerGroup group, ChunkRenderMatrices matrices, double x, double y, double z, GpuSampler sampler, CallbackInfo ci) {
        try {
            if (group != BlockRenderLayerGroup.TRANSLUCENT) {
                return;
            }
            Framebuffer main = MinecraftClient.getInstance().getFramebuffer();
            FogBlur fogBlur = FogBlur.getInstance();
            if (fogBlur != null && fogBlur.isEnabled() && !FogBlurRenderer.isDisabledAfterError()) {
                FogBlurRenderer.captureOpaqueDepth(main);
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @Inject(method={"drawChunkLayer"}, at={@At(value="RETURN")}, require=0, remap=false)
    private void kimiko$applySkyAfterOpaque(BlockRenderLayerGroup group, ChunkRenderMatrices matrices, double x, double y, double z, GpuSampler sampler, CallbackInfo ci) {
        try {
            if (group != BlockRenderLayerGroup.OPAQUE) {
                return;
            }
            CustomSkyRenderer.applyPending(MinecraftClient.getInstance().getFramebuffer());
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }
}

