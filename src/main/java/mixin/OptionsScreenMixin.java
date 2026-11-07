package mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.ui.menu.WhiteOptionsScreen;

/**
 * Подменяет ванильное меню настроек (OptionsScreen) на кастомное WhiteOptionsScreen
 * во всех точках входа: главное меню Kimiko, PlayPage, пауза игры.
 */
@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin {

    @Unique
    private static Screen kimiko$optionsParent;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void kimiko$captureParent(Screen parent, GameOptions gameOptions, CallbackInfo ci) {
        kimiko$optionsParent = parent;
    }

    @Inject(method = "init", at = @At("HEAD"))
    private void kimiko$openCustom(CallbackInfo ci) {
        try {
            MinecraftClient.getInstance().setScreen(new WhiteOptionsScreen(kimiko$optionsParent));
            // не даём ванилле достраивать уже заменённый экран
            ci.cancel();
        } catch (Throwable ignored) {
        }
    }
}
