/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBufferSlice
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.util.memory.ObjectAllocator
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Vector4f
 */
package rtx.kimiko.api.modules.impl.Visuals.portallive;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.util.memory.ObjectAllocator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector4f;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b*\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J+\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u0013\u0010\u0011\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0011\u0010\u0012J1\u0010\u0019\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001b\u001a\u0004\u0018\u00010\u0013H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001d\u001a\u0004\u0018\u00010\u0015H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010\u001f\u001a\u0004\u0018\u00010\u0017H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001f\u0010 J\u001b\u0010!\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b!\u0010\"J\u001b\u0010$\u001a\u00020\r2\u0006\u0010#\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b$\u0010%J\u0013\u0010&\u001a\u00020\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b&\u0010\u0003J\u0013\u0010'\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b'\u0010(J\u0013\u0010)\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b)\u0010\u0012R\u0014\u0010*\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010-R.\u0010/\u001a\u00020\b2\u0006\u0010.\u001a\u00020\b8\u0006@BX\u0087\u000er\u0002\b\u0005\u00a2\u0006\u0012\n\u0004\b/\u00100\u0012\u0004\b2\u0010\u0003\u001a\u0004\b1\u0010(R.\u00103\u001a\u00020\n2\u0006\u0010.\u001a\u00020\n8\u0006@BX\u0087\u000er\u0002\b\u0005\u00a2\u0006\u0012\n\u0004\b3\u0010+\u0012\u0004\b5\u0010\u0003\u001a\u0004\b4\u0010\u0012R.\u00106\u001a\u00020\n2\u0006\u0010.\u001a\u00020\n8\u0006@BX\u0087\u000er\u0002\b\u0005\u00a2\u0006\u0012\n\u0004\b6\u0010+\u0012\u0004\b8\u0010\u0003\u001a\u0004\b7\u0010\u0012R\u0016\u00109\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u00100R\u0016\u0010:\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u0010+R\u0018\u0010;\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u0018\u0010=\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b=\u0010>R\u0018\u0010?\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b?\u0010@\u00a8\u0006A"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveView;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "rendering", "()Z", "Lnet/minecraft/Vec3d;", "position", "", "yaw", "pitch", "", "begin", "(Lnet/minecraft/Vec3d;FF)V", "end", "fovOverride", "()F", "Lnet/minecraft/ObjectAllocator;", "allocator", "Lcom/mojang/blaze3d/buffers/GpuBufferSlice;", "fog", "Lorg/joml/Vector4f;", "fogColor", "recordFrameContext", "(Lnet/minecraft/ObjectAllocator;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;Lorg/joml/Vector4f;)V", "frameAllocator", "()Lnet/minecraft/ObjectAllocator;", "frameFog", "()Lcom/mojang/blaze3d/buffers/GpuBufferSlice;", "frameFogColor", "()Lorg/joml/Vector4f;", "recordActualCamera", "(Lnet/minecraft/Vec3d;)V", "fov", "recordActualFov", "(F)V", "resetProbe", "actualCamPos", "()Lnet/minecraft/Vec3d;", "actualFov", "STATIC_FOV", "F", "isRendering", "Z", "value", "cameraPos", "Lnet/minecraft/Vec3d;", "getCameraPos", "getCameraPos$annotations", "cameraYaw", "getCameraYaw", "getCameraYaw$annotations", "cameraPitch", "getCameraPitch", "getCameraPitch$annotations", "actualCamPosVal", "actualFovVal", "frameAllocatorVal", "Lnet/minecraft/ObjectAllocator;", "frameFogVal", "Lcom/mojang/blaze3d/buffers/GpuBufferSlice;", "frameFogColorVal", "Lorg/joml/Vector4f;", "rtx.kimiko:kimiko"})
public final class PortalLiveView {
    @NotNull
    public static final PortalLiveView INSTANCE = new PortalLiveView();
    private static final float STATIC_FOV = 70.0f;
    private static volatile boolean isRendering;
    @NotNull
    private static Vec3d cameraPos;
    private static float cameraYaw;
    private static float cameraPitch;
    @NotNull
    private static volatile Vec3d actualCamPosVal;
    private static volatile float actualFovVal;
    @Nullable
    private static volatile ObjectAllocator frameAllocatorVal;
    @Nullable
    private static volatile GpuBufferSlice frameFogVal;
    @Nullable
    private static volatile Vector4f frameFogColorVal;

    private PortalLiveView() {
    }

    @NotNull
    public static final Vec3d getCameraPos() {
        return cameraPos;
    }

    @JvmStatic
    public static /* synthetic */ void getCameraPos$annotations() {
    }

    public static final float getCameraYaw() {
        return cameraYaw;
    }

    @JvmStatic
    public static /* synthetic */ void getCameraYaw$annotations() {
    }

    public static final float getCameraPitch() {
        return cameraPitch;
    }

    @JvmStatic
    public static /* synthetic */ void getCameraPitch$annotations() {
    }

    @JvmStatic
    public static final boolean rendering() {
        return isRendering;
    }

    @JvmStatic
    public static final void begin(@NotNull Vec3d position, float yaw, float pitch) {
        Intrinsics.checkNotNullParameter((Object)position, (String)"position");
        cameraPos = position;
        cameraYaw = yaw;
        cameraPitch = pitch;
        isRendering = true;
    }

    @JvmStatic
    public static final void end() {
        isRendering = false;
    }

    @JvmStatic
    public static final float fovOverride() {
        return 70.0f;
    }

    @JvmStatic
    public static final void recordFrameContext(@Nullable ObjectAllocator allocator, @Nullable GpuBufferSlice fog, @Nullable Vector4f fogColor) {
        frameAllocatorVal = allocator;
        frameFogVal = fog;
        frameFogColorVal = fogColor;
    }

    @JvmStatic
    @Nullable
    public static final ObjectAllocator frameAllocator() {
        return frameAllocatorVal;
    }

    @JvmStatic
    @Nullable
    public static final GpuBufferSlice frameFog() {
        return frameFogVal;
    }

    @JvmStatic
    @Nullable
    public static final Vector4f frameFogColor() {
        return frameFogColorVal;
    }

    @JvmStatic
    public static final void recordActualCamera(@NotNull Vec3d position) {
        Intrinsics.checkNotNullParameter((Object)position, (String)"position");
        actualCamPosVal = position;
    }

    @JvmStatic
    public static final void recordActualFov(float fov) {
        actualFovVal = fov;
    }

    @JvmStatic
    public static final void resetProbe() {
        actualFovVal = -1.0f;
    }

    @JvmStatic
    @NotNull
    public static final Vec3d actualCamPos() {
        return actualCamPosVal;
    }

    @JvmStatic
    public static final float actualFov() {
        return actualFovVal;
    }

    static {
        Vec3d vec3d2 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
        cameraPos = vec3d2;
        Vec3d vec3d3 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"ZERO");
        actualCamPosVal = vec3d3;
        actualFovVal = -1.0f;
    }
}

