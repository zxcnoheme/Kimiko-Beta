/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.exceptions.MinecraftClientHttpException
 *  com.mojang.authlib.minecraft.UserApiService
 *  com.mojang.authlib.yggdrasil.response.KeyPairResponse
 *  net.minecraft.client.session.ProfileKeysImpl
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package mixin;

import com.mojang.authlib.exceptions.MinecraftClientHttpException;
import com.mojang.authlib.minecraft.UserApiService;
import com.mojang.authlib.yggdrasil.response.KeyPairResponse;
import net.minecraft.client.session.ProfileKeysImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={ProfileKeysImpl.class})
public abstract class AccountProfileKeyPairManagerMixin {
    @Redirect(method={"fetchKeyPair"}, at=@At(value="INVOKE", target="Lcom/mojang/authlib/minecraft/UserApiService;getKeyPair()Lcom/mojang/authlib/yggdrasil/response/KeyPairResponse;"), require=0)
    private KeyPairResponse kimiko$skipUnauthorizedProfileKeyFetch(UserApiService userApiService) {
        try {
            return userApiService.getKeyPair();
        }
        catch (MinecraftClientHttpException exception) {
            if (exception.getStatus() == 401) {
                return null;
            }
            throw exception;
        }
    }
}

