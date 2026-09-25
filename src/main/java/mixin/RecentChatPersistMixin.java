/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.hud.ChatHud
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.utils.chat.ChatHistory;

@Mixin(value={ChatHud.class})
public class RecentChatPersistMixin {
    @Inject(method={"addToMessageHistory"}, at={@At(value="HEAD")})
    private void kimiko$persist(String message, CallbackInfo ci) {
        if (!ChatHistory.seeding) {
            ChatHistory.add(message);
        }
    }
}

