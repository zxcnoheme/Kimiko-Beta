/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  kotlin.text.StringsKt
 *  net.minecraft.client.input.CharInput
 *  net.minecraft.client.input.KeyInput
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.glfw.GLFW
 */
package rtx.kimiko.api.ui.module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import net.minecraft.client.input.CharInput;
import net.minecraft.client.input.KeyInput;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import rtx.kimiko.IMinecraft;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\f\n\u0002\b \n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 v2\u00020\u0001:\u0001vB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0007\u00a2\u0006\u0004\b\n\u0010\tJ\u0017\u0010\f\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u000f\u0010\rJ\u0015\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0017\u0010\u0003J\r\u0010\u0018\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0018\u0010\u0003JU\u0010#\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u0010\u00a2\u0006\u0004\b#\u0010$J%\u0010(\u001a\u00020\u00072\u0006\u0010 \u001a\u00020%2\u0006\u0010!\u001a\u00020%2\u0006\u0010'\u001a\u00020&\u00a2\u0006\u0004\b(\u0010)J\u0015\u0010*\u001a\u00020\u00142\u0006\u0010'\u001a\u00020&\u00a2\u0006\u0004\b*\u0010+J\u0015\u0010.\u001a\u00020\u00072\u0006\u0010-\u001a\u00020,\u00a2\u0006\u0004\b.\u0010/J\u0015\u00101\u001a\u00020\u00072\u0006\u0010-\u001a\u000200\u00a2\u0006\u0004\b1\u00102J\u000f\u00103\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b3\u0010\u0003J\u000f\u00104\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b4\u0010\u0003J\u000f\u00105\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b5\u0010\u0003J\u000f\u00106\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b6\u0010\u0003J\u0017\u00108\u001a\u00020\u00142\u0006\u00107\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b8\u00109J\u0017\u0010:\u001a\u00020\u00142\u0006\u00107\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b:\u00109J\u001f\u0010=\u001a\u00020\u00142\u0006\u0010;\u001a\u00020&2\u0006\u0010<\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b=\u0010>J\u0017\u0010@\u001a\u00020&2\u0006\u0010?\u001a\u00020&H\u0002\u00a2\u0006\u0004\b@\u0010AJ\u0017\u0010B\u001a\u00020&2\u0006\u0010?\u001a\u00020&H\u0002\u00a2\u0006\u0004\bB\u0010AJ\u0017\u0010E\u001a\u00020\u00072\u0006\u0010D\u001a\u00020CH\u0002\u00a2\u0006\u0004\bE\u0010FJ'\u0010J\u001a\u00020\u00142\u0006\u0010G\u001a\u00020&2\u0006\u0010H\u001a\u00020&2\u0006\u0010I\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bJ\u0010KJ\u000f\u0010L\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\bL\u0010\tJ\u000f\u0010M\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bM\u0010\u0006J\u000f\u0010N\u001a\u00020&H\u0002\u00a2\u0006\u0004\bN\u0010OJ\u000f\u0010P\u001a\u00020&H\u0002\u00a2\u0006\u0004\bP\u0010OJ\u000f\u0010Q\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\bQ\u0010\u0003J\u000f\u0010R\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\bR\u0010\u0003J\u0017\u0010S\u001a\u00020&2\u0006\u0010 \u001a\u00020%H\u0002\u00a2\u0006\u0004\bS\u0010TJ\u000f\u0010U\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\bU\u0010\u0003J\u0017\u0010W\u001a\u00020\u00102\u0006\u0010V\u001a\u00020&H\u0002\u00a2\u0006\u0004\bW\u0010XR\u0016\u0010Y\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010ZR\u0016\u0010\f\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\f\u0010ZR\u0016\u0010[\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b[\u0010ZR\u0016\u0010\u0011\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\\R\u0016\u0010]\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u0016\u0010_\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010^R\u0016\u0010`\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010aR\u0016\u0010b\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010aR\u0016\u0010c\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010aR\u0016\u0010e\u001a\u00020d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010fR\u0016\u0010g\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bg\u0010\\R\u0016\u0010h\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bh\u0010\\R\u0016\u0010i\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bi\u0010\\R\u0016\u0010j\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bj\u0010\\R\u0016\u0010k\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bk\u0010\\R\u0016\u0010l\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bl\u0010\\R\u0016\u0010m\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bm\u0010\\R\u0016\u0010n\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bn\u0010\\R\u0016\u0010o\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bo\u0010\\R\u0016\u0010p\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bp\u0010\\R\u0016\u0010q\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bq\u0010^R$\u0010t\u001a\u0012\u0012\u0004\u0012\u00020\u00100rj\b\u0012\u0004\u0012\u00020\u0010`s8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bt\u0010u\u00a8\u0006w"}, d2={"Lrtx/kimiko/api/ui/module/SearchField;", "", "<init>", "()V", "", "getText", "()Ljava/lang/String;", "", "isTyping", "()Z", "hasText", "value", "placeholder", "(Ljava/lang/String;)Lrtx/kimiko/api/ui/module/SearchField;", "glyph", "icon", "", "rightPadding", "(F)Lrtx/kimiko/api/ui/module/SearchField;", "t", "", "setText", "(Ljava/lang/String;)V", "focus", "blur", "Lnet/minecraft/DrawContext;", "g", "x", "y", "width", "height", "alpha", "mouseX", "mouseY", "dt", "render", "(Lnet/minecraft/DrawContext;FFFFFFFF)V", "", "", "button", "mouseClicked", "(DDI)Z", "mouseReleased", "(I)V", "Lnet/minecraft/CharInput;", "event", "charTyped", "(Lnet/minecraft/CharInput;)Z", "Lnet/minecraft/KeyInput;", "keyPressed", "(Lnet/minecraft/KeyInput;)Z", "pasteFromClipboard", "copyToClipboard", "cutToClipboard", "selectAllText", "ctrlWord", "deletePrevious", "(Z)V", "deleteNext", "target", "withSelection", "moveCursorTo", "(IZ)V", "from", "findPreviousWordBoundary", "(I)I", "findNextWordBoundary", "", "c", "isWordCharacter", "(C)Z", "start", "end", "replacement", "replaceText", "(IILjava/lang/String;)V", "hasSelection", "getSelectedText", "getStartOfSelection", "()I", "getEndOfSelection", "clearSelection", "deleteSelectedText", "cursorIndexAt", "(D)I", "updateXOffset", "i", "charProgress", "(I)F", "text", "Ljava/lang/String;", "iconGlyph", "F", "typing", "Z", "dragging", "cursorPosition", "I", "selectionStart", "selectionEnd", "", "lastClickTime", "J", "xOffset", "bx", "by", "bw", "bh", "textStartX", "textVisibleW", "hoverT", "focusT", "animCursorX", "cursorSnap", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "charAnim", "Ljava/util/ArrayList;", "Companion", "rtx.kimiko:kimiko"})
public final class SearchField {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private String text = "";
    @NotNull
    private String placeholder = "Поиск...";
    @NotNull
    private String iconGlyph = "q";
    private float rightPadding;
    private boolean typing;
    private boolean dragging;
    private int cursorPosition;
    private int selectionStart = -1;
    private int selectionEnd = -1;
    private long lastClickTime;
    private float xOffset;
    private float bx;
    private float by;
    private float bw;
    private float bh;
    private float textStartX;
    private float textVisibleW;
    private float hoverT;
    private float focusT;
    private float animCursorX;
    private boolean cursorSnap = true;
    @NotNull
    private final ArrayList<Float> charAnim = new ArrayList();
    private static final float SIZE = 7.0f;
    private static final float PAD_X = 5.0f;
    private static final float REVEAL_SPEED = 3.6f;
    private static final float REVEAL_STAGGER = 0.14f;

