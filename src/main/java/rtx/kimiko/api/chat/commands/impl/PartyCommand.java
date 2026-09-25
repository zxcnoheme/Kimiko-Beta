/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  kotlin.Metadata
 *  kotlin.collections.ArraysKt
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.util.Formatting
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.network.PlayerListEntry
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.chat.commands.impl;

import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.util.Formatting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.chat.commands.Command;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Utils.Party;
import rtx.kimiko.api.party.PartyChat;
import rtx.kimiko.api.party.PartyClient;
import rtx.kimiko.api.party.PartyMember;
import rtx.kimiko.api.party.PartySnapshot;
import rtx.kimiko.utils.storage.friend.FriendUtils;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u000e\u001a\u00020\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0010\u001a\u00020\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u001d\u0010\u0011\u001a\u00020\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0003J\u000f\u0010\u0013\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0003J\u000f\u0010\u0014\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0003J\u001d\u0010\u0015\u001a\u00020\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u000fJ%\u0010\u0017\u001a\u00020\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\u0016\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J+\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u00192\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00040\u001cH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040\u001cH\u0002\u00a2\u0006\u0004\b\u001f\u0010\u001eJ\u0015\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040\u001cH\u0016\u00a2\u0006\u0004\b \u0010\u001e\u00a8\u0006!"}, d2={"Lrtx/kimiko/api/chat/commands/impl/PartyCommand;", "Lrtx/kimiko/api/chat/commands/Command;", "<init>", "()V", "", "label", "", "args", "", "execute", "(Ljava/lang/String;[Ljava/lang/String;)V", "", "ensureLink", "()Z", "handleCreate", "([Ljava/lang/String;)V", "handleInvite", "handleChat", "handleLeave", "handleDisband", "handleInfo", "handleKick", "accept", "handleRespond", "([Ljava/lang/String;Z)V", "Ljava/util/stream/Stream;", "tabComplete", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/util/stream/Stream;", "", "inviteCandidates", "()Ljava/util/List;", "memberNames", "getLongDesc", "rtx.kimiko:kimiko"})
public final class PartyCommand
extends Command {
    public PartyCommand() {
        super("party", "Система Party: create / invite / info / kick / leave / disband.", new String[]{"p"});
    }

    @Override
    public void execute(@NotNull String label, @NotNull String[] args) {
        Intrinsics.checkNotNullParameter((Object)label, (String)"label");
        Intrinsics.checkNotNullParameter((Object)args, (String)"args");
        if (args.length == 0) {
            this.usage();
            return;
        }
        String string = args[0].toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toLowerCase(...)");
        switch (string) {
            case "create": {
                this.handleCreate(args);
                break;
            }
            case "invite": {
                this.handleInvite(args);
                break;
            }
            case "leave": {
                this.handleLeave();
                break;
            }
            case "disband": {
                this.handleDisband();
                break;
            }
            case "info": {
                this.handleInfo();
                break;
            }
            case "msg": 
            case "c": 
            case "chat": {
                this.handleChat(args);
                break;
            }
            case "kick": {
                this.handleKick(args);
                break;
            }
            case "accept": {
                this.handleRespond(args, true);
                break;
            }
            case "decline": {
                this.handleRespond(args, false);
                break;
            }
            default: {
                this.usage();
            }
        }
    }

    private final boolean ensureLink() {
        if (PartyClient.INSTANCE.isConnected()) {
            return true;
        }
        Party party = ModuleManager.Companion.get().get(Party.class);
        if (party != null && !party.isEnabled()) {
            party.enable();
            this.logDirect(I18n.tr("Модуль Party включён — подключаюсь к серверу. Повторите команду через пару секунд."), Formatting.YELLOW);
        } else {
            this.logDirect(I18n.tr("Нет связи с Party-сервером. Попробуйте позже."), Formatting.RED);
        }
        return false;
    }

    private final void handleCreate(String[] args) {
        if (args.length < 2) {
            this.logDirect(I18n.tr("Использование: party create <название>"), Formatting.RED);
            return;
        }
        String name = ((Object)StringsKt.trim((CharSequence)String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length)))).toString();
        if (((CharSequence)name).length() == 0) {
            this.logDirect(I18n.tr("Укажите название Party."), Formatting.RED);
            return;
        }
        if (this.ensureLink()) {
            PartyClient.INSTANCE.create(name);
        }
    }

    private final void handleInvite(String[] args) {
        if (args.length < 2) {
            this.logDirect(I18n.tr("Использование: party invite <ник>"), Formatting.RED);
            return;
        }
        String target = ((Object)StringsKt.trim((CharSequence)args[1])).toString();
        if (((CharSequence)target).length() == 0) {
            this.logDirect(I18n.tr("Укажите ник игрока."), Formatting.RED);
            return;
        }
        if (this.ensureLink()) {
            PartyClient.INSTANCE.inviteUser(target);
        }
    }

    private final void handleChat(String[] args) {
        if (args.length < 2) {
            this.logDirect(I18n.tr("Использование: party chat <сообщение>"), Formatting.RED);
            return;
        }
        String text = ((Object)StringsKt.trim((CharSequence)String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length)))).toString();
        if (((CharSequence)text).length() == 0) {
            this.logDirect(I18n.tr("Введите сообщение."), Formatting.RED);
            return;
        }
        if (this.ensureLink()) {
            PartyClient.INSTANCE.sendChat(text);
        }
    }

    private final void handleLeave() {
        if (this.ensureLink()) {
            PartyClient.INSTANCE.leave();
        }
    }

    private final void handleDisband() {
        if (this.ensureLink()) {
            PartyClient.INSTANCE.disband();
        }
    }

    private final void handleInfo() {
        PartyChat.renderInfo(PartyClient.INSTANCE.snapshot());
    }

    private final void handleKick(String[] args) {
        if (args.length < 2) {
            this.logDirect(I18n.tr("Использование: party kick <ник>"), Formatting.RED);
            return;
        }
        String target = ((Object)StringsKt.trim((CharSequence)args[1])).toString();
        if (((CharSequence)target).length() == 0) {
            this.logDirect(I18n.tr("Укажите ник игрока."), Formatting.RED);
            return;
        }
        if (this.ensureLink()) {
            PartyClient.INSTANCE.kick(target);
        }
    }

    private final void handleRespond(String[] args, boolean accept) {
        if (args.length < 2) {
            this.logDirect(I18n.tr("Нет идентификатора приглашения."), Formatting.RED);
            return;
        }
        if (this.ensureLink()) {
            PartyClient.INSTANCE.respondInvite(((Object)StringsKt.trim((CharSequence)args[1])).toString(), accept);
        }
    }

    @Override
    @NotNull
    public Stream<String> tabComplete(@NotNull String label, @NotNull String[] args) {
        Intrinsics.checkNotNullParameter((Object)label, (String)"label");
        Intrinsics.checkNotNullParameter((Object)args, (String)"args");
        if (args.length == 1) {
            String[] stringArray = new String[]{"create", "invite", "info", "chat", "kick", "leave", "disband"};
            String prefix = args[0].toLowerCase(Locale.ROOT);
            return Stream.of(stringArray).filter(s -> s.startsWith(prefix));
        }
        if (args.length == 2) {
            String sub = args[0].toLowerCase(Locale.ROOT);
            String partial = args[1].toLowerCase(Locale.ROOT);
            if (Intrinsics.areEqual((Object)sub, (Object)"invite")) {
                return this.inviteCandidates().stream().filter(s -> s.toLowerCase(Locale.ROOT).startsWith(partial));
            }
            if (Intrinsics.areEqual((Object)sub, (Object)"kick")) {
                return this.memberNames().stream().filter(s -> s.toLowerCase(Locale.ROOT).startsWith(partial));
            }
        }
        return Stream.empty();
    }

    private final List<String> inviteCandidates() {
        LinkedHashSet<String> set = new LinkedHashSet<String>();
        MinecraftClient mc = MinecraftClient.getInstance();
        ClientPlayerEntity player = mc.player;
        if (player != null && player.networkHandler != null) {
            for (PlayerListEntry info : player.networkHandler.getPlayerList()) {
                GameProfile profile = info.getProfile();
                if (profile != null && profile.name() != null) {
                    set.add(profile.name());
                }
            }
        }
        set.addAll(FriendUtils.getFriendNames());
        if (mc.getSession() != null && mc.getSession().getUsername() != null) {
            set.remove(mc.getSession().getUsername());
        }
        return new ArrayList<String>(set);
    }

    private final List<String> memberNames() {
        ArrayList<String> names = new ArrayList<String>();
        PartySnapshot snap = PartyClient.INSTANCE.snapshot();
        for (PartyMember m : snap.members()) {
            if (m.leader()) continue;
            names.add(m.name());
        }
        return names;
    }

    @Override
    @NotNull
    public List<String> getLongDesc() {
        return List.of(
            I18n.tr("Система Party для друзей (до 10 человек)."),
            I18n.tr("> party create <название>"),
            I18n.tr("> party invite <ник>"),
            "> party info",
            I18n.tr("> party chat <сообщение>"),
            I18n.tr("> party kick <ник>"),
            "> party leave",
            "> party disband"
        );
    }
}

