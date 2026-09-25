/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.entity.state.LivingEntityRenderState
 *  net.minecraft.client.render.entity.state.PlayerEntityRenderState
 *  net.minecraft.client.render.entity.PlayerEntityRenderer
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.text.StyleSpriteSource
 *  net.minecraft.text.StyleSpriteSource$Font
 *  net.minecraft.client.render.state.CameraRenderState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.text.Text
 *  net.minecraft.text.Style
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.render.entity.model.ModelWithHead
 *  net.minecraft.client.render.entity.feature.FeatureRenderer
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.entity.EntityRendererFactory$Context
 *  net.minecraft.client.render.entity.model.EntityModel
 *  net.minecraft.client.model.ModelPart
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  net.minecraft.util.math.RotationAxis
 *  net.minecraft.entity.EntityAttachmentType
 *  net.minecraft.client.render.entity.LivingEntityRenderer
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Quaternionfc
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Constant
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyConstant
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package mixin;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.text.StyleSpriteSource;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.Style;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.entity.EntityAttachmentType;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionfc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rtx.kimiko.api.modules.impl.Utils.Globals;
import rtx.kimiko.api.modules.impl.Utils.StreamerMode;
import rtx.kimiko.api.modules.impl.Visuals.ChinaHat;
import rtx.kimiko.api.modules.impl.Visuals.HitColor;
import rtx.kimiko.api.modules.impl.Visuals.NameTags;
import rtx.kimiko.api.modules.impl.Visuals.SeeInvisible;
import rtx.kimiko.api.modules.impl.Visuals.SelfTag;
import rtx.kimiko.api.modules.impl.Visuals.customization.CustomizationLayer;
import rtx.kimiko.api.modules.impl.Visuals.seeinvisible.RevealTintHolder;
import rtx.kimiko.utils.net.ClientPresence;

@Mixin(value={LivingEntityRenderer.class})
public abstract class LivingEntityRendererMixin<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> {
    @Shadow
    public abstract M getModel();

    @Shadow
    protected abstract void setupTransforms(S var1, MatrixStack var2, float var3, float var4);

    @Shadow
    protected abstract void scale(S var1, MatrixStack var2);

    @Shadow
    protected abstract boolean addFeature(FeatureRenderer<?, ?> var1);

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void kimiko$addCustomizationLayer(EntityRendererFactory.Context context, EntityModel<?> model, float shadowRadius, CallbackInfo ci) {
        if ((Object)this instanceof PlayerEntityRenderer) {
            PlayerEntityRenderer renderer = (PlayerEntityRenderer)(Object)this;
            this.addFeature(new CustomizationLayer(renderer));
        }
    }

    @Inject(method={"updateRenderState"}, at={@At(value="TAIL")}, require=0)
    private void kimiko$extractState(T entity, S state, float partialTick, CallbackInfo ci) {
        boolean nameHidden;
        SeeInvisible module;
        if (state instanceof RevealTintHolder) {
            RevealTintHolder holder = (RevealTintHolder)state;
            holder.kimiko$setRevealTint(-1);
        }
        if (((LivingEntityRenderState)state).invisible && (module = SeeInvisible.getInstance()) != null && module.shouldReveal((LivingEntity)entity)) {
            ((LivingEntityRenderState)state).invisibleToPlayer = false;
            if (module.isSolid()) {
                ((LivingEntityRenderState)state).invisible = false;
            } else if (state instanceof RevealTintHolder) {
                RevealTintHolder holder = (RevealTintHolder)state;
                holder.kimiko$setRevealTint(module.ghostTint());
            }
        }
        if (nameHidden = NameTags.hidesNameTagFor(entity)) {
            ((LivingEntityRenderState)state).displayName = null;
        }
        if (!nameHidden && SelfTag.active()) {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (entity == mc.player && ((LivingEntityRenderState)state).displayName == null && !mc.options.getPerspective().isFirstPerson()) {
                ((LivingEntityRenderState)state).displayName = entity.getDisplayName();
                ((LivingEntityRenderState)state).nameLabelPos = entity.getAttachments().getPointNullable(EntityAttachmentType.NAME_TAG, 0, entity.getYaw());
            }
        }
        MinecraftClient mcRef = MinecraftClient.getInstance();
        if (((LivingEntityRenderState)state).displayName != null && entity == mcRef.player) {
            ((LivingEntityRenderState)state).displayName = StreamerMode.applySelfRank(((LivingEntityRenderState)state).displayName);
        }
        if (((LivingEntityRenderState)state).displayName != null && entity instanceof PlayerEntity) {
            PlayerEntity badgePlayer = (PlayerEntity)entity;
            if (Globals.tagsBadge() && ClientPresence.INSTANCE.isKimikoUser(badgePlayer.getGameProfile().name())) {
                ((LivingEntityRenderState)state).displayName = Text.empty().append((Text)Text.literal((String)"\ue000").setStyle(Style.EMPTY.withFont((StyleSpriteSource)new StyleSpriteSource.Font(Identifier.of((String)"kimiko", (String)"badge"))).withColor(9081843))).append((Text)Text.literal((String)" ").append(((LivingEntityRenderState)state).displayName)).append((Text)Text.literal((String)"  "));
            }
        }
        HitColor.captureTint(state, entity);
        if (HitColor.shouldTint(entity)) {
            ((LivingEntityRenderState)state).hurt = false;
        }
    }

