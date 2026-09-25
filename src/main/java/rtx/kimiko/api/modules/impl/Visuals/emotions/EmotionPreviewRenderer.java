/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.entity.state.EntityRenderState
 *  net.minecraft.client.render.entity.state.BipedEntityRenderState
 *  net.minecraft.client.render.entity.state.LivingEntityRenderState
 *  net.minecraft.client.gui.render.SpecialGuiElementRenderer
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.client.render.command.RenderDispatcher
 *  net.minecraft.client.render.state.CameraRenderState
 *  net.minecraft.entity.Entity
 *  net.minecraft.client.render.DiffuseLighting.Type
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.entity.EntityDimensions
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.network.ClientPlayerEntity
 *  net.minecraft.client.render.entity.EntityRenderer
 *  net.minecraft.client.render.entity.EntityRenderManager
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fc
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 */
package rtx.kimiko.api.modules.impl.Visuals.emotions;

import com.mojang.blaze3d.textures.GpuTextureView;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mixin.emotions.PictureInPictureRendererAccessor;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.gui.render.SpecialGuiElementRenderer;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.command.RenderDispatcher;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRenderManager;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionPlayback;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionPreviewState;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.render2d.sectormask.BuiltSectorMask;
import rtx.kimiko.utils.render.render2d.sectormask.SectorMaskRenderer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001d2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001dB\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0014\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0014\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0014H\u0014\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0019H\u0014\u00a2\u0006\u0004\b\u001b\u0010\u001c\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionPreviewRenderer;", "Lnet/minecraft/SpecialGuiElementRenderer;", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionPreviewState;", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "bufferSource", "<init>", "(Lnet/minecraft/VertexConsumerProvider$Immediate;)V", "Ljava/lang/Class;", "getRenderStateClass", "()Ljava/lang/Class;", "", "getTextureLabel", "()Ljava/lang/String;", "", "height", "guiScale", "", "getTranslateY", "(II)F", "state", "Lnet/minecraft/MatrixStack;", "poseStack", "", "renderToTexture", "(Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionPreviewState;Lnet/minecraft/MatrixStack;)V", "Lnet/minecraft/GuiRenderState;", "guiRenderState", "blitTexture", "(Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionPreviewState;Lnet/minecraft/GuiRenderState;)V", "Companion", "rtx.kimiko:kimiko"})
public final class EmotionPreviewRenderer
extends SpecialGuiElementRenderer<EmotionPreviewState> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final float TAU = (float)Math.PI * 2;

    public EmotionPreviewRenderer(@NotNull VertexConsumerProvider.Immediate bufferSource) {
        super(bufferSource);
    }

    @NotNull
    public Class<EmotionPreviewState> getElementClass() {
        return EmotionPreviewState.class;
    }

    @NotNull
    protected String getName() {
        return "kimiko emotion previews";
    }

    protected float getYOffset(int height, int guiScale) {
        return (float)height / 2.0f;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void render(@NotNull EmotionPreviewState state, @NotNull MatrixStack poseStack) {
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
        Intrinsics.checkNotNullParameter((Object)poseStack, (String)"poseStack");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        ClientPlayerEntity player = mc.player;
        if (player == null || state.emotions.isEmpty()) {
            return;
        }
        mc.gameRenderer.getDiffuseLighting().setShaderLights(DiffuseLighting.Type.ENTITY_IN_UI);
        EntityRenderManager entityRenderManager2 = mc.getEntityRenderDispatcher();
        Intrinsics.checkNotNullExpressionValue((Object)entityRenderManager2, (String)"getEntityRenderDispatcher(...)");
        EntityRenderManager dispatcher = entityRenderManager2;
        EntityRenderer entityRenderer2 = dispatcher.getRenderer((Entity)player);
        Intrinsics.checkNotNull((Object)entityRenderer2, (String)"null cannot be cast to non-null type net.minecraft.client.renderer.entity.EntityRenderer<in net.minecraft.world.entity.Entity, net.minecraft.client.renderer.entity.state.EntityRenderState>");
        EntityRenderer renderer = entityRenderer2;
        RenderDispatcher renderDispatcher2 = mc.gameRenderer.getEntityRenderDispatcher();
        Intrinsics.checkNotNullExpressionValue((Object)renderDispatcher2, (String)"getFeatureRenderDispatcher(...)");
        RenderDispatcher features = renderDispatcher2;
        Quaternionf flip = new Quaternionf().rotateZ((float)Math.PI);
        float atlasWidth = (float)state.columns * state.cellWidth;
        float atlasHeight = (float)state.rows * state.cellHeight;
        int n = ((Collection)state.emotions).size();
        for (int i = 0; i < n; ++i) {
            EntityDimensions standing;
            int column = i % state.columns;
            int row = i / state.columns;
            float dx = ((float)column + 0.5f) * state.cellWidth - atlasWidth * 0.5f;
            float dy = ((float)row + 0.5f) * state.cellHeight - atlasHeight * 0.5f;
            EntityRenderState renderState = null;
            EmotionPlayback.beginPreview(state.emotions.get(i), state.time);
            try {
                EntityRenderState entityRenderState2 = renderer.getAndUpdateRenderState((Entity)player, 1.0f);
                Intrinsics.checkNotNullExpressionValue((Object)entityRenderState2, (String)"createRenderState(...)");
                renderState = entityRenderState2;
            }
            catch (RuntimeException ignored) {
                continue;
            }
            finally {
                EmotionPlayback.endPreview();
            }
            renderState.light = 0xF000F0;
            renderState.outlineColor = 0;
            renderState.shadowPieces.clear();
            renderState.displayName = null;
            renderState.nameLabelPos = null;
            if (renderState instanceof LivingEntityRenderState) {
                ((LivingEntityRenderState)renderState).bodyYaw = 180.0f;
                ((LivingEntityRenderState)renderState).relativeHeadYaw = 0.0f;
                ((LivingEntityRenderState)renderState).pitch = 0.0f;
                renderState.width /= ((LivingEntityRenderState)renderState).baseScale;
                renderState.height /= ((LivingEntityRenderState)renderState).baseScale;
                ((LivingEntityRenderState)renderState).baseScale = 1.0f;
                ((LivingEntityRenderState)renderState).limbSwingAnimationProgress = 0.0f;
                ((LivingEntityRenderState)renderState).limbSwingAmplitude = 0.0f;
            }
            if (renderState instanceof BipedEntityRenderState) {
                ((BipedEntityRenderState)renderState).handSwingProgress = 0.0f;
                ((BipedEntityRenderState)renderState).isInSneakingPose = false;
                ((BipedEntityRenderState)renderState).isGliding = false;
                ((BipedEntityRenderState)renderState).leaningPitch = 0.0f;
                ((BipedEntityRenderState)renderState).hasVehicle = false;
            }
            standing = player.getType().dimensions;
            renderState.width = standing.width();
            float height = renderState.height = standing.height();
            CameraRenderState camera = new CameraRenderState();
            camera.orientation = new Quaternionf().rotateY((float)Math.PI);
            poseStack.push();
            poseStack.translate(dx, dy, 0.0f);
            poseStack.scale(state.modelScale, state.modelScale, state.modelScale);
            poseStack.translate(0.0f, height * 0.5f + state.modelDrop, 0.0f);
            poseStack.multiply((Quaternionfc)flip);
            dispatcher.render(renderState, camera, 0.0, 0.0, 0.0, poseStack, (OrderedRenderCommandQueue)features.getQueue());
            features.render();
            poseStack.pop();
        }
    }

    protected void blitTexture(@NotNull EmotionPreviewState state, @NotNull GuiRenderState guiRenderState) {
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
        Intrinsics.checkNotNullParameter((Object)guiRenderState, (String)"guiRenderState");
        Intrinsics.checkNotNull((Object)((Object)this), (String)"null cannot be cast to non-null type kotlin.Any");
        PictureInPictureRendererAccessor accessor = (PictureInPictureRendererAccessor)((Object)this);
        GpuTextureView gpuTextureView = accessor.kimiko$textureView();
        if (gpuTextureView == null) {
            return;
        }
        GpuTextureView view = gpuTextureView;
        Matrix3x2f pose = new Matrix3x2f((Matrix3x2fc)state.pose());
        Render2DCoordinateSpace.applyGuiScaleIndependence(pose);
        SectorMaskRenderer.Companion.getInstance().submit(guiRenderState, pose, new BuiltSectorMask(state.designX, state.designY, state.designSize, state.innerRadius, state.outerRadius, state.emotions.size(), state.gapRadians, state.corner, 0.8f, state.hoverIndex, state.hoverGrow, state.hoverShrink, state.hoverZoom, state.ringRadius, state.cellWidthDesign, state.cellHeightDesign, state.columns, state.rows, state.alpha, view), state.scissorArea());
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionPreviewRenderer.Companion;", "", "<init>", "()V", "", "TAU", "F", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

