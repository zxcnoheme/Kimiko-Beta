/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Lazy
 *  kotlin.LazyKt
 *  kotlin.LazyThreadSafetyMode
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.ranges.RangesKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.drags.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u0000 42\u00020\u0001:\u000245B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0005\u00a2\u0006\u0004\b\t\u0010\nJ=\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0013J/\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJI\u0010!\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0002\u00a2\u0006\u0004\b!\u0010\"R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010#R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010$R\u0014\u0010%\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010#R\u001b\u0010*\u001a\u00020\u00178BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R!\u00100\u001a\b\u0012\u0004\u0012\u00020,0+8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b-\u0010'\u001a\u0004\b.\u0010/R\u001b\u00103\u001a\u00020\u00178BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b1\u0010'\u001a\u0004\b2\u0010)\u00a8\u00066"}, d2={"Lrtx/kimiko/api/drags/components/HudHeaderRenderer;", "", "", "title", "glyph", "", "titleSize", "<init>", "(Ljava/lang/String;Ljava/lang/String;F)V", "minimumWidth", "()F", "x", "y", "width", "headerHeight", "alpha", "iconProgress", "", "render", "(FFFFFF)V", "titleX", "titleY", "opacity", "", "bounds", "drawTitleGradient", "(FFF[F)V", "panelX", "panelY", "panelWidth", "titleRight", "Lrtx/kimiko/api/modules/impl/Interface/InterfaceModule;", "module", "drawPattern", "(FFFFFFLrtx/kimiko/api/modules/impl/Interface/InterfaceModule;)V", "Ljava/lang/String;", "F", "iconGlyph", "titleBounds$delegate", "Lkotlin/Lazy;", "getTitleBounds", "()[F", "titleBounds", "", "Lrtx/kimiko/api/drags/components/HudHeaderRenderer$TitleGlyph;", "titleGlyphs$delegate", "getTitleGlyphs", "()[Lrtx/kimiko/api/drags/components/HudHeaderRenderer$TitleGlyph;", "titleGlyphs", "iconBounds$delegate", "getIconBounds", "iconBounds", "Companion", "TitleGlyph", "rtx.kimiko:kimiko"})
public final class HudHeaderRenderer {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String title;
    private final float titleSize;
    @NotNull
    private final String iconGlyph;
    @NotNull
    private final Lazy titleBounds$delegate;
    @NotNull
    private final Lazy titleGlyphs$delegate;
    @NotNull
    private final Lazy iconBounds$delegate;
    private static final float HEADER_INSET_X = 5.0f;
    private static final float PANEL_HEIGHT = 14.0f;
    private static final float PANEL_RADIUS = 2.5f;
    private static final float PANEL_ALPHA_LEFT = 0.1f;
    private static final float PANEL_ALPHA_RIGHT = 0.15f;
    private static final float STRIPE_INSET_X = 0.6f;
    private static final float STRIPE_SHIFT_X = 1.0f;
    private static final float STRIPE_WIDTH = 1.25f;
    private static final float STRIPE_HEIGHT = 10.0f;
    private static final float STRIPE_RADIUS = 0.625f;
    private static final float STRIPE_ALPHA = 0.92f;
    private static final float TITLE_GAP = 4.0f;
    private static final float TITLE_ACCENT_MIX = 0.68f;
    private static final float PATTERN_GAP = 4.0f;
    private static final float PATTERN_WIDTH = 34.0f;
    private static final float PATTERN_RIGHT_INSET = 3.0f;
    private static final float MIN_PATTERN_SPAN = 8.0f;
    private static final float ICON_METRIC_SIZE = 1.0f;
    private static final float ICON_CLIENT_MIX = 0.15f;
    @NotNull
    private static final float[] ICON_X;
    @NotNull
    private static final float[] ICON_Y;
    @NotNull
    private static final float[] ICON_SIZE;
    @NotNull
    private static final float[] ICON_ROTATION;
    @NotNull
    private static final float[] ICON_ALPHA;
    private static final float MIN_HEADER_WIDTH = 72.0f;
    private static final float MIN_ALPHA = 0.003921569f;
    private static final int WHITE = -1;
    private static final int FALLBACK_ACCENT = -4748033;

