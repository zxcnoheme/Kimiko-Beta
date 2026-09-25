/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.render.state.special.SpecialGuiElementRenderState
 *  net.minecraft.client.gui.ScreenRect
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 */
package rtx.kimiko.api.modules.impl.Visuals.emotions;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.render.state.special.SpecialGuiElementRenderState;
import net.minecraft.client.gui.ScreenRect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import rtx.kimiko.api.modules.impl.Visuals.emotions.Emotion;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u00f7\u0001\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\u0006\u0010\u0013\u001a\u00020\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u0005\u0012\u0006\u0010\u0015\u001a\u00020\u0005\u0012\u0006\u0010\u0016\u001a\u00020\u0005\u0012\u0006\u0010\u0017\u001a\u00020\u0005\u0012\u0006\u0010\u0018\u001a\u00020\u0005\u0012\u0006\u0010\u0019\u001a\u00020\u0005\u0012\u0006\u0010\u001a\u001a\u00020\u0005\u0012\u0006\u0010\u001b\u001a\u00020\u000e\u0012\u0006\u0010\u001c\u001a\u00020\u000e\u0012\u0006\u0010\u001e\u001a\u00020\u001d\u0012\u0006\u0010\u001f\u001a\u00020\u000e\u0012\u0006\u0010 \u001a\u00020\u000e\u0012\u0006\u0010!\u001a\u00020\u000e\u0012\u0006\u0010\"\u001a\u00020\u000e\u0012\b\u0010$\u001a\u0004\u0018\u00010#\u00a2\u0006\u0004\b%\u0010&J\u000f\u0010'\u001a\u00020\u000eH\u0016\u00a2\u0006\u0004\b'\u0010(J\u000f\u0010)\u001a\u00020\u000eH\u0016\u00a2\u0006\u0004\b)\u0010(J\u000f\u0010*\u001a\u00020\u000eH\u0016\u00a2\u0006\u0004\b*\u0010(J\u000f\u0010+\u001a\u00020\u000eH\u0016\u00a2\u0006\u0004\b+\u0010(J\u000f\u0010,\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b,\u0010-J\u000f\u0010.\u001a\u00020\u001dH\u0016\u00a2\u0006\u0004\b.\u0010/J\u0011\u00100\u001a\u0004\u0018\u00010#H\u0016\u00a2\u0006\u0004\b0\u00101J\u000f\u00102\u001a\u00020#H\u0016\u00a2\u0006\u0004\b2\u00101R\u001f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u0004\u00104R\u0019\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u0006\u00105R\u0019\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u0007\u00105R\u0019\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\b\u00105R\u0019\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\t\u00105R\u0019\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\n\u00105R\u0019\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u000b\u00105R\u0019\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\f\u00105R\u0019\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\r\u00105R\u0019\u0010\u000f\u001a\u00020\u000e8\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u000f\u00106R\u0019\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u0010\u00105R\u0019\u0010\u0011\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u0011\u00105R\u0019\u0010\u0012\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u0012\u00105R\u0019\u0010\u0013\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u0013\u00105R\u0019\u0010\u0014\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u0014\u00105R\u0019\u0010\u0015\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u0015\u00105R\u0019\u0010\u0016\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u0016\u00105R\u0019\u0010\u0017\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u0017\u00105R\u0019\u0010\u0018\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u0018\u00105R\u0019\u0010\u0019\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u0019\u00105R\u0019\u0010\u001a\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u001a\u00105R\u0019\u0010\u001b\u001a\u00020\u000e8\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u001b\u00106R\u0019\u0010\u001c\u001a\u00020\u000e8\u0006X\u0087\u0004\u0092\u0002\u0002\b3\u00a2\u0006\u0006\n\u0004\b\u001c\u00106R\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001e\u00107R\u0014\u0010\u001f\u001a\u00020\u000e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u00106R\u0014\u0010 \u001a\u00020\u000e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u00106R\u0014\u0010!\u001a\u00020\u000e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u00106R\u0014\u0010\"\u001a\u00020\u000e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u00106R\u0016\u0010$\u001a\u0004\u0018\u00010#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u00108R\u0014\u00109\u001a\u00020#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u00108\u00a8\u0006:"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionPreviewState;", "Lnet/minecraft/SpecialGuiElementRenderState;", "", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "emotions", "", "time", "ringRadius", "modelScale", "modelDrop", "innerRadius", "outerRadius", "gapRadians", "corner", "", "hoverIndex", "hoverGrow", "hoverShrink", "hoverZoom", "alpha", "designX", "designY", "designSize", "cellWidth", "cellHeight", "cellWidthDesign", "cellHeightDesign", "columns", "rows", "Lorg/joml/Matrix3x2f;", "poseVal", "x0Val", "y0Val", "x1Val", "y1Val", "Lnet/minecraft/ScreenRect;", "scissorAreaVal", "<init>", "(Ljava/util/List;FFFFFFFFIFFFFFFFFFFFIILorg/joml/Matrix3x2f;IIIILnet/minecraft/ScreenRect;)V", "x0", "()I", "x1", "y0", "y1", "scale", "()F", "pose", "()Lorg/joml/Matrix3x2f;", "scissorArea", "()Lnet/minecraft/ScreenRect;", "bounds", "Lkotlin/jvm/JvmField;", "Ljava/util/List;", "F", "I", "Lorg/joml/Matrix3x2f;", "Lnet/minecraft/ScreenRect;", "boundsVal", "rtx.kimiko:kimiko"})
public final class EmotionPreviewState
implements SpecialGuiElementRenderState {
    @JvmField
    @NotNull
    public final List<Emotion> emotions;
    @JvmField
    public final float time;
    @JvmField
    public final float ringRadius;
    @JvmField
    public final float modelScale;
    @JvmField
    public final float modelDrop;
    @JvmField
    public final float innerRadius;
    @JvmField
    public final float outerRadius;
    @JvmField
    public final float gapRadians;
    @JvmField
    public final float corner;
    @JvmField
    public final int hoverIndex;
    @JvmField
    public final float hoverGrow;
    @JvmField
    public final float hoverShrink;
    @JvmField
    public final float hoverZoom;
    @JvmField
    public final float alpha;
    @JvmField
    public final float designX;
    @JvmField
    public final float designY;
    @JvmField
    public final float designSize;
    @JvmField
    public final float cellWidth;
    @JvmField
    public final float cellHeight;
    @JvmField
    public final float cellWidthDesign;
    @JvmField
    public final float cellHeightDesign;
    @JvmField
    public final int columns;
    @JvmField
    public final int rows;
    @NotNull
    private final Matrix3x2f poseVal;
    private final int x0Val;
    private final int y0Val;
    private final int x1Val;
    private final int y1Val;
    @Nullable
    private final ScreenRect scissorAreaVal;
    @NotNull
    private final ScreenRect boundsVal;

    public EmotionPreviewState(@NotNull List<Emotion> emotions, float time, float ringRadius, float modelScale, float modelDrop, float innerRadius, float outerRadius, float gapRadians, float corner, int hoverIndex, float hoverGrow, float hoverShrink, float hoverZoom, float alpha, float designX, float designY, float designSize, float cellWidth, float cellHeight, float cellWidthDesign, float cellHeightDesign, int columns, int rows, @NotNull Matrix3x2f poseVal, int x0Val, int y0Val, int x1Val, int y1Val, @Nullable ScreenRect scissorAreaVal) {
        Intrinsics.checkNotNullParameter(emotions, (String)"emotions");
        Intrinsics.checkNotNullParameter((Object)poseVal, (String)"poseVal");
        this.emotions = emotions;
        this.time = time;
        this.ringRadius = ringRadius;
        this.modelScale = modelScale;
        this.modelDrop = modelDrop;
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
        this.gapRadians = gapRadians;
        this.corner = corner;
        this.hoverIndex = hoverIndex;
        this.hoverGrow = hoverGrow;
        this.hoverShrink = hoverShrink;
        this.hoverZoom = hoverZoom;
        this.alpha = alpha;
        this.designX = designX;
        this.designY = designY;
        this.designSize = designSize;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.cellWidthDesign = cellWidthDesign;
        this.cellHeightDesign = cellHeightDesign;
        this.columns = columns;
        this.rows = rows;
        this.poseVal = poseVal;
        this.x0Val = x0Val;
        this.y0Val = y0Val;
        this.x1Val = x1Val;
        this.y1Val = y1Val;
        this.scissorAreaVal = scissorAreaVal;
        ScreenRect screenRect2 = SpecialGuiElementRenderState.createBounds((int)this.x0Val, (int)this.y0Val, (int)this.x1Val, (int)this.y1Val, (ScreenRect)this.scissorAreaVal);
        if (screenRect2 == null) {
            screenRect2 = new ScreenRect(this.x0Val, this.y0Val, Math.max(0, this.x1Val - this.x0Val), Math.max(0, this.y1Val - this.y0Val));
        }
        this.boundsVal = screenRect2;
    }

    public int x1() {
        return this.x0Val;
    }

    public int x2() {
        return this.x1Val;
    }

    public int y1() {
        return this.y0Val;
    }

    public int y2() {
        return this.y1Val;
    }

    public float scale() {
        return 1.0f;
    }

    @NotNull
    public Matrix3x2f pose() {
        return this.poseVal;
    }

    @Nullable
    public ScreenRect scissorArea() {
        return this.scissorAreaVal;
    }

    @NotNull
    public ScreenRect bounds() {
        return this.boundsVal;
    }
}

