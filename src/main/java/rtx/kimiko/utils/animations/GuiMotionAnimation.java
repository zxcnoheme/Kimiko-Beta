/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.ranges.RangesKt
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.animations;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 /2\u00020\u0001:\u00020/B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\r\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0003J\r\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\u0003J\r\u0010\b\u001a\u00020\u0004\u00a2\u0006\u0004\b\b\u0010\u0003J\r\u0010\t\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\u0003J\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\n\u00a2\u0006\u0004\b\r\u0010\fJ\r\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\n\u00a2\u0006\u0004\b\u0011\u0010\fJ\r\u0010\u0012\u001a\u00020\n\u00a2\u0006\u0004\b\u0012\u0010\fJ\r\u0010\u0013\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0003J\r\u0010\u0014\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0014\u0010\u0010J\r\u0010\u0015\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0015\u0010\u0010J\r\u0010\u0016\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0016\u0010\u0010J\u000f\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u0019R\u0016\u0010\u001d\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b!\u0010 R\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010 R\u0016\u0010&\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010 R\u0016\u0010'\u001a\u00020\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u0010$R\u0016\u0010(\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010+R\u0016\u0010-\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010+R\u0016\u0010.\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010 \u00a8\u00061"}, d2={"Lrtx/kimiko/utils/animations/GuiMotionAnimation;", "", "<init>", "()V", "", "startOpening", "resumeOpening", "startClosing", "snapClosed", "snapOpen", "", "isClosing", "()Z", "isCloseFinished", "", "closeProgress", "()F", "isAnimating", "canInteract", "updateFrame", "alpha", "scale", "blurRadius", "", "progress", "()D", "currentAlpha", "currentScaleRaw", "", "startNs", "J", "alphaFrom", "D", "alphaTo", "Lrtx/kimiko/utils/animations/GuiMotionAnimation$Ease;", "alphaEase", "Lrtx/kimiko/utils/animations/GuiMotionAnimation$Ease;", "scaleFrom", "scaleTo", "scaleEase", "closing", "Z", "frameAlpha", "F", "frameScale", "frameBlurRadius", "frameProgress", "Companion", "Ease", "rtx.kimiko:kimiko"})
public final class GuiMotionAnimation {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private long startNs = System.nanoTime() - 500000000L;
    private double alphaFrom;
    private double alphaTo;
    @NotNull
    private Ease alphaEase = CUBIC_OUT;
    private double scaleFrom = 1.25;
    private double scaleTo = 1.0;
    @NotNull
    private Ease scaleEase = QUINT_OUT;
    private boolean closing;
    private float frameAlpha;
    private float frameScale = 1.0f;
    private float frameBlurRadius;
    private double frameProgress = 1.0;
    private static final long DURATION_NS = 500000000L;
    private static final double OPEN_START_SCALE = 1.25;
    private static final double REST_SCALE = 1.0;
    private static final double CLOSE_END_SCALE = 0.75;
    private static final float SCALE_MIN = 0.8f;
    private static final float SCALE_RANGE = 0.2f;
    private static final float MAX_BLUR_RADIUS = 9.0f;
    @NotNull
    private static final Ease CUBIC_OUT = GuiMotionAnimation::CUBIC_OUT$lambda$0;
    @NotNull
    private static final Ease CUBIC_IN = GuiMotionAnimation::CUBIC_IN$lambda$0;
    @NotNull
    private static final Ease QUINT_OUT = GuiMotionAnimation::QUINT_OUT$lambda$0;
    @NotNull
    private static final Ease BACK_IN = GuiMotionAnimation::BACK_IN$lambda$0;

    public final void startOpening() {
        this.closing = false;
        this.startNs = System.nanoTime();
        this.alphaFrom = 0.0;
        this.alphaTo = 1.0;
        this.alphaEase = CUBIC_OUT;
        this.scaleFrom = 1.25;
        this.scaleTo = 1.0;
        this.scaleEase = QUINT_OUT;
        this.updateFrame();
    }

    public final void resumeOpening() {
        double curAlpha = this.currentAlpha();
        double curScale = this.currentScaleRaw();
        this.closing = false;
        this.startNs = System.nanoTime();
        this.alphaFrom = curAlpha;
        this.alphaTo = 1.0;
        this.alphaEase = CUBIC_OUT;
        this.scaleFrom = curScale;
        this.scaleTo = 1.0;
        this.scaleEase = QUINT_OUT;
        this.updateFrame();
    }

