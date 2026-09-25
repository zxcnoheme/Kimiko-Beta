/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.math.MathKt
 *  kotlin.ranges.RangesKt
 *  kotlin.text.Regex
 *  kotlin.text.StringsKt
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.skyshader;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0006\n\u0002\b\u0018\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u0000 f2\u00020\u0001:\u0001fB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\t\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0013\u0010\u0014J=\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u0017\u00a2\u0006\u0004\b\u001d\u0010\u001eJ7\u0010!\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b!\u0010\"J\u0017\u0010%\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020#H\u0002\u00a2\u0006\u0004\b%\u0010&J%\u0010*\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020\u000f\u00a2\u0006\u0004\b*\u0010+J\u001d\u0010,\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u0017\u00a2\u0006\u0004\b,\u0010-J\r\u0010.\u001a\u00020\u0006\u00a2\u0006\u0004\b.\u0010\u0003J%\u00101\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u00172\u0006\u00100\u001a\u00020/\u00a2\u0006\u0004\b1\u00102J\u0015\u00104\u001a\u00020\u000f2\u0006\u00103\u001a\u00020#\u00a2\u0006\u0004\b4\u0010&J\u001d\u00107\u001a\u00020\u000f2\u0006\u00105\u001a\u00020#2\u0006\u00106\u001a\u00020#\u00a2\u0006\u0004\b7\u00108J\u001f\u0010:\u001a\u00020\u00062\u0006\u00109\u001a\u00020#2\u0006\u0010)\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b:\u0010;J\u0017\u0010<\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b<\u0010\fJ\u000f\u0010=\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b=\u0010\u0003J\u0017\u0010?\u001a\u00020\u00062\u0006\u0010>\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b?\u0010\u0014J'\u0010A\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u00172\u0006\u0010@\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bA\u0010BJ\u000f\u0010C\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bC\u0010\u0003J\u0017\u0010D\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\bD\u0010EJ\u000f\u0010F\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bF\u0010\u0011J\u000f\u0010G\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bG\u0010\u0003J\u000f\u0010I\u001a\u00020HH\u0002\u00a2\u0006\u0004\bI\u0010JJ\u000f\u0010K\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bK\u0010\u0003J\u001f\u0010L\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\bL\u0010MJ\u000f\u0010N\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\bN\u0010OR\u001e\u0010S\u001a\f\u0012\b\u0012\u00060Qj\u0002`R0P8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bS\u0010TR\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010UR\u0016\u0010V\u001a\u00020#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010WR\u0016\u0010X\u001a\u00020#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010WR\u0016\u0010Y\u001a\u00020#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010WR\u0016\u0010Z\u001a\u00020#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010WR\u0016\u0010[\u001a\u00020#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b[\u0010WR\u0016\u0010\\\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\\\u0010]R\u0016\u0010^\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010]R\u0016\u0010_\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010`R\u0016\u0010a\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010`R\u0016\u0010b\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010]R\u0016\u0010c\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010]R\u0016\u0010d\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bd\u0010]R\u0016\u0010e\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010]\u00a8\u0006g"}, d2={"Lrtx/kimiko/api/ui/skyshader/CodeArea;", "", "<init>", "()V", "Ljava/lang/Runnable;", "onChange", "", "setOnChange", "(Ljava/lang/Runnable;)V", "", "text", "setText", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "", "isFocused", "()Z", "value", "setFocused", "(Z)V", "Lnet/minecraft/DrawContext;", "graphics", "", "x", "y", "w", "h", "alpha", "render", "(Lnet/minecraft/DrawContext;FFFFF)V", "line", "inBlockCommentIn", "drawHighlighted", "(Ljava/lang/String;FFFZ)Z", "", "lineIndex", "blockCommentStateAt", "(I)Z", "mx", "my", "shift", "mouseClicked", "(FFZ)Z", "mouseDragged", "(FF)V", "mouseReleased", "", "delta", "mouseScrolled", "(FFD)Z", "codepoint", "charTyped", "key", "modifiers", "keyPressed", "(II)Z", "dir", "moveVertical", "(IZ)V", "insert", "deleteSelection", "cut", "copySelection", "keepAnchor", "placeCaret", "(FFZ)V", "scrollToCaret", "clampScroll", "(F)V", "hasSelection", "collapseSelection", "", "selectionOrdered", "()[I", "changed", "hit", "(FF)Z", "gutterWidth", "()F", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "lines", "Ljava/util/List;", "Ljava/lang/Runnable;", "caretLine", "I", "caretCol", "anchorLine", "anchorCol", "desiredCol", "scrollY", "F", "scrollX", "focused", "Z", "dragging", "lastX", "lastY", "lastW", "lastH", "Companion", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nCodeArea.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CodeArea.kt\nrtx/kimiko/api/ui/skyshader/CodeArea\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,710:1\n742#2,9:711\n37#3,2:720\n37#3,2:722\n*S KotlinDebug\n*F\n+ 1 CodeArea.kt\nrtx/kimiko/api/ui/skyshader/CodeArea\n*L\n48#1:711,9\n48#1:720,2\n513#1:722,2\n*E\n"})
public final class CodeArea {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final List<StringBuilder> lines = new ArrayList();
    @NotNull
    private Runnable onChange = CodeArea::onChange$lambda$0;
    private int caretLine;
    private int caretCol;
    private int anchorLine;
    private int anchorCol;
    private int desiredCol = -1;
    private float scrollY;
    private float scrollX;
    private boolean focused;
    private boolean dragging;
    private float lastX;
    private float lastY;
    private float lastW;
    private float lastH;
    @NotNull
    private static final Fonts FONT = Fonts.SF_MEDIUM;
    private static final float FONT_SIZE = 4.6f;
    private static final float LINE_H = 6.4f;
    private static final float PAD_X = 4.0f;
    private static final float PAD_Y = 3.0f;
    private static final float GUTTER_TEXT = 3.8f;
    private static final int MAX_LINES = 2000;
    private static final int MAX_LINE_LENGTH = 500;
    @NotNull
    private static final Set<String> KEYWORDS;

    public CodeArea() {
        this.lines.add(new StringBuilder());
    }

    public final void setOnChange(@Nullable Runnable onChange) {
        Runnable runnable = onChange;
        if (runnable == null) {
            runnable = CodeArea::setOnChange$lambda$0;
        }
        this.onChange = runnable;
    }

    /*
     * WARNING - void declaration
     */
    public final void setText(@Nullable String text) {
        this.lines.clear();
        String rawText = text == null ? "" : text.replace("\r", "");
        String[] splitLines = rawText.split("\n", -1);
        int end = splitLines.length;
        while (end > 0 && splitLines[end - 1].isEmpty()) {
            end--;
        }
        for (int i = 0; i < end; i++) {
            String line = splitLines[i];
            if (line.length() > 500) {
                line = line.substring(0, 500);
            }
            this.lines.add(new StringBuilder(line));
            if (this.lines.size() >= 2000) break;
        }
        if (this.lines.isEmpty()) {
            this.lines.add(new StringBuilder());
        }
        this.caretLine = 0;
        this.caretCol = 0;
        this.collapseSelection();
        this.scrollY = 0.0f;
        this.scrollX = 0.0f;
        this.desiredCol = -1;
    }

    @NotNull
    public final String getText() {
        StringBuilder sb = new StringBuilder();
        int n = ((Collection)this.lines).size();
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                sb.append('\n');
            }
            sb.append((CharSequence)this.lines.get(i));
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        return string;
    }

    public final boolean isFocused() {
        return this.focused;
    }

    public final void setFocused(boolean value) {
        this.focused = value;
        if (!value) {
            this.dragging = false;
        }
    }

    public final void render(@NotNull DrawContext graphics, float x, float y, float w, float h, float alpha) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        this.lastX = x;
        this.lastY = y;
        this.lastW = w;
        this.lastH = h;
        float gutterW = this.gutterWidth();
        float textX = x + gutterW + 4.0f;
        this.clampScroll(h);
        Render2D.pushScissor(graphics, x, y, w, h);
        int first = Math.max(0, (int)Math.floor(this.scrollY / 6.4f));
        int last = Math.min(this.lines.size() - 1, (int)Math.ceil((this.scrollY + h) / 6.4f));
        boolean hasSel = this.hasSelection();
        int[] sel = this.selectionOrdered();
        boolean inBlockComment = this.blockCommentStateAt(first);
        int i = first;
        if (i <= last) {
            while (true) {
                float lineY = y + 3.0f + (float)i * 6.4f - this.scrollY;
                String line = this.lines.get(i).toString();
                if (hasSel && i >= sel[0] && i <= sel[2]) {
                    int to;
                    int from = i == sel[0] ? sel[1] : 0;
                    int n = to = i == sel[2] ? sel[3] : line.length();
                    if (to > from || from == 0 && to == 0 && i < sel[2]) {
                        String string = line.substring(0, Math.min(from, line.length()));
                        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                        float sx = textX + CodeArea.Companion.width(string) - this.scrollX;
                        String string2 = line.substring(0, Math.min(to, line.length()));
                        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
                        float ex = textX + CodeArea.Companion.width(string2) - this.scrollX;
                        if (i < sel[2] && to >= line.length()) {
                            ex += 3.0f;
                        }
                        Render2D.rect(sx, lineY - 0.8f, Math.max(1.0f, ex - sx), 6.4f, 0.0f, ClientAccent.accentFillAt(60.0f * alpha, sx, lineY));
                    }
                }
                int lineColor = i == this.caretLine && this.focused ? CodeArea.Companion.color(255, 255, 255, 120, alpha) : CodeArea.Companion.color(255, 255, 255, 55, alpha);
                String num = String.valueOf(i + 1);
                float numW = FONT.width(num, 3.8f);
                FONT.draw(num, x + gutterW - 3.0f - numW, lineY + 0.7f, 3.8f, lineColor);
                inBlockComment = this.drawHighlighted(line, textX - this.scrollX, lineY, alpha, inBlockComment);
                if (i == last) break;
                ++i;
            }
        }
        if (this.focused) {
            i = this.caretLine;
            boolean bl = first <= i ? i <= last : false;
            if (bl && System.currentTimeMillis() / 500L % 2L == 0L) {
                String string = this.lines.get(this.caretLine).toString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                String line = string;
                String string3 = line.substring(0, Math.min(this.caretCol, line.length()));
                Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"substring(...)");
                float cx = textX + CodeArea.Companion.width(string3) - this.scrollX;
                float cy = y + 3.0f + (float)this.caretLine * 6.4f - this.scrollY;
                Render2D.rect(cx, cy - 0.5f, 0.7f, 5.6f, 0.0f, ClientAccent.accentBrightAt(230.0f * alpha, cx, cy));
            }
        }
        Render2D.popScissor(graphics);
        float contentH = (float)this.lines.size() * 6.4f + 6.0f;
        if (contentH > h) {
            float trackH = h - 4.0f;
            float thumbH = Math.max(8.0f, trackH * h / contentH);
            float thumbY = y + 2.0f + (trackH - thumbH) * (this.scrollY / (contentH - h));
            Render2D.rect(x + w - 2.2f, y + 2.0f, 1.2f, trackH, 0.6f, CodeArea.Companion.color(255, 255, 255, 22, alpha));
            Render2D.rect(x + w - 2.2f, thumbY, 1.2f, thumbH, 0.6f, ClientAccent.accentSoftAt(150.0f * alpha, x + w, thumbY));
        }
    }

    private final boolean drawHighlighted(String line, float x, float y, float alpha, boolean inBlockCommentIn) {
        String trimmed;
        boolean inBlockComment = inBlockCommentIn;
        int defaultColor = CodeArea.Companion.color(235, 238, 245, 225, alpha);
        int commentColor = CodeArea.Companion.color(130, 180, 140, 200, alpha);
        int numberColor = CodeArea.Companion.color(255, 200, 140, 230, alpha);
        int preprocColor = CodeArea.Companion.color(200, 165, 255, 230, alpha);
        int keywordColor = ClientAccent.accentBrightAt(235.0f * alpha, x, y);
        float cursor = x;
        int i = 0;
        int n = line.length();
        if (!inBlockComment && String.valueOf((trimmed = ((Object)StringsKt.trimStart((CharSequence)line)).toString())).startsWith("#")) {
            FONT.draw(line, x, y, 4.6f, preprocColor);
            return false;
        }
        while (i < n) {
            String chunk;
            char cc;
            if (inBlockComment) {
                String string;
                int end = String.valueOf(line).indexOf("*/", (int)i);
                if (end == -1) {
                    String string2 = line.substring(i);
                    string = string2;
                    Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
                } else {
                    String string3 = line.substring(i, end + 2);
                    string = string3;
                    Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"substring(...)");
                }
                String chunk2 = string;
                FONT.draw(chunk2, cursor, y, 4.6f, commentColor);
                cursor += CodeArea.Companion.width(chunk2);
                if (end == -1) {
                    return true;
                }
                inBlockComment = false;
                i = end + 2;
                continue;
            }
            char c = line.charAt(i);
            if (c == '/' && i + 1 < n && line.charAt(i + 1) == '/') {
                String string = line.substring(i);
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                FONT.draw(string, cursor, y, 4.6f, commentColor);
                return false;
            }
            if (c == '/' && i + 1 < n && line.charAt(i + 1) == '*') {
                FONT.draw("/*", cursor, y, 4.6f, commentColor);
                cursor += CodeArea.Companion.width("/*");
                i += 2;
                inBlockComment = true;
                continue;
            }
            if (Character.isLetter(c) || c == '_') {
                int start = i;
                while (i < n && (Character.isLetterOrDigit(line.charAt(i)) || line.charAt(i) == '_')) {
                    ++i;
                }
                String word = line.substring(start, i);
                FONT.draw(word, cursor, y, 4.6f, KEYWORDS.contains(word) ? keywordColor : defaultColor);
                cursor += CodeArea.Companion.width(word);
                continue;
            }
            if (Character.isDigit(c) || c == '.' && i + 1 < n && Character.isDigit(line.charAt(i + 1))) {
                int start = i;
                while (i < n && (Character.isLetterOrDigit(line.charAt(i)) || line.charAt(i) == '.')) {
                    ++i;
                }
                String number = line.substring(start, i);
                FONT.draw(number, cursor, y, 4.6f, numberColor);
                cursor += CodeArea.Companion.width(number);
                continue;
            }
            int start = i;
            while (!(i >= n || Character.isLetterOrDigit(cc = line.charAt(i)) || cc == '_' || cc == '/' || cc == '.' && i + 1 < n && Character.isDigit(line.charAt(i + 1)))) {
                ++i;
            }
            if (i == start) {
                ++i;
            }
            chunk = line.substring(start, i);
            FONT.draw(chunk, cursor, y, 4.6f, defaultColor);
            cursor += CodeArea.Companion.width(chunk);
        }
        return inBlockComment;
    }

    private final boolean blockCommentStateAt(int lineIndex) {
        boolean state = false;
        for (int i = 0; i < lineIndex && i < this.lines.size(); ++i) {
            String line = (String) (this.lines.get(i).toString());
            int pos = 0;
            int n = line.length();
            while (pos < n) {
                if (state) {
                    int end = String.valueOf(line).indexOf("*/", (int)pos);
                    if (end == -1) {
                        pos = n;
                        continue;
                    }
                    state = false;
                    pos = end + 2;
                    continue;
                }
                int lineComment = String.valueOf(line).indexOf("//", (int)pos);
                int blockStart = String.valueOf(line).indexOf("/*", (int)pos);
                if (blockStart == -1 || lineComment != -1 && lineComment < blockStart) {
                    pos = n;
                    continue;
                }
                state = true;
                pos = blockStart + 2;
            }
        }
        return state;
    }

    public final boolean mouseClicked(float mx, float my, boolean shift) {
        if (!this.hit(mx, my)) {
            if (this.focused) {
                this.focused = false;
            }
            return false;
        }
        this.focused = true;
        this.dragging = true;
        this.placeCaret(mx, my, shift);
        return true;
    }

    public final void mouseDragged(float mx, float my) {
        if (this.dragging && this.focused) {
            this.placeCaret(mx, my, true);
        }
    }

    public final void mouseReleased() {
        this.dragging = false;
    }

    public final boolean mouseScrolled(float mx, float my, double delta) {
        if (!this.hit(mx, my)) {
            return false;
        }
        this.scrollY -= (float)delta * 6.4f * 3.0f;
        this.clampScroll(this.lastH);
        return true;
    }

    public final boolean charTyped(int codepoint) {
        if (!this.focused || Character.isISOControl(codepoint)) {
            return false;
        }
        char[] cArray = Character.toChars(codepoint);
        Intrinsics.checkNotNullExpressionValue((Object)cArray, (String)"toChars(...)");
        char[] cArray2 = cArray;
        this.insert(new String(cArray2));
        return true;
    }

    public final boolean keyPressed(int key, int modifiers) {
        if (!this.focused) {
            return false;
        }
        boolean ctrl = (modifiers & 2) != 0 || (modifiers & 8) != 0;
        boolean shift = (modifiers & 1) != 0;
        switch (key) {
            case 256: {
                this.focused = false;
                return true;
            }
            case 257: 
            case 335: {
                int indentEnd;
                String string = this.lines.get(this.caretLine).toString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                String current = string;
                for (indentEnd = 0; indentEnd < current.length() && indentEnd < this.caretCol && current.charAt(indentEnd) == ' '; ++indentEnd) {
                }
                this.insert("\n" + StringsKt.repeat((CharSequence)" ", (int)indentEnd));
                return true;
            }
            case 258: {
                this.insert("    ");
                return true;
            }
            case 259: {
                if (this.hasSelection()) {
                    this.deleteSelection();
                } else if (this.caretCol > 0) {
                    this.lines.get(this.caretLine).deleteCharAt(this.caretCol - 1);
                    int current = this.caretCol;
                    this.caretCol = current + -1;
                    this.collapseSelection();
                    this.changed();
                } else if (this.caretLine > 0) {
                    StringBuilder prev = this.lines.get(this.caretLine - 1);
                    this.caretCol = prev.length();
                    prev.append((CharSequence)this.lines.get(this.caretLine));
                    this.lines.remove(this.caretLine);
                    int n = this.caretLine;
                    this.caretLine = n + -1;
                    this.collapseSelection();
                    this.changed();
                }
                this.desiredCol = -1;
                this.scrollToCaret();
                return true;
            }
            case 261: {
                if (this.hasSelection()) {
                    this.deleteSelection();
                } else {
                    StringBuilder line = this.lines.get(this.caretLine);
                    if (this.caretCol < line.length()) {
                        line.deleteCharAt(this.caretCol);
                        this.changed();
                    } else if (this.caretLine + 1 < this.lines.size()) {
                        line.append((CharSequence)this.lines.get(this.caretLine + 1));
                        this.lines.remove(this.caretLine + 1);
                        this.changed();
                    }
                }
                this.desiredCol = -1;
                this.scrollToCaret();
                return true;
            }
            case 263: {
                if (!shift && this.hasSelection()) {
                    int[] sel = this.selectionOrdered();
                    this.caretLine = sel[0];
                    this.caretCol = sel[1];
                    this.collapseSelection();
                } else {
                    if (this.caretCol > 0) {
                        int sel = this.caretCol;
                        this.caretCol = sel + -1;
                    } else if (this.caretLine > 0) {
                        int sel = this.caretLine;
                        this.caretLine = sel + -1;
                        this.caretCol = this.lines.get(this.caretLine).length();
                    }
                    if (!shift) {
                        this.collapseSelection();
                    }
                }
                this.desiredCol = -1;
                this.scrollToCaret();
                return true;
            }
            case 262: {
                if (!shift && this.hasSelection()) {
                    int[] sel = this.selectionOrdered();
                    this.caretLine = sel[2];
                    this.caretCol = sel[3];
                    this.collapseSelection();
                } else {
                    if (this.caretCol < this.lines.get(this.caretLine).length()) {
                        int sel = this.caretCol;
                        this.caretCol = sel + 1;
                    } else if (this.caretLine + 1 < this.lines.size()) {
                        int sel = this.caretLine;
                        this.caretLine = sel + 1;
                        this.caretCol = 0;
                    }
                    if (!shift) {
                        this.collapseSelection();
                    }
                }
                this.desiredCol = -1;
                this.scrollToCaret();
                return true;
            }
            case 265: {
                this.moveVertical(-1, shift);
                return true;
            }
            case 264: {
                this.moveVertical(1, shift);
                return true;
            }
            case 268: {
                if (ctrl) {
                    this.caretLine = 0;
                }
                this.caretCol = 0;
                if (!shift) {
                    this.collapseSelection();
                }
                this.desiredCol = -1;
                this.scrollToCaret();
                return true;
            }
            case 269: {
                if (ctrl) {
                    this.caretLine = this.lines.size() - 1;
                }
                this.caretCol = this.lines.get(this.caretLine).length();
                if (!shift) {
                    this.collapseSelection();
                }
                this.desiredCol = -1;
                this.scrollToCaret();
                return true;
            }
        }
        if (ctrl) {
            switch (key) {
                case 65: {
                    this.anchorLine = 0;
                    this.anchorCol = 0;
                    this.caretLine = this.lines.size() - 1;
                    this.caretCol = this.lines.get(this.caretLine).length();
                    return true;
                }
                case 67: {
                    this.copySelection(false);
                    return true;
                }
                case 88: {
                    this.copySelection(true);
                    return true;
                }
                case 86: {
                    String clip = CodeArea.Companion.clipboard();
                    CharSequence charSequence = clip;
                    if (!(charSequence == null || charSequence.length() == 0)) {
                        this.insert(String.valueOf(clip).replace("\r", ""));
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private final void moveVertical(int dir, boolean shift) {
        int target = this.caretLine + dir;
        if (target < 0 || target >= this.lines.size()) {
            if (!shift) {
                this.collapseSelection();
            }
            return;
        }
        if (this.desiredCol < 0) {
            this.desiredCol = this.caretCol;
        }
        this.caretLine = target;
        this.caretCol = Math.min(this.desiredCol, this.lines.get(this.caretLine).length());
        if (!shift) {
            this.collapseSelection();
        }
        this.scrollToCaret();
    }

    private final void insert(String text) {
        if (this.hasSelection()) {
            this.deleteSelection();
        }
        String[] parts = text.split("\n");
        StringBuilder line = this.lines.get(this.caretLine);
        String tail = line.substring(Math.min(this.caretCol, line.length()));
        line.setLength(Math.min(this.caretCol, line.length()));
        CodeArea.Companion.appendClamped(line, parts[0]);
        for (int i = 1; i < parts.length && this.lines.size() < 2000; ++i) {
            int n2 = this.caretLine;
            this.caretLine = n2 + 1;
            StringBuilder next = new StringBuilder();
            CodeArea.Companion.appendClamped(next, parts[i]);
            this.lines.add(this.caretLine, next);
        }
        StringBuilder last = this.lines.get(this.caretLine);
        this.caretCol = last.length();
        Intrinsics.checkNotNull((Object)tail);
        CodeArea.Companion.appendClamped(last, tail);
        this.collapseSelection();
        this.desiredCol = -1;
        this.changed();
        this.scrollToCaret();
    }

    private final void deleteSelection() {
        int[] sel = this.selectionOrdered();
        StringBuilder first = this.lines.get(sel[0]);
        String tail = this.lines.get(sel[2]).substring(Math.min(sel[3], this.lines.get(sel[2]).length()));
        first.setLength(Math.min(sel[1], first.length()));
        first.append(tail);
        int i = sel[2];
        int n = sel[0] + 1;
        if (n <= i) {
            while (true) {
                this.lines.remove(i);
                if (i == n) break;
                --i;
            }
        }
        this.caretLine = sel[0];
        this.caretCol = Math.min(sel[1], first.length());
        this.collapseSelection();
        this.changed();
    }

    private final void copySelection(boolean cut) {
        String text = null;
        if (this.hasSelection()) {
            int[] sel = this.selectionOrdered();
            StringBuilder sb = new StringBuilder();
            int i = sel[0];
            int n = sel[2];
            if (i <= n) {
                while (true) {
                    int to;
                    String line = (String) (this.lines.get(i).toString());
                    int from = i == sel[0] ? Math.min(sel[1], line.length()) : 0;
                    int n2 = to = i == sel[2] ? Math.min(sel[3], line.length()) : line.length();
                    if (i > sel[0]) {
                        sb.append('\n');
                    }
                    sb.append(line, from, to);
                    if (i == n) break;
                    ++i;
                }
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
            text = string;
        } else {
            text = this.getText();
        }
        if (((CharSequence)text).length() > 0) {
            try {
                MinecraftClient.getInstance().keyboard.setClipboard(text);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
        if (cut && this.hasSelection()) {
            this.deleteSelection();
            this.scrollToCaret();
        }
    }

    private final void placeCaret(float mx, float my, boolean keepAnchor) {
        float gutterW = this.gutterWidth();
        float textX = this.lastX + gutterW + 4.0f;
        int line = (int)Math.floor((my - this.lastY - 3.0f + this.scrollY) / 6.4f);
        line = Math.max(0, Math.min(this.lines.size() - 1, line));
        String string = this.lines.get(line).toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        String content = string;
        float target = mx - textX + this.scrollX;
        int col = content.length();
        float acc = 0.0f;
        int n = ((CharSequence)content).length();
        for (int i = 0; i < n; ++i) {
            float cw = CodeArea.Companion.width(String.valueOf(content.charAt(i)));
            if (acc + cw * 0.5f >= target) {
                col = i;
                break;
            }
            acc += cw;
        }
        if (target < 0.0f) {
            col = 0;
        }
        this.caretLine = line;
        this.caretCol = col;
        if (!keepAnchor) {
            this.collapseSelection();
        }
        this.desiredCol = -1;
        this.scrollToCaret();
    }

    private final void scrollToCaret() {
        float top = (float)this.caretLine * 6.4f;
        float bottom = top + 6.4f + 6.0f;
        if (top < this.scrollY) {
            this.scrollY = top;
        } else if (bottom > this.scrollY + this.lastH) {
            this.scrollY = bottom - this.lastH;
        }
        String string = this.lines.get(this.caretLine).toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        String line = string;
        String string2 = line.substring(0, Math.min(this.caretCol, line.length()));
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
        float caretX = CodeArea.Companion.width(string2);
        float viewW = this.lastW - this.gutterWidth() - 8.0f - 4.0f;
        if (caretX < this.scrollX) {
            this.scrollX = Math.max(0.0f, caretX - 10.0f);
        } else if (caretX > this.scrollX + viewW) {
            this.scrollX = caretX - viewW + 10.0f;
        }
        this.clampScroll(this.lastH);
    }

    private final void clampScroll(float h) {
        float contentH = (float)this.lines.size() * 6.4f + 6.0f;
        this.scrollY = Math.max(0.0f, Math.min(this.scrollY, Math.max(0.0f, contentH - h)));
        this.scrollX = Math.max(0.0f, this.scrollX);
    }

    private final boolean hasSelection() {
        return this.anchorLine != this.caretLine || this.anchorCol != this.caretCol;
    }

    private final void collapseSelection() {
        this.anchorLine = this.caretLine;
        this.anchorCol = this.caretCol;
    }

    private final int[] selectionOrdered() {
        if (this.anchorLine < this.caretLine || this.anchorLine == this.caretLine && this.anchorCol <= this.caretCol) {
            int[] nArray = new int[]{this.anchorLine, this.anchorCol, this.caretLine, this.caretCol};
            return nArray;
        }
        int[] nArray = new int[]{this.caretLine, this.caretCol, this.anchorLine, this.anchorCol};
        return nArray;
    }

    private final void changed() {
        this.onChange.run();
    }

    private final boolean hit(float mx, float my) {
        return mx >= this.lastX && mx <= this.lastX + this.lastW && my >= this.lastY && my <= this.lastY + this.lastH;
    }

    private final float gutterWidth() {
        return 8.5f + (this.lines.size() >= 100 ? 3.0f : 0.0f);
    }

    private static final void onChange$lambda$0() {
    }

    private static final void setOnChange$lambda$0() {
    }

    static {
        Object[] objectArray = new String[]{"void", "float", "int", "uint", "bool", "vec2", "vec3", "vec4", "ivec2", "ivec3", "ivec4", "uvec2", "uvec3", "uvec4", "bvec2", "bvec3", "bvec4", "mat2", "mat3", "mat4", "if", "else", "for", "while", "do", "return", "break", "continue", "discard", "const", "in", "out", "inout", "uniform", "layout", "struct", "true", "false", "sampler2D", "switch", "case", "default"};
        KEYWORDS = new HashSet(CollectionsKt.listOf((Object[])objectArray));
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\"\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J#\u0010\n\u001a\u00020\t2\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0011\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J7\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001dR\u0014\u0010 \u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u001dR\u0014\u0010!\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010\u001dR\u0014\u0010\"\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010#R\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00070%8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010'\u00a8\u0006("}, d2={"Lrtx/kimiko/api/ui/skyshader/CodeArea.Companion;", "", "<init>", "()V", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "target", "", "text", "", "appendClamped", "(Ljava/lang/StringBuilder;Ljava/lang/String;)V", "clipboard", "()Ljava/lang/String;", "", "width", "(Ljava/lang/String;)F", "", "r", "g", "b", "a", "mult", "color", "(IIIIF)I", "Lrtx/kimiko/utils/render/fonts/Fonts;", "FONT", "Lrtx/kimiko/utils/render/fonts/Fonts;", "FONT_SIZE", "F", "LINE_H", "PAD_X", "PAD_Y", "GUTTER_TEXT", "MAX_LINES", "I", "MAX_LINE_LENGTH", "", "KEYWORDS", "Ljava/util/Set;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final void appendClamped(StringBuilder target, String text) {
            String string;
            int room = 500 - target.length();
            if (room <= 0) {
                return;
            }
            if (text.length() > room) {
                String string2 = text.substring(0, room);
                string = string2;
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
            } else {
                string = text;
            }
            target.append(string);
        }

        private final String clipboard() {
            String string;
            try {
                string = MinecraftClient.getInstance().keyboard.getClipboard();
            }
            catch (Throwable throwable) {
                string = null;
            }
            return string;
        }

        private final float width(String text) {
            return FONT.width(text, 4.6f);
        }

        private final int color(int r, int g, int b, int a, float mult) {
            int fa = RangesKt.coerceIn((int)MathKt.roundToInt((float)((float)a * mult)), (int)0, (int)255);
            return fa <= 0 ? 0 : new Color(r, g, b, fa).getRGB();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

