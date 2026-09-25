/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.freelook;

import mods.freelook.CameraOverriddenEntity;
import mods.freelook.FreeLookState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Entity.class})
public class FreelookEntityMixin
implements CameraOverriddenEntity {
    @Unique
    private float freelookCameraPitch;
    @Unique
    private float freelookCameraYaw;
    @Unique
    private float freelookAnchorYaw;
    @Unique
    private boolean freelookHasAnchor = false;

    @Inject(method={"changeLookDirection"}, at={@At(value="HEAD")}, cancellable=true)
    public void freelook$changeCameraLookDirection(double xDelta, double yDelta, CallbackInfo ci) {
        if (FreeLookState.active && (Object)this instanceof ClientPlayerEntity) {
            double pitchDelta = yDelta * 0.15;
            double yawDelta = xDelta * 0.15;
            if (!this.freelookHasAnchor) {
                this.freelookAnchorYaw = this.freelookCameraYaw;
                this.freelookHasAnchor = true;
            }
            this.freelookCameraPitch = MathHelper.clamp((float)(this.freelookCameraPitch + (float)pitchDelta), (float)-90.0f, (float)90.0f);
            this.freelookCameraYaw = FreeLookState.maxHeadYaw >= 360.0f ? (this.freelookCameraYaw += (float)yawDelta) : MathHelper.clamp((float)(this.freelookCameraYaw + (float)yawDelta), (float)(this.freelookAnchorYaw - FreeLookState.maxHeadYaw), (float)(this.freelookAnchorYaw + FreeLookState.maxHeadYaw));
            ci.cancel();
        } else if (this.freelookHasAnchor) {
            this.freelookHasAnchor = false;
        }
    }

    @Override
    @Unique
    public float freelook$getCameraPitch() {
        return this.freelookCameraPitch;
    }

    @Override
    @Unique
    public float freelook$getCameraYaw() {
        return this.freelookCameraYaw;
    }

    @Override
    @Unique
    public void freelook$setCameraPitch(float pitch) {
        this.freelookCameraPitch = pitch;
    }

    @Override
    @Unique
    public void freelook$setCameraYaw(float yaw) {
        this.freelookCameraYaw = yaw;
        this.freelookAnchorYaw = yaw;
        this.freelookHasAnchor = true;
    }
}

