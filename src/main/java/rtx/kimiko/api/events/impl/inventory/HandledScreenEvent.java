/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  net.minecraft.screen.slot.Slot
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.events.impl.inventory;

import kotlin.Metadata;
import net.minecraft.screen.slot.Slot;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.Event;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\u0018\u00002\u00020\u0001B+\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\nB#\b\u0016\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\u000bJ\u000f\u0010\f\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0012\u0010\u0011R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0013R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0014R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0015R\u0014\u0010\b\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0015\u00a8\u0006\u0016"}, d2={"Lrtx/kimiko/api/events/impl/inventory/HandledScreenEvent;", "Lrtx/kimiko/api/events/Event;", "Lnet/minecraft/DrawContext;", "graphics", "Lnet/minecraft/Slot;", "slotHover", "", "imageWidth", "imageHeight", "<init>", "(Lnet/minecraft/DrawContext;Lnet/minecraft/Slot;II)V", "(Lnet/minecraft/Slot;II)V", "getGraphics", "()Lnet/minecraft/DrawContext;", "getSlotHover", "()Lnet/minecraft/Slot;", "getImageWidth", "()I", "getImageHeight", "Lnet/minecraft/DrawContext;", "Lnet/minecraft/Slot;", "I", "rtx.kimiko:kimiko"})
public final class HandledScreenEvent
extends Event {
    @Nullable
    private final DrawContext graphics;
    @Nullable
    private final Slot slotHover;
    private final int imageWidth;
    private final int imageHeight;

    public HandledScreenEvent(@Nullable DrawContext graphics, @Nullable Slot slotHover, int imageWidth, int imageHeight) {
        this.graphics = graphics;
        this.slotHover = slotHover;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public HandledScreenEvent(@Nullable Slot slotHover, int imageWidth, int imageHeight) {
        this(null, slotHover, imageWidth, imageHeight);
    }

    @Nullable
    public final DrawContext getGraphics() {
        return this.graphics;
    }

    @Nullable
    public final Slot getSlotHover() {
        return this.slotHover;
    }

    public final int getImageWidth() {
        return this.imageWidth;
    }

    public final int getImageHeight() {
        return this.imageHeight;
    }
}

