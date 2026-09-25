/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import mods.freelook.CameraOverriddenEntity;
import mods.freelook.FreeLookState;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Utils.CameraSettings;
import rtx.kimiko.api.modules.impl.Visuals.WastedDeath;
import rtx.kimiko.utils.render.modules.post.wasted.WastedState;
import rtx.kimiko.utils.render.others.ScreenShake;

@Mixin(value={Camera.class})
public abstract class CameraMixin {
    @Shadow
    private Entity focusedEntity;
    @Unique
    private final float[] kimiko$shakeScratch = new float[2];
    @Unique
    private boolean kimiko$freelookFirstTime = true;

    @Shadow
    protected abstract void setRotation(float var1, float var2);

    @Shadow
    protected abstract void setPos(double var1, double var3, double var5);

    @Inject(method={"update"}, at={@At(value="TAIL")})
    private void kimiko$wastedDetachedPosition(World level, Entity entity, boolean detached, boolean mirrored, float partialTick, CallbackInfo ci) {
        WastedDeath module = WastedDeath.getInstance();
        if (!(module != null && WastedDeath.isRunning() && module.orbitEnabled() && WastedState.isDetached())) {
            return;
        }
        Vec3d position = module.orbitPosition();
        this.setPos(position.x, position.y, position.z);
    }

    @Inject(method={"update"}, at={@At(value="TAIL")})
    private void kimiko$applyShake(World level, Entity entity, boolean detached, boolean mirrored, float partialTick, CallbackInfo ci) {
        if (!ScreenShake.sample(this.kimiko$shakeScratch)) {
            return;
        }
        Camera self = (Camera)(Object)this;
        this.setRotation(self.getYaw() + this.kimiko$shakeScratch[0], self.getPitch() + this.kimiko$shakeScratch[1]);
    }

    @Inject(method={"update"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/Camera;setRotation(FF)V", ordinal=1, shift=At.Shift.AFTER)}, require=0)
    private void kimiko$wastedAndFreelookRotation(CallbackInfo ci) {
        WastedDeath module = WastedDeath.getInstance();
        if (module != null && WastedDeath.isRunning() && module.orbitEnabled()) {
            this.setRotation(module.orbitYaw(), module.orbitPitch());
            return;
        }
        if (FreeLookState.active && this.focusedEntity instanceof ClientPlayerEntity) {
            CameraOverriddenEntity cameraOverriddenEntity = (CameraOverriddenEntity)this.focusedEntity;
            if (this.kimiko$freelookFirstTime && MinecraftClient.getInstance().player != null) {
                cameraOverriddenEntity.freelook$setCameraPitch(MinecraftClient.getInstance().player.getPitch());
                cameraOverriddenEntity.freelook$setCameraYaw(MinecraftClient.getInstance().player.getYaw());
                this.kimiko$freelookFirstTime = false;
            }
            this.setRotation(cameraOverriddenEntity.freelook$getCameraYaw(), cameraOverriddenEntity.freelook$getCameraPitch());
        }
        if (!FreeLookState.active && this.focusedEntity instanceof ClientPlayerEntity) {
            this.kimiko$freelookFirstTime = true;
        }
    }

    @ModifyArg(method={"update"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/Camera;clipToSpace(F)F"), index=0, require=0)
    private float kimiko$modifyZoom(float requestedDistance) {
        WastedDeath module = WastedDeath.getInstance();
        if (module != null && WastedDeath.isRunning() && module.orbitEnabled()) {
            return module.orbitDistanceNow();
        }
        float scale = CameraSettings.getCameraDistanceScale();
        return scale >= 1.0f ? requestedDistance : requestedDistance * scale;
    }
}

