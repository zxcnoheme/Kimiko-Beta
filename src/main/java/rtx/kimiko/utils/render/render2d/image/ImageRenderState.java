/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
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
package rtx.kimiko.utils.render.render2d.image;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
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
import rtx.kimiko.utils.render.render2d.image.ImageBatch;
import rtx.kimiko.utils.render.render2d.image.ImageQuad;
import rtx.kimiko.utils.render.render2d.image.ImageTexture;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 <2\u00020\u0001:\u0001<B1\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u00a2\u0006\u0004\b\u000b\u0010\fB+\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\u000b\u0010\rJ\u0015\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016J_\u0010\"\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020 H\u0002\u00a2\u0006\u0004\b\"\u0010#J\u0017\u0010$\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b$\u0010\u0012J\u001f\u0010%\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b%\u0010&J\u000f\u0010(\u001a\u00020'H\u0016\u00a2\u0006\u0004\b(\u0010)J\u000f\u0010+\u001a\u00020*H\u0016\u00a2\u0006\u0004\b+\u0010,J\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010-J\u0011\u0010.\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\b.\u0010-R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010/R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u00100R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u00101R\u0014\u0010\n\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u00101R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u00102R$\u00105\u001a\u0012\u0012\u0004\u0012\u00020\u000e03j\b\u0012\u0004\u0012\u00020\u000e`48\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00106R\u0016\u00107\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00108R\u0016\u00109\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u00108R\u0016\u0010:\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u00108R\u0016\u0010;\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b;\u00108\u00a8\u0006="}, d2={"Lrtx/kimiko/utils/render/render2d/image/ImageRenderState;", "Lnet/minecraft/SimpleGuiElementRenderState;", "Lorg/joml/Matrix3x2f;", "pose", "Lrtx/kimiko/utils/render/render2d/image/ImageTexture;", "texture", "Lnet/minecraft/ScreenRect;", "scissorArea", "", "nearest", "additive", "<init>", "(Lorg/joml/Matrix3x2f;Lrtx/kimiko/utils/render/render2d/image/ImageTexture;Lnet/minecraft/ScreenRect;ZZ)V", "(Lorg/joml/Matrix3x2f;Lrtx/kimiko/utils/render/render2d/image/ImageTexture;Lnet/minecraft/ScreenRect;Z)V", "Lrtx/kimiko/utils/render/render2d/image/ImageQuad;", "image", "", "add", "(Lrtx/kimiko/utils/render/render2d/image/ImageQuad;)V", "Lnet/minecraft/VertexConsumer;", "consumer", "buildVertices", "(Lnet/minecraft/VertexConsumer;)V", "", "x", "y", "u", "v", "", "coordX", "coordY", "batchIndex", "Lrtx/kimiko/utils/render/render2d/image/ImageRenderState.Companion$Spin;", "spin", "vertex", "(Lnet/minecraft/VertexConsumer;Lrtx/kimiko/utils/render/render2d/image/ImageQuad;FFFFIIILrtx/kimiko/utils/render/render2d/image/ImageRenderState.Companion$Spin;)V", "includeBounds", "include", "(FF)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "()Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lnet/minecraft/TextureSetup;", "textureSetup", "()Lnet/minecraft/TextureSetup;", "()Lnet/minecraft/ScreenRect;", "bounds", "Lrtx/kimiko/utils/render/render2d/image/ImageTexture;", "Lnet/minecraft/ScreenRect;", "Z", "Lorg/joml/Matrix3x2f;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "images", "Ljava/util/ArrayList;", "minX", "F", "minY", "maxX", "maxY", "Companion", "rtx.kimiko:kimiko"})
public final class ImageRenderState
implements SimpleGuiElementRenderState {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ImageTexture texture;
    @Nullable
    private final ScreenRect scissorArea;
    private final boolean nearest;
    private final boolean additive;
    @NotNull
    private final Matrix3x2f pose;
    @NotNull
    private final ArrayList<ImageQuad> images;
    private float minX;
    private float minY;
    private float maxX;
    private float maxY;

    public ImageRenderState(@NotNull Matrix3x2f pose, @NotNull ImageTexture texture, @Nullable ScreenRect scissorArea, boolean nearest, boolean additive) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
        this.texture = texture;
        this.scissorArea = scissorArea;
        this.nearest = nearest;
        this.additive = additive;
        this.pose = PoseCache.snapshot(pose);
        this.images = new ArrayList(16);
        this.minX = Float.MAX_VALUE;
        this.minY = Float.MAX_VALUE;
        this.maxX = -3.4028235E38f;
        this.maxY = -3.4028235E38f;
    }

    public ImageRenderState(@NotNull Matrix3x2f pose, @NotNull ImageTexture texture, @Nullable ScreenRect scissorArea, boolean nearest) {
        this(pose, texture, scissorArea, nearest, false);
    }

    public final void add(@NotNull ImageQuad image) {
        Intrinsics.checkNotNullParameter((Object)image, (String)"image");
        this.images.add(image);
        this.includeBounds(image);
    }

    public void setupVertices(@NotNull VertexConsumer consumer) {
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        for (ImageQuad image : this.images) {
            int batchIndex = ImageBatch.INSTANCE.reserve(image);
            if (batchIndex < 0) continue;
            float x0 = image.x;
            float y0 = image.y;
            float x1 = image.x + image.width;
            float y1 = image.y + image.height;
            Spin spin = Spin.of(image);
            this.vertex(consumer, image, x0, y0, image.u0, image.v0, 0, 0, batchIndex, spin);
            this.vertex(consumer, image, x0, y1, image.u0, image.v1, 0, 255, batchIndex, spin);
            this.vertex(consumer, image, x1, y1, image.u1, image.v1, 255, 255, batchIndex, spin);
            this.vertex(consumer, image, x1, y0, image.u1, image.v0, 255, 0, batchIndex, spin);
        }
    }

    private final void vertex(VertexConsumer consumer, ImageQuad image, float x, float y, float u, float v, int coordX, int coordY, int batchIndex, Spin spin) {
        float drawX = spin.x(image, x, y);
        float drawY = spin.y(image, x, y);
        consumer.vertex((Matrix3x2fc)this.pose, drawX, drawY).texture(u, v).color(coordX, coordY, 255, 255).lineWidth((float)(batchIndex + 1));
    }

    private final void includeBounds(ImageQuad image) {
        float x0 = image.x;
        float y0 = image.y;
        float x1 = image.x + image.width;
        float y1 = image.y + image.height;
        Spin spin = Spin.of(image);
        this.include(spin.x(image, x0, y0), spin.y(image, x0, y0));
        this.include(spin.x(image, x0, y1), spin.y(image, x0, y1));
        this.include(spin.x(image, x1, y1), spin.y(image, x1, y1));
        this.include(spin.x(image, x1, y0), spin.y(image, x1, y0));
    }

    private final void include(float x, float y) {
        this.minX = Math.min(this.minX, x);
        this.minY = Math.min(this.minY, y);
        this.maxX = Math.max(this.maxX, x);
        this.maxY = Math.max(this.maxY, y);
    }

    @NotNull
    public RenderPipeline pipeline() {
        return this.additive ? ImageBatch.ADDITIVE_PIPELINE : ImageBatch.PIPELINE;
    }

    @NotNull
    public TextureSetup textureSetup() {
        return this.texture.setup(this.nearest);
    }

    @Nullable
    public ScreenRect scissorArea() {
        return this.scissorArea;
    }

    @Nullable
    public ScreenRect bounds() {
        if (this.images.isEmpty()) {
            return new ScreenRect(0, 0, 1, 1);
        }
        int x = (int)Math.floor(this.minX);
        int y = (int)Math.floor(this.minY);
        int width = Math.max(1, (int)Math.ceil(this.maxX - this.minX));
        int height = Math.max(1, (int)Math.ceil(this.maxY - this.minY));
        ScreenRect screenRect2 = new ScreenRect(x, y, width, height).transformEachVertex((Matrix3x2fc)this.pose);
        Intrinsics.checkNotNullExpressionValue((Object)screenRect2, (String)"transformMaxBounds(...)");
        ScreenRect transformedBounds = screenRect2;
        return this.scissorArea == null ? transformedBounds : this.scissorArea.intersection(transformedBounds);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0005"}, d2={"Lrtx/kimiko/utils/render/render2d/image/ImageRenderState.Companion;", "", "<init>", "()V", "Spin", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    public static final class Spin {
            private final double cos;
            private final double sin;
            @NotNull
            private static final Spin NONE = new Spin(1.0, 0.0);

            public Spin(double cos, double sin) {
                this.cos = cos;
                this.sin = sin;
            }

            @NotNull
            public static final Spin of(@NotNull ImageQuad image) {
                Intrinsics.checkNotNullParameter((Object)image, (String)"image");
                float degrees = image.rotationDegrees;
                if (degrees == 0.0f || !(Math.abs(degrees) <= Float.MAX_VALUE)) {
                    return NONE;
                }
                double radians = Math.toRadians(degrees);
                return new Spin(Math.cos(radians), Math.sin(radians));
            }

            public final float x(@NotNull ImageQuad image, float x, float y) {
                Intrinsics.checkNotNullParameter((Object)image, (String)"image");
                if (this == NONE) {
                    return x;
                }
                float dx = x - image.rotationOriginX;
                float dy = y - image.rotationOriginY;
                return image.rotationOriginX + (float)((double)dx * this.cos - (double)dy * this.sin);
            }

            public final float y(@NotNull ImageQuad image, float x, float y) {
                Intrinsics.checkNotNullParameter((Object)image, (String)"image");
                if (this == NONE) {
                    return y;
                }
                float dx = x - image.rotationOriginX;
                float dy = y - image.rotationOriginY;
                return image.rotationOriginY + (float)((double)dx * this.sin + (double)dy * this.cos);
            }
        }
}

