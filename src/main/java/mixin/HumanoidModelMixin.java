/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.entity.state.BipedEntityRenderState
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.render.entity.model.BipedEntityModel
 *  net.minecraft.client.model.ModelPart
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.model.ModelPart;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiHoldPoseState;

@Mixin(value={BipedEntityModel.class})
public abstract class HumanoidModelMixin {
    @Shadow
    @Final
    public ModelPart rightArm;
    @Shadow
    @Final
    public ModelPart leftArm;

    @Inject(method={"setAngles"}, at={@At(value="TAIL")}, require=0)
    private void kimiko$applyGuiHoldPose(BipedEntityRenderState state, CallbackInfo ci) {
        if (!(state instanceof GuiHoldPoseState)) {
            return;
        }
        GuiHoldPoseState holder = (GuiHoldPoseState)state;
        float[] pose = holder.kimiko$getGuiHoldPose();
        if (pose == null || pose.length < 5) {
            return;
        }
        float weight = pose[0];
        if (weight <= 0.0f) {
            return;
        }
        this.rightArm.pitch = MathHelper.lerp((float)weight, (float)this.rightArm.pitch, (float)pose[1]);
        this.rightArm.yaw = MathHelper.lerp((float)weight, (float)this.rightArm.yaw, (float)pose[2]);
        this.rightArm.roll = MathHelper.lerp((float)weight, (float)this.rightArm.roll, (float)0.06f);
        this.leftArm.pitch = MathHelper.lerp((float)weight, (float)this.leftArm.pitch, (float)pose[3]);
        this.leftArm.yaw = MathHelper.lerp((float)weight, (float)this.leftArm.yaw, (float)pose[4]);
        this.leftArm.roll = MathHelper.lerp((float)weight, (float)this.leftArm.roll, (float)-0.06f);
    }
}