    @NotNull
    public final String getText() {
        return this.text;
    }

    public final boolean isTyping() {
        return this.typing;
    }

    public final boolean hasText() {
        return ((CharSequence)this.text).length() > 0;
    }

    @NotNull
    public final SearchField placeholder(@Nullable String value) {
        String string = value;
        if (string == null) {
            string = "";
        }
        this.placeholder = string;
        return this;
    }

    @NotNull
    public final SearchField icon(@Nullable String glyph) {
        String string = glyph;
        if (string == null) {
            string = "";
        }
        this.iconGlyph = string;
        return this;
    }

    @NotNull
    public final SearchField rightPadding(float value) {
        this.rightPadding = Math.max(0.0f, value);
        return this;
    }

    public final void setText(@Nullable String t) {
        String string = t;
        if (string == null) {
            string = "";
        }
        this.text = string;
        this.cursorPosition = Math.min(this.cursorPosition, this.text.length());
        this.clearSelection();
        this.dragging = false;
        this.cursorSnap = true;
        this.charAnim.clear();
        int n = ((CharSequence)this.text).length();
        for (int i = 0; i < n; ++i) {
            this.charAnim.add(Float.valueOf(1.0f));
        }
    }

    public final void focus() {
        this.typing = true;
        this.dragging = false;
        this.cursorPosition = this.text.length();
        this.clearSelection();
        this.cursorSnap = true;
    }

