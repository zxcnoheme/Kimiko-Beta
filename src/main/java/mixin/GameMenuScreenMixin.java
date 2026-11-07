package mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.ui.menu.WhitePauseScreen;

/**
 * Подменяет ванильное меню паузы (Esc в игре) на кастомное WhitePauseScreen.
 */
@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin {

    @Inject(method = "init", at = @At("HEAD"))
    private void kimiko$openCustomPause(CallbackInfo ci) {
        try {
            MinecraftClient.getInstance().setScreen(new WhitePauseScreen());
            ci.cancel();
        } catch (Throwable ignored) {
        }
    }
}