    public HudHeaderRenderer(@NotNull String title, @Nullable String glyph, float titleSize) {
        Intrinsics.checkNotNullParameter((Object)title, (String)"title");
        this.title = title;
        this.titleSize = titleSize;
        String string = glyph;
        if (string == null) {
            string = "";
        }
        this.iconGlyph = string;
        this.titleBounds$delegate = LazyKt.lazy((LazyThreadSafetyMode)LazyThreadSafetyMode.NONE, () -> HudHeaderRenderer.titleBounds_delegate$lambda$0(this));
        this.titleGlyphs$delegate = LazyKt.lazy((LazyThreadSafetyMode)LazyThreadSafetyMode.NONE, () -> HudHeaderRenderer.titleGlyphs_delegate$lambda$0(this));
        this.iconBounds$delegate = LazyKt.lazy((LazyThreadSafetyMode)LazyThreadSafetyMode.NONE, () -> HudHeaderRenderer.iconBounds_delegate$lambda$0(this));
    }

    private final float[] getTitleBounds() {
        Lazy lazy = this.titleBounds$delegate;
        return (float[])lazy.getValue();
    }

    private final TitleGlyph[] getTitleGlyphs() {
        Lazy lazy = this.titleGlyphs$delegate;
        return (TitleGlyph[])lazy.getValue();
    }

    private final float[] getIconBounds() {
        Lazy lazy = this.iconBounds$delegate;
        return (float[])lazy.getValue();
    }

    public final float minimumWidth() {
        float titleWidth = this.getTitleBounds()[2] - this.getTitleBounds()[0];
        float patternWidth = ((CharSequence)this.iconGlyph).length() == 0 ? 0.0f : 34.0f;
        float contentWidth = 5.25f + titleWidth + (patternWidth > 0.0f ? 4.0f + patternWidth : 0.0f);
        return Math.max(72.0f, 10.0f + contentWidth + 3.0f);
    }

    public final void render(float x, float y, float width, float headerHeight, float alpha, float iconProgress) {
        int stripeTop;
        InterfaceModule module;
        float opacity = HudHeaderRenderer.Companion.clamp01(alpha);
        if (opacity <= 0.003921569f) {
            return;
        }
        float panelX = x + 5.0f;
        float panelY = y + (headerHeight - 14.0f) * 0.5f;
        float panelWidth = width - 10.0f;
        if (panelWidth <= 5.25f) {
            return;
        }
        float centerY = panelY + 7.0f;
        InterfaceModule interfaceModule = module = InterfaceModule.Companion.getInstance();
        int leftAccent = interfaceModule != null ? interfaceModule.clientPrimaryColorOpaqueAt(panelX, centerY) : -4748033;
        InterfaceModule interfaceModule2 = module;
        int rightAccent = interfaceModule2 != null ? interfaceModule2.clientSecondaryColorOpaqueAt(panelX + panelWidth, centerY) : -4748033;
        int panelLeft = ColorEngine.multAlpha(leftAccent, opacity * 0.1f);
        int panelRight = ColorEngine.multAlpha(rightAccent, opacity * 0.15f);
        Render2D.rect(panelX, panelY, panelWidth, 14.0f, 2.5f, panelLeft, panelRight, panelRight, panelLeft);
        float[] bounds = this.getTitleBounds();
        float titleX = panelX + 1.25f + 4.0f - bounds[0];
        float titleY = centerY - (bounds[1] + bounds[3]) * 0.5f;
        float titleRight = titleX + bounds[2];
        this.drawPattern(panelX, panelY, panelWidth, titleRight, opacity, iconProgress, module);
        int stripeAccent = module == null ? leftAccent : ClientAccent.gradientColor(0.0f, 255.0f);
        int stripeColor = ColorEngine.lerpColor(-1, stripeAccent, 0.68f);
        int stripeBottom = stripeTop = ColorEngine.multAlpha(stripeColor, opacity * 0.92f);
        Render2D.rect(panelX + 0.6f + 1.0f, panelY + 2.0f, 1.25f, 10.0f, 0.625f, stripeTop, stripeTop, stripeBottom, stripeBottom);
        if (module == null) {
            int titleColor = ColorEngine.lerpColor(-1, leftAccent, 0.68f);
            Fonts.SEMIBOLD.msdf(this.title, titleX, titleY, this.titleSize, ColorEngine.multAlpha(titleColor, opacity));
        } else {
            this.drawTitleGradient(titleX, titleY, opacity, bounds);
        }
    }

