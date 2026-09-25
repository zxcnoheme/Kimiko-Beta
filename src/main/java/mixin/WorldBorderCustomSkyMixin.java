/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Builder
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Snippet
 *  com.mojang.blaze3d.pipeline.RenderPipeline$UniformDescription
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.render.WorldBorderRendering
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldBorderRendering;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.modules.impl.Visuals.Ambience;
import rtx.kimiko.utils.render.modules.post.customsky.CustomSkyRenderer;

@Mixin(value={WorldBorderRendering.class})
public abstract class WorldBorderCustomSkyMixin {
    @Unique
    private static RenderPipeline kimiko$noDepthPipeline;
    @Unique
    private static boolean kimiko$pipelineFailed;

    @Inject(method={"render"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$applySkyBeforeBorder(CallbackInfo ci) {
        Ambience ambience = Ambience.getInstance();
        if (ambience != null && ambience.isCustomSkyActive()) {
            CustomSkyRenderer.applyPending(MinecraftClient.getInstance().getFramebuffer());
        }
    }

    @Redirect(method={"render"}, at=@At(value="FIELD", target="Lnet/minecraft/client/gl/RenderPipelines;RENDERTYPE_WORLD_BORDER:Lcom/mojang/blaze3d/pipeline/RenderPipeline;", opcode=178), require=0)
    private RenderPipeline kimiko$borderPipeline() {
        Ambience ambience = Ambience.getInstance();
        if (ambience == null || !ambience.isCustomSkyActive()) {
            return RenderPipelines.RENDERTYPE_WORLD_BORDER;
        }
        RenderPipeline pipeline = WorldBorderCustomSkyMixin.kimiko$noDepthWrite();
        return pipeline != null ? pipeline : RenderPipelines.RENDERTYPE_WORLD_BORDER;
    }

    @Unique
    private static RenderPipeline kimiko$noDepthWrite() {
        if (kimiko$pipelineFailed) {
            return null;
        }
        if (kimiko$noDepthPipeline != null) {
            return kimiko$noDepthPipeline;
        }
        try {
            RenderPipeline vanilla = RenderPipelines.RENDERTYPE_WORLD_BORDER;
            RenderPipeline.Builder builder = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(Identifier.of((String)Kimiko.namespace(), (String)"pipeline/world_border_no_depth")).withVertexShader(Identifier.of((String)Kimiko.namespace(), (String)"core/world_border_fade")).withFragmentShader(Identifier.of((String)Kimiko.namespace(), (String)"core/world_border_fade")).withVertexFormat(vanilla.getVertexFormat(), vanilla.getVertexFormatMode()).withCull(vanilla.isCull()).withDepthTestFunction(vanilla.getDepthTestFunction()).withDepthBias(vanilla.getDepthBiasScaleFactor(), vanilla.getDepthBiasConstant()).withDepthWrite(false);
            vanilla.getBlendFunction().ifPresent(arg_0 -> ((RenderPipeline.Builder)builder).withBlend(arg_0));
            for (String sampler : vanilla.getSamplers()) {
                builder.withSampler(sampler);
            }
            for (RenderPipeline.UniformDescription uniform : vanilla.getUniforms()) {
                if (uniform.textureFormat() != null) {
                    builder.withUniform(uniform.name(), uniform.type(), uniform.textureFormat());
                    continue;
                }
                builder.withUniform(uniform.name(), uniform.type());
            }
            kimiko$noDepthPipeline = RenderPipelines.register((RenderPipeline)builder.build());
        }
        catch (Throwable throwable) {
            kimiko$pipelineFailed = true;
        }
        return kimiko$noDepthPipeline;
    }
}

