/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.drags;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.others.RoundedScissor;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u001e\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJi\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0017H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001a\u0010\u001bJa\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001c\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010 \u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b \u0010!J\u0017\u0010\"\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\"\u0010!J7\u0010#\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b#\u0010$JY\u0010)\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b)\u0010*JI\u0010/\u001a\u00020\u00172\u0006\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b/\u00100R\u0014\u00101\u001a\u00020\u00068\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b1\u00102R\u0014\u00103\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b3\u00102R\u0014\u00104\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u00102R\u0014\u00105\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u00102R\u0014\u00106\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u00102\u00a8\u00067"}, d2={"Lrtx/kimiko/api/drags/HudSettingsPanel;", "", "<init>", "()V", "", "count", "", "Lkotlin/jvm/JvmStatic;", "height", "(I)F", "Lnet/minecraft/DrawContext;", "graphics", "x", "y", "w", "h", "radius", "", "title", "", "Lrtx/kimiko/api/ui/settings/Setting;", "settings", "alpha", "", "drawBackground", "", "render", "(Lnet/minecraft/DrawContext;FFFFFLjava/lang/String;Ljava/util/List;FZ)V", "t", "renderReveal", "(Lnet/minecraft/DrawContext;FFFFFLjava/lang/String;Ljava/util/List;F)V", "v", "clamp01", "(F)F", "easeOut", "drawHeader", "(FFFLjava/lang/String;F)V", "childX", "childY", "childW", "childH", "renderGrowing", "(Lnet/minecraft/DrawContext;FFFFFLjava/lang/String;Ljava/util/List;)V", "mx", "my", "panelX", "panelY", "click", "(FFFFFLjava/util/List;)Z", "WIDTH", "F", "MARGIN", "HEADER_H", "ROW_STEP", "HEADER_SIZE", "rtx.kimiko:kimiko"})
public final class HudSettingsPanel {
    @NotNull
    public static final HudSettingsPanel INSTANCE = new HudSettingsPanel();
    public static final float WIDTH = 132.0f;
    private static final float MARGIN = 3.0f;
    private static final float HEADER_H = 16.0f;
    private static final float ROW_STEP = 20.0f;
    private static final float HEADER_SIZE = 6.0f;

    private HudSettingsPanel() {
    }

    @JvmStatic
    public static final float height(int count) {
        return 16.0f + (float)count * 20.0f + 3.0f;
    }

