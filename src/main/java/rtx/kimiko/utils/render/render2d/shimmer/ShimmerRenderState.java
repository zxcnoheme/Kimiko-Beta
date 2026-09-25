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
package rtx.kimiko.utils.render.render2d.shimmer;

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
import rtx.kimiko.utils.render.render2d.shimmer.BuiltShimmer;
import rtx.kimiko.utils.render.render2d.shimmer.ShimmerBatch;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJG\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0016\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0016\u00a2\u0006\u0004\b\u001f\u0010 J\u0011\u0010!\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\b!\u0010\"J\u0011\u0010#\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\b#\u0010\"R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010$R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010%R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010&\u00a8\u0006'"}, d2={"Lrtx/kimiko/utils/render/render2d/shimmer/ShimmerRenderState;", "Lnet/minecraft/SimpleGuiElementRenderState;", "Lorg/joml/Matrix3x2f;", "pose", "Lrtx/kimiko/utils/render/render2d/shimmer/BuiltShimmer;", "shimmer", "Lnet/minecraft/ScreenRect;", "scissor", "<init>", "(Lorg/joml/Matrix3x2f;Lrtx/kimiko/utils/render/render2d/shimmer/BuiltShimmer;Lnet/minecraft/ScreenRect;)V", "()Lrtx/kimiko/utils/render/render2d/shimmer/BuiltShimmer;", "Lnet/minecraft/VertexConsumer;", "consumer", "", "buildVertices", "(Lnet/minecraft/VertexConsumer;)V", "v", "", "x", "y", "u", "fv", "", "color", "batch", "vertex", "(Lnet/minecraft/VertexConsumer;FFFFII)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "()Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lnet/minecraft/TextureSetup;", "textureSetup", "()Lnet/minecraft/TextureSetup;", "scissorArea", "()Lnet/minecraft/ScreenRect;", "bounds", "Lrtx/kimiko/utils/render/render2d/shimmer/BuiltShimmer;", "Lnet/minecraft/ScreenRect;", "Lorg/joml/Matrix3x2f;", "rtx.kimiko:kimiko"})
public final class ShimmerRenderState
implements SimpleGuiElementRenderState {
    @NotNull
    private final BuiltShimmer shimmer;
    @Nullable
    private final ScreenRect scissor;
    @NotNull
    private final Matrix3x2f pose;

    public ShimmerRenderState(@NotNull Matrix3x2f pose, @NotNull BuiltShimmer shimmer, @Nullable ScreenRect scissor) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)shimmer, (String)"shimmer");
        this.shimmer = shimmer;
        this.scissor = scissor;
        this.pose = PoseCache.snapshot(pose);
    }

    @NotNull
    public final BuiltShimmer shimmer() {
        return this.shimmer;
    }

    public void setupVertices(@NotNull VertexConsumer consumer) {
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        int batchIndex = ShimmerBatch.INSTANCE.reserve(this.shimmer());
        if (batchIndex < 0) {
            return;
        }
        float x0 = this.shimmer.x();
        float y0 = this.shimmer.y();
        float x1 = this.shimmer.x() + this.shimmer.width();
        float y1 = this.shimmer.y() + this.shimmer.height();
        int c = this.shimmer.color();
        this.vertex(consumer, x0, y0, 0.0f, 0.0f, c, batchIndex);
        this.vertex(consumer, x0, y1, 0.0f, 1.0f, c, batchIndex);
        this.vertex(consumer, x1, y1, 1.0f, 1.0f, c, batchIndex);
        this.vertex(consumer, x1, y0, 1.0f, 0.0f, c, batchIndex);
    }

    private final void vertex(VertexConsumer v, float x, float y, float u, float fv, int color, int batch) {
        v.vertex((Matrix3x2fc)this.pose, x, y).texture(u, fv).color(color).lineWidth((float)(batch + 1));
    }

    @NotNull
    public RenderPipeline pipeline() {
        return ShimmerBatch.PIPELINE;
    }

    @NotNull
    public TextureSetup textureSetup() {
        TextureSetup textureSetup2 = TextureSetup.empty();
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup2, (String)"noTexture(...)");
        return textureSetup2;
    }

    @Nullable
    public ScreenRect scissorArea() {
        return this.scissor;
    }

    @Nullable
    public ScreenRect bounds() {
        int x = (int)Math.floor(this.shimmer.x());
        int y = (int)Math.floor(this.shimmer.y());
        int w = Math.max(1, (int)Math.ceil(this.shimmer.width()));
        int h = Math.max(1, (int)Math.ceil(this.shimmer.height()));
        ScreenRect screenRect2 = new ScreenRect(x, y, w, h).transformEachVertex((Matrix3x2fc)this.pose);
        Intrinsics.checkNotNullExpressionValue((Object)screenRect2, (String)"transformMaxBounds(...)");
        ScreenRect r = screenRect2;
        return this.scissor == null ? r : this.scissor.intersection(r);
    }
}

