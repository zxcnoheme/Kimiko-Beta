/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.entity.state.EntityRenderState
 *  net.minecraft.client.render.entity.state.LivingEntityRenderState
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.client.render.state.CameraRenderState
 *  net.minecraft.client.render.RenderLayers
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.block.Blocks
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.OverlayTexture
 *  net.minecraft.client.render.entity.EntityRendererFactory.Context
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  software.bernie.geckolib.animation.state.BoneSnapshot
 *  software.bernie.geckolib.model.GeoModel
 *  software.bernie.geckolib.renderer.GeoEntityRenderer
 *  software.bernie.geckolib.renderer.base.BoneSnapshots
 *  software.bernie.geckolib.renderer.base.GeoRenderState
 *  software.bernie.geckolib.renderer.base.GeoRenderer
 *  software.bernie.geckolib.renderer.base.RenderPassInfo
 *  software.bernie.geckolib.renderer.layer.GeoRenderLayer
 */
package rtx.kimiko.api.modules.impl.Visuals.custompet.render;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.entity.EntityRendererFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Visuals.custompet.CustomPetVariant;
import rtx.kimiko.api.modules.impl.Visuals.custompet.entity.CustomPetEntity;
import rtx.kimiko.api.modules.impl.Visuals.custompet.model.CustomPetModel;
import rtx.kimiko.api.modules.impl.Visuals.custompet.render.CustomPetRenderState;
import rtx.kimiko.api.modules.impl.Visuals.custompet.render.NightmareBbRenderer;
import rtx.kimiko.api.modules.impl.Visuals.custompet.render.UfoGlowLayer;
import software.bernie.geckolib.animation.state.BoneSnapshot;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.BoneSnapshots;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.base.GeoRenderer;
import software.bernie.geckolib.renderer.base.RenderPassInfo;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 0*\f\b\u0000\u0010\u0003*\u00020\u0001*\u00020\u00022\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u00000\u0004:\u00010B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ1\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u001a\u001a\u00020\u00152\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u001c\u0010\u001dJ!\u0010\u001e\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0016\u00a2\u0006\u0004\b\u001e\u0010\u001fJ/\u0010&\u001a\u00020\u00152\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010!\u001a\u00020 2\u0006\u0010#\u001a\u00020\"2\u0006\u0010%\u001a\u00020$H\u0016\u00a2\u0006\u0004\b&\u0010'J-\u0010*\u001a\u00020\u00152\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\u0006\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b*\u0010+J%\u0010.\u001a\u00020\u00152\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\u0006\u0010-\u001a\u00020,H\u0016\u00a2\u0006\u0004\b.\u0010/\u00a8\u00061"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/CustomPetRenderer;", "Lnet/minecraft/LivingEntityRenderState;", "Lsoftware/bernie/geckolib/renderer/base/GeoRenderState;", "R", "Lsoftware/bernie/geckolib/renderer/GeoEntityRenderer;", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;", "Lnet/minecraft/EntityRendererFactory$Context;", "context", "<init>", "(Lnet/minecraft/EntityRendererFactory$Context;)V", "renderState", "Lnet/minecraft/Identifier;", "texture", "Lnet/minecraft/RenderLayer;", "getRenderType", "(Lnet/minecraft/LivingEntityRenderState;Lnet/minecraft/Identifier;)Lnet/minecraft/RenderLayer;", "animatable", "Ljava/lang/Void;", "relatedObject", "", "partialTick", "", "addRenderData", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;Ljava/lang/Void;Lnet/minecraft/LivingEntityRenderState;F)V", "Lsoftware/bernie/geckolib/renderer/base/RenderPassInfo;", "renderPassInfo", "adjustRenderPose", "(Lsoftware/bernie/geckolib/renderer/base/RenderPassInfo;)V", "getMotionAnimThreshold", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;)F", "createRenderState", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;Ljava/lang/Void;)Lnet/minecraft/LivingEntityRenderState;", "Lnet/minecraft/MatrixStack;", "poseStack", "Lnet/minecraft/OrderedRenderCommandQueue;", "submitNodeCollector", "Lnet/minecraft/CameraRenderState;", "cameraRenderState", "submit", "(Lnet/minecraft/LivingEntityRenderState;Lnet/minecraft/MatrixStack;Lnet/minecraft/OrderedRenderCommandQueue;Lnet/minecraft/CameraRenderState;)V", "widthScale", "heightScale", "scaleModelForRender", "(Lsoftware/bernie/geckolib/renderer/base/RenderPassInfo;FF)V", "Lsoftware/bernie/geckolib/renderer/base/BoneSnapshots;", "snapshots", "adjustModelBonesForRender", "(Lsoftware/bernie/geckolib/renderer/base/RenderPassInfo;Lsoftware/bernie/geckolib/renderer/base/BoneSnapshots;)V", "Companion", "rtx.kimiko:kimiko"})
public class CustomPetRenderer<R extends LivingEntityRenderState & GeoRenderState>
extends GeoEntityRenderer<CustomPetEntity, R> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final float LILY_PAD_SCALE = 1.35f;
    private static final float LILY_PAD_Y_OFFSET = -0.008f;
    private static final float OWL_SCALE = 0.55f;
    private static final float UFO_SCALE = 0.8f;
    private static final float UFO_BEAM_ORIGIN = 0.596875f;
    private static final float UFO_BEAM_POOL_DROP = 0.7675f;
    private static final float UFO_LANDED_GAP = 0.390625f;
    private static final float UFO_BEAM_MIN_STRETCH = 0.5f;
    private static final float UFO_BEAM_MAX_STRETCH = 7.0f;
    @NotNull
    private static final String[] UFO_MOTE_BONES;
    @NotNull
    private static final String[] OWL_EFFECT_BONES;
    @NotNull
    private static final String[] OWL_GROUND_ROPE_BONES;
    @NotNull
    private static final String[] OWL_FLIGHT_ROPE_BONES;
    @NotNull
    private static final String[] CONDITIONAL_BONES;

    public CustomPetRenderer(@NotNull EntityRendererFactory.Context context) {
        super(context, (GeoModel)new CustomPetModel());
        this.shadowRadius = 0.35f;
        this.withRenderLayer((GeoRenderLayer)new UfoGlowLayer((GeoRenderer)this));
    }

    @NotNull
    public RenderLayer getRenderType(@NotNull R renderState, @NotNull Identifier texture) {
        Intrinsics.checkNotNullParameter(renderState, (String)"renderState");
        Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
        if (Intrinsics.areEqual((Object)((GeoRenderState)renderState).getOrDefaultGeckolibData(CustomPetModel.UFO, false), true)) {
            RenderLayer renderLayer2 = RenderLayers.entityTranslucent((Identifier)texture);
            Intrinsics.checkNotNullExpressionValue((Object)renderLayer2, (String)"entityTranslucent(...)");
            return renderLayer2;
        }
        RenderLayer renderLayer3 = RenderLayers.entityCutout((Identifier)texture);
        Intrinsics.checkNotNullExpressionValue((Object)renderLayer3, (String)"entityCutout(...)");
        return renderLayer3;
    }

    public void addRenderData(@NotNull CustomPetEntity animatable, @Nullable Void relatedObject, @NotNull R renderState, float partialTick) {
        Intrinsics.checkNotNullParameter((Object)((Object)animatable), (String)"animatable");
        Intrinsics.checkNotNullParameter(renderState, (String)"renderState");
        if (!animatable.isUfo()) {
            return;
        }
        float level = animatable.getUfoBeamLevel(partialTick);
        double drop = animatable.getUfoGroundDrop(partialTick);
        float stretch = (float)Math.clamp(((double)0.47750002f + drop) / (double)0.614f, 0.5, 7.0);
        ((GeoRenderState)renderState).addGeckolibData(CustomPetModel.UFO_BEAM_LEVEL, Float.valueOf(level));
        ((GeoRenderState)renderState).addGeckolibData(CustomPetModel.UFO_BEAM_STRETCH, Float.valueOf(stretch));
        ((GeoRenderState)renderState).addGeckolibData(CustomPetModel.UFO_BEAM_COLOR, CustomPetRenderer.Companion.beamColor(animatable));
        ((GeoRenderState)renderState).addGeckolibData(CustomPetModel.UFO_LAND_BLEND, Float.valueOf(animatable.getUfoLandBlend(partialTick)));
    }

    public void adjustRenderPose(@NotNull RenderPassInfo<R> renderPassInfo) {
        float landBlend;
        Intrinsics.checkNotNullParameter(renderPassInfo, (String)"renderPassInfo");
        super.adjustRenderPose(renderPassInfo);
        Float f = (Float)renderPassInfo.getOrDefaultGeckolibData(CustomPetModel.UFO_LAND_BLEND, Float.valueOf(0.0f));
        float f2 = landBlend = f != null ? f.floatValue() : 0.0f;
        if (landBlend > 0.001f) {
            renderPassInfo.poseStack().translate(0.0f, -0.390625f * landBlend, 0.0f);
        }
    }

    public float getMotionAnimThreshold(@NotNull CustomPetEntity animatable) {
        Intrinsics.checkNotNullParameter((Object)((Object)animatable), (String)"animatable");
        return 5.0E-4f;
    }

    @NotNull
    public R createRenderState(@NotNull CustomPetEntity animatable, @Nullable Void relatedObject) {
        Intrinsics.checkNotNullParameter((Object)((Object)animatable), (String)"animatable");
        return (R)((Object)new CustomPetRenderState());
    }

    @Override
    public void render(@NotNull R renderState, @NotNull MatrixStack poseStack, @NotNull OrderedRenderCommandQueue submitNodeCollector, @NotNull CameraRenderState cameraRenderState) {
        Intrinsics.checkNotNullParameter(renderState, (String)"renderState");
        Intrinsics.checkNotNullParameter((Object)poseStack, (String)"poseStack");
        Intrinsics.checkNotNullParameter((Object)submitNodeCollector, (String)"submitNodeCollector");
        Intrinsics.checkNotNullParameter((Object)cameraRenderState, (String)"cameraRenderState");
        super.render(renderState, poseStack, submitNodeCollector, cameraRenderState);
        if (Intrinsics.areEqual((Object)((GeoRenderState)renderState).getOrDefaultGeckolibData(CustomPetModel.NIGHTMARE_BB, false), true)) {
            NightmareBbRenderer.render((NightmareBbRenderer.Pose)((GeoRenderState)renderState).getGeckolibData(CustomPetModel.NIGHTMARE_BB_POSE), ((LivingEntityRenderState)renderState).bodyYaw, poseStack, submitNodeCollector, ((LivingEntityRenderState)renderState).light);
            return;
        }
        if (!Intrinsics.areEqual((Object)((GeoRenderState)renderState).getOrDefaultGeckolibData(CustomPetModel.AIRBORNE, false), true)) {
            return;
        }
        if (Intrinsics.areEqual((Object)((GeoRenderState)renderState).getOrDefaultGeckolibData(CustomPetModel.OWL, false), true) || Intrinsics.areEqual((Object)((GeoRenderState)renderState).getOrDefaultGeckolibData(CustomPetModel.CHEKUSHKA, false), true) || Intrinsics.areEqual((Object)((GeoRenderState)renderState).getOrDefaultGeckolibData(CustomPetModel.GOAT, false), true) || Intrinsics.areEqual((Object)((GeoRenderState)renderState).getOrDefaultGeckolibData(CustomPetModel.UFO, false), true)) {
            return;
        }
        String variantName = (String)((GeoRenderState)renderState).getOrDefaultGeckolibData(CustomPetModel.VARIANT, "NITWIT");
        CustomPetVariant variant = CustomPetVariant.Companion.fromSerializedName(variantName);
        if (variant.isRobot()) {
            return;
        }
        poseStack.push();
        poseStack.translate(-0.675f, -0.008f, -0.675f);
        poseStack.scale(1.35f, 1.0f, 1.35f);
        submitNodeCollector.submitBlock(poseStack, Blocks.LILY_PAD.getDefaultState(), ((LivingEntityRenderState)renderState).light, OverlayTexture.DEFAULT_UV, ((LivingEntityRenderState)renderState).outlineColor);
        poseStack.pop();
    }

    public void scaleModelForRender(@NotNull RenderPassInfo<R> renderPassInfo, float widthScale, float heightScale) {
        Intrinsics.checkNotNullParameter(renderPassInfo, (String)"renderPassInfo");
        float wScale = widthScale;
        float hScale = heightScale;
        if (Intrinsics.areEqual((Object)renderPassInfo.getOrDefaultGeckolibData(CustomPetModel.OWL, false), true)) {
            wScale *= 0.55f;
            hScale *= 0.55f;
        }
        if (Intrinsics.areEqual((Object)renderPassInfo.getOrDefaultGeckolibData(CustomPetModel.UFO, false), true)) {
            wScale *= 0.8f;
            hScale *= 0.8f;
        }
        super.scaleModelForRender(renderPassInfo, wScale, hScale);
    }

    public void adjustModelBonesForRender(@NotNull RenderPassInfo<R> renderPassInfo, @NotNull BoneSnapshots snapshots) {
        Intrinsics.checkNotNullParameter(renderPassInfo, (String)"renderPassInfo");
        Intrinsics.checkNotNullParameter((Object)snapshots, (String)"snapshots");
        boolean airborne = Intrinsics.areEqual((Object)renderPassInfo.getOrDefaultGeckolibData(CustomPetModel.AIRBORNE, false), true);
        if (Intrinsics.areEqual((Object)renderPassInfo.getOrDefaultGeckolibData(CustomPetModel.NIGHTMARE_BB, false), true)) {
            return;
        }
        if (Intrinsics.areEqual((Object)renderPassInfo.getOrDefaultGeckolibData(CustomPetModel.UFO, false), true)) {
            CustomPetRenderer.Companion.adjustUfoBones(renderPassInfo, snapshots);
            return;
        }
        if (Intrinsics.areEqual((Object)renderPassInfo.getOrDefaultGeckolibData(CustomPetModel.CHEKUSHKA, false), true) || Intrinsics.areEqual((Object)renderPassInfo.getOrDefaultGeckolibData(CustomPetModel.GOAT, false), true)) {
            return;
        }
        if (Intrinsics.areEqual((Object)renderPassInfo.getOrDefaultGeckolibData(CustomPetModel.OWL, false), true)) {
            CustomPetRenderer.Companion.adjustOwlBones(snapshots, airborne);
            return;
        }
        String variantName = (String)renderPassInfo.getOrDefaultGeckolibData(CustomPetModel.VARIANT, "NITWIT");
        CustomPetVariant variant = CustomPetVariant.Companion.fromSerializedName(variantName);
        boolean inRain = Intrinsics.areEqual((Object)renderPassInfo.getOrDefaultGeckolibData(CustomPetModel.UMBRELLA, false), true);
        for (String bone : CONDITIONAL_BONES) {
            boolean hidden = !CustomPetRenderer.Companion.isBoneVisible(bone, variant, inRain, airborne);
            snapshots.ifPresent(bone, arg_0 -> CustomPetRenderer.adjustModelBonesForRender$lambda$0(hidden, arg_0));
        }
    }

    private static final void adjustModelBonesForRender$lambda$0(boolean $hidden, BoneSnapshot snapshot) {
        Intrinsics.checkNotNullParameter((Object)snapshot, (String)"snapshot");
        snapshot.skipRender($hidden).skipChildrenRender($hidden);
    }

    static {
        String[] stringArray = new String[]{"mote0", "mote1", "mote2", "mote3", "mote4"};
        UFO_MOTE_BONES = stringArray;
        stringArray = new String[]{"trick_star", "stumble_star_left", "stumble_star_right", "celebrate_confetti_1", "celebrate_confetti_2", "celebrate_confetti_3", "signature"};
        OWL_EFFECT_BONES = stringArray;
        stringArray = new String[]{"rope_spin", "left_handle", "right_handle"};
        OWL_GROUND_ROPE_BONES = stringArray;
        stringArray = new String[]{"fly_rope_left", "fly_rope_right"};
        OWL_FLIGHT_ROPE_BONES = stringArray;
        stringArray = new String[]{"body_default", "body_merchant", "leaf", "gardener_hat", "watering_can", "sourcerer_hat", "accessories", "fishing_rod", "fishing_rod_2", "fishing_rod_3", "guitar", "flute", "bongo", "bass", "umbrella", "umbrella2", "umbrella3", "fisherman_umbrella", "fisherman_umbrella2", "fisherman_umbrella3", "pride"};
        CONDITIONAL_BONES = stringArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ#\u0010\u000e\u001a\u00020\r2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0012\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J/\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001dR\u0014\u0010 \u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u001dR\u0014\u0010!\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010\u001dR\u0014\u0010\"\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010\u001dR\u0014\u0010#\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010\u001dR\u0014\u0010$\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010\u001dR\u0014\u0010%\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010\u001dR\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00140&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u001a\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00140&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b)\u0010(R\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00140&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b*\u0010(R\u001a\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00140&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b+\u0010(R\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00140&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010(\u00a8\u0006-"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/CustomPetRenderer.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;", "animatable", "", "beamColor", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;)I", "Lsoftware/bernie/geckolib/renderer/base/RenderPassInfo;", "renderPassInfo", "Lsoftware/bernie/geckolib/renderer/base/BoneSnapshots;", "snapshots", "", "adjustUfoBones", "(Lsoftware/bernie/geckolib/renderer/base/RenderPassInfo;Lsoftware/bernie/geckolib/renderer/base/BoneSnapshots;)V", "", "airborne", "adjustOwlBones", "(Lsoftware/bernie/geckolib/renderer/base/BoneSnapshots;Z)V", "", "bone", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;", "variant", "inRain", "isBoneVisible", "(Ljava/lang/String;Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;ZZ)Z", "", "LILY_PAD_SCALE", "F", "LILY_PAD_Y_OFFSET", "OWL_SCALE", "UFO_SCALE", "UFO_BEAM_ORIGIN", "UFO_BEAM_POOL_DROP", "UFO_LANDED_GAP", "UFO_BEAM_MIN_STRETCH", "UFO_BEAM_MAX_STRETCH", "", "UFO_MOTE_BONES", "[Ljava/lang/String;", "OWL_EFFECT_BONES", "OWL_GROUND_ROPE_BONES", "OWL_FLIGHT_ROPE_BONES", "CONDITIONAL_BONES", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final int beamColor(CustomPetEntity animatable) {
            return switch (WhenMappings.$EnumSwitchMapping$0[animatable.getUfoBeamMode().ordinal()]) {
                case 1 -> 8847284;
                case 2 -> 14086911;
                default -> 5236991;
            };
        }

        private final void adjustUfoBones(RenderPassInfo<?> renderPassInfo, BoneSnapshots snapshots) {
            float stretch;
            Float f = (Float)renderPassInfo.getOrDefaultGeckolibData(CustomPetModel.UFO_BEAM_STRETCH, Float.valueOf(1.0f));
            float f2 = stretch = f != null ? f.floatValue() : 1.0f;
            if (stretch <= 0.0f || Math.abs(stretch - 1.0f) < 0.01f) {
                return;
            }
            snapshots.ifPresent("beam", arg_0 -> Companion.adjustUfoBones$lambda$0(stretch, arg_0));
            snapshots.ifPresent("motes", arg_0 -> Companion.adjustUfoBones$lambda$1(stretch, arg_0));
            float inverse = 1.0f / stretch;
            for (String bone : UFO_MOTE_BONES) {
                snapshots.ifPresent(bone, arg_0 -> Companion.adjustUfoBones$lambda$2(inverse, arg_0));
            }
        }

        private final void adjustOwlBones(BoneSnapshots snapshots, boolean airborne) {
            for (String bone : OWL_EFFECT_BONES) {
                snapshots.ifPresent(bone, s -> Companion.adjustOwlBones$lambda$0(s));
            }
            for (String bone : OWL_GROUND_ROPE_BONES) {
                snapshots.ifPresent(bone, arg_0 -> Companion.adjustOwlBones$lambda$1(airborne, arg_0));
            }
            for (String bone : OWL_FLIGHT_ROPE_BONES) {
                snapshots.ifPresent(bone, arg_0 -> Companion.adjustOwlBones$lambda$2(airborne, arg_0));
            }
        }

        private final boolean isBoneVisible(String bone, CustomPetVariant variant, boolean inRain, boolean airborne) {
            return switch (bone) {
                case "body_merchant" -> variant.usesMerchantBody();
                case "body_default" -> {
                    if (!variant.usesMerchantBody()) {
                        yield true;
                    }
                    yield false;
                }
                case "leaf" -> {
                    if (variant.usesMerchantLeaf() && !inRain) {
                        yield true;
                    }
                    yield false;
                }
                case "watering_can", "gardener_hat" -> variant.usesGardenerGear();
                case "sourcerer_hat" -> variant.usesSorcererHat();
                case "fishing_rod", "accessories", "fishing_rod_3", "fishing_rod_2" -> variant.usesFishermanGear();
                case "umbrella2" -> {
                    if (airborne && !variant.usesFishermanGear()) {
                        yield true;
                    }
                    yield false;
                }
                case "fisherman_umbrella2" -> {
                    if (airborne && variant.usesFishermanGear()) {
                        yield true;
                    }
                    yield false;
                }
                case "umbrella3" -> {
                    if (!airborne && inRain && !variant.usesFishermanGear()) {
                        yield true;
                    }
                    yield false;
                }
                case "fisherman_umbrella3" -> {
                    if (!airborne && inRain && variant.usesFishermanGear()) {
                        yield true;
                    }
                    yield false;
                }
                default -> false;
            };
        }

        private static final void adjustUfoBones$lambda$0(float $stretch, BoneSnapshot snapshot) {
            Intrinsics.checkNotNullParameter((Object)snapshot, (String)"snapshot");
            snapshot.setScaleY(snapshot.getScaleY() * $stretch);
        }

        private static final void adjustUfoBones$lambda$1(float $stretch, BoneSnapshot snapshot) {
            Intrinsics.checkNotNullParameter((Object)snapshot, (String)"snapshot");
            snapshot.setScaleY(snapshot.getScaleY() * $stretch);
        }

        private static final void adjustUfoBones$lambda$2(float $inverse, BoneSnapshot snapshot) {
            Intrinsics.checkNotNullParameter((Object)snapshot, (String)"snapshot");
            snapshot.setScaleY(snapshot.getScaleY() * $inverse);
        }

        private static final void adjustOwlBones$lambda$0(BoneSnapshot snapshot) {
            Intrinsics.checkNotNullParameter((Object)snapshot, (String)"snapshot");
            snapshot.skipRender(true).skipChildrenRender(true);
        }

        private static final void adjustOwlBones$lambda$1(boolean $airborne, BoneSnapshot snapshot) {
            Intrinsics.checkNotNullParameter((Object)snapshot, (String)"snapshot");
            snapshot.skipRender($airborne).skipChildrenRender($airborne);
        }

        private static final void adjustOwlBones$lambda$2(boolean $airborne, BoneSnapshot snapshot) {
            Intrinsics.checkNotNullParameter((Object)snapshot, (String)"snapshot");
            snapshot.skipRender(!$airborne).skipChildrenRender(!$airborne);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @Metadata(mv={2, 4, 0}, k=3, xi=48)
        public static final class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] nArray = new int[CustomPetEntity.UfoBeamMode.values().length];
                try {
                    nArray[CustomPetEntity.UfoBeamMode.ABDUCT.ordinal()] = 1;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[CustomPetEntity.UfoBeamMode.SCAN.ordinal()] = 2;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                $EnumSwitchMapping$0 = nArray;
            }
        }
    }
}

