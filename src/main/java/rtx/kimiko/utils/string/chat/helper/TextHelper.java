/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.text.Text
 *  net.minecraft.text.Style
 *  net.minecraft.text.MutableText
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.string.chat.helper;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.text.Text;
import net.minecraft.text.Style;
import net.minecraft.text.MutableText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.color.ColorEngine;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0016\u0018\u0000 \u00042\u00020\u0001:\u0002\u0005\u0004B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0006"}, d2={"Lrtx/kimiko/utils/string/chat/helper/TextHelper;", "", "<init>", "()V", "Companion", "GradientStyle", "rtx.kimiko:kimiko"})
public class TextHelper {
    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmStatic
    @NotNull
    public static final Text applyGradient(@Nullable String text, @NotNull GradientStyle style, int color1, int color2, boolean bold) {
        return Companion.applyGradient(text, style, color1, color2, bold);
    }

    @JvmStatic
    @NotNull
    public static final Text applyPredefinedGradient(@Nullable String text, @Nullable String gradientName, boolean bold) {
        return Companion.applyPredefinedGradient(text, gradientName, bold);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J=\u0010\u000f\u001a\u00020\r2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b\u000f\u0010\u0010J/\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J/\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0012J\u001f\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J/\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0012J/\u0010\u0018\u001a\u00020\r2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/utils/string/chat/helper/TextHelper.Companion;", "", "<init>", "()V", "", "text", "Lrtx/kimiko/utils/string/chat/helper/TextHelper$GradientStyle;", "style", "", "color1", "color2", "", "bold", "Lnet/minecraft/Text;", "Lkotlin/jvm/JvmStatic;", "applyGradient", "(Ljava/lang/String;Lrtx/kimiko/utils/string/chat/helper/TextHelper$GradientStyle;IIZ)Lnet/minecraft/Text;", "halfSplitGradient", "(Ljava/lang/String;IIZ)Lnet/minecraft/Text;", "fullGradient", "astolfoGradient", "(Ljava/lang/String;Z)Lnet/minecraft/Text;", "twoColorFade", "gradientName", "applyPredefinedGradient", "(Ljava/lang/String;Ljava/lang/String;Z)Lnet/minecraft/Text;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final Text applyGradient(@Nullable String text, @NotNull GradientStyle style, int color1, int color2, boolean bold) {
            Intrinsics.checkNotNullParameter((Object)((Object)style), (String)"style");
            String string = text;
            if (string == null) {
                string = "";
            }
            String value = string;
            return switch (WhenMappings.$EnumSwitchMapping$0[style.ordinal()]) {
                case 1 -> this.halfSplitGradient(value, color1, color2, bold);
                case 2 -> this.fullGradient(value, color1, color2, bold);
                case 3 -> this.astolfoGradient(value, bold);
                case 4 -> this.twoColorFade(value, color1, color2, bold);
                default -> throw new NoWhenBranchMatchedException();
            };
        }

        private final Text halfSplitGradient(String text, int color1, int color2, boolean bold) {
            MutableText mutableText2 = Text.empty();
            Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"empty(...)");
            MutableText result = mutableText2;
            int midPoint = text.length() / 2;
            int n = ((CharSequence)text).length();
            for (int i = 0; i < n; ++i) {
                int color = i < midPoint ? color1 : color2;
                result.append((Text)Text.literal((String)String.valueOf(text.charAt(i))).styled(arg_0 -> Companion.halfSplitGradient$lambda$0(color, bold, arg_0)));
            }
            return (Text)result;
        }

