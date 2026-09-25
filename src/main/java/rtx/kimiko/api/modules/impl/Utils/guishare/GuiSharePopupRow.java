/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils.guishare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ButtonSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.MultiSelectSetting;
import rtx.kimiko.api.modules.settings.impl.RangeSliderSetting;
import rtx.kimiko.api.modules.settings.impl.SelectSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.modules.settings.impl.TextSetting;
import rtx.kimiko.utils.key.KeyBind;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0015\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u0000 .2\u00020\u0001:\u0001.BU\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\u0006\u0010\u000f\u001a\u00020\b\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0013J\u0010\u0010\u0016\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u001e\u0010\u0019J\u0010\u0010\u001f\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u001f\u0010\u0019Jp\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\bH\u00c6\u0001\u00a2\u0006\u0004\b \u0010!J\u001b\u0010$\u001a\u00020\n2\b\u0010#\u001a\u0004\u0018\u00010\"H\u00d6\u0083\u0004\u00a2\u0006\u0004\b$\u0010%J\u0011\u0010&\u001a\u00020\bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b&\u0010\u0019J\u0011\u0010'\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b'\u0010\u0013R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b(\u00a2\u0006\u0006\n\u0004\b\u0003\u0010)R\u0019\u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b(\u00a2\u0006\u0006\n\u0004\b\u0004\u0010)R\u0019\u0010\u0005\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b(\u00a2\u0006\u0006\n\u0004\b\u0005\u0010)R\u0019\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\b(\u00a2\u0006\u0006\n\u0004\b\u0007\u0010*R\u0019\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b(\u00a2\u0006\u0006\n\u0004\b\t\u0010+R\u0019\u0010\u000b\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b(\u00a2\u0006\u0006\n\u0004\b\u000b\u0010,R\u001f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\f8\u0006X\u0087\u0004\u0092\u0002\u0002\b(\u00a2\u0006\u0006\n\u0004\b\r\u0010-R\u0019\u0010\u000e\u001a\u00020\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b(\u00a2\u0006\u0006\n\u0004\b\u000e\u0010+R\u0019\u0010\u000f\u001a\u00020\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b(\u00a2\u0006\u0006\n\u0004\b\u000f\u0010+\u00a8\u0006/"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiSharePopupRow;", "Ljava/lang/Record;", "", "type", "name", "value", "", "fraction", "", "rgb", "", "open", "", "options", "selectedMask", "alpha", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FIZLjava/util/List;II)V", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "()F", "component5", "()I", "component6", "()Z", "component7", "()Ljava/util/List;", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FIZLjava/util/List;II)Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiSharePopupRow;", "", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Lkotlin/jvm/JvmField;", "Ljava/lang/String;", "F", "I", "Z", "Ljava/util/List;", "Companion", "rtx.kimiko:kimiko"})
public final class GuiSharePopupRow
{
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public final String type;
    @JvmField
    @NotNull
    public final String name;
    @JvmField
    @NotNull
    public final String value;
    @JvmField
    public final float fraction;
    @JvmField
    public final int rgb;
    @JvmField
    public final boolean open;
    @JvmField
    @NotNull
    public final List<String> options;
    @JvmField
    public final int selectedMask;
    @JvmField
    public final int alpha;
    public static final int MAX_ROWS = 28;
    public static final int MAX_NAME = 32;
    public static final int MAX_VALUE = 24;
    public static final int MAX_OPTIONS = 24;

