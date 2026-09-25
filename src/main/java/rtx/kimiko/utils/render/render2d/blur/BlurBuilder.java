/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.render2d.blur;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.render2d.blur.BuiltBlur;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J-\u0010\t\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\nJ5\u0010\t\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\fJ\u0015\u0010\r\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0004\u00a2\u0006\u0004\b\r\u0010\u000eJ-\u0010\r\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004\u00a2\u0006\u0004\b\r\u0010\nJ\u0015\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0013\u0010\u000eJ\u0015\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0014\u0010\u000eJ\u0015\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0019\u001a\u00020\u0018\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u001bR\u0016\u0010\u0006\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u001bR\u0016\u0010\u0007\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u001bR\u0016\u0010\b\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001bR\u0016\u0010\u001d\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001bR\u0016\u0010\u001e\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001bR\u0016\u0010\u001f\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001bR\u0016\u0010\u0013\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u001bR\u0016\u0010\u0014\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u001bR\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010 \u00a8\u0006!"}, d2={"Lrtx/kimiko/utils/render/render2d/blur/BlurBuilder;", "", "<init>", "()V", "", "x", "y", "width", "height", "rectangle", "(FFFF)Lrtx/kimiko/utils/render/render2d/blur/BlurBuilder;", "cornerRadius", "(FFFFF)Lrtx/kimiko/utils/render/render2d/blur/BlurBuilder;", "radius", "(F)Lrtx/kimiko/utils/render/render2d/blur/BlurBuilder;", "topLeft", "topRight", "bottomRight", "bottomLeft", "smoothness", "blurRadius", "", "color", "(I)Lrtx/kimiko/utils/render/render2d/blur/BlurBuilder;", "Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;", "build", "()Lrtx/kimiko/utils/render/render2d/blur/BuiltBlur;", "F", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "I", "rtx.kimiko:kimiko"})
public final class BlurBuilder {
    private float x;
    private float y;
    private float width;
    private float height;
    private float radiusTopLeft;
    private float radiusTopRight;
    private float radiusBottomRight;
    private float radiusBottomLeft;
    private float smoothness = 1.0f;
    private float blurRadius = 16.0f;
    private int color = -1;

    @NotNull
    public final BlurBuilder rectangle(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        return this;
    }

    @NotNull
    public final BlurBuilder rectangle(float x, float y, float width, float height, float cornerRadius) {
        return this.rectangle(x, y, width, height).radius(cornerRadius);
    }

    @NotNull
    public final BlurBuilder radius(float radius) {
        this.radiusTopLeft = radius;
        this.radiusTopRight = radius;
        this.radiusBottomRight = radius;
        this.radiusBottomLeft = radius;
        return this;
    }

    @NotNull
    public final BlurBuilder radius(float topLeft, float topRight, float bottomRight, float bottomLeft) {
        this.radiusTopLeft = topLeft;
        this.radiusTopRight = topRight;
        this.radiusBottomRight = bottomRight;
        this.radiusBottomLeft = bottomLeft;
        return this;
    }

    @NotNull
    public final BlurBuilder smoothness(float smoothness) {
        this.smoothness = smoothness;
        return this;
    }

    @NotNull
    public final BlurBuilder blurRadius(float blurRadius) {
        this.blurRadius = blurRadius;
        return this;
    }

    @NotNull
    public final BlurBuilder color(int color) {
        this.color = color;
        return this;
    }

    @NotNull
    public final BuiltBlur build() {
        return new BuiltBlur(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.smoothness, this.blurRadius, this.color);
    }
}

