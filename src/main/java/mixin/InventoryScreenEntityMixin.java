/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen
 *  net.minecraft.client.gui.screen.ingame.InventoryScreen
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import rtx.kimiko.api.modules.impl.Visuals.BetterMinecraft;

@Mixin(value={InventoryScreen.class, CreativeInventoryScreen.class})
public abstract class InventoryScreenEntityMixin {
    @Redirect(method={"drawBackground"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/screen/ingame/InventoryScreen;drawEntity(Lnet/minecraft/client/gui/DrawContext;IIIIIFFFLnet/minecraft/entity/LivingEntity;)V"), require=0)
    private void kimiko$slideInventoryEntity(DrawContext graphics, int x1, int y1, int x2, int y2, int size, float f, float mouseX, float mouseY, LivingEntity entity) {
        int off = Math.round(BetterMinecraft.inventorySlideOffset());
        InventoryScreen.drawEntity((DrawContext)graphics, (int)x1, (int)(y1 + off), (int)x2, (int)(y2 + off), (int)size, (float)f, (float)mouseX, (float)mouseY, (LivingEntity)entity);
    }
}

