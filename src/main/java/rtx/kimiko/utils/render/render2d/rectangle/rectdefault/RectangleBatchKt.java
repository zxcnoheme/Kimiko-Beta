/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.render2d.rectangle.rectdefault;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.core.uniform.UniformLayout;

@Metadata(mv={2, 4, 0}, k=2, xi=48, d1={"\u0000\u0010\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0001\u001a\u00020\u00008\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0001\u0010\u0002\"\u0014\u0010\u0003\u001a\u00020\u00008\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0002\"\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"", "PAGE_SIZE", "I", "MAX_PAGES", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "RECTANGLE_LAYOUT", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "rtx.kimiko:kimiko"})
public final class RectangleBatchKt {
    private static final int PAGE_SIZE = 448;
    private static final int MAX_PAGES = 16;
    @NotNull
    private static final UniformLayout RECTANGLE_LAYOUT = UniformLayout.Companion.build("RectangleParams", RectangleBatchKt::RECTANGLE_LAYOUT$lambda$0);

    private static final Unit RECTANGLE_LAYOUT$lambda$0(UniformLayout.Builder $this$build) {
        Intrinsics.checkNotNullParameter((Object)$this$build, (String)"$this$build");
        $this$build.vec4("radii");
        $this$build.vec4("size");
        $this$build.color("colorTopLeft");
        $this$build.color("colorTopRight");
        $this$build.color("colorBottomRight");
        $this$build.color("colorBottomLeft");
        $this$build.vec4("scissorRect");
        $this$build.vec4("scissorRadii");
        $this$build.vec4("scissorRotation");
        return Unit.INSTANCE;
    }

    public static final /* synthetic */ UniformLayout access$getRECTANGLE_LAYOUT$p() {
        return RECTANGLE_LAYOUT;
    }
}