    public final void blur() {
        this.typing = false;
        this.dragging = false;
        this.clearSelection();
    }

    public final void render(@NotNull DrawContext g, float x, float y, float width, float height, float alpha, float mouseX, float mouseY, float dt) {
        Intrinsics.checkNotNullParameter((Object)g, (String)"g");
        this.bx = x;
        this.by = y;
        this.bw = width;
        this.bh = height;
        this.cursorPosition = SearchField.Companion.clamp(this.cursorPosition, 0, this.text.length());
        boolean hovered = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
        float rate = 1.0f - (float)Math.exp(-dt * 16.0f);
        float focusRate = 1.0f - (float)Math.exp(-dt * 11.0f);
        this.hoverT += ((hovered ? 1.0f : 0.0f) - this.hoverT) * rate;
        this.focusT += ((this.typing ? 1.0f : 0.0f) - this.focusT) * focusRate;
        float glow = Math.max(this.hoverT, this.focusT);
        int accentRgb = ClientAccent.accentAt(255.0f, x + width * 0.5f, y + height * 0.5f) & 0xFFFFFF;
        Render2D.rect(x, y, width, height, 4.0f, SearchField.Companion.col(0, 0, 0, ((float)40 + (float)12 * glow) * alpha));
        Color outlineLeft = new Color(9, 9, 9, 0);
        Color outlineRight = new Color(9, 12, 14, 0);
        int outL = SearchField.Companion.withAlpha(outlineLeft, alpha);
        int outR = SearchField.Companion.withAlpha(outlineRight, alpha);
        Render2D.outline(x, y, width, height, 4.0f, 0.8f, outL, outR, outR, outL);
        float iconSize = 7.5f;
        float iconX = x + 5.0f;
        if (((CharSequence)this.iconGlyph).length() == 0) {
            this.textStartX = x + 5.0f + 1.0f;
        } else {
            float iconY = y + (height - iconSize) * 0.5f + 0.3f;
            int iconRgb = SearchField.Companion.mixRgb(0xB4B4B4, accentRgb, glow);
            int iconA = SearchField.Companion.clampA(((float)125 + (float)95 * glow) * alpha);
            Fonts.KIMIKO.msdf(this.iconGlyph, iconX, iconY, iconSize, iconA << 24 | iconRgb);
            this.textStartX = iconX + iconSize + 4.0f;
        }
        float innerRight = x + width - 5.0f - this.rightPadding;
        this.textVisibleW = Math.max(4.0f, innerRight - this.textStartX);
        this.updateXOffset();
        float baseX = this.textStartX - this.xOffset;
        float textY = y + (height - 7.0f) * 0.5f - 0.5f;
        Render2D.pushScissor(g, this.textStartX - 2.0f, y, this.textVisibleW + 2.0f, height);
        if (this.typing && this.hasSelection()) {
            int s = this.getStartOfSelection();
            int e = this.getEndOfSelection();
            String string = this.text.substring(0, s);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            float sx = baseX + SearchField.Companion.w(string);
            String string2 = this.text.substring(0, e);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
            float ex = baseX + SearchField.Companion.w(string2);
            Render2D.rect(sx, y + 2.5f, ex - sx, height - 5.0f, 1.5f, SearchField.Companion.col(60, 128, 240, (float)165 * alpha));
        }
        float reveal = dt * 3.6f;
        boolean anyAnimating = false;
        int ex = ((Collection)this.charAnim).size();
        for (int i = 0; i < ex; ++i) {
            Float f = this.charAnim.get(i);
            Intrinsics.checkNotNullExpressionValue((Object)f, (String)"get(...)");
            float p = ((Number)f).floatValue();
            if (!(p < 1.0f)) continue;
            this.charAnim.set(i, Float.valueOf(Math.min(1.0f, p + reveal)));
            anyAnimating = true;
        }
        if (((CharSequence)this.text).length() > 0) {
            int textColor = SearchField.Companion.col(255, 255, 255, (float)235 * alpha);
            if (!anyAnimating) {
                Fonts.MEDIUM.draw(this.text, baseX, textY, 7.0f, textColor);
            } else {
                float cy = textY + 3.5f;
                int n = ((CharSequence)this.text).length();
                for (int i = 0; i < n; ++i) {
                    float p = SearchField.Companion.clamp01(this.charProgress(i));
                    if (p <= 0.0f) continue;
                    String string = this.text.substring(0, i);
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                    float xi = baseX + SearchField.Companion.w(string);
                    String ch = String.valueOf(this.text.charAt(i));
                    int a = SearchField.Companion.clampA((float)235 * alpha * p);
                    if (a <= 0) continue;
                    int cc = a << 24 | 0xFFFFFF;
                    if (p >= 1.0f) {
                        Fonts.MEDIUM.draw(ch, xi, textY, 7.0f, cc);
                        continue;
                    }
                    float s = SearchField.Companion.easeOutBack(p);
                    float cx = xi + SearchField.Companion.w(ch) * 0.5f;
                    g.getMatrices().pushMatrix();
                    g.getMatrices().translate(cx, cy);
                    g.getMatrices().scale(s, s);
                    g.getMatrices().translate(-cx, -cy);
                    Fonts.MEDIUM.draw(ch, xi, textY, 7.0f, cc);
                    g.getMatrices().popMatrix();
                }
            }
        } else {
            float ph = 1.0f - this.focusT;
            if (ph > 0.01f && ((CharSequence)this.placeholder).length() > 0) {
                Fonts.MEDIUM.draw(I18n.tr(this.placeholder), baseX, textY, 7.0f, SearchField.Companion.col(255, 255, 255, (float)95 * alpha * ph));
            }
        }
        Render2D.popScissor(g);
        String string = this.text.substring(0, this.cursorPosition);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
        float targetCursorX = SearchField.Companion.w(string);
        if (this.cursorSnap || !this.typing) {
            this.animCursorX = targetCursorX;
            this.cursorSnap = false;
        } else {
            this.animCursorX += (targetCursorX - this.animCursorX) * (1.0f - (float)Math.exp(-dt * 20.0f));
        }
        long now = System.currentTimeMillis();
        if (this.focusT > 0.01f && !this.hasSelection()) {
            float pulse = (float)(Math.sin((double)now / 200.0) * 0.5 + 0.5);
            float cx = baseX + this.animCursorX;
            Render2D.rect(cx, y + 2.5f, 0.6f, height - 5.0f, 0.0f, SearchField.Companion.col(255, 255, 255, ((float)70 + (float)185 * pulse) * this.focusT * alpha));
        }
        if (this.dragging) {
            int newCursor = this.cursorIndexAt(mouseX);
            if (this.selectionStart == -1) {
                this.selectionStart = this.cursorPosition;
            }
            this.selectionEnd = this.cursorPosition = newCursor;
            if (this.selectionStart == this.selectionEnd) {
                this.clearSelection();
            }
        }
    }

