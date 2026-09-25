/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.entity.state.PlayerEntityRenderState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 */
package mixin.emotions;

import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import rtx.kimiko.api.modules.impl.Visuals.emotions.Emotion;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionStateHolder;

@Mixin(value={PlayerEntityRenderState.class})
public abstract class AvatarRenderStateEmotionMixin
implements EmotionStateHolder {
    @Unique
    private Emotion kimiko$emotion;
    @Unique
    private float kimiko$emotionTime;
    @Unique
    private float kimiko$emotionWeight;

    @Override
    public Emotion kimiko$getEmotion() {
        return this.kimiko$emotion;
    }

    @Override
    public float kimiko$getEmotionTime() {
        return this.kimiko$emotionTime;
    }

    @Override
    public float kimiko$getEmotionWeight() {
        return this.kimiko$emotionWeight;
    }

    @Override
    public void kimiko$setEmotion(Emotion emotion, float time, float weight) {
        this.kimiko$emotion = emotion;
        this.kimiko$emotionTime = time;
        this.kimiko$emotionWeight = weight;
    }
}

