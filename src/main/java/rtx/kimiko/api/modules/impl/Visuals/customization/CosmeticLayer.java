package rtx.kimiko.api.modules.impl.Visuals.customization;

import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.modules.impl.Visuals.cosmetics.BundledCosmetics;
import rtx.kimiko.api.modules.impl.Visuals.cosmetics.CosmeticSettings;
import rtx.kimiko.api.modules.impl.Visuals.cosmetics.CosmeticType;
import rtx.kimiko.api.modules.impl.Visuals.cosmetics.ExternalCosmetic;
import rtx.kimiko.api.modules.impl.Visuals.cosmetics.ExternalCosmeticRenderer;

/**
 * РЎР»РѕР№ СЂРµРЅРґРµСЂР° РєРѕСЃРјРµС‚РёРє РЅР° РёРіСЂРѕРєРµ: РіРѕР»РѕРІРЅС‹Рµ СѓР±РѕСЂС‹ (РєРѕСЂРѕРЅР°/С€Р»СЏРїР°/РіР°Р»Рѕ), РєСЂС‹Р»СЊСЏ, РєРѕР·С‘Р» РЅР° РїР»РµС‡Рµ.
 * РСЃС‚РѕС‡РЅРёРє РІС‹Р±РѕСЂР° вЂ” CosmeticSettings (РІРєР»Р°РґРєР° Cosmetic РІ РєР»РёРє-РіСѓРё); СЂРёСЃСѓРµС‚СЃСЏ С‚РѕР»СЊРєРѕ РЅР° Р»РѕРєР°Р»СЊРЅРѕРј РёРіСЂРѕРєРµ.
 */
