/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.world.ClientWorld$Properties
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Visuals.Ambience;

@Mixin(value={ClientWorld.class})
public abstract class ClientLevelAmbienceMixin {
    @Shadow
    @Final
    private ClientWorld.Properties clientWorldProperties;

    @Inject(method={"tickTime"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$tickAmbienceTime(CallbackInfo ci) {
        Ambience ambience = Ambience.getInstance();
        if (ambience == null || !ambience.isEnabled()) {
            return;
        }
        ClientWorld level = (ClientWorld)(Object)this;
        this.clientWorldProperties.setTimeOfDay(ambience.getInternalTime());
        ambience.syncWeather(level, this.clientWorldProperties);
        ci.cancel();
    }
}

