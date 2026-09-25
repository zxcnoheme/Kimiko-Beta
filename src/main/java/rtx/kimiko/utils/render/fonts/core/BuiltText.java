/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.fonts.core;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b:\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u0000 R2\u00020\u0001:\u0001RB\u00a9\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0005\u0012\u0006\u0010\u0015\u001a\u00020\u0005\u0012\u0006\u0010\u0016\u001a\u00020\u0005\u0012\u0006\u0010\u0017\u001a\u00020\u0005\u0012\u0006\u0010\u0018\u001a\u00020\u0005\u0012\u0006\u0010\u0019\u001a\u00020\u0011\u00a2\u0006\u0004\b\u001a\u0010\u001bB;\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\u001c\u001a\u00020\t\u00a2\u0006\u0004\b\u001a\u0010\u001dBS\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u00a2\u0006\u0004\b\u001a\u0010\u001eJ\r\u0010\u001f\u001a\u00020\u0011\u00a2\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00020\u0011\u00a2\u0006\u0004\b!\u0010 J5\u0010'\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u0011\u00a2\u0006\u0004\b'\u0010(J5\u0010'\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\u0005\u00a2\u0006\u0004\b'\u0010+J\r\u0010,\u001a\u00020\u0000\u00a2\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b.\u0010/J\u0012\u00100\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b0\u0010/J\u0010\u00101\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b1\u00102J\u0010\u00103\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b3\u00102J\u0010\u00104\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b4\u00102J\u0010\u00105\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b5\u00106J\u0010\u00107\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b7\u00106J\u0010\u00108\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b8\u00106J\u0010\u00109\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b9\u00106J\u0010\u0010:\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b:\u00102J\u0010\u0010;\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b;\u00102J\u0010\u0010<\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b<\u00102J\u0010\u0010=\u001a\u00020\u0011H\u00c6\u0003\u00a2\u0006\u0004\b=\u0010 J\u0010\u0010>\u001a\u00020\u0011H\u00c6\u0003\u00a2\u0006\u0004\b>\u0010 J\u0010\u0010?\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b?\u00102J\u0010\u0010@\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b@\u00102J\u0010\u0010A\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\bA\u00102J\u0010\u0010B\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\bB\u00102J\u0010\u0010C\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\bC\u00102J\u0010\u0010D\u001a\u00020\u0011H\u00c6\u0003\u00a2\u0006\u0004\bD\u0010 J\u00da\u0001\u0010E\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u0011H\u00c6\u0001\u00a2\u0006\u0004\bE\u0010FJ\u001b\u0010H\u001a\u00020\u00112\b\u0010G\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\bH\u0010IJ\u0011\u0010J\u001a\u00020\tH\u00d6\u0081\u0004\u00a2\u0006\u0004\bJ\u00106J\u0011\u0010K\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\bK\u0010/R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010N\u001a\u0004\b\u0003\u0010/R'\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010N\u001a\u0004\b\u0004\u0010/R%\u0010\u0006\u001a\u00020\u00058\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010O\u001a\u0004\b\u0006\u00102R%\u0010\u0007\u001a\u00020\u00058\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010O\u001a\u0004\b\u0007\u00102R%\u0010\b\u001a\u00020\u00058\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010O\u001a\u0004\b\b\u00102R%\u0010\n\u001a\u00020\t8\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010P\u001a\u0004\b\n\u00106R%\u0010\u000b\u001a\u00020\t8\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010P\u001a\u0004\b\u000b\u00106R%\u0010\f\u001a\u00020\t8\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010P\u001a\u0004\b\f\u00106R%\u0010\r\u001a\u00020\t8\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010P\u001a\u0004\b\r\u00106R%\u0010\u000e\u001a\u00020\u00058\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010O\u001a\u0004\b\u000e\u00102R%\u0010\u000f\u001a\u00020\u00058\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010O\u001a\u0004\b\u000f\u00102R%\u0010\u0010\u001a\u00020\u00058\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010O\u001a\u0004\b\u0010\u00102R%\u0010\u0012\u001a\u00020\u00118\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\u0012\u00a2\u0006\f\n\u0004\b\u0012\u0010Q\u001a\u0004\b\u0012\u0010 R%\u0010\u0013\u001a\u00020\u00118\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\u0013\u00a2\u0006\f\n\u0004\b\u0013\u0010Q\u001a\u0004\b\u0013\u0010 R%\u0010\u0014\u001a\u00020\u00058\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\u0014\u00a2\u0006\f\n\u0004\b\u0014\u0010O\u001a\u0004\b\u0014\u00102R%\u0010\u0015\u001a\u00020\u00058\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\u0015\u00a2\u0006\f\n\u0004\b\u0015\u0010O\u001a\u0004\b\u0015\u00102R%\u0010\u0016\u001a\u00020\u00058\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\u0016\u00a2\u0006\f\n\u0004\b\u0016\u0010O\u001a\u0004\b\u0016\u00102R%\u0010\u0017\u001a\u00020\u00058\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\u0017\u00a2\u0006\f\n\u0004\b\u0017\u0010O\u001a\u0004\b\u0017\u00102R%\u0010\u0018\u001a\u00020\u00058\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\u0018\u00a2\u0006\f\n\u0004\b\u0018\u0010O\u001a\u0004\b\u0018\u00102R%\u0010\u0019\u001a\u00020\u00118\u0007z\f\bL\u0012\b\bM\u0012\u0004\b\b(\u0019\u00a2\u0006\f\n\u0004\b\u0019\u0010Q\u001a\u0004\b\u0019\u0010 \u00a8\u0006S"}, d2={"Lrtx/kimiko/utils/render/fonts/core/BuiltText;", "", "", "fontName", "text", "", "x", "y", "size", "", "colorTopLeft", "colorTopRight", "colorBottomRight", "colorBottomLeft", "rotationDegrees", "rotationOriginX", "rotationOriginY", "", "fadeLeft", "fadeRight", "fadeLeftX", "fadeRightX", "fadeWidth", "fadeLeftStrength", "fadeRightStrength", "snapOrigin", "<init>", "(Ljava/lang/String;Ljava/lang/String;FFFIIIIFFFZZFFFFFZ)V", "color", "(Ljava/lang/String;Ljava/lang/String;FFFI)V", "(Ljava/lang/String;Ljava/lang/String;FFFIIII)V", "visible", "()Z", "hasHorizontalFade", "leftX", "rightX", "width", "left", "right", "withHorizontalFade", "(FFFZZ)Lrtx/kimiko/utils/render/fonts/core/BuiltText;", "leftStrength", "rightStrength", "(FFFFF)Lrtx/kimiko/utils/render/fonts/core/BuiltText;", "withoutPixelSnap", "()Lrtx/kimiko/utils/render/fonts/core/BuiltText;", "component1", "()Ljava/lang/String;", "component2", "component3", "()F", "component4", "component5", "component6", "()I", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "copy", "(Ljava/lang/String;Ljava/lang/String;FFFIIIIFFFZZFFFFFZ)Lrtx/kimiko/utils/render/fonts/core/BuiltText;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Lkotlin/jvm/JvmName;", "name", "Ljava/lang/String;", "F", "I", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltText {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String fontName;
    @Nullable
    private final String text;
    private final float x;
    private final float y;
    private final float size;
    private final int colorTopLeft;
    private final int colorTopRight;
    private final int colorBottomRight;
    private final int colorBottomLeft;
    private final float rotationDegrees;
    private final float rotationOriginX;
    private final float rotationOriginY;
    private final boolean fadeLeft;
    private final boolean fadeRight;
    private final float fadeLeftX;
    private final float fadeRightX;
    private final float fadeWidth;
    private final float fadeLeftStrength;
    private final float fadeRightStrength;
    private final boolean snapOrigin;

    public BuiltText(@NotNull String fontName, @Nullable String text, float x, float y, float size, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float rotationDegrees, float rotationOriginX, float rotationOriginY, boolean fadeLeft, boolean fadeRight, float fadeLeftX, float fadeRightX, float fadeWidth, float fadeLeftStrength, float fadeRightStrength, boolean snapOrigin) {
        Intrinsics.checkNotNullParameter((Object)fontName, (String)"fontName");
        this.fontName = fontName;
        this.text = text;
        this.x = x;
        this.y = y;
        this.size = size;
        this.colorTopLeft = colorTopLeft;
        this.colorTopRight = colorTopRight;
        this.colorBottomRight = colorBottomRight;
        this.colorBottomLeft = colorBottomLeft;
        this.rotationDegrees = rotationDegrees;
        this.rotationOriginX = rotationOriginX;
        this.rotationOriginY = rotationOriginY;
        this.fadeLeft = fadeLeft;
        this.fadeRight = fadeRight;
        this.fadeLeftX = fadeLeftX;
        this.fadeRightX = fadeRightX;
        this.fadeWidth = fadeWidth;
        this.fadeLeftStrength = fadeLeftStrength;
        this.fadeRightStrength = fadeRightStrength;
        this.snapOrigin = snapOrigin;
    }

    @JvmName(name="fontName")
    @NotNull
    public final String fontName() {
        return this.fontName;
    }

    @JvmName(name="text")
    @Nullable
    public final String text() {
        return this.text;
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

    @JvmName(name="colorTopLeft")
    public final int colorTopLeft() {
        return this.colorTopLeft;
    }

    @JvmName(name="colorTopRight")
    public final int colorTopRight() {
        return this.colorTopRight;
    }

    @JvmName(name="colorBottomRight")
    public final int colorBottomRight() {
        return this.colorBottomRight;
    }

    @JvmName(name="colorBottomLeft")
    public final int colorBottomLeft() {
        return this.colorBottomLeft;
    }

    @JvmName(name="rotationDegrees")
    public final float rotationDegrees() {
        return this.rotationDegrees;
    }

    @JvmName(name="rotationOriginX")
    public final float rotationOriginX() {
        return this.rotationOriginX;
    }

    @JvmName(name="rotationOriginY")
    public final float rotationOriginY() {
        return this.rotationOriginY;
    }

    @JvmName(name="fadeLeft")
    public final boolean fadeLeft() {
        return this.fadeLeft;
    }

    @JvmName(name="fadeRight")
    public final boolean fadeRight() {
        return this.fadeRight;
    }

    @JvmName(name="fadeLeftX")
    public final float fadeLeftX() {
        return this.fadeLeftX;
    }

    @JvmName(name="fadeRightX")
    public final float fadeRightX() {
        return this.fadeRightX;
    }

    @JvmName(name="fadeWidth")
    public final float fadeWidth() {
        return this.fadeWidth;
    }

    @JvmName(name="fadeLeftStrength")
    public final float fadeLeftStrength() {
        return this.fadeLeftStrength;
    }

    @JvmName(name="fadeRightStrength")
    public final float fadeRightStrength() {
        return this.fadeRightStrength;
    }

    @JvmName(name="snapOrigin")
    public final boolean snapOrigin() {
        return this.snapOrigin;
    }

    public BuiltText(@NotNull String fontName, @Nullable String text, float x, float y, float size, int color) {
        this(fontName, text, x, y, size, color, color, color, color);
    }

    public BuiltText(@NotNull String fontName, @Nullable String text, float x, float y, float size, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft) {
        this(fontName, text, x, y, size, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft, 0.0f, x, y, false, false, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, true);
    }

    public final boolean visible() {
        return this.text != null && ((CharSequence)this.text).length() > 0 && this.size > 0.0f && ((this.colorTopLeft | this.colorTopRight | this.colorBottomRight | this.colorBottomLeft) >>> 24 & 0xFF) > 0;
    }

    public final boolean hasHorizontalFade() {
        return (this.fadeLeftStrength > 0.001f || this.fadeRightStrength > 0.001f) && this.fadeWidth > 0.0f && this.fadeRightX > this.fadeLeftX;
    }

    @NotNull
    public final BuiltText withHorizontalFade(float leftX, float rightX, float width, boolean left, boolean right) {
        return this.withHorizontalFade(leftX, rightX, width, left ? 1.0f : 0.0f, right ? 1.0f : 0.0f);
    }

    @NotNull
    public final BuiltText withHorizontalFade(float leftX, float rightX, float width, float leftStrength, float rightStrength) {
        return new BuiltText(this.fontName, this.text, this.x, this.y, this.size, this.colorTopLeft, this.colorTopRight, this.colorBottomRight, this.colorBottomLeft, this.rotationDegrees, this.rotationOriginX, this.rotationOriginY, leftStrength > 0.001f, rightStrength > 0.001f, leftX, rightX, width, BuiltText.Companion.clamp01(leftStrength), BuiltText.Companion.clamp01(rightStrength), this.snapOrigin);
    }

    @NotNull
    public final BuiltText withoutPixelSnap() {
        return new BuiltText(this.fontName, this.text, this.x, this.y, this.size, this.colorTopLeft, this.colorTopRight, this.colorBottomRight, this.colorBottomLeft, this.rotationDegrees, this.rotationOriginX, this.rotationOriginY, this.fadeLeft, this.fadeRight, this.fadeLeftX, this.fadeRightX, this.fadeWidth, this.fadeLeftStrength, this.fadeRightStrength, false);
    }

    @NotNull
    public final String component1() {
        return this.fontName;
    }

    @Nullable
    public final String component2() {
        return this.text;
    }

    public final float component3() {
        return this.x;
    }

    public final float component4() {
        return this.y;
    }

    public final float component5() {
        return this.size;
    }

    public final int component6() {
        return this.colorTopLeft;
    }

    public final int component7() {
        return this.colorTopRight;
    }

    public final int component8() {
        return this.colorBottomRight;
    }

    public final int component9() {
        return this.colorBottomLeft;
    }

    public final float component10() {
        return this.rotationDegrees;
    }

    public final float component11() {
        return this.rotationOriginX;
    }

    public final float component12() {
        return this.rotationOriginY;
    }

    public final boolean component13() {
        return this.fadeLeft;
    }

    public final boolean component14() {
        return this.fadeRight;
    }

    public final float component15() {
        return this.fadeLeftX;
    }

    public final float component16() {
        return this.fadeRightX;
    }

    public final float component17() {
        return this.fadeWidth;
    }

    public final float component18() {
        return this.fadeLeftStrength;
    }

    public final float component19() {
        return this.fadeRightStrength;
    }

    public final boolean component20() {
        return this.snapOrigin;
    }

    @NotNull
    public final BuiltText copy(@NotNull String fontName, @Nullable String text, float x, float y, float size, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float rotationDegrees, float rotationOriginX, float rotationOriginY, boolean fadeLeft, boolean fadeRight, float fadeLeftX, float fadeRightX, float fadeWidth, float fadeLeftStrength, float fadeRightStrength, boolean snapOrigin) {
        Intrinsics.checkNotNullParameter((Object)fontName, (String)"fontName");
        return new BuiltText(fontName, text, x, y, size, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft, rotationDegrees, rotationOriginX, rotationOriginY, fadeLeft, fadeRight, fadeLeftX, fadeRightX, fadeWidth, fadeLeftStrength, fadeRightStrength, snapOrigin);
    }

    public static /* synthetic */ BuiltText copy$default(BuiltText builtText, String string, String string2, float f, float f2, float f3, int n, int n2, int n3, int n4, float f4, float f5, float f6, boolean bl, boolean bl2, float f7, float f8, float f9, float f10, float f11, boolean bl3, int n5, Object object) {
        if ((n5 & 1) != 0) {
            string = builtText.fontName;
        }
        if ((n5 & 2) != 0) {
            string2 = builtText.text;
        }
        if ((n5 & 4) != 0) {
            f = builtText.x;
        }
        if ((n5 & 8) != 0) {
            f2 = builtText.y;
        }
        if ((n5 & 0x10) != 0) {
            f3 = builtText.size;
        }
        if ((n5 & 0x20) != 0) {
            n = builtText.colorTopLeft;
        }
        if ((n5 & 0x40) != 0) {
            n2 = builtText.colorTopRight;
        }
        if ((n5 & 0x80) != 0) {
            n3 = builtText.colorBottomRight;
        }
        if ((n5 & 0x100) != 0) {
            n4 = builtText.colorBottomLeft;
        }
        if ((n5 & 0x200) != 0) {
            f4 = builtText.rotationDegrees;
        }
        if ((n5 & 0x400) != 0) {
            f5 = builtText.rotationOriginX;
        }
        if ((n5 & 0x800) != 0) {
            f6 = builtText.rotationOriginY;
        }
        if ((n5 & 0x1000) != 0) {
            bl = builtText.fadeLeft;
        }
        if ((n5 & 0x2000) != 0) {
            bl2 = builtText.fadeRight;
        }
        if ((n5 & 0x4000) != 0) {
            f7 = builtText.fadeLeftX;
        }
        if ((n5 & 0x8000) != 0) {
            f8 = builtText.fadeRightX;
        }
        if ((n5 & 0x10000) != 0) {
            f9 = builtText.fadeWidth;
        }
        if ((n5 & 0x20000) != 0) {
            f10 = builtText.fadeLeftStrength;
        }
        if ((n5 & 0x40000) != 0) {
            f11 = builtText.fadeRightStrength;
        }
        if ((n5 & 0x80000) != 0) {
            bl3 = builtText.snapOrigin;
        }
        return builtText.copy(string, string2, f, f2, f3, n, n2, n3, n4, f4, f5, f6, bl, bl2, f7, f8, f9, f10, f11, bl3);
    }

    @NotNull
    public String toString() {
        return "BuiltText(fontName=" + this.fontName + ", text=" + this.text + ", x=" + this.x + ", y=" + this.y + ", size=" + this.size + ", colorTopLeft=" + this.colorTopLeft + ", colorTopRight=" + this.colorTopRight + ", colorBottomRight=" + this.colorBottomRight + ", colorBottomLeft=" + this.colorBottomLeft + ", rotationDegrees=" + this.rotationDegrees + ", rotationOriginX=" + this.rotationOriginX + ", rotationOriginY=" + this.rotationOriginY + ", fadeLeft=" + this.fadeLeft + ", fadeRight=" + this.fadeRight + ", fadeLeftX=" + this.fadeLeftX + ", fadeRightX=" + this.fadeRightX + ", fadeWidth=" + this.fadeWidth + ", fadeLeftStrength=" + this.fadeLeftStrength + ", fadeRightStrength=" + this.fadeRightStrength + ", snapOrigin=" + this.snapOrigin + ")";
    }

    public int hashCode() {
        int result = this.fontName.hashCode();
        result = result * 31 + (this.text == null ? 0 : this.text.hashCode());
        result = result * 31 + Float.hashCode(this.x);
        result = result * 31 + Float.hashCode(this.y);
        result = result * 31 + Float.hashCode(this.size);
        result = result * 31 + Integer.hashCode(this.colorTopLeft);
        result = result * 31 + Integer.hashCode(this.colorTopRight);
        result = result * 31 + Integer.hashCode(this.colorBottomRight);
        result = result * 31 + Integer.hashCode(this.colorBottomLeft);
        result = result * 31 + Float.hashCode(this.rotationDegrees);
        result = result * 31 + Float.hashCode(this.rotationOriginX);
        result = result * 31 + Float.hashCode(this.rotationOriginY);
        result = result * 31 + Boolean.hashCode(this.fadeLeft);
        result = result * 31 + Boolean.hashCode(this.fadeRight);
        result = result * 31 + Float.hashCode(this.fadeLeftX);
        result = result * 31 + Float.hashCode(this.fadeRightX);
        result = result * 31 + Float.hashCode(this.fadeWidth);
        result = result * 31 + Float.hashCode(this.fadeLeftStrength);
        result = result * 31 + Float.hashCode(this.fadeRightStrength);
        result = result * 31 + Boolean.hashCode(this.snapOrigin);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltText)) {
            return false;
        }
        BuiltText builtText = (BuiltText)other;
        if (!Intrinsics.areEqual((Object)this.fontName, (Object)builtText.fontName)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.text, (Object)builtText.text)) {
            return false;
        }
        if (Float.compare(this.x, builtText.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtText.y) != 0) {
            return false;
        }
        if (Float.compare(this.size, builtText.size) != 0) {
            return false;
        }
        if (this.colorTopLeft != builtText.colorTopLeft) {
            return false;
        }
        if (this.colorTopRight != builtText.colorTopRight) {
            return false;
        }
        if (this.colorBottomRight != builtText.colorBottomRight) {
            return false;
        }
        if (this.colorBottomLeft != builtText.colorBottomLeft) {
            return false;
        }
        if (Float.compare(this.rotationDegrees, builtText.rotationDegrees) != 0) {
            return false;
        }
        if (Float.compare(this.rotationOriginX, builtText.rotationOriginX) != 0) {
            return false;
        }
        if (Float.compare(this.rotationOriginY, builtText.rotationOriginY) != 0) {
            return false;
        }
        if (this.fadeLeft != builtText.fadeLeft) {
            return false;
        }
        if (this.fadeRight != builtText.fadeRight) {
            return false;
        }
        if (Float.compare(this.fadeLeftX, builtText.fadeLeftX) != 0) {
            return false;
        }
        if (Float.compare(this.fadeRightX, builtText.fadeRightX) != 0) {
            return false;
        }
        if (Float.compare(this.fadeWidth, builtText.fadeWidth) != 0) {
            return false;
        }
        if (Float.compare(this.fadeLeftStrength, builtText.fadeLeftStrength) != 0) {
            return false;
        }
        if (Float.compare(this.fadeRightStrength, builtText.fadeRightStrength) != 0) {
            return false;
        }
        return this.snapOrigin == builtText.snapOrigin;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/utils/render/fonts/core/BuiltText.Companion;", "", "<init>", "()V", "", "value", "clamp01", "(F)F", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float clamp01(float value) {
            return Math.max(0.0f, Math.min(1.0f, value));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

