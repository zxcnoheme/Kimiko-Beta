/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.buffers.GpuBufferSlice
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.systems.RenderPass
 *  com.mojang.blaze3d.vertex.VertexFormat$IndexType
 *  net.minecraft.client.gui.render.GuiRenderer
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.GameRenderer
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.util.List;
import java.util.function.Supplier;
import mixin.accessor.GuiRendererDrawAccessor;
import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Utils.guishare.RemoteGuiWorld;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.utils.render.core.frame.EngineFrame;
import rtx.kimiko.utils.render.modules.post.guilayerblur.GuiCapture;
import rtx.kimiko.utils.render.modules.post.guilayerblur.GuiLayerBlurRenderer;
import rtx.kimiko.utils.render.modules.post.guimotionblur.GuiMotionBlurRenderer;
import rtx.kimiko.utils.render.modules.post.handshader.HandShaderPreview;
import rtx.kimiko.utils.render.modules.post.hudlayer.HudLayerRenderer;
import rtx.kimiko.utils.render.modules.post.themeshock.ThemeShockwaveRenderer;
import rtx.kimiko.utils.render.modules.post.usersky.UserSkyPreview;
import rtx.kimiko.utils.render.others.profiler.RenderProfiler;
import rtx.kimiko.utils.render.render2d.ClientSplits;
import rtx.kimiko.utils.render.render2d.blur.BlurFramebuffer;
import rtx.kimiko.utils.render.render2d.glow.GlowRenderer;
import rtx.kimiko.utils.render.util.renderitem.RenderItem;

@Mixin(value={GuiRenderer.class})
public abstract class GuiRendererMixin {
    @Shadow
    @Final
    private List<?> draws;
    private RenderPass kimiko$currentRenderPass;
    private boolean kimiko$blurDrawActive;
    private boolean kimiko$glowDrawActive;
    private RenderPipeline kimiko$enginePipeline;
    private boolean kimiko$itemDrawActive;
    @Unique
    private RenderProfiler.Scope kimiko$prepareScope;
    @Unique
    private RenderProfiler.Scope kimiko$drawScope;

    @Inject(method={"render(Lcom/mojang/blaze3d/buffers/GpuBufferSlice;)V"}, at={@At(value="TAIL")})
    private void kimiko$renderThemeShockwave(CallbackInfo ci) {
        ThemeShockwaveRenderer.onFrameEnd();
    }

    @Inject(method={"prepare"}, at={@At(value="HEAD")})
    private void kimiko$profilePrepareBegin(CallbackInfo ci) {
        this.kimiko$prepareScope = RenderProfiler.begin("ui.vanilla.prepare");
    }

    @Inject(method={"prepare"}, at={@At(value="RETURN")})
    private void kimiko$profilePrepareEnd(CallbackInfo ci) {
        RenderProfiler.end(this.kimiko$prepareScope);
        this.kimiko$prepareScope = null;
    }

    @Inject(method={"renderPreparedDraws"}, at={@At(value="HEAD")})
    private void kimiko$profileDrawBegin(CallbackInfo ci) {
        this.kimiko$drawScope = RenderProfiler.begin("ui.vanilla.draw");
    }

    @Inject(method={"renderPreparedDraws"}, at={@At(value="RETURN")})
    private void kimiko$profileDrawEnd(CallbackInfo ci) {
        RenderProfiler.end(this.kimiko$drawScope);
        this.kimiko$drawScope = null;
    }

    @Inject(method={"render(Lcom/mojang/blaze3d/buffers/GpuBufferSlice;)V"}, at={@At(value="HEAD")})
    private void kimiko$beginBlurFrame(CallbackInfo ci) {
        RenderProfiler.beginFrame();
        BlurFramebuffer.getInstance().beginGuiFrame();
        GlowRenderer.getInstance().beginGuiFrame();
        EngineFrame.beginGuiFrame();
        RenderItem.beginGuiFrame();
    }

