/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;

@Feature(value={"itemphysics"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012\u00ca\u0001\u0010\b\u0014\u0012\f\b\u0015\u0012\b\b\fJ\u0004\b\b(\u0016\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ItemPhysics;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "isNormalMode", "()Z", "", "groundItemScale", "()F", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "mode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "groundSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "groundSize", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "itemphysics", "rtx.kimiko:kimiko"})
public final class ItemPhysics
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ModeSetting mode;
    @NotNull
    private final SeparatorSetting groundSeparator;
    @NotNull
    private final NumberSetting groundSize;
    @JvmField
    @Nullable
    public static ItemPhysics INSTANCE;

    public ItemPhysics() {
        super("Item Physics", "Добавляет физику падения выброшенным предметам.", Category.VISUALS);
        String[] stringArray = new String[]{"Обычная"};
        this.mode = (ModeSetting)this.register((Setting)new ModeSetting("Физика", "Режим физики выброшенных предметов.", "Обычная", stringArray));
        this.groundSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("На земле"));
        this.groundSize = (NumberSetting)this.register((Setting)new NumberSetting("Размер", "Масштаб предметов с физикой в полёте и на земле.", 1.0, 0.5, 2.5, 0.05));
        INSTANCE = this;
    }

    public final boolean isNormalMode() {
        return this.mode.is("Обычная");
    }

    public final float groundItemScale() {
        return this.isEnabled() ? this.groundSize.getFloat() : 1.0f;
    }

    @JvmStatic
    @Nullable
    public static final ItemPhysics getInstance() {
        return Companion.getInstance();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u001d\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\b\u00a2\u0006\u0006\n\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ItemPhysics.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/ItemPhysics;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/ItemPhysics;", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/ItemPhysics;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final ItemPhysics getInstance() {
            ItemPhysics module = ModuleManager.Companion.get().get(ItemPhysics.class);
            ItemPhysics itemPhysics = module;
            if (itemPhysics == null) {
                itemPhysics = INSTANCE;
            }
            return itemPhysics;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

