/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.impl.player.PlayerVelocityStrafeEvent;
import rtx.kimiko.api.modules.impl.Visuals.FakePlayer;
import rtx.kimiko.api.modules.impl.Visuals.custompet.entity.CustomPetEntity;

@Mixin(value={Entity.class})
public abstract class EntityMixin {
    @Inject(method={"pushAwayFrom"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$cancelCustomPetPush(Entity other, CallbackInfo ci) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null || (Object)this != player && other != player) {
            return;
        }
        if ((Object)this instanceof CustomPetEntity || other instanceof CustomPetEntity || FakePlayer.isFakePlayer((Entity)(Object)this) || FakePlayer.isFakePlayer(other)) {
            ci.cancel();
        }
    }

    @Redirect(method={"updateVelocity"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;movementInputToVelocity(Lnet/minecraft/util/math/Vec3d;FF)Lnet/minecraft/util/math/Vec3d;"), require=0)
    private Vec3d kimiko$fixMoveRelative(Vec3d movementInput, float speed, float yaw) {
        if (this.kimiko$isLocalPlayer()) {
            PlayerVelocityStrafeEvent event = EventBus.get().post(new PlayerVelocityStrafeEvent(EntityMixin.kimiko$computeInputVector(movementInput, speed, yaw), movementInput, speed));
            return event.getVelocity();
        }
        return EntityMixin.kimiko$computeInputVector(movementInput, speed, yaw);
    }

    @Unique
    private boolean kimiko$isLocalPlayer() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        return player != null && (Object)this == player;
    }

    @Unique
    private static Vec3d kimiko$computeInputVector(Vec3d input, float speed, float yaw) {
        double len = input.lengthSquared();
        if (len < 1.0E-7) {
            return Vec3d.ZERO;
        }
        Vec3d scaled = (len > 1.0 ? input.normalize() : input).multiply((double)speed);
        float sin = MathHelper.sin((double)(yaw * ((float)Math.PI / 180)));
        float cos = MathHelper.cos((double)(yaw * ((float)Math.PI / 180)));
        return new Vec3d(scaled.x * (double)cos - scaled.z * (double)sin, scaled.y, scaled.z * (double)cos + scaled.x * (double)sin);
    }
}

