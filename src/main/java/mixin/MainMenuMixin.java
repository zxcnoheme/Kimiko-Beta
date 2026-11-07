package mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.ui.menu.WhiteMenu;

@Mixin(TitleScreen.class)
public abstract class MainMenuMixin {
    @Inject(method = "init", at = @At("HEAD"))
    private void kimiko$openWhiteMenu(CallbackInfo ci) {
        try {
            MinecraftClient.getInstance().setScreen(new WhiteMenu());
            // не даём ванилле продолжать init уже снятого TitleScreen (риск реентерабельного краша)
            ci.cancel();
        } catch (Throwable ignored) {
        }
    }
}
