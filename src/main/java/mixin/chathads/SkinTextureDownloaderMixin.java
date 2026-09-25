/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.sugar.Local
 *  net.minecraft.client.texture.NativeImage
 *  net.minecraft.client.texture.NativeImageBackedTexture
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.client.texture.PlayerSkinTextureDownloader
 *  net.minecraft.util.AssetInfo$TextureAsset
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 */
package mixin.chathads;

import com.llamalad7.mixinextras.sugar.Local;
import java.util.function.Supplier;
import mods.chathads.ChatHeads;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.PlayerSkinTextureDownloader;
import net.minecraft.util.AssetInfo;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value={PlayerSkinTextureDownloader.class})
public abstract class SkinTextureDownloaderMixin {
    @ModifyArg(method={"registerTexture"}, at=@At(value="INVOKE", target="Ljava/util/concurrent/CompletableFuture;supplyAsync(Ljava/util/function/Supplier;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;"))
    private static Supplier<?> chatheads$registerBlendedHeadTexture(Supplier<?> supplier, @Local(argsOnly=true) AssetInfo.TextureAsset texture, @Local(argsOnly=true) NativeImage image) {
        return () -> {
            Identifier textureLocation = texture.texturePath();
            if (textureLocation.getPath().startsWith("skins/")) {
                MinecraftClient.getInstance().getTextureManager().registerTexture(ChatHeads.getBlendedHeadLocation(textureLocation), (AbstractTexture)new NativeImageBackedTexture(() -> "Chat Head of " + textureLocation.getPath(), ChatHeads.extractBlendedHead(image)));
                ChatHeads.blendedHeadTextures.add(textureLocation);
            }
            return supplier.get();
        };
    }
}

