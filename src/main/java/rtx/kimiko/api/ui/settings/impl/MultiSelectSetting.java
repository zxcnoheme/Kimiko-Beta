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
import rtx.kimiko.utils.animations.Decelerate;
import rtx.kimiko.utils.animations.Direction;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 ;2\u00020\u0001:\u0001;B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0010\u0010\bJ/\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\u0018\u0010\u000bJ9\u0010\u001b\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\u001d\u0010\u000bJ7\u0010 \u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b \u0010!J7\u0010\"\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\"\u0010!J\u000f\u0010#\u001a\u00020\u0015H\u0016\u00a2\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b%\u0010\u000eJ'\u0010)\u001a\u00020\f2\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b)\u0010*J'\u0010,\u001a\u00020\f2\u0006\u0010&\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\t2\u0006\u0010(\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b,\u0010*R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010-R\u0016\u0010.\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010/R\u0014\u00101\u001a\u0002008\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b1\u00102R\u0016\u00104\u001a\u0002038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00105R0\u00108\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f06j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f`78\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00109R0\u0010:\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f06j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f`78\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u00109\u00a8\u0006<"}, d2={"Lrtx/kimiko/api/ui/settings/impl/MultiSelectSetting;", "Lrtx/kimiko/api/ui/settings/Setting;", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "backend", "<init>", "(Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;)V", "", "name", "()Ljava/lang/String;", "", "isVisible", "()Z", "", "height", "()F", "preferredWidth", "label", "x", "y", "w", "alpha", "", "render", "(FFFF)V", "hasOverlay", "Lnet/minecraft/DrawContext;", "graphics", "renderOverlay", "(Lnet/minecraft/DrawContext;FFFF)V", "isOverlayOpen", "mx", "my", "clickOverlay", "(FFFFF)Z", "click", "closeOverlay", "()V", "frameDt", "option", "hovered", "dt", "updateHover", "(Ljava/lang/String;ZF)F", "selected", "updateSel", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "open", "Z", "Lrtx/kimiko/utils/animations/Decelerate;", "dropAnim", "Lrtx/kimiko/utils/animations/Decelerate;", "", "lastFrameNs", "J", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "hoverT", "Ljava/util/HashMap;", "selT", "Companion", "rtx.kimiko:kimiko"})
public class MultiSelectSetting
implements Setting {
    @NotNull
    private static final Companion Companion = new Companion(null);
    @NotNull
    private final rtx.kimiko.api.modules.settings.impl.MultiSelectSetting backend;
    private boolean open;
    @NotNull
    private final Decelerate dropAnim;
    private long lastFrameNs;
    @NotNull
    private final HashMap<String, Float> hoverT;
    @NotNull
    private final HashMap<String, Float> selT;
    private static final float ITEM_H = 13.3f;
    private static final float GAP = 1.7f;
    private static final float TEXT_SIZE = 5.0f;
    private static final float TEXT_PAD_X = 6.7f;
    private static final float CHIP_RADIUS = 2.5f;
    private static final float CHIP_H = 11.6f;
    private static final float TEXT_LINE_H = 5.833333f;
    private static final float TEXT_Y_OFF = 2.8833337f;
    private static final float HOVER_RATE = 16.0f;
    private static final float SEL_RATE = 13.0f;

    public MultiSelectSetting(@NotNull rtx.kimiko.api.modules.settings.impl.MultiSelectSetting backend) {
        Intrinsics.checkNotNullParameter((Object)backend, (String)"backend");
        this.backend = backend;
        this.dropAnim = new Decelerate();
        this.hoverT = new HashMap();
        this.selT = new HashMap();
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
        return 6.0f + Fonts.MEDIUM.width(this.backend.getDisplayName(), 6.5f) + 6.0f + Fonts.MEDIUM.width(this.label(), 6.0f) + 10.0f + 8.0f;
    }

    private final String label() {
        return this.backend.getSelected().size() + " of " + this.backend.getOptions().size();
    }

    @Override
    public void render(float x, float y, float w, float alpha) {
        String lbl = this.label();
        float lblW = Fonts.MEDIUM.width(lbl, 6.0f) + (float)10;
        float btnX = x + w - lblW - (float)4;
        float btnY = y + 2.0f;
        RenderHelper.drawName(this.backend.getDisplayName(), x, y, btnX - (x + 6.0f) - 4.0f, alpha);
        RenderHelper.drawBtn(btnX, btnY, lblW, 12.0f, lbl, alpha);
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
        float maxTextW = 0.0f;
        List<String> options = this.backend.getOptions();
        for (String opt : options) {
            maxTextW = Math.max(maxTextW, Fonts.MEDIUM.width(this.backend.getDisplayOption(opt), 5.0f));
        }
        float dropW = maxTextW + 13.4f + 3.4f;
        float dropH = 1.7f + (float)options.size() * 13.3f;
        float dropY = RenderHelper.overlayY(y, dropH);
        String lbl = this.label();
        float lblW = Fonts.MEDIUM.width(lbl, 6.0f) + (float)10;
        float dropX = x + w - lblW - (float)4 + lblW - dropW;
        RenderHelper.drawDropBackground(dropX, dropY, dropW, dropH, dAlpha);
        float popupR = RectUtil.clientWindowRadius(2.0f, dropW, dropH);
        float edgeR = Math.min(Math.max(2.5f, popupR - 1.7f), 5.8f);
        float dt = this.frameDt();
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        float chipX = dropX + 1.7f;
        float chipW = dropW - 3.4f;
        int n = ((Collection)options).size();
        for (int i = 0; i < n; ++i) {
            float chipBottomR;
            String opt = options.get(i);
            float itemY = dropY + 1.7f + (float)i * 13.3f;
            boolean isSelected = this.backend.isSelected(opt);
            boolean hovered = !isSelected && mx >= chipX && mx <= chipX + chipW && my >= itemY && my <= itemY + 11.6f;
            float hover = this.updateHover(opt, hovered, dt);
            float sel = this.updateSel(opt, isSelected, dt);
            float chipTopR = i == 0 ? edgeR : 2.5f;
            float f = chipBottomR = i == options.size() - 1 ? edgeR : 2.5f;
            if (hover > 0.01f) {
                Render2D.rect(chipX, itemY, chipW, 11.6f, chipTopR, chipTopR, chipBottomR, chipBottomR, new Color(255, 255, 255, (int)((float)24 * hover * dAlpha)).getRGB());
            }
            if (sel > 0.01f) {
                AccentGradient.fillHorizontal(chipX, itemY, chipW, 11.6f, chipTopR, chipTopR, chipBottomR, chipBottomR, (float)220 * sel * dAlpha);
            }
            float baseAlpha = 145.0f + 50.0f * hover;
            float textAlpha = baseAlpha + (235.0f - baseAlpha) * sel;
            int textColor = new Color(255, 255, 255, (int)(textAlpha * dAlpha)).getRGB();
            String optLabel = this.backend.getDisplayOption(opt);
            float textW = Fonts.MEDIUM.width(optLabel, 5.0f);
            float textX = chipX + (chipW - textW) * 0.5f;
            Fonts.MEDIUM.draw(optLabel, textX, itemY + 2.8833337f, 5.0f, textColor);
        }
    }

    @Override
    public boolean isOverlayOpen() {
        return this.open;
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
        float maxTextW = 0.0f;
        for (String o : options) {
            maxTextW = Math.max(maxTextW, Fonts.MEDIUM.width(this.backend.getDisplayOption(o), 5.0f));
        }
        float dropW = maxTextW + 13.4f + 3.4f;
        float dropH = 1.7f + (float)options.size() * 13.3f;
        float dropY = RenderHelper.overlayY(y, dropH);
        float lblW = Fonts.MEDIUM.width(this.label(), 6.0f) + (float)10;
        float dropX = x + w - lblW - (float)4 + lblW - dropW;
        if (mx < dropX || mx > dropX + dropW || my < dropY || my > dropY + dropH) {
            return false;
        }
        int n = ((Collection)options).size();
        for (int i = 0; i < n; ++i) {
            float itemY = dropY + 1.7f + (float)i * 13.3f;
            if (!(my >= itemY) || !(my <= itemY + 11.6f)) continue;
            this.backend.toggle(options.get(i));
            return true;
        }
        return true;
    }

    @Override
    public boolean click(float x, float y, float w, float mx, float my) {
        String lbl = this.label();
        float lblW = Fonts.MEDIUM.width(lbl, 6.0f) + (float)10;
        float btnX = x + w - lblW - (float)4;
        float btnY = y + 2.0f;
        if (mx >= btnX && mx <= btnX + lblW && my >= btnY && my <= btnY + 12.0f) {
            this.open = !this.open;
            Sounds.play(this.open ? "settings_open" : "settings_close");
            this.dropAnim.setDirection(this.open ? Direction.FORWARDS : Direction.BACKWARDS);
            this.dropAnim.counter.resetCounter();
            return true;
        }
        return false;
    }

    @Override
    public void closeOverlay() {
        if (this.open) {
            this.open = false;
            Sounds.play("settings_close");
            this.dropAnim.setDirection(Direction.BACKWARDS);
            this.dropAnim.counter.resetCounter();
        }
    }

    private final float frameDt() {
        long now = System.nanoTime();
        float dt = this.lastFrameNs == 0L ? 0.0f : Math.min(0.05f, (float)(now - this.lastFrameNs) / 1.0E9f);
        this.lastFrameNs = now;
        return dt;
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

    private final float updateSel(String option, boolean selected, float dt) {
        float target = selected ? 1.0f : 0.0f;
        Float f = this.selT.getOrDefault(option, Float.valueOf(target));
        Intrinsics.checkNotNullExpressionValue((Object)f, (String)"getOrDefault(...)");
        float cur = ((Number)f).floatValue();
        cur += (target - cur) * (1.0f - (float)Math.exp(-dt * 13.0f));
        if (Math.abs(target - cur) < 0.01f) {
            cur = target;
        }
        ((Map)this.selT).put(option, Float.valueOf(cur));
        return cur;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\f\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u0006R\u0014\u0010\f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u0006R\u0014\u0010\u000e\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0006\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/ui/settings/impl/MultiSelectSetting.Companion;", "", "<init>", "()V", "", "ITEM_H", "F", "GAP", "TEXT_SIZE", "TEXT_PAD_X", "CHIP_RADIUS", "CHIP_H", "TEXT_LINE_H", "TEXT_Y_OFF", "HOVER_RATE", "SEL_RATE", "rtx.kimiko:kimiko"})
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

