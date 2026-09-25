/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  net.minecraft.util.math.MathHelper
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.modules.targetesp;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J+\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u000e\u0010\r\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/utils/render/modules/targetesp/TargetEspMath;", "", "<init>", "()V", "", "current", "target", "delta", "Lkotlin/jvm/JvmStatic;", "approach", "(FFF)F", "value", "easeOutCubic", "(F)F", "easeInOutQuad", "rtx.kimiko:kimiko"})
public final class TargetEspMath {
    @NotNull
    public static final TargetEspMath INSTANCE = new TargetEspMath();

    private TargetEspMath() {
    }

    @JvmStatic
    public static final float approach(float current, float target, float delta) {
        if (current < target) {
            return Math.min(current + delta, target);
        }
        return Math.max(current - delta, target);
    }

    @JvmStatic
    public static final float easeOutCubic(float value) {
        float clamped = MathHelper.clamp((float)value, (float)0.0f, (float)1.0f);
        float inv = 1.0f - clamped;
        return 1.0f - inv * inv * inv;
    }

    @JvmStatic
    public static final float easeInOutQuad(float value) {
        float clamped = MathHelper.clamp((float)value, (float)0.0f, (float)1.0f);
        return clamped < 0.5f ? 2.0f * clamped * clamped : 1.0f - (float)Math.pow(-2.0f * clamped + 2.0f, 2.0) / 2.0f;
    }
}

