/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.client.network.ClientPlayerInteractionManager
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package mixin.accessor;

import net.minecraft.util.math.BlockPos;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={ClientPlayerInteractionManager.class})
public interface MultiPlayerGameModeAccessor {
    @Accessor(value="breakingBlock")
    public void kimiko$setDestroying(boolean var1);

    @Accessor(value="breakingBlock")
    public boolean kimiko$isDestroying();

    @Accessor(value="currentBreakingPos")
    public BlockPos kimiko$getDestroyBlockPos();

    @Invoker(value="syncSelectedSlot")
    public void kimiko$ensureHasSentCarriedItem();

    @Accessor(value="blockBreakingCooldown")
    public void kimiko$setDestroyDelay(int var1);

    @Accessor(value="currentBreakingProgress")
    public float kimiko$getDestroyProgress();

    @Accessor(value="currentBreakingProgress")
    public void kimiko$setDestroyProgress(float var1);
}

