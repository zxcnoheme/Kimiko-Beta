/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.entity.state.PlayerEntityRenderState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 */
package mixin;

import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiHoldPoseState;

@Mixin(value={PlayerEntityRenderState.class})
public abstract class AvatarRenderStateMixin
implements GuiHoldPoseState {
    @Unique
    private float[] kimiko$guiHoldPose;

    @Override
    public void kimiko$setGuiHoldPose(float[] pose) {
        this.kimiko$guiHoldPose = pose;
    }

    @Override
    public float[] kimiko$getGuiHoldPose() {
        return this.kimiko$guiHoldPose;
    }
}

