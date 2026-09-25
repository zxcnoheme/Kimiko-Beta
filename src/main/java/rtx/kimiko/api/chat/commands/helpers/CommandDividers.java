/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.text.MutableText
 *  net.minecraft.text.StringVisitable
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.chat.commands.helpers;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.StringVisitable;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.chat.commands.helpers.AnimatedChatText;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u001c\n\u0002\b\u000b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ#\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\fJ\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\u000fJ#\u0010\b\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\u0010J#\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0011\u0010\fJ#\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0010J#\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0011\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0015J\u001b\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0018\u0010\u0015J\u001b\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0018\u0010\u0017J)\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u001aH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u001fR\u0014\u0010!\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010\u001fR\u0014\u0010\"\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010#\u00a8\u0006%"}, d2={"Lrtx/kimiko/api/chat/commands/helpers/CommandDividers;", "", "<init>", "()V", "", "title", "Lnet/minecraft/MutableText;", "Lkotlin/jvm/JvmStatic;", "header", "(Ljava/lang/String;)Lnet/minecraft/MutableText;", "", "lineCount", "(Ljava/lang/String;I)Lnet/minecraft/MutableText;", "Lnet/minecraft/Text;", "titleComponent", "(Lnet/minecraft/Text;)Lnet/minecraft/MutableText;", "(Lnet/minecraft/Text;I)Lnet/minecraft/MutableText;", "footer", "titleWidth", "footerWithTitleWidth", "(II)Lnet/minecraft/MutableText;", "()Lnet/minecraft/MutableText;", "footerSimple", "(I)Lnet/minecraft/MutableText;", "simple", "totalLineCount", "", "contentLines", "calcLineCountForContent", "(Ljava/lang/String;Ljava/lang/Iterable;)I", "DIAMOND", "Ljava/lang/String;", "STAR", "LINE_CHAR", "DEFAULT_LINE_COUNT", "I", "MIN_LINE_COUNT", "rtx.kimiko:kimiko"})
public final class CommandDividers {
    @NotNull
    public static final CommandDividers INSTANCE = new CommandDividers();
    @NotNull
    private static final String DIAMOND = "\u2756";
    @NotNull
    private static final String STAR = "\u2726";
    @NotNull
    private static final String LINE_CHAR = "\u2501";
    private static final int DEFAULT_LINE_COUNT = 6;
    private static final int MIN_LINE_COUNT = 3;

    private CommandDividers() {
    }

    @JvmStatic
    @NotNull
    public static final MutableText header(@NotNull String title) {
        Intrinsics.checkNotNullParameter((Object)title, (String)"title");
        return CommandDividers.header(title, 6);
    }

