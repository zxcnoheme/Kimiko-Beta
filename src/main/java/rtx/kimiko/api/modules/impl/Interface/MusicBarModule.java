/*
 * MusicBar module for KimikoSRC
 * Shows current system music with cover, title and playback controls.
 */
package rtx.kimiko.api.modules.impl.Interface;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.impl.Interface.InterfaceComponentModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.utils.media.MediaPlayer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0005\u001a\u00020\u0004H\u0003b\u0002\b\u0006\u00a2\u0006\u0004\b\u0005\u0010\u0007R\u000e\u0010\b\u001a\u00020\u00048\u0006\u00a2\u0006\n\u0004\b\b\u0010\u0007\u00a8\u0006\n"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/MusicBarModule;", "Lrtx/kimiko/api/modules/impl/Interface/InterfaceComponentModule;", "<init>", "()V", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "rtx.kimiko:kimiko"})
public final class MusicBarModule
extends InterfaceComponentModule {
    @NotNull
    public final BooleanSetting showTime;
    @NotNull
    public final BooleanSetting showArt;
    @NotNull
    public final BooleanSetting showWave;

    public MusicBarModule() {
        super("MusicBar", "Текущая музыка из системного плеера с обложкой и кнопками управления.");
        this.showTime = (BooleanSetting)this.register((Setting)new BooleanSetting("Время", "Показывать текущее время.", true));
        this.showArt = (BooleanSetting)this.register((Setting)new BooleanSetting("Обложка", "Показывать обложку альбома.", true));
        this.showWave = (BooleanSetting)this.register((Setting)new BooleanSetting("Волны", "Анимированные волны вместо иконки виджета.", false));
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        if (!event.isPre()) {
            return;
        }
        if (!this.isEnabled() || !MediaPlayer.init()) {
            return;
        }
        MediaPlayer.tick();
    }
}