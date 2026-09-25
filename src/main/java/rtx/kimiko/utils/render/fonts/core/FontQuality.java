/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.fonts.core;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J#\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0011\u0010\u000bJ\u001b\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0012\u0010\u000bR\u0014\u0010\u0013\u001a\u00020\r8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\r8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\r8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0014\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/utils/render/fonts/core/FontQuality;", "", "<init>", "()V", "", "size", "density", "Lkotlin/jvm/JvmStatic;", "rasterScaleFor", "(FF)F", "baseOversampleFor", "(F)F", "rasterScale", "", "glyphPadding", "(F)I", "value", "snapOrigin", "coverageWeight", "ATLAS_SIZE", "I", "ATLAS_GAP", "MAX_LAYOUT_CACHE", "rtx.kimiko:kimiko"})
public final class FontQuality {
    @NotNull
    public static final FontQuality INSTANCE = new FontQuality();
    public static final int ATLAS_SIZE = 2048;
    public static final int ATLAS_GAP = 2;
    public static final int MAX_LAYOUT_CACHE = 2048;

    private FontQuality() {
    }

    @JvmStatic
    public static final float rasterScaleFor(float size, float density) {
        float base = INSTANCE.baseOversampleFor(size);
        if (density <= 0.0f || Math.abs(density - 1.0f) < 1.0E-4f) {
            return base;
        }
        float devicePerDesign = Render2DCoordinateSpace.designGuiScale() * density;
        return Math.max(1.0f, Math.min(12.0f, Math.min(base * density, devicePerDesign)));
    }

    private final float baseOversampleFor(float size) {
        if (size <= 10.0f) {
            return 6.0f;
        }
        if (size <= 14.0f) {
            return 5.0f;
        }
        if (size <= 28.0f) {
            return 4.0f;
        }
        if (size <= 64.0f) {
            return 3.0f;
        }
        return 2.0f;
    }

    @JvmStatic
    public static final int glyphPadding(float rasterScale) {
        return Math.max(4, (int)Math.ceil(rasterScale) + 2);
    }

    @JvmStatic
    public static final float snapOrigin(float value) {
        float scale = Render2DCoordinateSpace.pixelScale();
        return (float)Math.round(value * scale) / scale;
    }

    @JvmStatic
    public static final float coverageWeight(float size) {
        if (size <= 8.0f) {
            return 0.32f;
        }
        if (size <= 10.0f) {
            return 0.22f;
        }
        if (size <= 12.0f) {
            return 0.14f;
        }
        if (size <= 14.0f) {
            return 0.08f;
        }
        return 0.0f;
    }
}

