/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.hud.ChatHud$ChatState
 *  net.minecraft.registry.DynamicRegistryManager$Immutable
 *  net.minecraft.resource.featuretoggle.FeatureSet
 *  net.minecraft.client.network.ClientConfigurationNetworkHandler
 *  net.minecraft.client.network.ClientRegistries
 *  net.minecraft.client.resource.ClientDataPackManager
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package mixin.accessor;

import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.client.network.ClientConfigurationNetworkHandler;
import net.minecraft.client.network.ClientRegistries;
import net.minecraft.client.resource.ClientDataPackManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={ClientConfigurationNetworkHandler.class})
public interface ClientConfigurationPacketListenerAccessor {
    @Accessor(value="clientRegistries")
    public ClientRegistries kimiko$getRegistryDataCollector();

    @Accessor(value="registryManager")
    public DynamicRegistryManager.Immutable kimiko$getReceivedRegistries();

    @Accessor(value="enabledFeatures")
    public FeatureSet kimiko$getEnabledFeatures();

    @Accessor(value="dataPackManager")
    public ClientDataPackManager kimiko$getKnownPacks();

    @Accessor(value="chatState")
    public ChatHud.ChatState kimiko$getChatState();
}

