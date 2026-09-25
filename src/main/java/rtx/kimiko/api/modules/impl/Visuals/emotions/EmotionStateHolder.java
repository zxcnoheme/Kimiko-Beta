/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.emotions;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Visuals.emotions.Emotion;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\b\u0010\u0007J)\u0010\r\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\u00022\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\r\u0010\u000e\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\u000f\u00c0\u0006\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionStateHolder;", "", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "kimiko$getEmotion", "()Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "", "kimiko$getEmotionTime", "()F", "kimiko$getEmotionWeight", "emotion", "time", "weight", "", "kimiko$setEmotion", "(Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;FF)V", "rtx.kimiko:kimiko"})
public interface EmotionStateHolder {
    @Nullable
    public Emotion kimiko$getEmotion();

    public float kimiko$getEmotionTime();

    public float kimiko$getEmotionWeight();

    public void kimiko$setEmotion(@Nullable Emotion var1, float var2, float var3);
}

