/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmOverloads
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.util.math.MatrixStack
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Matrix4f
 */
package rtx.kimiko.api.events.impl.render;

import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import rtx.kimiko.api.events.Event;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u00002\u00020\u0001B?\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u001a\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\b\u00a2\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\b\u00a2\u0006\u0004\b\u001a\u0010\u0019R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u001bR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u001cR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u001dR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\u001eR\u0014\u0010\n\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010\u001eR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010\u001f\u00a8\u0006 "}, d2={"Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "Lrtx/kimiko/api/events/Event;", "Lnet/minecraft/MatrixStack;", "stack", "", "partialTicks", "Lnet/minecraft/Camera;", "camera", "Lorg/joml/Matrix4f;", "positionMatrix", "projectionMatrix", "", "portalPass", "Lkotlin/jvm/JvmOverloads;", "<init>", "(Lnet/minecraft/MatrixStack;FLnet/minecraft/Camera;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Z)V", "isPortalPass", "()Z", "getStack", "()Lnet/minecraft/MatrixStack;", "getPartialTicks", "()F", "getCamera", "()Lnet/minecraft/Camera;", "getPositionMatrix", "()Lorg/joml/Matrix4f;", "getProjectionMatrix", "Lnet/minecraft/MatrixStack;", "F", "Lnet/minecraft/Camera;", "Lorg/joml/Matrix4f;", "Z", "rtx.kimiko:kimiko"})
public final class WorldRenderEvent
extends Event {
    @NotNull
    private final MatrixStack stack;
    private final float partialTicks;
    @NotNull
    private final Camera camera;
    @NotNull
    private final Matrix4f positionMatrix;
    @NotNull
    private final Matrix4f projectionMatrix;
    private final boolean portalPass;

    @JvmOverloads
    public WorldRenderEvent(@NotNull MatrixStack stack, float partialTicks, @NotNull Camera camera, @NotNull Matrix4f positionMatrix, @NotNull Matrix4f projectionMatrix, boolean portalPass) {
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)camera, (String)"camera");
        Intrinsics.checkNotNullParameter((Object)positionMatrix, (String)"positionMatrix");
        Intrinsics.checkNotNullParameter((Object)projectionMatrix, (String)"projectionMatrix");
        this.stack = stack;
        this.partialTicks = partialTicks;
        this.camera = camera;
        this.positionMatrix = positionMatrix;
        this.projectionMatrix = projectionMatrix;
        this.portalPass = portalPass;
    }

    public /* synthetic */ WorldRenderEvent(MatrixStack matrixStack2, float f, Camera camera2, Matrix4f matrix4f, Matrix4f matrix4f2, boolean bl, int n, DefaultConstructorMarker defaultConstructorMarker) {
        this(matrixStack2, f, camera2, matrix4f, matrix4f2, ((n & 0x20) != 0 ? false : bl));
    }

    public final boolean isPortalPass() {
        return this.portalPass;
    }

    @NotNull
    public final MatrixStack getStack() {
        return this.stack;
    }

    public final float getPartialTicks() {
        return this.partialTicks;
    }

    @NotNull
    public final Camera getCamera() {
        return this.camera;
    }

    @NotNull
    public final Matrix4f getPositionMatrix() {
        return this.positionMatrix;
    }

    @NotNull
    public final Matrix4f getProjectionMatrix() {
        return this.projectionMatrix;
    }

    @JvmOverloads
    public WorldRenderEvent(@NotNull MatrixStack stack, float partialTicks, @NotNull Camera camera, @NotNull Matrix4f positionMatrix, @NotNull Matrix4f projectionMatrix) {
        this(stack, partialTicks, camera, positionMatrix, projectionMatrix, false, 32, null);
    }
}

