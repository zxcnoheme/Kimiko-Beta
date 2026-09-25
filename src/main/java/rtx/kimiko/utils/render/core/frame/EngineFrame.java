/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.systems.RenderPass
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 */
package rtx.kimiko.utils.render.core.frame;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderPass;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import rtx.kimiko.utils.render.core.batch.UiBatch;
import rtx.kimiko.utils.render.core.frame.PrimitiveRegistry;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\n\u001a\u00020\b2\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ1\u0010\u000e\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\bH\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u0013\u0010\u0011\u001a\u00020\bH\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u0011\u0010\u0003J\u001d\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u0015\u0010\u0016J'\u0010\u0019\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\bH\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u001b\u0010\u0003R.\u0010\u001c\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0087\u000er\u0002\b\t\u00a2\u0006\u0018\n\u0004\b\u001c\u0010\u001d\u0012\u0004\b\"\u0010\u0003\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!\u00a8\u0006#"}, d2={"Lrtx/kimiko/utils/render/core/frame/EngineFrame;", "", "<init>", "()V", "Lkotlin/Function1;", "Lorg/joml/Matrix3x2f;", "Lnet/minecraft/SimpleGuiElementRenderState;", "factory", "", "Lkotlin/jvm/JvmStatic;", "submit", "(Lkotlin/jvm/functions/Function1;)V", "Lnet/minecraft/DrawContext;", "graphics", "submitWith", "(Lnet/minecraft/DrawContext;Lkotlin/jvm/functions/Function1;)V", "beginGuiFrame", "prepareBuffers", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "", "owns", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Z", "Lcom/mojang/blaze3d/systems/RenderPass;", "pass", "bindForPipeline", "(Lcom/mojang/blaze3d/systems/RenderPass;Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Z", "closeAll", "activeGraphics", "Lnet/minecraft/DrawContext;", "getActiveGraphics", "()Lnet/minecraft/DrawContext;", "setActiveGraphics", "(Lnet/minecraft/DrawContext;)V", "getActiveGraphics$annotations", "rtx.kimiko:kimiko"})
public final class EngineFrame {
    @NotNull
    public static final EngineFrame INSTANCE = new EngineFrame();
    @Nullable
    private static DrawContext activeGraphics;

    private EngineFrame() {
    }

    @Nullable
    public static final DrawContext getActiveGraphics() {
        return activeGraphics;
    }

    public static final void setActiveGraphics(@Nullable DrawContext drawContext2) {
        activeGraphics = drawContext2;
    }

    @JvmStatic
    public static /* synthetic */ void getActiveGraphics$annotations() {
    }

    @JvmStatic
    public static final void submit(@NotNull Function1<? super Matrix3x2f, ? extends SimpleGuiElementRenderState> factory) {
        Intrinsics.checkNotNullParameter(factory, (String)"factory");
        EngineFrame.submitWith(activeGraphics, factory);
    }

    @JvmStatic
    public static final void submitWith(@Nullable DrawContext graphics, @NotNull Function1<? super Matrix3x2f, ? extends SimpleGuiElementRenderState> factory) {
        Intrinsics.checkNotNullParameter(factory, (String)"factory");
        if (graphics == null) {
            return;
        }
        try {
            Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
            ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState().addSimpleElement((SimpleGuiElementRenderState)factory.invoke(pose));
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    @JvmStatic
    public static final void beginGuiFrame() {
        List<UiBatch<?>> batches = PrimitiveRegistry.getAll();
        int n = ((Collection)batches).size();
        for (int index = 0; index < n; ++index) {
            batches.get(index).beginGuiFrame();
        }
    }

    @JvmStatic
    public static final void prepareBuffers() {
        List<UiBatch<?>> batches = PrimitiveRegistry.getAll();
        int n = ((Collection)batches).size();
        for (int index = 0; index < n; ++index) {
            batches.get(index).prepareBuffers();
        }
    }

    @JvmStatic
    public static final boolean owns(@Nullable RenderPipeline pipeline) {
        return PrimitiveRegistry.ownerOf(pipeline) != null;
    }

    @JvmStatic
    public static final boolean bindForPipeline(@Nullable RenderPass pass, @Nullable RenderPipeline pipeline) {
        UiBatch<?> uiBatch = PrimitiveRegistry.ownerOf(pipeline);
        if (uiBatch == null) {
            return false;
        }
        UiBatch<?> owner = uiBatch;
        owner.bindParams(pass, pipeline);
        return true;
    }

    @JvmStatic
    public static final void closeAll() {
        List<UiBatch<?>> batches = PrimitiveRegistry.getAll();
        int n = ((Collection)batches).size();
        for (int index = 0; index < n; ++index) {
            batches.get(index).close();
        }
    }
}

