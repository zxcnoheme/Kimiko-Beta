/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.drags;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.DragLerpAnim;
import rtx.kimiko.api.drags.DragOverlayRenderer;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.utils.math.MathUtils;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\t\n\u0002\b\r\u0018\u0000 w2\u00020\u0001:\u0001wB\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\t\u0010\bJ\r\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\n\u0010\bJ\u000f\u0010\u000b\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u000b\u0010\bJ\r\u0010\f\u001a\u00020\u0002\u00a2\u0006\u0004\b\f\u0010\bJ\r\u0010\r\u001a\u00020\u0002\u00a2\u0006\u0004\b\r\u0010\bJ\r\u0010\u000e\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000e\u0010\bJ\r\u0010\u000f\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000f\u0010\bJ\u0015\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0013J\u0015\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0013J\u0015\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0013J\u001f\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002H\u0000\u00a2\u0006\u0004\b\u001b\u0010\u0006J\u001d\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001f\u0010\u0006J%\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u0002\u00a2\u0006\u0004\b\u001f\u0010!J\r\u0010\"\u001a\u00020\u0011\u00a2\u0006\u0004\b\"\u0010#J\r\u0010$\u001a\u00020\u0011\u00a2\u0006\u0004\b$\u0010#J\r\u0010%\u001a\u00020\u0011\u00a2\u0006\u0004\b%\u0010#J\r\u0010'\u001a\u00020&\u00a2\u0006\u0004\b'\u0010(J\r\u0010)\u001a\u00020\u0002\u00a2\u0006\u0004\b)\u0010\bJ\u000f\u0010+\u001a\u00020\u0011H\u0000\u00a2\u0006\u0004\b*\u0010#J\u000f\u0010-\u001a\u00020\u0011H\u0000\u00a2\u0006\u0004\b,\u0010#J\u001d\u00100\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u00022\u0006\u0010/\u001a\u00020&\u00a2\u0006\u0004\b0\u00101J\r\u00102\u001a\u00020&\u00a2\u0006\u0004\b2\u0010(J\r\u00103\u001a\u00020\u0002\u00a2\u0006\u0004\b3\u0010\bJ\u0011\u00107\u001a\u0004\u0018\u000104H\u0000\u00a2\u0006\u0004\b5\u00106J\u0011\u00109\u001a\u0004\u0018\u000104H\u0000\u00a2\u0006\u0004\b8\u00106J\u0011\u0010;\u001a\u0004\u0018\u000104H\u0000\u00a2\u0006\u0004\b:\u00106J\u0011\u0010=\u001a\u0004\u0018\u000104H\u0000\u00a2\u0006\u0004\b<\u00106J\u000f\u0010?\u001a\u00020\u0002H\u0000\u00a2\u0006\u0004\b>\u0010\bJ\u000f\u0010A\u001a\u00020\u0002H\u0000\u00a2\u0006\u0004\b@\u0010\bJ\u0017\u0010D\u001a\u00020\u00112\u0006\u0010B\u001a\u00020\u0002H\u0000\u00a2\u0006\u0004\bC\u0010\u0013J\u0017\u0010G\u001a\u00020\u00112\u0006\u0010E\u001a\u00020\u0002H\u0000\u00a2\u0006\u0004\bF\u0010\u0013J5\u0010J\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u00022\u0006\u0010H\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u00022\u0006\u0010I\u001a\u00020&\u00a2\u0006\u0004\bJ\u0010KJ=\u0010J\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u00022\u0006\u0010H\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u00022\u0006\u0010I\u001a\u00020&2\u0006\u0010 \u001a\u00020\u0002\u00a2\u0006\u0004\bJ\u0010LJ5\u0010S\u001a\u00020\u00112\u0006\u0010N\u001a\u00020M2\u0006\u0010O\u001a\u00020\u00022\u0006\u0010P\u001a\u00020\u00022\u0006\u0010Q\u001a\u00020\u00022\u0006\u0010R\u001a\u00020\u0002\u00a2\u0006\u0004\bS\u0010TJ-\u0010U\u001a\u00020&2\u0006\u0010.\u001a\u00020\u00022\u0006\u0010H\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u0002\u00a2\u0006\u0004\bU\u0010VJ\r\u0010W\u001a\u00020\u0011\u00a2\u0006\u0004\bW\u0010#J\r\u0010X\u001a\u00020\u0011\u00a2\u0006\u0004\bX\u0010#J-\u0010Y\u001a\u00020&2\u0006\u0010.\u001a\u00020\u00022\u0006\u0010H\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u0002\u00a2\u0006\u0004\bY\u0010VJ\u000f\u0010Z\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\bZ\u0010#R\u0016\u0010[\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b[\u0010\\R\u0016\u0010]\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b]\u0010\\R\u0016\u0010^\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010\\R\u0016\u0010_\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010\\R\u0016\u0010`\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010\\R\u0016\u0010a\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010\\R\u0016\u0010b\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010\\R\u0016\u0010c\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010\\R\u0016\u0010d\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bd\u0010eR\u0016\u0010f\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bf\u0010eR\u0016\u0010B\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u0010\\R\u0016\u0010E\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bE\u0010\\R\u0016\u0010g\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bg\u0010\\R\u0016\u0010h\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bh\u0010\\R\u0016\u0010i\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bi\u0010\\R\u0016\u0010j\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bj\u0010\\R\u0016\u0010l\u001a\u00020k8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bl\u0010mR\u0016\u0010n\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bn\u0010eR\u0016\u0010o\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bo\u0010\\R\u0016\u0010p\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bp\u0010\\R\u0016\u0010I\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bI\u0010eR\u0014\u0010q\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bq\u0010rR\u0018\u0010s\u001a\u0004\u0018\u0001048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bs\u0010rR\u0018\u0010t\u001a\u0004\u0018\u0001048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010rR\u0018\u0010u\u001a\u0004\u0018\u0001048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bu\u0010rR\u0018\u0010v\u001a\u0004\u0018\u0001048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bv\u0010r\u00a8\u0006x"}, d2={"Lrtx/kimiko/api/drags/DragController;", "", "", "initialX", "initialY", "<init>", "(FF)V", "getRenderX", "()F", "computeRenderX", "getRenderY", "computeRenderY", "getTargetX", "getTargetY", "getDesiredX", "getDesiredY", "x", "", "setTargetX", "(F)V", "y", "setTargetY", "delta", "adjustX", "adjustY", "deltaX", "deltaY", "shiftRenderLock$rtx_kimiko_kimiko", "shiftRenderLock", "width", "height", "applyScreenClamp", "margin", "(FFF)V", "syncToTarget", "()V", "snapOverlayToTarget", "swapGrabOffset", "", "isDragging", "()Z", "getTiltAngle", "beginRender$rtx_kimiko_kimiko", "beginRender", "endRender$rtx_kimiko_kimiko", "endRender", "mouseX", "enabled", "updateTilt", "(FZ)V", "wasCancelled", "overlayAlpha", "Lrtx/kimiko/api/drags/DragLerpAnim;", "getAnimX$rtx_kimiko_kimiko", "()Lrtx/kimiko/api/drags/DragLerpAnim;", "getAnimX", "getAnimY$rtx_kimiko_kimiko", "getAnimY", "getAnimX2$rtx_kimiko_kimiko", "getAnimX2", "getAnimY2$rtx_kimiko_kimiko", "getAnimY2", "getSnapLineX$rtx_kimiko_kimiko", "getSnapLineX", "getSnapLineY$rtx_kimiko_kimiko", "getSnapLineY", "snapLineX", "setSnapLineX$rtx_kimiko_kimiko", "setSnapLineX", "snapLineY", "setSnapLineY$rtx_kimiko_kimiko", "setSnapLineY", "mouseY", "directDrag", "tick", "(FFFFZ)V", "(FFFFZF)V", "Lnet/minecraft/DrawContext;", "graphics", "anchorWidth", "anchorHeight", "liveWidth", "liveHeight", "renderOverlay", "(Lnet/minecraft/DrawContext;FFFF)V", "tryGrab", "(FFFF)Z", "release", "cancel", "isHovered", "ensureAnims", "xPos", "F", "yPos", "desiredX", "desiredY", "startX", "startY", "preDragX", "preDragY", "dragging", "Z", "cancelled", "tiltAngle", "tiltVelocity", "filteredMouseVelocity", "lastTiltMouseX", "", "lastTiltUpdateNs", "J", "renderPositionLocked", "lockedRenderX", "lockedRenderY", "dragAnimPc", "Lrtx/kimiko/api/drags/DragLerpAnim;", "animX", "animY", "animX2", "animY2", "Companion", "rtx.kimiko:kimiko"})
public final class DragController {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private float xPos;
    private float yPos;
    private float desiredX;
    private float desiredY;
    private float startX;
    private float startY;
    private float preDragX;
    private float preDragY;
    private boolean dragging;
    private boolean cancelled;
    private float snapLineX;
    private float snapLineY;
    private float tiltAngle;
    private float tiltVelocity;
    private float filteredMouseVelocity;
    private float lastTiltMouseX;
    private long lastTiltUpdateNs;
    private boolean renderPositionLocked;
    private float lockedRenderX;
    private float lockedRenderY;
    private boolean directDrag;
    @NotNull
    private final DragLerpAnim dragAnimPc;
    @Nullable
    private DragLerpAnim animX;
    @Nullable
    private DragLerpAnim animY;
    @Nullable
    private DragLerpAnim animX2;
    @Nullable
    private DragLerpAnim animY2;
    public static final long PULSE_PERIOD_MS = 500L;

