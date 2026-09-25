/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.util.Window
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fc
 */
package rtx.kimiko.utils.render.render2d;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.util.Window;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u001a\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u0010\u001a\u00020\u000fH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0014H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001b\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u001d\u0010\bJ\u001b\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u001f\u0010 J\u001b\u0010\"\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\"\u0010\bJ\u001b\u0010$\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b$\u0010\bJ\u001b\u0010%\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b%\u0010 J\u001b\u0010&\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b&\u0010 J\u001b\u0010)\u001a\u00020'2\u0006\u0010(\u001a\u00020'H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b)\u0010*J\u001b\u0010,\u001a\u00020'2\u0006\u0010+\u001a\u00020'H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b,\u0010*J\u0013\u0010-\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b-\u0010\u000eJ\u0013\u0010.\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b.\u0010\u000eJ\u001b\u00100\u001a\u00020\u00042\u0006\u0010/\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b0\u0010\bJ\u001b\u00101\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b1\u0010\bJ\u001b\u00102\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b2\u0010\bJ\u001b\u00103\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b3\u0010\bJ\u001b\u00104\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b4\u0010\bJ\u0013\u00105\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b5\u0010\u000eJ\u0013\u00106\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b6\u0010\u000eJ\u0013\u00107\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b7\u0010\u000eJ\u0013\u00108\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b8\u0010\u000eJ\u0017\u00109\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b9\u0010\bJ\u0017\u0010:\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b:\u0010\bJ\u0013\u0010;\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b;\u0010\u000eJ\u0013\u0010<\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b<\u0010\u000eJ\u0013\u0010=\u001a\u00020\u001eH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b=\u0010>R\u0014\u0010?\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b?\u0010@R\u0016\u0010\r\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\r\u0010@\u00a8\u0006A"}, d2={"Lrtx/kimiko/utils/render/render2d/Render2DCoordinateSpace;", "", "<init>", "()V", "", "zoom", "Lkotlin/jvm/JvmStatic;", "pushUiZoom", "(F)F", "previous", "", "popUiZoom", "(F)V", "uiZoom", "()F", "", "zoomed", "()Z", "Lnet/minecraft/DrawContext;", "graphics", "Lorg/joml/Matrix3x2f;", "pose", "(Lnet/minecraft/DrawContext;)Lorg/joml/Matrix3x2f;", "applyGuiScaleIndependence", "(Lorg/joml/Matrix3x2f;)V", "scale", "space", "(F)Lorg/joml/Matrix3x2f;", "value", "toGui", "", "toGuiInt", "(F)I", "designX", "toGuiX", "designY", "toGuiY", "toGuiIntX", "toGuiIntY", "", "guiX", "designXFromGui", "(D)D", "guiY", "designYFromGui", "pixelScale", "fontDensity", "designSize", "pixelSize", "pixelX", "pixelY", "normalizedDesignX", "normalizedDesignY", "designScreenWidth", "designScreenHeight", "designCenterX", "designCenterY", "zoomX", "zoomY", "guiIndependentScale", "designGuiScale", "guiScale", "()I", "DESIGN_GUI_SCALE", "F", "rtx.kimiko:kimiko"})
public final class Render2DCoordinateSpace {
    @NotNull
    public static final Render2DCoordinateSpace INSTANCE = new Render2DCoordinateSpace();
    private static final float DESIGN_GUI_SCALE = 2.0f;
    private static float uiZoom = 1.0f;

    private Render2DCoordinateSpace() {
    }

    @JvmStatic
    public static final float pushUiZoom(float zoom) {
        float previous = uiZoom;
        uiZoom = zoom > 0.0f ? zoom : 1.0f;
        return previous;
    }

    @JvmStatic
    public static final void popUiZoom(float previous) {
        uiZoom = previous > 0.0f ? previous : 1.0f;
    }

    @JvmStatic
    public static final float uiZoom() {
        return uiZoom;
    }

    @JvmStatic
    public static final boolean zoomed() {
        return Math.abs(uiZoom - 1.0f) > 1.0E-4f;
    }

