/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.settings.impl;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.api.ui.theme.AccentGradient;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.animations.Decelerate;
import rtx.kimiko.utils.animations.Direction;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 I2\u00020\u0001:\u0001IB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u000eJ/\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\u0017\u0010\u000bJ9\u0010\u001a\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\u001c\u0010\u000bJ?\u0010!\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010 \u001a\u00020\u001fH\u0016\u00a2\u0006\u0004\b!\u0010\"J7\u0010#\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b#\u0010$J7\u0010%\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b%\u0010$J\u000f\u0010&\u001a\u00020\u0014H\u0016\u00a2\u0006\u0004\b&\u0010'J\u0017\u0010)\u001a\u00020\u00142\u0006\u0010(\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b)\u0010*J\u000f\u0010+\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b+\u0010\u000eJ\u000f\u0010,\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b,\u0010\u000eJ\u000f\u0010-\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b-\u0010\u000eJ\u0017\u0010/\u001a\u00020\u00142\u0006\u0010.\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b/\u00100J'\u00103\u001a\u00020\f2\u0006\u00101\u001a\u00020\u00062\u0006\u00102\u001a\u00020\t2\u0006\u0010.\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b3\u00104J\u000f\u00105\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b5\u0010'R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u00106R\u0016\u00107\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0016\u0010<\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0016\u0010>\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010=R\u0016\u0010@\u001a\u00020?8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u0010AR0\u0010D\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f0Bj\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f`C8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u0016\u0010F\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u0010=R\u0016\u0010G\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bG\u00108R\u0016\u0010H\u001a\u00020?8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bH\u0010A\u00a8\u0006J"}, d2={"Lrtx/kimiko/api/ui/settings/impl/SelectSetting;", "Lrtx/kimiko/api/ui/settings/Setting;", "Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "backend", "<init>", "(Lrtx/kimiko/api/modules/settings/impl/SelectSetting;)V", "", "name", "()Ljava/lang/String;", "", "isVisible", "()Z", "", "height", "()F", "preferredWidth", "x", "y", "w", "alpha", "", "render", "(FFFF)V", "hasOverlay", "Lnet/minecraft/DrawContext;", "graphics", "renderOverlay", "(Lnet/minecraft/DrawContext;FFFF)V", "isOverlayOpen", "mx", "my", "", "delta", "scrollOverlay", "(FFFFFD)Z", "clickOverlay", "(FFFFF)Z", "click", "closeOverlay", "()V", "settled", "updatePendingClose", "(Z)V", "dropWidth", "maxScroll", "frameDt", "dt", "updateScroll", "(F)V", "option", "hovered", "updateHover", "(Ljava/lang/String;ZF)F", "initScrollToSelected", "Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "open", "Z", "Lrtx/kimiko/utils/animations/Decelerate;", "dropAnim", "Lrtx/kimiko/utils/animations/Decelerate;", "scroll", "F", "scrollTarget", "", "lastFrameNs", "J", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "hoverT", "Ljava/util/HashMap;", "indicatorPos", "pendingClose", "closeAtMs", "Companion", "rtx.kimiko:kimiko"})
public class SelectSetting
implements Setting {
    @NotNull
    private static final Companion Companion = new Companion(null);
    @NotNull
    private final rtx.kimiko.api.modules.settings.impl.SelectSetting backend;
    private boolean open;
    @NotNull
    private final Decelerate dropAnim;
    private float scroll;
    private float scrollTarget;
    private long lastFrameNs;
    @NotNull
    private final HashMap<String, Float> hoverT;
    private float indicatorPos;
    private boolean pendingClose;
    private long closeAtMs;
    private static final int MAX_VISIBLE = 6;
    private static final float ITEM_H = 13.3f;
    private static final float GAP = 1.7f;
    private static final float TEXT_SIZE = 5.0f;
    private static final float TEXT_PAD_X = 6.7f;
    private static final float CHIP_RADIUS = 2.5f;
    private static final float CHIP_H = 11.6f;
    private static final float TEXT_LINE_H = 5.833333f;
    private static final float TEXT_Y_OFF = 2.8833337f;
    private static final float HOVER_RATE = 16.0f;
    private static final float INDICATOR_RATE = 13.0f;
    private static final float SETTLE_EPS = 0.5f;
    private static final long CLOSE_DELAY_MS = 50L;
    private static final float EDGE_FADE = 3.3f;
    private static final float EDGE_INSET = 0.8f;

    public SelectSetting(@NotNull rtx.kimiko.api.modules.settings.impl.SelectSetting backend) {
        Intrinsics.checkNotNullParameter((Object)backend, (String)"backend");
        this.backend = backend;
        this.dropAnim = new Decelerate();
        this.hoverT = new HashMap();
        this.indicatorPos = Float.NaN;
        this.dropAnim.setMs(200);
        this.dropAnim.setValue(1.0);
        this.dropAnim.setDirection(Direction.BACKWARDS);
        this.dropAnim.counter.setTime(System.currentTimeMillis() - (long)10000);
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
        return 6.0f + Fonts.MEDIUM.width(this.backend.getDisplayName(), 6.5f) + 6.0f + Fonts.MEDIUM.width(this.backend.getDisplaySelected(), 6.0f) + 10.0f + 8.0f;
    }

    @Override
    public void render(float x, float y, float w, float alpha) {
        String sel = this.backend.getDisplaySelected();
        float selW = Fonts.MEDIUM.width(sel, 6.0f) + (float)10;
        float btnX = x + w - selW - (float)4;
        float btnY = y + 2.0f;
        RenderHelper.drawName(this.backend.getDisplayName(), x, y, btnX - (x + 6.0f) - 4.0f, alpha);
        RenderHelper.drawBtn(btnX, btnY, selW, 12.0f, sel, alpha);
    }

    @Override
    public boolean hasOverlay() {
        Double d = this.dropAnim.getOutput();
        Intrinsics.checkNotNull((Object)d);
        return (float)d.doubleValue() > 0.01f;
    }

    @Override
    public void renderOverlay(@Nullable DrawContext graphics, float x, float y, float w, float alpha) {
        Double d = this.dropAnim.getOutput();
        Intrinsics.checkNotNull((Object)d);
        float t = (float)d.doubleValue();
        if (t <= 0.01f) {
            return;
        }
        float dAlpha = t * alpha;
        List<String> options = this.backend.getOptions();
        float dropW = this.dropWidth();
        float dropX = x + w - dropW - (float)4;
        int visible = Math.min(options.size(), 6);
        float dropH = 1.7f + (float)visible * 13.3f;
        float dropY = RenderHelper.overlayY(y, dropH);
        String sel = this.backend.getSelected();
        boolean scrollable = options.size() > 6;
        float dt = this.frameDt();
        this.updateScroll(dt);
        RenderHelper.drawDropBackground(dropX, dropY, dropW, dropH, dAlpha);
        float popupR = RectUtil.clientWindowRadius(2.0f, dropW, dropH);
        float edgeR = Math.min(Math.max(2.5f, popupR - 1.7f), 5.8f);
        float top = dropY + 0.8f;
        float bottom = dropY + dropH - 0.8f;
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        float chipX = dropX + 1.7f;
        float chipW = dropW - 1.7f - (scrollable ? 6.5f : 1.7f);
        int selIdx = options.indexOf(sel);
        if (selIdx >= 0) {
            float target = (float)selIdx * 13.3f;
            if (Float.isNaN(this.indicatorPos)) {
                this.indicatorPos = target;
            } else {
                this.indicatorPos += (target - this.indicatorPos) * (1.0f - (float)Math.exp(-dt * 13.0f));
                if (Math.abs(target - this.indicatorPos) < 0.05f) {
                    this.indicatorPos = target;
                }
            }
            this.updatePendingClose(Math.abs(target - this.indicatorPos) < 0.5f);
            float indY = dropY + 1.7f + this.indicatorPos - this.scroll;
            float indBottom = indY + 11.6f;
            if (indBottom > top && indY < bottom) {
                float indFade;
                float f = indFade = scrollable ? Math.min(SelectSetting.Companion.edgeAlpha(indY, top, bottom), SelectSetting.Companion.edgeAlpha(indBottom, top, bottom)) : 1.0f;
                if (indFade > 0.01f) {
                    float totalSpan = Math.max(0.0f, (float)(options.size() - 1) * 13.3f);
                    float topBlend = Math.max(0.0f, Math.min(1.0f, (13.3f - this.indicatorPos) / 13.3f));
                    float bottomBlend = Math.max(0.0f, Math.min(1.0f, (this.indicatorPos - (totalSpan - 13.3f)) / 13.3f));
                    float indTopR = 2.5f + (edgeR - 2.5f) * topBlend;
                    float indBottomR = 2.5f + (edgeR - 2.5f) * bottomBlend;
                    AccentGradient.fillHorizontal(chipX, indY, chipW, 11.6f, indTopR, indTopR, indBottomR, indBottomR, (float)220 * dAlpha * indFade);
                }
            }
        } else {
            this.indicatorPos = Float.NaN;
            this.updatePendingClose(true);
        }
        int indY = ((Collection)options).size();
        for (int i = 0; i < indY; ++i) {
            float fade;
            String opt = options.get(i);
            float itemY = dropY + 1.7f + (float)i * 13.3f - this.scroll;
            float glyphBottom = itemY + 11.6f;
            if (glyphBottom <= top || itemY >= bottom) continue;
            float f = fade = scrollable ? Math.min(SelectSetting.Companion.edgeAlpha(itemY, top, bottom), SelectSetting.Companion.edgeAlpha(glyphBottom, top, bottom)) : 1.0f;
            if (fade <= 0.01f) continue;
            boolean isSel = Intrinsics.areEqual((Object)opt, (Object)sel);
            boolean hovered = !isSel && mx >= chipX && mx <= chipX + chipW && my >= itemY && my <= itemY + 11.6f && my >= top && my <= bottom;
            float hover = this.updateHover(opt, hovered, dt);
            if (hover > 0.01f) {
                float hoverTopR = i == 0 ? edgeR : 2.5f;
                float hoverBottomR = i == options.size() - 1 ? edgeR : 2.5f;
                Render2D.rect(chipX, itemY, chipW, 11.6f, hoverTopR, hoverTopR, hoverBottomR, hoverBottomR, new Color(255, 255, 255, (int)((float)24 * hover * dAlpha * fade)).getRGB());
            }
            float textAlpha = isSel ? 235.0f : 145.0f + 50.0f * hover;
            int color = new Color(255, 255, 255, (int)(textAlpha * dAlpha * fade)).getRGB();
            String label = this.backend.getDisplayOption(opt);
            float textW = Fonts.MEDIUM.width(label, 5.0f);
            float textX = chipX + (chipW - textW) * 0.5f;
            Fonts.MEDIUM.draw(label, textX, itemY + 2.8833337f, 5.0f, color);
        }
        if (scrollable) {
            float max = this.maxScroll();
            float trackX = dropX + dropW - 3.0f;
            float trackY = dropY + 1.7f;
            float trackH = dropH - 3.4f;
            Render2D.rect(trackX, trackY, 1.4f, trackH, 0.7f, new Color(255, 255, 255, (int)((float)28 * dAlpha)).getRGB());
            float thumbH = Math.max(9.0f, trackH * 6.0f / (float)options.size());
            float thumbY = trackY + (max <= 0.0f ? 0.0f : this.scroll / max * (trackH - thumbH));
            Render2D.rect(trackX, thumbY, 1.4f, thumbH, 0.7f, ClientAccent.accentSoftAt((float)190 * dAlpha, trackX, thumbY + thumbH * 0.5f));
        }
    }

    @Override
    public boolean isOverlayOpen() {
        return this.open;
    }

    @Override
    public boolean scrollOverlay(float x, float y, float w, float mx, float my, double delta) {
        if (!this.open) {
            return false;
        }
        float dropW = this.dropWidth();
        float dropX = x + w - dropW - (float)4;
        int visible = Math.min(this.backend.getOptions().size(), 6);
        float dropH = 1.7f + (float)visible * 13.3f;
        float dropY = RenderHelper.overlayY(y, dropH);
        if (mx < dropX || mx > dropX + dropW || my < dropY || my > dropY + dropH) {
            return false;
        }
        float max = this.maxScroll();
        if (max > 0.0f) {
            this.scrollTarget = SelectSetting.Companion.clamp(this.scrollTarget - (float)delta * 13.3f, 0.0f, max);
        }
        return true;
    }

    @Override
    public boolean clickOverlay(float x, float y, float w, float mx, float my) {
        Double d = this.dropAnim.getOutput();
        Intrinsics.checkNotNull((Object)d);
        float t = (float)d.doubleValue();
        if (!this.open || t <= 0.1f) {
            return false;
        }
        List<String> options = this.backend.getOptions();
        float dropW = this.dropWidth();
        float dropX = x + w - dropW - (float)4;
        int visible = Math.min(options.size(), 6);
        float dropH = 1.7f + (float)visible * 13.3f;
        float dropY = RenderHelper.overlayY(y, dropH);
        if (mx < dropX || mx > dropX + dropW || my < dropY + 1.7f || my > dropY + dropH - 1.7f) {
            return false;
        }
        int idx = (int)Math.floor((my - (dropY + 1.7f) + this.scroll) / 13.3f);
        if (idx >= 0 && idx < options.size()) {
            this.backend.setSelected(options.get(idx));
            this.pendingClose = true;
            this.closeAtMs = 0L;
        }
        return true;
    }

    @Override
    public boolean click(float x, float y, float w, float mx, float my) {
        String sel = this.backend.getSelected();
        float selW = Fonts.MEDIUM.width(sel, 6.0f) + (float)10;
        float btnX = x + w - selW - (float)4;
        float btnY = y + 2.0f;
        if (mx >= btnX && mx <= btnX + selW && my >= btnY && my <= btnY + 12.0f) {
            this.open = !this.open;
            this.pendingClose = false;
            this.closeAtMs = 0L;
            Sounds.play(this.open ? "settings_open" : "settings_close");
            this.dropAnim.setDirection(this.open ? Direction.FORWARDS : Direction.BACKWARDS);
            this.dropAnim.counter.resetCounter();
            if (this.open) {
                this.initScrollToSelected();
            }
            return true;
        }
        return false;
    }

    @Override
    public void closeOverlay() {
        this.pendingClose = false;
        this.closeAtMs = 0L;
        if (this.open) {
            this.open = false;
            Sounds.play("settings_close");
            this.dropAnim.setDirection(Direction.BACKWARDS);
            this.dropAnim.counter.resetCounter();
        }
    }

    private final void updatePendingClose(boolean settled) {
        if (!this.pendingClose || !this.open) {
            return;
        }
        if (!settled) {
            this.closeAtMs = 0L;
            return;
        }
        long now = System.currentTimeMillis();
        if (this.closeAtMs == 0L) {
            this.closeAtMs = now + 50L;
        } else if (now >= this.closeAtMs) {
            this.closeOverlay();
        }
    }

    private final float dropWidth() {
        float maxTextW = 0.0f;
        for (String opt : this.backend.getOptions()) {
            maxTextW = Math.max(maxTextW, Fonts.MEDIUM.width(this.backend.getDisplayOption(opt), 5.0f));
        }
        float base = maxTextW + 13.4f + 3.4f;
        return this.backend.getOptions().size() > 6 ? base + 5.0f : base;
    }

    private final float maxScroll() {
        return (float)Math.max(0, this.backend.getOptions().size() - 6) * 13.3f;
    }

    private final float frameDt() {
        long now = System.nanoTime();
        float dt = this.lastFrameNs == 0L ? 0.0f : Math.min(0.05f, (float)(now - this.lastFrameNs) / 1.0E9f);
        this.lastFrameNs = now;
        return dt;
    }

    private final void updateScroll(float dt) {
        float max = this.maxScroll();
        this.scrollTarget = SelectSetting.Companion.clamp(this.scrollTarget, 0.0f, max);
        this.scroll += (this.scrollTarget - this.scroll) * (1.0f - (float)Math.exp(-dt * 18.0f));
        if (Math.abs(this.scrollTarget - this.scroll) < 0.05f) {
            this.scroll = this.scrollTarget;
        }
        this.scroll = SelectSetting.Companion.clamp(this.scroll, 0.0f, max);
    }

    private final float updateHover(String option, boolean hovered, float dt) {
        float target = hovered ? 1.0f : 0.0f;
        Float f = this.hoverT.getOrDefault(option, Float.valueOf(0.0f));
        Intrinsics.checkNotNullExpressionValue((Object)f, (String)"getOrDefault(...)");
        float cur = ((Number)f).floatValue();
        cur += (target - cur) * (1.0f - (float)Math.exp(-dt * 16.0f));
        if (Math.abs(target - cur) < 0.01f) {
            cur = target;
        }
        ((Map)this.hoverT).put(option, Float.valueOf(cur));
        return cur;
    }

    private final void initScrollToSelected() {
        float target;
        List<String> options = this.backend.getOptions();
        int selIdx = Math.max(0, options.indexOf(this.backend.getSelected()));
        int maxTop = Math.max(0, options.size() - 6);
        int topRow = SelectSetting.Companion.clamp(selIdx - 3, 0, maxTop);
        this.scrollTarget = target = (float)topRow * 13.3f;
        this.scroll = target;
        this.lastFrameNs = 0L;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\t\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ'\u0010\r\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\r\u0010\tJ'\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\r\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0013R\u0014\u0010\u0017\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0013R\u0014\u0010\u0018\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0013R\u0014\u0010\u0019\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0013R\u0014\u0010\u001a\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0013R\u0014\u0010\u001b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0013R\u0014\u0010\u001c\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0013R\u0014\u0010\u001d\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u0013R\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010\u0013R\u0014\u0010\"\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010\u0013\u00a8\u0006#"}, d2={"Lrtx/kimiko/api/ui/settings/impl/SelectSetting.Companion;", "", "<init>", "()V", "", "yy", "top", "bottom", "edgeAlpha", "(FFF)F", "value", "min", "max", "clamp", "", "(III)I", "MAX_VISIBLE", "I", "ITEM_H", "F", "GAP", "TEXT_SIZE", "TEXT_PAD_X", "CHIP_RADIUS", "CHIP_H", "TEXT_LINE_H", "TEXT_Y_OFF", "HOVER_RATE", "INDICATOR_RATE", "SETTLE_EPS", "", "CLOSE_DELAY_MS", "J", "EDGE_FADE", "EDGE_INSET", "rtx.kimiko:kimiko"})
    private static final class Companion {
        private Companion() {
        }

        private final float edgeAlpha(float yy, float top, float bottom) {
            float a = this.clamp((yy - top) / 3.3f, 0.0f, 1.0f);
            return Math.min(a, this.clamp((bottom - yy) / 3.3f, 0.0f, 1.0f));
        }

        private final float clamp(float value, float min, float max) {
            return value < min ? min : (value > max ? max : value);
        }

        private final int clamp(int value, int min, int max) {
            return value < min ? min : (value > max ? max : value);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

