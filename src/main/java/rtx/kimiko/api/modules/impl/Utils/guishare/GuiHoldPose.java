/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.PlayerLikeEntity
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.MathHelper
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils.guishare;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.PlayerLikeEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareController;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u001cB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ)\u0010\u0010\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0016R\u0014\u0010\u001a\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0016R\u0014\u0010\u001b\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0016\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiHoldPose;", "", "<init>", "()V", "Lnet/minecraft/PlayerLikeEntity;", "entity", "", "tickDelta", "", "Lkotlin/jvm/JvmStatic;", "compute", "(Lnet/minecraft/PlayerLikeEntity;F)[F", "Lnet/minecraft/Vec3d;", "shoulder", "grip", "bodyYawRad", "armAngles", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;F)[F", "value", "wrapRadians", "(F)F", "GRIP_HALF_WIDTH", "F", "GRIP_DROP", "SHOULDER_SIDE", "SHOULDER_HEIGHT", "MAX_ARM_PITCH", "MAX_ARM_YAW", "HoldTarget", "rtx.kimiko:kimiko"})
public final class GuiHoldPose {
    @NotNull
    public static final GuiHoldPose INSTANCE = new GuiHoldPose();
    private static final float GRIP_HALF_WIDTH = 0.62f;
    private static final float GRIP_DROP = 0.55f;
    private static final float SHOULDER_SIDE = 0.32f;
    private static final float SHOULDER_HEIGHT = 1.35f;
    private static final float MAX_ARM_PITCH = 1.35f;
    private static final float MAX_ARM_YAW = 1.25f;

    private GuiHoldPose() {
    }

