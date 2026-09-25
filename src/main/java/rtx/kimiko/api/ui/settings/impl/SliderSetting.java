/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.settings.impl;

import java.awt.Color;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0010\t\n\u0002\b\u0004\b\u0016\u0018\u0000 +2\u00020\u0001:\u0001+B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u000eJ/\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016J7\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u0019\u0010\u001aJ7\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u0014H\u0016\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u001f\u0010 R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010!R\u0016\u0010\"\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0018\u0010$\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010&\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010)\u001a\u00020(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010*\u00a8\u0006,"}, d2={"Lrtx/kimiko/api/ui/settings/impl/SliderSetting;", "Lrtx/kimiko/api/ui/settings/Setting;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "backend", "<init>", "(Lrtx/kimiko/api/modules/settings/impl/SliderSetting;)V", "", "name", "()Ljava/lang/String;", "", "isVisible", "()Z", "", "height", "()F", "preferredWidth", "x", "y", "w", "alpha", "", "render", "(FFFF)V", "mx", "my", "click", "(FFFFF)Z", "middleClick", "releaseDrag", "()V", "v", "formatValue", "(F)Ljava/lang/String;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "dragging", "Z", "lastSoundText", "Ljava/lang/String;", "visProgress", "F", "", "lastNs", "J", "Companion", "rtx.kimiko:kimiko"})
public class SliderSetting
implements Setting {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final rtx.kimiko.api.modules.settings.impl.SliderSetting backend;
    private boolean dragging;
    @Nullable
    private String lastSoundText;
    private float visProgress;
    private long lastNs;
    public static final float ROW_H = 22.0f;
    public static final float BAR_H = 3.0f;
    public static final float BAR_TOP = 16.0f;

    public SliderSetting(@NotNull rtx.kimiko.api.modules.settings.impl.SliderSetting backend) {
        Intrinsics.checkNotNullParameter((Object)backend, (String)"backend");
        this.backend = backend;
        this.visProgress = -1.0f;
        this.lastNs = System.nanoTime();
    }

    @Override
    @NotNull
    public String name() {
        return this.backend.getName();
    }

    @Override
    public boolean isVisible() {
        return this.backend.isVisible();
    }

    @Override
    public float height() {
        return 22.0f;
    }

    @Override
    public float preferredWidth() {
        float nameW = Fonts.MEDIUM.width(this.backend.getDisplayName(), 6.5f);
        float valW = Fonts.MEDIUM.width(this.formatValue(this.backend.getMax()), 6.0f);
        return Math.max(100.0f, 6.0f + nameW + 6.0f + valW + 6.0f);
    }

    @Override
    public void render(float x, float y, float w, float alpha) {
        long now = System.nanoTime();
        float dt = Math.min(0.1f, (float)(now - this.lastNs) / 1.0E9f);
        this.lastNs = now;
        float barX = x + 6.0f;
        float barW = w - 12.0f;
        float barY = y + 16.0f;
        if (this.dragging) {
            float mx = Position.Companion.mouseX();
            float pct = SliderSetting.Companion.clamp01((mx - barX) / barW);
            this.backend.setValue(this.backend.getMin() + pct * (this.backend.getMax() - this.backend.getMin()));
            String t = this.formatValue(this.backend.getValue());
            if (!Intrinsics.areEqual((Object)t, (Object)this.lastSoundText)) {
                this.lastSoundText = t;
                Sounds.play("slider");
            }
        }
        float valSize = 6.0f;
        String valText = this.formatValue(this.backend.getValue());
        float tw = Fonts.MEDIUM.width(valText, valSize);
        float valX = x + w - 6.0f - tw;
        Fonts.MEDIUM.draw(valText, valX, y + 4.2f + 0.25f, valSize, SliderSetting.Companion.rgba(255, 255, 255, (float)238 * alpha));
        RenderHelper.drawName(this.backend.getDisplayName(), x, y, valX - (x + 6.0f) - 6.0f, alpha);
        float target = this.backend.getProgress();
        if (this.visProgress < 0.0f) {
            this.visProgress = target;
        }
        this.visProgress += (target - this.visProgress) * (1.0f - (float)Math.exp(-dt * 18.0f));
        if (Math.abs(target - this.visProgress) < 0.0015f) {
            this.visProgress = target;
        }
        float sliderPos = barX + barW * SliderSetting.Companion.clamp01(this.visProgress);
        float trackR = 1.5f;
        Render2D.rect(barX, barY, barW, 3.0f, trackR, SliderSetting.Companion.rgba(16, 16, 16, (float)64 * alpha));
        if (sliderPos - barX > 0.6f) {
            float fillCx = (barX + sliderPos) * 0.5f;
            float fillCy = barY + 1.5f;
            int fa = ClientAccent.gradientAAt((float)215 * alpha, fillCx, fillCy);
            int fb = ClientAccent.gradientBAt((float)215 * alpha, fillCx, fillCy);
            Render2D.rect(barX, barY, sliderPos - barX, 3.0f, trackR, fa, fb, fb, fa);
            Render2D.outline(barX, barY, sliderPos - barX, 3.0f, trackR, 0.5f, ClientAccent.accentBrightAt((float)150 * alpha, fillCx, fillCy));
        }
        float thW = 6.5f;
        float thH = 4.0f;
        float thR = 2.0f;
        float thX = Math.max(barX, Math.min(barX + barW - thW, sliderPos - thW * 0.5f));
        float thY = barY + 1.5f - thH * 0.5f;
        Render2D.rect(thX, thY, thW, thH, thR, SliderSetting.Companion.rgba(255, 255, 255, (float)245 * alpha));
        Render2D.outline(thX - 0.5f, thY - 0.5f, thW + 1.0f, thH + 1.0f, thR, 0.5f, SliderSetting.Companion.rgba(16, 16, 16, (float)128 * alpha));
    }

    @Override
    public boolean click(float x, float y, float w, float mx, float my) {
        float barX = x + 6.0f;
        float barW = w - 12.0f;
        float barY = y + 16.0f;
        if (mx >= barX - (float)4 && mx <= barX + barW + (float)4 && my >= barY - (float)5 && my <= barY + 3.0f + (float)5) {
            this.dragging = true;
            float pct = SliderSetting.Companion.clamp01((mx - barX) / barW);
            this.backend.setValue(this.backend.getMin() + pct * (this.backend.getMax() - this.backend.getMin()));
            this.lastSoundText = this.formatValue(this.backend.getValue());
            return true;
        }
        return false;
    }

    @Override
    public boolean middleClick(float x, float y, float w, float mx, float my) {
        float barX = x + 6.0f;
        float barW = w - 12.0f;
        float barY = y + 16.0f;
        if (mx >= barX - (float)4 && mx <= barX + barW + (float)4 && my >= barY - (float)5 && my <= barY + 3.0f + (float)5) {
            this.dragging = false;
            this.backend.setValue(this.backend.getDefaultValue());
            Sounds.play("slider");
            return true;
        }
        return false;
    }

    @Override
    public void releaseDrag() {
        this.dragging = false;
    }

    private final String formatValue(float v) {
        if (this.backend.isInteger()) {
            String string = "%.0f";
            Object[] objectArray = new Object[]{Float.valueOf(v)};
            String string2 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
            return string2;
        }
        float inc = this.backend.getIncrement();
        int decimals = 1;
        if (inc > 0.0f) {
            float step = inc;
            for (decimals = 0; decimals < 3 && Math.abs(step - (float)Math.round(step)) > 1.0E-4f; ++decimals) {
                step *= 10.0f;
            }
            decimals = Math.max(decimals, 1);
        }
        String string = "%." + decimals + "f";
        Object[] objectArray = new Object[]{Float.valueOf(v)};
        String string3 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"format(...)");
        return string3;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J/\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0010\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/api/ui/settings/impl/SliderSetting.Companion;", "", "<init>", "()V", "", "r", "g", "b", "", "a", "rgba", "(IIIF)I", "v", "clamp01", "(F)F", "ROW_H", "F", "BAR_H", "BAR_TOP", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final int rgba(int r, int g, int b, float a) {
            int alpha = Math.max(0, Math.min(255, Math.round(a)));
            if (alpha <= 0) {
                return 0;
            }
            return new Color(r, g, b, alpha).getRGB();
        }

        private final float clamp01(float v) {
            return v < 0.0f ? 0.0f : (v > 1.0f ? 1.0f : v);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

