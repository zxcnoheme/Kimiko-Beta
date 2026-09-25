/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.settings;

import java.awt.Color;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\"\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J+\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000b\u0010\fJ#\u0010\u000f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000f\u0010\u0010J;\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u0018\u0010\u0019JC\u0010!\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u001fH\u0007b\u0002\b\n\u00a2\u0006\u0004\b!\u0010\"J\u0017\u0010$\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b$\u0010%JC\u0010*\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0004H\u0007b\u0002\b\n\u00a2\u0006\u0004\b*\u0010+JK\u0010*\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u0004H\u0007b\u0002\b\n\u00a2\u0006\u0004\b*\u0010-JC\u0010/\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0007b\u0002\b\n\u00a2\u0006\u0004\b/\u00100J[\u0010/\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0007b\u0002\b\n\u00a2\u0006\u0004\b/\u00105J;\u0010:\u001a\u00020\u00172\u0006\u00106\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u00042\u0006\u00108\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0007b\u0002\b\n\u00a2\u0006\u0004\b:\u0010;J#\u0010>\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u0004H\u0007b\u0002\b\n\u00a2\u0006\u0004\b>\u0010\u0010R\u0014\u0010?\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b?\u0010@\u00a8\u0006A"}, d2={"Lrtx/kimiko/api/ui/settings/RenderHelper;", "", "<init>", "()V", "", "radiusScale", "()F", "baseRadius", "w", "h", "Lkotlin/jvm/JvmStatic;", "effectiveCornerRadius", "(FFF)F", "effR", "edgeDist", "cornerEdgeInset", "(FF)F", "", "name", "x", "y", "availW", "alpha", "", "drawName", "(Ljava/lang/String;FFFF)V", "text", "textX", "textY", "maxW", "size", "", "color", "drawScrollingText", "(Ljava/lang/String;FFFFI)V", "t", "easeInOutBack", "(F)F", "bx", "by", "bw", "bh", "drawBtn", "(FFFFLjava/lang/String;F)V", "textOffsetX", "(FFFFLjava/lang/String;FF)V", "radius", "drawPanelBg", "(FFFFFF)V", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "(FFFFFFFFF)V", "dx", "dy", "dw", "dh", "drawDropBackground", "(FFFFF)V", "rowY", "overlayH", "overlayY", "REFERENCE_RADIUS", "F", "rtx.kimiko:kimiko"})
public final class RenderHelper {
    @NotNull
    public static final RenderHelper INSTANCE = new RenderHelper();
    private static final float REFERENCE_RADIUS = 7.0f;

    private RenderHelper() {
    }

    private final float radiusScale() {
        InterfaceModule interfaceModule = InterfaceModule.Companion.getInstance();
        if (interfaceModule == null) {
            return 1.0f;
        }
        InterfaceModule module = interfaceModule;
        return module.rectCornerRadius.getFloat() / 7.0f;
    }

    @JvmStatic
    public static final float effectiveCornerRadius(float baseRadius, float w, float h) {
        float maxR = Math.min(w, h) * 0.5f;
        return Math.min(maxR, baseRadius * INSTANCE.radiusScale());
    }

    @JvmStatic
    public static final float cornerEdgeInset(float effR, float edgeDist) {
        if (effR <= edgeDist) {
            return 0.0f;
        }
        float k = effR - edgeDist;
        return effR - (float)Math.sqrt(Math.max(0.0f, effR * effR - k * k));
    }

