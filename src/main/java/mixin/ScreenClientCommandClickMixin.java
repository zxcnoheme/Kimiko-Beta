/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.network.ClientPlayNetworkHandler
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.chat.commands.CommandManager;

@Mixin(value={ClientPlayNetworkHandler.class})
public abstract class ScreenClientCommandClickMixin {
    @Inject(method={"runClickEventCommand"}, at={@At(value="HEAD")}, cancellable=true, require=1)
    private void kimiko$routeUnattendedClick(String command, Screen screen, CallbackInfo ci) {
        if (ScreenClientCommandClickMixin.kimiko$dispatchLocal(command)) {
            ci.cancel();
        }
    }

    @Inject(method={"sendChatCommand"}, at={@At(value="HEAD")}, cancellable=true, require=1)
    private void kimiko$routeSendCommand(String command, CallbackInfo ci) {
        if (ScreenClientCommandClickMixin.kimiko$dispatchLocal(command)) {
            ci.cancel();
        }
    }

    @Inject(method={"sendChatMessage"}, at={@At(value="HEAD")}, cancellable=true, require=1)
    private void kimiko$routeSendChat(String message, CallbackInfo ci) {
        if (ScreenClientCommandClickMixin.kimiko$dispatchLocal(message)) {
            ci.cancel();
        }
    }

    private static boolean kimiko$dispatchLocal(String text) {
        if (text == null) {
            return false;
        }
        CommandManager manager = CommandManager.get();
        if (!manager.isClientCommand(text)) {
            return false;
        }
        manager.executeRaw(text.substring(manager.getPrefix().length()));
        return true;
    }
}