    @Inject(method={"render"}, at={@At(value="TAIL")}, require=0)
    private void kimiko$captureChinaHatTransform(S state, MatrixStack poseStack, OrderedRenderCommandQueue collector, CameraRenderState camera, CallbackInfo ci) {
        if (!(state instanceof PlayerEntityRenderState)) {
            return;
        }
        PlayerEntityRenderState avatarState = (PlayerEntityRenderState)state;
        ChinaHat chinaHat = ChinaHat.getInstance();
        if (chinaHat == null || !chinaHat.isVisuallyActive()) {
            return;
        }
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world == null) {
            return;
        }
        Entity entity = mc.world.getEntityById(avatarState.id);
        if (!(entity instanceof AbstractClientPlayerEntity)) {
            return;
        }
        AbstractClientPlayerEntity player = (AbstractClientPlayerEntity)entity;
        if (avatarState.baby || ((LivingEntityRenderState)state).invisible || !chinaHat.shouldRender(player)) {
            return;
        }
        M model = this.getModel();
        if (!(model instanceof ModelWithHead)) {
            return;
        }
        ModelWithHead headedModel = (ModelWithHead)model;
        MatrixStack local = new MatrixStack();
        local.scale(((LivingEntityRenderState)state).baseScale, ((LivingEntityRenderState)state).baseScale, ((LivingEntityRenderState)state).baseScale);
        this.setupTransforms(state, local, ((LivingEntityRenderState)state).bodyYaw, ((LivingEntityRenderState)state).baseScale);
        local.scale(-1.0f, -1.0f, 1.0f);
        this.scale(state, local);
        local.translate(0.0f, -1.501f, 0.0f);
        ModelPart head = headedModel.getHead();
        head.applyTransform(local);
        local.multiply((Quaternionfc)RotationAxis.NEGATIVE_Z.rotationDegrees(180.0f));
        local.multiply((Quaternionfc)RotationAxis.NEGATIVE_Y.rotationDegrees(90.0f));
        chinaHat.captureTransform(player.getId(), new Matrix4f((Matrix4fc)local.peek().getPositionMatrix()));
    }

    @ModifyConstant(method={"render"}, constant={@Constant(intValue=0x26FFFFFF)}, require=0)
    private int kimiko$revealTint(int original, S state, MatrixStack poseStack, OrderedRenderCommandQueue collector, CameraRenderState camera) {
        RevealTintHolder holder;
        int tint;
        if (state instanceof RevealTintHolder && (tint = (holder = (RevealTintHolder)state).kimiko$getRevealTint()) != -1) {
            return tint;
        }
        return original;
    }

    @Inject(method={"getMixColor"}, at={@At(value="RETURN")}, cancellable=true, require=0)
    private void kimiko$customHurtTint(S state, CallbackInfoReturnable<Integer> cir) {
        Integer tint = HitColor.tintFor(state);
        if (tint != null) {
            cir.setReturnValue(tint);
        }
    }
}

