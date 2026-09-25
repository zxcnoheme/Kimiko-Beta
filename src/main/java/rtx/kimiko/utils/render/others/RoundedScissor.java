/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 */
package rtx.kimiko.utils.render.others;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import rtx.kimiko.utils.render.render2d.PoseCache;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010\u0018\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003JS\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b\u000f\u0010\u0010J]\u0010\u000f\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b\u000f\u0010\u0013Je\u0010\u000f\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b\u000f\u0010\u0015J]\u0010\u0016\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b\u0016\u0010\u0013J\u0013\u0010\u0017\u001a\u00020\rH\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b\u0017\u0010\u0003J\u0013\u0010\u0018\u001a\u00020\rH\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b\u0018\u0010\u0003J\u0013\u0010\u0014\u001a\u00020\u0004H\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b\u0014\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u0004H\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b\u001a\u0010\u0019J\u001f\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0013\u0010!\u001a\u00020 H\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b!\u0010\"J\u0013\u0010#\u001a\u00020\u0004H\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b#\u0010\u0019J\u0013\u0010$\u001a\u00020\u0004H\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b$\u0010\u0019J\u0013\u0010%\u001a\u00020\u0004H\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b%\u0010\u0019J\u0013\u0010&\u001a\u00020\u0004H\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b&\u0010\u0019J\u0013\u0010'\u001a\u00020\u0004H\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b'\u0010\u0019J\u0013\u0010(\u001a\u00020\u0004H\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b(\u0010\u0019J\u0013\u0010)\u001a\u00020\u0004H\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b)\u0010\u0019J\u0013\u0010*\u001a\u00020\u0004H\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b*\u0010\u0019J\u0013\u0010+\u001a\u00020\u0004H\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b+\u0010\u0019J\u0013\u0010,\u001a\u00020\u0004H\u0007b\u0002\b\u000e\u00a2\u0006\u0004\b,\u0010\u0019R\u0014\u0010.\u001a\u00020-8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010/R\u0016\u0010#\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u00100R\u0016\u0010$\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u00100R\u0016\u0010%\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u00100R\u0016\u0010&\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u00100R\u0016\u0010'\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u00100R\u0016\u0010(\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u00100R\u0016\u0010)\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u00100R\u0016\u0010*\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u00100R\u0016\u0010+\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u00100R\u0016\u0010,\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u00100R\u0016\u00101\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u00100R\u0016\u00102\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u00100R\u0016\u00103\u001a\u00020 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u00104R\u0016\u00105\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b5\u00100R\u0016\u00106\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u00100R\u0016\u00107\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00100R\u0016\u00108\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b8\u00100R\u0018\u00109\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u0010:R\u0016\u0010;\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b;\u0010/R\u0014\u0010<\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0014\u0010>\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010=R\u0014\u0010?\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010=R\u0014\u0010@\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u0010=R\u0014\u0010A\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bA\u0010=R\u0014\u0010B\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010=R\u0014\u0010C\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bC\u0010=R\u0014\u0010D\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010=R\u0014\u0010E\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010=R\u0014\u0010F\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u0010=R\u0014\u0010G\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010=R\u0014\u0010H\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010=R\u0014\u0010J\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010KR\u0014\u0010L\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010=R\u0014\u0010M\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bM\u0010=R\u0014\u0010N\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010=R\u0014\u0010O\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bO\u0010=R\u001c\u0010Q\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0P8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bQ\u0010R\u00a8\u0006S"}, d2={"Lrtx/kimiko/utils/render/others/RoundedScissor;", "", "<init>", "()V", "", "sx", "sy", "sw", "sh", "rtl", "rtr", "rbr", "rbl", "", "Lkotlin/jvm/JvmStatic;", "push", "(FFFFFFFF)V", "Lnet/minecraft/DrawContext;", "graphics", "(Lnet/minecraft/DrawContext;FFFFFFFF)V", "fadeTop", "(Lnet/minecraft/DrawContext;FFFFFFFFF)V", "pushNested", "popNested", "pop", "()F", "localFadeTop", "Lorg/joml/Matrix3x2f;", "pose", "", "localClipFor", "(Lorg/joml/Matrix3x2f;)[F", "", "isEnabled", "()Z", "x", "y", "width", "height", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "cos", "sin", "", "NESTED_CAPACITY", "I", "F", "fade", "fadeLocal", "enabled", "Z", "localX", "localY", "localWidth", "localHeight", "localPose", "Lorg/joml/Matrix3x2f;", "nestedDepth", "savedX", "[F", "savedY", "savedWidth", "savedHeight", "savedRadiusTopLeft", "savedRadiusTopRight", "savedRadiusBottomRight", "savedRadiusBottomLeft", "savedCos", "savedSin", "savedFade", "savedFadeLocal", "", "savedEnabled", "[Z", "savedLocalX", "savedLocalY", "savedLocalWidth", "savedLocalHeight", "", "savedLocalPose", "[Lorg/joml/Matrix3x2f;", "rtx.kimiko:kimiko"})
public final class RoundedScissor {
    @NotNull
    public static final RoundedScissor INSTANCE = new RoundedScissor();
    private static final int NESTED_CAPACITY = 8;
    private static float x;
    private static float y;
    private static float width;
    private static float height;
    private static float radiusTopLeft;
    private static float radiusTopRight;
    private static float radiusBottomRight;
    private static float radiusBottomLeft;
    private static float cos;
    private static float sin;
    private static float fade;
    private static float fadeLocal;
    private static boolean enabled;
    private static float localX;
    private static float localY;
    private static float localWidth;
    private static float localHeight;
    @Nullable
    private static Matrix3x2f localPose;
    private static int nestedDepth;
    @NotNull
    private static final float[] savedX;
    @NotNull
    private static final float[] savedY;
    @NotNull
    private static final float[] savedWidth;
    @NotNull
    private static final float[] savedHeight;
    @NotNull
    private static final float[] savedRadiusTopLeft;
    @NotNull
    private static final float[] savedRadiusTopRight;
    @NotNull
    private static final float[] savedRadiusBottomRight;
    @NotNull
    private static final float[] savedRadiusBottomLeft;
    @NotNull
    private static final float[] savedCos;
    @NotNull
    private static final float[] savedSin;
    @NotNull
    private static final float[] savedFade;
    @NotNull
    private static final float[] savedFadeLocal;
    @NotNull
    private static final boolean[] savedEnabled;
    @NotNull
    private static final float[] savedLocalX;
    @NotNull
    private static final float[] savedLocalY;
    @NotNull
    private static final float[] savedLocalWidth;
    @NotNull
    private static final float[] savedLocalHeight;
    @NotNull
    private static final Matrix3x2f[] savedLocalPose;

