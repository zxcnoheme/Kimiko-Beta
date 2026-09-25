/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.outline.outlineglass;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.render2d.outline.outlineglass.GlassOutlineRenderer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\b\u0018\u0000 I2\u00020\u0001:\u0001IB\u00a7\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\f\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aB\u008b\u0001\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\f\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001dJ\u0017\u0010!\u001a\u00020 2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u00a2\u0006\u0004\b!\u0010\"J\r\u0010#\u001a\u00020\u0012\u00a2\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b%\u0010&J\u0010\u0010'\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b'\u0010&J\u0010\u0010(\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b(\u0010&J\u0010\u0010)\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b)\u0010&J\u0010\u0010*\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b*\u0010&J\u0010\u0010+\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b+\u0010&J\u0010\u0010,\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b,\u0010&J\u0010\u0010-\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b-\u0010&J\u0010\u0010.\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b.\u0010&J\u0010\u0010/\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\b/\u00100J\u0010\u00101\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b1\u0010&J\u0010\u00102\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b2\u0010&J\u0010\u00103\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\b3\u00100J\u0010\u00104\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b4\u0010&J\u0010\u00105\u001a\u00020\u0012H\u00c6\u0003\u00a2\u0006\u0004\b5\u0010$J\u0010\u00106\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b6\u0010&J\u0010\u00107\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b7\u0010&J\u0010\u00108\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b8\u0010&J\u0010\u00109\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b9\u0010&J\u0010\u0010:\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b:\u0010&J\u00d8\u0001\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010\u0011\u001a\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u00022\b\b\u0002\u0010\u0018\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b;\u0010<J\u001b\u0010>\u001a\u00020\u00122\b\u0010=\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b>\u0010?J\u0011\u0010@\u001a\u00020\fH\u00d6\u0081\u0004\u00a2\u0006\u0004\b@\u00100J\u0011\u0010B\u001a\u00020AH\u00d6\u0081\u0004\u00a2\u0006\u0004\bB\u0010CR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010F\u001a\u0004\b\u0003\u0010&R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010F\u001a\u0004\b\u0004\u0010&R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010F\u001a\u0004\b\u0005\u0010&R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010F\u001a\u0004\b\u0006\u0010&R%\u0010\u0007\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010F\u001a\u0004\b\u0007\u0010&R%\u0010\b\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010F\u001a\u0004\b\b\u0010&R%\u0010\t\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010F\u001a\u0004\b\t\u0010&R%\u0010\n\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010F\u001a\u0004\b\n\u0010&R%\u0010\u000b\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010F\u001a\u0004\b\u000b\u0010&R%\u0010\r\u001a\u00020\f8\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010G\u001a\u0004\b\r\u00100R%\u0010\u000e\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010F\u001a\u0004\b\u000e\u0010&R%\u0010\u000f\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010F\u001a\u0004\b\u000f\u0010&R%\u0010\u0010\u001a\u00020\f8\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010G\u001a\u0004\b\u0010\u00100R%\u0010\u0011\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0011\u00a2\u0006\f\n\u0004\b\u0011\u0010F\u001a\u0004\b\u0011\u0010&R%\u0010\u0013\u001a\u00020\u00128\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0013\u00a2\u0006\f\n\u0004\b\u0013\u0010H\u001a\u0004\b\u0013\u0010$R%\u0010\u0014\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0014\u00a2\u0006\f\n\u0004\b\u0014\u0010F\u001a\u0004\b\u0014\u0010&R%\u0010\u0015\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0015\u00a2\u0006\f\n\u0004\b\u0015\u0010F\u001a\u0004\b\u0015\u0010&R%\u0010\u0016\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0016\u00a2\u0006\f\n\u0004\b\u0016\u0010F\u001a\u0004\b\u0016\u0010&R%\u0010\u0017\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0017\u00a2\u0006\f\n\u0004\b\u0017\u0010F\u001a\u0004\b\u0017\u0010&R%\u0010\u0018\u001a\u00020\u00028\u0007z\f\bD\u0012\b\bE\u0012\u0004\b\b(\u0018\u00a2\u0006\f\n\u0004\b\u0018\u0010F\u001a\u0004\b\u0018\u0010&\u00a8\u0006J"}, d2={"Lrtx/kimiko/utils/render/render2d/outline/outlineglass/BuiltGlassOutline;", "", "", "x", "y", "width", "height", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "thickness", "", "color", "globalAlpha", "fresnelPower", "fresnelColor", "baseAlpha", "", "fresnelInvert", "fresnelMix", "distortStrength", "squirt", "smoothness", "z", "<init>", "(FFFFFFFFFIFFIFZFFFFF)V", "", "radius", "(FFFF[FFIFFIFZFFFF)V", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "visible", "()Z", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "()I", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "copy", "(FFFFFFFFFIFFIFZFFFFF)Lrtx/kimiko/utils/render/render2d/outline/outlineglass/BuiltGlassOutline;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltGlassOutline {
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
    private final float thickness;
    private final int color;
    private final float globalAlpha;
    private final float fresnelPower;
    private final int fresnelColor;
    private final float baseAlpha;
    private final boolean fresnelInvert;
    private final float fresnelMix;
    private final float distortStrength;
    private final float squirt;
    private final float smoothness;
    private final float z;
    public static final float DEFAULT_SMOOTHNESS = 0.5f;

    public BuiltGlassOutline(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, float thickness, int color, float globalAlpha, float fresnelPower, int fresnelColor, float baseAlpha, boolean fresnelInvert, float fresnelMix, float distortStrength, float squirt, float smoothness, float z) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radiusTopLeft = radiusTopLeft;
        this.radiusTopRight = radiusTopRight;
        this.radiusBottomRight = radiusBottomRight;
        this.radiusBottomLeft = radiusBottomLeft;
        this.thickness = thickness;
        this.color = color;
        this.globalAlpha = globalAlpha;
        this.fresnelPower = fresnelPower;
        this.fresnelColor = fresnelColor;
        this.baseAlpha = baseAlpha;
        this.fresnelInvert = fresnelInvert;
        this.fresnelMix = fresnelMix;
        this.distortStrength = distortStrength;
        this.squirt = squirt;
        this.smoothness = smoothness;
        this.z = z;
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

    @JvmName(name="thickness")
    public final float thickness() {
        return this.thickness;
    }

    @JvmName(name="color")
    public final int color() {
        return this.color;
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

    @JvmName(name="squirt")
    public final float squirt() {
        return this.squirt;
    }

    @JvmName(name="smoothness")
    public final float smoothness() {
        return this.smoothness;
    }

    @JvmName(name="z")
    public final float z() {
        return this.z;
    }

    public BuiltGlassOutline(float x, float y, float width, float height, @Nullable float[] radius, float thickness, int color, float globalAlpha, float fresnelPower, int fresnelColor, float baseAlpha, boolean fresnelInvert, float fresnelMix, float distortStrength, float squirt, float z) {
        this(x, y, width, height, BuiltGlassOutline.Companion.radiusValue(radius, 0), BuiltGlassOutline.Companion.radiusValue(radius, 1), BuiltGlassOutline.Companion.radiusValue(radius, 2), BuiltGlassOutline.Companion.radiusValue(radius, 3), thickness, color, globalAlpha, fresnelPower, fresnelColor, baseAlpha, fresnelInvert, fresnelMix, distortStrength, squirt, 0.5f, z);
    }

    public final void render(@Nullable DrawContext graphics) {
        GlassOutlineRenderer.Companion.getInstance().draw(graphics, this);
    }

    public final boolean visible() {
        return this.width > 0.0f && this.height > 0.0f && this.thickness > 0.0f && this.globalAlpha > 0.0f && (this.color >>> 24 != 0 || this.baseAlpha > 0.0f || this.fresnelColor >>> 24 != 0);
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
        return this.thickness;
    }

    public final int component10() {
        return this.color;
    }

    public final float component11() {
        return this.globalAlpha;
    }

    public final float component12() {
        return this.fresnelPower;
    }

    public final int component13() {
        return this.fresnelColor;
    }

    public final float component14() {
        return this.baseAlpha;
    }

    public final boolean component15() {
        return this.fresnelInvert;
    }

    public final float component16() {
        return this.fresnelMix;
    }

    public final float component17() {
        return this.distortStrength;
    }

    public final float component18() {
        return this.squirt;
    }

    public final float component19() {
        return this.smoothness;
    }

    public final float component20() {
        return this.z;
    }

    @NotNull
    public final BuiltGlassOutline copy(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, float thickness, int color, float globalAlpha, float fresnelPower, int fresnelColor, float baseAlpha, boolean fresnelInvert, float fresnelMix, float distortStrength, float squirt, float smoothness, float z) {
        return new BuiltGlassOutline(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, thickness, color, globalAlpha, fresnelPower, fresnelColor, baseAlpha, fresnelInvert, fresnelMix, distortStrength, squirt, smoothness, z);
    }

    public static /* synthetic */ BuiltGlassOutline copy$default(BuiltGlassOutline builtGlassOutline, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int n, float f10, float f11, int n2, float f12, boolean bl, float f13, float f14, float f15, float f16, float f17, int n3, Object object) {
        if ((n3 & 1) != 0) {
            f = builtGlassOutline.x;
        }
        if ((n3 & 2) != 0) {
            f2 = builtGlassOutline.y;
        }
        if ((n3 & 4) != 0) {
            f3 = builtGlassOutline.width;
        }
        if ((n3 & 8) != 0) {
            f4 = builtGlassOutline.height;
        }
        if ((n3 & 0x10) != 0) {
            f5 = builtGlassOutline.radiusTopLeft;
        }
        if ((n3 & 0x20) != 0) {
            f6 = builtGlassOutline.radiusTopRight;
        }
        if ((n3 & 0x40) != 0) {
            f7 = builtGlassOutline.radiusBottomRight;
        }
        if ((n3 & 0x80) != 0) {
            f8 = builtGlassOutline.radiusBottomLeft;
        }
        if ((n3 & 0x100) != 0) {
            f9 = builtGlassOutline.thickness;
        }
        if ((n3 & 0x200) != 0) {
            n = builtGlassOutline.color;
        }
        if ((n3 & 0x400) != 0) {
            f10 = builtGlassOutline.globalAlpha;
        }
        if ((n3 & 0x800) != 0) {
            f11 = builtGlassOutline.fresnelPower;
        }
        if ((n3 & 0x1000) != 0) {
            n2 = builtGlassOutline.fresnelColor;
        }
        if ((n3 & 0x2000) != 0) {
            f12 = builtGlassOutline.baseAlpha;
        }
        if ((n3 & 0x4000) != 0) {
            bl = builtGlassOutline.fresnelInvert;
        }
        if ((n3 & 0x8000) != 0) {
            f13 = builtGlassOutline.fresnelMix;
        }
        if ((n3 & 0x10000) != 0) {
            f14 = builtGlassOutline.distortStrength;
        }
        if ((n3 & 0x20000) != 0) {
            f15 = builtGlassOutline.squirt;
        }
        if ((n3 & 0x40000) != 0) {
            f16 = builtGlassOutline.smoothness;
        }
        if ((n3 & 0x80000) != 0) {
            f17 = builtGlassOutline.z;
        }
        return builtGlassOutline.copy(f, f2, f3, f4, f5, f6, f7, f8, f9, n, f10, f11, n2, f12, bl, f13, f14, f15, f16, f17);
    }

    @NotNull
    public String toString() {
        return "BuiltGlassOutline(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", radiusTopLeft=" + this.radiusTopLeft + ", radiusTopRight=" + this.radiusTopRight + ", radiusBottomRight=" + this.radiusBottomRight + ", radiusBottomLeft=" + this.radiusBottomLeft + ", thickness=" + this.thickness + ", color=" + this.color + ", globalAlpha=" + this.globalAlpha + ", fresnelPower=" + this.fresnelPower + ", fresnelColor=" + this.fresnelColor + ", baseAlpha=" + this.baseAlpha + ", fresnelInvert=" + this.fresnelInvert + ", fresnelMix=" + this.fresnelMix + ", distortStrength=" + this.distortStrength + ", squirt=" + this.squirt + ", smoothness=" + this.smoothness + ", z=" + this.z + ")";
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
        result = result * 31 + Float.hashCode(this.thickness);
        result = result * 31 + Integer.hashCode(this.color);
        result = result * 31 + Float.hashCode(this.globalAlpha);
        result = result * 31 + Float.hashCode(this.fresnelPower);
        result = result * 31 + Integer.hashCode(this.fresnelColor);
        result = result * 31 + Float.hashCode(this.baseAlpha);
        result = result * 31 + Boolean.hashCode(this.fresnelInvert);
        result = result * 31 + Float.hashCode(this.fresnelMix);
        result = result * 31 + Float.hashCode(this.distortStrength);
        result = result * 31 + Float.hashCode(this.squirt);
        result = result * 31 + Float.hashCode(this.smoothness);
        result = result * 31 + Float.hashCode(this.z);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltGlassOutline)) {
            return false;
        }
        BuiltGlassOutline builtGlassOutline = (BuiltGlassOutline)other;
        if (Float.compare(this.x, builtGlassOutline.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtGlassOutline.y) != 0) {
            return false;
        }
        if (Float.compare(this.width, builtGlassOutline.width) != 0) {
            return false;
        }
        if (Float.compare(this.height, builtGlassOutline.height) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopLeft, builtGlassOutline.radiusTopLeft) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopRight, builtGlassOutline.radiusTopRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomRight, builtGlassOutline.radiusBottomRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomLeft, builtGlassOutline.radiusBottomLeft) != 0) {
            return false;
        }
        if (Float.compare(this.thickness, builtGlassOutline.thickness) != 0) {
            return false;
        }
        if (this.color != builtGlassOutline.color) {
            return false;
        }
        if (Float.compare(this.globalAlpha, builtGlassOutline.globalAlpha) != 0) {
            return false;
        }
        if (Float.compare(this.fresnelPower, builtGlassOutline.fresnelPower) != 0) {
            return false;
        }
        if (this.fresnelColor != builtGlassOutline.fresnelColor) {
            return false;
        }
        if (Float.compare(this.baseAlpha, builtGlassOutline.baseAlpha) != 0) {
            return false;
        }
        if (this.fresnelInvert != builtGlassOutline.fresnelInvert) {
            return false;
        }
        if (Float.compare(this.fresnelMix, builtGlassOutline.fresnelMix) != 0) {
            return false;
        }
        if (Float.compare(this.distortStrength, builtGlassOutline.distortStrength) != 0) {
            return false;
        }
        if (Float.compare(this.squirt, builtGlassOutline.squirt) != 0) {
            return false;
        }
        if (Float.compare(this.smoothness, builtGlassOutline.smoothness) != 0) {
            return false;
        }
        return Float.compare(this.z, builtGlassOutline.z) == 0;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J!\u0010\t\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2={"Lrtx/kimiko/utils/render/render2d/outline/outlineglass/BuiltGlassOutline.Companion;", "", "<init>", "()V", "", "radius", "", "index", "", "radiusValue", "([FI)F", "DEFAULT_SMOOTHNESS", "F", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float radiusValue(float[] radius, int index) {
            if (radius == null || index < 0 || index >= radius.length) {
                return 0.0f;
            }
            return radius[index];
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