    public final boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean hovered;
        boolean bl = hovered = mouseX >= (double)this.bx && mouseX <= (double)(this.bx + this.bw) && mouseY >= (double)this.by && mouseY <= (double)(this.by + this.bh);
        if (hovered && button == 0) {
            this.cursorSnap = true;
            long now = System.currentTimeMillis();
            int clickCursor = this.cursorIndexAt(mouseX);
            if (now - this.lastClickTime < 250L) {
                this.typing = true;
                this.dragging = false;
                this.selectAllText();
            } else {
                this.typing = true;
                this.dragging = true;
                this.selectionStart = this.cursorPosition = clickCursor;
                this.selectionEnd = this.cursorPosition;
            }
            this.lastClickTime = now;
            return true;
        }
        if (!hovered && button == 0) {
            this.blur();
        }
        return false;
    }

    public final void mouseReleased(int button) {
        if (button == 0) {
            this.dragging = false;
        }
    }

    public final boolean charTyped(@NotNull CharInput event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!this.typing) {
            return false;
        }
        int cp = event.codepoint();
        if (Character.isISOControl(cp)) {
            return false;
        }
        this.deleteSelectedText();
        String string = event.asString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"codepointAsString(...)");
        this.replaceText(this.cursorPosition, this.cursorPosition, string);
        Sounds.play("search_typing");
        return true;
    }

    public final boolean keyPressed(@NotNull KeyInput event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!this.typing) {
            return false;
        }
        int keyCode = event.key();
        int modifiers = event.modifiers();
        boolean ctrl = SearchField.Companion.ctrlDown(modifiers);
        boolean shift = SearchField.Companion.shiftDown(modifiers);
        if (ctrl) {
            return switch (keyCode) {
                case 65 -> {
                    this.selectAllText();
                    yield true;
                }
                case 67 -> {
                    this.copyToClipboard();
                    yield true;
                }
                case 86 -> {
                    this.pasteFromClipboard();
                    yield true;
                }
                case 88 -> {
                    this.cutToClipboard();
                    yield true;
                }
                case 263 -> {
                    this.moveCursorTo(this.findPreviousWordBoundary(this.cursorPosition), shift);
                    yield true;
                }
                case 262 -> {
                    this.moveCursorTo(this.findNextWordBoundary(this.cursorPosition), shift);
                    yield true;
                }
                case 259 -> {
                    this.deletePrevious(true);
                    yield true;
                }
                case 261 -> {
                    this.deleteNext(true);
                    yield true;
                }
                default -> false;
            };
        }
        return switch (keyCode) {
            case 263 -> {
                if (!shift && this.hasSelection()) {
                    this.moveCursorTo(this.getStartOfSelection(), false);
                } else {
                    this.moveCursorTo(this.cursorPosition - 1, shift);
                }
                yield true;
            }
            case 262 -> {
                if (!shift && this.hasSelection()) {
                    this.moveCursorTo(this.getEndOfSelection(), false);
                } else {
                    this.moveCursorTo(this.cursorPosition + 1, shift);
                }
                yield true;
            }
            case 268 -> {
                this.moveCursorTo(0, shift);
                yield true;
            }
            case 269 -> {
                this.moveCursorTo(this.text.length(), shift);
                yield true;
            }
            case 259 -> {
                this.deletePrevious(false);
                yield true;
            }
            case 261 -> {
                this.deleteNext(false);
                yield true;
            }
            case 257 -> {
                this.blur();
                yield true;
            }
            case 256 -> {
                if (((CharSequence)this.text).length() > 0) {
                    this.setText("");
                } else {
                    this.blur();
                }
                yield true;
            }
            default -> false;
        };
    }

    private final void pasteFromClipboard() {
        String string = IMinecraft.mc.keyboard.getClipboard();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getClipboard(...)");
        String clipboard = string;
        if (((CharSequence)clipboard).length() == 0) {
            return;
        }
        String normalized = clipboard.replace("\r", "").replace("\n", " ");
        this.deleteSelectedText();
        this.replaceText(this.cursorPosition, this.cursorPosition, normalized);
    }

    private final void copyToClipboard() {
        if (this.hasSelection()) {
            IMinecraft.mc.keyboard.setClipboard(this.getSelectedText());
        }
    }

    private final void cutToClipboard() {
        if (!this.hasSelection()) {
            return;
        }
        IMinecraft.mc.keyboard.setClipboard(this.getSelectedText());
        this.deleteSelectedText();
    }

    private final void selectAllText() {
        if (((CharSequence)this.text).length() == 0) {
            this.clearSelection();
            this.cursorPosition = 0;
            return;
        }
        this.selectionStart = 0;
        this.selectionEnd = this.text.length();
        this.cursorPosition = this.text.length();
    }

    private final void deletePrevious(boolean ctrlWord) {
        if (this.hasSelection()) {
            this.deleteSelectedText();
            return;
        }
        if (this.cursorPosition <= 0) {
            return;
        }
        int start = ctrlWord ? this.findPreviousWordBoundary(this.cursorPosition) : this.cursorPosition - 1;
        this.replaceText(start, this.cursorPosition, "");
    }

    private final void deleteNext(boolean ctrlWord) {
        if (this.hasSelection()) {
            this.deleteSelectedText();
            return;
        }
        if (this.cursorPosition >= this.text.length()) {
            return;
        }
        int end = ctrlWord ? this.findNextWordBoundary(this.cursorPosition) : this.cursorPosition + 1;
        this.replaceText(this.cursorPosition, end, "");
    }

    private final void moveCursorTo(int target, boolean withSelection) {
        int clamped = SearchField.Companion.clamp(target, 0, this.text.length());
        if (withSelection) {
            if (this.selectionStart == -1) {
                this.selectionStart = this.cursorPosition;
            }
            this.selectionEnd = this.cursorPosition = clamped;
            if (this.selectionStart == this.selectionEnd) {
                this.clearSelection();
            }
        } else {
            this.cursorPosition = clamped;
            this.clearSelection();
        }
    }

    private final int findPreviousWordBoundary(int from) {
        int position;
        for (position = SearchField.Companion.clamp(from, 0, this.text.length()); position > 0 && !this.isWordCharacter(this.text.charAt(position - 1)); --position) {
        }
        while (position > 0 && this.isWordCharacter(this.text.charAt(position - 1))) {
            --position;
        }
        return position;
    }

    private final int findNextWordBoundary(int from) {
        int position;
        for (position = SearchField.Companion.clamp(from, 0, this.text.length()); position < this.text.length() && !this.isWordCharacter(this.text.charAt(position)); ++position) {
        }
        while (position < this.text.length() && this.isWordCharacter(this.text.charAt(position))) {
            ++position;
        }
        return position;
    }

    private final boolean isWordCharacter(char c) {
        return Character.isLetterOrDigit(c) || c == '_';
    }

    private final void replaceText(int start, int end, String replacement) {
        int normalizedEnd;
        int normalizedStart = SearchField.Companion.clamp(start, 0, this.text.length());
        if (normalizedStart > (normalizedEnd = SearchField.Companion.clamp(end, 0, this.text.length()))) {
            int tmp = normalizedStart;
            normalizedStart = normalizedEnd;
            normalizedEnd = tmp;
        }
        String string = this.text.substring(0, normalizedStart);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
        String string2 = this.text.substring(normalizedEnd);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
        this.text = string + replacement + string2;
        this.cursorPosition = normalizedStart + replacement.length();
        int i = Math.min(normalizedEnd, this.charAnim.size()) - 1;
        int n = normalizedStart;
        if (n <= i) {
            while (true) {
                this.charAnim.remove(i);
                if (i == n) break;
                --i;
            }
        }
        n = ((CharSequence)replacement).length();
        for (int k = 0; k < n; ++k) {
            this.charAnim.add(normalizedStart + k, Float.valueOf(-((float)k * 0.14f)));
        }
        this.clearSelection();
    }

    private final boolean hasSelection() {
        return this.selectionStart != -1 && this.selectionEnd != -1 && this.selectionStart != this.selectionEnd;
    }

    private final String getSelectedText() {
        String string = this.text.substring(this.getStartOfSelection(), this.getEndOfSelection());
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
        return string;
    }

    private final int getStartOfSelection() {
        return Math.min(this.selectionStart, this.selectionEnd);
    }

    private final int getEndOfSelection() {
        return Math.max(this.selectionStart, this.selectionEnd);
    }

    private final void clearSelection() {
        this.selectionStart = -1;
        this.selectionEnd = -1;
    }

    private final void deleteSelectedText() {
        if (this.hasSelection()) {
            this.replaceText(this.getStartOfSelection(), this.getEndOfSelection(), "");
        }
    }

    private final int cursorIndexAt(double mouseX) {
        int position;
        float relativeX = (float)mouseX - this.textStartX + this.xOffset;
        for (position = 0; position < this.text.length(); ++position) {
            String string = this.text.substring(position, position + 1);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            float charWidth = SearchField.Companion.w(string);
            String string2 = this.text.substring(0, position);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
            float textWidth = SearchField.Companion.w(string2);
            if (textWidth + charWidth / 2.0f > relativeX) break;
        }
        return SearchField.Companion.clamp(position, 0, this.text.length());
    }

    private final void updateXOffset() {
        float maxOffset;
        String string = this.text.substring(0, Math.min(this.cursorPosition, this.text.length()));
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
        float cursorX = SearchField.Companion.w(string);
        if (cursorX < this.xOffset) {
            this.xOffset = Math.max(0.0f, cursorX - 6.0f);
        } else if (cursorX - this.xOffset > this.textVisibleW - 4.0f) {
            this.xOffset = cursorX - (this.textVisibleW - 4.0f) + 6.0f;
        }
        if (this.xOffset < 0.0f) {
            this.xOffset = 0.0f;
        }
        if (this.xOffset > (maxOffset = Math.max(0.0f, SearchField.Companion.w(this.text) - this.textVisibleW + 4.0f))) {
            this.xOffset = maxOffset;
        }
    }

    private final float charProgress(int i) {
        float f;
        if (i >= 0 && i < this.charAnim.size()) {
            Float f2 = this.charAnim.get(i);
            Intrinsics.checkNotNullExpressionValue((Object)f2, (String)"get(...)");
            f = ((Number)f2).floatValue();
        } else {
            f = 1.0f;
        }
        return f;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u000e\u0010\rJ'\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010 \u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b \u0010\u001eJ'\u0010#\u001a\u00020\t2\u0006\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b#\u0010$J/\u0010(\u001a\u00020\t2\u0006\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\t2\u0006\u0010'\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010+R\u0014\u0010-\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010+R\u0014\u0010.\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010+\u00a8\u0006/"}, d2={"Lrtx/kimiko/api/ui/module/SearchField.Companion;", "", "<init>", "()V", "", "s", "", "w", "(Ljava/lang/String;)F", "", "modifiers", "", "ctrlDown", "(I)Z", "shiftDown", "value", "min", "max", "clamp", "(III)I", "a", "clampA", "(F)I", "Ljava/awt/Color;", "c", "menuAlpha", "withAlpha", "(Ljava/awt/Color;F)I", "v", "clamp01", "(F)F", "t", "easeOutBack", "from", "to", "mixRgb", "(IIF)I", "r", "g", "b", "col", "(IIIF)I", "SIZE", "F", "PAD_X", "REVEAL_SPEED", "REVEAL_STAGGER", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float w(String s) {
            return Fonts.MEDIUM.width(s, 7.0f);
        }

        private final boolean ctrlDown(int modifiers) {
            if ((modifiers & 2) != 0) {
                return true;
            }
            long h = IMinecraft.mc.getWindow().getHandle();
            return GLFW.glfwGetKey((long)h, (int)341) == 1 || GLFW.glfwGetKey((long)h, (int)345) == 1;
        }

        private final boolean shiftDown(int modifiers) {
            if ((modifiers & 1) != 0) {
                return true;
            }
            long h = IMinecraft.mc.getWindow().getHandle();
            return GLFW.glfwGetKey((long)h, (int)340) == 1 || GLFW.glfwGetKey((long)h, (int)344) == 1;
        }

        private final int clamp(int value, int min, int max) {
            return Math.max(min, Math.min(max, value));
        }

        private final int clampA(float a) {
            return Math.max(0, Math.min(255, MathKt.roundToInt((float)a)));
        }

        private final int withAlpha(Color c, float menuAlpha) {
            int a = this.clampA((float)c.getAlpha() * menuAlpha);
            return a << 24 | c.getRGB() & 0xFFFFFF;
        }

        private final float clamp01(float v) {
            return v < 0.0f ? 0.0f : (v > 1.0f ? 1.0f : v);
        }

        private final float easeOutBack(float t) {
            float c1 = 3.6f;
            float c3 = c1 + 1.0f;
            float u = t - 1.0f;
            return 1.0f + c3 * u * u * u + c1 * u * u;
        }

        private final int mixRgb(int from, int to, float value) {
            float t = value < 0.0f ? 0.0f : (value > 1.0f ? 1.0f : value);
            int ar = from >> 16 & 0xFF;
            int ag = from >> 8 & 0xFF;
            int ab = from & 0xFF;
            int br = to >> 16 & 0xFF;
            int bg = to >> 8 & 0xFF;
            int bb = to & 0xFF;
            int r = MathKt.roundToInt((float)((float)ar + (float)(br - ar) * t));
            int g = MathKt.roundToInt((float)((float)ag + (float)(bg - ag) * t));
            int b = MathKt.roundToInt((float)((float)ab + (float)(bb - ab) * t));
            return r << 16 | g << 8 | b;
        }

        private final int col(int r, int g, int b, float a) {
            int alpha = Math.max(0, Math.min(255, MathKt.roundToInt((float)a)));
            if (alpha <= 0) {
                return 0;
            }
            return new Color(r, g, b, alpha).getRGB();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

