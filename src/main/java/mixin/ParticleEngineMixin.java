/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.Box
 *  net.minecraft.particle.ParticleEffect
 *  net.minecraft.particle.ParticleTypes
 *  net.minecraft.client.particle.ParticleManager
 *  net.minecraft.client.particle.Particle
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package mixin;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.particle.Particle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rtx.kimiko.api.modules.impl.Utils.Optimization;
import rtx.kimiko.api.modules.impl.Visuals.NoRender;

@Mixin(value={ParticleManager.class})
public abstract class ParticleEngineMixin {
    @Inject(method={"addParticle(Lnet/minecraft/client/particle/Particle;)V"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$cullDistantParticles(Particle particle, CallbackInfo ci) {
        if (ParticleEngineMixin.kimiko$noParticles()) {
            ci.cancel();
            return;
        }
        Box box = particle.getBoundingBox();
        double x = (box.minX + box.maxX) * 0.5;
        double y = (box.minY + box.maxY) * 0.5;
        double z = (box.minZ + box.maxZ) * 0.5;
        if (!Optimization.allowParticle(x, y, z)) {
            ci.cancel();
        }
    }

    @Inject(method={"addEmitter(Lnet/minecraft/entity/Entity;Lnet/minecraft/particle/ParticleEffect;)V"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$noTotemEmitter(Entity entity, ParticleEffect options, CallbackInfo ci) {
        if (ParticleEngineMixin.kimiko$noParticles() || ParticleEngineMixin.kimiko$blockTotem(options)) {
            ci.cancel();
        }
    }

    @Inject(method={"addEmitter(Lnet/minecraft/entity/Entity;Lnet/minecraft/particle/ParticleEffect;I)V"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$noTotemEmitterTimed(Entity entity, ParticleEffect options, int lifetime, CallbackInfo ci) {
        if (ParticleEngineMixin.kimiko$noParticles() || ParticleEngineMixin.kimiko$blockTotem(options)) {
            ci.cancel();
        }
    }

    @Inject(method={"addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)Lnet/minecraft/client/particle/Particle;"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$noTotemParticle(ParticleEffect options, double x, double y, double z, double vx, double vy, double vz, CallbackInfoReturnable<Particle> cir) {
        if (ParticleEngineMixin.kimiko$noParticles() || ParticleEngineMixin.kimiko$blockTotem(options)) {
            cir.setReturnValue(null);
        }
    }

    @Inject(method={"addToBatch"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$noParticleExtract(CallbackInfo ci) {
        if (ParticleEngineMixin.kimiko$noParticles()) {
            ci.cancel();
        }
    }

    private static boolean kimiko$noParticles() {
        return NoRender.isActive("Частицы");
    }

    private static boolean kimiko$blockTotem(ParticleEffect options) {
        return options != null && options.getType() == ParticleTypes.TOTEM_OF_UNDYING && NoRender.isActive("Тотем");
    }
}

