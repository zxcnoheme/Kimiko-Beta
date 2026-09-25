/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.item.ItemModelManager
 *  net.minecraft.client.render.item.ItemRenderState
 *  net.minecraft.util.HeldItemContext
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.Items
 *  net.minecraft.world.World
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.item.ItemDisplayContext
 *  net.minecraft.component.type.CustomModelDataComponent
 *  net.minecraft.component.DataComponentTypes
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 */
package mixin;

import java.util.List;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.util.HeldItemContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.component.DataComponentTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import rtx.kimiko.api.modules.impl.Visuals.CustomSwords;

@Mixin(value={ItemModelManager.class})
public abstract class ItemModelResolverMixin {
    @ModifyArg(method={"clearAndUpdate"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/item/ItemModelManager;update(Lnet/minecraft/client/render/item/ItemRenderState;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/world/World;Lnet/minecraft/util/HeldItemContext;I)V"), index=1)
    private ItemStack kimiko$replaceSwordModel(ItemRenderState renderState, ItemStack stack, ItemDisplayContext displayContext, World level, HeldItemContext itemOwner, int seed) {
        CustomSwords module = CustomSwords.getInstance();
        String previewWeapon = CustomSwords.preview();
        if (previewWeapon != null) {
            if (!ItemModelResolverMixin.kimiko$isSword(stack)) {
                return stack;
            }
            return CustomSwords.previewStack(stack, previewWeapon);
        }
        if (module == null || !module.isEnabled() || !ItemModelResolverMixin.kimiko$isSword(stack)) {
            return stack;
        }
        if (module.isSelfOnly()) {
            MinecraftClient minecraft = MinecraftClient.getInstance();
            if (minecraft.player == null || itemOwner != minecraft.player) {
                return stack;
            }
        }
        ItemStack renderedStack = stack.copy();
        renderedStack.set(DataComponentTypes.CUSTOM_MODEL_DATA, new CustomModelDataComponent(List.of(), List.of(), List.of(module.getSelectedWeapon()), List.of()));
        return renderedStack;
    }

    private static boolean kimiko$isSword(ItemStack stack) {
        return stack.isOf(Items.WOODEN_SWORD) || stack.isOf(Items.STONE_SWORD) || stack.isOf(Items.COPPER_SWORD) || stack.isOf(Items.IRON_SWORD) || stack.isOf(Items.GOLDEN_SWORD) || stack.isOf(Items.DIAMOND_SWORD) || stack.isOf(Items.NETHERITE_SWORD);
    }
}

