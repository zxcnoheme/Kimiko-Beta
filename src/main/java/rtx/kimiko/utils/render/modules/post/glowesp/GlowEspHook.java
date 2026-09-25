/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.util.math.MatrixStack
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fStack
 *  org.joml.Matrix4fc
 */
package rtx.kimiko.utils.render.modules.post.glowesp;

import com.mojang.blaze3d.systems.RenderSystem;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Matrix4fc;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.impl.Visuals.GlowEsp;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J9\u0010\r\u001a\u00020\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\tH\u0007b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u000bH\u0007b\u0002\b\f\u00a2\u0006\u0004\b\u000f\u0010\u0003R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0013R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001b\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001d\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001cR\u0016\u0010\u001e\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010\u001c\u00a8\u0006!"}, d2={"Lrtx/kimiko/utils/render/modules/post/glowesp/GlowEspHook;", "", "<init>", "()V", "Lnet/minecraft/Camera;", "frameCamera", "Lorg/joml/Matrix4f;", "position", "projection", "", "delta", "", "Lkotlin/jvm/JvmStatic;", "captureFrame", "(Lnet/minecraft/Camera;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;F)V", "renderBeforeParticles", "camera", "Lnet/minecraft/Camera;", "positionMatrix", "Lorg/joml/Matrix4f;", "projectionMatrix", "eventPosition", "eventProjection", "Lnet/minecraft/MatrixStack;", "eventStack", "Lnet/minecraft/MatrixStack;", "", "hasPosition", "Z", "hasProjection", "partialTicks", "F", "drawnThisPass", "rtx.kimiko:kimiko"})
public final class GlowEspHook {
    @NotNull
    public static final GlowEspHook INSTANCE = new GlowEspHook();
    @Nullable
    private static Camera camera;
    @NotNull
    private static final Matrix4f positionMatrix;
    @NotNull
    private static final Matrix4f projectionMatrix;
    @NotNull
    private static final Matrix4f eventPosition;
    @NotNull
    private static final Matrix4f eventProjection;
    @NotNull
    private static final MatrixStack eventStack;
    private static boolean hasPosition;
    private static boolean hasProjection;
    private static float partialTicks;
    private static boolean drawnThisPass;

    private GlowEspHook() {
    }

    @JvmStatic
    public static final void captureFrame(@Nullable Camera frameCamera, @Nullable Matrix4f position, @Nullable Matrix4f projection, float delta) {
        camera = frameCamera;
        boolean bl = hasPosition = position != null;
        if (position != null) {
            positionMatrix.set((Matrix4fc)position);
        }
        boolean bl2 = hasProjection = projection != null;
        if (projection != null) {
            projectionMatrix.set((Matrix4fc)projection);
        }
        partialTicks = delta;
        drawnThisPass = false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void renderBeforeParticles() {
        Camera frameCamera = camera;
        if (drawnThisPass || frameCamera == null || !hasPosition || !hasProjection) {
            return;
        }
        GlowEsp module = GlowEsp.Companion.getInstance();
        if (module == null || !module.isVisuallyActive()) {
            return;
        }
        drawnThisPass = true;
        MatrixStack stack = eventStack;
        stack.loadIdentity();
        stack.multiplyPositionMatrix((Matrix4fc)positionMatrix);
        Matrix4f matrix4f = eventPosition.set((Matrix4fc)positionMatrix);
        Intrinsics.checkNotNullExpressionValue((Object)matrix4f, (String)"set(...)");
        Matrix4f matrix4f2 = eventProjection.set((Matrix4fc)projectionMatrix);
        Intrinsics.checkNotNullExpressionValue((Object)matrix4f2, (String)"set(...)");
        WorldRenderEvent event = new WorldRenderEvent(stack, partialTicks, frameCamera, matrix4f, matrix4f2, false, 32, null);
        Matrix4fStack matrix4fStack = RenderSystem.getModelViewStack();
        Intrinsics.checkNotNullExpressionValue((Object)matrix4fStack, (String)"getModelViewStack(...)");
        Matrix4fStack modelView = matrix4fStack;
        modelView.pushMatrix();
        modelView.identity();
        try {
            module.renderGlow(event);
        }
        finally {
            modelView.popMatrix();
        }
    }

    static {
        positionMatrix = new Matrix4f();
        projectionMatrix = new Matrix4f();
        eventPosition = new Matrix4f();
        eventProjection = new Matrix4f();
        eventStack = new MatrixStack();
    }
}

