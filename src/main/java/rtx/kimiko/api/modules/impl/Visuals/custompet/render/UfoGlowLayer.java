/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.entity.state.EntityRenderState
 *  net.minecraft.client.render.entity.state.LivingEntityRenderState
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  software.bernie.geckolib.cache.model.GeoBone
 *  software.bernie.geckolib.renderer.base.GeoRenderState
 *  software.bernie.geckolib.renderer.base.GeoRenderer
 *  software.bernie.geckolib.renderer.base.PerBoneRender
 *  software.bernie.geckolib.renderer.base.RenderPassInfo
 *  software.bernie.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer
 */
package rtx.kimiko.api.modules.impl.Visuals.custompet.render;

import java.util.function.BiConsumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Visuals.custompet.entity.CustomPetEntity;
import rtx.kimiko.api.modules.impl.Visuals.custompet.model.CustomPetModel;
import rtx.kimiko.api.modules.impl.Visuals.custompet.render.UfoBeamGlow;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;
import software.bernie.geckolib.cache.model.GeoBone;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.base.GeoRenderer;
import software.bernie.geckolib.renderer.base.PerBoneRender;
import software.bernie.geckolib.renderer.base.RenderPassInfo;
import software.bernie.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u001b*\f\b\u0000\u0010\u0003*\u00020\u0001*\u00020\u00022\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u0004:\u0001\u001bB!\u0012\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u0007\u00a2\u0006\u0004\b\t\u0010\nJ\u0019\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000b\u001a\u00028\u0000H\u0014\u00a2\u0006\u0004\b\r\u0010\u000eJ7\u0010\u0016\u001a\u00020\u00152\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f2\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00130\u0011H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00028\u0000H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/UfoGlowLayer;", "Lnet/minecraft/LivingEntityRenderState;", "Lsoftware/bernie/geckolib/renderer/base/GeoRenderState;", "R", "Lsoftware/bernie/geckolib/renderer/layer/builtin/AutoGlowingGeoLayer;", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;", "Ljava/lang/Void;", "Lsoftware/bernie/geckolib/renderer/base/GeoRenderer;", "renderer", "<init>", "(Lsoftware/bernie/geckolib/renderer/base/GeoRenderer;)V", "renderState", "Lnet/minecraft/RenderLayer;", "getRenderType", "(Lnet/minecraft/LivingEntityRenderState;)Lnet/minecraft/RenderLayer;", "Lsoftware/bernie/geckolib/renderer/base/RenderPassInfo;", "renderPassInfo", "Ljava/util/function/BiConsumer;", "Lsoftware/bernie/geckolib/cache/model/GeoBone;", "Lsoftware/bernie/geckolib/renderer/base/PerBoneRender;", "consumer", "", "addPerBoneRender", "(Lsoftware/bernie/geckolib/renderer/base/RenderPassInfo;Ljava/util/function/BiConsumer;)V", "", "isUfo", "(Lnet/minecraft/LivingEntityRenderState;)Z", "Companion", "rtx.kimiko:kimiko"})
public final class UfoGlowLayer<R extends LivingEntityRenderState>
extends AutoGlowingGeoLayer<CustomPetEntity, Void, R> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final String BEAM_BONE = "beam";

    public UfoGlowLayer(@NotNull GeoRenderer<CustomPetEntity, Void, R> renderer) {
        super(renderer);
    }

    @Nullable
    protected RenderLayer getRenderType(@NotNull R renderState) {
        Intrinsics.checkNotNullParameter(renderState, "renderState");
        if (!this.isUfo(renderState)) {
            return null;
        }
        return super.getRenderType(renderState);
    }

    public void addPerBoneRender(@NotNull RenderPassInfo<R> renderPassInfo, @NotNull BiConsumer<GeoBone, PerBoneRender<R>> consumer) {
        Intrinsics.checkNotNullParameter(renderPassInfo, "renderPassInfo");
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        if (!Intrinsics.areEqual(renderPassInfo.getOrDefaultGeckolibData(CustomPetModel.UFO, false), true)) {
            return;
        }
        Float f = (Float)renderPassInfo.getOrDefaultGeckolibData(CustomPetModel.UFO_BEAM_LEVEL, Float.valueOf(0.0f));
        float f2 = f != null ? f.floatValue() : 0.0f;
        if (f2 <= 0.02f) {
            return;
        }
        renderPassInfo.model().getBone(BEAM_BONE).ifPresent(bone -> {
            consumer.accept(bone, (passInfo, geoBone, renderTasks) -> {
                Float fLevel = (Float)passInfo.getOrDefaultGeckolibData(CustomPetModel.UFO_BEAM_LEVEL, Float.valueOf(0.0f));
                float level = fLevel != null ? fLevel.floatValue() : 0.0f;
                Float fStretch = (Float)passInfo.getOrDefaultGeckolibData(CustomPetModel.UFO_BEAM_STRETCH, Float.valueOf(1.0f));
                float stretch = fStretch != null ? fStretch.floatValue() : 1.0f;
                Integer nColor = (Integer)passInfo.getOrDefaultGeckolibData(CustomPetModel.UFO_BEAM_COLOR, 5236991);
                int rgb = nColor != null ? nColor : 5236991;
                GeoRenderState geoRenderState = passInfo.renderState();
                LivingEntityRenderState state = (LivingEntityRenderState)geoRenderState;
                float age = state instanceof EntityRenderState ? state.age : 0.0f;
                renderTasks.submitCustom(passInfo.poseStack(), ClientPipelines.WORLD_PARTICLES_COLOR, (pose, buffer) -> {
                    UfoBeamGlow.render(buffer, pose, level, stretch, rgb, age);
                });
            });
        });
    }

    private final boolean isUfo(R renderState) {
        return Intrinsics.areEqual(((GeoRenderState)renderState).getOrDefaultGeckolibData(CustomPetModel.UFO, false), true);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/UfoGlowLayer.Companion;", "", "<init>", "()V", "", "BEAM_BONE", "Ljava/lang/String;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

