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
import rtx.kimiko.api.modules.impl.Interface.InterfaceComponentModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\u0006J\r\u0010\b\u001a\u00020\u0004\u00a2\u0006\u0004\b\b\u0010\u0006J\r\u0010\t\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\u0006J\r\u0010\n\u001a\u00020\u0004\u00a2\u0006\u0004\b\n\u0010\u0006J\r\u0010\u000b\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000b\u0010\u0006J\r\u0010\f\u001a\u00020\u0004\u00a2\u0006\u0004\b\f\u0010\u0006R\u0019\u0010\u000f\u001a\u00020\r8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0011\u001a\u00020\r8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0010R\u0019\u0010\u0005\u001a\u00020\u00128\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0013R\u0019\u0010\u0007\u001a\u00020\u00128\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0013R\u0019\u0010\f\u001a\u00020\u00128\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000e\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0013\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/TargetHudModule;", "Lrtx/kimiko/api/modules/impl/Interface/InterfaceComponentModule;", "<init>", "()V", "", "showArmor", "()Z", "showUseItem", "isNewMode", "isCircleMode", "barWhite", "barClient", "followTarget", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lkotlin/jvm/JvmField;", "hudMode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "barColorMode", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Companion", "rtx.kimiko:kimiko"})
public final class TargetHudModule
extends InterfaceComponentModule {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public final ModeSetting hudMode;
    @JvmField
    @NotNull
    public final ModeSetting barColorMode;
    @JvmField
    @NotNull
    public final BooleanSetting showArmor;
    @JvmField
    @NotNull
    public final BooleanSetting showUseItem;
    @JvmField
    @NotNull
    public final BooleanSetting followTarget;
    @NotNull
    public static final String MODE_CLASSIC = "Классический";
    @NotNull
    public static final String MODE_NEW = "Новый";
    @NotNull
    public static final String MODE_CIRCLE = "Кругляшок";
    @NotNull
    public static final String BAR_FROM_HP = "От хп";
    @NotNull
    public static final String BAR_WHITE = "Белая";
    @NotNull
    public static final String BAR_CLIENT = "Клиентский";

    public TargetHudModule() {
        super("Target HUD", "Перемещаемый HUD с информацией о цели.");
        String[] stringArray = new String[]{MODE_CLASSIC, MODE_NEW, MODE_CIRCLE};
        this.hudMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим", "Внешний вид Target HUD.", MODE_CLASSIC, stringArray));
        stringArray = new String[]{BAR_FROM_HP, BAR_WHITE, BAR_CLIENT};
        this.barColorMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим цвета полосы хп", "Цвет заливки полосы здоровья.", BAR_FROM_HP, stringArray));
        this.showArmor = (BooleanSetting)this.register((Setting)new BooleanSetting("Броня", "Показывать броню и предметы в руках цели.", true));
        this.showUseItem = (BooleanSetting)this.register((Setting)new BooleanSetting("Используемый предмет", "Показывать предмет, который цель ест или пьёт, с прогрессом использования.", true));
        this.followTarget = (BooleanSetting)this.register((Setting)new BooleanSetting("Следовать", "HUD плавно следует за целью на экране и возвращается на своё место, когда цель вне экрана.", false));
    }

    public final boolean showArmor() {
        return this.showArmor.getValue();
    }

    public final boolean showUseItem() {
        return this.showUseItem.getValue();
    }

    public final boolean isNewMode() {
        return this.hudMode.is(MODE_NEW);
    }

    public final boolean isCircleMode() {
        return this.hudMode.is(MODE_CIRCLE);
    }

    public final boolean barWhite() {
        return this.barColorMode.is(BAR_WHITE);
    }

    public final boolean barClient() {
        return this.barColorMode.is(BAR_CLIENT);
    }

    public final boolean followTarget() {
        return this.followTarget.getValue();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u0006\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/TargetHudModule.Companion;", "", "<init>", "()V", "", "MODE_CLASSIC", "Ljava/lang/String;", "MODE_NEW", "MODE_CIRCLE", "BAR_FROM_HP", "BAR_WHITE", "BAR_CLIENT", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

