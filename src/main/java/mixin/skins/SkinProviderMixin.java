package mixin.skins;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.texture.PlayerSkinProvider;
import net.minecraft.entity.player.SkinTextures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rtx.kimiko.api.ui.menu.alts.SkinLibrary;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * Локальные скины Kimiko: если для ника есть свой PNG (папка kimiko/skins),
 * игровой провайдер скинов сразу отдаёт его — и головы в меню, и игрок в мире.
 */
@Mixin(PlayerSkinProvider.class)
public class SkinProviderMixin {
    @Inject(method = "fetchSkinTextures(Lcom/mojang/authlib/GameProfile;)Ljava/util/concurrent/CompletableFuture;",
            at = @At("HEAD"), cancellable = true)
    private void kimiko$localSkin(GameProfile profile, CallbackInfoReturnable<CompletableFuture<Optional<SkinTextures>>> cir) {
        SkinTextures local = SkinLibrary.skinFor(profile == null ? null : profile.name());
        if (local != null) {
            cir.setReturnValue(CompletableFuture.completedFuture(Optional.of(local)));
        }
    }

    @Inject(method = "supplySkinTextures", at = @At("HEAD"), cancellable = true)
    private void kimiko$localSkinSupplier(GameProfile profile, boolean requiresSecure, CallbackInfoReturnable<Supplier<SkinTextures>> cir) {
        SkinTextures local = SkinLibrary.skinFor(profile == null ? null : profile.name());
        if (local != null) {
            cir.setReturnValue(() -> local);
        }
    }
}
