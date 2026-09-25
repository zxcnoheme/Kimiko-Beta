/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.PlayerInput
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.util.PlayerInput;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.impl.game.CloseScreenEvent;
import rtx.kimiko.api.events.impl.player.PlayerMoveEvent;
import rtx.kimiko.api.events.impl.player.UsingItemEvent;

@Mixin(value={ClientPlayerEntity.class})
public abstract class LocalPlayerMixin {
    @Inject(method={"closeHandledScreen"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$closeHandledScreenHook(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        Screen screen = client == null ? null : client.currentScreen;
        EventBus bus = EventBus.get();
        if (!bus.hasListeners(CloseScreenEvent.class)) {
            return;
        }
        CloseScreenEvent event = bus.post(new CloseScreenEvent(screen));
        if (event.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"tickMovement"}, at={@At(value="HEAD")})
    private void kimiko$onAiStep(CallbackInfo ci) {
        ClientPlayerEntity self = (ClientPlayerEntity)(Object)this;
        if (self.input == null || self.input.playerInput == null) {
            return;
        }
        EventBus bus = EventBus.get();
        if (!bus.hasListeners(PlayerMoveEvent.class)) {
            return;
        }
        PlayerInput current = self.input.playerInput;
        PlayerMoveEvent event = bus.post(new PlayerMoveEvent(current.forward(), current.backward(), current.left(), current.right(), current.jump(), current.sprint(), current.sneak()));
        if (event.isCancelled()) {
            self.input.playerInput = PlayerInput.DEFAULT;
        } else if (this.kimiko$inputChanged(current, event)) {
            self.input.playerInput = new PlayerInput(event.isForward(), event.isBackward(), event.isLeft(), event.isRight(), event.isJump(), event.isShift(), event.isSprint());
        }
    }

    @Inject(method={"tickMovement"}, at={@At(value="HEAD")})
    private void kimiko$onUsingItemPre(CallbackInfo ci) {
        ClientPlayerEntity self = (ClientPlayerEntity)(Object)this;
        if (!self.isUsingItem()) {
            return;
        }
        EventBus bus = EventBus.get();
        if (bus.hasListeners(UsingItemEvent.class)) {
            bus.post(new UsingItemEvent(0));
        }
    }

    @Inject(method={"tickMovement"}, at={@At(value="RETURN")})
    private void kimiko$onUsingItemPost(CallbackInfo ci) {
        ClientPlayerEntity self = (ClientPlayerEntity)(Object)this;
        if (!self.isUsingItem()) {
            return;
        }
        EventBus bus = EventBus.get();
        if (bus.hasListeners(UsingItemEvent.class)) {
            bus.post(new UsingItemEvent(1));
        }
    }

    @Unique
    private boolean kimiko$inputChanged(PlayerInput current, PlayerMoveEvent event) {
        return current.forward() != event.isForward() || current.backward() != event.isBackward() || current.left() != event.isLeft() || current.right() != event.isRight() || current.jump() != event.isJump() || current.sprint() != event.isSprint() || current.sneak() != event.isShift();
    }
}

