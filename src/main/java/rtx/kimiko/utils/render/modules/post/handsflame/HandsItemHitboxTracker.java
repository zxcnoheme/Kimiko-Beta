/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.item.ItemRenderState
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.item.ItemDisplayContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Vector3fc
 *  org.joml.Vector4f
 */
package rtx.kimiko.utils.render.modules.post.handsflame;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3fc;
import org.joml.Vector4f;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u000256B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ1\u0010\u0010\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0011J+\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001b\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0019\u0010\u001aJ#\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001bH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001d\u0010\u001eJ#\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001bH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001f\u0010\u001eJ?\u0010(\u001a\u00020\u00062\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020&H\u0002\u00a2\u0006\u0004\b(\u0010)J\u0019\u0010*\u001a\u00020\u00122\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b*\u0010+R\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0014\u00102\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00101R\u0016\u00103\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u00104\u00a8\u00067"}, d2={"Lrtx/kimiko/utils/render/modules/post/handsflame/HandsItemHitboxTracker;", "", "<init>", "()V", "Lorg/joml/Matrix4fc;", "projectionMatrix", "", "Lkotlin/jvm/JvmStatic;", "captureProjection", "(Lorg/joml/Matrix4fc;)V", "Lnet/minecraft/ItemDisplayContext;", "context", "Lnet/minecraft/MatrixStack;", "matrices", "Lnet/minecraft/ItemRenderState;", "state", "capture", "(Lnet/minecraft/ItemDisplayContext;Lnet/minecraft/MatrixStack;Lnet/minecraft/ItemRenderState;)V", "", "left", "", "mouseX", "mouseY", "contains", "(ZDD)Z", "isFresh", "(Z)Z", "", "fallback", "centerX", "(ZF)F", "centerY", "Lorg/joml/Vector3fc;", "point", "pose", "projection", "width", "height", "Lrtx/kimiko/utils/render/modules/post/handsflame/HandsItemHitboxTracker$PointCloud;", "points", "projectPoint", "(Lorg/joml/Vector3fc;Lorg/joml/Matrix4fc;Lorg/joml/Matrix4fc;FFLrtx/kimiko/utils/render/modules/post/handsflame/HandsItemHitboxTracker$PointCloud;)V", "isFirstPerson", "(Lnet/minecraft/ItemDisplayContext;)Z", "Lorg/joml/Matrix4f;", "PROJECTION", "Lorg/joml/Matrix4f;", "Lrtx/kimiko/utils/render/modules/post/handsflame/HandsItemHitboxTracker$Hitbox;", "LEFT", "Lrtx/kimiko/utils/render/modules/post/handsflame/HandsItemHitboxTracker$Hitbox;", "RIGHT", "hasProjection", "Z", "PointCloud", "Hitbox", "rtx.kimiko:kimiko"})
public final class HandsItemHitboxTracker {
    @NotNull
    public static final HandsItemHitboxTracker INSTANCE = new HandsItemHitboxTracker();
    @NotNull
    private static final Matrix4f PROJECTION = new Matrix4f();
    @NotNull
    private static final Hitbox LEFT = new Hitbox();
    @NotNull
    private static final Hitbox RIGHT = new Hitbox();
    private static boolean hasProjection;

    private HandsItemHitboxTracker() {
    }

    @JvmStatic
    public static final void captureProjection(@Nullable Matrix4fc projectionMatrix) {
        if (projectionMatrix == null) {
            hasProjection = false;
            return;
        }
        PROJECTION.set(projectionMatrix);
        hasProjection = true;
    }

    @JvmStatic
    public static final void capture(@Nullable ItemDisplayContext context, @Nullable MatrixStack matrices, @Nullable ItemRenderState state) {
        if (!hasProjection || matrices == null || state == null || !INSTANCE.isFirstPerson(context)) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient client = minecraftClient2;
        if (client.getWindow() == null) {
            return;
        }
        PointCloud points = new PointCloud();
        Matrix4f pose = new Matrix4f((Matrix4fc)matrices.peek().getPositionMatrix());
        Matrix4f projection = new Matrix4f((Matrix4fc)PROJECTION);
        float width = client.getWindow().getScaledWidth();
        float height = client.getWindow().getScaledHeight();
        state.load(arg_0 -> HandsItemHitboxTracker.capture$lambda$0(pose, projection, width, height, points, arg_0));
        if (points.getCount() < 2) {
            return;
        }
        (context == ItemDisplayContext.FIRST_PERSON_LEFT_HAND ? LEFT : RIGHT).update(points);
    }

