/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.entity.state.ItemEntityRenderState
 *  net.minecraft.client.render.entity.state.ItemStackEntityRenderState
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.client.render.state.CameraRenderState
 *  net.minecraft.entity.ItemEntity
 *  net.minecraft.util.math.Box
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.util.math.random.Random
 *  net.minecraft.util.math.RotationAxis
 *  net.minecraft.client.render.entity.ItemEntityRenderer
 *  org.joml.Quaternionfc
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import java.util.HashMap;
import java.util.WeakHashMap;
import net.minecraft.client.render.entity.state.ItemEntityRenderState;
import net.minecraft.client.render.entity.state.ItemStackEntityRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import org.joml.Quaternionfc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Visuals.ItemPhysics;

@Mixin(value={ItemEntityRenderer.class})
public abstract class ItemEntityRendererMixin {
    @Unique
    private static final WeakHashMap<ItemEntityRenderState, Boolean> kimiko$groundStateMap = new WeakHashMap();
    @Unique
    private static final HashMap<Integer, Integer> kimiko$groundHoldMap = new HashMap();
    @Unique
    private ItemEntityRenderState kimiko$currentState;

    @Inject(method={"updateRenderState"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$captureGroundState(ItemEntity entity, ItemEntityRenderState state, float tickDelta, CallbackInfo ci) {
        kimiko$groundStateMap.put(state, ItemEntityRendererMixin.kimiko$resolveGroundState(entity, entity.isOnGround()));
    }

    @Redirect(method={"render(Lnet/minecraft/client/render/entity/state/ItemEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/render/state/CameraRenderState;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V", ordinal=0), require=0)
    private void kimiko$translate(MatrixStack matrices, float x, float y, float z, ItemEntityRenderState state, MatrixStack matricesArg, OrderedRenderCommandQueue submitNodeCollector, CameraRenderState cameraRenderState) {
        this.kimiko$currentState = state;
        boolean enabled = ItemEntityRendererMixin.kimiko$isItemPhysicsEnabled();
        boolean stableGround = kimiko$groundStateMap.getOrDefault(state, false);
        if ((enabled || stableGround) && state.itemRenderState != null) {
            Box box = state.itemRenderState.getModelBoundingBox();
            matrices.translate(x, -((float)box.minY) + 0.0625f, z);
        } else {
            matrices.translate(x, y, z);
        }
    }

    @Redirect(method={"render(Lnet/minecraft/client/render/entity/state/ItemEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/render/state/CameraRenderState;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/entity/ItemEntityRenderer;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;ILnet/minecraft/client/render/entity/state/ItemStackEntityRenderState;Lnet/minecraft/util/math/random/Random;Lnet/minecraft/util/math/Box;)V"), require=0)
    private void kimiko$renderPhysics(MatrixStack matrices, OrderedRenderCommandQueue submitNodeCollector, int light, ItemStackEntityRenderState stackState, Random random, Box box) {
        if (ItemEntityRendererMixin.kimiko$isItemPhysicsEnabled() && this.kimiko$currentState != null) {
            boolean scaled;
            float age = this.kimiko$currentState.age;
            float offset = this.kimiko$currentState.uniqueOffset;
            boolean onGround = kimiko$groundStateMap.getOrDefault(this.kimiko$currentState, false);
            float scale = ItemEntityRendererMixin.kimiko$groundItemScale();
            boolean bl = scaled = Math.abs(scale - 1.0f) > 0.001f;
            if (scaled) {
                matrices.translate(0.0f, 0.0625f, 0.0f);
                matrices.scale(scale, scale, scale);
                matrices.translate(0.0f, -0.0625f, 0.0f);
            }
            matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotation(-ItemEntity.getRotation((float)age, (float)offset)));
            if (onGround) {
                matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(90.0f));
                matrices.translate(0.0f, -((float)box.getLengthY() / 2.0f) + 0.0625f, 0.0f);
            } else {
                matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees((age * 15.0f + offset * 360.0f) % 360.0f));
            }
        }
        ItemEntityRenderer.render((MatrixStack)matrices, (OrderedRenderCommandQueue)submitNodeCollector, (int)light, (ItemStackEntityRenderState)stackState, (Random)random, (Box)box);
    }

    @Unique
    private static boolean kimiko$isItemPhysicsEnabled() {
        ItemPhysics itemPhysics = ItemPhysics.getInstance();
        return itemPhysics != null && itemPhysics.isEnabled() && itemPhysics.isNormalMode();
    }

    @Unique
    private static float kimiko$groundItemScale() {
        ItemPhysics itemPhysics = ItemPhysics.getInstance();
        return itemPhysics != null ? itemPhysics.groundItemScale() : 1.0f;
    }

    @Unique
    private static boolean kimiko$resolveGroundState(ItemEntity entity, boolean onGround) {
        boolean lowMotion;
        int entityId = entity.getId();
        if (onGround) {
            kimiko$groundHoldMap.put(entityId, 8);
            ItemEntityRendererMixin.kimiko$trimGroundMap();
            return true;
        }
        Integer holdTicks = kimiko$groundHoldMap.get(entityId);
        if (holdTicks == null || holdTicks <= 0) {
            return false;
        }
        Vec3d velocity = entity.getVelocity();
        boolean bl = lowMotion = Math.abs(velocity.x) <= 0.08 && Math.abs(velocity.y) <= 0.08 && Math.abs(velocity.z) <= 0.08;
        if (!lowMotion) {
            kimiko$groundHoldMap.remove(entityId);
            return false;
        }
        if (holdTicks == 1) {
            kimiko$groundHoldMap.remove(entityId);
        } else {
            kimiko$groundHoldMap.put(entityId, holdTicks - 1);
        }
        return true;
    }

    @Unique
    private static void kimiko$trimGroundMap() {
        if (kimiko$groundHoldMap.size() > 4096) {
            kimiko$groundHoldMap.clear();
        }
    }
}

