/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.network.PlayerListEntry
 *  net.minecraft.network.message.SignedMessage
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 */
package mixin.chathads;

import mods.chathads.mixininterface.Ownable;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.network.message.SignedMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value={SignedMessage.class})
public abstract class PlayerChatMessageMixin
implements Ownable {
    @Unique
    private PlayerListEntry chatheads$owner;

    @Override
    public void chatheads$setOwner(PlayerListEntry playerInfo) {
        this.chatheads$owner = playerInfo;
    }

    @Override
    public PlayerListEntry chatheads$getOwner() {
        return this.chatheads$owner;
    }
}

