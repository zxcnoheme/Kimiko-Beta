/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraft.registry.RegistryKey
 *  net.minecraft.client.network.ClientPlayNetworkHandler
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.world.ClientWorld$Properties
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package mixin.accessor;

import java.util.Set;
import net.minecraft.world.World;
import net.minecraft.registry.RegistryKey;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={ClientPlayNetworkHandler.class})
public interface ClientPacketListenerAccessor {
    @Accessor(value="world")
    public void kimiko$setLevel(ClientWorld var1);

    @Accessor(value="worldProperties")
    public void kimiko$setLevelData(ClientWorld.Properties var1);

    @Accessor(value="worldProperties")
    public ClientWorld.Properties kimiko$getLevelData();

    @Accessor(value="chunkLoadDistance")
    public void kimiko$setServerChunkRadius(int var1);

    @Accessor(value="chunkLoadDistance")
    public int kimiko$getServerChunkRadius();

    @Accessor(value="simulationDistance")
    public void kimiko$setServerSimulationDistance(int var1);

    @Accessor(value="simulationDistance")
    public int kimiko$getServerSimulationDistance();

    @Accessor(value="worldKeys")
    public void kimiko$setLevels(Set<RegistryKey<World>> var1);
}

