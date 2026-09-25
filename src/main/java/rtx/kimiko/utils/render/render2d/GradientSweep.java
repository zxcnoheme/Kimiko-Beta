/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.render2d;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u0013\u0010\b\u001a\u00020\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\tR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\r\u0010\f\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/utils/render/render2d/GradientSweep;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "trigger", "", "progress", "()F", "", "SWEEP_MS", "J", "startMs", "rtx.kimiko:kimiko"})
public final class GradientSweep {
    @NotNull
    public static final GradientSweep INSTANCE = new GradientSweep();
    private static final long SWEEP_MS = 700L;
    private static long startMs = -4611686018427387904L;

    private GradientSweep() {
    }

    @JvmStatic
    public static final void trigger() {
        startMs = System.currentTimeMillis();
    }

    @JvmStatic
    public static final float progress() {
        float p = (float)(System.currentTimeMillis() - startMs) / 700.0f;
        return p < 0.0f || p > 1.0f ? -1.0f : p;
    }
}

