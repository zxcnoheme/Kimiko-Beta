/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBufferSlice
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.Frustum
 *  net.minecraft.client.render.WorldRenderer
 *  net.minecraft.client.render.BuiltChunkStorage
 *  net.minecraft.client.render.chunk.ChunkBuilder$BuiltChunk
 *  net.minecraft.client.render.RenderTickCounter
 *  net.minecraft.client.util.memory.ObjectAllocator
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Vector4f
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.BuiltChunkStorage;
import net.minecraft.client.render.chunk.ChunkBuilder;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.memory.ObjectAllocator;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.drags.DragSystem;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.impl.Utils.Optimization;
import rtx.kimiko.api.modules.impl.Visuals.Ambience;
import rtx.kimiko.api.modules.impl.Visuals.ChinaHat;
import rtx.kimiko.api.modules.impl.Visuals.ExplosionWave;
import rtx.kimiko.api.modules.impl.Visuals.FogBlur;
import rtx.kimiko.api.modules.impl.Visuals.GlassVapor;
import rtx.kimiko.api.modules.impl.Visuals.HitBubbles;
import rtx.kimiko.api.modules.impl.Visuals.JumpCircle;
import rtx.kimiko.api.modules.impl.Visuals.KillEffect;
import rtx.kimiko.api.modules.impl.Visuals.PortalLive;
import rtx.kimiko.api.modules.impl.Visuals.portallive.PortalLiveCull;
import rtx.kimiko.api.modules.impl.Visuals.portallive.PortalLiveView;
import rtx.kimiko.api.ui.window.WorldGuiCloseAnimation;
import rtx.kimiko.utils.render.modules.optimization.OcclusionCuller;
import rtx.kimiko.utils.render.modules.post.customsky.CustomSkyRenderer;
import rtx.kimiko.utils.render.modules.post.fogblur.FogBlurRenderer;
import rtx.kimiko.utils.render.modules.post.glowesp.GlowEspHook;
import rtx.kimiko.utils.render.modules.post.guilayerblur.GuiLayerBlurRenderer;
import rtx.kimiko.utils.render.modules.targetesp.wave.WindWaveRenderer;
import rtx.kimiko.utils.render.render2d.blur.BlurFramebuffer;

@Mixin(value={WorldRenderer.class})
public abstract class LevelRendererMixin {
    @Shadow
    @Final
    private MinecraftClient client;
    @Shadow
    private BuiltChunkStorage chunks;
    @Shadow
    @Final
    private ObjectArrayList<ChunkBuilder.BuiltChunk> builtChunks;
    @Shadow
    @Final
    private ObjectArrayList<ChunkBuilder.BuiltChunk> nearbyChunks;
    @Unique
    private static final Matrix4f kimiko$skyViewProj = new Matrix4f();
    @Unique
    private static final Matrix4f kimiko$posMatrixCopy = new Matrix4f();
    @Unique
    private static final Matrix4f kimiko$projMatrixCopy = new Matrix4f();

