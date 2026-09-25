/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.rectangle.tablepanel;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u001d\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 I2\u00020\u0001:\u0001IB\u009f\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\r\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018BY\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\r\u00a2\u0006\u0004\b\u0017\u0010\u001aJ\u001d\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010 \u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0002\u00a2\u0006\u0004\b \u0010!J\u0015\u0010\"\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0002\u00a2\u0006\u0004\b\"\u0010!J\u0015\u0010#\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0002\u00a2\u0006\u0004\b#\u0010!J\r\u0010%\u001a\u00020$\u00a2\u0006\u0004\b%\u0010&J\u0010\u0010'\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b'\u0010(J\u0010\u0010)\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b)\u0010(J\u0010\u0010*\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b*\u0010(J\u0010\u0010+\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b+\u0010(J\u0010\u0010,\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b,\u0010(J\u0010\u0010-\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b-\u0010(J\u0010\u0010.\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b.\u0010(J\u0010\u0010/\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b/\u0010(J\u0010\u00100\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b0\u0010(J\u0010\u00101\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b1\u0010(J\u0010\u00102\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\b2\u00103J\u0010\u00104\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\b4\u00103J\u0010\u00105\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\b5\u00103J\u0010\u00106\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\b6\u00103J\u0010\u00107\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b7\u0010(J\u0010\u00108\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b8\u0010(J\u0010\u00109\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b9\u0010(J\u0010\u0010:\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b:\u0010(J\u0010\u0010;\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b;\u0010(J\u00ce\u0001\u0010<\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b<\u0010=J\u001b\u0010?\u001a\u00020$2\b\u0010>\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b?\u0010@J\u0011\u0010A\u001a\u00020\rH\u00d6\u0081\u0004\u00a2\u0006\u0004\bA\u00103J\u0011\u0010C\u001a\u00020BH\u00d6\u0081\u0004\u00a2\u0006\u0004\bC\u0010DR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010G\u001a\u0004\b\u0003\u0010(R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010G\u001a\u0004\b\u0004\u0010(R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010G\u001a\u0004\b\u0005\u0010(R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010G\u001a\u0004\b\u0006\u0010(R%\u0010\u0007\u001a\u00020\u00028\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010G\u001a\u0004\b\u0007\u0010(R%\u0010\b\u001a\u00020\u00028\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010G\u001a\u0004\b\b\u0010(R%\u0010\t\u001a\u00020\u00028\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010G\u001a\u0004\b\t\u0010(R%\u0010\n\u001a\u00020\u00028\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010G\u001a\u0004\b\n\u0010(R%\u0010\u000b\u001a\u00020\u00028\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010G\u001a\u0004\b\u000b\u0010(R%\u0010\f\u001a\u00020\u00028\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010G\u001a\u0004\b\f\u0010(R%\u0010\u000e\u001a\u00020\r8\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010H\u001a\u0004\b\u000e\u00103R%\u0010\u000f\u001a\u00020\r8\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010H\u001a\u0004\b\u000f\u00103R%\u0010\u0010\u001a\u00020\r8\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010H\u001a\u0004\b\u0010\u00103R%\u0010\u0011\u001a\u00020\r8\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0011\u00a2\u0006\f\n\u0004\b\u0011\u0010H\u001a\u0004\b\u0011\u00103R%\u0010\u0012\u001a\u00020\u00028\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0012\u00a2\u0006\f\n\u0004\b\u0012\u0010G\u001a\u0004\b\u0012\u0010(R%\u0010\u0013\u001a\u00020\u00028\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0013\u00a2\u0006\f\n\u0004\b\u0013\u0010G\u001a\u0004\b\u0013\u0010(R%\u0010\u0014\u001a\u00020\u00028\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0014\u00a2\u0006\f\n\u0004\b\u0014\u0010G\u001a\u0004\b\u0014\u0010(R%\u0010\u0015\u001a\u00020\u00028\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0015\u00a2\u0006\f\n\u0004\b\u0015\u0010G\u001a\u0004\b\u0015\u0010(R%\u0010\u0016\u001a\u00020\u00028\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0016\u00a2\u0006\f\n\u0004\b\u0016\u0010G\u001a\u0004\b\u0016\u0010(\u00a8\u0006J"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/tablepanel/BuiltTablePanel;", "", "", "x", "y", "width", "height", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "smoothness", "frameThickness", "", "feltTop", "feltBottom", "woodLight", "woodDark", "cellSize", "cellStrength", "seed", "innerTint", "innerRadius", "<init>", "(FFFFFFFFFFIIIIFFFFF)V", "radius", "(FFFFFFIIII)V", "size", "strength", "withCells", "(FF)Lrtx/kimiko/utils/render/render2d/rectangle/tablepanel/BuiltTablePanel;", "value", "withSeed", "(F)Lrtx/kimiko/utils/render/render2d/rectangle/tablepanel/BuiltTablePanel;", "withInnerTint", "withInnerRadius", "", "visible", "()Z", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "()I", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "copy", "(FFFFFFFFFFIIIIFFFFF)Lrtx/kimiko/utils/render/render2d/rectangle/tablepanel/BuiltTablePanel;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltTablePanel {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final float x;
    private final float y;
    private final float width;
    private final float height;
    private final float radiusTopLeft;
    private final float radiusTopRight;
    private final float radiusBottomRight;
    private final float radiusBottomLeft;
    private final float smoothness;
    private final float frameThickness;
    private final int feltTop;
    private final int feltBottom;
    private final int woodLight;
    private final int woodDark;
    private final float cellSize;
    private final float cellStrength;
    private final float seed;
    private final float innerTint;
    private final float innerRadius;
    public static final float DEFAULT_SMOOTHNESS = 0.0f;
    public static final float DEFAULT_CELL_SIZE = 14.0f;
    public static final float DEFAULT_CELL_STRENGTH = 0.22f;
    public static final float DEFAULT_INNER_TINT = 0.1f;
    public static final float DEFAULT_INNER_RADIUS = 10.0f;

    public BuiltTablePanel(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, float smoothness, float frameThickness, int feltTop, int feltBottom, int woodLight, int woodDark, float cellSize, float cellStrength, float seed, float innerTint, float innerRadius) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radiusTopLeft = radiusTopLeft;
        this.radiusTopRight = radiusTopRight;
        this.radiusBottomRight = radiusBottomRight;
        this.radiusBottomLeft = radiusBottomLeft;
        this.smoothness = smoothness;
        this.frameThickness = frameThickness;
        this.feltTop = feltTop;
        this.feltBottom = feltBottom;
        this.woodLight = woodLight;
        this.woodDark = woodDark;
        this.cellSize = cellSize;
        this.cellStrength = cellStrength;
        this.seed = seed;
        this.innerTint = innerTint;
        this.innerRadius = innerRadius;
    }

    @JvmName(name="x")
    public final float x() {
        return this.x;
    }

    @JvmName(name="y")
    public final float y() {
        return this.y;
    }

    @JvmName(name="width")
    public final float width() {
        return this.width;
    }

    @JvmName(name="height")
    public final float height() {
        return this.height;
    }

    @JvmName(name="radiusTopLeft")
    public final float radiusTopLeft() {
        return this.radiusTopLeft;
    }

    @JvmName(name="radiusTopRight")
    public final float radiusTopRight() {
        return this.radiusTopRight;
    }

    @JvmName(name="radiusBottomRight")
    public final float radiusBottomRight() {
        return this.radiusBottomRight;
    }

    @JvmName(name="radiusBottomLeft")
    public final float radiusBottomLeft() {
        return this.radiusBottomLeft;
    }

    @JvmName(name="smoothness")
    public final float smoothness() {
        return this.smoothness;
    }

    @JvmName(name="frameThickness")
    public final float frameThickness() {
        return this.frameThickness;
    }

    @JvmName(name="feltTop")
    public final int feltTop() {
        return this.feltTop;
    }

    @JvmName(name="feltBottom")
    public final int feltBottom() {
        return this.feltBottom;
    }

    @JvmName(name="woodLight")
    public final int woodLight() {
        return this.woodLight;
    }

    @JvmName(name="woodDark")
    public final int woodDark() {
        return this.woodDark;
    }

    @JvmName(name="cellSize")
    public final float cellSize() {
        return this.cellSize;
    }

    @JvmName(name="cellStrength")
    public final float cellStrength() {
        return this.cellStrength;
    }

    @JvmName(name="seed")
    public final float seed() {
        return this.seed;
    }

    @JvmName(name="innerTint")
    public final float innerTint() {
        return this.innerTint;
    }

    @JvmName(name="innerRadius")
    public final float innerRadius() {
        return this.innerRadius;
    }

    public BuiltTablePanel(float x, float y, float width, float height, float radius, float frameThickness, int feltTop, int feltBottom, int woodLight, int woodDark) {
        this(x, y, width, height, radius, radius, radius, radius, 0.0f, frameThickness, feltTop, feltBottom, woodLight, woodDark, 14.0f, 0.22f, 0.0f, 0.1f, 10.0f);
    }

    @NotNull
    public final BuiltTablePanel withCells(float size, float strength) {
        return new BuiltTablePanel(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.smoothness, this.frameThickness, this.feltTop, this.feltBottom, this.woodLight, this.woodDark, size, strength, this.seed, this.innerTint, this.innerRadius);
    }

    @NotNull
    public final BuiltTablePanel withSeed(float value) {
        return new BuiltTablePanel(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.smoothness, this.frameThickness, this.feltTop, this.feltBottom, this.woodLight, this.woodDark, this.cellSize, this.cellStrength, value, this.innerTint, this.innerRadius);
    }

    @NotNull
    public final BuiltTablePanel withInnerTint(float value) {
        return new BuiltTablePanel(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.smoothness, this.frameThickness, this.feltTop, this.feltBottom, this.woodLight, this.woodDark, this.cellSize, this.cellStrength, this.seed, value, this.innerRadius);
    }

    @NotNull
    public final BuiltTablePanel withInnerRadius(float value) {
        return new BuiltTablePanel(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.smoothness, this.frameThickness, this.feltTop, this.feltBottom, this.woodLight, this.woodDark, this.cellSize, this.cellStrength, this.seed, this.innerTint, value);
    }

    public final boolean visible() {
        return this.width > 0.0f && this.height > 0.0f && ((this.feltTop >>> 24 & 0xFF) > 0 || (this.woodLight >>> 24 & 0xFF) > 0);
    }

    public final float component1() {
        return this.x;
    }

    public final float component2() {
        return this.y;
    }

    public final float component3() {
        return this.width;
    }

    public final float component4() {
        return this.height;
    }

    public final float component5() {
        return this.radiusTopLeft;
    }

    public final float component6() {
        return this.radiusTopRight;
    }

    public final float component7() {
        return this.radiusBottomRight;
    }

    public final float component8() {
        return this.radiusBottomLeft;
    }

    public final float component9() {
        return this.smoothness;
    }

    public final float component10() {
        return this.frameThickness;
    }

    public final int component11() {
        return this.feltTop;
    }

    public final int component12() {
        return this.feltBottom;
    }

    public final int component13() {
        return this.woodLight;
    }

    public final int component14() {
        return this.woodDark;
    }

    public final float component15() {
        return this.cellSize;
    }

    public final float component16() {
        return this.cellStrength;
    }

    public final float component17() {
        return this.seed;
    }

    public final float component18() {
        return this.innerTint;
    }

    public final float component19() {
        return this.innerRadius;
    }

    @NotNull
    public final BuiltTablePanel copy(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, float smoothness, float frameThickness, int feltTop, int feltBottom, int woodLight, int woodDark, float cellSize, float cellStrength, float seed, float innerTint, float innerRadius) {
        return new BuiltTablePanel(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, smoothness, frameThickness, feltTop, feltBottom, woodLight, woodDark, cellSize, cellStrength, seed, innerTint, innerRadius);
    }

    public static /* synthetic */ BuiltTablePanel copy$default(BuiltTablePanel builtTablePanel, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, int n, int n2, int n3, int n4, float f11, float f12, float f13, float f14, float f15, int n5, Object object) {
        if ((n5 & 1) != 0) {
            f = builtTablePanel.x;
        }
        if ((n5 & 2) != 0) {
            f2 = builtTablePanel.y;
        }
        if ((n5 & 4) != 0) {
            f3 = builtTablePanel.width;
        }
        if ((n5 & 8) != 0) {
            f4 = builtTablePanel.height;
        }
        if ((n5 & 0x10) != 0) {
            f5 = builtTablePanel.radiusTopLeft;
        }
        if ((n5 & 0x20) != 0) {
            f6 = builtTablePanel.radiusTopRight;
        }
        if ((n5 & 0x40) != 0) {
            f7 = builtTablePanel.radiusBottomRight;
        }
        if ((n5 & 0x80) != 0) {
            f8 = builtTablePanel.radiusBottomLeft;
        }
        if ((n5 & 0x100) != 0) {
            f9 = builtTablePanel.smoothness;
        }
        if ((n5 & 0x200) != 0) {
            f10 = builtTablePanel.frameThickness;
        }
        if ((n5 & 0x400) != 0) {
            n = builtTablePanel.feltTop;
        }
        if ((n5 & 0x800) != 0) {
            n2 = builtTablePanel.feltBottom;
        }
        if ((n5 & 0x1000) != 0) {
            n3 = builtTablePanel.woodLight;
        }
        if ((n5 & 0x2000) != 0) {
            n4 = builtTablePanel.woodDark;
        }
        if ((n5 & 0x4000) != 0) {
            f11 = builtTablePanel.cellSize;
        }
        if ((n5 & 0x8000) != 0) {
            f12 = builtTablePanel.cellStrength;
        }
        if ((n5 & 0x10000) != 0) {
            f13 = builtTablePanel.seed;
        }
        if ((n5 & 0x20000) != 0) {
            f14 = builtTablePanel.innerTint;
        }
        if ((n5 & 0x40000) != 0) {
            f15 = builtTablePanel.innerRadius;
        }
        return builtTablePanel.copy(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, n, n2, n3, n4, f11, f12, f13, f14, f15);
    }

    @NotNull
    public String toString() {
        return "BuiltTablePanel(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", radiusTopLeft=" + this.radiusTopLeft + ", radiusTopRight=" + this.radiusTopRight + ", radiusBottomRight=" + this.radiusBottomRight + ", radiusBottomLeft=" + this.radiusBottomLeft + ", smoothness=" + this.smoothness + ", frameThickness=" + this.frameThickness + ", feltTop=" + this.feltTop + ", feltBottom=" + this.feltBottom + ", woodLight=" + this.woodLight + ", woodDark=" + this.woodDark + ", cellSize=" + this.cellSize + ", cellStrength=" + this.cellStrength + ", seed=" + this.seed + ", innerTint=" + this.innerTint + ", innerRadius=" + this.innerRadius + ")";
    }

    public int hashCode() {
        int result = Float.hashCode(this.x);
        result = result * 31 + Float.hashCode(this.y);
        result = result * 31 + Float.hashCode(this.width);
        result = result * 31 + Float.hashCode(this.height);
        result = result * 31 + Float.hashCode(this.radiusTopLeft);
        result = result * 31 + Float.hashCode(this.radiusTopRight);
        result = result * 31 + Float.hashCode(this.radiusBottomRight);
        result = result * 31 + Float.hashCode(this.radiusBottomLeft);
        result = result * 31 + Float.hashCode(this.smoothness);
        result = result * 31 + Float.hashCode(this.frameThickness);
        result = result * 31 + Integer.hashCode(this.feltTop);
        result = result * 31 + Integer.hashCode(this.feltBottom);
        result = result * 31 + Integer.hashCode(this.woodLight);
        result = result * 31 + Integer.hashCode(this.woodDark);
        result = result * 31 + Float.hashCode(this.cellSize);
        result = result * 31 + Float.hashCode(this.cellStrength);
        result = result * 31 + Float.hashCode(this.seed);
        result = result * 31 + Float.hashCode(this.innerTint);
        result = result * 31 + Float.hashCode(this.innerRadius);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltTablePanel)) {
            return false;
        }
        BuiltTablePanel builtTablePanel = (BuiltTablePanel)other;
        if (Float.compare(this.x, builtTablePanel.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtTablePanel.y) != 0) {
            return false;
        }
        if (Float.compare(this.width, builtTablePanel.width) != 0) {
            return false;
        }
        if (Float.compare(this.height, builtTablePanel.height) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopLeft, builtTablePanel.radiusTopLeft) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopRight, builtTablePanel.radiusTopRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomRight, builtTablePanel.radiusBottomRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomLeft, builtTablePanel.radiusBottomLeft) != 0) {
            return false;
        }
        if (Float.compare(this.smoothness, builtTablePanel.smoothness) != 0) {
            return false;
        }
        if (Float.compare(this.frameThickness, builtTablePanel.frameThickness) != 0) {
            return false;
        }
        if (this.feltTop != builtTablePanel.feltTop) {
            return false;
        }
        if (this.feltBottom != builtTablePanel.feltBottom) {
            return false;
        }
        if (this.woodLight != builtTablePanel.woodLight) {
            return false;
        }
        if (this.woodDark != builtTablePanel.woodDark) {
            return false;
        }
        if (Float.compare(this.cellSize, builtTablePanel.cellSize) != 0) {
            return false;
        }
        if (Float.compare(this.cellStrength, builtTablePanel.cellStrength) != 0) {
            return false;
        }
        if (Float.compare(this.seed, builtTablePanel.seed) != 0) {
            return false;
        }
        if (Float.compare(this.innerTint, builtTablePanel.innerTint) != 0) {
            return false;
        }
        return Float.compare(this.innerRadius, builtTablePanel.innerRadius) == 0;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0006\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/tablepanel/BuiltTablePanel.Companion;", "", "<init>", "()V", "", "DEFAULT_SMOOTHNESS", "F", "DEFAULT_CELL_SIZE", "DEFAULT_CELL_STRENGTH", "DEFAULT_INNER_TINT", "DEFAULT_INNER_RADIUS", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

