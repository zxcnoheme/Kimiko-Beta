/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.render.GuiRenderer
 *  net.minecraft.client.gui.render.SpecialGuiElementRenderer
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.client.gui.render.state.special.SpecialGuiElementRenderState
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.client.render.command.RenderDispatcher
 *  net.minecraft.client.render.VertexConsumerProvider$Immediate
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.emotions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.gui.render.SpecialGuiElementRenderer;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gui.render.state.special.SpecialGuiElementRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.command.RenderDispatcher;
import net.minecraft.client.render.VertexConsumerProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionPreviewRenderer;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionPreviewState;
import rtx.kimiko.utils.render.others.hud.HotbarItemPipRenderer;
import rtx.kimiko.utils.render.others.hud.HotbarItemPipState;

@Mixin(value={GuiRenderer.class})
public abstract class GuiRendererEmotionPipMixin {
    @Shadow
    @Final
    @Mutable
    private Map<Class<? extends SpecialGuiElementRenderState>, SpecialGuiElementRenderer<?>> specialElementRenderers;

    @Inject(method={"<init>"}, at={@At(value="RETURN")}, require=0)
    private void kimiko$registerEmotionPreview(GuiRenderState renderState, VertexConsumerProvider.Immediate bufferSource, OrderedRenderCommandQueue collector, RenderDispatcher features, List<SpecialGuiElementRenderer<?>> renderers, CallbackInfo ci) {
        HashMap extended = new HashMap(this.specialElementRenderers);
        extended.put(EmotionPreviewState.class, new EmotionPreviewRenderer(bufferSource));
        extended.put(HotbarItemPipState.class, new HotbarItemPipRenderer(bufferSource));
        this.specialElementRenderers = extended;
    }
}

