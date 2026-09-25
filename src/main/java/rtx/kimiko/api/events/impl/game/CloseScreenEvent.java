/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  net.minecraft.client.gui.screen.Screen
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.events.impl.game;

import kotlin.Metadata;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.CancellableEvent;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/events/impl/game/CloseScreenEvent;", "Lrtx/kimiko/api/events/CancellableEvent;", "Lnet/minecraft/Screen;", "screen", "<init>", "(Lnet/minecraft/Screen;)V", "getScreen", "()Lnet/minecraft/Screen;", "Lnet/minecraft/Screen;", "rtx.kimiko:kimiko"})
public final class CloseScreenEvent
extends CancellableEvent {
    @Nullable
    private final Screen screen;

    public CloseScreenEvent(@Nullable Screen screen) {
        this.screen = screen;
    }

    @Nullable
    public final Screen getScreen() {
        return this.screen;
    }
}

