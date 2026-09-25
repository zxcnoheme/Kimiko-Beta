/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  net.minecraft.entity.PlayerLikeEntity
 *  net.minecraft.client.network.ClientMannequinEntity
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 */
package mods.waveycapes.delegate;

import lombok.Generated;
import mods.waveycapes.versionless.nms.MinecraftPlayer;
import net.minecraft.entity.PlayerLikeEntity;
import net.minecraft.client.network.ClientMannequinEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;

public class PlayerDelegate
implements MinecraftPlayer {
    private PlayerLikeEntity player;

    @Override
    public double getXCloak() {
        float delta = MinecraftClient.getInstance().getRenderTickCounter().getTickProgress(false);
        PlayerLikeEntity playerLikeEntity2 = this.player;
        if (playerLikeEntity2 instanceof AbstractClientPlayerEntity) {
            AbstractClientPlayerEntity acp = (AbstractClientPlayerEntity)playerLikeEntity2;
            return acp.getState().lerpX(delta);
        }
        playerLikeEntity2 = this.player;
        if (playerLikeEntity2 instanceof ClientMannequinEntity) {
            ClientMannequinEntity cm = (ClientMannequinEntity)playerLikeEntity2;
            return cm.getState().lerpX(delta);
        }
        return 0.0;
    }

    @Override
    public double getZCloak() {
        float delta = MinecraftClient.getInstance().getRenderTickCounter().getTickProgress(false);
        PlayerLikeEntity playerLikeEntity2 = this.player;
        if (playerLikeEntity2 instanceof AbstractClientPlayerEntity) {
            AbstractClientPlayerEntity acp = (AbstractClientPlayerEntity)playerLikeEntity2;
            return acp.getState().lerpZ(delta);
        }
        playerLikeEntity2 = this.player;
        if (playerLikeEntity2 instanceof ClientMannequinEntity) {
            ClientMannequinEntity cm = (ClientMannequinEntity)playerLikeEntity2;
            return cm.getState().lerpZ(delta);
        }
        return 0.0;
    }

    @Override
    public float getYBodyRotO() {
        return this.player.lastBodyYaw;
    }

    @Override
    public float getYBodyRot() {
        return this.player.bodyYaw;
    }

    @Override
    public double getYo() {
        return this.player.lastY;
    }

    @Override
    public double getXo() {
        return this.player.lastX;
    }

    @Override
    public double getZo() {
        return this.player.lastZ;
    }

    @Generated
    public PlayerDelegate(PlayerLikeEntity player) {
        this.player = player;
    }

    @Generated
    public PlayerLikeEntity getPlayer() {
        return this.player;
    }

    @Override
    @Generated
    public boolean isVisuallySwimming() {
        return this.getPlayer().isInSwimmingPose();
    }

    @Override
    @Generated
    public float getXRot() {
        return this.getPlayer().getPitch();
    }

    @Override
    @Generated
    public boolean isCrouching() {
        return this.getPlayer().isInSneakingPose();
    }

    @Override
    @Generated
    public double getY() {
        return this.getPlayer().getY();
    }

    @Override
    @Generated
    public float getYRot() {
        return this.getPlayer().getYaw();
    }

    @Override
    @Generated
    public double getZ() {
        return this.getPlayer().getZ();
    }

    @Override
    @Generated
    public double getX() {
        return this.getPlayer().getX();
    }

    @Override
    @Generated
    public boolean isUnderWater() {
        return this.getPlayer().isSubmergedInWater();
    }
}