    @JvmStatic
    public static final void drawName(@NotNull String name, float x, float y, float availW, float alpha) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        int color = new Color(255, 255, 255, (int)((float)200 * alpha)).getRGB();
        RenderHelper.drawScrollingText(name, x + 6.0f, y + 4.2f, availW, 6.5f, color);
    }

    @JvmStatic
    public static final void drawScrollingText(@NotNull String text, float textX, float textY, float maxW, float size, int color) {
        Intrinsics.checkNotNullParameter((Object)text, (String)"text");
        float tw = Fonts.MEDIUM.width(text, size);
        float over = tw - maxW;
        if (over <= 1.0f) {
            Fonts.MEDIUM.draw(text, textX, textY, size, color);
            return;
        }
        float speed = 11.0f;
        float pause = 1.2f;
        float travel = Math.max(0.35f, over / speed);
        double cycle = (double)(pause + travel) * 2.0;
        double t = (double)System.nanoTime() / 1.0E9 % cycle;
        float off = t < (double)pause ? 0.0f : (t < (double)(pause + travel) ? over * INSTANCE.easeInOutBack((float)((t - (double)pause) / (double)travel)) : (t < (double)pause * 2.0 + (double)travel ? over : over * (1.0f - INSTANCE.easeInOutBack((float)((t - (double)pause * 2.0 - (double)travel) / (double)travel)))));
        float leftStrength = Math.max(0.0f, Math.min(1.0f, off / 4.0f));
        float rightStrength = Math.max(0.0f, Math.min(1.0f, (over - off) / 4.0f));
        Fonts.MEDIUM.msdfFade(text, textX - off, textY, size, color, textX, textX + maxW, 5.0f, leftStrength, rightStrength);
    }

    private final float easeInOutBack(float t) {
        float c1 = 1.70158f;
        float c2 = c1 * 1.525f;
        if (t < 0.5f) {
            float p = 2.0f * t;
            return p * p * ((c2 + 1.0f) * p - c2) * 0.5f;
        }
        float p = 2.0f * t - 2.0f;
        return (p * p * ((c2 + 1.0f) * p + c2) + 2.0f) * 0.5f;
    }

    @JvmStatic
    public static final void drawBtn(float bx, float by, float bw, float bh, @NotNull String text, float alpha) {
        Intrinsics.checkNotNullParameter((Object)text, (String)"text");
        RenderHelper.drawBtn(bx, by, bw, bh, text, alpha, 0.0f);
    }

    @JvmStatic
    public static final void drawBtn(float bx, float by, float bw, float bh, @NotNull String text, float alpha, float textOffsetX) {
        Intrinsics.checkNotNullParameter((Object)text, (String)"text");
        RenderHelper.drawPanelBg(bx, by, bw, bh, 3.0f, alpha);
        float tw = Fonts.MEDIUM.width(text, 6.0f);
        Fonts.MEDIUM.draw(text, bx + (bw - tw) * 0.5f + textOffsetX, by + (bh - 7.0f) * 0.5f, 6.0f, ClientAccent.accentSoftAt((float)220 * alpha, bx + bw * 0.5f, by + bh * 0.5f));
    }

    @JvmStatic
    public static final void drawPanelBg(float x, float y, float w, float h, float radius, float alpha) {
        RenderHelper.drawPanelBg(x, y, w, h, radius, radius, radius, radius, alpha);
    }

    @JvmStatic
    public static final void drawPanelBg(float x, float y, float w, float h, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, float alpha) {
        int a = Math.max(0, Math.min(255, Math.round((float)40 * alpha)));
        if (a <= 0) {
            return;
        }
        float scale = INSTANCE.radiusScale();
        float maxR = Math.min(w, h) * 0.5f;
        float rtl = Math.min(maxR, radiusTopLeft * scale);
        float rtr = Math.min(maxR, radiusTopRight * scale);
        float rbr = Math.min(maxR, radiusBottomRight * scale);
        float rbl = Math.min(maxR, radiusBottomLeft * scale);
        Render2D.rect(x, y, w, h, rtl, rtr, rbr, rbl, new Color(0, 0, 0, a).getRGB());
    }

    @JvmStatic
    public static final void drawDropBackground(float dx, float dy, float dw, float dh, float alpha) {
        RectUtil.drawClientWindow(dx, dy, dw, dh, 2.0f, alpha);
    }

    @JvmStatic
    public static final float overlayY(float rowY, float overlayH) {
        float margin = 5.0f;
        float below = rowY + 16.0f + 1.0f;
        float screenH = Position.Companion.screenHeight();
        if (below + overlayH <= screenH - margin) {
            return below;
        }
        float above = rowY - overlayH - 1.0f;
        if (above >= margin) {
            return above;
        }
        return Math.max(margin, screenH - margin - overlayH);
    }
}

