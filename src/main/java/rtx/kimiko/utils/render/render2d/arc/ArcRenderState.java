/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.gui.ScreenRect
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fc
 */
package rtx.kimiko.utils.render.render2d.arc;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.gui.ScreenRect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;
import rtx.kimiko.utils.render.render2d.PoseCache;
import rtx.kimiko.utils.render.render2d.arc.ArcBatch;
import rtx.kimiko.utils.render.render2d.arc.BuiltArc;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ?\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0016\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\u001eJ\u0011\u0010\u001f\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\b\u001f\u0010\u001eR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010 R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010!R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\"R\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010!\u00a8\u0006#"}, d2={"Lrtx/kimiko/utils/render/render2d/arc/ArcRenderState;", "Lnet/minecraft/SimpleGuiElementRenderState;", "Lorg/joml/Matrix3x2f;", "pose", "Lrtx/kimiko/utils/render/render2d/arc/BuiltArc;", "arc", "Lnet/minecraft/ScreenRect;", "scissorArea", "<init>", "(Lorg/joml/Matrix3x2f;Lrtx/kimiko/utils/render/render2d/arc/BuiltArc;Lnet/minecraft/ScreenRect;)V", "Lnet/minecraft/VertexConsumer;", "consumer", "", "buildVertices", "(Lnet/minecraft/VertexConsumer;)V", "", "x", "y", "", "coordX", "coordY", "batchIndex", "vertex", "(Lnet/minecraft/VertexConsumer;FFIII)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "()Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lnet/minecraft/TextureSetup;", "textureSetup", "()Lnet/minecraft/TextureSetup;", "()Lnet/minecraft/ScreenRect;", "bounds", "Lrtx/kimiko/utils/render/render2d/arc/BuiltArc;", "Lnet/minecraft/ScreenRect;", "Lorg/joml/Matrix3x2f;", "rtx.kimiko:kimiko"})
public final class ArcRenderState
implements SimpleGuiElementRenderState {
    @NotNull
    private final BuiltArc arc;
    @Nullable
    private final ScreenRect scissorArea;
    @NotNull
    private final Matrix3x2f pose;
    @Nullable
    private final ScreenRect bounds;

    public ArcRenderState(@NotNull Matrix3x2f pose, @NotNull BuiltArc arc, @Nullable ScreenRect scissorArea) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)arc, (String)"arc");
        this.arc = arc;
        this.scissorArea = scissorArea;
        this.pose = PoseCache.snapshot(pose);
        float spanX = this.arc.spanX();
        float spanY = this.arc.spanY();
        float cx = this.arc.centerX();
        float cy = this.arc.centerY() - this.arc.lift() * 0.5f;
        int x0 = (int)Math.floor(cx - spanX);
        int y0 = (int)Math.floor(cy - spanY);
        int w = (int)Math.ceil(spanX * 2.0f);
        int h = (int)Math.ceil(spanY * 2.0f);
        ScreenRect screenRect2 = new ScreenRect(x0, y0, w, h).transformEachVertex((Matrix3x2fc)this.pose);
        Intrinsics.checkNotNullExpressionValue((Object)screenRect2, (String)"transformMaxBounds(...)");
        ScreenRect transformed = screenRect2;
        this.bounds = this.scissorArea == null ? transformed : this.scissorArea.intersection(transformed);
    }

    public void setupVertices(@NotNull VertexConsumer consumer) {
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        int batchIndex = ArcBatch.INSTANCE.reserve(this.arc);
        if (batchIndex < 0) {
            return;
        }
        float spanX = this.arc.spanX();
        float spanY = this.arc.spanY();
        float cx = this.arc.centerX();
        float cy = this.arc.centerY() - this.arc.lift() * 0.5f;
        float x0 = cx - spanX;
        float y0 = cy - spanY;
        float x1 = cx + spanX;
        float y1 = cy + spanY;
        this.vertex(consumer, x0, y0, 0, 0, batchIndex);
        this.vertex(consumer, x0, y1, 0, 255, batchIndex);
        this.vertex(consumer, x1, y1, 255, 255, batchIndex);
        this.vertex(consumer, x1, y0, 255, 0, batchIndex);
    }

    private final void vertex(VertexConsumer consumer, float x, float y, int coordX, int coordY, int batchIndex) {
        consumer.vertex((Matrix3x2fc)this.pose, x, y).color(coordX, coordY, 255, 255).lineWidth((float)(batchIndex + 1));
    }

    @NotNull
    public RenderPipeline pipeline() {
        return ArcBatch.PIPELINE;
    }

    @NotNull
    public TextureSetup textureSetup() {
        TextureSetup textureSetup2 = TextureSetup.empty();
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup2, (String)"noTexture(...)");
        return textureSetup2;
    }

    @Nullable
    public ScreenRect scissorArea() {
        return this.scissorArea;
    }

    @Nullable
    public ScreenRect bounds() {
        return this.bounds;
    }
}