    @JvmStatic
    @NotNull
    public static final MutableText header(@NotNull String title, int lineCount) {
        Intrinsics.checkNotNullParameter((Object)title, (String)"title");
        int count = Math.max(3, lineCount);
        String line = " " + StringsKt.repeat((CharSequence)LINE_CHAR, (int)count) + " ";
        MutableText mutableText2 = Text.literal((String)DIAMOND).fillStyle(AnimatedChatText.decorStyle()).append((Text)Text.literal((String)line).fillStyle(AnimatedChatText.lineStyle())).append((Text)Text.literal((String)title).fillStyle(AnimatedChatText.titleStyle())).append((Text)Text.literal((String)line).fillStyle(AnimatedChatText.lineStyle())).append((Text)Text.literal((String)DIAMOND).fillStyle(AnimatedChatText.decorStyle()));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"append(...)");
        return mutableText2;
    }

    @JvmStatic
    @NotNull
    public static final MutableText header(@NotNull Text titleComponent) {
        Intrinsics.checkNotNullParameter((Object)titleComponent, (String)"titleComponent");
        return CommandDividers.header(titleComponent, 6);
    }

    @JvmStatic
    @NotNull
    public static final MutableText header(@NotNull Text titleComponent, int lineCount) {
        Intrinsics.checkNotNullParameter((Object)titleComponent, (String)"titleComponent");
        int count = Math.max(3, lineCount);
        String line = " " + StringsKt.repeat((CharSequence)LINE_CHAR, (int)count) + " ";
        MutableText mutableText2 = Text.literal((String)DIAMOND).fillStyle(AnimatedChatText.decorStyle()).append((Text)Text.literal((String)line).fillStyle(AnimatedChatText.lineStyle())).append(titleComponent).append((Text)Text.literal((String)line).fillStyle(AnimatedChatText.lineStyle())).append((Text)Text.literal((String)DIAMOND).fillStyle(AnimatedChatText.decorStyle()));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"append(...)");
        return mutableText2;
    }

    @JvmStatic
    @NotNull
    public static final MutableText footer(@NotNull String title, int lineCount) {
        Intrinsics.checkNotNullParameter((Object)title, (String)"title");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.textRenderer == null) {
            return CommandDividers.footerSimple(lineCount);
        }
        int count = Math.max(3, lineCount);
        int titleWidth = mc.textRenderer.getWidth(title);
        int starWidth = mc.textRenderer.getWidth(STAR);
        int lineCharWidth = mc.textRenderer.getWidth(LINE_CHAR);
        int widthDiff = titleWidth - starWidth;
        int extraPixelsPerSide = widthDiff / 2;
        int extraCharsPerSide = Math.max(0, (extraPixelsPerSide + lineCharWidth - 1) / lineCharWidth);
        String footerLine = " " + StringsKt.repeat((CharSequence)LINE_CHAR, (int)(count + extraCharsPerSide)) + " ";
        MutableText mutableText2 = Text.literal((String)DIAMOND).fillStyle(AnimatedChatText.decorStyle()).append((Text)Text.literal((String)footerLine).fillStyle(AnimatedChatText.lineStyle())).append((Text)Text.literal((String)STAR).fillStyle(AnimatedChatText.decorStyle())).append((Text)Text.literal((String)footerLine).fillStyle(AnimatedChatText.lineStyle())).append((Text)Text.literal((String)DIAMOND).fillStyle(AnimatedChatText.decorStyle()));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"append(...)");
        return mutableText2;
    }

    @JvmStatic
    @NotNull
    public static final MutableText footer(@NotNull Text titleComponent, int lineCount) {
        Intrinsics.checkNotNullParameter((Object)titleComponent, (String)"titleComponent");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.textRenderer == null) {
            return CommandDividers.footerSimple(lineCount);
        }
        int titleWidth = mc.textRenderer.getWidth((StringVisitable)titleComponent);
        return CommandDividers.footerWithTitleWidth(titleWidth, lineCount);
    }

    @JvmStatic
    @NotNull
    public static final MutableText footerWithTitleWidth(int titleWidth, int lineCount) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.textRenderer == null) {
            return CommandDividers.footerSimple(lineCount);
        }
        int count = Math.max(3, lineCount);
        int starWidth = mc.textRenderer.getWidth(STAR);
        int lineCharWidth = mc.textRenderer.getWidth(LINE_CHAR);
        int widthDiff = titleWidth - starWidth;
        int extraPixelsPerSide = widthDiff / 2;
        int extraCharsPerSide = Math.max(0, (extraPixelsPerSide + lineCharWidth - 1) / lineCharWidth);
        String footerLine = " " + StringsKt.repeat((CharSequence)LINE_CHAR, (int)(count + extraCharsPerSide)) + " ";
        MutableText mutableText2 = Text.literal((String)DIAMOND).fillStyle(AnimatedChatText.decorStyle()).append((Text)Text.literal((String)footerLine).fillStyle(AnimatedChatText.lineStyle())).append((Text)Text.literal((String)STAR).fillStyle(AnimatedChatText.decorStyle())).append((Text)Text.literal((String)footerLine).fillStyle(AnimatedChatText.lineStyle())).append((Text)Text.literal((String)DIAMOND).fillStyle(AnimatedChatText.decorStyle()));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"append(...)");
        return mutableText2;
    }

    @JvmStatic
    @NotNull
    public static final MutableText footer() {
        return CommandDividers.footerSimple(6);
    }

    @JvmStatic
    @NotNull
    public static final MutableText footerSimple(int lineCount) {
        int count = Math.max(3, lineCount);
        String line = " " + StringsKt.repeat((CharSequence)LINE_CHAR, (int)count) + " ";
        MutableText mutableText2 = Text.literal((String)DIAMOND).fillStyle(AnimatedChatText.decorStyle()).append((Text)Text.literal((String)line).fillStyle(AnimatedChatText.lineStyle())).append((Text)Text.literal((String)STAR).fillStyle(AnimatedChatText.decorStyle())).append((Text)Text.literal((String)line).fillStyle(AnimatedChatText.lineStyle())).append((Text)Text.literal((String)DIAMOND).fillStyle(AnimatedChatText.decorStyle()));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"append(...)");
        return mutableText2;
    }

    @JvmStatic
    @NotNull
    public static final MutableText simple() {
        return CommandDividers.simple(14);
    }

    @JvmStatic
    @NotNull
    public static final MutableText simple(int totalLineCount) {
        String line = " " + StringsKt.repeat((CharSequence)LINE_CHAR, (int)Math.max(6, totalLineCount)) + " ";
        MutableText mutableText2 = Text.literal((String)DIAMOND).fillStyle(AnimatedChatText.decorStyle()).append((Text)Text.literal((String)line).fillStyle(AnimatedChatText.lineStyle())).append((Text)Text.literal((String)DIAMOND).fillStyle(AnimatedChatText.decorStyle()));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"append(...)");
        return mutableText2;
    }

    @JvmStatic
    public static final int calcLineCountForContent(@NotNull String title, @NotNull Iterable<String> contentLines) {
        Intrinsics.checkNotNullParameter((Object)title, (String)"title");
        Intrinsics.checkNotNullParameter(contentLines, (String)"contentLines");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.textRenderer == null) {
            return 6;
        }
        int maxContentWidth = 0;
        for (String line : contentLines) {
            int width = mc.textRenderer.getWidth(line);
            if (width <= maxContentWidth) continue;
            maxContentWidth = width;
        }
        int diamondWidth = mc.textRenderer.getWidth(DIAMOND);
        int lineCharWidth = mc.textRenderer.getWidth(LINE_CHAR);
        int spaceWidth = mc.textRenderer.getWidth(" ");
        int titleWidth = mc.textRenderer.getWidth(title);
        int fixedWidth = 2 * diamondWidth + 4 * spaceWidth + titleWidth;
        int neededLineWidth = Math.max(0, maxContentWidth - fixedWidth);
        int lineCount = Math.max(3, neededLineWidth / 2 / lineCharWidth + 1);
        return Math.min(lineCount, 20);
    }
}

