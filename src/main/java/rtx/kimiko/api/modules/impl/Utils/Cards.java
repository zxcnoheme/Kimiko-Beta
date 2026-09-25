/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  rtx.kimiko.api.ui.cards.CardsScreen
 */
package rtx.kimiko.api.modules.impl.Utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.cards.CardsChat;
import rtx.kimiko.api.cards.CardsClient;
import rtx.kimiko.api.cards.CardsInvite;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.impl.Interface.NotificationsModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.ui.cards.CardsScreen;
import rtx.kimiko.utils.sounds.Sounds;
import sigil.protect.Level;
import sigil.protect.Protect;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003J\u001f\u0010\n\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\n\u0010\u0003J\u001b\u0010\u000e\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0015R\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/Cards;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onEnable", "onDisable", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "openBind", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "chatInvites", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "autoOpen", "", "lastDown", "Z", "rtx.kimiko:kimiko"})
public final class Cards
extends Module {
    @NotNull
    private final BindSetting openBind = (BindSetting)this.register((Setting)new BindSetting("Открыть стол", "Открыть карточный стол.").setKey(-1));
    @NotNull
    private final BooleanSetting chatInvites = (BooleanSetting)this.register((Setting)new BooleanSetting("Приглашения в чат", "Показывать приглашения кликабельным сообщением в чате.", true));
    @NotNull
    private final BooleanSetting autoOpen = (BooleanSetting)this.register((Setting)new BooleanSetting("Автооткрытие стола", "Открывать стол, когда игра началась.", true));
    private boolean lastDown;

    public Cards() {
        super("Cards", "Подкидной дурак с игроками Kimiko: приглашайте и играйте прямо в клиенте.", Category.UTILS);
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onEnable() {
        CardsClient.INSTANCE.setInviteListener(arg_0 -> Cards.onEnable$lambda$0(this, arg_0));
        CardsClient.INSTANCE.setGameStartListener(() -> Cards.onEnable$lambda$1(this));
        CardsClient.INSTANCE.setRoomClosedListener(() -> Cards.onEnable$lambda$2(this));
        CardsClient.INSTANCE.start();
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        this.lastDown = false;
        CardsClient.INSTANCE.setInviteListener(null);
        CardsClient.INSTANCE.setGameStartListener(null);
        CardsClient.INSTANCE.setRoomClosedListener(null);
        CardsClient.INSTANCE.stop();
    }

    @EventHandler
    public final void onTick(@NotNull TickEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPre()) {
            return;
        }
        if (this.mc.getWindow() == null || this.mc.currentScreen != null || this.mc.player == null) {
            this.lastDown = false;
            return;
        }
        if (!this.openBind.isBound()) {
            this.lastDown = false;
            return;
        }
        boolean down = this.openBind.getValue().isDown(this.mc.getWindow().getHandle());
        if (down && !this.lastDown) {
            CardsScreen.Companion.open();
        }
        this.lastDown = down;
    }

    private static final void onEnable$lambda$0(Cards this$0, CardsInvite invite) {
        Intrinsics.checkNotNullParameter((Object)invite, (String)"invite");
        if (this$0.mc.currentScreen instanceof CardsScreen) {
            return;
        }
        Object[] objectArray = new Object[]{invite.fromName()};
        NotificationsModule.Companion.notify(I18n.tr("%s приглашает сыграть в Дурака", objectArray), 5000L);
        if (this$0.chatInvites.getValue()) {
            CardsChat.printInvite(invite);
        }
        Sounds.play("select_category");
    }

    private static final void onEnable$lambda$1(Cards this$0) {
        if (this$0.mc.currentScreen instanceof CardsScreen) {
            return;
        }
        if (this$0.autoOpen.getValue() && this$0.mc.currentScreen == null) {
            CardsScreen.Companion.open();
        } else {
            NotificationsModule.Companion.notify(I18n.tr("Игра началась — откройте карточный стол"), 5000L);
        }
    }

    private static final void onEnable$lambda$2(Cards this$0) {
        if (this$0.mc.currentScreen instanceof CardsScreen) {
            return;
        }
        NotificationsModule.Companion.notify(I18n.tr("Карточный стол закрыт"), 4000L);
    }
}

