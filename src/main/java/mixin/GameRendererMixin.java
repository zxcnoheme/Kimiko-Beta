/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyReturnValue
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  com.mojang.blaze3d.buffers.GpuBufferSlice
 *  net.minecraft.client.render.ProjectionMatrix3
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.GameRenderer
 *  net.minecraft.client.render.RenderTickCounter
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import net.minecraft.client.render.ProjectionMatrix3;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Utils.CameraSettings;
import rtx.kimiko.api.modules.impl.Visuals.Ambience;
import rtx.kimiko.api.modules.impl.Visuals.AspectRatio;
import rtx.kimiko.api.modules.impl.Visuals.KillEffect;
import rtx.kimiko.api.modules.impl.Visuals.NoRender;
import rtx.kimiko.api.modules.impl.Visuals.WastedDeath;
import rtx.kimiko.api.modules.impl.Visuals.portallive.PortalLiveCapture;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.api.ui.theme.ThemeWave;
import rtx.kimiko.utils.render.modules.post.guilayerblur.GuiCapture;
import rtx.kimiko.utils.render.modules.post.guilayerblur.GuiLayerBlurRenderer;
import rtx.kimiko.utils.render.modules.post.guimotionblur.GuiMotionBlurRenderer;
import rtx.kimiko.utils.render.modules.post.handsflame.HandsFlameRenderer;
import rtx.kimiko.utils.render.modules.post.handsflame.HandsItemHitboxTracker;
import rtx.kimiko.utils.render.modules.post.handsflame.IrisShaderCompat;
import rtx.kimiko.utils.render.modules.post.hologram.HologramHandsRenderer;
import rtx.kimiko.utils.render.modules.post.hpfocus.HPFocusRenderer;
import rtx.kimiko.utils.render.modules.post.itemoutline.ItemOutlineRenderer;
import rtx.kimiko.utils.render.modules.post.saturation.Saturation2D;
import rtx.kimiko.utils.render.modules.post.shaderhands.ShaderHandsRenderer;
import rtx.kimiko.utils.render.modules.post.wasted.WastedRenderer;
import rtx.kimiko.utils.render.others.RenderCompatibility;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.render.render2d.ThemeWaveUniform;
import rtx.kimiko.utils.render.util.underhand.UnderHand2D;

@Mixin(value={GameRenderer.class})
public abstract class GameRendererMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method={"render"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$primeRenderCompatibility(RenderTickCounter deltaTracker, boolean tick, CallbackInfo ci) {
        ThemeWave.beginFrame();
        ClientAccent.beginFrame();
        ClientPalette.update();
        ThemeWaveUniform.update();
        RenderCompatibility.primeFromCurrentContext();
    }