    public DragController(float initialX, float initialY) {
        this.xPos = initialX;
        this.yPos = initialY;
        this.desiredX = initialX;
        this.desiredY = initialY;
        this.snapLineX = Float.NaN;
        this.snapLineY = Float.NaN;
        this.dragAnimPc = new DragLerpAnim(0.0f, 0.0f, 0.125f);
    }

    public final float getRenderX() {
        if (this.renderPositionLocked) {
            return this.lockedRenderX;
        }
        return this.computeRenderX();
    }

    private final float computeRenderX() {
        this.ensureAnims();
        DragLerpAnim dragLerpAnim = this.animX;
        Intrinsics.checkNotNull((Object)dragLerpAnim);
        float x = dragLerpAnim.getAnim();
        if (this.dragging) {
            float pc = DragController.Companion.jitterStrength();
            x += MathUtils.getRandom(-2.0f * pc, 2.0f * pc);
        } else {
            DragLerpAnim dragLerpAnim2 = this.animX;
            Intrinsics.checkNotNull((Object)dragLerpAnim2);
            dragLerpAnim2.setTo(this.xPos);
        }
        return x;
    }

    public final float getRenderY() {
        if (this.renderPositionLocked) {
            return this.lockedRenderY;
        }
        return this.computeRenderY();
    }

