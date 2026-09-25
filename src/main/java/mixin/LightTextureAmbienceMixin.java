/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.LightmapTextureManager
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package mixin;

import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import rtx.kimiko.api.modules.impl.Visuals.Ambience;

@Mixin(value={LightmapTextureManager.class})
public abstract class LightTextureAmbienceMixin {
    @Redirect(method={"update"}, at=@At(value="INVOKE", target="Ljava/lang/Double;floatValue()F", ordinal=1), require=0)
    private float kimiko$ambienceBrightness(Double value) {
        float baseValue = value.floatValue();
        Ambience ambience = Ambience.getInstance();
        if (ambience != null && ambience.isEnabled()) {
            float brightness = ambience.getBrightnessValue();
            if (brightness >= 0.0f) {
                return Math.max(baseValue, brightness * 10.0f);
            }
            return Math.max(baseValue * (1.0f + brightness), 0.08f);
        }
        return baseValue;
    }
}