    @JvmStatic
    public static final void render(@NotNull DrawContext graphics, float x, float y, float w, float h, float radius, @NotNull String title, @NotNull List<? extends Setting> settings, float alpha, boolean drawBackground) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        Intrinsics.checkNotNullParameter((Object)title, (String)"title");
        Intrinsics.checkNotNullParameter(settings, (String)"settings");
        Render2D.beginFrame(graphics);
        if (drawBackground) {
            RectUtil.drawClientRect(x, y, w, h, radius, alpha);
        }
        INSTANCE.drawHeader(x, y, w, title, alpha);
        float rowY = y + 16.0f;
        for (Setting setting : settings) {
            setting.render(x, rowY, w, alpha);
            rowY += 20.0f;
        }
        Render2D.flush();
    }

    @JvmStatic
    public static final void renderReveal(@NotNull DrawContext graphics, float x, float y, float w, float h, float radius, @NotNull String title, @NotNull List<? extends Setting> settings, float t) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        Intrinsics.checkNotNullParameter((Object)title, (String)"title");
        Intrinsics.checkNotNullParameter(settings, (String)"settings");
        float ct = INSTANCE.clamp01(t);
        Render2D.beginFrame(graphics);
        RectUtil.drawClientRect(x, y, w, h, radius, INSTANCE.easeOut(INSTANCE.clamp01(ct / 0.35f)));
        float headerRaw = INSTANCE.clamp01((ct - 0.06f) / 0.3f);
        if (headerRaw > 0.0f) {
            float he = INSTANCE.easeOut(headerRaw);
            INSTANCE.drawHeader(x, y + (1.0f - he) * 6.0f, w, title, he);
        }
        int n = settings.size();
        float rowY = y + 16.0f;
        for (int i = 0; i < n; ++i) {
            float start = 0.15f + (n <= 1 ? 0.0f : (float)i / (float)n * 0.55f);
            float rowRaw = INSTANCE.clamp01((ct - start) / 0.3f);
            if (rowRaw > 0.0f) {
                float re = INSTANCE.easeOut(rowRaw);
                settings.get(i).render(x, rowY + (1.0f - re) * 7.0f, w, re);
            }
            rowY += 20.0f;
        }
        Render2D.flush();
    }

    private final float clamp01(float v) {
        return v < 0.0f ? 0.0f : (v > 1.0f ? 1.0f : v);
    }

    private final float easeOut(float v) {
        float u = 1.0f - v;
        return 1.0f - u * u * u;
    }

    private final void drawHeader(float x, float y, float w, String title, float alpha) {
        float maxW;
        String header = title + " Settings";
        float titleW = Fonts.MEDIUM.width(header, 6.0f);
        if (titleW > (maxW = Math.max(8.0f, w - 24.0f))) {
            titleW = maxW;
        }
        float centerX = x + w * 0.5f;
        float textX = centerX - titleW * 0.5f;
        float textY = y + 5.0f;
        float lineY = y + 8.0f;
        float gap = 6.0f;
        float edge = 6.0f;
        int textColor = ClientAccent.accentSoftAt(220.0f * alpha, centerX, textY);
        int lineColor = ClientAccent.accentSoftAt(55.0f * alpha, centerX, lineY);
        float leftX = x + edge;
        float leftW = Math.max(0.0f, textX - gap - leftX);
        float rightX = textX + titleW + gap;
        float rightW = Math.max(0.0f, x + w - edge - rightX);
        if (leftW > 1.0f) {
            Render2D.rect(leftX, lineY, leftW, 1.0f, 0.5f, lineColor);
        }
        if (rightW > 1.0f) {
            Render2D.rect(rightX, lineY, rightW, 1.0f, 0.5f, lineColor);
        }
        Fonts.MEDIUM.draw(header, textX, textY, 6.0f, textColor);
    }

    @JvmStatic
    public static final void renderGrowing(@NotNull DrawContext graphics, float childX, float childY, float childW, float childH, float radius, @NotNull String title, @NotNull List<? extends Setting> settings) {
        float t;
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        Intrinsics.checkNotNullParameter((Object)title, (String)"title");
        Intrinsics.checkNotNullParameter(settings, (String)"settings");
        float fullW = 132.0f;
        float fullH = HudSettingsPanel.height(settings.size());
        float f = t = fullW <= 0.0f ? 1.0f : Math.max(0.0f, Math.min(1.0f, childW / fullW));
        if (t <= 0.02f) {
            return;
        }
        float cx = childX + childW * 0.5f;
        float cy = childY + childH * 0.5f;
        RoundedScissor.push(graphics, childX, childY, childW, childH, radius, radius, radius, radius);
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(cx, cy);
        graphics.getMatrices().scale(t);
        graphics.getMatrices().translate(-cx, -cy);
        HudSettingsPanel.render(graphics, cx - fullW * 0.5f, cy - fullH * 0.5f, fullW, fullH, 0.0f, title, settings, 1.0f, false);
        graphics.getMatrices().popMatrix();
        RoundedScissor.pop();
    }

    @JvmStatic
    public static final boolean click(float mx, float my, float panelX, float panelY, float w, @NotNull List<? extends Setting> settings) {
        Intrinsics.checkNotNullParameter(settings, (String)"settings");
        float rowY = panelY + 16.0f;
        for (Setting setting : settings) {
            if (my >= rowY && my < rowY + 16.0f) {
                return setting.click(panelX, rowY, w, mx, my);
            }
            rowY += 20.0f;
        }
        return false;
    }
}

