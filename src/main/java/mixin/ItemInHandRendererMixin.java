/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  com.llamalad7.mixinextras.sugar.Local
 *  com.llamalad7.mixinextras.sugar.impl.SugarWrapper
 *  net.minecraft.client.render.item.ItemRenderState
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.client.render.command.RenderDispatcher
 *  net.minecraft.util.Hand
 *  net.minecraft.util.Arm
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.consume.UseAction
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.VertexConsumerProvider$Immediate
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  net.minecraft.client.network.ClientPlayerEntity
 *  net.minecraft.client.render.item.HeldItemRenderer
 *  net.minecraft.util.math.RotationAxis
 *  net.minecraft.item.ItemDisplayContext
 *  org.joml.Quaternionfc
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.command.RenderDispatcher;
import net.minecraft.util.Hand;
import net.minecraft.util.Arm;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.item.ItemDisplayContext;
import org.joml.Quaternionfc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Visuals.ShaderHands;
import rtx.kimiko.api.modules.impl.Visuals.SwingAnimation;
import rtx.kimiko.api.modules.impl.Visuals.ViewModel;
import rtx.kimiko.utils.render.modules.post.handsflame.HandsItemHitboxTracker;
import rtx.kimiko.utils.render.modules.post.itemoutline.ItemOutlineRenderer;
import rtx.kimiko.utils.render.modules.post.shaderhands.ShaderHandsRenderer;

@Mixin(value={HeldItemRenderer.class})
public abstract class ItemInHandRendererMixin {
    @Unique
    private MatrixStack kimiko$customSwingMatrices;
    @Unique
    private Hand kimiko$customSwingHand;
    @Unique
    private float kimiko$customSwingProgress;
    @Unique
    private Hand kimiko$outlineHand;
    @Unique
    private float kimiko$mainCx;
    @Unique
    private float kimiko$mainCy;
    @Unique
    private float kimiko$mainCz;
    @Unique
    private float kimiko$offCx;
    @Unique
    private float kimiko$offCy;
    @Unique
    private float kimiko$offCz;
    @Unique
    private boolean kimiko$mainCenterSet;
    @Unique
    private boolean kimiko$offCenterSet;
    @Unique
    private static final float kimiko$EAT_DRIFT_X = 0.2f;
    @Unique
    private static final float kimiko$EAT_DRIFT_Y = -0.05f;

