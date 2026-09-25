/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.entity.state.PlayerEntityRenderState
 *  net.minecraft.item.equipment.EquipmentAssetKeys
 *  net.minecraft.component.type.EquippableComponent
 *  net.minecraft.entity.PlayerLikeEntity
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  net.minecraft.client.network.ClientPlayerEntity
 *  net.minecraft.component.DataComponentTypes
 */
package mods.waveycapes.compat;

import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.PlayerLikeEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.component.DataComponentTypes;

public class PlayerWrapper {
    private final PlayerEntityRenderState renderState;
    private final PlayerLikeEntity avatar;

    public PlayerWrapper(PlayerEntityRenderState renderState) {
        this.renderState = renderState;
        this.avatar = PlayerWrapper.findAvatar(renderState);
    }

    public PlayerWrapper(AbstractClientPlayerEntity player) {
        this.renderState = null;
        this.avatar = player;
    }

    public PlayerEntityRenderState getRenderState() {
        return this.renderState;
    }

    public PlayerLikeEntity getAvatar() {
        return this.avatar;
    }

    public PlayerLikeEntity getEntity() {
        return this.avatar;
    }

    public Identifier getCapeTexture() {
        AbstractClientPlayerEntity player;
        if (this.renderState != null && this.renderState.skinTextures != null && this.renderState.skinTextures.cape() != null) {
            return this.renderState.skinTextures.cape().texturePath();
        }
        PlayerLikeEntity playerLikeEntity2 = this.avatar;
        if (playerLikeEntity2 instanceof AbstractClientPlayerEntity && (player = (AbstractClientPlayerEntity)playerLikeEntity2).getSkin().cape() != null) {
            return player.getSkin().cape().texturePath();
        }
        return null;
    }

    public boolean isCapeVisible() {
        return this.renderState == null || this.renderState.capeVisible;
    }

    public boolean isLocalPlayer() {
        ClientPlayerEntity localPlayer = MinecraftClient.getInstance().player;
        return localPlayer != null && this.avatar != null && this.avatar.getUuid().equals(localPlayer.getUuid());
    }

    public boolean isPlayerInvisible() {
        return this.renderState != null ? this.renderState.invisible || this.renderState.invisibleToPlayer : this.avatar != null && this.avatar.isInvisible();
    }

    public boolean hasElytraEquipped() {
        if (this.renderState != null) {
            return this.renderState.isGliding || PlayerWrapper.isElytra(this.renderState.equippedChestStack);
        }
        return false;
    }

    public boolean hasChestplateEquipped() {
        return this.renderState != null && this.renderState.equippedChestStack != null && !this.renderState.equippedChestStack.isEmpty() && !PlayerWrapper.isElytra(this.renderState.equippedChestStack);
    }

    private static PlayerLikeEntity findAvatar(PlayerEntityRenderState renderState) {
        PlayerLikeEntity avatar;
        if (renderState == null || MinecraftClient.getInstance().world == null) {
            return null;
        }
        Entity entity = MinecraftClient.getInstance().world.getEntityById(renderState.id);
        return entity instanceof PlayerLikeEntity ? (avatar = (PlayerLikeEntity)entity) : null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean isElytra(ItemStack stack) {
        if (stack == null) return false;
        if (stack.isEmpty()) {
            return false;
        }
        EquippableComponent equippable = (EquippableComponent)stack.get(DataComponentTypes.EQUIPPABLE);
        if (equippable == null) return false;
        if (equippable.assetId().map(EquipmentAssetKeys.ELYTRA::equals).orElse(false) == false) return false;
        return true;
    }
}

