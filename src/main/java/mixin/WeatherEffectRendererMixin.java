/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.particle.ParticleEffect
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.render.WeatherRendering
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.render.WeatherRendering;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Visuals.Ambience;
import rtx.kimiko.api.modules.impl.Visuals.NoRender;

@Mixin(value={WeatherRendering.class})
public class WeatherEffectRendererMixin {
    @Inject(method={"renderPrecipitation"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$hideVanillaRain(CallbackInfo ci) {
        if (NoRender.isActive("Частицы")) {
            ci.cancel();
            return;
        }
        Ambience ambience = Ambience.getInstance();
        if (ambience != null && ambience.shouldHideVanillaWeather()) {
            ci.cancel();
        }
    }

    @Inject(method={"buildPrecipitationPieces"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$noWeatherExtract(CallbackInfo ci) {
        if (NoRender.isActive("Частицы")) {
            ci.cancel();
        }
    }

    @Redirect(method={"addParticlesAndSound"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/world/ClientWorld;addParticleClient(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"), require=0)
    private void kimiko$hideVanillaSplash(ClientWorld level, ParticleEffect options, double x, double y, double z, double dx, double dy, double dz) {
        if (NoRender.isActive("Частицы")) {
            return;
        }
        Ambience ambience = Ambience.getInstance();
        if (ambience != null && ambience.shouldHideVanillaWeather()) {
            return;
        }
        level.addParticleClient(options, x, y, z, dx, dy, dz);
    }
}

