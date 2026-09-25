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
package rtx.kimiko.utils.render.render2d.radialglass;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.core.uniform.UniformLayout;

@Metadata(mv={2, 4, 0}, k=2, xi=48, d1={"\u0000\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0001\u001a\u00020\u00008\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0001\u0010\u0002\"\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u0005\u00a8\u0006\u0006"}, d2={"", "MAX_SECTORS", "I", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "RADIAL_GLASS_LAYOUT", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "rtx.kimiko:kimiko"})
public final class RadialGlassBatchKt {
    private static final int MAX_SECTORS = 48;
    @NotNull
    private static final UniformLayout RADIAL_GLASS_LAYOUT = UniformLayout.Companion.build("RadialGlassParams", RadialGlassBatchKt::RADIAL_GLASS_LAYOUT$lambda$0);

    private static final Unit RADIAL_GLASS_LAYOUT$lambda$0(UniformLayout.Builder $this$build) {
        Intrinsics.checkNotNullParameter((Object)$this$build, (String)"$this$build");
        $this$build.vec4("ring");
        $this$build.vec4("geometry");
        $this$build.vec4("fresnel");
        $this$build.color("fresnelColor");
        $this$build.vec4("material");
        $this$build.color("color");
        $this$build.color("secondColor");
        $this$build.vec4("capture");
        $this$build.color("highlightColor");
        $this$build.vec4("reserved");
        return Unit.INSTANCE;
    }

    public static final /* synthetic */ UniformLayout access$getRADIAL_GLASS_LAYOUT$p() {
        return RADIAL_GLASS_LAYOUT;
    }
}

