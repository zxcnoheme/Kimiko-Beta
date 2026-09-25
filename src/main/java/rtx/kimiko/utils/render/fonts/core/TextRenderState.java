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
package rtx.kimiko.utils.render.fonts.core;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import rtx.kimiko.utils.render.fonts.core.FontQuality;
import rtx.kimiko.utils.render.fonts.core.GlyphAtlasPage;
import rtx.kimiko.utils.render.fonts.core.GlyphQuad;
import rtx.kimiko.utils.render.fonts.core.LayoutGlyph;
import rtx.kimiko.utils.render.render2d.PoseCache;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0014\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\u00a3\u0001\u0010$\u001a\u00020#2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u001a\u00a2\u0006\u0004\b$\u0010%J\u0017\u0010(\u001a\u00020#2\u0006\u0010'\u001a\u00020&H\u0016\u00a2\u0006\u0004\b(\u0010)J?\u00101\u001a\u0002002\u0006\u0010*\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020\u000f2\u0006\u0010/\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b1\u00102J\u001f\u00103\u001a\u00020#2\u0006\u0010*\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b3\u00104JW\u00106\u001a\u00020\u00122\u0006\u00105\u001a\u00020\u00122\u0006\u0010*\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b6\u00107J\u0017\u00109\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b9\u0010:J'\u0010=\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\u000f2\u0006\u0010;\u001a\u00020\u000f2\u0006\u0010<\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b=\u0010>J?\u0010A\u001a\u00020#2\u0006\u0010'\u001a\u00020&2\u0006\u0010*\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\u000f2\u0006\u0010?\u001a\u00020\u000f2\u0006\u0010@\u001a\u00020\u000f2\u0006\u00105\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\bA\u0010BJ\u000f\u0010\t\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\t\u0010CJ\u000f\u0010E\u001a\u00020DH\u0016\u00a2\u0006\u0004\bE\u0010FJ\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010GJ\u0011\u0010H\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\bH\u0010GR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010IR\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010JR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010KR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010LR$\u0010P\u001a\u0012\u0012\u0004\u0012\u00020N0Mj\b\u0012\u0004\u0012\u00020N`O8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bP\u0010QR\u0016\u0010R\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bR\u0010SR\u0016\u0010T\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010SR\u0016\u0010U\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bU\u0010SR\u0016\u0010V\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010S\u00a8\u0006W"}, d2={"Lrtx/kimiko/utils/render/fonts/core/TextRenderState;", "Lnet/minecraft/SimpleGuiElementRenderState;", "Lorg/joml/Matrix3x2f;", "pose", "Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "page", "Lnet/minecraft/ScreenRect;", "scissorArea", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "<init>", "(Lorg/joml/Matrix3x2f;Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;Lnet/minecraft/ScreenRect;Lcom/mojang/blaze3d/pipeline/RenderPipeline;)V", "", "Lrtx/kimiko/utils/render/fonts/core/LayoutGlyph;", "glyphs", "", "offsetXIn", "offsetYIn", "", "colorTopLeft", "colorTopRight", "colorBottomRight", "colorBottomLeft", "rotationDegrees", "rotationOriginX", "rotationOriginY", "", "fadeLeft", "fadeRight", "fadeLeftX", "fadeRightX", "fadeWidth", "fadeLeftStrength", "fadeRightStrength", "snapOrigin", "", "add", "(Ljava/util/List;FFIIIIFFFZZFFFFFZ)V", "Lnet/minecraft/VertexConsumer;", "consumer", "buildVertices", "(Lnet/minecraft/VertexConsumer;)V", "x", "y", "originX", "originY", "s", "c", "", "rotate", "(FFFFFF)[F", "include", "(FF)V", "color", "fadeColor", "(IFZZFFFFF)I", "value", "smoothstep", "(F)F", "min", "max", "clamp", "(FFF)F", "u", "v", "vertex", "(Lnet/minecraft/VertexConsumer;FFFFI)V", "()Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lnet/minecraft/TextureSetup;", "textureSetup", "()Lnet/minecraft/TextureSetup;", "()Lnet/minecraft/ScreenRect;", "bounds", "Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "Lnet/minecraft/ScreenRect;", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lorg/joml/Matrix3x2f;", "Ljava/util/ArrayList;", "Lrtx/kimiko/utils/render/fonts/core/GlyphQuad;", "Lkotlin/collections/ArrayList;", "quads", "Ljava/util/ArrayList;", "minX", "F", "minY", "maxX", "maxY", "rtx.kimiko:kimiko"})
public final class TextRenderState
implements SimpleGuiElementRenderState {
    @NotNull
    private final GlyphAtlasPage page;
    @Nullable
    private final ScreenRect scissorArea;
    @NotNull
    private final RenderPipeline pipeline;
    @NotNull
    private final Matrix3x2f pose;
    @NotNull
    private final ArrayList<GlyphQuad> quads;
    private float minX;
    private float minY;
    private float maxX;
    private float maxY;

    public TextRenderState(@NotNull Matrix3x2f pose, @NotNull GlyphAtlasPage page, @Nullable ScreenRect scissorArea, @NotNull RenderPipeline pipeline) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)page, (String)"page");
        Intrinsics.checkNotNullParameter((Object)pipeline, (String)"pipeline");
        this.page = page;
        this.scissorArea = scissorArea;
        this.pipeline = pipeline;
        this.pose = PoseCache.snapshot(pose);
        this.quads = new ArrayList(128);
        this.minX = Float.MAX_VALUE;
        this.minY = Float.MAX_VALUE;
        this.maxX = -3.4028235E38f;
        this.maxY = -3.4028235E38f;
    }

    public final void add(@NotNull List<LayoutGlyph> glyphs, float offsetXIn, float offsetYIn, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float rotationDegrees, float rotationOriginX, float rotationOriginY, boolean fadeLeft, boolean fadeRight, float fadeLeftX, float fadeRightX, float fadeWidth, float fadeLeftStrength, float fadeRightStrength, boolean snapOrigin) {
        boolean axisAligned;
        Intrinsics.checkNotNullParameter(glyphs, (String)"glyphs");
        float offsetX = offsetXIn;
        float offsetY = offsetYIn;
        boolean bl = axisAligned = Math.abs(rotationDegrees) < 0.001f;
        if (axisAligned && snapOrigin) {
            offsetX = FontQuality.snapOrigin(offsetX);
            offsetY = FontQuality.snapOrigin(offsetY);
        }
        boolean snapGlyphs = axisAligned && snapOrigin && Render2DCoordinateSpace.zoomed();
        float radians = (float)Math.toRadians(rotationDegrees);
        float s = (float)Math.sin(radians);
        float c = (float)Math.cos(radians);
        for (LayoutGlyph glyph : glyphs) {
            float x0 = glyph.x0() + offsetX;
            float y0 = glyph.y0() + offsetY;
            float x1 = glyph.x1() + offsetX;
            float y1 = glyph.y1() + offsetY;
            if (snapGlyphs) {
                float snappedX = FontQuality.snapOrigin(x0);
                float snappedY = FontQuality.snapOrigin(y0);
                x1 += snappedX - x0;
                y1 += snappedY - y0;
                x0 = snappedX;
                y0 = snappedY;
            }
            float[] topLeft = this.rotate(x0, y0, rotationOriginX, rotationOriginY, s, c);
            float[] bottomLeft = this.rotate(x0, y1, rotationOriginX, rotationOriginY, s, c);
            float[] bottomRight = this.rotate(x1, y1, rotationOriginX, rotationOriginY, s, c);
            float[] topRight = this.rotate(x1, y0, rotationOriginX, rotationOriginY, s, c);
            this.quads.add(new GlyphQuad(topLeft[0], topLeft[1], bottomLeft[0], bottomLeft[1], bottomRight[0], bottomRight[1], topRight[0], topRight[1], glyph.u0(), glyph.v0(), glyph.u1(), glyph.v1(), this.fadeColor(colorTopLeft, topLeft[0], fadeLeft, fadeRight, fadeLeftX, fadeRightX, fadeWidth, fadeLeftStrength, fadeRightStrength), this.fadeColor(colorTopRight, topRight[0], fadeLeft, fadeRight, fadeLeftX, fadeRightX, fadeWidth, fadeLeftStrength, fadeRightStrength), this.fadeColor(colorBottomRight, bottomRight[0], fadeLeft, fadeRight, fadeLeftX, fadeRightX, fadeWidth, fadeLeftStrength, fadeRightStrength), this.fadeColor(colorBottomLeft, bottomLeft[0], fadeLeft, fadeRight, fadeLeftX, fadeRightX, fadeWidth, fadeLeftStrength, fadeRightStrength)));
            this.include(topLeft[0], topLeft[1]);
            this.include(bottomLeft[0], bottomLeft[1]);
            this.include(bottomRight[0], bottomRight[1]);
            this.include(topRight[0], topRight[1]);
        }
    }

    public void setupVertices(@NotNull VertexConsumer consumer) {
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        Iterator<GlyphQuad> iterator = this.quads.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<GlyphQuad> iterator2 = iterator;
        while (iterator2.hasNext()) {
            GlyphQuad quad = (GlyphQuad) (iterator2.next());
            this.vertex(consumer, quad.x0(), quad.y0(), quad.u0(), quad.v0(), quad.colorTopLeft());
            this.vertex(consumer, quad.x1(), quad.y1(), quad.u0(), quad.v1(), quad.colorBottomLeft());
            this.vertex(consumer, quad.x2(), quad.y2(), quad.u1(), quad.v1(), quad.colorBottomRight());
            this.vertex(consumer, quad.x3(), quad.y3(), quad.u1(), quad.v0(), quad.colorTopRight());
        }
    }

    private final float[] rotate(float x, float y, float originX, float originY, float s, float c) {
        float translatedX = x - originX;
        float translatedY = y - originY;
        float[] fArray = new float[]{originX + translatedX * c - translatedY * s, originY + translatedX * s + translatedY * c};
        return fArray;
    }

    private final void include(float x, float y) {
        this.minX = Math.min(this.minX, x);
        this.minY = Math.min(this.minY, y);
        this.maxX = Math.max(this.maxX, x);
        this.maxY = Math.max(this.maxY, y);
    }

    private final int fadeColor(int color, float x, boolean fadeLeft, boolean fadeRight, float fadeLeftX, float fadeRightX, float fadeWidth, float fadeLeftStrength, float fadeRightStrength) {
        float edgeAlpha;
        if (!fadeLeft && !fadeRight || fadeWidth <= 0.0f || fadeRightX <= fadeLeftX || fadeLeftStrength <= 0.001f && fadeRightStrength <= 0.001f) {
            return color;
        }
        float alpha = 1.0f;
        if (fadeLeft) {
            edgeAlpha = this.smoothstep(this.clamp((x - fadeLeftX) / fadeWidth, 0.0f, 1.0f));
            alpha = Math.min(alpha, 1.0f - this.clamp(fadeLeftStrength, 0.0f, 1.0f) * (1.0f - edgeAlpha));
        }
        if (fadeRight) {
            edgeAlpha = this.smoothstep(this.clamp((fadeRightX - x) / fadeWidth, 0.0f, 1.0f));
            alpha = Math.min(alpha, 1.0f - this.clamp(fadeRightStrength, 0.0f, 1.0f) * (1.0f - edgeAlpha));
        }
        int originalAlpha = color >>> 24 & 0xFF;
        return Math.round((float)originalAlpha * alpha) << 24 | color & 0xFFFFFF;
    }

    private final float smoothstep(float value) {
        return value * value * (3.0f - 2.0f * value);
    }

    private final float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    private final void vertex(VertexConsumer consumer, float x, float y, float u, float v, int color) {
        consumer.vertex((Matrix3x2fc)this.pose, x, y).texture(u, v).color(color);
    }

    @NotNull
    public RenderPipeline pipeline() {
        return this.pipeline;
    }

    @NotNull
    public TextureSetup textureSetup() {
        return this.page.textureSetup();
    }

    @Nullable
    public ScreenRect scissorArea() {
        return this.scissorArea;
    }

    @Nullable
    public ScreenRect bounds() {
        if (this.quads.isEmpty()) {
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
}