    @Inject(method={"prepare"}, at={@At(value="HEAD")})
    private void kimiko$preparePendingBlurResources(CallbackInfo ci) {
        ClientSplits.update();
        BlurFramebuffer.getInstance().preparePending();
        GlowRenderer.getInstance().preparePending();
        UserSkyPreview.renderPending();
        HandShaderPreview.renderPending();
    }

    @Inject(method={"prepare"}, at={@At(value="RETURN")})
    private void kimiko$prepareRenderUniforms(CallbackInfo ci) {
        BlurFramebuffer.getInstance().prepareBuffers();
        GlowRenderer.getInstance().prepareBuffers();
        EngineFrame.prepareBuffers();
        RenderItem.prepareBuffers();
    }

    @Inject(method={"renderPreparedDraws"}, at={@At(value="HEAD")})
    private void kimiko$prepareBlurCapture(CallbackInfo ci) {
        BlurFramebuffer.getInstance().prepareGuiDraw();
        GuiLayerBlurRenderer.beginCapture(GuiCapture.active(), RemoteGuiWorld.captureRequested());
    }

    @WrapOperation(method={"renderPreparedDraws"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/render/GuiRenderer;render(Ljava/util/function/Supplier;Lnet/minecraft/client/gl/Framebuffer;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;Lcom/mojang/blaze3d/buffers/GpuBuffer;Lcom/mojang/blaze3d/vertex/VertexFormat$IndexType;II)V")}, require=2)
    private void kimiko$routePanelRange(GuiRenderer instance, Supplier<String> label, Framebuffer target, GpuBufferSlice fog, GpuBufferSlice transforms, GpuBuffer indices, VertexFormat.IndexType indexType, int from, int to, Operation<Void> original) {
        int cursor = from;
        Framebuffer remoteTarget = GuiLayerBlurRenderer.remoteCaptureTarget();
        if (remoteTarget != null) {
            while (cursor < to) {
                if (GuiLayerBlurRenderer.isRemoteRouting()) {
                    int end = this.kimiko$findRemoteMark(cursor, to, false);
                    if (end < cursor) {
                        this.kimiko$drawCaptured(instance, label, remoteTarget, fog, transforms, indices, indexType, cursor, to, original);
                        return;
                    }
                    if (cursor < end) {
                        this.kimiko$drawCaptured(instance, label, remoteTarget, fog, transforms, indices, indexType, cursor, end, original);
                    }
                    GuiLayerBlurRenderer.setRemoteRouting(false);
                    cursor = end + 1;
                    continue;
                }
                int begin = this.kimiko$findRemoteMark(cursor, to, true);
                if (begin < cursor) break;
                if (cursor < begin) {
                    this.kimiko$routeLocal(instance, label, target, fog, transforms, indices, indexType, cursor, begin, original);
                }
                GuiLayerBlurRenderer.setRemoteRouting(true);
                cursor = begin + 1;
            }
            if (cursor >= to) {
                return;
            }
        }
        this.kimiko$routeLocal(instance, label, target, fog, transforms, indices, indexType, cursor, to, original);
    }

    @Unique
    private void kimiko$routeLocal(GuiRenderer instance, Supplier<String> label, Framebuffer target, GpuBufferSlice fog, GpuBufferSlice transforms, GpuBuffer indices, VertexFormat.IndexType indexType, int from, int to, Operation<Void> original) {
        if (from >= to) {
            return;
        }
        Framebuffer captureTarget = GuiLayerBlurRenderer.captureTarget();
        if (captureTarget == null) {
            int popupBoundary;
            int n = popupBoundary = UI.popupLayerCapturePending() ? this.kimiko$findBoundary(from, to, true) : -1;
            if (popupBoundary < from) {
                this.kimiko$emit(instance, label, target, fog, transforms, indices, indexType, from, to, original);
                return;
            }
            if (from < popupBoundary) {
                this.kimiko$emit(instance, label, target, fog, transforms, indices, indexType, from, popupBoundary, original);
            }
            if (UI.consumePopupLayerCapture()) {
                GuiMotionBlurRenderer.captureBackground(0.0f);
            }
            if (popupBoundary + 1 < to) {
                this.kimiko$emit(instance, label, target, fog, transforms, indices, indexType, popupBoundary + 1, to, original);
            }
            return;
        }
        int boundary = this.kimiko$findBoundary(from, to, false);
        if (boundary < from) {
            if (GuiCapture.emitPanelBoundary()) {
                this.kimiko$emit(instance, label, target, fog, transforms, indices, indexType, from, to, original);
            } else {
                this.kimiko$drawCaptured(instance, label, captureTarget, fog, transforms, indices, indexType, from, to, original);
            }
            return;
        }
        if (from < boundary) {
            this.kimiko$drawCaptured(instance, label, captureTarget, fog, transforms, indices, indexType, from, boundary, original);
        }
        if (boundary + 1 < to) {
            this.kimiko$emit(instance, label, target, fog, transforms, indices, indexType, boundary + 1, to, original);
        }
    }

    @Unique
    private void kimiko$emit(GuiRenderer instance, Supplier<String> label, Framebuffer target, GpuBufferSlice fog, GpuBufferSlice transforms, GpuBuffer indices, VertexFormat.IndexType indexType, int from, int to, Operation<Void> original) {
        int end;
        int begin;
        int cursor = from;
        while (cursor < to && (begin = this.kimiko$findHudMark(cursor, to, true)) >= cursor && (end = this.kimiko$findHudMark(begin + 1, to, false)) >= 0) {
            boolean layered;
            Framebuffer layerTarget;
            if (cursor < begin) {
                original.call(new Object[]{instance, label, target, fog, transforms, indices, indexType, cursor, begin});
            }
            Object object = layerTarget = (layered = HudLayerRenderer.beginDraw()) ? HudLayerRenderer.layerTarget() : null;
            if (layerTarget != null) {
                if (begin + 1 < end) {
                    original.call(new Object[]{instance, label, layerTarget, fog, transforms, indices, indexType, begin + 1, end});
                }
                HudLayerRenderer.endDraw(target);
            } else if (begin + 1 < end) {
                original.call(new Object[]{instance, label, target, fog, transforms, indices, indexType, begin + 1, end});
            }
            cursor = end + 1;
        }
        if (cursor < to) {
            original.call(new Object[]{instance, label, target, fog, transforms, indices, indexType, cursor, to});
        }
    }

    @Unique
    private int kimiko$findHudMark(int from, int to, boolean begin) {
        int end = Math.min(to, this.draws.size());
        for (int i = Math.max(0, from); i < end; ++i) {
            Object draw = this.draws.get(i);
            if (!(draw instanceof GuiRendererDrawAccessor)) continue;
            GuiRendererDrawAccessor accessor = (GuiRendererDrawAccessor)draw;
            RenderPipeline pipeline = accessor.kimiko$getPipeline();
            if (!(begin ? HudLayerRenderer.isBegin(pipeline) : HudLayerRenderer.isEnd(pipeline))) continue;
            return i;
        }
        return -1;
    }

    @Unique
    private int kimiko$findRemoteMark(int from, int to, boolean begin) {
        int end = Math.min(to, this.draws.size());
        for (int i = Math.max(0, from); i < end; ++i) {
            Object draw = this.draws.get(i);
            if (!(draw instanceof GuiRendererDrawAccessor)) continue;
            GuiRendererDrawAccessor accessor = (GuiRendererDrawAccessor)draw;
            RenderPipeline pipeline = accessor.kimiko$getPipeline();
            if (!(begin ? GuiLayerBlurRenderer.isRemoteBegin(pipeline) : GuiLayerBlurRenderer.isRemoteEnd(pipeline))) continue;
            return i;
        }
        return -1;
    }

    @Unique
    private void kimiko$drawCaptured(GuiRenderer instance, Supplier<String> label, Framebuffer captureTarget, GpuBufferSlice fog, GpuBufferSlice transforms, GpuBuffer indices, VertexFormat.IndexType indexType, int from, int to, Operation<Void> original) {
        int cardEnd;
        int cardBegin;
        int popupBoundary;
        int boundary;
        boolean remote;
        int cursor = from;
        boolean bl = remote = captureTarget == GuiLayerBlurRenderer.remoteCaptureTarget();
        while (cursor < to && (boundary = GuiRendererMixin.kimiko$firstBoundary(popupBoundary = this.kimiko$findBoundary(cursor, to, true), cardBegin = remote ? this.kimiko$findRemoteCardMark(cursor, to, true) : -1, cardEnd = remote ? this.kimiko$findRemoteCardMark(cursor, to, false) : -1)) >= cursor) {
            if (cursor < boundary) {
                this.kimiko$emit(instance, label, captureTarget, fog, transforms, indices, indexType, cursor, boundary, original);
            }
            if (boundary == cardBegin) {
                RemoteGuiWorld.beginCardBlurDraw(captureTarget);
            } else if (boundary == cardEnd) {
                RemoteGuiWorld.endCardBlurDraw(captureTarget);
            } else {
                BlurFramebuffer.getInstance().recaptureWorldBackdrop();
            }
            cursor = boundary + 1;
        }
        if (cursor < to) {
            this.kimiko$emit(instance, label, captureTarget, fog, transforms, indices, indexType, cursor, to, original);
        }
    }

    @Unique
    private int kimiko$findRemoteCardMark(int from, int to, boolean begin) {
        int end = Math.min(to, this.draws.size());
        for (int i = Math.max(0, from); i < end; ++i) {
            Object draw = this.draws.get(i);
            if (!(draw instanceof GuiRendererDrawAccessor)) continue;
            GuiRendererDrawAccessor accessor = (GuiRendererDrawAccessor)draw;
            RenderPipeline pipeline = accessor.kimiko$getPipeline();
            if (!(begin ? GuiLayerBlurRenderer.isRemoteCardBegin(pipeline) : GuiLayerBlurRenderer.isRemoteCardEnd(pipeline))) continue;
            return i;
        }
        return -1;
    }

    @Unique
    private static int kimiko$firstBoundary(int first, int second, int third) {
        int result = -1;
        if (first >= 0) {
            result = first;
        }
        if (second >= 0 && (result < 0 || second < result)) {
            result = second;
        }
        if (third >= 0 && (result < 0 || third < result)) {
            result = third;
        }
        return result;
    }

    @Unique
    private int kimiko$findBoundary(int from, int to, boolean popup) {
        int end = Math.min(to, this.draws.size());
        for (int i = Math.max(0, from); i < end; ++i) {
            Object draw = this.draws.get(i);
            if (!(draw instanceof GuiRendererDrawAccessor)) continue;
            GuiRendererDrawAccessor accessor = (GuiRendererDrawAccessor)draw;
            RenderPipeline pipeline = accessor.kimiko$getPipeline();
            if (!(popup ? GuiLayerBlurRenderer.isPopupBoundary(pipeline) : GuiLayerBlurRenderer.isPanelBoundary(pipeline))) continue;
            return i;
        }
        return -1;
    }

    @Redirect(method={"renderPreparedDraws"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/GameRenderer;renderBlur()V"))
    private void kimiko$cardLayerMidCapture(GameRenderer gameRenderer) {
        if (GuiCapture.active()) {
            GuiLayerBlurRenderer.markPanelRange();
            UI.consumePanelSplitMark();
            UI.consumeCardStratumMark();
            UI.consumePopupStratumMark();
            UI.consumeVanillaBlurRequest();
            return;
        }
        if (UI.consumePanelSplitMark()) {
            UI.applyMainCompositeAtSplit();
            if (UI.consumeVanillaBlurRequest() && !UI.isOpen()) {
                gameRenderer.renderBlur();
            }
            return;
        }
        if (UI.consumePopupStratumMark()) {
            BlurFramebuffer.getInstance().recaptureBackdrop();
            if (UI.consumePopupBlurCapture()) {
                GuiMotionBlurRenderer.captureBackground(0.0f);
            }
            return;
        }
        if (UI.consumeCardStratumMark()) {
            GuiMotionBlurRenderer.captureBackground(0.0f);
            return;
        }
        if (UI.isOpen()) {
            return;
        }
        gameRenderer.renderBlur();
    }

    @Redirect(method={"render(Lnet/minecraft/client/gui/render/GuiRenderer$Draw;Lcom/mojang/blaze3d/systems/RenderPass;Lcom/mojang/blaze3d/buffers/GpuBuffer;Lcom/mojang/blaze3d/vertex/VertexFormat$IndexType;)V"}, at=@At(value="INVOKE", target="Lcom/mojang/blaze3d/systems/RenderPass;setPipeline(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)V"))
    private void kimiko$trackPipeline(RenderPass renderPass, RenderPipeline pipeline) {
        this.kimiko$currentRenderPass = renderPass;
        this.kimiko$blurDrawActive = BlurFramebuffer.getInstance().isBlurPipeline(pipeline);
        this.kimiko$glowDrawActive = GlowRenderer.getInstance().isGlowPipeline(pipeline);
        this.kimiko$enginePipeline = EngineFrame.owns(pipeline) ? pipeline : null;
        this.kimiko$itemDrawActive = RenderItem.isItemPipeline(pipeline);
        renderPass.setPipeline(pipeline);
    }

    @Inject(method={"render(Lnet/minecraft/client/gui/render/GuiRenderer$Draw;Lcom/mojang/blaze3d/systems/RenderPass;Lcom/mojang/blaze3d/buffers/GpuBuffer;Lcom/mojang/blaze3d/vertex/VertexFormat$IndexType;)V"}, at={@At(value="INVOKE", target="Lcom/mojang/blaze3d/systems/RenderPass;drawIndexed(IIII)V", shift=At.Shift.BEFORE)})
    private void kimiko$bindBlurParams(CallbackInfo ci) {
        if (this.kimiko$blurDrawActive && this.kimiko$currentRenderPass != null) {
            BlurFramebuffer.getInstance().bindBlurParams(this.kimiko$currentRenderPass);
        }
        if (this.kimiko$glowDrawActive && this.kimiko$currentRenderPass != null) {
            GlowRenderer.getInstance().bindParams(this.kimiko$currentRenderPass);
        }
        if (this.kimiko$enginePipeline != null && this.kimiko$currentRenderPass != null) {
            EngineFrame.bindForPipeline(this.kimiko$currentRenderPass, this.kimiko$enginePipeline);
        }
        if (this.kimiko$itemDrawActive && this.kimiko$currentRenderPass != null) {
            RenderItem.bindParams(this.kimiko$currentRenderPass);
        }
    }

    @Inject(method={"render(Lnet/minecraft/client/gui/render/GuiRenderer$Draw;Lcom/mojang/blaze3d/systems/RenderPass;Lcom/mojang/blaze3d/buffers/GpuBuffer;Lcom/mojang/blaze3d/vertex/VertexFormat$IndexType;)V"}, at={@At(value="RETURN")})
    private void kimiko$clearTrackedPipeline(CallbackInfo ci) {
        this.kimiko$currentRenderPass = null;
        this.kimiko$blurDrawActive = false;
        this.kimiko$glowDrawActive = false;
        this.kimiko$enginePipeline = null;
        this.kimiko$itemDrawActive = false;
    }
}

