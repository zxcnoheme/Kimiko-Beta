/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.text.ClickEvent
 *  net.minecraft.text.ClickEvent.CopyToClipboard
 *  net.minecraft.text.Text
 *  net.minecraft.text.Style
 *  net.minecraft.text.MutableText
 *  net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket
 *  net.minecraft.client.network.ClientPlayerEntity
 */
package rtx.kimiko.api.modules.impl.Utils;

import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Text;
import net.minecraft.text.Style;
import net.minecraft.text.MutableText;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;
import net.minecraft.client.network.ClientPlayerEntity;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.network.PacketEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;

@Feature(value={"deathcoords"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0003b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\t\u00ca\u0001\u0010\b\n\u0012\f\b\u000b\u0012\b\b\fJ\u0004\b\b(\f\u00a8\u0006\r"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/DeathCoords;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "Lrtx/kimiko/api/events/impl/network/PacketEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onPacket", "(Lrtx/kimiko/api/events/impl/network/PacketEvent;)V", "Lrtx/kimiko/api/liteapi/Feature;", "value", "deathcoords", "rtx.kimiko:kimiko"})
public final class DeathCoords
extends Module {
    public DeathCoords() {
        super("Death Coords", "Выводит координаты смерти в чат.", Category.UTILS);
    }

    @EventHandler
    private final void onPacket(PacketEvent event) {
        if (!event.isReceive()) {
            return;
        }
        if (!(event.getPacket() instanceof DeathMessageS2CPacket)) {
            return;
        }
        this.mc.execute(() -> DeathCoords.onPacket$lambda$0(this));
    }

    private static final Style onPacket$lambda$0$0(String $coords, Style style) {
        Intrinsics.checkNotNullParameter((Object)style, (String)"style");
        return style.withClickEvent((ClickEvent)new ClickEvent.CopyToClipboard($coords));
    }

    private static final void onPacket$lambda$0(DeathCoords this$0) {
        ClientPlayerEntity player = this$0.mc.player;
        if (player != null && this$0.mc.world != null) {
            Locale locale = Locale.ROOT;
            String string = "%.0f %.0f %.0f";
            Object[] objectArray = new Object[]{player.getX(), player.getY(), player.getZ()};
            String string2 = String.format(locale, string, Arrays.copyOf(objectArray, objectArray.length));
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
            String coords = string2;
            MutableText mutableText2 = Text.literal((String)coords).styled(arg_0 -> DeathCoords.onPacket$lambda$0$0(coords, arg_0));
            Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"withStyle(...)");
            MutableText text = mutableText2;
            player.sendMessage((Text)text, false);
        }
    }
}