    private final float computeRenderY() {
        this.ensureAnims();
        DragLerpAnim dragLerpAnim = this.animY;
        Intrinsics.checkNotNull((Object)dragLerpAnim);
        float y = dragLerpAnim.getAnim();
        if (this.dragging) {
            float pc = DragController.Companion.jitterStrength();
            y += MathUtils.getRandom(-2.0f * pc, 2.0f * pc);
        } else {
            DragLerpAnim dragLerpAnim2 = this.animY;
            Intrinsics.checkNotNull((Object)dragLerpAnim2);
            dragLerpAnim2.setTo(this.yPos);
        }
        return y;
    }

    public final float getTargetX() {
        return this.xPos;
    }

    public final float getTargetY() {
        return this.yPos;
    }

    public final float getDesiredX() {
        return this.desiredX;
    }

    public final float getDesiredY() {
        return this.desiredY;
    }

    public final void setTargetX(float x) {
        this.xPos = x;
        this.desiredX = x;
    }

    public final void setTargetY(float y) {
        this.yPos = y;
        this.desiredY = y;
    }

    public final void adjustX(float delta) {
        this.xPos += delta;
        this.desiredX += delta;
    }

    public final void adjustY(float delta) {
        this.yPos += delta;
        this.desiredY += delta;
    }

    public final void shiftRenderLock$rtx_kimiko_kimiko(float deltaX, float deltaY) {
        if (!this.renderPositionLocked) {
            return;
        }
        this.lockedRenderX += deltaX;
        this.lockedRenderY += deltaY;
    }

    public final void applyScreenClamp(float width, float height) {
        this.applyScreenClamp(width, height, 5.0f);
    }

    public final void applyScreenClamp(float width, float height, float margin) {
        if (Position.Companion.screenWidth() <= 1.0f || Position.Companion.screenHeight() <= 1.0f) {
            return;
        }
        this.xPos = Position.Companion.clampX(this.desiredX, width, margin);
        this.yPos = Position.Companion.clampY(this.desiredY, height, margin);
    }