    @JvmStatic
    @Nullable
    public static final float[] compute(@Nullable PlayerLikeEntity entity, float tickDelta) {
        if (entity == null) {
            return null;
        }
        HoldTarget target = GuiShareController.holdTargetFor(entity);
        if (target == null || target.weight < 0.02f || target.anchor == null) {
            return null;
        }
        Vec3d vec3d2 = entity.getLerpedPos(tickDelta);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getPosition(...)");
        Vec3d position = vec3d2;
        float bodyYawDeg = MathHelper.lerpAngleDegrees((float)tickDelta, (float)entity.lastBodyYaw, (float)entity.bodyYaw);
        float bodyYawRad = (float)Math.toRadians(bodyYawDeg);
        Vec3d vec3d3 = Vec3d.fromPolar((float)target.pitch, (float)target.yaw);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"directionFromRotation(...)");
        Vec3d look = vec3d3;
        Vec3d panelRight = new Vec3d(-look.z, 0.0, look.x);
        if (panelRight.lengthSquared() < 1.0E-6) {
            panelRight = new Vec3d(-Math.cos(bodyYawRad), 0.0, -Math.sin(bodyYawRad));
        }
        Vec3d vec3d4 = panelRight.normalize();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"normalize(...)");
        panelRight = vec3d4;
        Vec3d vec3d5 = panelRight.crossProduct(look).normalize();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d5, (String)"normalize(...)");
        Vec3d panelUp = vec3d5;
        float grip = 0.62f * target.animScale;
        float drop = 0.55f * target.animScale;
        Vec3d vec3d6 = target.anchor.add(panelRight.multiply((double)grip)).subtract(panelUp.multiply((double)drop));
        Intrinsics.checkNotNullExpressionValue((Object)vec3d6, (String)"subtract(...)");
        Vec3d rightGrip = vec3d6;
        Vec3d vec3d7 = target.anchor.subtract(panelRight.multiply((double)grip)).subtract(panelUp.multiply((double)drop));
        Intrinsics.checkNotNullExpressionValue((Object)vec3d7, (String)"subtract(...)");
        Vec3d leftGrip = vec3d7;
        Vec3d bodyRight = new Vec3d(-Math.cos(bodyYawRad), 0.0, -Math.sin(bodyYawRad));
        Vec3d shoulderLift = new Vec3d(0.0, (double)1.35f, 0.0);
        Vec3d vec3d8 = position.add(bodyRight.multiply((double)0.32f)).add(shoulderLift);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d8, (String)"add(...)");
        Vec3d rightShoulder = vec3d8;
        Vec3d vec3d9 = position.subtract(bodyRight.multiply((double)0.32f)).add(shoulderLift);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d9, (String)"add(...)");
        Vec3d leftShoulder = vec3d9;
        float[] rightAngles = INSTANCE.armAngles(rightShoulder, rightGrip, bodyYawRad);
        float[] leftAngles = INSTANCE.armAngles(leftShoulder, leftGrip, bodyYawRad);
        if (rightAngles == null || leftAngles == null) {
            return null;
        }
        float[] fArray = new float[]{MathHelper.clamp((float)target.weight, (float)0.0f, (float)1.0f), rightAngles[0], rightAngles[1], leftAngles[0], leftAngles[1]};
        return fArray;
    }

    private final float[] armAngles(Vec3d shoulder, Vec3d grip, float bodyYawRad) {
        Vec3d vec3d2 = grip.subtract(shoulder);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"subtract(...)");
        Vec3d direction = vec3d2;
        if (direction.lengthSquared() < 1.0E-6) {
            return null;
        }
        Vec3d vec3d3 = direction.normalize();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"normalize(...)");
        direction = vec3d3;
        float pitchRad = (float)(-Math.asin(MathHelper.clamp((double)direction.y, (double)-1.0, (double)1.0)));
        float yawRad = (float)Math.atan2(-direction.x, direction.z);
        float relYaw = this.wrapRadians(yawRad - bodyYawRad);
        float armX = -1.5707964f + MathHelper.clamp((float)pitchRad, (float)-1.35f, (float)1.35f);
        float armY = MathHelper.clamp((float)relYaw, (float)-1.25f, (float)1.25f);
        float[] fArray = new float[]{armX, armY};
        return fArray;
    }

    private final float wrapRadians(float value) {
        float wrapped = value % ((float)Math.PI * 2);
        if (wrapped >= (float)Math.PI) {
            wrapped -= (float)Math.PI * 2;
        }
        if (wrapped < (float)(-Math.PI)) {
            wrapped += (float)Math.PI * 2;
        }
        return wrapped;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u000eJB\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0017\u001a\u00020\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u001a\u001a\u00020\u0019H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001d\u001a\u00020\u001cH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001d\u0010\u001eR\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001f\u00a2\u0006\u0006\n\u0004\b\u0003\u0010 R\u0019\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001f\u00a2\u0006\u0006\n\u0004\b\u0005\u0010!R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001f\u00a2\u0006\u0006\n\u0004\b\u0006\u0010!R\u0019\u0010\u0007\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001f\u00a2\u0006\u0006\n\u0004\b\u0007\u0010!R\u0019\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001f\u00a2\u0006\u0006\n\u0004\b\b\u0010!\u00a8\u0006\""}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiHoldPose$HoldTarget;", "Ljava/lang/Record;", "Lnet/minecraft/Vec3d;", "anchor", "", "yaw", "pitch", "animScale", "weight", "<init>", "(Lnet/minecraft/Vec3d;FFFF)V", "component1", "()Lnet/minecraft/Vec3d;", "component2", "()F", "component3", "component4", "component5", "copy", "(Lnet/minecraft/Vec3d;FFFF)Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiHoldPose$HoldTarget;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmField;", "Lnet/minecraft/Vec3d;", "F", "rtx.kimiko:kimiko"})
    public static final class HoldTarget
    {
        @JvmField
        @NotNull
        public final Vec3d anchor;
        @JvmField
        public final float yaw;
        @JvmField
        public final float pitch;
        @JvmField
        public final float animScale;
        @JvmField
        public final float weight;

        public HoldTarget(@NotNull Vec3d anchor, float yaw, float pitch, float animScale, float weight) {
            Intrinsics.checkNotNullParameter((Object)anchor, (String)"anchor");
            this.anchor = anchor;
            this.yaw = yaw;
            this.pitch = pitch;
            this.animScale = animScale;
            this.weight = weight;
        }

        @NotNull
        public final Vec3d component1() {
            return this.anchor;
        }

        public final float component2() {
            return this.yaw;
        }

        public final float component3() {
            return this.pitch;
        }

        public final float component4() {
            return this.animScale;
        }

        public final float component5() {
            return this.weight;
        }

        @NotNull
        public final HoldTarget copy(@NotNull Vec3d anchor, float yaw, float pitch, float animScale, float weight) {
            Intrinsics.checkNotNullParameter((Object)anchor, (String)"anchor");
            return new HoldTarget(anchor, yaw, pitch, animScale, weight);
        }

        public static /* synthetic */ HoldTarget copy$default(HoldTarget holdTarget, Vec3d vec3d2, float f, float f2, float f3, float f4, int n, Object object) {
            if ((n & 1) != 0) {
                vec3d2 = holdTarget.anchor;
            }
            if ((n & 2) != 0) {
                f = holdTarget.yaw;
            }
            if ((n & 4) != 0) {
                f2 = holdTarget.pitch;
            }
            if ((n & 8) != 0) {
                f3 = holdTarget.animScale;
            }
            if ((n & 0x10) != 0) {
                f4 = holdTarget.weight;
            }
            return holdTarget.copy(vec3d2, f, f2, f3, f4);
        }

        @Override
        @NotNull
        public String toString() {
            return "HoldTarget(anchor=" + this.anchor + ", yaw=" + this.yaw + ", pitch=" + this.pitch + ", animScale=" + this.animScale + ", weight=" + this.weight + ")";
        }

        @Override
        public int hashCode() {
            int result = this.anchor.hashCode();
            result = result * 31 + Float.hashCode(this.yaw);
            result = result * 31 + Float.hashCode(this.pitch);
            result = result * 31 + Float.hashCode(this.animScale);
            result = result * 31 + Float.hashCode(this.weight);
            return result;
        }

        @Override
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HoldTarget)) {
                return false;
            }
            HoldTarget holdTarget = (HoldTarget)other;
            if (!Intrinsics.areEqual((Object)this.anchor, (Object)holdTarget.anchor)) {
                return false;
            }
            if (Float.compare(this.yaw, holdTarget.yaw) != 0) {
                return false;
            }
            if (Float.compare(this.pitch, holdTarget.pitch) != 0) {
                return false;
            }
            if (Float.compare(this.animScale, holdTarget.animScale) != 0) {
                return false;
            }
            return Float.compare(this.weight, holdTarget.weight) == 0;
        }
    }
}

