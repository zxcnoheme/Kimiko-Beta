/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.font.TextRenderer
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.tooltip.TooltipComponent
 *  org.jetbrains.annotations.NotNull
 */
package mods.shulkerview.tooltip;

import java.util.List;
import mods.shulkerview.ShulkerPreviewHelper;
import mods.shulkerview.hook.ShulkerPreviewGuiGraphics;
import mods.shulkerview.tooltip.ShulkerPreviewTooltipComponent;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import org.jetbrains.annotations.NotNull;

public final class ShulkerPreviewClientTooltipComponent
implements TooltipComponent {
    private static final int SLOT_SIZE = 18;
    private static final int SLOT_OFFSET = 8;
    private static final int PADDING = 14;
    private static final Identifier WINDOW_TEXTURE = Identifier.of((String)"shulkerboxtooltip", (String)"shulker_box_tooltip");
    private static final Identifier SLOT_HIGHLIGHT_BACK = Identifier.ofVanilla((String)"container/slot_highlight_back");
    private static final Identifier SLOT_HIGHLIGHT_FRONT = Identifier.ofVanilla((String)"container/slot_highlight_front");
    private final ItemStack source;
    private final List<ItemStack> items;
    private final boolean fullPreview;

    public ShulkerPreviewClientTooltipComponent(ShulkerPreviewTooltipComponent component) {
        this.source = component.source();
        this.items = component.items();
        this.fullPreview = component.fullPreview();
    }

    public int getHeight(@NotNull TextRenderer font) {
        return 14 + this.rows() * 18 + 2;
    }

    public int getWidth(@NotNull TextRenderer font) {
        return 14 + this.columns() * 18;
    }

    public void drawItems(@NotNull TextRenderer font, int x, int y, int totalWidth, int totalHeight, @NotNull DrawContext graphics) {
        int width = this.getWidth(font);
        int height = this.getHeight(font) - 2;
        int mouseX = (graphics instanceof ShulkerPreviewGuiGraphics ext) ? ext.kimiko$getMouseX() : Integer.MIN_VALUE;
        int mouseY = (graphics instanceof ShulkerPreviewGuiGraphics ext) ? ext.kimiko$getMouseY() : Integer.MIN_VALUE;
        ShulkerPreviewHelper.recordTooltipPosition(this.source, x, y);
        graphics.drawGuiTexture(RenderPipelines.GUI_TEXTURED, WINDOW_TEXTURE, x, y, width, height, ShulkerPreviewHelper.shulkerColor(this.source));
        for (int slot = 0; slot < this.items.size(); ++slot) {
            ItemStack stack;
            boolean hovered;
            int slotX = x + 8 + slot % this.columns() * 18;
            int slotY = y + 8 + slot / this.columns() * 18;
            boolean bl = hovered = mouseX >= slotX && mouseX < slotX + 16 && mouseY >= slotY && mouseY < slotY + 16;
            if (hovered) {
                graphics.drawGuiTexture(RenderPipelines.GUI_TEXTURED, SLOT_HIGHLIGHT_BACK, slotX - 4, slotY - 4, 24, 24);
            }
            if (!(stack = this.items.get(slot)).isEmpty()) {
                String count = stack.getCount() > 1 ? String.valueOf(stack.getCount()) : "";
                graphics.drawItem(stack, slotX, slotY);
                graphics.drawStackOverlay(font, stack, slotX, slotY, count);
            }
            if (!hovered) continue;
            graphics.drawGuiTexture(RenderPipelines.GUI_TEXTURED, SLOT_HIGHLIGHT_FRONT, slotX - 4, slotY - 4, 24, 24);
        }
    }

    private int columns() {
        return this.fullPreview ? 9 : Math.max(1, Math.min(9, this.items.size()));
    }

    private int rows() {
        return Math.max(1, (int)Math.ceil((double)this.items.size() / (double)this.columns()));
    }
}

