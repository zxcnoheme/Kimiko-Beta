/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.render.Camera
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Vector3fc
 */
package rtx.kimiko.api.ui.window;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3fc;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0013\n\u0002\u0010\t\n\u0002\b\u0019\n\u0002\u0010\u0014\n\u0002\b\n\n\u0002\u0010\u0006\n\u0002\b\u001b\n\u0002\u0010\b\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J+\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\u000e\u001a\u00020\rH\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000e\u0010\u000fJO\u0010\u0018\u001a\u0004\u0018\u00010\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0011H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u0018\u0010\u0019J#\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0011H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\tH\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u001e\u0010\u0003J\u0013\u0010\u001f\u001a\u00020\u0007H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u001f\u0010 J\u0013\u0010!\u001a\u00020\u0011H\u0007b\u0002\b\n\u00a2\u0006\u0004\b!\u0010\"J\u0013\u0010#\u001a\u00020\u0011H\u0007b\u0002\b\n\u00a2\u0006\u0004\b#\u0010\"J\u0013\u0010$\u001a\u00020\rH\u0007b\u0002\b\n\u00a2\u0006\u0004\b$\u0010\u000fJ\u0013\u0010&\u001a\u00020%H\u0007b\u0002\b\n\u00a2\u0006\u0004\b&\u0010'J\u0013\u0010(\u001a\u00020\tH\u0007b\u0002\b\n\u00a2\u0006\u0004\b(\u0010\u0003J\u000f\u0010)\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b)\u0010\u0003J\u0017\u0010*\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b*\u0010+J\u000f\u0010,\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b,\u0010\"J\u0013\u0010-\u001a\u00020\rH\u0007b\u0002\b\n\u00a2\u0006\u0004\b-\u0010\u000fJ\u0013\u0010.\u001a\u00020\tH\u0007b\u0002\b\n\u00a2\u0006\u0004\b.\u0010\u0003J\u0013\u0010/\u001a\u00020%H\u0007b\u0002\b\n\u00a2\u0006\u0004\b/\u0010'J\u0013\u00100\u001a\u00020\rH\u0007b\u0002\b\n\u00a2\u0006\u0004\b0\u0010\u000fJ\u0013\u00101\u001a\u00020\rH\u0007b\u0002\b\n\u00a2\u0006\u0004\b1\u0010\u000fJ\u0013\u00102\u001a\u00020\rH\u0007b\u0002\b\n\u00a2\u0006\u0004\b2\u0010\u000fJ\u0013\u00103\u001a\u00020\u0011H\u0007b\u0002\b\n\u00a2\u0006\u0004\b3\u0010\"J\u0013\u00104\u001a\u00020\u0011H\u0007b\u0002\b\n\u00a2\u0006\u0004\b4\u0010\"J\u0013\u0010\u001b\u001a\u00020\u0011H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u001b\u0010\"J\u0013\u00105\u001a\u00020\u0011H\u0007b\u0002\b\n\u00a2\u0006\u0004\b5\u0010\"J%\u00108\u001a\u0004\u0018\u00010\u00042\u0006\u00106\u001a\u00020\u00112\u0006\u00107\u001a\u00020\u0011H\u0007b\u0002\b\n\u00a2\u0006\u0004\b8\u00109J\u000f\u0010:\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b:\u0010\"J\u0017\u0010;\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b;\u0010<J'\u0010=\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u00106\u001a\u00020\u00112\u0006\u00107\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b=\u0010>J!\u0010@\u001a\u0004\u0018\u00010?2\u0006\u00106\u001a\u00020\u00112\u0006\u00107\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b@\u0010AJ9\u0010E\u001a\u0004\u0018\u00010?2\u0006\u0010B\u001a\u00020\u00042\u0006\u0010C\u001a\u00020\u00112\u0006\u0010D\u001a\u00020\u00112\u0006\u00106\u001a\u00020\u00112\u0006\u00107\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\bE\u0010FJ\u0017\u0010H\u001a\u00020\u00112\u0006\u0010G\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\bH\u0010<J\u0017\u0010I\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\bI\u0010<R\u0014\u0010K\u001a\u00020J8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bK\u0010LR\u0014\u0010M\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bM\u0010NR\u0014\u0010O\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bO\u0010NR\u0014\u0010P\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bP\u0010NR\u0014\u0010Q\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bQ\u0010NR\u0014\u0010R\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bR\u0010NR\u0014\u0010S\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bS\u0010NR\u0014\u0010T\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u0010UR\u0014\u0010V\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bV\u0010UR\u0016\u0010W\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bW\u0010XR\u0016\u0010Y\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010ZR\u0016\u0010[\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b[\u0010ZR\u0016\u0010\\\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\\\u0010ZR\u0016\u0010]\u001a\u00020%8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u0016\u0010_\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010NR\u0016\u0010`\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010NR\u0016\u0010a\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010XR\u0016\u0010b\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010NR\u0016\u0010c\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010NR\u0016\u0010d\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bd\u0010NR\u0016\u0010e\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010NR\u0016\u0010g\u001a\u00020f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bg\u0010hR\u0016\u0010i\u001a\u00020f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bi\u0010hR\u0016\u0010j\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bj\u0010NR\u0016\u0010k\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bk\u0010NR\u0016\u0010l\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bl\u0010X\u00a8\u0006m"}, d2={"Lrtx/kimiko/api/ui/window/WorldGuiCloseAnimation;", "", "<init>", "()V", "Lorg/joml/Matrix4f;", "projection", "view", "Lnet/minecraft/Vec3d;", "cameraPos", "", "Lkotlin/jvm/JvmStatic;", "captureWorldMatrices", "(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/Vec3d;)V", "", "hasCapturedWorldMatrices", "()Z", "remoteAnchor", "", "remoteYaw", "remotePitch", "scaleXY", "animationScale", "centerX", "centerY", "buildRemoteMatrix", "(Lnet/minecraft/Vec3d;FFFFFF)Lorg/joml/Matrix4f;", "screenAlpha", "screenScale", "begin", "(FF)V", "reverse", "liveAnchor", "()Lnet/minecraft/Vec3d;", "liveYaw", "()F", "livePitch", "isReversing", "", "remainingNanos", "()J", "updateFrame", "alignToCamera", "faceCamera", "(Lnet/minecraft/Vec3d;)V", "reverseBlend", "surfaceChanged", "cancel", "token", "isActive", "isFinished", "isDetachedRender", "alpha", "blurRadius", "progress", "width", "height", "compositeMatrix", "(FF)Lorg/joml/Matrix4f;", "value", "smootherStep", "(F)F", "buildMatrix", "(FFF)Lorg/joml/Matrix4f;", "", "resolveWorldScales", "(FF)[F", "matrix", "x", "y", "project", "(Lorg/joml/Matrix4f;FFFF)[F", "openFactor", "scaleFactor", "clamp01", "", "DISTANCE", "D", "NEAR_PLANE", "F", "CLOSE_STEP_PER_TICK", "TICK_MS", "REVERSE_MS", "SCALE_END", "MAX_BLUR_RADIUS", "lastProjMat", "Lorg/joml/Matrix4f;", "lastViewMat", "lastCameraPos", "Lnet/minecraft/Vec3d;", "hasWorldMatrices", "Z", "active", "reversing", "startNanos", "J", "startValue", "frameValue", "anchor", "yaw", "pitch", "worldScaleX", "worldScaleY", "", "beginWidth", "I", "beginHeight", "startAlpha", "startScreenScale", "reverseAnchor", "rtx.kimiko:kimiko"})
public final class WorldGuiCloseAnimation {
    @NotNull
    public static final WorldGuiCloseAnimation INSTANCE = new WorldGuiCloseAnimation();
    private static final double DISTANCE = 7.5;
    private static final float NEAR_PLANE = 0.05f;
    private static final float CLOSE_STEP_PER_TICK = 0.08f;
    private static final float TICK_MS = 50.0f;
    private static final float REVERSE_MS = 350.0f;
    private static final float SCALE_END = 0.7f;
    private static final float MAX_BLUR_RADIUS = 9.0f;
    @NotNull
    private static final Matrix4f lastProjMat = new Matrix4f();
    @NotNull
    private static final Matrix4f lastViewMat = new Matrix4f();
    @NotNull
    private static Vec3d lastCameraPos;
    private static boolean hasWorldMatrices;
    private static boolean active;
    private static boolean reversing;
    private static long startNanos;
    private static float startValue;
    private static float frameValue;
    @NotNull
    private static Vec3d anchor;
    private static float yaw;
    private static float pitch;
    private static float worldScaleX;
    private static float worldScaleY;
    private static int beginWidth;
    private static int beginHeight;
    private static float startAlpha;
    private static float startScreenScale;
    @NotNull
    private static Vec3d reverseAnchor;

