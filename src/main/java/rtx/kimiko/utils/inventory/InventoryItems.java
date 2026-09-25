/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.item.equipment.EquipmentAssetKeys
 *  net.minecraft.component.type.EquippableComponent
 *  net.minecraft.entity.EquipmentSlot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.Items
 *  net.minecraft.registry.RegistryKey
 *  net.minecraft.component.DataComponentTypes
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.inventory;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.component.DataComponentTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001d\u0010\n\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\n\u0010\tJ\u001d\u0010\u000b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\tJ\u001d\u0010\f\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\f\u0010\tJ\u001d\u0010\r\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\r\u0010\tJ'\u0010\u0010\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/utils/inventory/InventoryItems;", "", "<init>", "()V", "Lnet/minecraft/ItemStack;", "stack", "", "Lkotlin/jvm/JvmStatic;", "isElytra", "(Lnet/minecraft/ItemStack;)Z", "isChestplate", "isChestEquippable", "isPlayerHead", "isEnchantedTotem", "", "query", "nameContains", "(Lnet/minecraft/ItemStack;Ljava/lang/String;)Z", "rtx.kimiko:kimiko"})
public final class InventoryItems {
    @NotNull
    public static final InventoryItems INSTANCE = new InventoryItems();

    private InventoryItems() {
    }

    @JvmStatic
    public static final boolean isElytra(@Nullable ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }
        EquippableComponent equippableComponent2 = (EquippableComponent)stack.get(DataComponentTypes.EQUIPPABLE);
        if (equippableComponent2 == null) {
            return false;
        }
        EquippableComponent equippable = equippableComponent2;
        return equippable.assetId().map(id -> EquipmentAssetKeys.ELYTRA.equals(id)).orElse(false);
    }

    @JvmStatic
    public static final boolean isChestplate(@Nullable ItemStack stack) {
        return InventoryItems.isChestEquippable(stack) && !InventoryItems.isElytra(stack);
    }

    @JvmStatic
    public static final boolean isChestEquippable(@Nullable ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }
        EquippableComponent equippableComponent2 = (EquippableComponent)stack.get(DataComponentTypes.EQUIPPABLE);
        if (equippableComponent2 == null) {
            return false;
        }
        EquippableComponent equippable = equippableComponent2;
        return equippable.slot() == EquipmentSlot.CHEST;
    }

    @JvmStatic
    public static final boolean isPlayerHead(@Nullable ItemStack stack) {
        return stack != null && !stack.isEmpty() && stack.isOf(Items.PLAYER_HEAD);
    }

    @JvmStatic
    public static final boolean isEnchantedTotem(@Nullable ItemStack stack) {
        return stack != null && !stack.isEmpty() && stack.isOf(Items.TOTEM_OF_UNDYING) && stack.hasEnchantments();
    }

    @JvmStatic
    public static final boolean nameContains(@Nullable ItemStack stack, @Nullable String query) {
        CharSequence charSequence;
        if (stack == null || stack.isEmpty() || (charSequence = (CharSequence)query) == null || StringsKt.isBlank((CharSequence)charSequence)) {
            return false;
        }
        String string = stack.getName().getString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getString(...)");
        charSequence = string;
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string2 = ((String)charSequence).toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
        CharSequence charSequence2 = string2;
        charSequence = query;
        Locale locale2 = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale2, (String)"ROOT");
        String string3 = ((String)charSequence).toLowerCase(locale2);
        Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toLowerCase(...)");
        return String.valueOf(charSequence2).contains(string3);
    }
}

