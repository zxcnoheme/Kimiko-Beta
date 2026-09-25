/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.custompet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Visuals.custompet.CustomPetVariantKt;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0086\u0081\u0002\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\bJ\r\u0010\n\u001a\u00020\u0006\u00a2\u0006\u0004\b\n\u0010\bJ\r\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\u0004\b\u000b\u0010\bJ\r\u0010\f\u001a\u00020\u0006\u00a2\u0006\u0004\b\f\u0010\bJ\r\u0010\r\u001a\u00020\u0006\u00a2\u0006\u0004\b\r\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;", "", "", "settingValue", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "", "isRobot", "()Z", "usesMerchantBody", "usesMerchantLeaf", "usesGardenerGear", "usesSorcererHat", "usesFishermanGear", "Ljava/lang/String;", "getSettingValue", "()Ljava/lang/String;", "Companion", "DEFAULT", "NITWIT", "GARDENER", "FISHERMAN", "MERCHANT", "SORCERER", "ROBOT", "rtx.kimiko:kimiko"})
public enum CustomPetVariant {
        DEFAULT("Обычная"),
        NITWIT("Нитвит"),
        GARDENER("Фермер"),
        FISHERMAN("Рыбак"),
        MERCHANT("Путешественник"),
        SORCERER("Ведьма"),
        ROBOT("Робот");
@NotNull
    public static final Companion Companion;
    @NotNull
    private final String settingValue;
    
    
    
    
    
    
    
    
    private CustomPetVariant(String settingValue) {
        this.settingValue = settingValue;
    }

    @NotNull
    public final String getSettingValue() {
        return this.settingValue;
    }

    public final boolean isRobot() {
        return this == ROBOT;
    }

    public final boolean usesMerchantBody() {
        return this == MERCHANT;
    }

    public final boolean usesMerchantLeaf() {
        return this == MERCHANT;
    }

    public final boolean usesGardenerGear() {
        return this == GARDENER;
    }

    public final boolean usesSorcererHat() {
        return this == SORCERER;
    }

    public final boolean usesFishermanGear() {
        return this == FISHERMAN;
    }

    

    

    @NotNull
    public static EnumEntries<CustomPetVariant> getEntries() {
        return EnumEntriesKt.enumEntries(values());
    }

    @JvmStatic
    @NotNull
    public static final String[] settingValues() {
        return Companion.settingValues();
    }

    @JvmStatic
    @NotNull
    public static final CustomPetVariant fromSettingValue(@Nullable String value) {
        return Companion.fromSettingValue(value);
    }

    @JvmStatic
    @NotNull
    public static final CustomPetVariant fromSerializedName(@Nullable String value) {
        return Companion.fromSerializedName(value);
    }

            static {
        Companion = new Companion(null);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\u0005H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\r\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\u0005H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\r\u0010\f\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant.Companion;", "", "<init>", "()V", "", "", "Lkotlin/jvm/JvmStatic;", "settingValues", "()[Ljava/lang/String;", "value", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;", "fromSettingValue", "(Ljava/lang/String;)Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;", "fromSerializedName", "rtx.kimiko:kimiko"})
    @SourceDebugExtension(value={"SMAP\nCustomPetVariant.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CustomPetVariant.kt\nrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant.Companion\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,91:1\n37#2,2:92\n*S KotlinDebug\n*F\n+ 1 CustomPetVariant.kt\nrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant.Companion\n*L\n48#1:92,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final String[] settingValues() {
            ArrayList<String> values = new ArrayList<String>();
            for (CustomPetVariant variant : CustomPetVariant.getEntries()) {
                if (variant == DEFAULT || variant == ROBOT) continue;
                values.add(variant.getSettingValue());
            }
            return values.toArray(new String[0]);
        }

        @JvmStatic
        @NotNull
        public final CustomPetVariant fromSettingValue(@Nullable String value) {
            Object object = value;
            if (object == null || StringsKt.isBlank((CharSequence)object)) {
                return NITWIT;
            }
            for (CustomPetVariant variant : CustomPetVariant.getEntries()) {
                if (!CustomPetVariantKt.access$equalsIgnoreCase(variant.getSettingValue(), value)) continue;
                return variant;
            }
            String string = ((Object)StringsKt.trim((CharSequence)value)).toString();
            Locale locale = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
            String string2 = string.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
            return switch (string2) {
                case "default", "обычная" -> NITWIT;
                case "nitwit" -> NITWIT;
                case "gardener", "садовник", "farmer" -> GARDENER;
                case "fisherman" -> FISHERMAN;
                case "traveler", "торговец", "merchant", "traveller" -> MERCHANT;
                case "sorcerer", "колдун", "witch" -> SORCERER;
                default -> NITWIT;
            };
        }

        @JvmStatic
        @NotNull
        public final CustomPetVariant fromSerializedName(@Nullable String value) {
            if (value == null || StringsKt.isBlank((CharSequence)value)) {
                return NITWIT;
            }
            try {
                String string = value.toUpperCase(Locale.ROOT);
                CustomPetVariant variant = CustomPetVariant.valueOf(string);
                return variant == DEFAULT ? NITWIT : variant;
            }
            catch (IllegalArgumentException ignored) {
                return this.fromSettingValue(value);
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