    public final void syncToTarget() {
        this.ensureAnims();
        DragLerpAnim dragLerpAnim = this.animX;
        Intrinsics.checkNotNull((Object)dragLerpAnim);
        dragLerpAnim.setAnim(this.xPos);
        DragLerpAnim dragLerpAnim2 = this.animX;
        Intrinsics.checkNotNull((Object)dragLerpAnim2);
        dragLerpAnim2.setTo(this.xPos);
        DragLerpAnim dragLerpAnim3 = this.animY;
        Intrinsics.checkNotNull((Object)dragLerpAnim3);
        dragLerpAnim3.setAnim(this.yPos);
        DragLerpAnim dragLerpAnim4 = this.animY;
        Intrinsics.checkNotNull((Object)dragLerpAnim4);
        dragLerpAnim4.setTo(this.yPos);
        DragLerpAnim dragLerpAnim5 = this.animX2;
        Intrinsics.checkNotNull((Object)dragLerpAnim5);
        dragLerpAnim5.setAnim(this.xPos);
        DragLerpAnim dragLerpAnim6 = this.animX2;
        Intrinsics.checkNotNull((Object)dragLerpAnim6);
        dragLerpAnim6.setTo(this.xPos);
        DragLerpAnim dragLerpAnim7 = this.animY2;
        Intrinsics.checkNotNull((Object)dragLerpAnim7);
        dragLerpAnim7.setAnim(this.yPos);
        DragLerpAnim dragLerpAnim8 = this.animY2;
        Intrinsics.checkNotNull((Object)dragLerpAnim8);
        dragLerpAnim8.setTo(this.yPos);
    }

    public final void snapOverlayToTarget() {
        this.ensureAnims();
        DragLerpAnim dragLerpAnim = this.animX2;
        Intrinsics.checkNotNull((Object)dragLerpAnim);
        dragLerpAnim.setAnim(this.xPos);
        DragLerpAnim dragLerpAnim2 = this.animX2;
        Intrinsics.checkNotNull((Object)dragLerpAnim2);
        dragLerpAnim2.setTo(this.xPos);
        DragLerpAnim dragLerpAnim3 = this.animY2;
        Intrinsics.checkNotNull((Object)dragLerpAnim3);
        dragLerpAnim3.setAnim(this.yPos);
        DragLerpAnim dragLerpAnim4 = this.animY2;
        Intrinsics.checkNotNull((Object)dragLerpAnim4);
        dragLerpAnim4.setTo(this.yPos);
    }

    public final void swapGrabOffset() {
        if (!this.dragging) {
            return;
        }
        float tmp = this.startX;
        this.startX = this.startY;
        this.startY = tmp;
    }

    public final boolean isDragging() {
        return this.dragging;
    }

    public final float getTiltAngle() {
        return this.tiltAngle;
    }

    public final void beginRender$rtx_kimiko_kimiko() {
        if (this.renderPositionLocked) {
            return;
        }
        this.lockedRenderX = this.computeRenderX();
        this.lockedRenderY = this.computeRenderY();
        this.renderPositionLocked = true;
    }

    public final void endRender$rtx_kimiko_kimiko() {
        this.renderPositionLocked = false;
    }

    public final void updateTilt(float mouseX, boolean enabled) {
        long now = System.nanoTime();
        if (!enabled) {
            this.tiltAngle = 0.0f;
            this.tiltVelocity = 0.0f;
            this.filteredMouseVelocity = 0.0f;
            this.lastTiltMouseX = mouseX;
            this.lastTiltUpdateNs = now;
            return;
        }
        if (this.lastTiltUpdateNs == 0L) {
            this.lastTiltMouseX = mouseX;
            this.lastTiltUpdateNs = now;
            return;
        }
        float dt = RangesKt.coerceIn((float)((float)(now - this.lastTiltUpdateNs) / 1.0E9f), (float)0.001f, (float)0.05f);
        float mouseVelocity = this.dragging ? (mouseX - this.lastTiltMouseX) / dt : 0.0f;
        this.lastTiltMouseX = mouseX;
        this.lastTiltUpdateNs = now;
        float velocityBlend = 1.0f - (float)Math.exp(-14.0f * dt);
        this.filteredMouseVelocity += (mouseVelocity - this.filteredMouseVelocity) * velocityBlend;
        float target = this.dragging ? RangesKt.coerceIn((float)(this.filteredMouseVelocity * 0.012f), (float)-6.5f, (float)6.5f) : 0.0f;
        float acceleration = (target - this.tiltAngle) * 105.0f - this.tiltVelocity * 16.0f;
        this.tiltVelocity += acceleration * dt;
        this.tiltAngle = RangesKt.coerceIn((float)(this.tiltAngle + this.tiltVelocity * dt), (float)-7.0f, (float)7.0f);
        if (!this.dragging && Math.abs(this.tiltAngle) < 0.01f && Math.abs(this.tiltVelocity) < 0.05f) {
            this.tiltAngle = 0.0f;
            this.tiltVelocity = 0.0f;
            this.filteredMouseVelocity = 0.0f;
        }
    }

