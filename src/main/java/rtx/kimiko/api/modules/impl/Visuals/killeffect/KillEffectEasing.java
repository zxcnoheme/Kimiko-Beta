/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.ranges.RangesKt
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals.killeffect;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\t\u0010\bJ\u001b\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\n\u0010\bJ\u001b\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u000b\u0010\b\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectEasing;", "", "<init>", "()V", "", "value", "Lkotlin/jvm/JvmStatic;", "quintOut", "(F)F", "sineOut", "sineInOut", "expoInOut", "rtx.kimiko:kimiko"})
public final class KillEffectEasing {
    @NotNull
    public static final KillEffectEasing INSTANCE = new KillEffectEasing();

    private KillEffectEasing() {
    }

    @JvmStatic
    public static final float quintOut(float value) {
        float t = RangesKt.coerceIn((float)value, (float)0.0f, (float)1.0f);
        return 1.0f - (float)Math.pow(1.0f - t, 5.0);
    }

    @JvmStatic
    public static final float sineOut(float value) {
        float t = RangesKt.coerceIn((float)value, (float)0.0f, (float)1.0f);
        return (float)Math.sin((double)t * Math.PI / 2.0);
    }

    @JvmStatic
    public static final float sineInOut(float value) {
        float t = RangesKt.coerceIn((float)value, (float)0.0f, (float)1.0f);
        return (float)(-(Math.cos(Math.PI * (double)t) - 1.0) / 2.0);
    }

    @JvmStatic
    public static final float expoInOut(float value) {
        float t = RangesKt.coerceIn((float)value, (float)0.0f, (float)1.0f);
        if (t == 0.0f) {
            return 0.0f;
        }
        if (t == 1.0f) {
            return 1.0f;
        }
        return t < 0.5f ? (float)(Math.pow(2.0, 20.0 * (double)t - 10.0) / 2.0) : (float)((2.0 - Math.pow(2.0, -20.0 * (double)t + 10.0)) / 2.0);
    }
}

