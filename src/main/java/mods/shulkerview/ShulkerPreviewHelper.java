/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.util.Window
 *  net.minecraft.util.Formatting
 *  net.minecraft.item.BlockItem
 *  net.minecraft.util.DyeColor
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.Items
 *  net.minecraft.block.Block
 *  net.minecraft.util.collection.DefaultedList
 *  net.minecraft.block.ShulkerBoxBlock
 *  net.minecraft.text.Text
 *  net.minecraft.text.Style
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.util.InputUtil
 *  net.minecraft.text.MutableText
 *  net.minecraft.component.type.ContainerComponent
 *  net.minecraft.component.DataComponentTypes
 */
package mods.shulkerview;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.util.Window;
import net.minecraft.util.Formatting;
import net.minecraft.item.BlockItem;
import net.minecraft.util.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.block.Block;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.text.Text;
import net.minecraft.text.Style;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.MutableText;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.component.DataComponentTypes;
import rtx.kimiko.api.modules.impl.Utils.ShulkerPreview;

public final class ShulkerPreviewHelper {
    public static final int SHULKER_SIZE = 27;
    public static final int ROW_SIZE = 9;
    private static ItemStack frozenShulker = null;
    private static float frozenOverlayX = 0.0f;
    private static float frozenOverlayY = 0.0f;
    private static ItemStack lastShownShulker = null;
    private static float lastRenderedX = 0.0f;
    private static float lastRenderedY = 0.0f;
    private static long lastRenderedTime = 0L;

    private ShulkerPreviewHelper() {
    }

    public static boolean canPreview(ItemStack stack) {
        BlockItem blockItem;
        Item item2;
        return ShulkerPreview.enabled() && stack != null && !stack.isEmpty() && (item2 = stack.getItem()) instanceof BlockItem && (blockItem = (BlockItem)item2).getBlock() instanceof ShulkerBoxBlock && stack.get(DataComponentTypes.CONTAINER) != null && ShulkerPreviewHelper.hasVisibleContent(stack);
    }

    public static boolean shouldShowPreview(ItemStack stack) {
        if (!ShulkerPreviewHelper.canPreview(stack)) {
            return false;
        }
        if (ShulkerPreviewHelper.isFrozen()) {
            return false;
        }
        return ShulkerPreviewHelper.previewKeyPressed();
    }

    public static boolean previewKeyPressed() {
        MinecraftClient mc = MinecraftClient.getInstance();
        return mc != null && mc.getWindow() != null && InputUtil.isKeyPressed((Window)mc.getWindow(), (int)340);
    }

    public static boolean ctrlKeyPressed() {
        MinecraftClient mc = MinecraftClient.getInstance();
        return mc != null && mc.getWindow() != null && InputUtil.isKeyPressed((Window)mc.getWindow(), (int)341);
    }

    public static boolean isFrozen() {
        return frozenShulker != null;
    }

    public static ItemStack getFrozenShulker() {
        return frozenShulker;
    }

    public static float getFrozenOverlayX() {
        return frozenOverlayX;
    }

    public static float getFrozenOverlayY() {
        return frozenOverlayY;
    }

    public static void tryFreeze() {
        long now = System.currentTimeMillis();
        if (lastShownShulker != null && ShulkerPreviewHelper.canPreview(lastShownShulker) && now - lastRenderedTime < 200L) {
            frozenShulker = lastShownShulker.copy();
            frozenOverlayX = lastRenderedX;
            frozenOverlayY = lastRenderedY;
        }
    }

    public static void unfreeze() {
        frozenShulker = null;
    }

    public static void recordTooltipPosition(ItemStack source, int tooltipX, int tooltipY) {
        lastShownShulker = source;
        lastRenderedX = tooltipX;
        lastRenderedY = tooltipY;
        lastRenderedTime = System.currentTimeMillis();
    }

    public static int contentStackCount(ItemStack stack) {
        int count = 0;
        for (ItemStack item : ShulkerPreviewHelper.getItems(stack)) {
            if (item.isEmpty() || item.getItem() == Items.AIR) continue;
            ++count;
        }
        return count;
    }

