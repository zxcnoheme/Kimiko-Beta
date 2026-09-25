/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.math.MathKt
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.mainmenu;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b>\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0013\u0010\u0012J\u001b\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0014\u0010\u0012J\u001b\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0015\u0010\u0012J#\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0018\u0010\u0019J#\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001a\u0010\u0019J#\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001c\u0010\u001dJ#\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001e\u0010\u0019J#\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001f\u0010\u0019J3\u0010\"\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\"\u0010#J+\u0010'\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b'\u0010(JC\u0010.\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b.\u0010/JK\u00101\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b1\u00102J3\u00103\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b3\u00104J[\u00108\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\u0006\u00106\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b8\u00109J\u001b\u0010:\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b:\u0010\u000eJ\u001b\u0010;\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b;\u0010\u000eJ\u001b\u0010<\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b<\u0010\u000eJ3\u0010A\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\u00042\u0006\u0010?\u001a\u00020\u00042\u0006\u0010@\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bA\u0010BJ3\u0010G\u001a\u00020\u00042\u0006\u0010C\u001a\u00020\u00042\u0006\u0010D\u001a\u00020\u00102\u0006\u0010E\u001a\u00020\u00102\u0006\u0010F\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bG\u0010HJ\u001b\u0010I\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bI\u0010\u000eJ+\u0010J\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bJ\u0010KJ\u0017\u0010L\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bL\u0010\u000eJ\u0017\u0010M\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\bM\u0010NR\u001b\u0010P\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\bO\u00a2\u0006\u0006\n\u0004\bP\u0010QR\u001b\u0010R\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\bO\u00a2\u0006\u0006\n\u0004\bR\u0010QR\u001b\u0010S\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\bO\u00a2\u0006\u0006\n\u0004\bS\u0010QR\u001b\u0010T\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\bO\u00a2\u0006\u0006\n\u0004\bT\u0010QR\u001b\u0010U\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\bO\u00a2\u0006\u0006\n\u0004\bU\u0010QR\u001b\u0010V\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\bO\u00a2\u0006\u0006\n\u0004\bV\u0010QR\u001b\u0010W\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\bO\u00a2\u0006\u0006\n\u0004\bW\u0010QR\u0016\u0010X\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010QR\u0014\u0010Y\u001a\u00020\u00108\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bY\u0010ZR\u0014\u0010[\u001a\u00020\u00108\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b[\u0010ZR\u0014\u0010\\\u001a\u00020\u00108\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\\\u0010ZR\u0014\u0010]\u001a\u00020\u00108\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b]\u0010Z\u00a8\u0006^"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuTheme;", "", "<init>", "()V", "", "designHeight", "", "Lkotlin/jvm/JvmStatic;", "updateMetrics", "(F)V", "metrics", "()F", "value", "scaled", "(F)F", "alpha", "", "ink", "(F)I", "inkSoft", "inkMuted", "inkFaint", "alpha255", "multiplier", "white", "(FF)I", "black", "color", "fade", "(IF)I", "accent", "accentBright", "screenX", "screenY", "accentAt", "(FFFF)I", "from", "to", "t", "mix", "(IIF)I", "x", "y", "w", "h", "radius", "panel", "(FFFFFF)V", "lift", "card", "(FFFFFFF)V", "hairline", "(FFFF)V", "mouseX", "mouseY", "strength", "spotlight", "(FFFFFFFFF)V", "ease", "easeInOut", "backOut", "current", "target", "dt", "speed", "approach", "(FFFF)F", "progress", "index", "count", "overlap", "stagger", "(FIIF)F", "clamp01", "lerp", "(FFF)F", "clampAlpha", "clamp255", "(I)I", "Lkotlin/jvm/JvmField;", "RAIL_W", "F", "TOP_H", "FOOT_H", "PAD", "GAP", "CARD_RADIUS", "PANEL_RADIUS", "metricsScale", "INK", "I", "INK_SOFT", "INK_MUTED", "INK_FAINT", "rtx.kimiko:kimiko"})
public final class MenuTheme {
    @NotNull
    public static final MenuTheme INSTANCE = new MenuTheme();
    @JvmField
    public static float RAIL_W = 54.0f;
    @JvmField
    public static float TOP_H = 34.0f;
    @JvmField
    public static float FOOT_H = 21.0f;
    @JvmField
    public static float PAD = 14.0f;
    @JvmField
    public static float GAP = 9.0f;
    @JvmField
    public static float CARD_RADIUS = 9.0f;
    @JvmField
    public static float PANEL_RADIUS = 12.0f;
    private static float metricsScale = 1.0f;
    public static final int INK = -1052681;
    public static final int INK_SOFT = -4934458;
    public static final int INK_MUTED = -8750450;
    public static final int INK_FAINT = -11382177;

    private MenuTheme() {
    }

    @JvmStatic
    public static final void updateMetrics(float designHeight) {
        float scale = designHeight / 540.0f;
        metricsScale = scale < 0.68f ? 0.68f : (scale > 1.18f ? 1.18f : scale);
        RAIL_W = 54.0f * metricsScale;
        TOP_H = 34.0f * metricsScale;
        FOOT_H = 21.0f * metricsScale;
        PAD = 14.0f * metricsScale;
        GAP = 9.0f * metricsScale;
        CARD_RADIUS = 9.0f * metricsScale;
        PANEL_RADIUS = 12.0f * metricsScale;
    }

    @JvmStatic
    public static final float metrics() {
        return metricsScale;
    }

    @JvmStatic
    public static final float scaled(float value) {
        return value * metricsScale;
    }