    @JvmStatic
    public static final boolean contains(boolean left, double mouseX, double mouseY) {
        return (left ? LEFT : RIGHT).contains(mouseX, mouseY);
    }

    @JvmStatic
    public static final boolean isFresh(boolean left) {
        return (left ? LEFT : RIGHT).isFresh();
    }

    @JvmStatic
    public static final float centerX(boolean left, float fallback) {
        Hitbox hitbox = left ? LEFT : RIGHT;
        return hitbox.isFresh() ? hitbox.getCenterX() : fallback;
    }

    @JvmStatic
    public static final float centerY(boolean left, float fallback) {
        Hitbox hitbox = left ? LEFT : RIGHT;
        return hitbox.isFresh() ? hitbox.getCenterY() : fallback;
    }

    private final void projectPoint(Vector3fc point, Matrix4fc pose, Matrix4fc projection, float width, float height, PointCloud points) {
        Vector4f clip = new Vector4f(point.x(), point.y(), point.z(), 1.0f);
        clip.mul(pose);
        clip.mul(projection);
        if (Math.abs(clip.w()) <= 1.0E-5f) {
            return;
        }
        float ndcX = clip.x() / clip.w();
        float ndcY = clip.y() / clip.w();
        if (!(Math.abs(ndcX) <= Float.MAX_VALUE) || !(Math.abs(ndcY) <= Float.MAX_VALUE)) {
            return;
        }
        points.add((ndcX * 0.5f + 0.5f) * width, (0.5f - ndcY * 0.5f) * height);
    }

    private final boolean isFirstPerson(ItemDisplayContext context) {
        return context == ItemDisplayContext.FIRST_PERSON_LEFT_HAND || context == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND;
    }

