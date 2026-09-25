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
package rtx.kimiko.utils.render.util.renderitem;

import com.mojang.blaze3d.pipeline.RenderPipeline;
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
import rtx.kimiko.utils.render.util.renderitem.CachedItemQuad;
import rtx.kimiko.utils.render.util.renderitem.CustomItemRenderer;
import rtx.kimiko.utils.render.util.renderitem.ItemTexture;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\f\b\u0000\u0018\u0000 <2\u00020\u0001:\u0001<B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J=\u0010\u0012\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\n\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0014H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017JG\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u001dH\u0016\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010!\u001a\u00020 H\u0016\u00a2\u0006\u0004\b!\u0010\"J\u0011\u0010$\u001a\u0004\u0018\u00010#H\u0016\u00a2\u0006\u0004\b$\u0010%J\u0011\u0010&\u001a\u0004\u0018\u00010#H\u0016\u00a2\u0006\u0004\b&\u0010%J\u001f\u0010'\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b'\u0010(J\u0017\u0010*\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b*\u0010+R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010,R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010-R\u0016\u0010/\u001a\u00020.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0016\u00102\u001a\u0002018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0016\u00104\u001a\u00020.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00100R\u0016\u00105\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b5\u00106R\u0016\u00107\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00108R\u0016\u00109\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u00108R\u0016\u0010:\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u00108R\u0016\u0010;\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b;\u00108\u00a8\u0006="}, d2={"Lrtx/kimiko/utils/render/util/renderitem/ItemRenderState;", "Lnet/minecraft/SimpleGuiElementRenderState;", "Lorg/joml/Matrix3x2f;", "pose", "Lrtx/kimiko/utils/render/util/renderitem/ItemTexture;", "texture", "<init>", "(Lorg/joml/Matrix3x2f;Lrtx/kimiko/utils/render/util/renderitem/ItemTexture;)V", "Lrtx/kimiko/utils/render/util/renderitem/CachedItemQuad;", "quad", "", "x", "y", "size", "", "color", "glintStrength", "", "add", "(Lrtx/kimiko/utils/render/util/renderitem/CachedItemQuad;FFFIF)V", "Lnet/minecraft/VertexConsumer;", "consumer", "buildVertices", "(Lnet/minecraft/VertexConsumer;)V", "u", "v", "batchIndex", "vertex", "(Lnet/minecraft/VertexConsumer;FFFFII)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "()Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lnet/minecraft/TextureSetup;", "textureSetup", "()Lnet/minecraft/TextureSetup;", "Lnet/minecraft/ScreenRect;", "scissorArea", "()Lnet/minecraft/ScreenRect;", "bounds", "include", "(FF)V", "targetQuads", "ensureCapacity", "(I)V", "Lrtx/kimiko/utils/render/util/renderitem/ItemTexture;", "Lorg/joml/Matrix3x2f;", "", "quads", "[F", "", "colors", "[I", "glints", "quadCount", "I", "minX", "F", "minY", "maxX", "maxY", "Companion", "rtx.kimiko:kimiko"})
public final class ItemRenderState
implements SimpleGuiElementRenderState {
    @NotNull
    private static final Companion Companion = new Companion(null);
    @NotNull
    private final ItemTexture texture;
    @NotNull
    private final Matrix3x2f pose;
    @NotNull
    private float[] quads;
    @NotNull
    private int[] colors;
    @NotNull
    private float[] glints;
    private int quadCount;
    private float minX;
    private float minY;
    private float maxX;
    private float maxY;
    private static final int FLOATS_PER_QUAD = 16;

    public ItemRenderState(@NotNull Matrix3x2f pose, @NotNull ItemTexture texture) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
        this.texture = texture;
        this.pose = PoseCache.snapshot(pose);
        this.quads = new float[256];
        this.colors = new int[16];
        this.glints = new float[16];
        this.minX = Float.MAX_VALUE;
        this.minY = Float.MAX_VALUE;
        this.maxX = -3.4028235E38f;
        this.maxY = -3.4028235E38f;
    }

    public final void add(@NotNull CachedItemQuad quad, float x, float y, float size, int color, float glintStrength) {
        Intrinsics.checkNotNullParameter((Object)quad, (String)"quad");
        this.ensureCapacity(this.quadCount + 1);
        int offset = this.quadCount * 16;
        float x0 = x + quad.getX0() * size;
        float y0 = y + quad.getY0() * size;
        float x1 = x + quad.getX1() * size;
        float y1 = y + quad.getY1() * size;
        float x2 = x + quad.getX2() * size;
        float y2 = y + quad.getY2() * size;
        float x3 = x + quad.getX3() * size;
        float y3 = y + quad.getY3() * size;
        this.quads[offset] = x0;
        this.quads[offset + 1] = y0;
        this.quads[offset + 2] = quad.getU0();
        this.quads[offset + 3] = quad.getV0();
        this.quads[offset + 4] = x1;
        this.quads[offset + 5] = y1;
        this.quads[offset + 6] = quad.getU1();
        this.quads[offset + 7] = quad.getV1();
        this.quads[offset + 8] = x2;
        this.quads[offset + 9] = y2;
        this.quads[offset + 10] = quad.getU2();
        this.quads[offset + 11] = quad.getV2();
        this.quads[offset + 12] = x3;
        this.quads[offset + 13] = y3;
        this.quads[offset + 14] = quad.getU3();
        this.quads[offset + 15] = quad.getV3();
        this.colors[this.quadCount] = color;
        this.glints[this.quadCount] = glintStrength;
        int n = this.quadCount;
        this.quadCount = n + 1;
        this.include(x0, y0);
        this.include(x1, y1);
        this.include(x2, y2);
        this.include(x3, y3);
    }

    public void setupVertices(@NotNull VertexConsumer consumer) {
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        int n = this.quadCount;
        for (int i = 0; i < n; ++i) {
            int batchIndex = CustomItemRenderer.Companion.getInstance().reserve$rtx_kimiko_kimiko(this.glints[i]);
            if (batchIndex < 0) continue;
            int offset = i * 16;
            int color = this.colors[i];
            this.vertex(consumer, this.quads[offset], this.quads[offset + 1], this.quads[offset + 2], this.quads[offset + 3], color, batchIndex);
            this.vertex(consumer, this.quads[offset + 4], this.quads[offset + 5], this.quads[offset + 6], this.quads[offset + 7], color, batchIndex);
            this.vertex(consumer, this.quads[offset + 8], this.quads[offset + 9], this.quads[offset + 10], this.quads[offset + 11], color, batchIndex);
            this.vertex(consumer, this.quads[offset + 12], this.quads[offset + 13], this.quads[offset + 14], this.quads[offset + 15], color, batchIndex);
        }
    }

    private final void vertex(VertexConsumer consumer, float x, float y, float u, float v, int color, int batchIndex) {
        consumer.vertex((Matrix3x2fc)this.pose, x, y).texture(u, v).color(color).lineWidth((float)(batchIndex + 1));
    }

    @NotNull
    public RenderPipeline pipeline() {
        return CustomItemRenderer.ITEM_PIPELINE;
    }

    @NotNull
    public TextureSetup textureSetup() {
        return this.texture.getSetup();
    }

    @Nullable
    public ScreenRect scissorArea() {
        return null;
    }

    @Nullable
    public ScreenRect bounds() {
        if (this.quadCount == 0) {
            return new ScreenRect(0, 0, 1, 1);
        }
        int x = (int)Math.floor(this.minX);
        int y = (int)Math.floor(this.minY);
        int width = Math.max(1, (int)Math.ceil(this.maxX - this.minX));
        int height = Math.max(1, (int)Math.ceil(this.maxY - this.minY));
        return new ScreenRect(x, y, width, height).transformEachVertex((Matrix3x2fc)this.pose);
    }

    private final void include(float x, float y) {
        this.minX = Math.min(this.minX, x);
        this.minY = Math.min(this.minY, y);
        this.maxX = Math.max(this.maxX, x);
        this.maxY = Math.max(this.maxY, y);
    }

    private final void ensureCapacity(int targetQuads) {
        if (targetQuads <= this.colors.length) {
            return;
        }
        int newCapacity = Math.max(targetQuads, this.colors.length * 2);
        float[] nextQuads = new float[newCapacity * 16];
        int[] nextColors = new int[newCapacity];
        float[] nextGlints = new float[newCapacity];
        System.arraycopy(this.quads, 0, nextQuads, 0, this.quadCount * 16);
        System.arraycopy(this.colors, 0, nextColors, 0, this.quadCount);
        System.arraycopy(this.glints, 0, nextGlints, 0, this.quadCount);
        this.quads = nextQuads;
        this.colors = nextColors;
        this.glints = nextGlints;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/ItemRenderState.Companion;", "", "<init>", "()V", "", "FLOATS_PER_QUAD", "I", "rtx.kimiko:kimiko"})
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