    public final void startClosing() {
        if (this.closing) {
            return;
        }
        double curAlpha = this.currentAlpha();
        double curScale = this.currentScaleRaw();
        this.closing = true;
        this.startNs = System.nanoTime();
        this.alphaFrom = curAlpha;
        this.alphaTo = 0.0;
        this.alphaEase = CUBIC_IN;
        this.scaleFrom = curScale;
        this.scaleTo = 0.75;
        this.scaleEase = BACK_IN;
        this.updateFrame();
    }

    public final void snapClosed() {
        this.closing = false;
        this.alphaFrom = 0.0;
        this.alphaTo = 0.0;
        this.scaleFrom = 0.75;
        this.scaleTo = 0.75;
        this.startNs = System.nanoTime() - 500000000L;
        this.updateFrame();
    }

    public final void snapOpen() {
        this.closing = false;
        this.alphaFrom = 1.0;
        this.alphaTo = 1.0;
        this.scaleFrom = 1.0;
        this.scaleTo = 1.0;
        this.startNs = System.nanoTime() - 500000000L;
        this.updateFrame();
    }

    public final boolean isClosing() {
        return this.closing;
    }

    public final boolean isCloseFinished() {
        return this.closing && this.frameProgress >= 1.0;
    }

    public final float closeProgress() {
        return this.closing ? (float)this.frameProgress : 0.0f;
    }

    public final boolean isAnimating() {
        return this.frameProgress < 1.0;
    }

    public final boolean canInteract() {
        return !this.closing && this.frameAlpha >= 0.9f;
    }

    public final void updateFrame() {
        double p;
        this.frameProgress = p = this.progress();
        double a = this.alphaFrom + (this.alphaTo - this.alphaFrom) * this.alphaEase.apply(p);
        double s = this.scaleFrom + (this.scaleTo - this.scaleFrom) * this.scaleEase.apply(p);
        this.frameAlpha = GuiMotionAnimation.Companion.clamp01((float)a);
        this.frameScale = 0.8f + (float)s * 0.2f;
        this.frameBlurRadius = 9.0f * (1.0f - this.frameAlpha);
    }

    public final float alpha() {
        return this.frameAlpha;
    }

    public final float scale() {
        return this.frameScale;
    }

    public final float blurRadius() {
        return this.frameBlurRadius;
    }

    private final double progress() {
        long elapsed = System.nanoTime() - this.startNs;
        if (elapsed >= 500000000L) {
            return 1.0;
        }
        if (elapsed <= 0L) {
            return 0.0;
        }
        return (double)elapsed / 5.0E8;
    }

    private final double currentAlpha() {
        double p = this.progress();
        return this.alphaFrom + (this.alphaTo - this.alphaFrom) * this.alphaEase.apply(p);
    }

    private final double currentScaleRaw() {
        double p = this.progress();
        return this.scaleFrom + (this.scaleTo - this.scaleFrom) * this.scaleEase.apply(p);
    }

    private static final double CUBIC_OUT$lambda$0(double x) {
        return 1.0 - Math.pow(1.0 - x, 3.0);
    }

    private static final double CUBIC_IN$lambda$0(double x) {
        return Math.pow(x, 3.0);
    }

    private static final double QUINT_OUT$lambda$0(double x) {
        return 1.0 - Math.pow(1.0 - x, 5.0);
    }

    private static final double BACK_IN$lambda$0(double x) {
        return 2.70158 * Math.pow(x, 3.0) - 1.70158 * Math.pow(x, 2.0);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0011R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00148\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00148\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u00148\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0016\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/utils/animations/GuiMotionAnimation.Companion;", "", "<init>", "()V", "", "value", "clamp01", "(F)F", "", "DURATION_NS", "J", "", "OPEN_START_SCALE", "D", "REST_SCALE", "CLOSE_END_SCALE", "SCALE_MIN", "F", "SCALE_RANGE", "MAX_BLUR_RADIUS", "Lrtx/kimiko/utils/animations/GuiMotionAnimation$Ease;", "CUBIC_OUT", "Lrtx/kimiko/utils/animations/GuiMotionAnimation$Ease;", "CUBIC_IN", "QUINT_OUT", "BACK_IN", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float clamp01(float value) {
            return RangesKt.coerceIn((float)value, (float)0.0f, (float)1.0f);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\b\u00e2\u0080\u0001\u0018\u00002\u00020\u0001J\u0017\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0004\u0010\u0005\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\u0006\u00c0\u0006\u0001"}, d2={"Lrtx/kimiko/utils/animations/GuiMotionAnimation$Ease;", "", "", "x", "apply", "(D)D", "rtx.kimiko:kimiko"})
    private static interface Ease {
        public double apply(double var1);
    }
}