public final class CosmeticLayer<S extends PlayerEntityRenderState, M extends EntityModel<S>>
extends FeatureRenderer<S, M> {
    public CosmeticLayer(@NotNull FeatureRendererContext<S, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(@NotNull MatrixStack poseStack, @NotNull OrderedRenderCommandQueue collector, int lightCoords, @NotNull S state, float yRot, float xRot) {
        Intrinsics.checkNotNullParameter(poseStack, "poseStack");
        Intrinsics.checkNotNullParameter(collector, "collector");
        Intrinsics.checkNotNullParameter(state, "state");
        if (state.invisible || state.baby) {
            return;
        }
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || mc.world == null || mc.player == null) {
            return;
        }
        ClientWorld level = mc.world;
        Entity entity = level.getEntityById(state.id);
        if (!(entity instanceof AbstractClientPlayerEntity)) {
            return;
        }
        AbstractClientPlayerEntity player = (AbstractClientPlayerEntity)entity;
        if (player != mc.player) {
            return;
        }
        if (mc.options.getPerspective() == Perspective.FIRST_PERSON) {
            return;
        }
        CosmeticSettings settings = CosmeticSettings.get();
        CosmeticType wingsType = settings.selectedType(CosmeticType.Cat.WINGS);
        CosmeticType headType = settings.selectedType(CosmeticType.Cat.HEAD);
        CosmeticType shoulderType = settings.selectedType(CosmeticType.Cat.SHOULDER);
        String wingsSel = settings.wingsSelection();
        String headSel = settings.headSelection();
        String shoulderSel = settings.shoulderSelection();
        String petSel = settings.petSelection();
        String backSel = settings.backSelection();
        String clothingSel = settings.clothingSelection();
        String effectSel = settings.effectSelection();
        ExternalCosmetic extWings = wingsSel.startsWith("ext:") ? BundledCosmetics.byKey(wingsSel.substring(4)) : null;
        ExternalCosmetic extHead = headSel.startsWith("ext:") ? BundledCosmetics.byKey(headSel.substring(4)) : null;
        ExternalCosmetic extShoulder = shoulderSel.startsWith("ext:") ? BundledCosmetics.byKey(shoulderSel.substring(4)) : null;
        ExternalCosmetic extPet = petSel.startsWith("ext:") ? BundledCosmetics.byKey(petSel.substring(4)) : null;
        ExternalCosmetic extBack = backSel.startsWith("ext:") ? BundledCosmetics.byKey(backSel.substring(4)) : null;
        ExternalCosmetic extClothing = clothingSel.startsWith("ext:") ? BundledCosmetics.byKey(clothingSel.substring(4)) : null;
        ExternalCosmetic extEffect = effectSel.startsWith("ext:") ? BundledCosmetics.byKey(effectSel.substring(4)) : null;
        boolean crown = headType == CosmeticType.HEAD_CROWN;
        boolean hat = headType == CosmeticType.HEAD_HAT;
        boolean halo = headType == CosmeticType.HEAD_HALO;
        boolean wings = wingsType != null;
        boolean goat = shoulderType == CosmeticType.SHOULDER_GOAT;
        boolean extBackOn = extBack != null;
        if (!crown && !hat && !halo && !wings && !goat && extHead == null && !extBackOn
                && extWings == null && extShoulder == null && extPet == null
                && extClothing == null && extEffect == null) {
            return;
        }
        float animTime = (float) ((System.currentTimeMillis() % 1000000L) / 1000.0);
        boolean moving = state.limbSwingAmplitude > 0.01f;
        EntityModel<S> parentModel = this.getContextModel();
        Intrinsics.checkNotNullExpressionValue(parentModel, "getParentModel(...)");
        if (crown || hat || halo || extHead != null) {
            poseStack.push();
            parentModel.getRootPart().applyTransform(poseStack);
            ((ModelWithHead)parentModel).applyTransform(poseStack);
            if (hat) {
                FlugerHatModel.render(poseStack, collector, lightCoords);
            }
            if (halo) {
                boolean helmet = !player.getEquippedStack(EquipmentSlot.HEAD).isEmpty();
                GoldenHaloRenderer.render(player, poseStack, collector, lightCoords, helmet);
            }
            if (crown) {
                poseStack.push();
                poseStack.multiply(RotationAxis.NEGATIVE_Z.rotationDegrees(180.0f));
                poseStack.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(90.0f));
                CrownRenderer.submit(poseStack, collector, lightCoords, headYOffset(player));
                poseStack.pop();
            }
            if (extHead != null) {
                ExternalCosmeticRenderer.render(extHead, poseStack, collector, lightCoords, animTime, moving);
            }
            poseStack.pop();
        }
        if ((wings || goat || extBackOn || extWings != null || extShoulder != null
                || extPet != null || extClothing != null || extEffect != null) && parentModel instanceof BipedEntityModel) {
            poseStack.push();
            ((BipedEntityModel)parentModel).getRootPart().applyTransform(poseStack);
            ((BipedEntityModel)parentModel).body.applyTransform(poseStack);
            if (wings) {
                boolean flying = state.isGliding || player.getAbilities().flying;
                switch (wingsType) {
                    case WINGS_KAGUNE -> KanekiKaguneRenderer.render(player, poseStack, collector, lightCoords, flying, state.limbSwingAmplitude, state.handSwingProgress);
                    case WINGS_SERAPH -> SeraphEyeWingsRenderer.render(player, poseStack, collector, lightCoords, flying, state.limbSwingAmplitude, state.handSwingProgress);
                    case WINGS_FEATHERED -> FeatheredWingsRenderer.render(player, poseStack, collector, lightCoords, flying, state.limbSwingAmplitude, state.handSwingProgress);
                    default -> RoyalWingsRenderer.render(poseStack, collector, lightCoords, flying, state.limbSwingAmplitude, player.getVelocity().y);
                }
            }
            if (goat) {
                ShoulderGoatRenderer.render(player, poseStack, collector, lightCoords);
            }
            if (extWings != null) {
                ExternalCosmeticRenderer.render(extWings, poseStack, collector, lightCoords, animTime, moving);
            }
            if (extShoulder != null) {
                ExternalCosmeticRenderer.render(extShoulder, poseStack, collector, lightCoords, animTime, moving);
            }
            if (extBackOn) {
                ExternalCosmeticRenderer.render(extBack, poseStack, collector, lightCoords, animTime, moving);
            }
            if (extClothing != null) {
                ExternalCosmeticRenderer.render(extClothing, poseStack, collector, lightCoords, animTime, moving);
            }
            if (extEffect != null) {
                ExternalCosmeticRenderer.render(extEffect, poseStack, collector, lightCoords, animTime, moving);
            }
            if (extPet != null) {
                ExternalCosmeticRenderer.render(extPet, poseStack, collector, lightCoords, animTime, moving);
            }
            poseStack.pop();
        }
    }

    private static float headYOffset(AbstractClientPlayerEntity player) {
        boolean helmet;
        boolean flying = player.getAbilities().flying;
        boolean swimming = player.isSwimming();
        boolean bl = helmet = !player.getEquippedStack(EquipmentSlot.HEAD).isEmpty();
        if (player.isInSneakingPose() && !flying && !swimming) {
            return helmet ? 0.38f : 0.28f;
        }
        return helmet ? 0.48f : 0.34f;
    }
}
