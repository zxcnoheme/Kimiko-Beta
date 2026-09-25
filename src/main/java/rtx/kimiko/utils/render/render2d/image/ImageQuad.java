/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 */
package rtx.kimiko.utils.render.render2d.image;

import kotlin.Metadata;
import kotlin.jvm.JvmField;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u00af\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\f\u0012\u0006\u0010\u0010\u001a\u00020\f\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u00a2\u0006\u0004\b\u001a\u0010\u001bBQ\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u001c\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\f\u0012\u0006\u0010\u0010\u001a\u00020\f\u00a2\u0006\u0004\b\u001a\u0010\u001dR\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u001fR\u0019\u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u001fR\u0019\u0010\u0005\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u001fR\u0019\u0010\u0006\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u001fR\u0019\u0010\u0007\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u001fR\u0019\u0010\b\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\b\u0010\u001fR\u0019\u0010\t\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\t\u0010\u001fR\u0019\u0010\n\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\n\u0010\u001fR\u0019\u0010\u000b\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u001fR\u0019\u0010\r\u001a\u00020\f8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\r\u0010 R\u0019\u0010\u000e\u001a\u00020\f8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u000e\u0010 R\u0019\u0010\u000f\u001a\u00020\f8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010 R\u0019\u0010\u0010\u001a\u00020\f8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u0010\u0010 R\u0019\u0010\u0011\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u001fR\u0019\u0010\u0012\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u001fR\u0019\u0010\u0013\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u001fR\u0019\u0010\u0014\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u001fR\u0019\u0010\u0015\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u001fR\u0019\u0010\u0016\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u001fR\u0019\u0010\u0017\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u001fR\u0019\u0010\u0019\u001a\u00020\u00188\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010!\u00a8\u0006\""}, d2={"Lrtx/kimiko/utils/render/render2d/image/ImageQuad;", "", "", "x", "y", "width", "height", "radiusTL", "radiusTR", "radiusBR", "radiusBL", "smoothness", "", "colorTopLeft", "colorTopRight", "colorBottomRight", "colorBottomLeft", "u0", "v0", "u1", "v1", "rotationDegrees", "rotationOriginX", "rotationOriginY", "", "hq", "<init>", "(FFFFFFFFFIIIIFFFFFFFZ)V", "radius", "(FFFFFIIII)V", "Lkotlin/jvm/JvmField;", "F", "I", "Z", "rtx.kimiko:kimiko"})
public final class ImageQuad {
    @JvmField
    public final float x;
    @JvmField
    public final float y;
    @JvmField
    public final float width;
    @JvmField
    public final float height;
    @JvmField
    public final float radiusTL;
    @JvmField
    public final float radiusTR;
    @JvmField
    public final float radiusBR;
    @JvmField
    public final float radiusBL;
    @JvmField
    public final float smoothness;
    @JvmField
    public final int colorTopLeft;
    @JvmField
    public final int colorTopRight;
    @JvmField
    public final int colorBottomRight;
    @JvmField
    public final int colorBottomLeft;
    @JvmField
    public final float u0;
    @JvmField
    public final float v0;
    @JvmField
    public final float u1;
    @JvmField
    public final float v1;
    @JvmField
    public final float rotationDegrees;
    @JvmField
    public final float rotationOriginX;
    @JvmField
    public final float rotationOriginY;
    @JvmField
    public final boolean hq;

    public ImageQuad(float x, float y, float width, float height, float radiusTL, float radiusTR, float radiusBR, float radiusBL, float smoothness, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float u0, float v0, float u1, float v1, float rotationDegrees, float rotationOriginX, float rotationOriginY, boolean hq) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radiusTL = radiusTL;
        this.radiusTR = radiusTR;
        this.radiusBR = radiusBR;
        this.radiusBL = radiusBL;
        this.smoothness = smoothness;
        this.colorTopLeft = colorTopLeft;
        this.colorTopRight = colorTopRight;
        this.colorBottomRight = colorBottomRight;
        this.colorBottomLeft = colorBottomLeft;
        this.u0 = u0;
        this.v0 = v0;
        this.u1 = u1;
        this.v1 = v1;
        this.rotationDegrees = rotationDegrees;
        this.rotationOriginX = rotationOriginX;
        this.rotationOriginY = rotationOriginY;
        this.hq = hq;
    }

    public ImageQuad(float x, float y, float width, float height, float radius, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft) {
        this(x, y, width, height, radius, radius, radius, radius, 0.0f, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, false);
    }
}

