/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.network.packet.Packet
 *  net.minecraft.client.network.ClientPlayNetworkHandler
 *  net.minecraft.network.packet.s2c.play.GameMessageS2CPacket
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Utils;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.network.packet.Packet;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.network.PacketEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.utils.storage.friend.FriendUtils;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"autotpaccept"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003J\u001f\u0010\n\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\n\u0010\u0003J\u001b\u0010\u000e\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0003b\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0011\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0010H\u0003b\u0002\b\r\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0017J\u0017\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010!\u00ca\u0001\u0010\b#\u0012\f\b\u0006\u0012\b\b\fJ\u0004\b\b($\u00a8\u0006%"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/AutoTpAccept;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onEnable", "onDisable", "Lrtx/kimiko/api/events/impl/network/PacketEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onPacket", "(Lrtx/kimiko/api/events/impl/network/PacketEvent;)V", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "", "message", "", "isTeleportMessage", "(Ljava/lang/String;)Z", "containsFriendName", "Lnet/minecraft/GameMessageS2CPacket;", "packet", "isOverlay", "(Lnet/minecraft/GameMessageS2CPacket;)Z", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "friendsOnly", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "canAccept", "Z", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "autotpaccept", "rtx.kimiko:kimiko"})
public final class AutoTpAccept
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final BooleanSetting friendsOnly = (BooleanSetting)this.register((Setting)new BooleanSetting("Только друзья", "Принимать запросы на телепорт только от друзей.", false));
    private boolean canAccept;
    @NotNull
    private static final String[] TELEPORT_MESSAGES;

    public AutoTpAccept() {
        super("Auto Accept", "Автоматически принимает запросы на телепорт.", Category.UTILS);
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onEnable() {
        this.canAccept = false;
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        this.canAccept = false;
    }

    @EventHandler
    private final void onPacket(PacketEvent event) {
        String message;
        if (!event.isReceive()) {
            return;
        }
        Packet<?> packet2 = event.getPacket();
        GameMessageS2CPacket gameMessageS2CPacket2 = packet2 instanceof GameMessageS2CPacket ? (GameMessageS2CPacket)packet2 : null;
        if (gameMessageS2CPacket2 == null) {
            return;
        }
        GameMessageS2CPacket packet = gameMessageS2CPacket2;
        if (this.isOverlay(packet)) {
            return;
        }
        String string = packet.content().getString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getString(...)");
        String string2 = message = string;
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string3 = string2.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toLowerCase(...)");
        String lower = string3;
        if (!this.isTeleportMessage(lower)) {
            return;
        }
        this.canAccept = !this.friendsOnly.getValue() || this.containsFriendName(lower);
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        ClientPlayNetworkHandler clientPlayNetworkHandler2 = player.networkHandler;
        if (clientPlayNetworkHandler2 == null) {
            return;
        }
        ClientPlayNetworkHandler conn = clientPlayNetworkHandler2;
        if (!this.canAccept) {
            return;
        }
        conn.sendChatCommand("tpaccept");
        this.canAccept = false;
    }

    private final boolean isTeleportMessage(String message) {
        for (String pattern : TELEPORT_MESSAGES) {
            CharSequence charSequence = message;
            String string = pattern;
            Locale locale = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
            String string2 = string.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
            if (!String.valueOf(charSequence).contains(string2)) continue;
            return true;
        }
        return false;
    }

    private final boolean containsFriendName(String message) {
        for (String friend : FriendUtils.friends()) {
            if (friend == null) continue;
            CharSequence charSequence = message;
            String string = friend;
            Locale locale = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
            String string2 = string.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
            if (!String.valueOf(charSequence).contains(string2)) continue;
            return true;
        }
        return false;
    }

    private final boolean isOverlay(GameMessageS2CPacket packet) {
        boolean bl;
        try {
            bl = packet.overlay();
        }
        catch (Throwable t) {
            bl = false;
        }
        return bl;
    }

    static {
        String[] stringArray = new String[]{"has requested teleport", "просит телепортироваться", "хочет телепортироваться к вам", "просит к вам телепортироваться"};
        TELEPORT_MESSAGES = stringArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/AutoTpAccept.Companion;", "", "<init>", "()V", "", "", "TELEPORT_MESSAGES", "[Ljava/lang/String;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