    private final void drawTitleGradient(float titleX, float titleY, float opacity, float[] bounds) {
        float titleWidth = bounds[2] - bounds[0];
        if (this.getTitleGlyphs().length == 0 || titleWidth <= 0.0f) {
            int titleAccent = ClientAccent.gradientColor(0.0f, 255.0f);
            int titleColor = ColorEngine.lerpColor(-1, titleAccent, 0.68f);
            Fonts.SEMIBOLD.msdf(this.title, titleX, titleY, this.titleSize, ColorEngine.multAlpha(titleColor, opacity));
            return;
        }
        float visualStart = bounds[0];
        for (TitleGlyph glyph : this.getTitleGlyphs()) {
            float leftT = RangesKt.coerceIn((float)((glyph.getLeft() - visualStart) / titleWidth), (float)0.0f, (float)1.0f);
            float rightT = RangesKt.coerceIn((float)((glyph.getRight() - visualStart) / titleWidth), (float)0.0f, (float)1.0f);
            int leftAccent = ClientAccent.gradientColor(leftT, 255.0f);
            int rightAccent = ClientAccent.gradientColor(rightT, 255.0f);
            int colorLeft = ColorEngine.multAlpha(ColorEngine.lerpColor(-1, leftAccent, 0.68f), opacity);
            int colorRight = ColorEngine.multAlpha(ColorEngine.lerpColor(-1, rightAccent, 0.68f), opacity);
            Fonts.SEMIBOLD.msdf(glyph.getText(), titleX + glyph.getOrigin(), titleY, this.titleSize, colorLeft, colorRight, colorRight, colorLeft);
        }
    }

    private final void drawPattern(float panelX, float panelY, float panelWidth, float titleRight, float alpha, float iconProgress, InterfaceModule module) {
        float appear = alpha * HudHeaderRenderer.Companion.clamp01(iconProgress);
        if (((CharSequence)this.iconGlyph).length() == 0 || appear <= 0.003921569f) {
            return;
        }
        float patternRight = panelX + panelWidth - 3.0f;
        float patternLeft = Math.max(titleRight + 4.0f, panelX + panelWidth - 34.0f);
        float patternSpan = patternRight - patternLeft;
        if (patternSpan <= 8.0f) {
            return;
        }
        float[] bounds = this.getIconBounds();
        float boundsCenterX = (bounds[0] + bounds[2]) * 0.5f;
        float boundsCenterY = (bounds[1] + bounds[3]) * 0.5f;
        int n = ICON_X.length;
        for (int index = 0; index < n; ++index) {
            float centerX = patternLeft + patternSpan * ICON_X[index];
            float centerY = panelY + 14.0f * ICON_Y[index];
            float size = ICON_SIZE[index];
            float drawX = centerX - boundsCenterX * size;
            float drawY = centerY - boundsCenterY * size;
            InterfaceModule interfaceModule = module;
            int clientColor = interfaceModule != null ? interfaceModule.clientPrimaryColorOpaqueAt(centerX, centerY) : -4748033;
            int tintedWhite = ColorEngine.lerpColor(-1, clientColor, 0.15f);
            int color = ColorEngine.multAlpha(tintedWhite, appear * ICON_ALPHA[index]);
            Fonts.KIMIKO.msdf(this.iconGlyph, drawX, drawY, size, color, ICON_ROTATION[index], centerX, centerY);
        }
    }

    private static final float[] titleBounds_delegate$lambda$0(HudHeaderRenderer this$0) {
        return HudHeaderRenderer.Companion.textBounds(Fonts.SEMIBOLD, this$0.title, this$0.titleSize);
    }

    private static final TitleGlyph[] titleGlyphs_delegate$lambda$0(HudHeaderRenderer this$0) {
        return HudHeaderRenderer.Companion.buildTitleGlyphs(Fonts.SEMIBOLD, this$0.title, this$0.titleSize);
    }

    private static final float[] iconBounds_delegate$lambda$0(HudHeaderRenderer this$0) {
        return HudHeaderRenderer.Companion.textBounds(Fonts.KIMIKO, this$0.iconGlyph, 1.0f);
    }

