/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.Arm
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Arm;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.notifications.Notifications;
import rtx.kimiko.utils.sounds.SoundManager;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"handswap"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003J\u001b\u0010\r\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0003R\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001a\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001b\u00ca\u0001\u0010\b\u001c\u0012\f\b\u0006\u0012\b\b\fJ\u0004\b\b(\u001d\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/HandSwap;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onEnable", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "swap", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "separator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "swapKey", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "notify", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "", "wasDown", "Z", "Lrtx/kimiko/api/liteapi/Feature;", "handswap", "rtx.kimiko:kimiko"})
public final class HandSwap
extends Module {
    @NotNull
    private final SeparatorSetting separator = new SeparatorSetting("Смена руки");
    @NotNull
    private final BindSetting swapKey = new BindSetting("Клавиша смены", "Одно нажатие — сменить ведущую руку (правая/левая).").setType(BindSetting.Type.TOGGLE);
    @NotNull
    private final BooleanSetting notify = new BooleanSetting("Уведомление", "Показывать уведомление при смене руки.", true);
    private boolean wasDown;

    public HandSwap() {
        super("Hand Swap", "Смена ведущей руки одним нажатием клавиши, без меню.", Category.UTILS);
        Setting[] settingArray = new Setting[]{this.separator, this.swapKey, this.notify};
        this.register(settingArray);
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onEnable() {
        this.wasDown = true;
    }

    @EventHandler
    public final void onTick(@NotNull TickEvent event) {
        boolean down;
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPre()) {
            return;
        }
        if (this.mc.player == null || this.mc.world == null || this.mc.currentScreen != null) {
            this.wasDown = false;
            return;
        }
        boolean bl = down = this.swapKey.isBound() && this.swapKey.getValue().isDown(this.mc.getWindow().getHandle());
        if (down && !this.wasDown) {
            this.swap();
        }
        this.wasDown = down;
    }

    private final void swap() {
        Arm arm2 = ((Arm)this.mc.options.getMainArm().getValue()).getOpposite();
        Intrinsics.checkNotNullExpressionValue((Object)arm2, (String)"getOpposite(...)");
        Arm next = arm2;
        this.mc.options.getMainArm().setValue(next);
        this.mc.options.sendClientSettings();
        this.mc.options.write();
        if (this.notify.getValue()) {
            String arm = next == Arm.LEFT ? I18n.tr("левая") : I18n.tr("правая");
            Object[] objectArray = new Object[]{arm};
            Notifications.push("Hand Swap", I18n.tr("Ведущая рука: %s.", objectArray), 2000L, SoundManager.NOTIFICATION_LOW);
        }
    }
}

