/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.entity.state.LivingEntityRenderState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 */
package mixin;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import rtx.kimiko.api.modules.impl.Visuals.seeinvisible.RevealTintHolder;

@Mixin(value={LivingEntityRenderState.class})
public abstract class LivingEntityRenderStateRevealMixin
implements RevealTintHolder {
    @Unique
    private int kimiko$revealTint = -1;

    @Override
    public int kimiko$getRevealTint() {
        return this.kimiko$revealTint;
    }

    @Override
    public void kimiko$setRevealTint(int tint) {
        this.kimiko$revealTint = tint;
    }
}

