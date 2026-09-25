/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.model.ModelTransform
 *  net.minecraft.client.render.entity.model.BipedEntityModel
 *  net.minecraft.client.model.ModelPart
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals.emotions;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.model.ModelPart;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b)\u0018\u0000 82\u00020\u0001:\u00018B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u0015\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ)\u0010\u000e\u001a\u00020\u00042\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0011\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0012R\u001b\u0010\u0015\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0012R\u001b\u0010\u0016\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0012R\u001b\u0010\u0017\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0012R\u001b\u0010\u0018\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0012R\u001b\u0010\u0019\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0012R\u001b\u0010\u001a\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0012R\u001b\u0010\u001b\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0012R\u001b\u0010\u001c\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0012R\u001b\u0010\u001d\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u0012R\u001b\u0010\u001e\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u0012R\u001b\u0010\u001f\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u0012R\u001b\u0010 \u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b \u0010\u0012R\u001b\u0010!\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b!\u0010\u0012R\u001b\u0010\"\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\"\u0010\u0012R\u001b\u0010#\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b#\u0010\u0012R\u001b\u0010$\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b$\u0010\u0012R\u001b\u0010%\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b%\u0010\u0012R\u001b\u0010&\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b&\u0010\u0012R\u001b\u0010'\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b'\u0010\u0012R\u001b\u0010(\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b(\u0010\u0012R\u001b\u0010)\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b)\u0010\u0012R\u001b\u0010*\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b*\u0010\u0012R\u001b\u0010+\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b+\u0010\u0012R\u001b\u0010,\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b,\u0010\u0012R\u001b\u0010-\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b-\u0010\u0012R\u001b\u0010.\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b.\u0010\u0012R\u001b\u0010/\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b/\u0010\u0012R\u001b\u00100\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b0\u0010\u0012R\u001b\u00101\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b1\u0010\u0012R\u001b\u00102\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b2\u0010\u0012R\u001b\u00103\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b3\u0010\u0012R\u001b\u00104\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b4\u0010\u0012R\u001b\u00105\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b5\u0010\u0012R\u001b\u00106\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b6\u0010\u0012R\u001b\u00107\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b7\u0010\u0012\u00a8\u00069"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionPose;", "", "<init>", "()V", "", "reset", "", "angle", "lean", "(F)V", "Lnet/minecraft/BipedEntityModel;", "model", "weight", "walkAmount", "applyTo", "(Lnet/minecraft/BipedEntityModel;FF)V", "Lkotlin/jvm/JvmField;", "headRotX", "F", "headRotY", "headRotZ", "bodyRotX", "bodyRotY", "bodyRotZ", "rightArmRotX", "rightArmRotY", "rightArmRotZ", "leftArmRotX", "leftArmRotY", "leftArmRotZ", "rightLegRotX", "rightLegRotY", "rightLegRotZ", "leftLegRotX", "leftLegRotY", "leftLegRotZ", "headOffX", "headOffY", "headOffZ", "bodyOffX", "bodyOffY", "bodyOffZ", "rightArmOffX", "rightArmOffY", "rightArmOffZ", "leftArmOffX", "leftArmOffY", "leftArmOffZ", "rightLegOffX", "rightLegOffY", "rightLegOffZ", "leftLegOffX", "leftLegOffY", "leftLegOffZ", "upperOffY", "upperOffZ", "Companion", "rtx.kimiko:kimiko"})
public final class EmotionPose {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    public float headRotX;
    @JvmField
    public float headRotY;
    @JvmField
    public float headRotZ;
    @JvmField
    public float bodyRotX;
    @JvmField
    public float bodyRotY;
    @JvmField
    public float bodyRotZ;
    @JvmField
    public float rightArmRotX;
    @JvmField
    public float rightArmRotY;
    @JvmField
    public float rightArmRotZ;
    @JvmField
    public float leftArmRotX;
    @JvmField
    public float leftArmRotY;
    @JvmField
    public float leftArmRotZ;
    @JvmField
    public float rightLegRotX;
    @JvmField
    public float rightLegRotY;
    @JvmField
    public float rightLegRotZ;
    @JvmField
    public float leftLegRotX;
    @JvmField
    public float leftLegRotY;
    @JvmField
    public float leftLegRotZ;
    @JvmField
    public float headOffX;
    @JvmField
    public float headOffY;
    @JvmField
    public float headOffZ;
    @JvmField
    public float bodyOffX;
    @JvmField
    public float bodyOffY;
    @JvmField
    public float bodyOffZ;
    @JvmField
    public float rightArmOffX;
    @JvmField
    public float rightArmOffY;
    @JvmField
    public float rightArmOffZ;
    @JvmField
    public float leftArmOffX;
    @JvmField
    public float leftArmOffY;
    @JvmField
    public float leftArmOffZ;
    @JvmField
    public float rightLegOffX;
    @JvmField
    public float rightLegOffY;
    @JvmField
    public float rightLegOffZ;
    @JvmField
    public float leftLegOffX;
    @JvmField
    public float leftLegOffY;
    @JvmField
    public float leftLegOffZ;
    @JvmField
    public float upperOffY;
    @JvmField
    public float upperOffZ;
    private static final float HIP_PIVOT = 12.0f;
    private static final float SHOULDER_HEIGHT = 2.0f;

