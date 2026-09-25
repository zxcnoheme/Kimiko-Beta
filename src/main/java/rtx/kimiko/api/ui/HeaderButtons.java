/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.ui.ClientLanguage;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.theme.AccentGradient;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.animations.Decelerate;
import rtx.kimiko.utils.animations.Direction;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u001e\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\t\n\u0002\b\t\u0018\u0000 c2\u00020\u0001:\u0001cB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\u000b\u001a\u00020\b\u00a2\u0006\u0004\b\u000b\u0010\nJ\r\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0003J\r\u0010\u0011\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0011\u0010\u0003JM\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\b\u00a2\u0006\u0004\b\u001d\u0010\u001eJO\u0010%\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b%\u0010&JG\u0010'\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b'\u0010(J\u001d\u0010)\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004\u00a2\u0006\u0004\b)\u0010*J\u0015\u0010,\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\f\u00a2\u0006\u0004\b,\u0010-J'\u00100\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\f2\u0006\u0010.\u001a\u00020\u00042\u0006\u0010/\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b0\u00101J\u001d\u00102\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004\u00a2\u0006\u0004\b2\u00103J\u001d\u00104\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0004\u00a2\u0006\u0004\b4\u00105J\u001f\u00108\u001a\u00020\u000f2\u0006\u00106\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b8\u00109J\r\u0010:\u001a\u00020\u0004\u00a2\u0006\u0004\b:\u0010;J\r\u0010<\u001a\u00020\u0004\u00a2\u0006\u0004\b<\u0010;J\r\u0010=\u001a\u00020\u0004\u00a2\u0006\u0004\b=\u0010;J\r\u0010>\u001a\u00020\u0004\u00a2\u0006\u0004\b>\u0010;J\r\u0010?\u001a\u00020\u0004\u00a2\u0006\u0004\b?\u0010;J\u001f\u0010C\u001a\u00020\b2\b\u0010A\u001a\u0004\u0018\u00010@2\u0006\u0010B\u001a\u00020\f\u00a2\u0006\u0004\bC\u0010DJ\u000f\u0010E\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bE\u0010;R\u0014\u0010G\u001a\u00020F8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010HR\u0016\u0010I\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bI\u0010JR\u0016\u0010K\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010JR\u0016\u0010L\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bL\u0010JR\u0016\u0010M\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010JR\u0016\u0010N\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bN\u0010JR\u0016\u0010O\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bO\u0010JR\u0016\u0010P\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010JR\u0016\u0010Q\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u0010JR\u0016\u0010R\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bR\u0010JR\u0016\u0010S\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010JR\u0016\u0010T\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010UR\u0016\u0010V\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010WR\u0016\u0010X\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010JR\u0016\u0010Y\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010JR\u0016\u0010Z\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010UR\u0016\u0010\\\u001a\u00020[8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\\\u0010]R\u0014\u0010^\u001a\u00020@8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b^\u0010_R\u0016\u0010`\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010JR\u0016\u0010a\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010UR\u0016\u0010b\u001a\u00020[8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010]\u00a8\u0006d"}, d2={"Lrtx/kimiko/api/ui/HeaderButtons;", "", "<init>", "()V", "", "height", "width", "(F)F", "", "isVisible", "()Z", "isOpen", "", "shareModal", "()I", "", "close", "closeSilent", "Lnet/minecraft/DrawContext;", "graphics", "x", "y", "alpha", "mouseX", "mouseY", "dt", "render", "(Lnet/minecraft/DrawContext;FFFFFFF)V", "value", "setActiveA", "(Z)V", "w", "h", "", "glyph", "hover", "active", "drawButton", "(FFFFLjava/lang/String;FFF)V", "drawLanguageButton", "(FFFFFFF)V", "buttonAt", "(FF)I", "index", "toggleStub", "(I)V", "anchorX", "anchorY", "toggle", "(IFF)V", "clickModal", "(FF)Z", "renderModal", "(Lnet/minecraft/DrawContext;F)V", "drawY", "a", "renderLanguageModal", "(FF)V", "modalX", "()F", "modalY", "modalW", "modalH", "blurPhase", "", "dst", "offset", "writeBlurRect", "([FI)Z", "output", "Lrtx/kimiko/utils/animations/Decelerate;", "anim", "Lrtx/kimiko/utils/animations/Decelerate;", "ax", "F", "ay", "aw", "ah", "vx", "vy", "vw", "vh", "hoverA", "hoverV", "open", "Z", "openIndex", "I", "mx", "my", "activeA", "", "lastFrameNs", "J", "langHover", "[F", "langIndicator", "pendingClose", "closeAtMs", "Companion", "rtx.kimiko:kimiko"})
public final class HeaderButtons {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Decelerate anim = new Decelerate();
    private float ax;
    private float ay;
    private float aw;
    private float ah;
    private float vx;
    private float vy;
    private float vw;
    private float vh;
    private float hoverA;
    private float hoverV;
    private boolean open;
    private int openIndex = -1;
    private float mx;
    private float my;
    private boolean activeA;
    private long lastFrameNs;
    @NotNull
    private final float[] langHover = new float[ClientLanguage.OPTIONS.length];
    private float langIndicator = Float.NaN;
    private boolean pendingClose;
    private long closeAtMs;
    @NotNull
    private static final String GLYPH_A = "a";
    @NotNull
    private static final String GLYPH_V = "v";
    private static final float ICON_SIZE = 7.5f;
    private static final float GAP = 4.0f;
    private static final float MODAL_W = 132.0f;
    private static final float MODAL_H = 76.0f;
    private static final float LANG_BUTTON_TEXT_SIZE = 5.6f;
    private static final float LANG_W = 84.0f;
    private static final float LANG_ITEM_H = 13.3f;
    private static final float LANG_GAP = 1.7f;
    private static final float LANG_TEXT_SIZE = 5.0f;
    private static final float LANG_CHIP_RADIUS = 2.5f;
    private static final float LANG_CHIP_H = 11.6f;
    private static final float LANG_TEXT_LINE_H = 5.833333f;
    private static final float LANG_SETTLE_EPS = 0.5f;
    private static final long LANG_CLOSE_DELAY_MS = 50L;

