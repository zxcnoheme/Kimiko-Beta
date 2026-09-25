/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmOverloads
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.window;

import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.ui.window.GuiShatterAnimation;
import rtx.kimiko.api.ui.window.WorldGuiCloseAnimation;
import rtx.kimiko.utils.animations.GuiMotionAnimation;
import rtx.kimiko.utils.render.modules.post.guilayerblur.GuiCapture;
import rtx.kimiko.utils.render.modules.post.guilayerblur.GuiLayerBlurRenderer;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u001a\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ-\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r\u00a2\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0017\u0010\u0016J\r\u0010\u0018\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0018\u0010\u0016J\r\u0010\u0019\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0019\u0010\u0016J\r\u0010\u001a\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001a\u0010\tJ\r\u0010\u001b\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001b\u0010\tJ\r\u0010\u001c\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001c\u0010\tJ\r\u0010\u001d\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001d\u0010\tJ\r\u0010\u001e\u001a\u00020\r\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\r\u0010 \u001a\u00020\r\u00a2\u0006\u0004\b \u0010\u001fJ\u0017\u0010#\u001a\u00020\u00122\b\u0010\"\u001a\u0004\u0018\u00010!\u00a2\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b%\u0010\tJ\u000f\u0010&\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b&\u0010\u001fJ\u000f\u0010'\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b'\u0010\u001fJ\u000f\u0010(\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b(\u0010\u001fJ\u000f\u0010)\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b)\u0010\u001fJ\u000f\u0010*\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b*\u0010\tJ\u000f\u0010+\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b+\u0010\u0016R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010,R\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010,R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010-R\u0016\u0010.\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010/R\u0016\u00100\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u0010/R\u0016\u00101\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u0010/R\u0016\u00102\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u0010/R\u0016\u0010&\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010/R\u0016\u00103\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u0010/R\u0016\u00104\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u0010,\u00a8\u00065"}, d2={"Lrtx/kimiko/api/ui/window/PanelAnimation;", "Lrtx/kimiko/utils/render/modules/post/guilayerblur/GuiCapture$Source;", "", "worldClose", "captureTrailingDraws", "Lkotlin/jvm/JvmOverloads;", "<init>", "(ZZ)V", "emitPanelBoundary", "()Z", "Lrtx/kimiko/utils/animations/GuiMotionAnimation;", "motion", "()Lrtx/kimiko/utils/animations/GuiMotionAnimation;", "", "x", "y", "width", "height", "", "setPanelRect", "(FFFF)V", "open", "()V", "close", "finish", "updateFrame", "detached", "isClosing", "isCloseFinished", "canInteract", "contentAlpha", "()F", "dimAlpha", "Lnet/minecraft/DrawContext;", "graphics", "beginCaptureStratum", "(Lnet/minecraft/DrawContext;)V", "captureActive", "captureScale", "captureBlurRadius", "shatterProgress", "rawCloseProgress", "worldFlightActive", "beginShatter", "Z", "Lrtx/kimiko/utils/animations/GuiMotionAnimation;", "rectX", "F", "rectY", "rectW", "rectH", "captureBlurMainPx", "live", "rtx.kimiko:kimiko"})
public final class PanelAnimation
implements GuiCapture.Source {
    private final boolean worldClose;
    private final boolean captureTrailingDraws;
    @NotNull
    private final GuiMotionAnimation motion;
    private float rectX;
    private float rectY;
    private float rectW;
    private float rectH;
    private float captureScale;
    private float captureBlurMainPx;
    private boolean live;

    @JvmOverloads
    public PanelAnimation(boolean worldClose, boolean captureTrailingDraws) {
        this.worldClose = worldClose;
        this.captureTrailingDraws = captureTrailingDraws;
        this.motion = new GuiMotionAnimation();
        this.captureScale = 1.0f;
    }

    public /* synthetic */ PanelAnimation(boolean bl, boolean bl2, int n, DefaultConstructorMarker defaultConstructorMarker) {
        this(bl, ((n & 2) != 0 ? false : bl2));
    }

    @Override
    public boolean emitPanelBoundary() {
        return !this.captureTrailingDraws;
    }

    @NotNull
    public final GuiMotionAnimation motion() {
        return this.motion;
    }

    public final void setPanelRect(float x, float y, float width, float height) {
        this.rectX = x;
        this.rectY = y;
        this.rectW = width;
        this.rectH = height;
    }

    public final void open() {
        this.live = true;
        GuiCapture.bind(this);
        if (this.motion.isClosing()) {
            if (this.worldClose) {
                WorldGuiCloseAnimation.reverse();
            } else {
                WorldGuiCloseAnimation.cancel();
            }
            GuiShatterAnimation.gather(WorldGuiCloseAnimation.isReversing() ? WorldGuiCloseAnimation.remainingNanos() : 0L);
            this.motion.resumeOpening();
            return;
        }
        WorldGuiCloseAnimation.cancel();
        GuiShatterAnimation.cancel();
        this.motion.startOpening();
    }

    public final void close() {
        if (this.motion.isClosing()) {
            return;
        }
        this.live = true;
        GuiCapture.bind(this);
        float handoffAlpha = this.motion.alpha();
        float handoffScale = this.motion.scale();
        this.motion.startClosing();
        if (!GuiLayerBlurRenderer.available()) {
            WorldGuiCloseAnimation.cancel();
            GuiShatterAnimation.cancel();
            return;
        }
        if (this.worldClose) {
            WorldGuiCloseAnimation.begin(handoffAlpha, handoffScale);
        } else {
            WorldGuiCloseAnimation.cancel();
        }
        this.beginShatter();
    }

    public final void finish() {
        this.live = false;
        WorldGuiCloseAnimation.cancel();
        GuiShatterAnimation.cancel();
    }

    public final void updateFrame() {
        this.motion.updateFrame();
        if (this.worldClose) {
            WorldGuiCloseAnimation.updateFrame();
            if (WorldGuiCloseAnimation.isReversing() && WorldGuiCloseAnimation.isFinished()) {
                WorldGuiCloseAnimation.cancel();
                GuiShatterAnimation.cancel();
                if (!this.motion.isClosing()) {
                    this.motion.snapOpen();
                }
            }
        }
        this.captureScale = this.motion.scale();
        float blurGuiPx = this.detached() ? WorldGuiCloseAnimation.blurRadius() : this.motion.blurRadius();
        this.captureBlurMainPx = Math.max(blurGuiPx, GuiShatterAnimation.blurRadius()) * Render2DCoordinateSpace.designGuiScale();
    }

    public final boolean detached() {
        return this.worldClose && WorldGuiCloseAnimation.isDetachedRender();
    }

    public final boolean isClosing() {
        return this.motion.isClosing();
    }

    public final boolean isCloseFinished() {
        return this.motion.isCloseFinished() && !this.worldFlightActive();
    }

    public final boolean canInteract() {
        return this.motion.canInteract();
    }

    public final float contentAlpha() {
        return this.detached() ? 1.0f : this.motion.alpha();
    }

    public final float dimAlpha() {
        return this.motion.alpha();
    }

    public final void beginCaptureStratum(@Nullable DrawContext graphics) {
        if (graphics == null || !this.captureActive()) {
            return;
        }
        Render2D.flush();
        Render2D.beginFrame(graphics);
        GuiRenderState state = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
        state.createNewRootLayer();
        state.applyBlur();
    }

    @Override
    public boolean captureActive() {
        if (!this.live) {
            return false;
        }
        return this.motion.isAnimating() || this.worldFlightActive();
    }

    @Override
    public float captureScale() {
        return this.captureScale;
    }

    @Override
    public float captureBlurRadius() {
        return this.captureBlurMainPx;
    }

    @Override
    public float shatterProgress() {
        return GuiShatterAnimation.progress(this.rawCloseProgress());
    }

    private final float rawCloseProgress() {
        return this.worldClose && WorldGuiCloseAnimation.isActive() ? WorldGuiCloseAnimation.progress() : this.motion.closeProgress();
    }

    private final boolean worldFlightActive() {
        return this.worldClose && WorldGuiCloseAnimation.isActive() && !WorldGuiCloseAnimation.isFinished();
    }

    private final void beginShatter() {
        if (GuiShatterAnimation.resume(this.rawCloseProgress())) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.getWindow() == null || this.rectW <= 0.0f || this.rectH <= 0.0f) {
            GuiShatterAnimation.cancel();
            return;
        }
        float px = Render2DCoordinateSpace.designGuiScale();
        InterfaceModule module = InterfaceModule.Companion.getInstance();
        float glowPad = module != null && module.rectGlow.getValue() ? module.rectGlowRadius.getFloat() : 0.0f;
        GuiShatterAnimation.begin(this.rectX * px, this.rectY * px, this.rectW * px, this.rectH * px, (glowPad + 14.0f) * px, mc.getWindow().getFramebufferWidth(), mc.getWindow().getFramebufferHeight());
        GuiShatterAnimation.rebase(this.rawCloseProgress());
    }

    @JvmOverloads
    public PanelAnimation(boolean worldClose) {
        this(worldClose, false, 2, null);
    }
}

