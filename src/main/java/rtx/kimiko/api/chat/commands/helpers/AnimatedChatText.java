/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.text.Style
 *  net.minecraft.text.CharacterVisitor
 *  net.minecraft.text.TextColor
 *  net.minecraft.text.OrderedText
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.chat.commands.helpers;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.text.Style;
import net.minecraft.text.CharacterVisitor;
import net.minecraft.text.TextColor;
import net.minecraft.text.OrderedText;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.ui.theme.ThemeManager;
import rtx.kimiko.utils.color.ColorEngine;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u000b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J\u0013\u0010\t\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0007J\u0019\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0014\u0010\u0015J'\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u000b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u000b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u000b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001dR\u0014\u0010 \u001a\u00020\u00188\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u00188\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010!\u00a8\u0006#"}, d2={"Lrtx/kimiko/api/chat/commands/helpers/AnimatedChatText;", "", "<init>", "()V", "Lnet/minecraft/Style;", "Lkotlin/jvm/JvmStatic;", "titleStyle", "()Lnet/minecraft/Style;", "decorStyle", "lineStyle", "style", "", "sentinelKind", "(Lnet/minecraft/Style;)I", "Lnet/minecraft/OrderedText;", "sequence", "", "hasSentinel", "(Lnet/minecraft/OrderedText;)Z", "original", "animate", "(Lnet/minecraft/OrderedText;)Lnet/minecraft/OrderedText;", "i", "total", "", "phase", "sweepColor", "(IIF)I", "SENTINEL_TITLE", "I", "SENTINEL_DECOR", "SENTINEL_LINE", "SCROLL_MS", "F", "BAND_GLYPHS", "rtx.kimiko:kimiko"})
public final class AnimatedChatText {
    @NotNull
    public static final AnimatedChatText INSTANCE = new AnimatedChatText();
    public static final int SENTINEL_TITLE = 12648430;
    public static final int SENTINEL_DECOR = 12648429;
    public static final int SENTINEL_LINE = 12648428;
    private static final float SCROLL_MS = 1400.0f;
    private static final float BAND_GLYPHS = 6.0f;

    private AnimatedChatText() {
    }

    @JvmStatic
    @NotNull
    public static final Style titleStyle() {
        Style style2 = Style.EMPTY.withColor(TextColor.fromRgb((int)12648430));
        Intrinsics.checkNotNullExpressionValue((Object)style2, (String)"withColor(...)");
        return style2;
    }

    @JvmStatic
    @NotNull
    public static final Style decorStyle() {
        Style style2 = Style.EMPTY.withColor(TextColor.fromRgb((int)12648429));
        Intrinsics.checkNotNullExpressionValue((Object)style2, (String)"withColor(...)");
        return style2;
    }

    @JvmStatic
    @NotNull
    public static final Style lineStyle() {
        Style style2 = Style.EMPTY.withColor(TextColor.fromRgb((int)12648428));
        Intrinsics.checkNotNullExpressionValue((Object)style2, (String)"withColor(...)");
        return style2;
    }

    private final int sentinelKind(Style style) {
        if (style == null) {
            return -1;
        }
        TextColor color = style.getColor();
        if (color == null) {
            return -1;
        }
        int rgb = color.getRgb() & 0xFFFFFF;
        if (rgb == 12648430) {
            return 0;
        }
        if (rgb == 12648429) {
            return 1;
        }
        if (rgb == 12648428) {
            return 2;
        }
        return -1;
    }

    @JvmStatic
    public static final boolean hasSentinel(@NotNull OrderedText sequence) {
        Intrinsics.checkNotNullParameter((Object)sequence, (String)"sequence");
        boolean[] blArray = new boolean[]{false};
        boolean[] found = blArray;
        sequence.accept((arg_0, arg_1, arg_2) -> AnimatedChatText.hasSentinel$lambda$0(found, arg_0, arg_1, arg_2));
        return found[0];
    }

