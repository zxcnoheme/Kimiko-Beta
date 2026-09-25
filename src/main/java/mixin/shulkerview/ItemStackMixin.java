/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.item.Item$TooltipContext
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.tooltip.TooltipType
 *  net.minecraft.text.Text
 *  net.minecraft.item.tooltip.TooltipData
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package mixin.shulkerview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import mods.shulkerview.ShulkerPreviewHelper;
import mods.shulkerview.tooltip.ShulkerPreviewTooltipComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.item.tooltip.TooltipData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ItemStack.class})
public abstract class ItemStackMixin {
    @Inject(method={"getTooltipData"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$shulkerPreviewTooltipImage(CallbackInfoReturnable<Optional<TooltipData>> cir) {
        ItemStack stack = (ItemStack)(Object)this;
        if (ShulkerPreviewHelper.shouldShowPreview(stack)) {
            List<ItemStack> items = ShulkerPreviewHelper.getItems(stack);
            cir.setReturnValue(Optional.of(new ShulkerPreviewTooltipComponent(stack.copy(), items, true)));
        }
    }

    @Inject(method={"getTooltip"}, at={@At(value="RETURN")}, cancellable=true)
    private void kimiko$shulkerPreviewTooltipLines(Item.TooltipContext context, PlayerEntity player, TooltipType flag, CallbackInfoReturnable<List<Text>> cir) {
        ItemStack stack = (ItemStack)(Object)this;
        ArrayList<Text> lines = new ArrayList<Text>((Collection)cir.getReturnValue());
        lines.addAll(ShulkerPreviewHelper.tooltipLines(stack));
        cir.setReturnValue(lines);
    }
}

