/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.util.Window
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.util.InputUtil
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.drags;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.util.Window;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.InputUtil;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.drags.DragController;
import rtx.kimiko.api.drags.DragLerpAnim;
import rtx.kimiko.api.drags.DragSystem;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0013\b\u00c0\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003JE\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b\u00a2\u0006\u0004\b\u000f\u0010\u0010J7\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J%\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b\u00a2\u0006\u0004\b\u0017\u0010\u0018J/\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010!\u001a\u00020 H\u0002\u00a2\u0006\u0004\b!\u0010\"J%\u0010#\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b\u00a2\u0006\u0004\b#\u0010\u0018J-\u0010#\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010$\u001a\u00020\b\u00a2\u0006\u0004\b#\u0010%Jg\u0010.\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010*\u001a\u00020\b2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b.\u0010/J?\u00104\u001a\u00020\u000e2\u0006\u00100\u001a\u00020\b2\u0006\u00101\u001a\u00020\b2\u0006\u00102\u001a\u00020\b2\u0006\u00103\u001a\u00020\b2\u0006\u0010-\u001a\u00020\b2\u0006\u0010,\u001a\u00020+H\u0002\u00a2\u0006\u0004\b4\u00105J7\u00106\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b6\u0010\u0016R\u0014\u0010:\u001a\u0002078BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b8\u00109R\u0014\u0010;\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u0014\u0010=\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b=\u0010<R\u0014\u0010>\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u0010<R\u0014\u0010?\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b?\u0010<R\u0014\u0010@\u001a\u00020+8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u0010AR\u0014\u0010B\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bB\u0010<R\u0014\u0010C\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u0010<R\u0014\u0010D\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u0010<R\u0014\u0010E\u001a\u00020+8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bE\u0010AR\u0014\u0010F\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bF\u0010<R\u0014\u0010G\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u0010<R\u0014\u0010H\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bH\u0010<R\u0014\u0010I\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bI\u0010<\u00a8\u0006J"}, d2={"Lrtx/kimiko/api/drags/DragOverlayRenderer;", "", "<init>", "()V", "Lnet/minecraft/DrawContext;", "graphics", "Lrtx/kimiko/api/drags/DragController;", "drag", "", "anchorWidth", "anchorHeight", "liveWidth", "liveHeight", "overlayAlpha", "", "render", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/api/drags/DragController;FFFFF)V", "boxX", "boxY", "width", "height", "drawCancelHint", "(FFFFF)V", "applyGridSnap", "(Lrtx/kimiko/api/drags/DragController;FF)V", "pos", "candidate", "guide", "", "best", "trySnap", "(FFF[F)V", "", "isAltHeld", "()Z", "clampToScreen", "margin", "(Lrtx/kimiko/api/drags/DragController;FFF)V", "x", "y", "xA", "yA", "offset", "", "color", "thickness", "drawCornerStretch", "(FFFFFFFFFIF)V", "x1", "y1", "x2", "y2", "dash", "(FFFFFI)V", "drawLandingRipple", "", "getCANCEL_TEXT", "()Ljava/lang/String;", "CANCEL_TEXT", "CANCEL_TEXT_SIZE", "F", "SNAP_STRENGTH", "DASH_ON", "DASH_OFF", "MAX_DASHES", "I", "LINE_THICKNESS", "DASH_FADE_NEAR", "SNAP_RADIUS", "RING_COUNT", "RIPPLE_PERIOD_MS", "RING_GROW", "RING_BASE_THICKNESS", "RING_RADIUS", "rtx.kimiko:kimiko"})
public final class DragOverlayRenderer {
    @NotNull
    public static final DragOverlayRenderer INSTANCE = new DragOverlayRenderer();
    private static final float CANCEL_TEXT_SIZE = 6.0f;
    private static final float SNAP_STRENGTH = 20.0f;
    private static final float DASH_ON = 3.9f;
    private static final float DASH_OFF = 3.25f;
    private static final int MAX_DASHES = 256;
    private static final float LINE_THICKNESS = 1.5f;
    private static final float DASH_FADE_NEAR = 0.15f;
    private static final float SNAP_RADIUS = 6.0f;
    private static final int RING_COUNT = 3;
    private static final float RIPPLE_PERIOD_MS = 1000.0f;
    private static final float RING_GROW = 2.5f;
    private static final float RING_BASE_THICKNESS = 1.2f;
    private static final float RING_RADIUS = 3.0f;

    private DragOverlayRenderer() {
    }

    private final String getCANCEL_TEXT() {
        return I18n.tr("Нажмите ПКМ для отмены действия.");
    }

    public final void render(@NotNull DrawContext graphics, @NotNull DragController drag, float anchorWidth, float anchorHeight, float liveWidth, float liveHeight, float overlayAlpha) {
        float snapLineY;
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        Intrinsics.checkNotNullParameter((Object)drag, (String)"drag");
        if (overlayAlpha <= 0.1f || drag.getAnimX$rtx_kimiko_kimiko() == null) {
            return;
        }
        float cornerOffset = -1.5f;
        DragLerpAnim dragLerpAnim = drag.getAnimX$rtx_kimiko_kimiko();
        Intrinsics.checkNotNull((Object)dragLerpAnim);
        float xA = dragLerpAnim.getTo();
        DragLerpAnim dragLerpAnim2 = drag.getAnimY$rtx_kimiko_kimiko();
        Intrinsics.checkNotNull((Object)dragLerpAnim2);
        float yA = dragLerpAnim2.getTo();
        DragLerpAnim dragLerpAnim3 = drag.getAnimX2$rtx_kimiko_kimiko();
        Intrinsics.checkNotNull((Object)dragLerpAnim3);
        float x = dragLerpAnim3.getAnim();
        DragLerpAnim dragLerpAnim4 = drag.getAnimY2$rtx_kimiko_kimiko();
        Intrinsics.checkNotNull((Object)dragLerpAnim4);
        float y = dragLerpAnim4.getAnim();
        int dashColor = ColorEngine.multAlpha(-1, overlayAlpha);
        this.drawCornerStretch(x, y, liveWidth, liveHeight, xA, yA, anchorWidth, anchorHeight, cornerOffset, dashColor, 1.5f);
        this.drawLandingRipple(x, y, liveWidth, liveHeight, overlayAlpha);
        this.drawCancelHint(x, y, liveWidth, liveHeight, overlayAlpha);
        float snapLineX = drag.getSnapLineX$rtx_kimiko_kimiko();
        if (!Float.isNaN(snapLineX)) {
            Render2D.rect(snapLineX - 0.5f, 0.0f, 1.0f, Position.Companion.screenHeight(), 0.0f, ColorEngine.multAlpha(-1, overlayAlpha));
        }
        if (!Float.isNaN(snapLineY = drag.getSnapLineY$rtx_kimiko_kimiko())) {
            Render2D.rect(0.0f, snapLineY - 0.5f, Position.Companion.screenWidth(), 1.0f, 0.0f, ColorEngine.multAlpha(-1, overlayAlpha));
        }
    }

    private final void drawCancelHint(float boxX, float boxY, float width, float height, float overlayAlpha) {
        float textW = Fonts.MEDIUM.width(this.getCANCEL_TEXT(), 6.0f);
        float padX = 8.0f;
        float chipW = textW + padX * 2.0f;
        float chipH = 14.0f;
        float chipX = boxX + (width - chipW) * 0.5f;
        float chipY = boxY + height + 12.0f;
        if (chipY + chipH > Position.Companion.screenHeight() - 2.0f) {
            chipY = boxY - chipH - 12.0f;
        }
        chipX = Position.Companion.clampX(chipX, chipW);
        RectUtil.drawClientRect(chipX, chipY, chipW, chipH, Math.min(6.0f, chipH * 0.5f), overlayAlpha);
        float textX = chipX + (chipW - textW) * 0.5f;
        float textY = chipY + (chipH - 6.0f) * 0.5f - 0.5f;
        Fonts.MEDIUM.draw(this.getCANCEL_TEXT(), textX, textY, 6.0f, ColorEngine.multAlpha(-1, overlayAlpha));
    }

    public final void applyGridSnap(@NotNull DragController drag, float width, float height) {
        Intrinsics.checkNotNullParameter((Object)drag, (String)"drag");
        drag.setSnapLineX$rtx_kimiko_kimiko(Float.NaN);
        drag.setSnapLineY$rtx_kimiko_kimiko(Float.NaN);
        if (this.isAltHeld()) {
            return;
        }
        float sw = Position.Companion.screenWidth();
        float sh = Position.Companion.screenHeight();
        float px = drag.getTargetX();
        float py = drag.getTargetY();
        float[] bx = new float[]{px, 7.0f, Float.NaN};
        float[] by = new float[]{py, 7.0f, Float.NaN};
        for (Draggable d : DragSystem.Companion.get().getAll()) {
            if (d.getDrag() == drag || !d.isVisible() || !d.isInteractive()) continue;
            float nx = d.getDrag().getTargetX();
            float ny = d.getDrag().getTargetY();
            float nw = d.width();
            float nh = d.height();
            this.trySnap(px, nx, nx, bx);
            this.trySnap(px, nx + nw - width, nx + nw, bx);
            this.trySnap(px, nx + nw * 0.5f - width * 0.5f, nx + nw * 0.5f, bx);
            this.trySnap(py, ny, ny, by);
            this.trySnap(py, ny + nh - height, ny + nh, by);
            this.trySnap(py, ny + nh * 0.5f - height * 0.5f, ny + nh * 0.5f, by);
        }
        this.trySnap(px, (sw - width) * 0.5f, sw * 0.5f, bx);
        this.trySnap(py, (sh - height) * 0.5f, sh * 0.5f, by);
        drag.setTargetX(bx[0]);
        drag.setTargetY(by[0]);
        drag.setSnapLineX$rtx_kimiko_kimiko(bx[2]);
        drag.setSnapLineY$rtx_kimiko_kimiko(by[2]);
    }

    private final void trySnap(float pos, float candidate, float guide, float[] best) {
        float dist = Math.abs(candidate - pos);
        if (dist <= 6.0f && dist < best[1]) {
            best[0] = candidate;
            best[1] = dist;
            best[2] = guide;
        }
    }

    private final boolean isAltHeld() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.getWindow() == null) {
            return false;
        }
        return InputUtil.isKeyPressed((Window)mc.getWindow(), (int)342) || InputUtil.isKeyPressed((Window)mc.getWindow(), (int)346);
    }

    public final void clampToScreen(@NotNull DragController drag, float width, float height) {
        Intrinsics.checkNotNullParameter((Object)drag, (String)"drag");
        this.clampToScreen(drag, width, height, 5.0f);
    }

    public final void clampToScreen(@NotNull DragController drag, float width, float height, float margin) {
        Intrinsics.checkNotNullParameter((Object)drag, (String)"drag");
        drag.setTargetX(Position.Companion.clampX(drag.getTargetX(), width, margin));
        drag.setTargetY(Position.Companion.clampY(drag.getTargetY(), height, margin));
    }

    private final void drawCornerStretch(float x, float y, float liveWidth, float liveHeight, float xA, float yA, float anchorWidth, float anchorHeight, float offset, int color, float thickness) {
        if (ColorEngine.alpha(color) <= 0) {
            return;
        }
        this.dash(x + offset, y + offset, xA, yA, thickness, color);
        this.dash(x + liveWidth - offset, y + offset, xA + anchorWidth, yA, thickness, color);
        this.dash(x + liveWidth - offset, y + liveHeight - offset, xA + anchorWidth, yA + anchorHeight, thickness, color);
        this.dash(x + offset, y + liveHeight - offset, xA, yA + anchorHeight, thickness, color);
    }

    private final void dash(float x1, float y1, float x2, float y2, float thickness, int color) {
        Render2D.dashedLine(x1, y1, x2, y2, thickness, color, 3.9f, 3.25f, 256, 0.15f, 0.15f);
    }

    private final void drawLandingRipple(float xA, float yA, float width, float height, float overlayAlpha) {
        InterfaceModule module;
        float phase = (float)(System.currentTimeMillis() % 1000L) / 1000.0f;
        InterfaceModule interfaceModule = module = InterfaceModule.Companion.getInstance();
        float ringRadius = interfaceModule == null ? 3.0f : interfaceModule.rectCornerRadius.getFloat();
        for (int i = 0; i < 3; ++i) {
            float grow = 2.5f * (float)i;
            float left = xA - grow;
            float top = yA - grow;
            float w = width + grow * 2.0f;
            float h = height + grow * 2.0f;
            float thickness = 1.2f;
            float ringPhase = phase - (float)i / 3.0f;
            float wave = 0.5f + 0.5f * (float)Math.sin(ringPhase * ((float)Math.PI * 2));
            int color = ColorEngine.multAlpha(-1, overlayAlpha * wave);
            if (ColorEngine.alpha(color) <= 0) continue;
            Render2D.outline(left, top, w, h, ringRadius + grow, thickness, color);
        }
    }
}

