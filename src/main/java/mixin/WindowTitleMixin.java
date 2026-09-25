/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.util.Window
 *  net.minecraft.client.util.MonitorTracker
 *  net.minecraft.client.WindowEventHandler
 *  net.minecraft.client.WindowSettings
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.client.util.Window;
import net.minecraft.client.util.MonitorTracker;
import net.minecraft.client.WindowEventHandler;
import net.minecraft.client.WindowSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.ui.window.MainWindow;

@Mixin(value={Window.class})
public abstract class WindowTitleMixin {
    @Shadow
    @Final
    private long handle;

    @ModifyVariable(method={"<init>"}, at=@At(value="HEAD"), argsOnly=true, ordinal=0)
    private static String kimiko$forceInitialTitle(String original) {
        return MainWindow.getTitle();
    }

    @ModifyVariable(method={"setTitle"}, at=@At(value="HEAD"), argsOnly=true)
    private String kimiko$forceTitle(String original) {
        return MainWindow.getTitle();
    }

    @Inject(method={"<init>"}, at={@At(value="TAIL")})
    private void kimiko$applyDarkTitleBar(WindowEventHandler eventHandler, MonitorTracker screenManager, WindowSettings displayData, String preferredFullscreenVideoMode, String title, CallbackInfo ci) {
        MainWindow.applyDarkMode(this.handle);
    }
}

