/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.entity.state.BipedEntityRenderState
 *  net.minecraft.client.render.entity.state.PlayerEntityRenderState
 *  net.minecraft.client.render.entity.model.BipedEntityModel
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.emotions;

import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionPlayback;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionStateHolder;

@Mixin(value={BipedEntityModel.class})
public abstract class HumanoidModelEmotionMixin {
    @Inject(method={"setAngles"}, at={@At(value="TAIL")}, require=0)
    private void kimiko$applyEmotion(BipedEntityRenderState state, CallbackInfo ci) {
        if (!(state instanceof PlayerEntityRenderState)) {
            return;
        }
        EmotionPlayback.applyTo((BipedEntityModel)(Object)this, (EmotionStateHolder)state, state.limbSwingAmplitude);
    }
}