    public GuiSharePopupRow(@NotNull String type, @NotNull String name, @NotNull String value, float fraction, int rgb, boolean open, @NotNull List<String> options, int selectedMask, int alpha) {
        Intrinsics.checkNotNullParameter((Object)type, (String)"type");
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)value, (String)"value");
        Intrinsics.checkNotNullParameter(options, (String)"options");
        this.type = type;
        this.name = name;
        this.value = value;
        this.fraction = fraction;
        this.rgb = rgb;
        this.open = open;
        this.options = options;
        this.selectedMask = selectedMask;
        this.alpha = alpha;
    }

    @NotNull
    public final String component1() {
        return this.type;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final String component3() {
        return this.value;
    }

    public final float component4() {
        return this.fraction;
    }

    public final int component5() {
        return this.rgb;
    }

    public final boolean component6() {
        return this.open;
    }

    @NotNull
    public final List<String> component7() {
        return this.options;
    }

    public final int component8() {
        return this.selectedMask;
    }

    public final int component9() {
        return this.alpha;
    }

    @NotNull
    public final GuiSharePopupRow copy(@NotNull String type, @NotNull String name, @NotNull String value, float fraction, int rgb, boolean open, @NotNull List<String> options, int selectedMask, int alpha) {
        Intrinsics.checkNotNullParameter((Object)type, (String)"type");
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)value, (String)"value");
        Intrinsics.checkNotNullParameter(options, (String)"options");
        return new GuiSharePopupRow(type, name, value, fraction, rgb, open, options, selectedMask, alpha);
    }

    public static /* synthetic */ GuiSharePopupRow copy$default(GuiSharePopupRow guiSharePopupRow, String string, String string2, String string3, float f, int n, boolean bl, List list, int n2, int n3, int n4, Object object) {
        if ((n4 & 1) != 0) {
            string = guiSharePopupRow.type;
        }
        if ((n4 & 2) != 0) {
            string2 = guiSharePopupRow.name;
        }
        if ((n4 & 4) != 0) {
            string3 = guiSharePopupRow.value;
        }
        if ((n4 & 8) != 0) {
            f = guiSharePopupRow.fraction;
        }
        if ((n4 & 0x10) != 0) {
            n = guiSharePopupRow.rgb;
        }
        if ((n4 & 0x20) != 0) {
            bl = guiSharePopupRow.open;
        }
        if ((n4 & 0x40) != 0) {
            list = guiSharePopupRow.options;
        }
        if ((n4 & 0x80) != 0) {
            n2 = guiSharePopupRow.selectedMask;
        }
        if ((n4 & 0x100) != 0) {
            n3 = guiSharePopupRow.alpha;
        }
        return guiSharePopupRow.copy(string, string2, string3, f, n, bl, list, n2, n3);
    }

    @Override
    @NotNull
    public String toString() {
        return "GuiSharePopupRow(type=" + this.type + ", name=" + this.name + ", value=" + this.value + ", fraction=" + this.fraction + ", rgb=" + this.rgb + ", open=" + this.open + ", options=" + this.options + ", selectedMask=" + this.selectedMask + ", alpha=" + this.alpha + ")";
    }

    @Override
    public int hashCode() {
        int result = this.type.hashCode();
        result = result * 31 + this.name.hashCode();
        result = result * 31 + this.value.hashCode();
        result = result * 31 + Float.hashCode(this.fraction);
        result = result * 31 + Integer.hashCode(this.rgb);
        result = result * 31 + Boolean.hashCode(this.open);
        result = result * 31 + ((Object)this.options).hashCode();
        result = result * 31 + Integer.hashCode(this.selectedMask);
        result = result * 31 + Integer.hashCode(this.alpha);
        return result;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GuiSharePopupRow)) {
            return false;
        }
        GuiSharePopupRow guiSharePopupRow = (GuiSharePopupRow)other;
        if (!Intrinsics.areEqual((Object)this.type, (Object)guiSharePopupRow.type)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.name, (Object)guiSharePopupRow.name)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.value, (Object)guiSharePopupRow.value)) {
            return false;
        }
        if (Float.compare(this.fraction, guiSharePopupRow.fraction) != 0) {
            return false;
        }
        if (this.rgb != guiSharePopupRow.rgb) {
            return false;
        }
        if (this.open != guiSharePopupRow.open) {
            return false;
        }
        if (!Intrinsics.areEqual(this.options, guiSharePopupRow.options)) {
            return false;
        }
        if (this.selectedMask != guiSharePopupRow.selectedMask) {
            return false;
        }
        return this.alpha == guiSharePopupRow.alpha;
    }

    @JvmStatic
    @NotNull
    public static final List<GuiSharePopupRow> capture(@Nullable Module module, @Nullable List<? extends rtx.kimiko.api.ui.settings.Setting> widgets) {
        return Companion.capture(module, widgets);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J3\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000b\u0010\fJ!\u0010\u000e\u001a\u0004\u0018\u00010\r2\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ%\u0010\u0013\u001a\u0004\u0018\u00010\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\rH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J%\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\u00062\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u001bH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ_\u0010*\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\u0006\u0010\"\u001a\u00020!2\u0006\u0010$\u001a\u00020#2\u0006\u0010&\u001a\u00020%2\u000e\u0010'\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00062\u0006\u0010(\u001a\u00020#2\u0006\u0010)\u001a\u00020#H\u0002\u00a2\u0006\u0004\b*\u0010+J!\u0010-\u001a\u00020\r2\b\u0010 \u001a\u0004\u0018\u00010\r2\u0006\u0010,\u001a\u00020#H\u0002\u00a2\u0006\u0004\b-\u0010.J\u0017\u0010/\u001a\u00020!2\u0006\u0010 \u001a\u00020!H\u0002\u00a2\u0006\u0004\b/\u00100R\u0014\u00101\u001a\u00020#8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b1\u00102R\u0014\u00103\u001a\u00020#8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b3\u00102R\u0014\u00104\u001a\u00020#8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b4\u00102R\u0014\u00105\u001a\u00020#8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b5\u00102\u00a8\u00066"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiSharePopupRow.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/Module;", "module", "", "Lrtx/kimiko/api/ui/settings/Setting;", "widgets", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiSharePopupRow;", "Lkotlin/jvm/JvmStatic;", "capture", "(Lrtx/kimiko/api/modules/Module;Ljava/util/List;)Ljava/util/List;", "", "openOverlayName", "(Ljava/util/List;)Ljava/lang/String;", "Lrtx/kimiko/api/modules/settings/Setting;", "backend", "openName", "fromBackend", "(Lrtx/kimiko/api/modules/settings/Setting;Ljava/lang/String;)Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiSharePopupRow;", "source", "trimOptions", "(Ljava/util/List;)Ljava/util/List;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "formatSlider", "(Lrtx/kimiko/api/modules/settings/impl/SliderSetting;)Ljava/lang/String;", "Lrtx/kimiko/api/modules/settings/impl/RangeSliderSetting;", "formatRange", "(Lrtx/kimiko/api/modules/settings/impl/RangeSliderSetting;)Ljava/lang/String;", "type", "name", "value", "", "fraction", "", "rgb", "", "open", "options", "selectedMask", "alpha", "row", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FIZLjava/util/List;II)Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiSharePopupRow;", "max", "trim", "(Ljava/lang/String;I)Ljava/lang/String;", "clamp01", "(F)F", "MAX_ROWS", "I", "MAX_NAME", "MAX_VALUE", "MAX_OPTIONS", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final List<GuiSharePopupRow> capture(@Nullable Module module, @Nullable List<? extends rtx.kimiko.api.ui.settings.Setting> widgets) {
            if (module == null) {
                return CollectionsKt.emptyList();
            }
            String openName = this.openOverlayName(widgets);
            ArrayList<GuiSharePopupRow> rows = new ArrayList<GuiSharePopupRow>();
            try {
                for (Setting backend : module.getSettings().all()) {
                    if (rows.size() < 28) {
                        GuiSharePopupRow row = this.fromBackend(backend, openName);
                        if (row == null) continue;
                        rows.add(row);
                        continue;
                    }
                    break;
                }
            }
            catch (Throwable throwable) {
                return CollectionsKt.emptyList();
            }
            return rows;
        }

        private final String openOverlayName(List<? extends rtx.kimiko.api.ui.settings.Setting> widgets) {
            if (widgets == null) {
                return null;
            }
            try {
                for (rtx.kimiko.api.ui.settings.Setting setting : widgets) {
                    if (setting == null || !setting.isOverlayOpen()) continue;
                    return setting.name();
                }
            } catch (Throwable ignored) {
            }
            return null;
        }

        private final GuiSharePopupRow fromBackend(Setting backend, String openName) {
            boolean open;
            if (backend == null || !backend.isVisible()) {
                return null;
            }
            boolean bl = open = openName != null && Intrinsics.areEqual((Object)openName, (Object)backend.getName());
            if (backend instanceof BooleanSetting) {
                return this.row("b", ((BooleanSetting)backend).getName(), "", ((BooleanSetting)backend).getValue() ? 1.0f : 0.0f, 0, false, CollectionsKt.emptyList(), 0, 0);
            }
            if (backend instanceof SliderSetting) {
                return this.row("s", ((SliderSetting)backend).getName(), this.formatSlider((SliderSetting)backend), this.clamp01(((SliderSetting)backend).getProgress()), 0, false, CollectionsKt.emptyList(), 0, 0);
            }
            if (backend instanceof RangeSliderSetting) {
                String text = this.formatRange((RangeSliderSetting)backend);
                return this.row("s", ((RangeSliderSetting)backend).getName(), text, this.clamp01(((RangeSliderSetting)backend).getMaxProgress()), 0, false, CollectionsKt.emptyList(), 0, 0);
            }
            if (backend instanceof ColorSetting) {
                int argb = ((ColorSetting)backend).getColor();
                int rgb = argb & 0xFFFFFF;
                String string = ((ColorSetting)backend).getName();
                String string2 = "#%06X";
                Object[] objectArray = new Object[]{rgb};
                String string3 = String.format(string2, Arrays.copyOf(objectArray, objectArray.length));
                Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"format(...)");
                return this.row("c", string, string3, 0.0f, rgb, open, CollectionsKt.emptyList(), 0, argb >>> 24 & 0xFF);
            }
            if (backend instanceof SelectSetting) {
                List<String> options = this.trimOptions(((SelectSetting)backend).getOptions());
                int mask = 0;
                int n = ((Collection)options).size();
                for (int i = 0; i < n; ++i) {
                    if (!Intrinsics.areEqual((Object)options.get(i), (Object)((SelectSetting)backend).getSelected())) continue;
                    mask |= 1 << i;
                }
                return this.row("m", ((SelectSetting)backend).getName(), ((SelectSetting)backend).getSelected(), 0.0f, 0, open, options, mask, 0);
            }
            if (backend instanceof MultiSelectSetting) {
                List<String> options = this.trimOptions(((MultiSelectSetting)backend).getOptions());
                int mask = 0;
                int n = ((Collection)options).size();
                for (int i = 0; i < n; ++i) {
                    if (!((MultiSelectSetting)backend).isSelected(options.get(i))) continue;
                    mask |= 1 << i;
                }
                return this.row("n", ((MultiSelectSetting)backend).getName(), ((MultiSelectSetting)backend).getSelected().size() + " of " + ((MultiSelectSetting)backend).getOptions().size(), 0.0f, 0, open, options, mask, 0);
            }
            if (backend instanceof BindSetting) {
                return this.row("k", ((BindSetting)backend).getName(), new KeyBind(((BindSetting)backend).getKey()).getDisplayName(), 0.0f, 0, false, CollectionsKt.emptyList(), 0, 0);
            }
            if (backend instanceof SeparatorSetting) {
                return this.row("p", ((SeparatorSetting)backend).getName(), "", 0.0f, 0, false, CollectionsKt.emptyList(), 0, 0);
            }
            if (backend instanceof TextSetting) {
                boolean empty;
                String value = ((TextSetting)backend).getValue();
                CharSequence i = value;
                boolean bl2 = empty = i == null || i.length() == 0;
                String shown = empty ? (((CharSequence)((TextSetting)backend).getPlaceholder()).length() == 0 ? "..." : ((TextSetting)backend).getPlaceholder()) : value;
                return this.row("x", ((TextSetting)backend).getName(), shown, empty ? 0.0f : 1.0f, 0, false, CollectionsKt.emptyList(), 0, 0);
            }
            if (backend instanceof ButtonSetting) {
                return this.row("u", ((ButtonSetting)backend).getName(), ((ButtonSetting)backend).getLabel(), 0.0f, 0, false, CollectionsKt.emptyList(), 0, 0);
            }
            return null;
        }

        private final List<String> trimOptions(List<String> source) {
            if (source == null || source.isEmpty()) {
                return CollectionsKt.emptyList();
            }
            ArrayList<String> result = new ArrayList<String>(Math.min(source.size(), 24));
            for (int i = 0; i < source.size() && i < 24; ++i) {
                result.add(this.trim(source.get(i), 24));
            }
            return result;
        }

        private final String formatSlider(SliderSetting backend) {
            float value = backend.getValue();
            if (backend.isInteger()) {
                String string = "%.0f";
                Object[] objectArray = new Object[]{Float.valueOf(value)};
                String string2 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
                return string2;
            }
            float inc = backend.getIncrement();
            int decimals = 1;
            if (inc > 0.0f) {
                float step = inc;
                for (decimals = 0; decimals < 3 && Math.abs(step - (float)MathKt.roundToInt((float)step)) > 1.0E-4f; ++decimals) {
                    step *= 10.0f;
                }
                decimals = Math.max(decimals, 1);
            }
            String string = "%." + decimals + "f";
            Object[] objectArray = new Object[]{Float.valueOf(value)};
            String string3 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
            Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"format(...)");
            return string3;
        }

        private final String formatRange(RangeSliderSetting backend) {
            if (backend.isInteger()) {
                String string = "%.0f - %.0f";
                Object[] objectArray = new Object[]{Float.valueOf(backend.getMinValue()), Float.valueOf(backend.getMaxValue())};
                String string2 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
                return string2;
            }
            float inc = backend.getIncrement();
            int decimals = 1;
            if (inc > 0.0f) {
                float step = inc;
                for (decimals = 0; decimals < 3 && Math.abs(step - (float)MathKt.roundToInt((float)step)) > 1.0E-4f; ++decimals) {
                    step *= 10.0f;
                }
                decimals = Math.max(decimals, 1);
            }
            String pattern = "%." + decimals + "f - %." + decimals + "f";
            Object[] objectArray = new Object[]{Float.valueOf(backend.getMinValue()), Float.valueOf(backend.getMaxValue())};
            String string = String.format(pattern, Arrays.copyOf(objectArray, objectArray.length));
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"format(...)");
            return string;
        }

        private final GuiSharePopupRow row(String type, String name, String value, float fraction, int rgb, boolean open, List<String> options, int selectedMask, int alpha) {
            String string = this.trim(name, 32);
            String string2 = this.trim(value, 24);
            List list = options;
            if (list == null) {
                list = CollectionsKt.emptyList();
            }
            return new GuiSharePopupRow(type, string, string2, fraction, rgb & 0xFFFFFF, open, list, selectedMask, alpha & 0xFF);
        }

        private final String trim(String value, int max) {
            String string;
            if (value == null) {
                return "";
            }
            String trimmed = ((Object)StringsKt.trim((CharSequence)value)).toString();
            if (trimmed.length() > max) {
                String string2 = trimmed.substring(0, max);
                string = string2;
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
            } else {
                string = trimmed;
            }
            return string;
        }

        private final float clamp01(float value) {
            return value < 0.0f ? 0.0f : Math.min(value, 1.0f);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

