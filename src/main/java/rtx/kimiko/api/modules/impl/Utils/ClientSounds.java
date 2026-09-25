/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.Util
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.module.ModuleToggleEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ButtonSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.modules.settings.impl.SelectSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.utils.sounds.CustomSounds;
import rtx.kimiko.utils.sounds.SoundManager;

@Feature(value={"clientsounds"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 L2\u00020\u0001:\u0001LB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\u0006J\u000f\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\t\u0010\u0003J\u0019\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\n\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0010H\u0003b\u0002\b\u0012\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u000fJ\r\u0010\u0017\u001a\u00020\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0016\u00a2\u0006\u0004\b\u0019\u0010\u0018J\r\u0010\u001a\u001a\u00020\u0016\u00a2\u0006\u0004\b\u001a\u0010\u0018J\u0017\u0010\u001d\u001a\u00020\u00162\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010\u001f\u001a\u00020\u0016\u00a2\u0006\u0004\b\u001f\u0010\u0018R\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b)\u0010(R\u0014\u0010+\u001a\u00020*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010\"R\u0014\u0010.\u001a\u00020*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b.\u0010,R\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0014\u00103\u001a\u0002028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u00104R\u0014\u00105\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00101R\u0014\u00106\u001a\u0002028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b6\u00104R\u0014\u00107\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00101R\u0014\u00108\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u0010\"R\u0014\u00109\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u0010(R\u0014\u0010:\u001a\u00020*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010,R\u0014\u0010;\u001a\u00020*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u0010,R\u0014\u0010<\u001a\u00020*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010,R\u0014\u0010=\u001a\u00020*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010,R\u0014\u0010>\u001a\u00020*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010,R\u0014\u0010?\u001a\u00020*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010,R\u0014\u0010@\u001a\u00020*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u0010,R\u0014\u0010A\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bA\u0010\"R\u0014\u0010B\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010(R\u0014\u0010C\u001a\u00020*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bC\u0010,R\u0014\u0010D\u001a\u00020\u000b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u001c\u0010G\u001a\b\u0012\u0004\u0012\u00020\u001b0F8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bG\u0010HR\u0016\u0010J\u001a\u00020I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bJ\u0010K\u00ca\u0001\u0010\bM\u0012\f\bN\u0012\b\b\fJ\u0004\b\b(O\u00a8\u0006P"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/ClientSounds;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "defaultEnabled", "()Z", "customVisible", "", "rescanCustom", "enabled", "Ljava/nio/file/Path;", "customPath", "(Z)Ljava/nio/file/Path;", "previewCustom", "(Z)V", "Lrtx/kimiko/api/events/impl/module/ModuleToggleEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onModuleToggle", "(Lrtx/kimiko/api/events/impl/module/ModuleToggleEvent;)V", "playToggleSound", "", "getVolume", "()F", "getInterfaceVolume", "getChatVolume", "", "name", "getVolumeFor", "(Ljava/lang/String;)F", "getPitch", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "soundSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "soundType", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "volume", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "pitch", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "moduleToggleSound", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "customSeparator", "customToggle", "Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "openFolder", "Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "enableFile", "Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "previewEnable", "disableFile", "previewDisable", "interfaceSeparator", "interfaceVolume", "guiSound", "categorySound", "moduleSettingsSound", "dropdownSound", "sliderSound", "searchTypingSound", "guiScaleSound", "chatSeparator", "chatVolume", "commandErrorSound", "customFolder", "Ljava/nio/file/Path;", "", "customFiles", "Ljava/util/List;", "", "lastScanMs", "J", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "clientsounds", "rtx.kimiko:kimiko"})
public final class ClientSounds
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting soundSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Звук"));
    @NotNull
    private final ModeSetting soundType;
    @NotNull
    private final NumberSetting volume;
    @NotNull
    private final NumberSetting pitch;
    @NotNull
    private final BooleanSetting moduleToggleSound;
    @NotNull
    private final SeparatorSetting customSeparator;
    @NotNull
    private final BooleanSetting customToggle;
    @NotNull
    private final ButtonSetting openFolder;
    @NotNull
    private final SelectSetting enableFile;
    @NotNull
    private final ButtonSetting previewEnable;
    @NotNull
    private final SelectSetting disableFile;
    @NotNull
    private final ButtonSetting previewDisable;
    @NotNull
    private final SeparatorSetting interfaceSeparator;
    @NotNull
    private final NumberSetting interfaceVolume;
    @NotNull
    private final BooleanSetting guiSound;
    @NotNull
    private final BooleanSetting categorySound;
    @NotNull
    private final BooleanSetting moduleSettingsSound;
    @NotNull
    private final BooleanSetting dropdownSound;
    @NotNull
    private final BooleanSetting sliderSound;
    @NotNull
    private final BooleanSetting searchTypingSound;
    @NotNull
    private final BooleanSetting guiScaleSound;
    @NotNull
    private final SeparatorSetting chatSeparator;
    @NotNull
    private final NumberSetting chatVolume;
    @NotNull
    private final BooleanSetting commandErrorSound;
    @NotNull
    private final Path customFolder;
    @NotNull
    private List<String> customFiles;
    private long lastScanMs;
    @NotNull
    private static final String DEFAULT_SOUND = "Стандартный";
    private static final long RESCAN_INTERVAL_MS = 1200L;
    @Nullable
    private static ClientSounds companionInstance;

    public ClientSounds() {
        super("Client Sounds", "Звуки клиента для действий модулей и меню.", Category.UTILS);
        String[] stringArray = new String[]{"Основной"};
        this.soundType = (ModeSetting)this.register((Setting)new ModeSetting("Тип звука", "Тип звука клиента.", "Основной", stringArray));
        this.volume = (NumberSetting)this.register((Setting)new NumberSetting("Громкость", "Громкость звуков вкл/выкл модулей.", 1.0, 0.0, 1.0, 0.05));
        this.pitch = (NumberSetting)this.register((Setting)new NumberSetting("Высота тона", "Высота тона звука вкл/выкл модулей.", 1.0, 0.5, 2.0, 0.05));
        this.moduleToggleSound = (BooleanSetting)this.register((Setting)new BooleanSetting("Вкл/выкл модулей", "Звук включения и выключения модулей.", true));
        this.customSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Свои звуки"));
        this.customToggle = (BooleanSetting)this.register((Setting)new BooleanSetting("Свои звуки вкл/выкл", "Заменить звук включения и выключения модулей своими файлами.", false));
        this.openFolder = (ButtonSetting)this.register((Setting)new ButtonSetting("Папка со звуками", "Открыть папку kimiko/clientsounds в проводнике.").label("Открыть").visible(() -> ClientSounds.openFolder$lambda$0(this)));
        this.enableFile = (SelectSetting)this.register((Setting)new SelectSetting("Звук включения", "Файл, который играет при включении модуля.").visible(() -> ClientSounds.enableFile$lambda$0(this)));
        this.previewEnable = (ButtonSetting)this.register((Setting)new ButtonSetting("Прослушать включение", "Проиграть звук включения.").label("Играть").visible(() -> ClientSounds.previewEnable$lambda$0(this)));
        this.disableFile = (SelectSetting)this.register((Setting)new SelectSetting("Звук выключения", "Файл, который играет при выключении модуля.").visible(() -> ClientSounds.disableFile$lambda$0(this)));
        this.previewDisable = (ButtonSetting)this.register((Setting)new ButtonSetting("Прослушать выключение", "Проиграть звук выключения.").label("Играть").visible(() -> ClientSounds.previewDisable$lambda$0(this)));
        this.interfaceSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Звуки интерфейса"));
        this.interfaceVolume = (NumberSetting)this.register((Setting)new NumberSetting("Громкость интерфейса", "Громкость звуков меню и UI.", 1.0, 0.0, 1.0, 0.05));
        this.guiSound = (BooleanSetting)this.register((Setting)new BooleanSetting("Открытие/закрытие меню", "Звук открытия и закрытия меню.", true));
        this.categorySound = (BooleanSetting)this.register((Setting)new BooleanSetting("Смена категории", "Звук переключения категории.", true));
        this.moduleSettingsSound = (BooleanSetting)this.register((Setting)new BooleanSetting("Настройки модуля", "Звук открытия и закрытия настроек модуля.", true));
        this.dropdownSound = (BooleanSetting)this.register((Setting)new BooleanSetting("Выпадающие списки", "Звук открытия и закрытия выпадающих списков.", true));
        this.sliderSound = (BooleanSetting)this.register((Setting)new BooleanSetting("Ползунок", "Звук перемещения ползунка.", true));
        this.searchTypingSound = (BooleanSetting)this.register((Setting)new BooleanSetting("Ввод в поиске", "Звук ввода текста в поиске.", true));
        this.guiScaleSound = (BooleanSetting)this.register((Setting)new BooleanSetting("Масштаб меню", "Звук увеличения и уменьшения меню.", true));
        this.chatSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Звуки чата"));
        this.chatVolume = (NumberSetting)this.register((Setting)new NumberSetting("Громкость чата", "Громкость звуков чата и команд.", 1.0, 0.0, 1.0, 0.05));
        this.commandErrorSound = (BooleanSetting)this.register((Setting)new BooleanSetting("Ошибки команд", "Звук ошибки или неизвестной команды.", true));
        this.customFolder = CustomSounds.folder("clientsounds");
        this.customFiles = CollectionsKt.emptyList();
        companionInstance = this;
        stringArray = new String[]{DEFAULT_SOUND};
        this.enableFile.value(stringArray);
        stringArray = new String[]{DEFAULT_SOUND};
        this.disableFile.value(stringArray);
        this.openFolder.onClick(() -> ClientSounds._init_$lambda$0(this));
        this.previewEnable.onClick(() -> ClientSounds._init_$lambda$1(this));
        this.previewDisable.onClick(() -> ClientSounds._init_$lambda$2(this));
        CustomSounds.ensureFolder(this.customFolder);
        this.rescanCustom();
    }

    @Override
    public boolean defaultEnabled() {
        return true;
    }

    private final boolean customVisible() {
        if (!this.customToggle.getValue()) {
            return false;
        }
        long now = System.currentTimeMillis();
        if (now - this.lastScanMs >= 1200L) {
            this.rescanCustom();
        }
        return true;
    }

    private final void rescanCustom() {
        this.lastScanMs = System.currentTimeMillis();
        List<String> found = CustomSounds.list(this.customFolder);
        if (Intrinsics.areEqual(found, this.customFiles)) {
            return;
        }
        this.customFiles = found;
        CustomSounds.invalidate();
        ArrayList<String> options = new ArrayList<String>();
        options.add(DEFAULT_SOUND);
        options.addAll((Collection)this.customFiles);
        this.enableFile.options((List<String>)options);
        this.disableFile.options((List<String>)options);
    }

    private final Path customPath(boolean enabled) {
        if (!this.customToggle.getValue()) {
            return null;
        }
        String selected = (enabled ? this.enableFile : this.disableFile).getSelected();
        if (selected == null || Intrinsics.areEqual((Object)selected, (Object)DEFAULT_SOUND) || !this.customFiles.contains(selected)) {
            return null;
        }
        return this.customFolder.resolve(selected);
    }

    private final void previewCustom(boolean enabled) {
        this.rescanCustom();
        Path path = this.customPath(enabled);
        if (path != null) {
            CustomSounds.play(path, this.volume.getFloat(), this.pitch.getFloat());
        } else {
            SoundManager.playSoundDirect(enabled ? SoundManager.TOGGLE1_ON : SoundManager.TOGGLE1_OFF, this.volume.getFloat(), this.pitch.getFloat());
        }
    }

    @EventHandler
    private final void onModuleToggle(ModuleToggleEvent event) {
        if (ConfigManager.Companion.isLoading()) {
            return;
        }
        if (this.mc.player == null || this.mc.world == null || Intrinsics.areEqual((Object)event.getModule(), (Object)this)) {
            return;
        }
        this.playToggleSound(event.isEnabled());
    }

    private final void playToggleSound(boolean enabled) {
        if (!this.isEnabled() || !this.moduleToggleSound.getValue()) {
            return;
        }
        float vol = this.volume.getFloat();
        float tone = this.pitch.getFloat();
        Path custom = this.customPath(enabled);
        if (custom != null) {
            CustomSounds.play(custom, vol, tone);
            return;
        }
        SoundManager.playSoundDirect(enabled ? SoundManager.TOGGLE1_ON : SoundManager.TOGGLE1_OFF, vol, tone);
    }

    public final float getVolume() {
        return this.volume.getFloat();
    }

    public final float getInterfaceVolume() {
        return this.interfaceVolume.getFloat();
    }

    public final float getChatVolume() {
        return this.chatVolume.getFloat();
    }

    /*
     * Unable to fully structure code
     */
    public final float getVolumeFor(@Nullable String name) {
        if (name == null) {
            return this.getVolume();
        }
        return switch (name) {
            case "slider", "search_typing", "gui_open", "module_settings_close", 
                 "gui_close", "module_settings_open", "settings_open", "settings_close", 
                 "select_category", "gui_scale_up", "gui_scale_down" -> this.getInterfaceVolume();
            case "command_error" -> this.getChatVolume();
            default -> this.getVolume();
        };
    }

    public final float getPitch() {
        return this.pitch.getFloat();
    }

    private static final Boolean openFolder$lambda$0(ClientSounds this$0) {
        return this$0.customVisible();
    }

    private static final Boolean enableFile$lambda$0(ClientSounds this$0) {
        return this$0.customVisible();
    }

    private static final Boolean previewEnable$lambda$0(ClientSounds this$0) {
        return this$0.customVisible();
    }

    private static final Boolean disableFile$lambda$0(ClientSounds this$0) {
        return this$0.customVisible();
    }

    private static final Boolean previewDisable$lambda$0(ClientSounds this$0) {
        return this$0.customVisible();
    }

    private static final void _init_$lambda$0(ClientSounds this$0) {
        CustomSounds.ensureFolder(this$0.customFolder);
        Util.getOperatingSystem().open(this$0.customFolder);
    }

    private static final void _init_$lambda$1(ClientSounds this$0) {
        this$0.previewCustom(true);
    }

    private static final void _init_$lambda$2(ClientSounds this$0) {
        this$0.previewCustom(false);
    }

    @JvmStatic
    @Nullable
    public static final ClientSounds getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final boolean isAllowed(@Nullable String name) {
        return Companion.isAllowed(name);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0014"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/ClientSounds.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Utils/ClientSounds;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Utils/ClientSounds;", "", "name", "", "isAllowed", "(Ljava/lang/String;)Z", "DEFAULT_SOUND", "Ljava/lang/String;", "", "RESCAN_INTERVAL_MS", "J", "companionInstance", "Lrtx/kimiko/api/modules/impl/Utils/ClientSounds;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final ClientSounds getInstance() {
            return companionInstance;
        }

        @JvmStatic
        public final boolean isAllowed(@Nullable String name) {
            ClientSounds clientSounds = companionInstance;
            if (clientSounds == null) {
                return true;
            }
            ClientSounds s = clientSounds;
            if (name == null) {
                return true;
            }
            if (!s.isEnabled()) {
                return false;
            }
            return switch (name) {
                case "gui_open", "gui_close" -> s.guiSound.getValue();
                case "select_category" -> s.categorySound.getValue();
                case "module_settings_close", "module_settings_open" -> s.moduleSettingsSound.getValue();
                case "settings_open", "settings_close" -> s.dropdownSound.getValue();
                case "slider" -> s.sliderSound.getValue();
                case "search_typing" -> s.searchTypingSound.getValue();
                case "gui_scale_up", "gui_scale_down" -> s.guiScaleSound.getValue();
                case "command_error" -> s.commandErrorSound.getValue();
                default -> true;
            };
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