    public final void reset() {
        this.headRotX = 0.0f;
        this.headRotY = 0.0f;
        this.headRotZ = 0.0f;
        this.bodyRotX = 0.0f;
        this.bodyRotY = 0.0f;
        this.bodyRotZ = 0.0f;
        this.rightArmRotX = 0.0f;
        this.rightArmRotY = 0.0f;
        this.rightArmRotZ = 0.0f;
        this.leftArmRotX = 0.0f;
        this.leftArmRotY = 0.0f;
        this.leftArmRotZ = 0.0f;
        this.rightLegRotX = 0.0f;
        this.rightLegRotY = 0.0f;
        this.rightLegRotZ = 0.0f;
        this.leftLegRotX = 0.0f;
        this.leftLegRotY = 0.0f;
        this.leftLegRotZ = 0.0f;
        this.headOffX = 0.0f;
        this.headOffY = 0.0f;
        this.headOffZ = 0.0f;
        this.bodyOffX = 0.0f;
        this.bodyOffY = 0.0f;
        this.bodyOffZ = 0.0f;
        this.rightArmOffX = 0.0f;
        this.rightArmOffY = 0.0f;
        this.rightArmOffZ = 0.0f;
        this.leftArmOffX = 0.0f;
        this.leftArmOffY = 0.0f;
        this.leftArmOffZ = 0.0f;
        this.rightLegOffX = 0.0f;
        this.rightLegOffY = 0.0f;
        this.rightLegOffZ = 0.0f;
        this.leftLegOffX = 0.0f;
        this.leftLegOffY = 0.0f;
        this.leftLegOffZ = 0.0f;
        this.upperOffY = 0.0f;
        this.upperOffZ = 0.0f;
    }

    public final void lean(float angle) {
        this.bodyRotX += angle;
        this.headRotX += angle;
        this.rightArmRotX += angle;
        this.leftArmRotX += angle;
        float cos = (float)Math.cos(angle);
        float sin = (float)Math.sin(angle);
        float torsoY = 12.0f - 12.0f * cos;
        float torsoZ = -12.0f * sin;
        this.headOffY += torsoY;
        this.headOffZ += torsoZ;
        this.bodyOffY += torsoY;
        this.bodyOffZ += torsoZ;
        float armArm = -10.0f;
        float armY = 12.0f + armArm * cos - 2.0f;
        float armZ = armArm * sin;
        this.rightArmOffY += armY;
        this.rightArmOffZ += armZ;
        this.leftArmOffY += armY;
        this.leftArmOffZ += armZ;
    }

