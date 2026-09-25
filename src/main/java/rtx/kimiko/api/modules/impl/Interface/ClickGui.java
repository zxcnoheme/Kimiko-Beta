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
package rtx.kimiko.api.modules.impl.Interface;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.ui.UiScale;
import rtx.kimiko.utils.key.KeyBind;

@Feature(value={"clickgui"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u0019\u0010\t\u001a\u00020\u00078\u0006X\u0087\u0004\u0092\u0002\u0002\b\b\u00a2\u0006\u0006\n\u0004\b\t\u0010\n\u00ca\u0001\u0010\b\f\u0012\f\b\r\u0012\b\b\fJ\u0004\b\b(\u000e\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/ClickGui;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "defaultEnabled", "()Z", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lkotlin/jvm/JvmField;", "scale", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "clickgui", "rtx.kimiko:kimiko"})
public final class ClickGui
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public final ModeSetting scale;
    @Nullable
    private static ClickGui companionInstance;

    public ClickGui() {
        super("Click GUI", "Открывает клик-меню клиента.", Category.DISPLAY);
        String[] stringArray = UiScale.LABELS;
        this.scale = (ModeSetting)this.register((Setting)new ModeSetting("Масштаб меню", "Размер клик-меню. Внутри меню: Ctrl + колесо или Ctrl + \"-\" / \"=\".", UiScale.defaultLabel(), Arrays.copyOf(stringArray, stringArray.length)));
        this.setBind(KeyBind.Companion.keyboard(344));
        companionInstance = this;
        this.scale.setChangeListener(() -> ClickGui._init_$lambda$0(this));
    }

    @Override
    public boolean defaultEnabled() {
        return true;
    }

    private static final void _init_$lambda$0(ClickGui this$0) {
        UiScale.select(this$0.scale.getValue());
    }

    @JvmStatic
    @Nullable
    public static final ClickGui getInstance() {
        return Companion.getInstance();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/ClickGui.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Interface/ClickGui;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Interface/ClickGui;", "companionInstance", "Lrtx/kimiko/api/modules/impl/Interface/ClickGui;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final ClickGui getInstance() {
            return companionInstance;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

