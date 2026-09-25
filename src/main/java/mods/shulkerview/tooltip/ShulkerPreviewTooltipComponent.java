/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.tooltip.TooltipData
 */
package mods.shulkerview.tooltip;

import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipData;

public record ShulkerPreviewTooltipComponent(ItemStack source, List<ItemStack> items, boolean fullPreview) implements TooltipData
{
}

