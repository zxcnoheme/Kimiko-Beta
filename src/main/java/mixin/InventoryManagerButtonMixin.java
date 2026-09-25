/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.PlayerInventory
 *  net.minecraft.screen.ScreenHandler
 *  net.minecraft.screen.PlayerScreenHandler
 *  net.minecraft.text.Text
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.Element
 *  net.minecraft.client.gui.widget.PressableWidget
 *  net.minecraft.client.gui.screen.ingame.HandledScreen
 *  net.minecraft.client.gui.screen.ingame.InventoryScreen
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.invmanager.InventoryManagerButtons;

@Mixin(value={InventoryScreen.class})
public abstract class InventoryManagerButtonMixin
extends HandledScreen<PlayerScreenHandler> {
    @Unique
    private PressableWidget kimiko$setsButton;
    @Unique
    private PressableWidget kimiko$arrangeButton;

    protected InventoryManagerButtonMixin(PlayerScreenHandler menu, PlayerInventory inventory, Text title) {
        super(menu, inventory, title);
    }

    @Inject(method={"init"}, at={@At(value="TAIL")})
    private void kimiko$addManagerButtons(CallbackInfo ci) {
        if (this.kimiko$setsButton != null) {
            this.remove((Element)this.kimiko$setsButton);
        }
        if (this.kimiko$arrangeButton != null) {
            this.remove((Element)this.kimiko$arrangeButton);
        }
        int bw = 90;
        int bx = this.x - bw - 4;
        this.kimiko$setsButton = (PressableWidget)this.addDrawableChild(InventoryManagerButtons.createSetsButton(bx, this.y, bw, 20));
        this.kimiko$arrangeButton = (PressableWidget)this.addDrawableChild(InventoryManagerButtons.createArrangeButton(bx, this.y + 23, bw, 20));
    }

    @Inject(method={"render"}, at={@At(value="TAIL")}, require=0)
    private void kimiko$tickArranger(DrawContext graphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        InventoryManagerButtons.tickArranger(this);
    }
}

