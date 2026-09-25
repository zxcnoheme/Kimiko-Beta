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
package rtx.kimiko.utils.render.render2d.circle;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.core.uniform.UniformLayout;

@Metadata(mv={2, 4, 0}, k=2, xi=48, d1={"\u0000\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0001\u001a\u00020\u00008\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0001\u0010\u0002\"\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u0005\u00a8\u0006\u0006"}, d2={"", "MAX_CIRCLES", "I", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "CIRCLE_LAYOUT", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "rtx.kimiko:kimiko"})
public final class CircleBatchKt {
    private static final int MAX_CIRCLES = 512;
    @NotNull
    private static final UniformLayout CIRCLE_LAYOUT = UniformLayout.Companion.build("CircleParams", CircleBatchKt::CIRCLE_LAYOUT$lambda$0);

    private static final Unit CIRCLE_LAYOUT$lambda$0(UniformLayout.Builder $this$build) {
        Intrinsics.checkNotNullParameter((Object)$this$build, (String)"$this$build");
        $this$build.vec4("shape");
        $this$build.color("tint");
        return Unit.INSTANCE;
    }

    public static final /* synthetic */ UniformLayout access$getCIRCLE_LAYOUT$p() {
        return CIRCLE_LAYOUT;
    }
}