        private final Text fullGradient(String text, int color1, int color2, boolean bold) {
            MutableText mutableText2 = Text.empty();
            Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"empty(...)");
            MutableText result = mutableText2;
            int divisor = Math.max(1, text.length() - 1);
            int n = ((CharSequence)text).length();
            for (int i = 0; i < n; ++i) {
                float ratio = (float)i / (float)divisor;
                int color = ColorEngine.lerpColor(color1, color2, ratio);
                result.append((Text)Text.literal((String)String.valueOf(text.charAt(i))).styled(arg_0 -> Companion.fullGradient$lambda$0(color, bold, arg_0)));
            }
            return (Text)result;
        }

        private final Text astolfoGradient(String text, boolean bold) {
            MutableText mutableText2 = Text.empty();
            Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"empty(...)");
            MutableText result = mutableText2;
            int n = ((CharSequence)text).length();
            for (int i = 0; i < n; ++i) {
                int color = ColorEngine.astolfo(10, i, 0.7f, 0.7f, 1.0f);
                result.append((Text)Text.literal((String)String.valueOf(text.charAt(i))).styled(arg_0 -> Companion.astolfoGradient$lambda$0(color, bold, arg_0)));
            }
            return (Text)result;
        }

        private final Text twoColorFade(String text, int color1, int color2, boolean bold) {
            MutableText mutableText2 = Text.empty();
            Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"empty(...)");
            MutableText result = mutableText2;
            int divisor = Math.max(1, text.length() - 1);
            int n = ((CharSequence)text).length();
            for (int i = 0; i < n; ++i) {
                float ratio = (float)i / (float)divisor;
                int color = ColorEngine.lerpColor(color1, color2, ratio);
                result.append((Text)Text.literal((String)String.valueOf(text.charAt(i))).styled(arg_0 -> Companion.twoColorFade$lambda$0(color, bold, arg_0)));
            }
            return (Text)result;
        }

        @JvmStatic
        @NotNull
        public final Text applyPredefinedGradient(@Nullable String text, @Nullable String gradientName, boolean bold) {
            String gradient;
            String string;
            block76: {
                block75: {
                    string = gradientName;
                    if (string == null) break block75;
                    String string2 = string;
                    Locale locale = Locale.ROOT;
                    Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
                    String string3 = string2.toLowerCase(locale);
                    Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toLowerCase(...)");
                    string = string3;
                    if (string3 != null) break block76;
                }
                string = "";
            }
            return switch (gradient = string) {
                case "red_blue" -> this.applyGradient(text, GradientStyle.HALF_SPLIT, -49088, ColorEngine.hex("#0000FF"), bold);
                case "green_purple" -> this.applyGradient(text, GradientStyle.HALF_SPLIT, -12517568, ColorEngine.hex("#800080"), bold);
                case "yellow_cyan" -> this.applyGradient(text, GradientStyle.FULL_GRADIENT, -192, ColorEngine.hex("#00FFFF"), bold);
                case "orange_magenta" -> this.applyGradient(text, GradientStyle.FULL_GRADIENT, -32736, ColorEngine.hex("#FF00FF"), bold);
                case "astolfo" -> this.applyGradient(text, GradientStyle.ASTOLFO, 0, 0, bold);
                case "blue_green_fade" -> this.applyGradient(text, GradientStyle.TWO_COLOR_FADE, ColorEngine.hex("#0000FF"), -12517568, bold);
                case "purple_red_fade" -> this.applyGradient(text, GradientStyle.TWO_COLOR_FADE, ColorEngine.hex("#800080"), -49088, bold);
                case "cyan_orange_fade" -> this.applyGradient(text, GradientStyle.TWO_COLOR_FADE, ColorEngine.hex("#00FFFF"), -32736, bold);
                case "white_black" -> this.applyGradient(text, GradientStyle.FULL_GRADIENT, -1, -15066598, bold);
                case "custom_purple" -> this.applyGradient(text, GradientStyle.FULL_GRADIENT, -8231726, -10797100, bold);
                case "black_light_purple" -> this.applyGradient(text, GradientStyle.FULL_GRADIENT, -15066598, ColorEngine.hex("#DA70D6"), bold);
                case "dark_red_bright_red" -> this.applyGradient(text, GradientStyle.FULL_GRADIENT, ColorEngine.hex("#8B0000"), -49088, bold);
                case "dark_red" -> this.applyGradient(text, GradientStyle.HALF_SPLIT, ColorEngine.hex("#8B0000"), ColorEngine.hex("#8B0000"), bold);
                case "red_white" -> this.applyGradient(text, GradientStyle.HALF_SPLIT, -49088, -1, bold);
                case "purple_bright_pink" -> this.applyGradient(text, GradientStyle.FULL_GRADIENT, ColorEngine.hex("#800080"), ColorEngine.hex("#FF69B4"), bold);
                case "pink_dark_pink" -> this.applyGradient(text, GradientStyle.FULL_GRADIENT, ColorEngine.hex("#FFC1CC"), ColorEngine.hex("#C71585"), bold);
                case "bright_red" -> this.applyGradient(text, GradientStyle.HALF_SPLIT, -49088, -49088, bold);
                case "dark_green_bright_green" -> this.applyGradient(text, GradientStyle.FULL_GRADIENT, ColorEngine.hex("#006400"), -12517568, bold);
                case "red_orange" -> this.applyGradient(text, GradientStyle.FULL_GRADIENT, -49088, -32736, bold);
                case "orange_white" -> this.applyGradient(text, GradientStyle.FULL_GRADIENT, -32736, -1, bold);
                case "gold_white" -> this.applyGradient(text, GradientStyle.FULL_GRADIENT, ColorEngine.hex("#FFD700"), -1, bold);
                case "turquoise_blue" -> this.applyGradient(text, GradientStyle.FULL_GRADIENT, ColorEngine.hex("#40E0D0"), ColorEngine.hex("#0000FF"), bold);
                default -> {
                    String v4 = text;
                    if (v4 == null) {
                        v4 = "";
                    }
                    MutableText v5 = Text.literal((String)v4).styled(arg_0 -> Companion.applyPredefinedGradient$lambda$0(bold, arg_0));
                    Intrinsics.checkNotNullExpressionValue((Object)v5, (String)"withStyle(...)");
                    yield (Text)v5;
                }
            };
        }

        private static final Style halfSplitGradient$lambda$0(int $color, boolean $bold, Style it) {
            Intrinsics.checkNotNullParameter((Object)it, (String)"it");
            return it.withColor($color).withBold(Boolean.valueOf($bold));
        }

        private static final Style fullGradient$lambda$0(int $color, boolean $bold, Style it) {
            Intrinsics.checkNotNullParameter((Object)it, (String)"it");
            return it.withColor($color).withBold(Boolean.valueOf($bold));
        }

        private static final Style astolfoGradient$lambda$0(int $color, boolean $bold, Style it) {
            Intrinsics.checkNotNullParameter((Object)it, (String)"it");
            return it.withColor($color).withBold(Boolean.valueOf($bold));
        }

        private static final Style twoColorFade$lambda$0(int $color, boolean $bold, Style it) {
            Intrinsics.checkNotNullParameter((Object)it, (String)"it");
            return it.withColor($color).withBold(Boolean.valueOf($bold));
        }

        private static final Style applyPredefinedGradient$lambda$0(boolean $bold, Style it) {
            Intrinsics.checkNotNullParameter((Object)it, (String)"it");
            return it.withColor(-1).withBold(Boolean.valueOf($bold));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @Metadata(mv={2, 4, 0}, k=3, xi=48)
        public static final class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] nArray = new int[GradientStyle.values().length];
                try {
                    nArray[GradientStyle.HALF_SPLIT.ordinal()] = 1;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[GradientStyle.FULL_GRADIENT.ordinal()] = 2;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[GradientStyle.ASTOLFO.ordinal()] = 3;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[GradientStyle.TWO_COLOR_FADE.ordinal()] = 4;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                $EnumSwitchMapping$0 = nArray;
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/utils/string/chat/helper/TextHelper$GradientStyle;", "", "<init>", "(Ljava/lang/String;I)V", "HALF_SPLIT", "FULL_GRADIENT", "ASTOLFO", "TWO_COLOR_FADE", "rtx.kimiko:kimiko"})
    public static enum GradientStyle {
        HALF_SPLIT,
        FULL_GRADIENT,
        ASTOLFO,
        TWO_COLOR_FADE;

        @NotNull
        public static EnumEntries<GradientStyle> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }
    }
}