    static {
        float[] fArray = new float[]{0.06f, 0.27f, 0.48f, 0.69f, 0.89f};
        ICON_X = fArray;
        fArray = new float[]{0.28f, 0.72f, 0.2f, 0.67f, 0.34f};
        ICON_Y = fArray;
        fArray = new float[]{3.9f, 4.6f, 4.2f, 5.0f, 5.4f};
        ICON_SIZE = fArray;
        fArray = new float[]{-15.0f, 11.0f, -9.0f, 14.0f, -12.0f};
        ICON_ROTATION = fArray;
        fArray = new float[]{0.08f, 0.14f, 0.21f, 0.3f, 0.4f};
        ICON_ALPHA = fArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J'\u0010\u000e\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ-\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0018R\u0014\u0010\u001b\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0018R\u0014\u0010\u001c\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0018R\u0014\u0010\u001d\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u0018R\u0014\u0010\u001e\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u0018R\u0014\u0010\u001f\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u0018R\u0014\u0010 \u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u0018R\u0014\u0010!\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010\u0018R\u0014\u0010\"\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010\u0018R\u0014\u0010#\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010\u0018R\u0014\u0010$\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010\u0018R\u0014\u0010%\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010\u0018R\u0014\u0010&\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010\u0018R\u0014\u0010'\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010\u0018R\u0014\u0010(\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010\u0018R\u0014\u0010)\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010\u0018R\u0014\u0010*\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010\u0018R\u0014\u0010+\u001a\u00020\r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020\r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010,R\u0014\u0010.\u001a\u00020\r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b.\u0010,R\u0014\u0010/\u001a\u00020\r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u0010,R\u0014\u00100\u001a\u00020\r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u0010,R\u0014\u00101\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u0010\u0018R\u0014\u00102\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u0010\u0018R\u0014\u00104\u001a\u0002038\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0014\u00106\u001a\u0002038\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u00105\u00a8\u00067"}, d2={"Lrtx/kimiko/api/drags/components/HudHeaderRenderer.Companion;", "", "<init>", "()V", "", "isNewMode", "()Z", "Lrtx/kimiko/utils/render/fonts/Fonts;", "font", "", "text", "", "size", "", "textBounds", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;F)[F", "", "Lrtx/kimiko/api/drags/components/HudHeaderRenderer$TitleGlyph;", "buildTitleGlyphs", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;F)[Lrtx/kimiko/api/drags/components/HudHeaderRenderer$TitleGlyph;", "value", "clamp01", "(F)F", "HEADER_INSET_X", "F", "PANEL_HEIGHT", "PANEL_RADIUS", "PANEL_ALPHA_LEFT", "PANEL_ALPHA_RIGHT", "STRIPE_INSET_X", "STRIPE_SHIFT_X", "STRIPE_WIDTH", "STRIPE_HEIGHT", "STRIPE_RADIUS", "STRIPE_ALPHA", "TITLE_GAP", "TITLE_ACCENT_MIX", "PATTERN_GAP", "PATTERN_WIDTH", "PATTERN_RIGHT_INSET", "MIN_PATTERN_SPAN", "ICON_METRIC_SIZE", "ICON_CLIENT_MIX", "ICON_X", "[F", "ICON_Y", "ICON_SIZE", "ICON_ROTATION", "ICON_ALPHA", "MIN_HEADER_WIDTH", "MIN_ALPHA", "", "WHITE", "I", "FALLBACK_ACCENT", "rtx.kimiko:kimiko"})
    @SourceDebugExtension(value={"SMAP\nHudHeaderRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HudHeaderRenderer.kt\nrtx/kimiko/api/drags/components/HudHeaderRenderer.Companion\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,264:1\n37#2,2:265\n*S KotlinDebug\n*F\n+ 1 HudHeaderRenderer.kt\nrtx/kimiko/api/drags/components/HudHeaderRenderer.Companion\n*L\n251#1:265,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public final boolean isNewMode() {
            InterfaceModule interfaceModule = InterfaceModule.Companion.getInstance();
            return interfaceModule != null ? interfaceModule.usesNewHudHeader() : false;
        }

        private final float[] textBounds(Fonts font, String text, float size) {
            float[] bounds = font.msdfBounds(text, size);
            if (bounds.length >= 4 && bounds[3] - bounds[1] > 0.0f) {
                return bounds;
            }
            float[] fArray = new float[]{0.0f, 0.0f, font.msdfWidth(text, size), size};
            return fArray;
        }

        private final TitleGlyph[] buildTitleGlyphs(Fonts font, String text, float size) {
            if (((CharSequence)text).length() == 0) {
                return new TitleGlyph[0];
            }
            List<TitleGlyph> result = new ArrayList<>(text.length());
            int index = 0;
            float penX = 0.0f;
            while (index < text.length()) {
                int codePoint = text.codePointAt(index);
                int nextIndex = index + Character.charCount(codePoint);
                String glyphText = text.substring(index, nextIndex);
                String string = text.substring(0, nextIndex);
                float endX = font.msdfWidth(string, size);
                float[] glyphBounds = font.msdfBounds(glyphText, size);
                float left = penX + (glyphBounds.length >= 4 ? glyphBounds[0] : 0.0f);
                float right = penX + (glyphBounds.length >= 4 ? glyphBounds[2] : font.msdfWidth(glyphText, size));
                result.add(new TitleGlyph(glyphText, penX, left, right));
                penX = endX;
                index = nextIndex;
            }
            return result.toArray(new TitleGlyph[0]);
        }

        private final float clamp01(float value) {
            return RangesKt.coerceIn((float)value, (float)0.0f, (float)1.0f);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\rJ8\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0017\u001a\u00020\u0016H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u0019\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0019\u0010\u000bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b\u001d\u0010\rR\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b\u001e\u0010\rR\u0017\u0010\u0007\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b\u001f\u0010\r\u00a8\u0006 "}, d2={"Lrtx/kimiko/api/drags/components/HudHeaderRenderer$TitleGlyph;", "", "", "text", "", "origin", "left", "right", "<init>", "(Ljava/lang/String;FFF)V", "component1", "()Ljava/lang/String;", "component2", "()F", "component3", "component4", "copy", "(Ljava/lang/String;FFF)Lrtx/kimiko/api/drags/components/HudHeaderRenderer$TitleGlyph;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Ljava/lang/String;", "getText", "F", "getOrigin", "getLeft", "getRight", "rtx.kimiko:kimiko"})
    private static final class TitleGlyph {
        @NotNull
        private final String text;
        private final float origin;
        private final float left;
        private final float right;

        public TitleGlyph(@NotNull String text, float origin, float left, float right) {
            Intrinsics.checkNotNullParameter((Object)text, (String)"text");
            this.text = text;
            this.origin = origin;
            this.left = left;
            this.right = right;
        }

        @NotNull
        public final String getText() {
            return this.text;
        }

        public final float getOrigin() {
            return this.origin;
        }

        public final float getLeft() {
            return this.left;
        }

        public final float getRight() {
            return this.right;
        }

        @NotNull
        public final String component1() {
            return this.text;
        }

        public final float component2() {
            return this.origin;
        }

        public final float component3() {
            return this.left;
        }

        public final float component4() {
            return this.right;
        }

        @NotNull
        public final TitleGlyph copy(@NotNull String text, float origin, float left, float right) {
            Intrinsics.checkNotNullParameter((Object)text, (String)"text");
            return new TitleGlyph(text, origin, left, right);
        }

        public static /* synthetic */ TitleGlyph copy$default(TitleGlyph titleGlyph, String string, float f, float f2, float f3, int n, Object object) {
            if ((n & 1) != 0) {
                string = titleGlyph.text;
            }
            if ((n & 2) != 0) {
                f = titleGlyph.origin;
            }
            if ((n & 4) != 0) {
                f2 = titleGlyph.left;
            }
            if ((n & 8) != 0) {
                f3 = titleGlyph.right;
            }
            return titleGlyph.copy(string, f, f2, f3);
        }

        @NotNull
        public String toString() {
            return "TitleGlyph(text=" + this.text + ", origin=" + this.origin + ", left=" + this.left + ", right=" + this.right + ")";
        }

        public int hashCode() {
            int result = this.text.hashCode();
            result = result * 31 + Float.hashCode(this.origin);
            result = result * 31 + Float.hashCode(this.left);
            result = result * 31 + Float.hashCode(this.right);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TitleGlyph)) {
                return false;
            }
            TitleGlyph titleGlyph = (TitleGlyph)other;
            if (!Intrinsics.areEqual((Object)this.text, (Object)titleGlyph.text)) {
                return false;
            }
            if (Float.compare(this.origin, titleGlyph.origin) != 0) {
                return false;
            }
            if (Float.compare(this.left, titleGlyph.left) != 0) {
                return false;
            }
            return Float.compare(this.right, titleGlyph.right) == 0;
        }
    }
}

