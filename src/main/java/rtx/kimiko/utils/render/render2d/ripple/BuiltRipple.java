/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 */
package rtx.kimiko.utils.render.render2d.ripple;

import kotlin.Metadata;
import kotlin.jvm.JvmField;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0012\u001a\u00020\u0010\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016Bi\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0012\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0015\u0010\u0018R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u001aR\u0019\u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u001aR\u0019\u0010\u0005\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u001aR\u0019\u0010\u0006\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u001aR\u0019\u0010\u0007\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u001aR\u0019\u0010\b\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\b\u0010\u001aR\u0019\u0010\t\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\t\u0010\u001aR\u0019\u0010\n\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\n\u0010\u001aR\u0019\u0010\u000b\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u001aR\u0019\u0010\f\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\f\u0010\u001aR\u0019\u0010\r\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\r\u0010\u001aR\u0019\u0010\u000e\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u001aR\u0019\u0010\u000f\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u001aR\u0019\u0010\u0011\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u001bR\u0019\u0010\u0012\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u001bR\u0019\u0010\u0014\u001a\u00020\u00138\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u001c\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/utils/render/render2d/ripple/BuiltRipple;", "", "", "x", "y", "width", "height", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "smoothness", "centerX", "centerY", "rippleRadius", "rippleSmoothness", "", "sourceColor", "targetColor", "", "textured", "<init>", "(FFFFFFFFFFFFFIIZ)V", "radius", "(FFFFFFFFFFII)V", "Lkotlin/jvm/JvmField;", "F", "I", "Z", "rtx.kimiko:kimiko"})
public final class BuiltRipple {
    @JvmField
    public final float x;
    @JvmField
    public final float y;
    @JvmField
    public final float width;
    @JvmField
    public final float height;
    @JvmField
    public final float radiusTopLeft;
    @JvmField
    public final float radiusTopRight;
    @JvmField
    public final float radiusBottomRight;
    @JvmField
    public final float radiusBottomLeft;
    @JvmField
    public final float smoothness;
    @JvmField
    public final float centerX;
    @JvmField
    public final float centerY;
    @JvmField
    public final float rippleRadius;
    @JvmField
    public final float rippleSmoothness;
    @JvmField
    public final int sourceColor;
    @JvmField
    public final int targetColor;
    @JvmField
    public final boolean textured;

    public BuiltRipple(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, float smoothness, float centerX, float centerY, float rippleRadius, float rippleSmoothness, int sourceColor, int targetColor, boolean textured) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radiusTopLeft = radiusTopLeft;
        this.radiusTopRight = radiusTopRight;
        this.radiusBottomRight = radiusBottomRight;
        this.radiusBottomLeft = radiusBottomLeft;
        this.smoothness = smoothness;
        this.centerX = centerX;
        this.centerY = centerY;
        this.rippleRadius = rippleRadius;
        this.rippleSmoothness = rippleSmoothness;
        this.sourceColor = sourceColor;
        this.targetColor = targetColor;
        this.textured = textured;
    }

    public BuiltRipple(float x, float y, float width, float height, float radius, float smoothness, float centerX, float centerY, float rippleRadius, float rippleSmoothness, int sourceColor, int targetColor) {
        this(x, y, width, height, radius, radius, radius, radius, smoothness, centerX, centerY, rippleRadius, rippleSmoothness, sourceColor, targetColor, false);
    }
}

