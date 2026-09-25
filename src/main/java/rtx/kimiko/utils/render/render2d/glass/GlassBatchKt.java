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
package rtx.kimiko.utils.render.render2d.glass;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.core.uniform.UniformLayout;

@Metadata(mv={2, 4, 0}, k=2, xi=48, d1={"\u0000\u0018\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0001\u001a\u00020\u00008\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0001\u0010\u0002\"\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u0005\"\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"", "MAX_GLASSES", "I", "", "PALETTE_SLOT_BASE", "F", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "GLASS_LAYOUT", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "rtx.kimiko:kimiko"})
public final class GlassBatchKt {
    private static final int MAX_GLASSES = 448;
    private static final float PALETTE_SLOT_BASE = 10.0f;
    @NotNull
    private static final UniformLayout GLASS_LAYOUT = UniformLayout.Companion.build("GlassParams", GlassBatchKt::GLASS_LAYOUT$lambda$0);

    private static final Unit GLASS_LAYOUT$lambda$0(UniformLayout.Builder $this$build) {
        Intrinsics.checkNotNullParameter((Object)$this$build, (String)"$this$build");
        $this$build.vec4("radii");
        $this$build.vec4("size");
        $this$build.vec4("fresnel");
        $this$build.color("fresnelColor");
        $this$build.vec4("material");
        $this$build.color("color");
        $this$build.color("secondColor");
        $this$build.vec4("capture");
        $this$build.vec4("scissorFade");
        return Unit.INSTANCE;
    }

    public static final /* synthetic */ UniformLayout access$getGLASS_LAYOUT$p() {
        return GLASS_LAYOUT;
    }
}

