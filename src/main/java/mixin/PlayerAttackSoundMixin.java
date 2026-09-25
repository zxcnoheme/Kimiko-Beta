/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.sound.SoundEvent
 *  net.minecraft.sound.SoundEvents
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Visuals.FakePlayer;

@Mixin(value={PlayerEntity.class})
public abstract class PlayerAttackSoundMixin {
    @Unique
    private boolean kimiko$attackingFakePlayer;
    @Unique
    private boolean kimiko$criticalFakePlayerAttack;
    @Unique
    private boolean kimiko$vanillaCriticalSound;

    @Inject(method={"attack"}, at={@At(value="HEAD")})
    private void kimiko$beginFakePlayerAttack(Entity target, CallbackInfo ci) {
        this.kimiko$attackingFakePlayer = FakePlayer.isFakePlayer(target);
        PlayerEntity player = (PlayerEntity)(Object)this;
        boolean airborne = !player.isOnGround() || player.fallDistance > 0.0 || player.getVelocity().y > 0.05;
        this.kimiko$criticalFakePlayerAttack = this.kimiko$attackingFakePlayer && airborne && player.getAttackCooldownProgress(0.5f) > 0.9f && !player.isClimbing() && !player.isTouchingWater() && !player.hasBlindnessEffect() && !player.hasVehicle() && !player.isSprinting();
        this.kimiko$vanillaCriticalSound = false;
    }

    @Inject(method={"attack"}, at={@At(value="RETURN")})
    private void kimiko$endFakePlayerAttack(Entity target, CallbackInfo ci) {
        if (this.kimiko$criticalFakePlayerAttack && !this.kimiko$vanillaCriticalSound) {
            ((PlayerEntity)(Object)this).addCritParticles(target);
        }
        this.kimiko$attackingFakePlayer = false;
        this.kimiko$criticalFakePlayerAttack = false;
    }

    @Inject(method={"playAttackSound"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$playFakePlayerAttackSound(SoundEvent sound, CallbackInfo ci) {
        if (!this.kimiko$attackingFakePlayer) {
            return;
        }
        PlayerEntity player = (PlayerEntity)(Object)this;
        this.kimiko$vanillaCriticalSound = sound == SoundEvents.ENTITY_PLAYER_ATTACK_CRIT;
        SoundEvent localSound = this.kimiko$criticalFakePlayerAttack ? SoundEvents.ENTITY_PLAYER_ATTACK_CRIT : sound;
        player.getEntityWorld().playSoundClient(player.getX(), player.getY(), player.getZ(), localSound, player.getSoundCategory(), 1.0f, 1.0f, false);
        ci.cancel();
    }

    @Inject(method={"knockbackTarget"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$cancelFakePlayerAttackPhysics(Entity target, float knockback, Vec3d movement, CallbackInfo ci) {
        if (this.kimiko$attackingFakePlayer) {
            ci.cancel();
        }
    }
}