    @JvmStatic
    @NotNull
    public static final Matrix3x2f pose(@NotNull DrawContext graphics) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        float scale = Render2DCoordinateSpace.guiIndependentScale();
        Matrix3x2f base = new Matrix3x2f((Matrix3x2fc)graphics.getMatrices());
        if (scale == 1.0f && !Render2DCoordinateSpace.zoomed()) {
            return base;
        }
        Matrix3x2f matrix3x2f = INSTANCE.space(scale).mul((Matrix3x2fc)base);
        Intrinsics.checkNotNullExpressionValue((Object)matrix3x2f, (String)"mul(...)");
        return matrix3x2f;
    }

    @JvmStatic
    public static final void applyGuiScaleIndependence(@NotNull Matrix3x2f pose) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        float scale = Render2DCoordinateSpace.guiIndependentScale();
        if (scale == 1.0f && !Render2DCoordinateSpace.zoomed()) {
            return;
        }
        pose.set((Matrix3x2fc)INSTANCE.space(scale).mul((Matrix3x2fc)pose));
    }

    private final Matrix3x2f space(float scale) {
        Matrix3x2f matrix = new Matrix3x2f();
        if (!(scale == 1.0f)) {
            matrix.scale(scale);
        }
        if (Render2DCoordinateSpace.zoomed()) {
            float cx = Render2DCoordinateSpace.designCenterX();
            float cy = Render2DCoordinateSpace.designCenterY();
            matrix.translate(cx, cy).scale(uiZoom, uiZoom).translate(-cx, -cy);
        }
        return matrix;
    }

    @JvmStatic
    public static final float toGui(float value) {
        return value * Render2DCoordinateSpace.guiIndependentScale() * uiZoom;
    }

    @JvmStatic
    public static final int toGuiInt(float value) {
        return Math.round(Render2DCoordinateSpace.toGui(value));
    }

    @JvmStatic
    public static final float toGuiX(float designX) {
        return INSTANCE.zoomX(designX) * Render2DCoordinateSpace.guiIndependentScale();
    }

    @JvmStatic
    public static final float toGuiY(float designY) {
        return INSTANCE.zoomY(designY) * Render2DCoordinateSpace.guiIndependentScale();
    }

    @JvmStatic
    public static final int toGuiIntX(float designX) {
        return Math.round(Render2DCoordinateSpace.toGuiX(designX));
    }

    @JvmStatic
    public static final int toGuiIntY(float designY) {
        return Math.round(Render2DCoordinateSpace.toGuiY(designY));
    }

    @JvmStatic
    public static final double designXFromGui(double guiX) {
        double design = guiX / (double)Render2DCoordinateSpace.guiIndependentScale();
        if (!Render2DCoordinateSpace.zoomed()) {
            return design;
        }
        double cx = Render2DCoordinateSpace.designCenterX();
        return cx + (design - cx) / (double)uiZoom;
    }

    @JvmStatic
    public static final double designYFromGui(double guiY) {
        double design = guiY / (double)Render2DCoordinateSpace.guiIndependentScale();
        if (!Render2DCoordinateSpace.zoomed()) {
            return design;
        }
        double cy = Render2DCoordinateSpace.designCenterY();
        return cy + (design - cy) / (double)uiZoom;
    }

    @JvmStatic
    public static final float pixelScale() {
        return 2.0f * uiZoom;
    }

    @JvmStatic
    public static final float fontDensity() {
        float quantized = (float)Math.round(uiZoom * 4.0f) / 4.0f;
        return Math.max(0.5f, Math.min(3.0f, quantized));
    }

    @JvmStatic
    public static final float pixelSize(float designSize) {
        return designSize * Render2DCoordinateSpace.pixelScale();
    }

    @JvmStatic
    public static final float pixelX(float designX) {
        return INSTANCE.zoomX(designX) * 2.0f;
    }

    @JvmStatic
    public static final float pixelY(float designY) {
        return INSTANCE.zoomY(designY) * 2.0f;
    }

    @JvmStatic
    public static final float normalizedDesignX(float designX) {
        return INSTANCE.zoomX(designX) / Math.max(1.0f, Render2DCoordinateSpace.designScreenWidth());
    }

    @JvmStatic
    public static final float normalizedDesignY(float designY) {
        return INSTANCE.zoomY(designY) / Math.max(1.0f, Render2DCoordinateSpace.designScreenHeight());
    }

    @JvmStatic
    public static final float designScreenWidth() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.getWindow() == null) {
            return 960.0f;
        }
        return (float)minecraft.getWindow().getFramebufferWidth() / 2.0f;
    }

    @JvmStatic
    public static final float designScreenHeight() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.getWindow() == null) {
            return 540.0f;
        }
        return (float)minecraft.getWindow().getFramebufferHeight() / 2.0f;
    }

    @JvmStatic
    public static final float designCenterX() {
        return Render2DCoordinateSpace.designScreenWidth() * 0.5f;
    }

    @JvmStatic
    public static final float designCenterY() {
        return Render2DCoordinateSpace.designScreenHeight() * 0.5f;
    }

    private final float zoomX(float designX) {
        if (!Render2DCoordinateSpace.zoomed()) {
            return designX;
        }
        float cx = Render2DCoordinateSpace.designCenterX();
        return cx + (designX - cx) * uiZoom;
    }

    private final float zoomY(float designY) {
        if (!Render2DCoordinateSpace.zoomed()) {
            return designY;
        }
        float cy = Render2DCoordinateSpace.designCenterY();
        return cy + (designY - cy) * uiZoom;
    }

    @JvmStatic
    public static final float guiIndependentScale() {
        return 2.0f / (float)Render2DCoordinateSpace.guiScale();
    }

    @JvmStatic
    public static final float designGuiScale() {
        return 2.0f;
    }

    @JvmStatic
    public static final int guiScale() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        if (minecraftClient2 == null) {
            return 1;
        }
        MinecraftClient minecraft = minecraftClient2;
        Window window2 = minecraft.getWindow();
        if (window2 == null) {
            return 1;
        }
        Window window = window2;
        return Math.max(1, window.getScaleFactor());
    }
}

