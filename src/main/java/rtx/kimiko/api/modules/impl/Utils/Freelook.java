/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.option.Perspective
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import mods.freelook.FreeLookState;
import net.minecraft.client.option.Perspective;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;

@Feature(value={"mods/freelook"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u001b\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000b\u0010\u0003J\u000f\u0010\f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\f\u0010\u0003R\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0014\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018\u00ca\u0001\u0010\b\u0019\u0012\f\b\u001a\u0012\b\b\fJ\u0004\b\b(\u001b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/Freelook;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onDisable", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "start", "stop", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "separator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "lookKey", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "", "active", "Z", "Lnet/minecraft/Perspective;", "previousCamera", "Lnet/minecraft/Perspective;", "Lrtx/kimiko/api/liteapi/Feature;", "value", "mods/freelook", "rtx.kimiko:kimiko"})
public final class Freelook
extends Module {
    @NotNull
    private final SeparatorSetting separator = new SeparatorSetting("Свободный обзор");
    @NotNull
    private final BindSetting lookKey = new BindSetting("Клавиша обзора", "Зажмите, чтобы свободно осматриваться от третьего лица").setType(BindSetting.Type.HOLD);
    private boolean active;
    @Nullable
    private Perspective previousCamera;

    public Freelook() {
        super("Freelook", "Свободный обзор камерой от третьего лица при зажатии клавиши", Category.UTILS);
        Setting[] settingArray = new Setting[]{this.separator, this.lookKey};
        this.register(settingArray);
    }

    @Override
    protected void onDisable() {
        this.stop();
    }

    @EventHandler
    public final void onTick(@NotNull TickEvent event) {
        boolean held;
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPre()) {
            return;
        }
        if (this.mc.player == null || this.mc.world == null || this.mc.currentScreen != null) {
            this.stop();
            return;
        }
        FreeLookState.maxHeadYaw = 360.0f;
        boolean bl = held = this.lookKey.isBound() && this.lookKey.getValue().isDown(this.mc.getWindow().getHandle());
        if (held && !this.active) {
            this.start();
        } else if (!held && this.active) {
            this.stop();
        }
    }

    private final void start() {
        this.previousCamera = this.mc.options.getPerspective();
        this.mc.options.setPerspective(Perspective.THIRD_PERSON_BACK);
        FreeLookState.active = true;
        this.active = true;
    }

    private final void stop() {
        if (!this.active) {
            return;
        }
        this.active = false;
        FreeLookState.active = false;
        Perspective prev = this.previousCamera;
        if (prev != null) {
            this.mc.options.setPerspective(prev);
            this.previousCamera = null;
        }
    }
}

