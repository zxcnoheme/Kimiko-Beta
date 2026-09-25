/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.screen.ChatScreen
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.chatanim;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import mods.chatanim.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Visuals.BetterMinecraft;

@Mixin(value={ChatScreen.class})
public abstract class ChatScreenMixin {
    @Unique
    private boolean kimiko$chatAnimWasOpenedLastFrame = false;
    @Unique
    private long kimiko$chatAnimLastOpenTime = 0L;
    @Unique
    private float kimiko$chatAnimDisplacement = 0.0f;

    @Unique
    private float kimiko$chatAnimCalculateDisplacement() {
        ModConfig config = ModConfig.getConfig();
        if (!BetterMinecraft.chatAnimationsEnabled() || !config.enableTextFieldAnimation) {
            return 0.0f;
        }
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && !this.kimiko$chatAnimWasOpenedLastFrame && !client.player.isSleeping()) {
            this.kimiko$chatAnimWasOpenedLastFrame = true;
            this.kimiko$chatAnimLastOpenTime = System.currentTimeMillis();
        }
        float fadeTime = config.fadeTimeTextField;
        float fadeOffset = 8.0f;
        float screenFactor = (float)client.getWindow().getFramebufferHeight() / 1080.0f;
        float timeSinceOpen = Math.min((float)(System.currentTimeMillis() - this.kimiko$chatAnimLastOpenTime), fadeTime);
        float alpha = 1.0f - timeSinceOpen / fadeTime;
        float c1 = 1.70158f;
        float c3 = c1 + 1.0f;
        float modifiedAlpha = c3 * alpha * alpha * alpha - c1 * alpha * alpha;
        return modifiedAlpha * fadeOffset * screenFactor;
    }

    @WrapOperation(method={"render"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V")})
    private void kimiko$chatAnimWrapBackgroundFill(DrawContext graphics, int x0, int y0, int x1, int y1, int color, Operation<Void> original) {
        this.kimiko$chatAnimDisplacement = this.kimiko$chatAnimCalculateDisplacement();
        if (this.kimiko$chatAnimDisplacement != 0.0f) {
            graphics.getMatrices().pushMatrix();
            graphics.getMatrices().translate(0.0f, this.kimiko$chatAnimDisplacement);
            original.call(new Object[]{graphics, x0, y0, x1, y1, color});
            graphics.getMatrices().popMatrix();
        } else {
            original.call(new Object[]{graphics, x0, y0, x1, y1, color});
        }
    }

    @WrapOperation(method={"render"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/screen/Screen;render(Lnet/minecraft/client/gui/DrawContext;IIF)V")})
    private void kimiko$chatAnimWrapSuperAndSuggestions(ChatScreen instance, DrawContext graphics, int mouseX, int mouseY, float delta, Operation<Void> original) {
        if (this.kimiko$chatAnimDisplacement != 0.0f) {
            graphics.getMatrices().pushMatrix();
            graphics.getMatrices().translate(0.0f, this.kimiko$chatAnimDisplacement);
            original.call(new Object[]{instance, graphics, mouseX, mouseY, Float.valueOf(delta)});
            graphics.getMatrices().popMatrix();
        } else {
            original.call(new Object[]{instance, graphics, mouseX, mouseY, Float.valueOf(delta)});
        }
    }

    @Inject(method={"removed"}, at={@At(value="HEAD")})
    private void kimiko$chatAnimClosed(CallbackInfo ci) {
        this.kimiko$chatAnimWasOpenedLastFrame = false;
    }
}

