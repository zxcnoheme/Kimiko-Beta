/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Utils;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.DeathScreenEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SelectSetting;

@Feature(value={"autorespawn"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0003b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tR\u0019\u0010\f\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000b\u00a2\u0006\u0006\n\u0004\b\f\u0010\r\u00ca\u0001\u0010\b\u000e\u0012\f\b\u000f\u0012\b\b\fJ\u0004\b\b(\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/AutoRespawn;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "Lrtx/kimiko/api/events/impl/game/DeathScreenEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onDeathScreen", "(Lrtx/kimiko/api/events/impl/game/DeathScreenEvent;)V", "Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "Lkotlin/jvm/JvmField;", "mode", "Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "Lrtx/kimiko/api/liteapi/Feature;", "value", "autorespawn", "rtx.kimiko:kimiko"})
public final class AutoRespawn
extends Module {
    @JvmField
    @NotNull
    public final SelectSetting mode;

    public AutoRespawn() {
        super("Auto Respawn", "Автоматически возрождает после смерти.", Category.UTILS);
        String[] stringArray = new String[]{"Стандарт"};
        this.mode = new SelectSetting("Режим", "Режим возрождения.").value(stringArray);
        this.register((Setting)this.mode);
    }

    @EventHandler
    private final void onDeathScreen(DeathScreenEvent event) {
        if (!this.mode.isSelected("Стандарт")) {
            return;
        }
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        player.requestRespawn();
        this.mc.setScreen(null);
    }
}