    public HeaderButtons() {
        this.anim.setMs(220);
        this.anim.setValue(1.0);
        this.anim.setDirection(Direction.BACKWARDS);
        this.anim.counter.setTime(System.currentTimeMillis() - (long)10000);
    }

    public final float width(float height) {
        return height * 2.0f + 4.0f;
    }

    public final boolean isVisible() {
        return this.openIndex >= 0 && this.output() > 0.01f;
    }

    public final boolean isOpen() {
        return this.open;
    }

    public final int shareModal() {
        return this.open ? this.openIndex : -1;
    }

    public final void close() {
        if (!this.open) {
            return;
        }
        this.open = false;
        this.pendingClose = false;
        this.closeAtMs = 0L;
        this.anim.setDirection(Direction.BACKWARDS);
        this.anim.counter.resetCounter();
    }

    public final void closeSilent() {
        this.open = false;
        this.openIndex = -1;
        this.pendingClose = false;
        this.closeAtMs = 0L;
        this.anim.setValue(1.0);
        this.anim.setDirection(Direction.BACKWARDS);
        this.anim.counter.setTime(System.currentTimeMillis() - (long)10000);
    }

    public final void render(@NotNull DrawContext graphics, float x, float y, float height, float alpha, float mouseX, float mouseY, float dt) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        this.ax = x;
        this.ay = y;
        this.aw = height;
        this.ah = height;
        this.vx = x + height + 4.0f;
        this.vy = y;
        this.vw = height;
        this.vh = height;
        float rate = 1.0f - (float)Math.exp(-dt * 16.0f);
        this.hoverA += ((HeaderButtons.Companion.contains(this.ax, this.ay, this.aw, this.ah, mouseX, mouseY) ? 1.0f : 0.0f) - this.hoverA) * rate;
        this.hoverV += ((HeaderButtons.Companion.contains(this.vx, this.vy, this.vw, this.vh, mouseX, mouseY) ? 1.0f : 0.0f) - this.hoverV) * rate;
        this.drawButton(this.ax, this.ay, this.aw, this.ah, GLYPH_A, this.hoverA, this.activeA ? 1.0f : 0.0f, alpha);
        this.drawLanguageButton(this.vx, this.vy, this.vw, this.vh, this.hoverV, this.open && this.openIndex == 1 ? 1.0f : 0.0f, alpha);
    }

    public final void setActiveA(boolean value) {
        this.activeA = value;
    }

    private final void drawButton(float x, float y, float w, float h, String glyph, float hover, float active, float alpha) {
        float glow = Math.max(hover, active);
        Render2D.rect(x, y, w, h, 4.0f, HeaderButtons.Companion.col(0, 0, 0, ((float)40 + (float)14 * glow) * alpha));
        int rgb = HeaderButtons.Companion.mixRgb(0xB4B4B4, ClientAccent.accentAt(255.0f, x + w * 0.5f, y + h * 0.5f) & 0xFFFFFF, glow);
        int a = HeaderButtons.Companion.clampA(((float)130 + (float)100 * glow) * alpha);
        float iconW = Fonts.KIMIKO.msdfWidth(glyph, 7.5f);
        Fonts.KIMIKO.msdf(glyph, x + (w - iconW) * 0.5f, y + (h - 7.5f) * 0.5f + 0.3f, 7.5f, a << 24 | rgb);
    }

    private final void drawLanguageButton(float x, float y, float w, float h, float hover, float active, float alpha) {
        float glow = Math.max(hover, active);
        Render2D.rect(x, y, w, h, 4.0f, HeaderButtons.Companion.col(0, 0, 0, ((float)40 + (float)14 * glow) * alpha));
        int rgb = HeaderButtons.Companion.mixRgb(0xB4B4B4, ClientAccent.accentAt(255.0f, x + w * 0.5f, y + h * 0.5f) & 0xFFFFFF, glow);
        int a = HeaderButtons.Companion.clampA(((float)150 + (float)90 * glow) * alpha);
        String label = ClientLanguage.shortName();
        float textW = Fonts.SEMIBOLD.width(label, 5.6f);
        Fonts.SEMIBOLD.draw(label, x + (w - textW) * 0.5f, y + (h - 5.6f) * 0.5f - 0.2f, 5.6f, a << 24 | rgb);
    }

    public final int buttonAt(float mouseX, float mouseY) {
        if (HeaderButtons.Companion.contains(this.ax, this.ay, this.aw, this.ah, mouseX, mouseY)) {
            return 0;
        }
        if (this.vw > 0.0f && HeaderButtons.Companion.contains(this.vx, this.vy, this.vw, this.vh, mouseX, mouseY)) {
            return 1;
        }
        return -1;
    }

    public final void toggleStub(int index) {
        float anchorX = index == 0 ? this.ax + this.aw * 0.5f : this.vx + this.vw * 0.5f;
        float anchorY = index == 0 ? this.ay + this.ah : this.vy + this.vh;
        this.toggle(index, anchorX, anchorY);
    }

    private final void toggle(int index, float anchorX, float anchorY) {
        if (this.open && this.openIndex == index) {
            this.close();
            return;
        }
        this.openIndex = index;
        this.open = true;
        this.pendingClose = false;
        this.closeAtMs = 0L;
        float w = this.modalW();
        this.mx = HeaderButtons.Companion.clampF(anchorX - w * 0.5f, 5.0f, Position.Companion.screenWidth() - w - 5.0f);
        this.my = anchorY + 4.0f;
        if (index == 1) {
            this.langIndicator = (float)ClientLanguage.index() * 13.3f;
            Arrays.fill(this.langHover, 0.0f);
            this.lastFrameNs = 0L;
        }
        this.anim.setDirection(Direction.FORWARDS);
        this.anim.counter.resetCounter();
        Sounds.play("module_settings_open");
    }

    public final boolean clickModal(float mouseX, float mouseY) {
        if (!this.open) {
            return false;
        }
        float w = this.modalW();
        float h = this.modalH();
        if (mouseX < this.mx || mouseX > this.mx + w || mouseY < this.my || mouseY > this.my + h) {
            this.close();
            return false;
        }
        if (this.openIndex == 1) {
            float chipX = this.mx + 1.7f;
            float chipW = w - 3.4f;
            int n = ClientLanguage.OPTIONS.length;
            for (int i = 0; i < n; ++i) {
                float itemY = this.my + 1.7f + (float)i * 13.3f;
                if (!(mouseX >= chipX) || !(mouseX <= chipX + chipW) || !(mouseY >= itemY) || !(mouseY <= itemY + 11.6f)) continue;
                ClientLanguage.set(ClientLanguage.OPTIONS[i]);
                this.pendingClose = true;
                this.closeAtMs = 0L;
                return true;
            }
        }
        return true;
    }

    public final void renderModal(@NotNull DrawContext graphics, float alpha) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        if (this.openIndex < 0) {
            return;
        }
        float t = this.output();
        if (t <= 0.01f) {
            if (!this.open) {
                this.openIndex = -1;
            }
            return;
        }
        float a = t * alpha;
        float drawY = this.my + (1.0f - t) * 4.0f;
        if (this.openIndex == 1) {
            this.renderLanguageModal(drawY, a);
            return;
        }
        RenderHelper.drawDropBackground(this.mx, drawY, 132.0f, 76.0f, a);
        String title = I18n.tr("Действие A");
        float titleW = Fonts.MEDIUM.width(title, 7.0f);
        Fonts.MEDIUM.draw(title, this.mx + (132.0f - titleW) * 0.5f, drawY + 9.0f, 7.0f, ColorEngine.multAlpha(-1, 0.92f * a));
        String hint = I18n.tr("Заглушка");
        float hintW = Fonts.MEDIUM.width(hint, 6.0f);
        Fonts.MEDIUM.draw(hint, this.mx + (132.0f - hintW) * 0.5f, drawY + 22.0f, 6.0f, ColorEngine.multAlpha(-1, 0.55f * a));
        float glyphSize = 18.0f;
        float gw = Fonts.KIMIKO.msdfWidth(GLYPH_A, glyphSize);
        AccentGradient.msdfIcon(Fonts.KIMIKO, GLYPH_A, this.mx + (132.0f - gw) * 0.5f, drawY + 36.0f, glyphSize, (float)210 * a, 0.5f);
    }

    private final void renderLanguageModal(float drawY, float a) {
        long now = System.nanoTime();
        float dt = this.lastFrameNs == 0L ? 0.0f : Math.min(0.05f, (float)(now - this.lastFrameNs) / 1.0E9f);
        this.lastFrameNs = now;
        float w = 84.0f;
        float h = this.modalH();
        RenderHelper.drawDropBackground(this.mx, drawY, w, h, a);
        float chipX = this.mx + 1.7f;
        float chipW = w - 3.4f;
        float mouseX = Position.Companion.mouseX();
        float mouseY = Position.Companion.mouseY();
        int selIdx = ClientLanguage.index();
        float target = (float)selIdx * 13.3f;
        if (Float.isNaN(this.langIndicator)) {
            this.langIndicator = target;
        } else {
            this.langIndicator += (target - this.langIndicator) * (1.0f - (float)Math.exp(-dt * 13.0f));
            if (Math.abs(target - this.langIndicator) < 0.05f) {
                this.langIndicator = target;
            }
        }
        AccentGradient.fillHorizontal(chipX, drawY + 1.7f + this.langIndicator, chipW, 11.6f, 2.5f, (float)220 * a);
        int n = ClientLanguage.OPTIONS.length;
        for (int i = 0; i < n; ++i) {
            float hover;
            String opt = ClientLanguage.OPTIONS[i];
            float itemY = drawY + 1.7f + (float)i * 13.3f;
            boolean isSel = i == selIdx;
            boolean hovered = !isSel && mouseX >= chipX && mouseX <= chipX + chipW && mouseY >= itemY && mouseY <= itemY + 11.6f;
            float hoverTarget = hovered ? 1.0f : 0.0f;
            float[] fArray = this.langHover;
            int n2 = i;
            fArray[n2] = fArray[n2] + (hoverTarget - this.langHover[i]) * (1.0f - (float)Math.exp(-dt * 16.0f));
            if (Math.abs(hoverTarget - this.langHover[i]) < 0.01f) {
                this.langHover[i] = hoverTarget;
            }
            if ((hover = this.langHover[i]) > 0.01f) {
                Render2D.rect(chipX, itemY, chipW, 11.6f, 2.5f, HeaderButtons.Companion.col(255, 255, 255, (float)24 * hover * a));
            }
            float textAlpha = isSel ? 235.0f : 145.0f + 50.0f * hover;
            float textW = Fonts.MEDIUM.width(opt, 5.0f);
            Fonts.MEDIUM.draw(opt, chipX + (chipW - textW) * 0.5f, itemY + 2.8833337f, 5.0f, HeaderButtons.Companion.col(255, 255, 255, textAlpha * a));
        }
        if (this.pendingClose && this.open) {
            boolean settled;
            boolean bl = settled = Math.abs(target - this.langIndicator) < 0.5f;
            if (!settled) {
                this.closeAtMs = 0L;
            } else {
                long nowMs = System.currentTimeMillis();
                if (this.closeAtMs == 0L) {
                    this.closeAtMs = nowMs + 50L;
                } else if (nowMs >= this.closeAtMs) {
                    this.close();
                }
            }
        }
    }

    public final float modalX() {
        return this.mx;
    }

    public final float modalY() {
        return this.my;
    }

    public final float modalW() {
        return this.openIndex == 1 ? 84.0f : 132.0f;
    }

    public final float modalH() {
        return this.openIndex == 1 ? 1.7f + (float)ClientLanguage.OPTIONS.length * 13.3f : 76.0f;
    }

    public final float blurPhase() {
        if (this.openIndex < 0) {
            return 0.0f;
        }
        float t = this.output();
        return t <= 0.01f ? 0.0f : Math.max(0.0f, Math.min(1.0f, 1.0f - t));
    }

    public final boolean writeBlurRect(@Nullable float[] dst, int offset) {
        if (this.openIndex < 0 || dst == null || offset + 6 > dst.length) {
            return false;
        }
        float t = this.output();
        if (t <= 0.01f) {
            return false;
        }
        dst[offset] = this.mx;
        dst[offset + 1] = this.my + (1.0f - t) * 4.0f;
        dst[offset + 2] = this.modalW();
        dst[offset + 3] = this.modalH();
        dst[offset + 4] = 1.0f;
        dst[offset + 5] = Math.max(0.0f, Math.min(1.0f, 1.0f - t));
        return true;
    }

    private final float output() {
        Double d = this.anim.getOutput();
        return (float)(d != null ? d : 0.0);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J?\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\f\u0010\rJ'\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ/\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010#R\u0014\u0010%\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010&R\u0014\u0010(\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010&R\u0014\u0010)\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010&R\u0014\u0010*\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010&R\u0014\u0010+\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010&R\u0014\u0010,\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010&R\u0014\u0010-\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010&R\u0014\u0010.\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010&R\u0014\u0010/\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u0010&R\u0014\u00100\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u0010&R\u0014\u00101\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u0010&R\u0014\u00102\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u0010&R\u0014\u00104\u001a\u0002038\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u00105\u00a8\u00066"}, d2={"Lrtx/kimiko/api/ui/HeaderButtons.Companion;", "", "<init>", "()V", "", "x", "y", "w", "h", "mouseX", "mouseY", "", "contains", "(FFFFFF)Z", "", "from", "to", "value", "mixRgb", "(IIF)I", "v", "min", "max", "clampF", "(FFF)F", "a", "clampA", "(F)I", "r", "g", "b", "col", "(IIIF)I", "", "GLYPH_A", "Ljava/lang/String;", "GLYPH_V", "ICON_SIZE", "F", "GAP", "MODAL_W", "MODAL_H", "LANG_BUTTON_TEXT_SIZE", "LANG_W", "LANG_ITEM_H", "LANG_GAP", "LANG_TEXT_SIZE", "LANG_CHIP_RADIUS", "LANG_CHIP_H", "LANG_TEXT_LINE_H", "LANG_SETTLE_EPS", "", "LANG_CLOSE_DELAY_MS", "J", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final boolean contains(float x, float y, float w, float h, float mouseX, float mouseY) {
            return mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h;
        }

        private final int mixRgb(int from, int to, float value) {
            float t = value < 0.0f ? 0.0f : (value > 1.0f ? 1.0f : value);
            int ar = from >> 16 & 0xFF;
            int ag = from >> 8 & 0xFF;
            int ab = from & 0xFF;
            int br = to >> 16 & 0xFF;
            int bg = to >> 8 & 0xFF;
            int bb = to & 0xFF;
            return MathKt.roundToInt((float)((float)ar + (float)(br - ar) * t)) << 16 | MathKt.roundToInt((float)((float)ag + (float)(bg - ag) * t)) << 8 | MathKt.roundToInt((float)((float)ab + (float)(bb - ab) * t));
        }

        private final float clampF(float v, float min, float max) {
            return v < min ? min : Math.min(v, max);
        }

        private final int clampA(float a) {
            return Math.max(0, Math.min(255, MathKt.roundToInt((float)a)));
        }

        private final int col(int r, int g, int b, float a) {
            int alpha = this.clampA(a);
            if (alpha <= 0) {
                return 0;
            }
            return alpha << 24 | r << 16 | g << 8 | b;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

