/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.AssetInfo$TextureAssetInfo
 *  net.minecraft.util.AssetInfo$TextureAsset
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  net.minecraft.entity.player.SkinTextures
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package mixin.waveycapes;

import net.minecraft.util.AssetInfo;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.SkinTextures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.modules.impl.Visuals.Customization;
import rtx.kimiko.utils.render.others.cape.CapeGradient;

@Mixin(value={AbstractClientPlayerEntity.class})
public class CustomCapeMixin {
    @Unique
    private static final Identifier KIMIKO_CAPE_ASSET_ID = Identifier.of((String)Kimiko.namespace(), (String)"capes/cape");
    @Unique
    private static final Identifier KIMIKO_CAPE_TEXTURE = Identifier.of((String)Kimiko.namespace(), (String)"textures/capes/cape.png");
    @Unique
    private static final Identifier VANILLA_ELYTRA_ASSET_ID = Identifier.of((String)"minecraft", (String)"entity/equipment/wings/elytra");
    @Unique
    private static final Identifier VANILLA_ELYTRA_TEXTURE = Identifier.of((String)"minecraft", (String)"textures/entity/equipment/wings/elytra.png");
    @Unique
    private static final AssetInfo.TextureAsset KIMIKO_CAPE_ASSET = new AssetInfo.TextureAssetInfo(KIMIKO_CAPE_ASSET_ID, KIMIKO_CAPE_TEXTURE);
    @Unique
    private static final AssetInfo.TextureAsset VANILLA_ELYTRA_ASSET = new AssetInfo.TextureAssetInfo(VANILLA_ELYTRA_ASSET_ID, VANILLA_ELYTRA_TEXTURE);

    @Inject(method={"getSkin"}, at={@At(value="RETURN")}, cancellable=true)
    private void kimiko$replaceCape(CallbackInfoReturnable<SkinTextures> cir) {
        AbstractClientPlayerEntity player = (AbstractClientPlayerEntity)(Object)this;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || !CustomCapeMixin.kimiko$shouldUseCustomCape(player, (AbstractClientPlayerEntity)client.player)) {
            return;
        }
        Customization customization = Customization.getInstance();
        if (customization != null && customization.wingsEnabledFor(player)) {
            return;
        }
        CapeGradient.tick();
        SkinTextures skin = (SkinTextures)cir.getReturnValue();
        cir.setReturnValue(new SkinTextures(skin.body(), CapeGradient.asset(), skin.elytra() == null ? VANILLA_ELYTRA_ASSET : skin.elytra(), skin.model(), skin.secure()));
    }

    @Unique
    private static boolean kimiko$shouldUseCustomCape(AbstractClientPlayerEntity player, AbstractClientPlayerEntity localPlayer) {
        return player.getUuid().equals(localPlayer.getUuid());
    }
}

