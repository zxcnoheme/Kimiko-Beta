/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.util.Formatting
 *  org.jetbrains.annotations.NotNull
 *  rtx.kimiko.api.ui.cards.CardsScreen
 */
package rtx.kimiko.api.chat.commands.impl;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.cards.CardsClient;
import rtx.kimiko.api.cards.CardsUser;
import rtx.kimiko.api.chat.commands.Command;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.ui.cards.CardsScreen;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000b\u001a\u00020\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ%\u0010\u000f\u001a\u00020\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J+\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00112\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0014H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/api/chat/commands/impl/CardsCommand;", "Lrtx/kimiko/api/chat/commands/Command;", "<init>", "()V", "", "label", "", "args", "", "execute", "(Ljava/lang/String;[Ljava/lang/String;)V", "handleInvite", "([Ljava/lang/String;)V", "", "accept", "handleRespond", "([Ljava/lang/String;Z)V", "Ljava/util/stream/Stream;", "tabComplete", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/util/stream/Stream;", "", "getLongDesc", "()Ljava/util/List;", "rtx.kimiko:kimiko"})
public final class CardsCommand
extends Command {
    public CardsCommand() {
        super("cards", "Карточная игра «Дурак»: open / invite / accept / decline.", new String[]{"durak"});
    }

    @Override
    public void execute(@NotNull String label, @NotNull String[] args) {
        Intrinsics.checkNotNullParameter((Object)label, (String)"label");
        Intrinsics.checkNotNullParameter((Object)args, (String)"args");
        if (args.length == 0) {
            CardsScreen.Companion.open();
            return;
        }
        String string = args[0].toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toLowerCase(...)");
        switch (string) {
            case "open": {
                CardsScreen.Companion.open();
                break;
            }
            case "invite": {
                this.handleInvite(args);
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
            case "leave": {
                CardsClient.INSTANCE.leaveRoom();
                break;
            }
            default: {
                this.usage();
            }
        }
    }

    private final void handleInvite(String[] args) {
        if (args.length < 2) {
            this.logDirect(I18n.tr("Использование: cards invite <ник>"), Formatting.RED);
            return;
        }
        String target = ((Object)StringsKt.trim((CharSequence)args[1])).toString();
        for (CardsUser u : CardsClient.INSTANCE.online()) {
            if (!StringsKt.equals((String)u.name(), (String)target, (boolean)true)) continue;
            CardsClient.INSTANCE.invite(u.id());
            return;
        }
        this.logDirect(I18n.tr("Игрок не в сети."), Formatting.RED);
    }

    private final void handleRespond(String[] args, boolean accept) {
        if (args.length < 2) {
            this.logDirect(I18n.tr("Нет идентификатора приглашения."), Formatting.RED);
            return;
        }
        CardsClient.INSTANCE.respondInvite(((Object)StringsKt.trim((CharSequence)args[1])).toString(), accept);
        if (accept) {
            CardsScreen.Companion.open();
        }
    }

    @Override
    @NotNull
    public Stream<String> tabComplete(@NotNull String label, @NotNull String[] args) {
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(args, "args");
        if (args.length == 1) {
            String prefix = args[0].toLowerCase(Locale.ROOT);
            return Stream.of("open", "invite", "leave")
                    .filter(it -> it.toLowerCase(Locale.ROOT).startsWith(prefix));
        }
        if (args.length == 2 && StringsKt.equals(args[0], "invite", true)) {
            String partial = args[1].toLowerCase(Locale.ROOT);
            String self = CardsClient.INSTANCE.selfId();
            return CardsClient.INSTANCE.online().stream()
                    .filter(it -> !Intrinsics.areEqual(it.id(), self) && !it.inGame())
                    .map(CardsUser::name)
                    .filter(it -> it != null && it.toLowerCase(Locale.ROOT).startsWith(partial));
        }
        return Stream.empty();
    }

    @Override
    @NotNull
    public List<String> getLongDesc() {
        return List.of(
            I18n.tr("Карточная игра «Подкидной дурак» с другими игроками Kimiko."),
            I18n.tr("> cards open — открыть стол"),
            I18n.tr("> cards invite <ник> — пригласить игрока"),
            I18n.tr("> cards leave — покинуть стол")
        );
    }
}

