/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.screen.multiplayer.ConnectScreen
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.network.ServerAddress
 *  net.minecraft.client.network.ServerInfo
 *  net.minecraft.client.network.CookieStorage
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.network.CookieStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.ui.vanilla.FunTimeWarningScreen;
import rtx.kimiko.utils.network.FunTimeJoinGuard;

@Mixin(value={ConnectScreen.class})
public abstract class ConnectScreenFunTimeMixin {
    @Inject(method={"connect(Lnet/minecraft/client/gui/screen/Screen;Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/network/ServerAddress;Lnet/minecraft/client/network/ServerInfo;ZLnet/minecraft/client/network/CookieStorage;)V"}, at={@At(value="HEAD")}, cancellable=true, require=1)
    private static void kimiko$funTimeWarning(Screen screen, MinecraftClient minecraft, ServerAddress serverAddress, ServerInfo serverData, boolean quickPlay, CookieStorage transferState, CallbackInfo ci) {
        FunTimeWarningScreen warning;
        try {
            if (FunTimeJoinGuard.bypass) {
                return;
            }
            if (minecraft.currentScreen instanceof ConnectScreen) {
                return;
            }
            if (!FunTimeJoinGuard.matches(serverAddress, serverData, transferState != null)) {
                return;
            }
            warning = new FunTimeWarningScreen(screen, serverAddress, serverData, quickPlay, transferState);
        }
        catch (Throwable throwable) {
            return;
        }
        ci.cancel();
        minecraft.setScreen((Screen)warning);
    }
}

