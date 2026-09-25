/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.events.impl.render;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.Event;
import rtx.kimiko.utils.render.util.underhand.UnderHand2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/events/impl/render/UnderHandRenderEvent;", "Lrtx/kimiko/api/events/Event;", "Lrtx/kimiko/utils/render/util/underhand/UnderHand2D;", "ctx", "<init>", "(Lrtx/kimiko/utils/render/util/underhand/UnderHand2D;)V", "()Lrtx/kimiko/utils/render/util/underhand/UnderHand2D;", "Lrtx/kimiko/utils/render/util/underhand/UnderHand2D;", "rtx.kimiko:kimiko"})
public final class UnderHandRenderEvent
extends Event {
    @NotNull
    private final UnderHand2D ctx;

    public UnderHandRenderEvent(@NotNull UnderHand2D ctx) {
        Intrinsics.checkNotNullParameter((Object)ctx, (String)"ctx");
        this.ctx = ctx;
    }

    @NotNull
    public final UnderHand2D ctx() {
        return this.ctx;
    }
}

