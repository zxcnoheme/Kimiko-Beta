/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  net.minecraft.entity.player.PlayerInventory
 *  net.minecraft.screen.ScreenHandler
 *  net.minecraft.screen.slot.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.font.TextRenderer
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.screen.ingame.HandledScreen
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.impl.inventory.HandledScreenEvent;
import rtx.kimiko.api.invmanager.InventoryTemplates;
import rtx.kimiko.api.modules.impl.Visuals.BetterMinecraft;
import rtx.kimiko.api.modules.impl.Visuals.ItemHighlight;
import rtx.kimiko.utils.render.others.anim.ItemMoveAnimator;

@Mixin(value={HandledScreen.class})
public abstract class HandledScreenMixin {
    @Shadow
    protected int x;
    @Shadow
    protected int y;
    @Shadow
    @Final
    protected int backgroundWidth;
    @Shadow
    @Final
    protected int backgroundHeight;
    @Shadow
    @Final
    protected ScreenHandler handler;
    @Shadow
    @Nullable
    protected Slot focusedSlot;
    @Unique
    private boolean kimiko$panelAnimated;
    @Unique
    private boolean kimiko$contentsAnimated;

    @Inject(method={"init"}, at={@At(value="TAIL")}, require=0)
    private void kimiko$trackInventoryOpen(CallbackInfo ci) {
        BetterMinecraft.markInventoryOpen();
    }

    @Inject(method={"renderBackground"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/screen/Screen;renderBackground(Lnet/minecraft/client/gui/DrawContext;IIF)V", shift=At.Shift.AFTER)}, require=0)
    private void kimiko$animatePanel(DrawContext graphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        this.kimiko$panelAnimated = BetterMinecraft.inventoryAnimationEnabled();
        if (!this.kimiko$panelAnimated) {
            return;
        }
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(0.0f, BetterMinecraft.inventorySlideOffset());
    }

    @Inject(method={"renderBackground"}, at={@At(value="RETURN")}, require=0)
    private void kimiko$endPanelAnimation(DrawContext graphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if (!this.kimiko$panelAnimated) {
            return;
        }
        this.kimiko$panelAnimated = false;
        graphics.getMatrices().popMatrix();
    }

    @Inject(method={"renderMain"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$beginContentsAnimation(DrawContext graphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        this.kimiko$contentsAnimated = BetterMinecraft.inventoryAnimationEnabled();
        if (!this.kimiko$contentsAnimated) {
            return;
        }
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(0.0f, BetterMinecraft.inventorySlideOffset());
    }

    @Inject(method={"renderMain"}, at={@At(value="RETURN")}, require=0)
    private void kimiko$endContentsAnimation(DrawContext graphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if (this.kimiko$contentsAnimated) {
            this.kimiko$contentsAnimated = false;
            graphics.getMatrices().popMatrix();
        }
        EventBus.get().post(new HandledScreenEvent(graphics, this.focusedSlot, this.backgroundWidth, this.backgroundHeight));
    }

    @Inject(method={"drawSlot"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$slotHighlightBackground(DrawContext graphics, Slot slot, int mouseX, int mouseY, CallbackInfo ci) {
        if (slot == null || graphics == null) {
            return;
        }
        ItemHighlight module = ItemHighlight.getInstance();
        if (module == null) {
            return;
        }
        int argb = module.backgroundFor(slot.getStack(), false);
        if (argb != 0) {
            module.drawSlotBackground(graphics, slot.x, slot.y, argb);
        }
    }

    @Inject(method={"drawSlots"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$beginSlotAnim(DrawContext graphics, int mouseX, int mouseY, CallbackInfo ci) {
        if (BetterMinecraft.itemMoveAnimationEnabled()) {
            ItemMoveAnimator.beginFrame(this.handler, mouseX, mouseY, this.x, this.y);
        }
    }

    @WrapOperation(method={"drawSlots"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/screen/ingame/HandledScreen;drawSlot(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/screen/slot/Slot;II)V")}, require=0)
    private void kimiko$animateSlot(HandledScreen<?> instance, DrawContext graphics, Slot slot, int mouseX, int mouseY, Operation<Void> original) {
        float[] off;
        float[] fArray = off = BetterMinecraft.itemMoveAnimationEnabled() ? ItemMoveAnimator.offset(slot) : null;
        if (off == null) {
            original.call(new Object[]{instance, graphics, slot, mouseX, mouseY});
            return;
        }
        graphics.getMatrices().pushMatrix();
        if (off[2] != 1.0f) {
            float cx = (float)slot.x + 8.0f;
            float cy = (float)slot.y + 8.0f;
            graphics.getMatrices().translate(cx, cy);
            graphics.getMatrices().scale(off[2], off[2]);
            graphics.getMatrices().translate(-cx, -cy);
        } else {
            graphics.getMatrices().translate(off[0], off[1]);
        }
        original.call(new Object[]{instance, graphics, slot, mouseX, mouseY});
        graphics.getMatrices().popMatrix();
    }

    @Inject(method={"drawSlots"}, at={@At(value="TAIL")}, require=0)
    private void kimiko$renderGhostSlots(DrawContext graphics, int mouseX, int mouseY, CallbackInfo ci) {
        try {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.player == null) {
                return;
            }
            InventoryTemplates.Template template = InventoryTemplates.active();
            if (template == null) {
                return;
            }
            PlayerInventory inventory = mc.player.getInventory();
            Map<String, Integer> missing = HandledScreenMixin.missingCounts(template, inventory);
            for (Slot slot : this.handler.slots) {
                ItemStack ghost;
                int missingCount;
                InventoryTemplates.Entry entry;
                if (slot.inventory != inventory || !slot.isEnabled() || (entry = template.slots.get(slot.getIndex())) == null || !slot.getStack().isEmpty() || (missingCount = HandledScreenMixin.takeMissing(missing, entry)) <= 0 || (ghost = InventoryTemplates.stackFor(entry)).isEmpty()) continue;
                graphics.drawItemWithoutEntity(ghost, slot.x, slot.y);
                graphics.fill(slot.x, slot.y, slot.x + 16, slot.y + 16, -1198222184);
                HandledScreenMixin.drawMissingCount(graphics, missingCount, slot.x, slot.y);
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private static Map<String, Integer> missingCounts(InventoryTemplates.Template template, PlayerInventory inventory) {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        InventoryTemplates.missingFor(template, inventory).forEach((key, item) -> result.put((String)key, item.count));
        return result;
    }

    private static int takeMissing(Map<String, Integer> missing, InventoryTemplates.Entry entry) {
        String key = InventoryTemplates.entryLayoutKey(entry);
        int count = missing.getOrDefault(key, 0);
        int used = Math.min(count, entry.count());
        if (used > 0) {
            missing.put(key, count - used);
        }
        return used;
    }

    private static void drawMissingCount(DrawContext graphics, int count, int x, int y) {
        if (count <= 1) {
            return;
        }
        String text = String.valueOf(count);
        TextRenderer font = MinecraftClient.getInstance().textRenderer;
        graphics.drawText(font, text, x + 17 - font.getWidth(text), y + 9, -4671304, true);
    }
}