    private RoundedScissor() {
    }

    @JvmStatic
    public static final void push(float sx, float sy, float sw, float sh, float rtl, float rtr, float rbr, float rbl) {
        float s = Render2DCoordinateSpace.guiIndependentScale() * Render2DCoordinateSpace.uiZoom();
        x = Render2DCoordinateSpace.toGuiX(sx);
        y = Render2DCoordinateSpace.toGuiY(sy);
        width = sw * s;
        height = sh * s;
        radiusTopLeft = rtl * s;
        radiusTopRight = rtr * s;
        radiusBottomRight = rbr * s;
        radiusBottomLeft = rbl * s;
        cos = 1.0f;
        sin = 0.0f;
        fade = 0.0f;
        fadeLocal = 0.0f;
        localPose = null;
        enabled = true;
    }

    @JvmStatic
    public static final void push(@Nullable DrawContext graphics, float sx, float sy, float sw, float sh, float rtl, float rtr, float rbr, float rbl) {
        RoundedScissor.push(graphics, sx, sy, sw, sh, rtl, rtr, rbr, rbl, 0.0f);
    }

    @JvmStatic
    public static final void push(@Nullable DrawContext graphics, float sx, float sy, float sw, float sh, float rtl, float rtr, float rbr, float rbl, float fadeTop) {
        if (graphics == null) {
            RoundedScissor.push(sx, sy, sw, sh, rtl, rtr, rbr, rbl);
            fade = fadeTop;
            return;
        }
        Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
        float scaleX = (float)Math.sqrt(pose.m00() * pose.m00() + pose.m01() * pose.m01());
        float scaleY = (float)Math.sqrt(pose.m10() * pose.m10() + pose.m11() * pose.m11());
        float localCenterX = sx + sw * 0.5f;
        float localCenterY = sy + sh * 0.5f;
        float centerX = pose.m00() * localCenterX + pose.m10() * localCenterY + pose.m20();
        float centerY = pose.m01() * localCenterX + pose.m11() * localCenterY + pose.m21();
        width = sw * scaleX;
        height = sh * scaleY;
        x = centerX - width * 0.5f;
        y = centerY - height * 0.5f;
        if (scaleX > 1.0E-5f) {
            cos = pose.m00() / scaleX;
            sin = pose.m01() / scaleX;
        } else {
            cos = 1.0f;
            sin = 0.0f;
        }
        localX = sx;
        localY = sy;
        localWidth = sw;
        localHeight = sh;
        localPose = PoseCache.snapshot(pose);
        float rScale = Math.min(scaleX, scaleY);
        fade = fadeTop * scaleY;
        fadeLocal = fadeTop;
        radiusTopLeft = rtl * rScale;
        radiusTopRight = rtr * rScale;
        radiusBottomRight = rbr * rScale;
        radiusBottomLeft = rbl * rScale;
        enabled = true;
    }