    @WrapOperation(method={"renderFirstPersonItem"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/item/HeldItemRenderer;applyEquipOffset(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/Arm;F)V")}, require=0)
    private void kimiko$baseSwingAnimation(HeldItemRenderer instance, MatrixStack matrices, Arm arm, float equipProgress, Operation<Void> original, @Local(argsOnly=true) AbstractClientPlayerEntity player, @Local(argsOnly=true) Hand hand, @Local(argsOnly=true, ordinal=2) float swingProgress) {
        ViewModel.apply(matrices, hand);
        if (player.isUsingItem() && player.getActiveHand() == hand) {
            float eq = ViewModel.suppressEatAnimation() ? 0.0f : equipProgress;
            original.call(new Object[]{instance, matrices, arm, Float.valueOf(eq)});
            ViewModel.applyScale(matrices, hand);
            this.kimiko$applyInPlaceEat(matrices, hand, arm, player);
            return;
        }
        if (SwingAnimation.applyAnimation(matrices, hand, swingProgress)) {
            ViewModel.applyScale(matrices, hand);
            this.kimiko$markCustomSwing(matrices, hand, swingProgress);
            return;
        }
        original.call(new Object[]{instance, matrices, arm, Float.valueOf(equipProgress)});
        ViewModel.applyScale(matrices, hand);
    }

    @WrapOperation(method={"renderFirstPersonItem"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/item/HeldItemRenderer;applyEatOrDrinkTransformation(Lnet/minecraft/client/util/math/MatrixStack;FLnet/minecraft/util/Arm;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;)V")}, require=0)
    private void kimiko$suppressEatTransform(HeldItemRenderer instance, MatrixStack poseStack, float tickDelta, Arm arm, ItemStack stack, PlayerEntity player, Operation<Void> original) {
        if (ViewModel.suppressEatAnimation()) {
            return;
        }
        original.call(new Object[]{instance, poseStack, Float.valueOf(tickDelta), arm, stack, player});
    }

    @Unique
    private void kimiko$applyInPlaceEat(MatrixStack matrices, Hand hand, Arm arm, AbstractClientPlayerEntity player) {
        float cz;
        float cy;
        float cx;
        boolean main;
        if (!ViewModel.suppressEatAnimation()) {
            return;
        }
        ItemStack stack = player.getStackInHand(hand);
        UseAction anim = stack.getUseAction();
        if (anim != UseAction.EAT && anim != UseAction.DRINK) {
            return;
        }
        boolean bl = main = hand == Hand.MAIN_HAND;
        if (main && this.kimiko$mainCenterSet) {
            cx = this.kimiko$mainCx;
            cy = this.kimiko$mainCy;
            cz = this.kimiko$mainCz;
        } else if (!main && this.kimiko$offCenterSet) {
            cx = this.kimiko$offCx;
            cy = this.kimiko$offCy;
            cz = this.kimiko$offCz;
        } else {
            int s = arm == Arm.RIGHT ? 1 : -1;
            cx = 0.070625f * (float)s;
            cy = 0.2f;
            cz = 0.070625f;
        }
        float ft = MinecraftClient.getInstance().getRenderTickCounter().getTickProgress(false);
        float g = (float)player.getItemUseTimeLeft() - ft + 1.0f;
        float h = g / (float)stack.getMaxUseTime((LivingEntity)player);
        if (h < 0.8f) {
            float bob = MathHelper.abs((float)(MathHelper.cos((double)(g / 4.0f * (float)Math.PI)) * 0.1f));
            matrices.translate(0.0f, bob, 0.0f);
        }
        float i = 1.0f - (float)Math.pow(h, 27.0);
        int j = arm == Arm.RIGHT ? 1 : -1;
        matrices.translate((float)(-j) * i * 0.2f, i * -0.05f, 0.0f);
        matrices.translate(cx, cy, cz);
        matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)j * i * 90.0f));
        matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(i * 10.0f));
        matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees((float)j * i * 30.0f));
        matrices.translate(-cx, -cy, -cz);
    }

    @WrapOperation(method={"renderFirstPersonItem"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/item/HeldItemRenderer;swingArm(FLnet/minecraft/client/util/math/MatrixStack;ILnet/minecraft/util/Arm;)V")}, require=0)
    private void kimiko$swingAnimation(HeldItemRenderer instance, float swingProgress, MatrixStack matrices, int armX, Arm arm, Operation<Void> original, @Local(argsOnly=true) AbstractClientPlayerEntity player, @Local(argsOnly=true) Hand hand) {
        if (player.isUsingItem() && player.getActiveHand() == hand) {
            original.call(new Object[]{instance, Float.valueOf(swingProgress), matrices, armX, arm});
            return;
        }
        if (this.kimiko$consumeCustomSwing(matrices, hand, swingProgress)) {
            return;
        }
        if (!SwingAnimation.applyAnimation(matrices, hand, swingProgress)) {
            original.call(new Object[]{instance, Float.valueOf(swingProgress), matrices, armX, arm});
        }
    }

    @Inject(method={"renderFirstPersonItem"}, at={@At(value="TAIL")}, require=0)
    private void kimiko$clearSwingAnimation(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack stack, float equipProgress, MatrixStack matrices, OrderedRenderCommandQueue nodeCollector, int light, CallbackInfo ci) {
        this.kimiko$clearCustomSwing();
        this.kimiko$outlineHand = null;
    }

    @Inject(method={"renderFirstPersonItem"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$captureOutlineHand(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack stack, float equipProgress, MatrixStack matrices, OrderedRenderCommandQueue nodeCollector, int light, CallbackInfo ci) {
        this.kimiko$outlineHand = hand;
    }

    @Inject(method={"renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/network/ClientPlayerEntity;I)V"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$captureShaderHandsScene(float tickProgress, MatrixStack matrices, OrderedRenderCommandQueue collector, ClientPlayerEntity player, int light, CallbackInfo ci) {
        ViewModel.beginHandFrame();
        if (ShaderHands.isCaptureModeActive() || ViewModel.wantsHandMask()) {
            ShaderHandsRenderer.captureScene(ShaderHands.wantsSceneBlur());
        }
    }

    @WrapOperation(method={"renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/network/ClientPlayerEntity;I)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/command/RenderDispatcher;render()V")}, require=0)
    private void kimiko$beginShaderHandsCapture(RenderDispatcher instance, Operation<Void> original) {
        if ((ShaderHands.isCaptureModeActive() || ViewModel.wantsHandMask()) && ShaderHandsRenderer.beginHandCapture()) {
            try {
                original.call(new Object[]{instance});
            }
            catch (Throwable t) {
                ShaderHandsRenderer.endHandCapture();
                throw t;
            }
        } else {
            original.call(new Object[]{instance});
        }
    }

    @WrapOperation(method={"renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/network/ClientPlayerEntity;I)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;draw()V")}, require=0)
    private void kimiko$captureShaderHands(VertexConsumerProvider.Immediate instance, Operation<Void> original) {
        original.call(new Object[]{instance});
        if (ShaderHandsRenderer.isCapturing()) {
            ShaderHandsRenderer.endHandCapture();
        }
    }

    @Inject(method={"renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/network/ClientPlayerEntity;I)V"}, at={@At(value="TAIL")}, require=0)
    private void kimiko$drawItemOutline(float tickProgress, MatrixStack matrices, OrderedRenderCommandQueue collector, ClientPlayerEntity player, int light, CallbackInfo ci) {
        if (ShaderHands.isCaptureModeActive()) {
            ShaderHands.composite();
        } else if (ViewModel.wantsHandMask()) {
            ShaderHandsRenderer.compositePlain();
        }
        if (ViewModel.wantsHandMask() || ShaderHands.isCaptureModeActive()) {
            ShaderHandsRenderer.updateHandMask();
        }
        ItemOutlineRenderer.run();
    }

    @WrapOperation(method={"renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;I)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/item/ItemRenderState;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;III)V")}, require=0)
    private void kimiko$stampOutline(ItemRenderState state, MatrixStack pose, OrderedRenderCommandQueue collector, int light, int overlay, int outlineColor, Operation<Void> original) {
        ClientPlayerEntity player;
        int color = outlineColor;
        if (this.kimiko$outlineHand != null && ViewModel.outlineAlpha(this.kimiko$outlineHand) > 0.001f) {
            color = ItemOutlineRenderer.outlineColor();
        }
        if (this.kimiko$outlineHand != null && ViewModel.suppressEatAnimation()) {
            Vec3d c = state.getModelBoundingBox().getCenter();
            if (this.kimiko$outlineHand == Hand.MAIN_HAND) {
                this.kimiko$mainCx = (float)c.x;
                this.kimiko$mainCy = (float)c.y;
                this.kimiko$mainCz = (float)c.z;
                this.kimiko$mainCenterSet = true;
            } else {
                this.kimiko$offCx = (float)c.x;
                this.kimiko$offCy = (float)c.y;
                this.kimiko$offCz = (float)c.z;
                this.kimiko$offCenterSet = true;
            }
        }
        if (ShaderHands.isNewModeActive() && this.kimiko$outlineHand != null && (player = MinecraftClient.getInstance().player) != null) {
            HandsItemHitboxTracker.capture(ItemInHandRendererMixin.kimiko$handDisplayContext(player, this.kimiko$outlineHand), pose, state);
        }
        original.call(new Object[]{state, pose, collector, light, overlay, color});
    }

    @Unique
    private static ItemDisplayContext kimiko$handDisplayContext(ClientPlayerEntity player, Hand hand) {
        boolean right = hand == Hand.MAIN_HAND == (player.getMainArm() == Arm.RIGHT);
        return right ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : ItemDisplayContext.FIRST_PERSON_LEFT_HAND;
    }

    @Unique
    private void kimiko$markCustomSwing(MatrixStack matrices, Hand hand, float swingProgress) {
        this.kimiko$customSwingMatrices = matrices;
        this.kimiko$customSwingHand = hand;
        this.kimiko$customSwingProgress = swingProgress;
    }

    @Unique
    private boolean kimiko$consumeCustomSwing(MatrixStack matrices, Hand hand, float swingProgress) {
        boolean matches;
        boolean bl = matches = this.kimiko$customSwingMatrices == matrices && this.kimiko$customSwingHand == hand && Float.compare(this.kimiko$customSwingProgress, swingProgress) == 0;
        if (matches) {
            this.kimiko$clearCustomSwing();
        }
        return matches;
    }

    @Unique
    private void kimiko$clearCustomSwing() {
        this.kimiko$customSwingMatrices = null;
        this.kimiko$customSwingHand = null;
        this.kimiko$customSwingProgress = 0.0f;
    }
}

