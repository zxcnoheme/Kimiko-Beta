/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.ScreenRect
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fc
 */
package rtx.kimiko.utils.render.util.scissor;

import java.util.ArrayDeque;
import java.util.Deque;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.ScreenRect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J3\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\u000fJ=\u0010\u000e\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\u0012J\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0015\u0010\u0003R\u0014\u0010\u0016\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/utils/render/util/scissor/ScissorUtil;", "", "<init>", "()V", "Lnet/minecraft/ScreenRect;", "Lkotlin/jvm/JvmStatic;", "current", "()Lnet/minecraft/ScreenRect;", "", "x", "y", "width", "height", "", "push", "(FFFF)V", "Lorg/joml/Matrix3x2f;", "pose", "(Lorg/joml/Matrix3x2f;FFFF)V", "next", "(Lnet/minecraft/ScreenRect;)V", "pop", "EMPTY", "Lnet/minecraft/ScreenRect;", "Ljava/util/Deque;", "STACK", "Ljava/util/Deque;", "rtx.kimiko:kimiko"})
public final class ScissorUtil {
    @NotNull
    public static final ScissorUtil INSTANCE = new ScissorUtil();
    @NotNull
    private static final ScreenRect EMPTY = new ScreenRect(0, 0, 0, 0);
    @NotNull
    private static final Deque<ScreenRect> STACK = new ArrayDeque();

    private ScissorUtil() {
    }

    @JvmStatic
    @Nullable
    public static final ScreenRect current() {
        return STACK.peek();
    }

    @JvmStatic
    public static final void push(float x, float y, float width, float height) {
        int left = Render2DCoordinateSpace.toGuiIntX(x);
        int top = Render2DCoordinateSpace.toGuiIntY(y);
        int right = Render2DCoordinateSpace.toGuiIntX(x + width);
        int bottom = Render2DCoordinateSpace.toGuiIntY(y + height);
        INSTANCE.push(new ScreenRect(left, top, Math.max(0, right - left), Math.max(0, bottom - top)));
    }

    @JvmStatic
    public static final void push(@Nullable Matrix3x2f pose, float x, float y, float width, float height) {
        if (pose == null) {
            ScissorUtil.push(x, y, width, height);
            return;
        }
        ScreenRect screenRect2 = new ScreenRect(Math.round(x), Math.round(y), Math.max(0, Math.round(width)), Math.max(0, Math.round(height))).transformEachVertex((Matrix3x2fc)pose);
        Intrinsics.checkNotNullExpressionValue((Object)screenRect2, (String)"transformMaxBounds(...)");
        ScreenRect next = screenRect2;
        INSTANCE.push(next);
    }

    private final void push(ScreenRect next) {
        ScreenRect current;
        ScreenRect screenRect2 = current = STACK.peek();
        ScreenRect clipped = screenRect2 == null ? next : screenRect2.intersection(next);
        ScreenRect screenRect3 = clipped;
        if (screenRect3 == null) {
            screenRect3 = EMPTY;
        }
        STACK.push(screenRect3);
    }

    @JvmStatic
    public static final void pop() {
        if (!STACK.isEmpty()) {
            STACK.pop();
        }
    }
}

