/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.math.MathKt
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui;

import java.awt.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.ui.theme.AccentGradient;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u000b\u0018\u0000 +2\u00020\u0001:\u0001+B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\u0006JE\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b\u00a2\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0017\u001a\u00020\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0003J\u0017\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001d\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001eR\u0016\u0010 \u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010\u001eR\u0016\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010$\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010\u001cR\u0016\u0010%\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010\u001eR\u0016\u0010&\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010\u001eR\u0016\u0010'\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u0010\u001eR\u0016\u0010(\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010\u001eR\u0016\u0010)\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010\u001eR\u0016\u0010*\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010\u001e\u00a8\u0006,"}, d2={"Lrtx/kimiko/api/ui/ScrollBar;", "", "<init>", "()V", "", "isDragging", "()Z", "isVisible", "", "trackX", "trackY", "trackH", "viewH", "contentH", "scrollValue", "alpha", "render", "(FFFFFFF)F", "mx", "my", "tryGrab", "(FF)Z", "", "release", "dt", "follow", "(F)F", "dragging", "Z", "grabOffset", "F", "hoverT", "dragT", "", "lastNs", "J", "visible", "gTrackX", "gTrackY", "gTrackH", "gThumbY", "gThumbH", "gHitW", "Companion", "rtx.kimiko:kimiko"})
public final class ScrollBar {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private boolean dragging;
    private float grabOffset;
    private float hoverT;
    private float dragT;
    private long lastNs = System.nanoTime();
    private boolean visible;
    private float gTrackX;
    private float gTrackY;
    private float gTrackH;
    private float gThumbY;
    private float gThumbH;
    private float gHitW;
    private static final float BAR_W = 1.5f;
    private static final float GRAB_PAD_X = 4.0f;
    private static final float GRAB_PAD_Y = 2.0f;
    private static final float ANIM_RATE = 16.0f;
    private static final float VERTICAL_SHRINK = 1.0f;

    public final boolean isDragging() {
        return this.dragging;
    }

    public final boolean isVisible() {
        return this.visible;
    }

    public final float render(float trackX, float trackY, float trackH, float viewH, float contentH, float scrollValue, float alpha) {
        float scroll = scrollValue;
        long now = System.nanoTime();
        float dt = Math.min(0.1f, (float)(now - this.lastNs) / 1.0E9f);
        this.lastNs = now;
        float maxScroll = Math.max(0.0f, contentH - viewH);
        boolean bl = this.visible = maxScroll > 0.5f;
        if (!this.visible) {
            this.dragging = false;
            this.hoverT += (0.0f - this.hoverT) * this.follow(dt);
            this.dragT += (0.0f - this.dragT) * this.follow(dt);
            return scroll;
        }
        float drawTrackH = trackH / 1.0f;
        float drawTrackY = trackY + (trackH - drawTrackH) * 0.5f;
        float thumbH = Math.max(12.0f, drawTrackH * (viewH / contentH));
        float travel = Math.max(1.0f, drawTrackH - thumbH);
        if (this.dragging) {
            float top = ScrollBar.Companion.clamp(Position.Companion.mouseY() - this.grabOffset, drawTrackY, drawTrackY + travel);
            scroll = (top - drawTrackY) / travel * maxScroll;
        }
        scroll = ScrollBar.Companion.clamp(scroll, 0.0f, maxScroll);
        float thumbY = drawTrackY + travel * (scroll / maxScroll);
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        boolean hover = mx >= trackX - 4.0f && mx <= trackX + 1.5f + 4.0f && my >= thumbY - 2.0f && my <= thumbY + thumbH + 2.0f;
        this.hoverT += ((hover ? 1.0f : 0.0f) - this.hoverT) * this.follow(dt);
        this.dragT += ((this.dragging ? 1.0f : 0.0f) - this.dragT) * this.follow(dt);
        float active = Math.max(this.hoverT, this.dragT);
        int trackAlpha = ScrollBar.Companion.clamp255((20.0f + 18.0f * active) * alpha);
        Render2D.rect(trackX, drawTrackY, 1.5f, drawTrackH, 0.75f, new Color(255, 255, 255, trackAlpha).getRGB());
        float thumbAlpha = (160.0f + 95.0f * active) * alpha;
        AccentGradient.fillVertical(trackX, thumbY, 1.5f, thumbH, 0.75f, thumbAlpha);
        this.gTrackX = trackX;
        this.gTrackY = drawTrackY;
        this.gTrackH = drawTrackH;
        this.gThumbY = thumbY;
        this.gThumbH = thumbH;
        this.gHitW = 1.5f;
        return scroll;
    }

    public final boolean tryGrab(float mx, float my) {
        if (!this.visible) {
            return false;
        }
        if (mx < this.gTrackX - 4.0f || mx > this.gTrackX + this.gHitW + 4.0f) {
            return false;
        }
        if (my >= this.gThumbY - 2.0f && my <= this.gThumbY + this.gThumbH + 2.0f) {
            this.dragging = true;
            this.grabOffset = my - this.gThumbY;
            return true;
        }
        if (my >= this.gTrackY && my <= this.gTrackY + this.gTrackH) {
            this.dragging = true;
            this.grabOffset = this.gThumbH * 0.5f;
            return true;
        }
        return false;
    }

    public final void release() {
        this.dragging = false;
    }

    private final float follow(float dt) {
        return 1.0f - (float)Math.exp(-dt * 16.0f);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u000eR\u0014\u0010\u0012\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u000e\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/api/ui/ScrollBar.Companion;", "", "<init>", "()V", "", "value", "min", "max", "clamp", "(FFF)F", "", "clamp255", "(F)I", "BAR_W", "F", "GRAB_PAD_X", "GRAB_PAD_Y", "ANIM_RATE", "VERTICAL_SHRINK", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float clamp(float value, float min, float max) {
            return Math.max(min, Math.min(max, value));
        }

        private final int clamp255(float value) {
            return Math.max(0, Math.min(255, MathKt.roundToInt((float)value)));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

