/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Interface;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.modules.impl.Interface.InterfaceComponentModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\u0006J\r\u0010\b\u001a\u00020\u0004\u00a2\u0006\u0004\b\b\u0010\u0006J\r\u0010\t\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\u0006R\u0019\u0010\u0005\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000b\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\fR\u0019\u0010\r\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000b\u00a2\u0006\u0006\n\u0004\b\r\u0010\fR\u0019\u0010\u000e\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000b\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000b\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\f\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/HPFocus;", "Lrtx/kimiko/api/modules/impl/Interface/InterfaceComponentModule;", "<init>", "()V", "", "scale", "()F", "captureWidthGui", "captureHeightGui", "hpThresholdHp", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "Lkotlin/jvm/JvmField;", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "captureWidth", "captureHeight", "hpThreshold", "rtx.kimiko:kimiko"})
public final class HPFocus
extends InterfaceComponentModule {
    @JvmField
    @NotNull
    public final NumberSetting scale = (NumberSetting)this.register((Setting)new NumberSetting("Масштаб", "Общий размер окна на экране.", 1.3, 0.4, 3.0, 0.05));
    @JvmField
    @NotNull
    public final NumberSetting captureWidth = (NumberSetting)this.register((Setting)new NumberSetting("Ширина захвата", "Ширина захватываемой области хотбара (в пикселях GUI).", 200.0, 120.0, 320.0, 2.0));
    @JvmField
    @NotNull
    public final NumberSetting captureHeight = (NumberSetting)this.register((Setting)new NumberSetting("Высота захвата", "Высота захватываемой области — хотбар, броня, еда и хп (в пикселях GUI).", 52.0, 30.0, 110.0, 1.0));
    @JvmField
    @NotNull
    public final NumberSetting hpThreshold = (NumberSetting)this.register((Setting)new NumberSetting("Порог ХП", "При каком уровне здоровья (ХП) показывать окно.", 10.0, 0.0, 20.0, 0.5));

    public HPFocus() {
        super("HP Focus", "Перемещаемое зазумленное окно хотбара, брони, еды и хп при низком ХП.");
    }

    public final float scale() {
        return this.scale.getFloat();
    }

    public final float captureWidthGui() {
        return this.captureWidth.getFloat();
    }

    public final float captureHeightGui() {
        return this.captureHeight.getFloat();
    }

    public final float hpThresholdHp() {
        return this.hpThreshold.getFloat();
    }
}