    public final boolean wasCancelled() {
        return this.cancelled;
    }

    public final float overlayAlpha() {
        this.dragAnimPc.setToAsBoolean(this.dragging);
        return this.dragAnimPc.getAnim();
    }

    @Nullable
    public final DragLerpAnim getAnimX$rtx_kimiko_kimiko() {
        return this.animX;
    }

    @Nullable
    public final DragLerpAnim getAnimY$rtx_kimiko_kimiko() {
        return this.animY;
    }

    @Nullable
    public final DragLerpAnim getAnimX2$rtx_kimiko_kimiko() {
        return this.animX2;
    }

    @Nullable
    public final DragLerpAnim getAnimY2$rtx_kimiko_kimiko() {
        return this.animY2;
    }

    public final float getSnapLineX$rtx_kimiko_kimiko() {
        return this.snapLineX;
    }

    public final float getSnapLineY$rtx_kimiko_kimiko() {
        return this.snapLineY;
    }

    public final void setSnapLineX$rtx_kimiko_kimiko(float snapLineX) {
        this.snapLineX = snapLineX;
    }

    public final void setSnapLineY$rtx_kimiko_kimiko(float snapLineY) {
        this.snapLineY = snapLineY;
    }

    public final void tick(float mouseX, float mouseY, float width, float height, boolean directDrag) {
        this.tick(mouseX, mouseY, width, height, directDrag, 5.0f);
    }

    public final void tick(float mouseX, float mouseY, float width, float height, boolean directDrag, float margin) {
        this.directDrag = directDrag;
        this.dragAnimPc.setToAsBoolean(this.dragging);
        this.dragAnimPc.getAnim();
        this.dragAnimPc.setSpeed(0.04f);
        this.ensureAnims();
        this.snapLineX = Float.NaN;
        this.snapLineY = Float.NaN;
        if (this.dragging) {
            this.xPos = mouseX - this.startX;
            this.yPos = mouseY - this.startY;
            DragOverlayRenderer.INSTANCE.clampToScreen(this, width, height, margin);
            DragOverlayRenderer.INSTANCE.applyGridSnap(this, width, height);
            this.desiredX = this.xPos;
            this.desiredY = this.yPos;
            DragLerpAnim dragLerpAnim = this.animX2;
            Intrinsics.checkNotNull((Object)dragLerpAnim);
            dragLerpAnim.setTo(this.xPos);
            DragLerpAnim dragLerpAnim2 = this.animY2;
            Intrinsics.checkNotNull((Object)dragLerpAnim2);
            dragLerpAnim2.setTo(this.yPos);
            if (directDrag) {
                DragLerpAnim dragLerpAnim3 = this.animX;
                Intrinsics.checkNotNull((Object)dragLerpAnim3);
                dragLerpAnim3.setTo(this.xPos);
                DragLerpAnim dragLerpAnim4 = this.animY;
                Intrinsics.checkNotNull((Object)dragLerpAnim4);
                dragLerpAnim4.setTo(this.yPos);
            }
        } else {
            DragLerpAnim dragLerpAnim = this.animX;
            Intrinsics.checkNotNull((Object)dragLerpAnim);
            dragLerpAnim.setTo(this.xPos);
            DragLerpAnim dragLerpAnim5 = this.animY;
            Intrinsics.checkNotNull((Object)dragLerpAnim5);
            dragLerpAnim5.setTo(this.yPos);
        }
    }