    public static int shulkerColor(ItemStack stack) {
        DyeColor dyeColor2;
        Block block;
        Item item2 = stack.getItem();
        if (item2 instanceof BlockItem) {
            BlockItem blockItem = (BlockItem)item2;
            block = blockItem.getBlock();
        } else {
            block = null;
        }
        if (block instanceof ShulkerBoxBlock) {
            ShulkerBoxBlock shulker = (ShulkerBoxBlock)block;
            dyeColor2 = shulker.getColor();
        } else {
            dyeColor2 = null;
        }
        DyeColor dye = dyeColor2;
        int rgb = dye == null ? 0x976797 : ShulkerPreviewHelper.dyeColor(dye);
        return 0xFF000000 | rgb;
    }

    public static List<ItemStack> getItems(ItemStack stack) {
        DefaultedList items = DefaultedList.ofSize((int)27, (Object)ItemStack.EMPTY);
        ContainerComponent contents = (ContainerComponent)stack.get(DataComponentTypes.CONTAINER);
        if (contents != null) {
            contents.copyTo(items);
        }
        return List.copyOf(items);
    }

    public static List<ItemStack> getCompactItems(ItemStack stack) {
        ArrayList<ItemStack> compact = new ArrayList<ItemStack>();
        for (ItemStack item : ShulkerPreviewHelper.getItems(stack)) {
            if (item.isEmpty() || item.getItem() == Items.AIR) continue;
            ItemStack existing = ShulkerPreviewHelper.findMatching(compact, item);
            if (existing == null) {
                compact.add(item.copy());
                continue;
            }
            existing.increment(item.getCount());
        }
        compact.sort((keyCodec, elementCodec) -> Integer.compare(elementCodec.getCount(), keyCodec.getCount()));
        return compact;
    }

    public static List<Text> tooltipLines(ItemStack stack) {
        if (!ShulkerPreviewHelper.canPreview(stack)) {
            return List.of();
        }
        MutableText shiftHint = Text.literal((String)"Shift").setStyle(Style.EMPTY.withColor(Formatting.YELLOW)).append((Text)Text.literal((String)": preview").setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
        MutableText ctrlHint = Text.literal((String)"  Ctrl").setStyle(Style.EMPTY.withColor(Formatting.YELLOW)).append((Text)Text.literal((String)": pin  ").setStyle(Style.EMPTY.withColor(Formatting.GRAY))).append((Text)Text.literal((String)"Shift+Ctrl").setStyle(Style.EMPTY.withColor(Formatting.YELLOW))).append((Text)Text.literal((String)": item info").setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
        return List.of(Text.translatable((String)"container.shulkerbox.contains", (Object[])new Object[]{ShulkerPreviewHelper.contentStackCount(stack)}), shiftHint, ctrlHint);
    }

    private static boolean hasVisibleContent(ItemStack stack) {
        ContainerComponent contents = (ContainerComponent)stack.get(DataComponentTypes.CONTAINER);
        if (contents == null) {
            return false;
        }
        DefaultedList<ItemStack> items = DefaultedList.ofSize(27, ItemStack.EMPTY);
        contents.copyTo(items);
        for (ItemStack item : items) {
            if (item.isEmpty() || item.getItem() == Items.AIR) continue;
            return true;
        }
        return false;
    }

    private static ItemStack findMatching(List<ItemStack> stacks, ItemStack needle) {
        for (ItemStack stack : stacks) {
            if (!ItemStack.areEqual((ItemStack)stack, (ItemStack)needle)) continue;
            return stack;
        }
        return null;
    }

    private static int dyeColor(DyeColor dye) {
        int color = dye.getEntityColor();
        int r = Math.max(38, color >> 16 & 0xFF);
        int g = Math.max(38, color >> 8 & 0xFF);
        int elementCodec = Math.max(38, color & 0xFF);
        return r << 16 | g << 8 | elementCodec;
    }
}

