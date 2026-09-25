/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.screen.slot.SlotActionType
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.events.impl.inventory;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.screen.slot.SlotActionType;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.CancellableEvent;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0002\u00a2\u0006\u0004\b\f\u0010\u000bJ\r\u0010\r\u001a\u00020\u0002\u00a2\u0006\u0004\b\r\u0010\u000bJ\r\u0010\u000e\u001a\u00020\u0006\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0010R\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u0010R\u0014\u0010\u0005\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0011\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/api/events/impl/inventory/ClickSlotEvent;", "Lrtx/kimiko/api/events/CancellableEvent;", "", "windowId", "slotId", "button", "Lnet/minecraft/SlotActionType;", "actionType", "<init>", "(IIILnet/minecraft/SlotActionType;)V", "getWindowId", "()I", "getSlotId", "getButton", "getActionType", "()Lnet/minecraft/SlotActionType;", "I", "Lnet/minecraft/SlotActionType;", "rtx.kimiko:kimiko"})
public final class ClickSlotEvent
extends CancellableEvent {
    private final int windowId;
    private final int slotId;
    private final int button;
    @NotNull
    private final SlotActionType actionType;

    public ClickSlotEvent(int windowId, int slotId, int button, @NotNull SlotActionType actionType) {
        Intrinsics.checkNotNullParameter((Object)actionType, (String)"actionType");
        this.windowId = windowId;
        this.slotId = slotId;
        this.button = button;
        this.actionType = actionType;
    }

    public final int getWindowId() {
        return this.windowId;
    }

    public final int getSlotId() {
        return this.slotId;
    }

    public final int getButton() {
        return this.button;
    }

    @NotNull
    public final SlotActionType getActionType() {
        return this.actionType;
    }
}

