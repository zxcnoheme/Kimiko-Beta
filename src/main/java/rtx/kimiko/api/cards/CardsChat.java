/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.util.Formatting
 *  net.minecraft.text.ClickEvent
 *  net.minecraft.text.ClickEvent.RunCommand
 *  net.minecraft.text.Text
 *  net.minecraft.text.HoverEvent
 *  net.minecraft.text.HoverEvent.ShowText
 *  net.minecraft.text.Style
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.text.MutableText
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.cards;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.util.Formatting;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Text;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.cards.CardsInvite;
import rtx.kimiko.api.chat.commands.CommandManager;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.utils.string.chat.helper.TextHelper;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\fJ%\u0010\u0010\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0013\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\rH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/api/cards/CardsChat;", "", "<init>", "()V", "Lnet/minecraft/MutableText;", "Lkotlin/jvm/JvmStatic;", "brand", "()Lnet/minecraft/MutableText;", "Lrtx/kimiko/api/cards/CardsInvite;", "invite", "", "printInvite", "(Lrtx/kimiko/api/cards/CardsInvite;)V", "", "level", "message", "printNotice", "(Ljava/lang/String;Ljava/lang/String;)V", "s", "safe", "(Ljava/lang/String;)Ljava/lang/String;", "rtx.kimiko:kimiko"})
public final class CardsChat {
    @NotNull
    public static final CardsChat INSTANCE = new CardsChat();

    private CardsChat() {
    }

    @JvmStatic
    @NotNull
    public static final MutableText brand() {
        Text text2 = TextHelper.Companion.applyPredefinedGradient("[Карты] ", "purple_bright_pink", false);
        Intrinsics.checkNotNull((Object)text2, (String)"null cannot be cast to non-null type net.minecraft.network.chat.MutableComponent");
        return (MutableText)text2;
    }

    @JvmStatic
    public static final void printInvite(@NotNull CardsInvite invite) {
        Intrinsics.checkNotNullParameter((Object)invite, (String)"invite");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.player == null) {
            return;
        }
        String p = CommandManager.Companion.get().getPrefix();
        MutableText mutableText2 = Text.literal((String)(" [" + I18n.tr("Принять") + "]")).styled(arg_0 -> CardsChat.printInvite$lambda$0(p, invite, arg_0));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"withStyle(...)");
        MutableText accept = mutableText2;
        MutableText mutableText3 = Text.literal((String)(" [" + I18n.tr("Отклонить") + "]")).styled(arg_0 -> CardsChat.printInvite$lambda$1(p, invite, arg_0));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText3, (String)"withStyle(...)");
        MutableText decline = mutableText3;
        MutableText mutableText4 = CardsChat.brand().append((Text)Text.literal((String)INSTANCE.safe(invite.fromName())).formatted(Formatting.WHITE)).append((Text)Text.literal((String)I18n.tr(" приглашает вас сыграть в ")).formatted(Formatting.GRAY)).append((Text)Text.literal((String)I18n.tr("«Дурака»")).formatted(Formatting.AQUA)).append((Text)accept).append((Text)decline);
        Intrinsics.checkNotNullExpressionValue((Object)mutableText4, (String)"append(...)");
        MutableText line = mutableText4;
        CommandManager.Companion.get().sendRaw((Text)line);
    }

    @JvmStatic
    public static final void printNotice(@Nullable String level, @NotNull String message) {
        String string;
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.player == null) {
            return;
        }
        String string2 = level;
        if (string2 == null) {
            string2 = "";
        }
        Formatting color = Intrinsics.areEqual((Object)(string = string2), (Object)"success") ? Formatting.GREEN : (Intrinsics.areEqual((Object)string, (Object)"error") ? Formatting.RED : Formatting.GRAY);
        CommandManager commandManager = CommandManager.Companion.get();
        MutableText mutableText2 = CardsChat.brand().append((Text)Text.literal((String)message).formatted(color));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"append(...)");
        commandManager.sendRaw((Text)mutableText2);
    }

    private final String safe(String s) {
        String string = s;
        if (string == null || (string = String.valueOf(string).replace((char)'\u00a7', (char)' ')) == null) {
            string = "";
        }
        return string;
    }

    private static final Style printInvite$lambda$0(String $p, CardsInvite $invite, Style s) {
        Intrinsics.checkNotNullParameter((Object)s, (String)"s");
        return s.withColor(Formatting.GREEN).withBold(Boolean.valueOf(true)).withHoverEvent((HoverEvent)new HoverEvent.ShowText((Text)Text.literal((String)I18n.tr("Сесть за стол")).formatted(Formatting.GRAY))).withClickEvent((ClickEvent)new ClickEvent.RunCommand($p + "cards accept " + $invite.id()));
    }

    private static final Style printInvite$lambda$1(String $p, CardsInvite $invite, Style s) {
        Intrinsics.checkNotNullParameter((Object)s, (String)"s");
        return s.withColor(Formatting.RED).withBold(Boolean.valueOf(true)).withHoverEvent((HoverEvent)new HoverEvent.ShowText((Text)Text.literal((String)I18n.tr("Отклонить приглашение")).formatted(Formatting.GRAY))).withClickEvent((ClickEvent)new ClickEvent.RunCommand($p + "cards decline " + $invite.id()));
    }
}