    @JvmStatic
    public static final int ink(float alpha) {
        return MenuTheme.fade(-1052681, alpha);
    }

    @JvmStatic
    public static final int inkSoft(float alpha) {
        return MenuTheme.fade(-4934458, alpha);
    }

    @JvmStatic
    public static final int inkMuted(float alpha) {
        return MenuTheme.fade(-8750450, alpha);
    }

    @JvmStatic
    public static final int inkFaint(float alpha) {
        return MenuTheme.fade(-11382177, alpha);
    }

    @JvmStatic
    public static final int white(float alpha255, float multiplier) {
        int a = INSTANCE.clamp255(MathKt.roundToInt((float)(alpha255 * multiplier)));
        return a << 24 | 0xFFFFFF;
    }

    @JvmStatic
    public static final int black(float alpha255, float multiplier) {
        int a = INSTANCE.clamp255(MathKt.roundToInt((float)(alpha255 * multiplier)));
        return a << 24;
    }

    @JvmStatic
    public static final int fade(int color, float multiplier) {
        int base = color >>> 24 & 0xFF;
        int a = INSTANCE.clamp255(MathKt.roundToInt((float)((float)base * multiplier)));
        return a << 24 | color & 0xFFFFFF;
    }

    @JvmStatic
    public static final int accent(float alpha255, float multiplier) {
        return ClientAccent.accent(INSTANCE.clampAlpha(alpha255 * multiplier));
    }

    @JvmStatic
    public static final int accentBright(float alpha255, float multiplier) {
        return ClientAccent.accentBright(INSTANCE.clampAlpha(alpha255 * multiplier));
    }

    @JvmStatic
    public static final int accentAt(float alpha255, float multiplier, float screenX, float screenY) {
        return ClientAccent.accentAt(INSTANCE.clampAlpha(alpha255 * multiplier), screenX, screenY);
    }

    @JvmStatic
    public static final int mix(int from, int to, float t) {
        return ColorEngine.lerpColor(from, to, Math.max(0.0f, Math.min(1.0f, t)));
    }

    @JvmStatic
    public static final void panel(float x, float y, float w, float h, float radius, float alpha) {
        if (w <= 0.0f || h <= 0.0f || alpha <= 0.004f) {
            return;
        }
        RectUtil.drawGlassCard(x, y, w, h, radius, radius, radius, radius, alpha * 0.92f, 0.1f);
        Render2D.rect(x, y, w, h, radius, MenuTheme.black(58.0f, alpha));
        Render2D.outline(x, y, w, h, radius, 0.7f, MenuTheme.white(26.0f, alpha));
    }

    @JvmStatic
    public static final void card(float x, float y, float w, float h, float radius, float alpha, float lift) {
        if (w <= 0.0f || h <= 0.0f || alpha <= 0.004f) {
            return;
        }
        RectUtil.drawGlassCard(x, y, w, h, radius, radius, radius, radius, alpha, lift);
        Render2D.rect(x, y, w, h, radius, MenuTheme.black(46.0f - 14.0f * lift, alpha));
        Render2D.outline(x, y, w, h, radius, 0.7f, MenuTheme.white(24.0f + 46.0f * lift, alpha));
    }

    @JvmStatic
    public static final void hairline(float x, float y, float w, float alpha) {
        Render2D.rect(x, y, w, 0.6f, 0.0f, MenuTheme.white(22.0f, alpha));
    }

    @JvmStatic
    public static final void spotlight(float x, float y, float w, float h, float radius, float mouseX, float mouseY, float strength, float alpha) {
        if (strength <= 0.004f) {
            return;
        }
        float cx = Math.max(x, Math.min(x + w, mouseX));
        float cy = Math.max(y, Math.min(y + h, mouseY));
        float size = Math.max(w, h) * 0.62f;
        Render2D.circle(cx, cy, size * 0.5f, size * 0.5f, MenuTheme.white(20.0f * strength, alpha));
    }

    @JvmStatic
    public static final float ease(float t) {
        float v = MenuTheme.clamp01(t);
        return 1.0f - (1.0f - v) * (1.0f - v) * (1.0f - v);
    }

    @JvmStatic
    public static final float easeInOut(float t) {
        float v = MenuTheme.clamp01(t);
        return v * v * (3.0f - 2.0f * v);
    }

    @JvmStatic
    public static final float backOut(float t) {
        float v = MenuTheme.clamp01(t) - 1.0f;
        return 1.0f + 2.70158f * v * v * v + 1.70158f * v * v;
    }

    @JvmStatic
    public static final float approach(float current, float target, float dt, float speed) {
        float k = 1.0f - (float)Math.exp(-dt * speed);
        float next = current + (target - current) * k;
        return Math.abs(target - next) < 8.0E-4f ? target : next;
    }

    @JvmStatic
    public static final float stagger(float progress, int index, int count, float overlap) {
        if (count <= 1) {
            return MenuTheme.clamp01(progress);
        }
        float span = 1.0f / (1.0f + (float)(count - 1) * overlap);
        float start = (float)index * overlap * span;
        return MenuTheme.clamp01((MenuTheme.clamp01(progress) - start) / span);
    }

    @JvmStatic
    public static final float clamp01(float value) {
        return value < 0.0f ? 0.0f : (value > 1.0f ? 1.0f : value);
    }

    @JvmStatic
    public static final float lerp(float from, float to, float t) {
        return from + (to - from) * t;
    }

    private final float clampAlpha(float alpha) {
        return Math.max(0.0f, Math.min(255.0f, alpha));
    }

    private final int clamp255(int value) {
        return value < 0 ? 0 : (value > 255 ? 255 : value);
    }
}

