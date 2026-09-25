/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyExpressionValue
 *  net.minecraft.client.font.PlayerHeadGlyphs
 *  net.minecraft.client.font.GlyphMetrics
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 */
package mixin.chathads;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import mods.chathads.PaddedChatGlyph;
import net.minecraft.client.font.PlayerHeadGlyphs;
import net.minecraft.client.font.GlyphMetrics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={PlayerHeadGlyphs.class}, priority=500)
public abstract class PlayerGlyphProviderMixin {
    @ModifyExpressionValue(method={"<clinit>"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/font/GlyphMetrics;empty(F)Lnet/minecraft/client/font/GlyphMetrics;")})
    private static GlyphMetrics chatheads$addPaddingInsideChat(GlyphMetrics original) {
        return new PaddedChatGlyph(original);
    }
}

