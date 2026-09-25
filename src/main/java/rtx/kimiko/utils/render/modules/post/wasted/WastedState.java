/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.math.Vec3d
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.modules.post.wasted;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001c\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J+\u0010\f\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u000e\u001a\u00020\nH\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\u000e\u0010\u0003J\u001b\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000fH\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u000fH\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u000fH\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\u0015\u0010\u0014J\u0013\u0010\u0016\u001a\u00020\bH\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u0006H\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u0006H\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\u001a\u0010\u0019J\u0013\u0010\u001b\u001a\u00020\u0004H\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u0006H\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\u001d\u0010\u0019J\u0013\u0010\u001e\u001a\u00020\u0006H\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\u001e\u0010\u0019J\u0013\u0010\u001f\u001a\u00020\u0006H\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\u001f\u0010\u0019J\u0013\u0010 \u001a\u00020\u0006H\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b \u0010\u0019R\u0014\u0010!\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b#\u0010\"R\u0016\u0010$\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010&\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010\"R\u0016\u0010'\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u0010\"R\u0016\u0010\u001b\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010(R\u0016\u0010\u001d\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010)R\u0016\u0010*\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010%\u00a8\u0006+"}, d2={"Lrtx/kimiko/utils/render/modules/post/wasted/WastedState;", "", "<init>", "()V", "Lnet/minecraft/Vec3d;", "deathPosition", "", "yaw", "", "duration", "", "Lkotlin/jvm/JvmStatic;", "begin", "(Lnet/minecraft/Vec3d;FJ)V", "stop", "", "value", "setDetached", "(Z)V", "isDetached", "()Z", "isActive", "elapsedMs", "()J", "progress", "()F", "strength", "anchor", "()Lnet/minecraft/Vec3d;", "startYaw", "flash", "radialBlur", "textAlpha", "FADE_IN_MS", "J", "TEXT_DELAY_MS", "active", "Z", "startedAtMs", "durationMs", "Lnet/minecraft/Vec3d;", "F", "detached", "rtx.kimiko:kimiko"})
public final class WastedState {
    @NotNull
    public static final WastedState INSTANCE = new WastedState();
    public static final long FADE_IN_MS = 900L;
    public static final long TEXT_DELAY_MS = 1100L;
    private static boolean active;
    private static long startedAtMs;
    private static long durationMs;
    @NotNull
    private static Vec3d anchor;
    private static float startYaw;
    private static boolean detached;

    private WastedState() {
    }

    @JvmStatic
    public static final void begin(@NotNull Vec3d deathPosition, float yaw, long duration) {
        Intrinsics.checkNotNullParameter((Object)deathPosition, (String)"deathPosition");
        active = true;
        startedAtMs = System.currentTimeMillis();
        durationMs = Math.max(1000L, duration);
        anchor = deathPosition;
        startYaw = yaw;
        detached = false;
    }

    @JvmStatic
    public static final void stop() {
        active = false;
        detached = false;
    }

    @JvmStatic
    public static final void setDetached(boolean value) {
        detached = value;
    }

    @JvmStatic
    public static final boolean isDetached() {
        return detached && active;
    }

    @JvmStatic
    public static final boolean isActive() {
        if (active && WastedState.elapsedMs() >= durationMs) {
            active = false;
        }
        return active;
    }

    @JvmStatic
    public static final long elapsedMs() {
        return System.currentTimeMillis() - startedAtMs;
    }

    @JvmStatic
    public static final float progress() {
        return Math.clamp((float)WastedState.elapsedMs() / (float)durationMs, 0.0f, 1.0f);
    }

    @JvmStatic
    public static final float strength() {
        float rise = Math.clamp((float)WastedState.elapsedMs() / 900.0f, 0.0f, 1.0f);
        return rise * rise * (3.0f - 2.0f * rise);
    }

    @JvmStatic
    @NotNull
    public static final Vec3d anchor() {
        return anchor;
    }

    @JvmStatic
    public static final float startYaw() {
        return startYaw;
    }

    @JvmStatic
    public static final float flash() {
        float t = (float)WastedState.elapsedMs() / 320.0f;
        if (t >= 1.0f) {
            return 0.0f;
        }
        return (1.0f - t) * (1.0f - t);
    }

    @JvmStatic
    public static final float radialBlur() {
        float t = (float)WastedState.elapsedMs() / 1400.0f;
        if (t >= 1.0f) {
            return 0.0f;
        }
        float fall = 1.0f - t;
        return fall * fall;
    }

    @JvmStatic
    public static final float textAlpha() {
        long elapsed = WastedState.elapsedMs();
        if (elapsed < 1100L) {
            return 0.0f;
        }
        float in = Math.clamp((float)(elapsed - 1100L) / 550.0f, 0.0f, 1.0f);
        float out = 1.0f - Math.clamp((WastedState.progress() - 0.85f) / 0.15f, 0.0f, 1.0f);
        return in * in * (3.0f - 2.0f * in) * out;
    }

    static {
        Vec3d vec3d2 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
        anchor = vec3d2;
    }
}

