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
package rtx.kimiko.utils.render.render2d.outline.outline360;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.core.uniform.UniformLayout;

@Metadata(mv={2, 4, 0}, k=2, xi=48, d1={"\u0000\u0010\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u0014\u0010\u0001\u001a\u00020\u00008\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0001\u0010\u0002\"\u0014\u0010\u0003\u001a\u00020\u00008\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0002\"\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\"\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006\u00a8\u0006\b"}, d2={"", "MAX_OUTLINES", "I", "MAX_RANGES", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "OUTLINE_360_LAYOUT", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "RANGE_LAYOUT", "rtx.kimiko:kimiko"})
public final class Outline360BatchKt {
    private static final int MAX_OUTLINES = 256;
    private static final int MAX_RANGES = 1024;
    @NotNull
    private static final UniformLayout OUTLINE_360_LAYOUT = UniformLayout.Companion.build("Outline360Params", Outline360BatchKt::OUTLINE_360_LAYOUT$lambda$0);
    @NotNull
    private static final UniformLayout RANGE_LAYOUT = UniformLayout.Companion.build("Outline360Range", Outline360BatchKt::RANGE_LAYOUT$lambda$0);

    private static final Unit OUTLINE_360_LAYOUT$lambda$0(UniformLayout.Builder $this$build) {
        Intrinsics.checkNotNullParameter((Object)$this$build, (String)"$this$build");
        $this$build.vec4("radii");
        $this$build.vec4("size");
        $this$build.color("defaultColor");
        $this$build.vec4("ranges");
        return Unit.INSTANCE;
    }

    private static final Unit RANGE_LAYOUT$lambda$0(UniformLayout.Builder $this$build) {
        Intrinsics.checkNotNullParameter((Object)$this$build, (String)"$this$build");
        $this$build.vec4("angles");
        $this$build.color("color");
        $this$build.color("colorEnd");
        return Unit.INSTANCE;
    }

    public static final /* synthetic */ UniformLayout access$getOUTLINE_360_LAYOUT$p() {
        return OUTLINE_360_LAYOUT;
    }

    public static final /* synthetic */ UniformLayout access$getRANGE_LAYOUT$p() {
        return RANGE_LAYOUT;
    }
}

