/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.InterfaceComponentModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.media.MediaLog;
import rtx.kimiko.utils.media.MediaPlayer;
import rtx.kimiko.utils.media.Radio;
import rtx.kimiko.utils.media.RadioScanner;
import rtx.kimiko.utils.media.RadioStation;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\u0018\u0000 +2\u00020\u0001:\u0001+B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b\b\u0010\u0003J\r\u0010\t\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\u0003J\r\u0010\n\u001a\u00020\u0004\u00a2\u0006\u0004\b\n\u0010\u0006J\u001b\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000bH\u0003b\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u000f\u0010\u0011\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0003J\u000f\u0010\u0012\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0003J\u000f\u0010\u0013\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0003R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00148\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0018\u0010$\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010 R\u0016\u0010&\u001a\u00020%8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010(\u001a\u00020!8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010#R\u0018\u0010)\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010*\u00a8\u0006,"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/MediaPlayerModule;", "Lrtx/kimiko/api/modules/impl/Interface/InterfaceComponentModule;", "<init>", "()V", "", "isHiddenInList", "()Z", "", "onDisable", "releaseMute", "radioEnabled", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "tickRadio", "refreshStationOptions", "silence", "restoreVolume", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "muteAds", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "radio", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "station", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "radioVolume", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "", "mutedProcess", "Ljava/lang/String;", "", "muteStamp", "J", "appliedStation", "", "appliedVolume", "F", "lastOptionsSync", "radioWasEnabled", "Ljava/lang/Boolean;", "Companion", "rtx.kimiko:kimiko"})
public final class MediaPlayerModule
extends InterfaceComponentModule {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final BooleanSetting muteAds = (BooleanSetting)this.register((Setting)new BooleanSetting("Глушить рекламу", "Автоматически выключать звук плеера на рекламных вставках.", true));
    @NotNull
    private final BooleanSetting radio = (BooleanSetting)this.register((Setting)new BooleanSetting("Радио", "Слушать интернет-радио прямо из клиента.", false));
    @NotNull
    private final ModeSetting station;
    @NotNull
    private final SliderSetting radioVolume;
    @Nullable
    private String mutedProcess;
    private long muteStamp;
    @Nullable
    private String appliedStation;
    private float appliedVolume;
    private long lastOptionsSync;
    @Nullable
    private Boolean radioWasEnabled;
    private static final long REMUTE_INTERVAL = 500L;
    @NotNull
    private static final String[] BROWSER_PROCESSES;

    public MediaPlayerModule() {
        super("Media Player", "Радио и глушилка рекламы в музыкальном плеере.");
        String[] stringArray = Radio.stationLabels();
        this.station = (ModeSetting)this.register((Setting)new ModeSetting("Волна", "Станция на шкале приёмника: частота и название.", Radio.stationLabels()[0], Arrays.copyOf(stringArray, stringArray.length)).visibleWhen(() -> MediaPlayerModule.station$lambda$0(this)));
        this.radioVolume = (SliderSetting)this.register((Setting)new SliderSetting("Громкость радио", "Громкость радиопотока.").setValue(Radio.volume() * 100.0f).range(0, 100).increment(1).visible(() -> MediaPlayerModule.radioVolume$lambda$0(this)));
        this.appliedVolume = -1.0f;
    }

    @Override
    public boolean isHiddenInList() {
        return true;
    }

    @Override
    protected void onDisable() {
        this.restoreVolume();
        Radio.stop();
    }

    public final void releaseMute() {
        this.restoreVolume();
    }

    public final boolean radioEnabled() {
        return this.radio.getValue();
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        if (!event.isPre()) {
            return;
        }
        if (!this.isEnabled() || !MediaPlayer.init()) {
            this.restoreVolume();
            if (Radio.isActive()) {
                Radio.stop();
            }
            return;
        }
        MediaPlayer.tick();
        this.tickRadio();
        if (!this.muteAds.getValue()) {
            this.restoreVolume();
            return;
        }
        if (MediaPlayer.isAdvertisement()) {
            this.silence();
        } else {
            this.restoreVolume();
        }
    }

    private final void tickRadio() {
        boolean enabled = this.radio.getValue();
        boolean known = this.radioWasEnabled != null;
        boolean justEnabled = known && Intrinsics.areEqual((Object)this.radioWasEnabled, (Object)false) && enabled;
        boolean justDisabled = known && Intrinsics.areEqual((Object)this.radioWasEnabled, (Object)true) && !enabled;
        this.radioWasEnabled = enabled;
        if (!enabled) {
            if (justDisabled) {
                Radio.pause();
            } else if (Radio.isActive()) {
                Radio.stop();
            }
            this.appliedStation = null;
            return;
        }
        if (this.mc.world == null) {
            if (Radio.isActive()) {
                Radio.stop();
            }
            return;
        }
        this.refreshStationOptions();
        float target = this.radioVolume.getFloat() / 100.0f;
        if (Math.abs(target - this.appliedVolume) > 0.004f) {
            this.appliedVolume = target;
            Radio.setVolume(target);
        }
        String selected = this.station.getSelected();
        if (this.appliedStation == null) {
            this.appliedStation = selected;
            Radio.select(MediaPlayerModule.Companion.stripDial(selected));
            if (justEnabled) {
                Radio.play();
                return;
            }
        } else if (!Intrinsics.areEqual((Object)selected, (Object)this.appliedStation)) {
            this.appliedStation = selected;
            Radio.select(MediaPlayerModule.Companion.stripDial(selected));
            Radio.play();
            return;
        }
        if (!Radio.isActive() && !Radio.paused()) {
            Radio.play();
        }
    }

    private final void refreshStationOptions() {
        long now = System.currentTimeMillis();
        if (now - this.lastOptionsSync < 1500L && !RadioScanner.scanning()) {
            return;
        }
        this.lastOptionsSync = now;
        ArrayList<String> names = new ArrayList<String>();
        for (RadioStation entry : Radio.available()) {
            names.add(MediaPlayerModule.Companion.label(entry));
        }
        RadioStation current = Radio.current();
        if (current != null && !names.contains(MediaPlayerModule.Companion.label(current))) {
            names.add(MediaPlayerModule.Companion.label(current));
        }
        if (!((Collection)names).isEmpty()) {
            this.station.options((List<String>)names);
        }
    }

    private final void silence() {
        long now = System.currentTimeMillis();
        String current = this.mutedProcess;
        if (current != null) {
            if (now - this.muteStamp < 500L) {
                return;
            }
            this.muteStamp = now;
            if (MediaPlayer.setMuted(current, true)) {
                return;
            }
            this.mutedProcess = null;
        }
        for (String process : MediaPlayerModule.Companion.processCandidates(MediaPlayer.getAppId())) {
            if (!MediaPlayer.setMuted(process, true)) continue;
            this.mutedProcess = process;
            this.muteStamp = now;
            MediaLog.note("client", "muted " + process);
            return;
        }
    }

    private final void restoreVolume() {
        String string = this.mutedProcess;
        if (string == null) {
            return;
        }
        String process = string;
        MediaPlayer.setMuted(process, false);
        this.mutedProcess = null;
        MediaLog.note("client", "unmuted " + process);
    }

    private static final Boolean station$lambda$0(MediaPlayerModule this$0) {
        return this$0.radio.getValue();
    }

    private static final Boolean radioVolume$lambda$0(MediaPlayerModule this$0) {
        return this$0.radio.getValue();
    }

    @JvmStatic
    @Nullable
    public static final MediaPlayerModule getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    @Nullable
    public static final String processFor(@Nullable String appId) {
        return Companion.processFor(appId);
    }

    static {
        String[] stringArray = new String[]{"chrome.exe", "msedge.exe", "firefox.exe", "opera.exe", "browser.exe", "yandex.exe"};
        BROWSER_PROCESSES = stringArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0010\u001a\u0004\u0018\u00010\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u000eJ\u001f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\u00112\b\u0010\u000f\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\u00118\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/MediaPlayerModule.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Interface/MediaPlayerModule;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Interface/MediaPlayerModule;", "Lrtx/kimiko/utils/media/RadioStation;", "entry", "", "label", "(Lrtx/kimiko/utils/media/RadioStation;)Ljava/lang/String;", "stripDial", "(Ljava/lang/String;)Ljava/lang/String;", "appId", "processFor", "", "processCandidates", "(Ljava/lang/String;)[Ljava/lang/String;", "", "REMUTE_INTERVAL", "J", "BROWSER_PROCESSES", "[Ljava/lang/String;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final MediaPlayerModule getInstance() {
            return ModuleManager.Companion.get().get(MediaPlayerModule.class);
        }

        private final String label(RadioStation entry) {
            return entry.dial() + " \u00b7 " + entry.name();
        }

        private final String stripDial(String label) {
            String string;
            int separator = String.valueOf(label).indexOf((char)'\u00b7');
            if (separator < 0) {
                string = label;
            } else {
                String string2 = label.substring(separator + 1);
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
                string = ((Object)StringsKt.trim((CharSequence)string2)).toString();
            }
            return string;
        }

        @JvmStatic
        @Nullable
        public final String processFor(@Nullable String appId) {
            String[] candidates = this.processCandidates(appId);
            return candidates.length == 0 ? null : candidates[0];
        }

        private final String[] processCandidates(String appId) {
            if (appId == null || appId.isEmpty()) {
                return new String[0];
            }
            String lower = appId.toLowerCase(Locale.ROOT);
            if (lower.contains("spotify")) {
                return new String[]{"Spotify.exe"};
            }
            if (lower.contains("yandex") || lower.contains("music.desktop")) {
                return new String[]{"Яндекс Музыка.exe", "YandexMusic.exe", "Yandex.Music.exe", "browser.exe"};
            }
            if (lower.contains("aimp")) {
                return new String[]{"AIMP.exe"};
            }
            if (lower.contains("foobar")) {
                return new String[]{"foobar2000.exe"};
            }
            if (lower.contains("deezer")) {
                return new String[]{"Deezer.exe"};
            }
            if (lower.contains("tidal")) {
                return new String[]{"TIDAL.exe"};
            }
            if (lower.contains("apple")) {
                return new String[]{"AppleMusic.exe"};
            }
            if (lower.contains("vlc")) {
                return new String[]{"vlc.exe"};
            }
            if (lower.contains("musicbee")) {
                return new String[]{"MusicBee.exe"};
            }
            if (lower.contains("winamp")) {
                return new String[]{"winamp.exe"};
            }
            if (lower.endsWith(".exe")) {
                int slash = Math.max(lower.lastIndexOf('\\'), lower.lastIndexOf('/'));
                return new String[]{appId.substring(slash + 1)};
            }
            for (String process : BROWSER_PROCESSES) {
                String string2 = process.substring(0, process.length() - 4);
                if (lower.contains(string2)) {
                    return new String[]{process};
                }
            }
            return BROWSER_PROCESSES;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

