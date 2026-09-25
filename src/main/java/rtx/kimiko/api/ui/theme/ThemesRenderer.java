/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.theme;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.api.ui.theme.AccentGradient;
import rtx.kimiko.api.ui.theme.Theme;
import rtx.kimiko.api.ui.theme.ThemeManager;
import rtx.kimiko.utils.animations.Animation;
import rtx.kimiko.utils.animations.Decelerate;
import rtx.kimiko.utils.animations.Direction;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.modules.post.themeshock.ThemeShockwaveRenderer;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.others.RoundedScissor;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\t\u0018\u0000 [2\u00020\u0001:\u0001[B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\u0006J\r\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\u0003J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0012\u0010\u000bJ\r\u0010\u0013\u001a\u00020\b\u00a2\u0006\u0004\b\u0013\u0010\u0003J\r\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\f\u00a2\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u0006J\r\u0010\u001c\u001a\u00020\b\u00a2\u0006\u0004\b\u001c\u0010\u0003J\r\u0010\u001d\u001a\u00020\b\u00a2\u0006\u0004\b\u001d\u0010\u0003J=\u0010%\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u000e\u00a2\u0006\u0004\b%\u0010&JE\u0010%\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u000e\u00a2\u0006\u0004\b%\u0010(JO\u0010*\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b*\u0010+J?\u00101\u001a\u00020\b2\u0006\u0010,\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\u000e2\u0006\u0010/\u001a\u00020\u000e2\u0006\u00100\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b1\u00102J\u001d\u00106\u001a\u00020\b2\u0006\u00104\u001a\u0002032\u0006\u00105\u001a\u00020\u000e\u00a2\u0006\u0004\b6\u00107J\r\u00108\u001a\u00020\b\u00a2\u0006\u0004\b8\u0010\u0003J\r\u00109\u001a\u00020\u000e\u00a2\u0006\u0004\b9\u0010\u001aJ5\u0010<\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020\u000e\u00a2\u0006\u0004\b<\u0010=R \u0010A\u001a\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020@0>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bA\u0010BR \u0010C\u001a\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020\u000e0>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bC\u0010BR0\u0010F\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020@0Dj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020@`E8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u0010GR\u0016\u0010H\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bH\u0010IR\u0016\u0010K\u001a\u00020J8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010LR\u0016\u0010M\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010NR\u0016\u0010O\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bO\u0010NR\u0016\u0010P\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010QR\u0016\u00106\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u0010QR\u0016\u0010R\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bR\u0010QR\u001e\u0010T\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010?0S8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010UR\u0016\u0010V\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010WR\u0016\u0010X\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010IR\u0016\u0010Y\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010QR\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010WR\u0016\u0010\u0017\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010IR\u0016\u0010\u0019\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010QR\u0016\u0010Z\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010N\u00a8\u0006\\"}, d2={"Lrtx/kimiko/api/ui/theme/ThemesRenderer;", "", "<init>", "()V", "", "isTransitioning", "()Z", "isFadeOutDone", "", "open", "quick", "(Z)V", "", "row", "", "rowAppear", "(I)F", "value", "setAppearComposite", "resetCardBlur", "", "cardBlurRects", "()[F", "cardBlurCount", "()I", "cardBlurMaxPhase", "()F", "hasAppearWork", "beginFadeOut", "finishTransition", "Lnet/minecraft/DrawContext;", "g", "x", "y", "w", "alpha", "dt", "render", "(Lnet/minecraft/DrawContext;FFFFF)V", "slideAlpha", "(Lnet/minecraft/DrawContext;FFFFFF)V", "animateAppear", "renderCards", "(Lnet/minecraft/DrawContext;FFFFFFZ)V", "listX", "listY", "listW", "listH", "maxScroll", "renderScrollBar", "(FFFFFF)V", "", "amount", "viewH", "scroll", "(DF)V", "resetScroll", "currentScroll", "mx", "my", "click", "(FFFFF)Z", "", "Lrtx/kimiko/api/ui/theme/Theme;", "Lrtx/kimiko/utils/animations/Decelerate;", "selectAnims", "Ljava/util/Map;", "hoverAnims", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "rowAppearAnims", "Ljava/util/HashMap;", "appearFadeMs", "I", "", "appearBaseMs", "J", "appearInitialFrame", "Z", "transitioning", "fadeOutTime", "F", "scrollTarget", "", "cardThemes", "[Lrtx/kimiko/api/ui/theme/Theme;", "cardParams", "[F", "cardCount", "contentH", "appearComposite", "Companion", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nThemesRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ThemesRenderer.kt\nrtx/kimiko/api/ui/theme/ThemesRenderer\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,426:1\n37#2,2:427\n37#2,2:436\n460#3,7:429\n*S KotlinDebug\n*F\n+ 1 ThemesRenderer.kt\nrtx/kimiko/api/ui/theme/ThemesRenderer\n*L\n157#1:427,2\n377#1:436,2\n203#1:429,7\n*E\n"})
public final class ThemesRenderer {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Map<Theme, Decelerate> selectAnims = new EnumMap(Theme.class);
    @NotNull
    private final Map<Theme, Float> hoverAnims = new EnumMap(Theme.class);
    @NotNull
    private final HashMap<Integer, Decelerate> rowAppearAnims = new HashMap();
    private int appearFadeMs = 320;
    private long appearBaseMs;
    private boolean appearInitialFrame;
    private boolean transitioning;
    private float fadeOutTime;
    private float scroll;
    private float scrollTarget;
    @NotNull
    private Theme[] cardThemes = new Theme[0];
    @NotNull
    private float[] cardParams = new float[0];
    private int cardCount;
    private float contentH;
    @NotNull
    private final float[] cardBlurRects = new float[192];
    private int cardBlurCount;
    private float cardBlurMaxPhase;
    private boolean appearComposite;
    private static final float FADE_OUT_DURATION = 0.15f;
    private static final int ROW_FADE_MS = 380;
    private static final int QUICK_ROW_FADE_MS = 320;
    private static final float SLIDE_PX = 6.0f;
    private static final float CARD_H = 35.0f;
    private static final float CARD_GAP = 5.0f;
    private static final float CARD_PAD = 5.0f;
    private static final float CARD_RADIUS = 6.0f;
    private static final float CORNER_BLEND = 16.0f;
    private static final float PANEL_RADIUS = 12.0f;
    private static final float CONTENT_Y_OFFSET = 5.0f;
    private static final float CONTENT_HEIGHT = 280.0f;
    private static final int CARD_PARAMS = 9;
    public static final int MAX_BLUR_CARDS = 32;

    public final boolean isTransitioning() {
        return this.transitioning;
    }

    public final boolean isFadeOutDone() {
        return this.transitioning && this.fadeOutTime <= 0.0f;
    }

    public final void open() {
        this.open(false);
    }

    public final void open(boolean quick) {
        this.rowAppearAnims.clear();
        this.scroll = 0.0f;
        this.scrollTarget = 0.0f;
        this.appearFadeMs = quick ? 320 : 380;
        this.appearBaseMs = System.currentTimeMillis();
        this.appearInitialFrame = true;
    }

    private final float rowAppear(int row) {
        Decelerate d = this.rowAppearAnims.get(row);
        if (d == null) {
            long start = this.appearInitialFrame ? this.appearBaseMs : System.currentTimeMillis();
            Animation animation = new Decelerate().setMs(this.appearFadeMs).setValue(1.0);
            Intrinsics.checkNotNull((Object)animation, (String)"null cannot be cast to non-null type rtx.kimiko.utils.animations.Decelerate");
            d = (Decelerate)animation;
            d.counter.setTime(start);
            ((Map)this.rowAppearAnims).put(row, d);
        }
        Double d2 = d.getOutput();
        float v = (float)(d2 != null ? d2 : 0.0);
        return Math.max(0.0f, Math.min(1.0f, v));
    }

    public final void setAppearComposite(boolean value) {
        this.appearComposite = value;
    }

    public final void resetCardBlur() {
        this.cardBlurCount = 0;
        this.cardBlurMaxPhase = 0.0f;
    }

    @NotNull
    public final float[] cardBlurRects() {
        return this.cardBlurRects;
    }

    public final int cardBlurCount() {
        return this.cardBlurCount;
    }

    public final float cardBlurMaxPhase() {
        return this.cardBlurMaxPhase;
    }

    private final boolean hasAppearWork() {
        if (this.appearInitialFrame) {
            return true;
        }
        if (Math.abs(this.scrollTarget - this.scroll) > 0.05f) {
            return true;
        }
        Iterator<Decelerate> iterator = this.rowAppearAnims.values().iterator();
        while (iterator.hasNext()) {
            Decelerate d = (Decelerate) (iterator.next());
            Double d2 = d.getOutput();
            double d3 = d2 != null ? d2 : 0.0;
            if (!((float)d3 < 0.999f)) continue;
            return true;
        }
        return false;
    }

    public final void beginFadeOut() {
        this.fadeOutTime = 0.15f;
        this.transitioning = true;
    }

    public final void finishTransition() {
        this.transitioning = false;
        this.fadeOutTime = 0.0f;
    }

    public final void render(@NotNull DrawContext g, float x, float y, float w, float alpha, float dt) {
        Intrinsics.checkNotNullParameter((Object)g, (String)"g");
        this.render(g, x, y, w, alpha, 1.0f, dt);
    }

    public final void render(@NotNull DrawContext g, float x, float y, float w, float alpha, float slideAlpha, float dt) {
        Intrinsics.checkNotNullParameter((Object)g, (String)"g");
        float factor = 1.0f - (float)Math.exp(-dt * 14.0f);
        this.scroll += (this.scrollTarget - this.scroll) * factor;
        if (Math.abs(this.scrollTarget - this.scroll) < 0.05f) {
            this.scroll = this.scrollTarget;
        }
        if (this.appearComposite && !this.transitioning && this.hasAppearWork()) {
            GuiRenderState rs = ((GuiGraphicsExtractorAccessor)g).kimiko$getGuiRenderState();
            rs.createNewRootLayer();
            rs.applyBlur();
            UI.Companion.markCardStratum();
        }
        this.renderCards(g, x, y, w, alpha, slideAlpha, dt, true);
        this.appearInitialFrame = false;
    }

    /*
     * WARNING - void declaration
     */
    private final void renderCards(DrawContext g, float x, float y, float w, float alpha, float slideAlpha, float dt, boolean animateAppear) {
        float cy;
        float cx;
        float listX = x + 117.0f;
        float listY = y + 5.0f;
        float listW = w - 122.0f;
        float gap = 5.0f;
        float pad = 5.0f;
        float colW = (listW - gap - pad * (float)2) * 0.5f;
        float c1x = listX + pad;
        float c2x = c1x + colW + gap;
        float scrollAreaH = CONTENT_HEIGHT;
        Render2D.pushScissor(g, listX, listY, listW, scrollAreaH);
        RoundedScissor.push(g, listX, listY, listW, scrollAreaH, 0.0f, 12.0f, 12.0f, 0.0f);
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        float slideOff = (1.0f - slideAlpha) * 8.0f;
        boolean interactive = UI.Companion.isOpen();
        boolean mouseInList = interactive && mx >= listX && mx <= listX + listW && my >= listY && my <= listY + scrollAreaH;
        float hoverFactor = interactive ? 1.0f - (float)Math.exp(-dt * 16.0f) : 0.0f;
        Theme[] themes = Theme.values();
        if (this.cardThemes.length < themes.length) {
            this.cardThemes = new Theme[themes.length];
            this.cardParams = new float[themes.length * 9];
        }
        this.cardCount = 0;
        for (int idx = 0; idx < themes.length; ++idx) {
            float contBot2;
            float bot;
            Object object;
Map $this$getOrPut$iv = this.selectAnims;
            float rowAppear;
            Theme theme = themes[idx];
            int col = idx % 2;
            int row = idx / 2;
            cx = col == 0 ? c1x : c2x;
            cy = listY + pad + (float)row * (35.0f + gap) + slideOff - this.scroll;
            boolean hovered = mouseInList && mx >= cx && mx <= cx + colW && my >= cy && my <= cy + 35.0f;
            Float f = this.hoverAnims.get((Object)theme);
            float hoverT = f != null ? f.floatValue() : 0.0f;
            hoverT += ((hovered ? 1.0f : 0.0f) - hoverT) * hoverFactor;
            this.hoverAnims.put(theme, Float.valueOf(hoverT));
            if (cy + 35.0f < listY - (float)5 || cy > listY + scrollAreaH + (float)5) continue;
            float f2 = rowAppear = animateAppear ? this.rowAppear(row) : 1.0f;
            if (rowAppear < 0.001f) continue;
            float settleT = Math.min(1.0f, rowAppear / 0.6f);
            float presence = settleT * settleT;
            float fadeT = rowAppear < 0.6f ? 0.0f : (rowAppear - 0.6f) / 0.4f;
            float blurPhase = 1.0f - fadeT * fadeT * (3.0f - 2.0f * fadeT);
            cy += (1.0f - settleT) * 6.0f;
            boolean cardAppearing = rowAppear < 0.999f;
            boolean compositeCard = this.appearComposite && cardAppearing;
            float ma = alpha * (!cardAppearing || compositeCard ? 1.0f : presence);
            if (cardAppearing && compositeCard && this.cardBlurCount < 32) {
                int off = this.cardBlurCount * 6;
                this.cardBlurRects[off] = cx;
                this.cardBlurRects[off + 1] = cy;
                this.cardBlurRects[off + 2] = colW;
                this.cardBlurRects[off + 3] = 35.0f;
                this.cardBlurRects[off + 4] = presence;
                this.cardBlurRects[off + 5] = blurPhase;
                int n = this.cardBlurCount;
                this.cardBlurCount = n + 1;
                this.cardBlurMaxPhase = Math.max(this.cardBlurMaxPhase, blurPhase);
            }
            Map<Theme, Decelerate> map = this.selectAnims;
            Theme key$iv = theme;
            boolean $i$f$getOrPut = false;
            Object value$iv = $this$getOrPut$iv.get((Object)key$iv);
            if (value$iv == null) {
                boolean bl = false;
                Decelerate answer$iv = ThemesRenderer.Companion.createAnim(220);
                $this$getOrPut$iv.put(key$iv, answer$iv);
                object = answer$iv;
            } else {
                object = value$iv;
            }
            Decelerate sel = (Decelerate)object;
            sel.setDirection(theme == ThemeManager.current() ? Direction.FORWARDS : Direction.BACKWARDS);
            Double d = sel.getOutput();
            float st = (float)(d != null ? d : 0.0);
            float rbr = 6.0f;
            if (col == 1 && (bot = cy + 35.0f) > (contBot2 = listY + scrollAreaH) - 16.0f) {
                float t = ThemesRenderer.Companion.clamp((bot - (contBot2 - 16.0f)) / 16.0f, 0.0f, 1.0f);
                rbr = 6.0f + 6.0f * t;
            }
            int base = this.cardCount * 9;
            this.cardThemes[this.cardCount] = theme;
            this.cardParams[base] = cx;
            this.cardParams[base + 1] = cy;
            this.cardParams[base + 2] = rbr;
            this.cardParams[base + 3] = ma;
            this.cardParams[base + 4] = hoverT;
            this.cardParams[base + 5] = st;
            this.cardParams[base + 6] = Math.max(hoverT, st * 0.65f);
            this.cardParams[base + 7] = cardAppearing ? 0.85f + 0.15f * settleT : 1.0f;
            this.cardParams[base + 8] = cardAppearing ? 1.0f : 0.0f;
            this.cardCount++;
        }
        for (int pass = 0; pass < 3; ++pass) {
            int n = this.cardCount;
            for (int card = 0; card < n; ++card) {
                boolean appearing;
                int base = card * 9;
                Theme theme = this.cardThemes[card];
                cx = this.cardParams[base];
                cy = this.cardParams[base + 1];
                float rbr = this.cardParams[base + 2];
                float ma = this.cardParams[base + 3];
                float hoverT = this.cardParams[base + 4];
                float st = this.cardParams[base + 5];
                boolean bl = appearing = this.cardParams[base + 8] > 0.5f;
                if (appearing) {
                    float cardScale = this.cardParams[base + 7];
                    float ox = cx + colW * 0.5f;
                    float oy = cy + 17.5f;
                    g.getMatrices().pushMatrix();
                    g.getMatrices().translate(ox, oy);
                    g.getMatrices().scale(cardScale, cardScale);
                    g.getMatrices().translate(-ox, -oy);
                }
                switch (pass) {
                    case 0: {
                        RectUtil.drawGlassCard(cx, cy, colW, 35.0f, 6.0f, 6.0f, rbr, 6.0f, ma, this.cardParams[base + 6]);
                        Render2D.rect(cx, cy, colW, 35.0f, 6.0f, 6.0f, rbr, 6.0f, ThemeManager.rgba(0xFFFFFF, (7.0f + 5.0f * hoverT) * ma), ThemeManager.rgba(0xFFFFFF, (7.0f + 5.0f * hoverT) * ma), ThemeManager.rgba(0xFFFFFF, 1.5f * ma), ThemeManager.rgba(0xFFFFFF, 1.5f * ma));
                        if (st > 0.01f) {
                            int gA = theme.gradientA();
                            int gB = theme.gradientB();
                            Render2D.rect(cx, cy, colW, 35.0f, 6.0f, 6.0f, rbr, 6.0f, ThemeManager.rgba(gA, 22.0f * st * ma), ThemeManager.rgba(gB, 22.0f * st * ma), ThemeManager.rgba(gB, 22.0f * st * ma), ThemeManager.rgba(gA, 22.0f * st * ma));
                        }
                        Unit unit = Unit.INSTANCE;
                        break;
                    }
                    case 1: {
                        int gA = theme.gradientA();
                        int gB = theme.gradientB();
                        int idleTop = ThemeManager.rgba(0xFFFFFF, (30.0f + 22.0f * hoverT) * ma);
                        int idleBottom = ThemeManager.rgba(0xFFFFFF, (10.0f + 8.0f * hoverT) * ma);
                        int outTop = ThemeManager.mix(idleTop, ThemeManager.rgba(gA, 78.0f * ma), st);
                        int outBottom = ThemeManager.mix(idleBottom, ThemeManager.rgba(gB, 52.0f * ma), st);
                        Render2D.outline(cx, cy, colW, 35.0f, 6.0f, 6.0f, rbr, 6.0f, 0.6f, outTop, outTop, outBottom, outBottom);
                        if (st > 0.01f) {
                            float dotX = cx + colW - 9.5f;
                            Render2D.rect(dotX, cy + 8.5f, 3.5f, 3.5f, 1.75f, ThemeManager.rgba(theme.accentBrightRgb(), (float)220 * st * ma));
                        }
                        int[] swatches = theme.palette().length >= 2 ? theme.palette() : theme.shades();
                        float swatchSize = 9.0f;
                        float swatchGap = 3.0f;
                        float swY = cy + 35.0f - swatchSize - 7.0f;
                        int swatchCount = Math.min(swatches.length, 5);
                        for (int sw = 0; sw < swatchCount; ++sw) {
                            float swX = cx + (float)8 + (float)sw * (swatchSize + swatchGap);
                            Render2D.rect(swX, swY, swatchSize, swatchSize, 3.0f, ThemeManager.rgba(swatches[sw], (float)235 * ma));
                        }
                        Unit unit = Unit.INSTANCE;
                        break;
                    }
                    default: {
                        Fonts.MEDIUM.draw(theme.displayName(), cx + (float)8, cy + 6.5f, 7.0f, ThemeManager.rgba(0xFFFFFF, ((float)185 + (float)35 * hoverT + (float)35 * st) * ma));
                        Unit unit = Unit.INSTANCE;
                    }
                }
                if (!appearing) continue;
                g.getMatrices().popMatrix();
            }
        }
        int rows = (themes.length + 1) / 2;
        this.contentH = (float)rows * (35.0f + gap) - gap;
        float maxScroll = Math.max(0.0f, this.contentH - scrollAreaH + pad * (float)2);
        this.scrollTarget = ThemesRenderer.Companion.clamp(this.scrollTarget, 0.0f, maxScroll);
        this.scroll = ThemesRenderer.Companion.clamp(this.scroll, 0.0f, maxScroll);
        RoundedScissor.pop();
        Render2D.popScissor(g);
        this.renderScrollBar(listX, listY, listW, scrollAreaH, maxScroll, alpha);
    }

    private final void renderScrollBar(float listX, float listY, float listW, float listH, float maxScroll, float alpha) {
        if (maxScroll <= 0.5f) {
            return;
        }
        float inset = 3.0f;
        float trackX = listX + listW + 0.25f;
        float trackY = listY + inset;
        float trackH = listH - inset * 2.0f;
        float visibleRatio = ThemesRenderer.Companion.clamp(listH / Math.max(this.contentH, listH), 0.0f, 1.0f);
        float thumbH = ThemesRenderer.Companion.clamp(trackH * visibleRatio, 12.0f, trackH);
        float travel = Math.max(0.0f, trackH - thumbH);
        float progress = ThemesRenderer.Companion.clamp(this.scroll / Math.max(maxScroll, 1.0f), 0.0f, 1.0f);
        float thumbY = trackY + travel * progress;
        Render2D.rect(trackX, trackY, 1.25f, trackH, 1.0f, ThemeManager.rgba(0xFFFFFF, (float)18 * alpha));
        AccentGradient.fillVertical(trackX, thumbY, 1.25f, thumbH, 1.0f, (float)165 * alpha);
    }

    public final void scroll(double amount, float viewH) {
        float maxValue = Math.max(0.0f, this.contentH - viewH + 10.0f);
        this.scrollTarget = ThemesRenderer.Companion.clamp(this.scrollTarget - (float)amount * (float)18, 0.0f, maxValue);
    }

    public final void resetScroll() {
        this.scroll = 0.0f;
        this.scrollTarget = 0.0f;
    }

    public final float currentScroll() {
        return this.scroll;
    }

    public final boolean click(float x, float y, float w, float mx, float my) {
        if (this.transitioning) {
            return false;
        }
        float listX = x + 117.0f;
        float listY = y + 5.0f;
        float listW = w - 122.0f;
        float gap = 5.0f;
        float pad = 5.0f;
        float colW = (listW - gap - pad * (float)2) * 0.5f;
        float c1x = listX + pad;
        float c2x = c1x + colW + gap;
        if (mx < listX || mx > listX + listW || my < listY || my > listY + CONTENT_HEIGHT) {
            return false;
        }
        Theme[] themes = Theme.values();
        int n = themes.length;
        for (int idx = 0; idx < n; ++idx) {
            int col = idx % 2;
            int row = idx / 2;
            float cx = col == 0 ? c1x : c2x;
            float cy = listY + pad + (float)row * (35.0f + gap) - this.scroll;
            if (!(mx >= cx) || !(mx <= cx + colW) || !(my >= cy) || !(my <= cy + 35.0f)) continue;
            Theme target = themes[idx];
            if (ThemeManager.current() != target) {
                ThemeShockwaveRenderer.trigger(mx, my);
            }
            ThemeManager.set(target);
            return true;
        }
        return false;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0010R\u0014\u0010\u0015\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0010R\u0014\u0010\u0016\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0010R\u0014\u0010\u0017\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0010R\u0014\u0010\u0018\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0010R\u0014\u0010\u0019\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0010R\u0014\u0010\u001a\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0010R\u0014\u0010\u001b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0010R\u0014\u0010\u001c\u001a\u00020\u00048\u0002X\u0082D\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0010R\u0014\u0010\u001d\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u0012R\u0014\u0010\u001e\u001a\u00020\n8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u0012\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/api/ui/theme/ThemesRenderer.Companion;", "", "<init>", "()V", "", "v", "min", "max", "clamp", "(FFF)F", "", "ms", "Lrtx/kimiko/utils/animations/Decelerate;", "createAnim", "(I)Lrtx/kimiko/utils/animations/Decelerate;", "FADE_OUT_DURATION", "F", "ROW_FADE_MS", "I", "QUICK_ROW_FADE_MS", "SLIDE_PX", "CARD_H", "CARD_GAP", "CARD_PAD", "CARD_RADIUS", "CORNER_BLEND", "PANEL_RADIUS", "CONTENT_Y_OFFSET", "CONTENT_HEIGHT", "CARD_PARAMS", "MAX_BLUR_CARDS", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float clamp(float v, float min, float max) {
            return Math.max(min, Math.min(max, v));
        }

        private final Decelerate createAnim(int ms) {
            Animation animation = new Decelerate().setMs(ms).setValue(1.0);
            Intrinsics.checkNotNull((Object)animation, (String)"null cannot be cast to non-null type rtx.kimiko.utils.animations.Decelerate");
            Decelerate d = (Decelerate)animation;
            d.setDirection(Direction.BACKWARDS);
            d.counter.setTime(System.currentTimeMillis() - (long)10000);
            return d;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