    private WorldGuiCloseAnimation() {
    }

    @JvmStatic
    public static final void captureWorldMatrices(@NotNull Matrix4f projection, @NotNull Matrix4f view, @NotNull Vec3d cameraPos) {
        Intrinsics.checkNotNullParameter((Object)projection, (String)"projection");
        Intrinsics.checkNotNullParameter((Object)view, (String)"view");
        Intrinsics.checkNotNullParameter((Object)cameraPos, (String)"cameraPos");
        lastProjMat.set((Matrix4fc)projection);
        lastViewMat.set((Matrix4fc)view);
        lastCameraPos = cameraPos;
        hasWorldMatrices = true;
    }

    @JvmStatic
    public static final boolean hasCapturedWorldMatrices() {
        return hasWorldMatrices;
    }

    @JvmStatic
    @Nullable
    public static final Matrix4f buildRemoteMatrix(@Nullable Vec3d remoteAnchor, float remoteYaw, float remotePitch, float scaleXY, float animationScale, float centerX, float centerY) {
        if (!hasWorldMatrices || remoteAnchor == null || animationScale <= 0.001f) {
            return null;
        }
        float relativeX = (float)(remoteAnchor.x - WorldGuiCloseAnimation.lastCameraPos.x);
        float relativeY = (float)(remoteAnchor.y - WorldGuiCloseAnimation.lastCameraPos.y);
        float relativeZ = (float)(remoteAnchor.z - WorldGuiCloseAnimation.lastCameraPos.z);
        Matrix4f model = new Matrix4f().translate(relativeX, relativeY, relativeZ).rotateY((float)Math.toRadians(-remoteYaw + 180.0f)).rotateX((float)Math.toRadians(-remotePitch + 180.0f)).scale(scaleXY, scaleXY, scaleXY).scale(animationScale, animationScale, 1.0f).translate(-centerX, -centerY, 0.0f);
        return new Matrix4f((Matrix4fc)lastProjMat).mul((Matrix4fc)lastViewMat).mul((Matrix4fc)model);
    }

