/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Pair
 *  kotlin.TuplesKt
 *  kotlin.collections.MapsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.sound.SoundEvent
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.sounds;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.modules.impl.Utils.ClientSounds;
import rtx.kimiko.utils.sounds.SoundManager;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J3\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0007b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\f\u00a2\u0006\u0004\b\u000f\u0010\u0010R0\u0010\u0014\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0011j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0012`\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R \u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00170\u00168\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R0\u0010\u001a\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00170\u0011j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0017`\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0015\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/sounds/Sounds;", "", "<init>", "()V", "", "name", "Lnet/minecraft/SoundEvent;", "event", "", "volume", "pitch", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/lang/String;Lnet/minecraft/SoundEvent;FF)V", "play", "(Ljava/lang/String;)V", "Ljava/util/LinkedHashMap;", "Lrtx/kimiko/utils/sounds/Sounds$Entry;", "Lkotlin/collections/LinkedHashMap;", "REG", "Ljava/util/LinkedHashMap;", "", "", "COOLDOWN_MS", "Ljava/util/Map;", "LAST_PLAY_MS", "Entry", "rtx.kimiko:kimiko"})
public final class Sounds {
    @NotNull
    public static final Sounds INSTANCE = new Sounds();
    @NotNull
    private static final LinkedHashMap<String, Entry> REG = new LinkedHashMap();
    @NotNull
    private static final Map<String, Long> COOLDOWN_MS;
    @NotNull
    private static final LinkedHashMap<String, Long> LAST_PLAY_MS;

    private Sounds() {
    }

    @JvmStatic
    public static final void register(@NotNull String name, @NotNull SoundEvent event, float volume, float pitch) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        ((Map)REG).put(name, new Entry(event, volume, pitch));
    }

    @JvmStatic
    public static final void play(@NotNull String name) {
        ClientSounds sounds;
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        if (!ClientSounds.Companion.isAllowed(name)) {
            return;
        }
        Entry entry = REG.get(name);
        if (entry == null) {
            return;
        }
        Entry e = entry;
        Long cooldown = COOLDOWN_MS.get(name);
        if (cooldown != null) {
            long now = System.currentTimeMillis();
            Long last = LAST_PLAY_MS.get(name);
            if (last != null && now - last < cooldown) {
                return;
            }
            ((Map)LAST_PLAY_MS).put(name, now);
        }
        ClientSounds clientSounds = sounds = ClientSounds.Companion.getInstance();
        float volumeScale = clientSounds != null ? clientSounds.getVolumeFor(name) : 1.0f;
        SoundManager.playSoundDirect(e.getEvent(), e.getVolume() * volumeScale, e.getPitch());
    }

    static {
        Pair[] pairArray = new Pair[]{TuplesKt.to((Object)"slider", (Object)40L), TuplesKt.to((Object)"search_typing", (Object)40L)};
        COOLDOWN_MS = MapsKt.mapOf((Pair[])pairArray);
        LAST_PLAY_MS = new LinkedHashMap();
        Sounds.register("command_error", SoundManager.COMMAND_ERROR, 1.0f, 1.0f);
        Sounds.register("slider", SoundManager.SETTINGS_SLIDER, 1.0f, 1.0f);
        Sounds.register("gui_open", SoundManager.OPEN_GUI, 1.0f, 1.0f);
        Sounds.register("gui_close", SoundManager.CLOSE_GUI, 1.0f, 1.0f);
        Sounds.register("settings_open", SoundManager.SETTINGS_OPEN_DIRECT, 1.0f, 1.0f);
        Sounds.register("settings_close", SoundManager.SETTINGS_CLOSE_DIRECT, 1.0f, 1.0f);
        Sounds.register("module_settings_open", SoundManager.MODULE_SETTINGS_OPEN, 0.1f, 1.0f);
        Sounds.register("module_settings_close", SoundManager.MODULE_SETTINGS_CLOSE, 0.1f, 1.0f);
        Sounds.register("search_typing", SoundManager.SEARCH_TYPING, 0.5f, 1.1f);
        Sounds.register("select_category", SoundManager.SELECT_CATEGORY, 1.0f, 1.0f);
        Sounds.register("gui_scale_up", SoundManager.SWITCH_RIGHT_DIRECT, 1.0f, 1.0f);
        Sounds.register("gui_scale_down", SoundManager.SWITCH_LEFT_DIRECT, 1.0f, 1.0f);
        Sounds.register("logo_pull", SoundManager.BUTTON_CLICK, 0.8f, 1.35f);
        Sounds.register("logo_insert", SoundManager.BUTTON_CLICK, 1.0f, 0.85f);
        Sounds.register("logo_bounce", SoundManager.BUTTON_CLICK, 0.3f, 1.7f);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\f\u001a\u0004\b\u000f\u0010\u000e\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/utils/sounds/Sounds$Entry;", "", "Lnet/minecraft/SoundEvent;", "event", "", "volume", "pitch", "<init>", "(Lnet/minecraft/SoundEvent;FF)V", "Lnet/minecraft/SoundEvent;", "getEvent", "()Lnet/minecraft/SoundEvent;", "F", "getVolume", "()F", "getPitch", "rtx.kimiko:kimiko"})
    private static final class Entry {
        @NotNull
        private final SoundEvent event;
        private final float volume;
        private final float pitch;

        public Entry(@NotNull SoundEvent event, float volume, float pitch) {
            Intrinsics.checkNotNullParameter((Object)event, (String)"event");
            this.event = event;
            this.volume = volume;
            this.pitch = pitch;
        }

        @NotNull
        public final SoundEvent getEvent() {
            return this.event;
        }

        public final float getVolume() {
            return this.volume;
        }

        public final float getPitch() {
            return this.pitch;
        }
    }
}

