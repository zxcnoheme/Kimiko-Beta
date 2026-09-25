/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ButtonSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.notifications.Notifications;
import rtx.kimiko.api.voice.VoiceBindManager;
import rtx.kimiko.utils.voice.VoiceTemplate;
import rtx.kimiko.utils.voice.VoiceTemplateStore;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0006\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u001b\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\f\u0010\u0003J\u000f\u0010\r\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\r\u0010\u0003J\u000f\u0010\u000e\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u0003J\u0017\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0003J\u000f\u0010\u0014\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0003R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0013\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0019R\u0014\u0010\u0014\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\f\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0019R\u0014\u0010\r\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\u0019R\u0014\u0010\u001d\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u0017R\u0014\u0010\u001e\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001cR\u0014\u0010\u001f\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001c\u00a8\u0006!"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/VoiceControl;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onEnable", "onDisable", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "clearAdapted", "clearAll", "sync", "", "key", "labelFor", "(Ljava/lang/String;)Ljava/lang/String;", "recordWake", "clearWake", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "wakeSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "requireWake", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "miscSeparator", "announce", "debug", "Companion", "rtx.kimiko:kimiko"})
public final class VoiceControl
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting wakeSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Ключевое слово"));
    @NotNull
    private final ButtonSetting recordWake = (ButtonSetting)this.register((Setting)new ButtonSetting("Ключевое слово", "Записать фразу активации, например «Кимико».").label("Записать").onClick(() -> VoiceControl.recordWake$lambda$0(this)));
    @NotNull
    private final ButtonSetting clearWake = (ButtonSetting)this.register((Setting)new ButtonSetting("Сбросить", "Удалить записанное ключевое слово.").label("Удалить").onClick(() -> VoiceControl.clearWake$lambda$0(this)).visible(VoiceControl::clearWake$lambda$1));
    @NotNull
    private final BooleanSetting requireWake = (BooleanSetting)this.register((Setting)new BooleanSetting("Требовать ключевое", "Команда сработает только после ключевого слова.", true));
    @NotNull
    private final ButtonSetting clearAdapted = (ButtonSetting)this.register((Setting)new ButtonSetting("Адаптация", "Удалить дубли, которые система дозаписала сама при срабатываниях — лечит ложные срабатывания.").label("Очистить").onClick(() -> VoiceControl.clearAdapted$lambda$0(this)));
    @NotNull
    private final ButtonSetting clearAll = (ButtonSetting)this.register((Setting)new ButtonSetting("Все записи", "Удалить все голосовые записи, включая ключевое слово.").label("Сбросить").onClick(() -> VoiceControl.clearAll$lambda$0(this)));
    @NotNull
    private final SeparatorSetting miscSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Прочее"));
    @NotNull
    private final BooleanSetting announce = (BooleanSetting)this.register((Setting)new BooleanSetting("Уведомления", "Показывать уведомление при срабатывании команды.", true));
    @NotNull
    private final BooleanSetting debug = (BooleanSetting)this.register((Setting)new BooleanSetting("Отладка", "Показывать расстояние до эталона — помогает настроить чувствительность.", false));
    @Nullable
    private static VoiceControl companionInstance;

    public VoiceControl() {
        super("Voice Control", "Голосовые бинды: скажи ключевое слово и название модуля.", Category.UTILS);
        companionInstance = this;
    }

    @Override
    protected void onEnable() {
        this.sync();
        VoiceBindManager.INSTANCE.setActive(true);
    }

    @Override
    protected void onDisable() {
        VoiceBindManager.INSTANCE.setActive(false);
    }

    @EventHandler
    public final void onTick(@NotNull TickEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPre()) {
            return;
        }
        this.sync();
        this.recordWake.label(this.labelFor("__wake__"));
        Object[] objectArray = new Object[]{VoiceTemplateStore.all().size()};
        this.clearAll.label(I18n.tr("Сбросить (%d)", objectArray));
    }

    private final void clearAdapted() {
        String string;
        int cleaned = VoiceTemplateStore.clearAdaptedAll();
        String string2 = I18n.tr("Голос");
        if (cleaned == 0) {
            string = I18n.tr("Адаптивных дублей нет");
        } else {
            Object[] objectArray = new Object[]{cleaned};
            string = I18n.tr("Адаптация очищена у шаблонов: %d", objectArray);
        }
        Notifications.push(string2, string, 2000L);
    }

    private final void clearAll() {
        VoiceBindManager.INSTANCE.cancelRecording();
        int count = VoiceTemplateStore.clearAll();
        for (Module module : ModuleManager.Companion.get().getAll()) {
            if (module.getBindType() != Module.BindType.VOICE) continue;
            module.setBindType(Module.BindType.KEY);
        }
        Object[] objectArray = new Object[]{count};
        Notifications.push(I18n.tr("Голос"), I18n.tr("Удалено записей: %d, бинды переведены на кнопки", objectArray), 2200L);
    }

    private final void sync() {
        VoiceBindManager manager = VoiceBindManager.INSTANCE;
        manager.setRequireWake(this.requireWake.getValue());
        manager.setAnnounce(this.announce.getValue());
        manager.setDebug(this.debug.getValue());
    }

    private final String labelFor(String key) {
        String string;
        int takes;
        VoiceBindManager manager = VoiceBindManager.INSTANCE;
        if (Intrinsics.areEqual((Object)key, (Object)manager.recordingKey())) {
            Object[] objectArray = new Object[]{manager.recordingRemaining()};
            return I18n.tr("Говорите %s", objectArray);
        }
        if (VoiceTemplateStore.has(key)) {
            VoiceTemplate voiceTemplate = VoiceTemplateStore.get(key);
            takes = voiceTemplate != null ? voiceTemplate.takeCount() : 0;
        } else {
            takes = 0;
        }
        if (takes == 0) {
            string = I18n.tr("Записать");
        } else {
            Object[] objectArray = new Object[]{takes};
            string = I18n.tr("Готово %d", objectArray);
        }
        return string;
    }

    private final void recordWake() {
        if (!this.isEnabled()) {
            Notifications.push(I18n.tr("Голос"), I18n.tr("Сначала включи VoiceControl"), 1800L);
            return;
        }
        VoiceBindManager.INSTANCE.beginRecording("__wake__", VoiceControl::recordWake$lambda$1);
        int left = VoiceBindManager.INSTANCE.promptRemaining("__wake__");
        Object[] objectArray = new Object[]{left, VoiceBindManager.Companion.times(left)};
        Notifications.push(I18n.tr("Голос"), I18n.tr("Скажи ключевое слово %d %s", objectArray), 1800L);
    }

    private final void clearWake() {
        if (Intrinsics.areEqual((Object)"__wake__", (Object)VoiceBindManager.INSTANCE.recordingKey())) {
            VoiceBindManager.INSTANCE.cancelRecording();
        }
        VoiceTemplateStore.remove("__wake__");
        Notifications.push(I18n.tr("Голос"), this.requireWake.getValue() ? I18n.tr("Ключевое слово удалено — команды не сработают, пока не запишешь новое") : I18n.tr("Ключевое слово удалено"), 2200L);
    }

    private static final void recordWake$lambda$0(VoiceControl this$0) {
        this$0.recordWake();
    }

    private static final void clearWake$lambda$0(VoiceControl this$0) {
        this$0.clearWake();
    }

    private static final Boolean clearWake$lambda$1() {
        return VoiceTemplateStore.has("__wake__");
    }

    private static final void clearAdapted$lambda$0(VoiceControl this$0) {
        this$0.clearAdapted();
    }

    private static final void clearAll$lambda$0(VoiceControl this$0) {
        this$0.clearAll();
    }

    private static final void recordWake$lambda$1(VoiceBindManager.RecordStatus status, String detail, int remaining) {
        Intrinsics.checkNotNullParameter((Object)((Object)status), (String)"status");
        switch (WhenMappings.$EnumSwitchMapping$0[status.ordinal()]) {
            case 1: {
                String string;
                if (remaining > 0) {
                    Object[] objectArray = new Object[]{remaining, VoiceBindManager.Companion.times(remaining), VoiceBindManager.Companion.takeHint(remaining)};
                    Notifications.push(I18n.tr("Голос"), I18n.tr("Отлично! Осталось ещё %d %s%s", objectArray), 2000L);
                    break;
                }
                String string2 = I18n.tr("Голос");
                String string3 = I18n.tr("Ключевое слово готово");
                if (detail == null) {
                    string = "";
                } else {
                    Object[] objectArray = new Object[]{detail};
                    string = I18n.tr(", совпадение %s", objectArray);
                }
                Notifications.push(string2, string3 + string, 2000L);
                break;
            }
            case 2: {
                Notifications.push(I18n.tr("Голос"), I18n.tr("Слишком коротко, повтори"), 1600L);
                break;
            }
            case 3: {
                Notifications.push(I18n.tr("Голос"), I18n.tr("Это шум, а не речь — повтори"), 1800L);
                break;
            }
            case 4: {
                Object[] objectArray = new Object[]{detail};
                Notifications.push(I18n.tr("Голос"), I18n.tr("Не услышал фразу, уровень %s", objectArray), 2200L);
                break;
            }
            default: {
                String string;
                String string4 = I18n.tr("Голос");
                if (detail == null) {
                    string = I18n.tr("Не получилось записать");
                } else {
                    Object[] objectArray = new Object[]{detail};
                    string = I18n.tr("Не получилось записать: %s", objectArray);
                }
                Notifications.push(string4, string, 2400L);
            }
        }
    }

    @JvmStatic
    @Nullable
    public static final VoiceControl getInstance() {
        return Companion.getInstance();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/VoiceControl.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Utils/VoiceControl;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Utils/VoiceControl;", "companionInstance", "Lrtx/kimiko/api/modules/impl/Utils/VoiceControl;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final VoiceControl getInstance() {
            return companionInstance;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=3, xi=48)
    public static final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] nArray = new int[VoiceBindManager.RecordStatus.values().length];
            try {
                nArray[VoiceBindManager.RecordStatus.OK.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[VoiceBindManager.RecordStatus.TOO_SHORT.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[VoiceBindManager.RecordStatus.TOO_QUIET.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[VoiceBindManager.RecordStatus.TIMEOUT.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
        }
    }
}

