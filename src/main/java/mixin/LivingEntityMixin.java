/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.damage.DamageSource
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.impl.player.JumpEvent;
import rtx.kimiko.api.modules.impl.Visuals.KillEffect;
import rtx.kimiko.api.modules.impl.Visuals.ModelCollapse;
import rtx.kimiko.api.modules.impl.Visuals.SwingAnimation;

@Mixin(value={LivingEntity.class})
public abstract class LivingEntityMixin {
    @Inject(method={"jump"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$jumpEvent(CallbackInfo ci) {
        if (!this.kimiko$isLocalPlayer()) {
            return;
        }
        JumpEvent event = EventBus.get().post(new JumpEvent((PlayerEntity)MinecraftClient.getInstance().player));
        if (event.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"getHandSwingDuration"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$swingDuration(CallbackInfoReturnable<Integer> cir) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null || (Object)this != player) {
            return;
        }
        Integer duration = SwingAnimation.currentSwingDuration();
        if (duration != null) {
            cir.setReturnValue(duration);
        }
    }

    @Inject(method={"onDeath"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$onDeath(DamageSource source, CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object)this;
        KillEffect.notifyEntityDied(self, source);
        ModelCollapse.notifyEntityDied(self);
    }

    @Inject(method={"setHealth"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$onSetHealth(float health, CallbackInfo ci) {
        if (health > 0.0f) {
            return;
        }
        LivingEntity self = (LivingEntity)(Object)this;
        if (self.getHealth() > 0.0f) {
            KillEffect.notifyEntityDied(self, null);
            ModelCollapse.notifyEntityDied(self);
        }
    }

    @Unique
    private boolean kimiko$isLocalPlayer() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        return player != null && (Object)this == player;
    }
}

