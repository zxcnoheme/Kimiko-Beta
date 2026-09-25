/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.Formatting
 *  net.minecraft.text.ClickEvent
 *  net.minecraft.text.ClickEvent.RunCommand
 *  net.minecraft.text.ClickEvent.SuggestCommand
 *  net.minecraft.text.Text
 *  net.minecraft.text.HoverEvent
 *  net.minecraft.text.HoverEvent.ShowText
 *  net.minecraft.text.Style
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.text.MutableText
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.party;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Formatting;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Text;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.chat.commands.CommandManager;
import rtx.kimiko.api.chat.commands.helpers.CommandDividers;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.party.PartyMember;
import rtx.kimiko.api.party.PartySnapshot;
import rtx.kimiko.utils.chat.ChatMessage;
import rtx.kimiko.utils.string.chat.helper.TextHelper;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J/\u0010\n\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ'\u0010\r\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u0010\u001a\u00020\u000fH\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J%\u0010\u0017\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u0017\u0010\u000eJ\u001d\u0010\u001a\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0019\u0010\u001d\u001a\u00020\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001e\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/api/party/PartyChat;", "", "<init>", "()V", "", "from", "partyName", "inviteId", "", "Lkotlin/jvm/JvmStatic;", "printInvite", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "text", "printChat", "(Ljava/lang/String;Ljava/lang/String;)V", "Lnet/minecraft/MutableText;", "brand", "()Lnet/minecraft/MutableText;", "name", "nick", "(Ljava/lang/String;)Lnet/minecraft/MutableText;", "level", "message", "printNotice", "Lrtx/kimiko/api/party/PartySnapshot;", "snap", "renderInfo", "(Lrtx/kimiko/api/party/PartySnapshot;)V", "s", "safe", "(Ljava/lang/String;)Ljava/lang/String;", "rtx.kimiko:kimiko"})
public final class PartyChat {
    @NotNull
    public static final PartyChat INSTANCE = new PartyChat();

    private PartyChat() {
    }

