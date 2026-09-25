/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 */
package mixin.chathads;

import mods.chathads.ChatHeads;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(targets={"net/minecraft/client/font/PlayerHeadGlyphs$HeadGlyph"})
public abstract class PlayerGlyphProviderInstanceMixin {
    @ModifyArg(method={"draw"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/font/PlayerHeadGlyphs$HeadGlyph;drawInternal(Lorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumer;IFFFFFIFFIIII)V", ordinal=0), index=3)
    public float leftHead(float original) {
        if (!ChatHeads.customHeadRendering) {
            return original;
        }
        return original + ChatHeads.CONFIG.threeDeeNess();
    }

    @ModifyArg(method={"draw"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/font/PlayerHeadGlyphs$HeadGlyph;drawInternal(Lorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumer;IFFFFFIFFIIII)V", ordinal=0), index=4)
    public float rightHead(float original) {
        if (!ChatHeads.customHeadRendering) {
            return original;
        }
        return original + ChatHeads.CONFIG.threeDeeNess();
    }

    @ModifyArg(method={"draw"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/font/PlayerHeadGlyphs$HeadGlyph;drawInternal(Lorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumer;IFFFFFIFFIIII)V", ordinal=1), index=4)
    public float rightHat(float original) {
        if (!ChatHeads.customHeadRendering) {
            return original;
        }
        return original + 2.0f * ChatHeads.CONFIG.threeDeeNess();
    }

    @ModifyArg(method={"draw"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/font/PlayerHeadGlyphs$HeadGlyph;drawInternal(Lorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumer;IFFFFFIFFIIII)V", ordinal=1), index=5)
    public float topHat(float original) {
        if (!ChatHeads.customHeadRendering) {
            return original;
        }
        return original - ChatHeads.CONFIG.threeDeeNess();
    }

    @ModifyArg(method={"draw"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/font/PlayerHeadGlyphs$HeadGlyph;drawInternal(Lorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumer;IFFFFFIFFIIII)V", ordinal=1), index=6)
    public float bottomHat(float original) {
        if (!ChatHeads.customHeadRendering) {
            return original;
        }
        return original + ChatHeads.CONFIG.threeDeeNess();
    }
}

