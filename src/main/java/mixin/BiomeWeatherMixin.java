/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.Biome
 *  net.minecraft.world.biome.Biome$Precipitation
 *  net.minecraft.util.math.BlockPos
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package mixin;

import net.minecraft.world.biome.Biome;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rtx.kimiko.api.modules.impl.Visuals.Ambience;

@Mixin(value={Biome.class})
public abstract class BiomeWeatherMixin {
    @Inject(method={"hasPrecipitation"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$hasAmbiencePrecipitation(CallbackInfoReturnable<Boolean> cir) {
        Ambience ambience = Ambience.getInstance();
        Biome.Precipitation precipitation = ambience != null ? ambience.getForcedPrecipitation() : null;
        if (precipitation != null) {
            cir.setReturnValue(precipitation != Biome.Precipitation.NONE);
        }
    }

    @Inject(method={"getPrecipitation"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$getAmbiencePrecipitationAt(BlockPos pos, int seaLevel, CallbackInfoReturnable<Biome.Precipitation> cir) {
        Ambience ambience = Ambience.getInstance();
        Biome.Precipitation precipitation = ambience != null ? ambience.getForcedPrecipitation() : null;
        if (precipitation != null) {
            cir.setReturnValue(precipitation);
        }
    }

    @Inject(method={"isCold"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$coldEnoughForAmbienceSnow(BlockPos pos, int seaLevel, CallbackInfoReturnable<Boolean> cir) {
        Ambience ambience = Ambience.getInstance();
        Biome.Precipitation precipitation = ambience != null ? ambience.getForcedPrecipitation() : null;
        if (precipitation == Biome.Precipitation.SNOW) {
            cir.setReturnValue(true);
        } else if (precipitation == Biome.Precipitation.RAIN || precipitation == Biome.Precipitation.NONE) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method={"doesNotSnow"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$warmEnoughForAmbienceRain(BlockPos pos, int seaLevel, CallbackInfoReturnable<Boolean> cir) {
        Ambience ambience = Ambience.getInstance();
        Biome.Precipitation precipitation = ambience != null ? ambience.getForcedPrecipitation() : null;
        if (precipitation == Biome.Precipitation.SNOW) {
            cir.setReturnValue(false);
        } else if (precipitation == Biome.Precipitation.RAIN) {
            cir.setReturnValue(true);
        }
    }
}

