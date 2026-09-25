/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.network.PlayerListEntry
 */
package mods.chathads.mixininterface;

import net.minecraft.client.network.PlayerListEntry;

public interface Ownable {
    public PlayerListEntry chatheads$getOwner();

    public void chatheads$setOwner(PlayerListEntry var1);
}

