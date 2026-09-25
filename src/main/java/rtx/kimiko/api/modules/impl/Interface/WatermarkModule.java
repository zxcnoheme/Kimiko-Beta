/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Interface;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.impl.Interface.InterfaceComponentModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.utils.media.MediaPlayer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0003b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tR\u0019\u0010\f\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000b\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\u0019\u0010\u000f\u001a\u00020\u000e8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000b\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/WatermarkModule;", "Lrtx/kimiko/api/modules/impl/Interface/InterfaceComponentModule;", "<init>", "()V", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lkotlin/jvm/JvmField;", "displayMode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "showMedia", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Companion", "rtx.kimiko:kimiko"})
public final class WatermarkModule
extends InterfaceComponentModule {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public final ModeSetting displayMode;
    @JvmField
    @NotNull
    public final BooleanSetting showMedia;
    @NotNull
    public static final String DISPLAY_NICKNAME = "Никнейм";
    @NotNull
    public static final String DISPLAY_TIME = "Время";
    @NotNull
    public static final String DISPLAY_BOTH = "Оба";

    public WatermarkModule() {
        super("Watermark", "Перемещаемый вотермарк клиента с никнеймом и временем.");
        String[] stringArray = new String[]{DISPLAY_NICKNAME, DISPLAY_TIME, DISPLAY_BOTH};
        this.displayMode = (ModeSetting)this.register((Setting)new ModeSetting("Что отображать", "Выберите, какие данные показывать в Watermark.", DISPLAY_BOTH, stringArray));
        this.showMedia = (BooleanSetting)this.register((Setting)new BooleanSetting("Играющий трек", "Иногда показывать обложку и название играющей песни.", true));
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        if (!(event.isPre() && this.isEnabled() && this.showMedia.getValue())) {
            return;
        }
        if (!MediaPlayer.init()) {
            return;
        }
        MediaPlayer.tick();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/WatermarkModule.Companion;", "", "<init>", "()V", "", "DISPLAY_NICKNAME", "Ljava/lang/String;", "DISPLAY_TIME", "DISPLAY_BOTH", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

