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
package rtx.kimiko.utils.render.render2d.radialglass;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b-\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\b\u0018\u0000 I2\u00020\u0001:\u0001IB\u00b7\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u000b\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u000b\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0006\u0010\u001a\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u001d\u0010 \u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u0002\u00a2\u0006\u0004\b \u0010!J\r\u0010\"\u001a\u00020\u0013\u00a2\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b$\u0010\u001eJ\u0010\u0010%\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b%\u0010\u001eJ\u0010\u0010&\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b&\u0010\u001eJ\u0010\u0010'\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b'\u0010\u001eJ\u0010\u0010(\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b(\u0010\u001eJ\u0010\u0010)\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b)\u0010\u001eJ\u0010\u0010*\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b*\u0010\u001eJ\u0010\u0010+\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b+\u0010\u001eJ\u0010\u0010,\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b.\u0010-J\u0010\u0010/\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b/\u0010\u001eJ\u0010\u00100\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b0\u0010\u001eJ\u0010\u00101\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b1\u0010\u001eJ\u0010\u00102\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b2\u0010-J\u0010\u00103\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b3\u0010\u001eJ\u0010\u00104\u001a\u00020\u0013H\u00c6\u0003\u00a2\u0006\u0004\b4\u0010#J\u0010\u00105\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b5\u0010\u001eJ\u0010\u00106\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b6\u0010\u001eJ\u0010\u00107\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b7\u0010\u001eJ\u0010\u00108\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b8\u0010-J\u0010\u00109\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b9\u0010\u001eJ\u0010\u0010:\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b:\u0010\u001eJ\u00ec\u0001\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u00022\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b;\u0010<J\u001b\u0010>\u001a\u00020\u00132\b\u0010=\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b>\u0010?J\u0011\u0010@\u001a\u00020\u000bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b@\u0010-J\u0011\u0010B\u001a\u00020AH\u00d6\u0081\u0004\u00a2\u0006\u0004\bB\u0010CR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010F\u001a\u0004\b\u0003\u0010\u001eR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010F\u001a\u0004\b\u0004\u0010\u001eR%\u0010\u0005\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010F\u001a\u0004\b\u0005\u0010\u001eR%\u0010\u0006\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010F\u001a\u0004\b\u0006\u0010\u001eR%\u0010\u0007\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010F\u001a\u0004\b\u0007\u0010\u001eR%\u0010\b\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010F\u001a\u0004\b\b\u0010\u001eR%\u0010\t\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010F\u001a\u0004\b\t\u0010\u001eR%\u0010\n\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010F\u001a\u0004\b\n\u0010\u001eR%\u0010\f\u001a\u00020\u000b8\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010G\u001a\u0004\b\f\u0010-R%\u0010\r\u001a\u00020\u000b8\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010G\u001a\u0004\b\r\u0010-R%\u0010\u000e\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010F\u001a\u0004\b\u000e\u0010\u001eR%\u0010\u000f\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010F\u001a\u0004\b\u000f\u0010\u001eR%\u0010\u0010\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010F\u001a\u0004\b\u0010\u0010\u001eR%\u0010\u0011\u001a\u00020\u000b8\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0011\u00a2\u0006\f\n\u0004\b\u0011\u0010G\u001a\u0004\b\u0011\u0010-R%\u0010\u0012\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0012\u00a2\u0006\f\n\u0004\b\u0012\u0010F\u001a\u0004\b\u0012\u0010\u001eR%\u0010\u0014\u001a\u00020\u00138\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0014\u00a2\u0006\f\n\u0004\b\u0014\u0010H\u001a\u0004\b\u0014\u0010#R%\u0010\u0015\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0015\u00a2\u0006\f\n\u0004\b\u0015\u0010F\u001a\u0004\b\u0015\u0010\u001eR%\u0010\u0016\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0016\u00a2\u0006\f\n\u0004\b\u0016\u0010F\u001a\u0004\b\u0016\u0010\u001eR%\u0010\u0017\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0017\u00a2\u0006\f\n\u0004\b\u0017\u0010F\u001a\u0004\b\u0017\u0010\u001eR%\u0010\u0018\u001a\u00020\u000b8\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0018\u00a2\u0006\f\n\u0004\b\u0018\u0010G\u001a\u0004\b\u0018\u0010-R%\u0010\u0019\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0019\u00a2\u0006\f\n\u0004\b\u0019\u0010F\u001a\u0004\b\u0019\u0010\u001eR%\u0010\u001a\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u001a\u00a2\u0006\f\n\u0004\b\u001a\u0010F\u001a\u0004\b\u001a\u0010\u001e\u00a8\u0006J"}, d2={"Lrtx/kimiko/utils/render/render2d/radialglass/BuiltRadialGlass;", "", "", "centerX", "centerY", "innerRadius", "outerRadius", "midAngle", "halfAngle", "corner", "feather", "", "color", "secondColor", "colorOffset", "globalAlpha", "fresnelPower", "fresnelColor", "baseAlpha", "", "fresnelInvert", "fresnelMix", "distortStrength", "blurRadius", "highlightColor", "highlight", "z", "<init>", "(FFFFFFFFIIFFFIFZFFFIFF)V", "extent", "()F", "amount", "withHighlight", "(IF)Lrtx/kimiko/utils/render/render2d/radialglass/BuiltRadialGlass;", "visible", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "()I", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "copy", "(FFFFFFFFIIFFFIFZFFFIFF)Lrtx/kimiko/utils/render/render2d/radialglass/BuiltRadialGlass;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltRadialGlass {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final float centerX;
    private final float centerY;
    private final float innerRadius;
    private final float outerRadius;
    private final float midAngle;
    private final float halfAngle;
    private final float corner;
    private final float feather;
    private final int color;
    private final int secondColor;
    private final float colorOffset;
    private final float globalAlpha;
    private final float fresnelPower;
    private final int fresnelColor;
    private final float baseAlpha;
    private final boolean fresnelInvert;
    private final float fresnelMix;
    private final float distortStrength;
    private final float blurRadius;
    private final int highlightColor;
    private final float highlight;
    private final float z;
    public static final float MAX_HALF_ANGLE = 1.45f;
    public static final float DEFAULT_FEATHER = 1.0f;

    public BuiltRadialGlass(float centerX, float centerY, float innerRadius, float outerRadius, float midAngle, float halfAngle, float corner, float feather, int color, int secondColor, float colorOffset, float globalAlpha, float fresnelPower, int fresnelColor, float baseAlpha, boolean fresnelInvert, float fresnelMix, float distortStrength, float blurRadius, int highlightColor, float highlight, float z) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
        this.midAngle = midAngle;
        this.halfAngle = halfAngle;
        this.corner = corner;
        this.feather = feather;
        this.color = color;
        this.secondColor = secondColor;
        this.colorOffset = colorOffset;
        this.globalAlpha = globalAlpha;
        this.fresnelPower = fresnelPower;
        this.fresnelColor = fresnelColor;
        this.baseAlpha = baseAlpha;
        this.fresnelInvert = fresnelInvert;
        this.fresnelMix = fresnelMix;
        this.distortStrength = distortStrength;
        this.blurRadius = blurRadius;
        this.highlightColor = highlightColor;
        this.highlight = highlight;
        this.z = z;
    }

    @JvmName(name="centerX")
    public final float centerX() {
        return this.centerX;
    }

    @JvmName(name="centerY")
    public final float centerY() {
        return this.centerY;
    }

    @JvmName(name="innerRadius")
    public final float innerRadius() {
        return this.innerRadius;
    }

    @JvmName(name="outerRadius")
    public final float outerRadius() {
        return this.outerRadius;
    }

    @JvmName(name="midAngle")
    public final float midAngle() {
        return this.midAngle;
    }

    @JvmName(name="halfAngle")
    public final float halfAngle() {
        return this.halfAngle;
    }

    @JvmName(name="corner")
    public final float corner() {
        return this.corner;
    }

    @JvmName(name="feather")
    public final float feather() {
        return this.feather;
    }

    @JvmName(name="color")
    public final int color() {
        return this.color;
    }

    @JvmName(name="secondColor")
    public final int secondColor() {
        return this.secondColor;
    }

    @JvmName(name="colorOffset")
    public final float colorOffset() {
        return this.colorOffset;
    }

    @JvmName(name="globalAlpha")
    public final float globalAlpha() {
        return this.globalAlpha;
    }

    @JvmName(name="fresnelPower")
    public final float fresnelPower() {
        return this.fresnelPower;
    }

    @JvmName(name="fresnelColor")
    public final int fresnelColor() {
        return this.fresnelColor;
    }

    @JvmName(name="baseAlpha")
    public final float baseAlpha() {
        return this.baseAlpha;
    }

    @JvmName(name="fresnelInvert")
    public final boolean fresnelInvert() {
        return this.fresnelInvert;
    }

    @JvmName(name="fresnelMix")
    public final float fresnelMix() {
        return this.fresnelMix;
    }

    @JvmName(name="distortStrength")
    public final float distortStrength() {
        return this.distortStrength;
    }

    @JvmName(name="blurRadius")
    public final float blurRadius() {
        return this.blurRadius;
    }

    @JvmName(name="highlightColor")
    public final int highlightColor() {
        return this.highlightColor;
    }

    @JvmName(name="highlight")
    public final float highlight() {
        return this.highlight;
    }

    @JvmName(name="z")
    public final float z() {
        return this.z;
    }

    public final float extent() {
        return this.outerRadius + this.feather + this.corner + 2.0f;
    }

    @NotNull
    public final BuiltRadialGlass withHighlight(int color, float amount) {
        return new BuiltRadialGlass(this.centerX, this.centerY, this.innerRadius, this.outerRadius, this.midAngle, this.halfAngle, this.corner, this.feather, this.color, this.secondColor, this.colorOffset, this.globalAlpha, this.fresnelPower, this.fresnelColor, this.baseAlpha, this.fresnelInvert, this.fresnelMix, this.distortStrength, this.blurRadius, color, amount, this.z);
    }

    public final boolean visible() {
        return this.outerRadius > this.innerRadius && this.halfAngle > 0.0f && this.globalAlpha > 0.0f;
    }

    public final float component1() {
        return this.centerX;
    }

    public final float component2() {
        return this.centerY;
    }

    public final float component3() {
        return this.innerRadius;
    }

    public final float component4() {
        return this.outerRadius;
    }

    public final float component5() {
        return this.midAngle;
    }

    public final float component6() {
        return this.halfAngle;
    }

    public final float component7() {
        return this.corner;
    }

    public final float component8() {
        return this.feather;
    }

    public final int component9() {
        return this.color;
    }

    public final int component10() {
        return this.secondColor;
    }

    public final float component11() {
        return this.colorOffset;
    }

    public final float component12() {
        return this.globalAlpha;
    }

    public final float component13() {
        return this.fresnelPower;
    }

    public final int component14() {
        return this.fresnelColor;
    }

    public final float component15() {
        return this.baseAlpha;
    }

    public final boolean component16() {
        return this.fresnelInvert;
    }

    public final float component17() {
        return this.fresnelMix;
    }

    public final float component18() {
        return this.distortStrength;
    }

    public final float component19() {
        return this.blurRadius;
    }

    public final int component20() {
        return this.highlightColor;
    }

    public final float component21() {
        return this.highlight;
    }

    public final float component22() {
        return this.z;
    }

    @NotNull
    public final BuiltRadialGlass copy(float centerX, float centerY, float innerRadius, float outerRadius, float midAngle, float halfAngle, float corner, float feather, int color, int secondColor, float colorOffset, float globalAlpha, float fresnelPower, int fresnelColor, float baseAlpha, boolean fresnelInvert, float fresnelMix, float distortStrength, float blurRadius, int highlightColor, float highlight, float z) {
        return new BuiltRadialGlass(centerX, centerY, innerRadius, outerRadius, midAngle, halfAngle, corner, feather, color, secondColor, colorOffset, globalAlpha, fresnelPower, fresnelColor, baseAlpha, fresnelInvert, fresnelMix, distortStrength, blurRadius, highlightColor, highlight, z);
    }

    public static /* synthetic */ BuiltRadialGlass copy$default(BuiltRadialGlass builtRadialGlass, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, int n2, float f9, float f10, float f11, int n3, float f12, boolean bl, float f13, float f14, float f15, int n4, float f16, float f17, int n5, Object object) {
        if ((n5 & 1) != 0) {
            f = builtRadialGlass.centerX;
        }
        if ((n5 & 2) != 0) {
            f2 = builtRadialGlass.centerY;
        }
        if ((n5 & 4) != 0) {
            f3 = builtRadialGlass.innerRadius;
        }
        if ((n5 & 8) != 0) {
            f4 = builtRadialGlass.outerRadius;
        }
        if ((n5 & 0x10) != 0) {
            f5 = builtRadialGlass.midAngle;
        }
        if ((n5 & 0x20) != 0) {
            f6 = builtRadialGlass.halfAngle;
        }
        if ((n5 & 0x40) != 0) {
            f7 = builtRadialGlass.corner;
        }
        if ((n5 & 0x80) != 0) {
            f8 = builtRadialGlass.feather;
        }
        if ((n5 & 0x100) != 0) {
            n = builtRadialGlass.color;
        }
        if ((n5 & 0x200) != 0) {
            n2 = builtRadialGlass.secondColor;
        }
        if ((n5 & 0x400) != 0) {
            f9 = builtRadialGlass.colorOffset;
        }
        if ((n5 & 0x800) != 0) {
            f10 = builtRadialGlass.globalAlpha;
        }
        if ((n5 & 0x1000) != 0) {
            f11 = builtRadialGlass.fresnelPower;
        }
        if ((n5 & 0x2000) != 0) {
            n3 = builtRadialGlass.fresnelColor;
        }
        if ((n5 & 0x4000) != 0) {
            f12 = builtRadialGlass.baseAlpha;
        }
        if ((n5 & 0x8000) != 0) {
            bl = builtRadialGlass.fresnelInvert;
        }
        if ((n5 & 0x10000) != 0) {
            f13 = builtRadialGlass.fresnelMix;
        }
        if ((n5 & 0x20000) != 0) {
            f14 = builtRadialGlass.distortStrength;
        }
        if ((n5 & 0x40000) != 0) {
            f15 = builtRadialGlass.blurRadius;
        }
        if ((n5 & 0x80000) != 0) {
            n4 = builtRadialGlass.highlightColor;
        }
        if ((n5 & 0x100000) != 0) {
            f16 = builtRadialGlass.highlight;
        }
        if ((n5 & 0x200000) != 0) {
            f17 = builtRadialGlass.z;
        }
        return builtRadialGlass.copy(f, f2, f3, f4, f5, f6, f7, f8, n, n2, f9, f10, f11, n3, f12, bl, f13, f14, f15, n4, f16, f17);
    }

    @NotNull
    public String toString() {
        return "BuiltRadialGlass(centerX=" + this.centerX + ", centerY=" + this.centerY + ", innerRadius=" + this.innerRadius + ", outerRadius=" + this.outerRadius + ", midAngle=" + this.midAngle + ", halfAngle=" + this.halfAngle + ", corner=" + this.corner + ", feather=" + this.feather + ", color=" + this.color + ", secondColor=" + this.secondColor + ", colorOffset=" + this.colorOffset + ", globalAlpha=" + this.globalAlpha + ", fresnelPower=" + this.fresnelPower + ", fresnelColor=" + this.fresnelColor + ", baseAlpha=" + this.baseAlpha + ", fresnelInvert=" + this.fresnelInvert + ", fresnelMix=" + this.fresnelMix + ", distortStrength=" + this.distortStrength + ", blurRadius=" + this.blurRadius + ", highlightColor=" + this.highlightColor + ", highlight=" + this.highlight + ", z=" + this.z + ")";
    }

    public int hashCode() {
        int result = Float.hashCode(this.centerX);
        result = result * 31 + Float.hashCode(this.centerY);
        result = result * 31 + Float.hashCode(this.innerRadius);
        result = result * 31 + Float.hashCode(this.outerRadius);
        result = result * 31 + Float.hashCode(this.midAngle);
        result = result * 31 + Float.hashCode(this.halfAngle);
        result = result * 31 + Float.hashCode(this.corner);
        result = result * 31 + Float.hashCode(this.feather);
        result = result * 31 + Integer.hashCode(this.color);
        result = result * 31 + Integer.hashCode(this.secondColor);
        result = result * 31 + Float.hashCode(this.colorOffset);
        result = result * 31 + Float.hashCode(this.globalAlpha);
        result = result * 31 + Float.hashCode(this.fresnelPower);
        result = result * 31 + Integer.hashCode(this.fresnelColor);
        result = result * 31 + Float.hashCode(this.baseAlpha);
        result = result * 31 + Boolean.hashCode(this.fresnelInvert);
        result = result * 31 + Float.hashCode(this.fresnelMix);
        result = result * 31 + Float.hashCode(this.distortStrength);
        result = result * 31 + Float.hashCode(this.blurRadius);
        result = result * 31 + Integer.hashCode(this.highlightColor);
        result = result * 31 + Float.hashCode(this.highlight);
        result = result * 31 + Float.hashCode(this.z);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltRadialGlass)) {
            return false;
        }
        BuiltRadialGlass builtRadialGlass = (BuiltRadialGlass)other;
        if (Float.compare(this.centerX, builtRadialGlass.centerX) != 0) {
            return false;
        }
        if (Float.compare(this.centerY, builtRadialGlass.centerY) != 0) {
            return false;
        }
        if (Float.compare(this.innerRadius, builtRadialGlass.innerRadius) != 0) {
            return false;
        }
        if (Float.compare(this.outerRadius, builtRadialGlass.outerRadius) != 0) {
            return false;
        }
        if (Float.compare(this.midAngle, builtRadialGlass.midAngle) != 0) {
            return false;
        }
        if (Float.compare(this.halfAngle, builtRadialGlass.halfAngle) != 0) {
            return false;
        }
        if (Float.compare(this.corner, builtRadialGlass.corner) != 0) {
            return false;
        }
        if (Float.compare(this.feather, builtRadialGlass.feather) != 0) {
            return false;
        }
        if (this.color != builtRadialGlass.color) {
            return false;
        }
        if (this.secondColor != builtRadialGlass.secondColor) {
            return false;
        }
        if (Float.compare(this.colorOffset, builtRadialGlass.colorOffset) != 0) {
            return false;
        }
        if (Float.compare(this.globalAlpha, builtRadialGlass.globalAlpha) != 0) {
            return false;
        }
        if (Float.compare(this.fresnelPower, builtRadialGlass.fresnelPower) != 0) {
            return false;
        }
        if (this.fresnelColor != builtRadialGlass.fresnelColor) {
            return false;
        }
        if (Float.compare(this.baseAlpha, builtRadialGlass.baseAlpha) != 0) {
            return false;
        }
        if (this.fresnelInvert != builtRadialGlass.fresnelInvert) {
            return false;
        }
        if (Float.compare(this.fresnelMix, builtRadialGlass.fresnelMix) != 0) {
            return false;
        }
        if (Float.compare(this.distortStrength, builtRadialGlass.distortStrength) != 0) {
            return false;
        }
        if (Float.compare(this.blurRadius, builtRadialGlass.blurRadius) != 0) {
            return false;
        }
        if (this.highlightColor != builtRadialGlass.highlightColor) {
            return false;
        }
        if (Float.compare(this.highlight, builtRadialGlass.highlight) != 0) {
            return false;
        }
        return Float.compare(this.z, builtRadialGlass.z) == 0;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006\u00a8\u0006\b"}, d2={"Lrtx/kimiko/utils/render/render2d/radialglass/BuiltRadialGlass.Companion;", "", "<init>", "()V", "", "MAX_HALF_ANGLE", "F", "DEFAULT_FEATHER", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

