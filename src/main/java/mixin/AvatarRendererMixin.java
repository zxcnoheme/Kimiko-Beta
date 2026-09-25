/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.entity.state.PlayerEntityRenderState
 *  net.minecraft.client.render.entity.PlayerEntityRenderer
 *  net.minecraft.entity.PlayerLikeEntity
 *  net.minecraft.entity.Entity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.PlayerLikeEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiHoldPose;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiHoldPoseState;
import rtx.kimiko.api.modules.impl.Visuals.NameTags;

@Mixin(value={PlayerEntityRenderer.class})
public abstract class AvatarRendererMixin {
    @Inject(method={"updateRenderState"}, at={@At(value="TAIL")}, require=0)
    private void kimiko$hidePlayerPlates(PlayerLikeEntity entity, PlayerEntityRenderState state, float tickDelta, CallbackInfo ci) {
        if (NameTags.hidesNameTagFor((Entity)entity)) {
            state.displayName = null;
            state.playerName = null;
        }
    }

    @Inject(method={"updateRenderState"}, at={@At(value="TAIL")}, require=0)
    private void kimiko$captureGuiHoldPose(PlayerLikeEntity entity, PlayerEntityRenderState state, float tickDelta, CallbackInfo ci) {
        if (state instanceof GuiHoldPoseState) {
            GuiHoldPoseState holder = (GuiHoldPoseState)state;
            holder.kimiko$setGuiHoldPose(GuiHoldPose.compute(entity, tickDelta));
        }
    }
}

