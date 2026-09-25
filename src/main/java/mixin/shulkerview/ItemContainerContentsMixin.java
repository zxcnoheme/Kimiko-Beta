/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item$TooltipContext
 *  net.minecraft.item.tooltip.TooltipType
 *  net.minecraft.text.Text
 *  net.minecraft.component.type.ContainerComponent
 *  net.minecraft.component.ComponentsAccess
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.shulkerview;

import java.util.function.Consumer;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.component.ComponentsAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Utils.ShulkerPreview;

@Mixin(value={ContainerComponent.class})
public abstract class ItemContainerContentsMixin {
    @Inject(method={"appendTooltip"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$shulkerPreviewHideVanillaContainerTooltip(Item.TooltipContext context, Consumer<Text> consumer, TooltipType flag, ComponentsAccess components, CallbackInfo ci) {
        if (ShulkerPreview.enabled()) {
            ci.cancel();
        }
    }
}

