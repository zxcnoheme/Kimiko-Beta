/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.input.KeyInput
 *  net.minecraft.client.Keyboard
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.screen.Screen
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.client.input.KeyInput;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.impl.input.KeyPressEvent;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.ClickGui;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.api.voice.VoiceBindManager;
import rtx.kimiko.utils.sounds.Sounds;

@Mixin(value={Keyboard.class})
public abstract class KeyboardHandlerMixin {
    @Inject(method={"onKey"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$onKeyPress(long window, int action, KeyInput keyEvent, CallbackInfo ci) {
        KeyPressEvent event;
        if (action == 1) {
            VoiceBindManager.INSTANCE.noteInput();
            ClickGui clickGui = ModuleManager.get().get(ClickGui.class);
            if (clickGui != null && keyEvent.key() == clickGui.getBind().getCode()) {
                MinecraftClient mc = MinecraftClient.getInstance();
                if (mc.world != null && mc.player != null && mc.currentScreen == null) {
                    mc.setScreen((Screen)UI.INSTANCE);
                    Sounds.play("gui_open");
                    ci.cancel();
                    return;
                }
            }
        }
        if ((event = EventBus.get().post(new KeyPressEvent(keyEvent.key(), keyEvent.scancode(), keyEvent.modifiers(), KeyPressEvent.Action.of(action)))).isCancelled()) {
            ci.cancel();
        }
    }
}

