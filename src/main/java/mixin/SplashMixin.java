package mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.SplashOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.ui.menu.WhiteLoading;

@Mixin(SplashOverlay.class)
public abstract class SplashMixin {
    @Inject(method = "renderProgressBar", at = @At("HEAD"), cancellable = true, require = 0)
    private void kimiko$whiteLoading(DrawContext context, int x, int y, int width, int height, float progress, CallbackInfo ci) {
        try {
            WhiteLoading.get().setProgress(progress);
            WhiteLoading.get().render(context);
            ci.cancel();
        } catch (Throwable ignored) {
        }
    }
}
