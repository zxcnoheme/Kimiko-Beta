/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.systems.RenderPass
 *  net.minecraft.client.render.BlockRenderLayerGroup
 *  net.minecraft.client.render.SectionRenderState
 *  net.minecraft.client.gl.GpuSampler
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.MinecraftClient
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderPass;
import net.minecraft.client.render.BlockRenderLayerGroup;
import net.minecraft.client.render.SectionRenderState;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Visuals.FogBlur;
import rtx.kimiko.utils.render.modules.post.customsky.CustomSkyRenderer;
import rtx.kimiko.utils.render.modules.post.fogblur.FogBlurRenderer;
import rtx.kimiko.utils.render.modules.targetesp.wave.WindWaveRenderer;

@Mixin(value={SectionRenderState.class})
public class ChunkSectionsToRenderMixin {
    @Inject(method={"renderSection"}, at={@At(value="RETURN")}, require=0)
    private void kimiko$applySkyAfterOpaque(BlockRenderLayerGroup group, GpuSampler sampler, CallbackInfo ci) {
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

    @Inject(method={"renderSection"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$captureOpaqueDepth(BlockRenderLayerGroup group, GpuSampler sampler, CallbackInfo ci) {
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

    @Redirect(method={"renderSection"}, at=@At(value="INVOKE", target="Lcom/mojang/blaze3d/systems/RenderPass;setPipeline(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)V"), require=0)
    private void kimiko$swapWavePipeline(RenderPass pass, RenderPipeline pipeline) {
        try {
            RenderPipeline target = WindWaveRenderer.substitute(pipeline);
            if (target != pipeline) {
                pass.setPipeline(target);
                WindWaveRenderer.bindParams(pass);
                return;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        pass.setPipeline(pipeline);
    }
}