    public final void renderOverlay(@NotNull DrawContext graphics, float anchorWidth, float anchorHeight, float liveWidth, float liveHeight) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        if (this.directDrag) {
            return;
        }
        float alpha = this.overlayAlpha();
        if (alpha <= 0.1f) {
            return;
        }
        DragOverlayRenderer.INSTANCE.render(graphics, this, anchorWidth, anchorHeight, liveWidth, liveHeight, alpha);
    }

    public final boolean tryGrab(float mouseX, float mouseY, float width, float height) {
        if (!this.isHovered(mouseX, mouseY, width, height)) {
            return false;
        }
        this.cancelled = false;
        this.dragging = true;
        this.preDragX = this.xPos;
        this.preDragY = this.yPos;
        this.startX = mouseX - this.xPos;
        this.startY = mouseY - this.yPos;
        this.lastTiltMouseX = mouseX;
        this.lastTiltUpdateNs = System.nanoTime();
        this.filteredMouseVelocity = 0.0f;
        return true;
    }

    public final void release() {
        block2: {
            if (!this.dragging) {
                return;
            }
            this.cancelled = false;
            this.dragging = false;
            DragLerpAnim dragLerpAnim = this.animX;
            if (dragLerpAnim != null) {
                dragLerpAnim.setTo(this.xPos);
            }
            DragLerpAnim dragLerpAnim2 = this.animY;
            if (dragLerpAnim2 == null) break block2;
            dragLerpAnim2.setTo(this.yPos);
        }
    }

    public final void cancel() {
        if (!this.dragging) {
            return;
        }
        this.cancelled = true;
        this.dragging = false;
        this.xPos = this.preDragX;
        this.yPos = this.preDragY;
        this.desiredX = this.preDragX;
        this.desiredY = this.preDragY;
        this.ensureAnims();
        DragLerpAnim dragLerpAnim = this.animX;
        Intrinsics.checkNotNull((Object)dragLerpAnim);
        dragLerpAnim.setTo(this.xPos);
        DragLerpAnim dragLerpAnim2 = this.animY;
        Intrinsics.checkNotNull((Object)dragLerpAnim2);
        dragLerpAnim2.setTo(this.yPos);
        DragLerpAnim dragLerpAnim3 = this.animX2;
        Intrinsics.checkNotNull((Object)dragLerpAnim3);
        dragLerpAnim3.setTo(this.xPos);
        DragLerpAnim dragLerpAnim4 = this.animY2;
        Intrinsics.checkNotNull((Object)dragLerpAnim4);
        dragLerpAnim4.setTo(this.yPos);
    }

    public final boolean isHovered(float mouseX, float mouseY, float width, float height) {
        return mouseX > this.xPos && mouseX < this.xPos + width && mouseY > this.yPos && mouseY < this.yPos + height;
    }

    private final void ensureAnims() {
        if (this.animX == null) {
            this.animX = new DragLerpAnim(this.xPos, this.xPos, 0.08f);
        }
        if (this.animY == null) {
            this.animY = new DragLerpAnim(this.yPos, this.yPos, 0.08f);
        }
        if (this.animX2 == null) {
            this.animX2 = new DragLerpAnim(this.xPos, this.xPos, 0.1f);
        }
        if (this.animY2 == null) {
            this.animY2 = new DragLerpAnim(this.yPos, this.yPos, 0.1f);
        }
    }

    @JvmStatic
    public static final float pulsePhase() {
        return Companion.pulsePhase();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\u0007R\u0014\u0010\n\u001a\u00020\t8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/drags/DragController.Companion;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "pulsePhase", "()F", "jitterStrength", "", "PULSE_PERIOD_MS", "J", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final float pulsePhase() {
            return (float)(System.currentTimeMillis() % 500L) / 500.0f;
        }

        private final float jitterStrength() {
            InterfaceModule module = InterfaceModule.Companion.getInstance();
            if (module != null && (module.dragStyle.is("Обычный") || !module.dragJitter.getValue())) {
                return 0.0f;
            }
            float phase = this.pulsePhase();
            float tri = phase > 0.5f ? 1.0f - phase : phase;
            float eased = tri < 0.5f ? 4.0f * tri * tri * tri : (float)(1.0 - Math.pow(-2.0 * (double)(tri *= 2.0f) + 2.0, 3.0) / 2.0);
            return eased * eased;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

