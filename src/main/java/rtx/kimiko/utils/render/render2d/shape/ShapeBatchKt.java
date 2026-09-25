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
package rtx.kimiko.utils.render.render2d.shape;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.core.uniform.UniformLayout;

@Metadata(mv={2, 4, 0}, k=2, xi=48, d1={"\u0000\u0010\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0001\u001a\u00020\u00008\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0001\u0010\u0002\"\u0014\u0010\u0003\u001a\u00020\u00008\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0002\"\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"", "MAX_SHAPES", "I", "MAX_SPANS", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "SHAPE_LAYOUT", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "rtx.kimiko:kimiko"})
public final class ShapeBatchKt {
    private static final int MAX_SHAPES = 16;
    private static final int MAX_SPANS = 64;
    @NotNull
    private static final UniformLayout SHAPE_LAYOUT = UniformLayout.Companion.build("ShapeParams", ShapeBatchKt::SHAPE_LAYOUT$lambda$0);

    private static final Unit SHAPE_LAYOUT$lambda$0(UniformLayout.Builder $this$build) {
        Intrinsics.checkNotNullParameter((Object)$this$build, (String)"$this$build");
        $this$build.vec4("radii");
        $this$build.vec4("size");
        $this$build.vec4("fresnel");
        $this$build.color("fresnelColor");
        $this$build.vec4("material");
        $this$build.color("color");
        $this$build.color("secondColor");
        $this$build.vec4("capture");
        $this$build.vec4("spanInfo");
        $this$build.vec4("rowWave");
        $this$build.vec4Array("spans", 64);
        return Unit.INSTANCE;
    }

    public static final /* synthetic */ UniformLayout access$getSHAPE_LAYOUT$p() {
        return SHAPE_LAYOUT;
    }
}