    public final void applyTo(@NotNull BipedEntityModel<?> model, float weight, float walkAmount) {
        Intrinsics.checkNotNullParameter(model, (String)"model");
        if (weight <= 0.0f) {
            return;
        }
        float w = Math.min(weight, 1.0f);
        float legW = w * (1.0f - RangesKt.coerceIn((float)walkAmount, (float)0.0f, (float)1.0f));
        ModelPart modelPart2 = model.head;
        Intrinsics.checkNotNullExpressionValue((Object)modelPart2, (String)"head");
        EmotionPose.Companion.applyAdditive(modelPart2, this.headRotX, this.headRotY, this.headRotZ, this.headOffX, this.headOffY + this.upperOffY, this.headOffZ + this.upperOffZ, w);
        ModelPart modelPart3 = model.body;
        Intrinsics.checkNotNullExpressionValue((Object)modelPart3, (String)"body");
        EmotionPose.Companion.apply(modelPart3, this.bodyRotX, this.bodyRotY, this.bodyRotZ, this.bodyOffX, this.bodyOffY + this.upperOffY, this.bodyOffZ + this.upperOffZ, w);
        ModelPart modelPart4 = model.rightArm;
        Intrinsics.checkNotNullExpressionValue((Object)modelPart4, (String)"rightArm");
        EmotionPose.Companion.apply(modelPart4, this.rightArmRotX, this.rightArmRotY, this.rightArmRotZ, this.rightArmOffX, this.rightArmOffY + this.upperOffY, this.rightArmOffZ + this.upperOffZ, w);
        ModelPart modelPart5 = model.leftArm;
        Intrinsics.checkNotNullExpressionValue((Object)modelPart5, (String)"leftArm");
        EmotionPose.Companion.apply(modelPart5, this.leftArmRotX, this.leftArmRotY, this.leftArmRotZ, this.leftArmOffX, this.leftArmOffY + this.upperOffY, this.leftArmOffZ + this.upperOffZ, w);
        ModelPart modelPart6 = model.rightLeg;
        Intrinsics.checkNotNullExpressionValue((Object)modelPart6, (String)"rightLeg");
        EmotionPose.Companion.apply(modelPart6, this.rightLegRotX, this.rightLegRotY, this.rightLegRotZ, this.rightLegOffX, this.rightLegOffY, this.rightLegOffZ, legW);
        ModelPart modelPart7 = model.leftLeg;
        Intrinsics.checkNotNullExpressionValue((Object)modelPart7, (String)"leftLeg");
        EmotionPose.Companion.apply(modelPart7, this.leftLegRotX, this.leftLegRotY, this.leftLegRotZ, this.leftLegOffX, this.leftLegOffY, this.leftLegOffZ, legW);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003JO\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010JO\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0013\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionPose.Companion;", "", "<init>", "()V", "Lnet/minecraft/ModelPart;", "part", "", "rotX", "rotY", "rotZ", "offX", "offY", "offZ", "weight", "", "applyAdditive", "(Lnet/minecraft/ModelPart;FFFFFFF)V", "apply", "HIP_PIVOT", "F", "SHOULDER_HEIGHT", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final void applyAdditive(ModelPart part, float rotX, float rotY, float rotZ, float offX, float offY, float offZ, float weight) {
            ModelTransform modelTransform2 = part.getDefaultTransform();
            Intrinsics.checkNotNullExpressionValue((Object)modelTransform2, (String)"getInitialPose(...)");
            ModelTransform base = modelTransform2;
            part.pitch += rotX * weight;
            part.yaw += rotY * weight;
            part.roll += rotZ * weight;
            part.originX = MathHelper.lerp((float)weight, (float)part.originX, (float)(base.x() + offX));
            part.originY = MathHelper.lerp((float)weight, (float)part.originY, (float)(base.y() + offY));
            part.originZ = MathHelper.lerp((float)weight, (float)part.originZ, (float)(base.z() + offZ));
        }

        private final void apply(ModelPart part, float rotX, float rotY, float rotZ, float offX, float offY, float offZ, float weight) {
            ModelTransform modelTransform2 = part.getDefaultTransform();
            Intrinsics.checkNotNullExpressionValue((Object)modelTransform2, (String)"getInitialPose(...)");
            ModelTransform base = modelTransform2;
            part.pitch = MathHelper.lerp((float)weight, (float)part.pitch, (float)rotX);
            part.yaw = MathHelper.lerp((float)weight, (float)part.yaw, (float)rotY);
            part.roll = MathHelper.lerp((float)weight, (float)part.roll, (float)rotZ);
            part.originX = MathHelper.lerp((float)weight, (float)part.originX, (float)(base.x() + offX));
            part.originY = MathHelper.lerp((float)weight, (float)part.originY, (float)(base.y() + offY));
            part.originZ = MathHelper.lerp((float)weight, (float)part.originZ, (float)(base.z() + offZ));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

