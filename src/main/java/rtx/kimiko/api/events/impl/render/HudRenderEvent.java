/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.render.RenderTickCounter
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.events.impl.render;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.Event;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\b\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0004\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u000fR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/events/impl/render/HudRenderEvent;", "Lrtx/kimiko/api/events/Event;", "Lnet/minecraft/DrawContext;", "graphics", "Lnet/minecraft/RenderTickCounter;", "delta", "<init>", "(Lnet/minecraft/DrawContext;Lnet/minecraft/RenderTickCounter;)V", "getGraphics", "()Lnet/minecraft/DrawContext;", "getDelta", "()Lnet/minecraft/RenderTickCounter;", "", "getPartialTick", "()F", "Lnet/minecraft/DrawContext;", "Lnet/minecraft/RenderTickCounter;", "rtx.kimiko:kimiko"})
public final class HudRenderEvent
extends Event {
    @NotNull
    private final DrawContext graphics;
    @NotNull
    private final RenderTickCounter delta;

    public HudRenderEvent(@NotNull DrawContext graphics, @NotNull RenderTickCounter delta) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        Intrinsics.checkNotNullParameter((Object)delta, (String)"delta");
        this.graphics = graphics;
        this.delta = delta;
    }

    @NotNull
    public final DrawContext getGraphics() {
        return this.graphics;
    }

    @NotNull
    public final RenderTickCounter getDelta() {
        return this.delta;
    }

    public final float getPartialTick() {
        return this.delta.getTickProgress(true);
    }
}

