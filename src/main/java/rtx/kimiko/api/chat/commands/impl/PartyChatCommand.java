/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.ArraysKt
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.util.Formatting
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.chat.commands.impl;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.chat.commands.Command;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Utils.Party;
import rtx.kimiko.api.party.PartyClient;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000eH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/chat/commands/impl/PartyChatCommand;", "Lrtx/kimiko/api/chat/commands/Command;", "<init>", "()V", "", "label", "", "args", "", "execute", "(Ljava/lang/String;[Ljava/lang/String;)V", "", "ensureLink", "()Z", "", "getLongDesc", "()Ljava/util/List;", "rtx.kimiko:kimiko"})
public final class PartyChatCommand
extends Command {
    public PartyChatCommand() {
        super("pc", "Party-чат: написать сообщение участникам Party.", new String[]{"pchat"});
    }

    @Override
    public void execute(@NotNull String label, @NotNull String[] args) {
        Intrinsics.checkNotNullParameter((Object)label, (String)"label");
        Intrinsics.checkNotNullParameter((Object)args, (String)"args");
        if (args.length == 0) {
            this.logDirect(I18n.tr("Использование: pc <сообщение>"), Formatting.RED);
            return;
        }
        String text = ((Object)StringsKt.trim((CharSequence)String.join(" ", java.util.Arrays.copyOfRange(args, 0, args.length)))).toString();
        if (((CharSequence)text).length() == 0) {
            this.logDirect(I18n.tr("Введите сообщение."), Formatting.RED);
            return;
        }
        if (!this.ensureLink()) {
            return;
        }
        PartyClient.INSTANCE.sendChat(text);
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

    @Override
    @NotNull
    public List<String> getLongDesc() {
        return List.of(I18n.tr("Party-чат — сообщение видят только участники вашей Party."), I18n.tr("> pc <сообщение>"), I18n.tr("Аналог: party chat <сообщение>"));
    }
}