    private static final void capture$lambda$0(Matrix4f $pose, Matrix4f $projection, float $width, float $height, PointCloud $points, Vector3fc point) {
        Intrinsics.checkNotNullParameter((Object)point, (String)"point");
        INSTANCE.projectPoint(point, (Matrix4fc)$pose, (Matrix4fc)$projection, $width, $height, $points);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\f\u00a2\u0006\u0004\b\u000f\u0010\u0010R\"\u0010\u0012\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0018\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\u0016\u0010\u001b\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0013R\u0016\u0010\u001c\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0013R\u0016\u0010\u001d\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u0013R\u0016\u0010\u001e\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u0013R\u0016\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010!\u00a8\u0006\""}, d2={"Lrtx/kimiko/utils/render/modules/post/handsflame/HandsItemHitboxTracker$Hitbox;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/modules/post/handsflame/HandsItemHitboxTracker$PointCloud;", "points", "", "update", "(Lrtx/kimiko/utils/render/modules/post/handsflame/HandsItemHitboxTracker$PointCloud;)V", "", "mouseX", "mouseY", "", "contains", "(DD)Z", "isFresh", "()Z", "", "centerX", "F", "getCenterX", "()F", "setCenterX", "(F)V", "centerY", "getCenterY", "setCenterY", "axisX", "axisY", "halfA", "halfB", "", "updatedNanos", "J", "rtx.kimiko:kimiko"})
    private static final class Hitbox {
        private float centerX;
        private float centerY;
        private float axisX = 1.0f;
        private float axisY;
        private float halfA;
        private float halfB;
        private long updatedNanos;

        public final float getCenterX() {
            return this.centerX;
        }

        public final void setCenterX(float f) {
            this.centerX = f;
        }

        public final float getCenterY() {
            return this.centerY;
        }

        public final void setCenterY(float f) {
            this.centerY = f;
        }

        public final void update(@NotNull PointCloud points) {
            Intrinsics.checkNotNullParameter((Object)points, (String)"points");
            float meanX = 0.0f;
            float meanY = 0.0f;
            int n = points.getCount();
            for (int i = 0; i < n; ++i) {
                meanX += points.getX()[i];
                meanY += points.getY()[i];
            }
            meanX /= (float)points.getCount();
            meanY /= (float)points.getCount();
            float covarianceXX = 0.0f;
            float covarianceXY = 0.0f;
            float covarianceYY = 0.0f;
            int n2 = points.getCount();
            for (int i = 0; i < n2; ++i) {
                float dx = points.getX()[i] - meanX;
                float dy = points.getY()[i] - meanY;
                covarianceXX += dx * dx;
                covarianceXY += dx * dy;
                covarianceYY += dy * dy;
            }
            float angle = (float)(0.5 * Math.atan2(2.0 * (double)covarianceXY, covarianceXX - covarianceYY));
            this.axisX = (float)Math.cos(angle);
            this.axisY = (float)Math.sin(angle);
            float minA = Float.MAX_VALUE;
            float maxA = -3.4028235E38f;
            float minB = Float.MAX_VALUE;
            float maxB = -3.4028235E38f;
            int n3 = points.getCount();
            for (int i = 0; i < n3; ++i) {
                float dx = points.getX()[i] - meanX;
                float dy = points.getY()[i] - meanY;
                float a = dx * this.axisX + dy * this.axisY;
                float b = dx * -this.axisY + dy * this.axisX;
                minA = Math.min(minA, a);
                maxA = Math.max(maxA, a);
                minB = Math.min(minB, b);
                maxB = Math.max(maxB, b);
            }
            this.centerX = meanX + this.axisX * ((minA + maxA) * 0.5f) - this.axisY * ((minB + maxB) * 0.5f);
            this.centerY = meanY + this.axisY * ((minA + maxA) * 0.5f) + this.axisX * ((minB + maxB) * 0.5f);
            this.halfA = Math.max(5.0f, (maxA - minA) * 0.5f + 2.0f);
            this.halfB = Math.max(5.0f, (maxB - minB) * 0.5f + 2.0f);
            this.updatedNanos = System.nanoTime();
        }

        public final boolean contains(double mouseX, double mouseY) {
            if (!this.isFresh()) {
                return false;
            }
            float dx = (float)mouseX - this.centerX;
            float dy = (float)mouseY - this.centerY;
            float a = dx * this.axisX + dy * this.axisY;
            float b = dx * -this.axisY + dy * this.axisX;
            float edgeGrace = 1.5f;
            return Math.abs(a) <= this.halfA + edgeGrace && Math.abs(b) <= this.halfB + edgeGrace;
        }

        public final boolean isFresh() {
            return this.updatedNanos != 0L && System.nanoTime() - this.updatedNanos <= 250000000L;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\b\u0010\tR\"\u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0011\u001a\u00020\n8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\"\u0010\u0015\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/utils/render/modules/post/handsflame/HandsItemHitboxTracker$PointCloud;", "", "<init>", "()V", "", "pointX", "pointY", "", "add", "(FF)V", "", "x", "[F", "getX", "()[F", "setX", "([F)V", "y", "getY", "setY", "", "count", "I", "getCount", "()I", "setCount", "(I)V", "rtx.kimiko:kimiko"})
    private static final class PointCloud {
        @NotNull
        private float[] x = new float[16];
        @NotNull
        private float[] y = new float[16];
        private int count;

        @NotNull
        public final float[] getX() {
            return this.x;
        }

        public final void setX(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.x = fArray;
        }

        @NotNull
        public final float[] getY() {
            return this.y;
        }

        public final void setY(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.y = fArray;
        }

        public final int getCount() {
            return this.count;
        }

        public final void setCount(int n) {
            this.count = n;
        }

        public final void add(float pointX, float pointY) {
            if (this.count >= this.x.length) {
                float[] nextX = new float[this.x.length * 2];
                float[] nextY = new float[this.y.length * 2];
                System.arraycopy(this.x, 0, nextX, 0, this.x.length);
                System.arraycopy(this.y, 0, nextY, 0, this.y.length);
                this.x = nextX;
                this.y = nextY;
            }
            this.x[this.count] = pointX;
            this.y[this.count] = pointY;
            int n = this.count;
            this.count = n + 1;
        }
    }
}

