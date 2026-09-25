/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  kotlin.ranges.RangesKt
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.settings.impl;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.animations.UiverseSwitchAnimation;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.core.frame.EngineFrame;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RoundedScissor;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 #2\u00020\u0001:\u0001#B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\r\u0010\u0010\u001a\u00020\t\u00a2\u0006\u0004\b\u0010\u0010\u000bJ\u0015\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\t\u00a2\u0006\u0004\b\u0013\u0010\u0014J/\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u0019\u0010\u001aJ7\u0010\u001d\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"\u00a8\u0006$"}, d2={"Lrtx/kimiko/api/ui/settings/impl/BoolSetting;", "Lrtx/kimiko/api/ui/settings/Setting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "backend", "<init>", "(Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;)V", "", "name", "()Ljava/lang/String;", "", "isVisible", "()Z", "", "height", "()F", "preferredWidth", "getValue", "v", "", "setValue", "(Z)V", "x", "y", "w", "alpha", "render", "(FFFF)V", "mx", "my", "click", "(FFFFF)Z", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lrtx/kimiko/utils/animations/UiverseSwitchAnimation;", "anim", "Lrtx/kimiko/utils/animations/UiverseSwitchAnimation;", "Companion", "rtx.kimiko:kimiko"})
public class BoolSetting
implements Setting {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final BooleanSetting backend;
    @NotNull
    private final UiverseSwitchAnimation anim;
    public static final float PW = 19.5f;
    public static final float PH = 10.4f;
    public static final float PR = 4.8f;
    public static final float PAD = 4.0f;
    private static final float CSS_SWITCH_HEIGHT_PX = 34.0f;
    private static final float CSS_TRANSLATE_X_PX = 41.6f;

    public BoolSetting(@NotNull BooleanSetting backend) {
        Intrinsics.checkNotNullParameter((Object)backend, (String)"backend");
        this.backend = backend;
        this.anim = new UiverseSwitchAnimation(this.backend.getValue());
        this.anim.reset(this.backend.getValue());
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
        return 16.0f;
    }

    @Override
    public float preferredWidth() {
        return 6.0f + Fonts.MEDIUM.width(this.backend.getDisplayName(), 6.5f) + 6.0f + 19.5f + 4.0f;
    }

    public final boolean getValue() {
        return this.backend.getValue();
    }

    public final void setValue(boolean v) {
        this.backend.setValue(v);
    }

    @Override
    public void render(float x, float y, float w, float alpha) {
        float px = x + w - 19.5f - 4.0f;
        float py = y + 2.8000002f;
        RenderHelper.drawName(this.backend.getDisplayName(), x, y, px - (x + 6.0f) - 4.0f, alpha);
        float t = this.anim.update(this.backend.getValue());
        BoolSetting.Companion.drawToggleAnimated(px, py, 19.5f, 10.4f, t, alpha, this.anim.thumbProgress(), this.anim.horizontalScale(), this.anim.isOpening());
    }

    @Override
    public boolean click(float x, float y, float w, float mx, float my) {
        float px = x + w - 19.5f - 4.0f;
        float py = y + 2.8000002f;
        if (mx >= px && mx <= px + 19.5f && my >= py && my <= py + 10.4f) {
            this.backend.toggle();
            return true;
        }
        return false;
    }

    @JvmStatic
    public static final void drawToggle(float px, float py, float t, float alpha) {
        Companion.drawToggle(px, py, t, alpha);
    }

    @JvmStatic
    public static final void drawToggle(float px, float py, float w, float h, @NotNull UiverseSwitchAnimation animation, boolean target, float alpha) {
        Companion.drawToggle(px, py, w, h, animation, target, alpha);
    }

    @JvmStatic
    public static final void drawToggle(float px, float py, float w, float h, float t, float alpha) {
        Companion.drawToggle(px, py, w, h, t, alpha);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J3\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000b\u0010\fJK\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000b\u0010\u0013JC\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000b\u0010\u0014JW\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ/\u0010 \u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b$\u0010#R\u0014\u0010%\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b%\u0010#R\u0014\u0010&\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b&\u0010#R\u0014\u0010'\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010#R\u0014\u0010(\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010#\u00a8\u0006)"}, d2={"Lrtx/kimiko/api/ui/settings/impl/BoolSetting.Companion;", "", "<init>", "()V", "", "px", "py", "t", "alpha", "", "Lkotlin/jvm/JvmStatic;", "drawToggle", "(FFFF)V", "w", "h", "Lrtx/kimiko/utils/animations/UiverseSwitchAnimation;", "animation", "", "target", "(FFFFLrtx/kimiko/utils/animations/UiverseSwitchAnimation;ZF)V", "(FFFFFF)V", "trackT", "travelT", "scaleX", "opening", "drawToggleAnimated", "(FFFFFFFFZ)V", "", "r", "g", "b", "a", "rgba", "(IIIF)I", "PW", "F", "PH", "PR", "PAD", "CSS_SWITCH_HEIGHT_PX", "CSS_TRANSLATE_X_PX", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final void drawToggle(float px, float py, float t, float alpha) {
            this.drawToggle(px, py, 19.5f, 10.4f, t, alpha);
        }

        @JvmStatic
        public final void drawToggle(float px, float py, float w, float h, @NotNull UiverseSwitchAnimation animation, boolean target, float alpha) {
            Intrinsics.checkNotNullParameter((Object)animation, (String)"animation");
            float trackT = animation.update(target);
            this.drawToggleAnimated(px, py, w, h, trackT, alpha, animation.thumbProgress(), animation.horizontalScale(), animation.isOpening());
        }

        @JvmStatic
        public final void drawToggle(float px, float py, float w, float h, float t, float alpha) {
            float safeT = RangesKt.coerceIn((float)t, (float)0.0f, (float)1.0f);
            this.drawToggleAnimated(px, py, w, h, safeT, alpha, safeT, 1.0f, true);
        }

        private final void drawToggleAnimated(float px, float py, float w, float h, float trackT, float alpha, float travelT, float scaleX, boolean opening) {
            if (w <= 0.0f || h <= 0.0f || alpha <= 0.0f) {
                return;
            }
            float safeColorT = RangesKt.coerceIn((float)trackT, (float)0.0f, (float)1.0f);
            float safeTranslationT = RangesKt.coerceIn((float)travelT, (float)0.0f, (float)1.0f);
            float safeScale = Math.max(1.0f, Math.min(3.6f, scaleX));
            float radius = Math.min(w, h) * 0.5f;
            float trackAlpha = 255.0f * alpha;
            int offTrack = this.rgba(16, 16, 16, 64.0f * alpha);
            int onLeft = ClientAccent.gradientAAt(trackAlpha, px, py + h * 0.5f);
            int onRight = ClientAccent.gradientBAt(trackAlpha, px + w, py + h * 0.5f);
            Render2D.rect(px, py, w, h, radius, ColorEngine.lerpColor(offTrack, onLeft, safeColorT), ColorEngine.lerpColor(offTrack, onRight, safeColorT), ColorEngine.lerpColor(offTrack, onRight, safeColorT), ColorEngine.lerpColor(offTrack, onLeft, safeColorT));
            float cssScale = h / 34.0f;
            float cssTranslate = 41.6f * cssScale;
            float translateDistance = Math.min(cssTranslate, Math.max(0.0f, w - h));
            int whiteBase = this.rgba(255, 255, 255, 255.0f * alpha);
            int whiteThumb = ColorEngine.lerpColor(whiteBase, this.rgba(176, 180, 188, 255.0f * alpha), 0.15f);
            int clientTint = ClientAccent.accentBrightAt(255.0f * alpha, px + w * 0.5f, py + h * 0.5f);
            int onThumb = ColorEngine.lerpColor(whiteThumb, clientTint, 0.15f);
            int grayThumb = this.rgba(138, 142, 150, 255.0f * alpha);
            int offThumb = ColorEngine.lerpColor(whiteThumb, grayThumb, 0.35f);
            int thumbColor = ColorEngine.lerpColor(offThumb, onThumb, safeColorT);
            DrawContext graphics = EngineFrame.getActiveGraphics();
            if (graphics != null) {
                RoundedScissor.pushNested(graphics, px, py, w, h, h * 0.5f, h * 0.5f, h * 0.5f, h * 0.5f);
                graphics.getMatrices().pushMatrix();
                float originX = px + h;
                graphics.getMatrices().translate(originX, py + h * 0.5f);
                graphics.getMatrices().scale(safeScale, 1.0f);
                graphics.getMatrices().translate(-originX, -(py + h * 0.5f));
                graphics.getMatrices().translate(translateDistance * safeTranslationT, 0.0f);
                Render2D.rect(px, py, h, h, h * 0.5f, thumbColor);
                graphics.getMatrices().popMatrix();
                RoundedScissor.popNested();
            } else {
                Render2D.rect(px, py, h, h, h * 0.5f, thumbColor);
            }
        }

        private final int rgba(int r, int g, int b, float a) {
            int alpha = Math.max(0, Math.min(255, MathKt.roundToInt((float)a)));
            if (alpha <= 0) {
                return 0;
            }
            return ColorEngine.rgba(r, g, b, alpha);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

