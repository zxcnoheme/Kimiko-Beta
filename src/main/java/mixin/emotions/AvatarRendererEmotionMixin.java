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
package mixin.emotions;

import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.PlayerLikeEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionPlayback;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionStateHolder;

@Mixin(value={PlayerEntityRenderer.class})
public abstract class AvatarRendererEmotionMixin {
    @Inject(method={"updateRenderState"}, at={@At(value="TAIL")}, require=0)
    private void kimiko$extractEmotion(PlayerLikeEntity entity, PlayerEntityRenderState state, float tickDelta, CallbackInfo ci) {
        EmotionPlayback.fill((EmotionStateHolder)state, (Entity)entity);
    }
}

