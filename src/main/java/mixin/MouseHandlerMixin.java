/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.input.MouseInput
 *  net.minecraft.client.Mouse
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.client.input.MouseInput;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.impl.input.HotBarScrollEvent;
import rtx.kimiko.api.events.impl.input.MouseButtonEvent;
import rtx.kimiko.api.voice.VoiceBindManager;

@Mixin(value={Mouse.class})
public abstract class MouseHandlerMixin {
    @Inject(method={"onMouseButton"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$onMouseButton(long window, MouseInput info, int action, CallbackInfo ci) {
        MouseButtonEvent event;
        if (action == 1) {
            VoiceBindManager.INSTANCE.noteInput();
        }
        if ((event = EventBus.get().post(new MouseButtonEvent(info.button(), info.modifiers(), MouseButtonEvent.Action.of(action)))).isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"onMouseScroll"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$onScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        HotBarScrollEvent event = EventBus.get().post(new HotBarScrollEvent(vertical));
        if (event.isCancelled()) {
            ci.cancel();
        }
    }
}

