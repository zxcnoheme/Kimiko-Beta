/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyReturnValue
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 */
package mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import rtx.kimiko.api.modules.impl.Visuals.NoRender;

@Mixin(value={AbstractClientPlayerEntity.class})
public abstract class AbstractClientPlayerFovMixin {
    @ModifyReturnValue(method={"getFovMultiplier"}, at={@At(value="RETURN")}, require=1)
    private float kimiko$noFovDynamic(float original) {
        return NoRender.isActive("Динамика поля зрения") ? 1.0f : original;
    }
}

