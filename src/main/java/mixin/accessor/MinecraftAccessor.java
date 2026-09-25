/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.world.ClientWorld
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package mixin.accessor;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={MinecraftClient.class})
public interface MinecraftAccessor {
    @Invoker(value="doAttack")
    public boolean kimiko$startAttack();

    @Invoker(value="doItemUse")
    public void kimiko$startUseItem();

    @Accessor(value="itemUseCooldown")
    public int kimiko$getRightClickDelay();

    @Accessor(value="itemUseCooldown")
    public void kimiko$setRightClickDelay(int var1);

    @Invoker(value="setWorld")
    public void kimiko$updateLevelInEngines(ClientWorld var1);
}

