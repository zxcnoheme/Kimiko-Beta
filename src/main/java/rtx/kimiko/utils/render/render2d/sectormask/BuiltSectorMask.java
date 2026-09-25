/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.sectormask;

import com.mojang.blaze3d.textures.GpuTextureView;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001f\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B\u00a9\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\b\u0012\u0006\u0010\u0015\u001a\u00020\b\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001c\u001a\u00020\u001b\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b \u0010\u001fJ\u0010\u0010!\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b!\u0010\u001fJ\u0010\u0010\"\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\"\u0010\u001fJ\u0010\u0010#\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b#\u0010\u001fJ\u0010\u0010$\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b&\u0010\u001fJ\u0010\u0010'\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b'\u0010\u001fJ\u0010\u0010(\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b(\u0010\u001fJ\u0010\u0010)\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b)\u0010%J\u0010\u0010*\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b*\u0010\u001fJ\u0010\u0010+\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b+\u0010\u001fJ\u0010\u0010,\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b,\u0010\u001fJ\u0010\u0010-\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b-\u0010\u001fJ\u0010\u0010.\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b.\u0010\u001fJ\u0010\u0010/\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b/\u0010\u001fJ\u0010\u00100\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b0\u0010%J\u0010\u00101\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b1\u0010%J\u0010\u00102\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b2\u0010\u001fJ\u0012\u00103\u001a\u0004\u0018\u00010\u0017H\u00c6\u0003\u00a2\u0006\u0004\b3\u00104J\u00da\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u00022\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\b2\b\b\u0002\u0010\u0016\u001a\u00020\u00022\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u00c6\u0001\u00a2\u0006\u0004\b5\u00106J\u001b\u00108\u001a\u00020\u001b2\b\u00107\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b8\u00109J\u0011\u0010:\u001a\u00020\bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b:\u0010%J\u0011\u0010<\u001a\u00020;H\u00d6\u0081\u0004\u00a2\u0006\u0004\b<\u0010=R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010@\u001a\u0004\b\u0003\u0010\u001fR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010@\u001a\u0004\b\u0004\u0010\u001fR%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010@\u001a\u0004\b\u0005\u0010\u001fR%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010@\u001a\u0004\b\u0006\u0010\u001fR%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010@\u001a\u0004\b\u0007\u0010\u001fR%\u0010\t\u001a\u00020\b8\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010A\u001a\u0004\b\t\u0010%R%\u0010\n\u001a\u00020\u00028\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010@\u001a\u0004\b\n\u0010\u001fR%\u0010\u000b\u001a\u00020\u00028\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010@\u001a\u0004\b\u000b\u0010\u001fR%\u0010\f\u001a\u00020\u00028\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010@\u001a\u0004\b\f\u0010\u001fR%\u0010\r\u001a\u00020\b8\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010A\u001a\u0004\b\r\u0010%R%\u0010\u000e\u001a\u00020\u00028\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010@\u001a\u0004\b\u000e\u0010\u001fR%\u0010\u000f\u001a\u00020\u00028\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010@\u001a\u0004\b\u000f\u0010\u001fR%\u0010\u0010\u001a\u00020\u00028\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010@\u001a\u0004\b\u0010\u0010\u001fR%\u0010\u0011\u001a\u00020\u00028\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\u0011\u00a2\u0006\f\n\u0004\b\u0011\u0010@\u001a\u0004\b\u0011\u0010\u001fR%\u0010\u0012\u001a\u00020\u00028\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\u0012\u00a2\u0006\f\n\u0004\b\u0012\u0010@\u001a\u0004\b\u0012\u0010\u001fR%\u0010\u0013\u001a\u00020\u00028\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\u0013\u00a2\u0006\f\n\u0004\b\u0013\u0010@\u001a\u0004\b\u0013\u0010\u001fR%\u0010\u0014\u001a\u00020\b8\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\u0014\u00a2\u0006\f\n\u0004\b\u0014\u0010A\u001a\u0004\b\u0014\u0010%R%\u0010\u0015\u001a\u00020\b8\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\u0015\u00a2\u0006\f\n\u0004\b\u0015\u0010A\u001a\u0004\b\u0015\u0010%R%\u0010\u0016\u001a\u00020\u00028\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\u0016\u00a2\u0006\f\n\u0004\b\u0016\u0010@\u001a\u0004\b\u0016\u0010\u001fR'\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0007z\f\b>\u0012\b\b?\u0012\u0004\b\b(\u0018\u00a2\u0006\f\n\u0004\b\u0018\u0010B\u001a\u0004\b\u0018\u00104\u00a8\u0006C"}, d2={"Lrtx/kimiko/utils/render/render2d/sectormask/BuiltSectorMask;", "", "", "x", "y", "size", "innerRadius", "outerRadius", "", "sectorCount", "gapRadians", "corner", "feather", "hoverIndex", "hoverGrow", "hoverShrink", "hoverZoom", "ringRadius", "cellWidth", "cellHeight", "columns", "rows", "alpha", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "texture", "<init>", "(FFFFFIFFFIFFFFFFIIFLcom/mojang/blaze3d/textures/GpuTextureView;)V", "", "visible", "()Z", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "()I", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "()Lcom/mojang/blaze3d/textures/GpuTextureView;", "copy", "(FFFFFIFFFIFFFFFFIIFLcom/mojang/blaze3d/textures/GpuTextureView;)Lrtx/kimiko/utils/render/render2d/sectormask/BuiltSectorMask;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "rtx.kimiko:kimiko"})
public final class BuiltSectorMask {
    private final float x;
    private final float y;
    private final float size;
    private final float innerRadius;
    private final float outerRadius;
    private final int sectorCount;
    private final float gapRadians;
    private final float corner;
    private final float feather;
    private final int hoverIndex;
    private final float hoverGrow;
    private final float hoverShrink;
    private final float hoverZoom;
    private final float ringRadius;
    private final float cellWidth;
    private final float cellHeight;
    private final int columns;
    private final int rows;
    private final float alpha;
    @Nullable
    private final GpuTextureView texture;

    public BuiltSectorMask(float x, float y, float size, float innerRadius, float outerRadius, int sectorCount, float gapRadians, float corner, float feather, int hoverIndex, float hoverGrow, float hoverShrink, float hoverZoom, float ringRadius, float cellWidth, float cellHeight, int columns, int rows, float alpha, @Nullable GpuTextureView texture) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
        this.sectorCount = sectorCount;
        this.gapRadians = gapRadians;
        this.corner = corner;
        this.feather = feather;
        this.hoverIndex = hoverIndex;
        this.hoverGrow = hoverGrow;
        this.hoverShrink = hoverShrink;
        this.hoverZoom = hoverZoom;
        this.ringRadius = ringRadius;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.columns = columns;
        this.rows = rows;
        this.alpha = alpha;
        this.texture = texture;
    }

    @JvmName(name="x")
    public final float x() {
        return this.x;
    }

    @JvmName(name="y")
    public final float y() {
        return this.y;
    }

    @JvmName(name="size")
    public final float size() {
        return this.size;
    }

    @JvmName(name="innerRadius")
    public final float innerRadius() {
        return this.innerRadius;
    }

    @JvmName(name="outerRadius")
    public final float outerRadius() {
        return this.outerRadius;
    }

    @JvmName(name="sectorCount")
    public final int sectorCount() {
        return this.sectorCount;
    }

    @JvmName(name="gapRadians")
    public final float gapRadians() {
        return this.gapRadians;
    }

    @JvmName(name="corner")
    public final float corner() {
        return this.corner;
    }

    @JvmName(name="feather")
    public final float feather() {
        return this.feather;
    }

    @JvmName(name="hoverIndex")
    public final int hoverIndex() {
        return this.hoverIndex;
    }

    @JvmName(name="hoverGrow")
    public final float hoverGrow() {
        return this.hoverGrow;
    }

    @JvmName(name="hoverShrink")
    public final float hoverShrink() {
        return this.hoverShrink;
    }

    @JvmName(name="hoverZoom")
    public final float hoverZoom() {
        return this.hoverZoom;
    }

    @JvmName(name="ringRadius")
    public final float ringRadius() {
        return this.ringRadius;
    }

    @JvmName(name="cellWidth")
    public final float cellWidth() {
        return this.cellWidth;
    }

    @JvmName(name="cellHeight")
    public final float cellHeight() {
        return this.cellHeight;
    }

    @JvmName(name="columns")
    public final int columns() {
        return this.columns;
    }

    @JvmName(name="rows")
    public final int rows() {
        return this.rows;
    }

    @JvmName(name="alpha")
    public final float alpha() {
        return this.alpha;
    }

    @JvmName(name="texture")
    @Nullable
    public final GpuTextureView texture() {
        return this.texture;
    }

    public final boolean visible() {
        return this.size > 0.0f && this.sectorCount > 0 && this.outerRadius > this.innerRadius && this.alpha > 0.0f && this.cellWidth > 0.0f && this.cellHeight > 0.0f && this.columns > 0 && this.rows > 0 && this.texture != null;
    }

    public final float component1() {
        return this.x;
    }

    public final float component2() {
        return this.y;
    }

    public final float component3() {
        return this.size;
    }

    public final float component4() {
        return this.innerRadius;
    }

    public final float component5() {
        return this.outerRadius;
    }

    public final int component6() {
        return this.sectorCount;
    }

    public final float component7() {
        return this.gapRadians;
    }

    public final float component8() {
        return this.corner;
    }

    public final float component9() {
        return this.feather;
    }

    public final int component10() {
        return this.hoverIndex;
    }

    public final float component11() {
        return this.hoverGrow;
    }

    public final float component12() {
        return this.hoverShrink;
    }

    public final float component13() {
        return this.hoverZoom;
    }

    public final float component14() {
        return this.ringRadius;
    }

    public final float component15() {
        return this.cellWidth;
    }

    public final float component16() {
        return this.cellHeight;
    }

    public final int component17() {
        return this.columns;
    }

    public final int component18() {
        return this.rows;
    }

    public final float component19() {
        return this.alpha;
    }

    @Nullable
    public final GpuTextureView component20() {
        return this.texture;
    }

    @NotNull
    public final BuiltSectorMask copy(float x, float y, float size, float innerRadius, float outerRadius, int sectorCount, float gapRadians, float corner, float feather, int hoverIndex, float hoverGrow, float hoverShrink, float hoverZoom, float ringRadius, float cellWidth, float cellHeight, int columns, int rows, float alpha, @Nullable GpuTextureView texture) {
        return new BuiltSectorMask(x, y, size, innerRadius, outerRadius, sectorCount, gapRadians, corner, feather, hoverIndex, hoverGrow, hoverShrink, hoverZoom, ringRadius, cellWidth, cellHeight, columns, rows, alpha, texture);
    }

    public static /* synthetic */ BuiltSectorMask copy$default(BuiltSectorMask builtSectorMask, float f, float f2, float f3, float f4, float f5, int n, float f6, float f7, float f8, int n2, float f9, float f10, float f11, float f12, float f13, float f14, int n3, int n4, float f15, GpuTextureView gpuTextureView, int n5, Object object) {
        if ((n5 & 1) != 0) {
            f = builtSectorMask.x;
        }
        if ((n5 & 2) != 0) {
            f2 = builtSectorMask.y;
        }
        if ((n5 & 4) != 0) {
            f3 = builtSectorMask.size;
        }
        if ((n5 & 8) != 0) {
            f4 = builtSectorMask.innerRadius;
        }
        if ((n5 & 0x10) != 0) {
            f5 = builtSectorMask.outerRadius;
        }
        if ((n5 & 0x20) != 0) {
            n = builtSectorMask.sectorCount;
        }
        if ((n5 & 0x40) != 0) {
            f6 = builtSectorMask.gapRadians;
        }
        if ((n5 & 0x80) != 0) {
            f7 = builtSectorMask.corner;
        }
        if ((n5 & 0x100) != 0) {
            f8 = builtSectorMask.feather;
        }
        if ((n5 & 0x200) != 0) {
            n2 = builtSectorMask.hoverIndex;
        }
        if ((n5 & 0x400) != 0) {
            f9 = builtSectorMask.hoverGrow;
        }
        if ((n5 & 0x800) != 0) {
            f10 = builtSectorMask.hoverShrink;
        }
        if ((n5 & 0x1000) != 0) {
            f11 = builtSectorMask.hoverZoom;
        }
        if ((n5 & 0x2000) != 0) {
            f12 = builtSectorMask.ringRadius;
        }
        if ((n5 & 0x4000) != 0) {
            f13 = builtSectorMask.cellWidth;
        }
        if ((n5 & 0x8000) != 0) {
            f14 = builtSectorMask.cellHeight;
        }
        if ((n5 & 0x10000) != 0) {
            n3 = builtSectorMask.columns;
        }
        if ((n5 & 0x20000) != 0) {
            n4 = builtSectorMask.rows;
        }
        if ((n5 & 0x40000) != 0) {
            f15 = builtSectorMask.alpha;
        }
        if ((n5 & 0x80000) != 0) {
            gpuTextureView = builtSectorMask.texture;
        }
        return builtSectorMask.copy(f, f2, f3, f4, f5, n, f6, f7, f8, n2, f9, f10, f11, f12, f13, f14, n3, n4, f15, gpuTextureView);
    }

    @NotNull
    public String toString() {
        return "BuiltSectorMask(x=" + this.x + ", y=" + this.y + ", size=" + this.size + ", innerRadius=" + this.innerRadius + ", outerRadius=" + this.outerRadius + ", sectorCount=" + this.sectorCount + ", gapRadians=" + this.gapRadians + ", corner=" + this.corner + ", feather=" + this.feather + ", hoverIndex=" + this.hoverIndex + ", hoverGrow=" + this.hoverGrow + ", hoverShrink=" + this.hoverShrink + ", hoverZoom=" + this.hoverZoom + ", ringRadius=" + this.ringRadius + ", cellWidth=" + this.cellWidth + ", cellHeight=" + this.cellHeight + ", columns=" + this.columns + ", rows=" + this.rows + ", alpha=" + this.alpha + ", texture=" + this.texture + ")";
    }

    public int hashCode() {
        int result = Float.hashCode(this.x);
        result = result * 31 + Float.hashCode(this.y);
        result = result * 31 + Float.hashCode(this.size);
        result = result * 31 + Float.hashCode(this.innerRadius);
        result = result * 31 + Float.hashCode(this.outerRadius);
        result = result * 31 + Integer.hashCode(this.sectorCount);
        result = result * 31 + Float.hashCode(this.gapRadians);
        result = result * 31 + Float.hashCode(this.corner);
        result = result * 31 + Float.hashCode(this.feather);
        result = result * 31 + Integer.hashCode(this.hoverIndex);
        result = result * 31 + Float.hashCode(this.hoverGrow);
        result = result * 31 + Float.hashCode(this.hoverShrink);
        result = result * 31 + Float.hashCode(this.hoverZoom);
        result = result * 31 + Float.hashCode(this.ringRadius);
        result = result * 31 + Float.hashCode(this.cellWidth);
        result = result * 31 + Float.hashCode(this.cellHeight);
        result = result * 31 + Integer.hashCode(this.columns);
        result = result * 31 + Integer.hashCode(this.rows);
        result = result * 31 + Float.hashCode(this.alpha);
        result = result * 31 + (this.texture == null ? 0 : this.texture.hashCode());
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltSectorMask)) {
            return false;
        }
        BuiltSectorMask builtSectorMask = (BuiltSectorMask)other;
        if (Float.compare(this.x, builtSectorMask.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtSectorMask.y) != 0) {
            return false;
        }
        if (Float.compare(this.size, builtSectorMask.size) != 0) {
            return false;
        }
        if (Float.compare(this.innerRadius, builtSectorMask.innerRadius) != 0) {
            return false;
        }
        if (Float.compare(this.outerRadius, builtSectorMask.outerRadius) != 0) {
            return false;
        }
        if (this.sectorCount != builtSectorMask.sectorCount) {
            return false;
        }
        if (Float.compare(this.gapRadians, builtSectorMask.gapRadians) != 0) {
            return false;
        }
        if (Float.compare(this.corner, builtSectorMask.corner) != 0) {
            return false;
        }
        if (Float.compare(this.feather, builtSectorMask.feather) != 0) {
            return false;
        }
        if (this.hoverIndex != builtSectorMask.hoverIndex) {
            return false;
        }
        if (Float.compare(this.hoverGrow, builtSectorMask.hoverGrow) != 0) {
            return false;
        }
        if (Float.compare(this.hoverShrink, builtSectorMask.hoverShrink) != 0) {
            return false;
        }
        if (Float.compare(this.hoverZoom, builtSectorMask.hoverZoom) != 0) {
            return false;
        }
        if (Float.compare(this.ringRadius, builtSectorMask.ringRadius) != 0) {
            return false;
        }
        if (Float.compare(this.cellWidth, builtSectorMask.cellWidth) != 0) {
            return false;
        }
        if (Float.compare(this.cellHeight, builtSectorMask.cellHeight) != 0) {
            return false;
        }
        if (this.columns != builtSectorMask.columns) {
            return false;
        }
        if (this.rows != builtSectorMask.rows) {
            return false;
        }
        if (Float.compare(this.alpha, builtSectorMask.alpha) != 0) {
            return false;
        }
        return Intrinsics.areEqual((Object)this.texture, (Object)builtSectorMask.texture);
    }
}

