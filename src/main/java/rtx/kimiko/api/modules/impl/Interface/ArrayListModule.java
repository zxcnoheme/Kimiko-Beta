/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Interface;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.impl.Interface.InterfaceComponentModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.MultiSelectSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/ArrayListModule;", "Lrtx/kimiko/api/modules/impl/Interface/InterfaceComponentModule;", "<init>", "()V", "", "categoryIcons", "()Z", "", "waveSpeed", "()D", "Lrtx/kimiko/api/modules/Category;", "category", "categoryShown", "(Lrtx/kimiko/api/modules/Category;)Z", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "waveSpeedSetting", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "categories", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "categoryIconsSetting", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "rtx.kimiko:kimiko"})
public final class ArrayListModule
extends InterfaceComponentModule {
    @NotNull
    private final SliderSetting waveSpeedSetting = (SliderSetting)this.register((Setting)new SliderSetting("Скорость волны", "Скорость перелива: больше — быстрее.").setValue(18.0f).range(1, 100).increment(1));
    @NotNull
    private final MultiSelectSetting categories;
    @NotNull
    private final BooleanSetting categoryIconsSetting;

    public ArrayListModule() {
        super("Array List", "Перемещаемый список включённых модулей.");
        String[] stringArray = new String[]{Category.VISUALS.getDisplayName(), Category.DISPLAY.getDisplayName(), Category.UTILS.getDisplayName()};
        MultiSelectSetting multiSelectSetting = new MultiSelectSetting("Категории", "Модули каких категорий показывать в списке.").value(stringArray);
        stringArray = new String[]{Category.VISUALS.getDisplayName(), Category.UTILS.getDisplayName()};
        this.categories = (MultiSelectSetting)this.register((Setting)multiSelectSetting.selected(stringArray));
        this.categoryIconsSetting = (BooleanSetting)this.register((Setting)new BooleanSetting("Иконки категорий", "Показывать иконку категории рядом с каждым модулем списка.", false));
    }

    public final boolean categoryIcons() {
        return this.categoryIconsSetting.getValue();
    }

    public final double waveSpeed() {
        return (double)this.waveSpeedSetting.getInt() * 1.0E-4;
    }

    public final boolean categoryShown(@Nullable Category category) {
        return category != null && this.categories.is(category.getDisplayName());
    }
}

