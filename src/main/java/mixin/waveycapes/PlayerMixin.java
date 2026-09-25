/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  net.minecraft.entity.PlayerLikeEntity
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityType
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.world.World
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.waveycapes;

import java.util.UUID;
import lombok.Generated;
import mods.waveycapes.delegate.PlayerDelegate;
import mods.waveycapes.versionless.CapeHolder;
import mods.waveycapes.versionless.ModBase;
import mods.waveycapes.versionless.sim.BasicSimulation;
import mods.waveycapes.versionless.util.Vector3;
import net.minecraft.entity.PlayerLikeEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={LivingEntity.class})
public abstract class PlayerMixin
extends Entity
implements CapeHolder {
    @Unique
    private BasicSimulation simulation;
    @Unique
    private Vector3 lastPlayerAnimatorPosition = new Vector3();
    @Unique
    private boolean dirty = false;
    @Unique
    private PlayerDelegate kimiko$playerDelegate;

    public PlayerMixin(EntityType<?> entityType, World level) {
        super(entityType, level);
    }

    @Override
    public void setDirty() {
        this.dirty = true;
    }

    @Inject(method={"tick"}, at={@At(value="TAIL")})
    private void moveCloakUpdate(CallbackInfo info) {
        if (ModBase.simulationBroken) {
            return;
        }
        if (!this.getEntityWorld().isClient()) {
            return;
        }
        if (!((Object)this instanceof PlayerLikeEntity)) {
            return;
        }
        PlayerLikeEntity entity = (PlayerLikeEntity)(Object)this;
        try {
            this.updateSimulation(16);
            PlayerDelegate playerDelegate = this.kimiko$playerDelegate;
            if (playerDelegate == null) {
                this.kimiko$playerDelegate = playerDelegate = new PlayerDelegate(entity);
            }
            BasicSimulation currentSimulation = this.getSimulation();
            if (this.dirty) {
                this.dirty = false;
                if (currentSimulation != null) {
                    currentSimulation.applyMovement(new Vector3(1.0f, 1.0f, 0.0f));
                    for (int i = 0; i < 5; ++i) {
                        this.simulate(playerDelegate);
                    }
                }
            }
            this.simulate(playerDelegate);
        }
        catch (Throwable throwable) {
            ModBase.simulationBroken = true;
            this.dirty = false;
            this.setSimulation(null);
        }
    }

    @Override
    public UUID getWCUUID() {
        return this.getUuid();
    }

    @Override
    @Generated
    public BasicSimulation getSimulation() {
        return this.simulation;
    }

    @Override
    @Generated
    public void setSimulation(BasicSimulation simulation) {
        this.simulation = simulation;
    }

    @Override
    @Generated
    public Vector3 getLastPlayerAnimatorPosition() {
        return this.lastPlayerAnimatorPosition;
    }

    @Override
    @Generated
    public void setLastPlayerAnimatorPosition(Vector3 lastPlayerAnimatorPosition) {
        this.lastPlayerAnimatorPosition = lastPlayerAnimatorPosition;
    }
}

