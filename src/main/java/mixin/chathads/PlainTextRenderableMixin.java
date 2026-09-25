/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyExpressionValue
 *  net.minecraft.client.font.DrawnSpriteGlyph
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 */
package mixin.chathads;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import mods.chathads.ChatHeads;
import net.minecraft.client.font.DrawnSpriteGlyph;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value={DrawnSpriteGlyph.class})
public interface PlainTextRenderableMixin {
    @Shadow
    public int shadowColor();

    @ModifyExpressionValue(method={"render"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/font/DrawnSpriteGlyph;shadowColor()I")})
    default public int chatheads$disableShadow(int original) {
        return ChatHeads.customHeadRendering && !ChatHeads.CONFIG.drawShadow() ? 0 : original;
    }

    @ModifyArg(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/font/DrawnSpriteGlyph;draw(Lorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumer;IFFFI)V", ordinal=1), index=4)
    default public float chatheads$moveDownWhenShadowDisabled(float offsetY) {
        return ChatHeads.customHeadRendering && !ChatHeads.CONFIG.drawShadow() && this.shadowColor() != 0 ? offsetY + 1.0f : offsetY;
    }
}

