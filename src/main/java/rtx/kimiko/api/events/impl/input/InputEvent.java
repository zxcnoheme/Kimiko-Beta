/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.PlayerInput
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.events.impl.input;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.PlayerInput;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.Event;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\u0005J-\u0010\u000f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\n\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\n\u00a2\u0006\u0004\b\u0015\u0010\u0013JE\u0010\u0019\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\n\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\b\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u000b\u001a\u00020\u001d\u00a2\u0006\u0004\b\u000b\u0010\u001eJ\r\u0010 \u001a\u00020\u001f\u00a2\u0006\u0004\b \u0010!R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\"\u00a8\u0006#"}, d2={"Lrtx/kimiko/api/events/impl/input/InputEvent;", "Lrtx/kimiko/api/events/Event;", "Lnet/minecraft/PlayerInput;", "input", "<init>", "(Lnet/minecraft/PlayerInput;)V", "getInput", "()Lnet/minecraft/PlayerInput;", "", "setInput", "", "forward", "backward", "left", "right", "setDirectionalLow", "(ZZZZ)V", "jumping", "setJumping", "(Z)V", "sprinting", "setSprinting", "sneak", "sprint", "jump", "setDirectional", "(ZZZZZZZ)V", "inputNone", "()V", "", "()I", "", "sideways", "()F", "Lnet/minecraft/PlayerInput;", "rtx.kimiko:kimiko"})
public final class InputEvent
extends Event {
    @NotNull
    private PlayerInput input;

    public InputEvent(@NotNull PlayerInput input) {
        Intrinsics.checkNotNullParameter((Object)input, (String)"input");
        this.input = input;
    }

    @NotNull
    public final PlayerInput getInput() {
        return this.input;
    }

    public final void setInput(@NotNull PlayerInput input) {
        Intrinsics.checkNotNullParameter((Object)input, (String)"input");
        this.input = input;
    }

    public final void setDirectionalLow(boolean forward, boolean backward, boolean left, boolean right) {
        this.input = new PlayerInput(forward, backward, left, right, this.input.jump(), this.input.sneak(), this.input.sprint());
    }

    public final void setJumping(boolean jumping) {
        this.input = new PlayerInput(this.input.forward(), this.input.backward(), this.input.left(), this.input.right(), jumping, this.input.sneak(), this.input.sprint());
    }

    public final void setSprinting(boolean sprinting) {
        this.input = new PlayerInput(this.input.forward(), this.input.backward(), this.input.left(), this.input.right(), this.input.jump(), this.input.sneak(), sprinting);
    }

    public final void setDirectional(boolean forward, boolean backward, boolean left, boolean right, boolean sneak, boolean sprint, boolean jump) {
        this.input = new PlayerInput(forward, backward, left, right, jump, sneak, sprint);
    }

    public final void inputNone() {
        this.input = new PlayerInput(false, false, false, false, false, false, false);
    }

    public final int forward() {
        return this.input.forward() ? 1 : (this.input.backward() ? -1 : 0);
    }

    public final float sideways() {
        return this.input.left() ? 1.0f : (this.input.right() ? -1.0f : 0.0f);
    }
}

