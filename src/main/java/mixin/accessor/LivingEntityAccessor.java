/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Hand
 *  net.minecraft.entity.LivingEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package mixin.accessor;

import net.minecraft.util.Hand;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={LivingEntity.class})
public interface LivingEntityAccessor {
    @Accessor(value="jumpingCooldown")
    public void kimiko$setNoJumpDelay(int var1);

    @Accessor(value="handSwingProgress")
    public void kimiko$setAttackAnim(float var1);

    @Accessor(value="handSwinging")
    public void kimiko$setSwinging(boolean var1);

    @Accessor(value="handSwingTicks")
    public void kimiko$setSwingTime(int var1);

    @Accessor(value="preferredHand")
    public void kimiko$setSwingingArm(Hand var1);
}

