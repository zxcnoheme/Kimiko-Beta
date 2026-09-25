/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.FilterMode
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gui.render.SpecialGuiElementRenderer
 *  net.minecraft.client.gui.render.state.TexturedQuadGuiElementRenderState
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.client.gui.render.state.special.SpecialGuiElementRenderState
 *  net.minecraft.client.render.item.KeyedItemRenderState
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.client.render.command.RenderDispatcher
 *  net.minecraft.client.gl.GpuSampler
 *  net.minecraft.client.render.DiffuseLighting.Type
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.render.OverlayTexture
 *  net.minecraft.util.math.RotationAxis
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Quaternionfc
 */
package rtx.kimiko.utils.render.others.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import mixin.emotions.PictureInPictureRendererAccessor;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gui.render.SpecialGuiElementRenderer;
import net.minecraft.client.gui.render.state.TexturedQuadGuiElementRenderState;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gui.render.state.special.SpecialGuiElementRenderState;
import net.minecraft.client.render.item.KeyedItemRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.command.RenderDispatcher;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionfc;
import rtx.kimiko.utils.render.others.hud.HotbarItemPipState;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002 \u001fB\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0014\u00a2\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0013\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0017\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0015H\u0014\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0012H\u0016\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001e\u00a8\u0006!"}, d2={"Lrtx/kimiko/utils/render/others/hud/HotbarItemPipRenderer;", "Lnet/minecraft/SpecialGuiElementRenderer;", "Lrtx/kimiko/utils/render/others/hud/HotbarItemPipState;", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "bufferSource", "<init>", "(Lnet/minecraft/VertexConsumerProvider$Immediate;)V", "Ljava/lang/Class;", "getRenderStateClass", "()Ljava/lang/Class;", "", "getTextureLabel", "()Ljava/lang/String;", "state", "Lnet/minecraft/GuiRenderState;", "guiRenderState", "", "guiScale", "", "prepare", "(Lrtx/kimiko/utils/render/others/hud/HotbarItemPipState;Lnet/minecraft/GuiRenderState;I)V", "Lnet/minecraft/MatrixStack;", "poseStack", "renderToTexture", "(Lrtx/kimiko/utils/render/others/hud/HotbarItemPipState;Lnet/minecraft/MatrixStack;)V", "close", "()V", "", "Lrtx/kimiko/utils/render/others/hud/HotbarItemPipRenderer$SlotRenderer;", "slots", "[Lrtx/kimiko/utils/render/others/hud/HotbarItemPipRenderer$SlotRenderer;", "Companion", "SlotRenderer", "rtx.kimiko:kimiko"})
public final class HotbarItemPipRenderer
extends SpecialGuiElementRenderer<HotbarItemPipState> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SlotRenderer[] slots;
    private static final int FULL_BRIGHT = 0xF000F0;
    private static final int SUPERSAMPLE = 2;
    private static final float SIZE_EPSILON = 0.01f;
    private static final float TILT_EPSILON = 0.05f;

    public HotbarItemPipRenderer(@NotNull VertexConsumerProvider.Immediate bufferSource) {
        super(bufferSource);
        this.slots = new SlotRenderer[9];
        for (int i = 0; i < 9; i++) {
            this.slots[i] = new SlotRenderer(bufferSource);
        }
    }

    @NotNull
    public Class<HotbarItemPipState> getElementClass() {
        return HotbarItemPipState.class;
    }

    @NotNull
    protected String getName() {
        return "kimiko hotbar item";
    }

    @Override
    public void render(@NotNull HotbarItemPipState state, @NotNull GuiRenderState guiRenderState, int guiScale) {
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
        Intrinsics.checkNotNullParameter((Object)guiRenderState, (String)"guiRenderState");
        this.slots[RangesKt.coerceIn((int)state.slot, (int)0, (int)(this.slots.length - 1))].render(state, guiRenderState, guiScale);
    }

    @Override
    protected void render(@NotNull HotbarItemPipState state, @NotNull MatrixStack poseStack) {
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
        Intrinsics.checkNotNullParameter((Object)poseStack, (String)"poseStack");
    }

    public void close() {
        for (SlotRenderer renderer : this.slots) {
            renderer.close();
        }
        super.close();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\n\u00a8\u0006\f"}, d2={"Lrtx/kimiko/utils/render/others/hud/HotbarItemPipRenderer.Companion;", "", "<init>", "()V", "", "FULL_BRIGHT", "I", "SUPERSAMPLE", "", "SIZE_EPSILON", "F", "TILT_EPSILON", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0014\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0014\u00a2\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u0002H\u0014\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001cH\u0014\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010 \u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0014H\u0014\u00a2\u0006\u0004\b \u0010!R\u0018\u0010#\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0016\u0010'\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u0010&\u00a8\u0006("}, d2={"Lrtx/kimiko/utils/render/others/hud/HotbarItemPipRenderer$SlotRenderer;", "Lnet/minecraft/SpecialGuiElementRenderer;", "Lrtx/kimiko/utils/render/others/hud/HotbarItemPipState;", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "bufferSource", "<init>", "(Lnet/minecraft/VertexConsumerProvider$Immediate;)V", "Ljava/lang/Class;", "getRenderStateClass", "()Ljava/lang/Class;", "", "getTextureLabel", "()Ljava/lang/String;", "", "height", "guiScale", "", "getTranslateY", "(II)F", "state", "Lnet/minecraft/GuiRenderState;", "guiRenderState", "", "prepare", "(Lrtx/kimiko/utils/render/others/hud/HotbarItemPipState;Lnet/minecraft/GuiRenderState;I)V", "", "textureIsReadyToBlit", "(Lrtx/kimiko/utils/render/others/hud/HotbarItemPipState;)Z", "Lnet/minecraft/MatrixStack;", "poseStack", "renderToTexture", "(Lrtx/kimiko/utils/render/others/hud/HotbarItemPipState;Lnet/minecraft/MatrixStack;)V", "blitTexture", "(Lrtx/kimiko/utils/render/others/hud/HotbarItemPipState;Lnet/minecraft/GuiRenderState;)V", "", "cachedIdentity", "Ljava/lang/Object;", "cachedSize", "F", "cachedTilt", "rtx.kimiko:kimiko"})
    private static final class SlotRenderer
    extends SpecialGuiElementRenderer<HotbarItemPipState> {
        @Nullable
        private Object cachedIdentity;
        private float cachedSize;
        private float cachedTilt;

        public SlotRenderer(@NotNull VertexConsumerProvider.Immediate bufferSource) {
            super(bufferSource);
            this.cachedSize = -1.0f;
        }

        @NotNull
        public Class<HotbarItemPipState> getElementClass() {
            return HotbarItemPipState.class;
        }

        @NotNull
        protected String getName() {
            return "kimiko hotbar item slot";
        }

        protected float getYOffset(int height, int guiScale) {
            return (float)height / 2.0f;
        }

        @Override
        public void render(@NotNull HotbarItemPipState state, @NotNull GuiRenderState guiRenderState, int guiScale) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            Intrinsics.checkNotNullParameter((Object)guiRenderState, (String)"guiRenderState");
            super.render(state, guiRenderState, guiScale * 2);
        }

        protected boolean textureIsReadyToBlit(@NotNull HotbarItemPipState state) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            KeyedItemRenderState item = state.itemState;
            return !item.isAnimated() && this.cachedIdentity != null && Intrinsics.areEqual((Object)item.getModelKey(), (Object)this.cachedIdentity) && Math.abs(state.sizePixels - this.cachedSize) < 0.01f && Math.abs(state.tiltDegrees - this.cachedTilt) < 0.05f;
        }

        @Override
        protected void render(@NotNull HotbarItemPipState state, @NotNull MatrixStack poseStack) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            Intrinsics.checkNotNullParameter((Object)poseStack, (String)"poseStack");
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            poseStack.scale(1.0f, -1.0f, -1.0f);
            poseStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(-state.tiltDegrees));
            mc.gameRenderer.getDiffuseLighting().setShaderLights(state.itemState.isSideLit() ? DiffuseLighting.Type.ITEMS_3D : DiffuseLighting.Type.ITEMS_FLAT);
            RenderDispatcher renderDispatcher2 = mc.gameRenderer.getEntityRenderDispatcher();
            Intrinsics.checkNotNullExpressionValue((Object)renderDispatcher2, (String)"getFeatureRenderDispatcher(...)");
            RenderDispatcher features = renderDispatcher2;
            state.itemState.render(poseStack, (OrderedRenderCommandQueue)features.getQueue(), 0xF000F0, OverlayTexture.DEFAULT_UV, 0);
            features.render();
            this.cachedIdentity = state.itemState.getModelKey();
            this.cachedSize = state.sizePixels;
            this.cachedTilt = state.tiltDegrees;
        }

        protected void blitTexture(@NotNull HotbarItemPipState state, @NotNull GuiRenderState guiRenderState) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            Intrinsics.checkNotNullParameter((Object)guiRenderState, (String)"guiRenderState");
            Intrinsics.checkNotNull((Object)((Object)this), (String)"null cannot be cast to non-null type kotlin.Any");
            GpuTextureView gpuTextureView = ((PictureInPictureRendererAccessor)((Object)this)).kimiko$textureView();
            if (gpuTextureView == null) {
                return;
            }
            GpuTextureView view = gpuTextureView;
            guiRenderState.addSimpleElementToCurrentLayer(new TexturedQuadGuiElementRenderState(RenderPipelines.GUI_TEXTURED_PREMULTIPLIED_ALPHA, TextureSetup.of((GpuTextureView)view, (GpuSampler)RenderSystem.getSamplerCache().get(FilterMode.LINEAR)), state.pose(), state.x1(), state.y1(), state.x2(), state.y2(), 0.0f, 1.0f, 1.0f, 0.0f, -1, state.scissorArea(), null));
        }
    }
}