    @JvmStatic
    public static final void pushNested(@Nullable DrawContext graphics, float sx, float sy, float sw, float sh, float rtl, float rtr, float rbr, float rbl) {
        if (nestedDepth >= 8) {
            return;
        }
        int n = nestedDepth;
        nestedDepth = n + 1;
        int slot = n;
        RoundedScissor.savedX[slot] = x;
        RoundedScissor.savedY[slot] = y;
        RoundedScissor.savedWidth[slot] = width;
        RoundedScissor.savedHeight[slot] = height;
        RoundedScissor.savedRadiusTopLeft[slot] = radiusTopLeft;
        RoundedScissor.savedRadiusTopRight[slot] = radiusTopRight;
        RoundedScissor.savedRadiusBottomRight[slot] = radiusBottomRight;
        RoundedScissor.savedRadiusBottomLeft[slot] = radiusBottomLeft;
        RoundedScissor.savedCos[slot] = cos;
        RoundedScissor.savedSin[slot] = sin;
        RoundedScissor.savedFade[slot] = fade;
        RoundedScissor.savedFadeLocal[slot] = fadeLocal;
        RoundedScissor.savedEnabled[slot] = enabled;
        RoundedScissor.savedLocalX[slot] = localX;
        RoundedScissor.savedLocalY[slot] = localY;
        RoundedScissor.savedLocalWidth[slot] = localWidth;
        RoundedScissor.savedLocalHeight[slot] = localHeight;
        RoundedScissor.savedLocalPose[slot] = localPose;
        RoundedScissor.push(graphics, sx, sy, sw, sh, rtl, rtr, rbr, rbl);
    }

    @JvmStatic
    public static final void popNested() {
        if (nestedDepth <= 0) {
            return;
        }
        int slot = nestedDepth += -1;
        x = savedX[slot];
        y = savedY[slot];
        width = savedWidth[slot];
        height = savedHeight[slot];
        radiusTopLeft = savedRadiusTopLeft[slot];
        radiusTopRight = savedRadiusTopRight[slot];
        radiusBottomRight = savedRadiusBottomRight[slot];
        radiusBottomLeft = savedRadiusBottomLeft[slot];
        cos = savedCos[slot];
        sin = savedSin[slot];
        fade = savedFade[slot];
        fadeLocal = savedFadeLocal[slot];
        enabled = savedEnabled[slot];
        localX = savedLocalX[slot];
        localY = savedLocalY[slot];
        localWidth = savedLocalWidth[slot];
        localHeight = savedLocalHeight[slot];
        localPose = savedLocalPose[slot];
        RoundedScissor.savedLocalPose[slot] = null;
    }

    @JvmStatic
    public static final void pop() {
        enabled = false;
        fade = 0.0f;
        fadeLocal = 0.0f;
        localPose = null;
    }

    @JvmStatic
    public static final float fadeTop() {
        return fade;
    }

    @JvmStatic
    public static final float localFadeTop() {
        return fadeLocal;
    }

    @JvmStatic
    @Nullable
    public static final float[] localClipFor(@Nullable Matrix3x2f pose) {
        Matrix3x2f snapshot = localPose;
        if (!enabled || snapshot == null || pose == null || !Intrinsics.areEqual((Object)snapshot, (Object)pose)) {
            return null;
        }
        if (localWidth <= 0.0f || localHeight <= 0.0f) {
            return null;
        }
        float[] fArray = new float[]{localX, localY, localX + localWidth, localY + localHeight};
        return fArray;
    }

    @JvmStatic
    public static final boolean isEnabled() {
        return enabled;
    }

    @JvmStatic
    public static final float x() {
        return x;
    }

    @JvmStatic
    public static final float y() {
        return y;
    }

    @JvmStatic
    public static final float width() {
        return width;
    }

    @JvmStatic
    public static final float height() {
        return height;
    }

    @JvmStatic
    public static final float radiusTopLeft() {
        return radiusTopLeft;
    }

    @JvmStatic
    public static final float radiusTopRight() {
        return radiusTopRight;
    }

    @JvmStatic
    public static final float radiusBottomRight() {
        return radiusBottomRight;
    }

    @JvmStatic
    public static final float radiusBottomLeft() {
        return radiusBottomLeft;
    }

    @JvmStatic
    public static final float cos() {
        return cos;
    }

    @JvmStatic
    public static final float sin() {
        return sin;
    }

    static {
        cos = 1.0f;
        savedX = new float[8];
        savedY = new float[8];
        savedWidth = new float[8];
        savedHeight = new float[8];
        savedRadiusTopLeft = new float[8];
        savedRadiusTopRight = new float[8];
        savedRadiusBottomRight = new float[8];
        savedRadiusBottomLeft = new float[8];
        savedCos = new float[8];
        savedSin = new float[8];
        savedFade = new float[8];
        savedFadeLocal = new float[8];
        savedEnabled = new boolean[8];
        savedLocalX = new float[8];
        savedLocalY = new float[8];
        savedLocalWidth = new float[8];
        savedLocalHeight = new float[8];
        savedLocalPose = new Matrix3x2f[8];
    }
}

