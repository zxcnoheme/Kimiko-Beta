/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.screen.DeathScreen
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.DeathScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.impl.game.DeathScreenEvent;
import rtx.kimiko.api.modules.impl.Visuals.WastedDeath;

@Mixin(value={DeathScreen.class})
public abstract class DeathScreenMixin {
    @Inject(method={"init"}, at={@At(value="HEAD")})
    private void kimiko$onDeathScreenInit(CallbackInfo ci) {
        EventBus.get().post(new DeathScreenEvent());
    }

    @Inject(method={"render"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$wastedOverlay(DrawContext graphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if (!WastedDeath.isRunning()) {
            return;
        }
        WastedDeath module = WastedDeath.getInstance();
        if (module != null) {
            module.renderOverlay(graphics);
        }
        ci.cancel();
    }
}

