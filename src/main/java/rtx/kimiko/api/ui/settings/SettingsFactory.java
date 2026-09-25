/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  rtx.kimiko.api.ui.settings.impl.ColorSetting
 */
package rtx.kimiko.api.ui.settings;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ButtonSetting;
import rtx.kimiko.api.modules.settings.impl.SwordPickerSetting;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.api.ui.settings.impl.BindSetting;
import rtx.kimiko.api.ui.settings.impl.BoolSetting;
import rtx.kimiko.api.ui.settings.impl.ButtonRowSetting;
import rtx.kimiko.api.ui.settings.impl.ColorSetting;
import rtx.kimiko.api.ui.settings.impl.MultiSelectSetting;
import rtx.kimiko.api.ui.settings.impl.RangeSliderSetting;
import rtx.kimiko.api.ui.settings.impl.SelectSetting;
import rtx.kimiko.api.ui.settings.impl.SeparatorSetting;
import rtx.kimiko.api.ui.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.settings.impl.SwordPickerWidget;
import rtx.kimiko.api.ui.settings.impl.TextSetting;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ!\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/api/ui/settings/SettingsFactory;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/settings/Setting;", "backend", "Lrtx/kimiko/api/ui/settings/Setting;", "Lkotlin/jvm/JvmStatic;", "create", "(Lrtx/kimiko/api/modules/settings/Setting;)Lrtx/kimiko/api/ui/settings/Setting;", "Lrtx/kimiko/api/modules/Module;", "module", "", "build", "(Lrtx/kimiko/api/modules/Module;)Ljava/util/List;", "rtx.kimiko:kimiko"})
public final class SettingsFactory {
    @NotNull
    public static final SettingsFactory INSTANCE = new SettingsFactory();

    private SettingsFactory() {
    }

    @JvmStatic
    @Nullable
    public static final Setting create(@NotNull rtx.kimiko.api.modules.settings.Setting backend) {
        Intrinsics.checkNotNullParameter((Object)backend, (String)"backend");
        rtx.kimiko.api.modules.settings.Setting setting = backend;
        return setting instanceof BooleanSetting ? (Setting)new BoolSetting((BooleanSetting)backend) : (setting instanceof rtx.kimiko.api.modules.settings.impl.SliderSetting ? (Setting)new SliderSetting((rtx.kimiko.api.modules.settings.impl.SliderSetting)backend) : (setting instanceof rtx.kimiko.api.modules.settings.impl.RangeSliderSetting ? (Setting)new RangeSliderSetting((rtx.kimiko.api.modules.settings.impl.RangeSliderSetting)backend) : (setting instanceof rtx.kimiko.api.modules.settings.impl.ColorSetting ? (Setting)new ColorSetting((rtx.kimiko.api.modules.settings.impl.ColorSetting)backend) : (setting instanceof rtx.kimiko.api.modules.settings.impl.SelectSetting ? (Setting)new SelectSetting((rtx.kimiko.api.modules.settings.impl.SelectSetting)backend) : (setting instanceof rtx.kimiko.api.modules.settings.impl.MultiSelectSetting ? (Setting)new MultiSelectSetting((rtx.kimiko.api.modules.settings.impl.MultiSelectSetting)backend) : (setting instanceof rtx.kimiko.api.modules.settings.impl.BindSetting ? (Setting)new BindSetting((rtx.kimiko.api.modules.settings.impl.BindSetting)backend) : (setting instanceof rtx.kimiko.api.modules.settings.impl.SeparatorSetting ? (Setting)new SeparatorSetting((rtx.kimiko.api.modules.settings.impl.SeparatorSetting)backend) : (setting instanceof rtx.kimiko.api.modules.settings.impl.TextSetting ? (Setting)new TextSetting((rtx.kimiko.api.modules.settings.impl.TextSetting)backend) : (setting instanceof SwordPickerSetting ? (Setting)new SwordPickerWidget() : (setting instanceof ButtonSetting ? (Setting)new ButtonRowSetting((ButtonSetting)backend) : null))))))))));
    }

    @JvmStatic
    @NotNull
    public static final List<Setting> build(@NotNull Module module) {
        Intrinsics.checkNotNullParameter((Object)module, (String)"module");
        TextSetting.Companion.unfocusAll();
        ArrayList<Setting> widgets = new ArrayList<Setting>();
        for (rtx.kimiko.api.modules.settings.Setting backend : module.getSettings().all()) {
            Setting widget = SettingsFactory.create(backend);
            if (widget == null) continue;
            widgets.add(widget);
        }
        return widgets;
    }
}

