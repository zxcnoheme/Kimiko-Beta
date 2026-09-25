/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.FilterMode
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gl.GpuSampler
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.gui.ScreenRect
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fc
 */
package rtx.kimiko.utils.render.fonts.core.msdf;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.gui.ScreenRect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfTextRenderer;
import rtx.kimiko.utils.render.render2d.PoseCache;
import rtx.kimiko.utils.render.render2d.blur.BlurCapture;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u000b\u0018\u0000 \\2\u00020\u0001:\u0001\\B]\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\b\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0095\u0001\u0010(\u001a\u00020'2\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u000b\u00a2\u0006\u0004\b(\u0010)J\r\u0010*\u001a\u00020\r\u00a2\u0006\u0004\b*\u0010+J\u0015\u0010-\u001a\u00020'2\u0006\u0010,\u001a\u00020\r\u00a2\u0006\u0004\b-\u0010.J\u0017\u0010/\u001a\u00020'2\u0006\u0010,\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b/\u0010.J\u0017\u00102\u001a\u00020'2\u0006\u00101\u001a\u000200H\u0016\u00a2\u0006\u0004\b2\u00103J\u001f\u00106\u001a\u00020'2\u0006\u00104\u001a\u00020\u000b2\u0006\u00105\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b6\u00107J?\u0010<\u001a\u00020'2\u0006\u00108\u001a\u0002002\u0006\u00104\u001a\u00020\u000b2\u0006\u00105\u001a\u00020\u000b2\u0006\u00109\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b<\u0010=JG\u0010?\u001a\u00020'2\u0006\u00108\u001a\u0002002\u0006\u00104\u001a\u00020\u000b2\u0006\u00105\u001a\u00020\u000b2\u0006\u00109\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020\r2\u0006\u0010>\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b?\u0010@J\u000f\u0010B\u001a\u00020AH\u0016\u00a2\u0006\u0004\bB\u0010CJ\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010DJ\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010EJ\u0011\u0010F\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\bF\u0010ER\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010GR\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010HR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010IR\u0014\u0010\n\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010IR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010JR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010KR\u0014\u0010\u000f\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000f\u0010IR\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010LR\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010MR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010NR\u0016\u0010P\u001a\u00020O8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010QR\u0016\u0010S\u001a\u00020R8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010TR\u0016\u0010*\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010KR\u0016\u0010U\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bU\u0010JR\u0016\u0010V\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010JR\u0016\u0010W\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bW\u0010JR\u0016\u0010X\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010JR\u0018\u0010Y\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010HR\u0018\u0010Z\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010GR\u0018\u0010[\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b[\u0010L\u00a8\u0006]"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderState;", "Lnet/minecraft/SimpleGuiElementRenderState;", "Lorg/joml/Matrix3x2f;", "pose", "Lnet/minecraft/TextureSetup;", "textureSetup", "Lnet/minecraft/ScreenRect;", "scissorArea", "", "shimmer", "wave", "", "atlasUnitRange", "", "expectedGlyphs", "adaptiveBackground", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "atlasView", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "backgroundCapture", "<init>", "(Lorg/joml/Matrix3x2f;Lnet/minecraft/TextureSetup;Lnet/minecraft/ScreenRect;ZZFIZLcom/mojang/blaze3d/textures/GpuTextureView;Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;)V", "x0", "y0", "x1", "y1", "u0", "v0", "u1", "v1", "colorTopLeft", "colorTopRight", "colorBottomRight", "colorBottomLeft", "l0", "l1", "rotationDegrees", "rotationOriginX", "rotationOriginY", "", "addGlyph", "(FFFFFFFFIIIIFFFFF)V", "glyphCount", "()I", "additionalGlyphs", "reserve", "(I)V", "ensureCapacity", "Lnet/minecraft/VertexConsumer;", "consumer", "buildVertices", "(Lnet/minecraft/VertexConsumer;)V", "x", "y", "include", "(FF)V", "c", "u", "v", "color", "vertex", "(Lnet/minecraft/VertexConsumer;FFFFI)V", "linePos", "shimmerVertex", "(Lnet/minecraft/VertexConsumer;FFFFIF)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "()Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "()Lnet/minecraft/TextureSetup;", "()Lnet/minecraft/ScreenRect;", "bounds", "Lnet/minecraft/TextureSetup;", "Lnet/minecraft/ScreenRect;", "Z", "F", "I", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "Lorg/joml/Matrix3x2f;", "", "geometry", "[F", "", "colors", "[I", "minX", "minY", "maxX", "maxY", "cachedBounds", "adaptiveSetup", "adaptiveView", "Companion", "rtx.kimiko:kimiko"})
public final class MsdfTextRenderState
implements SimpleGuiElementRenderState {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final TextureSetup textureSetup;
    @Nullable
    private final ScreenRect scissorArea;
    private final boolean shimmer;
    private final boolean wave;
    private final float atlasUnitRange;
    private final int expectedGlyphs;
    private final boolean adaptiveBackground;
    @Nullable
    private final GpuTextureView atlasView;
    @Nullable
    private final BlurCapture backgroundCapture;
    @NotNull
    private final Matrix3x2f pose;
    @NotNull
    private float[] geometry;
    @NotNull
    private int[] colors;
    private int glyphCount;
    private float minX;
    private float minY;
    private float maxX;
    private float maxY;
    @Nullable
    private ScreenRect cachedBounds;
    @Nullable
    private TextureSetup adaptiveSetup;
    @Nullable
    private GpuTextureView adaptiveView;
    private static final int FLOATS_PER_GLYPH = 14;
    private static final int COLORS_PER_GLYPH = 4;
    public static final int INITIAL_GLYPHS = 4;
    @NotNull
    private static final float[] EMPTY_GEOMETRY = new float[0];
    @NotNull
    private static final int[] EMPTY_COLORS = new int[0];

    public MsdfTextRenderState(@NotNull Matrix3x2f pose, @NotNull TextureSetup textureSetup, @Nullable ScreenRect scissorArea, boolean shimmer, boolean wave, float atlasUnitRange, int expectedGlyphs, boolean adaptiveBackground, @Nullable GpuTextureView atlasView, @Nullable BlurCapture backgroundCapture) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)textureSetup, (String)"textureSetup");
        this.textureSetup = textureSetup;
        this.scissorArea = scissorArea;
        this.shimmer = shimmer;
        this.wave = wave;
        this.atlasUnitRange = atlasUnitRange;
        this.expectedGlyphs = expectedGlyphs;
        this.adaptiveBackground = adaptiveBackground;
        this.atlasView = atlasView;
        this.backgroundCapture = backgroundCapture;
        this.pose = PoseCache.snapshot(pose);
        this.geometry = EMPTY_GEOMETRY;
        this.colors = EMPTY_COLORS;
        this.minX = Float.MAX_VALUE;
        this.minY = Float.MAX_VALUE;
        this.maxX = -3.4028235E38f;
        this.maxY = -3.4028235E38f;
    }

    public final void addGlyph(float x0, float y0, float x1, float y1, float u0, float v0, float u1, float v1, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float l0, float l1, float rotationDegrees, float rotationOriginX, float rotationOriginY) {
        float tlX = x0;
        float tlY = y0;
        float blX = x0;
        float blY = y1;
        float brX = x1;
        float brY = y1;
        float trX = x1;
        float trY = y0;
        if (Math.abs(rotationDegrees) >= 0.001f) {
            float radians = (float)Math.toRadians(rotationDegrees);
            float s = (float)Math.sin(radians);
            float c = (float)Math.cos(radians);
            float dx = tlX - rotationOriginX;
            float dy = tlY - rotationOriginY;
            tlX = rotationOriginX + dx * c - dy * s;
            tlY = rotationOriginY + dx * s + dy * c;
            dx = blX - rotationOriginX;
            dy = blY - rotationOriginY;
            blX = rotationOriginX + dx * c - dy * s;
            blY = rotationOriginY + dx * s + dy * c;
            dx = brX - rotationOriginX;
            dy = brY - rotationOriginY;
            brX = rotationOriginX + dx * c - dy * s;
            brY = rotationOriginY + dx * s + dy * c;
            dx = trX - rotationOriginX;
            dy = trY - rotationOriginY;
            trX = rotationOriginX + dx * c - dy * s;
            trY = rotationOriginY + dx * s + dy * c;
        }
        this.ensureCapacity(1);
        int base = this.glyphCount * 14;
        this.geometry[base] = tlX;
        this.geometry[base + 1] = tlY;
        this.geometry[base + 2] = blX;
        this.geometry[base + 3] = blY;
        this.geometry[base + 4] = brX;
        this.geometry[base + 5] = brY;
        this.geometry[base + 6] = trX;
        this.geometry[base + 7] = trY;
        this.geometry[base + 8] = u0;
        this.geometry[base + 9] = v0;
        this.geometry[base + 10] = u1;
        this.geometry[base + 11] = v1;
        this.geometry[base + 12] = l0;
        this.geometry[base + 13] = l1;
        int colorBase = this.glyphCount * 4;
        this.colors[colorBase] = colorTopLeft;
        this.colors[colorBase + 1] = colorBottomLeft;
        this.colors[colorBase + 2] = colorBottomRight;
        this.colors[colorBase + 3] = colorTopRight;
        int n = this.glyphCount;
        this.glyphCount = n + 1;
        this.include(tlX, tlY);
        this.include(blX, blY);
        this.include(brX, brY);
        this.include(trX, trY);
    }

    public final int glyphCount() {
        return this.glyphCount;
    }

    public final void reserve(int additionalGlyphs) {
        this.ensureCapacity(additionalGlyphs);
    }

    private final void ensureCapacity(int additionalGlyphs) {
        int required = this.glyphCount + Math.max(1, additionalGlyphs);
        if (required * 14 <= this.geometry.length) {
            return;
        }
        int nextGlyphs = Math.max(this.expectedGlyphs, Math.max(required, this.glyphCount * 2));
        float[] grownGeometry = new float[nextGlyphs * 14];
        System.arraycopy(this.geometry, 0, grownGeometry, 0, this.glyphCount * 14);
        this.geometry = grownGeometry;
        int[] grownColors = new int[nextGlyphs * 4];
        System.arraycopy(this.colors, 0, grownColors, 0, this.glyphCount * 4);
        this.colors = grownColors;
    }

    public void setupVertices(@NotNull VertexConsumer consumer) {
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        boolean lined = this.shimmer || this.wave;
        int n = this.glyphCount;
        for (int glyph = 0; glyph < n; ++glyph) {
            int base = glyph * 14;
            int colorBase = glyph * 4;
            float u0 = this.geometry[base + 8];
            float v0 = this.geometry[base + 9];
            float u1 = this.geometry[base + 10];
            float v1 = this.geometry[base + 11];
            float l0 = this.geometry[base + 12];
            float l1 = this.geometry[base + 13];
            if (lined) {
                this.shimmerVertex(consumer, this.geometry[base], this.geometry[base + 1], u0, v0, this.colors[colorBase], l0);
                this.shimmerVertex(consumer, this.geometry[base + 2], this.geometry[base + 3], u0, v1, this.colors[colorBase + 1], l0);
                this.shimmerVertex(consumer, this.geometry[base + 4], this.geometry[base + 5], u1, v1, this.colors[colorBase + 2], l1);
                this.shimmerVertex(consumer, this.geometry[base + 6], this.geometry[base + 7], u1, v0, this.colors[colorBase + 3], l1);
                continue;
            }
            this.vertex(consumer, this.geometry[base], this.geometry[base + 1], u0, v0, this.colors[colorBase]);
            this.vertex(consumer, this.geometry[base + 2], this.geometry[base + 3], u0, v1, this.colors[colorBase + 1]);
            this.vertex(consumer, this.geometry[base + 4], this.geometry[base + 5], u1, v1, this.colors[colorBase + 2]);
            this.vertex(consumer, this.geometry[base + 6], this.geometry[base + 7], u1, v0, this.colors[colorBase + 3]);
        }
    }

    private final void include(float x, float y) {
        this.minX = Math.min(this.minX, x);
        this.minY = Math.min(this.minY, y);
        this.maxX = Math.max(this.maxX, x);
        this.maxY = Math.max(this.maxY, y);
    }

    private final void vertex(VertexConsumer c, float x, float y, float u, float v, int color) {
        c.vertex((Matrix3x2fc)this.pose, x, y).texture(u, v).color(color).lineWidth(this.atlasUnitRange);
    }

    private final void shimmerVertex(VertexConsumer c, float x, float y, float u, float v, int color, float linePos) {
        c.vertex((Matrix3x2fc)this.pose, x, y).texture(u, v).color(color).lineWidth(linePos);
    }

    @NotNull
    public RenderPipeline pipeline() {
        if (this.wave) {
            return MsdfTextRenderer.MSDF_WAVE_PIPELINE;
        }
        if (this.adaptiveBackground) {
            BlurCapture blurCapture = this.backgroundCapture;
            if ((blurCapture != null ? blurCapture.backdropView : null) != null) {
                return MsdfTextRenderer.MSDF_ADAPTIVE_PIPELINE;
            }
        }
        return this.shimmer ? MsdfTextRenderer.MSDF_SHIMMER_PIPELINE : MsdfTextRenderer.MSDF_PIPELINE;
    }

    @NotNull
    public TextureSetup textureSetup() {
        TextureSetup textureSetup2;
        if (!this.adaptiveBackground) {
            return this.textureSetup;
        }
        GpuTextureView gpuTextureView = this.atlasView;
        if (gpuTextureView == null) {
            return this.textureSetup;
        }
        GpuTextureView atlas = gpuTextureView;
        BlurCapture blurCapture = this.backgroundCapture;
        if (blurCapture == null) {
            return this.textureSetup;
        }
        BlurCapture capture = blurCapture;
        GpuTextureView gpuTextureView2 = capture.backdropView;
        if (gpuTextureView2 == null) {
            return this.textureSetup;
        }
        GpuTextureView backdrop = gpuTextureView2;
        GpuSampler gpuSampler2 = capture.backdropSampler;
        if (gpuSampler2 == null) {
            GpuSampler gpuSampler3 = RenderSystem.getSamplerCache().get(FilterMode.LINEAR);
            gpuSampler2 = gpuSampler3;
            Intrinsics.checkNotNullExpressionValue((Object)gpuSampler3, (String)"getClampToEdge(...)");
        }
        GpuSampler sampler = gpuSampler2;
        if (this.adaptiveSetup == null || this.adaptiveView != backdrop) {
            this.adaptiveSetup = TextureSetup.of((GpuTextureView)atlas, (GpuSampler)sampler, (GpuTextureView)backdrop, (GpuSampler)sampler);
            this.adaptiveView = backdrop;
        }
        if ((textureSetup2 = this.adaptiveSetup) == null) {
            textureSetup2 = this.textureSetup;
        }
        return textureSetup2;
    }

    @Nullable
    public ScreenRect scissorArea() {
        return this.scissorArea;
    }

    @Nullable
    public ScreenRect bounds() {
        ScreenRect result;
        ScreenRect cached = this.cachedBounds;
        if (cached != null) {
            return cached;
        }
        if (this.glyphCount == 0) {
            ScreenRect empty;
            this.cachedBounds = empty = new ScreenRect(0, 0, 1, 1);
            return empty;
        }
        int x = (int)Math.floor(this.minX);
        int y = (int)Math.floor(this.minY);
        int width = Math.max(1, (int)Math.ceil(this.maxX - this.minX));
        int height = Math.max(1, (int)Math.ceil(this.maxY - this.minY));
        ScreenRect screenRect2 = new ScreenRect(x, y, width, height).transformEachVertex((Matrix3x2fc)this.pose);
        Intrinsics.checkNotNullExpressionValue((Object)screenRect2, (String)"transformMaxBounds(...)");
        ScreenRect r = screenRect2;
        this.cachedBounds = result = this.scissorArea == null ? r : this.scissorArea.intersection(r);
        return result;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderState.Companion;", "", "<init>", "()V", "", "FLOATS_PER_GLYPH", "I", "COLORS_PER_GLYPH", "INITIAL_GLYPHS", "", "EMPTY_GEOMETRY", "[F", "", "EMPTY_COLORS", "[I", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