    @Inject(method={"render"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/GameRenderer;renderWorld(Lnet/minecraft/client/render/RenderTickCounter;)V", shift=At.Shift.AFTER)}, require=0)
    private void kimiko$renderPortalView(RenderTickCounter deltaTracker, boolean tick, CallbackInfo ci) {
        PortalLiveCapture.renderPortalPass((GameRenderer)(Object)this, deltaTracker);
    }

    @Inject(method={"render"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/render/GuiRenderer;render(Lcom/mojang/blaze3d/buffers/GpuBufferSlice;)V", shift=At.Shift.BEFORE)}, require=0)
    private void kimiko$preGuiRender(RenderTickCounter deltaTracker, boolean tick, CallbackInfo ci) {
        if (this.client == null || this.client.player == null || this.client.world == null) {
            return;
        }
        this.kimiko$applyWorldSaturation();
        this.kimiko$applyWastedEffect();
        if (UI.motionBlurCapturePending()) {
            GuiMotionBlurRenderer.captureBackground(UI.motionBlurCaptureRadius());
        }
    }

    @Inject(method={"render"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/render/GuiRenderer;render(Lcom/mojang/blaze3d/buffers/GpuBufferSlice;)V", shift=At.Shift.AFTER)}, require=0)
    private void kimiko$postGuiRender(RenderTickCounter deltaTracker, boolean tick, CallbackInfo ci) {
        if (GuiLayerBlurRenderer.captureActiveThisFrame()) {
            UI.dropPendingBlurs();
            if (GuiCapture.active()) {
                GuiLayerBlurRenderer.composite(GuiCapture.scale(), GuiCapture.blurRadius());
            }
        } else {
            UI.flushMotionBlur();
        }
        HPFocusRenderer.captureIfRequested();
    }

    @Inject(method={"renderHand"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$captureHandsSceneBefore(float partialTicks, boolean renderItem, Matrix4f projectionMatrix, CallbackInfo ci) {
        boolean irisActive = IrisShaderCompat.isShaderPackInUse();
        GuiLayerBlurRenderer.compositeRemotePanels();
        UnderHand2D.renderNow();
        ShaderHandsRenderer.beginHandFrame();
        HandsItemHitboxTracker.captureProjection((Matrix4fc)projectionMatrix);
        if (irisActive) {
            return;
        }
        HandsFlameRenderer.captureBeforeHandRender();
        HologramHandsRenderer.captureBeforeHandRender();
    }

    @Inject(method={"renderHand"}, at={@At(value="RETURN")}, require=0)
    private void kimiko$captureHandsSceneAfter(float partialTicks, boolean renderItem, Matrix4f projectionMatrix, CallbackInfo ci) {
        GuiLayerBlurRenderer.snapshotHandDepth();
        if (IrisShaderCompat.isShaderPackInUse()) {
            return;
        }
        HandsFlameRenderer.captureAfterHandRender();
        HandsFlameRenderer.renderCapturedHandsFlame();
        HologramHandsRenderer.captureAfterHandRender();
        HologramHandsRenderer.renderCapturedHologram();
    }

    @Inject(method={"close"}, at={@At(value="RETURN")})
    private void kimiko$closeMotionBlur(CallbackInfo ci) {
        GuiMotionBlurRenderer.shutdown();
        GuiLayerBlurRenderer.shutdown();
        ItemOutlineRenderer.clear();
    }

    @ModifyReturnValue(method={"getFov"}, at={@At(value="RETURN")}, require=0)
    private float kimiko$killZoomFov(float original) {
        float scale = KillEffect.getKillZoomFovScale();
        return scale == 1.0f ? original : original * scale;
    }

    @ModifyReturnValue(method={"getFov"}, at={@At(value="RETURN")}, require=0)
    private float kimiko$cameraZoomFov(float original) {
        float scale = CameraSettings.getFovScale();
        return scale == 1.0f ? original : original * scale;
    }

    @ModifyReturnValue(method={"getBasicProjectionMatrix"}, at={@At(value="RETURN")}, require=0)
    private Matrix4f kimiko$aspectRatioProjection(Matrix4f original) {
        AspectRatio aspectRatio = AspectRatio.getInstance();
        if (aspectRatio == null || !aspectRatio.isEnabled() || original == null) {
            return original;
        }
        return AspectRatio.copyAdjusted((Matrix4fc)original);
    }

    @WrapOperation(method={"renderWorld"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/ProjectionMatrix3;set(IIF)Lcom/mojang/blaze3d/buffers/GpuBufferSlice;")}, require=0)
    private GpuBufferSlice kimiko$handAspectRatio(ProjectionMatrix3 buffer, int width, int height, float fov, Operation<GpuBufferSlice> original) {
        int adjustedWidth = Math.max(1, Math.round(AspectRatio.resolveRatio(width, height)));
        return (GpuBufferSlice)original.call(new Object[]{buffer, adjustedWidth, height, Float.valueOf(fov)});
    }

    @ModifyReturnValue(method={"getBasicProjectionMatrix"}, at={@At(value="RETURN")}, require=0)
    private Matrix4f kimiko$killCameraShake(Matrix4f original) {
        if (original == null) {
            return original;
        }
        float shake = KillEffect.getKillShakeDegrees();
        if (shake == 0.0f) {
            return original;
        }
        return new Matrix4f((Matrix4fc)original).rotateZ((float)Math.toRadians(shake));
    }

    @Inject(method={"tiltViewWhenHurt"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$noRenderCameraShake(MatrixStack stack, float tickDelta, CallbackInfo ci) {
        if (NoRender.isActive("Тряска камеры")) {
            ci.cancel();
        }
    }

    @Inject(method={"bobView"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$noRenderViewBobbing(MatrixStack stack, float tickDelta, CallbackInfo ci) {
        if (NoRender.isActive("Покачивание камеры")) {
            ci.cancel();
        }
    }

    private void kimiko$applyWastedEffect() {
        WastedDeath module = WastedDeath.getInstance();
        if (module == null || !WastedDeath.isRunning()) {
            return;
        }
        WastedRenderer.apply(1.0f, 0.97f, 0.94f, 0.35f);
    }

    private void kimiko$applyWorldSaturation() {
        float saturation = KillEffect.getWorldSaturationMultiplier();
        Ambience ambience = Ambience.getInstance();
        if (ambience != null && ambience.isEnabled()) {
            saturation *= ambience.getSaturationFactor();
        }
        if (!Float.isFinite(saturation) || Math.abs(saturation - 1.0f) <= 5.0E-4f) {
            return;
        }
        Saturation2D.applyWithCopy(Math.clamp(saturation, 0.0f, 2.0f));
    }
}

