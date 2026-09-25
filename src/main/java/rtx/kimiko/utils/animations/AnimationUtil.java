/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmOverloads
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.animations;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\b\u0018\u0000 \"2\u00020\u0001:\u0003#$\"B1\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u001a\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\u000b\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0002\u00a2\u0006\u0004\b\r\u0010\fJ\r\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0012J\u0015\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0018\u0010\fJ\u0015\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0019\u0010\u0012J\u0017\u0010\u001a\u001a\u00020\u000e2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u0010R\u0016\u0010\u001e\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010 R\u0016\u0010\u0004\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0004\u0010 R\u0016\u0010\u0005\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010 R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010!\u00a8\u0006%"}, d2={"Lrtx/kimiko/utils/animations/AnimationUtil;", "", "", "anim", "to", "speed", "Lrtx/kimiko/utils/animations/AnimationUtil$Curve;", "easing", "Lkotlin/jvm/JvmOverloads;", "<init>", "(FFFLrtx/kimiko/utils/animations/AnimationUtil$Curve;)V", "getAnim", "()F", "getAngleAnim", "", "reset", "()V", "setAnim", "(F)V", "setTo", "", "value", "setToAsBoolean", "(Z)V", "getTo", "setSpeed", "setEasing", "(Lrtx/kimiko/utils/animations/AnimationUtil$Curve;)V", "update", "", "lastUpdateNs", "J", "F", "Lrtx/kimiko/utils/animations/AnimationUtil$Curve;", "Companion", "Curve", "Easing", "rtx.kimiko:kimiko"})
public final class AnimationUtil {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private long lastUpdateNs;
    private float anim;
    private float to;
    private float speed;
    @NotNull
    private Curve easing;
    private static final float EPSILON = 1.0E-4f;
    private static final float FRAME_NS = 1.6666667E7f;

    @JvmOverloads
    public AnimationUtil(float anim, float to, float speed, @Nullable Curve easing) {
        this.lastUpdateNs = System.nanoTime();
        this.anim = AnimationUtil.Companion.sanitize(anim);
        this.to = AnimationUtil.Companion.sanitize(to);
        this.speed = Math.max(0.0f, AnimationUtil.Companion.sanitize(speed));
        Curve curve = easing;
        if (curve == null) {
            curve = Easing.NONE;
        }
        this.easing = curve;
    }

    public /* synthetic */ AnimationUtil(float f, float f2, float f3, Curve curve, int n, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, ((n & 8) != 0 ? Easing.NONE : curve));
    }

    public final float getAnim() {
        this.update();
        return this.anim;
    }

    public final float getAngleAnim() {
        this.update();
        float value = this.anim % 360.0f;
        if (value >= 180.0f) {
            value -= 360.0f;
        } else if (value < -180.0f) {
            value += 360.0f;
        }
        return value;
    }

    public final void reset() {
        this.lastUpdateNs = System.nanoTime();
    }

    public final void setAnim(float anim) {
        this.anim = AnimationUtil.Companion.sanitize(anim);
        this.lastUpdateNs = System.nanoTime();
    }

    public final void setTo(float to) {
        float sanitized = AnimationUtil.Companion.sanitize(to);
        if (Math.abs(this.to - sanitized) > 1.0E-4f) {
            this.update();
        }
        this.to = sanitized;
    }

    public final void setToAsBoolean(boolean value) {
        this.setTo(value ? 1.0f : 0.0f);
    }

    public final float getTo() {
        return this.to;
    }

    public final void setSpeed(float speed) {
        this.speed = Math.max(0.0f, AnimationUtil.Companion.sanitize(speed));
    }

    public final void setEasing(@Nullable Curve easing) {
        Curve curve = easing;
        if (curve == null) {
            curve = Easing.NONE;
        }
        this.easing = curve;
    }

    private final void update() {
        long now = System.nanoTime();
        float framesAt60 = Math.min((float)(now - this.lastUpdateNs) / 1.6666667E7f, 12.0f);
        if (framesAt60 <= 0.0f) {
            return;
        }
        this.lastUpdateNs = now;
        float delta = this.to - this.anim;
        if (Math.abs(delta) <= 1.0E-4f) {
            this.anim = this.to;
            return;
        }
        float step = AnimationUtil.Companion.clamp(this.speed, 0.0f, 1.0f);
        float frameFactor = 1.0f - (float)Math.pow(1.0f - step, framesAt60);
        if (!(Math.abs(frameFactor = AnimationUtil.Companion.clamp(this.easing.apply(frameFactor), 0.0f, 1.0f)) <= Float.MAX_VALUE)) {
            frameFactor = step;
        }
        this.anim += delta * frameFactor;
        if (Math.abs(this.to - this.anim) <= 1.0E-4f) {
            this.anim = this.to;
        }
    }

    @JvmOverloads
    public AnimationUtil(float anim, float to, float speed) {
        this(anim, to, speed, null, 8, null);
    }

    @JvmStatic
    public static final void init() {
        Companion.init();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u0017\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\t\u0010\nJ'\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0010\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/utils/animations/AnimationUtil.Companion;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "init", "", "value", "sanitize", "(F)F", "min", "max", "clamp", "(FFF)F", "EPSILON", "F", "FRAME_NS", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final void init() {
        }

        private final float sanitize(float value) {
            return Math.abs(value) <= Float.MAX_VALUE ? value : 0.0f;
        }

        private final float clamp(float value, float min, float max) {
            if (max < min) {
                return min;
            }
            return Math.max(min, Math.min(max, value));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u00e6\u0080\u0001\u0018\u00002\u00020\u0001J\u0017\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0004\u0010\u0005\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\u0006\u00c0\u0006\u0001"}, d2={"Lrtx/kimiko/utils/animations/AnimationUtil$Curve;", "", "", "value", "apply", "(F)F", "rtx.kimiko:kimiko"})
    public static interface Curve {
        public float apply(float var1);
    }

    public static enum Easing implements Curve {
        NONE {
            @Override
            public float apply(float value) {
                return value;
            }
        },
        LINEAR {
            @Override
            public float apply(float value) {
                return value;
            }
        },
        SINE_OUT {
            @Override
            public float apply(float value) {
                return (float)Math.sin((double)value * Math.PI * 0.5);
            }
        },
        QUAD_OUT {
            @Override
            public float apply(float value) {
                float inv = 1.0f - value;
                return 1.0f - inv * inv;
            }
        },
        CUBIC_OUT {
            @Override
            public float apply(float value) {
                float inv = 1.0f - value;
                return 1.0f - inv * inv * inv;
            }
        },
        EXPO_OUT {
            @Override
            public float apply(float value) {
                return value >= 1.0f ? 1.0f : 1.0f - (float)Math.pow(2.0, -10.0 * (double)value);
            }
        },
        BACK_OUT {
            @Override
            public float apply(float value) {
                float c1 = 1.70158f;
                float c3 = c1 + 1.0f;
                float p = value - 1.0f;
                return 1.0f + c3 * p * p * p + c1 * p * p;
            }
        };

        @NotNull
        public static EnumEntries<Easing> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

        
    }
}

