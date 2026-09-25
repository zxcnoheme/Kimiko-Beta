/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.animations;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b!\u0018\u0000 +2\u00020\u0001:\u0001+B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000b\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0012J\r\u0010\u0014\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0016\u0010\rJ\r\u0010\u0017\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0017\u0010\rJ\r\u0010\u0018\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0018\u0010\rJ\r\u0010\u0019\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0019\u0010\rJ\r\u0010\u001a\u001a\u00020\b\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u000b\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010!\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b#\u0010\u001bJ\u0017\u0010$\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b$\u0010%R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010&R\u0016\u0010'\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u0010&R\u0016\u0010(\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010&R\u0016\u0010)\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010*\u00a8\u0006,"}, d2={"Lrtx/kimiko/utils/animations/HudFadeAnimation;", "", "", "speed", "<init>", "(F)V", "", "value", "", "set", "(D)V", "", "update", "()Z", "visible", "updateTarget", "(Z)F", "get", "()F", "getProgress", "getToValue", "()D", "isAnimating", "isHidden", "isTargetVisible", "wasUpdatedThisFrame", "beginFrame", "()V", "defaultVisible", "ensureUpdated", "(Z)V", "current", "destination", "lerpTowards", "(FF)F", "snapAtThresholds", "clamp01", "(F)F", "F", "progress", "target", "updatedThisFrame", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class HudFadeAnimation {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final float speed;
    private float progress;
    private float target;
    private boolean updatedThisFrame;
    public static final float DEFAULT_SPEED = 15.0f;
    private static final float OPAQUE_THRESHOLD = 0.98f;
    private static final float HIDDEN_THRESHOLD = 0.02f;
    private static final float HIDDEN_EPSILON = 1.0E-4f;
    private static final float ANIMATION_EPSILON = 1.0E-4f;

    public HudFadeAnimation(float speed) {
        this.speed = speed;
    }

    public /* synthetic */ HudFadeAnimation(float f, int n, DefaultConstructorMarker defaultConstructorMarker) {
        this(((n & 1) != 0 ? 15.0f : f));
    }

    public final void set(double value) {
        float next;
        this.progress = next = this.clamp01((float)value);
        this.target = next;
        this.updatedThisFrame = false;
    }

    public final boolean update() {
        this.progress = this.lerpTowards(this.progress, this.target);
        this.updatedThisFrame = true;
        this.snapAtThresholds();
        return this.isAnimating();
    }

    public final float updateTarget(boolean visible) {
        this.target = visible ? 1.0f : 0.0f;
        this.progress = this.lerpTowards(this.progress, this.target);
        this.updatedThisFrame = true;
        this.snapAtThresholds();
        return this.progress;
    }

    public final float get() {
        this.snapAtThresholds();
        return this.progress;
    }

    public final float getProgress() {
        return this.get();
    }

    public final double getToValue() {
        return this.target;
    }

    public final boolean isAnimating() {
        return Math.abs(this.progress - this.target) > 1.0E-4f;
    }

    public final boolean isHidden() {
        return this.progress <= 1.0E-4f;
    }

    public final boolean isTargetVisible() {
        return this.target >= 0.5f;
    }

    public final boolean wasUpdatedThisFrame() {
        return this.updatedThisFrame;
    }

    public final void beginFrame() {
        this.updatedThisFrame = false;
    }

    public final void ensureUpdated(boolean defaultVisible) {
        if (!this.updatedThisFrame) {
            this.updateTarget(defaultVisible);
        }
    }

    private final float lerpTowards(float current, float destination) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        int fps = minecraft.getCurrentFps();
        float frameDelta = fps > 0 ? 1.0f / (float)fps : 1.0f;
        float factor = RangesKt.coerceIn((float)(frameDelta * this.speed), (float)0.0f, (float)1.0f);
        return current + (destination - current) * factor;
    }

    private final void snapAtThresholds() {
        if (this.target >= 0.5f) {
            if (this.progress >= 0.98f) {
                this.progress = 1.0f;
            }
        } else if (this.progress <= 0.02f) {
            this.progress = 0.0f;
        }
    }

    private final float clamp01(float value) {
        return RangesKt.coerceIn((float)value, (float)0.0f, (float)1.0f);
    }

    public HudFadeAnimation() {
        this(0.0f, 1, null);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0006\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/utils/animations/HudFadeAnimation.Companion;", "", "<init>", "()V", "", "DEFAULT_SPEED", "F", "OPAQUE_THRESHOLD", "HIDDEN_THRESHOLD", "HIDDEN_EPSILON", "ANIMATION_EPSILON", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