    @JvmStatic
    @NotNull
    public static final OrderedText animate(@NotNull OrderedText original) {
        Intrinsics.checkNotNullParameter((Object)original, (String)"original");
        int[] nArray = new int[]{0};
        int[] titleCount = nArray;
        original.accept((arg_0, arg_1, arg_2) -> AnimatedChatText.animate$lambda$0(titleCount, arg_0, arg_1, arg_2));
        int total = titleCount[0];
        float phase = (float)(System.currentTimeMillis() % 1400L) / 1400.0f;
        int decorColor = ThemeManager.accentBright(255.0f) & 0xFFFFFF;
        int lineColor = ThemeManager.accentFill(255.0f) & 0xFFFFFF;
        return arg_0 -> AnimatedChatText.animate$lambda$1(original, total, phase, decorColor, lineColor, arg_0);
    }

    private final int sweepColor(int i, int total, float phase) {
        int dark = ThemeManager.accentFill(255.0f) & 0xFFFFFF;
        int bright = ThemeManager.accentBright(255.0f) & 0xFFFFFF;
        float pos = total <= 1 ? 0.0f : (float)i / (float)(total - 1);
        float center = phase;
        float d = Math.abs(pos - center);
        d = Math.min(d, 1.0f - d);
        float band = 6.0f / (float)Math.max(1, total);
        float t = Math.max(0.0f, 1.0f - d / Math.max(1.0E-4f, band));
        t = t * t * (3.0f - 2.0f * t);
        return ColorEngine.lerpColor(dark, bright, t) & 0xFFFFFF;
    }

    private static final boolean hasSentinel$lambda$0(boolean[] $found, int n, Style style, int n2) {
        boolean bl;
        Intrinsics.checkNotNullParameter((Object)style, (String)"style");
        if (INSTANCE.sentinelKind(style) >= 0) {
            $found[0] = true;
            bl = false;
        } else {
            bl = true;
        }
        return bl;
    }

    private static final boolean animate$lambda$0(int[] $titleCount, int n, Style style, int n2) {
        Intrinsics.checkNotNullParameter((Object)style, (String)"style");
        if (INSTANCE.sentinelKind(style) == 0) {
            int n3 = $titleCount[0];
            $titleCount[0] = n3 + 1;
        }
        return true;
    }

    private static final boolean animate$lambda$1$0(int[] $titleIndex, int $total, float $phase, int $decorColor, int $lineColor, CharacterVisitor $sink, int index, Style style, int codePoint) {
        Intrinsics.checkNotNullParameter((Object)style, (String)"style");
        Style out = style;
        int kind = INSTANCE.sentinelKind(style);
        switch (kind) {
            case 0: {
                Style style2 = style.withColor(TextColor.fromRgb((int)INSTANCE.sweepColor($titleIndex[0], $total, $phase)));
                Intrinsics.checkNotNullExpressionValue((Object)style2, (String)"withColor(...)");
                out = style2;
                int n = $titleIndex[0];
                $titleIndex[0] = n + 1;
                break;
            }
            case 1: {
                Style style3 = style.withColor(TextColor.fromRgb((int)$decorColor));
                Intrinsics.checkNotNullExpressionValue((Object)style3, (String)"withColor(...)");
                out = style3;
                break;
            }
            case 2: {
                Style style4 = style.withColor(TextColor.fromRgb((int)$lineColor));
                Intrinsics.checkNotNullExpressionValue((Object)style4, (String)"withColor(...)");
                out = style4;
            }
        }
        return $sink.accept(index, out, codePoint);
    }

    private static final boolean animate$lambda$1(OrderedText $original, int $total, float $phase, int $decorColor, int $lineColor, CharacterVisitor sink) {
        Intrinsics.checkNotNullParameter((Object)sink, (String)"sink");
        int[] nArray = new int[]{0};
        int[] titleIndex = nArray;
        return $original.accept((arg_0, arg_1, arg_2) -> AnimatedChatText.animate$lambda$1$0(titleIndex, $total, $phase, $decorColor, $lineColor, sink, arg_0, arg_1, arg_2));
    }
}