    @JvmStatic
    public static final void printInvite(@Nullable String from, @Nullable String partyName, @NotNull String inviteId) {
        Intrinsics.checkNotNullParameter((Object)inviteId, (String)"inviteId");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.player == null) {
            return;
        }
        String p = CommandManager.Companion.get().getPrefix();
        MutableText mutableText2 = Text.literal((String)(" [" + I18n.tr("Принять") + "]")).styled(arg_0 -> PartyChat.printInvite$lambda$0(p, inviteId, arg_0));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"withStyle(...)");
        MutableText accept = mutableText2;
        MutableText mutableText3 = Text.literal((String)(" [" + I18n.tr("Отклонить") + "]")).styled(arg_0 -> PartyChat.printInvite$lambda$1(p, inviteId, arg_0));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText3, (String)"withStyle(...)");
        MutableText decline = mutableText3;
        MutableText mutableText4 = ChatMessage.brandmessage().append((Text)Text.literal((String)INSTANCE.safe(from)).formatted(Formatting.WHITE)).append((Text)Text.literal((String)I18n.tr(" пригласил вас в Party ")).formatted(Formatting.GRAY)).append((Text)Text.literal((String)("«" + INSTANCE.safe(partyName) + "»")).formatted(Formatting.AQUA)).append((Text)accept).append((Text)decline);
        Intrinsics.checkNotNullExpressionValue((Object)mutableText4, (String)"append(...)");
        MutableText line = mutableText4;
        CommandManager.Companion.get().sendRaw((Text)line);
    }

    @JvmStatic
    public static final void printChat(@Nullable String from, @Nullable String text) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.player == null) {
            return;
        }
        MutableText mutableText2 = PartyChat.brand().append((Text)INSTANCE.nick(INSTANCE.safe(from))).append((Text)Text.literal((String)" \u2192 ").formatted(Formatting.DARK_GRAY)).append((Text)Text.literal((String)INSTANCE.safe(text)).formatted(Formatting.GRAY));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"append(...)");
        MutableText line = mutableText2;
        CommandManager.Companion.get().sendRaw((Text)line);
    }

    @JvmStatic
    @NotNull
    public static final MutableText brand() {
        Text text2 = TextHelper.Companion.applyPredefinedGradient("[Party] ", "purple_bright_pink", false);
        Intrinsics.checkNotNull((Object)text2, (String)"null cannot be cast to non-null type net.minecraft.network.chat.MutableComponent");
        return (MutableText)text2;
    }

    private final MutableText nick(String name) {
        String p = CommandManager.Companion.get().getPrefix();
        MutableText mutableText2 = Text.literal((String)name).styled(arg_0 -> PartyChat.nick$lambda$0(p, arg_0));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"withStyle(...)");
        return mutableText2;
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
        MutableText mutableText2 = ChatMessage.brandmessage().append((Text)Text.literal((String)message).formatted(color));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"append(...)");
        commandManager.sendRaw((Text)mutableText2);
    }

    @JvmStatic
    public static final void renderInfo(@Nullable PartySnapshot snap) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.player == null) {
            return;
        }
        if (snap == null || !snap.exists()) {
            PartyChat.printNotice("error", I18n.tr("Вы не состоите в Party."));
            return;
        }
        String string = mc.getSession() != null && mc.getSession().getUsername() != null ? mc.getSession().getUsername() : "Player";
        Intrinsics.checkNotNull((Object)string);
        String myName = string;
        boolean iLead = snap.isLeader(myName);
        String p = CommandManager.Companion.get().getPrefix();
        String title = "PARTY " + snap.name();
        ArrayList<String> rowStrings = new ArrayList<>();
        rowStrings.add(I18n.tr("Участников: ") + snap.members().size() + "/" + snap.max());
        for (PartyMember m : snap.members()) {
            rowStrings.add("● " + m.name() + (m.leader() ? " [" + I18n.tr("Лидер") + "]" : "") + (iLead && !m.leader() ? "  [" + I18n.tr("Кик") + "]" : ""));
        }
        int lineCount = CommandDividers.calcLineCountForContent(title, rowStrings);
        CommandManager mgr = CommandManager.Companion.get();
        mgr.sendRaw(CommandDividers.header(title, lineCount));
        MutableText mutableText2 = Text.literal(I18n.tr("Участников: ")).formatted(Formatting.GRAY).append(Text.literal(snap.members().size() + "/" + snap.max()).formatted(Formatting.WHITE));
        mgr.sendRaw(mutableText2);
        for (PartyMember m : snap.members()) {
            MutableText row = Text.empty().append(Text.literal("● ").formatted(m.online() ? Formatting.GREEN : Formatting.DARK_GRAY)).append(Text.literal(m.name()).formatted(m.online() ? Formatting.WHITE : Formatting.GRAY));
            if (m.leader()) {
                row.append(Text.literal(" [" + I18n.tr("Лидер") + "]").formatted(Formatting.GOLD));
            }
            if (iLead && !m.leader()) {
                row.append(Text.literal("  [" + I18n.tr("Кик") + "]").styled(arg_0 -> PartyChat.renderInfo$lambda$0(m, p, arg_0)));
            }
            mgr.sendRaw(row);
        }
        mgr.sendRaw(CommandDividers.footer(title, lineCount));
    }

    private final String safe(String s) {
        String string = s;
        if (string == null) {
            string = "";
        }
        return string;
    }

    private static final Style printInvite$lambda$0(String $p, String $inviteId, Style s) {
        Intrinsics.checkNotNullParameter((Object)s, (String)"s");
        return s.withColor(Formatting.GREEN).withBold(Boolean.valueOf(true)).withHoverEvent((HoverEvent)new HoverEvent.ShowText((Text)Text.literal((String)I18n.tr("Вступить в Party")).formatted(Formatting.GRAY))).withClickEvent((ClickEvent)new ClickEvent.RunCommand($p + "party accept " + $inviteId));
    }

    private static final Style printInvite$lambda$1(String $p, String $inviteId, Style s) {
        Intrinsics.checkNotNullParameter((Object)s, (String)"s");
        return s.withColor(Formatting.RED).withBold(Boolean.valueOf(true)).withHoverEvent((HoverEvent)new HoverEvent.ShowText((Text)Text.literal((String)I18n.tr("Отклонить приглашение")).formatted(Formatting.GRAY))).withClickEvent((ClickEvent)new ClickEvent.RunCommand($p + "party decline " + $inviteId));
    }

    private static final Style nick$lambda$0(String $p, Style style) {
        Intrinsics.checkNotNullParameter((Object)style, (String)"style");
        return style.withColor(Formatting.WHITE).withClickEvent((ClickEvent)new ClickEvent.SuggestCommand($p + "pc ")).withHoverEvent((HoverEvent)new HoverEvent.ShowText((Text)Text.literal((String)I18n.tr("Написать в Party")).formatted(Formatting.GRAY)));
    }

    private static final Style renderInfo$lambda$0(PartyMember $m, String $p, Style s) {
        Intrinsics.checkNotNullParameter((Object)s, (String)"s");
        Object[] objectArray = new Object[]{$m.name()};
        return s.withColor(Formatting.RED).withHoverEvent((HoverEvent)new HoverEvent.ShowText((Text)Text.literal((String)I18n.tr("Исключить %s", objectArray)).formatted(Formatting.GRAY))).withClickEvent((ClickEvent)new ClickEvent.RunCommand($p + "party kick " + $m.name()));
    }
}