    @JvmStatic
    public static final void begin(float screenAlpha, float screenScale) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.world == null || mc.player == null || mc.getWindow() == null || !hasWorldMatrices) {
            WorldGuiCloseAnimation.cancel();
            return;
        }
        if (active && reversing && frameValue > 0.001f && !WorldGuiCloseAnimation.surfaceChanged()) {
            reversing = false;
            startValue = INSTANCE.clamp01(frameValue);
            startNanos = System.nanoTime();
            startAlpha = INSTANCE.clamp01(screenAlpha <= 0.0f ? 1.0f : screenAlpha);
            startScreenScale = Math.abs(screenScale) <= Float.MAX_VALUE && screenScale > 1.0E-4f ? screenScale : 1.0f;
            return;
        }
        Camera camera2 = mc.gameRenderer.getCamera();
        Intrinsics.checkNotNullExpressionValue((Object)camera2, (String)"getMainCamera(...)");
        Camera camera = camera2;
        Vector3fc vector3fc = camera.getHorizontalPlane();
        Intrinsics.checkNotNullExpressionValue((Object)vector3fc, (String)"forwardVector(...)");
        Vector3fc look = vector3fc;
        double offsetDistance = 7.5 * (double)mc.getWindow().getFramebufferHeight() / 1080.0;
        Vec3d vec3d2 = camera.getCameraPos().add((double)look.x() * offsetDistance, (double)look.y() * offsetDistance, (double)look.z() * offsetDistance);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
        anchor = vec3d2;
        yaw = camera.getYaw();
        pitch = camera.getPitch();
        float[] scales = INSTANCE.resolveWorldScales(mc.getWindow().getFramebufferWidth(), mc.getWindow().getFramebufferHeight());
        if (scales == null) {
            WorldGuiCloseAnimation.cancel();
            return;
        }
        worldScaleX = scales[0];
        worldScaleY = scales[1];
        beginWidth = mc.getWindow().getFramebufferWidth();
        beginHeight = mc.getWindow().getFramebufferHeight();
        startAlpha = INSTANCE.clamp01(screenAlpha <= 0.0f ? 1.0f : screenAlpha);
        startScreenScale = Math.abs(screenScale) <= Float.MAX_VALUE && screenScale > 1.0E-4f ? screenScale : 1.0f;
        startValue = 1.0f;
        startNanos = System.nanoTime();
        frameValue = startValue;
        reversing = false;
        active = true;
    }

    @JvmStatic
    public static final void reverse() {
        if (!active || reversing) {
            return;
        }
        reversing = true;
        startValue = INSTANCE.clamp01(frameValue);
        startNanos = System.nanoTime();
        reverseAnchor = anchor;
    }

    @JvmStatic
    @NotNull
    public static final Vec3d liveAnchor() {
        return anchor;
    }

    @JvmStatic
    public static final float liveYaw() {
        return yaw;
    }

    @JvmStatic
    public static final float livePitch() {
        return pitch;
    }

    @JvmStatic
    public static final boolean isReversing() {
        return active && reversing;
    }

    @JvmStatic
    public static final long remainingNanos() {
        if (!active) {
            return 0L;
        }
        if (reversing) {
            float elapsedMs = (float)(System.nanoTime() - startNanos) / 1000000.0f;
            return (long)(Math.max(0.0f, 350.0f - elapsedMs) * 1000000.0f);
        }
        return (long)(INSTANCE.clamp01(frameValue) / 0.08f * 50.0f * 1000000.0f);
    }

    @JvmStatic
    public static final void updateFrame() {
        if (!active) {
            return;
        }
        frameValue = INSTANCE.value();
        if (reversing) {
            INSTANCE.alignToCamera();
        }
    }

    private final void alignToCamera() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.world == null || mc.gameRenderer == null || mc.getWindow() == null) {
            return;
        }
        Camera camera2 = mc.gameRenderer.getCamera();
        Intrinsics.checkNotNullExpressionValue((Object)camera2, (String)"getMainCamera(...)");
        Camera camera = camera2;
        Vector3fc vector3fc = camera.getHorizontalPlane();
        Intrinsics.checkNotNullExpressionValue((Object)vector3fc, (String)"forwardVector(...)");
        Vector3fc look = vector3fc;
        double offsetDistance = 7.5 * (double)mc.getWindow().getFramebufferHeight() / 1080.0;
        Vec3d vec3d2 = camera.getCameraPos().add((double)look.x() * offsetDistance, (double)look.y() * offsetDistance, (double)look.z() * offsetDistance);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
        Vec3d target = vec3d2;
        float blend = this.reverseBlend();
        anchor = new Vec3d(WorldGuiCloseAnimation.reverseAnchor.x + (target.x - WorldGuiCloseAnimation.reverseAnchor.x) * (double)blend, WorldGuiCloseAnimation.reverseAnchor.y + (target.y - WorldGuiCloseAnimation.reverseAnchor.y) * (double)blend, WorldGuiCloseAnimation.reverseAnchor.z + (target.z - WorldGuiCloseAnimation.reverseAnchor.z) * (double)blend);
        Vec3d vec3d3 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"position(...)");
        this.faceCamera(vec3d3);
    }

    private final void faceCamera(Vec3d cameraPos) {
        double dx = WorldGuiCloseAnimation.anchor.x - cameraPos.x;
        double dy = WorldGuiCloseAnimation.anchor.y - cameraPos.y;
        double dz = WorldGuiCloseAnimation.anchor.z - cameraPos.z;
        double horizontal = Math.sqrt(dx * dx + dz * dz);
        if (horizontal < 1.0E-4 && Math.abs(dy) < 1.0E-4) {
            return;
        }
        yaw = (float)Math.toDegrees(Math.atan2(-dx, dz));
        pitch = -((float)Math.toDegrees(Math.atan2(dy, horizontal)));
    }

    private final float reverseBlend() {
        float elapsedMs = (float)(System.nanoTime() - startNanos) / 1000000.0f;
        return this.smootherStep(this.clamp01(elapsedMs / 350.0f));
    }

    @JvmStatic
    public static final boolean surfaceChanged() {
        if (!active) {
            return false;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        return mc.getWindow() == null || mc.getWindow().getFramebufferWidth() != beginWidth || mc.getWindow().getFramebufferHeight() != beginHeight;
    }

    @JvmStatic
    public static final void cancel() {
        active = false;
        reversing = false;
    }

    @JvmStatic
    public static final long token() {
        return startNanos;
    }

    @JvmStatic
    public static final boolean isActive() {
        return active;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @JvmStatic
    public static final boolean isFinished() {
        if (!active) return true;
        if (reversing) {
            if (!(frameValue >= 1.0f)) return false;
            return true;
        }
        if (!(frameValue <= 0.0f)) return false;
        return true;
    }

    @JvmStatic
    public static final boolean isDetachedRender() {
        return active && frameValue > 0.0f && (reversing || MinecraftClient.getInstance().currentScreen == null);
    }

    @JvmStatic
    public static final float alpha() {
        if (!active) {
            return 0.0f;
        }
        float t = 1.0f - INSTANCE.clamp01(frameValue);
        return INSTANCE.clamp01(startAlpha * (1.0f - t * t * t));
    }

    @JvmStatic
    public static final float blurRadius() {
        if (!active) {
            return 0.0f;
        }
        float t = 1.0f - INSTANCE.clamp01(frameValue);
        return 9.0f * Math.max(t * t, 1.0f - startAlpha);
    }

    @JvmStatic
    public static final float screenScale() {
        return active ? startScreenScale : 1.0f;
    }

    @JvmStatic
    public static final float progress() {
        return active ? 1.0f - INSTANCE.clamp01(frameValue) : 0.0f;
    }

    @JvmStatic
    @Nullable
    public static final Matrix4f compositeMatrix(float width, float height) {
        if (!active || !hasWorldMatrices) {
            return null;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.world == null || mc.currentScreen != null && !reversing) {
            return null;
        }
        if ((int)width != beginWidth || (int)height != beginHeight) {
            return null;
        }
        float animationScale = INSTANCE.scaleFactor(INSTANCE.clamp01(frameValue));
        if (animationScale <= 0.001f) {
            return null;
        }
        return INSTANCE.buildMatrix(animationScale, width, height);
    }

    private final float value() {
        float elapsedMs = (float)(System.nanoTime() - startNanos) / 1000000.0f;
        if (reversing) {
            return startValue + (1.0f - startValue) * this.smootherStep(this.clamp01(elapsedMs / 350.0f));
        }
        return Math.max(0.0f, startValue - elapsedMs / 50.0f * 0.08f);
    }

    private final float smootherStep(float value) {
        return value * value * value * (value * (value * 6.0f - 15.0f) + 10.0f);
    }

    private final Matrix4f buildMatrix(float animationScale, float width, float height) {
        float centerX = width / 2.0f;
        float centerY = height / 2.0f;
        float relativeX = (float)(WorldGuiCloseAnimation.anchor.x - WorldGuiCloseAnimation.lastCameraPos.x);
        float relativeY = (float)(WorldGuiCloseAnimation.anchor.y - WorldGuiCloseAnimation.lastCameraPos.y);
        float relativeZ = (float)(WorldGuiCloseAnimation.anchor.z - WorldGuiCloseAnimation.lastCameraPos.z);
        Matrix4f model = new Matrix4f().translate(relativeX, relativeY, relativeZ).rotateY((float)Math.toRadians(-yaw + 180.0f)).rotateX((float)Math.toRadians(-pitch + 180.0f)).scale(worldScaleX, worldScaleY, (worldScaleX + worldScaleY) * 0.5f).scale(animationScale, animationScale, 1.0f).translate(-centerX, -centerY, 0.0f);
        Matrix4f matrix4f = new Matrix4f((Matrix4fc)lastProjMat).mul((Matrix4fc)lastViewMat).mul((Matrix4fc)model);
        Intrinsics.checkNotNullExpressionValue((Object)matrix4f, (String)"mul(...)");
        return matrix4f;
    }

    private final float[] resolveWorldScales(float width, float height) {
        float candidate;
        float candidate2;
        float centerX = width / 2.0f;
        float centerY = height / 2.0f;
        float relativeX = (float)(WorldGuiCloseAnimation.anchor.x - WorldGuiCloseAnimation.lastCameraPos.x);
        float relativeY = (float)(WorldGuiCloseAnimation.anchor.y - WorldGuiCloseAnimation.lastCameraPos.y);
        float relativeZ = (float)(WorldGuiCloseAnimation.anchor.z - WorldGuiCloseAnimation.lastCameraPos.z);
        Matrix4f unitProjection = new Matrix4f((Matrix4fc)lastProjMat).mul((Matrix4fc)lastViewMat).mul((Matrix4fc)new Matrix4f().translate(relativeX, relativeY, relativeZ).rotateY((float)Math.toRadians(-yaw + 180.0f)).rotateX((float)Math.toRadians(-pitch + 180.0f)).translate(-centerX, -centerY, 0.0f));
        Intrinsics.checkNotNull((Object)unitProjection);
        float[] left = this.project(unitProjection, 0.0f, centerY, width, height);
        float[] right = this.project(unitProjection, width, centerY, width, height);
        float[] top = this.project(unitProjection, centerX, 0.0f, width, height);
        float[] bottom = this.project(unitProjection, centerX, height, width, height);
        float widthScale = Float.NaN;
        if (left != null && right != null && Math.abs(candidate2 = width / Math.max(1.0E-4f, Math.abs(right[0] - left[0]))) <= Float.MAX_VALUE && candidate2 > 0.0f) {
            widthScale = candidate2;
        }
        float heightScale = Float.NaN;
        if (top != null && bottom != null && Math.abs(candidate = height / Math.max(1.0E-4f, Math.abs(bottom[1] - top[1]))) <= Float.MAX_VALUE && candidate > 0.0f) {
            heightScale = candidate;
        }
        if (Float.isNaN(widthScale) && Float.isNaN(heightScale)) {
            return null;
        }
        if (Float.isNaN(widthScale)) {
            widthScale = heightScale;
        }
        if (Float.isNaN(heightScale)) {
            heightScale = widthScale;
        }
        float[] fArray = new float[]{widthScale, heightScale};
        return fArray;
    }

    private final float[] project(Matrix4f matrix, float x, float y, float width, float height) {
        float clipX = matrix.m00() * x + matrix.m10() * y + matrix.m30();
        float clipY = matrix.m01() * x + matrix.m11() * y + matrix.m31();
        float clipW = matrix.m03() * x + matrix.m13() * y + matrix.m33();
        if (clipW <= 0.05f) {
            return null;
        }
        float inverseW = 1.0f / clipW;
        float[] fArray = new float[]{(clipX * inverseW * 0.5f + 0.5f) * width, (1.0f - (clipY * inverseW * 0.5f + 0.5f)) * height};
        return fArray;
    }

    private final float scaleFactor(float openFactor) {
        float t = 1.0f - openFactor;
        float backIn = 2.70158f * t * t * t - 1.70158f * t * t;
        return 1.0f + -0.3f * backIn;
    }

    private final float clamp01(float value) {
        return Math.max(0.0f, Math.min(1.0f, value));
    }

    static {
        Vec3d vec3d2 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
        lastCameraPos = vec3d2;
        Vec3d vec3d3 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"ZERO");
        anchor = vec3d3;
        startAlpha = 1.0f;
        startScreenScale = 1.0f;
        Vec3d vec3d4 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"ZERO");
        reverseAnchor = vec3d4;
    }
}

