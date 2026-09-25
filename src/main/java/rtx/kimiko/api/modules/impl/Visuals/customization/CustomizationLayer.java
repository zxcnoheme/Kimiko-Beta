/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.entity.state.PlayerEntityRenderState
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.entity.Entity
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.render.entity.model.ModelWithHead
 *  net.minecraft.client.render.entity.feature.FeatureRendererContext
 *  net.minecraft.client.render.entity.feature.FeatureRenderer
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.entity.model.BipedEntityModel
 *  net.minecraft.client.render.entity.model.EntityModel
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  net.minecraft.util.math.RotationAxis
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Quaternionfc
 */
package rtx.kimiko.api.modules.impl.Visuals.customization;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.entity.Entity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionfc;
import rtx.kimiko.api.modules.impl.Visuals.Customization;
import rtx.kimiko.api.modules.impl.Visuals.customization.FlugerHatModel;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\u0012\b\u0001\u0010\u0005*\b\u0012\u0004\u0012\u00028\u00000\u0003*\u00020\u00042\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006B\u001b\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\u00a2\u0006\u0004\b\t\u0010\nJ?\u0010\u0016\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/CustomizationLayer;", "Lnet/minecraft/PlayerEntityRenderState;", "S", "Lnet/minecraft/EntityModel;", "Lnet/minecraft/ModelWithHead;", "M", "Lnet/minecraft/FeatureRenderer;", "Lnet/minecraft/FeatureRendererContext;", "renderer", "<init>", "(Lnet/minecraft/FeatureRendererContext;)V", "Lnet/minecraft/MatrixStack;", "poseStack", "Lnet/minecraft/OrderedRenderCommandQueue;", "collector", "", "lightCoords", "state", "", "yRot", "xRot", "", "submit", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/OrderedRenderCommandQueue;ILnet/minecraft/PlayerEntityRenderState;FF)V", "rtx.kimiko:kimiko"})
public final class CustomizationLayer<S extends PlayerEntityRenderState, M extends EntityModel<S>>
extends FeatureRenderer<S, M> {
    public CustomizationLayer(@NotNull FeatureRendererContext<S, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(@NotNull MatrixStack poseStack, @NotNull OrderedRenderCommandQueue collector, int lightCoords, @NotNull S state, float yRot, float xRot) {
        Intrinsics.checkNotNullParameter((Object)poseStack, (String)"poseStack");
        Intrinsics.checkNotNullParameter((Object)collector, (String)"collector");
        Intrinsics.checkNotNullParameter(state, (String)"state");
        Customization customization = Customization.Companion.getInstance();
        if (customization == null || !customization.isEnabled() || ((PlayerEntityRenderState)state).invisible || ((PlayerEntityRenderState)state).baby) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        ClientWorld clientWorld3 = mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        Entity entity = level.getEntityById(((PlayerEntityRenderState)state).id);
        if (!(entity instanceof AbstractClientPlayerEntity)) {
            return;
        }
        int flags = customization.flagsFor((AbstractClientPlayerEntity)entity);
        if (flags == 0) {
            return;
        }
        boolean crown = (flags & 1) != 0;
        boolean hat = (flags & 2) != 0;
        boolean halo = (flags & 4) != 0;
        boolean wings = (flags & 8) != 0;
        boolean goat = (flags & 0x10) != 0;
        EntityModel entityModel2 = this.getContextModel();
        Intrinsics.checkNotNullExpressionValue((Object)entityModel2, (String)"getParentModel(...)");
        EntityModel parentModel = entityModel2;
        if (crown || hat || halo) {
            poseStack.push();
            parentModel.getRootPart().applyTransform(poseStack);
            ((ModelWithHead)parentModel).applyTransform(poseStack);
            if (hat) {
                FlugerHatModel.render(poseStack, collector, lightCoords);
            }
            if (halo) {
                customization.submitHalo((AbstractClientPlayerEntity)entity, poseStack, collector, lightCoords);
            }
            if (crown) {
                poseStack.push();
                poseStack.multiply((Quaternionfc)RotationAxis.NEGATIVE_Z.rotationDegrees(180.0f));
                poseStack.multiply((Quaternionfc)RotationAxis.NEGATIVE_Y.rotationDegrees(90.0f));
                customization.submitCrown((AbstractClientPlayerEntity)entity, poseStack, collector, lightCoords);
                poseStack.pop();
            }
            poseStack.pop();
        }
        if ((wings || goat) && parentModel instanceof BipedEntityModel) {
            poseStack.push();
            ((BipedEntityModel)parentModel).getRootPart().applyTransform(poseStack);
            ((BipedEntityModel)parentModel).body.applyTransform(poseStack);
            if (wings) {
                customization.submitWings((AbstractClientPlayerEntity)entity, poseStack, collector, lightCoords, ((PlayerEntityRenderState)state).isGliding, ((PlayerEntityRenderState)state).limbSwingAmplitude, ((PlayerEntityRenderState)state).handSwingProgress);
            }
            if (goat) {
                customization.submitGoat((AbstractClientPlayerEntity)entity, poseStack, collector, lightCoords);
            }
            poseStack.pop();
        }
    }
}

