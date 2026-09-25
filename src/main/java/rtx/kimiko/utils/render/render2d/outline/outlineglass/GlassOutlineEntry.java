/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.outline.outlineglass;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.render2d.blur.BlurCapture;
import rtx.kimiko.utils.render.render2d.outline.outlineglass.BuiltGlassOutline;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/utils/render/render2d/outline/outlineglass/GlassOutlineEntry;", "", "Lrtx/kimiko/utils/render/render2d/outline/outlineglass/BuiltGlassOutline;", "outline", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "capture", "<init>", "(Lrtx/kimiko/utils/render/render2d/outline/outlineglass/BuiltGlassOutline;Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;)V", "Lrtx/kimiko/utils/render/render2d/outline/outlineglass/BuiltGlassOutline;", "getOutline", "()Lrtx/kimiko/utils/render/render2d/outline/outlineglass/BuiltGlassOutline;", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "getCapture", "()Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "rtx.kimiko:kimiko"})
public final class GlassOutlineEntry {
    @NotNull
    private final BuiltGlassOutline outline;
    @Nullable
    private final BlurCapture capture;

    public GlassOutlineEntry(@NotNull BuiltGlassOutline outline, @Nullable BlurCapture capture) {
        Intrinsics.checkNotNullParameter((Object)outline, (String)"outline");
        this.outline = outline;
        this.capture = capture;
    }

    @NotNull
    public final BuiltGlassOutline getOutline() {
        return this.outline;
    }

    @Nullable
    public final BlurCapture getCapture() {
        return this.capture;
    }
}

