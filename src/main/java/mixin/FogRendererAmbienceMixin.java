/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.render.Camera
 *  net.minecraft.block.enums.CameraSubmersionType
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.render.fog.FogRenderer
 *  net.minecraft.client.render.RenderTickCounter
 *  org.joml.Vector4f
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.ModifyArgs
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.invoke.arg.Args
 */
package mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.Camera;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.render.RenderTickCounter;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import rtx.kimiko.api.modules.impl.Visuals.Ambience;
import rtx.kimiko.api.modules.impl.Visuals.FogBlur;
import rtx.kimiko.api.modules.impl.Visuals.KillEffect;
import rtx.kimiko.api.modules.impl.Visuals.WastedDeath;

@Mixin(value={FogRenderer.class})
public abstract class FogRendererAmbienceMixin {
    @ModifyArg(method={"applyFog(Lnet/minecraft/client/render/Camera;ILnet/minecraft/client/render/RenderTickCounter;FLnet/minecraft/client/world/ClientWorld;)Lorg/joml/Vector4f;"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/fog/FogRenderer;applyFog(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V"), index=2, require=0)
    private Vector4f kimiko$modifyAmbienceFogColor(Vector4f color) {
        return FogRendererAmbienceMixin.applyAmbienceToFog(color);
    }

    @ModifyArgs(method={"applyFog(Lnet/minecraft/client/render/Camera;ILnet/minecraft/client/render/RenderTickCounter;FLnet/minecraft/client/world/ClientWorld;)Lorg/joml/Vector4f;"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/fog/FogRenderer;applyFog(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V"), require=0)
    private void kimiko$scaleFogDistances(Args args) {
        Ambience ambience;
        float value;
        int i;
        if (WastedDeath.isRunning()) {
            for (int i2 = 3; i2 <= 8; ++i2) {
                float value2 = ((Float)args.get(i2)).floatValue();
                args.set(i2, (Object)Float.valueOf(value2 * 512.0f));
            }
            return;
        }
        FogBlur fogBlur = FogBlur.getInstance();
        if (fogBlur != null && fogBlur.hasCustomFogDistance()) {
            float factor = fogBlur.getFogDistanceFactor();
            for (i = 3; i <= 8; ++i) {
                value = ((Float)args.get(i)).floatValue();
                args.set(i, (Object)Float.valueOf(value * factor));
            }
        }
        if ((ambience = Ambience.getInstance()) != null && MinecraftClient.getInstance().gameRenderer.getCamera().getSubmersionType() == CameraSubmersionType.NONE) {
            if (ambience.isCustomFogActive()) {
                for (i = 3; i <= 6; ++i) {
                    value = ((Float)args.get(i)).floatValue();
                    args.set(i, (Object)Float.valueOf(value * 512.0f));
                }
                float skyFactor = ambience.fogSkyEndFactor();
                args.set(7, (Object)Float.valueOf(((Float)args.get(7)).floatValue() * skyFactor));
                args.set(8, (Object)Float.valueOf(((Float)args.get(8)).floatValue() * skyFactor));
            } else if (ambience.isCustomSkyActive()) {
                for (i = 3; i <= 8; ++i) {
                    value = ((Float)args.get(i)).floatValue();
                    args.set(i, (Object)Float.valueOf(value * 512.0f));
                }
            }
        }
    }

    @Inject(method={"applyFog(Lnet/minecraft/client/render/Camera;ILnet/minecraft/client/render/RenderTickCounter;FLnet/minecraft/client/world/ClientWorld;)Lorg/joml/Vector4f;"}, at={@At(value="RETURN")}, cancellable=true, require=0)
    private void kimiko$setupAmbienceFog(Camera camera, int renderDistance, RenderTickCounter deltaTracker, float tickProgress, ClientWorld level, CallbackInfoReturnable<Vector4f> cir) {
        Vector4f modified = FogRendererAmbienceMixin.applyAmbienceToFog((Vector4f)cir.getReturnValue());
        if (modified != null && modified != cir.getReturnValue()) {
            cir.setReturnValue(modified);
        }
    }

    private static Vector4f applyAmbienceToFog(Vector4f fogColor) {
        float saturation;
        boolean fogBlurColor;
        if (fogColor == null) {
            return null;
        }
        Ambience ambience = Ambience.getInstance();
        FogBlur fogBlur = FogBlur.getInstance();
        boolean ambienceOn = ambience != null && ambience.isEnabled();
        boolean bl = fogBlurColor = fogBlur != null && fogBlur.hasCustomFogColor();
        if (!ambienceOn && !fogBlurColor) {
            return fogColor;
        }
        float r = fogColor.x;
        float g = fogColor.y;
        float elementCodec = fogColor.z;
        boolean changed = false;
        if (fogBlurColor) {
            int customColor = fogBlur.getCustomFogColor();
            r = (float)(customColor >> 16 & 0xFF) / 255.0f;
            g = (float)(customColor >> 8 & 0xFF) / 255.0f;
            elementCodec = (float)(customColor & 0xFF) / 255.0f;
            changed = true;
        }
        if (Float.isFinite(saturation = KillEffect.getWorldSaturationMultiplier() * (ambienceOn ? ambience.getSaturationFactor() : 1.0f)) && Math.abs(saturation - 1.0f) > 5.0E-4f) {
            float lum = r * 0.2126f + g * 0.7152f + elementCodec * 0.0722f;
            r = MathHelper.clamp((float)(lum + (r - lum) * saturation), (float)0.0f, (float)1.0f);
            g = MathHelper.clamp((float)(lum + (g - lum) * saturation), (float)0.0f, (float)1.0f);
            elementCodec = MathHelper.clamp((float)(lum + (elementCodec - lum) * saturation), (float)0.0f, (float)1.0f);
            changed = true;
        }
        if (ambienceOn && ambience.isCustomFogActive()) {
            int customColor = ambience.fogColorRGB();
            r = (float)(customColor >> 16 & 0xFF) / 255.0f;
            g = (float)(customColor >> 8 & 0xFF) / 255.0f;
            elementCodec = (float)(customColor & 0xFF) / 255.0f;
            changed = true;
        }
        return changed ? new Vector4f(r, g, elementCodec, fogColor.w) : fogColor;
    }
}

