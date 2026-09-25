/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  net.minecraft.item.ItemStack
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.inventory;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.inventory.SlotItem;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0013B!\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0004\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0006\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0010R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0011R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0012j\u0002\b\u0014\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/utils/inventory/SlotCategory;", "", "", "settingKey", "Lrtx/kimiko/utils/inventory/SlotItem$Group;", "itemGroup", "", "defaultColor", "<init>", "(Ljava/lang/String;ILjava/lang/String;Lrtx/kimiko/utils/inventory/SlotItem$Group;I)V", "getSettingKey", "()Ljava/lang/String;", "getItemGroup", "()Lrtx/kimiko/utils/inventory/SlotItem$Group;", "getDefaultColor", "()I", "Ljava/lang/String;", "Lrtx/kimiko/utils/inventory/SlotItem$Group;", "I", "Companion", "POTION", "rtx.kimiko:kimiko"})
public enum SlotCategory {
        POTION("Взрывные зелья", SlotItem.Group.POTION, 16738740);
@NotNull
    public static final Companion Companion;
    @NotNull
    private final String settingKey;
    @NotNull
    private final SlotItem.Group itemGroup;
    private final int defaultColor;
    
    
    private SlotCategory(String settingKey, SlotItem.Group itemGroup, int defaultColor) {
        this.settingKey = settingKey;
        this.itemGroup = itemGroup;
        this.defaultColor = defaultColor;
    }

    @NotNull
    public final String getSettingKey() {
        return this.settingKey;
    }

    @NotNull
    public final SlotItem.Group getItemGroup() {
        return this.itemGroup;
    }

    public final int getDefaultColor() {
        return this.defaultColor;
    }

    

    

    @NotNull
    public static EnumEntries<SlotCategory> getEntries() {
        return EnumEntriesKt.enumEntries(values());
    }

    @JvmStatic
    @Nullable
    public static final SlotCategory classify(@Nullable ItemStack stack) {
        return Companion.classify(stack);
    }

            static {
        Companion = new Companion(null);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/utils/inventory/SlotCategory.Companion;", "", "<init>", "()V", "Lnet/minecraft/ItemStack;", "stack", "Lrtx/kimiko/utils/inventory/SlotCategory;", "Lkotlin/jvm/JvmStatic;", "classify", "(Lnet/minecraft/ItemStack;)Lrtx/kimiko/utils/inventory/SlotCategory;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final SlotCategory classify(@Nullable ItemStack stack) {
            return SlotItem.Companion.match(stack, SlotItem.Group.POTION) != null ? POTION : null;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

