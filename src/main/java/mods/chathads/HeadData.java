/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.network.PlayerListEntry
 */
package mods.chathads;

import net.minecraft.client.network.PlayerListEntry;

public final class HeadData {
    public static final HeadData EMPTY = new HeadData(null, -1);
    public final PlayerListEntry playerInfo;
    public final int codePointIndex;

    public HeadData(PlayerListEntry playerInfo, int codePointIndex) {
        if (playerInfo == null && codePointIndex >= 0) {
            throw new AssertionError();
        }
        this.playerInfo = playerInfo;
        this.codePointIndex = codePointIndex;
    }

    public static HeadData of(PlayerListEntry playerInfo) {
        if (playerInfo == null) {
            return EMPTY;
        }
        return new HeadData(playerInfo, -1);
    }

    public boolean hasHeadPosition() {
        return this.codePointIndex >= 0;
    }
}

