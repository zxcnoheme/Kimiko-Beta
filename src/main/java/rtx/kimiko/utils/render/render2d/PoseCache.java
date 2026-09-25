/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fc
 */
package rtx.kimiko.utils.render.render2d;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00108\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u000f\u00a8\u0006\u0014"}, d2={"Lrtx/kimiko/utils/render/render2d/PoseCache;", "", "<init>", "()V", "Lorg/joml/Matrix3x2f;", "pose", "Lkotlin/jvm/JvmStatic;", "snapshot", "(Lorg/joml/Matrix3x2f;)Lorg/joml/Matrix3x2f;", "cached", "", "matches", "(Lorg/joml/Matrix3x2f;Lorg/joml/Matrix3x2f;)Z", "", "SLOTS", "I", "", "CACHE", "[Lorg/joml/Matrix3x2f;", "cursor", "rtx.kimiko:kimiko"})
public final class PoseCache {
    @NotNull
    public static final PoseCache INSTANCE = new PoseCache();
    private static final int SLOTS = 4;
    @NotNull
    private static final Matrix3x2f[] CACHE = new Matrix3x2f[4];
    private static int cursor;

    private PoseCache() {
    }

    @JvmStatic
    @NotNull
    public static final Matrix3x2f snapshot(@NotNull Matrix3x2f pose) {
        Matrix3x2f copy;
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        for (int slot = 0; slot < 4; ++slot) {
            Matrix3x2f cached = CACHE[slot];
            if (cached == null || !INSTANCE.matches(cached, pose)) continue;
            return cached;
        }
        PoseCache.CACHE[PoseCache.cursor] = copy = new Matrix3x2f((Matrix3x2fc)pose);
        cursor = (cursor + 1) % 4;
        return copy;
    }

    private final boolean matches(Matrix3x2f cached, Matrix3x2f pose) {
        return cached.m00 == pose.m00 && cached.m01 == pose.m01 && cached.m10 == pose.m10 && cached.m11 == pose.m11 && cached.m20 == pose.m20 && cached.m21 == pose.m21;
    }
}

