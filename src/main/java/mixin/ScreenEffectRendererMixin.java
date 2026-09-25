/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.texture.Sprite
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.Items
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.VertexConsumerProvider
 *  net.minecraft.client.gui.hud.InGameOverlayRenderer
 *  net.minecraft.util.math.random.Random
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.client.texture.Sprite;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Visuals.NoRender;
import rtx.kimiko.api.modules.impl.Visuals.WastedDeath;

@Mixin(value={InGameOverlayRenderer.class})
public abstract class ScreenEffectRendererMixin {
    @Inject(method={"renderFireOverlay"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private static void kimiko$noRenderFire(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Sprite sprite, CallbackInfo ci) {
        if (NoRender.isActive("Огонь")) {
            ci.cancel();
        }
    }

    @Inject(method={"setFloatingItem"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$noTotemPopAnimation(ItemStack stack, Random random, CallbackInfo ci) {
        if (stack.isOf(Items.TOTEM_OF_UNDYING) && NoRender.isActive("Тотем")) {
            ci.cancel();
        }
    }

    @Inject(method={"renderOverlays"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$wastedHideOverlay(boolean sleeping, float partialTick, OrderedRenderCommandQueue collector, CallbackInfo ci) {
        if (WastedDeath.isRunning()) {
            ci.cancel();
        }
    }
}