    @Inject(method={"updateCamera"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$portalCull(Camera camera, Frustum frustum, boolean spectator, CallbackInfo ci) {
        if (PortalLiveView.rendering()) {
            PortalLiveView.recordActualCamera(camera.getCameraPos());
            PortalLiveCull.beginPortalCull(this.chunks, this.builtChunks, this.nearbyChunks, camera, frustum);
            ci.cancel();
        } else if (PortalLiveCull.hasStash()) {
            PortalLiveCull.restoreAfterPortalPass(this.builtChunks, this.nearbyChunks);
        }
    }

    @Inject(method={"updateChunks"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$skipCompileInPortalPass(Camera camera, CallbackInfo ci) {
        if (PortalLiveView.rendering()) {
            ci.cancel();
        }
    }

    @Redirect(method={"fillEntityRenderStates"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/WorldRenderer;isRenderingReady(Lnet/minecraft/util/math/BlockPos;)Z"), require=0)
    private boolean kimiko$keepFrustumEntitiesVisible(WorldRenderer instance, BlockPos pos) {
        return true;
    }

    @Inject(method={"fillEntityRenderStates"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$beginCullFrame(CallbackInfo ci) {
        OcclusionCuller.beginFrame();
    }

    @ModifyArg(method={"fillEntityRenderStates"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;setRenderDistanceMultiplier(D)V"), index=0, require=0)
    private double kimiko$scaleEntityDistance(double original) {
        return Optimization.scaleEntityViewScale(original);
    }

    @Inject(method={"renderClouds"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$skipClouds(CallbackInfo ci) {
        if (Optimization.cullClouds()) {
            ci.cancel();
        }
    }

    @Inject(method={"render"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$captureFogBlurFallback(ObjectAllocator allocator, RenderTickCounter deltaTracker, boolean renderBlockOutline, Camera camera, Matrix4f positionMatrix, Matrix4f projectionMatrix, Matrix4f frustumMatrix, GpuBufferSlice fog, Vector4f fogColor, boolean renderSky, CallbackInfo ci) {
        if (!PortalLiveView.rendering()) {
            PortalLiveView.recordFrameContext(allocator, fog, fogColor);
        }
        GlowEspHook.captureFrame(camera, positionMatrix, projectionMatrix, deltaTracker.getTickProgress(true));
        if (this.client == null || this.client.world == null || this.client.player == null || PortalLiveView.rendering()) {
            return;
        }
        this.applyAmbienceFog(fogColor);
        if (fogColor != null) {
            FogBlurRenderer.setFallbackColor(fogColor.x, fogColor.y, fogColor.z);
            BlurFramebuffer.setSkyFallbackColor(fogColor.x, fogColor.y, fogColor.z);
        }
        FogBlurRenderer.beginFrame();
        WindWaveRenderer.beginFrame();
        kimiko$skyViewProj.set((Matrix4fc)projectionMatrix).mul((Matrix4fc)positionMatrix);
        CustomSkyRenderer.prepareFrame(kimiko$skyViewProj);
    }

    @Inject(method={"renderTargetBlockOutline"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$applySkyBeforeOutline(CallbackInfo ci) {
        if (this.client != null && this.client.world != null) {
            CustomSkyRenderer.applyPending(this.client.getFramebuffer());
        }
    }

    @Inject(method={"pushEntityRenders"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$applySkyBeforeEntities(CallbackInfo ci) {
        if (this.client != null && this.client.world != null) {
            CustomSkyRenderer.applyPending(this.client.getFramebuffer());
        }
    }

    @Inject(method={"render"}, at={@At(value="RETURN")}, require=0)
    private void kimiko$worldRenderEvent(ObjectAllocator allocator, RenderTickCounter deltaTracker, boolean renderBlockOutline, Camera camera, Matrix4f positionMatrix, Matrix4f projectionMatrix, Matrix4f frustumMatrix, GpuBufferSlice fog, Vector4f fogColor, boolean renderSky, CallbackInfo ci) {
        if (PortalLiveView.rendering()) {
            if (PortalLiveCull.hasStash()) {
                PortalLiveCull.restoreAfterPortalPass(this.builtChunks, this.nearbyChunks);
            }
            if (this.client == null || this.client.world == null || this.client.player == null) {
                return;
            }
            MatrixStack portalStack = new MatrixStack();
            portalStack.multiplyPositionMatrix((Matrix4fc)positionMatrix);
            kimiko$posMatrixCopy.set((Matrix4fc)positionMatrix);
            kimiko$projMatrixCopy.set((Matrix4fc)projectionMatrix);
            WorldRenderEvent portalEvent = new WorldRenderEvent(portalStack, deltaTracker.getTickProgress(true), camera, kimiko$posMatrixCopy, kimiko$projMatrixCopy, true);
            GlowEspHook.renderBeforeParticles();
            this.applyFogBlur();
            EventBus.get().post(portalEvent);
            this.applyHitBubbles(camera, positionMatrix, projectionMatrix);
            this.applyGlassVapor(camera, positionMatrix, projectionMatrix, false);
            this.applyJumpCircleDistortion(camera, positionMatrix, projectionMatrix, frustumMatrix);
            this.applyExplosionWave(camera, positionMatrix, projectionMatrix);
            this.renderChinaHat(portalEvent);
            return;
        }
        if (this.client == null || this.client.world == null || this.client.player == null) {
            CustomSkyRenderer.finishFrame();
            return;
        }
        GlowEspHook.renderBeforeParticles();
        MatrixStack stack = new MatrixStack();
        stack.multiplyPositionMatrix((Matrix4fc)positionMatrix);
        WorldGuiCloseAnimation.captureWorldMatrices(projectionMatrix, positionMatrix, camera.getCameraPos());
        GuiLayerBlurRenderer.snapshotWorldDepth();
        CustomSkyRenderer.applyPending(this.client.getFramebuffer());
        CustomSkyRenderer.finishFrame();
        this.applyFogBlur();
        kimiko$posMatrixCopy.set((Matrix4fc)positionMatrix);
        kimiko$projMatrixCopy.set((Matrix4fc)projectionMatrix);
        WorldRenderEvent worldRenderEvent = new WorldRenderEvent(stack, deltaTracker.getTickProgress(true), camera, kimiko$posMatrixCopy, kimiko$projMatrixCopy);
        this.applyAmbienceWetWorld(camera, positionMatrix, projectionMatrix, deltaTracker.getTickProgress(true));
        this.applyAmbienceCustomFog(camera, positionMatrix, projectionMatrix);
        EventBus.get().post(worldRenderEvent);
        this.applyHitBubbles(camera, positionMatrix, projectionMatrix);
        this.applyGlassVapor(camera, positionMatrix, projectionMatrix);
        this.applyJumpCircleDistortion(camera, positionMatrix, projectionMatrix, frustumMatrix);
        this.applyExplosionWave(camera, positionMatrix, projectionMatrix);
        DragSystem.get().applyDragDistortion(this.client.getFramebuffer());
        this.renderAmbienceRainfall(worldRenderEvent);
        this.renderAmbienceFireflies(worldRenderEvent);
        this.renderChinaHat(worldRenderEvent);
        this.renderPortalLive(worldRenderEvent);
    }

    private void renderPortalLive(WorldRenderEvent event) {
        PortalLive portalLive = PortalLive.getInstance();
        if (portalLive != null && portalLive.isEnabled()) {
            portalLive.onWorldRender(event);
        }
    }

    private void applyFogBlur() {
        FogBlur fogBlur = FogBlur.getInstance();
        if (fogBlur != null && fogBlur.isVisuallyActive()) {
            fogBlur.onAfterTranslucent(this.client.getFramebuffer());
        }
    }

    private void applyHitBubbles(Camera camera, Matrix4f positionMatrix, Matrix4f projectionMatrix) {
        HitBubbles hitBubbles = HitBubbles.getInstance();
        if (hitBubbles != null && hitBubbles.isVisuallyActive()) {
            hitBubbles.onAfterWorld(this.client.getFramebuffer(), positionMatrix, projectionMatrix, camera);
        }
    }

    private void applyGlassVapor(Camera camera, Matrix4f positionMatrix, Matrix4f projectionMatrix) {
        this.applyGlassVapor(camera, positionMatrix, projectionMatrix, true);
    }

    private void applyGlassVapor(Camera camera, Matrix4f positionMatrix, Matrix4f projectionMatrix, boolean simulate) {
        GlassVapor glassVapor = GlassVapor.getInstance();
        if (glassVapor != null && glassVapor.isVisuallyActive()) {
            glassVapor.onAfterWorld(this.client.getFramebuffer(), positionMatrix, projectionMatrix, camera, simulate);
        }
    }

    private void applyExplosionWave(Camera camera, Matrix4f positionMatrix, Matrix4f projectionMatrix) {
        ExplosionWave explosionWave = ExplosionWave.getInstance();
        if (explosionWave != null && explosionWave.isVisuallyActive()) {
            explosionWave.onAfterWorld(this.client.getFramebuffer(), positionMatrix, projectionMatrix, camera);
        }
    }

    private void applyAmbienceWetWorld(Camera camera, Matrix4f positionMatrix, Matrix4f projectionMatrix, float partialTick) {
        Ambience ambience = Ambience.getInstance();
        if (ambience != null && ambience.isVisuallyActive()) {
            ambience.onAfterWorldWetWorld(this.client.getFramebuffer(), positionMatrix, projectionMatrix, camera, partialTick);
        }
    }

    private void applyAmbienceCustomFog(Camera camera, Matrix4f positionMatrix, Matrix4f projectionMatrix) {
        Ambience ambience = Ambience.getInstance();
        if (ambience != null && ambience.isVisuallyActive()) {
            ambience.onAfterWorldFog(this.client.getFramebuffer(), positionMatrix, projectionMatrix, camera);
        }
    }

    private void renderAmbienceRainfall(WorldRenderEvent event) {
        Ambience ambience = Ambience.getInstance();
        if (ambience != null && ambience.isEnabled()) {
            ambience.renderRainfall(event);
        }
    }

    private void renderAmbienceFireflies(WorldRenderEvent event) {
        Ambience ambience = Ambience.getInstance();
        if (ambience != null && ambience.isVisuallyActive()) {
            ambience.renderFireflies(event);
        }
    }

    private void applyJumpCircleDistortion(Camera camera, Matrix4f positionMatrix, Matrix4f projectionMatrix, Matrix4f frustumMatrix) {
        JumpCircle jumpCircle = JumpCircle.getInstance();
        if (jumpCircle != null && jumpCircle.isEnabled()) {
            jumpCircle.onAfterWorld(this.client.getFramebuffer(), positionMatrix, projectionMatrix, frustumMatrix, camera);
        }
    }

    private void renderChinaHat(WorldRenderEvent event) {
        ChinaHat chinaHat = ChinaHat.getInstance();
        if (chinaHat != null && chinaHat.isVisuallyActive()) {
            chinaHat.renderAfterPostEffects(event);
        }
    }

    private void applyAmbienceFog(Vector4f fogColor) {
        if (fogColor == null) {
            return;
        }
        Ambience ambience = Ambience.getInstance();
        boolean customFog = ambience != null && ambience.isCustomFogActive();
        float saturation = KillEffect.getWorldSaturationMultiplier();
        if (ambience != null && ambience.isEnabled()) {
            saturation *= ambience.getSaturationFactor();
        }
        if (!Float.isFinite(saturation) || Math.abs(saturation - 1.0f) <= 5.0E-4f || customFog) {
            if (customFog) {
                int customColor = ambience.fogColorRGB();
                fogColor.set((float)(customColor >> 16 & 0xFF) / 255.0f, (float)(customColor >> 8 & 0xFF) / 255.0f, (float)(customColor & 0xFF) / 255.0f, fogColor.w);
            }
            return;
        }
        float r = fogColor.x;
        float g = fogColor.y;
        float elementCodec = fogColor.z;
        float luminance = r * 0.2126f + g * 0.7152f + elementCodec * 0.0722f;
        fogColor.set(MathHelper.clamp((float)(luminance + (r - luminance) * saturation), (float)0.0f, (float)1.0f), MathHelper.clamp((float)(luminance + (g - luminance) * saturation), (float)0.0f, (float)1.0f), MathHelper.clamp((float)(luminance + (elementCodec - luminance) * saturation), (float)0.0f, (float)1.0f), fogColor.w);
    }
}

