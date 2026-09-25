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
package rtx.kimiko.utils.render.fonts.core.msdf;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.fonts.Fonts;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u0000 K2\u00020\u0001:\u0001KB\u0091\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\n\u0012\u0006\u0010\u000f\u001a\u00020\u0006\u0012\u0006\u0010\u0010\u001a\u00020\u0006\u0012\u0006\u0010\u0011\u001a\u00020\u0006\u0012\u0006\u0010\u0012\u001a\u00020\u0006\u0012\u0006\u0010\u0013\u001a\u00020\u0006\u0012\u0006\u0010\u0014\u001a\u00020\u0006\u0012\u0006\u0010\u0015\u001a\u00020\u0006\u0012\u0006\u0010\u0016\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0017\u0010\u0018B;\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\u0019\u001a\u00020\n\u00a2\u0006\u0004\b\u0017\u0010\u001aBS\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\u0019\u001a\u00020\n\u0012\u0006\u0010\u000f\u001a\u00020\u0006\u0012\u0006\u0010\u0010\u001a\u00020\u0006\u0012\u0006\u0010\u0011\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0017\u0010\u001bBk\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\n\u0012\u0006\u0010\u000f\u001a\u00020\u0006\u0012\u0006\u0010\u0010\u001a\u00020\u0006\u0012\u0006\u0010\u0011\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0017\u0010\u001cBS\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\n\u00a2\u0006\u0004\b\u0017\u0010\u001dJ\r\u0010\u001f\u001a\u00020\u001e\u00a2\u0006\u0004\b\u001f\u0010 J5\u0010&\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u0006\u00a2\u0006\u0004\b&\u0010'J\r\u0010(\u001a\u00020\u001e\u00a2\u0006\u0004\b(\u0010 J\u0010\u0010)\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b)\u0010*J\u0012\u0010+\u001a\u0004\u0018\u00010\u0004H\u00c6\u0003\u00a2\u0006\u0004\b+\u0010,J\u0010\u0010-\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b-\u0010.J\u0010\u0010/\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b/\u0010.J\u0010\u00100\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b0\u0010.J\u0010\u00101\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b1\u00102J\u0010\u00103\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b3\u00102J\u0010\u00104\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b4\u00102J\u0010\u00105\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b5\u00102J\u0010\u00106\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b6\u0010.J\u0010\u00107\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b7\u0010.J\u0010\u00108\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b8\u0010.J\u0010\u00109\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b9\u0010.J\u0010\u0010:\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b:\u0010.J\u0010\u0010;\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b;\u0010.J\u0010\u0010<\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b<\u0010.J\u0010\u0010=\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b=\u0010.J\u00bc\u0001\u0010>\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0004\b>\u0010?J\u001b\u0010A\u001a\u00020\u001e2\b\u0010@\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\bA\u0010BJ\u0011\u0010C\u001a\u00020\nH\u00d6\u0081\u0004\u00a2\u0006\u0004\bC\u00102J\u0011\u0010D\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\bD\u0010,R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010G\u001a\u0004\b\u0003\u0010*R'\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010H\u001a\u0004\b\u0005\u0010,R%\u0010\u0007\u001a\u00020\u00068\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010I\u001a\u0004\b\u0007\u0010.R%\u0010\b\u001a\u00020\u00068\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010I\u001a\u0004\b\b\u0010.R%\u0010\t\u001a\u00020\u00068\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010I\u001a\u0004\b\t\u0010.R%\u0010\u000b\u001a\u00020\n8\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010J\u001a\u0004\b\u000b\u00102R%\u0010\f\u001a\u00020\n8\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010J\u001a\u0004\b\f\u00102R%\u0010\r\u001a\u00020\n8\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010J\u001a\u0004\b\r\u00102R%\u0010\u000e\u001a\u00020\n8\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010J\u001a\u0004\b\u000e\u00102R%\u0010\u000f\u001a\u00020\u00068\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010I\u001a\u0004\b\u000f\u0010.R%\u0010\u0010\u001a\u00020\u00068\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010I\u001a\u0004\b\u0010\u0010.R%\u0010\u0011\u001a\u00020\u00068\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0011\u00a2\u0006\f\n\u0004\b\u0011\u0010I\u001a\u0004\b\u0011\u0010.R%\u0010\u0012\u001a\u00020\u00068\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0012\u00a2\u0006\f\n\u0004\b\u0012\u0010I\u001a\u0004\b\u0012\u0010.R%\u0010\u0013\u001a\u00020\u00068\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0013\u00a2\u0006\f\n\u0004\b\u0013\u0010I\u001a\u0004\b\u0013\u0010.R%\u0010\u0014\u001a\u00020\u00068\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0014\u00a2\u0006\f\n\u0004\b\u0014\u0010I\u001a\u0004\b\u0014\u0010.R%\u0010\u0015\u001a\u00020\u00068\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0015\u00a2\u0006\f\n\u0004\b\u0015\u0010I\u001a\u0004\b\u0015\u0010.R%\u0010\u0016\u001a\u00020\u00068\u0007z\f\bE\u0012\b\bF\u0012\u0004\b\b(\u0016\u00a2\u0006\f\n\u0004\b\u0016\u0010I\u001a\u0004\b\u0016\u0010.\u00a8\u0006L"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/BuiltMsdfText;", "", "Lrtx/kimiko/utils/render/fonts/Fonts;", "font", "", "text", "", "x", "y", "size", "", "colorTopLeft", "colorTopRight", "colorBottomRight", "colorBottomLeft", "rotationDegrees", "rotationOriginX", "rotationOriginY", "fadeLeftX", "fadeRightX", "fadeWidth", "fadeLeftStrength", "fadeRightStrength", "<init>", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;FFFIIIIFFFFFFFF)V", "color", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;FFFI)V", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;FFFIFFF)V", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;FFFIIIIFFF)V", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;FFFIIII)V", "", "hasHorizontalFade", "()Z", "leftX", "rightX", "width", "leftStrength", "rightStrength", "withHorizontalFade", "(FFFFF)Lrtx/kimiko/utils/render/fonts/core/msdf/BuiltMsdfText;", "visible", "component1", "()Lrtx/kimiko/utils/render/fonts/Fonts;", "component2", "()Ljava/lang/String;", "component3", "()F", "component4", "component5", "component6", "()I", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;FFFIIIIFFFFFFFF)Lrtx/kimiko/utils/render/fonts/core/msdf/BuiltMsdfText;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Lkotlin/jvm/JvmName;", "name", "Lrtx/kimiko/utils/render/fonts/Fonts;", "Ljava/lang/String;", "F", "I", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltMsdfText {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Fonts font;
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
    private final float fadeLeftX;
    private final float fadeRightX;
    private final float fadeWidth;
    private final float fadeLeftStrength;
    private final float fadeRightStrength;

    public BuiltMsdfText(@NotNull Fonts font, @Nullable String text, float x, float y, float size, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float rotationDegrees, float rotationOriginX, float rotationOriginY, float fadeLeftX, float fadeRightX, float fadeWidth, float fadeLeftStrength, float fadeRightStrength) {
        Intrinsics.checkNotNullParameter((Object)((Object)font), (String)"font");
        this.font = font;
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
        this.fadeLeftX = fadeLeftX;
        this.fadeRightX = fadeRightX;
        this.fadeWidth = fadeWidth;
        this.fadeLeftStrength = fadeLeftStrength;
        this.fadeRightStrength = fadeRightStrength;
    }

    @JvmName(name="font")
    @NotNull
    public final Fonts font() {
        return this.font;
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

    public BuiltMsdfText(@NotNull Fonts font, @Nullable String text, float x, float y, float size, int color) {
        this(font, text, x, y, size, color, color, color, color);
    }

    public BuiltMsdfText(@NotNull Fonts font, @Nullable String text, float x, float y, float size, int color, float rotationDegrees, float rotationOriginX, float rotationOriginY) {
        this(font, text, x, y, size, color, color, color, color, rotationDegrees, rotationOriginX, rotationOriginY, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    public BuiltMsdfText(@NotNull Fonts font, @Nullable String text, float x, float y, float size, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float rotationDegrees, float rotationOriginX, float rotationOriginY) {
        this(font, text, x, y, size, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft, rotationDegrees, rotationOriginX, rotationOriginY, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    public BuiltMsdfText(@NotNull Fonts font, @Nullable String text, float x, float y, float size, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft) {
        this(font, text, x, y, size, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft, 0.0f, x, y, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    public final boolean hasHorizontalFade() {
        return (this.fadeLeftStrength > 0.001f || this.fadeRightStrength > 0.001f) && this.fadeWidth > 0.0f && this.fadeRightX > this.fadeLeftX;
    }

    @NotNull
    public final BuiltMsdfText withHorizontalFade(float leftX, float rightX, float width, float leftStrength, float rightStrength) {
        return new BuiltMsdfText(this.font, this.text, this.x, this.y, this.size, this.colorTopLeft, this.colorTopRight, this.colorBottomRight, this.colorBottomLeft, this.rotationDegrees, this.rotationOriginX, this.rotationOriginY, leftX, rightX, width, BuiltMsdfText.Companion.clamp01(leftStrength), BuiltMsdfText.Companion.clamp01(rightStrength));
    }

    public final boolean visible() {
        CharSequence charSequence = this.text;
        if (charSequence == null || charSequence.length() == 0 || this.size <= 0.0f) {
            return false;
        }
        return BuiltMsdfText.Companion.alpha(this.colorTopLeft) > 0 || BuiltMsdfText.Companion.alpha(this.colorTopRight) > 0 || BuiltMsdfText.Companion.alpha(this.colorBottomRight) > 0 || BuiltMsdfText.Companion.alpha(this.colorBottomLeft) > 0;
    }

    @NotNull
    public final Fonts component1() {
        return this.font;
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

    public final float component13() {
        return this.fadeLeftX;
    }

    public final float component14() {
        return this.fadeRightX;
    }

    public final float component15() {
        return this.fadeWidth;
    }

    public final float component16() {
        return this.fadeLeftStrength;
    }

    public final float component17() {
        return this.fadeRightStrength;
    }

    @NotNull
    public final BuiltMsdfText copy(@NotNull Fonts font, @Nullable String text, float x, float y, float size, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float rotationDegrees, float rotationOriginX, float rotationOriginY, float fadeLeftX, float fadeRightX, float fadeWidth, float fadeLeftStrength, float fadeRightStrength) {
        Intrinsics.checkNotNullParameter((Object)((Object)font), (String)"font");
        return new BuiltMsdfText(font, text, x, y, size, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft, rotationDegrees, rotationOriginX, rotationOriginY, fadeLeftX, fadeRightX, fadeWidth, fadeLeftStrength, fadeRightStrength);
    }

    public static /* synthetic */ BuiltMsdfText copy$default(BuiltMsdfText builtMsdfText, Fonts fonts, String string, float f, float f2, float f3, int n, int n2, int n3, int n4, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, int n5, Object object) {
        if ((n5 & 1) != 0) {
            fonts = builtMsdfText.font;
        }
        if ((n5 & 2) != 0) {
            string = builtMsdfText.text;
        }
        if ((n5 & 4) != 0) {
            f = builtMsdfText.x;
        }
        if ((n5 & 8) != 0) {
            f2 = builtMsdfText.y;
        }
        if ((n5 & 0x10) != 0) {
            f3 = builtMsdfText.size;
        }
        if ((n5 & 0x20) != 0) {
            n = builtMsdfText.colorTopLeft;
        }
        if ((n5 & 0x40) != 0) {
            n2 = builtMsdfText.colorTopRight;
        }
        if ((n5 & 0x80) != 0) {
            n3 = builtMsdfText.colorBottomRight;
        }
        if ((n5 & 0x100) != 0) {
            n4 = builtMsdfText.colorBottomLeft;
        }
        if ((n5 & 0x200) != 0) {
            f4 = builtMsdfText.rotationDegrees;
        }
        if ((n5 & 0x400) != 0) {
            f5 = builtMsdfText.rotationOriginX;
        }
        if ((n5 & 0x800) != 0) {
            f6 = builtMsdfText.rotationOriginY;
        }
        if ((n5 & 0x1000) != 0) {
            f7 = builtMsdfText.fadeLeftX;
        }
        if ((n5 & 0x2000) != 0) {
            f8 = builtMsdfText.fadeRightX;
        }
        if ((n5 & 0x4000) != 0) {
            f9 = builtMsdfText.fadeWidth;
        }
        if ((n5 & 0x8000) != 0) {
            f10 = builtMsdfText.fadeLeftStrength;
        }
        if ((n5 & 0x10000) != 0) {
            f11 = builtMsdfText.fadeRightStrength;
        }
        return builtMsdfText.copy(fonts, string, f, f2, f3, n, n2, n3, n4, f4, f5, f6, f7, f8, f9, f10, f11);
    }

    @NotNull
    public String toString() {
        return "BuiltMsdfText(font=" + this.font + ", text=" + this.text + ", x=" + this.x + ", y=" + this.y + ", size=" + this.size + ", colorTopLeft=" + this.colorTopLeft + ", colorTopRight=" + this.colorTopRight + ", colorBottomRight=" + this.colorBottomRight + ", colorBottomLeft=" + this.colorBottomLeft + ", rotationDegrees=" + this.rotationDegrees + ", rotationOriginX=" + this.rotationOriginX + ", rotationOriginY=" + this.rotationOriginY + ", fadeLeftX=" + this.fadeLeftX + ", fadeRightX=" + this.fadeRightX + ", fadeWidth=" + this.fadeWidth + ", fadeLeftStrength=" + this.fadeLeftStrength + ", fadeRightStrength=" + this.fadeRightStrength + ")";
    }

    public int hashCode() {
        int result = this.font.hashCode();
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
        result = result * 31 + Float.hashCode(this.fadeLeftX);
        result = result * 31 + Float.hashCode(this.fadeRightX);
        result = result * 31 + Float.hashCode(this.fadeWidth);
        result = result * 31 + Float.hashCode(this.fadeLeftStrength);
        result = result * 31 + Float.hashCode(this.fadeRightStrength);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltMsdfText)) {
            return false;
        }
        BuiltMsdfText builtMsdfText = (BuiltMsdfText)other;
        if (this.font != builtMsdfText.font) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.text, (Object)builtMsdfText.text)) {
            return false;
        }
        if (Float.compare(this.x, builtMsdfText.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtMsdfText.y) != 0) {
            return false;
        }
        if (Float.compare(this.size, builtMsdfText.size) != 0) {
            return false;
        }
        if (this.colorTopLeft != builtMsdfText.colorTopLeft) {
            return false;
        }
        if (this.colorTopRight != builtMsdfText.colorTopRight) {
            return false;
        }
        if (this.colorBottomRight != builtMsdfText.colorBottomRight) {
            return false;
        }
        if (this.colorBottomLeft != builtMsdfText.colorBottomLeft) {
            return false;
        }
        if (Float.compare(this.rotationDegrees, builtMsdfText.rotationDegrees) != 0) {
            return false;
        }
        if (Float.compare(this.rotationOriginX, builtMsdfText.rotationOriginX) != 0) {
            return false;
        }
        if (Float.compare(this.rotationOriginY, builtMsdfText.rotationOriginY) != 0) {
            return false;
        }
        if (Float.compare(this.fadeLeftX, builtMsdfText.fadeLeftX) != 0) {
            return false;
        }
        if (Float.compare(this.fadeRightX, builtMsdfText.fadeRightX) != 0) {
            return false;
        }
        if (Float.compare(this.fadeWidth, builtMsdfText.fadeWidth) != 0) {
            return false;
        }
        if (Float.compare(this.fadeLeftStrength, builtMsdfText.fadeLeftStrength) != 0) {
            return false;
        }
        return Float.compare(this.fadeRightStrength, builtMsdfText.fadeRightStrength) == 0;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/BuiltMsdfText.Companion;", "", "<init>", "()V", "", "color", "alpha", "(I)I", "", "value", "clamp01", "(F)F", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final int alpha(int color) {
            return color >>> 24 & 0xFF;
        }

        private final float clamp01(float value) {
            return Math.max(0.0f, Math.min(1.0f, value));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

